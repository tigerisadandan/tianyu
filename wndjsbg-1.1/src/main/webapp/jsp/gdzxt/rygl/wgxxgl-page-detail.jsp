<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%
	long ll= System.currentTimeMillis();
	String type=request.getParameter("type");
%>

<!-- Modal -->


<%--<div id="myModal" class="modal  fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  --%><div class="modal-dialog width-75 height-auto">
  <div class="modal-content" >
   <div class="widget-header widget-header-large">
  <%--<button type="button" class="close" data-dismiss="modal">&times;</button>
   --%>
   <div class="widget-toolbar">
		<a href="#" data-action="close" data-dismiss="modal">
			<i class="ace-icon fa fa-times"></i>
		</a>
	</div>
    <h3 id="myModalLabel" class="blue bigger">务工信息(姓名：)</h3>
    
     
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
 	 <table id="grid-table"></table>

						<div id="grid-pager"></div>
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


</script>
		