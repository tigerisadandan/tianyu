<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<%
	String pid = request.getParameter("pid");
%>
<title></title>

<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/ywlz/css/default.css"> </LINK>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/ztree_css/demo.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/base.css" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/ztree_css/zTreeStyle.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/ztree_css/zTreeStyle.css" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.min.js"></script>
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
  <div class="row-fluid" align="center">
  <div class="B-small-from-table-autoConcise">
  <form method="post" id="queryForm">
	<table class="B-table" width="100%">
		<!--可以再此处加入hidden域作为过滤条件 -->
		<TR style="display: none;">
			<TD class="right-border bottom-border"></TD>
			<TD class="right-border bottom-border">
			</TD>
		</TR>
		<!--可以再此处加入hidden域作为过滤条件 -->
	</table>
</form>
  	<div class="span7 text-left" style="height:522px;">
  		<div style="height: 10px;"></div>
 		<table border="1" width="100%" height="512px" cellpadding="0" cellspacing="0" class="content">
			<tr>
				<td class="yw-title">
					<h4>部门信息 
					<span class="pull-right">
						<button id="btnCancel" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
      					<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">保存</button>
      				</span>
					</h4>
					
				</td>
			</tr>
			<tr>
				<td>
					<ul id="ywcl_list">
					<div class="B-small-from-table-autoConcise">
					  <form method="post" id="deptForm">
						<table class="B-table" width="100%" id="DT1">
							<tr>
						        <th width="8%" class="right-border bottom-border text-right">部门名称</th>
						        <td colspan="2" class="bottom-border right-border" >
						        	<input type="hidden" name="ORG_TYPE" id="ORG_TYPE" maxlength="20" fieldname="ORG_TYPE" />
						        	<input type="hidden" name="P_ORGANIZE_UID" id="P_ORGANIZE_UID" maxlength="20" fieldname="P_ORGANIZE_UID" />
						        	<input type="text" name="ORG_NAME" id="ORG_NAME" maxlength="20" fieldname="ORG_NAME" check-type="required" />
						        </td>
						    </tr>
						    <tr>
						        <th width="8%" class="right-border bottom-border text-right">部门类型</th>
						        <td colspan="2" class="bottom-border right-border" >
						        	<select id="CODE" name="CODE" fieldname="CODE" operation="=">
						        		<option value="">一般部门</option>
						        		<option value="AJ">监督小组</option>
						        	</select>
						        </td>
						    </tr>
						    <tr>
						        <th width="8%" class="right-border bottom-border text-right">地址</th>
						        <td colspan="2" class="bottom-border right-border" >
						        	<input type="text" class="span6" name="ADDRESS" id="ADDRESS" maxlength="50" fieldname="ADDRESS" />
						        </td>
						    </tr>
						    <tr>
						        <th width="8%" class="right-border bottom-border text-right">电话号码</th>
						        <td colspan="2" class="bottom-border right-border" >
						        	<input type="text" name="PHONE" id="PHONE" maxlength="20" fieldname="PHONE" />
						        </td>
						    </tr>
						    <tr id="faxBox">
						        <th width="8%" class="right-border bottom-border text-right">传真</th>
						        <td colspan="2" class="bottom-border right-border" >
						        	<input type="text" name="FAX" id="FAX" maxlength="20" fieldname="FAX" />
						        </td>
						    </tr>
						    <tr id="postcodeBox">
						        <th width="8%" class="right-border bottom-border text-right">邮编</th>
						        <td colspan="2" class="bottom-border right-border" >
						        	<input type="text" name="POSTCODE" id="POSTCODE" maxlength="20" fieldname="POSTCODE" />
						        </td>
						    </tr>
						    <tr>
						        <th width="8%" class="right-border bottom-border text-right">备注</th>
						        <td colspan="2" class="bottom-border right-border" >
						        	<textarea class="span12" rows="6" maxlength="3500" id="DESCRIPTION"  fieldname="DESCRIPTION" name="DESCRIPTION"></textarea>
						        </td>
						    </tr>
							</table>
						</form>
					</div>
					</ul>
				</td>
			</tr>
		</table>
  	</div>
  </div>
  </div>
</div>
<div align="center">
	<FORM name="frmPost" method="post" style="display: none" target="_blank">
		<!--系统保留定义区域-->
		<input type="hidden" name="queryXML"/> 
		<input type="hidden" name="txtXML"/>
<%--		<input type="hidden" name="txtFilter" order="desc" fieldname="s.CREATED_DATE" id="txtFilter"/>--%>
<%--	<input type="hidden" name="txtFilter" order="desc" fieldname="ID" id="txtFilter"/>--%>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
<script type="text/javascript">
var controllername= "${pageContext.request.contextPath }/nbgl/organizeController";
var pid = <%=pid %>;
var iconType;//树节点前面显示图标类型
//点击保存按钮
$(function() {
	//给隐藏域赋值
	$("#P_ORGANIZE_UID").val(pid);
	
	//判断部门的类型
	if(pid != 1){
	//隐藏
		$("#faxBox").hide();
		$("#postcodeBox").hide();
	}else{
		//显示
		$("#faxBox").show();
		$("#postcodeBox").show();
	}
	
	//保存按钮
	$("#btnSave").click(function(){
		//判断添加子节点的类型
		if(pid == 1){//如果为1 说明是在 无锡新区建设环保局 下添加节点
			$("#ORG_TYPE").val("C");
			iconType = "../../../../img/zzjg/pd_dm_company.gif";
			
		}else{
			$("#ORG_TYPE").val("D");
			iconType = "../../../../img/zzjg/pd_dm_depart.gif";
			
		}
		if($("#deptForm").validationButton()){//验证保存数据是否正确
			//生成json串
			var data = Form2Json.formToJSON(deptForm);
			//组成保存json串格式
			var data1 = defaultJson.packSaveJson(data);
			$.ajax({
				url : controllername + "?insert",
				data : data1,
				dataType : "json",
				type : "post",
				async : true,
				success : function(response) {
					xAlert("保存成功！");
					//将返回json转换成对象
					var resultObj = defaultJson.dealResultJson(response.msg);
					
					//刷新父页面的tree结构
					var a=$(window).manhuaDialog.getParentObj();
					//重新加载数据
					var newNode = {"id":resultObj.ORGANIZE_UID,"parentId":resultObj.P_ORGANIZE_UID,"name":resultObj.ORG_NAME,"icon":iconType,"type":resultObj.ORG_TYPE};
					a.addNode(pid,newNode);
					//关闭当前页
					$(window).manhuaDialog.close();
				}
			});
		}else{
			xFailMsg("名称不能为空！","");
		}
	});
	
	//关闭按钮
	$("#btnCancel").click(function(){
		$(window).manhuaDialog.close();
	});
	
});

$(document).ready(function() {
	
});
</script>

</body>
</html>