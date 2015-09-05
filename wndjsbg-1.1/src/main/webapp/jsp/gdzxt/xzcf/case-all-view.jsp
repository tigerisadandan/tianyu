<!DOCTYPE html>
<%@page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext"%>
<%@page import="com.ccthanking.framework.common.User" %>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<!-- 添加的 样式 及页面  -->
<%
  String xzcfuid = request.getParameter("xzcfuid");
 %>
<app:base/>
<title>添加页面首页</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/ywlz/css/default.css"> </LINK>

<LINK type="text/css" rel="stylesheet" href="/wndjsbg/css/style.css"> </LINK>
<link type="text/css" rel="stylesheet" href="/wndjsbg/css/style-responsive.css">
<link type="text/css" rel="stylesheet" href="/wndjsbg/css/bootstrap.css">
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername = "${pageContext.request.contextPath }/xzcf/XZCFController";

var xzcfuid = "<%=xzcfuid%>";
//alert(xzcfuid);
//页面初始化
$(function() {
    setGongCheng_UID();
    getReportName();
    init();
    //退出
  $("#btnClose").bind("click", function(){
		var a=$(this).manhuaDialog.close();
	});
	
    //保存
  $("#btnSave").bind("click",function(){
		    var data = Form2Json.formToJSON(zgtzdForm);
			var data1 = defaultJson.packSaveJson(data);
	    	defaultJson.doInsertJson(controllername + "?update&xzcfuid="+xzcfuid, data1);
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
    $.ajax({
			url:controllername+"?queryXzcfXX&xzcfuid="+xzcfuid,
			type:"post",
			dataType:"json",
			success:function(response){
				var res = dealSpecialCharactor(response.msg);
				var resultmsgobj = convertJson.string2json1(res);
				 //长度大于1,说明有2条数据 有问答
		   if(resultmsgobj.response.data.length>=1){
		         $("#exsitDiv").hide();
			}else{//没有问答情况,隐藏问
		         $("#exsitDiv").show();
		         $("#exsitDiv0").hide();
		       
		    }
				var thtml = "";
		   if(resultmsgobj!=null && resultmsgobj !=''){//说明有数据
	          for(var i=0;i<resultmsgobj.response.data.length;i++){
		          var data01 = resultmsgobj.response.data[i];
		          var biluuid = data01.BILU_WENDA_UID;
		          var xuhao = data01.XUHAO;
		          var Q_A = data01.Q_A;
		          var content = data01.CONTENT;
		        var thtml="";
		        var answerHtml="";
			  if((i&1)===0){//偶数
				    thtml ="<div width=\"900px;\" id=\"exsitDiv"+i+"\">"+
				              "问： <input id =\"question\"  class=\"span12\" style=\"display:none;\"  fieldname=\"Q_A\" name=\"Q_A\" value =\""+Q_A+"\"/>"+
							    "<input id=\"XUHAO_TWO\" style=\"display:none;\" class=\"span12\" name=\"XUHAO\" fieldname=\"XUHAO\" value=\""+xuhao+"\"/>"+
								"<input id=\"CONTENT\" class=\"span10\" fieldname=\"CONTENT\" name=\"CONTENT\" value=\""+content+"\"/><br/></div>";
				   $("#needScroll").append(thtml); 
				if($("#CONTENT").val() == null || $("#CONTENT").val() ==""){
		            $("#exsitDiv0").hide();
		          }
				 }else{//奇数
				 var n = i-1;
				   answerHtml = "答： <input id =\"answer\"  class=\"span12\" style=\"display:none;\" fieldname=\"Q_A\" name=\"Q_A\" value =\""+Q_A+"\"/>"+
								"<input id=\"XUHAO_TWO\" style=\"display:none;\" class=\"span12\" name=\"XUHAO\" fieldname=\"XUHAO\" value=\""+xuhao+"\">"+
								"<input id=\"CONTENT\"  class=\"span10\" fieldname=\"CONTENT\" name=\"CONTENT\" value=\""+content+"\"/>"+
							"&nbsp;<img onclick=\"removeQW(this)\" style=\"cursor: pointer;\" title=\"删除\" src=\"/wndjsbg/assets/img/details_close.png\">"+
							"&nbsp;<img onclick=\"addQW(this)\" style=\"cursor: pointer;\" title=\"增加\" src=\"/wndjsbg/assets/img/details_open.png\">";	
			      $("#exsitDiv"+n).append(answerHtml);
			
				 } 
		       }
		       
		      //获取 最大 的 序号值 并 加1 (页面初始化 显示 序号值)
		      var xuhaoshi =  resultmsgobj.response.data[resultmsgobj.response.data.length-1].XUHAO;
		       if(xuhaoshi == null || xuhaoshi =='' || xuhaoshi <1){
		          xuhaoshi = 0;
		       }
		       xuhaoshi++;
		        $("#XUHAO_ONE").val(xuhaoshi);
		        $("#XUHAO_Two").val(xuhaoshi);
		        $("#XUHAO").val(xuhaoshi);
		        $("#XUHAO_Other").val(xuhaoshi);
				var data=resultmsgobj.response.data[0];
				//工程类别  
				 $("#ANYOU").val(data.ANYOU);
		         $("#DX_NAME").val(data.DX_NAME);
		         $("#DX_TYPE").val(data.DX_TYPE);
		         $("#FAREN").val(data.FAREN);
		         $("#DX_UID").val(data.DX_UID);
		         $("#ADDRESS").val(data.ADDRESS);
		         $("#LIANXIREN").val(data.LIANXIREN);
		         $("#MOBILE").val(data.MOBILE);
		         $("#DC_JINGGUO").html(data.DC_JINGGUO);
		         $("#DC_SHISHI_ZHENGJU").html(data.DC_SHISHI_ZHENGJU);
		         $("#DC_CHULI_YIJU").html(data.DC_CHULI_YIJU);
		         $("#DC_CHULI_YIJIAN").html(data.DC_CHULI_YIJIAN);
		          //页面 2 
		         $("#BL_DCDX").val(data.BL_DCDX);
		   	     $("#PEOPLE").val(data.FAREN);
		   	     $("#CONCAT").val(data.LIANXIREN);
		   	     $("#PHONE").val(data.MOBILE);
		   	     $("#ADDRESSBILU").val(data.ADDRESS);  
		         $("#FAX").val(data.FAX);
		         $("#changeSel").val(data.BL_DIAOCHA_SHIXIANG);//调查事项
		         $("#BL_DIAOCHA_DATE").val(data.BL_DIAOCHA_DATE); 
		         $("#BL_QINGKUANG").html(data.BL_QINGKUANG);//现场其他情况
		         $("#BL_YIJIAN").val(data.BL_YIJIAN);
		         $("#REPORTPEOPLE").val(data.BL_DCRY1);
                 $("#REPORTPEOPLE_ONE").val(data.BL_DCRY2);
		         //调查时间 ??
		         //页面 3
		         $("#BL_DCDX_ONE").val(data.DX_NAME);
		   	     $("#PEOPLE_ONE").val(data.FAREN);
		   	     $("#FAREN_MOBILE").val(data.FAREN_MOBILE);
		   	     $("#CONCAT_ONE").val(data.LIANXIREN);
		   	     $("#PHONE_ONE").val(data.MOBILE);
		   	     $("#ADDRESSBILU_ONE").val(data.ADDRESS);
		         $("#POSTCODE").val(data.POSTCODE); 
		         $("#inputOne").val(data.LA_LAIYUAN);//案件来源
		         
		         //复选框 选中 ....>问题 
		         if(data.LA_LAIYUAN != null && data.LA_LAIYUAN != "" ){
		          var str = data.LA_LAIYUAN;
				   if(str.indexOf("检查") != -1){
				    $("#inputOne").attr("checked","checked");
				   }
				     if(str.indexOf("举报") != -1){
				    $("#inputTwo").attr("checked","checked");
				   }
				     if(str.indexOf("移送") != -1){
				     $("#inputThree").attr("checked","checked");
				   }
				     if(str.indexOf("交代") != -1){
				     $("#inputFour").attr("checked","checked");
				   }
				     if(str.indexOf("其他") != -1){
				    $("#inputFive").attr("checked","checked");
				   }
				     if(str.indexOf("受理") != -1){
				    $("#inputSix").attr("checked","checked");
				   }
		         }else{
		         }
		     
		         //案发时间
		         $("#BL_DIAOCHA_DATE_ONE").val(data.BL_DIAOCHA_DATE);  
		         $("#LA_JIANJIE").val(data.LA_JIANJIE);
		         $("#LA_CBR_YIJIAN").val(data.LA_CBR_YIJIAN);
		         
		         if(data.CF_TYPES == "GC"){
				 $("#CF_TYPES_ONE1").attr("checked","checked");
				 }else if(data.CF_TYPES == "HB"){
				 $("#CF_TYPES_ONE2").attr("checked","checked");
				 }else if(data.CF_TYPES == "PS"){
				 $("#CF_TYPES_ONE3").attr("checked","checked");
				 }else if(data.CF_TYPES == "RQ"){
				 $("#CF_TYPES_ONE4").attr("checked","checked");
				 }
		        
		       }else{
		        // $("#exsitDiv").show();
		        alert("没有获取到页面初始化数据!");
		       }
		      
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
							 thtml += "<option value=\""+this.ORGANIZE_UID+"\">"+this.ORG_NAME+"</option>";
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
//增加 问答
var xuhao;
function addQW(demo){
        xuhao =  $("#XUHAO").val();//1
        ++xuhao;
        $("#XUHAO").val(xuhao);
		$("#XUHAO_Other").val(xuhao);
        var cloneNew = $("#needDiv").clone(true);
		$(cloneNew).removeAttr("style");
		$("#needScroll").append(cloneNew);
}
function removeQW(demo){
if($("#needScroll div").size()==3){return;}
		var tr_index = $("#needScroll div").index($(demo).closest("div"));
		$(demo).closest("div").remove();
		xuhao--;
}

</script>

</head>
<body >
<app:dialogs/>
<div class="container-fluid" id="DTone"  >
	<div class="row-fluid">
		<div class="B-small-from-table-autoConcise">
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
			<div id="bar" class="progress progress-striped active" width="100%"  >
	      	  <div id="reportIng" class="bar" style="width:99.999999999%;font-size:20px;">违法案件调查报告进行中</div>
	        </div>
	        <div><h4></h4></div>
		<!------------------------------- 第一个 table style="display:none;"------------------------------->
		 <form method="post" role="form" id="zgtzdForm" action="${pageContext.request.contextPath }/xzcf/XZCFController?update">
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
	                 <th >类&nbsp;&nbsp;&nbsp;别&nbsp;</th>
	                 <th id="SELECTTYPE"  colspan="4"  colindex=2 tdalign="center" >
			                 工程类<input id="CF_TYPES_ONE1"  fieldname="CF_TYPES" name="CF_TYPES" type="radio"  value="GC"/>
			                 环保类<input id="CF_TYPES_ONE2"  fieldname="CF_TYPES" name="CF_TYPES" fieldname="CF_TYPES" name="CF_TYPES" type="radio" value="HB"/>
			                 排水类<input id="CF_TYPES_ONE3" fieldname="CF_TYPES" name="CF_TYPES" type="radio"  value="PS"/>
			                 燃气类<input id="CF_TYPES_ONE4"  fieldname="CF_TYPES" name="CF_TYPES" type="radio"  value="RQ"/>
	                 </th>
	               </tr>
	              <tr>
	                <th >案&nbsp;&nbsp;&nbsp;由</th>
		       	 	<td class="bottom-border right-border"  colspan="4">
		       	 	<input class="span12"  id="ANYOU" type="text"  fieldname="ANYOU" name = "ANYOU" value="监理单位未健全质量体系" />	         	        	
		       	 	</td>
	               </tr>
	               
	               <tr>
					<th  >当事人</th>
		       	 	<td  colspan="3"  >	
		       	 	 <input class="span12"  id="DX_NAME"  fieldname="DX_NAME" name = "DX_NAME" type="text" readonly="readonly"/>
		       	 	</td>
		       	 	<td style="width:15%">
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
					<th >法定代表人</th>
		       	 	<td class="bottom-border right-border" colspan="2" >
		       	 	<input class="span12"  id="FAREN" type="text"  fieldname="FAREN" name = "FAREN"  value="" />	         	        	
		       	 	</td>
		       	 	<th text-align="center" width="15%">联系人</th>
		       	 	<td class="bottom-border right-border">
		       	 	<input class="span12"  id="LIANXIREN" type="text"  fieldname="LIANXIREN" name = "LIANXIREN"  value=""/>	         	        	
		       	 	</td>
	               </tr>
	               
	                <tr>
					 <th>单位详细地址</th>
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
					      <textarea onmouseover="focus()" onfocus="select()" class="span12" rows="2"  id="DC_JINGGUO"  fieldname="DC_JINGGUO" name = "DC_JINGGUO" >
					      </textarea>
					 </th>
	                </tr>
	                
	               <tr>
	               <th colspan="5" class="text-left" >查明的事实和证据：</th>
	               </tr>
	               <tr>
	                 <th colspan="5"  colindex=2 tdalign="center"    >
					      <textarea onmouseover="focus()" onfocus="select()" class="span12" rows="1"  id="DC_SHISHI_ZHENGJU" fieldname="DC_SHISHI_ZHENGJU" name = "DC_SHISHI_ZHENGJU"  >
					      </textarea>
					 </th>
	                </tr>
	                
	                <tr>
		               <th colspan="5" class="text-left" >处理依据：</th>
		            </tr>
		            
		            <tr>
		                 <th colspan="5"  colindex=2 tdalign="center" >
						      <textarea onmouseover="focus()" onfocus="select()" class="span12" rows="2"  id="DC_CHULI_YIJU"    fieldname="DC_CHULI_YIJU" name = "DC_CHULI_YIJU" >
                              </textarea>
						 </th>
	                </tr>
	                
	                <tr>
	                
		               <th colspan="5" class="text-left" >处理意见：</th>
		            </tr>
		            <tr>
		                 <th colspan="5"  colindex=2 tdalign="center" >
						      <textarea onmouseover="focus()" onfocus="select()" class="span12" rows="3"  id="DC_CHULI_YIJIAN"  fieldname="DC_CHULI_YIJIAN" name = "DC_CHULI_YIJIAN" >
                              </textarea>
						 </th>
	                </tr>
	              </tbody>
	           </table>
	             <div style="height:10px;"></div>
	        <div id="bar" class="progress progress-striped active" width="100%"  >
	      	  <div id="reportIng" class="bar" style="width:99.999999999%;font-size:20px;">现场检查/调查笔录表</div>
	        </div>
	          <!----------------------------------- table 2------------------------------------ -->
	           <table width="100%"  class="table-hover table-activeTd B-table" id="DT2" >
	             <thead style="display:none;">
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
			       	 	   <option  value="例行监察">例行监察</option>
			       	 	   <option  value="举报">举报</option>
			       	 	  </select>
			       	  </td>
		       	 	 <th >调查时间</th>
		       	 	 <td class="bottom-border right-border" >
		       	 	 <input id="BL_DIAOCHA_DATE" class="span12" type="text" fieldtype="date" fieldformat="YYYY-MM-DD HH:mm:ss" fieldname="BL_DIAOCHA_DATE" name = "BL_DIAOCHA_DATE" readonly="redyonly" />
		       	 	</td>
	               </tr>
	                <tr>
	                <th class="text-left" filename="" colspan="6">相关情况问答：</th>
	                </tr>
	                <tr  style="width:100%;" rows="4" id="rowShow" >
	                <!--style="height:100px;width:100%;overflow:scroll;"  -->
                      <td colspan="6"  id="getDivVal" >
                        <div style="height:200px;width:100%;overflow:scroll;" id="needScroll">
                         <div  id="needDiv" style="display:none;" width="900px;">
							问：<input id ="question01"  class="span12" style="display:none;" fieldname="Q_A" name="Q_A" value ="Q"/>
								<input id="XUHAO" style="display:none;" class="span12" name="XUHAO" fieldname="XUHAO" value="">
								<input id="CONTENTQ" class="span10" fieldname="CONTENT" name="CONTENT" /><br/>    
							答：<input id ="answer01"  class="span12" style="display:none;" fieldname="Q_A" name="Q_A" value ="A"/>
								<input id="XUHAO_Other" style="display:none;" class="span12" name="XUHAO" fieldname="XUHAO" value="">
								<input id="CONTENTA" class="span10" fieldname="CONTENT" name="CONTENT" />
							<img onclick="removeQW(this)" style="cursor: pointer;" title="删除" src="/wndjsbg/assets/img/details_close.png">
							<img onclick="addQW(this)" style="cursor: pointer;" title="增加" src="/wndjsbg/assets/img/details_open.png">	
			            </div>
                        <div width="900px;" id="exsitDiv">
                                                   问：<input id ="question"  class="span12" style="display:none;" fieldname="Q_A" name="Q_A" value ="Q"/>
								<input id="XUHAO_ONE" style="display:none;" class="span12" name="XUHAO" fieldname="XUHAO" value="">
								<input id="CONTENT01" class="span10" fieldname="CONTENT" name="CONTENT" /><br/>    
							答：<input id ="answer01"  class="span12" style="display:none;" fieldname="Q_A" name="Q_A" value ="A"/>
								<input id="XUHAO_Two" style="display:none;" class="span12" name="XUHAO" fieldname="XUHAO" value="">
								<input id="CONTENTA02" class="span10" fieldname="CONTENT" name="CONTENT" />
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
					      </textarea>
					 </th>
	               </tr>
	                
	                <tr>
		               <th colspan="6" class="text-left" >处理意见：</th>
		            </tr>
		                <tr>
		                 <th colspan="6" colindex=2 tdalign="center" >
						      <textarea  onmouseover="focus()" onfocus="select()" class="span12" rows="3"  id="BL_YIJIAN" fieldname="BL_YIJIAN" name = "BL_YIJIAN" >
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
	           <div style="height:5px;"></div>
	         <div id="bar" class="progress progress-striped active" width="100%"  >
	      	  <div id="reportIng" class="bar" style="width:99.999999999%;font-size:20px;">违法行为立案登记表</div>
	         </div>
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
		       	 	 <td onclick="findSelectOne();" id="inputData" name="myTd" colspan="5" >
			       	 	 检查 <input  id="inputOne" type="checkbox"   fieldname="LA_LAIYUAN" name = "LA_LAIYUAN1" value="检查"  />
			       	 	 举报 <input  id="inputTwo" type="checkbox"  fieldname="LA_LAIYUAN" name = "LA_LAIYUAN2" value="举报" />
			       	 	 移送 <input  id="inputThree" type="checkbox"  fieldname="LA_LAIYUAN" name = "LA_LAIYUAN3" value="移送" />
			       	 	 交代 <input  id="inputFour" type="checkbox" fieldname="LA_LAIYUAN" name = "LA_LAIYUAN4" value="交代" />
			       	 	 其他 <input  id="inputFive" type="checkbox"  fieldname="LA_LAIYUAN" name = "LA_LAIYUAN5" value="其他" />
			       	 	 受理<input  id="inputSix" type="checkbox"  fieldname="LA_LAIYUAN" name = "LA_LAIYUAN6" value="受理" />      	        	
		       	 	 </td>
		       	 	 <th text-align="center" >案发时间</th>
		       	 	 <td class="bottom-border right-border" >
		       	 	 <!--  <input id="BL_DIAOCHA_DATE_ONE" class="span12"  type="text" fieldtype="date" fieldformat="YYYY-MM-DD HH:mm:ss"   onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:ss:mm'})" readonly="readonly"   /> -->
	                  <input id="BL_DIAOCHA_DATE_ONE" class="span12"  type="text" fieldtype="date" fieldformat="YYYY-MM-DD HH:mm:ss"  readonly="readonly"   />
	               </tr>
	               <tr>
	               <th   text-align="center" >案情简介</th>
	                 <th colspan="7"  colindex=2 tdalign="center" >
					      <textarea onmouseover="focus()" onfocus="select()" class="span12" rows="3"  id="LA_JIANJIE"    fieldname="LA_JIANJIE" name = "LA_JIANJIE" >
					      </textarea>
					 </th>
	                </tr>
	                <tr>
	                 
	               <tr rows="2" >
	                 <th   text-align="center" >承办人意见</th>
	                 <th colspan="7"  colindex=2 tdalign="center" >
					      <textarea  id="LA_CBR_YIJIAN" fieldname="LA_CBR_YIJIAN" name = "LA_CBR_YIJIAN" onmouseover="focus()" onfocus="select()" class="span12" rows="3"  >
					      </textarea>
					 </th>
	                </tr>
	                <tr>
	                     <th colspan="8"></th>
						 
	                 </tr>
	              </tbody>
	           </table>
	         </form>
	         <div style="display:none;>
	          <input id="ss" type="text" style="display:none;"/>
	         </div>
	         <div class="wizard-actions" style=" margin-top:80px;">
	           <button id="btnClose" class="btn btn-danger "  style="width:72px;height:30px;" >
			         关闭
			     <i class="ace-icon fa fa-times"></i>
			   </button>
			    <button  id="btnSave"  class="btn-next btn btn-success"  style="width:72px;height:30px;float:right;margin:2px 2px 2px 2px;">
			        保存
			     <i class="ace-icon fa fa-arrow-right icon-on-right"></i>
			   </button>
	       </div> 
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