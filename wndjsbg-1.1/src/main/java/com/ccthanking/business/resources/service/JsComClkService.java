/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    resources.service.JsComClkService.java
 * 创建日期： 2014-06-14 下午 05:05:25
 * 功能：    接口：企业材料库
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2014-06-14 下午 05:05:25  隆楚雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.resources.service;


import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.ccthanking.business.project.vo.JsComClkVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> JsComClkService.java </p>
 * <p> 功能：企业材料库 </p>
 *
 * <p><a href="JsComClkService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:longchuxiong@kzcpm.com">隆楚雄</a>
 * @version 0.1
 * @since 2014-06-14
 * 
 */
public interface JsComClkService extends IBaseService<JsComClkVO, String> {
   
	
    public JsComClkVO saveJsComClkVO(JsComClkVO vo)  throws Exception;

    //查询注册公司list  add by longchuxiong 20140617
    public List<?> getAllCompanyList(String json);
    
    public List<?> getAllCompanyClkList(JSONObject object);
    
    /**
	 * * *************************企业资料库初始化添加节点方法
	 *1、 用于企业注册审核通过之后调用，
	 *2、立项审核通过之后调用，
	 *3、项目审核通过之后调用 
	 * 
	 * 参数说明：
	 * type：QY\LX\XM三种类型（YW类型不在该处进行处理）
	 * companyid：企业ID 
	 * typename：节点名称（type为QY时对应公司名称;type为LX时对应立项名称;
	 * 				type为XM时对应项目名称;）
	 * typeid：节点对应实际记录的ID（type为QY时为空;type为LX时对应立项ID;
	 * 				type为XM时对应项目ID;）
	 * 返回值：true 或  false
	 * */
    public boolean saveCompanyClk(String type,String companyid,String typename,String typeid);
    
    
}
