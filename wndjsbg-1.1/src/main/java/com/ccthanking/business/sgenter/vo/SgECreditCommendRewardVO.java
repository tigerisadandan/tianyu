package com.ccthanking.business.sgenter.vo;
import java.util.Date;

import com.ccthanking.framework.base.BaseVO;

public class SgECreditCommendRewardVO extends BaseVO{

	public SgECreditCommendRewardVO(){
		this.addField("CREDIT_COMMEND_REWARD_UID",OP_STRING|this.TP_PK|this.TP_SEQUENCE);
		this.addField("SG_ENTERPRISE_LIBRARY_UID",OP_STRING);
		this.addField("EVENT_UID",OP_STRING);
		this.addField("ENABLED",OP_STRING);
		this.addField("DESCRIBE",OP_STRING);
		this.addField("CREATED_UID",OP_STRING);
		this.addField("CREATED_NAME",OP_STRING);
		this.addField("CREATED_DATE",OP_DATE);
		this.addField("UPDATE_UID",OP_STRING);
		this.addField("UPDATE_NAME",OP_STRING);
		this.addField("UPDATE_DATE",OP_DATE);
		this.addField("SERIAL_NO",OP_STRING);
		this.addField("CR_NAME",OP_STRING);
		this.addField("CR_LEVEL",OP_STRING);
		this.addField("CR_TYPE",OP_STRING);
		this.addField("CR_SCORE",OP_STRING);
		this.addField("SF_CHECKED",OP_STRING);
		this.addField("BG_VALIDITY_DATE",OP_DATE);
		this.addField("YXQX",OP_STRING);
		this.setFieldDateFormat("CREATED_DATE","yyyy-MM-dd");
		this.setFieldDateFormat("UPDATE_DATE","yyyy-MM-dd");
		this.setFieldDateFormat("BG_VALIDITY_DATE","yyyy-MM-dd");
		
		this.bindFieldToSequence("CREDIT_COMMEND_REWARD_UID", "CREDIT_COMMEND_REWARD_UID");
		
		this.setVOTableName("SG_E_CREDIT_COMMEND_REWARD");
	}

	public void setCredit_commend_reward_uid(String credit_commend_reward_uid){
		this.setInternal("CREDIT_COMMEND_REWARD_UID",credit_commend_reward_uid);
	}
	public String getCredit_commend_reward_uid(){
		return (String)this.getInternal("CREDIT_COMMEND_REWARD_UID");
	}
	public void setSg_enterprise_library_uid(String sg_enterprise_library_uid){
		this.setInternal("SG_ENTERPRISE_LIBRARY_UID",sg_enterprise_library_uid);
	}
	public String getSg_enterprise_library_uid(){
		return (String)this.getInternal("SG_ENTERPRISE_LIBRARY_UID");
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
	public void setCr_name(String cr_name){
		this.setInternal("CR_NAME",cr_name);
	}
	public String getCr_name(){
		return (String)this.getInternal("CR_NAME");
	}
	public void setCr_level(String cr_level){
		this.setInternal("CR_LEVEL",cr_level);
	}
	public String getCr_level(){
		return (String)this.getInternal("CR_LEVEL");
	}
	public void setCr_type(String cr_type){
		this.setInternal("CR_TYPE",cr_type);
	}
	public String getCr_type(){
		return (String)this.getInternal("CR_TYPE");
	}
	public void setCr_score(String cr_score){
		this.setInternal("CR_SCORE",cr_score);
	}
	public String getCr_score(){
		return (String)this.getInternal("CR_SCORE");
	}
	public void setSf_checked(String sf_checked){
		this.setInternal("SF_CHECKED",sf_checked);
	}
	public String getSf_checked(){
		return (String)this.getInternal("SF_CHECKED");
	}
	public void setBg_validity_date(Date bg_validity_date){
		this.setInternal("BG_VALIDITY_DATE",bg_validity_date);
	}
	public Date getBg_validity_date(){
		return (Date)this.getInternal("BG_VALIDITY_DATE");
	}
	public void setYxqx(String yxqx){
		this.setInternal("YXQX",yxqx);
	}
	public String getYxqx(){
		return (String)this.getInternal("YXQX");
	}
}