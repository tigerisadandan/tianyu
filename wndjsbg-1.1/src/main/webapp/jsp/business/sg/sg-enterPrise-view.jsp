<%@page import="com.ccthanking.framework.common.User"%>
<%@page import="com.ccthanking.framework.Globals"%>
<%@page import="com.ccthanking.framework.Constants"%>
<%@page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext"%><!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<title>施工单位详细信息</title>
<%
	String type=request.getParameter("type");
	User user = RestContext.getCurrentUser();
	String id= (String)request.getAttribute("id");
%>
<app:base/>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/sgenter/sgEnterPriseLibraryController/";
var controllernameZizhi= "${pageContext.request.contextPath }/sgenter/sgZizhiController/";
var controllernameZizhiDengji= "${pageContext.request.contextPath }/sgenter/sgZizhiDengjiController/";
var controllernameZenZizhi= "${pageContext.request.contextPath }/sgenter/sgEnterpriseZenZizhiController/";
var controllernameJiangx= "${pageContext.request.contextPath }/sgenter/sgECreditCommendRewardController/";
var controllernameXiangm= "${pageContext.request.contextPath }/sgenter/sgECreditProjectsController/";

var type ="<%=type%>";
var id ="<%=id%>";
//页面初始化
$(function() {
	init();
	
	//置所有input 为disabled
	$(":input").each(function(i){
	   $(this).attr("readonly", "true");
	});
	$("#btnUpdate").click(function(){
		copyCompanyInfo();
	});

	$("#btnHistory").click(function(){
		window.open("${pageContext.request.contextPath }/sgenterapp");
	})

	$("#btnShowSp").click(function(){
		window.open("${pageContext.request.contextPath }/sgbbonsp");
	})
});
function copyCompanyInfo(){
	$.ajax({
		url : controllername+"updateCopySGXX",
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			window.open("${pageContext.request.contextPath }/sgentedit.do","企业信息-修改");
		}
	})
}
//页面默认参数
function init(){
	$("#optype").val(type);
	$("#QID").val(id);
	setFormValues();
	builderZizhiList();
	builderJiangxList();
	builderXiangmList();
	queryFileData2('ID','parent','SG_ENTERPRISE_LIBRARY');
	$("span[id='addFileSpan']").each(function(){
		$(this).hide();
	})
}
function setFormValues(){
	var data = combineQuery.getQueryCombineData(queryForm,frmPost);
	var data1 = {
		msg : data
	};
	$.ajax({
		url : controllername+"/query",
		data : data1,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			$("#resultXML").val(response.msg);
			var resultobj = defaultJson.dealResultJson(response.msg);
			$("#sgEnterPriseLibraryForm").setFormValues(resultobj);
			$("#WAIDI_SP").text(resultobj.WAIDI_Y_SV);
			//隐藏密码修改
			$("#PWD").val(resultobj.MIMA);
			$("#PWD1").val(resultobj.MIMA);
			$("#pwdtr").hide();
			$("#pwdtr_re").hide();
<%--			loadZZDJ($("#ZHENGSHU"),'zhuxiang',resultobj.ZHU_DENGJI);--%>
		<%--	$("#ZHU_DENGJI").val(resultobj.ZHU_DENGJI);--%>
			//组织机构代码
			var code = resultobj.COMPANY_CODE;
			$("#DAIMA_Z").val(code.split("-")[0]);
			$("#DAIMA_F").val(code.split("-")[1]);
			$("#QID").val(resultobj.SG_ENTERPRISE_LIBRARY_UID);
			$("#QID2").val(resultobj.SG_ENTERPRISE_LIBRARY_UID);
			if(resultobj.STATUS!='1'&&resultobj.STATUS!='40'){
				$("#btnUpdate").hide();
			}
			$("#SHENHE_JIEGUO").val(resultobj.SHENHE_JIEGUO_SV);
		}
	});
}
function builderForm(response){

	var obj=$("#resultXML").val();
	var resultobj=defaultJson.dealResultJson(obj);
	$("#sgEnterPriseLibraryForm").setFormValues(resultobj);
	
	//隐藏密码修改
	$("#PWD").val(resultobj.MIMA);
	$("#PWD1").val(resultobj.MIMA);
	$("#pwdtr").hide();
	$("#pwdtr_re").hide();
<%--	loadZZDJ($("#ZHENGSHU"),'zhuxiang',resultobj.ZHU_DENGJI);--%>
<%--	$("#ZHU_DENGJI").val(resultobj.ZHU_DENGJI);--%>
	//组织机构代码
	var code = resultobj.COMPANY_CODE;
	alert(code.split("-")[0]);
	$("#DAIMA_Z").val(code.split("-")[0]);
	$("#DAIMA_F").val(code.split("-")[1]);
	
}
function builderZizhiList(){
	var data = combineQuery.getQueryCombineData(queryForm2,frmPost,DT1);
	var flagrs =  defaultJson.doQueryJsonList(controllernameZenZizhi+"query",data,DT1,null, true);
	var rows = $("tbody tr" ,$("#DT1"));
	if(rows.size()==0){
		$("tbody" ,$("#DT1")).append("<tr><td colspan=\"10\" style=\"height: 1px;\">&nbsp;</td></tr>");
	}
}
function builderJiangxList(){
	var data = combineQuery.getQueryCombineData(queryForm2,frmPost,DT2);
	var flagrs =  defaultJson.doQueryJsonList(controllernameJiangx+"/query",data,DT2,null, true);
	var rows = $("tbody tr" ,$("#DT2"));
	if(rows.size()==0){
		$("tbody" ,$("#DT2")).append("<tr><td colspan=\"10\" style=\"height: 1px;\">&nbsp;</td></tr>");
	}
}
function builderXiangmList(){
	var data = combineQuery.getQueryCombineData(queryForm2,frmPost,DT3);
	var flagrs =  defaultJson.doQueryJsonList(controllernameXiangm+"/query",data,DT3,null, true);
	var rows = $("tbody tr" ,$("#DT3"));
	if(rows.size()==0){
		$("tbody" ,$("#DT3")).append("<tr><td colspan=\"10\" style=\"height: 1px;\">&nbsp;</td></tr>");
	}
}
var chushiDengji = "";
//加载资质列表
function loadZXZZ(){
	$.ajax({
		url : controllernameZizhi+"/loadAllZizhi",
		data : {},
		cache : false,
		async :	false,
		dataType : "text",  
		type : 'post',
		success : function(response) {
			var obj = eval('(' + response + ')')
			var obj1 = eval('(' + obj.msg + ')')
			var linkStr = "";
			$(obj1).each(function(){
				linkStr += "<option value='"+this.SG_ZIZHI_UID+"'>"+this.ZIZHI_NAME+"</option>";
			});
			//加载主项资质列表
			$("#ZHENGSHU").append(linkStr);

			//加载主项资质等级(初始化页面默认选中第一个资质)
			//loadZZDJ($("#ZHENGSHU"),'zhuxiang');
			//加载增项资质列表
			$("#zizhiList tr").each(function(){
				$(this).find("#SG_ZIZHI_UID").append(linkStr);
				//$(this).find("#ZIZHI_DENGJI").append(chushiDengji);
			})
		}
	});
	
}
//加载资质等级
function loadZZDJ(demo,type,value){
	var dengjiDom = null;
	if(type=='zhuxiang'){
		dengjiDom = $(demo).parent().find("#ZHU_DENGJI")
	}else if(type=='zengxiang'){
		dengjiDom = $(demo).parent().parent().find("#ZIZHI_DENGJI");
	}
	if($(demo).val()==''||$(demo).val()=='请选择'){
		$(dengjiDom).html("<option>等级</option>");
		return;
	}
	$.ajax({
		url : controllernameZizhiDengji+"/queryZizhiDengji&zizhi="+$(demo).val(),
		data : {},
		cache : false,
		async :	false,
		dataType : "text",  
		type : 'post',
		success : function(response) {
			
			var obj = eval('(' + response + ')');
			var obj1 = eval('(' + obj.msg + ')');
			chushiDengji = "";
			$(obj1).each(function(){
				chushiDengji += "<option value='"+this.SG_ZIZHI_DENGJI_UID+"'"
				if(value!=null&&this.SG_ZIZHI_DENGJI_UID==value){
					chushiDengji += " selected='selected' ";
				}
				chushiDengji += ">"+this.DENGJI_NAME+"</option>";
			});
			$(dengjiDom).empty();
			$(dengjiDom).append(chushiDengji);
		}
	});
}
//增加增项目资质
function addZengxiang(demo){
	var cloneNew = $("#cloneTR").clone(true);
	//alert(typeof(cloneNew));
	$(cloneNew).removeAttr("style")
	$("#zizhiList").append(cloneNew);
	$(demo).hide();
}

function removeZengxiang(demo){
	var tr_index = $("#zizhiList tr").index($(demo).closest("tr"));
	if(tr_index==$("#zizhiList tr").size()-1&&tr_index>2){
		$("#zizhiList tr").eq($("#zizhiList tr").size()-2).find("td").eq(3).append('&nbsp;<img onclick="addZengxiang(this)" style="cursor: pointer;" title="增加" src="/wndjssg/img/sg/icon-addZz.jpg">');
	}	
	$(demo).closest("tr").remove();
}
function checkCode(){
	var code = $("#DAIMA_Z").val()+$("#DAIMA_F").val();
	var id = $("#ID").val();
	$.ajax({
		url : controllername+"/queryCodeIsEmpty",
		data : {'code':'123','id':456},
		cache : false,
		async :	false,
		dataType : "text",  
		type : 'post',
		success : function(response) {
			var obj = eval('(' + response + ')')
			if(obj.msg=='empty'){
				$("#codeError").find("b").html("该组织机构代码已存在");
				$("#codeError").show();	
			}else{
				$("#codeError").hide();
			}
		}
	});
}
function doFjJx(obj){
	if(obj.FJS!='0'){
		return '<a href="javascript:void(0)" onclick="linkFj('+obj.CREDIT_COMMEND_REWARD_UID+',\'SG_E_CREDIT_COMMEND_REWARD\')"><img src="${pageContext.request.contextPath }/images/icon-annex.png"/></a>';
	}else{
		return '<div style="text-align:center">—</div>';
	}
}
function doFjXm(obj){
	if(obj.FJS!='0'){
		return '<a href="javascript:void(0)" onclick="linkFj('+obj.CREDIT_PROJECTS_UID+',\'SG_E_CREDIT_PROJECTS\')"><img src="${pageContext.request.contextPath }/images/icon-annex.png"/></a>';
	}else{
		return '<div style="text-align:center">—</div>';
	}
}
function linkFj(targetId,business_type){
	window.open("${pageContext.request.contextPath }/fujianShow/"+targetId+"/"+business_type+".do","查看附件","height=400, width=800, top=200, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no");
}
</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">
    <div class="B-small-from-table-autoConcise">
     <form method="post" id="queryForm"  >
      <table class="B-table" width="100%">
      <!--可以再此处加入hidden域作为过滤条件 -->
      	<TR  style="display : none;">
	        <TD class="right-border bottom-border">
	        	<INPUT type="text" class="span12" kind="text" id="QID" name="ID"  fieldname="n.SG_ENTERPRISE_LIBRARY_UID" value="" operation="="/>
	        	<INPUT type="text" class="span12" kind="text" id="QSTATUS" name="STATUS"  fieldname="n.STATUS" value="" operation="="/>
	        </TD>
			<TD class="right-border bottom-border">
			</TD>
        </TR>
      </table>
      </form>
      <form method="post" id="queryForm2"  >
      <table class="B-table" width="100%">
      <!--可以再此处加入hidden域作为过滤条件 -->
      	<TR  style="display : none;">
	        <TD class="right-border bottom-border">
	        	<INPUT type="text" class="span12" kind="text" id="QID2" name="ID"  fieldname="a.SG_ENTERPRISE_LIBRARY_UID" value="" operation="="/>
	        	
	        </TD>
			<TD class="right-border bottom-border">
			</TD>
        </TR>
      </table>
      </form>
    <div style="height:5px;"></div>
	</div>
	<div style="height:5px;"></div>
	<div class="B-small-from-table-autoConcise">
	<form method="post" id="sgEnterPriseLibraryForm">
		<div class="container-fluid">
			<p class="text-right tabsRightButtonP">
				<span class="pull-right">
					<button id="btnClose" class="btn" type="button" onclick="window.close();" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
				</span>
			</p>
			<ul class="nav nav-tabs"> 
				<li class="active"><a href="#xmsc1" data-toggle="tab" id="tabPage0">施工单位信息</a></li> 
				<li class=""><a href="#xmsc2" data-toggle="tab" id="tabPage1">相关荣誉</a></li>
			</ul>
			<div class="tab-content"> 
				<!-- 静态信息tab页 -->
				<div class="tab-pane active" id="xmsc1" style="height:100%"> 
				<table class="B-table" width="100%" >
			      <input type="hidden" id="ID" fieldname="SG_ENTERPRISE_LIBRARY_UID" name = "ID"/>
				  <input type="hidden" id="STATUS" fieldname="STATUS" name = "STATUS"/>
				  	<input type="hidden" id="optype" fieldname="optype" name = "optype"/>
				  	 <input type="hidden" id="SG_COMPANY_UID" fieldname="SG_COMPANY_UID" name = "SG_COMPANY_UID"/>
			        <tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">组织机构代码</th>
			       	 	<td class="bottom-border right-border">
			         		<input class="span12" style="width:35%" id="DAIMA_Z" maxlength="8" type="text"  check-type="required" fieldname="DAIMA_Z" name = "DAIMA_Z"/>
			         		-
			         		<input class="span12" style="width:5%" id="DAIMA_F" maxlength="1" type="text"  check-type="required" fieldname="DAIMA_F" name = "DAIMA_F"/>
			         		&nbsp;&nbsp;&nbsp;<span id="codeError" style="display: none;color: red;"><b>前半部分必须为8位</b></span>
			       	 	</td>
			        </tr>
			        <tr>
			        	<th width="15%" class="right-border bottom-border text-right" colspan="2">组织代码证扫描件</th>
			        	<td class="bottom-border right-border">
							<div>
								<span class="btn btn-fileUpload" id="addFileSpan" onclick="doSelectFile(this,'parent');"  ywid="ID" target_type="1" file_type="7" business_type="SG_ENTERPRISE_LIBRARY">
									<i class="icon-plus"></i>
									<span>添加文件...</span>
								</span>
								<table role="presentation" class="table table-striped">
									<tbody  onlyView="true" fjlb="0701" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>
								</table>
							</div>
						</td>
			        </tr>
			        <tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">施工企业全称</th>
						<td  class="right-border bottom-border">
							<input class="span12" style="width:85%" id="COMPANY_NAME" type="text"  check-type="required" fieldname="COMPANY_NAME" name = "COMPANY_NAME"  />
			<%--				<input id="ZFJE" class="span12" keep="true"  check-type="required" check-type="maxlength" maxlength="17" value=0 style="width:70%;text-align:right;" name="ZFJE" fieldname="ZFJE" type="number" />&nbsp;<b>(元)</b>--%>
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">企业类型</th>
						<td  class="right-border bottom-border">
							</select>
							<input class="span12" style="width:35%" id="COMPANY_TYPE_SV" type="text"  check-type="required" fieldname="COMPANY_TYPE_SV" name = "COMPANY_TYPE_SV"  />
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">外地企业</th>
						<td  class="right-border bottom-border">
							<span id="WAIDI_SP"></span>
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">营业执照</th>
						<td  class="right-border bottom-border" style="padding:0px;">
							<table width="100%">
								<tr>
									<td width="10%" class="text-right">注册号</th>
									<td style="border-right: 0px;">
										<input id="ZHIZHAO" class="span12" style="width:35%"  name="ZHIZHAO" fieldname="ZHIZHAO" type="text" />
										
									</td>
								</tr>
								<tr>
									<td width="10%" class="text-right">注册资金</th>
									<td style="border-right: 0px;">
										<input id="ZHUCE_ZIJIN" value=0 class="span12"  onkeydown="onlyNum()" onkeyup="isMoney(this)"  name="ZHUCE_ZIJIN" fieldname="ZHUCE_ZIJIN" type="text"  min="0" alt="请输入金额，不能输入中文" style="width:35%;text-align:right;"/><b>(万元)</b>
										
									</td>
								</tr>
								<tr>
									<td width="10%" class="text-right">有效期</th>
									<td style="border-right: 0px;">
										<input id="ZHIZHAO_VALID"   style="width:33.5%;" fieldtype="date" fieldformat="YYYY-MM-DD" name="ZHIZHAO_VALID" fieldname="ZHIZHAO_VALID" class="span12" type="text"/>
										
									</td>
								</tr>
								<tr>
									<td width="10%" class="text-right" style="border-bottom: 0px;">扫描件</th>
									<td style="border-bottom: 0px;border-right: 0px;">
										<div>
											<span class="btn btn-fileUpload" id="addFileSpan" onclick="doSelectFile(this,'parent');"  ywid="ID" target_type="1" file_type="1" business_type="SG_ENTERPRISE_LIBRARY">
												<i class="icon-plus"></i>
												<span>添加文件...</span>
											</span>
											<table role="presentation" class="table table-striped">
												<tbody onlyView="true" ywid="ID" target_type="1" file_type="2" business_type="SG_ENTERPRISE_LIBRARY" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>
											</table>
										</div>
									</td>
								</tr>
							</table>
						</td>	
					</tr>
					<tr>
						<th class="right-border bottom-border text-right" style="line-height:18px;" colspan="2">税务登记证</th>
						<td  class="right-border bottom-border" style="padding:0px;">
							<table width="100%">
								<tr>
									<td width="10%" class="right-border bottom-border text-right">税务登记号</td>
									<td style="border-right: 0px;">
										<input id="SHUIWU" class="span12" style="width:35%"  name="SHUIWU" fieldname="SHUIWU" type="text" />
										
									</td>
								</tr>
								<tr>
									<td width="10%" class="right-border bottom-border text-right" style="border-bottom: 0px;">扫描件</td>
						        	<td  class="bottom-border right-border"  style="border-bottom: 0px;border-right: 0px;">
										<div>
											<span class="btn btn-fileUpload" id="addFileSpan" onclick="doSelectFile(this,'parent');"  ywid="ID" target_type="1" file_type="2" business_type="SG_ENTERPRISE_LIBRARY">
												<i class="icon-plus"></i>
												<span>添加文件...</span>
											</span>
											<table role="presentation" class="table table-striped">
												<tbody onlyView="true" fjlb="0701" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>
											</table>
										</div>
									</td>
								</tr>
							</table>
						</td>	
					</tr>
					<tr>
						<th class="right-border bottom-border text-right" style="line-height:18px;" colspan="2">开户银行</th>
						<td  class="right-border bottom-border" style="padding:0px;">
							<table width="100%">
								<tr>
									<td width="10%" class="right-border bottom-border text-right">开户银行</td>
									<td  class="right-border bottom-border" style="border-right: 0px;">
										<input id="BANK" class="span12" style="width:35%"  name="BANK" fieldname="BANK" type="text" />
										
									</td>
								</tr>
								<tr>
									<td width="10%" class="right-border bottom-border text-right">银行账号</td>
									<td  class="right-border bottom-border" style="border-right: 0px;">
										<input id="BANK_ACCOUNT" class="span12" style="width:35%"  name="BANK_ACCOUNT" fieldname="BANK_ACCOUNT" type="text" />
										
									</td>
								</tr>
								<tr>
									<td width="10%" class="right-border bottom-border text-right" style="border-bottom: 0px;">扫描件</td>
						        	<td  class="bottom-border right-border" style="border-right: 0px;border-bottom: 0px;">
										<div>
											<span class="btn btn-fileUpload" id="addFileSpan" onclick="doSelectFile(this,'parent');" ywid="ID" target_type="1" file_type="3" business_type="SG_ENTERPRISE_LIBRARY">
												<i class="icon-plus"></i>
												<span>添加文件...</span>
											</span>
											<table role="presentation" class="table table-striped">
												<tbody onlyView="true" fjlb="0701" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>
											</table>
										</div>
									</td>
								</tr>
							</table>
						</td>	
					</tr>
					<tr>
						<th class="right-border bottom-border text-right" style="line-height:18px;" colspan="2">安全生产许可证</th>
						<td  class="right-border bottom-border" style="padding:0px;">
							<table width="100%">
								<tr>
									<td width="10%" class="right-border bottom-border text-right">编号</td>
									<td class="right-border bottom-border" style="border-right: 0px;">
										<input id="ANQUAN_CODE" class="span12"  style="width:35%"  name="ANQUAN_CODE" fieldname="ANQUAN_CODE" type="text" />
										
									</td>
								</tr>
								<tr>
									<td width="10%" class="right-border bottom-border text-right">有效期</td>
									<td  class="right-border bottom-border" style="border-right: 0px;">
										<input id="ANQUAN_VALID" style="width:33.5%;" fieldtype="date" fieldformat="YYYY-MM-DD" name="ANQUAN_VALID" fieldname="ANQUAN_VALID" class="span12" type="text" />
										
									</td>
								</tr>
								<tr>
									<td width="10%" class="right-border bottom-border text-right" style="border-bottom: 0px;">扫描件</td>
						        	<td  class="bottom-border right-border" style="border-right: 0px;border-bottom: 0px;">
										<div>
											<span class="btn btn-fileUpload" id="addFileSpan" onclick="doSelectFile(this,'parent');"  ywid="ID" target_type="1" file_type="5" business_type="SG_ENTERPRISE_LIBRARY">
												<i class="icon-plus"></i>
												<span>添加文件...</span>
											</span>
											<table role="presentation" class="table table-striped">
												<tbody onlyView="true" fjlb="0701" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>
											</table>
										</div>
									</td>
								</tr>
							</table>
						</td>
						
					</tr>
					<tr>
						<th class="right-border bottom-border text-right" style="line-height:18px;" colspan="2">信用手册</th>
						<td  class="right-border bottom-border" style="padding:0px;">
							<table width="100%">
								<tr>
									<td width="10%" class="right-border bottom-border text-right">编号</td>
									<td class="right-border bottom-border" style="border-right: 0px;">
										<input id="XINYONG_CODE" style="width:35%" class="span12" name="XINYONG_CODE" fieldname="XINYONG_CODE" type="text" />
										<font style="color:red;font-size: 16px"><b>*</b></font>外地企业无须填写
									</td>
								</tr>
								<tr>
									<td width="10%" class="right-border bottom-border text-right">有效期</td>
									<td  class="right-border bottom-border" style="border-right: 0px;">
										<input id="XINYONG_VALID" style="width:33.5%;"  fieldtype="date" fieldformat="YYYY-MM-DD" name="XINYONG_VALID" fieldname="XINYONG_VALID" class="Wdate" type="text" onClick="WdatePicker()" />
									</td>
								</tr>
								<tr>
									<td width="10%" class="right-border bottom-border text-right" style="border-bottom: 0px;">扫描件</td>
						        	<td  class="bottom-border right-border" style="border-right: 0px;border-bottom: 0px;">
										<div>
											<span class="btn btn-fileUpload" id="addFileSpan" onclick="doSelectFile(this,'parent');"  ywid="ID" target_type="1" file_type="6" business_type="SG_ENTERPRISE_LIBRARY">
												<i class="icon-plus"></i>
												<span>添加文件...</span>
											</span>
											<table role="presentation" class="table table-striped">
												<tbody id="xysc" fjlb="0701" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>
											</table>
										</div>
									</td>
								</tr>
							</table>
						</td>
						
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right"  colspan="2">主项资质</th>
						<td  class="right-border bottom-border" >
							<input id="ZIZHI_NAME" style="width:35%" class="span12"  check-type="required" check-type="maxlength" maxlength="36" name="ZIZHI_NAME" fieldname="ZIZHI_NAME" type="text" />
							<input id="DENGJI_NAME" style="width:35%" class="span12"  check-type="required" check-type="maxlength" maxlength="36" name="DENGJI_NAME" fieldname="DENGJI_NAME" type="text" />
						
						
<%--							<select  id="ZHENGSHU"  class="span12" style="width:35%" name="SG_ZIZHI_UID" fieldname="SG_ZIZHI_UID" onchange="loadZZDJ(this,'zhuxiang',null)"><option value="">请选择</option></select>--%>
<%--							--%>
<%--							<select  id="ZHU_DENGJI"  class="span12" style="width:25%" name="ZHU_DENGJI" fieldname="ZHU_DENGJI" value=""><option>等级</option></select>--%>
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">资质证书编号</th>
						<td  class="right-border bottom-border">
							<input id="ZHENGSHU_CODE" class="span12" style="width:35%"  check-type="required" check-type="maxlength" maxlength="36" name="ZHENGSHU_CODE" fieldname="ZHENGSHU_CODE" type="text" />
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">获取资质日期</th>
						<td  class="right-border bottom-border">
							<input id="ZIZHI_DATE" class="span9"  style="width:35%;" fieldtype="date" fieldformat="YYYY-MM-DD" name="ZIZHI_DATE" fieldname="ZIZHI_DATE"  type="text" />
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">资质有效期</th>
						<td  class="right-border bottom-border">
							<input id="VALID_DATE" class="span9"  style="width:35%;" fieldtype="date" fieldformat="YYYY-MM-DD" name="VALID_DATE" fieldname="VALID_DATE" type="text"  />
						</td>
					</tr>
					<tr>
			        	<th width="15%" class="right-border bottom-border text-right" colspan="2">资质证书扫描件</th>
			        	<td  class="bottom-border right-border">
							<div>
								<span class="btn btn-fileUpload" id="addFileSpan" onclick="doSelectFile(this,'parent');"  ywid="ID" target_type="1" file_type="4" business_type="SG_ENTERPRISE_LIBRARY">
									<i class="icon-plus"></i>
									<span>添加文件...</span>
								</span>
								<table role="presentation" class="table table-striped">
									<tbody onlyView="true" onlyView="true" fjlb="0701" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>
								</table>
							</div>
						</td>
			        </tr>
			        
					<!-- 增项资质 -->
					<tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">增项资质</th>
						<td  class="right-border bottom-border">
							<div class="overFlowX">
					            <table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single" noPage="true" pageNum="1000" >
					                <thead>
					                	<tr>
					                		<th  name="XH" id="_XH" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
					                		<th  fieldname="ZIZHI_NAME" tdalign="center" colindex=2 >&nbsp;资质名称&nbsp;</th>
					                		<th  fieldname="DENGJI_NAME" colindex=3 tdalign="center" >&nbsp;资质等级&nbsp;</th>
											<th  fieldname="ZIZHI_CODE" colindex=4 tdalign="center" >&nbsp;资质证书编号&nbsp;</th>
											<th  fieldname="VALID_DATE" colindex=5 tdalign="center" >&nbsp;有效日期&nbsp;</th>
					                </thead>
					              	<tbody></tbody>
					           </table>
					       </div>							
						</td>
					</tr>
					<tr>
				        <th width="15%" class="right-border bottom-border text-right" colspan="2">公司地址</th>
				        <td  class="bottom-border right-border" >
				        	<textarea class="span12" rows="2" id="ADDRESS" check-type="maxlength" maxlength="4000" fieldname="ADDRESS" name="ADDRESS"></textarea>
				        </td>
			        </tr>
			        <tr>
				        <th width="15%" class="right-border bottom-border text-right" colspan="2">驻锡地址</th>
				        <td  class="bottom-border right-border" >
				        	<textarea class="span12" rows="2" id="ZHUXI_ADDRESS" check-type="maxlength" maxlength="4000" fieldname="ZHUXI_ADDRESS" name="ZHUXI_ADDRESS"></textarea>
				        </td>
			        </tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">邮政编码</th>
						<td  class="right-border bottom-border">
							<input id="POSTCODE" class="span12" style="width:35%" check-type="maxlength" maxlength="36" name="POSTCODE" fieldname="POSTCODE" type="text" />
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">公司电话</th>
						<td  class="right-border bottom-border">
							<input id="PHONE" class="span12" style="width:35%"  check-type="required" check-type="maxlength" maxlength="36" name="PHONE" fieldname="PHONE" type="text" />
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">公司传真</th>
						<td  class="right-border bottom-border">
							<input id="FAX" class="span12" style="width:35%" check-type="maxlength" maxlength="36" name="FAX" fieldname="FAX" type="text" />
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">公司主页</th>
						<td  class="right-border bottom-border">
							<input id="URL" class="span12" style="width:35%" name="URL" fieldname="URL" type="text" />
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">法人代表</th>
						<td  class="right-border bottom-border">
							<input id="FAREN" class="span12" style="width:35%"  check-type="required" check-type="maxlength" maxlength="36" name="FAREN" fieldname="FAREN" type="text" />
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">法人职称</th>
						<td  class="right-border bottom-border">
							<input id="FAREN_ZHICHENG" style="width:35%" class="span12" check-type="maxlength" maxlength="36" name="FAREN_ZHICHENG" fieldname="FAREN_ZHICHENG" type="text" />
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">法人代表移动电话</th>
						<td  class="right-border bottom-border">
							<input id="FAREN_MOBILE" style="width:35%" class="span12" check-type="maxlength" maxlength="36" name="FAREN_MOBILE" fieldname="FAREN_MOBILE" type="text" />
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">驻锡负责人</th>
						<td  class="right-border bottom-border">
							<input id="ZHUXI_FZR" style="width:35%" class="span12"  check-type="required" check-type="maxlength" maxlength="36" name="ZHUXI_FZR" fieldname="ZHUXI_FZR" type="text" />
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">驻锡负责人移动电话</th>
						<td  class="right-border bottom-border">
							<input id="ZHUXI_MOBILE" style="width:35%" class="span12" check-type="maxlength" maxlength="36" name="ZHUXI_MOBILE" fieldname="ZHUXI_MOBILE" type="text" />
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">联系人</th>
						<td  class="right-border bottom-border">
							<input id="LIANXIREN" style="width:35%" class="span12"  check-type="required" check-type="maxlength" maxlength="36" name="LIANXIREN" fieldname="LIANXIREN" type="text" />
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">联系人移动电话</th>
						<td  class="right-border bottom-border">
							<input id="LIANXIREN_MOBILE" style="width:35%" class="span12"  check-type="required" check-type="maxlength" maxlength="36" name="LIANXIREN_MOBILE" fieldname="LIANXIREN_MOBILE" type="text" />
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right" colspan="2">联系人Email</th>
						<td  class="right-border bottom-border">
							<input id="LIANXIREN_MAIL" style="width:35%" class="span12" check-type="maxlength" maxlength="36" name="LIANXIREN_MAIL" fieldname="LIANXIREN_MAIL" type="text" />
						</td>
					</tr>
					<tr>
				        <th width="15%" class="right-border bottom-border text-right" colspan="2">备注</th>
				        <td  class="bottom-border right-border" >
				        	<textarea class="span12" rows="3" id="DESCRIPTION" check-type="maxlength" maxlength="4000" fieldname="DESCRIPTION" name="DESCRIPTION"></textarea>
				        </td>
			        </tr>
			       
			      </table>	
			    </div>
			    <div class="tab-pane" id="xmsc2" style="height:100%">
			    	
			    	<h4 class="title" >获奖信息</h4>
					<table class="B-table" width="100%">
						<input type="hidden" id="JXID" fieldname="CREDIT_COMMEND_REWARD_UID" name = "ID"/>
						<tr>
					        <th width="15%" class="right-border bottom-border text-right">所获荣誉</th>
					        <td class="bottom-border right-border" >
					        	<div class="overFlowX">
						            <table width="100%" class="table-hover table-activeTd B-table" id="DT2" type="single" noPage="true" pageNum="1000" >
						                <thead>
						                	<tr>
						                		<th  name="XH" id="_XH" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
						                		<th  fieldname="CR_NAME" tdalign="center" colindex=2 >&nbsp;奖项名称&nbsp;</th>
						                		<th  fieldname="CR_LEVEL" colindex=3 tdalign="center" >&nbsp;奖项级别&nbsp;</th>
												<th  fieldname="BG_VALIDITY_DATE" colindex=4 tdalign="center" >&nbsp;获奖日期&nbsp;</th>
												<th  fieldname="FJS" colindex=5 tdalign="center" CustomFunction="doFjJx">&nbsp;附件&nbsp;</th>
											</tr>
						                </thead>
						              	<tbody></tbody>
						           </table>
						       </div>	
					        </td>
				        </tr>
					</table>
					
					<h4 class="title" >工程信息</h4>
					<table class="B-table" width="100%">
					<input type="hidden" id="XMID" fieldname="CREDIT_PROJECTS_UID" name = "XMID"/>
						<tr>
					        <th width="15%" class="right-border bottom-border text-right">参与工程</th>
					        <td class="bottom-border right-border" >
					        	<div class="overFlowX">
						            <table width="100%" class="table-hover table-activeTd B-table" id="DT3" type="single" noPage="true" pageNum="1000" >
						                <thead>
						                	<tr>
						                		<th  name="XH" id="_XH" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
						                		<th  fieldname="PROJECT_NAME" tdalign="center" colindex=2 >&nbsp; 项目名称 &nbsp;</th>
						                		<th  fieldname="YXBEGIN_DATE" colindex=3 tdalign="center" >&nbsp;中标通知日期&nbsp;</th>
												<th  fieldname="FJS" colindex=4 tdalign="center" CustomFunction="doFjXm">&nbsp;附件&nbsp;</th>
											</tr>
						                </thead>
						              	<tbody></tbody>
						           </table>
						       </div>	
					        </td>
				        </tr>
					</table>
			    </div>
			</div>
			<div style="height: 5px;"></div>
			<div>
				<div class="modal-header" style="background:#0866c6;">
				<h3 style="color:white;">施工企业资质审核
				</h3></div>
      			<table class="B-table" width="100%" >
			        <tr>
						<th width="15%" class="right-border bottom-border text-right">审核结果</th>
			       	 	<td class="bottom-border right-border">
			       	 		<input id="SHENHE_JIEGUO" style="width:50%" class="span12"  name="SHENHE_JIEGUO" fieldname=SHENHE_JIEGUO type="text" />
			       	 	</td>
			        </tr>
			        <tr>
						<th width="15%" class="right-border bottom-border text-right">审核人</th>
			       	 	<td class="bottom-border right-border">
			       	 		<input id="SHENHE_REN" style="width:50%" class="span12"  name="SHENHE_REN" fieldname=USER_NAME type="text" />
			       	 	</td>
			       	 	
			        </tr>
			        <tr>
			        	<th width="15%" class="right-border bottom-border text-right">审核时间</th>
			       	 	<td class="bottom-border right-border" >
			       	 		<input id="SHENHE_DATE" style="width:50%" class="span12"  name="SHENHE_DATE" fieldname=SHENHE_DATE type="text" />
			       	 	</td>
			        </tr>
			       	<tr>
						<th width="15%" class="right-border bottom-border text-right">审核意见</th>
			       	 	<td class="bottom-border right-border">
			       	 		<textarea class="span12" rows="3" id="SHENHE_YIJIAN" check-type="maxlength" maxlength="4000" fieldname="SHENHE_YIJIAN" name="SHENHE_YIJIAN" readonly="readonly"></textarea>
			       	 	</td>
			        </tr>
				</table>
			</div>
      </form>
    </div>
   </div>
  </div>
  <jsp:include page="/jsp/file_upload/fileupload_config.jsp" flush="true"/>
  </div>
  <div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id = "queryXML">
         <input type="hidden" name="txtXML" id = "txtXML">
         <input type="hidden" name="txtFilter"  order="desc" fieldname = "SG_ENTERPRISE_LIBRARY_UID" id = "txtFilter">
         <input type="hidden" name="resultXML" id = "resultXML">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
 	</FORM>
 </div>
</body>
<script>
</script>
</html>