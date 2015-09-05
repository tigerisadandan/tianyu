<%@page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext"%>
<%@page import="com.ccthanking.framework.model.User"%>
<%@page import="com.ccthanking.framework.Globals"%><!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>

<app:base/>
<title>人员信息首页</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/sgenter/sgEnterPriseLibraryController/";
var zt = 30;
function setPageHeight(){
	var height = g_iHeight-pageTopHeight-pageTitle-pageQuery-getTableTh(4)-pageNumHeight;
	var pageNum = parseInt(height/pageTableOne,10);
	$("#DT1").attr("pageNum",pageNum);
}
//页面初始化
$(function() {
	init();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {

		zt = $("input:radio[name='RYSHZT']:checked").val();
		if(zt=="30"){
			$("#shsj").attr("fieldname","");
			$("#shsj").hide();
		}else{
			$("#shsj").attr("fieldname","SHENHE_DATE");
			$("#shsj").show();
		}
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"query?ZT="+zt,data,DT1,null,true);
		 var rows = $("tbody tr" ,$("#DT1"));
			if(rows.size()==0){
				$("tbody" ,$("#DT1")).append("<tr><td colspan=\"11\" style=\"height: 1px;\" align=\"center\">暂无记录</td></tr>");
			}
	});
	
	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#queryForm").clearFormResult(); 
        $("input:radio[name='RYSHZT']")[3].checked = true;
        clickRadioShowDate();
    });

    //按钮绑定事件(新增)
	$("#btnInsert").click(function() {
		 
		 window.open("${pageContext.request.contextPath }/sgperson","人员添加");
	    
	});

});
function doRandering(obj){
	var showHtml = "";
	showHtml = "<a href='javascript:rowView1(this);' title='查看详细信息'>"+COMPANY_NAME+"</a>";
	return showHtml;
}
function rowView(id,status){	
	if(status==30){
		copyCompanyInfo(id);
	}else{
 		//location.href="${pageContext.request.contextPath }/sgmessage/"+obj.SG_ENTERPRISE_LIBRARY_UID+".do";
		window.open("${pageContext.request.contextPath }/sgentview/"+id,"企业信息审批");//传递一个ID给详细页面，用于查找 
	}
	  
}

//页面默认参数
function init(){
	$("input:radio[name='RYSHZT']")[3].checked = true;//默认选中这个这个值“30”
	clickRadioShowDate();
	
	$("input:radio[name='RYSHZT']").each(function(){
		$(this).attr("onchange","clickRadioShowDate()");
	})
}
//单选按钮触发事件 
function clickRadioShowDate(){

	zt = $("input:radio[name='RYSHZT']:checked").val();
	if(zt=="30"){
		$("#shsj").attr("fieldname","");
		$("#shsj").hide();
		$("#btutuihui").show();
	}else{
		$("#shsj").attr("fieldname","SHENHE_DATE");
		$("#shsj").show();
		$("#btutuihui").hide();
	}
	
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
    defaultJson.doQueryJsonList(controllername+"query?ZT="+zt,data,DT1,null,true);//,null,true无记录时不弹出提示 
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
		defaultJson.doInsertJson(controllername + "insert", data1,DT1);
	}else{
		defaultJson.doUpdateJson(controllername + "update", data1,DT1);
	}
	
};
function EnterPress(){
	if(event.keyCode == 13){
		$("#btnQuery").click();
	}
}
//关闭子窗口，父窗口自动刷新，且父窗口内的信息保持原先状态。
 /*function closeParentCloseFunction(){
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
}*/
function doReload(){
	$("#btnQuery").click();
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
			window.open("${pageContext.request.contextPath }/sgentedit/"+id+"/"+uid,"企业信息审批");
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

function doAllSelect(demo){
	var flag = true;
	if(!$(demo).checked){
		flag = false;
	}	
	$("input:checkbox[name='doSelect']").each(function(){
		$(this).checked = flag;
	})
}
//全选\反选
function doAllSelect(demo){
	var flag = $(demo).prop("checked");
	$("input:checkbox[name='doSelect']").each(function(i){
		if(i>=0){
			$(this).prop("checked",flag);
		}
	})
}
//给每个多选框附上id值
function doMoreChoose(obj){
	return "<input type='checkbox' id='doSelect' name='doSelect' value='"+obj.SG_ENTERPRISE_LIBRARY_UID+"'/>";
}
function delSelectID(liyou){
	if(confirm("确认退回选中施工企业?")){
	 $.ajax({
		  //  url : controllername+"tuihui?ids="+$("#RENYUAN_ID").val()+"&yijian="+liyou,//$("#YIJIAN").val(),
			url : controllername+"tuihui?ids="+ids+"&yijian="+liyou,
			data : {},
			cache : false,
			async :	false,
			dataType : "json",  
			type : 'get',
			success : function(response) {
				clickRadioShowDate();
			}
	});
	}
}
var ids = "";
function plsh(){
	//$.each($("#DT1").find("input:checkbox[name='doSelect']"),function(){
	$.each($("#DT1").find("input:checkbox[name='doSelect']"),function(i){
		if($("#DT1").find("input:checkbox[name='doSelect']")[i].checked){
			var id=$(this).val();
			ids+=id+",";
		}
	})
	if(ids==""){
		alert("请选择施工企业!");
	}else {$(window).manhuaDialog({"title":"退回","type":"text","content":"${pageContext.request.contextPath }/jsp/business/person_manager/because.jsp","modal":"3"});
	}
}
</script>
</head>
<body onkeydown="EnterPress()">
<app:dialogs/>
<div class="container-fluid">
	<p></p>
	<div class="row-fluid">
		<div class="B-small-from-table-autoConcise">
			<form method="post" id="queryForm">
			<table class="B-table" width="100%">
					<!--可以再此处加入hidden域作为过滤条件 -->
				<TR style="display: none;">
					<TD class="right-border bottom-border">
						<INPUT type="text" class="span12" kind="text" id="STATUS" name="STATUS" fieldname="STATUS" value="" operation="="/>
					</TD>
				</TR>
				<!--可以再此处加入hidden域作为过滤条件 -->
			     <tr>
         	      	<th width="5%" class="right-border bottom-border text-right">审核状态</th>
			      	<td class="right-border bottom-border" width="15%">&nbsp;
				  		<span>
				  			<input class="span12" id="RYSHZT" type="radio" placeholder="" kind="dic" src="SHZT" operation="=" name="RYSHZT" onchange="clickRadioShowDate()" fieldname="n.STATUS">
				   		</span>
			     	</td>
			     	<th width="5%" class="right-border bottom-border text-right">企业名称</th>
			      	<td class="right-border bottom-border" width="20%">
				  		<input class="span12" id="COMPANY_NAME" type="text"  src="COMPANY_NAME" operation="like" name="COMPANY_NAME" fieldname="n.COMPANY_NAME">
			     	</td>
			     	<td class="right-border bottom-border text-right" width="20%" rowspan="2" style="background-image: none; background-position: initial initial; background-repeat: initial initial;">
			     	    <button id="btnQuery" class="btn btn-link"  type="button"><i class="icon-search"></i>查询</button>
	                 	<button id="btnClear" class="btn btn-link" type="button"><i class="icon-trash"></i>清空</button>
	                 	<button onclick="plsh()" id="btutuihui" class="btn btn-link"  type="button">退回</button>
           	 		</td>
			   	</tr>
			</table>
			</form>
			<div style="height:5px;"> </div>	
			<div class="overFlowX">
			 
	            <table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single"  pageNum="12">
	              <thead>
	                <tr>
	                	<th name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
	                	<th fieldname="COMPANY_NAME" tdalign="center" colindex=2 CustomFunction="doCpname" noprint="true">&nbsp;&nbsp;</th>
	                	<th fieldname="SG_ENTERPRISE_LIBRARY_UID" tdalign="center" colindex=2 CustomFunction="doMoreChoose" >
	                	<label class="checkbox inline" >
		                        <input type="checkbox" onclick="doAllSelect(this)" name="allSelect"/>
		                        </label>
	                	</th>
						<th fieldname="COMPANY_NAME" colindex=4 tdalign="left" >&nbsp;施工企业名称&nbsp;</th>
                        <th fieldname="ZHIZHAO" colindex=3 tdalign="center" >&nbsp;营业执照注册号&nbsp;</th>
                        <th fieldname="SHUIWU" colindex=3 tdalign="center" >&nbsp;税务登记号&nbsp;</th>
                        <th fieldname="ZHENGSHU_CODE" colindex=3 tdalign="center" >&nbsp;资质证书编号&nbsp;</th>
						<th fieldname="SG_ZIZHI_UID" colindex=7  tdalign="center" CustomFunction="doZizhi">&nbsp;主项资质&nbsp;</th>
						<th fieldname="WAIDI_Y" colindex=5 tdalign="center"  CustomFunction="doDy">&nbsp;地域&nbsp;</th>
						<th fieldname="COMPANY_TYPE" colindex=5 tdalign="center"  >&nbsp;经济类型&nbsp;</th>
						<th fieldname="TIJIAO_DATE" colindex=6 tdalign="center"  >&nbsp;提交时间&nbsp;</th>
						<th id="shsj" fieldname="SHENHE_DATE" colindex=9 tdalign="left" >&nbsp;审核时间&nbsp;</th>
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
		<input type="hidden" name="txtFilter" order="desc" fieldname="n.TIJIAO_DATE" id="txtFilter"/>
<%--		<input type="hidden" name="txtFilter" order="desc" fieldname="ID" id="txtFilter"/>--%>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>