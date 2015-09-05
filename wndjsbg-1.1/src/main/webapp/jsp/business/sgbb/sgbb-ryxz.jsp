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
var controllername= "${pageContext.request.contextPath }/person/SgPersonLibraryController.do";
var controllernameBbry= "${pageContext.request.contextPath }/sgbb/sgbbRyController.do";
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
		defaultJson.doQueryJsonList(controllername+"?queryInSgbb&gw_uid="+gw_uid,data,DT1,null,true);
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
			xInfoMsg(msg?msg:'请选择一名人员！',null);
			return
		}
		
		 var fuyemian=$(window).manhuaDialog.getParentObj();
		var rowValue = $("#DT1").getSelectedRow();
		fuyemian.loadRyxx(rowValue);
      	$(window).manhuaDialog.close();
		
		//$(window).manhuaDialog({"title":"业务页面模版表>修改","type":"text","content":"${pageContext.request.contextPath }/jsp/business/person/person_add.jsp?type=update","modal":"1"});
	});

});
function tr_click(obj,tabListid){
	var index = $("#DT1").getSelectedRowIndex();
	if($("tbody tr" ,$("#"+tabListid)).eq(index).find("td").eq(0).text().indexOf("不可用")!=-1){
		$("#btnYes").attr("disabled",true);
	}else{
		$("#btnYes").removeAttr("disabled");
		return;
		
	}
	
	if(gw_uid=="20"&&obj.ZHICHENG_NAME==""){
		$("#btnYes").attr("disabled","disabled");
		return;
	}
	
}
//点击获取行对象
function tr_dbclick(obj,tabListid){
	var index = $("#DT1").getSelectedRowIndex();
	if($("tbody tr" ,$("#"+tabListid)).eq(index).find("td").eq(0).text().indexOf("不可用")!=-1){
		alert("当前选择人员不可用!");
	}else{
		if(gw_uid=="20"&&obj.ZHICHENG_NAME==""){
			alert("当前选择人员不可用!");
		}else{
			$("#btnYes").click();
		}
	}
	
	
	
}
var gw_uid= "";
var bb_uid = "";
//页面默认参数
function init(){

	var parentmain=$(window).manhuaDialog.getParentObj();	
	gw_uid = parentmain.gw_now;
	bb_uid = parentmain.$("#SGBB_UID").val();
	clickRadioShowDate();
	
	$("input:radio[name='RYSHZT']").each(function(){
		$(this).attr("onchange","clickRadioShowDate()");
	})
}

function clickRadioShowDate(){
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?queryInSgbb&gw_uid="+gw_uid,data,DT1,null,true);

	var rows = $("tbody tr" ,$("#DT1"));
	if(rows.size()==0){
		$("tbody" ,$("#DT1")).append("<tr><td colspan=\"7\" style=\"height: 1px;\">&nbsp;</td></tr>");
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
function doRandering(obj){
	var showHtml = "";
	showHtml = "<a href='javascript:rowView1(this);' title='查看详细信息'><i class='icon-file showXmxxkInfo'></i></a>";
	return showHtml;
}
function rowView1(t){	
	//$("#DT1").setSelect(index);
	if($("#DT1").getSelectedRowIndex()==-1)
	 {
		requireSelectedOneRow();
	    return
	 }
	$("#resultXML").val($("#DT1").getSelectedRow());

	
	//var rowValue = $("#DT1").getSelectedRow();
	//var tempJson = convertJson.string2json1(rowValue);
	
	window.showModalDialog("person_detail_message.jsp?type=detail",window,'');
	//window.open("person_detail_message.jsp?type=detail");
	//window.open("${pageContext.request.contextPath }/jsp/business/person_manager/person_detail_message.jsp?type=detail");
}
function docolor(obj)
{	
	var rt = "";
	$.ajax({
		url : controllernameBbry+"?queryLockUser&sg_person_uid="+obj.SG_PERSON_UID+"&gangwei_uid="+gw_uid+"&sgbb_uid="+bb_uid,
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'get',
		success : function(response) {
			var obj1 = eval('('+response.msg+')');
			if(obj1.STATUS=="0"){
				if(gw_uid=="20"&&obj.ZHICHENG_NAME==""){
					rt = '<span class="label label-warning" title="没有职称">不可用</span>';
				}else{
					rt = '<span class="label label-success">可用</span>';
				}
			}else{
				rt = '<span class="label label-warning" title="'+obj1.REASON+'">不可用</span>';
			}
			
		}
	});
	return rt;
}
function EnterPress(){
	if(event.keyCode == 13){
		$("#btnQuery").click();
	}
}
//详细信息

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
							<INPUT type="text" class="span12" kind="text" id="STATUS" name="STATUS" fieldname="a.STATUS" value="1" operation="="/>
						</TD>
					</TR>
					
					<!--可以再此处加入hidden域作为过滤条件 -->
				<tr>
			    	<th width="5%" class="right-border bottom-border text-right" >姓名</th>
					<td width="20%" class="right-border bottom-border ">
                 		<input id="PERSON_NAME"  class="span12"  onkeyDown="EnterPress()"  type="text" autocomplete="off" placeholder="" name="a.PERSON_NAME" check-type="maxlength" maxlength="100" fieldname="PERSON_NAME" operation="like" logic="and"/>
					</td>
					<th width="5%" class="right-border bottom-border text-right" >身份证</th>
					<td width="13%" class="right-border bottom-border ">
                 		<input id="SHENFENZHENG"  class="span12" onkeyDown="EnterPress()" type="text" autocomplete="off" placeholder="" name="a.SHENFENZHENG" check-type="maxlength" maxlength="100" fieldname="a.SHENFENZHENG" operation="like" logic="and"/>
					</td>
					<td class="right-border bottom-border text-right" width="10%" rowspan="2" style="background-image: none; background-position: initial initial; background-repeat: initial initial;">
	                  	<button id="btnQuery" class="btn btn-link"  type="button"><i class="icon-search"></i>查询</button>
	                 	<button id="btnClear" class="btn btn-link" type="button"><i class="icon-trash"></i>清空</button>
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
	                	<th fieldname="PERSON_NAME"  colindex=1 tdalign="center" CustomFunction="docolor">&nbsp;状态&nbsp;</th>
	               		<th fieldname="PERSON_NAME" colindex=2 tdalign="center">&nbsp;姓名&nbsp;</th>
						<th fieldname="SEX" colindex=3 tdalign="center" >&nbsp;性别&nbsp;</th>
						<th fieldname="ZHENGSHU_LIST" colindex=4 tdalign="center" maxlength="30" >&nbsp;资格证书&nbsp;</th>
						<th fieldname="SHENFENZHENG" colindex=5 tdalign="center" maxlength="30">&nbsp;身份证&nbsp;</th>
						<th fieldname="ZHICHENG_NAME" colindex=6 tdalign="center" maxlength="30" >&nbsp;职称&nbsp;</th>
	                </thead>
	              	<tbody></tbody>
	           </table>
	       </div>
	           <div><b style="color:blue;">*双击可直接选择人员</b></div>
	 	</div>
	</div>     
</div>
<div align="center">
	<FORM name="frmPost" method="post" style="display: none" target="_blank">
		<!--系统保留定义区域-->
		<input type="hidden" name="queryXML"/> 
		<input type="hidden" name="txtXML"/>
		<input type="hidden" name="txtFilter" order="desc" fieldname="a.SERIAL_NO" id="txtFilter"/>
<%--		<input type="hidden" name="txtFilter" order="desc" fieldname="ID" id="txtFilter"/>--%>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>