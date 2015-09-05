/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    sgbb.service.SgBbService.java
 * 创建日期： 2014-04-21 下午 05:57:51
 * 功能：    接口：施工报备
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-04-21 下午 05:57:51  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sgbb.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.commons.Utils;
import com.ccthanking.business.person.dao.SgZhengshuDao;
import com.ccthanking.business.sgbb.dao.SgBbDao;
import com.ccthanking.business.sgbb.dao.SgbbRyDao;
import com.ccthanking.business.sgbb.service.SgBbService;
import com.ccthanking.business.sgbb.vo.SgbbRyVO;
import com.ccthanking.business.sgbb.vo.SgbbVO;
import com.ccthanking.business.sgenter.dao.SgEnterPriseLibraryDao;
import com.ccthanking.business.sgenter.service.rule.SgBbRuleEngine;
import com.ccthanking.business.sgenter.vo.SgBbPbrsPojo;
import com.ccthanking.business.sgenter.vo.SgBbPojo;
import com.ccthanking.business.sgenter.vo.SgBbZygmPojo;
import com.ccthanking.business.weixin.service.FsMessageInfoService;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;

/**
 * <p>
 * SgBbService.java
 * </p>
 * <p>
 * 功能：施工报备
 * </p>
 * 
 * <p>
 * <a href="SgBbService.java.html"><i>查看源代码</i></a>
 * </p>
 * 
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-04-21
 * 
 */

@Service
public class SgBbServiceImpl extends Base1ServiceImpl<SgbbVO, String> implements SgBbService {

	private static Logger logger = LoggerFactory.getLogger(SgBbServiceImpl.class);

	// 业务类型
	private String ywlx = YwlxManager.SGBB;

	private SgBbDao sgBbDao;
	private SgEnterPriseLibraryDao sgEnterPriseLibraryDao;
	private SgbbRyDao sgbbRyDao;
	private SgZhengshuDao sgZhengshuDao;

	@Autowired
	@Qualifier("sgBbDaoImpl")
	public void setSgBbDao(SgBbDao sgBbDao) {
		this.sgBbDao = sgBbDao;
	}

	@Autowired
	@Qualifier("sgEnterPriseLibraryDaoImpl")
	public void setSgEnterPriseLibraryDao(
			SgEnterPriseLibraryDao sgEnterPriseLibraryDao) {
		this.sgEnterPriseLibraryDao = sgEnterPriseLibraryDao;
	}
	
	@Autowired
	@Qualifier("sgbbRyDaoImpl")
	public void setSgbbRyDao(
			SgbbRyDao sgbbRyDao) {
		this.sgbbRyDao = sgbbRyDao;
	}
	
	@Autowired
	@Qualifier("sgZhengshuDaoImpl")
	public void setSgZhengshuDao(
			SgZhengshuDao sgZhengshuDao) {
		this.sgZhengshuDao = sgZhengshuDao;
	}
	@Autowired
	private FsMessageInfoService fsMessageInfoService;

	// @Override
	public String queryCondition(String json,Map map) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String domresult = "";
		try {
			
			domresult = sgBbDao.queryCondition(json, null, map);

			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY,LogManager.RESULT_SUCCESS, user.getName() + "查询<施工报备>", user, "", "");

		} catch (DaoException e) {
			logger.error("施工报备{}", e.getMessage());
			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY,LogManager.RESULT_FAILURE, user.getName() + "施工报备查询失败", user, "", "");
			SystemException.handleMessageException("施工报备查询失败,请联系相关人员处理");
		} finally {
		}
		return domresult;

	}

	public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVO = null;
		SgbbVO vo = new SgbbVO();

		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject obj = (JSONObject) list.get(0);
			vo.setValueFromJson(obj);
			
			vo.setSg_company_uid(user.getIdCard());
			
			vo.setBb_code(sgBbDao.getNewBaobeiCode());
			vo.setStatus("2");
						
			JSONArray ryArray = null;
			try{
				ryArray = obj.getJSONArray("SG_PERSON_UID");
	        }catch(JSONException e){
	        	System.out.println("没有人员!");
	        }
//	        vo.setSgry_nums(ryArray==null?0+"":ryArray.size()+"");
			// 插入
	        vo.setCreated_date(new Date());
	        vo.setCreated_name(user.getName());
	        vo.setCreated_uid(user.getUserSN());
			sgBbDao.save(vo);
			
			resultVO = vo.getRowJson();
			String reason = getDate()+"在"+vo.getProject_name()+"("+vo.getProject_code()+")报备中加锁";
	        SgbbRyVO ryVO = null;
	        if(ryArray!=null){
	        	for (int index = 0; index < ryArray.size(); index++) {
	        		if(StringUtils.isBlank((String) ryArray.get(index))){
						continue;
					}
	        		ryVO = new SgbbRyVO();
	        		ryVO.setSgbb_uid(vo.getSgbb_uid());
	        		ryVO.setStatus("1");
	        		ryVO.setGangwei_uid(obj.getJSONArray("GANGWEI_UID").getString(index));
	        		ryVO.setMust_y(obj.getJSONArray("MUST_Y").getString(index));
	        		ryVO.setSg_person_uid(ryArray.getString(index));
	        		ryVO.setSg_name(obj.getJSONArray("SG_NAME").getString(index));
	        		ryVO.setZhengshu_name(obj.getJSONArray("ZHENGSHU_NAME").getString(index));
	        		ryVO.setZhuanye(obj.getJSONArray("ZHUANYE").getString(index));
	        		ryVO.setZhengshu_code(obj.getJSONArray("ZHENGSHU_CODE").getString(index));
	        		ryVO.setZhengshu_date(Utils.formatToDate(obj.getJSONArray("ZHENGSHU_DATE").getString(index)));
	        		ryVO.setAge(obj.getJSONArray("AGE").getString(index));
	        		ryVO.setZhicheng_name(obj.getJSONArray("ZHICHENG_NAME").getString(index));
	        		ryVO.setMobile(obj.getJSONArray("MOBILE").getString(index));
	        		ryVO.setShenfenzheng(obj.getJSONArray("SHENFENZHENG").getString(index));
	        		ryVO.setXuhao(index+"");
	        		ryVO.setSerial_no(index+"");
	        		ryVO.setCreated_date(new Date());
	        		ryVO.setCreated_name(user.getName());
	        		ryVO.setCreated_uid(user.getUserSN());
	        		sgbbRyDao.save(ryVO);
	        		
	        		sgbbRyDao.insertSgbbStatus(null, ryVO, reason);
				}
	        }
			 LogManager.writeUserLog(vo.getSgbb_uid(), ywlx,Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_SUCCESS, user.getName() + "施工报备新增成功",user, "", "");

			// String
			// jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
			// return queryCondition(jsona, user);

		} catch (DaoException e) {
			e.printStackTrace();
			logger.error("施工报备{}", e.getMessage());
			 LogManager.writeUserLog(vo.getSgbb_uid(), ywlx,
			 Globals.OPERATION_TYPE_INSERT, LogManager.RESULT_FAILURE, user.getName() + "施工报备新增失败",user, "", "");
			SystemException.handleMessageException("施工报备新增失败,请联系相关人员处理");
		} finally {
		}
		return resultVO;

	}

	public String update(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVO = null;
		SgbbVO vo = new SgbbVO();

		try {
			JSONArray list = vo.doInitJson(json);
			vo.setValueFromJson((JSONObject) list.get(0));

			// 修改
			sgBbDao.update(vo);
			resultVO = vo.getRowJson();

			 LogManager.writeUserLog(vo.getSgbb_uid(), ywlx,
			 Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_SUCCESS,
			 user.getOrgDept()
			 .getDeptName() + " " + user.getName() + "施工报备修改成功", user, "",
			 "");

		} catch (DaoException e) {
			logger.error("施工报备{}", e.getMessage());
			 LogManager.writeUserLog(vo.getSgbb_uid(), ywlx,
			 Globals.OPERATION_TYPE_UPDATE, LogManager.RESULT_FAILURE,user.getName() + "施工报备修改失败", user, "",
			 "");
			SystemException.handleMessageException("施工报备修改失败,请联系相关人员处理");
		} finally {
		}
		return resultVO;

	}

	public String delete(String sgbb_uid) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
		String resultVo = null;
		try {
			resultVo = sgBbDao.delete(sgbb_uid);
			LogManager.writeUserLog(sgbb_uid, ywlx,Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS, user.getName() + "施工报备删除成功", user, "", "");
			
		} catch (DaoException e) {
			logger.error("施工报备{}", e.getMessage());
			LogManager.writeUserLog(sgbb_uid, ywlx,
					Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,user.getName() + "施工报备删除失败", user, "", "");
			SystemException.handleMessageException("施工报备删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	

	public String queryBbtj(String json, Map map) throws Exception {
		SgbbVO vo = new SgbbVO();
		User user = ActionContext.getCurrentUserInThread();
		String resultJson = null;
		try {
			String type = (String) map.get("type");
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);
			vo.setValueFromJson(jsonObj);
			// 验证是否有结果
			if (StringUtils.isBlank(type)) {
				return null;
			}
			SgBbRuleEngine sgBbRuleEngine = SgBbRuleEngine.getInstance();
			

			SgBbPojo outPojo = new SgBbPojo();
			
			if ("ck_gclx".equals(type)) {
				resultJson =  sgBbDao.queryParam(vo.getGc_sub_type_uid());
			} else if ("ck_zzdj".equals(type)) { 
				resultJson = sgBbDao.queryZg(vo);
			} else if ("ck_rys".equals(type)) {
				
				// long qyzczb;// 企业注册资本
				SgBbPbrsPojo inPojo = new SgBbPbrsPojo();
				inPojo.setGclb(Integer.parseInt(jsonObj.getString("GC_TYPE_UID")));
				inPojo.setGclx(Integer.parseInt(vo.getGc_sub_type_uid()));
				if(StringUtils.isNotBlank(vo.getCengshu())){
					inPojo.setJzwcs(Integer.parseInt(vo.getCengshu()));
				}
				if(StringUtils.isNotBlank(vo.getKuadu())){
					inPojo.setDwkd(Float.parseFloat(vo.getKuadu()));
				}
				if(StringUtils.isNotBlank(vo.getGuimo())){
					inPojo.setDwjzmj(Float.parseFloat(vo.getGuimo()));
					inPojo.setJzmj(Float.parseFloat(vo.getGuimo()));
					inPojo.setDxgcmj(Float.parseFloat(vo.getGuimo()));
					inPojo.setJzqjzmj(Float.parseFloat(vo.getGuimo()));
				}
				if(StringUtils.isNotBlank(vo.getKuadu())){
					inPojo.setGjgkd(Float.parseFloat(vo.getKuadu()));
				}
				if(StringUtils.isNotBlank(vo.getZhongliang())){
					inPojo.setZzl(Float.parseFloat(vo.getZhongliang()));
				}
				if(StringUtils.isNotBlank(vo.getJine())){
					Float jine = (Float.parseFloat(vo.getJine()))*10000;
					Long bb=jine.longValue();
					inPojo.setDxgchte(bb);
					//inPojo.setDxgchte(Float.parseFloat(vo.getJine())*10000);
				}
				if(StringUtils.isNotBlank(vo.getGaodu())){
					inPojo.setDxgcgd(Float.parseFloat(vo.getGaodu()));
					inPojo.setJzwgd(Float.parseFloat(vo.getGaodu()));
				}
				// inPojo.setQyzczb(Long.parseLong());
				sgBbRuleEngine.executeRuleEngine(inPojo, outPojo);
				if (outPojo.isEnabled()) {
					resultJson = printHtml(outPojo);
				} else {
					return null;
				}
			} else if("ck_level".equals(type)){
				SgBbZygmPojo inPojo = new SgBbZygmPojo();
				inPojo.setGclx(Integer.parseInt(vo.getGc_sub_type_uid()));
				if(StringUtils.isNotBlank(vo.getCengshu())){
					inPojo.setJzwcs(Integer.parseInt(vo.getCengshu()));
				}
				if(StringUtils.isNotBlank(vo.getKuadu())){
					inPojo.setDwkd(Float.parseFloat(vo.getKuadu()));
				}
				if(StringUtils.isNotBlank(vo.getGuimo())){
					inPojo.setDwjzmj(Float.parseFloat(vo.getGuimo()));
					inPojo.setJzmj(Float.parseFloat(vo.getGuimo()));
					inPojo.setDxgcmj(Float.parseFloat(vo.getGuimo()));
					inPojo.setJzqjzmj(Float.parseFloat(vo.getGuimo()));
				}
				if(StringUtils.isNotBlank(vo.getKuadu())){
					inPojo.setGjgkd(Float.parseFloat(vo.getKuadu()));
				}
				if(StringUtils.isNotBlank(vo.getZhongliang())){
					inPojo.setZzl(Float.parseFloat(vo.getZhongliang()));
				}
				if(StringUtils.isNotBlank(vo.getJine())){
					Float jine = (Float.parseFloat(vo.getJine()));
					Long bb=jine.longValue();
					inPojo.setDxgchte(bb);
					//inPojo.setDxgchte(Float.parseFloat(vo.getJine()));
				}
				if(StringUtils.isNotBlank(vo.getGaodu())){
					inPojo.setDxgcgd(Float.parseFloat(vo.getGaodu()));
					inPojo.setJzwgd(Float.parseFloat(vo.getGaodu()));
				}
				// inPojo.setQyzczb(Long.parseLong());
				sgBbRuleEngine.executeRuleEngine(inPojo, outPojo);
				
				if (outPojo.isEnabled()) {
					String person_uid = (String) map.get("person_uid");
					resultJson = outPojo.getJzszygcgmbz()+"";
					String dqlevel = sgZhengshuDao.queryZs(person_uid, "JZ");
					if(dqlevel!=null){
						if(outPojo.getJzszygcgmbz()>=Integer.parseInt(dqlevel)){
							return "allow";
						}else{
							return null;
						}
					}
					
				} else {
					return null;
				}
			}

			LogManager.writeUserLog(null, ywlx,Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS,user.getIdCard() + "施工报备删除成功", user, "", "");

		} catch (DaoException e) {
			logger.error("施工报备规则查询{}", e.getMessage());
			LogManager.writeUserLog(null, ywlx,
					Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE,
					user.getOrgDept().getDeptName() + " " + user.getName()
							+ "施工报备规则失败", user, "", "");
			SystemException.handleMessageException("施工报备规则,请联系相关人员处理");
		} finally {
		}
		return resultJson;
	}

	public String getHtml(JSONObject obj){
		
		
		return ywlx;
		
		
	}
	
	public String checkRule(SgbbVO vo, String type) {
		User user = ActionContext.getCurrentUserInThread();
		String resultJson = null;
		try {
			
		} catch (Exception e) {
			logger.error("施工报备规则验证{}", e.getMessage());
			LogManager.writeUserLog(user.getUserSN(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE, user.getIdCard() + "施工报备规则失败", user, "", "");
			SystemException.handleMessageException("施工报备规则失败,请联系相关人员处理");
		}

		return resultJson;
	}

	public JasperPrint query4Print(String bbid, String parpentPath,String childPath) {
		try {
			// 空的 MAP 装载数据 的容器
			HashMap<String, Object> map = new HashMap<String, Object>();
			// 给父表传入子表
			map.put("SUBREPORT_DIR", childPath);
/***		去掉直接的调用jrxml文件重编译的方式  直接调用编译好的jasper 文件
			// 加载 主报表XML 文件
			JasperDesign loadXmlDesign = JRXmlLoader.load(parpentPath);
			// 生成 文件JasperReport
			JasperReport compileReport = JasperCompileManager.compileReport(loadXmlDesign);
**/		
//			Connection connection= sgBbDao.query4Print(bbid);
			
			
			/**
			 * 查询子表打印信息修改   20140526 by longchuxiong
			 * 修正原PDF文件中子报表数据错误问题
			 * */
			List datalist = sgBbDao.query4PrintForList(bbid);
			Map<Object, Object> REPORT_LIST = new HashMap<Object, Object>();
			REPORT_LIST.put("datalist", datalist);
			map.put("REPORT_LIST", REPORT_LIST); 
			
			
			// 给报表绑定数据源
//			map.put("REPORT_CONNECTION", connection);
			// 设置报表最多为多少行
			map.put("REPORT_MAX_COUNT", 19);
			map.put("id", bbid);
			JRBeanCollectionDataSource jar = new JRBeanCollectionDataSource(findHeaderPrint(bbid));
			// 装载数据 并 生成 文件
			JasperPrint fillReport = JasperFillManager.fillReport(parpentPath, map, jar);
			System.out.println("加载数据文件成功！");
			return fillReport;
		} catch (JRException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取到当前报表头部的信息
	 * 
	 * @author 余健
	 * 
	 */
	public List findHeaderPrint(String xmbh) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List list = sgBbDao.findHeaderPrint(xmbh);
		System.out.println("获取到报表数据LIST" + list.size());
		return list;
	}

	public String queryZbgg(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String domresult = "";
		try {

			domresult = sgBbDao.queryZbgg(json);

			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getName() + "查询<施工报备>", user, "", "");

		} catch (DaoException e) {
			logger.error("施工报备{}", e.getMessage());
			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user.getName() + "施工报备查询失败", user, "", "");
			SystemException.handleMessageException("施工报备查询失败,请联系相关人员处理");
		} finally {
		}
		return domresult;

	}
	
	public String printHtml(SgBbPojo outPojo){
		String html = "";
		String zdpp = outPojo.getZdpbrs();
		
		for (int i = 0; i < zdpp.split(",").length; i++) {
			String[] type_rs = zdpp.split(",")[i].split(":");
			for (int j = 0; j < Integer.parseInt(type_rs[1]); j++) {
				String id = "";
				if("xmfzr".equals(type_rs[0])){
					id = "xmjl";
				}
				
				html += "<tr id=\""+id+"\" style=\"\"><td class=\"text-center\"></td>";
				if("zly".equals(type_rs[0])||"xxgly".equals(type_rs[0])){
					html+="			<td class=\"text-center\"><input id=\"MUST_Y\" style=\"width:99%\" class=\"span12\"  name=\"MUST_Y\" value=\"N\" fieldname=\"MUST_Y\" type=\"hidden\" /><span id=\"xh\"></span></td>";
				}else{
					html+="			<td class=\"text-center\"><input id=\"MUST_Y\" style=\"width:99%\" class=\"span12\"  name=\"MUST_Y\" value=\"Y\" fieldname=\"MUST_Y\" type=\"hidden\" /><span id=\"xh\"></span></td>";
				}
				
				html+="		<td><select id=\"GANGWEI_UID\" class=\"span12\" style=\"width:99%\" name=\"GANGWEI_UID\" fieldname=\"GANGWEI_UID\" >";
				
				if("xmfzr".equals(type_rs[0])){
					html += "<option value='19' selected>项目负责人</option>";
				}else if("xmjsfzr".equals(type_rs[0])){
					html += "<option value='20' selected>项目技术负责人</option>";
				}else if("sgy".equals(type_rs[0])){
					html += "<option value='21'selected>施工员</option>";
				}else if("aqy".equals(type_rs[0])){
					html += "<option value='22' selected>安全员</option>";
				}else if("zjy".equals(type_rs[0])){
					html += "<option value='23' selected>质检员</option>";
				}else if("zly".equals(type_rs[0])){
					html += "<option value='24' selected>资料员</option>";
				}else if("xxgly".equals(type_rs[0])){
					html += "<option value='25' selected>信息管理员</option>";
				}
				
				html +="		</select>"
					+"			<input id=\"SG_PERSON_UID\" style=\"width:99%\" class=\"span12\"  name=\"SG_PERSON_UID\" fieldname=\"SG_PERSON_UID\" type=\"hidden\" />"
					+"		</td>"
					+"		<td><input id=\"SG_NAME\" style=\"width:70%\" class=\"span12\"  name=\"SG_NAME\" fieldname=\"SG_NAME\" type=\"text\" readonly=\"readonly\"/><button class=\"btn btn-link\"  type=\"button\" onclick=\"selectRyxx(this)\"><i class=\"icon-edit\"></i></button></td>"
					+"		<td><input id=\"ZHENGSHU_NAME\" style=\"width:99%\" class=\"span12\"  name=\"ZHENGSHU_NAME\" fieldname=\"ZHENGSHU_NAME\" type=\"text\" readonly=\"readonly\"/></td>"
					+"		<td><input id=\"ZHENGSHU_CODE\" style=\"width:99%\" class=\"span12\"  name=\"ZHENGSHU_CODE\" fieldname=\"ZHENGSHU_CODE\" type=\"text\" readonly=\"readonly\"/></td>"
					+"		<td><input id=\"ZHUANYE\" style=\"width:99%\" class=\"span12\"  name=\"ZHUANYE\" fieldname=\"ZHUANYE\" type=\"text\" readonly=\"readonly\"/></td>"
					+"		<td><input id=\"ZHENGSHU_DATE\" style=\"width:99%\" class=\"span12\"  name=\"ZHENGSHU_DATE\" fieldname=\"ZHENGSHU_DATE\" type=\"text\" readonly=\"readonly\"/></td>"
					+"		<td><input id=\"AGE\" style=\"width:99%\" class=\"span12\"  name=\"AGE\" fieldname=\"AGE\" type=\"text\" readonly=\"readonly\"/></td>"
					+"		<td><input id=\"ZHICHENG_NAME\" style=\"width:99%\" class=\"span12\"  name=\"ZHICHENG_NAME\" fieldname=\"ZHICHENG_NAME\" type=\"text\" readonly=\"readonly\"/></td>"
					+"		<td><input id=\"SHENFENZHENG\" style=\"width:99%\" class=\"span12\"  name=\"SHENFENZHENG\" fieldname=\"SHENFENZHENG\" type=\"text\" readonly=\"readonly\"/></td>"
					+"		<td><input id=\"MOBILE\" style=\"width:99%\" class=\"span12\"  name=\"MOBILE\" fieldname=\"MOBILE\" type=\"text\" readonly=\"readonly\"/></td>"
					+"	</tr>";
			}
		}
		String ppStr = outPojo.getZdpbrszj(); 
		int bs = outPojo.getZdpbrszjfs(); 
		for (int i = 0; i < bs; i++) {
			for (int j = 0; j < ppStr.split(",").length; j++) {
				for (int j2 = 0; j2 < Integer.parseInt(ppStr.split(",")[j].split(":")[1]); j2++) {
					html += "<tr id=\"cloneTR\" style=\"\">"
						+"			<td class=\"text-center\"></td>";
					if("zly".equals(ppStr.split(",")[j].split(":")[0])||"xxgly".equals(ppStr.split(",")[j].split(":")[0])){
						html+="			<td class=\"text-center\"><input id=\"MUST_Y\" style=\"width:99%\" class=\"span12\"  name=\"MUST_Y\" value=\"N\" fieldname=\"MUST_Y\" type=\"hidden\" /><span id=\"xh\"></span></td>";
					}else{
						html+="			<td class=\"text-center\"><input id=\"MUST_Y\" style=\"width:99%\" class=\"span12\"  name=\"MUST_Y\" value=\"Y\" fieldname=\"MUST_Y\" type=\"hidden\" /><span id=\"xh\"></span></td>";
					}
						html+="		<td>"
						+"			<select id=\"GANGWEI_UID\" class=\"span12\" style=\"width:99%\" name=\"GANGWEI_UID\" fieldname=\"GANGWEI_UID\" >";
					if("xmfzr".equals(ppStr.split(",")[j].split(":")[0])){
						html += "<option value='19' selected>项目负责人</option>";
					}else if("xmjsfzr".equals(ppStr.split(",")[j].split(":")[0])){
						html += "<option value='20' selected>项目技术负责人</option>";
					}else if("sgy".equals(ppStr.split(",")[j].split(":")[0])){
						html += "<option value='21'selected>施工员</option>";
					}else if("aqy".equals(ppStr.split(",")[j].split(":")[0])){
						html += "<option value='22' selected>安全员</option>";
					}else if("zjy".equals(ppStr.split(",")[j].split(":")[0])){
						html += "<option value='23' selected>质检员</option>";
					}else if("zly".equals(ppStr.split(",")[j].split(":")[0])){
						html += "<option value='24' selected>资料员</option>";
					}else if("xxgly".equals(ppStr.split(",")[j].split(":")[0])){
						html += "<option value='25' selected>信息管理员</option>";
					}
						html+="</select>"
						+"			<input id=\"SG_PERSON_UID\" style=\"width:99%\" class=\"span12\"  name=\"SG_PERSON_UID\" fieldname=\"SG_PERSON_UID\" type=\"hidden\" />"
						+"		</td>"
						+"		<td><input id=\"SG_NAME\" style=\"width:70%\" class=\"span12\"  name=\"SG_NAME\" fieldname=\"SG_NAME\" type=\"text\" readonly=\"readonly\"/><button class=\"btn btn-link\"  type=\"button\" onclick=\"selectRyxx(this)\"><i class=\"icon-edit\"></i></button></td>"
						+"		<td><input id=\"ZHENGSHU_NAME\" style=\"width:99%\" class=\"span12\"  name=\"ZHENGSHU_NAME\" fieldname=\"ZHENGSHU_NAME\" type=\"text\" readonly=\"readonly\"/></td>"
						+"		<td><input id=\"ZHENGSHU_CODE\" style=\"width:99%\" class=\"span12\"  name=\"ZHENGSHU_CODE\" fieldname=\"ZHENGSHU_CODE\" type=\"text\" readonly=\"readonly\"/></td>"
						+"		<td><input id=\"ZHUANYE\" style=\"width:99%\" class=\"span12\"  name=\"ZHUANYE\" fieldname=\"ZHUANYE\" type=\"text\" readonly=\"readonly\"/></td>"
						+"		<td><input id=\"ZHENGSHU_DATE\" style=\"width:99%\" class=\"span12\"  name=\"ZHENGSHU_DATE\" fieldname=\"ZHENGSHU_DATE\" type=\"text\" readonly=\"readonly\"/></td>"
						+"		<td><input id=\"AGE\" style=\"width:99%\" class=\"span12\"  name=\"AGE\" fieldname=\"AGE\" type=\"text\" readonly=\"readonly\"/></td>"
						+"		<td><input id=\"ZHICHENG_NAME\" style=\"width:99%\" class=\"span12\"  name=\"ZHICHENG_NAME\" fieldname=\"ZHICHENG_NAME\" type=\"text\" readonly=\"readonly\"/></td>"
						+"		<td><input id=\"SHENFENZHENG\" style=\"width:99%\" class=\"span12\"  name=\"SHENFENZHENG\" fieldname=\"SHENFENZHENG\" type=\"text\" readonly=\"readonly\"/></td>"
						+"		<td><input id=\"MOBILE\" style=\"width:99%\" class=\"span12\"  name=\"MOBILE\" fieldname=\"MOBILE\" type=\"text\" readonly=\"readonly\"/></td>"
						+"	</tr>";
				}
			}
		}
		return html ;
		
	}
	
	public String insertToOld(String json) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
		if(StringUtils.isNotBlank(json)){
			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY,LogManager.RESULT_FAILURE, user.getUserSN() + "执行对老系统<施工报备信息>数据同步", user, "", "");
			return sgBbDao.insertToOld(json);
		}
		LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY,LogManager.RESULT_FAILURE, user.getUserSN() + "执行对老系统<施工报备信息>数据同步失败", user, "", "");
		return null;
	}
	
	public String getDate(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		
		return format.format(new Date());
	}

	public void updateBbzt(String sgbbUid, String status,String code,String name) throws Exception {
		SgbbVO vo=new SgbbVO();
		sgBbDao.updateBbzt(sgbbUid, status);
		if("20".equals(status)){
			
			//****************************消息添加
	         Map messageMap= new HashMap();
	         messageMap.put("TITLE", "施工人员审核");
	         messageMap.put("CONTENT", "人员审核通过");
	         messageMap.put("USERTO", code);//企业的登录Code
	         messageMap.put("USERTONAME", name);//企业的名称
	
	         fsMessageInfoService.insertVo(messageMap);
		}
	}

	public void updateBbToUnlock(String uid, String optype) throws Exception {
		try {
			if ("doBb".equals(optype)) {
				sgBbDao.unLockBb(uid);
			}else if("doBbry".equals(optype)){
				sgBbDao.unLockBbry(uid);
			}
		} catch (DaoException e) {
			logger.error("施工报备解锁{}", e.getMessage());
			SystemException.handleException("************施工报备/施工报备人员解锁**************");
		}
	}
	public void personLock(String uid) throws Exception {
		
				sgBbDao.personLock(uid);
		
	}

}
