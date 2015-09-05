/*
 * PageController.java  v1.00  2014-4-26
 * Peoject	wndjssg
 * Copyright (c) 2014 Wisdragon
 *
 * Filename	:	PageController.java  v1.00 2014-4-26
 * Project	: 	wndjssg
 * Copyight	:	Copyright (c) 2014 Wisdragon
 */
package com.ccthanking.business.commons;

import java.io.File;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ccthanking.business.spxx.service.BuSpYwxxService;
import com.ccthanking.business.spxx.vo.BuSpYwxxVO;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.coreapp.orgmanage.UserManager;
import com.ccthanking.framework.model.requestJson;
import com.copj.modules.utils.exception.DaoException;

/**
 * 页面跳转.<br>
 * 
 * 屏蔽Url 统一以管理.
 * 
 * @author <a href="mailto:genliang.jiang@wisdragon.com">蒋根亮</a>
 * @version v1.00
 * @since 1.00 2014-4-26
 * 
 */
@Controller
public class PageController {
	
	@Autowired
	private BuSpYwxxService buSpYwxxService;

	@RequestMapping(value = "/regrsshow")
	public String regrsshow() {
		return Constants.getString(Constants.PAGE_SG_REG_RS_SHOW, "jsp/business/commons/showCompanyList");
	}

	@RequestMapping(value = "/sgentshengm")
	public String sgentshengm() {
		return Constants.getString("Constants.PAGE_SG_ENTER_REG_BEFORE", "jsp/business/sg/befroe-register");
	}

	@RequestMapping(value = "/sgentreg")
	public String sgentreg() {
		return Constants.getString(Constants.PAGE_SG_ENTER_REG, "jsp/business/sg/sg-enterPrise-add");
	}

	@RequestMapping(value = "/sgentedit/{oldId}/{id}")
	public ModelAndView sgentedit(@PathVariable String oldId,@PathVariable String id) {
		ModelAndView m = new ModelAndView();
		m.setViewName(Constants.getString(Constants.PAGE_SG_ENTER_EDIT, "jsp/business/sg/sg-enterPrise-edit"));
		m.addObject("id", id);
		m.addObject("oldId", oldId);
		return m;
	}

	// 施工人员添加页面
	@RequestMapping(value = "/sgperson")
	public String sgpersonadd() {
		return Constants.getString(Constants.PAGE_SG_PERSON_ADD, "jsp/business/person_manager/person_add");
	}

	@RequestMapping(value = "/sgpersonaddone/{id}")
	public ModelAndView sgpersonaddone(@PathVariable String id) {
		ModelAndView m = new ModelAndView();
		m.setViewName(Constants.getString(Constants.PAGE_SG_PERSON_ADD_ONE, "jsp/business/person_manager/person_add_one"));
		m.addObject("id", id);
		return m;

	}
	@RequestMapping(value = "/sgperson/{id}")
	public ModelAndView sgpersona(@PathVariable String id) {
		ModelAndView m = new ModelAndView();
		m.setViewName(Constants.getString(Constants.PAGE_SG_PERSON_ADD, "jsp/business/person_manager/person_add"));
		m.addObject("id", id);
		return m;

	}
	@RequestMapping(value = "/sgperson/{id}/{t_id}")
	public ModelAndView sgpersonadd4(@PathVariable String id,@PathVariable String t_id) {
		ModelAndView m = new ModelAndView();
		m.setViewName(Constants.getString(Constants.PAGE_SG_PERSON_ADD, "jsp/business/person_manager/person_add"));
		m.addObject("id", id);
		m.addObject("t_id", t_id);
		return m;

	}
	@RequestMapping(value = "/sgperson/{id}/{sta}/{ty}")
	public ModelAndView sgpersonadd3(@PathVariable String id, @PathVariable String sta, @PathVariable String ty) {
		ModelAndView m = new ModelAndView();
		m.setViewName(Constants.getString(Constants.PAGE_SG_PERSON_ADD, "jsp/business/person_manager/person_add"));
		m.addObject("id", id);
		m.addObject("sta", sta);
		m.addObject("ty", ty);
		return m;

	}

	// 施工人员详细信息页面
	@RequestMapping(value = "/sgpersond/{id}")
	public ModelAndView sgpersondetail(@PathVariable String id) {
		ModelAndView m = new ModelAndView();
		m.setViewName(Constants.getString(Constants.PAGE_SG_PERSON_DETAIL, "jsp/business/person_manager/person_detail_message"));
		m.addObject("id", id);
		return m;
	}
	// 入库人员可以选择锁定
	@RequestMapping(value = "/suoding/{id}")
	public ModelAndView suoding(@PathVariable String id) {
		ModelAndView m = new ModelAndView();
		m.setViewName(Constants.getString(Constants.PAGE_SG_PERSON_SUODING, "jsp/business/person_manager/suoding"));
		m.addObject("id", id);
		return m;
	}
	
	// 监理入库人员可以选择锁定
	@RequestMapping(value = "/jlsuoding/{id}")
	public ModelAndView jlsuoding(@PathVariable String id) {
		ModelAndView m = new ModelAndView();
		m.setViewName(Constants.getString(Constants.PAGE_JL_PERSON_SUODING, "jsp/business/jl/jlsuoding"));
		m.addObject("id", id);
		return m;
	}

	// 附件显示页面
	@RequestMapping(value = "/fujianShow/{targetId}/{business_type}")
	public ModelAndView fujianShow(@PathVariable String targetId,@PathVariable String business_type) {
		ModelAndView m = new ModelAndView();
		m.setViewName(Constants.getString(Constants.DATA_FUJIAN_SHOW, "jsp/business/commons/showFiles"));
		m.addObject("business_type", business_type);
		m.addObject("targetId", targetId);
		return m;
	}

	@RequestMapping(value = "/sgmessage/{uid}")
	public ModelAndView sgmessage(@PathVariable String uid) {
		ModelAndView m = new ModelAndView();
		m.setViewName(Constants.getString(Constants.PAGE_SGENTERPRISE_SHENPIINFO, "jsp/business/commons/showMessage"));
		m.addObject("uid", uid);
		return m;
	}

	@RequestMapping(value = "/sysdeletefile")
	public void sysdeletefile(HttpServletRequest request) {
		request.getSession().removeAttribute(Constants.FILE_KEY);
	}

	@RequestMapping( value = "/sgenterapp")
	public String sgenterapp() {
		return Constants.getString(Constants.PAGE_SGENTERPRISE_SHENPI, "jsp/business/sg/sg-enterPrise-approval");
	}

	@RequestMapping(value = "/sgbbupdate/{id}")
	public ModelAndView sgbbupdate(@PathVariable String id) {
		ModelAndView m = new ModelAndView();
		m.setViewName(Constants.getString(Constants.PAGE_SGBBXX_EDIT, "jsp/business/sgbb/sgbb-edit"));
		m.addObject("id", id);
		return m;
	}

	@RequestMapping(value = "/sgbbview/{id}")
	public ModelAndView sgbbview(@PathVariable String id) {
		ModelAndView m = new ModelAndView();
		m.setViewName(Constants.getString(Constants.PAGE_SGBBXX_VIEW, "jsp/business/sgbb/sgbb-view"));
		m.addObject("id", id);
		return m;
	}
	
	@RequestMapping(value = "/sgbbonsp")
	public String sgbbonsp() {
		return Constants.getString(Constants.PAGE_SG_ENTER_ONSP, "jsp/business/sg-moni/sg-enterPrise-view");
		
	}
	@RequestMapping(value = "/sgentview/{id}")
	public ModelAndView sgentview(@PathVariable String id) {
		ModelAndView m = new ModelAndView();
		m.setViewName(Constants.getString("PAGE_SG_ENTER_VIEW", "jsp/business/sg/sg-enterPrise-add"));
		m.addObject("id", id);
		return m;
	}
	
	@RequestMapping(value = "/sgenteditrk/{id}")
	public ModelAndView sgenteditrk(@PathVariable String id) {
		ModelAndView m = new ModelAndView();
		m.setViewName(Constants.getString("PAGE_SG_ENTER_EDIT_RK", "jsp/business/sg/sg-enterPrise-edit-rk"));
		m.addObject("id", id);
		return m;
	}
	
	@RequestMapping(value = "/sgenteditqueren/{id}")
	public ModelAndView sgenteditqueren(@PathVariable String id) {
		ModelAndView m = new ModelAndView();
		m.setViewName(Constants.getString("PAGE_SG_ENTER_EDIT_QUEREN", "jsp/business/sg/sg-enterPrise-queren"));
		m.addObject("id", id);
		return m;
	}
	
	@RequestMapping(value = "/sgentscore/{id}")
	public ModelAndView sgentscore(@PathVariable String id) {
		ModelAndView m = new ModelAndView();
		m.setViewName(Constants.getString("PAGE_SG_ENTER_SCORE", "jsp/business/sg/sg-score-view"));
		m.addObject("id", id);
		return m;
	}
	
	@RequestMapping(value = "/sgouter")
	public String sgouter() {
		return Constants.getString("PAGE_SG_OUTER_TB", "jsp/business/sg/outer-index-rk");
		
	}
	
	@RequestMapping(value = "/sgouterlist")
	public String sgouterlist() {
		return Constants.getString("PAGE_SG_OUTER_LIST", "jsp/business/sg/outer-index-rk");
		
	}
	

	@RequestMapping("/ywxxAdd/{puid}")
	public ModelAndView ywxxAdd(@PathVariable String puid) {
		ModelAndView m = new ModelAndView();
		m.setViewName(Constants.getString(Constants.PAGE_SPYW_YWXX_ADD, "jsp/business/spxx/spxx-add"));
		m.addObject("puid", puid);
		return m;
	}
	
	@RequestMapping("/ywxxView/{zlc}/{uid}")
	public ModelAndView ywxxView(@PathVariable String zlc,@PathVariable String uid) {
		ModelAndView m = new ModelAndView();
		m.setViewName(Constants.getString(Constants.PAGE_SPYW_YWXX_VIEW, "jsp/business/spxx/spxx-view"));
		m.addObject("uid", uid);
		m.addObject("zlc", zlc);
		return m;
	}
	
	@RequestMapping("/ywxLayer")
	public String ywxLayer() {
		return Constants.getString(Constants.PAGE_SPYW_YWXX_LAYER, "jsp/business/spxx/spxx-layer");
		
	}
	
	
	@RequestMapping("/ywlzSpIndex/{ywid}")
	public ModelAndView ywsqIndex(@PathVariable String ywid) {
		ModelAndView m = new ModelAndView();
		BuSpYwxxVO ywxxVO = buSpYwxxService.findById(ywid);
		if(ywxxVO.getSpywlx().equals("SG")){
			m.setViewName("jsp/business/ywlz/bu-sp-ywlzsp-index-sgsx");
		}else{
    		m.setViewName(Constants.getString(Constants.PAGE_SPYW_YWLZ_INDEX, "jsp/business/ywlz/bu-sp-ywlzsp-index"));
		}
		m.addObject("ywid", ywid);
		return m;	
	}
	
	
	//排水施工图
	@RequestMapping("/psSgtSqb/{optype}")
	public ModelAndView psSgtSqb(@PathVariable String optype) {
		ModelAndView m = new ModelAndView();
		String[] params = optype.split(":");
		if ("insert".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_PSSGT_ADD, "jsp/business/sp/psSgtscsq-add"));
		}else if ("update".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_PSSGT_ADD, "jsp/business/sp/psSgtscsq-add"));
			m.addObject("id", params[1]);
		}else if ("detail".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_PSSGT_VIEW, "jsp/business/sp/psSgtscsq-view"));
			m.addObject("id", params[1]);
		}
		return m;
		
	}
	//新型墙体材料专项基金返退申请
	@RequestMapping("/XxqtClzxjjFtsq/{optype}")
	public ModelAndView XxqtClzxjjFtsq(@PathVariable String optype) {
		ModelAndView m = new ModelAndView();
		String[] params = optype.split(":");
		if ("insert".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_XXQTCLZXJJFTSQ_ADD, "jsp/business/sp/bu_spyw_xqxxqtclzxjjftsq-add"));
		}else if ("update".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_XXQTCLZXJJFTSQ_ADD, "jsp/business/sp/bu_spyw_xqxxqtclzxjjftsq-add"));
			m.addObject("id", params[1]);
		}else if ("detail".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_XXQTCLZXJJFTSQ_VIEW, "jsp/business/sp/bu_spyw_xqxxqtclzxjjftsq-view"));
			m.addObject("id", params[1]);
		}
		return m;
		
	}
	//排水方案预审
	@RequestMapping("/psFaysSqb/{optype}")
	public ModelAndView psFaysSqb(@PathVariable String optype) {
		ModelAndView m = new ModelAndView();
		String[] params = optype.split(":");
		if ("insert".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_PSFAYS_ADD, "jsp/business/sp/psFayssq-add"));
		}else if ("update".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_PSFAYS_ADD, "jsp/business/sp/psFayssq-add"));
			m.addObject("id", params[1]);
		}else if ("detail".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_PSFAYS_VIEW, "jsp/business/sp/psFayssq-view"));
			m.addObject("id", params[1]);
		}
		return m;
		
	}
	//建设工程施工许可
	@RequestMapping("/jsGcSgXk/{optype}")
	public ModelAndView jsGcSgXk(@PathVariable String optype) {
		ModelAndView m = new ModelAndView();
		String[] params = optype.split(":");
		if ("insert".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_JSGCSGXK_ADD, "jsp/business/sp/sgxkzSqb-add"));
		}else if ("update".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_JSGCSGXK_ADD, "jsp/business/sp/sgxkzSqb-add"));
			m.addObject("id", params[1]);
		}else if ("detail".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_JSGCSGXK_VIEW, "jsp/business/sp/sgxkzSqb-view"));
			m.addObject("id", params[1]);
		}
		return m;
		
	}
	//商品房交付使用验收申请
	@RequestMapping("/spfJfSyysSq/{optype}")
	public ModelAndView spfJfSyysSq(@PathVariable String optype) {
		ModelAndView m = new ModelAndView();
		String[] params = optype.split(":");
		if ("insert".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_SPFJFSYYSSQ_ADD, "jsp/business/sp/spfJfsyJgysSq-add"));
		}else if ("update".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_SPFJFSYYSSQ_ADD, "jsp/business/sp/spfJfsyJgysSq-add"));
			m.addObject("id", params[1]);
		}else if ("detail".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_SPFJFSYYSSQ_VIEW, "jsp/business/sp/spfJfsyJgysSq-view"));
			m.addObject("id", params[1]);
		}
		return m;
		
	}
	//商品房交付使用竣工验收项目情况表SPFJFSYJGYSXMQK
	@RequestMapping("/spfJfSyJgysXmqk/{optype}")
	public ModelAndView spfJfSyJgysXmqk(@PathVariable String optype) {
		ModelAndView m = new ModelAndView();
		String[] params = optype.split(":");
		if ("insert".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_SPFJFSYJGYSXMQK_ADD, "jsp/business/sp/bu_spyw_spfjfsyjgysxmqk-add"));
		}else if ("update".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_SPFJFSYJGYSXMQK_ADD, "jsp/business/sp/bu_spyw_spfjfsyjgysxmqk-add"));
			m.addObject("id", params[1]);
		}else if ("detail".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_SPFJFSYJGYSXMQK_VIEW, "jsp/business/sp/bu_spyw_spfjfsyjgysxmqk-view"));
			m.addObject("id", params[1]);
		}
		return m;
		
	}
	//雨污水入网申请
	@RequestMapping("/ywsRwsq/{optype}")
	public ModelAndView ywsRwsq(@PathVariable String optype) {
		ModelAndView m = new ModelAndView();
		String[] params = optype.split(":");
		if ("insert".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_YWSRWSQ_ADD, "jsp/business/sp/ywsRwsq-add"));
		}else if ("update".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_YWSRWSQ_ADD, "jsp/business/sp/ywsRwsq-add"));
			m.addObject("id", params[1]);
		}else if ("detail".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_YWSRWSQ_VIEW, "jsp/business/sp/ywsRwsq-view"));
			m.addObject("id", params[1]);
		}
		return m;
		
	}
	//燃气供应许可证
	@RequestMapping("/rqGyXkz/{optype}")
	public ModelAndView rqGyXkz(@PathVariable String optype) {
		ModelAndView m = new ModelAndView();
		String[] params = optype.split(":");
		if ("insert".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_RQGYXKZ_SQ_ADD, "jsp/business/sp/bu_spyw_rqgyxkz_sq-add"));
		}else if ("update".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_RQGYXKZ_SQ_ADD, "jsp/business/sp/bu_spyw_rqgyxkz_sq-add"));
			m.addObject("id", params[1]);
		}else if ("detail".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_RQGYXKZ_SQ_VIEW, "jsp/business/sp/bu_spyw_rqgyxkz_sq-view"));
			m.addObject("id", params[1]);
		}
		return m;
		
	}
	//施工企业业绩核查事项
	@RequestMapping("/sgQyYjhcsx/{optype}")
	public ModelAndView sgQyYjhcsx(@PathVariable String optype) {
		ModelAndView m = new ModelAndView();
		String[] params = optype.split(":");
		if ("insert".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_SGQYYJHCSX_ADD, "jsp/business/sp/bu_spyw_sgqyyjhcsx_qyyjhchz-add"));
		}else if ("update".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_SGQYYJHCSX_ADD, "jsp/business/sp/bu_spyw_sgqyyjhcsx_qyyjhchz-add"));
			m.addObject("id", params[1]);
		}else if ("detail".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_SGQYYJHCSX_VIEW, "jsp/business/sp/bu_spyw_sgqyyjhcsx_qyyjhchz-view"));
			m.addObject("id", params[1]);
		}
		return m;
		
	}
	//建设项目环境影响登记表告知承诺备案-工业类
	@RequestMapping("/gzcnbaGy/{optype}")
	public ModelAndView gzcnbaGy(@PathVariable String optype) {
		ModelAndView m = new ModelAndView();
		String[] params = optype.split(":");
		if ("insert".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_GZCNBA_GY_ADD, "jsp/business/sp/hjyxDjgzCns-add"));
		}else if ("update".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_GZCNBA_GY_ADD, "jsp/business/sp/hjyxDjgzCns-add"));
			m.addObject("id", params[1]);
		}else if ("detail".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_GZCNBA_GY_VIEW, "jsp/business/sp/hjyxDjgzCns-view"));
			m.addObject("id", params[1]);
		}
		return m;
		
	}
	//建设项目环境影响登记表告知承诺备案-区域开发及其他类
	@RequestMapping("/gzcnbaQykf/{optype}")
	public ModelAndView gzcnbaQykf(@PathVariable String optype) {
		ModelAndView m = new ModelAndView();
		String[] params = optype.split(":");
		if ("insert".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_GZCNBA_QYKF_ADD, "jsp/business/sp/hjyxDjgzCns2-add"));
		}else if ("update".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_GZCNBA_QYKF_ADD, "jsp/business/sp/hjyxDjgzCns2-add"));
			m.addObject("id", params[1]);
		}else if ("detail".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_GZCNBA_QYKF_VIEW, "jsp/business/sp/hjyxDjgzCns2-view"));
			m.addObject("id", params[1]);
		}
		return m;
		
	}
	//建设项目环境影响登记表告知承诺备案-饮食娱乐服务类
	@RequestMapping("/gzcnbaYsyl/{optype}")
	public ModelAndView gzcnbaYsyl(@PathVariable String optype) {
		ModelAndView m = new ModelAndView();
		String[] params = optype.split(":");
		if ("insert".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_GZCNBA_YSYL_ADD, "jsp/business/sp/hjyxDjgzCns3-add"));
		}else if ("update".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_GZCNBA_YSYL_ADD, "jsp/business/sp/hjyxDjgzCns3-add"));
			m.addObject("id", params[1]);
		}else if ("detail".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_GZCNBA_YSYL_VIEW, "jsp/business/sp/hjyxDjgzCns3-view"));
			m.addObject("id", params[1]);
		}
		return m;
		
	}
	
	//建设项目环境影响评价报告表（书）审批-工业类
	@RequestMapping("/hjyxpjGy/{optype}")
	public ModelAndView hjyxpjGy(@PathVariable String optype) {
		ModelAndView m = new ModelAndView();
		String[] params = optype.split(":");
		if ("insert".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_HJYXPJ_GY_ADD, "jsp/business/sp/hjyxDjgzCnsSp-add"));
		}else if ("update".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_HJYXPJ_GY_ADD, "jsp/business/sp/hjyxDjgzCnsSp-add"));
			m.addObject("id", params[1]);
		}else if ("detail".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_HJYXPJ_GY_VIEW, "jsp/business/sp/hjyxDjgzCnsSp-view"));
			m.addObject("id", params[1]);
		}
		return m;
		
	}
	
	//建设项目环境影响评价报告表（书）审批-区域开发及其他类
	@RequestMapping("/hjyxpjQykf/{optype}")
	public ModelAndView hjyxpjQykf(@PathVariable String optype) {
		ModelAndView m = new ModelAndView();
		String[] params = optype.split(":");
		if ("insert".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_HJYXPJ_QYKF_ADD, "jsp/business/sp/hjyxDjgzCns2Sp-add"));
		}else if ("update".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_HJYXPJ_QYKF_ADD, "jsp/business/sp/hjyxDjgzCns2Sp-add"));
			m.addObject("id", params[1]);
		}else if ("detail".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_HJYXPJ_QYKF_VIEW, "jsp/business/sp/hjyxDjgzCns2Sp-view"));
			m.addObject("id", params[1]);
		}
		return m;
		
	}
	//建设项目环境影响评价报告表（书）审批-饮食娱乐服务类
	@RequestMapping("/hjyxpjYsyl/{optype}")
	public ModelAndView hjyxpjYsyl(@PathVariable String optype) {
		ModelAndView m = new ModelAndView();
		String[] params = optype.split(":");
		if ("insert".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_HJYXPJ_YSYL_ADD, "jsp/business/sp/hjyxDjgzCns3Sp-add"));
		}else if ("update".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_HJYXPJ_YSYL_ADD, "jsp/business/sp/hjyxDjgzCns3Sp-add"));
			m.addObject("id", params[1]);
		}else if ("detail".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_HJYXPJ_YSYL_VIEW, "jsp/business/sp/hjyxDjgzCns3Sp-view"));
			m.addObject("id", params[1]);
		}
		return m;
		
	}
	//无锡新区房屋建筑和市政基础设施施工工程直接发包初步方案审批表
	@RequestMapping("/cbfasp/{optype}")
	public ModelAndView cbfasp(@PathVariable String optype) {
		ModelAndView m = new ModelAndView();
		String[] params = optype.split(":");
		if ("insert".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_CBFASP_ADD, "jsp/business/sp/cbfasp-add"));
		}else if ("update".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_CBFASP_ADD, "jsp/business/sp/cbfasp-add"));
			m.addObject("id", params[1]);
		}else if ("detail".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_CBFASP_VIEW, "jsp/business/sp/cbfasp-view"));
			m.addObject("id", params[1]);
		}else if ("print".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_CBFASP_VIEW, "jsp/business/sp/cbfasp-view"));
			m.addObject("ywlz", params[1]);
		}
		return m;
		
	}
	
	// 无锡新区房屋建筑和市政基础设施施工工程直接发包初步方案审批表 公开招标
			@RequestMapping("/cbfaspGkzb/{optype}")
			public ModelAndView cbfaspGkzb(@PathVariable String optype) {
				ModelAndView m = new ModelAndView();
				String[] params = optype.split(":");
				if ("insert".equals(params[0])) {
					m.setViewName("jsp/business/sp/cbfaspGkzb-add");
				} else if ("update".equals(params[0])) {
					m.setViewName("jsp/business/sp/cbfaspGkzb-add");
					m.addObject("id", params[1]);
				} else if ("detail".equals(params[0])) {
					m.setViewName("jsp/business/sp/cbfaspGkzb-view");
					m.addObject("id", params[1]);
				} else if ("print".equals(params[0])) {
					m.setViewName("jsp/business/sp/cbfaspGkzb-view");
					m.addObject("ywlz", params[1]);
				}
				return m;

			}
			
			// 无锡新区监理工程直接发包初步方案审批表 公开招标
			@RequestMapping("/cbfasp2Gkzb/{optype}")
			public ModelAndView cbfasp2Gkzb(@PathVariable String optype) {
				ModelAndView m = new ModelAndView();
				String[] params = optype.split(":");
				if ("insert".equals(params[0])) {
					m.setViewName("jsp/business/sp/cbfasp2Gkzb-add");
				} else if ("update".equals(params[0])) {
					m.setViewName("jsp/business/sp/cbfasp2Gkzb-add");
					m.addObject("id", params[1]);
				} else if ("detail".equals(params[0])) {
					m.setViewName("jsp/business/sp/cbfasp2Gkzb-view");
					m.addObject("id", params[1]);
				} else if ("print".equals(params[0])) {
					m.setViewName("jsp/business/sp/cbfasp2Gkzb-view");
					m.addObject("ywlz", params[1]);
				}
				return m;

			}
			
	//无锡新区监理工程直接发包初步方案审批表
	@RequestMapping("/cbfasp2/{optype}")
	public ModelAndView cbfasp2(@PathVariable String optype) {
		ModelAndView m = new ModelAndView();
		String[] params = optype.split(":");
		if ("insert".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_CBFASP2_ADD, "jsp/business/sp/cbfasp2-add"));
		}else if ("update".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_CBFASP2_ADD, "jsp/business/sp/cbfasp2-add"));
			m.addObject("id", params[1]);
		}else if ("detail".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_CBFASP2_VIEW, "jsp/business/sp/cbfasp2-view"));
			m.addObject("id", params[1]);
		}else if ("print".equals(params[0])) {
			m.setViewName(Constants.getString(Constants.PAGE_SPYW_CBFASP2_VIEW, "jsp/business/sp/cbfasp2-view"));
			m.addObject("ywlz", params[1]);
		}
		return m;
		
	}
	@RequestMapping("/zzhydj/{optype}")  // 建设 1 zzhydj
	public ModelAndView spZzhydjIndex(@PathVariable String optype) {
		ModelAndView m = new ModelAndView();
		String[] params = optype.split(":");
		if ("insert".equals(params[0])) {
			m.setViewName(Constants.getString("PAGE_SP_ZZHYDJ_ADD", "jsp/business/ywlz/rowView"));
		}else if ("update".equals(params[0])) {
			m.setViewName(Constants.getString("PAGE_SP_ZZHYDJ_UPDATE", "jsp/business/ywlz/rowView"));
			m.addObject("id", params[1]);
		}else if ("detail".equals(params[0])) {
			m.setViewName(Constants.getString("PAGE_SP_ZZHYDJ_VIEW", "jsp/business/ywlz/rowView"));
			m.addObject("id", params[1]);
		}
		return m;
		
	}
	

	@RequestMapping("/jsxmkzsjpssq/{optype}") // 建设 2 jsxmkzsjpssq
	public ModelAndView spJsxmkzsjpssqIndex(@PathVariable String optype) {
		ModelAndView m = new ModelAndView();
		String[] params = optype.split(":");
		if ("insert".equals(params[0])) {
			m.setViewName(Constants.getString("PAGE_SP_JSXMKZSJPSSQ_ADD", "jsp/business/ywlz/rowView"));
		}else if ("update".equals(params[0])) {
			m.setViewName(Constants.getString("PAGE_SP_JSXMKZSJPSSQ_UPDATE", "jsp/business/ywlz/rowView"));
			m.addObject("id", params[1]);
		}else if ("detail".equals(params[0])) {
			m.setViewName(Constants.getString("PAGE_SP_JSXMKZSJPSSQ_VIEW", "jsp/business/ywlz/rowView"));
			m.addObject("id", params[1]);
		}
		return m;
		
	}
	
	@RequestMapping("/jsgcaqzljdsbgzcns/{optype}") // 建设 4 jsgcaqzljdsbgzcns
	public ModelAndView spJsgcaqzljdsbgzcnsIndex(@PathVariable String optype) {
		ModelAndView m = new ModelAndView();
		String[] params = optype.split(":");
		if ("insert".equals(params[0])) {
			m.setViewName(Constants.getString("PAGE_SP_JSGCAQZLJDSBGZCNS_ADD", "jsp/business/ywlz/rowView"));
		}else if ("update".equals(params[0])) {
			m.setViewName(Constants.getString("PAGE_SP_JSGCAQZLJDSBGZCNS_UPDATE", "jsp/business/ywlz/rowView"));
			m.addObject("id", params[1]);
		}else if ("detail".equals(params[0])) {
			m.setViewName(Constants.getString("PAGE_SP_JSGCAQZLJDSBGZCNS_VIEW", "jsp/business/ywlz/rowView"));
			m.addObject("id", params[1]);
		}
		return m;
		
	}
	
	@RequestMapping("/jgysba/{optype}") // 建设 6 jgysba
	public ModelAndView spJgysbaIndex(@PathVariable String optype) {
		ModelAndView m = new ModelAndView();
		String[] params = optype.split(":");
		if ("insert".equals(params[0])) {
			m.setViewName(Constants.getString("PAGE_SP_JGYSBA_ADD", "jsp/business/ywlz/rowView"));
		}else if ("update".equals(params[0])) {
			m.setViewName(Constants.getString("PAGE_SP_JGYSBA_UPDATE", "jsp/business/ywlz/rowView"));
			m.addObject("id", params[1]);
		}else if ("detail".equals(params[0])) {
			m.setViewName(Constants.getString("PAGE_SP_JGYSBA_VIEW", "jsp/business/ywlz/rowView"));
			m.addObject("id", params[1]);
		}
		return m;
		
	}
	
	@RequestMapping("/cspsxkzsq/{optype}") // 建设 17 cspsxkzsq
	public ModelAndView spCspsxkzsqIndex(@PathVariable String optype) {
		ModelAndView m = new ModelAndView();
		String[] params = optype.split(":");
		if ("insert".equals(params[0])) {
			m.setViewName(Constants.getString("PAGE_SP_CSPSXKZSQ_ADD", "jsp/business/ywlz/rowView"));
		}else if ("update".equals(params[0])) {
			m.setViewName(Constants.getString("PAGE_SP_CSPSXKZSQ_UPDATE", "jsp/business/ywlz/rowView"));
			m.addObject("id", params[1]);
		}else if ("detail".equals(params[0])) {
			m.setViewName(Constants.getString("PAGE_SP_CSPSXKZSQ_VIEW", "jsp/business/ywlz/rowView"));
			m.addObject("id", params[1]);
		}
		return m;
		
	}
	
	//步骤文件
	
	@RequestMapping("/scxmmjhsd/{optype}") // 建设 9 建设3下的文件 三产项目面积核算单
	public ModelAndView ScxmmjhsdIndex(@PathVariable String optype) {
		ModelAndView m = new ModelAndView();
		String[] params = optype.split(":");
		if ("insert".equals(params[0])) {
			m.setViewName(Constants.getString("PAGE_SP_SCXMMJHSD_ADD", "jsp/business/sp/Bu_Spyw_Scxmmjhsd-add"));
			m.addObject("projects_uid", params[1]);
			m.addObject("js_company_uid", params[2]);
			m.addObject("filename", params[3]);
			
		}else if ("insertview".equals(params[0])) {
			m.setViewName(Constants.getString("PAGE_SP_SCXMMJHSD_VIEW", "jsp/business/sp/Bu_Spyw_Scxmmjhsd-view"));
			m.addObject("id", params[1]);
		}else if ("update".equals(params[0])) {
			m.setViewName(Constants.getString("PAGE_SP_SCXMMJHSD_UPDATE", "jsp/business/sp/Bu_Spyw_Scxmmjhsd-add"));
			m.addObject("id", params[1]);
		}else if ("detail".equals(params[0])) {
			m.setViewName(Constants.getString("PAGE_SP_SCXMMJHSD_VIEW", "jsp/business/sp/Bu_Spyw_Scxmmjhsd-add"));
			m.addObject("id", params[1]);
		}
		return m;
		
	}
	
		
	@RequestMapping("/Qtsfd/{optype}") // 建设 8 征收 步骤1 打印文件 BU_SPYW_QGJJZSJFTSPSX_QGSFTZD
	public ModelAndView QtsfdIndex(@PathVariable String optype) {
		ModelAndView m = new ModelAndView();
		String[] params = optype.split(":");
		if ("insert".equals(params[0])) {
			m.setViewName(Constants.getString("PAGE_SP_QGJJZSJFTSPSX_QGSFTZD_ADD", "jsp/business/bzwj/bu_spyw_qgjjzsjftspsx_qgsftzd-add"));
			m.addObject("projects_uid", params[1]);
			m.addObject("js_company_uid", params[2]);
			m.addObject("filename", params[3]);
			
		}else if ("insertview".equals(params[0])) {
			m.setViewName(Constants.getString("PAGE_SP_QGJJZSJFTSPSX_QGSFTZD_VIEW", "jsp/business/bzwj/bu_spyw_qgjjzsjftspsx_qgsftzd-view"));
			m.addObject("id", params[1]);
		}else if ("update".equals(params[0])) {
			m.setViewName(Constants.getString("PAGE_SP_QGJJZSJFTSPSX_QGSFTZD_UPDATE", "jsp/business/bzwj/bu_spyw_qgjjzsjftspsx_qgsftzd-add"));
			m.addObject("id", params[1]);
		}else if ("detail".equals(params[0])) {
			m.setViewName(Constants.getString("PAGE_SP_QGJJZSJFTSPSX_QGSFTZD_VIEW", "jsp/business/bzwj/bu_spyw_qgjjzsjftspsx_qgsftzd-add"));
			m.addObject("id", params[1]);
		}
		return m;	
	}
	
	@RequestMapping("/jzgcXmzcSqb/{optype}") // 施工手续
	public ModelAndView jzgcXmzcSqbIndex(@PathVariable String optype) {
		ModelAndView m = new ModelAndView();
		String[] params = optype.split(":");
		if ("insert".equals(params[0])) {
			m.setViewName("jsp/business/sgsx/jzgcXmzcSqb-add");
		}else if ("update".equals(params[0])) {
			m.setViewName("jsp/business/sgsx/jzgcXmzcSqb-add");
			m.addObject("id", params[1]);
		}else if ("detail".equals(params[0])) {
			m.setViewName("jsp/business/sgsx/jzgcXmzcSqb-view");
			m.addObject("id", params[1]);
		}
		return m;	
	}
	
	@RequestMapping("/jsgcAqjdSbs/{optype}") // 施工手续
	public ModelAndView jsgcAqjdSbsIndex(@PathVariable String optype) {
		ModelAndView m = new ModelAndView();
		String[] params = optype.split(":");
		if ("insert".equals(params[0])) {
			m.setViewName("jsp/business/sgsx/jsgcAqjdSbs-add");
		}else if ("update".equals(params[0])) {
			m.setViewName("jsp/business/sgsx/jsgcAqjdSbs-add");
			m.addObject("id", params[1]);
		}else if ("detail".equals(params[0])) {
			m.setViewName("jsp/business/sgsx/jsgcAqjdSbs-view");
			m.addObject("id", params[1]);
		}
		return m;	
	}
	
	@RequestMapping("/jsgcKgaqSctjFcb/{optype}") // 施工手续
	public ModelAndView jsgcKgaqSctjFcbIndex(@PathVariable String optype) {
		ModelAndView m = new ModelAndView();
		String[] params = optype.split(":");
		if ("insert".equals(params[0])) {
			m.setViewName("jsp/business/sgsx/jsgcKgaqSctjFcb-add");
		}else if ("update".equals(params[0])) {
			m.setViewName("jsp/business/sgsx/jsgcKgaqSctjFcb-add");
			m.addObject("id", params[1]);
		}else if ("detail".equals(params[0])) {
			m.setViewName("jsp/business/sgsx/jsgcKgaqSctjFcb-view");
			m.addObject("id", params[1]);
		}
		return m;	
	}
	
	@RequestMapping("/sggdsJrgwSb/{optype}") // 施工手续
	public ModelAndView sggdsJrgwSbIndex(@PathVariable String optype) {
		ModelAndView m = new ModelAndView();
		String[] params = optype.split(":");
		if ("insert".equals(params[0])) {
			m.setViewName("jsp/business/sgsx/sggdsJrgwSb-add");
		}else if ("update".equals(params[0])) {
			m.setViewName("jsp/business/sgsx/sggdsJrgwSb-add");
			m.addObject("id", params[1]);
		}else if ("detail".equals(params[0])) {
			m.setViewName("jsp/business/sgsx/sggdsJrgwSb-view");
			m.addObject("id", params[1]);
		}
		return m;	
	}
	
	@RequestMapping("/szgcXmzcSqb/{optype}") // 施工手续
	public ModelAndView szgcXmzcSqbIndex(@PathVariable String optype) {
		ModelAndView m = new ModelAndView();
		String[] params = optype.split(":");
		if ("insert".equals(params[0])) {
			m.setViewName("jsp/business/sgsx/szgcXmzcSqb-add");
		}else if ("update".equals(params[0])) {
			m.setViewName("jsp/business/sgsx/szgcXmzcSqb-add");
			m.addObject("id", params[1]);
		}else if ("detail".equals(params[0])) {
			m.setViewName("jsp/business/sgsx/szgcXmzcSqb-view");
			m.addObject("id", params[1]);
		}
		return m;	
	}
	
	@RequestMapping("/rlsbCth/{optype}") // 施工手续
	public ModelAndView rlsbCthIndex(@PathVariable String optype) {
		ModelAndView m = new ModelAndView();
		String[] params = optype.split(":");
		if ("insert".equals(params[0])) {
			m.setViewName("jsp/business/sgsx/rlsbCth-add");
		}else if ("update".equals(params[0])) {
			m.setViewName("jsp/business/sgsx/rlsbCth-add");
			m.addObject("id", params[1]);
		}else if ("detail".equals(params[0])) {
			m.setViewName("jsp/business/sgsx/rlsbCth-view");
			m.addObject("id", params[1]);
		}
		return m;	
	}
	
	@RequestMapping("/rlsbTsh/{optype}") // 施工手续
	public ModelAndView rlsbTshIndex(@PathVariable String optype) {
		ModelAndView m = new ModelAndView();
		String[] params = optype.split(":");
		if ("insert".equals(params[0])) {
			m.setViewName("jsp/business/sgsx/rlsbTsh-add");
		}else if ("update".equals(params[0])) {
			m.setViewName("jsp/business/sgsx/rlsbTsh-add");
			m.addObject("id", params[1]);
		}else if ("detail".equals(params[0])) {
			m.setViewName("jsp/business/sgsx/rlsbTsh-view");
			m.addObject("id", params[1]);
		}
		return m;	
	}
	
	@RequestMapping("/Jyfwf/{type}/{optype}") // 建设 3  步骤3 文件   十、交易服务费收费
	public ModelAndView JyfwfIndex(@PathVariable String type,@PathVariable String optype) {
		if("jl".equals(type)){
		ModelAndView m = new ModelAndView();
		String[] params = optype.split(":");
		if ("insert".equals(params[0])) {
			m.setViewName("jsp/business/bzwj/bu_spyw_jyfwfsf_jsgcjktzd-add-jl");
			m.addObject("projects_uid", params[1]);
			m.addObject("js_company_uid", params[2]);
			m.addObject("filename", params[3]);
			m.addObject("ywlzid", params[4]);
			
		}else if ("insertview".equals(params[0])) {
			m.setViewName("jsp/business/bzwj/bu_spyw_jyfwfsf_jsgcjktzd-view-jl");
			m.addObject("id", params[1]);
		}else if ("update".equals(params[0])) {
			m.setViewName("jsp/business/bzwj/bu_spyw_jyfwfsf_jsgcjktzd-add-jl");
			m.addObject("id", params[1]);
		}else if ("detail".equals(params[0])) {
			m.setViewName("jsp/business/bzwj/bu_spyw_jyfwfsf_jsgcjktzd-add-jl");
			m.addObject("id", params[1]);
		}
		return m;
		}else if("sg".equals(type)){
			ModelAndView m = new ModelAndView();
			String[] params = optype.split(":");
			if ("insert".equals(params[0])) {
				m.setViewName(Constants.getString("PAGE_SP_JYFWFSF_JSGCJKTZD_ADD", "jsp/business/bzwj/bu_spyw_qgjjzsjftspsx_qgsftzd-add"));
				m.addObject("projects_uid", params[1]);
				m.addObject("js_company_uid", params[2]);
				m.addObject("filename", params[3]);
				m.addObject("ywlzid", params[4]);
				
			}else if ("insertview".equals(params[0])) {
				m.setViewName(Constants.getString("PAGE_SP_JYFWFSF_JSGCJKTZD_VIEW", "jsp/business/bzwj/bu_spyw_qgjjzsjftspsx_qgsftzd-view"));
				m.addObject("id", params[1]);
			}else if ("update".equals(params[0])) {
				m.setViewName(Constants.getString("PAGE_SP_JYFWFSF_JSGCJKTZD_UPDATE", "jsp/business/bzwj/bu_spyw_qgjjzsjftspsx_qgsftzd-add"));
				m.addObject("id", params[1]);
			}else if ("detail".equals(params[0])) {
				m.setViewName(Constants.getString("PAGE_SP_JYFWFSF_JSGCJKTZD_VIEW", "jsp/business/bzwj/bu_spyw_qgjjzsjftspsx_qgsftzd-add"));
				m.addObject("id", params[1]);
			}
			return m;
		}
		return null;
	}
	
	
	
	
	@RequestMapping(value = "/jlpersonadd")
	public String jlpersonadd() {
		return Constants.getString(Constants.PAGE_Jl_PERSON_ADD, "jsp/business/jl/jlpersion-sh");
	}


	@RequestMapping(value = "/jlpersonadd/{id}")
	public ModelAndView jlpersona(@PathVariable String id) {
		ModelAndView m = new ModelAndView();
		m.setViewName(Constants.getString(Constants.PAGE_Jl_PERSON_EDIT, "jsp/business/jl/jlpersion-edit"));
		m.addObject("id", id);
		return m;

	}
	
	@RequestMapping(value = "/jlpersonadd/{id}/{t_id}")
	public ModelAndView jlpersonadd(@PathVariable String id,@PathVariable String t_id) {
		ModelAndView m = new ModelAndView();
		m.setViewName(Constants.getString(Constants.PAGE_Jl_PERSON_ADD, "jsp/business/jl/jlpersion-sh"));
		m.addObject("id", id);
		m.addObject("t_id", t_id);
		return m;

	}
	
	@RequestMapping(value = "/jlpersonedit/{id}")
	public ModelAndView jlpersonedit(@PathVariable String id) {
		ModelAndView m = new ModelAndView();
		m.setViewName(Constants.getString(Constants.PAGE_Jl_PERSON_EDIT, "jsp/business/jl/jlpersion-edit"));
		m.addObject("id", id);
		
		return m;

	}
	
	@RequestMapping(value = "/jlEnterprise/{id}")
	public ModelAndView jlEnterprise(@PathVariable String id) {
		ModelAndView m = new ModelAndView();
		m.setViewName(Constants.getString("PAGE_JL_ENTER_EDIT_RK", "jsp/business/jl/jlenterprise-edit"));
		m.addObject("id", id);
		return m;
	}
	
	/**
	 * 工地子系统跳转使用
	 * */
	@RequestMapping(value = "/pagegdzxt/{bse}/{pkg}/{filename:[\\w.\\-]+}")
	public String pageview_auth_gdzxt(HttpServletRequest request,ModelMap modeDriven, @PathVariable String bse, @PathVariable String pkg,
			@PathVariable String filename, @RequestParam(value = "gdzxt_gcid") String gdzxt_gcid 
			) {
		String fileName = "index";
		Object userid = request.getSession().getAttribute("userId");
		if (userid != null) {
			try {
				User user = UserManager.getInstance().getUserByLoginNameFromNc((String) userid);
				RestContext.setCurrentUserInThread(user);
//				request.setAttribute("GONGCHENG_UID", gdzxt_gcid);
				modeDriven.addAttribute("GONGCHENG_UID", gdzxt_gcid);
				fileName = "jsp/" + bse + File.separator + pkg + File.separator + filename;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return fileName;
	}
	
	
	@RequestMapping(value = "/pagegdzxt/getGdxxList")
    @ResponseBody
    public requestJson gdxxList(final HttpServletRequest request, requestJson json) throws Exception {
		Connection conn = DBUtil.getConnection();
		requestJson j = new requestJson();
		List<Map<String,String>> gdxxList=null;
		JSONArray list =new JSONArray();
		
		try {
			String GONGCHENG_UID=request.getParameter("GONGCHENG_UID");
			
            String sqlQuery = "SELECT G.*,J.COMPANY_NAME,J.JS_COMPANY_UID,S.COMPANY_NAME AS SG_COMPANY_NAME,P.PROJECTS_NAME " +
            		" FROM PROJECTS_GONGCHENG G " +
		    		" LEFT JOIN DT_GC_SGBB D ON G.GONGCHENG_UID=D.GONGCHENG_UID " +
		    		" LEFT JOIN SGBB B ON D.SGBB_UID=B.SGBB_UID " +
		    		" LEFT JOIN PROJECTS P ON P.PROJECTS_UID=G.PROJECTS_UID " +
		    		" LEFT JOIN JS_COMPANY J ON P.JS_COMPANY_UID=J.JS_COMPANY_UID " +
		    		" LEFT JOIN SG_ENTERPRISE_LIBRARY S ON S.SG_COMPANY_UID = B.SG_COMPANY_UID " +
		    		" WHERE G.GONGCHENG_UID = '"+GONGCHENG_UID+"'" ;
			gdxxList = DBUtil.queryReturnList(conn, sqlQuery);
			
			if(gdxxList!=null&&gdxxList.size()>0){
				Map<String,String> maptemp = gdxxList.get(0);
				JSONObject obj=new JSONObject();
				obj.put("GONGCHENG_UID", maptemp.get("GONGCHENG_UID"));
				obj.put("GONGCHENG_NAME", maptemp.get("GONGCHENG_NAME"));
				obj.put("SG_COMPANY_NAME", maptemp.get("SG_COMPANY_NAME"));
				obj.put("PROJECTS_NAME", maptemp.get("PROJECTS_NAME"));
				list.add(obj);
			}
			
		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		j.setMsg(list.toString());
		return j;
	}
	
	
	@RequestMapping(value = "/jlentedit/{oldId}/{id}")
	public ModelAndView jlentedit(@PathVariable String oldId,@PathVariable String id) {
		ModelAndView m = new ModelAndView();
		m.setViewName(Constants.getString(Constants.PAGE_JL_ENTER_SH, "jsp/business/jl/jlenterprise-sh"));
		m.addObject("id", id);
		m.addObject("oldId", oldId);
		return m;
	}
	
	//危险源过程管理管理
	@RequestMapping(value = "/wxy/{bse}/{pkg}/{filename:[\\w.\\-]+}")
	public String wxyview_auth(HttpServletRequest request,ModelMap modeDriven, @PathVariable String bse, @PathVariable String pkg,
			@PathVariable String filename, @RequestParam(value = "gdzxt_gcid") String gdzxt_gcid  , @RequestParam(value = "gc_type") String gc_type ) {

		String fileName = "index";
		Object userid = request.getSession().getAttribute("userId");
		if (userid != null) {
			try {
				User user = UserManager.getInstance().getUserByLoginNameFromNc((String) userid);
				RestContext.setCurrentUserInThread(user);
				modeDriven.addAttribute("GONGCHENG_UID", gdzxt_gcid);
				modeDriven.addAttribute("GC_TYPE", gc_type);
				fileName = "jsp/" + bse + File.separator + pkg + File.separator + filename;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return fileName;
	}

}
