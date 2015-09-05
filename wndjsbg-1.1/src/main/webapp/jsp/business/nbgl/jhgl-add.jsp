<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>添加下周计划管理</title>
<%
   String pid = request.getParameter("pid");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/date/moment.min.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername = "${pageContext.request.contextPath }/nbgl/jhgl/workPlanController";

var  deptId = "<%= pid%>";
var map = {};
	map['0'] = "周日";
	map['1'] = "周一";
	map['2'] = "周二";
	map['3'] = "周三";
	map['4'] = "周四";
	map['5'] = "周五";
	map['6'] = "周六";
	
//页面初始化
$(function() {
	
	init();
	$("#btnClose").bind("click", function(){
		var a=$(this).manhuaDialog.close();
	});	
	
	$("#btnSave").bind("click",function(){
		$.ajax({
			type:"POST",
			data:$("#zgtzdForm").serialize(),
			url:controllername+"?insert",
			dataType:"JSON",
			success:function(msg){
				
			},
			error:function(e){
				
			}
		});
		var a=$(window).manhuaDialog.getParentObj();
	    a.init();
		$(window).manhuaDialog.close();
	});
});
//页面默认参数
function init(){
	var zhouji = eval(moment().format('d')); //获取今天是周几 周日为0  周一为1
	if(zhouji==0){
		zhouji = 7; 
	}
	var nextweek = 7-zhouji; //几天后为下周一
	
	//获取时间段
	var day01 = moment().add('days',nextweek+1).format('YYYY/MM/DD'); 
	var day07 = moment().add('days',nextweek+7).format('YYYY/MM/DD');  
	var str = "20("+day01+","+day07+")";
	
	//设置 状态  "1"为 更新 "2"为 新增
	$.ajax({
		url:controllername+"?queryDeptData&timeStr="+str,
		type:"post",
		dataType : "json",
		success : function(response){
			var res = dealSpecialCharactor(response.msg);
			var resultmsgobj = convertJson.string2json1(res);
		
			var thtml = "";
		if(resultmsgobj!=null && resultmsgobj !=''){//说明有数据
			for(var i= 0;i<=6;i++){ //根据当前日期  计算下周一日期   循环下周的日期
				var data=resultmsgobj.response.data[i];
				//获取计划id
				var planId = data.WORK_PLAN_UID;
				var contentAm = data.PLAN_CONTENT_AM;
				var contentPm = data.PLAN_CONTENT_PM;
				var n = i+1;
				var day = moment().add('days',nextweek+n).format('YYYY年MM月DD日');  //前台展示的日期
				var week = moment().add('days',nextweek+n).format('d');
				var days = moment().add('days',nextweek+n).format('YYYY-MM-DD');   //传到后台保存的计划日期
				var tablehtml = "<tr><th  class=\"right-border bottom-border\">";
				tablehtml += "<input id=\"\" type=\"checkbox\" fieldname=\"AJ_Y\" name = \"\" value=\"Y\"/>";
				tablehtml += "<input type=\"hidden\" id=\"number"+n+"\" type=\"text\" fieldname=\"WORK_PLAN_UID\" name = \"workId_"+n+"\" value=\""+planId+"\"/>";
				tablehtml += "<input type=\"hidden\" id=\"day_"+n+"\" type=\"text\" name = \"day_"+n+"\" value=\""+days+"\"></th>";
				tablehtml += "<input type=\"hidden\" id=\"status_"+n+"\" type=\"text\" name = \"status_"+n+"\" value=\""+1+"\"></th>";
				tablehtml += "<td class=\"bottom-border right-border\" >"+n+"</td>";
				tablehtml += "<td class=\"bottom-border right-border\" >"+day+"</br>"+map[week]+"</td>";
				tablehtml += "<td class=\"bottom-border right-border\" >";
				tablehtml += "<textarea rows=\"6\" cols=\"\" style=\"width: 90%\" name = \"contentam_"+n+"\" id=\"contentam_"+n+"\">"+contentAm+"</textarea></td>";
				tablehtml += "<td class=\"bottom-border right-border\" >";
				tablehtml += "<textarea rows=\"6\" cols=\"\" style=\"width: 90%\" name = \"contentpm_"+n+"\" id=\"contentpm_"+n+"\">"+contentPm+"</textarea>";
				tablehtml += "</td>";
				tablehtml += "</tr>";
				$("#gzjh").append(tablehtml);
			}
				
			}else{
				 $.ajax({
						url:controllername+"?queryDeptName&deptId="+deptId,
						type:"post",
						dataType : "json",
					    success : function(response){
							var res = dealSpecialCharactor(response.msg);
							var resultmsgobj = convertJson.string2json1(res);
						if(resultmsgobj!=null&&resultmsgobj!=''){
				            for(var i= 1;i<=7;i++){ //根据当前日期  计算下周一日期   循环下周的日期
				                var datslist=resultmsgobj.response.data[i-i];
							    var deptName =datslist.ORG_NAME;
							    var deId = deptId; 
								var day = moment().add('days',nextweek+i).format('YYYY年MM月DD日');  //前台展示的日期
								var week = moment().add('days',nextweek+i).format('d');
								var days = moment().add('days',nextweek+i).format('YYYY-MM-DD');   //传到后台保存的计划日期
								var tablehtml = "<tr><th  class=\"right-border bottom-border\">";
								tablehtml += "<input id=\"\" type=\"checkbox\" fieldname=\"AJ_Y\" name = \"\" value=\"Y\"/>";
								tablehtml += "<input type=\"hidden\" id=\"number"+i+"\" type=\"text\" fieldname=\"WORK_PLAN_UID\" name = \"workId_"+i+"\" value=\""+i+"\"/>";
								tablehtml += "<input type=\"hidden\" id=\"deptid"+i+"\" type=\"text\" name = \"deptId_"+i+"\" value=\""+deId+"\"/>";
								tablehtml += "<input type=\"hidden\" id=\"name"+i+"\" type=\"text\" name = \"name_"+i+"\" value=\""+deptName+"\"/>";
								tablehtml += "<input type=\"hidden\" id=\"day_"+i+"\" type=\"text\" name = \"day_"+i+"\" value=\""+days+"\"></th>";
								tablehtml += "<input type=\"hidden\" id=\"status_"+i+"\" type=\"text\" name = \"status_"+i+"\" value=\""+2+"\"></th>";
								tablehtml += "<td class=\"bottom-border right-border\" >"+i+"</td>";
								tablehtml += "<td class=\"bottom-border right-border\" >"+day+"</br>"+map[week]+"</td>";
								tablehtml += "<td class=\"bottom-border right-border\" >";
								tablehtml += "<textarea rows=\"6\" cols=\"\" style=\"width: 90%\" name = \"contentam_"+i+"\" id=\"contentam_"+i+"\"></textarea></td>";
								tablehtml += "<td class=\"bottom-border right-border\" >";
								tablehtml += "<textarea rows=\"6\" cols=\"\" style=\"width: 90%\" name = \"contentpm_"+i+"\" id=\"contentpm_"+i+"\"></textarea>";
								tablehtml += "</td>";
								tablehtml += "</tr>";
								$("#gzjh").append(tablehtml);
					
			           }
						}else{
						   alert("没有 数据呢");
						}    
					}
					});	
				
			
		  } 
		}
	});
}

</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">
	<div style="height:5px;"></div>
	<div class="B-small-from-table-autoConcise">
      <h4 class="title">
      	<span class="pull-right">
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
	  		<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">保存</button>
      	</span>
      </h4>
     <form method="post" id="zgtzdForm"  >
      <table class="B-table" width="100%" style="text-align: center;" id="gzjh">
     	 <input  id="GONGCHENG_UID"  type="hidden" fieldname="GONGCHENG_UID" name = "GONGCHENG_UID" />
         <tr>
			<th  class="right-border bottom-border"style="width: 5%;"><input id="" type="checkbox" fieldname="AJ_Y" name = "" value="Y"/></th>
       		<td class="bottom-border right-border" style="width: 5%;">序号</td>
       		<td class="bottom-border right-border" style="width: 15%;">日期</td>
       		<td class="bottom-border right-border" style="width: 35%;">计划内容（上午）</td>
       		<td class="bottom-border right-border" style="width: 35%;">计划内容（下午）</td>
         </tr>
      </table>
    </form>
   </div>
 </div>
</div>
  <jsp:include page="/jsp/file_upload/fileuploadold_config_js.jsp" flush="true"/> 
</body>
</html>