<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>人员信息首页</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/sgbb/sgBbController.do";
function setPageHeight(){
	var height = g_iHeight-pageTopHeight-pageTitle-pageQuery-getTableTh(3)-pageNumHeight;
	var pageNum = parseInt(height/pageTableOne,10);
	$("#DT1").attr("pageNum",pageNum);
}

//页面初始化
$(function() {
	setPageHeight();
	init();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		//调用ajax插入
		//defaultJson.doQueryJsonList(controllername+"?query",data,DT1);
		defaultJson.doQueryJsonList(controllername+"?queryZbgg",data,DT1, null, true);

		var rows = $("tbody tr" ,$("#DT1"));
		if(rows.size()==0){
			$("tbody" ,$("#DT1")).append("<tr><td colspan=\"6\" style=\"height: 1px;\">&nbsp;</td></tr>");
		}
	});
	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#queryForm").clearFormResult(); 
        $("#btnQuery").click();
    });
	
	//按钮绑定事件(修改)
	$("#btnYes").click(function() {
		if($("#DT1").getSelectedRowIndex()==-1)
		{
			requireSelectedOneRow();
			return
		}
		
		var fuyemian=$(window).manhuaDialog.getParentObj();
		var rowValue = $("#DT1").getSelectedRow();
		fuyemian.loadXmxx(rowValue);
      	$(window).manhuaDialog.close();
		
		//$(window).manhuaDialog({"title":"业务页面模版表>修改","type":"text","content":"${pageContext.request.contextPath }/jsp/business/person/person_add.jsp?type=update","modal":"1"});
	});

});


function tr_dbclick(obj,tabListid){
	$("#btnYes").click();
}


var gw_uid= "";
//页面默认参数
function init(){

	var parentmain=$(window).manhuaDialog.getParentObj();	
	gw_uid = parentmain.gw_now;
	
	clickRadioShowDate();
	
	$("input:radio[name='RYSHZT']").each(function(){
		$(this).attr("onchange","clickRadioShowDate()");
	})
}

function clickRadioShowDate(){
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?queryZbgg",data,DT1, null, true);
	
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
function doRandering(obj){
	var showHtml = "";
	showHtml = "<a href='javascript:rowView1(this);' title='查看详细信息'><i class='icon-file showXmxxkInfo'></i></a>";
	return showHtml;
}
function EnterPress(){
	if(event.keyCode == 13){
		$("#btnQuery").click();
	}
}
</script>
</head>
<body onkeydown="EnterPress()">
<app:dialogs/>
<div class="container-fluid">
	<p></p>
	<div class="row-fluid">
		<div class="B-small-from-table-autoConcise">
		   	<h4 class="title">人员管理
				<span class="pull-right">
					<button id="btnYes" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">确定</button>
				</span>
			</h4>
			<form method="post" id="queryForm">
				<table class="B-table" width="100%">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<TR style="display: none;">
						<TD class="right-border bottom-border">
						</TD>
					</TR>
					
					<!--可以再此处加入hidden域作为过滤条件 -->
				<tr>
			    	<th width="5%" class="right-border bottom-border text-right" >项目名称</th>
					<td width="20%" class="right-border bottom-border ">
                 		<input id="PRO_NAME"  class="span12" type="text" autocomplete="off" placeholder="" name="PRO_NAME" check-type="maxlength" maxlength="100" fieldname="PRO_NAME" operation="like" logic="and"/>
					</td>
					<th width="5%" class="right-border bottom-border text-right" >标段编号</th>
					<td width="13%" class="right-border bottom-border ">
                 		<input id="BD_CODE"  class="span12" type="text" autocomplete="off" placeholder="" name="BD_CODE" check-type="maxlength" maxlength="100" fieldname="BD_CODE" operation="like" logic="and"/>
					</td>
					<th width="5%" class="right-border bottom-border text-right" >建设单位</th>
					<td width="13%" class="right-border bottom-border ">
                 		<input id="JS_NAME"  class="span12" type="text" autocomplete="off" placeholder="" name="JS_NAME" check-type="maxlength" maxlength="100" fieldname="JS_NAME" operation="like" logic="and"/>
					</td>
					<td rowspan="2" class="right-border bottom-border text-right" width="10%" rowspan="2" style="background-image: none; background-position: initial initial; background-repeat: initial initial;">
	                  	<button id="btnQuery" class="btn btn-link"  type="button"><i class="icon-search"></i>查询</button>
	                 	<button id="btnClear" class="btn btn-link" type="button"><i class="icon-trash"></i>清空</button>
           	   		</td>
				</tr>
				
				<tr>
					<th width="5%" class="right-border bottom-border text-right" >开标时间</th>
					<td width="13%" class="right-border bottom-border ">
                 		<input class="date" type="date" name="GG_BEGIN_DATE" style="width:93%;" fieldname="GG_BEGIN_DATE" operation=">=" fieldtype="date" fieldformat="YYYY-MM-DD">
					</td>
					<th width="5%" class="right-border bottom-border text-center" >至</th>
					<td width="13%" class="right-border bottom-border " colspan="2">
                 		<input class="date" type="date" name="GG_BEGIN_DATE" style="width:93%;" fieldname="GG_BEGIN_DATE" operation=">=" fieldtype="date" fieldformat="YYYY-MM-DD">
					</td>
					
			   </tr>
			</table> 
		</form>
		<div style="height:5px;"> </div>	
		<div class="overFlowX">
	            <table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single"  pageNum="18">
	              <thead>
	                <tr>
	                	<th name="XH" id="_XH" style="width:10px" colindex=0 tdalign="center">&nbsp;#&nbsp;</th>
	                	<th fieldname="PRO_NAME"  colindex=1 tdalign="center">&nbsp;项目名称&nbsp;</th>
	               		<th fieldname="BD_CODE"  colindex=2 tdalign="center">&nbsp;标段编号&nbsp;</th>
						<th fieldname="BD_TYPE" colindex=3 tdalign="center" >&nbsp;标段类型&nbsp;</th>
						<th fieldname="JS_NAME"  colindex=4 tdalign="center" maxlength="30" >&nbsp;建设单位&nbsp;</th>
						<th fieldname="KB_DATE" colindex=5 tdalign="center" maxlength="30">&nbsp;开标时间&nbsp;</th>
	                </thead>
	              	<tbody></tbody>
	           </table>
	       </div>
	           <div><b style="color:blue;">*双击可直接选择项目</b></div>
	 	</div>
	</div>     
</div>
<div align="center">
	<FORM name="frmPost" method="post" style="display: none" target="_blank">
		<!--系统保留定义区域-->
		<input type="hidden" name="queryXML"/> 
		<input type="hidden" name="txtXML"/>
		<input type="hidden" name="txtFilter" order="desc" fieldname="a.CREATED_DATE" id="txtFilter"/>
<%--		<input type="hidden" name="txtFilter" order="desc" fieldname="ID" id="txtFilter"/>--%>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>