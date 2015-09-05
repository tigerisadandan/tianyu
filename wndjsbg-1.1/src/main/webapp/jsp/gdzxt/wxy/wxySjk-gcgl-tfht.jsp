<%@page import="com.ccthanking.framework.Constants"%>
<%@page import="com.ccthanking.framework.common.User"%>
<%@page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<% 
String tfhtfjlb=Constants.FS_FILEUPLOAD_FJLB_WXY_SJK_TFHT; 
User usertfht = RestContext.getCurrentUser();
String usernametfht = usertfht.getName();%>
<form class="form-horizontal" id="tfhtForm">
<input type="hidden" id="tfhtywid" fieldname="ywid" name="ywid" />
<input id="TFHT_WXY_SJK_GC_UID" type="hidden"  fieldname="WXY_SJK_GC_UID" name="WXY_SJK_GC_UID">
<input id="TFHT_WXY_SJK_UID" type="hidden"  fieldname="WXY_SJK_UID" name="WXY_SJK_UID">
<input id="TFHT_GONGCHENG_UID" type="hidden"  fieldname="GONGCHENG_UID" name="GONGCHENG_UID">
<input id="TFHT_WXY_TYPE" type="hidden"  fieldname="WXY_TYPE" name="WXY_TYPE" value="4">
<input id="TFHT_STATUS" type="hidden"  fieldname="STATUS" name="STATUS">

<table border="1" width="100%" cellpadding="0" cellspacing="0" class="content">
			<tr>
				<td class="yw-title">
					<h4 ><span id="spyjtxhid">土方回填<span id="tfhtmsg" style="color: red;">(未申请)</span></span>
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
				<th width="15%" class="right-border bottom-border text-right">土方回填开始时间</th>
				<td width="20%" class="right-border bottom-border">
					<input class="span12" style="width:94%" id="BEGIN_DATE" type="text" readonly="readonly" check-type="required" fieldname="BEGIN_DATE" name = "BEGIN_DATE" />			
				</td>
				
				<th width="15%" class="right-border bottom-border text-right">土方回填结束时间</th>
				<td width="20%" class="right-border bottom-border">
					<input class="span12" style="width:94%" id="END_DATE" type="text" readonly="readonly" fieldname="END_DATE" name = "END_DATE" />			
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">上传文件</th>
	       	 	<td class="bottom-border right-border" style="width:32%" colspan="3">
	         		<table role="presentation" id="tfhtfj"  class="table table-striped" style="margin-bottom:0px;width: 96.1%">
											<tbody fjlb="<%=tfhtfjlb%>" id="tfhtTb"
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
<form class="form-inline" role="form" id="tfhtQueryForm">
	<input type="hidden" id="QUERY_TFHT_GONGCHENG_UID" fieldname="GONGCHENG_UID"
		operation="=" logic="and" />
	<input type="hidden" fieldname="WXY_TYPE"
		 operation="=" logic="and" value="4"/>
</form>  
<script type="text/javascript">

$(document).ready(function(){
	
	// setStyle(tfhtForm);
	 $("#tfhtForm").find("input,textarea,radio").each(function(i,item){
		 $(item).attr('disabled','disabled');
		});
	 $("#TFHT_GONGCHENG_UID").val(parent.document.getElementById("GDZXT_XM_ID").value);
	 $("#QUERY_TFHT_GONGCHENG_UID").val(parent.document.getElementById("GDZXT_XM_ID").value);
	 tfhtInit();
	});

function tfhtInit(){
	tfhtQuery();
}

function tfhtQuery(){
	var data1 = combineQuery.getQueryCombineData(tfhtQueryForm, frmPost);
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
				$("#tfhtForm").find("input,textarea,radio").each(function(i,item){
					 $(item).attr('disabled','disabled');
					 $(item).attr('placeholder','');
					   if($(item).attr('id')=="DATE"){
						   $(item).attr('id','');
					   }
					});
			}else if(resultobj.STATUS=="30"){
				//禁用非审核内容
				$("#tfhtForm").find("input,textarea,radio").each(function(i,item){
					if($(item).attr('status')!="true"){
					 $(item).attr('disabled','disabled');
					   if($(item).attr('id')=="DATE"){
						   $(item).attr('id','');
					   }
					}
				});
				$("#tfhtBut").show();
				$("#tfhtTb").attr("onlyView","true"); 
				$("#tfhtForm").setFormValues(resultobj);

				//默认
				$("#tfhtmsg").html("(待审核)");
				$("#TFHT_DATE").val(new Date().format("yyyy-MM-dd"));
				$("#TFHT_SHENHE_REN").val('<%=usernametfht %>');
				$("#TFHT_STATUS").val('33');
			}else if(resultobj.STATUS=="33"){
				$("#tfhtForm").setFormValues(resultobj);
				$("#tfhtmsg").html("(已审核)");
				$("#tfhtForm").find("input,textarea").each(function(i,item){
					   $(item).attr('disabled','disabled');
					   if($(item).attr('id')=="DATE"){
						   $(item).attr('id','');
					   }
					});
				$("#tfhtBut").hide();
				$("#tfhtTb").attr("onlyView","true"); 
			}else if(resultobj.STATUS=="32"){
				$("#zt1tfht").attr('checked','checked');
				$("#tfhtmsg").html("(审核未通过)");
				$("#tfhtForm").setFormValues(resultobj);
				$("#tfhtBut").hide();
				$("#tfhtTb").attr("onlyView","true"); 
				$("#tfhtForm").find("input,textarea,radio").each(function(i,item){
					   $(item).attr('disabled','disabled');
					   if($(item).attr('id')=="DATE"){
						   $(item).attr('id','');
					   }
					});
			}
			return true;
			}else{//没提交 
				$("#tfhtForm").find("input,textarea,radio").each(function(i,item){
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


function selectTfhtFile(obj){
	$("#tfhtywid").val($("#ywid").val());
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
	setFileData(ywid,"","","460010","0",'<%=tfhtfjlb %>');
	var inputArr = $(".myKeyValueDiv input");
	for(var xx=0;xx<inputArr.length;xx++){
		if(inputArr[xx].getAttribute("cond")=="true"){
			if(inputArr[xx].getAttribute("condName")=="fjlb"){
				inputArr[xx].value=$(obj).attr("fjlb");
			}
		}
	}
	if(isIE()){
		initdataswf(ywid,"","","460010","0",'<%=tfhtfjlb %>','<%=tfhtfjlb %>');
		//initdataswf(ywid,"","","470010","0",result.CLK_UID,"1609");
		document.getElementById("plfileuploadswf").click();
	}else{
		document.getElementById("fileupload_btn").click();
	}	
}
		</script>