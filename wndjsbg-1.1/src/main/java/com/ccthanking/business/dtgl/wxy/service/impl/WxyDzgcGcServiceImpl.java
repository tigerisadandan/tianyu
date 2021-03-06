/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    wxy.service.WxyDzgcGcService.java
 * 创建日期： 2015-05-19 下午 02:16:34
 * 功能：    接口：危险源吊装工程过程管理
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-05-19 下午 02:16:34  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dtgl.wxy.service.impl;


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
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.fileUpload.service.FileUploadOldService;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.dtgl.wxy.vo.WxyDzgcGcVO;
import com.ccthanking.business.dtgl.wxy.dao.WxyDzgcGcDao;
import com.ccthanking.business.dtgl.wxy.service.WxyDzgcGcService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> WxyDzgcGcService.java </p>
 * <p> 功能：危险源吊装工程过程管理 </p>
 *
 * <p><a href="WxyDzgcGcService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@163.com">曹伟杰</a>
 * @version 0.1
 * @since 2015-05-19
 * 
 */

@Service
public class WxyDzgcGcServiceImpl extends Base1ServiceImpl<WxyDzgcGcVO, String> implements WxyDzgcGcService {

	private static Logger logger = LoggerFactory.getLogger(WxyDzgcGcServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.WXY_DZGC_GC;
    
    private WxyDzgcGcDao wxyDzgcGcDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = wxyDzgcGcDao.queryCondition(json, null, null);

           
        }catch (DaoException e) {
        	logger.error("危险源吊装工程过程管理{}", e.getMessage());
			SystemException.handleMessageException("危险源吊装工程过程管理查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        WxyDzgcGcVO vo = new WxyDzgcGcVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            vo.setWxy_dzgc_gc_uid(DBUtil.getSequenceValue("WXY_DZGC_GC_UID"));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            JSONObject obj= (JSONObject) list.get(0);
            String ywid=obj.getString("ywid");
            if(ywid!=null&&!"".equals(ywid)){
            	FileUploadOldService.updateFjztOrYwidByYwid(ywid, vo.getWxy_dzgc_gc_uid(), Constants.FS_FILEUPLOAD_FJLB_WXY_DZGCGC);
            }
          
            // 插入
			wxyDzgcGcDao.save(vo);
            resultVO = vo.getRowJson();

   
			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("危险源吊装工程过程管理{}", e.getMessage());
           SystemException.handleMessageException("危险源吊装工程过程管理新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        WxyDzgcGcVO vo = new WxyDzgcGcVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */
            JSONObject obj= (JSONObject) list.get(0);
            String ywid=obj.getString("ywid");
            if(ywid!=null&&!"".equals(ywid)){
            	FileUploadOldService.updateFjztOrYwidByYwid(ywid, vo.getWxy_dzgc_gc_uid(), Constants.FS_FILEUPLOAD_FJLB_WXY_DZGCGC);
            }
          
            // 修改
            wxyDzgcGcDao.update(vo);
            resultVO = vo.getRowJson();

      
        } catch (DaoException e) {
            logger.error("危险源吊装工程过程管理{}", e.getMessage());
            SystemException.handleMessageException("危险源吊装工程过程管理修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		WxyDzgcGcVO vo = new WxyDzgcGcVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

		
			//删除   根据据主键
			wxyDzgcGcDao.delete(WxyDzgcGcVO.class, vo.getWxy_dzgc_gc_uid());

			resultVo = vo.getRowJson();

		} catch (DaoException e) {
            logger.error("危险源吊装工程过程管理{}", e.getMessage());
            SystemException.handleMessageException("危险源吊装工程过程管理删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("wxyDzgcGcDaoImpl")
	public void setWxyDzgcGcDao(WxyDzgcGcDao wxyDzgcGcDao) {
		this.wxyDzgcGcDao = wxyDzgcGcDao;
	}
    
}
