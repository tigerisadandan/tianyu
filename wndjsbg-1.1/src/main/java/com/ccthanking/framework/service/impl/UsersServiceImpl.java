package com.ccthanking.framework.service.impl;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ccthanking.business.commons.CacheManagerBg;
import com.ccthanking.common.FjlbManager;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.common.db.SqlStrHelper;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.base.BaseVO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.UserFsVO;
import com.ccthanking.framework.common.UserVO;
import com.ccthanking.framework.common.UsersVO;
import com.ccthanking.framework.common.cache.CacheManager;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.coreapp.orgmanage.UserManager;
import com.ccthanking.framework.dic.Dics;
import com.ccthanking.framework.dic.TreeNode;
import com.ccthanking.framework.fileUpload.service.FileUploadService;
import com.ccthanking.framework.fileUpload.vo.FileUploadVO;
import com.ccthanking.framework.log.LogManager;
import com.ccthanking.framework.model.requestJson;
import com.ccthanking.framework.params.ParaManager;
import com.ccthanking.framework.params.SysPara.SysParaConfigureVO;
import com.ccthanking.framework.service.UserServiceCust;
import com.ccthanking.framework.util.Encipher;
import com.ccthanking.framework.util.Pub;
import com.ccthanking.framework.util.QueryConditionList;
import com.ccthanking.framework.util.RandomGUID;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;

@Service
public class UsersServiceImpl extends Base1ServiceImpl<UserVO, String> implements UserServiceCust {

	private static String MODULE = UserServiceImpl.class.getName();

	private static Logger logger = LoggerFactory.getLogger(MODULE);

	public String queryUser(String json) throws Exception {
		Connection conn = DBUtil.getConnection();
		String domresult = "";
		try {
			// 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            String sql = "select t.users_uid,t.user_name,t.logon_name," +
            		" case t.admin_y when 'Y' then '是'  else '否' end as admin_y," +
            		" case t.use_y  when 'Y' then '启用' else '禁用'  end use_y," +
            		" t.created_date, t.description,t.mima  " +
            		" from USERS t";
            BaseResultSet bs = DBUtil.query(conn, sql, page);
			domresult = bs.getJson();

		} catch (Exception e) {
			e.printStackTrace(System.out);

		} finally {
			DBUtil.closeConnetion(conn);
		}
		return domresult;
	}

	public String executeUser(String json, User user, String id, String update) throws Exception {
		Connection conn = DBUtil.getConnection();
		UsersVO usersVo = new UsersVO();
		String resultVO = null;
		String msg = id == null ? "添加" : "修改";
		int updateInt = Integer.parseInt(update);
		try {
			conn.setAutoCommit(false);
			JSONArray list = usersVo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);
			String pwd_md5 = "";
			switch (updateInt) {
			case 1:
				msg = "添加";
				//jsonObj.put("PWD", Encipher.EncodePasswd("123456"));
				usersVo.setValueFromJson(jsonObj);
				if(usersVo.getAdmin_y()==null || usersVo.getAdmin_y().equals("")){
					usersVo.setAdmin_y("N");
				}
				if(usersVo.getUse_y()==null || usersVo.getUse_y().equals("")){
					usersVo.setUse_y("N");
				}
				pwd_md5 = DigestUtils.md5Hex(usersVo.getMima().toUpperCase());
				usersVo.setPwd(pwd_md5);
				usersVo.setCreated_date(new Date());
				usersVo.setCreated_by(user.getUserSN());
				BaseDAO.insert(conn, usersVo);
				resultVO = usersVo.getRowJson();
				conn.commit();
				com.ccthanking.framework.coreapp.orgmanage.UserManager.getInstance().synchronize(
						jsonObj.getString("LOGON_NAME"), CacheManager.ADD);
				break;
				
			case 2:
				msg = "修改";
				usersVo.setValueFromJson(jsonObj);
				if(usersVo.getAdmin_y() == null || usersVo.getAdmin_y().equals("")){
					usersVo.setAdmin_y("N");
				}
				if(usersVo.getUse_y()==null || usersVo.getUse_y().equals("")){
					usersVo.setUse_y("N");
				}
				usersVo.setUsers_uid(id);
				usersVo.setUpdated_by(user.getUserSN());
				usersVo.setUpdated_date(new Date());
				BaseDAO.update(conn, usersVo);
				resultVO = usersVo.getRowJson();
				conn.commit();

				com.ccthanking.framework.coreapp.orgmanage.UserManager.getInstance().synchronize(
						id, CacheManager.UPDATE);
				break;
			/*
			case 3:
				msg = "刪除";
				// 用标识来表示信息被删除
				jsonObj.put("FLAG", "0");// modify by zhangbr@ccthanking.com
											// 用0表示无效
				userVo.setValueFromJson(jsonObj);
				BaseDAO.update(conn, userVo);
				resultVO = userVo.getRowJson();
				conn.commit();

				com.ccthanking.framework.coreapp.orgmanage.UserManager.getInstance().synchronize(
						jsonObj.getString("ACCOUNT"), CacheManager.DELETE);
				break;

			default:
				break;
				*/
			}
			/*
			LogManager.writeUserLog(user.getAccount(), "ywlx", Globals.OPERATION_TYPE_INSERT,
					LogManager.RESULT_SUCCESS, user.getOrgDept().getDeptName() + " " + user.getName() + msg + "用户信息成功",
					user, "", "");
					*/
		} catch (Exception e) {
			DBUtil.rollbackConnetion(conn);
			e.printStackTrace(System.out);
			/*
			LogManager.writeUserLog(user.getAccount(), "ywlx", Globals.OPERATION_TYPE_INSERT,
					LogManager.RESULT_FAILURE, user.getOrgDept().getDeptName() + " " + user.getName() + msg + "用户信息失败",
					user, "", "");*/
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return resultVO;
	}

	// @Override
	public String queryUnique(String account, User user) {
		String sql = "select count(1) CNT from USERS t where t.logon_name = '"+account+"'";
		String[][] cnt = DBUtil.query(sql);
		JSONObject jsonObj = new JSONObject();
		if ("0".equals(cnt[0][0])) {
			jsonObj.put("isUnique", "可以使用");
			jsonObj.put("sign", "0");
		} else {
			jsonObj.put("isUnique", "登录用户名重复，请重新填写");
			jsonObj.put("sign", "1");
		}
		return jsonObj.toString();
	}

	// @Override
	public String[][] queryRole(String account) {
		String sql = "SELECT R.ROLE_ID RID, RP.ROLE_ID RPID, R.NAME RNAME,R.ROLETYPE,R.PARENTROLEID "
				+ "FROM (SELECT ROLE_ID,NAME,SFYX,ROLETYPE,PARENTROLEID FROM FS_ORG_ROLE WHERE SFYX='1') R LEFT JOIN FS_ORG_ROLE_PSN_MAP RP "
				+ "ON R.ROLE_ID = RP.ROLE_ID AND RP.PERSON_ACCOUNT='" + account + "' AND R.SFYX = '1' ";
		String[][] roleNameArray = DBUtil.query(sql);
		return roleNameArray;
	}

	public String[][] queryPersonRole(String id) {
		String sql = "select t.account,d.person_account,t.name,(select e.dept_name from FS_ORG_DEPT e where e.row_id = t.department) as dept_name "
				// + " from fs_org_person t,fs_org_role_psn_map d "
				+ " from v_org_person t,fs_org_role_psn_map d "
				+ " where t.account = d.person_account(+) and d.role_id(+) = '"
				+ id
				+ "' order by t.department , t.NAME";//to_number(t.sort) asc";
		String[][] personArray = DBUtil.query(sql);
		return personArray;
	}

	public void awardRoleToUser(String account, String[] roleNameAndId, User user) throws Exception {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			String deleteRoleByAccountSql = "DELETE FROM FS_ORG_ROLE_PSN_MAP WHERE PERSON_ACCOUNT='" + account + "'";
			boolean bDel = DBUtil.exec(conn, deleteRoleByAccountSql);
			if (bDel) {
				for (int i = 0; i < roleNameAndId.length; i++) {
					String roleId = roleNameAndId[i].split("=")[0];
					String roleName = roleNameAndId[i].split("=")[1];
					String addRoleToUserSql = "INSERT INTO FS_ORG_ROLE_PSN_MAP(ROLE_NAME, PERSON_ACCOUNT, ROLE_ID) "
							+ "VALUES ('" + roleName + "','" + account + "','" + roleId + "')";
					DBUtil.execSql(conn, addRoleToUserSql);
				}
			}
			conn.commit();
			LogManager.writeUserLog(user.getAccount(), YwlxManager.SYSTEM_USER, Globals.OPERATION_TYPE_INSERT,
					LogManager.RESULT_SUCCESS, user.getOrgDept().getDeptName() + " " + user.getName() + "为用户分配角色成功",
					user, "", "");
		} catch (Exception e) {
			DBUtil.rollbackConnetion(conn);
			e.printStackTrace();
			LogManager.writeUserLog(user.getAccount(), YwlxManager.SYSTEM_USER, Globals.OPERATION_TYPE_INSERT,
					LogManager.RESULT_SUCCESS, user.getOrgDept().getDeptName() + " " + user.getName() + "为用户分配角色失败",
					user, "", "");
		} finally {
			DBUtil.closeConnetion(conn);
		}
	}

	// @Override
	public void awardRoleToPerson(String roleid, String rolename, String[] personNameAndId, User user) throws Exception {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			String deleteRoleByAccountSql = "DELETE FROM FS_ORG_ROLE_PSN_MAP WHERE role_id='" + roleid + "'";
			boolean bDel = DBUtil.exec(conn, deleteRoleByAccountSql);
			if (bDel) {
				for (int i = 0; i < personNameAndId.length; i++) {
					String account = personNameAndId[i].split("=")[0];
					// String roleName = personNameAndId[i].split("=")[1];
					String addRoleToUserSql = "INSERT INTO FS_ORG_ROLE_PSN_MAP(ROLE_NAME, PERSON_ACCOUNT, ROLE_ID) "
							+ "VALUES ('" + rolename + "','" + account + "','" + roleid + "')";
					DBUtil.execSql(conn, addRoleToUserSql);
				}
			}
			conn.commit();
			
			LogManager.writeUserLog(user.getAccount(), YwlxManager.SYSTEM_USER, Globals.OPERATION_TYPE_INSERT,
					LogManager.RESULT_SUCCESS, user.getDepartment() + " " + user.getName() + "将角色授予用户成功",
					user, "", "");
		} catch (Exception e) {
			DaoException.handleException("***把角色授予用户失败***");
			DBUtil.rollbackConnetion(conn);
			throw e;
		} finally {
			DBUtil.closeConnetion(conn);
		}
	}

	private void insertFsVO(Connection conn, UserVO userVO) throws Exception {
		UserFsVO userFsVO = new UserFsVO();
		FileUploadVO fvo = new FileUploadVO();
		FileUploadService ser = new FileUploadService();
		// 插入头像附属信息
		userFsVO.setFs_org_person_fs_id(new RandomGUID().toString());
		userFsVO.setAccount(userVO.getAccount());
		String fsidSqlTX = "select fsid from FS_ORG_PERSON_FS where account='" + userVO.getAccount()
				+ "' and sfyx='1' and fslb='" + FjlbManager.FS_USER_YHTX + "'";
		String arrTX[][] = DBUtil.query(conn, fsidSqlTX);
		String fsidTX = "";
		if (arrTX != null) {
			fsidTX = arrTX[0][0];
			fvo.setYwid(userVO.getAccount());
		} else {
			fvo.setYwid(userVO.getYwid());
		}
		fvo.setFjlb(FjlbManager.FS_USER_YHTX);
		fvo = ser.getLastFileVO(fvo);
		if (!fsidTX.equals(fvo.getFilename())) {
			fsidTX = fvo.getFilename() == null || "".equals(fvo.getFilename()) ? fsidTX : fvo.getFilename();
			userFsVO.setFsid(fsidTX);
			userFsVO.setFslb(FjlbManager.FS_USER_YHTX);
			userFsVO.setSfyx("1");
			String sqlTx = "update FS_ORG_PERSON_FS set sfyx='0' where account='" + userVO.getAccount()
					+ "' and fslb='" + FjlbManager.FS_USER_YHTX + "'";
			DBUtil.execSql(conn, sqlTx);
			BaseDAO.insert(conn, userFsVO);
			String sqlTx2 = "delete from FS_FILEUPLOAD where ywid='" + userVO.getAccount() + "' and fjlb='"
					+ FjlbManager.FS_USER_YHTX + "'";
			DBUtil.execSql(conn, sqlTx2);
			// String sqlTx3 =
			// "update FS_FILEUPLOAD set glid1='"+userVO.getAccount()+"' where fileid='"+fvo.getFileid()+"'";
			// DBUtil.execSql(conn, sqlTx3);
		}
		// 插入签名附属信息
		fvo = new FileUploadVO();
		userFsVO.setFs_org_person_fs_id(new RandomGUID().toString());
		userFsVO.setAccount(userVO.getAccount());
		String fsidSqlQM = "select fsid from FS_ORG_PERSON_FS where account='" + userVO.getAccount()
				+ "' and sfyx='1' and fslb='" + FjlbManager.FS_USER_YHQM + "'";
		String arrQM[][] = DBUtil.query(conn, fsidSqlQM);
		String fsidQM = "";
		if (arrQM != null) {
			fsidQM = arrQM[0][0];
			fvo.setYwid(userVO.getAccount());
		} else {
			fvo.setYwid(userVO.getYwid());
		}
		fvo.setFjlb(FjlbManager.FS_USER_YHQM);
		fvo = ser.getLastFileVO(fvo);
		if (!fsidQM.equals(fvo.getFilename())) {
			fsidQM = fvo.getFilename() == null || "".equals(fvo.getFilename()) ? fsidQM : fvo.getFilename();
			userFsVO.setFsid(fsidQM);
			userFsVO.setFslb(FjlbManager.FS_USER_YHQM);
			userFsVO.setSfyx("1");
			String sqlQm = "update FS_ORG_PERSON_FS set sfyx='0' where account='" + userVO.getAccount()
					+ "' and fslb='" + FjlbManager.FS_USER_YHQM + "'";
			DBUtil.execSql(conn, sqlQm);
			BaseDAO.insert(conn, userFsVO);
			String sqlQm2 = "delete from FS_FILEUPLOAD where ywid='" + userVO.getAccount() + "' and fjlb='"
					+ FjlbManager.FS_USER_YHQM + "'";
			DBUtil.execSql(conn, sqlQm2);
			// String sqlQm3 =
			// "update FS_FILEUPLOAD set glid1='"+userVO.getAccount()+"' where fileid='"+fvo.getFileid()+"'";
			// DBUtil.execSql(conn, sqlQm3);
		}
	}

	// @Override
	public String loadAllUser(HttpServletRequest request) throws Exception {
		Connection conn = DBUtil.getConnection();
		String s = request.getParameter("str");
		String dept = request.getParameter("dept");

		String queryDictSql = "select * from (select row_id NAME,dept_name TITLE,'1' PARENT,'0' TREENODE,ROW_ID DEPTID,'' TEL,'' sort from FS_ORG_DEPT t ";
		if (!Pub.empty(dept))
			queryDictSql += " where ROW_ID='" + dept + "' ";
		queryDictSql += "union all ";
		queryDictSql += "select account NAME,name TITLE,DEPARTMENT PARENT,'1' TREENODE,DEPARTMENT DEPTID,SJHM TEL,sort from fs_org_person ";
		if (!Pub.empty(dept))
			queryDictSql += " where DEPARTMENT='" + dept + "' ";
		queryDictSql += " ) x order by to_number(x.sort) asc";
		JSONArray jsonArr = new JSONArray();
		try {
			List list = new ArrayList();
			List parentList = new ArrayList();
			if (!Pub.empty(s)) {
				String arrs[] = s.split(",");
				for (String arr : arrs) {
					String checkedParentSql = "select department from FS_ORG_PERSON where account='" + arr + "'";
					String parentArr[][] = DBUtil.query(conn, checkedParentSql);
					if (parentArr != null) {
						list.add(parentArr[0][0]);
					}
					list.add(arr);
				}
			}
			List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, queryDictSql);
			for (int i = 0; i < rsList.size(); i++) {
				Map<String, String> rsMap = rsList.get(i);
				JSONObject json = new JSONObject();
				json.put("id", rsMap.get("NAME"));
				json.put("parentId", rsMap.get("PARENT"));
				json.put("name", rsMap.get("TITLE"));
				json.put("treenode", rsMap.get("TREENODE"));
				json.put("deptid", rsMap.get("DEPTID"));
				json.put("tel", rsMap.get("TEL"));
				if (list.contains(rsMap.get("NAME"))) {
					json.put("checked", "true");
					json.put("open", "true");
				}
				jsonArr.add(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return jsonArr.toString();
	}

	// @Override
	public String loadDeptUser(HttpServletRequest request) throws Exception {
		Connection conn = DBUtil.getConnection();
		String s = request.getParameter("id");
		String dept = request.getParameter("dept");

		String queryDictSql = "select account ACCOUNT,name,sex,SJHM TEL,sort from fs_org_person ";
		if (!Pub.empty(dept))
			queryDictSql += " where DEPARTMENT='" + dept + "' and account <> 'superman' and FLAG='1'";
		queryDictSql += "  order by to_number(sort) asc";
		JSONArray jsonArr = new JSONArray();
		try {
			List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, queryDictSql);
			for (int i = 0; i < rsList.size(); i++) {
				Map<String, String> rsMap = rsList.get(i);
				JSONObject json = new JSONObject();
				json.put("account", rsMap.get("ACCOUNT"));
				json.put("name", rsMap.get("NAME"));
				if (!Pub.empty(rsMap.get("SEX"))) {
					json.put("sex", Pub.getDictValueByCode("XB", rsMap.get("SEX")));
				} else {
					json.put("sex", rsMap.get("SEX"));
				}
				json.put("tel", rsMap.get("TEL"));
				jsonArr.add(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return jsonArr.toString();
	}

	// @Override
	public String queryUserFile(HttpServletRequest request, requestJson js) throws Exception {
		org.json.JSONArray json = new org.json.JSONArray();
		String root = request.getContextPath();
		Connection conn = null;
		String domresult = "";
		try {
			conn = DBUtil.getConnection();
			SysParaConfigureVO syspara = null;
			UserFsVO condVO = new UserFsVO();
			List<UserFsVO> list = new ArrayList<UserFsVO>();
			net.sf.json.JSONArray tlist = condVO.doInitJson(js.getMsg());
			condVO.setValueFromJson((net.sf.json.JSONObject) tlist.get(0));
			condVO.setSfyx("1");
			if (FjlbManager.FS_USER_YHTX.equals(condVO.getFslb())) {
				syspara = (SysParaConfigureVO) ParaManager.getInstance().getSysParameter("SYSTEM_USER_TX");
			} else if (FjlbManager.FS_USER_YHQM.equals(condVO.getFslb())) {
				syspara = (SysParaConfigureVO) ParaManager.getInstance().getSysParameter("SYSTEM_USER_QM");
			}
			BaseVO[] bv = (BaseVO[]) BaseDAO.getVOByCondition(conn, condVO);
			if (bv != null) {
				for (int i = 0; i < bv.length; i++) {
					UserFsVO vo = (UserFsVO) bv[i];
					list.add(vo);
				}
			}
			String fileRoot = syspara.PARAVALUE1;
			for (UserFsVO vo : list) {
				String fileDir = fileRoot;
				JSONObject jsono = new JSONObject();
				jsono.put("fileid", ""); // 附件编号
				jsono.put("fileDir", fileRoot); // 附件路径
				jsono.put("fileType", Pub.empty(vo.getFsid()) ? "" : "image/jpg"); // 获取附件类型
				jsono.put("fjlb", vo.getFslb()); // 附件类别
				jsono.put("url", root + "/UploadServlet?getfile=" + vo.getFsid() + "&fileDir=" + fileDir + "/");
				jsono.put("thumbnail_url", root + "/UploadServlet?getthumb=" + vo.getFsid() + "&fileDir=" + fileDir
						+ "/");
				jsono.put("delete_url", root + "/UploadServlet?delfile=" + vo.getFsid() + "&fileDir=" + fileDir + "/"
						+ "&fileid=" + vo.getFsid());
				jsono.put("modifyflag_url", root + "/UploadServlet?modifyflag=" + vo.getFsid() + "&fileDir=" + fileDir
						+ "/&fileid=" + vo.getFsid());
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

	public User getUserByUsernameAndPassword(String username, String password) throws Exception {

		logger.debug("curr sql =[ {} ]", SqlStrHelper.getSql());

		User user = null;
		user = (User) UserManager.getInstance().getUserByLoginName(username);

		if (user == null) {
			System.out.println("用户不存在!");
			return null;

		}
		String pass1 = user.getPassword();
		if (pass1 == null)
			pass1 = "";
		if (password == null)
			password = "";

		if (Constants.ENCODER_TYPE.equals("MD5")) {// md5加密
			password = DigestUtils.md5Hex(password);
		} else {
			password = Encipher.EncodePasswd(password);
		}

		if (!pass1.equals(password)) {
			System.out.println("密码不正确!");
			return null;

		}

		return user;
	}

	// spring security 实现
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			return (User) UserManager.getInstance().getUserByLoginName(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// @Override
	public String resetPw(String account) throws Exception {
		User user = RestContext.getCurrentUser();
		Connection conn = null;
		String domresult = "";
		try {
			conn = DBUtil.getConnection();
			String pwd_md5 = DigestUtils.md5Hex("123456");
			String sqlQuery = "update users set pwd = '"+pwd_md5+"' ,mima = '123456' where logon_name  = '"+account+"'";
			 DBUtil.execSql(conn, sqlQuery);
			 //CacheManager.broadcastChanges(CacheManager.CACHE_USER, account, CacheManager.UPDATE);
			// CacheManager.broadcastChanges(CacheManager.CACHE_USER, user.getAccount(), CacheManager.UPDATE);
		} catch (Exception e) {
			DaoException.handleException("***重置密码出错***");
			DBUtil.rollbackConnetion(conn);
			throw e;
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return domresult;
	}

	// author by liujs
	// @Override
	public String personInfo(HttpServletRequest request, String json) throws Exception {
		Connection conn = DBUtil.getConnection();
		String account = request.getParameter("account");
		String domresult = "";
		try {
			PageManager page = new PageManager();
			conn.setAutoCommit(false);
			String sql = "SELECT ACCOUNT, PASSWORD, NAME, SEX, DEPARTMENT, PARENT, PERSON_KIND, "
					+ "USER_SN, LEVEL_NAME, SECRET_LEVEL, FLAG, IDCARD, CERTCODE, SMTP, MAILFROM, "
					+ "MAILNAME, MAILPSW, USERTEMPLATE, JWQ, ZRQ, SJHM,SORT FROM FS_ORG_PERSON WHERE FLAG='1' AND ACCOUNT='"
					+ account + "'";
			BaseResultSet bs = DBUtil.query(conn, sql, page);
			bs.setFieldDic("SEX", "XB");
			bs.setFieldDic("PERSON_KIND", "YHLB");
			bs.setFieldOrgDept("DEPARTMENT");
			domresult = bs.getJson();

		} catch (Exception e) {
			e.printStackTrace(System.out);

		} finally {
			DBUtil.closeConnetion(conn);
		}
		return domresult;
	}

}

