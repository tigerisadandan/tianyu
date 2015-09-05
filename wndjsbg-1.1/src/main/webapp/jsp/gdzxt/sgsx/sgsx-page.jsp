<!DOCTYPE html>
<%@page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext"%>
<%@page import="com.ccthanking.business.bzwj.GongCheng"%>
<%@page import="com.ccthanking.framework.common.Role"%>
<%@page import="com.ccthanking.framework.handle.ActionContext"%>
<%@page import="com.ccthanking.framework.common.User"%>
<%@page import="com.ccthanking.framework.Constants"%>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>

<%
   String gcid = GongCheng.getGcid();
   String gcname=GongCheng.getGcName();
   String sgname=GongCheng.getSgCompanyName();
   
   User user = RestContext.getCurrentUser();
	Role[] roles=user.getRoles();
	String xzjs="";
	String jbjs="";
	String sljs="";
	for(int i=0;i<roles.length;i++){
	if("内网小组审核人".equals(roles[i].getName())){
	xzjs="内网小组审核人";
	}
	if("内网经办审核人".equals(roles[i].getName())){
	jbjs="内网经办审核人";
	}
	if("内网受理人".equals(roles[i].getName())){
	sljs="内网受理人";
	}
	}
 %>
<app:base/>
<title>施工手续首页</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript">
  var controllername = "${pageContext.request.contextPath}/sgsx/SgsxProjectController";

///<link type="text/css" rel="stylesheet" class="ajax-stylesheet" href="/wndjsbg/sgsx/css/ui.jqgrid.css">
//<link rel="stylesheet" href="/wndjsbg/sgsx/css/ace.min.css" id="main-ace-style">
//<link type="text/css" rel="stylesheet" href="/wndjsbg/css/default.css">

$(function() {
    init();
	setGongCheng_UID();
	
    });
 
function setGongCheng_UID(){//获取当前工程id
	var GongCheng_UID=parent.document.getElementById("GDZXT_XM_ID").value;
	$("#GONGCHENG_UID").val(GongCheng_UID);
}

//页面初始化
function init(){
  $.ajax({
		url:controllername+"/queryYWxxMc",
		type:"post",
		dataType : "json",
		success : function(response){
			var res = dealSpecialCharactor(response.msg);
			var resultmsgobj = convertJson.string2json1(res);
			var thtml = "";
		if(resultmsgobj!=null && resultmsgobj !=''){//说明有数据
		  for(var i=0;i<4;i++){
		   var data=resultmsgobj.response.data[i];
		   if(data.SPYW_UID =="113"){
		     $("#menu_lf").html(data.SPYWMC);
		   }
		   if(data.SPYW_UID =="117"){
		     $("#menu_mid").html(data.SPYWMC);
		   }
		   if(data.SPYW_UID =="118"){
		     $("#menu_rig").html(data.SPYWMC);
		   }
		   if(data.SPYW_UID =="119"){
		     $("#menu_bot").html(data.SPYWMC);
		   }
		   //查询 状态
			getYwType();
		  
		}
			}else{
			
			}
		}
		});
				
}

  function getYwType(){//查询业务状态 
	$.ajax({
		url : controllername+"/queryYwType?gcid="+$("#GONGCHENG_UID").val(),
		data : null,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			if(response.msg!=""&&response.msg!="0"){
				 var obj = convertJson.string2json1(response.msg);
				for(var i =0;i<obj.response.data.length;i++){
				 var data=obj.response.data[i];
				 if(data.SPYW_UID == "114"){//施工申报
				   var status = data.STATUS;
				    if(status=="1"){
						 $("#status_lf").html("已审");
					 }else if(status=="0"){
					     $("#status_lf").html("待审");
					 }else if(status=="-1"){
						 $("#status_lf").html("退回");
					 }else if(status=="40"){
						 $("#status_lf").html("未提交");
					 }else{
						 $("#status_lf").html("未申请");
					 }
				     
				 }else if(data.SPYW_UID == "115"){//市政工程 
				    var status = data.STATUS;
				    if(status=="1"){
						 $("#status_lf").html("已审");
					 }else if(status=="0"){
					     $("#status_lf").html("待审");
					 }else if(status=="-1"){
						 $("#status_lf").html("退回");
					 }else if(status=="40"){
						 $("#status_lf").html("未提交");
					 }else{
						 $("#status_lf").html("未申请");
					 }
				 } else if(data.SPYW_UID == "117"){
				  var status = data.STATUS;
				  if(status=="1"){
						 $("#status_mid").html("已审");
					 }else if(status=="0"){
					     $("#status_mid").html("待审");
					 }else if(status=="-1"){
						 $("#status_mid").html("退回");
					 }else if(status=="40"){
						 $("#status_mid").html("未提交");
					 }else{
						 $("#status_mid").html("未申请");
					 }
				 }else if(data.SPYW_UID == "118"){
				   var status = data.STATUS;
				     if(status=="1"){
						 $("#status_rig").html("已审");
					 }else if(status=="0"){
					     $("#status_rig").html("待审");
					 }else if(status=="-1"){
						 $("#status_rig").html("退回");
					 }else if(status=="40"){
						 $("#status_rig").html("未提交");
					 }else{
						 $("#status_rig").html("未申请");
					 }
				 }else if(data.SPYW_UID == "119"){
				   var status = data.STATUS;
				 if(status=="1"){
						 $("#status_bot").html("已审");
					 }else if(status=="0"){
					     $("#status_bot").html("待审");
					 }else if(status=="-1"){
						 $("#status_bot").html("退回");
					 }else if(status=="40"){
						 $("#status_bot").html("未提交");
					 }else{
						 $("#status_bot").html("未申请");
					 }
				 }
				}
				
			}
			
		}
	});	
	
}

//判断 是施工申报还是市政
var type;
function initSbOrSz(){  
	$.ajax({
		url : controllername+"/querySgCommpany?gcid="+$("#GONGCHENG_UID").val(),
		data : null,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			if(response.msg!=""&&response.msg!="0"){
				 var obj = convertJson.string2json1(response.msg);
				 type=obj.response.data[0].SFSZGC; // 0施工申报  1市政工程
			     if(type=="0"){
					 type="114";
				 }else if(type=="1"){
					 type="115";
				 }
			}
			
		}
	});	
	return type;
}
// 企业资格审查
function shouxu(){
  var spywName = "";
  var type = initSbOrSz();
  if(type=="114"){
     spywName="施工申报";
  }else{
     spywName="市政工程申报";
  }
  
  var title01 = "实例业务流转>详细信息";

  if($("#status_lf").text()=="退回"){
     $(window).manhuaDialog({"title":title01,"type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/sgsx/gc-ywlz-sp-back.jsp?spyw_uid="+type+"&gongcheng_uid="+$("#GONGCHENG_UID").val(),"modal":"1"});
  }else if($("#status_lf").text()=="已审"){
   $(window).manhuaDialog(
   {"title":title01,"type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/sgsx/gc-ywlz-sp-view.jsp?spyw_uid="+type+"&gongcheng_uid="+$("#GONGCHENG_UID").val(),"modal":"1"});
  }else if($("#status_lf").text()=="待审"){
   $(window).manhuaDialog(
   {"title":"施工手续>审批","type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/sgsx/gc-ywlz-total-sh.jsp?gongcheng_uid="+$("#GONGCHENG_UID").val()+"&spyw_uid="+type+"&type="+"ywlzsh","modal":"1"});
  }else{
    xAlert("您好,手续未提交或未申请!");
 }
  
 
}
//人脸识别118
function confirm(){
  var title01 = "实例业务流转>详细信息";
  if($("#status_rig").text()=="退回"){
     $(window).manhuaDialog({"title":title01,"type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/sgsx/gc-ywlz-sp-back.jsp?spyw_uid="+118+"&gongcheng_uid="+$("#GONGCHENG_UID").val(),"modal":"1"});
  }else if($("#status_rig").text()=="已审"){
   $(window).manhuaDialog({"title": title01,"type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/sgsx/gc-ywlz-sp-view.jsp?spyw_uid="+118+"&gongcheng_uid="+$("#GONGCHENG_UID").val(),"modal":"1"});
  }else if($("#status_rig").text()=="待审"){
   $(window).manhuaDialog({"title":"施工手续>审批","type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/sgsx/gc-ywlz-total-sh.jsp?gongcheng_uid="+$("#GONGCHENG_UID").val()+"&spyw_uid="+118+"&type="+"ywlzsh","modal":"1"});
  }else{
    xAlert("您好,手续未提交或未申请!");
 }
}
//开工前 安全117
function safty(){
  var title01 = "实例业务流转>详细信息";
  if($("#status_mid").text()=="退回"){
     $(window).manhuaDialog({"title":title01,"type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/sgsx/gc-ywlz-sp-kagqBack.jsp?spyw_uid="+117+"&gongcheng_uid="+$("#GONGCHENG_UID").val(),"modal":"1"});
  }else if($("#status_mid").text()=="已审"){
   $(window).manhuaDialog({"title":title01,"type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/sgsx/gc-ywlz-sp-kgaqView.jsp?spyw_uid="+117+"&gongcheng_uid="+$("#GONGCHENG_UID").val(),"modal":"1"});
  }else if($("#status_mid").text()=="待审"){
   $(window).manhuaDialog({"title":"施工手续>审批","type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/sgsx/gc-ywlz-sp-kagqsh.jsp?gongcheng_uid="+$("#GONGCHENG_UID").val()+"&spyw_uid="+117+"&type="+"ywlzsh","modal":"1"});
  }else{
    xAlert("您好,手续未提交或未申请!");
 }
}
//建筑工程 119
function project(){
  var title01 = "实例业务流转>详细信息";
  if($("#status_bot").text()=="退回"){
     $(window).manhuaDialog({"title":title01,"type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/sgsx/gc-ywlz-sp-ProjectBack.jsp?spyw_uid="+119+"&gongcheng_uid="+$("#GONGCHENG_UID").val(),"modal":"1"});
  }else if($("#status_bot").text()=="已审"){
   $(window).manhuaDialog({"title": title01,"type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/sgsx/gc-ywlz-projectView.jsp?spyw_uid="+119+"&gongcheng_uid="+$("#GONGCHENG_UID").val(),"modal":"1"});
  }else if($("#status_bot").text()=="待审"){
   $(window).manhuaDialog({"title":"施工手续>审批","type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/sgsx/gc-ywlz-sp-projectsh.jsp?gongcheng_uid="+$("#GONGCHENG_UID").val()+"&spyw_uid="+119+"&type="+"ywlzsh","modal":"1"});
  }else{
    xAlert("您好,手续未提交或未申请!");
 }
}

//padding-top:100px;padding-left:80px;padding-right:50px;padding-bottom:80px
//javascript:bootbox.alert('未选择项目！');
</script>
<style type="text/css">
    #menu_left{
      position:absolute; 
      top:90px; 
      left:90px;
      width:100px;
      height:60px;
  
      
    }
     #menu_middle{
      position:absolute; 
      top:90px; 
      left:390px;
      width:100px;
      height:60px;
     
     
    }
     #menu_right{
     position:absolute; 
     top:90px;  
     left:690px;
     width:100px;
     height:60px;
     
    }
     #menu_bottom{
     position:absolute; 
     top:300px; 
     left:390px; 
     width:100px;
     height:60px;
      
    }
</style>
<body>
 <div class="col-xs-12" style=";text-align:center">
    <form method="post" id="queryForm">
			<table class="B-table" width="100%" id="condition">
				<!--可以再此处加入hidden域作为过滤条件 -->
				<tr style="display: none;">
					<td class="right-border bottom-border"></TD>
					<td class="right-border bottom-border">
						<input type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>
						<input type="text" class="span12" kind="text" id="GONGCHENG_UID" fieldname="GONGCHENG_UID" operation="=" />
					</td>
				</tr>
			</table>
	</form>
    <i class="#ace-icon glyphicon glyphicon-arrow-down bigger-200" style="color: rgb(255, 168, 59);font-weight: bold;"></i>
	<div id="menu_left" class="alert alert-info sx-view" style="padding-top:20px;width:20%">
		<a href="#" onClick="shouxu()" style="color:#9C6531" id="menu_lf"></a>
		<br/>
		<span style="text-align:center" id="status_lf">未填报</span>
	</div>
	<i class="#ace-icon glyphicon glyphicon-arrow-down bigger-200" style="color: rgb(255, 168, 59);font-weight: bold;"></i>
	<div id="menu_middle" class="alert alert-info sx-view"  style="padding-top:20px;width:20%">
		<a href="#" onClick="safty();" style="color: #9C6531" id="menu_mid"></a>
		<br/>
		<br/>
		<span style="text-align:center;" id="status_mid">未填报</span>
	</div>
	<i class="#ace-icon glyphicon glyphicon-arrow-down bigger-200" style="color: rgb(255, 168, 59);font-weight: bold;"></i>
	<div id="menu_right" class="alert alert-info sx-view" style="padding-top:20px;width:20%">
		<a href="#" onClick="confirm();" style="color: #9C6531" id="menu_rig"></a>
		<br/>
		<br/>
		<span style="text-align:center" id="status_rig">未填报</span>
	</div>
	<i class="#ace-icon glyphicon glyphicon-arrow-down bigger-200" style="color: rgb(255, 168, 59);font-weight: bold;"></i>
	
	<div>
	  <!--  <img id="pictures" src="../img/wstemplate/123.png"/>
	  <canvas width="1280" height="891" style="color: rgb(255, 168, 59);font-weight: bold;position: absolute; top: 0px; left: 0px; z-index: 100; opacity: 1;"></canvas>
	-->
	</div>
	<div id="menu_bottom" class="alert alert-info sx-view" style="padding-top:20px;width:20%">
		<a href="#" onClick="project();" style="color: #9C6531" id="menu_bot"></a>
		<br/>
		<br/>
		<span style="align:center" id="status_bot">未填报</span>
	</div>
 </div>
 
</div><!-- /.main-container color:black;-->  
<div class="footer" style="padding-top:800px;">
	<div class="footer-inner">
		<!-- #section:basics/footer
		<div class="footer-content" style="padding:0px 8px;text-align:center;">
			<span class="bigger-120" style="color:#ddd;">
				<span class="blue bolder"  >无锡新区建设环保局</span>
				版权所有 &copy; 2014-2015
			</span>
			&nbsp; &nbsp;
		</div>
 -->
		
	</div>
</div>
</body>
</head>
</html>