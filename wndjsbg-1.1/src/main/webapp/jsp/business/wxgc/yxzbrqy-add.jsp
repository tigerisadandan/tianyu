<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>招标人(实施方区域)维护</title>
<%
	String type=request.getParameter("type");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/wxgc/yxUserQyController";
var type ="<%=type%>";
//页面初始化
$(function() {
	init();
	
	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
		if($("#yxUserQyForm").validationButton()){
		    //生成json串
		    var data = Form2Json.formToJSON(yxUserQyForm);
		  //组成保存json串格式
		    var data1 = defaultJson.packSaveJson(data);
		  //调用ajax插入
		    if($("#USER_QY_UID").val() != "" && $("#USER_QY_UID").val() != null){
    			defaultJson.doInsertJson(controllername + "?update", data1);
    			$("#yxUserQyForm").clearFormResult();
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
		$("#QUSER_QY_UID").val(tempJson.USER_QY_UID);
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
				$("#yxUserQyForm").setFormValues(resultobj);		
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
    	  <input type="hidden" id="QUSER_QY_UID" fieldname="USER_QY_UID" name = "USER_QY_UID" operation="="/>
       </form>
     <form method="post" id="yxUserQyForm"  >
      <table class="B-table" width="100%" >
	  	<input type="hidden" id="USER_QY_UID" fieldname="USER_QY_UID" name = "USER_QY_UID"/>
          <tr>
			<th width="8%" class="right-border bottom-border text-right">实施方名称</th>
       	 	<td class="bottom-border right-border" colspan="3">
         		<input  id="QY_NAME" check-type="required"  type="text" fieldname="QY_NAME" name = "QY_NAME" placeholder="必填" class="span12" style="width:50%"/>
       	 	</td> 
          </tr>
         <tr>
			<th width="8%" class="right-border bottom-border text-right">实施方编码</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="QY_CODE" check-type="required"  type="text" fieldname="QY_CODE" name = "QY_CODE" placeholder="必填" />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">实施方类型</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="QY_TYPE" type="radio" kind="dic" src="WXGC_QYLX"  name="QY_TYPE" fieldname="QY_TYPE" defaultValue="1">
         	</td>
         </tr>
       
         <tr>
			<th width="8%" class="right-border bottom-border text-right">地址</th>
       	 	<td class="bottom-border right-border" colspan="3">
         		<input  id="DZ" check-type="required"  type="text" fieldname="DZ" name = "DZ" placeholder="必填" class="span12" style="width:50%"/>
       	 	</td> 
          </tr>
        <tr>
			<th width="8%" class="right-border bottom-border text-right">联系人</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="LXR" check-type="required"  type="text" fieldname="LXR" name = "LXR" placeholder="必填" />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">联系电话</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="LXDH" check-type="required"  type="text" fieldname="LXDH" name = "LXDH" placeholder="必填" />
         	</td>
         </tr>
          <tr>
			<th width="8%" class="right-border bottom-border text-right">邮政编号</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="YZBH" check-type="required"  type="text" fieldname="YZBH" name = "YZBH" placeholder="必填" />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">传真号</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="CZH" check-type="required"  type="text" fieldname="CZH" name = "CZH" placeholder="必填" />
         	</td>
         </tr>
       
       <tr>
			<th width="8%" class="right-border bottom-border text-right">电子邮箱</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="EMAIL" type="text" fieldname="EMAIL" name = "EMAIL" />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">网址</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="WEBWZ"  type="text" fieldname="WEBWZ" name = "WEBWZ"  />
         	</td>
         </tr>
        <tr>
			<th width="8%" class="right-border bottom-border text-right">开户银行</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="KHYH" check-type="required"  type="text" fieldname="KHYH" name = "KHYH" placeholder="必填" />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">开户银行帐号</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="KHYHZH" check-type="required"  type="text" fieldname="KHYHZH" name = "KHYHZH" placeholder="必填" />
         	</td>
         </tr>
       
        <tr>
	        <th width="8%" class="right-border bottom-border text-right">备注</th>
	        <td colspan="3" class="bottom-border right-border" >
	        	<textarea class="span12" style="width:50%" id="BZ" check-type="maxlength" maxlength="4000" fieldname="BZ" name="BZ"></textarea>
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
         <input type="hidden" name="txtFilter"  order="desc" fieldname = "USER_QY_UID" id = "txtFilter">
         <input type="hidden" name="resultXML" id = "resultXML">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
		
 	</FORM>
 </div>
</body>
<script>
</script>
</html>