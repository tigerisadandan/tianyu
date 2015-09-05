<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>用户-维护</title>
<%
	String type=request.getParameter("type");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/wxgc/yxUserController";
var type ="<%=type%>";
//页面初始化
$(function() {
	init();

	$("#btnCzmm").click(function() {
		$("#IS_CZMM").val("1");
	    //生成json串
	    var data = Form2Json.formToJSON(yxUserForm);
	  //组成保存json串格式
	    var data1 = defaultJson.packSaveJson(data);
	    defaultJson.doUpdateJson(controllername + "?update", data1);
	});
	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
		if($("#yxUserForm").validationButton()){
		    //生成json串
		    var data = Form2Json.formToJSON(yxUserForm);
		  //组成保存json串格式
		    var data1 = defaultJson.packSaveJson(data);
		  //调用ajax插入
		    if($("#USER_UID").val() == "" || $("#USER_UID").val() == null){
    			defaultJson.doInsertJson(controllername + "?insert", data1);
    			$("#yxUserForm").clearFormResult();
    		}else{
    			$("#IS_CZMM").val("0");
    			defaultJson.doUpdateJson(controllername + "?update", data1);
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
	 $("#btnCzmm").removeAttr('disabled');
	 $("#btnClose").removeAttr('disabled');
	 $("#btnSave").hide();
	<%
		}
	%>
	
});
//页面默认参数
function init(){
	if(type == "insert"){
		$("#btnCzmm").hide();
	}else if(type == "update" || type == "detail"){
		//生成json串
		var parentmain = $(window).manhuaDialog.getParentObj();
		var rowValue = parentmain.$("#resultXML").val();
		var tempJson = convertJson.string2json1(rowValue);
		$("#QUERY_USER_UID").val(tempJson.USER_UID);
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
				$("#yxUserForm").setFormValues(resultobj);		
				$("#USER_CODE").attr("readonly", "readonly");
				return true;
			}
		});
		
	}
}

function isExist(){
	var useruid=$("#USER_UID").val();
	if(useruid!=null&&useruid!=''){
      return;
	}
	var usercode=$("#USER_CODE").val();

	$("#QUSER_CODE").val(usercode);
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
			if(response.msg!=0){
				var res = dealSpecialCharactor(response.msg);
				var resultobj = defaultJson.dealResultJson(res);
			//	alert(resultobj.USER_CODE);
				var code= resultobj.USER_CODE;
				if(usercode==code){
					$("#usercodemes").html("*帐号已存在，请更换其他帐号");
					$("#USER_CODE").val("");
				}
			}
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
      <h4 class="title">
      	<span class="pull-right">
      		<button id="btnCzmm" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">重置密码</button>
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
	  		<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">保存</button>
      	</span>
      </h4>
     <form method="post" id="yxUserForm"  >
       <table class="B-table" width="100%" >
       	  <input type="hidden" id="IS_CZMM" fieldname="IS_CZMM" name = "IS_CZMM"/>
	  	  <input type="hidden" id="USER_UID" fieldname="USER_UID" name = "USER_UID"/>
	  	  <tr>
			<th width="8%" class="right-border bottom-border text-right">所属实施方</th>
       	 	<td class="bottom-border right-border" colspan="3">
         		<select  id="QY_CODE" check-type="required" fieldname="QY_CODE" name ="QY_CODE" kind="dic" src="T#YX_USER_QY:QY_CODE:QY_NAME:ENABLED='1' order by SERIAL_NO asc "></select>
       	 	</td> 
          </tr>
          <tr>
			<th width="8%" class="right-border bottom-border text-right">姓名</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="USER_NAME" check-type="required"  type="text" fieldname="USER_NAME" name = "USER_NAME" placeholder="必填" />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">职务</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="ZHIWU" type="text" fieldname="ZHIWU" name = "ZHIWU" />
         	</td>
         </tr>
         <tr>
			<th width="8%" class="right-border bottom-border text-right">登录帐号</th>
       	 	<td class="bottom-border right-border" width="15%">
         		<input  id="USER_CODE" type="text" fieldname="USER_CODE" name = "USER_CODE"  placeholder="必填" check-type="required" onblur="isExist();" />
       	 		<font color="red"><span id="usercodemes"></span></font>
       	 	</td>  
         	
			<th width="8%" class="right-border bottom-border text-right">所属角色</th>
       		<td class="bottom-border right-border" width="15%">
         		<select  id="USER_ROLE" check-type="required" fieldname="USER_ROLE" name ="USER_ROLE" kind="dic" src="WXGC_USERROLE"></select>
         		
         	</td>
        </tr>
        
         <tr>
			<th width="8%" class="right-border bottom-border text-right">手机号码</th>
       	 	<td class="bottom-border right-border" width="15%">
         		<input  id="MOBILE" type="text" fieldname="MOBILE" name = "MOBILE"  />
       	 	</td>
         	<th width="8%" class="right-border bottom-border text-right">邮箱</th>
       	 	<td class="bottom-border right-border" width="15%">
         		<input  id="EMAIL" type="text" fieldname="EMAIL" name = "EMAIL" />
       	 	</td>  
        </tr>
      </table>
      </form>
    </div>
   </div>
  </div>
  <form method="post" id="queryForm"  >
    	  <input type="hidden" id="QUERY_USER_UID" fieldname="USER_UID" name = "USER_UID" operation="="/>
    	   <input type="hidden" id="QUSER_CODE" fieldname="USER_CODE" name = "USER_CODE" operation="="/>
  </form>
  <div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id = "queryXML">
         <input type="hidden" name="txtXML" id = "txtXML">
         <input type="hidden" name="txtFilter"  order="desc" fieldname = "UPDATE_DATE" id = "txtFilter">
         <input type="hidden" name="resultXML" id = "resultXML">
       	<!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
 	</FORM>
 </div>
</body>
<script>
</script>
</html>