<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<LINK type="text/css" rel="stylesheet" href="/wndjsbg/jsp/business/sp/css/xAlert.css"> </LINK>
<script type="text/javascript" src="/wndjsbg/js/base/jquery.js"> </script>
<script type="text/javascript" src="/wndjsbg/jsp/business/sp/js/printPageHtml.js"> </script>
<script type="text/javascript" src="/wndjsbg/js/base/LockingTable.js"> </script>
<script type="text/javascript" src="/wndjsbg/js/base/bootstrap.js"> </script>
<script type="text/javascript" src="/wndjsbg/js/base/jsBruce.js"> </script>
<script type="text/javascript" src="/wndjsbg/js/common/Form2Json.js"> </script>
<script type="text/javascript" src="/wndjsbg/js/common/convertJson.js"> </script>
<script type="text/javascript" src="/wndjsbg/js/common/combineQuery.js"> </script>
<script type="text/javascript" src="/wndjsbg/js/common/default.js?version=20131206"> </script>
<script type="text/javascript" src="/wndjsbg/js/common/TabList.js"> </script>
<script type="text/javascript" src="/wndjsbg/js/common/bootstrap-validation.js"> </script>
<script type="text/javascript" src="/wndjsbg/js/common/loadFields.js?version=20131206"> </script>
<script type="text/javascript" src="/wndjsbg/js/My97DatePicker/WdatePicker.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/jsp/business/sp/js/printPageHtml.js"> </script>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/sp/css/bootstrap.css?version=20131201"></LINK>


<title></title>
<%
String id=(String)request.getAttribute("id");
   // String id=(String)request.getAttribute("id");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/sgsx/buSpywJsgcaqjcsqController/";
var contextPath="${pageContext.request.contextPath }";
var type ="insert";
var id="<%=id%>";
//页面初始化
$(function() {
	$("#pubAlert").hide();
	init();
	
	
	 $("#btnClose").click(function(){
		    window.close();
		    });
	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
	// var ywlzid = $(window.opener.document).find("#YWLZ_UID").val(); //业务流转ID 父页面获取
	// $("#YWLZ_UID").val(ywlzid);
        
        var yz=true;
         $("#d1").find("input[id='PhoneYz']").each(function(i,item) {
		  if(!isPhone(item)){
		  yz=false;
		  return false;
		  }
          });
		if($("#buSpywZzhydjForm").validationButton()&&yz)
		{
		  if(type=="insert"){
			var ywlz_uid = window.opener.getYwlz();
		  	$("#YWLZ_UID").val(ywlz_uid);//	YWLZ_UID
		  }
		    //生成json串
		    var data = Form2Json.formToJSON(buSpywZzhydjForm);
		  //组成保存json串格式
		    var data1 = defaultJson.packSaveJson(data);
		  //调用ajax插入
		    if(type=="insert"){
    			defaultJson.doInsertJson(controllername + "insert", data1);
    			//$("#buSpywZzhydjForm").clearFormResult();
    			var json=$("#resultXML").val();//接受返回的数据 
		    	 var tempJson=eval("("+json+")");
		    	 var resultobj=tempJson.response.data[0];
		    	 $("#ZZHYDJ_UID").val(resultobj.ZZHYDJ_UID);
    			
    			type="update";
    		}else if(type=="update"){
    			defaultJson.doUpdateJson(controllername + "update", data1);
    		}
		}else{
			requireFormMsg();
		  	return;
		}
	});
	
});
function open_lianjie(index){
	if(index==1){
		$("#div1").show();$("#div2").hide();$("#div3").hide();
		$("#div4").hide();$("#div5").hide();$("#div6").hide();
		$("#div7").hide();
	}else if(index==2){
		$("#div1").hide();$("#div2").show();$("#div3").hide();
		$("#div4").hide();$("#div5").hide();$("#div6").hide();
		$("#div7").hide();
	}else if(index==3){
		$("#div1").hide();$("#div2").hide();$("#div3").show();
		$("#div4").hide();$("#div5").hide();$("#div6").hide();
		$("#div7").hide();
	}else if(index==4){
		$("#div1").hide();$("#div2").hide();$("#div3").hide();
		$("#div4").show();$("#div5").hide();$("#div6").hide();
		$("#div7").hide();
	}else if(index==5){
		$("#div1").hide();$("#div2").hide();$("#div3").hide();
		$("#div4").hide();$("#div5").show();$("#div6").hide();
		$("#div7").hide();
	}else if(index==6){
		$("#div1").hide();$("#div2").hide();$("#div3").hide();
		$("#div4").hide();$("#div5").hide();$("#div6").show();
		$("#div7").hide();
	}else if(index==7){
		$("#div1").hide();$("#div2").hide();$("#div3").hide();
		$("#div4").hide();$("#div5").hide();$("#div6").hide();
		$("#div7").show();
	}
}

function queryDtgc(){
	$.ajax({
		url : controllername + "queryDtgc?ywid="+id,
		data : null,
		cache : false,
		async : false,
		dataType : "json",
		type : 'post',
		success : function(response) {
			if(response.msg!=""||response.msg!="0"){
				var cls =  convertJson.string2json1(response.msg);
				var size=cls.response.data.length;
				var showhtml="";
				for(var i=0;i<size;i++){
				 showhtml+="<tr>"
					    +"<td height=\"22\">"+cls.response.data[i].UNITS_NAME+"</td>"
					    +"<td>"+cls.response.data[i].JS_XZ_SV+"</td>"
					    +"<td>"+cls.response.data[i].UNITS_TYPE_SV+"</td>"
					    +"<td>"+cls.response.data[i].JGLX+"</td>"
					    +"<td>"+cls.response.data[i].DJLX_SV+"</td>"
					    +"<td>"+cls.response.data[i].JCLX_SV+"</td>"
					    +"<td>"+cls.response.data[i].JZMJ+"</td>"
					    +"<td>"+cls.response.data[i].DSMJ+"</td>"
					    +"<td>"+cls.response.data[i].DXMJ+"</td>"
					    +"<td>"+cls.response.data[i].DSCS+"</td>"
					    +"<td>"+cls.response.data[i].DXCS+"</td>"
					    +"<td>"+cls.response.data[i].DSJG_SV+"</td>"
					    +"<td>"+cls.response.data[i].DXJG_SV+"</td>"
					    +"<td>"+cls.response.data[i].YCCS+"</td>"
					    +"<td>"+cls.response.data[i].CD+"</td>"
					    +"<td>"+cls.response.data[i].ZXBZ_SV+"</td>"
					    +"<td>"+cls.response.data[i].GCZJ+"</td>"
					    +"<td>"+cls.response.data[i].GQ_DATE+"</td>"
					    +"</tr>";
				}
				$("#dt1").html(showhtml);
			}
		} 
	});
}
//修改 查询数据
function query(){ 
			//查询表单赋值       
			$("#QID").val(id);
			if(id==""||id==null||id=="null"){
				type="insert";
				return;
			}
			var data = combineQuery.getQueryCombineData(queryForm,
					frmPost);
			var data1 = { 
				msg : data
			};
			$.ajax({
				url : controllername + "query",
				data : data1,
				cache : false,
				async : false,
				dataType : "json",
				type : 'post',
				success : function(response) {
					var res = dealSpecialCharactor(response.msg);
					$("#resultXML").val(response.msg);
					//alert($("#resultXML").val());
					if(response.msg==""||response.msg=="0")
						type="insert";
					else{
						type="update";
					var resultobj = defaultJson.dealResultJson(res);
					$("#jsmsg").html(resultobj.COMPANY_NAME);
					$("#gcmsg").html(resultobj.GC_MC);
					$("#buSpywZzhydjForm").setFormValues(resultobj);
					}
				} 
			});

		}
//页面默认参数
function init(){ 
	query();
	queryDtgc();
	if(type=="insert"){
		var sgobj=getSgCompany();
		var jsobj=getJsCompany(gcid);
		setobj(sgobj,jsobj);	
	}
}



//赋值 企业 项目信心
function setobj(sgobj,jsobjs){
	var size=jsobjs.response.data.length;
	$("#div1").initSetFormValue(jsobjs.response.data[0]);
	$("#div6").initSetFormValue(jsobjs.response.data[0]);
	
	$("#div2").initSetFormValue(sgobj);
	for(var i=0;i<size;i++){
		var jsobj=jsobjs.response.data[i];
		if(jsobj.GANGWEI_UID=='19'){//19项目经理
			$("#SG_XM_JL").val(jsobj.SG_NAME);
			$("#XMJL_ZS_BH").val(jsobj.ZHENGSHU_CODE);
			$("#SG_PHONE").val(jsobj.MOBILE);
			$("#XMJL_AQ_BH").val(jsobj.AQKH_CODE);
			$("#SG_XMJL_SFZ").val(jsobj.SHENFENZHENG);
		}
		if(jsobj.GANGWEI_UID=='20'){//20技术负责人
			$("#JS_FZR").val(jsobj.SG_NAME);
			$("#SG_JS_SGZ").val(jsobj.ZHENGSHU_CODE);
			$("#JS_PHONE").val(jsobj.MOBILE);
			$("#SG_JS_SFZ").val(jsobj.SHENFENZHENG);
		}
		
	}
	$("#KG_JG_DATE").val(jsobjs.response.data[0].KG_DATE+"至"+jsobjs.response.data[0].JG_DATE);
	
}

//验证电话号码是否有效 
function isPhone(card){  
	      var reg =/^(13[0-9]|15[012356789]|17[0-9]|18[012356789]|14[57])\d{8}$/;
		  
		  if($(card).val() == null || $(card).val() == ""){
			  alert("申请人的联系电话不能为空");
			  return false;
			  }
		  else{
		     if(reg.test($(card).val()) == false)  
		     {  
		       alert("申请人的联系电话输入不合法"); 
		       $(card).attr("style", "color:red;font-size: 14px;width: 87%");
		       return  false;  
		     }else{
		    	 $(card).removeAttr("style");
		    	  $(card).attr("style", "width: 87%"); 
		    	 
			   return true;
		        }
		  }
}

$.fn.initSetFormValue = function(obj){
	var formobj = this;
	$('input',formobj).each(function(){
		if($(this).attr("setname")!=null){
			var paramName = $(this).attr("setname");
			if($(this).attr("fieldtype")=="dic"){
				paramName = $(this).attr("setname")+"_SV";
			}
			$(this).val(obj[paramName]);
		}
	})
};




</script>      
</head>
<body>
<div class="container-fluid">
<div class="row-fluid">
    <div class="B-small-from-table-autoConcise">
     <form method="post" id="queryForm"  >
     <table class="B-table" width="100%">
      <!--可以再此处加入hidden域作为过滤条件 -->
      	<TR  style="display:none;">
	        <TD class="right-border bottom-border">
	        <INPUT type="hidden" class="cc-input1" id="QID" fieldname="YWLZ_UID"
								 operation="=" />
	        </TD>
			<TD class="right-border bottom-border">
			</TD>
        </TR>
      </table>
      </form>
    <div style="height:5px;"></div>
	</div>
	<div style="height:5px;"></div>
	<div class="B-small-from-table-autoConcise">
	<span style="float:left">
      		<span id="P10080_PAGE_TO">
      		<a href="javascript:open_lianjie(1);">1</a>
      		<a href="javascript:open_lianjie(2);">2</a>
      		<a href="javascript:open_lianjie(3);">3</a>
      		<a href="javascript:open_lianjie(4);">4</a>
      		<a href="javascript:open_lianjie(5);">5</a>
      		<a href="javascript:open_lianjie(6);">6</a>
      		<a href="javascript:open_lianjie(7);">7</a>
      		
      		</span>
      	</span>
	    <span style="float:right">
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
	  		<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">保存</button>
      	</span><br/>
	 </div>
	     
        	
     <form method="post" id="buSpywZzhydjForm" >
            <input type="hidden" id="JSGCAQJCSQ_UID" fieldname="JSGCAQJCSQ_UID" setname = "JSGCAQJCSQ_UID"/>
	  	  <input type="hidden" id="GC_TCJH_XMXDK_ID" fieldname="XMID" setname = "XMID"/>
	  	      <input type="hidden" id="YWLZ_UID" fieldname="YWLZ_UID" value="0" setname = "YWLZ_UID"/>
	  	      <div align="center">
<div align="center" style="width:725px" id="div1" >
  <p align="center"><h4>建设工程安全监督申报表</h4><br />
    xx建设的xx工程，根据中华人民共和国国务院第393号令《建设工程安全生产管理条例》及有关规定，办理安全监督手续。 <br />
  <strong>一、工程概况</strong><strong> </strong></p>
  <table  border="1" cellspacing="0" cellpadding="0" width="725px" >
    <tr>
      <td ><p align="center">
        工程名称 </p></td>
      <td  colspan="4" ><input  maxlength="255" style="margin: 0;width: 96%" type="text" id="GC_NAME" fieldname="GC_NAME" setname = "GCNAME" readonly="readonly"  /></td>
      <td ><p align="center">工程地点 </p></td>
      <td colspan="3" ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="GC_DIDIAN" fieldname="GC_DIDIAN" setname = "JIANSHE_DIZHI" readonly="readonly"  /></td>
    </tr>
    <tr>
      <td ><p align="center">建筑面积(平方米)</p></td>
      <td  ><input  maxlength="255" style="margin: 0;width: 91%" type="text" id="JZ_MJ" fieldname="JZ_MJ" setname = "MIANJI" readonly="readonly"  /></td>
      <td ><p align="center">结构层次 </p></td>
      <td colspan="2" ><input  maxlength="255" style="margin: 0;width: 89%" type="text" id="JG_CC" fieldname="JG_CC"    /></td>
      <td  ><p align="center">开竣工日期 </p></td>
      <td colspan="3"><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="KG_JG_DATE" fieldname="KG_JG_DATE"   readonly="readonly"  /></td>
    </tr>
    <tr>
      <td width="75" rowspan="2" ><p align="center">工程造价(万元)</p></td>
      <td width="100" rowspan="2" ><input  maxlength="255" style="margin: 0;width: 91%" type="text" id="GCZJ" fieldname="GCZJ" setname = "JINE"   /></td>
      <td width="11" rowspan="2" ><p align="center">其中 </p></td> 
      <td width="22" ><p align="center">桩基 </p></td>
      <td width="48" ><input  maxlength="255" style="margin: 0;width: 84%" type="text" id="ZJ" fieldname="ZJ"    /></td>
      <td width="50" ><p align="center">土建 </p></td>
      <td width="48" ><input  maxlength="255" style="margin: 0;width: 84%" type="text" id="TJ" fieldname="TJ"    /></td>
      <td width="22" ><p align="center">安装 </p></td>
      <td width="69" ><input  maxlength="255" style="margin: 0;width: 86%" type="text" id="AZ" fieldname="AZ"    /></td>
    </tr>
    <tr>
      <td width="22" ><p align="center">装修 </p></td>
      <td width="48" ><input  maxlength="255" style="margin: 0;width: 84%" type="text" id="ZX" fieldname="ZX"    /></td>
      <td width="50" ><p align="center">室外工程 </p></td>
      <td  colspan="3" ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="HJ" fieldname="HJ"    /></td>
    </tr>
  </table>
  <p align="center"><strong>二、参建单位 </strong></p>
  <table border="1" cellspacing="0" cellpadding="0" width="725px" >
    <tr>
      <td width="20" rowspan="5" >
        <p align="center">建设单位 </p></td> 
      <td width="103" ><p align="center">法人代表 </p></td>
      <td width="190" ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="JS_FAREN1" fieldname="JS_FAREN1" setname = "JS_FAREN"   /></td>
      <td width="103"  ><p align="center">联系电话 </p></td>
      <td width="190" ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="FA_PHONE" fieldname="FA_PHONE" setname = "JS_FAREN_MOBILE"   /></td>
    </tr>
    <tr>
      <td ><p align="center">项目负责人 </p></td>
      <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="XMFZR" fieldname="XMFZR" setname = "JS_FZR"   /></td>
      <td  ><p align="center">联系电话 </p></td>
      <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="FZR_PHONE" fieldname="FZR_PHONE" setname = "JS_FZR_MOBILE"   /></td>
    </tr>
    <tr>
      <td ><p align="center">施工现场安全生产负责人 </p></td>
      <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="SGAQ_FZR" fieldname="SGAQ_FZR"    /></td>
      <td  ><p align="center">联系电话 </p></td>
      <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="AQ_FZR_PHONE" fieldname="AQ_FZR_PHONE"    /></td>
    </tr>
    <tr>
      <td ><p align="center">地址 </p></td>
      <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="DIZHI" fieldname="DIZHI" setname = "JS_ADDRESS"   /></td>
      <td  ><p align="center">现场电话 </p></td>
      <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="XC_PHONE" fieldname="XC_PHONE" setname = "JS_PHONE"   /></td>
    </tr>
    <tr>
      <td ><p align="center">开户银行 </p></td>
      <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="KH_BANK" fieldname="KH_BANK" setname = "JS_BANK"   /></td>
      <td  ><p align="center">账号 </p></td>
      <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="ZHANGHAO" fieldname="ZHANGHAO" setname = "JS_BANK_ACCOUNTjs"   /></td>
    </tr>
    <tr>
      <td width="28" rowspan="5" >
        <p align="center">勘察单位 </p></td>
      <td ><p align="center">单位全称 </p></td>
      <td width="427" colspan="3" ><input  maxlength="255" style="margin: 0;width: 97%" type="text" id="KC_DW_QC" fieldname="KC_DW_QC"   /></td>
    </tr>
    <tr>
      <td ><p align="center">法人代表 </p></td>
      <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="FRDB" fieldname="FRDB"    /></td>
      <td  ><p align="center">联系电话 </p></td>
      <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="DB_PHONE" fieldname="DB_PHONE"    /></td>
    </tr>
    <tr>
      <td ><p align="center">项目负责人 </p></td>
      <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="XMFUZR" fieldname="XMFUZR"    /></td>
      <td  ><p align="center">联系电话 </p></td>
      <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="FUZR_PHONE" fieldname="FUZR_PHONE"    /></td>
    </tr>
    <tr>
      <td ><p align="center">安全生产负责人 </p></td>
      <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="SC_FZR" fieldname="SC_FZR"    /></td>
      <td  ><p align="center">联系电话 </p></td>
      <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="SC_PHONE" fieldname="SC_PHONE"    /></td>
    </tr>
    <tr>
      <td ><p align="center">地址 </p></td>
      <td width="427" colspan="3" ><input  maxlength="255" style="margin: 0;width: 97%" type="text" id="DIZHI1" fieldname="DIZHI1"    /></td>
    </tr>
    <tr>
      <td width="28" rowspan="4" ><p>设计单位 </p></td>
      <td ><p align="center">单位全称</p></td>
      <td width="427" colspan="3" ><input  maxlength="255" style="margin: 0;width: 97%" type="text" id="SJ_DW_NAME" fieldname="SJ_DW_NAME"    /></td>
    </tr>
    <tr>
      <td ><p align="center">法人代表 </p></td>
      <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="SJ_FAREN1" fieldname="SJ_FAREN1"    /></td>
      <td  ><p align="center">联系电话 </p></td>
      <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="SJ_PHONE" fieldname="SJ_PHONE"    /></td>
    </tr>
    <tr>
      <td ><p align="center">项目负责人 </p></td>
      <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="SJ_XM_FZR1" fieldname="SJ_XM_FZR1"    /></td>
      <td  ><p align="center">联系电话 </p></td>
      <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="XM_FZR_PHONE" fieldname="XM_FZR_PHONE"    /></td>
    </tr>
    <tr>
      <td ><p align="center">地址 </p></td>
      <td width="427" colspan="3" ><input  maxlength="255" style="margin: 0;width: 97%" type="text" id="SJ_DIZHI" fieldname="SJ_DIZHI"    /></td>
    </tr>
    <tr>
      <td width="28" rowspan="6" >
        <p align="center">监理单位 </p></td>
      <td ><p align="center">监理企业报备编码 </p></td>
      <td width="427" colspan="3" ><input  maxlength="255" style="margin: 0;width: 97%" type="text" id="JL_BB_CODE" fieldname="JL_BB_CODE" setname = "BB_CODE"   /></td>
    </tr>
    <tr>
      <td ><p align="center">单位全称 </p></td>
      <td  ><input readonly="readonly" maxlength="255" style="margin: 0;width: 93%" type="text" id="JL_DW_NAME" fieldname="JL_DW_NAME" setname = "JL_COMPANY_NAME"   /></td>
      <td  ><p align="center">联系电话 </p></td>
      <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="JL_PHONE" fieldname="JL_PHONE" setname = "JL_PHONE"   /></td>
    </tr>
    <tr>
      <td ><p align="center">法人代表 </p></td>
      <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="JL_FAREN" fieldname="JL_FAREN" setname = "JL_FAREN"   /></td>
      <td  ><p align="center">联系电话 </p></td>
      <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="JL_FR_PHONE" fieldname="JL_FR_PHONE" setname = "JL_FAREN_MOBILE"   /></td>
    </tr>
    <tr>
      <td ><p align="center">总监理工程师 </p></td>
      <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="ZJL_GCS" fieldname="ZJL_GCS"    /></td>
      <td  ><p align="center">联系电话 </p></td>
      <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="GCS_PHONE" fieldname="GCS_PHONE"    /></td>
    </tr>
    <tr>
      <td ><p align="center">安全生产负责人 </p></td>
      <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="AQ_FZR" fieldname="AQ_FZR"    /></td>
      <td  ><p align="center">联系电话 </p></td>
      <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="AQ_PHONE" fieldname="AQ_PHONE"    /></td>
    </tr>
    <tr>
      <td ><p align="center">地址 </p></td>
      <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="JL_DIZHI" fieldname="JL_DIZHI"    /></td>
      <td  ><p align="center">现场电话 </p></td>
      <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="XCDH" fieldname="XCDH"    /></td>
    </tr>
  </table>
  </div>
  <div id="div2" style="display: none;">
  <p align="center"><strong>二、参建单位 </strong></p>
  <table width="725px"  border="1" cellspacing="0" cellpadding="0">
  <tr>
    <td colspan="2" rowspan="26"  >
      <p>施工单位 </p></td>
    <td width="130px"  ><p align="center">单位全称 </p></td>
    <td width="210px"  ><input readonly="readonly" maxlength="255" style="margin: 0;width: 93%" type="text" id="SG_DW_NAME" fieldname="SG_DW_NAME" setname = "COMPANY_NAME"   /></td>
    <td width="150px"  ><p align="center">法定代表人 </p></td>
    <td width="200px" ><input readonly="readonly" maxlength="255" style="margin: 0;width: 93%" type="text" id="FDDBR" fieldname="FDDBR" setname = "FAREN"   /></td>
  </tr>
  <tr>
    <td  ><p align="center">项目经理 </p></td>
    <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="SG_XM_JL" fieldname="SG_XM_JL"    /></td>
    <td  ><p align="center">项目经理联系电话 </p></td>
    <td ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="SG_PHONE" fieldname="SG_PHONE"    /></td>
  </tr>
  <tr>
    <td  ><p align="center">项目经理证书编号 </p></td>
    <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="XMJL_ZS_BH" fieldname="XMJL_ZS_BH"    /></td>
    <td  ><p align="center">项目经理<br />
      安全考核证书编号 </p></td>
    <td ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="XMJL_AQ_BH" fieldname="XMJL_AQ_BH"    /></td>
  </tr>
  <tr>
    <td  ><p align="center">项目经理身份证号 </p></td>
    <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="SG_XMJL_SFZ" fieldname="SG_XMJL_SFZ"    /></td>
    <td  ></td>
    <td ></td>
  </tr>
  <tr> 
    <td  ><p align="center">项目技术负责人 </p></td>
    <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="JS_FZR" fieldname="JS_FZR"    /></td>
    <td  ><p align="center">联系电话 </p></td>
    <td ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="JS_PHONE" fieldname="JS_PHONE"    /></td>
  </tr>
  <tr>
    <td  ><p align="center">技术负责人身份证号 </p></td>
    <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="SG_JS_SFZ" fieldname="SG_JS_SFZ"    /></td>
    <td  ><p align="center">技术负责人证书编号 </p></td>
    <td ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="SG_JS_SGZ" fieldname="SG_JS_SGZ"    /></td>
  </tr>
  <tr>
    <td  ><p align="center">安全生产负责人 </p></td>
    <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="AQ_SC_FZR" fieldname="AQ_SC_FZR"    /></td>
    <td  ><p align="center">联系电话 </p></td>
    <td ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="SC_FZR_PHONE" fieldname="SC_FZR_PHONE"    /></td>
  </tr>
  <tr>
    <td  ><p align="center">安全生产负责人<br />
      安全考核证书编号 </p></td>
    <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="AQ_SC_CODE" fieldname="AQ_SC_CODE"    /></td>
    <td  ></td>
    <td ></td>
  </tr>
  <tr>
    <td  ><p align="center">安全员1 </p></td>
    <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="SAFER" fieldname="SAFER"    /></td>
    <td  ><p align="center">联系电话 </p></td>
    <td ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="SAFER_PHONE" fieldname="SAFER_PHONE"    /></td>
  </tr>
  <tr>
    <td  ><p align="center">安全员1安全考核证书编号 </p></td>
    <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="SAFER_CODE" fieldname="SAFER_CODE"    /></td>
    <td  ><p align="center">安全员1身份证 </p></td>
    <td ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="AQY_SFZ" fieldname="AQY_SFZ"    /></td>
  </tr>
  <tr>
    <td  ><p align="center">安全员2 </p></td>
    <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="SAFER2" fieldname="SAFER2"    /></td>
    <td  ><p align="center">联系电话 </p></td>
    <td ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="SAFER2_PHONE" fieldname="SAFER2_PHONE"    /></td>
  </tr>
  <tr>
    <td  ><p align="center">安全员2安全考核证书编号 </p></td>
    <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="SAFER2_CODE" fieldname="SAFER2_CODE"    /></td>
    <td  ><p align="center">安全员2身份证 </p></td>
    <td ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="SAFER2_SFZ" fieldname="SAFER2_SFZ"    /></td>
  </tr>
  <tr>
    <td  ><p align="center">安全员3 </p></td>
    <td  ><input  max93%h="255" style="margin: 0;width: 93%" type="text" id="SAFER3" fieldname="SAFER3"    /></td>
    <td  ><p align="center">联系电话 </p></td>
    <td ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="SAFER3_PHONE" fieldname="SAFER3_PHONE"    /></td>
  </tr>
  <tr>
    <td  ><p align="center">安全员3安全考核证书编号 </p></td>
    <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="SAFER3_CODE" fieldname="SAFER3_CODE"    /></td>
    <td  ><p align="center">安全员3身份证 </p></td>
    <td ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="SAFER3_SFZ" fieldname="SAFER3_SFZ"    /></td>
  </tr>
  <tr>
    <td  ><p align="center">质检员 </p></td>
    <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="SG_ZLY" fieldname="SG_ZLY"    /></td>
    <td  ><p align="center">联系电话 </p></td>
    <td ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="SG_ZLY_PHONE" fieldname="SG_ZLY_PHONE"    /></td>
  </tr>
  <tr>
    <td  ><p align="center">质检员证书编号 </p></td>
    <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="SG_ZLY_CODE" fieldname="SG_ZLY_CODE"    /></td>
    <td  ><p align="center">质检员身份证 </p></td>
    <td ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="SG_ZLY_SFZ" fieldname="SG_ZLY_SFZ"    /></td>
  </tr>
  <tr>
    <td  ><p align="center">施工员 </p></td>
    <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="SG_SGY" fieldname="SG_SGY"    /></td>
    <td  ><p align="center">联系电话 </p></td>
    <td ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="SG_SGY_PHONE" fieldname="SG_SGY_PHONE"    /></td>
  </tr>
  <tr>
    <td  ><p align="center">施工员证书编号 </p></td>
    <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="SG_SGY_CODE" fieldname="SG_SGY_CODE"    /></td>
    <td  ><p align="center">施工员身份证 </p></td>
    <td ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="SG_SGY_SFZ" fieldname="SG_SGY_SFZ"    /></td>
  </tr>
  <tr>
    <td  ><p align="center">信息管理员 </p></td>
    <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="SG_XXY" fieldname="SG_XXY"    /></td>
    <td  ><p align="center">联系电话 </p></td>
    <td ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="SG_XXY_PHONE" fieldname="SG_XXY_PHONE"    /></td>
  </tr>
  <tr>
    <td  ><p align="center">信息管理员证书编号 </p></td>
    <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="SG_XXY_CODE" fieldname="SG_XXY_CODE"    /></td>
    <td  ><p align="center">信息管理员身份证 </p></td>
    <td ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="SG_XXY_SFZ" fieldname="SG_XXY_SFZ"    /></td>
  </tr>
  <tr>
    <td  ><p align="center">资料员 </p></td>
    <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="SG_ZILIAOYUAN" fieldname="SG_ZILIAOYUAN"    /></td>
    <td  ><p align="center">联系电话 </p></td>
    <td ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="SG_ZILIAOYUAN_PHONE" fieldname="SG_ZILIAOYUAN_PHONE"    /></td>
  </tr>
  <tr>
    <td  ><p align="center">资料员证书编号 </p></td>
    <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="SG_ZILIAOYUAN_CODE" fieldname="SG_ZILIAOYUAN_CODE"    /></td>
    <td  ><p align="center">资料员身份证 </p></td>
    <td ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="SG_ZILIAOYUAN_SFZ" fieldname="SG_ZILIAOYUAN_SFZ"    /></td>
  </tr>
  <tr>
    <td  rowspan="4" >
      <p align="center">特种作业人员 </p></td>
    <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="TZ_ZY1" fieldname="TZ_ZY1"    /></td>
    <td  ><input  maxlength="255" style="margin: 0;width: 90%" type="text" id="TZ_ZY2" fieldname="TZ_ZY2"    /></td>
    <td ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="TZ_ZY3" fieldname="TZ_ZY3"    /></td>
  </tr>
  <tr>
    <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="TZ_ZY4" fieldname="TZ_ZY4"    /></td>
    <td  ><input  maxlength="255" style="margin: 0;width: 90%" type="text" id="TZ_ZY5" fieldname="TZ_ZY5"    /></td>
    <td ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="TZ_ZY6" fieldname="TZ_ZY6"    /></td>
  </tr>
  <tr>
    <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="TZ_ZY7" fieldname="TZ_ZY7"    /></td>
    <td  ><input  maxlength="255" style="margin: 0;width: 90%" type="text" id="TZ_ZY8" fieldname="TZ_ZY8"    /></td>
    <td ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="TZ_ZY9" fieldname="TZ_ZY9"    /></td>
  </tr>
  <tr>
    <td  ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="TZ_ZY10" fieldname="TZ_ZY10"    /></td>
    <td  ><input  maxlength="255" style="margin: 0;width: 90%" type="text" id="TZ_ZY11" fieldname="TZ_ZY11"    /></td>
    <td ><input  maxlength="255" style="margin: 0;width: 93%" type="text" id="TZ_ZY12" fieldname="TZ_ZY12"    /></td>
  </tr>
  
  <tr>
    <td width="30" >
      <p>备注 </p></td>
    <td colspan="5" >
      <textarea rows="3" id="BEIZHU" fieldname="BEIZHU" maxlength="255" style="margin: 0;width: 98%" ></textarea></td>
  </tr>
</table>
  </div>
  
  <div id="div3" style="display: none;">
  <p align="center"><strong>三、专业承包单位备案登记表 </strong></p>
  <table border="1" cellspacing="0" cellpadding="0">
  <tr>
    <td width="73" rowspan="2" ><div align="center">分包工程概况 </div></td>
    <td colspan="2" ><p align="center">工程名称(内容)</p></td>
    <td width="198" ><input maxlength="255" style="margin: 0;width: 92%" type="text" id="ZYCB_GCNR" fieldname="ZYCB_GCNR"    /></td>
    <td width="126" ><p align="center">合同价格 </p></td>
    <td width="189" ><input maxlength="255" style="margin: 0;width: 92%" type="text" id="ZYCB_HTJG" fieldname="ZYCB_HTJG"    /></td>
  </tr>
  <tr>
    <td colspan="2" ><p align="center">合同开工日期 </p></td>
    <td width="198" ><input maxlength="255" readonly="readonly" class="Wdate" onClick="WdatePicker()"    data-date-format="YYYY-MM-DD" style="margin: 0;width: 92%" type="text" id="ZYCB_KG_DATE" fieldname="ZYCB_KG_DATE"    /></td>
    <td width="126" ><p align="center">合同竣工日期 </p></td>
    <td width="189" ><input maxlength="255" readonly="readonly" class="Wdate" onClick="WdatePicker()"    data-date-format="YYYY-MM-DD" style="margin: 0;width: 92%" type="text" id="ZYCB_JG_DATE" fieldname="ZYCB_JG_DATE"    /></td>
  </tr>
  <tr>
    <td colspan="3" ><p align="center">单位名称 </p></td>
    <td colspan="3" ><input maxlength="255" style="margin: 0;width: 97%" type="text" id="ZYCB_DWMC" fieldname="ZYCB_DWMC"    /></td>
  </tr>
  <tr>
    <td colspan="3" ><p align="center">地址 </p></td>
    <td colspan="3" ><input maxlength="255" style="margin: 0;width: 97%" type="text" id="ZYCB_DZ" fieldname="ZYCB_DZ"    /></td>
  </tr>
  <tr>
    <td colspan="3" ><p align="center">组织机构代码 </p></td>
    <td width="198" ><input maxlength="255" style="margin: 0;width: 92%" type="text" id="ZYCB_JGDM" fieldname="ZYCB_JGDM"    /></td>
    <td width="126" ><p align="center">安全生产许可证编号 </p></td>
    <td width="189" ><input maxlength="255" style="margin: 0;width: 92%" type="text" id="ZYCB_AQXKZ" fieldname="ZYCB_AQXKZ"    /></td>
  </tr>
  <tr>
    <td colspan="3" ><p align="center">资质证书编号 </p></td>
    <td width="198" ><input maxlength="255" style="margin: 0;width: 92%" type="text" id="ZYCB_ZZZS" fieldname="ZYCB_ZZZS"    /></td>
    <td width="126" ><p align="center">资质等级 </p></td>
    <td width="189" ><input maxlength="255" style="margin: 0;width: 92%" type="text" id="ZYCB_ZZDJ" fieldname="ZYCB_ZZDJ"    /></td>
  </tr>
  <tr>
    <td colspan="3" ><p align="center">法定代表人或委托负责人 </p></td>
    <td width="198" ><input maxlength="255" style="margin: 0;width: 92%" type="text" id="ZYCB_FR" fieldname="ZYCB_FR"    /></td>
    <td width="126" ><p align="center">安全考核证号 </p></td>
    <td width="189" ><input maxlength="255" style="margin: 0;width: 92%" type="text" id="ZYCB_FR_AQKH" fieldname="ZYCB_FR_AQKH"    /></td>
  </tr>
  <tr>
    <td colspan="3" ><p align="center">单位技术负责人 </p></td>
    <td width="198" ><input maxlength="255" style="margin: 0;width: 92%" type="text" id="ZYCB_JSFZR" fieldname="ZYCB_JSFZR"    /></td>
    <td width="126" ><p align="center">安全考核证号 </p></td>
    <td width="189" ><input maxlength="255" style="margin: 0;width: 92%" type="text" id="ZYCB_JSFZR_AQKH" fieldname="ZYCB_JSFZR_AQKH"    /></td>
  </tr>
  <tr>
    <td colspan="3" ><p align="center">单位分管安全负责人 </p></td>
    <td width="198" ><input maxlength="255" style="margin: 0;width: 92%" type="text" id="ZYCB_FGAQR" fieldname="ZYCB_FGAQR"    /></td>
    <td width="126" ><p align="center">安全考核证号 </p></td>
    <td width="189" ><input maxlength="255" style="margin: 0;width: 92%" type="text" id="ZYCB_FGAQR_AQKH" fieldname="ZYCB_FGAQR_AQKH"    /></td>
  </tr>
  <tr>
    <td colspan="2" rowspan="2" ><p align="center">项目经理 </p></td>
    <td width="92" ><p align="center">姓名 </p></td>
    <td width="198" ><input maxlength="255" style="margin: 0;width: 92%" type="text" id="ZYCB_XMJL" fieldname="ZYCB_XMJL"    /></td>
    <td width="126" ><p align="center">联系电话 </p></td>
    <td width="189" ><input maxlength="255" style="margin: 0;width: 92%" type="text" id="ZYCB_XMJL_LXDH" fieldname="ZYCB_XMJL_LXDH"    /></td>
  </tr>
  <tr>
    <td width="92" ><p align="center">资格等级及证书号 </p></td>
    <td width="198" ><input maxlength="255" style="margin: 0;width: 92%" type="text" id="ZYCB_XMJL_ZGDJ" fieldname="ZYCB_XMJL_ZGDJ"    /></td>
    <td width="126" ><p align="center">安全考核证号 </p></td>
    <td width="189" ><input maxlength="255" style="margin: 0;width: 92%" type="text" id="ZYCB_XMJL_AQKH" fieldname="ZYCB_XMJL_AQKH"    /></td>
  </tr>
  <tr>
    <td colspan="3" ><p align="center">项目技术负责人 </p></td>
    <td width="198" ><input maxlength="255" style="margin: 0;width: 92%" type="text" id="ZYCB_XMJSFZR" fieldname="ZYCB_XMJSFZR"    /></td>
    <td width="126" ><p align="center">安全考核证号 </p></td>
    <td width="189" ><input maxlength="255" style="margin: 0;width: 92%" type="text" id="ZYCB_XMJSFZR_AQKH" fieldname="ZYCB_XMJSFZR_AQKH"    /></td>
  </tr>
  <tr>
    <td colspan="3" ><p align="center">安全员 </p></td>
    <td width="198" ><input maxlength="255" style="margin: 0;width: 92%" type="text" id="ZYCB_AQY1" fieldname="ZYCB_AQY1"    /></td>
    <td width="126" ><p align="center">安全考核证号 </p></td>
    <td width="189" ><input maxlength="255" style="margin: 0;width: 92%" type="text" id="ZYCB_AQY1_AQKH" fieldname="ZYCB_AQY1_AQKH"    /></td>
  </tr>
  <tr>
    <td colspan="3" ><p align="center">安全员 </p></td>
    <td width="198" ><input maxlength="255" style="margin: 0;width: 92%" type="text" id="ZYCB_AQY2" fieldname="ZYCB_AQY2"    /></td>
    <td width="126" ><p align="center">安全考核证号 </p></td>
    <td width="189" ><input maxlength="255" style="margin: 0;width: 92%" type="text" id="ZYCB_AQY2_AQKH" fieldname="ZYCB_AQY2_AQKH"    /></td>
  </tr>
  <tr>
    <td colspan="3" ><p align="center">安全员 </p></td>
    <td width="198" ><input maxlength="255" style="margin: 0;width: 92%" type="text" id="ZYCB_AQY3" fieldname="ZYCB_AQY3"    /></td>
    <td width="126" ><p align="center">安全考核证号 </p></td>
    <td width="189" ><input maxlength="255" style="margin: 0;width: 92%" type="text" id="ZYCB_AQY3_AQKH" fieldname="ZYCB_AQY3_AQKH"    /></td>
  </tr>
</table>
  </div>
  
  <div id="div4" style="display: none;">
    <p align="center"><strong>四、劳务单位备案登记表</strong></p>
  <table width="700" height="252" border="1" cellpadding="0" cellspacing="0">
  <tr>
    <td width="48" rowspan="2" ><p align="center">分包工程概况 </p></td>
    <td width="90" ><p align="center">工程名称(内容)</p></td>
    <td width="273" ><input  maxlength="255" style="margin: 0;width: 95%" type="text" id="LWFB_GCNR" fieldname="LWFB_GCNR"    /></td>
    <td width="114" ><p align="center">合同价格 </p></td>
    <td width="163" ><input  maxlength="255" style="margin: 0;width: 91%" type="text" id="LWFB_HTJG" fieldname="LWFB_HTJG"    /></td>
  </tr>
  <tr>
    <td width="90" ><p align="center">合同开工日期 </p></td>
    <td width="273" ><input  maxlength="255" readonly="readonly" class="Wdate" onClick="WdatePicker()"    data-date-format="YYYY-MM-DD" style="margin: 0;width: 95%" type="text" id="LWFB_KG_DATE" fieldname="LWFB_KG_DATE"    /></td>
    <td width="114" ><p align="center">合同竣工日期 </p></td>
    <td width="163" ><input  maxlength="255" readonly="readonly" class="Wdate" onClick="WdatePicker()"    data-date-format="YYYY-MM-DD" style="margin: 0;width: 91%" type="text" id="LWFB_JG_DATE" fieldname="LWFB_JG_DATE"    /></td>
  </tr>
  <tr>
    <td colspan="2" ><p align="center">单位名称 </p></td>
    <td colspan="3" ><input  maxlength="255" style="margin: 0;width: 97%" type="text" id="LWFB_DWMC" fieldname="LWFB_DWMC"    /></td>
  </tr>
  <tr>
    <td colspan="2" ><p align="center">地址 </p></td>
    <td colspan="3" ><input  maxlength="255" style="margin: 0;width: 97%" type="text" id="LWFB_DZ" fieldname="LWFB_DZ"    /></td>
  </tr>
  <tr>
    <td colspan="2" ><p align="center">组织机构代码 </p></td>
    <td width="273" ><input  maxlength="255" style="margin: 0;width: 95%" type="text" id="LWFB_JGDM" fieldname="LWFB_JGDM"    /></td>
    <td width="114" ><p align="center">安全生产许可证编号 </p></td>
    <td width="163" ><input  maxlength="255" style="margin: 0;width: 91%" type="text" id="LWFB_AQXKZ" fieldname="LWFB_AQXKZ"    /></td>
  </tr>
  <tr>
    <td colspan="2" ><p align="center">资质证书编号 </p></td>
    <td width="273" ><input  maxlength="255" style="margin: 0;width: 95%" type="text" id="LWFB_ZZZS" fieldname="LWFB_ZZZS"    /></td>
    <td width="114" ><p align="center">资质等级 </p></td>
    <td width="163" ><input  maxlength="255" style="margin: 0;width: 91%" type="text" id="LWFB_ZZDJ" fieldname="LWFB_ZZDJ"    /></td>
  </tr>
  <tr>
    <td colspan="2" ><p align="center">法定代表人或委托负责人 </p></td>
    <td width="273" ><input  maxlength="255" style="margin: 0;width: 95%" type="text" id="LWFB_FR" fieldname="LWFB_FR"    /></td>
    <td width="114" ><p align="center">安全考核证号 </p></td>
    <td width="163" ><input  maxlength="255" style="margin: 0;width: 91%" type="text" id="LWFB_FR_AQKH" fieldname="LWFB_FR_AQKH"    /></td>
  </tr>
  <tr>
    <td colspan="2" ><p align="center">单位技术负责人 </p></td>
    <td width="273" ><input  maxlength="255" style="margin: 0;width: 95%" type="text" id="LWFB_JSFZR" fieldname="LWFB_JSFZR"    /></td>
    <td width="114" ><p align="center">安全考核证号 </p></td>
    <td width="163" ><input  maxlength="255" style="margin: 0;width: 91%" type="text" id="LWFB_JSFZR_AQKH" fieldname="LWFB_JSFZR_AQKH"    /></td>
  </tr>
  <tr>
    <td colspan="2" ><p align="center">单位分管安全负责人 </p></td>
    <td width="273" ><input  maxlength="255" style="margin: 0;width: 95%" type="text" id="LWFB_FGAQR" fieldname="LWFB_FGAQR"    /></td>
    <td width="114" ><p align="center">安全考核证号 </p></td>
    <td width="163" ><input  maxlength="255" style="margin: 0;width: 91%" type="text" id="LWFB_FGAQR_AQKH" fieldname="LWFB_FGAQR_AQKH"    /></td>
  </tr>
  <tr>
    <td colspan="2" ><p align="center">安全员 </p></td>
    <td width="273" ><input  maxlength="255" style="margin: 0;width: 95%" type="text" id="LWFB_AQY1" fieldname="LWFB_AQY1"    /></td>
    <td width="114" ><p align="center">安全考核证号 </p></td>
    <td width="163" ><input  maxlength="255" style="margin: 0;width: 91%" type="text" id="LWFB_AQY1_AQKH" fieldname="LWFB_AQY1_AQKH"    /></td>
  </tr>
  <tr>
    <td colspan="2" ><p align="center">安全员 </p></td>
    <td width="273" ><input  maxlength="255" style="margin: 0;width: 95%" type="text" id="LWFB_AQY2" fieldname="LWFB_AQY2"    /></td>
    <td width="114" ><p align="center">安全考核证号 </p></td>
    <td width="163" ><input  maxlength="255" style="margin: 0;width: 91%" type="text" id="LWFB_AQY2_AQKH" fieldname="LWFB_AQY2_AQKH"    /></td>
  </tr>
  <tr>
    <td colspan="2" ><p align="center">安全员 </p></td>
    <td width="273" ><input  maxlength="255" style="margin: 0;width: 95%" type="text" id="LWFB_AQY3" fieldname="LWFB_AQY3"    /></td>
    <td width="114" ><p align="center">安全考核证号 </p></td>
    <td width="163" ><input  maxlength="255" style="margin: 0;width: 91%" type="text" id="LWFB_AQY3_AQKH" fieldname="LWFB_AQY3_AQKH"    /></td>
  </tr>
</table>
  </div>
  
  <div id="div5" style="display: none;">
  <p align="center"><strong>五、危险性较大分部分项工程信息</strong></p>
  <table width="700"  border="1" cellpadding="0" cellspacing="0">
  <tr>
    <td width="167" ><p align="center">分部分项工程 </p></td>
    <td width="410" ><p align="center">内容 </p></td>
    <td width="115" ><p align="center">预计实施时间 </p></td>
  </tr>
  <tr>
    <td width="167" ><p align="center">一、基坑支护、降水及土方开挖工程 </p></td>
    <td width="410" ><p align="left">开挖深度<input  maxlength="255" style="margin: 0;width: 50px" type="text" fieldname="KAIWA_DEEP"  />m的基坑（槽）的土方开挖、支护、降水工程;
                  <input  maxlength="255" style="margin: 0;width: 50px" type="radio" fieldname="TFKWGC_Y" name="TFKWGC_Y"  kind="dic" src="YOUWU" title="on" disabled="disabled" style="display: none;" />&nbsp;开挖深度虽未超过5m，但地质条件、周围环境和地下管线复杂，或影响毗邻建筑（构筑）物安全的基坑(槽)的土方开挖、支护、降水工程。 </p></td>
    <td width="115" ><input readonly="readonly" class="Wdate" onClick="WdatePicker()"    data-date-format="YYYY-MM-DD"  maxlength="255" style="margin: 0;width: 87%" type="text" id="JK_TF_DATE" fieldname="JK_TF_DATE"   /></td>
  </tr>
   <tr>
    <td width="167" ><p align="center">二、模板工程及支撑体系</p></td>
    <td width="410" ><p align="left">
                 <input type="radio" fieldname="GLGJ_Y" name="GLGJ_Y" kind="dic" src="YOUWU" />
                 &nbsp;各类工具式模板工程：包括打模板、滑模、爬模、飞模工程;混凝土模板支撑工程：
                 搭设高度<input  maxlength="255" style="margin: 0;width: 50px" type="text" fieldname="TSGD"  />m；
                 搭设跨度<input  maxlength="255" style="margin: 0;width: 50px" type="text" fieldname="TSKD"  />m，
                 施工总载荷<input  maxlength="255" style="margin: 0;width: 50px" type="text" fieldname="SGZZH"  />KN/m2，
                 集中线载荷<input  maxlength="255" style="margin: 0;width: 50px" type="text" fieldname="JZXZH"  />KN/ m2；
     <input type="radio" fieldname="ZHICHENGGC_Y" name="ZHICHENGGC_Y" kind="dic" src="YOUWU" />&nbsp;高度大于支撑水平投影宽度且相对独立无联系构件的混凝土模板支撑工程;
                承重支撑系统：用于钢结构安装等满堂支撑体系，承受单点集中荷载<input  maxlength="255" style="margin: 0;width: 50px" type="text" fieldname="CSDDJZZH"  />kg。
                                   
                                   </td>
    <td width="115" ><input readonly="readonly" class="Wdate" onClick="WdatePicker()"  data-date-format="YYYY-MM-DD"  maxlength="255" style="margin: 0;width: 87%" type="text" id="MBGCTX_DATE" fieldname="MBGCTX_DATE"    /></td>
  </tr>
   <tr>
    <td width="167" ><p align="center">三、起重吊装安装拆卸工程 </p></td>
    <td width="410" ><p align="left">
                                              采用非常规起重设备、方法，单件起吊重量 <input  maxlength="255" style="margin: 0;width: 50px" type="text" fieldname="DJQDZL"  />KN的起重吊装工程：
                                              起重量<input  maxlength="255" style="margin: 0;width: 50px" type="text" fieldname="QZL"  />KN的起重设备安装工程、
                                              安装高度<input  maxlength="255" style="margin: 0;width: 50px" type="text" fieldname="AZGD"  />m的起重设备的拆除工程。                               
                                              </p></td>
                                              <td width="115" ><input readonly="readonly" class="Wdate" onClick="WdatePicker()"    data-date-format="YYYY-MM-DD"  maxlength="255" style="margin: 0;width: 87%" type="text" id="QZDZAZCX_DATE" fieldname="QZDZAZCX_DATE"    /></td>
  </tr> <tr>
    <td width="167" ><p align="center">四、脚手架工程</p></td>
    <td width="410" ><p align="left">搭设高度<input  maxlength="255" style="margin: 0;width: 50px" type="text" fieldname="TS_GD"  />m的落地式钢管脚手架工程；
    提升高度<input  maxlength="255" style="margin: 0;width: 50px" type="text" fieldname="TISHENG_HEIGHT"  />m的附着式整体和分片提升脚手架工程；
    架体高度<input  maxlength="255" style="margin: 0;width: 50px" type="text" fieldname="JIATI_HEIGHT"  />m的悬挑脚手架工程；
    <input type="radio" fieldname="DLJSJGC_Y" name="DLJSJGC_Y" kind="dic" src="YOUWU" />&nbsp;吊篮脚手架工程；
  <input type="radio" fieldname="ZZYCPT_Y" name="ZZYCPT_Y" name="ZZYCPT_Y" kind="dic" src="YOUWU" />&nbsp;  自制卸料平台、移动操作平台工程；
  <input type="radio" fieldname="JSJGC_Y" name="JSJGC_Y" kind="dic" src="YOUWU" />&nbsp;新型及异型脚手架工程。
    </p></td>
    <td width="115" ><input readonly="readonly" class="Wdate" onClick="WdatePicker()"    data-date-format="YYYY-MM-DD"  maxlength="255" style="margin: 0;width: 87%" type="text" id="JSJGC_DATE" fieldname="JSJGC_DATE"    /></td>
  </tr>
   <tr>
    <td width="167" ><p align="center">五、拆除、爆破工程 </p></td>
    <td width="410" ><p align="left"><input type="radio" fieldname="CCGC_Y" name="CCGC_Y" kind="dic" src="YOUWU" />&nbsp;建筑物、构筑物拆除工程；
    <input type="radio" fieldname="BPCCGC_Y" name="BPCCGC_Y" kind="dic" src="YOUWU" />&nbsp;采用爆破拆除工程；
    <input type="radio" fieldname="GJWCCGC_Y" name="GJWCCGC_Y" kind="dic" src="YOUWU" />&nbsp;码头、桥梁、高架、烟囱、水塔或拆除中容易引起有毒有害气（液）体或粉尘扩散、易燃易爆事故发生的特殊建、构筑物拆除工程；
    <input type="radio" fieldname="YXCCGC_Y" name="YXCCGC_Y" kind="dic" src="YOUWU" />&nbsp;可能影响行人、交通、电力设施、通讯设施或其他建、构筑物安全的拆除工程；
    <input type="radio" fieldname="BFJZCCGC_Y" name="BFJZCCGC_Y" kind="dic" src="YOUWU" />&nbsp;文物保护建筑、优秀历史建筑历史文化风貌区控制范围的拆除工程。</p></td>
    <td width="115" ><input readonly="readonly" class="Wdate" onClick="WdatePicker()"    data-date-format="YYYY-MM-DD" maxlength="255" style="margin: 0;width: 87%" type="text" id="CC_BP_DATE" fieldname="CC_BP_DATE"    /></td>
  </tr>
  <tr>
    <td width="167" ><p align="center">六、其他</p></td>
    <td width="410" ><p align="left">施工高度<input  maxlength="255" style="margin: 0;width: 50px" type="text" fieldname="QT_SG_HEIGHT"  />m的建筑幕墙安装工程；
     跨度<input  maxlength="255" style="margin: 0;width: 50px" type="text" fieldname="QT_KD_GJG"  />m的钢结构安装工程；跨度<input  maxlength="255" style="margin: 0;width: 50px" type="text" fieldname="QT_KD_SMJG"  />m的网架和索模结构安装工程。
     </p></td>
     
    <td width="115" ><input readonly="readonly" class="Wdate" onClick="WdatePicker()"    data-date-format="YYYY-MM-DD" maxlength="255" style="margin: 0;width: 87%" type="text" id="QTGC_DATE" fieldname="QTGC_DATE"    /></td>
  </tr>
</table>
<p align="center"><strong>　六、单位工程明细表</strong></p>
<p align="right" style="width: 700px"><strong>　<!-- 
<button id="btnNextAdd" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">添加行</button>
	  		<button id="btnNextSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">保存</button>
	  		 -->
	  		</strong></p>
<div style="overflow-x: auto; overflow-y: auto; width:700px;">
    <table id="table" border="1" align="center" width="1880px" >
    <thead>
  <tr>
    <td width="129" height="40"><div align="center">单位工程名称</div></td>
    <td width="40"><div align="center">建设<br />
    性质</div></td>
    <td width="108"><div align="center">建筑物类型</div></td>
    <td width="105"><div align="center">结构类型</div></td>
    <td width="77"><div align="center">地基类型</div></td>
    <td width="77"><div align="center">基础类型</div></td>
    <td width="109"><div align="center">总建筑面积</div></td>
    <td width="93"><div align="center">地上面积</div></td>
    <td width="92"><div align="center">地下面积</div></td>
    <td width="42"><div align="center">地上层数</div></td>
    <td width="39"><div align="center">地下层数</div></td>
    <td width="117"><div align="center">地上结构类型</div></td>
    <td width="110"><div align="center">地下结构类型</div></td>
    <td width="39"><div align="center">跃层层数</div></td>
    <td width="91"><div align="center">最大高度</div></td>
    <td width="78"><div align="center">装修标准</div></td>
    <td width="84"><div align="center">预算造价</div></td>
    <td width="87"><div align="center">工期</div></td>
  </tr>
  </thead>
  <tbody id="dt1"></tbody>
</table>
</div>
  
  </div>
  
  <div style="display: none;" id="div6">
  <p align="center"><strong>施工现场文明施工申报表
</strong></p>
  <table border="1" cellspacing="0" cellpadding="0">
  <tr>
    <td colspan="5" ><p align="right">锡新建管区(2009)单第_号 </p></td>
  </tr>
  <tr>
    <td colspan="2" ><p align="center">建设单位名称 </p></td>
    <td width="210" ><input  style="margin: 0;width: 93%" type="text" id="WMSGCOMPANY_NAME" fieldname="WMSGCOMPANY_NAME" setname = "COMPANY_NAME"   /></td>
    <td width="128" ><p align="center">总承包单位名称<br />
      (盖章) </p></td>
    <td width="217" ><input  style="margin: 0;width: 93%" type="text" id="ZB_DW" fieldname="ZB_DW"    /></td>
  </tr>
  <tr>
    <td width="64" rowspan="4" >
      <p align="center">工程概况 </p></td>
    <td width="94" ><p align="center">工程地点 </p></td>
    <td width="210" ><input  style="margin: 0;width: 93%" type="text" id="WMSGGC_DIDIAN" fieldname="WMSGGC_DIDIAN" setname = "JIANSHE_DIZHI"   /></td>
    <td width="128" ><p align="center">投资性质 </p></td>
    <td width="217" ><input  style="margin: 0;width: 93%" type="text" id="TZ_XZ" fieldname="TZ_XZ" setname = "COMPANY_PROPERTY_SV"   /></td>
  </tr>
  <tr>
    <td width="94" ><p align="center">建筑面积 </p></td>
    <td width="210" ><input  style="margin: 0;width: 93%" type="text" id="JZ_MJ1" fieldname="JZ_MJ1" setname = "MIANJI"   /></td>
    <td width="128" ><p align="center">项目名称 </p></td>
    <td width="217" ><input  style="margin: 0;width: 93%" type="text" id="PROJECTS_NAME" fieldname="PROJECTS_NAME" setname = "GONGCHENG_NAME"   /></td>
  </tr>
  <tr>
    <td width="94" ><p align="center">设计单位名称 </p></td>
    <td width="210" ><input  style="margin: 0;width: 93%" type="text" id="WMSGSJ_DW_NAME" fieldname="WMSGSJ_DW_NAME"    /></td>
    <td width="128" ><p align="center">监理单位名称 </p></td>
    <td width="217" ><input  style="margin: 0;width: 93%" type="text" id="WMSGJL_DW_NAME" fieldname="WMSGJL_DW_NAME" setname = "JL_COMPANY_NAME"   /></td>
  </tr>
  <tr>
    <td width="94" ><p align="center">工程造价 </p></td>
    <td width="210" ><input  style="margin: 0;width: 93%" type="text" id="GC_ZJ1" fieldname="GC_ZJ1" setname = "JINE"   /></td>
    <td width="128" ><p align="center">开竣工日期 </p></td>
    <td width="217" ><input  style="margin: 0;width: 93%" type="text" id="BEGIN_END_DATE" fieldname="BEGIN_END_DATE"    /></td>
  </tr>
  <tr>
    <td width="64" rowspan="3" >
      <p align="center">施工单位人员配备 </p></td>
    <td width="94" ><p align="center">公司负责人<br />
      (姓名及电话) </p></td>
    <td width="210" ><input  style="margin: 0;width: 93%" type="text" id="COMPANY_FZR" fieldname="COMPANY_FZR"    /></td>
    <td width="128" ><p align="center">现场施工员<br />
      (姓名及手机) </p></td>
    <td width="217" ><input  style="margin: 0;width: 93%" type="text" id="XC_SGY" fieldname="XC_SGY"    /></td>
  </tr>
  <tr>
    <td width="94" ><p align="center">手续办理人<br />
      (姓名及手机) </p></td>
    <td width="210" ><input  style="margin: 0;width: 93%" type="text" id="SX_BLR" fieldname="SX_BLR"    /></td>
    <td width="128" ><p align="center">文明安全管理员<br />
      (姓名及手机) </p></td>
    <td width="217" ><input  style="margin: 0;width: 93%" type="text" id="AQ_GLY" fieldname="AQ_GLY"    /></td>
  </tr>
  <tr>
    <td width="94" ><p align="center">项目经理<br />
      (姓名及手机) </p></td>
    <td width="210" ><input  style="margin: 0;width: 93%" type="text" id="XMJL" fieldname="XMJL"    /></td>
    <td width="128" ><p align="center">技术负责人<br />
      (姓名及手机) </p></td>
    <td width="217" ><input  style="margin: 0;width: 93%" type="text" id="JSFZR" fieldname="JSFZR"    /></td>
  </tr>
  <tr>
    <td width="64" rowspan="5" >
      <p align="center">建造师(项目经理) </p></td>
    <td width="94" >现场围栏</td>
    <td width="210" colspan="2">工地四周必须设置连续、整齐、牢固的围墙，墙高1.8米可书写规范的宣传标语，入口设置钢结构龙门架</td>
    <td width="217" ><textarea rows="2" style="margin: 0;width: 93%" fieldname="XCWL"  ></textarea></td>
  </tr>
  <tr>
     <td width="94" >现场标牌</td>
    <td width="210" colspan="2">在大门侧醒目处设置新区文明管理标志牌，在入口
道路侧设置工地宣传栏，布置五牌一图</td>
    <td width="217" ><textarea rows="2" style="margin: 0;width: 93%" fieldname="XC_BP"  ></textarea></td>
  </tr>
  <tr>
     <td width="94" >门前三包</td>
    <td width="210" colspan="2">工地出入口及主要道路地坪硬化，门口设置冲洗设
施（冲洗池、高压水枪）有效组织排水系统；建立
门卫及门卫制度</td>
    <td width="217" ><textarea rows="3" style="margin: 0;width: 93%" fieldname="MQ_SB"  ></textarea></td>
  </tr>
  <tr>
    <td width="94" >人员配备</td>
    <td width="210" colspan="2">项目经理和现场管理人员必须定人到岗到位</td>
    <td width="217" ><textarea rows="2" style="margin: 0;width: 93%" fieldname="RYPB"  ></textarea></td>
  </tr>
  <tr>
     <td width="94" >办公及宿
舍设施</td>
    <td width="210" colspan="2">设立固定独立的办公及生活宿舍区域，宿舍区与施
工作业区有明显分隔，宿舍区地坪硬化，整洁卫生
，食堂生熟分开，设置纱门纱窗，事员要有健康证
，厕所有水冲设备，设立化粪池，与区环卫部门联
系定时抽运</td>
    <td width="217" ><textarea rows="5" style="margin: 0;width: 93%" fieldname="BG_SS"  ></textarea></td>
  </tr>
  <tr>
    <td colspan="2" ><p align="center">施工现场总平面图 </p></td>
    <td colspan="3" ><input  style="margin: 0;width: 97.2%" type="text" id="ZPMT" fieldname="ZPMT"    /></td>
  </tr>
  <tr>
    <td width="64" ><p align="center">备注 </p></td>
    <td colspan="4" ><textarea rows="3" style="margin: 0;width: 97.5%" fieldname="WMSGBEIZHU"  ></textarea></td>
  </tr>
</table>
 <p align="left" style="width: 725px">说明：①现场文明施工临时设施按申报要求落实后建管办验收核发临时设施验收合格书
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;②在未领取合格书的情况下不得进入现场正式施工</p>
  </div>
  
  <div style="display: none;" id="div7">
  <p align="center"><strong>施工现场安全生产条件认证记录

</strong></p>
<table border="1" style="width: 725px;" cellspacing="0" cellpadding="0">
  <tr>
    <td width="39" ><p align="center">序号 </p></td>
    <td width="182" ><p align="right">施工现场安全条件 </p></td>
    <td width="246" ><p align="center">处理情况 </p></td>
    <td width="248" ><p align="left">备注 </p></td>
  </tr>
  <tr>
    <td width="39" ><p align="center">1</p></td>
    <td width="182" ><p align="right">相邻建筑物、构筑物工程 </p></td>
    <td width="246" ><input  maxlength="255" style="margin: 0;width: 94%" type="text" id="XL_JZ" fieldname="XL_JZ"    /></td>
    <td width="248" ><input  maxlength="255" style="margin: 0;width: 94%" type="text" id="BEIZHU2" fieldname="BEIZHU2"    /></td>
  </tr>
  <tr>
    <td width="39" ><p align="center">2</p></td>
    <td width="182" ><p align="right">地下工程 </p></td>
    <td width="246" ><input  maxlength="255" style="margin: 0;width: 94%" type="text" id="DX_GC" fieldname="DX_GC"    /></td>
    <td width="248" ><input  maxlength="255" style="margin: 0;width: 94%" type="text" id="BUZHOU3" fieldname="BUZHOU3"    /></td>
  </tr>
  <tr>
    <td width="39" ><p align="center">3</p></td>
    <td width="182" ><p align="right">施工周围高压线(地面管线) </p></td>
    <td width="246" ><input  maxlength="255" style="margin: 0;width: 94%" type="text" id="SG_ZWGY" fieldname="SG_ZWGY"    /></td>
    <td width="248" ><input  maxlength="255" style="margin: 0;width: 94%" type="text" id="BUZHOU4" fieldname="BUZHOU4"    /></td>
  </tr>
  <tr>
    <td width="39" ><p align="center">4</p></td>
    <td width="182" ><p align="right">施工现场管线 </p></td>
    <td width="246" ><input  maxlength="255" style="margin: 0;width: 94%" type="text" id="SG_XC" fieldname="SG_XC"    /></td>
    <td width="248" ><input  maxlength="255" style="margin: 0;width: 94%" type="text" id="BEIZHU5" fieldname="BEIZHU5"    /></td>
  </tr>
  <tr>
    <td width="39" ><p align="center">5</p></td>
    <td width="182" ><p align="right">施工现场问题 </p></td>
    <td width="246" ><input  maxlength="255" style="margin: 0;width: 94%" type="text" id="SG_XCWT" fieldname="SG_XCWT"    /></td>
    <td width="248" ><input  maxlength="255" style="margin: 0;width: 94%" type="text" id="BUZHOU6" fieldname="BUZHOU6"    /></td>
  </tr>
  <tr>
    <td width="39" ><p align="center">6</p></td>
    <td width="182" ><p align="right">5米以上深基坑 </p></td>
    <td width="246" ><input  maxlength="255" style="margin: 0;width: 94%" type="text" id="YISHANG" fieldname="YISHANG"    /></td>
    <td width="248" ><input  maxlength="255" style="margin: 0;width: 94%" type="text" id="BEIZHU7" fieldname="BEIZHU7"    /></td>
  </tr>
  <tr>
    <td width="39" ><p align="center">7</p></td>
    <td width="182" ><p align="right">其它 </p></td>
    <td width="246" ><input  maxlength="255" style="margin: 0;width: 94%" type="text" id="QITA" fieldname="QITA"    /></td>
    <td width="248" ><input  maxlength="255" style="margin: 0;width: 94%" type="text" id="BEIZHU8" fieldname="BEIZHU8"    /></td>
  </tr>
  <tr>
    <td width="39" ></td>
    <td width="182" ><p align="right">说明 </p></td>
    <td colspan="2" ><input  maxlength="255" style="margin: 0;width: 97%" type="text" id="SHUOMING" fieldname="SHUOMING"    /></td>
  </tr>
  <tr>
    <td colspan="2" ><p align="center">建设单位（盖章）： </p></td>
    <td width="246" ><p align="center">监理单位（盖章）： </p></td>
    <td width="248" ><p align="center">监施工单位（盖章）： </p></td>
  </tr>
  <tr>
    <td colspan="2" ><br/><br/><br/><br/></td>
    <td width="246" ></td>
    <td width="248" ></td>
  </tr>
  <tr>
    <td colspan="2" ><p align="center">现场安全负责人： </p></td>
    <td width="246" ><p align="center">监理工程师： </p></td>
    <td width="248" ><p align="center">项目经理： </p></td>
  </tr>
  <tr>
    <td colspan="2" ><br/><br/><br/><br/></td>
    <td width="246" ></td>
    <td width="248" ></td>
  </tr>
  <tr>
    <td colspan="2" ><p align="center">验收日期： </p></td>
    <td width="246" ><p align="center">验收日期： </p></td>
    <td width="248" ><p align="center">验收日期： </p></td>
  </tr>
  <tr>
    <td colspan="2" ><br/><br/><br/><br/></td>
    <td width="246" ></td>
    <td width="248" ></td>
  </tr>
</table>
  </div>
  
  </div>
</form>


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