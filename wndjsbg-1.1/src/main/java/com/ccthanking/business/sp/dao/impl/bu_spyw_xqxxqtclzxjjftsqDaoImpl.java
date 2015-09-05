/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.person.bu_spyw_xqxxqtclzxjjftsqDao.java
 * 创建日期： 2014-10-27 下午 06:40:51
 * 功能：   墙改基金征收及返退审批事项
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-10-27 下午 06:40:51  谢晨玮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sp.dao.impl;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ccthanking.business.sp.dao.bu_spyw_xqxxqtclzxjjftsqDao;
import com.ccthanking.business.spyw.vo.BuSpywXqxxqtclzxjjftsqClVO;
import com.ccthanking.business.spyw.vo.BuSpywXqxxqtclzxjjftsqVO;
import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;


/**
 * <p> bu_spyw_xqxxqtclzxjjftsqDao.java </p>
 * <p> 功能：墙改基金征收及返退审批事项 </p>
 *
 * <p><a href="bu_spyw_xqxxqtclzxjjftsqDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">谢晨玮</a>
 * @version 0.1
 * @since 2014-10-27
 * 
 */

@Component
public class bu_spyw_xqxxqtclzxjjftsqDaoImpl  extends BsBaseDaoTJdbc implements bu_spyw_xqxxqtclzxjjftsqDao {
	private static Logger logger = LoggerFactory.getLogger(bu_spyw_xqxxqtclzxjjftsqDaoImpl.class);
	private static String SQL_QUERY	="select xqxxqtclzxjjft_uid, xmb, sqdw, sqrq, gcmc, lxr, ghxkzbh, sgxkzbh, lxdh, khyhjzh, bjmj, sjmj, " +
		"jhyskje, ysssyl, ntqcsyl, ftqksm, qzqksm, fntxcl, fntxcljrd, fntsjxcl, fntsjxcljrz, ntzkz, ntkxz," +
		" ntsxz, cmjbj, qtbj, yftje, bjje, sjftje, trftje, trftjedx, hyjy, fhr, jbr, ldyj, event_uid, enabled, " +
		"describe, created_uid, created_name, created_date, update_uid, update_name, update_date, serial_no, " +
		"xh from bu_spyw_xqxxqtclzxjjftsq";
  
    public String queryCondition(String json) throws Exception { 
		User user = ActionContext.getCurrentUserInThread();
	    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

        	  PageManager page = RequestUtil.getPageManager(json);//获取查询后得到的记录数通过Util中的方法进行分页
              String condition = RequestUtil.getConditionList(json).getConditionWhere();//获取查询的语句

              String orderFilter = RequestUtil.getOrderFilter(json);//获取排序方法
              condition += orderFilter;//把查询出来的数据进行排序
              
              if (page == null)
                  page = new PageManager();
              page.setFilter(condition);

              conn.setAutoCommit(false);

             
              BaseResultSet bs = DBUtil.query(conn, SQL_QUERY, page);//连接数据库，进行查询，结果集给bs
             
              domresult = bs.getJson();//把转换好的数据给domresult
          } catch (Exception e) {
        	DBUtil.rollbackConnetion(conn);
            logger.error("墙改基金征收及返退审批事项信息查询失败!");
            e.printStackTrace(System.out);	
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}
    
    public List<Map<String, String>> find(String id) throws Exception{
		
		Connection conn = DBUtil.getConnection();
        String ListMX="select cl_uid, xqxxqtclzxjjft_uid, lb, qtbw, clpzjmc, zsbh, sjsylhjldw, zbz from bu_spyw_xqxxqtclzxjjftsq_cl where xqxxqtclzxjjft_uid="+id+"order by xqxxqtclzxjjft_uid desc";
        
		List<Map<String, String>> bs = DBUtil.queryReturnList(conn, ListMX);
		
		DBUtil.closeConnetion(conn);
		return bs;
		
	} 
	public String findByZjId(String ywlz) throws Exception {
		Connection conn = DBUtil.getConnection();
		String s="";
        String sql="select xqxxqtclzxjjft_uid from bu_spyw_xqxxqtclzxjjftsq where YWLZ_UID="+ywlz;
		String[][] res = DBUtil.query(conn, sql);
		if(res!=null){
			 s = res[0][0];
	         
		}
		
		DBUtil.closeConnetion(conn);
		return s;
	}
	public String insert(String json) throws Exception {
		Connection conn = DBUtil.getConnection();
        String resultVO = null;
        BuSpywXqxxqtclzxjjftsqVO vo = new BuSpywXqxxqtclzxjjftsqVO();
        BuSpywXqxxqtclzxjjftsqClVO mvo=new BuSpywXqxxqtclzxjjftsqClVO();
        try{
        conn.setAutoCommit(false);
        JSONArray list = vo.doInitJson(json);
        JSONObject obj = (JSONObject) list.get(0);
        
      
        vo.setValueFromJson(obj);//把页面的值给属性
       
       
        vo.setXqxxqtclzxjjft_uid(DBUtil.getSequenceValue("XQXXQTCLZXJJFT_UID"));
        vo.setCreated_date(new Date());
        vo.setCreated_name(ActionContext.getCurrentUserInThread().getName());
        vo.setCreated_uid(ActionContext.getCurrentUserInThread().getAccount());
        
        vo.setSerial_no(vo.getXqxxqtclzxjjft_uid());
        BaseDAO.insert(conn, vo);
        

        JSONArray sqmxArray = null;
        try{
        	sqmxArray = obj.getJSONArray("QTBW");
        }catch(JSONException e){
        	logger.error("没有添加材料信息!");
        }
        //处理增项资质
        if(sqmxArray!=null){
        	for (int i = 0; i < sqmxArray.size(); i++) {//判断添加到子表的记录的数量
				if(StringUtils.isBlank((String) sqmxArray.get(i))){
					continue;
				}
				//获取索引
		        mvo.setCl_uid(DBUtil.getSequenceValue("CL_UID"));
		        mvo.setXqxxqtclzxjjft_uid(vo.getXqxxqtclzxjjft_uid());
		        mvo.setLb(obj.getJSONArray("LB").getString(i));
		        mvo.setQtbw(obj.getJSONArray("QTBW").getString(i));
				mvo.setClpzjmc(obj.getJSONArray("CLPZJMC").getString(i));
				mvo.setZsbh(obj.getJSONArray("ZSBH").getString(i));
				mvo.setSjsylhjldw(obj.getJSONArray("SJSYLHJLDW").getString(i));
				mvo.setZbz(obj.getJSONArray("ZBZ").getString(i));
				
				//DBUtil.getSequenceValue(seqname)
	            
	          
	            //排序号
				//voZhengshuVO.setSerial_no(voZhengshuVO.getSg_person_zhengshu_uid());
	            //插入增项资质信息
	            BaseDAO.insert(conn, mvo);

			}
        }
        resultVO = vo.getRowJson();
        
        conn.commit();
        }catch(Exception e){
        	
        	DBUtil.rollbackConnetion(conn);
            e.printStackTrace(System.out);
            logger.error("墙改基金征收及返退审批事项信息表新增失败!");
        } finally {
            DBUtil.closeConnetion(conn);
        }
		return resultVO;
	}

	public String update(String json) throws Exception {
		Connection conn = DBUtil.getConnection();
        String resultVO = null;
        BuSpywXqxxqtclzxjjftsqVO vo = new BuSpywXqxxqtclzxjjftsqVO();
        User user = ActionContext.getCurrentUserInThread();


        try {
            conn.setAutoCommit(false);
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject) list.get(0);
            vo.setValueFromJson(obj);//把页面的值给属性
            
           /* SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date xmhtjfsj = sdf.parse(obj.get("XMHTJFSJ_DATE_SV").toString());
            vo.setXmhtjfsj_date(xmhtjfsj);*/
            
            vo.setUpdate_date(new Date());
            vo.setUpdate_name(user.getName());
            vo.setUpdate_uid(ActionContext.getCurrentUserInThread().getAccount());
            
        	
            //增加材料信息
            String MXList = getMX(conn, obj, vo);
            if(!"".equals(MXList)){
            	MXList = MXList.substring(0,MXList.length()-1);
        		//清除多余页面被删掉的材料信息
        		String clearMX_SQL = "delete from BU_SPYW_XQXXQTCLZXJJFTSQ_CL t where t.XQXXQTCLZXJJFT_UID = "+vo.getXqxxqtclzxjjft_uid()+" and t.CL_UID not in ("+MXList+")";
        		DBUtil.exec(conn, clearMX_SQL);

        	}else{
        		//删除所有材料信息
            	String clearCl_SQL = "delete from BU_SPYW_XQXXQTCLZXJJFTSQ_CL t where t.XQXXQTCLZXJJFT_UID = "+ vo.getXqxxqtclzxjjft_uid();
        		DBUtil.exec(conn, clearCl_SQL);

        	}
            BaseDAO.update(conn, vo);

            resultVO = vo.getRowJson();
            
            conn.commit();
        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            e.printStackTrace(System.out);
            logger.error("墙改基金征收及返退审批事项信息表更改失败!");
        } finally {
            DBUtil.closeConnetion(conn);
        }
		return null;
	}
	public String getMX( Connection conn , JSONObject obj, BuSpywXqxxqtclzxjjftsqVO vo) throws Exception{
		JSONArray MXArray = null;
		String emptyMX = "";
		BuSpywXqxxqtclzxjjftsqClVO MXVO = null;
        User user = ActionContext.getCurrentUserInThread();

        try{
        	MXArray = obj.getJSONArray("QTBW");
        }catch(JSONException e){
        	logger.error("没有添加材料信息!");
        }
        
        //处理增项资质
       
        if(MXArray!=null){
        	for (int i = 0; i < MXArray.size(); i++) {
				if(StringUtils.isBlank((String) MXArray.get(i))){
					continue;
				}
				//获取索引
				MXVO = new BuSpywXqxxqtclzxjjftsqClVO();
	            String zeng_uid = obj.getJSONArray("CL_UID").getString(i);
	            boolean flag = true;
	            if(zeng_uid==null||"".equals(zeng_uid)){
	            	//若不存在uid则执行新增,反之进行修改
	            	flag = false;
	            }else{
	            	MXVO.setCl_uid(zeng_uid);
	            }
	            MXVO.setXqxxqtclzxjjft_uid(vo.getXqxxqtclzxjjft_uid());
	            MXVO.setLb(obj.getJSONArray("LB").getString(i));
	            MXVO.setQtbw(obj.getJSONArray("QTBW").getString(i));
	            MXVO.setClpzjmc(obj.getJSONArray("CLPZJMC").getString(i));
	            MXVO.setZsbh(obj.getJSONArray("ZSBH").getString(i));
	            MXVO.setSjsylhjldw(obj.getJSONArray("SJSYLHJLDW").getString(i));
	            MXVO.setZbz(obj.getJSONArray("ZBZ").getString(i));
	            
	            //插入申请明细信息,如果为修改则进行update,新增为insert
	            if(flag){
	            	
	            	BaseDAO.update(conn, MXVO);

	            }else{
	            	
	            	BaseDAO.insert(conn, MXVO);

	            
	            }
	      
	            //User  user = ActionContext.getCurrentUserInThread();
	            //插入申请明细信息,如果为修改则进行update,新增为insert
	           
	            emptyMX += "'"+MXVO.getCl_uid()+"',";
			}
        }
        return emptyMX;
	}
    // 在此可加入其它方法

}
