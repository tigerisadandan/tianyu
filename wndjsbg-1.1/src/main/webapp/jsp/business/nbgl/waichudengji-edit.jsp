<!DOCTYPE html>
<%@page import="com.ccthanking.framework.Constants"%>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>外出记录-编辑</title>
<%
	String wcdjId = request.getParameter("wcdjId");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername = "${pageContext.request.contextPath }/nbgl/waiChuDengJiController";

var wcdjId ="<%=wcdjId%>";
//页面初始化
$(function() {
	init();
	$("#btnClose").bind("click", function(){
		var a=$(this).manhuaDialog.close();
	});
	$("#btnDel").bind("click",function(){
		xConfirm("信息确认","是否要执行删除操作？");
		$('#ConfirmYesButton').attr('click','');
		$('#ConfirmYesButton').one("click",function(){
			$.ajax({
				url :  controllername+"?delete",
				data : {"wcdjId":wcdjId},
				type : "post",
				dataType : "json",
				success : function(response){
					if(response.success){
						//获取父页面
						var a=$(window).manhuaDialog.getParentObj();
						//重新加载数据
			   			a.init();
			   			//关闭当前页
						$(window).manhuaDialog.close();	
					}
				}
			});
		});
	});
	$("#btnSave").bind("click",function(){
		//生成json串
		var data = Form2Json.formToJSON(WaiChuForm);
		//组成保存json串格式
		var data1 = defaultJson.packSaveJson(data);
		$.ajax({
			url : controllername + "?update",
			data : data1,
			cache : false,
			dataType : "json",
			type : "post",
			async : true,
			success : function(response) {
				//获取父页面
				var a=$(window).manhuaDialog.getParentObj();
				//重新加载数据
			   	a.init();
			   	//关闭当前页
				$(window).manhuaDialog.close();
				}
			});
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
      <h4 class="title">信息修改
      	<span class="pull-right">
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
      		<button id="btnDel" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">删除</button>
      		<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">保存</button>	  		
      	</span>
      </h4>
      <div style="height:5px;"></div>
     <form method="post" id="WaiChuForm" action="${pageContext.request.contextPath }/nbgl/waiChuDengJiController?update" >
     	<table class="B-table" width="100%" >
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">外出时间</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input id="WAICHU_SHIJIAN" type="text" fieldtype="date" fieldformat="YYYY-MM-DD HH:mm:ss" fieldname="WAICHU_SHIJIAN" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy/MM/dd HH:ss:mm'})" name = "WAICHU_SHIJIAN" readonly="readonly"   />	         	        	
	       	 	<!-- 隐藏属性 -->
	       	 		<input class="span12" id="WAICHU_DENGJI_UID" type="hidden" fieldname="WAICHU_DENGJI_UID" name = "WAICHU_DENGJI_UID"  />	
	       	 		<input class="span12" id="WAICHU_STATUS" type="hidden" fieldname="WAICHU_STATUS" name = "WAICHU_STATUS"  />	
	       	 		<input class="span12" id="WAICHU_RENYUAN_UID" type="hidden" fieldname="WAICHU_RENYUAN_UID" name = "WAICHU_RENYUAN_UID"  />	
	       	 		<input class="span12" id="RENYUAN_NAME" type="hidden" fieldname="RENYUAN_NAME" name = "RENYUAN_NAME"  />
	       	 		<input id="FANHUI_SHIJIAN" type="hidden" fieldtype="date" fieldformat="YYYY-MM-DD HH:mm:ss" fieldname="FANHUI_SHIJIAN" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy/MM/dd HH:ss:mm'})" name = "FANHUI_SHIJIAN" readonly="readonly"   />	
	       	 		<input class="span12" id="CREATE_BY" type="hidden" fieldname="CREATE_BY" name = "CREATE_BY"  />
	       	 		<input class="span12" id="CREATE_DATE" type="hidden" fieldname="CREATE_DATE" name = "CREATE_DATE"  />
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">预计返回时间</th>
	       	 	<td colspan="3" class="bottom-border right-border">
	       	 	<div id="dis">
					  <input id="YJ_FH_SHIJIAN" type="text" fieldtype="date" fieldformat="YYYY-MM-DD HH:mm:ss" fieldname="YJ_FH_SHIJIAN" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy/MM/dd HH:ss:mm'})" name = "YJ_FH_SHIJIAN" readonly="readonly"   />
					  </div>
				</td>
	        </tr>
	         <tr>
				<th width="15%" class="right-border bottom-border text-right">外出地点</th>
	       	 	<td class="bottom-border right-border" style="width:100%" colspan="3">
	       	 	<input class="span12"  id="WAICHU_DIDIAN" type="text"  fieldname="WAICHU_DIDIAN" name = "WAICHU_DIDIAN"  />	         	        	
	       	 	</td>
	        </tr>
	         <tr>
				<th width="15%" class="right-border bottom-border text-right">外出事宜</th>
	       	 	<td class="bottom-border right-border" style="width:32%" colspan="3">
	         		<textarea class="span12" rows="4"  id="WAICHU_NEIRONG"    fieldname="WAICHU_NEIRONG" name = "WAICHU_NEIRONG" ></textarea>	         	
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