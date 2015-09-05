<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp" %>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>施工子系统首页</title>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/ywlz/css/default.css"> </LINK>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script src="${pageContext.request.contextPath }/js/TweenMax/TweenMax.min.js"></script>

<style type="text/css">
  #jsdwxx,#sgdwxx,#jldwxx{  	
  	margin-top: 20px;
  	margin-left: 15px;
  	float: left;
  	width: 99%;
  }
  
  #dqxmzt{
  margin-top: 20px;
  margin-left: 15px;
  float:left;
  width: 48%;
  }
  
  #sxsbqk{
  margin-top: 20px;
  margin-left: 15px;
  float:right;
  width: 48%;
  }
  
   .h4-style{
   display: block;
   float: left;
   line-height: 30px;   
   }
   
   #projectstatus{
   position:relative;
   color:white;   
   cursor: pointer;
   }
   
   #projectstatus:HOVER{
   text-decoration: none;
   }
   
   #projectstatus span{
   letter-spacing: 10px;
   }
   
   #select-status{
   position: absolute;
   display:none;
   width:100%;
   left: 0px;
   top:35px;
   background-color: #F0F4F9;
   }
   
   #select-status ul{
   	margin-top: 0px;
   }
   
   #select-status ul li{
   	color:#333333;
   	font-weight:normal;
    line-height:25px;
   	letter-spacing: 10px;   	
   }
   
   .DisplayBar{
   position:relative;
   float:left;
   width:100px;
   height: 20px;
   border: 1px black solid;
   color: black;
   text-align: center;
   font-weight: bold;
   cursor:pointer;
   overflow: hidden;
   }
   
   .DisplayBar span{
   	position:absolute;
   	width:100px;
   	left:0px;
   	z-index: 10;
   }
      
   .DisplayBar div{
   position:absolute;
   left:0px;
   top:0px;
   width:0px;
   height:100%;
   z-index: 1;
   }
   
   .topbar{
   	margin-top: 8px;
   	margin-left: 20px;
   }
   
   .dt_information{
   color: blue;
   font-weight: bold;
   }
   
   #h4_button{
   float:right;
   margin-top: 5px;   
   }
   
   .tdheight tr td{
   	height: 30px;
   }
</style>
<script type="text/javascript" charset="utf-8">
var controllername = "${pageContext.request.contextPath}/sgzxt/getZxtIndexInformationController";

var GongCheng_UID=parent.document.getElementById("GDZXT_XM_ID").value;

$(function() {
	init();
});	

function init(){
	
	indexInformation();	
	
	initDisplayBar();
	
	setStatus();
	
	$("div[name = 'DisplayBar']").click(function(){
		var type =  this.attributes.getNamedItem("duixiangtype").value;
		var uid =  this.attributes.getNamedItem("duixiangId").value;
		if(uid==""){
			alert("对不起，该对象ID不存在，请联系管理员核对");
			return;
		}
		if(type=="JS"||type=="SG"||type=="JL"){
			window.open("${pageContext.request.contextPath }/jsp/gdzxt/index/qiye-xypf.jsp?COMPANY_UID="+uid+"&type="+type);
		}else if(type=="SGRY"||type=="JLRY"){
			window.open("${pageContext.request.contextPath }/jsp/gdzxt/index/geren-xypf.jsp?person_uid="+uid+"&type="+type);		
			}		
	});
	
	$("#GC_SCORE").bind("click",function(){
		$(window).manhuaDialog({"title":"项目部月度分值","type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/sgsx/ztzrgl-gcscore.jsp?GONGCHENG_UID="+GongCheng_UID,"modal":"2"});	
	});
	
	$("#sgryxx").bind("click",function(){
		$(window).manhuaDialog({"title":"施工单位人员信息","type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/sgsx/ztzrgl-sgdw-ryxx.jsp?GONGCHENG_UID="+GongCheng_UID,"modal":"1"});	
	});
	
	$("#jlryxx").bind("click",function(){
		$(window).manhuaDialog({"title":"监理单位人员信息","type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/sgsx/ztzrgl-jldw-ryxx.jsp?GONGCHENG_UID="+GongCheng_UID,"modal":"1"});	
	});
	
	$("#dtkh").bind("click",function(){
		$(window).manhuaDialog({"title":"动态考核","type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/sgsx/ztzrgl-dtkh.jsp?GONGCHENG_UID="+GongCheng_UID,"modal":"2"});
	});

}


function indexInformation(){
	$.ajax({
		url : controllername+"?query&GongCheng_UID="+GongCheng_UID,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var msg = response.msg;
			var msgarr =new Array();
			msgarr = msg.split("&&&&");
			//---------------当前工程信用分值
			var GC_SCORE =  new Object();
			if(msgarr[12]!="0"){
				GC_SCORE = defaultJson.dealResultJson(msgarr[12]);
			}else {
				GC_SCORE.SCORE = "";
			}
			
			$("#GC_SCORE").html(GC_SCORE.SCORE);
			//---------------处理当前项目状态信息
			var dqxmxx1 = new Object();
			var dqxmxx2 = new Object();
			if(msgarr[0]!="0"){
				dqxmxx1 = defaultJson.dealResultJson(msgarr[0]);
			}else {
				dqxmxx1.AJY = "";
				dqxmxx1.ZJY = "";
			}
			
			if(msgarr[1]!="0"){
				dqxmxx2 = defaultJson.dealResultJson(msgarr[1]);
			}else {
				dqxmxx2.JDXZ = "";
			}

			
			$("#projectstatus span").html("&nbsp;"+dqxmxx1.GC_STATUS);
			$("#AJY").html(dqxmxx1.AJY);
			$("#ZJY").html(dqxmxx1.ZJY);
			$("#JDXZ").html(dqxmxx2.JDXZ);
			//---------------处理手续审批情况
			var sxsp1 = new Object();
			var sxsp2 = new Object();
			var sxsp3 = new Object();
			sxsp1 = SetObject(sxsp1,msgarr,2);
			sxsp2 = SetObject(sxsp2,msgarr,3);
			sxsp3 = SetObject(sxsp3,msgarr,4);
			var  STATUS1 = SetStatus(sxsp1.STATUS, STATUS1);
			var  STATUS2 = SetStatus(sxsp2.STATUS, STATUS2);
			var  STATUS3 = SetStatus(sxsp3.STATUS, STATUS3);			
			$("#sxsp1").html(STATUS1);
			$("#sxsp2").html(STATUS2);
			$("#sxsp3").html(STATUS3);
			
			
			if(sxsp1.ACT_END_DATE!=""){
				$("#sxsp1").attr("title","办结日期:"+sxsp1.ACT_END_DATE);
			}
			
			if(sxsp2.ACT_END_DATE!=""){
				$("#sxsp2").attr("title","办结日期:"+sxsp2.ACT_END_DATE);
			}
			
			if(sxsp3.ACT_END_DATE!=""){
				$("#sxsp3").attr("title","办结日期:"+sxsp3.ACT_END_DATE);
			}
			
			if(sxsp1.ACT_END_DATE!=""){
				$("#sxsp3").attr("title",sxsp1.ACT_END_DATE);
			}
			
			//---------------处理建设单位信息
			var jsdw1 = new Object();
			var jsdw2 = new Object();
			var jsdw3 = new Object();
			if(msgarr[5]!="0"){
				jsdw1 = defaultJson.dealResultJson(msgarr[5]);
			}else {
				jsdw1.PROJECTS_NAME="";
				jsdw1.PLAN_KG_DATE="";
				jsdw1.JG_DATE="";
				jsdw1.LIANXIREN="";
				jsdw1.LIANXIREN_PHONE="";
				jsdw1.ZONG_TOUZI="";
				jsdw1.JIANZHU_MIANJI="";
			}
			
			if(msgarr[6]!="0"){
				jsdw2 = defaultJson.dealResultJson(msgarr[6]);
			}else {
				jsdw2.jsdw2 = "";
				jsdw2.SCORE = "";
				jsdw2.JS_COMPANY_UID = "";
			}
			
			if(msgarr[7]!="0"){
				jsdw3 = defaultJson.dealResultJson(msgarr[7]);
			}else{
				jsdw3.KCDWNAME="";
				jsdw3.SJDWMC="";
			}			
			

			var jsdwxxHtml = "<tr><td width=\"15%\" class=\"text-right\">建设单位：</td><td width=\"35%\">"+jsdw2.COMPANY_NAME+"</td><td width=\"20%\" class=\"text-right\">信用分值：</td>"+
				"<td width=\"30%\"><div name=\"DisplayBar\" duixiangtype = \"JS\" duixiangId = \""+jsdw2.JS_COMPANY_UID+"\"  class=\"DisplayBar\" title=\"点击查看信用分值信息\"><span>"+jsdw2.SCORE+"</span><div></div></div></td></tr>"+
				"<tr><td class=\"text-right\">项目名称：</td><td colspan=\"3\">"+jsdw1.PROJECTS_NAME+"</td></tr>"+
				"<tr><td class=\"text-right\">勘察单位：</td><td>"+jsdw3.KCDWNAME+"</td><td class=\"text-right\">设计单位：</td><td><span>"+jsdw3.SJDWMC+"</span></td></tr>"+
				"<tr><td class=\"text-right\">开工时间：</td><td>"+jsdw1.PLAN_KG_DATE+"</td><td class=\"text-right\">竣工时间：</td><td><span>"+jsdw1.JG_DATE+"</span></td></tr>"+
				"<tr><td class=\"text-right\">联系人：</td><td>"+jsdw1.LIANXIREN+"</td><td class=\"text-right\">联系电话：</td><td><span>"+jsdw1.LIANXIREN_PHONE+"</span></td></tr>"+
				"<tr><td class=\"text-right\">项目造价：</td><td><span>"+jsdw1.ZONG_TOUZI+"</span> (万元)</td><td class=\"text-right\">建筑面积：</td><td><span>"+jsdw1.JIANZHU_MIANJI+"</span> (平方米)</td></tr>";
			$("#jsdw-table").html(jsdwxxHtml);
			
			
			
			//---------------施工单位信息
			var sgdw1 = new Object();
			var sgdw2 = new Object();
			if(msgarr[8]!="0"){
				sgdw1 = defaultJson.dealResultJson(msgarr[8]);
			}else {
				sgdw1.COMPANY_NAME = "";
				sgdw1.SCORE = "";
				sgdw1.SG_COMPANY_UID = "";
				sgdw1.ZHENGSHU_CODE = "";
				sgdw1.ZHENGSHU_DENGJI = "";
				sgdw1.ANQUAN_CODE = "";
				sgdw1.LIANXIREN = "";
				sgdw1.LIANXIREN_MOBILE = "";
			} 
			
			if(msgarr[9]!="0"){
				sgdw2 = defaultJson.dealResultJson(msgarr[9]);
			}else {
				sgdw2.PERSON_NAME = "";
				sgdw2.SCORE = "";
				sgdw2.XMJL_UID = "";
				sgdw2.PHONE = "";
				sgdw2.ZHICHENG_CODE = "";
			} 
			
			var sgdwxxHtml = "<tr><td width=\"15%\" class=\"text-right\">施工单位：</td><td width=\"35%\">"+sgdw1.COMPANY_NAME+"</td><td width=\"20%\" class=\"text-right\">信用分值：</td>"+
				"<td width=\"30%\"><div name=\"DisplayBar\" duixiangtype = \"SG\" duixiangId = \""+sgdw1.SG_COMPANY_UID+"\"  class=\"DisplayBar\"  title=\"点击查看信用分值信息\"><span>"+sgdw1.SCORE+"</span><div></div></div></td></tr>"+
  				"<tr><td class=\"text-right\">资质证书号：</td><td>"+sgdw1.ZHENGSHU_CODE+"</td><td class=\"text-right\">资质类别等级：</td><td><span>"+sgdw1.ZHENGSHU_DENGJI+"</span></td></tr>"+
  				"<tr><td class=\"text-right\">项目经理：</td><td>"+sgdw2.PERSON_NAME+"</td><td class=\"text-right\">信用分值：</td>"+
  				"<td><div name=\"DisplayBar\"  duixiangtype = \"SGRY\" duixiangId = \""+sgdw2.XMJL_UID+"\"  class=\"DisplayBar\"  title=\"点击查看信用分值信息\"><span>"+sgdw2.SCORE+"</span><div></div></div></td></tr>"+
  				"<tr><td class=\"text-right\">安全生产许可证号：</td><td colspan=\"3\">"+sgdw1.ANQUAN_CODE+"</td></tr>"+
  				"<tr><td class=\"text-right\">项目经理电话：</td><td>"+sgdw2.PHONE+"</td><td class=\"text-right\">联系人：</td><td><span>"+sgdw1.LIANXIREN+"</span></td></tr>"+
  				"<tr><td class=\"text-right\">项目经理证号：</td><td>"+sgdw2.ZHICHENG_CODE+"</td><td class=\"text-right\">联系电话：</td><td><span>"+sgdw1.LIANXIREN_MOBILE+"</span></td></tr>";
  			$("#sgdw-table").html(sgdwxxHtml);
  			
  			
  			
  		//---------------监理单位信息
  		var jldw1 = new Object();
  		var jldw2 = new Object();
  		if(msgarr[10]!="0"){
  			 jldw1 = defaultJson.dealResultJson(msgarr[10]);
  		}else{
  			jldw1.JLDW_NAME = "";
  			jldw1.SCORE = "";
  			jldw1.JLDW_UID = "";
  		}
  		if(msgarr[11]!="0"){
  			 jldw2 = defaultJson.dealResultJson(msgarr[11]);
  		}else{
  			jldw2.ZJ_NAME = "";
  			jldw2.SCORE = "";
  			jldw2.ZJ_UID = "";
  			jldw2.ZHENGSHU_CODE = "";
  			jldw2.PHONE = "";
  		}
  		
  		var jldwxxHtml = ""+
  		"<tr><td width=\"15%\" class=\"text-right\">监理单位：</td><td width=\"35%\">"+jldw1.JLDW_NAME+"</td><td width=\"20%\" class=\"text-right\">信用分值：</td>"+
  		"<td width=\"30%\"><div name=\"DisplayBar\" duixiangtype = \"JL\" duixiangId = \""+jldw1.JLDW_UID+"\" class=\"DisplayBar\"  title=\"点击查看信用分值信息\"><span>"+jldw1.SCORE+"</span><div></div></div></td>"+
      	"</tr>"+
      	"<tr><td class=\"text-right\">总监：</td><td>"+jldw2.ZJ_NAME+"</td><td class=\"text-right\">信用分值：</td>"+
      		"<td><div name=\"DisplayBar\" duixiangtype = \"JLRY\" duixiangId = \""+jldw2.ZJ_UID+"\"  class=\"DisplayBar\"  title=\"点击查看信用分值信息\"><span>"+jldw2.SCORE+"</span><div></div></div></td></tr>"+
      	"<tr><td class=\"text-right\"> 总监证书编号：</td><td>"+jldw2.ZHENGSHU_CODE+"</td><td class=\"text-right\">总监联系电话：</td><td><span>"+jldw2.PHONE+"</span></td></tr>";
  		$("#jldw-table").html(jldwxxHtml);	
  		
		}
	
	});	
}

function SetObject(Obj,msgArr,index){
	if(msgArr[index]!="0"){
		Obj = defaultJson.dealResultJson(msgArr[index]);		
	}else{
		Obj.STATUS = "-100";
		Obj.ACT_END_DATE = "";
	}
	return Obj;
}

function SetStatus(SxspStatus,returnStatus){
	if(SxspStatus=="0"){
		returnStatus = "流转中";
	}else if(SxspStatus=="1"){
		returnStatus = "已办结";
	}else if(SxspStatus=="-1"){
		returnStatus = "未通过";
	}else {
		returnStatus = "未申请";
	}
	return returnStatus;
}

function setStatus(){
	$("#projectstatus").click(function(){
		if($("#select-status").is(":hidden")){
			$("#select-status").slideDown();
			TweenMax.to($(this).find("img").eq(0), .5, {rotation:-90,repeat:0});
		}else if($("#select-status").is(":visible")){
			TweenMax.to($(this).find("img").eq(0), .5, {rotation:0,repeat:0});
			$("#select-status").slideUp();
		}		
	});	
	
	$("#select-status li").bind("mouseover",function(){
		$(this).css("background-color","#FEE188");
	});
	
	
	$("#select-status li").bind("mouseout",function(){
		$(this).css("background-color","#F0F4F9");
	});
	
	$("#select-status li").bind("click",function(){
		var selected = $(this).html().replace("&nbsp;","");
		if(window.confirm("确定要将项目状态修改为:"+selected+"吗？")){
			//do confirm is yes	
			$("#projectstatus span").eq(0).html("&nbsp"+selected);
			return true;
		}else{

			return false;
		}
	});
}

function initDisplayBar(){
	$(".DisplayBar").each(function(){
		var number=$(this).find("span").eq(0).html();
		if(number==null||number.trim()==""){
			$(this).css("display","none");
			return;
		}
		
		if(number>=95){
			$(this).find("div").eq(0).css("background-color","#00FF00");
		}else if(number>=85&&number<95){
			$(this).find("div").eq(0).css("background-color","#FFFF00");
		}else if(number>70&&number<85){
			$(this).find("div").eq(0).css("background-color","#FF0000");
		}else if(number>=0&&number<=70){
			$(this).find("div").eq(0).css("background-color","#525252");
		}
		
		
		if(number>100){
			TweenMax.to($(this).find("div").eq(0), 5, {width:100,ease:Expo.easeOut,opacity:1});
		}else if(number>=0&&number<=100){
			TweenMax.to($(this).find("div").eq(0), 5, {width:number,ease:Expo.easeOut,opacity:1});
		}		
								
	});
}
</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid"> 
	<!-- 当前项目状态:开始 --> 
	<div id="dqxmzt">		
		<table class="content" width="100%">
		<tr><td class="yw-title">
          <h4 class="h4-style">当前项目状态：<a id="projectstatus" title="点击可修改项目状态"><span></span><img src="${pageContext.request.contextPath}/images/gdzxt/arrow.png"/>
          <div id="select-status">
          	<ul>
          		<li id="status-zj">&nbsp在建</li>
          		<li id="status-wg">&nbsp完工</li>
          		<li id="status-jg">&nbsp竣工</li>
          	</ul>
          </div></a></h4>
          <div class="DisplayBar topbar" title="点击查看信用分值信息" id="GC_DisplayBar"><span id="GC_SCORE"></span><div></div></div>                 
          </td></tr>
          <tr><td></td>
          </tr>
          <tr><td>
          	<table width="100%" class="tdheight">
          	<tr><td width="20%" class="text-right">监督小组：</td><td><span class="dt_information" id="JDXZ"></span></td></tr>
          	<tr><td class="text-right">安监人员：</td><td><span class="dt_information" id="AJY"></span></td></tr>
          	<tr><td class="text-right">质监人员：</td><td><span class="dt_information" id="ZJY"></span></td></tr>
          	</table>
          
          </td></tr>
        </table>        
	</div>
	
	
	<!-- 手续审批情况:开始 --> 
	<div id="sxsbqk">		
		<table class="content" width="100%">
		<tr><td class="yw-title">
          <h4 class="h4-style">手续审批情况</h4>
         	</td>
        </tr>
        <tr><td>
        <table width="100%" class="tdheight">
          	<tr><td class="text-right" width="50%">建筑工程施工注册证及安监备案：</td><td class="dt_information" id="sxsp1"></td></tr>
          	<tr><td class="text-right">安全监督受监通知书：</td><td><span  class="dt_information" id="sxsp2"></span></td></tr>
          	<tr><td class="text-right">建设工程施工许可证：</td><td><span  class="dt_information" id="sxsp3"></span></td></tr>
        </table>
        </td></tr>
        
        </table>       
    </div>
          
    <!-- 建设单位信息：开始 --> 
	<div id="jsdwxx">
			
		<table class="content" width="100%">
		<tr><td class="yw-title">
          <h4 class="h4-style">建设单位信息</h4>
         	</td>
        </tr>
        <tr><td>
        <table width="100%" class="tdheight" id="jsdw-table">
        <%-- 最终会形成的样式(可删除)
          	<tr><td width="10%" class="text-right">建设单位：</td><td width="30%"> 无锡明朗置业有限公司</td><td width="10%" class="text-right">信用分值：</td>
          	<td width="50%"><div class="DisplayBar" title="点击查看信用分值信息"><span>93</span><div></div></div></td>
          	</tr>
          	<tr><td class="text-right">项目名称：</td><td colspan="3">新建办公楼、综合配套楼、分拣车间一、分拣车间二、仓库</td></tr>
          	<tr><td class="text-right">勘察单位：</td><td> 无锡水文工程地质勘察院</td><td class="text-right">设计单位：</td><td><span > 浙江中房建筑设计研究院有限公司</span></td></tr>
          	<tr><td class="text-right">开工时间：</td><td><input type="text" fieldname="KG_DATE"/></td><td class="text-right">竣工时间：</td><td><span fieldname="JG_DATE"></span></td></tr>
          	<tr><td class="text-right">联系人：</td><td> 黄于斌</td><td class="text-right">联系电话：</td><td><span > 13814216759</span></td></tr>
          	<tr><td class="text-right">项目造价：</td><td><span>111111111111</span>万元</td><td class="text-right">建筑面积：</td><td><span fieldname="MIANJI"></span>平方米</td></tr>
        --%>
        </table>
        </td></tr>
        
        </table> 
           
    </div>      
    
      <!-- 施工单位信息(总包)：开始 --> 
	<div id="sgdwxx">		
		<table class="content" width="100%">
		<tr><td class="yw-title">
          <h4 class="h4-style">施工单位信息(总包)</h4>
          <div id="h4_button" class="pull-right">
          <button id="dtkh" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">动态考核</button>
          <button id="xcsp" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">现场视频</button>
          <button id="sgryxx" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">人员信息</button>
          </div>
         	</td>
        </tr>
        <tr><td>
        <table width="100%" class="tdheight" id="sgdw-table">
        <%-- 最终会形成的样式(可删除)
          	<tr><td width="15%" class="text-right">施工单位：</td><td width="35%"></td><td width="20%" class="text-right">信用分值：</td>
          		<td width="30%"><div class="DisplayBar"  title="点击查看信用分值信息"><span></span><div></div></div></td>
          	</tr>          	
          	<tr><td class="text-right">资质证书号：</td><td></td><td class="text-right">资质类别等级：</td><td><span></span></td></tr>
          	<tr><td class="text-right">项目经理：</td><td></td><td class="text-right">信用分值：</td>
          		<td><div class="DisplayBar"  title="点击查看信用分值信息"><span></span><div></div></div></td>
          	</tr>
          	<tr><td class="text-right">安全生产许可证号：</td><td colspan="3"></td></tr>
          	<tr><td class="text-right">项目经理电话：</td><td></td><td class="text-right">联系人：</td><td><span ></span></td></tr>
          	<tr><td class="text-right">项目经理证号：</td><td></td><td class="text-right">联系电话：</td><td><span ></span></td></tr>
         --%> 
        </table>
        </td></tr>
        </table> 
            
    </div> 
    
    
      <!-- 监理单位信息：开始 --> 
	<div id="jldwxx">		
		<table class="content" width="100%">
		<tr><td class="yw-title">
          <h4 class="h4-style">监理单位信息</h4>
           <div id="h4_button" class="pull-right">
           	<button id="jlryxx" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">人员信息</button>
          </div>
         	</td>
        </tr>
        <tr><td>
        <table width="100%" class="tdheight" id="jldw-table">
        <%-- 最终会形成的样式(可删除)
          	<tr><td width="15%" class="text-right">监理单位：</td><td width="35%"></td><td width="20%" class="text-right">信用分值：</td>
          		<td width="30%"><div class="DisplayBar"  title="点击查看信用分值信息"><span></span><div></div></div></td>
          	</tr>
          	<tr><td class="text-right">总监：</td><td> </td><td class="text-right">信用分值：</td>
          		<td><div class="DisplayBar"  title="点击查看信用分值信息"><span></span><div></div></div></td></tr>
          	<tr><td class="text-right"> 总监证书编号：</td><td></td><td class="text-right">总监联系电话：</td><td><span></span></td></tr>
        --%>
        </table>
        </td></tr>
        
        </table>       
    </div> 
    
  </div>
</div>
  
</body>
<script>
</script>
</html>