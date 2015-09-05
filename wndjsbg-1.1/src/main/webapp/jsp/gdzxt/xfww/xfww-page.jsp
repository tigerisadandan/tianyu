<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp" %>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base />
<title>信访维稳管理</title>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/ywlz/css/default.css"> </LINK>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>	
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/kindeditor/plugins/code/prettify.css" />
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/kindeditor/kindeditor.js"></script>
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/kindeditor/lang/zh_CN.js"></script>

<script type="text/javascript" charset="utf-8">
var controllername = "${pageContext.request.contextPath}/xfww/XinFangController";
$(function() {
	init();
	//查询按钮函数
	$("#btnQuery").click(function() {
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"?query",data,DT1);
	});
	
	//清空按钮点击事件
	$("#btnClear").click(function() {
    $("#condition").clearFormResult();
    init();
	});
	
	//添加按钮函数
	$("#btnInsert").click(function() {
		$(window).manhuaDialog({"title":"信访信息登记>新增","type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/xfww/xfww-add.jsp?type=insert","modal":"1"});
	});
});	

function doProQuery(){
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入	
	defaultJson.doQueryJsonList(controllername+"?query",data,DT1);
}

function setDate(){
	var nowdate = new Date();
	var nowy = nowdate.getFullYear();
	var nowm = nowdate.getMonth()+1;
	var nowd = nowdate.getDate();
	
	var bnowm = nowm;
	if(bnowm<10){
		bnowm = "0"+bnowm;
	}
	var bnowd = nowd;
	if(bnowd<10){
		bnowd = "0"+bnowd;
	}
	var datestr = nowy+"-"+bnowm+"-"+bnowd;
	
	var enowm = nowm;
	if(nowm-6<=0){
		enowm = nowm+12-6;
		nowy = nowy-1;
	}else{
		enowm = nowm-6;
	}

	if(enowm<10){
		enowm = "0"+enowm;
	}
	
	var enowd = nowd;
	if(enowd<10){
		enowd = "0"+enowd;
	}
	var datestr2 = nowy+"-"+enowm+"-"+enowd;

	$( "#AFTER_XF_DATE" ).val(datestr2);
	$( "#BEFORE_XF_DATE" ).val(datestr);
}

function setGongCheng_UID(){
	var GongCheng_UID=parent.document.getElementById("GDZXT_XM_ID").value;
	$("#GONGCHENG_UID").val(GongCheng_UID);
}
//查看详细信息
function doRandering(obj){
	var showHtml = "";
	showHtml = "<a href='javascript:rowView(this);' title='操作'><i class='icon-file'></i></a></i>";
	return showHtml;
}

function rowView(index){
	if($("#DT1").getSelectedRowIndex()==-1)
 	{
		requireSelectedOneRow();
    	return
 	}
	 var rowValue = $("#DT1").getSelectedRow();
     var tempJson = convertJson.string2json1(rowValue);
     var XINFANG_UID = tempJson.XINFANG_UID;
	 $(window).manhuaDialog({"title":"信访信息编辑","type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/xfww/xfww-edit.jsp?XINFANG_UID="+XINFANG_UID,"modal":"2"});
}

function init(){
	setGongCheng_UID();
	setDate();
	doProQuery();
}

function formatXFDate(obj){
	var XF_DATE=obj.XF_DATE;
	XF_DATE=XF_DATE.substring(0,11);
	return XF_DATE;
}

function formatZGDate(obj){
	var ZG_DATE=obj.ZG_DATE;
	ZG_DATE=ZG_DATE.substring(0,11);
	return ZG_DATE;
}
</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">  
	<div class="B-small-from-table-autoConcise">
     
	    <form method="post" id="queryForm">
				<table class="B-table" width="100%" id="condition">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr style="display: none;">
						<td class="right-border bottom-border"></TD>
						<td class="right-border bottom-border">
							<input type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>
							<input type="text" class="span12" kind="text" id="GONGCHENG_UID" fieldname="GONGCHENG_UID" operation="=" />
						</td>
					</tr>
					<tr>
						<th width="5%" class="right-border bottom-border text-right">信访人</th>
						<td width="10%">
							 <input class="span12" type="text" id="XF_NAME" fieldname="XF_NAME" check-type="maxlength" maxlength="100" operation="like" >
						</td>
						<th width="5%" class="right-border bottom-border text-right">来源</th>
						<td width="9%" class="right-border bottom-border" style="padding: 5px;">
							<select class="span12" id="SOURCE" name="SOURCE" fieldname="SOURCE" operation="=">
							<option value="">-来源-</option>
							<option value="网上投诉">网上投诉</option>
							<option value="电话投诉">电话投诉</option>
							<option value="现场投诉">现场投诉</option>
							<option value="转办受理">转办受理</option>
							</select>
						</td>
						<th width="8%" class="right-border bottom-border text-right">信访时间段</th>
						<td class="right-border bottom-border" width="30%">
						<input  id="AFTER_XF_DATE" style="width:40%" type="text" fieldtype="date" fieldformat="yyyy-MM-dd" fieldname="XF_DATE" name = "XF_DATE" class='Wdate'  operation=">"  onClick="WdatePicker()"/>~						
						<input  id="BEFORE_XF_DATE" style="width:40%" type="text" fieldtype="date" fieldformat="yyyy-MM-dd" fieldname="XF_DATE" name = "XF_DATE" class='Wdate'  operation="<="  onClick="WdatePicker()"/>
						</td>					
						<td width="14%" class="text-left bottom-border">
	                        <button onclick="doProQuery();" id="btnQuery" class="btn btn-link"  type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-search"></i>查询</button>
           					<button id="btnClear" class="btn btn-link" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-trash"></i>清空</button>         					
			            </td>
			            <td width="20%"><button id="btnInsert" type="button" class="btn" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;float:right;">添加信访人员及信访信息</button></td>				            	
					</tr>
				</table>
			</form>
		   <div style="height:5px;"> </div>	
			<div class="overFlowX">			   
	            <table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single"  pageNum="18">
	                <thead>
	                	<tr>
	                		<th rowspan="2" fieldname="XINFANG_UID" width="4%" tdalign="center" colindex=0 CustomFunction="doRandering" noprint="false">操作</th>
	                		<th id="SOURCE" fieldname="SOURCE" colindex=0 tdalign="center" >&nbsp;来源 &nbsp;</th>
	                		<th id="XF_NAME" fieldname="XF_NAME" colindex=0 tdalign="center" >&nbsp;信访人&nbsp;</th>
							<th id="XF_CONTENT" fieldname="XF_CONTENT" colindex=2 tdalign="center" maxlength="10">&nbsp;信访问题及述求&nbsp;</th>
							<th id="XF_DATE" fieldname="XF_DATE" colindex=0 tdalign="center" CustomFunction="formatXFDate">&nbsp;信访时间 &nbsp;</th>
							<th id="XC_TJYJ" fieldname="XC_TJYJ" colindex=0 tdalign="center" maxlength="10">&nbsp;现场调解情况及处理意见 &nbsp;</th>
							<th id="ZG_DATE" fieldname="ZG_DATE" colindex=0 tdalign="center" CustomFunction="formatZGDate">&nbsp;整改日期 &nbsp;</th>			
	                	</tr>
	                </thead>
	              	<tbody></tbody>
	           </table>
	       </div>
	
    </div>
   </div>
  </div>
   <div align="center">
		<FORM name="frmPost" method="post" style="display:none"
			target="_blank">
			<!--系统保留定义区域-->
			<input type="hidden" name="queryXML" id="queryXML"> 
			<input type="hidden" name="txtXML" id="txtXML">
			<input type="hidden" name="txtFilter" order="desc" fieldname="XF_DATE" id="txtFilter"/>
			<input type="hidden" name="resultXML" id="resultXML">
			<!--传递行数据用的隐藏域-->
			<input type="hidden" name="rowData">
		</FORM>
	</div>
</body>
<script>
</script>
</html>