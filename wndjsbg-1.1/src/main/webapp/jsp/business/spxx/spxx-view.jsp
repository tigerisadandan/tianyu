<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>业务信息</title>
<%
	String uid = (String)request.getAttribute("uid");
String zlc = (String)request.getAttribute("zlc");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/kindeditor/kindeditor.js"></script>
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/kindeditor/lang/zh_CN.js"></script>
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/kindeditor/customKE.js"></script>
<script type="text/javascript" charset="utf-8">
var id = "<%=uid %>";
var zlc = "<%=zlc%>";
var isFlag = true;
if(zlc=="0"){
	isFlag = false;
}
var controllername= "${pageContext.request.contextPath }/spxx/buSpYwxxController/";
var controllernameBz= "${pageContext.request.contextPath }/spxx/buSpBzController/";
var controllernameCl = "${pageContext.request.contextPath }/spxx/buSpYwclController/";
var editor;

//初始化加载
$(document).ready(function(){
	editor = KindEditor.create('textarea[name="DESCRIBE"]');
	init();
	//按钮绑定事件(保存)
	$("#btnUpdate").click(function() {
		if($("#buSpYwxxForm").validationButton())
		{
			$("#DESCRIBE").val(editor.html());
		    //生成json串
		    var data = Form2Json.formToJSON(buSpYwxxForm);
		  //组成保存json串格式
		    var data1 = defaultJson.packSaveJson(data);
		  //调用ajax插入
    			var flag = defaultJson.doInsertJson(controllername + "update", data1);
    			if(flag){
					window.location = "${pageContext.request.contextPath }/ywxxView/"+$("input:radio[name='SFZLC']:checked").val()+"/"+$("#SPYW_UID").val();
        		}

		}else{
			requireFormMsg();
		  	return;
		}
	});
	//按钮绑定事件(删除)
	$("#btnDel").click(function() {

		if($("#DT2").getSelectedRowIndex()==-1) {
			//xAlert("提示信息",'请选择一条记录');
	 		requireSelectedOneRow();
	 		return
		} 

		var rowValue = $("#DT2").getSelectedRow();
		var index = $("#DT2").getSelectedRowIndex();
		var tempJson=eval("("+rowValue+")");//string转json
		var uid=tempJson.SPBZ_UID;
		$("#SPBZ_UID").val(uid);
	
		var data = Form2Json.formToJSON(deleteFormForBz);
		  //组成保存json串格式
		var data1 = defaultJson.packSaveJson(data);
		var success=defaultJson.doInsertJson(controllernameBz + "delete", data1);
		if(success == true) {
			 $("#DT2").removeResult(index);
		}
		//$(window).manhuaDialog({"title":"业务页面模版表>修改","type":"text","content":"${pageContext.request.contextPath }/jsp/business/person/person_add.jsp?type=update","modal":"1"});
	});
	
	$("#btnAddBz").click(function() {
		window.open("${pageContext.request.contextPath }/jsp/business/spxx/spxx-bz-add.jsp");
	})
	$("#btnAddCl").click(function() {
		window.open("${pageContext.request.contextPath }/jsp/business/spxx/spxx-cl-add.jsp");
	})
	$("#btnAddChild").click(function(){
		window.open("${pageContext.request.contextPath}/ywxxAdd/1");
	})
});
function init(){

	$("#QID").val(id);
	$("#QPID").val(id);
	$("#QYWID").val(id);
	$("#QCL_YWID").val(id);
	setFormValues();
	initIsChild();
}
function initIsChild(){
	g_bAlertWhenNoResult = false;
	doChild();
	doBz();
	doCl();
	g_bAlertWhenNoResult = true;
	$("#currYwid").val($("#SPYW_UID").val());
	$("#currYwmc").val($("#SPYWMC").val());
	if(!isFlag){
		
		$("#bz_d").hide();
		$("#bz_title").hide();
		$("#bz_tab").hide();

		$("#cl_d").hide();
		$("#cl_title").hide();
		$("#cl_tab").hide();
	}
	
}
function setFormValues(){
	var data = combineQuery.getQueryCombineData(queryForm,frmPost);
	var data1 = {
		msg : data
	};
	$.ajax({
		url : controllername+"query",
		data : data1,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var resultobj = defaultJson.dealResultJson(response.msg);
			$("#buSpYwxxForm").setFormValues(resultobj);
			$("#currYwid").val($("#SPYW_UID").val());
			$("#currYwmc").val($("#SPYWMC").val());
			$("#P_SPYW_UID").val(resultobj.P_YWMC);
			$("#P_SPYW_UID").attr("code",resultobj.P_SPYW_UID);
			var bz = resultobj.DESCRIBE.toString().replace(new RegExp('([\b])', 'g')," ").replace(new RegExp('([\n])', 'g')," ").replace(new RegExp('([\t])', 'g')," ");
			//bz = bz.replace(new RegExp('&lt;', 'g'),"<").replaceAll(new RegExp('&gt;', 'g'),">");
			//alert(bz);
			bz = replaceIn(bz,"&lt;","<");
			bz = replaceIn(bz,"&gt;",">");
			editor.html(bz);
		}
	});
}

function replaceIn(str,rchar,tochar){
	if(str==null||str==""){return str;}
	while(str.indexOf(rchar)!=-1){
		str = str.replace(rchar,tochar);
	}
	return str;
}

function doChild(){
	
	//生成json串
	var data = combineQuery.getQueryCombineData(queryFormForChild,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"query",data,DT1);
}
function doBz(){
	//生成json串
	var data = combineQuery.getQueryCombineData(queryFormForBz,frmPostBz,DT2);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllernameBz+"query",data,DT2);
}
function doCl(){
	//生成json串
	var data = combineQuery.getQueryCombineData(queryFormForCl,frmPostCl,DT3);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllernameCl+"query",data,DT3);
}
//详细信息-业务
function rowView(uid){
	window.open("${pageContext.request.contextPath }/ywxxView/"+uid,"审批业务详细信息");
}
function doView(obj){
	var showHtml = "<a href='javascript:void(0)' onclick='rowView("+obj.SPYW_UID+");' title='查看详细信息'><i class='icon-file'></i></a>";
	return showHtml;
}
//详细信息-步骤
function rowViewBz(uid){
	window.open("${pageContext.request.contextPath }/jsp/business/spxx/spxx-bz-edit.jsp?uid="+uid,"审批步骤信息");
}
//详细信息-步骤
function rowViewCl(uid){
	window.open("${pageContext.request.contextPath }/jsp/business/spxx/spxx-cl-edit.jsp?uid="+uid,"审批步骤信息");
}
function doBzView(obj){
	var showHtml = "<a href='javascript:void(0)' onclick='rowViewBz("+obj.SPBZ_UID+");' title='查看详细信息'><i class='icon-file'></i></a>";
	return showHtml;
}
function doClView(obj){
	var showHtml = "<a href='javascript:void(0)' onclick='rowViewCl("+obj.YWCL_UID+");' title='查看详细信息'><i class='icon-file'></i></a>";
	return showHtml;
}
function doTslx(obj){
	return obj.CLTS+"天("+obj.TSLX_SV+")";
}
function doUpxh(obj){
	var showHtml = "<a href='javascript:void(0)' onclick='doUpOrder("+obj.SPBZ_UID+","+obj.BZXH+");' title='升序'><i class='icon-arrow-up'></i></a>";
}
function doUpOrder(uid,xh){
	$.ajax({
		url : controllernameBz+"updateXh",
		data : {"uid":uid,"xh":xh},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			doBz();
		}
	});
}
function selectYw(){
	window.open("${pageContext.request.contextPath }/ywxLayer","选择父业务","height=430, width=260, top=200, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no");
}
function doQueryYw(){
	doChild();
}
function doQueryBz(){
	doBz();
}
function doQueryCl(){
	doCl();
}
</script>    
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
	</p>	
	<div class="row-fluid">
		<div class="B-small-from-table-autoConcise">
			<form method="post" id="queryForm" style="display: none;">
				<input type="text" id="QID" name="SPYW_UID" fieldname="t.SPYW_UID" operation="=" />
				<input type="text" id="QP_SPYW_UID" name="P_SPYW_UID" fieldname="t.P_SPYW_UID" operation="=" />
			</form>
			<form method="post" id="queryFormForChild" style="display: none;">
				<input type="text" id="QPID" name="QPID" fieldname="t.P_SPYW_UID" operation="=" />
			</form>
			<form method="post" id="queryFormForBz" style="display: none;">
				<input type="text" id="QYWID" name="QYWID" fieldname="t.SPYW_UID" operation="=" />
			</form>
			<form method="post" id="queryFormForCl" style="display: none;">
				<input type="text" id="QCL_YWID" name="QCL_YWID" fieldname="t.SPYW_UID" operation="=" />
			</form>
			<form method="post" id="deleteFormForBz" style="display: none;">
				<input type="text" id="SPBZ_UID" name="SPBZ_UID" fieldname="SPBZ_UID" operation="=" />
			</form>
		</div>
	<div style="height:5px;"></div>
    	<div class="B-small-from-table-autoConcise">
  		<h4 class="title">
  			业务信息
			<span class="pull-right">
				<button id="btnUpdate" onclick="" class="btn" type="button">修改</button>
			</span>    			
  		</h4>
		<form method="post" id="buSpYwxxForm"  >
	      <table class="B-table" width="100%" >
		  	<input type="hidden" id="SPYW_UID" fieldname="SPYW_UID" name = "SPYW_UID" value=""/>
	        <tr>
				<th width="8%" class="right-border bottom-border text-right">审批业务名称</th>
	       	 	<td class="bottom-border right-border" width="23%">
	         		<input class="span12" style="width:85%" id="SPYWMC" type="text" placeholder="必填" check-type="required" fieldname="SPYWMC" name = "SPYWMC"/>
	       	 	</td>
	         	<th width="8%" class="right-border bottom-border text-right">父业务名称</th>
	       	 	<td class="bottom-border right-border" width="23%">
	         		<input class="span12" style="width:85%" id="P_SPYW_UID" type="text" fieldname="P_SPYW_UID" name = "P_SPYW_UID" readonly="readonly"/>
	         		<button class="btn btn-link" type="button" id="sel_yw" onclick="selectYw()" title="点击选择父业务"><i class="icon-edit"></i></button>
	       	 	</td>
	        </tr>
	        <tr>
				<th width="8%" class="right-border bottom-border text-right">审批业务类型</th>
	       		<td class="bottom-border right-border"width="15%">
	         		<select class="span12" style="width:85%" id="SPYWLX" check-type="required" fieldname="SPYWLX" kind="dic" src="SPYWLX" name = "SPYWLX">
	         	</td>
	         	<th width="8%" class="right-border bottom-border text-right">用章名称</th>
	       		<td class="bottom-border right-border"width="15%">
	         		<input class="span12" style="width:85%" id="YZMC" type="text" placeholder="必填" check-type="required" fieldname="YZMC" name = "YZMC"/>
	         	</td>
			</tr>
			<tr>
				<th width="8%" class="right-border bottom-border text-right">处理url</th>
				<td width="17%" class="right-border bottom-border" colspan="3">
					<input class="span12" style="width:94%" id="CLURL" type="text" placeholder="必填" check-type="required" fieldname="CLURL" name = "CLURL"/>
				</td>
				
			</tr>
			<tr>
				<th width="8%" class="right-border bottom-border text-right">是否子流程</th>
				<td width="17%" class="right-border bottom-border">
					<input class="span12" style="width:85%" id="SFZLC" type="radio" check-type="required" fieldname="SFZLC" name = "SFZLC" kind="dic" src="SF"/>
				</td>
				<th width="8%" class="right-border bottom-border text-right">是否可重复申请</th>
				<td width="17%" class="right-border bottom-border">
					<input class="span12" style="width:85%" id="MULTI_Y" type="radio" check-type="required" fieldname="MULTI_Y" name = "MULTI_Y" kind="dic" src="SF"/>
				</td>
			</tr>
	        <tr>
		        <th width="8%" class="right-border bottom-border text-right">备注</th>
		        <td colspan="3" class="bottom-border right-border">
<%--		        	<textarea class="span12" rows="2" id="DESCRIBE" check-type="maxlength" maxlength="4000" fieldname="DESCRIBE" name="DESCRIBE"></textarea>--%>
		        	
		        	<textarea id="DESCRIBE" name="DESCRIBE" class="span12" fieldname="DESCRIBE" style="visibility:hidden;width: 99%;"    rows="2"></textarea>
		        </td>
	        </tr>
	        <tr>
				<th width="8%" class="right-border bottom-border text-right">排序号</th>
				<td class="right-border bottom-border" colspan="3">
					<input class="span12" style="width:100px;" id="SERIAL_NO" type="number" max="9999" min="0" maxlength="4" fieldname="SERIAL_NO" name = "SERIAL_NO"/>
				</td>
			</tr>
	      </table>
      </form>
      <div style="height:5px;" id="zyw_d"> </div>	
 		<h4 class="title" id="zyw_title">子业务信息
    		<span class="pull-right">
	  		<button id="btnAddChild" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">新增子业务</button>
      		</span>
      	</h4>
			<div id="zyw_tab" class="overFlowX">
	            <table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single"  noPage="true" pageNum="100">
	                <thead>
	                	<tr>
	                		<th  name="XH" id="_XH" style="width:10px" colindex=0 tdalign="center">&nbsp;#&nbsp;</th>
	                		<th fieldname="SPYWMC" colindex=1 tdalign="center" CustomFunction="doView">&nbsp;&nbsp;</th>
							<th fieldname="SPYWMC" colindex=2 tdalign="center" >&nbsp;审批业务名称&nbsp;</th>
							<th fieldname="SPYWLX" colindex=4 tdalign="center">&nbsp;审批业务类型&nbsp;</th>
							<th fieldname="YZMC" colindex=5 tdalign="center" >&nbsp;用章名称&nbsp;</th>
	                	</tr>
	                </thead>
	              	<tbody></tbody>
	           </table>
	       </div>
		<div style="height:5px;" id="bz_d"> </div>	
 		<h4 class="title" id="bz_title">审批步骤
    		<span class="pull-right">
    		<button id="btnDel" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">删除</button>
	  		<button id="btnAddBz" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">新增审批步骤</button>
      		</span>
      	</h4>
		<div id="bz_tab" class="overFlowX">
            <table width="100%" class="table-hover table-activeTd B-table" id="DT2" type="single"  noPage="true" pageNum="100">
                <thead>
                	<tr>
                		<th  name="XH" id="_XH" style="width:10px" colindex=0 tdalign="center">&nbsp;#&nbsp;</th>
                		<th fieldname="BZMC" colindex=1 tdalign="center" CustomFunction="doBzView">&nbsp;&nbsp;</th>
						<th fieldname="BZMC" colindex=2 tdalign="center" >&nbsp;步骤名称&nbsp;</th>
						<th fieldname="BZXH" colindex=3 tdalign="center" >&nbsp;步骤序号&nbsp;</th>
						<th fieldname="BZLX" colindex=4 tdalign="center">&nbsp;步骤类型&nbsp;</th>
						<th fieldname="CLTS" colindex=5 tdalign="center" CustomFunction="doTslx">&nbsp;处理天数&nbsp;</th>
<%--						<th fieldname="SPBZ_UID" colindex=6 tdalign="center" CustomFunction="doUpxh">&nbsp;&nbsp;</th>--%>
                	</tr>
                </thead>
              	<tbody></tbody>
           </table>
		</div>
		
		<div style="height:5px;" id="cl_d"> </div>	
 		<h4 class="title" id="cl_title">流程所需材料
    		<span class="pull-right">
	  		<button id="btnAddCl" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">新增所需材料</button>
      		</span>
      	</h4>
		<div id="cl_tab" class="overFlowX">
            <table width="100%" class="table-hover table-activeTd B-table" id="DT3" type="single"  noPage="true" pageNum="100">
                <thead>
                	<tr>
                		<th	name="XH" id="_XH" style="width:10px" colindex=0 tdalign="center">&nbsp;#&nbsp;</th>
                		<th fieldname="YWCL_UID" colindex=1 tdalign="center" CustomFunction="doClView">&nbsp;&nbsp;</th>
						<th fieldname="CLMC" colindex=2 tdalign="left" maxlength="70">&nbsp;材料名称&nbsp;</th>
						<th fieldname="CL_LEVEL" colindex=3 tdalign="center" >&nbsp;材料级别&nbsp;</th>
						<th fieldname="CLSX" colindex=4 tdalign="center">&nbsp;材料属性&nbsp;</th>
						<th fieldname="CLLX" colindex=4 tdalign="center">&nbsp;材料类型&nbsp;</th>
						<th fieldname="URL" colindex=4 tdalign="center">&nbsp;填报URL&nbsp;</th>
						<th fieldname="SL" colindex=4 tdalign="center">&nbsp;数量&nbsp;</th>
						<th fieldname="SFYSC" colindex=4 tdalign="center">&nbsp;是否要上传&nbsp;</th>
						<th fieldname="SOURCE_LCUID" colindex=4 tdalign="center">&nbsp;源自业务&nbsp;</th>
						<th fieldname="SOURCE_CLUID" colindex=4 tdalign="center">&nbsp;源自核发&nbsp;</th>
                	</tr>
                </thead>
              	<tbody></tbody>
           </table>
		</div>
    </div>
  </div>
</div>
<div align="center">
	<FORM name="frmPost" method="post" style="display: none;" target="_blank" id="frmPost">
		<!--系统保留定义区域-->
		<input type="hidden" name="queryXML" id="queryXML"/>
		<input type="hidden" name="txtXML" id="txtXML"/>
		<input type="hidden" name="txtFilter" order="desc" fieldname="t.CREATED_DATE" id="txtFilter"/>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<input type="hidden" id="queryResult"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>	
		<input type="hidden" name="currYwid" id="currYwid"/>
		<input type="hidden" name="currYwmc" id="currYwmc"/>	
	</FORM>
	<FORM name="frmPostBz" method="post" style="display: none;" target="_blank" id="frmPostBz">
	<!--系统保留定义区域-->
		<input type="hidden" name="queryXML" id="queryXML"/>
		<input type="hidden" name="txtXML" id="txtXML"/>
		<input type="hidden" name="txtFilter" order="asc" fieldname="t.BZXH" id="txtFilter"/>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<input type="hidden" id="queryResult"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>	
	</FORM>
	<FORM name="frmPostCl" method="post" style="display: none;" target="_blank" id="frmPostCl">
	<!--系统保留定义区域-->
		<input type="hidden" name="queryXML" id="queryXML"/>
		<input type="hidden" name="txtXML" id="txtXML"/>
		<input type="hidden" name="txtFilter" order="asc" fieldname="t.SERIAL_NO" id="txtFilter"/>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<input type="hidden" id="queryResult"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>	
	</FORM>
</div>
</body>
</html>