/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    ywlz.service.BuSpYwlzBzService.java
 * 创建日期： 2014-06-19 下午 04:49:00
 * 功能：    接口：审批业务流转步骤
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-19 下午 04:49:00  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.ywlz.service.impl;

import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import oracle.net.aso.l;

import org.antlr.grammar.v3.ANTLRv3Parser.action_return;
import org.drools.lang.dsl.DSLMapParser.variable_definition_return;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.dtgl.kqgl.vo.KqGcInfoVO;
import com.ccthanking.business.dtgl.sgsx.vo.BuSpywJsgcaqjcsqVO;
import com.ccthanking.business.dtgl.sgsx.vo.ProjectsAjzjVO;
import com.ccthanking.business.dtgl.wxy.vo.SgSpWxygcqdVO;
import com.ccthanking.business.gongcheng.service.ProjectsGongchengService;
import com.ccthanking.business.gongcheng.service.impl.ProjectsGongchengServiceImpl;
import com.ccthanking.business.project.service.JsCompanyService;
import com.ccthanking.business.project.service.ProjectsService;
import com.ccthanking.business.project.vo.DtGcJlbbVO;
import com.ccthanking.business.project.vo.DtGcSgbbVO;
import com.ccthanking.business.project.vo.JsCompanyVO;
import com.ccthanking.business.project.vo.ProjectsGongchengUnitsVO;
import com.ccthanking.business.project.vo.ProjectsGongchengVO;
import com.ccthanking.business.project.vo.ProjectsUnitsVO;
import com.ccthanking.business.project.vo.ProjectsVO;
import com.ccthanking.business.sgsx.service.BuSpywJsgcaqjcsqService;
import com.ccthanking.business.sp.service.BuSpywCbfaspService;
import com.ccthanking.business.spxx.service.BuSpBzService;
import com.ccthanking.business.spxx.service.BuSpYwxxService;
import com.ccthanking.business.spxx.vo.BuSpYwxxVO;
import com.ccthanking.business.weixin.service.FsMessageInfoService;
import com.ccthanking.business.ywlz.dao.BuSpYwlzBzDao;
import com.ccthanking.business.ywlz.service.BuSpYwlzBzService;
import com.ccthanking.business.ywlz.service.BuSpYwlzService;
import com.ccthanking.business.ywlz.vo.BuSpYwlzBzVO;
import com.ccthanking.business.ywlz.vo.BuSpYwlzVO;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.ccthanking.framework.util.WorkdayUtils;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;
import com.ibm.icu.text.SimpleDateFormat;

/**
 * <p>
 * BuSpYwlzBzService.java
 * </p>
 * <p>
 * 功能：审批业务流转步骤
 * </p>
 * 
 * <p>
 * <a href="BuSpYwlzBzService.java.html"><i>查看源代码</i></a>
 * </p>
 * 
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-06-19
 * 
 */

@Service
public class BuSpYwlzBzServiceImpl extends
		Base1ServiceImpl<BuSpYwlzBzVO, String> implements BuSpYwlzBzService {
	private static Logger logger = LoggerFactory
			.getLogger(BuSpYwlzBzServiceImpl.class);

	private BuSpYwlzBzDao buSpYwlzBzDao;
	@Autowired
	private BuSpBzService buSpBzService;
	@Autowired
	private BuSpYwlzService buSpYwlzService;// 业务流转实例
	@Autowired
	private BuSpYwxxService buSpYwxxService;
	@Autowired
	private ProjectsGongchengService gongchengServiceImpl;
	@Autowired
	private ProjectsService projectsService;

	@Autowired
	private BuSpywCbfaspService buSpywCbfaspService;

	@Autowired
	private BuSpywJsgcaqjcsqService buSpywJsgcaqjcsqService;

	@Autowired
	private FsMessageInfoService fsMessageInfoService;

	@Autowired
	private JsCompanyService companyService;

	@Autowired
	@Qualifier("buSpYwlzBzDaoImpl")
	public void setBuSpYwlzBzDao(BuSpYwlzBzDao buSpYwlzBzDao) {
		this.buSpYwlzBzDao = buSpYwlzBzDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) buSpYwlzBzDao);
	}

	// @Override
	public String queryCondition(String json) throws Exception {
		// User user = ActionContext.getCurrentUserInThread();
		String domresult = "";
		try {
			domresult = buSpYwlzBzDao.queryCondition(json, null, null);
		} catch (DaoException e) {
			logger.error("审批业务流转步骤查询{}", e.getMessage());
			SystemException.handleMessageException("审批业务流转步骤查询失败,请联系相关人员处理");
		} finally {
		}
		return domresult;

	}

	public String queryJlRy(String json) throws Exception {
		// User user = ActionContext.getCurrentUserInThread();
		String domresult = "";
		try {
			domresult = buSpYwlzBzDao.queryJlRy(json, null, null);
		} catch (DaoException e) {
			logger.error("审批业务流转步骤查询监理人员{}", e.getMessage());
			SystemException
					.handleMessageException("审批业务流转步骤查询监理人员失败,请联系相关人员处理");
		} finally {
		}
		return domresult;

	}

	public String insert(String json) throws Exception {
		// User user = ActionContext.getCurrentUserInThread();
		String resultVO = null;
		BuSpYwlzBzVO vo = new BuSpYwlzBzVO();

		try {
			JSONArray list = vo.doInitJson(json);
			vo.setValueFromJson((JSONObject) list.get(0));

			// 插入
			buSpYwlzBzDao.save(vo);
			resultVO = vo.getRowJson();
		} catch (DaoException e) {
			logger.error("审批业务流转步骤新增{}", e.getMessage());
			SystemException.handleMessageException("审批业务流转步骤新增失败,请联系相关人员处理");
		} finally {
		}
		return resultVO;

	}

	public String insertJlRy(String json, String id, String jdxzId)
			throws Exception {
		// User user = ActionContext.getCurrentUserInThread();
		String resultVO = null;
		ProjectsAjzjVO vo = new ProjectsAjzjVO();

		try {
			// 监督小组添加
			if (jdxzId != null && !"".equals(jdxzId)) {
				vo.setProjects_ajzj_uid(DBUtil
						.getSequenceValue("PROJECTS_AJZJ_UID"));
				vo.setProjects_uid(id);
				vo.setJiandu_type("100");
				vo.setUsers_uid(jdxzId);
				buSpYwlzBzDao.save(vo);
			}
			String[] jlry = json.split(",");
			for (int i = 0; i < jlry.length; i++) {// 人员 添加
				String[] jl = jlry[i].split(":");
				vo.setProjects_uid(id);
				vo.setUsers_uid(jl[0]);
				vo.setZhujian_y(jl[1]);
				int zj = jl[2].indexOf("ZJ");
				int aj = jl[2].indexOf("AJ");
				// AJZJ 人员即是 安监也是质检 插入两条 Jiandu_type 1:安监 2:质监
				if (zj != -1) {
					vo.setProjects_ajzj_uid(DBUtil
							.getSequenceValue("PROJECTS_AJZJ_UID"));
					vo.setJiandu_type("2");
					buSpYwlzBzDao.save(vo);
				}
				if (aj != -1) {
					vo.setProjects_ajzj_uid(DBUtil
							.getSequenceValue("PROJECTS_AJZJ_UID"));
					vo.setJiandu_type("1");
					buSpYwlzBzDao.save(vo);
				}
			}

			// 插入
			resultVO = vo.getRowJson();
		} catch (DaoException e) {
			logger.error("审批业务流转步骤新增{}", e.getMessage());
			SystemException.handleMessageException("审批业务流转步骤新增失败,请联系相关人员处理");
		} finally {
		}
		return resultVO;

	}

	public String update(String json) throws Exception {
		// User user = ActionContext.getCurrentUserInThread();
		String resultVO = null;
		BuSpYwlzBzVO vo = new BuSpYwlzBzVO();
		try {
			JSONArray list = vo.doInitJson(json);
			vo.setValueFromJson((JSONObject) list.get(0));
			// 修改
			buSpYwlzBzDao.update(vo);
			resultVO = vo.getRowJson();
		} catch (DaoException e) {
			logger.error("审批业务流转步骤修改{}", e.getMessage());
			SystemException.handleMessageException("审批业务流转步骤修改失败,请联系相关人员处理");
		} finally {
		}
		return resultVO;

	}

	public String updateFfzcz(String json) throws Exception {
		// User user = ActionContext.getCurrentUserInThread();
		String resultVO = null;
		BuSpYwlzBzVO vo = new BuSpYwlzBzVO();
		try {
			vo.setLzbz_uid(json);
			// 修改
			vo.setFszcz_date(new Date());
			buSpYwlzBzDao.update(vo);
			resultVO = vo.getRowJson();
		} catch (DaoException e) {
			logger.error("审批业务流转步骤修改{}", e.getMessage());
			SystemException.handleMessageException("审批业务流转步骤修改失败,请联系相关人员处理");
		} finally {
		}
		return resultVO;

	}

	public String delete(String json) throws Exception {
		// User user = ActionContext.getCurrentUserInThread();
		String resultVo = null;
		BuSpYwlzBzVO vo = new BuSpYwlzBzVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);
			vo.setValueFromJson(jsonObj);

			// 删除 根据据主键
			buSpYwlzBzDao.delete(BuSpYwlzBzVO.class, vo.getLzbz_uid());
			resultVo = vo.getRowJson();
		} catch (DaoException e) {
			logger.error("审批业务流转步骤删除{}", e.getMessage());
			SystemException.handleMessageException("审批业务流转步骤删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	public void saveSgsxYw(BuSpYwlzVO lzvo, String spywid) throws Exception { // 施工手续特殊处理
		User user = ActionContext.getCurrentUserInThread();
		Connection conn = DBUtil.getConnection();
		try {
			ProjectsGongchengVO gcVo = gongchengServiceImpl.findById(lzvo
					.getProjects_uid());

			if (spywid.equals(Constants.SG_SX_JZGCSGZCZBA_YWID)// 建筑工程施工注册证及安监备案 手续
					&& "-1".equals(gcVo.getGc_status())) {
				// 更新工程 为在建
				String updateGc = "update PROJECTS_GONGCHENG set GC_STATUS ='0' WHERE GONGCHENG_UID='"
						+ lzvo.getProjects_uid() + "' ";
				DBUtil.exec(conn, updateGc);
			} else { //施工 其他手续
				if (gcVo.getSfszgc().equals("1")) {// 市政
					if (spywid.equals(Constants.SG_SX_SZGCSB_YWID)
							|| spywid.equals(Constants.SG_SX_KGQAQTJFC_YWID)) { // 115或117
						String sql = "select count(*) from bu_sp_ywlz where projects_uid='"
								+ lzvo.getProjects_uid()
								+ "' "
								+ "and spyw_uid in('"
								+ Constants.SG_SX_SZGCSB_YWID
								+ "','"
								+ Constants.SG_SX_KGQAQTJFC_YWID
								+ "')"
								+ " and status='1'";
						String[][] res = DBUtil.querySql(sql);
						if (res != null && res[0][0].equals("2")) {
							String kgywlzid = insertYwlzxx(
									Constants.SG_SX_JZGCSGZCZBA_YWID,
									lzvo.getProjects_uid(),
									lzvo.getSg_company_uid());
							buSpYwlzBzDao.updateSH(kgywlzid,
									lzvo.getProjects_uid());
							buSpYwlzBzDao.insertBz(kgywlzid);
						}
					}
				} else if (gcVo.getSfszgc().equals("0")) {// 建筑企业 114 117
															// 118
					String sql = "select count(*) from bu_sp_ywlz where projects_uid='"
							+ lzvo.getProjects_uid()
							+ "' "
							+ "and spyw_uid in('"
							+ Constants.SG_SX_JZGCSB_YWID
							+ "','"
							+ Constants.SG_SX_KGQAQTJFC_YWID
							+ "','"
							+ Constants.SG_SX_RLSBXT_YWID
							+ "')"
							+ " and status='1'";
					String[][] res = DBUtil.querySql(sql);
					if (res != null && res[0][0].equals("3")) {
						String kgywlzid = insertYwlzxx(
								Constants.SG_SX_JZGCSGZCZBA_YWID,
								lzvo.getProjects_uid(),
								lzvo.getSg_company_uid());
						buSpYwlzBzDao
								.updateSH(kgywlzid, lzvo.getProjects_uid());
						buSpYwlzBzDao.insertBz(kgywlzid);

					}

					if (spywid.equals(Constants.SG_SX_RLSBXT_YWID)) {
						this.saveSgsxRlsbkqba(lzvo.getYwlz_uid(),
								lzvo.getProjects_uid());
					}
				}
			}
		} catch (Exception e) {
			logger.error("审批业务审核后特殊处理施工手续特殊处理。出错{}", e.getMessage());
		} finally {
			DBUtil.closeConnetion(conn);
		}

	}

	public void saveSgsxRlsbkqba(String ywlzid, String gcid) {// 施工手续 - 人脸识别考勤备案
																// 业务处理
		User user = ActionContext.getCurrentUserInThread();
		Connection conn = DBUtil.getConnection();
		try {
			// *********** 特殊化
			String sql = "select * from bu_spyw_jsxmkqtshba where ywlz_uid='"
					+ ywlzid + "' ";
			String[][] tsh = DBUtil.query(conn, sql);
			// *********** 常态化
			String tshsql = "select zhongyao_ry_y,zhuyao_ry_y,BEGIN_DATE,ywlz_uid,jsxmkqcthba_uid from  bu_Spyw_Jsxmkqcthba  where ywlz_uid='"
					+ ywlzid + "' ";
			String[][] cth = DBUtil.query(conn, tshsql);

			// *********** cth[0][0]重要考勤人员 cth[0][1]主要考勤人员
			if (cth != null) {// 常态化

				// *********** 新建 工程考勤状态变更记录
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date kqDate = sdf.parse(cth[0][2]);
				KqGcInfoVO kqGcInfoVO = new KqGcInfoVO();
				kqGcInfoVO.setGongcheng_uid(gcid);
				kqGcInfoVO.setKq_target("Y");
				kqGcInfoVO.setChange_date(kqDate);
				kqGcInfoVO.setCreated_date(new Date());
				buSpYwlzBzDao.save(kqGcInfoVO);

				// *********** 更新 相关 重要人员 考勤状态 0 代表选中
				if (cth[0][0].equals("0")) {
					String zSgryUpdate = "update dt_gc_agry set kq_y='Y',kq_begin_date=to_date('"
							+ cth[0][2]
							+ "','yyyy-MM-dd HH24:mi:ss') "
							+ "where SG_PERSON_UID in (select PERSON_UID from V_GC_RY t where gongcheng_uid='"
							+ gcid
							+ "' and Gangwei_level='Z' and COM_TYPE='SG' ) ";
					DBUtil.exec(conn, zSgryUpdate);
					String zJlryUpdate = "update dt_gc_jlry set kq_y='Y',kq_begin_date=to_date('"
							+ cth[0][2]
							+ "','yyyy-MM-dd HH24:mi:ss') "
							+ "where JL_PERSON_UID in (select PERSON_UID from V_GC_RY t where gongcheng_uid='"
							+ gcid
							+ "' and Gangwei_level='Z' and COM_TYPE='JL' ) ";
					DBUtil.exec(conn, zJlryUpdate);

					// 更新工程 考勤状态:Y 重要人员考勤
					String updateGc = "update PROJECTS_GONGCHENG set KQ_STATUS='Y',KQ_ZHONGYAO_Y='1' WHERE GONGCHENG_UID='"
							+ gcid + "' ";
					DBUtil.exec(conn, updateGc);
				}
				// *********** 更新 主要人员 考勤状态
				if (cth[0][1].equals("0")) {
					String ySgryUpdate = "update dt_gc_sgry set kq_y='Y', kq_begin_date=to_date('"
							+ cth[0][2]
							+ "','yyyy-MM-dd HH24:mi:ss') "
							+ "where SG_PERSON_UID in (select PERSON_UID from V_GC_RY t where gongcheng_uid='"
							+ gcid
							+ "' and Gangwei_level='Y' and COM_TYPE='SG' ) ";
					DBUtil.exec(conn, ySgryUpdate);
					String yJlryUpdate = "update dt_gc_jlry set kq_y='Y', kq_begin_date=to_date('"
							+ cth[0][2]
							+ "','yyyy-MM-dd HH24:mi:ss') "
							+ "where JL_PERSON_UID in (select PERSON_UID from V_GC_RY t where gongcheng_uid='"
							+ gcid
							+ "' and Gangwei_level='Y' and COM_TYPE='JL' ) ";
					DBUtil.exec(conn, yJlryUpdate);

					// 更新工程 考勤状态:Y 主要人员是需要考勤
					String updateGc = "update PROJECTS_GONGCHENG set KQ_STATUS='Y',KQ_ZHUYAO_Y='1' WHERE GONGCHENG_UID='"
							+ gcid + "' ";
					DBUtil.exec(conn, updateGc);
				}

			}
			if (tsh != null) {// 特殊化
				// 更新工程 考勤状态:S
				String updateGc = "update PROJECTS_GONGCHENG set KQ_STATUS='S' WHERE GONGCHENG_UID='"
						+ gcid + "' ";
				DBUtil.exec(conn, updateGc);
			}
		} catch (Exception e) {
			logger.error("施工手续 - 人脸识别考勤备案 业务处理.出错{}", e.getMessage());
		} finally {
			DBUtil.closeConnetion(conn);
		}

	}

	public void saveDwgcSgnrZjfb(String ywlzid, String pid) throws Exception { // 直接发包业务特殊处理
		User user = ActionContext.getCurrentUserInThread();
		Connection conn = DBUtil.getConnection();
		try {

			// 施工
			String sql = "select p.cbfasp_uid,p.dt_ids,p.bb_code from bu_spyw_cbfasp p where p.ywlz_uid ='"
					+ ywlzid + "' and p.lx='sg' ";
			String[][] res = DBUtil.query(conn, sql);

			// 监理
			String sqljl = "select p.cbfasp_uid,p.dt_ids,p.bb_code from bu_spyw_cbfasp p where p.ywlz_uid ='"
					+ ywlzid + "' and p.lx='jl' ";
			String[][] resjl = DBUtil.query(conn, sqljl);

			if (res != null) { // 施工操作

				// 修改报备为已中标
				String updateSgbb = "update sgBB set status='" + 20
						+ "' WHERE bb_code='" + res[0][2] + "' ";
				DBUtil.exec(conn, updateSgbb);

				String gcid = DBUtil.getSequenceValue("GONGCHENG_UID");// id

				// 生成工程code
				String p_code = projectsService.findById(pid)
						.getProjects_code();
				String psql = "select count(*) from projects_gongcheng where projects_uid="
						+ pid + " and status=10";
				String[][] temp = DBUtil.query(conn, psql);
				int count = Integer.parseInt(temp[0][0]) + 1;
				if (count < 10) {
					p_code += "N0" + count;
				} else {
					p_code += "N" + count;
				}

				// 新增施工内容
				String gcsql = "insert into projects_gongcheng"
						+ "(SHENHE_REN,SHENHE_DATE,SHENHE_JIEGUO,STATUS,GONGCHENG_UID,PROJECTS_UID,CREATED_UID,CREATED_NAME,"
						+ "CREATED_DATE,UPDATE_UID,UPDATE_NAME,UPDATE_DATE,GONGCHENG_CODE,NEIRONG,"
						+ "GONGCHENG_NAME,CB_XINGZHI,BID_TYPE,MIANJI,CENGSHU,GAODU,KUADU,SHENDU,JINE,"
						+ "ZHONGLIANG,KG_DATE,WG_DATE,JG_DATE,SFSZGC)"
						+ "(select '"
						+ user.getUserSN()
						+ "',sysdate,'1','10',"
						+ gcid
						+ ",'"
						+ pid
						+ "',p.CREATED_UID,p.CREATED_NAME,"
						+ "p.CREATED_DATE,p.UPDATE_UID,p.UPDATE_NAME,p.UPDATE_DATE,'"
						+ p_code
						+ "',"
						+ "p.NEIRONG,p.GONGCHENG_NAME,p.CB_XINGZHI,p.BID_TYPE,p.ZJMJ,p.CS,p.GAODU,"
						+ "p.ZDDKKD,p.SHENDU,p.BDHTJ,p.ZHONGLIANG,p.DATE_JHKG,p.WG_DATE,p.DATE_JHJG,"
						+ "p.SFSZGC from bu_spyw_cbfasp p where p.ywlz_uid ='"
						+ ywlzid + "' and p.lx='sg' )";
				DBUtil.exec(conn, gcsql);

				// 绑定单位工程和施工内容
				String updateCbfasp = "update bu_spyw_cbfasp set GONGCHENG_UID='"
						+ gcid
						+ "' WHERE ywlz_uid='"
						+ ywlzid
						+ "' and lx='sg' ";
				DBUtil.exec(conn, updateCbfasp);

				// 绑定单位工程和施工内容
				String deleteRoleByAccountSql = "DELETE FROM PROJECTS_GONGCHENG_UNITS s WHERE s.GONGCHENG_UID="
						+ gcid;
				DBUtil.exec(conn, deleteRoleByAccountSql);
				String dwgcIds = res[0][1];
				String[] ids = dwgcIds.split(",");
				for (int i = 0; i < ids.length; i++) {
					ProjectsGongchengUnitsVO bdvo = new ProjectsGongchengUnitsVO();
					bdvo.setGongcheng_uid(gcid);
					bdvo.setUnits_uid(ids[i]);
					bdvo.setCreated_date(new Date());
					bdvo.setCreated_name(user.getAccount());
					bdvo.setCreated_uid(user.getUserSN());
					buSpYwlzBzDao.save(bdvo);
				}

				// 绑定其他 相关监理的单体 条件是监理的单体和施工内容单体需有一个相同的
				String queryJlCbfasp = "select c.dt_ids,c.BB_CODE,c.ywlz_uid,c.cbfasp_uid from bu_spyw_cbfasp c "
						+ "left join bu_sp_ywlz l on l.ywlz_uid=c.ywlz_uid "
						+ "where l.projects_uid='"
						+ pid
						+ "' and spyw_uid = '"
						+ Constants.JS_SPYW_ZJFB
						+ "' and c.lx = 'jl' and l.status = '1'  ";
				String[][] jlCbfasp = DBUtil.query(conn, queryJlCbfasp);
				if (jlCbfasp != null) {
					for (int i = 0; i < jlCbfasp.length; i++) { // 所有监理 cbfasp表
						String[] cbfaspDtId = jlCbfasp[i][0].split(","); // 单个监理
																			// cbfasp表的单体id[]
						boolean isbd = false;// 是否和施工有相同单体id
						for (int n = 0; n < cbfaspDtId.length; n++) {
							for (int j = 0; j < ids.length; j++) {
								if (cbfaspDtId[n].equals(ids[j])) { // 施工的单体id
																	// 和每一个监理
																	// cbfasp表
																	// 单体id
																	// 比较如果一样则绑定
									isbd = true;
									break;
								}
							}
							if (isbd) {
								break;
							}
						}
						if (isbd) { // 有相同的单体id 视为同一施工内容
							for (int x = 0; x < cbfaspDtId.length; x++) {// 邦定施工内容单体
								boolean save = false;
								for (int m = 0; m < ids.length; m++) {
									if (cbfaspDtId[x].equals(ids[m])) {
										save = true;
										break;
									}
								}
								if (!save) { // 重复的不绑定
									ProjectsGongchengUnitsVO bdvo = new ProjectsGongchengUnitsVO();
									bdvo.setGongcheng_uid(gcid);
									bdvo.setUnits_uid(cbfaspDtId[x]);
									bdvo.setCreated_date(new Date());
									bdvo.setCreated_name(user.getAccount());
									bdvo.setCreated_uid(user.getUserSN());
									buSpYwlzBzDao.save(bdvo);
								}
							}

							// 绑定施工内容监理报备
							String queryJlbb = "select * from dt_gc_jlbb where bb_code='"
									+ jlCbfasp[i][1]
									+ "' and gongcheng_uid='"
									+ gcid + "' ";
							String[][] jlbb = DBUtil.query(conn, queryJlbb);
							if (jlbb == null) { // 不重复添加
								String jlbbsql = "SELECT S.JLBB_UID FROM JLBB S WHERE  S.BB_CODE= '" // //
																										// WHERE
																										// S.status=2
																										// and
																										// S.BB_CODE=
										+ jlCbfasp[i][1] + "' ";
								String[][] bbres = DBUtil.query(conn, jlbbsql);
								String jlbbid = bbres[0][0];
								DtGcJlbbVO mvo = new DtGcJlbbVO();

								mvo.setDt_gc_jlbb_uid(DBUtil
										.getSequenceValue("DT_GC_JLBB_UID")); // 主键
								mvo.setGongcheng_uid(gcid);
								mvo.setCreated_date(new Date());
								mvo.setCreated_name(user.getName());
								mvo.setCreated_uid(user.getUserSN());
								mvo.setJlbb_uid(jlbbid);
								mvo.setBb_code(jlCbfasp[i][1]);

								String insql = "insert into dt_gc_jlbb(dt_gc_jlbb_uid,gongcheng_uid,created_uid,created_name,CREATED_DATE,jlbb_uid,bb_code)"
										+ " values('"
										+ mvo.getDt_gc_jlbb_uid()
										+ "','"
										+ mvo.getGongcheng_uid()
										+ "','"
										+ user.getIdCard()
										+ "','"
										+ user.getName()
										+ "',sysdate,'"
										+ mvo.getJlbb_uid()
										+ "','"
										+ mvo.getBb_code() + "')";
								// 插入
								DBUtil.exec(conn, insql);

								String delsql = "delete from DT_GC_JLry where gongcheng_uid = '"
										+ mvo.getGongcheng_uid() + "'";
								DBUtil.exec(conn, delsql);
								String addRys = "insert into DT_GC_JLry(DT_GC_JLRY_UID,GONGCHENG_UID,CREATED_UID,CREATED_NAME,CREATED_DATE,JLBB_JLY_UID,JL_PERSON_UID) "
										+ "select DT_GC_JLRY_UID.Nextval,'"
										+ mvo.getGongcheng_uid()
										+ "','"
										+ user.getIdCard()
										+ "','"
										+ user.getName()
										+ "',sysdate ,JLBB_JLY_UID,JL_PERSON_UID from JLBB_JLY where jlbb_uid = '"
										+ jlbbid + "'";
								DBUtil.exec(conn, addRys);
							}
						}
					}
				}

				// 绑定施工报备
				String sgbbsql = "SELECT S.SGBB_UID FROM SGBB S WHERE  S.BB_CODE='" // WHERE
																					// S.status=10
																					// and
																					// S.BB_CODE=
						+ res[0][2] + "'";
				String[][] bbres = DBUtil.query(conn, sgbbsql);
				String sgbbid = bbres[0][0];
				DtGcSgbbVO vo = new DtGcSgbbVO();
				vo.setBd_gcsgbb_uid(DBUtil.getSequenceValue("BD_GCSGBB_UID")); // 主键
				vo.setGongcheng_uid(gcid);
				vo.setCreated_date(new Date());
				vo.setCreated_name(user.getName());
				vo.setCreated_uid(user.getUserSN());
				vo.setSgbb_uid(sgbbid);
				vo.setBb_code(res[0][2]);

				// 插入
				buSpYwlzBzDao.save(vo);

				String delsql = "delete from DT_GC_SGRY where gongcheng_uid = '"
						+ gcid + "'";
				DBUtil.exec(conn, delsql);
				String addRys = "insert into DT_GC_SGRY(DT_GC_SGRY_UID,GONGCHENG_UID,CREATED_UID,CREATED_NAME,CREATED_DATE,SGBB_RY_UID,SG_PERSON_UID) "
						+ "select DT_GC_SGRY_UID.Nextval,'"
						+ vo.getGongcheng_uid()
						+ "','"
						+ user.getIdCard()
						+ "','"
						+ user.getAccount()
						+ "',sysdate ,SGBB_RY_UID,SG_PERSON_UID from SGBB_RY where SGBB_UID = '"
						+ sgbbid + "'";
				DBUtil.exec(conn, addRys);
			}

			if (resjl != null) { // 监理处理

				// 修改报备为已中标
				String updateJlbb = "update jlBB set status='" + 20
						+ "' WHERE bb_code='" + resjl[0][2] + "' ";
				DBUtil.exec(conn, updateJlbb);

				String dwgcIds = resjl[0][1];
				String[] jlDt = dwgcIds.split(","); // 监理 单体 ID
				String querySgCbfasp = "select c.dt_ids,c.BB_CODE,c.GONGCHENG_UID,c.ywlz_uid,c.cbfasp_uid from bu_spyw_cbfasp c "
						+ "left join bu_sp_ywlz l on l.ywlz_uid=c.ywlz_uid "
						+ "where l.projects_uid='"
						+ pid
						+ "' and spyw_uid = '"
						+ Constants.JS_SPYW_ZJFB
						+ "' and c.lx = 'sg'  and l.status = '1' ";
				String[][] sgCbfasp = DBUtil.query(conn, querySgCbfasp); // 所有施工
																			// cbfasp表

				if (sgCbfasp != null) {
					for (int i = 0; i < sgCbfasp.length; i++) {
						String[] cbfaspDtId = sgCbfasp[i][0].split(","); // 单个施工
																			// cbfasp表的单体id[]
						boolean isbd = false;// 是否和施工有相同单体id
						for (int n = 0; n < jlDt.length; n++) {
							for (int j = 0; j < cbfaspDtId.length; j++) {
								if (jlDt[n].equals(cbfaspDtId[j])) { // 监理的单体id
																		// 和每一个施工
																		// cbfasp表
																		// 单体id
																		// 比较如果一样则绑定
									isbd = true;
									break;
								}
							}
							if (isbd) {
								break;
							}
						}
						if (isbd) {
							for (int x = 0; x < jlDt.length; x++) {// 邦定施工内容单体
								boolean save = false;
								for (int m = 0; m < cbfaspDtId.length; m++) {
									if (jlDt[x].equals(cbfaspDtId[m])) {
										save = true;
										break;
									}
								}
								if (!save) { // 重复的不绑定
									ProjectsGongchengUnitsVO bdvo = new ProjectsGongchengUnitsVO();
									bdvo.setGongcheng_uid(sgCbfasp[i][2]);
									bdvo.setUnits_uid(jlDt[x]);
									bdvo.setCreated_date(new Date());
									bdvo.setCreated_name(user.getAccount());
									bdvo.setCreated_uid(user.getUserSN());
									buSpYwlzBzDao.save(bdvo);
								}
							}

							// 绑定施工内容监理报备
							String queryJlbb = "select * from dt_gc_jlbb where bb_code='"
									+ resjl[0][2]
									+ "' and gongcheng_uid='"
									+ sgCbfasp[i][2] + "' ";
							String[][] jlbb = DBUtil.query(conn, queryJlbb);
							if (jlbb == null) { // 不重复添加
								String jlbbsql = "SELECT S.JLBB_UID FROM JLBB S WHERE S.status=2 and S.BB_CODE= '"
										+ resjl[0][2] + "' ";
								String[][] bbres = DBUtil.query(conn, jlbbsql);
								String jlbbid = bbres[0][0];
								DtGcJlbbVO mvo = new DtGcJlbbVO();

								mvo.setDt_gc_jlbb_uid(DBUtil
										.getSequenceValue("DT_GC_JLBB_UID")); // 主键
								mvo.setGongcheng_uid(sgCbfasp[i][2]);
								mvo.setCreated_date(new Date());
								mvo.setCreated_name(user.getName());
								mvo.setCreated_uid(user.getUserSN());
								mvo.setJlbb_uid(jlbbid);
								mvo.setBb_code(resjl[0][2]);

								String insql = "insert into dt_gc_jlbb(dt_gc_jlbb_uid,gongcheng_uid,created_uid,created_name,CREATED_DATE,jlbb_uid,bb_code)"
										+ " values('"
										+ mvo.getDt_gc_jlbb_uid()
										+ "','"
										+ mvo.getGongcheng_uid()
										+ "','"
										+ user.getIdCard()
										+ "','"
										+ user.getName()
										+ "',sysdate,'"
										+ mvo.getJlbb_uid()
										+ "','"
										+ mvo.getBb_code() + "')";
								// 插入
								DBUtil.exec(conn, insql);

								String delsql = "delete from DT_GC_JLry where gongcheng_uid = '"
										+ mvo.getGongcheng_uid() + "'";
								DBUtil.exec(conn, delsql);
								String addRys = "insert into DT_GC_JLry(DT_GC_JLRY_UID,GONGCHENG_UID,CREATED_UID,CREATED_NAME,CREATED_DATE,JLBB_JLY_UID,JL_PERSON_UID) "
										+ "select DT_GC_JLRY_UID.Nextval,'"
										+ mvo.getGongcheng_uid()
										+ "','"
										+ user.getIdCard()
										+ "','"
										+ user.getName()
										+ "',sysdate ,JLBB_JLY_UID,JL_PERSON_UID from JLBB_JLY where jlbb_uid = '"
										+ jlbbid + "'";
								DBUtil.exec(conn, addRys);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("审批业务审核后特殊处理绑定施工内容和单位工程,绑定sg报备出错{}", e.getMessage());
		} finally {
			DBUtil.closeConnetion(conn);
		}

	}

	public void saveDwgcSgnrGkfb(String ywlzid, String pid) throws Exception { // 公开发包
																				// 业务特殊处理
		User user = ActionContext.getCurrentUserInThread();
		Connection conn = DBUtil.getConnection();
		try {

			// 施工
			String sql = "select p.CBFASP_GKZB_UID,p.dt_ids,p.bb_code from BU_SPYW_CBFASP_GKZB p where p.ywlz_uid ='"
					+ ywlzid + "' and p.lx='sg' ";
			String[][] res = DBUtil.query(conn, sql);

			// 监理
			String sqljl = "select p.CBFASP_GKZB_UID,p.dt_ids,p.bb_code from BU_SPYW_CBFASP_GKZB p where p.ywlz_uid ='"
					+ ywlzid + "' and p.lx='jl' ";
			String[][] resjl = DBUtil.query(conn, sqljl);

			if (res != null) { // 施工操作

				// 修改报备为已中标
				String updateSgbb = "update sgBB set status='" + 20
						+ "' WHERE bb_code='" + res[0][2] + "' ";
				DBUtil.exec(conn, updateSgbb);

				String gcid = DBUtil.getSequenceValue("GONGCHENG_UID");// id

				// 生成工程code
				String p_code = projectsService.findById(pid)
						.getProjects_code();
				String psql = "select count(*) from projects_gongcheng where projects_uid="
						+ pid + " and status=10";
				String[][] temp = DBUtil.query(conn, psql);
				int count = Integer.parseInt(temp[0][0]) + 1;
				if (count < 10) {
					p_code += "N0" + count;
				} else {
					p_code += "N" + count;
				}

				// 新增施工内容
				String gcsql = "insert into projects_gongcheng"
						+ "(SHENHE_REN,SHENHE_DATE,SHENHE_JIEGUO,STATUS,GONGCHENG_UID,PROJECTS_UID,CREATED_UID,CREATED_NAME,"
						+ "CREATED_DATE,UPDATE_UID,UPDATE_NAME,UPDATE_DATE,GONGCHENG_CODE,NEIRONG,"
						+ "GONGCHENG_NAME,CB_XINGZHI,BID_TYPE,MIANJI,CENGSHU,GAODU,KUADU,SHENDU,JINE,"
						+ "ZHONGLIANG,KG_DATE,WG_DATE,JG_DATE,SFSZGC)"
						+ "(select '"
						+ user.getUserSN()
						+ "',sysdate,'1','10',"
						+ gcid
						+ ",'"
						+ pid
						+ "',p.CREATED_UID,p.CREATED_NAME,"
						+ "p.CREATED_DATE,p.UPDATE_UID,p.UPDATE_NAME,p.UPDATE_DATE,'"
						+ p_code
						+ "',"
						+ "p.NEIRONG,p.GONGCHENG_NAME,p.CB_XINGZHI,p.BID_TYPE,p.MIANJI,p.CENGSHU,p.GAODU,"
						+ "p.KUADU,p.SHENDU,p.JINE,p.ZHONGLIANG,p.KG_DATE,p.WG_DATE,p.JG_DATE,"
						+ "p.SFSZGC from BU_SPYW_CBFASP_GKZB p where p.ywlz_uid ='"
						+ ywlzid + "' and p.lx='sg' )";
				DBUtil.exec(conn, gcsql);

				// 绑定单位工程和施工内容
				String updateCbfasp = "update BU_SPYW_CBFASP_GKZB set GONGCHENG_UID='"
						+ gcid
						+ "' WHERE ywlz_uid='"
						+ ywlzid
						+ "' and lx='sg' ";
				DBUtil.exec(conn, updateCbfasp);

				// 绑定单位工程和施工内容
				String deleteRoleByAccountSql = "DELETE FROM PROJECTS_GONGCHENG_UNITS s WHERE s.GONGCHENG_UID="
						+ gcid;
				DBUtil.exec(conn, deleteRoleByAccountSql);
				String dwgcIds = res[0][1];
				String[] ids = dwgcIds.split(",");
				for (int i = 0; i < ids.length; i++) {
					ProjectsGongchengUnitsVO bdvo = new ProjectsGongchengUnitsVO();
					bdvo.setGongcheng_uid(gcid);
					bdvo.setUnits_uid(ids[i]);
					bdvo.setCreated_date(new Date());
					bdvo.setCreated_name(user.getAccount());
					bdvo.setCreated_uid(user.getUserSN());
					buSpYwlzBzDao.save(bdvo);
				}

				// 绑定其他 相关监理的单体 条件是监理的单体和施工内容单体需有一个相同的
				String queryJlCbfasp = "select c.dt_ids,c.BB_CODE,c.ywlz_uid,c.cbfasp_gkzb_uid from BU_SPYW_CBFASP_GKZB c "
						+ "left join bu_sp_ywlz l on l.ywlz_uid=c.ywlz_uid "
						+ "where l.projects_uid='"
						+ pid
						+ "' and spyw_uid = '"
						+ Constants.JS_SPYW_GKFB
						+ "' and c.lx = 'jl' and l.status = '1'  ";
				String[][] jlCbfasp = DBUtil.query(conn, queryJlCbfasp);
				if (jlCbfasp != null) {
					for (int i = 0; i < jlCbfasp.length; i++) { // 所有监理 cbfasp表
						String[] cbfaspDtId = jlCbfasp[i][0].split(","); // 单个监理
																			// cbfasp表的单体id[]
						boolean isbd = false;// 是否和施工有相同单体id
						for (int n = 0; n < cbfaspDtId.length; n++) {
							for (int j = 0; j < ids.length; j++) {
								if (cbfaspDtId[n].equals(ids[j])) { // 施工的单体id
																	// 和每一个监理
																	// cbfasp表
																	// 单体id
																	// 比较如果一样则绑定
									isbd = true;
									break;
								}
							}
							if (isbd) {
								break;
							}
						}
						if (isbd) { // 有相同的单体id 视为同一施工内容
							for (int x = 0; x < cbfaspDtId.length; x++) {// 邦定施工内容单体
								boolean save = false;
								for (int m = 0; m < ids.length; m++) {
									if (cbfaspDtId[x].equals(ids[m])) {
										save = true;
										break;
									}
								}
								if (!save) { // 重复的不绑定
									ProjectsGongchengUnitsVO bdvo = new ProjectsGongchengUnitsVO();
									bdvo.setGongcheng_uid(gcid);
									bdvo.setUnits_uid(cbfaspDtId[x]);
									bdvo.setCreated_date(new Date());
									bdvo.setCreated_name(user.getAccount());
									bdvo.setCreated_uid(user.getUserSN());
									buSpYwlzBzDao.save(bdvo);
								}
							}

							// 绑定施工内容监理报备
							String queryJlbb = "select * from dt_gc_jlbb where bb_code='"
									+ jlCbfasp[i][1]
									+ "' and gongcheng_uid='"
									+ gcid + "' ";
							String[][] jlbb = DBUtil.query(conn, queryJlbb);
							if (jlbb == null) { // 不重复添加
								String jlbbsql = "SELECT S.JLBB_UID FROM JLBB S WHERE S.status=2 and S.BB_CODE= '"
										+ jlCbfasp[i][1] + "' ";
								String[][] bbres = DBUtil.query(conn, jlbbsql);
								String jlbbid = bbres[0][0];
								DtGcJlbbVO mvo = new DtGcJlbbVO();

								mvo.setDt_gc_jlbb_uid(DBUtil
										.getSequenceValue("DT_GC_JLBB_UID")); // 主键
								mvo.setGongcheng_uid(gcid);
								mvo.setCreated_date(new Date());
								mvo.setCreated_name(user.getName());
								mvo.setCreated_uid(user.getUserSN());
								mvo.setJlbb_uid(jlbbid);
								mvo.setBb_code(jlCbfasp[i][1]);

								String insql = "insert into dt_gc_jlbb(dt_gc_jlbb_uid,gongcheng_uid,created_uid,created_name,CREATED_DATE,jlbb_uid,bb_code)"
										+ " values('"
										+ mvo.getDt_gc_jlbb_uid()
										+ "','"
										+ mvo.getGongcheng_uid()
										+ "','"
										+ user.getIdCard()
										+ "','"
										+ user.getName()
										+ "',sysdate,'"
										+ mvo.getJlbb_uid()
										+ "','"
										+ mvo.getBb_code() + "')";
								// 插入
								DBUtil.exec(conn, insql);

								String delsql = "delete from DT_GC_JLry where gongcheng_uid = '"
										+ mvo.getGongcheng_uid() + "'";
								DBUtil.exec(conn, delsql);
								String addRys = "insert into DT_GC_JLry(DT_GC_JLRY_UID,GONGCHENG_UID,CREATED_UID,CREATED_NAME,CREATED_DATE,JLBB_JLY_UID,JL_PERSON_UID) "
										+ "select DT_GC_JLRY_UID.Nextval,'"
										+ mvo.getGongcheng_uid()
										+ "','"
										+ user.getIdCard()
										+ "','"
										+ user.getName()
										+ "',sysdate ,JLBB_JLY_UID,JL_PERSON_UID from JLBB_JLY where jlbb_uid = '"
										+ jlbbid + "'";
								DBUtil.exec(conn, addRys);
							}
						}
					}
				}

				// 绑定施工报备
				String sgbbsql = "SELECT S.SGBB_UID FROM SGBB S WHERE S.BB_CODE='" // WHERE
																					// S.status=10
																					// and
																					// S.BB_CODE=
						+ res[0][2] + "'";
				String[][] bbres = DBUtil.query(conn, sgbbsql);
				String sgbbid = bbres[0][0];
				DtGcSgbbVO vo = new DtGcSgbbVO();
				vo.setBd_gcsgbb_uid(DBUtil.getSequenceValue("BD_GCSGBB_UID")); // 主键
				vo.setGongcheng_uid(gcid);
				vo.setCreated_date(new Date());
				vo.setCreated_name(user.getName());
				vo.setCreated_uid(user.getUserSN());
				vo.setSgbb_uid(sgbbid);
				vo.setBb_code(res[0][2]);

				// 插入
				buSpYwlzBzDao.save(vo);

				String delsql = "delete from DT_GC_SGRY where gongcheng_uid = '"
						+ gcid + "'";
				DBUtil.exec(conn, delsql);
				String addRys = "insert into DT_GC_SGRY(DT_GC_SGRY_UID,GONGCHENG_UID,CREATED_UID,CREATED_NAME,CREATED_DATE,SGBB_RY_UID,SG_PERSON_UID) "
						+ "select DT_GC_SGRY_UID.Nextval,'"
						+ vo.getGongcheng_uid()
						+ "','"
						+ user.getIdCard()
						+ "','"
						+ user.getAccount()
						+ "',sysdate ,SGBB_RY_UID,SG_PERSON_UID from SGBB_RY where SGBB_UID = '"
						+ sgbbid + "'";
				DBUtil.exec(conn, addRys);
			}

			if (resjl != null) { // 监理处理

				// 修改报备为已中标
				String updateJlbb = "update jlBB set status='" + 20
						+ "' WHERE bb_code='" + resjl[0][2] + "' ";
				DBUtil.exec(conn, updateJlbb);

				String dwgcIds = resjl[0][1];
				String[] jlDt = dwgcIds.split(","); // 监理 单体 ID
				String querySgCbfasp = "select c.dt_ids,c.BB_CODE,c.GONGCHENG_UID,c.ywlz_uid,c.cbfasp_gkzb_uid from BU_SPYW_CBFASP_GKZB c "
						+ "left join bu_sp_ywlz l on l.ywlz_uid=c.ywlz_uid "
						+ "where l.projects_uid='"
						+ pid
						+ "' and spyw_uid = '"
						+ Constants.JS_SPYW_GKFB
						+ "' and c.lx = 'sg'  and l.status = '1' ";
				String[][] sgCbfasp = DBUtil.query(conn, querySgCbfasp); // 所有施工
																			// cbfasp表

				if (sgCbfasp != null) {
					for (int i = 0; i < sgCbfasp.length; i++) {
						String[] cbfaspDtId = sgCbfasp[i][0].split(","); // 单个施工
																			// cbfasp表的单体id[]
						boolean isbd = false;// 是否和施工有相同单体id
						for (int n = 0; n < jlDt.length; n++) {
							for (int j = 0; j < cbfaspDtId.length; j++) {
								if (jlDt[n].equals(cbfaspDtId[j])) { // 监理的单体id
																		// 和每一个施工
																		// cbfasp表
																		// 单体id
																		// 比较如果一样则绑定
									isbd = true;
									break;
								}
							}
							if (isbd) {
								break;
							}
						}
						if (isbd) {
							for (int x = 0; x < jlDt.length; x++) {// 邦定施工内容单体
								boolean save = false;
								for (int m = 0; m < cbfaspDtId.length; m++) {
									if (jlDt[x].equals(cbfaspDtId[m])) {
										save = true;
										break;
									}
								}
								if (!save) { // 重复的不绑定
									ProjectsGongchengUnitsVO bdvo = new ProjectsGongchengUnitsVO();
									bdvo.setGongcheng_uid(sgCbfasp[i][2]);
									bdvo.setUnits_uid(jlDt[x]);
									bdvo.setCreated_date(new Date());
									bdvo.setCreated_name(user.getAccount());
									bdvo.setCreated_uid(user.getUserSN());
									buSpYwlzBzDao.save(bdvo);
								}
							}

							// 绑定施工内容监理报备
							String queryJlbb = "select * from dt_gc_jlbb where bb_code='"
									+ resjl[0][2]
									+ "' and gongcheng_uid='"
									+ sgCbfasp[i][2] + "' ";
							String[][] jlbb = DBUtil.query(conn, queryJlbb);
							if (jlbb == null) { // 不重复添加
								String jlbbsql = "SELECT S.JLBB_UID FROM JLBB S WHERE  S.BB_CODE= '" // S.status=2
																										// and
																										// S.BB_CODE=
										+ resjl[0][2] + "' ";
								String[][] bbres = DBUtil.query(conn, jlbbsql);
								String jlbbid = bbres[0][0];
								DtGcJlbbVO mvo = new DtGcJlbbVO();

								mvo.setDt_gc_jlbb_uid(DBUtil
										.getSequenceValue("DT_GC_JLBB_UID")); // 主键
								mvo.setGongcheng_uid(sgCbfasp[i][2]);
								mvo.setCreated_date(new Date());
								mvo.setCreated_name(user.getName());
								mvo.setCreated_uid(user.getUserSN());
								mvo.setJlbb_uid(jlbbid);
								mvo.setBb_code(resjl[0][2]);

								String insql = "insert into dt_gc_jlbb(dt_gc_jlbb_uid,gongcheng_uid,created_uid,created_name,CREATED_DATE,jlbb_uid,bb_code)"
										+ " values('"
										+ mvo.getDt_gc_jlbb_uid()
										+ "','"
										+ mvo.getGongcheng_uid()
										+ "','"
										+ user.getIdCard()
										+ "','"
										+ user.getName()
										+ "',sysdate,'"
										+ mvo.getJlbb_uid()
										+ "','"
										+ mvo.getBb_code() + "')";
								// 插入
								DBUtil.exec(conn, insql);

								String delsql = "delete from DT_GC_JLry where gongcheng_uid = '"
										+ mvo.getGongcheng_uid() + "'";
								DBUtil.exec(conn, delsql);
								String addRys = "insert into DT_GC_JLry(DT_GC_JLRY_UID,GONGCHENG_UID,CREATED_UID,CREATED_NAME,CREATED_DATE,JLBB_JLY_UID,JL_PERSON_UID) "
										+ "select DT_GC_JLRY_UID.Nextval,'"
										+ mvo.getGongcheng_uid()
										+ "','"
										+ user.getIdCard()
										+ "','"
										+ user.getName()
										+ "',sysdate ,JLBB_JLY_UID,JL_PERSON_UID from JLBB_JLY where jlbb_uid = '"
										+ jlbbid + "'";
								DBUtil.exec(conn, addRys);
							}
						}
					}
				}
			}

		} catch (Exception e) {
			logger.error("审批业务审核后特殊处理绑定施工内容和单位工程,绑定sg报备出错{}", e.getMessage());
		} finally {
			DBUtil.closeConnetion(conn);
		}

	}

	public void updateDtgc(String ywlzid, String pid) throws Exception { // 申请许可证
																			// 业务特殊处理
		User user = ActionContext.getCurrentUserInThread();
		Connection conn = DBUtil.getConnection();
		try {

			String sql = "SELECT P.SGXKZSQ_UID,P.DT_IDS FROM BU_SPYW_JSGCSGXKZSQ P WHERE P.YWLZ_UID ='"
					+ ywlzid + "' ";
			String[][] res = DBUtil.query(conn, sql);
			if (res != null) { // 判断类型 施工
				String dwgcIds = res[0][1];
				String[] ids = dwgcIds.split(",");
				for (int i = 0; i < ids.length; i++) {
					ProjectsUnitsVO dwvo = new ProjectsUnitsVO();// 修改
																	// 申请许可证手续绑定的单位工程
																	// 不可修改
					dwvo.setUnits_uid(ids[i]);
					dwvo.setKxg("1");
					buSpYwlzBzDao.update(dwvo);
				}
			}
		} catch (Exception e) {
			logger.error("审批业务审核后{修改 申请许可证手续绑定的单位工程 不可修改 *****出错*** }",
					e.getMessage());
		} finally {
			DBUtil.closeConnetion(conn);
		}

	}

	/**
	 * 
	 * ywlzid 业务 bzcs 步骤次数 从0开始记开始步骤
	 * 
	 * 业务实例生成是调用即添加审批步骤的第一步记录数据 后面在步骤参与者审核通过后调用该方法
	 * */
	public String saveYwLzBzVo(String ywlzuid, int bzcs) throws Exception {
		String result = null;
		try {

			BuSpYwlzVO ywlzVo = buSpYwlzService.findById(ywlzuid);
			String spywid = ywlzVo.getSpyw_uid();
			List<?> spBzList = buSpBzService.getSpBzList(spywid, null);// SJ
																		// 收件、SH
																		// 审核、FJ
																		// 发件等
																		// -----20140701
																		// 去掉所有的

			// 流转步骤bzcs不得大于模版配置的步骤数
			if (spBzList != null && spBzList.size() > 0
					&& bzcs < spBzList.size()) {
				// if(spBzList!=null&&spBzList.size()>0&&(bzcs+1)<spBzList.size()){//20141111注释掉
				BuSpYwlzBzVO lzbzVo = new BuSpYwlzBzVO();
				// int size=spBzList.size();
				Map temmap = (Map) spBzList.get(bzcs);// 审核步骤的第几步（通过数据的排序来获取第几次数据）
				String spbzuid = (String) temmap.get("SPBZUID");// 审批步骤UID
				String bzmc = (String) temmap.get("BZMC");// 步骤名称
				String bzxh = (String) temmap.get("BZXH");// 步骤序号

				String clts = (String) temmap.get("CLTS");// 处理天数
				String tslx = (String) temmap.get("TSLX");// 天数类型 自然日或者工作日

				// String
				// cyzuid=(String)temmap.get("CYZUID");去掉实际处理人，只生成下一步的步骤数据，不涉及具体的参与处理步骤的人员

				lzbzVo.setYwlz_uid(ywlzuid);// 审批业务流转实例UID
				lzbzVo.setSpbz_uid(spbzuid);// 审批步骤UID
				lzbzVo.setBzmc(bzmc);// 步骤名称
				lzbzVo.setBzxh(bzxh);// 步骤序号
				// lzbzVo.setChuli_ren(cyzuid);//处理人

				lzbzVo.setStatus("0");// 0－流转中；1－已通过；-1－已退回
				lzbzVo.setBegin_date(new Date());
				lzbzVo.setChuli_jieguo("0");// 0待审中，1－已通过；-1－已退回

				Date planenddate = new Date();

				if (tslx.equals("GZR")) {// 工作日
					WorkdayUtils workdayUtils = new WorkdayUtils();
					planenddate = workdayUtils.getWorkday(new Date(),
							Integer.parseInt(clts));
					lzbzVo.setPlan_end_date(planenddate);// 计划结束时间，需要计算
				} else if (tslx.equals("ZRR")) {// 自然日
					Calendar c = Calendar.getInstance();
					c.setTime(new Date());
					c.add(Calendar.DAY_OF_MONTH, Integer.parseInt(clts));
					planenddate = c.getTime();
					lzbzVo.setPlan_end_date(planenddate);// 计划结束时间，需要计算
				}

				buSpYwlzBzDao.save(lzbzVo);
				result = lzbzVo.getRowJson();

				// 是否需要添加推送信息？？？
			} else if (bzcs >= spBzList.size()) {// 审批步骤数据记录小于传的步骤次数时则认为该次审核实例完成
													// 审核最后一步
				// }else if((bzcs+1)>=spBzList.size()){//20141111注释掉
				ywlzVo.setAct_end_date(new Date());
				ywlzVo.setStatus("1");// 0－流转中；1－已通过；-1－已退回
				buSpYwlzService.updateVo(ywlzVo);

				// **************************** 建设手续特殊业务 处理 需绑定 单位工程和施工内容
				if (Constants.JS_SPYW_ZJFB.equals(spywid)) {
					this.saveDwgcSgnrZjfb(ywlzVo.getYwlz_uid(),
							ywlzVo.getProjects_uid());
				} else if (Constants.JS_SPYW_GKFB.equals(spywid)) {
					this.saveDwgcSgnrGkfb(ywlzVo.getYwlz_uid(),
							ywlzVo.getProjects_uid());
				} else if (Constants.JS_SPYW_SQXKZ.equals(spywid)) {
					this.updateDtgc(ywlzVo.getYwlz_uid(),
							ywlzVo.getProjects_uid());
				}

				// **************************** 施工手续特殊业务 处理
				if (Constants.SG_SX_SZGCSB_YWID.equals(spywid)
						|| Constants.SG_SX_JZGCSB_YWID.equals(spywid)) { // 业务114
																			// 115
																			// 产生
																			// 危险源工程清单
					saveWxy(ywlzVo);// 危险源
				}

				// 审核完成 某些业务需自动产生 ----- 施工手续 114 115 自动生成下个业务
				if (Constants.SG_SX_SZGCSB_YWID.equals(spywid)
						|| Constants.SG_SX_JZGCSB_YWID.equals(spywid)
						|| Constants.SG_SX_KGQAQTJFC_YWID.equals(spywid)
						|| Constants.SG_SX_RLSBXT_YWID.equals(spywid)) {
					this.saveSgsxYw(ywlzVo, spywid); // 施工手续处理
				}

				// ****************************消息添加
				BuSpYwxxVO ywxxVo = buSpYwxxService.findById(spywid); // 消息推送 新增
																		// 施工工地
																		// 项目
																		// 手续办理推送

				if ("SG".equals(ywxxVo.getSpywlx())) {
					//
				} else {
					JsCompanyVO jsCompanyVO = companyService.findById(ywlzVo
							.getJs_company_uid());

					Map messageMap = new HashMap();
					if (ywlzVo.get("PROJECTS_UID") == null) { // 企业事务
						messageMap.put("TITLE", "建设企业企业事务审核");
						messageMap.put("CONTENT", "建设企业企业事务审核通过");
					} else {
						messageMap.put("TITLE", "建设企业业务审核");
						messageMap.put("CONTENT", "建设企业业务审核通过");
					}
					messageMap.put("USERTO", jsCompanyVO.getUser_name());
					messageMap.put("USERTONAME", jsCompanyVO.getCompany_name());
					messageMap.put("SYSTEM_TYPE", "JS");
					messageMap.put("COMPANY_UID", ywlzVo.getJs_company_uid());
					messageMap.put("MSG_TYPE",
							Constants.FS_MESSAGE_INFO_MSG_TYPE_JSSX);
					messageMap
							.put("QUANXIAN_UID", Constants.QUANXIAN_JS_JSSXSB);
					messageMap.put("MSG_VIEW_TYPE", "1");
					messageMap.put("PRM1", ywlzVo.getProjects_uid());
					messageMap.put("PRM2", ywlzuid);

					fsMessageInfoService.insertVo(messageMap);
				}
			}

		} catch (Exception e) {
			logger.error("审批业务流转步骤中步骤的初始化保存{}", e.getMessage());
			SystemException
					.handleMessageException("审批业务流转步骤中步骤的初始化保存失败,请联系相关人员处理");
		}
		return result;
	}

	/**
	 * 
	 * 页面主要数据有：审核状态，审核意见， 如果当前时间在计划时间后面的需要填写超时原因，
	 * 
	 * */
	public String updateYwLzBzVo(String json) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
		String reslut = null;
		BuSpYwlzBzVO bzVo = new BuSpYwlzBzVO();
		try {
			JSONArray list = bzVo.doInitJson(json);
			bzVo.setValueFromJson((JSONObject) list.get(0));

			bzVo.setChuli_ren(user.getUserSN());// 处理人 实际处理该次业务步骤的人员
												// user.getAccount()
												// 拿到的是users表的id 非办公人员的登录帐号
			bzVo.setAct_end_date(new Date());// 处理时间

			buSpYwlzBzDao.update(bzVo);

			reslut = bzVo.getRowJson();

			BuSpYwlzBzVO lzbzVo = this.findById(bzVo.getLzbz_uid());
			String chulijiegou = lzbzVo.getChuli_jieguo();

			/**
			 * chulijiegou 状态为1时，流转审核通过，则发送下一步骤参与人进行审核工作
			 * 1、通过ywlzuid获得该次审批业务流转实例的开始时间beginDate 2、通过beginDate 和ywlzuid 查
			 * BU_SP_YWLZ_BZ 获得审核通过的条数 chulijiegou 状态为-1时,
			 * 流转审核退回，修改该次审批业务流转实例状态， 不再提示步骤中下一步参与人进行审核工作
			 * */
			if (chulijiegou.equals("1")) {
				// BuSpYwlzBzVO tempVo = new BuSpYwlzBzVO();
				// JSONArray tempArr = tempVo.doInitJson(reslut);
				// tempVo.setValueFromJson((JSONObject) tempArr.get(0));
				String ywlzuid = lzbzVo.getYwlz_uid();

				BuSpYwlzVO ywlzVo = buSpYwlzService.findById(ywlzuid);
				Date beginDate = ywlzVo.getBegin_date();

				List<?> tempList = buSpYwlzBzDao.getSpYwLzBzList(ywlzuid,
						beginDate, null, "1", null);// 查询开始时间后面的且结果为申请通过的数据
				// List<?> tempList= buSpYwlzBzDao.getSpYwLzBzList(ywlzuid,
				// beginDate,"1",null,null);
				if (tempList != null && tempList.size() > 0) {
					// int bzcs= tempList.size()-1;
					this.saveYwLzBzVo(ywlzuid, tempList.size());// 初始化下一条审核步骤信息
				} else {// 添加原因。首次审核通过的时候，由于update动作未commit掉，tempList查询结果为0
						// this.saveYwLzBzVo(ywlzuid,1);//初始化下一条审核步骤信息
					logger.error("审批业务流转步骤更新{}",
							"ywlzywlzywlzywlzywlzywlzywlzywlzywlzywlzywlz0000000000000001");
				}

			} else if (chulijiegou.equals("-1")) {
				String ywlzuid = lzbzVo.getYwlz_uid();
				BuSpYwlzVO ywlzVo = buSpYwlzService.findById(ywlzuid);
				ywlzVo.setAct_end_date(new Date());
				ywlzVo.setStatus("-1");// 0－流转中；1－已通过；-1－已退回
				buSpYwlzService.updateVo(ywlzVo);

				// ****************************消息添加
				BuSpYwxxVO ywxxVo = buSpYwxxService.findById(ywlzVo
						.getSpyw_uid()); // 消息推送 新增 施工工地 项目 手续办理推送

				if ("SG".equals(ywxxVo.getSpywlx())) {
					//
				} else {
					JsCompanyVO jsCompanyVO = companyService.findById(ywlzVo
							.getJs_company_uid());

					Map messageMap = new HashMap();
					if (ywlzVo.get("PROJECTS_UID") == null) { // 企业事务
						messageMap.put("TITLE", "建设企业企业事务审核");
						messageMap.put("CONTENT", "建设企业企业事务审核未通过");
					} else {
						messageMap.put("TITLE", "建设企业业务审核");
						messageMap.put("CONTENT", "建设企业业务审核未通过");
					}
					messageMap.put("USERTO", jsCompanyVO.getUser_name());
					messageMap.put("USERTONAME", jsCompanyVO.getCompany_name());
					messageMap.put("SYSTEM_TYPE", "JS");
					messageMap.put("COMPANY_UID", ywlzVo.getJs_company_uid());
					messageMap.put("MSG_TYPE",
							Constants.FS_MESSAGE_INFO_MSG_TYPE_JSSX);
					messageMap
							.put("QUANXIAN_UID", Constants.QUANXIAN_JS_JSSXSB);
					messageMap.put("MSG_VIEW_TYPE", "2");
					messageMap.put("PRM1", ywlzVo.getProjects_uid());
					messageMap.put("PRM2", ywlzuid);
					fsMessageInfoService.insertVo(messageMap);
				}

			}
		} catch (Exception e) {
			logger.error("审批业务流转步骤更新{}", e);
			SystemException.handleMessageException("审批业务流转步骤更新失败,请联系相关人员处理");
		}
		return reslut;
	}

	public void saveWxy(BuSpYwlzVO ywlzVo) {
		Connection conn = DBUtil.getConnection();
		String domresult = "";
		try {

			String sql = "SELECT * FROM "
					+ "BU_SPYW_JSGCAQJCSQ t where YWLZ_UID = '"
					+ ywlzVo.getYwlz_uid() + "'";
			BaseResultSet bs = DBUtil.query(conn, sql, null);
			// 合同表
			// bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
			// 项目下达库
			// bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
			// 标段表
			// bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");

			// 设置字典
			bs.setFieldDic("TFKWGC_Y", "YOUWU");
			bs.setFieldDic("ZHICHENGGC_Y", "YOUWU");
			bs.setFieldDic("GLGJ_Y", "YOUWU");
			bs.setFieldDic("DLJSJGC_Y", "YOUWU");
			bs.setFieldDic("ZZYCPT_Y", "YOUWU");
			bs.setFieldDic("JSJGC_Y", "YOUWU");
			bs.setFieldDic("CCGC_Y", "YOUWU");
			bs.setFieldDic("GJWCCGC_Y", "YOUWU");
			bs.setFieldDic("YXCCGC_Y", "YOUWU");
			bs.setFieldDic("BPCCGC_Y", "YOUWU");
			bs.setFieldDic("BFJZCCGC_Y", "YOUWU");
			domresult = bs.getJson();
			JSONObject jasonObject = JSONObject.fromObject(domresult);
			JSONObject jasonObject2 = (JSONObject) jasonObject.get("response");
			JSONArray array = jasonObject2.getJSONArray("data");
			List<Map> list = (List) JSONArray.toCollection(array, Map.class);
			Map map = list.get(0);

			// 1
			if ((!map.get("KAIWA_DEEP").equals("") && Integer
					.parseInt((String) map.get("KAIWA_DEEP")) >= Constants.GCQD_KAIWA_DEEP)
					|| Constants.GCQD_TFKWGC_Y.equals(map.get("TFKWGC_Y_SV"))) {
				SgSpWxygcqdVO vo = new SgSpWxygcqdVO();
				vo.setWxygcqd_uid(DBUtil.getSequenceValue("WXYGCQD_UID"));
				vo.setGc_uid(ywlzVo.getProjects_uid());
				vo.setGc_type(Constants.GCQD_TYPE1);
				vo.setPrm1((String) map.get("KAIWA_DEEP"));
				vo.setPrm2((String) map.get("TFKWGC_Y_SV"));
				buSpYwlzBzDao.save(vo);
			}

			// 2
			if (Constants.GCQD_GLGJ_Y.equals(map.get("GLGJ_Y_SV"))
					|| (!map.get("TSGD").equals("") && Integer
							.parseInt((String) map.get("TSGD")) >= Constants.GCQD_TS_GD)
					|| (!map.get("TSKD").equals("") && Integer
							.parseInt((String) map.get("TSKD")) >= Constants.GCQD_TSKD)
					|| (!map.get("SGZZH").equals("") && Integer
							.parseInt((String) map.get("SGZZH")) >= Constants.GCQD_SGZZH)
					|| (!map.get("JZXZH").equals("") && Integer
							.parseInt((String) map.get("JZXZH")) >= Constants.GCQD_JZXZH)
					|| Constants.GCQD_ZHICHENGGC_Y.equals(map
							.get("ZHICHENGGC_Y_SV"))
					|| (!map.get("CSDDJZZH").equals("") && Integer
							.parseInt((String) map.get("CSDDJZZH")) >= Constants.GCQD_CSDDJZZH)) {
				SgSpWxygcqdVO vo = new SgSpWxygcqdVO();
				vo.setWxygcqd_uid(DBUtil.getSequenceValue("WXYGCQD_UID"));
				vo.setGc_uid(ywlzVo.getProjects_uid());
				vo.setGc_type(Constants.GCQD_TYPE2);
				vo.setPrm1((String) map.get("GLGJ_Y_SV"));
				vo.setPrm2((String) map.get("TSGD"));
				vo.setPrm3((String) map.get("TSKD"));
				vo.setPrm4((String) map.get("SGZZH"));
				vo.setPrm5((String) map.get("JZXZH"));
				vo.setPrm6((String) map.get("ZHICHENGGC_Y_SV"));
				vo.setPrm7((String) map.get("CSDDJZZH"));
				buSpYwlzBzDao.save(vo);
			}
			// 3
			if ((!map.get("DJQDZL").equals("") && Integer.parseInt((String) map
					.get("DJQDZL")) >= Constants.GCQD_DJQDZL)
					|| (!map.get("QZL").equals("") && Integer
							.parseInt((String) map.get("QZL")) >= Constants.GCQD_QZL)
					|| (!map.get("AZGD").equals("") && Integer
							.parseInt((String) map.get("AZGD")) >= Constants.GCQD_AZGD)) {
				SgSpWxygcqdVO vo = new SgSpWxygcqdVO();
				vo.setWxygcqd_uid(DBUtil.getSequenceValue("WXYGCQD_UID"));
				vo.setGc_uid(ywlzVo.getProjects_uid());
				vo.setGc_type(Constants.GCQD_TYPE3);
				vo.setPrm1((String) map.get("DJQDZL"));
				vo.setPrm2((String) map.get("QZL"));
				vo.setPrm3((String) map.get("AZGD"));
				buSpYwlzBzDao.save(vo);
			}
			// 4
			if ((!map.get("TS_GD").equals("") && Integer.parseInt((String) map
					.get("TS_GD")) >= Constants.GCQD_TS_GD)
					|| (!map.get("TISHENG_HEIGHT").equals("") && Integer
							.parseInt((String) map.get("TISHENG_HEIGHT")) >= Constants.GCQD_TISHENG_HEIGHT)
					|| (!map.get("JIATI_HEIGHT").equals("") && Integer
							.parseInt((String) map.get("JIATI_HEIGHT")) >= Constants.GCQD_JIATI_HEIGHT)
					|| Constants.GCQD_DLJSJGC_Y.equals(map.get("DLJSJGC_Y_SV"))
					|| Constants.GCQD_ZZYCPT_Y.equals(map.get("ZZYCPT_Y_SV"))
					|| Constants.GCQD_JSJGC_Y.equals(map.get("JSJGC_Y_SV"))) {
				SgSpWxygcqdVO vo = new SgSpWxygcqdVO();
				vo.setWxygcqd_uid(DBUtil.getSequenceValue("WXYGCQD_UID"));
				vo.setGc_uid(ywlzVo.getProjects_uid());
				vo.setGc_type(Constants.GCQD_TYPE4);
				vo.setPrm1((String) map.get("TS_GD"));
				vo.setPrm2((String) map.get("TISHENG_HEIGHT"));
				vo.setPrm3((String) map.get("JIATI_HEIGHT"));
				vo.setPrm4((String) map.get("DLJSJGC_Y_SV"));
				vo.setPrm5((String) map.get("ZZYCPT_Y_SV"));
				vo.setPrm6((String) map.get("JSJGC_Y_SV"));
				buSpYwlzBzDao.save(vo);

			}
			// 5
			if (Constants.GCQD_CCGC_Y.equals(map.get("CCGC_Y_SV"))
					|| Constants.GCQD_BPCCGC_Y.equals(map.get("BPCCGC_Y_SV"))
					|| Constants.GCQD_GJWCCGC_Y.equals(map.get("GJWCCGC_Y_SV"))
					|| Constants.GCQD_YXCCGC_Y.equals(map.get("YXCCGC_Y_SV"))
					|| Constants.GCQD_BFJZCCGC_Y.equals(map
							.get("BFJZCCGC_Y_SV"))) {
				SgSpWxygcqdVO vo = new SgSpWxygcqdVO();
				vo.setWxygcqd_uid(DBUtil.getSequenceValue("WXYGCQD_UID"));
				vo.setGc_uid(ywlzVo.getProjects_uid());
				vo.setGc_type(Constants.GCQD_TYPE5);
				vo.setPrm1((String) map.get("CCGC_Y_SV"));
				vo.setPrm2((String) map.get("BPCCGC_Y_SV"));
				vo.setPrm3((String) map.get("GJWCCGC_Y_SV"));
				vo.setPrm4((String) map.get("YXCCGC_Y_SV"));
				vo.setPrm5((String) map.get("BFJZCCGC_Y_SV"));
				buSpYwlzBzDao.save(vo);

			}

			// 6
			if ((!map.get("QT_SG_HEIGHT").equals("") && Integer
					.parseInt((String) map.get("QT_SG_HEIGHT")) >= Constants.GCQD_QT_SG_HEIGHT)) {
				SgSpWxygcqdVO vo = new SgSpWxygcqdVO();
				vo.setWxygcqd_uid(DBUtil.getSequenceValue("WXYGCQD_UID"));
				vo.setGc_uid(ywlzVo.getProjects_uid());
				vo.setGc_type(Constants.GCQD_TYPE6);
				vo.setPrm1((String) map.get("QT_SG_HEIGHT"));
				buSpYwlzBzDao.save(vo);
			}

			// 7
			if ((!map.get("QT_KD_GJG").equals("") && Integer
					.parseInt((String) map.get("QT_KD_GJG")) >= Constants.GCQD_QT_KD_GJG)) {
				SgSpWxygcqdVO vo = new SgSpWxygcqdVO();
				vo.setWxygcqd_uid(DBUtil.getSequenceValue("WXYGCQD_UID"));
				vo.setGc_uid(ywlzVo.getProjects_uid());
				vo.setGc_type(Constants.GCQD_TYPE7);
				vo.setGc_z_type(Constants.GCQD_TYPE7_GJG);
				vo.setPrm1((String) map.get("QT_KD_GJG"));
				buSpYwlzBzDao.save(vo);

			}

			// 7
			if ((!map.get("QT_KD_SMJG").equals("") && Integer
					.parseInt((String) map.get("QT_KD_SMJG")) >= Constants.GCQD_QT_KD_SMJG)) {
				SgSpWxygcqdVO vo = new SgSpWxygcqdVO();
				vo.setWxygcqd_uid(DBUtil.getSequenceValue("WXYGCQD_UID"));
				vo.setGc_uid(ywlzVo.getProjects_uid());
				vo.setGc_type(Constants.GCQD_TYPE7);
				vo.setGc_z_type(Constants.GCQD_TYPE7_MJ);
				vo.setPrm1((String) map.get("QT_KD_SMJG"));
				buSpYwlzBzDao.save(vo);

			}

		} catch (Exception e) {
			DaoException.handleMessageException("*********查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}

	}

	public String insertYwlzxx(String spyw, String project, String sguid)
			throws Exception {
		User user = ActionContext.getCurrentUserInThread();

		BuSpYwlzVO vo = new BuSpYwlzVO();
		String ywlz_uid = DBUtil.getSequenceValue("YWLZ_UID");
		vo.setYwlz_uid(ywlz_uid);
		vo.setSpyw_uid(spyw);
		vo.setProjects_uid(project);
		vo.setStatus("40");
		if (sguid != null) {
			vo.setSg_company_uid(sguid);// 施工企业
		}

		this.buSpYwlzBzDao.save(vo);
		return ywlz_uid;
	}

	public String geYwLzBzList(String ywlzuid) throws Exception {
		JSONArray arr = new JSONArray();
		try {
			List<?> tempList = buSpYwlzBzDao.getSpYwLzBzList(ywlzuid, null,
					null, null, null);
			if (tempList != null && tempList.size() > 0) {
				for (int i = 0; i < tempList.size(); i++) {
					Map temMap = (Map) tempList.get(i);
					JSONObject obj = new JSONObject();
					obj.put("LZBZ_UID", temMap.get("LZBZ_UID"));
					obj.put("YWLZ_UID", temMap.get("YWLZ_UID"));
					obj.put("SPBZ_UID", temMap.get("SPBZ_UID"));
					obj.put("BZMC", temMap.get("BZMC"));
					obj.put("BZXH", temMap.get("BZXH"));
					obj.put("STATUS", temMap.get("STATUS"));
					obj.put("BEGIN_DATE", temMap.get("BEGIN_DATE"));
					obj.put("PLAN_END_DATE", temMap.get("PLAN_END_DATE"));
					obj.put("ACT_END_DATE", temMap.get("ACT_END_DATE"));
					obj.put("CHAOSHI_YUANYIN", temMap.get("CHAOSHI_YUANYIN"));
					obj.put("CHULI_REN", temMap.get("CHULI_REN"));
					obj.put("CHULI_JIEGUO", temMap.get("CHULI_JIEGUO"));
					obj.put("CHULI_YIJIAN", temMap.get("CHULI_YIJIAN"));
					arr.add(obj);
				}
			}
		} catch (DaoException e) {
			logger.error("获取所有的审批业务流转步骤查询{}", e.getMessage());
			SystemException
					.handleMessageException("获取所有的审批业务流转步骤查询失败,请联系相关人员处理");
		} finally {
		}

		return arr.toString();
	}

	/**
	 * 办公人员点击审核时调用的方法 判断该条审核是否属于自己该审核的数据
	 * */
	public String getYwLzBzVo(String ywlzuid) throws Exception {
		// JSONArray arr = new JSONArray();
		User user = ActionContext.getCurrentUserInThread();
		JSONObject obj = new JSONObject();
		try {
			List<?> tempList = buSpYwlzBzDao.getSpYwLzBzList(ywlzuid, null,
					null, "0", user.getUserSN());
			if (tempList != null && tempList.size() > 0) {
				Map temMap = (Map) tempList.get(0);// 取一条，根据条件查询出来的数据只会有一条
				obj.put("LZBZ_UID", temMap.get("LZBZ_UID"));
				obj.put("YWLZ_UID", temMap.get("YWLZ_UID"));
				obj.put("SPBZ_UID", temMap.get("SPBZ_UID"));
				obj.put("BZMC", temMap.get("BZMC"));
				obj.put("BZXH", temMap.get("BZXH"));
				obj.put("STATUS", temMap.get("STATUS"));
				obj.put("BEGIN_DATE", temMap.get("BEGIN_DATE"));
				obj.put("PLAN_END_DATE", temMap.get("PLAN_END_DATE"));
				obj.put("ACT_END_DATE", temMap.get("ACT_END_DATE"));
				obj.put("CHAOSHI_YUANYIN", temMap.get("CHAOSHI_YUANYIN"));
				obj.put("CHULI_REN", temMap.get("CHULI_REN"));
				obj.put("CHULI_JIEGUO", temMap.get("CHULI_JIEGUO"));
				obj.put("CHULI_YIJIAN", temMap.get("CHULI_YIJIAN"));
				obj.put("SFCS", temMap.get("SFCS"));
				obj.put("ZS_NAME", temMap.get("ZS_NAME"));
				obj.put("LJR_NAME", temMap.get("LJR_NAME"));
				obj.put("LJR_DATE", temMap.get("LJR_DATE"));
				obj.put("LJR_PHONE", temMap.get("LJR_PHONE"));
				obj.put("FSZCZ_DATE", temMap.get("FSZCZ_DATE"));
				obj.put("ZS_CODE", temMap.get("ZS_CODE"));

			}

		} catch (DaoException e) {
			logger.error("根据ywlzuid查询审批业务流转步骤是否有操作参与的数据{}", e.getMessage());
			SystemException
					.handleMessageException("根据ywlzuid查询审批业务流转步骤是否有操作参与的数据失败,请联系相关人员处理");
		} finally {
		}

		return obj.toString();
	}

	public String queryYwLzBzLjrCode(String json) throws Exception {
		// JSONArray arr = new JSONArray();
		User user = ActionContext.getCurrentUserInThread();
		JSONObject obj = new JSONObject();
		String code = "";
		try {
			String sql = "select max(zs_code) from BU_SP_YWLZ_BZ where ywlz_uid in "
					+ "(select ywlz_uid from bu_sp_ywlz where spyw_uid='"
					+ Constants.SG_SX_JZGCSGZCZBA_YWID
					+ "' and begin_date "
					+ "between to_date((select Extract(year from sysdate)||'-01-01 00:00:00' from dual),'yyyy-mm-dd hh24:mi:ss') "
					+ "and to_date((select Extract(year from sysdate)||'-12-31 23:59:59' from dual),'yyyy-mm-dd hh24:mi:ss'))";
			String[][] res = DBUtil.querySql(sql);
			String pattern = "000";
			DecimalFormat df = new DecimalFormat();
			if (res == null || res[0][0].equals("")) {
				code = "001";
			} else {
				int index = Integer.parseInt(res[0][0]) + 1;
				code = df.format((index + ""));
			}
		} catch (DaoException e) {
			logger.error("查询审批业务流转步骤证书code{}", e.getMessage());
			SystemException
					.handleMessageException("根据ywlzuid查询审批业务流转步骤是否有操作参与的数据失败,请联系相关人员处理");
		} finally {
		}

		return code;
	}

	/**
	 * 办公人员点击审核时调用的方法 显示当前步骤 判断该条审核是否属于自己该审核的数据
	 * */
	public String getYwLzBzVoNoUser(String ywlzuid) throws Exception {
		// JSONArray arr = new JSONArray();
		User user = ActionContext.getCurrentUserInThread();
		JSONObject obj = new JSONObject();
		try {
			List<?> tempList = buSpYwlzBzDao.getSpYwLzBzList(ywlzuid, null,
					null, "0", null);
			if (tempList != null && tempList.size() > 0) {
				Map temMap = (Map) tempList.get(0);// 取一条，根据条件查询出来的数据只会有一条
				obj.put("LZBZ_UID", temMap.get("LZBZ_UID"));
				obj.put("YWLZ_UID", temMap.get("YWLZ_UID"));
				obj.put("SPBZ_UID", temMap.get("SPBZ_UID"));
				obj.put("BZMC", temMap.get("BZMC"));
				obj.put("BZXH", temMap.get("BZXH"));
				obj.put("STATUS", temMap.get("STATUS"));
				obj.put("BEGIN_DATE", temMap.get("BEGIN_DATE"));
				obj.put("PLAN_END_DATE", temMap.get("PLAN_END_DATE"));
				obj.put("ACT_END_DATE", temMap.get("ACT_END_DATE"));
				obj.put("CHAOSHI_YUANYIN", temMap.get("CHAOSHI_YUANYIN"));
				obj.put("CHULI_REN", temMap.get("CHULI_REN"));
				obj.put("CHULI_JIEGUO", temMap.get("CHULI_JIEGUO"));
				obj.put("CHULI_YIJIAN", temMap.get("CHULI_YIJIAN"));
				obj.put("SFCS", temMap.get("SFCS"));
			}

		} catch (DaoException e) {
			logger.error("根据ywlzuid查询审批业务流转步骤是否有操作参与的数据{}", e.getMessage());
			SystemException
					.handleMessageException("根据ywlzuid查询审批业务流转步骤是否有操作参与的数据失败,请联系相关人员处理");
		} finally {
		}

		return obj.toString();
	}

	// 通过YWLZUID来关联到审批业务信息下，查询出具体的那些材料文件
	public String geSpYwClList(String ywlzuid) throws Exception {
		JSONArray arr = new JSONArray();
		// User user = ActionContext.getCurrentUserInThread();

		try {
			// 通过业务流转UID关联项目再到企业,获取企业ID和登录帐号
			List<?> listte = buSpYwlzBzDao.geSpYwClList(ywlzuid);
			if (listte != null && listte.size() > 0) {
				for (int i = 0; i < listte.size(); i++) {
					JSONObject obj = new JSONObject();
					Map tem = (Map) listte.get(i);
					obj.put("YWLZ_UID", tem.get("YWLZ_UID"));
					obj.put("URL", tem.get("URL"));
					obj.put("SPYW_UID", tem.get("SPYW_UID"));
					obj.put("PROJECTS_UID", tem.get("PROJECTS_UID"));
					arr.add(obj);
				}
			}
		} catch (DaoException e) {
			logger.error("通过YWLZUID来关联查询审批业务信息的材料文件{}", e.getMessage());
			SystemException
					.handleMessageException("通过YWLZUID来关联查询审批业务信息的材料文件的查询失败,请联系相关人员处理");
		} finally {
		}

		return arr.toString();
	}

}
