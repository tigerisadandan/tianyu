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
<title></title>
<%
	String xmbh=request.getParameter("xmbh");
String id=(String)request.getAttribute("id");
	String zt=request.getParameter("zt");
	String pch=request.getParameter("pch");
	System.out.println(zt+"      "+pch);
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">

var controllername= "${pageContext.request.contextPath }/sp/buSpywZzhydjController/";
var controllerfile= "${pageContext.request.contextPath }/controller/fileController/";

var xmbh,id,json_xg,pch,zt;
 id ="<%=id%>";

//初始化加载
$(document).ready(function(){
$("#pubAlert").hide();

 $("#btnClose").click(function(){
		    window.close();
		    });
		    $("#btnSave").click(function(){
		    $.ajax({
			url : controllername + "download?uid="+$("#ZZHYDJ_UID").val(),
			cache : false,
			async : false,
			dataType : "json",
			type : 'post',
			success : function(response) {
		    	window.location.href = controllerfile+"download2?path="+response;
			 } 
		    });
		    
		    });
	xmbh='<%=xmbh%>';
	id='<%=id%>';
	pch='<%=pch%>';
	zt='<%=zt%>';
	$("#djid").val(id); //查询 绑定 ID
	var data=combineQuery.getQueryCombineData(queryForm,frmPost);
	doquery_xmxx(controllername+"query",data,null);
	var json=$("#queryResult").val();
	var tempJson=eval("("+json+")");
	var resultobj=tempJson.response.data[0];
	$("#demoForm").setFormValues(resultobj);
	$("#demoForm").setHtmlValue(resultobj);
	json_xg=JSON.stringify(resultobj)
});

//弹出修改窗口
//function OpenMiddleWindow_alter(){
//	json_xg=encodeURI(json_xg); 
//	$(window).manhuaDialog({"title":"项目储备库>修改","type":"text","content":"${pageContext.request.contextPath }/jsp/business/sp/sp-add.jsp?xx="+json_xg+"&&id="+id+"&zt="+zt+"&pch="+pch,"modal":"1"});
//}


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
	</p>	
	<div class="row-fluid">
    	<div class="B-small-from-table-autoConcise">
      		<span style="float:right">
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
	  		<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">打印</button>
          </span><br/>
     		<form method="post" id="demoForm">
     		  <h4 class="title" align="center">新区建设项目扩初设计评审申请表 </h4>
      		<div align="center">  
  <div align="center">  
  <table class="cc-alert-table"  > 
   <input type="hidden" id="ID" fieldname="ID" name = "ID"/></TD>
	  	  <input type="hidden" id="GC_TCJH_XMXDK_ID" fieldname="XMID" name = "XMID"/></TD> 
	  	  <INPUT type="hidden" id="ZZHYDJ_UID" fieldname="ZZHYDJ_UID" />
  <tr> 
    <td width="22%" >建设单位</td>  
    <td width="22%" fieldname="JSDW"></td>
    <td width="10%">联系人</td>
    <td width="12%" fieldname="LXR_JSDW"></td>
    <td width="13%">电话</td>
    <td width="15%" fieldname="DH_JSDW"></td>
  </tr>
  <tr>
    <td>工程名称</td>
    <td colspan="3" fieldname="GCNAME"></td>
    <td>建设地点</td>
    <td fieldname="JSDD"></td>
  </tr>
  <tr> 
    <td>批文文号</td>
    <td colspan="3" fieldname="PWWH"></td>
    <td>建设面积(m²)</td>
    <td fieldname="JSMJ"></td>
  </tr>
  <tr>
    <td>开工日期</td>
    <td colspan="3" fieldname="DATE_KG"></td>
    <td>竣工日期</td>
    <td fieldname="DATE_JG"></td>
  </tr>
  <tr>
    <td>勘察内容</td>
    <td colspan="5" fieldname="KCNR"></td>
  </tr>
  <tr>
    <td>勘察单位名称(公章)</td>
    <td colspan="3" fieldname="KCDWNAME"></td>
    <td>资质等级</td>
    <td fieldname="ZZDJ"></td>
  </tr>
  <tr >
    <td>项目负责人</td>
    <td fieldname="XMFZR_KC"></td>
    <td>职称</td>
    <td fieldname="ZC_KC"></td>
    <td>电话</td>
    <td fieldname="DH_KC"></td>
  </tr>
  <tr>
    <td>设计内容</td>
    <td colspan="5" fieldname="SJNR" ></td>
  </tr>
  <tr>
    <td>设计单位名称(公章)</td>
    <td colspan="3" fieldname="SJDWMC"></td>
    <td>资质等级</td>
    <td fieldname="ZZDJ_SJ"></td>
  </tr>
  <tr>
    <td>项目负责人</td>
    <td fieldname="XMFZR_SJ"></td>
    <td>职称</td>
    <td fieldname="ZC_SJ"></td>
    <td>电话</td>
    <td fieldname="DH_SJ"></td>
  </tr>
  
  <tr height="60px" cellSpacing=0 cellPadding=0 >
    <td colspan="6" cellSpacing=0 cellPadding=0 style="padding-left: 0px;" >
    <table width="100%" frame=void  style="BORDER-COLLAPSE:collapse;" border="1" cellSpacing=0 cellPadding=0 > 
        <tr border=0;>
          <td width="22%"  style="border-top:0;border-left: 0;">勘察专业人员情况</td>
          <td width="12%" style="border-top:0;">姓名</td>
           <td width="15%" style="border-top:0;" fieldname="KCZYRY_NAME1"></td>
          <td  width="15%" style="border-top:0;" fieldname="KCZYRY_NAME2"></td>
          <td  width="16%" style="border-top:0;" fieldname="KCZYRY_NAME3"></td>
          <td  width="16%" style="border-top:0;border-right: 0;" fieldname="KCZYRY_NAME4"></td>
          </tr> 
        <tr>  
          <td style="border-left: 0;"></td>
          <td>专业</td>
          <td fieldname="KCZYRY_ZY1" ></td>
          <td fieldname="KCZYRY_ZY2"></td>
          <td fieldname="KCZYRY_ZY3"></td>
          <td fieldname="KCZYRY_ZY4" style="border-right: 0;"></td>
          </tr>
        <tr>
          <td style="border-left: 0;"></td>
          <td>职称</td>
          <td fieldname="KCZYRY_ZC1" ></td>
          <td fieldname="KCZYRY_ZC2"></td>
          <td fieldname="KCZYRY_ZC3"></td>
          <td fieldname="KCZYRY_ZC4" style="border-right: 0;"></td>
          </tr>
        <tr>
          <td style="border-left: 0;">设计专业人员情况</td>
          <td>姓名</td>
          <td fieldname="SJZYRY_NAME1" ></td>
          <td fieldname="SJZYRY_NAME2" ></td>
          <td fieldname="SJZYRY_NAME3" ></td>
          <td fieldname="SJZYRY_NAME4" style="border-right: 0;"></td>
          </tr>
        <tr>
          <td style="border-left: 0;"></td>
          <td>专业</td>
          <td fieldname="SJZYRY_ZY1" ></td>
          <td fieldname="SJZYRY_ZY2"></td>
          <td fieldname="SJZYRY_ZY3"></td>
          <td fieldname="SJZYRY_ZY4" style="border-right: 0;"></td>
          </tr>
        <tr>
          <td style="border-bottom: 0;border-left: 0;" ></td>
          <td style="border-bottom: 0;">职称</td>
          <td fieldname="SJZYRY_ZC1" style="border-bottom: 0;"></td>
          <td fieldname="SJZYRY_ZC2" style="border-bottom: 0;"></td>
          <td fieldname="SJZYRY_ZC3" style="border-bottom: 0;"></td>
          <td fieldname="SJZYRY_ZC4" style="border-bottom: 0;border-right: 0;"></td>
          </tr>
      </table>
    </td>
  </tr>
   <tr >
    <td valign="top" height="127"  colspan="6">
      <div align="left" height="50" ><br/>&nbsp;建设单位意见</div>
      <div align="right" >
          <br/><br/><br/><br/><br/>
          (签章)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               &nbsp;年&nbsp;&nbsp;月&nbsp;&nbsp;日&nbsp;
        </div> 
    </td>
  </tr>
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
			<INPUT type="hidden" class="span12" kind="text" id="djid" fieldname="YWLZ_UID" value="1000" operation="="/>
		</TD>
	</TR>	
</form>
</body>
</html>