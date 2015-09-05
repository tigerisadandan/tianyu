package com.ccthanking.business.sgenter.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class SgScoreVO extends BaseVO{

	public SgScoreVO(){
		this.addField("SG_SCORE_UID",OP_STRING|this.TP_PK);//null
		this.addField("SG_COMPANY_UID",OP_STRING);//null
		this.addField("COMPANY_NAME",OP_STRING);//null
		this.addField("DENGLU_CODE",OP_STRING);//null
		this.addField("SCORE",OP_STRING);//null
		this.addField("BEGIN_DATE",OP_DATE);//null
		this.addField("END_DATE",OP_DATE);//null
		this.addField("CREATED_DATE",OP_DATE);//null
		this.addField("DESCRIPTION",OP_STRING);//null
		this.setFieldDateFormat("BEGIN_DATE","yyyy-MM-dd");
		this.setFieldDateFormat("END_DATE","yyyy-MM-dd");
		this.setFieldDateFormat("CREATED_DATE","yyyy-MM-dd");
		this.setVOTableName("SG_SCORE");
		
		this.bindFieldToSequence("SG_SCORE_UID", "SG_SCORE_UID");
	}

	public void setSg_score_uid(String sg_score_uid){
		this.setInternal("SG_SCORE_UID",sg_score_uid);
	}
	public String getSg_score_uid(){
		return (String)this.getInternal("SG_SCORE_UID");
	}
	public void setSg_company_uid(String sg_company_uid){
		this.setInternal("SG_COMPANY_UID",sg_company_uid);
	}
	public String getSg_company_uid(){
		return (String)this.getInternal("SG_COMPANY_UID");
	}
	public void setCompany_name(String company_name){
		this.setInternal("COMPANY_NAME",company_name);
	}
	public String getCompany_name(){
		return (String)this.getInternal("COMPANY_NAME");
	}
	public void setDenglu_code(String denglu_code){
		this.setInternal("DENGLU_CODE",denglu_code);
	}
	public String getDenglu_code(){
		return (String)this.getInternal("DENGLU_CODE");
	}
	public void setScore(String score){
		this.setInternal("SCORE",score);
	}
	public String getScore(){
		return (String)this.getInternal("SCORE");
	}
	public void setBegin_date(Date begin_date){
		this.setInternal("BEGIN_DATE",begin_date);
	}
	public Date getBegin_date(){
		return (Date)this.getInternal("BEGIN_DATE");
	}
	public void setEnd_date(Date end_date){
		this.setInternal("END_DATE",end_date);
	}
	public Date getEnd_date(){
		return (Date)this.getInternal("END_DATE");
	}
	public void setCreated_date(Date created_date){
		this.setInternal("CREATED_DATE",created_date);
	}
	public Date getCreated_date(){
		return (Date)this.getInternal("CREATED_DATE");
	}
	public void setDescription(String description){
		this.setInternal("DESCRIPTION",description);
	}
	public String getDescription(){
		return (String)this.getInternal("DESCRIPTION");
	}
}