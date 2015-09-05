<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>施工内容首页</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/gongcheng/projectsGongchengController";

//页面初始化
$(function() {
	init();
	$("#shrid").hide();			
		$("#shsjid").hide();	
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
		defaultJson.doQueryJsonList(controllername+"?query",data,DT1);
	});
	//按钮绑定事件(新增)
	$("#btnInsert").click(function() {
		$(window).manhuaDialog({"title":"施工内容>新增","type":"text","content":"${pageContext.request.contextPath }/jsp/business/gongcheng/projects-gongcheng-add.jsp?type=insert","modal":"1"});
	});
	//按钮绑定事件(修改)
	$("#btnUpdate").click(function() {
		if($("#DT1").getSelectedRowIndex()==-1)
		 {
			requireSelectedOneRow();
		    return
		 }
		$("#resultXML").val($("#DT1").getSelectedRow());
		$(window).manhuaDialog({"title":"施工内容>修改","type":"text","content":"${pageContext.request.contextPath }/jsp/business/gongcheng/projects-gongcheng-add.jsp?type=update","modal":"1"});
	});
	//按钮绑定事件（导出EXCEL）
	$("#btnExpExcel").click(function() {
		 var t = $("#DT1").getTableRows();
		 if(t<=0)
		 {
			 xAlert("提示信息","请至少查询出一条记录！");
			 return;
		 }
	  	 $(window).manhuaDialog({"title":"导出","type":"text","content":"${pageContext.request.contextPath }/jsp/framework/print/TabListEXP.jsp?tabId=DT1","modal":"3"});
	});
	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#queryForm").clearFormResult();
        getNd();
    });
	
});

//页面默认参数
function init(){
$("#shrid").hide();			
		$("#shsjid").hide();	
	getNd();
	getNums(null);
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?query",data,DT1);
}
//默认年度
function getNd(){
	//年度信息，里修改
	$("#ZFRQ").val(new Date().getFullYear());
}

//点击获取行对象
function tr_click(obj,tabListid){
	//alert(JSON.stringify(obj));
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




function shenhe(obj){
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

function rowSh(obj){
	while(obj.tagName.toLowerCase() != "tr"){
	    obj = obj.parentNode;
	    if(obj.tagName.toLowerCase() == "table")return null;
	   }
	obj.click();
	var data = $("#DT1").getSelectedRow();
	$("#resultXML").val(data);
	$(window).manhuaDialog({"title":"施工内容>修改","type":"text","content":"${pageContext.request.contextPath }/jsp/business/gongcheng/projects-gongcheng-add.jsp?type=update","modal":"2"});

}
//详细信息
function rowView(obj){
while(obj.tagName.toLowerCase() != "tr"){
	    obj = obj.parentNode;
	    if(obj.tagName.toLowerCase() == "table")return null;
	   }
	obj.click();
	var data = $("#DT1").getSelectedRow();
	$("#resultXML").val(data);
	$(window).manhuaDialog({"title":"施工内容>修改","type":"text","content":"${pageContext.request.contextPath }/jsp/business/gongcheng/projects-gongcheng-add.jsp?type=detail","modal":"2"});
}

//点击 状态
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
	defaultJson.doQueryJsonList(controllername+"?query",data,DT1);	

}

//状态数量
//获取状态下的数据
function getNums(projectsid){
	$.ajax({
		url :controllername+'?queryStatusNums&projectsid='+projectsid,
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			$("#ytg").text("(0)");
			$("#wtg").text("(0)");
			$("#dsh").text("(0)");
			$("#wtj").text("(0)");
			var arr = eval('('+response.msg+')');
			$(arr).each(function(){
				if(this.status==10){
					$("#ytg").text("("+this.nums+")");
				}else if(this.status==20){
					$("#wtg").text("("+this.nums+")");
				}else if(this.status==30){
					$("#dsh").text("("+this.nums+")");
				}
			})
		}
	});
	
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
							<INPUT type="text" class="span12" id="QSTATUS" fieldname="STATUS" value='30'  operation="="/>
						</TD>
					</TR>
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr>
						<th width="5%" class="right-border bottom-border text-right">工程名称</th>
						<td class="right-border bottom-border" width="20%">
							<input class="span12" type="text" id="GONGCHENG_NAME" name="GONGCHENG_NAME" fieldname="GONGCHENG_NAME" operation="like" >
						</td>
						
					    <th  width="5%" class="right-border bottom-border text-right">审核状态</th>
						<td class="right-border bottom-border" width="25%">
							 <input type=radio value="30" name="status" onclick="javascript:checkStatus(this)" checked="checked"  >待审<font style="color: red"  id="dsh">0</font>&nbsp;&nbsp;
      						 <input type=radio value="10" name="status" onclick="javascript:checkStatus(this)">已审<font style="color: red"  id="ytg">0</font>&nbsp;&nbsp;
      						 <input type=radio value="20" name="status" onclick="javascript:checkStatus(this)">退回<font style="color: red"  id="wtg" >0</font>			
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
	                		<!-- <th fieldname="ZFRQ" colindex=2 tdalign="center" maxlength="10" hasLink="true" linkFunction="rowView">&nbsp;支付日期&nbsp;</th> -->
							<th fieldname="GONGCHENG_UID" style="width:10px" colindex=0 tdalign="center" CustomFunction="shenhe" >&nbsp;&nbsp;</th>
							<!-- 
							<th fieldname="PROJECTS_UID" colindex=1 tdalign="center" >&nbsp;项目分期UID&nbsp;</th> -->
							<!-- <th fieldname="GONGCHENG_CODE" colindex=17 tdalign="center" maxlength="30" >&nbsp;工程代码（9+4+3+N+2）&nbsp;</th>
							<th fieldname="OLD_CODE" colindex=18 tdalign="center" >&nbsp;老系统施工代码&nbsp;</th> -->
							<th fieldname="NEIRONG_SV" colindex=19 tdalign="center" maxlength="30" >&nbsp;工程施工内容&nbsp;</th>
							<!-- <th fieldname="SGBB_UID" colindex=20 tdalign="center" >&nbsp;施工报备UID&nbsp;</th> 
							<th fieldname="JLBB_UID" colindex=21 tdalign="center" maxlength="30" >&nbsp;监理报备UID&nbsp;</th>-->
							<th fieldname="GONGCHENG_NAME" colindex=22 tdalign="center" maxlength="30" >&nbsp;工程名称&nbsp;</th>
							<th fieldname="CB_XINGZHI" colindex=23 tdalign="center" >&nbsp;工程承包性质&nbsp;</th>
							<th fieldname="BID_TYPE" colindex=24 tdalign="center" >&nbsp;发包方式&nbsp;</th>
							<th fieldname="MIANJI" colindex=25 tdalign="center" >&nbsp;建筑面积（平方米）&nbsp;</th><!-- 
							<th fieldname="CENGSHU" colindex=26 tdalign="center" >&nbsp;层数&nbsp;</th>
							<th fieldname="GAODU" colindex=27 tdalign="center" >&nbsp;高度（米）&nbsp;</th>
							<th fieldname="KUADU" colindex=28 tdalign="center" >&nbsp;跨度（米）&nbsp;</th>
							<th fieldname="SHENDU" colindex=29 tdalign="center" >&nbsp;深度（米）&nbsp;</th>
							<th fieldname="JINE" colindex=30 tdalign="center" >&nbsp;合同额（元）&nbsp;</th>
							<th fieldname="ZHONGLIANG" colindex=31 tdalign="center" >&nbsp;重量（吨）&nbsp;</th> -->	
							<th fieldname="UPDATE_DATE" colindex=31 tdalign="center" >&nbsp;提交时间&nbsp;</th> 
							<th id="shrid" fieldname="SHENHE_NAME" colindex=2 tdalign="center"  maxlength="30" >&nbsp;审核人&nbsp;</th>
							<th id="shsjid" fieldname="SHENHE_DATE" colindex=4 tdalign="center" >&nbsp;审核时间&nbsp;</th>
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
		<input type="hidden" name="txtFilter" order="desc" fieldname="UPDATE_DATE" id="txtFilter"/>
<%--		<input type="hidden" name="txtFilter" order="desc" fieldname="ID" id="txtFilter"/>--%>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>