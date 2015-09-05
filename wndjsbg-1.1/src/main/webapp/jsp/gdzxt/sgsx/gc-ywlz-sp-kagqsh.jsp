<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.params.SysPara.SysParaConfigureVO"%>
<%@ page import="com.ccthanking.framework.params.ParaManager"%>
<%@ page import="com.ccthanking.framework.Constants"%>
<%@ include file="/jsp/framework/common/taglibs.jsp" %>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base />
<title>审批业务流转实例审批</title>
<%
String type=request.getParameter("type");
SysParaConfigureVO syspara  = (SysParaConfigureVO) ParaManager.getInstance().getSysParameter(Constants.getString(Constants.FILEUPLOADOLD_ROOT_JS, "FILEUPLOADOLD_ROOT_WNDJSJS"));
String fileRoot = syspara.getSysParaConfigureParavalue1();
String spyw_uid = request.getParameter("spyw_uid");
String gongcheng_uid = request.getParameter("gongcheng_uid");
%>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/ywlz/css/default.css"> </LINK>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>	
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/kindeditor/plugins/code/prettify.css" />
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/kindeditor/kindeditor.js"></script>
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/kindeditor/lang/zh_CN.js"></script>

<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/ywlz/buSpYwlzController";
var controllernameWj= "${pageContext.request.contextPath }/bzwj/buSpBzwjController.do";
var controllernameWjCbfa= "${pageContext.request.contextPath }/sp/buSpywCbfaspController/";
var SgsxController="${pageContext.request.contextPath}/sgsx/SgsxProjectController";
var type ="<%=type%>";
var fileRoot="<%=fileRoot%>";
var spywid="<%=spyw_uid%>";
var rootpath="${pageContext.request.contextPath }";
//页面初始化
	$(function() {
		document.getElementById("divclhflb").style.display="none";
		init();
		
		$("#btnClose").bind("click", function(){
			var a=$(this).manhuaDialog.close();
		});	
	});
var wj_PROJECTS_UID;
var wj_JS_COMPANY_UID;
var ywlz_uid;
//页面默认参数
function init(){
       
       var projects_uid = "<%=gongcheng_uid%>";
	   
	 $.ajax({
		url : SgsxController+"/querySgYwlzId",
		data :{"gongcheng_uid":projects_uid,"spyw_uid":"<%=spyw_uid%>"},
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


		ywlz_uid =  ywlz_uid;
		wj_PROJECTS_UID=projects_uid;
		var spyw_uid = spywid;
		
		var spywmc ="开工前安全条件复查";
		$("#ywlzmcid").html("-"+spywmc);
		//列出审批意见信息
		var queryconditionXX = "{\"logic\":\"and\",\"fieldname\":\"YWLZ_UID\",\"operation\":\"=\",\"value\":\""+ywlz_uid+"\",\"fieldtype\":'',\"fieldformat\":''}";
		var queryconditionXX0 = "{\"logic\":\"and\",\"fieldname\":\"CHULI_JIEGUO\",\"operation\":\"!=\",\"value\":\""+0+"\",\"fieldtype\":'',\"fieldformat\":''}";
		
		var dataXX = "{querycondition: {conditions: [" +queryconditionXX+","+queryconditionXX0+" ]},pages:{recordsperpage:200, currentpagenum:1, totalpage:0, countrows:0},orders:[{\"fieldname\":\"BEGIN_DATE\",\"order\":\"asc\"}]}";
		//调用ajax插入
		defaultJson.doQueryJsonList("${pageContext.request.contextPath}/ywlz/buSpYwlzBzController?query", dataXX, spyjList);
		$("#YWLZ_UID_ID_HF").val(ywlz_uid);
		//geSpYwClList(ywlz_uid);
		
		getClxx(spyw_uid,ywlz_uid,projects_uid);
		getFiles();
		
//alert(type);
		//点击核发按钮进入时判断----
		if (type == "ywclhf") {
			document.getElementById("spyjtableid").style.display="none";
			document.getElementById("divid").style.display="none";
			
			$("#spyjtxhid").empty();
			geSpHfClList();			
		}else if(type == "ywlzsh"){
			$.ajax({
				url : "${pageContext.request.contextPath}/ywlz/buSpYwlzBzController?getYwLzBzVo&ywlzuid="+ywlz_uid,
			//	data : data1,
				cache : false,
				async : false,
				dataType : "json",
				type : 'post',
				success : function(response) {
					var resultdata = dealSpecialCharactor(response.msg);
					var res = convertJson.string2json1(resultdata);
					var lzbz=res.LZBZ_UID;
				//alert(lzbz);	
				    //不是该步骤参与者，直接把审批意见填写的table隐藏掉	
					if(lzbz==undefined){
						//$("#btnAddYj").attr("disabled", true);
						document.getElementById("spyjtableid").style.display="none";
						document.getElementById("divid").style.display="none";
						$("#spyjtxhid").empty();
					}else{
						$("#spyjtxhid").html(res.BZMC+"意见");
					}

					//默认同意
					$("input[name='CHULI_JIEGUO'][value='1']")[0].checked=true;

					$("#YWLZ_UID_ID").val(res.YWLZ_UID);//
					
					
				//alert(res.YWLZ_UID);	
					$("#SPBZ_UID_ID").val(res.SPBZ_UID);
					document.getElementById("kjhfdivid").style.display="none";//隐藏快捷回复
					$("#LZBZ_UID_ID").val(res.LZBZ_UID);
					
					if(res.SPBZ_UID!=undefined){
					$("#bzwj_SPBZ_UID").val(res.SPBZ_UID); //步骤uid 查询 文件
					$("#bzwj_SPBZ_UID2").val(res.SPBZ_UID); //步骤uid 查询 文件
					}
					$("#lz_LZBZ_UID").val(res.LZBZ_UID);  //业务流转步骤UID
					iswj();//文件初始化
					var sfcs=res.SFCS;//是否超时 1是     0 否
					if(sfcs==1){
						 $("#CHAOSHI_YUANYIN_ID").attr("check-type","required");
						 $("#csyytr").show();
					 }else{
						 $("#CHAOSHI_YUANYIN_ID").attr("check-type","");
						 $("#csyytr").hide();			 				
					}

				}
			});
		}
	}

//页面默认参数
function initwj(){
    if(spywid=="31"){   //31特殊处理
       $("#QYWLZ_UID").val(ywlz_uid);
	   $("#QLX").val("sg");
	   var msg=getCode();    //施工
	    if(msg!="0"){
	      $("#QJC").val("JYFWFJJTZD-SG");
        }
    
        $("#QLX").val("jl");
        var msg2=getCode();  //监理
        if(msg2!="0"){
	      $("#QJC").val("JYFWFJJTZD-JL");
        }
    
        if(msg!="0"&&msg2!="0"){//监理和施工 都有 
           var data = combineQuery.getQueryCombineData(queryForm,frmPostbzwj,bzwjDT);
           
        }else{
            
	       var data = combineQuery.getQueryCombineData(queryForm2,frmPostbzwj,bzwjDT);
	        if(msg=="0"&&msg2=="0"){//监理和施工 都没有 
	        $("#bzwjDT1").hide();
	        }else{}
	    }
	    defaultJson.doQueryJsonList(controllernameWj+"?query",data,bzwjDT);
    }else{ //其他业务
       var data = combineQuery.getQueryCombineData(queryForm,frmPostbzwj,bzwjDT);
       defaultJson.doQueryJsonList(controllernameWj+"?query",data,bzwjDT);
    }
    
  
}
function getCode() {
var msg=null;
		var data = combineQuery.getQueryCombineData(wjForm, frmPost31);
		var data1 = {
			msg : data
		};
		$.ajax( {
			url : controllernameWjCbfa + "query",
			data : data1,
			cache : false,
			async : false,
			dataType : "json",
			type : 'post',
			success : function(response) {
				$("#resultXML").val(response.msg);
				if(response.msg==0||response.msg==""){
				msg="0";
				}else{
				msg=response.msg;
				}
					//$("#BH").html();
			}
		});
		return msg;
	} 
function findByCompany(code,lx){  //
	var arr = new Array();
	$.ajax({
		url : controllernameWjCbfa + "findByCompany?lx="+lx+"&code="+code,
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
		
		    arr = response.split(",");
		  
		  
		}
	});
	 return  arr[0];
}

function iswj(){ //查询次步骤是否有文件
var data = combineQuery.getQueryCombineData(queryForm, frmPostbzwj);
		var data1 = {
			msg : data
		};
		$.ajax({
			url : controllernameWj + "?query",
			data : data1,
			cache : false,
			async : false,
			dataType : "json",
			type : 'post',
			success : function(response) {
			if(response.msg!=""&&response.msg!="0"){
			$("#bzwjDT1").show();
			   //防止 没有权限审核的人   spbz_UID为空       SELECT * FROM BU_SP_BZWJ t where  1=1
			   if($("#bzwj_SPBZ_UID"))
			     initwj();
			}else{
			
			}
			}
		});
}

//iframe形式展现
function geSpYwClList(ywlzuid){
//alert(ywlzuid);
	$.ajax({
		url : "${pageContext.request.contextPath}/ywlz/buSpYwlzBzController?geSpYwClList&ywlzuid="+ywlzuid,
	//	data : data1,
		cache : false,
		async : false,
		dataType : "json",
		type : 'post',
		success : function(response) {
			if(response!=""){
				var cls =  convertJson.string2json1(response.msg);
			//	alert(cls);
				$.each(cls,function(){
					var url=this.URL;
					//alert(this.URL);
					if(url!=null&&url!=''){
						document.getElementById("tbclframe").src = "${pageContext.request.contextPath}/"+this.URL+"/detail:"+this.YWLZ_UID;		
					}
					
					//i+1;
				})
			}
		}
	});	
}

function saveCLType(){
	var str="";
	for(var i=0;i<clsize;i++){
		str+=$("input[name=\"clradio"+i+"\"]:checked").attr('YWCLID')+":"+$("input[name=\"clradio"+i+"\"]:checked").prop('value');
		if(i<(clsize-1)){
			str+=",";
		}
	}
	
	var querycondition = "{\"YWLZ_UID\":\""+ywlz_uid+"\",\"CL_TYPE\":\""+str+"\"}";
	var data1 = defaultJson.packSaveJson(querycondition);
	 
	 var flag = defaultJson.doInsertJson("${pageContext.request.contextPath}/ywlz/buSpYwlzController?update", data1);
}

var clsize=0;
function getClxx(spyw_uid,ywlz_uid,projects_uid){
	$.ajax({
		url : "${pageContext.request.contextPath }/ywlz/buSpYwlzController/queryYwcl?ywlz_uid="+ywlz_uid,
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			if(response!=""){
				var cls =  convertJson.string2json1(response);
				clsize=cls.length;
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
					 //核发 
					 
                        
				    showHtml +="<span class=\"pull-right\"  style=\"margin-right: 20px\"> "+
					           "<input name=\"clradio"+i+"\" YWCLID=\""+this.YWCL_UID+"\" type=\"radio\" checked=\"\" value=\"1\"/>完成</input>&nbsp;&nbsp;&nbsp; "+
					           "<input name=\"clradio"+i+"\" YWCLID=\""+this.YWCL_UID+"\" type=\"radio\" checked=\"\" value=\"-1\"/>未完成</input>&nbsp;&nbsp;&nbsp;"+
					           "<input name=\"clradio"+i+"\" YWCLID=\""+this.YWCL_UID+"\" type=\"radio\" checked=\"\" value=\"0\"/>需整改</input>&nbsp;&nbsp;&nbsp;"+
					           "</span>";
					showHtml += "</li>";
				})
				$("#ywcl_list").append(showHtml);
			}
		}
	});
}


function showTbMsg(url,ywlzuid){
//	${pageContext.request.contextPath}/"+this.URL+"/detail:"+this.YWLZ_UID;	
	window.open("${pageContext.request.contextPath}/"+url+"/detail:"+ywlzuid,"填报信息查看");
}

function loadFiles(url){
	window.open(url,'文件下载');
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


function selectThly(obj){
    var thly=obj.value;
   // alert(thly);
    $("#CHULI_YIJIAN_ID").val(thly);
}


function addThly(){
	var spbzuid=$("#SPBZ_UID_ID").val();
	$(window).manhuaDialog({"title":"退回理由->添加","type":"text","content":"${pageContext.request.contextPath }/jsp/business/ywlz/bu-sp-addthly.jsp?type=insert&spbzuid="+spbzuid,"modal":"4"});	

}

function updateThly(){
	var spbzuid=$("#SPBZ_UID_ID").val();
	var obj=$("#thly_uid");
	obj.attr("src","T#bu_sp_bz_thly:liyou as c:liyou as t:SPBZ_UID='"+spbzuid+"' and ENABLED=1 order by SERIAL_NO asc ");
	obj.attr("kind","dic");
	obj.html('');
	reloadSelectTableDic(obj);
	
}


function checkValue(){
	 var jgvalues=$("input[name='CHULI_JIEGUO']:checked").val();
	// $("#STATUS_ID").val(status);
// alert(jgvalues);
	if(jgvalues=='-1'){
		document.getElementById("kjhfdivid").style.display="block";//显示快捷回复
		var spbzuid=$("#SPBZ_UID_ID").val();
		var obj=$("#thly_uid");
		obj.attr("src","T#bu_sp_bz_thly:liyou as c:liyou as t:SPBZ_UID='"+spbzuid+"' and ENABLED=1 order by SERIAL_NO asc ");
		obj.attr("kind","dic");
		obj.html('');
		reloadSelectTableDic(obj);		
	}else{
		document.getElementById("kjhfdivid").style.display="none";//隐藏快捷回复
	}
}

window.onunload = function(){ 
	var sfsx=$("#SFSX_ID").val();
	if(sfsx=='1'){
		$(window).manhuaDialog.getParentObj().$("#btnQuery").click();
	}
} 

		$(function() {		      					
				$("#btnAddYj").click(function() {
					var jg = $("input:radio[name='CHULI_JIEGUO']:checked").val();
					var chuli_yijian  = $("#CHULI_YIJIAN_ID").val();
					
					if(jg=="-1"&&(chuli_yijian==''||chuli_yijian==null)){
						alert("请填写退回理由！");
						return;
					}
					if(jg==1){
						saveCLType();
					}
					$("#btnAddYj").attr("disabled", true);
					 var lzbz_uid = $("#LZBZ_UID_ID").val();
					 var chaoshi_yuanyin=$("#CHAOSHI_YUANYIN_ID").val();
					 var chuli_jieguo=$("input[name='CHULI_JIEGUO']:checked").val();//审批结果
	
					// var chuli_yijian  = $("#CHULI_YIJIAN_ID").val();
	
					 var querycondition = "{\"LZBZ_UID\":\""+lzbz_uid+"\",\"CHULI_JIEGUO\":\""+chuli_jieguo+"\",\"CHULI_YIJIAN\":\""+chuli_yijian+"\",\"CHAOSHI_YUANYIN\":\""+chaoshi_yuanyin+"\"}";
					 var data1 = defaultJson.packSaveJson(querycondition);
					 
					 var flag = defaultJson.doInsertJson("${pageContext.request.contextPath}/ywlz/buSpYwlzBzController?update", data1);
					 if(flag){
						 //xAlert("信息提示","成功！");
						 //$("#clyjform").clearFormResult();
						//$("#btnAddYj").attr("disabled", false);
						$("#SFSX_ID").val("1");	//保存成功，复制，再关闭页面是自动刷新父窗体
					    var ywlz_uid_id= $("#YWLZ_UID_ID").val();
						//列出审批意见信息
						var queryconditionXX = "{\"logic\":\"and\",\"fieldname\":\"YWLZ_UID\",\"operation\":\"=\",\"value\":\""+ywlz_uid_id+"\",\"fieldtype\":'',\"fieldformat\":''}";
						var queryconditionXX0 = "{\"logic\":\"and\",\"fieldname\":\"CHULI_JIEGUO\",\"operation\":\"!=\",\"value\":\""+0+"\",\"fieldtype\":'',\"fieldformat\":''}";
	//					alert(queryconditionXX);
						var dataXX = "{querycondition: {conditions: [" +queryconditionXX+","+queryconditionXX0+","+queryconditionXX0+" ]},pages:{recordsperpage:200, currentpagenum:1, totalpage:0, countrows:0},orders:[{\"fieldname\":\"BEGIN_DATE\",\"order\":\"asc\"}]}";
					    //调用ajax插入
						defaultJson.doQueryJsonList("${pageContext.request.contextPath}/ywlz/buSpYwlzBzController?query", dataXX, spyjList);
						sfysp();//审批保存成调用查看是否该审批流转已经通过
					 }
					 
			});		
		});
		
		

	//判断是否已经审批完成
	function sfysp(){
		    var ywlz_uid_id= $("#YWLZ_UID_ID_HF").val();
			$.ajax({
				url : "${pageContext.request.contextPath }/ywlz/buSpYwlzController?sfysp&ywlzuid="+ywlz_uid_id,
			//	data : data1,
				cache : false,
				async : false,
				dataType : "json",
				type : 'post',
				success : function(response) {
					if(response!=""){
						var resultdata = dealSpecialCharactor(response.msg);			      								
						var res = convertJson.string2json1(resultdata);
						var status=res.STATUS;	
						var hfclcount=res.HFCLCOUNT;	      								
						if(status=='1'&&hfclcount!=0){
							
							geSpHfClList();
						}
					}
				}
			});	

		}

	var isfinish=true;//判断是否核发完成
	function geSpHfClList(){
		    document.getElementById("divclhflb").style.display="block";
			var ywlz_uid_id= $("#YWLZ_UID_ID_HF").val();
		//alert(ywlz_uid_id);
			//列出审批意见信息  status   
			var queryconditionXX = "{\"logic\":\"and\",\"fieldname\":\"YWLZ_UID\",\"operation\":\"=\",\"value\":\""+ywlz_uid_id+"\",\"fieldtype\":'',\"fieldformat\":''}";	
			var queryconditionXX0 = "{\"logic\":\"and\",\"fieldname\":\"CLSX\",\"operation\":\"=\",\"value\":\"H\",\"fieldtype\":'',\"fieldformat\":''}";
			var queryconditionXX1 = "{\"logic\":\"and\",\"fieldname\":\"STATUS\",\"operation\":\"=\",\"value\":\"1\",\"fieldtype\":'',\"fieldformat\":''}";
			var queryconditionXX1 = "{\"logic\":\"and\",\"fieldname\":\"SFYSC\",\"operation\":\"=\",\"value\":\"1\",\"fieldtype\":'',\"fieldformat\":''}";		
			var dataXX = "{querycondition: {conditions: [" +queryconditionXX+","+queryconditionXX0+","+queryconditionXX1+" ]},pages:{recordsperpage:200, currentpagenum:1, totalpage:0, countrows:0},orders:[{\"fieldname\":\"LINGJIAN_DATE\",\"order\":\"desc\"}]}";
		    //调用ajax插入
			defaultJson.doQueryJsonList("${pageContext.request.contextPath}/spxx/buSpLzhfController?query", dataXX, clhfList);	

			if(isfinish){
				var hftrid = document.getElementById("hftrid");
			     if(hftrid){
			    	 hftrid.style.display ='none';
			     }
			}	      					
		}

	function caozuoFun(obj){
		   var fileid=obj.FILEID;
		   var filename=obj.FILENAME;
		   var ywlzuid=obj.YWLZ_UID;
		   var clkuid=obj.CLK_UID;
		   var clsx=obj.CLSX;
		  // alert("----"+clsx);
		   if(fileid==null||fileid==''){
			   var lzhf_uid=obj.LZHF_UID;
			   var showHtml="";
			   if(lzhf_uid!=null&&lzhf_uid!=''){
				   showHtml +="<span>已核发</span> ";//处理有核发但是不产生核发文件的情况；
				 }else{
					isfinish=false;
					showHtml +="<span class='btn btn-link' id='hfSpan_"+clkuid+"' onclick='hfclFile(this);' >";
					showHtml +="<span>核发</span> ";
					showHtml +="</span>";
				 }			    
				return showHtml;	
		   	}else{	   		
		   		var furl=obj.FILEURL;
		   		var fileDir =fileRoot+"/"+furl.substring(0, furl.indexOf(filename));
				var downurl= rootpath+"/UploadOldServlet?getfile=" + filename+"&fileDir="+fileDir;	   				   	
		   		var showHtml ="<a href='"+downurl+"' class='btn btn-link' fileid='"+fileid+"' download='"+filename+"'>";
				showHtml +="<i class='icon-download-alt'></i>";
				showHtml +="<span>下载</span>";
				showHtml +="</a> "; /****/
				return showHtml;
			}	
		}
		
		
   var index=0;
   
   function queryScxmmjhsd(bzid){
    $.ajax({
			url : controllername + "?queryByLzbz/lzbzwjid="+$("#LZBZ_UID_ID").val()+"&bzwjid="+bzid,
			cache : false,
			async : false,
			dataType : "json",
			type : 'post',
			success : function(response) {
				window.open(controllerfile+"download2?path="+response,'文件下载');
			 }                                                      
		    });
   }
   

var LZBZ_WJ_UID;  //子页面 添加用 流转步骤文件_UID
function getLzbz_uid(){
return LZBZ_WJ_UID;
}

function getLzbzWj(obj){  //查询业务流转
$("#LZBZ_BZWJ_UID").val(obj.BZWJ_UID); 
$("#LZBZ_LZBZ_UID").val($("#LZBZ_UID_ID").val()); 
	var data = combineQuery.getQueryCombineData(lzbzwjqueryForm,frmPost);
			var data1 = { 	msg : data};
			$.ajax({
				url : controllernameWj + "?querylzbzwj",
				data : data1,
				cache : false,
				async : false,
				dataType : "json",
				type : 'post',
				success : function(response) {
					var res = dealSpecialCharactor(response.msg);
					$("#resultXML").val(response.msg);
					//alert($("#resultXML").val());
				//	var resultobj = defaultJson.dealResultJson(res);
					if(response.msg==""||response.msg=="0"){
					bu_sp_ywlz_bzwj_Add(obj);  //没有则添加 
					}	
					else{
					var resultobj = defaultJson.dealResultJson(res);
					  LZBZ_WJ_UID=resultobj.LZBZ_WJ_UID;
					}
				} 
			});
}
function bu_sp_ywlz_bzwj_Add(obj){   //添加审批业务流转步骤文件
	$("#lz_BZWJ_UID").val(obj.BZWJ_UID);
	$("#lz_CLMC").val(obj.WJNAME);
	var data = Form2Json.formToJSON(lzbzwjForm);
  	var data1 = defaultJson.packSaveJson(data);
		  //
	defaultJson.doInsertJson(controllernameWj + "?insertlzwj", data1);
	var json=$("#resultXML").val();//接受返回的数据 
	var tempJson=eval("("+json+")");
	var resultobj=tempJson.response.data[0];
	LZBZ_WJ_UID=resultobj.LZBZ_WJ_UID;
	
}




function hfclFile(obj){
		var ljr=$("#LINGJIAN_REN_ID").val();
//		alert("ljr---"+ljr);
		if(ljr==''||ljr==null){
			alert("领件人不能为空");
			return;
			}
		var ljrdh=	$("#LINGJIAN_PHONE_ID").val();
		if(ljrdh==''||ljrdh==null){
			alert("领件人电话不能为空");
			return;
			}
		
		while(obj.tagName.toLowerCase() != "tr"){
		    obj = obj.parentNode;
		    if(obj.tagName.toLowerCase() == "table")return null;
		   }
		obj.click();
		var rowJson = $("#clhfList").getSelectedRow();
		var data =convertJson.string2json1(rowJson);

	 	var codeid=$("#code"+data.YWCL_UID).val();
	 //	alert("codeid======"+codeid);
	 	
	 	if(codeid==''||codeid==null){
			alert("批件编号不能为空");
			return;
		}
	// var nameid="name"+data.YWCL_UID;
	 	var nameid= $("#name"+data.YWCL_UID).val();

	// 	alert("nameid======"+nameid);
	 	if(nameid==''||nameid==null){
			alert("批件名称不能为空");
			return;
		}

		var fzdate= $("#fzdate"+data.YWCL_UID).val();
		var yxqdate= $("#yxqdate"+data.YWCL_UID).val();
		if(fzdate==''||fzdate==null){
			alert("发证日期不能为空");
			return;
		}
		
		if(yxqdate==''||yxqdate==null){
			alert("截至有效期不能为空");
			return;
		}
		
//		提交核发
     	var url=data.URL;
     	if(url==''||url==null){
			alert("核发文件模版调取路径不存在，请配置！");
			return;
		} 

		var obj0 = new Object();
		obj0.YWLZ_UID = data.YWLZ_UID;
		obj0.YWCL_UID = data.YWCL_UID;
		obj0.PIJIAN_CODE = codeid;
		obj0.PIJIAN_NAME = nameid;
		obj0.LINGJIAN_REN =ljr;
		obj0.LINGJIAN_PHONE=ljrdh;
		obj0.FZ_DATE=fzdate;
		obj0.YXQ_DATE=yxqdate;
		obj0.CLK_UID=data.CLK_UID;//判断核发那个材料
		var data0 = JSON.stringify(obj0);
		var data1 = defaultJson.packSaveJson(data0);

		var hfurl=url.split(":"); 
		var isopen="";
		if(hfurl!=null&&hfurl.length>1){
			isopen=hfurl[1];
		}
		var flag=false;
		if(isopen!=""){//需要跳转到填写页面填写信息的
			var msg="YWLZ_UID="+data.YWLZ_UID+"&YWCL_UID="+data.YWCL_UID+"&PIJIAN_CODE="+codeid+"&PIJIAN_NAME="+nameid+"&LINGJIAN_REN="+ljr+"&LINGJIAN_PHONE="+ljrdh+"&FZ_DATE="+fzdate+"&YXQ_DATE="+yxqdate+"&CLK_UID="+data.CLK_UID;
			window.open("${pageContext.request.contextPath}/"+hfurl[1]+"?"+msg,"核发信息填写");
			flag=true;
		}else{
			flag = defaultJson.doInsertJson("${pageContext.request.contextPath}/"+hfurl[0]+"/ywlzclhf", data1);
		}	 
		if(flag){
			var spanid="#hfSpan_"+data.CLK_UID;//核发完成隐藏掉
			$(spanid).hide();
		}
	}

	function pijiancodeFun(obj){
		var pijiancode=obj.PIJIAN_CODE;
		
		//   alert("----"+pijiancode);	
		if(pijiancode==''){
			    var codeid="code"+obj.YWCL_UID;
			  //  alert(codeid);
				var showHtml ="<input type='text'  name='PIJIAN_CODE' id='"+codeid+"' check-type='required'  size=20 />";
				return showHtml;	
		  }	
	}

	function pijiannameFun(obj){
		var pijianname=obj.PIJIAN_NAME;
		var name=obj.CLMC;
		//   alert("----"+pijiancode);	
		if(pijianname==''){
				var nameid="name"+obj.YWCL_UID;
				var showHtml ="<input type='text'  name='PIJIAN_NAME' id='"+nameid+"' value="+name+" check-type='required'  size=20 />";
				return showHtml;	
		  }	
	}

	function fzdateFun(obj){
		var pijiancode=obj.PIJIAN_CODE;
		var name=obj.FZ_DATE;
	//	alert("----"+pijiancode);
		if(pijiancode==''){		
			var nameid="fzdate"+obj.YWCL_UID;// fieldtype='date' fieldformat='YYYY-MM-DD' class='Wdate' onClick='WdatePicker()' 
			var showHtml ="<input type='text'  style='width:100px;' name='FZ_DATE' id='"+nameid+"' value="+name+" size=20  fieldformat='YYYY-MM-DD' class='Wdate' onClick='WdatePicker()' />";
			return showHtml;	
		 }	
	}

	function yxqdateFun(obj){
		var pijiancode=obj.PIJIAN_CODE;
		var name=obj.YXQ_DATE;
	//	alert("----"+pijiancode);
		if(pijiancode==''){		
			var nameid="yxqdate"+obj.YWCL_UID;// fieldtype='date' fieldformat='YYYY-MM-DD' class='Wdate' onClick='WdatePicker()' 
			var showHtml ="<input type='text'  style='width:100px;' name='YXQ_DATE' id='"+nameid+"' value="+name+" size=20  fieldformat='YYYY-MM-DD' class='Wdate' onClick='WdatePicker()' />";
			return showHtml;	
		 }	
	}
</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">  
	<div class="B-small-from-table-autoConcise">
      <h4 class="title"><span id="ywlzmcid">审批业务流转实例审批</span>
      	<span class="pull-right">
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
			
      	</span>
      </h4>
	    <table border="1" width="100%" cellpadding="0" cellspacing="0" class="content">
			<tr>
				<td class="yw-title">
					<h4>材料信息</h4>
				</td>
			</tr>
			<tr>
				<td>
					<ul id="ywcl_list">
						
					</ul>
				</td>
			</tr>
		</table>
		<div style="height: 5px;"></div>
		<table id="YJ_LIST"  border="1" width="100%" cellpadding="0" cellspacing="0" class="content">
			<tr>
				<td class="yw-title">
					<h4>审批意见列表</h4>
				</td>
			</tr>
			<tr>
				<td>
					<div class="overFlowX">
				 		<table width="100%" class="table-hover table-activeTd B-table" id="spyjList" type="single" pageNum="2000"  noPage="true">
					    <thead>
					 		<tr style="display:blank">
					            <th fieldname="BZMC" style="width:200px;" colindex=1 tdalign="center" ">步骤名称</th>
					            <th fieldname="USERNAME" style="width:200px;" colindex=1 tdalign="center" >审批人</th>
					            <th fieldname="ACT_END_DATE_SV" style="width:250px;" colindex=2 tdalign="center" maxlength="200" >&nbsp;审批时间&nbsp;</th>
								<th fieldname="ZJIEGOU" colindex=2 tdalign="center" maxlength="200" >&nbsp;审批结果&nbsp;</th>
								<th fieldname="CHULI_YIJIAN" colindex=2 tdalign="center" maxlength="200" >&nbsp;审批意见&nbsp;</th>
					        </tr>
					    </thead>
					    <tbody></tbody>
					  </table>  
		       		</div>
				</td>
			</tr>
		</table>
	
	
	  <form method="post" id="wjForm">
						<table class="B-table" width="100%">
							<!--可以再此处加入hidden域作为过滤条件 -->
							<TR style="display: none;">
								<TD class="right-border bottom-border">
									<INPUT type="text" class="span12" kind="text" id="QYWLZ_UID" name="YWLZ_UID" fieldname="YWLZ_UID" value="" operation="=" />
									<INPUT type="text" class="span12" kind="text" id="QLX" name="LX" fieldname="LX" value="" operation="=" />
								</TD>
								<TD class="right-border bottom-border">
								</TD>
							</TR>
						</table>
					</form>
	  <table id="bzwjDT1" style="display: none;"  border="1" width="100%" cellpadding="0" cellspacing="0" class="content">
			<tr>
				<td class="yw-title">
					<h4>审批步骤文件</h4>
				</td>
			</tr>
			<tr>
				<td>
					<div class="overFlowX" >
				<table width="100%" class="table-hover table-activeTd B-table" id="bzwjDT" type="single" pageNum="2000" noPage="true">
						<thead>
						  <tr style="display:blank">
						  <th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
 			              <!--   <th fieldname="SPYWMC" colindex="1" tdalign="center" customfunction="doView">&nbsp;&nbsp;</th> --> 
				          <th fieldname="BZWJ_UID" colindex=0 tdalign="center" hidden >&nbsp;步骤文件_UID&nbsp;</th>
				          <th fieldname="CLK_UID" colindex=1 tdalign="center" hidden >&nbsp;材料UID&nbsp;</th>
				          <th fieldname="SPBZ_UID" colindex=2 tdalign="center" hidden>&nbsp;审批步骤UID&nbsp;</th>
				          <th fieldname="JC" colindex=3 tdalign="center" >&nbsp;简称&nbsp;</th>
				          <th fieldname="WJNAME" colindex=4 tdalign="center" maxlength="30" >&nbsp;文件名称&nbsp;</th>
				          <th fieldname="TMPWJNAME" colindex=5 tdalign="center" maxlength="30" >&nbsp;模版文件名&nbsp;</th>
				          <th fieldname="ENABLED" colindex=6 tdalign="center" >&nbsp;是否可用&nbsp;</th>
				          <th fieldname="OPTURL" colindex=7 tdalign="center" maxlength="30" customfunction="caozuo">&nbsp;打印&nbsp;</th>
						                	</tr>
						     </thead>
				  	<tbody></tbody>
					</table>  
					</div></td></tr></table>
			<div style="height: 5px;"></div>	
	      		<div class="overFlowX">
			     	<table  id="divid" border="1" width="100%" cellpadding="0" cellspacing="0" class="content">
						<tr>
							<td class="yw-title" >
								<h4 ><span id="spyjtxhid">审批意见填写</span>
								<span class="pull-right" > 
								<button id="btnAddYj" class="btn" type="button">保存</button>
								</span>
								</h4>
							</td>
						</tr>
						<tr>
							<td>
							 <div class="overFlowX">
							  <form id="spyjForm">
									<table class="B-table" width="100%"  id="spyjtableid">
									  			<input type="hidden"  id="LZBZ_UID_ID" fieldname="LZBZ_UID" name="LZBZ_UID"/>
												<input type="hidden"  id="YWLZ_UID_ID" fieldname="YWLZ_UID" name="YWLZ_UID"/>
												<input type="hidden"  id="SPBZ_UID_ID" fieldname="SPBZ_UID" name="SPBZ_UID"/>
												<input type="hidden"  id="SFSX_ID"  name="SFSX_ID"/>
										<tr >
											<th width="8%" class="right-border bottom-border text-right">是否同意</th>
											<td width="20%" class="right-border bottom-border" >
												<input class="span12" style="width:15%" id="CHULI_JIEGUO_ID" type="radio" onclick="javascript:checkValue(this)" check-type="required" fieldname="CHULI_JIEGUO" name = "CHULI_JIEGUO" kind="dic" src="SHYJ_SY" />			
				
											</td>
											<td width="62%" class="right-border bottom-border" >
												<div  id="kjhfdivid">
													<select id="thly_uid" onchange="selectThly(this)" class="span12" style="width:20%" name="thly_uid" fieldname="thly_uid" kind="dic" src="T#bu_sp_bz_thly:liyou as c:liyou as t"  defaultMemo="--选择退回理由--"></select>
													<button class="btn btn-link" style="width:20%"  type="button" onclick="addThly()" ><i class="icon-edit"></i>新增退回理由</button>
												</div>
											</td>
										</tr>
										<tr >
											<th width="8%" class="right-border bottom-border text-right">审批意见
											</th>
											<td width="92%" class="right-border bottom-border" colspan="2">
												<textarea class="span12" rows="4" id="CHULI_YIJIAN_ID"  fieldname="CHULI_YIJIAN" check-type="required" name="CHULI_YIJIAN"></textarea>						
											</td>
										</tr>
										 <tr id="csyytr">
									        <th width="8%" class="right-border bottom-border text-right">超时原因</th>
									        <td colspan="2" class="bottom-border right-border" >
									        	<textarea class="span12" rows="2"   maxlength="3500" id="CHAOSHI_YUANYIN_ID" check-type="required"  fieldname="CHAOSHI_YUANYIN" name="CHAOSHI_YUANYIN"></textarea>
									        </td>
								        </tr>
										
									</table>
								</form>	
			     <form method="post" id="queryForm">
							<INPUT type="hidden" class="span12" kind="text" id="bzwj_SPBZ_UID" value="0" fieldname="SPBZ_UID"  operation="="/>
						
				</form>
				<form method="post" id="queryForm2">
							<INPUT type="hidden" class="span12" kind="text" id="bzwj_SPBZ_UID2" value="0" fieldname="SPBZ_UID"  operation="="/>
						<INPUT type="hidden" class="span12" kind="text" id="QJC" value="0" fieldname="JC"  operation="="/>
				</form>
				<form method="post" id="lzbzwjqueryForm"  >
	                   <input   type="hidden" id="LZBZ_LZBZ_UID" fieldname="LZBZ_UID" name="LZBZ_UID" operation="="/>
	                   <input   type="hidden" id="LZBZ_BZWJ_UID" fieldname="BZWJ_UID" name="BZWJ_UID" operation="="/>
     	        </form>
     	        
     	        
     	      
					  </div>							
					</td>					
					</tr>
					</table>
					
					
			</form>
			
			     	
			</div>
			
			<div style="height: 5px;"></div>
			<div class="overFlowX" id="divclhflb">
					<table  id="divid" border="1" width="100%" cellpadding="0" cellspacing="0" class="content">
						<tr>
							<td class="yw-title" >
								<h4 ><span >材料核发</span></h4>
							</td>
						</tr>
						<tr>
							<td>
							  <div  class="overFlowX">
							 	 <table class="B-table" width="100%" >
									<tr id="hftrid">
										<input type="hidden"  id="YWLZ_UID_ID_HF" fieldname="YWLZ_UID" name="YWLZ_UID"/>
										<th width="8%" class="right-border bottom-border text-right">
											领件人							
										</th>
										<td width="23%" class="right-border bottom-border">
											<input type="text" name="LINGJIAN_REN" id="LINGJIAN_REN_ID" maxlength="20" fieldname="LINGJIAN_REN" check-type="required"  size=100 />						
										</td>
										<th width="8%" class="right-border bottom-border text-right">
											联系电话							
										</th>
										<td width="23%" class="right-border bottom-border">
											<input type="text" name="LINGJIAN_PHONE" id="LINGJIAN_PHONE_ID" maxlength="20" fieldname="LINGJIAN_PHONE" check-type="required"  size=20  />						
										</td>
									</tr>
								   <div class="overFlowX">
						            <table width="100%" class="table-hover table-activeTd B-table" id="clhfList" type="single" pageNum="2000" noPage="true">
						                <thead>
						                	<tr style="display:blank">
						                	    <th fieldname="CLMC" style="width:50px;" colindex=1 tdalign="left" >材料名称</th>		                	    
						                	    <th fieldname="PIJIAN_NAME"  CustomFunction="pijiannameFun" noprint="true"  style="width:40px;" colindex=3 tdalign="left" >批件名称</th>
						                		<th fieldname="PIJIAN_CODE"  CustomFunction="pijiancodeFun" noprint="true" style="width:40px;" colindex=2 tdalign="left" >批件编号</th>
						                		<th fieldname="FZ_DATE"  CustomFunction="fzdateFun" noprint="true" style="width:20px;" colindex=9 tdalign="center" >发证日期</th>
						                		<th fieldname="YXQ_DATE"  CustomFunction="yxqdateFun" noprint="true" style="width:20px;" colindex=10 tdalign="center" >截至有效期</th>
						                		<th fieldname="LINGJIAN_REN" style="width:20px;" colindex=4 tdalign="center" >领件人</th>
						                		<th fieldname="LINGJIAN_PHONE" colindex=5 tdalign="center" maxlength="200" style="width:20px;">&nbsp;联系电话&nbsp;</th>
												<th fieldname="LINGJIAN_DATE" colindex=6 tdalign="center" maxlength="200" style="width:20px;">&nbsp;领件时间&nbsp;</th>
												<th fieldname="USER_NAME" colindex=7 tdalign="center" maxlength="200" style="width:20px;">&nbsp;发放人&nbsp;</th>
												
												<th fieldname="YWCL_UID" colindex=8 CustomFunction="caozuoFun" noprint="true" style="width:30px;">&nbsp;操作&nbsp;</th>
						                	</tr>
						                </thead>
						              	<tbody></tbody>
						           </table>
						         </div>
								</table>
							  
							  </div>
							
							</td>
						
						</tr>
					</table>
					 <form method="post" id="lzbzwjForm"  > 
					     <input type="hidden" id="lz_LZBZ_UID" fieldname="LZBZ_UID" name = "LZBZ_UID"/>
	  	                 <input type="hidden" id="lz_BZWJ_UID" fieldname="BZWJ_UID" name = "BZWJ_UID"/>
	  	                 <input type="hidden" id="lz_JC" fieldname="JC" name = "JC"/>
	  	                  <input type="hidden"  fieldname="ALPRINT" value="0" name = "ALPRINT"/>
	  	                 <input type="hidden" id="lz_CLMC" fieldname="CLMC" name = "CLMC"/>
	  	                 <input type="hidden" id="lz_SAVFILEPATHNAME" fieldname="SAVFILEPATHNAME" name = "SAVFILEPATHNAME"/>
					 </form>
					
				
			</div>
    </div>
	<input type="hidden" name="txtXML" id="wjCbfa">
   </div>
  </div>
  
 <jsp:include page="/jsp/file_upload/fileuploadold_config_forYwlz.jsp" flush="true" />
   <div align="center">

		
		<!-- 步骤文件  -->
		<FORM name="frmPostbzwj" method="post" style="display:none"
			target="_blank">
			<!--系统保留定义区域-->
			<input type="hidden" name="queryXML" id="queryXML"> 
			<input type="hidden" name="txtXML" id="txtXML">
			<input type="hidden" name="resultXML" id="resultXML">

			<!--传递行数据用的隐藏域-->
			<input type="hidden" name="rowData">
		</FORM>
		<!-- 步骤文件  31-->
		<FORM name="frmPost31" method="post" style="display:none"
			target="_blank">
			<!--系统保留定义区域-->
			<input type="hidden" name="queryXML" id="queryXML"> 
			<input type="hidden" name="txtXML" id="txtXML">
			<input type="hidden" name="resultXML" id="resultXML">

			<!--传递行数据用的隐藏域-->
			<input type="hidden" name="rowData">
		</FORM>
		<FORM name="frmPost" method="post" style="display:none"
			target="_blank">
			<!--系统保留定义区域-->
			<input type="hidden" name="queryXML" id="queryXML"> 
			<input type="hidden" name="txtXML" id="txtXML">
			<input type="hidden" name="resultXML" id="resultXML">

			<!--传递行数据用的隐藏域-->
			<input type="hidden" name="rowData">
		</FORM>
	</div>
</body>
<script>
</script>
</html>