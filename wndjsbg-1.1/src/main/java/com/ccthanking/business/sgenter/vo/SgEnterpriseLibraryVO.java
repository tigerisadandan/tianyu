package com.ccthanking.business.sgenter.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class SgEnterpriseLibraryVO extends BaseVO{

	public SgEnterpriseLibraryVO(){
		this.addField("SG_ENTERPRISE_LIBRARY_UID",OP_STRING|this.TP_PK);//施工企业增长UID
		this.addField("SG_COMPANY_UID",OP_STRING);//施工企业编号
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
		this.addField("DENGLU_CODE",OP_STRING);//登录代码
		this.addField("PWD",OP_STRING);//登录密码（MD5加密）
		this.addField("MIMA",OP_STRING);//登录密码（未加密）
		this.addField("COMPANY_CODE",OP_STRING);//企业组织机构代码
		this.addField("COMPANY_NAME",OP_STRING);//企业全称
		this.addField("COMPANY_TYPE",OP_STRING);//经济类型，有限责任公司等
		this.addField("WAIDI_Y",OP_STRING);//是否为外地企业
		this.addField("ZHIZHAO",OP_STRING);//营业执照注册号
		this.addField("ZHIZHAO_VALID",OP_DATE);//营业执照有效期
		this.addField("SHUIWU",OP_STRING);//税务登记号
		this.addField("BANK",OP_STRING);//开户银行
		this.addField("BANK_ACCOUNT",OP_STRING);//开户行账号
		this.addField("ZHUCE_ZIJIN",OP_STRING);//注册资金（万元）
		this.addField("ANQUAN_CODE",OP_STRING);//安全生产许可证编号
		this.addField("ANQUAN_VALID",OP_DATE);//安全生产许可证有效期
		this.addField("XINYONG_CODE",OP_STRING);//信用手册编号
		this.addField("XINYONG_VALID",OP_DATE);//信用手册有效期
		this.addField("SG_ZIZHI_UID",OP_STRING);//主项资质
		this.addField("ZHU_DENGJI",OP_STRING);//主项资质等级
		this.addField("ZHENGSHU_CODE",OP_STRING);//资质证书编号
		this.addField("ZIZHI_DATE",OP_DATE);//取得资质日期
		this.addField("ADDRESS",OP_STRING);//公司地址
		this.addField("POSTCODE",OP_STRING);//邮政编码
		this.addField("PHONE",OP_STRING);//公司电话
		this.addField("FAX",OP_STRING);//公司传真
		this.addField("URL",OP_STRING);//公司主页
		this.addField("FAREN",OP_STRING);//法人代表
		this.addField("FAREN_ZHICHENG",OP_STRING);//法人职称
		this.addField("FAREN_MOBILE",OP_STRING);//法人代表移动电话
		this.addField("ZHUXI_ADDRESS",OP_STRING);//驻锡办公地址
		this.addField("ZHUXI_FZR",OP_STRING);//驻锡负责人
		this.addField("ZHUXI_MOBILE",OP_STRING);//驻锡负责人联系电话
		this.addField("LIANXIREN",OP_STRING);//联系人
		this.addField("LIANXIREN_MOBILE",OP_STRING);//联系人电话 
		this.addField("LIANXIREN_MAIL",OP_STRING);//联系人Email
		this.addField("DESCRIPTION",OP_STRING);//null
		this.addField("STATUS",OP_STRING);//状态  1：入库（最终数据）；10：审核通过；20：审核未通过；30：已提交未审核；40：未提交
		this.addField("UPDATED_DATE",OP_DATE);//记录更新时间
		this.addField("TIJIAO_DATE",OP_DATE);//提交时间
		this.addField("SHENHE_REN",OP_STRING);//审核人
		this.addField("SHENHE_JIEGUO",OP_STRING);//审核结果，1：审核通过；2：审核未通过
		this.addField("SHENHE_YIJIAN",OP_STRING);//审核详细意见
		this.addField("SHENHE_DATE",OP_DATE);//审核时间或入库更新时间 
		this.addField("VALID_DATE",OP_DATE);//有效日期
		this.addField("JZ_Y",OP_STRING);//是否禁止进入
		this.addField("JZ_YUANYIN",OP_STRING);//禁止进入原因
		this.addField("JZ_SJFW",OP_STRING);//是否是上级发文的限制
		this.addField("QUEREN_STATUS",OP_STRING);//是否确认
		this.addField("QUEREN_REN",OP_STRING);//确认人
		this.addField("QUEREN_DATE",OP_DATE);//确认时间
		this.addField("SCORE",OP_STRING);//企业信用分值
		this.setFieldDateFormat("CREATED_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("UPDATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("ZHIZHAO_VALID","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("ANQUAN_VALID","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("XINYONG_VALID","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("ZIZHI_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("UPDATED_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("TIJIAO_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("SHENHE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("VALID_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("QUEREN_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("SG_ENTERPRISE_LIBRARY");

		this.bindFieldToSequence("SG_ENTERPRISE_LIBRARY_UID", "SG_ENTERPRISE_LIBRARY_UID");
		this.bindFieldToSequence("SG_COMPANY_UID", "SG_COMPANY_UID");
	}

	public void setSg_enterprise_library_uid(String sg_enterprise_library_uid){
		this.setInternal("SG_ENTERPRISE_LIBRARY_UID",sg_enterprise_library_uid);
	}
	public String getSg_enterprise_library_uid(){
		return (String)this.getInternal("SG_ENTERPRISE_LIBRARY_UID");
	}
	public void setSg_company_uid(String sg_company_uid){
		this.setInternal("SG_COMPANY_UID",sg_company_uid);
	}
	public String getSg_company_uid(){
		return (String)this.getInternal("SG_COMPANY_UID");
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
	public void setCompany_code(String company_code){
		this.setInternal("COMPANY_CODE",company_code);
	}
	public String getCompany_code(){
		return (String)this.getInternal("COMPANY_CODE");
	}
	public void setCompany_name(String company_name){
		this.setInternal("COMPANY_NAME",company_name);
	}
	public String getCompany_name(){
		return (String)this.getInternal("COMPANY_NAME");
	}
	public void setCompany_type(String company_type){
		this.setInternal("COMPANY_TYPE",company_type);
	}
	public String getCompany_type(){
		return (String)this.getInternal("COMPANY_TYPE");
	}
	public void setWaidi_y(String waidi_y){
		this.setInternal("WAIDI_Y",waidi_y);
	}
	public String getWaidi_y(){
		return (String)this.getInternal("WAIDI_Y");
	}
	public void setZhizhao(String zhizhao){
		this.setInternal("ZHIZHAO",zhizhao);
	}
	public String getZhizhao(){
		return (String)this.getInternal("ZHIZHAO");
	}
	public void setZhizhao_valid(Date zhizhao_valid){
		this.setInternal("ZHIZHAO_VALID",zhizhao_valid);
	}
	public Date getZhizhao_valid(){
		return (Date)this.getInternal("ZHIZHAO_VALID");
	}
	public void setShuiwu(String shuiwu){
		this.setInternal("SHUIWU",shuiwu);
	}
	public String getShuiwu(){
		return (String)this.getInternal("SHUIWU");
	}
	public void setBank(String bank){
		this.setInternal("BANK",bank);
	}
	public String getBank(){
		return (String)this.getInternal("BANK");
	}
	public void setBank_account(String bank_account){
		this.setInternal("BANK_ACCOUNT",bank_account);
	}
	public String getBank_account(){
		return (String)this.getInternal("BANK_ACCOUNT");
	}
	public void setZhuce_zijin(String zhuce_zijin){
		this.setInternal("ZHUCE_ZIJIN",zhuce_zijin);
	}
	public String getZhuce_zijin(){
		return (String)this.getInternal("ZHUCE_ZIJIN");
	}
	public void setAnquan_code(String anquan_code){
		this.setInternal("ANQUAN_CODE",anquan_code);
	}
	public String getAnquan_code(){
		return (String)this.getInternal("ANQUAN_CODE");
	}
	public void setAnquan_valid(Date anquan_valid){
		this.setInternal("ANQUAN_VALID",anquan_valid);
	}
	public Date getAnquan_valid(){
		return (Date)this.getInternal("ANQUAN_VALID");
	}
	public void setXinyong_code(String xinyong_code){
		this.setInternal("XINYONG_CODE",xinyong_code);
	}
	public String getXinyong_code(){
		return (String)this.getInternal("XINYONG_CODE");
	}
	public void setXinyong_valid(Date xinyong_valid){
		this.setInternal("XINYONG_VALID",xinyong_valid);
	}
	public Date getXinyong_valid(){
		return (Date)this.getInternal("XINYONG_VALID");
	}
	public void setSg_zizhi_uid(String sg_zizhi_uid){
		this.setInternal("SG_ZIZHI_UID",sg_zizhi_uid);
	}
	public String getSg_zizhi_uid(){
		return (String)this.getInternal("SG_ZIZHI_UID");
	}
	public void setZhu_dengji(String zhu_dengji){
		this.setInternal("ZHU_DENGJI",zhu_dengji);
	}
	public String getZhu_dengji(){
		return (String)this.getInternal("ZHU_DENGJI");
	}
	public void setZhengshu_code(String zhengshu_code){
		this.setInternal("ZHENGSHU_CODE",zhengshu_code);
	}
	public String getZhengshu_code(){
		return (String)this.getInternal("ZHENGSHU_CODE");
	}
	public void setZizhi_date(Date zizhi_date){
		this.setInternal("ZIZHI_DATE",zizhi_date);
	}
	public Date getZizhi_date(){
		return (Date)this.getInternal("ZIZHI_DATE");
	}
	public void setAddress(String address){
		this.setInternal("ADDRESS",address);
	}
	public String getAddress(){
		return (String)this.getInternal("ADDRESS");
	}
	public void setPostcode(String postcode){
		this.setInternal("POSTCODE",postcode);
	}
	public String getPostcode(){
		return (String)this.getInternal("POSTCODE");
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
	public void setUrl(String url){
		this.setInternal("URL",url);
	}
	public String getUrl(){
		return (String)this.getInternal("URL");
	}
	public void setFaren(String faren){
		this.setInternal("FAREN",faren);
	}
	public String getFaren(){
		return (String)this.getInternal("FAREN");
	}
	public void setFaren_zhicheng(String faren_zhicheng){
		this.setInternal("FAREN_ZHICHENG",faren_zhicheng);
	}
	public String getFaren_zhicheng(){
		return (String)this.getInternal("FAREN_ZHICHENG");
	}
	public void setFaren_mobile(String faren_mobile){
		this.setInternal("FAREN_MOBILE",faren_mobile);
	}
	public String getFaren_mobile(){
		return (String)this.getInternal("FAREN_MOBILE");
	}
	public void setZhuxi_address(String zhuxi_address){
		this.setInternal("ZHUXI_ADDRESS",zhuxi_address);
	}
	public String getZhuxi_address(){
		return (String)this.getInternal("ZHUXI_ADDRESS");
	}
	public void setZhuxi_fzr(String zhuxi_fzr){
		this.setInternal("ZHUXI_FZR",zhuxi_fzr);
	}
	public String getZhuxi_fzr(){
		return (String)this.getInternal("ZHUXI_FZR");
	}
	public void setZhuxi_mobile(String zhuxi_mobile){
		this.setInternal("ZHUXI_MOBILE",zhuxi_mobile);
	}
	public String getZhuxi_mobile(){
		return (String)this.getInternal("ZHUXI_MOBILE");
	}
	public void setLianxiren(String lianxiren){
		this.setInternal("LIANXIREN",lianxiren);
	}
	public String getLianxiren(){
		return (String)this.getInternal("LIANXIREN");
	}
	public void setLianxiren_mobile(String lianxiren_mobile){
		this.setInternal("LIANXIREN_MOBILE",lianxiren_mobile);
	}
	public String getLianxiren_mobile(){
		return (String)this.getInternal("LIANXIREN_MOBILE");
	}
	public void setLianxiren_mail(String lianxiren_mail){
		this.setInternal("LIANXIREN_MAIL",lianxiren_mail);
	}
	public String getLianxiren_mail(){
		return (String)this.getInternal("LIANXIREN_MAIL");
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
	public void setValid_date(Date valid_date){
		this.setInternal("VALID_DATE",valid_date);
	}
	public Date getValid_date(){
		return (Date)this.getInternal("VALID_DATE");
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
	public void setQueren_status(String queren_status){
		this.setInternal("QUEREN_STATUS",queren_status);
	}
	public String getQueren_status(){
		return (String)this.getInternal("QUEREN_STATUS");
	}
	public void setQueren_ren(String queren_ren){
		this.setInternal("QUEREN_REN",queren_ren);
	}
	public String getQueren_ren(){
		return (String)this.getInternal("QUEREN_REN");
	}
	public void setQueren_date(Date queren_date){
		this.setInternal("QUEREN_DATE",queren_date);
	}
	public Date getQueren_date(){
		return (Date)this.getInternal("QUEREN_DATE");
	}
	public void setScore(String score){
		this.setInternal("SCORE",score);
	}
	public String getScore(){
		return (String)this.getInternal("SCORE");
	}
}