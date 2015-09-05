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

import com.ccthanking.business.common.vo.FsFileVO;
import com.ccthanking.business.common.vo.TbAttachmentVO;
import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;
import com.ccthanking.framework.params.ParaManager;
import com.ccthanking.framework.params.SysPara.SysParaConfigureVO;
import com.ccthanking.framework.util.PropertyUtil;
import com.ccthanking.framework.util.Pub;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;

public class FileUploadUtilService {

	private static Logger logger = LoggerFactory.getLogger(FileUploadUtilService.class);

	public FileUploadUtilService() {
		SysParaConfigureVO syspara = (SysParaConfigureVO) ParaManager.getInstance().getSysParameter("FILEROOT");
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
		String target_uid = request.getParameter("target_uid");
		String target_table = request.getParameter("target_table");
		String target_col = request.getParameter("target_col");
		String file_type = request.getParameter("file_type");
		String file_type_name = PropertyUtil.getProperty(file_type);
		//boolean hastargetUuid = false;

		User user = RestContext.getCurrentUser();
		try {
			String fileID = "";// 附件ID
			String fileDir = "";// 文件路径
			String fileName = "";// 文件名称
			List<FileItem> items = uploadHandler.parseRequest(request);
			if (Pub.empty(target_uid)) {
				target_uid = String.valueOf(System.nanoTime());
				
			} 
			for (FileItem item : items) {
				if (!item.isFormField()) {
					// 生成附件ID，40位
					fileID = DBUtil.getSequenceValue("FS_FILE_UID");
					// 文件保存路径：根路径/项目ID/附件ID
					fileDir = fileRoot + "/"+target_uid+"/"+fileID;
					System.out.println(fileDir);
					// 保存到系统的文件名，默认使用上传文件的名字
					fileName = item.getName();
					if (file_type == "0038" || "0038".equals(file_type)) {
						SysParaConfigureVO syspara = (SysParaConfigureVO) ParaManager.getInstance().getSysParameter(
								"SYSTEM_USER_TX");
						fileRoot = syspara.PARAVALUE1;
						fileDir = fileRoot;
						fileName = fileID + "." + this.getSuffix(fileName);
					} else if (file_type == "0039" || "0039".equals(file_type)) {
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
					file = new File(fileDir, fileName);
					item.write(file);
					String size = this.resizeImage(file);// 调整图片类文件的大小
					
					JSONObject jsono = new JSONObject();
					//ATTACHMENT_UID, FILE_NAME, FILE_EXT_NAME, FILE_PATH, MIME_TYPE, FILE_SIZE, CREATE_DATE, CREATE_USER
					//FS_FILE_UID, TARGET_TABLE, TARGET_COL, TARGET_UID, FILE_TYPE, FILE_TYPE_NAME, ATTACHMENT_UID, CREATE_DATE, CREATE_USER, ENABLED
					jsono.put("FS_FILE_UID", fileID);
					jsono.put("FILE_NAME", fileName);
					jsono.put("FILE_EXT_NAME", fileName.substring(fileName.lastIndexOf(".") + 1));
					jsono.put("FILE_PATH", "/"+target_uid+"/"+fileID+"/"+fileName);
					jsono.put("MIME_TYPE", this.getMimeType(file));
					jsono.put("FILE_SIZE", size);
					
					jsono.put("CREATE_USER", user.getName());
					
					jsono.put("TARGET_TABLE", target_table);
					jsono.put("TARGET_UID", target_uid);
					jsono.put("TARGET_COL", target_col);
					jsono.put("FILE_TYPE", file_type);
					jsono.put("FILE_TYPE_NAME", file_type_name);
					
					jsono.put(
							"url",
							root + "/UploadUtilServlet?getfile="+ "/"+target_uid+"/"+fileID+"/"+fileName+ "&fileDir="
									+ fileRoot);
					jsono.put(
							"url_look",
							root + "/UploadUtilServlet?getfile=" + "/"+target_uid+"/"+fileID+"/"+fileName+ "&fileDir="
									+ fileRoot + "&getLook=1");
					jsono.put(
							"thumbnail_url",
							root + "/UploadUtilServlet?getthumb=" + "/"+target_uid+"/"+fileID+"/"+fileName + "&fileDir="
									+ fileRoot + "/");
					jsono.put(
							"delete_url",
							root + "/UploadUtilServlet?delfile=" + fileName + "&fileDir="
									+ fileRoot + "/&FS_FILE_UID=" + fileID );
					jsono.put("modifyflag_url", root + "/UploadUtilServlet?modifyflag=" + fileName + "&fileDir="
							+ fileRoot+ "/&FS_FILE_UID=" + fileID);
					jsono.put("delete_type", "GET");
					

					json.put(jsono);
					 System.out.println(json.toString());
				}
			}
			
			insertTable(json, request);
		
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
			size = df.format(d / 1000) ;//+ "KB";
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
	private Map<String,Object> doInitVO(JSONArray json, HttpServletRequest request) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		
		try {
			for (int i = 0; i < json.length(); i++) {
				TbAttachmentVO vo = new TbAttachmentVO();
				FsFileVO fvo = new FsFileVO();
				JSONObject jsono = (JSONObject) json.get(i);
				// FS_FILE_UID, , , , , , ATTACHMENT_UID, , , ENABLED
				//ATTACHMENT_UID, , , , , , , 
				
				vo.setFile_name(jsono.getString("FILE_NAME"));
				vo.setFile_ext_name(jsono.getString("FILE_EXT_NAME"));
				vo.setFile_path(jsono.getString("FILE_PATH"));
				vo.setFile_size(jsono.getString("FILE_SIZE"));
				vo.setMime_type(jsono.getString("MIME_TYPE"));
				vo.setCreate_date(new Date());
				vo.setCreate_user(jsono.getString("CREATE_USER"));
				
				fvo.setFs_file_uid(jsono.getString("FS_FILE_UID"));
				fvo.setTarget_table(jsono.getString("TARGET_TABLE"));
				fvo.setTarget_col(jsono.getString("TARGET_COL"));
				fvo.setTarget_uid(jsono.getString("TARGET_UID"));
				fvo.setFile_type(jsono.getString("FILE_TYPE"));
				fvo.setFile_type_name(jsono.getString("FILE_TYPE_NAME"));
				fvo.setCreate_date(new Date());
				fvo.setCreate_user(jsono.getString("CREATE_USER"));
				fvo.setEnabled("0");

				map.put("vo", vo);
				map.put("fvo", fvo);
			}
		} catch (Exception e) {
			throw e;
		}
		return map;
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
			Map<String,Object> map = this.doInitVO(json, request);
			TbAttachmentVO vo = (TbAttachmentVO) map.get("vo");
			FsFileVO fvo = (FsFileVO) map.get("fvo");
			BaseDAO.insert(conn, vo);
			resultVO = vo.getRowJson();
			fvo.setAttachment_uid(vo.getAttachment_uid());
			BaseDAO.insert(conn, fvo);
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
	 * 根据业务tragetUid更新附件状态和tragetUid 把其原String换成number
	 * 
	 * @param conn
	 * @param ywid
	 * @return
	 * @throws Exception
	 */
	public static boolean updateBytragetUid(String tragetUid, String newtragetUid) throws Exception {
		boolean flag = true;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			if (!Pub.empty(tragetUid)) {
				String sql = "update fs_file set enabled=1,target_uid='" + newtragetUid + "' " + " where target_uid ='" + tragetUid
						+ "' and enabled=0 ";
				//if (StringUtils.isNotBlank(fileType)) {
				///	sql += " and file_type='" + fileType + "'";

				//}

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
	public static boolean updateFjztByTargetUid(Connection conn, String newtragetUid, String oldtragetUid) throws Exception {
		boolean flag = true;
		try {
			if (!Pub.empty(newtragetUid)) {
				String sql = "update fs_file set enabled=1," + "target_uid='" + newtragetUid + "'" +
						" where target_uid='" + oldtragetUid + "'";
				DBUtil.execUpdateSql(conn, sql);
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
		}
		return flag;
	}


	
	/**
	 * 附件删除方法
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete(FsFileVO vo) throws Exception {
		Connection conn = null;
		String resultVO = null;
		try {
			conn = DBUtil.getConnection();
			vo = (FsFileVO) BaseDAO.getVOByPrimaryKey(conn, vo);
			TbAttachmentVO tvo =  new TbAttachmentVO();
			tvo.setAttachment_uid(vo.getAttachment_uid());
			BaseDAO.delete(conn, tvo);
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
	public String update(FsFileVO vo) throws Exception {
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
		ResultSet rs = null;
		ResultSetMetaData md = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			FsFileVO vo = new FsFileVO();
			net.sf.json.JSONArray tlist = vo.doInitJson(js.getMsg());
			vo.setValueFromJson((net.sf.json.JSONObject)tlist.get(0));
			StringBuffer sql = new StringBuffer();
			sql.append(" select fs_file_uid, target_table, target_col,  ");
			sql.append("        target_uid, file_type, file_type_name,  ");
			sql.append("        t.attachment_uid, enabled, file_name,  ");
			sql.append("        file_ext_name, file_path, mime_type, ");
			sql.append("        file_size, t.create_date, t.create_user ");
			sql.append(" from fs_file  f ");
			sql.append(" left join tb_attachment t ");
			sql.append(" on f.attachment_uid = t.attachment_uid ");
			sql.append(" where f.enabled = 1 ");
			sql.append(" and f.target_table = '"+vo.getTarget_table()+"'");
			sql.append(" and f.target_uid = '"+vo.getTarget_uid()+"'");
			//sql.append(" and f.file_type = '"+vo.getFile_type()+"'");
			//System.out.println("sql==="+sql);
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			md = rs.getMetaData();
			while(rs.next()){
				JSONObject jsono = new JSONObject();
				for (int i = 0; i < md.getColumnCount(); i++) {
					String columnName = md.getColumnName(i+1);
					String sTemp  = rs.getString(columnName);	
					
					if(sTemp==null ||sTemp == ""){
						sTemp = " ";
					}
					jsono.put(columnName, sTemp);
				}
					
				jsono.put("url", root + "/UploadUtilServlet?getfile=" +rs.getString("File_PATH") 
						+ "&fileDir="+ fileRoot );
				jsono.put("thumbnail_url", root + "/UploadUtilServlet?getthumb="+rs.getString("File_PATH")
						+ "&fileDir="+ fileRoot );
				jsono.put(
						"delete_url",
						root + "/UploadUtilServlet?delfile="+rs.getString("File_PATH")+ "&fileDir="+ fileRoot
						+"&FS_FILE_UID="+rs.getString("FS_FILE_UID"));
				jsono.put("delete_type", "GET");
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
	private List<FsFileVO> getFileUploadVOByJson(Connection conn, requestJson js) throws Exception {
		ResultSet rs = null;
		ResultSetMetaData md = null;
		PreparedStatement ps = null;
		List<FsFileVO> list = new ArrayList<FsFileVO>();
		try {
			String sql = "select * from fs_file where ENABLED=1 ";
			FsFileVO condVO = new FsFileVO();
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
				if (StringUtils.isNotBlank(condVO.getTarget_table())) {
					cond += " and business_sub_type ='" + condVO.getTarget_table() + "' ";
				}
				sql = sql + cond;
				logger.debug(sql);

				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				md = rs.getMetaData();
				while (rs.next()) {
					FsFileVO res = new FsFileVO();
					for (int i = 1; i <= md.getColumnCount(); i++) {
						String colname = md.getColumnName(i).toUpperCase();
						if (!res.isValidField(colname))
							continue;
						int coltype = md.getColumnType(i);
						if (coltype == java.sql.Types.DATE || coltype == java.sql.Types.TIMESTAMP) {
							//if (rs.getDate(i) != null)
								//res.put(colname, Utils.formatToDate(rs.getDate(i) + " " + rs.getTime(i)));
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
