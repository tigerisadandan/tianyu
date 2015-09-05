<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>审批业务信息-维护</title>
<%
	//String type=request.getParameter("type");
String puid = (String)request.getAttribute("puid");
%>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/spxx/buSpYwxxController/";
var puid ="<%=puid%>";
//页面初始化
$(function() {
	init();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,buSpYwxxList);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"query",data,buSpYwxxList);
	});
	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
		if($("#buSpYwxxForm").validationButton())
		{
		    //生成json串
		    var data = Form2Json.formToJSON(buSpYwxxForm);
		  //组成保存json串格式
		    var data1 = defaultJson.packSaveJson(data);
		  //调用ajax插入
<%--		    if($("#ID").val() == "" || $("#ID").val() == null){--%>
    			var flag = defaultJson.doInsertJson(controllername + "insert", data1);
    			if(flag){
        			alert("新增完成!");
        			window.opener.doQueryYw();
					window.close();
        		}

		}else{
			requireFormMsg();
		  	return;
		}
	});
	
	
	
	
});
//页面默认参数
function init(){
	getParent();
}
function setParent(uid,name){
	$("#P_SPYW_UID").attr("code",uid);
	$("#P_SPYW_UID").val(name);
}
function getParent(){
	//puid = 1 从父页面的添加子流程进入,
	//puid = 0 从父页面的添加直接进入
	if(puid=="1"){
		var p_yw_uid = $(window.opener.document).find("input[name='currYwid']").val();
		var p_name = $(window.opener.document).find("input[name='currYwmc']").val();
		$("#P_SPYW_UID").attr("code",p_yw_uid);
		$("input:radio[name='SFZLC']")[2].checked=true;
		$("#P_SPYW_UID").val(p_name);
		$("#sel_yw").hide();
	}else if(puid=="0"){
		$("input:radio[name='SFZLC']")[1].checked=true;
		$("#sel_yw").show();
	}
}

function selectYw(){
	window.open("${pageContext.request.contextPath }/ywxLayer","选择父业务","height=430, width=260, top=200, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no");
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
	        <TD class="right-border bottom-border"></TD>
			<TD class="right-border bottom-border">
			</TD>
        </TR>
      </table>
      </form>
	</div>
	<div style="height:5px;"></div>
	<div class="B-small-from-table-autoConcise">
      <h4 class="title">
      	<span class="pull-right">
      		<button id="btnClose" onClick="window.close()" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
	  		<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">保存</button>
      	</span>
      </h4>
     <form method="post" id="buSpYwxxForm"  >
      <table class="B-table" width="100%" >
      <input type="hidden" id="ID" fieldname="ID" name = "ID"/></TD>
	  	<input type="hidden" id="SPYW_UID" fieldname="SPYW_UID" name = "SPYW_UID" value=""/></TD>
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
			<td width="17%" class="right-border bottom-border" >
				<input class="span12" style="width:94%" id="CLURL" type="text" placeholder="必填" check-type="required" fieldname="CLURL" name = "CLURL"/>
			</td>
			<th width="8%" class="right-border bottom-border text-right">是否子流程</th>
			<td width="17%" class="right-border bottom-border" colspan="3">
				<input class="span12" style="width:94%" id="SFZLC" type="radio" check-type="required" fieldname="SFZLC" name = "SFZLC" kind="dic" src="SF"/>
			</td>
		</tr>
        <tr>
	        <th width="8%" class="right-border bottom-border text-right">备注</th>
	        <td colspan="3" class="bottom-border right-border">
	        	<textarea class="span12" rows="2" id="DESCRIBE" check-type="maxlength" maxlength="4000" fieldname="DESCRIBE" name="DESCRIBE"></textarea>
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
    </div>
   </div>
  </div>
  <div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id = "queryXML">
         <input type="hidden" name="txtXML" id = "txtXML">
         <input type="hidden" name="txtFilter"  order="desc" fieldname = "t.CREATED_DATE" id = "txtFilter">
         <input type="hidden" name="resultXML" id = "resultXML">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
		
 	</FORM>
 </div>
</body>
<script>
</script>
</html>