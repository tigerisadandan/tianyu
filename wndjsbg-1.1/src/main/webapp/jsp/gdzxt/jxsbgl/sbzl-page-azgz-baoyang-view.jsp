<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%
	long ll = System.currentTimeMillis();
	String id = request.getParameter("id");
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

<div class="modal-dialog width-65 height-auto">
	<div class="modal-content">
		<div class="widget-header widget-header-large">
			<h3 id="dlixiangModalLabel" class="widget-title">起重设备产权信息</h3>
			<div class="widget-toolbar">
				<a href="#" data-action="close" data-dismiss="modal"> <i
					class="ace-icon fa fa-times"></i> </a>
			</div>
		</div>
		<div class="modal-body">

			<form role="form" class="form-horizontal" method="post"
				id="executeFrm">
				<input type="hidden" id="STATUS" fieldname="STATUS"
					name="STATUS" value="30" /> <input type="hidden"
					id="JXSB_UID" fieldname="JXSB_UID"
					name="JXSB_UID" />


				<div class="form-group">
					<label class="col-sm-2 control-label no-padding-right"
						for="form-field-1">产权单位</label>

					<div class="col-sm-3">
						<input id="ZHIZHAO" type="text" style="width: 120%" 
						datatype="*" nullmsg="请输入产权单位！" errormsg="" maxlength="100" placeholder="请输入产权单位"
							fieldname="CQDW" name="CQDW" class="col-xs-10 col-sm-10">
							<div class="info">
												<span class="Validform_checktip"></span><span class="dec"><s
													class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
								</div>
					</div>
					<label class="col-sm-2 control-label no-padding-right"
						for="form-field-1">设备产权编号</label>

					<div class="col-sm-3">
						<input id="QCQBH" type="text" style="width: 120%" style="width: 120%"  class="col-xs-10 col-sm-10"
								datatype="*" nullmsg="请输入产权编号！" errormsg="" maxlength="20" placeholder="请输入产权编号"
									fieldname="CQBH" name="CQBH"
									ajaxurl="${pageContext.request.contextPath }/azqy/jxsbController/chongfuYz"
											sucmsg="产权编号验证通过！" nullmsg="请输入产权编号！" errormsg="组织机构代码必须9位！" />
								<div class="info">
												<span class="Validform_checktip"></span><span class="dec"><s
													class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
								</div>
					</div>

				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label no-padding-right"
						for="form-field-1">设备类型</label>

					<div class="col-sm-3">
						<select style="width: 120%" id="FJXSB_TYPE_UID" type="text" kind="dic"
									src="AZ_JXSB_TYPE" name="JXSB_TYPE_UID" fieldname="JXSB_TYPE_UID"
									datatype="*" nullmsg="请选择设备类型！" errormsg="" 
									class="col-xs-10 col-sm-10"></select>
									<div class="info">
												<span class="Validform_checktip"></span><span class="dec"><s
													class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
								</div>
					</div>
					<label class="col-sm-2 control-label no-padding-right"
						for="form-field-1">设备型号</label>

					<div class="col-sm-3">
						<input id="SB_XH" type="text" style="width: 120%"
						datatype="*" nullmsg="请输入设备型号！" errormsg="" maxlength="20" placeholder="请输入设备型号"
							fieldname="SB_XH" name="SB_XH" class="col-xs-10 col-sm-10">
							<div class="info">
												<span class="Validform_checktip"></span><span class="dec"><s
													class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
								</div>
					</div>

				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label no-padding-right"
						for="form-field-1">制造厂家</label>

					<div class="col-sm-3">
						<input id="ZZDW" type="text" style="width: 120%"
						datatype="*" nullmsg="请输入制造厂家！" errormsg="" maxlength="200" placeholder="请输入制造厂家"
							fieldname="ZZDW" name="ZZDW" class="col-xs-10 col-sm-10">
							<div class="info">
												<span class="Validform_checktip"></span><span class="dec"><s
													class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
								</div>
					</div>
					<label class="col-sm-2 control-label no-padding-right"
						for="form-field-1">制造许可证</label>

					<div class="col-sm-3">
						<input id="ZHIZHAO" type="text" style="width: 120%"
						datatype="n" nullmsg="请输入制造许可证！" errormsg="" maxlength="50" placeholder="请输入制造许可证"
							fieldname="ZZXKZ" name="ZZXKZ" class="col-xs-10 col-sm-10">
							<div class="info">
												<span class="Validform_checktip"></span><span class="dec"><s
													class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
								</div>
					</div>

				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label no-padding-right"
						for="form-field-1">产权单位地址</label>

					<div class="col-sm-9">
						<input id="ZHIZHAO" type="text" style="width: 95%"
						datatype="*" nullmsg="请输入产权单位地址！" errormsg="" maxlength="200" placeholder="请输入产权单位地址"
							fieldname="CQDW_ADDRESS" name="CQDW_ADDRESS" class="col-xs-10 col-sm-10">
							<div class="info">
												<span class="Validform_checktip"></span><span class="dec"><s
													class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
								</div>
					</div>
				</div>
				

					
					<div class="form-group">
					<label class="col-sm-2 control-label no-padding-right"
						for="form-field-1">出厂编号</label>
					<div class="col-sm-3">
						<input id="ZHIZHAO" type="text" style="width: 120%"
						datatype="*" nullmsg="请输入出厂编号！" errormsg="" maxlength="50" placeholder="请输入出厂编号"
							fieldname="CC_CODE" name="CC_CODE" class="col-xs-10 col-sm-10">
							<div class="info">
												<span class="Validform_checktip"></span><span class="dec"><s
													class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
								</div>
					</div>
						<label class="col-sm-2 control-label no-padding-right"
							for="form-field-1">有效期
（设备寿命期）</label>

						<div class="col-sm-3">
							<span class="input-icon input-icon-right" style="width: 120%">
								<input type="text" fieldname="YOUXIAO_DATE" style="width: 100%" data-date-format="yyyy-mm-dd"
									datatype="*" nullmsg="请选择有效期！" errormsg="" placeholder="请选择有效期"
								id="ZHIZHAO_VALID" readonly="readonly" name="YOUXIAO_DATE" />
								<i class="ace-icon fa fa-calendar"></i> </span>
								<div class="info">
												<span class="Validform_checktip"></span><span class="dec"><s
													class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
								</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label no-padding-right"
						for="form-field-1">备注</label>
					<div class="col-sm-9">
						<textarea rows="4" cols="" style="width: 95%" fieldname="DESCRIBE"
						 datatype="*" nullmsg="请输入备注！" errormsg="" maxlength="1000" placeholder="请输入备注" ></textarea>
						<div class="info">
												<span class="Validform_checktip"></span><span class="dec"><s
													class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
								</div>
					</div>
					</div>



			</form>

		</div>
		<div class="modal-footer">
			<button class="btn btn-danger btn-sm pull-right" data-dismiss="modal"
				aria-hidden="true">关闭</button>
		</div>
	</div>
</div>
<!-- </div> -->

<script type="text/javascript">
    var validform;
	var type="";
	var controllername= "${pageContext.request.contextPath }/azqy/jxsbController/";
	jQuery(function($) {
		setStyle(executeFrm); //字典
		validform= FormValid.validbyformid(executeFrm);//验证
		init();
	});

	function init(){ //初始化
	    var queryconditionXX = "{\"logic\":\"and\",\"fieldname\":\"JXSB_UID\",\"operation\":\"=\",\"value\":\""+<%=id %>+"\",\"fieldtype\":'',\"fieldformat\":''}";
        var dataXX = "{querycondition: {conditions: [" +queryconditionXX+" ]},pages:{recordsperpage:200, currentpagenum:1, totalpage:0, countrows:0},orders:[{\"order\":\"desc\",\"fieldname\":\"CREATED_DATE\"}]}";
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
				$("#executeFrm").setFormValues(resultobj);
				return true;
				}
		});		
		disabled();
   }
	
	
    function disabled(){
    $("#executeFrm").find(":input").each(function(i){
	   $(this).attr("readonly", "readonly");
	 });
	 
	var input = $("#executeFrm").find("input:radio");
    input.attr("disabled","disabled");
 
    var checkbox = $("#executeFrm").find("input:checkbox");
    checkbox.attr("disabled","disabled");
	$("#FJXSB_TYPE_UID").attr("disabled","disabled");
	
	$("#QCQBH").removeAttr("ajaxurl");
	}
</script>