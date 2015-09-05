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
var controllername = "${pageContext.request.contextPath }/yhzg/zgDafuController";
//页面初始化
$(function() {
	init();
	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
		if($("#dfForm").validationButton()){
			$("#dfzt").val("3");
		    //生成json串
		    var data = Form2Json.formToJSON(dfForm);
		  //组成保存json串格式
		    var data1 = defaultJson.packSaveJson(data);
		  //调用ajax插入
    		defaultJson.doUpdateJson(controllername + "?update", data1);
		}else{
			requireFormMsg();
		  	//return;
		}
	});

	$("#btnAgain").click(function (){


	});

	$("#btnClose").bind("click", function(){
		var a=$(this).manhuaDialog.close();
	});	

});
//页面默认参数
function init(){
	setFormValues();
	queryZgsjData();
	
}

//查询表单值
function setFormValues(){
	var parentmain = $(window).manhuaDialog.getParentObj();
	var rowValue = parentmain.$("#resultXML").val();
	var tempJson = convertJson.string2json1(rowValue);
	$('#QZG_TZD_UID').val(tempJson.ZG_TZD_UID);
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
			var resultobj = defaultJson.dealResultJson(response.msg);
			queryFileData(resultobj.ZG_DAFU_UID, "", "", "900009");
			$("#dfForm").setFormValues(resultobj);
			if(resultobj.TYPE == '3'){
				$("#btnAgain").hide();
				$("#btnSave").hide();
			}
		}
	});
}

function queryZgsjData(){
	var data = combineQuery.getQueryCombineData(queryForm,frmPost);
	var data1 = {
		msg : data
	};
	$.ajax({
		url :"${pageContext.request.contextPath }/yhzg/zgContentController?query",
		data: data1,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			//var res = dealSpecialCharactor(response.msg);
			var resultmsgobj = convertJson.string2json1(response.msg);

			if(resultmsgobj!=null&&resultmsgobj!=''){
				var datslist = resultmsgobj.response.data;
				$(datslist).each(function(index,data){
					var cloneNew = $("#cloneTR").clone(true);
					$(cloneNew).removeAttr("style");
					setData(cloneNew,data);
					$("#zgsjList").append(cloneNew);
				});
			}else{
				var cloneNew = $("#cloneTR").clone(true);
				$(cloneNew).removeAttr("style");
				$("#zgsjList").append(cloneNew);
			}
		}
	});

}

//设置数据
function setData(dom,data){
	$(dom).find("#ZG_CONTENT_UID").val(data.ZG_CONTENT_UID);
	$(dom).find("#WG_DENGJI").text(data.WG_DENGJI);
	$(dom).find("#NAME").text(data.NAME);
	$(dom).find("#WG_MIAOSHU").text(data.WG_MIAOSHU);
	$(dom).find("#DESCRIPTION").text(data.DESCRIPTION);	
	$(dom).find("#DAFU_CONTENT1").text(data.DAFU_CONTENT);	
	
} 


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
      		<div id="shjltxdivid" >
				<div class="modal-header" style="background:#0866c6;">
				<h3 style="color:white;">整改答复  
				<span class="pull-right">
					<button type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;" id="btnClose">关闭</button>
					
					<button type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;" id="btnAgain">重新整改</button>
					<button type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;" id="btnSave">闭合</button>
					
	  			</span>
				</h3>
				</div>
      			<table class="B-table" width="100%" >
      				<input type="hidden" id="dfzt" fieldname="HOUXU_CL"/>
      				<input type="hidden" id="dfUid" fieldname="ZG_DAFU_UID"/>
      				<input type="hidden" id="tzdUid" fieldname="ZG_TZD_UID"/>
			        <tr>
						<th width="8%" class="right-border bottom-border text-right">答复时间</th>
			       		<td class="bottom-border right-border" width="15%">
			         		<input  id="DAFU_DATE"   type="text" fieldname="DAFU_DATE" name = "DAFU_DATE"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy/MM/dd'})" fieldformat="YYYY/MM/DD" fieldtype="date"/>
			         	</td>
			         	<th width="8%" class="right-border bottom-border text-right">整改完成时间</th>
			       		<td class="bottom-border right-border" width="15%">
			         		<input  id="DAFU_END_DATE" check-type="required"  type="text" fieldname="DAFU_END_DATE" name = "DAFU_END_DATE"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy/MM/dd'})" fieldformat="YYYY/MM/DD" fieldtype="date"/>
			         	</td>
			         </tr>
			         <tr>
			         	<th width="8%" class="right-border bottom-border text-right">整改答复情况</th>
			         	<td class="bottom-border right-border"colspan="3" width="15%">
			         		<textarea rows="3"  style="width: 98%" fieldname="DAFU_CONTENT"></textarea>
			         	</td>
			         </tr>
			         <tr>
						<th width="8%" class="right-border bottom-border text-right ">整改事件<br />
						<b><span id="zgtext" style="color:red;font-size: 14px"></span></b>
						</th>
						<td  class="right-border bottom-border" colspan="3" width="15%">
							<table class="B-table" width="100%" id="zgsjList">
								<tr>
									<th>违规等级 </th>
									<th>违规事件</th>
									<th>违规描述</th>
									<th>详细描述</th>
									<th>答复</th>
									<th>整改照片</th>
									
								</tr>
								<tr id="cloneTR" style="display : none;"><!-- 用来复制 -->
									<td>
										<input id="ZG_CONTENT_UID" type="hidden"  fieldname="ZG_CONTENT_UID"  />
										<!--  <input id="ZHUCE_NAME"  class="span12"style="width:99%"  check-type="maxlength" maxlength="36" name="ZHUCE_NAME" fieldname="ZHUCE_NAME" type="text"/>  -->
										<p id="WG_DENGJI"></p>
									</td>
									<td>
										<!-- <input id="ZHUCE_CODE1"  class="span12"style="width:99%"  check-type="maxlength" maxlength="36" name="ZHUCE_CODE1" fieldname="ZHUCE_CODE1" type="text"/> -->
										<p id="NAME" ></p>
									</td>
									<td>
										<!--	<input id="FAZHENG_DATE1"  class="Wdate" type="text" onClick="WdatePicker()" style="width:85%" field  fieldformat="YYYY-MM-DD" name="FAZHENG_DATE1" fieldname="FAZHENG_DATE1"   />-->
										<p id="WG_MIAOSHU"></p>
									</td>
								    <td>
								    	<!-- <input id="VALID_DATE1"  class="Wdate" type="text" onClick="WdatePicker()" style="width:85%" field  fieldformat="YYYY-MM-DD" name="VALID_DATE1" fieldname="VALID_DATE1"   />-->
								    	<p id="DESCRIPTION"></p>
								    </td>
								    <td>
								    	<p id="DAFU_CONTENT1"></p>
								    </td>
								    <td>
								    	<div>
							      			<table role="presentation"  class="table table-striped">
												<tbody fjlb="1071" id="gccltbody" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery">
												</tbody>
											</table>
							            </div>
								    </td>
								</tr>
								
							</table>
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