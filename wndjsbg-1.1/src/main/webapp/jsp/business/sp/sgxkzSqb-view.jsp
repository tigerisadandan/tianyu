<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>

<title>施工许可申请表-维护</title>
<%
	String type=request.getParameter("type");
	String id=(String)request.getAttribute("id");

%>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/sp/css/bootstrap.css?version=20131201"> </LINK>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/sp/css/xAlert.css"> </LINK>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/base/jquery.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/jsp/business/sp/js/printPageHtml.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/base/LockingTable.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/base/bootstrap.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/base/jsBruce.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/Form2Json.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/convertJson.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/combineQuery.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/default.js?version=20131206"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/TabList.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/bootstrap-validation.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/loadFields.js?version=20131206"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"> </script>
<script type="text/javascript" charset="utf-8"> 
var controllername= "${pageContext.request.contextPath }/sp/buSpywJsgcsgxkzsqController/";
var controllernameMx= "${pageContext.request.contextPath }/sp/buSpywJsgcsgxkzsqMxController/";
var controllerfile= "${pageContext.request.contextPath }/controller/fileController/";

var type ="<%=type%>";
var id ="<%=id%>";
//页面初始化
$(function() {
	init();
	
	$("#downLoad").click(function() {
		download();
	});
	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
		if($("#bU_Spyw_JsgcsgxkzSqForm").validationButton())
		{
		    //生成json串
		    var data = Form2Json.formToJSON(bU_Spyw_JsgcsgxkzSqForm);
		  //组成保存json串格式
		    var data1 = defaultJson.packSaveJson(data);
		    defaultJson.doInsertJson(controllername + "insert", data1);

		   
		}else{
			requireFormMsg();
		  	return;
		}
	});
	
	//按钮绑定事件（新增）
    $("#btnClear_Bins").click(function() {
        $("#bU_Spyw_JsgcsgxkzSqForm").clearFormResult();
        $("#bU_Spyw_JsgcsgxkzSqForm").cancleSelected();
        
        
        $("#ZFRQ").val(new Date().toLocaleDateString());
        $("#ZFJE").val(0);
        $("#ID").val("");
    });
	
	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#queryForm").clearFormResult();
    });
    
    <%
    if("detail".equals(type)){
	%>
	//置所有input 为disabled
	$(":input").each(function(i){
	   $(this).attr("disabled", "true");
	 });
	<%
		}
	%>
	
});
//页面默认参数
function init(){
	$("#pubAlert").hide();
	if(id!="null"&&id!=""){
		 $("#QID").val(id);
		
		var data = combineQuery.getQueryCombineData(queryForm,frmPost);
		var data1 = {
			msg : data
		};	
		$.ajax({

			url : controllername+"query",
			data : data1,
			cache : false,
			async :	false,
			dataType : "json",  
			type : 'post',
			success : function(response) {
				$("#resultXML").val(response.msg);
				
				var resultobj = defaultJson.dealResultJson(response.msg);
				 $("#QID1").val(resultobj.SGXKZSQ_UID);
				 $("#QID2").val(resultobj.SGXKZSQ_UID);
				 $("#SGXKZSQ_UID").val(resultobj.SGXKZSQ_UID);
				$("#bU_Spyw_JsgcsgxkzSqForm").setFormValues(resultobj);
				$("#bU_Spyw_JsgcsgxkzSqForm").setHtmlValue(resultobj);
				//readFile();获取附件的文件名称
				queryDt(resultobj.DT_IDS);
				queryFileData2('SGXKZSQ_UID','parent','BU_SPYW_JSGCSGXKZSQ');//读取附件
				QY(resultobj.ZZJGDM,resultobj.JS_COMPANY_UID);
		}
		});
		//builderMxList();
		
	}
}
function queryDt(dtIds){
	$("#DT_IDS").val(dtIds);
	$.ajax({
		url :"${pageContext.request.contextPath }/units/projectsUnitsController?querybyIds&IDS="+dtIds,
		data : null,
		cache : false,
		async : false,
		dataType : "json",
		type : 'post',
		success : function(response) {
			if(response.msg!='0'){
				var resultmsgobj = convertJson.string2json1(response.msg);
		        var  obj = resultmsgobj.response.data;
		        var html = "";  //清空
		        for(var i=0;i<resultmsgobj.response.data.length;i++){
		        	html += "<tr > "+
						"<td align=\"left\">"+obj[i].UNITS_NAME+"</td>"+
						"<td align=\"right\">"+obj[i].GCZJ+"</td>"+
						"<td align=\"right\">"+obj[i].JZMJ+"</td>"+
						"<td align=\"center\">"+obj[i].YCCS+"</td>"+
						"<td align=\"center\">"+obj[i].CD+"</td>"+
						"<td align=\"center\">"+obj[i].YT+"</td>"+
						"<td align=\"center\">"+obj[i].JGLX+"</td>"+
					"</tr>";
		        }
		        $("#DWGCTB").html(html);
			}
		}
	});
	var rows = $("tbody tr" ,$("#sqmx"));
	if(rows.size()==0){
		$("tbody" ,$("#sqmx")).append("<tr><td colspan=\"7\" style=\"height: 1px;\" align=\"center\">暂无记录</td></tr>");
	}
}
function QY(ZZJGDM,jsid){
	$("#ZZJGDM").val(ZZJGDM);
	queryFileDataCp(jsid,"","","");
}
function queryFileDataCp(ywid,glid1,sjbh,ywlx){
	var obj = new Object();
	obj.YWID = ywid;
	obj.GLID1 = glid1;
	obj.SJBH = sjbh;
	obj.YWLX = ywlx;
	var data = JSON.stringify(obj);
		var data1 = defaultJson.packSaveJson(data);
		$.ajax({
		url : "${pageContext.request.contextPath }/fileUploadOldController?queryFileList",
		data : data1,
		cache : false,
		async :	false,
		dataType : "json",
		success : function(result) {
			//clearFileTab("query");
			insertFileTabCS(result.msg);
		}
	});
}
function insertFileTabCS(result){
	var files = eval('(' + result + ')');
	if(g_nameMaxlength==undefined){
		g_nameMaxlength=12;
	}
	var tab_width = 0;
	
		tab_width = $("#ZZJGDMZSMSC").parent().parent().width();
		$("#ZZJGDMZSMSC").parent().addClass("nomargin");
		var showType = $("#ZZJGDMZSMSC").attr("showType");
		if(showType!=undefined){
			insertImageTabCS($("#ZZJGDMZSMSC"),files,tab_width);
		}else{
			insertDetailTabCS($("#ZZJGDMZSMSC"),files,tab_width);
		}
	
}
function insertDetailTabCS(obj,files,tab_width){
	var fjlb = obj.attr("fjlb");
	for(var i=0;i<files.length;i++){
		var showHtml = "";
		var file = files[i];
		
		var number = 0;
		//如果table没有附件类别，那么插入所有数据，否则只插入附件类别相匹配的数据
		if(fjlb==undefined || fjlb==file.fjlb){
			showHtml +="<tr class='template-download'   rowid='"+file.fileid+"'>";
			showHtml +="<td class='name' style='border:0;padding:0px;'>";
			//名字长度过长，折行处理
			if(file.name.length>30){
				showHtml +="<span class='nameSpan' onclick=\"doShow('"+file.fileid+"')\" fileid='"+file.fileid+"'><abbr title='"+file.name+"'>"+file.name.substring(0,g_nameMaxlength)+"...</abbr></span>"
			}else{
				showHtml +="<span class='nameSpan' onclick=\"doShow('"+file.fileid+"')\" fileid='"+file.fileid+"' target='_blank'>"+file.name+"</span>"
			}
			showHtml +="</td>";
			if(obj.attr("showSize")!="false"){
				showHtml +="<td class='size ' style='border:0;padding:0px;'>"+file.size+"</td>";
			}
			if(obj.attr("showLrr")!="false" && obj.attr("showLrr")!=undefined){
				showHtml +="<td class='size ' style='border:0;padding:0px;text-align:right;'>"+file.lrr+"</td>";
			}
			if(obj.attr("showLrsj")!="false" && obj.attr("showLrsj")!=undefined){
				showHtml +="<td class='size ' style='border:0;padding:0px;'>"+file.lrsj+"</td>";
			}

			showHtml +="<td class='delete ' style='border:0;padding:0px;'>";
			
			if(obj.attr("onlyView")=="true"){
					//如果是只读表格，那么不需要删除按钮
				if(obj.attr("deleteUser")!=undefined && obj.attr("deleteUser")==file.lrrCode){
// 								showHtml +="<td class='delete ' style='border:0;padding:0px;'>";
						showHtml +="<button class='btn btn-link my-del-btn' data-type='"+file.delete_type+"' data-url='"+file.delete_url+"' >";
						showHtml +="<i class='icon-trash'></i>";
						showHtml +="<span>删除</span>";
						showHtml +="</button>";
						//showHtml +="<input type='checkbox' name='delete' value='1'>"
// 								showHtml +="</td>"
// 								showHtml +="<td class='myPreview' style='border:0;padding:0px;'>";
					}else{
// 								showHtml +="<td class='myPreview' style='border:0;text-align:center;padding:0px;'>";
					}
			}else{
					showHtml +="<button class='btn btn-link my-del-btn' data-type='"+file.delete_type+"' data-url='"+file.delete_url+"' >";
					showHtml +="<i class='icon-trash'></i>";
					showHtml +="<span>删除</span>";
					showHtml +="</button>";
				}
	
			showHtml +="<a href='"+file.url+"' title='下载' class='btn btn-link' fileid='"+file.fileid+"' download='"+file.name+"'>";
			showHtml +="<i class='icon-download-alt'></i>";
			showHtml +="<span></span>";
			showHtml +="</a>&nbsp;";
		
			showHtml +="</td>";
			showHtml +="</tr>";
			var showHtmlHead = "";

			showHtmlHead += showHtml;
			obj.append(showHtmlHead);
		}
	}
}
function doShow(n){
	$("#previewFileid").val(n);
	window.open(encodeURI("${pageContext.request.contextPath }/jsp/file_upload/showPreviewold.jsp"));
}
function readFile(){
	
	var data = combineQuery.getQueryCombineData(queryForm1,frmPost);
	var data1 = {
		msg : data
	};	
	$.ajax({

		url : controllername+"queryReadFile",
		data : data1,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			//$("#resultXML").val(response.msg);
			//var resultobj = eval('(' + response.msg + ')');
			//var resultobj = convertJson.string2json1(response.msg);
			//alert(response.msg);
			var resultmsgobj = convertJson.string2json1(response.msg);
			var resultobj=subresultmsgobj = resultmsgobj.response.data;
			$.each(resultobj,function(){
				if(this.FILE_TYPE=="33"){
					$("#FJSC_LXPW").html(this.FILE_NAME);
				}
				else if(this.FILE_TYPE=="34"){
					$("#FJSC_TDSYZ").html(this.FILE_NAME);
				}
				else if(this.FILE_TYPE=="35"){
					$("#FJSC_GHXK").html(this.FILE_NAME);
				}
				else if(this.FILE_TYPE=="36"){
					$("#FJSC_ZFCN").html(this.FILE_NAME);
				}
				else if(this.FILE_TYPE=="7"){
					$("#ZZJGDMZSMSC").html(this.FILE_NAME);
				}
			})
		}
	});
	
}
//只输入数字 
function onlyNum(obj)
{
	obj.value = obj.value.replace(/[^\d]/g,"");
}
//只能输入数字和小数点
function onlyNumAndFloat(obj)
 {
  //先把非数字的都替换掉，除了数字和.
  obj.value = obj.value.replace(/[^\d.]/g,"");
  //必须保证第一个为数字而不是.
  obj.value = obj.value.replace(/^\./g,"");
  //保证只有出现一个.而没有多个.
  obj.value = obj.value.replace(/\.{2,}/g,".");
  //保证.只出现一次，而不能出现两次以上
  obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
 }
function builderMxList(){
	
	var data = combineQuery.getQueryCombineData(queryForm2,frmPost,sqmx); 
	try{
	//调用ajax插入
	defaultJson.doQueryJsonList(controllernameMx+"query",data,sqmx,null,true);
	}catch(e){
	}

	
	var rows = $("tbody tr" ,$("#sqmx"));
	if(rows.size()==0){
		$("tbody" ,$("#sqmx")).append("<tr><td colspan=\"7\" style=\"height: 1px;\" align=\"center\">暂无记录</td></tr>");
	}
}

//点击行事件
function tr_click(obj){
	//alert(JSON.stringify(obj));
	$("#bU_Spyw_JsgcsgxkzSqForm").setFormValues(obj);
}


//选中项目名称弹出页
function selectXm(){
	$(window).manhuaDialog({"title":"","type":"text","content":"${pageContext.request.contextPath }/jsp/business/zjgl/xmcx.jsp","modal":"2"});
}
//弹出区域回调
getWinData = function(data){
	$("#XMMC").val(JSON.parse(data).XMMC);
	$("#XMBH").val(JSON.parse(data).XMBH);
	$("#GC_TCJH_XMXDK_ID").val(JSON.parse(data).GC_TCJH_XMXDK_ID);
};

//详细信息
function rowView(index){
	var obj = $("#bU_Spyw_JsgcsgxkzSqList").getSelectedRowJsonByIndex(index);
	var xmbh = eval("("+obj+")").XMBH;
	$(window).manhuaDialog(xmscUrl(xmbh));
}
function download(){
	
	$.ajax({
		url : controllername+"download?id="+$("#SGXKZSQ_UID").val(), 
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			$("#resultXML").val(response.msg);
<%--			var resultobj = defaultJson.dealResultJson(response.msg);--%>
			//window.open(controllerfile+"download2?path="+response.msg,'文件下载打印'); 

			window.location.href = controllerfile+"download2?path="+response.msg;
	}
	});
	
}
function addSqmx(demo){
	var cloneNew = $("#cloneTR").clone(true);
	$(cloneNew).removeAttr("style")
	$("#sqmx").append(cloneNew);
	$(demo).hide();
}

function removeSqmx(demo){
	if($("#sqmx tr").size()==3){

		return;
		}
	var tr_index = $("#sqmx tr").index($(demo).closest("tr"));
	if(tr_index==$("#sqmx tr").size()-1&&tr_index>2){
		$("#sqmx tr").eq($("#sqmx tr").size()-2).find("td").eq($("#sqmx").find("th").size()-1).append('&nbsp;<img onclick="addSqmx(this)" style="cursor: pointer;" title="增加" src="/wndjsbg/img/sg/icon-addZz.jpg">');
	}
	$(demo).closest("tr").remove();
}
</script>      
</head>
<body>

<div class="container-fluid">
<div class="row-fluid">
    <div class="B-small-from-table-autoConcise">
     <form method="post" id="queryForm"  >
      <table class="B-table" width="100%">
      <!--可以再此处加入hidden域作为过滤条件 -->
      	<TR  style="display:none;">
	        <TD class="right-border bottom-border">
	        	<INPUT type="text" class="span12" kind="text" id="QID" name="ID"  fieldname="bu_spyw_jsgcsgxkzsq.YWLZ_UID" value="" operation="="/>
	        </TD>
			<TD class="right-border bottom-border">
			</TD>
        </TR>
      </table>
      </form>
      <form method="post" id="queryForm1"  >
      <table class="B-table" width="100%">
      <!--可以再此处加入hidden域作为过滤条件 -->
      	<TR  style="display:none;">
	        <TD class="right-border bottom-border">
	        	<INPUT type="text" class="span12" kind="text" id="QID1" name="ID"  fieldname="TARGET_UID" value="" operation="="/>
	        </TD>
			<TD class="right-border bottom-border">
			</TD>
        </TR>
      </table>
      </form>
       <form method="post" id="queryForm2"  >
      <table class="B-table" width="100%">
      <!--可以再此处加入hidden域作为过滤条件 -->
      	<TR  style="display:none;">
	        <TD class="right-border bottom-border">
	        	<INPUT type="text" class="span12" kind="text" id="QID2" name="ID"  fieldname="SGXKZSQ_UID" value="" operation="="/>
	        </TD>
			<TD class="right-border bottom-border">
			</TD>
        </TR>
      </table>
      </form>
   </div>
	<div class="B-small-from-table-autoConcise">
      
      	<span style="float:right">
            <button id="btnShutDown" onclick="window.close();" class="btn" type="button" style="font-family: SimYou, Microsoft YaHei; font-weight: bold;">关闭</button>
      	    <button id="downLoad" class="btn" type="button" style="font-family: SimYou, Microsoft YaHei; font-weight: bold;">打印</button>
      	</span>
        <br/>
     <form method="post" id="bU_Spyw_JsgcsgxkzSqForm" >
     <h4 class="title" align="center">无锡新区建设工程施工许可证申请表</h4>
      <%--<table class="B-table" width="60%" align="center">
       --%> <table border="1"  align="center" style="width: 750px;height: 950px;"  class="cc-alert-table" >
      			  <input type="hidden" id="BU_SPYW_JSGCSGXKZSQ_FILEUPLOAD" fieldname="BU_SPYW_JSGCSGXKZSQ_FILEUPLOAD" name = "BU_SPYW_JSGCSGXKZSQ_FILEUPLOAD"/>
                  <input type="hidden" id="SGXKZSQ_UID" fieldname="SGXKZSQ_UID" name = "SGXKZSQ_UID"/>
                 <input type="hidden" id="YWLZ_UID" fieldname="YWLZ_UID" name = "YWLZ_UID"/>
                   <th  colspan="4" align="center">建设单位信息</th>
                     <tr>
						<td height="30" width="15%" align="right">建设单位名称</td>
			       	 	<td height="30" width="35%"  fieldname="JSDWMC">
			         	</td>
			            <td height="30" width="15%" align="right">所有制性质</td>
						<td height="30" width="35%" fieldname="SYZXZ">
					    </td>
			        </tr>
			        <tr>
						<td height="30"  width="15%" align="right">建设单位地址</td>
						<td height="30"  width="35%" fieldname="JSDWDZ">
						</td>
						<td height="30"  width="15%" align="right">法人代表</td>
						<td height="30"  width="35%" fieldname="FRDB">
					    </td>
					</tr>
					<tr>
						<td width="15%" align="right">组织机构代码</td>
						<td width="35%" fieldname="ZZJGDM" id="ZZJGDMTD">
					    </td>
					    <td width="15%" align="right">组织机构代码证扫描上传</td>
						<td width="35%"  >
								<table role="presentation" class="table table-striped">
									<tbody fjlb="13" id="ZZJGDMZSMSC" class="files showFileTab" onlyView="true" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>
								</table>
					    </td>
					    
					</tr>	
					<th colspan="4" align="center">建设单位经办人信息</th>
					<tr>
						<td height="30"  width="15%" align="right">姓名</td>
						<td height="30"  width="35%" fieldname="XM_JSDW">
					    </td>
					    <td height="30"  width="15%" align="right">性别</td>
						<td height="30"  width="35%" fieldname="XB_JSDW" >
					    </td>
					</tr>
					<tr>
						<td height="30"  width="15%" align="right">身份证号码</td>
						<td height="30"  width="35%" fieldname="SFZH_JSDW">
					    </td>
					    <td width="15%" align="right">联系电话</td>
						<td width="35%" fieldname="LXDH_JSDW" >
					    </td>
					</tr>
					<th align="center" colspan="4">工程项目信息</th>
					<tr>
						<td height="30"  width="15%" align="right">工程名称</td>
						<td height="30"  width="35%"  fieldname="GCMC">
					    </td>
					
						<td height="30" width="15%" align="right">建设类别</td>
						<td height="30" width="35%"  fieldname="JSLB">
					    </td>
					</tr>
					<tr>
						<td height="30"  width="15%" align="right">项目建设地址</td>
						<td height="30"  colspan="3" fieldname="XMJSDZ">
					    </td>
					</tr>
					<tr>
						<td width="15%" align="right">计划（合同）开工日期</td>
						<td fieldname="JHKGRQ_DATE">
					     </td>
					
						<td width="15%" align="right">计划（合同）竣工日期</td>
						<td fieldname="JHJGRQ_DATE" >
					     </td>
					</tr>
					<tr>
						<td height="30"  width="15%" align="right">项目核准文号</td>
						<td height="30"  width="35%" fieldname="XMHZWH">
					    </td>
					
						<td width="15%" align="right">项目核准日期</td>
						<td width="35%" fieldname="XMHZRQ_DATE">
					       
					    </td>
					</tr>
					<tr>
						<td height="30"  width="15%" align="right">投资总额</td>
						<td height="30"  width="35%" fieldname="TZZE">
					    </td>
					      <td height="30" width="15%" align="right"></td>
						<td  height="30" width="35%">
					    </td>
					</tr>
					<tr>
						<td height="30" width="15%" align="right">建筑面积</td>
						<td  height="30" width="35%" fieldname="JZMJ" >
					    </td>
					
						<td height="30"  width="15%" align="right">合同造价</td>
						<td  height="30" width="35%" fieldname="HTZJ">
					    </td>
					</tr>
					<th colspan="4" align="center">单位工程</th>
					<tr >
					
						<td align="center" colspan="6" cellSpacing=0 cellPadding=0 style="padding-left: 0px;">
							<div class="overFlowX" >
							 <table width="100%" id="sqmx" border="1">
								  <thead>
								   <tr>
										<th width="12%" height="32" align="center">工程名称</th>
										<th width="10%"  align="center">工程造价</th>
										<th width="10%" align="center">建筑面积</th>
										<th width="6%" align="center">层数</th>
										<th width="6%" align="center">长度</th>
										<th width="12%" align="center">用途</th>
										<th width="8%" align="center">结构类型</th>
									</tr>
									</thead>
									<tbody id="DWGCTB">
									</tbody>
							</table>
							</div>
						</td>
					</tr>
					<th align="center" colspan="4">参建单位信息</th>
					<tr>
						<td  colspan="6" cellSpacing=0 cellPadding=0 style="padding-left: 0px;">
							<table width="100%" height="100%" frame=void  style="BORDER-COLLAPSE:   collapse;" border="1" cellSpacing=0 cellPadding=0>
								<tr>
									<th align="center" height="30"  style="width:15%">参建单位类型</th>
									<th align="center" height="30"  style="width:18%">参建单位名称</th>
									<th align="center" height="30"  style="width:15%">资质证书号</th>
									<th align="center" height="30"  style="width:14%">负责人</th>
									<th align="center" height="30"  style="width:14%">资格证书号</th>
								</tr>
								<tr>
									<td height="30"  align="center">勘察单位</td>
									<td  fieldname="CJDWMC_KC" >
									  </td>
									<td fieldname="ZZZSH_KC" >
									  </td>
									<td  fieldname="FZR_KC" >
									</td>
									<td  fieldname="ZGZSH_KC" >
									  </td>
								</tr>
								<tr>
									<td height="30"  align="center">设计单位</td>
									<td fieldname="CJDWMC_SJ" >
									  </td>
									<td fieldname="ZZZSH_SJ">
									 </td>
									<td fieldname="FZR_SJ" >
									 </td>
									<td fieldname="ZGZSH_SJ">
									  </td>
								</tr>
								<tr>
									<td height="30" align="center">施工单位</td>
									<td fieldname="CJDWMC_SG">
									  </td>
									<td fieldname="ZZZSH_SG">
									  </td>
									<td fieldname="FZR_SG">
									  </td>
									<td fieldname="ZGZSH_SG" >
									  </td>
								</tr>
								<tr>
									<td height="30" align="center">监理单位</td>
									<td fieldname="CJDWMC_JL" >
									  </td>
									<td fieldname="ZZZSH_JL" >
									  </td>
									<td fieldname="FZR_JL" >
									  </td>
									<td fieldname="ZGZSH_JL" >
									  </td>
								</tr>
							</table>
					  </td>
				</tr>
				<th align="center" colspan="4">建设单位提供的文件或证明材料</th>
				<tr>
				   <td align="center" colspan="6" cellSpacing=0 cellPadding=0 style="padding-left: 0px;">
				     <table  width="100%" frame=void  height="100%"  border="1">
				          <tr>
									<th align="center" height="30" style="width:8%">序号</td>
									<th align="center" height="30" style="width:18%">文件名称</th>
									<th align="center" height="30" style="width:15%">文件编号</th>
									<th align="center" height="30" style="width:14%">发放日期</th>
									<th align="center" height="30" style="width:14%">附件上传</th>
						  </tr>
				           <tr>
								    <td height="30" align="center">1</td>
				                    <td align="center">立项批文</td>
									<td fieldname="WJBH_LXPW" ></td>
									<td fieldname="FFRQ_LXPW" ></td>
									<td  id="FJSC_LXPW" >
									  <div>
						                     <span style="display:none;" class="btn btn-fileUpload" id="addFileSpan" onclick="doSelectFile(this,'parent');" ywid="SGXKZSQ_UID" target_type="2" file_type="33" business_type="BU_SPYW_JSGCSGXKZSQ">
								   			     <i class="icon-plus"></i>
											     <span>添加文件</span>
											</span>
											   <table role="presentation" class="table table-striped">
										    	  <tbody fjlb="0701" class="files showFileTab" data-toggle="modal-gallery" onlyView="true" data-target="#modal-gallery"></tbody>
											   </table>
											</div>
									</td>
						 </tr>
						  <tr>
						            <td height="30" align="center">2</td>
				                    <td align="center">国有土地使用证</td>
									<td fieldname="WJBH_TDSYZ" ></td>
									<td fieldname="FFRQ_TDSYZ" ></td>
									<td id="FJSC_TDSYZ" >
										<div>
						                     <span style="display:none;" class="btn btn-fileUpload" id="addFileSpan" onclick="doSelectFile(this,'parent');" ywid="SGXKZSQ_UID" target_type="2" file_type="34" business_type="BU_SPYW_JSGCSGXKZSQ">
								   			 </span>
											   <table role="presentation" class="table table-striped">
										    	  <tbody fjlb="0701" class="files showFileTab" data-toggle="modal-gallery" onlyView="true" data-target="#modal-gallery"></tbody>
											   </table>
										</div>
									</td>
						 </tr>
						  <tr>
						  			<td height="30" align="center">3</td>
				                    <td align="center">建设工程规划许可证</td>
									<td fieldname="WJBH_GHXK" ></td>
									<td fieldname="FFRQ_GHXK" ></td>
									<td id="FJSC_GHXK">
									<div>
									<span style="display:none;" class="btn btn-fileUpload" id="addFileSpan" onclick="doSelectFile(this,'parent');" ywid="SGXKZSQ_UID" target_type="2" file_type="35" business_type="BU_SPYW_JSGCSGXKZSQ">
								   			     <i class="icon-plus"></i>
											     <span>添加文件</span>
											</span>
									<table role="presentation" class="table table-striped">
										   <tbody fjlb="0701"  class="files showFileTab" data-toggle="modal-gallery" onlyView="true"  data-target="#modal-gallery"></tbody>
									</table>
									</div>
									</td>
						 </tr>
						  <tr>
						  			<td height="30" align="center">4</td>
				                    <td align="center">建设单位资金到位支付承诺</td>
									<td fieldname="WJBH_ZFCN" ></td>
									<td fieldname="FFRQ_ZFCN" ></td>
									<td  id="FJSC_ZFCN" >
									 <div>
						              <span style="display:none;" class="btn btn-fileUpload" id="addFileSpan" onclick="doSelectFile(this,'parent');" ywid="SGXKZSQ_UID" target_type="2" file_type="36" business_type="BU_SPYW_JSGCSGXKZSQ">
								   			<i class="icon-plus"></i>
											<span>添加文件</span>
									  </span>
										<table role="presentation" class="table table-striped">
										    <tbody fjlb="0701" class="files showFileTab" data-toggle="modal-gallery" onlyView="true" data-target="#modal-gallery"></tbody>
										</table>
									</div>
									</td>
						</tr>
				     </table>
				  </td>
				</tr>
				<tr>
				  <td colspan="4">
				     &nbsp;&nbsp;&nbsp;&nbsp;申请单位承诺：以上填写及上传资料内容真实无误。
				  <br/>
				  <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;法定代表人（签字）：
				  <div style="float:right">单位（盖章）：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
				  <br/>                                                                                                                                                                                                  
				  <div style="float:right">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
				  </td>
				</tr>
      </table>
      </form>
    </div>
   </div>
   <jsp:include page="/jsp/file_upload/fileupload_config.jsp" flush="true"/>
  </div>
  <div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id = "queryXML">
         <input type="hidden" name="txtXML" id = "txtXML">
         <input type="hidden" name="txtFilter"  order="desc" fieldname = "CREATED_DATE" id = "txtFilter">
         <input type="hidden" name="resultXML" id = "resultXML">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
		
 	</FORM>
 </div>
</body>
<script>
</script>
</html>