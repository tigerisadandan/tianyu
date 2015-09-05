<%@page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext"%>
<%@page import="com.ccthanking.framework.common.User" %>
<%@page import="com.ccthanking.framework.Globals"%>
<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>

<app:base/>
<title>打印工作计划首页</title>

<%
  String periods = request.getParameter("periods");
  User user = RestContext.getCurrentUser();
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
 var controllername = "${pageContext.request.contextPath }/nbgl/jhgl/workPlanController";
 var periods = "<%=periods%>";
 var uid = "<%= user.getUserSN()%>";
//页面初始化
$(function() {
	 init();
	//关闭
    $("#btnClose").bind("click", function(){
		var a=$(this).manhuaDialog.close();
	});
	//按钮绑定(打印按钮 )
	$("#btnQuery").click(function(){
	   	window.open("${pageContext.request.contextPath }/nbgl/jhgl/workPlanController?print&timeStr="+periods,"打印登记表");
	   
	});
	
});
/*
     画表格 
*/
function init(){
 $.ajax({
		url:controllername+"?querydata&getTime="+periods,
		type:"post",
		dataType : "json",
		success : function(response){
			var res = dealSpecialCharactor(response.msg);
			var resultmsgobj = convertJson.string2json1(res);
			var thtml = "";
		if(resultmsgobj!=null && resultmsgobj !=''){//说明有数据

          for(var i=0;i<=4;i++){ //根据当前日期  计算下周一日期   循环下周的日期
			    var data=resultmsgobj.response.data[i];
				var day = data.DAYS;//
				var jdyzAm = data.JDYZ_AM;
				var jdyzPm = data.JDYZ_PM;
				var jdezAm = data.JDEZ_AM;
				var jdezPm = data.JDEZ_PM;
				var zhkAm = data.ZHK_AM;
				var zhkPm = data.ZHK_PM;
				var jdkAm = data.JDK_AM;
				var jdkPm = data.JDK_PM;
				var tablehtml = "<tr>"+
                     "<th rowspan=\"2\" fieldname=\"DAYS\" width=\"15%\" tdalign=\"left\">"+day+"</th>"+
           	     	 "<th tdalign=\"left\" width=\"5%\" >上午</th>"+
           	     	 "<th fieldname=\"JDYZ_AM\" colindex=20 tdalign=\"left\" width=\"15%\">"+jdyzAm+"</th>"+
                	 "<th fieldname=\"JDEZ_AM\" colindex=20 tdalign=\"left\" width=\"15%\">"+jdezAm+"</th>"+
					 "<th fieldname=\"ZHK_AM\" colindex=20 tdalign=\"left\"  width=\"15%\">"+jdkAm+"</th>"+
					 "<th fieldname=\"JDK_AM\" colindex=20  tdalign=\"left\" width=\"15%\">"+zhkAm+"</th>"+
                     "</tr><tr>"+
                     "<th tdalign=\"left\" width=\"5%\" >下午</th>"+
                     "<th fieldname=\"JDYZ_PM\" colindex=20 tdalign=\"left\" width=\"15%\">"+jdyzPm+"</th>"+
                	 "<th fieldname=\"JDEZ_PM\" colindex=20 tdalign=\"left\" width=\"15%\">"+jdezPm+"</th>"+
					 "<th fieldname=\"ZHK_PM\" colindex=20 tdalign=\"left\"  width=\"15%\">"+jdkPm+"</th>"+
					 "<th fieldname=\"JDK_PM\" colindex=20  tdalign=\"left\" width=\"15%\">"+zhkPm+"</th>"+
                     "</tr>";
				     $("#DT1").append(tablehtml);
			}
	      }else{
	          alert("没有 查询到数据");
	      }
	       }
	      });

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
						  <INPUT type="text" class="span12" kind="text" id="selectWeek" fieldname="DAYS" value="" operation="=" />
						</TD>
					</TR>
				<!--可以再此处加入hidden域作为过滤条件 -->
			     <tr>
			      <td width="70%">
			        <table class="B-table" width="100%" id="find">
			      <tr>
				   <td class="right-border bottom-border text-right" width="5%" rowspan="2" style="background-image: none; background-position: initial initial; background-repeat: initial initial;">
	                  <button id="btnQuery" class="btn btn-link"  type="button">
	                  <i class="icon-print" href="javascript:void();" ></i>打印</button>
	                  <!-- <i class="icon-search" "></i>打印</button> -->
	                  <button id="btnClose" class="btn btn-link" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
           	      </td>
           	    </tr>
           	       </table>
           	     </td>
			   </tr>
			   </table>
			</form>
			<div style="height:5px;"> </div>	
			<div class="overFlowX">	
	            <table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single"  pageNum="5" noPage="true">
	                <thead>
	                    <tr>
	                      <th rowspan="2"  colspan="2" width="25%" tdalign="center">&nbsp;时间安排&nbsp;</th>
	                	  <th fieldname="" colindex=10 tdalign="center"  colspan="5"  width="15%">&nbsp;工作内容&nbsp;</th>
	                    </tr>
	                	<tr>
		                	<th  tdalign="center" width="0%" style="display:none" >&nbsp;&nbsp;</th>
	                		<th  colindex=10 tdalign="center"   width="15%">&nbsp;监督一组&nbsp;</th>
	                		<th  colindex=10 tdalign="center" width="15%">&nbsp;监督二组&nbsp;</th>
							<th  colindex=10 tdalign="center" width="15%"  >&nbsp;监督管理科&nbsp;</th>
							<th  colindex=10  tdalign="center" width="15%">&nbsp;综合管理科&nbsp;</th>
	                	</tr>
	                
	                </thead>
	              	<tbody></tbody>
	           </table>
	       </div>
	 	</div>
	</div>     
</div>
</body>
</html>