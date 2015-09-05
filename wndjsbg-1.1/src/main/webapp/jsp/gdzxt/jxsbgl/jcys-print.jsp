<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>安装告知表</title>
<% String id = request.getParameter("id");
String gcid = request.getParameter("gcid");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/kindeditor/kindeditor.js"></script>
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/jsp/business/sp/js/printPageHtml.js"> </script>
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/kindeditor/customKE.js"></script>
</head>
<body>
<app:dialogs/>
<div class="main-container" id="main-container">
		<div style="padding:10px;padding-top:2px;text-align:left;"
			valign="top">
			<div style="width:730;">
				<table id="apex_layout_84945431230501312" class="formlayout"
					summary="">
					<tbody>
						<tr>
							<td nowrap="nowrap" align="right"><label for="P6052_BT"><span
									class="t3Optional"></span> </label>
							</td>
							<td colspan="1" rowspan="1" align="center" style="width: 730px"><span
								id="P6052_BT"><font style="font-size:15pt;">起重设备检测管理表

								</font><br> <br> </span>
							</td>
							<td style="width: 140px" align="left">
							<span style="float:right">
			<button id="btnClose" class="btn" type="button"
				style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
			<button id="btnSave" class="btn" type="button"
				style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">打印</button>
		</span>
							</td>
						</tr>
					</tbody>
				</table>
			</div>

			<style>
.formlayout {
	border: none;
}

.styleform {
	padding: 5px 10px 5px 10px;
}

.styleform table {
	border-collapse: collapse;
}

.styleform table .radiogroup {
	border: none;
}

.styleform table .radiogroup td {
	border: none;
}

.styleform td {
	border: solid 1px #000000;
}

.styleform td .noborder {
	border: none;
}

.formtd {
	border: solid 1px #00000;
}
</style>

			<form class="form-horizontal" id="azgzform">

				<table class="t3RegionwithoutButtonsandTitles" cellspacing="0"
					border="0" summary="layout" id="R16502528098140193">
					<tbody>
						<tr>
							<td valign="bottom" class="t3RegionHeader"
								style="text-align:center;"></td>
						</tr>

						<tr class="t3Body">
							<td colspan="2" class="styleform"
								style="border:1px solid #000000;padding:0px;"><table
									id="apex_layout_16502528098140193" class="formlayout"
									summary="">
									<tbody>
										<tr>
											<td align="right" style="width: 120px"><label for="P8024_SHEBEI_NAME"><span
													class="t3Optional">设备名称</span>
											</label>
											</td>
											<td style="width:220px;" colspan="1" rowspan="1" align="left" fieldname="SHEBEI_NAME">
											</td>
											<td align="right" style="width: 120px"><label for="P8024_CQ_BH"><span
													class="t3Optional">产权编号</span>
											</label>
											</td>
											<td colspan="1" style="width: 230px" rowspan="1" align="left" fieldname="CQ_BH">
											</td>
										</tr>
										<tr>
											<td align="right"><label for="P8024_JC_DW"><span
													class="t3Optional">检测单位</span>
											</label>
											</td>
											<td colspan="3" rowspan="1" align="left" fieldname="JC_DW">
											</td>
										</tr>
										<tr>
											<td align="right"><label for="P8024_JCBG_BH"><span
													class="t3Optional">检测报告编号</span>
											</label>
											</td>
											<td style="width:150px;" colspan="1" rowspan="1" align="left"  fieldname="JCBG_BH">
											</td>
											<td align="right"><label for="P8024_JC_DATE"><span
													class="t3Optional">检测日期</span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"  fieldname="JC_DATE">
											</td>
										</tr>
										<tr>
											<td nowrap="" align="right"><label for="P8024_BGQF_DATE"><span
													class="t3Optional">报告签发日期</span>
											</label>
											</td>
											<td style="width:150px;" colspan="1" rowspan="1" align="left"  fieldname="BGQF_DATE">
											</td>
											<td align="right"><label for="P8024_JCBG_YOUXIAO_DATE"><span
													class="t3Optional">检测报告有效期</span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"  fieldname="JCBG_YOUXIAO_DATE">
											</td>
										</tr>
										<tr>
											<td align="right"><label for="P8024_SYDJ_END_DATE"><span
													class="t3Optional">使用证办理截至日期</span>
											</label>
											</td>
											<td style="width:250px;" colspan="3" rowspan="1" align="left"  fieldname="SYDJ_END_DATE">
											</td>
										</tr>
										<tr>
											<td align="right"><label for="P8024_DESCRIPTION"><span
													class="t3Optional">备注</span>
											</label>
											</td>
											<td colspan="3" rowspan="1" align="left">
											<textarea name="p_t09" rows="4"  wrap="virtual" style="width: 97%"  fieldname="DESCRIPTION"
													id="P8024_DESCRIPTION" style="width:250px;" readonly="true"></textarea>
											</td>
										</tr>
										<tr>
											<td nowrap=""
												style="border-right:none;border-bottom:none;border-top:none;"
												align="right"><label for="P8024_JL_SHENHE_YJ"><span
													class="t3Optional">监理审核意见</span>
											</label>
											</td>
											<td colspan="3" rowspan="1" align="left"><input
												type="hidden" name="p_arg_names" value="16506326204140201">
											<textarea name="p_t10" rows="4" wrap="virtual"  fieldname="JL_SHENHE_YJ" style="width: 97%" readonly="readonly"
													id="P8024_JL_SHENHE_YJ" style="width:250px;"></textarea>
											</td>
										</tr>
									</tbody>
								</table></td>
						</tr>
					</tbody>
				</table>
			</form>
			
		</div>

		<script type="text/javascript" charset="utf-8">
		var controllername= "${pageContext.request.contextPath }/bzwj/jxsbAzgzController";
	$(document).ready(function(){
		 $("input:checkbox").attr("disabled","true");
	 $("#btnClose").click(function(){
			    window.close(); 
			    });
	 $("#btnSave").click(function(){
		 window.location.href = controllername + "/downloadJcys?uid="+<%=id %>;

												});
								init();

							});
			function init() {
				query();
				$("#modal-gallery").hide();
			}
			
			function query(){
				var queryconditionXX = "{\"logic\":\"and\",\"fieldname\":\"JXSB_JCYS_HIS_UID\",\"operation\":\"=\",\"value\":\""
					+
	<%=id%>
		+ "\",\"fieldtype\":'',\"fieldformat\":''}";
			var dataXX = "{querycondition: {conditions: ["
					+ queryconditionXX
					+ " ]},pages:{recordsperpage:200, currentpagenum:1, totalpage:0, countrows:0},orders:[{\"order\":\"desc\",\"fieldname\":\"CREATED_DATE\"}]}";
			var data = {
				msg : dataXX
			};
				$.ajax({
					url : controllername + "?queryJcys",
					data : data,
					cache : false,
					async : false,
					dataType : "json",
					type : 'post', 
					success : function(response) {			
						var resultobj = defaultJson.dealResultJson(response.msg);
						$("#azgzform").setHtmlValue(resultobj);
						$("#azgzform").setFormValues(resultobj);
						return true;
						}
				});
			}
			
			
			
		</script>

		<form class="form-inline" role="form" id="azgzQueryForm">
			<input type="hidden" id="JXSB_CXGZ_UID" fieldname="JXSB_CXGZ_UID"
				value="<%=id%>" operation="=" logic="and" />
		</form>
		<form id="gdzxtformid" method="post">
			<input type="hidden" name="gdzxt_gcid" id="gdzxt_gcid">
		</form>
</body>
</html>
