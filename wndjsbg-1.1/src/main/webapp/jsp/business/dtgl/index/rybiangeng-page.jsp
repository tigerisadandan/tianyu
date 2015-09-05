<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>人员变更审核</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/rygl/dtRyBiangengController";
//页面初始化
$(function() {
	init();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {
		var zt = $('input[name="zt"]:checked').val();
		$('#status').val(zt);
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"?query",data,DT1);
	});
	
	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#queryForm").clearFormResult();
        $("#btnQuery").click();  
    });
	
});

//页面默认参数
function init(){
	var zt = $('input[name="zt"]:checked').val();
	$('#status').val(zt);
	//$('#gongcheng_uid').val(parent.document.getElementById("GDZXT_XM_ID").value);
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?query",data,DT1);

}

function doQuery(){
	$("#btnQuery").click();
}

function rowSh(obj){

	while(obj.tagName.toLowerCase() != "tr"){
	    obj = obj.parentNode;
	    if(obj.tagName.toLowerCase() == "table")return null;
	   }
	obj.click();   
	var data = $("#DT1").getSelectedRow();

	//$("#resultXML").val(data);
	var tempJson = convertJson.string2json1(data);
	$(window).manhuaDialog({"title":"现场人员管理>人员变更审核","type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/index/rybiangeng-sh.jsp?tempJson="+tempJson,"modal":"2"});
}


function caozuoFun(obj){
	var showHtml="";
	showHtml +="<span class='btn btn-link' id='addSpan' onclick='rowSh(this);' title='审核' >";
	showHtml +="<i class='icon-file'></i> ";
	showHtml +="</span>";
	return showHtml;
}

function ztFun(obj){
	var str = "";
	var status = obj.STATUS;
	var spStatus = obj.SHENPI_STATUS;
	//审批状态：1－一级待审（安质监待审）；2－二级待审（招标办待审）；3－三级待审（主管部门待审）
	if(spStatus=='1'){
		str+="安质监";
	}else if(spStatus=='2'){
		str+="招标办";
	}else if(spStatus=='3'){
		str+="主管部门";
	}

	if(status=='0'){
		str+="待审核";
	}else if(status=='1'){
		str = "审核完成";
	}else if(status=='-1'){
		str+="退回";
	}
	return str;
}


//回车事件
function enterSumbit(){  
    var event=arguments.callee.caller.arguments[0]||window.event;//消除浏览器差异  
   if (event.keyCode == 13){  
	   $("#btnQuery").click();  
   }  
}  

</script>
</head>
<body>
<app:dialogs/>
<div id="menuiframe"></div>
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
							<INPUT type="text" class="span12"  id="status" fieldname="STATUS"operation="="/>
						</TD>
					</TR>
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr>
						
						<td class="right-border bottom-border" width="15%">
							<input type="radio" name="zt" checked="checked" value="0" onclick="doQuery();">待审核
							<input type="radio" name="zt" value="1" onclick="doQuery();">已审核
							<input type="radio" name="zt" value="-1" onclick="doQuery();">已退回
						</td>
						<th width="3%" class="right-border bottom-border text-right">项目名称</th>
						<td class="right-border bottom-border" width="16%">
							<input class="span12" type="text" onkeydown="enterSumbit()" id="XMNAME" name="NAME" fieldname="NAME" operation="like" >
						</td>	
						<th width="3%" class="right-border bottom-border text-right">建设单位</th>
						<td class="right-border bottom-border" width="16%">
							<input class="span12" type="text" onkeydown="enterSumbit()" id="JSDW" name="JSDW" fieldname="JSDW" operation="like" >
						</td>	
						<th width="3%" class="right-border bottom-border text-right">变更单位</th>
						<td class="right-border bottom-border" width="16%">
							<input class="span12" type="text" onkeydown="enterSumbit()" id="DWMC" name="DWMC" fieldname="DWMC" operation="like" >
						</td>	
						<th width="3%" class="right-border bottom-border text-right">变更后</th>
						<td class="right-border bottom-border" width="10%">
							<input class="span12" type="text" onkeydown="enterSumbit()" id="BGH" name="BGH" fieldname="BGH" operation="like" >
						</td>	
					
						<td class="text-left" width="11%">
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
	                		<th fieldname="BGUID" colindex=0 tdalign="center"  CustomFunction="caozuoFun" >&nbsp;&nbsp;</th>
							<th fieldname="STATUS" colindex=2 tdalign="center" maxlength="30" CustomFunction="ztFun" >&nbsp;审核状态&nbsp;</th>
							<th fieldname="XMNAME" colindex=2 tdalign="center" maxlength="30">&nbsp;项目名称&nbsp;</th>
							<th fieldname="JSDW" colindex=2 tdalign="center" maxlength="30">&nbsp;建设单位&nbsp;</th>
							<th fieldname="DWMC" colindex=3 tdalign="center" maxlength="30"> &nbsp;变更单位</th>
							<th fieldname="GW" colindex=2 tdalign="center" maxlength="30">&nbsp;变更岗位&nbsp;</th>
							<th fieldname="BGH" colindex=2 tdalign="center" maxlength="30" >&nbsp;变更后&nbsp;</th>
							<th fieldname="CREATED_DATE" colindex=2 tdalign="center" maxlength="30" >&nbsp;提交时间&nbsp;</th>
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
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>
