<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<title>无锡新区房屋建筑和市政基础设施施工工程直接发包初步方案审批表</title>
<%

String YWLZ_UID=request.getParameter("YWLZ_UID");
String YWCL_UID=request.getParameter("YWCL_UID");
String PIJIAN_CODE=request.getParameter("PIJIAN_CODE");
String PIJIAN_NAME=request.getParameter("PIJIAN_NAME");
String LINGJIAN_REN=request.getParameter("LINGJIAN_REN");
String LINGJIAN_PHONE=request.getParameter("LINGJIAN_PHONE");
String FZ_DATE=request.getParameter("FZ_DATE");
String YXQ_DATE=request.getParameter("YXQ_DATE");
String CLK_UID=request.getParameter("CLK_UID");
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
var controllername= "${pageContext.request.contextPath }/sp/buSpywCbfaspController/";
var controllernamehf= "${pageContext.request.contextPath }/sp/buSpywCbfaspGcjsxmzjfbhtbabController/";


var type;

var YWLZ_UID="<%=YWLZ_UID%>"; 
var YWCL_UID="<%=YWCL_UID%>"; 
var PIJIAN_CODE="<%=PIJIAN_CODE%>"; 
var PIJIAN_NAME="<%=PIJIAN_NAME%>"; 
var LINGJIAN_REN="<%=LINGJIAN_REN%>"; 
var LINGJIAN_PHONE="<%=LINGJIAN_PHONE%>"; 
var FZ_DATE="<%=FZ_DATE%>"; 
var YXQ_DATE="<%=YXQ_DATE%>"; 
var CLK_UID="<%=CLK_UID%>"; 

var contextPath="${pageContext.request.contextPath }";
	//页面初始化
$(function() {
	
		init();

		//按钮绑定事件(保存)
		$("#btnSave").click(function() {
			if(confirm("您确定要保存本条信息吗？ ")){
				if ($("#gcjsxmzjfbhtbabForm").validationButton()) {
					//生成json串
					var data = Form2Json.formToJSON(gcjsxmzjfbhtbabForm);
					//组成保存json串格式
					var data1 = defaultJson.packSaveJson(data);
					//调用ajax插入
						defaultJson.doInsertJson(controllername + "insertHF", data1);
						 var json=$("#resultXML").val();//接受返回的数据 
				    	 var tempJson=eval("("+json+")");
				    	 var resultobj=tempJson.response.data[0];
				    	 $("#gcjsxmzjfbhtbabForm").setFormValues(resultobj);
				    	 $("#CBFASP_GCJSXMZJFBHTBAB_UID").val(resultobj.CBFASP_GCJSXMZJFBHTBAB_UID);
				    	 $("#QID").val(resultobj.CBFASP_GCJSXMZJFBHTBAB_UID);
				    var datahf = Form2Json.formToJSON(queryForm2);
					//组成保存json串格式
					var datahf1 = defaultJson.packSaveJson(datahf);
					//调用ajax插入
					defaultJson.doInsertJson(controllernamehf + "ywlzclhf", datahf1);
				}
				 else {
					requireFormMsg();
					return;
				
				}
			}
			window.close();
		});

	});
	//页面默认参数
function init() {
	$("#pubAlert").hide();

	
	$("#YWLZ_UID2").val(YWLZ_UID);	
	$("#YWCL_UID").val(YWCL_UID);	
	$("#PIJIAN_CODE").val(PIJIAN_CODE);	
	$("#PIJIAN_NAME").val(PIJIAN_NAME);	
	$("#LINGJIAN_REN").val(LINGJIAN_REN);	
	$("#LINGJIAN_PHONE").val(LINGJIAN_PHONE);	
	$("#FZ_DATE").val(FZ_DATE);	
	$("#YXQ_DATE").val(YXQ_DATE);	
	$("#CLK_UID").val(CLK_UID);	
	

	$("#LX").val("sg")
	$("#QLX").val("sg")

	//主键ID
	$("#QYWLZ_UID").val(YWLZ_UID);	
	
	setFormValues();//获取上个页面，本表的主键ID来查询所有信息 
 	//获取子表信息 


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
				$("#resultXML").val(response.msg);
				var resultobj = defaultJson.dealResultJson(response.msg);
					$("#gcjsxmzjfbhtbabForm").setFormValues(resultobj);
					$("#JZMJ").val(resultobj.ZJMJ);
					//$("#CBFASP_GCJSXMZJFBHTBAB_UID").val(resultobj.CBFASP_GCJSXMZJFBHTBAB_UID);
					$("#QYMC").html(resultobj.FBRMC);
					$("#XMMC").html(resultobj.GCMC);
					//$("#FBNR").val(resultobj.GCMC);
					var sDate=resultobj.DATE_JHKG;
					var eDate=resultobj.DATE_JHJG;
					var sArr = sDate.split("-");
					var eArr = eDate.split("-");
					var sRDate = new Date(sArr[0], sArr[1], sArr[2]);
					var eRDate = new Date(eArr[0], eArr[1], eArr[2]);
					var result = (eRDate-sRDate)/(24*60*60*1000);
					$("#HTGQ").val(result);
					getNum();
					findByPerson(resultobj.BB_CODE);
					findByCompany(resultobj.BB_CODE);
					//$("#BH").html();
			}
		});
	} 

function getNum(){
	var lx="sg";
	$.ajax({
		url : controllername + "getNum?lx="+lx,
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var arr =response;
			$("#BH").html(arr);
			$("#BH").val(arr);
		}
	});
	
}
function findByPerson(code){
	var uid=19;
	var lx="sg";
	$.ajax({
		url : controllername + "findByPerson?lx="+lx+"&uid="+uid+"&code="+code,
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var arr = new Array();
			arr =response;
			if(arr==""){
				$("#person_code").text("填报房屋建筑和市政基础设施工程施工、监理直接发包合同备案审批表时，报备编号填写错误！");
			}else{
			    arr = response.split(",");
			    $("#JZS").val(arr[0]);
			    $("#JZ_ZSBH").val(arr[1]);
			    $("#person_code").text("");
			}
		}
	});
}
function findByCompany(code){
	var lx="sg";
	$.ajax({
		url : controllername + "findByCompany?lx="+lx+"&code="+code,
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var arr = new Array();
			arr = response.split(",");
			$("#CBR").val(arr[0]);
			$("#CBR_ZSBH").val(arr[1]);
			 
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
					<form method="post" id="queryForm2">
						<table class="B-table" width="100%">
							<!--可以再此处加入hidden域作为过滤条件 -->
							<TR style="display: none;">
								<TD class="right-border bottom-border">
									<INPUT type="text" class="span12" kind="text" id="QID" name="CBFASP_GCJSXMZJFBHTBAB_UID" fieldname="CBFASP_GCJSXMZJFBHTBAB_UID" />
									<INPUT type="text" class="span12" kind="text" id="YWLZ_UID2" name="YWLZ_UID" fieldname="YWLZ_UID" />
									<INPUT type="text" class="span12" kind="text" id="YWCL_UID" name="YWCL_UID" fieldname="YWCL_UID" />
									<INPUT type="text" class="span12" kind="text" id="PIJIAN_CODE" name="PIJIAN_CODE" fieldname="PIJIAN_CODE" />
									<INPUT type="text" class="span12" kind="text" id="PIJIAN_NAME" name="PIJIAN_NAME" fieldname="PIJIAN_NAME" />
									<INPUT type="text" class="span12" kind="text" id="LINGJIAN_REN" name="LINGJIAN_REN" fieldname="LINGJIAN_REN" />
									<INPUT type="text" class="span12" kind="text" id="LINGJIAN_PHONE" name="LINGJIAN_PHONE" fieldname="LINGJIAN_PHONE" />
									<INPUT type="text" class="span12" kind="text" id="FZ_DATE" name="FZ_DATE" fieldname="FZ_DATE" />
									<INPUT type="text" class="span12" kind="text" id="YXQ_DATE" name="YXQ_DATE" fieldname="YXQ_DATE" />
									<INPUT type="text" class="span12" kind="text" id="CLK_UID" name="CLK_UID" fieldname="CLK_UID" />
								</TD>
								<TD class="right-border bottom-border">
								</TD>
							</TR>
						</table>
					</form>
				
					<div style="height: 5px;"></div>
					<div class="B-small-from-table-autoConcise">

						<span style="float: right">
							<button id="btnShutdown" onclick="window.close();" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
							<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">保存</button>
						</span>
						<br/>
						
						<form method="post" id="gcjsxmzjfbhtbabForm">
							<table style="width: 700px; height: 50px;" border="0" align="center">
							<h3 class="title" align="center">
									江苏省工程建设项目直接发包合同备案表
							</h3>
							<tr >
								<td align="left">
								<b>编号：</b><span id="BH" style="font-weight:bold;"></span><br/>
								<span id="QYMC" style="font-weight:bold;" fieldname="BH"></span>:<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;你方提交的&nbsp;<span id="XMMC" style="font-weight:bold;text-decoration:underline"></span>&nbsp;的合同备案材料收讫。你方可持该表到施工许可管理部门办理施工许可手续。
								</td>
							</tr>
							</table>
							
							<h4 class="title" align="center">
								工程基本情况
							</h4>
							<table style="width: 700px; height: 350px;" border="1" align="center">
								<input type="hidden" id="CBFASP_GCJSXMZJFBHTBAB_UID" fieldname="CBFASP_GCJSXMZJFBHTBAB_UID" name = "CBFASP_GCJSXMZJFBHTBAB_UID"/>
								<input type="hidden" id="YWLZ_UID" fieldname="YWLZ_UID" name = "YWLZ_UID"/>
								<input type="hidden" id="BH" fieldname="BH" name = "BH"/>
								<input type="hidden" id="CBFASP_UID" fieldname="CBFASP_UID" name = "CBFASP_UID"/>
								
								<tr>
									<td width="20%" align="center">发包人</td>
									<td width="80%" colspan="3">
										<input name="FBRMC" type="text" id="FBRMC" maxlength="50" placeholder="必填" check-type="required"  readonly="true" fieldname="FBRMC" style="width: 93%" />
									</td>
									
								</tr>
								<tr>
									<td width="20%"  align="center">工程名称</td>
									<td width="80%" colspan="3">
										<input name="GCMC" type="text" id="GCMC" maxlength="50" placeholder="必填" check-type="required"  readonly="true" fieldname="GCMC" style="width: 97%" />
									</td>
								</tr>
								<tr>
									<td width="20%"  align="center">工程地点</td>
									<td width="80%" colspan="3">
										<input name="JSTD" type="text" id="JSTD" fieldname="JSTD" style="width: 97%" readonly="true"/>
									</td>
								</tr>
								<tr>
									<td width="20%"  align="center">发包内容</td>
									<td width="80%" colspan="3">
										<input name="FBNR" type="text" id="FBNR" fieldname="FBNR" maxlength="50" style="width: 93%"  readonly="true"/>
									</td>
					
								</tr>
								<tr>
									<td width="20%"  align="center">建筑面积(M2)</td>
									<td width="30%" >
										<input name="JZMJ" type="text" id="JZMJ" fieldname="JZMJ" maxlength="50" style="width: 93%" onblur="onlyNumAndFloat(this)"/>
									</td>
									<td width="20" align="center">结构</td>
									<td width="30%" >
										<input name="JGLX" type="text" id="JGLX" maxlength="50" fieldname="JGLX" style="width: 93%" />
									</td>
								</tr>
								<tr>
									<td width="20%"  align="center">层次</td>
									<td width="30%" >
										<input name="CS" type="text" id="CS" fieldname="CS" style="width: 93%" onblur="onlyNum(this)"/>
									</td>
									<td width="20" align="center">合同价（万元）</td>
									<td width="30%" >
										<input name="BDHTJ" type="text" id="BDHTJ" maxlength="10" fieldname="BDHTJ" style="width: 93%"  onblur="onlyNumAndFloat(this)" />
									</td>
								</tr>
								<tr>
									<td width="20%" align="center">承包人</td>
									<td width="30%" c>
										<input name="CBR" type="text" id="CBR" fieldname="CBR"  maxlength="50" style="width: 93%" readonly placeholder="必填" check-type="required"/>
									</td>
									<td width="20" align="center">资质证书编号</td>
									<td width="30%" >
										<input name="CBR_ZSBH" type="text"  id="CBR_ZSBH" fieldname="CBR_ZSBH" style="width: 93%" readonly placeholder="必填" check-type="required" />
									</td>
								</tr>
								<tr>
									<td width="20%" align="center">质量等级</td>
									<td width="30%" >
										合格
									</td>
									<td width="20%" align="center">合同工期（天）</td>
									<td width="30%" >
										<input name="HTGQ" type="text" id="HTGQ"  maxlength="10" fieldname="HTGQ" style="width: 93%" onblur="onlyNum(this)" />
									</td>
								</tr>
								<tr>
									<td width="20%"  align="center">建造师</td>
									<td width="30%" >
										<input name="JZS" maxlength="20" type="text" id="JZS" fieldname="JZS" style="width: 93%"  readonly placeholder="必填" check-type="required"/>
									</td>
									<td width="20" align="center">资质证书编号</td>
									<td width="30%" >
										<input name="JZ_ZSBH" type="text" maxlength="20" id="JZ_ZSBH" fieldname="JZ_ZSBH" style="width: 93%" readonly placeholder="必填" check-type="required"/>
									</td>
								</tr>
								<tr>
									<td colspan="4">
									备注:<input name="BZ1" type="text" maxlength="20" id="BZ1" fieldname="BZ1" style="width: 93%" />
									</td>
								</tr>
					
				</table>
				<table style="width: 700px; height: 50px;" border="0" align="center">
							
							<tr >
								<td align="left"  valign="top" width="50%" >
									<b><span id="person_code" style="color:red;font-size: 14px"></span></b>
								</td>
								<td align="right">
									经办人（签名）：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>
									复核人（签名）：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>
									招标投标监督管理机构（盖章）：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>
									 年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
							</tr>
							<tr>
								<td align="left" colspan="2">
								说明：本表一式两份，招标人、招标投标监督管理机构各执一份，复印无效。
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