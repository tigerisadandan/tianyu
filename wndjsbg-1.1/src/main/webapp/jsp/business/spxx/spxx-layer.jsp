<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>审批业务信息首页</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath }/css/ztree_css/demo.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/ztree_css/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/spxx/buSpYwxxController/";
var setting = {
	async: {
		enable: true,
		url: controllername + "getAllSpyw",
		autoParam: ["id"],
		dataFilter: function (treeId, parentNode, responseData) {
   //         alert(responseData[0].parentId);
            return responseData;
        }
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
//页面初始化
$(function() {
	
	
	//按钮绑定事件（清空）
    $("#btnYet").click(function() {
    	onCheck();
    });
	
});

var zTree,menuTreeJson,operatorSign;
$(document).ready(function() {
	$.fn.zTree.init($("#menuTree"), setting);
	zTree = $.fn.zTree.getZTreeObj("menuTree");
});
function onCheck(){
   	var node=zTree.getSelectedNodes()[0];
    if(node&&node.id){
		var uid = $(window.opener.document).find("#SPYW_UID").val();
		if(uid!=node.id){
			window.opener.setParent(node.id,node.name);
			window.close();
		}else{
			alert("不能选择自己作为父业务!");
		}
	}else{
		alert("没有选择任何业务!");
	}
}
</script>
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
	<p></p>
	<div class="row-fluid">
	<div class="B-small-from-table-autoConcise">
	 <h4 class="title">
      	<span class="pull-right">
      		<button id="btnClose" onClick="window.close()" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
	  		<button id="btnYet" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">确定</button>
      	</span>
      	</h4>
		<div class="span3">
    	<p></p>
			<div class="zTreeDemoBackground left">
				<ul id="menuTree" class="ztree">
		    		<img alt="请稍后，正在加载数据……" src="${pageContext.request.contextPath }/img/loading.gif" />
		    	</ul>
			</div>
    	</div>
	</div>   
	</div>  
</div>
</body>
</html>