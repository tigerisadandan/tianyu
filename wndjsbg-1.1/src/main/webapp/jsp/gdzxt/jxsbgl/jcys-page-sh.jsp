<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%
	long ll = System.currentTimeMillis();
	String stuats = request.getParameter("STATUS");
%>

<!-- 
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/plugins/validform/css/form_validate.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/plugins/validform/css/form_validate_f.css" />

<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/fuelux/fuelux.wizard.min.js">
</script>
	<script src="${pageContext.request.contextPath }/js/project/Projectsunits22.js"></script>
<!-- 新增 修改 Modal  -->

<!--<div id="myModal" class="modal  fade" tabindex="-1" role="dialog" 
	aria-labelledby="myModalLabel" aria-hidden="true">   -->

<div class="modal-dialog width-65 height-aoto">
	<div class="modal-content">
		<div class="widget-header widget-header-large">
			<h3 id="dlixiangModalLabel" class="widget-title">起重设备检测管理   </h3>
			<div class="widget-toolbar">
				<a href="#" data-action="close" data-dismiss="modal"> <i
					class="ace-icon fa fa-times"></i> </a>
			</div>
		</div>
		<h3 align="center">起重设备检测管理</h3>
		<div class="modal-body" id="contentdivid" style="padding: 1px;">
        <div class="widget-box">
			<form action="wwv_flow.accept" method="post" name="wwv_flow" id="wwvFlowForm">
  <input type="hidden" name="p_flow_id" value="53500" id="pFlowId">  <input type="hidden" name="p_flow_step_id" value="8013" id="pFlowStepId">  <input type="hidden" name="p_instance" value="4459001321917698" id="pInstance">  <input type="hidden" name="p_page_submission_id" value="3980878267112314" id="pPageSubmissionId">  <input type="hidden" name="p_request" value="" id="pRequest"><div style="padding:10px;text-align:left;" valign="top"> 
  
<style>
.formlayout{border:none;}
.styleform{padding:5px 10px 5px 10px;}
.styleform table{border-collapse:collapse;}
.styleform table .radiogroup{border:none;}
.styleform table .radiogroup td{border:none;}
.styleform td{border:solid 1px #000000;}
.styleform td .noborder{border:none;}
.formtd{border:solid 1px #00000;}
</style>
<table class="t3RegionwithoutButtonsandTitles" cellspacing="0" border="0" summary="layout" id="R15723601909129273">
<tbody><tr>
<td valign="bottom" class="t3RegionHeader" style="text-align:center;"> </td>
</tr>

<tr class="t3Body">
<td colspan="2" class="styleform" style="border:1px solid #000000;padding:0px;"><table id="apex_layout_15723601909129273" class="formlayout" summary=""><tbody><tr><td align="right"><label for="P8013_SHEBEI_NAME"><span class="t3Optional">设备名称</span></label></td>
<td colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="15726207370129289"><input type="text" name="p_t01" size="40" maxlength="500" value="塔式起重机" id="P8013_SHEBEI_NAME" readonly="readonly"></td><td align="right"><label for="P8013_CQ_BH"><span class="t3Optional">产权编号</span></label></td>
<td colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="15726416419129289"><input type="text" name="p_t02" size="45" maxlength="100" value="wx-1" id="P8013_CQ_BH" readonly="readonly"></td></tr><tr><td align="right"><label for="P8013_JC_DW"><span class="t3Optional">检测单位</span></label></td>
<td colspan="3" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="15727408110129290"><input type="text" name="p_t03" size="60" maxlength="1000" value="" id="P8013_JC_DW" style="width:600px;" readonly="readonly"></td></tr><tr><td align="right"><label for="P8013_JCBG_BH"><span class="t3Optional">检测报告编号</span></label></td>
<td colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="15727617607129290"><input type="text" name="p_t04" size="40" maxlength="100" value="" id="P8013_JCBG_BH" readonly="readonly"></td><td align="right"><label for="P8013_JC_DATE"><span class="t3Optional">检测日期</span></label></td>
<td colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="15727801067129290"><input type="text" name="p_t05" size="45" maxlength="255" value="" id="P8013_JC_DATE" readonly="readonly"></td></tr><tr><td nowrap="" align="right"><label for="P8013_BGQF_DATE"><span class="t3Optional">报告签发日期</span></label></td>
<td colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="15724426635129281"><input type="text" name="p_t06" size="40" maxlength="2000" value="" id="P8013_BGQF_DATE" readonly="readonly"></td><td align="right"><label for="P8013_JCBG_YOUXIAO_DATE"><span class="t3Optional">检测报告有效期</span></label></td>
<td colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="15728000688129290"><input type="text" name="p_t07" size="45" maxlength="255" value="" id="P8013_JCBG_YOUXIAO_DATE" readonly="readonly"></td></tr><tr><td align="right"><label for="P8013_SYDJ_END_DATE"><span class="t3Optional">使用证办理截至日期</span></label></td>
<td colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="15728204837129290"><input type="text" name="p_t08" size="40" maxlength="255" value="2011/09/02" id="P8013_SYDJ_END_DATE" readonly="readonly"></td></tr><tr><td align="right"><label for="P8013_DESCRIPTION"><span class="t3Optional">备注</span></label></td>
<td colspan="3" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="15728401815129290"><textarea name="p_t09" rows="4" cols="60" wrap="virtual" id="P8013_DESCRIPTION" style="width:600px;" readonly="true">取得检测合格报告30天内办理使用证，检测合格满一年需重新检测</textarea></td></tr><tr><td nowrap="" style="border-right:none;border-bottom:none;border-top:none;" align="right"><label for="P8013_JL_STATUS"><span class="t3Optional"></span></label></td>
<td style="border-right:none;border-bottom:none;border-top:none;border-left:none;" onclick="chgselect(this.value);" colspan="3" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="15732802600159331"><fieldset id="P8013_JL_STATUS" class="radio_group"><table summary="" class="radiogroup"><tbody><tr>
<td nowrap="nowrap"><input type="radio" name="p_v10" value="31" checked="checked" id="P8013_JL_STATUS_0">
<label for="P8013_JL_STATUS_0">同意</label></td><td nowrap="nowrap"><input type="radio" name="p_v10" value="32" id="P8013_JL_STATUS_1">
<label for="P8013_JL_STATUS_1">不同意</label></td></tr></tbody></table></fieldset>
</td></tr><tr><td nowrap="" style="border-right:none;border-bottom:none;border-top:none;" align="right"><label for="P8013_JL_SHENHE_YJ"><span class="t3Optional">监理审核意见</span></label></td>
<td style="border-right:none;border-bottom:none;border-top:none;border-left:none;" onclick="chgselect(this.value);" colspan="3" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="15726603592129289"><textarea name="p_t11" rows="4" cols="60" wrap="virtual" id="P8013_JL_SHENHE_YJ" style="width:600px;">sss</textarea></td></tr>
</tbody></table>
</td>
</tr>
</tbody></table><table id="apex_layout_15723808499129278" class="formlayout" summary=""><tbody><tr><td></td><td colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="15725203154129287"><input type="hidden" name="p_t12" value="11089" id="P8013_ANZHUANG_GZ_UID"><input type="hidden" name="p_arg_names" value="15725408933129287"><input type="hidden" name="p_t13" value="2101" id="P8013_ZUZHI_GUANXI_UID"><input type="hidden" name="p_arg_names" value="15725601745129287"><input type="hidden" name="p_t14" value="1940" id="P8013_CREATE_BY"><input type="hidden" name="p_arg_names" value="15725829966129287"><input type="hidden" name="p_t15" value="20-6月 -11" id="P8013_CREATE_DATE"><input type="hidden" name="p_arg_names" value="15726030472129289"><input type="hidden" name="p_t16" value="31" id="P8013_STATUS"><input type="hidden" name="p_arg_names" value="15726829261129290"><input type="hidden" name="p_t17" value="" id="P8013_SHENHE_DATE"><input type="hidden" name="p_arg_names" value="15727002018129290"><input type="hidden" name="p_t18" value="" id="P8013_SHENHE_BY"><input type="hidden" name="p_arg_names" value="15727213061129290"><input type="hidden" name="p_t19" value="14" id="P8013_JXSB_UID"></td></tr><tr><td></td><td colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="15724625170129286"><input type="hidden" name="p_t20" value="" id="P8013_TAG"></td></tr><tr><td></td><td colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="15724830669129286"><input type="hidden" name="p_t21" value="11088" id="P8013_P_UID"></td></tr><tr><td></td><td colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="15725012736129287"><input type="hidden" name="p_t22" value="JC" id="P8013_JX_TYPE"></td><td></td><td colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="16474222155926336"><input type="hidden" name="p_t23" value="" id="P8013_PRINT_DISP"></td></tr>
</tbody></table>
</div>

<input type="hidden" name="p_md5_checksum" value="22EFEBE925967B7FB5B9862C8C8E460E"></form>
	    </div>
				
		</div>

		<div class="modal-footer">
			<button class="btn btn-success btn-sm" id="save">保存</button>
			<button class="btn btn-danger btn-sm pull-right" data-dismiss="modal"
				aria-hidden="true">关闭</button>
		</div>
	</div>
</div>
<!-- </div> -->

<script type="text/javascript">
var scripts = [null];

	ace.load_ajax_scripts(scripts, function() {
	//	var gridwidth=$("#contentdivid").width();
	//	JqgridManage.initJqgrid(content_grid_table,queryForm,gridwidth);

		//setStyle(xmxxtxformid);
		//DatePicker.datepickerid("#PF_DATE");
	});
	
	jQuery(function($) {
		setStyle($('form')); //字典
		$('[id=ZHIZHAO_VALID]').each(function() { //ace 时间
			var mydate = this;
			if (mydate.type !== 'date') {//if browser doesn't support "date" input
				$(mydate).datepicker({
					//options
					autoclose : true,
					todayHighlight : true,
					language : 'zh-CN',

				})
			}
		});

	});
</script>