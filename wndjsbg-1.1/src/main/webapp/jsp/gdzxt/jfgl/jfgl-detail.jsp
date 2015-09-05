<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%
	long ll= System.currentTimeMillis();
	String type=request.getParameter("type");
%>

<!-- Modal -->


<%--<div id="myModal" class="modal  fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  --%><div class="modal-dialog width-65 height-auto">
  <div class="modal-content" >
   <div class="widget-header widget-header-large">
  <%--<button type="button" class="close" data-dismiss="modal">&times;</button>
   --%>
   <div class="widget-toolbar">
		<a href="#" data-action="close" data-dismiss="modal">
			<i class="ace-icon fa fa-times"></i>
		</a>
	</div>
    <h3 id="myModalLabel" class="blue bigger">加分申请信息  </h3>
    
     
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
     	
			<div class="form-group" >
				<label class="col-sm-2 control-label no-padding-right" for="">
				申请日期：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
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
				申请单位：
				</label>
				<div class="col-sm-10">
					 <input  type="text" name="JIANSHE_DIZHI" id="JIANSHE_DIZHI" fieldname="JIANSHE_DIZHI" class="col-xs-10 col-sm-10" readonly />
				</div>
				
			</div>
		
			<div class="form-group" >
				<label class="col-sm-2 control-label no-padding-right" for="">
				备注：
				</label>
				<div class="col-sm-10">
					 <textarea type="text" rows="10" cols="90" id="JIANSHE_TYPE"  name = "JIANSHE_TYPE" fieldname="JIANSHE_TYPE"  class="col-xs-10 col-sm-10" readonly ></textarea>
				</div>
			</div>
			<div class="form-group" >
				<label class="col-sm-2 control-label no-padding-right" for="">
			           加分对象：
				</label>
				<%--<div class="col-sm-8">
					<table id="jfdx-table"></table>
					<div id="jfdx-pager"></div>
						<script type="text/javascript">
							var $path_base = "/";//this will be used in gritter alerts containing images
						</script>
				
					</div>
				</div>--%>
			</div>
		</table>
      </form>

	</div>
   <div class="widget-header widget-header-large " >
  <%--<button type="button" class="close" data-dismiss="modal">&times;</button>
   --%>
   <div class="widget-toolbar">
		
	</div>
    <h3 id="myModalLabel" class="blue bigger">加分事件 </h3>
    
  </div>
  <div class="modal-body">
 	<table id="jfsj-table"></table>
	<div id="jfsj-pager"></div>
		<script type="text/javascript">
			var $path_base = "/";//this will be used in gritter alerts containing images
		</script>

	</div>
 
</div>

  <div class="modal-footer">
    <button class="btn btn-success btn-sm pull-right" id="btnSave" data-dismiss="modal" aria-hidden="true">保存</button>
    <button class="btn btn-danger btn-sm pull-right" id="btnClose" data-dismiss="modal" aria-hidden="true">关闭</button>
  </div>
</div>
</div>
<%-- </div>--%>

<script type="text/javascript">


//点击保存按钮
$(function() {
	//$("#fujian").hide();//通过判断查询出来的附件个数，来设置附件列表是否显示
	
}

</script>
		