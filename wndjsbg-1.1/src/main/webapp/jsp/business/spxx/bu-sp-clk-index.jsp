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
var controllername= "${pageContext.request.contextPath }/spxx/buSpClkController";

//页面初始化
$(function() {
	init();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {
		//alert("-----");
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"?query",data,DT1);

	});
	//按钮绑定事件(新增)
	$("#btnInsert").click(function() {
		//alert("----");
		$(window).manhuaDialog({"title":"审批业务材料库>新增","type":"text","content":"${pageContext.request.contextPath }/jsp/business/spxx/bu-sp-clk-add.jsp?type=insert","modal":"2"});
	});
	//按钮绑定事件(修改)
	$("#btnUpdate").click(function() {
		if($("#DT1").getSelectedRowIndex()==-1){
			requireSelectedOneRow();
		    return
		 }
		$("#resultXML").val($("#DT1").getSelectedRow());
		$(window).manhuaDialog({"title":"审批业务材料库>修改","type":"text","content":"${pageContext.request.contextPath }/jsp/business/spxx/bu-sp-clk-add.jsp?type=update","modal":"2"});
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
	defaultJson.doQueryJsonList(controllername+"?query",data,DT1);

}

//回调函数--用于修改新增
getWinData = function(data){
	//var subresultmsgobj = defaultJson.dealResultJson(data);
	var data1 = defaultJson.packSaveJson(data);
	if(JSON.parse(data).ID == "" || JSON.parse(data).ID == null){
		defaultJson.doInsertJson(controllername + "?insert", data1,DT1);
	}else{
		defaultJson.doUpdateJson(controllername + "?update", data1,DT1);
	}
	
};



function rowUpdate(obj){
	while(obj.tagName.toLowerCase() != "tr"){
	    obj = obj.parentNode;
	    if(obj.tagName.toLowerCase() == "table")return null;
	   }
	obj.click();
	var data = $("#DT1").getSelectedRow();

	$("#resultXML").val(data);
	$(window).manhuaDialog({"title":"审批业务材料库>修改","type":"text","content":"${pageContext.request.contextPath }/jsp/business/spxx/bu-sp-clk-add.jsp?type=update","modal":"2"});

}


function caozuoFun(obj){
	var showHtml ="<span class='btn btn-link' id='addSpan' onclick='rowUpdate(this);' title='修改' >";
	showHtml +="<i class='icon-file'></i> ";
	showHtml +="</span>";
	return showHtml;
}

//回车事件
function EnterPress(){  
    var event=arguments.callee.caller.arguments[0]||window.event;//消除浏览器差异  
   if (event.keyCode == 13){  
	   $("#btnQuery").click();  
   }  
} 

//点击获取行对象
function tr_click(obj,tabListid){
	$("#btnDelete").attr("disabled", false);
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
				审批业务材料库
				<span class="pull-right">  
					<button id="btnInsert" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">新增</button>
<%--      				<button id="btnUpdate" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">修改</button>--%>
<%--      				<button id="btnDelete" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">删除</button>--%>
      					<script type="text/javascript">
		      				$(function() {		      					
		      					$("#btnDelete").click(function() {
		      				    	var rowinx = $("#DT1").getSelectedRowIndex();
		      				    	if(rowinx==-1)
		      						 {
		      							requireSelectedOneRow();
		      						    return
		      						 }
		      						xConfirm("信息确认","您选择的 第  [<font color=\"red\">"+parseInt(rowinx+1)+"</font>]行材料将被删除，删除后不可恢复！<br/><br/>确认要继续吗？");
		      						
		      						$('#ConfirmYesButton').attr('click','');
		      						$('#ConfirmYesButton').one("click",function(){
		      							$("#btnDelete").attr("disabled", true);
		      							 var rowJson = $("#DT1").getSelectedRow();
		      							 var data1 = defaultJson.packSaveJson(rowJson);
		      							 var flag = defaultJson.doDeleteJson(controllername+"?delete", data1, null);
		      							 if(flag){
		      								 $("#DT1").removeResult(rowinx);
		      							 }
		      						});
		      					});
		
		      				});
	      				</script>
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
						<th width="5%" class="right-border bottom-border text-right">材料名称</th>
						<td class="right-border bottom-border" width="20%">
							<input class="span12" type="text" id="QCLMC" name="CLMC"  onkeydown="EnterPress()"  fieldname="CLMC" operation="like" >
						</td>
						<th width="5%" class="right-border bottom-border text-right">材料级别</th>
						<td class="right-border bottom-border" width="30%">
							<select id="CL_LEVEL" name="CL_LEVEL" fieldname="CL_LEVEL"  onkeydown="EnterPress()"  check-type="required"  operation="in" kind="dic" src="SPYWCLJB"  defaultMemo="-请选择-" />
<%--								<input class="span12" type="checkbox" name="CL_LEVEL_view" kind="dic" onchange="queryChange()" operation="=" src="SPYWCLJB">--%>
<%--								<input type="hidden" fieldname="CL_LEVEL" name="CL_LEVEL" id="CL_LEVEL" operation="in" value="('LX','QY')" />--%>
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
	                		<th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
	                		<th fieldname="CLK_UID" colindex=2 tdalign="center" CustomFunction="caozuoFun" >&nbsp;&nbsp;</th>
							<th fieldname="CLMC" colindex=1 tdalign="center" maxlength="30" >&nbsp;材料名称&nbsp;</th>
							<!--材料级别 ：QY－企业材料；LX－立项材料；XM－项目材料 -->
							<th fieldname="CL_LEVEL" colindex=2 tdalign="center" >&nbsp;材料级别&nbsp;</th>
							<th fieldname="SFYFJ" colindex=3 tdalign="center" >&nbsp;是否有附件&nbsp;</th>
							<th fieldname="ENABLED" colindex=6 tdalign="center" >&nbsp;是否有效&nbsp;</th>
							<th fieldname="SERIAL_NO" colindex=14 tdalign="center" >&nbsp;排序号&nbsp;</th>
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
		<input type="hidden" name="txtFilter" order="asc" fieldname="SERIAL_NO" id="txtFilter"/>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>