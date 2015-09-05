<!DOCTYPE html>
<%@page import="com.ccthanking.framework.Constants"%>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>信访信息-编辑</title>
<%
	String XINFANG_UID = request.getParameter("XINFANG_UID");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername = "${pageContext.request.contextPath}/xfww/XinFangController";
var XINFANG_UID ="<%=XINFANG_UID%>";
//页面初始化
$(function() {
	init();
	
	$("#btnClose").bind("click", function(){
		var a=$(this).manhuaDialog.close();
	});
	
	$("#btnDel").bind("click",function(){
		xConfirm("信息确认","数据一旦被删除后将难以恢复，确认要删除吗？");
		$('#ConfirmYesButton').attr('click','');
		$('#ConfirmYesButton').one("click",function(){
			$.ajax({
				url :  controllername+"?delete",
				data : {"XINFANG_UID":XINFANG_UID},
				type : "post",
				dataType : "json",
				success : function(response){
					if(response.success){
						//获取父页面
						var a=$(window).manhuaDialog.getParentObj();
						//重新加载数据
			   			a.init();
			   			//关闭当前页
						$(window).manhuaDialog.close();	
					}
				}
			});
		});
	});
	$("#btnSave").bind("click",function(){
		//添加约束
		//约束来源及信访人不能为空
		if($("#SOURCE").val().trim()==""){
			return;
		}
		
		//验证邮箱是否规范			
		var correctEmail=/^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		var currentEmail=$("#XF_EMAIL").val();
		if($("#XF_EMAIL").val()!=""&&!correctEmail.test(currentEmail)){			
			alert("您输入的邮箱格式有误，请重新输入");
			return;
		}
		
		//验证电话号码
		var phoneNumber=/^1\d{10}$|^0\d{2,3}-?\d{7,8}$/;
		var userphone=$("#XF_MOBILE").val();
		if($("#XF_MOBILE").val()!=""&&!phoneNumber.test(userphone)){			
			alert("您输入的手机格式有误，请重新输入。如果没有手机号码可以不输入");
			return;
		}
		
		//生成json串
		var data = Form2Json.formToJSON(XinfangForm);
		//组成保存json串格式
		var data1 = defaultJson.packSaveJson(data);
		$.ajax({
			url : controllername + "?update",
			data : data1,
			cache : false,
			dataType : "json",
			type : "post",
			async : true,
			success : function(response) {
				//获取父页面
				var a=$(window).manhuaDialog.getParentObj();
				//重新加载数据
			   	a.init();
			   	//关闭当前页
				$(window).manhuaDialog.close();
			}
			});
	});
}); 

//页面默认参数
function init(){
	
	$.ajax({
		url : controllername+"?detail",
		data : {"XINFANG_UID":XINFANG_UID},
		type : "post",
		dataType : "json",
		success : function(response){
			var resultObj = defaultJson.dealResultJson(response.msg);			
			$("#XinfangForm").setFormValues(resultObj);
			//操作来源及部门
			var SOURCE=resultObj.SOURCE;
			$("#SOURCE").val(SOURCE);
			if(SOURCE==null||SOURCE!="转办受理"){
				$("#ZBBM").attr("disabled",true);
			};
			
			//显示附件
			queryFileData('XINFANG',XINFANG_UID);
		
		}
	});	
}

////处理来源和部门之间的关系
function change(id){
	if($("#"+id).val()=="转办受理"){
		$("#ZBBM").attr("disabled",false);
	}else{
		$("#ZBBM").val("");
		$("#ZBBM").attr("disabled",true);
	}
}

	//添加附件处理
	function selectFile (obj){
   	var target_uid = $('#target_uid').val();
   	var file_type = $(obj).attr('file_type');
	setFileData("XINFANG","XINFANG_UID",target_uid,file_type);
	document.getElementById("fileupload_btn").click();	
}
</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">
	
	<div class="B-small-from-table-autoConcise">
      <h4 class="title">信访信息修改
      	<span class="pull-right">
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
      		<button id="btnDel" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">删除</button>
      		<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">保存</button>	  		
      	</span>
      </h4>
      <div style="height:5px;"></div>
     <form method="post" id="XinfangForm" action="${pageContext.request.contextPath }/xfww/XinFangController?update" >
     	<table class="B-table" width="100%" >
     	
	       	 <!-- 信访所有信息 -->
	       	 <tr>
				<th width="15%" class="right-border bottom-border text-right">来源</th>
				<td class="bottom-border right-border" colspan="3">
	       	 	<select class="span12" id="SOURCE" name="SOURCE" fieldname="SOURCE" onchange="change(this.id)" >
					<option value="网上投诉">网上投诉</option>
					<option value="电话投诉">电话投诉</option>
					<option value="现场投诉">现场投诉</option>
					<option value="转办受理">转办受理</option>
				</select>
	       	 	<!-- 隐藏属性 -->
	       	 		<input class="span12" id="XINFANG_UID" type="hidden" fieldname="XINFANG_UID" name = "XINFANG_UID"  />
	       	 		<input class="span12" id="GONGCHENG_UID" type="hidden" fieldname="GONGCHENG_UID" name = "GONGCHENG_UID"  />	       	 	
	       	 		<input class="span12" id="UPDATED_BY" type="hidden" fieldname="UPDATED_BY" name = "UPDATED_BY"  />
	       	 		<input class="span12" id="UPDATED_DATE" type="hidden" fieldname="UPDATED_DATE" name = "UPDATED_DATE"/>	       	 		
	       	 	</td>
	       	 </tr>
	       	 <tr>
	       	 	<th width="15%" class="right-border bottom-border text-right">转办部门</th>	      
				<td class="bottom-border right-border" colspan="3">
	       	 	<input class="span12"  id="ZBBM" type="text"  fieldname="ZBBM" name = "ZBBM"  />	         	        	
	       	 	</td>
	       	 </tr>
	       	 <tr>
	       	 	<th width="15%" class="right-border bottom-border text-right">信访人</th>
	       	 	<td class="bottom-border right-border" colspan="3">
	       	 	<input class="span12"  id="XF_NAME" type="text"  fieldname="XF_NAME" name = "XF_NAME"/>	         	        	
	       	 	</td>
	       	 </tr>
	       	 <tr>	
	       	 	<th width="15%" class="right-border bottom-border text-right">信访人地址</th>
	       	 	<td class="bottom-border right-border"  colspan="3">
	       	 	<input class="span12"  id="XF_ADDRESS" type="text"  fieldname="XF_ADDRESS" name = "XF_ADDRESS"  />	         	        	
	       	 	</td>
	       	 </tr>
	       	 <tr>
	       	 	<th width="15%" class="right-border bottom-border text-right">信访人邮箱</th>
	       	 	<td class="bottom-border right-border"  colspan="3">
	       	 	<input class="span12"  id="XF_EMAIL" type="text"  fieldname="XF_EMAIL" name = "XF_EMAIL"  />	         	        	
	       	 	</td>
	       	</tr>
	       	<tr> 	
	       	 	<th width="15%" class="right-border bottom-border text-right">信访人号码</th>
	       	 	<td class="bottom-border right-border"  colspan="3">
	       	 	<input class="span12"  id="XF_MOBILE" type="text"  fieldname="XF_MOBILE" name = "XF_MOBILE"  />	         	        	
	       	 	</td>
	       	 </tr>
	       	 <tr>
	       	 	<th width="15%" class="right-border bottom-border text-right">信访问题及诉求</th>
	       	 	<td class="bottom-border right-border"  colspan="3">
	         		<textarea class="span12" rows="4"  id="XF_CONTENT"    fieldname="XF_CONTENT" name = "XF_CONTENT" ></textarea>	         	
	       	 	</td>
	       	 </tr>
	       	 <tr>
	       	 	<th width="15%" class="right-border bottom-border text-right">信访时间</th>
	       	 	<td colspan="3" class="bottom-border right-border">
	       	 		<div id="dis">
					  <input id="XF_DATE" type="text" fieldtype="date" fieldformat="YYYY-MM-DD HH:mm:ss" fieldname="XF_DATE" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy/MM/dd HH:ss:mm'})" name = "XF_DATE" readonly="readonly"   />
					</div>
				</td>
	       	 </tr>	
	       	 <tr>
	       	 	<th width="15%" class="right-border bottom-border text-right">接待人</th>
	       	 	<td class="bottom-border right-border" colspan="3">
	       	 	<input class="span12"  id="RECEIVER" type="text"  fieldname="RECEIVER" name = "RECEIVER"  />	         	        	
	       	 	</td>	       	 	
	       	 </tr>
	       	 <tr>
	       	 	<th width="15%" class="right-border bottom-border text-right">现场调解地点</th>
	       	 	<td class="bottom-border right-border" colspan="3">
	       	 	<input class="span12"  id="XC_ADDRESS" type="text"  fieldname="XC_ADDRESS" name = "XC_ADDRESS"  />	         	        	
	       	 	</td>
	       	 </tr>
	       	 <tr>
	       	 	<th width="15%" class="right-border bottom-border text-right">现场调解时间</th>
	       	 	<td colspan="3" class="bottom-border right-border">
	       	 	<div id="dis">
					  <input id="XC_DATE" type="text" fieldtype="date" fieldformat="YYYY-MM-DD HH:mm:ss" fieldname="XC_DATE" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy/MM/dd HH:ss:mm'})" name = "XC_DATE" readonly="readonly"   />
					  </div>
				</td>
	       	 </tr>
	       	 <tr>
	       	 	<th width="15%" class="right-border bottom-border text-right">现场调解情况及处理意见</th>
	       	 	<td class="bottom-border right-border"  colspan="3">
	         		<textarea class="span12" rows="4"  id="XC_TJYJ"    fieldname="XC_TJYJ" name = "XC_TJYJ" ></textarea>	         	
	       	 	</td>
	       	 </tr>
	       	 <tr>
	       	 	<th width="15%" class="right-border bottom-border text-right">现场处理参加人员</th>
	       	 	<td class="bottom-border right-border"  colspan="3">
	       	 	<input class="span12"  id="XC_USER" type="text"  fieldname="XC_USER" name = "XC_USER"  />	         	        	
	       	 	</td>	      
	       	</tr>	
	      	<tr>
	       	 	<th width="15%" class="right-border bottom-border text-right">现场存在问题及处理请求</th>
	       	 	<td class="bottom-border right-border"  colspan="3">
	         		<textarea class="span12" rows="4"  id="XC_CONTENT"    fieldname="XC_CONTENT" name = "XC_CONTENT" ></textarea>	         	
	       	 	</td>
	       	</tr>
	       	<tr>
	       	 	<th width="15%" class="right-border bottom-border text-right">责任人意见</th>
	       	 	<td class="bottom-border right-border" colspan="3">
	       	 	<input class="span12"  id="ZR_YJ" type="text"  fieldname="ZR_YJ" name = "ZR_YJ"  />	         	        	
	       	 	</td>	       	 	
	       	 </tr>
	       	 <tr>
	       	 	<th width="15%" class="right-border bottom-border text-right">信访人意见</th>
	       	 	<td class="bottom-border right-border" colspan="3">
	       	 	<input class="span12"  id="XF_YJ" type="text"  fieldname="XF_YJ" name = "XF_YJ"  />	         	        	
	       	 	</td>
	       	 </tr>
	       	 <tr>
	       	 	<th width="15%" class="right-border bottom-border text-right">整改日期</th>
	       	 	<td colspan="3" class="bottom-border right-border">
	       	 	<div id="dis">
					  <input id="ZG_DATE" type="text" fieldtype="date" fieldformat="YYYY-MM-DD HH:mm:ss" fieldname="ZG_DATE" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy/MM/dd HH:ss:mm'})" name = "ZG_DATE" readonly="readonly"   />
					  </div>
				</td>
	       	 </tr>
	       	 <tr>
	       	 	<th width="15%" class="right-border bottom-border text-right">跟踪结果信息</th>
	       	 	<td class="bottom-border right-border" colspan="3">
	         		<textarea class="span12" rows="4"  id="RESULT"    fieldname="RESULT" name = "RESULT" ></textarea>	         	
	       	 	</td>
	       	</tr>	
	       	<tr>
	       		<th width="15%" class="right-border bottom-border text-right">附件</th>
				<td class="bottom-border right-border" colspan="1">
					<input type="hidden" id="target_uid" fieldname="target_uid" />
					<div>
					<span class="btn btn-fileUpload" id="addFileSpan" onclick="selectFile(this);" file_type="3014">
					 <i class="icon-plus"></i> 
					 <span>上传...</span> 
					</span>
					<table role="presentation" class="table table-striped">
					<tbody class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" file_type="3014"></tbody>
					</table>
					</div> 
					<jsp:include page="/jsp/file_upload/fileupload_util.jsp" flush="true"/>
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
         <input type="hidden" name="resultXML" id = "resultXML">
       	 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">		
 	</FORM>
 </div>
</body>
<script>
</script>
</html>