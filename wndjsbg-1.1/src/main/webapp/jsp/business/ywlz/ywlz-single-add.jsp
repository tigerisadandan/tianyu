<!DOCTYPE html>
<html>
<head> 	
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<%
String spyw_uid = request.getParameter("spyw_uid");
 %>

<app:base/>
<title>审批业务流转实例首页</title>

<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/ywlz/css/default.css"> </LINK>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" src="/wndjsjs/jsp/business/sp/js/printPageHtml.js"> </script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/ywlz/buSpYwlzController/";
var controllernameScxm= "${pageContext.request.contextPath }/sp/buSpywScxmmjhsdController";
var controllernameJyfwfsf= "${pageContext.request.contextPath }/bzwj/buSpywJyfwfsfJsgcjktzdController/";
var controllernameQtsfd= "${pageContext.request.contextPath }/bzwj/buSpywQgjjzsjftspsxQgsftzdController/";
var spywid="<%=spyw_uid%>";
//页面初始化
$(function() {	
	init();	
	
});
var wj_PROJECTS_UID="";
function init(){
	var parentmain = $(window).manhuaDialog.getParentObj();
	var rowValue = parentmain.$("#resultXML").val();
	var tempJson = convertJson.string2json1(rowValue);
	var ywlz_uid = tempJson.YWLZ_UID;
	var spyw_uid = tempJson.SPYW_UID;
	var projects_uid =tempJson.PROJECTS_UID;
	wj_PROJECTS_UID=projects_uid;
	$("#SPYW_UID").val(spyw_uid);
	getClxx(spyw_uid,ywlz_uid,projects_uid);
	getSlyj(tempJson.YWLZ_UID);
	getFiles();
}
//点击获取行对象
function tr_click(obj,tabListid){
	
}
function loadFiles(url){
	window.open(url,'文件下载');
}

function getClxx(spyw_uid,ywlz_uid,projects_uid){
	$.ajax({
		url : controllername+"queryYwcl?ywlz_uid="+ywlz_uid,
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			if(response!=""){
				var cls =  convertJson.string2json1(response);
				var showHtml = "";
				$.each(cls,function(i){
				    if(this.SOURCE_LCUID!=null){
				    this.CLSX="H";
				    }
					showHtml += "<li>"+(i+1)+"."+this.CLNR;
					
					if(this.URL!=""){
						showHtml += '<button id="btnInsert" onclick="showTbMsg(\''+this.URL+'\',\''+ywlz_uid+'\')" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">查看</button>';
					}
					if(this.CLSX!="H"){ //核发不需要下载 
					if(this.SFYFJ=="1"&&(this.URL==""||this.URL=="#")){
						showHtml += "<a href='javascript:void(0)' onclick='loadFiles(\"${pageContext.request.contextPath }"+this.LOADPATH+"\")' class='btn btn-link' fileid='"+this.FILEID+"' download='"+this.LOADFILENAME+"'>";
						showHtml +="<i class='icon-download-alt'></i>";
						showHtml +="<span>下载</span>";
						showHtml +="</a>";
					}
					}
                   if(this.SFYSC=="1"){
						if(this.CL_LEVEL=="YW"){ //业务附件
						showHtml += '<span onlyView="true" id="'+this.YWCL_UID+'" fjlb="'+this.UPLOAD_FJLB+'" projects="'+projects_uid+'" ywid="'+projects_uid+'" glid1="'+this.JS_COM_CJK_UID+'" cl_level="'+this.CL_LEVEL+'" glid2="'+this.CLK_UID+'" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"></span>';
						}
					    else if(this.CL_LEVEL=="LX"){  //立项附件
					    showHtml += '<span onlyView="true" id="'+this.YWCL_UID+'" showSize="false" fjlb="'+this.CLK_UID+'" projects="'+wj_PROJECTS_UID+'" ywid="'+spywid+'" glid1="'+this.JS_COM_CJK_UID+'" glid2="'+this.CLK_UID+'" cl_level="'+this.CL_LEVEL+'" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"></span>';
					    } 
					
					}else{
				    	if(this.CLSX=="H"){ //核发 附件
						showHtml += '<span onlyView="true" id="'+this.YWCL_UID+'" showSize="false" fjlb="'+this.CLK_UID+'" projects="'+wj_PROJECTS_UID+'" ywid="'+spywid+'" glid1="'+this.JS_COM_CJK_UID+'" glid2="'+this.CLK_UID+'" cl_level="'+this.CLSX+'" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"></span>';
					    }
					}

					showHtml += "</li>";

					if(i==0){
						$("#yw_title").html(this.YWMC);
						$("#bz_text").html(this.BZXX);
					}
				})
				$("#ywcl_list").append(showHtml);
			}
		}
	});
}
function showTbMsg(url,ywlz_uid){
//	alert(url+"---"+ywlz_uid);
	window.open("${pageContext.request.contextPath}/"+url+"/detail:"+ywlz_uid,"填报信息查看");
	
}

function getBzwj(obj){
var html="";
  $.ajax({   //ajax 
			url : controllernameScxm + "/queryByLzbz?LZBZ_UID="+obj.LZBZ_UID,
			cache : false,
			async : false,
			dataType : "json",
			type : 'post',
			success : function(response) {
				 var res = dealSpecialCharactor(response.msg);
				    if(response.msg=="0"){
				    return;
				    }
					var resultmsgobj = convertJson.string2json1(res);
					size=resultmsgobj.response.data.length;
					for(var i=0;i<size;i++){
					html+="<a href=\"#\" onclick=\"bzwjadd('/wndjsbg"+resultmsgobj.response.data[i].URL+"','"+resultmsgobj.response.data[i].SCXMMJHSD_UID+"')\"><i class=\"icon-print\" title=\"打印"+resultmsgobj.response.data[i].WJNAME+"\"></i></a>&nbsp;";
					}
			 }
		 });
	$.ajax({   //ajax  文件2
			url : controllernameQtsfd + "/queryByLzbz?LZBZ_UID="+obj.LZBZ_UID,
			cache : false,
			async : false,
			dataType : "json",
			type : 'post',
			success : function(response) {
				 var res = dealSpecialCharactor(response.msg);
				    if(response.msg=="0"){
				    return;
				    }
					var resultmsgobj = convertJson.string2json1(res);
					size=resultmsgobj.response.data.length;
					for(var i=0;i<size;i++){
					html+="<a href=\"#\" onclick=\"bzwjadd('/wndjsbg"+resultmsgobj.response.data[i].URL+"','"+resultmsgobj.response.data[i].QGJJZSJFTSPSX_QGSFTZD_UID+"')\"><i class=\"icon-print\" title=\"打印"+resultmsgobj.response.data[i].WJNAME+"\"></i></a>&nbsp;";
					}
			 }
		 }); 
		 $.ajax({   //ajax  文件3   10 建设工程交易服务费缴款通知单（直接发包）
			url : controllernameJyfwfsf + "/queryByLzbz?LZBZ_UID="+obj.LZBZ_UID,
			cache : false,
			async : false,
			dataType : "json",
			type : 'post',
			success : function(response) {
				 var res = dealSpecialCharactor(response.msg);
				    if(response.msg=="0"){
				    return;
				    }
					var resultmsgobj = convertJson.string2json1(res);
					size=resultmsgobj.response.data.length;
					for(var i=0;i<size;i++){
					html+="<a href=\"#\" onclick=\"bzwjadd('/wndjsbg"+resultmsgobj.response.data[i].URL+"','"+resultmsgobj.response.data[i].JYFWFSF_JSGCJKTZD_UID+"')\"><i class=\"icon-print\" title=\"打印"+resultmsgobj.response.data[i].WJNAME+"\"></i></a>&nbsp;";
					}
			 }
		 }); 
  if(html==""){
  html="无";
  }
return html;
}

function bzwjadd(url,id){ //操作 
//	${pageContext.request.contextPath}/"+this.URL+"/detail:"+this.YWLZ_UID;
	window.open(url+"view:"+id,"步骤文件view打印");
}

function getFiles(){
	$(".showFileTab").each(function(i,item){
		var obj = new Object();
		obj.GLID1 = $(item).attr("glid1");
		obj.GLID2 = $(item).attr("glid2");
		obj.YWID = $(item).attr("ywid");
		obj.FJLB = $(item).attr("fjlb");
		
		obj.PROJECTS_UID = wj_PROJECTS_UID;//项目分期ID
		obj.CL_LEVEL= $(item).attr("cl_level");//材料级别
		if(obj.CL_LEVEL=="undefined"){
		obj.CL_LEVEL="";
		}
		obj.PROJECTS_TYPE= "J";//项目 类型
		
		var data = JSON.stringify(obj);
		var data1 = defaultJson.packSaveJson(data);		
		$.ajax({
			url : "${pageContext.request.contextPath }/fileUploadOldController?queryFileListYwlz",
			data : data1,
			cache : false,
			async :	false,
			dataType : "json",
			success : function(result) {
				clearFileTab2("query",item);
				insertFileTab2(result.msg,item);
			}
		});
	})
}

function getSlyj(ywlzuid){
//alert(ywlzuid);	
	//列出审批意见信息
		var queryconditionXX = "{\"logic\":\"and\",\"fieldname\":\"YWLZ_UID\",\"operation\":\"=\",\"value\":\""+ywlzuid+"\",\"fieldtype\":'',\"fieldformat\":''}";
//		var queryconditionXX0 = "{\"logic\":\"and\",\"fieldname\":\"CHULI_JIEGUO\",\"operation\":\"!=\",\"value\":\""+0+"\",\"fieldtype\":'',\"fieldformat\":''}";
//		alert(queryconditionXX);
		var dataXX = "{querycondition: {conditions: [" +queryconditionXX+" ]},pages:{recordsperpage:200, currentpagenum:1, totalpage:0, countrows:0},orders:[{\"fieldname\":\"BEGIN_DATE\",\"order\":\"asc\"}]}";
	    //调用ajax插入
		defaultJson.doQueryJsonList("${pageContext.request.contextPath}/ywlz/buSpYwlzBzController?query", dataXX, spyjList);
		
}

</script>
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
	<p><input type="hidden" name="YWLZ_UID" id="YWLZ_UID"/></p>
	<div class="row-fluid">
		<div class="B-small-from-table-autoConcise">
		<h4 class="title">
			<span id="yw_title">审批信息</span>	
		</h4>
		<table border="1" width="100%" cellpadding="0" cellspacing="0" class="content">
		
			<tr>
				<td>
					<ul id="ywcl_list">
						
					</ul>
				</td>
			</tr>
		</table>
		<div style="height: 5px;"></div>
		<table border="1" width="100%" cellpadding="0" cellspacing="0" class="content">
			<tr>
				<td class="yw-title">
					<h4>受理意见</h4>
				</td>
			</tr>
			<tr>
				<table width="100%" class="table-hover table-activeTd B-table" id="spyjList" type="single" pageNum="2000" noPage="true">
			                <thead>
			                	<tr style="display:blank">
			                		<th fieldname="BZMC" style="width:180px;" colindex=1 tdalign="center" ">步骤名称</th>
			                		<th fieldname="USERNAME" style="width:180px;" colindex=1 tdalign="center" ">审批人</th>
			                		<th fieldname="ACT_END_DATE_SV" colindex=2 tdalign="center" style="width:180px;" >&nbsp;审批时间&nbsp;</th>
									<th fieldname="ZJIEGOU" colindex=2 tdalign="center" style="width:180px;" >&nbsp;审批结果&nbsp;</th>
									<th fieldname="CHULI_YIJIAN" colindex=2 tdalign="left" >&nbsp;审批意见&nbsp;</th>
									<th fieldname="LZBZ_UID" colindex=2 tdalign="center" customfunction="getBzwj" >&nbsp;步骤文件&nbsp;</th><!--  -->
			                	</tr>
			                </thead>
			              	<tbody></tbody>
			     </table>
			</tr>
		</table>
		<div style="height: 5px;"></div>
		<table border="1" width="100%"  cellpadding="0" cellspacing="0" class="content">
			<tr>
				<td class="yw-title">
					<h4>帮助</h4>
				</td>
			</tr>
			<tr>
				<td id="bz_text">
					
				</td>
			</tr>
		</table>
		 <jsp:include page="/jsp/file_upload/fileuploadold_config_forYwlz.jsp" flush="true" />
		 
	</div>     
</div>

<div align="center">
	<FORM name="frmPost" method="post" style="display: none" target="_blank">
		<input type="text" id="YWLZ_UID"/>
		<input type="text" id="SPYW_UID"/>
		<!--系统保留定义区域-->
		<input type="hidden" name="queryXML"/> 
		<input type="hidden" name="txtXML"/>
		<input type="hidden" name="txtFilter" order="desc"  id="txtFilter"/>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>