<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>项目首页</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/project/projectsController";

//页面初始化
$(function() {
	init();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {
		 var status=$("input[name='status']:checked").val();
		 $("#QSTATUS").val(status);
		 if(status=='30'){
				// alert(status);
			$("#shrid").hide();			
			$("#shsjid").hide();	
		}else{
			$("#shrid").show();			
			$("#shsjid").show();
		}
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"?queryHb",data,DT1);
		getCount();

	});
	
	//按钮绑定事件(修改)
	$("#btnUpdate").click(function() {
		if($("#DT1").getSelectedRowIndex()==-1)
		 {
			requireSelectedOneRow();
		    return
		 }
		$("#resultXML").val($("#DT1").getSelectedRow());
		$(window).manhuaDialog({"title":"环保项目>审核","type":"text","content":"${pageContext.request.contextPath }/jsp/business/project/hb-project-add.jsp?type=update","modal":"2"});
	});
	//按钮绑定事件（导出EXCEL）
/**	$("#btnExpExcel").click(function() {
		 var t = $("#DT1").getTableRows();
		 if(t<=0)
		 {
			 xAlert("提示信息","请至少查询出一条记录！");
			 return;
		 }
	  	 $(window).manhuaDialog({"title":"导出","type":"text","content":"${pageContext.request.contextPath }/jsp/framework/print/TabListEXP.jsp?tabId=DT1","modal":"3"});
	});**/
	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#queryForm").clearFormResult();
        $("#QSTATUS").val("30");
        $("#PROJECTS_TYPE").val("H");
        
        $("#num").val("1000");
        $("input:radio[name='status']")[0].checked=true;
        $("input:radio[name='status']")[1].checked=false;
        $("input:radio[name='status']")[2].checked=false;
        $("#btnQuery").click();  
    });
	
});

//页面默认参数
function init(){
	$("#shrid").hide();			
	$("#shsjid").hide();
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?queryHb",data,DT1);
	getCount();

}


//点击获取行对象
function tr_click(obj,tabListid){
	if(obj.STATUS=="30"){
		$("#btnUpdate").show();
	}else{
		$("#btnUpdate").hide();
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


//详细信息
function rowView(obj){
	while(obj.tagName.toLowerCase() != "tr"){
	    obj = obj.parentNode;
	    if(obj.tagName.toLowerCase() == "table")return null;
	   }
	obj.click();
	var data = $("#DT1").getSelectedRow();

	$("#resultXML").val(data);
	$(window).manhuaDialog({"title":"环保项目>详细信息","type":"text","content":"${pageContext.request.contextPath }/jsp/business/project/hb-project-add.jsp?type=detail","modal":"2"});

}


function rowSh(obj){
	while(obj.tagName.toLowerCase() != "tr"){
	    obj = obj.parentNode;
	    if(obj.tagName.toLowerCase() == "table")return null;
	   }
	obj.click();
	var data = $("#DT1").getSelectedRow();

	$("#resultXML").val(data);
	$(window).manhuaDialog({"title":"环保项目>审核","type":"text","content":"${pageContext.request.contextPath }/jsp/business/project/hb-project-add.jsp?type=update","modal":"2"});

}


function caozuoFun(obj){
	var status=obj.STATUS;
	var showHtml="";
	if(status=='30'){
		showHtml +="<span class='btn btn-link' id='addSpan' onclick='rowSh(this);' title='审核' >";
		showHtml +="<i class='icon-file'></i> ";
		showHtml +="</span>";
	}else{
		showHtml +="<span class='btn btn-link' id='addSpan' onclick='rowView(this);' title='查看' >";
		showHtml +="<i class='icon-file'></i> ";
		showHtml +="</span>";
	}
	return showHtml;
}

function xmbhFun(obj){
	//var code=obj.PROJECTS_CODE;
	//var name=obj.PROJECTS_NAME;
	//return showHtml="<span title="+code+">"+name+"</span>";
	if($("input[name='status']:checked").val()==10){
		return showHtml = "<a href='javascript:void(0);' title='项目编号:"+obj.PROJECTS_CODE+"'>"+obj.PROJECTS_NAME+"</a>";
	}else{
		return "";
		}
}


function checkStatus(obj){
	 var status=$("input[name='status']:checked").val();
	 $("#QSTATUS").val(status);
	 if(status=='30'){
			// alert(status);
		$("#shrid").hide();			
		$("#shsjid").hide();	
	}else{
		$("#shrid").show();			
		$("#shsjid").show();
	}
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?queryHb",data,DT1);	

}


function getCount(){	
	$.ajax({
		url : controllername+"?getProjectCount&type=H",
	//	data : data1,
		cache : false,
		async : false,
		dataType : "json",
		type : 'post',
		success : function(response) {
			var resultdata = dealSpecialCharactor(response.msg);
			var res = convertJson.string2json1(resultdata);
			//$("h").empty();	
			$("#dsid").empty();
			$("#ysid").empty();
			$("#thid").empty();	
		    var dsh = document.createElement("h");			  
		    dsh.innerHTML = " (<font color=\"red\">"+res.DSH+"</font>)";				
			document.getElementById("dsid").appendChild(dsh);

			var ysh = document.createElement("h");			  
			ysh.innerHTML = " (<font color=\"red\">"+res.YTG+"</font>)";				
			document.getElementById("ysid").appendChild(ysh);

		    var thh = document.createElement("h");			  
		    thh.innerHTML = " (<font color=\"red\">"+res.WTG+"</font>)";				
			document.getElementById("thid").appendChild(thh);	
		}
	});
}

//回车事件
function enterSumbit(){  
    var event=arguments.callee.caller.arguments[0]||window.event;//消除浏览器差异  
   if (event.keyCode == 13){  
	   $("#btnQuery").click();  
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
<%--			<h4 class="title">
				项目
				<span class="pull-right">  
					<button id="btnUpdate" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">审核</button>
      				<button id="btnExpExcel" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">导出</button>
				</span>
			</h4>--%>
			<form method="post" id="queryForm">
				<table class="B-table" width="100%">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<TR style="display: none;">
						<TD class="right-border bottom-border"></TD>
						<TD class="right-border bottom-border">
							<INPUT type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>
<%--							<INPUT type="text" class="span12" kind="text" id="STATUS" fieldname="STATUS" value="40" operation="!="/>--%>
							<INPUT type="text" class="span12" id="QSTATUS" fieldname="STATUS" value='30'  operation="="/>
							<INPUT type="text" class="span12" id=PROJECTS_TYPE fieldname="PROJECTS_TYPE" value='H'  operation="="/>
							
						</TD>
					</TR>
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr>
						<th width="5%" class="right-border bottom-border text-right">公司名称</th>
						<td class="right-border bottom-border" width="20%">
							<input class="span12" type="text" onkeydown="enterSumbit()" id="QCOMPANYNAME" name="COMPANYNAME" fieldname="COMPANYNAME" operation="like" >
						</td>
						<th width="5%" class="right-border bottom-border text-right">项目名称</th>
						<td class="right-border bottom-border" width="20%">
							<input class="span12" type="text" onkeydown="enterSumbit()" id="QPROJECTS_NAME" name="PROJECTS_NAME" fieldname="PROJECTS_NAME" operation="like" >
						</td>
						<th  width="5%" class="right-border bottom-border text-right">审核状态</th>
						<td class="right-border bottom-border" width="25%">
							 <input type=radio value="30" name="status" onclick="javascript:checkStatus(this)" checked="checked"  >待审<h id="dsid"></h>&nbsp;&nbsp;
      						 <input type=radio value="10" name="status" onclick="javascript:checkStatus(this)">已审<h id="ysid"></h>&nbsp;&nbsp;
      						 <input type=radio value="20" name="status" onclick="javascript:checkStatus(this)">退回<h id="thid" ></h>			
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
	                		<th fieldname="PROJECTS_UID" colindex=2 tdalign="center" CustomFunction="caozuoFun" >&nbsp;&nbsp;</th>
							<th fieldname="COMPANYNAME" colindex=2 tdalign="center" >&nbsp;公司名称&nbsp;</th>
							<th fieldname="PROJECTS_NAME" colindex=5 tdalign="center"  maxlength="30"  CustomFunction="xmbhFun">&nbsp;项目名称&nbsp;</th>
							<th fieldname="PROJECTS_LEVEL" colindex=7 tdalign="center" >&nbsp;项目等级&nbsp;</th>
							<th fieldname="PROJECTS_TYPE" colindex=3 tdalign="center" >&nbsp;项目类型&nbsp;</th>							
							<th fieldname="PROJECTS_XINZHI" colindex=6 tdalign="center" maxlength="30" >&nbsp;项目性质&nbsp;</th>
							<th fieldname="PLAN_KG_DATE" colindex=4 tdalign="center" maxlength="30" >&nbsp;拟开工日期&nbsp;</th>	
							<th fieldname="JG_DATE" colindex=4 tdalign="center" maxlength="30" >&nbsp;预计竣工时间&nbsp;</th>							
							<th id="shrid" fieldname="SHENPINAME" colindex=9 tdalign="center" maxlength="30" >&nbsp;审批人&nbsp;</th>
							<th id="shsjid" fieldname="SHENPI_DATE" colindex=10 tdalign="center" >&nbsp;审批日期&nbsp;</th>
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
		<input type="hidden" name="queryXML"/> 
		<input type="hidden" name="txtXML"/>
		<input type="hidden" name="txtFilter" order="desc" fieldname="CREATED_DATE" id="txtFilter"/>
		<input type="hidden" name="txtFilter" order="desc" fieldname="STATUS" id="txtFilter"/>
		<input type="hidden" name="txtFilter" order="desc" fieldname="PROJECTS_TYPE" id="txtFilter"/>
		<input type="hidden" name="txtFilter" order="desc" fieldname="PROJECTS_XINZHI" id="txtFilter"/>
<%--		<input type="hidden" name="txtFilter" order="desc" fieldname="ID" id="txtFilter"/>--%>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>