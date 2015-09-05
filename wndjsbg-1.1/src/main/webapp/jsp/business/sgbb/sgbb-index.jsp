<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>施工报备首页</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/sgbb/sgBbController/";
var hasTy = 0;
function setPageHeight(){
	var height = g_iHeight-pageTopHeight-pageTitle-pageQuery-getTableTh(4)-pageNumHeight;
	var pageNum = parseInt(height/pageTableOne,10);
	$("#DT1").attr("pageNum",pageNum);
}
//页面初始化
$(function() {

	init();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {
		doQuery();
	});
	//按钮绑定事件(新增)
	$("#btnInsert").click(function() {
		$(window).manhuaDialog({"title":"施工报备>新增","type":"text","content":"${pageContext.request.contextPath }/jsp/business/sgbb/before-bb.jsp","modal":"1"});
<%--		window.open("${pageContext.request.contextPath }/jsp/business/sgbb/sgbb-add.jsp","施工报备-新增");--%>
	});
	//按钮绑定事件(修改)
	$("#btnUpdate").click(function() {
		if($("#DT1").getSelectedRowIndex()==-1)
		 {
			requireSelectedOneRow();
		    return
		 }
		$("#resultXML").val($("#DT1").getSelectedRow());
		$(window).manhuaDialog({"title":"施工报备>修改","type":"text","content":"${pageContext.request.contextPath }/jsp/business/sgbb/sgbb-add.jsp?type=update","modal":"1"});
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
        $("input:radio[name='SGBB_STATUS']")[3].checked = true;
    	$("input:checkbox[name='BID_TYPE']")[1].checked = true;
    	$("input:checkbox[name='BID_TYPE']")[2].checked = true;
    	$("input:checkbox[name='BID_TYPE']")[3].checked = true;
        $("#btnQuery").click();
        if($("input:radio[name='SGBB_STATUS']:checked").val()=="2"){
        	//$("#KB_DATE").val(getCudate());
    	}
        ankzFun();//清空时按钮权限控制
    });
    $("#btnExpExcel").click(function() {
	  	 $(window).manhuaDialog({"title":"导出","type":"text","content":"/wndjsbg/jsp/framework/print/TabListEXP.jsp?tabId=DT1","modal":"3"});
	});
   
	
});
function openBB(){
	$(window).manhuaDialog({"title":"施工报备>新增","type":"text","content":"${pageContext.request.contextPath }/jsp/business/sgbb/sgbb-add.jsp","modal":"1"});
}
function EnterPress(){
	if(event.keyCode == 13){
		$("#btnQuery").click();
	}
}
function doQuery(){
	var status_v = $("input:radio[name='SGBB_STATUS']:checked").val();
	if(status_v=="2"){
		//$("#KB_DATE").val(getCudate());
		$("input:checkbox[name='BID_TYPE']")[3].checked = true;
	}else{
		$("#KB_DATE").val("");
	}
	var returnValue = getCheckBoxSelect();
	
	
	if(status_v=="20"){
		$("#xmbh_v").show();
		$("#xmbh").show();
		$("#DT1_d").hide();
		$("#DT2_d").show();
		$("#page_DT1").hide();
		$("#page_DT2").show();
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT2);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"query?statuses="+returnValue+"&date_time="+$("#KB_DATE").val(),data,DT2,null,true);
		var rows2 = $("tbody tr" ,$("#DT2"));
		if(rows2.size()==0){
			$("tbody" ,$("#DT2")).append("<tr><td colspan=\"11\" style=\"height: 1px;\">&nbsp;</td></tr>");
		}
	}else{
		$("#SHIGONG_PROJECTS_CODE").val("");
		$("#xmbh_v").hide();
		$("#xmbh").hide();
		$("#DT1_d").show();
		$("#DT2_d").hide();
		$("#page_DT1").show();
		$("#page_DT2").hide();
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"query?statuses="+returnValue+"&date_time="+$("#KB_DATE").val(),data,DT1,null,true);
		var rows = $("tbody tr" ,$("#DT1"));
		if(rows.size()==0){
			$("tbody" ,$("#DT1")).append("<tr><td colspan=\"10\" style=\"height: 1px;\">&nbsp;</td></tr>");
		}
		
	}
	
}
//页面默认参数
function init(){
	$("input:radio[name='SGBB_STATUS']")[3].checked = true;
	$("input:checkbox[name='BID_TYPE']")[1].checked = true;
	$("input:checkbox[name='BID_TYPE']")[2].checked = true;
	$("input:checkbox[name='BID_TYPE']")[3].checked = true;
	$("input[name='KB_DATE']")[0].value = getCudate();
	$("#xmbh_v").hide();
	$("#xmbh").hide();
	$.each($("input:radio[name='SGBB_STATUS']"),function(){
		$(this).attr("onchange","doQuery()");
		$(this).attr("disabled",true);
	})
	$.each($("input:checkbox[name='BID_TYPE']"),function(){
		$(this).attr("disabled",true);
	})
	
	
	ankzFun();
	doQuery();
}


function ankzFun(){
	var flag1 = true;
	var flag2 = true;
	var flag3 = true;
	var flag4 = true;
	var flag5 = true;
	var flag6 = true;

	var ckyzbbbxx=$("#ckyzbbbxx").val();
	if(ckyzbbbxx=='ckyzbbbxx'){
		flag1=false;
		$("input:radio[name='SGBB_STATUS']")[1].disabled=false;
	}
	
	var ckwzbbbxx=$("#ckwzbbbxx").val();
	if(ckwzbbbxx=='ckwzbbbxx'){
		flag2=false;
		$("input:radio[name='SGBB_STATUS']")[2].disabled=false;
	}
	var cktbzbbxx=$("#cktbzbbxx").val();
	if(cktbzbbxx=='cktbzbbxx'){
		flag3=false;
		$("input:radio[name='SGBB_STATUS']")[3].disabled=false;
	}


	var fbfsgkzb=$("#fbfsgkzb").val();
	if(fbfsgkzb=='fbfsgkzb'){
		flag4=false;
		$("input:checkbox[name='BID_TYPE']")[1].disabled=false;
	}


	var fbfsyqzb=$("#fbfsyqzb").val();
	if(fbfsyqzb=='fbfsyqzb'){
		flag5=false;
		$("input:checkbox[name='BID_TYPE']")[2].disabled=false;
	}

	var fbfszjfb=$("#fbfszjfb").val();
	if(fbfszjfb=='fbfszjfb'){
		flag6=false;
		$("input:checkbox[name='BID_TYPE']")[3].disabled=false;
	}

	if(flag1){
		$("input:radio[name='SGBB_STATUS']")[1].checked=false;
	}
	if(flag2){
		$("input:radio[name='SGBB_STATUS']")[2].checked=false;
	}
	if(flag3){
		$("input:radio[name='SGBB_STATUS']")[3].checked=false;
	}
	if(flag4){
		$("input:checkbox[name='BID_TYPE']")[1].checked=false;
	}
	if(flag5){
		$("input:checkbox[name='BID_TYPE']")[2].checked=false;
	}
	if(flag6){
		$("input:checkbox[name='BID_TYPE']")[3].checked=false;
	}
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

function doRandering(obj){
	var showHtml = "";
	var title = obj.STATUS == "2"?"修改":"查看详细信息";
	showHtml = "<a href='javascript:rowView("+obj.STATUS+","+obj.SGBB_UID+");' title='"+title+"'><i class='icon-file showXmxxkInfo'></i></a>";
	return showHtml;
	
}
function getCheckBoxSelect(){
	var values = "";
	$("input:checkbox[name='BID_TYPE']:checked").each(function(){
		values += ($(this).val()+",");
	})
	if(values.length!=0){
		values = values.substring(0,values.length-1);
	}
	return values;
}
//详细信息
function rowView(status,uid){
	window.open("${pageContext.request.contextPath }/jsp/business/sgbb/sgbb-view.jsp?id="+uid);
}



function docolor(obj)
{
	var xqzt=obj.STATUS;
	if(xqzt=="2"){
		return '<span class="label label-info">投标中</span>';
	}else if(xqzt=="20"||xqzt=="50"){
	 	return '<span class="label label-success">已中标</span>';
	}else if(xqzt=="10"){
	 	return '<span class="label label-warning">未中标</span>';
	}
}
function doPrint(obj){
	return "<a href='javascript:void(0)'><i class='icon-print' onclick='doLoad("+obj.SGBB_UID+")'  title='打印登记表'></i></a>";
}
function doLoad(uid){
	window.open("${pageContext.request.contextPath }/sgbb/sgBbController/query4Print?bbid="+uid+"&type=pdf","打印登记表");
}
function doRy(obj){
	var showHtml = "";
	var arr = obj.SG_NAMES.split(",");
	for (i = 0; i < arr.length; i++) {
		showHtml += (arr[i].toString()+",");
		if((i+1)%4==0){
			showHtml+="<br/>";
		}
	}
	if(showHtml!=""){
		showHtml = showHtml.substring(0,showHtml.length-1);
	}
	return showHtml;
}
function doGc(obj){
	var showHtml = "";
	var arr = obj.PROJECT_NAME;
	var ibs = Math.floor(arr.length/15);
	if(ibs==0){
		showHtml = obj.PROJECT_NAME;
	}else{
		for ( var i = 0; i < ibs; i++) {
			if(!((i+1)*15>=obj.PROJECT_NAME.length)){
				if(i==ibs-1){
					showHtml += arr.substring(i*15);
				}else{
					showHtml += arr.substring(i*15,(i+1)*15)+"<br/>";
				}
			}
		}
	}
	return showHtml;
}
function doCode(obj){
	var showHtml = "";
	if(obj.BB_CODE!=""){
		showHtml += (obj.BB_CODE.split("-")[0]+"-"+"<br/>"+obj.BB_CODE.split("-")[1])
	}
	return showHtml;
}
function queryChange(){
	$("#btnQuery").click();
}
function doBdxm(obj){
	if(obj.SHIGONG_PROJECTS_CODE!=""){
		return "<i class='icon-ok' title='绑定项目:"+obj.PROJECTS_NAME+"("+obj.SHIGONG_PROJECTS_CODE+")'></i>";
	}else{
		return "<i class='icon-remove' title='未绑定项目'></i>";
	}
}
function doWgjs(obj){

	if(obj.STATUS=="20"){
		return "<a href='javascript:void(0)' onclick=lockOp("+obj.SGBB_UID+",'doBb')><div style='width:16px;'><img src='${pageContext.request.contextPath }/images/sgxt/lock.png' title='已锁定,点击完工解锁'/></div></a>";
	}else if(obj.STATUS=="50"){
		return "<div style='width:16px;'><img src='${pageContext.request.contextPath }/images/sgxt/lock-unlock.png' title='已完工解锁'/></div>";
	}
}
function lockOp(uid,optype){
	var title = "";
	if (optype=="doBb") {
		title = "确定完工并解锁所有施工人员吗?";
	}
	 var flag = confirm(title);
	 if(!flag){return;}
	$.ajax({
		url : controllername+"/updateBbToUnlock",
		data : {"uid":uid,"optype":optype},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			alert("已解锁施工人员");
			doQuery();
		}
	});
}
function getCudate(){
	var now = new Date();
    
    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日
   
    var hh = now.getHours();            //时
    var mm = now.getMinutes();          //分
   
    var clock = year + "-";
   
    if(month < 10)
        clock += "0";
   
    clock += month + "-";
   
    if(day < 10)
        clock += "0";
       
    clock += day + " ";
   
    if(hh < 10)
        clock += "0";
       
    clock += hh + ":";
    if (mm < 10) clock += '0'; 
    clock += mm; 
    clock += ":00"; 
    return(clock);
}
</script>
</head>
<body onkeydown="EnterPress()">
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
							<input class="span12" type="text" id="KB_DATE" name="KB_DATE">
						</TD>
					</TR>
					
					<app:oPerm url="ckyzbbbxx">
						<input type="hidden" id="ckyzbbbxx" name="ckyzbbbxx" value="ckyzbbbxx"/> 
					</app:oPerm> 
					<app:oPerm url="ckwzbbbxx">
						<input type="hidden" id="ckwzbbbxx" name="ckwzbbbxx" value="ckwzbbbxx"/> 
					</app:oPerm> 
					<app:oPerm url="cktbzbbxx">
						<input type="hidden" id="cktbzbbxx" name="cktbzbbxx" value="cktbzbbxx"/> 
					</app:oPerm> 
				
					<app:oPerm url="fbfsgkzb">
						<input type="hidden" id="fbfsgkzb" name="fbfsgkzb" value="fbfsgkzb"/> 
					</app:oPerm> 
					<app:oPerm url="fbfsyqzb">
						<input type="hidden" id="fbfsyqzb" name="fbfsyqzb" value="fbfsyqzb"/> 
					</app:oPerm> 
					<app:oPerm url="fbfszjfb">
						<input type="hidden" id="fbfszjfb" name="fbfszjfb" value="fbfszjfb"/> 
					</app:oPerm>
					
					
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr>
						<th width="7%" class="right-border bottom-border text-right" colspan="2">报备状态</th>
						<td class="right-border bottom-border" width="10%" colspan="3">
							<input class="span12" id="SGBB_STATUS" type="radio" name="SGBB_STATUS" kind="dic" src="T#FS_DIC_TREE: DIC_CODE as c:DIC_VALUE as t:PARENT_ID ='1000000000040' order by to_number(DIC_CODE) DESC" operation="=" fieldname="t.STATUS" />
						</td>
						<th width="7%" class="right-border bottom-border text-right" colspan="2">发包方式</th>
						<td class="right-border bottom-border" width="30%" colspan="3">
							<input class="span12" type="checkbox" placeholder=""  name="BID_TYPE" kind="dic" onchange="queryChange()" operation="=" src="BID_TYPE">
						</td>
						<td class="text-left bottom-border text-right" rowspan="2">
	                        <button id="btnQuery" class="btn btn-link"  type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-search"></i>查询</button>
           					<button id="btnClear" class="btn btn-link" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-trash"></i>清空</button>
			            </td>						
					</tr>
					<tr>
						<th width="7%" class="right-border bottom-border text-right">项目编号</th>
						<td class="right-border bottom-border" width="10%"  >
							<input class="span12" type="text" id="PROJECT_CODE" name="PROJECT_CODE" fieldname="PROJECT_CODE" operation="like" >
						</td>
						<th width="7%" class="right-border bottom-border text-right">报备编码</th>
						<td class="right-border bottom-border" width="10%">
							<input class="span12" type="text" id="BB_CODE" name="BB_CODE" fieldname="BB_CODE" operation="like" >
						</td>
						
			            
						<th width="7%" class="right-border bottom-border text-right">项目名称</th>
						<td class="right-border bottom-border" width="10%">
							<input class="span12" type="text" id="PROJECT_NAME" name="PROJECT_NAME" fieldname="PROJECT_NAME" operation="like" >
						</td>
						<th width="7%" class="right-border bottom-border text-right">单位名称</th>
						<td class="right-border bottom-border" width="10%">
							<input class="span12" type="text" id="COMPANY_NAME" name="COMPANY_NAME" fieldname="sel.COMPANY_NAME" operation="like" >
						</td>
						<th width="7%" class="right-border bottom-border text-right">施工人员</th>
						<td class="right-border bottom-border" width="10%">
							<input class="span12" type="text" id="n.SG_NAME" name="n.SG_NAME" fieldname="n.SG_NAME" operation="like" >
						</td>
						
					</tr>
				</table>
			</form>
			<div style="height:5px;"> </div>	
			<div id="DT1_d" class="overFlowX">
	            <table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single"  pageNum="9">
	                <thead>
	                	<tr>
	                		<th name="XH" id="_XH" style="width:10px" colindex=1 noprint="true" tdalign="center">&nbsp;#&nbsp;</th>
	                		<th fieldname="SGBB_UID" colindex=2 tdalign="center" width="1%" Customfunction="doRandering" noprint="true">&nbsp;&nbsp;</th>
	                		<th fieldname="SGBB_UID" colindex=4 tdalign="center" width="1%" Customfunction="doPrint" noprint="true">&nbsp;&nbsp;</th>
							<th fieldname="STATUS" colindex=5 tdalign="center" width="7%" CustomFunction="docolor">&nbsp;状态&nbsp;</th>
							<th fieldname="PROJECT_CODE" colindex=6 tdalign="center" width="15%">&nbsp;招标工程项目编号&nbsp;</th>
							<th fieldname="PROJECT_NAME" colindex=7 tdalign="center" width="20%" CustomFunction="doGc">&nbsp;项目名称&nbsp;</th>
							<th fieldname="BB_CODE" colindex=8 tdalign="center" width="13%" CustomFunction="doCode">&nbsp;报备编码&nbsp;</th>
							<th fieldname="COMPANY_NAME" colindex=9 tdalign="center" width="15%">&nbsp;施工单位&nbsp;</th>
							<th fieldname="COMPANY_DENGLU_CODE" hidden colindex=9 tdalign="center" width="15%">&nbsp;施工单位登录代码&nbsp;</th>
							<th fieldname="SG_NAME_JL" colindex=10 tdalign="center" width="7%">&nbsp;项目经理&nbsp;</th>
							<th fieldname="SG_NAMES" colindex=11 tdalign="center" width="20%" CustomFunction="doRy">&nbsp;施工人员&nbsp;</th>
	                	</tr>
	                </thead>
	              	<tbody></tbody>
	           </table>
	       </div>
	       <div id="DT2_d" class="overFlowX">
	            <table width="100%" class="table-hover table-activeTd B-table" id="DT2" type="single"  pageNum="9">
	                <thead>
	                	<tr>
	                		<th name="XH" id="_XH" style="width:10px" colindex=1 noprint="true" tdalign="center">&nbsp;#&nbsp;</th>
	                		<th fieldname="SGBB_UID" colindex=2 tdalign="center" width="1%" Customfunction="doRandering" noprint="true">&nbsp;&nbsp;</th>
							<th fieldname="STATUS" colindex=3 tdalign="center" width="1%" CustomFunction="doBdxm">&nbsp;&nbsp;</th>
	                		<th fieldname="SGBB_UID" colindex=2 tdalign="center"  Customfunction="doWgjs" noprint="true">&nbsp;&nbsp;</th>
	                		<th fieldname="SGBB_UID" colindex=4 tdalign="center" width="1%" Customfunction="doPrint" noprint="true">&nbsp;&nbsp;</th>
							<th fieldname="STATUS" colindex=5 tdalign="center" width="7%" CustomFunction="docolor">&nbsp;状态&nbsp;</th>
							<th fieldname="PROJECT_CODE" colindex=6 tdalign="center" width="15%">&nbsp;招标工程项目编号&nbsp;</th>
							<th fieldname="PROJECT_NAME" colindex=7 tdalign="center" width="20%" CustomFunction="doGc">&nbsp;项目名称&nbsp;</th>
							<th fieldname="BB_CODE" colindex=8 tdalign="center" width="13%" CustomFunction="doCode">&nbsp;报备编码&nbsp;</th>
							<th fieldname="COMPANY_NAME" colindex=9 tdalign="center" width="15%">&nbsp;施工单位&nbsp;</th>
							<th fieldname="COMPANY_DENGLU_CODE" hidden colindex=9 tdalign="center" width="15%">&nbsp;施工单位登录代码&nbsp;</th>
							<th fieldname="SG_NAME_JL" colindex=10 tdalign="center" width="7%">&nbsp;项目经理&nbsp;</th>
							<th fieldname="SG_NAMES" colindex=11 tdalign="center" width="20%" CustomFunction="doRy">&nbsp;施工人员&nbsp;</th>
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
		<input type="hidden" name="txtFilter" order="desc" fieldname="t.TIJIAO_DATE" id="txtFilter"/>
<%--		<input type="hidden" name="txtFilter" order="desc" fieldname="ID" id="txtFilter"/>--%>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>