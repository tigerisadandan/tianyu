<!DOCTYPE html>
<html>
<head> 	
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>

<app:base/>
<title>审批业务流转实例首页</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>

<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/fsMessageInfoController";
//页面初始化
$(function() {	
	init();	

	$("#btnClose").bind("click", function(){
		var a=$(window).manhuaDialog.getParentObj();
	    a.init();
		var a=$(this).manhuaDialog.close();
	});

});

function init(){
	var parentmain = $(window).manhuaDialog.getParentObj();
	var rowValue = parentmain.$("#resultXML").val();
	var tempJson = convertJson.string2json1(rowValue);
	var OPID = tempJson.OPID;
	$("#OPID_SEARCH").val(OPID);
	var state=tempJson.STATE;
	
	if(state==1){
		var datadel = Form2Json.formToJSON(buForm);
		var datadel1 = defaultJson.packSaveJson(datadel);
	    $.ajax({
			url :'${pageContext.request.contextPath}/fsMessageInfoController?delete',
			data : datadel1,
			cache : false,
			async :	false,
			dataType : "json",  
			type : 'post',
			success : function(response) {					
			}
		});
	}
	
	//查询表单赋值
	var data = combineQuery.getQueryCombineData(buForm, frmPost);
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
			var title=resultobj.TITLE+"<br><small id=\"smallid\"> 发布时间："+resultobj.OPTIME+" &nbsp发布人："+resultobj.USERFROMNAME+"   </small>";
			$("#titleh3").html(title);
			$("#messagepid").html(resultobj.CONTENT);	
			return true;	
		}
	});

}



</script>
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">
  
	<div style="height:5px;"></div>
	<div class="B-small-from-table-autoConcise">
      <h4 class="title">消息内容
      	<span class="pull-right">
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
      	</span>
      </h4>
		     <form action="post" id="buForm">
				<input type="hidden" id="OPID_SEARCH" fieldname="OPID" name="OPID" operation="=" />
			 </form>
     
    			<div class="row-fluid">
			        <div class="page-header"><h3 class="text-center" id="titleh3"><br><small id="smallid"></small></h3></div>
			        <div>
			        	<p id="messagepid"></p>
			        </div>
			        <div>
			        	
			        </div>
			    </div>
     
     
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
</html>