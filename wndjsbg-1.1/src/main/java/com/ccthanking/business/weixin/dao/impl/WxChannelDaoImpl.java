package com.ccthanking.business.weixin.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RandomGUID;
import com.ccthanking.business.weixin.dao.WxChannelDao;
import com.ccthanking.business.weixin.service.impl.WxChannelServiceImpl;
import com.ccthanking.weixin.vo.WxChannelVO;
import com.copj.modules.utils.exception.DaoException;

@Component
public class WxChannelDaoImpl  extends BsBaseDaoTJdbc implements WxChannelDao{
	private static Logger logger = LoggerFactory.getLogger(WxChannelServiceImpl.class);

	public String queryCondition(String json, WxChannelVO vo) {
		Connection conn = DBUtil.getConnection();	
		JSONArray jsonArr = new JSONArray();
		JSONObject jobject0 = new JSONObject();
		jobject0.put("id", "0");
		jobject0.put("pId", "");
		jobject0.put("name", "根节点");
		jobject0.put("open", true);
		jsonArr.add(jobject0);
		try {
			String querySql="select t.*,(select c.channel_name from wx_channel c where c.channel_uid=t.p_channel_uid) as p_channel_name from wx_channel t";
			List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, querySql);
			for (int i = 0; i < rsList.size(); i++) {
				Map<String, String> rsMap = rsList.get(i);
				JSONObject jobject = new JSONObject();
				jobject.put("id", rsMap.get("CHANNEL_UID"));
				jobject.put("pId", rsMap.get("P_CHANNEL_UID"));
				jobject.put("name", rsMap.get("CHANNEL_NAME"));
				jobject.put("ENABLED", rsMap.get("ENABLED"));
				jobject.put("DESCRIBE", rsMap.get("DESCRIBE"));
				jobject.put("SERIAL_NO", rsMap.get("SERIAL_NO"));
				jobject.put("P_CHANNEL_UID", rsMap.get("P_CHANNEL_UID"));
				jobject.put("P_CHANNEL_NAME", rsMap.get("P_CHANNEL_NAME"));
				jobject.put("CHANNEL_NAME", rsMap.get("CHANNEL_NAME"));
				jobject.put("CHANNEL_SNAME", rsMap.get("CHANNEL_SNAME"));
				jobject.put("CHANNEL_PIC", rsMap.get("CHANNEL_PIC"));
				jobject.put("CHANNEL_URL", rsMap.get("CHANNEL_URL"));
				jobject.put("EVENTKEY", rsMap.get("EVENTKEY"));
				jobject.put("CHANNEL_TYPE", rsMap.get("CHANNEL_TYPE"));
				jobject.put("open", true);
			    jsonArr.add(jobject);
			}
		} catch (Exception e) {
			 DaoException.handleMessageException("*********栏目查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return jsonArr.toString();
	}

	
	
	/**
	 * 查询所有权限范围内的栏目信息
	 * 
	 * */
	public String queryRightCondition(String json, WxChannelVO vo) {
		User user = ActionContext.getCurrentUserInThread();
		
		Connection conn = DBUtil.getConnection();	
		JSONArray jsonArr = new JSONArray();
//		JSONObject jobject0 = new JSONObject();
//		jobject0.put("id", "0");
//		jobject0.put("pId", "");
//		jobject0.put("name", "根节点");
//		jobject0.put("open", true);
//		jsonArr.add(jobject0);
		try {
			String querySql="select t.*,(select c.channel_name from wx_channel c where c.channel_uid=t.p_channel_uid) as p_channel_name " +
					" from at_adm_auth_channel ac left join wx_channel t on t.channel_uid=ac.channel_uid" +
					" where ac.authid ='"+user.getAccount()+"'";
			List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, querySql);
			for (int i = 0; i < rsList.size(); i++) {
				Map<String, String> rsMap = rsList.get(i);
				JSONObject jobject = new JSONObject();
				jobject.put("id", rsMap.get("CHANNEL_UID"));
				jobject.put("pId", rsMap.get("P_CHANNEL_UID"));
				jobject.put("name", rsMap.get("CHANNEL_NAME"));
				jobject.put("ENABLED", rsMap.get("ENABLED"));
				jobject.put("DESCRIBE", rsMap.get("DESCRIBE"));
				jobject.put("SERIAL_NO", rsMap.get("SERIAL_NO"));
				jobject.put("P_CHANNEL_UID", rsMap.get("P_CHANNEL_UID"));
				jobject.put("P_CHANNEL_NAME", rsMap.get("P_CHANNEL_NAME"));
				jobject.put("CHANNEL_NAME", rsMap.get("CHANNEL_NAME"));
				jobject.put("CHANNEL_SNAME", rsMap.get("CHANNEL_SNAME"));
				jobject.put("CHANNEL_PIC", rsMap.get("CHANNEL_PIC"));
				jobject.put("CHANNEL_URL", rsMap.get("CHANNEL_URL"));
				jobject.put("EVENTKEY", rsMap.get("EVENTKEY"));
				jobject.put("CHANNEL_TYPE", rsMap.get("CHANNEL_TYPE"));
				jobject.put("open", true);
			    jsonArr.add(jobject);
			}
		} catch (Exception e) {
			 DaoException.handleMessageException("*********栏目查询出错!*********");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return jsonArr.toString();
	}
	
	
	
	public String getChannelIdByEventKey(String eventkey) {
		Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {        
            String sql = "select t.channel_uid from wx_channel t  where t.eventkey='"+eventkey+"'";
            
            String[][] tempIds= DBUtil.query(conn, sql);
            if(tempIds!=null&&tempIds.length>0){
            	for(int i=0;i<tempIds.length;i++){
            		domresult=tempIds[0][i]+",";
            	}
            }
            domresult = domresult.substring(0, domresult.length()-1);
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询栏目ID出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}

	public List<Map<String, String>> listCondition(WxChannelVO vo,
			String eventKey, int rownum) {
		List<Map<String, String>> list=null;
		Connection conn = DBUtil.getConnection();
		try{		
			String sql="select a.*,rownum idnum from ("+
				 " select t.channel_uid,t.describe,t.channel_name,t.channel_pic,t.channel_url" +
				 " from wx_channel t where t.eventkey ='"+eventKey+"'" +
				 " order by t.channel_uid desc ) a  "+
				 " where rownum <="+rownum;
			
			list =DBUtil.queryReturnList(conn, sql);
					
		  } catch (Exception e) {
	          DaoException.handleMessageException("*********查询栏目信息出错!*********");
	      } finally {
	          DBUtil.closeConnetion(conn);
	      }
		return list;
	}

	public String loadAllAdmUsers(Map maptemp) {
		Connection conn = DBUtil.getConnection();
		String channel_uid=(String)maptemp.get("CHANNEL_UID");
		
		String sql=" select vc.fr as fr,vc.pfr as pfr,vc.account as account, vc.name as name,vc.user_sn as usersn, "+
					" ac.channel_uid as channel_uid,ac.authid as authid    "+
					" from v_at_auth_channel vc "+
					" left join at_adm_auth_channel ac on vc.account=ac.authid and ac.channel_uid='"+channel_uid+"' ";
		JSONArray jsonArr = new JSONArray();
		try {
			
			List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, sql);
			for (int i = 0; i < rsList.size(); i++) {
				Map<String, String> rsMap = rsList.get(i);
				JSONObject json = new JSONObject();
				json.put("id", rsMap.get("FR"));
				json.put("parentId", rsMap.get("PFR"));
				json.put("account", rsMap.get("ACCOUNT"));
				json.put("name",  rsMap.get("ACCOUNT")+"-"+rsMap.get("NAME"));
				if (rsMap.get("ACCOUNT").equals(rsMap.get("AUTHID"))) {
					json.put("checked", "true");
				}
				jsonArr.add(json);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return jsonArr.toString();
	}

	public String loadAllRecUsers(Map maptemp) {
		Connection conn = DBUtil.getConnection();
		String channel_uid=(String)maptemp.get("CHANNEL_UID");
		
		String sql=" select vc.fr as fr,vc.pfr as pfr,vc.account as account, vc.name as name,vc.user_sn as usersn, "+
					" ac.channel_uid as channel_uid,ac.recid as recid    "+
					" from v_at_auth_channel vc "+
					" left join at_rec_auth_channel ac on vc.account=ac.recid and ac.channel_uid='"+channel_uid+"' ";
		JSONArray jsonArr = new JSONArray();
		try {	
			List<Map<String, String>> rsList = DBUtil.queryReturnList(conn, sql);
			for (int i = 0; i < rsList.size(); i++) {
				Map<String, String> rsMap = rsList.get(i);
				JSONObject json = new JSONObject();
				json.put("id", rsMap.get("FR"));
				json.put("parentId", rsMap.get("PFR"));
				json.put("account", rsMap.get("ACCOUNT"));
				json.put("name", rsMap.get("ACCOUNT")+"-"+rsMap.get("NAME"));
				if(rsMap.get("ACCOUNT").equals(rsMap.get("AUTHID"))) {
					json.put("checked", "true");
				}
				jsonArr.add(json);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return  jsonArr.toString();
	}

	public void deleteAllUsers(String channelid) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			String deleteRecSql = "DELETE FROM AT_REC_AUTH_CHANNEL F WHERE F.channel_uid ='" + channelid + "'";
			DBUtil.exec(conn, deleteRecSql);

			String deleteAdmSql = "DELETE FROM AT_ADM_AUTH_CHANNEL F WHERE F.channel_uid ='" + channelid + "'";
			DBUtil.exec(conn, deleteAdmSql);
			
			conn.commit();

		} catch (Exception e) {
			DBUtil.rollbackConnetion(conn);
			e.printStackTrace();
			
		} finally {
			DBUtil.closeConnetion(conn);
		}
	}
	
		
}
