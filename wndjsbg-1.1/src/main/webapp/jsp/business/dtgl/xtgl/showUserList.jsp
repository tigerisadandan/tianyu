<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<%
%>
<title></title>

<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/ywlz/css/default.css"> </LINK>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/ztree_css/demo.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/base.css" type="text/css" />
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
  <div class="row-fluid" align="center">
  <div class="B-small-from-table-autoConcise">
  <h4 class="title">
	<span class="pull-right"> 
		<button id="btnCancel" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">取消</button>
		<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">确定</button>
	</span>
  </h4>
  <form method="post" id="queryForm">
	<table class="B-table" width="100%">
		<!--可以再此处加入hidden域作为过滤条件 -->
		<TR style="display: none;">
			<TD class="right-border bottom-border"></TD>
			<TD class="right-border bottom-border">
			</TD>
		</TR>
		<!--可以再此处加入hidden域作为过滤条件 -->
		<tr>
				<th width="5%" class="right-border bottom-border text-right">登录名</th>
				<td width="10%" class="right-border bottom-border">
					<input class="span12" type="text" placeholder="" name="LOGON_NAME" fieldname="LOGON_NAME" operation="like" logic="and" onkeydown="queryOperator(event);" ></td>
				<th width="5%" class="right-border bottom-border text-right">姓名</th>
				<td width="10%" class="right-border bottom-border">
					<input class="span12" type="text" placeholder="" name="USER_NAME" fieldname="USER_NAME" operation="like" logic="and" onkeydown="queryOperator(event);" ></td>
				<td width="32%"  class="text-left bottom-border text-right">
					<button	id="queryBtn" class="btn btn-link" type="button"><i class="icon-search"></i>查询</button>
                    <button id="query_clear" class="btn btn-link" type="button"><i class="icon-trash"></i>清空</button>
	            </td>	
			</tr>
	</table>
	</form>
  	<div style="height:5px;"> </div>		
	<div class="overFlowX"> 
		<table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single">
			<thead>
				<tr>
					<th name="XH" id="_XH" width="3%">#</th>
					<th fieldname="USERS_UID" tdalign="center" hidden >&nbsp;&nbsp;</th>
					<th fieldname="LOGON_NAME" tdalign="center" >&nbsp;登录名&nbsp;</th>
					<th fieldname="USER_NAME" tdalign="center" >&nbsp;用户姓名&nbsp;</th>
					<th fieldname="USE_Y" tdalign="center" width="5%">&nbsp;是否启用&nbsp;</th>
					<th fieldname="ADMIN_Y" tdalign="center" width="5%">&nbsp;是否是管理员&nbsp;</th>
					<th fieldname="CREATED_DATE" tdalign="center" >&nbsp;创建时间&nbsp;</th>
					<th fieldname="DESCRIPTION" tdalign="center" >&nbsp;描述&nbsp;</th>
				</tr>
			</thead>
		    <tbody>
		    </tbody>
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
<%--		<input type="hidden" name="txtFilter" order="desc" fieldname="s.CREATED_DATE" id="txtFilter"/>--%>
<%--	<input type="hidden" name="txtFilter" order="desc" fieldname="ID" id="txtFilter"/>--%>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
<script type="text/javascript">
var controllername= "${pageContext.request.contextPath }/userController.do";
//点击保存按钮
$(function() {
	
	//关闭按钮
	$("#btnCancel").click(function(){
		$(window).manhuaDialog.close();
	});
	// 清除表单按钮
	$("#query_clear").click(function() {
       $("#queryForm").clearFormResult();
	});
	//查询按钮
	$("#queryBtn").click(function() {
       doQuery();
	});
	//确定按钮
	$("#btnSave").click(function() {
	   //获取父页面
	   var a=$(window).manhuaDialog.getParentObj();
	   
       if($("#DT1").getSelectedRowIndex()==-1) {
       		//没有选中任何数据
       		a.$("#USER_UID").val("");
			a.$("#LOGON_NAME").val("");
		} else {
			//选中行数据
			var rowValue = $("#DT1").getSelectedRow();
			var tempJson = convertJson.string2json1(rowValue);
			//重新加载数据
			a.$("#USER_UID").val(tempJson.USERS_UID);
			a.$("#LOGON_NAME").val(tempJson.LOGON_NAME);
		}
		//关闭当前页
		$(window).manhuaDialog.close();
	});
});

$(document).ready(function() {
	doQuery();//默认查询
});
//点击回车，触发查询事件
function queryOperator(e){
	if(e.keyCode == 13){
		$("#queryBtn").click();
	}
}

function tr_dbclick(obj,tabListid){
	//alert(obj.LOGON_NAME);
	//获取父页面
	var a=$(window).manhuaDialog.getParentObj();
	//重新加载数据
	//重新加载数据
	a.$("#USER_UID").val(obj.USERS_UID);
	a.$("#LOGON_NAME").val(obj.LOGON_NAME);
	//关闭当前页
	$(window).manhuaDialog.close();
}
//执行查询
function doQuery(){
	 //生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost, DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?queryUser", data, DT1);
}

</script>

</body>
</html>