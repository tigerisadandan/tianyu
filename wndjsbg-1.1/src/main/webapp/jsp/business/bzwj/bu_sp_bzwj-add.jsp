<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>步骤文件-维护</title>
<%
	String type=request.getParameter("type");
	String SPBZ_UID=request.getParameter("SPBZ_UID");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/bzwj/buSpBzwjController.do";
var type ="<%=type%>";
var SPBZ_UID="<%=SPBZ_UID %>";
//页面初始化
$(function() {
//	init();
  $("#SPBZ_UID").val(SPBZ_UID);
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,buSpBzwjList);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"?query",data,buSpBzwjList);
	});
	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
		if($("#buSpBzwjForm").validationButton())
		{
		    //生成json串
		    var data = Form2Json.formToJSON(buSpBzwjForm);
		  //组成保存json串格式
		    var data1 = defaultJson.packSaveJson(data);
		  //调用ajax插入
		    if($("#ID").val() == "" || $("#ID").val() == null){
    			defaultJson.doInsertJson(controllername + "?insert", data1,null);
    			$("#buSpBzwjForm").clearFormResult();
    		}else{
    			defaultJson.doUpdateJson(controllername + "?insert", data1,null);
    		}
    		window.opener.initwj();
    		window.close();
		}else{
			requireFormMsg();
		  	return;
		}
	});
	
	//按钮绑定事件（新增）
    $("#btnClear_Bins").click(function() {
        $("#buSpBzwjForm").clearFormResult();
        $("#buSpBzwjForm").cancleSelected();
        
        
        $("#ZFRQ").val(new Date().toLocaleDateString());
        $("#ZFJE").val(0);
        $("#ID").val("");
    });
	
	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#queryForm").clearFormResult();
    });
    
    <%
    if(type.equals("detail")){
	%>
	//置所有input 为disabled
	$(":input").each(function(i){
	   $(this).attr("disabled", "true");
	 });
	<%
		}
	%>
	
});
//页面默认参数
function init(){
	getNd();
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,buSpBzwjList);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?query",data,buSpBzwjList);
	
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
<%--		$("#$("#buSpBzwjForm")").setFormValues(tempJson);--%>
<%--	}--%>
}


//点击行事件
function tr_click(obj){
	//alert(JSON.stringify(obj));
	$("#buSpBzwjForm").setFormValues(obj);
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
	var obj = $("#buSpBzwjList").getSelectedRowJsonByIndex(index);
	var xmbh = eval("("+obj+")").XMBH;
	$(window).manhuaDialog(xmscUrl(xmbh));
}
</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">
    <div class="B-small-from-table-autoConcise">
    
	</div>
	<div style="height:5px;"></div>
	<div class="B-small-from-table-autoConcise">
      <h4 class="title">步骤文件
      	<span class="pull-right">
      		<button id="btnClear_Bins" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">清空</button>
	  		<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">保存</button>
      	</span>
      </h4>
     <form method="post" id="buSpBzwjForm"  >
     <table class="B-table" width="100%">
      <input type="hidden" id="SPBZ_UID" fieldname="SPBZ_UID" name = "SPBZ_UID"/></TD>
        <tr>
			<th width="8%" class="right-border bottom-border text-right">简称</th>
       	 	<td class="bottom-border right-border" width="23%">
         		<input class="span12" style="width:85%" id="JC" type="text" maxlength="30" placeholder="必填" check-type="required"  fieldname="JC" name = "JC"/>
       	 	</td>
       	 	<th width="8%" class="right-border bottom-border text-right">文件名称</th>
       		<td class="bottom-border right-border"width="15%" colspan="3">
         		<input class="span12" style="width:85%" id="WJNAME" type="text" placeholder="必填" check-type="required"  fieldname="WJNAME" name = "WJNAME"/>
         	</td>
        </tr>
         <tr>
			<th width="8%" class="right-border bottom-border text-right">模版文件名</th>
       	 	<td class="bottom-border right-border" width="23%">
         		<input class="span12" style="width:85%" id="TMPWJNAME" type="text" placeholder="必填" check-type="required"  fieldname="TMPWJNAME" name = "TMPWJNAME"/>
       	 	</td>
       	 	<th width="8%" class="right-border bottom-border text-right">是否可用</th>
       		<td class="bottom-border right-border"width="15%" colspan="3">
         		<input class="span12" style="width:50%" id="ENABLED" type="radio" check-type="required" kind="dic" src="SF" defaultvalue=1 fieldname="ENABLED" name = "SF" />
         	</td>
        </tr>
         <tr>
			<th width="8%" class="right-border bottom-border text-right">操作URL</th>
       	 	<td class="bottom-border right-border" width="23%">
         		<input class="span12" style="width:85%" id="OPTURL" type="text" placeholder="必填" check-type="required"  fieldname="OPTURL" name = "OPTURL"/>
       	 	</td>
        </tr>
        </table>
      </form>
    </div>
   </div>
  </div>
  <div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id = "queryXML">
         <input type="hidden" name="txtXML" id = "txtXML">
         <input type="hidden" name="txtFilter"  order="desc" fieldname = "t.LRSJ" id = "txtFilter">
         <input type="hidden" name="resultXML" id = "resultXML">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
		
 	</FORM>
 </div>
</body>
<script>
</script>
</html>