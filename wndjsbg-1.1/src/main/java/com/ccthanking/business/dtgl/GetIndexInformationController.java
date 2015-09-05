package com.ccthanking.business.dtgl;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccthanking.business.dtgl.service.GetIndexInformationService;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.model.requestJson;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;


@Controller
@RequestMapping("/IndexInformationController")
public class GetIndexInformationController {
	private static Logger logger = LoggerFactory
			.getLogger(GetIndexInformationController.class);
	
	private GetIndexInformationService getIndexInformationService;
	/**
	 * 查询权限
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "getQuanxian")
	@ResponseBody
	public requestJson getQuanxian(final HttpServletRequest request) throws Exception {
		
		User user = RestContext.getCurrentUser();
		logger.info("<{}>执行操作【查询用户权限记录查询】", user.getName());				
		String userId = request.getParameter("userId");
		requestJson j = new requestJson();
		String domresult = "";
	    domresult = this.getIndexInformationService.getQuanxianService(userId);
	    j.setMsg(domresult);
		return j;
	}
	
	/**
	 * 查询柱状图数据
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "getChartDataWeek")
	@ResponseBody
	public requestJson getChartDataWeek(final HttpServletRequest request,final HttpServletResponse response) throws Exception {
		response.setDateHeader("Expires", -1);  
        response.setHeader("Cache-Control", "no-cache");  
        response.setHeader("Prama", "no-cache");
		
		User user = RestContext.getCurrentUser();
		logger.info("<{}>执行操作【查询图形数据记录查询】", user.getName());
		String week = request.getParameter("week");
		String before = "";
		String after = "";
		requestJson j = new requestJson();
		String domresult = "";
		String chart1 = "";
		
		if(week!=null&&week!=""){
			//获取当年某一周的开始和结束时间		
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");			
			Calendar cal = Calendar.getInstance();
	        int year =cal.get(Calendar.YEAR);
	        
	        cal.set(Calendar.YEAR, year);
	        cal.set(Calendar.WEEK_OF_YEAR,Integer.parseInt(week));
	        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
	        before = sdf.format(cal.getTime());
	        
	        cal.set(Calendar.WEEK_OF_YEAR,Integer.parseInt(week)+1);
	        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
	        after = sdf.format(cal.getTime());	        
			
	        domresult = this.getIndexInformationService.getChartData(before,after);	        
	        chart1 = this.ChartXML(domresult,"1");	
		}
		j.setMsg(chart1);
		return j;
	}	
	
	/**
	 * 查询柱状图数据
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "getChartDataMonth")
	@ResponseBody
	public requestJson getChartDataMonth(final HttpServletRequest request,final HttpServletResponse response) throws Exception {
		response.setDateHeader("Expires", -1);  
        response.setHeader("Cache-Control", "no-cache");  
        response.setHeader("Prama", "no-cache");
		
		User user = RestContext.getCurrentUser();
		logger.info("<{}>执行操作【查询图形数据记录查询】", user.getName());
		String month = request.getParameter("month");
		String year = request.getParameter("year");
		String before = "";
		String after = "";
		requestJson j = new requestJson();
		String domresult = "";
		String chart2 = "";
		if(year!=null&&year!=""&&month!=null&&month!=""){
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();

	        cal.set(Calendar.YEAR, Integer.parseInt(year));     
	        cal.set(Calendar.MONTH, Integer.parseInt(month)-1);
	        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DATE));	        
	        before = sdf.format(cal.getTime());
	        
	        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DATE));
	        after = sdf.format(cal.getTime());
	        domresult = this.getIndexInformationService.getChartData(before,after);	        
	        chart2 = this.ChartXML(domresult,"2");
	        
		} 
	    j.setMsg(chart2);
		return j;
	}
	
	//通过返回数据建立柱状图XML数据
	public String ChartXML(String domresult,String whichone){
		int sum = 0 ;
		JSONArray jsonArray = new JSONArray();
        if(!domresult.equals("0")){
        	JSONObject resp = JSONObject.fromObject(domresult);
	        String resp_txt = resp.getString("response");
			JSONObject data = JSONObject.fromObject(resp_txt);
			String data_txt = data.getString("data");
			jsonArray = (JSONArray)JSONSerializer.toJSON(data_txt);
			
			if(jsonArray.size()>0){
        		for(int i=0;i<jsonArray.size();i++){
	        		JSONObject STZG =(JSONObject)jsonArray.get(i);
	        		sum += Integer.parseInt(STZG.getString("STZG"));
	        	}
        	}
			
			if(jsonArray.size()>0){
        		for(int k=0;k<jsonArray.size();k++){
	        		JSONObject KQZG =(JSONObject)jsonArray.get(k);
	        		sum += Integer.parseInt(KQZG.getString("KQZG"));
	        	}
        	}			
        }
        
		StringBuffer data = new StringBuffer();
		data.append("<chart baseFont='SunSim' baseFontSize='12' yAxisMinValue='0' hovercapbg='FFECAA' hovercapborder='F47E00' formatNumberScale='0' decimalPrecision='0' ");
		data.append("showvalues='1' numdivlines='10' numVdivlines='0' shownames='1' rotateNames='0' numberSuffix='张'>");
		data.append("<categories font='Arial' fontSize='12' fontColor='000000'>");
		if(jsonArray.size()>0){
    		for(int l=0;l<jsonArray.size();l++){
        		JSONObject DEPT =(JSONObject)jsonArray.get(l);
        		data.append("<category name='"+DEPT.getString("ORG_NAME")+"'/>");
        	}
    	}else{
    		data.append("<category name='综合管理科'/>");
    		data.append("<category name='监督一组'/>");
    		data.append("<category name='监督二组'/>");
    		data.append("<category name='监督三组'/>");   		
    	}	
        	data.append("</categories>");
        	data.append("<dataset seriesname='现场整改' color='FDC12E' alpha='100'>");
        	if(jsonArray.size()>0){
        		for(int i=0;i<jsonArray.size();i++){
	        		JSONObject JSONObject =(JSONObject)jsonArray.get(i);
	        		data.append("<set value='"+JSONObject.getString("STZG")+"' link='javascript:ShowDetail(\"STZG\",\""+JSONObject.getString("DEPT")+"\",\""+whichone+"\")' />");
	        	}
        	}else{
        		data.append("<set value='0' link='javascript:ShowDetail(\"STZG\",\"52\"\",\""+whichone+"\")' />");
        		data.append("<set value='0' link='javascript:ShowDetail(\"STZG\",\"11\",\""+whichone+"\")' />");
        		data.append("<set value='0' link='javascript:ShowDetail(\"STZG\",\"16\",\""+whichone+"\")' />");
        		data.append("<set value='0' link='javascript:ShowDetail(\"STZG\",\"17\",\""+whichone+"\")' />");
        	}
        	data.append("</dataset>");
        	data.append("<dataset seriesname='考勤整改'  color='56B9F9' showValues='1' alpha='100'>");
        	if(jsonArray.size()>0){
        		for(int k=0;k<jsonArray.size();k++){
	        		JSONObject JSONObject =(JSONObject)jsonArray.get(k);
	        		data.append("<set value='"+JSONObject.getString("KQZG")+"' link='javascript:ShowDetail(\"KQZG\",\""+JSONObject.getString("DEPT")+"\",\""+whichone+"\")' />");
	        	}
        	}else{
        		data.append("<set value='0' link='javascript:ShowDetail(\"KQZG\",\"52\",\""+whichone+"\")' />");
        		data.append("<set value='0' link='javascript:ShowDetail(\"KQZG\",\"11\",\""+whichone+"\")' />");
        		data.append("<set value='0' link='javascript:ShowDetail(\"KQZG\",\"16\",\""+whichone+"\")' />");
        		data.append("<set value='0' link='javascript:ShowDetail(\"KQZG\",\"17\",\""+whichone+"\")' />");
        	}	        			        		
        	data.append("</dataset>");
        	data.append("</chart>");
        	System.out.println(data.toString());
        	data.append("&&&&");
        	data.append(""+sum);
        		        	
        return data.toString();
	}	


	
	/**
	 * 查询整改单数据
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "queryZGD")
	@ResponseBody
	public requestJson queryZGD(final HttpServletRequest request) throws Exception {
		
		User user = RestContext.getCurrentUser();
		logger.info("<{}>执行操作【查询整改单数据记录查询】", user.getName());
		requestJson j = new requestJson();		
		String domresult = "";		
		domresult = this.getIndexInformationService.queryZGD();
	    j.setMsg(domresult);
		return j;
	}
	
	
	/**
	 * 查询考勤信息数据
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "queryKaoQing")
	@ResponseBody
	public requestJson queryKaoQing(final HttpServletRequest request) throws Exception {
		
		User user = RestContext.getCurrentUser();
		logger.info("<{}>执行操作【查询项目考勤数据记录查询】", user.getName());
		
		requestJson j = new requestJson();
		String domresult = "";
		domresult = this.getIndexInformationService.queryKaoqingInfo();
	    j.setMsg(domresult);
		return j;
	}
	
	
	
	public GetIndexInformationService getGetIndexInformationService() {
		return getIndexInformationService;
	}
	
	@Autowired
	public void setGetIndexInformationService(GetIndexInformationService getIndexInformationService) {
		this.getIndexInformationService = getIndexInformationService;
	}
	

}
