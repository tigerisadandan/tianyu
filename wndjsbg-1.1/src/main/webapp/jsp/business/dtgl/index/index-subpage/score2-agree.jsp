<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>同意</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<%
	String JFSQ_UID = request.getParameter("JFSQ_UID");
%>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/yhzg/scoreController";
var JFSQ_UID = <%=JFSQ_UID%>;
//页面初始化
$(function() {
	init();	
	
	$("#btnClose").bind("click", function(){
		parent.document.getElementById("btnClear").click();
	   	//关闭当前页
		$(window).manhuaDialog.close();
	});
	
	//按钮绑定事件（查询）
	$("#shenhe").click(function() {				
		var JFSTATUS =parent.document.getElementById("JFSTATUS").value;
		var agree = $('input[name="agree"]:checked').val();
		var shyj = $("#shyj").val();
		
		//更新加分表
		$.ajax({
			url : controllername+"?shenhetonggou&JFSTATUS="+JFSTATUS+"&JFSQ_UID="+JFSQ_UID+"&agree="+agree+"&shyj="+shyj,
			cache : false,
			async :	false,
			dataType : "json",  
			type : 'post',
			success : function(response) {
				if(response.msg){
					alert("操作成功");
				}
			}
		});		
		
		if(agree=="1"&&JFSTATUS=="50"){
			/*操作更新SCROE表的AJAX
			$.ajax({
				url : controllername+"?shenhetonggou&JFSTATUS="+JFSTATUS+"&JFSQ_UID="+JFSQ_UID+"&agree="+agree,
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(response) {
					
										
				}
			});
			
			*/	
		}		
		
	});	
	
});

//页面默认参数
function init(){
	queryjfxx();
	queryspxx();
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?queryjfdx&JFSQ_UID="+JFSQ_UID,null,jfdx);
	
	defaultJson.doQueryJsonList(controllername+"?queryjfsj&JFSQ_UID="+JFSQ_UID,null,JFSJ);	
}

function queryjfxx(){
	$.ajax({
		url : controllername+"?queryjfxx",
		data : {"JFSQ_UID":JFSQ_UID},
		type : "post",
		dataType : "json",
		success : function(response){
			var resultObj = defaultJson.dealResultJson(response.msg);			
			$("#jfxx").setFormValues(resultObj);			
		}
	});
}

function queryspxx(){
	$.ajax({
		url : controllername+"?queryspxx",
		data : {"JFSQ_UID":JFSQ_UID},
		type : "post",
		dataType : "json",
		success : function(response){
			var resultObj = defaultJson.dealResultJson(response.msg);			
			$("#spxx").setFormValues(resultObj);			
		}
	});
}

function rowSh(JFSQ_UID){
	$(window).manhuaDialog({"title":"加分情况查询>同意","type":"text","content":"${pageContext.request.contextPath }/jsp/business/dtgl/index/index-subpage/score2-agree.jsp?JFSQ_UID="+JFSQ_UID,"modal":"2"});
}


function caozuoFun(obj){
	
	var showHtml="";
	showHtml +="<span class='btn btn-link' id='addSpan' onclick='rowSh("+obj.JFSQ_UID+");' title='申请加分审核' >";
	showHtml +="<i class='icon-file'></i> ";
	showHtml +="</span>";
	return showHtml;
}
</script>
</head>
<body>
<app:dialogs/>
<div id="menuiframe"></div>
<div class="container-fluid">
<div class="row-fluid">  
	<div style="height:5px;"></div>
	<div class="B-small-from-table-autoConcise">
	<div class="modal-header" style="background:#0866c6;">
	<h3 style="color:white;">加分申请审核
		<span class="pull-right">
			<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
			<button id="shenhe" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">审核</button>			
		</span>				 
	</h3>
	</div>

	<table class="B-table" width="100%" id="DT1" type="single"  noPage=true>
		<tr><th width="8%"></th><td class="bottom-border right-border" width="92%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					 <input type="radio" value="1" name="agree"  checked="checked" />同意&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					 <input type="radio" value="-1" name="agree"/>不同意
		</td></tr>
		<tr><th class="right-border bottom-border text-right">审核意见</th>
			<td class="bottom-border right-border"><textarea class="span12" rows="4"  id="shyj"></textarea></td>
		</tr>
	</table>
	
	
	<div class="modal-header" style="background:#0866c6;margin-top: 20px;">
	<h3 style="color:white;">加分申请信息
		<span class="pull-right">
			<button id="kaoqingnv" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">考勤率</button>
			<button id="print" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">打印</button>			
		</span>				 
	</h3>
	</div>
	<form method="post" id="jfxx">	
	<table class="B-table" width="100%" id="DT2" type="single"  noPage=true>
		<tr><th class="right-border bottom-border text-right" width="8%">申请日期</th>
			<td>
			<div id="dis">
				<input id="SQ_DATE" type="text" fieldtype="date" fieldformat="YYYY-MM-DD HH:mm:ss" fieldname="SQ_DATE" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:ss:mm'})" readonly="readonly"/>
			</div>
			</td>
		</tr>	
		<tr><th class="right-border bottom-border text-right">申请单位</th>
			<td>
				<input class="span12"  id="SQ_DANWEI" type="text"  fieldname="SQ_DANWEI" name = "SQ_DANWEI" readonly="readonly"/>	         	        	
			</td>
		</tr>	
		<tr><th class="right-border bottom-border text-right">备注</th>
			<td>
				<textarea class="span12" rows="4"  id="DESCRIPTION" fieldname="DESCRIPTION" readonly="readonly"></textarea>
			</td>
		</tr>	
		<tr><th class="right-border bottom-border text-right">加分对象</th>
			<td>
				<div class="overFlowX">
	            <table width="100%" class="table-hover table-activeTd B-table" id="jfdx" type="single" nopage="true">
	                <thead>
	                	<tr>
	                		<th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
	                		<th fieldname="DUIXIANG_ROLE" colindex=0 tdalign="center">&nbsp;加分对象&nbsp;</th>
							<th fieldname="DUIXIANG_NAME" colindex=2 tdalign="center" maxlength="50">&nbsp;名称/姓名&nbsp;</th>							
							<th fieldname="SCORE" colindex=2 tdalign="center">&nbsp;加分值&nbsp;</th>							
	                	</tr>
	                </thead>
	              	<tbody></tbody>
	           </table>
	       	</div>
			</td>
		</tr>	
	</table>
	</form>

	<div class="modal-header" style="background:#0866c6;margin-top: 20px;">
	<h3 style="color:white;">加分事件					 
	</h3>
	</div>
	
	<div class="overFlowX">	
  <table width="100%" class="table-hover table-activeTd B-table" id="JFSJ" type="single" nopage="true">
		<thead>
           <tr>
           <th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
           <th fieldname="JF_CONTENT_UID" colindex=0 tdalign="center"  CustomFunction="caozuoFun" >&nbsp;&nbsp;</th>
           <th fieldname="WEIGUI_CONTENT" colindex=0 tdalign="center">&nbsp;加分事件&nbsp;</th>
		   <th fieldname="DESCRIPTION" colindex=2 tdalign="center" maxlength="100">&nbsp;详细描述&nbsp;</th>					
           </tr>
       </thead>
       <tbody></tbody>		
	</table>
    </div>
    
    <div class="modal-header" style="background:#0866c6;margin-top: 20px;">
	<h3 style="color:white;">审批信息						 
	</h3>
	</div>	
	<form method="post" id="spxx">
	<table class="B-table" width="100%" id="DT4" type="single"  noPage=true>
		<tr><th width="15%" class="right-border bottom-border text-right">监理单位审核意见</th>
			<td width="85%" class="bottom-border right-border" colspan="3"><textarea class="span12" rows="4"  id="JL_SH_YIJIAN" fieldname="JL_SH_YIJIAN" readonly="readonly"></textarea></td>
		</tr>
		<tr><th class="right-border bottom-border text-right">审核日期</th>
	       	 	<td class="bottom-border right-border" colspan="3">
	       	 		<div id="dis">
					  <input id="JL_SH_DATE" type="text" fieldtype="date" fieldformat="YYYY-MM-DD HH:mm:ss" fieldname="JL_SH_DATE" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:ss:mm'})" name = "JL_SH_DATE" readonly="readonly"   />
					</div>
		</td></tr>
		<tr><th width="15%" class="right-border bottom-border text-right">建设单位审核意见</th>
			<td width="85%" class="bottom-border right-border" colspan="3"><textarea class="span12" rows="4"  id="JS_SH_YIJIAN" fieldname="JS_SH_YIJIAN" readonly="readonly"></textarea></td>
		</tr>
		<tr><th class="right-border bottom-border text-right">审核日期</th>
	       	 	<td class="bottom-border right-border" colspan="3">
	       	 		<div id="dis">
					  <input id="JS_SH_DATE" type="text" fieldtype="date" fieldformat="YYYY-MM-DD HH:mm:ss" fieldname="JS_SH_DATE" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:ss:mm'})" name = "JS_SH_DATE" readonly="readonly"   />
					</div>
		</td></tr>
		<tr><th width="15%" class="right-border bottom-border text-right">监督小组确认意见</th>
			<td width="85%" class="bottom-border right-border" colspan="3"><textarea class="span12" rows="4"  id="XZ_SH_YIJIAN" fieldname="XZ_SH_YIJIAN" readonly="readonly"></textarea></td>
		</tr>
		<tr><th class="right-border bottom-border text-right">确认人员</th>
	       	 <td class="bottom-border right-border">
	       	 	<input class="span12"  id="XZ_SH_REN" type="text"  fieldname="XZ_SH_REN" name = "XZ_SH_REN" readonly="readonly"/>	
			</td>
			<th class="right-border bottom-border text-right">审核日期</th>
	       	<td class="bottom-border right-border">
	       	 		<div id="dis">
					  <input id="XZ_SH_DATE" type="text" fieldtype="date" fieldformat="YYYY-MM-DD HH:mm:ss" fieldname="XZ_SH_DATE" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:ss:mm'})" name = "XZ_SH_DATE" readonly="readonly"   />
					</div>
			</td>	
		</tr>
		<tr><th width="15%" class="right-border bottom-border text-right">二级审批意见</th>
			<td width="85%" class="bottom-border right-border" colspan="3"><textarea class="span12" rows="4"  id="ERJI_SH_YIJIAN" fieldname="ERJI_SH_YIJIAN" readonly="readonly"></textarea></td>
		</tr>
		<tr><th class="right-border bottom-border text-right">审核人</th>
	       	 <td class="bottom-border right-border">
	       	 <input class="span12"  id="ERJI_SH_REN" type="text"  fieldname="ERJI_SH_REN" name = "ERJI_SH_REN" readonly="readonly"/>	
			 </td>
			 <th class="right-border bottom-border text-right">审核日期</th>
	       	 <td class="bottom-border right-border">
	       	 	<div id="dis">
					 <input id="ERJI_SH_DATE" type="text" fieldtype="date" fieldformat="YYYY-MM-DD HH:mm:ss" fieldname="ERJI_SH_DATE" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:ss:mm'})" name = "ERJI_SH_DATE" readonly="readonly" />
				</div>
			 </td>
		</tr>		
	</table>
    </form>
	</div>	  
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
