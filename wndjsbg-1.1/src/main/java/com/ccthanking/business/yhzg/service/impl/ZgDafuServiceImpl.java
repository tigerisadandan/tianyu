/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    yhzg.service.ZgDafuService.java
 * 创建日期： 2015-04-21 下午 01:27:03
 * 功能：    接口：整改答复
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-04-21 下午 01:27:03  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.yhzg.service.impl;


import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.ccthanking.common.BusinessUtil;
import com.ccthanking.common.EventManager;
import com.ccthanking.common.YwlxManager;
import com.ccthanking.common.util.FreemarkerHelper;
import com.ccthanking.common.util.Word2PDF;
import com.ccthanking.common.vo.EventVO;
import com.ccthanking.framework.Constants;
import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.log.LogManager;

import com.ccthanking.business.dtgl.yhzg.vo.ZgDafuVO;
import com.ccthanking.business.dtgl.yhzg.vo.ZgTzdVO;
import com.ccthanking.business.yhzg.dao.ZgDafuDao;
import com.ccthanking.business.yhzg.service.ZgDafuService;
import com.ccthanking.framework.service.impl.Base1ServiceImpl;
import com.copj.modules.utils.exception.DaoException;
import com.copj.modules.utils.exception.SystemException;



/**
 * <p> ZgDafuService.java </p>
 * <p> 功能：整改答复 </p>
 *
 * <p><a href="ZgDafuService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-04-21
 * 
 */

@Service
public class ZgDafuServiceImpl extends Base1ServiceImpl<ZgDafuVO, String> implements ZgDafuService {

	private static Logger logger = LoggerFactory.getLogger(ZgDafuServiceImpl.class);
    
    private ZgDafuDao zgDafuDao;

    // @Override
    public String queryCondition(String json) throws Exception {
    
    	User user = ActionContext.getCurrentUserInThread();
    
        String domresult = "";
        try {

			domresult = zgDafuDao.queryCondition(json, null, null);

        }catch (DaoException e) {
        	logger.error("整改答复{}", e.getMessage());
			SystemException.handleMessageException("整改答复查询失败,请联系相关人员处理");
        } finally {
        }
        return domresult;

    }
    
    public String insert(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();
		
        String resultVO = null;
        ZgDafuVO vo = new ZgDafuVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            //vo.setId(new RandomGUID().toString()); // 主键
            
            BusinessUtil.setInsertCommonFields(vo, user);
            //vo.setYwlx(ywlx);

           // EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
		   // vo.setSjbh(eventVO.getSjbh());

            // 插入
			zgDafuDao.save(vo);
            resultVO = vo.getRowJson();


			//String jsona="{querycondition: {conditions: [{\"value\":\""+vo.getId()+"\",\"fieldname\":\"t.id\",\"operation\":\"=\",\"logic\":\"and\"} ]}}";
            //return queryCondition(jsona, user);
            
        } catch (DaoException e) {
            logger.error("整改答复{}", e.getMessage());
            SystemException.handleMessageException("整改答复新增失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String update(String json) throws Exception {

        User user = ActionContext.getCurrentUserInThread();
        
        String resultVO = null;
        ZgDafuVO vo = new ZgDafuVO();

        try {
            JSONArray list = vo.doInitJson(json);
            vo.setValueFromJson((JSONObject) list.get(0));
            // 设置主键
            /*
             * vo.setId(new RandomGUID().toString()); // 主键
             *//* vo.setId("20FC7EF9-696D-6398-15C8-A77F2C4DFC02"); */

          	//BusinessUtil.setUpdateCommonFields(vo, user);
           // vo.setYwlx(ywlx);
           // EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
			//vo.setSjbh(eventVO.getSjbh());

            // 修改
            zgDafuDao.update(vo);
            resultVO = vo.getRowJson();


        } catch (DaoException e) {
            logger.error("整改答复{}", e.getMessage());
            SystemException.handleMessageException("整改答复修改失败,请联系相关人员处理");
        } finally {
        }
        return resultVO;

    }

    public String delete(String json) throws Exception {

		User user = ActionContext.getCurrentUserInThread();

		String resultVo = null;
		ZgDafuVO vo = new ZgDafuVO();
		try {
			JSONArray list = vo.doInitJson(json);
			JSONObject jsonObj = (JSONObject) list.get(0);

			vo.setValueFromJson(jsonObj);

			//EventVO eventVO = EventManager.createEvent(vo.getYwlx(), user);// 生成事件
			//vo.setSjbh(eventVO.getSjbh());

			//删除   根据据主键
			//zgDafuDao.delete(ZgDafuVO.class, vo.getId());

			resultVo = vo.getRowJson();


		} catch (DaoException e) {
            logger.error("整改答复{}", e.getMessage());
            SystemException.handleMessageException("整改答复删除失败,请联系相关人员处理");
		} finally {
		}
		return resultVo;

	}

	@Autowired
	@Qualifier("zgDafuDaoImpl")
	public void setZgDafuDao(ZgDafuDao zgDafuDao) {
		this.zgDafuDao = zgDafuDao;
	}

	public String toword(HttpServletResponse response, String tzdfuid) {
		Map<String, Object> map =  null;
		try {
			List<?> list = this.zgDafuDao.queryPrint(tzdfuid);
			String path = System.getProperty(Constants.getString(Constants.webAppRootKey, "wndjsbg.root"));
			String connpath = path.replace("\\", "/") + "template/";
			System.out.println("connpath=="+connpath);
			//String filePath=connpath;	//转前 ftl 路径
			//String pdfPath=Constants.getString(Constants.PATH_TEMPLATE_XML,"");	//转后 pdf 路径
			String ftlName="zgdf";
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

	public String toword2(HttpServletResponse response, String tzduid) {
		Map<String, Object> map =  null;
		try {
			List<?> list = this.zgDafuDao.queryPrint2(tzduid);
			String path = System.getProperty(Constants.getString(Constants.webAppRootKey, "wndjsbg.root"));
			String connpath = path.replace("\\", "/") + "template/";
			System.out.println("connpath=="+connpath);
			//String filePath=connpath;	//转前 ftl 路径
			//String pdfPath=Constants.getString(Constants.PATH_TEMPLATE_XML,"");	//转后 pdf 路径
			String ftlName="fgtzs";
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
    
}
