/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    person.service.BuSpywCbfaspGcjsxmzjfbhtbabService.java
 * 创建日期： 2014-11-18 下午 05:55:20
 * 功能：    接口：房屋建筑和市政基础设施工程施工、监理直接发包合同备案_步骤文件
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-11-18 下午 05:55:20  谢晨玮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.sp.service.impl;


import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.bzwj.BuSpywCbfaspGcjsxmzjfbhtbabVO;
import com.ccthanking.business.sp.dao.BuSpywCbfaspGcjsxmzjfbhtbabDao;
import com.ccthanking.business.sp.service.BuSpywCbfaspGcjsxmzjfbhtbabService;
import com.ccthanking.business.spxx.service.BuSpLzhfService;
import com.ccthanking.business.spxx.vo.BuSpLzhfVO;
import com.ccthanking.business.spyw.vo.BuSpywCbfaspVO;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;
import com.visural.common.StringUtil;



/**
 * <p> BuSpywCbfaspGcjsxmzjfbhtbabService.java </p>
 * <p> 功能：房屋建筑和市政基础设施工程施工、监理直接发包合同备案_步骤文件 </p>
 *
 * <p><a href="BuSpywCbfaspGcjsxmzjfbhtbabService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">谢晨玮</a>
 * @version 0.1
 * @since 2014-11-18
 * 
 */

@Service
public class BuSpywCbfaspGcjsxmzjfbhtbabServiceImpl extends Base1ServiceImpl<BuSpywCbfaspGcjsxmzjfbhtbabVO, String> implements BuSpywCbfaspGcjsxmzjfbhtbabService {

	private static Logger logger = LoggerFactory.getLogger(BuSpywCbfaspGcjsxmzjfbhtbabServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.BU_SPYW_CBFASP_GCJSXMZJFBHTBAB;
   
    private BuSpywCbfaspGcjsxmzjfbhtbabDao buSpywCbfaspGcjsxmzjfbhtbabDao;
    
    @Autowired
    private BuSpLzhfService buSpLzhfService;
    @Autowired
	@Qualifier("buSpywCbfaspGcjsxmzjfbhtbabDaoImpl")
	public void setBuSpywCbfaspGcjsxmzjfbhtbabDao(BuSpywCbfaspGcjsxmzjfbhtbabDao buSpywCbfaspGcjsxmzjfbhtbabDao) {
		this.buSpywCbfaspGcjsxmzjfbhtbabDao = buSpywCbfaspGcjsxmzjfbhtbabDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) buSpywCbfaspGcjsxmzjfbhtbabDao);
	}
    
    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			
        }catch (DaoException e) {
        	logger.error("房屋建筑和市政基础设施工程施工、监理直接发包合同备案_步骤文件{}", e.getMessage());
			LogManager.writeUserLog(null, ywlx, Globals.OPERATION_TYPE_QUERY, LogManager.RESULT_FAILURE, user
					.getOrgDept().getDeptName() + " " + user.getName() + "房屋建筑和市政基础设施工程施工、监理直接发包合同备案_步骤文件查询失败", user, "", "");
			SystemException.handleMessageException("房屋建筑和市政基础设施工程施工、监理直接发包合同备案_步骤文件查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		 BuSpywCbfaspGcjsxmzjfbhtbabVO vo = new BuSpywCbfaspGcjsxmzjfbhtbabVO();
        String resultVO = null;

        try {
        	buSpywCbfaspGcjsxmzjfbhtbabDao.save(vo);
           
        } catch (DaoException e) {
            logger.error("房屋建筑和市政基础设施工程施工、监理直接发包合同备案_步骤文件{}", e.getMessage());
           
            SystemException.handleMessageException("房屋建筑和市政基础设施工程施工、监理直接发包合同备案_步骤文件新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        BuSpywCbfaspGcjsxmzjfbhtbabVO vo = new BuSpywCbfaspGcjsxmzjfbhtbabVO();

        try {
            
            buSpywCbfaspGcjsxmzjfbhtbabDao.update(vo);
            
        } catch (DaoException e) {
            logger.error("房屋建筑和市政基础设施工程施工、监理直接发包合同备案_步骤文件{}", e.getMessage());
            SystemException.handleMessageException("房屋建筑和市政基础设施工程施工、监理直接发包合同备案_步骤文件修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		String resultVo = null;
		try {
			
		} catch (DaoException e) {
            logger.error("房屋建筑和市政基础设施工程施工、监理直接发包合同备案_步骤文件{}", e.getMessage());
            SystemException.handleMessageException("房屋建筑和市政基础设施工程施工、监理直接发包合同备案_步骤文件删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}
    /**
     * 材料核发调用获取MAP数据 
     * */
	public String ywlzclhf(String json) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        BuSpLzhfVO vo = new BuSpLzhfVO();

        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject object=(JSONObject) list.get(0);
            String YWLZ_UID=(String)object.get("YWLZ_UID");
            String id=(String)object.get("CBFASP_GCJSXMZJFBHTBAB_UID");
            
            //通过业务流转UID查询企业填报的数据，map类型
            BuSpywCbfaspGcjsxmzjfbhtbabVO mapFtl=new BuSpywCbfaspGcjsxmzjfbhtbabVO();
            if(StringUtil.isNotBlankStr(id)){
            	 mapFtl =this.findById(id);  
            	
            }

        	//组装业务流转核发数据
        	Map mapVo = new HashMap();
        	mapVo.put("YWLZ_UID", YWLZ_UID);
        	mapVo.put("YWCL_UID",object.get("YWCL_UID") );
        	mapVo.put("PIJIAN_CODE",object.get("PIJIAN_CODE") );
        	mapVo.put("PIJIAN_NAME",object.get("PIJIAN_NAME") );
        	mapVo.put("LINGJIAN_PHONE", object.get("LINGJIAN_PHONE"));
        	mapVo.put("LINGJIAN_REN", object.get("LINGJIAN_REN"));
        	mapVo.put("CLK_UID", object.getString("CLK_UID"));
        	mapVo.put("FZ_DATE", object.getString("FZ_DATE"));
        	mapVo.put("YXQ_DATE", object.getString("YXQ_DATE"));
        	
        	if(mapFtl!=null){
        		buSpLzhfService.saveBuSpLzhfVO(mapFtl, mapVo);
        	}
            
            resultVO = vo.getRowJson();    
        } catch (DaoException e) {
            logger.error("审批业务材料核发{}", e);
            SystemException.handleMessageException("审批业务材料核发调用失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;
	}
	
}
