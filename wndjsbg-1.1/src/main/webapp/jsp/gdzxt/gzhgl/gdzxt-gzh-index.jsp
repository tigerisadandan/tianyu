<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<%
	String projectid = request.getParameter("projectid");
%>
<app:base/>
<title>告知会首页</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/gzhgl/gzhController.do";
var projectid = <%=projectid %>
//页面初始化
$(function() {
	//给项目编号赋值
	$("#PROJECT_UID").val(projectid);
	init();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"?querySingle",data,DT1);
	});
	
	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#queryForm").clearFormResult();
    });
	
});

//页面默认参数
function init(){
	
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?querySingle",data,DT1);
}

//查看详细信息
function doRandering(obj){
	var showHtml = "";
	showHtml = "<a href='javascript:rowView1("+obj.GZH_UID+");' title='查看详细信息'><i class='icon-file showXmxxkInfo'></i></a>";
	return showHtml;
}
function rowView1(v){
	$(window).manhuaDialog({"title":"告知会>查看","type":"text","content":"${pageContext.request.contextPath }/jsp/business/gzhgl/gzh-edit.jsp?type=detail&gzhId="+v,"modal":"2"});
}
//时间操作
function time1CaoZuo(obj){
	var huiyiTime = obj.HUIYI_DATE;
	huiyiTime = huiyiTime.substring(0,11);
	return huiyiTime;
}
function time2CaoZuo(obj){
	var chuangJianTime = obj.CREATE_DATE;
	chuangJianTime = chuangJianTime.substring(0,11);
	return chuangJianTime;
}
//点击回车，触发查询事件
function queryOperator(e){
	if(e.keyCode == 13){
		$("#btnQuery").click();
	}
}
</script>
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
	<p></p>
	<div class="row-fluid">
		<div class="B-small-from-table-autoConcise">
			<h4 class="title">
				告知会信息
			</h4>
			<form method="post" id="queryForm">
				<table class="B-table" width="100%">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<TR style="display: none;">
						<TD class="right-border bottom-border"></TD>
						<TD class="right-border bottom-border">
							<INPUT type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>
							<INPUT type="text" class="span12" kind="text" id="PROJECT_UID" name="PROJECT_UID" fieldname="gr.PROJECT_UID" value="" operation="="/>
						</TD>
					</TR>
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr>
						<th width="5%" class="right-border bottom-border text-right">会议时间</th>
						<td class="right-border bottom-border" width="50%">
							<input id="HUIYI_DATE" type="text" fieldtype="date" fieldformat="YYYY/MM/DD" fieldname="HUIYI_DATE" class="Wdate" onClick="WdatePicker()" onkeydown="queryOperator(event);" name = "HUIYI_DATE"  operation=">="    /> ~
							<input id="HUIYI_DATE" type="text" fieldtype="date" fieldformat="YYYY/MM/DD" fieldname="HUIYI_DATE" class="Wdate" onClick="WdatePicker()" onkeydown="queryOperator(event);" name = "HUIYI_DATE"  operation="<=" />
							<button id="btnQuery" class="btn btn-link"  type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-search"></i>查询</button>
						</td>
			            <td class="text-left bottom-border text-right">
	                      
			            </td>							
					</tr>
				</table>
			</form>
			<div style="height:5px;"> </div>	
			<div class="overFlowX">
	            <table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single"  pageNum="18">
	                <thead>
	                	<tr>
							<th fieldname="GZH_UID" colindex=0 tdalign="center" CustomFunction="doRandering">&nbsp;操作&nbsp;</th>
							<th fieldname="HUIYI_TITLE" colindex=1 tdalign="center" maxlength="30" >&nbsp;会议主题&nbsp;</th>
							<th fieldname="HUIYI_DIDIAN" colindex=2 tdalign="center" maxlength="30" >&nbsp;会议地点&nbsp;</th>
							<th fieldname="HUIYI_DATE" colindex=3 tdalign="center" CustomFunction="time1CaoZuo" >&nbsp;会议时间&nbsp;</th>
							<th fieldname="ZHUCHI_REN" colindex=4 tdalign="center" >&nbsp;会议主持人&nbsp;</th>
							<th fieldname="CREATE_DATE" colindex=5 tdalign="center" CustomFunction="time2CaoZuo" >&nbsp;创建日期&nbsp;</th>
	                	</tr>
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
<%--		<input type="hidden" name="txtFilter" order="desc" fieldname="LRSJ" id="txtFilter"/>--%>
		<input type="hidden" name="txtFilter" order="desc" fieldname="CREATE_DATE" id="txtFilter"/>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>