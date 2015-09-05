<!DOCTYPE html>
<%@page import="com.ccthanking.framework.Constants"%>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>外出登记-明细</title>
<%
	String wcdjId = request.getParameter("wcdjId");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/nbgl/waiChuDengJiController";

var wcdjId ="<%=wcdjId%>";
//页面初始化
$(function() {
	init();
	$("#btnClose").bind("click", function(){
		var a=$(this).manhuaDialog.close();
	});
	
});


//页面默认参数
function init(){
	$.ajax({
		url : controllername+"?detail",
		data : {"wcdjId":wcdjId},
		type : "post",
		dataType : "json",
		success : function(response){
			var resultObj = defaultJson.dealResultJson(response.msg);
			$("#WaiChuForm").setFormValues(resultObj);
		}
	});
}


</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">
	
	<div class="B-small-from-table-autoConcise">
      <h4 class="title">信息查看
      	<span class="pull-right">
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>  		  		
      	</span>
      </h4>
      <div style="height:5px;"></div>
     <form method="post" id="WaiChuForm"  >
     	<table class="B-table" width="100%" >
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">外出时间</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12"  id="WAICHU_SHIJIAN" type="text"  fieldname="WAICHU_SHIJIAN"  fieldtype="date" readonly="readonly" name = "WAICHU_SHIJIAN"  />	         	        	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">预计返回时间</th>
	       	 	<td colspan="3" class="bottom-border right-border">
	       	 	<div id="dis">
					  <input   id="YJ_FH_SHIJIAN" type="text" fieldname="YJ_FH_SHIJIAN" name = "YJ_FH_SHIJIAN" fieldtype="date" readonly="readonly"  />
					  </div
				</td>
	        </tr>
	         <tr>
				<th width="15%" class="right-border bottom-border text-right">外出地点</th>
	       	 	<td class="bottom-border right-border" style="width:100%" colspan="3">
	       	 	<input class="span12"  id="WAICHU_DIDIAN" type="text" readonly="readonly"  fieldname="WAICHU_DIDIAN" name = "WAICHU_DIDIAN"  />	         	        	
	       	 	</td>
	        </tr>
	         <tr>
				<th width="15%" class="right-border bottom-border text-right">外出事宜</th>
	       	 	<td class="bottom-border right-border" style="width:32%" colspan="3">
	         		<textarea class="span12" rows="4"  id="WAICHU_NEIRONG"  readonly="readonly"  fieldname="WAICHU_NEIRONG" name = "WAICHU_NEIRONG" ></textarea>	         	
	       	 	</td>
	        </tr>
			
      	</table>
      </form>
    </div>
   </div>
  </div>

  <jsp:include page="/jsp/file_upload/fileuploadold_config_js.jsp" flush="true"/> 
  <div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id = "queryXML">
         <input type="hidden" name="txtXML" id = "txtXML">
         <input type="hidden" name="txtFilter" order="desc" fieldname="SHENHE_JIEGUO" id="txtFilter"/>
		 <input type="hidden" name="txtFilter" order="desc" fieldname="CREATED_DATE" id="txtFilter"/>
         <input type="hidden" name="resultXML" id = "resultXML">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
		
 	</FORM>
 </div>
</body>
<script>
</script>
</html>