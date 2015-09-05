package com.ccthanking.business.person.vo;
import java.util.Date;

import com.ccthanking.framework.base.BaseVO;

public class SgZhengshuVO extends BaseVO{

	public SgZhengshuVO(){
		this.addField("SG_ZHENGSHU_UID",OP_STRING|this.TP_PK);
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
		this.addField("ZHENGSHU_TYPE",OP_STRING);
		this.addField("ZHENGSHU_LEVEL",OP_STRING);
		this.addField("ZHENGSHU_NAME",OP_STRING);
		this.addField("DESCRIPTION",OP_STRING);
		this.setFieldDateFormat("CREATED_DATE","yyyy-MM-dd");
		this.setFieldDateFormat("UPDATE_DATE","yyyy-MM-dd");
		this.setVOTableName("SG_ZHENGSHU");
	}

	public void setSg_zhengshu_uid(String sg_zhengshu_uid){
		this.setInternal("SG_ZHENGSHU_UID",sg_zhengshu_uid);
	}
	public String getSg_zhengshu_uid(){
		return (String)this.getInternal("SG_ZHENGSHU_UID");
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
	public void setZhengshu_type(String zhengshu_type){
		this.setInternal("ZHENGSHU_TYPE",zhengshu_type);
	}
	public String getZhengshu_type(){
		return (String)this.getInternal("ZHENGSHU_TYPE");
	}
	public void setZhengshu_level(String zhengshu_level){
		this.setInternal("ZHENGSHU_LEVEL",zhengshu_level);
	}
	public String getZhengshu_level(){
		return (String)this.getInternal("ZHENGSHU_LEVEL");
	}
	public void setZhengshu_name(String zhengshu_name){
		this.setInternal("ZHENGSHU_NAME",zhengshu_name);
	}
	public String getZhengshu_name(){
		return (String)this.getInternal("ZHENGSHU_NAME");
	}
	public void setDescription(String description){
		this.setInternal("DESCRIPTION",description);
	}
	public String getDescription(){
		return (String)this.getInternal("DESCRIPTION");
	}
}