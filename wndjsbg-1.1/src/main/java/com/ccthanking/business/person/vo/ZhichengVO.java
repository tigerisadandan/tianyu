package com.ccthanking.business.person.vo;
import com.ccthanking.framework.base.BaseVO;

public class ZhichengVO extends BaseVO{

	public ZhichengVO(){
		this.addField("ZHICHENG_UID",OP_STRING|this.TP_PK);
		this.addField("ZHICHENG_NAME",OP_STRING);
		this.addField("SERIAL_NO",OP_STRING);
		this.setVOTableName("ZHICHENG");
	}

	public void setZhicheng_uid(String zhicheng_uid){
		this.setInternal("ZHICHENG_UID",zhicheng_uid);
	}
	public String getZhicheng_uid(){
		return (String)this.getInternal("ZHICHENG_UID");
	}
	public void setZhicheng_name(String zhicheng_name){
		this.setInternal("ZHICHENG_NAME",zhicheng_name);
	}
	public String getZhicheng_name(){
		return (String)this.getInternal("ZHICHENG_NAME");
	}
	public void setSerial_no(String serial_no){
		this.setInternal("SERIAL_NO",serial_no);
	}
	public String getSerial_no(){
		return (String)this.getInternal("SERIAL_NO");
	}
}