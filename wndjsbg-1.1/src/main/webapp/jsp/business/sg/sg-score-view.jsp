<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>信用综合评价分</title>
<%
	String id = (String)request.getAttribute("id");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/sgenter/sgScoreController/";
//页面初始化
$(function() {
	init();
});
function init(){
	$("#QID").val("<%=id%>");
	setFormValues("scoreInfo");
	setFormValues("jbInfo");
	setFormValues("xmInfo");
	setFormValues("jxInfo");
}
//修改情况下,读取表单内容
function setFormValues(type){
	$.ajax({
		url : controllername+"tongjiScore?id="+$("#QID").val()+"&type="+type,
		data : {},
		cache : false,
		async :	false,
		dataType : "text",  
		type : 'post',
		success : function(response) {
			if(type=="scoreInfo"){
				doScoreInfo(response);
			}else if(type=="jbInfo"){
				doJbScore(response);
			}else if(type=="xmInfo"){
				doXmScore(response);
			}else if(type=="jxInfo"){
				doJxScore(response);
			}
		}
	});
}
function doScoreInfo(data){
	var item = convertJson.string2json1(data);
	var obj = convertJson.string2json1(item)[0];
	$("#COMPANY_NAME").html(obj.COMPANY_NAME);
	$("#DENGLU_CODE").html(obj.DENGLU_CODE);
	$("#SCORE").html(obj.SCORE);
	$("#V_DATE").html(obj.BEGIN_DATE+"至"+obj.END_DATE);
	$("#DENGLU_CODE").html(obj.DENGLU_CODE);
	$("#RC_SCORE").html(obj.RC_SCORE);
	$("#JB_SCORE").html(obj.JB_SCORE);
}
function doJbScore(data){
	var item = convertJson.string2json1(data);
	var arr = convertJson.string2json1(item);
	var sumSc = 0;
	if(arr.length>0&&arr[0]!=null){
		$("#ZIZHI_NAME").html(arr[0].SCORE_NAME);
		$("#ZIZHI_DATE").html(arr[0].VALID_DATE);
		if(arr[0].SCORE!=""){
			sumSc += parseInt(arr[0].SCORE);
		}
	}
	if(arr.length>1&&arr[1]!=null){
		$("#ANQUAN_NAME").html(arr[1].SCORE_NAME);
		$("#ANQUAN_DATE").html(arr[1].VALID_DATE);
		if(arr[1].SCORE!=""){
			sumSc += parseInt(arr[1].SCORE);
		}
	}
	$("#JB_SUM").html(sumSc);
}
function doXmScore(data){
	var item = convertJson.string2json1(data);
	var arr = convertJson.string2json1(item);
	var showHtml = "";
	var sumSc = 0;
	$(arr).each(function(i,item){
		showHtml += "<tr>";
		if(i==0){
			showHtml += "<th class='right-border bottom-border text-center' rowspan='"+arr.length+"'>承接<br/>工程分<br/>(8分)</th>";
		}
		showHtml += "<td>工程"+(i+1)+"</td>";
		showHtml += "<td>"+item.SCORE_NAME+"</td>";
		showHtml += "<td>"+item.VALID_DATE+"</td>";
		if(i==0){
			showHtml += "<td rowspan='"+arr.length+"'>&&sum&&</td>";
		}
		showHtml += "</tr>"
		if(item.SCORE!=""){
			sumSc += parseInt(item.SCORE);
		}
	})
	showHtml = showHtml.replace("&&sum&&",sumSc);
	$("#xmBefore").before(showHtml);
}
function doJxScore(data){
	var item = convertJson.string2json1(data);
	var arr = convertJson.string2json1(item);
	var showHtml = "";
	var sumSc = 0;
	$(arr).each(function(i,item){
		showHtml += "<tr>";
		if(i==0){
			showHtml += "<th class='right-border bottom-border text-center' rowspan='"+arr.length+"'>表彰<br/>奖励分<br/>(12分)</th>";
		}
		showHtml += "<td>"+item.CR_LEVEL+"</td>";
		showHtml += "<td>"+item.SCORE_NAME+"</td>";
		showHtml += "<td>"+item.VALID_DATE+"</td>";
		if(i==0){
			showHtml += "<td rowspan='"+arr.length+"'>&&sum&&</td>";
		}
		showHtml += "</tr>"
		if(item.SCORE!=""){
			sumSc += parseInt(item.SCORE);
		}
	})
	showHtml = showHtml.replace("&&sum&&",sumSc);
	$("#JxBefore").before(showHtml);
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
				        	<input id="QID" class="span12" name="SG_SCORE_UID" fieldname="t.SG_SCORE_UID" type="text" operation="="/>
				        </TD>
						<TD class="right-border bottom-border"></TD>
			        </TR>
				</table>
			</form>
		</div>
		<div style="height:5px;"></div>
		<div class="B-small-from-table-autoConcise">
			<form method="post" id="sgScoreForm"  >
	     	 	<h4 class="title" id="xmxx_title">
	     	 		<span class="pull-right" id="btnSave_span">
			      		<button id="btnClear_Bins" class="btn" onclick="window.close();" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
					</span>
	     	 	</h4>
				<table class="B-table" width="100%" id="xmxx_card">
				<input id="SG_SCORE_UID" class="span12"  name="SGBB_UID" fieldname="SG_SCORE_UID" type="hidden" />
			        <tr>
						<th width="8%" class="right-border bottom-border text-center">单位名称</th>
			       	 	<td class="bottom-border right-border" width="50%" colspan="2" id="COMPANY_NAME">
			         		
			       	 	</td>
			       	 	<th width="30%" class="right-border bottom-border text-center">登录代码</th>
			       	 	<td class="bottom-border right-border" width="23%"  id="DENGLU_CODE">
			         		
			       	 	</td>
			       	 </tr>
			       	 <tr>
			         	<th width="8%" class="right-border bottom-border text-center">得分项目</th>
			       		<th width="50%" class="right-border bottom-border text-center" colspan="2">评价项目</th>
			       		<th width="8%" class="right-border bottom-border text-center">有效期</th>
			       		<th width="23%" class="right-border bottom-border text-center">得分</th>
			        </tr>
			       
					 <tr id="jcList">
						<th class="right-border bottom-border text-center" rowspan="2">基本分<br/>(20分)</th>
						<td>资质等级</td>
						<td width="30%" id="ZIZHI_NAME"></td>
						<td id="ZIZHI_DATE"></td>
						<td rowspan="2" id="JB_SUM"></td>
					</tr>
					 <tr id="jcList">
						<td>安全生产许可证</td>
						<td id="ANQUAN_NAME"></td>
						<td id="ANQUAN_DATE"></td>
					</tr>
					
					<tr id="xmBefore"></tr>
					<tr id="JxBefore"></tr>
			        <tr>
				        <th width="8%" class="right-border bottom-border text-center" colspan="4">基本信息得分</th>
				        <td class="bottom-border right-border" id="JB_SCORE">
				        	
				        </td>
			        </tr>
			         <tr>
				        <th width="8%" class="right-border bottom-border text-center" colspan="4">日常考核得分</th>
				        <td class="bottom-border right-border" id="RC_SCORE">
				        	
				        </td>
			        </tr>
			        <tr>
				        <th width="8%" class="right-border bottom-border text-center" >信用综合评价分</th>
				        <td class="bottom-border right-border" id="SCORE">
				        	
				        </td>
				        <th width="8%" class="right-border bottom-border text-center">有效期</th>
				        <td class="bottom-border right-border" colspan="2" id="V_DATE">
				        	
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
        <input type="hidden" name="txtFilter"  order="desc" fieldname = "t.CREATED_DATE" id = "txtFilter">
        <input type="hidden" name="resultXML" id = "resultXML">
        <input type="hidden" name="currRy" id = "currRy">
        <!--传递行数据用的隐藏域-->
        <input type="hidden" name="rowData">
	</FORM>
</div>
</body>
<script>
</script>
</html>