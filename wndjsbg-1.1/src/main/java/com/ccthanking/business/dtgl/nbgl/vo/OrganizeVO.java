package com.ccthanking.business.dtgl.nbgl.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class OrganizeVO extends BaseVO{

	public OrganizeVO(){
		this.addField("ORGANIZE_UID",OP_STRING|this.TP_PK);//null
		this.addField("P_ORGANIZE_UID",OP_STRING);//null
		this.addField("ORG_NAME",OP_STRING);//null
		this.addField("ORG_TYPE",OP_STRING);//null
		this.addField("CODE",OP_STRING);//null
		this.addField("ADDRESS",OP_STRING);//null
		this.addField("PHONE",OP_STRING);//null
		this.addField("FAX",OP_STRING);//null
		this.addField("POSTCODE",OP_STRING);//null
		this.addField("E_MAIL",OP_STRING);//null
		this.addField("HTTP",OP_STRING);//null
		this.addField("SERIAL_NO",OP_STRING);//null
		this.addField("DESCRIPTION",OP_STRING);//null
		this.addField("CREATED_BY",OP_STRING);//null
		this.addField("CREATED_DATE",OP_DATE);//null
		this.addField("USER_UID",OP_STRING);//null
		this.addField("ZJ_CODE",OP_STRING);//null
		this.addField("SEND_Y",OP_STRING);//null
		this.setFieldDateFormat("CREATED_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("ORGANIZE");
		this.bindFieldToSequence("ORGANIZE_UID", "ORGANIZE_UID");
	}

	public void setOrganize_uid(String organize_uid){
		this.setInternal("ORGANIZE_UID",organize_uid);
	}
	public String getOrganize_uid(){
		return (String)this.getInternal("ORGANIZE_UID");
	}
	public void setP_organize_uid(String p_organize_uid){
		this.setInternal("P_ORGANIZE_UID",p_organize_uid);
	}
	public String getP_organize_uid(){
		return (String)this.getInternal("P_ORGANIZE_UID");
	}
	public void setOrg_name(String org_name){
		this.setInternal("ORG_NAME",org_name);
	}
	public String getOrg_name(){
		return (String)this.getInternal("ORG_NAME");
	}
	public void setOrg_type(String org_type){
		this.setInternal("ORG_TYPE",org_type);
	}
	public String getOrg_type(){
		return (String)this.getInternal("ORG_TYPE");
	}
	public void setCode(String code){
		this.setInternal("CODE",code);
	}
	public String getCode(){
		return (String)this.getInternal("CODE");
	}
	public void setAddress(String address){
		this.setInternal("ADDRESS",address);
	}
	public String getAddress(){
		return (String)this.getInternal("ADDRESS");
	}
	public void setPhone(String phone){
		this.setInternal("PHONE",phone);
	}
	public String getPhone(){
		return (String)this.getInternal("PHONE");
	}
	public void setFax(String fax){
		this.setInternal("FAX",fax);
	}
	public String getFax(){
		return (String)this.getInternal("FAX");
	}
	public void setPostcode(String postcode){
		this.setInternal("POSTCODE",postcode);
	}
	public String getPostcode(){
		return (String)this.getInternal("POSTCODE");
	}
	public void setE_mail(String e_mail){
		this.setInternal("E_MAIL",e_mail);
	}
	public String getE_mail(){
		return (String)this.getInternal("E_MAIL");
	}
	public void setHttp(String http){
		this.setInternal("HTTP",http);
	}
	public String getHttp(){
		return (String)this.getInternal("HTTP");
	}
	public void setSerial_no(String serial_no){
		this.setInternal("SERIAL_NO",serial_no);
	}
	public String getSerial_no(){
		return (String)this.getInternal("SERIAL_NO");
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
	public void setUser_uid(String user_uid){
		this.setInternal("USER_UID",user_uid);
	}
	public String getUser_uid(){
		return (String)this.getInternal("USER_UID");
	}
	public void setZj_code(String zj_code){
		this.setInternal("ZJ_CODE",zj_code);
	}
	public String getZj_code(){
		return (String)this.getInternal("ZJ_CODE");
	}
	public void setSend_y(String send_y){
		this.setInternal("SEND_Y",send_y);
	}
	public String getSend_y(){
		return (String)this.getInternal("SEND_Y");
	}
}