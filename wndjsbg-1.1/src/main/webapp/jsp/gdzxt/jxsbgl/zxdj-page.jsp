<!DOCTYPE html>
<%@page import="com.ccthanking.business.bzwj.GongCheng"%>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<% String gcid = GongCheng.getGcid();
   String gcname=GongCheng.getGcName();
   String sgname=GongCheng.getSgCompanyName();
%>
<app:base/>
<title>安装过程</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/jxsb/jxsbCxgcController";

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
	//按钮绑定事件(新增)
	$("#btnInsert").click(function() {
		$(window).manhuaDialog({"title":"微型工程类型维护>新增","type":"text","content":"${pageContext.request.contextPath }/jsp/business/wxgc/wxgclx-add.jsp?type=insert","modal":"1"});
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
	defaultJson.doQueryJsonList(controllername+"/query",data,DT1);
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
	$(window).manhuaDialog({"title":"微型工程类型>修改","type":"text","content":"${pageContext.request.contextPath }/jsp/business/wxgc/wxgclx-add.jsp?type=update","modal":"1"});
}

function zxzt(obj){
	var html="";
	if(obj.END_DATE==""){
		html="未注销";
	}else{
		html="已注销";
	}
	return html;
}

function djzdy(obj){
	var html= "<a href='javascript:void(0)' onclick='djzDoPrint("+obj.JXSB_SYGL_UID+")' title='打印'>"+obj.SYDJ_BH+"</a>";
	return html;
}

function djzDoPrint(syglid){
	  $.ajax({
			url : "${pageContext.request.contextPath }/jxsb/jxsbSydjController/downloadDjz?uid="+syglid,
			cache : false,
			async : false,
			dataType : "json",
			type : 'post',
			success : function(response) {
				window.location.href = "${pageContext.request.contextPath }/controller/fileController/download2?path="+response;
			 } 
		    });
}


function caozuoFun(obj){
	var showHtml="";
	 if(obj.END_DATE==""){
		showHtml +="<span class='btn btn-link' id='addSpan' onclick=\"rowSh('"+obj.JXSB_UID+"','"+obj.JXSB_SYGL_UID+"');\" title='注销' >";
		showHtml +="<i class='icon-off'></i> ";
		showHtml +="</span>";
		//showHtml += "<a href='javascript:rowSh("+obj+");' title='审核'><i class='icon-file'></i></a>";	
	}else{
		showHtml +=" ";
		//showHtml += "<a href='javascript:rowView("+obj+");' title='查看'><i class='icon-file'></i></a>";
	}
	return showHtml;
}

//详细信息
function rowView(sbid,id){
	if(confirm("注销 ?")){
	     defaultJson.doUpdateJson(controllername + "/updateZhuXiao?sbid="+sbid+"&id="+id, null);
	     init();
	}
}

function rowSh(sbid,id){
	if(confirm("注销 ?")){
		defaultJson.doUpdateJson(controllername + "/updateZhuXiao?sbid="+sbid+"&id="+id, null);
		init();
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
			
			<form method="post" id="queryForm">
				<table class="B-table" width="100%">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<TR style="display: none;">
						<TD class="right-border bottom-border"></TD>
						<TD class="right-border bottom-border">
							<INPUT type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>
							<INPUT type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>
						</TD>
					</TR>
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr>
						<th width="4%" class="right-border bottom-border text-right">设备名称</th>
							<td class="right-border bottom-border" width="12%"><select
								class="form-control" style="margin-bottom:8px;width: 110px"
								id="JXSB_TYPE_UID" type="text" name="JXSB_TYPE_UID"
								fieldname="g.SHEBEI_NAME" class="col-xs-10 col-sm-10"
								operation="=">
									<option value="">--全部设备--</option>
									<option value="塔式起重机">塔式起重机</option>
									<option value="物料提升机">物料提升机</option>
									<option value="施工升降机">施工升降机</option>
									<option value="吊篮">吊篮</option>
									<option value="爬升式脚手架">爬升式脚手架</option>
							</select>
							</td>
							<th width="4%" class="right-border bottom-border text-right">产权编号</th>
							<td class="right-border bottom-border" width="15%">
							 <input class="span12" type="text" id="CQ_BH" name="s.CQ_BH" fieldname="g.CQ_BH" operation="like" >
							</td>
							<td class="text-left bottom-border text-right">
								<button id="btnQuery" class="btn btn-link" type="button"
									style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">
									<i class="icon-search"></i>查询
								</button>
								<button id="btnClear" class="btn btn-link" type="button"
									style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">
									<i class="icon-trash"></i>清空
								</button>
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
	                	    <th width="15" CustomFunction="caozuoFun" fieldname="JXSB_SYGL_UID" colindex=0 tdalign="center" >&nbsp;&nbsp;</th>
							<th CustomFunction="zxzt" fieldname="END_DATE"  colindex=2 tdalign="center" maxlength="30" >&nbsp;注销状态&nbsp;</th>
							<th fieldname=JXSB_UID colindex=3 tdalign="center" maxlength="30" hidden >&nbsp;&nbsp;</th>
							<th fieldname=JXSB_SYGL_UID colindex=3 tdalign="center" maxlength="30" hidden >&nbsp;&nbsp;</th>
							<th fieldname=SHEBEI_NAME colindex=3 tdalign="center" maxlength="30" >&nbsp;设备名称&nbsp;</th>
							<th CustomFunction="djzdy" fieldname="SYDJ_BH" colindex=20 tdalign="center" >&nbsp;使用登记编号&nbsp;</th>
							<th fieldname="GGXH" colindex=20 tdalign="center" >&nbsp;规格型号&nbsp;</th>
							<th fieldname="SBCX_E_DATE" colindex=20 tdalign="center" >&nbsp;设备拆卸结束日期&nbsp;</th>
							<th fieldname="BEGIN_DATE" colindex=20 tdalign="center" >&nbsp;开始时间&nbsp;</th>
							<th fieldname="END_DATE" colindex=20 tdalign="center" >&nbsp;注销时间&nbsp;</th>
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
		<input type="hidden" name="txtFilter" order="desc" fieldname="c.SHENHE_DATE" id="txtFilter"/>
<%--		<input type="hidden" name="txtFilter" order="desc" fieldname="ID" id="txtFilter"/>--%>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>