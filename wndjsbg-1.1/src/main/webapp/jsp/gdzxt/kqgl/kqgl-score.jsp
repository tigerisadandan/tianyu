<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<!DOCTYPE html>
<html>

<head>
<base href="${ctx_site}/">
<title><fmt:message key="ui.title" />
</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="无锡建设环保局-建设单位信用管理系统" />

</head>
<body class="no-skin">
	<%@ include file="/jsp/framework/common/head.jsp"%>
	
	<app:base />
	<div class="main-container" id="main-container">
		<div class="main-content">
			<div class="page-content">
				<div class="row" style="100%">
				<div class="col-xs-12">
					<div class="widget-box">
								<div class="widget-header">
									<h4 class="lighter blue widget-title">
										<span class="col-xs-6">个人信用评分  </span>
										
										<span class="col-xs-6">
											<span class="pull-right inline">
													<button id="btn_close" class="btn btn-primary btn-sm" onclick="window.close()" type="button">关闭</button>
											</span>
										</span>
									</h4>
								</div>
								<div class="widget-body">
									<div class="widget-main" style="padding:0px;">
										<form method="post" role="form" class="form-horizontal"  id="executeFrm"  >
     
									      <table class="B-table" width="100%">
									     		 
												<div class="form-group" >
													<label class="col-sm-2 control-label no-padding-right" for="">
													姓名:
													</label>
													<div class="col-sm-3 inline">
									 					 <input  type="text"  id="GUIHUA_CODE" maxlength="40"  fieldname="GUIHUA_CODE" name = "GUIHUA_CODE"  class="col-xs-10 col-sm-10"  readonly/>
													</div>
													<label class="col-sm-2 control-label no-padding-right" for="">
													身份证:
													</label>
													<div class="col-sm-3 inline">
									 					 <input  type="text"  id="GUIHUA_CODE" maxlength="40"  fieldname="GUIHUA_CODE" name = "GUIHUA_CODE"  class="col-xs-10 col-sm-10"  readonly/>
													</div>
												</div>
												<div class="form-group" >
													<label class="col-sm-2 control-label no-padding-right" for="">
													专业:
													</label>
													<div class="col-sm-3">
														  <input  type="text"  id="GUIHUA_CODE" maxlength="40"  fieldname="GUIHUA_CODE" name = "GUIHUA_CODE"  class="col-xs-10 col-sm-10"  readonly/>
													</div>
													<label class="col-sm-2 control-label no-padding-right" for="">
													资质证书编号:
													</label>
													<div class="col-sm-3">
														<input  type="text"  id="GUIHUA_CODE" maxlength="40"  fieldname="GUIHUA_CODE" name = "GUIHUA_CODE"  class="col-xs-10 col-sm-10"  readonly/>
													</div>
												</div>	
												<div class="form-group" >
													<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
													信用分值:
													</label>
													<div class="col-sm-3">
														<input  type="text"  id="GUIHUA_CODE" maxlength="40"  fieldname="GUIHUA_CODE" name = "GUIHUA_CODE"  class="col-xs-10 col-sm-10"  readonly/>
													</div>
													<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
													当前状态:
													</label>
													<div class="col-sm-3">
														<input  type="text"  id="GUIHUA_CODE" maxlength="40"  fieldname="GUIHUA_CODE" name = "GUIHUA_CODE"  class="col-xs-10 col-sm-10"  readonly/>
													</div>
												</div>
												<div class="form-group" >
													<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
													禁入原因:
													</label>
													<div class="col-sm-8">
														<input  type="text"  id="GUIHUA_CODE" maxlength="40"  fieldname="GUIHUA_CODE" name = "GUIHUA_CODE"  class="col-xs-10 col-sm-10"  readonly/>
													</div>
													
												</div>
												
											</table>
								
						      		</form>
									</div>
								</div>
							</div>
					</div>
   			</div>
   			<%--<div class="row" style="100%">
				<div class="col-xs-12">
		   			<form class="form-inline" role="form" id="queryForm" style="border:solid 1px #ddd;height:50px;line-height:50px;vertical-align:middle;">
							<div class="col-xs-12">
								<div class="form-group col-xs-12">
									<input class="form-control" type="radio" id="ZGGL" name="ZGGL" defaultValue="1" fieldname="ZGGL" operation="=" kind="dic" src="PROJECT" onclick="doQuery()">
									<input class="form-control" type="radio" id="ZGGL" name="ZGGL" defaultValue="1" fieldname="ZGGL" operation="=" kind="dic" src="ZGGL" onclick="doQuery()">
									<input class="form-control" type="radio" id="ZGGL" name="ZGGL" defaultValue="1" fieldname="ZGGL" operation="=" kind="dic" src="RECORD" onclick="doQuery()">
								</div>
							</div>
					</form>
				</div>
   			</div>
   			--%><div class="row" style="100%">
					<div class="col-xs-12">
						<!-- PAGE CONTENT BEGINS -->
						<div class="widget-box">
								<div class="widget-header">
									<h4 class="lighter blue widget-title">
										<span class="col-xs-6">扣分记录 </span>
									</h4>
								</div>
								<div class="widget-body">
									<div class="widget-main" style="padding:0px;">
										<table id="jfgl-table"></table>
										<div id="jfgl-pager"></div>
										<script type="text/javascript">
												var $path_base = "/";//this will be used in gritter alerts containing images
										</script>
									</div>
								</div>
							</div>
						<!-- PAGE CONTENT ENDS -->
					</div>
				</div>
   			
			</div>
		</div>
	</div>
	<div id="jsProject-input" class="modal"></div>

	

	<%@ include file="/jsp/framework/common/basic_scripts.jsp"%>
	<script
		src="${pageContext.request.contextPath }/assets/js/jquery-ui.custom.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/assets/js/jquery.ui.touch-punch.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/assets/sys_resources/plugins/cookie/jquery.cookie.js"></script>
	<script type="text/javascript" charset="utf-8">
		//计算鼠标坐标
	

		//页面初始化
		$(function() {
			//doProQuery();

		});

		$('#new').click(function(){

			$('#jsProject-input').removeData("bs.modal");
			$("#jsProject-input").empty();
			$('#jsProject-input').modal({
				backdrop: 'static'
				});
			$.get(contextPath+"/jsp/gdzxt/kqgl/kqgl-add.jsp", function(data){
				  $("#jsProject-input").html(data);
				 // setStyle($('#xmInfoFrm'));
				 // $('#manyLX').empty();
				 // $("#xmInfoFrm").clearFormResult(); 
				 //$("input:radio[name='PROJECTS_LEVEL']")[1].checked= true;
				 // $("input:radio[name='PROJECTS_XINZHI']")[1].checked= true;
			});
			$('#grid-table').trigger("reloadGrid");
		});
		$('#detail').click(function(){

			$('#jsProject-input').removeData("bs.modal");
			$("#jsProject-input").empty();
			$('#jsProject-input').modal({
				backdrop: 'static'
				});
			$.get(contextPath+"/jsp/gdzxt/kqgl/kqgl-detail.jsp", function(data){
				  $("#jsProject-input").html(data);
				 // setStyle($('#xmInfoFrm'));
				 // $('#manyLX').empty();
				 // $("#xmInfoFrm").clearFormResult(); 
				 //$("input:radio[name='PROJECTS_LEVEL']")[1].checked= true;
				 // $("input:radio[name='PROJECTS_XINZHI']")[1].checked= true;
			});
			$('#grid-table').trigger("reloadGrid");
		});
	

	</script>
</body>
</html>