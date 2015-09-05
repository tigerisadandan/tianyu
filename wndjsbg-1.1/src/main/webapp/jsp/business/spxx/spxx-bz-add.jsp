<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>审批步骤添加</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/spxx/buSpBzController/";
var controllernameCyz= "${pageContext.request.contextPath }/spxx/buSpBzCyzController.do";
var controllernameWj= "${pageContext.request.contextPath }/bzwj/buSpBzwjController.do";
var type= "insert";
var SPBZ_UID;
//页面初始化
$(function() {
	init();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,buSpYwxxList);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"query",data,buSpYwxxList);
	});
	//按钮绑定事件(保存)
	$("#btnSave").click(function() {

	
		if($("#buSpYwxxForm").validationButton())
		{
		    //生成json串
		    var data = Form2Json.formToJSON(buSpYwxxForm);
		  //组成保存json串格式
		    var data1 = defaultJson.packSaveJson(data);
		  //调用ajax插入
    			var flag = defaultJson.doInsertJson(controllername + "insert", data1);
    			if(flag){
        			window.opener.doQueryBz();
        			var r=confirm("保存成功!是否添加步骤文件?"); 
        			if(r){
    			    $("#btnSave").hide();
        			var result=$("#resultXML").val();
					var res = dealSpecialCharactor(result);
			        var subresultmsgobj = defaultJson.dealResultJson(res);
			        $("#qSPBZ_UID").val(subresultmsgobj.SPBZ_UID);
			        SPBZ_UID=subresultmsgobj.SPBZ_UID;
			        showBzwj();
        			}else{
        			window.close();
        			}
					
					
        		}
        	

		}else{
			requireFormMsg();
		  	return;
		}	
	});
	
	//bzwj文件新增
	$("#bzwjnew").click(function() {
	window.open("${pageContext.request.contextPath}/jsp/business/bzwj/bu_sp_bzwj-add.jsp?type=insert&SPBZ_UID="+SPBZ_UID);
	//$(window).manhuaDialog({"title":"施工内容>新增","type":"text","content":"${pageContext.request.contextPath }/jsp/business/gongcheng/projects-gongcheng-add.jsp?type=insert","modal":"1"});
		//$(window).manhuaDialog({"title":"步骤文件>新增","type":"text","content":"${pageContext.request.contextPath }/jsp/business/bzwj/bu_sp_bzwj-add.jsp?type=insert","modal":"2"});
	});
	//bzwj文件删除 
	$("#bzwjdel").click(function() {
		if($("#DT1").getSelectedRowIndex()==-1) {
			//xAlert("提示信息",'请选择一条记录');
	 		requireSelectedOneRow();
	 		return
		} 

		var rowValue = $("#DT1").getSelectedRow();
		var index = $("#DT1").getSelectedRowIndex();
		var tempJson=eval("("+rowValue+")");//string转json
		var uid=tempJson.BZWJ_UID;
		$("#BZWJ_UID").val(uid);
	
		var data = Form2Json.formToJSON(wjdelQuery);
		  //组成保存json串格式
		var data1 = defaultJson.packSaveJson(data);
		var success=defaultJson.doInsertJson(controllernameWj + "?delete", data1);
		if(success == true) {
			 $("#DT1").removeResult(index);
		}
	});
	
});

//页面默认参数
function initwj(){
	//生成json串
	var data = combineQuery.getQueryCombineData(bzwjQuery,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllernameWj+"?query",data,DT1);
}
//页面默认参数
function init(){
	getParent();
	addUsers();
	$("#userBlock").hide()
}
function setParent(uid,name){
	$("#P_SPYW_UID").attr("code",uid);
	$("#P_SPYW_UID").val(name);
}
function getParent(){
	//puid = 1 从父页面的添加子流程进入,
	//puid = 0 从父页面的添加直接进入
	var p_yw_uid = $(window.opener.document).find("input[name='currYwid']").val();
	var mc = $(window.opener.document).find("input[name='currYwmc']").val();
	$("#SPYW_UID").val(mc);
	$("#SPYW_UID").attr("code",p_yw_uid);
}


//删除参与者
function removeCurr(obj){
	$(obj).closest("span").remove();
}

//添加参与者
function submitUsers(){
	var users = "";
	$("#chooseUsers").empty();
	var showHtml = "";
	$("input:checkbox[name='cyz']:checked").each(function(){
		showHtml += "<span style='padding-right:10px;'>";
		showHtml += $(this).attr("text");
		showHtml += "<input type='hidden' id='user_"+$(this).val()+"' value='"+$(this).val()+"' fieldname='USERS_UID'>";
		showHtml += "<a href='javascript:void(0)' onclick='removeCurr(this)'>";
		showHtml += "<i class='icon-remove-sign'></i>";
		showHtml += "</a>";
		showHtml += "</span>";
	})
	$("#chooseUsers").append(showHtml);
}

function addUsers(){
	$.ajax({
		url : controllernameCyz+"?getUsers&uid="+$("#SPBZ_UID").val()+"&uname="+$("#uname").val(),
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			$("#users").remove();
			var showHtml = "<table class='B-table' width='100%' id='users'>";
			var arr = eval('('+response.msg+')');
			$(arr).each(function(i){
				if(i%6==0){
					showHtml += "<tr>";
				}
				showHtml += "<td><label class='checkbox inline'>";
				var checked = "";
				if ($("#user_"+this.USERS_ID).val()!=null) {
					checked = "checked='checked'";
				}
				showHtml += "<input type='checkbox' name='cyz' id='cyz' value='"+this.USERS_ID+"' text='"+this.USER_NAME+"' "+checked+"/>";
				showHtml += this.USER_NAME;
				showHtml += "</label></td>";
				if(i%6==5){
					showHtml += "</tr>";
				}
				
			})
			showHtml += "</table>";
			$("#queryU").after(showHtml);
		}
	});
}

function onlyNum()
{
	
 if(!(event.keyCode==46)&&!(event.keyCode==8)&&!(event.keyCode==37)&&!(event.keyCode==39))
  if((event.keyCode!=46)&&!((event.keyCode>=48&&event.keyCode<=57)||(event.keyCode>=96&&event.keyCode<=105))){
    event.returnValue=false;
  }
}
//显示人员选择
function showUsers(){
	$("#userBlock").slideToggle("slow");
	$("input:checkbox[name='cyz']:checked").each(function(){
		if ($("#user_"+$(this).val()).val()!=null) {
			$(this)[0].checked = true;
		}else{
			$(this)[0].checked = false;
		}
	});
}
var index=1;
function showBzwj(){
$("#bzwj").slideToggle("slow");
index++;
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
	        <TD class="right-border bottom-border"></TD>
			<TD class="right-border bottom-border">
			</TD>
        </TR>
      </table>
      </form>
	</div>
	<div style="height:5px;"></div>
	<div class="B-small-from-table-autoConcise">
      <h4 class="title">
      	审批步骤
      	<span class="pull-right">
      		<button id="btnClose" onClick="window.close()" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
	  		<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">保存</button>
      	</span>
      </h4>
     <form method="post" id="buSpYwxxForm">
      <table class="B-table" width="100%">
      <input type="hidden" id="ID" fieldname="ID" name = "ID"/></TD>
        <input type="hidden" id="SPBZ_UID" fieldname="SPBZ_UID" name = "SPBZ_UID"/></TD>
        <tr>
			<th width="8%" class="right-border bottom-border text-right">步骤名称</th>
       	 	<td class="bottom-border right-border" width="23%">
         		<input class="span12" style="width:85%" id="BZMC" type="text" placeholder="必填" check-type="required" fieldname="BZMC" name = "BZMC"/>
       	 	</td>
       	 	<th width="8%" class="right-border bottom-border text-right">步骤类型</th>
       		<td class="bottom-border right-border"width="15%" colspan="3">
         		<select class="span12" style="width:85%" id="BZLX" check-type="required" kind="dic" src="BZLX" fieldname="BZLX" name = "BZLX">
         	</td>
        </tr>
		<tr>
			<th width="8%" class="right-border bottom-border text-right">处理天数</th>
       		<td class="bottom-border right-border"width="15%">
         		<input class="span12" style="width:85%" id="CLTS" maxlength="4" onkeydown="onlyNum()" type="text" placeholder="必填" check-type="required" fieldname="CLTS" name = "CLTS"/>
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">天数类型</th>
       		<td class="bottom-border right-border"width="15%">
         		<select class="span12" style="width:85%" id="TSLX" check-type="required" fieldname="TSLX" kind="dic" src="TSLX" name = "TSLX">
         	</td>
		</tr>
		<tr>
			
			<th width="8%" class="right-border bottom-border text-right">所属业务</th>
       		<td class="bottom-border right-border"width="15%">
         		<input class="span12" style="width:85%" id="SPYW_UID" type="text" placeholder="必填" check-type="required" fieldname="SPYW_UID" name = "SPYW_UID" readonly="readonly"/>
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">是否有效</th>
       		<td class="bottom-border right-border"width="15%">
         		<input class="span12" style="width:50%" id="ENABLED" type="radio" check-type="required" kind="dic" src="SF" defaultvalue=1 fieldname="ENABLED" name = "SF" />
         	</td>
		</tr>
		
		<tr>
	        <th width="8%" class="right-border bottom-border text-right">参与者</th>
	        <td colspan="3" class="bottom-border right-border">
	        	<span id="addUsers" onclick="showUsers()" class="btn btn-fileUpload" >
	        		<i class="icon-plus"></i> <span>添加流程参与人员...</span> 
				</span>
				<div id="chooseUsers">
					
				</div>
				<div id="userBlock" style="margin-top: 5px">
					<table class="B-table" width="100%" id="queryU">
					<tr>
						<th width="5%" class="right-border bottom-border text-right">人员名称</th>
						<td class="right-border bottom-border" width="40%">
							<input class="span12" id="uname"/>
						</td>
						
						<td class="text-left bottom-border text-right">
	                        <button id="btnQueryUser" onClick="addUsers()" class="btn btn-link"  type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-search"></i>查询</button>
	                        <button onClick="submitUsers()" class="btn btn-link"  type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-plus"></i>添加所选人员</button>
	                        <button onClick="showUsers()" class="btn btn-link"  type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-minus"></i>隐藏</button>
			            </td>	
					</tr>
				</table>
				</div>
	        </td>
        </tr>
        <tr>
	        <th width="8%" class="right-border bottom-border text-right">备注</th>
	        <td colspan="3" class="bottom-border right-border">
	        	<textarea class="span12" rows="2" id="DESCRIPTION" check-type="maxlength" maxlength="4000" fieldname="DESCRIPTION" name="DESCRIPTION"></textarea>
	        </td>
        </tr>
        <!-- 
        <tr>
	        <th width="8%" class="right-border bottom-border text-right">步骤文件</th>
	        <td colspan="3" class="bottom-border right-border">
	        	<span id="addUsers" onclick="showBzwj()" class="btn btn-fileUpload" >
	        		<i class="icon-plus"></i> <span>步骤文件...</span> 
				</span>
	        </td>
        </tr> -->
      </table>
      </form>
       <div id="bzwj" style="display: none;">
       <h4 class="title">
      	步骤文件
      	<span class="pull-right">
      		<button id="bzwjnew" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">新增</button>
	  		<button id="bzwjdel" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">删除</button>
      	</span>
      </h4>
      <form method="post" id="bzwjQuery"  >
				<input class="span12" type="hidden" id="qSPBZ_UID" name = "SPBZ_UID" fieldname = "SPBZ_UID" operation="=" >
      </form>
      <form method="post" id="wjdelQuery"  >
				<input class="span12" type="hidden" id="BZWJ_UID" name = "BZWJ_UID" fieldname = "BZWJ_UID" operation="=" >
      </form>
      <table class="table-hover table-activeTd B-table" id="DT1" width="100%" type="single" pageNum="5">
		<thead>
			<tr>
 			    <th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
 			 <!--   <th fieldname="SPYWMC" colindex="1" tdalign="center" customfunction="doView">&nbsp;&nbsp;</th> --> 
				<th fieldname="BZWJ_UID" colindex=0 tdalign="center" hidden >&nbsp;步骤文件_UID&nbsp;</th>
				<th fieldname="CLK_UID" colindex=1 tdalign="center" hidden >&nbsp;材料UID&nbsp;</th>
				<th fieldname="SPBZ_UID" colindex=2 tdalign="center" hidden>&nbsp;审批步骤UID&nbsp;</th>
				<th fieldname="JC" colindex=3 tdalign="center" >&nbsp;简称&nbsp;</th>
				<th fieldname="WJNAME" colindex=4 tdalign="center" maxlength="30" >&nbsp;文件名称&nbsp;</th>
				<th fieldname="TMPWJNAME" colindex=5 tdalign="center" maxlength="30" >&nbsp;模版文件名&nbsp;</th>
				<th fieldname="ENABLED" colindex=6 tdalign="center" >&nbsp;是否可用&nbsp;</th>
				<th fieldname="OPTURL" colindex=7 tdalign="center" maxlength="30" >&nbsp;操作URL&nbsp;</th>
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
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id = "queryXML">
         <input type="hidden" name="txtXML" id = "txtXML">
         <input type="hidden" name="txtFilter"  order="desc" fieldname = "t.CREATED_DATE" id = "txtFilter">
         <input type="hidden" name="resultXML" id = "resultXML">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
		
 	</FORM>
 </div>
</body>
<script>
</script>
</html>