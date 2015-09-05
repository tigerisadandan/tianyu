<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>通知管理</title>
<!--<%-->
<!--	String gcuid = request.getParameter("gcuid");-->
<!--%>-->
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername = "${pageContext.request.contextPath }/yhzg/zgTzdController";
var controllernametzgl = "${pageContext.request.contextPath }/tzgl/tongzhiController";
var controllernamegc= "${pageContext.request.contextPath }/gongcheng/projectsGongchengController";

//页面初始化
$(function() {
	init();
	$("#btnClose").bind("click", function(){
		var a=$(this).manhuaDialog.close();
	});	
	$("#btnAdd").click(function() {
		$(window).manhuaDialog({"title":"添加管理>选择发送工程 ","type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/tz/tz-add-detail.jsp","modal":"2"});
	});
	$("#btnSave").bind("click",function(){
		if($("#zgtzdForm").validationButton() && $("#gongchengnum").val()!=""){
//			//生成json串
//		    var data = Form2Json.formToJSON(zgtzdForm);
//		  	//组成保存json串格式
//		    var data1 = defaultJson.packSaveJson(data);
			$.ajax({
				type:"POST",
				data:$("#zgtzdForm").serialize(),
				url:controllernametzgl+"?insert",
				datatype:"JSON",
				success:function(msg){
					
				},
				error:function(e){
					
				}
			});
			var a=$(window).manhuaDialog.getParentObj();
		    a.init();
			$(window).manhuaDialog.close();
		}
	});
});
//页面默认参数
function init(){
//	$('#GONGCHENG_UID').val(parent.document.getElementById("GDZXT_XM_ID").value);
	if($("#resultXML").val()!=""){
		$("#gongchengnum").val($("#resultXML").val().split(",").length);
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT3);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllernamegc+"?querygc&gcid="+$("#resultXML").val(),data,DT3);
	}
}

function xmjl(obj){
	var showHtml="<input style=\"width:80px;\" id=\"xmjl_"+obj.IDNUM+"\" type=\"text\" name = \"xmjl_"+obj.IDNUM+"\" value=\""+obj.XMJL+"\">";
	showHtml+="<input type=\"hidden\" style=\"width:80px;\" id=\"gcuid_"+obj.IDNUM+"\" type=\"text\" name = \"gcuid_"+obj.IDNUM+"\" value=\""+obj.GONGCHENG_UID+"\">";
	return showHtml;
}

function zongjian(obj){
	var showHtml="<input style=\"width:80px;\" id=\"zongjian_"+obj.IDNUM+"\" type=\"text\" name = \"zongjian_"+obj.IDNUM+"\" value=\""+obj.ZJ+"\">";
	return showHtml;
}

function xmljphone(obj){
	var showHtml="<input style=\"width:80px;\" id=\"xmljphone_"+obj.IDNUM+"\" type=\"text\" name = \"xmljphone_"+obj.IDNUM+"\" value=\""+obj.XMJLPHONE+"\">";
	return showHtml;
}

function zongjianphone(obj){
	var showHtml="<input style=\"width:80px;\" id=\"zjphone_"+obj.IDNUM+"\" type=\"text\" name = \"zjphone_"+obj.IDNUM+"\" value=\""+obj.ZJPHONE+"\">";
	return showHtml;
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
	  		<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">保存</button>
      	</span>
      </h4>
     <form method="post" id="zgtzdForm"  >
      <table class="B-table" width="100%" >
     	 <input  id="gongchengnum"  type="hidden" name = "gongchengnum" />
         <tr>
			<th  class="right-border bottom-border text-right">情况级别</th>
       		<td class="bottom-border right-border" >
         		<input  id="ZG_CODE" type="radio" fieldname="TZ_LEVEL" name = "ZG_CODE"  checked="checked" value="YB"/> 一般通知 
         		<input  id="ZG_CODE" type="radio" fieldname="TZ_LEVEL" name = "ZG_CODE" value="ZY" />  重要通知 
         	</td>
         </tr>
    
 
        <tr>
			<th  class="right-border bottom-border text-right">录入栏目</th>
       		<td class="bottom-border right-border" >
         		<input  id="AJ_Y" type="checkbox" fieldname="AJ_Y" name = "AJ_Y"/>  同时发送安监人员 
         	</td>
         </tr>
        <tr>
			<th  class="right-border bottom-border text-right">通知标题</th>
       		<td class="bottom-border right-border" >
         		<input check-type="required" id="TONGZHI_TITLE" style="width: 90%"  type="text" fieldname="TONGZHI_TITLE" name = "TONGZHI_TITLE"/>
         	</td>
         </tr>
       
         
         <tr>
			<th  class="right-border bottom-border text-right">通知内容</th>
       		<td class="bottom-border right-border">
         		<textarea check-type="required" rows="5" cols="" style="width: 90%" fieldname="TONGZHI_CONTENT" id="TONGZHI_CONTENT" name = "TONGZHI_CONTENT"></textarea>
         	</td>

         </tr>
      </table>
      <div style="height: 5px;"></div>
 		<div id="shjltxdivid">
				<div class="modal-header" style="background:#0866c6;">
				<h3 style="color:white;">接收通知工程
				<span class="pull-right">
					<button id="btnAdd" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">添加</button>
	  			</span>
				</h3>
				</div>
      			<table class="table-hover table-activeTd B-table" width="100%" id="DT3" type="single" noPage=true pageNum="10000">
					<thead>
						<tr>
							<th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
		                	<th fieldname="GCMC" colindex=1 tdalign="center">&nbsp;工程名称&nbsp;</th>
		                	<th fieldname="JSDW" colindex=1 tdalign="center" rowMerge="true">&nbsp;建设单位&nbsp;</th>
	                		<th fieldname="XMMC" colindex=4 tdalign="left" maxlength="30" rowMerge="true">&nbsp;项目名称&nbsp;</th>
							<th fieldname="SGDW" colindex=1 tdalign="center" maxlength="30" >&nbsp;施工单位 &nbsp;</th>
							<th fieldname="XMJL" colindex=2 tdalign="center" maxlength="30" CustomFunction="xmjl">&nbsp;项目经理 &nbsp;</th>
							<th fieldname="XMJLPHONE" colindex=3 tdalign="center" maxlength="30" CustomFunction="xmljphone">&nbsp;联系方式&nbsp;</th>
							<th fieldname="ZJ" colindex=6 tdalign="center" maxlength="30" CustomFunction="zongjian">&nbsp;总监&nbsp;</th>						
							<th fieldname="ZJPHONE" colindex=6 tdalign="center" maxlength="30" CustomFunction="zongjianphone">&nbsp;联系方式&nbsp;</th>						
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
      </form>
    </div>
   </div>
  </div>
  <form method="post" id="queryForm"  >
<!--    	  <input type="hidden" id="QZG_TZD_UID" fieldname="t.zg_tzd_uid" name = "ZG_TZD_UID" operation="="/>-->
		<input type="hidden"  kind="text" id="num" fieldname="rownum" value="10000" operation="<="/>
  </form>

  <jsp:include page="/jsp/file_upload/fileuploadold_config_js.jsp" flush="true"/> 
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