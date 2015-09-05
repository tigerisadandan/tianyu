<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>通知管理</title>
<!--<%-->
<!--	String gcuid = request.getParameter("gcuid");-->
<!--%>-->
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/gongcheng/projectsGongchengController";

//页面初始化
$(function() {
	init();
	$("#btnClose").bind("click", function(){
		var a=$(this).manhuaDialog.close();
	});	
	
	$("#btnSave").bind("click",function(){
		var gcstr = "";
		for(var i = 0;i<$("#DT1").find("input:checkbox[name^='cball']:checked").length;i++){
			gcstr += $("#DT1").find("input:checkbox[name^='cball']:checked").eq(i).val()+",";
		}
		gcstr = gcstr.substring(0,gcstr.length-1);
		var parentmain = $(window).manhuaDialog.getParentObj();
		parentmain.$("#resultXML").val(gcstr);
		
		var a=$(window).manhuaDialog.getParentObj();
	    a.init();
		$(window).manhuaDialog.close();
	});
});
//页面默认参数
function init(){
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?querygc",data,DT1);
	var parentmain = $(window).manhuaDialog.getParentObj();
	var gcstr = parentmain.$("#resultXML").val();
	if(gcstr!=""){
		var gcnum = gcstr.split(",");
		for(var i = 0; i<$("#DT1").find("input:checkbox[name^='cball']").length;i++){
			var val = $("#DT1").find("input:checkbox[name^='cball']").eq(i).val();
			for(var j = 0;j<gcnum.length;j++){
				if(gcnum[j]==val){
					$("#DT1").find("input:checkbox[name^='cball']").eq(i).attr("checked","checked")
				}
			}
		}
	}
}

function formatEdit1(obj){
	var showHtml="<input id=\"cball_"+obj.GONGCHENG_UID+"\" type=\"checkbox\" name = \"cball_"+obj.GONGCHENG_UID+"\" value=\""+obj.GONGCHENG_UID+"\">";
	return showHtml;
}

//全选\反选
function doAllSelect(demo){
	var flag = $(demo).prop("checked");
	$("input:checkbox[name^='cball_']").each(function(i){
		if(i>=0){
			$(this).prop("checked",flag);
		}
	})
}

</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">
   
	<div style="height:5px;"></div>
	<div class="B-small-from-table-autoConcise">
	  <form method="post" id="queryForm"  >
	  	<input type="hidden"  kind="text" id="num" fieldname="rownum" value="10000" operation="<="/>
	  </form>
     <form method="post" id="zgtzdForm"  >
 		<div id="shjltxdivid">
				<div class="modal-header" style="background:#0866c6;">
				<h3 style="color:white;">选择工程
				<span class="pull-right">
					<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
	  				<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">保存</button>
	  			</span>
				</h3>
				</div>
      			<table class="table-hover table-activeTd B-table" width="100%" id="DT1" type="single" noPage=true pageNum="10000">
					<thead>
						<tr>
							<th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
							<th fieldname="GONGCHENG_UID" colindex=1 tdalign="center" CustomFunction="formatEdit1">&nbsp;
								<label class="checkbox inline" >
									<input id="allSelect" onclick="doAllSelect(this)" type="checkbox" name="allSelect">&nbsp;
								</label>
							</th>
		                	<th fieldname="GCMC" colindex=1 tdalign="center">&nbsp;工程名称&nbsp;</th>
		                	<th fieldname="JSDW" colindex=1 tdalign="center" rowMerge="true">&nbsp;建设单位&nbsp;</th>
	                		<th fieldname="XMMC" colindex=4 tdalign="left" maxlength="30" rowMerge="true">&nbsp;项目名称&nbsp;</th>
							<th fieldname="SGDW" colindex=1 tdalign="center" maxlength="30" >&nbsp;施工单位 &nbsp;</th>
							<th fieldname="XMJL" colindex=2 tdalign="center" maxlength="30" >&nbsp;项目经理 &nbsp;</th>
							<th fieldname="XMJLPHONE" colindex=3 tdalign="center" maxlength="30" >&nbsp;联系方式&nbsp;</th>
							<th fieldname="ZJ" colindex=6 tdalign="center" maxlength="30">&nbsp;总监&nbsp;</th>						
							<th fieldname="ZJPHONE" colindex=6 tdalign="center" maxlength="30">&nbsp;联系方式&nbsp;</th>						
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
      </form>
    </div>
   </div>
  </div>

  <jsp:include page="/jsp/file_upload/fileuploadold_config_js.jsp" flush="true"/> 
  <div align="center">
 	<FORM name="frmPost" method="post" style="display: none" target="_blank">
		<!--系统保留定义区域-->
		<input type="hidden" name="queryXML"/> 
		<input type="hidden" name="txtXML"/>
		<input type="hidden" name="txtFilter" order="asc" fieldname="pg.gongcheng_uid" id="txtFilter"/>

<%--		<input type="hidden" name="txtFilter" order="desc" fieldname="ID" id="txtFilter"/>--%>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
 </div>
</body>

</html>