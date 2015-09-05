<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>审批业务信息首页</title>

<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/spxx/buSpYwxxController/";

//页面初始化
$(function() {
	init();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"query",data,DT1);
	});
	//按钮绑定事件(新增)
	$("#btnInsert").click(function() {
		window.open("${pageContext.request.contextPath}/ywxxAdd/0");
	});
	
	
	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#queryForm").clearFormResult();
        init();
    });
	
});

//页面默认参数
function init(){
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"query",data,DT1);
}

//点击获取行对象
function tr_click(obj,tabListid){
	//alert(JSON.stringify(obj));
}

function doAddChild(uid,mc){
	$("#currYwid").val(uid);
	$("#currYwmc").val(mc);
	window.open("${pageContext.request.contextPath}/ywxxAdd/1");
}
function doQueryYw(){
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"query",data,DT1);
}
//详细信息
function rowView(uid,zlc){
		window.open("${pageContext.request.contextPath }/ywxxView/"+zlc+"/"+uid,"审批业务详细信息");
}
function doView(obj){
	var showHtml = "<a href='javascript:rowView("+obj.SPYW_UID+","+obj.SFZLC+");' title='查看详细信息'><i class='icon-file'></i></a>";
	if(obj.SFZLC=="0"){
		showHtml += "<a href='javascript:void(0)' title='添加子业务' onclick=\"doAddChild("+obj.SPYW_UID+",'"+obj.SPYWMC+"')\"><i class='icon-plus'></i></a>";
	}
	return showHtml;
}
//回车事件
function EnterPress(){  
    var event=arguments.callee.caller.arguments[0]||window.event;//消除浏览器差异  
   if (event.keyCode == 13){  
	   $("#btnQuery").click();  
   }  
} 
</script>
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
	<p></p>
	<div class="row-fluid">
		<div class="B-small-from-table-autoConcise">
			<h4 class="title">
				审批业务信息
				<span class="pull-right">  
					<button id="btnInsert" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">新增</button>
				</span>
			</h4>
			<form method="post" id="queryForm">
				<table class="B-table" width="100%">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<TR style="display: none;">
						<TD class="right-border bottom-border"></TD>
						<TD class="right-border bottom-border">
							<INPUT type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>
						</TD>
					</TR>
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr>
<%--						<td width="7%" class="right-border bottom-border">--%>
<%--							<select class="span12" id="QND" name="ND" fieldname="ND" operation="=" kind="dic" src="XMNF"  defaultMemo="-年度-">--%>
<%--						</td>--%>
<%--						<th width="5%" class="right-border bottom-border text-right">项目名称</th>--%>
<%--						<td class="right-border bottom-border" width="20%">--%>
<%--							<input class="span12" type="text" id="QXMMC" name="XMMC" fieldname="XMMC" operation="like" >--%>
<%--						</td>--%>
						<th width="15%" class="text-left bottom-border text-right">
							审批业务名称
						</th>
						<td class="text-left bottom-border text-right">
							<INPUT type="text" class="span12" onkeydown="EnterPress()" kind="text" id="SPYWMC" fieldname="t.SPYWMC" operation="like"/>
						</td>
						<th width="8%" class="right-border bottom-border text-right">审批业务类型</th>
			       		<td class="bottom-border right-border"width="15%">
			         		<select class="span12" style="width:85%" id="SPYWLX" fieldname="t.SPYWLX" operation="=" kind="dic" src="SPYWLX" name = "SPYWLX" >
			         	</td>
			         	
			            <td class="text-left bottom-border text-right">
	                        <button id="btnQuery" class="btn btn-link"  type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-search"></i>查询</button>
           					<button id="btnClear" class="btn btn-link" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-trash"></i>清空</button>
			            </td>							
					</tr>
				</table>
			</form>
			<div style="height:5px;"> </div>	
			<div class="overFlowX">
	            <table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single"  pageNum="18">
	                <thead>
	                	<tr>
	                		<th  name="XH" id="_XH" style="width:10px" colindex=0 tdalign="center">&nbsp;#&nbsp;</th>
	                		<th fieldname="SPYWMC" colindex=1 tdalign="center" CustomFunction="doView">&nbsp;&nbsp;</th>
	                		<th fieldname="SPYW_UID" style="width:33px" colindex=2 tdalign="center" >&nbsp;ID&nbsp;</th>
							<th fieldname="SPYWMC" colindex=3 tdalign="center" >&nbsp;审批业务名称&nbsp;</th>
							<th fieldname="P_YWMC" colindex=4 tdalign="center" >&nbsp;父审批业务&nbsp;</th>
							<th fieldname="SPYWLX" style="width:40px" colindex=5 tdalign="center">&nbsp;审批业务类型&nbsp;</th>
							<th fieldname="MULTI_Y" style="width:35px" colindex=6 tdalign="center" >&nbsp;可否重复申请&nbsp;</th>
							<th fieldname="SFZLC" style="width:35px" colindex=6 tdalign="center" >&nbsp;是否子流程&nbsp;</th>
							<th fieldname="SERIAL_NO" style="width:35px" colindex=7 tdalign="center" >&nbsp;排序号&nbsp;</th>
	                	</tr>
	                </thead>
	              	<tbody></tbody>
	           </table>
	       </div>
	 	</div>
	</div>     
</div>
<div align="center">
	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		<!--系统保留定义区域-->
		<input type="hidden" name="queryXML"/> 
		<input type="hidden" name="txtXML"/>
		<input type="hidden" name="txtFilter" order="desc" fieldname="t.spywlx"/>
		<input type="hidden" name="txtFilter" order="asc" fieldname="t.serial_no" id="txtFilter"/>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
		<input type="hidden" name="currYwid" id="currYwid"/>
		<input type="hidden" name="currYwmc" id="currYwmc"/>
		
	</FORM>
</div>
</body>
</html>