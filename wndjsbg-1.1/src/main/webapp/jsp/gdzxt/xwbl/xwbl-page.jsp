<!DOCTYPE html>
<%@page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext"%>
<%@page import="com.ccthanking.framework.common.User" %>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<!-- 添加的 样式 及页面  -->

<app:base/>
<title>询问笔录</title>

<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/ywlz/css/default.css"> </LINK>

<LINK type="text/css" rel="stylesheet" href="/wndjsbg/css/style.css"> </LINK>
<link type="text/css" rel="stylesheet" href="/wndjsbg/css/style-responsive.css">
<link type="text/css" rel="stylesheet" href="/wndjsbg/css/bootstrap.css">
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername = "${pageContext.request.contextPath }/xzcf/XZCFController"

//页面初始化
$(function() {

setGongCheng_UID();
   	$("#DT1").show();
   	//$("#DT2").hide();
    $("#DT3").hide();
    $("#btnPrev").hide();
        
    //保存
	$("#btnAdd").bind("click",function(){
	        var data = Form2Json.formToJSON(zgtzdForm);
			alert("发送后端 的数据是 ===>"+data);
			var data1 = defaultJson.packSaveJson(data);
	    	defaultJson.doInsertJson(controllername + "?insertXwbl",data1);
		    var a=$(window).manhuaDialog.getParentObj();
	      //  a.init();
		    $(window).manhuaDialog.close();
		/* $.ajax({
			type:"POST",
			data:$("#zgtzdForm").serialize(),
			url:controllername+"?insert",
			datatype:"JSON",
			success:function(msg){
				
			},
			error:function(e){
				
			}
		});
		var a=$(window).manhuaDialog.getParentObj();
	    a.init();
		$(window).manhuaDialog.close();*/
	}); 
 });

//获取当前工程id  ok
function setGongCheng_UID(){
	var GongCheng_UID=parent.document.getElementById("GDZXT_XM_ID").value;
	var gongcheng_uid = $("#GONGCHENG_UID").val(GongCheng_UID);
}


//增加 问答  1
var xuhao = 1;
function addWD(demo){
   xuhao =  $("#XUHAOWEN").val();//1
alert("dangiqna d xuhaoshi "+xuhao);
       ++xuhao;
        $("#XUHAOWEN").val(xuhao);
		$("#XUHAODA").val(xuhao);
		$("#XUHAO").val(xuhao+2);
		$("#XUHAO_Other").val(xuhao+2);
		$("#XUHAON1").val(xuhao+1);
		$("#XUHAON2").val(xuhao+1);
		alert($("#XUHAOWEN").val()+"===="+$("#XUHAODA").val()+"=============="+$("#XUHAO").val());
        var cloneNew = $("#needDiv").clone(true);
        $(cloneNew).removeAttr("style");
		$("#needScroll").append(cloneNew);
			/* $("#needScroll").append('"<div  id=\"needDiv\" width=\"900px;\">"'+
							'"问：<input id =\"question01\"  class=\"span12\" style=\"display:none;\" fieldname=\"Q_A\" name=\"Q_A\" value =\"Q\"/>"'+
								'"<input id=\"XUHAOWEN\" style=\"display:none;\" class=\"span12\" name=\"XUHAO\" fieldname=\"XUHAO\" value=\"0\">"'+
								'"<input id=\"CONTENTWEN\" class=\"span10\" fieldname=\"CONTENT\" name=\"CONTENT\" /><br/>  "'+  
							'"答：<input id =\"answer01\"  class=\"span12\" style=\"display:none;\" fieldname=\"Q_A\" name=\"Q_A\" value =\"A\"/>"'+
								'"<input id=\"XUHAODA\" style=\"display:none;\" class=\"span12\" name=\"XUHAO\" fieldname=\"XUHAO\" value=\"0\">"'+
								'"<input id=\"CONTENTDA\" class=\"span10\" fieldname=\"CONTENT\" name=\"CONTENT\" />"'+
							'"<img onclick=\"removeWD(this)\" style=\"cursor: pointer;\" title=\"删除\" src=\"/wndjsbg/assets/img/details_close.png\">"'+
							'"<img onclick=\"addWD(this)\" style=\"cursor: pointer;\" title=\"增加\" src=\"/wndjsbg/assets/img/details_open.png\">"'+	
			            '"</div>"'); */
		
}
function removeWD(demo){
if($("#needScroll div").size()==2){return;}
		var tr_index = $("#needScroll div").index($(demo).closest("div"));
		$(demo).closest("div").remove();
}
//2
function addQW(demo){
    var xuhao =  $("#XUHAO").val();
        ++xuhao;
        $("#XUHAO").val(xuhao);
		$("#XUHAO_Other").val(xuhao);
		alert( $("#XUHAO").val()+"===="+ $("#XUHAO_Other").val());
        var cloneNew = $("#needDiv01").clone(true);
        $(cloneNew).removeAttr("style");
        $("#needScorlDiv").append(cloneNew);
		
}
function removeQW(demo){
if($("#needScorlDiv div").size()==2){return;}
		var tr_index = $("#needScorlDiv div").index($(demo).closest("div"));
		$(demo).closest("div").remove();
}

//下一步
var count = 1;
function  btnNext(){
	count++;
	if(count==1){
    	$("#DT1").css("display","block");	
   	    $("#DT3").css("display","none");
	}else if(count==2){
	 $("#getTitle").text("材料纸");
	    $("#btnAdd").css("display","block");
		$("#DT3").fadeIn("slow");
		$("#DT1").css("display","none");
	}
	
	if(count>=1&&count<2){
	$("#btnNext").css("display","block");	
	}else{
	$("#btnNext").css("display","none");	
	}
	if(count>=2&&count<3){
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
	    $("#getTitle").text("询问笔录");
    	$("#DT1").fadeIn("slow");
   	    $("#DT3").css("display","none");
	} else if(count==2){
	    $("#getTitle").text("材料纸");
	    $("#btnAdd").css("display","block");
		$("#DT3").css("display","none");
		$("#DT1").fadeIn("slow");
	    
	} 
	
	//按钮的 显示隐藏
	if(count>=1&&count<2){
	$("#btnNext").css("display","block");	
	}else{
	$("#btnNext").css("display","none");
	}
	if(count>=2&&count<3){
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
<body>
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
			 <div style="height:5px;"></div>
	         <div>
			     <table  width="100%" class=" yw-title" >
	                  <thead>
	                    <tr>
	                        <th >
	                        <h4 align="center"  >
	                        <span id="getTitle">询问笔录</span>
						      	<!-- <span class="pull-right">
						      		<button id="btnQuery" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">全部处罚</button>
						      		<button id="btnLook" class="btn" data-last="Finish" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">
								         打印处罚清单
								   </button>
						      		<button id="btnAdd" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">添加处罚</button>	
						      	</span> -->
			                </h4>
	                        </th>
	                    </tr>
	              	<tbody></tbody>
                 </table>
            </div>           
		<!------------------------------- 第一个 table style="display:none;"------------------------------->
		 <form method="post" role="form" id="zgtzdForm"  action="${pageContext.request.contextPath }/xzcf/XZCFController?insertXwbl" >
			<input  id="GONGCHENG_UID" type="hidden"  fieldname="GONGCHENG_UID"  name="GONGCHENG_UID" value=""/>
	      <div id="DT1" style="height:750px;">
	       <table width="100%"  class="table-hover table-activeTd B-table"  >
	             <thead style="display:none;" >
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
	                 <th>案由：</th>
	                 <td colspan="5" id="checkObject">
	                   <input class="span12" id="ANYOU" type="text" filename="ANYOU" name="ANYOU"  value="AC---- "  ></input>
	                 </td>
	               </tr>
	              
	               <tr>
					<th  >时间：</th>
		       	 	<td class="bottom-border right-border" colspan="5" >
		       	 	<input id="getTime" fieldname="BEGIN_DATE" name = "BEGIN_DATE" type="text" fieldtype="date" fieldformat="YYYY-MM-DD HH:mm:ss"  class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:ss:mm'})"  />         	        	
		       	 	</td>
	               </tr>
	               
	                <tr>
					 <th >地点：</th>
		       	 	 <td class="bottom-border right-border" colspan="5" >
		       	 	   <input class="span12"  id="DIDIAN" type="text" fieldname="DIDIAN" name="DIDIAN"  value="" />	         	        	
		       	 	 </td>
	               </tr>
	               
	               <tr>
	                 <th >调查询问人：</th>
			       	  <td colspan="3">
			       	 	  <!-- <select id="changeSel" fieldname="BL_DIAOCHA_SHIXIANG" name = "BL_DIAOCHA_SHIXIANG"  onchange="change(this.id)" >
			       	 	   <option id="OP1" value="例行监察">例行监察</option>
			       	 	   <option id="OP2" value="举报">举报</option>
			       	 	  </select> -->
			       	 	 <input class="span12" fieldname="XUNWEN_REN1" name = "XUNWEN_REN1" id="XUNWEN_REN1" type="text"   value="" />
			       	  </td>
		       	 	 <th >记录人：</th>
		       	 	 <th class="bottom-border right-border" >
		       	 	  <input class="span12" fieldname="JILU_REN" name = "JILU_REN"  id="JILU_REN" type="text"   value="" />
		       	 	 </th>
	               </tr>
	                <tr>
	                 <th>被调查询问人：</th>
	                 <td class="bottom-border right-border" >
		       	 	  <input class="span12" fieldname="BEIXUNWEN_REN" name = "BEIXUNWEN_REN"  id="BEIXUNWEN_REN" type="text"   value="" />
		       	 	 </td>
		       	 	 <th>性别：</th>
	                 <td class="bottom-border right-border" >
		       	 	  <input class="span12" fieldname="XINGBIE" name = "XINGBIE"  id="XINGBIE" type="text"   value="" />
		       	 	 </td>
		       	 	 <th>出生年月：</th>
	                 <td class="bottom-border right-border" >
	                 <!-- fieldtype="date" fieldformat="YYYY-MM-DD"   onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  -->
		       	 	  <input id="CSNY" fieldname="CSNY" name = "CSNY" class="span12" type="text"  />
		       	 	 </td>
	                </tr>
	                
	                <tr>
	                 <th >工作单位：</th>
			       	  <td colspan="3">
			       	 	 <input class="span12" fieldname="DANWEI" name = "DANWEI"  id="DANWEI" type="text"   value="" />
			       	  </td>
		       	 	 <th >职务：</th>
		       	 	 <th class="bottom-border right-border" >
		       	 	  <input class="span12" fieldname="ZHIWU" name = "ZHIWU"  id="ZHIWU" type="text"   value="" />
		       	 	 </th>
	               </tr>
	               <tr>
	                 <th >身份证号码：</th>
			       	  <td colspan="3">
			       	 	 <input class="span12" fieldname="SHENFENZHENG" name = "SHENFENZHENG"  id="SHENFENZHENG" type="text"   value="" />
			       	  </td>
		       	 	 <th >住址：</th>
		       	 	 <td class="bottom-border right-border" >
		       	 	  <input class="span12" fieldname="ZHUZHI" name = "ZHUZHI" id="ZHUZHI" type="text"   value="" />
		       	 	 </td>
	               </tr>
	               
	               <tr>
	                 <th >邮编：</th>
			       	  <td colspan="3">
			       	 	 <input class="span12" fieldname="YZBM" name = "YZBM"  id="YZBM" type="text"   value="" />
			       	  </td>
		       	 	 <th >电话：</th>
		       	 	 <th class="bottom-border right-border" >
		       	 	  <input class="span12" fieldname="DIANHUA" name = "DIANHUA" id="DIANHUA" type="text"   value="" />
		       	 	 </th>
	               </tr>
	                <tr>
	                <th class="text-left"  colspan="6">相关情况问答：</th>
	                </tr>
	                <tr  style="width:100%;" rows="4" id="rowShow" >
	                <!--style="height:100px;width:100%;overflow:scroll;"  -->
                      <td colspan="6"  id="getDivVal"  >
                        <div style="height:400px;width:100%;overflow:scroll;" id="needScroll">
                        <div  style="display:none;" id="needDiv" width="900px;">
							问：<input id ="question01"  class="span12" style="display:none;" fieldname="Q_A" name="Q_A" value ="Q"/>
								<input id="XUHAOWEN" style="display:none;" class="span12" name="XUHAO" fieldname="XUHAO" value="1">
								<input id="CONTENTWEN" class="span10" fieldname="CONTENT" name="CONTENT" /><br/>    
							答：<input id ="answer01"  class="span12" style="display:none;" fieldname="Q_A" name="Q_A" value ="A"/>
								<input id="XUHAODA" style="display:none;" class="span12" name="XUHAO" fieldname="XUHAO" value="1">
								<input id="CONTENTDA" class="span10" fieldname="CONTENT" name="CONTENT" />
							<img onclick="removeWD(this)" style="cursor: pointer;" title="删除" src="/wndjsbg/assets/img/details_close.png">
							<img onclick="addWD(this)" style="cursor: pointer;" title="增加" src="/wndjsbg/assets/img/details_open.png">	
			            </div>
			              <div width="900px;">
							问：<input id ="question"  class="span12" type="hidden" fieldname="Q_A" name="Q_A" value ="Q"/>
							    <input id="XUHAO_ONE" style="display:none;" class="span12" name="XUHAO" fieldname="XUHAO" value="0"/>
							    <input id="CONTENTW" class="span10" fieldname="CONTENT" name="CONTENT" /><br/>    
							答：<input id ="answer"  class="span12" type="hidden" fieldname="Q_A" name="Q_A" value ="A"/>
								<input id="XUHAO_TWO" style="display:none;" class="span12" name="XUHAO" fieldname="XUHAO" value="0">
								<input id="CONTENTQ" class="span10" fieldname="CONTENT" name="CONTENT" />
							<img onclick="removeWD(this)" style="cursor: pointer;" title="删除" src="/wndjsbg/assets/img/details_close.png">
							<img onclick="addWD(this)" style="cursor: pointer;" title="增加" src="/wndjsbg/assets/img/details_open.png">	
                         </div>
                        </div>
                       </td>
				    </tr>
	              </tbody>
	          </table  >
	        </div>
	          <!-- -------------------------------- 3------------------------------ -->
	          <div id="DT3" style="height:750px;">
	            <div><h4 style="font-size:20px;font-family: yahei 微软雅黑;"><span>材料纸</span></h4></div>
	         <table width="100%"  class="table-hover table-activeTd B-table">
	           <thead  style="display:none;">
                	<tr>
                		<th   tdalign="center" width="15%" >&nbsp;&nbsp;</th>
                		<th   tdalign="center" width="20%" >&nbsp;&nbsp;</th>
						<th   tdalign="center" width="15%" >&nbsp;&nbsp;</th>
						<th   tdalign="center"  width="15%" >&nbsp;&nbsp;</th>
						<th   tdalign="center"  width="15%" >&nbsp;&nbsp;</th>
						<th   tdalign="center"  width="20%" >&nbsp;&nbsp;</th>
                	</tr>
	             </thead>
	           <tbody>
	            <!--  <tr>
	                <th class="text-left"  colspan="6"></th>
	                </tr>
	             <tr  style="width:100%;" rows="4" id="rowShow" >
	                style="height:100px;width:100%;overflow:scroll;" 
                      <th colspan="6"  id="getDivVal"  >
                        <div style="height:300px;width:100%;overflow:scroll;" id="needScorlDiv">
                         <div  id="needDiv01" width="900px;">
					               问：<input id ="question01"  class="span12" style="display:none;" fieldname="Q_A" name="Q_A" value ="Q"/>
								<input id="XUHAO" style="display:none;" class="span12" name="XUHAO" fieldname="XUHAO" value="0">
								<input id="CONTENTQ" class="span10" fieldname="CONTENT" name="CONTENT" /><br/>    
							答：<input id ="answer01"  class="span12" style="display:none;" fieldname="Q_A" name="Q_A" value ="A"/>
								<input id="XUHAO_Other" style="display:none;" class="span12" name="XUHAO" fieldname="XUHAO" value="0">
								<input id="CONTENTA" class="span10" fieldname="CONTENT" name="CONTENT" />
							<img onclick="removeQW(this)" style="cursor: pointer;" title="删除" src="/wndjsbg/assets/img/details_close.png">
							<img onclick="addQW(this)" style="cursor: pointer;" title="增加" src="/wndjsbg/assets/img/details_open.png">	
			            </div>
                        </div>
                      </th>
				    </tr>
				     -->
				    <tr>
	                <th class="text-left"  colspan="6"></th>
	                </tr>
	                <tr  style="width:100%;" rows="4" id="rowShow" >
	                <!--style="height:100px;width:100%;overflow:scroll;"  -->
                      <td colspan="6"  id="getDivVal"  >
                        <div style="height:500px;width:100%;overflow:scroll;" id="needScorlDiv">
                        <div  style="display:none;" id="needDiv01" width="900px;">
							问：<input id ="question01"  class="span12" style="display:none;" fieldname="Q_A" name="Q_A" value ="Q"/>
								<input id="XUHAO" style="display:none;" class="span12" name="XUHAO" fieldname="XUHAO" value="0">
								<input id="CONTENTQ" class="span10" fieldname="CONTENT" name="CONTENT" /><br/>    
							答：<input id ="answer01"  class="span12" style="display:none;" fieldname="Q_A" name="Q_A" value ="A"/>
								<input id="XUHAO_Other" style="display:none;" class="span12" name="XUHAO" fieldname="XUHAO" value="0">
								<input id="CONTENTA" class="span10" fieldname="CONTENT" name="CONTENT" />
							<img onclick="removeQW(this)" style="cursor: pointer;" title="删除" src="/wndjsbg/assets/img/details_close.png">
							<img onclick="addQW(this)" style="cursor: pointer;" title="增加" src="/wndjsbg/assets/img/details_open.png">	
			            </div>
			            <div width="900px;">
							问：<input id ="question"  class="span12" type="hidden" fieldname="Q_A" name="Q_A" value ="Q"/>
							    <input id="XUHAON1" style="display:none;" class="span12" name="XUHAO" fieldname="XUHAO" value="1"/>
							    <input id="CONTENTWEN" class="span10" fieldname="CONTENT" name="CONTENT" /><br/>    
							答：<input id ="answer"  class="span12" type="hidden" fieldname="Q_A" name="Q_A" value ="A"/>
								<input id="XUHAON2" style="display:none;" class="span12" name="XUHAO" fieldname="XUHAO" value="1">
								<input id="CONTENTDA" class="span10" fieldname="CONTENT" name="CONTENT" />
							<img onclick="removeQW(this)" style="cursor: pointer;" title="删除" src="/wndjsbg/assets/img/details_close.png">
							<img onclick="addQW(this)" style="cursor: pointer;" title="增加" src="/wndjsbg/assets/img/details_open.png">	
                         </div>
                        </div>
                       </td>
				    </tr>
	               <tr>
	                 <th style="width:15%">被询问人：</th>
	                 <td style="width:20%">
	                   <input class="span12" id="BEIXUNWEN_REN" filename="BEIXUNWEN_REN"  name="BEIXUNWEN_REN" value=""/>
	                 </td>
	                <!--  <td colspan="3"></td>
	                 <td style="width:20%">
	                   <input id="END_DATE" fieldname="END_DATE" name = "END_DATE"  style="float:right;" class="span12" type="text" fieldtype="date" fieldformat="YYYY-MM-DD"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  />
	                 </td> -->
	               </tr>
	               
	                <tr>
	                 <th style="width:15%">调查询问人员：</th>
	                 <td style="width:20%">
	                   <input class="span12" id="XUNWEN_REN2" filename="XUNWEN_REN2" name="XUNWEN_REN2" value=""/>
	                 </td>
	                   <th style="width:15%;text-align:center;">询问结束时间</th>
	                 <td style="width:20%">
	                   <input id="END_DATE" fieldname="END_DATE" name ="END_DATE" style="float:right;" class="span12" type="text" fieldtype="date" fieldformat="YYYY-MM-DD"   onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
	                 </td>
	               </tr>
	           </tbody>
	         </table>
	        </div>
	         </form>
	 	   </div>
	 	   
	 	     <div class="wizard-actions" style=" margin-top:10px;">
			    <button  id="btnNext" onclick="btnNext()" class="btn-next btn btn-success"  style="width:72px;height:30px;float:right;margin:2px 2px 2px 2px;">
			         下一页 
			     <i class="ace-icon fa fa-arrow-right icon-on-right"></i>
			   </button>
			   <button id="btnAdd" class="btn-next btn btn-success"  data-last="Finish" style="width:72px;height:30px;float:right;margin:2px 2px 2px 2px;display:none;">
			         保存
			     <i class="ace-icon fa fa-arrow-right icon-on-right"></i>
			   </button>
			  <button id="btnPrev"  onclick="btnPrev()" class="btn-prev btn btn-success" style="width:72px;float:right;margin:2px 2px 2px 2px;">
			     <i class="ace-icon fa fa-arrow-left" ></i> 
			         上一页
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