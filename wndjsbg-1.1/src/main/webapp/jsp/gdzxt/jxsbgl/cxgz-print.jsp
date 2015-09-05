<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>安装告知表</title>
<% String id = request.getParameter("id");
String gcid = request.getParameter("gcid");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/kindeditor/kindeditor.js"></script>
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/jsp/business/sp/js/printPageHtml.js"> </script>
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/kindeditor/customKE.js"></script>
</head>
<body>
<app:dialogs/>
<div class="main-container" id="main-container">
	 <span style="float:right">
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
	  		<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">打印</button>
          </span>
		<div style="padding:10px;padding-top:2px;text-align:left;" valign="top">
			<div style="width:530px;">
				<table id="apex_layout_84945431230501312" class="formlayout" summary="">
					<tbody>
						<tr>
							<td nowrap="nowrap" align="right"><label for="P6052_BT"><span class="t3Optional"></span>
							</label>
							</td>
							<td colspan="1" rowspan="1" align="center"><span id="P6052_BT"><font style="font-size:15pt;">无锡市新区建筑施工起重机械设备拆卸告知表</font><br>
								<br>
							</span>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<table id="apex_layout_84945604016501314" class="formlayout" summary="">
				<tbody>
					<tr>
						<td nowrap="nowrap" align="right"><label for="P6052_AZDW"><span class="t3Optional">安装单位（章）：</span>
						</label>
						</td>
						<td colspan="1" rowspan="1" align="right"><span id="P6052_AZDW"> 拆装负责人签字：</span>
						</td>
						<td nowrap="nowrap" align="right"><label for="P6052_PHONE"><span class="t3Optional"></span>
						</label>
						</td>
						<td colspan="1" rowspan="1" align="left"><span id="P6052_PHONE"> 联系电话：</span>
						</td>
					</tr>
				</tbody>
			</table>
			<style>
.formlayout {
	border: none;
}

.styleform {
	padding: 5px 10px 5px 10px;
}

.styleform table {
	border-collapse: collapse;
}

.styleform table .radiogroup {
	border: none;
}

.styleform table .radiogroup td {
	border: none;
}

.styleform td {
	border: solid 1px #000000;
}

.styleform td .noborder {
	border: none;
}

.formtd {
	border: solid 1px #00000;
}
</style>

								   
			<table class="t3RegionwithoutButtonsandTitles" cellspacing="0" border="0" summary="layout" id="R84944805573501306">
				<tbody>
					<tr>
						<td valign="bottom" class="t3RegionHeader" style="text-align:center;"></td>
					</tr>
                
					<tr class="t3Body">
						<td colspan="2" class="styleform" style="border:1px solid #000000;padding:0px;">
						 <form class="form-horizontal" id="azgzform" style="margin: 0">
						<table id="apex_layout_84944805573501306" class="formlayout" summary="">
								<tbody>
									<tr>
										<td align="center" style="width:150px"><label for="P6052_SHEBEI_NAME"><span class="t3Optional">设备名称</span>
										</label>
										</td>
										<td colspan="1" rowspan="1" align="left" style="width:240px" fieldname="SHEBEI_NAME">
										</td>
										<td align="center" style="width:153px"><label for="P6052_XKZHAO"><span class="t3Optional">规格型号</span>
										</label> 
										</td>
										<td colspan="1" rowspan="1" align="left" fieldname="GGXH"  style="width:240px" >
										</td>
									</tr>
									<tr>
										<td nowrap="nowrap" align="center"><label for="P6052_ZZCJ"><span class="t3Optional">工程名称</span> 
										</label>
										</td>
										<td colspan="1" rowspan="1" align="left" fieldname="GC_NAME">
										</td>
										<td align="center"><label for="P6052_GGXH"><span class="t3Optional">工程地点</span>
										</label>
										</td>
										<td colspan="1" rowspan="1" align="left" fieldname="GC_ADDRESS">
										</td>
									</tr>
									
									<tr>
										<td nowrap="nowrap" align="center"><label for="P6052_ZZCJ"><span class="t3Optional">使用登记编号</span>
										</label>
										</td>
										<td colspan="1" rowspan="1" align="left" fieldname="SYDJ_BH">
										</td>
										<td align="center"><label for="P6052_GGXH"><span class="t3Optional">停止使用时间</span>
										</label>
										</td>
										<td colspan="1" rowspan="1" align="left" fieldname="TZ_DATE">
										</td>
									</tr>
									<tr>
										<td nowrap="nowrap" align="center"><label for="P6052_HGZH"><span class="t3Optional">使用单位项目经理</span>
										</label>
										</td>
										<td colspan="1" rowspan="1" align="XMJL" fieldname="XMJL" >
										</td>
										<td align="center"><label for="P6052_CHUCHANG_DATE"><span class="t3Optional">联系电话</span>
										</label>
										</td>
										<td colspan="1" rowspan="1" align="left" fieldname="LXDH">
										</td>
									</tr>
									<tr>
										<td nowrap="nowrap" align="center"><label for="P6052_CQ_DANWEI"><span class="t3Optional">计划拆卸开始时间</span>
										</label>
										</td>
										<td colspan="1" rowspan="1" align="left" fieldname="SBCX_DATE">
										</td>
										<td align="center"><label for="P6052_CQ_BH"><span class="t3Optional">计划拆卸结束时间</span>
										</label>
										</td>
										<td colspan="1" rowspan="1" align="left" fieldname="SBCX_E_DATE">
										</td>
									</tr>
								
									<tr>
										<td nowrap="nowrap" rowspan="2" align="center"><label for="P6052_AZ_DANWEI"><span class="t3Optional">拆卸单位</span>
										</label>
										</td>
										<td colspan="1" rowspan="2" align="left" fieldname="CX_DANWEI">
										</td>
										<td nowrap="nowrap" align="center"><label for="P6052_ZYJSREN"><span class="t3Optional">拆卸现场专业技术人员</span>
										</label>
										</td>
										<td colspan="1" rowspan="1" align="left" fieldname="JSY" id="JSY">
										</td>
									</tr>
									<tr>
										<td nowrap="nowrap" align="center"><label for="P6052_ZZAQ"><span class="t3Optional">拆卸现场专职安全员</span>
										</label>
										</td>
										<td colspan="1" rowspan="1" align="left"  fieldname="AQY" id="AQY">
										</td>
									</tr>
									<tr>
										<td nowrap="nowrap" align="center"><label for="P6052_ZZHIBH"><span class="t3Optional">资质证书编号</span>
										</label>
										</td>
										<td colspan="1" rowspan="1" align="left" fieldname="ZZBH">
										</td>
										<td align="center"><label for="P6052_AQSCBH"><span class="t3Optional">安全生产许可证编号</span>
										</label>
										</td>
										<td colspan="1" rowspan="1" align="left" fieldname="AQSCBH">
										</td>
									</tr>
									
									<tr>
										<td nowrap="" style="border-right:none;" align="center"><span class="t3NoLabel"> </span>
										</td>
										<td style="border-left:none;" colspan="4" rowspan="1" align="center"><span id="P6052_GC_ZJ" readonly="true"><b>设备安装中特种作业人员名单（含司机、指挥司索工及安装维修工等）<b></b>
											</b>
										</span>
										</td>
									</tr>
								</tbody>
							</table>
							
			</form>
							<table class="formlayout" id="rytable" summary="">
								
							</table>
							
							
							<table class="formlayout" id="cltable"  summary="">
							</table>
							
							 <form class="form-horizontal" id="shenheform" style="margin: 0">
							<table class="formlayout" id="P6052_BK2" summary="">
								<tbody>
									<tr>
										<td></td>
									</tr>
									<tr>
										<td nowrap="nowrap" align="left"></td>
										<td colspan="8" rowspan="1" align="left"></td>
									</tr>
									<tr>
										<td nowrap="" style="border-right:none;border-bottom:none;" align="right"><label for="P6052_SG_SHENHE_YJ"><span class="t3Optional">施工总承包单位审核意见：</span>
										</label>
										</td>
										<td style="border-left:none;border-right:none;border-bottom:none;" colspan="1" rowspan="1" align="left">
										<textarea fieldname="SG_SHENHE_YJ" name = "SG_SHENHE_YJ" rows="5" cols="80" wrap="virtual" id="P6052_SG_SHENHE_YJ" style="width:187px;" readonly="true"></textarea>
										</td>
										<td nowrap="" style="border-right:none;border-left:none;border-bottom:none;" align="right"><label for="P6052_AQGL_REN"><span class="t3Optional">总承包指定起重机械<br>专职安全管理人员：</span>
										</label>
										</td>
										<td style="border-left:none;border-bottom:none;" colspan="1" rowspan="1" align="left">
										<textarea fieldname="AQGL_REN" name = "AQGL_REN" rows="5" cols="80" wrap="virtual" id="P6052_AQGL_REN" style="width:197px;" readonly="true"></textarea>
										</td>
									</tr>
									<tr>
										<td nowrap="" style="border-right:none;border-top:none;" align="left"><label for="P6052_XMJL_QZ"><span class="t3Optional">项目经理（签字）：</span>
										</label>
										</td>
										<td style="border-left:none;border-top:none;border-right:none;height:50px;" colspan="1" rowspan="1" align="center"><span id="P6052_XMJL_QZ"></span> 盖章</td>
										<td nowrap="" style="border-right:none;border-top:none;border-left:none;" align="right"><label for="P6052_KONG"><span class="t3Optional"></span>
										</label>
										</td>
										<td style="border-left:none;border-top:none;" colspan="1" rowspan="1" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
										</td>
									</tr>
									<tr>
										<td nowrap="" style="border-right:none;border-bottom:none;border-top:none;" align="left"><label for="P6052_JL_SHENHE_YJ"><span class="t3Optional">监理单位审核意见：</span>
										</label>
										</td>
										<td style="border-left:none;border-right:none;border-bottom:none;border-top:none;" colspan="3" rowspan="1" align="left">
										<textarea fieldname="JL_SHENHE_YJ" name = "JL_SHENHE_YJ" rows="5" cols="100" wrap="virtual" id="P6052_JL_SHENHE_YJ" style="width:608px;" readonly="true"></textarea>
										</td>
									</tr>
									<tr>
										<td nowrap="" style="border-right:none;border-top:none;" align="left"><label for="P6052_JL_QZ"><span class="t3Optional">监理总监（签字）：</span>
										</label>
										</td>
										<td style="border-left:none;border-top:none;border-right:none;height:50px;" colspan="1" rowspan="1" align="right"><span id="P6052_JL_QZ"></span> 监理单位（章）</td>
										<td nowrap="" style="border-right:none;border-top:none;border-left:none;" align="right"><span class="t3NoLabel"></span>
										</td>
										<td style="border-left:none;border-top:none;" colspan="1" rowspan="1" align="left"><span id="SHENHE_DATE"></span>
										</td>
									</tr>
									<tr>
										<td nowrap="" style="border-right:none;border-top:none" align="left"><label for="P6052_XZSH_REN_NAME"><span class="t3Optional">小组审核人：</span><span id="XZSH_NAME"></span>
										</label>
										</td>
										<td style="border-left:none;border-right:none;border-top:none;height:50px;" colspan="1" rowspan="1" align="left"><span id="P6052_XZSH_REN_NAME"></span>
										</td>
										<td nowrap="" style="border-left:none;border-right:none;border-top:none" align="right"><label for="P6052_XZSH_DATE"><span class="t3Optional">小组审核时间：</span><span id="XZSH_DATE"></span>
										</label>
										</td>
										<td style="border-left:none;border-right:none;border-top:none" colspan="1" rowspan="1" align="left"><span id="P6052_XZSH_DATE"></span>
										</td>
									</tr>
									<tr>
										<td nowrap="" style="border-right:none;border-bottom:none;" align="left"><label for="P6052_GZ_SHOULI_YJ"><span class="t3Optional">告知受理部门意见：</span>
										</label>
										</td>
										<td style="border-left:none;border-right:none;border-bottom:none;" colspan="3" rowspan="1" align="left">
										<textarea fieldname="GZ_SHOULI_YJ" name = "GZ_SHOULI_YJ"  rows="5" cols="80" wrap="virtual" id="P6052_GZ_SHOULI_YJ" style="width:608px;" readonly="true"></textarea>
										</td>
									</tr>
									
									<tr>
										<td nowrap="" style="border-right:none;border-top:none;" align="left"><label for="P6052_KSFZR"><span class="t3Optional">科室负责人（签字）：</span>
										</label>
										</td>
										<td style="border-left:none;border-top:none;border-right:none;height:50px;" colspan="1" rowspan="1" align="right"><span id="P6052_KSFZR">新区安监站</span> 登记部门（章）</td>
										<td nowrap="" style="border-right:none;border-top:none;border-left:none;" align="right"><span class="t3NoLabel"></span>
										</td>
										<td style="border-left:none;border-top:none;" colspan="1" rowspan="1" align="left"><span id="SHOULI_DATE"></span>
										</td>
									</tr>
								</tbody>
							</table>
							</form>
							</td>
					</tr>
				</tbody>
			</table>
			<table cellspacing="0" border="0" summary="layout" id="R84945232754501312">
				<tbody>
					<tr>
						<td colspan="2" class="styleform" style="border:1px solid #000000;border-top:none;padding:0px;"></td>
					</tr>
				</tbody>
			</table>
			<table id="apex_layout_84945031144501312" class="formlayout" summary="">
				<tbody>
					<tr>
						<td></td>
						<td colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="84950408947501323"><input type="hidden" name="p_t95" value="11691" id="P6052_ANZHUANG_GZ_UID">
						</td>
					</tr>
					<tr>
						<td></td>
						<td colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="84953006503501325"><input type="hidden" name="p_t96" value="2101" id="P6052_ZUZHI_GUANXI_UID">
						</td>
						<td></td>
						<td style="border-left:none;border-top:none;" colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="84966409330501334"><input type="hidden" name="p_t97" value="02-8月 -11" id="P6052_DATE">
						</td>
						<td></td>
						<td style="border-left:none;border-top:none;" colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="84967221958501335"><input type="hidden" name="p_t98" value="03-8月 -11" id="P6052_DATE2">
						</td>
						<td></td>
						<td style="border-left:none;border-top:none;" colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="84967819410501335"><input type="hidden" name="p_t99" value="08-10月-12" id="P6052_DATE3">
						</td>
						<td></td>
						<td colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="84968601648501337"><input type="hidden" name="p_t100" value="33" id="P6052_STATUS">
						</td>
					</tr>
					<tr>
						<td></td>
						<td colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="84952815220501325"><input type="hidden" name="p_t101" value="" id="P6052_TAG">
						</td>
					</tr>
					<tr>
						<td></td>
						<td colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="84968014697501335"><input type="hidden" name="p_t102" value="PRINT" id="P6052_PRINT_DISP">
						</td>
					</tr>
					<tr>
						<td></td>
						<td colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="84968229831501335"><input type="hidden" name="p_t103" value="1940" id="P6052_CREATE_BY">
						</td>
					</tr>
					<tr>
						<td></td>
						<td colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="84968407927501335"><input type="hidden" name="p_t104" value="" id="P6052_CREATE_DATE">
						</td>
						<td></td>
						<td colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="84968822364501337"><input type="hidden" name="p_t105" value="1" id="P6052_BTN_FLAG">
						</td>
						<td></td>
						<td colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="84946624312501320"><input type="hidden" name="p_t106" value="4618" id="P6052_SHENHE_BY">
						</td>
						<td></td>
						<td colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="84946811022501320"><input type="hidden" name="p_t107" value="1" id="P6052_SHOULI_BY">
						</td>
						<td></td>
						<td colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="65726229642779600"><input type="hidden" name="p_t108" value="" id="P6052_JBRSH_REN">
						</td>
						<td></td>
						<td colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="87350319739002112"><input type="hidden" name="p_t109" value="" id="P6052_XZSH_REN">
						</td>
					</tr>
				</tbody>
			</table>

		</div>
	</div>
	<script type="text/javascript" charset="utf-8">
	var controllername= "${pageContext.request.contextPath }/dtgl/jxsbCxgzController";
	$(document).ready(function(){
		 $("input:checkbox").attr("disabled","true");
	 $("#btnClose").click(function(){
			    window.close(); 
			    });
	 $("#btnSave").click(function(){
		 window.location.href = "${pageContext.request.contextPath }/dtgl/jxsbCxgzController/download?uid="+<%=id %>;
		  
		    });
		init();
	
	});
	
	function init(){
		querySydj();
//		query()
	//	 queryXm();
		initSyglCl();
		initry();
	}
	var syglid="";
	
	function queryXm(){
		var queryconditionXX = "{\"logic\":\"and\",\"fieldname\":\"s.JXSB_SYGL_UID\",\"operation\":\"=\",\"value\":\""+syglid+"\",\"fieldtype\":'',\"fieldformat\":''}";
		var dataXX = "{querycondition: {conditions: ["
				+ queryconditionXX
				+ " ]},pages:{recordsperpage:200, currentpagenum:1, totalpage:0, countrows:0},orders:[{\"order\":\"asc\",\"fieldname\":\"s.JXSB_SYGL_UID\"}]}";
		var data = {
			msg : dataXX
		};
		$.ajax({
			url : contextPath + "/jxsb/jxsbSyglController/queryXmxx",
			data : data,
			cache : false,
			async : false,
			dataType : "json",
			type : 'post',
			success : function(response) {			
				var resultobj = defaultJson.dealResultJson(response.msg);
				$("#azgzform").setFormValues(resultobj);
				return true;
				}
		});		
	}
	
	function querySydj(){
		 var queryconditionXX = "{\"logic\":\"and\",\"fieldname\":\"c.JXSB_CXGZ_UID\",\"operation\":\"=\",\"value\":\""
				+ <%=id %> + "\",\"fieldtype\":'',\"fieldformat\":''}";
		var dataXX = "{querycondition: {conditions: ["
				+ queryconditionXX
				+ " ]},pages:{recordsperpage:200, currentpagenum:1, totalpage:0, countrows:0},orders:[{\"order\":\"desc\",\"fieldname\":\"c.JXSB_CXGZ_UID\"}]}";
		var data = {
			msg : dataXX
		};
		$.ajax({
			url : controllername+ "/query",
			data : data,
			cache : false,
			async : false,
			dataType : "json",
			type : 'post',
			success : function(response) {			
				var resultobj = defaultJson.dealResultJson(response.msg);
				$("#azgzform").setHtmlValue(resultobj);
				$("#shenheform").setFormValues(resultobj);
				$("#CREATED_DATE").html(resultobj.CREATED_DATE_SV+"日");
				$("#SHENHE_DATE").html(resultobj.SHENHE_DATE_SV+"日");
				$("#XZSH_DATE").html(resultobj.XZSH_DATE_SV+"日");
				$("#SHOULI_DATE").html(resultobj.SHOULI_DATE_SV+"日");
				$("#XZSH_NAME").html(resultobj.XZSH_NAME);
				syglid=resultobj.JXSB_SYGL_UID;
			}
		});		
	}
	
	function queryXm(){
		var queryconditionXX = "{\"logic\":\"and\",\"fieldname\":\"s.JXSB_SYGL_UID\",\"operation\":\"=\",\"value\":\""+syglid+"\",\"fieldtype\":'',\"fieldformat\":''}";
		var dataXX = "{querycondition: {conditions: ["
				+ queryconditionXX
				+ " ]},pages:{recordsperpage:200, currentpagenum:1, totalpage:0, countrows:0},orders:[{\"order\":\"asc\",\"fieldname\":\"s.JXSB_SYGL_UID\"}]}";
		var data = {
			msg : dataXX
		};
		$.ajax({
			url : contextPath + "/jxsb/jxsbSyglController/queryXmxx",
			data : data,
			cache : false,
			async : false,
			dataType : "json",
			type : 'post',
			success : function(response) {			
				var resultobj = defaultJson.dealResultJson(response.msg);
				$("#azgzform").setHtmlValue(resultobj);
				$("#shenheform").setFormValues(resultobj);
				return true;
				}
		});		
	}
	
	function query(){
		var queryconditionXX = "{\"logic\":\"and\",\"fieldname\":\"s.JXSB_SYGL_UID\",\"operation\":\"=\",\"value\":\""+syglid+"\",\"fieldtype\":'',\"fieldformat\":''}";
		var dataXX = "{querycondition: {conditions: ["
				+ queryconditionXX
				+ " ]},pages:{recordsperpage:200, currentpagenum:1, totalpage:0, countrows:0},orders:[{\"order\":\"asc\",\"fieldname\":\"s.JXSB_SYGL_UID\"}]}";
		var data = {
			msg : dataXX
		};
		$.ajax({
			url : contextPath + "/jxsb/jxsbSyglController/queryJxsb",
			data : data,
			cache : false,
			async : false,
			dataType : "json",
			type : 'post',
			success : function(response) {			
				var resultobj = defaultJson.dealResultJson(response.msg);
				$("#azgzform").setHtmlValue(resultobj);
				return true;
				}
		});		
	}
	
	function queryXmxx() {
		$.ajax({
			url : contextPath + "/sggc/projectsGongchengController/queryXmxx?gcid=<%=gcid%>",
			data :null,
			cache : false,
			async : false,
			dataType : "json",
			type : 'post',
			success : function(response) {
				var resultobj = defaultJson.dealResultJson(response.msg);
				$("#XMJL").val(resultobj.SG_NAME);
				$("#GC_ADDRESS").val(resultobj.JIANSHE_DIZHI);
				$("#GONGCHENG_NAME").html(resultobj.GONGCHENG_NAME);
				$("#COMPANY_NAME").html(resultobj.COMPANY_NAME);
				return true;
			}
		});
	}
	
	function initry(){
		var queryconditionXX = "{\"logic\":\"and\",\"fieldname\":\"JXSB_STEP_UID\",\"operation\":\"=\",\"value\":\""
			+ <%=id %>
			+ "\",\"fieldtype\":'',\"fieldformat\":''}";
			var queryconditionXX2 = "{\"logic\":\"and\",\"fieldname\":\"JXSB_STEP\",\"operation\":\"=\",\"value\":\"CXGZ\",\"fieldtype\":'',\"fieldformat\":''}";
			var dataXX = "{querycondition: {conditions: ["
					+ queryconditionXX+","+queryconditionXX2
			+ " ]},pages:{recordsperpage:200, currentpagenum:1, totalpage:0, countrows:0},orders:[{\"order\":\"desc\",\"fieldname\":\"JXSB_STEP_UID\"}]}";
	var data = {
		msg : dataXX
	};
	$
			.ajax({
				url : controllername + "/queryRy",
				data : data,
				cache : false,
				async : false,
				dataType : "json",
				type : 'post',
				success : function(response) {
				if (response.msg != '0') {
					var resultmsgobj = convertJson
							.string2json1(response.msg);

					var obj = resultmsgobj.response.data;
					var html = "<tr>"+
					"	<td nowrap='nowrap' colspan='2' align='center'><label for='P6052_JG_LEIXING'><span class='t3Optional'>姓名</span>"+
					"	</label>"+
					"	</td>"+
					"	<td colspan='2' rowspan='1' align='center'><span id='P6052_JG_LEIXING'>工种</span>"+
					"	</td>"+
					"	<td nowrap='nowrap' colspan='2' align='center'><label for='P6052_CC_KD'><span class='t3Optional'>资格证编号</span>"+
					"	</label>"+
					"	</td>"+
					"	<td colspan='2' rowspan='1' align='center'><span id='P6052_CC_KD' style='width:100px;'>备注</span>"+
					"	</td>"+
					"</tr>"; //清空
					
				
					for ( var i = 0; i < resultmsgobj.response.data.length; i++) {
						if(obj[i].JOB_TYPE=="安全员"){
							$("#AQY").html(obj[i].PERSON_NAME);
						}else if(obj[i].JOB_TYPE=="驻锡技术负责人"){
							$("#JSY").html(obj[i].PERSON_NAME);
						}
						var index=i+1;
						html +="<tr>"+
						"	<td nowrap='' style='border-right:none;' align='center'><span class='t3NoLabel'></span>"+
						"	</td>"+
						"	<td style='border-left:none;width:120px' colspan='1' rowspan='1' align='center'>"+obj[i].PERSON_NAME+
						"	</td>"+
						"	<td nowrap='' style='border-right:none;' align='center'><span class='t3NoLabel'></span>"+
						"	</td>"+
						"	<td style='border-left:none;width:250px' colspan='1' rowspan='1' align='center'>"+obj[i].JOB_TYPE+
						"	</td>"+
						"	<td nowrap='' style='border-right:none;' align='right'><span class='t3NoLabel'></span>"+
						"	</td>"+
						"	<td style='border-left:none;width:200px' colspan='1' rowspan='1' align='center'>"+obj[i].ZGBH+
						"	</td>"+
						"	<td nowrap='' style='border-right:none;' align='center'><span class='t3NoLabel'></span>"+
						"	</td>"+
						"	<td style='border-left:none;width:205px' colspan='1' rowspan='1' align='center'>"+obj[i].BEIZHU+
						"	</td>"+
						"</tr>";
					}
					html+="	<tr>"+
						"<td nowrap='' style='border-right:none;' align='right'><span class='t3NoLabel'></span>"+
						"</td>"+
						"<td style='border-left:none;' colspan='8' rowspan='1' align='center'><span id='P6052_AZGZ_ZL'><b>安装告知时应提交的资料</b>"+
						"</span>"+
						"</td>"+
					"</tr>"; //清空
					$("#rytable").append(html);
					return true;
				}
			}
		});
		
	

	
	}
	
	function initSyglCl(){
		var queryconditionXX = "{\"logic\":\"and\",\"fieldname\":\"JXSB_STEP\",\"operation\":\"=\",\"value\":\"CXGZ\",\"fieldtype\":'',\"fieldformat\":''}";
		var queryconditionXX2 = "{\"logic\":\"and\",\"fieldname\":\"JXSB_STEP_UID\",\"operation\":\"=\",\"value\":\"<%=id %>\",\"fieldtype\":'',\"fieldformat\":''}";
		var dataXX = "{querycondition: {conditions: ["
				+ queryconditionXX+","+queryconditionXX2
				+ " ]},pages:{recordsperpage:200, currentpagenum:1, totalpage:0, countrows:0},orders:[{\"order\":\"asc\",\"fieldname\":\"JXSB_SYGL_CL_UID\"}]}";
		var data = {
			msg : dataXX
		};
		$
		.ajax({
			url : "${pageContext.request.contextPath }/bzwj/jxsbAzgzController?querySyglCl",
			data : data,
			cache : false,
			async : false,
			dataType : "json",
			type : 'post',
			success : function(response) {
				if (response.msg != '0') {
					var resultmsgobj = convertJson
							.string2json1(response.msg);

					var obj = resultmsgobj.response.data;
					var html = "<tr><td nowrap='nowrap' style='width:40px;' colspan='2' align='center'><label for='P6052_XH'><span class='t3Optional'>序号</span>"+
					"</label>"+
					"</td>"+
					"<td style='width:400px;' colspan='2' rowspan='1' align='center'><span id='P6052_XH'>资料名称</span>"+
					"</td>"+
					"<td nowrap='' style='border-right:none;' align='right'><span class='t3NoLabel'></span>"+
					"</td>"+
					"<td style='border-left:none;' colspan='1' rowspan='1' align='center'><span id='P6052_JLSH'>监理审核情况</span>"+
					"</td>"+
				"</tr>"; //清空
					
				
					for ( var i = 0; i < resultmsgobj.response.data.length; i++) {
						var index=i+1;
						html +="<tr>"+
							"<td nowrap=\"\" style=\"border-right:none;\" align=\"right\"><span class=\"t3NoLabel\"></span>"+
							"</td>"+
							"<td style=\"border-left:none;\" colspan=\"1\" rowspan=\"1\" align=\"center\"><span id=\"P6052_XH1\">"+index+"</span>"+
							"</td>"+
							"<td nowrap=\"\" style=\"border-right:none;\" align=\"right\"><span class=\"t3NoLabel\"></span>"+
						    "	</td>"+
							"<td style=\"border-left:none;width:545px\" colspan=\"1\" rowspan=\"1\" align=\"left\"><span id=\"P6052_ZL_NAME1\">"+obj[i].CL_NAME+"</span>"+
							"</td>"+
							"<td nowrap=\"\" style=\"border-right:none;\" align=\"right\"><span class=\"t3NoLabel\"></span>"+
							"</td>"+
							"<td style=\"border-left:none;width:196px\" colspan=\"1\" rowspan=\"1\" align=\"left\"><input type=\"hidden\" name=\"p_arg_names\" value=\"84962218753501331\">"+obj[i].JLSHQK+""+
							"</td>"+
						"</tr>";
					}
					html+="	<tr><td nowrap='' style='border-right:none;' align='right'><span class='t3NoLabel'></span></td>"+
						"<td style='border-left:none;' colspan='8' rowspan='1' align='left'><span id='P6052_SHUOMING'> <b>安装单位提交的资料必须齐全、合法，并对其真实性负责，提交的复印件应注明与原件相符的注明那个<br>"+
                        " <br>工地使用并加盖单位公章，监理单位应对提供的复印与原件进行核查。</b></span></td></tr>"; //清空
					$("#cltable").append(html);
					return true;
				}
			}
		});
		}
	 
	</script>
	
<form class="form-inline" role="form" id="azgzQueryForm">
	<input type="hidden" id="JXSB_CXGZ_UID" fieldname="JXSB_CXGZ_UID"
		value="<%=id%>" operation="=" logic="and" />
</form>
	<form id="gdzxtformid" method="post" >
		<input type="hidden" name="gdzxt_gcid" id="gdzxt_gcid" >
	</form>
</body>

</html>