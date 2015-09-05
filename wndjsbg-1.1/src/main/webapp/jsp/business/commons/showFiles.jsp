<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<title>查看附件</title>
<%
	String target_uid=(String)request.getAttribute("targetId");
	String business_type=(String)request.getAttribute("business_type");
%>
<app:base/>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping

var target_uid ="<%=target_uid%>";
var business_type = "<%=business_type%>";
//页面初始化
$(function() {
	init();
	
});
//页面默认参数
function init(){
	setFormValues();
}
function setFormValues(){
	var obj = new Object();
	obj.TARGET_UID = target_uid+",";
	obj.BUSINESS_SUB_TYPE = business_type;
	var data = JSON.stringify(obj);
		var data1 = defaultJson.packSaveJson(data);
		$.ajax({
		url : "${pageContext.request.contextPath }/fileUploadController.do?queryFileList",
		data : data1,
		cache : false,
		async :	false,
		dataType : "json",
		success : function(result) {
			var showHtml = "";
			var fileList = eval('('+result.msg+')');
			$.each(fileList,function(index,file){
				showHtml += "<tr>";
				showHtml +=	"<td>"+(index+1)+"</td>";
				showHtml +=	"<td>"+file.FILE_NAME+"</td>";
				showHtml +=	"<td align='center'>"+file.FILE_TYPE_SV+"</td>";
				showHtml +=	"<td align='right'>"+file.DOC_SIZE+"</td>";
				showHtml +=	"<td align='center'><a href='javascript:void(0)' onclick='link("+file.AT_FILEUPLOAD_UID+")'>查看</a>|<a href='javascript:void(0)' onclick='load(\""+file.url+"\")'>下载</a></td>";
				showHtml += "</tr>";
				
			})
			$("#fileList").append(showHtml);
		}
	});
}
function link(n){
		$("#previewFileid").val(n);
		window.open(encodeURI("${pageContext.request.contextPath }/jsp/file_upload/showPreview.jsp"));
}
function load(url){
	window.open(url,"文件下载");
}
</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">
<input type='hidden' id='previewFileid' >
    <div class="B-small-from-table-autoConcise">
     <form method="post" id="queryForm"  >
      <table class="B-table" width="100%">
      <!--可以再此处加入hidden域作为过滤条件 -->
      	<TR  style="display:none;">
	        <TD class="right-border bottom-border">
	        	<INPUT type="text" class="span12" kind="text" id="QID" name="ID"  fieldname="n.SG_COMPANY_UID" value="" operation="="/>
	        	<INPUT type="text" class="span12" kind="text" id="QSTATUS" name="STATUS"  fieldname="n.STATUS" value="1" operation="="/>
	        </TD>
			<TD class="right-border bottom-border">
			</TD>
        </TR>
      </table>
      </form>
      
    <div style="height:5px;"></div>
	</div>
	<div style="height:5px;"></div>
	<div class="B-small-from-table-autoConcise">
      <h4 class="title">附件列表
      </h4>
	<form method="post" id="sgEnterPriseLibraryForm">
		<div class="container-fluid">
			<div class="overFlowX">
				<table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single"  pageNum="5">
					<thead>
						<tr>
					   		<th  name="XH" id="_XH" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
					    	<th colindex=2 tdalign="right" >&nbsp;文件名称&nbsp;</th>
					       	<th colindex=3 tdalign="right" >&nbsp;文件类型&nbsp;</th>
							<th colindex=4 tdalign="right" >&nbsp;文件大小(KB)&nbsp;</th>
							<th colindex=5 tdalign="center" >&nbsp;操作&nbsp;</th>
						</tr>        		
					</thead>
					<tbody id="fileList"></tbody>
				</table>
			</div>			
		</div>
      </form>
    </div>
   </div>
  </div>
  <jsp:include page="/jsp/file_upload/fileupload_config.jsp" flush="true"/>
  </div>
  <div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id = "queryXML">
         <input type="hidden" name="txtXML" id = "txtXML">
         <input type="hidden" name="txtFilter"  order="desc" fieldname = "SERIAL_NO" id = "txtFilter">
         <input type="hidden" name="resultXML" id = "resultXML">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
 	</FORM>
 </div>
</body>
<script>
</script>
</html>