<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>业务信息</title>
<%
 String PROJECTS_UID=request.getParameter("PROJECTS_UID");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/kindeditor/kindeditor.js"></script>
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/kindeditor/lang/zh_CN.js"></script>
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/kindeditor/customKE.js"></script>
<script type="text/javascript" charset="utf-8">
var pid = "<%=PROJECTS_UID %>";
var controllername= "${pageContext.request.contextPath }/ywlz/buSpYwlzController";

//初始化加载
$(document).ready(function(){
	init();
	
});
function init(){
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?queryByProjectsid&projects_uid="+pid,null,DT1);
}
function cishu(obj){
$("#jsdw").html(obj.COMPANY_NAME);
$("#xmmc").html(obj.PROJECTS_NAME);
if(obj.CISHU==""){
return "0";
}

}
function status(obj){
if(obj.STATUS=="-1"){
return "退回";
}else if(obj.STATUS=="1"){
return "已通过";
}else if(obj.STATUS=="0"){
return "流转中";
}else if(obj.STATUS=="40"){
return "未提交 ";
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
		<!-- 
			<h4 class="title">
				业务流程审批
				<span class="pull-right">  
					<button id="btnSh" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">审批</button> 
				 <button id="btnHf" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">核发</button>	    				      		   
				 </span>
			</h4> -->
			
			<form method="post" id="queryForm">
				<table class="B-table" width="100%">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<TR style="display: none;">
						<TD class="right-border bottom-border"></TD>
						<TD class="right-border bottom-border">
							<INPUT type="text" class="span12" kind="text" id="STATUS" fieldname="STATUS" value="40" operation="!="/>
							<INPUT type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>
							<INPUT type="text" class="span12" id="SPYW_UID" name="SPYW_UID"/>
							<INPUT type="text" class="span12" kind="text" id="STATUS_ID" fieldname="STATUS" value="0"  operation="="/>
						</TD>
					</TR>
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr>	
						<th width="5%" class="right-border bottom-border text-right">项目名称</th>
						<td width="20%" class="right-border bottom-border" id="xmmc">
						
						</td>
						<th width="5%" class="right-border bottom-border text-right">建设单位</th>
						<td width="25%" class="right-border bottom-border" id="jsdw">
						
						</td>
										
					</tr>
					
				</table>
			</form>
			<div style="height:5px;"> </div>	
			<div class="overFlowX">
	            <table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single"  pageNum="18">
	                <thead>
	                	<tr>
	                		<th  name="XH" id="_XH" style="width:10px" colindex=0 tdalign="center">&nbsp;#&nbsp;</th>
	                		<th fieldname="SPYWMC" colindex=1 tdalign="left"  style="width:10px" >流程</th>
	                		<th id="ywlzbz" fieldname="CISHU" colindex=2 tdalign="center" CustomFunction="cishu" style="width: 10px"  >申请次数</th>	                		
	                		<th fieldname="STATUS" colindex=3 tdalign="center"  CustomFunction="status" style="width: 50px"  >&nbsp;办理状态&nbsp;</th>
	                		<th  fieldname="PLAN_END_DATE" colindex=5 tdalign="center" style="width: 150px"   >&nbsp;开始时间&nbsp;</th>
							<th  fieldname="ACT_END_DATE" colindex=4 tdalign="center" style="width: 150px"   >&nbsp;完成时间&nbsp;</th>
	                	</tr>
	                </thead>
	              	<tbody></tbody>
	           </table>
	       </div>
	 	</div>
	</div>     
</div>
<div align="center">
	<FORM name="frmPost" method="post" style="display: none;" target="_blank" id="frmPost">
		<!--系统保留定义区域-->
		<input type="hidden" name="queryXML" id="queryXML"/>
		<input type="hidden" name="txtXML" id="txtXML"/>
		<input type="hidden" name="txtFilter" order="desc" fieldname="t.CREATED_DATE" id="txtFilter"/>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<input type="hidden" id="queryResult"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>	
		<input type="hidden" name="currYwid" id="currYwid"/>
		<input type="hidden" name="currYwmc" id="currYwmc"/>	
	</FORM>
</div>
</body>
</html>