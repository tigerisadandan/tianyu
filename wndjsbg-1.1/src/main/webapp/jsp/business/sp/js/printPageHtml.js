$.fn.setHtmlValue = function(obj){
	var formobj = this;
	$('td',formobj).each(function(){
		if($(this).attr("fieldname")!=null){
			var paramName = $(this).attr("fieldname");
			if($(this).attr("fieldtype")=="dic"){
				paramName = $(this).attr("fieldname")+"_SV";
			}
			$(this).html(obj[paramName]);
		}
	})
};
//查看是否已存在业务流转实例
function getYwlz(){
	var ywlz = $(window.opener.document).find("#YWLZ_UID");
	if(ywlz==""){
		
		ywlz = doYwlzsl();
	}
	return ywlz;
}
//新增流转信息
function doYwlzsl(){
	return window.opener.getYwlz();
}
//获取已选项目信息
function getXmxx(){
	var obj ;
	$.ajax({
		url : "/wndjsjs/project/projectsController/getProject",
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			if(response.msg!=""&&response.msg!="0"){
				obj = defaultJson.dealResultJson(response.msg)
			}else{
				obj = null;
			}
		}
	});
	return obj;
}
//获取项目信息
function getCompany(){
	var obj ;
	$.ajax({
		url : "/wndjsjs/project/jsCompanyController/queryCurrent",
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			obj = convertJson.string2json1(response.msg);
		}
	});
	return obj;
}



//新增--
function getjscompany(){ //根据 jscompanyForm 查询 企业信息
	var obj ;
	var data = combineQuery.getQueryCombineData(jscompanyForm,frmPost);
	var data1 = {
		msg : data
	};
	$.ajax({
		url : "/wndjsbg/project/jsCompanyController/query",
		data : data1,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			if(response.msg!=""&&response.msg!="0"){
				obj = defaultJson.dealResultJson(response.msg);
			}else{
				obj = null;
			}
			
		}
	});
	return obj;
}
function getjsproject(){//根据 jsprojectForm 查询 项目
	var obj ;
	var data = combineQuery.getQueryCombineData(jsprojectForm,frmPost);
	var data1 = {
		msg : data
	};
	$.ajax({
		url : "/wndjsbg/project/jsProjectController/query",
		data : data1,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			if(response.msg!=""&&response.msg!="0"){
				obj = defaultJson.dealResultJson(response.msg);
			}else{
				obj = null;
			}
		}
	});
	return obj;
}
function getprojects(){//根据 projectsForm 查询 项目分期
	var obj ;
	var data = combineQuery.getQueryCombineData(projectsForm,frmPost);
	var data1 = {
		msg : data
	};
	$.ajax({
		url : "/wndjsbg/project/jsProjectController/query",
		data : data1,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			if(response.msg!=""&&response.msg!="0"){
				obj = defaultJson.dealResultJson(response.msg);
			}else{
				obj = null;
			}
			
		}
	});
	return obj;
}
