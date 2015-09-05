<!DOCTYPE html>
<%@page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext"%>
<%@page import="com.ccthanking.framework.common.User" %>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<%
	User user = RestContext.getCurrentUser();
 %>
<app:base/>
<title>外出登记首页</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/nbgl/waiChuDengJiController.do";

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
		$(window).manhuaDialog({"title":"外出登记>新增","type":"text","content":"${pageContext.request.contextPath }/jsp/business/nbgl/waichudengji-add.jsp?type=insert","modal":"2"});
	});
	//外出所有记录按钮
	$("#btnAllWaiChu").click(function(){
		$(window).manhuaDialog({"title":"所有外出记录","type":"text","content":"${pageContext.request.contextPath }/jsp/business/nbgl/waichudengji-view.jsp","modal":"2"});
	})
	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#queryForm").clearFormResult();
    });
	
});

//页面默认参数
function init(){
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?query",data,DT1);
	//查询条件默认为当前时间
	$("#WAICHU_SHIJIAN").val(CurentTime());
}

//点击获取行对象
function tr_click(obj,tabListid){
	//alert(JSON.stringify(obj));
}


//操作按钮
function caozuoFun(obj){
	
}
//详细信息
function rowView(index){
	$("#DT1").setSelect(index);
	if($("#DT1").getSelectedRowIndex()==-1)
	 {
		requireSelectedOneRow();
	    return
	 }
	$("#resultXML").val($("#DT1").getSelectedRow());
	$(window).manhuaDialog({"title":"外出登记>详细信息","type":"text","content":"${pageContext.request.contextPath }/jsp/business/nbgl/waichudengji-add.jsp?type=detail","modal":"1"});
}

	function operationFormat(obj){
		var showHtml = "";
		var status = obj.WAICHU_STATUS;
		if(status == 1){
			showHtml = "<a href='javascript:void(0)' onclick='returnAlert("+obj.WAICHU_DENGJI_UID+")'>返回</a>";
		}else{
			showHtml = "已返回";
		}
		return showHtml;
	}
	function returnAlert(id){
		xConfirm("信息确认","确定此人已返回？");
		$('#ConfirmYesButton').attr('click','');
		$('#ConfirmYesButton').one("click",function(){
			//点击了确定
			$.ajax({
				url:controllername+"?updateState",
				type:"post",
				data :{"wcdjId":id},
				dataType : "json",
				success : function(reponse){
					$("#btnQuery").click();
				}
			});
		});
	}
//查看详细信息
function doRandering(obj){
	var showHtml = "";
	showHtml = "<a href='javascript:rowView1(this);' title='操作'><i class='icon-file'></i></a></i>";
	return showHtml;
}
function rowView1(t){	
	if($("#DT1").getSelectedRowIndex()==-1)
	 	{
			requireSelectedOneRow();
	    	return
	 	}
		 var rowValue = $("#DT1").getSelectedRow();
	     var tempJson = convertJson.string2json1(rowValue);
	     var wcdjId = tempJson.WAICHU_DENGJI_UID;
	     $(window).manhuaDialog({"title":"外出记录编辑","type":"text","content":"${pageContext.request.contextPath }/jsp/business/nbgl/waichudengji-edit.jsp?wcdjId="+wcdjId,"modal":"2"});  
}
function CurentTime()
    { 
        var now = new Date();
       
        var year = now.getFullYear();       //年
        var month = now.getMonth() + 1;     //月
        var day = now.getDate();            //日
       
        var hh = now.getHours();            //时
        var mm = now.getMinutes();          //分
        var ss = now.getSeconds();			//秒
       
        var clock = year + "-";
       
        if(month < 10)
            clock += "0";
       
        clock += month + "-";
       
        if(day < 10)
            clock += "0";
           
        clock += day;
        
        return clock; 
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
				本人外出记录
				<span class="pull-right"> 
					<button id="btnAllWaiChu" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">所有外出记录</button>
      				<button id="btnInsert" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">外出登记</button>
				</span>
			</h4>
			<form method="post" id="queryForm">
				<table class="B-table" width="100%">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<TR style="display: none;">
						<TD class="right-border bottom-border"></TD>
						<TD class="right-border bottom-border">
							<INPUT type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<=" />
							<INPUT type="text" class="span12" kind="text" id="WAICHU_RENYUAN_UID" fieldname="WAICHU_RENYUAN_UID" value="<%= user.getUserSN() %>" operation="=" />
						</TD>
					</TR>
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr>
						<th width="5%" class="right-border bottom-border text-right">外出时间</th>
						<td class="right-border bottom-border" width="20%">
							<input  id="WAICHU_SHIJIAN" type="text" fieldtype="date" fieldformat="yyyy-MM-dd" fieldname="WAICHU_SHIJIAN" name = "WAICHU_SHIJIAN" class='Wdate'  operation="="  onClick="WdatePicker()"/>
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
	            <table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single" pageNum="18">
	                <thead>
	                	<tr>
	                		<th rowspan="2" fieldname="WAICHU_DENGJI_UID" width="4%" tdalign="center" colindex=0 CustomFunction="doRandering" noprint="true">操作</th>
	                		<th fieldname="WAICHU_DIDIAN" colindex=2 tdalign="center" maxlength="10">&nbsp;外出地点&nbsp;</th>
							<th fieldname="WAICHU_NEIRONG" colindex=3 tdalign="center" >&nbsp;外出事宜&nbsp;</th>
							<th fieldname="WAICHU_SHIJIAN" colindex=4 tdalign="center" maxlength="30" >&nbsp;外出时间&nbsp;</th>
							<th fieldname="FANHUI_SHIJIAN" colindex=5 tdalign="center" >&nbsp;返回时间&nbsp;</th>
							<th fieldname="WAICHU_STATUS" colindex=6 tdalign="center" CustomFunction="operationFormat" maxlength="30" >&nbsp;操作&nbsp;</th>
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
		<input type="hidden" name="txtFilter" order="desc" fieldname="WAICHU_SHIJIAN" id="txtFilter"/>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>