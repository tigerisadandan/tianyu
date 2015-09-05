<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>步骤处理时的退回理由首页</title>
<%
	String spbzuid=request.getParameter("spbzuid");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/ztree_css/demo.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/ztree_css/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/spxx/buSpBzThlyController";
var spbzuid ="<%=spbzuid%>";
//页面初始化
$(function() {

	init();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"?query",data,DT1);
		
	});
	//按钮绑定事件(新增)
	$("#btnInsert").click(function() {
		$(window).manhuaDialog({"title":"步骤处理时的退回理由>新增","type":"text","content":"${pageContext.request.contextPath }/jsp/business/spxx/bu-sp-bz-thly-add.jsp?type=insert","modal":"2"});
	});
	//按钮绑定事件(修改)
	$("#btnUpdate").click(function() {
		if($("#DT1").getSelectedRowIndex()==-1)
		 {
			requireSelectedOneRow();
		    return
		 }
		$("#resultXML").val($("#DT1").getSelectedRow());
		$(window).manhuaDialog({"title":"步骤处理时的退回理由>修改","type":"text","content":"${pageContext.request.contextPath }/jsp/business/spxx/bu-sp-bz-thly-view.jsp?type=update","modal":"2"});
	});

	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#queryForm").clearFormResult();
       
    });
});

//页面默认参数
function init(){

	//生成json串
	//var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	//defaultJson.doQueryJsonList(controllername+"?query",data,DT1);
	var rows = $("tbody tr" ,$("#DT1"));
	if(rows.size()==0){
		$("tbody" ,$("#DT1")).append("<tr style=\"height: 1px;border:none;\"></tr>");
	}
	
}


//点击获取行对象
function tr_click(obj,tabListid){
	//alert(JSON.stringify(obj));
}

//回调函数--用于修改新增
getWinData = function(data){
	//alert("-----");
	//var subresultmsgobj = defaultJson.dealResultJson(data);
	var data1 = defaultJson.packSaveJson(data);
	if(JSON.parse(data).ID == "" || JSON.parse(data).ID == null){
		defaultJson.doInsertJson(controllername + "?insert", data1,DT1);
	}else{
		defaultJson.doUpdateJson(controllername + "?update", data1,DT1);
	}
	
};


function rowUpdate(obj){
	while(obj.tagName.toLowerCase() != "tr"){
	    obj = obj.parentNode;
	    if(obj.tagName.toLowerCase() == "table")return null;
	   }
	obj.click();
	var data = $("#DT1").getSelectedRow();

	$("#resultXML").val(data);
	$(window).manhuaDialog({"title":"步骤处理时的退回理由>修改","type":"text","content":"${pageContext.request.contextPath }/jsp/business/spxx/bu-sp-bz-thly-view.jsp?type=update","modal":"2"});

}


function caozuoFun(obj){
	var showHtml ="<span class='btn btn-link' id='addSpan' onclick='rowUpdate(this);' title='修改' >";
	showHtml +="<i class='icon-file'></i> ";
	showHtml +="</span>";

	showHtml +="<span class='btn btn-link' id='deleteSpan' onclick='rowDelete(this);' title='删除' >";
	showHtml +="<i class='icon-trash'></i>";
	showHtml +="</span>";
	return showHtml;
}


function rowDelete(obj){
	while(obj.tagName.toLowerCase() != "tr"){
	    obj = obj.parentNode;
	    if(obj.tagName.toLowerCase() == "table")return null;
	   }
	obj.click();
	//var data = $("#DT1").getSelectedRow();

	var rowinx = $("#DT1").getSelectedRowIndex();
	xConfirm("信息确认","您选择的 第  [<font color=\"red\">"+parseInt(rowinx+1)+"</font>]行记录信息将被删除，删除后不可恢复！<br/><br/>确认要继续吗？");
		
	$('#ConfirmYesButton').attr('click','');
	$('#ConfirmYesButton').one("click",function(){
		$("#btnDelete").attr("disabled", true);
		 var rowJson = $("#DT1").getSelectedRow();
		 var data1 = defaultJson.packSaveJson(rowJson);
		 var flag = defaultJson.doDeleteJson(controllername+"?delete", data1, null);
		 if(flag){
			 $("#DT1").removeResult(rowinx);
			 //xAlert("信息提示","删除成功！");
		 }
	});
	
}






function showSpyw() {
	
	var cityObj = $("#SPYW_UID");
	var cityOffset = $("#SPYW_UID").offset();
	//alert("show------");
	$("#spywContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

	$("body").bind("mousedown", onBodyDown);
}
function hideSpyw() {
	$("#spywContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	//alert(event.target.id);
	if (!(event.target.id == "spywid" || event.target.id == "spywContent" || $(event.target).parents("#spywContent").length>0)) {
		hideSpyw();
	}
}
function filter(treeId, parentNode, responseData) {
   // alert(responseData);
    	return responseData;
};
var start_flag=true;
var setting = {
		async: {
			enable: true,
			contentType: "application/json",
			url:controllername+"?getSpYwxx",
			autoParam:["id=nodeid"],
			dataFilter: filter
		},
		data: {
			keep: {
				parent: true
			},
			key: {
				name: "name"
			},
			simpleData: {
				enable: true,
				idKey:"id",
				pIdKey:"pid"
			}
		},
		callback: {
		//	beforeClick: beforeClick,
			onClick: onClick,
			onAsyncSuccess: function(){
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				zTree.expandAll(true);
			},beforeAsync: function(){
				if(start_flag){
					start_flag = false;
					return true;
				}else{
					return false;
				}
			}
		}
	};

/**
 * 下接菜单树选择
 */
function beforeClick(treeId, treeNode) {
	var check = (treeNode && !treeNode.isParent);
	return check;
}

function onClick(e, treeId, treeNode) {
	//alert(treeNode.name+"---------id:"+treeNode.id);
	$("#SPYW_UID").val(treeNode.name);
	var obj=$("#spbz_uid");
	obj.attr("src","T#bu_sp_bz:spbz_uid as c:bzmc as t:spyw_uid='"+treeNode.id+"' order by bzxh asc ");
	obj.attr("kind","dic");
	obj.html('');
	reloadSelectTableDic(obj);
	hideSpyw();
}

$(document).ready(function(){
	$.fn.zTree.init($("#treeDemo"), setting);
});


</script>
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
	<p></p>
	<div class="row-fluid">
		<div class="B-small-from-table-autoConcise">
			<h4 class="title">
				步骤处理时的退回理由
				<span class="pull-right">  
					<button id="btnInsert" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">新增</button>
<%--      				<button id="btnUpdate" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">修改</button>--%>
<%--      				<button id="btnDelete" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">删除</button>--%>
      				<%if (!"".equals(spbzuid)&&spbzuid!=null) {%>
						<button id="btnSelect" class="btn" type="button" >选择</button>
						<button id="btnClose" class="btn" type="button" >关闭</button>
					<%} %>
      				
      				<script type="text/javascript">
		      				$(function() {
		      					$("#btnSelect").click(function() {
		      				        if($("#DT1").getSelectedRowIndex()==-1){
		      				    		xInfoMsg("请选择一条记录!");
		      						    return;
		      						 }
		      				    	var obj = $("#DT1").getSelectedRowJsonObj();
		      					   	var type = $(window).manhuaDialog.getParentObj().$("#CHULI_YIJIAN_ID")[0].tagName;
		      					//   	alert("-----"+type);
		      				    	$(window).manhuaDialog.getParentObj().$("#CHULI_YIJIAN_ID").val(obj.LIYOU);
		      				        $(window).manhuaDialog.close();
		      			    	});
		      					$("#btnClose").bind("click", function(){
		      						var a=$(this).manhuaDialog.close();
		      					});
		      					
		      					$("#btnDelete").click(function() {
		      				    	var rowinx = $("#DT1").getSelectedRowIndex();
		      				    	if(rowinx==-1)
		      						 {
		      							requireSelectedOneRow();
		      						    return
		      						 }
		      						xConfirm("信息确认","您选择的 第  [<font color=\"red\">"+parseInt(rowinx+1)+"</font>]行记录信息将被删除，删除后不可恢复！<br/><br/>确认要继续吗？");
		      						
		      						$('#ConfirmYesButton').attr('click','');
		      						$('#ConfirmYesButton').one("click",function(){
		      							$("#btnDelete").attr("disabled", true);
		      							 var rowJson = $("#DT1").getSelectedRow();
		      							 var data1 = defaultJson.packSaveJson(rowJson);
		      							 var flag = defaultJson.doDeleteJson(controllername+"?delete", data1, null);
		      							 if(flag){
		      								 $("#DT1").removeResult(rowinx);
		      								 //xAlert("信息提示","删除成功！");
		      							 }
		      						});
		      					});
		
		      				});
	      				</script>
				</span>
			</h4>
			<form method="post" id="queryForm">
				<table class="B-table" width="100%">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<TR style="display: none;">
						<TD class="right-border bottom-border"></TD>
						<TD class="right-border bottom-border">
							<INPUT type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>
						</TD>
					</TR>
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr>
						<th width="8%" class="right-border bottom-border text-right">业务名称</th>
						<td class="bottom-border right-border" width="15%">
							<button class="btn btn-link" id="spywid" type="button" onclick="showSpyw()">
							<input id="SPYW_UID" class="right-border bottom-border text-left" fieldname="SPYWMC" name="SPYWMC" readOnly type="text"  operation="like" />
			<%--					<i class="icon-edit"></i>--%>
							</button>
						</td>
										
						<th width="8%" class="right-border bottom-border text-right">审批步骤</th>
			       	 	<td class="bottom-border right-border" width="23%">
			       	 		<select id="spbz_uid" class="span12" style="width:90%" name="SPBZ_UID" fieldname="SPBZ_UID"   defaultMemo="--请选择--" operation="=" ></select>  
			       	 	</td>
						<th width="5%" class="right-border bottom-border text-right">退回理由</th>
						<td class="right-border bottom-border" width="20%">
							<input class="span12" type="text" id="QLIYOU" name="LIYOU" fieldname="LIYOU" operation="like" >
						</td>
						
						
			            <td class="text-left bottom-border text-right">
	                        <button id="btnQuery" class="btn btn-link"  type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-search"></i>查询</button>
           					<button id="btnClear" class="btn btn-link" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-trash"></i>清空</button>
			            </td>							
					</tr>
				</table>
			</form>
			<div style="height:5px;"> </div>	
			<div class="overFlowX">
	            <table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single"  pageNum="18">
	                <thead>
	                	<tr>
	                		<th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
							<th fieldname="THLY_UID" colindex=0 tdalign="center" style="width:50px"  CustomFunction="caozuoFun" >&nbsp;&nbsp;</th>
							<th fieldname="BZMC" colindex=1 tdalign="center" maxlength="15" >&nbsp;审批步骤名称&nbsp;</th>
							<th fieldname="LIYOU" colindex=2 tdalign="center" maxlength="30" >&nbsp;退回理由&nbsp;</th>
							<th fieldname="ENABLED" colindex=4 tdalign="center" >&nbsp;是否有效&nbsp;</th>
							<th fieldname="CREATED_NAME" colindex=5 tdalign="center" >&nbsp;创建人&nbsp;</th>
							<th fieldname="CREATED_DATE" colindex=6 tdalign="center" >&nbsp;创建时间&nbsp;</th>
							<th fieldname="SERIAL_NO" colindex=7 tdalign="center" >&nbsp;排序号&nbsp;</th>
	                	</tr>
	                </thead>
	              	<tbody ></tbody>
	           </table>
	       </div>
	 	</div>
	</div>   
	     <div id="spywContent" class="spywContent" style="display:none; position: absolute;">
			<ul id="treeDemo" class="ztree" style="margin-top:0; width:210px;"></ul>
		</div>  
</div>
<div align="center">
	<FORM name="frmPost" method="post" style="display: none" target="_blank">
		<!--系统保留定义区域-->
		<input type="hidden" name="queryXML"/> 
		<input type="hidden" name="txtXML"/>
		<input type="hidden" name="txtFilter" order="desc" fieldname="CREATED_DATE" id="txtFilter"/>
<%--		<input type="hidden" name="txtFilter" order="desc" fieldname="ID" id="txtFilter"/>--%>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>