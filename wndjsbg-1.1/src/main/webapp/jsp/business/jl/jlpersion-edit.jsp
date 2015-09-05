<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<title>监理人员信息库-维护</title>
<%

	String id=(String)request.getAttribute("id");
    String t_id=(String)request.getAttribute("t_id");

	
%>
<app:base/>
<style type="text/css">
.hasChanged{
	background:yellow;
}
</style>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername="${pageContext.request.contextPath }/jlPersonLibraryController/";
var controllernamePersonZhengshu="${pageContext.request.contextPath }/jl/jlPersonZhengshuController/";
var controllernameBgMenu="${pageContext.request.contextPath }/bgMenuController/";

var id ="<%=id%>";
var t_id ="<%=t_id%>";

//页面初始化
$(function() {
	//验证是否有权显示锁定/解锁按钮
	$.ajax({
		url : controllernameBgMenu+"?getByMenuCodeAndUserId",
		data : {code : "2040201"},
		type :"post",
		async : false,
		dataType : "json",
		success : function(response){
			if(response.msg == "1"){
				$("#btnLock").show();
				$("#btnUnlock").show();
			}else{
				$("#btnLock").hide();
				$("#btnUnlock").hide();
			}
		}
	});
	init();
	$(":input").each(function(i){
		   $(this).attr("disabled", "true");
	});
	
	$("#btnClose").removeAttr('disabled');
	$("#btnLock").removeAttr('disabled');
	$("#btnUnlock").removeAttr('disabled');
	
	
	//入库(保存) 
	$("#btnSave").click(function(){
		var status=$("#STATUS").val();
		
		if($("#jlPersonLibraryForm").validationButton())
		{ 
			
				var u_id=$("#JL_PERSON_UID").val();
				
				var data = Form2Json.formToJSON(sgPersonLibraryForm);
				var data1 = defaultJson.packSaveJson(data);//用UPDATE
				defaultJson.doInsertJson(controllername + "updatePerson",data1);
				window.opener.closeParentCloseFunction();
		}else{
			requireFormMsg();
		  	return;
		
		}
	});
	//关闭窗口 
	$("#btnClose").click(function() {
	       window.close();
	       window.opener.closeParentCloseFunction();
	  	
	});
   
 $("#btnStop").click(function() {
	if(confirm("确定不通过此次审核吗？")){
		var data = Form2Json.formToJSON(jlPersonLibraryForm);
		  //组成保存json串格式
		var data1 = defaultJson.packSaveJson(data);
	var status=20;
	var u_id=$("#JL_PERSON_UID").val();
	defaultJson.doInsertJson(controllername + "updateShenhe?u_id="+u_id+"&status="+status,data1);
    alert("信息进入未通过状态！"); 
    
	window.close(); 
	
	window.opener.closeParentCloseFunction();
	}
	});

 $("#btnLock").click(function() {
	 
	 var zid=$("#PERSON_LIBRARY_UID").val();
	 window.open("${pageContext.request.contextPath }/jlsuoding/"+zid,"人员锁定");
	
 });
 
 $("#btnUnlock").click(function() {
		if(confirm("确定解锁吗？")){
		 $.ajax({
			    url : controllername+"jiesuo?id="+$("#PERSON_LIBRARY_UID").val(), 
				data : {},
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'get',
				success : function(response) {
					$("#resultXML").val(response.msg);
				}
		     });
		 window.location.href = window.location.href;
		}
	 });	
 
 $("#btnFindOne").click(function() {
	 var jg = $("input:radio[name='SPXZZT']:checked").val();
	 var yj = $("#SHENHE_YIJIAN").val();
	 var uid = $("#PERSON_LIBRARY_UID").val(); 

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
	 	url : controllername+"updateDshxx?jg="+jg+"&yj="+yj+"&uid="+uid,
	 	data : {},
	 	cache : false,
	 	async : false,
	 	dataType : "text",
	 	type : "post",
	 	success : function(response){
	 		if(jg=="10"){
	 			//$("#STATUS").val(10);
	 			//生成json串
	 	 		var data = Form2Json.formToJSON(jlPersonLibraryForm);
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
	    		    var data = Form2Json.formToJSON(jlPersonLibraryForm);
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
});
});
//页面默认参数
function init(){
	
	if(id!="null"&&id!=""){
			$("#QID").val(id);
		    $("#STA").val(1);
		    //获取父页面传过来的参数 
		 
			setFormValues();
			builderZhengshuList();

			 
			//queryFileData2('PERSON_LIBRARY_UID','parent','PERSON_LIBRARY');
			//queryFileData2('JL_PERSON_ZHENGSHU_UID','child','JL_PERSON_ZHENGSHU');

			
	}
		else{
			$("#btnDel").hide();
			//$("#btnShenHe").hide();
			}
	//$("input:radio[name='SPXZZT']")[1].checked = true;
	hideTs(false);
}
function check(){

	 var jg = $("input:radio[name='SPXZZT']:checked").val();
	 if (jg==null){
		 $("#jgtext").text("请输入您的选择"); 
	       return  false;  
		 }
	 else{
		   $("#jgtext").text("");
		   return true;
	   }
}

function stop(){
	if(confirm("确定不通过此次审核吗？")){
		var data = Form2Json.formToJSON(jlPersonLibraryForm);
		  //组成保存json串格式
		var data1 = defaultJson.packSaveJson(data);
	var status=20;
	var u_id=$("#JL_PERSON_UID").val();
	
	defaultJson.doInsertJson(controllername + "updateShenhe?u_id="+u_id+"&status="+status+"&t_id="+t_id,data1);
    alert("信息为未通过状态！"); 
    window.opener.clickRadioShowDate();
	window.close(); 
	
	}
}
//入库(保存) 
function pass(){
	var status=$("#STATUS").val();
	var yj = $("#SHENHE_YIJIAN").val();	
	if(confirm("确定通过此次审核吗？")){ 
		if($("#jlPersonLibraryForm").validationButton()){ 
			
				var u_id=$("#JL_PERSON_UID").val();
				var sta=10;
				var data = Form2Json.formToJSON(jlPersonLibraryForm);
				var data1 = defaultJson.packSaveJson(data);//用UPDATE
				var flag = defaultJson.doInsertJson(controllername + "updateShenhe?u_id="+u_id+"&status="+sta+"&t_id="+t_id,data1);
				var js="";
				if(flag){
					js = $("#resultXML").val();
					
				}
				var sta2=1;
				//defaultJson.doInsertJson(controllername + "updateStatusOne?u_id="+u_id+"&status="+sta2+"&js="+js,data1);
				alert("信息已通过并入库！"); 
				window.opener.clickRadioShowDate();
				window.close(); 
				//window.opener.closeParentCloseFunction();
		}else{
			requireFormMsg();
		  	return;
		}
	}
}
//删除人员信息 
function deletePerson(){
	
	var data = Form2Json.formToJSON(queryForm);
	  //组成保存json串格式
	var data1 = defaultJson.packSaveJson(data);
	defaultJson.doInsertJson(controllername + "delete", data1);

}

function setFormValues(){
	var data = combineQuery.getQueryCombineData(queryForm,frmPost);
	var data1 = {
		msg : data
	};

	var resultobj ;
	var myobj1;
	$.ajax({
		url : controllername+"query",
		data : data1,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {

		$("#resultXML").val(response.msg);
		resultobj = defaultJson.dealResultJson(response.msg);
		$("#jlPersonLibraryForm").setFormValues(resultobj);
		//alert(resultobj.PERSON_LIBRARY_UID);
		queryFileData(resultobj.PERSON_LIBRARY_UID, "", "", "999003");
		$("#QID1").val(resultobj.JL_PERSON_UID);
		$("#QID2").val(resultobj.JL_PERSON_UID);
		$("#ZHUCE_TYPE option").each(function(){ //遍历全部option
		       var txt = $(this).text(); //获取option的内容
		       if(txt==resultobj.ZHUCE_TYPE){
		    	   $(this).attr("selected",true);
				}
		 });

		var jz_y = resultobj.JZ_Y;
		if(jz_y!=""&&jz_y!="0"){
			$("#btnLock").hide();
			$("#sdxx").show();
			$("#RyList").show();
		}else{
			$("#btnUnlock").hide();
		}
	}   
 });
	
	

//	var d= combineQuery.getQueryCombineData(queryForm1,frmPost);
//	var d1 = {
//		msg : d
//	};
//	var myobj2;
//	$.ajax({
//		url : controllername+"query",
//		data : d1,
//		cache : false,
//		async :	false,
//		dataType : "json",  
//		type : 'post',
//		success : function(response) {
//			if(response.msg!=""&&response.msg!="0"){
//				var resultobj2 = defaultJson.dealResultJson(response.msg);
				
				//doBdChayi(resultobj,resultobj2);//
				//doFileChayi(resultobj,resultobj2);//
//			}
	//	
	//	}  
	
	//});
	
	//比较两个json
	//for(var i=0; i<myobj2.length; i++){   
	//	if(myobj1[myobj2[i].text]==myobj2[i].value){
			
		//}else{
		//	document.getElementById("myobj2[i].text").style.backgroundColor = "red";
		//	}}
	   
}
//对比附件差异 
function doFileChayi(nowObj,oldObj){
	$.ajax({
		url : "${pageContext.request.contextPath }/fileUploadController.do?doFileHasChange&uid="+nowObj.PERSON_LIBRARY_UID+"&oldUid="+oldObj.PERSON_LIBRARY_UID+"&type=PERSON_LIBRARY",
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var nums = response.msg.split(":");
			for ( var i = 0; i < nums.length; i++) {
				$("#addFileSpan[file_type='"+nums[i]+"']").closest("td").css("background","#FFCC99");
				$("#addFileSpan[file_type='"+nums[i]+"']").closest("td").append("<div id='changeIcon' style='float:right'><i class='icon-warning-sign showXmxxkInfo' title='附件发生了改变'></i></div>");
			}
		}
	});
}
//对比信息差异
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

function doBdChayi2(zhengshu){
			
		
		$("#ZHENGSHU").val(zhengshu);
		var d= combineQuery.getQueryCombineData(queryForm2,frmPost);
		var d1 = {
			msg : d
		};
		$.ajax({
			url : controllernamePersonZhengshu+"queryListPersoCY?uid="+$("#QID2").val()+"&sta="+$("#STA").val()+"&zs="+$("#ZHENGSHU").val(),
			data : d1, 
			cache : false,
			async :	false,
			dataType : "json",  
			type : 'post',
			success : function(response) {
				if(response.msg!=""&&response.msg!="0"){
				    var resultobj2 = eval('(' + response.msg + ')');
				    alert(response.msg);
					return resultobj2;
				}
			}  
		});
	
}
//已更改的数据提示，提供是否显示 
function hideTs(flag){
	if(flag){
		$("#hideTs").hide();
		$("#showTs").show();
	}else{
		$("#showTs").hide();
		$("#hideTs").show();
	}
	$("div[id='changeIcon']").each(function(){
		if(flag){
			$(this).hide();
		}else{
			$(this).show();
		}
	})
}

function doClearSH(){

	$("#changeIcon" ,$("#RyList")).each(function(){
		$(this).remove();
	})
}

//查询证书并对比证书是否更改 
function builderZhengshuList(){
	var obj1="";
	$.ajax({
		url : controllernamePersonZhengshu+"queryListPersonZhengshu?uid="+$("#QID").val(),
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			obj1 = eval('(' + response.msg + ')');
			$(obj1).each(function(){
				var cloneNew = $("#cloneTR").clone(true);
				//alert(typeof(cloneNew));
				$(cloneNew).removeAttr("style")
				$("#zhigeList").find("tr").eq(2).before(cloneNew);
				//$(cloneNew).find("#SG_PERSON_ZHENGSHU_UID").val(this.SG_PERSON_ZHENGSHU_UID);
				$(cloneNew).find("#JL_PERSON_ZHENGSHU_UID").val(this.JL_PERSON_ZHENGSHU_UID);
				$(cloneNew).find("#ZHUCE_NAME").val(this.ZHUCE_NAME);
				$(cloneNew).find("#ZHUCE_CODE1").val(this.ZHUCE_CODE);
				$(cloneNew).find("#FAZHENG_DATE1").val(this.FAZHENG_DATE);
				$(cloneNew).find("#VALID_DATE1").val(this.VALID_DATE);	
				//$(cloneNew).find("#XX").val(this.XX);//调用存储过程查询 
				//var count=this.XX;
				//if(count!="0"){
					//var title = "此信息进行了修改,原内容为:证书类型:"+oldObj.ZHENGSHU_NAME+",注册专业:"+oldObj.ZHUANYE_NAME+",证书编号:"+oldObj.ZHENGSHU_CODE+",注册日期:"+oldObj.BEGIN_DATE+",有效期:"+oldObj.END_DATE; 
				//	$(cloneNew).find("#bt").after("<div id='changeIcon' style='float:right'><i class='icon-warning-sign showXmxxkInfo' title='"+this.XX+"'></i></div>");
				//	$(cloneNew).css("background","#FFCC99");
				//	}
				
				});
		}
	});
	
	 var rows = $("tbody tr" ,$("#DT1"));
		if(rows.size()==0){
			$("tbody" ,$("#DT1")).append("<tr><td colspan=\"7\" style=\"height: 1px;\" align=\"center\">暂无记录</td></tr>");

		}
}

function builderForm(response){
	$("#resultXML").val(response.msg);
	var resultobj = defaultJson.dealResultJson(response.msg);
	$("#jlPersonLibraryForm").setFormValues(resultobj);
	//隐藏密码修改
	$("#PWD").val(resultobj.MIMA);
	$("#pwdtr").hide();
	$("#pwdtr_re").hide();

	//组织机构代码
	//var code = resultobj.COMPANY_CODE;
	//$("#DAIMA_Z").val(code.substring(0,code.length-2));
	//$("#DAIMA_F").val(code.substring(code.length-2,code.length-1));

	loadZZDJ($("#ZHENGSHU"),'zhuxiang');
}

//增加增项目资格
function addZhige(demo){
	var cloneNew = $("#cloneTR").clone(true);
	$(cloneNew).removeAttr("style")
	$("#zhigeList").append(cloneNew);
	$(demo).hide();
}

function removeZhige(demo){
	if($("#zhigeList tr").size()==3){

		return;
		}
	var tr_index = $("#zhigeList tr").index($(demo).closest("tr"));
	if(tr_index==$("#zhigeList tr").size()-1&&tr_index>2){
		$("#zhigeList tr").eq($("#zhigeList tr").size()-2).find("td").eq($("#zhigeList").find("th").size()-1).append('&nbsp;<img onclick="addZhige(this)" style="cursor: pointer;" title="增加" src="/wndjsbg/img/sg/icon-addZz.jpg">');
	}	
	$(demo).closest("tr").remove();
}
//通过身份证进行查询该人员是否已存在，存在的话返回该人员的sg_person_uid
function checkCode(){
	$.ajax({
		url : controllername+"queryCodeIsEmpty?shenfenID="+$("#SHENFENZHENG").val(),
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			if(response.msg!=null&&response.msg!="0"&&response.msg!=""){//如果数据库中有该ID
				//console.log(response.msg);写日志  
				var resultobj = eval('(' + response.msg + ')')
				$.each(resultobj,function(){
					$("#SG_PERSON_UID").val(this.SG_PERSON_UID);//获取返回来的该人员的person_uid
				})
			}
		}
	});
}
//查询入库状态是否有该信息
function checkStatus(){

var flag =true;
$.ajax({
		url : controllername+"queryStatusIsEmpty?shenfenID="+$("#SHENFENZHENG").val()+"&personUID="+$("#SG_PERSON_UID").val()+"&bz="+ty,
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			if(response.msg!=null&&response.msg!="0"&&response.msg!=""){//如果数据库中有该ID
				flag = false;
				alert("该信息,人员库中已存在! ");
				$("#btnSave").hide();
	 			$("#btnShenHe").hide();
				return flag;
			}
			else{
			$("#btnSave").show();
 			$("#btnShenHe").show();
				return flag;}
		}
	});
	return flag;
}
//验证证书附件 
function doZsFj(){
	flag = true;
	$("#zhigeList>tbody>tr").each(function(i){
		var num = $(this).find(".showFileTab .fu_blockTable_full").size();
		if ($(this).find("#ZHENGSHU_NAME").val()!=null&&$(this).find("#ZHENGSHU_NAME").val()!=""&&$(this).find("#ZSBEGIN_DATE").val()=="") {
			alert("请填写证书注册日期!");
			flag = false;
			return false;
		}
		if ($(this).find("#ZHENGSHU_NAME").val()!=null&&$(this).find("#ZHENGSHU_NAME").val()!=""&&$(this).find("#ZSEND_DATE").val()=="") {
			alert("请填写证书有效期!");
			flag = false;
			return false;
		}
		if ($(this).find("#ZHENGSHU_NAME").val()!=null&&$(this).find("#ZHENGSHU_NAME").val()!=""&&num==0) {
			alert("资格证书必须添加相关附件!");
			flag = false;
			return false;
		}
	})
	return flag;
}
//验证附件 
function fujianNotNull(){

	if($("#SHENFENZHENGHAO>tr").size()>0&&$("#SHEBAOZM>tr").size()>0&&$("#LAODONGHT>tr").size()>0){ 
			return true;
		}else{
			alert("请添加【身份证扫描件】、【劳动合同扫描件】和【社保证明扫描件】!"); 
			return false;
			}
	
}
//验证职称扫描件
function zhichengNotNull(){
	if($("#ZHICHENG_UID").val()!="1"){
		if($("#ZHICHENG>tr").size()>0){
			return true;
		}else{
			alert("请添加【职称扫描件】!");
			return false;
			}
		}else{
		return true;
		}
}
//验证合同开始日期
function beDate(card){
	 if($("#HTBEGIN_DATE").val()=="")
      {
	       $("#datetext").text("合同开始日期不为空");   
	       return  false;   
	    }else{
		   $("#datetext").text("");
		   return true;
	    }
    
	}
//验证合同结束日期 
function enDate(card){
	 if($("#HTEND_DATE").val()=="")
     {
	       $("#datetext").text("合同结束日期不为空");   
	       return  false;   
	    }else{
		   $("#datetext").text("");
		   return true;
	    }
   
	}
//验证身份证是否有效
function isCardNo(card){  
	   //checkStatus();
	   // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
	   var reg = /(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
	   if($("#SHENFENZHENG").val() == null || $("#SHENFENZHENG").val() == "")
		   {
	    	  $("#shenfentext").text("身份证号不能为空"); 
			  return false;
	          }

	   else{
 			 if(reg.test($(card).val()) == false)  
	  		 {  
         		$("#shenfentext").text("身份证输入不合法");
	       		return  false;  
	  		 }else{
		  		 $("#shenfentext").text("");
		  		IdCardValidate($("#SHENFENZHENG").val());
		   	     return true;
	   			}
	   		}
	}
var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];    // 加权因子   
var ValideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];            // 身份证验证位值.10代表X   
function IdCardValidate(idCard) { 

    idCard = trim(idCard.replace(/ /g, ""));               //去掉字符串头尾空格                     
    if (idCard.length == 15) {   
        return isValidityBrithBy15IdCard(idCard);       //进行15位身份证的验证    
    } else if (idCard.length == 18) {   
        var a_idCard = idCard.split("");                // 得到身份证数组   
        if(isValidityBrithBy18IdCard(idCard)&&isTrueValidateCodeBy18IdCard(a_idCard)){   //进行18位身份证的基本验证和第18位的验证
            return true;   
        }else {   
           
            return false;   
        }   
    } else {   
        return false;   
    }   
}  
/**  
 * 验证18位数身份证号码中的生日是否是有效生日  
 * @param idCard 18位书身份证字符串  
 * @return  
 */  
function isValidityBrithBy18IdCard(idCard18){   
   var year =  idCard18.substring(6,10);   
   var month = idCard18.substring(10,12);   
   var day = idCard18.substring(12,14);   
   var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
   // 这里用getFullYear()获取年份，避免千年虫问题   
   if(temp_date.getFullYear()!=parseFloat(year)   
         ||temp_date.getMonth()!=parseFloat(month)-1   
         ||temp_date.getDate()!=parseFloat(day)){   
       	 alert("输入的身份证信息错误"); 
           return false;   
   }else{   
       return true;   
   }   
} 
/**  
 * 判断身份证号码为18位时最后的验证位是否正确  
 * @param a_idCard 身份证号码数组  
 * @return  
 */  
function isTrueValidateCodeBy18IdCard(a_idCard) {   
    var sum = 0;                             // 声明加权求和变量   
    if (a_idCard[17].toLowerCase() == 'x') {   
        a_idCard[17] = 10;                    // 将最后位为x的验证码替换为10方便后续操作   
    }   
    for ( var i = 0; i < 17; i++) {   
        sum += Wi[i] * a_idCard[i];            // 加权求和   
    }   
    valCodePosition = sum % 11;                // 得到验证码所位置
    if (a_idCard[17] == ValideCode[valCodePosition]) {   
        return true;   
    } else {   
         alert("身份证效验位错误!");
        return false;   
    }   
}   
//去掉字符串头尾空格   
function trim(str) {   
    return str.replace(/(^\s*)|(\s*$)/g, "");   
}
//验证电话号码是否有效 
function isPhone(card){  
	//var reg =/^((\d{11})|((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})))$/
	      var reg =/^(13[0-9]|15[012356789]|17[0-9]|18[012356789]|14[57])\d{8}$/;
		  
		  if($("#PHONE").val() == null || $("#PHONE").val() == ""){
			  $("#phonetext").text("电话号码不能为空");
			  return false;
			  }
		  else{
		     if(reg.test($(card).val()) == false)  
		     {  
	         $("#phonetext").text("电话号码输入不合法"); 
		       return  false;  
		     }else{
			   $("#phonetext").text("");
			   return true;
		        }
		  }
		}

//验证邮箱地址是否有效  
function isEmail(card){
	
		  var reg= /^([a-zA-Z0-9_\.])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;  
		  if($("#EMAIL").val() == null || $("#EMAIL").val() == ""){
        	  $("#emailtext").text("");
			   return true;
              }
		  else{
		    if(reg.test($(card).val()) == false)  
		    {  
	         $("#emailtext").text("邮箱地址输入不合法"); 
		       return  false;   
		    }else{
			   $("#emailtext").text("");
			   return true;
		    }
         }
		}
//验证姓名不为空   
function isName(card){
	
		 if($("#PERSON_NAME").val()==null||$("#PERSON_NAME").val()=="")
            {
		       $("#nametext").text("姓名不为空");  
		       return  false;   
		    }else{
			   $("#nametext").text("");
			   return true;
		    }
          
		}
//当证书类型选择某些选项时，进行控制
function doNotShow(item){

	   var zhuanye = null;
	   zhuanye=$(item).parent().parent().find("#ZHUANYE_NAME");
	   code=$(item).parent().parent().find("#ZHENGSHU_CODE");
	   if(item.value==13||item.value==14||item.value==15){
		    $(zhuanye).val("");
			$(zhuanye).prop("disabled","true");
			 $("#zgtext").text("");
			 $(code).attr("placeholder","");
		}
	   else if(item.value==10||item.value==11){
		   $(zhuanye).removeAttr("disabled"); 
		   if($(code).val()==""||$(code).val()=="0"){
			   $("#zgtext").text("建造师证请填写注册编号"); 
		   	   $(code).attr("placeholder","建造师证请填写注册编号");
		   //$(code).val("建造师证，请填写注册编号");
		   }else{
			   $("#zgtext").text("");
			   }
		   }
	   else{
		   $(zhuanye).removeAttr("disabled"); 
		   $("#zgtext").text("");
		   $(code).attr("placeholder","");
		   }
}
//验证证书编号不能为空
function codeNotShow(item){
	
	   var zhuanye = null;
	   zhengshu=$(item).parent().parent().find("#ZHENGSHU_NAME").val();
	   zhuanye=$(item).parent().parent().find("#ZHUANYE_NAME");
	   code=$(item).parent().parent().find("#ZHENGSHU_CODE");

	   if(code!=""){
		   if(zhengshu==13||zhengshu==14||zhengshu==15){
			$(zhuanye).val("");
			$(zhuanye).prop("disabled","true");
		  
		  	}else{
			   $(zhuanye).removeAttr("disabled"); 
			   $("#jgtext").text("");
			   $(code).attr("placeholder","");
			   }
		   
		   }
}

$("#btnExpExcel").click(function() {
	
 	 $(window).manhuaDialog({"title":"导出","type":"text","content":"/wndjsbg/jsp/framework/print/TabListEXP.jsp?tabId=DT1","modal":"3"});
});
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
	        	<INPUT type="text" class="span12" kind="text" id="QID" name="ID"  fieldname="PERSON_LIBRARY_UID" value="" operation="="/>
	        </TD>
			<TD class="right-border bottom-border">
			</TD>
        </TR>
      </table>
      </form>
      <form method="post" id="queryForm1"  >
      <table class="B-table" width="100%">
      <!--可以再此处加入hidden域作为过滤条件 -->
      	<TR  style="display:none;">
	        <TD class="right-border bottom-border">
	        	<INPUT type="text" class="span12" kind="text" id="QID1" name="ID"  fieldname="JL_PERSON_UID" value="" operation="="/>
	            <INPUT type="text" class="span12" kind="text" id="STA" name="STA"  fieldname="a.STATUS" value="" operation="="/>
	        </TD>
			<TD class="right-border bottom-border">
			</TD>
        </TR>
      </table>
      </form>
       <form method="post" id="queryForm2"  >
      <table class="B-table" width="100%">
      <!--可以再此处加入hidden域作为过滤条件 -->
      	<TR style="display:none;">
	        <TD class="right-border bottom-border">
	        	<INPUT type="text" class="span12" kind="text" id="QID2" name="ID"  fieldname="a.JL_PERSON_UID" value="" operation="="/>
	            <INPUT type="text" class="span12" kind="text" id="ZHENGSHU" name="ZHENGSHU"  fieldname="b.ZHENGSHU_NAME" value="" operation="="/> 
	        </TD>
			<TD class="right-border bottom-border">
			</TD>
        </TR>
      </table>
      </form>
    <%--<div style="height:5px;"></div>
	--%></div>
	
	<div class="B-small-from-table-autoConcise">
 	 <h4 class="title">人员信息
      	<span class="pull-right"> 
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>  
      		<button id="btnLock" class="btn" type="button">锁定</button>
      		<button id="btnUnlock" class="btn" type="button">解锁</button> 		
      		<!-- 
      		<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">保存</button>   		
      	 	-->
      	</span>
      </h4>	
      <form method="post" id="jlPersonLibraryForm">
		<%--<div class="tab-content"> --%>
				<!-- 静态信息tab页 -->
				<div class="tab-pane active" id="xmsc1" style="height:100%"> 
				
				<!--  提交前经过修改的人员信息的内容会以<i class='icon-warning-sign showXmxxkInfo' title='"+title+"'></i>提示,您也可以<span id="hideTs"><a href="javascript:void(0)" onclick="hideTs(true)">隐藏</a></span><span id="showTs"><a href="javascript:void(0)" onclick="hideTs(false)">显示</a></span> --> 
				<table class="B-table" width="100%" id="val1">
				    <input type="hidden" id="STATUS" fieldname="STATUS" name="STATUS"/>
			        <input type="hidden" id="PERSON_LIBRARY_UID" fieldname="PERSON_LIBRARY_UID" name="PERSON_LIBRARY_UID"/>
				  	<input type="hidden" id="JL_COMPANY_UID" fieldname="JL_COMPANY_UID" name = "JL_COMPANY_UID"/>
				  	<input type="hidden" id="DENGLU_CODE" fieldname="DENGLU_CODE" name = "DENGLU_CODE"/>
			        <input type="hidden" id="PERSON_LIBRARY_FILEUPLOAD" fieldname="PERSON_LIBRARY_FILEUPLOAD" name = "PERSON_LIBRARY_FILEUPLOAD"/>
			        <input type="hidden" id="JL_PERSON_UID" fieldname="JL_PERSON_UID" name ="JL_PERSON_UID"/>
			        <input type="hidden" id="ywid"/>
			       <tr>
						<th width="15%" class="right-border bottom-border text-right">所属企业</th>
			       	 	<td class="bottom-border right-border">
			         		<input class="span12" style="width:50%" id="COMPANY_NAME" type="text" placeholder="必填" check-type="required" check-type="maxlength" maxlength="10" fieldname="COMPANY_NAME" name = "COMPANY_NAME" readonly />
			         	</td>
			       </tr>
			       <tr>
						<th width="15%" class="right-border bottom-border text-right">姓名</th>
			       	 	<td class="bottom-border right-border">
			         		<input class="span12" style="width:50%" id="PERSON_NAME" type="text" placeholder="必填" check-type="required" check-type="maxlength" maxlength="10" fieldname="PERSON_NAME" name = "PERSON_NAME" onblur="isName(this)" /><font color="red">*</font><b><span id="nametext" style="color:red;font-size: 14px"></span></b>
			         	</td>
			        </tr>
			        <tr>
						<th width="15%" class="right-border bottom-border text-right">性别</th>
						<td  class="right-border bottom-border">
						    <select  id="SEX"  class="span12" style="width:50%"  name="SEX" fieldname="SEX"  operation="="   defaultMemo="全部">
								<option>男</option>
								<option>女</option>
							</select>
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right ">身份证号</th>
						<td  class="right-border bottom-border">
							<input id="SHENFENZHENG" class="span12" style="width:50%" placeholder="必填" check-type="required" check-type="maxlength" maxlength="18	" name="SHENFENZHENG" fieldname="SHENFENZHENG" type="text" onblur="isCardNo(this)"/><font color="red">*</font><b><span id="shenfentext" style="color:red;font-size: 14px"></span></b>
					    </td>
					</tr>	   
					<tr>
					    <th width="15%" class="right-border bottom-border text-right ">身份证扫描件</th>
						<td  class="right-border bottom-border">
						<!-- 
							<span class="btn btn-fileUpload" id="addFileSpan" onclick="selectSfzFile(this);" fjlb="1065" > 
								<i class="icon-plus"></i>
								<span>添加文件</span>
							</span>
							<span id="sfzError" style="display: none; color: red;"><b>请上传身份证    </b></span>
						 -->	
							<table role="presentation" id="sfz" class="table table-striped">
								<tbody fjlb="1065"  class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true">
								</tbody>
							</table>
							
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right ">电话号码</th>
						<td  class="right-border bottom-border">
							<input class="span12" style="width:50%" id="PHONE" type="text" placeholder="必填" check-type="required"  check-type="maxlength" maxlength="11" fieldname="PHONE" name = "PHONE" onblur="isPhone(this)" /><font color="red">*</font><b><span id="phonetext" style="color:red;font-size: 14px"></span></b>
		     		   </td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right ">EMAIL</th>
						<td  class="right-border bottom-border">
							<input id="EMAIL" class="span12" style="width:50%"  check-type="maxlength" maxlength="36" name="EMAIL" fieldname="EMAIL" type="text" onblur="isEmail(this)"/><b><span id="emailtext" style="color:red;font-size: 14px"></span></b>
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right ">职称</th>
						<td  class="right-border bottom-border">
						   <select id="ZHICHENG_UID"  class="span12" style="width:50%" name="ZHICHENG_UID" fieldname="ZHICHENG_UID" kind="dic" src="T#zhicheng: zhicheng_uid as c:zhicheng_name as t:1=1 order by zhicheng_uid" noNullSelect="true"></select>
						</td>	
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right ">职称证号</th>
						<td  class="right-border bottom-border">
							<input id="ZHICHENG_CODE" class="span12" style="width:50%"  check-type="maxlength" maxlength="36" name="ZHICHENG_CODE" fieldname="ZHICHENG_CODE" type="text" />
						</td>
					</tr>
					<tr>	
						<th width="15%" class="right-border bottom-border text-right ">职称扫描件</th>
						<td  class="right-border bottom-border">
						<!--  
							<span class="btn btn-fileUpload" id="addFileSpan" onclick="selectZczsFile(this);" fjlb="1066" > 
								<i class="icon-plus"></i>
								<span>添加文件</span>
							</span>
							<span id="zczsError" style="display: none; color: red;"><b>请上传职称证书     </b></span>
						-->	
							<table role="presentation" id="zczs" class="table table-striped">
								<tbody fjlb="1066"  class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true">
								</tbody>
							</table>
						
						</td>
					</tr>	
					<tr>
						<th width="15%" class="right-border bottom-border text-right ">从业资格证号</th>
						<td  class="right-border bottom-border">
									<select id="ZHUCE_TYPE" name="ZHUCE_TYPE" fieldname="ZHUCE_TYPE" style="width: 25%">
											<option value="">--请选择--</option>
											<option value="G">国注</option>
											<option value="S">省注</option>
											<option value="Y">监理员</option>
										</select>
							<input id="ZHUCE_CODE" class="span12" style="width:30%"  check-type="maxlength" maxlength="36" name="ZHUCE_CODE" fieldname="ZHUCE_CODE" type="text" />
						</td>
					</tr>
					<tr>	
						<th width="15%" class="right-border bottom-border text-right ">从业资格证扫描件</th>
						<td  class="right-border bottom-border">
						<!-- 
							<span class="btn btn-fileUpload" id="addFileSpan" onclick="selectCyzsFile(this);" fjlb="1067" > 
								<i class="icon-plus"></i>
								<span>添加文件</span>
						    </span>
							<span id="cyzsError" style="display: none; color: red;"><b>请上传从业资格证    </b></span>
						 -->	
							<table role="presentation" id="cyzs" class="table table-striped">
								<tbody fjlb="1067"  class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true">
								</tbody>
							</table>
							
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right ">发证日期</th>
						<td  class="right-border bottom-border">
							<input id="FAZHENG_DATE" class="span12" style="width:50%"  check-type="maxlength" maxlength="36" name="FAZHENG_DATE" fieldname="FAZHENG_DATE" type="text" />
						</td>
					</tr>	
					<tr>
						<th width="15%" class="right-border bottom-border text-right ">有效期</th>
						<td  class="right-border bottom-border">
							<input id="VALID_DATE" class="span12" style="width:50%"  check-type="maxlength" maxlength="36" name="VALID_DATE" fieldname="VALID_DATE" type="text" />
						</td>
					</tr>	
					<tr>
						<th width="15%" class="right-border bottom-border text-right ">注册专业1</th>
						<td  class="right-border bottom-border">
							<input id="ZHUANYE1" class="span12" style="width:50%"  check-type="maxlength" maxlength="36" name="ZHUANYE1" fieldname="ZHUANYE1" type="text" />
						</td>
					</tr>	
					<tr>
						<th width="15%" class="right-border bottom-border text-right ">注册专业2</th>
						<td  class="right-border bottom-border">
							<input id="ZHUANYE2" class="span12" style="width:50%"  check-type="maxlength" maxlength="36" name="ZHUANYE2" fieldname="ZHUANYE2" type="text" />
						</td>
					</tr>	
					<tr>
						<th width="15%" class="right-border bottom-border text-right ">资格证书 <br />
						<b><span id="zgtext" style="color:red;font-size: 14px"></span></b>
						</th>
						<td  class="right-border bottom-border">
							<table class="B-table" width="100%" id="zhigeList">
								<tr>
									<th style="width:19%">证书名称</th>
									<th style="width:18%">证书编号</th>
									<th style="width:12%">发证日期</th>
									<th style="width:12%">有效期</th>
									<th style="width:5%">操作</th>
									
								</tr>
								<tr id="cloneTR" style="display : none;"><!-- 用来复制 -->
									<td>
									<input id="JL_PERSON_ZHENGSHU_UID" type="hidden"  style="width:99%" class="span12" check-type="maxlength" maxlength="36" name="JL_PERSON_ZHENGSHU_UID" fieldname="JL_PERSON_ZHENGSHU_UID" type=text />
										<input id="ZHUCE_NAME"  class="span12"style="width:99%"  check-type="maxlength" maxlength="36" name="ZHUCE_NAME" fieldname="ZHUCE_NAME" type="text"/> 
									</td>
									<td><input id="ZHUCE_CODE1"  class="span12"style="width:99%"  check-type="maxlength" maxlength="36" name="ZHUCE_CODE1" fieldname="ZHUCE_CODE1" type="text"/> </td>
									<td><input id="FAZHENG_DATE1"  class="Wdate" type="text" onClick="WdatePicker()" style="width:85%" field  fieldformat="YYYY-MM-DD" name="FAZHENG_DATE1" fieldname="FAZHENG_DATE1"   /></td>
								    <td><input id="VALID_DATE1"  class="Wdate" type="text" onClick="WdatePicker()" style="width:85%" field  fieldformat="YYYY-MM-DD" name="VALID_DATE1" fieldname="VALID_DATE1"   /></td>
									 <!-- 
									<td>
									<input id="JL_PERSON_ZHENGSHU_UID" type="hidden"  style="width:99%" class="span12" check-type="maxlength" maxlength="36" name="JL_PERSON_ZHENGSHU_UID" fieldname="JL_PERSON_ZHENGSHU_UID" type=text />
									<select id="ZHENGSHU_NAME"  class="span12" style="width:99%" name="ZHENGSHU_NAME" fieldname="ZHENGSHU_NAME" kind="dic" src="T#sg_zhengshu: sg_zhengshu_uid as c:zhengshu_name as t:enabled=1"  defaultMemo="请选择" onchange="doNotShow(this)"></select></td>
									<td><select id="ZHUANYE_NAME" class="span12" style="width:99%" name="ZHUANYE_NAME"  fieldname="ZHUANYE_NAME"  kind="dic" src="T#sg_zizhi: sg_zizhi_uid as c:zhuanye_name as t:1=1 order by SERIAL_NO"  defaultMemo="请选择"></select></td>
									<td><input id="ZHENGSHU_CODE" class="span12" style="width:99%" placeholder="建造师证请填写注册编号" check-type="maxlength" maxlength="36" name="ZHENGSHU_CODE" fieldname="ZHENGSHU_CODE" type="text" onchange="codeNotShow(this)"/></td>
									<td><input id="ZSBEGIN_DATE"  class="Wdate" type="text" onClick="WdatePicker()" style="width:85%" field  fieldformat="YYYY-MM-DD" name="BEGIN_DATE" fieldname="ZSBEGIN_DATE" /></td>
								    <td><input id="ZSEND_DATE"  class="Wdate" type="text" onClick="WdatePicker()" style="width:85%" field  fieldformat="YYYY-MM-DD" name="END_DATE" fieldname="ZSEND_DATE"  /></td>&nbsp;
								   
								    <td>
								       <input type="hidden" id="JL_PERSON_ZHENGSHU_FILEUPLOAD" fieldname="JL_PERSON_ZHENGSHU_FILEUPLOAD" name = "JL_PERSON_ZHENGSHU_FILEUPLOAD"/>
						               	 <div>
								              <span class="btn btn-fileUpload" id="addFileSpan" onclick="doSelectFile(this,'child');" ywid="JL_PERSON_ZHENGSHU_UID" target_type="2" file_type="11" business_type="JL_PERSON_ZHENGSHU">
									            <i class="icon-plus"></i>
									            <span>添加文件</span>
								              </span>
								                <table role="presentation" class="table table-striped">
									               <tbody fjlb="0701" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>
								                </table>
							            </div>
								    </td>
								     -->
								    <td><img onclick="removeZhige(this)" style="cursor: pointer;" title="删除" src="/wndjsbg/img/sg/icon-removeZz.jpg">
								    <img onclick="addZhige(this)" id="bt" style="cursor: pointer;" title="增加" src="/wndjsbg/img/sg/icon-addZz.jpg"></td>
								</tr>
								<tr>
									<td>
									<input id="JL_PERSON_ZHENGSHU_UID" type="hidden"  style="width:99%" class="span12" check-type="maxlength" maxlength="36" name="JL_PERSON_ZHENGSHU_UID" fieldname="JL_PERSON_ZHENGSHU_UID" type=text />
										<input id="ZHUCE_NAME"  class="span12"style="width:99%"  check-type="maxlength" maxlength="36" name="ZHUCE_NAME" fieldname="ZHUCE_NAME" type="text"/> 
									</td>
									<td><input id="ZHUCE_CODE1"  class="span12"style="width:99%"  check-type="maxlength" maxlength="36" name="ZHUCE_CODE1" fieldname="ZHUCE_CODE1" type="text"/> </td>
									<td><input id="FAZHENG_DATE1"  class="Wdate" type="text" onClick="WdatePicker()" style="width:85%" field  fieldformat="YYYY-MM-DD" name="FAZHENG_DATE1" fieldname="FAZHENG_DATE1"   /></td>
								    <td><input id="VALID_DATE1"  class="Wdate" type="text" onClick="WdatePicker()" style="width:85%" field  fieldformat="YYYY-MM-DD" name="VALID_DATE1" fieldname="VALID_DATE1"   /></td>
								    <!--  
								    <td>
								       <input type="hidden" id="SG_PERSON_ZHENGSHU_FILEUPLOAD" fieldname="SG_PERSON_ZHENGSHU_FILEUPLOAD" name = "SG_PERSON_ZHENGSHU_FILEUPLOAD"/>
						               	 <div>
								              <span class="btn btn-fileUpload" id="addFileSpan" onclick="doSelectFile(this,'child');" ywid="SG_PERSON_ZHENGSHU_UID" target_type="2" file_type="11" business_type="SG_PERSON_ZHENGSHU">
									            <i class="icon-plus"></i>
									            <span>添加文件</span>
								              </span>
								                <table role="presentation" class="table table-striped">
									               <tbody fjlb="0701" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>
								                </table>
							            </div>
								    </td>
								    -->
								    <td><img onclick="removeZhige(this)" style="cursor: pointer;" title="删除" src="/wndjsbg/img/sg/icon-removeZz.jpg">
								    <img onclick="addZhige(this)" id="bt" style="cursor: pointer;" title="增加" src="/wndjsbg/img/sg/icon-addZz.jpg"></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right ">合同起止日期</th>
						<td  class="right-border bottom-border">
						   <input id="HTBEGIN_DATE"  class="Wdate" type="text" onClick="WdatePicker()" style="width:14%;" fieldtype="date" onblur="beDate(this)" fieldformat="YYYY-MM-DD" name="BEGIN_DATE" fieldname="BEGIN_DATE"   />
					        —
						   <input id="HTEND_DATE"  class="Wdate" type="text" onClick="WdatePicker()" style="width:14%;" fieldtype="date" onblur="enDate(this)" fieldformat="YYYY-MM-DD" name="END_DATE" fieldname="END_DATE"   /><font color="red">*</font><b><span id="datetext" style="color:red;font-size: 14px"></span></b>
						</td>
					</tr> 
					<tr>  
					   <th width="15%" class="right-border bottom-border text-right ">劳动合同扫描件</th>
					   <td>
					   <!-- 
							<span class="btn btn-fileUpload" id="addFileSpan" onclick="selectCyzsFile(this);" fjlb="1067" > 
								<i class="icon-plus"></i>
								<span>添加文件</span>
							</span>
							<span id="cyzsError" style="display: none; color: red;"><b>请上传从业资格证    </b></span>
						 -->	
							<table role="presentation" id="ldht" class="table table-striped">
								<tbody fjlb="1068"  class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true">
								</tbody>
							</table>
						</td>
					</tr>
					<tr>
					    <th width="15%" class="right-border bottom-border text-right ">社保证明扫描件</th>
						<td>  
						<!-- 
						    <span class="btn btn-fileUpload" id="addFileSpan" onclick="selectSbzmFile(this);" fjlb="1069" > 
								<i class="icon-plus"></i>
								<span>添加文件</span>
							</span>
							<span id="sbzmError" style="display: none; color: red;"><b>请上传社保证明    </b></span>
						 -->	
							<table role="presentation" id="sbzm" class="table table-striped">
								<tbody fjlb="1069"  class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true">
								</tbody>
							</table>
							
						</td>
					</tr>
					<tr>
					    <th width="15%" class="right-border bottom-border text-right ">监理人员学习文件阅知单附件</th>
						<td>  
						<!-- 
							<span class="btn btn-fileUpload" id="addFileSpan" onclick="selectXxwjFile(this);" fjlb="1070" > 
								<i class="icon-plus"></i>
								<span>添加文件</span>
							</span>
							<span id="xxwjError" style="display: none; color: red;"><b>请上传监理人员学习文件阅知单    </b></span>
						 -->	
							<table role="presentation" id="xxwj" class="table table-striped">
								<tbody fjlb="1070"  class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true">
								</tbody>
							</table>
							
						</td>
					</tr>
					<tr>
				        <th width="15%" class="right-border bottom-border text-right ">备注或个人简介</th>
				        <td  class="bottom-border right-border" >
				        	<textarea class="span12" rows="3" id="DESCRIPTION" check-type="maxlength" maxlength="4000" fieldname="DESCRIPTION" name="DESCRIPTION"></textarea>
				        </td>
			        </tr>
				</table>

				
					
			    </div>
	    </form>
    </div>
   </div>
  </div><%--操作附件上传的方法 --%>
 <jsp:include page="/jsp/file_upload/fileupload_config_all.jsp" flush="true"/> 
 <jsp:include page="/jsp/swfupload_jsp/swfupload.jsp" flush="true" />	
  </div>
  <div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id = "queryXML">
         <input type="hidden" name="txtXML" id = "txtXML">
         <input type="hidden" name="txtFilter"  order="desc" fieldname = "a.TIJIAO_DATE" id = "txtFilter">
         <input type="hidden" name="resultXML" id = "resultXML">
         <input type="hidden" name="ywid" id="ywid">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
		
 	</FORM>
 </div>
</body>
<script>
//身份证上传
function selectSfzFile(obj){
	$("#sfzError").hide();
	var ywid=$("#ywid").val();	
	
	setFileData(ywid,"","","999003","0","1065");
	$("#_fileupload_fjlb").val($(obj).attr("fjlb"));
	if(isIE()){
		initdataswf(ywid,"","","999003","0","1065","1065");
		document.getElementById("plfileuploadswf").click();
	}else{
		document.getElementById("fileupload_btn").click();
	}	
}
//职称证书上传
function selectZczsFile(obj){
	$("#zczsError").hide();
	var ywid=$("#ywid").val();	
	
	setFileData(ywid,"","","999003","0","1066");
	$("#_fileupload_fjlb").val($(obj).attr("fjlb"));
	if(isIE()){
		initdataswf(ywid,"","","999003","0","1066","1066");
		document.getElementById("plfileuploadswf").click();
	}else{
		document.getElementById("fileupload_btn").click();
	}	
}
//从业证书上传
function selectCyzsFile(obj){
	$("#cyzsError").hide();
	var ywid=$("#ywid").val();	
	
	setFileData(ywid,"","","999003","0","1067");
	$("#_fileupload_fjlb").val($(obj).attr("fjlb"));
	if(isIE()){
		initdataswf(ywid,"","","999003","0","1067","1067");
		document.getElementById("plfileuploadswf").click();
	}else{
		document.getElementById("fileupload_btn").click();
	}	
}
//劳动合同上传
function selectLdhtFile(obj){
	$("#ldhtError").hide();
	var ywid=$("#ywid").val();	
	
	setFileData(ywid,"","","999003","0","1068");
	$("#_fileupload_fjlb").val($(obj).attr("fjlb"));
	if(isIE()){
		initdataswf(ywid,"","","999003","0","1068","1068");
		document.getElementById("plfileuploadswf").click();
	}else{
		document.getElementById("fileupload_btn").click();
	}	
}
//社保证明上传
function selectSbzmFile(obj){
	$("#sbzmError").hide();
	var ywid=$("#ywid").val();	
	
	setFileData(ywid,"","","999003","0","1069");
	$("#_fileupload_fjlb").val($(obj).attr("fjlb"));
	if(isIE()){
		initdataswf(ywid,"","","999003","0","1069","1069");
		document.getElementById("plfileuploadswf").click();
	}else{
		document.getElementById("fileupload_btn").click();
	}	
}
//学习文件上传
function selectXxwjFile(obj){
	$("#xxwjError").hide();
	var ywid=$("#ywid").val();	
	
	setFileData(ywid,"","","999003","0","1070");
	$("#_fileupload_fjlb").val($(obj).attr("fjlb"));
	if(isIE()){
		initdataswf(ywid,"","","999003","0","1070","1070");
		document.getElementById("plfileuploadswf").click();
	}else{
		document.getElementById("fileupload_btn").click();
	}	
}

</script>
</html>