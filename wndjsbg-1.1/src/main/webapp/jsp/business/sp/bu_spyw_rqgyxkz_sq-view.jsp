<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>

<title>燃气供应许可申请表-维护</title>
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

//请求路径，对应后台RequestMapping						    
var controllername= "${pageContext.request.contextPath }/sp/buSpywRqgyxkzSqController/";
var controllerfile= "${pageContext.request.contextPath }/controller/fileController/";

var type ="<%=type%>";
var id ="<%=id%>";
var contextPath="${pageContext.request.contextPath }";
//页面初始化
$(function() {
	init();
	
	$("#downLoad").click(function() {
		download();
	});
	
	
});
//页面默认参数
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
				
				 $("#RQGYXKZ_SQ_UID").val(resultobj.RQGYXKZ_SQ_UID);

				$("#rqgyxkzForm").setHtmlValue(resultobj);
				$("#DWMC").html(resultobj.DWMC);
				$("#HZGQGM").html(resultobj.HZGQGM);
				$("#XKZBH").html(resultobj.XKZBH);
		}
		});
		
	}
}
function download(){
	
	$.ajax({
		url : controllername+"download?id="+$("#RQGYXKZ_SQ_UID").val(), 
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
        <div style="height:5px;"></div>
		<div class="B-small-from-table-autoConcise">
      
      	<span style="float:right">
      		<button id="btnShutdown" onclick="window.close();" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
	  		<button id="downLoad" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">打印</button>
      	</span><br/>
      <form method="post" id="rqgyxkzForm" >
     <h3 class="title" align="center">江苏省瓶装燃气供应许可申请表</h3>
  
      <table style="width: 750px; height: 20px;" border="0" align="center">
				<tr>
					<td align="left">
						单位名称(盖章):
						<span id="DWMC"></span>
					</td>
				</tr>
		</table>
       <table width="750" height="750" border="1"  align="center"  >
                  <input type="hidden" id="RQGYXKZ_SQ_UID" fieldname="RQGYXKZ_SQ_UID" name = "RQGYXKZ_SQ_UID"/>
                  <input type="hidden" id="YWLZ_UID" fieldname="YWLZ_UID" name = "YWLZ_UID"/>
                  	<tr>
						<td width="20%" height="40" align="center">供应站点名称</td>
			       	 	<td  width="80%" colspan="5" fieldname="GYZDMC">
			         	</td>
			        </tr>
			        <tr>
			        	<td width="20%" height="40" align="center">地       址</td>
			       	 	<td  width="80%" colspan="5" fieldname="DZ">
			         	</td>
			        </tr>
			         <tr>
			        	<td width="20%" height="40" align="center">建 立 时 间</td>
			       	 	<td  width="17%" fieldname="JLSJ" >			       	 	
			         	</td>
			         	<td width="15%" align="center">燃气种类</td>
			       	 	<td  width="18%"  fieldname="RQZL">
			         	</td>
			        	<td width="15%" align="center">邮  编</td>
			       	 	<td  width="15%" fieldname="YB">
			         	</td>
			        </tr>
			         <tr>
			        	<td width="20%" height="40" align="center">负  责  人</td>
			       	 	<td  width="17%"  fieldname="FZR">
			         	</td>
			         	<td width="15%" align="center">联系电话</td>
			       	 	<td  width="18%"  fieldname="LXDH">
			         	</td>
			         	<td width="15%" align="center">安全员</td>
			       	 	<td  width="15%" fieldname="AQY" >
			         	</td>
			        </tr>
			         <tr>
			        	<td width="20%" height="40" align="center">营业执照编号</td>
			       	 	<td  width="17%" fieldname="YYZZBH">
			         	</td>
			         	<td width="33%" align="center" colspan="2">申报供气规模(立方米)</td>
			       	 	<td  width="30%" colspan="2"  fieldname="SBGQGM">
			         	</td>
			         	
			        </tr>
			        <tr>
			        	<td width="20%" align="center">安全管理等制度情况</td>
			       	 	<td  width="80%" colspan="5" fieldname="AQGLZDQK">
			         	</td>
			        </tr> 
			        <tr>
			        	<td width="20%" align="center">属地燃气管理部门审查意见</td>
			       	 	<td  width="80%" colspan="5" fieldname="SDBMSCYJ">
			         	</td>
			        </tr> 
			        <tr>
			        	<td width="20%" align="center">新区燃气管理部门审查意见</td>
			       	 	<td  width="80%" colspan="5" fieldname="XQBMSCYJ">
			         	</td>
			        </tr> 
			        <tr>
			        	<td  align="left" height="40" colspan="6">
			        		核准供气规模：
			        		<span id="HZGQGM"></span>
			        	</td>
			        </tr>
			        <tr>
			        	<td  align="left" height="40" colspan="6">
			        		许可证编号：
			        		<span id="XKZBH"></span>
			        	</td>
			        </tr>
					
			</table>
      </form>
	</div>
   </div>
  </div>
  </div>
  <div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id = "queryXML">
         <input type="hidden" name="txtXML" id = "txtXML"><%--
         <input type="hidden" name="txtFilter"  order="desc" fieldname = "created_date" id = "txtFilter">
         --%><input type="hidden" name="resultXML" id = "resultXML">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
		
 	</FORM>
 </div>
</body>
<script>
</script>
</html>