<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>选择项目</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/gzhgl/gzhController.do";

//页面初始化
$(function() {
	init();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"?queryUndoneProject",data,DT1);
	});
	
	//关闭
	$("#btnClose").click(function(){
		$(window).manhuaDialog.close();
	});
	//保存
	$("#btnSave").click(function(){
		//得到选中的值
		var ids = "";
		$("input[name='projectId']:checked").each(function(index,obj){
			ids += $(obj).val()+","
		});
		//获取父窗口，并传递值
		var a=$(window).manhuaDialog.getParentObj();
			a.$("#type1").val("all");
			a.$("#pIds").val("-1");
			a.$("#pIds1").val("-1");
		if(ids!=""){
			ids = ids.substring(0,ids.length-1);
			a.$("#pIds").val("-1,"+ids);
			a.$("#pIds1").val("-1,"+ids);
		}
		//调用父窗口的方法
		a.init();
		//关闭当前窗口
		$(window).manhuaDialog.close();
		
	});
});
//页面默认参数
function init(){
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?queryUndoneProject",data,DT1);
	
	var parent = $(window).manhuaDialog.getParentObj();//获取父窗口
	var pIds = parent.$("#pIds").val();
		pIds = pIds.replace("(","");
		pIds = pIds.replace(")","");
		var array = pIds.split(",");
		for(var i = 0;i< array.length;i++){
			$("input[name='projectId']").each(function(index,obj){
				if($(obj).val() == array[i]){
					$(this).prop("checked","checked");
				}
			});
		}
}

//回调函数--用于修改新增
getWinData = function(data){
	//var subresultmsgobj = defaultJson.dealResultJson(data);
	var data1 = defaultJson.packSaveJson(data);
	if(JSON.parse(data).ID == "" || JSON.parse(data).ID == null){
		defaultJson.doInsertJson(controllername + "?insert", data1,DT1);
	}else{
		defaultJson.doUpdateJson(controllername + "?update", data1,DT1);
	}
	
};

//查看详细信息
	function doRandering(obj) {
		var showHtml = "";
		showHtml = "<input type='checkbox' name='projectId' value="+obj.PROJECTS_UID+" />";
		return showHtml;
	}
function  tr_dbclick(){
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
				告知会信息
				<span class="pull-right">
				    <button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
					<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">保存</button>
				</span>
			</h4>
			<form method="post" id="queryForm">
				<table class="B-table" width="100%">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<TR style="display: none;">
						<TD class="right-border bottom-border">
							<INPUT type="text" class="span12" kind="text" id="num" fieldname="rownum" value="100000" operation="<="/>
						</TD>
					</TR>
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr>
					<th width="5%" class="right-border bottom-border text-right">项目名称</th>
						<td class="right-border bottom-border" width="18%">
							<input class="span12" id="PROJECTS_NAME" type="text"  operation="like" name=PROJECTS_NAME fieldname="p.PROJECTS_NAME">
						</td>						
						<th width="5%" class="right-border bottom-border text-right">施工单位</th>
						<td class="right-border bottom-border" width="18%">
							<input class="span12" id="selCOMPANY_NAME" type="text"  operation="like" name="selCOMPANY_NAME" fieldname="sel.COMPANY_NAME">
						</td>
						<th width="5%" class="right-border bottom-border text-right">建设单位</th>
						<td class="right-border bottom-border" width="18%">
							<input class="span12" id="COMPANY_NAME" type="text"   operation="like" name="COMPANY_NAME" fieldname="jcu.COMPANY_NAME">
						</td>
						<th width="5%" class="right-border bottom-border text-right">项目代码</th>
						<td class="right-border bottom-border" width="18%">
							<input class="span12" id="PROJECTS_CODE" type="text"  operation="like" name=PROJECTS_CODE fieldname="p.PROJECTS_CODE">
						</td>
						<td class="right-border bottom-border text-right" width="20%" rowspan="2" style="background-image: none; background-position: initial initial; background-repeat: initial initial;">
			     	    <button id="btnQuery" class="btn btn-link"  type="button"><i class="icon-search"></i>查询</button>
           	 		</td>					
					</tr>
				</table>
			</form>
			<div style="height:5px;"> </div>	
			<div class="overFlowX">
	            <table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single" noPage=true pageNum="100000" >
	                <thead>
	                	<tr>
							<th rowspan="2" fieldname="PROJECTS_UID" width="2%"
									tdalign="center" colindex=2 CustomFunction="doRandering"
									noprint="true">&nbsp;选择&nbsp;</th>
							<th fieldname="PROJECTS_NAME" colindex=1 tdalign="center" maxlength="30" >&nbsp;项目名称&nbsp;</th>
							<th fieldname="COMPANY_NAME" colindex=2 tdalign="center" maxlength="30" >&nbsp;建设单位&nbsp;</th>
	                	</tr>
	                </thead>
	              	<tbody></tbody>
	           </table>
	       </div>
	       
	 	</div>
	</div>     
</div>
<div align="center">
	<FORM name="frmPost" method="post" style="display: none" target="_blank">
		<!--系统保留定义区域-->
		<input type="hidden" name="queryXML" /> 
		<input type="hidden" name="txtXML"/>
<%--		<input type="hidden" name="txtFilter" order="desc" fieldname="LRSJ" id="txtFilter"/>--%>
<%--	<input type="hidden" name="txtFilter" order="desc" fieldname="ID" id="txtFilter"/>--%>
		<input type="hidden" name="resultXML" id="resultXML" />
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>