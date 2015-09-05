<!DOCTYPE html>
<%@page import="com.ccthanking.framework.Constants"%>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>建设单位-维护</title>
<%
	String type=request.getParameter("type");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/jxsb/jxsbBywxController";
var type ="<%=type%>";
//页面初始化
$(function() {
	init();
	$("#btnClose").bind("click", function(){
		var a=$(this).manhuaDialog.close();
	});
	
});
//页面默认参数
function init(){

		//生成json串
		var parentmain = $(window).manhuaDialog.getParentObj();
		var rowValue = parentmain.$("#resultXML").val();
		var tempJson = convertJson.string2json1(rowValue);
		$("#QJXSB_BYWX_UID").val(tempJson.JXSB_BYWX_UID);
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
				$("#jsCompanyForm").setFormValues(resultobj);	
		
				return true;
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
      <h4 class="title">设备保养、维护 
      	<span class="pull-right">
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
      	</span>
      </h4>
       <form method="post" id="queryForm"  >
     		<input type="hidden" id="QJXSB_BYWX_UID" fieldname="JXSB_BYWX_UID" name="JXSB_BYWX_UID" operation="=" />
      </form>
      <div style="height:5px;"></div>
     <form method="post" id="jsCompanyForm"  >
     	<table class="B-table" width="100%" >
      		<input type="hidden" id="JXSB_BYWX_UID" fieldname="JXSB_BYWX_UID" name = "JXSB_BYWX_UID"/>
      		
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">工程名称</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="GC_NAME" type="text" readonly="readonly"  fieldname="GC_NAME" name = "GC_NAME"  />	         	        	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">产权编号  </th>
	       	 	<td colspan="3" class="bottom-border right-border">
					  <input  class="span12"  id="CQ_BH" type="text" readonly="readonly"  name = "CQ_BH" fieldname="CQ_BH" />
					
				</td>
	        </tr>
			
			<tr>
				<th width="15%" class="right-border bottom-border text-right">设备名称</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="SHEBEI_NAME" type="text" readonly="readonly"  fieldname="SHEBEI_NAME" name = "SHEBEI_NAME"  />	         	        	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">设备型号  </th>
	       	 	<td colspan="3" class="bottom-border right-border">
	       	 	<div id="dis">
					  <input  class="span12"  style="width:100%" id="SB_XH" type="text" readonly="readonly"  name = "SB_XH" fieldname="SB_XH" />
					  </div
				</td>
	        </tr>
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">维保单位</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="COMPANY_NAME" type="text" readonly="readonly" fieldname="COMPANY_NAME" name = "COMPANY_NAME"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">检测报告编号</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input  class="span12"  style="width:100%" id="JCBG_BH" type="text" readonly="readonly" fieldname="JCBG_BH" name = "JCBG_BH"  />
				</td>
	        </tr>
	         <tr>
				<th width="15%" class="right-border bottom-border text-right">操作人</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="CZ_REN1" type="text" readonly="readonly" fieldname="CZ_REN1" name = "CZ_REN1"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">操作证号</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input  class="span12"  style="width:100%" id="CZ_ZH1" type="text" readonly="readonly" fieldname="CZ_ZH1" name = "CZ_ZH1"  />
				</td>
	        </tr>
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">操作人</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="CZ_REN2" type="text" readonly="readonly" fieldname="CZ_REN2" name = "CZ_REN2"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">操作证号</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input  class="span12"  style="width:100%" id="CZ_ZH2" type="text" readonly="readonly" fieldname="CZ_ZH2" name = "CZ_ZH2"  />
				</td>
	        </tr>
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">操作人</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="CZ_REN3" type="text" readonly="readonly" fieldname="CZ_REN3" name = "CZ_REN3"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">操作证号</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input  class="span12"  style="width:100%" id="CZ_ZH3" type="text" readonly="readonly" fieldname="CZ_ZH3" name = "CZ_ZH3"  />
				</td>
	        </tr>
	         <tr>
				<th width="15%" class="right-border bottom-border text-right">保养日期</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="BY_DATE" type="text" readonly="readonly" fieldname="BY_DATE" name = "BY_DATE"  />	     	
	       	 	</td>
	        </tr>
			
			<tr>
				<th width="15%" class="right-border bottom-border text-right">设备保养记录</th>
	       	 	<td class="bottom-border right-border" colspan="3">
	         		<textarea class="span12" rows="2" id="BY_JL" check-type="required" maxlength="4000"  fieldname="BY_JL" name="BY_JL"></textarea>
	       	 	</td>
	        </tr>
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">设备维修记录</th>
	       	 	<td class="bottom-border right-border" colspan="3">
	         		<textarea class="span12" rows="2" id="WX_JL" check-type="required" maxlength="4000"  fieldname="WX_JL" name="WX_JL"></textarea>
	       	 	</td>
	        </tr>
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">备注</th>
	       	 	<td class="bottom-border right-border" colspan="3">
	         		<textarea class="span12" rows="2" id="DESCRIBE" check-type="required" maxlength="4000"  fieldname="DESCRIBE" name="DESCRIBE"></textarea>
	       	 	</td>
	        </tr>
      	</table>
      </form>
        <jsp:include page="/jsp/file_upload/fileuploadold_config_js.jsp" flush="true"/> 
    </div>
   </div>
  </div>

 
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