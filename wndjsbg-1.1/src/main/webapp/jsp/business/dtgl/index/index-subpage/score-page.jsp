<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>需审核扣分列表</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/date/moment.min.js"></script>
<%
	String condition = request.getParameter("condition");
%>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/yhzg/scoreController";
var condition="<%=condition%>";
//页面初始化
$(function() {
	init();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {		
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"?queryFenshu&condition="+condition,data,DT1);
	});
	
	//按钮绑定事件（清空）
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
	if(condition=="today"||condition=="week"||condition=="month"){
		setDate();			
	}else if(condition=="dshkf"){		
		$("#top-h4").html("待审核扣分查询");
	}
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?queryFenshu&condition="+condition,data,DT1);
}

function setDate(){
	var datestr1 = "";
	var datestr2 = "";
	if(condition=="today"){	
		datestr1 = moment(new Date()).format("YYYY-MM-DD");
		datestr2 = moment(new Date()).format("YYYY-MM-DD");
	}else if(condition=="week"){
		var xingqi = new Date().getDay();
		if(xingqi==0){
			datestr1 = moment(new Date()).startOf('week').add(-6,'days').format("YYYY-MM-DD");
			datestr2 = moment(new Date()).endOf('week').add(-6,'days').format("YYYY-MM-DD");
		}else{
			datestr1 = moment(new Date()).startOf('week').add(1,'days').format("YYYY-MM-DD");
			datestr2 = moment(new Date()).endOf('week').add(1,'days').format("YYYY-MM-DD");
		}
		
	}else if(condition=="month"){
		datestr1 = moment(new Date()).startOf('month').format("YYYY-MM-DD");
		datestr2 = moment(new Date()).endOf('month').format("YYYY-MM-DD");
	}

	$( "#B_DATE" ).val(datestr1);
	$( "#E_DATE" ).val(datestr2);
	
}

function rowSh(obj){
	while(obj.tagName.toLowerCase() != "tr"){
	    obj = obj.parentNode;
	    if(obj.tagName.toLowerCase() == "table")return null;
	   }
	obj.click();   
	var data = $("#DT1").getSelectedRow();
	var tempJson = convertJson.string2json1(data);
	var url  = "";
	var type = tempJson.TYPE;
	var sUid = tempJson.SCORE_UID;
	if(type=="ZJ"||type=="XMJL"){
		url = "${pageContext.request.contextPath }/jsp/business/dtgl/index/index-subpage/score-detail-geren.jsp?sUid="+sUid;
	}else{
		url = "${pageContext.request.contextPath }/jsp/business/dtgl/index/index-subpage/score-detail-qiye.jsp?sUid="+sUid;
	}
	$(window).manhuaDialog({"title":"隐患整改>全面停工审核","type":"text","content":url,"modal":"2"});
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
<div id="menuiframe"></div>
<div class="container-fluid">
	<h4 id="top-h4">扣分情况查询</h4>
	<div class="row-fluid">
		<div class="B-small-from-table-autoConcise">
			<form method="post" id="queryForm">
				<table class="B-table" width="100%">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<TR style="display: none;">
						<TD class="right-border bottom-border"></TD>
						<TD class="right-border bottom-border">
							<INPUT type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>
							<INPUT type="hidden" id="SH_STATUS"  fieldname="S.SH_STATUS" value="" operation="="/>
						</TD>
					</TR>
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr>
					<th width="3%" class="right-border bottom-border text-right">扣分对象</th>
					<td>
						<input type="text" fieldname="CHULI_DUIXIANG" operation="like"/>
					</td>																									            						
					<th width="3%" class="right-border bottom-border text-right">工程名称</th>
					<td>
						<input type="text"  fieldname="GONGCHENG_NAME" operation="like"/>
					</td>
					<th width="3%" class="right-border bottom-border text-right">日期</th>
					<td class="right-border bottom-border" width="25%" colspan="3">
						<input id="B_DATE" fieldname="CREATE_DATE" fieldformat="yyyy-MM-dd" fieldtype="date" operation=">=" class="Wdate" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:35%"  name="B_DATE" >			  
						~<input id="E_DATE" fieldname="CREATE_DATE" fieldformat="yyyy-MM-dd" fieldtype="date" operation="<=" class="Wdate" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:35%" name="E_DATE"  >
					</td>
					<th width="3%" class="right-border bottom-border text-right">整改单编号</th>
					<td>
						<input type="text"  fieldname="ZG_CODE" operation="="/>
					</td>						
					<td class="text-left" width="15%">
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
	                		<th fieldname="SCORE_UID" colindex=0 tdalign="center"  CustomFunction="caozuoFun" >&nbsp;&nbsp;</th>
							<th fieldname="CHULI_DUIXIANG" colindex=2 tdalign="center" maxlength="30" >&nbsp;扣分对象&nbsp;</th>
							<th fieldname="GONGCHENG_NAME" colindex=2 tdalign="center" maxlength="30">&nbsp;工程名称&nbsp;</th>
							<th fieldname="CHANGE_SCORE" colindex=2 tdalign="center" maxlength="30">&nbsp;扣分&nbsp;</th>
							<th fieldname="YUANYIN" colindex=3 tdalign="center" maxlength="30"> &nbsp;扣分原因&nbsp;</th>
							<th fieldname="CREATE_DATE" colindex=2 tdalign="center" maxlength="30">&nbsp;日期&nbsp;</th>
							<th fieldname="ZG_CODE" colindex=2 tdalign="center" maxlength="30" >&nbsp;整改单编号&nbsp;</th>
							<th fieldname="SCORE_UID" colindex=2 tdalign="center" maxlength="30" >&nbsp;审核&nbsp;</th>
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
