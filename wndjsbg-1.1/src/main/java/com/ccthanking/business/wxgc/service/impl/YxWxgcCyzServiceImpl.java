/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    wxgc.service.YxWxgcCyzService.java
 * 创建日期： 2014-12-22 下午 02:58:58
 * 功能：    接口：微型工程参与者
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-22 下午 02:58:58  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.wxgc.service.impl;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.wxgc.dao.YxWxgcCyzDao;
import com.ccthanking.business.wxgc.service.YxWxgcCyzService;
import com.ccthanking.business.wxgc.service.YxWxgcService;
import com.ccthanking.business.wxgc.vo.YxWxgcCyzVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> YxWxgcCyzService.java </p>
 * <p> 功能：微型工程参与者 </p>
 *
 * <p><a href="YxWxgcCyzService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kcit.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-12-22
 * 
 */

@Service
public class YxWxgcCyzServiceImpl extends Base1ServiceImpl<YxWxgcCyzVO, String> implements YxWxgcCyzService {
	private static Logger logger = LoggerFactory.getLogger(YxWxgcCyzServiceImpl.class);
    private YxWxgcCyzDao yxWxgcCyzDao;
    @Autowired
    private YxWxgcService yxWxgcService;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = yxWxgcCyzDao.queryCondition(json, null, null);

            
        }catch (DaoException e) {
        	logger.error("微型工程参与者{}", e.getMessage());
			SystemException.handleMessageException("微型工程参与者查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        YxWxgcCyzVO vo = new YxWxgcCyzVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
            BusinessUtil.setInsertCommonFields(vo, user);

            // 插入
			yxWxgcCyzDao.save(vo);
            resultVO = vo.getRowJson();


			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("微型工程参与者{}", e.getMessage());
            SystemException.handleMessageException("微型工程参与者新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        YxWxgcCyzVO vo = new YxWxgcCyzVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

          	BusinessUtil.setUpdateCommonFields(vo, user);

            // 修改
            yxWxgcCyzDao.update(vo);
            resultVO = vo.getRowJson();


        } catch (DaoException e) {
            logger.error("微型工程参与者{}", e.getMessage());
            SystemException.handleMessageException("微型工程参与者修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		YxWxgcCyzVO vo = new YxWxgcCyzVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//删除   根据据主键
			yxWxgcCyzDao.delete(YxWxgcCyzVO.class, vo.getCyz_uid());

			resultVo = vo.getRowJson();


		} catch (DaoException e) {
            logger.error("微型工程参与者{}", e.getMessage());
            SystemException.handleMessageException("微型工程参与者删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("yxWxgcCyzDaoImpl")
	public void setYxWxgcCyzDao(YxWxgcCyzDao yxWxgcCyzDao) {
		this.yxWxgcCyzDao = yxWxgcCyzDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) yxWxgcCyzDao);
	}

	

}
