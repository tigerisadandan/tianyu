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
    <h3 id="myModalLabel" class="blue bigger">考勤暂停 </h3>
    
     
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
				姓名:
				</label>
				<div class="col-sm-2">
					 <input  type="text" name="JIANSHE_DIZHI" id="JIANSHE_DIZHI" fieldname="JIANSHE_DIZHI" class="col-xs-10 col-sm-10"  />
				</div>
				<label class="col-sm-1 control-label no-padding-right" for="">
				职务:
				</label>
				<div class="col-sm-3">
					 <input  type="text" name="JIANSHE_DIZHI" id="JIANSHE_DIZHI" fieldname="JIANSHE_DIZHI" class="col-xs-10 col-sm-10"  />
				</div>
				<label class="col-sm-2 control-label no-padding-right" for="">
				考勤阶段:
				</label>
				<div class="col-sm-2">
					 <select  type="text" kind="dic" src="KQJD" name="JIANSHE_DIZHI" id="JIANSHE_DIZHI" fieldname="JIANSHE_DIZHI" class="col-xs-10 col-sm-10"  ></select>
				</div>
			</div>
			<div class="form-group" >
				<label class="col-sm-2 control-label no-padding-right" for="">
				分阶段考勤日期:
				</label>
				<div class="col-sm-3 inline">
 					<div class="input-daterange">
					<span class="input-icon input-icon-right">
					
					<input datatype="*"  nullmsg="请输入预计投产日期！" errormsg="" placeholder="请输入预计投产日期！" type="text" data-date-format="yyyy-mm-dd" fieldname="TC_DATE"  style="height:30px" name="TC_DATE" id="TC_DATE"  readonly />
						 <i class="ace-icon fa fa-calendar"></i>
						  <div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
						 </span>
					</div>	
					
				</div>
				<label class="col-sm-1 control-label no-padding-right " for="">
					~
				</label>
				<div class="col-sm-3 inline">
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
				分阶段考勤原因:
				</label>
				<div class="col-sm-10">
					<textarea type="text" rows="3" cols="120" id="JIANSHE_TYPE"  name = "JIANSHE_TYPE" fieldname="JIANSHE_TYPE"  class="col-xs-10 col-sm-10" readonly ></textarea>
				</div>
			</div>
			<div class="form-group" >
				<label class="col-sm-2 control-label no-padding-right" for="">
				监督小组确认意见:
				</label>
				<div class="col-sm-10">
					<textarea type="text" rows="3" cols="120" id="JIANSHE_TYPE"  name = "JIANSHE_TYPE" fieldname="JIANSHE_TYPE"  class="col-xs-10 col-sm-10" readonly ></textarea>
				</div>
			</div>
			<div class="form-group" >
				<label class="col-sm-2 control-label no-padding-right" for="">
				一级审核意见:
				</label>
				<div class="col-sm-10">
					<textarea type="text" rows="3" cols="120" id="JIANSHE_TYPE"  name = "JIANSHE_TYPE" fieldname="JIANSHE_TYPE"  class="col-xs-10 col-sm-10" readonly ></textarea>
				</div>
			</div>
			<div class="form-group" >
				<label class="col-sm-2 control-label no-padding-right" for="">
				二级审核意见:
				</label>
				<div class="col-sm-10">
					<textarea type="text" rows="3" cols="120" id="JIANSHE_TYPE"  name = "JIANSHE_TYPE" fieldname="JIANSHE_TYPE"  class="col-xs-10 col-sm-10" readonly ></textarea>
				</div>
			</div>
			<div class="form-group" >
				<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
				创建时间:
				</label>
				<div class="col-sm-3">
					<lable id="SGDW" fieldname="SGDW" name="SGDW" ></lable>
				</div>
			</div>
			<div class="form-group" >
				<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
				小组确认人:
				</label>
				<div class="col-sm-3">
					<lable id="SGDW" fieldname="SGDW" name="SGDW" ></lable>
				</div>
				<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
				小组确认时间:
				</label>
				<div class="col-sm-3">
					<lable id="SGDW_XYFZ" fieldname="SGDW_XYFZ" name="SGDW_XYFZ" ></lable>
				</div>
			</div>
			<div class="form-group" >
				<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
				审核人1:
				</label>
				<div class="col-sm-3">
					<lable id="SGDW" fieldname="SGDW" name="SGDW" ></lable>
				</div>
				<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
				审核时间:
				</label>
				<div class="col-sm-3">
					<lable id="SGDW_XYFZ" fieldname="SGDW_XYFZ" name="SGDW_XYFZ" ></lable>
				</div>
			</div>
			<div class="form-group" >
				<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
				审核人2:
				</label>
				<div class="col-sm-3">
					<lable id="SGDW" fieldname="SGDW" name="SGDW" ></lable>
				</div>
				<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
				审核时间:
				</label>
				<div class="col-sm-3">
					<lable id="SGDW_XYFZ" fieldname="SGDW_XYFZ" name="SGDW_XYFZ" ></lable>
				</div>
			</div>
			<div class="form-group" >
				<label class="col-sm-2 control-label no-padding-right" for="">
				</label>
				<div class="col-sm-10">
					<font style="color:blue;">保存后点击打印按钮，打印备案表并提交安监站备案</font>
				</div>
			</div>
		</table>
      </form>

	</div>
  
 
</div>

  <div class="modal-footer">
    <button class="btn btn-success btn-sm pull-right" id="btnPrint" data-dismiss="modal" aria-hidden="true">打印</button>
    <button class="btn btn-danger btn-sm pull-right" id="btnClose" data-dismiss="modal" aria-hidden="true">关闭</button>
  </div>
</div>
</div>
<%-- </div>--%>

<script type="text/javascript">



</script>
		