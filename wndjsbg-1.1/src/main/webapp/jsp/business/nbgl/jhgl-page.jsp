<!DOCTYPE html>
<%@page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext"%>
<%@page import="com.ccthanking.framework.common.User" %>
<%@page import="java.text.*"%>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<%
	User user = RestContext.getCurrentUser();
 %>
<app:base/>
<title>计划管理首页</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/date/moment.min.js"></script>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/ywlz/css/default.css"> </LINK>
<LINK type="text/css" rel="stylesheet" href="/wndjsbg/css/style.css"> </LINK>
<link type="text/css" rel="stylesheet" href="/wndjsbg/css/style-responsive.css">
<link type="text/css" rel="stylesheet" href="/wndjsbg/css/bootstrap.css">
<link type="text/css" rel="stylesheet" href="/wndjsbg/css/perfect-scrollbar.min.css" id="style_color">
<script type="text/javascript" charset="utf-8">

  //获取 请求路径  对应后台的RequestMapping
	var controllername = "${pageContext.request.contextPath }/nbgl/jhgl/workPlanController";
	var id = "<%= user.getUserSN()%>";
	$(function(){
		showButton(id);
		init();
		//点击查询按钮
		$("#btnQuery").click(function(){ 
			enterSumbit();
		});
	    $("#btnAdd").click(function() {//添加 下周计划按钮
	        $.ajax({
				url:controllername+"?queryExist&user_uid="+"<%= user.getUserSN()%>",
				type:"post",
				dataType : "json",
			    success : function(response){
					var res = dealSpecialCharactor(response.msg);
					var resultmsgobj = convertJson.string2json1(res);
				if(resultmsgobj!=null&&resultmsgobj!=''){
						var datslist=resultmsgobj.response.data[0];
					    number = datslist.P_ORGANIZE_UID;//获取员工部门id
		                $(window).manhuaDialog({"title":"计划管理>添加下周计划 ","type":"text","content":"${pageContext.request.contextPath }/jsp/business/nbgl/jhgl-add.jsp?pid="+number,"modal":"2"});
				}else{
				
				}    
			}
			});
			
	    });	
		$("#btnAddYj").click(function() {//点击打印周计划按钮
		     var obj = document.getElementById("week");
			 var txt = obj.options[obj.selectedIndex].text;
			 /** 获取分集信息 **/
			 var re = /[\u4000-\uFFFF]/g;
			 var periods = txt.replace(re, '').split('~');
			 var week = 0;
			 if(periods[0].length==13){
			     week = periods[0].substr(0,2);
			 }else{
			     week = periods[0].substr(0,1);
			 }
			  $(window).manhuaDialog({"title":"无锡新区安监站第"+week+"周工作报表 ","type":"text","content":"${pageContext.request.contextPath }/jsp/business/nbgl/jhgl-print.jsp?periods="+periods,"modal":"2"});
		
	    });
	});
	 
	function init(){
		var d = new Date();
		for(var i = 5;i>0;i--){
			var vyear = d.getFullYear()-4+i;//2016年
			$("#nianfen").append("<option value='"+vyear+"'>"+vyear+"年</option>");
		}
		 //获取当前年的整年
         var timeYear = $("#nianfen").val(new Date().getFullYear());
         //删除第一个请选择选项
		 $("#week >option").eq(0).remove(); 
		 var thisweek =  moment(new Date()).format("W");
		 $("#week").val(thisweek);  //默认选中当前日期所在的周
	     var nextweek = $("#week option[value='"+thisweek+1+"']").text();
		 
		 var obj = document.getElementById("week");
		 var txt = obj.options[obj.selectedIndex].text;
		 /** 获取分集信息 **/
		 var re = /[\u4000-\uFFFF]/g;
		 var periods = txt.replace(re, '').split('~');
		 workPlanList(periods);
			
	};

	//获取的是 选择的年份
	function checkNianfen(id){
		var vyear = ($("#"+id).val());
		findTime(vyear);
	}
  	
  	//....................
  	  function getUpdate01(obj){
	  	  $("#org_name01").html(obj.ORG_NAME);
		  $("#update_date01").html(obj.UPDATE_DATE);
		  var sometime = $("#update_date01");
		  contentWrite(sometime);
	      return obj.PLAN_DATE;
  	}
	  // 2
	  function getUpdate02(obj){
		 //获取到的 更新日期json  "2015/06/16\b13:45"
		  $("#org_name02").html(obj.ORG_NAME);
	      $("#update_date02").html(obj.UPDATE_DATE);
	      var sometime = $("#update_date02");
		  contentWrite(sometime);
		  return obj.PLAN_DATE;
		  
	  }
	  //  3
	  function getUpdate03(obj){
		  $("#org_name03").html(obj.ORG_NAME);
		  $("#update_date03").html(obj.UPDATE_DATE);
		  var sometime = $("#update_date03");
		  contentWrite(sometime);
		  return obj.PLAN_DATE;
	  }
	  //4
	  function getUpdate04(obj){
		  $("#org_name04").html(obj.ORG_NAME);
		  $("#update_date04").html(obj.UPDATE_DATE);
		  var sometime = $("#update_date04");
		  contentWrite(sometime);
		  return obj.PLAN_DATE;
	  }
	 
	  /**
	    时间显示设置
	  **/
	  function contentWrite(timeType){
		  var sometime = timeType.text();
		    var re = /[\u4000-\uFFFF]/g;
		    var periods = sometime.replace(re, '');
		    var str = periods.substr(0,10);
		    var str1 = periods.substr(11,16);
		    var somestr =  str+" ";
		    return timeType.html(somestr+str1);
	  }
	  /**
	     添加下周计划 按钮的显示 隐藏
	  **/
	   function showButton(id){
	  
		  $.ajax({
				url:controllername+"?queryExist&user_uid="+"<%= user.getUserSN()%>",
				type:"post",
				dataType : "json",
			success : function(response){
					var res = dealSpecialCharactor(response.msg);
					var resultmsgobj = convertJson.string2json1(res);
				
					var thtml = "";
				if(resultmsgobj!=null&&resultmsgobj!=''){
						var datslist=resultmsgobj.response.data[0];
					    var number = datslist.P_ORGANIZE_UID;
					    if(number==11 || number==16 || number==101|| number==52){
					         $("#btnAdd").show();
					    }else{
					         $("#btnAdd").hide();
					    }
					}else{
						alert("您不属于任何部门!");
					}
			
						
				}
			});
		  
	 }
	  
	  
	  
	 /**
	   根据年份查询 周
	  */
	 function findTime(nianfen){
		 var timeYear = nianfen;
		  $.ajax({
				url:"${pageContext.request.contextPath }/nbgl/jhgl/workPlanController?query01&nianfen="+nianfen,
				type:"post",
				dataType : "json",
				success : function(response){
					var res = dealSpecialCharactor(response.msg);
					var resultmsgobj = convertJson.string2json1(res);
				
					var thtml = "";
				if(resultmsgobj!=null&&resultmsgobj!=''){
						var datslist=resultmsgobj.response.data;
						$(datslist).each(function(){
							 thtml += "<option value="+this.THE_WEEK+">"+this.WEEK+"</option>";
						     $("#week >option").eq(0).remove(); //删除第一个请选择选项
						     
					});
					}else{
						thtml += "<tr><td colspan=\"4\" align=\"center\">没有数据！</td></tr>";
					}
					        $("#week").append(thtml);	
				}
			});
		  
	 }
	 
		/**
		   获取日期请选择项 下拉框 中的值(点击)
		**/
	     function CheckTime(id){
	  	     var obj = document.getElementById("week");
	  	     var txt = obj.options[obj.selectedIndex].text;
	  		
	  		 var number = ($("#"+id).val());
	  		 /**获取分集信息 **/
	  		 var re = /[\u4000-\uFFFF]/g;
	  	     var periods = txt.replace(re, '').split('~');//存到数组中
	    	 return  workPlanList(periods);
		 };
		 
	
		     function Check(id){
				     var periods = getSelectedTime();
				     var number = ($("#"+id).val());
				  if(number==''){//所有部门
				         $("#Tb03 div").show();
						 $("#Tb01 div").show();
						 $("#Tb02 div").show();
						 $("#Tb04 div").show();
						 var datatxt  = $("#PLAN_CONTENT_AM").val();
						 $("#PLAN_CONTENT_AM02").val(datatxt);
						 $("#PLAN_CONTENT_AM03").val(datatxt);
						 $("#PLAN_CONTENT_AM04").val(datatxt);
				         workPlanList(periods);
						
					 }
					 
					 if(number==52){//综合管理
						 $("#Tb03 div").show();
						 $("#Tb01 div").hide();
						 $("#Tb02 div").hide();
						 $("#Tb04 div").hide();
						 var datatxt  = $("#PLAN_CONTENT_AM").val();
						 $("#PLAN_CONTENT_AM03").val(datatxt);
						 $("#selectWeek03").val(periods);
						 var data3 = combineQuery.getQueryCombineData(queryForm03,frmPost,DT5);
						 defaultJson.doQueryJsonList(controllername+"?query",data3,DT5);
						
				     }
					 if(number==101){//监督管理
						 $("#Tb01 div").hide();
						 $("#Tb02 div").hide();
						 $("#Tb03 div").hide();
						 $("#Tb04 div").show();
						 var datatxt  = $("#PLAN_CONTENT_AM").val();
						 $("#PLAN_CONTENT_AM04").val(datatxt);
						 $("#selectWeek04").val(periods);
						 var data4 = combineQuery.getQueryCombineData(queryForm04,frmPost,DT7);
						 defaultJson.doQueryJsonList(controllername+"?query",data4,DT7);
						
				    }
						
					 if(number==11){//监督一组
						 $("#Tb01 div").show();
						 $("#Tb02 div").hide();
						 $("#Tb03 div").hide();
						 $("#Tb04 div").hide();
						 $("#selectWeek").val(periods);
						 var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
						 defaultJson.doQueryJsonList(controllername+"?query",data,DT1);
						 
					 }
					 if(number==16){//监督二组
						 $("#Tb01 div").hide();
						 $("#Tb03 div").hide();
						 $("#Tb04 div").hide();
						 $("#Tb02 div").show();
						 var datatxt  = $("#PLAN_CONTENT_AM").val();
						 $("#PLAN_CONTENT_AM02").val(datatxt);
						 $("#selectWeek02").val(periods);
						 var data2 = combineQuery.getQueryCombineData(queryForm02,frmPost,DT3);
						 defaultJson.doQueryJsonList(controllername+"?query",data2,DT3);
						 
					 }
					
		 }
		 /*
		    打包数据发请求
		 */
		 function workPlanList(periods){ 
				 $("#selectWeek").val(periods);
				 var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
				 defaultJson.doQueryJsonList(controllername+"?query",data,DT1);
				
				 $("#selectWeek02").val(periods);
				 var data2 = combineQuery.getQueryCombineData(queryForm02,frmPost,DT3);
				 defaultJson.doQueryJsonList(controllername+"?query",data2,DT3);
				
				 $("#selectWeek03").val(periods);
				 var data3 = combineQuery.getQueryCombineData(queryForm03,frmPost,DT5);
				 defaultJson.doQueryJsonList(controllername+"?query",data3,DT5);
		      
				 $("#selectWeek04").val(periods);
				 var data4 = combineQuery.getQueryCombineData(queryForm04,frmPost,DT7);
				 defaultJson.doQueryJsonList(controllername+"?query",data4,DT7);
				 
			
		    } 
		 
		 /** 每次 都会 获取 到 点击时间**/
		  function getSelectedTime(){
	    	  var obj = document.getElementById("week");
	  		  var txt = obj.options[obj.selectedIndex].text;
	  		
	  		  /**获取分集信息 **/
	  		  var re = /[\u4000-\uFFFF]/g;
	  		  var periods = txt.replace(re, '').split('~');//存到数组中
	  		  return periods;
	     }
</script>  
<style type="text/css">
</style>
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="B-small-from-table-autoConcise" id="Tb01">
			<form method="post" id="queryForm" >
				<table class="B-table" width="100%" >
					<!--可以再此处加入hidden域作为过滤条件 -->
					<TR style="display: none;">
						<TD class="right-border bottom-border"></TD>
						 <TD class="right-border bottom-border">
						 <!-- 对应 服务器端 的 条件  -->
						    <INPUT type="text" class="span12" kind="text" id="selectWeek" fieldname="PLAN_DATE" value="" operation="=" />
						    <INPUT type="text" class="span12" kind="text" id="organize" fieldname="g.ORGANIZE_UID" value="11" operation="=" />
						      <input class="span12" type="text" kind="text" id="PLAN_CONTENT_PM" fieldname="PLAN_CONTENT_PM" value="" operation="like" >
							<INPUT type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>
						 </TD>
					</TR>
					<!--可以再此处加入hidden域作为过滤条件 -->
				    <tr>
					<td class="right-border bottom-border" style="padding: 5px;width: 100px;">
							 <select class="span12"  id="bumen" filename="org_name" name ="bumen" onchange="Check(this.id)" >
							 	<option value='' select="selected">-所有部门-</option>
							 	<option value='52'>综合管理科</option>
							 	<option value='101'>监督管理科</option>
							 	<option value='11'>监督一组</option>
							 	<option value='16'>监督二组</option>
							 </select>
						 </td>
						<td class="right-border bottom-border" style="padding: 5px;width: 90px;">
						    <select class="span12"  id="nianfen"  name ="nianfen" onchange="checkNianfen(this.id)" >
							</select>
						</td>	
					    <td class="right-border bottom-border" style="padding: 5px;width: 260px;">
						     <select class="span12"  id="week"  operation="=" kind="dic" src="T#v_week_year:the_week:week:1=1" name = "eek" onchange="CheckTime(this.id)">
						       <option value=""></option>
							 </select>
						</td>
						<th width="5%" class="right-border bottom-border">计划内容</th>
						<td class="right-border bottom-border" width="15%" style="padding: 5px;">
							<input class="span12" type="text" onClick="enterSumbit()" id="PLAN_CONTENT_AM" name="PLAN_CONTENT_AM" fieldname="PLAN_CONTENT_AM" operation="like" >
						    <input style="display:none;" class="span12" type="text" onClick="enterSumbit()" id="PLAN_CONTENT_AM" name="PLAN_CONTENT_PM" fieldname="PLAN_CONTENT_PM" operation="like" >
						</td>	
						<td class="text-left bottom-border text-right">
	                        <button id="btnQuery" class="btn btn-link"  type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">
	                         <i class="icon-search" textalign="left"></i>查询</button>
           					 <button id="btnAdd" class="btn" type="button">添加下周计划</button>
           					 <button id="btnAddYj" class="btn" type="button">打印工作汇表</button>
			            </td>	
					</tr>
			   </table>
			</form>
		 <form>
			<div style="height:30px;" >
			   <table style="margin:" width="100%" class="table-hover yw-title" >
	                <thead>
	                    <tr>
	                        <th fieldname="ORG_NAME" colindex=2 tdalign="left" maxlength="30" >
	                        <h4 align="left"><span id="spyjtxhid" >监督一组工作计划(更新人:<span id="org_name01"></span>&nbsp;&nbsp;更新日期:<span id="update_date01"></span>)</span>
				                <span class="pull-right" ></span>
			                </h4>
	                        </th>
	                    </tr>
	              	<tbody></tbody>
	           </table>
			</div>	
			<div class="overFlowX ">
	            <table width="100%" class="table-hover table-activeTd B-table"  id="DT1" type="single" pageNum="7" noPage="true">
	                <thead>
	                    <tr>
	                    <!-- 隐藏 组织管理用户  CustomFunction="getUpdate01"-->
	                        <th fieldname="ORG_NAME"  colindex=2 tdalign="center" maxlength="10" CustomFunction="getName08" 
	                            id="nameOne01" style="display:none" >&nbsp;&nbsp;</th>
	                       
	                        <th fieldname="UPDATE_DATE" colindex=2 tdalign="center" maxlength="10"  
	                            id="update01" style="display:none" >&nbsp;&nbsp;</th>         
	                		<th fieldname="PLAN_DATE" colindex=2 tdalign="center" width="25%" CustomFunction="getUpdate01"
	                		    id="date01">&nbsp;日期&nbsp;</th>
							<th fieldname="PLAN_CONTENT_AM" colindex=2 tdalign="left"  width="35%" maxlength="15" >&nbsp;计划内容（上午） &nbsp;</th>
							<th fieldname="PLAN_CONTENT_PM" colindex=2 tdalign="left"  width="35%" maxlength="15">&nbsp;计划内容（下午）&nbsp;</th>
	                	</tr>
	                </thead>
	           </table>
	       </div>
	     </form>
	 	</div>
	</div>     
</div>
<div align="center">
	<FORM name="frmPost" method="post" style="display: none" target="_blank">
		<!--系统保留定义区域-->
		<input type="hidden" name="queryXML"/> 
		<input type="hidden" name="txtXML"/>
		<input type="hidden" name="txtFilter" order="desc" fieldname="UPDATE_DATE" id="txtFilter"/>
		<input type="hidden" name="txtFilter" order="asc" fieldname="WORK_PLAN_UID" id="txtFilter"/>
		
	    <input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
<div class="container-fluid" >
	<div height="0">
		<div class="B-small-from-table-autoConcise " id="Tb02">
			<form method="post" id="queryForm02">
				<table class="B-table" width="100%">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<TR style="display: none;">
						<TD class="right-border bottom-border"></TD>
						<TD class="right-border bottom-border">
					        <INPUT type="text" class="span12" kind="text" id="selectWeek02" fieldname="PLAN_DATE" value="" operation="=" />
						    <input class="span12" type="text" kind="text" id="PLAN_CONTENT_AM02" fieldname="PLAN_CONTENT_AM" value="" operation="like" >
						    <input class="span12" type="text" kind="text" id="PLAN_CONTENT_PM02" fieldname="PLAN_CONTENT_PM" value="" operation="like" >
							<INPUT type="text" class="span12" kind="text" id="organize02" fieldname="g.ORGANIZE_UID" value="16" operation="=" />
							<INPUT type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>
						</TD>
					</TR>
			   </table>
			 </form>
		  <form>
			<div style="height:30px;">
			   <table backgroundcolor="#F5F7FB" width="100%" class="table-hover yw-title" >
	                <thead>
	                    <tr>
	                        <th fieldname="ORG_NAME" colindex=2 tdalign="left" maxlength="30" >
	                        <h4 align="left"><span id="spyjtxhid" >监督二组工作计划(更新人:<span id="org_name02"></span>&nbsp;&nbsp;更新日期:<span id="update_date02"></span>)</span>
				                <span class="pull-right" ></span>
			                </h4>
	                        </th>
	                    </tr>
	              	<tbody></tbody>
	           </table>
			</div>	
			<div class="overFlowX">
	            <table width="100%" class="table-hover table-activeTd B-table" id="DT3" type="single"  pageNum="7" noPage="true">
	                <thead>
	                    <tr>
	                    <!-- 隐藏 组织管理用户(更新人)  -->
	                      
	                        <th fieldname="ORG_NAME" colindex=2 tdalign="center" maxlength="10" CustomFunction="getName02" 
	                            id="nameOne02" style="display:none">&nbsp;&nbsp;</th>
	                    <!-- 更新 日期 显示 页面上的  -->
	                        <th fieldname="UPDATE_DATE" colindex=2 tdalign="center" maxlength="10"  
	                            id="update02"  style="display:none" >&nbsp;&nbsp;</th>   
	                		<th fieldname="PLAN_DATE" colindex=1 tdalign="center" width="25%" CustomFunction="getUpdate02">&nbsp;日期&nbsp;</th>
							<th fieldname="PLAN_CONTENT_AM" colindex=2 tdalign="leftr"  width="35%" maxlength="15" >&nbsp;计划内容（上午） &nbsp;</th>
							<th fieldname="PLAN_CONTENT_PM" colindex=2 tdalign="left"  width="35%" maxlength="15" >&nbsp;计划内容（下午）&nbsp;</th>
	                	</tr>
	                </thead>
	              	<tbody></tbody>
	           </table>
	       </div>
	      </form>
	 	</div>
	</div>     
</div>
<div class="container-fluid">
	<div>
		<div class="B-small-from-table-autoConcise" id="Tb03">
			<form method="post" id="queryForm03">
				<table class="B-table" width="100%" height="10%">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<TR style="display: none;">
						<TD class="right-border bottom-border"></TD>
						<TD class="right-border bottom-border">
						     <INPUT type="text" class="span12" kind="text" id="selectWeek03" fieldname="PLAN_DATE" value="" operation="=" />
							<input class="span12" type="text" kind="text" id="PLAN_CONTENT_AM03" fieldname="PLAN_CONTENT_AM" value="" operation="like" >
							   <input class="span12" type="text" kind="text" id="PLAN_CONTENT_PM03" fieldname="PLAN_CONTENT_PM" value="" operation="like" >
							 <INPUT type="text" class="span12" kind="text" id="organize03" fieldname="g.ORGANIZE_UID" value="52" operation="=" />
							 <INPUT type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>
						</TD>
					</TR>
					
			  </table>
			</form>
		<form>
			<div style="height:30px;">
			   <table width="100%" class="table-hover yw-title"  type="single">
	                <thead>
	                    <tr>
	                        <th fieldname="ORG_NAME" colindex=2 tdalign="left" maxlength="30" >
	                        <h4 align="left"><span id="spyjtxhid" >综合管理科工作计划(更新人:<span id="org_name03"></span>&nbsp;&nbsp;更新日期:<span id="update_date03"></span>)</span>
				                <span class="pull-right" ></span>
			                </h4>
	                        </th>
	                    </tr>
	              	<tbody></tbody>
	           </table>
			</div>	
			<div class="overFlowX">
	            <table width="100%" class="table-hover table-activeTd B-table" id="DT5" type="single"  pageNum="7" nopage="true">
	                <thead>
	                    <tr>
	                     <!-- 隐藏 组织管理用户(更新人)  -->
	                        <th fieldname="ORG_NAME" colindex=2 tdalign="center" maxlength="10" 
	                            id="nameOne03" style="display:none">&nbsp;&nbsp;</th>
	                       <!-- 更新 日期 显示 页面上的  -->
	                        <th fieldname="UPDATE_DATE" colindex=2 tdalign="center" maxlength="10" 
	                            id="update03"  style="display:none">&nbsp;&nbsp;</th>   
	                		<th fieldname="PLAN_DATE" colindex=1 tdalign="center" width="25%" CustomFunction="getUpdate03" >&nbsp;日期&nbsp;</th>
							<th fieldname="PLAN_CONTENT_AM" colindex=2 tdalign="left"  width="35%" maxlength="15">&nbsp;计划内容（上午） &nbsp;</th>
							<th fieldname="PLAN_CONTENT_PM" colindex=2 tdalign="left"  width="35%" maxlength="15">&nbsp;计划内容（下午）&nbsp;</th>
	                	</tr>
	                </thead>
	              	<tbody></tbody>
	           </table>
	       </div>
	      </form>
	 	</div>
	</div>     
</div>
<div class="container-fluid">
	<div>
		<div class="B-small-from-table-autoConcise" id="Tb04" >
			<form method="post" id="queryForm04">
				<table class="B-table" width="100%" >
					<!--可以再此处加入hidden域作为过滤条件 -->
					<TR style="display: none;">
						<TD class="right-border bottom-border">
						  <INPUT type="text" class="span12" kind="text" id="selectWeek04" fieldname="PLAN_DATE" value="" operation="=" />
					      <input class="span12" type="text" kind="text" id="PLAN_CONTENT_AM04" fieldname="PLAN_CONTENT_AM" value="" operation="like" >
					      <input class="span12" type="text" kind="text" id="PLAN_CONTENT_PM04" fieldname="PLAN_CONTENT_PM" value="" operation="like" >
					      <INPUT type="text" class="span12" kind="text" id="organize04" fieldname="g.ORGANIZE_UID" value="101" operation="=" />
					      <INPUT type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>
						</TD>
					</TR>
			  </table>
			</form>
		<form>
			<div style="height:30px;"
			   <table width="100%" class="table-hover yw-title">
	                <thead>
	                    <tr>
	                        <th fieldname="ORG_NAME" colindex=2 tdalign="left" maxlength="30" >
	                        <h4 align="left"><span id="spyjtxhid" >监督管理科工作计划(更新人:<span id="org_name04"></span>&nbsp;&nbsp;更新日期:<span id="update_date04"></span>)</span>
				                <span class="pull-right" ></span>
			                </h4>
	                        </th>
	                    </tr>
	              	<tbody></tbody>
	           </table>
			</div>	
			<div class="overFlowX">
	            <table width="100%" class="table-hover table-activeTd B-table" id="DT7" type="single" pageNum="7" noPage="true">
	                <thead>
	                    <tr>
	                     <!-- 隐藏 组织管理用户(更新人)  -->
	                        <th fieldname="ORG_NAME" colindex=2 tdalign="center" maxlength="10" CustomFunction="getName04" 
	                            id="nameOne04" style="display:none">&nbsp;&nbsp;</th>
	                       <!-- 更新 日期 显示 页面上的  -->
	                        <th fieldname="UPDATE_DATE" colindex=2 tdalign="center" maxlength="10" 
	                            id="update04" style="display:none" >&nbsp;更新日期&nbsp;</th>   
	                		<th fieldname="PLAN_DATE" colindex=1 tdalign="center" width="25%" CustomFunction="getUpdate04" >&nbsp;日期&nbsp;</th>
							<th fieldname="PLAN_CONTENT_AM" colindex=2 tdalign="left" width="35%" maxlength="15">&nbsp;计划内容（上午） &nbsp;</th>
							<th fieldname="PLAN_CONTENT_PM" colindex=2 tdalign="left" width="35%" maxlength="15">&nbsp;计划内容（下午）&nbsp;</th>
	                	</tr>
	                </thead>
	              	<tbody></tbody>
	           </table>
	       </div>
	      </form>
	 	</div>
	</div>     
</div>
</body>
<script type="text/javaScript" charset="utf-8">
//onKeydown  点击 查询按钮或按Enter键
 function enterSumbit(){ 
	     var obj = document.getElementById("week");
		 var txt = obj.options[obj.selectedIndex].text;
		 /**获取分集信息 **/
		 var re = /[\u4000-\uFFFF]/g;
		 var periods = txt.replace(re, '').split('~');
		 var obj01 = document.getElementById("bumen");
		 var selectedBumen = obj01.options[obj01.selectedIndex].text;
		
		 if(selectedBumen=="-所有部门-"){
			 var datatxt  = $("#PLAN_CONTENT_AM").val();
			 $("#PLAN_CONTENT_AM02").val(datatxt);
			// $("#PLAN_CONTENT_PM02").val(datatxt);
					
			 $("#PLAN_CONTENT_AM03").val(datatxt);
			// $("#PLAN_CONTENT_PM03").val(datatxt);	
			 	
			 $("#PLAN_CONTENT_AM04").val(datatxt);
			// $("#PLAN_CONTENT_PM04").val(datatxt);		
			 workPlanList(periods);
		 }else if(selectedBumen=="综合管理科"){
			 $("#PLAN_CONTENT_AM03").val(
					 document.getElementById("PLAN_CONTENT_AM").value);
			 
			 $("#selectWeek03").val(periods);
			 var data3 = combineQuery.getQueryCombineData(queryForm03,frmPost,DT5);
			 defaultJson.doQueryJsonList(controllername+"?query",data3,DT5);
		 }else if(selectedBumen=="监督管理科"){
			 $("#PLAN_CONTENT_AM04").val(
					 document.getElementById("PLAN_CONTENT_AM").value);
			 
			 $("#selectWeek04").val(periods);
			 var data4 = combineQuery.getQueryCombineData(queryForm04,frmPost,DT7);
			 defaultJson.doQueryJsonList(controllername+"?query",data4,DT7); 
		 }else if(selectedBumen=="监督一组"){
			 $("#selectWeek").val(periods);
			 var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
			 defaultJson.doQueryJsonList(controllername+"?query",data,DT1); 
		 }else if(selectedBumen=="监督二组"){
			 $("#PLAN_CONTENT_AM02").val(
					 document.getElementById("PLAN_CONTENT_AM").value);
				 
			 $("#selectWeek02").val(periods);
			 var data2 = combineQuery.getQueryCombineData(queryForm02,frmPost,DT3);
			 defaultJson.doQueryJsonList(controllername+"?query",data2,DT3);
			 
		 }
		 
	    
	     
 }	 
 
	</script>
</html>