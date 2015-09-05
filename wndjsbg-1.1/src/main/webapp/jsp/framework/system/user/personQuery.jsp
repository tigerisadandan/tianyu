<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>实例页面-用户信息查询</title>

<script type="text/javascript" charset="UTF-8">
 var id,account,json,rowindex,rowValue;
 var recordName;
  var controllername= "${pageContext.request.contextPath }/userController.do";


	//计算本页表格分页数
	function setPageHeight(){
		var height = g_iHeight-pageTopHeight-pageTitle-pageQuery-getTableTh(1)-pageNumHeight;
		var pageNum = parseInt(height/pageTableOne,10);
		$("#DT1").attr("pageNum",pageNum);
	}
	
	$(function() {
		var btn = $("#queryBtn");
		btn.click(function() {
	        //生成json串
			var data = combineQuery.getQueryCombineData(queryForm,frmPost, DT1);
			//调用ajax插入
			defaultJson.doQueryJsonList(controllername+"?queryUser", data, DT1);
		});
	});
	
	//页面默认参数
	$(document).ready(function() { 
		setPageHeight();
        //生成json串
        g_bAlertWhenNoResult = false;
		var data = combineQuery.getQueryCombineData(queryForm,frmPost, DT1);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"?queryUser", data, DT1);
        g_bAlertWhenNoResult = true;
	}); 
	
	// 清除表单
	$(function() {
		$("#query_clear").click(function() {
	       $("#queryForm").clearFormResult();
		});
	});
	
	function tr_click(obj,tabListid) {
		//obj为行数据的json 对象，可以通过obj.XMMC获得选中行的项目名称
		rowindex = $("#DT1").getSelectedRowIndex();//获得选中行的索引
		rowValue = $("#DT1").getSelectedRow();//获得选中行的json对象
		id = obj.USERS_UID;
		name = obj.USER_NAME;
		account = obj.LOGON_NAME ;
		var mima = obj.MIMA;
		var isAdmin = obj.ADMIN_Y;
		var isUse = obj.USE_Y;
		$("#executeFrm").setFormValues(obj);
		$("#MIMA").val(mima);
		$("#MIMA1").val(mima);
		//是否是管理员
		if(isAdmin == '是'){
			$("#ADMIN_Y").prop("checked",true);
		}else if(isAdmin == '否'){
			$("#ADMIN_Y").prop("checked",false);
		}
		//是否启用
		if(isUse == '启用'){
			$("#USE_Y").prop("checked",true);
		}else if(isUse == '禁用'){
			$("#USE_Y").prop("checked",false);
		}
	}
	
	// 点击添加按钮 清空表单
	$(function() {
		var btn = $("#insertBtn");
		btn.click(function() {
			sign = 0;//添加时，先把值改为0，防止验证登录名重复时出现问题
			recordName = "";//添加时，先把值改为空，防止验证登录名重复时出现问题
			$("#LOGON_NAME").css("border","1px solid #ccc");//把登陆名文本框改为正常颜色。
			$("#USER_NAME").css("border","1px solid #ccc");//把登陆名文本框改为正常颜色。
			$("#MIMA").css("border","1px solid #ccc");//把密码文本框改为正常颜色。
			$("#MIMA1").css("border","1px solid #ccc");//把确认密码文本框改为正常颜色。
			//clearFileTab();
			$("#ywid").val("");
			// 当添加用户信息时，解除ID为【ACCOUNT】的disabled属性
			$("#myModalLabel").html("用户管理>新增");
			//$("#LOGON_NAME").removeAttr("disabled");
			$("#executeFrm").clearFormResult(); 
			$("#DT1").cancleSelected();
			id = "null";
			//设置默认密码为123456
			$("#MIMA").val("123456");
			$("#MIMA1").val("123456");
			//默认选中用户为启用
			$("#USE_Y").prop("checked",true);
			//添加用户时，打开密码框
			$("#pwdTR").show();
			$("#rePwdTR").show();
		});
	});
	
	// 点击修改按钮
	$(function() {
		var btn = $("#updateBtn");
		btn.click(function() {
			recordName = $("#LOGON_NAME").val();
			sign = 0;//修改时，先把值改为0，防止验证登录名重复时出现问题
			$("#LOGON_NAME").css("border","1px solid #ccc");//把登陆名文本框改为正常颜色。
			$("#USER_NAME").css("border","1px solid #ccc");//把用户姓名文本框改为正常颜色。
			if($("#DT1").getSelectedRowIndex()==-1) {
				//没有选中一条记录
				//xAlert("提示信息",'请选择一条记录');
				requireSelectedOneRow();
				// 没有选中结果集行的时候，不显示弹出层，将【修改用户信息】按钮的【data-toggle】属性修改成空即可
				btn.attr("data-toggle","");
			} else {
				$("#myModalLabel").html("用户管理>修改");
				// 选中时，需要恢复【修改用户信息】按钮的【data-toggle】属性值
				btn.attr("data-toggle","modal");
				
				// 当修改用户信息时，由于【用户登录名ACCOUNT】是主键，所以不能修改。
				//$("#LOGON_NAME").attr("disabled","disabled");
				
				//修改用户时，隐藏密码框
				$("#pwdTR").hide();
				$("#rePwdTR").hide();
			}
			//clearFileTab();
			$("#ywid").val("");
			var rowValue = $("#DT1").getSelectedRow();
			var tempJson = convertJson.string2json1(rowValue);
			queryUserFile("0038",tempJson.ACCOUNT,"","");
			queryUserFile("0039",tempJson.ACCOUNT,"","");
			setFileData(tempJson.ACCOUNT,"","","");
		});
	});
	//----------------------------------------
	//-查询文件信息，并插入到表格(这个是用户和签名特殊要求的)
	//-@param ywid 业务ID，可以暂时理解为批次编号
	//-@param glid1 项目ID
	//-@param sjbh	事件编号
	//-@param ywlx	业务类型
	//----------------------------------------
	function queryUserFile(fjlb,glid1,sjbh,ywlx,url){
		var obj = new Object();
		obj.FSLB = fjlb;
		obj.ACCOUNT = glid1;
		var data = JSON.stringify(obj);
			var data1 = defaultJson.packSaveJson(data);
			$.ajax({
			url : controllername+"?queryUserFile",
			data : data1,
			cache : false,
			async :	false,
			dataType : "json",
			success : function(result) {
				//clearFileTab("query");
				insertFileTab(result.msg);
			}
		});
	}
	/*
	// 点击删除按钮
	$(function() {
		var btn = $("#deleteBtn");
		btn.click(function() {
		 	if($("#DT1").getSelectedRowIndex()==-1) {
				//xAlert("提示信息",'请选择一条记录');
		 		requireSelectedOneRow();
			} else {
				xConfirm("提示信息","是否确认删除！");
				//生成json串
				var data = Form2Json.formToJSON(executeFrm);
				//组成保存json串格式
				var data1 = defaultJson.packSaveJson(data);
				//通过判断id是否为空来判断是插入还是修改
				$('#ConfirmYesButton').one("click",function(obj) {
					var success = defaultJson.doUpdateJson(controllername + "?executeUser&id="+id+"&update=3", data1, DT1);
					
					if(success == true) {
						$("#DT1").setSelect(rowindex);
				//		var dd = {id:1,name:2};//此处为入参
						var index = $("#DT1").getSelectedRowIndex();//获得选中行的索引
						var value = $("#DT1").getSelectedRow();//获得选中行的json对象

				        $("#DT1").removeResult(index);
					}
				});  
				
			} 
		});
	});
	*/
	// 点击密码初始化
	$(function() {
		var btn = $("#resetPw");
		btn.click(function() {
		 	if($("#DT1").getSelectedRowIndex()==-1) {
		 		requireSelectedOneRow();
			} else {
				//生成json串
				var data = Form2Json.formToJSON(executeFrm);
				//组成保存json串格式
				var obj = convertJson.string2json1(rowValue);
				xConfirm("提示信息","是否确认密码重置！");
				$('#ConfirmYesButton').unbind();
				$('#ConfirmYesButton').one("click",function(){
					$.ajax({
						url : controllername+"?resetPw&account="+account,
						cache : false,
						async : false,
						dataType : 'json',
						success : function(response) {
							xSuccessMsg("密码重置成功","");
						}
					});
				});
			} 
		});
	});
	var sign;
	// 检测用户名是否重复
	$(function() {
		var userAccount = $("#LOGON_NAME");
		userAccount.blur(function() {
			//如果登录名输入为空，不验证
			if(userAccount.val()=="" || userAccount.val()==null){
				return;
			}else if(userAccount.val()==recordName){
				sign = 0;
				return;
			}
			
			$.ajax({
				url : controllername+"?queryUnique&account="+$("#LOGON_NAME").val(),
		//		data : data,
				cache : false,
				async : false,
				dataType : 'json',
				success : function(response) {
					var result = eval("(" + response + ")");
					sign = result.sign;
					//xAlert("提示信息", result.isUnique);
					xSuccessMsg(result.isUnique,"");
					if(sign == 1){
						$("#LOGON_NAME").css("border","1px solid red");
					}else if(sign == 2){
						$("#LOGON_NAME").css("border","1px solid #ccc");
					}
				}
			});
		});
	});
	
	// 点击保存按钮
	$(function() {
		
		var saveUserInfoBtn = $("#saveUserInfo");
		saveUserInfoBtn.click(function() {
			if(sign == 1) {
				 //xAlert("提示信息","登录用户名重复，请重新填写");
				 xFailMsg("登录用户名重复，请重新填写","");
				 return;
			}
			
			if($("#executeFrm").validationButton()) {
				 if(!checkRePwd()) return;
				//生成json串
				var data = Form2Json.formToJSON(executeFrm);
				//组成保存json串格式
				var data1 = defaultJson.packSaveJson(data);
				//通过判断id是否为空来判断是插入还是修改
				
				var success = false;
				if(id == null || id == "null" || id == "indefined") {
					success = defaultJson.doInsertJson(controllername + "?executeUser&id="+id+"&update=1", data1, DT1);
				} else {
					success = defaultJson.doUpdateJson(controllername + "?executeUser&id="+id+"&update=2", data1, DT1);
				}
				
				if(success == true) {
					$("#myModal").modal("hide");
				} else {
					//	失败
				}
				$("#queryBtn").click();
				//clearFileTab();
				$("#ywid").val("");
			}
		});
	});

	//父页面调用查询页面的方法
	$(function() {
		$("#awardRole").click(function() {
			if($("#DT1").getSelectedRowIndex()==-1) {
				 //xAlert("提示信息","请选择一条用户信息");
				 //requireSelectedOneRow();
				 xInfoMsg("请选择一条用户信息","");
				 return;
			}
			$(window).manhuaDialog({"title" : "用户管理>授予角色","type" : "text","content" : "${pageContext.request.contextPath}/jsp/framework/system/user/awardRole.jsp?account="+account+"&name="+name,"modal":"2"});
		});
	});
	
	//跳转到分配权限页面
	$(function() {
		$("#awardMenu").click(function() {
			if($("#DT1").getSelectedRowIndex()==-1) {
				 //xAlert("提示信息","请选择一条角色信息");
				 //requireSelectedOneRow();
		 		requireSelectedOneRow();
				return;
			}
			$(window).manhuaDialog({"title" : "用户管理>赋予权限","type" : "text","content" : "${pageContext.request.contextPath}/jsp/framework/system/role/awardMenu.jsp?type=user&id="+id,"modal":"2"});
			//$(window).manhuaDialog({"title" : "用户管理>赋予权限","type" : "text","content" : "${pageContext.request.contextPath}/jsp/framework/system/role/awardMenu.jsp?account=aaaa","modal":"2"});
		});
	});
	
	//确认密码失去焦点时触发!
	$(function() {
		$("#MIMA1").blur(function() {
			checkRePwd();
		});
	});
	
	//验证两次输入的密码是否一致
	function checkRePwd(){
		var pwd1 = $("#MIMA").val();
		var pwd2 = $("#MIMA1").val();
		if(pwd1!=pwd2){
			xSuccessMsg("两次输入的密码不一致！","");
			$("#MIMA1").css("border","1px solid red");
			return false;
		}
		$("#MIMA1").css("border","1px solid #ccc");
		return true;
	}
	//点击回车，触发查询事件
	function queryOperator(e){
		if(e.keyCode == 13){
			$("#queryBtn").click();
		}
	}
</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
	<p></p>
	<div class="row-fluid">
		<div class="B-small-from-table-autoConcise">
			<h4 class="title">用户管理
			<span class="pull-right">
				<a href="#myModal" role="button" class="btn" data-toggle="modal" id="insertBtn">新增</a>
				<a href="#myModal" role="button" class="btn" data-toggle="modal" id="updateBtn">修改</a>
				<!--  <a href="#myModal" role="button" class="btn" id="deleteBtn">删除</a> -->
				<a href="#myModal" role="button" class="btn" id="awardRole">授予角色</a>
				<a href="#myModal" role="button" class="btn" id="awardMenu">权限配置</a>
				<button id="resetPw" class="btn" type="button" name="resetPw" >重置密码</button>
			</span>
			</h4>
		<form method="post" id="queryForm"  >
		<table class="B-table" width="100%">
		<!--可以再此处加入hidden域作为过滤条件 -->
			<TR  style="display:none;">
				<TD>
					<input type="text" class="span12" kind="text"  fieldname="rownum" value="1000" operation="<=" >
				</TD>
			</TR>
		<!--可以再此处加入hidden域作为过滤条件 -->
			<tr>
				<th width="5%" class="right-border bottom-border text-right">登录名</th>
				<td width="10%" class="right-border bottom-border">
					<input class="span12" type="text" placeholder="" name="LOGON_NAME" fieldname="LOGON_NAME" operation="like" logic="and" onkeydown="queryOperator(event);" ></td>
				<th width="5%" class="right-border bottom-border text-right">姓名</th>
				<td width="10%" class="right-border bottom-border">
					<input class="span12" type="text" placeholder="" name="name" fieldname="USER_NAME" operation="like" logic="and" onkeydown="queryOperator(event);" ></td>
		        <!--  
		        <th width="5%" class="right-border bottom-border text-right"></th>
				<td width="8%" class="right-border bottom-border">
					<select class="span12" name="SEX" fieldname="SEX" id="SEX" kind="dic" src="XB" operation="=" logic="and" defaultMemo="全部"></select>
				</td>
				<th width="5%" class="right-border bottom-border text-right"></th>
				<td width="20%" class="bottom-border">
					<select class="span12" placeholder="" name="department" fieldname="DEPARTMENT" defaultMemo="全部" kind="dic" src="T#VIEW_YW_ORG_DEPT:ROW_ID:DEPT_NAME:ACTIVE_FLAG='1' ORDER BY SORT" operation="="  logic="and"></select></td>
				-->
				<td width="32%"  class="text-left bottom-border text-right">
					<button	id="queryBtn" class="btn btn-link" type="button"><i class="icon-search"></i>查询</button>
                    <button id="query_clear" class="btn btn-link" type="button"><i class="icon-trash"></i>清空</button>
	            </td>	
			</tr>
		</table>
		</form>
	
	<div style="height:5px;"> </div>		
	<div class="overFlowX"> 
		<table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single">
			<thead>
				<tr>
					<th name="XH" id="_XH" width="3%">#</th>
					<th fieldname="USERS_UID" tdalign="center" hidden >&nbsp;&nbsp;</th>
					<th fieldname="MIMA" tdalign="center" hidden >&nbsp;&nbsp;</th>
					<th fieldname="LOGON_NAME" tdalign="center" >&nbsp;登录名&nbsp;</th>
					<th fieldname="USER_NAME" tdalign="center" >&nbsp;用户姓名&nbsp;</th>
					<th fieldname="USE_Y" tdalign="center" width="5%">&nbsp;是否启用&nbsp;</th>
					<th fieldname="ADMIN_Y" tdalign="center" width="5%">&nbsp;是否是管理员&nbsp;</th>
					<th fieldname="CREATED_DATE" tdalign="center" >&nbsp;创建时间&nbsp;</th>
					<th fieldname="DESCRIPTION" tdalign="center" >&nbsp;描述&nbsp;</th>
				</tr>
			</thead>
		    <tbody>
		    </tbody>
		</table>
		            
		</div>
		</div>
	</div>
</div>

<div align="center">
	<FORM name="frmPost" method="post" style="display:none" target="_blank" id ="frmPost">
		<!--系统保留定义区域-->
		<input type="hidden" name="queryXML" id = "queryXML">
		<input type="hidden" name="txtXML" id = "txtXML">
		<input type="hidden" name="txtFilter"  order="asc" fieldname = "to_number(sort)"	id = "txtFilter">
		<input type="hidden" name="resultXML" id = "resultXML">
		<input type="hidden" name="queryResult" id = "queryResult">
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData">
	</FORM>
</div>

<!-- Modal -->
		<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header" style="background:#0866c6;">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					<i class="icon-remove icon-white"></i>
				</button>
				<h3 id="myModalLabel" style="color:white;">
					添加用户信息
				</h3>
			</div>
			<div class="modal-body">
				<form method="post" id="executeFrm">
					<table class="B-table">
						<tr>
							<th width="30%" class="right-border bottom-border">
								用户登录名
							</th>
							<td class="right-border bottom-border">
								<input type="text" placeholder="必填" check-type="required"
									maxlength="32" fieldname="LOGON_NAME" name="LOGON_NAME" id="LOGON_NAME">
							</td>
						</tr>
						<tr>
							<th width="30%" class="right-border bottom-border">
								用户姓名
							</th>
							<td class="right-border bottom-border">
								<input type="text" placeholder="必填" check-type="required"
									maxlength="32" fieldname="USER_NAME" name="USER_NAME" id="USER_NAME">
							</td>
						</tr>
							<tr id="pwdTR">
								<th width="30%" class="right-border bottom-border">
									密码
								</th>
								<td class="right-border bottom-border">
									<input type="password" fieldname="MIMA" name="MIMA" id="MIMA" placeholder="必填" check-type="required" /><br />
									<span style="color:red;">默认初始密码为:123456</span>
								</td>
							</tr>
							<tr id="rePwdTR">
								<th width="30%" class="right-border bottom-border">
									确认密码
								</th>
								<td class="right-border bottom-border">
									<input type="password" maxlength="32" fieldname="MIMA1" name="MIMA1" id="MIMA1" />
								</td>
							</tr>
						<tr>
							<th width="30%" class="right-border bottom-border">
								是否管理员
							</th>
							<td class="right-border bottom-border" style="height: 45px;">
								<input type="checkbox" maxlength="32"  fieldname="ADMIN_Y" name="ADMIN_Y" id="ADMIN_Y" value="Y" />
							</td>
						</tr>
						<tr>
							<th width="30%" class="right-border bottom-border">
								是否启用
							</th>
							<td class="right-border bottom-border">
								<input type="checkbox" maxlength="32"  fieldname="USE_Y" name="USE_Y" id="USE_Y" value="Y" checked="checked" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div class="modal-footer">
				<button class="btn" id="saveUserInfo">
					保存
				</button>
				<button class="btn" data-dismiss="modal" aria-hidden="true">
					关闭
				</button>
			</div>
		</div>
		<jsp:include page="/jsp/file_upload/fileupload_config.jsp" flush="true" />
</body>
</html>