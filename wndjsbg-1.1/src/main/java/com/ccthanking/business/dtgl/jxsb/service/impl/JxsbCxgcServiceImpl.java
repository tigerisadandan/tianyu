/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jxsb.service.JxsbCxgcService.java
 * 创建日期： 2015-01-28 下午 06:05:57
 * 功能：    接口：机械设备拆卸过程
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-28 下午 06:05:57  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dtgl.jxsb.service.impl;


import java.util.Date;

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
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.dtgl.azqy.vo.JxsbCxgcVO;
import com.ccthanking.business.dtgl.azqy.vo.JxsbSyglVO;
import com.ccthanking.business.dtgl.azqy.vo.JxsbVO;
import com.ccthanking.business.dtgl.jxsb.dao.JxsbCxgcDao;
import com.ccthanking.business.dtgl.jxsb.service.JxsbCxgcService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> JxsbCxgcService.java </p>
 * <p> 功能：机械设备拆卸过程 </p>
 *
 * <p><a href="JxsbCxgcService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-01-28
 * 
 */

@Service
public class JxsbCxgcServiceImpl extends Base1ServiceImpl<JxsbCxgcVO, String> implements JxsbCxgcService {

	private static Logger logger = LoggerFactory.getLogger(JxsbCxgcServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.JXSB_CXGC;
    
    private JxsbCxgcDao jxsbCxgcDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = jxsbCxgcDao.queryCondition(json, null, null);

           
        }catch (DaoException e) {
        	logger.error("机械设备拆卸过程{}", e.getMessage());
			SystemException.handleMessageException("机械设备拆卸过程查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        JxsbCxgcVO vo = new JxsbCxgcVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
       
            // 插入
			jxsbCxgcDao.save(vo);
            resultVO = vo.getRowJson();

         
			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("机械设备拆卸过程{}", e.getMessage());
           SystemException.handleMessageException("机械设备拆卸过程新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        JxsbCxgcVO vo = new JxsbCxgcVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

           // 修改
            jxsbCxgcDao.update(vo);
            resultVO = vo.getRowJson();

         
        } catch (DaoException e) {
            logger.error("机械设备拆卸过程{}", e.getMessage());
           SystemException.handleMessageException("机械设备拆卸过程修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }
    
    public String updateZhuXiao(String sbid,String syglid) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        JxsbCxgcVO vo = new JxsbCxgcVO();

        try {
        	JxsbVO sbvo=new JxsbVO();
        	sbvo.setJxsb_uid(sbid);
        	sbvo.setSy_status("ZXDJ");  //注销jxsb
        	jxsbCxgcDao.update(sbvo);
        	  
        	JxsbSyglVO glvo=new JxsbSyglVO(); //注销 sygl
        	glvo.setJxsb_sygl_uid(syglid);
        	glvo.setStatus("1");
        	glvo.setEnd_date(new Date());
        	
          
            jxsbCxgcDao.update(glvo);
            resultVO = vo.getRowJson();

         
        } catch (DaoException e) {
            logger.error("机械设备拆卸过程{}", e.getMessage());
           SystemException.handleMessageException("机械设备拆卸过程修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		JxsbCxgcVO vo = new JxsbCxgcVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

		
			//删除   根据据主键
			jxsbCxgcDao.delete(JxsbCxgcVO.class, vo.getJxsb_cxgc_uid());

			resultVo = vo.getRowJson();

	
		} catch (DaoException e) {
            logger.error("机械设备拆卸过程{}", e.getMessage());
           SystemException.handleMessageException("机械设备拆卸过程删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("jxsbCxgcDaoImpl")
	public void setJxsbCxgcDao(JxsbCxgcDao jxsbCxgcDao) {
		this.jxsbCxgcDao = jxsbCxgcDao;
	}
    
}
