<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>绿化企业信息库-维护</title>
<%
	String type=request.getParameter("type");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/wxgc/yxLhEnterpriseController";
var type ="<%=type%>";
//页面初始化
$(function() {
	init();
	
	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
		if($("#yxlhEnterpriseForm").validationButton()){
		    //生成json串
		    var data = Form2Json.formToJSON(yxlhEnterpriseForm);
		  //组成保存json串格式
		    var data1 = defaultJson.packSaveJson(data);
		  //调用ajax插入
		    if($("#LH_ENTERPRISE_UID").val() != "" && $("#LH_ENTERPRISE_UID").val() != null){
    			defaultJson.doInsertJson(controllername + "?update", data1);
    		}
		    var a=$(window).manhuaDialog.getParentObj();
		    a.init();
			$(window).manhuaDialog.close();
		}else{
			requireFormMsg();
		  	return;
		}
	});

	//按钮绑定事件(保存)
	$("#btnSaveXz").click(function() {
		if($("#enterpriseForm").validationButton()){
			 //生成json串
		    var data = Form2Json.formToJSON(enterpriseForm);
		  //组成保存json串格式
		    var data1 = defaultJson.packSaveJson(data);
		  //调用ajax插入
		    if($("#YXCBS_UID").val() != "" && $("#YXCBS_UID").val() != null){
    			defaultJson.doInsertJson("${pageContext.request.contextPath }/wxgc/yxYxcbsController?updateXzYxcbs", data1);
    		}
		    var a=$(window).manhuaDialog.getParentObj();
		    a.init();
			$(window).manhuaDialog.close();
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
	initYxcbsFun();
});
//页面默认参数
function init(){
if(type == "insert"){
		
	}else if(type == "update" || type == "detail"){
		//生成json串
		var parentmain = $(window).manhuaDialog.getParentObj();
		var rowValue = parentmain.$("#resultXML").val();
		var tempJson = convertJson.string2json1(rowValue);
		$("#QLH_ENTERPRISE_UID").val(tempJson.LH_ENTERPRISE_UID);
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
				$("#yxlhEnterpriseForm").setFormValues(resultobj);

				$("#QYXCBS_UID").val(resultobj.YXCBS_UID);//预选承包商
				getLhGcalxx(tempJson.LH_ENTERPRISE_UID);//加载工程案例信息
				return true;
			}
		});

		//列出审批意见信息
		var queryconditionXX = "{\"logic\":\"and\",\"fieldname\":\"LH_ENTERPRISE_UID\",\"operation\":\"=\",\"value\":\""+tempJson.LH_ENTERPRISE_UID+"\",\"fieldtype\":'',\"fieldformat\":''}";
		var dataXX = "{querycondition: {conditions: [" +queryconditionXX+"]},pages:{recordsperpage:200, currentpagenum:1, totalpage:0, countrows:0},orders:[{\"fieldname\":\"SHRQ\",\"order\":\"asc\"}]}";
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"?queryspyj", dataXX, spyjList);
		//加载附件
		queryFileData(tempJson.LH_ENTERPRISE_UID, "", "", "777001");
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

function getLhGcalxx(lhuid){
	var queryconditionXX = "{\"logic\":\"and\",\"fieldname\":\"LH_ENTERPRISE_UID\",\"operation\":\"=\",\"value\":\""+lhuid+"\",\"fieldtype\":'',\"fieldformat\":''}";
	var dataXX = "{querycondition: {conditions: [" +queryconditionXX+"]},pages:{recordsperpage:200, currentpagenum:1, totalpage:0, countrows:0},orders:[{\"fieldname\":\"ALGCXX_UID\",\"order\":\"asc\"}]}";
	var data = {
			msg : dataXX
		};
	$.ajax({
		url :"${pageContext.request.contextPath }/qyxx/yxLhEnterpriseGcalxxController?query",
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
			$("#enterpriseForm").setFormValues(resultobj);	
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
 	 <h4 class="title">企业信息审核&nbsp;&nbsp; <span id="xzmesid"></span>
      	<span class="pull-right"> 
      		<button id="btnSaveXz" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">确认限制</button>
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>   		
      	</span>
      </h4>
      <div id="yxcbsdivid" style="display: none">
      <form method="post" id="yxcbsqueryForm"  >
    	  <input type="hidden" id="QYXCBS_UID" fieldname="YXCBS_UID" name = "YXCBS_UID" operation="="/>
      </form> 
      <FORM name="xzfrmPost" method="post" style="display:none" target="_blank">
         <input type="hidden" name="txtFilter"  order="desc" fieldname = "YXCBS_UID" id = "txtFilter">
 	  </FORM>
      <form method="post" id="enterpriseForm"  >
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
      </form>
      </div>				 
     <form method="post" id="yxlhEnterpriseForm"  >
      <table class="B-table" width="100%" >
	  		<input type="hidden" id="LH_ENTERPRISE_UID" fieldname="LH_ENTERPRISE_UID" name = "LH_ENTERPRISE_UID"/>
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
						<tbody fjlb="7791" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true"></tbody>
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
						<tbody fjlb="7792"  class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true">
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
				<th width="8%" class="right-border bottom-border text-right">安全生产许可证编号</th>
	       	 	<td class="bottom-border right-border" width="15%">
	         		<input  id="ANQUAN_CODE"   type="text" fieldname="ANQUAN_CODE" name = "ANQUAN_CODE" />
	       	 	</td> 
	       	 	<th width="8%" class="right-border bottom-border text-right"></th>
	       		<td class="bottom-border right-border" width="15%">
	         	</td>      	
           </tr>
           
            <tr>
				<th width="8%" class="right-border bottom-border text-right">有效期</th>
	       	 	<td class="bottom-border right-border" width="15%">
	         		<input  id="ANQUAN_VALID"   type="text" fieldname="ANQUAN_VALID" name = "ANQUAN_VALID" class='Wdate' onClick='WdatePicker()'/>
	       	 	</td> 
	       	 	<th width="8%" class="right-border bottom-border text-right">附件</th>
	       	 	<td class="bottom-border right-border" width="15%">
	       	 		<table role="presentation"  class="table table-striped">
						<tbody fjlb="7793"  class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true">
						</tbody>
					</table>
	       	 	</td>        	
           </tr>
        
        
          <tr>
				<th width="8%" class="right-border bottom-border text-right">信用手册编号</th>
	       	 	<td class="bottom-border right-border" width="15%">
	         		<input  id="XINYONG_CODE"   type="text" fieldname="XINYONG_CODE" name = "XINYONG_CODE" />
	       	 	</td> 
	       	 	<th width="8%" class="right-border bottom-border text-right"></th>
	       		<td class="bottom-border right-border" width="15%">
	         	</td>      	
           </tr>
           
            <tr>
				<th width="8%" class="right-border bottom-border text-right">有效期</th>
	       	 	<td class="bottom-border right-border" width="15%">
	         		<input  id="XINYONG_VALID"   type="text" fieldname="XINYONG_VALID" name = "XINYONG_VALID" class='Wdate' onClick='WdatePicker()'/>
	       	 	</td> 
	       	 	<th width="8%" class="right-border bottom-border text-right">附件</th>
	       	 	<td class="bottom-border right-border" width="15%">
	       	 		<table role="presentation"  class="table table-striped">
						<tbody fjlb="7794"  class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true">
						</tbody>
					</table>
	       	 	</td>        	
           </tr>
          <tr>
			
         	<th width="8%" class="right-border bottom-border text-right">主项资质等级</th>
       		<td class="bottom-border right-border" colspan="3">
				<select  id="SG_ZIZHI_UID"   name="SG_ZIZHI_UID" fieldname="SG_ZIZHI_UID" defaultValue="34"  kind="dic"  src="T#SG_ZIZHI:SG_ZIZHI_UID:ZIZHI_NAME:1=1 order by SERIAL_NO" disabled ></select>
				-
				<select id="ZHU_DENGJI"  name="ZHU_DENGJI" fieldname="ZHU_DENGJI" kind="dic" src="T#SG_ZIZHI_DENGJI:SG_ZIZHI_DENGJI_UID:DENGJI_NAME:SG_ZIZHI_UID='34' order by DENGJI_NUMS  "></select>
         	</td>
         </tr>

            <tr>
				<th width="8%" class="right-border bottom-border text-right">主项资质编号</th>
	       	 	<td class="bottom-border right-border" width="15%">
	         		<input  id="ZHENGSHU_CODE"   type="text" fieldname="ZHENGSHU_CODE" name = "ZHENGSHU_CODE" class='Wdate' onClick='WdatePicker()'/>
	       	 	</td> 
	       	 	<th width="8%" class="right-border bottom-border text-right">附件</th>
	       	 	<td class="bottom-border right-border" width="15%">
	       	 		<table role="presentation"  class="table table-striped">
						<tbody fjlb="7795"  class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true">
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
         		<input  id="ZHUCE_ZIJIN"   type="text" fieldname="ZHUCE_ZIJIN" name = "ZHUCE_ZIJIN" />万元
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
			<th width="8%" class="right-border bottom-border text-right">驻无锡负责人</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="ZHUXI_FZR"  type="text" fieldname="ZHUXI_FZR" name = "ZHUXI_FZR" />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">负责人手机</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="ZHUXI_MOBILE"  type="text" fieldname="ZHUXI_MOBILE" name = "ZHUXI_MOBILE" />
         	</td>
         </tr>
          <tr>
			
         	<th width="8%" class="right-border bottom-border text-right">驻无锡办公地址</th>
       		<td class="bottom-border right-border" colspan="3">
         		<input  id="ZHUXI_ADDRESS" type="text" fieldname="ZHUXI_ADDRESS" name = "ZHUXI_ADDRESS" />
         	</td>
         </tr>
         <tr>
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
			<th width="8%" class="right-border bottom-border text-right">工程案例</th>
       		<td class="bottom-border right-border" colspan="3">
         		<table  width="100%" class="table table-bordered">
			    	<thead>
						<tr>
							<th style="width:45%" >&nbsp;项目名称&nbsp;</th>
							<th style="width:15%" >&nbsp;中标通知日期&nbsp;</th>
							<th style="width:40%" >&nbsp;附件&nbsp;</th>
<%--							<th style="width:8%" >&nbsp;操作&nbsp;</th>--%>
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
									<tbody id="FJTBODYID{0}" glid1="{0}" fjlb="7790"  class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>
								</table>
							</td>
<%--							<td class="text-center"></td>--%>
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
				<h3 style="color:white;">绿化企业资质审核
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
    	  <input type="hidden" id="QLH_ENTERPRISE_UID" fieldname="LH_ENTERPRISE_UID" name = "LH_ENTERPRISE_UID" operation="="/>
     </form> 
  
  <div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id = "queryXML">
         <input type="hidden" name="txtXML" id = "txtXML">
         <input type="hidden" name="txtFilter"  order="desc" fieldname = "LH_ENTERPRISE_UID" id = "txtFilter">
         <input type="hidden" name="resultXML" id = "resultXML">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
 	</FORM>
 	
 </div>
 <jsp:include page="/jsp/file_upload/fileuploadold_config_js.jsp" flush="true"/> 
</body>
</html>