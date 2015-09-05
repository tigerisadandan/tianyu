/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.person.bu_spyw_spfjfsyjgysbadtjzhzDao.java
 * 创建日期： 2014-11-06 下午 03:56:47
 * 功能：   商品房交付使用竣工验收备案单体建筑汇总
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-11-06 下午 03:56:47  谢晨玮   创建文件，实现基本功能
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

import com.ccthanking.business.sp.dao.bu_spyw_spfjfsyjgysbadtjzhzDao;
import com.ccthanking.business.spyw.vo.BuSpywSpfjfsyjgysbadtjzhzDtVO;
import com.ccthanking.business.spyw.vo.BuSpywSpfjfsyjgysbadtjzhzVO;
import com.ccthanking.business.spyw.vo.BuSpywSpfjfsyjgysxmqkMxVO;
import com.ccthanking.business.spyw.vo.BuSpywSpfjfsyjgysxmqkVO;
import com.ccthanking.common.BusinessUtil;
import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> bu_spyw_spfjfsyjgysbadtjzhzDao.java </p>
 * <p> 功能：商品房交付使用竣工验收备案单体建筑汇总 </p>
 *
 * <p><a href="bu_spyw_spfjfsyjgysbadtjzhzDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">谢晨玮</a>
 * @version 0.1
 * @since 2014-11-06
 * 
 */

@Component
public class bu_spyw_spfjfsyjgysbadtjzhzDaoImpl  extends BsBaseDaoTJdbc implements bu_spyw_spfjfsyjgysbadtjzhzDao {

	private static Logger logger = LoggerFactory.getLogger(bu_spyw_spfjfsyjgysbadtjzhzDaoImpl.class);
	private static String SQL_QUERY	="select spfjfsyjgysbadtjzhz_uid, ywlz_uid, dwmc, dwgz, tbrq, event_uid, " +
			"enabled, describe, created_uid, created_name, created_date, update_uid, update_name, update_date, " +
			"serial_no from bu_spyw_spfjfsyjgysbadtjzhz";
		
    public String queryCondition(String json){
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
            logger.error("商品房交付使用竣工验收备案单体建筑汇总表信息查询失败!");
            e.printStackTrace(System.out);	
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
    }
    public String findByZjId(String ywlz) throws Exception {
		Connection conn = DBUtil.getConnection();
		String s="";
        String sql="select spfjfsyjgysbadtjzhz_uid, ywlz_uid, dwmc, dwgz, tbrq, event_uid, enabled, describe, created_uid, created_name, created_date, update_uid, update_name, update_date, serial_no from bu_spyw_spfjfsyjgysbadtjzhz where YWLZ_UID="+ywlz;
		String[][] res = DBUtil.query(conn, sql);
		if(res!=null){
			 s = res[0][0];
	         
		}
		
		DBUtil.closeConnetion(conn);
		return s;
	}
    // 在此可加入其它方法
    public String insert(String json) throws Exception {
		Connection conn = DBUtil.getConnection();
        String resultVO = null;
        BuSpywSpfjfsyjgysbadtjzhzVO vo = new BuSpywSpfjfsyjgysbadtjzhzVO();
        BuSpywSpfjfsyjgysbadtjzhzDtVO mvo=new BuSpywSpfjfsyjgysbadtjzhzDtVO();
        try{
        conn.setAutoCommit(false);
        JSONArray list = vo.doInitJson(json);
        JSONObject obj = (JSONObject) list.get(0);
        
      
        vo.setValueFromJson(obj);//把页面的值给属性
       
       
        vo.setSpfjfsyjgysbadtjzhz_uid(DBUtil.getSequenceValue("SPFJFSYJGYSBADTJZHZ_UID"));
        vo.setCreated_date(new Date());
        vo.setCreated_name(ActionContext.getCurrentUserInThread().getName());
        vo.setCreated_uid(ActionContext.getCurrentUserInThread().getAccount());
        
        vo.setSerial_no(vo.getSpfjfsyjgysbadtjzhz_uid());
        BaseDAO.insert(conn, vo);
        

        JSONArray sqmxArray = null;
        try{
        	sqmxArray = obj.getJSONArray("JGDTDH");
        }catch(JSONException e){
        	logger.error("没有项目单体建筑信息!");
        }
        //处理增项资质
        if(sqmxArray!=null){
        	for (int i = 0; i < sqmxArray.size(); i++) {//判断添加到子表的记录的数量
				if(StringUtils.isBlank((String) sqmxArray.get(i))){
					continue;
				}
				//获取索引
		        mvo.setSpfjfsyjgysbadtjzhz_dt_uid(DBUtil.getSequenceValue("SPFJFSYJGYSBADTJZHZ_DT_UID"));
		        mvo.setSpfjfsyjgysbadtjzhz_uid(vo.getSpfjfsyjgysbadtjzhz_uid());
		        mvo.setUnits_uid(DBUtil.getSequenceValue("UNITS_UID"));
		        mvo.setJgdtdh(obj.getJSONArray("JGDTDH").getString(i));
				mvo.setJgmj(obj.getJSONArray("JGMJ").getString(i));
				mvo.setHs(obj.getJSONArray("HS").getString(i));
				mvo.setCs(obj.getJSONArray("CS").getString(i));
				mvo.setZgd(obj.getJSONArray("ZGD").getString(i));
			
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
            logger.error("商品房交付使用竣工验收备案单体建筑汇总表新增失败!");
        } finally {
            DBUtil.closeConnetion(conn);
        }
		return resultVO;
	}
    
    public String update(String json) throws Exception {
		Connection conn = DBUtil.getConnection();
        String resultVO = null;
        BuSpywSpfjfsyjgysbadtjzhzVO vo = new BuSpywSpfjfsyjgysbadtjzhzVO();
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
        		String clearMX_SQL = "delete from BU_SPYW_SPFJFSYJGYSBADTJZHZ_DT t where t.SPFJFSYJGYSBADTJZHZ_UID = "+vo.getSpfjfsyjgysbadtjzhz_uid()+" and t.SPFJFSYJGYSBADTJZHZ_DT_UID not in ("+MXList+")";
        		DBUtil.exec(conn, clearMX_SQL);

        	}else{
        		//删除所有材料信息
            	String clearCl_SQL = "delete from BU_SPYW_SPFJFSYJGYSBADTJZHZ_DT t where t.SPFJFSYJGYSBADTJZHZ_UID = "+ vo.getSpfjfsyjgysbadtjzhz_uid();
        		DBUtil.exec(conn, clearCl_SQL);

        	}
            BaseDAO.update(conn, vo);

            resultVO = vo.getRowJson();
            
            conn.commit();
        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            e.printStackTrace(System.out);
            logger.error("商品房交付使用竣工验收备案单体建筑汇总表修改失败!");
        } finally {
            DBUtil.closeConnetion(conn);
        }
		return null;
	}
	public String getMX( Connection conn , JSONObject obj, BuSpywSpfjfsyjgysbadtjzhzVO vo) throws Exception{
		JSONArray MXArray = null;
		String emptyMX = "";
		BuSpywSpfjfsyjgysbadtjzhzDtVO MXVO = null;
        User user = ActionContext.getCurrentUserInThread();

        try{
        	MXArray = obj.getJSONArray("JGDTDH");
        }catch(JSONException e){
        	logger.error("没有项目单体建筑信息!");
        }
        
        //处理增项资质
       
        if(MXArray!=null){
        	for (int i = 0; i < MXArray.size(); i++) {
				if(StringUtils.isBlank((String) MXArray.get(i))){
					continue;
				}
				//获取索引
				MXVO = new BuSpywSpfjfsyjgysbadtjzhzDtVO();
	            String zeng_uid = obj.getJSONArray("SPFJFSYJGYSBADTJZHZ_DT_UID").getString(i);
	            boolean flag = true;
	            if(zeng_uid==null||"".equals(zeng_uid)){
	            	//若不存在uid则执行新增,反之进行修改
	            	flag = false;
	            }else{
	            	MXVO.setSpfjfsyjgysbadtjzhz_dt_uid(zeng_uid);
	            }
	            MXVO.setSpfjfsyjgysbadtjzhz_uid(vo.getSpfjfsyjgysbadtjzhz_uid());
	            MXVO.setJgdtdh(obj.getJSONArray("JGDTDH").getString(i));
	            MXVO.setJgmj(obj.getJSONArray("JGMJ").getString(i));
	            MXVO.setHs(obj.getJSONArray("HS").getString(i));
	            MXVO.setCs(obj.getJSONArray("CS").getString(i));
	            MXVO.setZgd(obj.getJSONArray("ZGD").getString(i));
	            MXVO.setUnits_uid(obj.getJSONArray("UNITS_UID").getString(i));
	           
	            //插入申请明细信息,如果为修改则进行update,新增为insert
	            if(flag){
	            	
	            	BaseDAO.update(conn, MXVO);

	            }else{
	            	
	            	BaseDAO.insert(conn, MXVO);

	            
	            }
	      
	            //User  user = ActionContext.getCurrentUserInThread();
	            //插入申请明细信息,如果为修改则进行update,新增为insert
	           
	            emptyMX += "'"+MXVO.getSpfjfsyjgysbadtjzhz_dt_uid()+"',";
			}
        }
        return emptyMX;
	}

}
