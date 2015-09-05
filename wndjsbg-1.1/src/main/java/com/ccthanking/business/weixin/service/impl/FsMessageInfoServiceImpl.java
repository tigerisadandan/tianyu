/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    weixin.service.FsMessageInfoService.java
 * 创建日期： 2014-12-02 上午 11:04:41
 * 功能：    接口：消息表
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-12-02 上午 11:04:41  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.weixin.service.impl;


import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.business.weixin.dao.FsMessageInfoDao;
import com.ccthanking.business.weixin.service.FsMessageInfoService;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.common.EventManager;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.common.vo.EventVO;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.ccthanking.framework.util.RandomGUID;
import com.ccthanking.weixin.vo.FsMessageInfoVO;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> FsMessageInfoService.java </p>
 * <p> 功能：消息表 </p>
 *
 * <p><a href="FsMessageInfoService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-12-02
 * 
 */

@Service
public class FsMessageInfoServiceImpl extends Base1ServiceImpl<FsMessageInfoVO, String> implements FsMessageInfoService {

	private static Logger logger = LoggerFactory.getLogger(FsMessageInfoServiceImpl.class);
	
	// 业务类型
    private String ywlx = YwlxManager.FS_MESSAGE_INFO;
    
    private FsMessageInfoDao fsMessageInfoDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = fsMessageInfoDao.queryCondition(json, null, null);

            
        }catch (DaoException e) {
        	logger.error("消息表{}", e.getMessage());
			SystemException.handleMessageException("消息表查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        FsMessageInfoVO vo = new FsMessageInfoVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
            BusinessUtil.setInsertCommonFields(vo, user);
            vo.setYwlx(ywlx);

            EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
		    vo.setSjbh(eventVO.getSjbh());

            // 插入
			fsMessageInfoDao.save(vo);
            resultVO = vo.getRowJson();


			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("消息表{}", e.getMessage());
            SystemException.handleMessageException("消息表新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        FsMessageInfoVO vo = new FsMessageInfoVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

          	BusinessUtil.setUpdateCommonFields(vo, user);
            vo.setYwlx(ywlx);
            EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
			vo.setSjbh(eventVO.getSjbh());

            // 修改
            fsMessageInfoDao.update(vo);
            resultVO = vo.getRowJson();


        } catch (DaoException e) {
            logger.error("消息表{}", e.getMessage());
            SystemException.handleMessageException("消息表修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    //不提供物理删除消息功能，只修改状态，供查看功能调用
    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		FsMessageInfoVO vo = new FsMessageInfoVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);
			vo.setValueFromJson(jsonObj);
			vo=this.findById(vo.getOpid());
			vo.setState("2");
			vo.setDeloper(user.getAccount());
			vo.setDeltime(new Date());
			
			fsMessageInfoDao.update(vo);
			resultVo = vo.getRowJson();


		} catch (DaoException e) {
            logger.error("消息表{}", e.getMessage());
            SystemException.handleMessageException("消息表删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("fsMessageInfoDaoImpl")
	public void setFsMessageInfoDao(FsMessageInfoDao fsMessageInfoDao) {
		this.fsMessageInfoDao = fsMessageInfoDao;
		super.setBsBaseDaoTJdbc((BsBaseDaoTJdbc) fsMessageInfoDao);
	}

	
    /**
     * 消息发送保存调用接口参数说明
     * ******必须有的字段如下
     * USERTO       接受者帐号
     * USERTONAME   接受者姓名
     * TITLE        消息主题
     * CONTENT      消息内容
     * YWLX         消息来源业务类型
     * *******其他可选字段
     * SYSMESSAGE   是否系统消息  默认是
     * EMAILMESSAGE 是否邮件发送  默认否
     * SMSMESSAGE   是否短信发送  默认否
     * */
	public String insertVo(Map messageMap) throws Exception {
		User user = ActionContext.getCurrentUserInThread();
		String resultVO = null;
		FsMessageInfoVO vo = new FsMessageInfoVO();
		try {	
			String opid = new RandomGUID().toString();
			String userto=(String)messageMap.get("USERTO");
			String usertoname=(String)messageMap.get("USERTONAME");
			String title=(String)messageMap.get("TITLE");
			String content=(String)messageMap.get("CONTENT");
			String ywlx=(String)messageMap.get("YWLX");
			String sysmessage=(String)messageMap.get("SYSMESSAGE");//是否系统消息
			String emailmessage=(String)messageMap.get("EMAILMESSAGE");//是否邮件发送
			String smsmessage=(String)messageMap.get("SMSMESSAGE");//是否短信发送
			
			
			/**
			 * 2015。6.10 修改添加以下字段 用于各系统 动态信息
			 * */
			String SYSTEM_TYPE=(String)messageMap.get("SYSTEM_TYPE");//系统:JS/BG/SG/JL 接受者系统简称
			String COMPANY_UID=(String)messageMap.get("COMPANY_UID");//对应系统的企业uid
			String MSG_TYPE=(String)messageMap.get("MSG_TYPE");//消息类型 如:建设手续，建设项目,建设项目分期，企业事务，整改单，施工人员，施工企业
			String QUANXIAN_UID=(String)messageMap.get("QUANXIAN_UID");//权限点UID
			String MSG_VIEW_TYPE=(String)messageMap.get("MSG_VIEW_TYPE");//消息提示框样式;1已通过之类的提示框(绿色)，2未通过之类的提示框(红色)，3警告之类的提示框(黄色)
			String PRM1=(String)messageMap.get("PRM1");//系统:JS/BG/SG/JL 参数1;
			String PRM2=(String)messageMap.get("PRM2");//系统:JS/BG/SG/JL 参数2;
			String PRM3=(String)messageMap.get("PRM3");//系统:JS/BG/SG/JL 参数3
			String PRM4=(String)messageMap.get("PRM4");//系统:JS/BG/SG/JL 参数4
			vo.setSystem_type(SYSTEM_TYPE);
			vo.setCompany_uid(COMPANY_UID);
			vo.setMsg_type(MSG_TYPE);
			vo.setQuanxian_uid(QUANXIAN_UID);
			vo.setMsg_view_type(MSG_VIEW_TYPE);
			vo.setPrm1(PRM1);
			vo.setPrm2(PRM2);
			vo.setPrm3(PRM3);
			vo.setPrm4(PRM4);
			/**
			 */
			
			
			vo.setOpid(opid);//id
			vo.setUserfrom(user.getAccount());//消息发送者帐号
			vo.setUserfromname(user.getName());//消息发送者姓名
			vo.setUserto(userto);//消息接受者帐号
			vo.setUsertoname(usertoname);//消息接受者姓名
			vo.setTitle(title);//消息主题
			vo.setContent(content);//消息内容
			vo.setYwlx(ywlx);//消息来源业务类型
			vo.setOptime(new Date());//消息发送时间
			vo.setState("1");//消息状态 1正常 2删除（已查看）
			if(sysmessage==null||"".equals(sysmessage)){
				sysmessage="1";
			}
			if(emailmessage==null||"".equals(emailmessage)){
				emailmessage="0";
			}
			if(smsmessage==null||"".equals(smsmessage)){
				smsmessage="0";
			}
			vo.setSysmessage(sysmessage);//是否系统消息
			vo.setEmailmessage(emailmessage);//是否邮件发送
			vo.setSmsmessage(smsmessage);//是否短信发送
			
			fsMessageInfoDao.save(vo);
	        resultVO = vo.getRowJson();
	        
		} catch (DaoException e) {
	        logger.error("消息表{}", e.getMessage());
	        SystemException.handleMessageException("消息表新增失败,请联系相关人员处理");
	    } finally {
	    }
		return resultVO;
	}

	
	//查询当前登录人的未查看的消息总数
	public String countMessage(Map map) {
		JSONArray list=new JSONArray();		
		try {
			map.put("STATE", "1");
			List<?> tempList= fsMessageInfoDao.countMessage(map);
			
			if(tempList!=null&&tempList.size()>0){		
				for(int i=0;i<tempList.size();i++){
					JSONObject obj = new JSONObject();
					Map temMap=(Map)tempList.get(i);		
					obj.put("ALLMES", temMap.get("ALLMES"));
					list.add(obj);	
					
				}   				
			}			
		} catch (DaoException e) {
            logger.error("获取所有范围内的消息数据{}", e.getMessage());
            SystemException.handleMessageException("获取所有范围内的消息数据失败,请联系相关人员处理");
		} finally {
		}
		
		return list.toString();
	}
    
}
