<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>预选承包商</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/wxgc/yxYxcbsController";

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
        $("#queryForm").clearFormResult();
        $("#QTZTID").val("40");
        $("#ZT_ID").val("30");
        $("#zt0").removeAttr("checked");
        $("#zt1").removeAttr("checked");
        $("#zt2").removeAttr("checked");
        document.getElementById('zt1').checked=true;
        $("#btnQuery").click();
    });
	
});

//页面默认参数
function init(){
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?query",data,DT1);
}



//详细信息
function rowView(obj){
	while(obj.tagName.toLowerCase() != "tr"){
		obj = obj.parentNode;
		if(obj.tagName.toLowerCase() == "table")return null;
	}
	obj.click();
	if($("#DT1").getSelectedRowIndex()==-1){
		requireSelectedOneRow();
	    return
	 }
	$("#resultXML").val($("#DT1").getSelectedRow());
	$(window).manhuaDialog({"title":"预选承包商>查看","type":"text","content":"${pageContext.request.contextPath }/jsp/business/wxgc/yxyxcbs-add.jsp?type=detail","modal":"1"});
}

function checkStatus(status){
	 $("#ZT_ID").val(status);
	 $("#btnQuery").click();
}


function doeditor(obj){
	var rehtml=" ";
	var start=obj.ZT;
	if(start=='30'){
		rehtml+="<a href='javascript:void(0)' onclick='editorU(this)'  title='审核'><i class='icon-file showXmxxkInfo'></i></a>";
	}else{
		rehtml+="<a href='javascript:void(0)' onclick='rowView(this)'  title='查看'><i class='icon-file showXmxxkInfo'></i></a>";
	}
	return rehtml;
}

function editorU(obj){
	while(obj.tagName.toLowerCase() != "tr"){
		obj = obj.parentNode;
		if(obj.tagName.toLowerCase() == "table")return null;
	}
		//alert(obj.rowIndex);
	obj.click();
	if($("#DT1").getSelectedRowIndex()==-1)
	 {
		requireSelectedOneRow();
	    return
	 }
	$("#resultXML").val($("#DT1").getSelectedRow());
	$(window).manhuaDialog({"title":"预选承包商>审核","type":"text","content":"${pageContext.request.contextPath }/jsp/business/wxgc/yxyxcbs-add.jsp?type=update","modal":"1"});

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
							<INPUT type="text" class="span12" kind="text" id="QTZTID" fieldname="ZT" value="40" operation="!="/>
							<INPUT type="text" class="span12" kind="text" id="ZT_ID" fieldname="ZT" value="30" operation="="/>
						</TD>
					</TR>
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr>
						<th width="5%" class="right-border bottom-border text-right">审核状态</th>
						<td width="25%" class="right-border bottom-border">
						<nobr>	
							<input type=radio id="zt0" value="" name="status" onclick="javascript:checkStatus()">全部&nbsp;&nbsp;
							<input type=radio id="zt1"  value="30" name="status" onclick="javascript:checkStatus(30)" checked="checked"  >待审&nbsp;&nbsp;
<%--      						 <input type=radio value="10" name="status" onclick="javascript:checkStatus(10)">已审&nbsp;&nbsp;--%>
      						<input type=radio id="zt2"  value="20" name="status" onclick="javascript:checkStatus(20)">退回
						</nobr>
						</td>
						<th width="5%" class="right-border bottom-border text-right">承包商名称</th>
						<td class="right-border bottom-border" width="20%">
							<input class="span12" type="text" id="USERNAME" name="USERNAME" fieldname="USERNAME" operation="like" >
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
							<th fieldname="YXCBS_UID" colindex=0 tdalign="center" style="width:10px" CustomFunction="doeditor" noprint="true">&nbsp;&nbsp;</th>
							<th fieldname="USERNAME" colindex=2 tdalign="left" style="width:120px" >&nbsp;承包商名称&nbsp;</th>
							<th fieldname="CBS_TYPE_SV" colindex=1 tdalign="center" style="width:30px">&nbsp;承包商类型&nbsp;</th>
							<th fieldname="ZT_SV" colindex=3 tdalign="center" style="width:15px">&nbsp;状态&nbsp;</th>
							<th fieldname="GSKSRQ_SV" colindex=4 tdalign="center" style="width:10px">&nbsp;公示开始时间&nbsp;</th>
							<th fieldname="GSJZRQ_SV" colindex=5 tdalign="center" style="width:10px">&nbsp;公示截止时间&nbsp;</th>
<%--							<th fieldname="ENABLED_SV" colindex=7 tdalign="center" >&nbsp;是否有效&nbsp;</th>--%>
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
		<input type="hidden" name="txtFilter" order="desc" fieldname="UPDATE_DATE" id="txtFilter"/>
<%--		<input type="hidden" name="txtFilter" order="desc" fieldname="ID" id="txtFilter"/>--%>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>