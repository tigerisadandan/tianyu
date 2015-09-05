<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.params.SysPara.SysParaConfigureVO"%>
<%@ page import="com.ccthanking.framework.params.ParaManager"%>
<%@ page import="com.ccthanking.framework.Constants"%>
<%@ include file="/jsp/framework/common/taglibs.jsp" %>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base />
<title>危险源管理</title>
<%
String type=request.getParameter("type");
SysParaConfigureVO syspara  = (SysParaConfigureVO) ParaManager.getInstance().getSysParameter(Constants.getString(Constants.FILEUPLOADOLD_ROOT_JS, "FILEUPLOADOLD_ROOT_WNDJSJS"));
String fileRoot = syspara.getSysParaConfigureParavalue1();
String spyw_uid = request.getParameter("spyw_uid");
%>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/ywlz/css/default.css"> </LINK>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>	
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/kindeditor/plugins/code/prettify.css" />
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/kindeditor/kindeditor.js"></script>
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/kindeditor/lang/zh_CN.js"></script>

<script type="text/javascript" charset="utf-8">
var controllername = "${pageContext.request.contextPath}/wxy/wxySjkController";
$(function() {
	init();
	//查询按钮点击事件
	$("#btnQuery").click(function() {
		getData();
	});
	
	//清空按钮点击事件
    $("#btnClear").click(function() {
        $("#condition").clearFormResult();
       		 init();
    	});
	});



function init(){
	//加载工程数量
	getGcCount();
	//显示通知单数据
	getData();
}

//空格=提交
function EnterPress(){
var event=arguments.callee.caller.arguments[0]||window.event;//消除浏览器差异  
	if(event.keyCode == 13){
		$("#btnQuery").click();
	}
}

//改变工程类型单选框显示对应的值
function changeType(gcType){
	getData();
}
//改变信息状态
function checkStatus(){
	getGcCount();
	getData();
}
//根据条件查询数据显示到表格中
function getData(){
	var stateType = $("input[type='radio'][name='status']:checked").val();//获取选中的状态
	if (stateType=="33") {
		$("#gcgl").show();
	}else{
		$("#gcgl").hide();
	}
	var gcType = $("input[type='radio'][name='radio']:checked").val();	//选中的工程类型
	var pname = $("#PROJECTS_NAME").val();//项目名称			
	var gc_name = $("#GC_NAME").val();//工程名称
	var sg_name = $("#SG_NAME").val();//施工单位名称
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入	
	defaultJson.doQueryJsonList(controllername+"?queryAllGC&gcType="+gcType+"&stateType="+stateType+"&pname="+pname+"&gc_name="+gc_name+"&sg_name="+sg_name,data,DT1);
}
//根据状态加载工程数量
function getGcCount(){
	var stateType = $("input[type='radio'][name='status']:checked").val();//获取选中的状态
	$.ajax({
		url : controllername+"?queryGcCountByState&stateType="+stateType,
		data : {},
		type : "post",
		success : function(response){
			var res = eval('('+response+')');
			var values = eval('('+res.msg+')');
			var data = values[0];
			$("#sjk").text(data.sjk);
			$("#gdmb").text(data.gdmb);
			$("#dzgc").text(data.dzgc);
			$("#jsj").text(data.jsj);
			$("#mq").text(data.mq);
			$("#gjg").text(data.gjg);
			$("#wj").text(data.wj);
		}
	});
}

function doRandering(obj){
	var showHtml = "";
	showHtml = "<a href='javascript:rowView1("+obj.GONGCHENG_UID+");' style='color:#9c6531'>"+obj.GC_NAME+"</a>"
	return showHtml;
}
function rowView1(t){
	if($("#DT1").getSelectedRowIndex()==-1)
	 	{
			requireSelectedOneRow();
	    	return
	 	}
		 var rowValue = $("#DT1").getSelectedRow();
	     var tempJson = convertJson.string2json1(rowValue);
	     var gcId = tempJson.GONGCHENG_UID;
	     var gcType = $("input[type='radio'][name='radio']:checked").val();	//选中的工程类型
	     if (gcType=="jkzhjs") {
			 window.open("${pageContext.request.contextPath}/jsp/gdzxt/wxy/wxySjk-add.jsp?openType=chakan&gcid="+gcId,"基坑填报","height=600, width=850");
		 }else if (gcType=="gdmb") {
			window.open("${pageContext.request.contextPath}/jsp/gdzxt/wxy/wxyGdmb-add.jsp?openType=chakan&gcid="+gcId,"基坑填报","height=600, width=850");
		}else if (gcType=="dzgc") {
			window.open("${pageContext.request.contextPath}/jsp/gdzxt/wxy/wxyDzgc-add.jsp?openType=chakan&gcid="+gcId,"基坑填报","height=600, width=850");
		}else if (gcType=="jsj") {
			window.open("${pageContext.request.contextPath}/jsp/gdzxt/wxy/wxyJsj-add.jsp?openType=chakan&gcid="+gcId,"基坑填报","height=600, width=850");
		}else if (gcType=="mq") {
			window.open("${pageContext.request.contextPath}/jsp/gdzxt/wxy/wxyMq-add.jsp?openType=chakan&gcid="+gcId,"基坑填报","height=600, width=850");
		}else if (gcType=="gjg") {
			window.open("${pageContext.request.contextPath}/jsp/gdzxt/wxy/wxyGjg-add.jsp?openType=chakan&gcid="+gcId,"基坑填报","height=600, width=850");
		}else if (gcType=="wjsm") {
			window.open("${pageContext.request.contextPath}/jsp/gdzxt/wxy/wxyWj-add.jsp?openType=chakan&gcid="+gcId,"基坑填报","height=600, width=850");
		}
}
//过程管理
function doRandering1(obj){
	var showHtml = "";
	var gcType = $("input[type='radio'][name='radio']:checked").val();	//选中的工程类型
	var type = "";
	if(gcType=="jkzhjs"){
		type="SJK";
	 }else if(gcType=="gdmb"){
		 type="GDMB";
	 }else if(gcType=="dzgc"){
		 type="DZGC";
	 }else if(gcType=="jsj"){
		 type="JSJ";
	 }else if(gcType=="mq"){
		 type="MQ";
	 }else if(gcType=="gjg"){
		 type="GJG";
     }
     else if(gcType=="wjsm"){
		  type="WJ";
     }
	showHtml = "<a href='javascript:guocheng("+obj.GONGCHENG_UID+",\""+type+"\");' >过程管理</a>"
	return showHtml;
}
function guocheng(gcId,type){
	//	window.open("${pageContext.request.contextPath }/jsp/gdzxt/sgsx/sxsp/yw_frame.jsp?gcid="+$("#GDZXT_XM_ID").val(),"审批业务信息");
	    $("#gdzxt_gcid").val(gcId);
		$("#gc_type").val(type);
        var f =document.getElementById('wxyformid');
        var url='${pageContext.request.contextPath }/wxy/gdzxt/wxy/wxy_frame';
        //var url='${pageContext.request.contextPath }/pagegdzxt/framework/gdzxt/frame_gdzxt_main';
		f.action=url;
		f.target="_blank"; 
		f.submit();
	}
</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">  
	<div class="B-small-from-table-autoConcise">
     	<div style="height:5px;"> </div>	
	    <form method="post" id="queryForm">
				<table class="B-table" width="100%" id="condition">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr style="display: none;">
						<td class="right-border bottom-border"></TD>
						<td class="right-border bottom-border">
							<input type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>
							  <!--  <input type="text" class="span12" id="type"  fieldname="type" value="jkzhjs"/>
							  <input type="text" class="span12" id="state"  fieldname="state" value="0"/> -->
						</td>
					</tr>
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr>
							<td class="right-border bottom-border" colspan="8">
									
                            <label style="float:left;margin-right: 15px;"><input type="radio" name="radio" value="jkzhjs" onclick="javascript:changeType()" checked>基坑支护降水(<span style="color:blue;" id="sjk" >7</span>)</label>
　　　						<label style="float:left;margin-right: 15px;"><input type="radio" name="radio" value="gdmb" onclick="javascript:changeType()" >模板、混凝土模板支撑(<span style="color:blue;" id="gdmb" ></span>)</label>
　　　						<label style="float:left;margin-right: 15px;"><input type="radio" name="radio" value="dzgc" onclick="javascript:changeType()" >起重吊装安装拆卸(<span style="color:blue;" id="dzgc" ></span>)</label>
　　　						<label style="float:left;margin-right: 15px;"><input type="radio" name="radio" value="jsj" onclick="javascript:changeType()" >脚手架(<span style="color:blue;" id="jsj" ></span>)</label>
							<label style="float:left;margin-right: 15px;"><input type="radio" name="radio" value="mq" onclick="javascript:changeType()" >幕墙(<span style="color:blue;" id="mq"></span>)</label>
							<label style="float:left;margin-right: 15px;"><input type="radio" name="radio" value="gjg" onclick="javascript:changeType()" >钢结构(<span style="color:blue;" id="gjg" ></span>)</label>
							<label style="float:left;"><input type="radio" name="radio" value="wjsm" onclick="javascript:changeType()" >网架、索模结构安装(<span style="color:blue;" id="wj" ></span>)</label>
						</td>
			   		</tr>
					<tr>
					<td class="right-border bottom-border" width="20%">
							 <input type=radio value="30" name="status" onclick="javascript:checkStatus()" checked="checked"  >待审核<h id="dsid"></h>&nbsp;&nbsp;
      						 <input type=radio value="33" name="status" onclick="javascript:checkStatus()">已审核<h id="ysid"></h>&nbsp;&nbsp;
      						 <input type=radio value="32" name="status" onclick="javascript:checkStatus()">已退回<h id="thid" ></h></td>
						<th width="4%" class="right-border bottom-border text-right">项目名称</th>
						<td class="right-border bottom-border" width="20%">
							<input class="span12" type="text" id="PROJECTS_NAME" name="PROJECTS_NAME" fieldname="PROJECTS_NAME" onkeydown="EnterPress()" operation="like" >
						</td>	
						<th width="3%" class="right-border bottom-border text-right">工程名称</th>
						<td class="right-border bottom-border" width="10%">
							<input class="span12" type="text" id="GC_NAME" name="GC_NAME" fieldname="GC_NAME" onkeydown="EnterPress()" operation="like" >
						</td>	
						<th width="3%" class="right-border bottom-border text-right">施工单位</th>
						<td class="right-border bottom-border" width="20%">
							<input class="span12" type="text" id="SG_NAME" name="SG_NAME" onkeydown="EnterPress()" fieldname="SG_NAME" operation="like" >
						</td>	
						<td class="text-left bottom-border text-right">
	                        <button id="btnQuery" class="btn btn-link"  type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-search"></i>查询</button>
           					<button id="btnClear" class="btn btn-link" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-trash"></i>清空</button>
			            </td>          	  
			   </tr>
				</table>
			</form>
		   <div style="height:5px;"> </div>	
			<div class="overFlowX">
		            <table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single"  pageNum="18">
		                <thead>
		                	<tr>
		                		<th  name="XH" id="_XH" style="width:10px" colindex=0 tdalign="center">&nbsp;序号&nbsp;</th>	               		 	                		
		                		<th id="" fieldname="PROJECTS_NAME" colindex=1 tdalign="center" >&nbsp;项目名称 &nbsp;</th>
		                		<th id="" fieldname="CNAME" colindex=2 tdalign="center" >&nbsp;建设单位&nbsp;</th>
		                		<th id="" fieldname="GONGCHENG_UID" colindex=2 tdalign="center" hidden >&nbsp;工程编号&nbsp;</th>
								<th id="" fieldname="GC_NAME" colindex=3 tdalign="center" maxlength="10" CustomFunction="doRandering">&nbsp;工程名称&nbsp;</th>
								<th id="" fieldname="BDATE" colindex=4 tdalign="center">&nbsp;计划开始时间 &nbsp;</th>
								<th id="" fieldname="EDATE" colindex=5 tdalign="center">&nbsp;计划结束时间&nbsp;</th>
								<th id="gcgl" fieldname="GONGCHENG_UID" colindex=6 tdalign="center" CustomFunction="doRandering1" >&nbsp;过程管理&nbsp;</th>
								<th id="" fieldname="XMJL_NAME" colindex=7 tdalign="center">&nbsp;项目经理&nbsp;</th>
								<th id="" fieldname="SG_NAME" colindex=8 tdalign="center">&nbsp;施工单位&nbsp;</th>
								<th id="" fieldname="JL_NAME" colindex=9 tdalign="center">&nbsp;监理单位&nbsp;</th>							 			
		                	</tr>
		                </thead>
		              	<tbody></tbody>
		           </table>
	       </div>
       </div>
   </div>
   <form id="wxyformid" method="post" >
		<input type="hidden" name="gdzxt_gcid" id="gdzxt_gcid">
		<input type="hidden" name="gc_type" id="gc_type" >
</form>
  </div>
   <div align="center">
		<FORM name="frmPost" method="post" style="display:none"
			target="_blank">
			<!--系统保留定义区域-->
			<input type="hidden" name="queryXML" id="queryXML"> 
			<input type="hidden" name="txtXML" id="txtXML">
			<input type="hidden" name="txtFilter" order="desc" fieldname="" id="txtFilter"/>
			<input type="hidden" name="resultXML" id="resultXML">
			<!--传递行数据用的隐藏域-->
			<input type="hidden" name="rowData">
		</FORM>
	</div>
</body>
<script>
</script>
</html>