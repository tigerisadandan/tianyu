/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    gzhgl.service.GzhRyService.java
 * 创建日期： 2015-05-31 下午 04:34:35
 * 功能：    接口：告知会参与人员
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-31 下午 04:34:35  宋帅   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dtgl.gzhgl.service.impl;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.common.BusinessUtil;
import com.ccthanking.common.EventManager;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.common.vo.EventVO;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.dtgl.gzhgl.vo.GzhRyVO;
import com.ccthanking.business.dtgl.gzhgl.dao.GzhDao;
import com.ccthanking.business.dtgl.gzhgl.dao.GzhRyDao;
import com.ccthanking.business.dtgl.gzhgl.service.GzhRyService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> GzhRyService.java </p>
 * <p> 功能：告知会参与人员 </p>
 *
 * <p><a href="GzhRyService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:songshuai@kzcpm.com">宋帅</a>
 * @version 0.1
 * @since 2015-05-31
 * 
 */

@Service
public class GzhRyServiceImpl extends Base1ServiceImpl<GzhRyVO, String> implements GzhRyService {

	private static Logger logger = LoggerFactory.getLogger(GzhRyServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.GZH_RY;
    
    private GzhRyDao gzhRyDao;
    private GzhDao gzhDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = gzhRyDao.queryCondition(json, null, null);

            LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_SUCCESS, user.getOrgDept().getDeptName()
                    + " " + user.getName() + "查询<告知会参与人员>", user, "", "");
            
        }catch (DaoException e) {
        	logger.error("告知会参与人员{}", e.getMessage());
			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user
					.getOrgDept().getDeptName() + " " + user.getName() + "告知会参与人员查询失败", user, "", "");
			SystemException.handleMessageException("告知会参与人员查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        GzhRyVO vo = new GzhRyVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
            // 插入
			gzhRyDao.save(vo);
            resultVO = vo.getRowJson();

			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("告知会参与人员{}", e.getMessage());
            SystemException.handleMessageException("告知会参与人员新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        GzhRyVO vo = new GzhRyVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */


            // 修改
            gzhRyDao.update(vo);
            resultVO = vo.getRowJson();

        

        } catch (DaoException e) {
            logger.error("告知会参与人员{}", e.getMessage());
            SystemException.handleMessageException("告知会参与人员修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		GzhRyVO vo = new GzhRyVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//删除   根据据主键
			gzhRyDao.delete(GzhRyVO.class, vo.getGzh_ry_uid());

			resultVo = vo.getRowJson();

			LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_SUCCESS, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "告知会参与人员删除成功", user, "", "");

		} catch (DaoException e) {
            logger.error("告知会参与人员{}", e.getMessage());
            LogManager.writeUserLog(user.getAccount(), ywlx, Globals.OPERATION_TYPE_DELETE, LogManager.RESULT_FAILURE, user.getOrgDept()
                    .getDeptName() + " " + user.getName() + "告知会参与人员删除失败", user, "", "");
            SystemException.handleMessageException("告知会参与人员删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}
    
    public String insertByQuery(String gzhId,String pIds1,String personIds,String type) throws Exception {
    	User user = ActionContext.getCurrentUserInThread();
    	
		String resultVo = null;
		String sql = "";
		try {
			if(type.equals("gzh")){
				sql = "select distinct sr.sg_person_uid,p.projects_uid,p.projects_name,sr.sg_name,gw.gangwei_uid from gzh_ry ry inner join projects p on  ry.project_uid = p.projects_uid inner join sgbb_ry sr on sr.sg_person_uid = ry.person_uid inner join gangwei gw on gw.gangwei_uid = ry.gangwei_uid where ry.gzh_uid = "+gzhId;
			}else if(type.equals("all")){
				sql = "select  distinct sr.sg_person_uid,p.projects_uid,p.projects_name,sr.sg_name,gw.gangwei_uid from projects p inner join projects_gongcheng pg on p.projects_uid = pg.projects_uid inner join dt_gc_sgry dgs on dgs.gongcheng_uid = pg.gongcheng_uid inner join sgbb_ry sr on sr.sg_person_uid = dgs.sg_person_uid inner join gangwei gw on gw.gangwei_uid  = sr.gangwei_uid where p.PROJECTS_UID in "+pIds1+" and pg.gc_status = 0 and gw.names in ('总监','安全监理','项目负责人','安全员')";
			}
			if(personIds!=null && !"".equals(personIds)){
				sql += " and sr.sg_person_uid not in "+personIds;
			}
			
			String res[][] =DBUtil.query(sql);
			//先删除原有数据，后添加
			sql = "delete gzh_ry where gzh_uid=?";
			DBUtil.executeUpdate(sql, new String[]{gzhId});
			if (res!=null) {
				
				for (int i = 0; i < res.length; i++) {
					GzhRyVO vo = new GzhRyVO();
					vo.setGzh_uid(gzhId);
					vo.setPerson_uid(res[i][0]);
					vo.setProject_uid(res[i][1]);
					vo.setProject_name(res[i][2]);
					vo.setPerson_name(res[i][3]);
					vo.setGangwei_uid(res[i][4]);
					gzhRyDao.save(vo);
				}
			}
			

		} catch (DaoException e) {
            logger.error("告知会参与人员{}", e.getMessage());
            SystemException.handleMessageException("告知会参与人员添加失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;
	}
    
    public String queryXmDwByGzhId(String json) throws Exception {
    	User user = ActionContext.getCurrentUserInThread();
        
        String domresult = "";
        try {
			domresult = gzhRyDao.queryXmDwByGzhId(json);
        }catch (DaoException e) {
        	logger.error("告知会项目单位{}", e.getMessage());
			SystemException.handleMessageException("告知会项目单位查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;
	}
    
    public String queryQianDaoRYByGzhId(String json) throws Exception {
		
    	User user = ActionContext.getCurrentUserInThread();
        
        String domresult = "";
        try {
			domresult = gzhRyDao.queryQianDaoRYByGzhId(json);
        }catch (DaoException e) {
        	logger.error("告知会参与人员{}", e.getMessage());
			SystemException.handleMessageException("告知会参与人员查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;
	}

	@Autowired
	@Qualifier("gzhRyDaoImpl")
	public void setGzhRyDao(GzhRyDao gzhRyDao) {
		this.gzhRyDao = gzhRyDao;
	}
	@Autowired
	@Qualifier("gzhDaoImpl")
	public void setGzhDao(GzhDao gzhDao) {
		this.gzhDao = gzhDao;
	}
 
}
