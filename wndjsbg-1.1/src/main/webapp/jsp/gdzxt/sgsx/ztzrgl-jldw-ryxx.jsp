<!DOCTYPE html>
<%@page import="com.ccthanking.framework.Constants"%>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>监理单位管理人员信息</title>
<%
	String GONGCHENG_UID = request.getParameter("GONGCHENG_UID");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername = "${pageContext.request.contextPath}/sgzxt/getZxtIndexInformationController";
var GONGCHENG_UID ="<%=GONGCHENG_UID%>";
//页面初始化
$(function() {
	init();
	
	$("#btnClose").bind("click", function(){
		var a=$(this).manhuaDialog.close();
	});
		
}); 

//页面默认参数
function init(){
	doProQuery();
}

function doProQuery(){
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入	
	defaultJson.doQueryJsonList(controllername+"?queryJLRY",data,DT1);
}

//查看详细信息
function doRandering(obj){
	var showHtml = "";
	showHtml = "<a href='javascript:rowView(this);' title='查看'><i class='icon-search'></i></a></i>";
	return showHtml;
}

function rowView(index){
	if($("#DT1").getSelectedRowIndex()==-1)
 	{
		requireSelectedOneRow();
    	return;
 	}
	 var rowValue = $("#DT1").getSelectedRow();
	 var tempJson = convertJson.string2json1(rowValue);
	 var PERSON_UID = tempJson.PERSON_UID;
     $(window).manhuaDialog({"title":"人员详细信息查询","type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/sgsx/ztzrgl-ryxx-detail.jsp?PERSON_UID="+PERSON_UID+"&personType=JLRY","modal":"1"});
}
</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">
	
	<div class="B-small-from-table-autoConcise">
      <h4 class="title">监理单位管理人员信息
      		<span class="pull-right">
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
      		</span>
      </h4>
      <div style="height:5px;"></div>
      <div class="overFlowX">			   
	        	<table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single"  pageNum="18">
	                <thead>
	                	<tr>
	                		<th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;&nbsp;#&nbsp;&nbsp;</th>
	                		<th rowspan="2" fieldname="PERSON_UID" width="4%" tdalign="center" colindex=0 CustomFunction="doRandering" noprint="false">&nbsp;&nbsp;查看&nbsp;&nbsp;</th>
	                		<th id="PERSON_NAME" fieldname="PERSON_NAME" colindex=0 tdalign="center" > &nbsp;&nbsp;姓名 &nbsp;&nbsp;</th>
	                		<th id="" fieldname="" colindex=0 tdalign="center" >&nbsp;照片&nbsp;</th>
							<th id="" fieldname="" colindex=0 tdalign="center">&nbsp;信息卡&nbsp;</th>
							<th id="SHENFENZHENG" fieldname="SHENFENZHENG" colindex=0 tdalign="center">&nbsp;身份证号 &nbsp;</th>
							<th id="ZHENGSHU_CODE" fieldname="ZHENGSHU_CODE" colindex=0 tdalign="center">&nbsp;证书编号 &nbsp;</th>
							<th id="ZHUANYE" fieldname="ZHUANYE" colindex=0 tdalign="center">&nbsp;专业 &nbsp;</th>	
							<th id="NAMES" fieldname="NAMES" colindex=0 tdalign="center">&nbsp;人员类型 &nbsp;</th>
	                	</tr>
	                </thead>
	              	<tbody></tbody>
	           </table>
	       </div>
     <form method="post" id="queryForm">
				<table class="B-table" width="100%" id="condition">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr style="display: none;">
						<td class="right-border bottom-border"></TD>
						<td class="right-border bottom-border">
							<input type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>
							<input type="text" class="span12" kind="text" id="GONGCHENG_UID" fieldname="V.GONGCHENG_UID" operation="=" value="<%=GONGCHENG_UID%>"/>
							<input type="text" class="span12" kind="text" id="COM_TYPE" fieldname="V.COM_TYPE" operation="=" value="JL"/>
						</td>
					</tr>
				</table>
	</form>
    </div>
   </div>
  </div>

  <div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id = "queryXML">
         <input type="hidden" name="txtXML" id = "txtXML">
         <input type="hidden" name="txtFilter" order="ASC" fieldname="V.XUHAO" id="txtFilter"/>
         <input type="hidden" name="resultXML" id = "resultXML">
       	 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">		
 	</FORM>
 </div>
</body>
<script>
</script>
</html>