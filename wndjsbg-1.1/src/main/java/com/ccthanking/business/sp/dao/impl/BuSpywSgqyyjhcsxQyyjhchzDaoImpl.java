/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.person.BuSpywSgqyyjhcsxQyyjhchzDao.java
 * 创建日期： 2014-11-12 上午 11:31:47
 * 功能：   施工企业业绩核查汇总表
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-11-12 上午 11:31:47  谢晨玮   创建文件，实现基本功能
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

import com.ccthanking.business.commons.Utils;
import com.ccthanking.business.sp.dao.BuSpywSgqyyjhcsxQyyjhchzDao;
import com.ccthanking.business.spyw.vo.BuSpywSgqyyjhcsxQyyjhchzMxVO;
import com.ccthanking.business.spyw.vo.BuSpywSgqyyjhcsxQyyjhchzVO;
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
 * <p> BuSpywSgqyyjhcsxQyyjhchzDao.java </p>
 * <p> 功能：施工企业业绩核查汇总表 </p>
 *
 * <p><a href="BuSpywSgqyyjhcsxQyyjhchzDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">谢晨玮</a>
 * @version 0.1
 * @since 2014-11-12
 * 
 */

@Component
public class BuSpywSgqyyjhcsxQyyjhchzDaoImpl  extends BsBaseDaoTJdbc implements BuSpywSgqyyjhcsxQyyjhchzDao {

	private static Logger logger = LoggerFactory.getLogger(BuSpywSgqyyjhcsxQyyjhchzDaoImpl.class);
	private static String SQL_QUERY	="select sgqyyjhcsx_qyyjhchz_uid, ywlz_uid, zzlb, tbdw, tbr, tbrq, " +
			"event_uid, enabled, created_uid, created_name, created_date, update_uid, update_name, " +
			"update_date, serial_no from bu_spyw_sgqyyjhcsx_qyyjhchz";
  
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
            logger.error("施工企业业绩核查汇总表信息查询失败!");
            e.printStackTrace(System.out);	
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}
    
    public List<Map<String, String>> find(String id) throws Exception{
		
		Connection conn = DBUtil.getConnection();
        String ListMX="select sgqyyjhcsx_qyyjhchz_mx_uid, sgqyyjhcsx_qyyjhchz_uid, sbdwmc, xmmc, gcdz, zbsj, kjgkcsjsmsj, zbj, sjwcdw, zyjszb1, zyjszb2, sffsaqsg, sfysmsjyz,  describe from bu_spyw_sgqyyjhcsx_qyyjhchz_mx where sgqyyjhcsx_qyyjhchz_uid="+id+" order by sgqyyjhcsx_qyyjhchz_uid desc";
        
		List<Map<String, String>> bs = DBUtil.queryReturnList(conn, ListMX);
		
		DBUtil.closeConnetion(conn);
		return bs;
		
	} 
	public String findByZjId(String ywlz) throws Exception {
		Connection conn = DBUtil.getConnection();
		String s="";
        String sql="select SGQYYJHCSX_QYYJHCHZ_UID from BU_SPYW_SGQYYJHCSX_QYYJHCHZ where YWLZ_UID="+ywlz;
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
        BuSpywSgqyyjhcsxQyyjhchzVO vo = new BuSpywSgqyyjhcsxQyyjhchzVO();
        BuSpywSgqyyjhcsxQyyjhchzMxVO mvo=new BuSpywSgqyyjhcsxQyyjhchzMxVO();
        try{
        conn.setAutoCommit(false);
        JSONArray list = vo.doInitJson(json);
        JSONObject obj = (JSONObject) list.get(0);
        
      
        vo.setValueFromJson(obj);//把页面的值给属性
       
       
        vo.setSgqyyjhcsx_qyyjhchz_uid(DBUtil.getSequenceValue("SGQYYJHCSX_QYYJHCHZ_UID"));
        vo.setCreated_date(new Date());
        vo.setCreated_name(ActionContext.getCurrentUserInThread().getName());
        vo.setCreated_uid(ActionContext.getCurrentUserInThread().getAccount());
        
        vo.setSerial_no(vo.getSgqyyjhcsx_qyyjhchz_uid());
        BaseDAO.insert(conn, vo);
        

        JSONArray sqmxArray = null;
        try{
        	sqmxArray = obj.getJSONArray("SBDWMC");
        }catch(JSONException e){
        	logger.error("没有添加明细!");
        }
        //处理增项资质
        if(sqmxArray!=null){
        	for (int i = 0; i < sqmxArray.size(); i++) {//判断添加到子表的记录的数量
				if(StringUtils.isBlank((String) sqmxArray.get(i))){
					continue;
				}
				//获取索引
		        mvo.setSgqyyjhcsx_qyyjhchz_mx_uid(DBUtil.getSequenceValue("SGQYYJHCSX_QYYJHCHZ_MX_UID"));
		        mvo.setSgqyyjhcsx_qyyjhchz_uid(vo.getSgqyyjhcsx_qyyjhchz_uid());
		        mvo.setSbdwmc(obj.getJSONArray("SBDWMC").getString(i));
		        mvo.setXmmc(obj.getJSONArray("XMMC").getString(i));
				mvo.setGcdz(obj.getJSONArray("GCDZ").getString(i));
				mvo.setZbsj(Utils.formatToDate(obj.getJSONArray("ZBSJ").getString(i)));
				mvo.setKjgkcsjsmsj(obj.getJSONArray("KJGKCSJSMSJ").getString(i));
				mvo.setZbj(obj.getJSONArray("ZBJ").getString(i));
				
				mvo.setSjwcdw(obj.getJSONArray("SJWCDW").getString(i));
			    mvo.setZyjszb1(obj.getJSONArray("ZYJSZB1").getString(i));
				mvo.setZyjszb2(obj.getJSONArray("ZYJSZB2").getString(i));
				mvo.setSffsaqsg(obj.getJSONArray("SFFSAQSG").getString(i));
				mvo.setSfysmsjyz(obj.getJSONArray("SFYSMSJYZ").getString(i));
				mvo.setDescribe(obj.getJSONArray("DESCRIBE").getString(i));
				
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
            logger.error("墙施工企业业绩核查汇总表新增失败!");
        } finally {
            DBUtil.closeConnetion(conn);
        }
		return resultVO;
	}

	public String update(String json) throws Exception {
		Connection conn = DBUtil.getConnection();
        String resultVO = null;
        BuSpywSgqyyjhcsxQyyjhchzVO vo = new BuSpywSgqyyjhcsxQyyjhchzVO();
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
        		String clearMX_SQL = "delete from BU_SPYW_SGQYYJHCSX_QYYJHCHZ_MX t where t.SGQYYJHCSX_QYYJHCHZ_UID = "+vo.getSgqyyjhcsx_qyyjhchz_uid()+" and t.SGQYYJHCSX_QYYJHCHZ_MX_UID not in ("+MXList+")";
        		DBUtil.exec(conn, clearMX_SQL);

        	}else{
        		//删除所有材料信息
            	String clearCl_SQL = "delete from BU_SPYW_SGQYYJHCSX_QYYJHCHZ_MX t where t.SGQYYJHCSX_QYYJHCHZ_UID = "+ vo.getSgqyyjhcsx_qyyjhchz_uid();
        		DBUtil.exec(conn, clearCl_SQL);

        	}
            BaseDAO.update(conn, vo);

            resultVO = vo.getRowJson();
            
            conn.commit();
        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            e.printStackTrace(System.out);
            logger.error("施工企业业绩核查汇总表更改失败!");
        } finally {
            DBUtil.closeConnetion(conn);
        }
		return null;
	}
	public String getMX( Connection conn , JSONObject obj, BuSpywSgqyyjhcsxQyyjhchzVO vo) throws Exception{
		JSONArray MXArray = null;
		String emptyMX = "";
		BuSpywSgqyyjhcsxQyyjhchzMxVO MXVO = null;
        User user = ActionContext.getCurrentUserInThread();

        try{
        	MXArray = obj.getJSONArray("SBDWMC");
        }catch(JSONException e){
        	logger.error("没有添加明细!");
        }
        
        //处理增项资质
       
        if(MXArray!=null){
        	for (int i = 0; i < MXArray.size(); i++) {
				if(StringUtils.isBlank((String) MXArray.get(i))){
					continue;
				}
				//获取索引
				MXVO = new BuSpywSgqyyjhcsxQyyjhchzMxVO();
	            String zeng_uid = obj.getJSONArray("SGQYYJHCSX_QYYJHCHZ_MX_UID").getString(i);
	            boolean flag = true;
	            if(zeng_uid==null||"".equals(zeng_uid)){
	            	//若不存在uid则执行新增,反之进行修改
	            	flag = false;
	            }else{
	            	MXVO.setSgqyyjhcsx_qyyjhchz_mx_uid(zeng_uid);
	            }
	            MXVO.setSgqyyjhcsx_qyyjhchz_uid(vo.getSgqyyjhcsx_qyyjhchz_uid());
	            MXVO.setSbdwmc(obj.getJSONArray("SBDWMC").getString(i));
	            MXVO.setXmmc(obj.getJSONArray("XMMC").getString(i));
	            MXVO.setGcdz(obj.getJSONArray("GCDZ").getString(i));
	            MXVO.setZbsj(Utils.formatToDate(obj.getJSONArray("ZBSJ").getString(i)));
	            MXVO.setKjgkcsjsmsj(obj.getJSONArray("KJGKCSJSMSJ").getString(i));
	            MXVO.setZbj(obj.getJSONArray("ZBJ").getString(i));
	            
	            MXVO.setSjwcdw(obj.getJSONArray("SJWCDW").getString(i));
	            MXVO.setZyjszb1(obj.getJSONArray("ZYJSZB1").getString(i));
	            MXVO.setZyjszb2(obj.getJSONArray("ZYJSZB2").getString(i));
	            MXVO.setSffsaqsg(obj.getJSONArray("SFFSAQSG").getString(i));
	            MXVO.setSfysmsjyz(obj.getJSONArray("SFYSMSJYZ").getString(i));
	            MXVO.setDescribe(obj.getJSONArray("DESCRIBE").getString(i));
	            
	            
	           
			    
	            //插入申请明细信息,如果为修改则进行update,新增为insert
	            if(flag){
	            	
	            	BaseDAO.update(conn, MXVO);

	            }else{
	            	
	            	BaseDAO.insert(conn, MXVO);

	            
	            }
	      
	            //User  user = ActionContext.getCurrentUserInThread();
	            //插入申请明细信息,如果为修改则进行update,新增为insert
	           
	            emptyMX += "'"+MXVO.getSgqyyjhcsx_qyyjhchz_mx_uid()+"',";
			}
        }
        return emptyMX;
	}
    // 在此可加入其它方法

}
