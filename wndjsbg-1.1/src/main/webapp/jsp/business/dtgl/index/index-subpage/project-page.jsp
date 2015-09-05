<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>项目信息展示</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<%
	String condition = request.getParameter("condition");	
%>

<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/gongcheng/projectsGongchengController";
var condition="<%=condition%>";
//页面初始化
$(function() {
	
	init();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {
		var AJZ_UID = $("#AJZ").val();
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"?query3&condition="+condition+"&AJZ_UID="+AJZ_UID,data,DT1);
		
	});
	
	
	//按钮绑定事件(清空)
    $("#btnClear").click(function() {
        $("#queryForm").clearFormResult();
        $("#btnQuery").click();
    });
	
});

//页面默认参数
function init(){
	if(condition=="yqmtgwfgxm"){
		$("#topTitle").html("已全面停工未复工项目信息查询");
	}else if(condition=="yjbtgwfgxm"){
		$("#topTitle").html("已局部停工未复工项目信息查询");
	}
	
	queryAjDept();
	
	//生成json串 
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?query3&condition="+condition,data,DT1);				
}

function queryAjDept(){
	$.ajax({		
		url : controllername+"?queryAjDept",
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var res = dealSpecialCharactor(response.msg);
			var resultmsgobj = convertJson.string2json1(res);
			var datslist=resultmsgobj.response.data;
			var selectObj=document.getElementById('AJZ');
			$.each(datslist, function(){    
				selectObj.options.add(new Option(this.ORG_NAME,this.ORGANIZE_UID));
			});
		}	
	});
}

function caozuoFun(obj){
	var showHtml="<a href='javascript:void(0)' title='查看' onclick=\"rowView(this,'"+obj.GONGCHENG_UID+"','"+obj.GONGCHENG_NAME+"','"+obj.SGDW+"')\">"+obj.SGDW+"</a>";		
	return showHtml;
}

//详细信息
function rowView(obj,id,name,dw){
	$("#gdzxt_gcid").val(id);
    var f =document.getElementById('gdzxtformid');
    var url='${pageContext.request.contextPath }/pagegdzxt/framework/portal/gdzxtframe';
	f.action=url;
	f.target="_blank"; 
	f.submit();
}

function showinfo(obj){
	var arr = new Array();
	arr = obj.GC_INFO.split("|");	
	var showHtml="<span title=\""+arr[1]+"\">"+arr[0]+"</span>";		
	return showHtml;
}

</script>
</head>
<body>
<form id="gdzxtformid" method="post" >
		<input type="hidden" name="gdzxt_gcid" id="gdzxt_gcid" >
</form>
<app:dialogs/>
<div id="menuiframe"></div>
<div class="container-fluid" class="main-container">
	<div class="top-h4">
	<h4 id="topTitle" class="topTitle"></h4>
	</div>
	<div class="row-fluid">
		<div class="B-small-from-table-autoConcise">

			<form method="post" id="queryForm">
				<table class="B-table" width="100%">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr style="display: none;">
						<td class="right-border bottom-border"></td>
						<td class="right-border bottom-border">
							<input type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>							
							<%--<input type="hidden" name="ZG_TZD_UID" order="desc" fieldname="t.ZG_TZD_UID" id="ZG_TZD_UID"/>--%>
						</td>
					</tr>
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr>
						<td width="11%" class="right-border bottom-border" style="padding: 5px;">
							<select class="span12" id="AJZ" name="AJZ">
							<option value="">-请选择安监组-</option>														
							</select>
						</td>
						<th width="5%" class="right-border bottom-border text-right">项目名称</th>
						<td width="12%">
							 <input class="span12" type="text" id="PROJECTS_NAME" fieldname="PROJECTS_NAME" check-type="maxlength" maxlength="100" operation="like" >
						</td>					
						<th width="5%" class="right-border bottom-border text-right">建设单位</th>
						<td width="12%">
							 <input class="span12" type="text" id="JSDW" fieldname="JSDW" check-type="maxlength" maxlength="100" operation="like" >
						</td>					
						<th width="5%" class="right-border bottom-border text-right">施工单位</th>
						<td width="12%">
							 <input class="span12" type="text" id="SGDW" fieldname="SGDW" check-type="maxlength" maxlength="100" operation="like" >
						</td>						
			            <td class="text-right bottom-border" width="15%">
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
							<th fieldname="PROJECTS_NAME" colindex=6 tdalign="center" maxlength="100" style="width:10px">&nbsp;项目名称&nbsp;</th>	
							<th fieldname="JSDW" colindex=6 tdalign="center" maxlength="100" style="width:10px">&nbsp;建设单位&nbsp;</th>												
							<th fieldname="SGDW" colindex=6 tdalign="center" maxlength="100" style="width:10px" CustomFunction="caozuoFun">&nbsp;施工单位&nbsp;</th>
							<th fieldname="JLDW" colindex=6 tdalign="center" maxlength="100" style="width:10px">&nbsp;监理单位&nbsp;</th>								               
							<th fieldname="AJY" colindex=6 tdalign="center" maxlength="100" style="width:10px">&nbsp;安监&nbsp;</th>								               
							<th fieldname="ZJY" colindex=6 tdalign="center" maxlength="100" style="width:10px">&nbsp;质监&nbsp;</th>								               
							<th fieldname="ZTCREATED_DATE" colindex=6 tdalign="center" style="width:10px">&nbsp;停工日期&nbsp;</th>
							<th fieldname="GC_INFO" colindex=6 tdalign="center" style="width:10px" CustomFunction="showinfo">&nbsp;更新时间&nbsp;</th>
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