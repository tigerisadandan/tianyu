<!DOCTYPE html>
<html>
	<head>
		<%@ page language="java" pageEncoding="UTF-8"%>
		<title>个人扣分公示</title>
		<link rel="stylesheet"
			href="/wndjsbg/jsp/business/sg/css/theme_3_1.css" type="text/css" />
		<script type="text/javascript" src="/wndjsbg/js/base/jquery.js"> </script>
		<script type="text/javascript" src="/wndjsbg/js/base/LockingTable.js"> </script>
		<script type="text/javascript" src="/wndjsbg/js/base/bootstrap.js"> </script>
		<script type="text/javascript" src="/wndjsbg/js/base/jsBruce.js"> </script>
		<script type="text/javascript" src="/wndjsbg/js/common/Form2Json.js"> </script>
		<script type="text/javascript" src="/wndjsbg/js/common/convertJson.js"> </script>
		<script type="text/javascript"
			src="/wndjsbg/js/common/combineQuery.js"> </script>
		<script type="text/javascript"
			src="/wndjsbg/js/common/default.js?version=20131206"> </script>
		<script type="text/javascript" src="/wndjsbg/js/common/TabList.js"> </script>
		<script type="text/javascript"
			src="/wndjsbg/js/common/bootstrap-validation.js"> </script>
		<script type="text/javascript"
			src="/wndjsbg/js/common/loadFields.js?version=20131206"> </script>
		<script type="text/javascript" src="/wndjsbg/js/common/xWindow.js"> </script>
		<script type="text/javascript"
			src="/wndjsbg/js/My97DatePicker/WdatePicker.js"> </script>
		<script src="/wndjsbg/jsp/business/sg/js/apex_ns_3_1.js"
			type="text/javascript"></script>
		<script src="/wndjsbg/jsp/business/sg/js/apex_3_1.js"
			type="text/javascript"></script>
		<script src="/wndjsbg/jsp/business/sg/js/apex_get_3_1.js"
			type="text/javascript"></script>
		<script src="/wndjsbg/jsp/business/sg/js/apex_builder.js"
			type="text/javascript"></script>
		<script type="text/javascript">
<!--
/*Global JS Variables*/
var htmldb_Img_Dir = "/i/";
//-->
$("#pubAlert").remove();

</script>
		<link rel="stylesheet"
			href="/wndjsbg/jsp/business/sg/css/apex_3_1.css" type="text/css" />
		<!--[if IE]><link rel="stylesheet" href="/i/css/apex_ie_3_1.css" type="text/css" /><![endif]-->
		<meta name="keywords" content="无锡新区,建设环保局,个人扣分公示">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	</head>
	<body>
		<form action="wwv_flow.accept" method="post" name="wwv_flow"
			id="wwvFlowForm">
			<input type="hidden" name="p_flow_id" value="50000" id="pFlowId" />
			<input type="hidden" name="p_flow_step_id" value="80001"
				id="pFlowStepId" />
			<input type="hidden" name="p_instance" value="1666713972129183"
				id="pInstance" />
			<input type="hidden" name="p_page_submission_id"
				value="6682709954344110" id="pPageSubmissionId" />
			<input type="hidden" name="p_request" value="" id="pRequest" />
			<div style="padding: 0px; text-align: left;" valign="top">
				<div style="width: 982px; height: 219px; overflow: no;">
					<div id="report_361514012502319976_catch">
						<table border="0" cellpadding="0" cellspacing="0" summary="">
							<tr>
								<td>
									<table class="standardLook" summary="">
										<tr>
											<td></td>
										</tr>
										<tr id="N1">
											<th id="DOT"></th>
											<th align="left" id="SJ"></th>
											<th align="left" id="RIQI"></th>
										</tr>
										
									</table>
								</td>
							</tr>
							<tr>
								<td align="right">
									<span class="fielddatasmall"></span>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<input type="hidden" name="p_md5_checksum" value="" />
		</form>

		<script type="text/javascript">

</script>
	</body>
	<script type="text/javascript">
var controllername= "${pageContext.request.contextPath }/sgenter/sgScoreController/";
$(function() {
	init();
	$("#pubAlert").remove();
});

function init(){
	setFormValues("scoreInfo");
}
//修改情况下,读取表单内容
function setFormValues(type){
	$.ajax({
		url : controllername+"tongjiScore?id=&type="+type,
		data : {},
		cache : false,
		async :	false,
		dataType : "text",  
		type : 'post',
		success : function(response) {
			var showHtml = "";
			var item = convertJson.string2json1(response);
			var arr = convertJson.string2json1(item);
			$(arr).each(function(i,item){
				showHtml += '<tr><td headers="DOT"><img src="/wndjsbg/jsp/business/sg/css/dot_2.jpg"></td>'
					+'<td headers="SJ"><span style="width:880px;">'+item.COMPANY_NAME+'(登陆代码:'+item.DENGLU_CODE+')信用综合评价分为'+item.SCORE+'分</span></td>'
					+'<td headers="RIQI">('+item.CREATED_DATE.substring(5,10)+')</td>'
					+'</tr>';
			})
			$('#N1').after(showHtml);
		}
	});
}
</script>
</html>
