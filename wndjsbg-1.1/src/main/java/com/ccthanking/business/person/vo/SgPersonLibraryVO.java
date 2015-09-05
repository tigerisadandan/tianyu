package com.ccthanking.business.person.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class SgPersonLibraryVO extends BaseVO{

	public SgPersonLibraryVO(){
		this.addField("SG_PERSON_LIBRARY_UID",OP_STRING|this.TP_PK);//null
		this.addField("ZHUANYE1",OP_STRING);//专业1
		this.addField("ZHUANYE2",OP_STRING);//专业2
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
		this.addField("SG_COMPANY_UID",OP_STRING);//施工企业编号
		this.addField("DENGLU_CODE",OP_STRING);//登录代码
		this.addField("PWD",OP_STRING);//登录密码（MD5加密）
		this.addField("MIMA",OP_STRING);//登录密码（未加密）
		this.addField("PERSON_NAME",OP_STRING);//施工人员姓名 
		this.addField("SEX",OP_STRING);//性别
		this.addField("BIRTHDAY",OP_DATE);//出生日期
		this.addField("SHENFENZHENG",OP_STRING);//身份证
		this.addField("PHOTO",OP_STRING);//照片文件名称
		this.addField("NATION",OP_STRING);//民族
		this.addField("PHONE",OP_STRING);//公司电话
		this.addField("EMAIL",OP_STRING);//Email
		this.addField("XUELI",OP_STRING);//学历
		this.addField("COLLEGE",OP_STRING);//毕业院校
		this.addField("ZHICHENG_CODE",OP_STRING);//职称证号
		this.addField("JISHU_Y",OP_STRING);//是否为技术负责人
		this.addField("FZR_Y",OP_STRING);//是否为企业负责人
		this.addField("END_DATE",OP_DATE);//劳动合同结束日期
		this.addField("DESCRIPTION",OP_STRING);//null
		this.addField("STATUS",OP_STRING);//状态  1：入库（最终数据）；10：审核通过；20：审核未通过；30：已提交未审核；40：未提交
		this.addField("UPDATED_DATE",OP_DATE);//记录更新时间
		this.addField("TIJIAO_DATE",OP_DATE);//提交时间
		this.addField("SHENHE_REN",OP_STRING);//审核人
		this.addField("SHENHE_JIEGUO",OP_STRING);//审核结果，1：审核通过；2：审核未通过
		this.addField("SHENHE_YIJIAN",OP_STRING);//审核详细意见
		this.addField("SHENHE_DATE",OP_DATE);//审核时间或入库更新时间 
		this.addField("JZ_Y",OP_STRING);//是否禁止进入
		this.addField("JZ_YUANYIN",OP_STRING);//禁止进入原因
		this.addField("JZ_SJFW",OP_STRING);//是否是上级发文的限制
		this.addField("JZ_DATE",OP_DATE);//禁止进入限期
		this.addField("ZHICHENG_UID",OP_STRING);//职称UID
		this.addField("BEGIN_DATE",OP_DATE);//劳动合同开始日期
		this.addField("SCORE",OP_STRING);//人员信用分值
		this.setFieldDateFormat("CREATED_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("UPDATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("BIRTHDAY","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("END_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("UPDATED_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("TIJIAO_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("SHENHE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("JZ_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("BEGIN_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("SG_PERSON_LIBRARY");

		this.bindFieldToSequence("SG_PERSON_UID", "SG_PERSON_UID");
		this.bindFieldToSequence("SG_PERSON_LIBRARY_UID", "SG_PERSON_LIBRARY_UID");
	}

	public void setSg_person_library_uid(String sg_person_library_uid){
		this.setInternal("SG_PERSON_LIBRARY_UID",sg_person_library_uid);
	}
	public String getSg_person_library_uid(){
		return (String)this.getInternal("SG_PERSON_LIBRARY_UID");
	}
	public void setZhuanye1(String zhuanye1){
		this.setInternal("ZHUANYE1",zhuanye1);
	}
	public String getZhuanye1(){
		return (String)this.getInternal("ZHUANYE1");
	}
	public void setZhuanye2(String zhuanye2){
		this.setInternal("ZHUANYE2",zhuanye2);
	}
	public String getZhuanye2(){
		return (String)this.getInternal("ZHUANYE2");
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
	public void setSg_company_uid(String sg_company_uid){
		this.setInternal("SG_COMPANY_UID",sg_company_uid);
	}
	public String getSg_company_uid(){
		return (String)this.getInternal("SG_COMPANY_UID");
	}
	public void setDenglu_code(String denglu_code){
		this.setInternal("DENGLU_CODE",denglu_code);
	}
	public String getDenglu_code(){
		return (String)this.getInternal("DENGLU_CODE");
	}
	public void setPwd(String pwd){
		this.setInternal("PWD",pwd);
	}
	public String getPwd(){
		return (String)this.getInternal("PWD");
	}
	public void setMima(String mima){
		this.setInternal("MIMA",mima);
	}
	public String getMima(){
		return (String)this.getInternal("MIMA");
	}
	public void setPerson_name(String person_name){
		this.setInternal("PERSON_NAME",person_name);
	}
	public String getPerson_name(){
		return (String)this.getInternal("PERSON_NAME");
	}
	public void setSex(String sex){
		this.setInternal("SEX",sex);
	}
	public String getSex(){
		return (String)this.getInternal("SEX");
	}
	public void setBirthday(Date birthday){
		this.setInternal("BIRTHDAY",birthday);
	}
	public Date getBirthday(){
		return (Date)this.getInternal("BIRTHDAY");
	}
	public void setShenfenzheng(String shenfenzheng){
		this.setInternal("SHENFENZHENG",shenfenzheng);
	}
	public String getShenfenzheng(){
		return (String)this.getInternal("SHENFENZHENG");
	}
	public void setPhoto(String photo){
		this.setInternal("PHOTO",photo);
	}
	public String getPhoto(){
		return (String)this.getInternal("PHOTO");
	}
	public void setNation(String nation){
		this.setInternal("NATION",nation);
	}
	public String getNation(){
		return (String)this.getInternal("NATION");
	}
	public void setPhone(String phone){
		this.setInternal("PHONE",phone);
	}
	public String getPhone(){
		return (String)this.getInternal("PHONE");
	}
	public void setEmail(String email){
		this.setInternal("EMAIL",email);
	}
	public String getEmail(){
		return (String)this.getInternal("EMAIL");
	}
	public void setXueli(String xueli){
		this.setInternal("XUELI",xueli);
	}
	public String getXueli(){
		return (String)this.getInternal("XUELI");
	}
	public void setCollege(String college){
		this.setInternal("COLLEGE",college);
	}
	public String getCollege(){
		return (String)this.getInternal("COLLEGE");
	}
	public void setZhicheng_code(String zhicheng_code){
		this.setInternal("ZHICHENG_CODE",zhicheng_code);
	}
	public String getZhicheng_code(){
		return (String)this.getInternal("ZHICHENG_CODE");
	}
	public void setJishu_y(String jishu_y){
		this.setInternal("JISHU_Y",jishu_y);
	}
	public String getJishu_y(){
		return (String)this.getInternal("JISHU_Y");
	}
	public void setFzr_y(String fzr_y){
		this.setInternal("FZR_Y",fzr_y);
	}
	public String getFzr_y(){
		return (String)this.getInternal("FZR_Y");
	}
	public void setEnd_date(Date end_date){
		this.setInternal("END_DATE",end_date);
	}
	public Date getEnd_date(){
		return (Date)this.getInternal("END_DATE");
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
	public void setUpdated_date(Date updated_date){
		this.setInternal("UPDATED_DATE",updated_date);
	}
	public Date getUpdated_date(){
		return (Date)this.getInternal("UPDATED_DATE");
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
	public void setShenhe_date(Date shenhe_date){
		this.setInternal("SHENHE_DATE",shenhe_date);
	}
	public Date getShenhe_date(){
		return (Date)this.getInternal("SHENHE_DATE");
	}
	public void setJz_y(String jz_y){
		this.setInternal("JZ_Y",jz_y);
	}
	public String getJz_y(){
		return (String)this.getInternal("JZ_Y");
	}
	public void setJz_yuanyin(String jz_yuanyin){
		this.setInternal("JZ_YUANYIN",jz_yuanyin);
	}
	public String getJz_yuanyin(){
		return (String)this.getInternal("JZ_YUANYIN");
	}
	public void setJz_sjfw(String jz_sjfw){
		this.setInternal("JZ_SJFW",jz_sjfw);
	}
	public String getJz_sjfw(){
		return (String)this.getInternal("JZ_SJFW");
	}
	public void setJz_date(Date jz_date){
		this.setInternal("JZ_DATE",jz_date);
	}
	public Date getJz_date(){
		return (Date)this.getInternal("JZ_DATE");
	}
	public void setZhicheng_uid(String zhicheng_uid){
		this.setInternal("ZHICHENG_UID",zhicheng_uid);
	}
	public String getZhicheng_uid(){
		return (String)this.getInternal("ZHICHENG_UID");
	}
	public void setBegin_date(Date begin_date){
		this.setInternal("BEGIN_DATE",begin_date);
	}
	public Date getBegin_date(){
		return (Date)this.getInternal("BEGIN_DATE");
	}
	public void setScore(String score){
		this.setInternal("SCORE",score);
	}
	public String getScore(){
		return (String)this.getInternal("SCORE");
	}
}