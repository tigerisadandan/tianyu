<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>微型工程类型维护</title>
<%
	String type=request.getParameter("type");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/wxgc/yxGcTypeController";
var type ="<%=type%>";
//页面初始化
$(function() {
	init();
	
	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
		if($("#yxGcTypeForm").validationButton()){
		    //生成json串
		    var data = Form2Json.formToJSON(yxGcTypeForm);
		  //组成保存json串格式
		    var data1 = defaultJson.packSaveJson(data);
		  //调用ajax插入
		    if($("#GC_TYPE_UID").val() != "" && $("#GC_TYPE_UID").val() != null){
    			defaultJson.doUpdateJson(controllername + "?update", data1);
    		}else{
    			defaultJson.doInsertJson(controllername + "?insert", data1);
    		}
		    var a=$(window).manhuaDialog.getParentObj();
		    a.init();
			$(window).manhuaDialog.close();
		}else{
			requireFormMsg();
		  	return;
		}
	});

	$("#btnClose").bind("click", function(){
		var a=$(this).manhuaDialog.close();
	});

    <%
    if(type.equals("detail")){
	%>
	//置所有input 为disabled
	$(":input").each(function(i){
	   $(this).attr("disabled", "true");
	 });
	 $("#btnClose").removeAttr('disabled');
	 $("#btnSave").hide();
	<%
		}
	%>
	
});
//页面默认参数
function init(){
	if(type == "insert"){
	}else if(type == "update" || type == "detail"){
		$("#GC_TYPE_CODE").attr("readonly", "readonly");
		//生成json串
		var parentmain = $(window).manhuaDialog.getParentObj();
		var rowValue = parentmain.$("#resultXML").val();
		var tempJson = convertJson.string2json1(rowValue);
		$("#QGC_TYPE_UID").val(tempJson.GC_TYPE_UID);
		//查询表单赋值
		var data = combineQuery.getQueryCombineData(queryForm, frmPost);
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
				$("#yxGcTypeForm").setFormValues(resultobj);		
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
      <h4 class="title">
      	<span class="pull-right">
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
	  		<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">保存</button>
      	</span>
      </h4>
     <form method="post" id="yxGcTypeForm"  >
      <table class="B-table" width="100%" >
     	 <input type="hidden" id="GC_TYPE_UID" fieldname="GC_TYPE_UID" name = "GC_TYPE_UID"/></TD>
        
         <tr>
			<th width="8%" class="right-border bottom-border text-right">类型代码</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="GC_TYPE_CODE" check-type="required"  type="text" fieldname="GC_TYPE_CODE" name = "GC_TYPE_CODE" placeholder="必填" />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">类型名称</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="GC_TYPE_NAME" check-type="required"  type="text" fieldname="GC_TYPE_NAME" name = "GC_TYPE_NAME" placeholder="必填" />
         	</td>
         </tr>
         <tr>
			<th width="8%" class="right-border bottom-border text-right">所属承包商类型</th>
       		<td class="bottom-border right-border" width="15%">
         		<select  id="CBS_TYPE"  check-type="required"  name="CBS_TYPE" fieldname="CBS_TYPE"  kind="dic" src="WXGC_YXCBSLX" defaultMemo="--请选择--">
				</select>
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">明标价限额</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="GC_MBJGXE" check-type="required"  type="text" fieldname="GC_MBJGXE" name = "GC_MBJGXE" placeholder="必填" />元
         	</td>
         </tr>
        <tr>
			<th width="8%" class="right-border bottom-border text-right">链接地址</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="LINK"   type="text" fieldname="LINK" name = "LINK" />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">链接返回地址</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="RETURN_LINK"   type="text" fieldname="RETURN_LINK" name = "RETURN_LINK"  />
         	</td>
         </tr>
        
      </table>
      </form>
    </div>
   </div>
  </div>
  <form method="post" id="queryForm"  >
    	  <input type="hidden" id="QGC_TYPE_UID" fieldname="GC_TYPE_UID" name = "GC_TYPE_UID" operation="="/>
  </form>
  <div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id = "queryXML">
         <input type="hidden" name="txtXML" id = "txtXML">
         <input type="hidden" name="txtFilter"  order="desc" fieldname = "GC_TYPE_UID" id = "txtFilter">
         <input type="hidden" name="resultXML" id = "resultXML">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
		
 	</FORM>
 </div>
</body>
<script>
</script>
</html>