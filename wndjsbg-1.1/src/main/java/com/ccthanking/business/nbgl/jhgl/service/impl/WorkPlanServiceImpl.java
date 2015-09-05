/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    nbgl.jhgl.service.WorkPlanService.java
 * 创建日期： 2015-05-22 下午 12:00:14
 * 功能：    接口：计划管理
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-22 下午 12:00:14  喻敏   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.nbgl.jhgl.service.impl;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.directwebremoting.export.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.common.BusinessUtil;
import com.ccthanking.common.EventManager;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.common.util.FreemarkerHelper;
import com.ccthanking.common.util.Word2PDF;
import com.ccthanking.common.vo.EventVO;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.nbgl.jhgl.vo.WorkPlanVO;
import com.ccthanking.business.nbgl.jhgl.dao.WorkPlanDao;
import com.ccthanking.business.nbgl.jhgl.service.WorkPlanService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;
import com.ibm.icu.text.SimpleDateFormat;



/**
 * <p> WorkPlanService.java </p>
 * <p> 功能：计划管理 </p>
 *
 * <p><a href="WorkPlanService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:yuminchn@163.com">luhongang</a>
 * @version 0.1
 * @since 2015-05-22
 * 
 */
//计划管理 业务层实现类 
@Service
public class WorkPlanServiceImpl extends Base1ServiceImpl<WorkPlanVO, String> implements WorkPlanService {

	private static Logger logger = LoggerFactory.getLogger(WorkPlanServiceImpl.class);
    private WorkPlanDao workPlanDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    	User user = ActionContext.getCurrentUserInThread();
        String domresult = "";
        try {
			domresult = workPlanDao.queryCondition(json,null,null);
           // logger.info(domresult);
        }catch (DaoException e) {
        	logger.error("计划管理{}", e.getMessage());
			SystemException.handleMessageException("计划管理查询数据失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(List<Map<String, String>> list) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        WorkPlanVO vo = null;
        Integer status =null; 
        try {
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	Date createdata = new Date();
        	//获取 状态 数字 "1"为update  "2"为insert
        	for(Map<String,String> map:list){
        		status = Integer.parseInt(map.get("status"));
        	}
        	if(status == 2){//没有添加下周工作计划
        	for (Map<String,String> map:list) {
        		vo = new WorkPlanVO();
				vo.setPlan_content_am(map.get("contentam"));  //上午计划
				vo.setPlan_content_pm(map.get("contentpm"));  //下午计划
				vo.setCreate_by(user.getUserSN());  // 创建用户uid
				vo.setCreate_date(createdata); // 创建时间
				String plandate = map.get("day")+" 01:00:00";
				vo.setPlan_date(sdf.parse(plandate));  // 计划日期
				vo.setFankuiren_uid(map.get("deptId"));   //部门id
				vo.setFankuiren_name(map.get("name"));  // 部门名称
				vo.setUpdate_by(user.getUserSN());    //最后更新用户uid
				vo.setUpdate_date(createdata);  //  最后更新时间
				workPlanDao.save(vo);
			}
        	}else{
        		//更新 
        		for (Map<String,String> map:list) {
            		vo = new WorkPlanVO();
    				vo.setPlan_content_am(map.get("contentam"));  //上午计划
    				vo.setPlan_content_pm(map.get("contentpm"));  //下午计划
    				//vo.setCreate_by(user.getUserSN());  // 创建用户uid
    				//vo.setCreate_date(createdata); // 创建时间
    				String plandate = map.get("day")+" 01:00:00";
    				vo.setPlan_date(sdf.parse(plandate));  // 计划日期
    				//vo.setFankuiren_uid(map.get("deptId"));   //部门id
    				//vo.setFankuiren_name(map.get("name"));  // 部门名称
    				vo.setUpdate_by(user.getUserSN());    //最后更新用户uid
    				vo.setUpdate_date(createdata);  //  最后更新时间
    				vo.setWork_plan_uid(map.get("workId"));//获取 工作计划id
    				// 插入
    				workPlanDao.update(vo);
    			}
        	}
            resultVO = vo.getRowJson();
        } catch (DaoException e) {
            logger.error("计划管理{}", e.getMessage());
            SystemException.handleMessageException("添加下周计划查询失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        WorkPlanVO vo = new WorkPlanVO();

        try {
          	BusinessUtil.setUpdateCommonFields(vo, user);

            // 修改
            workPlanDao.update(vo);
            resultVO = vo.getRowJson();


        } catch (DaoException e) {
            logger.error("计划管理{}", e.getMessage());
            SystemException.handleMessageException("计划管理修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		WorkPlanVO vo = new WorkPlanVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);


			//删除   根据据主键
//			workPlanDao.delete(WorkPlanVO.class, vo.getId());

			resultVo = vo.getRowJson();

		} catch (DaoException e) {
            logger.error("计划管理{}", e.getMessage());
            SystemException.handleMessageException("计划管理删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}
    /**
     *  根据 传入 的时间 查询
     */
	public String queryByTime(String time) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
		String resultVo = null;
		try {
			resultVo = this.workPlanDao.getByTime(time);
		} catch (Exception e) {
			logger.error("获取周数{}",e.getMessage());
			SystemException.handleMessageException("更新时间失败,请联系相关人员处理");
		}
		
	return resultVo;
	}
    //通过时间段 查询当前用户所在 部门的下周计划
	public String queryByData(String timeStr) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
		String resultVo = null;
		try {
			resultVo = this.workPlanDao.getDataByTime(timeStr);
		} catch (Exception e) {
			logger.error("查询计划{}",e.getMessage());
			SystemException.handleMessageException("查询下周计划失败,请联系相关人员处理");
		}
		return resultVo;
	}
    //通过user_uid 查询员工部门ID
	public String queryOid(String user_uid) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
		String resultVo = null;
		try {
			resultVo = this.workPlanDao.getByOrgId(user_uid);
		} catch (Exception e) {
			logger.error("查询计划{}",e.getMessage());
			SystemException.handleMessageException("查询员工部门编号失败,请联系相关人员处理");
		}
		return resultVo;
	}

	public String queryCode(String deptId) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
		String resultVo = null;
		try {
			resultVo = this.workPlanDao.getDeptName(deptId);
		} catch (Exception e) {
			logger.error("查询计划{}",e.getMessage());
			SystemException.handleMessageException("查询部门名称失败,请联系相关人员处理");
		}
		return resultVo;
	}
    //查询 4个 部门的数据
	public String getDataForDept(String msg) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
		String resultVo = null;
		try {
			resultVo = this.workPlanDao.getDataForDept(msg);
		} catch (Exception e) {
			logger.error("查询计划{}",e.getMessage());
			SystemException.handleMessageException("查询数据失败,请联系相关人员处理");
		}
		return resultVo;
	}
	
	public String toword(HttpServletResponse response, String timeStr){
		Map<String, Object> map =  null;
		String week = null;
		String year = null;
		try {
			 if(timeStr.length()==23){
	            	week= timeStr.substring(0,1);
	            	year = timeStr.substring(2,6);
	            }else {
	            	week = timeStr.substring(0,2);
	            	year = timeStr.substring(3,7);
	            }	
			List<?> list = this.workPlanDao.findHeaderPrint(timeStr);
			if(list.size()!=0){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				map = (Map<String, Object>) list.get(0);
				map.put("TBLIST", list);
				map.put("WEEK",week);
			}
			String filePath=Constants.getString("PATH_TEMPLATE_WORD","");//转前 ftl路径
			String pdfPath=Constants.getString(Constants.PATH_TEMPLATE_XML,"");	//转后 pdf 路径
			String ftlName="无锡新区安监站周工作报表";//无锡新区建设工程现场监理部关键岗位人员配备表
			String workName=year+"年无锡新区安监站"+week+"周工作报表";// System.nanoTime()
			FreemarkerHelper.createWord(map, filePath, ftlName, pdfPath, workName);
			String filename =Constants.getString("PATH_TEMPLATE_XML","")+""+workName;
			
			
			Word2PDF word2pdf = new Word2PDF();
			word2pdf.toPdf(filename);
			return filename+".xml.pdf";//filename+".xml.pdf"
			
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

		
	}
	@Autowired
	@Qualifier("workPlanDaoImpl")
	public void setWorkPlanDao(WorkPlanDao workPlanDao) {
		this.workPlanDao = workPlanDao;
	}
	
    
}
