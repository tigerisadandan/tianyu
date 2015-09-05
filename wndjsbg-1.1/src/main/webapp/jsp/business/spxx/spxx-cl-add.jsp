<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>审批业务信息-维护</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/spxx/buSpYwclController/";
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
    			var flag = defaultJson.doInsertJson(controllername + "insert", data1);
    			if(flag){
        			alert("保存完成!");
        			window.opener.doQueryCl();
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
	$("input:radio[name='SFYSC']")[1].checked = true;
	$("input:radio[name='CLSX']")[1].checked = true;
}
function setParent(uid,name){
	$("#P_SPYW_UID").attr("code",uid);
	$("#P_SPYW_UID").val(name);
}
function getParent(){
	//puid = 1 从父页面的添加子流程进入,
	//puid = 0 从父页面的添加直接进入
	var p_yw_uid = $(window.opener.document).find("input[name='currYwid']").val();
	$("#SPYW_UID").val(p_yw_uid);
}
function selectCl(){
	window.open("${pageContext.request.contextPath }/jsp/business/spxx/select-clk.jsp","选择材料");
}
function doSelectCl(jsonStr){
	var obj = eval('('+jsonStr+')');
	$("#CLK_UID").val(obj.CLMC);
	$("#CLK_UID").attr("code",obj.CLK_UID);
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
     <form method="post" id="buSpYwxxForm">
      <table class="B-table" width="100%">
      <input class="span12" style="width:85%" id="SPYW_UID" type="hidden" fieldname="SPYW_UID" name = "SPYW_UID"/>
	 	<tr>
			<th width="8%" class="right-border bottom-border text-right">材料名称</th>
	    	<td class="bottom-border right-border" width="23%">
	       		<input class="span12" style="width:85%" id="CLK_UID" type="text" fieldname="CLK_UID" name = "CLK_UID" readonly="readonly"/>
	       		<button class="btn btn-link" type="button" id="sel_yw" onclick="selectCl()" title="点击选择材料"><i class="icon-edit"></i></button>
			</td>
			<th width="8%" class="right-border bottom-border text-right">材料属性</th>
       	 	<td class="bottom-border right-border" width="23%">
         		<input class="span12" style="width:85%" id="CLSX" type="radio" check-type="required" kind="dic" src="CLSX" fieldname="CLSX" name = "CLSX"/>
       	 	</td>
	   	</tr>
        <tr>
			<th width="8%" class="right-border bottom-border text-right">数量</th>
       		<td class="bottom-border right-border"width="15%">
         		<input type="text" class="span12" style="width:85%" id="SL"   fieldname="SL" name = "SL"/>
         	</td>
       	 	<th width="8%" class="right-border bottom-border text-right">是否要上传</th>
       		<td class="bottom-border right-border"width="15%">
         		<input class="span12" style="width:85%" type="radio" id="SFYSC" check-type="required" kind="dic" src="SF" fieldname="SFYSC" name = "SFYSC"/>
         	</td>
        </tr>
		<tr>
			<th width="8%" class="right-border bottom-border text-right">材料内容</th>
       		<td class="bottom-border right-border"width="15%">
         		<input class="span12" style="width:85%" id="CLNR" type="text" fieldname="CLNR" name = "CLNR"/>
         	</td>
			<th width="8%" class="right-border bottom-border text-right">在线填报URL</th>
       		<td class="bottom-border right-border"width="15%">
         		<input class="span12" style="width:85%" id="URL" type="text" fieldname="URL" name = "URL"/>
         	</td>
		</tr>
        <tr>
	        <th width="8%" class="right-border bottom-border text-right">备注</th>
	        <td colspan="3" class="bottom-border right-border">
	        	<textarea class="span12" rows="2" id="DESCRIBE" check-type="maxlength" maxlength="4000" fieldname="DESCRIBE" name="DESCRIBE"></textarea>
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