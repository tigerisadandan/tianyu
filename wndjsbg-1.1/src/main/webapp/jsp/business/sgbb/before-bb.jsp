<%@page import="com.ccthanking.framework.common.User"%>
<%@page import="com.ccthanking.framework.Globals"%><!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<title>施工报备</title>
<app:base/>

<script type="text/javascript" charset="utf-8">
$(function() {
	init();
	$("#btnReg").click(function(){
		$(window).manhuaDialog.getParentObj().hasTy = 1;
		$(window).manhuaDialog.close();
<%--		$(window).manhuaDialog({"title":"施工报备>新增","type":"text","content":"${pageContext.request.contextPath }/jsp/business/sgbb/sgbb-add.jsp","modal":"1"});--%>
	})
})
function init(){
	$("#btnReg").attr("disabled",true);
}
function changeChoose(){
	if($("input:checkbox[name='TONGYI']:checked").size()!=0&&$("input:checkbox[name='TONGYI']:checked")[0].value=="0"){
		$("#btnReg").removeAttr("disabled");
	}else{
		$("#btnReg").attr("disabled",true);
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
      <h4 class="title">关键岗位人员配备表填报须知
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
			         		<textarea name="p_t01" rows="25" cols="80" wrap="virtual" style="width:1200px;" id="P31511_CONTENT" style="width:650px;line-height:200%;" readonly="true">　　1、投标单位必须严格按照《无锡新区建设工程施工项目部和现场监理部关键岗位人员配备标准及管理暂行办法》（锡新管办发〔2013〕1号）配备施工项目部和现场监理部关键岗位人员；
　　2、投标单位要根据本单位实际情况科学配备各关键岗位人员，如若中标，则所有填报的关键岗位人员必须严格按照《无锡新区在建工程施工项目部和现场监理部关键岗位人员人脸识别考勤管理暂行实施细则》（锡新管办发〔2013〕6号）要求进行人脸识别考勤管理；
　　3、根据《无锡新区建设工程施工项目部和现场监理部关键岗位人员配备标准及管理暂行办法》（锡新管办发〔2013〕1号）第四条第二款：施工项目部、现场监理部关键岗位人员人脸识别考勤正常进行后，工程项目才能正式开工。如果投标时填报的关键岗位人员不能到岗参加人脸识别考勤，将可能导致相关建设手续无法办理，或工程项目不能正式开工，其后果由投标单位自行承担；
　　4、根据《无锡新区建设工程施工项目部和现场监理部关键岗位人员配备标准及管理暂行办法》（锡新管办发〔2013〕1号）第九条第三款：除不可抗力因素外，中标单位自投标截止之日起至完成合同约定工程量之日止，施工项目部和项目监理部关键岗位人员应保持稳定，不得擅自更换和撤离关键岗位人员；
　　5、根据《无锡新区建设工程安全生产动态管理办法》（锡新管发〔2013〕19号）规定：（1）施工、监理单位，同一年度在新区同一项目或不同项目累计出现3次以上（含3次）以上变更关键岗位中的重要人员的，扣30分；（2）施工、监理单位，变更关键岗位重要人员，每人次扣10分；（3）施工、监理变更关键岗位主要人员，每人次扣5分。</textarea>
			       	 	</td>
			        </tr>
			        <tr>
			       	 	<td class="bottom-border " style="width:40%" align="right">
			       	 		<label class="checkbox inline"><input onchange="changeChoose()" type="checkbox" name="TONGYI" value="0" ><b style="color: red;">我单位已认真阅读《关键岗位人员配备表填报须知》</b></label>
			       	 	</td>
			       	 	<td class="bottom-border right-border" style="width:60%">
			       	 		<button id="btnReg" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">下一步</button>
			       	 	</td>
					</tr>
				</table>
      </form>
    </div>
   </div>
  </div>
  </div>
</body>
<script>
</script>
</html>