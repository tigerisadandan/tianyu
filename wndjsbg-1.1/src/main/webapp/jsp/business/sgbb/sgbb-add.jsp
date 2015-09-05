<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>施工报备-维护</title>
<%
	String type=request.getParameter("type");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/sgbb/sgBbController.do";
var controllernameGctype= "${pageContext.request.contextPath }/sgbb/gcTypeController.do";
var controllernamePersonZs = "${pageContext.request.contextPath }/person/SgZhengshuController.do";
var type ="<%=type%>";
var stemp = 0;
var array_tj = ["GUIMO","CENGSHU","GAODU","KUADU","SHENDU","JINE","ZHONGLIANG"];
//页面初始化
$(function() {
	init();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,sgBbList);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"?query",data,sgBbList);
	});
	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
		if(confirm("报备完成后项目信息不能修改,您确定完成?")){
			if($("#sgBbForm").validationButton())
			{
<%--				check_level();--%>
				if(checkRyxx()&&!ryxzPd()){
				   	check_level();
				}else{
					return ;
				}
			}else{
				requireFormMsg();
			  	return;
			}
		}
	});

	//下一步
	$("#btnNext").click(function(){
		if(beforeNext()){
			check_zzxx();			//验证资质信息
			
		}else{
			$("#cwXX").show();
		}
	});
	//上一步
	$("#btnReturn").click(function(){

		if(confirm("返回上一步已设定人员信息将会清空,您确定返回?")){
			closeError();
		
			$("#xmxx_title").show();
			$("#xmxx_card").show();
			$("#ryxx_title").hide();
			$("#ryxx_card").hide();
			clearRyxx();			//清空已设置的人员信息
		}
	})
	
	//删除人员
	$("#btnRemoveRy").click(delSelect);
	//添加人员
	$("#btnAddRy").click(addTR);
		
	//取消
    $("#btnClose1").click(function() {
<%--       window.close();--%>
   		$(window).manhuaDialog.close();
    });
    $("#btnClose2").click(function() {
<%--        window.close();--%>
    	$(window).manhuaDialog.close();
	});
});
function init(){
	clearTj();
	$("#ryxx_title").hide();
	$("#ryxx_card").hide();
	$("#cwXX").hide();
}
//加载资质等级	
function loadZlx(demo){
	if($("#GC_TYPE_UID").val()==''||$("#GC_TYPE_UID").val()=='请选择'){
		$("#GC_SUB_TYPE_UID").html("<option value=''>请选择</option>");
		return ;
	}
	$.ajax({
		url : controllernameGctype+"?queryTypelist&pType="+$("#GC_TYPE_UID").val(),
		data : {},
		cache : false,
		async :	false,
		dataType : "text",  
		type : 'post',
		success : function(response) {
			
			var obj = eval('(' + response + ')');
			var obj1 = eval('(' + obj.msg + ')');
			var showHtml = "";
			$(obj1).each(function(){
				showHtml += "<option value='"+this.GC_TYPE_UID+"'";
				showHtml += ">"+this.NAMES+"</option>";
			});
			$("#GC_SUB_TYPE_UID").empty();
			$("#GC_SUB_TYPE_UID").append(showHtml);
			loadTjs($("#GC_SUB_TYPE_UID"));
		}
	});
}
var hasTjs = "";
function loadTjs(demo){
	$("#GC_SUB_TYPE_UID").val()
	if($("#GC_SUB_TYPE_UID").val()==''){
		return;
	}
	clearTj();
	var data = Form2Json.formToJSON(sgBbForm);
	  	//组成保存json串格式
	var data1 = defaultJson.packSaveJson(data);
	$.ajax({
		url : controllername+"?queryBbtj&ck_type=ck_gclx",
		data : data1,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			if(response.msg!=""){
				var obj = eval('('+response.msg+')');
				$.each(obj,function(){
					$("#"+this.BASIS_CODE+"_TR").show();
					$("#"+this.BASIS_CODE+"_TR").find("th").eq(0).text(this.BASIS_NAME);
				})
			}
			
		}
	});
}

function check_zzxx(){
	
	$("#pname").text($("#PROJECT_NAME").val());
	$("#gclx").text($("#GC_TYPE_UID").find("option:selected").text()+"-"+$("#GC_SUB_TYPE_UID").find("option:selected").text());
	$("#cbxz").text($("#CB_XINGZHI").find("option:selected").text());

	
	var data = Form2Json.formToJSON(sgBbForm);
	  	//组成保存json串格式
	var data1 = defaultJson.packSaveJson(data);
	$.ajax({
		url : controllername+"?queryBbtj&ck_type=ck_zzdj",
		data : data1,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var txt = response.msg;
			if(txt=='1'){
				check_qyzz();
			}else{
				
				$("#cwXX").show();	
				$("#errorInfo").html("");
				$("#errorInfo").append("<tr><td>根据项目的规模，贵企业没有承接此项目的相应资质！</td></tr>");
			}
		}
	});
}
//验证企业是否有资质
function check_qyzz(){
	$("#cwXX").hide();
	var data = Form2Json.formToJSON(sgBbForm);
	  	//组成保存json串格式
	var data1 = defaultJson.packSaveJson(data);
	$.ajax({
		url : controllername+"?queryBbtj&ck_type=ck_rys",
		data : data1,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
<%--			var obj = eval('('+response.msg+')'); --%>
<%--			var txt = obj.zdpp;--%>
			$("#RyList").append(response.msg);
			$("#xmxx_title").hide();
			$("#xmxx_card").hide();
			$("#ryxx_title").show();
			$("#ryxx_card").show();
			getZrs();
<%--			if(txt!=null&&txt!=""){--%>
<%--				//使用 split(',')分割需要的数据内容的字符串--%>
<%--				//alert(txt);--%>
<%--				loadRs(txt,true);--%>
<%--				--%>
<%--				$("#xmxx_title").hide();--%>
<%--				$("#xmxx_card").hide();--%>
<%--				$("#ryxx_title").show();--%>
<%--				$("#ryxx_card").show();--%>
<%--				getIndex();--%>
<%--			}else{--%>
<%--				$("#cwXX").show();--%>
<%--				$("#errorInfo").html("");--%>
<%--				$("#errorInfo").append("<tr><td>根据项目的规模，贵企业没有承接此项目的相应资质！</td></tr>");--%>
<%--			}--%>
<%--			var fs = obj.zjfs;--%>
<%--			for(i=0;i<parseInt(fs);i++){--%>
<%--				var zjrs = obj.zjrs;--%>
<%--				loadRs(zjrs,false);--%>
<%--			}--%>
			getIndex();
		}
	});
}
//验证项目经理是否有资质的
function check_level(){
	var personUid = $("#xmjl").eq(0).find("#SG_PERSON_UID").val();
	
	var data = Form2Json.formToJSON(sgBbForm);
  	//组成保存json串格式
	var data1 = defaultJson.packSaveJson(data);
	$.ajax({
		url : controllername+"?queryBbtj&ck_type=ck_level&person_uid="+personUid,
		data : data1,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var txt = response.msg;
			if(txt=='allow'){
				var data = Form2Json.formToJSON(sgBbForm);
				//组成保存json串格式
				var data1 = defaultJson.packSaveJson(data);
				var flag = defaultJson.doInsertJson(controllername + "?insert", data1);
				if(flag){
					alert("报备已完成!");
					$(window).manhuaDialog.close();
				}
			}else{
				$("#cwXX").show();	
				$("#errorInfo").html("");
				$("#errorInfo").append("<tr><td>根据项目的规模，项目经理没有负责此项目的相应资质！</td></tr>");
			}
		}
	});
}
function loadRs(txt,returnZs){
	var zrs = 0;
	var pds = txt.split(",");
	for(i=0;i<pds.length;i++){
		if(pds[i]!=""){
			var array = pds[i].split(":");
			for(n=0;n<parseInt(array[1]);n++){
				
				var cloneNew = $("#cloneTR").clone(true);
				//显示该条tr
				$(cloneNew).removeAttr("style");
				$(cloneNew).removeAttr("id");
				$("#RyList").append(cloneNew);
				//设置为必要人员
				$(cloneNew).find("#MUST_Y").val("Y");
				var option = "";
				$(cloneNew).find("#doSelect").remove();
				if(array[0]=="xmfzr"){
					$(cloneNew).attr("id","xmjl")
					option = "<option value='19' selected>项目负责人</option>";
				}else if(array[0]=="xmjsfzr"){
					option = "<option value='20' selected>项目技术负责人</option>";
				}else if(array[0]=="sgy"){
					option = "<option value='21'selected>施工员</option>";
				}else if(array[0]=="aqy"){
					option = "<option value='22' selected>安全员</option>";
				}else if(array[0]=="zjy"){
					option = "<option value='23' selected>质检员</option>";
				}else if(array[0]=="zly"){
					option = "<option value='24' selected>资料员</option>";
				}else if(array[0]=="xxgly"){
					option = "<option value='25' selected>信息管理员</option>";
				}
				if(array[0]!="xxgly"&&array[0]!="zly"){
					zrs += 1;
				}
				//设置岗位的select只有当前一个岗位
				$(cloneNew).find("td").eq(2).find("#GANGWEI_UID").html(option);
				//去除可选删除的选择框
				$(cloneNew).find("td").eq(2).find("input:checkbox").remove();
			}
			//"xmfzr:1,xmjsfzr:1,sgy:3,aqy:3,zjy:2,zly:0,xxgly:0"
			
		}
	}
	if(returnZs){
		$("#sgzry").text(zrs);
	}
	
}
//重新选择工程子类型时清空之前的参数填写痕迹
function clearTj(){
	for(i=0;i<array_tj.length;i++){
		$("#"+array_tj[i]).val("");
		$("#"+array_tj[i]+"_TR").hide();
	}
}
function getIndex(){
	$("#RyList").find("tbody").find("tr").each(function(i,demo){
		$(demo).find("#xh").text(i-1);
	})
}

function getZrs(){
	var zrs = 0;
	$("#RyList").find("tbody").find("tr").each(function(i,demo){
		if($(this).find("#MUST_Y")!=null&&$(this).find("#MUST_Y").val()=="Y"){
			zrs ++;
		}
	})
	$("#sgzry").text(zrs);
	$("#SGRY_NUMS").val(zrs);
}
//返回到上一步时 ,清空所有人员设置
function clearRyxx(){
	$("input:checkbox[name='allSelect']").checked=false;
	$.each($("#RyList").find("tbody").find("tr"),function(i,demo){
		if(i>1){
			$(demo).remove();
		}
	})
}
var currTR = null;			//选择人员时点击的行对象
var gw_now = "";
//点击选择人员信息
function selectRyxx(demo){
	currTR = $(demo).closest("tr");
	gw_now = $(currTR).find("#GANGWEI_UID").val();
	if(gw_now==null||gw_now==""||gw_now=="请选择"){
		alert("请先选择添加的岗位！");
		return;
	}
<%--	window.showModalDialog("ryPage.jsp",window,"dialogWidth:800px,dialogHeight:700px");--%>
	$(window).manhuaDialog({"title":"新增报备>人员选择","type":"text","content":"${pageContext.request.contextPath }/jsp/business/sgbb/sgbb-ryxz.jsp","modal":"2"});
}
function selectXmbd(demo){
<%--	window.showModalDialog("ryPage.jsp",window,"dialogWidth:800px,dialogHeight:700px");--%>
	$(window).manhuaDialog({"title":"新增报备>项目(标段)选择","type":"text","content":"${pageContext.request.contextPath }/jsp/business/sgbb/sgbb-gcxz.jsp","modal":"2"});
}
//选择人员后的回调
function loadRyxx(demo){
	var tempjson = convertJson.string2json1(demo);
	$(currTR).find("#SG_NAME").val(tempjson.PERSON_NAME);
	$(currTR).find("#SG_PERSON_UID").val(tempjson.SG_PERSON_UID);

	getPersonInfo(tempjson.SG_PERSON_UID,$(currTR).find("#GANGWEI_UID").val());
	
	
	var age = 0;
	if(tempjson.SHENFENZHENG!=null&&tempjson.SHENFENZHENG!=""){
		age = getAgeByShenfenz(tempjson.SHENFENZHENG);
	}
	$(currTR).find("#AGE").val(age);
	$(currTR).find("#ZHICHENG_NAME").val(tempjson.ZHICHENG_NAME);
	$(currTR).find("#SHENFENZHENG").val(tempjson.SHENFENZHENG);
	$(currTR).find("#MOBILE").val(tempjson.PHONE);
}
function loadXmxx(demo){
	var tempjson = convertJson.string2json1(demo);
	$("#PROJECT_NAME").val(tempjson.PRO_NAME);
	$("#PROJECT_CODE").val(tempjson.BD_CODE);
	$("#KB_DATE").val(tempjson.GG_BEGIN_DATE);
	$("#ZBGG_ID").val(tempjson.ZBGG_ID);
}
var jl_level = 0;//当前项目经理的资质等级
function getPersonInfo(person_uid,gw_uid){
	$.ajax({
		url : controllernamePersonZs+"?queryZsByGw&person_uid="+person_uid+"&gw_uid="+gw_uid,
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			if(response.msg!=""&&response.msg!="0"&&response.msg!="[]"){
				var text =  response.msg ;
				var obj1 = text.split(":");
				$(currTR).find("#ZHENGSHU_NAME").val(obj1[0]);
				$(currTR).find("#ZHENGSHU_CODE").val(obj1[1]);
				$(currTR).find("#ZHUANYE").val(obj1[2]);
				$(currTR).find("#ZHENGSHU_DATE").val(obj1[3]);
			}
		}
	});
}
//根据生日获取年龄
function getAge(birthday){
	var myDate = new Date();
	var s1 = birthday;
	s1 = new Date(s1);

	var years= myDate.getYear() - s1.getYear(); 
	var month= myDate.getMonth() - s1.getMonth();
	if(month>=6){
		years += 1;
	}
	return years;
	
}
//全选\反选
function doAllSelect(demo){
	var flag = $(demo).prop("checked");
	$("input:checkbox[name='doSelect']").each(function(i){
		if(i>0){
			$(this).prop("checked",flag);
		}
	})
}
//删除人员
function delSelect(){
	$.each($("#RyList").find("input:checkbox[name='doSelect']:checked"),function(){
		$(this).closest("tr").remove();
	})
	getIndex();
}
//添加人员
function addTR(){
	var cloneNew = $("#cloneTR").clone(true);
	//显示该条tr
	$(cloneNew).removeAttr("style");
	$(cloneNew).removeAttr("id");
	$("#RyList").append(cloneNew);
	//设置为必要人员
	$(cloneNew).find("#MUST_Y").val("N");
	getIndex();
}
function fbfs(){
	$("#PROJECT_NAME").val("");
	$("#PROJECT_CODE").val("");
	$("#KB_DATE").val("");
	var bid = $("#BID_TYPE").val()
	
	if(bid=='2'){
		$("#choseXM").hide();
		$("#PROJECT_NAME").removeAttr("disabled");
<%--		$("#PROJECT_CODE").removeAttr("disabled");--%>
<%--		$("#KB_DATE").removeAttr("disabled");--%>
	}else{
		$("#choseXM").show();
		$("#PROJECT_NAME").attr("disabled",true);
		$("#PROJECT_CODE").attr("disabled",true);
		$("#KB_DATE").attr("disabled",true);
	}
}
function closeError(){
	$("#cwXX").hide();
	$("#errorInfo").html("");
}


function getAgeByShenfenz(UUserCard){
	//获取出生日期 
	//UUserCard.substring(6, 10) + "-" + UUserCard.substring(10, 12) + "-" + UUserCard.substring(12, 14); 
	//获取性别 www.jbxue.com
	if (parseInt(UUserCard.substr(16, 1)) % 2 == 1) { 
	//男 
	} else { 
	//女 
	} 
	//获取年龄 
	var myDate = new Date(); 
	var month = myDate.getMonth() + 1; 
	var day = myDate.getDate();
	var age = myDate.getFullYear() - UUserCard.substring(6, 10) - 1; 
	if (UUserCard.substring(10, 12) < month || UUserCard.substring(10, 12) == month && UUserCard.substring(12, 14) <= day) { 
		age++; 
	} 
	if(age<0){
		age = 0;
	}
	return age;
}



<%-- 值验证 --%> 
function beforeNext(){
	$("#errorInfo").html("");
	var flag = true;
	if($("#BID_TYPE").val()==''){
		flag = false;
		$("#errorInfo").append("<tr><td>请选择发包方式!</td></tr>");
	}else if($("#BID_TYPE").val()=='0'||$("#BID_TYPE").val()=='1'){
		
		if($("#PROJECT_CODE").val()==''){
			flag = false;
			$("#errorInfo").append("<tr><td>请输入招标工程项目编号!</td></tr>");
		}
	}
	
	if($("#PROJECT_NAME").val()==''){
		flag = false;
		$("#errorInfo").append("<tr><td>请输入项目(标段)名称!</td></tr>");
	}
	
	if($("#CB_XINGZHI").val()==''){
		flag = false;
		$("#errorInfo").append("<tr><td>请选择承包类型!</td></tr>");
	}
	if($("#GC_SUB_TYPE_UID").val()==''||$("#GC_SUB_TYPE_UID").val()=='请选择'){
		flag = false;
		$("#errorInfo").append("<tr><td>请选择工程类型!</td></tr>");
	}
	for(i=0;i<array_tj.length;i++){
		var status = $("#"+array_tj[i]+"_TR").css("display");
		if(status!="none"){
			if($("#"+array_tj[i]).val()==""){
				flag = false;
				$("#errorInfo").append("<tr><td>请输入"+$("#"+array_tj[i]+"_TR").find("th").eq(0).text()+"!</td></tr>");
			}
		}
	}
	return flag;
}


function beforeSubmit(){
	flag = true;
	var bxRy = "";
	$($("#RyList tr")).each(function(){
		if($(this).find("#MUST_Y")!=null){
			if($(this).find("#MUST_Y").val()=="Y"){
				if($(this).find("#SG_NAME").val()==""){
					flag = false;
					$("#errorInfo").append("<tr><td>请选择"+$(this).find("#GANGWEI_UID").find("option:selected").text().text()+"!</td></tr>");
				}
			}
		}
	})
	
}
function checkRyxx(){
	$("#errorInfo").html("");
	var flag = true;
	var zongrs = 0;
	var kongrs = 0;
	var bygw_krs = 0;
	var zdpbrs = 0;
	$("#RyList tr").each(function(i){
		if(i>1){
			zongrs += 1;
			if($(this).find("#SG_PERSON_UID").val()==""){
				kongrs += 1;
				if($(this).find("#MUST_Y").val()=="Y"){
					bygw_krs += 1;
				}
			}
			if($(this).find("#MUST_Y").val()=="Y"){
				zdpbrs += 1;
			}
			
			
		}
	});
	if(kongrs>0){
		flag = false;
		$("#errorInfo").append("<tr><td>有"+kongrs+"个岗位未选择人员！</td></tr>");
	}
	if(kongrs>0){
		flag = false;
		$("#errorInfo").append("<tr><td>不满足施工人员配备最低标准，应至少配备"+zdpbrs+"人！</td></tr>");
	}
	if(!flag){
		$("#cwXX").show();
	}
	return flag ;
}
function onlyNum(){
	if(!(event.keyCode==46)&&!(event.keyCode==8)&&!(event.keyCode==37)&&!(event.keyCode==39))
		if((event.keyCode!=9)&&(event.keyCode!=46)&&!((event.keyCode>=48&&event.keyCode<=57)||(event.keyCode>=96&&event.keyCode<=105))){
		    event.returnValue=false;
		  }
  
}
function ryxzPd(){
	var flag = false;
<%--	$("#cwXX").show();	--%>
	$("#errorInfo").html("");
	var errorMsg = "";
	$("#RyList tr").each(function(i,demo){
		if($(demo).find("#GANGWEI_UID").val()!="24"&&$(demo).find("#GANGWEI_UID").val()!="25"){
			$("#RyList tr").each(function(n,demo2){
				if($(demo2).find("#GANGWEI_UID").val()!="24"&&$(demo2).find("#GANGWEI_UID").val()!="25"){
					if($(demo).find("#SG_PERSON_UID").val()==$(demo2).find("#SG_PERSON_UID").val()&&n!=i){
						flag = true;
						if($(demo).find("#GANGWEI_UID").val()=="19"&&$(demo2).find("#GANGWEI_UID").val()!='25'){
							if(errorMsg.indexOf("项目经理不能兼任信息管理员以外的岗位")==-1){
								errorMsg += "项目经理不能兼任信息管理员以外的岗位!<br/>";
							}
						}
						if(errorMsg.indexOf($(demo).find("#SG_NAME").val()+"兼任了多个岗位")==-1){
							errorMsg += $(demo).find("#SG_NAME").val()+"兼任了多个岗位!<br/>";
						}
						
					}
				}
			})
		}
	})
	if(flag){
		$("#errorInfo").append("<tr><td>"+errorMsg+"</td></tr>");
		$("#cwXX").show();
	}
	return flag;
}
</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="B-small-from-table-autoConcise">
			<form method="post" id="queryForm"  >
				<table class="B-table" width="100%">
			      <!--可以再此处加入hidden域作为过滤条件 -->
			      	<TR  style="display:none;">
				        <TD class="right-border bottom-border">
				        	<input type="text" id="SGBB_UID"/>
				        </TD>
						<TD class="right-border bottom-border"></TD>
			        </TR>
				</table>
			</form>
		</div>
		<div style="height:5px;"></div>
		<div class="B-small-from-table-autoConcise">
			<div>
				<table id="cwXX" width="100%">
					<tr>
						<td>错误信息</td><td align="right"><a href="javascript:void(0)" onclick="closeError()">x</a></td>
					</tr>
					<tr>
						<td style="border: 3px #990000 solid;background: #999999" colspan="2">
							<table id="errorInfo">
							</table>
						</td>
					</tr>
				</table>
			</div>
			<form method="post" id="sgBbForm"  >
	     	 	<h4 class="title" id="xmxx_title">项目信息
	     	 		<span class="pull-right" id="btnSave_span">
			      		<button id="btnClose1" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
						<button id="btnNext" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">下一步</button>
					</span>
	     	 	</h4>
				<table class="B-table" width="100%" id="xmxx_card">
			        <tr>
						<th width="8%" class="right-border bottom-border text-right">发包方式</th>
			       	 	<td class="bottom-border right-border" width="23%" colspan="3">
			         		<select id="BID_TYPE" style="width:35%" placeholder="必填" check-type="required" class="span6" onchange="fbfs()" name="BID_TYPE" fieldname="BID_TYPE"  kind="dic" src="BID_TYPE"  defaultMemo="请选择"></select>
			       	 	</td>
			       	 </tr>
			       	 <tr>
			         	<th width="8%" class="right-border bottom-border text-right">项目(标段)名称</th>
			       		<td class="bottom-border right-border" colspan="3">
			         		<input class="span12" style="width:55%" id="PROJECT_NAME" type="text" placeholder="必填" fieldname="PROJECT_NAME" name = "PROJECT_NAME"  disabled />
			          		<button id="choseXM" class="btn btn-link"  type="button" onclick="selectXmbd()"><i class="icon-edit"></i></button>
			          		<b style="color:blue;">请点击进行招标项目选择，直接发包项目请直接输入</b>
			         	</td>
			        </tr>
			        <tr>
						<th width="8%" class="right-border bottom-border text-right">招标工程项目编号</th>
						<td width="17%" class="right-border bottom-border">
							<input id="PROJECT_CODE" class="span12" check-type="maxlength" maxlength="36" name="PROJECT_CODE" fieldname="PROJECT_CODE" type="text" disabled/>
							<input id="ZBGG_ID" class="span12" name="ZBGG_ID" fieldname="ZBGG_ID" type="hidden"/>
						</td>
						<th width="8%" class="right-border bottom-border text-right">开标时间</th>
						<td width="17%" class="right-border bottom-border">
							<input id="KB_DATE"   style="width:80%" fieldtype="date" fieldformat="YYYY-MM-DD" name="KB_DATE" fieldname="KB_DATE" class="Wdate" type="text" onClick="WdatePicker()" disabled/>
						</td>
					</tr>
					<tr>
						<th width="8%" class="right-border bottom-border text-right">承包类型</th>
						<td width="17%" class="right-border bottom-border" colspan="3">
							<select id="CB_XINGZHI" style="width:35%" class="span6"  name="CB_XINGZHI" fieldname="CB_XINGZHI"  kind="dic" src="CB_XINGZHI"  defaultMemo="请选择"></select>
						</td>
					</tr>
					<tr>
				        <th width="8%" class="right-border bottom-border text-right">工程类型</th>
				        <td colspan="3" class="bottom-border right-border" colspan="3">
				        	<select  id="GC_TYPE_UID"  class="span12" style="width:35%" name="GC_TYPE_UID" fieldname="GC_TYPE_UID" onchange="loadZlx(this)" kind="dic" src="T#GC_TYPE: GC_TYPE_UID as c:NAMES as t:P_TYPE_UID is NULL and TAGS = 'S' order by SERIAL_NO"  defaultMemo="请选择"></select>
							<select  id="GC_SUB_TYPE_UID"  class="span12" style="width:25%" name="GC_SUB_TYPE_UID" fieldname="GC_SUB_TYPE_UID" onchange="loadTjs(this)" value=""><option>请选择</option></select>
				        </td>
			        </tr>
			        
			        <tr id="GUIMO_TR">
			        	<th width="8%" class="right-border bottom-border text-right">建设面积</th>
			       		<td class="bottom-border right-border" width="15%" colspan="3">
			         		<input class="span12" onkeydown="onlyNum()" style="width:40%;text-align:right;" id="GUIMO" type="number" fieldname="GUIMO" name = "GUIMO"  />(平方米)
			         	</td>
			        </tr>
			        <tr id="CENGSHU_TR">
			        	<th width="8%" class="right-border bottom-border text-right">层数</th>
			       		<td class="bottom-border right-border" colspan="3">
			         		<input class="span12" onkeydown="onlyNum()" style="width:40%;text-align:right;" id="CENGSHU" type="number" fieldname="CENGSHU" name = "CENGSHU"  />(层)
			         	</td>
			        </tr>
			        <tr id="GAODU_TR">
			        	<th width="8%" class="right-border bottom-border text-right">高度</th>
			       		<td class="bottom-border right-border" colspan="3">
			         		<input class="span12" onkeydown="onlyNum()" onkeydown="onlyNum()" style="width:40%;text-align:right;" id="GAODU" type="number" fieldname="GAODU" name = "GAODU"  />(米)
			         	</td>
			        </tr>
			        <tr id="KUADU_TR">
			        	<th width="8%" class="right-border bottom-border text-right">跨度</th>
			       		<td class="bottom-border right-border" colspan="3">
			         		<input class="span12" onkeydown="onlyNum()" style="width:40%;text-align:right;" id="KUADU" type="number" fieldname="KUADU" name = "KUADU"  />(米)
			         	</td>
			        </tr>
			        <tr id="SHENDU_TR">
			        	<th width="8%" class="right-border bottom-border text-right">深度</th>
			       		<td class="bottom-border right-border" colspan="3">
			         		<input class="span12" onkeydown="onlyNum()" style="width:40%;text-align:right;" id="SHENDU" type="number" fieldname="SHENDU" name = "SHENDU"  />(米)
			         	</td>
			        </tr>
			        <tr id="JINE_TR">
			        	<th width="8%" class="right-border bottom-border text-right">单项合同额</th>
			       		<td class="bottom-border right-border" colspan="3">
			         		<input class="span12" onkeydown="onlyNum()" style="width:40%;text-align:right;" id="JINE" type="number" fieldname="JINE" name = "JINE"  />(万元)
			         	</td>
			        </tr>
			        <tr id="ZHONGLIANG_TR">
			        	<th width="8%" class="right-border bottom-border text-right">重量</th>
			       		<td class="bottom-border right-border" width="15%" colspan="3">
			         		<input class="span12" style="width:40%;text-align:right;" id="ZHONGLIANG" type="number" fieldname="ZHONGLIANG" name = "ZHONGLIANG"  />(吨)
			         	</td>
			        </tr>
			        <tr>
				        <th width="8%" class="right-border bottom-border text-right">备注</th>
				        <td colspan="3" class="bottom-border right-border" colspan="3">
				        	<textarea class="span12" rows="2" id="DESCRIBE" check-type="maxlength" maxlength="4000" fieldname="DESCRIBE" name="DESCRIBE"></textarea>
				        </td>
			        </tr>
			        <tr>
			        	<th width="8%" class="right-border bottom-border text-right"></th>
				        <td class="bottom-border right-border text-left"" colspan="3">
							<input class="span12"  id="SGRY_NUMS" type="hidden" fieldname="SGRY_NUMS" name = "SGRY_NUMS"  />
				        	    <h5><b style="color:blue;">项目规模都必须填写数字。请如实填写项目规模，若由于规模填写错误造成废标，后果自负。</b></h5>
				        </td>
			        </tr>
				</table>
	      
				<h4 class="title" id="ryxx_title">设置项目部人员
					<span class="pull-right">
						<button id="btnClose2" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
				  		<button id="btnAddRy" class="btn"  type="button">添加岗位</button>
						<button id="btnRemoveRy" class="btn"  type="button">删除岗位</button>
						<button id="btnReturn" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">上一步</button>
						<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">完成</button>
					</span> 
				</h4>
				<table class="B-table" width="100%" id="ryxx_card">
			        <tr>
						<th  class="right-border bottom-border text-right" width="10%">工程名称</th>
						<td class="right-border bottom-border text-left" id="pname" width="30%"></td>
						<th  class="right-border bottom-border text-right" width="10%">承包性质</th>
						<td class="right-border bottom-border text-left" id="cbxz" width="30%"></td>
			        </tr>
			        <tr>
						<th class="right-border bottom-border text-right">工程类型</th>
						<td class="right-border bottom-border text-left" id="gclx"></td>
						<th  class="right-border bottom-border text-right">施工人员配备最低标准</th>
						<td class="right-border bottom-border text-left" id="sgzry">
						</td>
			        </tr>
			        <tr>
						<td width="17%" class="right-border bottom-border" colspan="4">
							<table class="B-table" width="100%" id="RyList" style="overflow: scroll;">
								<tr>
									<th style="width:1%">&nbsp;<label class="checkbox inline"><input type="checkbox" id="allSelect" onclick="doAllSelect(this)" name="allSelect"/></label>&nbsp;</th>
									<th style="width:3%">&nbsp;序号&nbsp;</th>
									<th style="width:10%">&nbsp;岗位&nbsp;</th>
									<th style="width:12%">&nbsp;姓名&nbsp;</th>
									<th style="width:10%">&nbsp;证书名称&nbsp;</th>
									<th style="width:10%">&nbsp;证书编号&nbsp;</th>
									<th style="width:10%">&nbsp;注册专业&nbsp;</th>
									<th style="width:10%">&nbsp;证书有效期&nbsp;</th>
									<th style="width:3%">&nbsp;年龄&nbsp;</th>
									<th style="width:8%">&nbsp;职称&nbsp;</th>
									<th style="width:15%">&nbsp;身份证号码&nbsp;</th>
									<th style="width:8%">&nbsp;联系电话&nbsp;</th>
								</tr>
									<tr id="cloneTR" style="display: none;">
										<td class="text-center"><label class="checkbox inline"><input type="checkbox" id="doSelect" name="doSelect"/></label></td>
										<td class="text-center"><input id="MUST_Y" style="width:99%" class="span12"  name="MUST_Y" fieldname="MUST_Y" type="hidden" /><span id="xh"></span></td>
										<td>
											<select id="GANGWEI_UID" class="span12" style="width:99%" name="GANGWEI_UID" fieldname="GANGWEI_UID" kind="dic" src="T#gangwei: GANGWEI_UID as c:NAMES as t:TAGS='S' AND GANGWEI_UID!=19"></select>
											<input id="SG_PERSON_UID" style="width:99%" class="span12"  name="SG_PERSON_UID" fieldname="SG_PERSON_UID" type="hidden" />
										</td>
										<td><input id="SG_NAME" style="width:70%" class="span12"  name="SG_NAME" fieldname="SG_NAME" type="text" readonly="readonly"/><button class="btn btn-link"  type="button" onclick="selectRyxx(this)"><i class="icon-edit"></i></button></td>
										<td><input id="ZHENGSHU_NAME" style="width:99%" class="span12"  name="ZHENGSHU_NAME" fieldname="ZHENGSHU_NAME" type="text" readonly="readonly"/></td>
										<td><input id="ZHENGSHU_CODE" style="width:99%" class="span12"  name="ZHENGSHU_CODE" fieldname="ZHENGSHU_CODE" type="text" readonly="readonly"/></td>
										<td><input id="ZHUANYE" style="width:99%" class="span12"  name="ZHUANYE" fieldname="ZHUANYE" type="text" readonly="readonly"/></td>
										<td><input id="ZHENGSHU_DATE" style="width:99%" class="span12"  name="ZHENGSHU_DATE" fieldname="ZHENGSHU_DATE" type="text" readonly="readonly"/></td>
										<td><input id="AGE" style="width:99%" class="span12"  name="AGE" fieldname="AGE" type="text" readonly="readonly"/></td>
										<td><input id="ZHICHENG_NAME" style="width:99%" class="span12"  name="ZHICHENG_NAME" fieldname="ZHICHENG_NAME" type="text" readonly="readonly"/></td>
										<td><input id="SHENFENZHENG" style="width:99%" class="span12"  name="SHENFENZHENG" fieldname="SHENFENZHENG" type="text" readonly="readonly"/></td>
										<td><input id="MOBILE" style="width:99%" class="span12"  name="MOBILE" fieldname="MOBILE" type="text" readonly="readonly"/></td>
									</tr>
							</table>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>
<div align="center">
	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
        <input type="hidden" name="queryXML" id = "queryXML">
    	<input type="hidden" name="txtXML" id = "txtXML">
        <input type="hidden" name="txtFilter"  order="desc" fieldname = "t.LRSJ" id = "txtFilter">
        <input type="hidden" name="resultXML" id = "resultXML">
        <input type="hidden" name="currRy" id = "currRy">
        <!--传递行数据用的隐藏域-->
        <input type="hidden" name="rowData">
	</FORM>
</div>
</body>
<script>
</script>
</html>