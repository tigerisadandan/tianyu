package com.ccthanking.business.sgbb.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class GcTypeVO extends BaseVO{

	public GcTypeVO(){
		this.addField("GC_TYPE_UID",OP_STRING);//null
		this.addField("P_TYPE_UID",OP_STRING);//null
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
		this.addField("CODES",OP_STRING);//工程类型代码，GG－公共建筑工程；XQ－住宅小区（群体）工程；SY－商业用户；BZ－保障性住房；AZ－农民安置房；SZ－市政工程
		this.addField("NAMES",OP_STRING);//工程类型名称
		this.addField("NODE_TYPE",OP_STRING);//节点类型，T－主要类型；S－子类型。监理工程等级仅对主要类型进行设置。
		this.addField("UNIT",OP_STRING);//工程等级划分标准的单位，如平方米、万元等
		this.addField("DESCRIPTION",OP_STRING);//null
		this.addField("TAGS",OP_STRING);//标记，J－监理项目工程类型；S－施工项目工程类型
		this.addField("SG_ZIZHI_UID",OP_STRING);//施工项目工程类型时对应的专业
		this.setFieldDateFormat("CREATED_DATE","yyyy-MM-dd");
		this.setFieldDateFormat("UPDATE_DATE","yyyy-MM-dd");
		this.setVOTableName("GC_TYPE");
	}

	public void setGc_type_uid(String gc_type_uid){
		this.setInternal("GC_TYPE_UID",gc_type_uid);
	}
	public String getGc_type_uid(){
		return (String)this.getInternal("GC_TYPE_UID");
	}
	public void setP_type_uid(String p_type_uid){
		this.setInternal("P_TYPE_UID",p_type_uid);
	}
	public String getP_type_uid(){
		return (String)this.getInternal("P_TYPE_UID");
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
	public void setCodes(String codes){
		this.setInternal("CODES",codes);
	}
	public String getCodes(){
		return (String)this.getInternal("CODES");
	}
	public void setNames(String names){
		this.setInternal("NAMES",names);
	}
	public String getNames(){
		return (String)this.getInternal("NAMES");
	}
	public void setNode_type(String node_type){
		this.setInternal("NODE_TYPE",node_type);
	}
	public String getNode_type(){
		return (String)this.getInternal("NODE_TYPE");
	}
	public void setUnit(String unit){
		this.setInternal("UNIT",unit);
	}
	public String getUnit(){
		return (String)this.getInternal("UNIT");
	}
	public void setDescription(String description){
		this.setInternal("DESCRIPTION",description);
	}
	public String getDescription(){
		return (String)this.getInternal("DESCRIPTION");
	}
	public void setTags(String tags){
		this.setInternal("TAGS",tags);
	}
	public String getTags(){
		return (String)this.getInternal("TAGS");
	}
	public void setSg_zizhi_uid(String sg_zizhi_uid){
		this.setInternal("SG_ZIZHI_UID",sg_zizhi_uid);
	}
	public String getSg_zizhi_uid(){
		return (String)this.getInternal("SG_ZIZHI_UID");
	}
}