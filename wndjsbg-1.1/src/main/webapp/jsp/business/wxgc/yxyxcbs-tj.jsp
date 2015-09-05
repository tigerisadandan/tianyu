<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>预选承包商审核统计</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/wxgc/yxYxcbsController";
var jsonStr = "";
//页面初始化
$(function() {
	init();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"?querytj",data,DT1);
	});

	//按钮绑定事件（导出EXCEL）
	$("#btnExpExcel").click(function() {
		 var t = $("#DT1").getTableRows();
		 if(t<=0){
			 xAlert("提示信息","请至少查询出一条记录！");
			 return;
		 }
		 var fieldnames="COMPANYNAME,CBS_TYPE_SV,TYPENAME,SHJG_SV,SHRQ_SV,SHYJ";
	  	 $(window).manhuaDialog({"title":"导出","type":"text","content":"${pageContext.request.contextPath }/jsp/framework/print/TabListEXP.jsp?tabId=DT1&templateName=wxgc_yxcbssh.xls&fieldnames="+fieldnames+"&dao=yxYxcbsDaoImpl&jsonStr="+jsonStr,"modal":"3"});
	});
	
	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#queryForm").clearFormResult();
        $("#QTZTID").val("40");
        $("#ZT_ID").val("30");
        $("#zt0").removeAttr("checked");
        $("#zt1").removeAttr("checked");
        $("#zt2").removeAttr("checked");
        document.getElementById('zt1').checked=true;
        $("#btnQuery").click();
    });
	
});

//页面默认参数
function init(){
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?querytj",data,DT1);
}


function checkStatus(status){
	 $("#SHJG_ID").val(status);
	 $("#btnQuery").click();
}

function typecodefun(code){
	$("#TYPECODE_ID").val(code);
	$("#btnQuery").click();
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
						<TD class="right-border bottom-border">
							<INPUT type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>
							<INPUT type="text" class="span12" kind="text" id="SHJG_ID" fieldname="SHJG" value="10" operation="="/>
							<INPUT type="text" class="span12" kind="text" id="TYPECODE_ID" fieldname="TYPECODE" operation="like"/>
						</TD>
					</TR>
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr>
						<th width="5%" class="right-border bottom-border text-right">审核结果</th>
						<td width="12%" class="right-border bottom-border">
						<nobr>	
							<input type=radio id="zt0" value="" name="status" onclick="javascript:checkStatus()">全部&nbsp;&nbsp;
							<input type=radio id="zt1"  value="10" name="status" onclick="javascript:checkStatus(10)" checked="checked" >通过&nbsp;&nbsp;
      						<input type=radio id="zt2"  value="20" name="status" onclick="javascript:checkStatus(20)">退回
						</nobr>
						</td>
						<th width="5%" class="right-border bottom-border text-right">承包商名称</th>
						<td class="right-border bottom-border" width="20%">
							<input class="span12" type="text" id="COMPANYNAME" name="COMPANYNAME" fieldname="COMPANYNAME" operation="like" >
						</td>
						<th width="5%" class="right-border bottom-border text-right">工程类型</th>
						<td class="right-border bottom-border" width="8%">
							<select class="span12"  id="GC_TYPE_CODE" onchange="typecodefun(this.value)" defaultMemo="--全部--" name="GC_TYPE_CODE" kind="dic" src="T#YX_GC_TYPE:GC_TYPE_CODE:GC_TYPE_NAME:ENABLED='1' order by SERIAL_NO asc " ></select>
						</td>
						<th width="5%" class="right-border bottom-border text-right">审核日期段</th>
						<td class="right-border bottom-border" width="20%">
							<input class="span12" width="8%" type="text" fieldtype="date" id="SHRQ" name="SHRQ" fieldname="SHRQ" operation=">=" fieldformat='YYYY-MM-DD' class='Wdate' onClick='WdatePicker()'>
							至
							<input class="span12" width="8%" type="text" fieldtype="date" id="SHRQ" name="SHRQ" fieldname="SHRQ" operation="<=" fieldformat='YYYY-MM-DD' class='Wdate' onClick='WdatePicker()'>
						</td>
			            <td class="text-left bottom-border text-right">
	                        <button id="btnQuery" class="btn btn-link"  type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-search"></i>查询</button>
           					<button id="btnClear" class="btn btn-link" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-trash"></i>清空</button>
			           		<button id="btnExpExcel" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">导出</button>
			            </td>							
					</tr>
				</table>
			</form>
			<div style="height:5px;"> </div>	
			<div class="overFlowX">
	            <table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single"  pageNum="18">
	                <thead>
	                	<tr>
	                		<th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
							<th fieldname="COMPANYNAME" colindex=2 tdalign="left" style="width:250px" >&nbsp;承包商名称&nbsp;</th>
							<th fieldname="CBS_TYPE_SV" colindex=1 tdalign="center" style="width:5px">&nbsp;承包商类型&nbsp;</th>
							<th fieldname="TYPENAME" colindex=3 tdalign="center" style="width:10px">&nbsp;预选类型&nbsp;</th>
							<th fieldname="SHJG_SV" colindex=3 tdalign="center" style="width:5px">&nbsp;状态&nbsp;</th>
							<th fieldname="SHRQ_SV" colindex=4 tdalign="center" style="width:5px">&nbsp;审核日期&nbsp;</th>
							<th fieldname="SHYJ" colindex=5 tdalign="center" style="width:250px">&nbsp;审核意见&nbsp;</th>
<%--						<th fieldname="ENABLED_SV" colindex=7 tdalign="center" >&nbsp;是否有效&nbsp;</th>--%>
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
		<input type="hidden" name="txtFilter" order="desc" fieldname="SHRQ" id="txtFilter"/>
<%--		<input type="hidden" name="txtFilter" order="desc" fieldname="ID" id="txtFilter"/>--%>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>