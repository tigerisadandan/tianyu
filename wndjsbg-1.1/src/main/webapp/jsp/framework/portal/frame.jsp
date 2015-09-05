<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp" %>

<%@page import="com.ccthanking.framework.Constants"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.OrgDept"%>
<%@ page import="com.ccthanking.framework.Globals"%>
<%@ page import="com.ccthanking.framework.util.Pub"%>
<%@page import="org.directwebremoting.WebContextFactory"%>
<%@page import="com.ccthanking.framework.params.SysPara.SysParaConfigureVO"%>
<%@page import="com.ccthanking.framework.params.ParaManager"%>
<%@page import="com.ccthanking.common.YwlxManager"%>
<%@page import="com.ccthanking.framework.coreapp.orgmanage.UserManager"%>
<%@page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext"%>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", -10);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><fmt:message key="ui.title"/></title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css"> </LINK>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/style-responsive.css"> </LINK>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.css"> </LINK>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/base.css"> </LINK>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/default.css"> </LINK>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/perfect-scrollbar.min.css" rel="stylesheet" id="style_color"/>
<link rel="icon" href="${pageContext.request.contextPath }/favicon.ico" type="image/x-icon" /> 
<link rel="shortcut icon" href="${pageContext.request.contextPath }/favicon.ico" type="image/x-icon" /> 
<script type="text/javascript" src="${pageContext.request.contextPath }/js/base/jquery.js"></script>
<style type="text/css">
	.user{
	width:230px;
	margin-top: 10px; 
	margin-left: 50px;
	}
</style>
</head>

<body class="MainBodyBg" onload="dwr.engine.setActiveReverseAjax(true); ">
<app:dialogs />
<%
  User user = RestContext.getCurrentUser();  
  String userid = user.getAccount();
  String userId = user.getUserSN();
  String username = user.getName();
  String sysdate = Pub.getDate("yyyy-MM-dd HH:mm:ss");
  SysParaConfigureVO syspara = (SysParaConfigureVO) ParaManager
			.getInstance().getSysParameter("DesktopRemindTime");
  String miao = "10";
  if (syspara != null&& !"".equals(syspara.getSysParaConfigureParavalue1())) {
		miao = syspara.getSysParaConfigureParavalue1();
	}
  String lastLoginTime = UserManager.getInstance().getUserLastLoginTime(userid);
 // System.out.println(username+"*****"+userid);
 
 	//获取URL后面的各种参数
	String information = request.getParameter("information");
 	
 	String app_name = request.getParameter("app_name");
%>

<script type="text/javascript">

	var p_userAccount = '<%=userid%>';
	
	var information= '<%=information%>';
	var datalist=new Array();
	datalist=information.split(",");
	if(information=="null"||information==null&&information==""){
		var app_name='<%=app_name%>';
	}else{
		var app_name=	datalist[0];
	}	
	var data_id=	datalist[1];
	var level=		datalist[2];
	var data_url=	datalist[3];
	var p_name=		datalist[4];
	var g_name=		datalist[5];
	$(function() {   
		getCountMess();
		init();
	});	

	
	function init(){
		if(app_name!=null&&app_name!=""&&app_name!="none"&&app_name!="null"){			
			showXtMenu(app_name);
			
			if(data_id==null||data_id=="none"){												
				$(".page-sidebar-menu li[appname="+app_name+"]:first").attr("class","open");
				$(".page-sidebar-menu li[appname="+app_name+"]:first").find("ul").css("display","block");
			}	
			
			if(level=="2"){
				if(data_url!="none"){
					document.getElementById("menuiframe").src = "${pageContext.request.contextPath}/"+data_url;
				}
				$(".page-sidebar-menu li[appname="+app_name+"][id="+data_id+"]:first").attr("class","open");
				$(".page-sidebar-menu li[appname="+app_name+"][id="+data_id+"]:first").find("ul").css("display","block");			
			}
			
			if(level=="3"){
				if(data_url!="none"){
					document.getElementById("menuiframe").src = "${pageContext.request.contextPath}/"+data_url;
				}
				$(".page-sidebar-menu li[appname="+app_name+"][id="+p_name+"]:first").attr("class","open");
				$(".page-sidebar-menu li[appname="+app_name+"][id="+p_name+"]:first").find("ul").css("display","block");				
				$(".page-sidebar-menu li[appname="+app_name+"][id="+data_id+"][p_name="+p_name+"]:first").attr("class","active");
			}
			
			if(level=="4"){
				//do level==4;
			}
		}		
	}
	
	//通过给出的参数跳转页面
	function forwardTagPage(app_name,data_id,data_name,data_url,secondname){
		
	}
	
	
    //菜单按钮点击响应函数
    function menutree_click(menuName,menuLocation,menuDesc,target)
    { 
        if($(this).attr("lType")=="out"){
			$(this).attr("class","open");
        }
   
    	if("#"!=menuLocation){
    		if(target=="pagearea"){
    			document.getElementById("menuiframe").src = "${pageContext.request.contextPath}/"+menuLocation;
    			
    		}else if(target=="fullscreen"){
    			//OpenFullWindow(menuLocation,menuDesc);
    			$(window).manhuaDialog({"title":menuDesc,"type":"url","content":"${pageContext.request.contextPath}/"+menuLocation,"modal":"1"});
    		}
    	}
    	
    }
    //首页按钮点击响应函数
    function doHomePage()
    {
    	location.href="${pageContext.request.contextPath}/jsp/framework/portal/frame_homepage.jsp";
    }
    //注销按钮响应函数
    function dologout()
    {
    	document.location.href="${pageContext.request.contextPath }/logout";
    }
    
    /**打开消息列表**/
    function openMessage(){
 	   $(window).manhuaDialog({"title":"短消息","type":"text","content":"${pageContext.request.contextPath}/jsp/framework/message/allMessage.jsp","modal":"1"});
    }
	
    function modifyPassword(){
 	   $(window).manhuaDialog({"title":"修改密码","type":"text","content":"${pageContext.request.contextPath}/jsp/framework/system/user/modifyPassword.jsp","modal":"4"});
    }
  	
 	function showSuccessMsg(){
 		xAlert("信息提示","操作成功！");
 	}
    
	function showXtMenu(app_name2){
		$.each($("li"),function(){
			if($(this).attr("appName")!=null){
				if($(this).attr("appName")==app_name2){
					$(this).show();
				}else{
					$(this).hide();
				}
			}
		});
		if(app_name2=='sxsp'){
			 getAllDsCount();
		}
		jQuery('.page-sidebar-menu>li>a:visible:first').click();
	}

	function getAllDsCount(){
		$.ajax({
			url : "${pageContext.request.contextPath }/ywlz/buSpYwlzController?getAllDsCount",
			data : {},
			cache : false,
			async :	false,
			dataType : "json",  
			type : 'post',
			success : function(response) {
				if(response!=""){
					var j = "(" + response.msg +")";  // 用括号将json字符串括起来
				    var jsonarr= eval(j);
					$.each(jsonarr,function(i){			
						/*
						var ds = document.createElement("h");		  
						ds.innerHTML = " (<font color=\"red\">"+this.COUNTDS+"</font>)";
						var ddd=document.getElementById(this.SPYW_UID);
						if(ddd!=null){
							$("#"+this.SPYW_UID+"").empty();
							document.getElementById(this.SPYW_UID).appendChild(ds);
						}					*/
						$("#num_"+this.SPYW_UID+"").html("(<font color=\"red\">"+this.COUNTDS+"</font>)");
						
					});					
				}
			}
		});
	}
		
	//----------------------------------消息加载
		

	function openmessage(){
		document.getElementById("menuiframe").src = "${pageContext.request.contextPath}/jsp/weixin/fsmessage-list.jsp";
	}
	
	window.setInterval(getCountMess,300000);

	//初始化未阅读的信息条数
	function getCountMess(){
		$.ajax({
			url : '${pageContext.request.contextPath}/fsMessageInfoController?countMessage',
			//data :"",
			cache : false,
			async :	false,
			dataType : "json",  
			type : 'post',
			success : function(response) {
				var obj =eval("("+response.msg+")");
				//alert(obj);
				if(obj!=null){
					$("#spanidmes").html(obj[0].ALLMES);
					if(obj[0].ALLMES>0){
						getMess();
					}
				}else{
					$("#spanidmes").html("");
				}
			}
		});
	} 

	//弹出显示未读的前5条
	function getMess(){
		var data1 = combineQuery.getQueryCombineData(querytopform,querytopfrmPost,null);
	    var data = {
	  		msg : data1
	  	};
		$.ajax({
			url : "${pageContext.request.contextPath}/fsMessageInfoController?query",
			data : data,
			cache : false,
			async :	false,
			dataType : "json",  
			type : 'post',
			success : function(response) {
				var obj =eval("("+response.msg+")");
				
				if(obj!="0"){			
					var contentdata =  convertJson.string2json1(response.msg);
					var listmesg=contentdata.response.data;
					
					$.each(listmesg,function(i){
						xAlert(this.TITLE,this.CONTENT,1);						
					});
					
				}
			}
		});
		
	}
	
</script>

<!-- 消息显示层start -->
<div id="MessageModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="MessageModalTitle"></h3>
  </div>
  <div class="modal-body" id="MessageModalContent">
    
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
    <button class="btn btn-inverse" data-dismiss="modal" aria-hidden="true">知道了</button>
  </div>
</div>
<!-- 消息显示层end -->


<!-- start 顶部导航栏 -->
	<div class="header" style="min-width: 1250px !important;">
		<div class="logo text-center"><img src="${pageContext.request.contextPath}/images/glxt/mainLogo.png" alt="无锡市新区建设环保局—政务审批及项目动态管控平台"></div>
        
        <div class="Nav text-center DefaultLink">
			<a href="javascript:void(0)" onclick="doHomePage()"><img class="DefaultLinkImg" src="${pageContext.request.contextPath}/images/glxt/headerNavSy.png" alt="首页"><br>首页</a>
		</div> 
		  
		<!--顶部菜单 -->     
        <app:menutop jsfunc="showXtMenu"/>
        
        <div class="user" style="width: 230px;">
        	<div class="contents">您好，<%=username %>&nbsp;&nbsp;&nbsp;&nbsp;
        	<a href="javascript:void(0)" onclick="openmessage();">
        		<span class="badge badge-important" id="spanidmes"></span>
        	</a>
        		<ul>       			
        			<li>上次登录：<%=lastLoginTime %></li>
        			<li>
        				<a href="javascript:void(0)" id="modifyPass" onclick="modifyPassword()"><i class="icon-lock icon-white" ></i>修改密码</a>
        				&nbsp;
        				<a href="javascript:void(0)" onclick="dologout();"><i class="icon-off icon-white" ></i> 注销登录</a>
        			</li>
        		</ul>
        	</div>
        	<input type="hidden" id="userId" value="<%=userId %>"/>
        </div>
    </div>

	<div class="page-sidebar nav-collapse collapse leftNav"> 
        <ul class="page-sidebar-menu">
            <li> 
                <!-- start 左侧点击缩放 -->
                <div class="sidebar-toggler hidden-phone"></div>
                <!-- end 左侧点击缩放 -->
            </li>
			 <app:menusub/>
	</div>

	<div class="MainBox">
		<iframe  src="" id="menuiframe" width="100%"frameborder="0"></iframe>
	</div>
	<form style=" height:0; margin:0;" method="get" action="${pageContext.request.contextPath }/userController/logout" id="logoutForm" >
	</form>
<form role="form" id="querytopform" name="querytopform" style="display: none">
<input class="hidden" id="STATEID" type="text" fieldname="STATE"  value="1" operation="="/>									
<input class="hidden" id="rownum" type="text" fieldname="rownum"  value="3" operation="  &lt;= "/>
</form>
	<FORM name="querytopfrmPost" method="post" style="display: none" target="_blank">
		<!--系统保留定义区域-->
		<input type="hidden" name="queryXML"/> 
		<input type="hidden" name="txtXML"/>
		<input type="hidden" name="txtFilter" order="desc" fieldname="OPTIME" id="txtFilter_PUBLISH_TIME"/>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>	
<script src="${pageContext.request.contextPath }/js/base/jquery.js"></script>
<script src="${pageContext.request.contextPath }/js/base/jquery-ui.js"></script>

<script src="${pageContext.request.contextPath }/js/base/bootstrap.js"></script>
<script src="${pageContext.request.contextPath }/js/base/jsBruce.js"></script>

<script src="${pageContext.request.contextPath }/js/base/app.js"></script>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script src="${pageContext.request.contextPath }/js/base/modal.js"></script>
<script src="${pageContext.request.contextPath }/js/common/default.js"></script>
<script src="${pageContext.request.contextPath }/js/common/loadFields.js"></script>
<script src="${pageContext.request.contextPath }/js/common/bootstrap-validation.js"></script>
<script src="${pageContext.request.contextPath }/js/common/convertJson.js"></script>
<script src="${pageContext.request.contextPath }/js/common/combineQuery.js"></script>

	<script type="text/javascript">
      //弹出窗口关闭父页面回调空函数
      function closeParentCloseFunction(val){
	  }
      //弹出窗口关闭弹出页面回调空函数
      function closeNowCloseFunction(){
	  }
      function showMessage(title,content)
      {
    		 if($("#MessageModal")&&$("#MessageModal").length>0){
    				$("#MessageModalTitle").text(title);
    				$("#MessageModalContent").html("<p>"+content+"</p>");
    				$("#MessageModal").modal("show");
    			 }
      }
	var openWindowHeight = 5;
	jQuery(document).ready(function() { 
	   App.init(); // initlayout and core plugins
	   openWindowHeight = $(window).innerHeight();
	   //showXtMenu("wndjssgsp");
	});
</script> 
<!-- end 主体Box -->
<script type='text/javascript' src='${pageContext.request.contextPath }/dwr/engine.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath }/dwr/util.js'></script>

<script type="text/javascript">
//处理 dwr弹出错误
dwr.engine._errorHandler= function(message ,ex){
    dwr.engine._debug( "Error: " + ex.name + ", " + ex.message, true );
     if (message == null || message == ""){} 
		//alert("A server error has occurred.");
     // Ignore NS_ERROR_NOT_AVAILABLE if Mozilla is being narky
     else if(message.indexOf( "0x80040111") != -1) 
		dwr.engine._debug(message);
     else{};//alert(message); 忽略第一没建立数据通道的错误提示。当正确建立数据通道后，平台不会报此错误。
};

//var miao = <%=miao%>;
//js模拟静态常量
var staticmiao = function(){
    var miao = <%=miao%>;   //  在运行时NUM值是不会被修改的
    return function(){
        this.getMiao = function(){
            return miao;
        };
    };
    }();
//var obj = new staticmiao();
//alert(obj.getMiao());  //获取值
//设置多个常量
/*
var Class = function(){
    var constants = {
        X : 1,
        Y : 2,
        STR: '你好'   
    };
    return funtion(){
        this.getConstants = funtion( name ){
            return constants[name];
        }
    };
}
*/
//var obj = new Class();
//obj.getConstants('X');  //获取常量X

var pushIcon = "${pageContext.request.contextPath }/images/logo.png";
var pushTitle = "消息提示";
var pushInfo =  null;
var pushUrl = null;

function show(info,url,title){
	/**
	var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
	var isChrome = userAgent.indexOf("Chrome") > -1 ; //判断是否Chrome
	if(isChrome){
	  notify(info,url);
	}else{
	  alert(info);//非chrome浏览器暂时处理
	}
	**/
	
	var obj = convertJson.string2json1(info);
	if("1"==obj.type || ("2"==obj.type) || ("3"==obj.type)){
		xAlert(title,obj.msg,obj.type); 
		return ;
	}
	
	if(!window.webkitNotifications){
		alert(info);
		return false;
	}
	
	//getDxx();//不知道为什么要放到这里
	
	
	pushInfo = obj.msg;
	pushUrl = obj.url;
	pushTitle = title?title:pushTitle;
	
	notify();
}

function RequestPermission (callback) {
	window.webkitNotifications.requestPermission(callback);
}


function notify() {
        if (window.webkitNotifications.checkPermission()==0){
            var popup = window.webkitNotifications.createNotification(pushIcon, pushTitle, pushInfo);
            popup.ondisplay = function(event){
            	/**
                setTimeout(function() {
                    event.currentTarget.cancel();
                }, miao * 1000);
            	**/
            };
            popup.onclick = function(){
            	if(pushUrl){
           			document.getElementById("menuiframe").src = "${pageContext.request.contextPath}/"+pushUrl;
            	}
               	this.cancel();
            };
            popup.show();
        } else {
            RequestPermission(notify);
        };
};

</script>

</body>
</html>