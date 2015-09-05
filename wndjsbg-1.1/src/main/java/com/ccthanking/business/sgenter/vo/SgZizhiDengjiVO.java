package com.ccthanking.business.sgenter.vo;
import java.util.Date;

import com.ccthanking.framework.base.BaseVO;

public class SgZizhiDengjiVO extends BaseVO{

	public SgZizhiDengjiVO(){
		this.addField("SG_ZIZHI_DENGJI_UID",OP_STRING|this.TP_PK|this.TP_SEQUENCE);
		this.addField("SG_ZIZHI_UID",OP_STRING);
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
		this.addField("DENGJI_NUMS",OP_STRING);
		this.addField("DENGJI_NAME",OP_STRING);
		this.addField("DESCRIPTION",OP_STRING);
		this.setFieldDateFormat("CREATED_DATE","yyyy-MM-dd");
		this.setFieldDateFormat("UPDATE_DATE","yyyy-MM-dd");
		this.setVOTableName("SG_ZIZHI_DENGJI");
		
		this.bindFieldToSequence("SG_ZIZHI_DENGJI_UID", "SG_ZIZHI_DENGJI_UID");
	}

	public void setSg_zizhi_dengji_uid(String sg_zizhi_dengji_uid){
		this.setInternal("SG_ZIZHI_DENGJI_UID",sg_zizhi_dengji_uid);
	}
	public String getSg_zizhi_dengji_uid(){
		return (String)this.getInternal("SG_ZIZHI_DENGJI_UID");
	}
	public void setSg_zizhi_uid(String sg_zizhi_uid){
		this.setInternal("SG_ZIZHI_UID",sg_zizhi_uid);
	}
	public String getSg_zizhi_uid(){
		return (String)this.getInternal("SG_ZIZHI_UID");
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
	public void setDengji_nums(String dengji_nums){
		this.setInternal("DENGJI_NUMS",dengji_nums);
	}
	public String getDengji_nums(){
		return (String)this.getInternal("DENGJI_NUMS");
	}
	public void setDengji_name(String dengji_name){
		this.setInternal("DENGJI_NAME",dengji_name);
	}
	public String getDengji_name(){
		return (String)this.getInternal("DENGJI_NAME");
	}
	public void setDescription(String description){
		this.setInternal("DESCRIPTION",description);
	}
	public String getDescription(){
		return (String)this.getInternal("DESCRIPTION");
	}
}