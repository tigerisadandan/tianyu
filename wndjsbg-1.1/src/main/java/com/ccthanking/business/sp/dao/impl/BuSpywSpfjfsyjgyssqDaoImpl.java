/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.sp.BuSpywSpfjfsyjgyssqDao.java
 * 创建日期： 2014-06-09 下午 01:59:10
 * 功能：   商品房交付使用竣工验收申请
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-09 下午 01:59:10  蒋根亮   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.sp.dao.impl;

import java.sql.Connection;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ccthanking.business.sp.dao.BuSpywSpfjfsyjgyssqDao;
import com.ccthanking.business.spyw.vo.BuSpywSpfjfsyjgyssqPtmxVO;
import com.ccthanking.business.spyw.vo.BuSpywSpfjfsyjgyssqVO;
import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;


/**
 * <p> BuSpywSpfjfsyjgyssqDao.java </p>
 * <p> 功能：商品房交付使用竣工验收申请 </p>
 *
 * <p><a href="BuSpywSpfjfsyjgyssqDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:jianggl88@163.com">蒋根亮</a>
 * @version 0.1
 * @since 2014-06-09
 * 
 */

@Component
public class BuSpywSpfjfsyjgyssqDaoImpl  extends BsBaseDaoTJdbc implements BuSpywSpfjfsyjgyssqDao {

	private static Logger logger = LoggerFactory.getLogger(BuSpywSpfjfsyjgyssqDaoImpl.class);
	private static String SQL_QUERY="select spfjfsyjgyssq_uid, ywlz_uid, xmmc, dwmc, txdz, frdb, lxdhjcz," +
			" lxr, lxsj, xmxz, xmdz, xmhtjfsj_date, jfxz, gc_ghkj, dc_ghkj, dxs_ghkj, hj_ghkj," +
			" gjptmj_ghkj, gc_sqys, dc_sqys, dzs_sqys, hj_sqys, gcdh_sqys, dcdh_sqys, dxsdh_sqys," +
			" hjdh_sqys from bu_spyw_spfjfsyjgyssq";

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
            logger.error("商品房交付使用信息查询失败!");
            e.printStackTrace(System.out);	
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;
	}

	
	public String insert(String json) throws Exception {
		Connection conn = DBUtil.getConnection();
        String resultVO = null;
        BuSpywSpfjfsyjgyssqVO vo = new BuSpywSpfjfsyjgyssqVO();
        BuSpywSpfjfsyjgyssqPtmxVO mvo=new BuSpywSpfjfsyjgyssqPtmxVO();
        try{
        conn.setAutoCommit(false);
        JSONArray list = vo.doInitJson(json);
        JSONObject obj = (JSONObject) list.get(0);
        
      
        vo.setValueFromJson(obj);//把页面的值给属性
       
       
        vo.setSpfjfsyjgyssq_uid(DBUtil.getSequenceValue("SPFJFSYJGYSSQ_UID"));
        vo.setCreated_date(new Date());
        vo.setCreated_name(ActionContext.getCurrentUserInThread().getName());
        vo.setCreated_uid(ActionContext.getCurrentUserInThread().getUserSN());
        
        vo.setSerial_no(vo.getSpfjfsyjgyssq_uid());
        BaseDAO.insert(conn, vo);
        

        JSONArray sqmxArray = null;
        try{
        	sqmxArray = obj.getJSONArray("XMMC_MX");
        }catch(JSONException e){
        	logger.error("没有添加公建配套验收信息!");
        }
        //处理增项资质
        if(sqmxArray!=null){
        	for (int i = 0; i < sqmxArray.size(); i++) {//判断添加到子表的记录的数量
				if(StringUtils.isBlank((String) sqmxArray.get(i))){
					continue;
				}
				//获取索引
		        mvo.setGjptysmx_uid(DBUtil.getSequenceValue("GJPTYSMX_UID"));
		        mvo.setSpfjfsyjgyssq_uid(vo.getSpfjfsyjgyssq_uid());
		        mvo.setXmmc(obj.getJSONArray("XMMC_MX").getString(i));
		        mvo.setJhmj(obj.getJSONArray("JHMJ").getString(i));
				mvo.setGs(obj.getJSONArray("GS").getString(i));
				mvo.setJgmj(obj.getJSONArray("JGMJ").getString(i));
				mvo.setWz(obj.getJSONArray("WZ").getString(i));
				
				//DBUtil.getSequenceValue(seqname)
	            
	            //插入用户/企业
				mvo.setCreated_date(new Date());
				mvo.setCreated_name(ActionContext.getCurrentUserInThread().getName());
				mvo.setCreated_uid(ActionContext.getCurrentUserInThread().getUserSN());
				
	            //排序号
				//voZhengshuVO.setSerial_no(voZhengshuVO.getSg_person_zhengshu_uid());
	            //插入增项资质信息
	            BaseDAO.insert(conn, mvo);

			}
        }
        resultVO = mvo.getRowJson();
        
        conn.commit();
        }catch(Exception e){
        	
        	DBUtil.rollbackConnetion(conn);
            e.printStackTrace(System.out);
            logger.error("商品房交付使用信息表新增失败!");
        } finally {
            DBUtil.closeConnetion(conn);
        }
		return resultVO;
	}

	public String update(String json) throws Exception {
		Connection conn = DBUtil.getConnection();
        String resultVO = null;
        BuSpywSpfjfsyjgyssqVO vo = new BuSpywSpfjfsyjgyssqVO();
        User user = ActionContext.getCurrentUserInThread();


        try {
            conn.setAutoCommit(false);
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject) list.get(0);
            vo.setValueFromJson(obj);//把页面的值给属性
            
            
            vo.setUpdate_date(new Date());
            vo.setUpdate_name(user.getName());
            vo.setUpdate_uid(ActionContext.getCurrentUserInThread().getUserSN());
            
        	
            //增加资格证书
            String MXList = getMX(conn, obj, vo);
            if(!"".equals(MXList)){
            	MXList = MXList.substring(0,MXList.length()-1);
        		//清楚多余页面被删掉的资格证书
        		String clearMX_SQL = "delete from bu_spyw_spfjfsyjgyssq_ptmx t where t.spfjfsyjgyssq_uid = "+vo.getSpfjfsyjgyssq_uid()+" and t.gjptysmx_uid not in ("+MXList+")";
        		DBUtil.exec(conn, clearMX_SQL);

        	}else{
        		//删除所有资格证书
            	String clearzhengshu_SQL = "delete from bu_spyw_spfjfsyjgyssq_ptmx t where t.spfjfsyjgyssq_uid = "+ vo.getSpfjfsyjgyssq_uid();
        		DBUtil.exec(conn, clearzhengshu_SQL);

        	}
            BaseDAO.update(conn, vo);

            resultVO = vo.getRowJson();
            
            conn.commit();
        } catch (Exception e) {
            DBUtil.rollbackConnetion(conn);
            e.printStackTrace(System.out);
            logger.error("商品房交付使用竣工信息表更改失败!");
        } finally {
            DBUtil.closeConnetion(conn);
        }
		return null;
	}
	/**
	 * insert明细
	 * @param conn
	 * @param obj
	 * @param vo
	 * @throws Exception
	 */
	public String getMX( Connection conn , JSONObject obj, BuSpywSpfjfsyjgyssqVO vo) throws Exception{
		JSONArray MXArray = null;
		String emptyMX = "";
		BuSpywSpfjfsyjgyssqPtmxVO MXVO = null;
        User user = ActionContext.getCurrentUserInThread();

        try{
        	MXArray = obj.getJSONArray("XMMC_MX");
        }catch(JSONException e){
        	logger.error("没有添加公建配套验收信息!");
        }
       
        //处理增项资质
       
        if(MXArray!=null){
        	for (int i = 0; i < MXArray.size(); i++) {
				if(StringUtils.isBlank((String) MXArray.get(i))){
					continue;
				}
				//获取索引
				MXVO = new BuSpywSpfjfsyjgyssqPtmxVO();
	            String zeng_uid = obj.getJSONArray("GJPTYSMX_UID").getString(i);
	            boolean flag = true;
	            if(zeng_uid==null||"".equals(zeng_uid)){
	            	//若不存在uid则执行新增,反之进行修改
	            	flag = false;
	            	//MXVO.setGjptysmx_uid(DBUtil.getSequenceValue("GJPTYSMX_UID"));
	            }else{
	            	MXVO.setGjptysmx_uid(zeng_uid);
	            }
	            
	          
	            
	            MXVO.setSpfjfsyjgyssq_uid(vo.getSpfjfsyjgyssq_uid());
	            MXVO.setXmmc(obj.getJSONArray("XMMC_MX").getString(i));
	            MXVO.setJhmj(obj.getJSONArray("JHMJ").getString(i));
	            MXVO.setGs(obj.getJSONArray("GS").getString(i));
	            MXVO.setJgmj(obj.getJSONArray("JGMJ").getString(i));
	            MXVO.setWz(obj.getJSONArray("WZ").getString(i));
	            //User  user = ActionContext.getCurrentUserInThread();

	           
	            //插入增项资质信息,如果为修改则进行update,新增为insert
	            if(flag){
	            	//插入用户/企业
	            	MXVO.setUpdate_date(new Date());
	            	MXVO.setUpdate_name(ActionContext.getCurrentUserInThread().getName());
	            	MXVO.setUpdate_uid(ActionContext.getCurrentUserInThread().getUserSN());
	            	BaseDAO.update(conn, MXVO);

	            }else{
	            	//更新用户/企业
	            	MXVO.setCreated_date(new Date());
	            	MXVO.setCreated_name(ActionContext.getCurrentUserInThread().getName());
	            	MXVO.setCreated_uid(ActionContext.getCurrentUserInThread().getUserSN());
		             
	            	BaseDAO.insert(conn, MXVO);

	            
	            }
	      
	            
	            emptyMX += "'"+MXVO.getGjptysmx_uid()+"',";
			}
        }
        return emptyMX;
	}
    
   
}
