package com.ccthanking.business.sgbb.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class SgbbRyVO extends BaseVO{

	public SgbbRyVO(){
		this.addField("SGBB_RY_UID",OP_STRING|this.TP_PK);//null
		this.addField("SGBB_UID",OP_STRING);//null
		this.addField("EVENT_UID",OP_STRING);//事件编号
		this.addField("ENABLED",OP_STRING);//是否有效
		this.addField("DESCRIBE",OP_STRING);//备注
		this.addField("CREATED_UID",OP_STRING);//创建人编号
		this.addField("CREATED_NAME",OP_STRING);//创建人
		this.addField("CREATED_DATE",OP_DATE);//创建时间
		this.addField("UPDATE_UID",OP_STRING);//更新人编号
		this.addField("UPDATE_NAME",OP_STRING);//更新人
		this.addField("UPDATE_DATE",OP_DATE);//更新时间
		this.addField("SERIAL_NO",OP_STRING);//排序号
		this.addField("XUHAO",OP_STRING);//序号
		this.addField("MUST_Y",OP_STRING);//是否为必需的岗位，Y－必须
		this.addField("SG_PERSON_UID",OP_STRING);//施工人员 UID
		this.addField("SG_NAME",OP_STRING);//施工人员姓名
		this.addField("ZHENGSHU_NAME",OP_STRING);//证书名称
		this.addField("ZHUANYE",OP_STRING);//注册专业
		this.addField("ZHENGSHU_CODE",OP_STRING);//资质证书编号
		this.addField("ZHENGSHU_DATE",OP_DATE);//证书有效期
		this.addField("AQKH_CODE",OP_STRING);//安全考核证书号
		this.addField("AGE",OP_STRING);//年龄
		this.addField("ZHICHENG_NAME",OP_STRING);//职称
		this.addField("MOBILE",OP_STRING);//联系电话
		this.addField("SHENFENZHENG",OP_STRING);//身份证
		this.addField("UPDATED_DATE",OP_DATE);//记录更新时间
		this.addField("STATUS",OP_STRING);//状态  1：入库（最终数据）；10：审核通过；20：审核未通过；30：已提交未审核；40：未提交
		this.addField("LOCK_END_DATE",OP_DATE);//锁定状态到期日期，到期自动解锁
		this.addField("CHANGED_DATE",OP_DATE);//变更日期
		this.addField("CHANGED_REASON",OP_STRING);//变更原因
		this.addField("YOUXIAO_Y",OP_STRING);//是否属于该项目部成员，变更后为N
		this.addField("OLD_UID",OP_STRING);//仅变更的记录有效，记录原UID
		this.addField("GANGWEI_UID",OP_STRING);//岗位
		this.setFieldDateFormat("CREATED_DATE","yyyy-MM-dd");
		this.setFieldDateFormat("UPDATE_DATE","yyyy-MM-dd");
		this.setFieldDateFormat("ZHENGSHU_DATE","yyyy-MM-dd");
		this.setFieldDateFormat("UPDATED_DATE","yyyy-MM-dd");
		this.setFieldDateFormat("LOCK_END_DATE","yyyy-MM-dd");
		this.setFieldDateFormat("CHANGED_DATE","yyyy-MM-dd");
		this.setVOTableName("SGBB_RY");
		
		this.bindFieldToSequence("SGBB_RY_UID", "SGBB_RY_UID");
	}

	public void setSgbb_ry_uid(String sgbb_ry_uid){
		this.setInternal("SGBB_RY_UID",sgbb_ry_uid);
	}
	public String getSgbb_ry_uid(){
		return (String)this.getInternal("SGBB_RY_UID");
	}
	public void setSgbb_uid(String sgbb_uid){
		this.setInternal("SGBB_UID",sgbb_uid);
	}
	public String getSgbb_uid(){
		return (String)this.getInternal("SGBB_UID");
	}
	public void setEvent_uid(String event_uid){
		this.setInternal("EVENT_UID",event_uid);
	}
	public String getEvent_uid(){
		return (String)this.getInternal("EVENT_UID");
	}
	public void setEnabled(String enabled){
		this.setInternal("ENABLED",enabled);
	}
	public String getEnabled(){
		return (String)this.getInternal("ENABLED");
	}
	public void setDescribe(String describe){
		this.setInternal("DESCRIBE",describe);
	}
	public String getDescribe(){
		return (String)this.getInternal("DESCRIBE");
	}
	public void setCreated_uid(String created_uid){
		this.setInternal("CREATED_UID",created_uid);
	}
	public String getCreated_uid(){
		return (String)this.getInternal("CREATED_UID");
	}
	public void setCreated_name(String created_name){
		this.setInternal("CREATED_NAME",created_name);
	}
	public String getCreated_name(){
		return (String)this.getInternal("CREATED_NAME");
	}
	public void setCreated_date(Date created_date){
		this.setInternal("CREATED_DATE",created_date);
	}
	public Date getCreated_date(){
		return (Date)this.getInternal("CREATED_DATE");
	}
	public void setUpdate_uid(String update_uid){
		this.setInternal("UPDATE_UID",update_uid);
	}
	public String getUpdate_uid(){
		return (String)this.getInternal("UPDATE_UID");
	}
	public void setUpdate_name(String update_name){
		this.setInternal("UPDATE_NAME",update_name);
	}
	public String getUpdate_name(){
		return (String)this.getInternal("UPDATE_NAME");
	}
	public void setUpdate_date(Date update_date){
		this.setInternal("UPDATE_DATE",update_date);
	}
	public Date getUpdate_date(){
		return (Date)this.getInternal("UPDATE_DATE");
	}
	public void setSerial_no(String serial_no){
		this.setInternal("SERIAL_NO",serial_no);
	}
	public String getSerial_no(){
		return (String)this.getInternal("SERIAL_NO");
	}
	public void setXuhao(String xuhao){
		this.setInternal("XUHAO",xuhao);
	}
	public String getXuhao(){
		return (String)this.getInternal("XUHAO");
	}
	public void setMust_y(String must_y){
		this.setInternal("MUST_Y",must_y);
	}
	public String getMust_y(){
		return (String)this.getInternal("MUST_Y");
	}
	public void setSg_person_uid(String sg_person_uid){
		this.setInternal("SG_PERSON_UID",sg_person_uid);
	}
	public String getSg_person_uid(){
		return (String)this.getInternal("SG_PERSON_UID");
	}
	public void setSg_name(String sg_name){
		this.setInternal("SG_NAME",sg_name);
	}
	public String getSg_name(){
		return (String)this.getInternal("SG_NAME");
	}
	public void setZhengshu_name(String zhengshu_name){
		this.setInternal("ZHENGSHU_NAME",zhengshu_name);
	}
	public String getZhengshu_name(){
		return (String)this.getInternal("ZHENGSHU_NAME");
	}
	public void setZhuanye(String zhuanye){
		this.setInternal("ZHUANYE",zhuanye);
	}
	public String getZhuanye(){
		return (String)this.getInternal("ZHUANYE");
	}
	public void setZhengshu_code(String zhengshu_code){
		this.setInternal("ZHENGSHU_CODE",zhengshu_code);
	}
	public String getZhengshu_code(){
		return (String)this.getInternal("ZHENGSHU_CODE");
	}
	public void setZhengshu_date(Date zhengshu_date){
		this.setInternal("ZHENGSHU_DATE",zhengshu_date);
	}
	public Date getZhengshu_date(){
		return (Date)this.getInternal("ZHENGSHU_DATE");
	}
	public void setAqkh_code(String aqkh_code){
		this.setInternal("AQKH_CODE",aqkh_code);
	}
	public String getAqkh_code(){
		return (String)this.getInternal("AQKH_CODE");
	}
	public void setAge(String age){
		this.setInternal("AGE",age);
	}
	public String getAge(){
		return (String)this.getInternal("AGE");
	}
	public void setZhicheng_name(String zhicheng_name){
		this.setInternal("ZHICHENG_NAME",zhicheng_name);
	}
	public String getZhicheng_name(){
		return (String)this.getInternal("ZHICHENG_NAME");
	}
	public void setMobile(String mobile){
		this.setInternal("MOBILE",mobile);
	}
	public String getMobile(){
		return (String)this.getInternal("MOBILE");
	}
	public void setShenfenzheng(String shenfenzheng){
		this.setInternal("SHENFENZHENG",shenfenzheng);
	}
	public String getShenfenzheng(){
		return (String)this.getInternal("SHENFENZHENG");
	}
	public void setUpdated_date(Date updated_date){
		this.setInternal("UPDATED_DATE",updated_date);
	}
	public Date getUpdated_date(){
		return (Date)this.getInternal("UPDATED_DATE");
	}
	public void setStatus(String status){
		this.setInternal("STATUS",status);
	}
	public String getStatus(){
		return (String)this.getInternal("STATUS");
	}
	public void setLock_end_date(Date lock_end_date){
		this.setInternal("LOCK_END_DATE",lock_end_date);
	}
	public Date getLock_end_date(){
		return (Date)this.getInternal("LOCK_END_DATE");
	}
	public void setChanged_date(Date changed_date){
		this.setInternal("CHANGED_DATE",changed_date);
	}
	public Date getChanged_date(){
		return (Date)this.getInternal("CHANGED_DATE");
	}
	public void setChanged_reason(String changed_reason){
		this.setInternal("CHANGED_REASON",changed_reason);
	}
	public String getChanged_reason(){
		return (String)this.getInternal("CHANGED_REASON");
	}
	public void setYouxiao_y(String youxiao_y){
		this.setInternal("YOUXIAO_Y",youxiao_y);
	}
	public String getYouxiao_y(){
		return (String)this.getInternal("YOUXIAO_Y");
	}
	public void setOld_uid(String old_uid){
		this.setInternal("OLD_UID",old_uid);
	}
	public String getOld_uid(){
		return (String)this.getInternal("OLD_UID");
	}
	public void setGangwei_uid(String gangwei_uid){
		this.setInternal("GANGWEI_UID",gangwei_uid);
	}
	public String getGangwei_uid(){
		return (String)this.getInternal("GANGWEI_UID");
	}
}