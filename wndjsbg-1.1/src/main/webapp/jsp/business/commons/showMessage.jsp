<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<%
	String uid = (String)request.getAttribute("uid");
%>
<title>查看审核意见</title>
<app:base/>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/sgenter/sgEnterPriseLibraryController.do";

//页面初始化
$(function() {
	init();
	$("#btnClose").click(function(){
		window.close();
	})
	$("#btnReturn").click(function(){
<%--		location.href = "${pageContext.request.contextPath }/jsp/business/commons/showCompanyList.jsp";--%>
		history.go(-1)
	})
});
//页面默认参数
function init(){
	$("#QID").val('<%=uid%>');
	showList(false);
}
function showList(type){
	var data = combineQuery.getQueryCombineData(queryForm,frmPost);
	var data1 = {
		msg : data
	};
	$.ajax({
		url : controllername+"?query",
		data : data1,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			
			var resultobj = defaultJson.dealResultJson(response.msg);
			$("#dw").text(resultobj.COMPANY_NAME);
			$("#dm").text(resultobj.DENGLU_CODE);
			$("#yj").text(resultobj.SHENHE_YIJIAN);
			$("#rq").text(resultobj.SHENHE_DATE);
		}
	});
}
</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">
<input type='hidden' id='previewFileid' >
    <div class="B-small-from-table-autoConcise">
     <form method="post" id="queryForm"  >
      <table class="B-table" width="100%">
      <!--可以再此处加入hidden域作为过滤条件 -->
      	<TR  style="display:none;">
	        <TD class="right-border bottom-border">
	        	<INPUT type="text" class="span12" kind="text" id="QID" name="STATUS"  fieldname="n.SG_ENTERPRISE_LIBRARY_UID" value="10" operation="="/>
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
      <h4 class="title">审核信息
      		<span class="pull-right" id="btnSave_span">
			      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
			      		<button id="btnReturn" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">返回</button>
					</span>
      </h4>
	<form method="post" id="sgEnterPriseLibraryForm">
	
		<table class="B-table" width="100%" >
			        <tr>
						<th width="27%" class="right-border bottom-border text-right">施工单位</th>
			       	 	<td class="bottom-border right-border" id="dw">
			       	 	</td>
			        </tr>
			        <tr>
						<th width="27%" class="right-border bottom-border text-right">登录代码</th>
			       	 	<td class="bottom-border right-border" id="dm">
			       	 	</td>
			        </tr>
			        <tr>
						<th width="27%" class="right-border bottom-border text-right">审核意见</th>
			       	 	<td class="bottom-border right-border" id="yj">
			       	 	</td>
			        </tr>
			        <tr>
						<th width="27%" class="right-border bottom-border text-right">审核日期</th>
			       	 	<td class="bottom-border right-border" id="rq">
			         		<input class="span12" style="width:35%" id="DAIMA_Z" maxlength="8" type="text" placeholder="必填" check-type="required" fieldname="DAIMA_Z" name = "DAIMA_Z"/>
			       	 	</td>
			        </tr>
			        </table>
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
         <input type="hidden" name="txtFilter"  order="desc" fieldname = "SERIAL_NO" id = "txtFilter">
         <input type="hidden" name="resultXML" id = "resultXML">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
 	</FORM>
 </div>
</body>
<script>
</script>
</html>