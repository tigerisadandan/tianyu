<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>栏目管理</title>

<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/ztree_css/demo.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/ztree_css/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.core-3.5.min.js"></script>
<%--<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.excheck-3.5.min.js"></script>--%>
<style type="text/css">
	div#rMenu {position:absolute; visibility:hidden; top:0; background-color: #555;text-align: left;padding: 2px;}
	div#rMenu ul li{
		margin: 1px 0;
		padding: 0 5px;
		cursor: pointer;
		list-style: none outside none;
		background-color: #DFDFDF;
	}
	th {
	font-weight: bold;
}
th {
	display: table-cell;
	vertical-align: inherit;
}
table {
	border-collapse: collapse;
	border-spacing: 0;
}
table {
	border-spacing: 2px;
	border-color: gray;
}
</style>
</head>
<body>
<app:dialogs/>
	  
<div class="container-fluid">
  <div class="row-fluid">
    <div class="span3">
    <p></p>
	  <div class="zTreeDemoBackground left">
	    <ul id="channelTree" class="ztree">
	    	<img alt="请稍后，正在加载数据……" src="${pageContext.request.contextPath }/img/loading.gif" />
	    </ul>
	  </div>
	  
	  <!-- 右键菜单 -->
	  <div id="rMenu">
		<ul>
			<li id="m_add" onclick="addChannel();">增加子栏目</li>
			<li id="m_fresh" onclick="freshMenu()">刷新</li>
			<li id="m_reset" onclick="updateChannel();">修改栏目</li>
			<li id="m_del" onclick="deleteChannel();">删除节点</li>
		</ul>
	  </div>
    </div>
    <div class="span9">
		<div class="B-small-from-table-autoConcise">
			<h4 class="title" id="h4">栏目管理（ *选中要操作的节点右键，即可操作菜单）
		    <span class="pull-right">
		    	
		    	<button class="btn" id="saveChannelInfo">保存</button>
		    </span>
			</h4>
			
		<form method="post" id="queryForm"  >
		<table style="width: 100%" class="B-table">
		<!--可以再此处加入hidden域作为过滤条件 -->
				<input type="hidden" name="channel_uid" id="channel_uid" fieldname="channel_uid"/>
				<input id="p_channel_uid" name="p_channel_uid" type="hidden" fieldname="p_channel_uid"/>
							
		<!--可以再此处加入hidden域作为过滤条件 -->
			<tr>
				<th width="5%">父栏目名称</th>
				<td width="10%">
					<input type="text" id="p_channel_name" name="p_channel_name" />	
				</td>
			</tr>
			<tr>
				<th width="5%">事件KEY</th>
				<td width="10%">
					<input type="text" placeholder="必填" check-type="required" name="eventkey" id="eventkey" fieldname="eventkey" ></td>
			</tr>
			<tr>
				<th width="5%">栏目名称</th>
				<td width="10%">
					<input type="text" placeholder="必填" check-type="required" name="channel_name" id="channel_name" fieldname="channel_name"  />
				</td>
			</tr>
			<tr>
				<th width="5%">栏目简称</th>
				<td width="10%">
					<input type="text" name="channel_sname" id="channel_sname" fieldname="channel_sname"  >
				</td>
			</tr>
			<tr>
				<th width="5%">栏目图片</th>
				<td width="10%">
					<input type="text" name="channel_pic" id="channel_pic" fieldname="channel_pic"></td>
			</tr>
			<tr>
				<th width="5%">栏目URL</th>
				<td width="10%">
					<input type="text" name="channel_url" id="channel_url" fieldname="channel_url">
				</td>
			</tr>
			<tr style="display: none">
				<th width="5%">栏目类型</th>
				<td width="10%">
					<select type="text"  name="channel_type" fieldname="channel_type" id="channel_type" kind="dic" src="LMLX"></select>
				</td>
			</tr>
			<tr>
				<th width="5%">排序号</th>
				<td width="10%">
					<input type="text" name="serial_no" id="serial_no" fieldname="serial_no" >
				</td>
			</tr>
			<tr>
				<th width="5%">是否有效</th>
				<td width="10%">
					<select type="text" placeholder="必填" check-type="required"  name="enabled" fieldname="enabled" id="enabled" kind="dic" src="SF"></select>
				</td>
			</tr>
			<tr>
				<th width="5%">备注</th>
				<td width="10%">
					<textarea class="span12" rows="2"  id="describe" name="describe" fieldname="describe"></textarea>
				</td>
			</tr>
		</table>
		</form>
		</div>
		
	
		
		<div class="B-small-from-table-autoConcise" id="admuserList">
			<h4 class="title" id="h4">具有操作权限用户列表
				<span class="pull-right">
					<button class="btn" id="addAdmUserList">添加操作用户</button>			
				</span>
			</h4>
			
			<div class="overFlowX">
	            <table width="100%" class="table-hover table-activeTd B-table" id="DTadm" type="single">
					<thead>
						<tr>
							<th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
							<th fieldname="PFR" colindex=1 tdalign="center" maxlength="30" >&nbsp;所属系统&nbsp;</th>
							<th fieldname="CHANNEL_NAME" colindex=1 tdalign="center" maxlength="30" >&nbsp;栏目名称&nbsp;</th>
							<th fieldname="ACCOUNT" colindex=2 tdalign="center" maxlength="30" >&nbsp;登录名&nbsp;</th>
							<th fieldname="NAME" colindex=3 tdalign="center" maxlength="30" >&nbsp;用户姓名&nbsp;</th>
							<th fieldname="ADM_AUTH_CHANNEL_UID" colindex=6 tdalign="center"  CustomFunction="caozuoFunAdm">&nbsp;操作&nbsp;</th>
						</tr>
					</thead>
				    <tbody></tbody>
				</table>
	       </div>
		</div>
		
		<div class="B-small-from-table-autoConcise" id="recuserList">
			<h4 class="title" id="h4">具有接收权限用户列表
				<span class="pull-right">
					
					<button class="btn" id="addRecUserList">添加接收用户</button>
				</span>
			</h4>
			
			<div class="overFlowX">
	            <table width="100%" class="table-hover table-activeTd B-table" id="DTrec" type="single">
					<thead>
						<tr>
							<th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
							<th fieldname="PFR" colindex=1 tdalign="center" maxlength="30" >&nbsp;所属系统&nbsp;</th>
							<th fieldname="CHANNEL_NAME" colindex=1 tdalign="center" maxlength="30" >&nbsp;栏目名称&nbsp;</th>
							<th fieldname="ACCOUNT" colindex=2 tdalign="center" maxlength="30" >&nbsp;登录名&nbsp;</th>
							<th fieldname="NAME" colindex=3 tdalign="center" maxlength="30" >&nbsp;用户姓名&nbsp;</th>
							<th fieldname="REC_AUTH_CHANNEL_UID" colindex=6 tdalign="center"  CustomFunction="caozuoFunRec">&nbsp;操作&nbsp;</th>
						</tr>
					</thead>
				    <tbody></tbody>
				</table>
	       </div>
		</div>
		
		<form method="post" id="queryUserForm">
		  	<table class="B-table" width="100%">
				<!--可以再此处加入hidden域作为过滤条件 -->
				<TR  style="display:none;">
					<TD>
						<input type="text" class="span12" kind="text"  fieldname="rownum" value="1000" operation="<=" >
						<input type="text" class="span12" kind="text"  fieldname="CHANNEL_UID" id="CHANNEL_UID_QUERY" operation="=">
					</TD>
				</TR>
			</table>
		  </form>
		  
		  
	</div>
  </div>
</div>
<form name="frmPost" method="post" style="display:none" target="_blank" id ="frmPost">
		<!--系统保留定义区域-->
		<input type="hidden" name="queryXML" id = "queryXML">
		<input type="hidden" name="txtXML" id = "txtXML">
		<input type="hidden" name="txtFilter"  order="asc" fieldname = "CHANNEL_UID" id = "txtFilter">
		<input type="hidden" name="resultXML" id = "resultXML">
		<input type="hidden" name="queryResult" id = "queryResult">
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData">
</form>
<script type="text/javascript">

var controllername= "${pageContext.request.contextPath }/wndjsbg/wxChannelController";
var controllernameadm= "${pageContext.request.contextPath }/wndjsbg/weixin/atAdmAuthChannelController";
var controllernamerec= "${pageContext.request.contextPath }/wndjsbg/weixin/atRecAuthChannelController";

function caozuoFunAdm(obj){
	
	var showHtml="";
	showHtml +="<span class='btn btn-link' id='addSpan' onclick='delAdmUser(this);'  >";
	showHtml +="移除 ";
	showHtml +="</span>";
	return showHtml;
}

function delAdmUser(obj){
	while(obj.tagName.toLowerCase() != "tr"){
	    obj = obj.parentNode;
	    if(obj.tagName.toLowerCase() == "table")return null;
	   }
	obj.click();
	var data = $("#DTadm").getSelectedRow();
	var tempJson = convertJson.string2json1(data);
	var adm_auth_channel_uid=tempJson.ADM_AUTH_CHANNEL_UID;
//alert(adm_auth_channel_uid);
	
	defaultJson.doInsertJson(controllernameadm + "?delete&adm_auth_channel_uid="+adm_auth_channel_uid, null);

	var dataadm = combineQuery.getQueryCombineData(queryUserForm,frmPost, DTadm);
	defaultJson.doQueryJsonList(controllernameadm+"?query", dataadm, DTadm);
}


function caozuoFunRec(obj){

	var showHtml="";
	showHtml +="<span class='btn btn-link' id='addSpan' onclick='delRecUser(this);'  >";
	showHtml +="移除 ";
	showHtml +="</span>";
	return showHtml;
}

function delRecUser(obj){
	while(obj.tagName.toLowerCase() != "tr"){
	    obj = obj.parentNode;
	    if(obj.tagName.toLowerCase() == "table")return null;
	   }
	obj.click();
	var data = $("#DTrec").getSelectedRow();
	var tempJson = convertJson.string2json1(data);
	var rec_auth_channel_uid=tempJson.REC_AUTH_CHANNEL_UID;
	defaultJson.doInsertJson(controllernamerec + "?delete&rec_auth_channel_uid="+rec_auth_channel_uid, null);

	var datarec = combineQuery.getQueryCombineData(queryUserForm,frmPost, DTrec);
	defaultJson.doQueryJsonList(controllernamerec+"?query&channelid="+channelid, datarec, DTrec);
}



var setting = {
	async: {
		enable: true,
		url: controllername + "/query",		
		dataFilter: function (treeId, parentNode, responseData) {	
			   return responseData;		   
			}
	},
	view: {
		dblClickExpand: true
	},
	data: {
		simpleData: {
			enable: true,		
		}
	},
	callback: {
		onRightClick:showMenuValue,
		onClick:showMenuInfo,
		onDblClick: showUserList,	
	}
};

function showUserList(event, treeId, treeNode) {
	$("#saveChannelInfo").hide();
	
	var channelid=treeNode.id;
	
	$("#CHANNEL_UID_QUERY").val(channelid);
	var dataadm = combineQuery.getQueryCombineData(queryUserForm,frmPost, DTadm);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllernameadm+"?query", dataadm, DTadm);


	var datarec = combineQuery.getQueryCombineData(queryUserForm,frmPost, DTrec);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllernamerec+"?query&channelid="+channelid, datarec, DTrec);
}


function showMenuInfo(event, treeId, treeNode) {
	$("#saveChannelInfo").hide();
    channelTreeJson = treeNode;
    $("#channel_uid").val(treeNode.id);
	$("#channel_name").val(treeNode.name);
	$("#enabled").val(treeNode.ENABLED);
	$("#describe").val(treeNode.DESCRIBE);
	$("#serial_no").val(treeNode.SERIAL_NO);	
	var pid= channelTreeJson.P_CHANNEL_UID;
	if(pid==null||pid==''){
		$("#p_channel_uid").val(channelTreeJson.P_CHANNEL_UID);
		$("#p_channel_name").val("根节点");
	}else{
		$("#p_channel_uid").val(channelTreeJson.P_CHANNEL_UID);
		$("#p_channel_name").val(channelTreeJson.P_CHANNEL_NAME);
	}
	$("#channel_sname").val(treeNode.CHANNEL_SNAME);
	$("#channel_pic").val(treeNode.CHANNEL_PIC);
	$("#channel_url").val(treeNode.CHANNEL_URL);
	$("#eventkey").val(treeNode.EVENTKEY);
	$("#channel_type").val(treeNode.CHANNEL_TYPE);

};

/**
 * 点击右键时，获取的菜单信息
 */
function showMenuValue(event, treeId, treeNode) {
	$("#saveChannelInfo").hide();
	channelTreeJson = treeNode;
	if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
		// 取消选中节点
		zTree.cancelSelectedNode();
		showRMenu("root", event.clientX, event.clientY);
	} else if (treeNode && !treeNode.noR) {
		// 设置选中节点
		zTree.selectNode(treeNode);
		showRMenu("node", event.clientX, event.clientY);
	}
}

/**
 * 显示右键按钮
 * type	 
 * x	右键时所在的x坐标
 * y	右键时所在的y坐标
 */
function showRMenu(type, x, y) {
//	alert("showRMenu : " + type);
	$("#rMenu ul").show();
	if (type=="root") {
		$("#m_add").hide();
		$("#m_fresh").hide();
		$("#m_reset").hide();
		$("#m_del").hide();
	} else {
		var levelno = channelTreeJson.id;
		// 根节点只能添加
		if(levelno == 0) {
			$("#m_add").show();
			$("#m_fresh").show();
			$("#m_reset").hide();
			$("#m_del").hide();		
		//其他正常
		}  else {
			$("#m_add").show();
			$("#m_fresh").hide();
			$("#m_reset").show();
			$("#m_del").show();
		}
		
	}
	rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});
	$("body").bind("mousedown", onBodyMouseDown);
}

/**
 * 隐藏右键按钮
 */
function hideRMenu() {
	if (rMenu) rMenu.css({"visibility": "hidden"});
	$("body").unbind("mousedown", onBodyMouseDown);
}

function onBodyMouseDown(event) {
	if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
		rMenu.css({"visibility" : "hidden"});
	}
}

/**
 * 添加按钮方法
 */
function addChannel() {
	hideRMenu();

	$("#p_channel_uid").val(channelTreeJson.id);
	$("#p_channel_name").val(channelTreeJson.name);
	$("#p_channel_name").attr("readonly", "readonly");

	$("#channel_uid").val("");
	$("#channel_name").val("");
	$("#enabled").val("1");
	$("#describe").val("");
	$("#serial_no").val("");	
	
	$("#channel_sname").val("");
	$("#channel_pic").val("");
	$("#channel_url").val("");
	$("#eventkey").val("");
	$("#channel_type").val("");
	$("#saveChannelInfo").show();
	// 设置操作标识符，1表示添加 
	operatorSign = 1;
}

/**
 * 修改按钮方法
 */
function updateChannel() {
	hideRMenu();

	$("#channel_uid").val(channelTreeJson.id);
	$("#channel_name").val(channelTreeJson.name);
	$("#enabled").val(channelTreeJson.ENABLED);
	$("#describe").val(tchannelTreeJson.DESCRIBE);
	$("#serial_no").val(channelTreeJson.SERIAL_NO);	
	var pid= channelTreeJson.P_CHANNEL_UID;
	if(pid==null||pid==''){
		$("#p_channel_uid").val(channelTreeJson.P_CHANNEL_UID);
		$("#p_channel_name").val("根节点");
	}else{
		$("#p_channel_uid").val(channelTreeJson.P_CHANNEL_UID);
		$("#p_channel_name").val(channelTreeJson.P_CHANNEL_NAME);
	}
	$("#channel_sname").val(channelTreeJson.CHANNEL_SNAME);
	$("#channel_pic").val(channelTreeJson.CHANNEL_PIC);
	$("#channel_url").val(channelTreeJson.CHANNEL_URL);
	$("#eventkey").val(channelTreeJson.EVENTKEY);
	$("#channel_type").val(channelTreeJson.CHANNEL_TYPE);

	$("#saveChannelInfo").show();
	// 设置操作标识符，2表示修改
	operatorSign = 2;
}


function freshMenu() {
	$("#saveChannelInfo").hide();
	$.fn.zTree.init($("#channelTree"), setting);
	hideRMenu();
}

function deleteChannel(){
	$("#saveChannelInfo").hide();
  	var channelid=channelTreeJson.id;
	defaultJson.doInsertJson(controllername + "/delete?channel_uid="+channelid, null);
	hideRMenu();
	$.fn.zTree.init($("#channelTree"), setting);
}

//点击保存按钮
$(function() {
	
	var saveChannelInfoBtn = $("#saveChannelInfo");
	saveChannelInfoBtn.click(function() {
		var p_channel_uid=$("#p_channel_uid").val();
		if(p_channel_uid==null||p_channel_uid=='') {
			xInfoMsg("请选择一个父栏目进行添加！");
			return;
		}
		if($("#queryForm").validationButton()) {
				//生成json串
			var data = Form2Json.formToJSON(queryForm);
				//组成保存json串格式
			var data1 = defaultJson.packSaveJson(data);
				//通过判断id是否为空来判断是插入还是修改
				
			var success = false;
			if(operatorSign == 1) {
				success = defaultJson.doInsertJson(controllername + "/insert", data1, null);
			}else if(operatorSign == 2) {
				success = defaultJson.doUpdateJson(controllername + "/update", data1, null);
			}
			$("#saveChannelInfo").hide();
			$.fn.zTree.init($("#channelTree"), setting);
		}
	});

	$("#saveChannelInfo").hide();
	var addAdmUserListBtn = $("#addAdmUserList");
	addAdmUserListBtn.click(function() {
		if(typeof(channelTreeJson)=='undefined'){
			xInfoMsg("请点击选择一个栏目！");
			return;
		}
		var channelid= channelTreeJson.id;	
		var channelname=channelTreeJson.name;
		
		if(typeof(channelid)==undefined||channelid==null||channelid=='') {
			xInfoMsg("请点击选择一个栏目！");
			return;
		}					 
		$(window).manhuaDialog({"title":"栏目>添加权限到操作用户","type":"text","content":"${pageContext.request.contextPath }/jsp/weixin/channelToAdmUsers.jsp?channelid="+channelid+"&channelname="+channelname,"modal":"4"});
	});

	var addRecUserListBtn = $("#addRecUserList");
	addRecUserListBtn.click(function() {		
		if(typeof(channelTreeJson)=='undefined'){
			xInfoMsg("请点击选择一个栏目！");
			return;
		}
		
		var channelid= channelTreeJson.id;	
		var channelname=channelTreeJson.name;
		
		if(typeof(channelid)==undefined||channelid==null||channelid=='') {
			xInfoMsg("请点击选择一个栏目！");
			return;
		}					 
		$(window).manhuaDialog({"title":"栏目>添加权限到接收用户","type":"text","content":"${pageContext.request.contextPath}/jsp/weixin/channelToRecUsers.jsp?channelid="+channelid+"&channelname="+channelname,"modal":"4"});
	});
});

var zTree,rMenu,channelTreeJson,operatorSign;
$(document).ready(function() {
	$.fn.zTree.init($("#channelTree"), setting);
	zTree = $.fn.zTree.getZTreeObj("channelTree");
	$("#channelTree").css("height",document.documentElement.clientHeight-50);

	rMenu = $("#rMenu");
});
</script>

</body>
</html>