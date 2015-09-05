package com.ccthanking.framework.coreapp.orgmanage;

import java.sql.Connection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ccthanking.business.commons.CacheManagerBg;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.Role;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.UserVO;
import com.ccthanking.framework.common.cache.Cache;
import com.ccthanking.framework.common.cache.CacheManager;
import com.ccthanking.framework.util.Pub;
import com.google.common.collect.Maps;

public class UserManager implements Cache {

	private static Logger log = LoggerFactory.getLogger(UserManager.class);

	// private Hashtable usertable;
	private Map<String, User> usertable;
	private Map<String, User> usersntable;// 根据usre_id存储

	static private UserManager instance;

	private UserManager() throws Exception {
		init();
	}

	public void reBuildMemory() throws Exception {
		if (usertable != null) {
			usertable.clear();
			usertable = null;
		}
		if (usersntable != null) {
			usersntable.clear();
			usersntable = null;
		}
		init();
	}

	public void synchronize(String userName, int action) throws Exception {

		User user = this.loadUser(userName, true);
		User old = this.getUserByLoginName(userName);
		if (action == CacheManager.ADD) {
			this.usertable.put(user.getAccount(), user);
		} else if (action == CacheManager.UPDATE) {
			old.setCertCode(user.getCertCode());
			old.setDepartment(user.getDepartment());
			old.setFlag(user.getFlag());
			old.setIdCard(user.getIdCard());
			old.setIP(user.getIP());
			old.setLevelName(user.getLevelName());
			old.setParent(user.getParent());
			old.setPWD(user.getPWD());
			old.setName(user.getName());
			old.setSex(user.getSex());
			old.setPersonKind(user.getPersonKind());
			old.setScretLevel(user.getScretLevel());
			old.setUserSN(user.getUserSN());
			old.setIdCard(user.getIdCard());
			old.setCertCode(user.getCertCode());
			old.setSmtp(user.getSmtp());
			old.setMailFrom(user.getMailFrom());
			old.setMailName(user.getMailName());
			old.setMailPsw(user.getMailPsw());
			old.setUserTemplate(user.getUserTemplate());
			old.setJwqdm(user.getJwqdm());
			old.setZrqdm(user.getZrqdm());
			old.setYhbh(user.getYhbh());
		} else if (action == CacheManager.DELETE) {
			usertable.remove(user.getAccount());
		}
		if (action == CacheManager.MAP_CHANGEED || action == CacheManager.DELETE) {
			Role[] roles = old.getRoles();
			if (roles != null)
				for (int i = 0; i < roles.length; i++) {
					roles[i].setUsers(null);
				}
			old.setRoles(null);
			old.setAllowedMenus(null);
			roles = user.getRoles();
			if (roles != null) {
				for (int i = 0; i < roles.length; i++) {
					roles[i].setUsers(null);
				}
			}
		}

	}

	private void init() throws Exception {

		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String querySql = "select account,password,name,sex,department,"
					+ "parent,person_kind,user_sn,level_name,secret_level,"
					+ "flag,idcard,certcode,smtp,mailfrom,mailname,mailpsw,"
					// +
					// "usertemplate,jwq,zrq from fs_org_person where flag='1'";
					+ "usertemplate,jwq,zrq from fs_org_person where flag='1'";
			String[][] list = DBUtil.query(conn, querySql);
			if (list != null) {

				// usertable = new Hashtable(list.length);
				usertable = Maps.newConcurrentMap();
				usersntable = Maps.newConcurrentMap();

				for (int i = 0; i < list.length; i++) {
					User user = new UserVO();
					user.setAccount(list[i][0]);
					// user.setPassWord(Encipher.DecodePasswd(list[i][1]));
					user.setPWD(list[i][1]);
					user.setName(list[i][2]);
					user.setSex(list[i][3]);
					user.setDepartment(list[i][4]);
					user.setParent(list[i][5]);
					user.setPersonKind(list[i][6]);
					user.setUserSN(list[i][7]);
					user.setLevelName(list[i][8]);
					user.setScretLevel(list[i][9]);
					user.setFlag(list[i][10]);
					user.setIdCard(list[i][11]);
					user.setCertCode(list[i][12]);
					user.setSmtp(list[i][13]);
					user.setMailFrom(list[i][14]);
					user.setMailName(list[i][15]);
					user.setMailPsw(list[i][16]);
					user.setYhbh(user.getIdCard());// --新增用户编号,对应中心库用户表的用户编号字段
					if (Pub.empty(list[i][17])) {
						user.setUserTemplate("blueTemplate");
					} else {
						user.setUserTemplate(list[i][17]);
					}
					usertable.put(list[i][0], user);
				}
			}
		} catch (Exception e) {
			log.error("", e);
		} finally {
			DBUtil.closeConnetion(conn);
		}
	}

	public User getUserFromDB(String account, boolean flag) throws Exception {
		return loadUser(account, flag);
	}

	private User loadUser(String account, boolean flag) throws Exception {

		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			// 登录名不区分大小写
			String querySql = "select USERS_UID idcard,pwd,user_name,'' sex,user_type department,"
					+ " '' user_sn,admin_y PERSON_KIND," + " '1' flag, logon_name" + " from users "
					// +
					// "usertemplate,jwq,zrq from fs_org_person where account='"
					// + account + "'";
					+ "  where logon_name='" + account + "' ";
			if (flag) {
				querySql += " and USE_Y='Y'";
			}
			String[][] list = DBUtil.query(conn, querySql);
			if (list != null) {
				for (int i = 0; i < list.length; i++) {
					User user = new UserVO();
					// user.setAccount(list[i][0]);
					// user.setPassWord(Encipher.DecodePasswd(list[i][1]));
					user.setPWD(list[i][1]);
					user.setName(list[i][2]);
					user.setSex(list[i][3]);
					user.setDepartment(list[i][4]);
					user.setUserSN(list[i][0]); // 将user_sn绑定users_uid
					user.setPersonKind("Y".equals(list[i][6]) ? "2" : "3");
					user.setFlag(list[i][7]);
					user.setAccount(list[i][8]); // --20140729
													// 将account内的值设置为logon_name
					user.setYhbh(user.getIdCard());
					user.setUserTemplate("blueTemplate");

					usersntable.put(user.getUserSN(), user);// 设置
					return user;
				}
			}
		} catch (Exception e) {
			log.error("", e);
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return null;
	}

	public User getUserByCertCode(String certCode) throws Exception {
		Connection conn = null;
		try {
			if (Pub.empty(certCode))
				return null;
			conn = DBUtil.getConnection();
			String querySql = "select account,password,name,sex,department,"
					+ "parent,person_kind,user_sn,level_name,secret_level,"
					+ "flag,idcard,certcode,smtp,mailfrom,mailname,mailpsw,"
					// +
					// "usertemplate,jwq,zrq from fs_org_person where certcode='"
					// + certCode + "'";
					+ "usertemplate,jwq,zrq from fs_org_person where certcode='" + certCode + "'";
			String[][] list = DBUtil.query(conn, querySql);
			if (list != null) {
				for (int i = 0; i < list.length; i++) {
					User user = new UserVO();
					user.setAccount(list[i][0]);
					// user.setPassWord(Encipher.DecodePasswd(list[i][1]));
					user.setPWD(list[i][1]);
					user.setName(list[i][2]);
					user.setSex(list[i][3]);
					user.setDepartment(list[i][4]);
					user.setParent(list[i][5]);
					user.setPersonKind(list[i][6]);
					user.setUserSN(list[i][7]);
					user.setLevelName(list[i][8]);
					user.setScretLevel(list[i][9]);
					user.setFlag(list[i][10]);
					user.setIdCard(list[i][11]);
					user.setCertCode(list[i][12]);
					user.setSmtp(list[i][13]);
					user.setMailFrom(list[i][14]);
					user.setMailName(list[i][15]);
					user.setMailPsw(list[i][16]);// user.setMailPsw(Encipher.DecodePasswd(list[i][16]));
					user.setYhbh(user.getIdCard());
					if (Pub.empty(list[i][17])) {
						user.setUserTemplate("blueTemplate");
					} else {
						user.setUserTemplate(list[i][17]);
					}
					return user;
				}
			}
		} catch (Exception e) {
			log.error("", e);
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return null;
	}

	public User getUserByLoginName(String loginName) throws Exception {
		
		//User user = getUserByLoginNameFromNc(loginName);
		//User user = null;
		//if (user == null) {
			User user = getUserFromDB(loginName, true);// 从数据库中读取User信息
			if (user != null) {
				usertable.put(user.getAccount(), user);
			}
		//}	
		return user;
	}
	
	public User getUserByLoginNameFromNc(String loginName) throws Exception {
		User user = (User) usertable.get(loginName);// -从内存中读取User信息
		return user;
	}
	

	public String getUserLastLoginTime(String loginName) throws Exception {
		String lastLoginTime = "";
		String[][] s = DBUtil
				.query("select * from (select to_char(logintime,'yyyy-MM-dd HH24:MI') from fs_log_userlogin t where t.userid = '"
						+ loginName + "' order by t.logintime desc ) where rownum<=1");
		if (s != null && s.length > 0) {
			lastLoginTime = s[0][0];
		}
		return lastLoginTime;
	}

	// public Hashtable getUserTable() {
	public Map<String, User> getUserTable() {
		return this.usertable;
	}

	/**
	 * 根据用户ID 获取用户名字.<br/>
	 * 
	 * @param userId
	 * @return String
	 * @since v1.00
	 */
	public String getUserNameByUserID(String userId) {
		User user = usersntable.get(userId);
		if (user != null) {
			return user.getUsername();
		}
		return "";
	}
	
	static synchronized public UserManager getInstance() throws Exception {
		if (instance == null) {
			instance = new UserManager();
			CacheManager.register(CacheManagerBg.CACHE_USER, instance);
			CacheManager.register(CacheManagerBg.CACHE_USERS, instance);
		}
		return instance;
	}
	
}