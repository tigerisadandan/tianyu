<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp" %>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<%
	String id = request.getParameter("gcid");
String type = request.getParameter("openType");
%>

<!DOCTYPE html>
<html>
<head>

<base href="${ctx_site}/">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<title>脚手架工程申报表</title>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/ywlz/css/default.css"> </LINK>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>	
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/kindeditor/plugins/code/prettify.css" />
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/kindeditor/kindeditor.js"></script>
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/kindeditor/lang/zh_CN.js"></script>
	
</script>
<app:base />
<app:dialogs />
</head>

<body>
<form method="post" id="queryForm"  >
     		<input type="hidden" id="GC_UID" fieldname="GONGCHENG_UID" name="GONGCHENG_UID" operation="=" />
      </form>
	<div class="modal-dialog width-90 height-aoto">
		<form class="form-horizontal" id="wxysjkform">
		<input type="hidden" id="WXY_SJK_UID" fieldname="WXY_JSJ_UID"/>
			<div style="padding:10px;text-align:left;" valign="top">
				<table style="width: 702px;" class="content">
					<tbody>
					<tr height="41px">
							<td align="left" colspan="3" class="yw-title">
							<h4><span id="spyjtxhid">脚手架<span id="shstatus" ></span> </span>
								<span class="pull-right"> 
								<%
									String type1 = request.getParameter("openType");
									if(type1.equals("chakan")){//从查询全部危险源管理跳过来
									%>
									<button id="btnTuihui" class="btn" onclick="tuihui()" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">退回</button>
								<%
									}else{
								%>
								<button id="btnClose" class="btn" onclick="print()" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">打印</button>
	  							<%
									}
								%>
	  		<button id="btnSave" class="btn" onclick="col()" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
								</span>
								</h4></td>
						</tr>
						<tr>
							<td align="right">&nbsp;</td>
							<td align="left"><table>
									<tbody>
										<tr>
											<td align="right" style="width: 17%"><label for="FORM_CODE"><span
													class="t3Optional">重大危险源编号：</span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"><span
												fieldname="FORM_CODE" class="swidth"><span id="FORM_CODE"></span></span>
											</td>
										</tr>
										<tr>
											<td align="right"><label for="GC_NAME"><span
													class="t3Optional">工程名称</span>
											</label>
											</td>
											<td colspan="3" rowspan="1" align="left"><input
												type="text" fieldname="GC_NAME" style="width:450px;"
												readonly="readonly">
											</td>
										</tr>
										<tr>
											<td align="right"><label for="SG_NAME"><span
													class="t3Optional">（悬挑）脚手架高度	</span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"><input
												type="text" fieldname="JSJ_GD" readonly="readonly">
											</td>
										</tr>
									</tbody>
								</table></td>
							<td align="left">&nbsp;</td>
						</tr>
					</tbody>
				</table>
				<table style="width: 702px;" class="content">
					<tbody style="border: 1px;border-color: red">
						<tr height="41px">
						<td align="left" colspan="3" class="yw-title">
							<h4><span fieldname="spyjtxhid">单位及专家论证 </span>
								<span class="pull-right"> 
								</span>
								</h4></td>
						</tr>
						<tr>
							<td align="right">&nbsp;</td>
							<td align="left"><table fieldname="apex_layout_89663626999534478"
									class="formlayout" summary="">
									<tbody>
										<tr>
											<td align="right"><label for="ZJYJ_JB"><span
													class="t3Optional">专家论证意见<br>是否具备</span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"><input
												type="text" fieldname="ZJYJ_JB" class="lwidth"
												readonly="readonly">
											</td>
										</tr>
										<tr>
											<td align="right"><label for="ZJLZ_DATE"><span
													class="t3Optional">专家论证时间</span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"><input
												type="text" fieldname="ZJLZ_DATE" class="datewidth"
												readonly="true">
											</td>
											<td align="right"><label for="ZJYJ_TZ"><span
													class="t3Optional">专项施工方案<br>是否按专家<br>意见进行调整</span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"><input
												type="text" fieldname="ZJYJ_TZ" class="lwidth"
												readonly="readonly">
											</td>
										</tr>
										<tr>
											<td align="right"><label for="PLAN_B_DATE"><span
													class="t3Optional">计划开始实施时间</span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"><input
												type="text" fieldname="PLAN_B_DATE" class="datewidth"
												readonly="true">
											</td>
											<td align="right"><label for="PLAN_E_DATE"><span
													class="t3Optional">计划实施结束时间</span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"><input
												type="text" fieldname="PLAN_E_DATE" class="datewidth"
												readonly="true">
											</td>
										</tr>
										<tr>
											<td nowrap="nowrap" align="right"><label
												for="ZJ_NAME1"><span class="t3Optional">专家姓名</span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"><input
												type="text" fieldname="ZJ_NAME1" class="swidth"
												readonly="true">
											</td>
											<td nowrap="nowrap" align="right"><label
												for="ZJ_ZHICHEN1"><span class="t3Optional">职称</span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"><input
												type="text" fieldname="ZJ_ZHICHEN1" class="swidth"
												readonly="true">
											</td>
										</tr>
										<tr>
											<td nowrap="nowrap" align="right"><label
												for="ZJ_DANWEI1"><span class="t3Optional">单位</span>
											</label>
											</td>
											<td colspan="3" rowspan="1" align="left"><input
												type="text" fieldname="ZJ_DANWEI1" style="width:470px;"
												readonly="true">
											</td>
										</tr>
										<tr>
											<td nowrap="nowrap" align="right"><label
												for="ZJ_NAME2"><span class="t3Optional">专家姓名</span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"><input
												type="text" fieldname="ZJ_NAME2" class="swidth"
												readonly="true">
											</td>
											<td nowrap="nowrap" align="right"><label
												for="ZJ_ZHICHEN2"><span class="t3Optional">职称</span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"><input
												type="text" fieldname="ZJ_ZHICHEN2" class="swidth"
												readonly="true">
											</td>
										</tr>
										<tr>
											<td nowrap="nowrap" align="right"><label
												for="ZJ_DANWEI2"><span class="t3Optional">单位</span>
											</label>
											</td>
											<td colspan="3" rowspan="1" align="left"><input
												type="text" fieldname="ZJ_DANWEI2" style="width:470"
												readonly="true">
											</td>
										</tr>
										<tr>
											<td nowrap="nowrap" align="right"><label
												for="ZJ_NAME3"><span class="t3Optional">专家姓名</span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"><input
												type="text" fieldname="ZJ_NAME3" class="swidth"
												readonly="true">
											</td>
											<td nowrap="nowrap" align="right"><label
												for="ZJ_ZHICHEN3"><span class="t3Optional">职称</span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"><input
												type="text" fieldname="ZJ_ZHICHEN3" class="swidth"
												readonly="true">
											</td>
										</tr>
										<tr>
											<td nowrap="nowrap" align="right"><label
												for="ZJ_DANWEI3"><span class="t3Optional">单位</span>
											</label>
											</td>
											<td colspan="3" rowspan="1" align="left"><input
												type="text" fieldname="ZJ_DANWEI3" style="width:470"
												readonly="true">
											</td>
										</tr>
										<tr>
											<td nowrap="nowrap" align="right"><label
												for="ZJ_NAME4"><span class="t3Optional">专家姓名</span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"><input
												type="text" fieldname="ZJ_NAME4" class="swidth"
												readonly="true">
											</td>
											<td nowrap="nowrap" align="right"><label
												for="ZJ_ZHICHEN4"><span class="t3Optional">职称</span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"><input
												type="text" fieldname="ZJ_ZHICHEN4" class="swidth"
												readonly="true">
											</td>
										</tr>
										<tr>
											<td nowrap="nowrap" align="right"><label
												for="ZJ_DANWEI4"><span class="t3Optional">单位</span>
											</label>
											</td>
											<td colspan="3" rowspan="1" align="left"><input
												type="text" fieldname="ZJ_DANWEI4" style="width:470"
												readonly="true">
											</td>
										</tr>
										<tr>
											<td nowrap="nowrap" align="right"><label
												for="ZJ_NAME5"><span class="t3Optional">专家姓名</span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"><input
												type="text" fieldname="ZJ_NAME5" class="swidth"
												readonly="true">
											</td>
											<td nowrap="nowrap" align="right"><label
												for="ZJ_ZHICHEN5"><span class="t3Optional">职称</span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"><input
												type="text" fieldname="ZJ_ZHICHEN5" class="swidth"
												readonly="true">
											</td>
										</tr>
										<tr>
											<td nowrap="nowrap" align="right"><label
												for="ZJ_DANWEI5"><span class="t3Optional">单位</span>
											</label>
											</td>
											<td colspan="3" rowspan="1" align="left"><input
												type="text" fieldname="ZJ_DANWEI5" style="width:470"
												readonly="true">
											</td>
										</tr>
									</tbody>
								</table></td>
							<td align="left">&nbsp;</td>
						</tr>
						<tr>
							<td colspan="3"></td>
						</tr>
					</tbody>
				</table>
				
				<table  style="width: 702px;" class="content">
					<tbody>
					<tr height="41px">
					<td align="left" colspan="3" class="yw-title">
							<h4><span fieldname="spyjtxhid">吊装特种作业人员名单（含吊车司机、指挥工、司索工） </span>
								<span class="pull-right"> 
								</span>
								</h4></td>
						</tr>
						<tr>
							<td align="right"
								>&nbsp;</td>
							<td align="left"><table >
									<tbody>
										<tr>
											<td align="right"><label for="ZJYJ_JB"><span
													class="t3Optional">姓名</span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"><input style="width: 100px;"
												type="text" fieldname="XM1" class="lwidth"
												readonly="readonly">
											</td>
											<td align="right"><label for="ZJYJ_JB"><span
													class="t3Optional">工种</span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"><input  style="width: 150px;"
												type="text" fieldname="GZ1" class="lwidth"
												readonly="readonly">
											</td>
											<td align="right"><label for="ZJYJ_JB"><span
													class="t3Optional">资格证书编号</span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"><input
												type="text" fieldname="ZS1" class="lwidth"
												readonly="readonly">
											</td>
										</tr>
										<tr>
											<td align="right"><label for="ZJYJ_JB"><span
													class="t3Optional">姓名</span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"><input  style="width: 100px;"
												type="text" fieldname="XM2" class="lwidth"
												readonly="readonly">
											</td>
											<td align="right"><label for="ZJYJ_JB"><span
													class="t3Optional">工种</span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"><input  style="width: 150px;"
												type="text" fieldname="GZ2" class="lwidth"
												readonly="readonly">
											</td>
											<td align="right"><label for="ZJYJ_JB"><span
													class="t3Optional">资格证书编号</span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"><input
												type="text" fieldname="ZS2" class="lwidth"
												readonly="readonly">
											</td>
										</tr>
										<tr>
											<td align="right"><label for="ZJYJ_JB"><span
													class="t3Optional">姓名</span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"><input style="width: 100px;"
												type="text" fieldname="XM3" class="lwidth"
												readonly="readonly">
											</td>
											<td align="right"><label for="ZJYJ_JB"><span
													class="t3Optional">工种</span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"><input  style="width: 150px;"
												type="text" fieldname="GZ3" class="lwidth"
												readonly="readonly">
											</td>
											<td align="right"><label for="ZJYJ_JB"><span
													class="t3Optional">资格证书编号</span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"><input
												type="text" fieldname="ZS3" class="lwidth"
												readonly="readonly">
											</td>
										</tr>
										<tr>
											<td align="right"><label for="ZJYJ_JB"><span
													class="t3Optional">姓名</span>
											</label>
											</td> 
											<td colspan="1" rowspan="1" align="left"><input style="width: 100px;"
												type="text" fieldname="XM4" class="lwidth"
												readonly="readonly">
											</td>
											<td align="right"><label for="ZJYJ_JB"><span
													class="t3Optional">工种</span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"><input  style="width: 150px;"
												type="text" fieldname="GZ4" class="lwidth"
												readonly="readonly">
											</td>
											<td align="right"><label for="ZJYJ_JB"><span
													class="t3Optional">资格证书编号</span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"><input
												type="text" fieldname="ZS4" class="lwidth"
												readonly="readonly">
											</td>
										</tr>
									</tbody>
								</table>
							</td>
							<td align="left"
								>&nbsp;</td>
						</tr>
						<tr>
							<td colspan="3"
								></td>
						</tr>
					</tbody>
				</table>
				
				
				<table style="width: 702px;"  class="content">
					<tbody>
					<tr height="41px">
					<td align="left" colspan="3" class="yw-title">
							<h4><span fieldname="spyjtxhid">申报时应提交的资料(监理审核)</span>
								<span class="pull-right"> 
								</span>
								</h4></td>
						</tr>
						<tr>
							<td align="right">&nbsp;</td>
							<td align="left" style="width: 500px;">
							<table fieldname="apex_layout_89663028610534478"
									class="formlayout" summary="">
									<tbody>
										<tr>
											<td align="left"><label for="CAILIAO1"><span
													class="t3Optional"> 1、《无锡新区重大危险源申报表》一式2份 </span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"><input
												type="hidden" name="p_arg_names" value="89672003230534489">
											<fieldset fieldname="CAILIAO1" class="checkbox_group">
													<input type="checkbox" name="p_v33" value="1"
														disabled="true" fieldname="CAILIAO1">
												</fieldset></td>
										</tr>
										<tr>
											<td align="left"><label for="CAILIAO2"><span
													class="t3Optional"> 2、提供经总包单位、监理单位审批的专项施工方案</span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"><input
												type="hidden" name="p_arg_names" value="89672203412534489">
											<fieldset fieldname="CAILIAO2" class="checkbox_group">
													<input type="checkbox" name="p_v34" value="1"
														disabled="true" fieldname="CAILIAO2"><label
														for="CAILIAO2_0"> </label>
												</fieldset></td>
										</tr>
										<tr>
											<td align="left"><label for="CAILIAO3"><span
													class="t3Optional"> 3、提供专家论证意见文稿及签到名单（达到专家论证规模需专家论证时填写）</span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"><input
												type="hidden" name="p_arg_names" value="89672423909534489">
											<fieldset fieldname="CAILIAO3" class="checkbox_group">
													<input type="checkbox" name="p_v35" value="1"
														disabled="true" fieldname="CAILIAO3"><label
														for="CAILIAO3_0"> </label>
												</fieldset></td>
										</tr>
										<tr>
											<td align="left"><label for="CAILIAO4"><span
													class="t3Optional"> 4、提供经总包单位、监理单位审批的实施过程事故应急救援预案</span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"><input
												type="hidden" name="p_arg_names" value="89672629466534489">
											<fieldset fieldname="CAILIAO4" class="checkbox_group">
													<input type="checkbox" name="p_v36" value="1"
														disabled="true" fieldname="CAILIAO4"><label
														for="CAILIAO4_0"> </label>
												</fieldset></td>
										</tr>
										<tr>
											<td align="left"><label for="CAILIAO5"><span
													class="t3Optional"> 5、专项安全技术交底原件</span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"><input
												type="hidden" name="p_arg_names" value="89672816942534490">
											<fieldset fieldname="CAILIAO5" class="checkbox_group">
													<input type="checkbox" name="p_v37" value="1"
														disabled="true" fieldname="CAILIAO5"><label
														for="CAILIAO5_0"> </label>
												</fieldset></td>
										</tr>
										
									</tbody>
								</table></td>
							<td align="left">&nbsp;</td>
						</tr>
						<tr>
							<td colspan="3"></td>
						</tr>
					</tbody>
				</table>
				
				<table  style="width: 702px;" class="content">
					<tbody>
					<tr height="41px">
					<td align="left" colspan="3" class="yw-title">
							<h4><span fieldname="spyjtxhid">审核意见 </span>
								<span class="pull-right"> 
								</span>
								</h4></td>
						</tr>
						<tr>
							<td align="right"
								>&nbsp;</td>
							<td align="left"><table fieldname="apex_layout_89663203839534478"
									class="formlayout" summary="">
									<tbody>
										<tr>
											<td align="right"><label for="SG_YJ"><span
													class="t3Optional">总承包单位审核意见</span></label>
											</td>
											<td colspan="3" rowspan="1" align="left"><input
												type="hidden" name="p_arg_names" value="89668816982534486">
											<textarea name="p_t45" rows="4" cols="60" wrap="virtual" style="width: 97%"
													fieldname="SG_YJ" class="textareawidth" readonly="true">dsaerw</textarea>
											</td>
										</tr>
										<tr>
											<td align="right"><label for="XMJL_NAME"><span
													class="t3Optional">项目经理</span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"><input
												type="hidden" name="p_arg_names" value="89669201816534486"><input
												type="text" name="p_t46" size="32" maxlength="100"
												value="tag" fieldname="XMJL_NAME" class="swidth"
												readonly="readonly">
											</td>
											<td align="right"><label for="CREATE_DATE"><span
													class="t3Optional">审核时间</span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"><input
												type="hidden" name="p_arg_names" value="89669003438534486"><input
												type="text" name="p_t47" size="32" maxlength="255"
												value="2011/12/01" fieldname="CREATE_DATE" class="datewidth"
												readonly="true">
											</td>
										</tr>
										<tr>
											<td align="right"><label for="JL_YJ"><span
													class="t3Optional">监理单位审核意见</span>
											</label>
											</td>
											<td colspan="3" rowspan="1" align="left"><input
												type="hidden" name="p_arg_names" value="89669406732534486">
											<textarea name="p_t48" rows="4" cols="60" wrap="virtual" style="width: 97%"
													fieldname="JL_YJ" class="textareawidth" readonly="true">dsaf</textarea>
											</td>
										</tr>
										<tr>
											<td align="right"><label for="ZJ_NAME"><span
													class="t3Optional">总监</span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"><input
												type="hidden" name="p_arg_names" value="89669610473534486"><input
												type="text" name="p_t49" size="32" maxlength="100"
												value="张三" fieldname="ZJ_NAME" class="swidth"
												readonly="readonly">
											</td>
											<td align="right"><label for="SHENHE_DATE"><span
													class="t3Optional">审核时间</span>
											</label>
											</td>
											<td colspan="1" rowspan="1" align="left"><input
												type="hidden" name="p_arg_names" value="89669821355534489"><input
												type="text" name="p_t50" size="32" maxlength="255"
												value="2011/12/02" fieldname="SHENHE_DATE" class="datewidth"
												readonly="readonly">
											</td>
										</tr>
									</tbody>
								</table></td>
							<td align="left"
								>&nbsp;</td>
						</tr>
						<tr>
							<td colspan="3"
								></td>
						</tr>
					</tbody>
				</table>
				
			</div>
		</form>
	</div>
<div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id = "queryXML">
         <input type="hidden" name="txtXML" id = "txtXML">
         <input type="hidden" name="resultXML" id = "resultXML">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
		
 	</FORM>
 </div>
	<script type="text/javascript">
		var validform;
		var gcid =
	<%=id%>
		;
		var type = "insert";
		
		$(function() {
			$("#GC_UID").val(gcid);
			//validform = FormValid.validbyformid(wxysjkform);//验证
			init();
			  if('<%=type %>'=="print"){
		        	print();
		        }
		});
		function print(){
			window.location.href = "${pageContext.request.contextPath }/wxy/wxyJsjController/download?uid="+$("#WXY_SJK_UID").val();
		}
		function init() {
			query();
		}

		function query() {
			var data = combineQuery.getQueryCombineData(queryForm, frmPost);
			var data1 = {
				msg : data
			};
			$.ajax({
				        url : "${pageContext.request.contextPath }/wxy/wxyJsjController?query",
						data : data1,
						cache : false,
						async : false,
						dataType : "json",
						type : 'post',
						success : function(response) {
							if (response.msg != "0") {
								type = "update";
								var resultobj = defaultJson
										.dealResultJson(response.msg);
								$("#wxysjkform").setFormValues(resultobj);
								$("#FORM_CODE").html(resultobj.FORM_CODE);
								$("#WXYBH_HTML").html(resultobj.FORM_CODE);
								if (resultobj.STATUS == "33"
										|| resultobj.STATUS == "30") { //查看不可修改
									$("#wxysjkform")
											.find("input,textarea")
											.each(
													function(i, item) {
														$(item).attr(
																'readonly',
																'readonly');
														if ($(item).attr('id') == "DATE") {
															$(item).attr('id',
																	'');
														}
													});
									$("#save").hide();
									$("#sum").hide();
								}
								if (resultobj.STATUS == "0") {
									$("#shstatus").html("(未提交)");
								} else if (resultobj.STATUS == "30") {
									$("#shstatus").html("(已提交)");
								} else if (resultobj.STATUS == "33") {
									$("#shstatus").html("(已审核)");
								} else if (resultobj.STATUS == "32") {
									$("#shstatus").html("(未通过)");
									$("#tjmsg").html("重新提交");
								}
								return;
							}
						}
					});
		}

		function save() { //azgz add  
			var YXQ_BEGIN = document.getElementsByName("PLAN_B_DATE")[0].value;
			var YXQ_END = document.getElementsByName("PLAN_E_DATE")[0].value;
			if (YXQ_END < YXQ_BEGIN) {
				bootbox.alert("开始日期不能大 于结束日期！");
			} else {
				if (validform.check()) {
					bootbox
							.confirm(
									"请核对信息无误后保存！",
									function(result) {
										if (result) {
											if (type == "insert") {
												$("#SET_STATUS").val(0);
												var data = Form2Json
														.formToJSON(wxysjkform);
												//组成保存json串格式
												var data1 = defaultJson
														.packSaveJson(data);
												//调用ajax插入
												defaultJson
														.doInsertJson(
																"${pageContext.request.contextPath }/wxy/wxySjkController?insert",
																data1);
											} else if (type == "update") {
												var data = Form2Json
														.formToJSON(wxysjkform);
												//组成保存json串格式
												var data1 = defaultJson
														.packSaveJson(data);
												defaultJson
														.doInsertJson(
																"${pageContext.request.contextPath }/wxy/wxySjkController?update",
																data1);
											}
											query();
											var parent = window.opener;
											if (parent != null) {
												parent.initSX();
											}
										}
									});

				}
			}
		}

		function col() {
			window.close();
		}

		function sum() {
			var YXQ_BEGIN = document.getElementsByName("PLAN_B_DATE")[0].value;
			var YXQ_END = document.getElementsByName("PLAN_E_DATE")[0].value;
			if (YXQ_END < YXQ_BEGIN) {
				bootbox.alert("开始日期不能大 于结束日期！");
			} else {
				if (validform.check()) {
					bootbox
							.confirm(
									"请核对信息无误后提交！",
									function(result) {
										if (result) {
											$("#SET_STATUS").val(30);
											var data = Form2Json
													.formToJSON(wxysjkform);
											//组成保存json串格式
											var data1 = defaultJson
													.packSaveJson(data);
											if (type == "null") {
												//调用ajax插入
												defaultJson
														.doInsertJson(
																"${pageContext.request.contextPath }/wxy/wxySjkController?insert",
																data1);
											} else if (type == "update") {
												defaultJson
														.doInsertJson(
																"${pageContext.request.contextPath }/wxy/wxySjkController?update",
																data1);
											}
											query();
											var parent = window.opener;
											if (parent != null) {
												parent.initSX();
											}
										}
									});
				}
			}
		}
		//改变状态为退回
		function tuihui(){
				xConfirm("信息确认","确认退回？");
				$('#ConfirmYesButton').attr('click','');
				$('#ConfirmYesButton').one("click",function(){
					var gcId = $("#GC_UID").val();
					$.ajax({
					url : "${pageContext.request.contextPath }/wxy/wxyJsjController?tuiHui",
					data : {"gcId":gcId},
					type : "post",
					success : function(response){
						 window.opener.init();
						 window.close();
					}
				});
			});
		}
	</script>
</body>
</html>
