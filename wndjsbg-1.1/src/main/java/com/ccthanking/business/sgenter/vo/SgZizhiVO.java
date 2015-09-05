package com.ccthanking.business.sgenter.vo;
import com.ccthanking.framework.base.BaseVO;

public class SgZizhiVO extends BaseVO{

	public SgZizhiVO(){
		this.addField("SG_ZIZHI_UID",OP_STRING|this.TP_PK|this.TP_SEQUENCE);
		this.addField("ZIZHI_NAME",OP_STRING);
		this.addField("ZIZHI_TYPE",OP_STRING);
		this.addField("ZHUANYE_NAME",OP_STRING);
		this.addField("SERIAL_NO",OP_STRING);
		this.setVOTableName("sg_zizhi");
		
		this.bindFieldToSequence("SG_ZIZHI_UID", "SG_ZIZHI_UID");
	}

	public void setSg_zizhi_uid(String sg_zizhi_uid){
		this.setInternal("SG_ZIZHI_UID",sg_zizhi_uid);
	}
	public String getSg_zizhi_uid(){
		return (String)this.getInternal("SG_ZIZHI_UID");
	}
	public void setZizhi_name(String zizhi_name){
		this.setInternal("ZIZHI_NAME",zizhi_name);
	}
	public String getZizhi_name(){
		return (String)this.getInternal("ZIZHI_NAME");
	}
	public void setZizhi_type(String zizhi_type){
		this.setInternal("ZIZHI_TYPE",zizhi_type);
	}
	public String getZizhi_type(){
		return (String)this.getInternal("ZIZHI_TYPE");
	}
	public void setZhuanye_name(String zhuanye_name){
		this.setInternal("ZHUANYE_NAME",zhuanye_name);
	}
	public String getZhuanye_name(){
		return (String)this.getInternal("ZHUANYE_NAME");
	}
	public void setSerial_no(String serial_no){
		this.setInternal("SERIAL_NO",serial_no);
	}
	public String getSerial_no(){
		return (String)this.getInternal("SERIAL_NO");
	}
}