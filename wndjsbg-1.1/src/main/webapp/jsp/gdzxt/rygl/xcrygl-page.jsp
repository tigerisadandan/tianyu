<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>现场人员</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/rygl/xcryglController";

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

	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#queryForm").clearFormResult();
        $("#btnQuery").click();  
    });
	
});

//页面默认参数
function init(){
	$('#gongcheng_uid').val(parent.document.getElementById("GDZXT_XM_ID").value);
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?query",data,DT1);
//	getCount();

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
	$(window).manhuaDialog({"title":"现场人员管理>人员变更审核","type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/rygl/xcrygl-page-update.jsp","modal":"2"});
}


function caozuoFun(obj){
	var showHtml="";
	showHtml +="<span class='btn btn-link' id='addSpan' onclick='rowSh(this);' title='审核' >";
	showHtml +="<i class='icon-file'></i> ";
	showHtml +="</span>";
	return showHtml;
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
			<form method="post" id="queryForm">
				<table class="B-table" width="100%">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<TR style="display: none;">
						<TD class="right-border bottom-border"></TD>
						<TD class="right-border bottom-border">
							<INPUT type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>
							<INPUT type="text" class="span12" id="gongcheng_uid" fieldname="v.GONGCHENG_UID"  operation="="/>
							<input type="hidden" id="STATUS" fieldname="d.STATUS" name = "STATUS" operation="=" value="0"/>
						</TD>
					</TR>
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr>
						
						<th width="3%" class="right-border bottom-border text-right">姓名</th>
						<td class="right-border bottom-border" width="8%">
							<input class="span12" type="text" onkeydown="enterSumbit()" id="NAME" name="NAME" fieldname="NAME" operation="like" >
						</td>	
						<th width="3%" class="right-border bottom-border text-right">证件号码</th>
						<td class="right-border bottom-border" width="18%">
							<input class="span12" type="text" onkeydown="enterSumbit()" id="SFZ" name="SFZ" fieldname="SFZ" operation="like" >
						</td>	
					
						<td class="text-left bottom-border text-right">
	                        <button id="btnQuery" class="btn btn-link"  type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-search"></i>查询</button>
           					<button id="btnClear" class="btn btn-link" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-trash"></i>清空</button>
           					<!--  <button id="btnAddYj" class="btn" type="button">     批量发卡</button> -->
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
	                		<th fieldname="DT_RY_BIANGENG_UID" colindex=0 tdalign="center"  CustomFunction="caozuoFun" >&nbsp;&nbsp;</th>
							<th fieldname="GANGWEI" colindex=2 tdalign="center" maxlength="30">&nbsp;岗位&nbsp;</th>
							<th fieldname="NAME" colindex=2 tdalign="center" maxlength="30">&nbsp;姓名&nbsp;</th>
							<!--  <th fieldname="FZ" colindex=2 tdalign="center" maxlength="30">&nbsp;分值&nbsp;</th> -->
							<th fieldname="PHOTO" colindex=2 tdalign="center" maxlength="30">&nbsp;照片&nbsp;</th>
							<!--  <th fieldname="XXK" colindex=2 tdalign="center" maxlength="30">&nbsp;信息卡&nbsp;</th> -->
							<th fieldname="DH" colindex=3 tdalign="center" maxlength="30"> &nbsp;联系电话</th>
							<th fieldname="SFZ" colindex=2 tdalign="center" maxlength="30">&nbsp;身份证&nbsp;</th>
							<th fieldname="ZSBH" colindex=2 tdalign="center" maxlength="30" >&nbsp;证书编号&nbsp;</th>
							<th fieldname="ZY" colindex=2 tdalign="center" maxlength="30" >&nbsp;专业&nbsp;</th>
							<th fieldnae="GONGCHENG_UID" hidde></th>
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
<%--		<input type="hidden" name="txtFilter" order="desc" fieldname="ID" id="txtFilter"/>--%>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>
