<!DOCTYPE html>
<%@page import="com.sun.org.apache.xalan.internal.xsltc.compiler.sym"%>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>首页</title>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/ywlz/css/default.css"> </LINK>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script src="${pageContext.request.contextPath }/FusionCharts/JSClass/FusionCharts.js"></script>
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/date/moment.min.js"></script>
<mce:script language="JavaScript" src="${pageContext.request.contextPath }/FusionCharts/JSClass/FusionCharts.js" mce_src="${pageContext.request.contextPath }/FusionCharts/JSClass/FusionCharts.js"></mce:script>

<style type="text/css">
	.container_x{
	margin-left: 40px;
	color: #333333;
	}
	
	#dtglxx,#xmxxhz,#wxyxxhz,#qtxxhz,#qzjxzh{
	margin-top: 20px;
	min-width:900px;
	}
	
	#dtglxx_content,#xmxxhz_content,#wxyxxhz_content,#qtxxhz_content,#qzjxzh_content{
	padding-left: 40px;
	}
	
	#wxyxxhz_content{
	padding-top: 20px;
	}
	
	#xmxxhz-text,#qtxxhz-text{
	margin-top: 20px;
	}	
	
	#dtglxx-top{
	float: left;
	}
	#dtglxx-text,#kqxx-text,#kqdsxx-text,#xmxxhz-text,#qtxxhz-text p{	
	font-family: SimYou,Microsoft YaHei,"Helvetica Neue", Helvetica, Arial, sans-serif;
	font-size:15px;
	color:#333333;
	line-height: 18px;
	}
	
	.container_x a{
	text-decoration: underline;
	cursor: pointer;	
	}
	
	#MSColumns{
	width:100%;
	height: 390px;
	text-align:center;
	}
	
	.chart-div{
	float:left;
	height: 350px;
	text-align: center;
	}
	
	#dtglxx_content h4{
	margin-top: 20px;
	}
	
	.totals1{
	color:#00BF00;
	}
	
	.totals2{
	color:#00BF00;
	}
	
	#kqxx-table td{
	height: 25px;
	}
	
	#inner-border{
	padding:10px 10px 10px 10px;	
	border: 5px #DDDDFF solid;
	}
	
	#information-float{
	float: left;
	}
	
	#information{
	position: relative;
	display: block;
	float:left;
	width:20px;
	height:20px;
	cursor: pointer;
	}
	
	#information-div{
	position: absolute;
	display:none;
	bottom:22px;
	left:22px;
	border: 1px #0866C6 solid;
	background-color:#FFFFFF;
	z-index: 999;
	}
	
	#information-table {
	background-color:#F5F7FB;
	width: 360px;
	}

	#information-table th{
	background-color:#DDDDFF;
	border: 1px #0866C6 solid;
	}
	
	#information-table td{
	border: 1px #0866C6 solid;
	text-align: center;
	font-size: 13px;
	height: 20px;
	}
	
	#qzjx-table tr td{
	text-align: center;
	line-height: 25px;
	border: 1px #DDDDDD solid;
	}		
		
	#qzjx-table tr th{
	text-align: center;
	background-color: #F6F6F6;
	line-height: 32px;
	border: 1px #DDDDDD solid;
	
	}
	
	#qzjx-table tr td a{
	text-decoration: none;	
	}
	
	.shenhequanxian{
	color: #FF0000;
	}	
	
	.important-info{
	color:#FF0000;
	}
	
	.dtglxx-td{
	display:block;
	height:40px;
	overflow: hidden;
	border-bottom: 1px #FFFFFF solid;
	}
	
	.split-iron-text{
	float: right;
	margin-top:5px;
	margin-right:10px;
	width: 40px;
	height: 41px;
	background-color: #FFFFFF;
	background-image: url("${pageContext.request.contextPath }/images/dtgl_index/text_checked.png");
	background-repeat: no-repeat;
	background-position: center;
	border-radius:5px;
	}
	
	.split-iron-pic{
	float: right;
	margin-right:10px;
	margin-top:5px;
	width: 40px;
	height: 41px;
	background-color: #2074CA;
	background-image: url("${pageContext.request.contextPath }/images/dtgl_index/pic_uncheck.png");
	background-repeat: no-repeat;
	background-position: center;
	border-radius:5px;
	}
	
	#dtglxx-text{
	display: block;
	}
	
	#charts{
	margin-top:40px;
	margin-bottom:40px;
	display: none;
	}
	
	#MSColumn1,#MSColumn2{
	text-align:center;
	} 	
</style>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/rygl/dtRyBiangengController";

var controllernameZgtzd= "${pageContext.request.contextPath }/yhzg/zgTzdController";

var controllernameScore= "${pageContext.request.contextPath }/yhzg/scoreController";

var controllernameIndex = "${pageContext.request.contextPath }/IndexInformationController";

var userId = parent.document.getElementById("userId").value;

//页面初始化
$(function() {

	doQuanxian();
	
	queryZGD();
	
	queryKaoQing();
	
	init();
});


function shenhe(){
	window.open("${pageContext.request.contextPath }/jsp/gdzxt/index/rybiangeng-page.jsp","人员变更审批");//传递一个ID给详细页面,用于查找 	
}

<%--
function jtshenhe(){
	window.open("${pageContext.request.contextPath }/jsp/gdzxt/index/yhzg-jbtg-page.jsp","局部停工审核");//传递一个ID给详细页面,用于查找 	
}

function qtshenhe(){
	window.open("${pageContext.request.contextPath }/jsp/gdzxt/index/yhzg-qmtg-page.jsp","全面停工审核");//传递一个ID给详细页面,用于查找 	
}

function jscore(){
	window.open("${pageContext.request.contextPath }/jsp/gdzxt/index/jscore-page.jsp","加分审核");//传递一个ID给详细页面,用于查找 	
}

function kscore(){
	window.open("${pageContext.request.contextPath }/jsp/gdzxt/index/kscore-page.jsp","扣分审核");//传递一个ID给详细页面,用于查找 	
}
--%>

function doQuanxian(){
	<%--
	var shenhequanxian = false;
	//获取当前用户权限
	$.ajax({		
		url : controllernameIndex+"?getQuanxian&userId="+userId,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var resultObj = defaultJson.dealResultJson(response.msg);
			var quanxian = resultObj.QUANXIAN;
			if(quanxian=="1"){
				$(".shenhequanxian").css("display","block");
				shenhequanxian = true;
			}
		}		
	});
	
	
	if(shenhequanxian){
		
		$.ajax({
			url : controllername+"?getBgCount",
			cache : false,
			async :	false,
			dataType : "json",  
			type : 'post',
			success : function(response) {
				var num = response.msg;
				$('#num').text(num);
			}
		});

			$.ajax({
			url : controllernameZgtzd+"?getJtCount",
			cache : false,
			async :	false,
			dataType : "json",  
			type : 'post',
			success : function(response) {
				var num = response.msg;
				$('#jtnum').text(num);
			}
		});
		
		$.ajax({
			url : controllernameZgtzd+"?getQtCount",
			cache : false,
			async :	false,
			dataType : "json",  
			type : 'post',
			success : function(response) {
				var num = response.msg;
				$('#qtnum').text(num);
			}
		});

			$.ajax({
			url : controllernameScore+"?getDshkCount",
			cache : false,
			async :	false,
			dataType : "json",  
			type : 'post',
			success : function(response) {
				var num = response.msg;
				$('#kscorenum').text(num);
			}
		});
			
		$.ajax({
			url : controllernameScore+"?getDshjCount",
			cache : false,
			async :	false,
			dataType : "json",  
			type : 'post',
			success : function(response) {
				var num = response.msg;
				$('#jscorenum').text(num);
			}
		});						
	}
	--%>
	//上面查询的数据有点问题	
	$.ajax({
		url : controllername+"?getBgCount",
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var num = response.msg;
			$('#num').text(num);
		}
	});<%-- 本应该一起查的，蛋疼 --%>

	
	$.ajax({		
		url : controllernameIndex+"?getQuanxian&userId="+userId,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var msg = response.msg;
			var msgarr =new Array();
			msgarr = msg.split("&&&&");
			var resultObj1 = defaultJson.dealResultJson(msgarr[0]);
			var resultObj2 = defaultJson.dealResultJson(msgarr[1]);
			var resultObj3 = defaultJson.dealResultJson(msgarr[2]);
			var resultObj4 = defaultJson.dealResultJson(msgarr[3]);
			var resultObj5 = defaultJson.dealResultJson(msgarr[4]);
			
			$("#shenhequanxian-line1").find("a").eq(0).html(resultObj1.TOTALS);
			$("#shenhequanxian-line1").find("a").eq(1).html(resultObj2.TOTALS);
			$("#shenhequanxian-line2").find("a").eq(0).html(resultObj3.TOTALS);
			$("#shenhequanxian-line2").find("a").eq(1).html(resultObj4.TOTALS);
			$("#shenhequanxian-line2").find("a").eq(2).html(resultObj5.TOTALS);

		}		
	});
}

//整改单信息收集
function queryZGD(){
	//查询更新整改单
	$.ajax({		
		url : controllernameIndex+"?queryZGD",
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {			
			var msg = response.msg;
			var msgarr =new Array();
			msgarr = msg.split("&&&&");
			var resultObj1 = defaultJson.dealResultJson(msgarr[0]);
			var resultObj2 = defaultJson.dealResultJson(msgarr[1]);
			var resultObj3 = defaultJson.dealResultJson(msgarr[2]);
			var resultObj4 = defaultJson.dealResultJson(msgarr[3]);
			var resultObj5 = defaultJson.dealResultJson(msgarr[4]);
			var resultObj6 = defaultJson.dealResultJson(msgarr[5]);
			var resultObj7 = defaultJson.dealResultJson(msgarr[6]);
			var resultObj8 = defaultJson.dealResultJson(msgarr[7]);
			var resultObj9 = defaultJson.dealResultJson(msgarr[8]);
			var resultObj10 = defaultJson.dealResultJson(msgarr[9]);
			var resultObj11 = defaultJson.dealResultJson(msgarr[10]);
			var resultObj12 = defaultJson.dealResultJson(msgarr[11]);
			var resultObj13 = defaultJson.dealResultJson(msgarr[12]);
			
			$("#line-1").find("a").eq(0).html(resultObj1.TOTALS);
			$("#line-1").find("a").eq(1).html(resultObj1.ZGNUMS);
			$("#line-1").find("a").eq(2).html(resultObj1.KQNUMS);
			$("#line-1").find("a").eq(3).html(resultObj2.TOTALS);
			$("#line-1").find("a").eq(4).html(resultObj2.ZGNUMS);
			$("#line-1").find("a").eq(5).html(resultObj2.KQNUMS);
			
			$("#line-2").find("a").eq(0).html(resultObj3.TOTALS);
			$("#line-2").find("a").eq(1).html(resultObj3.ZGNUMS);
			$("#line-2").find("a").eq(2).html(resultObj3.KQNUMS);
			$("#line-2").find("a").eq(3).html(resultObj4.TOTALS);
			$("#line-2").find("a").eq(4).html(resultObj5.TOTALS);
			$("#line-2").find("a").eq(5).html(resultObj5.ZGNUMS);
			$("#line-2").find("a").eq(6).html(resultObj5.KQNUMS);
			
			$("#line-3").find("a").eq(0).html(resultObj6.TOTALS);
			$("#line-3").find("a").eq(1).html(resultObj7.TOTALS);
			$("#line-3").find("a").eq(2).html(resultObj8.TOTALS);
			
			$("#line-4").find("a").eq(0).html(resultObj9.TOTALS);
			$("#line-4").find("a").eq(1).html(resultObj10.TOTALS);	
			
			$("#line-5").find("a").eq(0).html(resultObj11.TOTALS);
			$("#line-5").find("a").eq(1).html(resultObj11.ZGNUMS);
			$("#line-5").find("a").eq(2).html(resultObj11.KQNUMS);
			$("#line-5").find("a").eq(3).html(resultObj12.TOTALS);
			$("#line-5").find("a").eq(4).html(resultObj12.ZGNUMS);
			$("#line-5").find("a").eq(5).html(resultObj12.KQNUMS);
			$("#line-5").find("a").eq(6).html(resultObj13.TOTALS);
			$("#line-5").find("a").eq(7).html(resultObj13.ZGNUMS);
			$("#line-5").find("a").eq(8).html(resultObj13.KQNUMS);
			
		}		
	});
}


//项目考勤状态收集
function queryKaoQing(){
	//查询更新整改单
	$.ajax({		
		url : controllernameIndex+"?queryKaoQing",
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {			
			var msg = response.msg;
			var msgarr =new Array();
			msgarr = msg.split("&&&&");						
			
			var MainKaoqingInfo = defaultJson.dealResultJson(msgarr[0]);
			var Zhuyaorenyuan2Days = defaultJson.dealResultJson(msgarr[1]);
			var ZuoriWuyouxiaoKaoQing = defaultJson.dealResultJson(msgarr[2]);
			var SgdwGuanjiangangweiWuyouxiaoKaoqing = defaultJson.dealResultJson(msgarr[3]);
			var SgdwGuanZhongyaoWuyouxiaoKaoqing = defaultJson.dealResultJson(msgarr[4]);
			var SgdwGuanZhuyaoWuyouxiaoKaoqing = defaultJson.dealResultJson(msgarr[5]);
			var JldwGuanjiangangweiWuyouxiaoKaoqing = defaultJson.dealResultJson(msgarr[6]);
			
			var JldwGuanZhongyaoWuyouxiaoKaoqing = defaultJson.dealResultJson(msgarr[7]);
			var JldwGuanZhuyaoWuyouxiaoKaoqing = defaultJson.dealResultJson(msgarr[8]);
			var KaoqingKoufen = defaultJson.dealResultJson(msgarr[9]);
			var DaishenKaoqingZanting = defaultJson.dealResultJson(msgarr[10]);
			var DaishenKaoqingZhongzhi = defaultJson.dealResultJson(msgarr[11]);
			var DaishenFenjieduan = defaultJson.dealResultJson(msgarr[12]);
			var KaoqingZhuangtaiBiangeng = defaultJson.dealResultJson(msgarr[13]);
			var DaishenJiesuo = defaultJson.dealResultJson(msgarr[14]);
			var InformationTable = defaultJson.dealResultJson(msgarr[15]);
			
			
			if(MainKaoqingInfo.ALL_PERSON=="") MainKaoqingInfo.ALL_PERSON=0;
			if(MainKaoqingInfo.ZHONGYAO_PERSON=="") MainKaoqingInfo.ZHONGYAO_PERSON=0;
			if(MainKaoqingInfo.ZHUYAO_PERSON=="") MainKaoqingInfo.ZHUYAO_PERSON=0;			
			if(MainKaoqingInfo.XUKAOQING=="") MainKaoqingInfo.XUKAOQING=0;
			if(MainKaoqingInfo.ZR_YIKAOQING=="") MainKaoqingInfo.ZR_YIKAOQING=0;
			if(MainKaoqingInfo.ZR_YOUXIAOKAOQING=="") MainKaoqingInfo.ZR_YOUXIAOKAOQING=0;
			if(MainKaoqingInfo.KQ_REGION_IN=="") MainKaoqingInfo.KQ_REGION_IN=0;
			if(MainKaoqingInfo.KQ_REGION_OUT=="") MainKaoqingInfo.KQ_REGION_OUT=0;
			if(MainKaoqingInfo.KQ_ZANTING=="") MainKaoqingInfo.KQ_ZANTING=0;						
			
			
			$("#line-11").find("a").eq(0).html(MainKaoqingInfo.TOTALS);
			$("#line-11").find("a").eq(1).html(MainKaoqingInfo.ALL_PERSON);
			$("#line-11").find("a").eq(2).html(MainKaoqingInfo.ZHONGYAO_PERSON);
			$("#line-11").find("a").eq(3).html(MainKaoqingInfo.ZHUYAO_PERSON);


			var QueqingTOTALS = parseInt(MainKaoqingInfo.TOTALS) - MainKaoqingInfo.ALL_PERSON;
			var QueqingZHONGYAO = MainKaoqingInfo.TOTALS - MainKaoqingInfo.ZHONGYAO_PERSON;
			var QueqingZHUYAO = MainKaoqingInfo.TOTALS - MainKaoqingInfo.ZHUYAO_PERSON;
			$("#line-12").find("a").eq(0).html(QueqingTOTALS);
			$("#line-12").find("a").eq(1).html(QueqingZHONGYAO);
			$("#line-12").find("a").eq(2).html(QueqingZHUYAO);
			$("#line-12").find("a").eq(3).html(Zhuyaorenyuan2Days.TOTALS);			
			
			$("#line-13").find("a").eq(0).html(ZuoriWuyouxiaoKaoQing.TOTALS);
			
			$("#line-14").find("a").eq(0).html(SgdwGuanjiangangweiWuyouxiaoKaoqing.TOTALS);
			$("#line-14").find("a").eq(1).html(SgdwGuanZhongyaoWuyouxiaoKaoqing.TOTALS);
			$("#line-14").find("a").eq(2).html(SgdwGuanZhuyaoWuyouxiaoKaoqing.TOTALS);
			
			
			$("#line-15").find("a").eq(0).html(JldwGuanjiangangweiWuyouxiaoKaoqing.TOTALS);
			$("#line-15").find("a").eq(1).html(JldwGuanZhongyaoWuyouxiaoKaoqing.TOTALS);
			$("#line-15").find("a").eq(2).html(JldwGuanZhuyaoWuyouxiaoKaoqing.TOTALS);			
			
			$("#line-16").find("a").eq(0).html(MainKaoqingInfo.XUKAOQING);
			$("#line-16").find("a").eq(1).html(MainKaoqingInfo.ZR_YIKAOQING);
			$("#line-16").find("a").eq(2).html(MainKaoqingInfo.ZR_YOUXIAOKAOQING);
			
			var fenjieduanKaoqing = MainKaoqingInfo.KQ_REGION_IN + MainKaoqingInfo.KQ_REGION_OUT;
			$("#line-17").find("a").eq(0).html(fenjieduanKaoqing);
			$("#line-17").find("a").eq(1).html(MainKaoqingInfo.KQ_REGION_IN);
			$("#line-17").find("a").eq(2).html(MainKaoqingInfo.KQ_REGION_OUT);
			$("#line-17").find("a").eq(3).html(MainKaoqingInfo.KQ_ZANTING);
			
			
			$("#line-21").find("a").eq(0).html(KaoqingKoufen.TOTALS);
			
			$("#line-22").find("a").eq(0).html(DaishenKaoqingZanting.TOTALS);
			$("#line-22").find("a").eq(1).html(DaishenKaoqingZhongzhi.TOTALS);
			$("#line-22").find("a").eq(2).html(DaishenFenjieduan.TOTALS);
			
			$("#line-23").find("a").eq(0).html(KaoqingZhuangtaiBiangeng.TOTALS);
			$("#line-23").find("a").eq(2).html(DaishenJiesuo.TOTALS);
			
			if(InformationTable.SG_ZHONGYAO_NUMS=="") InformationTable.SG_ZHONGYAO_NUMS = 0;
			if(InformationTable.SG_ZHONGYAO_KQ=="") InformationTable.SG_ZHONGYAO_KQ = 0;
			if(InformationTable.SG_ZHONGYAO_VALID=="") InformationTable.SG_ZHONGYAO_VALID = 0;
			if(InformationTable.SG_ZHUYAO_NUMS=="") InformationTable.SG_ZHUYAO_NUMS = 0;
			if(InformationTable.SG_ZHUYAO_KQ=="") InformationTable.SG_ZHUYAO_KQ = 0;
			if(InformationTable.SG_ZHUYAO_VALID=="") InformationTable.SG_ZHUYAO_VALID = 0;
			if(InformationTable.JL_ZHONGYAO_NUMS=="") InformationTable.JL_ZHONGYAO_NUMS = 0;
			if(InformationTable.JL_ZHONGYAO_KQ=="") InformationTable.JL_ZHONGYAO_KQ = 0;
			if(InformationTable.JL_ZHONGYAO_VALID=="") InformationTable.JL_ZHONGYAO_VALID = 0;
			if(InformationTable.JL_ZHUYAO_NUMS=="") InformationTable.JL_ZHUYAO_NUMS = 0;
			if(InformationTable.JL_ZHUYAO_KQ=="") InformationTable.JL_ZHUYAO_KQ = 0;
			if(InformationTable.JL_ZHUYAO_VALID=="") InformationTable.JL_ZHUYAO_VALID = 0;
			
			var NUMSTOTAL = InformationTable.SG_ZHONGYAO_NUMS+InformationTable.SG_ZHUYAO_NUMS+
			InformationTable.JL_ZHONGYAO_NUMS+InformationTable.JL_ZHUYAO_NUMS;
			var KQTOTAL = InformationTable.SG_ZHONGYAO_KQ+InformationTable.SG_ZHUYAO_KQ+
			InformationTable.JL_ZHONGYAO_KQ+InformationTable.JL_ZHUYAO_KQ;
			var VALIDTOTAL = InformationTable.SG_ZHONGYAO_VALID+InformationTable.SG_ZHUYAO_VALID+
			InformationTable.JL_ZHONGYAO_VALID+InformationTable.JL_ZHUYAO_VALID;
			
			var tabledate = 
			"<tr><th colspan=\"2\" width=\"160px\">人员类别</th>"+
			"<th width=\"65px\">应考勤(人次)</th>"+
			"<th width=\"65px\">已考勤(人次)</th>"+
			"<th width=\"65px\">有效考勤(人次)"+
			"</th></tr>"+
			"<tr><td rowspan=\"2\" width=\"80px\">施工单位</td><td width=\"80px\">重要人员</td><td>"+InformationTable.SG_ZHONGYAO_NUMS+"</td><td>"+InformationTable.SG_ZHONGYAO_KQ+"</td><td>"+InformationTable.SG_ZHONGYAO_VALID+"</td></tr>"+
			"<tr><td>主要人员</td><td>"+InformationTable.SG_ZHUYAO_NUMS+"</td><td>"+InformationTable.SG_ZHUYAO_KQ+"</td><td>"+InformationTable.SG_ZHUYAO_VALID+"</td></tr>"+
			"<tr><td rowspan=\"2\">监理单位</td><td>重要人员</td><td>"+InformationTable.JL_ZHONGYAO_NUMS+"</td><td>"+InformationTable.JL_ZHONGYAO_KQ+"</td><td>"+InformationTable.JL_ZHONGYAO_VALID+"</td></tr>"+
			"<tr><td>主要人员</td><td>"+InformationTable.JL_ZHUYAO_NUMS+"</td><td>"+InformationTable.JL_ZHUYAO_KQ+"</td><td>"+InformationTable.JL_ZHUYAO_VALID+"</td></tr>"+
			"<tr><td colspan=\"2\">合计</td><td>"+NUMSTOTAL+"</td><td>"+KQTOTAL+"</td><td>"+VALIDTOTAL+"</td></tr>";
			
			$("#information-table tbody").html(tabledate);
			
		}		
	});
}


//!下面写页面操作的需要的函数
function init(){

	widthNumber();
	
	$("#information").bind("mouseover",function(){
		$("#information-div").css("display","block");		
	});
	
	$("#information").bind("mouseout",function(){
		$("#information-div").css("display","none");		
	});	
	
	$(".split-iron-text").bind("click",function(){
		$(".split-iron-text").css("background-color","#FFFFFF");	
		$(".split-iron-text").css("background-image","url(\"${pageContext.request.contextPath }/images/dtgl_index/text_checked.png\")");
		$(".split-iron-pic").css("background-color","#2074CA");	
		$(".split-iron-pic").css("background-image","url(\"${pageContext.request.contextPath }/images/dtgl_index/pic_uncheck.png\")");
		$("#dtglxx-text").css("display","block");
		$("#charts").css("display","none");
	});
	
	$(".split-iron-pic").bind("click",function(){
		$(".split-iron-text").css("background-color","#2074CA");	
		$(".split-iron-text").css("background-image","url(\"${pageContext.request.contextPath }/images/dtgl_index/text_uncheck.png\")");
		$(".split-iron-pic").css("background-color","#FFFFFF");	
		$(".split-iron-pic").css("background-image","url(\"${pageContext.request.contextPath }/images/dtgl_index/pic_checked.png\")");
		$("#dtglxx-text").css("display","none");
		$("#charts").css("display","block");
		if($("#week").val()==null){
			setWeek();
		}
		if($("#month").val()==null){
			setMonth();
		}
		if($("#year").val()==null){
			setYear();
		}		
		charWeekSet();
		charMonthSet();
		
	});
	
	//点击链接函数
	$("#dtglxx-text,#kqxx-text,#kqdsxx-text,#xmxxhz-text,#qtxxhz-text").find("a").bind("click",function(){
		var linkable = $(this).attr("linkable");
		var linkanother = $(this).attr("linkanother");
		if(linkable=="false"){
			return;
		}		
		//链接到另外页面
		if(linkanother=="true"){
			var condition = $(this).attr("condition");
			if(condition==null||condition=="") condition="none";
			var url = "${pageContext.request.contextPath}/"+$(this).attr("url")+"?condition="+condition;		
			window.open(url,"整改单查询");			
		}else{
			var app_name=$(this).attr("appName");
			if(app_name==null||app_name==""){
				return;
			}
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
		}
		
	});
	
}

function widthNumber(){
	$(".container_x a").each(function(){
		$(this).html("&nbsp&nbsp"+$(this).html()+"&nbsp&nbsp");
	});
}


function charWeekSet(){
	var week = $("#week").val();
	$.ajax({
		url : controllernameIndex+"?getChartDataWeek&week="+week,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var msg = response.msg;
			var msgarr =new Array();
			msgarr = msg.split("&&&&");
			
			var width = $("#charts").width();
			var chartsWidth = width/2-100;
			$(".chart-div").css("width",chartsWidth+100);
			//第一张表
		 	var chart1 = new FusionCharts('${pageContext.request.contextPath }/FusionCharts/Charts/MSColumn3D.swf', "ChartId", chartsWidth, "300");
	        chart1.setDataXML(msgarr[0]);
	        chart1.render('MSColumn1');
	        
	        $(".totals1").html(msgarr[1]);
		}
	});
	
		                   
                     
}

function charMonthSet(){
	var year = $("#year").val();
	var month = $("#month").val();
	$.ajax({
		url : controllernameIndex+"?getChartDataMonth&year="+year+"&month="+month,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var msg = response.msg;
			var msgarr =new Array();
			msgarr = msg.split("&&&&");			
			
			var width = $("#charts").width();
			 var chartsWidth = width/2-100;
			 $(".chart-div").css("width",chartsWidth+100);                         
	         //第二张表
	         var chart2 = new FusionCharts('${pageContext.request.contextPath }/FusionCharts/Charts/MSColumn3D.swf', "ChartId", chartsWidth, "300");         
	         chart2.setDataXML(msgarr[0]);	      
	         chart2.render('MSColumn2'); 
	         
	         $(".totals2").html(msgarr[1]);
    
		}
	});	

    
}


function setWeek(){
		var selectObj=document.getElementById('week');
		for(var i=1;i<54;i++){
			if(i>0&&i<10){
				selectObj.options.add(new Option("第0"+i+"周",i));	
			}else{
				selectObj.options.add(new Option("第"+i+"周",i));
			}			
		}
		
		var thisweek =  moment(new Date()).format("W");
		$("#week").val(thisweek);
		$("#thisweek").html(thisweek);
}

function setMonth(){	
	var selectObj=document.getElementById('month');
	for(var i=1;i<13;i++){
		if(i>0&&i<10){
			selectObj.options.add(new Option(i+"月",i));	
		}else{
			selectObj.options.add(new Option(i+"月",i));
		}			
	}	
	var thismonth =  moment(new Date()).format("M");
	$("#month").val(thismonth);
	$("#thismonth").html(thismonth);
}


function setYear(){
	
	var thisyear = new Date().getFullYear();	
	var selectObj=document.getElementById('year');
	for(var i=thisyear-20;i<thisyear+20;i++){		
		selectObj.options.add(new Option(i+"年",i));					
	}
	
	$("#year").val(thisyear);
	$("#thisyear").html(thisyear);
}



function year(id){
	var year=$("#year").val();
	$("#thisyear").html(year);
	charMonthSet();
}

function month(id){
	var month=$("#month").val();
	$("#thismonth").html(month);
	charMonthSet();
}

function week(id){
	var week=$("#week").val();
	$("#thisweek").html(week);
	charWeekSet();
}

//点击柱状图事件
function ShowDetail(zhenggai,dept,whichone){
	var week=$("#week").val();
	var year = $("#year").val();
	var month = $("#month").val();
	var condition = "chart";	
	var url = "${pageContext.request.contextPath}/jsp/business/dtgl/index/index-subpage/ZGD-page.jsp?condition="+condition+"&zhenggai="+zhenggai+"&whichone="+whichone+"&dept="+dept+"&week="+week+"&year="+year+"&month="+month;
	window.open(url,"整改单查询");
}
</script>
</head>
<body>
    <div class="container_x">
          <div class="row">          
          <div id="dtglxx" width="100%">
          <table class="content" width="100%">
          <tr><td class="yw-title dtglxx-td">
          <h4 id="dtglxx-top">动态管理信息</h4>
          <div class="split-iron-pic"></div><div class="split-iron-text"></div></td>          
          </tr>
          <tr><td id="dtglxx_content">                  
            <div id="dtglxx-text"><!-- class="col-6 col-sm-6 col-lg-4" -->
            <div>
              <h4>项目整改状态：</h4> 				
			<!-- 动态管理信息 -->
			<div id="dtglxx-text">						
				<p id="line-1"><span>今日更新</span><a linkanother="true" url="jsp/business/dtgl/index/index-subpage/ZGD-page.jsp" condition="today-update"></a><span>张(实体</span><a linkanother="true" url="jsp/business/dtgl/index/index-subpage/ZGD-page.jsp" condition="today-update-shiti"></a><span>张，考勤</span><a linkanother="true" url="jsp/business/dtgl/index/index-subpage/ZGD-page.jsp" condition="today-update-kaoqing"></a><span>张)，昨日更新</span><a linkanother="true" url="jsp/business/dtgl/index/index-subpage/ZGD-page.jsp" condition="yesterday-update"></a>
				   <span>张(实体</span><a linkanother="true" url="jsp/business/dtgl/index/index-subpage/ZGD-page.jsp" condition="yesterday-update-shiti"></a><span>张，考勤</span><a linkanother="true" url="jsp/business/dtgl/index/index-subpage/ZGD-page.jsp" condition="yesterday-update-kaoqing"></a><span>张)</span>
				</p>
				<p id="line-2"><span>今日开具整改单</span><a linkanother="true" url="jsp/business/dtgl/index/index-subpage/ZGD-page.jsp" condition="today-create"></a><span>张(实体</span><a linkanother="true" url="jsp/business/dtgl/index/index-subpage/ZGD-page.jsp" condition="today-create-shiti"></a><span>张，考勤 </span><a linkanother="true" url="jsp/business/dtgl/index/index-subpage/ZGD-page.jsp" condition="today-create-kaoqing"></a><span>张)，局部停工</span><a linkanother="true" url="jsp/business/dtgl/index/index-subpage/ZGD-page.jsp" condition="jbtg"></a>
				   <span>张，全面停工</span><a linkanother="true" url="jsp/business/dtgl/index/index-subpage/ZGD-page.jsp" condition="qmtg"></a><span>张(实体</span><a linkanother="true" url="jsp/business/dtgl/index/index-subpage/ZGD-page.jsp" condition="qmtg-shiti"></a><span>张，考勤</span><a linkanother="true" url="jsp/business/dtgl/index/index-subpage/ZGD-page.jsp" condition="qmtg-kaoqing"></a><span>张)</span>
				</p>
				<p id="line-3"><span>今日扣分</span><a linkanother="true" url="jsp/business/dtgl/index/index-subpage/score-page.jsp" condition="today"></a><span>条，本周扣分</span><a linkanother="true" url="jsp/business/dtgl/index/index-subpage/score-page.jsp" condition="week"></a><span>条，本月扣分</span><a linkanother="true" url="jsp/business/dtgl/index/index-subpage/score-page.jsp" condition="month"></a><span>条</span></p>              
						
				<!-- 需要审核权限才可查看内容 -->						
				<p class="shenhequanxian" id="shenhequanxian-line1"><span>待审核扣分</span><a linkanother="true" url="jsp/business/dtgl/index/index-subpage/score-page.jsp" condition="dshkf"></a>
				   <span>条 待审核加分</span><a linkanother="true" url="jsp/business/dtgl/index/index-subpage/score2-page.jsp" condition="dshjf"></a>
				   <span>条</span>				
				</p>
				<p class="shenhequanxian" id="shenhequanxian-line2"><span>待审核全面停工</span><a linkanother="true" url="jsp/business/dtgl/index/index-subpage/ZGD-page.jsp" condition="dshqmtg"></a>
				   <span>张， 待审核局部停工</span><a linkanother="true" url="jsp/business/dtgl/index/index-subpage/ZGD-page.jsp" condition="dshjbtg"></a>
				   <span>张， 待审核复工</span><a linkanother="true" url="jsp/business/dtgl/index/index-subpage/ZGD-page.jsp" condition="dshfg"></a><span>张</span>
				</p>						
												
				<p id="line-4"><span>已全面停工未复工项目</span><a linkanother="true" url="jsp/business/dtgl/index/index-subpage/project-page.jsp" condition="yqmtgwfgxm"></a><span>个 已局部停工未复工项目</span><a linkanother="true" url="jsp/business/dtgl/index/index-subpage/project-page.jsp" condition="yjbtgwfgxm"></a><span>个 </span></p>	        
				<p id="line-5"><span>未闭合</span><a linkanother="true" url="jsp/business/dtgl/index/index-subpage/ZGD-page.jsp" condition="weibihe"></a><span>张(实体</span><a linkanother="true" url="jsp/business/dtgl/index/index-subpage/ZGD-page.jsp" condition="weibihe-shiti"></a><span>张，考勤</span><a linkanother="true" url="jsp/business/dtgl/index/index-subpage/ZGD-page.jsp" condition="weibihe-kaoqing"></a><span>张)，即将超时未答复</span><a linkanother="true" url="jsp/business/dtgl/index/index-subpage/ZGD-page.jsp" condition="jjcswdf"></a>
				   <span>张(实体</span><a linkanother="true" url="jsp/business/dtgl/index/index-subpage/ZGD-page.jsp" condition="jjcswdf-shiti"></a><span>张，考勤</span><a linkanother="true" url="jsp/business/dtgl/index/index-subpage/ZGD-page.jsp" condition="jjcswdf-kaoqing"></a><span>张)，超时未答复</span><a linkanother="true" url="jsp/business/dtgl/index/index-subpage/ZGD-page.jsp" condition="cswdf"></a><span>张(实体</span><a linkanother="true" url="jsp/business/dtgl/index/index-subpage/ZGD-page.jsp" condition="cswdf-shiti"></a>
				   <span>张，考勤</span><a linkanother="true" url="jsp/business/dtgl/index/index-subpage/ZGD-page.jsp" condition="cswdf-kaoqing"></a><span>张)</span>
				</p>	     
				<p><a>企业</a>、<a>个人</a>、<a>项目</a>信用分值查询</p>	             
			</div>											
			</div>							
					
            <div><!-- class="col-6 col-sm-6 col-lg-4" -->
              <h4>项目考勤状态：</h4>
             	<h5>1、考勤信息 </h5>             	
				<div id="kqxx-text">
				<table id="kqxx-table">
				<tr><td id="line-11"><span>昨日需考勤项目共有</span><a></a><span>个，其中满勤项目</span><a></a><span>个，重要人员满勤项目</span><a></a>
				   <span>个，主要人员满勤项目</span><a></a><span>个</span>
				</td></tr>
				<tr><td id="line-12"><span>昨日缺勤项目</span><a></a><span>个，其中重要人员缺勤项目</span><a></a><span>个，主要人员缺勤项目</span><a></a>
				   <span>个，重要人员缺勤2天项目</span><a></a><span>个</span>
				</td></tr>                 
				<tr><td id="line-13"><span>昨日无有效考勤记录项目</span><a></a><span>个:</span></td></tr>                 
				<tr><td id="line-14"><span>施工单位关键岗位人员无有效考勤记录项目</span><a></a><span>个，重要人员无有效考勤项目</span><a></a><span>个，主要人员无有效考勤记录项目</span><a></a><span>个</span>				   
				</td></tr>    
				<tr><td id="line-15"><span>监理单位关键岗位人员无有效考勤记录项目</span><a></a><span>个，重要人员无有效考勤项目</span><a></a><span>个，主要人员无有效考勤记录项目</span><a></a><span>个</span>				 
				</td></tr>            
				<tr><td id="line-16"><div id="information-float"><span>目前需考勤人员</span><a></a><span>人次，昨日已考勤</span><a></a><span>人次，有效考勤</span><a></a><span>人次&nbsp</span></div>
						<!-- 查看人员信息表 -->						 
						<div id="information">						
						<img src="${pageContext.request.contextPath }/images/dtgl_index/information-balloon.png"/>
						<div id="information-div">
						<div id="inner-border">
						<table id="information-table">
							<tbody>
							</tbody>							
						</table>							
						</div></div>				
						</div>						
				</td></tr>				             
				<tr><td id="line-17"><span>目前分阶段考勤</span><a></a><span>人次(正在考勤</span><a></a><span>人次，即将考勤</span><a></a><span>人次)，暂停考勤</span><a></a><span>人次</span>			  
				</td></tr>
				</table>			                  
				</div>
				
				
				<h5>2、考勤待审信息 </h5>
				<div id="kqdsxx-text">
				<p id="line-21"><span>目前考勤违规应扣分项目</span><a></a><span>个</span>
				</p>
				<p id="line-22"><span>待审暂停考勤</span><a></a><span>个，待审终止考勤</span><a></a><span>个，待审分阶段考勤</span><a></a><span>个</span>
				</p>     
				<p id="line-23"><span>待审项目考勤状态</span><a></a><span>个，待审人员变更</span><a href="javascript:void(0)" onclick="shenhe()" id="num"></a>
				   <span>个，待审人员解锁</span><a></a><span>个</span>
				</p>            		            
				</div>
			</div>					
            </div>

            
            <div id="charts">
			<!-- 柱状图开始 -->
				<div id="MSColumns">					
					<div class="chart-div">
						<select style="width:90px;"  id="week"  name = "week" onchange="week(this.id)">						  
						</select>
						<span style="line-height: 40px;">更新整改单<span>
						<div id="MSColumn1"></div>
						<span>第<font id="thisweek"></font>周共更新整改单&nbsp&nbsp<font class="totals1"></font>&nbsp&nbsp张<span>
					</div>																				
					
					<div class="chart-div">
						<select style="width:90px;"  id="year"  name = "year" onchange="year(this.id)">						  
						</select>
						<select style="width:70px;"  id="month"  name = "month" onchange="month(this.id)">						  
						</select>
						<span style="line-height: 40px;">更新整改单<span>
						<div id="MSColumn2"></div>
						<span><font id="thisyear"></font>年<font id="thismonth"></font>月共更新整改单&nbsp&nbsp<font class="totals2"></font>&nbsp&nbsp张<span>
					</div>				
			</div>
			</div>            
            </td></tr></table>
           </div>
           <!-- 动态管理信息结束 -->
            
          <div id="xmxxhz" width="100%">
          <table class="content" width="100%"><tr><td class="yw-title">
          <h4>项目信息汇总 </h4></td></tr>
          <tr><td id="xmxxhz_content">
          <div>
          <div id="xmxxhz-text">
          <p><span>在建有安监项目</span><a></a><span>个(共计</span><a></a><span>万平方米，</span><a></a><span>万元)</span>
          </p>
          <p><span>其中市政项目</span><a></a><span>个 房建项目</span><a></a><span>个</span>
          </p>
          <p><span>其中未完成质安监手续项目</span><a></a><span>个 未完成施工许可证手续项目</span><a></a><span>个 未完成施工注册证手续工地</span><a></a><span>个</span>
          </p>
          <p><span>在建施工企业</span><a></a><span>家 在建监理企业</span><a></a><span>家</span>
          </p>
          <p><span class="important-info">超过28天未检查项目</span><a></a><span class="important-info">个，标段</span><a></a><span class="important-info">个</span><span>&nbsp;&nbsp;&nbsp;&nbsp;其中市政项目</span><a></a>
             <span>个，标段</span><a></a><span>个 房建项目</span><a></a><span>个，标段</span><a></a><span>个</span>
          </p>
          <p><span class="important-info">超过21天未检查项目</span><a></a><span class="important-info">个，标段</span><a></a><span class="important-info">个</span><span>&nbsp;&nbsp;&nbsp;&nbsp;其中市政项目</span><a></a>
             <span>个，标段</span><a></a><span>个 房建项目</span><a></a><span>个，标段</span><a></a><span>个</span>
          </p>
          <p><span>在建项目中绿色工地</span><a></a><span>个 标准化工地</span><a></a><span>个 重点监管工地</span><a></a><span>个</span>
          </p>	            
		  <p>本年度项目信息：</p>
		  <p><span>新开工项目</span><a></a><span>个(共计</span><a></a><span>万平方米) 其中市政项目</span><a></a>
             <span>个 房建项目</span><a></a><span>个</span>
          </p>
          <p><span>已完工</span><a></a><span>个项目</span><a></a><span>个工地(共计</span><a></a>
             <span>万平方米)</span>
          </p>                       	            
		  </div>          
          </div>          
          </td></tr></table></div>
          <!-- 项目信息汇总结束 -->
          
          <div id="qzjxzh" width="100%">
          <table class="content" width="100%" ><tr><td class="yw-title">
          <h4> 起重机械汇总  </h4></td></tr>
          <tr><td id="qzjxzh_content">
          <h5><a>查看汇总信息</a></h5>
          <div>
          <table width="90%" id="qzjx-table" class="table-hover table-activeTd B-table">
          <tr><th width="20%">&nbsp</th><th width="16%">塔式起重机</th><th width="16%">施工升降机</th><th width="16%">物料提升机</th><th width="16%">吊篮</th><th width="16%">爬升式脚手架</th></tr>
          <tr><td><a>今日安装</a></td><td></td><td></td><td></td><td></td><td></td></tr>
          <tr><td><a>安装告知后未办理下一手续</a></td><td></td><td></td><td></td><td></td><td></td></tr>
          <tr><td><a>安装过程完成15天后未检测</a></td><td></td><td></td><td></td><td></td><td></td></tr>
          <tr><td><a>检测报告即将逾期</a> </td><td></td><td></td><td></td><td></td><td></td></tr>
          <tr><td><a>已逾期检测报告</a></td><td></td><td></td><td></td><td></td><td></td></tr>
          <tr><td><a>即将逾期办理使用证</a></td><td></td><td></td><td></td><td></td><td></td></tr>
          <tr><td><a>已逾期未办理使用证</a></td><td></td><td></td><td></td><td></td><td></td></tr>
          <tr><td><a>拆卸告知完成30天后未办理下一步手续</a></td><td></td><td></td><td></td><td></td><td></td></tr>          
          </table>            
          </div> 
          <br/>     
          </td></tr></table>
          </div>
          <!-- 起重机械汇总结束 -->
          
          
          <div id="wxyxxhz" width="100%">
          <table class="content" width="100%"><tr><td class="yw-title">
          <h4> 危险源信息汇总  </h4></td></tr>
          <tr><td id="wxyxxhz_content">
          <div>
          <table width="90%" id="qzjx-table" class="table-hover table-activeTd B-table">
          <tr><th width="34%" rowspan="2">危险源</th><th width="33%" colspan="3">监督一组</th><th width="33%" colspan="3">监督二组</th></tr>
          <tr><th>将开始</th><th>实施中</th><th>已结束</th><th>将开始</th><th>实施中</th><th>已结束</th></tr>
          <tr><td>基坑支护降水工程</td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
          <tr><td>模板工程 混凝土模板支撑工程</td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
          <tr><td>起重吊装安装拆卸工程</td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
          <tr><td>脚手架工程</td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
          <tr><td>幕墙工程</td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
          <tr><td>钢结构工程</td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
          <tr><td>网架、索模结构安装工程</td><td></td><td></td><td></td><td></td><td></td><td></td></tr>       
          </table>  
          <br/>	        
          </div>          
          </td></tr></table></div>
          <!-- 危险源信息汇总结束 -->
          
                  
          <div id="qtxxhz" width="100%">
          <table class="content" width="100%"><tr><td class="yw-title">
          <h4> 其他信息汇总  </h4></td></tr>
          <tr><td id="qtxxhz_content">
          <div id="qtxxhz-text">
          <p>人员汇总:</p>
          <p><span>目前共有人员</span><a></a><span>人 其中:已发卡</span><a></a><span>人， 监理发卡</span><a></a>
             <span>人，管理人员发卡</span><a></a><span>人，务工人员发卡</span><a></a><span>人</span>
          </p>
          <p>安监手续汇总:</p>
          <p><span>当月完成安监手续</span><a></a><span>个</span>
          </p>          
          </div>          
          </td></tr></table></div>
          <!-- 其他信息汇总结束 -->
          </div>
    </div>
 
    
</body>
</html>