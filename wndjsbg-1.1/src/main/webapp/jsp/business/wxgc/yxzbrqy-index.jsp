<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>招标人(实施方区域)维护</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/wxgc/yxUserQyController";

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
	//按钮绑定事件(新增)
	$("#btnInsert").click(function() {
		$(window).manhuaDialog({"title":"实施方维护>新增","type":"text","content":"${pageContext.request.contextPath }/jsp/business/wxgc/yxzbrqy-add.jsp?type=insert","modal":"1"});
	});

	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#queryForm").clearFormResult();
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
function rowView(index){
	$("#DT1").setSelect(index);
	if($("#DT1").getSelectedRowIndex()==-1)
	 {
		requireSelectedOneRow();
	    return
	 }
	$("#resultXML").val($("#DT1").getSelectedRow());
	$(window).manhuaDialog({"title":"实施方维护>详细信息","type":"text","content":"${pageContext.request.contextPath }/jsp/business/wxgc/yxzbrqy-add.jsp?type=detail","modal":"1"});
}


function doeditor(){
	return "<a href='javascript:void(0)' onclick='editorU(this)'  title='修改'><i class='icon-file showXmxxkInfo'></i></a>";
}

function editorU(obj){
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
	$(window).manhuaDialog({"title":"实施方维护>修改","type":"text","content":"${pageContext.request.contextPath }/jsp/business/wxgc/yxzbrqy-add.jsp?type=update","modal":"1"});

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
						<TD class="right-border bottom-border"></TD>
						<TD class="right-border bottom-border">
							<INPUT type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>
						</TD>
					</TR>
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr>
						<th width="5%" class="right-border bottom-border text-right">实施方名称</th>
						<td class="right-border bottom-border" width="20%">
							<input class="span12" type="text" id="QQY_NAME" name="QY_NAME" fieldname="QY_NAME" operation="like" >
						</td>
						
			            <td class="text-left bottom-border text-right">
	                        <button id="btnQuery" class="btn btn-link"  type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-search"></i>查询</button>
           					<button id="btnClear" class="btn btn-link" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-trash"></i>清空</button>
			           		<button id="btnInsert" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">新增</button>
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
							<th fieldname="USER_QY_UID" colindex=0 tdalign="center" style="width:10px" CustomFunction="doeditor" noprint="true">&nbsp;&nbsp;</th>
							<th fieldname="QY_CODE" colindex=1 tdalign="center" style="width:10px">&nbsp;实施方编码&nbsp;</th>
<%--							<th fieldname="QY_PCODE" colindex=2 tdalign="center" maxlength="30" >&nbsp;区域父编码&nbsp;</th>--%>
							<th fieldname="QY_NAME" colindex=3 tdalign="center" style="width:100px" >&nbsp;实施方名称&nbsp;</th>
							<th fieldname="QY_TYPE_SV" colindex=4 tdalign="center" style="width:10px" >&nbsp;实施方类型&nbsp;</th>
<%--							<th fieldname="DZ" colindex=5 tdalign="center" style="width:10px">&nbsp;地址&nbsp;</th>--%>
<%--							<th fieldname="YZBH" colindex=6 tdalign="center" maxlength="30" >&nbsp;邮政编号&nbsp;</th>--%>
							<th fieldname="LXR" colindex=7 tdalign="center" style="width:10px" >&nbsp;联系人&nbsp;</th>
							<th fieldname="LXDH" colindex=8 tdalign="center" style="width:10px" >&nbsp;联系电话&nbsp;</th>
<%--							<th fieldname="CZH" colindex=9 tdalign="center" style="width:10px" >&nbsp;传真号&nbsp;</th>--%>
<%--							<th fieldname="EMAIL" colindex=10 tdalign="center" style="width:10px" >&nbsp;电子邮箱&nbsp;</th>--%>
<%--							<th fieldname="WEBWZ" colindex=11 tdalign="center" style="width:10px" >&nbsp;网址&nbsp;</th>--%>
							<th fieldname="KHYH" colindex=12 tdalign="center" style="width:10px" >&nbsp;开户银行&nbsp;</th>
							<th fieldname="KHYHZH" colindex=13 tdalign="center" style="width:20px" >&nbsp;开户银行帐号&nbsp;</th>
<%--							<th fieldname="ENABLED" colindex=16 tdalign="center" >&nbsp;是否有效&nbsp;</th>--%>
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
		<input type="hidden" name="txtFilter" order="desc" fieldname="QY_NAME" id="txtFilter"/>
<%--		<input type="hidden" name="txtFilter" order="desc" fieldname="ID" id="txtFilter"/>--%>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>