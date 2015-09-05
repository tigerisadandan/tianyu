<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>个人信用评分</title>
<%
	String type=request.getParameter("type");
	String gcuid = request.getParameter("gcuid");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername = "${pageContext.request.contextPath }/yhzg/zgTzdController";
var controllernameXmjd = "${pageContext.request.contextPath }/yhzg/zgXmjdController";
var controllernameWgsj= "${pageContext.request.contextPath }/yhzg/zgWeiguiSjController";
var controllernameContent= "${pageContext.request.contextPath }/yhzg/zgContentController";
var type ="<%=type%>";
//页面初始化
$(function() {
	init();
	//按钮绑定事件(保存)
	/*
	$("#btnSave").click(function() {
		if($("#yxGcTypeForm").validationButton()){
		    //生成json串
		    var data = Form2Json.formToJSON(yxGcTypeForm);
		  //组成保存json串格式
		    var data1 = defaultJson.packSaveJson(data);
		  //调用ajax插入
		    if($("#GC_TYPE_UID").val() != "" && $("#GC_TYPE_UID").val() != null){
    			defaultJson.doUpdateJson(controllername + "?update", data1);
    		}else{
    			defaultJson.doInsertJson(controllername + "?insert", data1);
    		}
		    var a=$(window).manhuaDialog.getParentObj();
		    a.init();
			$(window).manhuaDialog.close();
		}else{
			requireFormMsg();
		  	return;
		}
	});
	*/
	$("#btnClose").bind("click", function(){
		window.close();
	});	

});
//页面默认参数
function init(){
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT3);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllernameContent+"?query",data,DT3);
	$('#FAFANG_DATE').val(getNowDate());
}

function getNowDate(){
	var nowdate = new Date();
	var nowy = nowdate.getFullYear();
	var nowm = nowdate.getMonth()+1;
	var nowd = nowdate.getDate();
	if(nowm<10){
		nowm = "0"+nowm;
	}
	var datestr = nowy+"-"+nowm+"-"+nowd;
	return datestr;
}

function selectFun(demo){
	
	if(demo=='zt'){
		$('#XXJDT').show();
		$('#XXJD2').hide();
	}else{
		$('#XXJDT').hide();
		$('#XXJD2').show();
		var obj=$("#XXJD2");
		obj.attr("src","T#zg_xmjd: NAME as c:TITLE as t:PARENT ='"+demo+"'ORDER BY ID ");
		obj.attr("kind","dic");
		obj.html('');
		reloadSelectTableDic(obj);	
	}

}

function selectFun2(demo){
	if(demo!='wqzs'){
		$('#XXJD3').hide();
	}else{
		$('#XXJD3').show();
		var obj=$("#XXJD3");
		obj.attr("src","T#zg_xmjd: NAME as c:TITLE as t:PARENT ='"+demo+"'ORDER BY ID ");
		obj.attr("kind","dic");
		obj.html('');
		reloadSelectTableDic(obj);				
	}

}

function addEvent(){
	var tzdUid = $('#ZG_TZD_UID').val();
	alert("tzdUid1=="+tzdUid);
	if(tzdUid==""){
		$.ajax({
			url : controllername + "?queryUid",
			dataType : "json",
			type : 'post',
			success : function(response) {
				$('#ZG_TZD_UID').val(response.msg);				
			}
		});
	}

	window.open("wgsj-select.jsp");
}

function radioFun(demo){
	if(demo=='2'){
		$('#jbtgwz').show();
	}else{
		$('#jbtgwz').hide();
	}
}


</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">
	<div style="height:5px;"></div>
	<div class="B-small-from-table-autoConcise">
     <form method="post" id="yxGcTypeForm"  >
      <table class="B-table" width="100%" >
     	 <input type="hidden" id="ZG_TZD_UID" fieldname="ZG_TZD_UID" name = "ZG_TZD_UID"/>
     	 <input type="hidden" id="GONGCHENG_UID" fieldname="GONGCHENG_UID" name = "GONGCHENG_UID" value="<%=gcuid %>"/>
        <div class="modal-header" style="background:#0866c6;">
			<h3 style="color:white;">个人信用评分 
				<span class="pull-right">
					<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
	  			</span>
	  			</h3>
			</div>
         <tr>
			<th width="8%" class="right-border bottom-border text-right">姓名</th>
       		<td class="bottom-border right-border" width="15%">
         		<input readonly="readonly"  id="ZG_CODE" type="text" fieldname="ZG_CODE" name = "ZG_CODE"  />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">身份证号</th>
       		<td class="bottom-border right-border" width="15%">
       			<input type="text" readonly="readonly" id="KOU_JS" name="KOU_JS" fieldname="KOU_JS">
       			
         	</td>
         </tr>

        <tr>
			<th width="8%" class="right-border bottom-border text-right">专业</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="KOU_SG"   type="text" fieldname="KOU_SG" name = "KOU_SG" readonly="readonly"/>
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">资质证书编号</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="KOU_JL"   type="text" fieldname="KOU_JL" name = "KOU_JL"  readonly="readonly"/>
         	</td>
         </tr>
        <tr>
			<th width="8%" class="right-border bottom-border text-right">信用分值</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="FAFANG_DATE"   type="text" fieldname="FAFANG_DATE" name = "FAFANG_DATE"  />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">当前状态</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="ZG_DATE"   type="text" fieldname="ZG_DATE" name = "ZG_DATE"   />
         	</td>
         </tr>
      </table>
       <div style="height: 5px;"></div>
      		<div id="shjltxdivid" >
				<div class="modal-header" style="background:#0866c6;">
				<h3 style="color:white;">扣分记录 
				</h3>
				</div>    
  
			<form method="post" id="queryForm">
				<table class="B-table" width="100%">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<TR style="display: none;">
						<TD class="right-border bottom-border"></TD>
						<TD class="right-border bottom-border">
							<INPUT type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>
							<INPUT type="hidden" class="span12"   fieldname="e.status" operation="=" value="1"/>
							<INPUT type="hidden" class="span12"   fieldname="se.status" operation="=" value="1"/>
							<INPUT type="hidden" class="span12"   fieldname="tzd.ZG_XINGZHI_UID" operation="=" value="3"/>
						</TD>
					</TR>
					<!--可以再此处加入hidden域作为过滤条件 -->
					<%-- 
					<tr>
						<td class="right-border bottom-border" width="15%">
							<input type="radio" name="zt" checked="checked" value="0" onclick="doQuery();">所有项目
							<input type="radio" name="zt" value="1" onclick="doQuery();">本项目 
						</td>
						<td class="right-border bottom-border" width="16%">
							<input type="radio" name="lx" value="1" onclick="doQuery();" checked="checked"> 所有 
							<input type="radio" name="lx" value="1" onclick="doQuery();"> 违规违章 
							<input type="radio" name="lx" value="1" onclick="doQuery();"> 优秀表彰
						</td>	
						<td class="right-border bottom-border" width="16%">
							<input type="radio" name="rq" value="1" onclick="doQuery();" checked="checked"> 今年记录 
							<input type="radio" name="rq" value="1" onclick="doQuery();"> 历年记录 
						</td>	
						
					</tr>
					--%>
				</table>
			</form>
      			<table class="table-hover table-activeTd B-table" width="100%" id="DT3" type="single"  noPage=true >
					<thead>
						<tr>    
							<th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
		                	<th fieldname="WG_DENGJI" colindex=1 tdalign="center">&nbsp;项目名称&nbsp;</th>
							<th fieldname="NAME" colindex=1 tdalign="center" maxlength="30" >&nbsp;分数/限制 &nbsp;</th>
							<th fieldname="WEIGUI_CONTENT" colindex=2 tdalign="center" maxlength="30" >&nbsp;原因 &nbsp;</th>
							<th fieldname="DESCRIPTION" colindex=3 tdalign="center" maxlength="30" >&nbsp;评定事件&nbsp;</th>
							<th fieldname="JLDW_SCORE" colindex=6 tdalign="center"  >&nbsp;日期 &nbsp;</th>						
							<th fieldname="JLDW_SCORE" colindex=6 tdalign="center"  >&nbsp;操作 &nbsp;</th>						
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
      </form>
    </div>
   </div>
  </div>
  <form method="post" id="queryForm"  >
    	  <input type="hidden" id="ZG_TZD_UID" fieldname="t.zg_tzd_uid" name = "ZG_TZD_UID" operation="="/>
  </form>
  
  <div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id = "queryXML">
         <input type="hidden" name="txtXML" id = "txtXML">
         <input type="hidden" name="resultXML" id = "resultXML">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
		
 	</FORM>
 </div>
</body>
<script type="text/javascript" charset="utf-8">
function loadWgsj(str){
	var tzdUid = $('#ZG_TZD_UID').val();
	$('#ZG_WEIGUI_SJ_UID').val(str);
	var gongchenguid = $('#GONGCHENG_UID').val();
	var data = "{'tzdUid':'"+tzdUid+"','wgsjUidstr':'"+str+"','gongcheng_uid':'"+gongchenguid+"'}";
	//var data = Form2Json.formToJSON(yxGcTypeForm);
	var data1 = defaultJson.packSaveJson(data);
	$.ajax({
		url : controllernameContent + "?insert",
		data:data1,
		dataType : "json",
		type : 'post',
		success : function(response) {
		init();		
		}
	});
}
</script>
</html>