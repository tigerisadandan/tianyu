/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    yhzg.service.ZgContentService.java
 * 创建日期： 2015-04-21 下午 01:24:35
 * 功能：    接口：需整改的安全隐患
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-21 下午 01:24:35  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.yhzg.service.impl;



import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;


import com.ccthanking.business.dtgl.yhzg.vo.ZgContentVO;
import com.ccthanking.business.dtgl.yhzg.vo.ZgTzdVO;
import com.ccthanking.business.yhzg.dao.ZgContentDao;
import com.ccthanking.business.yhzg.service.ZgContentService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> ZgContentService.java </p>
 * <p> 功能：需整改的安全隐患 </p>
 *
 * <p><a href="ZgContentService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-04-21
 * 
 */

@Service
public class ZgContentServiceImpl extends Base1ServiceImpl<ZgContentVO, String> implements ZgContentService {

	private static Logger logger = LoggerFactory.getLogger(ZgContentServiceImpl.class);
	
    
    private ZgContentDao zgContentDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = zgContentDao.queryCondition(json, null, null);

        }catch (DaoException e) {
        	logger.error("需整改的安全隐患{}", e.getMessage());
			SystemException.handleMessageException("需整改的安全隐患查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

        String resultVO = null;
        ZgTzdVO tvo = new ZgTzdVO();
        String zg_content_uid = "";
        try {
            JSONArray list = tvo.doInitJson(json);
            JSONObject obj = (JSONObject) list.get(0);
            String tzdUid = obj.getString("tzdUid");
            String wgsjUidstr = obj.getString("wgsjUidstr");
            if(null!=wgsjUidstr&&!"".equals(wgsjUidstr)){
            	String[] wgsjUids = wgsjUidstr.split(",");
            	for (int i = 0; i < wgsjUids.length; i++) {
            		StringBuffer sql = new StringBuffer();
            		zg_content_uid = DBUtil.getSequenceValue("ZG_CONTENT_UID");
            		sql.append(" insert into zg_content  ");
            		sql.append(" (zg_content_uid, zg_tzd_uid, zg_weigui_sj_uid, wg_miaoshu, xuhao, description) ");
            		sql.append(" select '"+zg_content_uid+"','"+tzdUid+"',t.zg_weigui_sj_uid,t.weigui_content, ");
            		sql.append(" '"+(i+1)+"','' from zg_weigui_sj t where t.zg_weigui_sj_uid = '"+wgsjUids[i]+"' ");

            		//ZgContentVO vo = new ZgContentVO();
            		//vo.setZg_tzd_uid(tzdUid);
					//vo.setZg_weigui_sj_uid(wgsjUids[i]);
					//vo.setXuhao(i+1+"");
					//vo.setDescription("");
					//zgContentDao.save(vo);
            		System.out.println(sql);
            		DBUtil.exec(sql.toString());
				}
            }

            // 插入
			//zgContentDao.save(vo);
            //resultVO = vo.getRowJson();
            this.zgContentDao.queryScore(tzdUid);
            
        } catch (DaoException e) {
            logger.error("需整改的安全隐患{}", e.getMessage());
            SystemException.handleMessageException("需整改的安全隐患新增失败,请联系相关人员处理");
        } finally {
        	
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        ZgContentVO vo = new ZgContentVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

          	//BusinessUtil.setUpdateCommonFields(vo, user);
            //vo.setYwlx(ywlx);
            //EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
			//vo.setSjbh(eventVO.getSjbh());

            // 修改
            zgContentDao.update(vo);
            resultVO = vo.getRowJson();


        } catch (DaoException e) {
            logger.error("需整改的安全隐患{}", e.getMessage());
            SystemException.handleMessageException("需整改的安全隐患修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		ZgContentVO vo = new ZgContentVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
			//vo.setSjbh(eventVO.getSjbh());

			//删除   根据据主键
			//zgContentDao.delete(ZgContentVO.class, vo.getId());

			resultVo = vo.getRowJson();

		} catch (DaoException e) {
            logger.error("需整改的安全隐患{}", e.getMessage());
            SystemException.handleMessageException("需整改的安全隐患删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("zgContentDaoImpl")
	public void setZgContentDao(ZgContentDao zgContentDao) {
		this.zgContentDao = zgContentDao;
	}

	public String queryByTzdUid(String tzdUid) {
		// TODO Auto-generated method stub
		return this.zgContentDao.queryByTzdUid(tzdUid);
	}

	public String queryScore(String tzdUid) {
		// TODO Auto-generated method stub
		return this.zgContentDao.queryScore(tzdUid);
	}

	public String getContent(String tzdUid) {
		// TODO Auto-generated method stub
		return this.zgContentDao.getContent(tzdUid);
	}

	public String getPicNum(String tzdUid) {
		// TODO Auto-generated method stub
		return this.zgContentDao.getPicNum(tzdUid);
	}
    
}
