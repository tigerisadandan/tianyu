<%@page import="com.ccthanking.framework.Constants"%>
<%@page import="com.ccthanking.framework.common.User"%>
<%@page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<% 
String jcysfjlb=Constants.FS_FILEUPLOAD_FJLB_WXY_JSJ_JCYS; 
User user = RestContext.getCurrentUser();
String username = user.getName();
%>
<form class="form-horizontal" id="JCYSForm">
<input type="hidden" id="jcysywid" fieldname="ywid" name="ywid" />
<input id="JCYS_WXY_JSJ_GC_UID" type="hidden"  fieldname="WXY_JSJ_GC_UID" name="WXY_JSJ_GC_UID">
<input id="JCYS_WXY_JSJ_UID" type="hidden"  fieldname="WXY_JSJ_UID" name="WXY_JSJ_UID">
<input id="JCYS_GONGCHENG_UID" type="hidden"  fieldname="GONGCHENG_UID" name="GONGCHENG_UID">
<input id="JCYS_WXY_TYPE" type="hidden"  fieldname="WXY_TYPE" name="WXY_TYPE" value="1">
<input id="JCYS_STATUS" type="hidden"  fieldname="STATUS" name="STATUS">
<table border="1" width="100%" cellpadding="0" cellspacing="0" class="content">
			<tr>
				<td class="yw-title">
					<h4 ><span id="spyjtxhid">基础验收<span id="JCYSmsg" style="color: red;">(未申请)</span></span>
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
				<th width="15%" class="right-border bottom-border text-right">搭设开始时间</th>
				<td width="20%" class="right-border bottom-border">
					<input class="span12" style="width:94%" id="JSJ_DATE" type="text" readonly="readonly" check-type="required" fieldname="JSJ_DATE" name = "JSJ_DATE" />			
				</td>
				<td width="35%" class="right-border bottom-border" colspan="2">
								
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">实施前安全交底情况</th>
				<td colspan="3" class="right-border bottom-border">
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
				<th width="15%" class="right-border bottom-border text-right">悬挑型钢长度、锚固及搁置点实施情况</th>
				<td colspan="3" class="right-border bottom-border">
				    <textarea rows="" class="span12" style="width:98%" id="GANG_MAOGU_GEZHIDIAN" readonly="readonly" fieldname="GANG_MAOGU_GEZHIDIAN" name = "GANG_MAOGU_GEZHIDIAN"></textarea>
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">钢丝绳斜拉情况</th>
				<td colspan="3" class="right-border bottom-border">
				    <textarea rows="" class="span12" style="width:98%" id="GANGSI_XIELA" readonly="readonly" fieldname="GANGSI_XIELA" name = "GANGSI_XIELA"></textarea>
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">基础书面验收情况</th>
				<td colspan="3" class="right-border bottom-border">
				    <textarea rows="" class="span12" style="width:98%" id="SHUMIAN_YANSHOU" readonly="readonly" fieldname="SHUMIAN_YANSHOU" name = "SHUMIAN_YANSHOU"></textarea>
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">上传文件</th>
	       	 	<td class="bottom-border right-border" style="width:32%" colspan="3">
	         		<table role="presentation" id="jcysfj"  class="table table-striped" style="margin-bottom:0px;width: 96.1%">
											<tbody fjlb="<%=jcysfjlb%>" id="jcysTb"  onlyView="true"
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
					<h4 ><span id="spyjtxhid">基础硬化图片</span></span>
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
<form class="form-inline" role="form" id="JCYSQueryForm">
	<input type="hidden" id="QUERY_JCYS_GONGCHENG_UID" fieldname="GONGCHENG_UID"
		operation="=" logic="and" />
	<input type="hidden" fieldname="WXY_TYPE"
		 operation="=" logic="and" value="1"/>
</form>
<script type="text/javascript">

			$(document).ready(function(){
				
				 $("#JCYS_GONGCHENG_UID").val(parent.document.getElementById("GDZXT_XM_ID").value);
				 $("#QUERY_JCYS_GONGCHENG_UID").val(parent.document.getElementById("GDZXT_XM_ID").value);

				 tfkwInit();
				});

			function tfkwInit(){
				JCYSQuery();
			}
			 
			
			function JCYSQuery(){
				var data1 = combineQuery.getQueryCombineData(JCYSQueryForm, frmPost);
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
							$("#JCYSForm").find("input,textarea,radio").each(function(i,item){
								 $(item).attr('disabled','disabled');
								   if($(item).attr('id')=="DATE"){
									   $(item).attr('id','');
								   }
								});
						}else if(resultobj.STATUS=="30"){
							//禁用非审核内容
							$("#JCYSForm").find("input,textarea").each(function(i,item){
								if($(item).attr('status')!="true"){
								 $(item).attr('disabled','disabled');
								   if($(item).attr('id')=="DATE"){
									   $(item).attr('id','');
								   }
								}
							});
							$("#sum").show();
							$("#JCYSForm").setFormValues(resultobj);

							//默认
							$("#JCYSmsg").html("(待审核)");
							queryFileData($("#JCYS_WXY_JSJ_GC_UID").val(), "", "", "460010");
						}else if(resultobj.STATUS=="33"){
							$("#JCYSmsg").html("(已审核)");
							$("#JCYSForm").find("input,textarea,radio").each(function(i,item){
								   $(item).attr('disabled','disabled');
								   if($(item).attr('id')=="DATE"){
									   $(item).attr('id','');
								   }
								});
							$("#sum").hide();
							
							queryFileData($("#JCYS_WXY_JSJ_GC_UID").val(), "", "", "460010");
							$("#JCYSForm").setFormValues(resultobj);
						}else if(resultobj.STATUS=="32"){
							$("#JCYSForm").find("input,textarea,radio").each(function(i,item){
								   $(item).attr('disabled','disabled');
								   if($(item).attr('id')=="DATE"){
									   $(item).attr('id','');
								   }
								});
							$("#JCYSmsg").html("(审核未通过)");
							$("#JCYSForm").setFormValues(resultobj);
							$("#sum").hide();
							$("#zt1").attr('checked','checked');

							queryFileData($("#JCYS_WXY_JSJ_GC_UID").val(), "", "", "460010");
						}
						return true;
						}else{//没提交 
							$("#JCYSForm").find("input,textarea,radio").each(function(i,item){
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
			
			function selectJcysFile(obj){
				$("#jcysywid").val($("#ywid").val());
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
				setFileData(ywid,"","","460010","0",'<%=jcysfjlb %>');
				var inputArr = $(".myKeyValueDiv input");
				for(var xx=0;xx<inputArr.length;xx++){
					if(inputArr[xx].getAttribute("cond")=="true"){
						if(inputArr[xx].getAttribute("condName")=="fjlb"){
							inputArr[xx].value=$(obj).attr("fjlb");
						}
					}
				}
				if(isIE()){
					initdataswf(ywid,"","","460010","0",'<%=jcysfjlb %>','<%=jcysfjlb %>');
					//initdataswf(ywid,"","","470010","0",result.CLK_UID,"1609");
					document.getElementById("plfileuploadswf").click();
				}else{
					document.getElementById("fileupload_btn").click();
				}	
			}
		</script>