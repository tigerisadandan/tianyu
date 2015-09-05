/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jxsb.service.AzCompanyService.java
 * 创建日期： 2014-12-11 上午 11:06:42
 * 功能：    接口：安装企业
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-11 上午 11:06:42  曹伟杰   创建文件，实现基本功能
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

import com.ccthanking.business.dtgl.azqy.vo.AzCompanyVO;
import com.ccthanking.business.dtgl.jxsb.dao.AzCompanyDao;
import com.ccthanking.business.dtgl.jxsb.service.AzCompanyService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;
import com.visural.common.StringUtil;



/**
 * <p> AzCompanyService.java </p>
 * <p> 功能：安装企业 </p>
 *
 * <p><a href="AzCompanyService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caoweijie@kzcpm.com">曹伟杰</a>
 * @version 0.1
 * @since 2014-12-11
 * 
 */

@Service
public class AzCompanyServiceImpl extends Base1ServiceImpl<AzCompanyVO, String> implements AzCompanyService {

	private static Logger logger = LoggerFactory.getLogger(AzCompanyServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.AZ_COMPANY;
    
    private AzCompanyDao azCompanyDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = azCompanyDao.queryCondition(json, null, null);

               
        }catch (DaoException e) {
        	logger.error("安装企业{}", e.getMessage());
	     } finally {
        }
        return domresult;

    }
    
    public String queryDq(String json) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = azCompanyDao.queryDq(json, null, null);

               
        }catch (DaoException e) {
        	logger.error("安装企业{}", e.getMessage());
	     } finally {
        }
        return domresult;
    }
    
   public String queryCount(String json) throws Exception {
        
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = azCompanyDao.queryCount(json, null, null);

               
        }catch (DaoException e) {
        	logger.error("安装企业{}", e.getMessage());
	     } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        AzCompanyVO vo = new AzCompanyVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
           // 插入
			azCompanyDao.save(vo);
            resultVO = vo.getRowJson();

         	//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("安装企业{}", e.getMessage());
         } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        AzCompanyVO vo = new AzCompanyVO();

        try {
        	JSONArray list = vo.doInitJson(json);
            JSONObject object=  (JSONObject) list.get(0);
            vo.setValueFromJson(object);
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */
            String SHENHE_JIEGUO=object.getString("SHENHE_JIEGUO");
            String SHENHE_YIJIAN=object.getString("SHENHE_YIJIAN");

            vo.setShenhe_date(new Date());
            vo.setShenhe_ren(user.getUserSN());
            vo.setShenhe_yijian(SHENHE_YIJIAN);
            vo.setShenhe_jieguo(SHENHE_JIEGUO);
            
            if(StringUtil.isNotBlankStr(SHENHE_JIEGUO)&&"1".equals(SHENHE_JIEGUO)){
            	 String domresult = azCompanyDao.queryCode(json, null, null);
   			     int year=new Date().getYear()+1900;
   			     domresult=year+"AZ"+String.format("%03d", Integer.parseInt(domresult));     
   			     vo.setDl_code(domresult);
            	vo.setStatus("10");
            }else{
            	vo.setStatus("20");
            }
        
            // 修改
            azCompanyDao.update(vo);
            resultVO = vo.getRowJson();

          } catch (DaoException e) {
            logger.error("安装企业{}", e.getMessage());
         } finally {
        }
        return resultVO;
    }
    
    public String updateOld(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        AzCompanyVO vo = new AzCompanyVO();

        try {
        	JSONArray list = vo.doInitJson(json);
            JSONObject object=  (JSONObject) list.get(0);
            vo.setValueFromJson(object);
        
            // 修改
            azCompanyDao.update(vo);
            resultVO = vo.getRowJson();

          } catch (DaoException e) {
            logger.error("安装企业{}", e.getMessage());
         } finally {
        }
        return resultVO;
    }
    
    
    
    public String updateYqsq(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        AzCompanyVO vo = new AzCompanyVO();

        try {
        	JSONArray list = vo.doInitJson(json);
            JSONObject object=  (JSONObject) list.get(0);
            vo.setValueFromJson(object);
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */
            String SHENHE_JIEGUO=object.getString("SHENHE_JIEGUO");
            String SHENHE_YIJIAN=object.getString("YQ_DESC");

            vo.setYq_desc(SHENHE_YIJIAN);
            
            if(StringUtil.isNotBlankStr(SHENHE_JIEGUO)&&"1".equals(SHENHE_JIEGUO)){
            	vo.setYq_status("1");
            	vo.setYxq_begin(vo.getYq_b_date());
            	vo.setYxq_end(vo.getYq_e_date());
            }else{
            	vo.setYq_status("2");
            }
        
            // 修改
            azCompanyDao.update(vo);
            resultVO = vo.getRowJson();

          } catch (DaoException e) {
            logger.error("安装企业{}", e.getMessage());
         } finally {
        }
        return resultVO;

    }
    
    public String updateStop(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        AzCompanyVO vo = new AzCompanyVO();

        try {
        	JSONArray list = vo.doInitJson(json);
            JSONObject object=  (JSONObject) list.get(0);
            vo.setValueFromJson(object);
            
            // 修改
            azCompanyDao.update(vo);
            resultVO = vo.getRowJson();

          } catch (DaoException e) {
            logger.error("安装企业{}", e.getMessage());
         } finally {
        }
        return resultVO;
    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		AzCompanyVO vo = new AzCompanyVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//删除   根据据主键
			azCompanyDao.delete(AzCompanyVO.class, vo.getAz_company_uid());

			resultVo = vo.getRowJson();

		
		} catch (DaoException e) {
            logger.error("安装企业{}", e.getMessage());
       	} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("azCompanyDaoImpl")
	public void setAzCompanyDao(AzCompanyDao azCompanyDao) {
		this.azCompanyDao = azCompanyDao;
	}
    
}
