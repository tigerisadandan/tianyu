<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>

<title>雨污水入网申请-维护</title>
<%
	String id=(String)request.getAttribute("id");

%>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/sp/css/bootstrap.css?version=20131201"> </LINK>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/sp/css/xAlert.css"> </LINK>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/base/jquery.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/jsp/business/sp/js/printPageHtml.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/base/LockingTable.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/base/bootstrap.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/base/jsBruce.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/Form2Json.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/convertJson.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/combineQuery.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/default.js?version=20131206"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/TabList.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/bootstrap-validation.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/loadFields.js?version=20131206"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"> </script>
<script type="text/javascript" charset="utf-8"> 

var controllername= "${pageContext.request.contextPath }/sp/buSpywYwsrwsqController/";
var controllerfile= "${pageContext.request.contextPath }/controller/fileController/";

var id ="<%=id%>";

$(function() {
	init();
	$("#downLoad").click(function() {
		download();
	});
	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
		if($("#buSpywYwsrwsqForm").validationButton())
		{
		    //生成json串
		    var data = Form2Json.formToJSON(buSpywYwsrwsqForm);
		  //组成保存json串格式
		    var data1 = defaultJson.packSaveJson(data);
		    defaultJson.doInsertJson(controllername + "insert", data1);

		   
		}else{
			requireFormMsg();
		  	return;
		}
	});
	
});
//只输入数字 
function onlyNum(obj)
{
	obj.value = obj.value.replace(/[^\d]/g,"");
}
//只能输入数字和小数点
function onlyNumAndFloat(obj)
 {
  //先把非数字的都替换掉，除了数字和.
  obj.value = obj.value.replace(/[^\d.]/g,"");
  //必须保证第一个为数字而不是.
  obj.value = obj.value.replace(/^\./g,"");
  //保证只有出现一个.而没有多个.
  obj.value = obj.value.replace(/\.{2,}/g,".");
  //保证.只出现一次，而不能出现两次以上
  obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
 }
function download(){
	
	$.ajax({
		url : controllername+"download?id="+$("#YWSRWSQ_UID").val(), 
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			$("#resultXML").val(response.msg);
<%--			var resultobj = defaultJson.dealResultJson(response.msg);--%>
			//window.open(controllerfile+"download2?path="+response.msg,'文件下载打印');
			window.location.href = controllerfile+"download2?path="+response.msg;
	}
	});
	
}
function init(){
	$("#pubAlert").hide();
	if(id!="null"&&id!=""){
		 $("#QID").val(id);
		 $("#YWSRWSQ_UID").val(id);
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
				$("#resultXML").val(response.msg);
				var resultobj = defaultJson.dealResultJson(response.msg);
				$("#WSJR_L").html(resultobj.WSJR_L);
				$("#WSJR_J").html(resultobj.WSJR_J);
				$("#YSJR_L").html(resultobj.YSJR_L);
				$("#YSJR_J").html(resultobj.YSJR_J);
				
				$("#buSpywYwsrwsqForm").setFormValues(resultobj);
				$("#buSpywYwsrwsqForm").setHtmlValue(resultobj);
			}
		});
	}
}
</script>    
</head>
<body>

<div class="container-fluid">
	<div class="row-fluid">
    	<div class="B-small-from-table-autoConcise">
    	 <form method="post" id="queryForm"  >
      <table class="B-table" width="120%">
      <!--可以再此处加入hidden域作为过滤条件 -->
      <TR  style="display:none;">
	        <TD class="right-border bottom-border">
	        	<INPUT type="text" class="span12" kind="text" id="QID" name="ID"  fieldname="YWLZ_UID" value="" operation="="/>
	        </TD>
			<TD class="right-border bottom-border">
			</TD>
        </TR>
      </table>
      </form>
      </div>
	</div>
	<div class="B-small-from-table-autoConcise">
      		<span style="float:right">
      			<button id="btnShutDown" onclick="window.close();" class="btn" type="button" style="font-family: SimYou, Microsoft YaHei; font-weight: bold;">关闭</button>
			    <button id="downLoad" class="btn" type="button" style="font-family: SimYou, Microsoft YaHei; font-weight: bold;">打印</button>
			</span>    
			<br/>			
      	<form method="post" id="buSpywYwsrwsqForm">
     	 <h4 class="title" align="center">污水、雨水接入管网(土建环保验收)申请表</h4>
      	
      <table style="width: 700px;height: 600px;"  border="1" class="cc-alert-table" align="center">
         <input type="hidden" id="YWSRWSQ_UID" fieldname="YWSRWSQ_UID" name = "YWSRWSQ_UID" />
         <input type="hidden" id="YWLZ_UID" fieldname="YWLZ_UID" name = "YWLZ_UID"/>
 <tr>
      <td width="115" height="30"><div align="right">申请单位</div></td>
      <td width="245" fieldname="SQDW" >
      
      <td width="117" height="30"><div align="right">项目名称</div></td>
      <td width="245" fieldname="XMMC" >
    </tr>
    <tr>
      <td height="30"><div align="right">项目批准文号</div></td>
      <td fieldname="XMPZWH">
      <td height="30"><div align="right">项目地点</div></td>
      <td fieldname="XMDD">
    </tr>
    <tr>
      <td height="30"><div align="right">联系人</div></td>
      <td fieldname="LXR" >
      <td height="30"><div align="right">联系电话</div></td>
      <td fieldname="LXDH" >
    </tr>
    <tr>
      <td height="30"><div align="right">开工日期</div></td>
      <td fieldname="KG_DATE">
      <td height="30"><div align="right">完工日期</div></td>
      <td fieldname="WG_DATE">
    </tr>
    <tr>
      <td height="30" ><div align="right">用水量(吨/日)</div></td>
      <td fieldname="YXL">
      <td height="30" ><div align="right">排污量(吨/日)</div></td>
      <td fieldname="PWL" >
    </tr>
    <tr>
      <td height="30" ><div align="right">污水管长度(米)</div></td>
      <td fieldname="WSGCD">
      <td height="30" ><div align="right">雨水管长度(米)</div></td>
      <td fieldname="YSGCD" >
    </tr>
    <tr>
      <td height="30" ><div align="right">污水接入口数量(个)</div></td>
      <td fieldname="WSJKSL" >
      <td height="30" ><div align="right">雨水接入口数量(个)</div></td>
      <td fieldname="YSJKSL">
    </tr>
    <tr>
      <td height="30"><div align="right">施工单位</div></td>
      <td colspan="3" fieldname="SGDW" >
    </tr>
    <tr>
      <td height="30"><div align="right">施工负责人</div></td>
      <td fieldname="SGFZR" >
      <td height="30"><div align="right">负责人联系电话</div></td>
      <td fieldname="SGFZRLXDH">
    </tr>
    <tr>
      <td height="30"><div align="right">接管地点</div></td>
      <td colspan="3" fieldname="JGDD" >
    </tr>
    <tr>
      <td height="98" style="line-height:5px"><div align="right">
        <p>环境监察大队</p>
        <p>现场验收意见</p>
      </div></td>
      <td colspan="3" fieldname="JCDDYSYJ" >
    </tr>
    <tr>
      <td height="95"><div align="right">排水处签发</div></td>
      <td colspan="3"><br /><br /><div style="float:right">
签发人：&nbsp;&nbsp;&nbsp;&nbsp;(章)&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</div></td>
    </tr>
    <tr>
      <td height="30"><div align="right">同意污水接入：</div></td>
      <td>
     <span id="WSJR_L"></span>
          路
	<span id="WSJR_J"></span>
          井</td>
      <td><div align="right">同意雨水接入：</div></td>
      <td>
      <span id="YSJR_L"></span>
	路
	<span id="YSJR_J"></span>
	井</td>
    </tr>
    <tr>
      <td  height="120"><div align="right">备注</div></td>
      <td colspan="3">&nbsp;</td>
    </tr>
  </table>
      	</form>
    </div>
  </div>
</div>
<div align="center">
	<FORM name="frmPost" method="post" style="display:none" target="_blank" id="frmPost">
		<!--系统保留定义区域-->
		<input type="hidden" name="queryXML" id="queryXML"/>
		<input type="hidden" name="txtXML" id="txtXML"/>
		<input type="hidden" name="txtFilter" order="desc" fieldname="CREATED_DATE" id="txtFilter"/>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<input type="hidden" id="queryResult"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>		
	</FORM>
</div>

</body>
</html>