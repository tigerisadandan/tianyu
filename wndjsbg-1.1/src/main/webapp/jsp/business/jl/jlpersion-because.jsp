<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>步骤处理时的退回理由首页</title>
<%
	
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/jlPersonLibraryController/";
//页面初始化
$(function() {

	init();
	//按钮绑定事件（查询）
	$("#btnYes").click(function() {
		if(feiNull()){
		var fuyemian=$(window).manhuaDialog.getParentObj();
		var liyou=$("#SHENHE_YIJIAN").val();
		fuyemian.delSelectID(liyou);
		//$(window).manhuaDialog.getParentObj().$("#YIJIAN").val(liyou);
		$(window).manhuaDialog.close();
		}
	});
	
	//按钮绑定事件(修改)
	$("#btnUpdate").click(function() {
		if($("#DT1").getSelectedRowIndex()==-1)
		 {
			requireSelectedOneRow();
		    return
		 }
		$("#resultXML").val($("#DT1").getSelectedRow());
		$(window).manhuaDialog({"title":"步骤处理时的退回理由>修改","type":"text","content":"${pageContext.request.contextPath }/jsp/business/spxx/bu-sp-bz-thly-add.jsp?type=update","modal":"2"});
	});

	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#queryForm").clearFormResult();
       
    });
	
});

//页面默认参数
function init(){
	
	
}
//退回理由不为空 
function feiNull(){

	var yijian=$("#SHENHE_YIJIAN").val();
	if(yijian==""||yijian=="0"){
		alert("请填写理由"); 
		return  false;   
	}else{
		return true; 
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
			<h4 class="title">
				退回理由
				<span class="pull-right">  
					<button id="btnYes" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">确定</button>
      			
      			</span>
			</h4>
			<form method="post" id="queryForm">
				<table class="B-table" width="100%">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<TR style="display: none;">
						<TD class="right-border bottom-border"></TD>
						<TD class="right-border bottom-border">
							<INPUT type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>
							<INPUT type="text" class="span12"  id="SPBZ_UID_ID" fieldname="SPBZ_UID"  operation="="/>
						</TD>
					</TR>
				</table>
			</form>
			<form method="post" id="queryLiYou">
	            <table class="B-table" width="100%" id="DT1">
	               <tr>
	                 <td class="bottom-border right-border">
	                	<textarea name="SHENHE_YIJIAN"   id="SHENHE_YIJIAN" fieldname="SHENHE_YIJIAN" style="height:90%;width:98%;" ></textarea>
	                 </td>
	               </tr>
	           </table>
	        </form>
	 	</div>
	</div>     
</div>
<div align="center">
	<FORM name="frmPost" method="post" style="display: none" target="_blank">
		<!--系统保留定义区域-->
		<input type="hidden" name="queryXML"/> 
		<input type="hidden" name="txtXML"/>
		<input type="hidden" name="txtFilter" order="desc" fieldname="SPBZ_UID" id="txtFilter"/>
		<input type="hidden" name="txtFilter" order="asc" fieldname="SERIAL_NO" id="txtFilter"/>
<%--		<input type="hidden" name="txtFilter" order="desc" fieldname="ID" id="txtFilter"/>--%>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>