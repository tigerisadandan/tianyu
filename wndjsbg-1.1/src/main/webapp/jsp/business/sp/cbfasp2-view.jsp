<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>

<title>无锡新区监理工程直接发包初步方案审批表</title>
<%
String id=(String)request.getAttribute("id");
String ywlz=(String)request.getAttribute("ywlz");
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

var controllername= "${pageContext.request.contextPath }/sp/buSpywCbfaspController/";
var controllernameMx= "${pageContext.request.contextPath }/sp/buSpywCbfaspFbfaController/";
var controllerfile= "${pageContext.request.contextPath }/controller/fileController/";

var id ="<%=id%>";
var ywlz="<%=ywlz%>";
var contextPath="${pageContext.request.contextPath }";
$(function() {
	init();
	$("#downLoad").click(function() {
		download();
	});
	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
		if($("#demoForm").validationButton())
		{
		    //生成json串
		    var data = Form2Json.formToJSON(demoForm);
		  //组成保存json串格式
		    var data1 = defaultJson.packSaveJson(data);
		    defaultJson.doInsertJson(controllername + "insert", data1);

		   
		}else{
			requireFormMsg();
		  	return;
		}
	});

});
function download(){
	var sg="jl";
	$.ajax({
		url : controllername+"download?id="+$("#CBFASP_UID").val()+"&ywlz="+ywlz+"&ty="+sg, 
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			$("#resultXML").val(response.msg);
			//window.open(controllerfile+"download2?path="+response.msg,'文件下载打印');
<%--			var resultobj = defaultJson.dealResultJson(response.msg);--%>
<%--		var showHtml= "<a id='print' href="+controllerfile+"download2?path="+response.msg+" ></a>";--%>
			window.location.href = controllerfile+"download2?path="+response.msg;
			//window.location.href = window.location.href;
	}
	});
	
}
function init(){
	$("#pubAlert").hide();

	$("#QLX").val("jl")
	if(ywlz!="null"&&ywlz!=""){
		 $("#QID").val(ywlz);
		 $("#downLoad").hide();
		 setForm();
		 download();
		//window.close();
		}
	if(id!="null"&&id!=""){
		 $("#QID").val(id);
		 setForm();
	}
	if($("#CBFASP_UID").val()!=""){
		//builderMxList();
	}
}
function setForm(){
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
			if(response.msg!=0){
			resultobj = defaultJson.dealResultJson(response.msg);
			$("#QID1").val(resultobj.CBFASP_UID);
			$("#CBFASP_UID").val(resultobj.CBFASP_UID);
			$("#demoForm").setFormValues(resultobj);
			$("#demoForm").setHtmlValue(resultobj);
			$("#BB_CODE").html(resultobj.BB_CODE);
			$("#TZGC_GYTZ").html(resultobj.TZGC_GYTZ);
			$("#TZGC_WZTZ").html(resultobj.TZGC_WZTZ);
			$("#TZGC_SYTZ").html(resultobj.TZGC_SYTZ);
			queryDt(resultobj.DT_IDS);
			}
	}
	});
	
}

function queryDt(dtIds){
	$("#DT_IDS").val(dtIds);
	$.ajax({
		url :"${pageContext.request.contextPath }/units/projectsUnitsController?querybyIds&IDS="+dtIds,
		data : null,
		cache : false,
		async : false, 
		dataType : "json",
		type : 'post',
		success : function(response) {
			if(response.msg!='0'){
				var resultmsgobj = convertJson.string2json1(response.msg);
		        var  obj = resultmsgobj.response.data;
		        var html = "";  //清空
		        for(var i=0;i<resultmsgobj.response.data.length;i++){
		        	html += "<tr > "+
						"<td align=\"left\">"+obj[i].UNITS_NAME+"</td>"+
						"<td align=\"right\">"+obj[i].GCZJ+"</td>"+
						"<td align=\"right\">"+obj[i].JZMJ+"</td>"+
						"<td align=\"center\">"+obj[i].YCCS+"</td>"+
						"<td align=\"center\">"+obj[i].CD+"</td>"+
						"<td align=\"center\">"+obj[i].YT+"</td>"+
						"<td align=\"center\">"+obj[i].JGLX+"</td>"+
					"</tr>";
		        }
		        $("#DTTB").html(html);
			}
		}
	});
	var rows = $("tbody tr" ,$("#sqmx"));
	if(rows.size()==0){
		$("tbody" ,$("#sqmx")).append("<tr><td colspan=\"7\" style=\"height: 1px;\" align=\"center\">暂无记录</td></tr>");
	}
}
function builderMxList(){
	var data = combineQuery.getQueryCombineData(queryForm1,frmPost,sqmx); 
	try{
	//调用ajax插入
	defaultJson.doQueryJsonList(controllernameMx+"query",data,sqmx,null,true);

	}catch(e){
	}
	var rows = $("tbody tr" ,$("#sqmx"));
	if(rows.size()==0){
		$("tbody" ,$("#sqmx")).append("<tr><td colspan=\"5\" style=\"height: 1px;\" align=\"center\">暂无记录</td></tr>");
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
	       		<INPUT type="text" class="span12" kind="text" id="QLX" name="LX" fieldname="LX" value="" operation="=" />
	       		 </TD>
				<TD class="right-border bottom-border">
				</TD>
        	</TR>
      		</table>
     	 </form>
 	<form method="post" id="queryForm1">
		<table class="B-table" width="100%">
		<!--可以再此处加入hidden域作为过滤条件 -->
		<TR style="display: none;">
		<TD class="right-border bottom-border">
			<INPUT type="text" class="span12" kind="text" id="QID1" name="ID" fieldname="CBFASP_UID" value="" operation="=" />
		</TD>
		<TD class="right-border bottom-border">
		</TD>
		</TR>
   		</table>
	</form>
 	
    	<div class="B-small-from-table-autoConcise">
      		<h4 class="title">
				<span class="pull-right">
					<button id="btnShutdown" onclick="window.close();" class="btn" type="button" style="font-family: SimYou, Microsoft YaHei; font-weight: bold;">关闭</button>
		    		<button id="downLoad" class="btn" type="button" style="font-family: SimYou, Microsoft YaHei; font-weight: bold;">打印</button>				</span>    			
      		</h4>
     		<form method="post" id="demoForm">
     		<h4 class="title" align="center">无锡新区监理工程直接发包初步方案审批表</h4>
      			<table style="width: 700px; height: 650px;" border="1" align="center" >
					<input type="hidden" id="CBFASP_UID" fieldname="CBFASP_UID" name = "CBFASP_UID" />
      				<input type="hidden" id="YWLZ_UID" fieldname="YWLZ_UID" name = "YWLZ_UID"/>
					<tr>
						<td width="150" height="25" align="right">项目审批(备案)部门</td>
						<td width="200" fieldname="XMSPBABM">
						</td>
						<td  width="150"  height="25" align="right">项目审批(备案)文号</td>
						<td  width="200"  fieldname="XMSPBAWH">
						</td>	
					</tr>
					<tr>
						<td  height="25" align="right">项目审批（备案）标题</td>
					 	<td  colspan="3" fieldname="XMSPBABT">
					 	</td>
					</tr>
					<tr>							
				        <td  height="25" align="right">发包人名称</td>
	        			<td  colspan="3" fieldname="FBRMC">
	       				</td>
	       			</tr>
	       			<tr>
						<td  height="25" align="right">单位性质</td>
						<td  fieldname="DWXZ">
						</td>
						<td  height="25" align="right">报建日期</td>
						<td  fieldname="DATE_BJRQ">
						</td>
					</tr>
					<tr>							
						<td  height="25" align="right">工程名称</td>
						<td  colspan="3" fieldname="GCMC">
						</td>						
					</tr>
					<tr>							
						<td  height="25" align="right">投资构成</td>
						<td width="80%" colspan="3">
										&nbsp;&nbsp;国有投资	<span id="TZGC_GYTZ"></span>%
										&nbsp;&nbsp;&nbsp;&nbsp;外资投资<span id="TZGC_WZTZ"></span>%
										&nbsp;&nbsp;&nbsp;&nbsp;私有投资<span id="TZGC_SYTZ"></span>%
									</td>					
					</tr>
					<tr>
						<td  height="25" align="right">施工内容名称</td>
						<td  fieldname="GONGCHENG_NAME">
						</td>
						<td  height="25" align="right">施工内容</td>
						<td  fieldname="NEIRONG_SV">
						</td>			
					</tr>
					<tr>
						<td  height="25" align="right">承包性质</td>
						<td  fieldname="CB_XINGZHI_SV">
						</td>
						<td  height="25" align="right">是否市政工程</td>
						<td  fieldname="SFSZGC_SV">
						</td>			
					</tr>
					<tr>
						<td  height="25" align="right">发包方式</td>
						<td  fieldname="BID_TYPE_SV">
						</td>
						<td  height="25" align="right">重量(吨)</td>
						<td  fieldname="ZHONGLIANG">
						</td>			
					</tr>
					<tr>
						<td  height="25" align="right">高度(米)</td>
						<td  fieldname="GAODU">
						</td>
						<td  height="25" align="right">深度(米)</td>
						<td  fieldname="SHENDU">
						</td>			
					</tr>
					<tr>
						<td  height="25" align="right">资金来源</td>
						<td  fieldname="ZJLY">
						</td>
						<td  height="25" align="right">资金落实情况</td>
						<td  fieldname="ZJSHQK">
						</td>			
					</tr>
 					<tr>
						<td  height="25" align="right">建设地点</td>
						<td  fieldname="JSTD">
						</td>							
						<td  height="25" align="right">建筑面积(平方米)</td>
						<td  fieldname="ZJMJ">
						</td>
												
					</tr>			
					<tr>
						<td  height="25" align="right">结构类型</td>
						<td  fieldname="JGLX">
						</td>
						<td  height="25" align="right">层数</td>
						<td  fieldname="CS">
						</td>
					</tr>
					<tr>
						<td  height="25" align="right">最大单跨跨度(米)</td>
						<td  fieldname="ZDDKKD">
						</td>
						<td  height="25" align="right">本期项目总规模（米/平方米）</td>
						<td  fieldname="ZJGM">
						</td>
					</tr>
					<tr>
						<td  height="25" align="right">投资总额（万元）</td>
						<td fieldname="TZZE">
						</td>
						<td  height="25" align="right">标段合同价（万元）</td>
						<td  fieldname="BDHTJ">
						</td>
					</tr>
					<tr>
						<td  height="25" align="right">计划开工日期</td>
						<td  fieldname="DATE_JHKG">
						</td>
						<td  height="25" align="right">计划竣工日期</td>
						<td  fieldname="DATE_JHJG">
						</td>
					</tr>
					<tr>
						<td  height="25" align="right">发包内容</td>
						<td   fieldname="FBNR">
						</td>
						<td  height="25" align="right">计划竣工日期</td>
						<td  fieldname="WG_DATE">
						</td>
					</tr>
					<tr>
						<td height="25" align="center" colspan="4">单位工程</td>
					</tr>
					<tr >
						<td align="center" colspan="4" >
							<table width="100%" id="sqmx" border="1">
								  <thead>
								   <tr>
										<th width="12%" height="32" align="center">工程名称</th>
										<th width="10%"  align="center">工程造价</th>
										<th width="10%" align="center">建筑面积</th>
										<th width="6%" align="center">层数</th>
										<th width="6%" align="center">长度</th>
										<th width="12%" align="center">用途</th>
										<th width="8%" align="center">结构类型</th>
									</tr>
									</thead>
									<tbody id="DTTB">
									</tbody>
							</table>
						</td>
					</tr>
					<tr>
						<td  height="25" align="right">建设单位联系人</td>
						<td  fieldname="LXR_JSDW">
						</td>
						<td  height="25" align="right">联系方式</td>
						<td  fieldname="LXFS_JSDW">
						</td>
					</tr>
					<tr>
						<td  height="25" align="right">承包单位联系人</td>
						<td  fieldname="LXR_CBDW">
						</td>
						<td  height="25" align="right">联系方式</td>
						<td  fieldname="LXFS_CBDW">
						</td>
					</tr><tr>
						<td width="20%" align="center">合同备案号</td>
						<td width="80%" colspan="3" fieldname="HT_BAH">
						</td>
					</tr>
					<tr>
						<td colspan="6">
					    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 本工程是我单位使用私有资金投资建设自建的，不涉及到国有投资或集体投资，也不涉及国家融资、国际组织或者外国政府贷款。由我单位提交的工程项目直接发包及合同备案资料没有隐瞒、虚假、伪造等弄虚作假行为，全部资料复印件均与原件相符。以上承诺如有不实，我单位将承担全部法律责任，特此承诺。
							<br /><br /> <div style="float:right">发包人（公章）：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;法定代表人（签名）：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;　
						</div>
						</td>
					</tr>
     		</table>
     		<table style="width: 700px; height: 30px;" border="0" align="center">
					<tr>
						<td align="right">
							报备编号:<span id=BB_CODE></span>
						</td>
					</tr>
					<tr>
						<td align="left">
							注：承包人情况参照《无锡新区建设工程监理项目部关键岗位人员配备表》
     		
						</td>
					</tr>
			</table>
			
      	</form>
      	
    </div>
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