<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>隐患整改</title>
<%
	String gcuid = request.getParameter("gcuid");
	String username = request.getParameter("username");
	String sjuid = request.getParameter("sjuid");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllernameContent= "${pageContext.request.contextPath }/yhzg/zgWeiguiSjController";
//页面初始化
$(function() {
	init();
	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
		if($("#zgtzdForm").validationButton()){
		    //生成json串
		    var data = Form2Json.formToJSON(zgtzdForm);
		  //组成保存json串格式
		    var data1 = defaultJson.packSaveJson(data);
		  //调用ajax插入
		    if($("#QSJUID").val() != "" && $("#QSJUID").val() != null){
    			defaultJson.doUpdateJson(controllername + "?update", data1);
    		}else{
    			defaultJson.doInsertJson(controllername + "?insert", data1);
    			var rowValue = $("#resultXML").val();
    			var res = dealSpecialCharactor(rowValue);
    			var resultobj = defaultJson.dealResultJson(res);
    			$('#QQSJUID').val(resultobj.ZG_TZD_UID);
    			//给表单赋值
    			$("#zgtzdForm").setFormValues(resultobj);
    		    $('#shjltxdivid').show();
    		}
		    //var a = $(window).manhuaDialog.getParentObj();
		    //a.setFormValues();
			//$(window).manhuaDialog.close();
		}else{
			requireFormMsg();
		  	return;
		}
	});

	$("#btnClose").bind("click", function(){
		var a=$(this).manhuaDialog.close();
	});	

	$('#btnDel').click(function(){

	});

});
//页面默认参数
function init(){
	//生成json串
	//var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT3);
	//调用ajax插入
	//defaultJson.doQueryJsonList(controllernameContent+"?query",data,DT3);
	//setFormValues();
	
setFormValues();

	
}

//查询表单值
function setFormValues(){
	var data = combineQuery.getQueryCombineData(queryForm,frmPost);
	var data1 = {
		msg : data
	};
	$.ajax({
		url : controllernameContent+"?query",
		data : data1,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			$("#resultXML").val(response.msg);
			var resultobj = defaultJson.dealResultJson(response.msg);
			$("#zgtzdForm").setFormValues(resultobj);
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
      <h4 class="title">
      	<span class="pull-right">
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
      		<button id="btnDel" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">删除</button>
	  		<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">保存</button>
      	</span>
      </h4>
     <form method="post" id="zgtzdForm"  >
      <table class="B-table" width="100%" >
     	 <input type="hidden" id="ZG_CONTENT_UID" fieldname="ZG_CONTENT_UID" name = "ZG_CONTENT_UID" value="<%=sjuid %>"/>
         <tr>
			<th width="15%" class="right-border bottom-border text-right">违规类型  </th>
       		<td class="bottom-border right-border" >
         		<input readonly="readonly" style="width: 50%;"  id="ZG_CODE" type="text" fieldname="NAME" name = "ZG_CODE"  />
         	</td>
         </tr>

        <tr>
			<th width="15%" class="right-border bottom-border text-right">违规条例</th>
       		<td class="bottom-border right-border" >
         		<input  id="CONTENT" style="width: 50%;"   type="text" fieldname="CONTENT" name = "CONTENT" readonly="readonly"/>
         	</td>
         </tr>
        <tr>
			<th width="15%" class="right-border bottom-border text-right">违规内容</th>
       		<td class="bottom-border right-border">
         		<textarea style="width: 50%;"  rows="3"  fieldname="WEIGUI_CONTENT"></textarea>
         	</td>
         </tr>
        <tr>
			<th width="15%" class="right-border bottom-border text-right">详细描述</th>
       		<td class="bottom-border right-border" colspan="3" >
         		<textarea style="width: 50%;"  rows="3"  fieldname="DESCRIPTION"></textarea>
         	</td>
         </tr>
      </table>	
      </form>
    </div>
   </div>
  </div>
  <form method="post" id="queryForm"  >
    	  <input type="hidden" id="QSJUID" fieldname="sj.ZG_WEIGUI_SJ_UID" name = "SJUID" operation="=" value="<%=sjuid %>"/>
  </form>
  
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