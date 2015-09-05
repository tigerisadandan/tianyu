package com.ccthanking.common;

import java.lang.reflect.Method;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import sun.misc.BASE64Decoder;
import net.sf.json.JSONObject;
import com.copj.modules.utils.spring.SpringContextHolder;



public class ExcelUtil {
	

	/**
	 * 
	 * @param json 查询条件
	 * @param dao 
	 * @return
	 * @throws Exception
	 */
	public static String  queryDate(String json,String dao,String jsonStr) {
		String domresult = "";
		String className  = SpringContextHolder.getBean(dao).getClass().toString();
		className = className.replace("class ", "");
		Method queryConditionMethod = null;
		Object obj = null;
		try {
			Class clazz = Class.forName(className);
			//通过反射调用queryCondition方法
			if(!StringUtils.isBlank(jsonStr)){
		        queryConditionMethod = clazz.getMethod("queryCondition", String.class,Map.class);
		        BASE64Decoder decoder = new BASE64Decoder(); 
		        byte[] b = decoder.decodeBuffer(jsonStr); 
		        String jsonMap = new String(b);
		        JSONObject  jasonObject = JSONObject.fromObject(jsonMap);
		        Map map = (Map)jasonObject;
		        obj = clazz.newInstance();
		        domresult = (String) queryConditionMethod.invoke(obj, json,map);
			}else{
				queryConditionMethod = clazz.getMethod("queryCondition", String.class);
				obj = clazz.newInstance();
				domresult = (String) queryConditionMethod.invoke(obj, json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			obj = null;
		}

       return domresult;
	}	

}
