<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>安装人员首页</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/rygl/minGongController";

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
		if($("#DT1").getSelectedRowIndex()==-1)
		 {
			requireSelectedOneRow();
		    return
		 }
		$("#resultXML").val($("#DT1").getSelectedRow());
		$(window).manhuaDialog({"title":"建设单位>审核","type":"text","content":"${pageContext.request.contextPath }/jsp/business/project/js-company-add.jsp?type=update","modal":"2"});
	});
	//按钮绑定事件（导出EXCEL）
/**	$("#btnExpExcel").click(function() {
		 var t = $("#DT1").getTableRows();
		 if(t<=0)
		 {
			 xAlert("提示信息","请至少查询出一条记录！");
			 return;
		 }
	  	 $(window).manhuaDialog({"title":"导出","type":"text","content":"${pageContext.request.contextPath }/jsp/framework/print/TabListEXP.jsp?tabId=DT1","modal":"3"});
	});**/
	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $('#xxk').val("");
        $('#ht').val("");
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
//	getCount();

}

//点击获取行对象
function tr_click(obj,tabListid){
	if(obj.STATUS=="30"){
		$("#btnUpdate").show();
	}else{
		$("#btnUpdate").hide();
	}
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

//详细信息
function rowView(obj){
	while(obj.tagName.toLowerCase() != "tr"){
	    obj = obj.parentNode;
	    if(obj.tagName.toLowerCase() == "table")return null;
	   }
	obj.click();
	var data = $("#DT1").getSelectedRow();

	$("#resultXML").val(data);
	$(window).manhuaDialog({"title":"安装企业>人员信息","type":"text","content":"${pageContext.request.contextPath }/jsp/business/dtgl/azqy/az-rygl-add.jsp?type=detail","modal":"2"});
}


function rowSh(obj){
	while(obj.tagName.toLowerCase() != "tr"){
	    obj = obj.parentNode;
	    if(obj.tagName.toLowerCase() == "table")return null;
	   }
	obj.click();
	var data = $("#DT1").getSelectedRow();

	$("#resultXML").val(data);
	$(window).manhuaDialog({"title":"安装企业>人员审核","type":"text","content":"${pageContext.request.contextPath }/jsp/business/dtgl/azqy/az-rygl-add.jsp?type=update","modal":"2"});
}


function caozuoFun(obj){
	var status=obj.STATUS;
	var showHtml="";
	if(status=='30'){
		showHtml +="<span class='btn btn-link' id='addSpan' onclick='rowSh(this);' title='审核' >";
		showHtml +="<i class='icon-file'></i> ";
		showHtml +="</span>";
		
		//showHtml += "<a href='javascript:rowSh("+obj+");' title='审核'><i class='icon-file'></i></a>";	
	}else{
		showHtml +="<span class='btn btn-link' id='addSpan' onclick='rowView(this);' title='查看' >";
		showHtml +="<i class='icon-file'></i> ";
		showHtml +="</span>";
		//showHtml += "<a href='javascript:rowView("+obj+");' title='查看'><i class='icon-file'></i></a>";
	}
	return showHtml;
}

//回车事件
function enterSumbit(){  
    var event=arguments.callee.caller.arguments[0]||window.event;//消除浏览器差异  
   if (event.keyCode == 13){  
	   $("#btnQuery").click();  
   }  
}  

function sffkfun(v){
	$('#sffk').val(v);
	$('#btnQuery').click();
}

function sfyhtfun(v){
	$('#sfyht').val(v);
	$('#btnQuery').click();
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
							<input type="hidden" id="sffk" type="text" fieldname="M.CARD_FLAG"  operation=" is " />
							<input type="hidden" id="sfyht" type="text" fieldname="L.LAOWU_CONTRACTS_UID"  operation=" is " />
						</TD>
					</TR>
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr>
						
						<th  class="right-border bottom-border text-right">姓名</th>
						<td class="right-border bottom-border" width="10%">
							<input class="span12" type="text" onkeydown="enterSumbit()" id="MINGONG_NAME" name="MINGONG_NAME" fieldname="MINGONG_NAME" operation="like" >
						</td>	
						<th class="right-border bottom-border text-right">证件号码</th>
						<td class="right-border bottom-border" width="20%">
							<input class="span12" type="text" onkeydown="enterSumbit()" id="ZHENGJIAN_NUMBER" name="ZHENGJIAN_NUMBER" fieldname="ZHENGJIAN_NUMBER" operation="=" >
						</td>	
						<th  class="right-border bottom-border text-right">是否发卡</th>
						<td class="right-border bottom-border" width="10%">
							<select id="xxk" onchange="sffkfun(this.value);" style="width: 100%">
								<option value="">--请选择--</option>
								<option value="not null">已发</option>
								<option value="null">未发</option>
							</select>
						</td>	
						<th  class="right-border bottom-border text-right">是否有合同</th>
						<td class="right-border bottom-border" width="10%">
							<select id="ht" onchange="sfyhtfun(this.value);" style="width: 100%">
								<option value="">--请选择--</option>
								<option value="not null">有</option>
								<option value="null">无</option>
							</select>
						</td>	
						<td class="text-left bottom-border text-right">
	                        <button id="btnQuery" class="btn btn-link"  type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-search"></i>查询</button>
           					<button id="btnClear" class="btn btn-link" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-trash"></i>清空</button>
           					<!--  <button id="btnAddYj" class="btn" type="button"> 登记务工人员  </button> -->
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
							<th fieldname="MINGONG_NAME" colindex=2 tdalign="center" maxlength="30">&nbsp;姓名&nbsp;</th>
							<th fieldname="ZP" colindex=2 tdalign="center" maxlength="30">&nbsp;照片&nbsp;</th>
							<th fieldname="XXK" colindex=2 tdalign="center" maxlength="30">&nbsp;信息卡&nbsp;</th>
							<th fieldname="HT" colindex=2 tdalign="center" maxlength="30">&nbsp;合同&nbsp;</th>
							<th fieldname="ZHENGJIAN_NUMBER" colindex=2 tdalign="center" maxlength="30" >&nbsp;证件号码&nbsp;</th>
							<th fieldname="COMPANYS_NAME" colindex=3 tdalign="center" maxlength="30" >&nbsp;工作单位&nbsp;</th>
							<th fieldname="LIANXI_PHONE" colindex=3 tdalign="center" maxlength="30" >&nbsp;联系电话&nbsp;</th>
							<th fieldname="ORIGIN" colindex=20 tdalign="center" >&nbsp;籍贯&nbsp;</th>					
							<th fieldname="ZT" colindex=20 tdalign="center" >&nbsp;状态&nbsp;</th>					
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
		
<%--		<input type="hidden" name="txtFilter" order="desc" fieldname="ID" id="txtFilter"/>--%>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>