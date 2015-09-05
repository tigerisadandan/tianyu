/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    qyxx.service.YxKcEnterpriseGcalxxService.java
 * 创建日期： 2015-01-28 下午 12:09:51
 * 功能：    接口：材料设备工程案例信息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-01-28 下午 12:09:51  隆楚雄   创建文件，实现基本功能
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

import com.ccthanking.business.wxgc.dao.YxClEnterpriseGcalxxDao;
import com.ccthanking.business.wxgc.service.YxClEnterpriseGcalxxService;
import com.ccthanking.business.wxgc.vo.YxClEnterpriseGcalxxVO;
import com.ccthanking.business.wxgc.vo.YxKcEnterpriseGcalxxVO;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;



/**
 * <p> YxKcEnterpriseGcalxxService.java </p>
 * <p> 功能：材料设备工程案例信息 </p>
 *
 * <p><a href="YxKcEnterpriseGcalxxService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kcit.com">隆楚雄</a>
 * @version 0.1
 * @since 2015-01-28
 * 
 */

@Service
public class YxClEnterpriseGcalxxServiceImpl extends Base1ServiceImpl<YxClEnterpriseGcalxxVO, String> implements YxClEnterpriseGcalxxService {

	private static Logger logger = LoggerFactory.getLogger(YxClEnterpriseGcalxxServiceImpl.class);
	
    private YxClEnterpriseGcalxxDao yxClEnterpriseGcalxxDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = yxClEnterpriseGcalxxDao.queryCondition(json, null, null);
            
        }catch (DaoException e) {
        	logger.error("材料设备工程案例信息{}", e.getMessage());
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        YxClEnterpriseGcalxxVO vo = new YxClEnterpriseGcalxxVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
            // 插入
			yxClEnterpriseGcalxxDao.save(vo);
            resultVO = vo.getRowJson();

        } catch (DaoException e) {
            logger.error("材料设备工程案例信息{}", e.getMessage());
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        YxClEnterpriseGcalxxVO vo = new YxClEnterpriseGcalxxVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));

            // 修改
            yxClEnterpriseGcalxxDao.update(vo);
            resultVO = vo.getRowJson();

        } catch (DaoException e) {
            logger.error("材料设备工程案例信息{}", e.getMessage());
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		YxKcEnterpriseGcalxxVO vo = new YxKcEnterpriseGcalxxVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//删除   根据据主键
			yxClEnterpriseGcalxxDao.delete(YxKcEnterpriseGcalxxVO.class, vo.getAlgcxx_uid());

			resultVo = vo.getRowJson();

		} catch (DaoException e) {
            logger.error("材料设备工程案例信息{}", e.getMessage());
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("yxClEnterpriseGcalxxDaoImpl")
	public void setyxClEnterpriseGcalxxDao(YxClEnterpriseGcalxxDao yxClEnterpriseGcalxxDao) {
		this.yxClEnterpriseGcalxxDao = yxClEnterpriseGcalxxDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc)yxClEnterpriseGcalxxDao);
	}
    
}
