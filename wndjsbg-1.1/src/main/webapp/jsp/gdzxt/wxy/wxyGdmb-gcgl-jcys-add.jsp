<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<% 
long ll = System.currentTimeMillis();
String id = request.getParameter("id");
%>

<app:base/>
<title>高支架工程 - 基础验收</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/ywlz/css/default.css"> </LINK>
</head>
<body>
<div class="container-fluid">
	<p></p>
	<div class="row-fluid">
		<div class="B-small-from-table-autoConcise">
			<form method="post" id="queryForm">
			<input type="hidden" id="QUERY_WXY_GDMB_GC_UID" fieldname="WXY_GDMB_GC_UID" value="<%=id %>"
		operation="=" logic="and" />
			</form>
			<div style="height:5px;"> </div>	
			<div id="div1">
            <form class="form-horizontal" id="tfkwForm">
<input id="TFKW_WXY_GDMB_GC_UID" type="hidden"  fieldname="WXY_GDMB_GC_UID" name="WXY_GDMB_GC_UID">
<input id="TFKW_WXY_GDMB_UID" type="hidden"  fieldname="WXY_GDMB_UID" name="WXY_GDMB_UID">
<input id="TFKW_GONGCHENG_UID" type="hidden"  fieldname="GONGCHENG_UID" name="GONGCHENG_UID">
<input id="TFKW_WXY_TYPE" type="hidden"  fieldname="WXY_TYPE" name="WXY_TYPE" value="1">
<input id="TFKW_STATUS" type="hidden"  fieldname="STATUS" name="STATUS">
<table border="1" width="100%" cellpadding="0" cellspacing="0" class="content">
			<tr>
				<td class="yw-title">
					<h4 ><span id="spyjtxhid">基础验收<span id="tfkwmsg" style="color: red;"></span></span>
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
				<th width="15%" class="right-border bottom-border text-right">高支模开始时间</th>
				<td width="20%" class="right-border bottom-border">
					<input class="span12" style="width:94%" id="YUJI_DATE" type="text" readonly="readonly" check-type="required" fieldname="YUJI_DATE" name = "YUJI_DATE" />			
				</td>
				<td width="35%" class="right-border bottom-border" colspan="2">
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">实施前安全交底情况</th>
				<td class="right-border bottom-border" colspan="3">
					 <textarea rows="" class="span12" style="width:98%" id="ANQUAN_QINGKUANG" readonly="readonly" fieldname="ANQUAN_QINGKUANG" name = "ANQUAN_QINGKUANG"></textarea>			
				</td>
				
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">基础硬化（地耐力）情况</th>
				<td colspan="3" class="right-border bottom-border">
				    <textarea rows="" class="span12" style="width:98%" id="JICHU_YINGHUA" readonly="readonly" fieldname="JICHU_YINGHUA" name = "JICHU_YINGHUA"></textarea>
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">基础书面验收情况</th>
				<td colspan="3" class="right-border bottom-border">
				    <textarea rows="" class="span12" style="width:98%" id="JICHU_SHUMIAN" readonly="readonly" fieldname="JICHU_SHUMIAN" name = "JICHU_SHUMIAN"></textarea>
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
<script type="text/javascript">

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
	var data1 = combineQuery.getQueryCombineData(queryForm, frmPost);
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

		</script>
</body>
</html>