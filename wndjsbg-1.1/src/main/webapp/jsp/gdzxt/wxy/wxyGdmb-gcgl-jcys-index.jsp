<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>危险源——过程管理</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/ywlz/css/default.css"> </LINK>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/project/jsCompanyController";
var TOP_GONGCHENG_NAME="";
//页面初始化
$(function() {
	$("#QGONGCHENG_UID").val(parent.document.getElementById("GDZXT_XM_ID").value);
	TOP_GONGCHENG_NAME =  parent.document.getElementById("GDZXT_XM_NAME").value;
	init();
	
});
function init(){
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList("${pageContext.request.contextPath }/wxy/wxyGdmbGcController/query",data,DT1);
}
//状态选择，并显示列控制
function doCg(v1){
	//var v1 = $("input:radio[name='STATUS']:checked").val();
	if($("#QWXY_TYPE").val()==v1)
		return true;
	
	$("#YUJI_DATE").attr("hidden",'hidden');
	$("#QWXY_TYPE").val(v1);
	if(v1=='1'){
		$("#gctitle").html("基础验收 ");
		$("#gzmks").removeAttr('hidden');
		$("#jcsm").removeAttr('hidden');
		$("#lgjj").attr('hidden','hidden');
		$("#sphg").attr('hidden','hidden');
		$("#yjjz").attr('hidden','hidden');
		$("#spjd").attr('hidden','hidden');
		$("#hxjd").attr('hidden','hidden');
		$("#cmwc").attr('hidden','hidden');
	}else if(v1=='2'){
		$("#gctitle").html("中间验收 ");
		$("#lgjj").removeAttr('hidden');
		$("#sphg").removeAttr('hidden');
		$("#gzmks").attr('hidden','hidden');
		$("#jcsm").attr('hidden','hidden');
		$("#yjjz").attr('hidden','hidden');
		$("#spjd").attr('hidden','hidden');
		$("#hxjd").attr('hidden','hidden');
		$("#cmwc").attr('hidden','hidden');
	}else if(v1=='3'){
		$("#gctitle").html("浇筑前验收 ");
		$("#yjjz").removeAttr('hidden');
		$("#spjd").removeAttr('hidden');
		$("#hxjd").removeAttr('hidden');
		$("#gzmks").attr('hidden','hidden');
		$("#jcsm").attr('hidden','hidden');
		$("#lgjj").attr('hidden','hidden');
		$("#sphg").attr('hidden','hidden');
		$("#cmwc").attr('hidden','hidden');
	}else if(v1=='4'){
		$("#gctitle").html("拆模  ");
		$("#cmwc").removeAttr('hidden');
		$("#spjd").attr('hidden','hidden');
		$("#hxjd").attr('hidden','hidden');
		$("#gzmks").attr('hidden','hidden');
		$("#jcsm").attr('hidden','hidden');
		$("#lgjj").attr('hidden','hidden');
		$("#sphg").attr('hidden','hidden');
		$("#yjjz").attr('hidden','hidden');
	}
 init();
}

function edit(id) {
	var data = $("#DT1").getSelectedRow();
	var url="";
	if($("#QWXY_TYPE").val()=="1"){
		url="${pageContext.request.contextPath }/jsp/gdzxt/wxy/wxyGdmb-gcgl-jcys-add.jsp?id="+id;
	}else if($("#QWXY_TYPE").val()=="2"){
		url="${pageContext.request.contextPath }/jsp/gdzxt/wxy/wxyGdmb-gcgl-zjys-add.jsp?id="+id;
	}else if($("#QWXY_TYPE").val()=="3"){
		url="${pageContext.request.contextPath }/jsp/gdzxt/wxy/wxyGdmb-gcgl-jzqys-add.jsp?id="+id;
	}else if($("#QWXY_TYPE").val()=="4"){
		url="${pageContext.request.contextPath }/jsp/gdzxt/wxy/wxyGdmb-gcgl-cm-add.jsp?id="+id;
	}
	$("#resultXML").val(data);
	$(window).manhuaDialog({"title":"高支架工程  >基础验收","type":"text","content":url,"modal":"2"});
}

function statusHtml(obj){
	var html = "";
	if(obj.STATUS=="0"){
		html="待审核";
	}else if(obj.STATUS=="1"){
		html="已审核";
	}else if(obj.STATUS=="-1"){
		html="审核未通过";
	}
	return html;
}
function xmmcHtml(obj){
	return "<a href=\"javascript:void(0)\" onclick=\"edit('"+obj.WXY_GDMB_GC_UID+"')\">"+TOP_GONGCHENG_NAME+"</a>";
}
function lgjjY(obj){
	var html = "";
	if(obj.LIGAN_JIANJU_Y=="0"){
		html = "否";
	}else if(obj.LIGAN_JIANJU_Y=="1"){
		html = "是";
	}
	return html;
}
function sphgY(obj){
	var html = "";
	if(obj.SHUIPING_HENGGAN_Y=="0"){
		html = "否";
	}else if(obj.SHUIPING_HENGGAN_Y=="1"){
		html = "是";
	}
	return html;
}
function spjdY(obj){
	var html = "";
	if(obj.SHUIPING_JIANDAO_Y=="0"){
		html = "否";
	}else if(obj.SHUIPING_JIANDAO_Y=="1"){
		html = "是";
	}
	return html;
} 
function sxjdY(obj){
	var html = "";
	if(obj.SHUXIANG_JIANDAO_Y=="0"){
		html = "否";
	}else if(obj.SHUXIANG_JIANDAO_Y=="1"){
		html = "是";
	}
	return html;
}
function yjjzSJ(obj){
	return obj.YUJI_DATE;
}
function cmwcSJ(obj){
	return obj.YUJI_DATE;
}
</script>
</head>
<body>
<div class="container-fluid">
	<p></p>
	<div class="row-fluid">
		<div class="B-small-from-table-autoConcise">
<%--			<h4 class="title">
				建设单位
				<span class="pull-right">  
      				<button id="btnUpdate" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">审核</button>
      				<button id="btnExpExcel" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">导出</button>
				</span>
			</h4>--%>
			<form role="form" class="form-horizontal" id="queryForm">
			<div class="form-group" style="padding-left: 10PX">
				<div class="col-xs-8">
					<input type="hidden" id="QGONGCHENG_UID" name="GONGCHENG_UID" fieldname="GONGCHENG_UID" operation="=" >

					<label class="col-sm-2 radio inline">
						<input type="radio" name="STATUS" value="40" onclick="doCg('1')" checked="checked">
						基础验收
						<!-- <font style="color: red" id="wtj">(0)</font> -->
					</label>
					<label class="col-sm-2 radio inline">
						<input type="radio" name="STATUS" value="0" onclick="doCg('2')">
						中间验收	
					</label>
					<label class="col-sm-2 radio inline">
						<input type="radio" name="STATUS" value="1" onclick="doCg('3')">
						浇筑前验收
					</label>
					<label class="col-sm-2 radio inline">
						<input type="radio" name="STATUS" value="-1" onclick="doCg('4')">
						拆模
					</label>
					<input type="hidden" fieldname="WXY_TYPE" operation="=" value="1" id="QWXY_TYPE" />
					<input class="hidden" id="rownum" type="text" fieldname="rownum" value="10000" operation="  &lt;= " />
				</div>

			</div>
		<!-- 	<div class="hr hr10 hr-dotted"></div>  -->
		</form>
			<div style="height:5px;"> </div>	
			<table border="1" width="100%" cellpadding="0" cellspacing="0" class="content">
			<tr>
				<td class="yw-title">
					<h4 ><span id="gctitle">基础验收 </span>
								<span class="pull-right" > <!-- 
								<button id="btnAddYj" class="btn" type="button">   重新填写分项工程信息    </button>   -->
								</span>
								</h4>
				</td>
			</tr>
			<tr>
				<td>
					<ul id="ywcl_list" style=""> 
						<div class="overFlowX">
	            <table width="100%" class="table-hover table-activeTd B-table"  id="DT1" type="single" pageNum="9999">
	                <thead>
	                           
	                	<tr>
	                		<th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
	                		<th fieldname="WXY_GDMB_GC_UID" colindex=2 tdalign="center" hidden>&nbsp;使用状态&nbsp;</th>
	                		<th fieldname="STATUS"  style="width:50px" tdalign="center" CustomFunction="statusHtml">&nbsp;状态&nbsp;</th>
	                		<th fieldname="GONGCHENG_UID" colindex=3 tdalign="left" style="width:120px" maxlength="30" CustomFunction="xmmcHtml" >&nbsp;项目名称&nbsp;</th>
	                		<th id="gzmks" fieldname="YUJI_DATE" colindex=4 tdalign="center"  style="width:50px">&nbsp;高支模开始时间&nbsp;</th>
	                		<th id="jcsm" fieldname="ANQUAN_QINGKUANG" style="width:150px" colindex=5 tdalign="left"  >&nbsp;基础书面验收情况&nbsp;</th>
	                		<th id="lgjj" fieldname="LIGAN_JIANJU_Y" style="width:50px" colindex=7 tdalign="center" CustomFunction="lgjjY" maxlength="30" hidden="">&nbsp;立杆间距、垂直度等是否满足方案要求满足方案要求&nbsp;</th>
	                		<th id="sphg" fieldname="SHUIPING_HENGGAN_Y" style="width:50px" colindex=6 tdalign="center" CustomFunction="sphgY" maxlength="30" hidden="">&nbsp;水平横杆步距是否满足方案要求&nbsp;</th>
	                		<th id="yjjz" fieldname="WXY_GDMB_GC_UID" style="width:80px" colindex=8 tdalign="center" CustomFunction="yjjzSJ"  hidden="">&nbsp;预计浇筑时间&nbsp;</th>
	                		<th id="spjd" fieldname="SHUIPING_JIANDAO_Y" tdalign="center" style="width:100px" CustomFunction="spjdY" hidden="">&nbsp;水平剪刀撑是否满足方案要求&nbsp;</th>
	                		<th id="hxjd" fieldname="SHUXIANG_JIANDAO_Y" tdalign="center" style="width:100px" CustomFunction="sxjdY" hidden="">&nbsp;竖向剪刀撑是否满足方案要求&nbsp;</th>
	                		<th id="cmwc" fieldname="WXY_GDMB_GC_UID" tdalign="center" CustomFunction="cmwcSJ" style="width:50px" hidden>&nbsp;拆模完成时间&nbsp;</th>	
	                	</tr>
	                </thead>
	              	<tbody></tbody>
	           </table>
	       </div>
						
					</ul>
				</td>
			</tr>
		</table>
	 	</div>
	</div>     
</div>
<jsp:include page="/jsp/file_upload/fileuploadold_config_js.jsp" flush="true"/> 
<div align="center">
	<FORM name="frmPost" method="post" style="display: none" target="_blank">
		<!--系统保留定义区域-->
		<input type="hidden" name="queryXML"/> 
		<input type="hidden" name="txtXML"/>
		<input type="hidden" name="txtFilter" order="desc" fieldname="GONGCHENG_UID" id="txtFilter"/>
<%--		<input type="hidden" name="txtFilter" order="desc" fieldname="ID" id="txtFilter"/>--%>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>