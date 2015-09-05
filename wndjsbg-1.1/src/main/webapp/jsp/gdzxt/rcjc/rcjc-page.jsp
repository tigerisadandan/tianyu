<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>日常检查</title>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/rcjc/dtRcjcController";
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
		$(window).manhuaDialog({"title":"日程检查>添加日常检查","type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/rcjc/rcjc-add.jsp?gcuid="+gcuid+"&username="+username,"modal":"2"});
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
function formatEdit(){
	return "<a href='javascript:void(0)' onclick='doEdit(this)'  title='修改'><i class='icon-file showXmxxkInfo'></i></a>";
}

function doEdit(obj){
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
	$(window).manhuaDialog({"title":"日程检查>修改日常检查","type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/rcjc/rcjc-update.jsp","modal":"2"});

}

function formatJcContent(obj){
	var content = "";
	var tzdUid = obj.DT_RCJC_UID;
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
							<input type="hidden" name="DT_RCJC_UID" order="desc" fieldname="t.DT_RCJC_UID" id="DT_RCJC_UID"/>
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
	                		<th fieldname="DT_RCJC_UID" colindex=2 tdalign="left" style="width:10px"  CustomFunction="formatEdit" >&nbsp;&nbsp;</th>
							<th fieldname="JC_USER" colindex=1 tdalign="left" style="width:5px" >&nbsp;检查人&nbsp;</th>
							<th fieldname="JC_DATE" colindex=2 tdalign="left" style="width:100px"  >&nbsp;检查日期&nbsp;</th>
							<th fieldname="DT_RCJC_UID" colindex=6 tdalign="left" style="width:10px" CustomFunction="formatJcContent" >&nbsp;检查内容&nbsp;</th>
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