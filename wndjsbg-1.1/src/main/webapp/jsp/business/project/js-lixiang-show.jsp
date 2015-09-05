<!DOCTYPE html>
<%@page import="com.ccthanking.framework.Constants"%>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>项目-维护</title>
<%
	String lxpw=Constants.FS_FILEUPLOAD_FJLB_LX_LXPW;
	String kypf=Constants.FS_FILEUPLOAD_FJLB_LX_KYPF;
	String xmgs=Constants.FS_FILEUPLOAD_FJLB_LX_XMGS;
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">

//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/project/jsProjectController";
//页面初始化
$(function() {
	init();
	$("#btnClose").bind("click", function(){
		var a=$(this).manhuaDialog.close();
	});
	
});
//页面默认参数
function init(){

	
		//生成json串
		var parentmain = $(window).manhuaDialog.getParentObj();
		var rowValue = parentmain.$("#resultXML").val();
		var tempJson = convertJson.string2json1(rowValue);
		$("#QLIXIANG_UID").val(tempJson.LIXIANG_UID);
		//查询表单赋值
		var data = combineQuery.getQueryCombineData(queryForm, frmPost);
		var data1 = {
			msg : data
		};
		$.ajax({
			url : controllername + "?queryLXdetail", 
			data : data1,
			cache : false,
			async : false,
			dataType : "json",
			type : 'post',
			success : function(response) {
				var res = dealSpecialCharactor(response.msg);
				$("#resultXML").val(response.msg);
				var resultobj = defaultJson.dealResultJson(res);
				$("#lixiangForm").setFormValues(resultobj);		
				$("#PF_DATE").val(resultobj.PF_DATE);		
				return true;
			}
		});
		queryFileData(tempJson.LIXIANG_UID, "", "", "470010");
	
}


</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">
    <div class="B-small-from-table-autoConcise">
      <form method="post" id="queryForm"  >
     	<input type="hidden" id="QLIXIANG_UID" fieldname="LIXIANG_UID" name="LIXIANG_UID" operation="=" />
      </form>
      
	</div>
	<div style="height:5px;"></div>
	<div class="B-small-from-table-autoConcise">
      <h4 class="title">建设项目信息
      	<span class="pull-right">
      		<button id="btnClose" class="btn"type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
      	</span>
      </h4>
     <form method="post" id="lixiangForm"  >
	      <table class="B-table" width="100%" >
	      		<input type="hidden" id="LIXIANG_UID" fieldname="LIXIANG_UID" name ="LIXIANG_UID"/>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">立项批文号</th>
				<td width="35%" class="right-border bottom-border" colspan="3">
					<input class="span12" style="width:94%" id="LIXIANG_CODE" type="text"  fieldname="LIXIANG_CODE" name = "LIXIANG_CODE" readonly />
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">立项名称</th>
				<td width="35%" class="right-border bottom-border" colspan="3">
					<input class="span12" style="width:94%" id="LIXIANG_NAME" type="text" fieldname="LIXIANG_NAME" name = "LIXIANG_NAME" readonly />
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">项目总投资（万元）</th>
				<td width="35%" class="right-border bottom-border">
					<input class="span12 text-right" style="width:85%" id="TOUZI" type="text" fieldname="TOUZI" name = "TOUZI"  readonly/>
				</td>
				<th width="15%" class="right-border bottom-border text-right">资金来源</th>
				<td width="35%" class="right-border bottom-border">
					<input class="span12 text-right" style="width:85%" id="ZJLY"  type="text" fieldname="ZJLY" name = "ZJLY"  readonly/>
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">占地面积</th>
				<td width="35%" class="right-border bottom-border">
					<input class="span12 text-right" style="width:55%" id="ZDMJ"  type="text" fieldname="ZDMJ" name = "ZDMJ"  readonly/>
					<input class="span12 text-right" style="width:30%" id="ZDMJ_UNIT"  type="text" fieldname="ZDMJ_UNIT" name = "ZDMJ_UNIT"  readonly/>
				</td>
				<th width="15%" class="right-border bottom-border text-right">建筑面积（万平方米）</th>
				<td width="35%" class="right-border bottom-border">
					<input class="span12 text-right" style="width:85%" id="JZMJ" type="text" fieldname="JZMJ" name = "JZMJ" readonly />
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">立项批复部门</th>
				<td width="35%" class="right-border bottom-border">
					<input class="span12" style="width:85%" id="PFBM" type="text"  fieldname="PFBM" name = "PFBM" readonly />
				</td>
				<th width="15%" class="right-border bottom-border text-right">批复日期</th>
				<td width="35%" class="right-border bottom-border">
					<input style="width:85%" id="PF_DATE" type="text" fieldname="PF_DATE" name = "PF_DATE" readonly/>
				</td>
			</tr>
			</table>
			<hr/>
			<table class="B-table" width="100%" >
			<tr>
				<th width="15%" class="right-border bottom-border text-right">立项附件</th>
				<td colspan="3" class="bottom-border right-border">
					<div>
						<table role="presentation" class="table table-striped">
							<tbody fjlb="<%=lxpw %>" id="lxfj" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true" ></tbody>
						</table>							
					</div>
				</td>
			</tr>
			<!-- 
			<tr>
				<th width="15%" class="right-border bottom-border text-right">可研批复</th>
				<td colspan="3" class="bottom-border right-border">
					<div>
						<table role="presentation" class="table table-striped">
							<tbody fjlb="%=kypf %>" id="kypf" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true" ></tbody>
						</table>							
					</div>
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">项目概算</th>
				<td colspan="3" class="bottom-border right-border">
					<div>
						<table role="presentation" class="table table-striped">
							<tbody fjlb="%=xmgs %>" id="xmgs" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true" ></tbody>
						</table>							
					</div>
				</td>
			</tr> -->
	      </table>
      </form>
    </div>
   </div>
    <jsp:include page="/jsp/file_upload/fileuploadold_config_js.jsp" flush="true"/> 
  </div>
  <div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id = "queryXML">
         <input type="hidden" name="txtXML" id = "txtXML">
         <input type="hidden" name="txtFilter" order="desc" fieldname="CREATED_DATE" id="txtFilter"/>
         <input type="hidden" name="resultXML" id = "resultXML">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
		
 	</FORM>
 </div>
</body>
<script>
</script>
</html>