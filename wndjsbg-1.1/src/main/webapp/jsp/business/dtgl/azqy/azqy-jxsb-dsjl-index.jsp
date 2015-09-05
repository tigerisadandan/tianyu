<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<%
String JXSB_UID=request.getParameter("JXSB_UID");
 %>
<app:base/>
<title>建设单位首页</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/jxsb/jxsbDsjjController";

//页面初始化
$(function() {
	init();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"?query",data,DT1);
	//	getCount();

	});
	
	$("#btnClose").bind("click", function(){
		var a=$(this).manhuaDialog.close();
	});
	//按钮绑定事件(修改)
	$("#btnUpdate").click(function() {
		if($("#DT1").getSelectedRowIndex()==-1)
		 {
			requireSelectedOneRow();
		    return
		 }
		$("#resultXML").val($("#DT1").getSelectedRow());
		$(window).manhuaDialog({"title":"建设单位>审核","type":"text","content":"${pageContext.request.contextPath }/jsp/business/project/js-company-add.jsp?type=update","modal":"2"});
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
        $("#JXSB_UID").val(<%=JXSB_UID %>);
        $("#btnQuery").click();  
    });
     $("#yqQuery").click(function() {
       window.open("${pageContext.request.contextPath }/jsp/business/sgbb/sgbb-view.jsp?id="+uid);
    });
	
});

//页面默认参数
function init(){
	$("#shrid").hide();			
	$("#shsjid").hide();
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?query",data,DT1);
//	getCount();

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



function rowSh(obj){
	while(obj.tagName.toLowerCase() != "tr"){
	    obj = obj.parentNode;
	    if(obj.tagName.toLowerCase() == "table")return null;
	   }
	obj.click();
	var data = $("#DT1").getSelectedRow();

	$("#resultXML").val(data);
	$(window).manhuaDialog({"title":"设备顶升加节附墙记录","type":"text","content":"${pageContext.request.contextPath }/jsp/business/dtgl/azqy/azqy-jxsb-dsjl-index-view.jsp?type=update","modal":"2"});
}


function caozuoFun(obj){
	var status=obj.STATUS;
	var showHtml="";
	showHtml +="<span class='btn btn-link' id='addSpan' onclick='rowSh(this);' title='查看' >";
	showHtml +="<i class='icon-file'></i> ";
	showHtml +="</span>";
	return showHtml;
}

function nameFun(obj){
	var showHtml="";
if(obj.ENABLED==0){
		showHtml +=obj.COMPANY_NAME+"<span style='color:red;' >";
		showHtml +="(已停用)";
		showHtml +="</span>";
		}else{
		showHtml=obj.COMPANY_NAME;
		}
	return showHtml;
}


function checkStatus(obj){
	$("#btnQuery").click();
}



function getCount(){	
	$.ajax({
		url : controllername+"?getCompanyCount",
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
			<h4 class="title">
				设备顶升加节附墙记录
				<span class="pull-right">  
      				<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
				</span>
			</h4>
			<form method="post" id="queryForm">
				<table class="B-table" width="100%">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<TR style="display: none;">
						<TD class="right-border bottom-border"></TD>
						<TD class="right-border bottom-border">
							<INPUT type="text" class="span12" id="JXSB_UID" fieldname="JXSB_UID" value="<%=JXSB_UID %>"  operation="="/>
							
						</TD>
					</TR>
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr>
						<th width="5%" class="right-border bottom-border text-right">项目名称</th>
						<td class="right-border bottom-border" width="15%">
							<input class="span12" type="text" onkeydown="enterSumbit()" id="GC_NAME" name="GC_NAME" fieldname="GC_NAME" operation="like" >
						</td>
						<th width="5%" class="right-border bottom-border text-right">使用登记编号</th>
						<td class="right-border bottom-border" width="15%">
							<input class="span12" type="text" onkeydown="enterSumbit()" id="SYDJ_BH" name="SYDJ_BH" fieldname="SYDJ_BH" operation="like" >
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
	                		<th fieldname="JXSB_DSJJ_UID" colindex=0 tdalign="center"  CustomFunction="caozuoFun" >&nbsp;&nbsp;</th>
							<th fieldname="GC_NAME" colindex=2 tdalign="center" maxlength="30" >&nbsp;项目名称&nbsp;</th>
							<th fieldname="HEIGHT" colindex=2 tdalign="center" maxlength="30" >&nbsp;安装初始高度&nbsp;</th>
							<th fieldname=NEW_HEIGHT colindex=3 tdalign="center" maxlength="30" >&nbsp;本次顶升高度&nbsp;</th>
							<th fieldname=DS_DATE colindex=3 tdalign="center" maxlength="30" >&nbsp;顶升日期&nbsp;</th>
							<th fieldname=FQGD colindex=3 tdalign="center" maxlength="30" >&nbsp;本次附墙高度&nbsp;</th>
							<th fieldname=SYDJ_BH colindex=3 tdalign="center" maxlength="30" >&nbsp;使用登记编号&nbsp;</th>
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
		<input type="hidden" name="txtFilter" order="desc" fieldname="SHENHE_JIEGUO" id="txtFilter"/>
		<input type="hidden" name="txtFilter" order="desc" fieldname="CREATED_DATE" id="txtFilter_sj"/>
<%--		<input type="hidden" name="txtFilter" order="desc" fieldname="ID" id="txtFilter"/>--%>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>