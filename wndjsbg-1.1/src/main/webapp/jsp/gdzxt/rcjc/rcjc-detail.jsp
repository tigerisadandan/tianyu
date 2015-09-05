<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%
	long ll= System.currentTimeMillis();
	String type=request.getParameter("type");
%>

<!-- Modal -->
<div class="modal-dialog width-75 height-auto">
  <div class="modal-content" >
   <div class="widget-header widget-header-large">
   <div class="widget-toolbar">
		<a href="#" data-action="close" data-dismiss="modal">
			<i class="ace-icon fa fa-times"></i>
		</a>
	</div>
    <h3 id="myModalLabel" class="blue bigger">日常检查信息</h3>
  </div>
  <div class="modal-body">
   <form method="post" id="queryFormById"  >
      <table class="B-table" width="100%">
      <!--可以再此处加入hidden域作为过滤条件 -->
      	<TR  style="display:none;">
	        <TD class="right-border bottom-border">
	        	<INPUT type="text"  kind="text" id="QID" name="PROJECT_UID"  fieldname="a.PROJECT_UID" value="" operation="="/>
	        </TD>
			<TD class="right-border bottom-border">
			</TD>
        </TR>
      </table>
      </form>
 	 <form method="post" role="form" class="form-horizontal"  id="executeFrm"  >
     
      <table class="B-table" width="100%">
      <input type="hidden" id="PROJECTS_UID" fieldname="PROJECTS_UID" name = "PROJECTS_UID"/></TD>
     		
			<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
														整改单编号：
													</label>
													<div class="col-sm-3">
														<input  type="text"  id="GUIHUA_CODE" maxlength="40"  fieldname="GUIHUA_CODE" name = "GUIHUA_CODE"  class="col-xs-10 col-sm-10"  readonly/>
													</div>
													<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
														建议扣分值：
													</label>
													<div class="col-sm-3">
														<input  type="text"  id="GUIHUA_CODE" maxlength="40"  fieldname="GUIHUA_CODE" name = "GUIHUA_CODE"  class="col-xs-10 col-sm-10"  readonly/>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
														责任单位（人）：
													</label>
													<div class="col-sm-8">
														<input  type="text"  id="GUIHUA_CODE" maxlength="40"  fieldname="GUIHUA_CODE" name = "GUIHUA_CODE"  class="col-xs-10 col-sm-10"  readonly/>
													</div>
													
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
														工程进度：
													</label>
													<div class="col-sm-8">
														<input  type="text"  id="GUIHUA_CODE" maxlength="40"  fieldname="GUIHUA_CODE" name = "GUIHUA_CODE"  class="col-xs-10 col-sm-10"  readonly/>
													</div>
													
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
														发放单位：
													</label>
													<div class="col-sm-3">
														<label>
															<input type="checkbox" id="AJZ" fieldname="AJZ" name="AJZ" />安监站
														</label>
														<label>
															<input type="checkbox" id="AJZ" fieldname="AJZ" name="AJZ" />质监站
														</label>
														<label>
															<input type="checkbox" id="AJZ" fieldname="AJZ" name="AJZ" />建管办
														</label>
													</div>
													<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
														下发人：
													</label>
													<div class="col-sm-3">
														<input  type="text"  id="GUIHUA_CODE" maxlength="40"  fieldname="GUIHUA_CODE" name = "GUIHUA_CODE"  class="col-xs-10 col-sm-10"  readonly/>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
														发放时间：
													</label>
													<div class="col-sm-3">
														<input  type="text"  id="GUIHUA_CODE" maxlength="40"  fieldname="GUIHUA_CODE" name = "GUIHUA_CODE"  class="col-xs-10 col-sm-10"  readonly/>
													</div>
													<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
														要求整改完成时间：
													</label>
													<div class="col-sm-3">
														<input  type="text"  id="GUIHUA_CODE" maxlength="40"  fieldname="GUIHUA_CODE" name = "GUIHUA_CODE"  class="col-xs-10 col-sm-10"  readonly/>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
														整改性质：
													</label>
													<div class="col-sm-3">
														<input  type="radio"  id="GUIHUA_CODE" maxlength="40"  fieldname="GUIHUA_CODE" name = "GUIHUA_CODE" kind="dic" src="ZGXZ" class="col-xs-10 col-sm-10"  />
													</div>
													
												</div>
		</table>
      </form>

	</div>
	
   <div class="widget-header widget-header-large">
   <div class="widget-toolbar">
	</div>
    <h3 id="myModalLabel" class="blue bigger">整改事件</h3>
  </div>
  <div class="modal-body">
	    <table id="zgsjxx-table"></table>
		
		<div id="zgsjxx-pager"></div>
		<script type="text/javascript">
				var $path_base = "/";//this will be used in gritter alerts containing images
		</script>
     </div>
  <div class="widget-header widget-header-large">
   <div class="widget-toolbar">
		
	</div>
    <h3 id="myModalLabel" class="blue bigger">整改图片</h3>
  </div>
  <div class="modal-body">
	    <table id="zgtp-table"></table>
		
		<div id="zgtp-pager"></div>
		<script type="text/javascript">
				var $path_base = "/";//this will be used in gritter alerts containing images
		</script>
     </div>
</div>

  <div class="modal-footer">
    <button class="btn btn-success btn-sm" id="btnSave" data-dismiss="modal" aria-hidden="true">保存</button>
    <button class="btn btn-danger btn-sm pull-right" id="btnClose" data-dismiss="modal" aria-hidden="true">关闭</button>
  </div>
</div>
</div>

<script type="text/javascript">



</script>
		