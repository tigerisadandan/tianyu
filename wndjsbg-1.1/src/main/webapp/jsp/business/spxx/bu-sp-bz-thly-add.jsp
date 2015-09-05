<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>步骤处理时的退回理由-维护</title>
<%
	String type=request.getParameter("type");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/ztree_css/demo.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/ztree_css/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.core-3.5.min.js"></script>

<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/spxx/buSpBzThlyController";
var type ="<%=type%>";
//页面初始化
$(function() {
	init();

	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
		if($("#buSpBzThlyForm").validationButton())
		{
		    //生成json串
		    var data = Form2Json.formToJSON(buSpBzThlyForm);
		  //组成保存json串格式
		    var data1 = defaultJson.packSaveJson(data);
		  //调用ajax插入
		    if($("#THLY_UID").val() == "" || $("#THLY_UID").val() == null){
    			defaultJson.doInsertJson(controllername + "?insert", data1);
    			$("#buSpBzThlyForm").clearFormResult();
    		}else{
    			defaultJson.doUpdateJson(controllername + "?update", data1);
    		}
			var a=$(window).manhuaDialog.getParentObj();
		    a.init();
			$(window).manhuaDialog.close();
		}else{
			requireFormMsg();
		  	return;
		}
	});

	$("#btnClose").bind("click", function(){
		var a=$(this).manhuaDialog.close();
	});
	
	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#queryForm").clearFormResult();
    });
    
    <%
    if(type.equals("detail")){
	%>
	//置所有input 为disabled
	$(":input").each(function(i){
	   $(this).attr("disabled", "true");
	 });
	<%
		}
	%>
	
});
//页面默认参数
function init(){

	if (type == "insert") {
		$("input[name='ENABLED'][value='1']")[0].checked=true;	
	} else if (type == "detail") {
	
	} else {
		//生成json串
		var parentmain = $(window).manhuaDialog.getParentObj();
		var rowValue = parentmain.$("#resultXML").val();
		var tempJson = convertJson.string2json1(rowValue);
		$("#QTHLY_UID").val(tempJson.THLY_UID);
		//查询表单赋值
		var data = combineQuery.getQueryCombineData(buForm, frmPost);
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
				var res = dealSpecialCharactor(response.msg);
				$("#resultXML").val(response.msg);
				var resultobj = defaultJson.dealResultJson(res);
				$("#buSpBzThlyForm").setFormValues(resultobj);		
				return true;
			}
		});
	}
}


function selectSsbz(){
	//popPage = "xm";
	$(window).manhuaDialog({"title":"退回理由->选择步骤","type":"text","content":"${pageContext.request.contextPath }/jsp/business/spxx/spxx-bz-index.jsp","modal":"2"});	
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
   // alert(responseData.length);
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
<div class="row-fluid">
  
	<div style="height:5px;"></div>
	<div class="B-small-from-table-autoConcise">
      <h4 class="title">步骤处理时的退回理由
      	<span class="pull-right">
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>		
<%--      		<button id="btnClear_Bins" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">清空</button>--%>
	  		<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">保存</button>
      	</span>
      </h4>
     <form action="post" id="buForm">
     	
		<input type="hidden" id="QTHLY_UID" fieldname="THLY_UID" name="THLY_UID" operation="=" />
	 </form>
     <form method="post" id="buSpBzThlyForm"  >
      <table class="B-table" width="100%" >

	  	<input type="hidden" id="THLY_UID" fieldname="THLY_UID" name = "THLY_UID"/></TD>
        <tr>
        	<th width="8%" class="right-border bottom-border text-right">业务名称</th>
			<td class="bottom-border right-border" width="23%">
				<button class="btn btn-link" id="spywid" type="button" onclick="showSpyw()" style="width:95%">
				<input id="SPYW_UID" class="right-border bottom-border text-left"  style="width:90%" fieldname="SPYW_UID" name="SPYW_UID" readOnly type="text" value='--请选择--' />
<%--					<i class="icon-edit"></i>--%>
				</button>
			</td>
							
			<th width="8%" class="right-border bottom-border text-right">审批步骤</th>
       	 	<td class="bottom-border right-border" width="23%">
       	 		<select id="spbz_uid" class="span12" style="width:50%" name="SPBZ_UID" fieldname="SPBZ_UID"   defaultMemo="--请选择--"></select>  
       	 	</td>
         	
        </tr>
        <tr>
        	<th width="8%" class="right-border bottom-border text-right">是否有效</th>
       		<td class="bottom-border right-border"width="15%">
         		<input class="span12"  id="ENABLED_ID" type="radio" check-type="required" fieldname="ENABLED" name = "ENABLED" kind="dic" src="SF" />
         	</td>
	        <th width="8%" class="right-border bottom-border text-right">排序号</th>
	        <td class="bottom-border right-border" width="15%">
         		<input id="SERIAL_NO" name="SERIAL_NO"  fieldname="SERIAL_NO" value=100 type="number"  check-type="required" />
         	</td>
        </tr>
		<tr>
	        <th width="8%" class="right-border bottom-border text-right">退回理由</th>
	        <td colspan="3" class="bottom-border right-border" >
	        	<textarea class="span12" rows="2" id="LIYOU"  fieldname="LIYOU" name="LIYOU"></textarea>
	        </td>
        </tr>
        <tr>
	        <th width="8%" class="right-border bottom-border text-right">备注</th>
	        <td colspan="3" class="bottom-border right-border" >
	        	<textarea class="span12" rows="2" id="BZ" check-type="maxlength" maxlength="4000" fieldname="BZ" name="BZ"></textarea>
	        </td>
        </tr>
      </table>
      </form>
      
      	<div id="spywContent" class="spywContent" style="display:none; position: absolute;">
			<ul id="treeDemo" class="ztree" style="margin-top:0; width:280px;"></ul>
		</div>
    </div>
   </div>
  </div>
  <div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id = "queryXML">
         <input type="hidden" name="txtXML" id = "txtXML">
         <input type="hidden" name="resultXML" id = "resultXML">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
		
 	</FORM>
 </div>
</body>
<script>
</script>
</html>