<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>微型工程审核</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/wxgc/yxWxgcController";

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
        $("#QTSHZT").val("40");
        $("#ZT_ID").val("4");
        $("#zt0").removeAttr("checked");
        $("#zt1").removeAttr("checked");
        $("#zt2").removeAttr("checked");
        document.getElementById('zt2').checked=true;
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
/**	$("#DT1").setSelect(index);
	if($("#DT1").getSelectedRowIndex()==-1)
	 {
		requireSelectedOneRow();
	    return
	 }
**/
	while(obj.tagName.toLowerCase() != "tr"){
		obj = obj.parentNode;
		if(obj.tagName.toLowerCase() == "table")return null;
	}
	obj.click();
	if($("#DT1").getSelectedRowIndex()==-1)
	 {
		requireSelectedOneRow();
	    return
	 }
	$("#resultXML").val($("#DT1").getSelectedRow());
	$(window).manhuaDialog({"title":"微型工程>查看","type":"text","content":"${pageContext.request.contextPath }/jsp/business/wxgc/yxwxgc-add.jsp?type=detail","modal":"1"});
}

function checkStatus(zt){
	$("#ZT_ID").val(zt);
	$("#btnQuery").click();
}

function doeditor(obj){
	var rehtml=" ";
	var start=obj.ZT;
	if(start=='4'){
		rehtml+="<a href='javascript:void(0)' onclick='editorU(this)'  title='备案'><i class='icon-file showXmxxkInfo'></i></a>";
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
	$(window).manhuaDialog({"title":"微型工程>备案","type":"text","content":"${pageContext.request.contextPath }/jsp/business/wxgc/yxwxgc-add.jsp?type=update","modal":"1"});
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
					<input type="hidden"  kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>
					<input  type="hidden" id="QTSHZT" name="ZT" value='40' fieldname="ZT" operation="!="  >
					<input type="hidden" kind="text" id="ZT_ID" fieldname="ZT" value="4" operation="="/>
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr>
						<th  width="5%" class="right-border bottom-border text-right">备案状态</th>
						<td class="right-border bottom-border" width="18%">
      						 <input type=radio id="zt0" value="" name="status" onclick="checkStatus(this.value)">全部<h id="ysid"></h>&nbsp;&nbsp;
      						 <input type=radio  id="zt1" value="4" name="status" onclick="checkStatus(this.value)" checked="checked" >待备案&nbsp;&nbsp;
      						  <input type=radio  id="zt2" value="20" name="status" onclick="checkStatus(this.value)" >退回&nbsp;&nbsp;
						</td>
						
						<th width="5%" class="right-border bottom-border text-right">工程名称</th>
						<td class="right-border bottom-border" width="10%">
							<input class="span12" type="text" id="QGC_NAME" name="GC_NAME" fieldname="GC_NAME" operation="like" >
						</td>
						<th width="5%" class="right-border bottom-border text-right">工程类型</th>
						<td class="right-border bottom-border" width="10%">
							<select style="width: 100%;" id="GC_TYPE_CODE" fieldname="GC_TYPE_CODE" defaultMemo="--全部--" operation="=" name="GC_TYPE_CODE" kind="dic" src="T#YX_GC_TYPE:GC_TYPE_CODE:GC_TYPE_NAME:ENABLED='1' order by SERIAL_NO asc " ></select>
						</td>
						<th width="5%" class="right-border bottom-border text-right">评标方式</th>
						<td class="right-border bottom-border" width="10%">
							<select style="width: 100%;"  id="PBFS_UID" fieldname="PBFS_UID" defaultMemo="--全部--" operation="=" name="PBFS_UID" kind="dic" src="T#YX_WXGC_PBFS:PBFS_UID:PBFS_NAME:ENABLED='1' order by SERIAL_NO asc " ></select>
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
	                		<th fieldname="WXGC_UID" colindex=2 tdalign="center" style="width:5px"  CustomFunction="doeditor" noprint="true">&nbsp;&nbsp;</th>
							<th fieldname="GC_NAME" colindex=4 tdalign="left" style="width:250px"   >&nbsp;工程名称&nbsp;</th>
							<th fieldname="GC_TYPE_NAME" colindex=2  style="width:5px"   tdalign="center" >&nbsp;工程类型&nbsp;</th>
							<th fieldname="PBFS_NAME" colindex=6  style="width:5px"   tdalign="center" >&nbsp;评标方式&nbsp;</th>
							<th fieldname="JSDW" colindex=7 tdalign="center" style="width:50px" >&nbsp;建设单位&nbsp;</th>
							<th fieldname="ZT_SV" colindex=8 tdalign="center" style="width:5px" >&nbsp;状态&nbsp;</th>
							<th fieldname="QY_NAME" colindex=9 tdalign="center" style="width:18px" >&nbsp;招标人&nbsp;</th>
<%--							<th fieldname="JHGQ" colindex=12 tdalign="center" style="width:3px">&nbsp;计划工期(天)&nbsp;</th>--%>
							<th fieldname="ZBJ_SV" colindex=13 tdalign="right" style="width:3px">&nbsp;明标价(元)&nbsp;</th>
							<th fieldname="GGJZRQ_SV" colindex=14 tdalign="center" style="width:5px"  >&nbsp;报名截止时间&nbsp;</th>
							<th fieldname="KBRQ_SV" colindex=15 tdalign="center" style="width:5px" >&nbsp;开标时间&nbsp;</th>
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
		<input type="hidden" name="txtFilter" order="desc" fieldname="QY_CODE" id="txtFilter"/>
		<input type="hidden" name="txtFilter" order="desc" fieldname="GC_TYPE_CODE" id="txtFilter"/>
		<input type="hidden" name="txtFilter" order="desc" fieldname="GC_CODE" id="txtFilter"/>
<%--		<input type="hidden" name="txtFilter" order="desc" fieldname="ID" id="txtFilter"/>--%>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>