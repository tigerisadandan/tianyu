<!DOCTYPE html>
<%@page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext"%>
<%@page import="com.ccthanking.framework.common.User" %>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<!-- 添加的 样式 及页面  -->

<app:base/>
<title>添加页面首页</title>

<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/date/moment.min.js"></script>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/ywlz/css/default.css"> </LINK>

<LINK type="text/css" rel="stylesheet" href="/wndjsbg/css/style.css"> </LINK>
<link type="text/css" rel="stylesheet" href="/wndjsbg/css/style-responsive.css">
<link type="text/css" rel="stylesheet" href="/wndjsbg/css/bootstrap.css">
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername = "${pageContext.request.contextPath }/xzcf/XZCFController";
//页面初始化
$(function() {
  
    setGongCheng_UID();
    init();
    getReportName();
    $("#DT1").show();
	$("#DT2").hide();
    $("#DT3").hide();
    $("#btnPrev").css("display","none");
        
    //保存
	$("#btnAdd").click(function(){
			var data = Form2Json.formToJSON(zgtzdForm);
			alert("发送诶后端 的数据是 ===>"+data);
			var data1 = defaultJson.packSaveJson(data);
	    	defaultJson.doInsertJson(controllername + "?insert", data1);
		    var a=$(window).manhuaDialog.getParentObj();
	        a.init();
		    $(window).manhuaDialog.close();
	});
 });
 
 /**
  获得 或失去焦点的响应
**/
$(document).ready(function(){
  $("input").focus(function(){
    $("input").css("background-color","#FFFFCC");
  });
  $("input").blur(function(){
    $("input").css("background-color","#D6D6FF");
  });
   $("textarea").focus(function(){
    $("textarea").css("background-color","#FFFFCC");
  });
  
   $("textarea").blur(function(){
    $("textarea").css("background-color","#D6D6FF");
  });
});
 
 //页面初始化
 function init(){
 
   var GongCheng_UID=parent.document.getElementById("GDZXT_XM_ID").value;
    $.ajax({
			url:controllername+"?query&gongcheng_uid="+GongCheng_UID,
			type:"post",
			dataType:"json",
			success:function(response){
				var res = dealSpecialCharactor(response.msg);
				var resultmsgobj = convertJson.string2json1(res);
				var thtml = "";
		   if(resultmsgobj!=null && resultmsgobj !=''){//说明有数据
	            var datslist=resultmsgobj.response.data;
						$(datslist).each(function(){
						 thtml += "<option value="+this.JS_COMPANY_UID+">"+this.COMPANY_NAME+"</option>";
						     
					}); 
					
				var data=resultmsgobj.response.data[0];	
		        //$("#DX_NAME").val(data.COMPANY_NAME);
		         $("#FAREN").val(data.FAREN);
		         $("#DX_UID").val(data.JS_COMPANY_UID);
		         $("#ADDRESS").val(data.ADDRESS);
		         $("#LIANXIREN").val(data.LIANXIREN);
		         $("#MOBILE").val(data.LIANXIREN_MOBILE);
		         //页面 2 
		         $("#BL_DCDX").val(data.COMPANY_NAME);
		   	     $("#PEOPLE").val(data.FAREN);
		   	     $("#CONCAT").val(data.LIANXIREN);
		   	     $("#PHONE").val(data.LIANXIREN_MOBILE);
		   	     $("#ADDRESSBILU").val(data.ADDRESS);  
		         $("#FAX").val(data.FAX);
		         //调查时间 ?
		         //页面 3
		         $("#BL_DCDX_ONE").val(data.COMPANY_NAME);
		   	     $("#PEOPLE_ONE").val(data.FAREN);
		   	     $("#FAREN_MOBILE").val(data.FAREN_MOBILE);
		   	     $("#CONCAT_ONE").val(data.LIANXIREN);
		   	     $("#PHONE_ONE").val(data.LIANXIREN_MOBILE);
		   	     $("#ADDRESSBILU_ONE").val(data.ADDRESS);
		         $("#POSTCODE").val(data.POSTCODE);
		        
		         $("#DX_TYPE").val(data.DUIXIANG_TYPE);
		       
		       }else{
		        alert("没有获取到页面初始化数据!");
		       }
		        $("#DX_NAME").append(thtml);	
			},
			
		}); 
 }
 
function  getMassage(JS_COMPANY_UID){
    var GongCheng_UID=parent.document.getElementById("GDZXT_XM_ID").value;
    $.ajax({
			url:controllername+"?queryDXTYPE",
			data:{"gongcheng_uid":GongCheng_UID,"js_company_uid":JS_COMPANY_UID},
			type:"post",
			dataType:"json",
			success:function(response){
				var res = dealSpecialCharactor(response.msg);
				var resultmsgobj = convertJson.string2json1(res);
				var thtml = "";
		   if(resultmsgobj!=null && resultmsgobj !=''){//说明有数据
		        var getdata=resultmsgobj.response.data[0];
	            var datslist=resultmsgobj.response.data;
						$(datslist).each(function(){
							 thtml += "<option value="+this.DUIXIANG_TYPE+">"+this.DUIXIANG_TYPE+"</option>";
					});
			    var companyName = getdata.COMPANY_NAME;
				// $("#DX_NAME").empty();//清空下拉框 
				 //$("#DX_NAME").attr("value",companyName);//填充内容 
				 //$("#DX_NAME").val(getdata.COMPANY_NAME);
		         $("#FAREN").val(getdata.FAREN);
		         $("#DX_UID").val(getdata.JS_COMPANY_UID);
		         $("#ADDRESS").val(getdata.ADDRESS);
		         $("#LIANXIREN").val(getdata.LIANXIREN);
		         $("#MOBILE").val(getdata.LIANXIREN_MOBILE);
		         //页面 2 
		         $("#BL_DCDX").val(getdata.COMPANY_NAME);
		   	     $("#PEOPLE").val(getdata.FAREN);
		   	     $("#CONCAT").val(getdata.LIANXIREN);
		   	     $("#PHONE").val(getdata.LIANXIREN_MOBILE);
		   	     $("#ADDRESSBILU").val(getdata.ADDRESS);  
		         $("#FAX").val(getdata.FAX);
		         //调查时间 ?
		         //页面 3
		         $("#BL_DCDX_ONE").val(companyName);
		   	     $("#PEOPLE_ONE").val(getdata.FAREN);
		   	     $("#FAREN_MOBILE").val(getdata.FAREN_MOBILE);
		   	     $("#CONCAT_ONE").val(getdata.LIANXIREN);
		   	     $("#PHONE_ONE").val(getdata.LIANXIREN_MOBILE);
		   	     $("#ADDRESSBILU_ONE").val(getdata.ADDRESS);
		         $("#POSTCODE").val(getdata.POSTCODE);
		        
		         $("#DX_TYPE").val(getdata.DUIXIANG_TYPE);
		       
		        
		   
		       }else{
		          alert("没有数据");
		       }
		        $("#DX_TYPE").append(thtml);	
			},
			
		});  
  
 
 
 }
function getReportName(){
  $.ajax({
			url:controllername+"?queryReportxx",
			type:"post",
			dataType:"json",
			success:function(response){
				var res = dealSpecialCharactor(response.msg);
				var resultmsgobj = convertJson.string2json1(res);
				var thtml = "";
		   if(resultmsgobj!=null && resultmsgobj !=''){//说明有数据
		       var data=resultmsgobj.response.data[0];
	            var datslist=resultmsgobj.response.data;
						$(datslist).each(function(){
							 thtml += "<option value="+this.ORGANIZE_UID+">"+this.ORG_NAME+"</option>";
						     
					});
			
		       }else{
		          alert("没有数据");
		       }
		        $("#REPORTPEOPLE").append(thtml);	
		        $("#REPORTPEOPLE_ONE").append(thtml);
			},
			
		});  
}  
 
 
 //获取当前工程id  ok
function setGongCheng_UID(){
	var GongCheng_UID=parent.document.getElementById("GDZXT_XM_ID").value;
	var gongcheng_uid = $("#gongcheng_uid").val(GongCheng_UID);
}
 //验证电话号码
function checkPhone(){
	var phoneNumber=/^1\d{10}$|^0\d{2,3}-?\d{7,8}$/;
	var userphone=$("#MOBILE").val();
	if($("#MOBILE").val()!=""&&!phoneNumber.test(userphone)){	
	   alert("您输入的电话号码格式有误,请您重新输入!");	
		//$("#SHOWRESPONSE").show();
		//$("#MOBILE").hide();
		return;
	  }

}
 
function findSelectOne(){ //jquery获取复选框值 
var chk_value =[]; 
$('input[name="LA_LAIYUAN"]:checked').each(function(){ 
chk_value.push($(this).val()); 
});
if(chk_value.length==0){
alert("你还没有选择任何内容!"); 
}

}
 
//选择框赋值案件来源

//调查事项
function change(id){
   var s = $("#"+id).val();
   $("#changeSel").val(s);
	
}

function selectSomeOne(id){
var uid= $("#"+id).val();
$("REPORTPEOPLE").val(uid);
$("REPORTPEOPLE_ONE").val(uid);

}

//获取 选择的当前 对象并 确定 类型
function selectCP(id){
 var JS_COMPANY_UID = $("#"+id).val();
  getMassage(JS_COMPANY_UID);
 
  
}
/* function check(id){
   var s = $("#"+id).val();
   $("#DX_TYPE").val(s);
	
} */

/**
  页面1 框值获取(默认及选择值)
 **/
window.onload = function(){
//通过名字获取  getElementsByName
//var obj = document.getElementsByName("CF_TYPES");
//通过标签获取  getElementsByTagName
var obj = document.getElementsByTagName("input");
    for(var i=0; i<obj.length; i ++){
        if(obj[i].checked){
        $("#SELECTTYPE").val((obj[i].value));
          var getValType=obj[i].value;
          if(getValType ==='GC'){
            $("#CF_TYPES").val("工程类");
          }else if(getValType ==='HB'){
            $("#CF_TYPES").val("环保类");
          }else if (getValType ==='PS'){
            $("#CF_TYPES").val("排水类");
          }else if(getValType ==='RQ'){
            $("#CF_TYPES").val("燃气类");
          }
        }
    }
}

function selectGC(){
 var obj = document.getElementsByTagName("input");
    for(var i=0; i<obj.length; i ++){
        if(obj[i].checked){
          $("#SELECTTYPE").val((obj[i].value));
           var getValType=obj[i].value;
          if(getValType ==='GC'){
            $("#CF_TYPES").val("工程类");
          }else if(getValType ==='HB'){
            $("#CF_TYPES").val("环保类");
          }else if (getValType ==='PS'){
            $("#CF_TYPES").val("排水类");
          }else if(getValType ==='RQ'){
            $("#CF_TYPES").val("燃气类");
          }
        }
    }

} 




//增加 问答
var xuhao = 1;
function addQW(demo){
        ++xuhao;
        $("#XUHAO").val(xuhao);
		$("#XUHAO_Other").val(xuhao);
		//alert($("#needDiv").html());
        var cloneNew = $("#needDiv").clone(true);
		$(cloneNew).removeAttr("style");
		$("#needScroll").append(cloneNew);
		//$(demo).hide();
}

function removeQW(demo){
if($("#needScroll div").size()==2){return;}
		var tr_index = $("#needScroll div").index($(demo).closest("div"));
		
		$(demo).closest("div").remove();
}
 
//退出点击事件
 function btnExit(){ 
 alert("信息确认,您真的要退出吗？");
		var a=$(this).manhuaDialog.close();
}

//下一步	
	var count = 1;
function  btnNext(){
	count++;
	
	if(count==1){
		$("#reportIng").css({width:"33.3333333333%"});
    	$("#reportIng").html("违法案件调查报告");
    	$("#DT1").fadeIn("slow");	
   	 	$("#DT2").css("display","none");
   	    $("#DT3").css("display","none");
	}else if(count==2){
		$("#reportIng").css({width:"66.666666666%"});
		$("#reportIng").html("现场检查/调查笔录表笔录中");
	   	$("#DT1").css("display","none");
   	 	$("#DT2").fadeIn("slow");
   	    $("#DT3").css("display","none");
   	    
   	    // $("#BL_DCDX").val($("#DX_NAME").val());
   	     $("#PEOPLE").val($("#FAREN").val());
   	     $("#CONCAT").val($("#LIANXIREN").val());
   	     $("#ADDRESSBILU").val($("#ADDRESS").val());
   	     $("#PHONE").val($("#MOBILE").val());

       
   	   // var value = document.getElementById("DX_NAME").value;//取得原来的值
       // document.getElementById("BL_DCDX").value = value //赋新的框值，
	}else if(count==3){
	  
	     $("#BL_DIAOCHA_DATE").val($("#getTime").val());
	    // $("#BL_DCDX_ONE").val($("#BL_DCDX").val());
   	     $("#PEOPLE_ONE").val($("#PEOPLE").val());
   	     $("#CONCAT_ONE").val($("#CONCAT").val());
   	     $("#ADDRESSBILU_ONE").val($("#ADDRESSBILU").val());
   	     $("#PHONE_ONE").val($("#PHONE").val()); 
	    $("#btnAdd").css("display","block");
		$("#reportIng").css({width:"99.999999999%"});
		$("#reportIng").html("违法行为立案登记表登记中");
		$("#DT3").fadeIn("slow");	
		$("#DT1").css("display","none");
   	    $("#DT2").css("display","none");
	}	
	
	if(count>=1&&count<3){
	$("#btnNext").css("display","block");	
	}else{
	$("#btnNext").css("display","none");	
	}
	if(count>=2&&count<4){
	$("#btnPrev").css("display","block");		
	}else{
	$("#btnPrev").css("display","none");	
	}
	
	

}
//上一步  2  3
function btnPrev(){
	count--;
	if(count==1){
	  $("#btnAdd").css("display","none");
		$("#reportIng").css({width:"33.3333333333%"});
    	$("#reportIng").html("违法案件调查报告");
    	$("#DT1").fadeIn("slow");//css("display","block");
   	 	$("#DT2").css("display","none");
   	    $("#DT3").css("display","none");
   	    
   	   
	}else if(count==2){
	$("#btnAdd").css("display","none");
		$("#reportIng").css({width:"66.666666666%"});
		$("#reportIng").html("现场检查/调查笔录表笔录中");
		$("#DT3").css("display","none");
		$("#DT2").fadeIn("slow");
	    $("#DT1").css("display","none");
	    
	}
	
	//按钮的 显示隐藏
	if(count>=1&&count<3){
	$("#btnNext").css("display","block");	
	}else{
	$("#btnNext").css("display","none");
	}
	if(count>=2&&count<4){
	$("#btnPrev").css("display","block");	
	}else{
	$("#btnPrev").css("display","none");
	}
	
	}	

</script>
<style>
#bar{
  position:fixed;
  width:96%;
  height:25px;
  
}

</style>
</head>
<body >
<app:dialogs/>
<div class="container-fluid" id="DTone"  >
	<div class="row-fluid">
		<div class="B-small-from-table-autoConcise" style="height:99%">
			<form method="post" id="queryForm">
				<table class="B-table" >
					<!--可以再此处加入hidden域作为过滤条件 -->
					<TR style="display: none;">
						<TD class="right-border bottom-border"></TD>
						<TD class="right-border bottom-border">
							<INPUT type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<=" />
						</TD>
					</TR>
					<!--可以再此处加入hidden域作为过滤条件 -->
				</table>
			 </form>
	        <div id="bar" class="progress progress-striped active" width="100%">
	      	  <div id="reportIng" class="bar" style="width:33.333333333%;font-size:20px;">违法案件调查报告进行中</div>
	        </div>             
			<div style="height:30px;"></div>
		<!------------------------------- 第一个 table style="display:none;"------------------------------->
		 <form method="post" role="form" id="zgtzdForm" action="${pageContext.request.contextPath }/xzcf/XZCFController?insert">
		    <input id="STATUS" type="hidden" fieldname="STATUS" name="STATUS" value="1">
			<input id="DX_UID" type="hidden" fieldname="DX_UID" name="DX_UID" value="">
			<input  id="gongcheng_uid" type="hidden"  fieldname="GONGCHENG_UID"  name="GONGCHENG_UID" value=""/>
	       <table width="100%"  class="table-hover table-activeTd B-table" id="DT1">
	             <thead style="display:none;">
                	<tr>
                		<th fieldname=""  tdalign="center" width="15%" >1</th>
                		<th fieldname=""  tdalign="center" width="40%" >&nbsp;2&nbsp;</th>
						<th fieldname=""  tdalign="center" width="15%" >&nbsp;3&nbsp;</th>
						<th fieldname=""  tdalign="center"  width="15%" >&nbsp;4&nbsp;</th>
						<th fieldname=""  tdalign="center"  width="15%" >&nbsp;5&nbsp;</th>
                	</tr>
	             </thead>
	             <tbody>
	               <tr>
	                 <th  class="text-left" >类&nbsp;&nbsp;&nbsp;别&nbsp;</th>
	                 <th id="SELECTTYPE"  colspan="4"  colindex=2 tdalign="center" onclick="selectGC();">工程类<input id="CF_TYPES_ONE" fieldname="CF_TYPES" name="CF_TYPES" type="radio" checked="checked"   value="GC"/>环保类<input id="CF_TYPES_TWO" fieldname="CF_TYPES" name="CF_TYPES" fieldname="CF_TYPES" name="CF_TYPES" type="radio"   value="HB"/>&nbsp;&nbsp;</input>排水类<input id="CF_TYPES_THREE" fieldname="CF_TYPES" name="CF_TYPES" type="radio"  value="PS"/>燃气类<input id="CF_TYPES_FIVE" fieldname="CF_TYPES" name="CF_TYPES" type="radio"  value="RQ"/>
	                 </th>
	               </tr>
	              <tr>
	                <th class="text-left">案&nbsp;&nbsp;&nbsp;由</th>
		       	 	<td class="bottom-border right-border"  colspan="4">
		       	 	<input class="span12"  id="ANYOU" type="text"  fieldname="ANYOU" name = "ANYOU" value="监理单位未健全质量体系" />	         	        	
		       	 	</td>
	               </tr>
	               
	               <tr>
					<th  class="text-left">当事人</th>
		       	 	<td  colspan="2" id="DX_NAME01"   >	
		       	 	 <select class="span12"  id="DX_NAME"  fieldname="DX_NAME" name = "DX_NAME" type="text" onchange="selectCP(this.id)" >
			       	 	   <option style="display:none;" id="DX_NAME01" value="456">测试企业</option>
			       	 	   <option style="display:none;" id="DX_NAME02" value="55">无锡国家高新技术产业开发区市政公用事业有限公司</option>
			       	 	   <option style="display:none;" id="DX_NAME03" value="99">无锡苏诚建设顾问有限公司</option>
			       	 	   <option style="display:none;" id="DX_NAME04" value="341">赵新建</option>
			       	 	   <option style="display:none;" id="DX_NAME05" value="2302">丁兴</option> 
			       	 </select>
		       	 	</td>
		       	 	<td>
		       	 	 <select class="span12" disabled="disabled" id="DX_TYPE" type="text"  fieldname="DX_TYPE" name = "DX_TYPE" value=""  >
			       	 	   <option id="DX_TYPE01" value="JSDW">建设单位</option>
						   <option id="DX_TYPE02" value="SGDW">施工单位</option>
						   <option id="DX_TYPE03" value="JLDW">监理单位</option>
						   <option id="DX_TYPE04" value="SGRY">施工人员</option>
						   <option id="DX_TYPE05" value="XMJL">监理项目岗位</option>
						   <option id=" /DX_TYPE06" value="ZJ">总监</option>   <!--onchange="check(this.id)" -->
			       	 </select>
		       	 	</td>
		       	 	
	               </tr>
	               
	               <tr>
					<th  class="text-left" >法定代表人</th>
		       	 	<td class="bottom-border right-border" colspan="2" >
		       	 	<input class="span12"  id="FAREN" type="text"  fieldname="FAREN" name = "FAREN"  value="" />	         	        	
		       	 	</td>
		       	 	<th text-align="center" width="15%">联系人</th>
		       	 	<td class="bottom-border right-border">
		       	 	<input class="span12"  id="LIANXIREN" type="text"  fieldname="LIANXIREN" name = "LIANXIREN"  value=""/>	         	        	
		       	 	</td>
	               </tr>
	               
	                <tr>
					 <th class="text-left">单位详细地址</th>
		       	 	 <td class="bottom-border right-border" colspan="2" >
		       	 	   <input class="span12"  id="ADDRESS" type="text"  fieldname="ADDRESS" name = "ADDRESS" value="无锡市运河东路555号A楼3206号" />	         	        	
		       	 	 </td>
		       	 	 <th text-align="center" width="15%">电&nbsp;&nbsp;&nbsp;话</th>
		       	 	 <td class="bottom-border right-border" >
		       	 	 <%--datatype="m" nullmsg="请输入联系人电话！" errormsg="" class="Validfrom_error" --%>
		       	 	  <input class="span12"  id="MOBILE" onclick="checkPhone();" type="text"  fieldname="MOBILE" name = "MOBILE"  value="13861848439" />
		       	 	 </td>
	               </tr>
	               
	               <tr>
	               <th colspan="5" class="text-left" >调&nbsp;查&nbsp;经&nbsp;过：</th>
	               </tr>
	               
	               <tr>
	                 <th colspan="5"  colindex=2 tdalign="center" >
					      <textarea onmouseover="focus()" onfocus="select()" class="span12" rows="2"  id=""  fieldname="DC_JINGGUO" name = "DC_JINGGUO" >
我站在2015年6月11日巡查发现新区银鹰文体新建车间项目已经施工至主体结束，监理单位相关人员存在长期不到岗的现象。现场验收存在弄虚作假现象，该项目未健全相关质量管理体系。
					      </textarea>
					 </th>
	                </tr>
	                
	               <tr>
	               <th colspan="5" class="text-left" >查明的事实和证据：</th>
	               </tr>
	               <tr>
	                 <th colspan="5"  colindex=2 tdalign="center"    >
					      <textarea onmouseover="focus()" onfocus="select()" class="span12" rows="1"  id="DC_SHISHI_ZHENGJU" fieldname="DC_SHISHI_ZHENGJU" name = "DC_SHISHI_ZHENGJU"  >
见现场调查笔录和现场照片					      
					      </textarea>
					 </th>
	                </tr>
	                
	                <tr>
		               <th colspan="5" class="text-left" >处理依据：</th>
		            </tr>
		            
		            <tr>
		                 <th colspan="5"  colindex=2 tdalign="center" >
						      <textarea onmouseover="focus()" onfocus="select()" class="span12" rows="2"  id="DC_CHULI_YIJU"    fieldname="DC_CHULI_YIJU" name = "DC_CHULI_YIJU" >
经查实，该单位的上述行为违反了《江苏省房屋建筑和市政基础设施施工程质量监督管理办法》第20条第1款的有关规定。
依据《江苏省房屋建筑和市政基础设施施工程质量监督管理办法》第26条第3款相关规定处理。
                              </textarea>
						 </th>
	                </tr>
	                
	                <tr>
	                
		               <th colspan="5" class="text-left" >处理意见：</th>
		            </tr>
		            <tr>
		                 <th colspan="5"  colindex=2 tdalign="center" >
						      <textarea onmouseover="focus()" onfocus="select()" class="span12" rows="3"  id="DC_CHULI_YIJIAN"  fieldname="DC_CHULI_YIJIAN" name = "DC_CHULI_YIJIAN" >
依据《江苏省房屋建筑和市政基础设施施工程质量监督管理办法》第26条第3款相关规定以及自由裁量，建议给予1万元--3万元的处罚 。</textarea>
						 </th>
	                </tr>
	              </tbody>
	           </table>
	          <!----------------------------------- table 2------------------------------------ -->
	           <table width="100%"  class="table-hover table-activeTd B-table" id="DT2" >
	             <thead  >
                	<tr>
                		<th   tdalign="center" width="15%" ></th>
                		<th   tdalign="center" width="20%" >&nbsp;&nbsp;</th>
						<th   tdalign="center" width="15%" >&nbsp;&nbsp;</th>
						<th   tdalign="center"  width="15%" >&nbsp;&nbsp;</th>
						<th   tdalign="center"  width="15%" >&nbsp;&nbsp;</th>
						<th   tdalign="center"  width="20%" >&nbsp;&nbsp;</th>
                	</tr>
	             </thead>
	             <tbody>
	               <tr>
	                 <th>调查对象</th>
	                 <td colspan="5" >
	                   <input class="span12" id="BL_DCDX" type="text" filename="BL_DCDX" name="BL_DCDX"  value=" "  readonly="readonly"></input>
	                 </td>
	               </tr>
	              
	               <tr>
					<th  >法人代表</th>
		       	 	<td class="bottom-border right-border" >
		       	 	<input class="span12"  id="PEOPLE" type="text"   value="" />	         	        	
		       	 	</td>
		       	 	<th  width="15%">联系人</th>
		       	 	<td class="bottom-border right-border">
		       	 	<input class="span12"  id="CONCAT" type="text"    value="" />	         	        	
		       	 	</td>
		       	 	<th  width="15%">电话</th>
		       	 	<td class="bottom-border right-border">
		       	 	<input class="span12"  id="PHONE" type="text"    value=""  />	         	        	
		       	 	</td>
	               </tr>
	                <tr>
					 <th >地址</th>
		       	 	 <td class="bottom-border right-border" colspan="3" >
		       	 	   <input class="span12"  id="ADDRESSBILU" type="text"   value="" />	         	        	
		       	 	 </td>
		       	 	 <th text-align="center" width="15%">传真</th>
		       	 	 <td class="bottom-border right-border" >
		       	 	  <input class="span12"  id="FAX" type="text"  fieldname="FAX" name = "FAX"  />	         	        	
		       	 	 </td>
	               </tr>
	               <tr>
	                 <th >调查事项</th>
			       	  <td colspan="3">
			       	 	  <select id="changeSel" fieldname="BL_DIAOCHA_SHIXIANG" name = "BL_DIAOCHA_SHIXIANG"  onchange="change(this.id)" >
			       	 	   <option id="OP1" value="例行监察">例行监察</option>
			       	 	   <option id="OP2" value="举报">举报</option>
			       	 	  </select>
			       	  </td>
		       	 	 <th >调查时间</th>
		       	 	 <td class="bottom-border right-border" >
		       	 	 <input id="getTime"  type="text" fieldtype="date" fieldformat="YYYY-MM-DD HH:mm:ss" fieldname="BL_DIAOCHA_DATE" name = "BL_DIAOCHA_DATE" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:ss:mm'})"  />
		       	 	</td>
	               </tr>
	               <!-- <tr >
	                 <th colspan="6" fieldname="" colindex=2 tdalign="center" >
					      <textarea onmouseover="focus()" onfocus="select()" class="span12" rows="3"  id="DC_JINGGUO"    fieldname="DC_JINGGUO" name = "DC_JINGGUO" >
告知：我们是无锡市新区建设环保局的执法人员，这是我们的执法证件，请你过目，
你享有以下权利：如果执法人员少于两人或执法证件与身份不符的，你有权拒绝调查。同时你
应当承担以下义务：如实提供有关资料，回答询问，不得拒绝，阻扰调查，请你配合我们，你是否听清楚了：
答：清楚。
					      </textarea>
					 </th>
	                </tr> -->
	                <tr>
	                <th class="text-left" filename="" colspan="6">相关情况问答：</th>
	                </tr>
	                <tr  style="width:100%;" rows="4" id="rowShow" >
	                <!--style="height:100px;width:100%;overflow:scroll;"  -->
                      <td colspan="6"  id="getDivVal" >
                        <div style="height:200px;width:100%;overflow:scroll;" id="needScroll">
                          <div style="display:none;" id="needDiv" width="900px;">
							问：<input id ="question01"  class="span12" style="display:none;" fieldname="Q_A" name="Q_A" value ="Q"/>
								<input id="XUHAO" type="hidden" class="span12" name="XUHAO" fieldname="XUHAO" value="000">
								<input id="CONTENTQ" class="span10" fieldname="CONTENT" name="CONTENT" /><br/>    
							答：<input id ="answer01"  class="span12" style="display:none;" fieldname="Q_A" name="Q_A" value ="A"/>
								<input id="XUHAO_Other" type="hidden" class="span12" name="XUHAO" fieldname="XUHAO" value="000">
								<input id="CONTENTA" class="span10" fieldname="CONTENT" name="CONTENT" />
							<img onclick="removeQW(this)" style="cursor: pointer;" title="删除" src="/wndjsbg/assets/img/details_close.png">
							<img onclick="addQW(this)" style="cursor: pointer;" title="增加" src="/wndjsbg/assets/img/details_open.png">	
			            </div>
                         <div width="900px;">
							问：<input id ="question"  class="span12" type="hidden" fieldname="Q_A" name="Q_A" value ="Q"/>
							    <input id="XUHAO_ONE" style="display:none;" class="span12" name="XUHAO" fieldname="XUHAO" value="1"/>
							    <input id="CONTENT" class="span10" fieldname="CONTENT" name="CONTENT" /><br/>    
							答：<input id ="answer"  class="span12" type="hidden" fieldname="Q_A" name="Q_A" value ="A"/>
								<input id="XUHAO_TWO" style="display:none;" class="span12" name="XUHAO" fieldname="XUHAO" value="1">
								<input id="CONTENT" class="span10" fieldname="CONTENT" name="CONTENT" />
							<img onclick="removeQW(this)" style="cursor: pointer;" title="删除" src="/wndjsbg/assets/img/details_close.png">
							<img onclick="addQW(this)" style="cursor: pointer;" title="增加" src="/wndjsbg/assets/img/details_open.png">	
                         </div>
                         
                        </div>
                       </td>
				    </tr>
	               <tr>
	               <th colspan="6" class="text-left" >现场其它情况：</th>
	               </tr>
	               <tr>
	                 <th colspan="6" colindex=2 tdalign="center" >
					      <textarea onmouseover="focus()" onfocus="select()" class="span12" rows="3"  id="BL_QINGKUANG" fieldname="BL_QINGKUANG" name = "BL_QINGKUANG" >
我站在2015年6月11日巡查发现新区银鹰文体新建车间项目已经施工至主体结束，监理单位相关人员存在长期不到岗的现象。
现场验收存在弄虚作假现象，该项目未健全相关质量管理体系。
					      </textarea>
					 </th>
	               </tr>
	                
	                <tr>
		               <th colspan="6" class="text-left" >处理意见：</th>
		            </tr>
		                <tr>
		                 <th colspan="6" colindex=2 tdalign="center" >
						      <textarea  onmouseover="focus()" onfocus="select()" class="span12" rows="3"  id="BL_YIJIAN" fieldname="BL_YIJIAN" name = "BL_YIJIAN" >
依据《江苏省房屋建筑和市政基础设施施工程质量监督管理办法》第26条第3款相关规定以及自由裁量，建议给予1万元--3万元的处罚 。		      
						      </textarea>
						 </th>
	                </tr>
	                <tr>
	                <!------------待定 -------- -->
	                   <th id="" >调查人员</th>
	                   <td colspan="3" >
	                     <select id="REPORTPEOPLE" onchange="selectSomeOne(this.id);" type="text"  fieldname="BL_DCRY1" name = "BL_DCRY1"  value="">
	                     </select>
	                   </td>
	                    <th id="" >调查人员</th>
	                   <td colspan="3" >
	                    <select id="REPORTPEOPLE_ONE" onchange="selectSomeOne(this.id);"  type="text"  fieldname="BL_DCRY2" name = "BL_DCRY2"  value="">
	                    </select>	
	                   </td>
	                </tr>
	                
	              </tbody>
	          </table>
	         <!-------------------------------- 3 ---------------------------------->
	          <table width="100%"  class="table-hover table-activeTd B-table" id="DT3"  >
	             <thead >
                	<tr>
                		<th fieldname=""  tdalign="center" width="15%" ></th>
                		<th fieldname=""  tdalign="center" width="10%" >&nbsp;&nbsp;</th>
						<th fieldname=""  tdalign="center" width="10%" >&nbsp;&nbsp;</th>
						<th fieldname=""  tdalign="center"  width="15%" >&nbsp;&nbsp;</th>
						<th fieldname=""  tdalign="center"  width="10%" >&nbsp;&nbsp;</th>
						<th fieldname=""  tdalign="center" width="10%" >&nbsp;&nbsp;</th>
						<th fieldname=""  tdalign="center"  width="15%" >&nbsp;&nbsp;</th>
						<th fieldname=""  tdalign="center"  width="15%" >&nbsp;&nbsp;</th>
                	</tr>
	             </thead>
	             <tbody>
	               <tr>
	                 <th >类&nbsp;&nbsp;&nbsp;别&nbsp;</th>
	                 <th colspan="7" >
	                    <input id="CF_TYPES"  class="span12" value="工程类" readonly="readonly"/>
	                 </th>
	               </tr>
	              <tr>
	                <th >当事人</th>
	                <th colspan="7"  colindex=2 tdalign="center" >
	                  <input id="BL_DCDX_ONE" class="span12" value="" readonly="readonly"/>
	                </th>
	               </tr>
	               <tr>
					<th >法定代表人</th>
		       	 	<td class="bottom-border right-border"  >
		       	 	<input class="span12"  id="PEOPLE_ONE" type="text"  value="" readonly="readonly" />	         	        	
		       	 	</td>
		       	 	<th text-align="center">联系电话</th>
		       	 	<td class="bottom-border right-border">
		       	 	<input class="span12"  id="FAREN_MOBILE" type="text"  fieldname="FAREN_MOBILE" name = "FAREN_MOBILE"  value=""/>	         	        	
		       	 	</td>
		       	 	<th text-align="center">联系人</th>
		       	 	<td class="bottom-border right-border"  >
		       	 	<input class="span12"  id="CONCAT_ONE" type="text"    value="" readonly="readonly"/>	         	        	
		       	 	</td>
		       	 	<th text-align="center">联系电话</th>
		       	 	<td class="bottom-border right-border">
		       	 	<input class="span12"  id="PHONE_ONE" type="text"   value="" readonly="readonly"/>	         	        	
		       	 	</td>
	               </tr>
	               
	                <tr>
					 <th  text-align="center">单位详细地址</th>
		       	 	 <td class="bottom-border right-border" colspan="5" >
		       	 	   <input class="span12"  id="ADDRESSBILU_ONE" type="text"   value=""/>	         	        	
		       	 	 </td>
		       	 	 <th text-align="center" >邮编</th>
		       	 	 <td class="bottom-border right-border" >
		       	 	  <input class="span12"  id="POSTCODE" type="text"  fieldname="POSTCODE" name = "POSTCODE"  value=""/>	         	        	
		       	 	 </td>
	               </tr>
	                 <tr>
					 <th  id="getValue" text-align="center">案件来源案件受理</th>
		       	 	 <td onclick="findSelectOne();" id="inputData" colspan="5" >
		       	 	 检查 <input  id="inputone" type="checkbox"   fieldname="LA_LAIYUAN" name = "LA_LAIYUAN" value="检查"/>
		       	 	 举报<input  id="inputone" type="checkbox"  fieldname="LA_LAIYUAN" name = "LA_LAIYUAN" value="举报" />
		       	 	 移送 <input  id="inputone" type="checkbox"  fieldname="LA_LAIYUAN" name = "LA_LAIYUAN" value="移送" />
		       	 	 交代 <input  id="inputone" type="checkbox" fieldname="LA_LAIYUAN" name = "LA_LAIYUAN" value="交代" />
		       	 	 其他 <input  id="inputone" type="checkbox"  fieldname="LA_LAIYUAN" name = "LA_LAIYUAN" value="其他" />
		       	 	 受理<input  id="inputone" type="checkbox"  fieldname="LA_LAIYUAN" name = "LA_LAIYUAN" value="受理" />      	        	
		       	 	 </td>
		       	 	 <th text-align="center" >案发时间</th>
		       	 	 <td class="bottom-border right-border" >
		       	 	  <input id="BL_DIAOCHA_DATE" class="span12"  type="text" fieldtype="date" fieldformat="YYYY-MM-DD HH:mm:ss"   onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:ss:mm'})" readonly="readonly"   />
	               </tr>
	               <tr>
	               <th   text-align="center" >案情简介</th>
	                 <th colspan="7"  colindex=2 tdalign="center" >
					      <textarea onmouseover="focus()" onfocus="select()" class="span12" rows="3"  id="LA_JIANJIE"    fieldname="LA_JIANJIE" name = "LA_JIANJIE" >
    我站在2015年6月11日巡查发现新区银鹰文体新建车间项目已经施工至主体结束，监理单位相关人员存在长期不到岗的现象。现场验收存在弄虚作假现象，该项目未健全相关质量管理体系。
					      </textarea>
					 </th>
	                </tr>
	                <tr>
	                 
	               <tr rows="2" >
	                 <th   text-align="center" >承办人意见</th>
	                 <th colspan="7"  colindex=2 tdalign="center" >
					      <textarea  onmouseover="focus()" onfocus="select()" class="span12" rows="3"  id="LA_CBR_YIJIAN" fieldname="LA_CBR_YIJIAN" name = "LA_CBR_YIJIAN" >
  经初步调查核实:该单位上述行为涉嫌违反《江苏省房屋建筑和市政基础设施工程质量监督管理办法》第20条第1款相关规定。建议立案调查。
					      </textarea>
					 </th>
	                </tr>
	                <tr>
	                     <th colspan="8"></th>
						 <!--  <th  text-align="center" >承办人:</th>
						 <th  id="chengban" fieldname="BL_DCRY1"  tdalign="center"  width="15%" value="小明" >&nbsp;1&nbsp;</th>
			       	 	 <th></th>
			       	 	 <th  text-align="center" >审核人:</th>
			       	 	 <th  id="shenghe" fieldname="BL_DCRY2"  tdalign="center"  width="15%" value="小智" >&nbsp;2&nbsp;</th>         	        	
			       	 	 <th  id="create_date" text-align="center" colspan="2" filename="CREATED_DATE" name="CREATED_DATE" >&nbsp;3&nbsp;</th>
			       	 	  
			       	 	   <div id="dis" style="float:right;">
					         <input id="date"  type="text" fieldtype="date" fieldformat="YYYY-MM-DD HH:mm:ss" fieldname="" name = "" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:ss:mm'})" readonly="readonly"   />
					        </div> -->
	                 </tr>
	              </tbody>
	           </table>
	         </form>
	 	   </div>
	 	   
	 	    <!-- onclick="btnExit();" -->
	 	    <div class="wizard-actions" style=" margin-top:80px;">
	           <button id="btnExit"  onclick="btnExit();" class="btn btn-danger "  style="width:72px;height:30px;" >
			          退出
			     <i class="ace-icon fa fa-times"></i>
			   </button>
			    <button  id="btnNext" onclick="btnNext()" class="btn-next btn btn-success"  style="width:72px;height:30px;float:right;margin:2px 2px 2px 2px;">
			         下一步 
			     <i class="ace-icon fa fa-arrow-right icon-on-right"></i>
			   </button>
			   
			   <button id="btnAdd" class="btn-next btn btn-success"  data-last="Finish" style="width:72px;height:30px;float:right;margin:2px 2px 2px 2px;display:none;">
			         保存
			     <i class="ace-icon fa fa-arrow-right icon-on-right"></i>
			   </button>
			  <button id="btnPrev"  onclick="btnPrev()" class="btn-prev btn btn-success" style="width:72px;float:right;margin:2px 2px 2px 2px;">
			     <i class="ace-icon fa fa-arrow-left" ></i> 
			         上一步
			   </button>
	       </div>
	 </div>     
</div>

<div align="center">
	<FORM name="frmPost" method="post" style="display: none" target="_blank">
		<!--系统保留定义区域-->
		<input type="hidden" name="queryXML"/> 
		<input type="hidden" name="txtXML"/>
		<input type="hidden" name="txtFilter" order="asc" fieldname="" id="txtFilter"/>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>


</body>
</html>