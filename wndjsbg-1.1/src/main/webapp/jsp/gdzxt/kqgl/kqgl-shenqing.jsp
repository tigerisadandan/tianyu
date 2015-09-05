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
					
				<form method="post" role="form" class="form-horizontal"  id="executeFrm"  >
     
			      <table class="B-table" width="100%">
			     		 <div class="form-group" >
				     		<h4 class="lighter blue widget-title">
								<span class="col-xs-6">终止考勤 </span>
							</h4>
						</div>
						<div class="form-group" >
							<label class="col-sm-2 control-label no-padding-right" for="">
							终止考勤日期:
							</label>
							<div class="col-sm-2 inline">
			 					<div class="input-daterange">
								<span class="input-icon input-icon-right">
								
								<input datatype="*"  nullmsg="请输入预计投产日期！" errormsg="" placeholder="请输入预计投产日期！" type="text" data-date-format="yyyy-mm-dd" fieldname="TC_DATE"  style="height:30px" name="TC_DATE" id="TC_DATE"  readonly />
									 <i class="ace-icon fa fa-calendar"></i>
									  <div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
									 </span>
								</div>	
							</div>
						</div>
						<div class="form-group" >
							<label class="col-sm-2 control-label no-padding-right" for="">
							考终止勤原因:
							</label>
							<div class="col-sm-10">
								<textarea type="text" rows="3" cols="120" id="JIANSHE_TYPE"  name = "JIANSHE_TYPE" fieldname="JIANSHE_TYPE"  class="col-xs-10 col-sm-10"  ></textarea>
							</div>
						</div>
						<div class="form-group" >
							<label class="col-sm-2 control-label no-padding-right" for="">
							监督小组确认意见:
							</label>
							<div class="col-sm-10">
								<textarea type="text" rows="3" cols="120" id="JIANSHE_TYPE"  name = "JIANSHE_TYPE" fieldname="JIANSHE_TYPE"  class="col-xs-10 col-sm-10"  ></textarea>
							</div>
						</div>
						<div class="form-group" >
							<label class="col-sm-2 control-label no-padding-right" for="">
							一级审核意见:
							</label>
							<div class="col-sm-10">
								<textarea type="text" rows="3" cols="120" id="JIANSHE_TYPE"  name = "JIANSHE_TYPE" fieldname="JIANSHE_TYPE"  class="col-xs-10 col-sm-10"  ></textarea>
							</div>
						</div>
						<div class="form-group" >
							<label class="col-sm-2 control-label no-padding-right" for="">
							二级审核意见:
							</label>
							<div class="col-sm-10">
								<textarea type="text" rows="3" cols="120" id="JIANSHE_TYPE"  name = "JIANSHE_TYPE" fieldname="JIANSHE_TYPE"  class="col-xs-10 col-sm-10"  ></textarea>
							</div>
						</div>
						<div class="form-group" >
							<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
							创建时间:
							</label>
							<div class="col-sm-3">
								<lable id="SGDW" fieldname="SGDW" name="SGDW" ></lable>
							</div>
						</div>
						<div class="form-group" >
							<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
							小组确认人:
							</label>
							<div class="col-sm-3">
								<lable id="SGDW" fieldname="SGDW" name="SGDW" ></lable>
							</div>
							<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
							小组确认时间:
							</label>
							<div class="col-sm-3">
								<lable id="SGDW_XYFZ" fieldname="SGDW_XYFZ" name="SGDW_XYFZ" ></lable>
							</div>
						</div>
						<div class="form-group" >
							<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
							审核人1:
							</label>
							<div class="col-sm-3">
								<lable id="SGDW" fieldname="SGDW" name="SGDW" ></lable>
							</div>
							<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
							审核时间:
							</label>
							<div class="col-sm-3">
								<lable id="SGDW_XYFZ" fieldname="SGDW_XYFZ" name="SGDW_XYFZ" ></lable>
							</div>
						</div>
						<div class="form-group" >
							<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
							审核人2:
							</label>
							<div class="col-sm-3">
								<lable id="SGDW" fieldname="SGDW" name="SGDW" ></lable>
							</div>
							<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
							审核时间:
							</label>
							<div class="col-sm-3">
								<lable id="SGDW_XYFZ" fieldname="SGDW_XYFZ" name="SGDW_XYFZ" ></lable>
							</div>
						</div>
						<div class="form-group" >
							<label class="col-sm-2 control-label no-padding-right" for="">
							</label>
							<div class="col-sm-10">
								<font style="color:blue;">保存后点击打印按钮，打印备案表并提交安监站备案</font>
							</div>
						</div>
					</table>
		 <button class="btn btn-success btn-sm pull-right" id="btnPrint" data-dismiss="modal" aria-hidden="true">打印</button>
    	<button class="btn btn-danger btn-sm pull-right" id="btnClose" data-dismiss="modal" aria-hidden="true">关闭</button>
	
      </form>
   			</div>
			</div>
		</div>
	</div>
	<div id="jsProject-input" class="modal"></div>

	<div id="ywlzdata-input" class="modal"></div>
	<input type="hidden" id="pagex" />
	<input type="hidden" id="pagey" />


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