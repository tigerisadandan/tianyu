<%@page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext"%>
<%@page import="com.ccthanking.framework.model.User"%>
<%@page import="com.ccthanking.framework.Globals"%><!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>

<app:base/>
<title>人员信息首页</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/person/SgPersonLibraryController/";
var controllernamePersonZhengshu="${pageContext.request.contextPath }/person/SgPersonZhengshuController/";
var jsonStr = "";
//页面初始化
$(function() {
	init();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"query",data,DT1,null,true);
		 var rows = $("tbody tr" ,$("#DT1"));
			if(rows.size()==0){
				$("tbody" ,$("#DT1")).append("<tr><td colspan=\"12\" style=\"height: 1px;\" align=\"center\">暂无记录</td></tr>");
			}
	});
	$("#btnExpExcel").click(function() {
		
	  	 $(window).manhuaDialog({"title":"导出","type":"text","content":"/wndjsbg/jsp/framework/print/TabListEXP.jsp?tabId=DT1","modal":"3"});
	});
	
	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#find").clearFormResult(); 
        //closeParentCloseFunction();
        init();
    });
 
});

function doPrint() {    
        //bdhtml=window.document.body.innerHTML;    
        //sprnstr="<!--startprint-->";    
        //eprnstr="<!--endprint-->";    
        //prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17);    
        //prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));    
        //window.document.body.innerHTML=prnhtml; 
        //window.print();    
    window .print();
}    
//页面默认参数
function init(){
	$("#STATUS").val(1);
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
    defaultJson.doQueryJsonList(controllername+"query",data,DT1,null,true);//,null,true无记录时不弹出提示 
    //处理IE浏览器在查询无记录时出现的滚动条问题
    var rows = $("tbody tr" ,$("#DT1"));
	if(rows.size()==0){
		$("tbody" ,$("#DT1")).append("<tr><td colspan=\"12\" style=\"height: 1px;\" align=\"center\">暂无记录</td></tr>");
	}
}
//在名字上显示登陆代码
function doDengluCode(obj){
	var showHtml = "";
	showHtml = "<a href='javascript:void(0);' title='登陆代码:"+obj.DENGLU_CODE+"'>"+obj.PERSON_NAME+"</a>";
	return showHtml;
}
//对证书进行换行显示
function doList(obj){	
	var showHtml = "";
	var rt = "";
	$.ajax({
		url : controllernamePersonZhengshu+"queryZS?id="+obj.SG_PERSON_LIBRARY_UID,
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'get',
		success : function(response) {
			rt = response.msg;
		}
	});
	var arr = rt.split(",");
	for (i = 0; i < arr.length; i++) {
		showHtml += (arr[i].toString()+",");//对后台传出来的字符串，通过,号分割成数组 
		if((i+1)%2==0){//每两个数组元素换一行 
			showHtml+="<br/>";
		}
	}
	if(showHtml!=""){
		showHtml = showHtml.substring(0,showHtml.length-1);
	}
	return showHtml;
}
//对公司名称进行换行显示 
function doCn(obj){
	var showHtml = "";
	var arr = obj.COMPANY_NAME;
	var ibs = Math.floor(arr.length/15);
	if(ibs==0){
		showHtml = obj.COMPANY_NAME;
	}else{
		for ( var i = 0; i <= ibs; i++) {
				if(i==ibs){
					showHtml += arr.substring(i*15);
<%--					alert("i倍数："+i+",处理结果："+arr.substring(i*3));--%>
				}else{
					showHtml += arr.substring(i*15,(i+1)*15)+"<br/>";
<%--					alert("i倍数："+i+",处理结果："+arr.substring(i*3,(i+1)*3)+"<br/>");--%>
				}
		}
	}
	return showHtml;
}
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
	
    var rowValue = $("#DT1").getSelectedRow();
	var tempJson = convertJson.string2json1(rowValue);
    window.open("${pageContext.request.contextPath }/sgpersonaddone/"+tempJson.SG_PERSON_LIBRARY_UID,"人员信息查看");//传递一个ID给详细页面，用于查找 
	
}
function EnterPress(){
	if(event.keyCode == 13){
		$("#btnQuery").click();
	}
}
//关闭子窗口，父窗口自动刷新，且父窗口内的信息保持原先状态。 
function closeParentCloseFunction(){
	    var index =	$("#DT1").getSelectedRowIndex();
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		var tempJson = convertJson.string2json1(data);
		var a=$("#DT1").getCurrentpagenum();
		tempJson.pages.currentpagenum=a;
		data = JSON.stringify(tempJson);
		defaultJson.doQueryJsonList(controllername+"query",data,DT1,null,true);
		$("#DT1").cancleSelected();
		$("#DT1").setSelect(index);
		//处理IE浏览器在查询无记录时出现的滚动条问题
		  var rows = $("tbody tr" ,$("#DT1"));
			if(rows.size()==0){
				$("tbody" ,$("#DT1")).append("<tr><td colspan=\"12\" style=\"height: 1px;\" align=\"center\">暂无记录</td></tr>");
			}
}

function doExecl(tabId){
	   $(window).manhuaDialog({"title":"导出","type":"text","content":"/wndjsbg/jsp/framework/print/TabListEXP.jsp?tabId="+tabId+"&dao=sgPersonLibraryDaoImpl&jsonStr="+jsonStr,"modal":"3"});
}
</script>
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
	<p></p>
	<div class="row-fluid">
		<div class="B-small-from-table-autoConcise">
			
			<form method="post" class="noprint" id="queryForm">
				<table class="B-table" width="100%" id="find">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<TR style="display: none;">
						<TD class="right-border bottom-border">
						    <INPUT type="text" class="span12" kind="text" id="STATUS" name="STATUS" fieldname="a.STATUS" value="" operation="="/>
						</TD>
					</TR>
				<!--可以再此处加入hidden域作为过滤条件 -->
			    
			     <th width="4%" class="right-border bottom-border text-right">姓名</th>
						<td width="10%" class="right-border bottom-border ">
                            <input id="PERSON_NAME" onkeydown="EnterPress()" class="span12" type="text" autocomplete="off" placeholder="" name="PERSON_NAME" check-type="maxlength" maxlength="100" fieldname="PERSON_NAME" operation="like" logic="and"/>
						</td>
				<th width="5%" class="right-border bottom-border text-right">单位名称</th>
						<td width="18%" class="right-border bottom-border ">
                            <input id="COMPANY_NAME" onkeydown="EnterPress()" class="span12" type="text" autocomplete="off" placeholder="" name="COMPANY_NAME" check-type="maxlength" maxlength="100" fieldname="COMPANY_NAME" operation="like" logic="and"/>
						</td>
				<th width="5%" class="right-border bottom-border text-right">身份证</th>
						<td width="12%" class="right-border bottom-border ">
                            <input id="SHENFENZHENG" onkeydown="EnterPress()" class="span12" type="text" autocomplete="off" placeholder="" name="SHENFENZHENG" check-type="maxlength" maxlength="100" fieldname="SHENFENZHENG" operation="like" logic="and"/>
						</td>
						
						<td width="12%"  class="right-border bottom-border">
						   <select id="ZHICHENG_UID" onkeydown="EnterPress()" class="span12" style="width:95%" name="ZHICHENG_UID" fieldname="a.ZHICHENG_UID" kind="dic" src="T#zhicheng: zhicheng_uid as c:zhicheng_name as t"  defaultMemo="-----职称-----" operation="like" logic="and"></select>
						</td>	
				
				<td class="right-border bottom-border text-right" width="12%" rowspan="2" style="background-image: none; background-position: initial initial; background-repeat: initial initial;">
	                 <button id="btnQuery" class="btn btn-link"  type="button"><i class="icon-search"></i>查询</button>
	                 <button id="btnClear" class="btn btn-link" type="button"><i class="icon-trash"></i>清空</button>
           	   </td>
           	   </tr>
           	  
			   </table>
			</form>
			<div style="height:5px;"> </div>	
			<div class="overFlowX">
			 <!--startprint-->
	            <table width="100%"  print="true" class="table-hover table-activeTd B-table" id="DT1" type="single"  pageNum="14">
	              <thead>
	                <tr>
	                
	                		<th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
	                		<th  fieldname="SG_PERSON_LIBRARY_UID" tdalign="center" colindex=2 CustomFunction="doRandering" noprint="true">&nbsp;&nbsp;</th><%--
	                        <th fieldname="PERSON_NAME" width="6%" colindex=0 tdalign="center" >&nbsp;姓名&nbsp;</th>
							--%>
							<th fieldname="PERSON_NAME" width="6%" colindex=0 tdalign="center"  CustomFunction="doDengluCode">&nbsp;姓名&nbsp;</th>
							<th fieldname="COMPANY_NAME" width="15%" colindex=1 tdalign="center" maxlength="30" CustomFunction="doCn">&nbsp;施工单位名称&nbsp;</th>
							<th fieldname="ZAIJIANXIANGMU" width="13%" colindex=2 tdalign="center" maxlength="30" >&nbsp;在建项目&nbsp;</th>
							<th fieldname="SEX" width="3%" colindex=3  tdalign="center" >&nbsp;性别&nbsp;</th>
							<th fieldname="SHENFENZHENG" width="13%" colindex=5 tdalign="center" maxlength="30">&nbsp;身份证&nbsp;</th>
							<th fieldname="ZHENGSHU_LIST" width="10%" colindex=4 tdalign="center" maxlength="30" CustomFunction="doList">&nbsp;从业资格证书&nbsp;</th>
							<th fieldname="ZHICHENG_NAME" width="8%" colindex=6 tdalign="center" maxlength="30" >&nbsp;职称&nbsp;</th>
							<th fieldname="PHONE" width="10%" colindex=6 tdalign="center" maxlength="30" >&nbsp;联系电话&nbsp;</th>
							<th fieldname="XINYONGFENSHU" width="6%" colindex=7 tdalign="center" >&nbsp;信用分数&nbsp;</th>
							<th fieldname="YEJI" colindex=8 width="6%" tdalign="center" >&nbsp;业绩&nbsp;</th>
							
	                </thead>
	              	<tbody></tbody>
	           </table>
	           <!--endprint-->
	       </div>
	 	</div>
	</div>     
</div>
<div align="center">
	<FORM name="frmPost" method="post" style="display: none" target="_blank">
		<!--系统保留定义区域-->
		<input type="hidden" name="queryXML"/> 
		<input type="hidden" name="txtXML"/>
		<input type="hidden" name="txtFilter" order="desc" fieldname="UPDATE_DATE" id="txtFilter"/>
<%--		<input type="hidden" name="txtFilter" order="desc" fieldname="ID" id="txtFilter"/>--%>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>