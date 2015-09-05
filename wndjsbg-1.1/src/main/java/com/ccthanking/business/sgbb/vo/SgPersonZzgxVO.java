package com.ccthanking.business.sgbb.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class SgPersonZzgxVO extends BaseVO{

	public SgPersonZzgxVO(){
		this.addField("SG_PERSON_ZZGX_UID",OP_STRING|this.TP_PK);//null
		this.addField("GANGWEI_UID",OP_STRING);//null
		this.addField("SGBB_RY_UID",OP_STRING);//null
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
		this.addField("SG_PERSON_UID",OP_STRING);//施工人员 UID
		this.addField("ZUZHI_GUANXI_UID",OP_STRING);//对应的组织关系UID
		this.setFieldDateFormat("CREATED_DATE","yyyy-MM-dd");
		this.setFieldDateFormat("UPDATE_DATE","yyyy-MM-dd");
		this.setVOTableName("SG_PERSON_ZZGX");
	}

	public void setSg_person_zzgx_uid(String sg_person_zzgx_uid){
		this.setInternal("SG_PERSON_ZZGX_UID",sg_person_zzgx_uid);
	}
	public String getSg_person_zzgx_uid(){
		return (String)this.getInternal("SG_PERSON_ZZGX_UID");
	}
	public void setGangwei_uid(String gangwei_uid){
		this.setInternal("GANGWEI_UID",gangwei_uid);
	}
	public String getGangwei_uid(){
		return (String)this.getInternal("GANGWEI_UID");
	}
	public void setSgbb_ry_uid(String sgbb_ry_uid){
		this.setInternal("SGBB_RY_UID",sgbb_ry_uid);
	}
	public String getSgbb_ry_uid(){
		return (String)this.getInternal("SGBB_RY_UID");
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
	public void setSg_person_uid(String sg_person_uid){
		this.setInternal("SG_PERSON_UID",sg_person_uid);
	}
	public String getSg_person_uid(){
		return (String)this.getInternal("SG_PERSON_UID");
	}
	public void setZuzhi_guanxi_uid(String zuzhi_guanxi_uid){
		this.setInternal("ZUZHI_GUANXI_UID",zuzhi_guanxi_uid);
	}
	public String getZuzhi_guanxi_uid(){
		return (String)this.getInternal("ZUZHI_GUANXI_UID");
	}
}