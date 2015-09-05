<!DOCTYPE html>
<%@page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext"%>
<%@page import="com.ccthanking.framework.common.User" %>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<!-- 添加的 样式 及页面  -->


<app:base/>
<title>内部通知管理首页</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/ywlz/css/default.css"> </LINK>

<LINK type="text/css" rel="stylesheet" href="/wndjsbg/css/style.css"> </LINK>
<link type="text/css" rel="stylesheet" href="/wndjsbg/css/style-responsive.css">
<link type="text/css" rel="stylesheet" href="/wndjsbg/css/bootstrap.css">
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername = "${pageContext.request.contextPath }/xzcf/XZCFController";


//页面初始化
$(function() {
    
    init();     
    //全部处罚 按钮 
	$("#btnQuery").click(function() {
		$(window).manhuaDialog({"title":"处罚清单","type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/xzcf/case-all-view.jsp","modal":"2"});
	});
	
	//添加通知按钮
	$("#btnAdd").click(function(){
		$(window).manhuaDialog({"title":"行政处罚立案登记","type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/xzcf/case-confirm-add.jsp","modal":"2"});
	});
	
	//全部处罚 按钮 
	//$("#btnLook").click(function() {
	//	$(window).manhuaDialog({"title":"打印处罚清单","type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/xzcf/download-content-view.jsp","modal":"2"});
	//});
});



function init(){
 //获取 当前工程的UID
    var GONGCHENG_UID=parent.document.getElementById("GDZXT_XM_ID").value;

 //生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?queryXzcfMsg&gcuid="+GONGCHENG_UID,data,DT1);
	
  
}

//点击获取行对象
function tr_click(obj,tabListid){
	//alert(JSON.stringify(obj));
}
//操作按钮
function caozuoFun(obj){
	
}
//查看详细信息
function doRandering(obj){
	var showHtml = "";
	showHtml = "<a href='javascript:rowView1(this);' title='修改'><i class='icon-file'></i></a></i>";
	return showHtml;
}
function rowView1(t){	
	if($("#DT1").getSelectedRowIndex()==-1)
	 	{
			requireSelectedOneRow();
	    	return
	 	}
		 var rowValue = $("#DT1").getSelectedRow();
	     var tempJson = convertJson.string2json1(rowValue);
	     var xzcfuid = tempJson.XZCF_UID		;
	     $(window).manhuaDialog({"title":"行政处罚>>修改清单","type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/xzcf/case-all-view.jsp?xzcfuid="+xzcfuid,"modal":"2"});  
}


//打印
function formatPrint(obj){
	var xzcf_uid = obj.XZCF_UID;
	return "<a href='javascript:void(0)' onclick='doPrint("+xzcf_uid+")'  title='打印处罚表'><i class='icon-print'></i></a>";
}

function doPrint(xzcf_uid){
	
	window.open("${pageContext.request.contextPath }/xzcf/XZCFController?printXzcf&xzcfuid="+xzcf_uid,"打印处罚表");
}

function getTypeName(obj){
var getTPname = obj.CF_TYPES;
var re =  /^[A-Z]+$/g;
var typeName;
   if(getTPname ==='GC'){
      typeName = getTPname.replace(re,"工程类");
    }
   /* if(getValType ==='HB'){
      typeName = getTPname.replace(re,"环保类");
    }
     if(getValType ==='PS'){
       typeName = getTPname.replace(re,"排水类");
    }
     if(getValType ==='RQ'){
      typeName = getTPname.replace(re,"燃气类");;
    } */
    return  typeName;
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
				<table class="B-table" width="100%" >
					<!--可以再此处加入hidden域作为过滤条件 -->
					<TR style="display: none;">
						<TD class="right-border bottom-border"></TD>
						<TD class="right-border bottom-border">
							<INPUT type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<=" />
						
						</TD>
					</TR>
					<!--可以再此处加入hidden域作为过滤条件 -->
				</table>
			 </form>
			 <div>
			     <table style="margin:" width="100%" class=" yw-title" >
	                  <thead>
	                    <tr>
	                        <th fieldname="ORG_NAME" colindex=2 tdalign="left" maxlength="30" >
	                        <h4 align="left">
							      处罚清单
						      	<span class="pull-right">
						      		<!-- <button id="btnQuery" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">全部处罚</button> -->
						      		<!-- <button id="btnLook" class="btn" data-last="Finish" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">
								         打印处罚清单
								   </button> -->
						      		<button id="btnAdd" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">添加处罚</button>	
						      	</span>
			                </h4>
	                        </th>
	                    </tr>
	              	<tbody></tbody>
                 </table>
                </div>
                <div style="height:5px;"></div>
	          <table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single" pageNum="10" >
	               <input  id="gongcheng_uid" type="hidden"  fieldname="GONGCHENG_UID"  name="GONGCHENG_UID" value=""/>
	                <thead>
	                	<tr>
	                		<th rowspan="2" fieldname="XZCF_UID" width="4%" tdalign="center" colindex=0 CustomFunction="doRandering" noprint="true">操作</th>
	                		<th rowspan="2" fieldname="XZCF_UID" width="4%" tdalign="center" CustomFunction="formatPrint">&nbsp;打印&nbsp;</th>
	                		<!-- <th fieldname="CF_TYPES" id ="CF_TYPES" colindex=1 tdalign="center" width="20%" CustomFunction="getTypeName">&nbsp;处罚类别&nbsp;</th> -->
							<th fieldname="DX_NAME" colindex=2 tdalign="center" width="40%" >&nbsp;处罚对象名称&nbsp;</th>
							
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
		<input type="hidden" name="txtFilter" order="asc" fieldname="XZCF_UID" id="txtFilter"/>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>