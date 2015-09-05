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
			<h3 id="dlixiangModalLabel" class="widget-title">保养维修记录</h3>
			<div class="widget-toolbar">
				<a href="#" data-action="close" data-dismiss="modal"> <i
					class="ace-icon fa fa-times"></i> </a>
			</div>
		</div>
		<div class="modal-body" id="contentdivid" style="padding: 1px;">
<div class="widget-box">
													<div class="widget-header widget-header-flat">
														<h4 class="widget-title smaller"> 设备保养、维护  
														</h4>
														<div class="widget-toolbar">
															
															
														</div>
													</div>
													<div class="widget-body">
														<div class="widget-main">
															<form class="form-horizontal" id="sample-form">
														<!-- #section:elements.form.input-state -->
														<div class="form-group"  style="margin: 0;line-height: 20px;font-size: 16px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">工程名称</label>
															<div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME"  style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">产权编号</label>
                                                            <div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME"  style="width: 90%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                             <a title="产权编号" href="javascript:;" style="width: 10%" onclick="chanQuan()" ><i  class="#ace-icon fa fa-list-ol bigger-110"></i></a> 
                                                            </div>
														</div>
														<div style="margin: 2px;"></div>
														<div class="form-group"  style="margin: 0;line-height: 20px;font-size: 16px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">设备名称</label>
															<div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME"  style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">设备型号</label>
                                                            <div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME"  style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
														</div>
														<div style="margin: 2px;"></div>
														<div class="form-group"  style="margin: 0;line-height: 15px;font-size: 14px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">维保单位</label>
                                                            <div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME"  style="width: 90%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                             <a title="维保单位" href="javascript:;" style="width: 10%" onclick="weiBao()" ><i  class="#ace-icon fa fa-list-ol bigger-110"></i></a> 
                                                            </div>
                                                            <label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">检测报告编号</label>
															<div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME"  style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
														</div>
														<div style="margin: 2px;"></div>
														<div class="form-group"  style="margin: 0;line-height: 20px;font-size: 16px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">操作人</label>
															<div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME"  style="width: 90%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                             <a title="操作人" href="javascript:;" style="width: 10%" onclick="caozZuoren()" ><i  class="#ace-icon fa fa-list-ol bigger-110"></i></a> 
                                                            </div>	
                                                            <label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">操作证号</label>
															<div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME"  style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>	
                                                        </div>
                                                       <div style="margin: 2px;"></div>
														<div class="form-group"  style="margin: 0;line-height: 20px;font-size: 16px;" >
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
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">保养日期</label>
															<div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME"  style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>	
                                                        </div>
                                                         <div style="margin: 2px;"></div>
														<div class="form-group"  style="margin: 0;line-height: 15px;font-size: 14px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">设备保养记录</label>
                                                            <div class="col-xs-12 col-sm-8">
                                                            <textarea type="text" rows="3" cols="120" id="JIANSHE_TYPE"  name = "JIANSHE_TYPE" fieldname="JIANSHE_TYPE"  class="col-xs-10 col-sm-10"  ></textarea>
                                                            </div>
                                                        </div>
                                                         <div style="margin: 2px;"></div>
														<div class="form-group"  style="margin: 0;line-height: 15px;font-size: 14px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">设备维修记录</label>
                                                            <div class="col-xs-12 col-sm-8">
                                                            <textarea type="text" rows="3" cols="120" id="JIANSHE_TYPE"  name = "JIANSHE_TYPE" fieldname="JIANSHE_TYPE"  class="col-xs-10 col-sm-10"  ></textarea>
                                                            </div>
                                                        </div>
                                                         <div style="margin: 2px;"></div>
														<div class="form-group"  style="margin: 0;line-height: 15px;font-size: 14px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">备注</label>
                                                            <div class="col-xs-12 col-sm-8">
                                                            <textarea type="text" rows="3" cols="120" id="JIANSHE_TYPE"  name = "JIANSHE_TYPE" fieldname="JIANSHE_TYPE"  class="col-xs-10 col-sm-10"  ></textarea>
                                                            </div>
                                                        </div>
													</form>
														</div>
													</div>
												</div>
												
												<div class="widget-box">
													<div class="widget-header widget-header-flat">
														<h4 class="widget-title smaller">安装单位及转场保养单位 
														</h4>
														
													</div>
													<div class="widget-body">
														<div class="widget-main">
															<form class="form-horizontal" id="sample-form">
														<!-- #section:elements.form.input-state -->
														<div class="form-group"  style="margin: 0;line-height: 15px;font-size: 14px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">转场保养单位</label>
                                                            <div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME" style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
                                                            <label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">无锡市信用手册编号</label>
                                                            <div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME" style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
														</div>
														
														<div style="margin: 1px;"></div>
														<div class="form-group"  style="margin: 0;line-height: 15px;font-size: 14px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">设备安装单位</label>
                                                            <div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME" style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
                                                            <label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">资质证书编号</label>
                                                            <div class="col-xs-12 col-sm-4">
                                                             <input id="USER_NAME" style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
														</div>
														<div style="margin: 2px;"></div>
														<div class="form-group"  style="margin: 0;line-height: 15px;font-size: 14px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">生产安全许可证编号</label>
                                                            <div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME" style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
                                                            <label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">设备拟安装日期从</label>
                                                            <div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME" style="width: 40%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                           	 至
                                                           	 <input id="USER_NAME" style="width: 40%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
														</div>
														<div style="margin: 2px;"></div>
														<div class="form-group"  style="margin: 0;line-height: 15px;font-size: 14px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">安装自检日期</label>
                                                            <div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME" style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
                                                            <label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">安装验收日期</label>
                                                            <div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME" style="width: 100%" type="text"  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
														</div>
													</form>
														</div>
													</div>
												</div>
												
												<div class="widget-box">
													<div class="widget-header widget-header-flat">
														<h4 class="widget-title smaller">项目信息 
														</h4>
													</div>
													<div class="widget-body">
														<div class="widget-main">
															<form class="form-horizontal" id="sample-form">
														<!-- #section:elements.form.input-state -->
														<div class="form-group"  style="margin: 0;line-height: 15px;font-size: 14px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">工程名称</label>
                                                            <div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME" style="width: 100%" type="text" readonly  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
                                                            <label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">工程地点</label>
                                                            <div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME" style="width: 100%" type="text" readonly  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
														</div>
														
														<div style="margin: 1px;"></div>
														<div class="form-group"  style="margin: 0;line-height: 15px;font-size: 14px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">工程施工单位</label>
                                                            <div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME" style="width: 100%" type="text" readonly fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
                                                            <label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">使用单位项目经理</label>
                                                            <div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME" style="width: 100%" type="text" readonly fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
														</div>
																	
													</form>
														</div>
													</div>
												</div>
												<div class="widget-box">
													<div class="widget-header widget-header-flat">
														<h4 class="widget-title smaller">安装人员
														</h4>
													</div>
													<div class="widget-body">
														<div class="widget-main">
															<form class="form-horizontal" id="sample-form">
														<!-- #section:elements.form.input-state -->
														<div class="form-group"  style="margin: 0;line-height: 15px;font-size: 14px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">安装现场专业技术人员</label>
                                                            <div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME" style="width: 100%" type="text" readonly  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
                                                            <label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-2 control-label no-padding-right">安装现场专职安全员</label>
                                                            <div class="col-xs-12 col-sm-4">
                                                            <input id="USER_NAME" style="width: 100%" type="text" readonly  fieldname="USER_NAME" name="USER_NAME">
                                                            </div>
														</div>
														<table id="jfgl-table"></table>
														<div id="jfgl-pager"></div>
														<script type="text/javascript">
																var $path_base = "/";//this will be used in gritter alerts containing images
														</script>		
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
	
	jQuery(function($) {
		setStyle($('form')); //字典
		$('[id=ZHIZHAO_VALID]').each(function() { //ace 时间
			var mydate = this;
			if (mydate.type !== 'date') {//if browser doesn't support "date" input
				$(mydate).datepicker({
					//options
					autoclose : true,
					todayHighlight : true,
					language : 'zh-CN',

				})
			}
		});

	});
	function caozZuoren(){
		
		window.open("jsp/gdzxt/jxsbgl/bywxjl-page-caozuoren.jsp","操作人选择","height=600, width=800");
	}	
	function chanQuan(){
		
		window.open("jsp/gdzxt/jxsbgl/bywxjl-page-chanquan.jsp","产权编号选择","height=600, width=800");
	}
	function weiBao(){
		
		window.open("jsp/gdzxt/jxsbgl/bywxjl-page-weibaodanwei.jsp","维保单位选择","height=600, width=800");
	}
</script>