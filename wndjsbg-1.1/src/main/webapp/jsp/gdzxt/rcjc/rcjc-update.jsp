<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>日常检查修改</title>
<%
	String gcuid = request.getParameter("gcuid");
	String username = request.getParameter("username");
%>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername = "${pageContext.request.contextPath }/rcjc/dtRcjcController";
//页面初始化
$(function() {
	init();
	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
		if($("#rcjcForm").validationButton()){
		    //生成json串
		    var data = Form2Json.formToJSON(rcjcForm);
		  //组成保存json串格式
		    var data1 = defaultJson.packSaveJson(data);
		  //调用ajax插入
		    if($("#DT_RCJC_UID").val() != "" && $("#DT_RCJC_UID").val() != null){
    			defaultJson.doUpdateJson(controllername + "?update", data1);
    		}else{
    			defaultJson.doInsertJson(controllername + "?insert", data1);

    		    //$('#shjltxdivid').show();
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
		var a=$(this).manhuaDialog.close();
	});	

});
//页面默认参数
function init(){
	//生成json串
	//var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT3);
	//调用ajax插入
	//defaultJson.doQueryJsonList(controllername+"?query",data,DT3);
	//setFormValues();
	$('#FAFANG_DATE').val(getNowDate());
	//var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT3);
	//调用ajax插入
	//defaultJson.doQueryJsonList(controllernameContent+"?query",data,DT3);
}

//查询表单值
function setFormValues(){
	var parentmain = $(window).manhuaDialog.getParentObj();
	var rowValue = parentmain.$("#resultXML").val();
	var tempJson = convertJson.string2json1(rowValue);
	$("#QDT_RCJC_UID").val(tempJson.DT_RCJC_UID);
	var data = combineQuery.getQueryCombineData(queryForm,frmPost);
	var data1 = {
		msg : data
	};
	$.ajax({
		url : controllername+"?query",
		data : data1,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			$("#resultXML").val(response.msg);
			resultobj = defaultJson.dealResultJson(response.msg);
			$("#rcjcForm").setFormValues(resultobj);
			//queryFileData(resultobj.ZG_TZD_UID, "", "", "900009");
			//setXXJD(resultobj);
		}
	});
}



function formatEdit1(obj){
	return "<a href='javascript:void(0)' onclick=\"doEdit1('"+obj.ZG_CONTENT_UID+"')\"  title='查看'><i class='icon-file showXmxxkInfo'></i></a>";
}

function doEdit1(sjuid){
	$(window).manhuaDialog({"title":"隐患整改>整改事件查看","type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/yhzg/yhzg-zgsj.jsp?sjuid="+sjuid,"modal":"2"});
}

function formatFileUpload(obj){
	var zgdUid = $('#QZG_TZD_UID').val();
	var zgcontUid = obj.ZG_CONTENT_UID;
	var html = "<div><span class=\"btn btn-fileUpload\" id=\"addFileSpan\" onclick='selectgcalxx("+zgdUid+","+zgcontUid+");'  ywid='"+zgdUid+"' fjlb='"+zgcontUid+"' target_type=\"2\" file_type=\"11\" business_type=\"ZG_TZD\">";
		html += "  <i class=\"icon-plus\"></i>";
		html += " <span>添加文件</span></span>";
		html += " <table role=\"presentation\" class=\"table table-striped\">";
		html += " <tbody ywid='"+zgdUid+"' fjlb='"+zgcontUid+"' class=\"files showFileTab\" data-toggle=\"modal-gallery\" data-target=\"#modal-gallery\"></tbody>";
		html += " </table></div>" ;
	return html;
}


function selectgcalxx(zgdUid,zgcontUid){
	
	setFileData(zgdUid,"","","900009","0",zgcontUid);
	$("#_fileupload_fjlb").val(zgcontUid);
	document.getElementById("fileupload_btn").click();
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




function addEvent(){
	window.open("wgsj-select.jsp");
}

function radioFun(demo){
	if(demo=='2'){
		$('#jbtgwz').show();
	}else{
		$('#jbtgwz').hide();
		$('#JBTGWZ').val("");
	}
}



function selectXmtzFile(obj){
	var ywid = $('#ZG_TZD_UID').val();
	setFileData(ywid,"","","900009","1","7763");
	$("#_fileupload_fjlb").val($(obj).attr("fjlb"));
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
      	</span>
      </h4>
     <form method="post" id="rcjcForm"  >
      <table class="B-table" width="100%" >
     	 <input type="hidden" id="DT_RCJC_UID" fieldname="DT_RCJC_UID" name = "DT_RCJC_UID"/>
     	 <input type="hidden" id="GONGCHENG_UID" fieldname="GONGCHENG_UID" name = "GONGCHENG_UID" value="<%=gcuid %>"/>
        
         <tr>
			<th width="8%" class="right-border bottom-border text-right">检查人</th>
       		<td class="bottom-border right-border" width="15%">
         		<input readonly="readonly"  id="JC_USER" type="text" fieldname="JC_USER" name = "JC_USER"  />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">检查时间</th>
       		<td class="bottom-border right-border" width="15%">
       			<input type="text" readonly="readonly" id="JC_DATE" name="JC_DATE" fieldname="JC_DATE" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy/MM/dd'})" fieldformat="YYYY/MM/DD" fieldtype="date">
         	</td>
         </tr>

      </table>
       <div style="height: 5px;"></div>
      		<div id="shjltxdivid">
				<div class="modal-header" style="background:#0866c6;">
				<h3 style="color:white;">整改事件 
				<span class="pull-right">
					<button type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;" onclick="addEvent();">添加</button>
	  			</span>
				</h3>
				</div>
      			<table class="table-hover table-activeTd B-table" width="100%" id="DT3" type="single"  noPage=true >
					<thead>
						<tr>
							<th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
							<th fieldname="ZG_CONTENT_UID" colindex=1 tdalign="center" CustomFunction="formatEdit1">&nbsp;&nbsp;</th>
		                	<th fieldname="WG_DENGJI" colindex=1 tdalign="center">&nbsp;违规等级&nbsp;</th>
							<th fieldname="NAME" colindex=1 tdalign="center" maxlength="30" >&nbsp;违规类型 &nbsp;</th>
							<th fieldname="WG_MIAOSHU" colindex=2 tdalign="center" maxlength="30" >&nbsp;违规描述 &nbsp;</th>
							<th fieldname="DESCRIPTION" colindex=3 tdalign="center" maxlength="30" >&nbsp;详细描述&nbsp;</th>
							<!--  <th fieldname="ZG_CONTENT_UID" colindex=6 tdalign="center"  CustomFunction="formatFileUpload" >&nbsp;照片&nbsp;</th>	 -->						
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
       <div style="height: 5px;"></div>
      		<div id="zgtpdivid">
				<div class="modal-header" style="background:#0866c6;">
				<h3 style="color:white;">整改图片
				<span class="pull-right">
					<span class="btn btn-info  btn-sm" id="addFileSpan3" onclick="selectXmtzFile(this);" fjlb="7763"> 
						<i class="icon-plus"></i> <span>上传图片</span> 
					</span>
	  			</span>
				</h3>
				</div>
      			<table role="presentation"  class="table table-striped">
					<tbody fjlb="7763" id="gccltbody" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery">
					</tbody>
				</table>
			</div>
      </form>
    </div>
   </div>
  </div>
  <form method="post" id="queryForm"  >
    	  <input type="hidden" id="QDT_RCJC_UID" fieldname="t.dt_rcjc_uid" name = "DT_RCJC_UID" operation="="/>
  </form>
<%@ include file="/jsp/file_upload/fileupload_config_ajax_gd.jsp"%>
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