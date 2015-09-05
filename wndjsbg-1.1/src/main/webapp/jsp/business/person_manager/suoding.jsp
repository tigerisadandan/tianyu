<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<title>锁定</title>
<%
String id=(String)request.getAttribute("id");
%>
<app:base/>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/person/SgPersonLibraryController/";

var id ="<%=id%>";

//页面初始化
$(function() {

	init();
	//按钮绑定事件（查询）
	$("#btnYes").click(function() {
		
		var check = $("input:radio[name='JZ_SJFW']:checked").val();
 	  if(feiNull()){
		if(confirm("确定锁定吗？")){
		  $.ajax({
		    url : controllername+"suoding?id="+$("#SG_PERSON_LIBRARY_UID").val()+"&yijian="+$("#JZ_YUANYIN").val()+"&sjfw="+check+"&jz_y="+$("#JZ_Y").val(), 
			data : {},
			cache : false,
			async :	false,
			dataType : "json",  
			type : 'get',
			success : function(response) {
				$("#resultXML").val(response.msg);
			}
	     });
		 window.opener.location.href=window.opener.location.href;
		 window.close();
		}
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
	var jz_y="y";
	 $("#JZ_Y").val(jz_y);
	 $("#SG_PERSON_LIBRARY_UID").val(id);
	$("input:radio[name='JZ_SJFW']")[1].checked = true; 
}
//退回理由不为空 
function feiNull(){

	var yijian=$("#JZ_YUANYIN").val();
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
	             <input type="hidden" id="JZ_Y" fieldname="JZ_Y" name="JZ_Y"/>
	            <input type="hidden" id="SG_PERSON_LIBRARY_UID" fieldname="SG_PERSON_LIBRARY_UID" name="SG_PERSON_LIBRARY_UID"/>
	            	<tr>
	            		<th width="15%" class="right-border bottom-border text-right ">是否是上级发文的限制</th>
	            		<td class="right-border bottom-border">
	            			<input class="span12" id="JZ_SJFW" type="radio" placeholder="" kind="dic" src="SUODING" name = "JZ_SJFW" fieldname="JZ_SJFW"  disabled="disabled">
	            		</td>
	            	</tr>
	               	<tr>
	               		<th width="15%" class="right-border bottom-border text-right ">锁定原因</th>
	                 	<td class="bottom-border right-border">	                 
	                		<textarea name="JZ_YUANYIN"   id="JZ_YUANYIN" fieldname="JZ_YUANYIN" style="height:90%;width:98%;" ></textarea>
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