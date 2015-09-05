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
var controllername= "${pageContext.request.contextPath }/fsMessageInfoController";


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
   		$("#num").val("1000");
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
function rowEdit(obj){
	while(obj.tagName.toLowerCase() != "tr"){
	    obj = obj.parentNode;
	    if(obj.tagName.toLowerCase() == "table")return null;
	   }
	obj.click();
	var data = $("#DT1").getSelectedRow();
	
	$("#resultXML").val(data);
	$(window).manhuaDialog({"title":"消息内容>查看","type":"text","content":"${pageContext.request.contextPath }/jsp/weixin/fsmessage-view.jsp","modal":"2"});
}

function caozuoFun(obj){
	var showHtml="";
	showHtml +="<span class='btn btn-link' id='editSpan' onclick='rowEdit(this);' title='查看' >";
	showHtml +="<i class='icon-file'></i> ";
	showHtml +="</span>";
	return showHtml;
}
function checkstate(v){
	$("#STATEID").val(v);
	$("#btnQuery").click();
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
							<input class="hidden" id="rownum" type="text" fieldname="rownum"  value="10000" operation="  &lt;= "/>
							<input class="hidden" id="STATEID" type="text" fieldname="STATE"  value="1" operation="="/>
						</TD>
					</TR>
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr>				
						<th width="5%" class="right-border bottom-border text-right">标题</th>
						<td class="right-border bottom-border" width="20%">
							<input class="span12" type="text" id="TITLE" name="TITLE" fieldname="TITLE" operation="like" >
						</td>
						<th width="5%" class="right-border bottom-border text-right">状态</th>
						<td class="right-border bottom-border" width="20%">
							<label class="no-padding-left">
								<input  type="radio" id="STATEID1" name="STATENAME" checked="checked" value="1" onclick="checkstate(1)">未查看&nbsp;&nbsp;
								<input  type="radio" id="STATEID2" name="STATENAME"  value="2" onclick="checkstate(2)">已查看
							</label>
						</td>
						
			            <td class="text-left bottom-border text-right">
	                        <button id="btnQuery" class="btn btn-link"  type="button" m style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-search"></i>查询</button>
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
	                		<th  name="XH" id="_XH" style="width:5px" colindex=0 tdalign="center">&nbsp;#&nbsp;</th>
	                		<th fieldname="OPID" colindex=1 tdalign="center"  style="width:5px" CustomFunction="caozuoFun"  >&nbsp;&nbsp;</th>                		
	                		<th fieldname="TITLE" colindex=2 tdalign="left" style="width:320px">&nbsp;标题&nbsp;</th>
							<th fieldname="CONTENT" colindex=6 tdalign="left" >&nbsp;内容&nbsp;</th>
							<th fieldname="OPTIME" colindex=7 tdalign="center" style="width:10px">&nbsp;发布时间&nbsp;</th>
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
		<input type="hidden" name="txtFilter" order="desc" fieldname="OPTIME" id="txtFilter_PUBLISH_TIME"/>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>