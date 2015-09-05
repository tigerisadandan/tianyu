<!DOCTYPE html>
<%@page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext"%>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<%@page import="com.ccthanking.framework.common.User" %>
<app:base/>
<title>告知会-添加</title>
<%
	String type=request.getParameter("type");
	User user = RestContext.getCurrentUser();
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/gzhgl/gzhController.do";
var controllername1 = "${pageContext.request.contextPath }/gzhgl/gzhRyController.do";
var type ="<%=type%>";
var userName = "<%=user.getUsername() %>";
//页面初始化
$(function() {
	init();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {
		//生成json串
		//var data = combineQuery.getQueryCombineData(queryForm,frmPost,gzhList);
		//调用ajax插入
		//defaultJson.doQueryJsonList(controllername+"?query",data,gzhList);
	});
	//按钮绑定事件(添加与会项目单位)
	$("#btnAdd").click(function() {
		$(window).manhuaDialog({"title":"选择项目","type":"text","content":"${pageContext.request.contextPath }/jsp/business/gzhgl/xmdw-list.jsp","modal":"2"});
	});
	//按钮绑定事件（保存）
	$("#btnSave").click(function(){
							if($("#gzhForm").validationButton()){
								//生成json串
							var data = Form2Json.formToJSON(gzhForm);
							//组成保存json串格式
							var data1 = defaultJson.packSaveJson(data);
							$.ajax({
								url : controllername + "?insert",
								data : data1,
								dataType : "json",
								type : "post",
								async : true,
								success : function(response) {
									var pIds1 = $("#pIds1").val();
									var personIds = $("#personIds").val();
									var type1 = $("#type1").val();
									$.ajax({
										url : controllername1 + "?insertByQuery",
										data : {pIds1 : pIds1,personIds:personIds,type:type1},
										dataType : "json",
										type : "post",
										success : function(response){
											//xAlert("添加成功！");
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
	//置所有input 为disabled
	$(":input").each(function(i){
	   $(this).attr("disabled", "true");
	 });
	<%
		}
	%>
	
});
//页面默认参数
function init(){
	//会议时间默认选中当前时间
	$("#HUIYI_DATE").val(CurentTime());
	//会议主持人默认为当前登陆用户
	$("#ZHUCHI_REN").val(userName);
	if($("#pIds").val()!='-1' && $("#pIds").val()!=''){
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

	var data1 = combineQuery.getQueryCombineData(queryForm1,frmPost,DT2);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?querySgPersonByGC",data1,DT2);
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
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?querySgPersonByGC",data,DT2);
	});	
}
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
    
    //上传使用方法
    function selectFile (obj){
	   var targetUid = $('#target_uid').val();
	   var file_type = $(obj).attr('file_type');
		setFileData("GZH","GZH_UID",targetUid,file_type);
		document.getElementById("fileupload_btn").click();	
	}
	
</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">
    <div class="B-small-from-table-autoConcise">
    <input type="hidden" id="type1" value="all"  />
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
	  		<button id="btnAdd" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">添加与会项目单位</button>
	  		<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">保存</button>
      	</span>
      	</h4>
		</tr>
      </table>
      </form>
      
	<form method="post" id="queryForm1"  >
		 <table class="B-table" width="100%">
      <!--可以再此处加入hidden域作为过滤条件 -->
      	<TR  style="display:none;">
			<TD class="right-border bottom-border">
	        	<input class="span12" id="pIds1" type="text"  operation="in" name="PROJECTS_UID" fieldname="p.PROJECTS_UID" value="-1" fieldtype="int" >
	        </TD>
			<TD class="right-border bottom-border">
				<input class="span12" id="personIds" type="text" operation="not in" name="SG_PERSON_UID" fieldname="sr.SG_PERSON_UID" fieldtype="int" >
			</TD>
        </TR>
      </table>
	</form>
	
	<!--   <div style="height:3px;"></div> -->
	<div class="B-small-from-table-autoConcise">
     <form method="post" id="gzhForm"  >
      <table class="B-table" width="100%" >
        <tr>
			<th width="8%" class="right-border bottom-border text-right">会议主题</th>
       	 	<td class="bottom-border right-border" width="23%">
         		<input class="span12" style="width:85%" id="HUIYI_TITLE" type="text" placeholder="必填" check-type="required" fieldname="HUIYI_TITLE" name = "HUIYI_TITLE" value="方案告知会" />
       	 		<!-- 文件上传需用 -->
       	 		<input type="hidden" name="target_uid" id="target_uid" fieldname="target_uid" >
       	 	</td>
         	<th width="8%" class="right-border bottom-border text-right">会议开始时间</th>
       		<td class="bottom-border right-border"width="15%">
         		<input  id="HUIYI_DATE" type="text" fieldtype="date"  check-type="required" fieldformat="yyyy/MM/dd HH:mm" fieldname="HUIYI_DATE" name = "HUIYI_DATE" class='Wdate'  operation="="  onClick="WdatePicker({dateFmt:'yyyy/MM/dd HH:mm'})" />
         	</td>
        </tr>
        <tr>
			<th width="8%" class="right-border bottom-border text-right">会议地点</th>
			<td width="17%" class="right-border bottom-border" colspan="3" >
				<input id="HUIYI_DIDIAN" class="span12" placeholder="必填" check-type="required"  name="HUIYI_DIDIAN" fieldname="HUIYI_DIDIAN" type="text" value="建设会场"  />
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
							<th fieldname="PROJECTS_NAME" colindex=0 tdalign="center" maxlength="30" >&nbsp;项目名称&nbsp;</th>
							<th fieldname="COMPANY_NAME" colindex=1 tdalign="center" maxlength="30" >&nbsp;建设单位&nbsp;</th>
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
							<th fieldname="SG_PERSON_UID" colindex=4 tdalign="center"
								maxlength="30" CustomFunction="doRandering" title="删除">&nbsp;删除&nbsp;
							</th>
	                	</tr>
	                </thead>
	              	<tbody></tbody>
	           </table>
	       </div>    
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
   </div>
  </div>
  <div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id="queryXML"> 
         <input type="hidden" name="txtXML" id = "txtXML" >
         <%-- <input type="hidden" name="txtFilter"  order="desc" fieldname = "t.LRSJ" id = "txtFilter">--%>
         <input type="hidden" name="txtFilter"  order="desc" fieldname ="p.PROJECTS_NAME" id = "txtFilter">
         <input type="hidden" name="resultXML" id = "resultXML"  >
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">		
 	</FORM>
 </div>
  <jsp:include page="/jsp/file_upload/fileupload_util.jsp" flush="true"/>
</body>
<script>
</script>
</html>