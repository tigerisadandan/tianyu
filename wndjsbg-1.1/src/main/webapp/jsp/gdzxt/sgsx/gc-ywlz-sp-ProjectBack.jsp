<!DOCTYPE html>
<html>
<head> 	
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<%
String spyw_uid = request.getParameter("spyw_uid");
String gongcheng_uid = request.getParameter("gongcheng_uid");
 %>

<app:base/>
<title>审批业务流转实例首页</title>

<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/ywlz/css/default.css"> </LINK>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" src="/wndjsbg/jsp/business/sp/js/printPageHtml.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/ywlz/buSpYwlzController/";
var controllernameScxm= "${pageContext.request.contextPath }/sp/buSpywScxmmjhsdController";
var controllernameJyfwfsf= "${pageContext.request.contextPath }/bzwj/buSpywJyfwfsfJsgcjktzdController/";
var controllernameQtsfd= "${pageContext.request.contextPath }/bzwj/buSpywQgjjzsjftspsxQgsftzdController/";
var SgsxController="${pageContext.request.contextPath}/sgsx/SgsxProjectController";
var spywid="<%=spyw_uid%>";

//页面初始化
$(function() {	
	init();	
	initBz();//业务没材料不查帮助  需单独查询
});
var wj_PROJECTS_UID="";
function init(){
	
	 var projects_uid = "<%=gongcheng_uid%>";
	 var spyw_uid = "<%=spyw_uid%>";
	 var ywlz_uid;
	$.ajax({
		url : SgsxController+"/querySgYwlzId",
		data :{"gongcheng_uid":projects_uid,"spyw_uid":spyw_uid},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			if(response.msg!=""&&response.msg!="0"){
				 var obj = convertJson.string2json1(response.msg);
				 ywlz_uid=obj.response.data[0].YWLZ_UID; 
			}else{
			  xAlert("请联系相关人员处理");
			}
			
		}
	});	
	
	wj_PROJECTS_UID=projects_uid;
	$("#SPYW_UID").val(spyw_uid);
	getClxx(spyw_uid,ywlz_uid,projects_uid);
	getSlyj(ywlz_uid);
	getFiles();
}

function gcPrint(){
	window.location.href =controllername + "downloadGc?uid="+wj_PROJECTS_UID+"&bh="+$("#ZS_CODE").val();
}
function jzPrint(){
	window.location.href =controllername + "downloadJz?uid="+wj_PROJECTS_UID;
}

function szPrint(){
	window.location.href =controllername + "downloadSz?uid="+wj_PROJECTS_UID;
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
						showHtml += '<span onlyView="true" id="'+this.YWCL_UID+'" fjlb="'+this.UPLOAD_FJLB+'" projects="'+projects_uid+'" ywid="'+this.YWLZ_UID+'" glid1="'+this.JS_COM_CJK_UID+'" cl_level="'+this.CL_LEVEL+'" glid2="'+this.CLK_UID+'" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"></span>';
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
		getYwbz(dataXX);
}

function getYwbz(dataXX){
	var data = {
   			msg : dataXX
   		};
	$.ajax({
		url : "${pageContext.request.contextPath}/ywlz/buSpYwlzBzController?query",
		data : data,
		cache : false,
		async : false,
		dataType : "json",
		type : 'post',
		success : function(response) {
			var b = convertJson.string2json1(response.msg);
        	$("#ZS_NAME").val(b.response.data[0].ZS_NAME);
        	$("#ZS_CODE").val(b.response.data[0].ZS_CODE);
        	$("#LJR_NAME").val(b.response.data[0].LJR_NAME);
        	$("#LJR_DATE").val(b.response.data[0].LJR_DATE);
        	$("#LJR_PHONE").val(b.response.data[0].LJR_PHONE);
        	bzid=b.response.data[0].LZBZ_UID;
        	if(b.response.data[0].FSZCZ_DATE!=""){
        		$("#btnFfzcz").prop("disabled","disabled");
        		$("#ffzcz").html("已发放注册证");
        	}
		}
	});
}
var bzid="";
function btnFfzcz(){
	if(defaultJson.doInsertJson("${pageContext.request.contextPath}/ywlz/buSpYwlzBzController?updateFfzcz&lzbzid="+bzid, null)){
		$("#btnFfzcz").prop("disabled","disabled");
		$("#ffzcz").html("已发放注册证");
	}
}

function initBz(){
	var queryconditionXX = "{\"logic\":\"and\",\"fieldname\":\"t.SPYW_UID\",\"operation\":\"=\",\"value\":\""+spywid+"\",\"fieldtype\":'',\"fieldformat\":''}";
	var dataXX = "{querycondition: {conditions: [" +queryconditionXX+" ]},pages:{recordsperpage:200, currentpagenum:1, totalpage:0, countrows:0},orders:[{\"fieldname\":\"t.SPYW_UID\",\"order\":\"asc\"}]}";
	var data = {
   			msg : dataXX
   		};
	$.ajax({
		url : "${pageContext.request.contextPath}/spxx/buSpYwxxController/queryBz",
		data : data,
		cache : false,
		async : false,
		dataType : "json",
		type : 'post',
		success : function(response) {
			
			$("#bz_text").html(response);
		}
	});
}
</script>
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
	<p><input type="hidden" name="YWLZ_UID" id="YWLZ_UID"/></p>
	<input type="hidden" id="FSZCZ_DATE"/>
	<div class="row-fluid">
		<div class="B-small-from-table-autoConcise">
		</h4>
		<table border="1" width="100%" cellpadding="0" cellspacing="0" class="content">
		<tr>
				<td class="yw-title">
					<div class="span6"><h4><span id="yw_title">领取《安全监督受监通知书》</span></h4></div>
					<div class="pull-right">
					    <button  onclick="gcPrint()" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"> 建设工程安监通知书</button>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<ul id="ywcl_list">
						<form method="post" id="ywlzForm" > 
						<table class="B-table" width="100%">
	      	<tbody>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">证书名称</th>
				<td class="right-border bottom-border">
				    <input  id="FSZCZ_DATE" type="hidden" fieldname="FSZCZ_DATE" name="FSZCZ_DATE" value="0">
					<input readonly="readonly" class="span12" style="width:93%" id="ZS_NAME" type="text" value="安全监督受监通知书" fieldname="ZS_NAME" name="ZS_NAME">
				</td>
			</tr>
			<tr>
			    <th width="15%" class="right-border bottom-border text-right">证书编号</th>
				<td class="right-border bottom-border">
					<input readonly="readonly" class="span12" style="width:93%" id="ZS_CODE" type="text" fieldname="ZS_CODE" name="ZS_CODE">
				</td>
				<th width="15%" class="right-border bottom-border text-right">领件人</th>
				<td class="right-border bottom-border">
					<input readonly="readonly" class="span12" style="width:93%" id="LJR_NAME" type="text" fieldname="LJR_NAME" name="LJR_NAME">
				</td>
			</tr>
			<tr>
			    <th width="15%" class="right-border bottom-border text-right">联系电话</th>
				<td class="right-border bottom-border">
					<input readonly="readonly" class="span12" style="width:93%" id="LJR_PHONE" type="text"  fieldname="LJR_PHONE" name="LJR_PHONE">
				</td>
				<th width="15%" class="right-border bottom-border text-right">领件日期</th>
				<td class="right-border bottom-border">
				    <input readonly="readonly" style="width:85%" id="LJR_DATE" type="text" fieldname="LJR_DATE" name="LJR_DATE" fieldtype="date" fieldformat="YYYY-MM-DD" class="Wdate" >
				</td>
			</tr>
	      </tbody></table>
	      </form>
					</ul>
				</td>
			</tr>
		</table>
		<div style="height: 5px;"></div>
		<table border="1" width="100%" cellpadding="0" cellspacing="0" class="content">
			<tr>
				<td class="yw-title">
					<div class="span6"><h4><span id="cltitle">受理意见</span>&nbsp;&nbsp;&nbsp;</h4></div>
					<div class="pull-right">
					    <button id="btnFfzcz" onclick="btnFfzcz()" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><span id="ffzcz">发放注册证 </span></button>
					    <button id="btnPrint" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">发卡 </button>
					    <button id="btnPrint" onclick="jzPrint()" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"> 建筑注册登记表 </button>
					    <button id="btnPrint" class="btn" onclick="szPrint()" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"> 市政注册登记表</button>
					</div>
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
		<input type="hidden" name="queryResult" id="ywlzbzRes"/>
	</FORM>
</div>
</body>
</html>