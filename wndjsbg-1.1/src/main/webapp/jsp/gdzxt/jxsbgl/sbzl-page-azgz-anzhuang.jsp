<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp" %>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<!DOCTYPE html>
<html>
	
<head>
	<base href="${ctx_site}/">
	<title>安装单位选择</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
 	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="无锡建设环保局-建设单位信用管理系统" />
	<%@ include file="/jsp/framework/common/head.jsp"%>
 	<app:base />
</head>
<body class="no-skin">
<div class="main-container" id="main-container">
		<div class="main-content">
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12" id="contentdivid">
							<!-- PAGE CONTENT BEGINS -->
							<form class="form-inline" role="form" id="sbForm" style="border:solid 1px #ddd;height:50px;line-height:50px;vertical-align:middle;">
							<div class="col-xs-10">
							<input type="hidden" value="1" fieldname="SHENHE_JIEGUO" operation="=" logic="and" >
							<input type="hidden" value="1" fieldname="ENABLED" operation="=" logic="and" >
							 <div class="form-group">
										<label>单位名称</label>
							   </div>
							   <div class="form-group">
									<input class="form-control" style="margin-bottom:8px;width: 180px" name="CQBH" fieldname="CQBH" id="CQBH"  operation="like" logic="and" >
								
								</div>
								&nbsp;&nbsp;
								 <div class="form-group">
										<label>代码</label>
							   </div>
							   <div class="form-group">
									<input class="form-control" style="margin-bottom:8px;width: 180px" name="CQDW" fieldname="CQDW" id="CQDW"  operation="like" logic="and" >
								
								</div>
							 
							<button id="search" class="btn btn-link btn-sm" type="button"><i class="#ace-icon glyphicon glyphicon-search  bigger-110"></i>查询</button>
							<button id="clean" class="btn btn-link btn-sm"  type="button"><i class="#ace-icon glyphicon glyphicon-trash  bigger-110"></i>清空</button>
							</div>	
								<div class="col-xs-2 align-right" >
									<button id="savesb" onclick="selSb()" class="btn btn-primary btn-sm" type="button">确定</button>
									<button onclick="javascript:window.close()" class="btn btn-primary btn-sm" type="button">关闭</button>
							</div>
						 </form>
						 <div id="tableY">
							<table  sortname="CREATED_DATE" multiselect=true  rownum="50"
									sortorder="desc" 
									class="auto_startgrid" 
									id="content_grid_table" 
									action="${pageContext.request.contextPath }/jxsb/azCompanyController/query" >
								<tr>
								    <th name="AZ_COMPANY_UID" hidden="">id</th>
									<th formatter="view" name="COMPANY_NAME" width="120"  align=center>安装单位</th>				<!-- formatter="jqgridactions" -->
									<th name="WX_ADDRESS"  width="150" align=center>驻无锡地址</th>
									<th name="YYZZ_CODE" width="100" align=center>营业执照注册号</th>
									<th name="XYSC_CODE" width="100" align=center >信用手册编号</th>
									<th name="PHONE" width="100" align=center >联系电话</th>
								</tr>
							</table>
							</div>
						</div>
					</div>

				</div>
			</div>		
		</div>
	<div id="jsProject-input" class="modal"></div>
<%@ include file="/jsp/framework/common/basic_scripts.jsp"%>
 	<app:base />

<script type="text/javascript" charset="utf-8">


var scripts =[null];// ["jsp/business/project/JsProject.js"];
var type="y";
ace.load_ajax_scripts(scripts, function() {
		var gridwidth=$("#contentdivid").width();
		JqgridManage.initJqgrid(content_grid_table,sbForm,gridwidth);
		$("#cb_content_grid_table").hide();
		//setStyle(xmxxtxformid);
		//DatePicker.datepickerid("#PF_DATE");
	});
//页面初始化
$(function() {
		$('#new').click(function() {
							type = null;// 清下id 要不然修改会 id回村起来 下次业务就成update了
							$(".info").hide();
							// $("#executeFrm").clearFormResult(); 1.爆炸20.6 - 56.4  2.钉刺16.1 - 98.7 3.奥术11.9 4.黑10.9 5.弹幕9.6 6.自动7.2bb自动7。1乌鸦6.5专注5.1bb抓4.2亡灵0.6
							$('#new').attr("data-target","sbsh-input");
							$('#sbsh-input').removeData("bs.modal");
							$("#sbsh-input").empty();
							$('#sbsh-input').modal({
								backdrop: 'static'
							});
							$.get(contextPath+ "/jsp/gdzxt/jxsbgl/sbzl-page-azgz.jsp",function(data) {$("#sbsh-input").html(data);});
						});
						
		$('#search').click(function() {
		if(type=="y"){
			$('#content_grid_table').trigger("reloadGrid");
		}else if(type=="n"){
			$('#content_grid_table2').trigger("reloadGrid");
		}
		});
		$('#clean').click(function(){
			$("#CQBH").val("");
			$("#CQDW").val("");
			document.getElementById("JXSB_TYPE_UID").options[0].selected=true;
			 $("#search").click();  
		});
});

function selSb(){
var id;
var rowData;
id=$("#content_grid_table").jqGrid("getGridParam","selrow");
rowData = $("#content_grid_table").jqGrid('getRowData',id);
var sbid = rowData.AZ_COMPANY_UID;
if(sbid==null){
bootbox.alert("请选择一条记录!");
}else{
 window.opener.queryazqy(sbid);
  window.close();  
}
}
</script>		
</body>
</html>