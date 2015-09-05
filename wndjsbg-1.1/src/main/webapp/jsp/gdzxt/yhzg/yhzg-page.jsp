<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>隐患整改</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/yhzg/zgTzdController";
var controllernameContent= "${pageContext.request.contextPath }/yhzg/zgContentController";
//页面初始化
$(function() {
	init();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		//调用ajax插入
		//defaultJson.doQueryJsonList(controllername+"?query",data,DT1);
	});
	//按钮绑定事件(新增)
	$("#btnInsert").click(function() {
		var gcuid = $('#GONGCHENG_UID').val();
		var username = $('#loginUserName').val();
		$(window).manhuaDialog({"title":"隐患整改>添加整改单 ","type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/yhzg/yhzg-add.jsp?gcuid="+gcuid+"&username="+username,"modal":"2"});
	});
	
	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#queryForm").clearFormResult();
        $("#btnQuery").click();
    });
	
});

//页面默认参数
function init(){
	$('#GONGCHENG_UID').val(parent.document.getElementById("GDZXT_XM_ID").value);
	$('#loginUserName').val(parent.document.getElementById("loginUserName").value);
	$('#sgname').text(parent.document.getElementById("sg_company_name").value);
	$('#pname').text(parent.document.getElementById("projects_name").value);
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?query",data,DT1);
	
}

//--------表格中操作begin----------
//修改
function formatEdit(obj){
	return "<a href='javascript:void(0)' onclick='doEdit("+obj.ZG_TZD_UID+")'  title='修改'><i class='icon-file showXmxxkInfo'></i></a>";
}

function doEdit(tzdUid){

	$(window).manhuaDialog({"title":"隐患整改>修改整改单","type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/yhzg/yhzg-update.jsp?zgdid="+tzdUid,"modal":"2"});

}

//打印
function formatPrint(obj){
	var tzdUid = obj.ZG_TZD_UID;
	return "<a href='javascript:void(0)' onclick='doPrint("+tzdUid+")'  title='打印整改单'><i class='icon-print'></i></a>";
}

function doPrint(tzdUid){
	
	window.open("${pageContext.request.contextPath}/yhzg/zgTzdController?printZgtzd&tzduid="+tzdUid,"打印整改单");
}

//复工通知单
function formatFugong(obj){
	var html = "";
	var tzdUid = obj.ZG_TZD_UID ;
	if(tzdUid != ""){
		html = "<a href='javascript:void(0)' onclick='doFugong("+tzdUid+")'  title='打印复工通知单'><i class='icon-print'></i></a>";
	}
	return html;
}

function doFugong(id){
	window.open("${pageContext.request.contextPath}/yhzg/zgDafuController?printFgtzd&tzduid="+id,"打印复工通知单");
}

//答复
function formatDafu(obj){
	var html = "";
	var dafuUid = obj.ZG_DAFU_UID ;
	if(dafuUid != ""){
		html = "<a href='javascript:void(0)' onclick='doDafu(this)'  title='答复情况'><i class='icon-envelope'></i></a>";
	}
	return html;
}

function doDafu(obj){
	while(obj.tagName.toLowerCase() != "tr"){
		obj = obj.parentNode;
		if(obj.tagName.toLowerCase() == "table")return null;
	}
	obj.click();
	if($("#DT1").getSelectedRowIndex()==-1){
		requireSelectedOneRow();
	    return
	 }
	$("#resultXML").val($("#DT1").getSelectedRow());
	$(window).manhuaDialog({"title":"隐患整改>整改答复情况","type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/yhzg/yhzg-dafu-list.jsp","modal":"2"});

}

function formatZgContent(obj){
	var content = "";
	var tzdUid = obj.ZG_TZD_UID;
	$.ajax({
		url : controllernameContent+"?getContent&tzdUid="+tzdUid,
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var obj1 = eval('('+response.msg+')');
			var v = obj1.CONTENT;
			if(v!=undefined){
				content = "<p>"+v+"</p>";
			}else{
				content = "<p></p>";
			}
		}
	});

	return content;	
}


function formatZgPicture(obj){
	var content = "";
	var tzdUid = obj.ZG_TZD_UID;
	$.ajax({
		url : controllernameContent+"?getPicNum&tzdUid="+tzdUid,
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var v = response.msg;
			if(v!=0){
				content = "<p><a href=''>"+v+"</a></p>";
			}else{
				content = "<p>"+v+"</p>";
			}
		}
	});

	return content;	
}

//--------表格中操作end--------------


</script>
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
	<p></p>
	<div class="row-fluid">
		<div class="B-small-from-table-autoConcise">

			<form method="post" id="queryForm">
				<table class="B-table" width="100%">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<TR style="display: none;">
						<TD class="right-border bottom-border"></TD>
						<TD class="right-border bottom-border">
							<INPUT type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>
							<INPUT type="text" class="span12" kind="text" id="GONGCHENG_UID" fieldname="GONGCHENG_UID" operation="="/>
							<INPUT type="text" class="span12" kind="text" id="loginUserName"  />
							<input type="hidden" name="ZG_TZD_UID" order="desc" fieldname="t.ZG_TZD_UID" id="ZG_TZD_UID"/>
						</TD>
					</TR>
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr>
						<td class="text-left bottom-border">
							<h4 >
						     	施工单位:<span id="sgname"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						     	项目名称:<span id="pname"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						    
						      </h4>
						
						</td>
			            <td class="text-left bottom-border">
			            <!--  
	                        <button id="btnQuery" class="btn btn-link"  type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-search"></i>查询</button>
           					<button id="btnClear" class="btn btn-link" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-trash"></i>清空</button>
			         	 -->	
			         		<button id="btnInsert" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">新增</button>
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
	                		<th fieldname="ZG_TZD_UID" colindex=2 tdalign="center" style="width:10px"  CustomFunction="formatEdit" >&nbsp;&nbsp;</th>
							<th fieldname="ZG_TZD_UID" colindex=1 tdalign="center" style="width:5px" CustomFunction="formatPrint">&nbsp;整改单&nbsp;</th>
							<th fieldname="ZG_TZD_UID" colindex=2 tdalign="center" style="width:100px"  CustomFunction="formatFugong">&nbsp;复工通知书&nbsp;</th>
							<th fieldname="ZG_DAFU_UID" colindex=3 tdalign="center" style="width:140px"  CustomFunction="formatDafu">&nbsp;答复情况&nbsp;</th>
							<th fieldname="ZGZT" colindex=4 tdalign="center" style="width:140px"  >&nbsp;整改状态&nbsp;</th>
							<th fieldname="ZGXZ" colindex=6 tdalign="center" style="width:10px" >&nbsp;整改性质&nbsp;</th>
							<th fieldname="XMJL_KOUFEN" colindex=6 tdalign="center" style="width:10px" >&nbsp;项目经理扣分&nbsp;</th>
							<th fieldname="JL_KOUFEN" colindex=6 tdalign="center" style="width:10px" >&nbsp;总监扣分&nbsp;</th>
							<th fieldname="ZG_TZD_UID" colindex=6 tdalign="left" style="width:10px" CustomFunction="formatZgContent" >&nbsp;整改内容&nbsp;</th>
							<th fieldname="ZG_TZD_UID" colindex=6 tdalign="center" style="width:10px" CustomFunction="formatZgPicture" >&nbsp;整改图片&nbsp;</th>
							<th fieldname="FAFANG_DATE" colindex=6 tdalign="center" style="width:10px" >&nbsp;发放时间&nbsp;</th>
							<th fieldname="ZG_DATE" colindex=6 tdalign="center" style="width:10px" >&nbsp;要求整改<br>完成时间&nbsp;</th>
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
		
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>