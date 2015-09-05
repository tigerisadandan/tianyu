<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>需审核加分列表</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/date/moment.min.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/yhzg/scoreController";
//页面初始化
$(function() {
	init();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {
		var shenhe = $('input[name="shenhe"]:checked').val();
				
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"?queryJiafen&shenhe="+shenhe,data,DT1);
	});
	
	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#queryForm").clearFormResult();
        $("#btnQuery").click();  
    });
	
});

//页面默认参数
function init(){
	var shenhe = $('input[name="shenhe"]:checked').val();
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?queryJiafen&shenhe="+shenhe,data,DT1);
}

function doQuery(){
	$("#btnQuery").click();
}

function agree(obj){
	if(obj.STATUS==40||obj.STATUS==50){
		var showHtml = "<a href=\"javascript:void(0)\" onclick=\"rowSh("+obj.JFSQ_UID+")\">同意</a>";
	}
	return showHtml;
}

function rowSh(JFSQ_UID){
	$(window).manhuaDialog({"title":"加分情况查询>同意","type":"text","content":"${pageContext.request.contextPath }/jsp/business/dtgl/index/index-subpage/score2-agree.jsp?JFSQ_UID="+JFSQ_UID,"modal":"2"});
}


function caozuoFun(obj){
	$("#JFSTATUS").val(obj.STATUS);
	var showHtml="";
	showHtml +="<span class='btn btn-link' id='addSpan' onclick='rowSh("+obj.JFSQ_UID+");' title='申请加分审核' >";
	showHtml +="<i class='icon-file'></i> ";
	showHtml +="</span>";
	return showHtml;
}

//回车事件
function enterSumbit(){  
    var event=arguments.callee.caller.arguments[0]||window.event;//消除浏览器差异  
   if (event.keyCode == 13){  
	   $("#btnQuery").click();  
   };  
}  

</script>
</head>
<body>
<app:dialogs/>
<div id="menuiframe"></div>
<div class="container-fluid">
	<h4 id="top-h4">加分情况查询</h4>
	<div class="row-fluid">
		<div class="B-small-from-table-autoConcise">
			<form method="post" id="queryForm">
				<table class="B-table" width="100%">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<TR style="display: none;">
						<TD class="right-border bottom-border"></TD>
						<TD class="right-border bottom-border">
							<INPUT type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>							
							<input type="hidden" value="" id="JFSTATUS">
						</TD>
					</TR>
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr>
					<th width="3%" class="right-border bottom-border text-right">审核状态</th>
					<td>						
						<input type="radio" value="1" name="shenhe" checked="checked" onclick="doQuery();"/>待审核
						<input type="radio" value="2" name="shenhe" onclick="doQuery();"/>已审核
					</td>
					<th width="3%" class="right-border bottom-border text-right">申请单位</th>
					<td>
						<input type="text" fieldname="SQ_DANWEI" operation="like"/>
					</td>																									            						
					<th width="3%" class="right-border bottom-border text-right">工程名称</th>
					<td>
						<input type="text"  fieldname="GONGCHENG_NAME" operation="like"/>
					</td>
					<th width="3%" class="right-border bottom-border text-right">日期</th>
					<td class="right-border bottom-border" width="25%" colspan="3">
						<input id="B_DATE" fieldname="SQ_DATE" fieldformat="yyyy-MM-dd" fieldtype="date" operation=">=" class="Wdate" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:35%"  name="B_DATE" >			  
						~<input id="E_DATE" fieldname="SQ_DATE" fieldformat="yyyy-MM-dd" fieldtype="date" operation="<=" class="Wdate" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:35%" name="E_DATE"  >
					</td>
											
					<td class="text-left" width="15%">
	                    <button id="btnQuery" class="btn btn-link"  type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-search"></i>查询</button>
           				<button id="btnClear" class="btn btn-link" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-trash"></i>清空</button>           				
			        </td>	
					</tr>
				</table>
			</form>
			<div style="height:5px;"></div>	
			<div class="overFlowX">
	            <table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single"  pageNum="18">
	                <thead>
	                	<tr>
	                		<th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
	                		<th fieldname="JFSQ_UID" colindex=0 tdalign="center"  CustomFunction="caozuoFun" >&nbsp;&nbsp;</th>
							<th fieldname="ZT" colindex=2 tdalign="center" maxlength="30" >&nbsp;审核状态&nbsp;</th>
							<th fieldname="SQ_DANWEI" colindex=2 tdalign="center" maxlength="30">&nbsp;申请单位&nbsp;</th>
							<th fieldname="GONGCHENG_NAME" colindex=2 tdalign="center" maxlength="30">&nbsp;工程名称&nbsp;</th>
							<th fieldname="SQ_CONTENT" colindex=3 tdalign="left"> &nbsp;申请原因&nbsp;</th>
							<th fieldname="JF_DUIXIANG" colindex=2 tdalign="left">&nbsp;加分对象&nbsp;</th>
							<th fieldname="SQ_DATE" colindex=2 tdalign="center" maxlength="30">&nbsp;日期&nbsp;</th>
							<th fieldname="STATUS" colindex=2 tdalign="center" maxlength="30" CustomFunction="agree">&nbsp;审核&nbsp;</th>
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
		<input type="hidden" name="txtFilter" order="desc" fieldname="SQ_DATE" id="txtFilter"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>
