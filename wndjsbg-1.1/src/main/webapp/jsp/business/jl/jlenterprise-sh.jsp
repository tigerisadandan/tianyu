<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>监理企业信息库-维护</title>
<%
	//String type=request.getParameter("type");
	String id= (String)request.getAttribute("id");
	String oldId= (String)request.getAttribute("oldId");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/jlEnterpriseController/";
var controllernameZenZizhi= "${pageContext.request.contextPath }/jl/enterpriseZenZizhiController/";
var id ="<%=id%>";
var oldId = "<%=oldId%>";
//页面初始化
$(function() {
	init();
	
	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
		if($("#jlEnterpriseForm").validationButton()){
		    //生成json串
		   // var data = Form2Json.formToJSON(jlEnterpriseForm);
		   
		  //组成保存json串格式
		  //  var data1 = defaultJson.packSaveJson(data);
		  //  alert($("#ENTERPRISE_LIBRARY_UID").val());
		  //调用ajax插入
		   // if($("#ENTERPRISE_LIBRARY_UID").val() != "" && $("#ENTERPRISE_LIBRARY_UID").val() != null){
    		//	defaultJson.doInsertJson(controllername + "?update", data1);
    		//}
		   // var a=$(window).manhuaDialog.getParentObj();
		   // a.init();
			//$(window).manhuaDialog.close();
			updateShzt();
		    //var a=$(window).manhuaDialog.getParentObj();
		    //a.init();
			//$(window).manhuaDialog.close();
		}else{
			requireFormMsg();
		  	return;
		}
	});
	

	$("#btnClose").bind("click", function(){
		//var a=$(this).manhuaDialog.close();
		 window.close();
	});


	$("#ZHU_ZIZHI").change(function(){
		var options = $("#ZHU_ZIZHI option:selected").text();//获取当前选择项.
		if(options=='综合资质'){
			$("#ZHU_DENGJI").hide();
			$("#ZHU_DENGJI").val();
		}else{
			$("#ZHU_DENGJI").show();
		}
	}); 
	
	$("#zizhiList tbody tr").find("td:first").find("select").change(function(){
		var v = $(this).val();
		var currTR = $(this).parent().next().find("select");
		if(v=='15'){
			currTR.attr("disabled",true);
		}else{
			currTR.removeAttr("disabled");
		}
	});
    

	
});
//页面默认参数
function init(){

		//生成json串
		//var parentmain = $(window).manhuaDialog.getParentObj();
		//var rowValue = parentmain.$("#resultXML").val();
		//var tempJson = convertJson.string2json1(rowValue);
		$("#QENTERPRISE_LIBRARY_UID").val(id);
		$("#oldId").val(oldId);
		//$("#JL_COMPANY_UID").val(tempJson.JL_COMPANY_UID);
		//查询表单赋值
		var data = combineQuery.getQueryCombineData(queryForm, frmPost);
		var data1 = {
			msg : data
		};
		$.ajax({
			url : controllername + "?query",
			data : data1,
			cache : false,
			async : false,
			dataType : "json",
			type : 'post',
			success : function(response) {				
				var res = dealSpecialCharactor(response.msg);
				$("#resultXML").val(response.msg);
				var resultobj = defaultJson.dealResultJson(res);
				$("#jlEnterpriseForm").setFormValues(resultobj);
				$("#COMPANY_TYPE option").each(function(){ //遍历全部option
				       var txt = $(this).text(); //获取option的内容
				       if(txt==resultobj.COMPANY_TYPE){
				    	   $(this).attr("selected",true);
						}
				 });	
				 var code = resultobj.COMPANY_CODE;
				 $("#DAIMA_Z").val(code.split("-")[0]);
				 $("#DAIMA_F").val(code.split("-")[1]);
				//getOldValues(resultobj);	
				return true;
			}
		});
		//加载附件
		queryFileData(id, "", "", "999002");
		//queryFileData2('ENTERPRISE_LIBRARY_UID','parent','ENTERPRISE_LIBRARY');
		builderZizhiList();
		//列出审批意见信息
		//var queryconditionXX = "{\"logic\":\"and\",\"fieldname\":\"ENTERPRISE_LIBRARY_UID\",\"operation\":\"=\",\"value\":\""+id+"\",\"fieldtype\":'',\"fieldformat\":''}";
	
		//var dataXX = "{querycondition: {conditions: [" +queryconditionXX+"]},pages:{recordsperpage:200, currentpagenum:1, totalpage:0, countrows:0},orders:[{\"fieldname\":\"SHRQ\",\"order\":\"asc\"}]}";
		//调用ajax插入
		//defaultJson.doQueryJsonList(controllername+"?queryspyj", dataXX, spyjList);
	

//组织机构代码验证
$("#DAIMA_Z").blur(function(){
	var daimaz = $("#DAIMA_Z").val();
	var reg = /^\d*$/;
	
    //验证字符长度
	if(daimaz == ''|| daimaz.length<8 || !reg.test(daimaz)){
		alert("前半部分必须为8位数字");
		$("#DAIMA_Z").focus();
		return;
	}
	//字符有效性(是否重复)
	//if(daimaz!=''&&daimaf!=''){
	//	checkCode();
	//}
});	

$("#DAIMA_F").blur(function(){
	var daimaz = $("#DAIMA_Z").val();
	var daimaf = $("#DAIMA_F").val();
	var regf = /^[A-Za-z0-9]$/;
	var regz = /^\d*$/;
	
    //验证字符长度
	if(daimaz == ''|| daimaz.length<8 || !regz.test(daimaz)){
		alert("前半部分必须为8位数字");
		$("#DAIMA_Z").focus();
		return;
	}
	if(daimaz == ''||!regf.test(daimaf)){
		alert("后半部分是数字或字母");
		$("#DAIMA_F").focus();
		return;
	}
	//字符有效性(是否重复)
	if(daimaz!=''&&daimaf!=''){
		checkCode();
	}
});
}




function builderZizhiList(){
	$.ajax({
		url : controllernameZenZizhi+"queryListZeng?uid="+$("#JL_COMPANY_UID").val(),
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
				//loadZZDJ($(cloneNew).find("select").eq(0),'zengxiang',this.ZIZHI_DENGJI_UID);
				$(cloneNew).find("#ENTERPRISE_ZEN_ZIZHI_UID").val(this.ENTERPRISE_ZEN_ZIZHI_UID);
				$(cloneNew).find("#ZIZHI_CODE").val(this.ZIZHI_CODE);
				$(cloneNew).find("#ZIZHI_UID").val(this.ZIZHI_UID);
				$(cloneNew).find("#ZIZHI_DENGJI").val(this.ZIZHI_DENGJI);
				//alert(this.VALID_DATE_SV);
				$(cloneNew).find("#ZENG_VALID_DATE").val(this.ZENG_VALID_DATE.substring(0,10));
				$(cloneNew).find("img").eq(1).hide();

				//if(this.CZ!="0"){
				//	$(cloneNew).find("td").eq(3).append("<div id='changeIcon' style='float:right'><i class='icon-warning-sign showXmxxkInfo' title='"+this.CZ+"'></i></div>");
				//	$(cloneNew).css("background","#FFCC99");
				//}
			});
		}
	});
}

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
				$(this).find("#ZIZHI_UID").append(linkStr);
				//$(this).find("#ZIZHI_DENGJI").append(chushiDengji);
			})
		}
	});
	
}

function updateShzt(){
	var jg = $("input:radio[name='SPXZZT']:checked").val();
	var yj = $("#SHENHE_YIJIAN").val();
	var uid = $("#ENTERPRISE_LIBRARY_UID").val(); 
	var oldId= $("#oldId").val();
	var qyId = $("#JL_COMPANY_UID").val();
	var qyName = $("#COMPANY_NAME").val();
	var qyCode = $("#COMPANY_CODE").val();
	//alert(oldId);
	//if(oldId==""){return;}

	if(jg=="10"){
		if(!confirm("您确定通过此次审核吗?")){
			return;
		}
	}else if(jg=="20"){
		var reason = $("#SHENHE_YIJIAN").val();
		if(reason.length==0){
			alert("请填写审批不通过理由!");
			return;
		}else{
			if(!confirm("确定不通过此次审核吗?")){
				return;
			}	
		}		
	}
	$.ajax({
		url : controllername+"updateDshxx?jg="+jg+"&yj="+yj+"&uid="+oldId,
		data : {"qyId":qyId,"qyName":qyName,"qyCode":qyCode},
		cache : false,
		async : false,
		dataType : "text",
		type : "post",
		success : function(response){
			if(jg=="10"){
				//$("#STATUS").val(10);
				//生成json串
		 		var data = Form2Json.formToJSON(jlEnterpriseForm);
		  		//组成保存json串格式
		   		var data1 = defaultJson.packSaveJson(data);
				var flag =defaultJson.doInsertJson(controllername + "update?jg="+jg, data1);
		    	if(flag){
		        	alert("企业信息已更新入库!");
		        	if(window.opener!=null){window.opener.doReload();}
		        	window.close();
		   		}
			}else if(jg=="20"){
				//$("#STATUS").val(20);
				
				//生成json串
	   		    var data = Form2Json.formToJSON(jlEnterpriseForm);
	   		  	//组成保存json串格式
	   		    var data1 = defaultJson.packSaveJson(data);
	       		var flag =defaultJson.doInsertJson(controllername + "update?jg="+jg, data1);
	       		if(flag){
	       			alert("企业信息审核已退回!");
	       			if(window.opener!=null){window.opener.doReload();}
	       			window.close();
	       		}	
			}
		}
	});
}

function getOldValues(nowObj){
	//查询表单赋值
	var data = combineQuery.getQueryCombineData(queryForm, frmPost);
	var data1 = {
		msg : data
	};
	$.ajax({
		url : controllername + "?queryold",
		data : data1,
		cache : false,
		async : false,
		dataType : "json",
		type : 'post',
		success : function(response) {	
			if(response.msg!=0){			
				var res = dealSpecialCharactor(response.msg);
				oldobj = defaultJson.dealResultJson(res);
				doBdChayi(nowObj,oldobj);
			}
		}
	});

}

function doReload(){
	$("#btnQuery").click();
}
//数据差异
function doBdChayi(nowObj,oldObj){
	for(var key in nowObj){
		if(nowObj[key]!=oldObj[key]){
			
			if($('#'+key).attr("type")!="hidden"){
				var title = "此信息进行了修改,原内容为:"+oldObj[key];
				if($('#'+key).attr("kind")=="dic"){
					if(oldObj[key+"_SV"]){
						title = "此信息进行了修改,原内容为:"+oldObj[key+"_SV"];
					}else{
						title = "此信息进行了修改";
					}

				}
				
				$('#val1 #'+key).after("<div id='changeIcon' style='float:right'><i class='icon-warning-sign showXmxxkInfo' title='"+title+"'></i></div>");
				$('#val1 #'+key).parent().css("background","#FFCC99");
			}
		}
	}
	doClearSH();
}
function doClearSH(){

	$("#changeIcon" ,$("#shenheTable")).each(function(){
		$(this).remove();
	})
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
		$("#zizhiList tr").eq($("#zizhiList tr").size()-2).find("td").eq(3).append('<img onclick="addZengxiang(this)" style="cursor: pointer;" title="增加" src="/wndjbg/img/sg/icon-addZz.jpg">');
	}	
	$(demo).closest("tr").remove();
}
</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">
	<div class="B-small-from-table-autoConcise">
 	 <h4 class="title">企业信息审核
      	<span class="pull-right"> 
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>   		
      	</span>
      </h4>				 
     <form method="post" id="jlEnterpriseForm"  id="val1" >
      <table class="B-table" width="100%" >
	  		<input type="hidden" id="ENTERPRISE_LIBRARY_UID" fieldname="ENTERPRISE_LIBRARY_UID" name = "ENTERPRISE_LIBRARY_UID"/>
	  		<input type="hidden" id="JL_COMPANY_UID" fieldname="JL_COMPANY_UID" name = "JL_COMPANY_UID"/>
	  		<input type="hidden" id="oldId" name = "oldId"/>
        	<tr>
				<th width="8%" class="right-border bottom-border text-right">监理企业全称</th>
	       	 	<td class="bottom-border right-border" colspan="3">
	         		<input  id="COMPANY_NAME" check-type="required"  type="text" fieldname="COMPANY_NAME" name = "COMPANY_NAME" />
	       	 	</td> 
	       	 	      	
           </tr>
        	<tr>
				<th width="8%" class="right-border bottom-border text-right">组织机构代码</th>
	       	 	<td class="bottom-border right-border" >
	       	 	<input type="hidden" id="COMPANY_CODE" fieldname="COMPANY_CODE" />
	         		<input id="DAIMA_Z" name="DAIMA_Z" type="text" maxlength="8"  style="width: 49%"/>
						-
					<input type="text" style="width: 15%"  maxlength="1" id="DAIMA_F" name="DAIMA_F">	
	       	 	</td> 
	       	 	<th width="8%" class="right-border bottom-border text-right">附件</th>
	       	 	<td class="bottom-border right-border" width="15%">
					<table role="presentation" id="zzjg" class="table table-striped">
						<tbody fjlb="1071" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>
					</table>
	       	 	</td>    	
           </tr>
           <tr>

         	<th width="8%" class="right-border bottom-border text-right">企业类型</th>
       		<td class="bottom-border right-border" width="15%">
         		<select  id="COMPANY_TYPE"   name="COMPANY_TYPE" fieldname="COMPANY_TYPE"  kind="dic" src="COMPANY_TYPE" defaultMemo="--请选择--">
				</select>
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">是否外地企业</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="WAIDI_Y" type="radio" kind="dic" src="WAIDI_Y"  name="WAIDI_Y" fieldname="WAIDI_Y" defaultValue="N">
         	</td>
         </tr>

           <tr>
				<th width="8%" class="right-border bottom-border text-right">营业执照注册号</th>
	       	 	<td class="bottom-border right-border" width="15%">
	         		<input  id="ZHIZHAO"   type="text" fieldname="ZHIZHAO" name = "ZHIZHAO" />
	       	 	</td> 
  				<th width="8%" class="right-border bottom-border text-right">附件</th>
	       	 	<td class="bottom-border right-border" width="15%">
	       	 		<table role="presentation" id="yyzz" class="table table-striped">
						<tbody fjlb="1061"  class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" >
						</tbody>
					</table>
	       	 	</td>     
           </tr>
           
            <tr>
				<th width="8%" class="right-border bottom-border text-right">营业执照有效期</th>
	       	 	<td class="bottom-border right-border" width="15%" colspan="3">
	         		<input  id="ZHIZHAO_VALID"   type="text" fieldname="ZHIZHAO_VALID" name = "ZHIZHAO_VALID" class='Wdate' onClick='WdatePicker()'/>
	       	 	</td> 
	       	 	   	
           </tr>
           <tr>
           		<th width="8%" class="right-border bottom-border text-right">税务登记号</th>
	       		<td class="bottom-border right-border" width="15%">
	         		<input  id="SHUIWU" type="text" fieldname="SHUIWU" name = "SHUIWU" />
	         	</td>  
	         	<th width="8%" class="right-border bottom-border text-right">附件</th>
	       	 	<td class="bottom-border right-border" width="15%">
							<table role="presentation" id="swdj" class="table table-striped" >
												<tbody fjlb="1062"  class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery">
												</tbody>
										</table>
	       	 	</td>       
           </tr>
         <tr>
			<th width="8%" class="right-border bottom-border text-right">开户银行</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="BANK"   type="text" fieldname="BANK" name = "BANK" />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">附件</th>
	       	 	<td class="bottom-border right-border" width="15%">
					<table role="presentation" id="khxkz" class="table table-striped">
												<tbody fjlb="1063"  class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" >
												</tbody>
										</table>
	       	</td>  
         </tr>
         <tr>
			<th width="8%" class="right-border bottom-border text-right">开户行帐号</th>
       		<td class="bottom-border right-border" width="15%" colspan="3">
         		<input  id="BANK_ACCOUNT" type="text" fieldname="BANK_ACCOUNT" name = "BANK_ACCOUNT" />
         	</td>
         </tr>
       
          <tr>
			
         	<th width="8%" class="right-border bottom-border text-right">主项资质</th>
       		<td class="bottom-border right-border" colspan="3">
					<select  id="ZHU_ZIZHI"   name="ZHU_ZIZHI" fieldname="ZHU_ZIZHI"   kind="dic"  src="T#ZIZHI: ZIZHI_UID:ZIZHI_NAME:1=1 order by SERIAL_NO" defaultValue="" style="width: 15%" >
					</select>
										
					<select  id="ZHU_DENGJI"   name="ZHU_DENGJI" fieldname="ZHU_DENGJI"  kind="dic" src="JLQY_ZXZZ" defaultMemo="--请选择--" style="width: 10%">
				</select>
         	</td>
         </tr>

            <tr>
				<th width="8%" class="right-border bottom-border text-right">主项资质编号</th>
	       	 	<td class="bottom-border right-border" width="15%">
	         		<input  id="ZHENGSHU_CODE"   type="text" fieldname="ZHENGSHU_CODE" name = "ZHENGSHU_CODE" />
	       	 	</td> 
	       	 	<th width="8%" class="right-border bottom-border text-right">附件</th>
	       	 	<td class="bottom-border right-border" width="15%">
	       	 		<table role="presentation" id="zzzs" class="table table-striped">
												<tbody fjlb="1064"  class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery">
												</tbody>
										</table>
	       	 	</td>        	
           </tr>
        
        
         <tr>
				<th width="8%" class="right-border bottom-border text-right">主项资质取得日期</th>
	       	 	<td class="bottom-border right-border" width="15%">
	         		<input  id="ZIZHI_DATE"   type="text" fieldname="ZIZHI_DATE" name = "ZIZHI_DATE" class='Wdate' onClick='WdatePicker()'/>
	       	 	</td> 
	       	 	<th width="8%" class="right-border bottom-border text-right">主项资质有效期</th>
	       	 	<td class="bottom-border right-border" width="15%">
	         		<input  id="VALID_DATE"   type="text" fieldname="VALID_DATE" name = "VALID_DATE" class='Wdate' onClick='WdatePicker()'/>
	       	 	</td>    	
           </tr>
        
					<!-- 增项资质 -->
					<tr>
						<th width="8%" class="right-border bottom-border text-right" >增项资质</th>
						<td  class="right-border bottom-border" colspan="3">
							<table class="B-table" width="100%" id="zizhiList">
								<tr>
									<th style="width:36%">资质</th>
									<th style="width:26%">等级</th>
									<th style="width:14%">资质编号</th>
									<th style="width:24%">有效期</th>
								</tr>
								<tr id="cloneTR" style="display: none;"><!-- 用来复制 -->
									<td>

										<select  id="ZIZHI_UID"   name="ZIZHI_UID" fieldname="ZENG_ZIZHI_UID"   kind="dic"  src="T#ZIZHI:ZIZHI_UID:ZIZHI_NAME:1=1 order by SERIAL_NO" defaultValue="--请选择--" style="width: 100%">
										</select>
									</td>
									<td>
										<select  id="ZIZHI_DENGJI"    name="ZIZHI_DENGJI" fieldname="ZIZHI_DENGJI"  kind="dic" src="JLQY_ZENXZZ" defaultMemo="--请选择--" style="width: 100%">
										</select>
									</td>
									<td><input id="ZIZHI_CODE" style="width:99%" class="span12" name="ZIZHI_CODE" fieldname="ZIZHI_CODE" type="text" /></td>
									<td><input id="ZENG_VALID_DATE"   style="width:80%" fieldtype="date" fieldformat="YYYY-MM-DD" name="ZENG_VALID_DATE" fieldname="ZENG_VALID_DATE" class="Wdate" type="text" onClick="WdatePicker()" /><img onclick="removeZengxiang(this)" style="cursor: pointer;" title="删除" src="/wndjsbg/img/sg/icon-removeZz.jpg"><img onclick="addZengxiang(this)" style="cursor: pointer;" title="增加" src="/wndjsbg/img/sg/icon-addZz.jpg"></td>
								</tr>
				
								<tr>
									<td>
										<select  id="ZIZHI_UID"   name="ZIZHI_UID" fieldname="ZENG_ZIZHI_UID"   kind="dic"  src="T#ZIZHI:ZIZHI_UID:ZIZHI_NAME:1=1 order by SERIAL_NO" defaultValue="--请选择--" style="width: 100%">
										</select>
									</td>
									<td>
										<select  id="ZIZHI_DENGJI"    name="ZIZHI_DENGJI" fieldname="ZIZHI_DENGJI"  kind="dic" src="JLQY_ZENXZZ" defaultMemo="--请选择--" style="width: 100%">
										</select>
									</td>
									<td><input id="ZIZHI_CODE" style="width:99%" class="span12" name="ZIZHI_CODE" fieldname="ZIZHI_CODE" type="text" /></td>
									<td><input id="ZENG_VALID_DATE"   style="width:80%" fieldtype="date" fieldformat="YYYY-MM-DD" name="ZENG_VALID_DATE" fieldname="ZENG_VALID_DATE" class="Wdate" type="text" onClick="WdatePicker()" /><img onclick="removeZengxiang(this)" style="cursor: pointer;" title="删除" src="/wndjsbg/img/sg/icon-removeZz.jpg"><img onclick="addZengxiang(this)" style="cursor: pointer;" title="增加" src="/wndjsbg/img/sg/icon-addZz.jpg"></td>
								
								</tr>
							</table>
							
						</td>
					</tr>
          <tr>
			<th width="8%" class="right-border bottom-border text-right">公司地址</th>
       		<td class="bottom-border right-border" colspan="3">
			       <textarea class="span12"  id="ADDRESS"  type="text" fieldname="ADDRESS" name = "ADDRESS" maxlength="4000" ></textarea>
         	</td>
         	
         </tr>
          <tr>

			<th width="8%" class="right-border bottom-border text-right">邮政编码</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="POSTCODE"  type="text" fieldname="POSTCODE" name = "POSTCODE" />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">公司电话</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="PHONE"  type="text" fieldname="PHONE" name = "PHONE" />
         	</td>
         </tr>
          <tr>

         	<th width="8%" class="right-border bottom-border text-right">公司传真</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="FAX" type="text" fieldname="FAX" name = "FAX" />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">公司主页</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="URL" type="text" fieldname="URL" name = "URL" />
         	</td>
         </tr>
         <tr>
			<th width="8%" class="right-border bottom-border text-right">法人代表</th>
       		<td class="bottom-border right-border" >
         		<input  id="FAREN"  type="text" fieldname="FAREN" name = "FAREN" />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">法人职称</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="FAREN_ZHICHENG"  type="text" fieldname="FAREN_ZHICHENG" name = "FAREN_ZHICHENG" />
         	</td>
         </tr>
          <tr>

         	<th width="8%" class="right-border bottom-border text-right">法人代表移动电话</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="FAREN_MOBILE" type="text" fieldname="FAREN_MOBILE" name = "FAREN_MOBILE" />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">联系人</th>
       		<td class="bottom-border right-border" colspan="3">
         		<input  id="LIANXIREN"  type="text" fieldname="LIANXIREN" name = "LIANXIREN" />
         	</td>
         </tr>
         
        
          <tr>
			<th width="8%" class="right-border bottom-border text-right">联系人手机</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="LIANXIREN_MOBILE"  type="text" fieldname="LIANXIREN_MOBILE" name = "LIANXIREN_MOBILE" />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">联系人邮箱</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="LIANXIREN_MAIL" type="text" fieldname="LIANXIREN_MAIL" name = "LIANXIREN_MAIL" />
         	</td>
         </tr>
         <tr>
			<th width="8%" class="right-border bottom-border text-right">备注</th>
         	<td class="bottom-border right-border" colspan="3"> 
			     <textarea class="span12" rows="" cols="" maxlength="4000" fieldname="DESCRIPTION" name="DESCRIPTION"></textarea>
         	</td>
         </tr>
      </table>
      
    	<div style="height: 5px;"></div>
      		<div id="shjltxdivid">
				<div class="modal-header" style="background:#0866c6;">
				<h3 style="color:white;">监理企业资质审核
				<span class="pull-right">
					<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">审核</button>
	  			</span>
				</h3>
				</div>
      			<table id="shenheTable" class="B-table" width="100%" >
			        <tr>
						<th width="15%" class="right-border bottom-border text-right">审核结果</th>
			       	 	<td class="bottom-border right-border">
			       	 	
			       	 		<input check-type="required" class="span12" id="SPXZZT" type="radio" kind="dic" src="SPXZZT"  name="SPXZZT" fieldname="SPXZZT" defaultValue="20">
			       	 	</td>
			        </tr>
			       	<tr>
						<th width="15%" class="right-border bottom-border text-right">审核意见</th>
			       	 	<td class="bottom-border right-border">
			       	 		<textarea class="span12" rows="3" id="SHENHE_YIJIAN" check-type="maxlength" maxlength="4000" fieldname="SHENHE_YIJIAN" name="SHENHE_YIJIAN"></textarea>
			       	 	</td>
			        </tr>
				</table>
			</div>
      </form>
      
      <!-- 
      <div style="height: 5px;"></div>
		<table id="YJ_LIST"  border="0" width="100%" cellpadding="0" cellspacing="0" class="content">
			<div class="modal-header" style="background:#0866c6;">
					<h3 style="color:white;">审核记录</h3>
			</div>
			<tr>
				<td>
					<div class="overFlowX">
				 		<table width="100%" class="table-hover table-activeTd B-table" id="spyjList" type="single" pageNum="2000"  noPage="true">
					    <thead>
					 		<tr style="display:blank">
					           <th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
					            <th fieldname="SHR" style="width:200px;" colindex=1 tdalign="center" >审核人</th>
					            <th fieldname="SHRQ_SV" style="width:250px;" colindex=2 tdalign="center" maxlength="200" >&nbsp;审核时间&nbsp;</th>
								<th fieldname="SHJG_SV" colindex=2 tdalign="center" maxlength="200" >&nbsp;审核结果&nbsp;</th>
								<th fieldname="SHYJ" colindex=2 tdalign="center" maxlength="200" >&nbsp;审核意见&nbsp;</th>
					        </tr>
					    </thead>
					    <tbody></tbody>
					  </table>  
		       		</div>
				</td>
			</tr>
	</table>
	 -->
    </div>
   </div>
    <jsp:include page="/jsp/file_upload/fileuploadold_config_js.jsp" flush="true"/> 
  </div>
     <form method="post" id="queryForm"  >
    	  <input type="hidden" id="QENTERPRISE_LIBRARY_UID" fieldname="ENTERPRISE_LIBRARY_UID" name = "ENTERPRISE_LIBRARY_UID" operation="="/>
     </form> 
  
  <div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id = "queryXML">
         <input type="hidden" name="txtXML" id = "txtXML">
         <input type="hidden" name="txtFilter"  order="desc" fieldname = "ENTERPRISE_LIBRARY_UID" id = "txtFilter">
         <input type="hidden" name="resultXML" id = "resultXML">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
 	</FORM>
 </div>

</body>
</html>