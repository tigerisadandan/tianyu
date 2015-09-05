<%@page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext"%>
<%@page import="com.ccthanking.framework.model.User"%>
<%@page import="com.ccthanking.framework.Globals"%>
<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>

<app:base/>
<title>人员信息首页</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/nbgl/waiChuDengJiController.do";

//页面初始化
$(function() {
	 init();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"?query",data,DT1);
	});
	
	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#find").clearFormResult(); 
    });
});
//页面默认参数
function init(){
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入数据到表格
	defaultJson.doQueryJsonList(controllername+"?query",data,DT1);
}
function caozuoFun(){
	
}
//查看详细信息
function doRandering(obj){
	var showHtml = "";
	showHtml = "<a href='javascript:rowView1("+obj.WAICHU_DENGJI_UID+");' >"+obj.RENYUAN_NAME+"</a></i>";
	return showHtml;
}
function rowView1(obj){
	     $(window).manhuaDialog({"title":"外出登记信息","type":"text","content":"${pageContext.request.contextPath }/jsp/business/nbgl/waichudengji-detail.jsp?wcdjId="+obj,"modal":"2"});  
}
</script>
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
	<p></p>
	<div class="row-fluid">
		<div class="B-small-from-table-autoConcise">
			<form method="post" id="queryForm">
				<table class="B-table" width="100%">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<TR style="display: none;">
						<TD class="right-border bottom-border">
						</TD>
					</TR>
				<!--可以再此处加入hidden域作为过滤条件 -->
			     <tr>

			     <td width="70%">
			     <table class="B-table" width="100%" id="find">
			     <tr>
			     <th width="3%" class="right-border bottom-border">时间</th>
						<td width="10%" class="right-border bottom-border ">
                           <input  id="WAICHU_SHIJIAN" type="text" fieldtype="date" fieldformat="yyyy-MM-dd" fieldname="WAICHU_SHIJIAN" name = "WAICHU_SHIJIAN" class='Wdate'  operation="="  onClick="WdatePicker()"/>
						</td>
				<td class="right-border bottom-border text-right" width="5%" rowspan="2" style="background-image: none; background-position: initial initial; background-repeat: initial initial;">
	                  <button id="btnQuery" class="btn btn-link"  type="button"><i class="icon-search"></i>查询</button>
	                 <button id="btnClear" class="btn btn-link" type="button"><i class="icon-trash"></i>清空</button>
           	   </td>
           	   </tr>
           	  </table>
           	  </td>
			   </tr>
			   </table>
			</form>
			<div style="height:5px;"> </div>	
			<div class="overFlowX">	
	            <table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single"  pageNum="18">
	                <thead>
	                	<tr>
	                		
	                		<th rowspan="2" fieldname="WAICHU_DENGJI_UID" width="4%" tdalign="center" hidden colindex=0 noprint="true">&nbsp;&nbsp;</th>
	                		<th fieldname="RENYUAN_NAME" colindex=1 tdalign="center"  maxlength="10" CustomFunction="doRandering"  >&nbsp;人员姓名&nbsp;</th>
	                		<th fieldname="WAICHU_DIDIAN" colindex=2 tdalign="center" maxlength="10">&nbsp;外出地点&nbsp;</th>
	                		<th fieldname="WAICHU_SHIJIAN" colindex=3 tdalign="center" width="25%">&nbsp;外出时间&nbsp;</th>
							<th fieldname="YJ_FH_SHIJIAN" colindex=4 tdalign="center" width="25%"  >&nbsp;预计返回时间&nbsp;</th>
							<th fieldname="FANHUI_SHIJIAN" colindex=5 width="25%" tdalign="center" >&nbsp;实际返回时间&nbsp;</th>
	                	</tr>
	                </thead>
	              	<tbody></tbody>
	           </table>
	       </div>
	 	</div>
	</div>     
</div>
<div align="center">
	<FORM name="frmPost" method="post" style="display: none" target="_blank">
		<!--系统保留定义区域-->
		<input type="hidden" name="queryXML"/> 
		<input type="hidden" name="txtXML"/>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<input type="hidden" name="txtFilter" order="desc" fieldname="WAICHU_SHIJIAN" id="txtFilter"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
<div align="center">
	<FORM name="frmPost2" method="post" style="display: none" target="_blank">
		<!--系统保留定义区域-->
	</FORM>
</div>
<div align="center">
	<FORM name="frmPost3" method="post" style="display: none" target="_blank">
		<!--系统保留定义区域-->
		<input type="hidden" name="txtFilter" order="desc" fieldname="TIJIAO_DATE" id="txtFilter"/>
	</FORM>
</div>
</body>
</html>