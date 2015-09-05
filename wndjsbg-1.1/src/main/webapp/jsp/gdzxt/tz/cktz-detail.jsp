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
    <h3 id="myModalLabel" class="blue bigger">查看重要通知 </h3>
    
     
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
				情况级别：
				</label>
				<div class="col-sm-4">
					 <input  type="text" name="PROJECTS_NAME" id="PROJECTS_NAME" fieldname="PROJECTS_NAME" class="col-xs-10 col-sm-10" readonly />
					
				</div>
				<label class="col-sm-2 control-label no-padding-right" for="">
				录入栏目：
				</label>
				<div class="col-sm-4">
					 <input  type="text" name="PROJECTS_NAME" id="PROJECTS_NAME" fieldname="PROJECTS_NAME" class="col-xs-10 col-sm-10" readonly />
				</div>
			</div>
			<div class="form-group" >
				<label class="col-sm-2 control-label no-padding-right" for="">
				通知标题：
				</label>
				<div class="col-sm-10">
					 <input  type="text" name="JIANSHE_DIZHI" id="JIANSHE_DIZHI" fieldname="JIANSHE_DIZHI" class="col-xs-10 col-sm-10" readonly />
				</div>
				
			</div>
		
			<div class="form-group" >
				<label class="col-sm-2 control-label no-padding-right" for="">
				通知内容：
				</label>
				<div class="col-sm-10">
					 <textarea type="text" rows="10" cols="90" id="JIANSHE_TYPE"  name = "JIANSHE_TYPE" fieldname="JIANSHE_TYPE"  class="col-xs-10 col-sm-10" readonly ></textarea>
				</div>
			</div>	
			<div class="form-group" >
				<label class="col-sm-2 control-label no-padding-right" for="">
				发布人：
				</label>
				<div class="col-sm-4">
					 <input  type="text"  id="GUIHUA_CODE" maxlength="40"  fieldname="GUIHUA_CODE" name = "GUIHUA_CODE"  class="col-xs-10 col-sm-10" readonly />
				</div>
				<label class="col-sm-2 control-label no-padding-right" for="">
				发布日期：
				</label>
				<div class="col-sm-4">
					 <input  type="text"  id="HYFL" maxlength="40"  fieldname="HYFL" name = "HYFL" class="col-xs-10 col-sm-10" readonly />
				</div>
			</div>	
			
      </table>
      </form>

	</div>
  <div class="widget-header widget-header-large hidden" >
  <%--<button type="button" class="close" data-dismiss="modal">&times;</button>
   --%>
   <div class="widget-toolbar">
		
	</div>
    <h3 id="myModalLabel" class="blue bigger">附件列表 </h3>
    
  </div>
  <div class="modal-body">
 	<table id="fjlb-table"></table>
	<div id="fjlb-pager"></div>
		<script type="text/javascript">
			var $path_base = "/";//this will be used in gritter alerts containing images
		</script>

	</div>
</div>

  <div class="modal-footer">
  
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
		