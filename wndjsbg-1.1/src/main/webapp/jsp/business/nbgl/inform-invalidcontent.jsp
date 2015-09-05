<!DOCTYPE html>
<%@page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext"%>
<%@page import="java.text.*" %>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<%
  String nbId = request.getParameter("nbId");
 %>
<app:base/>
<title>查看通知首页</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script charset="UTF-8" src="/wndjsbg/js/kindeditor/kindeditor.js"></script>
<script charset="UTF-8" src="/wndjsbg/js/kindeditor/lang/zh_CN.js"></script>
<script charset="UTF-8" src="/wndjsbg/js/kindeditor/customKE.js"></script>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/ywlz/css/default.css"> </LINK>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/nbgl/inform01/WorkInformController";
var  NEIBU_TONGZHI_UID ="<%=nbId%>";
  var editor;
  var editor01;
 //页面初始化
 $(function() {
	editor = KindEditor.create('textarea[name="TONGZHI_BIAOTI"]');
	editor01 = KindEditor.create('textarea[name="TONGZHI_NEIRONG"]');
	$("#NEIBU_TONGZHI_UID").val(NEIBU_TONGZHI_UID);
	init();
	//关闭
	$("#btnClose").bind("click", function() {
			var a = $(this).manhuaDialog.close();
		});
		
	//删除 btnDel	
    $("#btnDel").bind("click",function(){
		xConfirm("信息确认","是否要执行删除操作？");
		$('#ConfirmYesButton').attr('click','');
		$('#ConfirmYesButton').one("click",function(){
			$.ajax({
				url :  controllername+"?delete",
				data : {"NEIBU_TONGZHI_UID":NEIBU_TONGZHI_UID},
				type : "post",
				dataType : "json",
				success : function(response){
					if(response.success){
						//获取父页面
						var a=$(window).manhuaDialog.getParentObj();
						//重新加载数据
			   			a.init();
			   			//关闭当前页
						$(window).manhuaDialog.close();	
					}
				}
			});
		});
	});
	
	//保存
	$("#btnAdd").click(function(){
	    if($("#TONGZHIForm").validationButton())
		{
			$("#DESCRIBE").val(editor.html());
	    	$("#DESCRIBE01").val(editor01.html());
		//生成json串
			var data = Form2Json.formToJSON(TONGZHIForm);
			//组成保存json串格式
			var data1 = defaultJson.packSaveJson(data);
			$.ajax({
				url :controllername+"?update",
				data : data1,
				dataType : "json",
				type : 'post',
				success : function(response) {
			   	  alert("保存成功");
				//获取父页面
				  var a=$(window).manhuaDialog.getParentObj();
				  a.init();
				}
				
			});
			}
	});
	
	
});

function init(){
   $.ajax({
		url:controllername+"?queryValidData&NEIBU_TONGZHI_UID="+"<%=nbId%>",
		type:"post",
		dataType : "json",
	    success : function(response){
			var res = dealSpecialCharactor(response.msg);
			var resultmsgobj = convertJson.string2json1(res);
		if(resultmsgobj!=null&&resultmsgobj!=''){
		     var data=resultmsgobj.response.data[0];
		     $("#DESCRIBE").val(editor.html(data.TONGZHI_BIAOTI));
		     $("#DESCRIBE01").val(editor01.html(data.TONGZHI_NEIRONG));
		     $("#SHENGXIAO_DATE").val(data.SHENGXIAO_DATE);
		     $("#SHIXIAO_DATE").val(data.SHIXIAO_DATE);
		}else{
		}
		}
		});				
						
		
}
</script>
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
	<p></p>
	<div class="row-fluid">
		<div class="B-small-from-table-autoConcise">
			<form method="post" id="queryForm">
				<table class="B-table" width="100%">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<TR style="display: none;">
						<TD class="right-border bottom-border"></TD>
						<TD class="right-border bottom-border">
							<INPUT type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<=" />
						</TD>
					</TR>
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr>
						<table style="margin:" width="100%" class=" yw-title" >
		                  <thead>
		                    <tr>
		                        <th fieldname="ORG_NAME" colindex=2 tdalign="left" maxlength="30" >
		                         <h4 align="left">
								       查看通知
							      	<span class="pull-right">
							      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
							      		<button id="btnDel" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">删除</button>
							      		<button id="btnAdd" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">保存</button>	  		
							      	</span>
				                </h4>
		                    </tr>
	                 </table>
					</tr>
				</table>
			</form>
		    <form method="post" id="TONGZHIForm" action="${pageContext.request.contextPath }/nbgl/inform01/WorkInformController?insert"> 
	            <table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single" pageNum="10">
	              <input id="NEIBU_TONGZHI_UID" name="NEIBU_TONGZHI_UID" class="span12" fieldname="NEIBU_TONGZHI_UID" style="display:none;"/>
	                <thead>
	                	<tr>
	                	   <th width="8%" class="right-border bottom-border text-right">标题</th>
	                	   <td colspan="6" class="bottom-border right-border">
                              <textarea id="DESCRIBE" name="TONGZHI_BIAOTI" class="span12" fieldname="TONGZHI_BIAOTI" style="width: 99%;" rows="2"></textarea>
		                   </td>
		                </tr>
		                <tr>
	                	   <th width="8%" class="right-border bottom-border text-right">内容</th>
	                	   <td colspan="6" class="bottom-border right-border">
                               <textarea id="DESCRIBE01" name="TONGZHI_NEIRONG" class="span12" fieldname="TONGZHI_NEIRONG" style="width: 99%;" rows="2"></textarea>
		                   </td>
		                </tr>
	                	<tr>
			       	 	 <th width="15%" class="right-border bottom-border text-right">生效时间</th>
			       	 	 <td colspan="3" class="bottom-border right-border">
			       	 		<div id="dis">
							   <input id="SHENGXIAO_DATE" type="text" fieldtype="date" fieldformat="YYYY-MM-DD HH:mm" fieldname="SHENGXIAO_DATE" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:ss:mm'})" name = "SHENGXIAO_DATE" readonly="readonly"/>
							</div>
						 </td>
						 
						 <th width="15%" class="right-border bottom-border text-right">失效时间</th>
			       	 	<td colspan="3" class="bottom-border right-border">
			       	 		<div id="dis">
							  <input id="SHIXIAO_DATE" type="text" fieldtype="date" fieldformat="YYYY-MM-DD HH:mm" fieldname="SHIXIAO_DATE" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:ss:mm'})" name = "SHIXIAO_DATE" readonly="readonly"/>
							</div>
						</td>
			       	   </tr>
	                </thead>
	              	<tbody></tbody>
	           </table>
	          </form>
	       </div>
	 	</div>
	</div>     
</div>
<div align="center">
	<FORM name="frmPost" method="post" style="display: none" target="_blank">
		<!--系统保留定义区域-->
		<input type="hidden" name="queryXML"/> 
		<input type="hidden" name="txtXML"/>
		<input type="hidden" name="txtFilter" order="asc" fieldname="NEIBU_TONGZHI_UID" id="txtFilter"/>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>