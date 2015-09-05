<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>招标代理机构维护-维护</title>
<%
	String type=request.getParameter("type");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/wxgc/ztbZbdlController";
var type ="<%=type%>";
//页面初始化
$(function() {
	init();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,ztbZbdlList);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"?query",data,ztbZbdlList);
	});
	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
		if($("#ztbZbdlForm").validationButton()){
		    //生成json串
		    var data = Form2Json.formToJSON(ztbZbdlForm);
		  //组成保存json串格式
		    var data1 = defaultJson.packSaveJson(data);
		  //调用ajax插入
		    if($("#ZTB_ZBDL_UID").val() != "" &&$("#ZTB_ZBDL_UID").val() != null){
    			//defaultJson.doInsertJson(controllername + "?update", data1,ztbZbdlList); 修改原因：ztbZbdlList找不到
    			defaultJson.doInsertJson(controllername + "?update", data1);
    			$("#ztbZbdlForm").clearFormResult();
    		}else{
    			//defaultJson.doUpdateJson(controllername + "?insert", data1,ztbZbdlList);  修改原因：ztbZbdlList找不到
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
		$("#QZTB_ZBDL_UID").val(tempJson.ZTB_ZBDL_UID);
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
				$("#ztbZbdlForm").setFormValues(resultobj);		
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
     <form method="post" id="ztbZbdlForm"  >
      <table class="B-table" width="100%" >
	  	<input type="hidden" id="ZTB_ZBDL_UID" fieldname="ZTB_ZBDL_UID" name = "ZTB_ZBDL_UID"/>
	  	 <tr>
			<th width="8%" class="right-border bottom-border text-right">机构名称</th>
       	 	<td class="bottom-border right-border" colspan="3">
       	 		<input class="span12" style="width:50%" id="ZBDL_NAME" type="text" placeholder="必填" check-type="required" fieldname="ZBDL_NAME" name = "ZBDL_NAME"/>
       	 	</td> 
        </tr>
        
         <tr>
				<th width="8%" class="right-border bottom-border text-right">营业执照号</th>
	       	 	<td class="bottom-border right-border" width="15%">
	         		<input    id="ZHIZHAO"  type="text" fieldname="ZHIZHAO" name = "ZHIZHAO" placeholder="必填" check-type="required"/>
	       	 	</td> 
	       	 	<th width="8%" class="right-border bottom-border text-right">营业执照有效期</th>
	       		<td class="bottom-border right-border" width="15%">
	         		<input  id="ZHIZHAO_VALID"   type="text" fieldname="ZHIZHAO_VALID" name = "ZHIZHAO_VALID" class='Wdate' onClick='WdatePicker()'/>
	         	</td>      	
         </tr>
        <tr>
			<th width="8%" class="right-border bottom-border text-right">税务登记号</th>
       	 	<td class="bottom-border right-border" colspan="3">
         		<input class="span12" style="width:50%" id="SHUIWU" type="text" placeholder="必填" check-type="required" fieldname="SHUIWU" name = "SHUIWU"  />
       	 	</td>
        </tr>
        
         <tr>
			<th width="8%" class="right-border bottom-border text-right">开户银行</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="BANK"   type="text" fieldname="BANK" name = "BANK" />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">开户行帐号</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="BANK_ACCOUNT" type="text" fieldname="BANK_ACCOUNT" name = "BANK_ACCOUNT" />
         	</td>
         </tr>
         <tr>
			<th width="8%" class="right-border bottom-border text-right">注册资金</th>
       		<td class="bottom-border right-border" width="15%" colspan="3">
         		<input  id="ZHUCE_ZIJIN"   type="text" fieldname="ZHUCE_ZIJIN" name = "ZHUCE_ZIJIN" />万元
         	</td>
         	
         </tr>
          <tr>
			<th width="8%" class="right-border bottom-border text-right">公司地址</th>
       		<td class="bottom-border right-border" colspan="3">
         		<input  id="ADDRESS"  type="text" fieldname="ADDRESS" name = "ADDRESS" />
         	</td>
         	
         </tr>
          <tr>
			<th width="8%" class="right-border bottom-border text-right">公司电话</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="PHONE"  type="text" fieldname="PHONE" name = "PHONE" />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">公司传真</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="FAX" type="text" fieldname="FAX" name = "FAX" />
         	</td>
         </tr>
          <tr>
			<th width="8%" class="right-border bottom-border text-right">邮政编码</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="POSTCODE"  type="text" fieldname="POSTCODE" name = "POSTCODE" />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">公司主页</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="URL" type="text" fieldname="URL" name = "URL" />
         	</td>
         </tr>
         <tr>
			<th width="8%" class="right-border bottom-border text-right">法人代表</th>
       		<td class="bottom-border right-border" colspan="3">
         		<input  id="FAREN"  type="text" fieldname="FAREN" name = "FAREN" />
         	</td>
         	
         </tr>
          <tr>
			<th width="8%" class="right-border bottom-border text-right">法人职称</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="FAREN_ZHICHENG"  type="text" fieldname="FAREN_ZHICHENG" name = "FAREN_ZHICHENG" />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">手机号码</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="FAREN_MOBILE" type="text" fieldname="FAREN_MOBILE" name = "FAREN_MOBILE" />
         	</td>
         </tr>
        <tr>
			<th width="8%" class="right-border bottom-border text-right">联系人</th>
       		<td class="bottom-border right-border" colspan="3">
         		<input  id="LIANXIREN"  type="text" fieldname="LIANXIREN" name = "LIANXIREN" />
         	</td>
         	
         </tr>
          <tr>
			<th width="8%" class="right-border bottom-border text-right">联系人电话</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="LIANXIREN_MOBILE"  type="text" fieldname="LIANXIREN_MOBILE" name = "LIANXIREN_MOBILE" />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">联系人邮箱</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="LIANXIREN_MAIL" type="text" fieldname="LIANXIREN_MAIL" name = "LIANXIREN_MAIL" />
         	</td>
         </tr>
        <tr>
	        <th width="8%" class="right-border bottom-border text-right">备注</th>
	        <td colspan="3" class="bottom-border right-border" >
	        	<textarea class="span12" style="width:50%" rows="2" id="DESCRIPTION" check-type="maxlength" maxlength="4000" fieldname="DESCRIPTION" name="DESCRIPTION"></textarea>
	        </td>
        </tr>
      </table>
      </form>
    </div>
   </div>
  </div>
  <form method="post" id="queryForm"  >
    <input type="hidden" id="QZTB_ZBDL_UID" fieldname="ZTB_ZBDL_UID" name = "ZTB_ZBDL_UID" operation="="/>
  </form>
  <div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id = "queryXML">
         <input type="hidden" name="txtXML" id = "txtXML">
         <input type="hidden" name="txtFilter"  order="desc" fieldname = "ZTB_ZBDL_UID" id = "txtFilter">
         <input type="hidden" name="resultXML" id = "resultXML">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
		
 	</FORM>
 </div>
</body>
<script>
</script>
</html>