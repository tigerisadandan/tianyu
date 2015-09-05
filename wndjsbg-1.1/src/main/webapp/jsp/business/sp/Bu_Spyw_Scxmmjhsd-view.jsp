
<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/sp/xAlert.css"> </LINK>
<!-- 
<script type="text/javascript" src="${pageContext.request.contextPath }/js/base/jquery.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/base/LockingTable.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/base/bootstrap.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/base/jsBruce.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/Form2Json.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/convertJson.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/combineQuery.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/default.js?version=20131206"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/TabList.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/bootstrap-validation.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/loadFields.js?version=20131206"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common.js"></script> -->
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
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/sp/css/bootstrap.css?version=20131201"></LINK>


<title></title>
<%
	//String id=request.getParameter("id");
    String id=(String)request.getAttribute("id");
    String projects_uid=(String)request.getAttribute("projects_uid");
	String js_company_uid=(String)request.getAttribute("js_company_uid");
	
%>
<script src="${pageContext.request.contextPath}/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/sp/buSpywScxmmjhsdController/";
var controllerfile= "${pageContext.request.contextPath }/controller/fileController/";
var contextPath="${pageContext.request.contextPath }";
var type ="";
var id="<%=id%>";
var projects_uid="<%=projects_uid%>";
var js_company_uid="<%=js_company_uid%>";
//页面初始化
$(function() {
	$("#pubAlert").hide();
	//init();
	$("#SCXMMJHSD_UID_QUERY").val(id);
	query();
	 $("#btnClose").click(function(){
		    window.close();
		    });
	//按钮绑定事件(保存)
	$("#btnSave2").click(function() {
        $.ajax({
			url : controllername + "download?uid="+$("#SCXMMJHSD_UID_QUERY").val(),
			cache : false,
			async : false,
			dataType : "json",
			type : 'post',
			success : function(response) {
        			window.location.href = controllerfile+"download2?path="+response;
			 } 
		    });
	});
});

function query(){ 
			//查询表单赋值       
			var data = combineQuery.getQueryCombineData(scxmmjhsdForm,frmPost);
			var data1 = { 	msg : data};
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
					var resultobj = defaultJson.dealResultJson(res);
					
$("#gs").html(resultobj.DWMC);
$("#xm").html(resultobj.XMMC);
$("#jd").html(resultobj.SD);
$("#mj").html(resultobj.JZMJ);
$("#BCSF").html(resultobj.BCSF);
$("#SKR").html(resultobj.SKR);
$("#KHYH").html(resultobj.KHYH);
$("#SKZH").html(resultobj.SKZH);
$("#JBR").html(resultobj.JBR);
$("#SHR").html(resultobj.SHR);
$("#RQ").html(resultobj.RQ);
$("#BH").html(resultobj.BH);
$("#DESCRIBE").html(resultobj.DESCRIBE);
if(resultobj.JBR==""){
$("#JBR").html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
}
if(resultobj.SHR==""){
$("#SHR").html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
}
if(resultobj.BH=="NO"){
$("#BH").html("NO&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
}
if(resultobj.RQ==""){
var date = new Date(); //日期对象
var now = "";
now = date.getFullYear()+"年"; //读英文就行了
now = now + (date.getMonth()+1)+"月"; //取月的时候取的是当前月-1如果想取当前月+1就可以了
now = now + date.getDate()+"日";
$("#RQ").html(now);
}else{
var date = new Date(resultobj.RQ); //日期对象
var now = "";
now = date.getFullYear()+"年"; //读英文就行了
now = now + (date.getMonth()+1)+"月"; //取月的时候取的是当前月-1如果想取当前月+1就可以了
now = now + date.getDate()+"日";
$("#RQ").html(now);
}

$("#gs2").html(resultobj.DWMC);
$("#xm2").html(resultobj.XMMC);
$("#jd2").html(resultobj.SD);
$("#mj2").html(resultobj.JZMJ);
$("#BCSF2").html(resultobj.BCSF);
$("#SKR2").html(resultobj.SKR);
$("#KHYH2").html(resultobj.KHYH);
$("#SKZH2").html(resultobj.SKZH);
$("#JBR2").html(resultobj.JBR);
$("#SHR2").html(resultobj.SHR);
$("#RQ2").html(resultobj.RQ);
$("#BH2").html(resultobj.BH);
$("#DESCRIBE2").html(resultobj.DESCRIBE);
if(resultobj.JBR==""){
$("#JBR2").html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
}
if(resultobj.SHR==""){
$("#SHR2").html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
}
if(resultobj.BH=="NO"){
$("#BH2").html("NO&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
}
if(resultobj.RQ==""){
var date = new Date(); //日期对象
var now = "";
now = date.getFullYear()+"年"; //读英文就行了
now = now + (date.getMonth()+1)+"月"; //取月的时候取的是当前月-1如果想取当前月+1就可以了
now = now + date.getDate()+"日";
$("#RQ2").html(now);
}else{
var date = new Date(resultobj.RQ); //日期对象
var now = "";
now = date.getFullYear()+"年"; //读英文就行了
now = now + (date.getMonth()+1)+"月"; //取月的时候取的是当前月-1如果想取当前月+1就可以了
now = now + date.getDate()+"日";
$("#RQ2").html(now);
}
				} 
			});

		}

function isnum(card){  
	      var reg =/^(\d+\.\d{1,2}|\d+)$/;
		  
		  if($("#BCSF").val() == null || $("#BCSF").val() == ""){
			  alert("请输入金额!");
			  return false;
			  }
		  else{
		     if(reg.test($(card).val()) == false)  
		     {  
		       alert("输入金额不合法!");
		       $("#BCSF").attr("style", "width: 90%;text-align: right;color:red;");
		       return  false;  
		     }else{
		    	 $("#BCSF").attr("style", "width: 90%;text-align: right;");
			   return true;
		        }
		  }
}
//页面默认参数
function init(){ 
	if(id!="null"&&id!=""){
		query();
	}
	else{
	type="insert";
	var gsobj=getCompany(); //公司
	alert(gsobj.COMPANY_NAME);
	var xmobj  =getXmxx();//项目
	alert(xmobj.PROJECTS_NAME);
	//var jsxmobj=getJsXmxx();//JsProject
	//var lixiang=getJsLxxx(); //lixiang
	
	//setobj(gsobj,xmobj,jsxmobj,lixiang);
	}
	//生成json串
//	var data = combineQuery.getQueryCombineData(queryForm,frmPost);
	//调用ajax插入
//	defaultJson.doQueryJsonList(controllername+"query",data,);
	
<%--	if(type == "insert"){--%>
<%--	}else if(type == "update" || type == "detail"){--%>
<%--		var tempJson;--%>
<%--		if(navigator.userAgent.indexOf("Firefox")>0) {--%>
<%--			var rowValue = $(parent.frames["menuiframe"]).contents().find("#resultXML").val();--%>
<%--			tempJson = eval("("+rowValue+")");--%>
<%--		}else{--%>
<%--			var rowValue = $(parent.frames["menuiframe"].document).find("#resultXML").val();--%>
<%--			tempJson = eval("("+rowValue+")");--%>
<%--		}--%>
<%--		//表单赋值--%>
<%--		$("#$("#buSpywZzhydjForm")").setFormValues(tempJson);--%>
<%--	}--%>
}

function getXinxi(){
$("#PROJECTS_UID").val(projects_uid);
$("#JS_COMPANY_UID").val(js_company_uid);
var jscompany=getjscompany();
var projects=getprojects();
$("#PROJECT_UID").val(projects.PROJECT_UID);
var jsproject=getjsproject();
$("#gs").html(jscompany.COMPANY_NAME);
$("#xm").html(projects.PROJECTS_NAME);
$("#jd").html(projects.QUYU_SV);
$("#mj").html(projects.ZHANDI_MIANJI);
$("#JZMJ").val(projects.ZHANDI_MIANJI);
$("#DWMC").val(jscompany.COMPANY_NAME);
$("#XMMC").val(projects.PROJECTS_NAME);
$("#SD").val(projects.QUYU_SV);
var mey=105*projects.ZHANDI_MIANJI;
$("#BCSF").val(mey.toFixed(2));
}


//新增--
function getjscompany(){ //根据 jscompanyForm 查询 企业信息
	var obj ;
	var data = combineQuery.getQueryCombineData(jscompanyForm,frmPost);
	var data1 = {
		msg : data
	};
	$.ajax({
		url : "/wndjsbg/project/jsCompanyController?query",
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
		url : "/wndjsbg/project/jsProjectController?query2",
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
		url : "/wndjsbg/project/projectsController?query",
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
//项目信息查询
doquery_xmxx= function(actionName, data1,tablistID){
    var success  = true;
	var data = {
		msg : data1
	};
	$.ajax({
		url : actionName,
		data : data,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(result) {
			//查询清空结果
			var returnMsg = result.msg;
			$("#queryResult").val(result.msg);
			success = true;
		}
	});
    return success;	
};


//点击行事件
function tr_click(obj){
	//alert(JSON.stringify(obj));
	$("#buSpywZzhydjForm").setFormValues(obj);
}

//默认年度
function getNd(){
	$("#QZFRQ").val(new Date().getFullYear());
}
//选中项目名称弹出页
function selectXm(){
	$(window).manhuaDialog({"title":"","type":"text","content":"${pageContext.request.contextPath }/jsp/business/zjgl/xmcx.jsp","modal":"2"});
}
//弹出区域回调
getWinData = function(data){
	$("#XMMC").val(JSON.parse(data).XMMC);
	$("#XMBH").val(JSON.parse(data).XMBH);
	$("#GC_TCJH_XMXDK_ID").val(JSON.parse(data).GC_TCJH_XMXDK_ID);
};

//详细信息
function rowView(index){
	var obj = $("#buSpywZzhydjList").getSelectedRowJsonByIndex(index);
	var xmbh = eval("("+obj+")").XMBH;
	$(window).manhuaDialog(xmscUrl(xmbh));
}

//赋值 企业 项目信心
function setobj(gsobj,xmobj,jsxmobj,lixiang){
$("#JSDW").val(gsobj.COMPANY_NAME);
$("#LXR_JSDW").val(gsobj.LIANXIREN);
$("#DH_JSDW").val(gsobj.LIANXIREN_MOBILE);

$("#PWWH").val(xmobj.LIXIANG_CODE);
$("#GCNAME").val(xmobj.PROJECTS_NAME);
$("#DATE_KG").val(xmobj.PLAN_KG_DATE);
$("#DATE_JG").val(xmobj.JG_DATE);

$("#JSDD").val(xmobj.JIANSHE_DIZHI);
$("#JSMJ").val(xmobj.JIANZHU_MIANJI);

$("#PWWH").val(lixiang.code);
}
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
	        <INPUT type="" class="cc-input1" kind="SCXMMJHSD_UID" id="QID" fieldname="YWLZ_UID"
								 operation="=" />
	        </TD>
			<TD class="right-border bottom-border">
			</TD>
        </TR>
      </table>
      </form>
    <div style="height:5px;"></div>
    <div class="overFlowX">
	
	</div>
	</div>
	<div style="height:5px;"></div>
	<div class="B-small-from-table-autoConcise">
	    <span style="float:right">
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
	  		<button id="btnSave2"  class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">打印</button>
      	</span><br/>
	     
     	  <form method="post" id="scxmmjhsdForm"  >
	        <input   type="hidden" id="SCXMMJHSD_UID_QUERY" fieldname="SCXMMJHSD_UID" name="SCXMMJHSD_UID" operation="="/>
     	 </form>
        	
     <form method="post" id="buSpywZzhydjForm"  > 
          <h4 class="title" align="center">建筑管理办公室征收三产配套费收费通知单</h4>
          <div align="center" >  <span style="font-size: 17px;">兹有<u><span id="gs"></span></u>建设的<u><span id="xm"></span></u>（<u><span id="jd"></span></u>）工程项目，总建筑面积为 <u><span id="mj"></span></u> ㎡。</span><div style="width: 800px;" align="left">本次应收取配套费如下：</div></div>
         
            <input type="hidden" id="SCXMMJHSD_UID" fieldname="SCXMMJHSD_UID" name = "SCXMMJHSD_UID"/>
	  	  <!-- <input type="hidden" id="GC_TCJH_XMXDK_ID" fieldname="XMID" name = "XMID"/>
	  	      <input type="hidden" id="YWLZ_UID" fieldname="YWLZ_UID" value="0" name = "YWLZ_UID"/> -->
	  	      <input type="hidden" id="LZBZ_WJ_UID" fieldname="LZBZ_WJ_UID" name = "LZBZ_WJ_UID"/>
	<div align="center">  
  <table  style="width: 800px;font-size: 17px;"  border="1" class="cc-alert-table" > 
  <input   id="JZMJ" type="hidden"  fieldname="JZMJ" name = "JZMJ"  />
  <input   id="DWMC" type="hidden"  fieldname="DWMC" name = "DWMC"  />
  <input   id="XMMC" type="hidden"  fieldname="XMMC" name = "XMMC"  />
    <input   id="SD" type="hidden"  fieldname="SD" name = "SD"  />
  <tr style="height: 35px;"> 
    <td width="50%" align="center" colspan="2">基 础 设 施 配 套 费</td>  
    <td width="50%" align="center" colspan="2">本次收费（金额）</td>  
  
  </tr>
  <tr style="height: 35px;">
   <td width="50%" style="text-align: center;" colspan="2" >105元/㎡</td>  
    <td width="50%" colspan="2" style="text-align: center;" ><span id="BCSF"></span>元</td>  
  </tr>
  <tr style="height: 35px;"> 
    <td width="25%" >收款人</td>  
    <td width="75%" colspan="3" ><span id="SKR"></span></td>  
  </tr>
  <tr style="height: 35px;"> 
    <td width="25%" >开户行</td>  
    <td width="75%" colspan="3" ><span id="KHYH"></span></td> 
  </tr>
  <tr style="height: 35px;" > 
     <td width="25%" >收款账户</td>  
    <td width="75%" colspan="3" ><span id="SKZH"></span></td> 
  </tr>
</table><br/>
<div align="right" style="width: 800px;font-size: 17px;height: 35px;"> 
经办人<u><span id="JBR"></span></u>&nbsp;
审核人<u><span id="SHR"></span></u>&nbsp;</div>
<table  style="width: 800px;font-size: 17px;"  border="1" class="cc-alert-table" > 
<tr style="height: 35px;;"> 
    <td style="border-bottom: 0px">补充说明&nbsp;：<span id="DESCRIBE"></span></td>
  </tr>
  <tr style="height: 35px;"> 
    <td align="right" style="border-top: 0px;"><span id="RQ"><b><i></span>&nbsp;&nbsp;编号：NO<u><span id="BH"></span></u></i></b>&nbsp;</td>
  </tr>
</table>
      </form>
      <hr style="width:800px; wBORDER-BOTTOM-STYLE: dotted; BORDER-LEFT-STYLE: dotted; BORDER-RIGHT-STYLE: dotted; BORDER-TOP-STYLE: dotted" color=#111111 size=1>
      
</div>
      <form method="post"  > 
          <h4 class="title" align="center">建筑管理办公室征收三产配套费收费回单</h4>
          <div align="center" >  <span style="font-size: 17px;">兹有<u><span id="gs2"></span></u>建设的<u><span id="xm2"></span></u>（<u><span id="jd2"></span></u>）工程项目，总建筑面积为 <u><span id="mj2"></span></u> ㎡。</span><br/><div style="width: 800px;" align="left">本次应收取配套费如下：</div></div>
         
	<div align="center">  
  <table  style="width: 800px;font-size: 17px;"  border="1" class="cc-alert-table" > 
  <tr style="height: 35px;"> 
    <td width="50%" align="center" colspan="2">基 础 设 施 配 套 费</td>  
    <td width="50%" align="center" colspan="2">本次收费（金额）</td>  
  
  </tr>
  <tr style="height: 35px;">
   <td width="50%" style="text-align: center;" colspan="2" >105元/㎡</td>  
    <td width="50%" colspan="2" style="text-align: center;" ><span id="BCSF2"></span>元</td>  
  </tr>
  <tr style="height: 35px;"> 
    <td width="25%" >收款人</td>  
    <td width="75%" colspan="3" ><span id="SKR2"></span></td>  
  </tr>
  <tr style="height: 35px;"> 
    <td width="25%" >开户行</td>  
    <td width="75%" colspan="3" ><span id="KHYH2"></span></td> 
  </tr>
  <tr style="height: 35px;" > 
     <td width="25%" >收款账户</td>  
    <td width="75%" colspan="3" ><span id="SKZH2"></span></td> 
  </tr>
</table><br/>
<div align="right" style="width: 800px;font-size: 17px;height: 35px;"> 
经办人<u><span id="JBR2"></span></u>&nbsp;
审核人<u><span id="SHR2"></span></u>&nbsp;</div>
<table  style="width: 800px;font-size: 17px;"  border="1" class="cc-alert-table" > 
<tr style="height: 35px;;"> 
    <td style="border-bottom: 0px">补充说明&nbsp;：<span id="DESCRIBE2"></span></td>
  </tr>
  <tr style="height: 35px;"> 
    <td align="right" style="border-top: 0px;"><span id="RQ2"><b><i></span>&nbsp;&nbsp;编号：NO<u><span id="BH2"></span></u></i></b>&nbsp;</td>
  </tr>
</table>
</div>
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