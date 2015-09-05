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
    <h3 id="myModalLabel" class="blue bigger">务工人员基本信息录入</h3>
    
     
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
				<div class="col-sm-4">
					 <input  type="text" name="JIANSHE_DIZHI" id="JIANSHE_DIZHI" fieldname="JIANSHE_DIZHI" class="col-xs-10 col-sm-10"  />
				</div>
				
			</div>
			<div class="form-group" >
				<label class="col-sm-2 control-label no-padding-right" for="">
				所在单位:
				</label>
				<div class="col-sm-8">
					 <input  type="text" name="JIANSHE_DIZHI" id="JIANSHE_DIZHI" fieldname="JIANSHE_DIZHI" class="col-xs-10 col-sm-10"  />
				</div>
			</div>
			<div class="form-group" >
				<label class="col-sm-2 control-label no-padding-right" for="">
				证件类型:
				</label>
				<div class="col-sm-2">
					 <select class="form-control">
						 <OPTION name="ZJLX" filedname="ZJLX" value="1">身份证</OPTION> 
						 <OPTION name="ZJLX" filedname="ZJLX" value="2">驾驶证</OPTION>
					  </select>
				</div>
			</div>
			<div class="form-group" >
				<label class="col-sm-2 control-label no-padding-right" for="">
				证件号码:
				</label>
				<div class="col-sm-4">
					 <input  type="text" name="JIANSHE_DIZHI" id="JIANSHE_DIZHI" fieldname="JIANSHE_DIZHI" class="col-xs-10 col-sm-10"  />
				</div>
			</div>
			<div class="form-group" >
				<label class="col-sm-2 control-label no-padding-right" for="">
				性别:
				</label>
				<div class="col-sm-2">
					 <select class="form-control">
						 <OPTION value='1'>男</OPTION> 
						 <OPTION  value='2'>女</OPTION>
					 </select>
				</div>
				<label class="col-sm-1 control-label no-padding-right" for="">
				年龄:
				</label>
				<div class="col-sm-1">
					 <input  type="text" name="JIANSHE_DIZHI" id="JIANSHE_DIZHI" fieldname="JIANSHE_DIZHI" class="col-xs-10 col-sm-10"  />
				</div>
				<label class="col-sm-2 control-label no-padding-right" for="">
				出生日期:
				</label>
				<div class="col-sm-4 inline">
 					<div class="input-daterange">
					<span class="input-icon input-icon-right">
					<input  type="text" data-date-format="yyyy-mm-dd" fieldname="TC_DATE"  style="height:30px" name="TC_DATE" id="TC_DATE"  readonly />
						 <i class="ace-icon fa fa-calendar"></i>
						  <div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
						 </span>
					</div>	
				</div>
			</div>
			<div class="form-group" >
				<label class="col-sm-2 control-label no-padding-right" for="">
				现在地址:
				</label>
				<div class="col-sm-8">
					 <input  type="text" name="JIANSHE_DIZHI" id="JIANSHE_DIZHI" fieldname="JIANSHE_DIZHI" class="col-xs-10 col-sm-10"  />
				</div>
			</div>
			<div class="form-group" >
				<label class="col-sm-2 control-label no-padding-right" for="">
				籍贯:
				</label>
				<div class="col-sm-4">
					 <select class="form-control">
							<OPTION name="JG" filedname="JG" value="1">北京</OPTION>
							<OPTION name="JG" filedname="JG" value="2">天津</OPTION> 
							<OPTION name="JG" filedname="JG" value="3">河北</OPTION>
							<OPTION name="JG" filedname="JG" value="4">山西</OPTION>
							<OPTION name="JG" filedname="JG" value="5">内蒙古自治区</OPTION>
							<OPTION name="JG" filedname="JG" value="6">辽宁</OPTION> 
							<OPTION name="JG" filedname="JG" value="7">吉林</OPTION>
							<OPTION name="JG" filedname="JG" value="8">黑龙江</OPTION>
							<OPTION name="JG" filedname="JG" value="9">上海</OPTION> 
							<OPTION name="JG" filedname="JG" value="10">河南</OPTION>
							<OPTION name="JG" filedname="JG" value="11">江苏</OPTION>
							<OPTION name="JG" filedname="JG" value="12">浙江</OPTION> 
							<OPTION name="JG" filedname="JG" value="13">安徽</OPTION>
							<OPTION name="JG" filedname="JG" value="14">福建</OPTION>
							<OPTION name="JG" filedname="JG" value="15">江西</OPTION>
							<OPTION name="JG" filedname="JG" value="16">山东</OPTION> 
							<OPTION name="JG" filedname="JG" value="17">湖北</OPTION>
							<OPTION name="JG" filedname="JG" value="18">湖南</OPTION>
							<OPTION name="JG" filedname="JG" value="19">广东</OPTION> 
							<OPTION name="JG" filedname="JG" value="20">广西壮族自治区</OPTION>
							<OPTION name="JG" filedname="JG" value="21">海南</OPTION>
							<OPTION name="JG" filedname="JG" value="22">重庆</OPTION>
							<OPTION name="JG" filedname="JG" value="23">四川</OPTION> 
							<OPTION name="JG" filedname="JG" value="24">贵州</OPTION>
							<OPTION name="JG" filedname="JG" value="25">云南</OPTION>
							<OPTION name="JG" filedname="JG" value="26">西藏自治区</OPTION>
							<OPTION name="JG" filedname="JG" value="27">陕西</OPTION>
							<OPTION name="JG" filedname="JG" value="28">甘肃</OPTION> 
							<OPTION name="JG" filedname="JG" value="29">青海</OPTION>
							<OPTION name="JG" filedname="JG" value="30">宁夏回族自治区</OPTION>
					  </select>
				</div>
				<label class="col-sm-2 control-label no-padding-right" for="">
				联系电话:
				</label>
				<div class="col-sm-4">
					 <input  type="text" name="JIANSHE_DIZHI" id="JIANSHE_DIZHI" fieldname="JIANSHE_DIZHI" class="col-xs-10 col-sm-10"  />
				</div>
			</div>
			<div class="form-group" >
				<label class="col-sm-2 control-label no-padding-right" for="">
				紧急联系人:
				</label>
				<div class="col-sm-4">
					 <input  type="text" name="JIANSHE_DIZHI" id="JIANSHE_DIZHI" fieldname="JIANSHE_DIZHI" class="col-xs-10 col-sm-10"  />
				</div>
				<label class="col-sm-2 control-label no-padding-right" for="">
				联系电话:
				</label>
				<div class="col-sm-4">
					 <input  type="text" name="JIANSHE_DIZHI" id="JIANSHE_DIZHI" fieldname="JIANSHE_DIZHI" class="col-xs-10 col-sm-10"  />
				</div>
			</div>
			<div class="form-group" >
				<label class="col-sm-2 control-label no-padding-right" for="">
				学历:
				</label>
				<div class="col-sm-4">
					 <input  type="text" name="JIANSHE_DIZHI" id="JIANSHE_DIZHI" fieldname="JIANSHE_DIZHI" class="col-xs-10 col-sm-10"  />
				</div>
			</div>
			<div class="form-group" >
				<label class="col-sm-2 control-label no-padding-right" for="">
				人员类别:
				</label>
				<div class="col-sm-4">
					<label>
						<input id="RYLB" type="radio" name="RYLB" value="20" onclick="doQuery()" checked="checked" title="">本省市 </label>
					<label>
						<input id="RYLB" type="radio" name="RYLB" value="20" onclick="doQuery()"  title="">外省市  </label>
				</div>
				<div class="col-sm-4">
					<label>
						<input id="RY" type="checkbox" name="RY" value="20" filedname="RY" title="">工会会员 </label>
					<label>
					<input id="RY" type="checkbox" name="RY" value="20" filedname="RY" title="">特殊工种人员  </label>
				</div>
			</div>
			<div class="form-group" >
				<label class="col-sm-2 control-label no-padding-right" for="">
				户籍类别:
				</label>
				<div class="col-sm-4">
					 <label>
					 	<input id="HJLB" type="radio" name="HJLB" value="20" onclick="doQuery()" checked="checked" title="">农业 </label>
					 <label>
					 	<input id="HJLB" type="radio" name="HJLB" value="20" onclick="doQuery()"  title="">非农业 </label>
				</div>
			</div>
			<div class="form-group" >
				<label class="col-sm-2 control-label no-padding-right" for="">
				岗前培训:
				</label>
				<div class="col-sm-4">
					  <label>
					  	 <input id="GQPX" type="radio" name="GQPX" value="20" onclick="doQuery()" checked="checked" title="">参加过 </label>
					  <label>
					     <input id="GQPX" type="radio" name="GQPX" value="20" onclick="doQuery()"  title="">没有参加 </label>
				</div>
				<label class="col-sm-2 control-label no-padding-right" for="">
				岗前培训日期:
				</label>
				<div class="col-sm-4 inline">
 					<div class="input-daterange">
					<span class="input-icon input-icon-right">
					<input  type="text" data-date-format="yyyy-mm-dd" fieldname="TC_DATE"  style="height:30px" name="TC_DATE" id="TC_DATE"  readonly />
						 <i class="ace-icon fa fa-calendar"></i>
						  <div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
						 </span>
					</div>	
				</div>
			</div>
		</table>
      </form>

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



</script>
		