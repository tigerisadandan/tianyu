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
			<h3 id="dlixiangModalLabel" class="widget-title">人员解锁  </h3>
			<div class="widget-toolbar">
				<a href="#" data-action="close" data-dismiss="modal"> <i
					class="ace-icon fa fa-times"></i> </a>
			</div>
		</div>
		<div class="modal-body" id="contentdivid" style="padding: 1px;">
<div class="widget-box">
													<div class="widget-header widget-header-flat">
														<h4 class="widget-title smaller">申请解锁理由 
														</h4>
													<!-- 	<div class="widget-toolbar"><small class="green">
									                               <button class="btn btn-primary"
									                                  style="height: 35px;margin-bottom: 2px;">关闭</button>
									                               <button class="btn btn-primary" 
									                                  style="height: 35px;margin-bottom: 2px;">申请解锁</button>
																</small>
														</div> -->
													</div>
													<div class="widget-body">
														<div class="widget-main" style="padding: 1px">
															<form class="form-horizontal" id="sample-form">
														<!-- #section:elements.form.input-state -->
														<div class="form-group"  style="margin: 0;line-height: 20px;font-size: 16px;" >
															<label style="color: #667E99;" for="inputWarning" class="col-xs-12 col-sm-1 control-label no-padding-right"><span style="color: red;">*</span>理由:</label>
															<div class="col-xs-12 col-sm-11">
                                                             <textarea rows="6" cols="" style="width: 100%"></textarea>
                                                            </div>
														</div>
														
													</form>
														</div>
													</div>
												</div>
												
												<div class="widget-box" >
													<div class="widget-header widget-header-flat">
														<h4 class="widget-title smaller">
选择人员
														</h4>
													</div>
													<div class="widget-body">
														<div class="widget-main" id="contentdivid" style="padding: 1px">
															 <form role="form" class="form-inline" id="queryForm2" >
								
									
							</form>
											
							<table  sortname="PUBLISH_TIME" multiselect=false  rownum="50"
									sortorder="desc" jqgridheight=aoto
									class="auto_startgrid" 
									id="content_grid_table" 
									action="" >
								<tr>
									<th formatter="jqgridactions" width="40"  align=center>序号</th>
									<th name="CONTENT_UID"  width="150" >岗位	</th>
									<th name="CONTENT_TITLE" width="80" align=left >姓名</th>			
									<th name="CONTENT_STXT" width="80" align=left >证书名称	</th>
									<th name="CONTENT_STXT" width="100" align=left >证书编号</th>
								</tr>
							</table>
														</div>
													</div>
												</div>
												
												

		</div>

		<div class="modal-footer">
			<button class="btn btn-success btn-sm" id="save">申请解锁</button>
			<button class="btn btn-danger btn-sm pull-right" data-dismiss="modal"
				aria-hidden="true">关闭</button>
		</div>
	</div>
</div>
<!-- </div> -->

<script type="text/javascript">
var scripts = [null];

	ace.load_ajax_scripts(scripts, function() {
		var gridwidth=$("#contentdivid").width()-3;
		JqgridManage.initJqgrid(content_grid_table,queryForm2,gridwidth);

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
</script>