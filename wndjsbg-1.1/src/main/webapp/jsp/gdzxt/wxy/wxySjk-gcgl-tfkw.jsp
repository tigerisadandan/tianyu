 <%@page import="com.ccthanking.framework.Constants"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<% 
String tfkwfjlb=Constants.FS_FILEUPLOAD_FJLB_WXY_SJK_TFKW; %>
<form class="form-horizontal" id="tfkwForm">
<input type="hidden" id="tfkwywid" fieldname="ywid" name="ywid" />
<input id="TFKW_WXY_SJK_GC_UID" type="hidden"  fieldname="WXY_SJK_GC_UID" name="WXY_SJK_GC_UID">
<input id="TFKW_WXY_SJK_UID" type="hidden"  fieldname="WXY_SJK_UID" name="WXY_SJK_UID">
<input id="TFKW_GONGCHENG_UID" type="hidden"  fieldname="GONGCHENG_UID" name="GONGCHENG_UID">
<input id="TFKW_WXY_TYPE" type="hidden"  fieldname="WXY_TYPE" name="WXY_TYPE" value="1">
<input id="TFKW_STATUS" type="hidden"  fieldname="STATUS" name="STATUS">
<table border="1" width="100%" cellpadding="0" cellspacing="0" class="content">
			<tr>
				<td class="yw-title">
					<h4 ><span id="spyjtxhid">土方开挖<span id="tfkwmsg" style="color: red;">(未填报)</span></span>
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
				<th width="15%" class="right-border bottom-border text-right">开挖开始时间</th>
				<td width="20%" class="right-border bottom-border">
					<input class="span12" style="width:94%" id="BEGIN_DATE" type="text" readonly="readonly" check-type="required" fieldname="BEGIN_DATE" name = "BEGIN_DATE" />			
				</td>
				
				<th width="15%" class="right-border bottom-border text-right">开挖结束时间</th>
				<td width="20%" class="right-border bottom-border">
					<input class="span12" style="width:94%" id="END_DATE" type="text" readonly="readonly" fieldname="END_DATE" name = "END_DATE" />			
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">运输车辆“三证”是否齐全</th>
				<td class="right-border bottom-border">
					<input class="span12" style="width:94%"  type="radio" readonly="readonly" fieldname="SANZHENG_Y" kind="dic" src="SF" />			
				</td>
				
				<th width="15%" class="right-border bottom-border text-right">放坡是否按方案执行</th>
				<td class="right-border bottom-border">
					<input class="span12" style="width:94%"  type="radio" readonly="readonly" fieldname="FANGAN_ZHIXING_Y"  kind="dic" src="SF" />			
					
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">开挖前安全交底情况</th>
				<td colspan="3" class="right-border bottom-border">
				    <textarea rows="" class="span12" style="width:98%" id="JIAODI_QINGKUANG" readonly="readonly" fieldname="JIAODI_QINGKUANG" name = "JIAODI_QINGKUANG"></textarea>
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">开挖过程车辆冲洗情况</th>
				<td colspan="3" class="right-border bottom-border">
				    <textarea rows="" class="span12" style="width:98%" id="CARWASH_QINGKUANG" readonly="readonly" fieldname="CARWASH_QINGKUANG" name = "CARWASH_QINGKUANG"></textarea>
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">排水措施实施情况</th>
				<td colspan="3" class="right-border bottom-border">
				    <textarea rows="" class="span12" style="width:98%" id="PAISHUI_QINGKUANG" readonly="readonly" fieldname="PAISHUI_QINGKUANG" name = "PAISHUI_QINGKUANG"></textarea>
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">基坑临边防护实施情况</th>
				<td colspan="3" class="right-border bottom-border">
				    <textarea rows="" class="span12" style="width:98%" id="JIKENG_FANGHU_QINGKUANG" readonly="readonly" fieldname="JIKENG_FANGHU_QINGKUANG" name = "JIKENG_FANGHU_QINGKUANG"></textarea>
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">上传文件</th>
	       	 	<td class="bottom-border right-border" style="width:32%" colspan="3">
	         		<table role="presentation" id="tfkwfj" class="table table-striped" style="margin-bottom:0px;width: 96.1%">
											<tbody fjlb="<%=tfkwfjlb%>" id="tfkwTb"
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
<form class="form-inline" role="form" id="tfkwQueryForm">
	<input type="hidden" id="QUERY_TFKW_GONGCHENG_UID" fieldname="GONGCHENG_UID"
		operation="=" logic="and" />
	<input type="hidden" fieldname="WXY_TYPE"
		 operation="=" logic="and" value="1"/>
</form>
<script type="text/javascript">
$(document).ready(function(){
	 //setStyle(tfkwForm);
		$("#tfkwForm").find("input,textarea,radio").each(function(i,item){
			 $(item).attr('disabled','disabled');
			});
	 $("#TFKW_GONGCHENG_UID").val(parent.document.getElementById("GDZXT_XM_ID").value);
	 $("#QUERY_TFKW_GONGCHENG_UID").val(parent.document.getElementById("GDZXT_XM_ID").value);

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
				
			}else if(resultobj.STATUS=="30"){
				$("#tfkwTb").attr("onlyView","true"); 
				$("#tfkwForm").setFormValues(resultobj);
				queryFileData($("#TFKW_WXY_SJK_GC_UID").val(), "", "", "460010");

				//默认
				$("#tfkwmsg").html("(待审核)");
			}else if(resultobj.STATUS=="33"){
				$("#tfkwmsg").html("(已审核)");
				
				$("#tfkwTb").attr("onlyView","true"); 
				$("#tfkwForm").setFormValues(resultobj);
				queryFileData($("#TFKW_WXY_SJK_GC_UID").val(), "", "", "460010");
			}else if(resultobj.STATUS=="32"){
				
				$("#tfkwmsg").html("(审核未通过)");
				$("#tfkwForm").setFormValues(resultobj);
				$("#tfkwTb").attr("onlyView","true"); 
				$("#zt1").attr('checked','checked');
				queryFileData($("#TFKW_WXY_SJK_GC_UID").val(), "", "", "460010");
			}
			
			return true;
			}
			}
	});		
}



function selectTfkwFile(obj){
	$("#tfkwywid").val($("#ywid").val());
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
	setFileData(ywid,"","","460010","0",'<%=tfkwfjlb %>');
	var inputArr = $(".myKeyValueDiv input");
	for(var xx=0;xx<inputArr.length;xx++){
		if(inputArr[xx].getAttribute("cond")=="true"){
			if(inputArr[xx].getAttribute("condName")=="fjlb"){
				inputArr[xx].value=$(obj).attr("fjlb");
			}
		}
	}
	if(isIE()){
		initdataswf(ywid,"","","460010","0",'<%=tfkwfjlb %>','<%=tfkwfjlb %>');
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