<!DOCTYPE html>
<%@page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext"%>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<%@page import="com.ccthanking.framework.common.User" %>
<app:base/>
<title>告知会-修改</title>
<%
	String type = request.getParameter("type");
	String gzhId = request.getParameter("gzhId");
	session.setAttribute("gzh_uid", gzhId);
	User user = RestContext.getCurrentUser();
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/gzhgl/gzhController.do";
var controllername1 = "${pageContext.request.contextPath }/gzhgl/gzhRyController.do";
var type ="<%=type%>";
var userName = "<%=user.getUsername() %>";
var gzhId = "<%=gzhId %>";
var pIds = "";
//页面初始化
$(function() {
	//加载附件
	queryFileData('GZH','<%=gzhId %>');
	
	//记录告知会编号
	$("#GZH_UID").val(gzhId);
	$("#GZH_UID1").val(gzhId);
	init1();
	//记录告知会项目单位
	if(pIds!=""){
		pIds = pIds.substring(0,pIds.length-1);
		$("#pIds").val("(-1,"+pIds+")");
		$("#pIds1").val("(-1,"+pIds+")");
	}
	//按钮绑定事件(添加与会项目单位)
	$("#btnAdd").click(function() {
		$(window).manhuaDialog({"title":"选择项目","type":"text","content":"${pageContext.request.contextPath }/jsp/business/gzhgl/xmdw-list.jsp","modal":"2"});
	});
	//按钮绑定事件（删除）
	$("#btnDel").click(function(){
		xConfirm("信息确认","确定要删除吗？");
		$('#ConfirmYesButton').attr('click','');
		$('#ConfirmYesButton').one("click",function(){
			//生成json串
		   var data = Form2Json.formToJSON(queryForm2);
		   //组成保存json串格式
		   var data1 = defaultJson.packSaveJson(data);
		   $.ajax({
		   		url : controllername + "?delete",
		   		data : data1,
		   		type : "post",
		   		dataType : "json",
		   		success : function(response){
		   			xAlert("告知会信息删除成功！");
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
	//按钮绑定事件（保存）
	$("#btnSave").click(function(){
				if($("#gzhForm").validationButton()){
						//生成json串
						var data = Form2Json.formToJSON(gzhForm);
						//组成保存json串格式
						var data1 = defaultJson.packSaveJson(data);
						$.ajax({
							url : controllername + "?update",
							data : data1,
							dataType : "json",
							type : "post",
							async : true,
							success : function(response) {
								var pIds1 = $("#pIds1").val();
								var personIds = $("#personIds").val();
								var type1 = $("#type1").val();
								//得到插入类型
								$.ajax({
									url : controllername1 + "?insertByQuery",
									data : {pIds1 : pIds1,personIds:personIds,type:type1},
									dataType : "json",
									type : "post",
									success : function(response){
										//获取父页面
										var a=$(window).manhuaDialog.getParentObj();
										//重新加载数据
										a.init();
										//关闭当前页
										$(window).manhuaDialog.close();
									}
								});
							}
						});
				}
	});
	//按钮绑定事件（清空）
    $("#btnClose").click(function() {
        $(window).manhuaDialog.close();
    });
    <%
    if(type.equals("detail")){
	%>
	$("input[type='text']").attr("disabled",true);
	//显示详细所要显示的窗口
	$(".btnDetail").each(function(i){
	   $(this).show();
	 });
	<%
		}else if(type.equals("edit")){
	%>
			$("input[type='text']").attr("disabled",false);
			$(".btnEdit").each(function(i){
	  		 $(this).show();
		 });
		<%
		}
	%>
	
});
//页面默认参数
function init1(){
	//回显
	var data = combineQuery.getQueryCombineData(queryForm2, frmPost);
	var data1 = {
		msg : data
	};
	$.ajax({
		url : controllername + "?query",
			data : data1,
			cache : false,
			async : false,
			dataType : "json",
			type : 'post',
			success : function(response) {
				$("#resultXML").val(response.msg);
				var resultobj = defaultJson.dealResultJson(response.msg);
				$("#gzhForm").setFormValues(resultobj);
			}
	});
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm2,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername1+"?queryXmDwByGzhId",data,DT1);
	//---------------------------------------------------------------------
	//查询签到人员
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm2,frmPost,DT2);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername1+"?queryQianDaoRYByGzhId",data,DT2);
}
function init(){
	$("#GZH_UID1").val("");//把告知会编号制空
	$("#personIds").attr("fieldname","sr.SG_PERSON_UID");//查询
	if($("#pIds").val()!='-1' && $("#pIds").val()!=""){
		var pIds  = $("#pIds").val();
		pIds = pIds.replace("(","");
		pIds = pIds.replace(")","");
		pIds = "("+pIds+")";
		$("#pIds").val(pIds);
		$("#pIds1").val(pIds);
	}
	//查询与会项目单位
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?queryUndoneProject",data,DT1);
	//---------------------------------------------------------------------
	//查询签到人员
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm1,frmPost,DT2);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?querySgPersonByGC",data,DT2);
}
//弹出区域回调
getWinData = function(data){
	$("#XMMC").val(JSON.parse(data).XMMC);
	$("#XMBH").val(JSON.parse(data).XMBH);
	$("#GC_TCJH_XMXDK_ID").val(JSON.parse(data).GC_TCJH_XMXDK_ID);
};
//删除
function doRandering(obj) {
		var showHtml = "";
		showHtml = "<a href='javascript:rowView("+obj.SG_PERSON_UID+")'><i class='icon-trash'></i></a>";
		return showHtml;
}
//删除细节
function rowView(obj){
		xConfirm("信息确认","确认要删除吗？");
		$('#ConfirmYesButton').attr('click','');
		$('#ConfirmYesButton').one("click",function(){
			var personIds = $("#personIds").val();
			if(personIds==""){
				$("#personIds").val(obj);
			}else{
				personIds = personIds.replace("(","");
				personIds = personIds.replace(")","");
				personIds = "("+personIds+","+obj+")";
				$("#personIds").val(personIds);
			}
			
				//查询签到人员
				//生成json串
				var data = combineQuery.getQueryCombineData(queryForm1,frmPost,DT2);
				if ($("#personIds").attr("fieldname")=="ry.PERSON_UID") {
					//调用ajax插入
					defaultJson.doQueryJsonList(controllername1+"?queryQianDaoRYByGzhId",data,DT2);
				}else if($("#personIds").attr("fieldname")=="sr.SG_PERSON_UID"){
					//调用ajax插入
					defaultJson.doQueryJsonList(controllername+"?querySgPersonByGC",data,DT2);
				}
				
		});
}
//隐藏的多选框，用于记录告知会的项目单位
function doRandering1(obj){
	pIds += obj.PROJECT_UID+","
}

 //上传使用方法
    function selectFile (obj){
	   var targetUid = $('#target_uid').val();
	   var file_type = $(obj).attr('file_type');
		setFileData("GZH","GZH_UID",targetUid,file_type);
		document.getElementById("fileupload_btn").click();
	}
/*
function CurentTime()
    { 
        var now = new Date();
       
        var year = now.getFullYear();       //年
        var month = now.getMonth() + 1;     //月
        var day = now.getDate();            //日
       
        var hh = now.getHours();            //时
        var mm = now.getMinutes();          //分
        var ss = now.getSeconds();			//秒
       
        var clock = year + "/";
       
        if(month < 10)
            clock += "0";
       
        clock += month + "/";
       
        if(day < 10)
            clock += "0";
           
        clock += day +" ";
        if(hh <10){
        	clock +="0";
        }
        clock += hh+":";
        if(mm <10){
        	clock +="0";
        }
        clock += mm;
        
        
        return clock; 
    }
    */
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
	        	<input class="span12" id="pIds" type="text"  operation="in" name="PROJECTS_UID" fieldname="p.PROJECTS_UID" value="-1" fieldtype="int" >
	        </TD>
        </TR>
         <tr>
        <h4 class="title">告知会内容
      	<span class="pull-right">
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
	  		<button id="btnAdd" class="btn btnEdit" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;display:none;">添加与会项目单位</button>
	  		<button id="btnDel" class="btn btnEdit" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;display:none;">删除</button>
	  		<button id="btnSave" class="btn btnEdit" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;display:none;">保存</button>
      	</span>
      	</h4>
		</tr>
      </table>
      </form>
    <input type="hidden" id="type1" value="gzh"  />
	<form method="post" id="queryForm1"  >
		 <table class="B-table" width="100%">
      <!--可以再此处加入hidden域作为过滤条件 -->
      	<TR  style="display:none;">
			<TD class="right-border bottom-border">
	        	<input class="span12" id="pIds1" type="text"  operation="in" name="PROJECTS_UID" fieldname="p.PROJECTS_UID" value="-1" fieldtype="int" >
	        </TD>
	        <% if(gzhId != null && !"".equals(gzhId)){
			%>
				<TD class="right-border bottom-border">
					<input class="span12" id="personIds" type="text"  operation="not in" name="PERSON_UID" fieldname="ry.PERSON_UID" fieldtype="int" >
				</TD>
			<%
			}else{
			%>
				<TD class="right-border bottom-border">
				<input class="span12" id="personIds" type="text"  operation="not in" name="SG_PERSON_UID" fieldname="sr.SG_PERSON_UID" fieldtype="int" >
				</TD>
			<%
			} %>
			<% if(gzhId != null && !"".equals(gzhId)){
			%>
				<TD class="right-border bottom-border">
				<input class="span12" id="GZH_UID1" type="text"  operation="=" name="GZH_UID" fieldname="t.GZH_UID"  >
			</TD>
			<%
			} %>
        </TR>
      </table>
	</form>
	<!-- 回显Form -->
	<form method="post" id="queryForm2"  >
		 <table class="B-table" width="100%">
      <!--可以再此处加入hidden域作为过滤条件 -->
      	<TR  style="display:none;">
			<TD class="right-border bottom-border">
	        	<input class="span12" id="GZH_UID" type="text"  operation="in" name="GZH_UID" fieldname="t.GZH_UID"  fieldtype="int" >
	        </TD>
        </TR>
      </table>
	</form>
	
	<!--   <div style="height:3px;"></div> -->
	<div class="B-small-from-table-autoConcise">
     <form method="post" id="gzhForm">
      <table class="B-table" width="100%" >
        <tr>
			<th width="8%" class="right-border bottom-border text-right">会议主题</th>
       	 	<td class="bottom-border right-border" width="23%">
       	 	<!-- 隐藏域，用于修改 -->
       	 		<input class="span12" style="width:85%" id="GZH_UID" type="hidden" fieldname="GZH_UID" name = "GZH_UID" />
         		<input class="span12" style="width:85%" id="CREATE_DATE" type="hidden" fieldname="CREATE_DATE" name = "CREATE_DATE" />
         		<input class="span12" style="width:85%" id="CREATE_BY" type="hidden" fieldname="CREATE_BY" name = "CREATE_BY" />	
         		<input class="span12" style="width:85%" id="HUIYI_TITLE" type="text" placeholder="必填" check-type="required" fieldname="HUIYI_TITLE" name = "HUIYI_TITLE" />
       	 		<!-- 文件上传需用 -->
       	 		<input type="hidden" name="target_uid" id="target_uid" fieldname="target_uid" >
       	 	</td>
         	<th width="8%" class="right-border bottom-border text-right">会议开始时间</th>
       		<td class="bottom-border right-border"width="15%">
         		<input  id="HUIYI_DATE" type="text" fieldtype="date" fieldformat="yyyy/MM/dd HH:mm" fieldname="HUIYI_DATE" name = "HUIYI_DATE" class='Wdate'  operation="="  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" />
         	</td>
        </tr>
        <tr>
			<th width="8%" class="right-border bottom-border text-right">会议地点</th>
			<td width="17%" class="right-border bottom-border" colspan="3" >
				<input id="HUIYI_DIDIAN" class="span12" placeholder="必填" check-type="required"  name="HUIYI_DIDIAN" fieldname="HUIYI_DIDIAN" type="text"  />
			</td>
		</tr>
		<tr>
			<th width="8%" class="right-border bottom-border text-right">会议主持人</th>
			<td width="17%" class="right-border bottom-border">
				<input id="ZHUCHI_REN" class="span12" placeholder="必填" check-type="required"  name="ZHUCHI_REN" fieldname="ZHUCHI_REN" type="text" />
			</td>
		</tr>
      </table>
      </form>
    </div>
    <div style="height:5px;"></div>
    <h4 class="title" style="font-size:16px;color:black;" >与会项目单位</h4>
    <div class="overFlowX">
	            <table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single"  pageNum="18">
	                <thead>
	                	<tr>
	                		<th rowspan="2" fieldname="PROJECT_UID" width="1"
									tdalign="center" colindex=0
									noprint="true" hidden></th>
							<th fieldname="PROJECTS_NAME" colindex=1 tdalign="center" maxlength="30" CustomFunction="doRandering1">&nbsp;项目名称&nbsp;</th>
							<th fieldname="COMPANY_NAME" colindex=2 tdalign="center" maxlength="30" >&nbsp;建设单位&nbsp;</th>
	                	</tr>
	                </thead>
	              	<tbody></tbody>
	           </table>
	       </div>
	       
	    <div style="height:5px;"></div>
    <h4 class="title" style="font-size:16px;color:black;" >签到人员</h4>
    <div class="overFlowX">
	            <table width="100%" class="table-hover table-activeTd B-table" id="DT2" type="single"  pageNum="18">
	                <thead>
	                	<tr>
							<th name="XH" id="_XH" style="width:10px" colindex=0 tdalign="center">&nbsp;序号&nbsp;</th>
							<th fieldname="PROJECTS_NAME" colindex=1 tdalign="center">&nbsp;项目名称&nbsp;</th>
							<th fieldname="PERSON_NAME" colindex=2 tdalign="center">&nbsp;姓名&nbsp;</th>
							<th fieldname="NAMES" colindex=3 tdalign="center">&nbsp;人员类型&nbsp;</th>
							<%
	  							if(type.equals("edit")){
							  %>
							<th fieldname="SG_PERSON_UID" colindex=4 tdalign="center"
								maxlength="30" CustomFunction="doRandering" title="删除" >&nbsp;删除&nbsp;</th>
	                		  <%
	  								}
	 							 %> 
	                	</tr>
	                </thead>
	              	<tbody></tbody>
	           </table>
	       </div>
	  <%
	  	if(type.equals("edit")){
	  %>
	  		 <div style="height:5px;"></div>
		    <h4 class="title" style="font-size:16px;color:black;" >上传文件
		    <span class="pull-right">
				    	 <div>
				<span class="btn btn-fileUpload" id="addFileSpan" onclick="selectFile(this);" file_type="3012">
							<i class="icon-plus"></i>
							<span>上传...</span>
				</span>
				</div>
		    </span>
		    <table role="presentation" class="table table-striped">
					<tbody class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" file_type="3012"></tbody>
				</table>
		    </h4>  
	  <%
	  	}
	  %> 
	       
	       
   </div>
  </div>
  <div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id="queryXML"> 
         <input type="hidden" name="txtXML" id = "txtXML" >
         <%-- <input type="hidden" name="txtFilter"  order="desc" fieldname = "t.LRSJ" id = "txtFilter">--%>
        <%-- <input type="hidden" name="txtFilter"  order="desc" fieldname ="p.PROJECTS_NAME" id = "txtFilter">--%>
         <input type="hidden" name="resultXML" id = "resultXML"  >
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">	
 	</FORM>
 </div>
 <jsp:include page="/jsp/file_upload/fileupload_util.jsp" />
</body>
<script>
</script>
</html>