<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>八墙体材料专项基金预收款收费通知单首页</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/bzwj/bu_Spyw_Qgjjzsjftspsx_QgsftzdController.do";

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
		$(window).manhuaDialog({"title":"八墙体材料专项基金预收款收费通知单>新增","type":"text","content":"${pageContext.request.contextPath }/jsp/business/bzwj/bu_spyw_qgjjzsjftspsx_qgsftzd-add.jsp?type=insert","modal":"1"});
	});
	//按钮绑定事件(修改)
	$("#btnUpdate").click(function() {
		if($("#DT1").getSelectedRowIndex()==-1)
		 {
			requireSelectedOneRow();
		    return
		 }
		$("#resultXML").val($("#DT1").getSelectedRow());
		$(window).manhuaDialog({"title":"八墙体材料专项基金预收款收费通知单>修改","type":"text","content":"${pageContext.request.contextPath }/jsp/business/bzwj/bu_spyw_qgjjzsjftspsx_qgsftzd-add.jsp?type=update","modal":"1"});
	});
	//按钮绑定事件（导出EXCEL）
	$("#btnExpExcel").click(function() {
		 var t = $("#DT1").getTableRows();
		 if(t<=0)
		 {
			 xAlert("提示信息","请至少查询出一条记录！");
			 return;
		 }
	  	 $(window).manhuaDialog({"title":"导出","type":"text","content":"${pageContext.request.contextPath }/jsp/framework/print/TabListEXP.jsp?tabId=DT1","modal":"3"});
	});
	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#queryForm").clearFormResult();
        getNd();
    });
	
});

//页面默认参数
function init(){
	getNd();
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?query",data,DT1);
}
//默认年度
function getNd(){
	//年度信息，里修改
	$("#ZFRQ").val(new Date().getFullYear());
}

//点击获取行对象
function tr_click(obj,tabListid){
	//alert(JSON.stringify(obj));
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
function rowView(index){
	$("#DT1").setSelect(index);
	if($("#DT1").getSelectedRowIndex()==-1)
	 {
		requireSelectedOneRow();
	    return
	 }
	$("#resultXML").val($("#DT1").getSelectedRow());
	$(window).manhuaDialog({"title":"八墙体材料专项基金预收款收费通知单>详细信息","type":"text","content":"${pageContext.request.contextPath }/jsp/business/bzwj/bu_spyw_qgjjzsjftspsx_qgsftzd-add.jsp?type=detail","modal":"1"});
}
</script>
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
	<p></p>
	<div class="row-fluid">
		<div class="B-small-from-table-autoConcise">
			<h4 class="title">
				八墙体材料专项基金预收款收费通知单
				<span class="pull-right">  
					<button id="btnInsert" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">新增</button>
      				<button id="btnUpdate" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">修改</button>
      				<button id="btnExpExcel" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">导出</button>
				</span>
			</h4>
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
						<td width="7%" class="right-border bottom-border">
							<select class="span12" id="QND" name="ND" fieldname="ND" operation="=" kind="dic" src="XMNF"  defaultMemo="-年度-">
						</td>
						<th width="5%" class="right-border bottom-border text-right">项目名称</th>
						<td class="right-border bottom-border" width="20%">
							<input class="span12" type="text" id="QXMMC" name="XMMC" fieldname="XMMC" operation="like" >
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
	                		<th fieldname="ZFRQ" colindex=2 tdalign="center" maxlength="10" hasLink="true" linkFunction="rowView">&nbsp;支付日期&nbsp;</th>
							<th fieldname="QGJJZSJFTSPSX_QGSFTZD_UID" colindex=0 tdalign="center" >&nbsp;墙体材料专项基金预收款收费通知单_UID&nbsp;</th>
							<th fieldname="YWLZ_UID" colindex=1 tdalign="center" >&nbsp;审批业务流转UID&nbsp;</th>
							<th fieldname="EVENT_UID" colindex=2 tdalign="center" maxlength="30" >&nbsp;事件编号&nbsp;</th>
							<th fieldname="ENABLED" colindex=3 tdalign="center" >&nbsp;是否有效&nbsp;</th>
							<th fieldname="DESCRIBE" colindex=4 tdalign="center" maxlength="30" >&nbsp;备注&nbsp;</th>
							<th fieldname="CREATED_UID" colindex=5 tdalign="center" maxlength="30" >&nbsp;创建人编号&nbsp;</th>
							<th fieldname="CREATED_NAME" colindex=6 tdalign="center" maxlength="30" >&nbsp;创建人&nbsp;</th>
							<th fieldname="CREATED_DATE" colindex=7 tdalign="center" >&nbsp;创建时间&nbsp;</th>
							<th fieldname="UPDATE_UID" colindex=8 tdalign="center" maxlength="30" >&nbsp;更新人编号&nbsp;</th>
							<th fieldname="UPDATE_NAME" colindex=9 tdalign="center" maxlength="30" >&nbsp;更新人&nbsp;</th>
							<th fieldname="UPDATE_DATE" colindex=10 tdalign="center" >&nbsp;更新时间&nbsp;</th>
							<th fieldname="SERIAL_NO" colindex=11 tdalign="center" >&nbsp;排序号&nbsp;</th>
							<th fieldname="LZBZ_WJ_UID" colindex=12 tdalign="center" >&nbsp;流转步骤文件_UID&nbsp;</th>
							<th fieldname="DWMC" colindex=13 tdalign="center" maxlength="30" >&nbsp;单位名称&nbsp;</th>
							<th fieldname="XMMC" colindex=14 tdalign="center" maxlength="30" >&nbsp;项目名称&nbsp;</th>
							<th fieldname="SD" colindex=15 tdalign="center" maxlength="30" >&nbsp;属地&nbsp;</th>
							<th fieldname="JZMJ" colindex=16 tdalign="center" >&nbsp;建筑面积&nbsp;</th>
							<th fieldname="BCSF" colindex=17 tdalign="center" >&nbsp;本次收费(元)&nbsp;</th>
							<th fieldname="SKR" colindex=18 tdalign="center" maxlength="30" >&nbsp;收款人&nbsp;</th>
							<th fieldname="KHYH" colindex=19 tdalign="center" maxlength="30" >&nbsp;开户银行&nbsp;</th>
							<th fieldname="SKZH" colindex=20 tdalign="center" maxlength="30" >&nbsp;收款账户&nbsp;</th>
							<th fieldname="JBR" colindex=21 tdalign="center" maxlength="30" >&nbsp;经办人&nbsp;</th>
							<th fieldname="SHR" colindex=22 tdalign="center" maxlength="30" >&nbsp;审核人&nbsp;</th>
							<th fieldname="RQ" colindex=23 tdalign="center" >&nbsp;日期&nbsp;</th>
							<th fieldname="BH" colindex=24 tdalign="center" >&nbsp;编号&nbsp;</th>
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
		<input type="hidden" name="txtFilter" order="desc" fieldname="LRSJ" id="txtFilter"/>
<%--		<input type="hidden" name="txtFilter" order="desc" fieldname="ID" id="txtFilter"/>--%>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>