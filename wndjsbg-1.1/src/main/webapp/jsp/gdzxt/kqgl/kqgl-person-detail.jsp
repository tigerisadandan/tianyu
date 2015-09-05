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
    <h3 id="myModalLabel" class="blue bigger">管理人员信息 </h3>
    
     
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
				姓名：
				</label>
				<div class="col-sm-4">
					 <input  type="text" name="PROJECTS_NAME" id="PROJECTS_NAME" fieldname="PROJECTS_NAME" class="col-xs-10 col-sm-10"  />
					
				</div>
				<label class="col-sm-2 control-label no-padding-right" for="">
				信用评分：
				</label>
				<div class="col-sm-4"><%--
					 <lable id="JSDW" fieldname="JSDW" name="JSDW" ></lable>
					 --%><a href="javascript:void(0);" onclick="open_score()">
					 90
					 </a>
				</div>
			</div>
			<div class="form-group" >
				<label class="col-sm-2 control-label no-padding-right" for="">
				人员编号：
				</label>
				<div class="col-sm-4">
					 <input  type="text" name="PROJECTS_NAME" id="PROJECTS_NAME" fieldname="PROJECTS_NAME" class="col-xs-10 col-sm-10"  readonly/>
					
				</div>
				<label class="col-sm-2 control-label no-padding-right" for="">
					<a href="javascript:void(0);" onclick="open_card()">
					发卡情况
					</a>
				</label>
			
			</div>
			<div class="form-group" >
				<label class="col-sm-2 control-label no-padding-right" for="">
				证件类型：
				</label>
				<div class="col-sm-4">
					 <input  type="text"  id="GUIHUA_CODE" maxlength="40"  fieldname="GUIHUA_CODE" name = "GUIHUA_CODE"  class="col-xs-10 col-sm-10"  />
				</div>
				<label class="col-sm-2 control-label no-padding-right" for="">
				证件号码：
				</label>
				<div class="col-sm-4">
					 <input  type="text"  id="HYFL" maxlength="40"  fieldname="HYFL" name = "HYFL" class="col-xs-10 col-sm-10"  />
				</div>
			</div>	
			<div class="form-group" >
				<label class="col-sm-2 control-label no-padding-right" for="">
				联系电话：
				</label>
				<div class="col-sm-10">
					 <input  type="text" name="JIANSHE_DIZHI" id="JIANSHE_DIZHI" fieldname="JIANSHE_DIZHI" class="col-xs-10 col-sm-10"  />
				</div>
			</div>
			<div class="form-group" >
				<label class="col-sm-2 control-label no-padding-right" for="">
				专业：
				</label>
				<div class="col-sm-4">
					 <input  type="text"  id="GUIHUA_CODE" maxlength="40"  fieldname="GUIHUA_CODE" name = "GUIHUA_CODE"  class="col-xs-10 col-sm-10"  />
				</div>
				<label class="col-sm-2 control-label no-padding-right" for="">
				证书编号：
				</label>
				<div class="col-sm-4">
					 <input  type="text"  id="HYFL" maxlength="40"  fieldname="HYFL" name = "HYFL" class="col-xs-10 col-sm-10"  />
				</div>
			</div>	
			<div class="form-group" >
				<label class="col-sm-2 control-label no-padding-right" for="">
				岗位：
				</label>
				<div class="col-sm-4">
					 <input  type="text"  id="GUIHUA_CODE" maxlength="40"  fieldname="GUIHUA_CODE" name = "GUIHUA_CODE"  class="col-xs-10 col-sm-10"  />
				</div>
				
			</div>	
      </table>
      </form>

	</div>

</div>

  <div class="modal-footer">
    <button class="btn btn-success btn-sm pull-right" id="btnStop" data-dismiss="modal" aria-hidden="true">考勤终止</button>
    <button class="btn btn-success btn-sm pull-right" id="btnOver" data-dismiss="modal" aria-hidden="true">考勤暂停</button>
  	<button class="btn btn-success btn-sm pull-right" id="btnJieDuan" data-dismiss="modal" aria-hidden="true">分阶段考勤</button>
    <button class="btn btn-danger btn-sm pull-right" id="btnClose" data-dismiss="modal" aria-hidden="true">关闭</button>
  </div>
</div>
</div>
<%-- </div>--%>

<script type="text/javascript">

function open_score(){

	window.open("jsp/gdzxt/kqgl/kqgl-score.jsp","终止考勤申请","height=600, width=800");
}
function open_card(){

	window.open("jsp/gdzxt/kqgl/kqgl-card.jsp","终止考勤申请","height=600, width=800");
}
</script>
		