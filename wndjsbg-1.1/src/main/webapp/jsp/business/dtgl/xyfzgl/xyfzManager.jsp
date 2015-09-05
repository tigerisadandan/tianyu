<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<%

%>
<title></title>

<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/ywlz/css/default.css"> </LINK>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/base.css" type="text/css" />
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
  <div class="row-fluid" align="center">
  <div class="B-small-from-table-autoConcise">
  <form method="post" id="queryForm">
	<table class="B-table" width="100%" id="condition">
		<!--可以再此处加入hidden域作为过滤条件 -->
		<TR style="display: none;">
			<TD class="right-border bottom-border"></TD>
			<TD class="right-border bottom-border">
					<INPUT type="text" class="span12" kind="text" name="ORGANIZE_UID" id="" fieldname=""  operation="="/>
			</TD>
		</TR>
		<!--可以再此处加入hidden域作为过滤条件 -->
		<tr>
			<td class="right-border bottom-border" width="15%">
				 <input type=radio id="qiyeRadio" value="qiye" name="status" onclick="javascript:checkStatus(this.value)" checked="checked"  />企业&nbsp;&nbsp;
   				 <input type=radio id="gerenRadio" value="geren" name="status" onclick="javascript:checkStatus(this.value)" />个人&nbsp;&nbsp;
   			</td>
   			<th width="4%" class="right-border bottom-border text-right">类型</th>
   			<td class="right-border bottom-border" width="10%">
				<select class="span12" id="qiyeSelect" onchange="changeSelect()" >
					<option value="JS">建设单位</option>
					<option value="SG">施工企业</option>
					<option value="JL">监理单位</option>
				</select>
				<select class="span12" id="renyuanSelect" onchange="changeSelect()" style="display: none;">
					<option value="SGRY">施工人员</option>
					<option value="JLRY">监理人员</option>
				</select>
   			</td>
			<th width="6%" class="right-border bottom-border text-right" id="queryType">企业名称</th>
			<td class="right-border bottom-border" width="20%">
				<input class="span12" type="text" id="name" name="name" fieldname="COMPANY_NAME" onkeydown="queryOperator(event);"  operation="like" >
			</td>	
			<th width="6%" class="right-border bottom-border text-right">信用分值</th>
			<td class="right-border bottom-border" width="20%">
				<input class="span3" type="text" id="BSCORE" name="SCORE" fieldname="SCORE" onkeydown="queryOperator(event);"  value="0" operation=">=" >-
				<input class="span3" type="text" id="ESCORE" name="SCORE" fieldname="SCORE"  onkeydown="queryOperator(event);" operation="<=" >
			</td>
			<td class="text-left bottom-border text-right">
                <button id="btnQuery" class="btn btn-link"  type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-search"></i>查询</button>
            	<button id="btnClear" class="btn btn-link" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-trash"></i>清空</button>
            </td>          	  
		</tr>
	</table>
   </form>
   <div style="height:5px;"> </div>	
	<div ><!-- class="overFlowX" -->
	           <table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single"  pageNum="13" noheight="true">
	               <thead>
		               	<tr>
		               		<th  name="XH" id="_XH" style="width:10px" colindex=0 tdalign="center">&nbsp;#&nbsp;</th>
		               		<th fieldname="COMPANY_UID" colindex=1 width="4%" tdalign="center" CustomFunction="doRandering">查看	</th>
		               		<th fieldname="COMPANY_NAME" colindex=2 tdalign="center">&nbsp;企业名称&nbsp;</th>
		               		<th fieldname="COMPANY_CODE" colindex=3 tdalign="center">&nbsp;组织机构代码&nbsp;</th>
		               		<th fieldname="ADDRESS" colindex=4 tdalign="center">&nbsp;企业地址&nbsp;</th>
		               		<th fieldname="FZR" colindex=5 tdalign="center">&nbsp;联系人&nbsp;</th>
		               		<th fieldname="FZR_MOBILE" colindex=6 tdalign="center">&nbsp;联系人电话&nbsp;</th>
		               		<th fieldname="SCORE" colindex=7 tdalign="center">&nbsp;当前分数&nbsp;</th>
		               	</tr>
	              </thead>
	            <tbody></tbody>
	         </table>
          <table width="100%" class="table-hover table-activeTd B-table" id="DT2" type="single"  pageNum="13" noheight="true" style="display:none;" >
               <thead>
	               	<tr>
	               		<th  name="XH" id="_XH" style="width:10px" colindex=0 tdalign="center">&nbsp;#&nbsp;</th>
	               		<th fieldname="PERSON_UID" colindex=1 width="4%" tdalign="center" CustomFunction="doRandering1" >查看	</th>
	               		<th fieldname="PERSON_NAME" colindex=2 tdalign="center">&nbsp;姓名&nbsp;</th>
	               		<th fieldname="COMPANY_NAME" colindex=2 tdalign="center">&nbsp;企业名称&nbsp;</th>
	               		<th fieldname="SHENFENZHENG" colindex=2 tdalign="center">&nbsp;身份证号&nbsp;</th>
	               		<th fieldname="PHONE" colindex=3 tdalign="center">&nbsp;联系电话&nbsp;</th>
	               		<th fieldname="SCORE" colindex=4 tdalign="center">&nbsp;当前分数&nbsp;</th>
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
<%--		<input type="hidden" name="txtFilter" order="desc" fieldname="s.CREATED_DATE" id="txtFilter"/>--%>
<%--	<input type="hidden" name="txtFilter" order="desc" fieldname="ID" id="txtFilter"/>--%>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
<script type="text/javascript">
var controllername = "${pageContext.request.contextPath }/dtgl/xyfzController";
var radioValue = "qiye";
var selectValue = "JS";
//点击保存按钮
$(function() {
	//查询按钮
	$("#btnQuery").click(function(){
		//判断输入的是否是数字
		if(isNaN($("#BSCORE").val())){
			xAlert("提示信息","信用分值输入格式不正确！");
			return;
		}
		if(isNaN($("#ESCORE").val())){
			xAlert("提示信息","信用分值输入格式不正确！");
			return;
		}
		init();
	});
	//清空按钮点击事件
    $("#btnClear").click(function() {
        $("#condition").clearFormResult();
        $("#BSCORE").val(0);
       	 init();
    });
	
	//弹出人员信息列表
	$("#queryBtn").click(function(){
		$(window).manhuaDialog({"title":"组织机构管理>用户列表","type":"text","content":"${pageContext.request.contextPath }/jsp/business/dtgl/xtgl/showUserList.jsp","modal":"2"});
	});
});

$(document).ready(function() {
	clearTitle();
	init();
});

function init(){
	if(radioValue == 'qiye'){
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"?query&type="+radioValue+"&qiyeType="+selectValue,data,DT1);
	}else if(radioValue == 'geren'){
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT2);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"?query&type="+radioValue+"&qiyeType="+selectValue,data,DT2);
	}
	
}
//选中个人/企业 单选按钮
function checkStatus(type){
	radioValue = type;
	if(type=='qiye'){//企业
		selectValue = 'JS';//默认选中的option
		$("#qiyeSelect").prop("selectedIndex",0);
		$("#qiyeSelect").show();
		$("#renyuanSelect").hide();//改变下拉框
		$("#queryType").text("企业名称");
		$("#name").attr("fieldname","COMPANY_NAME");
		$("#name").val("");
		 $("#DT2").hide();
		$("#DT1").show();
		$("#page_DT2").hide();
	}else if(type=='geren'){//个人
		selectValue = 'SGRY';//默认选中的option
		$("#renyuanSelect").prop("selectedIndex",0);
		$("#qiyeSelect").hide();
		$("#renyuanSelect").show();
		$("#queryType").text("姓名");
		$("#name").val("");
		$("#name").attr("fieldname","PERSON_NAME");
		$("#DT1").hide();
		$("#DT2").show();
		$("#page_DT1").hide();
	}
	init();
}
//选中下拉菜单
function changeSelect(){
	if(radioValue=='qiye'){//企业
		selectValue = $("#qiyeSelect option:selected").val();
	}else if(radioValue=='geren'){//个人
		selectValue = $("#renyuanSelect option:selected").val();
	}
	init();	
}
//去掉下拉框/单选框自动加的title属性
function clearTitle(){
	$("#qiyeRadio").removeAttr("title");
	$("#gerenRadio").removeAttr("title");
	$("#qiyeSelect").removeAttr("title");
	$("#renyuanSelect").removeAttr("title");
}
//点击回车，触发查询事件
function queryOperator(e){
	if(e.keyCode == 13){
		$("#btnQuery").click();
	}
}
//查看企业详细信息
function doRandering(obj){
	var showHtml = "";
	showHtml = "<a href='javascript:rowView(this);' class='btn btn-link' title='查看'><i class='icon-file'></i></a>";
	return showHtml;
}	
//查看人员详细信息
function doRandering1(obj){
	var showHtml = "";
	showHtml = "<a href='javascript:rowView1(this);' class='btn btn-link' title='查看'><i class='icon-file'></i></a>";
	return showHtml;
}
function rowView(){
	if($("#DT1").getSelectedRowIndex()==-1)
 	{
		requireSelectedOneRow();
    	return
 	}
	 var rowValue = $("#DT1").getSelectedRow();
     var tempJson = convertJson.string2json1(rowValue);
     var COMPANY_UID = tempJson.COMPANY_UID;
	// $(window).manhuaDialog({"type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/index/qiye-xypf.jsp?COMPANY_UID="+COMPANY_UID,"modal":"1"});
	window.open("${pageContext.request.contextPath }/jsp/gdzxt/index/qiye-xypf.jsp?COMPANY_UID="+COMPANY_UID+"&type="+selectValue);
	
}
function rowView1(){
if($("#DT2").getSelectedRowIndex()==-1)
{
	requireSelectedOneRow();
  	return
}
 var rowValue = $("#DT2").getSelectedRow();
    var tempJson = convertJson.string2json1(rowValue);
    var PERSON_UID = tempJson.PERSON_UID;
 	//$(window).manhuaDialog({"title":"查看人员信息","type":"text","content":"${pageContext.request.contextPath }/jsp/business/dtgl/rygl/gdry-detail.jsp?PERSON_UID="+PERSON_UID,"modal":"1"});
	window.open("${pageContext.request.contextPath }/jsp/gdzxt/index/geren-xypf.jsp?person_uid="+PERSON_UID+"&type="+selectValue);
}
</script>

</body>
</html>