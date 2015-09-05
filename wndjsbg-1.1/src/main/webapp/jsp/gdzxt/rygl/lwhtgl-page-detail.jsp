<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%
	long ll = System.currentTimeMillis();
	
%>

<div class="modal-dialog width-65 height-aoto">
	<div class="modal-content">
		<div class="widget-header widget-header-large">
			<h3 id="dlixiangModalLabel" class="widget-title">劳务合同信息</h3>
			<div class="widget-toolbar">
				<a href="#" data-action="close" data-dismiss="modal"> <i
					class="ace-icon fa fa-times"></i> </a>
			</div>
		</div>
		<div class="modal-body" id="contentdivid" style="padding: 1px;">
<div class="widget-box">
													<div class="widget-header widget-header-flat">
														<h4 class="widget-title smaller">合同签订方 
														</h4>
														
													</div>
													<div class="widget-body">
														<div class="widget-main">
															<form class="form-horizontal" id="sample-form">
														<!-- #section:elements.form.input-state -->
														<div class="form-group"  style="margin: 0;line-height: 20px;font-size: 16px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">甲方:</label>
															<div class="col-xs-12 col-sm-4">
                                                           		 <input id="USER_NAME" readonly="readonly" style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">法人代表:</label>
                                                            <div class="col-xs-12 col-sm-4">
                                                            	 <input id="USER_NAME"  style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME" readonly>
                                                            </div>
														</div>
														<div style="margin: 2px;"></div>
														<div class="form-group"  style="margin: 0;line-height: 20px;font-size: 16px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">资质证书编号:</label>
															<div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME" readonly="readonly" style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
														</div>
														<div style="margin: 2px;"></div>
														<div class="form-group"  style="margin: 0;line-height: 20px;font-size: 16px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">注册地址:</label>
															<div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME"  style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME" readonly>
                                                            </div>
                                                            <label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">联系电话:</label>
															<div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME"  style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME" readonly>
                                                            </div>
														</div>
														<div style="margin: 2px;"></div>
														<div class="form-group"  style="margin: 0;line-height: 20px;font-size: 16px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">办公地址:</label>
															<div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME"  style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME" readonly>
                                                            </div>
                                                            <label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">联系电话:</label>
															<div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME"  style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME" readonly>
                                                            </div>
														</div>
														<div style="margin: 2px;"></div>
														<div class="form-group"  style="margin: 0;line-height: 15px;font-size: 14px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">乙方:</label>
                                                            <div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME" readonly="readonly" style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
                                                            <label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">身份证号:</label>
															<div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME" readonly="readonly" style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
														</div>
														
														<div style="margin: 2px;"></div>
														<div class="form-group"  style="margin: 0;line-height: 20px;font-size: 16px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">身份证住址:</label>
															<div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME" readonly="readonly" style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
                                                            <label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">联系电话:</label>
															<div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME" readonly="readonly" style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
														</div>
														<div style="margin: 2px;"></div>
														<div class="form-group"  style="margin: 0;line-height: 20px;font-size: 16px;" >
															 <label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">家庭住址:</label>
															 <div class="col-xs-12 col-sm-4">
                                                             <input id="USER_NAME" readonly="readonly" style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                             </div>
														</div>
													</form>
														</div>
													</div>
												</div>
												
												<div class="widget-box">
													<div class="widget-header widget-header-flat">
														<h4 class="widget-title smaller">合同基本内容 
														</h4>
													</div>
													<div class="widget-body">
														<div class="widget-main">
															<form class="form-horizontal" id="sample-form">
														<!-- #section:elements.form.input-state -->
														<div class="form-group"  style="margin: 0;line-height: 15px;font-size: 14px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">合同名称:</label>
                                                            <div class="col-xs-12 col-sm-8">
                                                            <input id="USER_NAME" style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME" readonly>
                                                            </div>
														</div>
														
														<div style="margin: 2px;"></div>
														<div class="form-group"  style="margin: 0;line-height: 15px;font-size: 14px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">合同编码:</label>
                                                            <div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME" style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME" readonly>
                                                            </div>
														</div>
														
														<div style="margin: 2px;"></div>
														<div class="form-group"  style="margin: 0;line-height: 15px;font-size: 14px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">担任岗位:</label>
                                                            <div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME" style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME" readonly>
															</select>
                                                            </div>
                                                            <label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">担任工种:</label>
                                                            <div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME" style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME" readonly>	
															</select>
                                                            </div>
														</div>
														<div style="margin: 2px;"></div>
														<div class="form-group"  style="margin: 0;line-height: 15px;font-size: 14px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">工作地点:</label>
                                                            <div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME" style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME" readonly>
                                                            </div>
														</div>
														<div style="margin: 2px;"></div>
														<div class="form-group"  style="margin: 0;line-height: 15px;font-size: 14px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">工作内容:</label>
                                                            <div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME" style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME" readonly> 
                                                            </div>
														</div>
														<div style="margin: 2px;"></div>
														<div class="form-group"  style="margin: 0;line-height: 15px;font-size: 14px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">月工资:</label>
                                                            <div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME" style="width: 60%" type="text"  fieldname="USER_NAME" name="USER_NAME" readonly>
                                                            </div>
														</div>
														<div style="margin: 2px;"></div>
														<div class="form-group"  style="margin: 0;line-height: 15px;font-size: 14px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">每月几日支付:</label>
                                                            <div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME" style="width: 60%" type="text"  fieldname="USER_NAME" name="USER_NAME" readonly>
                                                            </div>
														</div>
														<div style="margin: 2px;"></div>
														<div class="form-group"  style="margin: 0;line-height: 15px;font-size: 14px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">开始日期:</label>
                                                            <div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME" style="width: 60%" type="text"  fieldname="USER_NAME" name="USER_NAME" readonly>
                                                            </div>
                                                            <label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">结束日期:</label>
                                                            <div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME" style="width: 60%" type="text"  fieldname="USER_NAME" name="USER_NAME" readonly>
                                                            </div>
														</div>
														
														<div style="margin: 2px;"></div>
														<div class="form-group"  style="margin: 0;line-height: 15px;font-size: 14px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">经办人:</label>
                                                            <div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME" style="width: 60%" type="text"  fieldname="USER_NAME" name="USER_NAME" readonly>
                                                            </div>
														</div>				
													</form>
														</div>
													</div>
												</div>
												
												<div class="widget-box">
													<div class="widget-header widget-header-flat">
														<h4 class="widget-title smaller">合同条款 
														</h4>
													</div>
													<div class="widget-body">
														<div class="widget-main">
															<form class="form-horizontal" id="sample-form">
														<!-- #section:elements.form.input-state -->
														<div class="form-group"  style="margin: 0;line-height: 15px;font-size: 14px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-1 control-label no-padding-right">条款1:</label>
                                                            <div class="col-xs-12 col-sm-10">
                                                             <textarea rows="4" cols="" style="width: 80%"></textarea>
                                                            </div>
														</div>
														
														<div style="margin: 1px;"></div>
														<div class="form-group"  style="margin: 0;line-height: 15px;font-size: 14px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-1 control-label no-padding-right">条款2:</label>
                                                            <div class="col-xs-12 col-sm-10">
                                                             <textarea rows="4" cols="" style="width: 80%"></textarea>
                                                            </div>
														</div>
														
														<div style="margin: 1px;"></div>
														<div class="form-group"  style="margin: 0;line-height: 15px;font-size: 14px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-1 control-label no-padding-right">条款3:</label>
                                                            <div class="col-xs-12 col-sm-10">
                                                             <textarea rows="4" cols="" style="width: 80%"></textarea>
                                                            </div>
														</div>
														
														<div style="margin: 1px;"></div>
														<div class="form-group"  style="margin: 0;line-height: 15px;font-size: 14px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-1 control-label no-padding-right">备注:</label>
                                                            <div class="col-xs-12 col-sm-10">
                                                             <textarea rows="4" cols="" style="width: 80%"></textarea>
                                                            </div>
														</div>
														
																	
													</form>
														</div>
													</div>
												</div>

		</div>

		<div class="modal-footer">
			<button class="btn btn-primary btn-sm" id="update" onclick="doUpdate()">修改</button>
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


	});
</script>