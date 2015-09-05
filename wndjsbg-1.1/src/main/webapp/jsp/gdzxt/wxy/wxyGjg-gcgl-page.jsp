<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<%@page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext"%>
<%@page import="com.ccthanking.framework.common.User"%>
<%@page import="com.ccthanking.framework.Constants"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<% 
String gcid=request.getParameter("gcid");
String fjlb=Constants.FS_FILEUPLOAD_FJLB_WXY_GJGGC;
User user = RestContext.getCurrentUser();
String username = user.getName();
%>

<app:base/>
<title>危险源——钢结构工程</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/ywlz/css/default.css"> </LINK>
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
			<form method="post" id="queryForm">
			<input type="hidden" id="QUERY_TFKW_GONGCHENG_UID" fieldname="GONGCHENG_UID"
		operation="=" logic="and" />
			</form>
			<div style="height:5px;"> </div>	
			<div id="div1">
            <form class="form-horizontal" id="tfkwForm">
<input type="hidden" id="ywid" fieldname="ywid" name="ywid" />
<input id="TFKW_WXY_DZGC_GC_UID" type="hidden"  fieldname="WXY_DZGC_GC_UID" name="WXY_DZGC_GC_UID">
<input id="TFKW_WXY_DZGC_UID" type="hidden"  fieldname="WXY_DZGC_UID" name="WXY_DZGC_UID">
<input id="TFKW_GONGCHENG_UID" type="hidden"  fieldname="GONGCHENG_UID" name="GONGCHENG_UID">
<input id="TFKW_STATUS" type="hidden"  fieldname="STATUS" name="STATUS">
<table border="1" width="100%" cellpadding="0" cellspacing="0" class="content">
			<tr>
				<td class="yw-title">
					<h4 ><span id="spyjtxhid">钢结构安装<span id="tfkwmsg" style="color: red;">(未申请)</span></span>
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
				<th width="15%" class="right-border bottom-border text-right">吊装开始时间</th>
				<td width="20%" class="right-border bottom-border">
					<input class="span12" style="width:94%" id="BEGIN_DATE" type="text" readonly="readonly" check-type="required" fieldname="BEGIN_DATE" name = "BEGIN_DATE" />			
				</td>
				
				<th width="15%" class="right-border bottom-border text-right">吊装完成时间</th>
				<td width="20%" class="right-border bottom-border">
					<input class="span12" style="width:94%" id="END_DATE" type="text" readonly="readonly" fieldname="END_DATE" name = "END_DATE" />			
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">汽车吊年检证明</th>
				<td colspan="3" class="right-border bottom-border">
				    <textarea rows="" class="span12" style="width:98%" id="NIANJIAN_ZHENGMING" readonly="readonly" fieldname="NIANJIAN_ZHENGMING" name = "NIANJIAN_ZHENGMING"></textarea>
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">特种作业人员持证上岗情况</th>
				<td colspan="3" class="right-border bottom-border">
				    <textarea rows="" class="span12" style="width:98%" id="CHIZHENG_SHANGGANG" readonly="readonly" fieldname="CHIZHENG_SHANGGANG" name = "CHIZHENG_SHANGGANG"></textarea>
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">吊装区域设警戒区域专人监护情况</th>
				<td colspan="3" class="right-border bottom-border">
				    <textarea rows="" class="span12" style="width:98%" id="ZHUANREN_JIANHU" readonly="readonly" fieldname="ZHUANREN_JIANHU" name = "ZHUANREN_JIANHU"></textarea>
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">动火审批情况</th>
				<td colspan="3" class="right-border bottom-border">
				    <textarea rows="" class="span12" style="width:98%" id="DONGHUO_SHENPI" readonly="readonly" fieldname="DONGHUO_SHENPI" name = "DONGHUO_SHENPI"></textarea>
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">上传文件</th>
	       	 	<td class="bottom-border right-border" style="width:32%" colspan="3">
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
					 <input type=radio id="zt0" onclick="cecked(1)" status="true" name="status" checked="checked">同意&nbsp;&nbsp; 
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
				<table border="1" width="100%" cellpadding="0" cellspacing="0" class="content">
			<tr>
				<td class="yw-title">
					<h4 ><span id="spyjtxhid">土方开挖图片</span></span>
								<span class="pull-right" > 
								</span>
								</h4>
				</td>
			</tr>
			<tr>
				<td>
					<ul id="ywcl_list">
						&nbsp;
					</ul>
				</td>
			</tr>
		</table>			
</form>
            </div>
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
<script type="text/javascript">

$(document).ready(function(){
	$("#tfkwForm").find("input,textarea,radio").each(function(i,item){
		 $(item).attr('disabled','disabled');
		});
	 tfkwInit();
	 $("#TFKW_GONGCHENG_UID").val(parent.document.getElementById("GDZXT_XM_ID").value);
	 $("#QUERY_TFKW_GONGCHENG_UID").val(parent.document.getElementById("GDZXT_XM_ID").value);
	});

function tfkwInit(){
	tfkwQuery();
	 $("#TFKW_STATUS").val('33');
}



function tfkwQuery(){
	var data1 = combineQuery.getQueryCombineData(queryForm, frmPost);
	var data = {
		msg : data1
	};
	$.ajax({
		url : "${pageContext.request.contextPath }/wxy/wxyGjgGcController/query",
		data : data,
		cache : false,
		async : false,
		dataType : "json",
		type : 'post',
		success : function(response) {
			if(response.msg!="0"){
			var resultobj = defaultJson.dealResultJson(response.msg);
			if(resultobj.STATUS=="0"){
				
			}else if(resultobj.STATUS=="30"){
				//禁用非审核内容
				$("#tfkwmsg").html("(待审核)");
				$("#tfkwTb").attr("onlyView","true"); 
				$("#tfkwForm").setFormValues(resultobj);
				queryFileData(resultobj.WXY_GJG_GC_UID, "", "", "");
			}else if(resultobj.STATUS=="33"){
				$("#tfkwmsg").html("(已审核)");
				
				$("#sum").hide();
				$("#tfkwTb").attr("onlyView","true"); 
				queryFileData(resultobj.WXY_GJG_GC_UID, "", "", "");
				$("#tfkwForm").setFormValues(resultobj);
			}else if(resultobj.STATUS=="32"){
				
				$("#tfkwmsg").html("(审核未通过)");
				$("#tfkwForm").setFormValues(resultobj);
				$("#sum").hide();
				$("#tfkwTb").attr("onlyView","true"); 
				$("#zt1").attr('checked','checked');
				queryFileData(resultobj.WXY_GJG_GC_UID, "", "", "");
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
	setFileData(ywid,"","","460010","0",'<%=fjlb %>');
	var inputArr = $(".myKeyValueDiv input");
	for(var xx=0;xx<inputArr.length;xx++){
		if(inputArr[xx].getAttribute("cond")=="true"){
			if(inputArr[xx].getAttribute("condName")=="fjlb"){
				inputArr[xx].value=$(obj).attr("fjlb");
			}
		}
	}
	if(isIE()){
		initdataswf(ywid,"","","460010","0",'<%=fjlb %>','<%=fjlb %>');
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