<%@page import="com.ccthanking.framework.common.User"%>
<%@page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<% 
User user = RestContext.getCurrentUser();
String username = user.getName();
%>
<form class="form-horizontal" id="CCForm">
<input id="CC_WXY_JSJ_GC_UID" type="hidden"  fieldname="WXY_JSJ_GC_UID" name="WXY_JSJ_GC_UID">
<input id="CC_WXY_JSJ_UID" type="hidden"  fieldname="WXY_JSJ_UID" name="WXY_JSJ_UID">
<input id="CC_GONGCHENG_UID" type="hidden"  fieldname="GONGCHENG_UID" name="GONGCHENG_UID">
<input id="CC_WXY_TYPE" type="hidden"  fieldname="WXY_TYPE" name="WXY_TYPE" value="3">
<input id="CC_STATUS" type="hidden"  fieldname="STATUS" name="STATUS">
<table border="1" width="100%" cellpadding="0" cellspacing="0" class="content">
			<tr>
				<td class="yw-title">
					<h4 >拆除<span id="CCmsg" style="color: red;">(未申请)</span></span>
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
				<th width="15%" class="right-border bottom-border text-right">拆除完成时间</th>
				<td width="20%"  class="right-border bottom-border">
					<input class="span12" style="width:94%" type="text" readonly="readonly" fieldname="JSJ_DATE"  />			
				</td>
				<th width="15%" class="right-border bottom-border text-right">有无先拆连墙件后拆架体情况</th>
				<td width="20%" class="right-border bottom-border">
					<input class="span12" style="width:94%" type="radio" readonly="readonly" fieldname="LIANQIANGJIAN_Y" kind="dic" src="SF" />			
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">人员持证上岗及安全交底情况</th>
				<td colspan="3" class="right-border bottom-border">
				    <textarea rows="" class="span12" style="width:98%" id="SAODIGAN" readonly="readonly" fieldname="CHIZHENG_SHANGGANG" name = "CHIZHENG_SHANGGANG"></textarea>
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">拆除设警戒区域专人监护</th>
				<td colspan="3" class="right-border bottom-border">
				    <textarea rows="" class="span12" style="width:98%" id="CHAICHU_JINGJIE" readonly="readonly" fieldname="CHAICHU_JINGJIE" name = "CHAICHU_JINGJIE"></textarea>
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
<form class="form-inline" role="form" id="CCQueryForm">
	<input type="hidden" id="QUERY_CC_GONGCHENG_UID" fieldname="GONGCHENG_UID"
		operation="=" logic="and" />
	<input type="hidden" fieldname="WXY_TYPE"
		 operation="=" logic="and" value="3"/>
</form>
<script type="text/javascript">

			$(document).ready(function(){
				 
				 $("#CC_GONGCHENG_UID").val(parent.document.getElementById("GDZXT_XM_ID").value);
				 $("#QUERY_CC_GONGCHENG_UID").val(parent.document.getElementById("GDZXT_XM_ID").value);
				 CCInit();
				});

			function CCInit(){
				CCQuery();
			}
			
			
			
			function CCQuery(){
				var data1 = combineQuery.getQueryCombineData(CCQueryForm, frmPost);
				var data = {
					msg : data1
				};
				$.ajax({
					url : "${pageContext.request.contextPath }/wxy/wxyJsjGcController/query",
					data : data,
					cache : false,
					async : false,
					dataType : "json",
					type : 'post',
					success : function(response) {
						if(response.msg!="0"){
						var resultobj = defaultJson.dealResultJson(response.msg);
						if(resultobj.STATUS=="0"){
							$("#CCForm").find("input,textarea,radio").each(function(i,item){
								 $(item).attr('disabled','disabled');
								   if($(item).attr('id')=="DATE"){
									   $(item).attr('id','');
								   }
								});
						}else if(resultobj.STATUS=="30"){
							//禁用非审核内容
							$("#CCForm").find("input,textarea").each(function(i,item){
								if($(item).attr('status')!="true"){
								 $(item).attr('disabled','disabled');
								   if($(item).attr('id')=="DATE"){
									   $(item).attr('id','');
								   }
								}
							});
							$("#sum").show();
							$("#CCForm").setFormValues(resultobj);

							//默认
							$("#CCmsg").html("(待审核)");
							$("#CC_DATE").val(new Date().format("yyyy-MM-dd"));
							$("#CC_SHENHE_REN").val('<%=username %>');
							$("#CC_STATUS").val('33');
						}else if(resultobj.STATUS=="33"){
							$("#CCmsg").html("(已审核)");
							$("#CCForm").find("input,textarea,radio").each(function(i,item){
								   $(item).attr('disabled','disabled');
								   if($(item).attr('id')=="DATE"){
									   $(item).attr('id','');
								   }
								});
							$("#sum").hide();
							$("#CCForm").setFormValues(resultobj);
						}else if(resultobj.STATUS=="32"){
							$("#CCForm").find("input,textarea,radio").each(function(i,item){
								   $(item).attr('disabled','disabled');
								   if($(item).attr('id')=="DATE"){
									   $(item).attr('id','');
								   }
								});
							$("#CCmsg").html("(审核未通过)");
							$("#CCForm").setFormValues(resultobj);
							$("#sum").hide();
							$("#zt1CC").attr('checked','checked');
						}
						return true;
						}else{//没提交 
							$("#CCForm").find("input,textarea,radio").each(function(i,item){
								$(item).attr('placeholder','disabled');
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