<!DOCTYPE html>
<%@page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext"%>
<%@page import="com.ccthanking.framework.common.User" %>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>内部通知管理首页</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/ywlz/css/default.css"> </LINK>

<LINK type="text/css" rel="stylesheet" href="/wndjsbg/css/style.css"> </LINK>
<link type="text/css" rel="stylesheet" href="/wndjsbg/css/style-responsive.css">
<link type="text/css" rel="stylesheet" href="/wndjsbg/css/bootstrap.css">
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/nbgl/inform01/WorkInformController";

//页面初始化
$(function() {
    init();
	//已失效按钮 
	$("#btnQuery").click(function() {
		$(window).manhuaDialog({"title":"失效通知","type":"text","content":"${pageContext.request.contextPath }/jsp/business/nbgl/inform-invalid.jsp","modal":"2"});
	});
	
	//添加通知按钮
	$("#btnAdd").click(function(){
		$(window).manhuaDialog({"title":"添加通知","type":"text","content":"${pageContext.request.contextPath }/jsp/business/nbgl/inform-add.jsp","modal":"2"});
	});
	
	
});

function init(){
    //生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?queryValid",data,DT1);
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
	showHtml = "<a href='javascript:rowView1(this);' title='操作'><i class='icon-file'></i></a></i>";
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
	     var nbId = tempJson.NEIBU_TONGZHI_UID;
	     $(window).manhuaDialog({"title":"查看通知","type":"text","content":"${pageContext.request.contextPath }/jsp/business/nbgl/inform-content.jsp?nbId="+nbId,"modal":"2"});  
}
/**
  时间 显示 格式 设置 
 **/

function operationFormat(obj){
       var sometime = obj.SHENGXIAO_DATE;
		var re = /[\u4000-\uFFFF]/g;
	    var periods = sometime.replace(re,'');
	    var str = periods.substr(0,10);
	    var str1 = periods.substr(11,16);
	    var somestr = str+" ";
	    return somestr+str1;

}

function operationStatus(obj){
        var sometime = obj.STATUS;
		var re = /^[0-9]*$/g;
		var periods;
		if(sometime == 1){
		   periods = sometime.replace(re,'有效');
		}else{
		   periods = sometime.replace(re,'无效');
		}
	    return periods;

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
							      当前有效通知
						      	<span class="pull-right">
						      		<button id="btnQuery" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">已失效通知</button>
						      		<button id="btnAdd" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">添加通知</button>	
						      	</span>
			                </h4>
	                        </th>
	                    </tr>
	              	<tbody></tbody>
                 </table>
                </div>
	            <table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single" pageNum="10" >
	                <thead>
	                	<tr>
	                		<th rowspan="2" fieldname="NEIBU_TONGZHI_UID" width="4%" tdalign="center" colindex=0 CustomFunction="doRandering" noprint="true">操作</th>
	                		<th fieldname="TONGZHI_BIAOTI" colindex=2 tdalign="center" width="20%" >&nbsp;标题&nbsp;</th>
							<th fieldname="TONGZHI_NEIRONG" colindex=3 tdalign="center" width="20%" >&nbsp;内容&nbsp;</th>
							<th fieldname="STATUS" colindex=4 tdalign="center"  width="10%" CustomFunction="operationStatus">&nbsp;状态&nbsp;</th>
							<th fieldname="SHENGXIAO_DATE" colindex=5 tdalign="center"  width="20%" CustomFunction="operationFormat">&nbsp;生效时间&nbsp;</th>
	                	</tr>
	                </thead>
	              <tbody></tbody>
	           </table>
	 	</div>
	</div>     
</div>
<div align="center">
	<FORM name="frmPost" method="post" style="display: none" target="_blank">
		<!--系统保留定义区域-->
		<input type="hidden" name="queryXML"/> 
		<input type="hidden" name="txtXML"/>
		<input type="hidden" name="txtFilter" order="asc" fieldname="NEIBU_TONGZHI_UID" id="txtFilter"/>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>