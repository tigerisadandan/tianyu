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
    <h3 id="myModalLabel" class="blue bigger">务工人员信息查看</h3>
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
														人员编号：
													</label>
													<div class="col-sm-4">
														<input  type="text"  id="GUIHUA_CODE" maxlength="40"  fieldname="GUIHUA_CODE" name = "GUIHUA_CODE"  class="col-xs-10 col-sm-10"  readonly/>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
														姓名：
													</label>
													<div class="col-sm-4">
														<input  type="text"  id="GUIHUA_CODE" maxlength="40"  fieldname="GUIHUA_CODE" name = "GUIHUA_CODE"  class="col-xs-10 col-sm-10"  readonly/>
													</div>
													<div class="col-sm-2">
														<label>
															<input id="QK" type="checkbox" name="QK" value="20" filedname="RY" title="" disabled>信息卡情况</label>
													</div>
													<div class="col-sm-2">
														<a href="javascript:void(0);"  onclick="showCard()">发卡情况</a>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
														所在单位：
													</label>
													<div class="col-sm-8">
														<input  type="text"  id="GUIHUA_CODE" maxlength="40"  fieldname="GUIHUA_CODE" name = "GUIHUA_CODE"  class="col-xs-10 col-sm-10"  readonly/>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
														证件类型：
													</label>
													<div class="col-sm-4">
														<input  type="text"  id="GUIHUA_CODE" maxlength="40"  fieldname="GUIHUA_CODE" name = "GUIHUA_CODE"  class="col-xs-10 col-sm-10"  readonly/>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
														证件号码：
													</label>
													<div class="col-sm-4">
														<input  type="text"  id="GUIHUA_CODE" maxlength="40"  fieldname="GUIHUA_CODE" name = "GUIHUA_CODE"  class="col-xs-10 col-sm-10"  readonly/>
													</div>
													<div class="col-sm-2">
														<span class="btn btn-info"  id="addFileSpan" onclick="selectYyzzFile(this);"  clkUid="41" fjlb="1000"> 
															<i class="icon-plus"></i> 
															<span >添加立项附件...</span> 
														</span>&nbsp; 
														<span id="yyzzError" style="display: none;color:red;"><b>添加立项附件</b>
														</span>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
														性别：
													</label>
													<div class="col-sm-4">
														<input  type="text"  id="GUIHUA_CODE" maxlength="40"  fieldname="GUIHUA_CODE" name = "GUIHUA_CODE"  class="col-xs-10 col-sm-10"  readonly/>
													</div>
													<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
														出生日期：
													</label>
													<div class="col-sm-4">
														<input  type="text"  id="GUIHUA_CODE" maxlength="40"  fieldname="GUIHUA_CODE" name = "GUIHUA_CODE"  class="col-xs-10 col-sm-10"  readonly/>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
														现在住址：
													</label>
													<div class="col-sm-8">
														<input  type="text"  id="GUIHUA_CODE" maxlength="40"  fieldname="GUIHUA_CODE" name = "GUIHUA_CODE"  class="col-xs-10 col-sm-10"  readonly/>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
														籍贯：
													</label>
													<div class="col-sm-4">
														<input  type="text"  id="GUIHUA_CODE" maxlength="40"  fieldname="GUIHUA_CODE" name = "GUIHUA_CODE"  class="col-xs-10 col-sm-10"  readonly/>
													</div>
													<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
														联系电话：
													</label>
													<div class="col-sm-4">
														<input  type="text"  id="GUIHUA_CODE" maxlength="40"  fieldname="GUIHUA_CODE" name = "GUIHUA_CODE"  class="col-xs-10 col-sm-10"  readonly/>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
														紧急联系人：
													</label>
													<div class="col-sm-4">
														<input  type="text"  id="GUIHUA_CODE" maxlength="40"  fieldname="GUIHUA_CODE" name = "GUIHUA_CODE"  class="col-xs-10 col-sm-10"  readonly/>
													</div>
													<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
														紧急联系人电话：
													</label>
													<div class="col-sm-4">
														<input  type="text"  id="GUIHUA_CODE" maxlength="40"  fieldname="GUIHUA_CODE" name = "GUIHUA_CODE"  class="col-xs-10 col-sm-10"  readonly/>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
														学历：
													</label>
													<div class="col-sm-4">
														<input  type="text"  id="GUIHUA_CODE" maxlength="40"  fieldname="GUIHUA_CODE" name = "GUIHUA_CODE"  class="col-xs-10 col-sm-10"  readonly/>
													</div>
													<div class="col-sm-2">
														<span class="btn btn-info"  id="addFileSpan" onclick="selectYyzzFile(this);"  clkUid="41" fjlb="1000"> 
															<i class="icon-plus"></i> 
															<span >添加立项附件...</span> 
														</span>&nbsp; 
														<span id="yyzzError" style="display: none;color:red;"><b>添加立项附件</b>
														</span>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
														户籍类别：
													</label>
													<div class="col-sm-4">
														<input  type="text"  id="GUIHUA_CODE" maxlength="40"  fieldname="GUIHUA_CODE" name = "GUIHUA_CODE"  class="col-xs-10 col-sm-10"  readonly/>
													</div>
													<div class="col-sm-2">
														<label>
															<input id="RY" type="checkbox" name="RY" value="20" filedname="RY" title="" disabled>特殊工种人员  </label>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
														人员类别：
													</label>
													<div class="col-sm-4">
														<input  type="text"  id="GUIHUA_CODE" maxlength="40"  fieldname="GUIHUA_CODE" name = "GUIHUA_CODE"  class="col-xs-10 col-sm-10"  readonly/>
													</div>
													<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
														公会会员：
													</label>
													<div class="col-sm-4">
														<input  type="text"  id="GUIHUA_CODE" maxlength="40"  fieldname="GUIHUA_CODE" name = "GUIHUA_CODE"  class="col-xs-10 col-sm-10"  readonly/>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
														岗前培训：
													</label>
													<div class="col-sm-4">
														<input  type="text"  id="GUIHUA_CODE" maxlength="40"  fieldname="GUIHUA_CODE" name = "GUIHUA_CODE"  class="col-xs-10 col-sm-10"  readonly/>
													</div>
													<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
														培训日期：
													</label>
													<div class="col-sm-4">
														<input  type="text"  id="GUIHUA_CODE" maxlength="40"  fieldname="GUIHUA_CODE" name = "GUIHUA_CODE"  class="col-xs-10 col-sm-10"  readonly/>
													</div>
												</div>
		</table>
      </form>

	</div>
	
   <div class="widget-header widget-header-large">
  <%--<button type="button" class="close" data-dismiss="modal">&times;</button>
   --%>
   <div class="widget-toolbar">
	</div>
    <h3 id="myModalLabel" class="blue bigger">专业技能 </h3>
  </div>
  <div class="modal-body">
	    <table id="zyjn-table"></table>
		
		<div id="zyjn-pager"></div>
		<script type="text/javascript">
				var $path_base = "/";//this will be used in gritter alerts containing images
		</script>
     </div>
  <div class="widget-header widget-header-large">
  <%--<button type="button" class="close" data-dismiss="modal">&times;</button>
   --%>
   <div class="widget-toolbar">
		
	</div>
    <h3 id="myModalLabel" class="blue bigger">体检情况</h3>
  </div>
  <div class="modal-body">
	    <table id="tjqk-table"></table>
		
		<div id="tjqk-pager"></div>
		<script type="text/javascript">
				var $path_base = "/";//this will be used in gritter alerts containing images
		</script>
     </div>
     <div class="widget-header widget-header-large">
  <%--<button type="button" class="close" data-dismiss="modal">&times;</button>
   --%>
   <div class="widget-toolbar">
		
	</div>
    <h3 id="myModalLabel" class="blue bigger">工作情况 </h3>
  </div>
  <div class="modal-body">
	    <table id="gzqk-table"></table>
		
		<div id="gzqk-pager"></div>
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

function showCard(){
	window.open("jsp/gdzxt/rygl/wgryjbxxgl-page-card.jsp","发卡情况","height=600, width=800");
	}

</script>
		