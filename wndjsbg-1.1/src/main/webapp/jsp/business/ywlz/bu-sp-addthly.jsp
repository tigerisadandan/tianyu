<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>步骤处理时的退回理由-维护</title>
<%
	String type=request.getParameter("type");
	String spbzuid=request.getParameter("spbzuid");
%>

<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/spxx/buSpBzThlyController";
var type ="<%=type%>";
var spbzuid="<%=spbzuid%>";
//页面初始化
$(function() {
	init();

	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
		    var liyou=$("#LIYOU").val();
		    if(liyou==""||liyou==null){
				alert("理由不能为空");
				return;
			}
		    //生成json串
		    var data = Form2Json.formToJSON(buSpBzThlyForm);
		  //组成保存json串格式
		    var data1 = defaultJson.packSaveJson(data);
		  //调用ajax插入
		    if($("#THLY_UID").val() == "" || $("#THLY_UID").val() == null){
    			defaultJson.doInsertJson(controllername + "?insert", data1);
    			$("#buSpBzThlyForm").clearFormResult();
    		}else{
    			defaultJson.doUpdateJson(controllername + "?update", data1);
    		}
			var a=$(window).manhuaDialog.getParentObj();
			a.$("#CHULI_YIJIAN_ID").val(liyou);
		    a.updateThly();
			$(window).manhuaDialog.close();
	
	});

	$("#btnClose").bind("click", function(){
		var a=$(this).manhuaDialog.close();
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
	$("#SPBZ_UID").val(spbzuid);
	if (type == "insert") {
		var resultobj = new Object();
		resultobj.ENABLED="1";
		$("#buSpBzThlyForm").setFormValues(resultobj);
			
	} else if (type == "detail") {
	
	} else {
		//生成json串
		var parentmain = $(window).manhuaDialog.getParentObj();
		var rowValue = parentmain.$("#resultXML").val();
		var tempJson = convertJson.string2json1(rowValue);
		$("#QTHLY_UID").val(tempJson.THLY_UID);
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
				$("#buSpBzThlyForm").setFormValues(resultobj);				
				return true;
			}
		});
	}
}

</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">
  
	<div style="height:5px;"></div>
	<div class="B-small-from-table-autoConcise">
      <h4 class="title">新增退回理由
      	<span class="pull-right">
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>		
<%--      		<button id="btnClear_Bins" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">清空</button>--%>
	  		<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">保存</button>
      	</span>
      </h4>
     <form action="post" id="buForm">  	
		<input type="hidden" id="QTHLY_UID" fieldname="THLY_UID" name="THLY_UID" operation="=" />
	 </form>
     <form method="post" id="buSpBzThlyForm"  >
      <table class="B-table" width="100%" >

	  	<input type="hidden" id="THLY_UID" fieldname="THLY_UID" name = "THLY_UID"/>
	  	<input type="hidden" id="SPBZ_UID" fieldname="SPBZ_UID" name = "SPBZ_UID"/>
	  	<input type="hidden" id="ENABLED" fieldname="ENABLED" name = "ENABLED" value='1'/>
	  	<input type="hidden" id="SERIAL_NO" fieldname="SERIAL_NO" name = "SERIAL_NO" value='100'/>

		<tr>
	        <th width="8%" class="right-border bottom-border text-right">退回理由</th>
	        <td colspan="3" class="bottom-border right-border" >
	        	<textarea class="span12" rows="4" id="LIYOU"  fieldname="LIYOU" name="LIYOU"></textarea>
	        </td>
        </tr>
        <tr>
	        <th width="8%" class="right-border bottom-border text-right">备注</th>
	        <td colspan="3" class="bottom-border right-border" >
	        	<textarea class="span12" rows="2" id="BZ" check-type="maxlength" maxlength="4000" fieldname="BZ" name="BZ"></textarea>
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
         <input type="hidden" name="resultXML" id = "resultXML">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
		
 	</FORM>
 </div>
</body>
<script>
</script>
</html>