/* ==================================================================
1 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    project.service.JsCompanyService.java
 * 创建日期： 2014-07-02 下午 12:05:19
 * 功能：    接口：建设单位
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-07-02 下午 12:05:19  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.project.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.bzwj.dao.JsdwUserDao;
import com.ccthanking.business.bzwj.service.JsdwUserService;
import com.ccthanking.business.common.vo.JsdwUserVO;
import com.ccthanking.business.project.dao.JsCompanyDao;
import com.ccthanking.business.project.service.JsCompanyService;
import com.ccthanking.business.project.vo.JsCompanyVO;
import com.ccthanking.business.resources.service.JsComClkService;
import com.ccthanking.business.weixin.service.FsMessageInfoService;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;
import com.visural.common.StringUtil;



/**
 * <p> JsCompanyService.java </p>
 * <p> 功能：建设单位 </p>
 *
 * <p><a href="JsCompanyService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-07-02
 * 
 */

@Service
public class JsCompanyServiceImpl extends Base1ServiceImpl<JsCompanyVO, String> implements JsCompanyService {

	private static Logger logger = LoggerFactory.getLogger(JsCompanyServiceImpl.class);
	
	// 业务类型
//    private String ywlx = YwlxManager.JS_COMPANY;
    
    private JsCompanyDao jsCompanyDao;
    @Autowired
    private JsComClkService jsComClkService;
    
    @Autowired
    private JsdwUserService jsdwUserService;
    
    @Autowired
    private FsMessageInfoService fsMessageInfoService;

    // @Override
    public String queryCondition(String json) throws Exception {
    
//    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = jsCompanyDao.queryCondition(json, null, null);

                
        }catch (DaoException e) {
        	logger.error("建设单位查询{}", e.getMessage());
			SystemException.handleMessageException("建设单位查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

//		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        JsCompanyVO vo = new JsCompanyVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
           
            // 插入
			jsCompanyDao.save(vo);
            resultVO = vo.getRowJson();
          
			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("建设单位{}", e.getMessage());
            SystemException.handleMessageException("建设单位新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        JsCompanyVO jsCompanyVO = new JsCompanyVO();

        try {
            JSONArray list = jsCompanyVO.doInitJson(json);
            JSONObject object=  (JSONObject) list.get(0);
           jsCompanyVO.setValueFromJson(object);
           // String companyid=object.getString("JS_COMPANY_UID");
//          JsCompanyVO jsCompanyVO=this.findById(companyid);
           
            String SHENHE_JIEGUO=object.getString("SHENHE_JIEGUO");
            String SHENHE_YIJIAN=object.getString("SHENHE_YIJIAN");

            jsCompanyVO.setShenhe_date(new Date());
            jsCompanyVO.setShenhe_ren(user.getUserSN());
            jsCompanyVO.setShenhe_yijian(SHENHE_YIJIAN);
            jsCompanyVO.setShenhe_jieguo(SHENHE_JIEGUO);
            
            if(StringUtil.isNotBlankStr(SHENHE_JIEGUO)&&"1".equals(SHENHE_JIEGUO)){
            	jsCompanyVO.setStatus("10");
            	JsCompanyVO jsvo=this.findById(jsCompanyVO.getJs_company_uid());
            	String SQL_DELETE="DELETE FROM JSDW_USER WHERE JS_COMPANY_UID = "+jsvo.getJs_company_uid();
    			DBUtil.exec(SQL_DELETE);
    			logger.info("<{}>执行操作【JSDW_USER历史删除】",user.getName());
    			JsdwUserVO jsdwVo=new JsdwUserVO();
    			jsdwVo.setJsdw_user_uid(DBUtil.getSequenceValue("JSDW_USER_UID"));
    			jsdwVo.setJs_company_uid(jsvo.getJs_company_uid());
    			jsdwVo.setUser_code(jsvo.getUser_name());
    			jsdwVo.setUser_code2(jsvo.getJiguo_daima());
    			jsdwVo.setPwd(jsvo.getPwd());
    			jsdwVo.setMima(jsvo.getMima());
    			jsdwVo.setUser_name("管理员");
    			jsdwVo.setAdmin_y("1");
    			jsdwVo.setCreated_date(new Date());
    			jsCompanyDao.save(jsdwVo);
            }else{
            	jsCompanyVO.setStatus("20");
            }
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */ 	
            // 修改
            jsCompanyDao.update(jsCompanyVO);
            resultVO = jsCompanyVO.getRowJson();
//            String companyid=object.getString("JS_COMPANY_UID");
//            jsCompanyVO=this.findById(companyid);
            //审核通过后调用方法新建企业节点、立项和项目的根节点
            if(StringUtil.isNotBlankStr(SHENHE_JIEGUO)&&"1".equals(SHENHE_JIEGUO)){
            	jsComClkService.saveCompanyClk(Constants.YWCLKJB_QY, jsCompanyVO.getJs_company_uid(), jsCompanyVO.getCompany_name(), null);
            }
            if(StringUtil.isNotBlankStr(SHENHE_JIEGUO)&&"1".equals(SHENHE_JIEGUO)){
	            //****************************消息添加
	            Map messageMap= new HashMap();
	            messageMap.put("title", "建设企业注册审核");
	            messageMap.put("content", "注册审核通过");
	            messageMap.put("userto", jsCompanyVO.getUser_name());
	            messageMap.put("usertoname", jsCompanyVO.getCompany_name());
	
	            fsMessageInfoService.insertVo(messageMap);
            }
        } catch (DaoException e) {
            logger.error("建设单位审核{}", e.getMessage());
            SystemException.handleMessageException("建设单位审核失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }
    
    public String updateZyxx(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        JsCompanyVO jsCompanyVO = new JsCompanyVO();

        try {
            JSONArray list = jsCompanyVO.doInitJson(json);
            JSONObject object=  (JSONObject) list.get(0);
           jsCompanyVO.setValueFromJson(object);
           // String companyid=object.getString("JS_COMPANY_UID");
//          JsCompanyVO jsCompanyVO=this.findById(companyid);
           
            String SHENHE_JIEGUO=object.getString("SHENHE_JIEGUO");
            String SHENHE_YIJIAN=object.getString("ZYXX_SHENHE_YIJIAN");
            jsCompanyVO.setZyxx_shenhe_date(new Date());
            jsCompanyVO.setZyxx_shenhe_ren(user.getUserSN());
            jsCompanyVO.setZyxx_shenhe_yijian(SHENHE_YIJIAN);
            
            if(StringUtil.isNotBlankStr(SHENHE_JIEGUO)&&"1".equals(SHENHE_JIEGUO)){
            	jsCompanyVO.setZyxx_status("10");
            	jsCompanyVO.setCompany_name(jsCompanyVO.getNew_company_name());
            	jsCompanyVO.setZhizhao(jsCompanyVO.getNew_zhizhao());
            	jsCompanyVO.setZhizhao_valid(jsCompanyVO.getNew_zhizhao_valid());
            	jsCompanyVO.setZhuce_zijin(jsCompanyVO.getNew_zhuce_zijin());
            }else{
            	jsCompanyVO.setZyxx_status("20");
            }
          
            jsCompanyDao.update(jsCompanyVO);
            resultVO = jsCompanyVO.getRowJson();
          
            if(StringUtil.isNotBlankStr(SHENHE_JIEGUO)&&"1".equals(SHENHE_JIEGUO)){
	            //****************************消息添加
            	 Map messageMap= new HashMap();
            	 messageMap.put("TITLE", "建设企业信息变更审核");
 	            messageMap.put("CONTENT", "企业信息变更审核通过");
 	            messageMap.put("USERTO", jsCompanyVO.getUser_name());
 	            messageMap.put("USERTONAME", jsCompanyVO.getCompany_name());
                 messageMap.put("SYSTEM_TYPE", "JS");
                 messageMap.put("COMPANY_UID", jsCompanyVO.getJs_company_uid());
                 messageMap.put("MSG_TYPE", Constants.FS_MESSAGE_INFO_MSG_TYPE_JSQY_XXBG);
                 messageMap.put("QUANXIAN_UID", Constants.QUANXIAN_JS_QYXXWH);
                 messageMap.put("MSG_VIEW_TYPE", "1");
                 fsMessageInfoService.insertVo(messageMap);
            }else if(StringUtil.isNotBlankStr(SHENHE_JIEGUO)&&"2".equals(SHENHE_JIEGUO)){
	            //****************************消息添加
	            Map messageMap= new HashMap();
	            messageMap.put("TITLE", "建设企业信息变更审核");
	            messageMap.put("CONTENT", "企业信息审核未通过");
	            messageMap.put("USERTO", jsCompanyVO.getUser_name());
	            messageMap.put("USERTONAME", jsCompanyVO.getCompany_name());
	            messageMap.put("SYSTEM_TYPE", "JS");
                messageMap.put("COMPANY_UID", jsCompanyVO.getJs_company_uid());
                messageMap.put("MSG_TYPE", Constants.FS_MESSAGE_INFO_MSG_TYPE_JSQY_XXBG);
                messageMap.put("QUANXIAN_UID", Constants.QUANXIAN_JS_QYXXWH);
                messageMap.put("MSG_VIEW_TYPE", "2");
                fsMessageInfoService.insertVo(messageMap);
            }
        } catch (DaoException e) {
            logger.error("建设单位审核{}", e.getMessage());
            SystemException.handleMessageException("建设单位信息变更审核失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

//		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		JsCompanyVO vo = new JsCompanyVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			
			//删除   根据据主键
			jsCompanyDao.delete(JsCompanyVO.class, vo.getJs_company_uid());

			resultVo = vo.getRowJson();

		} catch (DaoException e) {
            logger.error("建设单位删除{}", e.getMessage());
            SystemException.handleMessageException("建设单位删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("jsCompanyDaoImpl")
	public void setJsCompanyDao(JsCompanyDao jsCompanyDao) {
		this.jsCompanyDao = jsCompanyDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) jsCompanyDao);
	}

	public String getCompanyCount() throws Exception {
		JSONObject obj = new JSONObject();
		try {
			List<?> tempList= jsCompanyDao.getCompanyCount();
			if(tempList!=null&&tempList.size()>0){				
					Map temMap=(Map)tempList.get(0);		
					obj.put("DSH", temMap.get("DSH"));
					obj.put("WTG", temMap.get("WTG"));
					obj.put("YTG", temMap.get("YTG"));
	
			}
			
		} catch (DaoException e) {
            logger.error("建设单位注册统计{}", e.getMessage());
            SystemException.handleMessageException("建设单位注册统计查询失败,请联系相关人员处理");
		} finally {
		}
		
		return obj.toString();
	}
	
	public String getCompanyZyxxCount() throws Exception {
		JSONObject obj = new JSONObject();
		try {
			List<?> tempList= jsCompanyDao.getCompanyZyxxCount();
			if(tempList!=null&&tempList.size()>0){				
					Map temMap=(Map)tempList.get(0);		
					obj.put("DSH", temMap.get("DSH"));
					obj.put("WTG", temMap.get("WTG"));
					obj.put("YTG", temMap.get("YTG"));
	
			}
			
		} catch (DaoException e) {
            logger.error("建设单位注册统计{}", e.getMessage());
            SystemException.handleMessageException("建设单位注册统计查询失败,请联系相关人员处理");
		} finally {
		}
		
		return obj.toString();
	}
	
	
    
}
