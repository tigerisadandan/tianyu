<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>审批步骤查询</title>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/spxx/buSpBzController/";

//页面初始化
$(function() {
	init();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {
		//alert("-----");
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"query",data,DT1);
	});
	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#queryForm").clearFormResult();
    });
    
	$("#btnClose").bind("click", function(){
		var a=$(this).manhuaDialog.close();
	});
	
	$("#btnSelect").click(function() {
	        if($("#DT1").getSelectedRowIndex()==-1){
	    		xInfoMsg("请选择一条记录!");
			    return;
			 }
	    	var obj = $("#DT1").getSelectedRowJsonObj();
		   	var type = $(window).manhuaDialog.getParentObj().$("#SPBZ_UID")[0].tagName;
		//   	alert("-----"+type);
	    	if(type == "INPUT"){
		        $(window).manhuaDialog.getParentObj().$("#SPBZ_UID").val(obj.BZMC);
		        $(window).manhuaDialog.getParentObj().$("#SPBZ_UID").attr("code", obj.SPBZ_UID);
	    	}else{
	    		$(window).manhuaDialog.getParentObj().$("#SPBZ_UID").val(obj.SPBZ_UID);
	    	}
	        $(window).manhuaDialog.close();
  	});
});

//页面默认参数
function init(){
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"query",data,DT1);
}

//点击获取行对象
function tr_click(obj,tabListid){
	//$("#btnDelete").attr("disabled", false);
}
</script>
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="B-small-from-table-autoConcise">
			<h4 class="title">
				审批步骤信息
				<span class="pull-right">  
					<button id="btnSelect" class="btn" type="button" >选择</button>
					<button id="btnClose" class="btn" type="button" >关闭</button>
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
						<th width="5%" class="right-border bottom-border text-right">步骤名称</th>
						<td class="right-border bottom-border" width="20%">
							<input class="span12" type="text" id="QBZMC" name="BZMC" fieldname="BZMC" operation="like" >
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
		                	<th  name="XH" id="_XH" style="width:10px" colindex=0 tdalign="center">&nbsp;#&nbsp;</th>
							<th fieldname="SPYWMC" colindex=1 tdalign="center" maxlength="15">&nbsp;审批业务名称&nbsp;</th>
							<th fieldname="BZMC" colindex=2 tdalign="center" maxlength="15">&nbsp;步骤名称&nbsp;</th>
							<th fieldname="BZXH" colindex=3 tdalign="center" >&nbsp;步骤序号&nbsp;</th>
							<th fieldname="BZLX_SV" colindex=4 tdalign="center">&nbsp;步骤类型&nbsp;</th>
							<th fieldname="CLTS" colindex=5 tdalign="center" >&nbsp;处理天数&nbsp;</th>
							<th fieldname="TSLX_SV" colindex=6 tdalign="center" >&nbsp;处理天数&nbsp;</th>							
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
		<input type="hidden" name="queryXML" id="queryXML"/>
		<input type="hidden" name="txtXML" id="txtXML"/>
		<input type="hidden" name="txtFilter" order="desc" fieldname="SPYW_UID" id="txtFilter"/>
		<input type="hidden" name="txtFilter" order="asc" fieldname="BZXH" id="txtFilter"/>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<input type="hidden" id="queryResult"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>	
	</FORM>
</div>
</body>
</html>