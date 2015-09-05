/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    person.service.bu_spyw_spfjfsyjgysbadtjzhzService.java
 * 创建日期： 2014-11-06 下午 03:56:47
 * 功能：    接口：商品房交付使用竣工验收备案单体建筑汇总
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-11-06 下午 03:56:47  谢晨玮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.sp.dao.bu_spyw_spfjfsyjgysbadtjzhzDao;
import com.ccthanking.business.sp.service.bu_spyw_spfjfsyjgysbadtjzhzService;
import com.ccthanking.business.spyw.vo.BuSpywSpfjfsyjgysbadtjzhzVO;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> bu_spyw_spfjfsyjgysbadtjzhzService.java </p>
 * <p> 功能：商品房交付使用竣工验收备案单体建筑汇总 </p>
 *
 * <p><a href="bu_spyw_spfjfsyjgysbadtjzhzService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">谢晨玮</a>
 * @version 0.1
 * @since 2014-11-06
 * 
 */

@Service
public class bu_spyw_spfjfsyjgysbadtjzhzServiceImpl extends Base1ServiceImpl<BuSpywSpfjfsyjgysbadtjzhzVO, String> implements bu_spyw_spfjfsyjgysbadtjzhzService {

	private static Logger logger = LoggerFactory.getLogger(bu_spyw_spfjfsyjgysbadtjzhzServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.BU_SPYW_SPFJFSYJGYSBADTJZHZ;
    
    private bu_spyw_spfjfsyjgysbadtjzhzDao bu_spyw_spfjfsyjgysbadtjzhzDao;
	@Autowired
	@Qualifier("bu_spyw_spfjfsyjgysbadtjzhzDaoImpl")
	public void setbu_spyw_spfjfsyjgysbadtjzhzDao(bu_spyw_spfjfsyjgysbadtjzhzDao bu_spyw_spfjfsyjgysbadtjzhzDao) {
		this.bu_spyw_spfjfsyjgysbadtjzhzDao = bu_spyw_spfjfsyjgysbadtjzhzDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) bu_spyw_spfjfsyjgysbadtjzhzDao);
	}
    
    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

        	domresult = bu_spyw_spfjfsyjgysbadtjzhzDao.queryCondition(json);
        	
        }catch (DaoException e) {
        	logger.error("商品房交付使用竣工验收备案单体建筑汇总{}", e.getMessage());
			
			SystemException.handleMessageException("商品房交付使用竣工验收备案单体建筑汇总查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    public  BuSpywSpfjfsyjgysbadtjzhzVO findByDtId(String tid) throws Exception{
    	BuSpywSpfjfsyjgysbadtjzhzVO tem=new BuSpywSpfjfsyjgysbadtjzhzVO();
    	
    	tem =this.findById(tid);
	 	
    	return tem;
    } 
    public String insert(String json) throws Exception {

		
        String resultVO = null;

        try {
           
        	resultVO = bu_spyw_spfjfsyjgysbadtjzhzDao.insert(json);
        	
        } catch (DaoException e) {
            logger.error("商品房交付使用竣工验收备案单体建筑汇总{}", e.getMessage());
            
            SystemException.handleMessageException("商品房交付使用竣工验收备案单体建筑汇总新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        
        String resultVO = null;

        try {
           
        	resultVO = bu_spyw_spfjfsyjgysbadtjzhzDao.update(json);
        	
        } catch (DaoException e) {
            logger.error("商品房交付使用竣工验收备案单体建筑汇总{}", e.getMessage());
           
            SystemException.handleMessageException("商品房交付使用竣工验收备案单体建筑汇总修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		String resultVo = null;
		try {
			
		} catch (DaoException e) {
            logger.error("商品房交付使用竣工验收备案单体建筑汇总{}", e.getMessage());
           
            SystemException.handleMessageException("商品房交付使用竣工验收备案单体建筑汇总删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}


}
