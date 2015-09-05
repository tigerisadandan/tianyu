<!DOCTYPE html>
<%@page import="com.ccthanking.framework.Constants"%>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>务工人员信息-查看</title>
<%
	String MINGONG_UID = request.getParameter("MINGONG_UID");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername = "${pageContext.request.contextPath}/rygl/minGongController";

var MINGONG_UID ="<%=MINGONG_UID%>";
//页面初始化
$(function() {
	init();
	//关闭按钮点击事件
	$("#btnClose").bind("click", function(){
		var a=$(this).manhuaDialog.close();
	});
	
	//关闭按钮点击事件
	$("#btnClose2").bind("click", function(){
		var a=$(this).manhuaDialog.close();
});
	
});
	
	//页面默认参数
	function init(){
		
	$.ajax({
		url : controllername+"?detail",
		data : {"MINGONG_UID":MINGONG_UID},
		type : "post",
		dataType : "json",
		success : function(response){
			//将返回json转换成对象
			var resultObj = defaultJson.dealResultJson(response.msg);
			//对表单中插入数据
			$("#MINGONGForm").setFormValues(resultObj);	
			var CARD_UID=resultObj.CARD_UID;
			var HUJI_TYPE=resultObj.HUJI_TYPE;
			var PERSON_TYPE=resultObj.PERSON_TYPE;
			var HUIYUAN=resultObj.HUIYUAN;
			var MG_TYPE=resultObj.MG_TYPE;
			var PEIXUN=resultObj.PEIXUN;
			Card_Information(CARD_UID);
			MG_TYPE_Information(MG_TYPE);
			HUIJI_Information(HUJI_TYPE);
			PERSON_TYPE_Information(PERSON_TYPE);
			HUIYUAN_Information(HUIYUAN);
			PEIXUN_Information(PEIXUN);
			
			queryFileData('MINGONG',MINGONG_UID);
		}		
		});
}
	
	function showCard(){
		window.open("${pageContext.request.contextPath}/jsp/business/dtgl/rygl/gdry-card.jsp","发卡情况","height=600, width=800");
}

	function Card_Information(CARD_UID){
		if(CARD_UID!=null&&CARD_UID!=""){			
			$("#CARD_UID").attr("checked","checked");
		}
	}
	
	function MG_TYPE_Information(MG_TYPE){
		if(MG_TYPE=="1"){			
			$("#MG_TYPE").attr("checked","checked");
		}
	}
	
	function HUIJI_Information(HUJI_TYPE){
		if(HUJI_TYPE=="0"){
			$("#HUJI_TYPE").val("农业");
		}else if(HUJI_TYPE=="1"){
			$("#HUJI_TYPE").val("非农业");
		}
	}
	
	function PERSON_TYPE_Information(PERSON_TYPE){
		if(PERSON_TYPE=="0"){
			$("#PERSON_TYPE").val("本省市");
		}else if(PERSON_TYPE=="1"){
			$("#PERSON_TYPE").val("外省市");
		}
	}
	
	function HUIYUAN_Information(HUIYUAN){
		if(HUIYUAN=="N"){
			$("#HUIYUAN").val("非会员");
		}else if(HUIYUAN=="Y"){
			$("#HUIYUAN").val("会员");
		}
	}
	
	function PEIXUN_Information(PEIXUN){
		if(PEIXUN=="0"){
			$("#PEIXUN").val("有");
		}else if(PEIXUN=="1"){
			$("#PEIXUN").val("无");
		}
	}
</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">
	
	<div class="B-small-from-table-autoConcise">
      <h4 class="title">务工人员信息-查看
      	<span class="pull-right">
      		<button id="showCard" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;" onclick="showCard()">发卡情况</button>
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>	  		
      	</span>
      </h4>
      <div style="height:5px;"></div>
     <form method="post" id="MINGONGForm">
     	<table class="B-table" width="100%" >
     	
	       	 <!-- 务工人员所有信息 -->	       	 	
	       	 <tr>	       	 	
	       	 	<th width="15%" class="right-border bottom-border text-right">人员姓名</th>
	       	 	<td class="bottom-border right-border" colspan="1">
	       	 	<input class="span12"  id="MINGONG_NAME" type="text"  fieldname="MINGONG_NAME" name = "MINGONG_NAME" readonly="readonly"/>	         	        	
	       	 	</td>
	       	 	<td class="bottom-border right-border text-center" colspan="2">
	       	 	<label><input id="CARD_UID" type="checkbox"  fieldname="CARD_UID" name = "CARD_UID"  disabled="disabled" />信息卡情况</label>	         	        	
	       	 	</td>
	       	 </tr>
	       	 
	       	 <tr>
	       	 	<th width="15%" class="right-border bottom-border text-right">性别</th>	      
				<td class="bottom-border right-border" colspan="1">
	       	 	<input class="span12" id="SEX" type="text"  fieldname="SEX" name = "SEX" readonly="readonly" />	         	  	       
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">出生日期</th>
	       	 	<td class="bottom-border right-border" colspan="1">
	       	 	<input class="span12"  id="BIRTH_DATE" type="text"  fieldname="BIRTH_DATE" name = "BIRTH_DATE" readonly="readonly"/>	         	        	
	       	 	</td>
	       	 </tr>	       	 	       	        	 
	       	 
	       	 <tr>
	       	 	<th width="15%" class="right-border bottom-border text-right">籍贯</th>	      
				<td class="bottom-border right-border" colspan="1">
	       	 	<input class="span12" id="ORIGIN" type="text"  fieldname="ORIGIN" name = "ORIGIN" readonly="readonly" />	         	  	       
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">联系电话</th>
	       	 	<td class="bottom-border right-border" colspan="1">
	       	 	<input class="span12"  id="LIANXI_PHONE" type="text"  fieldname="LIANXI_PHONE" name = "LIANXI_PHONE" readonly="readonly"/>	         	        	
	       	 	</td>
	       	 </tr>
	       	 
	       	 <tr>
	       	 	<th width="15%" class="right-border bottom-border text-right">证件类型</th>	      
				<td class="bottom-border right-border" colspan="1">
	       	 	<input class="span12" id="ZHENGJIAN_TYPE_NAME" type="text"  fieldname="ZHENGJIAN_TYPE_NAME" name = "ZHENGJIAN_TYPE_NAME" readonly="readonly" />	         	  	       
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">证件号码</th>
	       	 	<td class="bottom-border right-border" colspan="1">
	       	 	<input class="span12"  id="ZHENGJIAN_NUMBER" type="text"  fieldname="ZHENGJIAN_NUMBER" name = "ZHENGJIAN_NUMBER" readonly="readonly"/>	         	        	
	       	 	</td>
	       	 </tr>
	       	 
			<tr>
			<th  class="right-border bottom-border text-right" colspan="1">身份证</th>
			<td class="bottom-border right-border" colspan="3">
				<input type="hidden" id="target_uid" fieldname="target_uid"  />
				<div>				
				<table role="presentation" class="table table-striped">
				<tbody class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"  file_type="3101"></tbody>
				</table>
				</div>
				<jsp:include page="/jsp/file_upload/fileupload_util.jsp" flush="true"/>
			</td>
			</tr>
			
			<tr>
	       	 	<th width="15%" class="right-border bottom-border text-right">所在单位</th>	      
				<td class="bottom-border right-border" colspan="3">
	       	 	<input class="span12" id="COMPANY_NAME" type="text"  fieldname="COMPANY_NAME" name = "COMPANY_NAME" readonly="readonly" />	         	  	       
	       	 	</td>       	 	
	       	 </tr>
			
			<tr>
	       	 	<th width="15%" class="right-border bottom-border text-right">现在住址</th>	      
				<td class="bottom-border right-border" colspan="3">
	       	 	<input class="span12" id="ADDRESS" type="text"  fieldname="ADDRESS" name = "ADDRESS" readonly="readonly" />	         	  	       
	       	 	</td>       	 	
	       	</tr>
	       	 
	       	<tr>
	       	 	<th width="15%" class="right-border bottom-border text-right">紧急联系人</th>	      
				<td class="bottom-border right-border" colspan="1">
	       	 	<input class="span12" id="LIANXI_REN" type="text"  fieldname="LIANXI_REN" name = "LIANXI_REN" readonly="readonly" />	         	  	       
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">紧急联系人电话</th>
	       	 	<td class="bottom-border right-border" colspan="1">
	       	 	<input class="span12"  id="LIANXI_REN_PHONE" type="text"  fieldname="LIANXI_REN_PHONE" name = "LIANXI_REN_PHONE" readonly="readonly"/>	         	        	
	       	 	</td>
	       	 </tr>
	       			
	       	<tr>
	       	 	<th width="15%" class="right-border bottom-border text-right">学历</th>	      
				<td class="bottom-border right-border" colspan="1">
	       	 	<input class="span12" id="XUELI" type="text"  fieldname="XUELI" name = "XUELI" readonly="readonly" />	         	  	       
	       	 	</td>       	 	
	       	</tr>
	       	
	       	<tr>
			<th  class="right-border bottom-border text-right" colspan="1">学历证书</th>
			<td class="bottom-border right-border" colspan="3">
				<input type="hidden" id="target_uid" fieldname="target_uid"  />
				<div>				
				<table role="presentation" class="table table-striped">
				<tbody class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"  file_type="3102"></tbody>
				</table>
				</div>
				<jsp:include page="/jsp/file_upload/fileupload_util.jsp" flush="true"/>
			</td>
			</tr>
			
			<tr>
	       	 	<th width="15%" class="right-border bottom-border text-right">户籍类别</th>	      
				<td class="bottom-border right-border" colspan="1">
	       	 	<input class="span12" id="HUJI_TYPE" type="text"  fieldname="HUJI_TYPE" name = "HUJI_TYPE" readonly="readonly" />	         	  	       
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">人员类别</th>
	       	 	<td class="bottom-border right-border" colspan="1">
	       	 	<input class="span12"  id="PERSON_TYPE" type="text"  fieldname="PERSON_TYPE" name = "PERSON_TYPE" readonly="readonly"/>	         	        	
	       	 	</td>
	       	 </tr>
	       	 
	       	 <tr>
	       	 	<th width="15%" class="right-border bottom-border text-right">公会会员</th>	      
				<td class="bottom-border right-border" colspan="1">
	       	 	<input class="span12" id="HUIYUAN" type="text"  fieldname="HUIYUAN" name = "HUIYUAN" readonly="readonly" />	         	  	       
	       	 	</td>
	       	 	
	       	 	<td class="bottom-border right-border text-center" colspan="2">
	       	 	<label><input id="MG_TYPE" type="checkbox"  fieldname="MG_TYPE" name = "MG_TYPE" disabled="disabled"/>特殊工种人员</label>	         	        	
	       	 	</td>
	       	 </tr>
	       	 
	       	 <tr>
	       	 	<th width="15%" class="right-border bottom-border text-right">岗前培训</th>	      
				<td class="bottom-border right-border" colspan="1">
	       	 	<input class="span12" id="PEIXUN" type="text"  fieldname="PEIXUN" name = "PEIXUN" readonly="readonly" />	         	  	       
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">培训日期</th>
	       	 	<td class="bottom-border right-border" colspan="1">
	       	 	<input id="PEIXUN_DATE" fieldname="PEIXUN_DATE" name = "PEIXUN_DATE" type="text" readonly="readonly"/>	         	        	
	       	 	</td>
	       	 </tr>
					
      	</table>
      </form>
    </div>
   </div>
  </div>
   
  <div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id = "queryXML">
         <input type="hidden" name="txtXML" id = "txtXML">
         <input type="hidden" name="resultXML" id = "resultXML">
       	 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">		
 	</FORM>
 </div>
</body>
<script>
</script>
</html>