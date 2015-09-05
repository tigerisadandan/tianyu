package com.ccthanking.framework.common;
import com.ccthanking.framework.base.BaseVO;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.MenuVo;
import com.ccthanking.framework.coreapp.orgmanage.MenuManager;
import com.ccthanking.framework.util.Pub;

import java.sql.Connection;
import java.util.Date;

public class UsersVO extends BaseVO{

	//private String ywid;
	public UsersVO(){
		// 设置字段信息
		this.addField("USERS_UID",OP_STRING|this.TP_PK);//null
		this.addField("USER_NAME",OP_STRING);//用户姓名
		this.addField("LOGON_NAME",OP_STRING);//登录名
		this.addField("PWD",OP_STRING);//加密后的密码
		this.addField("MIMA",OP_STRING);//未加密的密码
		this.addField("ADMIN_Y",OP_STRING);//是否为管理员，Y为是
		this.addField("USE_Y",OP_STRING);//此用户是否可用，Y为可用
		this.addField("DESCRIPTION",OP_STRING);//null
		this.addField("CREATED_BY",OP_STRING);//null
		this.addField("CREATED_DATE",OP_DATE);//null
		this.addField("UPDATED_BY",OP_STRING);//null
		this.addField("UPDATED_DATE",OP_DATE);//null
		this.addField("USER_TYPE",OP_STRING);//JS:建设手续 SG:施工手续 ALL:所有权限
		this.addField("USER_GROUP",OP_STRING);//null
		this.addField("GS_SERIAL",OP_STRING);//null
		this.addField("DEPARTMENT",OP_STRING);//null
		this.setFieldDateFormat("CREATED_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("UPDATED_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("USERS");
		this.bindFieldToSequence("USERS_UID", "USERS_UID");
	}
	
	
	//String users_uid,user_name,logon_name,pwd,mima,admin_y,use_y,description,created_by,created_date,updated_by,updated_date;
	
	public void setUsers_uid(String users_uid){
		this.setInternal("USERS_UID",users_uid);
	}
	public String getUsers_uid(){
		return (String)this.getInternal("USERS_UID");
	}
	public void setUser_name(String user_name){
		this.setInternal("USER_NAME",user_name);
	}
	public String getUser_name(){
		return (String)this.getInternal("USER_NAME");
	}
	public void setLogon_name(String logon_name){
		this.setInternal("LOGON_NAME",logon_name);
	}
	public String getLogon_name(){
		return (String)this.getInternal("LOGON_NAME");
	}
	public void setPwd(String pwd){
		this.setInternal("PWD",pwd);
	}
	public String getPwd(){
		return (String)this.getInternal("PWD");
	}
	public void setMima(String mima){
		this.setInternal("MIMA",mima);
	}
	public String getMima(){
		return (String)this.getInternal("MIMA");
	}
	public void setAdmin_y(String admin_y){
		this.setInternal("ADMIN_Y",admin_y);
	}
	public String getAdmin_y(){
		return (String)this.getInternal("ADMIN_Y");
	}
	public void setUse_y(String use_y){
		this.setInternal("USE_Y",use_y);
	}
	public String getUse_y(){
		return (String)this.getInternal("USE_Y");
	}
	public void setDescription(String description){
		this.setInternal("DESCRIPTION",description);
	}
	public String getDescription(){
		return (String)this.getInternal("DESCRIPTION");
	}
	public void setCreated_by(String created_by){
		this.setInternal("CREATED_BY",created_by);
	}
	public String getCreated_by(){
		return (String)this.getInternal("CREATED_BY");
	}
	public void setCreated_date(Date created_date){
		this.setInternal("CREATED_DATE",created_date);
	}
	public Date getCreated_date(){
		return (Date)this.getInternal("CREATED_DATE");
	}
	public void setUpdated_by(String updated_by){
		this.setInternal("UPDATED_BY",updated_by);
	}
	public String getUpdated_by(){
		return (String)this.getInternal("UPDATED_BY");
	}
	public void setUpdated_date(Date updated_date){
		this.setInternal("UPDATED_DATE",updated_date);
	}
	public Date getUpdated_date(){
		return (Date)this.getInternal("UPDATED_DATE");
	}
	public void setUser_type(String user_type){
		this.setInternal("USER_TYPE",user_type);
	}
	public String getUser_type(){
		return (String)this.getInternal("USER_TYPE");
	}
	public void setUser_group(String user_group){
		this.setInternal("USER_GROUP",user_group);
	}
	public String getUser_group(){
		return (String)this.getInternal("USER_GROUP");
	}
	public void setGs_serial(String gs_serial){
		this.setInternal("GS_SERIAL",gs_serial);
	}
	public String getGs_serial(){
		return (String)this.getInternal("GS_SERIAL");
	}
	public void setDepartment(String department){
		this.setInternal("DEPARTMENT",department);
	}
	public String getDepartment(){
		return (String)this.getInternal("DEPARTMENT");
	}

	/*
	public String getYwid() {
		return ywid;
	}

	public void setYwid(String ywid) {
		this.ywid = ywid;
	}
	*/
	
	// 权限集合
	public static MenuVo[] getMenus(String users_uid) throws Exception {
		
		if (Pub.empty(users_uid))
			return null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select r.users_uid,m.name from fs_user_role r, FS_EAP_MENU m"
					+ " where r.role_code = m.code and r.users_uid = "+ users_uid
					+ " and m.name in (select NAME from fs_eap_menu where SFYX = '1' and levelno!=4)" 
					+ " order by ORDERNO";
			//log.debug("getRoleMenus : " + sql);
			String[][] list = DBUtil.querySql(conn, sql);
			if (list != null) {
				MenuVo[] menus = new MenuVo[list.length];
				for (int i = 0; i < list.length; i++) {
					menus[i] = MenuManager.getInstance().getMenu(list[i][1]);
				}
				return menus;
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
		} finally {
			DBUtil.closeConnetion(conn);
		}
		
		return null;
	}
	
}