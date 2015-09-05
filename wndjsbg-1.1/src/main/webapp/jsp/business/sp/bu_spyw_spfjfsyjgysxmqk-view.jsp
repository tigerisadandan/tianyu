<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>

<title>墙改基金征收及返退审批事项-维护</title>
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
var controllername= "${pageContext.request.contextPath }/sp/bu_spyw_spfjfsyjgysxmqkController/";
var controllernameMX= "${pageContext.request.contextPath }/sp/bu_spyw_spfjfsyjgysxmqk_mxController/";
var controllerfile= "${pageContext.request.contextPath }/controller/fileController/";
var controllernameJZHZ= "${pageContext.request.contextPath }/sp/bu_spyw_spfjfsyjgysbadtjzhzController/";
var controllernameDT= "${pageContext.request.contextPath }/sp/bu_spyw_spfjfsyjgysbadtjzhz_dtController/";
 
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
				 $("#QID2").val(resultobj.SPFJFSYJGYSXMQK_UID);
				 $("#SPFJFSYJGYSXMQK_UID").val(resultobj.SPFJFSYJGYSXMQK_UID);

				$("#spfjfsyjgysxmqkForm").setHtmlValue(resultobj);
				
		}
		});
		builderMxList();
		setFormValuesJZHZ()
	}
}
function setFormValuesJZHZ(){

	var data = combineQuery.getQueryCombineData(queryForm,frmPost);
	var data1 = {
		msg : data
	};	
	$.ajax({

		url : controllernameJZHZ+"query",
		data : data1,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			$("#resultXML").val(response.msg);
			if(response.msg!=""&&response.msg!="0"){
				
				var resultobj = defaultJson.dealResultJson(response.msg);
				 $("#QID3").val(resultobj.SPFJFSYJGYSBADTJZHZ_UID);
				 $("#SPFJFSYJGYSBADTJZHZ_UID").val(resultobj.SPFJFSYJGYSBADTJZHZ_UID);
		
				//$("#spfjfsyjgysxmqkForm").setFormValues(resultobj);
				$("#spfjfsyjgysbadtjzhzForm").setHtmlValue(resultobj);
				$("#DTDWMC").html(resultobj.DWMC);
				$("#TBRQ").html(resultobj.TBRQ);
				builderDtList();
				
			}
	}
	});

}
function builderMxList(){
	
	var data = combineQuery.getQueryCombineData(queryForm2,frmPost,sqmx); 
	try{
	//调用ajax插入
	defaultJson.doQueryJsonList(controllernameMX+"query",data,sqmx,null,true);
	}catch(e){
	}

	
	var rows = $("tbody tr" ,$("#sqmx"));
	if(rows.size()==0){
		$("tbody" ,$("#sqmx")).append("<tr><td colspan=\"8\" style=\"height: 1px;\" align=\"center\">暂无记录</td></tr>");
	}
}
function builderDtList(){
	var data = combineQuery.getQueryCombineData(queryForm3,frmPost,jzhz); 
	try{
	//调用ajax插入
	defaultJson.doQueryJsonList(controllernameDT+"query",data,jzhz,null,true);
	}catch(e){
	}

	doSum();
		
		var rows = $("tbody tr" ,$("#jzhz"));
		if(rows.size()==0){
			$("tbody" ,$("#jzhz")).append("<tr><td colspan=\"5\" style=\"height: 1px;\" align=\"center\">暂无记录</td></tr>");
		}
}
function doSum(){
	var a = 0;
	$("tbody tr",$("#jzhz")).each(function(){
		var val = $(this).find("td").eq(1).html()
		if(val){
			a+=parseFloat(val);
		}
	})
	var b = 0;
	$("tbody tr",$("#jzhz")).each(function(){
		var val = $(this).find("td").eq(2).html()
		if(val){
			b+=parseFloat(val);
		}
	})
	var c = 0;
	$("tbody tr",$("#jzhz")).each(function(){
		var val = $(this).find("td").eq(3).html()
		if(val){
			c+=parseFloat(val);
		}
	})
	var d = 0;
	$("tbody tr",$("#jzhz")).each(function(){
		var val = $(this).find("td").eq(4).html()
		if(val){
			d+=parseFloat(val);
		}
	})
	$("#HJJGMJ").html(a);
	$("#HJHS").html(b);
	$("#HJCS").html(c);
	$("#HJZGD").html(d);
}

function download(){
	$.ajax({
		url : controllername+"download?id="+$("#SPFJFSYJGYSXMQK_UID").val()+"&tid="+$("#SPFJFSYJGYSBADTJZHZ_UID").val(),
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
function showIntruduce(){

	window.open("${pageContext.request.contextPath}/jsp/business/sp/spfjfsyjgys-introduce.jsp","编制说明","height=600, width=800");
	
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
      <form method="post" id="queryForm2"  >
      <table class="B-table" width="100%">
      <!--可以再此处加入hidden域作为过滤条件 -->
      	<TR  style="display:none;">
	        <TD class="right-border bottom-border">
	        	<INPUT type="text" class="span12" kind="text" id="QID2" name="ID"  fieldname="SPFJFSYJGYSXMQK_UID" value="" operation="="/>
	        </TD>
			<TD class="right-border bottom-border">
			</TD>
        </TR>
      </table>
      </form>
       <form method="post" id="queryForm3"  >
      <table class="B-table" width="100%">
      <!--可以再此处加入hidden域作为过滤条件 -->
      	<TR  style="display:none;">
	        <TD class="right-border bottom-border">
	        	<INPUT type="text" class="span12" kind="text" id="QID3" name="ID"  fieldname="SPFJFSYJGYSBADTJZHZ_UID" value="" operation="="/>
	        </TD>
			<TD class="right-border bottom-border">
			</TD>
        </TR>
      </table>
      </form>
        <div style="height:5px;"></div>
		<div class="B-small-from-table-autoConcise">
      
      	<span style="float:right">
            <button id="btnShutDown" onclick="window.close();" class="btn" type="button" style="font-family: SimYou, Microsoft YaHei; font-weight: bold;">关闭</button>
      	    <button id="downLoad" class="btn" type="button" style="font-family: SimYou, Microsoft YaHei; font-weight: bold;">打印</button>
      	</span>
      	<br/>
 		 <form method="post" id="spfjfsyjgysxmqkForm" >
     <h3 class="title" align="center">无锡新区商品房交付使用竣工验收项目情况表</h3>
    	<table style="width: 750px; height: 20px;" border="0" align="center">
				<tr>
					<td align="right">
						<a href='javascript:void(0);' onclick="showIntruduce()">编制说明</a>
					</td>
				</tr>
		</table>
       <table width="750" height="750" border="1"  align="center"  >
                  <input type="hidden" id="SPFJFSYJGYSXMQK_UID" fieldname="SPFJFSYJGYSXMQK_UID" name = "SPFJFSYJGYSXMQK_UID"/>
                  <input type="hidden" id="YWLZ_UID" fieldname="YWLZ_UID" name = "YWLZ_UID"/>
                  	<tr>
                  		<td width="5%" rowspan="5" style="line-height: 25px">
									<font size="5">	
										<p align="center">单	</p>
										<p align="center">位</p>
										<p align="center">情</p>
										<p align="center">况</p>
									</font>	
									</td>
						<td width="20%" height="40" align="center">单位名称</td>
			       	 	<td  width="70%" fieldname="DWMC">
			         	</td>
			        </tr>
			        <tr>
			        	<td width="20%" height="40" align="center">负责人</td>
			       	 	<td  width="70%" fieldname="FZR">
			         	</td>
			        </tr>
			         <tr>
			        	<td width="20%" height="40" align="center">联系人</td>
			       	 	<td  width="70%"  fieldname="LXR">
			         	</td>
			        </tr>
			         <tr>
			        	<td width="20%" height="40" align="center">联系电话</td>
			       	 	<td  width="70%" fieldname="LXDH">
			         	</td>
			        </tr>
			         <tr>
			        	<td width="20%" height="40" align="center">联系地址</td>
			       	 	<td  width="70%" fieldname="LXDZ">
			         	</td>
			        </tr>
			         <tr>
			         	<td width="5%" rowspan="4" style="line-height: 25px">
			         		<font size="5">	
										<p align="center">项</p>
										<p align="center">目</p>
										<p align="center">情</p>
										<p align="center">况</p>
							</font>		
						</td>
			        	<td width="20%" height="40" align="center">项目名称</td>
			       	 	<td  width="70%"  fieldname="XMMC">
			         	</td>
			        </tr>
			        <tr>
			        	<td width="20%" height="40" align="center">项目性质</td>
			       	 	<td  width="70%" fieldname="XMXZ">
			         	</td>
			        </tr> 
			        <tr>
			        	<td width="20%" height="40" align="center">项目地址</td>
			       	 	<td  width="70%" fieldname="XMDZ">
			         	</td>
			        </tr> 
			        <tr>
			        	<td width="20%" height="40" align="center">所在区</td>
			       	 	<td  width="70%" fieldname="SZQ">
			         	</td>
			        </tr> 
			         <tr>
			         	<td width="5%" rowspan="6" style="line-height: 25px">
			         		<font size="5">	
										<p align="center">建</p>
										<p align="center">设</p>
										<p align="center">情</p>
										<p align="center">况</p>
							</font>
						</td>
			        	<td width="20%" height="40" align="center">开工日期</td>
			       	 	<td  width="70%" fieldname="KGRQ">
			         	</td>
			        </tr>
			        <tr>
			        	<td width="20%" height="40" align="center">竣工日期</td>
			       	 	<td  width="70%" fieldname="JGRQ">
			         	</td>
			        </tr>
					 <tr>
			        	<td width="20%" height="40" align="center">规划合格证面积</td>
			       	 	<td  width="70%" fieldname="GHHGZMJ">
			         	</td>
			        </tr>  
			        <tr>
			        	<td width="20%" height="40" align="center">幢数</td>
			       	 	<td  width="70%" fieldname="DS">
			         	</td>
			        </tr> 
			         <tr>
			        	<td width="20%" height="40" align="center">其中住宅面积</td>
			       	 	<td  width="70%" fieldname="QZZZMJ" >
			         	</td>
			        </tr> 
			         <tr>
			        	<td width="20%" height="40" align="center">其中公建配套面积</td>
			       	 	<td  width="70%" fieldname="QZGJPTMJ" >
			         	</td>
			        </tr>
			       
			         <tr>
			         	<td width="10%" height="40" style="line-height: 25px"></td> 
			        	<td width="20%" align="center">其中非住宅面积</td>
			       	 	<td  width="70%" fieldname="QZFZZMJ" >
			         	</td>
			        </tr> 
			       <tr >
						<td align="center" colspan="6" cellSpacing=0 cellPadding=0 >
							<div class="overFlowX" >
							<table border="1" class="table-hover table-activeTd B-table" noprint="true" height="100%" width="100%"   id="sqmx" noPage="true" type="single">
								<thead>
								<tr>
									<th height="30" fieldname="DH" width="10%" colindex=0 tdalign="center" >幢号</th>
									<th height="30" fieldname="MPH" width="12%" colindex=0 tdalign="center" >门牌号</th>
									<th height="30" fieldname="CS" width="8%" colindex=0 tdalign="center" >层数</th>
									<th height="30" fieldname="MPFMJ" width="17%" colindex=0 tdalign="center" >毛坯房面积(㎡)</th>
									<th height="30" fieldname="MPCS" width="8%" colindex=0 tdalign="center" >套数</th>
									<th height="30" fieldname="JZFMJ" width="17%" colindex=0 tdalign="center" >精装房面积(㎡)</th>
									<th height="30" fieldname="JZTS" width="8%" colindex=0 tdalign="center" >套数</th>
									<th height="30" fieldname="BZ" width="16%" colindex=0 tdalign="center" >备注</th>
								</tr>
								</thead>
								<tbody></tbody>
							</table>
							</div>
						</td>
					</tr>
			</table>
      </form>
      <form method="post" id="spfjfsyjgysbadtjzhzForm" >
     		<h3 class="title" align="center">竣工项目综合验收备案单体建筑汇总</h3>
		    <table style="width: 750px; height: 20px;" border="0" align="center">
				<tr>
					<td align="left" >
						单位名称(盖章):
						<span id="DTDWMC"></span>
					</td>
					<td align="right">
						填表日期:
						<span id="TBRQ"></span>
					</td>
				</tr>
			</table>
	       <table width="750" height="180" border="1"  align="center"  >
	        	  <input type="hidden" id="SPFJFSYJGYSBADTJZHZ_UID" fieldname="SPFJFSYJGYSBADTJZHZ_UID" name = "SPFJFSYJGYSBADTJZHZ_UID"/>
                  <input type="hidden" id="YWLZ_UID1" fieldname="YWLZ_UID" name = "YWLZ_UID"/>
	       		  <tr >
						<td align="center" colspan="6" cellSpacing=0 cellPadding=0 >
							<div class="overFlowX" >
							<table border="1" class="table-hover table-activeTd B-table" noprint="true" height="100%" width="100%"   id="jzhz" noPage="true" type="single">
								<thead>
								<tr>
									<th height="30" fieldname="JGDTDH" id="JGDTDH" width="20%" colindex=0 tdalign="center" >竣工单体栋号</th>
									<th height="30" fieldname="JGMJ" id="JGMJ" width="20%" colindex=0 tdalign="center" >竣工面积</th>
									<th height="30" fieldname="HS" id="HS" width="20%" colindex=0 tdalign="center" >户数</th>
									<th height="30" fieldname="CS" id="CS" width="20%" colindex=0 tdalign="center" >层数</th>
									<th height="30" fieldname="ZGD" id="ZGD" width="20%" colindex=0 tdalign="center" >总高度</th>
								</tr>
								</thead>
								<tbody></tbody>
							</table>
							</div>
						</td>
					</tr>
	       			<tr height="40">
	       				<td width="20%" align="center">合计</td>
			       	 	<td  width="20%" id="HJJGMJ" align="center">
			         	</td>
			         	<td  width="20%" id="HJHS" align="center">
			         	</td>
			         	<td  width="20%" id="HJCS" align="center">
			         	</td>
			         	<td  width="20%" id="HJZGD" align="center">
			         	</td>
	       			</tr>
	       			<tr height="100">
	       				<td width="100%" colspan="5" align="left" valign="top">备注:<br/>
	       					<p fieldname="DESCRIBE">
					    	</p>
					    </td>
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