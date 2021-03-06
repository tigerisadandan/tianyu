<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>勘察设计企业信息库-审核</title>
<%
	String type=request.getParameter("type");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/wxgc/yxKcEnterpriseController";
var type ="<%=type%>";
//页面初始化
$(function() {
	init();
	
	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
		if($("#yxKcEnterpriseForm").validationButton()){
			xConfirm("信息确认","确认提交审核结果？");
			$('#ConfirmYesButton').attr('click','');
			$('#ConfirmYesButton').one("click",function(){
			    //生成json串
			    var data = Form2Json.formToJSON(yxKcEnterpriseForm);
			  //组成保存json串格式
			    var data1 = defaultJson.packSaveJson(data);
			  //调用ajax插入
			    if($("#KC_ENTERPRISE_UID").val()!= ""&&$("#KC_ENTERPRISE_UID").val()!= null){
	    			defaultJson.doInsertJson(controllername + "?update", data1);
	    		}
			    var a=$(window).manhuaDialog.getParentObj();
			    a.init();
				$(window).manhuaDialog.close();
			});	
		}else{
			requireFormMsg();
		  	return;
		}
	});


	$("#btnClose").bind("click", function(){
		var a=$(this).manhuaDialog.close();
	});

    <%
    if(type.equals("detail")){
	%>
	//置所有input 为disabled
	$(":input").each(function(i){
	   $(this).attr("disabled", "true");
	 });
	$("#shjltxdivid").hide();
	$("#btnClose").removeAttr('disabled');
	<%
		}
	%>
	
});
//页面默认参数
function init(){
	if(type == "insert"){
		
	}else if(type == "update" || type == "detail"){
		//生成json串
		var parentmain = $(window).manhuaDialog.getParentObj();
		var rowValue = parentmain.$("#resultXML").val();
		var tempJson = convertJson.string2json1(rowValue);
		$("#QKC_ENTERPRISE_UID").val(tempJson.KC_ENTERPRISE_UID);
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
				$("#yxKcEnterpriseForm").setFormValues(resultobj);	
				getKcGcalxx(resultobj.KC_ENTERPRISE_UID);//加载工程案例信息
				getOldValues(resultobj);	
				return true;
			}
		});
		
		//列出审批意见信息
		var queryconditionXX = "{\"logic\":\"and\",\"fieldname\":\"KC_ENTERPRISE_UID\",\"operation\":\"=\",\"value\":\""+tempJson.KC_ENTERPRISE_UID+"\",\"fieldtype\":'',\"fieldformat\":''}";
		var dataXX = "{querycondition: {conditions: [" +queryconditionXX+"]},pages:{recordsperpage:200, currentpagenum:1, totalpage:0, countrows:0},orders:[{\"fieldname\":\"SHRQ\",\"order\":\"asc\"}]}";
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"?queryspyj", dataXX, spyjList);
		
		//加载附件
		queryFileData(tempJson.KC_ENTERPRISE_UID, "", "", "777000");
	}
}


$.format = function (source, params) {
    if (arguments.length == 1)
        return function () {
            var args = $.makeArray(arguments);
            args.unshift(source);
            return $.format.apply(this, args);
        };
    if (arguments.length > 2 && params.constructor != Array) {
        params = $.makeArray(arguments).slice(1);
    }
    if (params.constructor != Array) {
        params = [params];
    }
    $.each(params, function (i, n) {
        source = source.replace(new RegExp("\\{" + i + "\\}", "g"), n);
    });
    return source;
};


var attachIndex = 1;
var attachTpl = $.format($("#attachTr").val());
function addAttachLine() {
	attachIndex++;
	var athtml=attachTpl(attachIndex);
	$('#gcalxxTable').append(athtml);
	
}

function getKcGcalxx(kcuid){
	var queryconditionXX = "{\"logic\":\"and\",\"fieldname\":\"KC_ENTERPRISE_UID\",\"operation\":\"=\",\"value\":\""+kcuid+"\",\"fieldtype\":'',\"fieldformat\":''}";
	var dataXX = "{querycondition: {conditions: [" +queryconditionXX+"]},pages:{recordsperpage:200, currentpagenum:1, totalpage:0, countrows:0},orders:[{\"fieldname\":\"ALGCXX_UID\",\"order\":\"asc\"}]}";
	var data = {
			msg : dataXX
		};
	$.ajax({
		url :"${pageContext.request.contextPath }/qyxx/yxKcEnterpriseGcalxxController?query",
		data : data,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var res = dealSpecialCharactor(response.msg);
			var resultmsgobj = convertJson.string2json1(res);
			if(resultmsgobj!=null&&resultmsgobj!=''){
				var datslist=resultmsgobj.response.data;
				//subresultmsgobj = resultmsgobj.response.data[0];
				$(datslist).each(function(){
					addAttachLine();
					$("#ALGCMC"+attachIndex).val(this.ALGCMC);
					$("#ALGCZBRQ"+attachIndex).val(this.ALGCZBRQ);
					$("#FJTBODYID"+attachIndex).attr("glid1",this.ALGCXX_UID);
				});
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
     <form method="post" id="yxKcEnterpriseForm"  >
      <table class="B-table" width="100%"  id="val1">
     	 	<input type="hidden" id="KC_ENTERPRISE_UID" fieldname="KC_ENTERPRISE_UID" name = "KC_ENTERPRISE_UID"/>
        	<tr>
				<th width="8%" class="right-border bottom-border text-right">企业全称</th>
	       	 	<td class="bottom-border right-border" colspan="3">
	         		<input  id="COMPANY_NAME" check-type="required"  type="text" fieldname="COMPANY_NAME" name = "COMPANY_NAME" />
	       	 	</td> 
           </tr>
           <tr>
				<th width="8%" class="right-border bottom-border text-right">组织机构代码</th>
	       	 	<td class="bottom-border right-border" width="15%">
	         		<input  id="COMPANY_CODE"  type="text" fieldname="COMPANY_CODE" name = "COMPANY_CODE" />
	       	 	</td> 
	       	 	<th width="8%" class="right-border bottom-border text-right">附件</th>
	       	 	<td class="bottom-border right-border" width="15%">
	       	 		<table role="presentation" id="zzjg" class="table table-striped">
						<tbody fjlb="7771" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true"></tbody>
					</table>
	       	 	</td>        	
           </tr>
           <tr>
				<th width="8%" class="right-border bottom-border text-right">营业执照号</th>
	       	 	<td class="bottom-border right-border" width="15%">
	         		<input  id="ZHIZHAO"   type="text" fieldname="ZHIZHAO" name = "ZHIZHAO" />
	       	 	</td> 
	       	 	<th width="8%" class="right-border bottom-border text-right">税务登记号</th>
	       		<td class="bottom-border right-border" width="15%">
	         		<input  id="SHUIWU" type="text" fieldname="SHUIWU" name = "SHUIWU" />
	         	</td>      	
           </tr>
           
            <tr>
				<th width="8%" class="right-border bottom-border text-right">营业有效期</th>
	       	 	<td class="bottom-border right-border" width="15%">
	         		<input  id="ZHIZHAO_VALID"   type="text" fieldname="ZHIZHAO_VALID" name = "ZHIZHAO_VALID" class='Wdate' onClick='WdatePicker()'/>
	       	 	</td> 
	       	 	<th width="8%" class="right-border bottom-border text-right">附件</th>
	       	 	<td class="bottom-border right-border" width="15%">
	       	 		<table role="presentation" id="yyzz" class="table table-striped">
						<tbody fjlb="7772"  class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true">
						</tbody>
					</table>
	       	 	</td>        	
           </tr>
         <tr>
			<th width="8%" class="right-border bottom-border text-right">是否外地企业</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="WAIDI_Y" type="radio" kind="dic" src="WAIDI_Y"  name="WAIDI_Y" fieldname="WAIDI_Y" defaultValue="N">
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">企业类型</th>
       		<td class="bottom-border right-border" width="15%">
         		<select  id="COMPANY_TYPE"   name="COMPANY_TYPE" fieldname="COMPANY_TYPE"  kind="dic" src="COMPANY_TYPE" defaultMemo="--请选择--">
				</select>
         	</td>
         </tr>
         <tr>
			<th width="8%" class="right-border bottom-border text-right">开户银行</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="BANK"   type="text" fieldname="BANK" name = "BANK" />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">开户行帐号</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="BANK_ACCOUNT" type="text" fieldname="BANK_ACCOUNT" name = "BANK_ACCOUNT" />
         	</td>
         </tr>
         <tr>
			<th width="8%" class="right-border bottom-border text-right">注册资金</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="ZHUCE_ZIJIN"   type="text" fieldname="ZHUCE_ZIJIN" name = "ZHUCE_ZIJIN" />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right"></th>
       		<td class="bottom-border right-border" width="15%">
         	</td>
         </tr>
          <tr>
			<th width="8%" class="right-border bottom-border text-right">公司地址</th>
       		<td class="bottom-border right-border" colspan="3">
         		<input  id="ADDRESS"  type="text" fieldname="ADDRESS" name = "ADDRESS" />
         	</td>
         	
         </tr>
          <tr>
			<th width="8%" class="right-border bottom-border text-right">公司电话</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="PHONE"  type="text" fieldname="PHONE" name = "PHONE" />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">公司传真</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="FAX" type="text" fieldname="FAX" name = "FAX" />
         	</td>
         </tr>
          <tr>
			<th width="8%" class="right-border bottom-border text-right">邮政编码</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="POSTCODE"  type="text" fieldname="POSTCODE" name = "POSTCODE" />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">公司主页</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="URL" type="text" fieldname="URL" name = "URL" />
         	</td>
         </tr>
         <tr>
			<th width="8%" class="right-border bottom-border text-right">法人代表</th>
       		<td class="bottom-border right-border" colspan="3">
         		<input  id="FAREN"  type="text" fieldname="FAREN" name = "FAREN" />
         	</td>
         	
         </tr>
          <tr>
			<th width="8%" class="right-border bottom-border text-right">法人职称</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="FAREN_ZHICHENG"  type="text" fieldname="FAREN_ZHICHENG" name = "FAREN_ZHICHENG" />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">手机号码</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="FAREN_MOBILE" type="text" fieldname="FAREN_MOBILE" name = "FAREN_MOBILE" />
         	</td>
         </tr>
         
         
          <tr>
			<th width="8%" class="right-border bottom-border text-right">业务负责人</th>
       		<td class="bottom-border right-border" colspan="3">
         		<input  id="YWFZR"  type="text" fieldname="YWFZR" name = "YWFZR" />
         	</td>
         	
         </tr>
          <tr>
			<th width="8%" class="right-border bottom-border text-right">联系电话</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="YWFZR_LXDH"  type="text" fieldname="YWFZR_LXDH" name = "YWFZR_LXDH" />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">手机号码</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="YWFZR_MOBILE" type="text" fieldname="YWFZR_MOBILE" name = "YWFZR_MOBILE" />
         	</td>
         </tr>
         <tr>
			<th width="8%" class="right-border bottom-border text-right">信息管理员</th>
       		<td class="bottom-border right-border" colspan="3">
         		<input  id="XXGLY"  type="text" fieldname="XXGLY" name = "XXGLY" />
         	</td>
         	
         </tr>
          <tr>
			<th width="8%" class="right-border bottom-border text-right">联系电话</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="XXGLY_LXDH"  type="text" fieldname="XXGLY_LXDH" name = "XXGLY_LXDH" />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">手机号码</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="XXGLY_MOBILE" type="text" fieldname="XXGLY_MOBILE" name = "XXGLY_MOBILE" />
         	</td>
         </tr>
          <tr>
			<th width="8%" class="right-border bottom-border text-right">工程勘察综合资质</th>
       		<td class="bottom-border right-border" width="15%">
         		<select  id="GCKCZZ_ZHZZ"  name="GCKCZZ_ZHZZ" fieldname="GCKCZZ_ZHZZ"  kind="dic" src="GCKCZZ_ZHZZ" defaultMemo="--请选择--">
				</select>
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">附件</th>
       		<td class="bottom-border right-border" width="15%">
         		<table role="presentation" class="table table-striped">
				<tbody fjlb="7773" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true"></tbody>
				</table>
         	</td>
         </tr>
         <tr>
			<th width="8%" class="right-border bottom-border text-right">工程勘察专业资质</th>
       		<td class="bottom-border right-border" width="15%">
         		<select  id="GCKCZZ_ZYZZ"   name="GCKCZZ_ZYZZ" fieldname="GCKCZZ_ZYZZ"  kind="dic" src="GCKCZZ_ZYZZ" defaultMemo="--请选择--">
				</select>
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">附件</th>
       		<td class="bottom-border right-border" width="15%">
         		<table role="presentation" class="table table-striped">
					<tbody fjlb="7774" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true"></tbody>
				</table>
         	</td>
         </tr>
         
          <tr>
			<th width="8%" class="right-border bottom-border text-right">工程勘察劳务资质</th>
       		<td class="bottom-border right-border" width="15%">
         		<select  id="GCKCZZ_LWZZ"   name="GCKCZZ_LWZZ" fieldname="GCKCZZ_LWZZ"  kind="dic" src="GCKCZZ_LWZZ" defaultMemo="--请选择--">
				</select>
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">附件</th>
       		<td class="bottom-border right-border" width="15%">
         		<table role="presentation" class="table table-striped">
					<tbody fjlb="7775" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true"></tbody>
				</table>
         	</td>
         </tr>
         
         
           <tr>
			<th width="8%" class="right-border bottom-border text-right">工程设计综合资质</th>
       		<td class="bottom-border right-border" width="15%">
         		<select  id="GCSJZZ_ZHZZ"  name="GCSJZZ_ZHZZ" fieldname="GCSJZZ_ZHZZ"  kind="dic" src="GCSJZZ_ZHZZ" defaultMemo="--请选择--">
				</select>
										
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">附件</th>
       		<td class="bottom-border right-border" width="15%">
         		<table role="presentation" class="table table-striped">
					<tbody fjlb="7776" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true"></tbody>
				</table>
         	</td>
         </tr>
                  
           <tr>
			<th width="8%" class="right-border bottom-border text-right">工程设计行业资质</th>
       		<td class="bottom-border right-border" width="15%">
         		<select  id="GCSJZZ_HYZZ"  name="GCSJZZ_HYZZ" fieldname="GCSJZZ_HYZZ"  kind="dic" src="GCSJZZ_HYZZ" defaultMemo="--请选择--">
				</select>				
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">附件</th>
       		<td class="bottom-border right-border" width="15%">
         		<table role="presentation" class="table table-striped">
					<tbody fjlb="7777" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true"></tbody>
				</table>
         	</td>
         </tr>
                          
           <tr>
			<th width="8%" class="right-border bottom-border text-right">工程设计专业资质</th>
       		<td class="bottom-border right-border" width="15%">
         		<select  id="GCSJZZ_ZYZZ"  style="width:30%"  name="GCSJZZ_ZYZZ" fieldname="GCSJZZ_ZYZZ"  kind="dic" src="GCSJZZ_ZYZZ" defaultMemo="--请选择--">
				</select>				
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">附件</th>
       		<td class="bottom-border right-border" width="15%">
         		<table role="presentation" class="table table-striped">
					<tbody fjlb="7778" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true"></tbody>
				</table>
         	</td>
         </tr>
          <tr>
			<th width="8%" class="right-border bottom-border text-right">工程设计专项资质</th>
       		<td class="bottom-border right-border" width="15%">
         		<select  id="GCSJZZ_ZXZZ"  style="width:30%"  name="GCSJZZ_ZXZZ" fieldname="GCSJZZ_ZXZZ"  kind="dic" src="GCSJZZ_ZXZZ" defaultMemo="--请选择--">
				</select>				
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">附件</th>
       		<td class="bottom-border right-border" width="15%">
         		<table role="presentation" class="table table-striped">
					<tbody fjlb="7779" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true"></tbody>
				</table>
         	</td>
         </tr>
         
          <tr>
			<th width="8%" class="right-border bottom-border text-right">工程案例</th>
       		<td class="bottom-border right-border" colspan="3">
         		<table  width="100%" class="table table-bordered">
			    	<thead>
						<tr>
							<th style="width:45%" >&nbsp;项目名称&nbsp;</th>
							<th style="width:15%" >&nbsp;中标通知日期&nbsp;</th>
							<th style="width:40%" >&nbsp;附件&nbsp;</th>
						</tr>
					</thead>
					 <tbody  id="gcalxxTable">
						
					 </tbody>	
					</table>
			    	<textarea id="attachTr" style="display:none">
						<tr id="gcalxxTr{0}" >
							<input type="hidden" value="{0}" id="GCALXH{0}" name="GCALXH" fieldname="GCALXH" />
							<input type="hidden" id="ALGCXX_UID{0}" name="ALGCXX_UID" fieldname="ALGCXX_UID" />
							<td class="text-center">
								<input id="ALGCMC{0}" class="span12" style="width:95%" name="ALGCMC" fieldname="ALGCMC" type="text" />
							</td>
							<td class="text-center">
								<input class="date-picker" id="ALGCZBRQ{0}" style="width:80%" name="ALGCZBRQ" fieldname="ALGCZBRQ" type="text" data-date-format="yyyy-mm-dd" 	readonly="readonly" />
								<i class="ace-icon fa fa-calendar"></i>
							</td>
							<td >
								<table role="presentation" id="fjtable{0}" class="table table-striped">
									<tbody id="FJTBODYID{0}" glid1="{0}" fjlb="7770"  class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>
								</table>
							</td>
						</tr>
					</textarea>
					<script type="text/javascript" >
						var attachTpl = $.format($("#attachTr").val());
					</script>
         	</td>
         </tr>
      </table>
			
		    <div style="height: 5px;"></div>
      		
			<div id="shjltxdivid">
				<div class="modal-header" style="background:#0866c6;">
				<h3 style="color:white;">勘察设计企业资质审核
				<span class="pull-right">
					<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">审核</button>
	  			</span>
				</h3>
				</div>
      			<table id="shenheTable" class="B-table" width="100%" >
			        <tr>
						<th width="15%" class="right-border bottom-border text-right">是否通过</th>
			       	 	<td class="bottom-border right-border">
			       	 		<input id="SHJG" type="radio" check-type="required" fieldname="SHJG" name = "SHJG" defaultValue="1" kind="dic" src="SHYJ_SY" />	
			       	 	</td>
			        </tr>
			       	<tr>
						<th width="15%" class="right-border bottom-border text-right">审核意见</th>
			       	 	<td class="bottom-border right-border">
			       	 		<textarea class="span12" rows="2" id="SHYJ" check-type="required" maxlength="4000"  fieldname="SHYJ" name="SHYJ"></textarea>
			       	 	</td>
			        </tr>
				</table>
			</div>
      
      </form>
      
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
    </div>
   </div>
  </div>
     <form method="post" id="queryForm"  >
    	  <input type="hidden" id="QKC_ENTERPRISE_UID" fieldname="KC_ENTERPRISE_UID" name = "KC_ENTERPRISE_UID" operation="="/>
     </form> 
  
  <div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id = "queryXML">
         <input type="hidden" name="txtXML" id = "txtXML">
         <input type="hidden" name="txtFilter"  order="desc" fieldname = "KC_ENTERPRISE_UID" id = "txtFilter">
         <input type="hidden" name="resultXML" id = "resultXML">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
		
 	</FORM>
 </div>
 <jsp:include page="/jsp/file_upload/fileuploadold_config_js.jsp" flush="true"/> 
</body>

</html>