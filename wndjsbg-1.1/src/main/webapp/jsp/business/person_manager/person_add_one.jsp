<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<title>入库人员信息</title>
<%

	String tid=(String)request.getAttribute("id");

	
%>
<app:base/>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername="${pageContext.request.contextPath }/person/SgPersonLibraryController/";
var controllernameZizhi= "${pageContext.request.contextPath }/sgenter/sgZizhiController/";
var controllernameZhengshu="${pageContext.request.contextPath }/person/SgZhengshuController/";
var controllernameZhicheng="${pageContext.request.contextPath }/person/SgZhichengController/"; 
var controllernamePersonZhengshu="${pageContext.request.contextPath }/person/SgPersonZhengshuController/";
var controllernameBgMenu="${pageContext.request.contextPath }/bgMenuController/";




var id ="<%=tid%>";

//页面初始化
$(function() {
	init();
	
	//入库(保存) 
	$("#btnSave").click(function(){
		var status=$("#STATUS").val();
		
		if($("#sgPersonLibraryForm").validationButton())
		{ 
			
				var u_id=$("#SG_PERSON_UID").val();
				
				var data = Form2Json.formToJSON(sgPersonLibraryForm);
				var data1 = defaultJson.packSaveJson(data);//用UPDATE
				defaultJson.doInsertJson(controllername + "update",data1);
				window.opener.closeParentCloseFunction();
		}else{
			requireFormMsg();
		  	return;
		
		}
	});
	//关闭窗口 
	$("#btnShutDown").click(function() {
	       window.close();
	       window.opener.closeParentCloseFunction();
	  	
	});
   
 $("#btnStop").click(function() {
	if(confirm("确定阻止这条数据通过吗？")){
		var data = Form2Json.formToJSON(sgPersonLibraryForm);
		  //组成保存json串格式
		var data1 = defaultJson.packSaveJson(data);
	var status=20;
	var u_id=$("#SG_PERSON_UID").val();
	defaultJson.doInsertJson(controllername + "updateShenhe?u_id="+u_id+"&status="+status,data1);
    alert("信息进入未通过状态！"); 
	window.close(); 
	window.opener.closeParentCloseFunction();
	}
	});
	
 $("#btnLock").click(function() {
	 
	 suoding();
 	  //$(window).manhuaDialog({"title":"锁定","type":"text","content":"${pageContext.request.contextPath }/jsp/business/person_manager/suoding.jsp","modal":"3"});	
	//window.location.href = window.location.href;
	
 });
 
$("#btnUnlock").click(function() {
	if(confirm("确定解锁吗？")){
	 $.ajax({
		    url : controllername+"jiesuo?id="+$("#SG_PERSON_LIBRARY_UID").val(), 
			data : {},
			cache : false,
			async :	false,
			dataType : "json",  
			type : 'get',
			success : function(response) {
				$("#resultXML").val(response.msg);
			}
	     });
	 window.location.href = window.location.href;
	}
 });
 $("#btnFindOne").click(function() {
	 var jg = $("input:radio[name='SPXZZT']:checked").val();
		//if(check()){
			if(jg=="10"){
				if(isName($("#PERSON_NAME"))&&isCardNo($("#SHENFENZHENG"))&&isPhone($("#PHONE"))&&isEmail($("#EMAIL")))
                   {
                      pass();
				   }
				}
			else{

				    var reason = $("#SHENHE_YIJIAN").val(); 
					if(reason.length==0){
						alert("请填写审批不通过理由!");
						return;
					}else{  stop();	}		
				}
		//}
			})
});

//页面默认参数
function init(){
	$("#sdxx").hide();
	$("#RyList").hide();
	if(id!="null"&&id!=""){
			$("#QID").val(id);
		    $("#QID1").val(id);
		   	//验证是否有权显示锁定/解锁按钮
			$.ajax({
				url : controllernameBgMenu+"?getByMenuCodeAndUserId",
				data : {code : "2040101"},
				type :"post",
				dataType : "json",
				success : function(response){
					if(response.msg == "1"){
						$("#btnLock").show();
						$("#btnUnlock").show();
					}else{
						$("#btnLock").hide();
						$("#btnUnlock").hide();
					}
					 //获取父页面传过来的参数 
					 setFormValues();
				}
			});
			//验证是否有权显示保存按钮
		   $.ajax({
				url : controllernameBgMenu+"?getByMenuCodeAndUserId",
				data : {code : "2040102"},
				type :"post",
				dataType : "json",
				success : function(response){
					if(response.msg == "1"){
						$("#btnSave").show();
					}else{
						$("#btnSave").hide();
					}
				}
			});
			
			builderZhengshuList();
			queryFileData2('SG_PERSON_LIBRARY_UID','parent','SG_PERSON_LIBRARY');
			queryFileData2('SG_PERSON_ZHENGSHU_UID','child','SG_PERSON_ZHENGSHU');
	}
		else{
			$("#btnDel").hide();
			//$("#btnShenHe").hide();
			}
}
function suoding(){
	
	 var zid=$("#SG_PERSON_LIBRARY_UID").val();
	 window.open("${pageContext.request.contextPath }/suoding/"+zid,"人员添加");
	
	 //window.opener.location.reload();
}

function stop(){
	if(confirm("确定阻止这条数据通过吗？")){
		var data = Form2Json.formToJSON(sgPersonLibraryForm);
		  //组成保存json串格式
		var data1 = defaultJson.packSaveJson(data);
	var status=20;
	var u_id=$("#SG_PERSON_UID").val();
	var yj = $("#SHENHE_YIJIAN").val();
	defaultJson.doInsertJson(controllername + "updateShenhe?u_id="+u_id+"&status="+status+"&yijian="+yj+"&t_id="+t_id,data1);
    alert("信息为未通过状态！"); 
	window.close(); 
	window.opener.closeParentCloseFunction();
	}
	}
//入库(保存) 
function pass(){
	var status=$("#STATUS").val();
	var yj = $("#SHENHE_YIJIAN").val()
	if(confirm("确定让这条数据通过吗？")){ 
	if($("#sgPersonLibraryForm").validationButton())
	   { 
		
			var u_id=$("#SG_PERSON_UID").val();
			var sta=10;
			var data = Form2Json.formToJSON(sgPersonLibraryForm);
			var data1 = defaultJson.packSaveJson(data);//用UPDATE
			var flag = defaultJson.doInsertJson(controllername + "updateShenhe?u_id="+u_id+"&status="+sta+"&yijian="+yj+"&t_id="+t_id,data1);
			var js="";
			if(flag){
				js = $("#resultXML").val();
				
			}
			var sta2=1;
			defaultJson.doInsertJson(controllername + "updateStatusOne?u_id="+u_id+"&status="+sta2+"&js="+js,data1);
			alert("信息已通过并入库！"); 
			window.close(); 
			window.opener.closeParentCloseFunction();
	}else{
		requireFormMsg();
	  	return;
	}
}
}
//删除人员信息 
function deletePerson(){
	
	var data = Form2Json.formToJSON(queryForm);
	  //组成保存json串格式
	var data1 = defaultJson.packSaveJson(data);
	defaultJson.doInsertJson(controllername + "delete", data1);

}

function setFormValues(){
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
		//alert(response.msg);
		var resultobj = defaultJson.dealResultJson(response.msg);
		$("#sgPersonLibraryForm").setFormValues(resultobj);

		$("#RyList").setFormValues(resultobj);
		$("#SJFW").text(resultobj.JZ_SJFW_SV);
	     }
	});
	if($("#JZ_Y").val()!=""&&$("#JZ_Y").val()!="0"){
		$("#btnLock").hide();
		$("#sdxx").show();
		$("#RyList").show();
	}
	else{
		$("#btnUnlock").hide();
		}
	
	
}

function builderZhengshuList(){
	$.ajax({
		url : controllernamePersonZhengshu+"queryListPersonZhengshu?uid="+$("#QID1").val(),
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var obj1 = eval('(' + response.msg + ')');
			$(obj1).each(function(){
				var cloneNew = $("#cloneTR").clone(true);
				//alert(typeof(cloneNew));
				$(cloneNew).removeAttr("style")
				$("#zhigeList").find("tr").eq(2).before(cloneNew);
				$(cloneNew).find("#SG_PERSON_ZHENGSHU_UID").val(this.SG_PERSON_ZHENGSHU_UID);
				$(cloneNew).find("#ZHENGSHU_NAME").val(this.ZHENGSHU_UID);
				$(cloneNew).find("#ZHUANYE_NAME").val(this.SG_ZIZHI_UID);
				$(cloneNew).find("#ZHENGSHU_CODE").val(this.ZHENGSHU_CODE);
				$(cloneNew).find("#BEGIN_DATE").val(this.BEGIN_DATE.substring(0,10));
				$(cloneNew).find("#END_DATE").val(this.END_DATE.substring(0,10));
				
				});
		}
	});
	 var rows = $("tbody tr" ,$("#DT1"));
		if(rows.size()==0){
			$("tbody" ,$("#DT1")).append("<tr><td colspan=\"7\" style=\"height: 1px;\" align=\"center\">暂无记录</td></tr>");

		}
}

function builderForm(response){
	$("#resultXML").val(response.msg);
	var resultobj = defaultJson.dealResultJson(response.msg);
	$("#sgPersonLibraryForm").setFormValues(resultobj);
	//隐藏密码修改
	$("#PWD").val(resultobj.MIMA);
	$("#pwdtr").hide();
	$("#pwdtr_re").hide();

	//组织机构代码
	var code = resultobj.COMPANY_CODE;
	$("#DAIMA_Z").val(code.substring(0,code.length-2));
	$("#DAIMA_F").val(code.substring(code.length-2,code.length-1));

	loadZZDJ($("#ZHENGSHU"),'zhuxiang');
}

//增加增项目资格
function addZhige(demo){
	var cloneNew = $("#cloneTR").clone(true);
	$(cloneNew).removeAttr("style")
	$("#zhigeList").append(cloneNew);
	$(demo).hide();
}

function removeZhige(demo){
	if($("#zhigeList tr").size()==3){

		return;
		}
	var tr_index = $("#zhigeList tr").index($(demo).closest("tr"));
	if(tr_index==$("#zhigeList tr").size()-1&&tr_index>2){
		$("#zhigeList tr").eq($("#zhigeList tr").size()-2).find("td").eq($("#zhigeList").find("th").size()-1).append('&nbsp;<img onclick="addZhige(this)" style="cursor: pointer;" title="增加" src="/wndjsbg/img/sg/icon-addZz.jpg">');
	}	
	$(demo).closest("tr").remove();
}
//验证输入记录是否已存在 
function checkCode(){
	$.ajax({
		url : controllername+"queryCodeIsEmpty?shenfenID="+$("#SHENFENZHENG").val(),
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			if(response.msg!=null&&response.msg!="0"&&response.msg!=""){//如果数据库中有该ID
				//console.log(response.msg);写日志  
				var resultobj = eval('(' + response.msg + ')')
				$.each(resultobj,function(){
					$("#SG_PERSON_UID").val(this.SG_PERSON_UID);//获取返回来的该人员的person_uid
				})
			}
		}
	});
}

function checkStatus(){

var flag =true;
$.ajax({
		url : controllername+"queryStatusIsEmpty?shenfenID="+$("#SHENFENZHENG").val()+"&personUID="+$("#SG_PERSON_UID").val()+"&bz="+ty,
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			if(response.msg!=null&&response.msg!="0"&&response.msg!=""){//如果数据库中有该ID
				flag = false;
				alert("该信息,人员库中已存在! ");
				$("#btnSave").hide();
	 			$("#btnShenHe").hide();
				return flag;
			}
			else{
			$("#btnSave").show();
 			$("#btnShenHe").show();
				return flag;}
		}
	});
	return flag;
}

//验证身份证是否有效
function isCardNo(card){  
	   //checkStatus();
	   // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
	   var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
	   if($("#SHENFENZHENG").val() == null || $("#SHENFENZHENG").val() == "")
		   {
	    	  $("#shenfentext").text("身份证号不能为空"); 
			  return false;
	          }

	   else{
 			 if(reg.test($(card).val()) == false)  
	  		 {  
         		$("#shenfentext").text("身份证输入不合法");
	       		return  false;  
	  		 }else{
		  		 $("#shenfentext").text("");
		   	     return true;
	   			}
	   		}
	}
//验证电话号码是否有效 
function isPhone(card){  
	 var reg =/^((\d{11})|((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})))$/
	     // var reg =/^(13[0-9]|15[012356789]|18[012356789]|14[57])\d{8}$/;
		  
		  if($("#PHONE").val() == null || $("#PHONE").val() == ""){
			  $("#phonetext").text("电话号码不能为空");
			  return false;
			  }
		  else{
		     if(reg.test($(card).val()) == false)  
		     {  
	         $("#phonetext").text("电话号码输入不合法"); 
		       return  false;  
		     }else{
			   $("#phonetext").text("");
			   return true;
		        }
		  }
		}

//验证邮箱地址是否有效  
function isEmail(card){
	
		  var reg= /^([a-zA-Z0-9_\.])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;  
		  if($("#EMAIL").val() == null || $("#EMAIL").val() == ""){
        	  $("#emailtext").text("");
			   return true;
              }
		  else{
		    if(reg.test($(card).val()) == false)  
		    {  
	         $("#emailtext").text("邮箱地址输入不合法"); 
		       return  false;   
		    }else{
			   $("#emailtext").text("");
			   return true;
		    }
         }
		}
//验证姓名不为空   
function isName(card){
	
		 if($("#PERSON_NAME").val()==null||$("#PERSON_NAME").val()=="")
            {
		       $("#nametext").text("姓名不为空");  
		       return  false;   
		    }else{
			   $("#nametext").text("");
			   return true;
		    }
          
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
	        	<INPUT type="text" class="span12" kind="text" id="QID" name="ID"  fieldname="SG_PERSON_LIBRARY_UID" value="" operation="="/>
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
	        	<INPUT type="text" class="span12" kind="text" id="QID1" name="ID"  fieldname="SG_PERSON_UID" value="" operation="="/>
	        </TD>
			<TD class="right-border bottom-border">
			</TD>
        </TR>
      </table>
      </form>
    <div style="height:5px;"></div>
	</div>
	<p class="text-right tabsRightButtonP">
      <span class="pull-right">
      <button id="btnLock" class="btn" type="button">锁定</button>
      <button id="btnUnlock" class="btn" type="button">解锁</button>
				<button id="btnShutDown" class="btn" type="button">关闭</button>
				<button id="btnSave" class="btn" type="button">保存</button>
	</span>
	</p>
				<div style="height:5px;"></div>
	<div class="B-small-from-table-autoConcise">
	
      <form method="post" id="sgPersonLibraryForm">
		<%--<div class="tab-content"> --%>
				<!-- 静态信息tab页 -->
				<div class="tab-pane active" id="xmsc1" style="height:100%"> 
				<table class="B-table" width="100%" >
				    <input type="hidden" id="STATUS" fieldname="STATUS" name="STATUS"/>
				    <input type="hidden" id="JZ_Y" fieldname="JZ_Y" name="JZ_Y"/>
			        <input type="hidden" id="SG_PERSON_LIBRARY_UID" fieldname="SG_PERSON_LIBRARY_UID" name="SG_PERSON_LIBRARY_UID"/>
				  	<input type="hidden" id="SG_COMPANY_UID" fieldname="SG_COMPANY_UID" name = "SG_COMPANY_UID"/>
			        <input type="hidden" id="SG_PERSON_LIBRARY_FILEUPLOAD" fieldname="SG_PERSON_LIBRARY_FILEUPLOAD" name = "SG_PERSON_LIBRARY_FILEUPLOAD"/>
			        <input type="hidden" id="SG_PERSON_UID" fieldname="SG_PERSON_UID" name ="SG_PERSON_UID"/>
			       <tr>
						<th width="15%" class="right-border bottom-border text-right">所属企业</th>
			       	 	<td class="bottom-border right-border">
			         		<input class="span12" style="width:50%" id="COMPANY_NAME" type="text" placeholder="必填" check-type="required" check-type="maxlength" maxlength="10" fieldname="COMPANY_NAME" name = "COMPANY_NAME" readonly />
			         	</td>
			       </tr>
			       <tr>
						<th width="15%" class="right-border bottom-border text-right">姓名</th>
			       	 	<td class="bottom-border right-border">
			         		<input class="span12" style="width:50%" id="PERSON_NAME" type="text" placeholder="必填" check-type="required" check-type="maxlength" maxlength="10" fieldname="PERSON_NAME" name = "PERSON_NAME" onblur="isName(this)" /><font color="red">*</font><b><span id="nametext" style="color:red;font-size: 14px"></span></b>
			         	</td>
			        </tr>
			        <tr>
						<th width="15%" class="right-border bottom-border text-right">性别</th>
						<td  class="right-border bottom-border">
						    <select  id="SEX"  class="span12" style="width:50%"  name="SEX" fieldname="SEX"  operation="="   defaultMemo="全部">
								<option>男</option>
								<option>女</option>
							</select>
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right ">身份证号</th>
						<td  class="right-border bottom-border">
							<input id="SHENFENZHENG" class="span12" style="width:50%" placeholder="必填" check-type="required" check-type="maxlength" maxlength="18	" name="SHENFENZHENG" fieldname="SHENFENZHENG" type="text" onblur="isCardNo(this)"/><font color="red">*</font><b><span id="shenfentext" style="color:red;font-size: 14px"></span></b>
					    </td>
					</tr>	   
					<tr>
					    <th width="15%" class="right-border bottom-border text-right ">身份证扫描件</th>
						<td  class="right-border bottom-border">
						<div>
						      <span class="btn btn-fileUpload" id="addFileSpan" onclick="doSelectFile(this,'parent');" ywid="SG_PERSON_LIBRARY_UID" target_type="2" file_type="16" business_type="SG_PERSON_LIBRARY">
									<i class="icon-plus"></i>
									<span>添加文件</span>
								</span>
								<table role="presentation" class="table table-striped">
									<tbody fjlb="0701" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>
								</table>
							</div>
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right ">电话号码</th>
						<td  class="right-border bottom-border">
							<input class="span12" style="width:50%" id="PHONE" type="text" placeholder="必填" check-type="required"  check-type="maxlength" maxlength="11" fieldname="PHONE" name = "PHONE" onblur="isPhone(this)" /><font color="red">*</font><b><span id="phonetext" style="color:red;font-size: 14px"></span></b>
		     		   </td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right ">EMAIL</th>
						<td  class="right-border bottom-border">
							<input id="EMAIL" class="span12" style="width:50%"  check-type="maxlength" maxlength="36" name="EMAIL" fieldname="EMAIL" type="text" onblur="isEmail(this)"/><b><span id="emailtext" style="color:red;font-size: 14px"></span></b>
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right ">职称</th>
						<td  class="right-border bottom-border">
						   <select id="ZHICHENG_UID"  class="span12" style="width:50%" name="ZHICHENG_UID" fieldname="ZHICHENG_UID" kind="dic" src="T#zhicheng: zhicheng_uid as c:zhicheng_name as t:1=1 order by zhicheng_uid" noNullSelect="true"></select>
						</td>	
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right ">职称证号</th>
						<td  class="right-border bottom-border">
							<input id="ZHICHENG_CODE" class="span12" style="width:50%"  check-type="maxlength" maxlength="36" name="ZHICHENG_CODE" fieldname="ZHICHENG_CODE" type="text" />
						</td>
					</tr>
					<tr>	
						<th width="15%" class="right-border bottom-border text-right ">职称扫描件</th>
						<td  class="right-border bottom-border">
							<div >
						        <span class="btn btn-fileUpload" id="addFileSpan" onclick="doSelectFile(this,'parent');" ywid="SG_PERSON_LIBRARY_UID" target_type="2" file_type="12" business_type="SG_PERSON_LIBRARY">
									<i class="icon-plus"></i>
									<span>添加文件</span>
								</span>
								<table role="presentation" class="table table-striped ">
									<tbody fjlb="0701" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>
								</table>
							</div>
						</td>
					</tr>	
					<tr>
						<th width="15%" class="right-border bottom-border text-right ">资格证书</th>
						<td  class="right-border bottom-border">
							<table class="B-table" width="100%" id="zhigeList">
								<tr>
									<th style="width:19%">证书类型</th>
									<th style="width:18%">注册专业</th>
									<th style="width:15%">证书编号或注册编号</th>
									<th style="width:12%">注册日期</th>
									<th style="width:12%">有效期</th>
									<th style="width:15%">附件</th>
									<th style="width:5%">操作</th>
									
								</tr>
								<tr id="cloneTR" style="display : none;"><!-- 用来复制 -->
									<td>
									<input id="SG_PERSON_ZHENGSHU_UID" type="hidden"  style="width:99%" class="span12" check-type="maxlength" maxlength="36" name="SG_PERSON_ZHENGSHU_UID" fieldname="SG_PERSON_ZHENGSHU_UID" type=text />
									<select id="ZHENGSHU_NAME"  class="span12" style="width:99%" name="ZHENGSHU_NAME" fieldname="ZHENGSHU_NAME" kind="dic" src="T#sg_zhengshu: sg_zhengshu_uid as c:zhengshu_name as t:enabled=1"  defaultMemo="请选择"></select></td>
									<td><select id="ZHUANYE_NAME" class="span12" style="width:99%" name="ZHUANYE_NAME"  fieldname="ZHUANYE_NAME"  kind="dic" src="T#sg_zizhi: sg_zizhi_uid as c:zhuanye_name as t:1=1 order by SERIAL_NO"  defaultMemo="请选择"></select></td>
									<td><input id="ZHENGSHU_CODE" class="span12" style="width:99%" placeholder="建造师证请填写注册编号" check-type="maxlength" maxlength="36" name="ZHENGSHU_CODE" fieldname="ZHENGSHU_CODE" type="text" /></td>
									<td><input id="BEGIN_DATE"  class="Wdate" type="text" onClick="WdatePicker()" style="width:85%" field  fieldformat="YYYY-MM-DD" name="BEGIN_DATE" fieldname="ZSBEGIN_DATE" /></td>
								    <td><input id="END_DATE"  class="Wdate" type="text" onClick="WdatePicker()" style="width:85%" field  fieldformat="YYYY-MM-DD" name="END_DATE" fieldname="ZSEND_DATE"  /></td>&nbsp;
								    <td>
								       <input type="hidden" id="SG_PERSON_ZHENGSHU_FILEUPLOAD" fieldname="SG_PERSON_ZHENGSHU_FILEUPLOAD" name = "SG_PERSON_ZHENGSHU_FILEUPLOAD"/>
						               	 <div>
								              <span class="btn btn-fileUpload" id="addFileSpan" onclick="doSelectFile(this,'child');" ywid="SG_PERSON_ZHENGSHU_UID" target_type="2" file_type="11" business_type="SG_PERSON_ZHENGSHU">
									            <i class="icon-plus"></i>
									            <span>添加文件</span>
								              </span>
								                <table role="presentation" class="table table-striped">
									               <tbody fjlb="0701" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>
								                </table>
							            </div>
								    </td>
								    <td><img onclick="removeZhige(this)" style="cursor: pointer;" title="删除" src="/wndjsbg/img/sg/icon-removeZz.jpg">
								    <img onclick="addZhige(this)" style="cursor: pointer;" title="增加" src="/wndjsbg/img/sg/icon-addZz.jpg"></td>
								</tr>
								<tr>
									<td>
									<input id="SG_PERSON_ZHENGSHU_UID" type="hidden"  style="width:99%" class="span12" check-type="maxlength" maxlength="36" name="SG_PERSON_ZHENGSHU_UID" fieldname="SG_PERSON_ZHENGSHU_UID" type=text />
									<select id="ZHENGSHU_NAME"  class="span12" style="width:99%" name="ZHENGSHU_NAME" fieldname="ZHENGSHU_NAME" kind="dic" src="T#sg_zhengshu: sg_zhengshu_uid as c:zhengshu_name as t:enabled=1"  defaultMemo="请选择"></select></td>
									<td><select id="ZHUANYE_NAME"  class="span12" style="width:99%" name="ZHUANYE_NAME" fieldname="ZHUANYE_NAME" kind="dic" src="T#sg_zizhi: sg_zizhi_uid as c:zhuanye_name as t:1=1 order by SERIAL_NO"  defaultMemo="请选择"></select></td>
									<td><input id="ZHENGSHU_CODE"  class="span12"style="width:99%" placeholder="建造师证请填写注册编号" check-type="maxlength" maxlength="36" name="ZHENGSHU_CODE" fieldname="ZHENGSHU_CODE" type="text" /></td>
									<td><input id="BEGIN_DATE"  class="Wdate" type="text" onClick="WdatePicker()" style="width:85%" field  fieldformat="YYYY-MM-DD" name="BEGIN_DATE" fieldname="ZSBEGIN_DATE"   /></td>
								    <td><input id="END_DATE"  class="Wdate" type="text" onClick="WdatePicker()" style="width:85%" field  fieldformat="YYYY-MM-DD" name="END_DATE" fieldname="ZSEND_DATE"   /></td>
								    <td>
								       <input type="hidden" id="SG_PERSON_ZHENGSHU_FILEUPLOAD" fieldname="SG_PERSON_ZHENGSHU_FILEUPLOAD" name = "SG_PERSON_ZHENGSHU_FILEUPLOAD"/>
						               	 <div>
								              <span class="btn btn-fileUpload" id="addFileSpan" onclick="doSelectFile(this,'child');" ywid="SG_PERSON_ZHENGSHU_UID" target_type="2" file_type="11" business_type="SG_PERSON_ZHENGSHU">
									            <i class="icon-plus"></i>
									            <span>添加文件</span>
								              </span>
								                <table role="presentation" class="table table-striped">
									               <tbody fjlb="0701" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>
								                </table>
							            </div>
								    </td>
								    <td><img onclick="removeZhige(this)" style="cursor: pointer;" title="删除" src="/wndjsbg/img/sg/icon-removeZz.jpg">
								    <img onclick="addZhige(this)" style="cursor: pointer;" title="增加" src="/wndjsbg/img/sg/icon-addZz.jpg"></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right ">合同起止日期</th>
						<td  class="right-border bottom-border">
						   <input id="BEGIN_DATE"  class="Wdate" type="text" onClick="WdatePicker()" style="width:14%;" fieldtype="date"  fieldformat="YYYY-MM-DD" name="BEGIN_DATE" fieldname="BEGIN_DATE"   />
					        —
						   <input id="END_DATE"  class="Wdate" type="text" onClick="WdatePicker()" style="width:14%;" fieldtype="date"  fieldformat="YYYY-MM-DD" name="END_DATE" fieldname="END_DATE"   />
						</td>
					</tr> 
					<tr>  
					   <th width="15%" class="right-border bottom-border text-right ">劳动合同扫描件</th>
					   <td>
						 <div>
						    <span class="btn btn-fileUpload" id="addFileSpan" onclick="doSelectFile(this,'parent');" ywid="SG_PERSON_LIBRARY_UID" target_type="2" file_type="13" business_type="SG_PERSON_LIBRARY">
								<i class="icon-plus"></i>
								<span>添加文件</span>
							</span>
							<table role="presentation" class="table table-striped">
								<tbody fjlb="0701" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>
							</table>
							</div>
						</td>
					</tr>
					<tr>
					    <th width="15%" class="right-border bottom-border text-right ">社保证明扫描件</th>
						<td>  
						    <div>
								 <span class="btn btn-fileUpload" id="addFileSpan" onclick="doSelectFile(this,'parent');" ywid="SG_PERSON_LIBRARY_UID" target_type="2" file_type="14" business_type="SG_PERSON_LIBRARY">
									<i class="icon-plus"></i>
									<span>添加文件</span>
								</span>
								<table role="presentation" class="table table-striped">
									<tbody fjlb="0701" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>
								</table>
							</div>
						</td>
					</tr>
					<tr>
				        <th width="15%" class="right-border bottom-border text-right ">备注或个人简介</th>
				        <td  class="bottom-border right-border" >
				        	<textarea class="span12" rows="3" id="DESCRIPTION" check-type="maxlength" maxlength="4000" fieldname="DESCRIPTION" name="DESCRIPTION"></textarea>
				        </td>
			        </tr>
			    
				</table>
			 </div>
				<div id="sdxx" class="modal-header" style="background:#0866c6;">
				<h4 id="ryxx_title" style="color:white;">人员锁定信息
				</h4>
				</div>
				<table width="100%" class="B-table"  id="RyList"  >
			        <tr>
	               		<th width="15%" class="right-border bottom-border text-right ">锁定原因</th>
	                 	<td class="bottom-border right-border">	                 
	                		<textarea name="JZ_YUANYIN"   id="JZ_YUANYIN" fieldname="JZ_YUANYIN" style="height:90%;width:98%;" readonly="true"></textarea>
	                 	</td>
	               	</tr>
	               	<tr>
	            		<th width="15%" class="right-border bottom-border text-right ">是否是上级发文的限制</th>
	            		<td class="right-border bottom-border">
	            			<%--<input class="span12" id="JZ_SJFW" type="radio" placeholder="" kind="dic" src="SUODING" name = "JZ_SJFW" fieldname="JZ_SJFW" disabled="true">
	            		--%>
	            		<span id="SJFW"></span>
	            		</td>
	            	</tr>
	               	<tr>
	               		<th width="15%" class="right-border bottom-border text-right ">锁定时间</th>
	                 	<td class="bottom-border right-border">	                 
	                		<input name="JZ_DATE" type="text"  id="JZ_DATE" fieldtype="date"  fieldname="JZ_DATE" style="height:90%;width:30%;" readonly="true" />
	                 	</td>
	               	</tr>
	               	</table>
	    </form>
    </div>
   </div>
  </div><%--操作附件上传的方法 --%>
 <jsp:include page="/jsp/file_upload/fileupload_config.jsp" flush="true"/>
  </div>
  <div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id = "queryXML">
         <input type="hidden" name="txtXML" id = "txtXML">
         <input type="hidden" name="txtFilter"  order="desc" fieldname = "STATUS" id = "txtFilter">
         <input type="hidden" name="resultXML" id = "resultXML">
         <input type="hidden" name="ywid" id="ywid">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
		
 	</FORM>
 </div>
</body>
<script>
</script>
</html>