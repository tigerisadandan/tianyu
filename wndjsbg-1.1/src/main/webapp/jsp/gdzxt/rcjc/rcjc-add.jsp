<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>日常检查</title>
<%
	String gcuid = request.getParameter("gcuid");
	String username = request.getParameter("username");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername = "${pageContext.request.contextPath }/rcjc/dtRcjcController";
var controllernameWgsj= "${pageContext.request.contextPath }/yhzg/zgWeiguiSjController";
//页面初始化
$(function() {
	init();
	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
		if($("#rcjcForm").validationButton()){
			$("#saveType").val('save');
		    //生成json串
		    var data = Form2Json.formToJSON(rcjcForm);
		  //组成保存json串格式
		    var data1 = defaultJson.packSaveJson(data);
		  //调用ajax插入
		    if($("#DT_RCJC_UID").val() != "" && $("#DT_RCJC_UID").val() != null){
    			defaultJson.doUpdateJson(controllername + "?update", data1);
    			$(window).manhuaDialog.close();
    		}else{
    			defaultJson.doInsertJson(controllername + "?insert", data1);
    			var rowValue = $("#resultXML").val();
    			var res = dealSpecialCharactor(rowValue);
    			var resultobj = defaultJson.dealResultJson(res);
    			$('#QDT_RCJC_UID').val(resultobj.DT_RCJC_UID);
    			//给表单赋值
    			resultobj = defaultJson.dealResultJson(rowValue);
    			$("#rcjcForm").setFormValues(resultobj);
    		    //$('#shjltxdivid').show();
    		   // $('#zgtpdivid').show();
    		}
		    //var a = $(window).manhuaDialog.getParentObj();
		    //a.setFormValues();
			//$(window).manhuaDialog.close();
		}else{
			requireFormMsg();
		  	return;
		}
	});


	$("#btnUp").click(function() {
		if($("#rcjcForm").validationButton()){
			$("#saveType").val('zgd');
		    //生成json串
		    var data = Form2Json.formToJSON(rcjcForm);
		  //组成保存json串格式
		    var data1 = defaultJson.packSaveJson(data);
		  //调用ajax插入
		    if($("#DT_RCJC_UID").val() != "" && $("#DT_RCJC_UID").val() != null){
    			defaultJson.doUpdateJson(controllername + "?update", data1);
    			$(window).manhuaDialog.close();
    		}else{
    			defaultJson.doInsertJson(controllername + "?insert", data1);
    			var rowValue = $("#resultXML").val();
    			var res = dealSpecialCharactor(rowValue);
    			var resultobj = defaultJson.dealResultJson(res);
    			$('#QDT_RCJC_UID').val(resultobj.DT_RCJC_UID);
    			//给表单赋值
    			resultobj = defaultJson.dealResultJson(rowValue);
    			$("#rcjcForm").setFormValues(resultobj);
    		    //$('#shjltxdivid').show();
    		   // $('#zgtpdivid').show();
    		}
		    //var a = $(window).manhuaDialog.getParentObj();
		    //a.setFormValues();
			//$(window).manhuaDialog.close();
		}else{
			requireFormMsg();
		  	return;
		}
	});
	

	$("#btnClose").bind("click", function(){
		$(window).manhuaDialog.getParentObj().init();
		var a=$(this).manhuaDialog.close();
		
	});	

});
//页面默认参数
function init(){
	//生成json串
	//var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT3);
	//调用ajax插入
	//defaultJson.doQueryJsonList(controllernameContent+"?query",data,DT3);
	//setFormValues();
	$('#JC_DATE').val(getNowDate());

	
}

//查询表单值
function setFormValues(){
	var data = combineQuery.getQueryCombineData(queryForm1,frmPost);
	var data1 = {
		msg : data
	};
	$.ajax({
		url : controllername+"?queryForm",
		data : data1,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			$("#resultXML").val(response.msg);
			var resultobj = defaultJson.dealResultJson(response.msg);
			queryFileData("DT_RCJC",resultobj.DT_RCJC_UID);
			$("#rcjcForm").setFormValues(resultobj);
		}
	});
}

function getNowDate(){
	var nowdate = new Date();
	var nowy = nowdate.getFullYear();
	var nowm = nowdate.getMonth()+1;
	var nowd = nowdate.getDate();
	if(nowm<10){
		nowm = "0"+nowm;
	}
	var datestr = nowy+"/"+nowm+"/"+nowd;
	return datestr;
}



//----添加违规事件begin--------
function addEvent(){
	window.open("wgsj-select.jsp");
}

//回调
function loadWgsj(str){
	$('#QSJ_UID').val("("+str+")");

	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT3);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllernameWgsj+"?queryZgsj",data,DT3);
}

function formatEdit1(obj){
	return "<a href='javascript:void(0)' onclick=\"doEdit1('"+obj.ZG_CONTENT_UID+"')\"  title='查看'><i class='icon-file showXmxxkInfo'></i></a>";
}

function doEdit1(sjuid){
	$(window).manhuaDialog({"title":"隐患整改>整改事件查看","type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/yhzg/yhzg-zgsj-edit.jsp?sjuid="+sjuid,"modal":"2"});
}


function formatWgdj(obj){
	var html = "<input style=\"width:95%;\"  type=\"text\" fieldname = \"WG_DENGJI\" disabled=\"disabled\" value=\""+obj.WG_DENGJI+"\">";
		html +="<input style=\"width:95%;\"  type=\"hidden\" fieldname = \"SJUID\"  value=\""+obj.SJUID+"\">";

	return html ;
}

function formatWglx(obj){

	return "<input style=\"width:95%;\" type=\"text\"  fieldname = \"NAME\" disabled=\"disabled\" value=\""+obj.NAME+"\">";
}


function formatWgms(obj){
	return "<textarea style=\"width:95%;\" rows=\"3\"  fieldname = \"WG_MIAOSHU\" disabled=\"disabled\">"+obj.WG_MIAOSHU+"</textarea>";
}


function formatXxms(obj){
	return "<textarea style=\"width:95%;\" rows=\"3\"  fieldname = \"DESCRIPTION\" >"+obj.DESCRIPTION+"</textarea>";
}

function formatFileUpload(){
	var html = "<div><span class=\"btn btn-fileUpload\" id=\"addFileSpan\" onclick=\"doSelectFile(this,'child');\" ywid=\"ZG_TZD_UID\" target_type=\"2\" file_type=\"11\" business_type=\"ZG_TZD\">";
		html += "  <i class=\"icon-plus\"></i>";
		html += " <span>添加文件</span></span>";
		html += " <table role=\"presentation\" class=\"table table-striped\">";
		html += " <tbody ywid=\"ZG_TZD_UID\" class=\"files showFileTab\" data-toggle=\"modal-gallery\" data-target=\"#modal-gallery\"></tbody>";
		html += " </table></div>" ;
	return html;
}

function selectFile (obj){
	   var targetUid = $('#target_uid').val();
	   var file_type = $(obj).attr('file_type');
			setFileData("DT_RCJC","DT_RCJC_UID",targetUid,file_type);
		document.getElementById("fileupload_btn").click();	
}


</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">
   
	<div style="height:5px;"></div>
	<div class="B-small-from-table-autoConcise">
      <h4 class="title">
      	<span class="pull-right">
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
	  		<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">保存</button>
	  		<button id="btnUp" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">转为整改单</button>
      	</span>
      </h4>
     <form method="post" id="rcjcForm"  >
      <table class="B-table" width="100%" >
     	 <input type="hidden" id="DT_RCJC_UID" fieldname="DT_RCJC_UID" name = "DT_RCJC_UID" />
     	 <input type="hidden" id="GONGCHENG_UID" fieldname="GONGCHENG_UID" name = "GONGCHENG_UID" value="<%=gcuid %>"/>
         <input type="hidden" id="target_uid" fieldname="target_uid"  />
         <input type="hidden" id="saveType" fieldname="saveType"  />
         <tr>
			<th width="8%" class="right-border bottom-border text-right">检查人</th>
       		<td class="bottom-border right-border" width="15%">
         		<input   id="JC_USER" type="text" fieldname="JC_USER" name = "JC_USER"  />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">检查时间</th>
       		<td class="bottom-border right-border" width="15%">
       			<input type="text" readonly="readonly" id="JC_DATE" name="JC_DATE" fieldname="JC_DATE" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy/MM/dd'})" fieldformat="YYYY/MM/DD" fieldtype="date">
         	</td>
         </tr>

      </table>
       <div style="height: 5px;"></div>
      		<div id="shjltxdivid"  >
				<div class="modal-header" style="background:#0866c6;">
				<h3 style="color:white;">违规事件 
				<span class="pull-right">
					<button type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;" onclick="addEvent();">添加</button>
	  			</span>
				</h3>
				</div>
      			<table class="table-hover table-activeTd B-table" width="100%" id="DT3" type="single"  noPage=true >
					<thead>
						<tr>
							<th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
							<th fieldname="SJUID" colindex=1 tdalign="center" CustomFunction="formatEdit1">&nbsp;&nbsp;</th>
		                	<th fieldname="WG_DENGJI" colindex=1 tdalign="center" CustomFunction="formatWgdj">&nbsp;违规等级&nbsp;</th>
							<th fieldname="NAME" colindex=1 tdalign="center" maxlength="30" CustomFunction="formatWglx">&nbsp;违规类型 &nbsp;</th>
							<th fieldname="WG_MIAOSHU" colindex=2 tdalign="center" maxlength="4000" CustomFunction="formatWgms">&nbsp;违规描述 &nbsp;</th>
							<th fieldname="DESCRIPTION" colindex=3 tdalign="center" maxlength="4000" CustomFunction="formatXxms">&nbsp;详细描述&nbsp;</th>
							<!--  <th fieldname="ZG_CONTENT_UID" colindex=6 tdalign="center"  CustomFunction="formatFileUpload" >&nbsp;照片&nbsp;</th>	 -->					
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
			<div style="height: 5px;"></div>
      		<div id="zgtpdivid" >
				<div class="modal-header" style="background:#0866c6;">
				<h3 style="color:white;">整改图片
				<span class="pull-right">
					<span class="btn btn-info  btn-sm" id="addFileSpan3" onclick="selectFile(this);" file_type="3502"> 
						<i class="icon-plus"></i> <span>上传图片</span> 
					</span>
	  			</span>
				</h3>
				</div>
      			<table role="presentation"  class="table table-striped">
					<tbody  id="gccltbody" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" file_type="3502">
					</tbody>
				</table>
			</div>
      </form>
    </div>
   </div>
  </div>
  <form method="post" id="queryForm"  >
    <input type="hidden" id="QSJ_UID" fieldname="ws.zg_weigui_sj_uid"  operation="in"/>
  </form>
  <form method="post" id="queryForm1"  >
    	  <input type="hidden" id="QDT_RCJC_UID" fieldname="t.DT_RCJC_UID"  operation="="/>
  </form>
<%@ include file="/jsp/file_upload/fileupload_util.jsp"%>
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

</html>