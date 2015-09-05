<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>

<title>排水方案施工图审查-维护</title>
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

var controllername= "${pageContext.request.contextPath }/sp/buSpywPssgtscsqController/";
var controllerfile= "${pageContext.request.contextPath }/controller/fileController/";

var id ="<%=id%>";

$(function() {
	init();
	$("#downLoad").click(function() {
		download();
	});
	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
		if($("#buSpywPssgtscsqForm").validationButton())
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
		url : controllername+"download?id="+$("#PSSGTSCSQ_UID").val(), 
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			$("#resultXML").val(response.msg);
			//window.open(controllerfile+"download2?path="+response.msg,'文件下载打印');
<%--			var resultobj = defaultJson.dealResultJson(response.msg);--%>
			
			window.location.href = controllerfile+"download2?path="+response.msg;
	}
	});
	
}
function init(){
	$("#pubAlert").hide();
	if(id!="null"&&id!=""){
		 $("#QID").val(id);
		 $("#PSSGTSCSQ_UID").val(id);
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
				
				$("#buSpywPssgtscsqForm").setFormValues(resultobj);
				$("#buSpywPssgtscsqForm").setHtmlValue(resultobj);
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
    <div style="height: 5px;"></div>
    <div class="B-small-from-table-autoConcise">
      
      	<span style="float: right">
			<button id="btnShutdown" onclick="window.close();" class="btn" type="button" style="font-family: SimYou, Microsoft YaHei; font-weight: bold;">关闭</button>
		    <button id="downLoad" class="btn" type="button" style="font-family: SimYou, Microsoft YaHei; font-weight: bold;">打印</button>
		</span>
		<br/>
    <form method="post" id="buSpywPssgtscsqForm">
    <h4 class="title" align="center">无锡新区城市排水施工图审查申请表 </h4>
      <table style="width: 700px;height: 650px;"  border="1" align="center" class="cc-alert-table" >
      <input type="hidden" id="PSSGTSCSQ_UID" fieldname="PSSGTSCSQ_UID" name = "PSSGTSCSQ_UID" />
      <input type="hidden" id="YWLZ_UID" fieldname="YWLZ_UID" name = "YWLZ_UID"/>
  <tr>
    <td width="60" rowspan="9" style="line-height:15px">
    <p align="center">基</p>
    <p align="center">本</p>
    <p align="center">情</p>
    <p align="center">况</p></td>
    <td width="110" height="25" align="right">*单位名称</td>
    <td colspan="3" fieldname="DWMC">
  </tr>
  <tr>
    <td width="110" height="25" align="right">*单位地址</td>
    <td colspan="3" fieldname="DWDZ">
  </tr>
  <tr>
    <td width="110" height="25" align="right">*法定代表人</td>
    <td width="193" fieldname="FDDBR"> 
    <td width="110" align="right">*联系电话</td>
    <td width="193" fieldname="FDDBR_LXDH">
  </tr>
  <tr>
    <td width="110" height="25" align="right">*申请办理人</td>
    <td width="193" fieldname="SQBLR">
    <td width="110" align="right">*联系电话</td>
    <td width="193" fieldname="SQBLR_LXDH" >
  </tr>
  <tr>
    <td width="110" height="25" align="right">项目名称</td>
    <td colspan="3" fieldname="XMMC">
  </tr>
  <tr>
    <td height="25" align="right">建设地点</td>
    <td colspan="3" fieldname="JSDD" >
  </tr>
  <tr>
    <td height="25" align="right">用地面积</td>
    <td width="193" fieldname="YDMJ" >
    <td align="right">*行业分类</td>
    <td width="193" fieldname="HYFL" >
  </tr>
  <tr>
    <td height="25" align="right">*污水性质</td>
    <td>    &nbsp;&nbsp; 
    <label class="checkbox inline" >  
    	<input type="checkbox" name="WSXZ_SHWS" fieldname="WSXZ_SHWS" id="WSXZ_SHWS" value="0" onclick="return false"/>
   生活污水</label>
    <label class="checkbox inline" >  
   <input type="checkbox" name="WSZX_GYWS" fieldname="WSZX_GYWS" id="WSZX_GYWS" value="1" onclick="return false"/>
  工业用水 </label>
    </td>
    <td align="right">*污水量(m3/d)</td>
    <td fieldname="SHWS_L">
</td>
  </tr>
  <tr>
    <td height="25" align="right">*用水量(m3/d)</td>
    <td  fieldname="YSL">
      </td>
    <td align="right">主要污染物</td>
    <td fieldname="ZYWRW" >
  </tr>
  <tr>
    <td height="160" style="line-height:10px">
    <p align="center">所</p>
    <p align="center">附</p>
    <p align="center">材</p>
    <p align="center">料</p>
    <p align="center">目</p>
    <p align="center">录</p></td>
    <td colspan="4">&nbsp;
      <label class="checkbox inline" >  
        <input type="checkbox" name="SFCLML" id="SFCLML" fieldname="SFCLML" value="01" onclick="return false"/>
       
      1.规划定点图</label><br/>&nbsp;
      <label class="checkbox inline" >  
        <input type="checkbox" name="SFCLML" id="SFCLML" fieldname="SFCLML" value="02" onclick="return false"/>
       
      2.规划部门的管线综合方案审查意见(仅三产项目)</label><br/>&nbsp;
     <label class="checkbox inline" >  
         <input type="checkbox" name="SFCLML" id="SFCLML" fieldname="SFCLML" value="03" onclick="return false"/>
        
      3.室外排水、污水、雨水管平面图(蓝图3份)</label><br/>&nbsp;
      <label class="checkbox inline" >  
         <input type="checkbox" name="SFCLML" id="SFCLML" fieldname="SFCLML" value="04" onclick="return false"/>
        
      4.管线综合图(电子图)    </label><br/>&nbsp;
      <label class="checkbox inline" >   
        <input type="checkbox" name="SFCLML" id="SFCLML" fieldname="SFCLML" value="05" onclick="return false"/>
       
      5.建筑水施部分施工图(电子图)    </label><br/>&nbsp;
     <label class="checkbox inline" >  
       <input type="checkbox" name="SFCLML" id="SFCLML" fieldname="SFCLML" value="06" onclick="return false"/>
       
        6.该项目的环评批复
      </label><br/>
    </td>
  </tr>
  <tr>
    <td height="100" style="line-height:10px">
    <p align="center">申</p>
    <p align="center">请</p>
    <p align="center">人</p>
    <p align="center">签</p>
    <p align="center">章</p></td>
    <td colspan="4">本申请人对上述情况及所提供材料的真实性负责。<br /><br /><br /><br /><br />
    <div style="float:right">
    _________________&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
		 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		 		
    </div></td>
  </tr>
  <tr>
    <td height="54" style="line-height:10px">
    <p align="center">备</p>
    <p align="center">注</p></td>
    <td colspan="4">注：申请人签章栏内，组织申请人由法定代表人签字并加盖公章。</td>
  </tr>
</table>
 <table style="width: 700px; height: 60px;" border="0" align="center">
     <tr><td>
             基本情况栏中注*为必填项目。
     </td></tr>
     <tr><td>
              行业分类栏中可选填机关事业单位、工业企业、新村小区、公用设施、服务业-酒店餐饮、
     </td></tr>
     <tr><td>
             服务业-浴场浴室、服务业-汽车服务、服务业-美容美发、服务业-干洗、其他服务业。
     </td></tr>
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
         <input type="hidden" name="txtFilter"  order="desc" fieldname = "CREATED_DATE" id = "txtFilter">
         <input type="hidden" name="resultXML" id = "resultXML">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
		
 	</FORM>
 </div>
</body>
<script>
</script>
</html>