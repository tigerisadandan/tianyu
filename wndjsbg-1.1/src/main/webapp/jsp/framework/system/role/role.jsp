<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>

<title>实例页面-角色信息查询</title>

<script type="text/javascript" charset="UTF-8">
 var id,json,rowindex,rowValue;
  var controllername= "${pageContext.request.contextPath }/roleController.do";
	$(function() {
		var btn = $("#queryBtn");
		btn.click(function() {
	        //生成json串
			var data = combineQuery.getQueryCombineData(queryForm,frmPost, DT1);
			//调用ajax插入
			defaultJson.doQueryJsonList(controllername+"?queryRole", data, DT1);
		});
	});

  	//计算本页表格分页数
  	function setPageHeight() {
  		var height = g_iHeight-pageTopHeight-pageTitle-pageQuery-getTableTh(1)-pageNumHeight;
  		var pageNum = parseInt(height/pageTableOne,10);
  		$("#DT1").attr("pageNum",pageNum);
  	}
	
	//页面默认参数
	$(document).ready(function() { 
		setPageHeight();
        //生成json串
        g_bAlertWhenNoResult = false;
		var data = combineQuery.getQueryCombineData(queryForm,frmPost, DT1);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"?queryRole", data, DT1);
        g_bAlertWhenNoResult = true;
	}); 
	
	function init() {
		var data = combineQuery.getQueryCombineData(queryForm,frmPost, DT1);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"?queryRole", data, DT1);
	}
	
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
		id = obj.ROLE_ID;
		name = obj.NAME;
		$("#executeFrm").setFormValues(obj);
	//	alert(rowindex);
	}
	
	// 点击添加按钮 清空表单
	$(function() {
		var btn = $("#insertBtn");
		btn.click(function() {
			// 当添加用户信息时，解除ID为【ACCOUNT】的disabled属性
			$("#executeFrm").clearFormResult(); 
			id = "null";
			sign = 0;//添加时，先把值改为0，防止验证角色名重复时出现问题
			recordName = "";//添加时，先把值改为空，防止验证角色名重复时出现问题
			$("#NAME").css("border","1px solid #ccc");//把角色名文本框改为正常颜色。
			$("#ROLETYPE").css("border","1px solid #ccc");//把角色类别文本框改为正常颜色。
		});
	});
	
	// 点击修改按钮
	$(function() {
		var btn = $("#updateBtn");
		btn.click(function() {
			recordName = $("#NAME").val();//记录修改前的名字，防止去后台查名字
			sign = 0;//修改时，先把值改为0，防止验证角色名重复时出现问题
			$("#NAME").css("border","1px solid #ccc");//把角色名文本框改为正常颜色。
			$("#ROLETYPE").css("border","1px solid #ccc");//把角色类别文本框改为正常颜色。
			if($("#DT1").getSelectedRowIndex()==-1) {
				//xAlert("提示信息",'请选择一条记录');
				requireSelectedOneRow();
				// 没有选中结果集行的时候，不显示弹出层，将【修改用户信息】按钮的【data-toggle】属性修改成空即可
				btn.attr("data-toggle","");
			} else {
				var rowValue = $("#DT1").getSelectedRowJsonObj();//获得选中行的json对象
				$("#executeFrm").setFormValues(rowValue);
				// 选中时，需要恢复【修改用户信息】按钮的【data-toggle】属性值
				btn.attr("data-toggle","modal");
			}
		});
	});
	
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
					var success = defaultJson.doUpdateJson(controllername + "?executeRole&operatorSign=3", data1, DT1);
					
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
	
	var sign;
	// 检测角色信息是否重复
	$(function() {
		var roleAccount = $("#NAME");
			roleAccount.blur(function() {
				//如果角色名输入为空，不验证
				if(roleAccount.val()=="" || roleAccount.val()==null){
					sign = 0;
					return;
				}else if(roleAccount.val()==recordName){
					sign = 0;
					return;
				}
				$.ajax({
					url : controllername+"?queryUnique&roleName="+roleAccount.val(),
			//		data : data,
					cache : false,
					async : false,
					dataType : 'json',
					success : function(response) {
						var result = eval("(" + response + ")");
						sign = result.sign;
						//xAlert("提示信息", result.isUnique);
						xSuccessMsg(result.isUnique,"");		//角色信息不重复信息提示
						if(sign == 1){
							$("#NAME").css("border","1px solid red");
						}else if(sign == 0){
							$("#NAME").css("border","1px solid #ccc");
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
				 xFailMsg("角色名重复，请重新填写","");
				 return;
			}
			
			if($("#executeFrm").validationButton()) {
				//生成json串
				var data = Form2Json.formToJSON(executeFrm);
				//组成保存json串格式
				var data1 = defaultJson.packSaveJson(data);
				//通过判断id是否为空来判断是插入还是修改
				
				var success = false;
				if(id == null || id == "null" || id == "undefined") {
					success = defaultJson.doInsertJson(controllername + "?executeRole&operatorSign=1", data1, DT1);
				} else {
					success = defaultJson.doUpdateJson(controllername + "?executeRole&operatorSign=2", data1, DT1);
				}
				
				if(success == true) {
					$("#myModal").modal("hide");
					init();
				} else {
					//	失败
				}
			} else {
				requireFormMsg();
			  	return;
			}
		});
	});


	//父页面调用查询页面的方法
	$(function() {
		$("#awardMenu").click(function() {
			if($("#DT1").getSelectedRowIndex()==-1) {
				 //xAlert("提示信息","请选择一条角色信息");
				 //requireSelectedOneRow();
		 		requireSelectedOneRow();
				return;
			}
			$(window).manhuaDialog({"title" : "角色管理>赋予权限","type" : "text","content" : "${pageContext.request.contextPath}/jsp/framework/system/role/awardMenu.jsp?type=role&id="+id,"modal":"2"});
		});
	});
	//父页面调用查询页面的方法
	$(function() {
		$("#awardPerson").click(function() {
			if($("#DT1").getSelectedRowIndex()==-1) {
				 //xAlert("提示信息","请选择一条角色信息");
				 //requireSelectedOneRow();
		 		requireSelectedOneRow();
				return;
			}
			$(window).manhuaDialog({"title" : "角色管理>将角色授予人员>请选择菜单","type" : "text","content" : "${pageContext.request.contextPath}/jsp/framework/system/role/awardPerson.jsp?id="+id+"&name="+name,"modal":"2"});
		});
	});	
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
			<h4 class="title">角色信息查询
			<span class="pull-right">
				<a href="#myModal" role="button" class="btn" data-toggle="modal" id="insertBtn">新增</a>
				<a href="#myModal" role="button" class="btn" data-toggle="modal" id="updateBtn">修改</a>
				<a href="#myModal" role="button" class="btn" id="awardMenu">权限配置</a>
				<a href="#myModal" role="button" class="btn" id="awardPerson">授予人员角色</a>
			</span>
		</h4>
		<form method="post" id="queryForm"  >
		<table class="B-table" width="100%">
		<!--可以再此处加入hidden域作为过滤条件 -->
			<TR  style="display:none;">
				<TD>
					<input type="text" class="span12" kind="text"  fieldname="rownum"  value="1000" operation="<=" >
				</TD>
			</TR>
		<!--可以再此处加入hidden域作为过滤条件 -->
			<tr>
				<th width="5%" class="right-border bottom-border text-right">角色名称</th>
				<td width="10%" class="right-border bottom-border">
					<input class="span12" type="text" placeholder="" name="NAME" fieldname="NAME" operation="like" logic="and" onkeydown="queryOperator(event);" ></td>
				 <th width="5%" class="right-border bottom-border">角色类别</th>
          		<td width="10%" class="right-border bottom-border">
          			<select name="QROLETYPE" fieldname="ROLETYPE" id="QROLETYPE" kind="dic" src="JSLB" operation="=" logic="and" onkeydown="queryOperator(event);" defaultmemo="全部">

          			</select>
          		</td>
				<td width="40%"  class="bottom-border text-right">
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
					<th name="XH" id="_XH">&nbsp;#&nbsp;</th>
					<th fieldname="NAME" >&nbsp;角色名称&nbsp;</th>
					<th fieldname="ROLETYPE">&nbsp;角色类别&nbsp;</th>
					<th fieldname="S_MEMO" >&nbsp;汉字描述&nbsp;</th>
					<th fieldname="GXSJ" tdalign="center">&nbsp;录入时间&nbsp;</th>
					<th fieldname="GXR" tdalign="center" >&nbsp;录入人&nbsp;</th>
					<!--  <th fieldname="GXBMMC">&nbsp;创建者部门名称&nbsp;</th> -->
				</tr>
			</thead>
		    <tbody></tbody>
		</table>
		            
		</div>
		</div>
	</div>
</div>

<div align="center">
	<FORM name="frmPost" method="post" style="display:none" target="_blank" id ="frmPost">
		<!--系统保留定义区域-->
		<input type="hidden" name="queryXML" id="queryXML">
		<input type="hidden" name="txtXML" id="txtXML">
		<input type="hidden" name="txtFilter" order="desc" fieldname="LRSJ" id="txtFilter">
		<input type="hidden" name="resultXML" id="resultXML">
		<input type="hidden" name="queryResult" id="queryResult">
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData">
	</FORM>
</div>

<!-- Modal -->
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header" style="background:#0866c6;">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="icon-remove icon-white"></i></button>
    <h3 id="myModalLabel" style="color:white;">角色信息管理</h3>
  </div>
  <div class="modal-body">
    <form method="post" id="executeFrm"  >
      <table class="B-table" >
         <TR  style="display:none;">
	        <TD class="right-border bottom-border"></TD>
			<TD class="right-border bottom-border">
			<input type="text" kind="text" fieldname="ROLE_ID" name="ROLE_ID" id="ROLE_ID">
			</TD>
        </TR>
        <tr>
          <th width="30%" class="right-border bottom-border">角色名称</th>
          <td class="right-border bottom-border">
          	<input type="text" placeholder="必填"  check-type="required" maxlength="64" fieldname="NAME" name="NAME"  id="NAME"></td>
        </tr>
        <tr>
          <th width="30%" class="right-border bottom-border">角色类别</th>
          <td class="right-border bottom-border">
          	<select name="ROLETYPE" fieldname="ROLETYPE" id="ROLETYPE" check-type="required" kind="dic" src="JSLB" defaultmemo="请选择">

          	</select>
          	</td>
        </tr>
        <tr>
          <th width="30%" class="right-border bottom-border">汉字描述</th>
          <td class="right-border bottom-border">
          	<input type="text" maxlength="64" fieldname="S_MEMO" name="S_MEMO" id="S_MEMO"></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="modal-footer">
    <button class="btn" id="saveUserInfo">保存</button>
    <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
  </div>
</div>
</body>
</html>