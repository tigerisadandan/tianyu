<!DOCTYPE html>
<%@page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext"%>
<%@page import="com.ccthanking.business.bzwj.GongCheng"%>
<%@page import="com.ccthanking.framework.common.Role"%>
<%@page import="com.ccthanking.framework.handle.ActionContext"%>
<%@page import="com.ccthanking.framework.common.User"%>
<%@page import="com.ccthanking.framework.Constants"%>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>建设单位-维护</title>
<%
	String id = request.getParameter("id");
	String type = request.getParameter("type");
	User user = ActionContext.getCurrentUserInThread();
	/* Role[] roles=user.getRoles();
	String xzjs="";
	String jbjs="";
	String sljs="";
	for(int i=0;i<roles.length;i++){
	if("内网小组审核人".equals(roles[i].getName())){
	xzjs="内网小组审核人";
	}
	if("内网经办审核人".equals(roles[i].getName())){
	jbjs="内网经办审核人";
	}
	if("内网受理人".equals(roles[i].getName())){
	sljs="内网受理人";
	}
	} */
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/ywlz/css/default.css"> </LINK>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>	
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/kindeditor/plugins/code/prettify.css" />
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/kindeditor/kindeditor.js"></script>
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/dtgl/jxsbCxgzController";
var controllernameBgMenu="${pageContext.request.contextPath }/bgMenuController/";

var type ="<%=type%>";

var yjsh = ""; //一级审核 小组审核
var ejsh = "";//二级审核 告知受理部门
//页面初始化
$(function() {
	init();
    var input = $("#dis").find("input:radio");
   input.attr("disabled","disabled");
	$("#btnClose").bind("click", function(){
		var a=$(this).manhuaDialog.close();
	});
	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
	if($("#NB_STATUS").val()=='-1'){
	msg="确认审核不通过？";
	}else{
	msg="确认审核通过？";
	}
	       if(confirm(msg)){
		    if(yjsh=="yes"&&status=="30"){
		        if($("#XZSH_YIJIAN").val()==""){
 					 alert("请填写小组审核意见!");
 					 return;
 			    }else{
 			         if($("#NB_STATUS").val()=='1'){
 			    	  $("#SHENHE_STATUS").val('41');
 			    	  $("#NB_STATUS").val('0');
           			 }else{
           			  $("#NB_STATUS").val('-1');
           			 
           			 }
 			         var data = Form2Json.formToJSON(shenheform);
		             var data1 = defaultJson.packSaveJson(data);
 			         defaultJson.doUpdateJson(controllername + "/update?type=xz", data1);
 			    	 
 			    }
		    }else if(ejsh=="yes"&&status=="41"){
		        if($("#GZ_SHOULI_YJ").val()==""){
 					alert("请填写告知受理部门意见!");
 					return;
 			    }else{
 			         if($("#NB_STATUS").val()=='1'){
 			    	  $("#SHENHE_STATUS").val('50');
 			    	  $("#NB_STATUS").val('0');
           			 }else{
           			  $("#NB_STATUS").val('-1');
           			 }
 			         var data = Form2Json.formToJSON(shenheform);
		             var data1 = defaultJson.packSaveJson(data);
 			         defaultJson.doUpdateJson(controllername + "/update?type=sl", data1);
 			    }
		    }
               
    		}else{
    		//defaultJson.doUpdateJson(controllername + "?update", data1);
    		return;
    		}
		    var a=$(window).manhuaDialog.getParentObj();
		    a.init();
			$(window).manhuaDialog.close();
		
	});

    
    <%
    if(type.equals("detail")){
	%>
	//置所有input 为disabled
	$(":input").each(function(i){
	   $(this).attr("disabled", "true");
	   $("#shztBox").hide();
	 });
	$("#btnClose").attr("disabled", false);
	<%
		}
	%>
	 
});
var syglid=""; //使用管理id
var status=""; //当前审核状态
function query(){
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
				$("#azgzform").setFormValues(resultobj);
				syglid=resultobj.JXSB_SYGL_UID;
				$("#JL_SHENHE_YJ").val(resultobj.JL_SHENHE_YJ);
				$("#XZSH_YIJIAN").val(resultobj.XZSH_YIJIAN);
				$("#JBRSH_YIJIAN").val(resultobj.JBRSH_YIJIAN);
				$("#GZ_SHOULI_YJ").val(resultobj.GZ_SHOULI_YJ);
				$("#SHENHE_STATUS").val(resultobj.SHENHE_STATUS);
				status=resultobj.SHENHE_STATUS;
				return true;
				}
		});		
	}
	
function queryJxsb(){
	    var queryconditionXX = "{\"logic\":\"and\",\"fieldname\":\"s.JXSB_SYGL_UID\",\"operation\":\"=\",\"value\":\""
				+ syglid + "\",\"fieldtype\":'',\"fieldformat\":''}";
		var dataXX = "{querycondition: {conditions: ["
				+ queryconditionXX
				+ " ]},pages:{recordsperpage:200, currentpagenum:1, totalpage:0, countrows:0},orders:[{\"order\":\"desc\",\"fieldname\":\"CREATED_DATE\"}]}";
		var data = {
			msg : dataXX
		};
		$.ajax({
			url : controllername+ "?queryJxsb",
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
	
	   function initRy() {
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
							size = obj.length;
							var html = ""; //清空
							for ( var i = 0; i < resultmsgobj.response.data.length; i++) {
								
	       	 	                html +="<tr>"+
	       	 	                "<td><input class=\"span12\" style=\"width:100%\" id=\"ZHIZHAO\" type=\"text\" readonly=\"readonly\"fieldname=\"ZHENGSHU_CODE\" name = \"ZHENGSHU_CODE\" value=\""+obj[i].PERSON_NAME+"\" /></td>"+
	       	 	                "<td><input class=\"span12\" style=\"width:100%\" id=\"ZHIZHAO\" type=\"text\" readonly=\"readonly\" fieldname=\"ZHENGSHU_CODE\" name = \"ZHENGSHU_CODE\" value=\""+obj[i].JOB_TYPE+"\" /></td>"+
	       	 	                "<td><input class=\"span12\" style=\"width:100%\" id=\"ZHIZHAO\" type=\"text\" readonly=\"readonly\"  fieldname=\"ZHENGSHU_CODE\" name = \"ZHENGSHU_CODE\" value=\""+obj[i].ZGBH+"\" /></td>"+
	       	 	                "<td><input class=\"span12\" style=\"width:100%\" id=\"ZHIZHAO\" type=\"text\" readonly=\"readonly\" fieldname=\"ZHENGSHU_CODE\" name = \"ZHENGSHU_CODE\" value=\""+obj[i].BEIZHU+"\" /></td>"+
	       	 	                "</tr>";
								
							}
							$("#rytable").append(html);
							return true;
						}
					}
				});

	}
//页面默认参数
function init(){
    query();	
	initRy();
	initSyglCl();
		 if(status=="30"){
			//判断是否有一级审核(小组审核权限)
			$.ajax({
				url : controllernameBgMenu+"?getByMenuCodeAndUserId",
				data : {code : "4040101"},
				type :"post",
				dataType : "json",
				success : function(response){
					if(response.msg == "1"){
					  yjsh="yes";
					  $("#GZ_SHOULI_YJ").attr('readonly','readonly');
					}else{
					  $("#XZSH_YIJIAN").attr('readonly','readonly');
					  $("#GZ_SHOULI_YJ").attr('readonly','readonly');
			          $("#shztBox").hide();
			          $("#btnSave").hide();
					}
				}
			});
		}else if(status=="41"){
		//判断是否有二级审核(告知受理部门权限)
		$.ajax({
			url : controllernameBgMenu+"?getByMenuCodeAndUserId",
			data : {code : "4040102"},
			type :"post",
			dataType : "json",
			success : function(response){
				if(response.msg == "1"){
					//有权
					ejsh="yes";
					$("#XZSH_YIJIAN").attr('readonly','readonly');
				}else{
					$("#XZSH_YIJIAN").attr('readonly','readonly');
					 $("#GZ_SHOULI_YJ").attr('readonly','readonly');
					 $("#shztBox").hide();
					 $("#btnSave").hide();
				}
			}
		});
	}
}

function checkStatus(stu){
	$("#NB_STATUS").val(stu);
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
	$.ajax({
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
				size = obj.length;
				var html = ""; //清空
				for ( var i = 0; i < resultmsgobj.response.data.length; i++) {
					var index=i+1;
					 html +="<tr>"+
	 	                "<td  style='text-align: center;'>"+index+"</td>"+
	 	               "<td >"+obj[i].CL_NAME+"</td>"+
	 	              "<td><input class=\"span12\" style=\"width:100%\"  type=\"text\" readonly=\"readonly\" value=\""+obj[i].JLSHQK+"\" /></td>"+
	 	                "</tr>";
				}
				$("#cltable").append(html);
				return true;
			}
		}
	});
}
</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">

	<div class="B-small-from-table-autoConcise">
       <form method="post" id="queryForm"  >
     		<input type="hidden" id="QAZ_COMPANY_UID" fieldname="AZ_COMPANY_UID" name="AZ_COMPANY_UID" operation="=" />
      </form>
      <div style="height:5px;"></div>
     
     <input id="JXSB_SYGL_UID" type="hidden" fieldname="JXSB_SYGL_UID">
			<input type="hidden" fieldname="JXSB_AZGZ_UID"> <input
				type="hidden" fieldname="GONGCHENG_UID"> <input
				id="SSHENHE_STATUS" type="hidden" fieldname="SHENHE_STATUS"
				value="0"> <input id="JXSB_UID" type="hidden"
				fieldname="JXSB_UID" name="JXSB_UID" readonly="readonly"> <input
				id="BY_COMPANY_UID" type="hidden" fieldname="BY_COMPANY_UID"
				name="BY_COMPANY_UID"> <input id="AZ_COMPANY_UID"
				type="hidden" fieldname="AZ_COMPANY_UID" name="AZ_COMPANY_UID">
				
				<form class="form-horizontal" id="shenheform">
        <table border="1" width="100%" cellpadding="0" cellspacing="0" class="content">
			<tr>
				<td class="yw-title">
					<h4 ><span id="spyjtxhid">内部审核
					</span>
								<span class="pull-right" > 
								<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
							  		<%if (!type.equals("detail")) {%>
										<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">审核</button>
									<%} %>
								</span>
								</h4>
				</td>
			</tr>
			<tr>
				<td>
					<ul id="ywcl_list">
						<table class="B-table" width="100%" >
      		<input type="hidden" id="NB_STATUS" fieldname="STATUS" name = "STATUS" value="1"/>
      		<input type="hidden" id="JXSB_JXGZ_UID" fieldname="JXSB_CXGZ_UID" name = "JXSB_CXGZ_UID" value="<%=id %>"/>
      		<input type="hidden" id="SHENHE_STATUS" fieldname="SHENHE_STATUS" name = "SHENHE_STATUS"/>
			<tr id="shztBox">
				<th width="15%" class="right-border bottom-border text-right">审核状态</th>
	       	 	<td class="bottom-border right-border" style="width:32%" colspan="3">
	       	 	 <input type=radio id="zt0" value="1" name="status" checked="checked"
										onclick="javascript:checkStatus(1)">同意&nbsp;&nbsp; <input
										type=radio id="zt1" value="-1" name="status"
										onclick="javascript:checkStatus(-1)">不同意    	
	       	 	</td> 
	       	 	
	        </tr>
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">监理单位审核意见</th>
	       	 	<td class="bottom-border right-border" style="width:32%" colspan="3">
	       	 	<textarea class="span12" rows="2" id="JL_SHENHE_YJ"  maxlength="4000"  fieldname="JL_SHENHE_YJ" readonly="readonly" name="JL_SHENHE_YJ"></textarea>	     	
	       	 	</td>
	       	 	
	        </tr>
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">小组审核意见</th>
	       	 	<td class="bottom-border right-border" style="width:32%" colspan="3">
	       	 	<textarea class="span12" rows="2" id="XZSH_YIJIAN" maxlength="4000"  fieldname="XZSH_YIJIAN" name="XZSH_YIJIAN"></textarea>	     	
	       	 	</td>
	        </tr>
	        <!-- 
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">经办人审核意见</th>
	       	 	<td class="bottom-border right-border" style="width:32%" colspan="3" align="left">
	       	 	<textarea class="span12" rows="2" id="JBRSH_YIJIAN" maxlength="4000"  fieldname="JBRSH_YIJIAN" name="JBRSH_YIJIAN"></textarea>	     	
	       	 	</td>
	        </tr>
	         -->
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">告知受理部门意见</th>
	       	 	<td class="bottom-border right-border" style="width:95%" colspan="3">
	       	 	<textarea class="span12" rows="2" id="GZ_SHOULI_YJ"  maxlength="4000"  fieldname="GZ_SHOULI_YJ" name="GZ_SHOULI_YJ"></textarea>	     	
	       	 	</td>
	        </tr>
	        
      	</table>
					</ul>
				</td>
			</tr>
		</table>
		</form>
		
		<form class="form-horizontal" id="azgzform">
		<table border="1" width="100%" cellpadding="0" cellspacing="0" class="content">
			<tr>
				<td class="yw-title">
					<h4 ><span id="spyjtxhid">起重机械设备 </span>
								<span class="pull-right" > 
								</span>
								</h4>
				</td>
			</tr>
			<tr>
				<td>
					<ul id="ywcl_list">
						<table class="B-table" width="100%" >
      		<input type="hidden" id="AZ_COMPANY_UID" fieldname="AZ_COMPANY_UID" name = "AZ_COMPANY_UID"/>
      		
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">设备名称</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="SHEBEI_NAME" type="text" readonly="readonly" fieldname="SHEBEI_NAME" name = "SHEBEI_NAME"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">规格型号</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input style="width:94%" id="GGXH" type="text" readonly="readonly" fieldname="GGXH" name = "GGXH"  />
				</td>
	        </tr>
	         <tr>
	       	 	<th width="15%" class="right-border bottom-border text-right">使用登记编号</th>
	       	 	<td  class="bottom-border right-border">
					<input class="span12" style="width:100%" id="SYDJ_BH" type="text" readonly="readonly" fieldname="SYDJ_BH" name = "SYDJ_BH"  />	     
				</td>
				<th width="15%" class="right-border bottom-border text-right">停止使用时间</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input class="span12" style="width:100%" id="TZ_DATE" type="text" readonly="readonly" fieldname="TZ_DATE" name = "TZ_DATE"  />	     
				</td>
	        </tr>
      	</table>
					</ul>
				</td>
			</tr>
		</table>
		
		<table border="1" width="100%" cellpadding="0" cellspacing="0" class="content">
			<tr>
				<td class="yw-title">
					<h4 ><span id="spyjtxhid">拆卸单位  </span>
								<span class="pull-right" > 
								</span>
								</h4>
				</td>
			</tr>
			<tr>
				<td>
					<ul id="ywcl_list">
						<table class="B-table" width="100%" >
      		<input type="hidden" id="AZ_COMPANY_UID" fieldname="AZ_COMPANY_UID" name = "AZ_COMPANY_UID"/>
      		
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">拆卸单位</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="CX_DANWEI" type="text" readonly="readonly" fieldname="CX_DANWEI" name = "CX_DANWEI"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">资质证书编号</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input style="width:94%" id="ZZBH" type="text" readonly="readonly" fieldname="ZZBH" name = "ZZBH"  />
				</td>
	        </tr>
	         <tr>
				<th width="15%" class="right-border bottom-border text-right">安全生产许可证编号</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="AQSCBH" type="text" readonly="readonly" fieldname="AQSCBH" name = "AQSCBH"  />	     	
	       	 	</td>
	       	 	
	        </tr>
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">计划拆卸开始时间</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="SBCX_DATE" type="text" readonly="readonly" fieldname="SBCX_DATE" name = "SBCX_DATE"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">计划拆卸结束时间</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input class="span12" style="width:100%" id="SBCX_E_DATE" type="text" readonly="readonly" fieldname="SBCX_E_DATE" name = "SBCX_E_DATE"  />
					
				</td>
	        </tr>
      	</table>
					</ul>
				</td>
			</tr>
		</table>
		
		<table border="1" width="100%" cellpadding="0" cellspacing="0" class="content">
			<tr>
				<td class="yw-title">
					<h4 ><span id="spyjtxhid"> 项目信息
   </span>
								<span class="pull-right" > 
								</span>
								</h4>
				</td>
			</tr>
			<tr>
				<td>
					<ul id="ywcl_list">
						<table class="B-table" width="100%" >
      		<input type="hidden" id="AZ_COMPANY_UID" fieldname="AZ_COMPANY_UID" name = "AZ_COMPANY_UID"/>
      		
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">工程名称</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="GC_NAME" type="text" readonly="readonly" fieldname="GC_NAME" name = "GC_NAME"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">工程地点</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input style="width:94%" id="GC_ADDRESS" type="text" readonly="readonly" fieldname="GC_ADDRESS" name = "GC_ADDRESS"  />
				</td>
	        </tr>
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">项目经理</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="XMJL" type="text" readonly="readonly" fieldname="XMJL" name = "XMJL"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">联系电话</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input style="width:94%" id="LXDH" type="text" readonly="readonly" fieldname="LXDH" name = "LXDH"  />
				</td>
	        </tr>
      	</table>
					</ul>
				</td>
			</tr>
		</table>
     
		<table border="1" width="100%" cellpadding="0" cellspacing="0" class="content">
			<tr>
				<td class="yw-title">
					<h4 ><span id="spyjtxhid">设备使用中特种作业人员 
   </span>
								<span class="pull-right" > 
								</span>
								</h4>
				</td>
			</tr>
			<tr>
				<td>
					<ul id="ywcl_list">
						<table class="B-table" width="100%" id="rytable">
	         <tr>
				<th width="25%" class="right-border bottom-border text-center">姓名</th>
	       	 	<th width="25%" class="right-border bottom-border text-center">工种</th>
	       	 	<th width="25%" class="right-border bottom-border text-center">资格证编号</th>
	       	 	<th width="25%" class="right-border bottom-border text-center">备注</th>
	        </tr>
      	</table>
					</ul>
				</td>
			</tr>
		</table>
		
		<table border="1" width="100%" cellpadding="0" cellspacing="0" class="content">
			<tr>
				<td class="yw-title">
					<h4 ><span id="spyjtxhid"> 安装告知时应提交的资料</span>
								<span class="pull-right" > 
								</span>
								</h4>
				</td>
			</tr>
			<tr>
				<td>
					<ul id="ywcl_list">
						<table class="B-table" width="100%" id="cltable">
	         <tr>
				<th width="5%" class="right-border bottom-border text-center">序号</th>
	       	 	<th width="75%" class="right-border bottom-border text-center">材料名称</th>
	       	 	<th width="20%" class="right-border bottom-border text-center">监理审核情况</th>
	        </tr>
      	</table>
					</ul>
				</td>
			</tr>
		</table>
		
		
		 </form>
		
        <jsp:include page="/jsp/file_upload/fileuploadold_config_js.jsp" flush="true"/> 
    </div>
   </div>
  </div>

 
  <div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id = "queryXML">
         <input type="hidden" name="txtXML" id = "txtXML">
         <input type="hidden" name="txtFilter" order="desc" fieldname="SHENHE_JIEGUO" id="txtFilter"/>
		 <input type="hidden" name="txtFilter" order="desc" fieldname="CREATED_DATE" id="txtFilter"/>
         <input type="hidden" name="resultXML" id = "resultXML">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
		
 	</FORM>
 </div>
</body>
<script>
</script>
</html>