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
			<h3 id="dlixiangModalLabel" class="widget-title">建筑施工起重机械设备拆卸过程审核 </h3>
			<div class="widget-toolbar">
				<a href="#" data-action="close" data-dismiss="modal"> <i
					class="ace-icon fa fa-times"></i> </a>
			</div>
		</div>
		<h3 align="center">起重设备拆卸过程管理审核表</h3>
		<div class="modal-body" id="contentdivid" style="padding: 1px;">
        <div class="widget-box">
			<form action="wwv_flow.accept" method="post" name="wwv_flow" id="wwvFlowForm">
  <input type="hidden" name="p_flow_id" value="53500" id="pFlowId">  <input type="hidden" name="p_flow_step_id" value="8014" id="pFlowStepId">  <input type="hidden" name="p_instance" value="4459001321917698" id="pInstance">  <input type="hidden" name="p_page_submission_id" value="4295323052054394" id="pPageSubmissionId">  <input type="hidden" name="p_request" value="" id="pRequest"><style>
.tarea{width:620px;}
.right{width:250px;}
.left{width:250px;}
.date{width:80px;}
</style><div style="padding:10px;text-align:left;" valign="top"> 
  
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
<table class="t3RegionwithoutButtonsandTitles" cellspacing="0" border="0" summary="layout" id="R16351111262295262">
<tbody><tr>
<td valign="bottom" class="t3RegionHeader" style="text-align:center;"> </td>
</tr>

<tr class="t3Body">
<td colspan="2" class="styleform" style="border:1px solid #000000;padding:0px;"><table id="apex_layout_16351111262295262" class="formlayout" summary=""><tbody><tr><td nowrap="" align="right"><label for="P8014_SHEBEI_NAME"><span class="t3Optional">设备名称</span></label></td>
<td colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="16355511070295275"><input type="text" name="p_t01" size="30" maxlength="2000" value="塔式起重机" id="P8014_SHEBEI_NAME" class="left" readonly="readonly"></td><td nowrap="" align="right"><label for="P8014_CQ_BH"><span class="t3Optional">产权编号</span></label></td>
<td colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="16355711528295275"><input type="text" name="p_t02" size="30" maxlength="2000" value="wx-1" id="P8014_CQ_BH" class="right" readonly="readonly"></td></tr><tr><td align="right"><label for="P8014_AZCX_BEGIN_DATE"><span class="t3Optional">拆卸开始时间</span></label></td>
<td colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="16354531635295273"><input type="text" name="p_t03" size="32" maxlength="255" value="2013/05/16" id="P8014_AZCX_BEGIN_DATE" class="left" readonly="readonly"></td><td align="right"><label for="P8014_AZCX_END_DATE"><span class="t3Optional">拆卸结束时间</span></label></td>
<td colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="16354702709295273"><input type="text" name="p_t04" size="32" maxlength="255" value="2013/05/19" id="P8014_AZCX_END_DATE" class="right" readonly="readonly"></td></tr><tr><td align="right"><label for="P8014_AZCX_AQJD_QK"><span class="t3Optional">拆卸前安全交底情况</span></label></td>
<td colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="16355123421295275"><input type="text" name="p_t05" size="60" maxlength="1000" value="已交底" id="P8014_AZCX_AQJD_QK" class="right" readonly="readonly"></td><td align="right"><label for="P8014_AZCX_CZSG_QK"><span class="t3Optional">拆卸过程持证上岗情况</span></label></td>
<td colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="16355924510295275"><input type="text" name="p_t06" size="60" maxlength="1000" value="齐全" id="P8014_AZCX_CZSG_QK" class="right" readonly="readonly"></td></tr><tr><td align="right"><label for="P8014_AZCX_GZQQ"><span class="t3Optional">工种是否齐全</span></label></td>
<td colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="16356102024295275"><input type="text" name="p_t07" size="60" maxlength="1000" value="是" id="P8014_AZCX_GZQQ" class="right" readonly="readonly"></td></tr><tr><td align="right"><label for="P8014_AZCX_JJSZ_QK"><span class="t3Optional">警戒线、警戒标志设置情况</span></label></td>
<td colspan="3" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="16356302273295275"><textarea name="p_t08" rows="4" cols="60" wrap="virtual" id="P8014_AZCX_JJSZ_QK" class="tarea" readonly="true">设置</textarea></td></tr><tr><td align="right"><label for="P8014_AZCX_AQYP_QK"><span class="t3Optional">拆卸过程安全用品配备情况</span></label></td>
<td colspan="3" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="16356523301295275"><textarea name="p_t09" rows="4" cols="60" wrap="virtual" id="P8014_AZCX_AQYP_QK" class="tarea" readonly="true">配备齐全</textarea></td></tr><tr><td align="right"><label for="P8014_AZCX_AQYJD_QK"><span class="t3Optional">拆卸过程安全员<br>全过程监督情况</span></label></td>
<td colspan="3" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="16357926430295276"><textarea name="p_t10" rows="4" cols="60" wrap="virtual" id="P8014_AZCX_AQYJD_QK" class="tarea" readonly="true">有</textarea></td></tr><tr><td align="right"><label for="P8014_AZCX_CPH"><span class="t3Optional">汽车吊牌号</span></label></td>
<td colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="16357328440295276"><input type="text" name="p_t11" size="60" maxlength="1000" value="２６４７６４" id="P8014_AZCX_CPH" class="left" readonly="readonly"></td><td align="right"><label for="P8014_AZCX_JGNJZM"><span class="t3Optional">汽车吊起重<br>机构年检证明</span></label></td>
<td colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="16357511280295276"><input type="text" name="p_t12" size="60" maxlength="1000" value="有" id="P8014_AZCX_JGNJZM" class="right" readonly="readonly"></td></tr><tr><td nowrap="" style="border-right:none;border-bottom:none;border-top:none;" align="right"><label for="P8014_JL_STATUS"><span class="t3Optional"></span></label></td>
<td style="border-right:none;border-bottom:none;border-top:none;border-left:none;" colspan="3" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="16356928246295276"><fieldset id="P8014_JL_STATUS" class="radio_group"><table summary="" class="radiogroup"><tbody><tr>
<td nowrap="nowrap"><input type="radio" name="p_v13" value="31" checked="checked" onclick="chgselect(this.value);" id="P8014_JL_STATUS_0">
<label for="P8014_JL_STATUS_0">同意</label></td><td nowrap="nowrap"><input type="radio" name="p_v13" value="32" onclick="chgselect(this.value);" id="P8014_JL_STATUS_1">
<label for="P8014_JL_STATUS_1">不同意</label></td></tr></tbody></table></fieldset>
</td></tr><tr><td nowrap="" style="border-right:none;border-bottom:none;border-top:none;" align="right"><label for="P8014_JL_SHENHE_YJ"><span class="t3Optional">审核意见</span></label></td>
<td style="border-right:none;border-bottom:none;border-top:none;border-left:none;" colspan="3" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="16358331621295276"><textarea name="p_t14" rows="5" cols="80" wrap="virtual" id="P8014_JL_SHENHE_YJ" class="tarea"></textarea></td></tr>
</tbody></table>
</td>
</tr>
</tbody></table><table id="apex_layout_16351306563295265" class="formlayout" summary=""><tbody><tr><td></td><td colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="16352727843295272"><input type="hidden" name="p_t15" value="24318" id="P8014_ANZHUANG_GZ_UID"><input type="hidden" name="p_arg_names" value="16352322283295268"><input type="hidden" name="p_t16" value="2101" id="P8014_ZUZHI_GUANXI_UID"><input type="hidden" name="p_arg_names" value="16352516188295272"><input type="hidden" name="p_t17" value="1940" id="P8014_CREATE_BY"><input type="hidden" name="p_arg_names" value="16352903126295273"><input type="hidden" name="p_t18" value="15-5月 -13" id="P8014_CREATE_DATE"><input type="hidden" name="p_arg_names" value="16356700250295275"><input type="hidden" name="p_t19" value="40" id="P8014_STATUS"><input type="hidden" name="p_arg_names" value="16353129057295273"><input type="hidden" name="p_t20" value="CXGC" id="P8014_JX_TYPE"><input type="hidden" name="p_arg_names" value="16353315142295273"><input type="hidden" name="p_t21" value="19572" id="P8014_P_UID"><input type="hidden" name="p_arg_names" value="16353513020295273"><input type="hidden" name="p_t22" value="" id="P8014_SHENHE_DATE"><input type="hidden" name="p_arg_names" value="16353724400295273"><input type="hidden" name="p_t23" value="" id="P8014_SHENHE_BY"><input type="hidden" name="p_arg_names" value="16353903433295273"><input type="hidden" name="p_t24" value="14" id="P8014_JXSB_UID"><input type="hidden" name="p_arg_names" value="16354125200295273"><input type="hidden" name="p_t25" value="" id="P8014_AZ_COMPANYS_DL_UID"><input type="hidden" name="p_arg_names" value="16354330840295273"><input type="hidden" name="p_t26" value="" id="P8014_CZ_REN_UID"></td></tr><tr><td></td><td colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="16355301581295275"><input type="hidden" name="p_t27" value="" id="P8014_TAG"></td></tr><tr><td></td><td colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="16357114937295276"><input type="hidden" name="p_t28" value="" id="P8014_SHBACK"></td><td></td><td colspan="1" rowspan="1" align="left"><input type="hidden" name="p_arg_names" value="16476017700953401"><input type="hidden" name="p_t29" value="" id="P8014_PRINT_DISP"></td></tr>
</tbody></table>
</div>

<input type="hidden" name="p_md5_checksum" value="D3E1226AA0BBD21521DFF780AA1D2C1D"></form>
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