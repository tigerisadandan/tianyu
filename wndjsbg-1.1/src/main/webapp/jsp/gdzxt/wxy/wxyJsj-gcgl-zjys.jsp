<%@page import="com.ccthanking.framework.Constants"%>
<%@page import="com.ccthanking.framework.common.User"%>
<%@page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<% 
String zjysfjlb=Constants.FS_FILEUPLOAD_FJLB_WXY_JSJ_ZJYS;
User user = RestContext.getCurrentUser();
String username = user.getName();
%>
<form class="form-horizontal" id="ZJYSForm">
<input type="hidden" id="zjysywid" fieldname="ywid" name="ywid" />
<input id="ZJYS_WXY_JSJ_GC_UID" type="hidden"  fieldname="WXY_JSJ_GC_UID" name="WXY_JSJ_GC_UID">
<input id="ZJYS_WXY_JSJ_UID" type="hidden"  fieldname="WXY_JSJ_UID" name="WXY_JSJ_UID">
<input id="ZJYS_GONGCHENG_UID" type="hidden"  fieldname="GONGCHENG_UID" name="GONGCHENG_UID">
<input id="ZJYS_WXY_TYPE" type="hidden"  fieldname="WXY_TYPE" name="WXY_TYPE" value="2">
<input id="ZJYS_STATUS" type="hidden"  fieldname="STATUS" name="STATUS">
<table border="1" width="100%" cellpadding="0" cellspacing="0" class="content">
			<tr>
				<td class="yw-title">
					<h4 >中间验收：每三步<span id="ZJYSmsg" style="color: red;">(未申请)</span></span>
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
				<th width="15%" class="right-border bottom-border text-right">立杆间距、垂直度等是否满足方案要求</th>
				<td width="20%"  class="right-border bottom-border">
					<input class="span12" style="width:94%" type="radio" readonly="readonly" fieldname="LIGAN_JIANJU_Y" kind="dic" src="SF" />			
				</td>
				<th width="15%" class="right-border bottom-border text-right">水平横杆步距及间距是否满足方案要求</th>
				<td width="20%" class="right-border bottom-border">
					<input class="span12" style="width:94%" type="radio" readonly="readonly" fieldname="SHUIPING_HENGGAN_Y" kind="dic" src="SF" />			
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">扫地杆设置情况</th>
				<td colspan="3" class="right-border bottom-border">
				    <textarea rows="" class="span12" style="width:98%" id="SAODIGAN" readonly="readonly" fieldname="SAODIGAN" name = "SAODIGAN"></textarea>
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">钢管扣件检测验收情况</th>
				<td colspan="3" class="right-border bottom-border">
				    <textarea rows="" class="span12" style="width:98%" id="GANGGUAN_KOUJIAN" readonly="readonly" fieldname="GANGGUAN_KOUJIAN" name = "GANGGUAN_KOUJIAN"></textarea>
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">连墙件设置情况</th>
				<td colspan="3" class="right-border bottom-border">
				    <textarea rows="" class="span12" style="width:98%" id="LIANQIANGJIAN" readonly="readonly" fieldname="LIANQIANGJIAN" name = "LIANQIANGJIAN"></textarea>
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">护身栏杆及安全网设置情况</th>
				<td colspan="3" class="right-border bottom-border">
				    <textarea rows="" class="span12" style="width:98%" id="HUSHEN_LANGAN" readonly="readonly" fieldname="HUSHEN_LANGAN" name = "HUSHEN_LANGAN"></textarea>
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">与墙体间距过大防护设置情况</th>
				<td colspan="3" class="right-border bottom-border">
				    <textarea rows="" class="span12" style="width:98%" id="QIANGTI_JIANJU" readonly="readonly" fieldname="QIANGTI_JIANJU" name = "QIANGTI_JIANJU"></textarea>
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">人员持证上岗情况</th>
				<td colspan="3" class="right-border bottom-border">
				    <textarea rows="" class="span12" style="width:98%" id="CHIZHENG_SHANGGANG" readonly="readonly" fieldname="CHIZHENG_SHANGGANG" name = "CHIZHENG_SHANGGANG"></textarea>
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">上传文件</th>
	       	 	<td class="bottom-border right-border" style="width:32%" colspan="3">
	         		<table role="presentation" id="zjysfj"  class="table table-striped" style="margin-bottom:0px;width: 96.1%">
											<tbody fjlb="<%=zjysfjlb%>" id="zjysTb" onlyView="true"
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

 <!-- 条件form -->
<form class="form-inline" role="form" id="ZJYSQueryForm">
	<input type="hidden" id="QUERY_ZJYS_GONGCHENG_UID" fieldname="GONGCHENG_UID"
		operation="=" logic="and" />
	<input type="hidden" fieldname="WXY_TYPE"
		 operation="=" logic="and" value="2"/>
</form>
<script type="text/javascript">

			$(document).ready(function(){
				
				 $("#ZJYS_GONGCHENG_UID").val(parent.document.getElementById("GDZXT_XM_ID").value);
				 $("#QUERY_ZJYS_GONGCHENG_UID").val(parent.document.getElementById("GDZXT_XM_ID").value);
				 ZJYSInit();
				});

			function ZJYSInit(){
				ZJYSQuery();
			}
			
			function ZJYSQuery(){
				var data1 = combineQuery.getQueryCombineData(ZJYSQueryForm, frmPost);
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
							$("#ZJYSForm").find("input,textarea,radio").each(function(i,item){
								 $(item).attr('disabled','disabled');
								   if($(item).attr('id')=="DATE"){
									   $(item).attr('id','');
								   }
								});
						}else if(resultobj.STATUS=="30"){
							//禁用非审核内容
							$("#ZJYSForm").find("input,textarea").each(function(i,item){
								if($(item).attr('status')!="true"){
								 $(item).attr('disabled','disabled');
								   if($(item).attr('id')=="DATE"){
									   $(item).attr('id','');
								   }
								}
							});
							$("#sum").show();
							$("#ZJYSForm").setFormValues(resultobj);

							//默认
							$("#ZJYSmsg").html("(待审核)");
							$("#ZJYS_DATE").val(new Date().format("yyyy-MM-dd"));
							$("#ZJYS_SHENHE_REN").val('<%=username %>');
							$("#ZJYS_STATUS").val('33');
						}else if(resultobj.STATUS=="33"){
							$("#ZJYSmsg").html("(已审核)");
							$("#ZJYSForm").find("input,textarea,radio").each(function(i,item){
								   $(item).attr('disabled','disabled');
								   if($(item).attr('id')=="DATE"){
									   $(item).attr('id','');
								   }
								});
							$("#sum").hide();
							$("#ZJYSForm").setFormValues(resultobj);
						}else if(resultobj.STATUS=="32"){
							$("#ZJYSForm").find("input,textarea,radio").each(function(i,item){
								   $(item).attr('disabled','disabled');
								   if($(item).attr('id')=="DATE"){
									   $(item).attr('id','');
								   }
								});
							$("#ZJYSmsg").html("(审核未通过)");
							$("#ZJYSForm").setFormValues(resultobj);
							$("#sum").hide();
							$("#zt1ZJYS").attr('checked','checked');
						}
						return true;
						}
						}
				});		
			}
			
			
		</script>