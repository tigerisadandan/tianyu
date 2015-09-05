package com.ccthanking.business.sgenter.vo;
import java.util.Date;

import com.ccthanking.framework.base.BaseVO;

public class AtFileuploadVO extends BaseVO{

	public AtFileuploadVO(){
		this.addField("AT_FILEUPLOAD_UID",OP_STRING|this.TP_PK|this.TP_SEQUENCE);
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
		this.addField("TARGET_TYPE",OP_STRING);
		this.addField("TARGET_UID",OP_STRING);
		this.addField("FILE_TYPE",OP_STRING);
		this.addField("FILE_NAME",OP_STRING);
		this.addField("EXT_NAME",OP_STRING);
		this.addField("DOC_SIZE",OP_STRING);
		this.addField("MIME_TYPE",OP_STRING);
		this.addField("BUSINESS_SUB_TYPE",OP_STRING);
		this.addField("FILE_SAVE_NAME",OP_STRING);
		this.setFieldDateFormat("CREATED_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("UPDATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("AT_FILEUPLOAD");
		
		this.bindFieldToSequence("AT_FILEUPLOAD_UID", "AT_FILEUPLOAD_UID");
	}

	public void setAt_fileupload_uid(String at_fileupload_uid){
		this.setInternal("AT_FILEUPLOAD_UID",at_fileupload_uid);
	}
	public String getAt_fileupload_uid(){
		return (String)this.getInternal("AT_FILEUPLOAD_UID");
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
	public void setTarget_type(String target_type){
		this.setInternal("TARGET_TYPE",target_type);
	}
	public String getTarget_type(){
		return (String)this.getInternal("TARGET_TYPE");
	}
	public void setTarget_uid(String target_uid){
		this.setInternal("TARGET_UID",target_uid);
	}
	public String getTarget_uid(){
		return (String)this.getInternal("TARGET_UID");
	}
	public void setFile_type(String file_type){
		this.setInternal("FILE_TYPE",file_type);
	}
	public String getFile_type(){
		return (String)this.getInternal("FILE_TYPE");
	}
	public void setFile_name(String file_name){
		this.setInternal("FILE_NAME",file_name);
	}
	public String getFile_name(){
		return (String)this.getInternal("FILE_NAME");
	}
	public void setExt_name(String ext_name){
		this.setInternal("EXT_NAME",ext_name);
	}
	public String getExt_name(){
		return (String)this.getInternal("EXT_NAME");
	}
	public void setDoc_size(String doc_size){
		this.setInternal("DOC_SIZE",doc_size);
	}
	public String getDoc_size(){
		return (String)this.getInternal("DOC_SIZE");
	}
	public void setMime_type(String mime_type){
		this.setInternal("MIME_TYPE",mime_type);
	}
	public String getMime_type(){
		return (String)this.getInternal("MIME_TYPE");
	}
	public void setBusiness_sub_type(String business_sub_type){
		this.setInternal("BUSINESS_SUB_TYPE",business_sub_type);
	}
	public String getBusiness_sub_type(){
		return (String)this.getInternal("BUSINESS_SUB_TYPE");
	}
	public void setFile_save_name(String file_save_name){
		this.setInternal("FILE_SAVE_NAME",file_save_name);
	}
	public String getFile_save_name(){
		return (String)this.getInternal("FILE_SAVE_NAME");
	}
}