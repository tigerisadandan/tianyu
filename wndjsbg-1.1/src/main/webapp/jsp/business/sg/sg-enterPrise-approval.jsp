<%@page import="com.ccthanking.framework.model.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext"%>
<%@page import="com.ccthanking.framework.common.UserVO"%>
<%@page import="com.ccthanking.framework.Constants"%><!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<title>业务页面模版表-维护</title>
<%
    User user = RestContext.getCurrentUser();
	String id= user.getIdCard();
%>
<app:base/>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/sgenter/sgEnterPriseLibraryController.do";
var controllernameZizhi= "${pageContext.request.contextPath }/sgenter/sgZizhiController.do";
var controllernameZizhiDengji= "${pageContext.request.contextPath }/sgenter/sgZizhiDengjiController.do";
var controllernameZenZizhi= "${pageContext.request.contextPath }/sgenter/sgEnterpriseZenZizhiController.do";

var id ="<%=id%>";
//页面初始化
$(function() {
	init();
	
	//置所有input 为disabled
	$(":input").each(function(i){
	   $(this).attr("disabled", "true");
	});
	$("#btnUpdate").click(function(){
		window.showModalDialog("${pageContext.request.contextPath }/jsp/business/sg/sg-enterPrise-add.jsp?type=update&_registerPage&id=46","","dialogWidth=600px;dialogHeight=400px");
	});
});
//页面默认参数
function init(){
	$("#QID").val(id);
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	var flagrs =  defaultJson.doQueryJsonList(controllername+"?queryApproval",data,DT1,null, true);
}
function doyJ(obj){

	if(obj.SHENHE_JIEGUO=='10'){
		return '审核通过';
	}else if(obj.SHENHE_JIEGUO=='20'){
		return '审核未通过';
	}
}
//详细信息
function rowView(index){
	$("#DT1").setSelect(index);
	if($("#DT1").getSelectedRowIndex()==-1)
	 {
		requireSelectedOneRow();
	    return
	 }
	var rowValue = $("#DT1").getSelectedRow()
	var obj = convertJson.string2json1(rowValue);
	window.open("${pageContext.request.contextPath }/jsp/business/sg-moni/sgqy-view-task.jsp?id="+obj.SG_ENTERPRISE_LIBRARY_UID,"详细信息");
}
</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">
    <div class="B-small-from-table-autoConcise">
     <form method="post" id="queryForm"  >
      <table class="B-table" width="100%">
      <!--可以再此处加入hidden域作为过滤条件 -->
      	<TR  style="display:none;">
	        <TD class="right-border bottom-border">
	        	<INPUT type="text" class="span12" kind="text" id="QID" name="ID"  fieldname="a.SG_COMPANY_UID" value="" operation="="/>
	        </TD>
			<TD class="right-border bottom-border">
			</TD>
        </TR>
      </table>
      </form>
    <div style="height:5px;"></div>
	</div>
	<div style="height:5px;"></div>
	<div class="B-small-from-table-autoConcise">
		<h4 class="title">审批历史
		</h4>
		<form method="post" id="sgEnterPriseLibraryForm">
			<div class="overFlowX">
				<table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single" noPage="true" pageNum="1000" >
					<thead>
						<tr>
							<th  name="XH" id="_XH" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
							<% if(Constants.getBoolean("DEV_FLAG",false)) {%><th  fieldname="COMPANY_NAME" tdalign="center" colindex=2 hasLink="true" linkFunction="rowView">&nbsp; &nbsp;</th><%} %>
							<th  fieldname="TIJIAO_DATE" tdalign="center" colindex=2 >&nbsp; 提交日期 &nbsp;</th>
							<th  fieldname="USER_NAME" colindex=3 tdalign="center" >&nbsp;审核人&nbsp;</th>
							<th  fieldname="SHENHE_JIEGUO" colindex=3 tdalign="center" CustomFunction="doyJ" >&nbsp;审核结果&nbsp;</th>
							<th  fieldname="SHENHE_YIJIAN" colindex=3 tdalign="center" >&nbsp;审核意见&nbsp;</th>
							<th  fieldname="SHENHE_DATE" colindex=4 tdalign="center" >&nbsp;审核日期&nbsp;</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>	
      	</form>
    </div>
   </div>
  </div>
  <jsp:include page="/jsp/file_upload/fileupload_config.jsp" flush="true"/>
  </div>
  <div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id = "queryXML">
         <input type="hidden" name="txtXML" id = "txtXML">
         <input type="hidden" name="txtFilter"  order="desc" fieldname = "a.SHENHE_DATE" id = "txtFilter">
         <input type="hidden" name="resultXML" id = "resultXML">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
 	</FORM>
 </div>
</body>
<script>
</script>
</html>