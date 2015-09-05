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
var controllername= "${pageContext.request.contextPath }/jxsb/jxsbDsjjController";
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
		$("#QJXSB_DSJJ_UID").val(tempJson.JXSB_DSJJ_UID);
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
     		<input type="hidden" id="QJXSB_DSJJ_UID" fieldname="JXSB_DSJJ_UID" name="JXSB_DSJJ_UID" operation="=" />
      </form>
     <form method="post" id="jsCompanyForm"  >
	<div class="B-small-from-table-autoConcise">
      <h4 class="title">设备顶升加节附墙记录 
      	<span class="pull-right">
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
      	</span>
      </h4>
       
      <div style="height:5px;"></div>
     	<table class="B-table" width="100%" >
      		<input type="hidden" id="JXSB_DSJJ_UID" fieldname="JXSB_DSJJ_UID" name = "JXSB_DSJJ_UID"/>
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
				<th width="15%" class="right-border bottom-border text-right">使用栋号</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="DONGSHU" type="text" readonly="readonly" fieldname="DONGSHU" name = "DONGSHU"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">检测报告编号</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input  class="span12"  style="width:100%" id="JCBG_BH" type="text" readonly="readonly" fieldname="JCBG_BH" name = "JCBG_BH"  />
				</td>
	        </tr>
	         <tr>
				<th width="15%" class="right-border bottom-border text-right">设置安装日期</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input style="width:40%" id="SBAZ_E_DATE" type="text" readonly="readonly" fieldname="SBAZ_E_DATE" name = "SBAZ_E_DATE"  />&nbsp;&nbsp;安装初始高度
	       	 	<input  style="width:15%" id="HEIGHT" type="text" readonly="readonly" fieldname="HEIGHT" name = "HEIGHT"  />	  	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">使用的登记编号</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input  class="span12"  style="width:100%" id="SYDJ_BH" type="text" readonly="readonly" fieldname="SYDJ_BH" name = "SYDJ_BH"  />
				</td>
	        </tr>
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">设备产权单位</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="CQDW" type="text" readonly="readonly" fieldname="CQDW" name = "CQDW"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">维修保养单位</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input  class="span12"  style="width:100%" id="COMPANY_NAME" type="text" readonly="readonly" fieldname="COMPANY_NAME" name = "COMPANY_NAME"  />
				</td>
	        </tr>
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">使用单位</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="GCSG_DANWEI" type="text" readonly="readonly" fieldname="GCSG_DANWEI" name = "GCSG_DANWEI"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">监理单位</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input  class="span12"  style="width:100%" id="" type="text" readonly="readonly" fieldname="" name = ""  /><!-- 12.25 暂不查询 -->
				</td>
	        </tr>
      	</table>
    </div>
    
    <div class="B-small-from-table-autoConcise">
      <h4 class="title">机械设备顶升加节附墙 
      </h4>
      <div style="height:5px;"></div>
     	<table class="B-table" width="100%" >
      		<input type="hidden" id="JXSB_BYWX_UID" fieldname="JXSB_BYWX_UID" name = "JXSB_BYWX_UID"/>
      		<tr>
				<th width="15%" class="right-border bottom-border text-right">顶升日期</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="DS_DATE" type="text" readonly="readonly"  fieldname="DS_DATE" name = "DS_DATE"  />	         	        	
	       	 	</td>
	        </tr>
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">本次顶升高度</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="NEW_HEIGHT" type="text" readonly="readonly"  fieldname="NEW_HEIGHT" name = "NEW_HEIGHT"  />	         	        	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">顶升后塔吊总高度  </th>
	       	 	<td colspan="3" class="bottom-border right-border">
					  <input  class="span12"  id="DS_HEIGHT" type="text" readonly="readonly"  name = "DS_HEIGHT" fieldname="DS_HEIGHT" />
					
				</td>
	        </tr>
			
			<tr>
				<th width="15%" class="right-border bottom-border text-right">附着间距</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="FZJJ" type="text" readonly="readonly"  fieldname="FZJJ" name = "FZJJ"  />	         	        	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">塔身自由端高度  </th>
	       	 	<td colspan="3" class="bottom-border right-border">
	       	 	<div id="dis">
					  <input  class="span12"  style="width:100%" id="ZYD_HEIGHT" type="text" readonly="readonly"  name = "ZYD_HEIGHT" fieldname="ZYD_HEIGHT" />
					  </div
				</td>
	        </tr>
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">本次附墙高度</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="FQGD" type="text" readonly="readonly"  fieldname="FQGD" name = "FQGD"  />	         	        	
	       	 	</td>
	        </tr>
	         <tr>
				<th width="15%" class="right-border bottom-border text-right">操作人员名单</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="CZ_REN" type="text" readonly="readonly" fieldname="CZ_REN" name = "CZ_REN"  />	     	
	       	 	</td>
	        </tr>
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">备注</th>
	       	 	<td class="bottom-border right-border" colspan="3">
	         		<textarea class="span12" rows="2" id="DESCRIBE" check-type="required" maxlength="4000"  fieldname="DESCRIBE" name="DESCRIBE"></textarea>
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