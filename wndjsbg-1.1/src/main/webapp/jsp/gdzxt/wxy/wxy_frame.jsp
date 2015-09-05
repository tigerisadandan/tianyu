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
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/base_gdzxt.css"> </LINK>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/default.css"> </LINK>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/perfect-scrollbar.min.css" rel="stylesheet" id="style_color"/>
<link rel="icon" href="${pageContext.request.contextPath }/favicon.ico" type="image/x-icon" /> 
<link rel="shortcut icon" href="${pageContext.request.contextPath }/favicon.ico" type="image/x-icon" /> 


</head>
<body class="MainBodyBg" onload="dwr.engine.setActiveReverseAjax(true); ">
<%--<body class="MainBodyBg">--%>
<app:dialogs />
<%
	User user = RestContext.getCurrentUser();
  
  String userid = user.getAccount();
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
%>

<script type="text/javascript">

	var p_userAccount = '<%=userid%>';
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
    	document.getElementById("menuiframe").src = "${pageContext.request.contextPath}/jsp/framework/portal/main.jsp?userid=<%=userid%>";
    }
    //注销按钮响应函数
    function dologout()
    {
    	//$("#logoutForm").submit();
    	document.location.href="${pageContext.request.contextPath }/logout";
    }
    function doXxzx()
    {
    	//document.getElementById("menuiframe").src = "${pageContext.request.contextPath}/jsp/framework/portal/moreNews.jsp";
    	$(window).manhuaDialog({"title":"中心新闻  |  通知公告","type":"text","content":"${pageContext.request.contextPath}/jsp/framework/portal/moreNews.jsp","modal":"1"});
    }
    function doLczx()
    {
    	//document.getElementById("menuiframe").src = "${pageContext.request.contextPath}/jsp/framework/portal/moreNews.jsp";
    	$(window).manhuaDialog({"title":"流程中心","type":"text","content":"${pageContext.request.contextPath}/jsp/business/lcgl/lccx/Mylcgl.jsp","modal":"1"});
    }
    function showGG(id,ydcs){
    	readGG(id,ydcs);
   	 $(window).manhuaDialog({"title":"通知公告","type":"text","content":"${pageContext.request.contextPath}/jsp/framework/portal/newsDetail.jsp?type=1&id="+id+"&ydcs="+ydcs,"modal":"1"});
     getMessages();
   }
    
   /**打开消息列表**/
   function openMessage(){
	   $(window).manhuaDialog({"title":"短消息","type":"text","content":"${pageContext.request.contextPath}/jsp/framework/message/allMessage.jsp","modal":"1"});
   }
   
   function modifyPassword(){
	   $(window).manhuaDialog({"title":"修改密码","type":"text","content":"${pageContext.request.contextPath}/jsp/framework/system/user/modifyPassword.jsp","modal":"4"});
   }
   function readGG(ggid, ydcs) {
		$.ajax({
			url : "${pageContext.request.contextPath }/ggtzController.do?readMainGg&ggid="+ggid+"&ydcs="+ydcs ,
//			data : data,
			cache : false,
			async : false,
			dataType : 'json',
			success : function(response) {
				var result = eval("(" + response + ")");
			}
		});
	}
	//打开问题中心
	function openIssueCore(){
		var account = getLeaderAccount();
		if(account.indexOf(p_userAccount)!=-1){
			$(window).manhuaDialog({"title":"问题中心","type":"text","content":"${pageContext.request.contextPath}/jsp/business/wttb/wttbBzMain.jsp","modal":"1"});
		}else{
			$(window).manhuaDialog({"title":"问题中心","type":"text","content":"${pageContext.request.contextPath}/jsp/business/wttb/wttbMain.jsp","modal":"1"});
		}
	}
	//获取部门负责人账号
	function getLeaderAccount(){
		var account = "";
		$.ajax({
			url:"${pageContext.request.contextPath }/wttb/wttbController.do?queryLeader",
			data:"",
			dataType:"json",
			async:false,
			success:function(result){
				var res = result.msg;
				if(res!=""){
					var tempJson = eval("("+res+")");
					var blrStr = "";
					var blrCodeStr = "";
					for(var i=0;i<1;i++){
						var row = tempJson.response.data[i];
						blrStr +=row.NAME+",";
						blrCodeStr +=row.ACCOUNT+",";
					}
					account = blrCodeStr.length==0?"":blrCodeStr.substring(0,blrCodeStr.length-1);
				}
			}
		});
		return account;
	}
	function showXtMenu(app_name){
		$.each($("li"),function(){
			if($(this).attr("appName")!=null){
				if($(this).attr("appName")==app_name){
					$(this).show();
				}else{
					$(this).hide();
				}
			}
		})
	}
	function showSuccessMsg(){
		xAlert("信息提示","操作成功！");
	}
	function showXtMenu(app_name){
		$.each($("li"),function(){
			if($(this).attr("appName")!=null){
				if($(this).attr("appName")==app_name){
					$(this).show();
				}else{
					$(this).hide();
				}
			}
		})
		if(app_name=='sxsp'){
			 getAllDsCount();
		}

		//jQuery('.page-sidebar-menu>li>a:visible:first').click();//默认选择
	}

	function getAllDsCount(){
		//alert("-----");
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
						var ds = document.createElement("h");		  
						ds.innerHTML = " (<font color=\"red\">"+this.COUNTDS+"</font>)";
						var ddd=document.getElementById(this.SPYW_UID);
						if(ddd!=null){
							$("#"+this.SPYW_UID+"").empty();
							document.getElementById(this.SPYW_UID).appendChild(ds);
						}					
					})
					
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
	<div class="header">
		<span class="navbar-brand" style="width:70%;font-size: 18px;padding:10px;color: #fff;float: left;"> 
				<small>
					<b id="gdzxt_div_id" > </b>
				</small> 
			</span>
			<div class="navbar-header pull-right" role="navigation" style="float:right;text-align: center; padding:8px">
			 <ul class="nav ace-nav">
<%--				<button id="doChange" data-target="rowView-input"  data-toggle="modal" class="btn btn-inverse" type="button" onclick="doChange()">切换项目</button>--%>
				<button id="closegdzxt" class="btn btn-inverse" type="button " onclick="closeGdzxt()">关闭</button>
			
			</ul>
		</div>
    </div>

	<div class="page-sidebar nav-collapse collapse leftNav"> 
        <ul class="page-sidebar-menu">
            <li>
                <!-- start 左侧点击缩放 -->
                <div class="sidebar-toggler hidden-phone"></div>
                <!-- end 左侧点击缩放 -->
            </li>
			 <!--<app:menusub/> -->
			 <app:wxygcglmenusub/>		
	</div>

	<div class="MainBox">
		<iframe  src="${pageContext.request.contextPath}/jsp/framework/portal/main.jsp?userid=<%=userid%>" id="menuiframe" width="100%"frameborder="0"></iframe>
	</div>
	<form style=" height:0; margin:0;" method="get" action="${pageContext.request.contextPath }/userController/logout" id="logoutForm" >
	</form>
<form role="form" id="querytopform" name="querytopform" style="display: none">
<input class="hidden" id="STATEID" type="text" fieldname="STATE"  value="1" operation="="/>									
<input class="hidden" id="rownum" type="text" fieldname="rownum"  value="5" operation="  &lt;= "/>
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

	   showXtMenu("wndjssgsp");
	});
	function closeGdzxt(){
		window.openen=null;
		window.open('','_self');
		window.close();
	}
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
}

const miao = <%=miao%>;

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
	
	
	//pushInfo = info;
	//pushUrl = url;
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
            popup.ondisplay = function(event) {
            	/**
                setTimeout(function() {
                    event.currentTarget.cancel();
                }, miao * 1000);
            	**/
            }
            popup.onclick = function(){
            	if(pushUrl){
           			document.getElementById("menuiframe").src = "${pageContext.request.contextPath}/"+pushUrl;
            	}
               	this.cancel();
            }
            popup.show();
        } else {
            RequestPermission(notify);
        }
}
$(function() {
	var GDZXT_XM_ID=$("#GDZXT_XM_ID").val();
	//alert(GDZXT_XM_ID);
		if(GDZXT_XM_ID==null||GDZXT_XM_ID==''){
			return false;
		}else{
			$.ajax({
				url :'${pageContext.request.contextPath}/pagegdzxt/getGdxxList',
				data : "GONGCHENG_UID="+GDZXT_XM_ID,
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(response) {
					var obj = convertJson.string2json1(response.msg);
					var resultobj=obj[0];
					var GONGCHENG_UID=resultobj.GONGCHENG_UID;
					var GONGCHENG_NAME=resultobj.GONGCHENG_NAME;
					var SG_COMPANY_NAME=resultobj.SG_COMPANY_NAME;
					$("#GDZXT_XM_NAME").val(resultobj.GONGCHENG_NAME);
					$('#projects_name').val(resultobj.PROJECTS_NAME);
					$('#sg_company_name').val(resultobj.SG_COMPANY_NAME);
					var htmlstr="<b>"+GONGCHENG_NAME+"</b>---- "+SG_COMPANY_NAME;
					$("#gdzxt_div_id").html(htmlstr);
				}	
			});
		}
	
	var defaultUrl="";
	if($("#GC_TYPE").val()=="SJK"){
    	defaultUrl = "jsp/gdzxt/wxy/wxySjk-gcgl-page.jsp";
    }else if($("#GC_TYPE").val()=="GDMB"){
    	defaultUrl = "jsp/gdzxt/wxy/wxyGdmb-gcgl-jcys-index.jsp";
    }else if($("#GC_TYPE").val()=="DZGC"){
    	defaultUrl = "jsp/gdzxt/wxy/wxyDzgc-gcgl-page.jsp";
    }else if($("#GC_TYPE").val()=="JSJ"){
    	defaultUrl = "jsp/gdzxt/wxy/wxyJsj-gcgl-page.jsp";
    }else if($("#GC_TYPE").val()=="MQ"){
    	defaultUrl = "jsp/gdzxt/wxy/wxyMq-gcgl-page.jsp";
    }else if($("#GC_TYPE").val()=="GJG"){
    	defaultUrl = "jsp/gdzxt/wxy/wxyGjg-gcgl-page.jsp";
    }else if($("#GC_TYPE").val()=="WJ"){
    	defaultUrl = "jsp/gdzxt/wxy/wxyWj-gcgl-page.jsp";
    }
	document.getElementById("menuiframe").src = "${pageContext.request.contextPath}/"+defaultUrl;
	
    $("#li_"+$("#GC_TYPE").val()).attr('class','open'); 
});
//----------------------------------消息加载
$(function() {   
	getMess();
});


function openmessage(){
	document.getElementById("menuiframe").src = "${pageContext.request.contextPath}/jsp/weixin/fsmessage-list.jsp";
	//window.location.href= "${pageContext.request.contextPath }/jsp/weixin/fsmessage-list.jsp";   
	//window.open("${pageContext.request.contextPath }/jsp/weixin/fsmessage-list.jsp"); 	
}
window.setInterval(getMess,300000);

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
			}else{
				$("#spanidmes").html(0);
			}
		}
	});
} 

function getMess(){
	getCountMess();//初始化未阅读的信息条数 	
	//var data1 = combineQuery.getQueryCombineData(querytopform, null, null);
	
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
				//alert("------------"+this.TITLE);
					xAlert(this.TITLE,this.CONTENT,1);						
				})
				
			}
		}
	});
	
}

</script>
<input type="text" id="GDZXT_XM_ID" value="${GONGCHENG_UID}" >
<input type="text" id="GC_TYPE" value="${GC_TYPE}" >
<input type="hidden" value="<%=username %>" id="loginUserName">
<input type="hidden"  id="projects_name">
<input type="hidden"  id="sg_company_name">
<input type="hidden" id="GDZXT_XM_NAME" >
</body>
</html>