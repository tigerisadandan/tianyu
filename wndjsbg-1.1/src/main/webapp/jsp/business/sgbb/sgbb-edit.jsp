<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>施工报备-维护</title>
<%
	String id = request.getParameter("id");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/sgbb/sgBbController.do";
var controllernameGctype= "${pageContext.request.contextPath }/sgbb/gcTypeController.do";
var controllernamePersonZs = "${pageContext.request.contextPath }/person/SgZhengshuController.do";
var controllernameRy = "${pageContext.request.contextPath }/sgbb/sgbbRyController.do";
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
		if($("#sgBbForm").validationButton())
		{
<%--			check_level();--%>
				if(checkRyxx()&&!ryxzPd()){
				   	check_level();
				}else{
					return ;
				}
		}else{
			requireFormMsg();
		  	return;
		}
	});

	//下一步
	$("#btnNext").click(function(){
		
		check_zzxx();			//验证资质信息
	});
	//上一步
	$("#btnReturn").click(function(){
		$("#xmxx_title").show();
		$("#xmxx_card").show();
		$("#ryxx_title").hide();
		$("#ryxx_card").hide();
		clearRyxx();			//清空已设置的人员信息
	})
	
	//删除人员
	$("#btnRemoveRy").click(delSelect);
	//添加人员
	$("#btnAddRy").click(addTR);
		
	//取消
    $("#btnClear_Bins").click(function() {
<%--       window.close();--%>
		$(window).manhuaDialog.close();
    });
    $("#btnRemove").click(function(){
        if(!confirm("您确定要删除报备信息吗,删除之后不可恢复!")){
            return;
        }
		$.ajax({
			url : controllername+"?delete&sgbb_uid="+$("#QID").val(),
			data : {},
			cache : false,
			async :	false,
			dataType : "json",  
			type : 'post',
			success : function(response) {
				alert("删除成功!");
				$(window).manhuaDialog.close();
			}
		})
		
	})
});
function init(){
	$("#cwXX").hide();
	$("#QID").val("<%=id%>");
	setFormValues();
	getPtype();
	$(":input").each(function(i){
		   $(this).attr("readonly", "true");
	});
	builderRyList();
}
//重新选择工程子类型时清空之前的参数填写痕迹
function clearTj(){
	for(i=0;i<array_tj.length;i++){
		$("#"+array_tj[i]).val("");
		$("#"+array_tj[i]+"_TR").hide();
	}
	
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
var zrsLoad = 0;
//修改情况下,读取表单内容
function setFormValues(){
	var data = combineQuery.getQueryCombineData(queryForm,frmPost);
	var data1 = {
		msg : data
	};
	$.ajax({
		url : controllername+"?query&type=detail",
		data : data1,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			$("#resultXML").val(response.msg);
			var resultobj = defaultJson.dealResultJson(response.msg);
			$("#sgBbForm").setFormValues(resultobj);
			$("#sgry_num").text(resultobj.SGRY_NUMS)
			if(resultobj.SGRY_NUMS!=null&&resultobj.SGRY_NUMS!=""){
				zrsLoad = parseInt(resultobj.SGRY_NUMS);

			}
		}
	});
}
var currTR = null;			//选择人员时点击的行对象
var gw_now = "";

//选择人员后的回调
function loadRyxx(demo){
	var tempjson = convertJson.string2json1(demo);
	$(currTR).find("#SG_NAME").val(tempjson.PERSION_NAME);
	$(currTR).find("#SG_PERSON_UID").val(tempjson.SG_PERSON_UID);

	getPersonInfo(tempjson.SG_PERSON_UID,$(currTR).find("#GANGWEI_UID").val());
	
	
	var age = getAge(tempjson.BIRTHDAY);
	$(currTR).find("#AGE").val(age);
	$(currTR).find("#ZHICHENG_NAME").val(tempjson.ZHICHENG_NAME);
	$(currTR).find("#SHENFENZHENG").val(tempjson.SHENFENZHENG);
	$(currTR).find("#MOBILE").val(tempjson.PHONE);
}
//根据生日获取年龄
function getAge(birthday){
	var myDate = new Date();
	var s1 = birthday;

	s1 = s1.replace(/-/g, "/");  
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
	var flag = true;
	if(!$(demo).checked){
		flag = false;
	}	
	$("input:checkbox[name='doSelect']").each(function(){
		$(this).checked = flag;
	})
}
//删除人员
function delSelect(){
	$.each($("#RyList").find("input:checkbox[name='doSelect']"),function(){
		if($(this).checked){
			$(this).closest("tr").remove();
		}
	})
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
}
//点击选择人员信息
function selectRyxx(demo){
	currTR = $(demo).closest("tr");
	gw_now = $(currTR).find("#GANGWEI_UID").val();
	if(gw_now==null||gw_now==""||gw_now=="请选择"){
		alert("请先选择添加的岗位！");
		return;
	}
<%--	window.showModalDialog("ryPage.jsp",window,"dialogWidth:800px,dialogHeight:700px");--%>
	$(window).manhuaDialog({"title":"修改报备>人员选择","type":"text","content":"${pageContext.request.contextPath }/jsp/business/sgbb/sgbb-ryxz.jsp","modal":"2"});
}

function builderRyList(){
	$.ajax({
		url : controllernameRy+"?queryBbry&bb_uid="+$("#QID").val(),
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var obj1 = eval('(' + response.msg + ')');
			chushiDengji = "";
			$(obj1).each(function(){
				var cloneNew = $("#cloneTR").clone(true);
				//显示该条tr
				$(cloneNew).removeAttr("style");
				
				$("#RyList").append(cloneNew);
				//设置为必要人员
				currTR = cloneNew;
				$(cloneNew).find("#MUST_Y").val(this.MUST_Y);
				if(this.MUST_Y=="Y"){
					$(cloneNew).find("#doSelect").remove();
				}
				if(parseInt(this.XUHAO)-zrsLoad<3){
					$(cloneNew).find("#doSelect").remove();
				}
<%--				$(cloneNew).find("#SFBG").val("N");--%>
				$(cloneNew).find("#SGBB_RY_UID").val(this.SGBB_RY_UID);
				$(cloneNew).find("#SG_PERSON_UID").val(this.SG_PERSON_UID);
<%--				$(cloneNew).find("#SG_PERSON_UID_YL").val(this.SG_PERSON_UID);--%>
				$(cloneNew).find("#SG_NAME").val(this.SG_NAME);
				$(cloneNew).find("#ZHENGSHU_NAME").val(this.ZHENGSHU_NAME);
				$(cloneNew).find("#ZHENGSHU_CODE").val(this.ZHENGSHU_CODE);
				$(cloneNew).find("#ZHUANYE").val(this.ZHUANYE);
				$(cloneNew).find("#ZHENGSHU_DATE").val(this.ZHENGSHU_DATE);
				$(cloneNew).find("#AGE").val(this.AGE);
				$(cloneNew).find("#ZHICHENG_NAME").val(this.ZHICHENG_NAME);
				$(cloneNew).find("#SHENFENZHENG").val(this.SHENFENZHENG);
				$(cloneNew).find("#MOBILE").val(this.MOBILE);
				var option = "<option value='"+this.GANGWEI_UID+"' selected>"+this.NAMES+"</option>";
				
				if(this.GANGWEI_UID=="19"){
					$(cloneNew).attr("id","xmjl");
				}else{
					$(cloneNew).removeAttr("id");
				}
				//设置岗位的select只有当前一个岗位
				$(cloneNew).find("td").eq(2).find("#GANGWEI_UID").html(option);
				//去除可选删除的选择框
				$(cloneNew).find("td").eq(2).find("input:checkbox").remove();
				getIndex();
			});
		}
	});
}
function getIndex(){
	$("#RyList").find("tbody").find("tr").each(function(i,demo){
		$(demo).find("#xh").text(i-1);
	})
}
function jianzhiCheck(sg_person_uid){
	var msg = "";
	$("#RyList tr").each(function(){
		if($(this).find("#SG_PERSON_UID")!=null&&$(this).find("#MUST_Y")=="Y"&&$(this).find("#SG_PERSON_UID")!=currTR){
			if(sg_person_uid==$(this).find("#SG_PERSON_UID").val()){
				var msg = "false:"+"当前人员已被选择担任"+$(currTR).find("#GANGWEI_UID").find("option:selected").text()+"岗位,不能兼职";
				return false
			}
		}
	})
	return msg;
}
//选择人员后的回调
function loadRyxx(demo){
	var tempjson = convertJson.string2json1(demo);
	$(currTR).find("#SG_NAME").val(tempjson.PERSON_NAME);
	$(currTR).find("#SG_PERSON_UID").val(tempjson.SG_PERSON_UID);


<%--	if($(currTR).find("#SG_PERSON_UID_YL").val()!=""){--%>
<%--		if($(currTR).find("#SG_PERSON_UID_YL").val()!=$(currTR).find("#SG_PERSON_UID").val()){--%>
<%--			$(cloneNew).find("#SFBG").val("Y");--%>
<%--		}else{--%>
<%--			$(cloneNew).find("#SFBG").val("N");--%>
<%--		}--%>
<%--	}--%>
	
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
function getJlLevel(person_uid,gw_uid){
	$.ajax({
		url : controllernamePersonZs+"?queryZsByGw&person_uid="+person_uid+"&gw_uid="+gw_uid,
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			if(response.msg!=""&&response.msg!="0"&&response.msg!="[]"){
					jl_level = parseInt(obj1[0].ZHENGSHU_LEVEL);
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
		$("#PROJECT_CODE").removeAttr("disabled");
		$("#KB_DATE").removeAttr("disabled");
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
function getPtype(){
	$.ajax({
		url : controllernameGctype+"?queryPType&uid="+$("#GC_SUB_TYPE_UID").val(),
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var obj = eval('('+response.msg+')');
			$("#P_NAMES").val(obj[0].NAMES); 
		}
	});
	
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
//验证项目经理是否有资质的
function check_level(){
	$("#cwXX").hide();
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
			if(txt==''){
				$("#cwXX").show();
				$("#errorInfo").html("");
				$("#errorInfo").append("<tr><td>根据项目的规模，项目经理没有负责此项目的相应资质！</td></tr>");
			}else if(parseInt(txt)>jl_level){
				$("#cwXX").show();
				$("#errorInfo").html("");
				$("#errorInfo").append("<tr><td>根据项目的规模，项目经理没有负责此项目的相应资质！</td></tr>");
			}else{
				var data = Form2Json.formToJSON(sgBbForm);
				//组成保存json串格式
				var data1 = defaultJson.packSaveJson(data);
				var flag = defaultJson.doInsertJson(controllernameRy + "?update", data1);
				if(flag){
					clearRyxx();
					builderRyList();
				}
			}
		}
	});
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
				        	<input id="QID" class="span12" check-type="maxlength" maxlength="36" name="SGBB_UID" fieldname="t.SGBB_UID" type="text" operation="="/>
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
			      		<button id="btnClear_Bins" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
			      		<button id="btnRemove" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">删除</button>
			      		<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">保存</button>
					</span>
	     	 	</h4>
				<table class="B-table" width="100%" id="xmxx_card">
				<input id="SGBB_UID" class="span12" check-type="maxlength" maxlength="36" name="SGBB_UID" fieldname="SGBB_UID" type="hidden" />
			        <tr>
						<th width="8%" class="right-border bottom-border text-right">发包方式</th>
			       	 	<td class="bottom-border right-border" width="23%" colspan="3">
			         		<input id="BID_TYPE" class="span12" style="width:30%" name="BID_TYPE_SV" fieldname="BID_TYPE" type="text" />
			       	 	</td>
			       	 </tr>
			       	 <tr>
			         	<th width="8%" class="right-border bottom-border text-right">项目(标段)名称</th>
			       		<td class="bottom-border right-border" colspan="3">
			         		<input class="span12" style="width:40%" id="PROJECT_NAME" type="text" fieldname="PROJECT_NAME" name = "PROJECT_NAME"  />
			         	</td>
			        </tr>
			        <tr>
						<th width="8%" class="right-border bottom-border text-right">招标工程项目编号</th>
						<td width="17%" class="right-border bottom-border">
							<input id="PROJECT_CODE" class="span12" name="PROJECT_CODE" fieldname="PROJECT_CODE" type="text" />
						</td>
						<th width="8%" class="right-border bottom-border text-right">开标时间</th>
						<td width="17%" class="right-border bottom-border">
							<input id="KB_DATE" class="span12" name="KB_DATE" fieldname="KB_DATE" type="text" />
						</td>
					</tr>
					<tr>
						<th width="8%" class="right-border bottom-border text-right">承包类型</th>
						<td width="17%" class="right-border bottom-border" colspan="3">
							<input id="CB_XINGZHI" class="span12" style="width:40%" name="CB_XINGZHI" fieldname="CB_XINGZHI" type="text" />
						</td>
					</tr>
					<tr>
				        <th width="8%" class="right-border bottom-border text-right">工程类型</th>
				        <td colspan="3" class="bottom-border right-border" colspan="3">
				        	<input id="GC_SUB_TYPE_UID" class="span12" placeholder="必填" check-type="required" check-type="maxlength" maxlength="36" name="GC_SUB_TYPE_UID" fieldname="GC_SUB_TYPE_UID" type="hidden" />
				     		<input id="P_NAMES" style="width:40%" class="span12" placeholder="必填" check-type="required" check-type="maxlength" maxlength="36" name="P_NAMES" fieldname="P_NAMES" type="text" />
							<input id="GC_SUB_TYPE_NAMES" style="width:30%" class="span12" placeholder="必填" check-type="required" check-type="maxlength" maxlength="36" name="GC_SUB_TYPE_UID" fieldname="NAMES" type="text" />
				        </td>
			        </tr>
			        
			        <tr id="GUIMO_TR">
			        	<th width="8%" class="right-border bottom-border text-right">建设面积</th>
			       		<td class="bottom-border right-border" width="15%" >
			         		<input class="span12" style="width:40%" id="GUIMO" type="number" fieldname="GUIMO" name = "GUIMO"  /><b>(平方米)</b>
			         	</td>
			         	<th width="8%" class="right-border bottom-border text-right">层数</th>
			       		<td class="bottom-border right-border" >
			         		<input class="span12" style="width:40%" id="CENGSHU" type="number" fieldname="CENGSHU" name = "CENGSHU"  /><b>(层)</b>
			         	</td>
			        </tr>
			        <tr id="GAODU_TR">
			        	<th width="8%" class="right-border bottom-border text-right">高度</th>
			       		<td class="bottom-border right-border" >
			         		<input class="span12" style="width:40%" id="GAODU" type="number" fieldname="GAODU" name = "GAODU"  /><b>(米)</b>
			         	</td>
			         	<th width="8%" class="right-border bottom-border text-right">跨度</th>
			       		<td class="bottom-border right-border" >
			         		<input class="span12" style="width:40%" id="KUADU" type="number" fieldname="KUADU" name = "KUADU"  /><b>(米)</b>
			         	</td>
			        </tr>
			        <tr id="SHENDU_TR">
			        	<th width="8%" class="right-border bottom-border text-right">深度</th>
			       		<td class="bottom-border right-border" >
			         		<input class="span12" style="width:40%" id="SHENDU" type="number" fieldname="SHENDU" name = "SHENDU"  /><b>(米)</b>
			         	</td>
			         	<th width="8%" class="right-border bottom-border text-right">单项合同额</th>
			       		<td class="bottom-border right-border" >
			         		<input class="span12" style="width:40%" id="JINE" type="number" fieldname="JINE" name = "JINE"  /><b>(万元)</b>
			         	</td>
			        </tr>
			        <tr id="ZHONGLIANG_TR">
			        	<th width="8%" class="right-border bottom-border text-right">重量</th>
			       		<td class="bottom-border right-border" width="15%" >
			         		<input class="span12" style="width:40%" id="ZHONGLIANG" type="number" fieldname="ZHONGLIANG" name = "ZHONGLIANG"  /><b>(吨)</b>
			         	</td>
			         	<th width="8%" class="right-border bottom-border text-right">最低配备人员数</th>
			       		<td class="bottom-border right-border" width="15%" >
			         		<span id="sgry_num">
			         			
			         		</span>
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
				        	    <h5><b style="color:blue;">工程类型和建设规模不能修改，若需修改请删除此报备信息并重新报备。</b></h5>
				        	    <h5><b style="color:blue;">开标过程中会对已报备信息进行核查，请不要随意删除以避免符合性检查不通过。</b></h5>
				        </td>
			        </tr>
				</table>
	      
				<h4 class="title" id="ryxx_title">设置项目部人员
					<span class="pull-right">
				  		<button id="btnAddRy" class="btn"  type="button">添加人员</button>
						<button id="btnRemoveRy" class="btn"  type="button">删除人员</button>
					</span> 
				</h4>
				<table class="B-table" width="100%" id="ryxx_card">
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
									<tr id="cloneTR" style="display:none;">
										<td class="text-center"><label class="checkbox inline"><input type="checkbox" id="doSelect" name="doSelect"/></label></td>
										<td class="text-center"><input id="MUST_Y" style="width:99%" class="span12" check-type="maxlength" maxlength="36" name="MUST_Y" fieldname="MUST_Y" type="hidden" /><span id="xh"></span></td>
										<td>
											<select id="GANGWEI_UID" class="span12" style="width:99%" name="GANGWEI_UID" fieldname="GANGWEI_UID" kind="dic" src="T#gangwei: GANGWEI_UID as c:NAMES as t:TAGS='S' AND GANGWEI_UID!=19"></select>
											<input id="SG_PERSON_UID" style="width:99%" class="span12" name="SG_PERSON_UID" fieldname="SG_PERSON_UID" type="hidden" />
											<input id="SGBB_RY_UID" style="width:99%" name="SGBB_RY_UID" fieldname="SGBB_RY_UID" type="hidden" />
										</td>
										<td><input id="SG_NAME" style="width:70%" class="span12" name="SG_NAME" fieldname="SG_NAME" type="text" readonly="readonly"/><button class="btn btn-link"  type="button" onclick="selectRyxx(this)"><i class="icon-edit"></i></button></td>
										<td><input id="ZHENGSHU_NAME" style="width:99%" class="span12" name="ZHENGSHU_NAME" fieldname="ZHENGSHU_NAME" type="text" readonly="readonly"/></td>
										<td><input id="ZHENGSHU_CODE" style="width:99%" class="span12" name="ZHENGSHU_CODE" fieldname="ZHENGSHU_CODE" type="text" readonly="readonly"/></td>
										<td><input id="ZHUANYE" style="width:99%" class="span12" name="ZHUANYE" fieldname="ZHUANYE" type="text" readonly="readonly"/></td>
										<td><input id="ZHENGSHU_DATE" style="width:99%" class="span12" name="ZHENGSHU_DATE" fieldname="ZHENGSHU_DATE" type="text" readonly="readonly"/></td>
										<td><input id="AGE" style="width:99%" class="span12" name="AGE" fieldname="AGE" type="text" readonly="readonly"/></td>
										<td><input id="ZHICHENG_NAME" style="width:99%" class="span12" name="ZHICHENG_NAME" fieldname="ZHICHENG_NAME" type="text" readonly="readonly"/></td>
										<td><input id="SHENFENZHENG" style="width:99%" class="span12" name="SHENFENZHENG" fieldname="SHENFENZHENG" type="text" readonly="readonly"/></td>
										<td><input id="MOBILE" style="width:99%" class="span12" name="MOBILE" fieldname="MOBILE" type="text" readonly="readonly"/></td>
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
        <input type="hidden" name="txtFilter"  order="desc" fieldname = "t.CREATED_DATE" id = "txtFilter">
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