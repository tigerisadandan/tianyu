<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>监理报备信息</title>
<%
	String id = request.getParameter("id");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/jlbb/jlbbController/";
var controllernameGctype= "${pageContext.request.contextPath }/jlbb/jlgcTypeController/";
var controllernameRy = "${pageContext.request.contextPath }/jlbb/jlbbJlyController/";
var stemp = 0;
var array_tj = ["GUIMO","CENGSHU","GAODU","KUADU","SHENDU","JINE","ZHONGLIANG"];
//页面初始化
$(function() {
	init();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,sgBbList);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"query",data,sgBbList);
	});
	

	//取消
    $("#btnClear_Bins").click(function() {
       window.close();
<%--		$(window).manhuaDialog.close();--%>
    });

    $("#btnPrint").click(function(){
       //	window.open("${pageContext.request.contextPath }/jlbb/sgBbController/query4Print?bbid="+$("#QID").val()+"&type=pdf","打印PDF");
<%--    	$(window).manhuaDialog({"title":"导出","type":"text","content":"${pageContext.request.contextPath }/jsp/business/jlbb/print.jsp?bbid="+$("#ID").val(),"modal":"3"});--%>
//		$.ajax({
//			url : "${pageContext.request.contextPath}/jlbb/jlbbController?print&bbid="+$("#QID").val(), 
//			data : {},
//			cache : false,
//			async :	false,
//			dataType : "json",  
//			type : 'post',
//			success : function(response) {
				//$("#resultXML").val(response.msg);
//				window.location.href = "${pageContext.request.contextPath }/jlbb/jlbbController?download&path="+response;
//		}
//		});

		window.open("${pageContext.request.contextPath}/jlbb/jlbbController?print&bbid="+$("#QID").val(),"打印PDF");
    })

    $("#btnPass").click(function() {
        var flag = confirm("确定中标吗!");
        if(!flag){return;}
        var bbid = $("#JLBB_UID").val();
        var code = $("#COMPANY_DENGLU_CODE").val();
        var name = $("#COMPANY_NAME").val();
    	$.ajax({
    		url : controllername+"updateBbzt?bbid="+bbid+"&status="+20+"&code="+code+"&name="+name,
    		data : {},
    		cache : false,
    		async :	false,
    		dataType : "json",  
    		type : 'post',
    		success : function(response) {
    			alert("报备状态更新为已中标!");
    			window.opener.doQuery();
    			window.close();
    		}
    	});
	});
    $("#btnFilePass").click(function() {
    	var flag = confirm("确定未中标吗!");
     	if(!flag){return;}
    	var bbid = $("#JLBB_UID").val();
    	var code = $("#COMPANY_DENGLU_CODE").val();
        var name = $("#COMPANY_NAME").val();
    	$.ajax({
    		url : controllername+"updateBbzt?bbid="+bbid+"&status="+10+"&code="+code+"&name="+name, 
    		data : {},
    		cache : false,
    		async :	false,
    		dataType : "json",  
    		type : 'post',
    		success : function(response) {
    			alert("报备状态更新为未中标!");
    			window.opener.doQuery();
    			window.close();
    		}
    	});
   	});
    $("#btnUnlock").click(function(){
    	unlock();
	})
});
function unlock(){
	var uid = $("#JLBB_UID").val();
	lockOp(uid,"doBb");
}
function lockOp(uid,optype){
	var title = "";
	if (optype=="doBb") {
		title = "确定完工并解锁所有施工人员吗?";
	}else{
		title = "确定解锁该施工人员吗?";
	}
	 var flag = confirm(title);
	 if(!flag){return;}
	$.ajax({
		url : controllername+"updateBbToUnlock",
		data : {"uid":uid,"optype":optype},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			alert("已解锁施工人员");
			builderRyList();
			window.opener.doQuery();
		}
	});
}
function init(){
	
	$("#QID").val("<%=id%>");
	setFormValues();
	getPtype();
	$(":input").each(function(i){
		   $(this).attr("readonly", "true");
		});
	builderRyList();

}
var status = "";
//修改情况下,读取表单内容
function setFormValues(){
	var data = combineQuery.getQueryCombineData(queryForm,frmPost);
	var data1 = {
		msg : data
	};
	$.ajax({
		url : controllername+"query?type=detail",
		data : data1,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			$("#resultXML").val(response.msg);
			var resultobj = defaultJson.dealResultJson(response.msg);
			$("#jlbbForm").setFormValues(resultobj);
			$("#STA").val(resultobj.STATUS);
			if(resultobj.BID_TYPE=="1"){
				$("#BID_TYPE").val("公开招标");
			}else if(resultobj.BID_TYPE=="2"){
				$("#BID_TYPE").val("邀请招标");
			}else if(resultobj.BID_TYPE=="3"){
				$("#BID_TYPE").val("直接发包");
			}
			
			if(resultobj.STATUS!="2"){
				$("#btnFilePass").remove();
				$("#btnPass").remove();
			}
			if(resultobj.STATUS=="50"||resultobj.STATUS=="20"){
				$("#zbxx").show();
<%--				$("#SHENHE_DATE").val(resultobj.SHENHE_DATE.substring(0,10))--%>
			}else{
				$("#zbxx").hide();
			}
			if(resultobj.STATUS=="20"){
				$("#btnUnlock").show();
<%--				$("#SHENHE_DATE").val(resultobj.SHENHE_DATE.substring(0,10))--%>
			}else{
				$("#btnUnlock").hide();
			}
			status = resultobj.STATUS;
		}
	});
}
function builderRyList(){
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,RyList);
	var flagrs =  defaultJson.doQueryJsonList(controllernameRy+"query",data,RyList,null, true);

	var rows = $("tbody tr" ,$("#RyList"));
	if(rows.size()==0){
		$("tbody" ,$("#RyList")).append("<tr><td colspan=\"10\" style=\"height: 1px;\">&nbsp;</td></tr>");
	}
}
function getPtype(){
	$.ajax({
		url : controllernameGctype+"queryPType?uid="+$("#GC_SUB_TYPE_UID").val(),
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var obj = eval('('+response.msg+')');
			$("#P_NAMES").val(obj[0].NAMES); 
		}
	});
	
}
function personLock(uid){
	var title = "确定锁定该施工人员吗?"; 

	 var flag = confirm(title);
	 if(!flag){return;}
	$.ajax({
		url : controllername+"personLock",
		data : {"uid":uid},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			alert("已锁定该人员");
			builderRyList();
			window.opener.doQuery();
		}
	});
}
function dozt(obj){
	if($("#STA").val()==20||$("#STA").val()==50){
		if(obj.STATUS==1){//解锁
			return "<a href='javascript:void(0)' onclick=lockOp("+obj.JLBB_JLY_UID+",'doBbry')><img src='${pageContext.request.contextPath }/images/sgxt/lock.png' title='锁定中,点击解锁'/></a>";
		}
		else{//锁定
			return "<a href='javascript:void(0)' onclick=personLock("+obj.JLBB_JLY_UID+")><img src='${pageContext.request.contextPath }/images/sgxt/lock-unlock.png' title='锁定人员'/></a>";
		}
	}else if($("#STA").val()==2){
		if(obj.STATUS==1){//解锁
			return "<img src='${pageContext.request.contextPath }/images/sgxt/lock.png' title='锁定中,点击解锁'/></a>";
		}
		else{//锁定
			return "<img src='${pageContext.request.contextPath }/images/sgxt/lock-unlock.png' title='锁定人员'/></a>";
			}
		//return "<img src='${pageContext.request.contextPath }/images/sgxt/lock.png' title='已解锁'/>";
		}
	else{
		if(obj.STATUS==1){//解锁
			return "<img src='${pageContext.request.contextPath }/images/sgxt/lock.png' title='锁定中,点击解锁'/></a>";
		}
		else{//锁定
			return "<img src='${pageContext.request.contextPath }/images/sgxt/lock-unlock.png' title='锁定人员'/></a>";
			}
		//return "<img src='${pageContext.request.contextPath }/images/sgxt/lock-unlock.png' title='已解锁'/>";
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
				        	<input id="QID" class="span12" check-type="maxlength" maxlength="36" name="JLBB_UID" fieldname="TABA.JLBB_UID" type="text" operation="="/>
				        </TD>
						<TD class="right-border bottom-border"></TD>
			        </TR>
				</table>
			</form>
		</div>
		<div style="height:5px;"></div>
		<div class="B-small-from-table-autoConcise">
			<form method="post" id="jlbbForm"  >
	     	 	<h4 class="title" id="xmxx_title">项目信息
	     	 		<span class="pull-right" id="btnSave_span">
			      		<button id="btnClear_Bins" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
			      		<button id="btnUnlock" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">完工解锁</button>
			      		<button id="btnPrint" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">打印登记表</button>
			      		<button id="btnPass" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">中标</button>
			      		<button id="btnFilePass" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">未中标</button>
					</span>
	     	 	</h4>
				<table class="B-table" width="100%" id="xmxx_card">
				<input id="JLBB_UID" class="span12" check-type="maxlength" maxlength="36" name="JLBB_UID" fieldname="JLBB_UID" type="hidden" />
			    <input id="STA" class="span12" check-type="maxlength" maxlength="36" name="STATUS" fieldname="STATUS" type="hidden" />
			    <input id="COMPANY_DENGLU_CODE" class="span12" check-type="maxlength" maxlength="36" name="COMPANY_DENGLU_CODE" fieldname="DENGLU_CODE" type="hidden" />
			        <tr>
						<th width="8%" class="right-border bottom-border text-right">报备编号</th>
			       	 	<td class="bottom-border right-border" width="23%" colspan="3">
			         		<input id="BB_CODE" class="span12" style="width:30%" name="BB_CODE" fieldname="BB_CODE" type="text" />
			       	 	</td>
			       	 </tr>
			        <tr>
						<th width="8%" class="right-border bottom-border text-right">发包方式</th>
			       	 	<td class="bottom-border right-border" width="23%" colspan="3">
			         		<input id="BID_TYPE" class="span12" style="width:30%" name="BID_TYPE_SV" fieldname="BID_TYPE" type="text" />
			       	 	</td>
			       	 </tr>
			       	 <tr>
			         	<th width="8%" class="right-border bottom-border text-right">项目(标段)名称</th>
			       		<td class="bottom-border right-border">
			         		<input class="span12" id="PROJECT_NAME" type="text" placeholder="必填" check-type="required" fieldname="PROJECT_NAME" name = "PROJECT_NAME"  />
			         	</td>
			         	<th width="8%" class="right-border bottom-border text-right">监理单位</th>
			       		<td class="bottom-border right-border">
			         		<input class="span12" id="COMPANY_NAME" type="text" placeholder="必填" check-type="required" fieldname="COMPANY_NAME" name = "COMPANY_NAME"  />
			         	</td>
			        </tr>
			        <tr>
						<th width="8%" class="right-border bottom-border text-right">招标工程项目编号</th>
						<td width="17%" class="right-border bottom-border">
							<input id="PROJECT_CODE" class="span12" placeholder="必填" check-type="required" check-type="maxlength" maxlength="36" name="PROJECT_CODE" fieldname="PROJECT_CODE" type="text" />
						</td>
						<th width="8%" class="right-border bottom-border text-right">开标时间</th>
						<td width="17%" class="right-border bottom-border">
							<%--<input id="KB_DATE" class="span12" maxlength="16" name="KB_DATE" fieldname="KB_DATE" filedtype="date" type="text" />
							--%><input id="KB_DATE" class="span12" maxlength="10" name="KB_DATE" fieldname="KB_DATE" fieldtype="date" type="text" placeholder="必填" check-type="required" check-type="maxlength"/>
							
						</td>
					</tr>
					<!-- 
					<tr>
						<th width="8%" class="right-border bottom-border text-right">承包类型</th>
						<td width="17%" class="right-border bottom-border" colspan="3">
							<input id="CB_XINGZHI" class="span12" style="width:40%" name="CB_XINGZHI" fieldname="CB_XINGZHI" type="text" />
						</td>
					</tr>
					 -->
					<tr>
				        <th width="8%" class="right-border bottom-border text-right">工程类型</th>
				        <td colspan="3" class="bottom-border right-border" colspan="3">
				        	<input id="GC_SUB_TYPE_UID" class="span12" placeholder="必填" check-type="required" check-type="maxlength" maxlength="36" name="GC_SUB_TYPE_UID" fieldname="GC_SUB_TYPE_UID" type="hidden" />
				     		<input id="P_NAMES" style="width:40%" class="span12" placeholder="必填" check-type="required" check-type="maxlength" maxlength="36" name="P_NAMES" fieldname="P_NAMES" type="text" />
							<input id="GC_SUB_TYPE_NAMES" style="width:30%" class="span12" placeholder="必填" check-type="required" check-type="maxlength" maxlength="36" name="GC_SUB_TYPE_UID" fieldname="GCLX" type="text" />
				        </td>
			        </tr>
			        
			        <tr id="GUIMO_TR">
			        	<th width="8%" class="right-border bottom-border text-right">建设面积</th>
			       		<td class="bottom-border right-border" width="15%" >
			         		<input class="span12" style="width:40%" id="GUIMO" type="number" fieldname="GUIMO" name = "GUIMO"  /><b>(平方米)</b>
			         	</td>
			         	<th width="8%" class="right-border bottom-border text-right">层数</th>
			       		<td class="bottom-border right-border" >
			         		<input class="span12" style="width:40%" id="CENGSHU" type="number" fieldname="CENGSHU" name = "CENGSHU"  /><b>(层)</b>
			         	</td>
			        </tr>
			        <tr id="GAODU_TR">
			        	<th width="8%" class="right-border bottom-border text-right">高度</th>
			       		<td class="bottom-border right-border" >
			         		<input class="span12" style="width:40%" id="GAODU" type="number" fieldname="GAODU" name = "GAODU"  /><b>(米)</b>
			         	</td>
			         	<th width="8%" class="right-border bottom-border text-right">跨度</th>
			       		<td class="bottom-border right-border" >
			         		<input class="span12" style="width:40%" id="KUADU" type="number" fieldname="KUADU" name = "KUADU"  /><b>(米)</b>
			         	</td>
			        </tr>
			        <tr id="SHENDU_TR">
			        	<th width="8%" class="right-border bottom-border text-right">深度</th>
			       		<td class="bottom-border right-border" >
			         		<input class="span12" style="width:40%" id="SHENDU" type="number" fieldname="SHENDU" name = "SHENDU"  /><b>(米)</b>
			         	</td>
			         	<th width="8%" class="right-border bottom-border text-right">单项合同额</th>
			       		<td class="bottom-border right-border" >
			         		<input class="span12" style="width:40%" id="JINE" type="number" fieldname="JINE" name = "JINE"  /><b>(万元)</b>
			         	</td>
			        </tr>
			        <tr id="ZHONGLIANG_TR">
			        	<th width="8%" class="right-border bottom-border text-right">重量</th>
			       		<td class="bottom-border right-border" width="15%" >
			         		<input class="span12" style="width:40%" id="ZHONGLIANG" type="number" fieldname="ZHONGLIANG" name = "ZHONGLIANG"  /><b>(吨)</b>
			         	</td>
			         	<th width="8%" class="right-border bottom-border text-right">最低配备人员数</th>
			       		<td class="bottom-border right-border" width="15%" >
			         		<span id="sgry_num">
			         			
			         			<input class="span12" style="width:40%" id="JLRY_NUMS" type="number" fieldname="JLRY_NUMS" name = "JLRY_NUMS"  /><b>(人)</b>
			         		</span>
			         	</td>
			         	
			        </tr>
			        <tr id="zbxx" style="display: none;">
			         	<th width="8%" class="right-border bottom-border text-right">中标状态</th>
			       		<td class="bottom-border right-border">
			         		<input class="span12" id="ZB_STATUS" type="text" name = "STATUS" value="已中标" />
			         	</td>
			         	<th width="8%" class="right-border bottom-border text-right">完成日期</th>
			       		<td class="bottom-border right-border">
			         		<input class="span12" id="SHENHE_DATE" type="text" fieldname="SHENHE_DATE" name = "SHENHE_DATE"  />
			         	</td>
			        </tr>
			        <tr>
				        <th width="8%" class="right-border bottom-border text-right">备注</th>
				        <td colspan="3" class="bottom-border right-border" colspan="3">
				        	<textarea class="span12" rows="2" id="DESCRIBE" check-type="maxlength" maxlength="4000" fieldname="DESCRIBE" name="DESCRIBE"></textarea>
				        </td>
			        </tr>
				</table>
	      
				<h4 class="title" id="ryxx_title">项目部人员
				</h4>
				<div class="overFlowX">
				<table width="100%" class="table-hover table-activeTd B-table" id="RyList" type="single" pageNum="1000" >
				<thead>
					<tr>
						<th  name="XH" id="_XH" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
						<th  fieldname="STATUS" tdalign="center" colindex=2 CustomFunction="dozt">&nbsp;状态&nbsp;</th>
						<th  fieldname="GWNAME" colindex=3 tdalign="center" >&nbsp;岗位&nbsp;</th>
						<th  fieldname="JL_NAME" colindex=4 tdalign="center" >&nbsp;姓名&nbsp;</th>
						<th  fieldname="ZHENGSHU_NAME" colindex=5 tdalign="center" >&nbsp;证书名称&nbsp;</th>
						<th  fieldname="ZHENGSHU_CODE" tdalign="center" colindex=2 >&nbsp;证书编号&nbsp;</th>
						<th  fieldname="ZHUANYE" tdalign="center" colindex=2 >&nbsp;注册专业&nbsp;</th>
						<th  fieldname="AGE" tdalign="center" colindex=2 >&nbsp;年龄&nbsp;</th>
						<th  fieldname="ZHICHENG_NAME" tdalign="center" colindex=2 >&nbsp;职称&nbsp;</th>
						<th  fieldname="SHENFENZHENG" tdalign="center" colindex=2 >&nbsp;身份证&nbsp;</th>
					</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
				</div>
			</form>
		</div>
	</div>
</div>
<div align="center">
	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
        <input type="hidden" name="queryXML" id = "queryXML">
    	<input type="hidden" name="txtXML" id = "txtXML">
        <!--  <input type="hidden" name="txtFilter"  order="desc" fieldname = "taba.TIJIAO_DATE" id = "txtFilter"> -->
        <input type="hidden" name="resultXML" id = "resultXML">
        <input type="hidden" name="currRy" id = "currRy">
        <!--传递行数据用的隐藏域-->
        <input type="hidden" name="rowData">
	</FORM>
</div>
</body>
<script>
</script>
</html>