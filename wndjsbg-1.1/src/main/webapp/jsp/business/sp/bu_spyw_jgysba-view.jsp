<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<LINK type="text/css" rel="stylesheet" href="/wndjsbg/jsp/business/sp/css/xAlert.css"> </LINK>
<script type="text/javascript" src="/wndjsbg/js/base/jquery.js"> </script>
<script type="text/javascript" src="/wndjsbg/jsp/business/sp/js/printPageHtml.js"> </script>
<script type="text/javascript" src="/wndjsbg/js/base/LockingTable.js"> </script>
<script type="text/javascript" src="/wndjsbg/js/base/bootstrap.js"> </script>
<script type="text/javascript" src="/wndjsbg/js/base/jsBruce.js"> </script>
<script type="text/javascript" src="/wndjsbg/js/common/Form2Json.js"> </script>
<script type="text/javascript" src="/wndjsbg/js/common/convertJson.js"> </script>
<script type="text/javascript" src="/wndjsbg/js/common/combineQuery.js"> </script>
<script type="text/javascript" src="/wndjsbg/js/common/default.js?version=20131206"> </script>
<script type="text/javascript" src="/wndjsbg/js/common/TabList.js"> </script>
<script type="text/javascript" src="/wndjsbg/js/common/bootstrap-validation.js"> </script>
<script type="text/javascript" src="/wndjsbg/js/common/loadFields.js?version=20131206"> </script>
<script type="text/javascript" src="/wndjsbg/js/My97DatePicker/WdatePicker.js"> </script>
<title>竣工验收备案-维护</title>
<%
	
	String id=(String)request.getAttribute("id");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/sp/buSpywJgysbaController/";
var controllerfile= "${pageContext.request.contextPath }/controller/fileController/";
var id ="<%=id%>";
//初始化加载
$(document).ready(function(){
    $("#pubAlert").hide();
    $("input:checkbox").attr("disabled","true");
     $("#btnClose").click(function(){
		    window.close();
		    });
		    $("#btnSave").click(function(){
		    $.ajax({
			url : controllername + "download?uid="+$("#JGYSBA_UID").val(),
			cache : false,
			async : false,
			dataType : "json",
			type : 'post',
			success : function(response) {
		    	window.location.href = controllerfile+"download2?path="+response;
			 } 
		    });
		    
		    });
    
	$("#YWLZ_UID").val(id); //查询 绑定 ID
	var data=combineQuery.getQueryCombineData(queryForm,frmPost);
	doquery_xmxx(controllername+"query",data,null);
	var json=$("#queryResult").val();
	var tempJson=eval("("+json+")");
	var resultobj=tempJson.response.data[0];
	$("#buSpywJgysbaForm").setHtmlValue(resultobj);
	$("#buSpywJgysbaForm").setFormValues(resultobj);
	json_xg=JSON.stringify(resultobj)
});

//弹出修改窗口
function OpenMiddleWindow_alter(){
	json_xg=encodeURI(json_xg); 
	$(window).manhuaDialog({"title":"项目储备库>修改","type":"text","content":"${pageContext.request.contextPath }/jsp/business/sp/sp-add.jsp?xx="+json_xg+"&&id="+id+"&zt="+zt+"&pch="+pch,"modal":"1"});
}


//项目信息查询
doquery_xmxx= function(actionName, data1,tablistID){
    var success  = true;
	var data = {
		msg : data1
	};
	$.ajax({
		url : actionName,
		data : data,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(result) {
			//查询清空结果
			var returnMsg = result.msg;
			$("#queryResult").val(result.msg);
			success = true;
		}
	});
    return success;	
};
</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">
	</div>
	<div style="height:5px;"></div>
		<div class="B-small-from-table-autoConcise">
	    <span style="float:right">
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
	  		<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">打印</button>
      	</span><br/>	
      	
     <form method="post" id="buSpywJgysbaForm"  > 
           <h4 class="title" align="center">房屋建筑工程和市政基础设施工程竣工验收备案表 </h4>
         
	<div align="center">  
  <table    border="1" class="jgysba-table" style="font-size: 17px;" > 
  <INPUT type="hidden" id="JGYSBA_UID" fieldname="JGYSBA_UID" />
  <tr>
    <td class="certer" width="150px;" >建设单位名称</td>
    <td colspan="3" fieldname="JSDWMC">
    </td>
  </tr>
   <tr>
    <td class="certer">备案日期</td>
    <td fieldname="BARQ_DATE">
   </td>
    <td class="certer" style="font-size: 12px;" width="100px;">建设单位<br/>联系人电话</td>
    <td width="90px;" fieldname="JSDWLXRDH">
   </td>
  </tr>
  
  <tr>
    <td class="certer">工程名称</td>
    <td colspan="3"  fieldname="GCMC">
    </td>
 
  </tr>
  <tr >
    <td class="certer">工程地点</td>
    <td colspan="3" fieldname="GCDD">
     </td>
 
  </tr>
  <tr >
    <td class="certer">建筑面积(  m²  )</td>
    <td colspan="3" fieldname="GCMJ">
     </td>
 
  </tr>
  <tr>
    <td class="certer">结构类型及层次</td>
    <td colspan="3" fieldname="JGLXJCC">
    </td>
 
  </tr>
  <tr>
    <td class="certer">工程用途</td>
    <td colspan="3" fieldname="GCYT">
    </td>
 
  </tr>
  <tr>
    <td class="certer">开工日期</td>
    <td colspan="3" fieldname="KGRQ_DATE">
   </td>
 
  <tr>
    <td class="certer">竣工验收日期</td>
    <td colspan="3" fieldname="JGYSRQ">
    </td>
 
  </tr>
  <tr>
    <td class="certer">施工许可证号</td>
    <td colspan="3" fieldname="SGXKZH">
    </td>
 
  </tr>
  <tr>
    <td class="certer">施工图审查合格书</td>
    <td colspan="3" fieldname="SGTSCHGS">
    </td>
 
  </tr>
  <tr >
    <td class="certer">勘察单位名称</td>
    <td  fieldname="KCDW_MC">
    </td>
 
    <td class="certer">资质等级</td>
    <td fieldname="KCDW_ZZDJ">
    </td>
 
  </tr>
  <tr>
    <td class="certer">设计单位名称</td>
    <td fieldname="JSDW_MC">
   </td>
 
    <td class="certer">资质等级</td>
    <td  fieldname="JSDW_ZZDJ" >
    </td>
 
  </tr>
  <tr>
    <td class="certer">施工单位名称</td>
    <td fieldname="SGDW_MC">
    </td>
 
    <td class="certer">资质等级</td>
    <td fieldname="SGDW_ZZDJ">
    </td>
 
  </tr>
  <tr>
    <td class="certer">监理单位名称</td>
    <td fieldname="JLDW_MC">
    </td>
 
    <td class="certer">资质等级</td>
    <td fieldname="JLDW_ZZDJ" >
    </td>
 
  </tr>
  <tr>
    <td class="certer">工程质量监督<br/>机构名称</td>
    <td colspan="3" fieldname="GCZLJDJGMC" >
   </td>
 
</table>
</div>
      </form>
    </div>
   </div>
  </div>
  <div align="center">
	<FORM name="frmPost" method="post" style="display:none" target="_blank" id="frmPost">
		<!--系统保留定义区域-->
		<input type="hidden" name="queryXML" id="queryXML"/>
		<input type="hidden" name="txtXML" id="txtXML"/>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<input type="hidden" id="queryResult"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>		
	</FORM>
</div>
<form method="post" id="queryForm">
	<!--可以再此处加入hidden域作为过滤条件 -->
	<TR style="display: none;">
		<TD class="right-border bottom-border"></TD>
		<TD class="right-border bottom-border">
			<INPUT type="hidden" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>
			<INPUT type="hidden" class="span12" kind="text" id="YWLZ_UID" fieldname="YWLZ_UID" value="0" operation="="/>
		</TD>
	</TR>	
</form>
 </div>
</body>
<script>
</script>
</html>