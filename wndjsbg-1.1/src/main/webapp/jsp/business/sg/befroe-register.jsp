<%@page import="com.ccthanking.framework.common.User"%>
<%@page import="com.ccthanking.framework.Globals"%><!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<title>施工单位-注册</title>
<app:base/>

<script type="text/javascript" charset="utf-8">
$(function() {
	init();
	$("#btnReg").click(function(){
		location.href = "/wndjssg/sgentreg.do";
	})
})
function init(){
	$("#btnReg").attr("disabled",true);
	$("input:radio[name='TONGYI']")[1].checked = true;
}
function changeChoose(){
	if($("input:radio[name='TONGYI']:checked")[0].value=="1"){
		$("#btnReg").attr("disabled",true);
	}else{
		$("#btnReg").removeAttr("disabled");
	}
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
	        <TD class="right-border bottom-border">
	        	<INPUT type="text" class="span12" kind="text" id="QID" name="ID"  fieldname="n.SG_COMPANY_UID" value="" operation="="/>
	        	<INPUT type="text" class="span12" kind="text" id="QSTATUS" name="STATUS"  fieldname="n.STATUS" value="40" operation="="/>
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
      <h4 class="title">声&nbsp;&nbsp;明 
      </h4>
	<form method="post" id="sgEnterPriseLibraryForm">
		<div class="container-fluid">
			<p class="text-right tabsRightButtonP">
				<span class="pull-right">
					
				</span>
			</p>
			
			<table class="B-table" width="100%" >
			      	<input type="hidden" id="ID" fieldname="SG_ENTERPRISE_LIBRARY_UID" name = "ID"/>
				  	<input type="hidden" id="STATUS" fieldname="STATUS" name = "STATUS"/>
				  	<input type="hidden" id="optype" fieldname="optype" name = "optype"/>
				  	<input type="hidden" id="SG_COMPANY_UID" fieldname="SG_COMPANY_UID" name = "SG_COMPANY_UID"/>
					<input type="hidden" id="SG_ENTERPRISE_LIBRARY_FILEUPLOAD" fieldname="SG_ENTERPRISE_LIBRARY_FILEUPLOAD" name = "SG_ENTERPRISE_LIBRARY_FILEUPLOAD"/>
				  	
			        <tr>
			       	 	<td class="bottom-border right-border" colspan="2">
			         		<textarea name="p_t01" rows="35" cols="150" wrap="virtual" id="P13_SHENGMING" readonly="true" style="width:1200px;">一、 用户的权利和义务 

     1、 用户有权利拥有自己在本网站的用户名和密码，并有权利使用自己的用户名和密码随时登陆本网站。用户不得以任何形式擅自转让或授权他人使用自己在本网站的用户名。

     2、 用户有权利拥有自己在本网站的用户和密码，在本网站早报查询建设手续审批情况及有关政务公开内容、举报投诉渠道等信息。 

     3、 用户有义务在注册时提供自己的真实资料，并保证诸如电子邮件地址、联系电话、联系地址、邮政编码等内容的有效性和安全性，保证主管部门和相关部门通过上述联系方式与用户取得联系。同时，用户也有义务在相关资料实际变更时及时更新有关注册资料。用户保证不以他人资料在本网站进行注册。

     4、 用户应当保证在使用本网站过程中遵守诚实信用的原则，不从事与本网站无关的行为。用户保证不利用技术手段及其他手段破坏及扰知己本网站的正常运行。

二、 本网站的权利和义务 

     1、 本网站有义务保证用户资料的安全。 

     2、 当用户在登陆注册时遇到问题或反映情况，本网站有义务提供回复或帮助。 

     3、 本网站有权对用户的注册资料进行查阅，发现问题有权向用户发出询问，要求用户提供必要的补充信息，或对此作出相应的处理。

     4、 当发现有足够理由相信存在恶意虚假成份或存在试图扰乱系统运行的行为时，本网站有权在不通知用户的前提下进行删除或采取其他措施。 
</textarea>
			       	 	</td>
			        </tr>
			        <tr>
			       	 	<td class="bottom-border " style="width:29%" align="right">
			       	 		<label class="radio inline"><input onchange="changeChoose()" type="radio" name="TONGYI" value="0" sv="同意">同意</label>
			       	 		<label class="radio inline"><input onchange="changeChoose()" type="radio" name="TONGYI" value="1" sv="不同意">不同意</label>
			       	 	</td>
			       	 	<td class="bottom-border right-border" style="width:70%">
			       	 		<button id="btnReg" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">下一步</button>
			       	 	</td>
					</tr>
				</table>
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
<%--         <input type="hidden" name="txtFilter"  order="desc" fieldname = "n.SERIAL_NO" id = "txtFilter">--%>
         <input type="hidden" name="resultXML" id = "resultXML">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
         
		 <input type="hidden" name="ywid2" id = "ywid2" value="1234">
		 <input type="hidden" name="ywid" id = "ywid" value="123">
 	</FORM>
 </div>
</body>
<script>
</script>
</html>