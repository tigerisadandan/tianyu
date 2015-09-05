package com.ccthanking.framework.fileUpload.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.imgscalr.Scalr;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ccthanking.business.commons.Utils;
import com.ccthanking.business.sgenter.vo.AtFileuploadVO;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.base.BaseVO;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.fileUpload.vo.FileUploadVO;
import com.ccthanking.framework.model.requestJson;
import com.ccthanking.framework.params.ParaManager;
import com.ccthanking.framework.params.SysPara.SysParaConfigureVO;
import com.ccthanking.framework.util.Pub;
import com.ccthanking.framework.util.RandomGUID;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;

public class FileUploadService {

	private static Logger logger = LoggerFactory.getLogger(FileUploadService.class);

	public FileUploadService() {
		SysParaConfigureVO syspara = (SysParaConfigureVO) ParaManager.getInstance().getSysParameter("FILEUPLOADROOT");
		fileRoot = syspara.PARAVALUE1;
	}

	public static String fileRoot = "";

	/**
	 * 
	 * @param request
	 * @param fileRoot
	 *            这个参数不再使用了
	 * @return
	 * @throws Exception
	 */
	public JSONArray doInsert(HttpServletRequest request, String fileRoot1) throws Exception {
		ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());
		uploadHandler.setHeaderEncoding("UTF-8");
		String root = request.getContextPath();
		JSONArray json = new JSONArray();
		String ywid = request.getParameter("ywid");
		String target_type = request.getParameter("target_type");
		String file_type = request.getParameter("file_type");
		String business_type = request.getParameter("business_type");
		boolean hasYwid = false;

		User user = RestContext.getCurrentUser();
		try {
			String fileID = "";// 附件ID
			String fileDir = "";// 文件路径
			String fileName = "";// 文件名称
			List<FileItem> items = uploadHandler.parseRequest(request);
			if (Pub.empty(ywid)) {
				ywid = new RandomGUID().toString();
				hasYwid = false;
			} else {
				hasYwid = true;
			}
			for (FileItem item : items) {
				if (!item.isFormField()) {
					// 生成附件ID，40位
					fileID = new RandomGUID().toString();
					// 文件保存路径：根路径/项目ID/附件ID
					fileDir = fileRoot + "/";
					// 保存到系统的文件名，默认使用上传文件的名字
					fileName = item.getName();
					if (target_type == "0038" || "0038".equals(target_type)) {
						SysParaConfigureVO syspara = (SysParaConfigureVO) ParaManager.getInstance().getSysParameter(
								"SYSTEM_USER_TX");
						fileRoot = syspara.PARAVALUE1;
						fileDir = fileRoot;
						fileName = fileID + "." + this.getSuffix(fileName);
					} else if (target_type == "0039" || "0039".equals(target_type)) {
						SysParaConfigureVO syspara = (SysParaConfigureVO) ParaManager.getInstance().getSysParameter(
								"SYSTEM_USER_QM");
						fileRoot = syspara.PARAVALUE1;
						fileDir = fileRoot;
						fileName = fileID + "." + this.getSuffix(fileName);
					}
					// 文件夹不存在的话，需要创建文件夹，否则直接写入文件
					File file = new File(fileDir);
					if (!file.exists() && !file.isDirectory()) {
						file.mkdirs();
						file = new File(fileDir);
						file.mkdir();
					} else {
						// do nothing
					}
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String sequenceVal = DBUtil.getSequenceValue("AT_FILEUPLOAD_UID");
					String fileRealName = sequenceVal + fileName.substring(fileName.lastIndexOf("."));
					file = new File(fileDir, fileRealName);
					item.write(file);
					String size = this.resizeImage(file);// 调整图片类文件的大小
					JSONObject jsono = new JSONObject();
					jsono.put("name", fileName);
					jsono.put("CREATED_NAME", user.getName());// 附件录入人
					jsono.put("CREATED_UID", user.getUserSN());
					jsono.put("CREATED_DATE", format.format(new Date()));
					jsono.put("TARGET_TYPE", target_type); // 附件类别
					jsono.put("TARGET_UID", ywid); // 业务编号
					jsono.put("FILE_TYPE", file_type);
					jsono.put("AT_FILEUPLOAD_UID", sequenceVal); // 附件编号
					jsono.put("FILE_SAVE_NAME", fileRealName); // 附件路径
					jsono.put("fileType", this.getMimeType(file));// 获取附件类型
					jsono.put("DOC_SIZE", size.replace("KB", ""));
					jsono.put("size", size);
					jsono.put("MIME_TYPE", item.getContentType());
					jsono.put("EXT_NAME", fileName.substring(fileName.lastIndexOf(".") + 1));
					jsono.put("FILE_NAME", fileName);
					jsono.put(
							"url",
							root + "/UploadServlet?getfile=" + fileRealName + "&fileDir="
									+ fileDir.replace(fileRoot1, ""));
					jsono.put(
							"url_look",
							root + "/UploadServlet?getfile=" + fileRealName + "&fileDir="
									+ fileDir.replace(fileRoot1, "") + "&getLook=1");
					jsono.put(
							"thumbnail_url",
							root + "/UploadServlet?getthumb=" + fileRealName + "&fileDir="
									+ fileDir.replace(fileRoot1, "") + "/");
					jsono.put(
							"delete_url",
							root + "/UploadServlet?delfile=" + fileRealName + "&fileDir="
									+ fileDir.replace(fileRoot1, "") + "/&AT_FILEUPLOAD_UID=" + sequenceVal + "&ywid="
									+ ywid + "&business_type=" + business_type);
					jsono.put("modifyflag_url", root + "/UploadServlet?modifyflag=" + fileRealName + "&fileDir="
							+ fileDir.replace(fileRoot1, "") + "/&AT_FILEUPLOAD_UID=" + sequenceVal);
					jsono.put("delete_type", "GET");
					jsono.put("BUSINESS_SUB_TYPE", business_type);

					json.put(jsono);
					// System.out.println(json.toString());
				}
			}
			if (hasYwid) {
				insertTable(json, request);
			} else {
				insertSession(json, request);
			}
		} catch (Exception e) {
			throw e;
		}
		return json;
	}

	public String resizeImage(File file) throws Exception {
		String size = "";
		try {
			String mimetype = this.getMimeType(file);
			File newFile = new File(file.getAbsolutePath());
			if (mimetype.endsWith("png") || mimetype.endsWith("jpeg") || mimetype.endsWith("jpg")) {

				String imgType = mimetype.substring(6);
				
				//把jpg类型文件重写(java 不支持 某些图片的操作（放大，缩小）)
				if(mimetype.endsWith("jpeg") || mimetype.endsWith("jpg")){
					JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(new FileInputStream(newFile));  
			        BufferedImage image = decoder.decodeAsBufferedImage();  
			        ImageIO.write(image, "JPEG", newFile); 
			        
				}
				// 读入图片文件
				BufferedImage im = ImageIO.read(newFile);
				// 图片高度超出尺寸时，重置文件大小
				if (im != null && im.getHeight() > 1280) {
					BufferedImage thumb = Scalr.resize(im, 1280);// 重新定义图片的高度
					FileOutputStream op = new FileOutputStream(newFile);
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					ImageIO.write(thumb, getSuffix(file.getName()), out);// 将图片写入输出流
					byte[] b = out.toByteArray();
					op.write(b);
					op.close();
				} else {
					ImageIO.write(im, imgType, newFile);
				}
			}
			Double d = new Double(2);
			d = Double.parseDouble(Long.toString(newFile.length()));
			java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
			size = df.format(d / 1000) + "KB";
			if (size.startsWith(".")) {
				size = "0" + size;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return size;
	}

	/**
	 * 获得VO集合
	 * 
	 * @param json
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private List<AtFileuploadVO> doInitVO(JSONArray json, HttpServletRequest request) throws Exception {
		List<AtFileuploadVO> list = new ArrayList<AtFileuploadVO>();
		String fjzt = request.getParameter("enabled");
		User user = RestContext.getCurrentUser();
		try {
			for (int i = 0; i < json.length(); i++) {
				AtFileuploadVO vo = new AtFileuploadVO();
				JSONObject jsono = (JSONObject) json.get(i);
				vo.setAt_fileupload_uid((String) jsono.get("AT_FILEUPLOAD_UID"));
				vo.setCreated_uid(user.getUserSN());
				vo.setCreated_name(user.getName());
				vo.setCreated_date(new Date());
				vo.setTarget_uid((String) jsono.get("TARGET_UID"));
				vo.setTarget_type((String) jsono.get("TARGET_TYPE"));
				// 处理url
				String fileDir = vo.getTarget_uid() + "/" + vo.getAt_fileupload_uid();
				vo.setFile_save_name(fileDir + "/" + (String) jsono.get("name"));
				// 区分新增页面的插入附件和查询页面的插入附件
				// if(fjzt=="1" || "1".equals(fjzt)){
				vo.setEnabled("1");// 表示已生效
				// }else{
				// vo.setEnabled("0");//表示刚存入，还未生效
				// }
				vo.setMime_type(jsono.getString("MIME_TYPE"));
				vo.setExt_name((String) jsono.get("EXT_NAME"));
				vo.setDoc_size((String) jsono.get("DOC_SIZE"));
				vo.setBusiness_sub_type((String) jsono.get("BUSINESS_SUB_TYPE"));
				vo.setFile_type((String) jsono.get("FILE_TYPE"));
				vo.setFile_name((String) jsono.getString("FILE_NAME"));
				vo.setFile_save_name((String) jsono.getString("FILE_SAVE_NAME"));

				list.add(vo);
			}
		} catch (Exception e) {
			throw e;
		}
		return list;
	}

	/**
	 * 将VO信息存入数据库表
	 * 
	 * @param json
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private String insertTable(JSONArray json, HttpServletRequest request) throws Exception {
		Connection conn = DBUtil.getConnection();
		String resultVO = null;
		try {
			conn.setAutoCommit(false);
			List<AtFileuploadVO> list = this.doInitVO(json, request);
			for (AtFileuploadVO vo : list) {
				BaseDAO.insert(conn, vo);
				resultVO = vo.getRowJson();
			}
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace(System.out);
			throw e;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return resultVO;
	}

	/**
	 * 将VO信息存入Session
	 * 
	 * @param json
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private String insertSession(JSONArray json, HttpServletRequest request) throws Exception {
		String resultVO = null;
		try {
			String business_type = request.getParameter("business_type");
			Map<String, AtFileuploadVO> listFiles = null;
			if (request.getSession().getAttribute(Constants.FILE_KEY) == null) {
				request.getSession().setAttribute(Constants.FILE_KEY, new HashMap<String, AtFileuploadVO>());
			}
			listFiles = (Map<String, AtFileuploadVO>) request.getSession().getAttribute(Constants.FILE_KEY);
			List<AtFileuploadVO> list = this.doInitVO(json, request);
			for (int i = 0; i < list.size(); i++) {
				if (listFiles.get(list.get(i).getTarget_uid()) == null) {
					listFiles.put(list.get(i).getTarget_uid(), list.get(i));
				}
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
			throw e;
		}
		return resultVO;
	}

	/**
	 * 根据业务ID更新附件状态
	 * 
	 * @param conn
	 * @param ywid
	 * @return
	 * @throws Exception
	 */
	public static boolean updateFjztByYwid(Connection conn, String ywid) throws Exception {
		boolean flag = true;
		try {
			// BaseDAO.delete(conn, vo);
			// conn.commit();
			if (!Pub.empty(ywid)) {
				String sql = "update fs_fileupload set fjzt='1'" + " where ywid='" + ywid + "' and fjzt='0'";
				DBUtil.execUpdateSql(conn, sql);
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			// 传入的conn不由我关闭
		}
		return flag;
	}

	/**
	 * 根据FileUploadVO更新附件状态
	 * 
	 * @param conn
	 * @param ywid
	 * @return
	 * @throws Exception
	 */
	public static boolean updateFjztByVO(Connection conn, FileUploadVO vo) throws Exception {
		boolean flag = true;
		try {
			if (!Pub.empty(vo.getYwid())) {
				String sql = "update fs_fileupload set fjzt='1'," + "glid1='" + vo.getGlid1() + "'," + "sjbh='"
						+ vo.getSjbh() + "'," + "ywlx='" + vo.getYwlx() + "' " + " where ywid='" + vo.getYwid()
						+ "' and fjzt='0'";
				DBUtil.execUpdateSql(conn, sql);
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			// 传入的conn不由我关闭
		}
		return flag;
	}

	/**
	 * 根据YWID更新附件信息
	 * 
	 * @param conn
	 * @param vo
	 * @param ywid
	 * @return
	 * @throws Exception
	 */
	public static boolean updateVOByYwid(Connection conn, FileUploadVO vo, String ywid) throws Exception {
		boolean flag = true;
		try {
			String sql = "select FILEID from FS_FILEUPLOAD where ywid='" + ywid + "'";
			String[][] arr = DBUtil.query(conn, sql);
			if (arr != null) {
				for (int i = 0; i < arr.length; i++) {
					vo.setFileid(arr[i][0]);
					BaseDAO.update(conn, vo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			// 传入的conn不由我关闭
		}
		return flag;
	}

	/**
	 * 根据YWID更新附件信息
	 * 
	 * @param conn
	 * @param vo
	 * @param ywid
	 * @return
	 * @throws Exception
	 */
	public static boolean updateVOByYwid(Connection conn, FileUploadVO vo, String ywid, User user) throws Exception {
		boolean flag = true;
		try {
			String sql = "select FILEID from FS_FILEUPLOAD where ywid='" + ywid + "' and lrr='" + user.getUserSN()
					+ "' and fjzt='0'" + " union all " + " select FILEID from FS_FILEUPLOAD where ywid='" + ywid
					+ "' and fjzt='1'";
			String[][] arr = DBUtil.query(conn, sql);
			if (arr != null) {
				for (int i = 0; i < arr.length; i++) {
					vo.setFileid(arr[i][0]);
					vo.setGxr(user.getUserSN());
					vo.setGxbm(user.getDepartment());
					vo.setGxsj(Pub.getCurrentDate());
					BaseDAO.update(conn, vo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			// 传入的conn不由我关闭
		}
		return flag;
	}

	/**
	 * 根据GLID1更新附件信息
	 * 
	 * @param conn
	 * @param vo
	 * @param glid1
	 * @return
	 * @throws Exception
	 */
	public static boolean updateVOByGlid1(Connection conn, FileUploadVO vo, String glid1, User user) throws Exception {
		boolean flag = true;
		try {
			String sql = "select FILEID from FS_FILEUPLOAD where glid1='" + glid1 + "' and lrr='" + user.getUserSN()
					+ "' and fjzt='0'" + " union all " + " select FILEID from FS_FILEUPLOAD where glid1='" + glid1
					+ "' and fjzt='1'";
			String[][] arr = DBUtil.query(conn, sql);
			if (arr != null) {
				for (int i = 0; i < arr.length; i++) {
					vo.setFileid(arr[i][0]);
					vo.setGxr(user.getUserSN());
					vo.setGxbm(user.getDepartment());
					vo.setGxsj(Pub.getCurrentDate());
					BaseDAO.update(conn, vo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			// 传入的conn不由我关闭
		}
		return flag;
	}

	/**
	 * 根据FileUploadVO更新附件信息
	 * 
	 * @param conn
	 * @param vo
	 * @param condVO
	 * @return
	 * @throws Exception
	 */
	public static boolean updateVOByCondVO(Connection conn, FileUploadVO vo, FileUploadVO condVO) throws Exception {
		boolean flag = true;
		int createFlag = 0;
		try {
			BaseVO[] bv = null;
			if (conn == null) {
				createFlag = 1;
				conn = DBUtil.getConnection();
			}
			if (condVO != null && !condVO.isEmpty()) {
				bv = (BaseVO[]) BaseDAO.getVOByCondition(conn, condVO);
			}
			if (bv != null) {
				for (int i = 0; i < bv.length; i++) {
					FileUploadVO newvo = (FileUploadVO) bv[i];
					vo.setFileid(newvo.getFileid());
					BaseDAO.update(conn, vo);
				}
			}
			if (createFlag == 1) {
				// 本方法创建的conn由本方法提交
				conn.commit();
			}
		} catch (Exception e) {
			if (createFlag == 1) {
				// 本方法创建的conn由本方法回撤
				DBUtil.rollbackConnetion(conn);
			}
			e.printStackTrace();
			throw e;
		} finally {
			if (createFlag == 1) {
				// 本方法创建的conn由本方法关闭
				DBUtil.closeConnetion(conn);
			}
		}
		return flag;
	}

	/**
	 * 根据业务ID更新附件状态
	 * 
	 * @param conn
	 * @param ywid
	 * @return
	 * @throws Exception
	 */
	public static boolean updateFjztByYwid(String ywid) throws Exception {
		boolean flag = true;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			if (!Pub.empty(ywid)) {
				String sql = "update fs_fileupload set fjzt='1'" + " where ywid='" + ywid + "' and fjzt='0'";
				DBUtil.execUpdateSql(conn, sql);
			} else {
				flag = false;
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return flag;
	}

	/**
	 * 根据FileUploadVO更新附件状态
	 * 
	 * @param conn
	 * @param ywid
	 * @return
	 * @throws Exception
	 */
	public static boolean updateFjztByVO(FileUploadVO vo) throws Exception {
		boolean flag = true;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			if (!Pub.empty(vo.getYwid())) {
				String sql = "update fs_fileupload set fjzt='1'," + "glid1='" + vo.getGlid1() + "'," + "sjbh='"
						+ vo.getSjbh() + "'," + "ywlx='" + vo.getYwlx() + "' " + " where ywid='" + vo.getYwid()
						+ "' and fjzt='0'";
				DBUtil.execUpdateSql(conn, sql);
			} else {
				flag = false;
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return flag;
	}

	/**
	 * 根据新增的业务编号更新附件状态
	 * 
	 * @param conn
	 * @param newywid
	 *            ,oldywid
	 * @return
	 * @throws Exception
	 */
	public static boolean updateFjztByYwid(Connection conn, String newywid, String oldywid) throws Exception {
		boolean flag = true;
		// Connection conn = null;
		try {
			// conn = DBUtil.getConnection();
			if (!Pub.empty(newywid)) {
				String sql = "update fs_fileupload set fjzt='1'," + "ywid='" + newywid + "'" +
				// "sjbh='"+vo.getSjbh()+"'," +
				// "ywlx='"+vo.getYwlx()+"' " +
						" where ywid='" + oldywid + "'";
				DBUtil.execUpdateSql(conn, sql);
			} else {
				flag = false;
			}
			// conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			// if (conn != null) {
			// conn.close();
			// }
		}
		return flag;
	}

	/**
	 * public static boolean deleteByYwid(Connection conn,String ywid) throws
	 * Exception{ boolean b = false; try{ if(conn==null){ conn =
	 * DBUtil.getConnection(); } String sql =
	 * "select FILEID,FILENAME,URL from fs_fileupload where ywid='"+ywid+"'";
	 * String[][] arr = DBUtil.query(conn, sql); if(arr!=null){ for(int
	 * i=0;i<arr.length;i++){ FileUploadVO vo = new FileUploadVO();
	 * vo.setFileid(arr[i][0]); BaseDAO.delete(conn, vo); //1.先删除文件 File file =
	 * new File(fileRoot+"/"+arr[i][2]); if (file.exists()) { file.delete(); //
	 * TODO:check and report success } } } b = true; }catch(Exception e){
	 * e.printStackTrace(); throw e; } return b; }
	 */

	public static boolean deleteByConditionVO(Connection conn, FileUploadVO condVO) throws Exception {
		boolean b = false;
		int createFlag = 0;
		try {
			BaseVO[] bv = null;
			if (conn == null) {
				createFlag = 1;
				conn = DBUtil.getConnection();
			}
			if (condVO != null && !condVO.isEmpty()) {
				bv = (BaseVO[]) BaseDAO.getVOByCondition(conn, condVO);
			}
			if (bv != null) {
				for (int i = 0; i < bv.length; i++) {
					FileUploadVO vo = (FileUploadVO) bv[i];
					if ("0".equals(vo.getFjzt())) {
						// 如果附件状态为0，那么删除附件
						BaseDAO.delete(conn, vo);
						// 1.先删除文件
						File file = new File(fileRoot + "/" + vo.getUrl());
						if (file.exists()) {
							file.delete(); // TODO:check and report success
						}
					} else {
						// 如果附件状态不为0，那么把附件状态置为2（被删除）,文件和记录都保留
						vo.setFjzt("2");
						BaseDAO.update(conn, vo);
					}
				}
			}
			b = true;
			if (createFlag == 1) {
				// 本方法创建的conn由本方法提交
				conn.commit();
			}
		} catch (Exception e) {
			if (createFlag == 1) {
				// 本方法创建的conn由本方法回撤
				DBUtil.rollbackConnetion(conn);
			}
			e.printStackTrace();
			throw e;
		} finally {
			if (createFlag == 1) {
				// 本方法创建的conn由本方法关闭
				DBUtil.closeConnetion(conn);
			}
		}
		return b;
	}

	/**
	 * 附件删除方法
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete(AtFileuploadVO vo) throws Exception {
		Connection conn = null;
		String resultVO = null;
		try {
			conn = DBUtil.getConnection();
			BaseDAO.delete(conn, vo);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			DBUtil.rollbackConnetion(conn);
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return resultVO;
	}

	/**
	 * 附件修改方法
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update(AtFileuploadVO vo) throws Exception {
		Connection conn = null;
		String resultVO = null;
		try {
			conn = DBUtil.getConnection();
			BaseDAO.update(conn, vo);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			DBUtil.rollbackConnetion(conn);
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return resultVO;
	}

	/**
	 * 附件修改方法
	 * 
	 * @return
	 * @throws Exception
	 */
	public FileUploadVO getLastFileVO(FileUploadVO condVO) throws Exception {
		Connection conn = null;
		BaseVO[] bv = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select fileid from fs_fileupload where ywid='" + condVO.getYwid() + "' and fjlb='"
					+ condVO.getFjlb() + "' order by lrsj desc";
			String arr[][] = DBUtil.query(conn, sql);
			if (arr != null) {
				condVO.setFileid(arr[0][0]);
				bv = (BaseVO[]) BaseDAO.getVOByCondition(conn, condVO);
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			DBUtil.rollbackConnetion(conn);
		} finally {
			DBUtil.closeConnetion(conn);
		}
		if (bv == null) {
			return new FileUploadVO();
		} else {
			return (FileUploadVO) bv[0];
		}
	}

	/**
	 * 根据项目ID查询附件方法
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String queryFile(HttpServletRequest request, requestJson js) throws Exception {
		JSONArray json = new JSONArray();
		String root = request.getContextPath();
		Connection conn = null;
		String domresult = "";
		try {
			conn = DBUtil.getConnection();
			List<AtFileuploadVO> list = this.getFileUploadVOByJson(conn, js);
			for (AtFileuploadVO vo : list) {
				String fileDir = vo.getFile_save_name();
				JSONObject jsono = new JSONObject();
				jsono.put("name", vo.getFile_name());
				jsono.put("size", vo.getDoc_size() + "KB");// 暂时不准备使用大小
				jsono.put("DOC_SIZE", vo.getDoc_size());
				jsono.put("CREATED_NAME", vo.getCreated_name());// 附件录入人
				jsono.put("CREATED_UID", vo.getCreated_uid());
				jsono.put("CREATED_DATE", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(vo.getCreated_date()));
				jsono.put("TARGET_TYPE", vo.getTarget_type()); // 附件类别
				jsono.put("TARGET_UID", vo.getTarget_uid()); // 附件类别
				jsono.put("AT_FILEUPLOAD_UID", vo.getAt_fileupload_uid());
				// jsono.put("xmid", xmid); //项目编号
				jsono.put("FILE_SAVE_NAME", fileDir);
				jsono.put("EXT_NAME", vo.getExt_name());
				jsono.put("fileType", vo.getExt_name()); // 获取附件类型
				jsono.put("FILE_TYPE", vo.getFile_type()); // 获取附件类型
				jsono.put("FILE_TYPE_SV", Pub.getDictValueByCode("WDLX", vo.getFile_type()));
				jsono.put("FILE_NAME", vo.getFile_name()); // 附件类别
				jsono.put("url", root + "/UploadServlet?getfile=" + vo.getFile_save_name()
						+ "&fileDir=/&AT_FILEUPLOAD_UID=" + vo.getAt_fileupload_uid());
				jsono.put("thumbnail_url", root + "/UploadServlet?getthumb=" + vo.getFile_save_name()
						+ "&fileDir=/&AT_FILEUPLOAD_UID=" + vo.getAt_fileupload_uid());
				jsono.put(
						"delete_url",
						root + "/UploadServlet?delfile=" + vo.getFile_save_name() + "&fileDir=/&fileid="
								+ vo.getAt_fileupload_uid() + "&AT_FILEUPLOAD_UID=" + vo.getAt_fileupload_uid());
				jsono.put("delete_type", "GET");
				jsono.put("BUSINESS_SUB_TYPE", vo.getBusiness_sub_type());
				json.put(jsono);

			}
			domresult = json.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			conn.close();
		}
		return domresult;
	}

	/**
	 * 这个是临时使用的方法，由于BaseDAO.getVOByCondition方法不能使用，只能先自己查询了
	 * 
	 * @param conn
	 *            数据库连接
	 * @param js
	 *            前台传入条件
	 * @return
	 */
	private List<AtFileuploadVO> getFileUploadVOByJson(Connection conn, requestJson js) throws Exception {
		// FileUploadVO condVO = new FileUploadVO();
		// List list = new ArrayList();
		// try{
		// net.sf.json.JSONArray tlist = condVO.doInitJson(js.getMsg());
		// condVO.setValueFromJson((net.sf.json.JSONObject)tlist.get(0));
		// //保证至少存在一个条件，否则不允许查询
		// if(!(Pub.empty(condVO.getYwid()) && Pub.empty(condVO.getGlid1()) &&
		// Pub.empty(condVO.getYwlx()) && Pub.empty(condVO.getSjbh()))){
		// condVO.setFjzt("1");
		// BaseVO[] bv = (BaseVO [])BaseDAO.getVOByCondition(conn, condVO);
		// if(bv!=null){
		// for(int i=0;i<bv.length;i++){
		// FileUploadVO vo = (FileUploadVO)bv[i];
		// list.add(vo);
		// }
		// }
		// }
		// }catch(Exception e){
		// e.printStackTrace();
		//
		// }
		// return list;
		ResultSet rs = null;
		ResultSetMetaData md = null;
		PreparedStatement ps = null;
		List<AtFileuploadVO> list = new ArrayList<AtFileuploadVO>();
		try {
			String sql = "select * from at_fileupload where ENABLED=1 ";
			AtFileuploadVO condVO = new AtFileuploadVO();
			net.sf.json.JSONArray tlist = condVO.doInitJson(js.getMsg());
			condVO.setValueFromJson((net.sf.json.JSONObject) tlist.get(0));
			String cond = "";
			// 保证至少存在一个条件，否则不允许查询
			if (!Pub.empty(condVO.getTarget_uid())) {
				// 处理业务ID条件

				if (condVO.getTarget_uid().indexOf(",") != -1) {
					String[] tempArr = condVO.getTarget_uid().split(",");
					for (int i = 0; i < tempArr.length; i++) {
						cond += "  target_uid ='" + tempArr[i] + "' or ";
					}
					cond = " and (" + cond.substring(0, cond.length() - 3) + ")";
				} else {
					cond += " and target_uid='" + condVO.getTarget_uid() + "'";
				}
				if (StringUtils.isNotBlank(condVO.getBusiness_sub_type())) {
					cond += " and business_sub_type ='" + condVO.getBusiness_sub_type() + "' ";
				}
				sql = sql + cond;
				logger.debug(sql);

				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				md = rs.getMetaData();
				while (rs.next()) {
					AtFileuploadVO res = new AtFileuploadVO();
					for (int i = 1; i <= md.getColumnCount(); i++) {
						String colname = md.getColumnName(i).toUpperCase();
						if (!res.isValidField(colname))
							continue;
						int coltype = md.getColumnType(i);
						if (coltype == java.sql.Types.DATE || coltype == java.sql.Types.TIMESTAMP) {
							if (rs.getDate(i) != null)
								res.put(colname, Utils.formatToDate(rs.getDate(i) + " " + rs.getTime(i)));
						} else if (coltype == java.sql.Types.BLOB) {
							Blob dbBlob;
							dbBlob = rs.getBlob(i);
							if (dbBlob == null)
								continue;
							int length = (int) dbBlob.length();
							byte[] buffer = dbBlob.getBytes(1, length);
							res.put(colname, buffer);
						} else if (coltype == java.sql.Types.NULL) {
							// res.put(colname,null);
						} else {
							if (rs.getString(i) != null) {
								res.put(colname, rs.getString(i));
							}
						}
					}
					list.add(res);
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
		}
		return list;
	}

	/**
	 * 获取文件扩展类型
	 * 
	 * @param file
	 * @return
	 */
	public String getMimeType(File file) {
		String mimetype = "";
		if (file.exists()) {
			if (getSuffix(file.getName()).equalsIgnoreCase("png")) {
				mimetype = "image/png";
			} else if (getSuffix(file.getName()).equalsIgnoreCase("jpg")) {
				mimetype = "image/jpg";
			} else if (getSuffix(file.getName()).equalsIgnoreCase("jpeg")) {
				mimetype = "image/jpeg";
			} else if (getSuffix(file.getName()).equalsIgnoreCase("gif")) {
				mimetype = "image/gif";
			} else {
				javax.activation.MimetypesFileTypeMap mtMap = new javax.activation.MimetypesFileTypeMap();
				mimetype = mtMap.getContentType(file);
			}
		}
		return mimetype;
	}

	/**
	 * 
	 * @param filename
	 * @return
	 */
	private String getSuffix(String filename) {
		String suffix = "";
		int pos = filename.lastIndexOf('.');
		if (pos > 0 && pos < filename.length() - 1) {
			suffix = filename.substring(pos + 1);
		}
		return suffix;
	}

	/**
     * 
     */
	public static ArrayList getUploadFiles(String ywid, String fjlb) {
		ArrayList arr = new ArrayList();
		String file_sql = "select  FILEID,FILENAME,BZ from fs_fileupload where ywid = '" + ywid + "' and fjzt='1' ";
		if (!Pub.empty(fjlb)) {
			if (fjlb.indexOf(",") != -1) {
				file_sql += " and fjlb in ('" + fjlb.replaceAll("\\,", "\\','") + "')";
			} else {
				file_sql += " and fjlb='" + fjlb + "'";
			}
		}
		String[][] a = DBUtil.query(file_sql);
		// String fileid = "";
		String[] fileid = null;
		String[] filenames = null;
		if (a != null && a.length > 0) {
			filenames = new String[a.length];
			fileid = new String[a.length];

			for (int i = 0; i < a.length; i++) {
				if (!Pub.empty(a[i][2])) {
					filenames[i] = a[i][2];
				} else {
					fileid[i] = a[i][0];
					filenames[i] = a[i][1];
				}
			}

		}
		arr.add(0, fileid);
		arr.add(1, filenames);

		return arr;
	}

	public boolean doTransfer(Connection conn, FileUploadVO condVO) {
		boolean b = false;

		// String fileID = request.getParameter("fileid");
		// FileUploadVO condVO = new FileUploadVO();
		if (conn == null) {
			conn = DBUtil.getConnection();
		}
		try {
			String sql = "select fileid from FS_FILEUPLOAD where ywid='" + condVO.getYwid() + "' and fjlb='"
					+ condVO.getFjlb() + "'";
			String arr[][] = DBUtil.query(conn, sql);
			condVO.setFileid(arr[0][0]);
			FileUploadVO vo = (FileUploadVO) BaseDAO.getVOByPrimaryKey(conn, condVO);
			File oldFile = new File(fileRoot + "/" + vo.getUrl());
			if (oldFile.exists()) {
				// String base = AppInit.appPath+"/file";
				// String root = request.getContextPath()+"/file";
				// String oldFileDir = "";
				String newFileDir = "";
				// String result = "";
				// 文件保存路径：根路径/项目ID/附件ID
				// oldFileDir = oldFileRoot + "/" + vo.getYwid() + "/" +
				// vo.getFileid();
				newFileDir = condVO.getUrl();
				// 文件夹不存在的话，需要创建文件夹，否则直接使用
				File copyFile = new File(newFileDir);
				if (!copyFile.exists() && !copyFile.isDirectory()) {
					copyFile.mkdirs();
					copyFile = new File(newFileDir);
					copyFile.mkdir();

				} else {
					copyFile.delete();
				}
				copyFile = new File(newFileDir, condVO.getFilename());
				copyFile.createNewFile();
				// -----------------------------------
				int bytes = 0;
				FileOutputStream op = new FileOutputStream(copyFile);
				byte[] bbuf = new byte[1024];
				FileInputStream in = new FileInputStream(oldFile);
				while ((in != null) && ((bytes = in.read(bbuf)) != -1)) {
					op.write(bbuf, 0, bytes);
				}
				in.close();
				op.flush();
				op.close();
				// -----------------------------------
				b = true;
				// result = root + "/"+ vo.getYwid() + "/" +
				// fileID+"/"+vo.getFilename();
				// j.setMsg(result);
			}
		} catch (Exception e) {
			DBUtil.rollbackConnetion(conn);
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return b;
	}

	public String doFileHasChange(String uid, String oldUid, String type) {
		Connection conn = DBUtil.getConnection();
		String[][] gp1 = null;
		String[][] gp2 = null;
		String[][] res = null;
		String[][] olds = null;
		String hasNoSure = "";
		String domReason = "";
		try {

			String sqlAll = "SELECT file_name, doc_size, created_date,   file_type, af.target_uid FROM at_fileupload af WHERE (af.target_uid=? OR target_uid=?) "
					+ "AND  af.business_sub_type=? AND af.file_type IN(SELECT code FROM  V_FILE_FILETYPE WHERE ftype=?) "
					+ "ORDER BY af.target_uid, af.file_type";
			String[][] rsArray = DBUtil.querySql(conn, sqlAll, new Object[] { uid, oldUid, type, type });
			if (rsArray != null) {
				Map<String, List<String[]>> newMap = new HashMap<String, List<String[]>>();
				Map<String, List<String[]>> oldMap = new HashMap<String, List<String[]>>();
				for (String[] strings : rsArray) {
					if (strings[4].equals(uid)) {
						if (newMap.containsKey(strings[3])) {
							newMap.get(strings[3]).add(strings);
						} else {
							List list = new ArrayList<String[]>();
							list.add(strings);
							newMap.put(strings[3], list);
						}
					} else {
						if (oldMap.containsKey(strings[3])) {
							oldMap.get(strings[3]).add(strings);
						} else {
							List list = new ArrayList<String[]>();
							list.add(strings);
							oldMap.put(strings[3], list);
						}
					}
				}

				// 判断返回变化
				for (Entry<String, List<String[]>> o : newMap.entrySet()) {
					String key = o.getKey();
					if (oldMap.containsKey(key)) {
						// 新老都有，需要进一步判断
						List<String[]> new_list = o.getValue();
						List<String[]> old_list = oldMap.get(key);
						boolean flag = false;
						for (String[] newstrings : new_list) {
							for (String[] oldStrings : old_list) {
								if (newstrings[0].equals(oldStrings[0]) && newstrings[1].equals(oldStrings[1])
										&& newstrings[2].equals(oldStrings[2])) {
									old_list.remove(oldStrings);
									flag = false;
									break;
								} else {
									// 在老的里面没有相同的，这个类别有变化
									flag = true;
								}
							}
							if (flag) {
								domReason += newstrings[3] + ":";
								break;
							}
						}
					} else {
						// 新的有，在老的里面没有 肯定不同
						List<String[]> list = o.getValue();
						String[] val = list.get(0);
						domReason += val[3] + ":";
					}
				}
			}

			// 分两块处理
			// 第一块 先处理 不同file_type的文件个数是否相同 ,如果个数都不同 则一定发生了改变
			// String sql =
			// "select (select count(*) from at_fileupload t where t.business_sub_type='"
			// + type
			// + "' and t.file_type=g.code and t.target_uid = " + uid
			// + " ),g.code from V_FILE_FILETYPE g where g.ftype='" + type +
			// "'";
			//
			// gp1 = DBUtil.query(conn, sql);
			// if (gp1 != null) {
			// for (int i = 0; i < gp1.length; i++) {
			// String sql3 =
			// "select count(*) from at_fileupload t where t.file_type=" +
			// gp1[i][1]
			// + " and t.business_sub_type='" + type + "' and t.target_uid = " +
			// oldUid;
			// gp2 = DBUtil.query(conn, sql3);
			// if (gp2 != null && gp1[i][0].equals(gp2[0][0])) {
			// // 个数相同,记录下相同的 file_type 稍后比较详细数据
			// if (!"0".equals(gp1[i][0])) {
			// hasNoSure += gp1[i][1] + ",";
			// }
			// } else {
			// // 个数不同,一定发生了改变
			// domReason += gp1[i][1] + ":";
			// }
			// }
			// }
			//
			// String sql0 =
			// " select t.file_name,t.doc_size,t.created_date,t.created_uid,t.file_type from at_fileupload t where t.file_type in ("
			// + hasNoSure.substring(0, hasNoSure.length() - 1)
			// + ") and t.business_sub_type='"
			// + type
			// + "' and t.target_uid = " + uid;
			// res = DBUtil.query(conn, sql0);
			// if (res != null) {
			// for (int i = 0; i < res.length; i++) {
			// String sql1 = " select count(*) from at_fileupload t " +
			// " where t.target_uid = " + oldUid
			// + " and t.file_name = '" + res[i][0] + "' and t.doc_size = '" +
			// res[i][1] + "'"
			// + " and t.created_date = to_date('" + res[i][2] +
			// "','yyyy-mm-dd hh24:mi:ss') "
			// + " and t.file_type = '" + res[i][4] + "'";
			// olds = DBUtil.query(conn, sql1);
			// if (olds == null || !"1".equals(olds[0][0])) {
			// domReason += res[i][4] + ":";
			// }
			// }
			// }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return domReason;

	}
}
