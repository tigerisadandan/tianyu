<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.params.SysPara.SysParaConfigureVO"%>
<%@ page import="com.ccthanking.framework.params.ParaManager"%>
<%@ page import="com.ccthanking.framework.Constants"%>
<%@ include file="/jsp/framework/common/taglibs.jsp" %>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base />
<title>审批业务流转实例审批</title>
<%
String type=request.getParameter("type");
SysParaConfigureVO syspara  = (SysParaConfigureVO) ParaManager.getInstance().getSysParameter(Constants.getString(Constants.FILEUPLOADOLD_ROOT_JS, "FILEUPLOADOLD_ROOT_WNDJSJS"));
String fileRoot = syspara.getSysParaConfigureParavalue1();
String spyw_uid = request.getParameter("spyw_uid");
%>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/ywlz/css/default.css"> </LINK>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>	
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/kindeditor/plugins/code/prettify.css" />
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/kindeditor/kindeditor.js"></script>
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/kindeditor/lang/zh_CN.js"></script>

<script type="text/javascript" charset="utf-8">
var controllername = "${pageContext.request.contextPath}/rygl/wuGongController";
$(function() {
	setDate();
	doProQuery();

});

function doProQuery(){
	var tjlx = $("input[name='TJLB']:checked").val();
	$('#tjlx').val(tjlx);
	if(tjlx=='gz'){
		$('#GANGWEI').hide();
		$('#GONGZHONG').show();
	}else{
		$('#GONGZHONG').hide();
		$('#GANGWEI').show();
	}
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?wugongtj",data,DT1);
}

function setDate(){
	var nowdate = new Date();
	var nowy = nowdate.getFullYear();
	var nowm = nowdate.getMonth()+1;
	var nowd = nowdate.getDate();
	
	var bnowm = nowm;
	if(bnowm<10){
		bnowm = "0"+bnowm;
	}
	var datestr = nowy+"/"+bnowm+"/"+nowd;
	var enowm = nowm;
	if(nowm-6<=0){
		enowm = nowm+12-6;
		nowy = nowy-1;
	}else{
		enowm = nowm-6;
	}

	if(enowm<10){
		enowm = "0"+enowm;
	}
	
	var datestr2 = nowy+"/"+enowm+"/"+nowd;

	$( "#B_DATE" ).val(datestr2);
	$( "#E_DATE" ).val(datestr);
}
</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">  
	<div class="B-small-from-table-autoConcise">
     
	    <form method="post" id="queryForm">
				<table class="B-table" width="100%">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<TR style="display: none;">
						<TD class="right-border bottom-border"></TD>
						<TD class="right-border bottom-border">
							<INPUT type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>
							<input class="hidden" id="tjlx" type="text" fieldname="tjlx"  />
						</TD>
					</TR>
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr>
						<td class="right-border bottom-border" width="35%">
							 <input id="TJLB" type="radio" checked="checked" name="TJLB" value="gz" onclick="doProQuery();" checked="checked"  >各工种务工人次统计 <h id="dsid"></h>&nbsp;&nbsp;
      						 <input id="TJLB" type="radio" name="TJLB" value="gw" onclick="doProQuery();"> 各岗位务工人次统计 
 							<h id="ysid"></h>&nbsp;&nbsp;
						
						</td>
						<th width="3%" class="right-border bottom-border text-right">统计时间段</th>
						<td class="right-border bottom-border" width="25%">
							<input id="B_DATE" class="Wdate" type="text" onclick="WdatePicker({dateFmt:'yyyy/MM/dd'})" style="width:35%" field=""  name="B_DATE" fieldname="B_DATE" >
							~<input id="E_DATE" class="Wdate" type="text" onclick="WdatePicker({dateFmt:'yyyy/MM/dd'})" style="width:35%" field=""  name="E_DATE" fieldname="E_DATE" >
						</td>	
						
						<td class="text-left bottom-border">
	                        <button onclick="doProQuery();" id="btnQuery" class="btn btn-link"  type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-search"></i>查询</button>
           					<!-- <button id="btnClear" class="btn btn-link" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-trash"></i>清空</button>  -->
           					
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
	                		<th id="GONGZHONG" fieldname="GONGZHONG" colindex=0 tdalign="center" >&nbsp;工种名称 &nbsp;</th>
	                		<th id="GANGWEI" fieldname="GANGWEI" colindex=0 tdalign="center" >&nbsp;岗位名称 &nbsp;</th>
							<th fieldname="ZS" colindex=2 tdalign="center" maxlength="30">&nbsp;总用工数（人次）&nbsp;</th>
							<th fieldname="ZG" colindex=2 tdalign="center" maxlength="30" >&nbsp;当前在工人数（人）&nbsp;</th>
							<th fieldname="TG" colindex=2 tdalign="center" maxlength="30" >&nbsp;已退工人数（人次）  &nbsp;</th>				
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