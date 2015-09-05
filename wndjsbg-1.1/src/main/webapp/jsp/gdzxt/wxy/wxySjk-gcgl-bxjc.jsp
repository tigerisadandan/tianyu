<%@page import="com.ccthanking.framework.common.User"%>
<%@page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<% 
User userbxjc = RestContext.getCurrentUser();
String usernamebxjc = userbxjc.getName();
%>
<form class="form-horizontal" id="bxjcForm">
<input id="BXJC_WXY_SJK_GC_UID" type="hidden"  fieldname="WXY_SJK_GC_UID" name="WXY_SJK_GC_UID">
<input id="BXJC_WXY_SJK_UID" type="hidden"  fieldname="WXY_SJK_UID" name="WXY_SJK_UID">
<input id="BXJC_GONGCHENG_UID" type="hidden"  fieldname="GONGCHENG_UID" name="GONGCHENG_UID">
<input id="BXJC_WXY_TYPE" type="hidden"  fieldname="WXY_TYPE" name="WXY_TYPE" value="3">
<input id="BXJC_STATUS" type="hidden"  fieldname="STATUS" name="STATUS">
<table border="1" width="100%" cellpadding="0" cellspacing="0" class="content">
			<tr>
				<td class="yw-title">
					<h4 ><span id="spyjtxhid">变形监测<span id="bxjcmsg" style="color: red;">(未申请)</span></span>
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
				<th width="15%" class="right-border bottom-border text-right">喷锚（支撑）开始时间</th>
				<td width="20%" class="right-border bottom-border">
					<input class="span12" style="width:94%" id="BEGIN_DATE" type="text" readonly="readonly" check-type="required" fieldname="BEGIN_DATE" name = "BEGIN_DATE" />			
				</td>
				
				<th width="15%" class="right-border bottom-border text-right">喷锚（支撑）结束时间</th>
				<td width="20%" class="right-border bottom-border">
					<input class="span12" style="width:94%" id="END_DATE" type="text" readonly="readonly" fieldname="END_DATE" name = "END_DATE" />			
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">监测单位资质</th>
				<td colspan="3" class="right-border bottom-border">
				    <textarea rows="" class="span12" style="width:98%" id="JIANCE_ZIZHI" readonly="readonly" fieldname="JIANCE_ZIZHI" name = "JIANCE_ZIZHI"></textarea>
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">基坑变形检测情况</th>
				<td colspan="3" class="right-border bottom-border">
				    <textarea rows="" class="span12" style="width:98%" id="JIANCE_QINGKUANG" readonly="readonly" fieldname="JIANCE_QINGKUANG" name = "JIANCE_QINGKUANG"></textarea>
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">变形异常采取的措施</th>
				<td colspan="3" class="right-border bottom-border">
				    <textarea rows="" class="span12" style="width:98%" id="YICHANG_CUOSHI" readonly="readonly" fieldname="YICHANG_CUOSHI" name = "YICHANG_CUOSHI"></textarea>
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
						</form>
						
 <!-- 条件form -->
<form class="form-inline" role="form" id="bxjcQueryForm">
	<input type="hidden" id="QUERY_BXJC_GONGCHENG_UID" fieldname="GONGCHENG_UID"
		operation="=" logic="and" />
	<input type="hidden" fieldname="WXY_TYPE"
		 operation="=" logic="and" value="3"/>
</form>  
<script type="text/javascript">

$(document).ready(function(){
	
	 $("#BXJC_GONGCHENG_UID").val(parent.document.getElementById("GDZXT_XM_ID").value);
	 $("#QUERY_BXJC_GONGCHENG_UID").val(parent.document.getElementById("GDZXT_XM_ID").value);

	 bxjcInit();
	});

function bxjcInit(){
	bxjcQuery();
	$("#bxjcForm").find("input,textarea,radio").each(function(i,item){
		$(item).attr('placeholder','');
		 $(item).attr('disabled','disabled');
		   if($(item).attr('id')=="DATE"){
			   $(item).attr('id','');
		   }
		});
}

function bxjcQuery(){

	var data1 = combineQuery.getQueryCombineData(bxjcQueryForm, frmPost);
	var data = {
		msg : data1
	};
	$.ajax({
		url :"${pageContext.request.contextPath }/wxy/wxySjkGcController/query",
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
				$("#bxjcForm").find("input,textarea,radio").each(function(i,item){
					if($(item).attr('status')!="true"){
					 $(item).attr('disabled','disabled');
					   if($(item).attr('id')=="DATE"){
						   $(item).attr('id','');
					   }
					}
				});
				$("#bxjcBut").show();
				$("#bxjcTb").attr("onlyView","true"); 
				$("#bxjcForm").setFormValues(resultobj);
				//默认
				$("#bxjcmsg").html("(待审核)");
				$("#BXJC_DATE").val(new Date().format("yyyy-MM-dd"));
				$("#BXJC_SHENHE_REN").val('<%=usernamebxjc %>');
				$("#BXJC_STATUS").val('33');
				
			}else if(resultobj.STATUS=="33"){
				$("#bxjcmsg").html("(已审核)");
				$("#bxjcForm").find("input,textarea").each(function(i,item){
					   $(item).attr('disabled','disabled');
					   if($(item).attr('id')=="DATE"){
						   $(item).attr('id','');
					   }
					});
				$("#bxjcBut").hide();
				$("#bxjcTb").attr("onlyView","true"); 
				$("#bxjcForm").setFormValues(resultobj);
			}else if(resultobj.STATUS=="32"){
				$("#zt1bxjc").attr('checked','checked');
				$("#bxjcmsg").html("(审核未通过)");
				$("#bxjcForm").setFormValues(resultobj);
				$("#bxjcBut").hide();
				$("#bxjcTb").attr("onlyView","true"); 
				$("#bxjcForm").find("input,textarea,radio").each(function(i,item){
					   $(item).attr('disabled','disabled');
					   if($(item).attr('id')=="DATE"){
						   $(item).attr('id','');
					   }
					});
			}
			return true;
			}else{//没提交 
				$("#bxjcForm").find("input,textarea,radio").each(function(i,item){
					$(item).attr('placeholder','');
					 $(item).attr('disabled','disabled');
					   if($(item).attr('id')=="DATE"){
						   $(item).attr('id','');
					   }
					});
			} 
			}
	});		
}


			
		</script>