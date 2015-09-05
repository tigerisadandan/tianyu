<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<style type="text/css">
.row-fluid a{
	text-decoration: underline;
}

.row-fluid hr{
	margin-left:2%;
	margin-bottom:0;
	height: 1px;
	width: 96%;
}

.row-fluid p{
	margin-left: 30px;
}

.row-fluid h3{
	margin-left: 10px;
	margin-top:0;
	margin-bottom:0;
	font-weight: normal;
	font-size:20px;
	color:#0866C6;
}

.row-fluid span{
	font-size: 15px;
}

.Expired{
	color:red;
}

.Expired a{
	color:red;
}


#dtgl_homepage_right a{
	cursor: pointer;
}

#dtgl_homepage_right table{
	width: 100%;
}

#dtgl_homepage_right table td{
margin-bottom: 12px;
}

.dtgl_homepage_right_ksrk{
	font-size:18px;
	line-height: 22px;
}

.dtgl_homepage_right_setting{
	font-size:15px;
	line-height: 22px;
}

.rollclass{
	height:130px;
	overflow-y:auto;
}


#resourceStyle a{
	display:block;	
	width:130px;
	height:130px;
	text-decoration:none;
	border:1px white solid;
	cursor: pointer;
	background-image: url("${pageContext.request.contextPath }/images/dtgl_homepage/quicklink.png");
}

#resourceStyle a span{	
	display:block;
	margin-top:90px;
	margin-left:10px;
	text-align:center;
	width:110px;
	color: white;	
	font-size: 18px;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}
</style>
<app:base />
<title>无锡新区建设环保局—政务审批及项目动态管控平台-首页</title> 
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/ywlz/css/default.css"> </LINK>
<%
	String userid = request.getParameter("userid");
%>
<script type="text/javascript" charset="utf-8">
var controllername = "${pageContext.request.contextPath}";

$(function(){
	init();	
		
	$("#setting").click(function(){
		var code=$("#resourceStyle").html();		
		var resourceStyle=code.replaceAll("\"","@");
		$(window).manhuaDialog({"title":"首页>快速入口>设置","type":"text","content":"${pageContext.request.contextPath }/jsp/framework/portal/main_setting.jsp?resourceStyle="+resourceStyle,"modal":"1"});
	});
	
});

function init(){
	var userid="<%=userid%>";
	
	addresourceStyle();
	
	$("#setting").css("float","right");
	
	$("#dtgl_homepage_right_table tr").each(function(){
		$(this).children('td').eq(0).css("float","left");
		$(this).children('td').eq(1).css("float","right");
	});
	
	$("#dtgl_homepage_right_table tr").eq(3).children('td').each(function(){
		$(this).css("margin-bottom","0");		
	});
	
	$("#dtgl_homepage_right_table tr td a").each(function(){
		$(this).click(function(){		
			var app_name=$(this).attr("appName");
			var p_name=$(this).attr("p_name");
			var g_name=$(this).attr("g_name");
			var data_id=$(this).attr("id");
			var level=$(this).attr("level");
			var data_url=$(this).attr("url");
								
			if(data_id==""||data_id==null||typeof(data_id)=="undefined") data_id="none";
			if(level==""||level==null||typeof(level)=="undefined") level="none";
			if(data_url==""||data_url==null||typeof(data_url)=="undefined") data_url="none";
			if(p_name==""||p_name==null||typeof(p_name)=="undefined") p_name="none";
			if(g_name==""||g_name==null||typeof(g_name)=="undefined") g_name="none";
				
			var information=app_name+","+data_id+","+level+","+data_url+","+p_name+","+g_name;			
			parent.location.href="${pageContext.request.contextPath}/jsp/framework/portal/frame.jsp?information="+information;					
	});	
	});
	
}



String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {
    if (!RegExp.prototype.isPrototypeOf(reallyDo)) {
        return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);
    } else {
        return this.replace(reallyDo, replaceWith);
    }
};

//动态添加resourceStyle中内容
function addresourceStyle(){
	var resource=parent.document.getElementById("getResource").value;
	if(resource=="null"||resource==null||resource==""){
		var resourceStyle="<table id=\"dtgl_homepage_right_table\">"+
		"<tr><td><a level=\"2\" id=\"dtsy\" appName=\"dtgl\" url=\"jsp/business/dtgl/index/index.jsp\" title=\"首页\"><span>首页</span></a></td><td><a level=\"2\" id=\"RYGL\" appName=\"dtgl\" url=\"jsp/business/dtgl/rygl/gdry-index.jsp\" title=\"人员管理\"><span>人员管理</span></a></td></tr>"+		
		"<tr><td><a level=\"3\" p_name=\"qyrksh\"  id=\"kcsjqysh\" appName=\"wndjssgsp\" url=\"jsp/business/wxgc/kcenterprise-index.jsp\" title=\"勘察设计企业库\"><span>勘察设计企业库</span></a></td><td><a level=\"3\" p_name=\"nbgl\"  id=\"jhgl\"   appName=\"dtgl\" url=\"jsp/business/nbgl/jhgl-page.jsp\" title=\"计划管理\"><span>计划管理</span></a></td></tr>"+
		"<tr><td><a level=\"2\" id=\"wxgcsh\" appName=\"wxgc\" url=\"jsp/business/wxgc/yxwxgc-index.jsp\" title=\"微型工程信息\"><span>微型工程信息</span></a></td><td><a level=\"3\" p_name=\"wxgcsjgl\"  id=\"wgsjgl\"   appName=\"wxgc\" url=\"jsp/business/wxgc/yxwgsj-index.jsp\" title=\"评价标准管理\"><span>评价标准管理</span></a></td></tr>"+
		"<tr><td><a title=\"自定义\"><span>自定义</span></a></td><td><a level=\"\" p_name=\"\" id=\"\" appName=\"\" url=\"\" title=\"\"><span>自定义</span></a></td></tr>"+
		"</table>";
	   $("#resourceStyle").html(resourceStyle);
	}else{
		$("#resourceStyle").html(resource);
	}		
}
//add functions here

</script>    
</head>

<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">
	<div>
	<div style="float:left;width:900px;margin-left: 15px;margin-top: 20px;">	
	
	<table class="content" width="100%">
	<tr><td class="yw-title"><h4>待办事项</h4></td></tr>
	<tr><td><h3>手续审批</h3><div class="rollclass">
	<span>
	<p>勘察、设计单位资质审核及合同备案有<a href="#">&nbsp8&nbsp</a>个待审核，其中<a href="#">&nbsp1&nbsp</a>个即将过期，
	<span class="Expired"><a href="#" >&nbsp2&nbsp</a>个已过期</span></p>
	<p>房屋建筑和市政基础设施工程施工、监理直接发包合同备案有<a href="#">&nbsp7&nbsp</a>个待审核，其中<a href="#">&nbsp1&nbsp</a>个即将过期，
	<span class="Expired"><a href="#" >&nbsp2&nbsp</a>个已过期</span></p>
	<p>建设工程安全、质量监督申报有<a href="#">&nbsp8&nbsp</a>个待审核，其中<a href="#">&nbsp1&nbsp</a>个即将过期，
	<span class="Expired"><a href="#" >&nbsp0&nbsp</a>个已过期</span></p>
	<p>建设工程安全、质量监督申报有<a href="#">&nbsp8&nbsp</a>个待审核，其中<a href="#">&nbsp1&nbsp</a>个即将过期，
	<span class="Expired"><a href="#" >&nbsp0&nbsp</a>个已过期</span></p>
	<p>建设工程安全、质量监督申报有<a href="#">&nbsp8&nbsp</a>个待审核，其中<a href="#">&nbsp1&nbsp</a>个即将过期，
	<span class="Expired"><a href="#" >&nbsp0&nbsp</a>个已过期</span></p>
	<p>建设工程安全、质量监督申报有<a href="#">&nbsp8&nbsp</a>个待审核，其中<a href="#">&nbsp1&nbsp</a>个即将过期，
	<span class="Expired"><a href="#" >&nbsp0&nbsp</a>个已过期</span></p>
	<p>建设工程安全、质量监督申报有<a href="#">&nbsp8&nbsp</a>个待审核，其中<a href="#">&nbsp1&nbsp</a>个即将过期，
	<span class="Expired"><a href="#" >&nbsp0&nbsp</a>个已过期</span></p>
	</span></div>
	<hr color="#CCCCCC";/></td></tr>
	
	
	<tr><td><h3>项目动态</h3><div class="rollclass">
	<span>
	<p>待审核扣分<a href="#" >&nbsp9&nbsp</a>条，待审核扣分<a href="#" >&nbsp2&nbsp</a>条</p>
	<p>待审核全面停工<a href="#" >&nbsp0&nbsp</a>张，待审核局部停工<a href="#" >&nbsp0&nbsp</a>张，待审核复工<a href="#" >&nbsp0&nbsp</a>张</p>
	<p>考勤违规应扣分工程<a href="#" >&nbsp6&nbsp</a>个</p>
	<p>待审暂停考勤<a href="#" >&nbsp6&nbsp</a>个，待审核终止考勤<a href="#" >&nbsp6&nbsp</a>个，待审核分阶段考勤<a href="#" >&nbsp6&nbsp</a>个</p>
	<p>待审项目考勤状态变更<a href="#" >&nbsp6&nbsp</a>个，待审人员变更<a href="#" >&nbsp6&nbsp</a>人，待审人员解锁<a href="#" >&nbsp6&nbsp</a>人</p>	
	</span></div>
	<hr color="#CCCCCC"/></td></tr>	
		
	
	<tr><td><h3>待审事项</h3><div class="rollclass">
	<span>
	<p>待审建设单位<a href="#" >&nbsp1&nbsp</a>个，待审项目<a href="#" >&nbsp9&nbsp</a>个，待审项目分期<a href="#" >&nbsp2&nbsp</a>个，待审施工内容（工程）<a href="#" >&nbsp0&nbsp</a>个</p>
	<p>待审施工单位<a href="#" >&nbsp1&nbsp</a>个，待审施工人员<a href="#" >&nbsp9&nbsp</a>人，待审监理单位<a href="#" >&nbsp1&nbsp</a>个，待审监理人员<a href="#" >&nbsp9&nbsp</a>人</p>
	<p>待审起重机械安装单位<a href="#" >&nbsp1&nbsp</a>个，待审安装人员<a href="#" >&nbsp9&nbsp</a>人，待审起重机械设备<a href="#" >&nbsp9&nbsp</a>台</p>
	</span></div>	
	</td></tr>	
	</table>
   </div>
   
   
   <div id="dtgl_homepage_right" style="width: 285px;margin-left:40px;margin-top:20px;float: left;">
   <table >
   <tr><td class="dtgl_homepage_right_ksrk">快速入口</td><td class="dtgl_homepage_right_setting"><a id="setting" style="text-decoration: none;">设置</a></td></tr>
   <tr><td colspan="2" id="resourceStyle">

   </td></tr>
   
   </table>
   </div>
   </div>
   
   </div>
  </div> 
</body>
<script>
</script>
</html>