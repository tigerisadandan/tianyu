<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>

<%@ taglib uri="/tld/base.tld" prefix="app"%>
<!DOCTYPE html>
<html>
	
<head>
	<base href="${ctx_site}/">
	<title><fmt:message key="ui.title"/></title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
 	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="无锡建设环保局-建设单位信用管理系统" />
	<%@ include file="/jsp/framework/common/include.jsp"%>
 	<app:base />
</head>
<body class="no-skin">

		<%--<%@ include file="/decorators/top.jsp"%>
		--%><div class="main-container" id="main-container">
		<div class="main-content">
				<div class="page-content">
					<div class="row">
					<div class="page-header">
						<div class="form-group">
						<div class="col-xs-9">
						<h1>
							设备列表
							<small> 
								<i class="icon-double-angle-right"></i>
							
									 
							</small>
						</h1>
						</div>
						<div  class="col-xs-3">
							<small> 
								<i class="icon-double-angle-right"></i>
							
									 <button class="btn btn-primary" 
									    style="height: 35px;margin-bottom: 2px;">关闭</button>
									<button class="btn btn-primary" 
									     style="height: 35px;margin-bottom: 2px;">选择</button>
									
							</small>
						</div>
						</div>
					</div>
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<form role="form" class="form-horizontal" id="queryForm">
								<div class="form-group">
									
									
								</div>
								<div class="hr hr10 hr-dotted"></div>
							</form>
										
							<table id="grid-table"></table>
							<div id="grid-pager"></div>
							<script type="text/javascript">
									var $path_base = "/";//this will be used in gritter alerts containing images
								</script>

							<!-- PAGE CONTENT ENDS -->
						</div>
					</div>
					</div>
					</div>			
				</div>
			</div>		
		</div>
	<div id="jsProject-input" class="modal"></div>


<link rel="stylesheet" href="${ctx}/plugins/validform/css/form_validate.css" />
<link rel="stylesheet" href="${ctx}/plugins/validform/css/form_validate_f.css" />

<script type="text/javascript" charset="utf-8">

 $(document).ready(function(){
	

 });


	ace.load_ajax_scripts(scripts, function() {
	  //inline scripts related to this page
	
});

	
	//新增按钮
	$('#new').click(function(){
		//$('#new').attr("data-target","#jsProject-input");
		$('#jsProject-input').removeData("bs.modal");
		$("#jsProject-input").empty();
		$('#jsProject-input').modal({
			backdrop: 'static'
			});
		$.get(contextPath+"/jsp/business/project/lixiang-jq-input.jsp", function(data){
			  $("#jsProject-input").html(data);
			  $('#PF_DATE').datepicker({
			   		autoclose: true,
			   		todayHighlight: true,
			   		language:'zh-CN'

			   		}).next().on(ace.click_event, function(){
			   		$(this).prev().focus();
			   		});  
		});
	
	});
	//查询按钮
	$('#search').click(function(){
		var data = combineQuery.getQueryCombineData(queryForm);
		var json = {
			'validateName':$("#validateName").val()
		};
		jQuery("#grid-table").jqGrid('setGridParam', { postData: json }).jqGrid('setGridParam', { 'page': 1 })
		.trigger("reloadGrid");
	});
	
	//清空
	$('#clean').click(function(){
		//$("#PROJECT_NAME").val("");
		//window.location.reload();
		$("#queryForm").clearFormResult()
		$('#grid-table').trigger("reloadGrid");
		//$("#queryForm").clearFormResult(); 
	});
</script>		
</body>
</html>