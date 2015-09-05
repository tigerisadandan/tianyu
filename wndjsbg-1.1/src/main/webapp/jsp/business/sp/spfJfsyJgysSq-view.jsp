<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<title>商品房交付使用竣工验收申请-维护</title>
<%
String type = request.getParameter("type");
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
//请求路径，对应后台RequestMapng
var controllername= "${pageContext.request.contextPath }/sp/spfjfsyjgyssqController/";
var controllernameMx= "${pageContext.request.contextPath }/sp/spfjfsyjgyssqPtmxController/";
var controllerfile= "${pageContext.request.contextPath }/controller/fileController/";


var type ="<%=type%>";
var id ="<%=id%>";

	//页面初始化
$(function() {
	init();
	$("#downLoad").click(function() {
		download();
	});
		//按钮绑定事件(保存)
		$("#btnSave").click(function() {
			if ($("#buSpywSpfjfsyjgyssqForm").validationButton()) {
				//生成json串
				var data = Form2Json.formToJSON(buSpywSpfjfsyjgyssqForm);
				//组成保存json串格式
				var data1 = defaultJson.packSaveJson(data);
				//调用ajax插入
				if (type == ("insert")) {
					defaultJson.doInsertJson(controllername + "insert", data1);
				} else if (type == ("update")) {
					defaultJson.doInsertJson(controllername + "update", data1);
				}
			} else {
				requireFormMsg();
				return;
			}
		});

	});
	//页面默认参数
function init() {
	$("#pubAlert").hide();

	if(id!="null"&&id!=""){
		 $("#QID").val(id);
		
		var data = combineQuery.getQueryCombineData(queryForm,frmPost);
		var data1 = {
			msg : data
		};	
		var resultobj;
		$.ajax({

			url : controllername+"query",
			data : data1,
			cache : false,
			async :	false,
			dataType : "json",  
			type : 'post',
			success : function(response) {
				$("#resultXML").val(response.msg);
				resultobj = defaultJson.dealResultJson(response.msg);
				$("#QID1").val(resultobj.SPFJFSYJGYSSQ_UID);
				$("#buSpywSpfjfsyjgyssqForm").setFormValues(resultobj);
				$("#buSpywSpfjfsyjgyssqForm").setHtmlValue(resultobj);
				//queryFileData2('SGXKZSQ_UID','parent','BU_SPYW_JSGCSGXKZSQ');//读取附件
		}
		});
		
		builderMxList();
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
	doSum();
}

//点击行事件
function tr_click(obj) {
		//alert(JSON.stringify(obj));
		$("#buSpywSpfjfsyjgyssqForm").setFormValues(obj);
	}

function download(){
	
	$.ajax({
		url : controllername+"download?id="+$("#SPFJFSYJGYSSQ_UID").val(), 
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
function addSqmx(demo){
		var cloneNew = $("#cloneTR").clone(true);
		$(cloneNew).removeAttr("style")
		$("#heji").before(cloneNew);
<%--		$(demo).hide();--%>
	}

function removeSqmx(demo){
		if($("#sqmx tr").size()==8){

			return;
			}
		var tr_index = $("#sqmx tr").index($(demo).closest("tr"));
		if(tr_index==$("#sqmx tr").size()-1&&tr_index>2){
			$("#sqmx tr").eq($("#sqmx tr").size()-2).find("td").eq($("#sqmx").find("th").size()-1).append('&nbsp;<img onclick="addSqmx(this)" style="cursor: pointer;" title="增加" src="/wndjsjs/img/sg/icon-addZz.jpg">');
		}
			$(demo).closest("tr").remove();
	}
function getJgSum(){
	var a = 0;
	$("tr",$("#sqmx")).each(function(i){
		if(i>1&&i<$("#sqmx").find("tr").size()-1){
			var n =$("#sqmx").find("tr").eq(i).find("#JHMJ").val();
			if(n){a+=parseFloat(n);}
		}
	})
	$("#HJ_JGMJ").val(a);
}
function doSum(){
	var a = 0;
	$("tbody tr",$("#sqmx")).each(function(){
		var val = $(this).find("td").eq(1).html()
		if(val){
			a+=parseFloat(val);
		}
	})
	var b = 0;
	$("tbody tr",$("#sqmx")).each(function(){
		var val = $(this).find("td").eq(3).html()
		if(val){
			b+=parseFloat(val);
		}
	})
	$("#sqmx").find("tbody").append("<td align=center height=20>合计</td><td align=right>"+a+"</td><td align=center></td><td align=right>"+b+"</td><td align=center></td>");
	
}
</script>
</head>
<body>
<div class="container-fluid">
	<div class="row-fluid">
	<div class="B-small-from-table-autoConcise">
	<form method="post" id="queryForm">
	<table class="B-table" width="100%">
		<!--可以再此处加入hidden域作为过滤条件 -->
		<TR style="display: none;">
		<TD class="right-border bottom-border">
			<INPUT type="text" class="span12" kind="text" id="QID" name="ID" fieldname="YWLZ_UID" value="" operation="=" />
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
			<INPUT type="text" class="span12" kind="text" id="QID1" name="ID" fieldname="SPFJFSYJGYSSQ_UID" value="" operation="=" />
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
				<form method="post" id="buSpywSpfjfsyjgyssqForm">
						<h4 class="title" align="center">
							无锡市新区商品房交付使用竣工验收申请表
						</h4>
							<table style="width: 750px; height: 800px;" border="1" align="center" >
							 <input type="hidden" id="SPFJFSYJGYSSQ_UID" fieldname="SPFJFSYJGYSSQ_UID" name = "SPFJFSYJGYSSQ_UID"/>
							 <input type="hidden" id="YWLZ_UID" fieldname="YWLZ_UID" name = "YWLZ_UID"/>
							<tr>
								<td width="15%" colspan="2" align="center">单位名称(公章)</td>
								<td width="35%" colspan="2" fieldname="DWMC">
								</td>
								<td width="15%" align="center">通讯地址</td>
								<td width="35%" colspan="2"  fieldname="TXDZ">
								</td>
							</tr>
							<tr>
								<td width="15%" colspan="2" align="center">法人代表</td>
								<td width="35%" colspan="2"  fieldname="FRDB" >
								</td>
								<td width="15" align="center">联系电话及传真</td>
								<td width="35%" colspan="2" fieldname="LXDHJCZ" >
								</td>
							</tr>
							<tr>
								<td width="15%" colspan="2" align="center">联系人</td>
								<td width="35%" colspan="2" fieldname="LXR" >
								</td>
								<td width="15%" align="center">联系手机</td>
								<td width="35%" colspan="2" fieldname="LXSJ" >
								</td>
							</tr>
							<tr>
								<td width="15%" colspan="2" align="center">项目性质</td>
								<td width="35%" colspan="2" fieldname="XMXZ">
								</td>
								<td width="15" align="center">项目地址</td>
								<td width="35%" colspan="2" fieldname="XMDZ">
								</td>
							</tr>
							<tr>
								<td width="15%" colspan="2" align="center">项目合同交付时间</td>
								<td width="35%" colspan="2" fieldname="XMHTJFSJ_DATE">
								</td>
								<td width="15%" align="center">交付性质</td>
								<td width="35%" colspan="2" fieldname="JFXZ">
								</td>
							</tr>
							<tr>
								<td width="15%" colspan="2" align="center"><strong>项目名称</strong></td>
								<td width="85%" colspan="5" fieldname="XMMC">
								</td>
							</tr>
							<tr>
								<td width="5%" rowspan="11" style="line-height: 15px">
									<p align="center">住</p>
									<p align="center">宅	</p>
									<p align="center">交</p>
									<p align="center">付</p>
									<p align="center">情</p>
									<p align="center">况</p>
								</td>
								<td width="10%" rowspan="6" style="line-height: 15px">
									<p align="center">规</p>
									<p align="center">划</p>
									<p align="center">可</p>
									<p align="center">建</p>
									<p align="center">面</p>
									<p align="center">积</p>
								</td>
								<td width="85%" height="33" colspan="5" align="center">其中</td>
							</tr>
							<tr>
								<td width="10%" height="27" align="center">层数</td>
								<td width="30%" colspan="2" align="center">住宅面积(平方米)、户数</td>
								<td width="35%" colspan="2" align="center">公建配套面积(平方米)</td>
							</tr>
							<tr>
								<td height="30" align="center">高层</td>
								<td colspan="2"  fieldname="GC_GHKJ" >
								</td>
								<td colspan="2" rowspan="4" fieldname="GJPTMJ_GHKJ" >
								</td>
							</tr>
							<tr>
								<td height="30" align="center">多层</td>
								<td colspan="2" fieldname="DC_GHKJ">
								</td>
									
							</tr>
							<tr>
								<td height="30" align="center">地下室</td>
								<td colspan="2" fieldname="DXS_GHKJ" >
								</td>
							</tr>
							<tr>
								<td height="30"  align="center">合计</td>
								<td colspan="2"  fieldname="HJ_GHKJ" >
								</td>
								
							</tr>
							<tr>
								<td width="10%" rowspan="5" style="line-height: 15px">
									<p align="center">申	</p>
									<p align="center">请</p>
									<p align="center">验</p>
									<p align="center">收</p>
									<p align="center">面</p>
									<p align="center">积</p>
								</td>
								<td height="30" align="center">层数</td>
								<td colspan="2" align="center">面积(平方米)</td>
								<td colspan="2" align="center">幢号(户数)</td>
							</tr>
							<tr>
								<td height="30" align="center">高层</td>
								<td colspan="2"  fieldname="GC_SQYS">
								</td>
								<td  colspan="2" fieldname="GCDH_SQYS" >
								</td>
							</tr>
							<tr>
								<td height="30" align="center">多层</td>
								<td colspan="2"  fieldname="DC_SQYS" >
								</td>
								<td colspan="2"  fieldname="DCDH_SQYS" >
								</td>
							</tr>
							<tr>
								<td height="30" align="center">地下室</td>
								<td colspan="2"  fieldname="DZS_SQYS" >
								</td>
								<td colspan="2"  fieldname="DXSDH_SQYS" >
								</td>
							</tr>
							<tr>
								<td height="30"  align="center">合计</td>
								<td colspan="2"  fieldname="HJ_SQYS" >
								</td>
								<td colspan="2"  fieldname="HJDH_SQYS">
								</td>
							</tr>
							<tr>
							  <td width="4%" id="GJ" style="line-height: 15px">
											<p align="center">公</p>
											<p align="center">建</p>
											<p align="center">配</p>
											<p align="center">套</p>
											<p align="center">验</p>
											<p align="center">收</p>
										</td>
							  <%--<td align="center" colspan="6" cellSpacing=0 cellPadding=0 style="padding-left: 0px;">--%>
							  <td align="center" colspan="6" cellSpacing=0 cellPadding=0 style="padding-top: 0px;">
							  <div class="overFlowX" >
								<table border="1" class="table-hover table-activeTd B-table"  noprint="true" height="100%" width="100%" id="sqmx" noPage="true" type="single">
								
								<thead>
								<tr>
								
									<th  fieldname="XMMC" width="8%" colindex=0  height="30"  tdalign="center">项目名称</th>
									<th  fieldname="JHMJ" width="26%" colindex=0  height="30" tdalign="right">计划面积(平方米)</th>
									<th  fieldname="GS" width="9%" colindex=0 height="30" tdalign="center">归属</th>
									<th  fieldname="JGMJ" width="22%" colindex=0 height="30" tdalign="right">竣工面积(平方米)</th>
									<th  fieldname="WZ" width="11%" colindex=0 height="30" tdalign="center">位置</th>
									
								</tr>
								<%--<% if("yes".equals(val)){%>
								<tr>
                                    <td width="8%"></td>		
                                    <td width="26%"></td>
                                    <td width="9%"></td>
                                    <td width="22%"></td>
                                    <td width="11%"></td>								
								</tr>
								
								
								
								<% }else{} %>
								--%><%--<tr id="heji">
								
									<td height="30"  align="center" width="13%">
										合计
									</td>
									<td  width="25%">
									</td>
									<td width="8%">
									</td>
									<td width="22%">
									</td>
									<td  width="15%">
									</td>
								</tr>
								--%></thead>
							    <tbody></tbody>	
								</table>
								</div>
							</td>
							
							<tr>
								<td colspan="2" align="center" width="15%">
									累计
								</td>
								<td colspan="2" width="33%">
								</td>
								<td  width="5%">
								</td>
								<td width="26%">
								</td>
								<td width="13%">
								</td>
							</tr>
						</table>
					</form>
					</div>
				</div>
			</div>
			<div align="center">
				<FORM name="frmPost" method="post" style="display: none"
					target="_blank">
					<!--系统保留定义区域-->
					<input type="hidden" name="queryXML" id="queryXML">
					<input type="hidden" name="txtXML" id="txtXML">
					<input type="hidden" name="txtFilter" order="desc" fieldname="CREATED_DATE" id="txtFilter">
					<input type="hidden" name="resultXML" id="resultXML">
					<!--传递行数据用的隐藏域-->
					<input type="hidden" name="rowData">

				</FORM>
			</div>
	</body>
</html>