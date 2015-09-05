<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>材料设备企业信息库首页</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/wxgc/yxClEnterpriseController";

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
	
	//按钮绑定事件(修改)
	$("#btnUpdate").click(function() {
		if($("#DT1").getSelectedRowIndex()==-1) {
			requireSelectedOneRow();
		    return
		 }
		$("#resultXML").val($("#DT1").getSelectedRow());
		$(window).manhuaDialog({"title":"材料设备企业信息库>修改","type":"text","content":"${pageContext.request.contextPath }/jsp/business/wxgc/clenterprise-add.jsp?type=update","modal":"1"});
	});
	
	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#queryForm").clearFormResult();
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
	$(window).manhuaDialog({"title":"材料设备企业信息库>查看","type":"text","content":"${pageContext.request.contextPath }/jsp/business/wxgc/clenterprise-add.jsp?type=detail","modal":"1"});
}

function checkStatus(status){
	 $("#ZT_ID").val(status);
	 $("#btnQuery").click();
}


function doeditor(obj){
//	alert(obj.STATUS);
	var rehtml=" ";
	var start=obj.STATUS;
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
	obj.click();
	if($("#DT1").getSelectedRowIndex()==-1){
		requireSelectedOneRow();
	    return
	 }
	$("#resultXML").val($("#DT1").getSelectedRow());
	$(window).manhuaDialog({"title":"材料设备企业信息库>审核","type":"text","content":"${pageContext.request.contextPath }/jsp/business/wxgc/clenterprise-sh.jsp?type=update","modal":"1"});

}


</script>
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="B-small-from-table-autoConcise">
			<form method="post" id="queryForm">
				<table class="B-table" width="100%">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<TR style="display: none;">
						<TD class="right-border bottom-border">
							<INPUT type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>
							<INPUT type="text" class="span12" kind="text" id="ZT_ID" fieldname="STATUS" value="1" operation="="/>
						</TD>
					</TR>
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr>
						<th width="5%" class="right-border bottom-border text-right">企业名称</th>
						<td class="right-border bottom-border" width="20%">
							<input class="span12" type="text" id="QCOMPANY_NAME" name="COMPANY_NAME" fieldname="COMPANY_NAME" operation="like" >
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
	                		<th fieldname="CL_ENTERPRISE_UID" colindex=2 tdalign="center" style="width:10px" CustomFunction="doeditor" noprint="true">&nbsp;&nbsp;</th>
							<th fieldname="COMPANY_NAME" colindex=4 tdalign="left" style="width:250px" >&nbsp;企业全称&nbsp;</th>
							<th fieldname="COMPANY_CODE" colindex=5 tdalign="center" style="width:8px" >&nbsp;组织机构代码&nbsp;</th>
							<th fieldname="DENGLU_CODE" colindex=1 tdalign="center" style="width:8px" >&nbsp;登录代码&nbsp;</th>
							<th fieldname="WAIDI_Y" colindex=7 tdalign="center" style="width:3px">&nbsp;是否外地&nbsp;</th>
							<th fieldname="ZHIZHAO" colindex=8 tdalign="center" style="width:10px" >&nbsp;营业执照注册号&nbsp;</th>
							<th fieldname="ZHIZHAO_VALID_SV" colindex=9 tdalign="center" style="width:10px">&nbsp;执照有效期&nbsp;</th>
							<th fieldname="SHUIWU" colindex=10 tdalign="center" style="width:10px" >&nbsp;税务登记号&nbsp;</th>
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
		<input type="hidden" name="txtFilter" order="desc" fieldname="CREATED_DATE" id="txtFilter"/>
<%--		<input type="hidden" name="txtFilter" order="desc" fieldname="ID" id="txtFilter"/>--%>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>