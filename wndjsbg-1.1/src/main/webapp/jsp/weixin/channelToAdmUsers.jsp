<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<%
String channelid = request.getParameter("channelid");
String channelname = request.getParameter("channelname");
%>
<title>使用用户</title>

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
	  <h4 class="title" style="width: 260px">
	    <span class="pull-right" >
	    	<button class="btn" id="saveAdmUserInfo">保存</button>
		    <button class="btn" id="btnCancel">关闭</button>
	    </span>
	  </h4>
	  <div class="zTreeDemoBackground left" style="height: 280px;">
	    <ul id="admUserTree" class="ztree" style="height: 280px;width: 360px">
	    	<img alt="请稍后，正在加载数据……" src="${pageContext.request.contextPath }/img/loading.gif" />
	    </ul>
	  </div>
    </div>
  </div>
</div>


<script type="text/javascript">
var controllername= "${pageContext.request.contextPath }/wndjsbg/wxChannelController";
var CHANNEL_UID = "<%=channelid %>";
var CHANNELNAME = "<%=channelname %>";

var setting = {
	check: {
		enable: true,
		chkStyle : "checkbox",
		//chkboxType : {"Y":"ps","N":"s"}
	},
	async: {
		enable: true,
		url: controllername + "/loadAllAdmUsers?CHANNEL_UID="+CHANNEL_UID,
		autoParam: ["id"]
	},
	view: {
		dblClickExpand: false
	},
	data: {
		simpleData: {
			enable: true,
			idKey: "id",
			pIdKey: "parentId"
		}
	}
};
	
//点击保存按钮
$(function() {
	var saveAdmUserInfoBtn = $("#saveAdmUserInfo");
	saveAdmUserInfoBtn.click(function() {
		if(CHANNEL_UID==''||CHANNEL_UID==null){
			xInfoMsg("栏目不能为空！");
			return;
		}
	
		var zTree = $.fn.zTree.getZTreeObj("admUserTree");
		// 获取输入框被勾选的节点集合
		var nodes = zTree.getCheckedNodes(true);
		var val = "";
		for(var i = 0; i < nodes.length; i++) {
			val += nodes[i].account + ",";
		}

		if(val==''||val==null){
			xInfoMsg("请选择用户！");
			return;
		}
		var success = defaultJson.doInsertJson("${pageContext.request.contextPath }/wndjsbg/weixin/atAdmAuthChannelController?awardToUsers&channelid="+CHANNEL_UID+"&val="+val, null, null);
	});
	

	$("#btnCancel").click(function(){
		var a=$(this).manhuaDialog.close();
	});
});

$(document).ready(function() {
	$.fn.zTree.init($("#admUserTree"), setting);
});
</script>

</body>
</html>