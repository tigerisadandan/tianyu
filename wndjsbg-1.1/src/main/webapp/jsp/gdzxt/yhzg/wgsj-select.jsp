<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>整改事件</title>

<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/ztree_css/demo.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/ztree_css/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.core-3.5.min.js"></script>
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
	    <ul id="menuTree" class="ztree">
	    	<img alt="请稍后，正在加载数据……" src="${pageContext.request.contextPath }/img/loading.gif" />
	    </ul>
	  </div>
	</div> 

	<div class="span9">
		<form method="post" id="queryForm">
				<table class="B-table" width="100%">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<TR style="display: none;">
						<TD class="right-border bottom-border"></TD>
						<TD class="right-border bottom-border">
							<INPUT type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>
							<input type="hidden" id="yhuid" fieldname="sj.zg_yinhuan_uid" name = "yhuid" operation="="/>
						</TD>
					</TR>
				</table>
			</form> 
		  <div class="B-small-from-table-autoConcise" id="userList">
				<h4 class="title" id="h4">违规类型
					<span class="pull-right">
						<input type="hidden" id="valueType" />
					</span>
				</h4>
				<button >关闭</button>
				<button id="confirm">确定</button>
			  <div class="overFlowX">
		            <table width="100%" class="table-hover table-activeTd B-table" id="DT2" type="single"  pageNum="10" >
		                <thead>
		                	<tr>
		                		<th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
		                		<th fieldname="ZG_WEIGUI_SJ_UID" colindex=1 tdalign="center" Customfunction="doEditor">&nbsp; &nbsp;</th>
								<th fieldname="WEIGUI_CONTENT" colindex=1 tdalign="center" maxlength="30" >&nbsp;违规内容 &nbsp;</th>
								<th fieldname="XMJL_SCORE" colindex=2 tdalign="center" maxlength="30" >&nbsp;项目经理<br/>扣分值 &nbsp;</th>
								<th fieldname="ZJ_SCORE" colindex=3 tdalign="center" maxlength="30" >&nbsp;总监<br/>扣分值&nbsp;</th>
								<th fieldname="JSDW_SCORE" colindex=5 tdalign="center" >&nbsp;建设单位<br/>扣分值&nbsp;</th>
								<th fieldname="SGDW_SCORE" colindex=6 tdalign="center" >&nbsp;施工单位<br/>扣分值&nbsp;</th>
								<th fieldname="JLDW_SCORE" colindex=6 tdalign="center"  >&nbsp;监理单位<br/>扣分值&nbsp;</th>
		                	</tr>
		                </thead>
		              	<tbody></tbody>
		           </table>
		       </div>
	       </div>
		 
	</div>
  </div>
</div>
<FORM name="frmPost" method="post" style="display:none" target="_blank" id ="frmPost">
		<!--系统保留定义区域-->
		<input type="hidden" name="queryXML" id = "queryXML">
		<input type="hidden" name="txtXML" id = "txtXML">
		<input type="hidden" name="resultXML" id = "resultXML">
		<input type="hidden" name="queryResult" id = "queryResult">
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData">
	</FORM>
<script type="text/javascript">

var controllername= "${pageContext.request.contextPath }/yhzg/zgWeiguiSjController";
var setting = {
	async: {
		enable: true,
		url: controllername + "?getTree",
		autoParam: ["id"],
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
			idKey: "id",
			pIdKey: "parentId"
		}
	},
	callback: {
		//onRightClick  : showMenuValue,
		onClick: showMenuInfo,
		//onDblClick: showUserList,
		onAsyncSuccess: function(){zTree.expandAll(true);}
	}
};

function showMenuInfo(event, treeId, treeNode) {
	$('#h4').text(treeNode.name);
	$('#yhuid').val(treeNode.id);
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT2);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?query",data,DT2);
    //$("#queryForm").setFormValues(treeNode);
    
    //showResouceURL(menuTreeJson.id);
    
    //$("#DT1").clearResult();

};

var zTree,rMenu,menuTreeJson,operatorSign;
$(document).ready(function() {
	$.fn.zTree.init($("#menuTree"), setting);
	zTree = $.fn.zTree.getZTreeObj("menuTree");
	$("#menuTree").css("height",document.documentElement.clientHeight-50);

	rMenu = $("#rMenu");
});

function doEditor(obj){
	var html = "<input type=\"checkbox\"  name=\"wgsj\" value=\""+obj.ZG_WEIGUI_SJ_UID+"\">";	
	return html;
}

//获取选中checkbox的值
function getCheckBoxSelect(){
	var values = "";
	$("input:checkbox[name='wgsj']:checked").each(function(){
		values += ($(this).val()+",");
	})
	if(values.length!=0){
		values = values.substring(0,values.length-1);
	}
	
	return values;
}

//绑定确定按钮事件
$("#confirm").click(function(){
	var str = getCheckBoxSelect()
	if(str==''||str==undefined){
		alert('请选择违规内容!');
	}else{
		if(window.opener){
			//执行父窗口方法
			window.opener.loadWgsj(str);
		}
		window.close();
	}
});
</script>

</body>
</html>