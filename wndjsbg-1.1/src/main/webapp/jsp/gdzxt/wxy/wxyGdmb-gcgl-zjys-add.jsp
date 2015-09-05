<!DOCTYPE html>
<%@page import="com.ccthanking.framework.Constants"%>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<% 
long ll = System.currentTimeMillis();
String id = request.getParameter("id");
String fjlb=Constants.FS_FILEUPLOAD_FJLB_WXY_GDMBGC_ZJYS;
%>

<app:base/>
<title>高支架工程 - 中间验收</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/ywlz/css/default.css"> </LINK>
</head>
<body>
<div class="container-fluid">
	<p></p>
	<div class="row-fluid">
		<div class="B-small-from-table-autoConcise">
			<form class="form-inline" role="form" id="tfkwQueryForm">
	<input type="hidden" id="QUERY_WXY_GDMB_GC_UID" fieldname="WXY_GDMB_GC_UID" value="<%=id %>"
		operation="=" logic="and" />
</form>
			<div style="height:5px;"> </div>	
			<div id="div1">
            <form class="form-horizontal" id="tfkwForm">
<input id="TFKW_WXY_GDMB_GC_UID" type="hidden"  fieldname="WXY_GDMB_GC_UID" name="WXY_GDMB_GC_UID">
<input id="TFKW_WXY_GDMB_UID" type="hidden"  fieldname="WXY_GDMB_UID" name="WXY_GDMB_UID">
<input id="TFKW_GONGCHENG_UID" type="hidden"  fieldname="GONGCHENG_UID" name="GONGCHENG_UID">
<input id="TFKW_WXY_TYPE" type="hidden"  fieldname="WXY_TYPE" name="WXY_TYPE" value="2">
<input id="TFKW_STATUS" type="hidden"  fieldname="STATUS" name="STATUS">
<table border="1" width="100%" cellpadding="0" cellspacing="0" class="content">
			<tr>
				<td class="yw-title">
					<h4 ><span id="spyjtxhid">中间验收<span id="tfkwmsg" style="color: red;"></span></span>
								<span class="pull-right" > 
								</span>
								</h4>
				</td>
			</tr>
			<tr>
				<td>
					<ul id="ywcl_list">
						<table class="B-table" width="100%" >
      		<tr>
				<th width="15%" class="right-border bottom-border text-right">立杆间距、垂直度等是否满足方案要求</th>
				<td class="right-border bottom-border">
					<input class="span12" style="width:94%"  type="radio" readonly="readonly" fieldname="LIGAN_JIANJU_Y" name="LIGAN_JIANJU_Y" kind="dic" src="SF" />			
				</td>
				<th width="15%" class="right-border bottom-border text-right">水平横杆步距是否满足方案要求</th>
				<td class="right-border bottom-border">
					<input class="span12" style="width:94%"  type="radio" readonly="readonly" fieldname="SHUIPING_HENGGAN_Y" name="SHUIPING_HENGGAN_Y" kind="dic" src="SF" />			
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">扫地杆设置情况</th>
				<td class="right-border bottom-border" colspan="3">
					 <textarea rows="" class="span12" style="width:98%" id="SAODIGAN_SHIZHI" readonly="readonly" fieldname="SAODIGAN_SHIZHI" name = "SAODIGAN_SHIZHI"></textarea>			
				</td>
				
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">钢管扣件检测验收情况</th>
				<td colspan="3" class="right-border bottom-border">
				    <textarea rows="" class="span12" style="width:98%" id="GANGGUAN_JIANCE" readonly="readonly" fieldname="GANGGUAN_JIANCE" name = "GANGGUAN_JIANCE"></textarea>
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">上传文件</th>
				<td colspan="3" class="right-border bottom-border">
				    <table role="presentation" id="zzjg" class="table table-striped" style="margin-bottom:0px;width: 96.1%">
											<tbody fjlb="<%=fjlb%>" id="zzjgdm" onlyView="true"
												class="files showFileTab" data-toggle="modal-gallery"
												data-target="#modal-gallery"></tbody>
					</table>
				</td>
			</tr>
	        </table>
					</ul>
				</td>
			</tr>
		</table>

<table border="1" width="100%" cellpadding="0" cellspacing="0" class="content">
			<tr>
				<td class="yw-title">
					<h4 ><span id="spyjtxhid">审核意见</span></span>
								<span class="pull-right" > 
								</span>
								</h4>
				</td>
			</tr>
			<tr>
				<td>
					<ul id="ywcl_list">
						<table class="B-table" width="100%" >
      		<input type="hidden" id="JS_COMPANY_UID" fieldname="JS_COMPANY_UID" name = "JS_COMPANY_UID"/>
      		<tr>
				<th width="15%" class="right-border bottom-border text-right">总承包单位审核意见</th>
				<td width="20%" class="right-border bottom-border" colspan="3">
				   <textarea rows="" class="span12" style="width:98%" id="SG_YJ" readonly="readonly" fieldname="SG_YJ" name = "SG_YJ"></textarea>			
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">项目经理</th>
				<td width="20%" class="right-border bottom-border">
					<input class="span12" style="width:94%" id="XMJL_NAME" type="text" readonly="readonly" fieldname="XMJL_NAME" name = "XMJL_NAME" />			
				</td>
				
				<th width="15%" class="right-border bottom-border text-right">审核时间</th>
				<td width="20%" class="right-border bottom-border">
					<input class="span12" style="width:94%" id="SGSH_DATE" type="text" readonly="readonly" fieldname="SGSH_DATE" name = "SGSH_DATE" />			
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">审核结果</th>
				<td width="20%" class="right-border bottom-border" colspan="1">
					 <input type=radio id="zt0" onclick="cecked(1)" status="true" name="status" >同意&nbsp;&nbsp; 
										 <input type=radio id="zt1" onclick="cecked(-1)" status="true" name="status" >不同意		
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">监理单位审核意见</th>
				<td width="20%" class="right-border bottom-border" colspan="3">
					 <textarea rows="" class="span12" style="width:98%" id="SHENHE_YJ" readonly="readonly" fieldname="SHENHE_YJ" name = "SHENHE_YJ"></textarea>				
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">总监</th>
				<td width="20%" class="right-border bottom-border">
					<input class="span12" style="width:94%" id="SHENHE_REN" type="text" readonly="readonly" fieldname="SHENHE_REN" name = "SHENHE_REN" />			
				</td>
				
				<th width="15%" class="right-border bottom-border text-right">审核时间</th>
				<td width="20%" class="right-border bottom-border">
					<input class="span12" style="width:94%" id="SHENHE_DATE" type="text" readonly="readonly" fieldname="SHENHE_DATE" name = "SHENHE_DATE" />			
				</td>
			</tr>
	        </table>
					</ul>
				</td>
			</tr>
		</table>
				
</form>
            </div>
	 	</div>
	</div>     
</div>
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
<jsp:include page="/jsp/file_upload/fileuploadold_config_js.jsp" flush="true"/> 
<script type="text/javascript">
var tfkwQueryValidform;//验证
$(document).ready(function(){
	$("#tfkwForm").find("input,textarea,radio").each(function(i,item){
		 $(item).attr('disabled','disabled');
		});
	 tfkwInit();
	});

function tfkwInit(){
	tfkwQuery();
}

function tfkwQuery(){
	var data1 = combineQuery.getQueryCombineData(tfkwQueryForm, frmPost);
	var data = {
		msg : data1
	};
	$.ajax({
		url :"${pageContext.request.contextPath }/wxy/wxyGdmbGcController/query",
		data : data,
		cache : false,
		async : false,
		dataType : "json",
		type : 'post',
		success : function(response) {
			if(response.msg!="0"){
			var resultobj = defaultJson.dealResultJson(response.msg);

			$("#tfkwForm").setFormValues(resultobj);
			queryFileData(resultobj.WXY_GDMB_GC_UID, "", "", "460010");
			if(resultobj.STATUS=="0"){
				$("#tfkwmsg").html("(待审核)");
			}else if(resultobj.STATUS=="1"){
				$("#tfkwmsg").html("(已通过)");
				$("#zt0").attr('checked','checked');
			}else if(resultobj.STATUS=="-1"){
				$("#tfkwmsg").html("(未通过)");
				$("#zt1").attr('checked','checked');
			}
			return true;
			}
			}
	});		
}


function selectYyzzFile(obj){
	
	$("#yyzzError").hide();
	//var result=getSpClkUid("LX",$(obj).attr("clkUid"));
	var ywid="";
	var id=$("#ywid").val();
	if(id!=""){	
		ywid=id;
		var temid=$("#ID").val();	
		if(temid!=null&&temid!=""){
			ywid=temid;
		}
	}
	setFileData(ywid,"","","460010","0",$(obj).attr("clkUid"));
	var inputArr = $(".myKeyValueDiv input");
	for(var xx=0;xx<inputArr.length;xx++){
		if(inputArr[xx].getAttribute("cond")=="true"){
			if(inputArr[xx].getAttribute("condName")=="fjlb"){
				inputArr[xx].value=$(obj).attr("fjlb");
			}
		}
	}
	if(isIE()){
		initdataswf(ywid,"","","460010","0",$(obj).attr("clkUid"),$(obj).attr("clkUid"));
		//initdataswf(ywid,"","","470010","0",result.CLK_UID,"1609");
		document.getElementById("plfileuploadswf").click();
	}else{
		document.getElementById("fileupload_btn").click();
	}	
}

function isIE(){
	if(navigator.userAgent.indexOf("MSIE")>0){ 
		if(navigator.userAgent.indexOf("MSIE 6.0")>0){ 
			return false;
		} 
		if(navigator.userAgent.indexOf("MSIE 7.0")>0){
			return false;
		} 
		if(navigator.userAgent.indexOf("MSIE 8.0")>0){
			return true;
		} 
		if(navigator.userAgent.indexOf("MSIE 9.0")>0){
			return true;
		} 
	}else{
		return false;
	} 	
}

		</script>
</body>
</html>