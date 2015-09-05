<%@page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext"%>
<%@page import="com.ccthanking.framework.model.User"%>
<%@page import="com.ccthanking.framework.Globals"%><!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>

<app:base/>
<title>人员信息首页</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/sgenter/sgScoreController/";

//页面初始化
$(function() {
	init();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {

		
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"query",data,DT1,null,true);
		 var rows = $("tbody tr" ,$("#DT1"));
			if(rows.size()==0){
				$("tbody" ,$("#DT1")).append("<tr><td colspan=\"8\" style=\"height: 1px;\" align=\"center\">暂无记录</td></tr>");
			}
	});
	
	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#queryForm").clearFormResult(); 
        //closeParentCloseFunction();
        clickRadioShowDate();
    });
});
function doRandering(obj){
	var showHtml = "";
	showHtml = "<a href='javascript:rowView1(this);' title='查看详细信息'>"+COMPANY_NAME+"</a>";
	return showHtml;
}
function rowView(id){	
	window.open("${pageContext.request.contextPath }/sgentscore/"+id,"企业信息审批");//传递一个ID给详细页面，用于查找 
}

//页面默认参数
function init(){
	clickRadioShowDate();
}
//单选按钮触发事件 
function clickRadioShowDate(){

	
	
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
    defaultJson.doQueryJsonList(controllername+"query",data,DT1,null,true);//,null,true无记录时不弹出提示 
    //处理IE浏览器在查询无记录时出现的滚动条问题
    var rows = $("tbody tr" ,$("#DT1"));
	if(rows.size()==0){
		$("tbody" ,$("#DT1")).append("<tr><td colspan=\"8\" style=\"height: 1px;\" align=\"center\">暂无记录</td></tr>");
	}
}

function doQuery(){
	$("#btnQuery").click();
}
function EnterPress(){
	if(event.keyCode == 13){
		$("#btnQuery").click();
	}
}


function doZizhi(obj){
	if(obj.ZIZHI_NAME==""){return '<div style="text-align:center">—</div>';}
	var showHtml = obj.ZIZHI_NAME;
	if(obj.DENGJI_NAME!=""){
		showHtml += "<br/>"+obj.DENGJI_NAME;
	}
	return showHtml;
}
function doZengZizhi(obj){
	if(obj.ZENG_ZIZHI==""){return '<div style="text-align:center">—</div>';}
	var showHtml = "";
	var arr = obj.ZENG_ZIZHI.split(",");
	for(i=0;i<arr.length;i++){
		showHtml += arr[i]+"<br/>";
	}
	return showHtml;
}
function doZT(obj){
	var status = obj.STATUS;
	if(status=="30"){
		return '<span class="label label-info">'+obj.STATUS_SV+'</span>';
	}else if(status=="1"){
	 	return '<span class="label label-success">'+obj.STATUS_SV+'</span>';
	}else if(status=="20"){
	 	return '<span class="label label-warning">'+obj.STATUS_SV+'</span>';
	}
}
function doDy(obj){
	if(obj.WAIDI_Y=="Y"){
		return "外地";
	}else if(obj.WAIDI_Y=="N"){
		return "本地";
	}
}
function doCpname(obj){
	return "<a href='javascript:void(0)' onclick='rowView("+obj.SG_ENTERPRISE_LIBRARY_UID+","+obj.STATUS+")'title='查看详细信息'><i class='icon-file showXmxxkInfo'></i></a>";
}
function EnterPress(){
	if(event.keyCode == 13){
		$("#btnQuery").click();
	}
}
function doScore(obj){
		return "<a href='javascript:void(0)' onclick='showScoreView("+obj.SG_SCORE_UID+")'>"+obj.SCORE+"</a>";
}
function showScoreView(id){
	window.open("${pageContext.request.contextPath }/sgentscore/"+id,"企业信用分数查看");
}
</script>
</head>
<body onkeydown="EnterPress()">
<app:dialogs/>
<div class="container-fluid">
<p></p>
	<div class="row-fluid">
		<div class="B-small-from-table-autoConcise">
<%--			<h4 class="title">--%>
<%--				<span class="pull-right">--%>
<%--					<button id="btnExpExcel" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">导出</button>--%>
<%--				</span>--%>
<%--			</h4>--%>
			<form method="post" id="queryForm">
				<table class="B-table" width="100%">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<TR style="display: none;">
						<TD class="right-border bottom-border">
						    <INPUT type="text" class="span12" kind="text" id="STATUS" name="STATUS" fieldname="n.STATUS" value="1" operation="="/>
						</TD>
					</TR>
				<!--可以再此处加入hidden域作为过滤条件 -->
			     <tr>
			      <td class="right-border bottom-border" width="3%">
				  <select class="span12" id="WAIDI_Y" operation="=" name="WAIDI_Y" fieldname="n.WAIDI_Y" >
				  	<option value="">-地域-</option>
				  	<option value="N">本地</option>
				  	<option value="Y">外地</option>
				  </select>
			     </td>
			     <th width="1%" class="right-border bottom-border text-right">企业名称</th>
			      <td class="right-border bottom-border" width="10%">
				  <input class="span12" id="COMPANY_NAME" type="text"  src="COMPANY_NAME" operation="like" name="COMPANY_NAME" fieldname="t.COMPANY_NAME">
			     </td>
			     <td class="right-border bottom-border text-right" width="10%" rowspan="2" style="background-image: none; background-position: initial initial; background-repeat: initial initial;">
	          		<button id="btnQuery" class="btn btn-link"  type="button"><i class="icon-search"></i>查询</button>
	         		<button id="btnClear" class="btn btn-link" type="button"><i class="icon-trash"></i>清空</button>
           	   	</td>
			   </tr>
			   </table>
			</form>
			<div style="height:5px;"> </div>	
			<div class="overFlowX">
			 
	            <table width="100%" print="true" class="table-hover table-activeTd B-table" id="DT1" type="single"  pageNum="12">
	              <thead>
	                <tr>
	                	<th name="XH" id="_XH" style="width:10px" colindex=1 noprint="true" tdalign="center">&nbsp;#&nbsp;</th>
						<th fieldname="COMPANY_NAME" colindex=3 tdalign="left">&nbsp;施工企业名称&nbsp;</th>
						<th fieldname="DENGLU_CODE" colindex=3 tdalign="CENTER">&nbsp;登陆代码&nbsp;</th>
						<th fieldname="SCORE" colindex=4 tdalign="center"  CustomFunction="doScore">&nbsp;信用分数&nbsp;</th>
                        <th fieldname="ZHIZHAO" colindex=5 tdalign="center" >&nbsp;营业执照注册号&nbsp;</th>
                        <th fieldname="SHUIWU" colindex=6 tdalign="center" >&nbsp;税务登记号&nbsp;</th>
						<th fieldname="SCORE" colindex=10 tdalign="center"  CustomFunction="doDy">&nbsp;地域&nbsp;</th>
	                </thead>
	              	<tbody></tbody>
	           </table>
	       </div>
	 	</div>
	</div>     
</div>
<div align="center">
	<FORM name="frmPost" method="post" style="display: none" target="_blank">
		<!--系统保留定义区域-->
		<input type="hidden" name="queryXML"/> 
		<input type="hidden" name="txtXML"/>
		<input type="hidden" name="txtFilter" order="desc" fieldname="t.CREATED_DATE" id="txtFilter"/>
<%--		<input type="hidden" name="txtFilter" order="desc" fieldname="ID" id="txtFilter"/>--%>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>