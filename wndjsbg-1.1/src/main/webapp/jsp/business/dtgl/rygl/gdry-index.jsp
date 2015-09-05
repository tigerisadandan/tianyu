<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp" %>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base />
<title>人员管理</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
var controllername = "${pageContext.request.contextPath}/rygl/minGongController";
$(function() {
	init();
	//查询按钮点击事件
	$("#btnQuery").click(function() {
		doProQuery();
	});
	
	//清空按钮点击事件
	$("#btnClear").click(function() {
    $("#condition").clearFormResult();
    init();
	});	
});
	
	//查询函数
	function doProQuery(){
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"?query2",data,DT1);
	}
	
	function init(){
		doProQuery();
	}
	
	//查看详细信息
	function doRandering(obj){
	var showHtml = "";
	showHtml = "<a href='javascript:rowView(this);' class='btn btn-link' title='操作'><i class='icon-file'></i></a>";
	return showHtml;
}

	function rowView(index){
	if($("#DT1").getSelectedRowIndex()==-1)
 	{
		requireSelectedOneRow();
    	return
 	}
	 var rowValue = $("#DT1").getSelectedRow();
     var tempJson = convertJson.string2json1(rowValue);
     var MINGONG_UID = tempJson.MINGONG_UID;
	 $(window).manhuaDialog({"title":"查看人员信息","type":"text","content":"${pageContext.request.contextPath }/jsp/business/dtgl/rygl/gdry-detail.jsp?MINGONG_UID="+MINGONG_UID,"modal":"1"});
}
	

	function FormatSTATUS(obj){
		var STATUS=obj.STATUS;
		if(STATUS=='1'){
			STATUS="在工";
		}else if(STATUS=='0'){
			STATUS="空闲";
		}else if(STATUS=='-1'){
			STATUS="注销";
		}		
		return STATUS;
	}
	
	//空格=提交
	function EnterPress(){
		if(event.keyCode == 13){
			$("#btnQuery").click();
		}
	}
</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">  
	<div class="B-small-from-table-autoConcise">
     
	    <form method="post" id="queryForm">
				<table class="B-table" width="100%" id="condition">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr style="display: none;">
						<td class="right-border bottom-border"></TD>
						<td class="right-border bottom-border">
							<input type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>
						</td>
					</tr>
					
					<tr>
						<th width="3%" class="right-border bottom-border text-right">姓名</th>
						<td width="15%">
							 <input class="span12" type="text" id="MINGONG_NAME" fieldname="MINGONG_NAME" onkeydown="EnterPress()" operation="like" >
						</td>
						
						<th width="5%" class="right-border bottom-border text-right">证件号码</th>
						<td width="15%">
							 <input class="span12" type="text" id="ZHENGJIAN_NUMBER" fieldname="ZHENGJIAN_NUMBER" onkeydown="EnterPress()" operation="like" >
						</td>
						
						<th width="5%" class="right-border bottom-border text-right">工作单位</th>
						<td width="15%">
							 <input class="span12" type="text" id="COMPANY_NAME" fieldname="COMPANY_NAME" onkeydown="EnterPress()" operation="like" >
						</td>
						
						<th width="3%" class="right-border bottom-border text-right">状态</th>
						<td class="right-border bottom-border" style="padding: 5px;width: 100px;">
							<select class="span12" id="STATUS" name="STATUS" fieldname="m.STATUS" operation="=">
							<option value="">-状态-</option>
							<option value="1">在工</option>
							<option value="0">空闲</option>
							<option value="-1">注销</option>
							</select>
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
	                		<th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;序号&nbsp;</th>
	                		<th rowspan="2" fieldname="MINGONG_UID" width="4%" tdalign="center" colindex=0 CustomFunction="doRandering" noprint="false">操作</th>
	                		<th id="MINGONG_NAME" fieldname="MINGONG_NAME" colindex=0 tdalign="center">&nbsp;姓名 &nbsp;</th>
	                		<th id="ZHENGJIAN_TYPE_NAME" fieldname="ZHENGJIAN_TYPE_NAME" colindex=0 tdalign="center">&nbsp;证件类型&nbsp;</th>
							<th id="ZHENGJIAN_NUMBER" fieldname="ZHENGJIAN_NUMBER" colindex=2 tdalign="center">&nbsp;证件号码&nbsp;</th>
							<th id="COMPANY_NAME" fieldname="COMPANY_NAME" colindex=0 tdalign="center" >&nbsp;工作单位&nbsp;</th>
							<th id="LIANXI_PHONE" fieldname="LIANXI_PHONE" colindex=0 tdalign="center" maxlength="10">&nbsp;联系电话 &nbsp;</th>
							<th id="ORIGIN" fieldname="ORIGIN" colindex=0 tdalign="center" >&nbsp;籍贯 &nbsp;</th>	
							<th id="STATUS" fieldname="STATUS" colindex=0 tdalign="center" CustomFunction="FormatSTATUS">&nbsp;状态 &nbsp;</th>		
	                	</tr>
	                </thead>
	              	<tbody></tbody>
	           </table>
	       </div>
	
    </div>
   </div>
  </div>
   <div align="center">
		<FORM name="frmPost" method="post" style="display:none"
			target="_blank">
			<!--系统保留定义区域-->
			<input type="hidden" name="queryXML" id="queryXML"> 
			<input type="hidden" name="txtXML" id="txtXML">
			<input type="hidden" name="resultXML" id="resultXML">
			<!--传递行数据用的隐藏域-->
			<input type="hidden" name="rowData">
		</FORM>
	</div>
</body>
<script>
</script>
</html>