<%@page import="com.ccthanking.framework.common.User"%>
<%@page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext"%>
<%@page import="com.ccthanking.framework.Constants"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<% 
String pmfjlb=Constants.FS_FILEUPLOAD_FJLB_WXY_SJK_PM; 
User userpm = RestContext.getCurrentUser();
String usernamepm = userpm.getName();
%>
<form class="form-horizontal" id="pmForm">
<input type="hidden" id="pmywid" fieldname="ywid" name="ywid" />
<input id="PM_WXY_SJK_GC_UID" type="hidden"  fieldname="WXY_SJK_GC_UID" name="WXY_SJK_GC_UID">
<input id="PM_WXY_SJK_UID" type="hidden"  fieldname="WXY_SJK_UID" name="WXY_SJK_UID">
<input id="PM_GONGCHENG_UID" type="hidden"  fieldname="GONGCHENG_UID" name="GONGCHENG_UID">
<input id="PM_WXY_TYPE" type="hidden"  fieldname="WXY_TYPE" name="WXY_TYPE" value="2">
<input id="PM_STATUS" type="hidden"  fieldname="STATUS" name="STATUS">

<table border="1" width="100%" cellpadding="0" cellspacing="0" class="content">
			<tr>
				<td class="yw-title">
					<h4 >喷锚（支撑）<span id="pmmsg" style="color: red;">(未申请)</span></span>
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
				<th width="15%" class="right-border bottom-border text-right">喷锚（支撑）是否按方案执行</th>
				<td class="right-border bottom-border">
					<input class="span12" style="width:94%" type="radio" readonly="readonly" fieldname="FANGAN_ZHIXING_Y" name="FANGAN_ZHIXING_Y_PM" kind="dic" src="SF" />			
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">喷锚（支撑）前安全交底情况</th>
				<td colspan="3" class="right-border bottom-border">
				    <textarea rows="" class="span12" style="width:98%"  readonly="readonly" fieldname="JIAODI_QINGKUANG" ></textarea>
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">排水措施实施情况</th>
				<td colspan="3" class="right-border bottom-border">
				    <textarea rows="" class="span12" style="width:98%" id="PAISHUI_QINGKUANG" readonly="readonly" fieldname="PAISHUI_QINGKUANG" name = "PAISHUI_QINGKUANG"></textarea>
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">上传文件</th>
	       	 	<td class="bottom-border right-border" style="width:32%" colspan="3">
	         		<table role="presentation" id="pmfj"  class="table table-striped" style="margin-bottom:0px;width: 96.1%">
											<tbody fjlb="<%=pmfjlb%>" id="pmTb"
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
<form class="form-inline" role="form" id="pmQueryForm">
	<input type="hidden" id="QUERY_PM_GONGCHENG_UID" fieldname="GONGCHENG_UID"
		operation="=" logic="and" />
	<input type="hidden" fieldname="WXY_TYPE"
		 operation="=" logic="and" value="2"/>
</form>
<script type="text/javascript">

			$(document).ready(function(){
				// setStyle(pmForm);
				 $("#PM_GONGCHENG_UID").val(parent.document.getElementById("GDZXT_XM_ID").value);
				 $("#QUERY_PM_GONGCHENG_UID").val(parent.document.getElementById("GDZXT_XM_ID").value);
				 pmInit();
				});

			function pmInit(){
				pmQuery();
			}
			
			function pmQuery(){
				var data1 = combineQuery.getQueryCombineData(pmQueryForm, frmPost);
				var data = {
					msg : data1
				};
				$.ajax({
					url : "${pageContext.request.contextPath }/wxy/wxySjkGcController/query",
					data : data,
					cache : false,
					async : false,
					dataType : "json",
					type : 'post',
					success : function(response) {
						if(response.msg!="0"){
						var resultobj = defaultJson.dealResultJson(response.msg);
						if(resultobj.STATUS=="0"){
							$("#pmForm").find("input,textarea,radio").each(function(i,item){
								 $(item).attr('disabled','disabled');
								   if($(item).attr('id')=="DATE"){
									   $(item).attr('id','');
								   }
								});
						}else if(resultobj.STATUS=="30"){
							//禁用非审核内容
							$("#pmForm").find("input,textarea,radio").each(function(i,item){
								if($(item).attr('status')!="true"){
								 $(item).attr('disabled','disabled');
								   if($(item).attr('id')=="DATE"){
									   $(item).attr('id','');
								   }
								}
							});
							$("#pmBut").show();
							$("#pmTb").attr("onlyView","true"); 
							$("#pmForm").setFormValues(resultobj);

							//默认
							$("#pmmsg").html("(待审核)");
							$("#PM_DATE").val(new Date().format("yyyy-MM-dd"));
							$("#PM_SHENHE_REN").val('<%=usernamepm %>');
							$("#PM_STATUS").val('33');
						}else if(resultobj.STATUS=="33"){
							$("#pmmsg").html("(已审核)");
							$("#pmForm").find("input,textarea").each(function(i,item){
								   $(item).attr('disabled','disabled');
								   if($(item).attr('id')=="DATE"){
									   $(item).attr('id','');
								   }
								});
							$("#pmBut").hide();
							$("#addFileSpanPm").hide();
							$("#pmTb").attr("onlyView","true"); 
							$("#pmForm").setFormValues(resultobj);
						}else if(resultobj.STATUS=="32"){
							$("#zt1pm").attr('checked','checked');
							$("#pmmsg").html("(审核未通过)");
							$("#pmForm").setFormValues(resultobj);
							$("#pmBut").hide();
							$("#pmTb").attr("onlyView","true"); 
							$("#pmForm").find("input,textarea,radio").each(function(i,item){
								   $(item).attr('disabled','disabled');
								   if($(item).attr('id')=="DATE"){
									   $(item).attr('id','');
								   }
								});
						}
						
						return true;
						}else{//没提交 
							$("#pmForm").find("input,textarea,radio").each(function(i,item){
								 $(item).attr('disabled','disabled');
								   if($(item).attr('id')=="DATE"){
									   $(item).attr('id','');
								   }
								});
						} 
						}
				});		
			}
			
			function selectPmFile(obj){
				$("#pmywid").val($("#ywid").val());
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
				setFileData(ywid,"","","460010","0",'<%=pmfjlb %>');
				var inputArr = $(".myKeyValueDiv input");
				for(var xx=0;xx<inputArr.length;xx++){
					if(inputArr[xx].getAttribute("cond")=="true"){
						if(inputArr[xx].getAttribute("condName")=="fjlb"){
							inputArr[xx].value=$(obj).attr("fjlb");
						}
					}
				}
				if(isIE()){
					initdataswf(ywid,"","","460010","0",'<%=pmfjlb %>','<%=pmfjlb %>');
					//initdataswf(ywid,"","","470010","0",result.CLK_UID,"1609");
					document.getElementById("plfileuploadswf").click();
				}else{
					document.getElementById("fileupload_btn").click();
				}	
			}
		</script>