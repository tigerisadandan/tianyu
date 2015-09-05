package com.ccthanking.business.sgenter.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class SgScoreDetailVO extends BaseVO{

	public SgScoreDetailVO(){
		this.addField("SG_SCORE_DETAIL_UID",OP_STRING|this.TP_PK);//null
		this.addField("SG_SCORE_UID",OP_STRING);//null
		this.addField("SCORE_TYPE",OP_STRING);//信用分类型：JB－基本分；GC－工程分；JL－奖励分；KH－日常考核分
		this.addField("CR_LEVEL",OP_STRING);//奖励分的奖项级别
		this.addField("SERIAL_NO",OP_STRING);//同一分值类型下的顺序号
		this.addField("SCORE_NAME",OP_STRING);//得分内容名称，如项目名称、将项名称、安全许可证号等
		this.addField("VALID_DATE",OP_DATE);//得分内容有效期
		this.addField("SCORE",OP_STRING);//得分分值
		this.addField("CREATED_DATE",OP_DATE);//统计时间
		this.addField("DESCRIPTION",OP_STRING);//备注
		this.setFieldDateFormat("VALID_DATE","yyyy-MM-dd");
		this.setFieldDateFormat("CREATED_DATE","yyyy-MM-dd");
		this.setVOTableName("SG_SCORE_DETAIL");
		
		this.bindFieldToSequence("SG_SCORE_DETAIL_UID", "SG_SCORE_DETAIL_UID");
	}

	public void setSg_score_detail_uid(String sg_score_detail_uid){
		this.setInternal("SG_SCORE_DETAIL_UID",sg_score_detail_uid);
	}
	public String getSg_score_detail_uid(){
		return (String)this.getInternal("SG_SCORE_DETAIL_UID");
	}
	public void setSg_score_uid(String sg_score_uid){
		this.setInternal("SG_SCORE_UID",sg_score_uid);
	}
	public String getSg_score_uid(){
		return (String)this.getInternal("SG_SCORE_UID");
	}
	public void setScore_type(String score_type){
		this.setInternal("SCORE_TYPE",score_type);
	}
	public String getScore_type(){
		return (String)this.getInternal("SCORE_TYPE");
	}
	public void setCr_level(String cr_level){
		this.setInternal("CR_LEVEL",cr_level);
	}
	public String getCr_level(){
		return (String)this.getInternal("CR_LEVEL");
	}
	public void setSerial_no(String serial_no){
		this.setInternal("SERIAL_NO",serial_no);
	}
	public String getSerial_no(){
		return (String)this.getInternal("SERIAL_NO");
	}
	public void setScore_name(String score_name){
		this.setInternal("SCORE_NAME",score_name);
	}
	public String getScore_name(){
		return (String)this.getInternal("SCORE_NAME");
	}
	public void setValid_date(Date valid_date){
		this.setInternal("VALID_DATE",valid_date);
	}
	public Date getValid_date(){
		return (Date)this.getInternal("VALID_DATE");
	}
	public void setScore(String score){
		this.setInternal("SCORE",score);
	}
	public String getScore(){
		return (String)this.getInternal("SCORE");
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