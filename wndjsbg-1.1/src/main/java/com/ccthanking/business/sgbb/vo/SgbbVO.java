package com.ccthanking.business.sgbb.vo;

import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class SgbbVO extends BaseVO{

	public SgbbVO(){
		this.addField("SGBB_UID",OP_STRING|this.TP_PK);//null
		this.addField("GC_TYPE_UID",OP_STRING);//null
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
		this.addField("PROJECT_CODE",OP_STRING);//项目招标公告编号
		this.addField("KB_DATE",OP_DATE);//开标日期
		this.addField("BB_CODE",OP_STRING);//施工报备编码
		this.addField("PROJECT_NAME",OP_STRING);//工程名称
		this.addField("CB_XINGZHI",OP_STRING);//承包性质，ZB－总承包；CB－专业承包
		this.addField("CB_ZIZHI_DENGJI",OP_STRING);//承包企业对应的资质等级
		this.addField("GC_SUB_TYPE_UID",OP_STRING);//工程子类型UID
		this.addField("GC_SUB_TYPE_CODE",OP_STRING);//工程子类型编码
		this.addField("BID_TYPE",OP_STRING);//发包方式，1－公开招标；2－邀请招标；3－直接发包
		this.addField("GUIMO",OP_STRING);//建设面积（万平方米）
		this.addField("CENGSHU",OP_STRING);//层数
		this.addField("GAODU",OP_STRING);//高度（米）
		this.addField("KUADU",OP_STRING);//跨度（米）
		this.addField("SHENDU",OP_STRING);//深度（米）
		this.addField("JINE",OP_STRING);//单项合同额（万元）
		this.addField("ZHONGLIANG",OP_STRING);//重量（吨）
		this.addField("SGRY_NUMS",OP_STRING);//施工人员配备标准
		this.addField("SG_COMPANY_UID",OP_STRING);//施工企业编号
		this.addField("UPDATED_DATE",OP_DATE);//记录更新时间
		this.addField("DESCRIPTION",OP_STRING);//null
		this.addField("STATUS",OP_STRING);//状态  1：入库（最终数据）；10：审核通过；20：审核未通过；30：已提交未审核；40：未提交
		this.addField("TIJIAO_DATE",OP_DATE);//提交时间
		this.addField("SHENHE_REN",OP_STRING);//审核人
		this.addField("SHENHE_DATE",OP_DATE);//审核时间或入库更新时间 
		this.addField("SHENHE_JIEGUO",OP_STRING);//审核结果，1：审核通过；2：审核未通过
		this.addField("SHENHE_YIJIAN",OP_STRING);//审核详细意见
		this.addField("FINISH_DATE",OP_DATE);//完成时间，确定未中标或者确定中标
		this.addField("PROJECTS_UID",OP_STRING);//最终备案的实际项目UID
		this.addField("ZUZHI_GUANXI_UID",OP_STRING);//对应的组织关系UID
		this.addField("ZBGG_ID",OP_STRING);//招标公告ID
		this.setFieldDateFormat("CREATED_DATE","yyyy-MM-dd");
		this.setFieldDateFormat("UPDATE_DATE","yyyy-MM-dd");
		this.setFieldDateFormat("KB_DATE","yyyy-MM-dd HH:mm");
		this.setFieldDateFormat("UPDATED_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("TIJIAO_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("SHENHE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("FINISH_DATE","yyyy-MM-dd");
		this.setVOTableName("SGBB");
		
		this.bindFieldToSequence("SGBB_UID", "SGBB_UID");
	}

	public void setSgbb_uid(String sgbb_uid){
		this.setInternal("SGBB_UID",sgbb_uid);
	}
	public String getSgbb_uid(){
		return (String)this.getInternal("SGBB_UID");
	}
	public void setGc_type_uid(String gc_type_uid){
		this.setInternal("GC_TYPE_UID",gc_type_uid);
	}
	public String getGc_type_uid(){
		return (String)this.getInternal("GC_TYPE_UID");
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
	public void setProject_code(String project_code){
		this.setInternal("PROJECT_CODE",project_code);
	}
	public String getProject_code(){
		return (String)this.getInternal("PROJECT_CODE");
	}
	public void setKb_date(Date kb_date){
		this.setInternal("KB_DATE",kb_date);
	}
	public Date getKb_date(){
		return (Date)this.getInternal("KB_DATE");
	}
	public void setBb_code(String bb_code){
		this.setInternal("BB_CODE",bb_code);
	}
	public String getBb_code(){
		return (String)this.getInternal("BB_CODE");
	}
	public void setProject_name(String project_name){
		this.setInternal("PROJECT_NAME",project_name);
	}
	public String getProject_name(){
		return (String)this.getInternal("PROJECT_NAME");
	}
	public void setCb_xingzhi(String cb_xingzhi){
		this.setInternal("CB_XINGZHI",cb_xingzhi);
	}
	public String getCb_xingzhi(){
		return (String)this.getInternal("CB_XINGZHI");
	}
	public void setCb_zizhi_dengji(String cb_zizhi_dengji){
		this.setInternal("CB_ZIZHI_DENGJI",cb_zizhi_dengji);
	}
	public String getCb_zizhi_dengji(){
		return (String)this.getInternal("CB_ZIZHI_DENGJI");
	}
	public void setGc_sub_type_uid(String gc_sub_type_uid){
		this.setInternal("GC_SUB_TYPE_UID",gc_sub_type_uid);
	}
	public String getGc_sub_type_uid(){
		return (String)this.getInternal("GC_SUB_TYPE_UID");
	}
	public void setGc_sub_type_code(String gc_sub_type_code){
		this.setInternal("GC_SUB_TYPE_CODE",gc_sub_type_code);
	}
	public String getGc_sub_type_code(){
		return (String)this.getInternal("GC_SUB_TYPE_CODE");
	}
	public void setBid_type(String bid_type){
		this.setInternal("BID_TYPE",bid_type);
	}
	public String getBid_type(){
		return (String)this.getInternal("BID_TYPE");
	}
	public void setGuimo(String guimo){
		this.setInternal("GUIMO",guimo);
	}
	public String getGuimo(){
		return (String)this.getInternal("GUIMO");
	}
	public void setCengshu(String cengshu){
		this.setInternal("CENGSHU",cengshu);
	}
	public String getCengshu(){
		return (String)this.getInternal("CENGSHU");
	}
	public void setGaodu(String gaodu){
		this.setInternal("GAODU",gaodu);
	}
	public String getGaodu(){
		return (String)this.getInternal("GAODU");
	}
	public void setKuadu(String kuadu){
		this.setInternal("KUADU",kuadu);
	}
	public String getKuadu(){
		return (String)this.getInternal("KUADU");
	}
	public void setShendu(String shendu){
		this.setInternal("SHENDU",shendu);
	}
	public String getShendu(){
		return (String)this.getInternal("SHENDU");
	}
	public void setJine(String jine){
		this.setInternal("JINE",jine);
	}
	public String getJine(){
		return (String)this.getInternal("JINE");
	}
	public void setZhongliang(String zhongliang){
		this.setInternal("ZHONGLIANG",zhongliang);
	}
	public String getZhongliang(){
		return (String)this.getInternal("ZHONGLIANG");
	}
	public void setSgry_nums(String sgry_nums){
		this.setInternal("SGRY_NUMS",sgry_nums);
	}
	public String getSgry_nums(){
		return (String)this.getInternal("SGRY_NUMS");
	}
	public void setSg_company_uid(String sg_company_uid){
		this.setInternal("SG_COMPANY_UID",sg_company_uid);
	}
	public String getSg_company_uid(){
		return (String)this.getInternal("SG_COMPANY_UID");
	}
	public void setUpdated_date(Date updated_date){
		this.setInternal("UPDATED_DATE",updated_date);
	}
	public Date getUpdated_date(){
		return (Date)this.getInternal("UPDATED_DATE");
	}
	public void setDescription(String description){
		this.setInternal("DESCRIPTION",description);
	}
	public String getDescription(){
		return (String)this.getInternal("DESCRIPTION");
	}
	public void setStatus(String status){
		this.setInternal("STATUS",status);
	}
	public String getStatus(){
		return (String)this.getInternal("STATUS");
	}
	public void setTijiao_date(Date tijiao_date){
		this.setInternal("TIJIAO_DATE",tijiao_date);
	}
	public Date getTijiao_date(){
		return (Date)this.getInternal("TIJIAO_DATE");
	}
	public void setShenhe_ren(String shenhe_ren){
		this.setInternal("SHENHE_REN",shenhe_ren);
	}
	public String getShenhe_ren(){
		return (String)this.getInternal("SHENHE_REN");
	}
	public void setShenhe_date(Date shenhe_date){
		this.setInternal("SHENHE_DATE",shenhe_date);
	}
	public Date getShenhe_date(){
		return (Date)this.getInternal("SHENHE_DATE");
	}
	public void setShenhe_jieguo(String shenhe_jieguo){
		this.setInternal("SHENHE_JIEGUO",shenhe_jieguo);
	}
	public String getShenhe_jieguo(){
		return (String)this.getInternal("SHENHE_JIEGUO");
	}
	public void setShenhe_yijian(String shenhe_yijian){
		this.setInternal("SHENHE_YIJIAN",shenhe_yijian);
	}
	public String getShenhe_yijian(){
		return (String)this.getInternal("SHENHE_YIJIAN");
	}
	public void setFinish_date(Date finish_date){
		this.setInternal("FINISH_DATE",finish_date);
	}
	public Date getFinish_date(){
		return (Date)this.getInternal("FINISH_DATE");
	}
	public void setProjects_uid(String projects_uid){
		this.setInternal("PROJECTS_UID",projects_uid);
	}
	public String getProjects_uid(){
		return (String)this.getInternal("PROJECTS_UID");
	}
	public void setZuzhi_guanxi_uid(String zuzhi_guanxi_uid){
		this.setInternal("ZUZHI_GUANXI_UID",zuzhi_guanxi_uid);
	}
	public String getZuzhi_guanxi_uid(){
		return (String)this.getInternal("ZUZHI_GUANXI_UID");
	}
	public void setZbgg_id(String zbgg_id){
		this.setInternal("ZBGG_ID",zbgg_id);
	}
	public String getZbgg_id(){
		return (String)this.getInternal("ZBGG_ID");
	}
}