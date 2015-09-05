<%@page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext"%>
<%@page import="com.ccthanking.framework.model.User"%>
<%@page import="com.ccthanking.framework.Globals"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="net.sf.json.JSONObject"%>
<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<%
	Map map = new HashMap();
	map.put("zt","");
	JSONObject jsonObject = JSONObject.fromObject(map); 
	String jsonMap = jsonObject.toString();
	String jsonStr = (new sun.misc.BASE64Encoder()).encode( jsonMap.getBytes()); 
	
%>
<app:base/>
<title>人员信息首页</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/sgenter/sgEnterPriseLibraryController/";
var jsonStr = "<%=jsonStr%>";
//页面初始化
$(function() {
	init();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {

		
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"query",data,DT1,null,true);
		 var rows = $("tbody tr" ,$("#DT1"));
			if(rows.size()==0){
				$("tbody" ,$("#DT1")).append("<tr><td colspan=\"11\" style=\"height: 1px;\" align=\"center\">暂无记录</td></tr>");
			}
	});
	$("#btnExpExcel").click(function() {
	  	 $(window).manhuaDialog({"title":"导出","type":"text","content":"/wndjsbg/jsp/framework/print/TabListEXP.jsp?tabId=DT1","modal":"3"});
	});
	
	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#queryForm").clearFormResult(); 
        $("#STATUS").val(1);
        //closeParentCloseFunction();
        clickRadioShowDate();
    });

    //按钮绑定事件(新增)
	$("#btnInsert").click(function() {
		 
		 window.open("${pageContext.request.contextPath }/sgperson.do","人员添加");
	    
	});

});
function doRandering(obj){
	var showHtml = "";
	showHtml = "<a href='javascript:rowView1(this);' title='查看详细信息'>"+COMPANY_NAME+"</a>";
	return showHtml;
}
function rowView(id){	
	window.open("${pageContext.request.contextPath }/sgenteditrk/"+id,"企业信息审批");//传递一个ID给详细页面，用于查找 
}

//页面默认参数
function init(){
	clickRadioShowDate();
}
//单选按钮触发事件 
function clickRadioShowDate(){

	
	
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
    defaultJson.doQueryJsonList(controllername+"query",data,DT1,null,true);//,null,true无记录时不弹出提示 
    //处理IE浏览器在查询无记录时出现的滚动条问题
    var rows = $("tbody tr" ,$("#DT1"));
	if(rows.size()==0){
		$("tbody" ,$("#DT1")).append("<tr><td colspan=\"11\" style=\"height: 1px;\" align=\"center\">暂无记录</td></tr>");
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
function doQuery(){
	$("#btnQuery").click();
}
function EnterPress(){
	if(event.keyCode == 13){
		$("#btnQuery").click();
	}
}
//关闭子窗口，父窗口自动刷新，且父窗口内的信息保持原先状态。 
function closeParentCloseFunction(){
	    var index =	$("#DT1").getSelectedRowIndex();
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		var tempJson = convertJson.string2json1(data);
		var a=$("#DT1").getCurrentpagenum();
		tempJson.pages.currentpagenum=a;
		data = JSON.stringify(tempJson);
		defaultJson.doQueryJsonList(controllername+"query",data,DT1,null,true);
		$("#DT1").cancleSelected();
		$("#DT1").setSelect(index);
		//处理IE浏览器在查询无记录时出现的滚动条问题
		  var rows = $("tbody tr" ,$("#DT1"));
			if(rows.size()==0){
				$("tbody" ,$("#DT1")).append("<tr><td colspan=\"11\" style=\"height: 1px;\" align=\"center\">暂无记录</td></tr>");
			}
}

function doZizhi(obj){
	if(obj.ZIZHI_NAME==""){return '<div style="text-align:center">—</div>';}
	var showHtml = obj.ZIZHI_NAME;
	if(obj.DENGJI_NAME!=""){
		showHtml += "<br/>"+obj.DENGJI_NAME;
	}
	return showHtml;
}
function doZengZizhi(obj){
	if(obj.ZENG_ZIZHI==""){return '<div style="text-align:center">—</div>';}
	var showHtml = "";
	var arr = obj.ZENG_ZIZHI.split(",");
	for(i=0;i<arr.length;i++){
		showHtml += arr[i]+"<br/>";
	}
	return showHtml;
}
function doZT(obj){
	var status = obj.STATUS;
	if(status=="30"){
		return '<span class="label label-info">'+obj.STATUS_SV+'</span>';
	}else if(status=="1"){
	 	return '<span class="label label-success">'+obj.STATUS_SV+'</span>';
	}else if(status=="20"){
	 	return '<span class="label label-warning">'+obj.STATUS_SV+'</span>';
	}
}
function copyCompanyInfo(id){
	$.ajax({
		url : controllername+"updateCopyByStatus",
		data : {"uid":id,"status":15,"reason":""},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var uid = response.msg;
			window.open("${pageContext.request.contextPath }/sgentedit/"+id+"/"+uid+".do","企业信息审批");
		}
	})
}
function doDy(obj){
	if(obj.WAIDI_Y=="Y"){
		return "外地";
	}else if(obj.WAIDI_Y=="N"){
		return "本地";
	}
}
function doCpname(obj){
	return "<a href='javascript:void(0)' onclick='rowView("+obj.SG_ENTERPRISE_LIBRARY_UID+","+obj.STATUS+")'title='查看详细信息'><i class='icon-file showXmxxkInfo'></i></a>";
}
function EnterPress(){
	if(event.keyCode == 13){
		$("#btnQuery").click();
	}
}
function doDm(obj){
	return "<a href='javascript:void(0)' title='登陆代码:"+obj.DENGLU_CODE+"' >"+obj.COMPANY_NAME+"</a>";
}
function doScore(obj){
	
	if(obj.QUEREN_STATUS==""){
		if(obj.CO==""){
			return "  ";
		}else{
			return "<a href='javascript:void(0)' onclick='doQueren("+obj.SG_ENTERPRISE_LIBRARY_UID+")' title='未确认奖项和项目信息,点击确认' ><i class='icon-warning-sign showXmxxkInfo'></i></a><a href='javascript:void(0)' onclick='showScoreView("+obj.SG_SCORE_UID+")'>"+obj.SCORE+"</a>";
		}
	}else{
		return "<a href='javascript:void(0)' title='已确认奖项和项目信息' ><i class='icon-ok showXmxxkInfo'></i></a><a href='javascript:void(0)' onclick='showScoreView("+obj.SG_SCORE_UID+")'>"+obj.SCORE+"</a>";
	}
}
function doQueren(id){
	window.open("${pageContext.request.contextPath }/sgenteditqueren/"+id,"企业信息审批");//传递一个ID给详细页面，用于查找 
}
function showScoreView(id){
	window.open("${pageContext.request.contextPath }/sgentscore/"+id,"企业信用分数查看");
}
function doCh(obj){
	if($(obj).find("option:selected").text()=="未确认"){
		$(obj).attr("operation","!=");
	}else{
		$(obj).attr("operation","=");
	}
}

function doExecl(tabId){
	   $(window).manhuaDialog({"title":"导出","type":"text","content":"/wndjsbg/jsp/framework/print/TabListEXP.jsp?tabId="+tabId+"&dao=sgEnterPriseLibraryDaoImpl&jsonStr="+jsonStr,"modal":"3"});
}
</script>
</head>
<body onkeydown="EnterPress()">
<app:dialogs/>
<div class="container-fluid">
<p></p>
	<div class="row-fluid">
		<div class="B-small-from-table-autoConcise">
<%--			<h4 class="title">--%>
<%--				<span class="pull-right">--%>
<%--					<button id="btnExpExcel" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">导出</button>--%>
<%--				</span>--%>
<%--			</h4>--%>
			<form method="post" id="queryForm">
				<table class="B-table" width="100%">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<TR style="display: none;">
						<TD class="right-border bottom-border">
						    <INPUT type="text" class="span12" kind="text" id="STATUS" name="STATUS" fieldname="n.STATUS" value="1" operation="="/>
						</TD>
					</TR>
				<!--可以再此处加入hidden域作为过滤条件 -->
			     <tr>
			      <td class="right-border bottom-border" width="3%">
				  <select class="span12" id="WAIDI_Y" operation="=" name="WAIDI_Y" fieldname="n.WAIDI_Y" >
				  	<option value="">-地域-</option>
				  	<option value="N">本地</option>
				  	<option value="Y">外地</option>
				  </select>
			     </td>
			     <th width="1%" class="right-border bottom-border text-right">企业名称</th>
			      <td class="right-border bottom-border" width="10%">
				  <input class="span12" id="COMPANY_NAME" type="text"  src="COMPANY_NAME" operation="like" name="COMPANY_NAME" fieldname="n.COMPANY_NAME">
			     </td>
			     <th width="1%" class="right-border bottom-border text-right">是否确认</th>
			      <td class="right-border bottom-border" width="5%">
				   <select class="span12" id="QUEREN_STATUS" onchange="doCh(this)" operation="=" name="QUEREN_STATUS" fieldname="n.QUEREN_STATUS" >
				  	<option value="">-请选择-</option>
				  	<option value="10">已确认</option>
				  	<option value="10">未确认</option>
				  </select>
			     </td>
			     <td class="right-border bottom-border text-right" width="10%" rowspan="2" style="background-image: none; background-position: initial initial; background-repeat: initial initial;">
	          		<button id="btnQuery" class="btn btn-link"  type="button"><i class="icon-search"></i>查询</button>
	         		<button id="btnClear" class="btn btn-link" type="button"><i class="icon-trash"></i>清空</button>
           	   	</td>
			   </tr>
			   </table>
			</form>
			<div style="height:5px;"> </div>	
			<div class="overFlowX">
			 
	            <table width="100%" print="true" class="table-hover table-activeTd B-table" id="DT1" type="single"  pageNum="12">
	              <thead>
	                <tr>
	                	<th name="XH" id="_XH" style="width:10px" colindex=1 noprint="true" tdalign="center">&nbsp;#&nbsp;</th>
						<th fieldname="COMPANY_NAME" tdalign="center" colindex=2 noprint="true" CustomFunction="doCpname" >&nbsp;&nbsp;</th>
						<th fieldname="COMPANY_NAME" colindex=3 tdalign="left" CustomFunction="doDm">&nbsp;施工企业名称&nbsp;</th>
						<th fieldname="COMPANY_TYPE" colindex=4 tdalign="center"  CustomFunction="doScore">&nbsp;信用分数&nbsp;</th>
                        <th fieldname="ZHIZHAO" colindex=5 tdalign="center" >&nbsp;营业执照注册号&nbsp;</th>
                        <th fieldname="SHUIWU" colindex=6 tdalign="center" >&nbsp;税务登记号&nbsp;</th>
                        <th fieldname="ZHENGSHU_CODE" colindex=7 tdalign="center" >&nbsp;资质证书编号&nbsp;</th>
						<th fieldname="SG_ZIZHI_UID" colindex=8  tdalign="center" CustomFunction="doZizhi">&nbsp;主项资质&nbsp;</th>
						<th fieldname="SCORE" colindex=10 tdalign="center"  CustomFunction="doDy">&nbsp;地域&nbsp;</th>
						<th fieldname="TIJIAO_DATE" colindex=11 tdalign="center"  >&nbsp;提交时间&nbsp;</th>
						<th fieldname="SHENHE_DATE" colindex=12 tdalign="center" >&nbsp;审核时间&nbsp;</th>
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
		<input type="hidden" name="txtFilter" order="desc" fieldname="n.CREATED_DATE" id="txtFilter"/>
<%--		<input type="hidden" name="txtFilter" order="desc" fieldname="ID" id="txtFilter"/>--%>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>