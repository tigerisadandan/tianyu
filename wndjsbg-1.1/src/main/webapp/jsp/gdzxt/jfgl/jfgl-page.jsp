<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>加分管理</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/wxgc/yxGcTypeController";

//页面初始化
$(function() {
	init();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		//调用ajax插入
		//defaultJson.doQueryJsonList(controllername+"?query",data,DT1);
	});

	
	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#queryForm").clearFormResult();
        $("#btnQuery").click();
    });
	
});

//页面默认参数
function init(){
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	//defaultJson.doQueryJsonList(controllername+"?query",data,DT1);
}




</script>
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
	<p></p>
	<div class="row-fluid">
		<div class="B-small-from-table-autoConcise">
			
			<form method="post" id="queryForm">
				<table class="B-table" width="100%">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<TR style="display: none;">
						<TD class="right-border bottom-border"></TD>
						<TD class="right-border bottom-border">
							<INPUT type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>
						</TD>
					</TR>
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr>
						<td class="text-left bottom-border"  width="15%">
							<input  type="radio" checked="checked" id="shzt" name="shzt" fieldname="shzt" operation="=" >待审核 &nbsp;&nbsp;
							<input type="radio" id="shzt" name="shzt" fieldname="shzt" operation="=" >已审核
						</td>
						<th width="5%" class="right-border bottom-border text-right">申请单位</th>
						<td>
							<input type="text" id="" name="">
						</td>
						<th width="5%" class="right-border bottom-border text-right">工程名称</th>
						<td>
							<input type="text" id="" name="">
						</td>
						<th width="5%" class="right-border bottom-border text-right">日期</th>
						<td>
							<input class="span12"  type="text" fieldtype="date" id="SHRQ" name="SHRQ" fieldname="SHRQ" operation=">=" fieldformat="YYYY-MM-DD" onclick="WdatePicker()">
							至
							<input class="span12"  type="text" fieldtype="date" id="SHRQ" name="SHRQ" fieldname="SHRQ" operation="<=" fieldformat="YYYY-MM-DD" onclick="WdatePicker()">
						</td>
			            <td class="text-left bottom-border  text-right">
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
	                		<th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;序号&nbsp;</th>
	                		<th fieldname="GC_TYPE_UID" colindex=2 tdalign="center" style="width:10px"  CustomFunction="doeditor" noprint="true">&nbsp;&nbsp;</th>
							<th fieldname="GC_TYPE_CODE" colindex=1 tdalign="center" style="width:5px" >&nbsp;打印&nbsp;</th>       
							<th fieldname="GC_TYPE_NAME" colindex=2 tdalign="center" style="width:100px"  >&nbsp;审核状态&nbsp;</th>
							<th fieldname="LINK" colindex=3 tdalign="center" style="width:140px"  >&nbsp;申请单位&nbsp;</th>
							<th fieldname="RETURN_LINK" colindex=4 tdalign="center" style="width:140px"  >&nbsp;工程名称&nbsp;</th>
							<th fieldname="ENABLED_SV" colindex=6 tdalign="center" style="width:10px" >&nbsp;申请原因&nbsp;</th>
							<th fieldname="ENABLED_SV" colindex=6 tdalign="center" style="width:10px" >&nbsp;加分对象&nbsp;</th>
							<th fieldname="ENABLED_SV" colindex=6 tdalign="center" style="width:10px" >&nbsp;日期&nbsp;</th>
							<th fieldname="ENABLED_SV" colindex=6 tdalign="center" style="width:10px" >&nbsp;审核 &nbsp;</th>
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
		<input type="hidden" name="txtFilter" order="desc" fieldname="GC_TYPE_NAME" id="txtFilter"/>
<%--		<input type="hidden" name="txtFilter" order="desc" fieldname="ID" id="txtFilter"/>--%>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>