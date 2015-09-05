<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%
	long ll = System.currentTimeMillis();
	String stuats = request.getParameter("STATUS");
%>

<!-- 
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/plugins/validform/css/form_validate.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/plugins/validform/css/form_validate_f.css" />

<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/fuelux/fuelux.wizard.min.js">
</script>
	<script src="${pageContext.request.contextPath }/js/project/Projectsunits22.js"></script>
<!-- 新增 修改 Modal  -->

<!--<div id="myModal" class="modal  fade" tabindex="-1" role="dialog" 
	aria-labelledby="myModalLabel" aria-hidden="true">   -->

<div class="modal-dialog width-65 height-aoto">
	<div class="modal-content">
		<div class="widget-header widget-header-large">
			<h3 id="dlixiangModalLabel" class="widget-title"> 施工升降机防坠落试验记录 </h3>
			<div class="widget-toolbar">
				<a href="#" data-action="close" data-dismiss="modal"> <i
					class="ace-icon fa fa-times"></i> </a>
			</div>
		</div>
		<div class="modal-body" id="contentdivid" style="padding: 1px;">
<div class="widget-box">
													<div class="widget-header widget-header-flat">
														<h4 class="widget-title smaller">施工升降机防坠落试验记录 
														</h4>
														<div class="widget-toolbar">
															
															
														</div>
													</div>
													<div class="widget-body">
														<div class="widget-main">
															<form class="form-horizontal" id="sample-form">
														<!-- #section:elements.form.input-state -->
														<div class="form-group"  style="margin: 0;line-height: 20px;font-size: 16px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">施工升降机</label>
															<div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME"  style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
														</div>
														<div style="margin: 2px;"></div>
														<div class="form-group"  style="margin: 0;line-height: 20px;font-size: 16px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">防坠器编号</label>
															<div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME"  style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">试验单位</label>
                                                            <div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME"  style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
														</div>
														<div style="margin: 2px;"></div>
														<div class="form-group"  style="margin: 0;line-height: 15px;font-size: 14px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">操作人</label>
                                                            <div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME"  style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
                                                            <label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">操作证号</label>
															<div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME"  style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
														</div>
														<div style="margin: 2px;"></div>
														<div class="form-group"  style="margin: 0;line-height: 15px;font-size: 14px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">操作人</label>
                                                            <div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME"  style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
                                                            <label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">操作证号</label>
															<div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME"  style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
														</div>
														<div style="margin: 2px;"></div>
														<div class="form-group"  style="margin: 0;line-height: 15px;font-size: 14px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">操作人</label>
                                                            <div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME"  style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
                                                            <label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">操作证号</label>
															<div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME"  style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
														</div>
														<div style="margin: 2px;"></div>
														<div class="form-group"  style="margin: 0;line-height: 15px;font-size: 14px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">操作人</label>
                                                            <div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME"  style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
                                                            <label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">操作证号</label>
															<div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME"  style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
														</div>
                                                        <div style="margin: 2px;"></div>
														<div class="form-group"  style="margin: 0;line-height: 20px;font-size: 16px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">试验日期</label>
															<div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME"  style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
                                                        </div>
													</form>
														</div>
													</div>
												</div>
												
												
		</div>

		<div class="modal-footer">
			<button class="btn btn-success btn-sm" id="save">保存</button>
			<button class="btn btn-danger btn-sm pull-right" data-dismiss="modal"
				aria-hidden="true">关闭</button>
		</div>
	</div>
</div>
<!-- </div> -->

<script type="text/javascript">
var scripts = [null];

	ace.load_ajax_scripts(scripts, function() {
	//	var gridwidth=$("#contentdivid").width();
	//	JqgridManage.initJqgrid(content_grid_table,queryForm,gridwidth);

		//setStyle(xmxxtxformid);
		//DatePicker.datepickerid("#PF_DATE");
	});
	

</script>