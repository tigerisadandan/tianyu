<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<title>无锡新区房屋建筑和市政基础设施施工工程公开（邀请）招标初步方案审批表</title>
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
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/sp/buSpywCbfaspGkzbController/";
var controllernameMx= "${pageContext.request.contextPath }/sp/buSpywCbfaspFbfaController/";
var controllerfile= "${pageContext.request.contextPath }/controller/fileController/";

var id ="<%=id%>";
var ywlz="<%=ywlz%>";
var contextPath="${pageContext.request.contextPath }";
	//页面初始化
$(function() {
		init();
		$("#downLoad").click(function() {
			download();
		});
		//按钮绑定事件(保存)

	});
function download(){
	var sg="sg";
	$.ajax({
		url : controllername+"download?id="+$("#CBFASP_GKZB_UID").val()+"&ywlz="+ywlz+"&ty="+sg, 
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

	//页面默认参数
function init() {
	$("#pubAlert").hide();
	
	$("#LX").val("sg")
	$("#QLX").val("sg")
	$("#QYWLZ_UID").val(id);	
	
	setFormValues();
}

	
function setFormValues() {
		var data = combineQuery.getQueryCombineData(queryForm, frmPost);
		var data1 = {
			msg : data
		};
		$.ajax( {

			url : controllername + "query",
			data : data1,
			cache : false,
			async : false,
			dataType : "json",
			type : 'post',
			success : function(response) {
				//alert(response.msg);
				$("#resultXML").val(response.msg);
				if(response.msg==""||response.msg=="0"){
					type="insert";
				}else{
					type="update"
					var resultobj = defaultJson.dealResultJson(response.msg);
					
					resultobj.BB_CODE = "监理报备编号："+resultobj.BB_CODE; 
					$("#buSpywSpfjfsyjgyssqForm").setHtmlValue(resultobj);
					$("#CBFASP_GKZB_UID").val(resultobj.CBFASP_GKZB_UID);
					queryDt(resultobj.DT_IDS);
					}
			}
		});
		if($("#CBFASP_GKZB_UID").val()!=""){
			//builderMxList();
			}
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
									<INPUT type="text" class="span12" kind="text" id="QYWLZ_UID" name="YWLZ_UID" fieldname="YWLZ_UID" value="" operation="=" />
									<INPUT type="text" class="span12" kind="text" id="QLX" name="LX" fieldname="LX" value="" operation="=" />
								</TD>
								<TD class="right-border bottom-border">
								</TD>
							</TR>
						</table>
					</form>
					
					<div style="height: 5px;"></div>
					<div class="B-small-from-table-autoConcise">

						<span class="pull-right">
					<button id="btnShutdown" onclick="window.close();" class="btn" type="button" style="font-family: SimYou, Microsoft YaHei; font-weight: bold;">关闭</button>
		    		<button id="downLoad" class="btn" type="button" style="font-family: SimYou, Microsoft YaHei; font-weight: bold;">打印</button>				
		    	</span>
						<br />

						<form method="post" id="buSpywSpfjfsyjgyssqForm">
							<h4 class="title" align="center">
								无锡新区施工工程公开（邀请）招标初步方案审批表
							</h4>
							<table style="width: 700px; height: 700px;"  border="1" align="center">
								<input type="hidden" id="CBFASP_GKZB_UID" fieldname="CBFASP_GKZB_UID" name = "CBFASP_GKZB_UID"/>
								<input type="hidden" id="DT_IDS" fieldname="DT_IDS" name = "DT_IDS"/>
								<input type="hidden" id="LX" fieldname="LX" name="LX"/>
								<input type="hidden" id="YWLZ_UID" fieldname="YWLZ_UID" name = "YWLZ_UID"/>
								<tr>
									<td width="20%"  align="center">工程名称</td>
									<td width="80%" colspan="3" fieldname="GONGCHENG_NAME">
									</td>
								</tr>
								<tr>
									<td width="20%"  align="center">建设单位名称</td>
									<td width="80%" colspan="3" fieldname="JS_COMPANY_NAME">
									</td>
								</tr>
								<tr>
									<td width="20%"  align="center">承包单位名称</td>
									<td width="80%" colspan="3" fieldname="CB_COMPANY_NAME">
									</td>
								</tr>
								<tr>
									<td width="20%"  align="center">建设地点</td>
									<td width="80%" colspan="3" fieldname="JS_DIDIAN">
									</td>
								</tr>
								<tr>
									<td width="20%"  align="center">项目负责人（项目总监）</td>
									<td width="30%" fieldname="XM_FZR">
									</td>
									<td width="20%"  align="center">中标价（万元）</td>
									<td width="30%" fieldname="ZBJ">
									</td>
								</tr>
								<tr>
									<td width="20%"  align="center">地上建筑面积（平方米）</td>
									<td width="30%" fieldname="JZMJ_DS">
									</td>
									<td width="20%"  align="center">地下建筑面积（平方米）</td>
									<td width="30%" fieldname="JZMJ_DX">
									</td>
								</tr>
								<tr>
									<td width="20%"  align="center">承包性质</td>
									<td width="30%" fieldname="CB_XINGZHI_SV">
									</td>
									<td width="20" align="center">是否市政工程</td>
									<td width="30%" fieldname="SFSZGC_SV">
									</td>
								</tr>
								<tr>
									<td width="20%"  align="center">发包方式</td>
									<td width="30%" fieldname="BID_TYPE_SV">
									</td>
									<td width="20" align="center">重量(吨)</td>
									<td width="30%" fieldname="ZHONGLIANG">
									</td>
								</tr>
								<tr>
									<td width="20%"  align="center">高度(米)</td>
									<td width="30%" fieldname="GAODU">
									</td>
									<td width="20" align="center">深度(米)</td>
									<td width="30%" fieldname="SHENDU">
									</td>
								</tr>
								<tr>
									<td width="20%" align="center">跨度（米）</td>
									<td width="30%" fieldname="KUADU">
									</td>
									<td width="20" align="center">建筑面积(平方米)</td>
									<td width="30%" fieldname="MIANJI">
									</td>
								</tr>
								<tr>
								<td width="20%" align="center">合同额（元）</td>
									<td width="30%" fieldname="JINE">
									</td>
									<td width="20" align="center">层数</td>
									<td width="30%" fieldname="CENGSHU">
									</td>
								</tr>
								<tr>
									<td width="20%"  align="center">计划开工日期</td>
									<td width="30%" fieldname="KG_DATE">
									</td>
									<td width="20" align="center">计划完工日期</td>
									<td width="30%" fieldname="WG_DATE">
									</td>
								</tr>
								<tr>
									
									<td width="20" align="center">计划竣工日期</td>
									<td width="30%" fieldname="JG_DATE">
									</td>
								</tr>
								<tr>
									<td colspan="4" align="center">
									单位工程 
									</td>
								</tr>
							<tr>
									<td width="20%"  align="center">建设类型</td>
									<td width="30%" fieldname="JS_LEIXING_SV">
									</td>
									<td width="20" align="center">投资类型</td>
									<td width="30%" fieldname="TZ_LEIXING_SV">
									</td>
								</tr>
								<tr>
									<td width="20%"  align="center">工程内容</td>
									<td width="30%" fieldname="NEIRONG_SV">
									</td>
									<td width="20" align="center">工程性质</td>
									<td width="30%" fieldname="GC_XINGZHI_SV">
									</td>
								</tr>
								<tr>
								  <td  colspan="4">
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
						<td width="20%"  align="center">建设单位联系人</td>
						<td width="30%" fieldname="JSDW_LXR">
						</td>
						<td width="20" align="center">联系方式</td>
						<td width="30%" fieldname="JSDW_LXR_MOBILE">
						</td>
					</tr>
					<tr>
						<td width="20%" align="center">承包单位联系人</td>
						<td width="30%" fieldname="CBDW_LXR">
						</td>
						<td width="20" align="center" id="lxfs">联系方式</td>
						<td width="30%" fieldname="CBDW_LXR_MOBILE">
						</td>
					</tr>
					<tr>
						<td width="20%" align="center">合同备案号</td>
						<td width="80%" colspan="3" fieldname="HT_BAH">
						</td>
					</tr>
				</table>
				<table style="width: 700px; height: 30px;" border="0" align="center">
					<tr>
						<td align="right" fieldname="BB_CODE">
							报备编号
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
	<script>
	
</script>
</html>