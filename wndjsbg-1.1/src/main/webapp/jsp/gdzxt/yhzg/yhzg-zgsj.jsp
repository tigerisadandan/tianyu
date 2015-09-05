<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>隐患整改</title>
<%
	String sjuid = request.getParameter("sjuid");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllernameContent= "${pageContext.request.contextPath }/yhzg/zgContentController";
//页面初始化
$(function() {
	setFormValues();
	$("#btnClose").bind("click", function(){
		var a=$(this).manhuaDialog.close();
	});	

});

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
      	</span>
      </h4>
     <form method="post" id="zgtzdForm"  >
      <table class="B-table" width="100%" >
         <tr>
			<th width="15%" class="right-border bottom-border text-right">违规类型  </th>
       		<td class="bottom-border right-border" >
         		<input readonly="readonly" style="width: 50%;"  id="ZG_CODE" type="text" fieldname="NAME" name = "ZG_CODE"  />
         	</td>
         </tr>

        <tr>
			<th width="15%" class="right-border bottom-border text-right">违规条例</th>
       		<td class="bottom-border right-border" >
         		<input  id="KOU_SG" style="width: 50%;"   type="text" fieldname="TIAOLI" name = "KOU_SG" readonly="readonly"/>
         	</td>
         </tr>
        <tr>
			<th width="15%" class="right-border bottom-border text-right">违规内容</th>
       		<td class="bottom-border right-border">
         		<textarea style="width: 50%;"  rows="3"  fieldname="WG_MIAOSHU"></textarea>
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
    	  <input type="hidden" id="QZG_CONTENT_UID" fieldname="t.ZG_CONTENT_UID" name = "ZG_CONTENT_UID" operation="=" value="<%=sjuid %>"/>
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