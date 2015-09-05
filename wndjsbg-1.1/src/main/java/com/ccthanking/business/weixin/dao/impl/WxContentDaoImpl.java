package com.ccthanking.business.weixin.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.ccthanking.business.weixin.dao.WxContentDao;
import com.ccthanking.business.weixin.service.impl.WxContentServiceImpl;
import com.ccthanking.weixin.vo.WxContentVO;
import com.copj.modules.utils.exception.DaoException;

@Component
public class WxContentDaoImpl  extends BsBaseDaoTJdbc implements WxContentDao{
	private static Logger logger = LoggerFactory.getLogger(WxContentServiceImpl.class);

	public String queryCondition(String json, WxContentVO vo) {
		User user = ActionContext.getCurrentUserInThread();
		Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {
            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            
            //加入限制，只查看本人的和栏目权限范围内的
//            condition +=" and t.channel_uid in ( select ac.channel_uid from at_adm_auth_channel ac where ac.authid='"+user.getAccount()+"' )" ;
            condition +="  and t.created_uid='"+user.getAccount()+"'";
            
            String orderFilter = RequestUtil.getOrderFilter(json);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            String sql = "select * from (select t.*,(select c.channel_name from wx_channel c where c.channel_uid=t.channel_uid) as channel_name from wx_content t) t";
            BaseResultSet bs = DBUtil.query(conn, sql, page);

            // 设置查询条件
            bs.setFieldDateFormat("PUBLISH_TIME", "yyyy-MM-dd HH:mm:ss");
            bs.setFieldDateFormat("UPDATE_DATE", "yyyy-MM-dd HH:mm:ss");
            // bs.setFieldThousand("DYJLSDZ");
            bs.setFieldDic("ENABLED", "SF");
            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询内容信息出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}

	public List<Map<String, String>> listCondition(WxContentVO vo,String eventKey,int rownum) {
		List<Map<String, String>> list=null;
		Connection conn = DBUtil.getConnection();
		try{
			
			String sql="select a.*,rownum idnum from ("+
				 " select ct.content_uid,ct.content_title,ct.content_pic,ct.channel_uid ," +
				 " cl.channel_url,ct.content_stxt "+
				 " from wx_content ct  "+
				 " join wx_channel cl on cl.channel_uid=ct.channel_uid  "+
				 " where ct.enabled=1 and ct.publish_time <= sysdate and cl.eventkey='"+eventKey+"'" +
				 " order by ct.publish_time desc ) a  "+
				 " where rownum <="+rownum;
			
			list =DBUtil.queryReturnList(conn, sql);
					
		  } catch (Exception e) {
	          DaoException.handleMessageException("*********查询内容信息出错!*********");
	      } finally {
	          DBUtil.closeConnetion(conn);
	      }
		return list;
	}

	public List<Map<String, String>> listContent(WxContentVO vo,
			String channelid, int rownum) {
		List<Map<String, String>> list=null;
		Connection conn = DBUtil.getConnection();
		try{
			
			String sql="select a.*,rownum idnum from ("+
				 " select ct.content_uid,ct.content_title,ct.content_pic,ct.channel_uid ," +
				 "(select cl.channel_name from wx_channel cl where cl.channel_uid=ct.channel_uid) as channelname," +
				 " ct.content_stxt,ct.publish_time "+
				 " from wx_content ct  "+
				 " where ct.enabled=1 and ct.publish_time <= sysdate and ct.channel_uid='"+channelid+"'" +
				 " order by ct.publish_time desc ) a  "+
				 " where rownum <="+rownum;
			
			list =DBUtil.queryReturnList(conn, sql);
					
		  } catch (Exception e) {
	          DaoException.handleMessageException("*********查询内容信息出错!*********");
	      } finally {
	          DBUtil.closeConnetion(conn);
	      }
		return list;
	}
		
}
