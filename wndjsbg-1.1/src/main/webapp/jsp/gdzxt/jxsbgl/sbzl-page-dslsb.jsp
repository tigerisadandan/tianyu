<!DOCTYPE html>
<%@page
	import="com.ccthanking.framework.common.rest.handle.servlet.RestContext"%>
<%@page import="com.ccthanking.business.bzwj.GongCheng"%>
<%@page import="com.ccthanking.framework.common.Role"%>
<%@page import="com.ccthanking.framework.handle.ActionContext"%>
<%@page import="com.ccthanking.framework.common.User"%>
<%@page import="com.ccthanking.framework.Constants"%>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<%
    String gcid=request.getParameter("gcid");
	User user = RestContext.getCurrentUser();
	Role[] roles = user.getRoles();
	String xzjs = "";
	String jbjs = "";
	String sljs = "";
	for (int i = 0; i < roles.length; i++) {
		if ("内网小组审核人".equals(roles[i].getName())) {
			xzjs = "内网小组审核人";
		}
		if ("内网经办审核人".equals(roles[i].getName())) {
			jbjs = "内网经办审核人";
		}
		if ("内网受理人".equals(roles[i].getName())) {
			sljs = "内网受理人";
		}
	}
%>
<app:base />
<title>待受理设备</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/bzwj/jxsbAzgzController";

//页面初始化
$(function() {
	init();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {
		var status=$("input[name='status']:checked").val();
		if(status=="2"){
			$("#shth").attr('hidden','hidden');
			$("#doSelect").attr('hidden','hidden');
			$("#btnStop").hide();
			$("#btnTg").hide();
		}
		if(status=="1"){
			$("#shth").attr('hidden','hidden');
			$("#doSelect").attr('hidden','hidden');
			$("#btnStop").hide();
			$("#btnTg").hide();
		}
		if(status=="0"){
			$("#shth").removeAttr('hidden');
			$("#doSelect").removeAttr('hidden');
			$("#btnStop").show();
			$("#btnTg").show();
		}
			var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
			//调用ajax插入
			defaultJson.doQueryJsonList(controllername+"?queryDslSb&type="+status,data,DT1);
		//生成json串
		
	});
	//按钮绑定事件(新增)
	$("#btnInsert").click(function() {
		$(window).manhuaDialog({"title":"微型工程类型维护>新增","type":"text","content":"${pageContext.request.contextPath }/jsp/business/wxgc/wxgclx-add.jsp?type=insert","modal":"1"});
	});
	
	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#queryForm").clearFormResult();
        $("#btnQuery").click();
    });
	
    $("#btnStop").click(function() {
    	doSelectPerson();
    	 //window.open("${pageContext.request.contextPath }/jlperson","人员添加");
    	//delSelectID();
    })
    
    $("#btnTg").click(function() {
    	doSelectPerson2();
    	 //window.open("${pageContext.request.contextPath }/jlperson","人员添加");
    	//delSelectID();
    })
	
});

function checkStatus(obj){
	$("#btnQuery").click();
}

var dqzt="";
//页面默认参数
function init(){
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?queryDslSb",data,DT1);
}

function doeditor(){
	return "<a href='javascript:void(0)' onclick='editorU(this)'  title='修改'><i class='icon-file showXmxxkInfo'></i></a>";
}

function editorU(obj){
	while(obj.tagName.toLowerCase() != "tr"){
		obj = obj.parentNode;
		if(obj.tagName.toLowerCase() == "table")return null;
	}
	obj.click();
	if($("#DT1").getSelectedRowIndex()==-1){
		requireSelectedOneRow();
	    return
	 }
	$("#resultXML").val($("#DT1").getSelectedRow());
	$(window).manhuaDialog({"title":"微型工程类型>修改","type":"text","content":"${pageContext.request.contextPath }/jsp/business/wxgc/wxgclx-add.jsp?type=update","modal":"1"});

}
function shenhe(obj){
	var html ="";
	if(obj.SHENHE_STATUS=="50"){
		html="已通过";
	}else if(obj.SHENHE_STATUS=="20"||obj.SHENHE_STATUS=="30"||obj.SHENHE_STATUS=="40"||obj.SHENHE_STATUS=="41"){
		html="审核中";
	} 
	if(obj.STATUS=="-1"){
		html="未通过";
	}
	return html;
}
function caozuoFun(obj){
	var status=obj.SHENHE_STATUS;
	  if(status=='30'&&'<%=xzjs%>'!=""){
		    dqzt="30";
    }else if(status=='40'&&'<%=jbjs%>'!=""){
		    dqzt="40";
    }else if(status=='41'&&'<%=sljs%>'!=""){
		     dqzt="41";      
    }
	var showHtml="";
	if(obj.SHENHE_STATUS==dqzt&&obj.STATUS!="-1"){
		showHtml +="<span class='btn btn-link' id='addSpan' onclick=\"rowSh(this,'"+obj.JXSB_AZGZ_UID+"');\" title='审核' >";
		showHtml +="<i class='icon-file'></i> ";
		showHtml +="</span>";
		
		//showHtml += "<a href='javascript:rowSh("+obj+");' title='审核'><i class='icon-file'></i></a>";	
	}else{
		showHtml +="<span class='btn btn-link' id='addSpan' onclick=\"rowView(this,'"+obj.JXSB_AZGZ_UID+"');\" title='查看' >";
		showHtml +="<i class='icon-file'></i> ";
		showHtml +="</span>";
		//showHtml += "<a href='javascript:rowView("+obj+");' title='查看'><i class='icon-file'></i></a>";
	}
	return showHtml;
}

//详细信息
function rowView(obj,id){
	while(obj.tagName.toLowerCase() != "tr"){
	    obj = obj.parentNode;
	    if(obj.tagName.toLowerCase() == "table")return null;
	   }
	obj.click();
	var data = $("#DT1").getSelectedRow();

	$("#resultXML").val(data);
	$(window).manhuaDialog({"title":"安装企业>人员信息","type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/jxsbgl/azgz-page-sh.jsp?type=detail&id="+id,"modal":"2"});
}


function rowSh(obj,id){
	while(obj.tagName.toLowerCase() != "tr"){
	    obj = obj.parentNode;
	    if(obj.tagName.toLowerCase() == "table")return null;
	   }
	obj.click();
	var data = $("#DT1").getSelectedRow();

	$("#resultXML").val(data);
	$(window).manhuaDialog({"title":"安装企业>人员审核","type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/jxsbgl/azgz-page-sh.jsp?type=update&id="+id,"modal":"2"});
}

function shzt(obj){
if(obj.SHENHE_STATUS=="30"){
html="小组审核";
}else if(obj.SHENHE_STATUS=="41"){
html="受理人审核";
}
return html;
}

function doMoreChoose(obj){
	var id=obj.BZ_UID; 
	var type=obj.BZ_TYPE; 
	var status=obj.SHENHE_STATUS;
	var djcode=obj.CQ_BH+"-B7-"+obj.BZ_UID+"-"+obj.SY_COUNTS+"-"+obj.CQ_ATTRIBUTE;
	return "<input type='checkbox' id='doSelect' name='doSelect' value='"+type+"/"+id+"/"+status+"/"+djcode+"'/>";
}

//全选\反选
function doAllSelect(demo){
	var flag = $(demo).prop("checked");
	$("input:checkbox[name='doSelect']").each(function(i){
		if(i>=0){
			$(this).prop("checked",flag);
		}
	})
}

function delSelectID(type){
	$.each($("#DT1").find("input:checkbox[name='doSelect']"),function(i){
		if($("#DT1").find("input:checkbox[name='doSelect']")[i].checked){
			
			var id=$(this).val();
			$("#RENYUAN_ID").val($("#RENYUAN_ID").val()+id+",");
		}
	})
  $.ajax({
		    url : controllername+"?UpdateSH&ids="+$("#RENYUAN_ID").val()+"&type="+type,//$("#YIJIAN").val(),
			data : {},
			cache : false,
			async :	false,
			dataType : "json",  
			type : 'get',
			success : function(response) {
				init();
			}
	});
	//$("#btnQuery").click();
	  
	clickRadioShowDate();
	$("#RENYUAN_ID").val("");
}

function doSelectPerson(){
	//$.each($("#DT1").find("input:checkbox[name='doSelect']"),function(){
		
		if($("#DT1").find("input:checkbox[name='doSelect']:checked").size()>0){
			optype = 0;
			delSelectID("-1");
		}
		else{
			alert("请选择设备！");  
			}
}
	
function doSelectPerson2(){
	//$.each($("#DT1").find("input:checkbox[name='doSelect']"),function(){
		
		if($("#DT1").find("input:checkbox[name='doSelect']:checked").size()>0){
			optype = 0;
			delSelectID("1");
		}
		else{
			alert("请选择设备！");  
			}
}
	

</script>
</head>
<body>
	<app:dialogs />
	<div class="container-fluid">
		<p></p>
		<div class="row-fluid">
			<div class="B-small-from-table-autoConcise">

				<form method="post" id="queryForm">
					<table class="B-table" width="100%">
						<!--可以再此处加入hidden域作为过滤条件 -->
						<TR style="display: none;">
							<TD class="right-border bottom-border"></TD>
							<TD class="right-border bottom-border"><INPUT type="text"
								class="span12" kind="text" id="num" fieldname="rownum"
								value="1000" operation="<=" /> <INPUT type="text" class="span12"
								kind="text" id="SHENHE_STATUS" fieldname="SHENHE_STATUS"
								operation="=" /> <INPUT type="text" class="span12" kind="text"
								id="gcuid" fieldname="gl.GONGCHENG_UID" operation="=" value="<%=gcid %>" /></TD>
						</TR>
						<!--可以再此处加入hidden域作为过滤条件 -->
						<tr>
						<td class="right-border bottom-border" width="20%">&nbsp;
							 <input type=radio value="0" name="status" onclick="javascript:checkStatus(this)" checked="checked"  >受理中<h id="dsid"></h>&nbsp;&nbsp;
      						 <input type=radio value="1" name="status" onclick="javascript:checkStatus(this)">不受理<h id="ysid"></h>&nbsp;&nbsp;
      						 <input type=radio value="2" name="status" onclick="javascript:checkStatus(this)">待注销<h id="thid" ></h>
						</td>
							<th width="4%" class="right-border bottom-border text-right">设备名称</th>
							<td class="right-border bottom-border" width="12%"><select
								class="form-control" 
								id="JXSB_TYPE_UID" type="text" name="JXSB_TYPE_UID"
								fieldname="gl.SHEBEI_NAME" class="col-xs-10 col-sm-10"
								operation="=">
									<option value="">--全部设备--</option>
									<option value="塔式起重机">塔式起重机</option>
									<option value="物料提升机">物料提升机</option>
									<option value="施工升降机">施工升降机</option>
									<option value="吊篮">吊篮</option>
									<option value="爬升式脚手架">爬升式脚手架</option>
							</select>
							</td>
							<th width="4%" class="right-border bottom-border text-right">产权编号</th>
							<td class="right-border bottom-border" width="15%">
							 <input class="span12" type="text" id="CQ_BH" name="gl.CQ_BH" fieldname="gl.CQ_BH" operation="like" >
							</td>
							<td class="text-left bottom-border text-right">
								<button id="btnQuery" class="btn btn-link" type="button"
									style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">
									<i class="icon-search"></i>查询
								</button>
								<button id="btnClear" class="btn btn-link" type="button"
									style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">
									<i class="icon-trash"></i>清空
								</button>
								 <button id="btnStop" class="btn btn-link"  type="button">批量退回</button>
								 <button id="btnTg" class="btn btn-link"  type="button">批量通过</button>
							</td>


						</tr>
					</table>
				</form>
				<form method="post" id="queryForm2"  >
      			<table class="B-table" width="100%">
      			<!--可以再此处加入hidden域作为过滤条件 -->
      				<TR  style="display:none;">
	        			<TD class="right-border bottom-border">
	        			<INPUT type="text" class="span12" kind="text" id="YIJIAN" name="YIJIAN"  fieldname="YIJIAN" value="" />
	        			<INPUT type="text" class="span12" kind="text" id="RENYUAN_ID" name="RENYUAN_ID"  fieldname="RENYUAN_ID" value="" />
	       				 </TD>
						<TD class="right-border bottom-border">
						</TD>
        			</TR>
      			</table>
     	 	</form>
				<div style="height:5px;"></div>
				<div class="overFlowX">
					<table width="100%" class="table-hover table-activeTd B-table"
						id="DT1" type="single" pageNum="18">
						<thead>
							<tr>
								<th name="XH" id="_XH" style="width:10px" colindex=1
									tdalign="center">&nbsp;#&nbsp;</th>
								<!-- <th CustomFunction="caozuoFun" fieldname="JXSB_AZGC_UID"
									colindex=0 tdalign="center">&nbsp;&nbsp;</th> -->
								<th fieldname="SHENHE_STATUS" id="doSelect" name="doSelect" tdalign="center" colindex=2 CustomFunction="doMoreChoose" >
		                		      <label class="checkbox inline" >
		                                <input type="checkbox" id="allSelect" onclick="doAllSelect(this)" name="allSelect"/>
		                              </label>
	                            </th>
	                            <th fieldname=BZ_TYPE colindex=3 tdalign="center" hidden="" >&nbsp;&nbsp;</th>
	                            <th fieldname=BZ_UID colindex=3 tdalign="center" hidden="" >&nbsp;&nbsp;</th>
								<th fieldname=SHENHE_STATUS colindex=3 tdalign="center"  CustomFunction="shzt" id="shth"
									maxlength="30">&nbsp;审核状态&nbsp;</th>
								<th fieldname="SHEBEI_NAME" colindex=20 tdalign="center">&nbsp;设备名称&nbsp;</th>
								<th fieldname="SYDJ_BH" colindex=20 tdalign="center">&nbsp;使用登记编号&nbsp;</th>
								<th fieldname="CQ_BH" colindex=20 tdalign="center">&nbsp;产权编号&nbsp;</th>
								<th fieldname="GGXH" colindex=20 tdalign="center">&nbsp;规格型号&nbsp;</th>
								<th fieldname="CQ_BH" colindex=20 tdalign="center" hidden="">&nbsp;审核日期&nbsp;</th>
								<th fieldname="STEP" colindex=20 tdalign="center">&nbsp;阶段&nbsp;</th>
								<th fieldname="BEGIN_DATE" colindex=20 tdalign="center">&nbsp;完成日期&nbsp;</th>
								<th fieldname="CQ_ATTRIBUTE" colindex=20 tdalign="center" hidden="">&nbsp;&nbsp;</th>
								<th fieldname="SY_COUNTS" colindex=20 tdalign="center" hidden="">&nbsp;&nbsp;</th>
								
							</tr>
						</thead>
						<tbody></tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		<!--系统保留定义区域-->
		<input type="hidden" name="queryXML" id="queryXML"> <input
			type="hidden" name="txtXML" id="txtXML"> <input type="hidden"
			name="resultXML" id="resultXML">

		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData">
	</FORM>
</body>
</html>