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
var controllername= "${pageContext.request.contextPath }/jxsb/jxsbJjsyController";
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
		$("#QJXSB_JJSY_UID").val(tempJson.JXSB_JJSY_UID);
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
	<form method="post" id="queryForm"  >
     		<input type="hidden" id="QJXSB_JJSY_UID" fieldname="JXSB_JJSY_UID" name="JXSB_JJSY_UID" operation="=" />
      </form>
     <form method="post" id="jsCompanyForm"  >
	<div class="B-small-from-table-autoConcise">
      <h4 class="title">机械设备降级使用记录
      	<span class="pull-right">
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
      	</span>
      </h4>
       
      <div style="height:5px;"></div>
     	<table class="B-table" width="100%" >
      		<input type="hidden" id="JXSB_JJSY_UID" fieldname="JXSB_JJSY_UID" name = "JXSB_JJSY_UID"/>
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">工程名称</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="GC_NAME" type="text" readonly="readonly"  fieldname="GC_NAME" name = "GC_NAME"  />	         	        	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">设备名称</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="SHEBEI_NAME" type="text" readonly="readonly"  fieldname="SHEBEI_NAME" name = "SHEBEI_NAME"  />	         	        	
	       	 	</td>
	        </tr>
			
	        <tr>
	           <th width="15%" class="right-border bottom-border text-right">设备产权编号  </th>
	       	 	<td  class="bottom-border right-border">
					  <input  class="span12"  id="CQ_BH" type="text" readonly="readonly"  name = "CQ_BH" fieldname="CQ_BH" />
				</td>
				<th width="15%" class="right-border bottom-border text-right">设备产权单位</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="CQDW" type="text" readonly="readonly" fieldname="CQDW" name = "CQDW"  />	     	
	       	 	</td>
	        </tr>
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">设备类型</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="JXSB_TYPE_UID" type="text" readonly="readonly" fieldname="JXSB_TYPE_UID_SV" name = "JXSB_TYPE_UID"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">设备型号(降级后)</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input  class="span12"  style="width:100%" id="NEW_SB_XH" type="text" readonly="readonly" fieldname="NEW_SB_XH" name = "NEW_SB_XH"  />
				</td>
	        </tr>
	         <tr>
				<th width="15%" class="right-border bottom-border text-right">制造厂家  </th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="ZZDW" type="text" readonly="readonly" fieldname="ZZDW" name = "ZZDW"  />  	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">制造许可证</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input  class="span12"  style="width:100%" id="ZZXKZ" type="text" readonly="readonly" fieldname="ZZXKZ" name = "ZZXKZ"  />
				</td>
	        </tr>
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">厂家地址</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="ZZXKZ" type="text" readonly="readonly" fieldname="ZZXKZ" name = "ZZXKZ"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">出厂编号</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input  class="span12"  style="width:100%" id="CC_CODE" type="text" readonly="readonly" fieldname="CC_CODE" name = "CC_CODE"  />
				</td>
	        </tr>
	         <tr>
				<th width="15%" class="right-border bottom-border text-right">结构检验报告编号</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="REPORT_CODE" type="text" readonly="readonly" fieldname="REPORT_CODE" name = "REPORT_CODE"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">有效期(设备寿命期)</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input  class="span12"  style="width:100%" id="YOUXIAO_DATE" type="text" readonly="readonly" fieldname="YOUXIAO_DATE" name = "YOUXIAO_DATE"  />
				</td>
	        </tr>
      	</table>
    </div>
    
    </form>
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