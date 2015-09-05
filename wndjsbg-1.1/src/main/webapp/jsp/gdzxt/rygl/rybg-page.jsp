<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.params.SysPara.SysParaConfigureVO"%>
<%@ page import="com.ccthanking.framework.params.ParaManager"%>
<%@ page import="com.ccthanking.framework.Constants"%>
<%@ include file="/jsp/framework/common/taglibs.jsp" %>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base />
<title>审批业务流转实例审批</title>
<%
String type=request.getParameter("type");
SysParaConfigureVO syspara  = (SysParaConfigureVO) ParaManager.getInstance().getSysParameter(Constants.getString(Constants.FILEUPLOADOLD_ROOT_JS, "FILEUPLOADOLD_ROOT_WNDJSJS"));
String fileRoot = syspara.getSysParaConfigureParavalue1();
String spyw_uid = request.getParameter("spyw_uid");
%>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/ywlz/css/default.css"> </LINK>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>	
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/kindeditor/plugins/code/prettify.css" />
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/kindeditor/kindeditor.js"></script>
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/kindeditor/lang/zh_CN.js"></script>

<script type="text/javascript" charset="utf-8">
</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">  
	<div class="B-small-from-table-autoConcise">
     <br/>
	    <table border="1" width="100%" cellpadding="0" cellspacing="0" class="content">
			<tr>
				<td class="yw-title">
					<h4 ><span id="spyjtxhid">现场管理人员 </span>
								<span class="pull-right" > 
								<button id="btnAddYj" class="btn" type="button"> 打印 </button>  
								</span>
								</h4>
				</td>
			</tr>
			<tr>
				<td>
					<ul id="ywcl_list">
						  <table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single"  pageNum="18">
	                <thead>
	                	<tr>
	                		<th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
	                		<th fieldname="AZ_COMPANY_UID" colindex=0 tdalign="center"  CustomFunction="caozuoFun" >&nbsp;&nbsp;</th>
							<th fieldname="PERSON_NAME" colindex=2 tdalign="center" maxlength="30">&nbsp;岗位&nbsp;</th>
							<th fieldname="SHENFENZHENG" colindex=2 tdalign="center" maxlength="30" >&nbsp;姓名&nbsp;</th>
							<th fieldname="SHENFENZHENG" colindex=2 tdalign="center" maxlength="30" >&nbsp;分值  &nbsp;</th>
							<th fieldname="SHENFENZHENG" colindex=2 tdalign="center" maxlength="30" >&nbsp;照片  &nbsp;</th>			
							<th fieldname="SHENFENZHENG" colindex=2 tdalign="center" maxlength="30" >&nbsp;信息卡  &nbsp;</th>			
							<th fieldname="SHENFENZHENG" colindex=2 tdalign="center" maxlength="30" >&nbsp;联系电话  &nbsp;</th>			
							<th fieldname="SHENFENZHENG" colindex=2 tdalign="center" maxlength="30" >&nbsp;身份证号 &nbsp;</th>			
							<th fieldname="SHENFENZHENG" colindex=2 tdalign="center" maxlength="30" >&nbsp;证书编号  &nbsp;</th>			
							<th fieldname="SHENFENZHENG" colindex=2 tdalign="center" maxlength="30" >&nbsp;专业   &nbsp;</th>							
	                	</tr>
	                </thead>
	              	<tbody></tbody>
	           </table>
					</ul>
				</td>
			</tr>
		</table>
		<div style="height: 5px;"></div>
		<table border="1" width="100%" cellpadding="0" cellspacing="0" class="content">
			<tr>
				<td class="yw-title">
					<h4 ><span id="spyjtxhid">人员变更  </span>
								<span class="pull-right" > 
								
								</span>
								</h4>
				</td>
			</tr>
			<tr>
				<td>
					<ul id="ywcl_list">
					  <table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single"  pageNum="18">
	                <thead>
	                	<tr>序号 
	                		<th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
	                		<th fieldname="AZ_COMPANY_UID" colindex=0 tdalign="center"  CustomFunction="caozuoFun" >&nbsp;&nbsp;</th>
							<th fieldname="SHENFENZHENG" colindex=2 tdalign="center" maxlength="30" >&nbsp;审核状态   &nbsp;</th>			
							<th fieldname="SHENFENZHENG" colindex=2 tdalign="center" maxlength="30" >&nbsp;变更岗位  &nbsp;</th>			
							<th fieldname="SHENFENZHENG" colindex=2 tdalign="center" maxlength="30" >&nbsp; 变更前  &nbsp;</th>			
							<th fieldname="SHENFENZHENG" colindex=2 tdalign="center" maxlength="30" >&nbsp; 变更后  &nbsp;</th>			
							<th fieldname="SHENFENZHENG" colindex=2 tdalign="center" maxlength="30" >&nbsp;变更理由  &nbsp;</th>			
							<th fieldname="SHENFENZHENG" colindex=2 tdalign="center" maxlength="30" >&nbsp; 提交时间    &nbsp;</th>							
	                	</tr>
	                </thead>
	              	<tbody></tbody>
	           </table>
						
					</ul>
				</td>
			</tr>
		</table>
	
			
    </div>
	<input type="hidden" name="txtXML" id="wjCbfa">
   </div>
  </div>
  
 <jsp:include page="/jsp/file_upload/fileuploadold_config_forYwlz.jsp" flush="true" />
   <div align="center">

		
		<!-- 步骤文件  -->
		<FORM name="frmPostbzwj" method="post" style="display:none"
			target="_blank">
			<!--系统保留定义区域-->
			<input type="hidden" name="queryXML" id="queryXML"> 
			<input type="hidden" name="txtXML" id="txtXML">
			<input type="hidden" name="resultXML" id="resultXML">

			<!--传递行数据用的隐藏域-->
			<input type="hidden" name="rowData">
		</FORM>
		<!-- 步骤文件  31-->
		<FORM name="frmPost31" method="post" style="display:none"
			target="_blank">
			<!--系统保留定义区域-->
			<input type="hidden" name="queryXML" id="queryXML"> 
			<input type="hidden" name="txtXML" id="txtXML">
			<input type="hidden" name="resultXML" id="resultXML">

			<!--传递行数据用的隐藏域-->
			<input type="hidden" name="rowData">
		</FORM>
		<FORM name="frmPost" method="post" style="display:none"
			target="_blank">
			<!--系统保留定义区域-->
			<input type="hidden" name="queryXML" id="queryXML"> 
			<input type="hidden" name="txtXML" id="txtXML">
			<input type="hidden" name="resultXML" id="resultXML">

			<!--传递行数据用的隐藏域-->
			<input type="hidden" name="rowData">
		</FORM>
	</div>
</body>
<script>
</script>
</html>