/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    wxy.service.WxySjkService.java
 * 创建日期： 2015-04-23 下午 02:31:00
 * 功能：    接口：基坑备案提示单
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-23 下午 02:31:00  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dtgl.wxy.service.impl;


import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import sun.java2d.pipe.SpanShapeRenderer.Simple;

import com.ccthanking.common.BusinessUtil;
import com.ccthanking.common.EventManager;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.common.util.FreemarkerHelper;
import com.ccthanking.common.util.Word2PDF;
import com.ccthanking.common.vo.EventVO;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.dtgl.wxy.vo.WxyGdmbVO;
import com.ccthanking.business.dtgl.wxy.vo.WxySjkVO;
import com.ccthanking.business.dtgl.wxy.dao.WxySjkDao;
import com.ccthanking.business.dtgl.wxy.service.WxySjkService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> WxySjkService.java </p>
 * <p> 功能：基坑备案提示单 </p>
 *
 * <p><a href="WxySjkService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-04-23
 * 
 */

@Service
public class WxySjkServiceImpl extends Base1ServiceImpl<WxySjkVO, String> implements WxySjkService {

	private static Logger logger = LoggerFactory.getLogger(WxySjkServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.WXY_SJK;
    
    private WxySjkDao wxySjkDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = wxySjkDao.queryCondition(json, null, null);
   
        }catch (DaoException e) {
        	logger.error("基坑备案提示单{}", e.getMessage());
			SystemException.handleMessageException("基坑备案提示单查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;
    }
    
    /**
     * 根据类型查询全部工程
     */
    public String queryAllGC(Map map) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    	Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {
        	String gcType = (String) map.get("gcType");
        	String stateType = (String) map.get("stateType");
        	String pname = (String) map.get("pname");
        	String gc_name = (String) map.get("gc_name");
        	String sg_name = (String) map.get("sg_name");
        	StringBuffer sql = new StringBuffer();
        	//根据不同的工程类型查询数据
        	if (gcType.equals("jkzhjs")) {//基坑支护降水
        		sql.append("select p.projects_name,(select jc.company_name from js_company jc where jc.js_company_uid = p.js_company_uid) cname,pg.gongcheng_uid,");
				sql.append(" ws.gc_name,ws.plan_b_date as bDate,ws.plan_e_date as eDate,ws.xmjl_name,ws.sg_name,ws.jl_name from WXY_SJK ws");
				sql.append(" inner join projects_gongcheng pg on pg.gongcheng_uid = ws.gongcheng_uid inner join projects p on p.projects_uid = pg.projects_uid");
				sql.append(" where 1=1 and ws.status = '"+stateType+"'");
				if (!"".equals(pname)) {
					sql.append(" and p.projects_name like '%"+pname+"%'");
				}
				if (!"".equals(gc_name)) {
					sql.append("  and pg.gongcheng_name like '%"+gc_name+"%'");
				}
				if (!"".equals(sg_name)) {
					sql.append("  and ws.sg_name like '%"+sg_name+"%'");
				}
			}else if(gcType.equals("gdmb")){//高大模板提示单
				sql.append(" select p.projects_name,(select jc.company_name from js_company jc where jc.js_company_uid = p.js_company_uid) cname, wg.gc_name,pg.gongcheng_uid,");
				sql.append(" wg.plan_b_date as bDate,wg.plan_e_date as eDate,wg.xmjl_name, gc_package.get_sgdw(wg.gongcheng_uid) as sg_name,gc_package.get_jldw(wg.gongcheng_uid) as jl_name");
				sql.append(" from WXY_GDMB wg inner join projects_gongcheng pg on pg.gongcheng_uid = wg.gongcheng_uid");
				sql.append(" inner join projects p on p.projects_uid = pg.projects_uid where 1=1 and wg.status = '"+stateType+"'");
				if (!"".equals(pname)) {
					sql.append(" and p.projects_name like '%"+pname+"%'");
				}
				if (!"".equals(gc_name)) {
					sql.append("  and pg.gongcheng_name like '%"+gc_name+"%'");
				}
				if (!"".equals(sg_name)) {
					sql.append("  and gc_package.get_sgdw(wg.gongcheng_uid) like '%"+sg_name+"%'");
				}
			}else if (gcType.equals("dzgc")) {//起重吊装安装拆卸
				sql.append("select p.projects_name,(select jc.company_name from js_company jc where jc.js_company_uid = p.js_company_uid) cname,pg.gongcheng_uid, wd.gc_name,");
				sql.append(" wd.plan_b_date as bDate,wd.plan_e_date as eDate,wd.xmjl_name,gc_package.get_sgdw(wd.gongcheng_uid) as sg_name,gc_package.get_jldw(wd.gongcheng_uid) as jl_name");
				sql.append("  from wxy_dzgc wd inner join projects_gongcheng pg on pg.gongcheng_uid = wd.gongcheng_uid inner join projects p on p.projects_uid = pg.projects_uid");
				sql.append(" where 1=1 and wd.status = '"+stateType+"'");
				if (!"".equals(pname)) {
					sql.append(" and p.projects_name like '%"+pname+"%'");
				}
				if (!"".equals(gc_name)) {
					sql.append("  and pg.gongcheng_name like '%"+gc_name+"%'");
				}
				if (!"".equals(sg_name)) {
					sql.append("  and gc_package.get_sgdw(wd.gongcheng_uid) like '%"+sg_name+"%'");
				}
			}else if (gcType.equals("jsj")) {//脚手架
				sql.append(" select p.projects_name,(select jc.company_name from js_company jc where jc.js_company_uid = p.js_company_uid) cname,pg.gongcheng_uid, wj.gc_name,");
				sql.append(" wj.plan_b_date as bDate,wj.plan_e_date as eDate,wj.xmjl_name,gc_package.get_sgdw(wj.gongcheng_uid) as sg_name,gc_package.get_jldw(wj.gongcheng_uid) as jl_name");
				sql.append("  from wxy_jsj wj inner join projects_gongcheng pg on pg.gongcheng_uid = wj.gongcheng_uid inner join projects p on p.projects_uid = pg.projects_uid");
				sql.append(" where 1=1 and wj.status = '"+stateType+"'");
				if (!"".equals(pname)) {
					sql.append(" and p.projects_name like '%"+pname+"%'");
				}
				if (!"".equals(gc_name)) {
					sql.append("  and pg.gongcheng_name like '%"+gc_name+"%'");
				}
				if (!"".equals(sg_name)) {
					sql.append("  and gc_package.get_sgdw(wj.gongcheng_uid) like '%"+sg_name+"%'");
				}
			}else if (gcType.equals("mq")) {//幕墙
				sql.append(" select p.projects_name,(select jc.company_name from js_company jc where jc.js_company_uid = p.js_company_uid) cname,pg.gongcheng_uid, wm.gc_name,");
				sql.append(" wm.plan_b_date as bDate,wm.plan_e_date as eDate,wm.xmjl_name,gc_package.get_sgdw(wm.gongcheng_uid) as sg_name,gc_package.get_jldw(wm.gongcheng_uid) as jl_name");
				sql.append("  from wxy_mq wm inner join projects_gongcheng pg on pg.gongcheng_uid = wm.gongcheng_uid inner join projects p on p.projects_uid = pg.projects_uid");
				sql.append(" where 1=1 and wm.status = '"+stateType+"'");
				if (!"".equals(pname)) {
					sql.append(" and p.projects_name like '%"+pname+"%'");
				}
				if (!"".equals(gc_name)) {
					sql.append("  and pg.gongcheng_name like '%"+gc_name+"%'");
				}
				if (!"".equals(sg_name)) {
					sql.append("  and gc_package.get_sgdw(wm.gongcheng_uid) like '%"+sg_name+"%'");
				}
			}else if (gcType.equals("gjg")) {//钢结构
				sql.append(" select p.projects_name,(select jc.company_name from js_company jc where jc.js_company_uid = p.js_company_uid) cname, pg.gongcheng_uid,wg.gc_name,");
				sql.append(" wg.plan_b_date as bDate,wg.plan_e_date as eDate,wg.xmjl_name,gc_package.get_sgdw(wg.gongcheng_uid) as sg_name,gc_package.get_jldw(wg.gongcheng_uid) as jl_name");
				sql.append("  from wxy_gjg wg inner join projects_gongcheng pg on pg.gongcheng_uid = wg.gongcheng_uid inner join projects p on p.projects_uid = pg.projects_uid");
				sql.append(" where 1=1 and wg.status = '"+stateType+"'");
				if (!"".equals(pname)) {
					sql.append(" and p.projects_name like '%"+pname+"%'");
				}
				if (!"".equals(gc_name)) {
					sql.append("  and pg.gongcheng_name like '%"+gc_name+"%'");
				}
				if (!"".equals(sg_name)) {
					sql.append("  and gc_package.get_sgdw(wg.gongcheng_uid) like '%"+sg_name+"%'");
				}
			}else if (gcType.equals("wjsm")) {//网架、索模结构安装
				sql.append("select p.projects_name,(select jc.company_name from js_company jc where jc.js_company_uid = p.js_company_uid) cname,pg.gongcheng_uid,");
				sql.append(" ww.gc_name,ww.plan_b_date as bDate,ww.plan_e_date as eDate,ww.xmjl_name,ww.sg_name,gc_package.get_jldw(ww.gongcheng_uid) as jl_name from WXY_WJ ww");
				sql.append(" inner join projects_gongcheng pg on pg.gongcheng_uid = ww.gongcheng_uid inner join projects p on p.projects_uid = pg.projects_uid");
				sql.append(" where 1=1 and ww.status = '"+stateType+"'");
				if (!"".equals(pname)) {
					sql.append(" and p.projects_name like '%"+pname+"%'");
				}
				if (!"".equals(gc_name)) {
					sql.append("  and pg.gongcheng_name like '%"+gc_name+"%'");
				}
				if (!"".equals(sg_name)) {
					sql.append("  and ww.sg_name like '%"+sg_name+"%'");
				}	
			}
        	
        	BaseResultSet bs = DBUtil.query(conn,sql.toString(),null);
			domresult = bs.getJson();
   
        }catch (DaoException e) {
        	logger.error("提示单{}", e.getMessage());
			SystemException.handleMessageException("提示单查询失败,请联系相关人员处理");
        } finally {
        	DBUtil.closeConnetion(conn);
        }
        return domresult;
        
        
    }
    
    /**
     * 根据类型查询项目总数量
     */
    public String queryGcCountByState(Map map) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    	Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {
        	String stateType = (String) map.get("stateType");
        	String[][] arr = null;
        	Map m = new HashMap();
        	String sql = "select count(0) from wxy_sjk where status ="+stateType;
        	arr = DBUtil.query(sql);
        	m.put("sjk", arr[0][0]);
        	sql = "select count(0) from wxy_gdmb where status ="+stateType;
        	arr = DBUtil.query(sql);
        	m.put("gdmb", arr[0][0]);
        	sql = "select count(0) from wxy_dzgc where status ="+stateType;
        	arr = DBUtil.query(sql);
        	m.put("dzgc", arr[0][0]);
        	sql = "select count(0) from wxy_jsj where status ="+stateType;
        	arr = DBUtil.query(sql);
        	m.put("jsj", arr[0][0]);
        	sql = "select count(0) from wxy_mq where status ="+stateType;
        	arr = DBUtil.query(sql);
        	m.put("mq", arr[0][0]);
        	sql = "select count(0) from wxy_gjg where status ="+stateType;
        	arr = DBUtil.query(sql);
        	m.put("gjg", arr[0][0]);
        	sql = "select count(0) from wxy_wj where status ="+stateType;
        	arr = DBUtil.query(sql);
        	m.put("wj", arr[0][0]);
        	JSONArray json = JSONArray.fromObject(m);
        	domresult = json.toString();
   
        }catch (DaoException e) {
        	logger.error("提示单数量查询{}", e.getMessage());
			SystemException.handleMessageException("提示单数量查询查询失败,请联系相关人员处理");
        } finally {
        	DBUtil.closeConnetion(conn);
        }
        return domresult;
        
        
    }
    
//  toword
    public String toword(HttpServletResponse response,String id) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

        	WxySjkVO vo = this.findById(id);
        	
        	SimpleDateFormat rqgs = new SimpleDateFormat("yyyy-MM-dd");
		    String cdate=rqgs.format(vo.getCreate_date());
		    String shdate=rqgs.format(vo.getShenhe_date());
		    String bdate=rqgs.format(vo.getPlan_b_date());
		    String edate=rqgs.format(vo.getPlan_e_date());
		    String ZJLZ_DATE=rqgs.format(vo.getZjlz_date());
		    vo.remove("CREATE_DATE");
		    vo.put("CREATE_DATE", cdate);
		    vo.remove("SHENHE_DATE");
		    vo.put("SHENHE_DATE", shdate);
		    vo.remove("ZJLZ_DATE");
		    vo.put("ZJLZ_DATE", ZJLZ_DATE);
		    vo.remove("PLAN_B_DATE");
		    vo.put("PLAN_B_DATE", bdate);
		    vo.remove("PLAN_E_DATE");
		    vo.put("PLAN_E_DATE", edate);
        	
			String filePath=Constants.getString(Constants.PATH_TEMPLATE_WORD,"");	//转前 ftl 路径
			String pdfPath=Constants.getString(Constants.PATH_TEMPLATE_XML,"");	//转后 pdf 路径 
			String ftlName="深基坑工程申报表";
			String workName="深基坑工程申报表"+vo.getWxy_sjk_uid()+".doc";
			FreemarkerHelper.createWord(vo, filePath, ftlName, pdfPath, workName);
			String filename =Constants.getString(Constants.PATH_TEMPLATE_XML,"")+"" +workName;
		    
			Word2PDF word2pdf = new Word2PDF();
			word2pdf.toPdf(filename);
			domresult= filename+".xml.pdf";
        }catch (DaoException e) {	
        	logger.error("深基坑工程申报表PDF{}", e.getMessage());
			SystemException.handleMessageException("深基坑工程申报表PDF失败,请联系相关人员处理");
        }
		return domresult;

    }
    
    public boolean tuiHui(String gcId) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		boolean flag = false;
        try {
             flag = wxySjkDao.tuiHui(gcId);
        } catch (DaoException e) {
            logger.error("基坑备案提示单{}", e.getMessage());
            SystemException.handleMessageException("基坑备案提示单修改失败,请联系相关人员处理");
        } finally {
        	
        }
        return flag;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        WxySjkVO vo = new WxySjkVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
         vo.setWxy_sjk_uid(DBUtil.getSequenceValue("WXY_SJK_UID"));
            // 插入
			wxySjkDao.save(vo);
            resultVO = vo.getRowJson();

       
			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("基坑备案提示单{}", e.getMessage());
            SystemException.handleMessageException("基坑备案提示单新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        WxySjkVO vo = new WxySjkVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

          	
            // 修改
            wxySjkDao.update(vo);
            resultVO = vo.getRowJson();

        
        } catch (DaoException e) {
            logger.error("基坑备案提示单{}", e.getMessage());
            SystemException.handleMessageException("基坑备案提示单修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		WxySjkVO vo = new WxySjkVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

		
			//删除   根据据主键
			wxySjkDao.delete(WxySjkVO.class, vo.getWxy_sjk_uid());

			resultVo = vo.getRowJson();

	
		} catch (DaoException e) {
            logger.error("基坑备案提示单{}", e.getMessage());
            SystemException.handleMessageException("基坑备案提示单删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("wxySjkDaoImpl")
	public void setWxySjkDao(WxySjkDao wxySjkDao) {
		this.wxySjkDao = wxySjkDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) wxySjkDao);
	}
    
}
