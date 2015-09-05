<%@page import="com.ccthanking.framework.common.User"%>
<%@page import="com.ccthanking.framework.Globals"%>
<%@page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext"%><!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<%
	String type=request.getParameter("type");
	if(type==null) 
		type="update";
	User user = RestContext.getCurrentUser();
	String id= (String)request.getAttribute("id");
	String oldId= (String)request.getAttribute("oldId");
	System.out.print(type);
%>
<title>施工单位信息维护</title>
<app:base/>

<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/sgenter/sgEnterPriseLibraryController/";
var controllernameZizhi= "${pageContext.request.contextPath }/sgenter/sgZizhiController/";
var controllernameZizhiDengji= "${pageContext.request.contextPath }/sgenter/sgZizhiDengjiController/";
var controllernameZenZizhi= "${pageContext.request.contextPath }/sgenter/sgEnterpriseZenZizhiController/";
var controllernameJiangx= "${pageContext.request.contextPath }/sgenter/sgECreditCommendRewardController/";
var controllernameXiangm= "${pageContext.request.contextPath }/sgenter/sgECreditProjectsController/";

var type ="<%=type%>";
var id ="<%=id%>";
var oldId = "<%=oldId%>";
//页面初始化
$(function() {
	init();
	
	//通过
	$("#btnSave").click(function() {
		if($("#DAIMA_Z").val().length<8){
			$("#codeError").show();
		}
		if(!checkAllPhone()){
			return;
        }
    	//必填验证
		if($("#sgEnterPriseLibraryForm").validationButton())
		{
			var data = Form2Json.formToJSON(sgEnterPriseLibraryForm);
	  		//组成保存json串格式
	   		var data1 = defaultJson.packSaveJson(data);
				var flag =defaultJson.doInsertJson(controllername + "update", data1);
	    	if(flag){
		    	if(window.opener!=null){window.opener.doQuery();}
		    	alert("修改完成!");
		    	
	        	window.location = "${pageContext.request.contextPath }/sgenteditrk/"+id;
	   		}			
		}else{
			requireFormMsg();
		  	return;
		}
	});
	
	//按钮绑定事件（关闭）
    $("#btnClose").click(function() {
        window.close();
    });
	
	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#queryForm").clearFormResult();
    });

	//按钮绑定事件(保存)
	$("#btnSaveXz").click(function() {
		if($("#sgEnterPriseLibraryForm").validationButton()){
			xConfirm("信息确认","确认限制该承包商承接新区微型工程？");
			$('#ConfirmYesButton').attr('click','');
			$('#ConfirmYesButton').one("click",function(){
			    //生成json串
			    var data = Form2Json.formToJSON(sgEnterPriseLibraryForm);
			  //组成保存json串格式
			    var data1 = defaultJson.packSaveJson(data);
			  //调用ajax插入
			    if($("#YXCBS_UID").val() != "" && $("#YXCBS_UID").val() != null){
	    			defaultJson.doInsertJson("${pageContext.request.contextPath }/wxgc/yxYxcbsController?updateXzYxcbs", data1);
	    		}
			    $("#btnClose").click();
			});
			
		}else{
			requireFormMsg();
		  	return;
		}
	});

	//两次密码对等验证
    $("#PWD1").blur(function(){
    	if($("#PWD").val()!=$("#PWD1").val()){
    		$("#pwdError").show();
    		return;
    	}else{
    		$("#pwdError").hide();
        }
    });
    $("#PWD").blur(function(){
    	if($("#PWD1").val()!=''&&$("#PWD").val()!=$("#PWD1").val()){
    		$("#pwdError").show();
    		return;
    	}else{
    		$("#pwdError").hide();
        }
    });
    $("#insertBtn").click(function(){
    	$("#jxtb").show();
    });
    //组织机构代码验证
    $("#DAIMA_Z").blur(function(){
        //验证字符长度
		if($("#DAIMA_Z").val()!=''&&$("#DAIMA_Z").val().length<8){
			$("#codeError").find("b").html("前半部分必须为8位字符");
			$("#codeError").show();	
			return;
		}else{
			$("#codeError").hide();
		}
		//字符有效性(是否重复)
		if($("#DAIMA_Z").val()!=''&&$("#DAIMA_F").val()!=''){
			checkCode();
		}
    });

    $("#FAREN_MOBILE").blur(function(){
    	if($("#FAREN_MOBILE").val()==""){
    		$("#FAREN_MOBILE").closest("td").find("#ers").html("");
    		return true;
    	}
    	if(!checkPhone($("#FAREN_MOBILE").val())){
    		$("#FAREN_MOBILE").closest("td").find("#ers").html("<b>请输入正确的手机号码</b>");
    		return false;
 		}else{
 			$("#FAREN_MOBILE").closest("td").find("#ers").html("");
    		return true;
 	 	}
    });
    $("#ZHUXI_MOBILE").blur(function(){
    	if($("#ZHUXI_MOBILE").val()==""){
    		$("#ZHUXI_MOBILE").closest("td").find("#ers").html("");
    		return true;
    	}
    	if(!checkPhone($("#ZHUXI_MOBILE").val())){
    		$("#ZHUXI_MOBILE").closest("td").find("#ers").html("<b>请输入正确的手机号码</b>");
    		return false;
 		}else{
 			$("#ZHUXI_MOBILE").closest("td").find("#ers").html("");
    		return true;
 	 	}
    });
    $("#LIANXIREN_MOBILE").blur(function(){
    	if($("#LIANXIREN_MOBILE").val()==""){
    		$(obj).closest("td").find("#ers").html("");
    		return true;
    	}
    	if(!checkPhone($("#LIANXIREN_MOBILE").val())){
    		$("#LIANXIREN_MOBILE").closest("td").find("#ers").html("<b>请输入正确的手机号码</b>");
    		return false;
 		}else{
 			$("#LIANXIREN_MOBILE").closest("td").find("#ers").html("");
    		return true;
 	 	}
    });
    
    
    $("#DAIMA_F").blur(function(){
    	//字符有效性(是否重复)
		if($("#DAIMA_Z").val()!=''&&$("#DAIMA_F").val()!=''){
			checkCode();
		}
    });
    <%
    if(type.equals("detail")){
	%>
	//置所有input 为disabled
	$(":input").each(function(i){
	   $(this).attr("disabled", "true");
	});
	<%
		}
	%>
});

function checkButton(){
	$("#btnResend").hide();
	if($("#STATUS").val()=='40'){
		$("#btnResend").show();
	}
	
}
function checkAllPhone(){
	var flag = false;
	if($("#FAREN_MOBILE").val()==""){
		$("#FAREN_MOBILE").closest("td").find("#ers").html("");
		flag =  true;
	}else if(!checkPhone($("#FAREN_MOBILE").val())){
		$("#FAREN_MOBILE").closest("td").find("#ers").html("<b>请输入正确的手机号码</b>");
		flag =  false;
		}else{
			$("#FAREN_MOBILE").closest("td").find("#ers").html("");
		flag =  true;
	 }

	if($("#ZHUXI_MOBILE").val()==""){
		$("#ZHUXI_MOBILE").closest("td").find("#ers").html("");
		flag =  true;
	}else if(!checkPhone($("#ZHUXI_MOBILE").val())){
		$("#ZHUXI_MOBILE").closest("td").find("#ers").html("<b>请输入正确的手机号码</b>");
		flag =  false;
		}else{
			$("#ZHUXI_MOBILE").closest("td").find("#ers").html("");
		flag =  true;
	 	}

	if($("#LIANXIREN_MOBILE").val()==""){
		$(obj).closest("td").find("#ers").html("");
		flag =  true;
	}else if(!checkPhone($("#LIANXIREN_MOBILE").val())){
		$("#LIANXIREN_MOBILE").closest("td").find("#ers").html("<b>请输入正确的手机号码</b>");
		flag =  false;
		}else{
			$("#LIANXIREN_MOBILE").closest("td").find("#ers").html("");
		flag =  true;
	 	}
 	return flag;
}
//页面默认参数
function init(){
	
	$("#optype").val(type);
	loadZXZZ();
	
	$("#QID").val(id);
	$("#oldId").val(oldId);
	setFormValues();
	builderZizhiList();
	builderJiangxList();
	builderXiangmList();
	queryFileData2('ID','parent','SG_ENTERPRISE_LIBRARY');

	checkButton();
	initYxcbsFun();
}
function getSequences(){
	$.ajax({
		url : controllername+"getSeq",
		data : "",
		cache : false,
		async : false,
		dataType : "text",
		type : "post",
		success : function(response){
			var obj = eval('(' + response + ')');
			var obj2 = eval('('+obj+')');
			$("#ID").val(obj2.ID);
			$("#JXID").val(obj2.JXID);
			$("#XMID").val(obj2.XMID);
		}
	});
	
}
//修改情况下,读取表单内容
function setFormValues(){
	var data = combineQuery.getQueryCombineData(queryForm,frmPost);
	var data1 = {
		msg : data
	};
	$.ajax({
		url : controllername+"query",
		data : data1,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			$("#resultXML").val(response.msg);
			var resultobj = defaultJson.dealResultJson(response.msg);
			$("#sgEnterPriseLibraryForm").setFormValues(resultobj);
			//隐藏密码修改
			$("#PWD").val(resultobj.MIMA);
			$("#PWD1").val(resultobj.MIMA);
			$("#pwdtr").hide();
			$("#pwdtr_re").hide();
			loadZZDJ($("#ZHENGSHU"),'zhuxiang',resultobj.ZHU_DENGJI);
			$("#QYXCBS_UID").val(resultobj.YXCBS_UID);//预选承包商	
			//组织机构代码
			var code = resultobj.COMPANY_CODE;
			$("#DAIMA_Z").val(code.split("-")[0]);
			$("#DAIMA_F").val(code.split("-")[1]);
		}
	});
}
function builderForm(response){

	var obj=$("#resultXML").val();
	var resultobj=defaultJson.dealResultJson(obj);
	$("#sgEnterPriseLibraryForm").setFormValues(resultobj);
	//隐藏密码修改
<%--	$("#PWD").val(resultobj.MIMA);--%>
<%--	$("#PWD1").val(resultobj.MIMA);--%>
	$("#pwdtr").hide();
	$("#pwdtr_re").hide();
	loadZZDJ($("#ZHENGSHU"),'zhuxiang',resultobj.ZHU_DENGJI);
<%--	$("#ZHU_DENGJI").val(resultobj.ZHU_DENGJI);--%>
	//组织机构代码
	var code = resultobj.COMPANY_CODE;
	$("#DAIMA_Z").val(code.split("-")[0]);
	$("#DAIMA_F").val(code.split("-")[1]);
	checkButton();
}
function builderZizhiList(){
	$.ajax({
		url : controllernameZenZizhi+"queryListZeng?uid="+$("#ID").val(),
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var obj1 = eval('(' + response.msg + ')');
			chushiDengji = "";
			$(obj1).each(function(){


				var cloneNew = $("#cloneTR").clone(true);
				//alert(typeof(cloneNew));
				$(cloneNew).removeAttr("style")
				$(cloneNew).find("select").eq(0).val(this.SG_ZIZHI_UID);
				$("#zizhiList").find("tr").eq(2).before(cloneNew);
				loadZZDJ($(cloneNew).find("select").eq(0),'zengxiang',this.SG_ZIZHI_DENGJI_UID);
				$(cloneNew).find("#SG_ENTERPRISE_ZEN_ZIZHI_UID").val(this.SG_ENTERPRISE_ZEN_ZIZHI_UID);
				$(cloneNew).find("#ZIZHI_CODE").val(this.ZIZHI_CODE);
				//alert(this.VALID_DATE_SV);
				$(cloneNew).find("#ZENG_VALID_DATE").val(this.ZENG_VALID_DATE.substring(0,10));
				$(cloneNew).find("img").eq(1).hide();
			});
		}
	});
}

function builderJiangxList(){
	$.ajax({
		url : controllernameJiangx+"queryJxList?uid="+$("#ID").val(),
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var obj1 = eval('(' + response.msg + ')');
			chushiDengji = "";
			$(obj1).each(function(){


				var cloneNew = $("#jx_cloneTR").clone(true);
				//alert(typeof(cloneNew));
				$(cloneNew).removeAttr("style");
				$(cloneNew).removeAttr("id");
				$(cloneNew).find("select").eq(0).val(this.SG_ZIZHI_UID);
				$("#jx_List").find("tr").eq(2).before(cloneNew);
				$(cloneNew).find("#CREDIT_COMMEND_REWARD_UID").val(this.CREDIT_COMMEND_REWARD_UID);
				$(cloneNew).find("#CR_NAME").val(this.CR_NAME);
				$(cloneNew).find("#CR_LEVEL").val(this.CR_LEVEL);
				//alert(this.VALID_DATE_SV);
				$(cloneNew).find("#BG_VALIDITY_DATE").val(this.BG_VALIDITY_DATE.substring(0,10));
				$(cloneNew).find("img").eq(1).hide();
			});
			queryFileData2('CREDIT_COMMEND_REWARD_UID','child','SG_E_CREDIT_COMMEND_REWARD');
		}
	});
}

function builderXiangmList(){
	$.ajax({
		url : controllernameXiangm+"queryXmList?uid="+$("#ID").val(),
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var obj1 = eval('(' + response.msg + ')');
			chushiDengji = "";
			$(obj1).each(function(){


				var cloneNew = $("#xm_cloneTR").clone(true);
				//alert(typeof(cloneNew));
				$(cloneNew).removeAttr("style")
				$(cloneNew).removeAttr("id");
				$(cloneNew).find("select").eq(0).val(this.SG_ZIZHI_UID);
				$("#xm_List").find("tr").eq($("#xm_List").find("tr").size()-1).before(cloneNew);
				
				
				
				$(cloneNew).find("#CREDIT_PROJECTS_UID").val(this.CREDIT_PROJECTS_UID);
				$(cloneNew).find("#PROJECT_NAME").val(this.PROJECT_NAME);
				//alert(this.VALID_DATE_SV);
				$(cloneNew).find("#YXBEGIN_DATE").val(this.YXBEGIN_DATE.substring(0,10));
				$(cloneNew).find("img").eq(1).hide();
			});			
			queryFileData2('CREDIT_PROJECTS_UID','child','SG_E_CREDIT_PROJECTS');
		}
	});
}
var chushiDengji = "";
//加载资质列表
function loadZXZZ(){
	$.ajax({
		url : controllernameZizhi+"loadAllZizhi",
		data : {},
		cache : false,
		async :	false,
		dataType : "text",  
		type : 'post',
		success : function(response) {
			var obj = eval('(' + response + ')')
			var obj1 = eval('(' + obj.msg + ')')
			var linkStr = "";
			$(obj1).each(function(){
				linkStr += "<option value='"+this.SG_ZIZHI_UID+"'>"+this.ZIZHI_NAME+"</option>";
			});
			//加载主项资质列表
			$("#ZHENGSHU").append(linkStr);

			//加载主项资质等级(初始化页面默认选中第一个资质)
			//loadZZDJ($("#ZHENGSHU"),'zhuxiang');
			//加载增项资质列表
			$("#zizhiList tr").each(function(){
				$(this).find("#SG_ZIZHI_UID").append(linkStr);
				//$(this).find("#ZIZHI_DENGJI").append(chushiDengji);
			})
		}
	});
	
}
//加载资质等级
function loadZZDJ(demo,type,value){
	var dengjiDom = null;
	if(type=='zhuxiang'){
		dengjiDom = $(demo).parent().find("#ZHU_DENGJI")
	}else if(type=='zengxiang'){
		dengjiDom = $(demo).parent().parent().find("#ZIZHI_DENGJI");
	}
	if($(demo).val()==''||$(demo).val()=='请选择'){
		$(dengjiDom).html("<option>等级</option>");
		return;
	}
	$.ajax({
		url : controllernameZizhiDengji+"queryZizhiDengji?zizhi="+$(demo).val(),
		data : {},
		cache : false,
		async :	false,
		dataType : "text",  
		type : 'post',
		success : function(response) {
			
			var obj = eval('(' + response + ')');
			var obj1 = eval('(' + obj.msg + ')');
			chushiDengji = "";
			$(obj1).each(function(){
				chushiDengji += "<option value='"+this.SG_ZIZHI_DENGJI_UID+"'"
				if(value!=null&&this.SG_ZIZHI_DENGJI_UID==value){
					chushiDengji += " selected='selected' ";
				}
				chushiDengji += ">"+this.DENGJI_NAME+"</option>";
			});
			$(dengjiDom).empty();
			$(dengjiDom).append(chushiDengji);
		}
	});
}
//增加增项目资质
function addZengxiang(demo){
	var cloneNew = $("#cloneTR").clone(true);
	//alert(typeof(cloneNew));
	$(cloneNew).removeAttr("style")
	$("#zizhiList").append(cloneNew);
	$(demo).hide();
}

function removeZengxiang(demo){
	if($("#zizhiList tr").size()==3){return;}
	var tr_index = $("#zizhiList tr").index($(demo).closest("tr"));
	if(tr_index==$("#zizhiList tr").size()-1&&tr_index>2){
		$("#zizhiList tr").eq($("#zizhiList tr").size()-2).find("td").eq(3).append('<img onclick="addZengxiang(this)" style="cursor: pointer;" title="增加" src="/wndjsbg/img/sg/icon-addZz.jpg">');
	}	
	$(demo).closest("tr").remove();
}
//增加奖项
function addTR(demo,type){
	var cloneNew = $("#"+type+"_cloneTR").clone(true);
	//alert(typeof(cloneNew));
	$(cloneNew).removeAttr("style")
	$("#"+type+"_List").append(cloneNew);
	$(demo).hide();
}

function removeTR(demo,type){
	if($("#"+type+"_List").find(".constant_tr").size()==2){return;}
	var tr_index = $("#"+type+"_List").find(".constant_tr").index($(demo).closest("tr"));
	if(tr_index==$("#"+type+"_List").find(".constant_tr").size()-1&&tr_index>1){
		$("#"+type+"_List").find(".constant_tr").eq($("#"+type+"_List").find(".constant_tr").size()-2).find("img").eq(1).show();
	}	
	$(demo).closest("tr").remove();
}
function checkCode(){
	var code = $("#DAIMA_Z").val()+"-"+$("#DAIMA_F").val();
	var id = $("#SG_COMPANY_UID").val();
	$.ajax({
		url : controllername+"queryCodeIsEmpty",
		data : {'code':code,'id':id},
		cache : false,
		async :	false,
		dataType : "text",  
		type : 'post',
		success : function(response) {
			var obj = eval('(' + response + ')')
			if(obj.msg=='empty'){
				$("#codeError").find("b").html("该组织机构代码已存在");
				$("#codeError").show();	
			}else{
				$("#codeError").hide();
			}
		}
	});
}
function reqXysc(){
	alert($("input:radio[name='WAIDI_Y']:check").val());
}
function onlyNum()
{
	
 if(!(event.keyCode==46)&&!(event.keyCode==8)&&!(event.keyCode==37)&&!(event.keyCode==39))
  if((event.keyCode!=46)&&!((event.keyCode>=48&&event.keyCode<=57)||(event.keyCode>=96&&event.keyCode<=105))){
    event.returnValue=false;
  }
}
function isMoney(money){
	var mon=money.value;
	mon=mon*0.5/0.5;
	money.value=mon;
}
function checkPhone(text){
	var reg=/^(13|15|18|17)\d{9}$/;
	var result=  reg.test(text);
	if(!result){
		return false;
	}else{
		return true;
	}
}




function initYxcbsFun(){
	var YXCBS_UID=$("#QYXCBS_UID").val();
	
	if(YXCBS_UID==null||YXCBS_UID==''){
		$("#yxcbsdivid").hide();
		$("#btnSaveXz").hide();
		return false;
	}
	
	$("#yxcbsdivid").show();
	
	$("#USERNAME").removeAttr('disabled');
	//$("#SFYZXZ").removeAttr('disabled');
	$("input[name='SFYZXZ']").removeAttr('disabled');
	$("#XZDQRQ").removeAttr('disabled');
	$("#XZYY").removeAttr('disabled');
	$("#btnSaveXz").removeAttr('disabled');

	$("#XZCZR").attr("readonly", "true");
	$("#XZCZRQ").attr("readonly", "true");
	//生成json串
	
	//查询表单赋值
	var data = combineQuery.getQueryCombineData(yxcbsqueryForm, xzfrmPost);
	var data1 = {
		msg : data
	};
	$.ajax({
		url : "${pageContext.request.contextPath }/wxgc/yxYxcbsController?query",
		data : data1,
		cache : false,
		async : false,
		dataType : "json",
		type : 'post',
		success : function(response) {				
			var res = dealSpecialCharactor(response.msg);
			//$("#resultXML").val(response.msg);
			var resultobj = defaultJson.dealResultJson(res);
			$("#sgEnterPriseLibraryForm").setFormValues(resultobj);	
			var SFYZXZ=resultobj.SFYZXZ;
			if(SFYZXZ!=''&&SFYZXZ!=null){
				$("#xzmesid").text("(*已被限制承接新区微型工程！)");
				if(SFYZXZ=='1'){
					$("#xzrqname").hide();
					$("#xzrqvalue").hide();
				}
			}else {
				$("input:radio[value=0]").attr('checked','true');
			}

			$("#XZDQRQ").removeAttr('code');
			return true;
		}
	});
}

function sfyxxzFun(v){
	if(v=='1'){
		$("#xzrqname").hide();
		$("#xzrqvalue").hide();
	}else{
		$("#xzrqname").show();
		$("#xzrqvalue").show();
	}
}
</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">



<div class="row-fluid">
    <div class="B-small-from-table-autoConcise">
     <form method="post" id="queryForm"  >
      <table class="B-table" width="100%">
      <!--可以再此处加入hidden域作为过滤条件 -->
      	<TR  style="display:none;">
	        <TD class="right-border bottom-border">
	        	<INPUT type="text" class="span12" kind="text" id="QID" name="ID"  fieldname="n.SG_ENTERPRISE_LIBRARY_UID" value="" operation="="/>
	        	<INPUT type="text" class="span12" kind="text" id="QSTATUS" name="STATUS"  fieldname="n.STATUS" value="1" operation="="/>
	        </TD>
			<TD class="right-border bottom-border">
			</TD>
        </TR>
      </table>
      </form>
    <div style="height:5px;"></div>
	</div>
	<div style="height:5px;"></div>
	 <form method="post" id="yxcbsqueryForm"  >
			    	  <input type="hidden" id="QYXCBS_UID" fieldname="YXCBS_UID" name = "YXCBS_UID" operation="="/>
			      </form> 
			      <FORM name="xzfrmPost" method="post" style="display:none" target="_blank">
			         <input type="hidden" name="txtFilter"  order="desc" fieldname = "YXCBS_UID" id = "txtFilter">
			 	  </FORM>
	<div class="B-small-from-table-autoConcise">
	<form method="post" id="sgEnterPriseLibraryForm">
		<div class="container-fluid">
			<p class="text-right tabsRightButtonP">
				<span class="pull-right">
					<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
					<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">保存</button>
					<button id="btnSaveXz" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">确认限制</button>
				</span>
			</p>
			<ul class="nav nav-tabs"> 
				<li class="active"><a href="#xmsc1" data-toggle="tab" id="tabPage0">施工单位信息</a></li> 
				<li class=""><a href="#xmsc2" data-toggle="tab" id="tabPage1">相关荣誉</a></li>
				&nbsp;&nbsp;<span id="xzmesid"></span>
			</ul>
			<div class="tab-content"> 
				<!-- 静态信息tab页 -->
				<div class="tab-pane active" id="xmsc1" style="height:100%"> 
				
				<div id="yxcbsdivid" style="display: none">
				      <table class="B-table" width="100%" >
					  	   <input type="hidden" id="YXCBS_UID" fieldname="YXCBS_UID" name = "YXCBS_UID"/>
				           <tr>
					           	<th width="8%" class="right-border bottom-border text-right">是否永久限制</th>
					       		<td class="bottom-border right-border" width="15%">
					         		<input id="SFYZXZ" type="radio" kind="dic" src="SF" defaultValue="1" name="SFYZXZ" fieldname="SFYZXZ" onclick="sfyxxzFun(this.value);" >
					         	</td>
								<th id="xzrqname" width="8%" class="right-border bottom-border text-right">限制到期日期</th>
					       	 	<td id="xzrqvalue" class="bottom-border right-border" width="15%">
					         		<input  id="XZDQRQ"   type="text" fieldname="XZDQRQ" name = "XZDQRQ" class='Wdate' onClick='WdatePicker()'/>
					       	 	</td> 
				           </tr>
				          <tr>
				         	<th width="8%" class="right-border bottom-border text-right">限制原因</th>
				       		<td class="bottom-border right-border" colspan="3">
				         		<textarea class="span12" style="width:80%" rows="2" id="XZYY" check-type="required" maxlength="4000"  fieldname="XZYY" name="XZYY"></textarea>
				         	</td>
				          </tr>
				          <tr>
				           	<th width="8%" class="right-border bottom-border text-right">限制操作人</th>
				       		<td class="bottom-border right-border" width="15%">
				         		<input type="text"  id="XZCZR"  name="XZCZR" fieldname="XZCZR" >
				         	</td>
							<th width="8%" class="right-border bottom-border text-right">操作日期</th>
				       	 	<td class="bottom-border right-border" width="15%">
				         		<input  id="XZCZRQ"   type="text" fieldname="XZCZRQ" name = "XZCZRQ" />
				       	 	</td> 
				           </tr>
				      </table>
			      </div>	
				
				
				<table class="B-table" width="100%" >
			      	<input type="hidden" id="ID" fieldname="SG_ENTERPRISE_LIBRARY_UID" name = "ID"/>
				  	<input type="hidden" id="STATUS" fieldname="STATUS" name = "STATUS"/>
				  	<input type="hidden" id="optype" fieldname="optype" name = "optype"/>
				  	<input type="hidden" id="SG_COMPANY_UID" fieldname="SG_COMPANY_UID" name = "SG_COMPANY_UID"/>
					<input type="hidden" id="SG_ENTERPRISE_LIBRARY_FILEUPLOAD" fieldname="SG_ENTERPRISE_LIBRARY_FILEUPLOAD" name = "SG_ENTERPRISE_LIBRARY_FILEUPLOAD"/>
				  	<input type="hidden" id="oldId" name = "oldId"/>
			        <tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">组织机构代码</th>
			       	 	<td class="bottom-border right-border">
			         		<input class="span12" style="width:35%" id="DAIMA_Z" maxlength="8" type="text" placeholder="必填" check-type="required" fieldname="DAIMA_Z" name = "DAIMA_Z"/>
			         		-
			         		<input class="span12" style="width:5%" id="DAIMA_F" maxlength="1" type="text" placeholder="必填" check-type="required" fieldname="DAIMA_F" name = "DAIMA_F"/>
			         		&nbsp;&nbsp;&nbsp;<span id="codeError" style="display: none;color: red;"><b>前半部分必须为8位</b></span>
			         		<font style="color:red;font-size: 16px"><b>*</b></font>
			       	 	</td>
			        </tr>
			        <tr>
			        	<th width="15%" class="right-border bottom-border text-right" colspan="2">组织代码证扫描件</th>
			        	<td class="bottom-border right-border">
							<div>
								<span class="btn btn-fileUpload" id="addFileSpan" onclick="doSelectFile(this,'parent');"  ywid="ID" target_type="1" file_type="7" business_type="SG_ENTERPRISE_LIBRARY">
									<i class="icon-plus"></i>
									<span>添加文件...</span>
								</span>
								<table role="presentation" class="table table-striped" >
									<tbody ywid="ID" target_type="1" file_type="1" business_type="SG_ENTERPRISE_LIBRARY" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>
								</table>
								<div id="show"></div>
							</div>
						</td>
			        </tr>
			        <tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">施工企业全称</th>
						<td  class="right-border bottom-border">
							<input class="span12" style="width:85%" id="COMPANY_NAME" type="text" placeholder="必填" check-type="required" fieldname="COMPANY_NAME" name = "COMPANY_NAME"  />
							<font style="color:red;font-size: 16px"><b>*</b></font>
			<%--				<input id="ZFJE" class="span12" keep="true" placeholder="必填" check-type="required" check-type="maxlength" maxlength="17" value=0 style="width:70%;text-align:right;" name="ZFJE" fieldname="ZFJE" type="number" />&nbsp;<b>(元)</b>--%>
						</td>
					</tr>
					<tr id="pwdtr">
						<th width="15%" class="right-border bottom-border text-right" colspan="2">密码</th>
						<td  class="right-border bottom-border">
							<input id="PWD" class="span12" style="width:35%" placeholder="必填" check-type="required" name="PWD" fieldname="PWD" type="password" />
							<font style="color:red;font-size: 16px"><b>*</b></font>
						</td>
					</tr>
					<tr id="pwdtr_re">
						<th width="15%" class="right-border bottom-border text-right" colspan="2">密码确认</th>
						<td  class="right-border bottom-border">
							<input id="PWD1" class="span12" style="width:35%" placeholder="必填" check-type="required" name="PWD" type="password" />
							<font style="color:red;font-size: 16px"><b>*</b></font>
							&nbsp;&nbsp;&nbsp;<span id="pwdError" style="display: none;color: red;"><b>两次密码不一致</b></span>
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">企业类型</th>
						<td  class="right-border bottom-border">
							<select  id="COMPANY_TYPE"  class="span12" style="width:35%"  name="COMPANY_TYPE" fieldname="COMPANY_TYPE"  operation="="  kind="dic" src="COMPANY_TYPE" defaultMemo="全部">
							<font style="color:red;font-size: 16px"><b>*</b></font>
								
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">外地企业</th>
						<td  class="right-border bottom-border">
							<input class="span12" id="WAIDI_Y" type="radio" placeholder="" kind="dic" src="WAIDI_Y" name = "WAIDI_Y" fieldname="WAIDI_Y">
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">营业执照</th>
						<td  class="right-border bottom-border" style="padding:0px;">
							<table width="100%">
								<tr>
									<td width="10%" class="text-right">注册号</th>
									<td style="border-right: 0px;">
										<input id="ZHIZHAO" class="span12" style="width:35%" placeholder="必填" check-type="required" name="ZHIZHAO" fieldname="ZHIZHAO" type="text" />
										<font style="color:red;font-size: 16px"><b>*</b></font>
									</td>
								</tr>
								<tr>
									<td width="10%" class="text-right">注册资金</th>
									<td style="border-right: 0px;">
										<input id="ZHUCE_ZIJIN" value=0 class="span12"  onkeydown="onlyNum()" onkeyup="isMoney(this)"  name="ZHUCE_ZIJIN" fieldname="ZHUCE_ZIJIN" type="text"  min="0" alt="请输入金额，不能输入中文" style="width:35%;text-align:right;"/><b>(万元)</b>
										<font style="color:red;font-size: 16px"><b>*</b></font>
									</td>
								</tr>
								<tr>
									<td width="10%" class="text-right">有效期</th>
									<td style="border-right: 0px;">
										<input id="ZHIZHAO_VALID"   style="width:33.5%;" fieldtype="date" fieldformat="YYYY-MM-DD" name="ZHIZHAO_VALID" fieldname="ZHIZHAO_VALID" class="Wdate" type="text" onClick="WdatePicker()"/>
										<font style="color:red;font-size: 16px"><b>*</b></font>
									</td>
								</tr>
								<tr>
									<td width="10%" class="text-right" style="border-bottom: 0px;">扫描件</th>
									<td style="border-bottom: 0px;border-right: 0px;">
										<div>
											<span class="btn btn-fileUpload" id="addFileSpan" onclick="doSelectFile(this,'parent');"  ywid="ID" target_type="1" file_type="1" business_type="SG_ENTERPRISE_LIBRARY">
												<i class="icon-plus"></i>
												<span>添加文件...</span>
											</span>
											<table role="presentation" class="table table-striped">
												<tbody ywid="ID" target_type="1" file_type="2" business_type="SG_ENTERPRISE_LIBRARY" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>
											</table>
										</div>
									</td>
								</tr>
							</table>
						</td>	
					</tr>
					<tr>
						<th class="right-border bottom-border text-right" style="line-height:18px;" colspan="2">税务登记证</th>
						<td  class="right-border bottom-border" style="padding:0px;">
							<table width="100%">
								<tr>
									<td width="10%" class="right-border bottom-border text-right">税务登记号</td>
									<td style="border-right: 0px;">
										<input id="SHUIWU" class="span12" style="width:35%" placeholder="必填" check-type="required" name="SHUIWU" fieldname="SHUIWU" type="text" />
										<font style="color:red;font-size: 16px"><b>*</b></font>
									</td>
								</tr>
								<tr>
									<td width="10%" class="right-border bottom-border text-right" style="border-bottom: 0px;">扫描件</td>
						        	<td  class="bottom-border right-border"  style="border-bottom: 0px;border-right: 0px;">
										<div>
											<span class="btn btn-fileUpload" id="addFileSpan" onclick="doSelectFile(this,'parent');"  ywid="ID" target_type="1" file_type="2" business_type="SG_ENTERPRISE_LIBRARY">
												<i class="icon-plus"></i>
												<span>添加文件...</span>
											</span>
											<table role="presentation" class="table table-striped">
												<tbody fjlb="0701" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>
											</table>
										</div>
									</td>
								</tr>
							</table>
						</td>	
					</tr>
					<tr>
						<th class="right-border bottom-border text-right" style="line-height:18px;" colspan="2">开户银行</th>
						<td  class="right-border bottom-border" style="padding:0px;">
							<table width="100%">
								<tr>
									<td width="10%" class="right-border bottom-border text-right">开户银行</td>
									<td  class="right-border bottom-border" style="border-right: 0px;">
										<input id="BANK" class="span12" style="width:35%" placeholder="必填" check-type="required" name="BANK" fieldname="BANK" type="text" />
										<font style="color:red;font-size: 16px"><b>*</b></font>
									</td>
								</tr>
								<tr>
									<td width="10%" class="right-border bottom-border text-right">银行账号</td>
									<td  class="right-border bottom-border" style="border-right: 0px;">
										<input id="BANK_ACCOUNT" class="span12" style="width:35%" placeholder="必填" check-type="required" name="BANK_ACCOUNT" fieldname="BANK_ACCOUNT" type="text" />
										<font style="color:red;font-size: 16px"><b>*</b></font>
									</td>
								</tr>
								<tr>
									<td width="10%" class="right-border bottom-border text-right" style="border-bottom: 0px;">扫描件</td>
						        	<td  class="bottom-border right-border" style="border-right: 0px;border-bottom: 0px;">
										<div>
											<span class="btn btn-fileUpload" id="addFileSpan" onclick="doSelectFile(this,'parent');" ywid="ID" target_type="1" file_type="3" business_type="SG_ENTERPRISE_LIBRARY">
												<i class="icon-plus"></i>
												<span>添加文件...</span>
											</span>
											<table role="presentation" class="table table-striped">
												<tbody fjlb="0701" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>
											</table>
										</div>
									</td>
								</tr>
							</table>
						</td>	
					</tr>
					<tr>
						<th class="right-border bottom-border text-right" style="line-height:18px;" colspan="2">安全生产许可证</th>
						<td  class="right-border bottom-border" style="padding:0px;">
							<table width="100%">
								<tr>
									<td width="10%" class="right-border bottom-border text-right">编号</td>
									<td class="right-border bottom-border" style="border-right: 0px;">
										<input id="ANQUAN_CODE" class="span12"  style="width:35%" placeholder="必填" check-type="required" name="ANQUAN_CODE" fieldname="ANQUAN_CODE" type="text" />
										<font style="color:red;font-size: 16px"><b>*</b></font>
									</td>
								</tr>
								<tr>
									<td width="10%" class="right-border bottom-border text-right">有效期</td>
									<td  class="right-border bottom-border" style="border-right: 0px;">
										<input id="ANQUAN_VALID" style="width:33.5%;" fieldtype="date" fieldformat="YYYY-MM-DD" name="ANQUAN_VALID" fieldname="ANQUAN_VALID" class="Wdate" type="text" onClick="WdatePicker()" />
										<font style="color:red;font-size: 16px"><b>*</b></font>
									</td>
								</tr>
								<tr>
									<td width="10%" class="right-border bottom-border text-right" style="border-bottom: 0px;">扫描件</td>
						        	<td  class="bottom-border right-border" style="border-right: 0px;border-bottom: 0px;">
										<div>
											<span class="btn btn-fileUpload" id="addFileSpan" onclick="doSelectFile(this,'parent');"  ywid="ID" target_type="1" file_type="5" business_type="SG_ENTERPRISE_LIBRARY">
												<i class="icon-plus"></i>
												<span>添加文件...</span>
											</span>
											<table role="presentation" class="table table-striped">
												<tbody fjlb="0701" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>
											</table>
										</div>
									</td>
								</tr>
							</table>
						</td>
						
					</tr>
					<tr>
						<th class="right-border bottom-border text-right" style="line-height:18px;" colspan="2">信用手册</th>
						<td  class="right-border bottom-border" style="padding:0px;">
							<table width="100%">
								<tr>
									<td width="10%" class="right-border bottom-border text-right">编号</td>
									<td class="right-border bottom-border" style="border-right: 0px;">
										<input id="XINYONG_CODE" style="width:35%" class="span12" name="XINYONG_CODE" fieldname="XINYONG_CODE" type="text" />
										<font style="color:red;font-size: 16px"><b>*</b></font>外地企业无须填写
									</td>
								</tr>
								<tr>
									<td width="10%" class="right-border bottom-border text-right">有效期</td>
									<td  class="right-border bottom-border" style="border-right: 0px;">
										<input id="XINYONG_VALID" style="width:33.5%;"  fieldtype="date" fieldformat="YYYY-MM-DD" name="XINYONG_VALID" fieldname="XINYONG_VALID" class="Wdate" type="text" onClick="WdatePicker()" />
									</td>
								</tr>
								<tr>
									<td width="10%" class="right-border bottom-border text-right" style="border-bottom: 0px;">扫描件</td>
						        	<td  class="bottom-border right-border" style="border-right: 0px;border-bottom: 0px;">
										<div>
											<span class="btn btn-fileUpload" id="addFileSpan" onclick="doSelectFile(this,'parent');"  ywid="ID" target_type="1" file_type="6" business_type="SG_ENTERPRISE_LIBRARY">
												<i class="icon-plus"></i>
												<span>添加文件...</span>
											</span>
											<table role="presentation" class="table table-striped">
												<tbody fjlb="0701" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>
											</table>
										</div>
									</td>
								</tr>
							</table>
						</td>
						
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right"  colspan="2">主项资质</th>
						<td  class="right-border bottom-border" >
							<select  id="ZHENGSHU"  class="span12" style="width:35%" name="SG_ZIZHI_UID" fieldname="SG_ZIZHI_UID" onchange="loadZZDJ(this,'zhuxiang',null)"><option value="">请选择</option></select>
							<select  id="ZHU_DENGJI"  class="span12" style="width:25%" name="ZHU_DENGJI" fieldname="ZHU_DENGJI" value=""><option>等级</option></select>
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right"  colspan="2">资质证书编号</th>
						<td  class="right-border bottom-border">
							<input id="ZHENGSHU_CODE" class="span12" style="width:35%" placeholder="必填" check-type="required"  name="ZHENGSHU_CODE" fieldname="ZHENGSHU_CODE" type="text" />
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">获取资质日期</th>
						<td  class="right-border bottom-border">
							<input id="ZIZHI_DATE"   style="width:33.5%;" fieldtype="date" fieldformat="YYYY-MM-DD" name="ZIZHI_DATE" fieldname="ZIZHI_DATE" class="Wdate" type="text" onClick="WdatePicker()" />
							<font style="color:red;font-size: 16px"><b>*</b></font>
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">资质有效期</th>
						<td  class="right-border bottom-border">
							<input id="VALID_DATE"   style="width:33.5%;" fieldtype="date" fieldformat="YYYY-MM-DD" name="VALID_DATE" fieldname="VALID_DATE" class="Wdate" type="text" onClick="WdatePicker()" />
							<font style="color:red;font-size: 16px"><b>*</b></font>
						</td>
					</tr>
					<tr>
			        	<th width="15%" class="right-border bottom-border text-right" colspan="2">资质证书扫描件</th>
			        	<td  class="bottom-border right-border">
							<div>
								<span class="btn btn-fileUpload" id="addFileSpan" onclick="doSelectFile(this,'parent');"  ywid="ID" target_type="1" file_type="4" business_type="SG_ENTERPRISE_LIBRARY">
									<i class="icon-plus"></i>
									<span>添加文件...</span>
								</span>
								<table role="presentation" class="table table-striped">
									<tbody ywid="ID" target_type="1" file_type="7" business_type="SG_ENTERPRISE_LIBRARY" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>
								</table>
							</div>
						</td>
			        </tr>
					<!-- 增项资质 -->
					<tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">增项资质</th>
						<td  class="right-border bottom-border">
							<table class="B-table" width="100%" id="zizhiList">
								<tr>
									<th style="width:36%">资质</th>
									<th style="width:26%">等级</th>
									<th style="width:14%">资质编号</th>
									<th style="width:24%">有效期</th>
								</tr>
								<tr id="cloneTR" style="display: none;"><!-- 用来复制 -->
									<td>
										<input id="SG_ENTERPRISE_ZEN_ZIZHI_UID" style="width:99%" class="span12"  name="SG_ENTERPRISE_ZEN_ZIZHI_UID" fieldname="SG_ENTERPRISE_ZEN_ZIZHI_UID" type=hidden />
										<select id="SG_ZIZHI_UID"  class="span12" style="width:99%" name="SG_ZIZHI_UID" fieldname="ZENG_SG_ZIZHI_UID" onchange="loadZZDJ(this,'zengxiang',null)"><option value="">请选择</option></select>
									</td>
									<td><select id="ZIZHI_DENGJI"  class="span12" style="width:99%" name="ZIZHI_DENGJI" fieldname="ZIZHI_DENGJI"><option value="">等级</option></select></td>
									<td><input id="ZIZHI_CODE" style="width:99%" class="span12"  name="ZIZHI_CODE" fieldname="ZIZHI_CODE" type="text" /></td>
									<td><input id="ZENG_VALID_DATE"   style="width:70%" fieldtype="date" fieldformat="YYYY-MM-DD" name="ZENG_VALID_DATE" fieldname="ZENG_VALID_DATE" class="Wdate" type="text" onClick="WdatePicker()" /><img onclick="removeZengxiang(this)" style="cursor: pointer;" title="删除" src="/wndjsbg/img/sg/icon-removeZz.jpg"><img onclick="addZengxiang(this)" style="cursor: pointer;" title="增加" src="/wndjsbg/img/sg/icon-addZz.jpg"></td>
								</tr>
								<% 
									if("register".equals(type)){
										%>
										<tr>
											<td>
												<input id="SG_ENTERPRISE_ZEN_ZIZHI_UID" style="width:99%" class="span12" name="SG_ENTERPRISE_ZEN_ZIZHI_UID" fieldname="SG_ENTERPRISE_ZEN_ZIZHI_UID" type=hidden />
												<select id="SG_ZIZHI_UID"  class="span12" style="width:99%" name="SG_ZIZHI_UID" fieldname="ZENG_SG_ZIZHI_UID" onchange="loadZZDJ(this,'zengxiang',null)"><option value="">请选择</option></select>
											</td>
											<td><select id="ZIZHI_DENGJI"  class="span12" style="width:99%" name="ZIZHI_DENGJI" fieldname="ZIZHI_DENGJI"><option value="">等级</option></select></td>
											<td><input id="ZIZHI_CODE" style="width:99%" class="span12"  name="ZIZHI_CODE" fieldname="ZIZHI_CODE" type="text" /></td>
											<td><input id="ZENG_VALID_DATE"   style="width:70%" fieldtype="date" fieldformat="YYYY-MM-DD" name="ZENG_VALID_DATE" fieldname="ZENG_VALID_DATE" class="Wdate" type="text" onClick="WdatePicker()"/><img onclick="removeZengxiang(this)" style="cursor: pointer;" title="删除" src="/wndjsbg/img/sg/icon-removeZz.jpg"></td>
										</tr>
										<tr>
											<td>
												<input id="SG_ENTERPRISE_ZEN_ZIZHI_UID" style="width:99%" class="span12" name="SG_ENTERPRISE_ZEN_ZIZHI_UID" fieldname="SG_ENTERPRISE_ZEN_ZIZHI_UID" type=hidden />
												<select id="SG_ZIZHI_UID"  class="span12" style="width:99%" name="SG_ZIZHI_UID" fieldname="ZENG_SG_ZIZHI_UID" onchange="loadZZDJ(this,'zengxiang',null)"><option value="">请选择</option></select>
											</td>
											<td><select id="ZIZHI_DENGJI"  class="span12" style="width:99%" name="ZIZHI_DENGJI" fieldname="ZIZHI_DENGJI"><option value="">等级</option></select></td>
											<td><input id="ZIZHI_CODE" style="width:99%" class="span12"  name="ZIZHI_CODE" fieldname="ZIZHI_CODE" type="text" /></td>
											<td><input id="ZENG_VALID_DATE"   style="width:70%" fieldtype="date" fieldformat="YYYY-MM-DD" name="ZENG_VALID_DATE" fieldname="ZENG_VALID_DATE" class="Wdate" type="text" onClick="WdatePicker()" /><img onclick="removeZengxiang(this)" style="cursor: pointer;" title="删除" src="/wndjsbg/img/sg/icon-removeZz.jpg"></td>
										</tr>
										<tr>
											<td>
												<input id="SG_ENTERPRISE_ZEN_ZIZHI_UID" style="width:99%" class="span12" name="SG_ENTERPRISE_ZEN_ZIZHI_UID" fieldname="SG_ENTERPRISE_ZEN_ZIZHI_UID" type=hidden />
												<select id="SG_ZIZHI_UID"  class="span12" style="width:99%" name="SG_ZIZHI_UID" fieldname="ZENG_SG_ZIZHI_UID" onchange="loadZZDJ(this,'zengxiang',null)"><option value="">请选择</option></select>
											</td>
											<td><select id="ZIZHI_DENGJI"  class="span12" style="width:99%" name="ZIZHI_DENGJI" fieldname="ZIZHI_DENGJI"><option value="">等级</option></select></td>
											<td><input id="ZIZHI_CODE" style="width:99%" class="span12"  name="ZIZHI_CODE" fieldname="ZIZHI_CODE" type="text" /></td>
											<td><input id="ZENG_VALID_DATE"   style="width:70%" fieldtype="date" fieldformat="YYYY-MM-DD" name="ZENG_VALID_DATE" fieldname="ZENG_VALID_DATE" class="Wdate" type="text" onClick="WdatePicker()" /><img onclick="removeZengxiang(this)" style="cursor: pointer;" title="删除" src="/wndjsbg/img/sg/icon-removeZz.jpg"></td>
										</tr>
										<%
									}
								%>
								<tr>
									<td>
										<input id="SG_ENTERPRISE_ZEN_ZIZHI_UID" style="width:99%" class="span12" name="SG_ENTERPRISE_ZEN_ZIZHI_UID" fieldname="SG_ENTERPRISE_ZEN_ZIZHI_UID" type=hidden />
										<select id="SG_ZIZHI_UID"  class="span12" style="width:99%" name="SG_ZIZHI_UID" fieldname="ZENG_SG_ZIZHI_UID" onchange="loadZZDJ(this,'zengxiang',null)"><option value="">请选择</option></select>
									</td>
									<td><select id="ZIZHI_DENGJI"  class="span12" style="width:99%" name="ZIZHI_DENGJI" fieldname="ZIZHI_DENGJI"><option value="">等级</option></select></td>
									<td><input id="ZIZHI_CODE" style="width:99%" class="span12"  name="ZIZHI_CODE" fieldname="ZIZHI_CODE" type="text" /></td>
									<td><input id="ZENG_VALID_DATE"   style="width:70%" fieldtype="date" fieldformat="YYYY-MM-DD" name="ZENG_VALID_DATE" fieldname="ZENG_VALID_DATE" class="Wdate" type="text" onClick="WdatePicker()" /><img onclick="removeZengxiang(this)" style="cursor: pointer;" title="删除" src="/wndjsbg/img/sg/icon-removeZz.jpg"><img onclick="addZengxiang(this)" style="cursor: pointer;" title="增加" src="/wndjsbg/img/sg/icon-addZz.jpg"></td>
								</tr>
							</table>
							
						</td>
					</tr>
					<tr>
				        <th width="15%" class="right-border bottom-border text-right" colspan="2">公司地址</th>
				        <td  class="bottom-border right-border" >
				        	<textarea class="span12" rows="2" id="ADDRESS" check-type="maxlength" maxlength="4000" fieldname="ADDRESS" name="ADDRESS"></textarea>
				        </td>
			        </tr>
			        <tr>
				        <th width="15%" class="right-border bottom-border text-right" colspan="2">驻锡地址</th>
				        <td  class="bottom-border right-border" >
				        	<textarea class="span12" rows="2" id="ZHUXI_ADDRESS" check-type="maxlength" maxlength="4000" fieldname="ZHUXI_ADDRESS" name="ZHUXI_ADDRESS"></textarea>
				        </td>
			        </tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">邮政编码</th>
						<td  class="right-border bottom-border">
							<input id="POSTCODE" class="span12" style="width:35%"  name="POSTCODE" fieldname="POSTCODE" type="text" />
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">公司电话</th>
						<td  class="right-border bottom-border">
							<input id="PHONE" class="span12" style="width:35%" placeholder="必填" check-type="required"  name="PHONE" fieldname="PHONE" type="text" />
							<font style="color:red;font-size: 16px"><b>*</b></font>
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">公司传真</th>
						<td  class="right-border bottom-border">
							<input id="FAX" class="span12" style="width:35%"  name="FAX" fieldname="FAX" type="text" />
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">公司主页</th>
						<td  class="right-border bottom-border">
							<input id="URL" class="span12" style="width:35%"  name="URL" fieldname="URL" type="text" />
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">法人代表</th>
						<td  class="right-border bottom-border">
							<input id="FAREN" class="span12" style="width:35%" placeholder="必填" check-type="required"  name="FAREN" fieldname="FAREN" type="text" />
							<font style="color:red;font-size: 16px"><b>*</b></font>
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">法人职称</th>
						<td  class="right-border bottom-border">
							<input id="FAREN_ZHICHENG" style="width:35%" class="span12"  name="FAREN_ZHICHENG" fieldname="FAREN_ZHICHENG" type="text" />
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">法人代表移动电话</th>
						<td  class="right-border bottom-border">
							<input id="FAREN_MOBILE" style="width:35%"  class="span12" name="FAREN_MOBILE" fieldname="FAREN_MOBILE" type="text" />
							<span style="color: red;" id="ers"></span>
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">驻锡负责人</th>
						<td  class="right-border bottom-border">
							<input id="ZHUXI_FZR" style="width:35%" class="span12" placeholder="必填" check-type="required"  name="ZHUXI_FZR" fieldname="ZHUXI_FZR" type="text" />
							<font style="color:red;font-size: 16px"><b>*</b></font>
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">驻锡负责人移动电话</th>
						<td  class="right-border bottom-border">
							<input id="ZHUXI_MOBILE" style="width:35%" onblur="checkPhone(this)" class="span12" name="ZHUXI_MOBILE" fieldname="ZHUXI_MOBILE" type="text" />
							<span style="color: red;" id="ers"></span>
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">联系人</th>
						<td  class="right-border bottom-border">
							<input id="LIANXIREN" style="width:35%" onblur="checkPhone(this)" class="span12" placeholder="必填" check-type="required"  name="LIANXIREN" fieldname="LIANXIREN" type="text" />
							<font style="color:red;font-size: 16px"><b>*</b></font>
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">联系人移动电话</th>
						<td  class="right-border bottom-border">
							<input id="LIANXIREN_MOBILE" style="width:35%" onblur="checkPhone(this)" class="span12" placeholder="必填" check-type="required" name="LIANXIREN_MOBILE" fieldname="LIANXIREN_MOBILE" type="text" />
							<font style="color:red;font-size: 16px"><b>*</b></font>
							<span style="color: red;" id="ers"></span>
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">联系人Email</th>
						<td  class="right-border bottom-border">
							<input id="LIANXIREN_MAIL" style="width:35%" class="span12"  name="LIANXIREN_MAIL" fieldname="LIANXIREN_MAIL" type="text" />
						</td>
					</tr>
					<tr>
				        <th width="15%" class="right-border bottom-border text-right" colspan="2">备注</th>
				        <td  class="bottom-border right-border" >
				        	<textarea class="span12" rows="3" id="DESCRIPTION" check-type="maxlength" maxlength="4000" fieldname="DESCRIPTION" name="DESCRIPTION"></textarea>
				        </td>
			        </tr>
			       
			      </table>	
			    </div>
			    <div class="tab-pane" id="xmsc2" style="height:100%">
			    	<h4 class="title" >荣誉信息</h4>
					 <table class="B-table" width="100%">
						
							<tr>
						        <th width="15%" class="right-border bottom-border text-right">所获荣誉</th>
						        <td class="bottom-border right-border" >
						        	<table class="B-table" width="100%" id="jx_List">
										<tr>
											<th style="width:26%">&nbsp;奖项名称&nbsp;</th>
											<th style="width:26%">&nbsp;奖项级别&nbsp;</th>
											<th style="width:10%">&nbsp;获奖日期&nbsp;</th>
											<th style="width:22%">&nbsp;附件&nbsp;</th>
											<th style="width:8%">&nbsp;操作&nbsp;</th>
										</tr>
										<tr id="jx_cloneTR" style="display:none;" class="constant_tr"><!-- 用来复制 -->
											<td>
												<input type="hidden" id="SG_E_CREDIT_COMMEND_REWARD_FILEUPLOAD" fieldname="SG_E_CREDIT_COMMEND_REWARD_FILEUPLOAD" name = "CREDIT_COMMEND_REWARD_UID"/> 
												<input type="hidden" id="CREDIT_COMMEND_REWARD_UID" fieldname="CREDIT_COMMEND_REWARD_UID" name = "CREDIT_COMMEND_REWARD_UID"/>
												<input id="CR_NAME" class="span12" style="width:100%"  name="CR_NAME" fieldname="CR_NAME" type="text" />
											</td>
											<td>
												<select id="CR_LEVEL" style="width:100%"  class="span6"  name="CR_LEVEL" fieldname="CR_LEVEL" kind="dic" src="CR_LEVEL" defaultMemo="请选择">
											</td>
											<td><input id="BG_VALIDITY_DATE"   style="width:80%" fieldtype="date" fieldformat="YYYY-MM-DD" name="BG_VALIDITY_DATE" fieldname="BG_VALIDITY_DATE" class="Wdate" type="text" onClick="WdatePicker()" /></td>
											<td>
												<div>
													<span class="btn btn-fileUpload" id="addFileSpan" onclick="doSelectFile(this,'child');"  ywid="CREDIT_COMMEND_REWARD_UID" target_type="1" file_type="31" business_type="SG_E_CREDIT_COMMEND_REWARD">
														<i class="icon-plus"></i>
														<span>上传...</span>
													</span>
													<table role="presentation" class="table table-striped">
														<tbody fjlb="0701" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>
													</table>
												</div>
											</td>
											<td><img onclick="removeTR(this,'jx')" style="cursor: pointer;" title="删除" src="/wndjsbg/img/sg/icon-removeZz.jpg"><img onclick="addTR(this,'jx')" style="cursor: pointer;" title="增加" src="/wndjsbg/img/sg/icon-addZz.jpg"></td>
										</tr>
										<tr class="constant_tr">
											<td>
												<input type="hidden" id="SG_E_CREDIT_COMMEND_REWARD_FILEUPLOAD" fieldname="SG_E_CREDIT_COMMEND_REWARD_FILEUPLOAD" name = "CREDIT_COMMEND_REWARD_UID"/>
												<input type="hidden" id="CREDIT_COMMEND_REWARD_UID" fieldname="CREDIT_COMMEND_REWARD_UID" name = "CREDIT_COMMEND_REWARD_UID"/>
												<input id="CR_NAME" class="span12" style="width:100%"  name="CR_NAME" fieldname="CR_NAME" type="text" />
											</td>
											<td>
												<select id="CR_LEVEL" style="width:100%"  class="span6"  name="CR_LEVEL" fieldname="CR_LEVEL" kind="dic" src="CR_LEVEL" defaultMemo="请选择">
											</td>
											<td><input id="BG_VALIDITY_DATE"   style="width:80%" fieldtype="date" fieldformat="YYYY-MM-DD" name="BG_VALIDITY_DATE" fieldname="BG_VALIDITY_DATE" class="Wdate" type="text" onClick="WdatePicker()" /></td>
											<td>
												<div>
													<span class="btn btn-fileUpload" id="addFileSpan" onclick="doSelectFile(this,'child');"  ywid="CREDIT_COMMEND_REWARD_UID" target_type="1" file_type="31" business_type="SG_E_CREDIT_COMMEND_REWARD">
														<i class="icon-plus"></i>
														<span>上传...</span>
													</span>
													<table role="presentation" class="table table-striped">
														<tbody fjlb="0701" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>
													</table>
												</div>
											</td>
											<td><img onclick="removeTR(this,'jx')" style="cursor: pointer;" title="删除" src="/wndjsbg/img/sg/icon-removeZz.jpg"><img onclick="addTR(this,'jx')" style="cursor: pointer;" title="增加" src="/wndjsbg/img/sg/icon-addZz.jpg"></td>
										</tr>
									</table>
						        </td>
						   	</tr>
							
						</table>
						
						
						
					<h4 class="title" >工程信息</h4>
					<table class="B-table" width="100%">
						<tr>
					        <th width="15%" class="right-border bottom-border text-right">参与工程</th>
					        <td class="bottom-border right-border" >
					        	<table class="B-table" width="100%" id="xm_List">
										<tr>
											<th style="width:52%">&nbsp;项目名称&nbsp;</th>
											<th style="width:18%">&nbsp;中标通知日期&nbsp;</th>
											<th style="width:22%">&nbsp;附件&nbsp;</th>
											<th style="width:8%">&nbsp;操作&nbsp;</th>
										</tr>
										<tr id="xm_cloneTR" style="display:none;" class="constant_tr"><!-- 用来复制 -->
											<td>
												<input type="hidden" id="SG_E_CREDIT_PROJECTS_FILEUPLOAD" fieldname="SG_E_CREDIT_PROJECTS_FILEUPLOAD" name = "SG_E_CREDIT_PROJECTS_FILEUPLOAD"/>
												<input type="hidden" id="CREDIT_PROJECTS_UID" fieldname="CREDIT_PROJECTS_UID" name = "CREDIT_PROJECTS_UID"/>
												<input id="PROJECT_NAME" class="span12" style="width:100%"  name="PROJECT_NAME" fieldname="PROJECT_NAME" type="text" />
											</td>
											<td><input id="YXBEGIN_DATE"   style="width:80%" fieldtype="date" fieldformat="YYYY-MM-DD" name="YXBEGIN_DATE" fieldname="YXBEGIN_DATE" class="Wdate" type="text" onClick="WdatePicker()" /></td>
											<td>
												<div>
													<span class="btn btn-fileUpload" id="addFileSpan" onclick="doSelectFile(this,'child');"  ywid="CREDIT_PROJECTS_UID" target_type="1" file_type="32" business_type="SG_E_CREDIT_PROJECTS">
														<i class="icon-plus"></i>
														<span>上传...</span>
													</span>
													<table role="presentation" class="table table-striped">
														<tbody fjlb="0701" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>
													</table>
												</div>
											</td>
											<td><img onclick="removeTR(this,'xm')" style="cursor: pointer;" title="删除" src="/wndjsbg/img/sg/icon-removeZz.jpg"><img onclick="addTR(this,'xm')" style="cursor: pointer;" title="增加" src="/wndjsbg/img/sg/icon-addZz.jpg"></td>
										</tr>
										<tr class="constant_tr">
											<td>
												<input type="hidden" id="SG_E_CREDIT_PROJECTS_FILEUPLOAD" fieldname="SG_E_CREDIT_PROJECTS_FILEUPLOAD" name = "SG_E_CREDIT_PROJECTS_FILEUPLOAD"/>
												<input type="hidden" id="CREDIT_PROJECTS_UID" fieldname="CREDIT_PROJECTS_UID" name = "CREDIT_PROJECTS_UID"/>
												<input id="PROJECT_NAME" class="span12" style="width:100%"  name="PROJECT_NAME" fieldname="PROJECT_NAME" type="text" />
											</td>
											<td><input id="YXBEGIN_DATE"   style="width:80%" fieldtype="date" fieldformat="YYYY-MM-DD" name="YXBEGIN_DATE" fieldname="YXBEGIN_DATE"class="Wdate" type="text" onClick="WdatePicker()" /></td>
											<td>
												<div>
													<span class="btn btn-fileUpload" id="addFileSpan" onclick="doSelectFile(this,'child');"  ywid="CREDIT_PROJECTS_UID" target_type="1" file_type="32" business_type="SG_E_CREDIT_PROJECTS">
														<i class="icon-plus"></i>
														<span>上传...</span>
													</span>
													<table role="presentation" class="table table-striped">
														<tbody class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" ywid="CREDIT_PROJECTS_UID" target_type="1" file_type="32" business_type="SG_E_CREDIT_PROJECTS"></tbody>
													</table>
												</div>
											</td>
											<td><img onclick="removeTR(this,'xm')" style="cursor: pointer;" title="删除" src="/wndjsbg/img/sg/icon-removeZz.jpg"><img onclick="addTR(this,'xm')" style="cursor: pointer;" title="增加" src="/wndjsbg/img/sg/icon-addZz.jpg"></td>
										</tr>
									</table>
					        </td>
				        </tr>
					</table>
			    </div>
			</div>
			<div style="height: 5px;"></div>
      </form>
    </div>
   </div>
  </div>
  <jsp:include page="/jsp/file_upload/fileupload_config.jsp" flush="true"/>
  </div>
  <div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id = "queryXML">
         <input type="hidden" name="txtXML" id = "txtXML">
<%--         <input type="hidden" name="txtFilter"  order="desc" fieldname = "n.SERIAL_NO" id = "txtFilter">--%>
         <input type="hidden" name="resultXML" id = "resultXML">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
         
		 <input type="hidden" name="ywid2" id = "ywid2" value="1234">
		 <input type="hidden" name="ywid" id = "ywid" value="123">
 	</FORM>
 </div>
</body>
<script>
</script>
</html>