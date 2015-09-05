<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>危险源——过程管理</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/ywlz/css/default.css"> </LINK>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/project/jsCompanyController";

//页面初始化
$(function() {
	$("#QGONGCHENG_UID").val(parent.document.getElementById("GDZXT_XM_ID").value);
});
function doCg(v1){
	//var v1 = $("input:radio[name='STATUS']:checked").val();
	if($("#QWXY_TYPE").val()==v1)
		return true;
	
	$("#QWXY_TYPE").val(v1);
	if(v1=='1'){
		$("#div1").show();
		$("#div2").hide();
		$("#div3").hide();
		if($("#JCYS_WXY_JSJ_GC_UID").val()!=""){
		queryFileData($("#JCYS_WXY_JSJ_GC_UID").val(), "", "", "460010");
		}
	}else if(v1=='2'){
		$("#div1").hide();
		$("#div2").show();
		$("#div3").hide();
		if($("#ZJYS_WXY_JSJ_GC_UID").val()!=""){
			queryFileData($("#ZJYS_WXY_JSJ_GC_UID").val(), "", "", "460010");
			}
	}else if(v1=='3'){
		$("#div1").hide();
		$("#div2").hide();
		$("#div3").show();
	}

}
</script>
</head>
<body>
<div class="container-fluid">
	<p></p>
	<div class="row-fluid">
		<div class="B-small-from-table-autoConcise">
<%--			<h4 class="title">
				建设单位
				<span class="pull-right">  
      				<button id="btnUpdate" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">审核</button>
      				<button id="btnExpExcel" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">导出</button>
				</span>
			</h4>--%>
			<form method="post" id="queryForm">
				
					
							<label class="col-sm-2 radio inline">
						<input type="radio" name="STATUS" value="40" onclick="doCg('1')" checked="checked">
						基础验收
						<!-- <font style="color: red" id="wtj">(0)</font> -->
					</label>
					<label class="col-sm-3 radio inline">
						<input type="radio" name="STATUS" value="0" onclick="doCg('2')">
						中间验收：每三步	
					</label>
					<label class="col-sm-2 radio inline">
						<input type="radio" name="STATUS" value="1" onclick="doCg('3')">
					拆除 
					</label>
			</form>
			<div style="height:5px;"> </div>	
			<div id="div1">
            <jsp:include page="/jsp/gdzxt/wxy/wxyJsj-gcgl-jcys.jsp"  flush="true" /></div>
            <div id="div2"  style="display: none;">
            <jsp:include page="/jsp/gdzxt/wxy/wxyJsj-gcgl-zjys.jsp"  flush="true" /></div> 
            <div id="div3" style="display: none;">
            <jsp:include page="/jsp/gdzxt/wxy/wxyJsj-gcgl-cc.jsp"  flush="true" /></div>
	 	</div>
	</div>     
</div>
<jsp:include page="/jsp/file_upload/fileuploadold_config_js.jsp" flush="true"/> 
<div align="center">
	<FORM name="frmPost" method="post" style="display: none" target="_blank">
		<!--系统保留定义区域-->
		<input type="hidden" name="queryXML"/> 
		<input type="hidden" name="txtXML"/>
		<input type="hidden" name="txtFilter" order="desc" fieldname="GONGCHENG_UID" id="txtFilter"/>
<%--		<input type="hidden" name="txtFilter" order="desc" fieldname="ID" id="txtFilter"/>--%>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>