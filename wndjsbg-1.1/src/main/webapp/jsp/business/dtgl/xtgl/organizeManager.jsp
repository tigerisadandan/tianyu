<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<%
	
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
				<INPUT type="text" class="span12" kind="text" name="ORGANIZE_UID" id="ORGANIZE_UID" fieldname="ORGANIZE_UID"  operation="="/>
			</TD>
		</TR>
		<!--可以再此处加入hidden域作为过滤条件 -->
	</table>
</form>
  <form method="post" id="queryForm2">
	<table class="B-table" width="100%">
		<!--可以再此处加入hidden域作为过滤条件 -->
		<TR style="display: none;">
			<TD class="right-border bottom-border"></TD>
			<TD class="right-border bottom-border">
				<INPUT type="text" class="span12" kind="text" name="USERS_UID" id="USERS_UID" fieldname="USERS_UID"  operation="="/>
			</TD>
		</TR>
		<!--可以再此处加入hidden域作为过滤条件 -->
	</table>
	</form>
  	<div class="span3 text-center">
  		<div class="zTreeDemoBackground left" style="height: 100%;width:100%;">
		   	<div class="zTreeDemoBackground left" style="height: 100%;width:100%;">
	  		  <ul id="menuTree" class="ztree" style="height: 500px;width:100%;">
	    	<img alt="请稍后，正在加载数据……" src="${pageContext.request.contextPath }/img/loading.gif" />
	    </ul>
	  </div>
	  </div>
  	</div>
  	<div class="span8 text-left" style="height:522px;overflow: hidden;">
  		<div style="height: 10px;"></div>
 		<table border="1" width="100%" height="512px" cellpadding="0" cellspacing="0" class="content">
			<tr>
				<td class="yw-title">
					<h4>人员管理
					<span class="pull-right" style="padding-right:3px;">
						<button id="btnAddUser" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">添加人员</button>
      					<button id="btnAddDept" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">添加部门</button>
      					<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">保存</button>
      				</span>
					</h4>
					
				</td>
			</tr>
			<tr>
				<td>
					<ul id="ywcl_list">
					<div class="B-small-from-table-autoConcise" id="organizeBox" style="display:none;">
					  <form method="post" id="organizeForm">
						<table class="B-table" width="100%" id="DT1">
							<tr>
						        <th width="8%" class="right-border bottom-border text-right">名称</th>
						        <td colspan="2" class="bottom-border right-border" >
						        	<input type="hidden" name="ORGANIZE_UID" id="ORGANIZE_UID" maxlength="20" fieldname="ORGANIZE_UID" />
						        	<input type="text" name="ORG_NAME" id="ORG_NAME" maxlength="20" fieldname="ORG_NAME" check-type="required" />
						        </td>
						    </tr>
						    <tr>
						        <th width="8%" class="right-border bottom-border text-right">地址</th>
						        <td colspan="2" class="bottom-border right-border" >
						        	<input type="text" name="ADDRESS" id="ADDRESS" maxlength="20" fieldname="ADDRESS" />
						        </td>
						    </tr>
						    <tr>
						        <th width="8%" class="right-border bottom-border text-right">电话</th>
						        <td colspan="2" class="bottom-border right-border" >
						        	<input type="text" name="PHONE" id="PHONE" maxlength="20" fieldname="PHONE" />
						        </td>
						    </tr>
						    <tr>
						        <th width="8%" class="right-border bottom-border text-right">传真</th>
						        <td colspan="2" class="bottom-border right-border" >
						        	<input type="text" name="FAX" id="FAX" maxlength="20" fieldname="FAX" />
						        </td>
						    </tr>
						    <tr>
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
					<!-- 第二块 -->
					<div class="B-small-from-table-autoConcise" id="departmentBox" style="display:none;">
					  <form method="post" id="departmentForm">
						<table class="B-table" width="100%" id="DT2">
							<tr>
						        <th width="8%" class="right-border bottom-border text-right">名称</th>
						        <td colspan="2" class="bottom-border right-border" >
						        	<input type="hidden" name="ORGANIZE_UID" id="ORGANIZE_UID" maxlength="20" fieldname="ORGANIZE_UID" />
						        	<input type="text" name="ORG_NAME" id="ORG_NAME" maxlength="20" fieldname="ORG_NAME" check-type="required" />
						        </td>
						    </tr>
						    <tr>
						        <th width="8%" class="right-border bottom-border text-right">地址</th>
						        <td colspan="2" class="bottom-border right-border" >
						        	<input type="text" name="ADDRESS" id="ADDRESS" maxlength="20" fieldname="ADDRESS" />
						        </td>
						    </tr>
						    <tr>
						        <th width="8%" class="right-border bottom-border text-right">电话</th>
						        <td colspan="2" class="bottom-border right-border" >
						        	<input type="text" name="PHONE" id="PHONE" maxlength="20" fieldname="PHONE" />
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
					<!-- 第三块 -->
					<div class="B-small-from-table-autoConcise" id="userBox">
					  <form method="post" id="userForm">
						<table class="B-table" width="100%" id="DT3">
							<tr>
						        <th width="8%" class="right-border bottom-border text-right">名称</th>
						        <td colspan="2" class="bottom-border right-border" >
						        	<input type="hidden" name="ORGANIZE_UID" id="ORGANIZE_UID" maxlength="20" fieldname="ORGANIZE_UID" />
						        	<input type="text" name="ORG_NAME" id="ORG_NAME" maxlength="20" fieldname="ORG_NAME" check-type="required" />
						        </td>
						    </tr>
						    <tr>
						        <th width="8%" class="right-border bottom-border text-right">用户名</th>
						        <td colspan="2" class="bottom-border right-border" >
						        	<input type="hidden" name="USER_UID" id="USER_UID" maxlength="20" fieldname="USER_UID" />
						        	<input type="text" name="" id="LOGON_NAME" maxlength="20" fieldname="" readonly="readonly" />
						        	<button id="queryBtn" class="btn btn-link" type="button"><i class="icon-search"></i></button>
						        </td>
						    </tr>
						    <tr>
						        <th width="8%" class="right-border bottom-border text-right">地址</th>
						        <td colspan="2" class="bottom-border right-border" >
						        	<input type="text" name="ADDRESS" id="ADDRESS" maxlength="20" fieldname="ADDRESS" />
						        </td>
						    </tr>
						    <tr>
						        <th width="8%" class="right-border bottom-border text-right">电话</th>
						        <td colspan="2" class="bottom-border right-border" >
						        	<input type="text" name="PHONE" id="PHONE" maxlength="20" fieldname="PHONE" />
						        </td>
						    </tr>
						    <tr>
						        <th width="8%" class="right-border bottom-border text-right">执法证号</th>
						        <td colspan="2" class="bottom-border right-border" >
						        	<input type="text" name="ZJ_CODE" id="ZJ_CODE" maxlength="20" fieldname="ZJ_CODE" />
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
var controllername = "${pageContext.request.contextPath }/nbgl/organizeController";
var controllername1 = "${pageContext.request.contextPath }/userController.do";
var org_type;
//设置角色权限
var setting = {
	async: {
		enable: true,
		url: controllername + "?queryOrganize",
	},
	view: {
		dblClickExpand: true
	},
	data: {
		simpleData: {
			enable: true,
			idKey: "id",
			pIdKey: "parentId"
		}
	},
	callback: {
		onClick: zTreeOnClick,
		onAsyncSuccess : defaultSelect
	}
};	
//点击保存按钮
$(function() {
	//保存按钮
	$("#btnSave").click(function(){
		if(org_type == "C"){//修改顶级节点
			if($("#organizeForm").validationButton()){//验证保存数据是否正确
			//生成json串
			var data = Form2Json.formToJSON(organizeForm);
			//组成保存json串格式
			var data1 = defaultJson.packSaveJson(data);
			$.ajax({
				url : controllername + "?update",
				data : data1,
				dataType : "json",
				type : "post",
				async : true,
				success : function(response) {
					xAlert("保存成功！");
				}
			});
			}else{
				xFailMsg("部门名称不能为空！","");
			}
		}else if(org_type == "D"){//修改部门信息
			if($("#departmentForm").validationButton()){//验证保存数据是否正确
			//生成json串
			var data = Form2Json.formToJSON(departmentForm);
			//组成保存json串格式
			var data1 = defaultJson.packSaveJson(data);
			$.ajax({
				url : controllername + "?update",
				data : data1,
				dataType : "json",
				type : "post",
				async : true,
				success : function(response) {
					xAlert("保存成功！");
				}
			});
			}else{
				xFailMsg("部门名称不能为空！","");
			}
		}else if(org_type == "U"){//修改用户信息
			if($("#userForm").validationButton()){//验证保存数据是否正确
			//生成json串
			var data = Form2Json.formToJSON(userForm);
			//组成保存json串格式
			var data1 = defaultJson.packSaveJson(data);
			$.ajax({
				url : controllername + "?update",
				data : data1,
				dataType : "json",
				type : "post",
				async : true,
				success : function(response) {
					xAlert("保存成功！");
				}
			});
			}else{
				xFailMsg("部门名称不能为空！","");
			}
		}
	});
	//添加人员
	$("#btnAddUser").click(function(){
		
	});
	//添加部门
	$("#btnAddDept").click(function(){
		//获取当前要添加子节点的父节点编号
		var pid = $("#ORGANIZE_UID").val();
		$(window).manhuaDialog({"title":"组织机构管理>添加部门","type":"text","content":"${pageContext.request.contextPath }/jsp/business/dtgl/xtgl/addDept.jsp?pid="+pid,"modal":"2"});
	});
	//添加人员
	$("#btnAddUser").click(function(){
		//获取当前要添加子节点的父节点编号
		var pid = $("#ORGANIZE_UID").val();
		$(window).manhuaDialog({"title":"组织机构管理>添加人员","type":"text","content":"${pageContext.request.contextPath }/jsp/business/dtgl/xtgl/addPerson.jsp?pid="+pid,"modal":"2"});
	});
	//关闭窗口
	$("#btnCancel").click(function(){
		parent.$(window).manhuaDialog.close();
	});
	//弹出人员信息列表
	$("#queryBtn").click(function(){
		$(window).manhuaDialog({"title":"组织机构管理>用户列表","type":"text","content":"${pageContext.request.contextPath }/jsp/business/dtgl/xtgl/showUserList.jsp","modal":"2"});
	});
});
//zTree点击事件
function zTreeOnClick(event, treeId, treeNode){
	$("#ORGANIZE_UID").val(treeNode.id);
	//alert(treeId+"----"+treeNode.id+"----"+treeNode.parentId+"------"+treeNode.name+"-----"+treeNode.type);
	//生成json串
	//回显
	var data = combineQuery.getQueryCombineData(queryForm, frmPost);
	var data1 = {
		msg : data
	};
	$.ajax({
		url : controllername + "?query",
			data : data1,
			cache : false,
			async : false,
			dataType : "json",
			type : 'post',
			success : function(response) {
				$("#resultXML").val(response.msg);
				var resultobj = defaultJson.dealResultJson(response.msg);
				//根据数据类型的不同，显示不同的样式内容（一次只显示一个容器）
				if(resultobj.ORG_TYPE == "C"){
					$("#organizeBox").css("display","block");
					$("#departmentBox").css("display","none");
					$("#userBox").css("display","none");
					//部门类型
					org_type = "C";
					$("#organizeForm").setFormValues(resultobj);
				}else if(resultobj.ORG_TYPE == "D"){
					$("#organizeBox").css("display","none");
					$("#departmentBox").css("display","block");
					$("#userBox").css("display","none");
					org_type = "D";
					$("#departmentForm").setFormValues(resultobj);
				}else if(resultobj.ORG_TYPE == "U"){
					$("#organizeBox").css("display","none");
					$("#departmentBox").css("display","none");
					$("#userBox").css("display","block");
					org_type = "U";
					$("#userForm").setFormValues(resultobj);
					//回显登陆名
					$("#USERS_UID").val(resultobj.USER_UID);
					if(resultobj.USER_UID != "" ){
							//回显登陆名
						var data3 = combineQuery.getQueryCombineData(queryForm2, frmPost);
						var data4 = {
							msg : data3
						};
						$.ajax({
							url : controllername1 + "?queryUser",
								data : data4,
								cache : false,
								async : false,
								dataType : "json",
								type : 'post',
								success : function(response) {
									var resultobj = defaultJson.dealResultJson(response.msg);
									$("#LOGON_NAME").val(resultobj.LOGON_NAME);
								}
						});
					
					}else{
						$("#LOGON_NAME").val("");
					}	
				}
				
				if(resultobj.ORG_TYPE == "C" || resultobj.ORG_TYPE == "D"){
					$("#btnAddUser").show();
					$("#btnAddDept").show();
				}else{
					$("#btnAddUser").hide();
					$("#btnAddDept").hide();
				}
				
			}
	});
}
//默认选中第一个节点
function defaultSelect(node,addFlag){
	var treeObj = $.fn.zTree.getZTreeObj("menuTree");
	var nodes = treeObj.getNodes();
	if (nodes.length>0) {
		treeObj.selectNode(nodes[0]);
		$(".curSelectedNode").trigger("click");//触发tree的第一个节点的click事件
	}
}
//添加节点
function addNode(parentId,newNode){
	var treeObj = $.fn.zTree.getZTreeObj("menuTree");
	var parentZNode = treeObj.getNodeByParam("id", parentId, null); //获取父节点  
	treeObj.addNodes(parentZNode, newNode);
}
$(document).ready(function() {
	$.fn.zTree.init($("#menuTree"),setting);
});

</script>

</body>
</html>