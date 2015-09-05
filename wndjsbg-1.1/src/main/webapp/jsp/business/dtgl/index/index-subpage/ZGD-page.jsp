<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="net.sf.json.JSONObject"%>
<app:base/>
<title>隐患整改</title>
<%
	String condition = request.getParameter("condition");
	String zhenggai = request.getParameter("zhenggai");
	String dept = request.getParameter("dept");
	String week = request.getParameter("week");
	String year = request.getParameter("year");
	String month = request.getParameter("month");
	String whichone = request.getParameter("whichone");
	
	
	User user = RestContext.getCurrentUser();
	String userUid = user.getUserSN();
	Map map = new HashMap();
	map.put("condition2",condition);
	map.put("userUid", userUid);
	JSONObject jsonObject = JSONObject.fromObject(map); 
	String jsonMap = jsonObject.toString();
	String jsonStr = (new sun.misc.BASE64Encoder()).encode( jsonMap.getBytes()); 
	
%>
<app:base/>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<style type="text/css">
	.top-h4{
		margin-top: 20px;
		margin-bottom: 10px;
	}
	
</style>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/yhzg/zgTzdController";
var controllernameContent= "${pageContext.request.contextPath }/yhzg/zgContentController";
var condition="<%=condition%>";
var zhenggai = "<%=zhenggai%>";
var deptUid = "<%=dept%>";
var week = "<%=week%>";
var year = "<%=year%>";
var month = "<%=month%>";
var whichone = "<%=whichone%>";
var jsonStr ="<%=jsonStr%>";
//页面初始化
$(function() {	

	init();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {
		//生成json串
		if(condition=="chart"){
			//生成json串 
			var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
			//调用ajax插入
			defaultJson.doQueryJsonList(controllername+"?queryZGD2&zhenggai="+zhenggai+"&whichone="+whichone+"&deptUid="+deptUid+"&week="+week+"&year="+year+"&month="+month,data,DT1);	
		}else{
			var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
			//调用ajax插入
			defaultJson.doQueryJsonList(controllername+"?queryZGD&condition="+condition,data,DT1);
		}
		
	});
	
	
	//按钮绑定事件(清空)
    $("#btnClear").click(function() {
        $("#queryForm").clearFormResult();
        $("#btnQuery").click();
    });
	
});

//页面默认参数
function init(){
	if(condition=="none"||condition==null||condition==""){
		return;
	}
	if(condition!="none"&&condition!=null&&condition!="chart"){		
		if(condition=="today-update"){
			$("#topTitle").html("今日更新整改单查询");			
		}else if(condition=="today-update-shiti"){
			$("#topTitle").html("今日更新实体整改单查询");
		}else if(condition=="today-update-kaoqing"){
			$("#topTitle").html("今日更新考勤整改单查询");
		}else if(condition=="yesterday-update"){
			$("#topTitle").html("昨日更新整改单查询");
		}else if(condition=="yesterday-update-shiti"){
			$("#topTitle").html("昨日更新实体整改单查询");
		}else if(condition=="yesterday-update-kaoqing"){
			$("#topTitle").html("昨日更新实体整改单查询");
		}else if(condition=="today-create"){
			$("#topTitle").html("今日开具整改单查询");
		}else if(condition=="today-create-shiti"){
			$("#topTitle").html("今日开具实体整改单查询");
		}else if(condition=="today-create-kaoqing"){
			$("#topTitle").html("今日开具考勤整改单查询");
		}else if(condition=="jbtg"){
			$("#topTitle").html("今日开具局部停工整改单查询");
		}else if(condition=="qmtg"){
			$("#topTitle").html("今日开具全面停工整改单查询");
		}else if(condition=="qmtg-shiti"){
			$("#topTitle").html("今日开具全面停工实体整改单查询");
		}else if(condition=="qmtg-kaoqing"){
			$("#topTitle").html("今日开具全面停工考勤整改查询");
		}else if(condition=="weibihe"){
			$("#topTitle").html("未闭合整改单查询");
		}else if(condition=="weibihe-shiti"){
			$("#topTitle").html("未闭合实体整改单查询");
		}else if(condition=="weibihe-kaoqing"){
			$("#topTitle").html("未闭合考勤整改单查询");
		}else if(condition=="jjcswdf"){
			$("#topTitle").html("即将超时未答复整改单查询");
		}else if(condition=="jjcswdf-shiti"){
			$("#topTitle").html("即将超时未答复实体整改单查询");
		}else if(condition=="jjcswdf-kaoqing"){
			$("#topTitle").html("即将超时未答复考勤整改单查询");
		}else if(condition=="cswdf"){
			$("#topTitle").html("超时未答复整改单查询");
		}else if(condition=="cswdf-shiti"){
			$("#topTitle").html("超时未答复实体整改单查询");
		}else if(condition=="cswdf-kaoqing"){
			$("#topTitle").html("超时未答复考勤整改单查询");				
		}else if(condition=="dshqmtg"){
			$("#topTitle").html("待审核全面停工整改单查询");				
		}else if(condition=="dshjbtg"){
			$("#topTitle").html("待审核局部停工整改单查询");				
		}else if(condition=="dshfg"){
			$("#topTitle").html("待审核复工整改单查询");
		}		
		//生成json串 
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"?queryZGD&condition="+condition,data,DT1);				
	}else if(condition=="chart"){
		var zhenggai = "<%=zhenggai%>";
		var deptUid = "<%=dept%>";
		var week = "<%=week%>";
		var year = "<%=year%>";
		var month = "<%=month%>";
		var whichone = "<%=whichone%>";
		var ORG_NAME = "";

		var zg = "";
		if(zhenggai=="STZG"){
			zg = "现场整改";
		}else if(zhenggai=="KQZG"){
			zg = "考勤整改";
		}
		
		$.ajax({
			url : controllername+"?getDeptName101&deptUid="+deptUid,
			cache : false,
			async :	false,
			dataType : "json",  
			type : 'post',
			success : function(response) {
				var resultObj = defaultJson.dealResultJson(response.msg);
				ORG_NAME = resultObj.ORG_NAME;	
			}
		});

		if(whichone=="1"){
			$("#topTitle").html(ORG_NAME+"今年第"+week+"周更新"+zg+"单查询");			
		}else if(whichone=="2"){
			$("#topTitle").html(ORG_NAME+"第"+year+"年"+month+"月更新"+zg+"单查询");
		}
		
		//生成json串 
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"?queryZGD2&zhenggai="+zhenggai+"&whichone="+whichone+"&deptUid="+deptUid+"&week="+week+"&year="+year+"&month="+month,data,DT1);		
	
	}
		
}

//--------表格中操作begin----------
//修改
function formatEdit(obj){
	return "<a href='javascript:void(0)' onclick='doEdit("+obj.ZG_TZD_UID+")'  title='修改'><i class='icon-file showXmxxkInfo'></i></a>";
}

function doEdit(tzdUid){
	$(window).manhuaDialog({"title":"隐患整改>修改整改单","type":"text","content":"${pageContext.request.contextPath }/jsp/business/dtgl/index/index-subpage/ZGD-detail.jsp?zgdid="+tzdUid,"modal":"2"});
}

//打印
function formatPrint(obj){
	var tzdUid = obj.ZG_TZD_UID;
	return "<a href='javascript:void(0)' onclick='doPrint("+tzdUid+")'  title='打印整改单'><i class='icon-print'></i></a>";
}

function doPrint(tzdUid){	
	window.open("${pageContext.request.contextPath}/yhzg/zgTzdController?printZgtzd&tzduid="+tzdUid,"打印整改单");
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

//导出Excel
function doExecl(){
  $(window).manhuaDialog({"title":"导出","type":"text","content":"/wndjsbg/jsp/framework/print/TableListEXP.jsp?tabId=DT1&dao=zgTzdDaoImpl&jsonStr="+jsonStr,"modal":"3"});
}
	   

//--------表格中操作end--------------


</script>
</head>
<body>
<app:dialogs/>
<div id="menuiframe"></div>
<div class="container-fluid" class="main-container">
	<div class="top-h4">
	<h4 id="topTitle" class="topTitle"></h4>
	</div>
	<div class="row-fluid">
		<div class="B-small-from-table-autoConcise">

			<form method="post" id="queryForm">
				<table class="B-table" width="100%">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr style="display: none;">
						<td class="right-border bottom-border"></td>
						<td class="right-border bottom-border">
							<input type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>
							<!-- 
								<input type="text" class="span12" kind="text" id="GONGCHENG_UID" fieldname="GONGCHENG_UID" operation="="/>
								<input type="text" class="span12" kind="text" id="loginUserName"  />
							 -->							
							<input type="hidden" name="ZG_TZD_UID" order="desc" fieldname="t.ZG_TZD_UID" id="ZG_TZD_UID"/>
						</td>
					</tr>
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr>
						<td width="8%" class="right-border bottom-border" style="padding: 5px;">
							<select class="span12" id="ZG_XINGZHI_UID" name="ZG_XINGZHI_UID" fieldname="ZG_XINGZHI_UID" operation="=">
							<option value="">-整改性质-</option>
							<option value="1">限期整改</option>
							<option value="2">局部停工整改</option>
							<option value="3">全面停工整改</option>
							</select>
						</td>
						<th width="5%" class="right-border bottom-border text-right">整改内容</th>
						<td width="12%">
							 <input class="span12" type="text" id="WG_MIAOSHU" fieldname="WG_MIAOSHU" check-type="maxlength" maxlength="100" operation="like" >
						</td>					
						<th width="5%" class="right-border bottom-border text-right">发放时间段</th>
						<td class="right-border bottom-border" width="20%">
						<input  id="FAFANG_DATE" style="width:40%" type="text" fieldtype="date" fieldformat="yyyy-MM-dd" fieldname="FAFANG_DATE" name = "FAFANG_DATE" class='Wdate'  operation=">"  onClick="WdatePicker()"/>~						
						<input  id="FAFANG_DATE" style="width:40%" type="text" fieldtype="date" fieldformat="yyyy-MM-dd" fieldname="FAFANG_DATE" name = "FAFANG_DATE" class='Wdate'  operation="<="  onClick="WdatePicker()"/>
						</td>
						<th width="5%" class="right-border bottom-border text-right">要求整改时间段</th>
						<td class="right-border bottom-border" width="20%">
						<input  id="ZG_DATE" style="width:40%" type="text" fieldtype="date" fieldformat="yyyy-MM-dd" fieldname="t.ZG_DATE" name = "ZG_DATE" class='Wdate'  operation=">"  onClick="WdatePicker()"/>~						
						<input  id="ZG_DATE" style="width:40%" type="text" fieldtype="date" fieldformat="yyyy-MM-dd" fieldname="t.ZG_DATE" name = "ZG_DATE" class='Wdate'  operation="<="  onClick="WdatePicker()"/>
						</td>
			            <td class="text-right bottom-border" width="20%">
	                        <button id="btnQuery" class="btn btn-link"  type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-search"></i>查询</button>
           					<button id="btnClear" class="btn btn-link" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-trash"></i>清空</button>
			            </td>							
					</tr>
				</table>
			</form>
			<div style="height:5px;"> </div>	
			<div class="overFlowX" id="needData">
	            <table width="100%" print="true" class="table-hover table-activeTd B-table" action="TableListEXP.jsp" id="DT1" type="single"  pageNum="18">
	                <thead>
	                	<tr>
	                		<th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
	                		<th fieldname="ZG_TZD_UID" colindex=2 tdalign="center" style="width:10px"  CustomFunction="formatEdit" >&nbsp;&nbsp;</th>
							<th fieldname="ZG_TZD_UID" colindex=1 tdalign="center" style="width:5px" CustomFunction="formatPrint">&nbsp;整改单&nbsp;</th>
							<th fieldname="ZGXZ" colindex=6 tdalign="center" style="width:10px" >&nbsp;整改性质&nbsp;</th>
							<th fieldname="XMJL_KOUFEN" colindex=6 tdalign="center" style="width:10px" >&nbsp;项目经理扣分&nbsp;</th>
							<th fieldname="JL_KOUFEN" colindex=6 tdalign="center" style="width:10px" >&nbsp;总监扣分&nbsp;</th>
							<th fieldname="ZG_TZD_CONTENT" colindex=6 tdalign="left" style="width:10px">&nbsp;整改内容&nbsp;</th>
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