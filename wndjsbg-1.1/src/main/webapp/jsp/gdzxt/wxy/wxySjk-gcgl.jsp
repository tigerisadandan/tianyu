<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<title>审批业务流转实例</title>
 
 	<!-- jqgrid插件CSS样式 -->
	<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/ui.jqgrid.css" />
	<!-- ace主题基础样式及字体 -->
	<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/ace.min.css" />
	<div class="row">
			<div class="col-sm-12">
			<jsp:include page="ywlz-single-tab.jsp" flush="true" />
			
					<div class="row">
						<div class="col-sm-12">
							<div class="widget-box">
								<div class="widget-header">
									<h4 class="lighter blue widget-title">
										<span class="col-xs-6"><span id="cltitle">材料信息</span></span>
										
										<span class="col-xs-6">
											<span class="pull-right inline">
												<button id="btnRet" class="btn btn-primary btn-sm" onClick="returnView()" type="button"><i class="#ace-icon glyphicon glyphicon-arrow-left  bigger-110"></i>返回</button>
											</span>
										</span>
									</h4>
								</div>
								<div class="widget-body">
									<div class="widget-main">
										<div id="ywcl_list">
											
										
										</div><!-- /span -->
									</div>
								</div>
							</div>
						</div><!-- /span -->
					</div>
					
					<div class="row">
						<div class="col-sm-12">
							<div class="widget-box">
								<div class="widget-header">
									<h4 class="lighter blue widget-title">
										<span class="col-xs-12">帮助</span><!-- /span -->
									</h4>
								</div>
								<div class="widget-body">
									<div class="widget-main">
										<div id="bz_text">
											
										
										</div><!-- /span -->
									</div>
								</div>
							</div>
						</div><!-- /span -->
					</div>

					<div class="row">
						<div class="col-sm-12">
							<div class="widget-box">
								<div class="widget-header">
									<h4 class="lighter blue widget-title">
										<span class="col-xs-12">受理意见</span>
									</h4>
								</div>
								<div class="widget-body">
									<div class="widget-main" style="padding:0px;">
										<table id="grid-table"></table>
										<div id="grid-pager"></div>
										<script type="text/javascript">
												var $path_base = "/";//this will be used in gritter alerts containing images
										</script>
									</div>
								</div>
							</div>
						</div>
					</div>	
					<div class="row" id="hftable">
						<div class="col-sm-12">
							<div class="widget-box">
								<div class="widget-header">
									<h4 class="lighter blue widget-title">
										<span class="col-xs-12">核发文件</span>
									</h4>
								</div>
								<div class="widget-body">
									<div class="widget-main" style="padding:0px;">
										<table id="grid-table2"></table>
										<div id="grid-pager2"></div>
										<script type="text/javascript">
												var $path_base = "/";//this will be used in gritter alerts containing images
										</script>
									</div>
								</div>
							</div>
						</div>
					</div>	
					<jsp:include page="/jsp/gdzxt/sgsx/sxsp/fileupload_config_ajax_forYwlz.jsp" flush="true" />
					<div class="row">
						<div class="col-sm-12">
							<form role="form" class="form-horizontal" id="queryForm">
								<div class="form-group">
									<div class="col-xs-8">
										<input type="hidden" name="YWLZ_UID" id="YWLZ_UID" fieldname="YWLZ_UID" operation="=" />
										<input class="hidden" id="rownum" type="text" fieldname="rownum"  value="10000" operation="  &lt;= "/>
									</div>
								</div>
							</form>							
						</div>
					</div>

		</div>
		</div>


<script type="text/javascript" charset="utf-8">


</script>		
</body>
</html>