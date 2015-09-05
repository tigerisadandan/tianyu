<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page import="com.ccthanking.framework.Constants"%>
<%@page import="com.visural.common.web.client.WebClient"%>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", -10);

//设置客户端信息
WebClient webclient = WebClient.detect(request);
//session.setAttribute(Constants.WEB_CLIENT, webclient);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="ui.title"/></title>
<link href="${pageContext.request.contextPath }/css/bootstrap.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath }/css/base.css" rel="stylesheet" media="screen">
<script src="${pageContext.request.contextPath }/js/base/jquery.js"></script>
<script src="${pageContext.request.contextPath }/js/plugins/cookie/jquery.cookie.js"></script>

<%--<script src="${pageContext.request.contextPath }/js/base/bootstrap.js"></script>--%>
<link rel="icon" href="${pageContext.request.contextPath }/favicon.ico" type="image/x-icon" /> 
<link rel="shortcut icon" href="${pageContext.request.contextPath }/favicon.ico" type="image/x-icon" />
</head>
<%
 String error = (String)request.getAttribute("error");
  if(error==null)
  {
	  error = "";
  }
%>

<body class="body_login_bg">
<div id="MessageModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="MessageModalTitle">提示信息</h3>
  </div>
  <div class="modal-body" id="MessageModalContent">
    <%=error %>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
  </div>
</div>
<div class="loginBox">
<div id="browser_msg" style="color:red;font-weight: bold;text-align: right">
    		
</div>
	<div class="loginBoxLeft">
        <div class="loginBoxLeft_1"><img src="${pageContext.request.contextPath }/images/login/loginBoxLeft_1.png"/></div>
        <div class="loginBoxLeft_6"><a href="#"><img src="${pageContext.request.contextPath }/images/login/loginBoxLeft_4.jpg"/></a></div>
        <div class="loginBoxLeft_3"><a href="#"><img src="${pageContext.request.contextPath }/images/login/loginBoxLeft_3.png"/></a></div>
        <div class="loginBoxLeft_5"><a href="#"><img src="${pageContext.request.contextPath }/images/login/loginBoxLeft_5.jpg"/></a></div>
    </div>
    <form method="post" action="${pageContext.request.contextPath }/userController/login" id="loginForm" >
    
    <div class="loginBoxRight">
    	<h3>用户登录</h3>
    	
        <div class="loginBoxRightRow">
        	<span class="text-center"><i class="icon-user"></i></span>
        	<input type="text"  name="username" id="username"  value=""  placeholder="请输入用户名">
            </div>
            <div class="loginBoxRightRow">
        	<span class="text-center"><i class="icon-lock"></i></span>
        	<input type="password" name="password" id="password" value="" placeholder="请输入密码">
        </div>
        <div class="loginBoxRightRow"><button id="login">登 录</button>
        	
        </div>
        <div class="pull-left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   			<input type="checkbox" id="remem_username" name="remem_username" value="1"/>记住账号
   			&nbsp;&nbsp;
   			<input type="checkbox" id="remem_password" name="remem_password" value="1"/>记住密码
        </div>
    </div>
    </form>
    <br/>
    <div> <%= Constants.getString("BROWSER_ALTER","") %></div>
    <div> <% 
    	//WebClient webclient =  (WebClient)session.getAttribute(Constants.WEB_CLIENT);
    	if("IE".equals(webclient.getUserAgent().toString())&&webclient.getMajorVersion()<=9){
    		%>
    			<script type="text/javascript">
    				$("#browser_msg").text("<%=Constants.getString("BROWSER_ERROR","")%>");
    				$("#username").attr("disabled",true);
    				$("#password").attr("disabled",true);
    				alert("<%=Constants.getString("BROWSER_ERROR","")%>");
    			</script>
    		<%
    		
    	}
    %></div>
</div>

</body>


</html>
<script  type="text/javascript">
<%-- 
function _scroll(obj) {   
	/*往上*/  
	var tmp = (obj.scrollTop)++;    
	if (obj.scrollTop == tmp) {        
		obj.innerHTML += obj.innerHTML;     
	}         
	if (obj.scrollTop >= obj.offsetHeifht) {      
		obj.scrollTop = 0;   
	}  
}  

var _timer = setInterval("_scroll(document.getElementById('scrollnews'))", 100);  
function _stop() {  
	if (_timer != null) {  
		clearInterval(_timer);  
	}  
}  
function _start() {  
	_timer = setInterval("_scroll(document.getElementById('scrollnews'))", 100);  
}  
--%>     
 
function reg(){
	window.open("${pageContext.request.contextPath }/sgentshengm.do", "施工单位注册");
}

function download(){
	window.open("${pageContext.request.contextPath }/setup.zip");
}

function setAlertHTML()
{
	var alertHTML  = "<div id=\"pubAlert\" class=\"alert alert-block block-b-open\">";
	    alertHTML += "<button type=\"button\" class=\"close\" data-dismiss=\"alert\"></button>";
        alertHTML += "<h4>提示信息</h4>";
        alertHTML += "<span class=\"alertContent\"></span >";
        alertHTML += "</div>";
    $("body").prepend(alertHTML);
}
function showAppMsg(){
	window.open("${pageContext.request.contextPath }/regrsshow.do", "审核状态", "width=800,height=600");
}
$(document).ready(function(){
	  setAlertHTML();
	  $("#username").focus();
	<% if(error.length()>0){%>
	  $("#pubAlert").find("span").html("<%=error %>");

	  $("#pubAlert").slideDown('fast').delay(2000).slideUp('fast');
	<%}%>
	var path='${pageContext.request.contextPath }';
	
	$("#username").keydown(function(event){ 
		if(event.keyCode==13){ 
			$("#password").focus();
		}
	});
	$("#password").keydown(function(event){ 
		if(event.keyCode==13){ 
			//$("#loginForm").submit();
			$("#login").click();
			return false;
		}
	});

	var uname = $.cookie("<%=Constants.APP_COOKIE_NAME_REMBERUSERNAME%>");
	if(uname==undefined){
		$("#remem_username").attr('checked',false);
	}else{
		$("#remem_username").attr('checked',true);
		$("#username").val(uname);
	}
	var uname_pwd = $.cookie("<%=Constants.APP_COOKIE_NAME_REMBERUSERNAME%>_pwd");
	if(uname_pwd==undefined){
		$("#remem_password").attr('checked',false);
	}else{
		$("#remem_password").attr('checked',true);
		$("#password").val(uname_pwd);
	}
	
	
	//$("#login").bind("click",function() {
	$("#login").click(function() {
		var username = $("#username").val();
		if(username=="")
		{
			$("#pubAlert").find("span").html("请输入用户名！");
			$("#pubAlert").slideDown('fast').delay(2000).slideUp('fast');		
			$("#username").focus();
			return false;
		}
		var password = $("#password").val();
		
		if($("#remem_username")[0].checked){
			var date = new Date(); 
			date.setTime(date.getTime() + (14 * 24 * 60 * 60 * 1000)); 
			$.cookie('<%=Constants.APP_COOKIE_NAME_REMBERUSERNAME%>',username, {expires:date});
		}else{
			$.removeCookie('<%=Constants.APP_COOKIE_NAME_REMBERUSERNAME%>');
		}
		
		if($("#remem_password")[0].checked){
			var date = new Date(); 
			date.setTime(date.getTime() + (14 * 24 * 60 * 60 * 1000)); 
			$.cookie('<%=Constants.APP_COOKIE_NAME_REMBERUSERNAME%>_pwd',password, {expires:date});
		}else{
			$.removeCookie('<%=Constants.APP_COOKIE_NAME_REMBERUSERNAME%>_pwd');
		}
		
		$("#loginForm").submit();
		return false;
		
<%--		var fields = $("#loginForm").serialize();--%>
<%--		alert(fields);--%>
<%--		$.ajax({--%>
<%--			type: "GET",--%>
<%--			cache:false,--%>
<%--			ifModified:true,--%>
<%--			url: '${pageContext.request.contextPath }/userController/login',--%>
<%--			data: fields,--%>
<%--			success: function(msg){--%>
<%--				var result = eval("(" + msg + ")");--%>
<%--				if(result.flag=="true"){--%>
<%--			   		alert("操作成功!");--%>
<%--			   		window.returnValue='True';--%>
<%--			   		window.close();				   		--%>
<%--		   		}else{--%>
<%--		   			alert("保存记录失败,请联系管理员!");--%>
<%--			   	}--%>
<%--		   }--%>
<%--		});--%>
	});

	
});

</script>