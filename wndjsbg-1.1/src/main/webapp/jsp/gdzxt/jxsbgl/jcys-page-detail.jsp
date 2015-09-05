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
			<h3 id="dlixiangModalLabel" class="widget-title">起重设备安装过程管理   </h3>
			<div class="widget-toolbar">
				<a href="#" data-action="close" data-dismiss="modal"> <i
					class="ace-icon fa fa-times"></i> </a>
			</div>
		</div>
		<div class="modal-body" id="contentdivid" style="padding: 1px;">
							<div class="widget-box">
													
													<div class="widget-body">
														<div class="widget-main">
															<form class="form-horizontal" id="sample-form">
														<!-- #section:elements.form.input-state -->
														<div class="form-group"  style="margin: 0;line-height: 20px;font-size: 16px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">设备名称</label>
															<div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME"  style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">产权编号</label>
                                                            <div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME"  style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
														</div>
														<div style="margin: 2px;"></div>
														<div class="form-group"  style="margin: 0;line-height: 20px;font-size: 16px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">检测单位</label>
															<div class="col-xs-12 col-sm-8">
                                                            <input id="USER_NAME"  style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
														</div>
														<div style="margin: 2px;"></div>
														<div class="form-group"  style="margin: 0;line-height: 20px;font-size: 16px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">检测报告编号</label>
															<div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME"  style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">检测日期</label>
                                                            <div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME"  style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
														</div>
														<div style="margin: 2px;"></div>
														<div class="form-group"  style="margin: 0;line-height: 15px;font-size: 14px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">报告签发日期</label>
                                                            <div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME"  style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
                                                            <label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">检测报告有效期</label>
															<div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME"  style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
														</div>
														<div style="margin: 2px;"></div>
														<div class="form-group"  style="margin: 0;line-height: 20px;font-size: 16px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">使用证办理结束日期</label>
															<div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME"  style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>	
                                                        </div>
                                                        <div style="margin: 2px;"></div>
                                                        <div class="form-group" >
															<label style="color: #667E99;" for="inputWarning"  class="col-xs-12 col-sm-2 control-label no-padding-right" >
															备注
															</label>
															<div class="col-sm-10">
																<textarea type="text" rows="3" cols="120" id="JIANSHE_TYPE"  name = "JIANSHE_TYPE" fieldname="JIANSHE_TYPE"  class="col-xs-10 col-sm-10"  ></textarea>
															</div>
														</div>
                                                        <div style="margin: 2px;"></div>
                                                        <div class="form-group" >
															<label style="color: #667E99;" for="inputWarning"  class="col-xs-12 col-sm-2 control-label no-padding-right">
															监理审核意见
															</label>
															<div class="col-sm-10">
																<textarea type="text" rows="3" cols="120" id="JIANSHE_TYPE"  name = "JIANSHE_TYPE" fieldname="JIANSHE_TYPE"  class="col-xs-10 col-sm-10"  ></textarea>
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
	
	function baoYang(){
		
		window.open("jsp/gdzxt/jxsbgl/sbzl-page-azgz-baoyang.jsp","转场保养单位选择","height=600, width=800");
	}	
	function anZhuang(){
		
		window.open("jsp/gdzxt/jxsbgl/sbzl-page-azgz-anzhuang.jsp","设备安装单位选择","height=600, width=800");
	}
</script>