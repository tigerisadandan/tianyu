<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>隐患整改</title>
<%
	String gcuid = request.getParameter("gcuid");
	String username = request.getParameter("username");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername = "${pageContext.request.contextPath }/yhzg/zgTzdController";
var controllernameXmjd = "${pageContext.request.contextPath }/yhzg/zgXmjdController";
var controllernameWgsj= "${pageContext.request.contextPath }/yhzg/zgWeiguiSjController";
var controllernameContent= "${pageContext.request.contextPath }/yhzg/zgContentController";
//页面初始化
$(function() {
	init();
	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
		if($("#zgtzdForm").validationButton()){
		    //生成json串
		    var data = Form2Json.formToJSON(dfForm);
		  //组成保存json串格式
		    var data1 = defaultJson.packSaveJson(data);
		  //调用ajax插入
		    if($("#ZG_TZD_UID").val() != "" && $("#ZG_TZD_UID").val() != null){
			   
    			defaultJson.doUpdateJson(controllername + "?update", data1);
    		}else{
    			defaultJson.doInsertJson(controllername + "?insert", data1);
    			var rowValue = $("#resultXML").val();
    			var res = dealSpecialCharactor(rowValue);
    			var resultobj = defaultJson.dealResultJson(res);
    			$('#QZG_TZD_UID').val(resultobj.ZG_TZD_UID);
    			//给表单赋值
    			resultobj = defaultJson.dealResultJson(rowValue);
    			$("#dfForm").setFormValues(resultobj);
    		    $('#shjltxdivid').show();
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
	//defaultJson.doQueryJsonList(controllernameContent+"?query",data,DT3);
	//setFormValues();
	$('#FAFANG_DATE').val(getNowDate());

	var tzdUid = $('#ZG_TZD_UID').val();
	if($("#ZG_TZD_UID").val() != "" && $("#ZG_TZD_UID").val() != null){
		$('#shjltxdivid').show();
	}
	
}

//查询表单值
function setFormValues(){
	var data = combineQuery.getQueryCombineData(queryForm,frmPost);
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
			$("#dfForm").setFormValues(resultobj);
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

//---表单操作begin-----
function selectFun(demo){	
	if(demo=='zt'){
		//显示 添加属性  隐藏 移除属性
		$('#XXJDT').show();
		$('#XXJDT').attr('fieldname','XXJD2');
		$('#XXJD2').hide();
		$('#XXJD2').removeAttr('fieldname');
	}else{
		$('#XXJDT').hide();
		$('#XXJDT').val("");
		$('#XXJDT').removeAttr('fieldname');
		$('#XXJD2').show();
		$('#XXJD2').attr('fieldname','XXJD2');
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

function radioFun(demo){
	if(demo=='2'){
		$('#jbtgwz').show();
	}else{
		$('#jbtgwz').hide();
		$('#JBTGWZ').val("");
	}
}

//----表单操作end-----

//----添加违规事件begin--------
function addEvent(){
	window.open("wgsj-select.jsp");
}

//回调
function loadWgsj(str){
	
	var tzdUid = $('#QZG_TZD_UID').val();
	var gongchenguid = $('#GONGCHENG_UID').val();
	var data = "{'tzdUid':'"+tzdUid+"','wgsjUidstr':'"+str+"','gongcheng_uid':'"+gongchenguid+"'}";
	//var data = Form2Json.formToJSON(dfForm);
	var data1 = defaultJson.packSaveJson(data);
	$.ajax({
		url : controllernameContent + "?insert",
		data : data1,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT3);
			//调用ajax插入
			defaultJson.doQueryJsonList(controllernameContent+"?query",data,DT3);
			setFormValues();		
		}
	});
}

function formatEdit1(obj){
	return "<a href='javascript:void(0)' onclick=\"doEdit1('"+obj.ZG_CONTENT_UID+"')\"  title='查看'><i class='icon-file showXmxxkInfo'></i></a>";
}

function doEdit1(sjuid){
	$(window).manhuaDialog({"title":"隐患整改>整改事件查看","type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/yhzg/yhzg-zgsj-edit.jsp?sjuid="+sjuid,"modal":"2"});
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



//----添加违规事件end--------
</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">
   
	<div style="height:5px;"></div>
	<div class="B-small-from-table-autoConcise">

     <form method="post" id="dfForm"  >
       <div style="height: 5px;"></div>
      		<div id="shdivid" style="display: none;">
				<div class="modal-header" style="background:#0866c6;">
				<h3 style="color:white;">复工审核  
				<span class="pull-right">
					<button type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;" onclick="addEvent();">审核</button>
	  			</span>
				</h3>
				</div>
      			<table class="B-table" width="100%" >
			        <tr>
						<th width="8%" class="right-border bottom-border text-right">答复时间</th>
			       		<td class="bottom-border right-border" width="15%">
			         		<input  id="FAFANG_DATE"   type="text" fieldname="FAFANG_DATE" name = "FAFANG_DATE"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy/MM/dd'})" fieldformat="YYYY/MM/DD" fieldtype="date"/>
			         	</td>
			         	<th width="8%" class="right-border bottom-border text-right">整改完成时间</th>
			       		<td class="bottom-border right-border" width="15%">
			         		<input  id="ZG_DATE" check-type="required"  type="text" fieldname="ZG_DATE" name = "ZG_DATE"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy/MM/dd'})" fieldformat="YYYY/MM/DD" fieldtype="date"/>
			         	</td>
			         </tr>
			         <tr>
			         	<th width="8%" class="right-border bottom-border text-right">整改答复情况</th>
			         	<td class="bottom-border right-border"colspan="3">
			         		<textarea rows="3" cols="" style="width: 81%" fieldname="CHOUCHA"></textarea>
			         	</td>
			         </tr>
				</table>
			</div>
			
      		<div id="sqdivid" >
				<div class="modal-header" style="background:#0866c6;">
				<h3 style="color:white;">整改答复  
				<span class="pull-right">
					<button type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;" onclick="addEvent();">关闭</button>
					<button type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;" onclick="addEvent();">重新整改</button>
					<button type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;" onclick="addEvent();">复工</button>
	  			</span>
				</h3>
				</div>
      			<table class="B-table" width="100%" >
			        <tr>
						<th width="8%" class="right-border bottom-border text-right">答复时间</th>
			       		<td class="bottom-border right-border" width="15%">
			         		<input  id="FAFANG_DATE"   type="text" fieldname="FAFANG_DATE" name = "FAFANG_DATE"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy/MM/dd'})" fieldformat="YYYY/MM/DD" fieldtype="date"/>
			         	</td>
			         	<th width="8%" class="right-border bottom-border text-right">整改完成时间</th>
			       		<td class="bottom-border right-border" width="15%">
			         		<input  id="ZG_DATE" check-type="required"  type="text" fieldname="ZG_DATE" name = "ZG_DATE"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy/MM/dd'})" fieldformat="YYYY/MM/DD" fieldtype="date"/>
			         	</td>
			         </tr>
			         <tr>
			         	<th width="8%" class="right-border bottom-border text-right">整改答复情况</th>
			         	<td class="bottom-border right-border"colspan="3">
			         		<textarea rows="3" cols="" style="width: 81%" fieldname="CHOUCHA"></textarea>
			         	</td>
			         </tr>
				</table>
			</div>
      </form>
    </div>
   </div>
  </div>
  <form method="post" id="queryForm"  >
    	  <input type="hidden" id="QZG_TZD_UID" fieldname="t.zg_tzd_uid" name = "ZG_TZD_UID" operation="="/>
  </form>
  <jsp:include page="/jsp/file_upload/fileupload_config.jsp" flush="true"/>
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