<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>隐患整改</title>
<%
	String gcuid = request.getParameter("gcuid");
	String username = request.getParameter("username");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/yhzg/zgDafuController";

var controllernameContent= "${pageContext.request.contextPath }/yhzg/zgContentController";
//页面初始化
$(function() {
	init();
	$("#btnClose").bind("click", function(){
		var a=$(this).manhuaDialog.close();
	});	

});
//页面默认参数
function init(){
	var parentmain = $(window).manhuaDialog.getParentObj();
	var rowValue = parentmain.$("#resultXML").val();
	var tempJson = convertJson.string2json1(rowValue);
	$("#QZG_TZD_UID").val(tempJson.ZG_TZD_UID);
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?query",data,DT1);

}


//修改
function formatEdit(obj){
	//alert(obj.ZG_XINGZHI_UID);
	return "<a href='javascript:void(0)' onclick='doEdit(this)'  title='修改'><i class='icon-file showXmxxkInfo'></i></a>";
}

function doEdit(obj){
	while(obj.tagName.toLowerCase() != "tr"){
		obj = obj.parentNode;
		if(obj.tagName.toLowerCase() == "table")return null;
	}
	obj.click();
	if($("#DT1").getSelectedRowIndex()==-1){
		requireSelectedOneRow();
	    return
	 }
	$("#resultXML").val($("#DT1").getSelectedRow());
	var data = $("#DT1").getSelectedRow();
	//$("#resultXML").val(data);
	var tempJson = convertJson.string2json1(data);
	var url  = "";
	var type = tempJson.ZG_XINGZHI_UID;

	if(type=="1"){
		url = "${pageContext.request.contextPath }/jsp/gdzxt/yhzg/yhzg-dafu-xszg.jsp";
	}else{
		url = "${pageContext.request.contextPath }/jsp/gdzxt/yhzg/yhzg-dafu-tgzg.jsp";
	}
	$(window).manhuaDialog({"title":"隐患整改>修改整改单","type":"text","content":url,"modal":"2"});

}


//打印
function formatPrint(obj){
	var tzdfuid = obj.ZG_DAFU_UID;
	return "<a href='javascript:void(0)' onclick='doPrint("+tzdfuid+")'  title='打印答复单'><i class='icon-print'></i></a>";
}

function doPrint(tzdfuid){
	window.open("${pageContext.request.contextPath}/yhzg/zgDafuController?printZgdf&tzdfuid="+tzdfuid,"打印答复单");
}


function formatZgPicture(obj){
	var content = "";
	var tzdUid = obj.ZG_TZD_UID;
	$.ajax({
		url : controllernameContent+"?getPicNum&tzdUid="+tzdUid,
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var v = response.msg;
			if(v!=0){
				content = "<p><a href=''>"+v+"</a></p>";
			}else{
				content = "<p>"+v+"</p>";
			}
		}
	});

	return content;	
}
</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">
   
	<div style="height:5px;"></div>
	<div class="B-small-from-table-autoConcise">

     <form method="post" id="dfForm"  >
       <div style="height: 5px;"></div>
      		<div id="shjltxdivid" >
				<div class="modal-header" style="background:#0866c6;">
				<h3 style="color:white;">整改答复情况
				<span class="pull-right">
					<button type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;" id="btnClose">关闭</button>
	  			</span>
				</h3>
				</div>
      			<div class="overFlowX">
		            <table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single"  pageNum="18">
		                <thead>
		                	<tr>
		                		<th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
		                		<th fieldname="ZG_DAFU_UID" colindex=2 tdalign="center" style="width:10px"  CustomFunction="formatEdit" >&nbsp;&nbsp;</th>
								<th fieldname="ZG_DAFU_UID" colindex=1 tdalign="center" style="width:5px" CustomFunction="formatPrint">&nbsp;答复单&nbsp;</th>
								<th fieldname="DAFU_DATE" colindex=2 tdalign="center" style="width:100px"  >&nbsp;答复时间&nbsp;</th>
								<th fieldname="DAFU_CONTENT" colindex=3 tdalign="center" style="width:140px"  >&nbsp;答复内容&nbsp;</th>
								<th fieldname="ZG_DAFU_UID" colindex=4 tdalign="center" style="width:140px" CustomFunction="formatZgPicture" >&nbsp;整改答复图片&nbsp;</th>
								<th fieldname="DAFU_END_DATE" colindex=6 tdalign="center" style="width:10px" >&nbsp;整改完成时间&nbsp;</th>
								<th fieldname="HOUXU_CL" colindex=6 tdalign="center" style="width:10px" >&nbsp;后续处理&nbsp;</th>
		                	</tr>
		                </thead>
		              	<tbody></tbody>
		           </table>
		       </div>
			</div>
      </form>
    </div>
   </div>
  </div>
  
  <form method="post" id="queryForm"  >
    <input type="hidden" id="QZG_TZD_UID" fieldname="t.zg_tzd_uid" name = "ZG_TZD_UID" operation="="/>
  </form>
  <jsp:include page="/jsp/file_upload/fileupload_config.jsp" flush="true"/>
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

</html>