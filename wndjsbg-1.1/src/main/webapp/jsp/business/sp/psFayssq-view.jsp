<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>

<title>排水方案预审申请-维护</title>
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

var controllername= "${pageContext.request.contextPath }/sp/buSpywPsfayssqController/";
var controllerfile= "${pageContext.request.contextPath }/controller/fileController/";


var id ="<%=id%>";

$(function() {
	init();
	//按钮绑定事件（查询）
	$("#downLoad").click(function() {
		download();
	});
	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
		if($("#buSpywPsfayssqForm").validationButton())
		{
		    //生成json串
		    var data = Form2Json.formToJSON(buSpywPsfayssqForm);
		  //组成保存json串格式
		    var data1 = defaultJson.packSaveJson(data);
		    defaultJson.doInsertJson(controllername + "insert", data1);

		   
		}else{
			requireFormMsg();
		  	return;
		}
	});
	
	//按钮绑定事件（新增）
    $("#btnClear_Bins").click(function() {
        $("#buSpywPsfayssqForm").clearFormResult();
        $("#buSpywPsfayssqForm").cancleSelected();
        
        
        $("#ZFRQ").val(new Date().toLocaleDateString());
        $("#ZFJE").val(0);
        $("#ID").val("");
    });
	
	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#queryForm").clearFormResult();
    })
    
 	
});
function init(){
	$("#pubAlert").hide();
	if(id!="null"&&id!=""){
		 $("#QID").val(id);
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
				
				
				$("#buSpywPsfayssqForm").setFormValues(resultobj);
				$("#buSpywPsfayssqForm").setHtmlValue(resultobj);
				
		}
		});
	}
}
function download(){
	var resultobj;
	$.ajax({
		url : controllername+"download?id="+$("#PSFAYSSQ_UID").val(), 
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			$("#resultXML").val(response.msg);
			
			//window.open(controllerfile+"download2?path="+response.msg,'文件下载打印');
			window.location.href = controllerfile+"download2?path="+response.msg;
	}
	});
}
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


</script>    
</head>
<body>

<div class="container-fluid">
	<div class="row-fluid">
    	<div class="B-small-from-table-autoConcise">
    	 <form method="post" id="queryForm"  >
      <table class="B-table" width="100%">
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
      	<form method="post" id="buSpywPsfayssqForm">
     	 <h4 class="title" align="center">无锡新区城市排水方案预审申请表</h4>
      	<table style="width: 750px;height: 700px;"  border="1"  align="center" class="cc-alert-table" >
     <input type="hidden" id="PSFAYSSQ_UID" fieldname="PSFAYSSQ_UID" name = "PSFAYSSQ_UID"/></TD>
     <input type="hidden" id="YWLZ_UID" fieldname="YWLZ_UID" name = "YWLZ_UID"/>
    <tr>
      <td width="131" height="25"><div align="right">*单位名称</div></td>
      <td colspan="3" fieldname="DWMC">
    </tr>
    <tr>
      <td height="25"><div align="right">*单位地址</div></td>
      <td colspan="3" fieldname="DWDZ" >
    </tr>
    <tr>
      <td height="25"><div align="right">*法定代表人</div></td>
      <td width="230"  fieldname="FDDBR">
      <td width="131"><div align="right">*联系电话</div></td>
      <td width="230" fieldname="FDDBR_LXDH" >
    </tr>
    <tr>
      <td height="25"><div align="right">*申请办理人</div></td>
      <td fieldname="SQBLR" >
      <td><div align="right">*联系电话</div></td>
      <td fieldname="SQBLR_LXDH" >
    </tr>
    <tr>
      <td height="25"><div align="right">项目名称</div></td>
      <td colspan="3" fieldname="XMMC" >
    </tr>
    <tr>
      <td height="25"><div align="right">建设地点</div></td>
      <td colspan="3" fieldname="JSDD" >
    </tr>
    <tr>
      <td height="25"><div align="right">用地面积</div></td>
      <td fieldname="YDMJ">
      <td><div align="right">*行业分类</div></td>
      <td fieldname="HYFL" >
    </tr>
    <tr>
      <td height="25"><div align="right">*污水性质</div></td>
      <td>&nbsp;&nbsp;
        <label class="checkbox inline" >  
        <input type="checkbox" name="WSXZ_SHWS" id="WSXZ_SHWS" fieldname="WSXZ_SHWS" value="0" onclick="return false"/>
    生活污水</label>
    <label class="checkbox inline" >  
        <input type="checkbox" name="WSZX_GYWS" id="WSZX_GYWS"  fieldname="WSZX_GYWS" value="1" onclick="return false"/>
    工业污水</label>
  	</td>
      <td><div align="right">*污水量(m3/d)</div></td>
      <td fieldname="SHWS_L"  >
        </td>
    </tr>
    <tr>
      <td height="25"><div align="right">*用水量(m3/d)</div></td>
      <td fieldname="YSL" >
      </td>
      <td><div align="right">主要污染物</div></td>
      <td fieldname="ZYWRW"  >
    </tr>
    <tr>
      <td height="117" colspan="4">一、新建项目<br />&nbsp;
        <label class="checkbox inline" >  
          <input type="checkbox" name="SFCLML" id="SFCLML" fieldname="SFCLML" value="01" title="01" onclick="return false"/>
          1、项目选址图（或地块规划图）</label>
        <br />&nbsp;
        <label class="checkbox inline" >  
          <input type="checkbox" name="SFCLML" id="SFCLML" fieldname="SFCLML" value="02" title="02" onclick="return false"/>
          2、排水方案图（标出拟接污水的市政道路及排水量）</label>
        <br />&nbsp;
       <label class="checkbox inline" >  
          <input type="checkbox" name="SFCLML" id="SFCLML" fieldname="SFCLML" value="03" title="03" onclick="return false"/>
          3、该项目的环评批复 </label>
        <br />
        二、改扩建项目<br />&nbsp;
       <label class="checkbox inline" >           
       <input type="checkbox" name="SFCLML" id="SFCLML" fieldname="SFCLML" value="11" title="11" onclick="return false"/>
          1、原项目的排水许可证</label>
        <br />&nbsp;
        <label class="checkbox inline" >  
          <input type="checkbox" name="SFCLML" id="SFCLML" fieldname="SFCLML" value="12" title="12" onclick="return false"/>
          2、排水方案草图（标出拟接厂区管网位置及排水量）</label>
        <br />&nbsp;
        <label class="checkbox inline" >  
          <input type="checkbox" name="SFCLML" id="SFCLML" fieldname="SFCLML" value="13" title="13" onclick="return false"/>
      3、该项目的环评批复 </label></td>
    </tr>
    <tr>
      <td height="77" colspan="4">本申请人对上述情况及所提供材料的真实性负责<br />
        <br />
      <div style="float:right">_________________&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
		 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		 		</div></td>
    </tr>
    <tr>
      <td height="47" colspan="4">注：申请人签章栏内，组织申请人有法定代表人签字并加盖公章。</td>
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