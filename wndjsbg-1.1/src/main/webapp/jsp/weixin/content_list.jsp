<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>审批业务材料库首页</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">

//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/wxContentController";


//页面初始化
$(function() { 
	init();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {

		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"/query",data,DT1);
		
	});

	//按钮绑定事件(审批)
	$("#addQuery").click(function() {
		
		$(window).manhuaDialog({"title":"内容消息>新增","type":"text","content":"${pageContext.request.contextPath }/jsp/weixin/content-input.jsp?isadd=true","modal":"1"});
	});


	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#queryForm").clearFormResult();
   		$("#num").val("1000");
    });
	
});

//页面默认参数
function init(){
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"/query",data,DT1);
	
}


//详细信息
function rowEdit(obj){
	while(obj.tagName.toLowerCase() != "tr"){
	    obj = obj.parentNode;
	    if(obj.tagName.toLowerCase() == "table")return null;
	   }
	obj.click();
	var data = $("#DT1").getSelectedRow();
	$("#resultXML").val(data);
	$(window).manhuaDialog({"title":"内容消息>修改","type":"text","content":"${pageContext.request.contextPath }/jsp/weixin/content-input.jsp?isadd=false","modal":"1"});
}

function caozuoFun(obj){
	var showHtml="";
	showHtml +="<span class='btn btn-link' id='editSpan' onclick='rowEdit(this);' title='修改' >";
	showHtml +="<i class='icon-file'></i> ";
	showHtml +="</span>";
	return showHtml;
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
							<INPUT type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>
							<INPUT type="text" class="span12" kind="text" id="STATUS_ID" fieldname="STATUS"  operation="="/>
						</TD>
					</TR>
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr>				
						<th width="5%" class="right-border bottom-border text-right">标题名称</th>
						<td class="right-border bottom-border" width="20%">
							<input class="span12" type="text" id="QCONTENT_TITLE" name="CONTENT_TITLE" fieldname="CONTENT_TITLE" operation="like" >
						</td>
						<th width="5%" class="right-border bottom-border text-right">是否有效</th>
						<td class="right-border bottom-border" width="20%">
							<select class="span12" id="ENABLED" name="ENABLED" fieldname="ENABLED" kind="dic" src="SF"  operation="=" logic="and" defaultMemo="全部" ></select>
						</td>
			            <td class="text-left bottom-border text-right">
	                        <button id="btnQuery" class="btn btn-link"  type="button" m style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-search"></i>查询</button>
           					<button id="btnClear" class="btn btn-link" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-trash"></i>清空</button>
           					<button id="addQuery" class="btn btn-link"  type="button" m style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">新增</button>
			            </td>							
					</tr>
					
				</table>
			</form>
			<div style="height:5px;"> </div>	
			<div class="overFlowX">
	            <table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single"  pageNum="18">
	                <thead>
	                	<tr>
	                		<th  name="XH" id="_XH" style="width:5px" colindex=0 tdalign="center">&nbsp;#&nbsp;</th>
	                		<th fieldname="CONTENT_UID" colindex=1 tdalign="center"  style="width:5px" CustomFunction="caozuoFun"  >&nbsp;&nbsp;</th>                		
	                		<th fieldname="CONTENT_TITLE" colindex=2 tdalign="left" >&nbsp;标题&nbsp;</th>
							<th fieldname="CHANNEL_NAME" colindex=3 tdalign="center" style="width:8px">&nbsp;所属栏目&nbsp;</th>
<%--							<th fieldname="SERIAL_NO" colindex=4 tdalign="center" style="width:2px">&nbsp;排序号&nbsp;</th>--%>
							<th fieldname="ENABLED_SV" colindex=6 tdalign="center" style="width:4px">&nbsp;是否有效&nbsp;</th>
							<th fieldname="PUBLISH_TIME_SV" colindex=7 tdalign="center" style="width:8px">&nbsp;发布时间&nbsp;</th>
							<th fieldname="UPDATE_DATE_SV" colindex=8 tdalign="center" style="width:8px">&nbsp;更新时间&nbsp;</th>
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
		<input type="hidden" name="txtFilter" order="desc" fieldname="SERIAL_NO" id="txtFilter_SERIAL_NO"/>
		<input type="hidden" name="txtFilter" order="desc" fieldname="PUBLISH_TIME" id="txtFilter_PUBLISH_TIME"/>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>