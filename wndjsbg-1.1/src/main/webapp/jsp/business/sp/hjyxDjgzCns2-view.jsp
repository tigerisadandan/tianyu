<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>

<title>环境影响登记告知承诺—-区域开发及其他类——详细信息</title>
<%
String type=request.getParameter("type");
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

var controllername= "${pageContext.request.contextPath }/sp/buSpywHjyxdjgzcns2Controller/";
var controllerfile= "${pageContext.request.contextPath }/controller/fileController/";
var uploadController= "${pageContext.request.contextPath }/fileUploadController/";


var type ="<%=type%>";
var id ="<%=id%>";

$(function() {
	init();

	$("#downLoad").click(function() {
		download();
	});
	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
		if($("#buSpywHjyxdjgzcnsForm").validationButton())
		{
		    //生成json串
		    var data = Form2Json.formToJSON(bU_Spyw_JsgcsgxkzSqForm);
		  //组成保存json串格式
		    var data1 = defaultJson.packSaveJson(data);
		    defaultJson.doInsertJson(controllername + "insert", data1);

		   
		}else{
			requireFormMsg();
		  	return;
		}
	});

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
				$("#HJYXDJGZCNS_UID").val(resultobj.HJYXDJGZCNS_UID);

				$("#CQCS").html(resultobj.CQCS);
				$("#TQSM").html(resultobj.TQSM);
				$("#buSpywHjyxdjgzcnsForm").setFormValues(resultobj);
				$("#buSpywHjyxdjgzcnsForm").setHtmlValue(resultobj);
				readFile();
				show();
		}
		});
	
	}
}
function readFile(){
	
	$.ajax({
		url : controllername+"queryReadFile?id="+$("#HJYXDJGZCNS_UID").val(),
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			//$("#resultXML").val(response.msg);
			//var resultobj = eval('(' + response.msg + ')');
			//var resultobj = convertJson.string2json1(response.msg);
			//var resultmsgobj = convertJson.string2json1(response.msg);
			//var resultobj=subresultmsgobj = resultmsgobj.response.data;
			if(response.msg!=null&&response.msg!="0"&&response.msg!=""){//如果数据库中有该ID
				//console.log(response.msg);写日志  
				var resultobj = eval('(' + response.msg + ')')
				$.each(resultobj,function(){
					//$("#syt").val(this.FILEPATH);//获取返回来的该人员的FILEPATH
					var root=this.FILEROOT;
					var name=this.FILENAME;
					var filename=root+"/"+name;
					//alert(filename);
					$("#syt").val(filename);
					//alert($("#syt").val());
					$("#QID2").val(this.AT_FILEUPLOAD_UID);
					//alert($("#syt").val());
					//alert($("#QID2").val());
					})
				}			
			//$("#syt").attr("src",resultobj);
		}
	});
}
function show(){
	if($("#QID2").val()!=""&&$("#QID2").val()!="0"){
	$.ajax({
		url : uploadController+"doPreview?fileid="+$("#QID2").val(),
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			$("#resultXML").val(response.msg);
			//alert(response.msg);
	
			$("#syt").attr("src",response.msg);
		}
	});
	}
}
function download(){
	
	$.ajax({
		url : controllername+"download?id="+$("#HJYXDJGZCNS_UID").val(), 
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
</script>    
</head>
<body>

<div class="container-fluid">
	</p>	
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
   <form method="post" id="queryForm2"  >
      <table class="B-table" width="100%">
      <!--可以再此处加入hidden域作为过滤条件 -->
      	<TR  style="display:none;">
	        <TD class="right-border bottom-border">
	        	<INPUT type="text" class="span12" kind="text" id="QID2" name="ID"  fieldname="AT_FILEUPLOAD_UID" value="" operation="="/>
	        </TD>
			<TD class="right-border bottom-border">
			</TD>
        </TR>
      </table>
      </form>
   <div style="height:5px;"></div>
	<div class="B-small-from-table-autoConcise">
       <span class="pull-right">
      		<button id="btnShutdown" onclick="window.close();" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
      	    <button id="downLoad" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">打印</button>
      	</span>
      	<br/>
      
     <form method="post" id="buSpywHjyxdjgzcnsForm"  >
     <h4 class="title" align="center">无锡市新区建设项目环境影响评价申请表（区域开发及其他类）</h4>
     <table width="750" height="800" border="1" align="center" >
         <input type="hidden" id="HJYXDJGZCNS_UID" fieldname="HJYXDJGZCNS_UID" name = "HJYXDJGZCNS_UID"/>
         <input type="hidden" id="YWLZ_UID" fieldname="YWLZ_UID" name = "YWLZ_UID"/>
    <tr>
      <td height="25" colspan="6" align="left"><strong>（一）建设项目基本信息</strong></td>
    </tr>
    <tr>
      <td width="145" height="25" align="center">项目名称</td>
      <td colspan="5" fieldname="XMMC" ></td>
    </tr>
    <tr>
      <td height="25" align="center">建设单位</td>
      <td colspan="5"  fieldname="JSDW" ></td>
    </tr>
    <tr>
      <td height="25" align="center">法人代表</td>
      <td colspan="2" fieldname="FRDB" >
      </td>
      <td width="145" align="center">联系人</td>
      <td colspan="2" fieldname="LXR" >
      </td>
    </tr>
    <tr>
      <td height="25" align="center">联系电话</td>
      <td width="164"  fieldname="LXDH" >
      </td>
      <td width="66" align="center">传真</td>
      <td fieldname="CZ" >
      </td>
      <td width="83" align="center">邮政编码</td>
      <td width="107"  fieldname="YZBM" >
      </td>
    </tr>
    <tr>
      <td height="25" align="center">通讯地点</td>
      <td colspan="5" fieldname="TXL" >
      </td>
    </tr>
    <tr>
      <td height="25" align="center">建设地址</td>
      <td colspan="5" fieldname="JSDD">
      </td>
    </tr>
   <tr>
      <td height="25" align="center">占地面积</td>
      <td colspan="2" fieldname="ZDMJ">
      </td>
      <td align="center">绿化面积</td>
      <td colspan="2" fieldname="RHMJ" >
     </td>
    </tr>
    <tr>
      <td height="25" align="center">总投资</td>
      <td colspan="2" fieldname="ZTZ" >
      </td>
      <td align="center">环保投资</td>
      <td colspan="2" fieldname="HBTZ">
      </td>
    </tr>
    <tr>
      <td height="25" align="center">预期投产日期</td>
      <td colspan="2"  fieldname="YQTCRQ">
      </td>
      <td align="center">预计工作日</td>
      <td colspan="2" fieldname="YJGZR" >
     </td>
    </tr>
    <tr>
      <td height="25" colspan="6" align="left"><strong>（二）项目拟选建设地址周围环境（如非占用整栋厂房，须注明上下层企业情况）及周边300米范围主要敏感目标（居民点、河流等）分布状况示意图</strong> 。</td>
    </tr>
    <tr>
      <td height="25" colspan="6" align="left">示意图：
        <img id="syt" src="">
        </td>
    </tr>
    <tr>
    <td height="25" colspan="6" align="left"><p><strong>（三） 土地用途</strong></p></td>
  </tr>
  <tr>
    <td height="25" align="center">用地性质</td>
    <td height="25" colspan="5" align="left" fieldname="YDXZ" ></td>
  </tr>
  <tr>
    <td height="25" align="center">建设项目内容</td>
    <td height="25" colspan="5" align="left" fieldname="JSXMNR"></td>
  </tr>
  <tr>
    <td height="25" align="center">建设规模</td>
    <td height="25" colspan="5" align="left" fieldname="JSGM"></td>
  </tr>
  <tr>
    <td height="25" align="center">生产规模</td>
    <td height="25" colspan="5" align="left" fieldname="SCGM"></td>
  </tr>
  <tr>
    <td height="60" align="center">其他情况（水、电、气用量及运输量）</td>
    <td height="25" colspan="5" align="left" fieldname="QTQK"></td>
  </tr>
  <tr>
    <td height="25" align="center">拟开工日期</td>
    <td height="25" colspan="2" align="left" fieldname="NKGRQ"></td>
    <td height="25" align="center">计划建设期</td>
    <td height="25" colspan="2" align="left" fieldname="JHJSQ"></td>
  </tr>
  <tr>
    <td height="25" colspan="6" align="left"><strong>（四）施工期和运营期主要环境问题和拟采取的环保措施 </strong></td>
  </tr>
  <tr>
    <td height="40" colspan="6" align="left" fieldname="CQCS">
    </td>
  </tr>
  <tr>
    <td height="25" colspan="6" align="left"><strong>（五）其它说明 </strong></td>
  </tr>
  <tr>
    <td height="40" colspan="6" align="left" fieldname="TQSM">
    </td>
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