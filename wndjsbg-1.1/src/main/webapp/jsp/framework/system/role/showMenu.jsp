<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>

<%--
	String account = request.getParameter("account");
	String roleId = request.getParameter("roleId");
	
--%>
<%
	String type = request.getParameter("type");
	String id = request.getParameter("id");
 %>
<title>insertDemo</title>

<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/ztree_css/demo.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/ztree_css/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.min.js"></script>

</head>
<body>
<app:dialogs/>
<div class="container-fluid">
  <div class="row-fluid" align="center">
   	  <div class="B-small-from-table-autoConcise">
	  <h4 class="title" style="width: 380px">
	    <span class="pull-right" >
	    	<button class="btn" id="saveUserInfo">保存</button>
		    <button class="btn" id="btnCancel">关闭</button>
	    </span>
	  </h4>
	  <div class="zTreeDemoBackground left" style="height: 100%;margin-right:100px;">
	    <ul id="menuTree" class="ztree" style="height: 400px;width: 350px;">
	    	<img alt="请稍后，正在加载数据……" src="${pageContext.request.contextPath }/img/loading.gif" />
	    </ul>
	  </div>
    </div>
  </div>
</div>


<script type="text/javascript">
var controllername= "${pageContext.request.contextPath }/bgMenuController.do";
var type = "<%=type %>"; 
var id = "<%=id %>";
//设置角色权限
var setting = {
	check: {
		enable: true,
		chkStyle : "checkbox",
		chkboxType : {"Y":"ps","N":"s"}
	},
	async: {
		enable: true,
		url: controllername + "?loadAllMenu&type="+type+"&id="+id,
		autoParam: ["id"]
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
	}
};
/*
//设置用户权限
var setting1 = {
	check: {
		enable: true,
		chkStyle : "checkbox",
		chkboxType : {"Y":"ps","N":"s"}
	},
	async: {
		enable: true,
		url: controllername + "?loadAllMenu&roleId="+roleId,
		autoParam: ["id"]
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
	}
};
*/
	
//点击保存按钮
$(function() {
	var saveUserInfoBtn = $("#saveUserInfo");
	saveUserInfoBtn.click(function() {
		var zTree = $.fn.zTree.getZTreeObj("menuTree");
		// 获取输入框被勾选的节点集合
		var nodes = zTree.getCheckedNodes(true);
		var val = "";
		var codes = "";
		for(var i = 0; i < nodes.length; i++) {
			val += nodes[i].id + ",";
			codes += nodes[i].code +",";
		}
		if(type=="role"){
			//保存角色分配的权限
			var success = defaultJson.doInsertJson(controllername + "?awardMenuToRole&roleId="+id+"&val="+val, null, null);
		}else if(type=="user"){
			//保存用户分配的权限
			defaultJson.doInsertJson(controllername+"?awardMenuToUser&userId="+id+"&val="+val+"&codes="+codes, null, null);
		}
	});
	
	$("#btnCancel").click(function(){
		parent.$(window).manhuaDialog.close();
	});
});

$(document).ready(function() {
	/* if (roleId !="" && roleId != null) {
	
		$.fn.zTree.init($("#menuTree"), setting);
	}else if(account != "" && account != null){
	
		$.fn.zTree.init($("#menuTree"), setting1);
	} */
	
	$.fn.zTree.init($("#menuTree"),setting)
});
</script>

</body>
</html>