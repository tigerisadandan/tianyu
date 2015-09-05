<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>预选评价内容维护</title>
<%
	String type=request.getParameter("type");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/wxgc/yxWgsjController";
var type ="<%=type%>";
//页面初始化
$(function() {
	init();
	
	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
		if($("#yxWgsjForm").validationButton()){
		    //生成json串
		    var data = Form2Json.formToJSON(yxWgsjForm);
		  //组成保存json串格式
		    var data1 = defaultJson.packSaveJson(data);
		  //调用ajax插入
		    if($("#WGSJ_UID").val() != "" && $("#WGSJ_UID").val() != null){
    			defaultJson.doInsertJson(controllername + "?update", data1);
    			$("#yxWgsjForm").clearFormResult();
    		}else{
    			defaultJson.doUpdateJson(controllername + "?insert", data1);
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
		//生成json串
		var parentmain = $(window).manhuaDialog.getParentObj();
		var rowValue = parentmain.$("#resultXML").val();
		var tempJson = convertJson.string2json1(rowValue);
		$("#QWGSJ_UID").val(tempJson.WGSJ_UID);
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
				$("#yxWgsjForm").setFormValues(resultobj);		
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
	<div class="B-small-from-table-autoConcise">
      <h4 class="title">
      	<span class="pull-right">
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
	  		<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">保存</button>
      	</span>
      </h4>
       <form method="post" id="queryForm"  >
    	  <input type="hidden" id="QWGSJ_UID" fieldname="WGSJ_UID" name = "WGSJ_UID" operation="="/>
       </form>
     <form method="post" id="yxWgsjForm"  >
      <table class="B-table" width="100%" >
	  	<input type="hidden" id="WGSJ_UID" fieldname="WGSJ_UID" name = "WGSJ_UID"/>
         
         <tr>
         	<th width="8%" class="right-border bottom-border text-right">评价内容</th>
       		<td class="bottom-border right-border" width="15%">
         		<select  id="WGSJFL"  style="width:30%"  name="WGSJFL" fieldname="WGSJFL"  kind="dic" src="WXGCXYPJLX" defaultMemo="--请选择--">
				</select>	
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">扣分分值</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="WGSJKFFZ" type="text" fieldname="WGSJKFFZ" name = "WGSJKFFZ" check-type="required" />分
         	</td>
         </tr>

        <tr>
	        <th width="8%" class="right-border bottom-border text-right">评价标准</th>
	        <td colspan="3" class="bottom-border right-border" >
	        	<textarea class="span12" style="width:50%" id="WGSJNR" check-type="maxlength" maxlength="4000" fieldname="WGSJNR" name="WGSJNR"></textarea>
	        </td>
        </tr>
        
        <tr>
	        <th width="8%" class="right-border bottom-border text-right">详细标准</th>
	        <td colspan="3" class="bottom-border right-border" >
	        	<textarea class="span12" style="width:50%" id="WGSJXXNR" check-type="maxlength" maxlength="4000" fieldname="WGSJXXNR" name="WGSJXXNR"></textarea>
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
         <input type="hidden" name="txtFilter"  order="desc" fieldname = "WGSJ_UID" id = "txtFilter">
         <input type="hidden" name="resultXML" id = "resultXML">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
		
 	</FORM>
 </div>
</body>
<script>
</script>
</html>