/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    yhzg.service.ZgTzdService.java
 * 创建日期： 2015-04-21 下午 02:41:29
 * 功能：    接口：整改通知单
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-21 下午 02:41:29  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.yhzg.service.impl;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ccthanking.framework.Constants;
import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;

import com.ccthanking.business.dtgl.yhzg.vo.ZgContentVO;
import com.ccthanking.business.dtgl.yhzg.vo.ZgTzdVO;
import com.ccthanking.business.yhzg.dao.ZgTzdDao;
import com.ccthanking.business.yhzg.service.ZgTzdService;
import com.ccthanking.common.util.FreemarkerHelper;
import com.ccthanking.common.util.Word2PDF;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> ZgTzdService.java </p>
 * <p> 功能：整改通知单 </p>
 *
 * <p><a href="ZgTzdService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-04-21
 * 
 */

@Service
public class ZgTzdServiceImpl extends Base1ServiceImpl<ZgTzdVO, String> implements ZgTzdService {

	private static Logger logger = LoggerFactory.getLogger(ZgTzdServiceImpl.class);
	
    
    private ZgTzdDao zgTzdDao;

    // @Override
    public String query(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = zgTzdDao.queryCondition01(json, null, null);

        }catch (DaoException e) {
        	logger.error("整改通知单{}", e.getMessage());
			SystemException.handleMessageException("整改通知单查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    // @Override
    public String queryCondition(String json, String condition2) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    	String userUid = user.getUserSN();
    	Map map =new HashMap();
    	map.put("condition2", condition2);
    	map.put("userUid", userUid);
        String domresult = "";
        try {
			domresult = zgTzdDao.queryCondition(json,map);
        }catch (DaoException e) {
        	logger.error("整改通知单{}", e.getMessage());
			SystemException.handleMessageException("整改通知单查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    // @Override
    public String queryZGD2(String zhenggai, String deptUid, String before,String after,String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {
			domresult = zgTzdDao.queryZGD2(zhenggai,deptUid,before,after,json);
        }catch (DaoException e) {
        	logger.error("整改通知单{}", e.getMessage());
			SystemException.handleMessageException("整改通知单查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    // @Override
    public String getDeptName(String ORGANIZE_UID) throws Exception {
    	
    	User user = ActionContext.getCurrentUserInThread();
    	
    	String domresult = "";
    	try {
    		domresult = zgTzdDao.getDeptName(ORGANIZE_UID);
    	}catch (DaoException e) {
    		logger.error("组织部门名称查询{}", e.getMessage());
    		SystemException.handleMessageException("组织部门名称查询失败,请联系相关人员处理");
    	} finally {
    	}
    	return domresult;
    	
    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        ZgTzdVO vo = new ZgTzdVO();
        String zg_content_uid = "";
        try {
            JSONArray list = vo.doInitJson(json);
            JSONObject obj = (JSONObject) list.get(0);
            vo.setValueFromJson(obj);
            
            //JSONArray arry = obj.getJSONArray("SJUID");
            
            String zgdxz = vo.getZg_xingzhi_uid();
            vo.setCreated_by(user.getUserSN());
            vo.setCreated_date(new Date());
            vo.setZg_code(getCode(zgdxz));
            vo.setZgstatus("1");
            if("2".equals(zgdxz)){
            	vo.setSh_level("1");
            }else if("3".equals(zgdxz)){
            	vo.setSh_level("2");
            }
            // 插入
			zgTzdDao.save(vo);
            
            String sjuid = obj.getString("SJUID");
            //判断违规事件是一条还是多条
            if(sjuid.indexOf("[")!=-1){
            	JSONArray arry = obj.getJSONArray("SJUID");
                if(null!=arry){
                	for (int i = 0; i < arry.size(); i++) {
                		ZgContentVO cvo = new ZgContentVO();
                		cvo.setZg_tzd_uid(vo.getZg_tzd_uid());
                		cvo.setZg_weigui_sj_uid(arry.getString(i));
                		cvo.setWg_miaoshu(obj.getJSONArray("WG_MIAOSHU").getString(i));
                		cvo.setXuhao(i+1+"");
                		cvo.setDescription(obj.getJSONArray("DESCRIPTION").getString(i));
                		BaseDAO.insert(DBUtil.getConnection(), cvo);
    				}
                }
            	
            }else{
        		ZgContentVO cvo = new ZgContentVO();
        		cvo.setZg_tzd_uid(vo.getZg_tzd_uid());
        		cvo.setZg_weigui_sj_uid(sjuid);
        		cvo.setWg_miaoshu(obj.getString("WG_MIAOSHU"));
        		cvo.setXuhao(1+"");
        		cvo.setDescription(obj.getString("DESCRIPTION"));
        		BaseDAO.insert(DBUtil.getConnection(), cvo);
            }
            this.zgTzdDao.queryScore(vo.getZg_tzd_uid());
            zgTzdDao.setScore(vo.getZg_tzd_uid());
            resultVO = vo.getRowJson();
            
        } catch (DaoException e) {
            logger.error("整改通知单{}", e.getMessage());
            SystemException.handleMessageException("整改通知单新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        ZgTzdVO vo = new ZgTzdVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            String zgdxz = vo.getZg_xingzhi_uid(); 
            vo.setUpdated_by(user.getUserSN());
            vo.setUpdated_date(new Date());

            if("2".equals(zgdxz)){
            	vo.setSh_level("1");
            }else if("3".equals(zgdxz)){
            	vo.setSh_level("2");
            }
            // 修改
            zgTzdDao.update(vo);
			if("1".equals(zgdxz)){
				zgTzdDao.setScore(vo.getZg_tzd_uid());
			}
            resultVO = vo.getRowJson();


        } catch (DaoException e) {
            logger.error("整改通知单{}", e.getMessage());
            SystemException.handleMessageException("整改通知单修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		ZgTzdVO vo = new ZgTzdVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
			//vo.setSjbh(eventVO.getSjbh());

			//删除   根据据主键
			//zgTzdDao.delete(vo);
			
			BaseDAO.delete(DBUtil.getConnection(), vo);
			//this.zgTzdDao.delScore(vo.getZg_tzd_uid());

			resultVo = vo.getRowJson();


		} catch (DaoException e) {
            logger.error("整改通知单{}", e.getMessage());
            SystemException.handleMessageException("整改通知单删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}
    
    
    public String getCode(String zglx){
    	Calendar c = Calendar.getInstance();
    	int year = c.get(Calendar.YEAR);
    	String co = this.zgTzdDao.getCode(zglx,year);
    	if("1".equals(zglx)){
    		zglx =  "ZG";
    	}else if("2".equals(zglx)){
    		zglx =  "JT";
    	}else if("3".equals(zglx)){
    		zglx =  "TG";
    	}
    	
    	String code = year+"-"+zglx+"-"+co;
    	return code;
    }

	@Autowired
	@Qualifier("zgTzdDaoImpl")
	public void setZgTzdDao(ZgTzdDao zgTzdDao) {
		this.zgTzdDao = zgTzdDao;
	}

	public String queryUid(String json) {
		// TODO Auto-generated method stub
		return this.zgTzdDao.queryUid(json);
	}

	public String queryForm(String msg) {
		// TODO Auto-generated method stub
		return this.zgTzdDao.queryForm(msg);
	}

	public String getJtCount(String msg) {
		// TODO Auto-generated method stub
		return this.zgTzdDao.getJtCount(msg);
	}

	public String getQtCount(String msg) {
		// TODO Auto-generated method stub
		return this.zgTzdDao.getQtCount(msg);
	}

	public String querySh(String msg) {
		// TODO Auto-generated method stub
		return this.zgTzdDao.querySh(msg);
	}

	public String toword(HttpServletResponse response, String tzduid) {
		Map<String, Object> map =  null;
		try {
			List<?> list = this.zgTzdDao.queryPrint(tzduid);
			String path = System.getProperty(Constants.getString(Constants.webAppRootKey, "wndjsbg.root"));
			String connpath = path.replace("\\", "/") + "template/";
			System.out.println("connpath=="+connpath);
			//String filePath=connpath;	//转前 ftl 路径
			//String pdfPath=Constants.getString(Constants.PATH_TEMPLATE_XML,"");	//转后 pdf 路径
			String ftlName="xqzg";
			String workName=""+System.nanoTime();
			String filename =connpath+workName;
			System.out.println("filename=="+filename);
			if(list.size()!=0){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				map = (Map<String, Object>) list.get(0);
				map.put("dydate", sdf.format(new Date()));
				FreemarkerHelper.createWord(map, connpath, ftlName, connpath, workName);
				Word2PDF word2pdf = new Word2PDF();
				word2pdf.toPdf(filename);
			}
			return filename+".xml.pdf";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	public String updateSh(String msg) throws Exception{

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        ZgTzdVO vo = new ZgTzdVO();

        try {
            JSONArray list = vo.doInitJson(msg);
            vo.setValueFromJson((JSONObject) list.get(0));
            String shlevel =  vo.getSh_level();
            String zgdxz = vo.getZg_xingzhi_uid(); 
            vo.setUpdated_by(user.getUserSN());
            vo.setUpdated_date(new Date());

            if("2".equals(shlevel)){
            	vo.setSh_level("1");
            }else if("3".equals(zgdxz)){
            	vo.setSh_level("2");
            }
            // 修改
            //zgTzdDao.update(vo);
			if("2".equals(zgdxz)){
				
				zgTzdDao.setScore(vo.getZg_tzd_uid());
			}else{
				
				
			}
            resultVO = vo.getRowJson();


        } catch (DaoException e) {
            logger.error("整改通知单{}", e.getMessage());
            SystemException.handleMessageException("整改通知单修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;
	}
    
}
