
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
	String filename=(String)request.getAttribute("filename");
	String ywlzid=(String)request.getAttribute("ywlzid");
%>
<script src="${pageContext.request.contextPath}/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/bzwj/buSpywJyfwfsfJsgcjktzdController/";
var controllerfile= "${pageContext.request.contextPath }/controller/fileController/";
var contextPath="${pageContext.request.contextPath }";
var controllernameWjCbfa= "${pageContext.request.contextPath }/sp/buSpywCbfaspController/";

var type ="";
var id="<%=id%>";
var projects_uid="<%=projects_uid%>";
var js_company_uid="<%=js_company_uid%>";
var filename="<%=filename %>";
var ywlz_uid="<%=ywlzid %>"; 

//页面初始化
$(function() {
	$("#pubAlert").hide();
	
	init();
	getCbr();
	var parent = window.opener;
	var LZBZ_WJ_UID= parent.getLzbz_uid();
	$("#LZBZ_WJ_UID").val(LZBZ_WJ_UID);
	$("#QUERY_LZBZ_WJ_UID").val(LZBZ_WJ_UID);
	getXinxi();
	query();
	 $("#btnClose").click(function(){
		    window.close();
		    });
	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
	// var ywlzid = $(window.opener.document).find("#YWLZ_UID").val(); //业务流转ID 父页面获取
	// $("#YWLZ_UID").val(ywlzid);
		if($("#buSpywZzhydjForm").validationButton())
		{
		//  if(isnum($("#BCSF"))){
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
		    	 $("#JYFWFSF_JSGCJKTZD_UID").val(resultobj.JYFWFSF_JSGCJKTZD_UID);
		    	 $("#btnSave2").show();
		    	 type="update";
    	  	}else if(type=="update"){
    			defaultJson.doUpdateJson(controllername + "update", data1);
    		}	
    		
    //	}
		}else{
			requireFormMsg();
		  	return;
		}
	});
	$("#btnSave2").click(function() {  //打印
        $.ajax({
			url : controllername + "download?uid="+$("#JYFWFSF_JSGCJKTZD_UID").val()+"&filename="+filename,
			cache : false,
			async : false,
			dataType : "json",
			type : 'post',
			success : function(response) {
				window.open(controllerfile+"download2?path="+response,'文件下载');
			 } 
		    });
	});
});

var ht="";
var cbr="";
function getCbr(){
       $("#QYWLZ_UID").val(ywlz_uid);
	   $("#QLX").val("jl");
	   var msg=getCode();    //施工
	    if(msg!=null){
	      var resultobj = defaultJson.dealResultJson(msg);
	      ht=resultobj.BDHTJ;
	      cbr=findByCompany(resultobj.BB_CODE,'jl');
	      fyinit();
	     
        }
    
      /*   $("#QLX").val("jl");
        var msg2=getCode();  //监理
        if(msg2!=null){
	    var resultobj2 = defaultJson.dealResultJson(msg2);
	    jlHt=resultobj2.BDHTJ;
	    jlCbr=findByCompany(resultobj.BB_CODE,'jl');
	    $("#QJC").val("JYFWFJJTZD-JL");
        }
 */
}

function getCode() {
var msg=null;
		var data = combineQuery.getQueryCombineData(wjForm, frmPostCbr);
		var data1 = {
			msg : data
		};
		$.ajax( {
			url : controllernameWjCbfa + "query",
			data : data1,
			cache : false,
			async : false,
			dataType : "json",
			type : 'post',
			success : function(response) {
				$("#resultXML").val(response.msg);
				if(response.msg==0||response.msg==""){
				}else{
				msg=response.msg;
				}
					//$("#BH").html();
			}
		});
		return msg;
	} 
function findByCompany(code,lx){  //
	var arr = new Array();
	$.ajax({
		url : controllernameWjCbfa + "findByCompany?lx="+lx+"&code="+code,
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
		
		    arr = response.split(",");
		  
		  
		}
	});
	 return  arr[0];
}



function fyinit(){
   
   if(cbr==0){
   cbr="暂无";
   }
    $("#cbrspan").html(cbr);
	$("#CBR").val(cbr);
	
	$("#ht").html(ht);
	$("#HTJE").val(ht);
	
	if(ht>30){
	$("#jyfwf").html(3000);
	$("#JYfy").val(3000);
	}else if(ht<30){
	$("#jyfwf").html(1500);
	$("#JYfy").val(1500);
	}
	$("#cbrfy").html(parseFloat($("#JYfy").val())*0.3);
	$("#CBRFY").val(parseFloat($("#JYfy").val())*0.3);
	
	$("#dwfy").html(parseFloat($("#JYfy").val())*0.7);
	$("#DWFY").val(parseFloat($("#JYfy").val())*0.7);
}
function jisuan(){
      if($("#JZMJ").val()!=""){
		    	 var mey=105*parseFloat($("#JZMJ").val());
                 $("#BCSF").val(mey.toFixed(2));
		    	 $("#mjhtml").html(mey.toFixed(2)+"元");
	 }else{
	  $("#BCSF").val(0);
		    	 $("#mjhtml").html("0.00元");
	 }
}
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
				
					if(response.msg==""||response.msg=="0"){   
		                getBh();
						type="insert";
						$("#btnSave2").hide();
						}
					else{
						type="update";
					var resultobj = defaultJson.dealResultJson(res);
					$("#buSpywZzhydjForm").setFormValues(resultobj);
					$("#bh").html(resultobj.BDBH);
					jisuan();
					}
				} 
			});

		}
		
function getBh(){
			$.ajax({
				url : controllername + "getCount",
				data : null,
				cache : false,
				async : false,
				dataType : "json",
				type : 'post',
				success : function(response) {
					$("#bh").html(response.msg);
                    $("#BDBH").val(response.msg);
				} 
			});

}

function isnum(card){  
	      var reg = /^([0-9]{1,8}|[0-9]{1,8}[\.]{1,1}[0-9]{1,2})$/;
		  if($("#JZMJ").val() == null || $("#JZMJ").val() == ""){
			  alert("请输入面积!");
			  return false;
			  }
		  else{
		     if(reg.test($("#JZMJ").val() ) == false)  
		     {  
		            alert("请输入8位整数或带2位小数!");
		       $("#JZMJ").attr("style", "width: 100px;text-align: right;color:red;");
		       return  false;  
		     }else{
		    	 $("#JZMJ").attr("style", "width: 100px;text-align: right;");
		    	 
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
	var xmobj  =getXmxx();//项目
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
$("#JSDW").val(jscompany.COMPANY_NAME);
$("#xm").val(projects.PROJECTS_NAME);
$("#jd").html(projects.QUYU_SV);
$("#JZMJ").val(projects.ZHANDI_MIANJI);
$("#DWMC").val(jscompany.COMPANY_NAME);
$("#HTJE").val(projects.ZONG_TOUZI);
$("#XMMC").val(projects.PROJECTS_NAME);
$("#SD").val(projects.QUYU_SV);
var mey=105*projects.ZHANDI_MIANJI;
$("#BCSF").val(mey.toFixed(2));
$("#mjhtml").html(mey.toFixed(2)+"元");
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
      	<TR  style="">
	        <TD class="right-border bottom-border">
	        <INPUT type="hidden" class="cc-input1" kind="JYFWFSF_JSGCJKTZD_UID" id="QID" fieldname="YWLZ_UID"
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
	  		<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">保存</button>
	  		<button id="btnSave2"  class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">打印</button>
      	</span><br/>
	     
	     <form method="post" id="jscompanyForm"  >
	        <input type="hidden" id="JS_COMPANY_UID" fieldname="JS_COMPANY_UID" name="JS_COMPANY_UID" operation="="/>
     	 </form>
     	 <form method="post" id="projectsForm"  >
	        <input  type="hidden" id="PROJECTS_UID" fieldname="PROJECTS_UID" name="PROJECTS_UID" operation="="/>
     	 </form>
     	 <form method="post" id="jsprojectForm"  >
	        <input   type="hidden" id="PROJECT_UID" fieldname="PROJECT_UID" name="PROJECT_UID" operation="="/>
     	 </form>
     	  <form method="post" id="scxmmjhsdForm"  >
	        <input   type="hidden" id="QUERY_LZBZ_WJ_UID" fieldname="LZBZ_WJ_UID" name="LZBZ_WJ_UID" operation="="/>
     	 </form>
        <form method="post" id="wjForm">
						<table class="B-table" width="100%">
							<!--可以再此处加入hidden域作为过滤条件 -->
							<TR style="display: none;">
								<TD class="right-border bottom-border">
									<INPUT type="text" class="span12" kind="text" id="QYWLZ_UID" name="YWLZ_UID" fieldname="YWLZ_UID" value="" operation="=" />
									<INPUT type="text" class="span12" kind="text" id="QLX" name="LX" fieldname="LX" value="" operation="=" />
								</TD>
								<TD class="right-border bottom-border">
								</TD>
							</TR>
						</table>
		</form>
     <form method="post" id="buSpywZzhydjForm"   > 
      <h4 class="title" align="center">建设工程交易服务费缴款通知单（监理工程直接发包）</h4>
        
            <input type="hidden" id="JYFWFSF_JSGCJKTZD_UID" fieldname="JYFWFSF_JSGCJKTZD_UID" name = "JYFWFSF_JSGCJKTZD_UID"/>
	  	  <!-- <input type="hidden" id="GC_TCJH_XMXDK_ID" fieldname="XMID" name = "XMID"/>
	  	      <input type="hidden" id="YWLZ_UID" fieldname="YWLZ_UID" value="0" name = "YWLZ_UID"/> -->
	  	      <input type="hidden" id="LZBZ_WJ_UID" fieldname="LZBZ_WJ_UID" name = "LZBZ_WJ_UID"/>
	<div align="center">  
  <table  style="width: 800px;font-size: 17px;"  border="1" class="cc-alert-table" > 
  <input  maxlength="10" style="width: 100%;text-align: right;" class="cc-input1"  id="BCSF" type="hidden"  fieldname="BCSF" name = "BCSF"  />
    <input   id="SD" type="hidden"  fieldname="SD" name = "SD"  />
               <tr style="height: 35px;"> 
                   <td width="25%" style="text-align: right;" >标段编号</td>  
                   <td width="75%" colspan="3" ><span id="bh"></span><input  type="hidden" id="BDBH"  fieldname="BDBH" name = "BDBH"  style="width: 97%;" placeholder="必填" check-type="required" maxlength="100" class="cc-input1"  /></td>  
               </tr>
               <tr style="height: 35px;"> 
                   <td width="25%" style="text-align: right;" >项目名称</td>  
                   <td width="75%" colspan="3" ><input  style="width: 97%;" readonly="readonly" placeholder="必填" check-type="required" maxlength="200" class="cc-input1"  id="xm" type="text"  fieldname="XMMC" name = "XMMC"  /></td>  
               </tr>
               <tr style="height: 35px;"> 
                   <td width="25%" style="text-align: right;" >标段名称</td>  
                   <td width="75%" colspan="3" ><input type="text"  fieldname="BDMC" name = "BDMC"  style="width: 97%;" placeholder="必填" check-type="required" maxlength="100" class="cc-input1"  /></td>  
               </tr>
               <tr style="height: 35px;"> 
                   <td width="25%" style="text-align: right;" >合同金额（万元）</td>  
                   <td width="25%" ><span id="ht"></span><input type="hidden" id="HTJE" fieldname="HTJE" name = "HTJE"  style="width: 93%;" placeholder="必填" check-type="required" maxlength="21" class="cc-input1"  /></td>  
                   <td width="25%" style="text-align: right;" >交易服务费（元）</td>  
                   <td width="25%" ><span id="jyfwf"></span><input type="hidden" id="JYfy" fieldname="JYFWF" name = "JYFWF"  style="width: 93%;" placeholder="必填" check-type="required" maxlength="21" class="cc-input1"  /></td>
               </tr>
              <tr style="height: 35px;"> 
                 <td width="25%" style="text-align: right;" >收款人</td>  
                 <td width="75%" colspan="3" >无锡市人民政府新区管理委员会财政局<input value="无锡市人民政府新区管理委员会财政局"  style="width: 97%;display: none;" placeholder="必填" check-type="required" maxlength="20" class="cc-input1"  id="SKR" type="text"  fieldname="SKR" name = "SKR"  /></td>  
              </tr>
              <tr style="height: 35px;"> 
                  <td width="25%" style="text-align: right;" >开户行</td>  
                  <td width="75%" colspan="3" >中信银行新区支行<input style="width: 97%;display: none;" value="中信银行新区支行"  placeholder="必填" check-type="required" maxlength="50"  class="cc-input1"  id="KHH" type="text"  fieldname="KHH" name = "KHH"  /></td> 
              </tr>
              <tr style="height: 35px;" > 
                <td width="25%" style="text-align: right;" >收款账户</td>  
                <td width="75%" colspan="3" >7352310182600002786<input style="width: 97%;display: none;" value="7352310182600002786" placeholder="必填" check-type="required" maxlength="50"  class="cc-input1"  id="SKZH" type="text"  fieldname="SKZH" name = "SKZH"  /></td> 
              </tr>
              <tr style="height: 35px;"> 
                   <td width="25%" colspan="2" style="text-align:center;" ><span id="gs"></span><input style="width: 97%;display: none;" value=""  maxlength="50"  class="cc-input1"  id="JSDW" type="text"  fieldname="JSDW" name = "JSDW"  /></td>  
          
                   <td width="25%" colspan="2" style="text-align: center;" ><span id="cbrspan"></span><input id="CBR" style="width: 93%"  maxlength="50"  class="cc-input1"  id="CBR" type="hidden"  fieldname="CBR" name = "CBR"  /></td>  
                   
               </tr>
               <tr style="height: 35px;"> 
                   <td width="25%" style="text-align: right;" >应缴费用</td>  
                   <td width="25%" ><span id="dwfy"></span><input type="hidden" id="DWFY" fieldname="JSDWYJFY" name = "JSDWYJFY"  style="width: 93%;" placeholder="必填" check-type="required" maxlength="20" class="cc-input1"  /></td>  
                   <td width="25%" style="text-align: right;" >应缴费用</td>  
                   <td width="25%" ><span id="cbrfy"></span><input type="hidden" id="CBRFY" fieldname="CBRYJFY" name = "CBRYJFY"  style="width: 93%;"  maxlength="20" class="cc-input1"  /></td>
               </tr>
               <tr style="height: 35px;"> 
                   <td width="25%" style="text-align: right;"  >缴款经办人签字</td>  
                   <td width="25%" ><!-- <input type="text"  fieldname="JSDWJKJBRQZ" name = "JSDWJKJBRQZ"  style="width: 93%;" placeholder="必填" check-type="required" maxlength="20" class="cc-input1"  /> --></td>  
                   <td width="25%" style="text-align: right;"  >缴款经办人签字</td>  
                   <td width="25%" ><!--<input type="text"  fieldname="CBRJKJBRQZ" name = "CBRJKJBRQZ"  style="width: 93%;"  maxlength="20" class="cc-input1"  />--></td>
               </tr>
               <tr style="height: 35px;"> 
                   <td width="25%" style="text-align: right;" >收据编号</td>  
                   <td width="25%" ><!--<input type="text"  fieldname="JSDWSJBH" name = "JSDWSJBH"  style="width: 93%;" placeholder="必填" check-type="required" maxlength="20" class="cc-input1"  />--></td>  
                   <td width="25%" style="text-align: right;" >收据编号</td>  
                   <td width="25%" ><!--<input type="text"  fieldname="CBRSJBH" name = "CBRSJBH"  style="width: 93%;"  maxlength="20" class="cc-input1"  />--></td>
               </tr>
               <tr style="height: 35px;"> 
                   <td width="25%" style="text-align: right;">服务中心签字</td>  
                   <td width="25%" ><!--<input type="text"  fieldname="JSDWFWZXQZ" name = "JSDWFWZXQZ"  style="width: 93%;" placeholder="必填" check-type="required" maxlength="20" class="cc-input1"  />--></td>  
                   <td width="25%" style="text-align: right;">服务中心签字</td>  
                   <td width="25%" ><!--<input type="text"  fieldname="CBRFWZXQZ" name = "CBRFWZXQZ"  style="width: 93%;"  maxlength="20" class="cc-input1"  />--></td>
               </tr>
                <tr style="height: 35px;"> 
                   <td width="25%" style="text-align: right;" >日期</td>  
                   <td width="25%" ><!--<input id="JSDWRQ" class="Wdate" type="text" onclick="WdatePicker()" fieldtype="date" style="width: 93%; border: 1px solid #ccc;" placeholder="必填" check-type="required" data-date-format="yyyy-mm-dd" name="JSDWRQ" fieldname="JSDWRQ" title="">-->
                   </td>
                     
                   <td width="25%" style="text-align: right;" >日期</td>  
                   <td width="25%" ><!--<input id="CBRRQ" class="Wdate" type="text" onclick="WdatePicker()" fieldtype="date" style="width: 93%; border: 1px solid #ccc;" placeholder="必填" check-type="required" data-date-format="yyyy-mm-dd" name="CBRRQ" fieldname="CBRRQ" title="">-->
                  </td>
               </tr>
</table><br/>
  <div align="left" style="width: 800px;" >  
注意事项<br/>
1、缴款单位经办人持本缴款通知单至指定银行缴费。<br/>
2、经确认到账后，缴款单位经办人携以下资料至无锡市建设工程交易管理中心新区服务点（无锡新区长江路7号 无锡科技创业园四区一楼）开取交易服务费收据，并请交易服务中心经办人签字确认
   ①本缴款通知单；
②缴款凭证（原件或复印件）；<br/>
3、建设单位持经交易服务中心经办人签字确认后的本通知单，至建设局工程建设处领取直接发包合同备案表。<br/>
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
 	<FORM name="frmPostCbr" method="post" style="display:none" target="_blank">
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