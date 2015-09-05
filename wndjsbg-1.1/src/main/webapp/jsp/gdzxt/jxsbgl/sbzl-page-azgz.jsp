<%@page import="com.ccthanking.business.sggc.GongCheng"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%
	long ll = System.currentTimeMillis();
	String type = request.getParameter("type");
	String id = request.getParameter("id");
	String gcid = GongCheng.getGcid();
%>

<div class="modal-dialog width-65 height-aoto">
	<div class="modal-content">
		<div class="widget-header widget-header-large">
			<h3 id="dlixiangModalLabel" class="widget-title">安装告知</h3>
			<div class="widget-toolbar">
				<a href="#" data-action="close" data-dismiss="modal" id="clo"> <i
					class="ace-icon fa fa-times"></i> </a>
			</div>
		</div>

		<div class="modal-body" id="contentdivid" style="padding: 1px;">
			<form class="form-horizontal" id="azgzform">
				<input type="hidden" fieldname="JXSB_SYGL_UID"> <input
					type="hidden" fieldname="JXSB_AZGZ_UID"> <input
					type="hidden" fieldname="GONGCHENG_UID" value="<%=gcid%>">
				<input id="SSHENHE_STATUS" type="hidden" fieldname="SHENHE_STATUS"
					value="0"> <input id="JXSB_UID" type="hidden"
					fieldname="JXSB_UID" name="JXSB_UID" readonly="readonly"> <input
					id="BY_COMPANY_UID" type="hidden" fieldname="BY_COMPANY_UID"
					name="BY_COMPANY_UID"> <input id="AZ_COMPANY_UID"
					type="hidden" fieldname="AZ_COMPANY_UID" name="AZ_COMPANY_UID">
				<div class="widget-box">
					<div class="widget-header widget-header-flat">
						<h4 class="widget-title smaller">起重机械设备</h4>
						<!-- 
														<div class="widget-toolbar">
															
																<small class="green">
									                               <button class="btn btn-primary" 
									                                  style="height: 35px;margin-bottom: 2px;">关闭</button>
									                                <button class="btn btn-primary" 
									                                  style="height: 35px;margin-bottom: 2px;">保存</button>
																</small>

														</div>
														 -->
					</div>
					<div class="widget-body">
						<div class="widget-main">
							<div class="form-horizontal" id="sbform">
								<!-- #section:elements.form.input-state -->
								<div class="form-group"
									style="margin: 0;line-height: 20px;font-size: 16px;">
									<label style="color: #667E99;" for="inputWarning"
										class="col-xs-12 col-sm-2 control-label no-padding-right">设备名称</label>
									<div class="col-xs-12 col-sm-4">
										<input id="JXSB_TYPE_UID_SV" style="width: 90%" type="text"
											fieldname="SHEBEI_NAME" name="SHEBEI_NAME" datatype="*"
											nullmsg="请选择机械设备！" errormsg="" placeholder="请选择机械设备"
											readonly="readonly">
										<div class="info">
											<span class="Validform_checktip"></span><span class="dec"><s
												class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
										</div>
										<a title="选择" href="javascript:;" style="width: 10%"
											onclick="checkSb()"><i
											class="#ace-icon fa fa-list-ol bigger-110"></i> </a>
									</div>
									<label style="color: #667E99;" for="inputWarning"
										class="col-xs-12 col-sm-2 control-label no-padding-right">制造许可证号</label>
									<div class="col-xs-12 col-sm-4">
										<input id="ZZXKZ" style="width: 90%" type="text"
											fieldname="XKZHAO" name="XKZHAO" readonly="readonly">
										<div class="info">
											<span class="Validform_checktip"></span><span class="dec"><s
												class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
										</div>
									</div>
								</div>
								<div style="margin: 2px;"></div>
								<div class="form-group"
									style="margin: 0;line-height: 20px;font-size: 16px;">
									<label style="color: #667E99;" for="inputWarning"
										class="col-xs-12 col-sm-2 control-label no-padding-right">制造厂家</label>
									<div class="col-xs-12 col-sm-4">
										<input id="ZZDW" style="width: 90%" type="text"
											fieldname="ZZCJ" name="ZZCJ" readonly="readonly">
										<div class="info">
											<span class="Validform_checktip"></span><span class="dec"><s
												class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
										</div>
									</div>
									<label style="color: #667E99;" for="inputWarning"
										class="col-xs-12 col-sm-2 control-label no-padding-right">规格型号</label>
									<div class="col-xs-12 col-sm-4">
										<input id="SB_XH" style="width: 90%" type="text"
											fieldname="GGXH" name="GGXH" readonly="readonly">
										<div class="info">
											<span class="Validform_checktip"></span><span class="dec"><s
												class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
										</div>
									</div>
								</div>
								<div style="margin: 2px;"></div>
								<div class="form-group"
									style="margin: 0;line-height: 15px;font-size: 14px;">
									<label style="color: #667E99;" for="inputWarning"
										class="col-xs-12 col-sm-2 control-label no-padding-right">产品合格证号</label>
									<div class="col-xs-12 col-sm-4">
										<input id="HGZH" style="width: 90%" type="text"
											fieldname="HGZH" name="HGZH" readonly="readonly">
										<div class="info">
											<span class="Validform_checktip"></span><span class="dec"><s
												class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
										</div>
									</div>
									<label style="color: #667E99;" for="inputWarning"
										class="col-xs-12 col-sm-2 control-label no-padding-right">出厂日期</label>
									<div class="col-xs-12 col-sm-4">
										<input id="CC_DATE" style="width: 90%" type="text"
											fieldname="CHUCHANG_DATE" name="CHUCHANG_DATE"
											readonly="readonly">
										<div class="info">
											<span class="Validform_checktip"></span><span class="dec"><s
												class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
										</div>
									</div>
								</div>
								<div style="margin: 2px;"></div>
								<div class="form-group"
									style="margin: 0;line-height: 20px;font-size: 16px;">
									<label style="color: #667E99;" for="inputWarning"
										class="col-xs-12 col-sm-2 control-label no-padding-right">设备产权单位</label>
									<div class="col-xs-12 col-sm-4">
										<input id="CQDW" style="width: 90%" type="text"
											fieldname="CQ_DANWEI" name="CQ_DANWEI" readonly="readonly">
										<div class="info">
											<span class="Validform_checktip"></span><span class="dec"><s
												class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
										</div>
									</div>
									<label style="color: #667E99;" for="inputWarning"
										class="col-xs-12 col-sm-2 control-label no-padding-right">产权编号</label>
									<div class="col-xs-12 col-sm-4">
										<input id="CQBH" style="width: 90%" type="text"
											fieldname="CQ_BH" name="CQ_BH" readonly="readonly">
										<div class="info">
											<span class="Validform_checktip"></span><span class="dec"><s
												class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
										</div>
									</div>
								</div>
								<div style="margin: 2px;"></div>
								<div class="form-group"
									style="margin: 0;line-height: 20px;font-size: 16px;">
									<label style="color: #667E99;" for="inputWarning"
										class="col-xs-12 col-sm-2 control-label no-padding-right">设备租赁行业确认书编号</label>
									<div class="col-xs-12 col-sm-8">
										<input id="SBZLQRS_CODE" style="width: 43%" type="text"
											datatype="n-a" nullmsg="请输入租赁行业确认书编号！" errormsg=""
											placeholder="请输入租赁行业确认书编号" maxlength="200"
											fieldname="SBZLQRS_CODE" name="SBZLQRS_CODE"> <label
											style="color: #667E99;"
											class=" control-label no-padding-right">
											(非使用单位产权，由产权单位提供)</label>
										<div class="info">
											<span class="Validform_checktip"></span><span class="dec"><s
												class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					</div>
					
					<div class="widget-box">
					<div class="widget-header widget-header-flat">
						<h4 class="widget-title smaller">安装单位及转场保养单位</h4>

					</div>
					<div class="widget-body">
						<div class="widget-main">
							<div class="form-horizontal" id="sample-form">
								<!-- #section:elements.form.input-state -->
								<div class="form-group"
									style="margin: 0;line-height: 15px;font-size: 14px;">
									<label style="color: #667E99;" for="inputWarning"
										class="col-xs-12 col-sm-2 control-label no-padding-right">转场保养单位</label>
									<div class="col-xs-12 col-sm-4">
										<input id="COMPANY_NAME" style="width: 90%" type="text"
											fieldname="ZCBY_DANWEI" name="ZCBY_DANWEI" datatype="*"
											nullmsg="请选择保养单位！" errormsg="" placeholder="请选择保养单位"
											readonly="readonly">
										<div class="info">
											<span class="Validform_checktip"></span><span class="dec"><s
												class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
										</div>
										<a title="选择" href="javascript:;" style="width: 10%"
											onclick="baoYang()"><i
											class="#ace-icon fa fa-list-ol bigger-110"></i> </a>
									</div>
									<label style="color: #667E99;padding-left: 0px"
										for="inputWarning"
										class="col-xs-12 col-sm-2 control-label no-padding-right">无锡市信用手册编号</label>
									<div class="col-xs-12 col-sm-4">
										<input id="XYSC_CODE" style="width: 90%" type="text"
											readonly="readonly" fieldname="XYSC_CODE" name="XYSC_CODE">
										<div class="info">
											<span class="Validform_checktip"></span><span class="dec"><s
												class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
										</div>
									</div>
								</div>

								<div style="margin: 1px;"></div>
								<div class="form-group"
									style="margin: 0;line-height: 15px;font-size: 14px;">
									<label style="color: #667E99;" for="inputWarning"
										class="col-xs-12 col-sm-2 control-label no-padding-right">设备安装单位</label>
									<div class="col-xs-12 col-sm-4">
										<input id="AZ_DANWEI" style="width: 90%" type="text"
											datatype="*" nullmsg="请选择安装单位！" errormsg=""
											placeholder="请选择安装单位" fieldname="AZ_DANWEI" name="AZ_DANWEI"
											readonly="readonly">
										<div class="info">
											<span class="Validform_checktip"></span><span class="dec"><s
												class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
										</div>
										<a title="选择" href="javascript:;" style="width: 10%"
											onclick="anZhuang()"><i
											class="#ace-icon fa fa-list-ol bigger-110"></i> </a>
									</div>
									<label style="color: #667E99;" for="inputWarning"
										class="col-xs-12 col-sm-2 control-label no-padding-right">资质证书编号</label>
									<div class="col-xs-12 col-sm-4">
										<input id="ZZBH" style="width: 90%" type="text"
											maxlength="100" readonly="readonly" fieldname="ZZBH"
											name="ZZBH">
										<div class="info">
											<span class="Validform_checktip"></span><span class="dec"><s
												class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
										</div>
									</div>
								</div>
								<div style="margin: 2px;"></div>
								<div class="form-group"
									style="margin: 0;line-height: 15px;font-size: 14px;">
									<label style="color: #667E99;padding-left: 0px"
										for="inputWarning"
										class="col-xs-12 col-sm-2 control-label no-padding-right">生产安全许可证编号</label>
									<div class="col-xs-12 col-sm-4">
										<input id="AQSCBH" style="width: 90%" type="text"
											maxlength="100" readonly="readonly" fieldname="AQSCBH"
											name="AQSCBH">
										<div class="info">
											<span class="Validform_checktip"></span><span class="dec"><s
												class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
										</div>
									</div>
									<label style="color: #667E99;" for="inputWarning"
										class="col-xs-12 col-sm-2 control-label no-padding-right">设备拟安装日期从</label>
									<div class="col-xs-12 col-sm-4">
										<input id="DATE" style="width: 40%" type="text"
											fieldname="SBAZ_DATE" name="SBAZ_DATE" readonly="readonly"
											datatype="*" nullmsg="请选择日期！" errormsg="" placeholder="请选择日期"
											data-date-format="yyyy-mm-dd">
										<div class="info">
											<span class="Validform_checktip"></span><span class="dec"><s
												class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
										</div>
										至 <input id="DATE" style="width: 40%" type="text"
											fieldname="SBAZ_E_DATE" name="SBAZ_E_DATE"
											readonly="readonly" datatype="*" nullmsg="请选择日期！" errormsg=""
											placeholder="请选择日期" data-date-format="yyyy-mm-dd">
										<div class="info">
											<span class="Validform_checktip"></span><span class="dec"><s
												class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
										</div>
									</div>
								</div>
								<div style="margin: 2px;"></div>
								<div class="form-group"
									style="margin: 0;line-height: 15px;font-size: 14px;">
									<label style="color: #667E99;" for="inputWarning"
										class="col-xs-12 col-sm-2 control-label no-padding-right">安装自检日期</label>
									<div class="col-xs-12 col-sm-4">
										<input id="DATE" style="width: 90%" type="text"
											fieldname="AZZJ_DATE" name="AZZJ_DATE" readonly="readonly"
											datatype="*" nullmsg="请选择日期！" errormsg="" placeholder="请选择日期"
											data-date-format="yyyy-mm-dd">
										<div class="info">
											<span class="Validform_checktip"></span><span class="dec"><s
												class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
										</div>
									</div>
									<label style="color: #667E99;" for="inputWarning"
										class="col-xs-12 col-sm-2 control-label no-padding-right">安装验收日期</label>
									<div class="col-xs-12 col-sm-4">
										<input id="DATE" style="width: 90%" type="text"
											fieldname="AZYS_DATE" name="AZYS_DATE" readonly="readonly"
											datatype="*" nullmsg="请选择日期！" errormsg="" placeholder="请选择日期"
											data-date-format="yyyy-mm-dd">
										<div class="info">
											<span class="Validform_checktip"></span><span class="dec"><s
												class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					</div>


                    <div class="widget-box">
					<div class="widget-header widget-header-flat">
						<h4 class="widget-title smaller">项目信息</h4>
					</div>
					<div class="widget-body">
						<div class="widget-main">
							<div class="form-horizontal" id="xmxxform">
								<!-- #section:elements.form.input-state -->
								<div class="form-group"
									style="margin: 0;line-height: 15px;font-size: 14px;">
									<label style="color: #667E99;" for="inputWarning"
										class="col-xs-12 col-sm-2 control-label no-padding-right">工程名称</label>
									<div class="col-xs-12 col-sm-4">
										<input id="GC_NAME" style="width: 90%" type="text" readonly
											fieldname="GC_NAME" name="GC_NAME">
										<div class="info">
											<span class="Validform_checktip"></span><span class="dec"><s
												class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
										</div>
									</div>
									<label style="color: #667E99;" for="inputWarning"
										class="col-xs-12 col-sm-2 control-label no-padding-right">工程地点</label>
									<div class="col-xs-12 col-sm-4">
										<input id="GC_ADDRESS" style="width: 90%" type="text" readonly
											fieldname="GC_ADDRESS" name="GC_ADDRESS">
										<div class="info">
											<span class="Validform_checktip"></span><span class="dec"><s
												class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
										</div>
									</div>
								</div>

								<div style="margin: 1px;"></div>
								<div class="form-group"
									style="margin: 0;line-height: 15px;font-size: 14px;">
									<label style="color: #667E99;" for="inputWarning"
										class="col-xs-12 col-sm-2 control-label no-padding-right">工程施工单位</label>
									<div class="col-xs-12 col-sm-4">
										<input id="GCSG_DANWEI" style="width: 90%" type="text"
											readonly fieldname="GCSG_DANWEI" name="GCSG_DANWEI">
										<div class="info">
											<span class="Validform_checktip"></span><span class="dec"><s
												class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
										</div>
									</div>
									<label style="color: #667E99;" for="inputWarning"
										class="col-xs-12 col-sm-2 control-label no-padding-right">使用单位项目经理</label>
									<div class="col-xs-12 col-sm-4">
										<input id="XMJL" style="width: 90%" type="text" readonly
											fieldname="XMJL" name="XMJL">
										<div class="info">
											<span class="Validform_checktip"></span><span class="dec"><s
												class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
										</div>
									</div>
								</div>

							</div>
						</div>
					</div>
			</form>
		</div>

		<div class="modal-footer">
			<%
				if ("update".equals(type) || type == null) {
			%>
			<button class="btn btn-success btn-sm" onclick="sum()" id="save">提交</button>
			<button class="btn btn-success btn-sm" onclick="save()" id="save">保存</button>
			<%
				}
			%>
			<button class="btn btn-danger btn-sm pull-right" data-dismiss="modal"
				aria-hidden="true">关闭</button>
		</div>
	</div>
</div>
</div>

<form class="form-inline" role="form" id="azgzQueryForm">
	<input type="hidden" id="JXSB_AZGZ_UID" fieldname="JXSB_AZGZ_UID"
		value="<%=id%>" operation="=" logic="and" />
</form>
<script type="text/javascript">
var validform;
	var scripts = [ null ];
	ace.load_ajax_scripts(scripts, function() {
		//	var gridwidth=$("#contentdivid").width();
		//	JqgridManage.initJqgrid(content_grid_table,queryForm,gridwidth);

		//setStyle(xmxxtxformid);
		//DatePicker.datepickerid("#PF_DATE");
		
		
		validform= FormValid.validbyformid(azgzform);//验证
		$('[id=DATE]').each(function() { //ace 时间
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
        if('<%=type%>'=="null"){
        queryXmxx();
        }else{
        init();
        }
	});
    
    function init(){
	query();
	}
	
	function query(){
	    var data1 = combineQuery.getQueryCombineData(azgzQueryForm, null, null);
		var data = {
			msg : data1
		};
		$.ajax({
			url : contextPath + "/jxsb/jxsbAzgzController/query",
			data : data,
			cache : false,
			async : false,
			dataType : "json",
			type : 'post',
			success : function(response) {			
				var resultobj = defaultJson.dealResultJson(response.msg);
				$("#azgzform").setFormValues(resultobj);
				return true;
				}
		});		
	
	}
    
    
	function save() { //azgz add
	
	if(validform.check()){
		bootbox.confirm("请核对信息无误后提交!", function(result) {
			if (result) {
				var data = Form2Json.formToJSON(azgzform);
				//组成保存json串格式
				var data1 = defaultJson.packSaveJson(data);
				if('<%=type%>'=="null"){
				//调用ajax插入
				defaultJson.doInsertJson(contextPath
						+ "/jxsb/jxsbSyglController/insert", data1);
			    }else if('<%=type%>' == "update") {
						defaultJson.doInsertJson(contextPath
								+ "/jxsb/jxsbSyglController/update", data1);
					}
					$("#clo").click();
					jQuery("#content_grid_table").jqGrid()
							.trigger("reloadGrid");
				}
			});
		}

	}
	function sum() {
		$("#SSHENHE_STATUS").val("10");
		save();
	}

	function queryXmxx() {
		$.ajax({
			url : contextPath + "/sggc/projectsGongchengController/queryXmxx",
			data : null,
			cache : false,
			async : false,
			dataType : "json",
			type : 'post',
			success : function(response) {
				var resultobj = defaultJson.dealResultJson(response.msg);
				$("#XMJL").val(resultobj.SG_NAME);
				$("#GC_ADDRESS").val(resultobj.JIANSHE_DIZHI);
				$("#GC_NAME").val(resultobj.GONGCHENG_NAME);
				$("#GCSG_DANWEI").val(resultobj.COMPANY_NAME);
				return true;
			}
		});
	}

	//页面默认参数
	function querySb(id) { //设备 
		var queryconditionXX = "{\"logic\":\"and\",\"fieldname\":\"JXSB_UID\",\"operation\":\"=\",\"value\":\""
				+ id + "\",\"fieldtype\":'',\"fieldformat\":''}";
		var dataXX = "{querycondition: {conditions: ["
				+ queryconditionXX
				+ " ]},pages:{recordsperpage:200, currentpagenum:1, totalpage:0, countrows:0},orders:[{\"order\":\"desc\",\"fieldname\":\"CREATED_DATE\"}]}";
		var data = {
			msg : dataXX
		};
		$.ajax({
			url : contextPath + "/jxsb/jxsbController/query",
			data : data,
			cache : false,
			async : false,
			dataType : "json",
			type : 'post',
			success : function(response) {
				var resultobj = defaultJson.dealResultJson(response.msg);
				$("#JXSB_TYPE_UID_SV").val(resultobj.JXSB_TYPE_UID_SV);
				$("#JXSB_UID").val(resultobj.JXSB_UID);
				$("#ZZXKZ").val(resultobj.ZZXKZ);
				$("#ZZDW").val(resultobj.ZZDW);
				$("#SB_XH").val(resultobj.SB_XH);
				$("#HGZH").val(resultobj.HGZH);
				$("#CC_DATE").val(resultobj.CC_DATE);
				$("#CQDW").val(resultobj.CQDW);
				$("#CQBH").val(resultobj.CQBH);
				return true;
			}
		});
	}

	function querybyqy(id) {//保养单位
		var queryconditionXX = "{\"logic\":\"and\",\"fieldname\":\"AZ_COMPANY_UID\",\"operation\":\"=\",\"value\":\""
				+ id + "\",\"fieldtype\":'',\"fieldformat\":''}";
		var dataXX = "{querycondition: {conditions: ["
				+ queryconditionXX
				+ " ]},pages:{recordsperpage:200, currentpagenum:1, totalpage:0, countrows:0},orders:[{\"order\":\"desc\",\"fieldname\":\"CREATED_DATE\"}]}";
		var data = {
			msg : dataXX
		};
		$.ajax({
			url : contextPath + "/jxsb/azCompanyController/query",
			data : data,
			cache : false,
			async : false,
			dataType : "json",
			type : 'post',
			success : function(response) {
				var resultobj = defaultJson.dealResultJson(response.msg);
				$("#COMPANY_NAME").val(resultobj.COMPANY_NAME);
				$("#BY_COMPANY_UID").val(resultobj.AZ_COMPANY_UID);
				$("#XYSC_CODE").val(resultobj.XYSC_CODE);
				return true;
			}
		});
	}

	function queryazqy(id) {//安装单位
		var queryconditionXX = "{\"logic\":\"and\",\"fieldname\":\"AZ_COMPANY_UID\",\"operation\":\"=\",\"value\":\""
				+ id + "\",\"fieldtype\":'',\"fieldformat\":''}";
		var dataXX = "{querycondition: {conditions: ["
				+ queryconditionXX
				+ " ]},pages:{recordsperpage:200, currentpagenum:1, totalpage:0, countrows:0},orders:[{\"order\":\"desc\",\"fieldname\":\"CREATED_DATE\"}]}";
		var data = {
			msg : dataXX
		};
		$.ajax({
			url : contextPath + "/jxsb/azCompanyController/query",
			data : data,
			cache : false,
			async : false,
			dataType : "json",
			type : 'post',
			success : function(response) {
				var resultobj = defaultJson.dealResultJson(response.msg);
				$("#AZ_DANWEI").val(resultobj.COMPANY_NAME);
				$("#AZ_COMPANY_UID").val(resultobj.AZ_COMPANY_UID);
				$("#AQSCBH").val(resultobj.AQSCXKZ);
				$("#ZZBH").val(resultobj.ZHENGSHU_CODE);
				return true;
			}
		});
	}

	function checkSb() {
		window.open("jsp/gdzxt/jxsbgl/sbzl-page-azgz-checkSb.jsp", "选择机械设备",
				"height=600, width=1000");
	}

	function baoYang() {

		window.open("jsp/gdzxt/jxsbgl/sbzl-page-azgz-baoyang.jsp", "转场保养单位选择",
				"height=600, width=1000");
	}
	function anZhuang() {

		window.open("jsp/gdzxt/jxsbgl/sbzl-page-azgz-anzhuang.jsp", "设备安装单位选择",
				"height=600, width=1000");
	}
</script>