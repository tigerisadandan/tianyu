package com.ccthanking.framework.fileUpload.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.sgenter.vo.AtFileuploadVO;
import com.ccthanking.common.FjlbManager;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.fileUpload.service.FileUploadService;
import com.ccthanking.framework.fileUpload.vo.FileUploadVO;
import com.ccthanking.framework.model.requestJson;
import com.ccthanking.framework.params.ParaManager;
import com.ccthanking.framework.params.SysPara.SysParaConfigureVO;
import com.ccthanking.framework.plugin.AppInit;
import com.ccthanking.framework.util.Pub;
import com.ccthanking.framework.util.RandomGUID;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;

@Controller
@RequestMapping("/fileUploadController")
public class FileUploadController {

	// private final BlobstoreService blobstoreService =
	// BlobstoreServiceFactory.getBlobstoreService();
	// private final BlobInfoFactory blobInfoFactory = new BlobInfoFactory();
	/**
	 * 查询文件列表
	 * 
	 * @param request
	 * @param js
	 */
	@RequestMapping(params = "queryFileList")
	@ResponseBody
	public requestJson queryFileList(HttpServletRequest request, requestJson js) throws Exception {
		requestJson j = new requestJson();
		FileUploadService ser = new FileUploadService();
		String resultVO = "";
		resultVO = ser.queryFile(request, js);
		// System.out.println("zhangbr:"+resultVO);
		j.setMsg(resultVO);
		return j;
	}

	/**
	 * 删除文件
	 * 
	 * @param request
	 * @param js
	 */
	@RequestMapping(params = "deleteFile")
	@ResponseBody
	public requestJson deleteFile(HttpServletRequest request, requestJson js) throws Exception {
		requestJson j = new requestJson();
		FileUploadVO vo = new FileUploadVO();
		JSONArray list = vo.doInitJson(js.getMsg());
		vo.setValueFromJson((JSONObject) list.get(0));
		// FileUploadService ser = new FileUploadService();
		String resultVO = "";
		User user = RestContext.getCurrentUser();
		vo.setLrr(user.getUserSN());// 只能删除当前登录人上传的
		FileUploadService.deleteByConditionVO(null, vo);
		j.setMsg(resultVO);
		return j;
	}

	/**
	 * 预览功能
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/doPreview")
	@ResponseBody
	public requestJson doPreview(HttpServletRequest request, HttpServletResponse response) throws Exception {
		requestJson j = new requestJson();
		String fileID = request.getParameter("fileid");
		AtFileuploadVO condVO = new AtFileuploadVO();
		Connection conn = DBUtil.getConnection();
		try {
			condVO.setAt_fileupload_uid(fileID);
			AtFileuploadVO vo = (AtFileuploadVO) BaseDAO.getVOByPrimaryKey(conn, condVO);
			SysParaConfigureVO syspara = (SysParaConfigureVO) ParaManager.getInstance().getSysParameter(
					"FILEUPLOADROOT");
			String fileRoot = syspara.PARAVALUE1;

			String base = AppInit.appPath + "/file";
			 if (Constants.getBoolean(Constants.APP_DEV_FLAG, true)) {
				// 开发模式下 存放在应用目录下
			} else {
				// 文件下载暂存放目录 有httpserver的才有价值
				SysParaConfigureVO syspara_download = (SysParaConfigureVO) ParaManager.getInstance().getSysParameter(
						"FILEDOWNLOADROOT");
				if (syspara_download != null) {
					String file_down_root = syspara_download.PARAVALUE1;
					base = file_down_root + "/" + Constants.APP_NAME + "/file";
				}
			}

			File file = new File(fileRoot + "/" + vo.getFile_save_name());
			if (file.exists()) {
				/**
				 * int bytes = 0; response.setCharacterEncoding("GBK");
				 * response.setContentType(vo.getFjlx());
				 * response.setContentLength((int) file.length());
				 * response.setHeader("Content-Disposition",
				 * "inline; filename=\"" +
				 * MimeUtility.encodeWord(file.getName()) + "\"");
				 * response.setHeader("Content-type", vo.getFjlx());
				 * response.setHeader("Accept-Ranges", "bytes");
				 * response.setHeader("content", "text/html");
				 * ServletOutputStream op = response.getOutputStream(); byte[]
				 * bbuf = new byte[1024]; FileInputStream in = new
				 * FileInputStream(file); while ((in != null) && ((bytes =
				 * in.read(bbuf)) != -1)) { op.write(bbuf, 0, bytes); }
				 * in.close(); op.flush(); op.close();
				 */

				String root = request.getContextPath() + "/file";
				String fileDir = "";
				String result = "";
				// 文件保存路径：根路径/项目ID/附件ID
				fileDir = base + "/" + vo.getTarget_uid() + "/" + fileID;
				// 文件夹不存在的话，需要创建文件夹，否则直接使用
				File copyFile = new File(fileDir + File.separator + vo.getFile_save_name());
				if (!copyFile.exists()) {
					FileUtils.copyFile(file, copyFile);
				}
				file = null;
				copyFile = null;

				if (false && !copyFile.exists() && !copyFile.isDirectory()) {
					copyFile.mkdirs();
					// copyFile = new File(fileDir);
					// copyFile.mkdir();
					copyFile = new File(fileDir, vo.getFile_save_name());
					copyFile.createNewFile();
					// -----------------------------------
					int bytes = 0;
					FileOutputStream op = new FileOutputStream(copyFile);
					byte[] bbuf = new byte[1024];
					FileInputStream in = new FileInputStream(file);
					while ((in != null) && ((bytes = in.read(bbuf)) != -1)) {
						op.write(bbuf, 0, bytes);
					}
					in.close();
					op.flush();
					op.close();
					// -----------------------------------
				} else {
					// do nothing
				}
				result = root + "/" + vo.getTarget_uid() + "/" + fileID + "/" + vo.getFile_save_name();
				j.setMsg(result);
			}
		} catch (Exception e) {
			DBUtil.rollbackConnetion(conn);
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return j;
	}

	/**
	 * 获取用户图片
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "getFile")
	@ResponseBody
	public void getFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String fileID = request.getParameter("fileid");
		FileUploadVO condVO = new FileUploadVO();
		Connection conn = DBUtil.getConnection();
		String account = request.getParameter("account");
		String ywlx = request.getParameter("ywlx");
		try {
			SysParaConfigureVO syspara = (SysParaConfigureVO) ParaManager.getInstance().getSysParameter(
					"FILEUPLOADROOT");
			if (YwlxManager.SYSTEM_USER_TX.equals(ywlx)) {
				String getFileIDSql = "select fsid from FS_ORG_PERSON_FS where account='" + account + "' and fslb='"
						+ FjlbManager.FS_USER_YHTX + "' and sfyx='1'";
				String arr[][] = DBUtil.query(conn, getFileIDSql);
				syspara = (SysParaConfigureVO) ParaManager.getInstance().getSysParameter("SYSTEM_USER_TX");
				if (arr == null || Pub.empty(arr[0][0])) {
					// 没查到用户的头像，那么使用表中保存的默认头像
					// fileRoot = AppInit.appPath;
					condVO.setFileid("000000000000000000000000000000000001");
				} else {
					condVO.setFileid(arr[0][0]);
				}
			} else if (YwlxManager.SYSTEM_USER_QM.equals(ywlx)) {
				String getFileIDSql = "select fsid from FS_ORG_PERSON_FS where account='" + account + "' and fslb='"
						+ FjlbManager.FS_USER_YHQM + "' and sfyx='1'";
				String arr[][] = DBUtil.query(conn, getFileIDSql);
				syspara = (SysParaConfigureVO) ParaManager.getInstance().getSysParameter("SYSTEM_USER_QM");
				if (arr == null || Pub.empty(arr[0][0])) {
					// 没查到用户的头像，那么使用表中保存的默认头像
					// fileRoot = AppInit.appPath;
					condVO.setFileid("000000000000000000000000000000000001");
				} else {
					condVO.setFileid(arr[0][0]);
				}
			} else {
				condVO.setFileid(fileID);
			}
			String fileRoot = syspara.PARAVALUE1;
			FileUploadVO vo = (FileUploadVO) BaseDAO.getVOByPrimaryKey(conn, condVO);
			File file = null;
			if (YwlxManager.SYSTEM_USER_TX.equals(ywlx)) {
				// 处理用户头像
				file = new File(fileRoot + "/" + condVO.getFileid());
				vo = new FileUploadVO();
				vo.setFjlx("image/jpg");
				if (file.exists()) {
				} else {
					file = new File(AppInit.appPath + "/images/Snip20130907_2.png");
				}

			} else if (YwlxManager.SYSTEM_USER_QM.equals(ywlx)) {
				// 处理用户签名
				file = new File(fileRoot + "/" + condVO.getFileid());
				vo = new FileUploadVO();
				vo.setFjlx("image/jpg");
				if (file.exists()) {
				} else {
					file = new File(AppInit.appPath + "/images/signature.png");
				}

			} else {
				file = new File(fileRoot + "/" + vo.getUrl());
			}

			if (file.exists()) {
				int bytes = 0;
				response.setCharacterEncoding("GBK");
				response.setContentType(vo.getFjlx());
				response.setContentLength((int) file.length());
				response.setHeader("Content-Disposition",
						"inline; filename=\"" + MimeUtility.encodeWord(file.getName()) + "\"");
				response.setHeader("Content-type", vo.getFjlx());
				response.setHeader("Accept-Ranges", "bytes");
				response.setHeader("content", "text/html");
				ServletOutputStream op = response.getOutputStream();
				byte[] bbuf = new byte[1024];
				FileInputStream in = new FileInputStream(file);
				while ((in != null) && ((bytes = in.read(bbuf)) != -1)) {
					op.write(bbuf, 0, bytes);
				}
				in.close();
				op.flush();
				op.close();
			}
		} catch (Exception e) {
			DBUtil.rollbackConnetion(conn);
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
	}

	// /**
	// * 生成项目二维码
	// * @param request
	// * @param response
	// * @throws Exception
	// */
	// @RequestMapping(params = "getFileEwm")
	// @ResponseBody
	// public void getFileEwm(HttpServletRequest request, HttpServletResponse
	// response) throws Exception{
	// String GC_TCJH_XMXDK_ID = request.getParameter("id");
	//
	// Connection conn =DBUtil.getConnection();
	//
	// try {
	// if(!Pub.empty(GC_TCJH_XMXDK_ID)){
	// File file = null;
	// file = new File(AppInit.appPath +
	// "/images/TwoDimensionCode/"+GC_TCJH_XMXDK_ID+".png");
	//
	// if(file.exists()){//存在则直接输出二维码
	// int bytes = 0;
	// response.setCharacterEncoding("GBK");
	// response.setContentType("image/png");
	// response.setContentLength((int) file.length());
	// response.setHeader("Content-Disposition", "inline; filename=\""
	// + MimeUtility.encodeWord(file.getName()) + "\"");
	// response.setHeader("Content-type", "image/png");
	// response.setHeader("Accept-Ranges", "bytes");
	// response.setHeader("content", "text/html");
	// ServletOutputStream op = response.getOutputStream();
	// byte[] bbuf = new byte[1024];
	// FileInputStream in = new FileInputStream(file);
	// while ((in != null) && ((bytes = in.read(bbuf)) != -1)) {
	// op.write(bbuf, 0, bytes);
	// }
	// in.close();
	// op.flush();
	// op.close();
	// }else{
	// String arr[][] = null;
	// String encoderContent = "项目信息";
	// //生成新的file并输出
	// String imgPath = AppInit.appPath +
	// "/images/TwoDimensionCode/"+GC_TCJH_XMXDK_ID+".png";
	// //获取内容
	// String getFileIDSql =
	// "select xmbh,xmmc from GC_TCJH_XMXDK t where gc_tcjh_xmxdk_id ='"+GC_TCJH_XMXDK_ID+"'";
	// arr = DBUtil.query(conn, getFileIDSql);
	// if(arr != null){
	// encoderContent = "项目编号:" +arr[0][0]+
	// " 项目名称:"+arr[0][1]+"\n 访问地址 http://www.sina.com.cn";
	// }
	//
	//
	// TwoDimensionCode handler = new TwoDimensionCode();
	// handler.encoderQRCode(encoderContent, imgPath, "png");
	// System.out.println("========encoder success");
	//
	// file = new File(AppInit.appPath +
	// "/images/TwoDimensionCode/"+GC_TCJH_XMXDK_ID+".png");
	// if(file.exists()){//存在则直接输出二维码
	// int bytes = 0;
	// response.setCharacterEncoding("GBK");
	// response.setContentType("image/png");
	// response.setContentLength((int) file.length());
	// response.setHeader("Content-Disposition", "inline; filename=\""
	// + MimeUtility.encodeWord(file.getName()) + "\"");
	// response.setHeader("Content-type", "image/png");
	// response.setHeader("Accept-Ranges", "bytes");
	// response.setHeader("content", "text/html");
	// ServletOutputStream op = response.getOutputStream();
	// byte[] bbuf = new byte[1024];
	// FileInputStream in = new FileInputStream(file);
	// while ((in != null) && ((bytes = in.read(bbuf)) != -1)) {
	// op.write(bbuf, 0, bytes);
	// }
	// in.close();
	// op.flush();
	// op.close();
	// }
	//
	// }
	// }
	//
	// } catch (Exception e){
	// DBUtil.rollbackConnetion(conn);
	// e.printStackTrace();
	// } finally {
	// DBUtil.closeConnetion(conn);
	// }
	// }
	/**
	 * 附件获取业务ID
	 * 
	 * @param request
	 * @param js
	 */
	@RequestMapping(params = "getYwid")
	@ResponseBody
	public requestJson getYwid(HttpServletRequest request, requestJson js) throws Exception {
		requestJson j = new requestJson();
		String resultVO = new RandomGUID().toString();
		j.setMsg(resultVO);
		return j;
	}

	/**
	 * 获取重复的附件
	 * 
	 * @param request
	 * @param js
	 */
	@RequestMapping(params = "doFileHasChange")
	@ResponseBody
	public requestJson doFileHasChange(HttpServletRequest request, requestJson js) throws Exception {
		requestJson j = new requestJson();
		String uid = request.getParameter("uid");
		String oldUid = request.getParameter("oldUid");
		String type = request.getParameter("type");
		FileUploadService ser = new FileUploadService();
		String resultVO = ser.doFileHasChange(uid, oldUid, type);
		j.setMsg(resultVO);
		return j;
	}

}
