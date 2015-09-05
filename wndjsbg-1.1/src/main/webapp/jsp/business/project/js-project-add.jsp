<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base />
<title>项目-维护</title>
<%
	String type = request.getParameter("type");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>

<LINK type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/jsp/business/ywlz/css/default.css">
</LINK>
<script language="javascript" type="text/javascript"
	src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/js/kindeditor/themes/default/default.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/js/kindeditor/plugins/code/prettify.css" />
<script charset="UTF-8"
	src="${pageContext.request.contextPath }/js/kindeditor/kindeditor.js"></script>
<script charset="UTF-8"
	src="${pageContext.request.contextPath }/js/kindeditor/lang/zh_CN.js"></script>

<script type="text/javascript" charset="utf-8">

//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/project/jsProjectController";
var type ="<%=type%>";
	//页面初始化
	$(function() {
		init();

		//按钮绑定事件(保存)
		$("#btnSave").click(
				function() {
					if($("#projectsForm").validationButton())
					{
						
					if ($("#PROJECT_UID").val() == ""
							|| $("#PROJECT_UID").val() == null) {
						defaultJson.doInsertJson(controllername + "?insert",
								data1);
						$("#projectsForm").clearFormResult();
					} else {
						$("#FM_SHENHE_YIJIAN").val($("#SHENHE_YIJIAN").val());
						$("#FM_SHENHE_JIEGUO").val($("input:radio[name='SHENHE_JIEGUO']:checked").val());
						//alert($("#PROJECT_UID").val());
						if ($("input:radio[name='SHENHE_JIEGUO']:checked")
								.val() == 2) {
							var data = Form2Json.formToJSON(projectsForm);
							var data1 = defaultJson.packSaveJson(data);
							var reason = $("#SHENHE_YIJIAN").val();
							if (reason.length == 0) {
								alert("请填写审批不通过理由!");
								return;
							} else {
								if (confirm("确定不通过此次审核吗？")) {
									defaultJson.doUpdateJson(controllername
											+ "?update", data1);
								}
							}
						} else {
							var data = Form2Json.formToJSON(projectsForm);
							var data1 = defaultJson.packSaveJson(data);
							var reason = $("#SHENHE_YIJIAN").val();
							if (reason.length == 0) {
								alert("请填写审批意见!");
								return;
							} else {
							if (confirm("确定通过此次审核吗？")) {
								defaultJson.doUpdateJson(controllername
										+ "?update", data1);
							}
							}
						}

					}
					}else{
						requireFormMsg();
					  	return;
					}
					var a = $(window).manhuaDialog.getParentObj();
					a.init();
					$(window).manhuaDialog.close();

				});

		$("#btnClose").bind("click", function() {
			var a = $(this).manhuaDialog.close();
		});
<%if (type.equals("detail")) {%>
	//置所有input 为disabled
		$(":input").each(function(i) {
			$(this).attr("disabled", "true");
		});
		$("#btnClose").attr("disabled", false);
<%}%>
	});
	//页面默认参数
	function init() {
		$("#shrsjdiv").css("visibility", "hidden")
		$("#shrsj1div").css("visibility", "hidden");

		if (type == "insert") {
			//生成json串

		} else if (type == "detail") {
			//生成json串
			var parentmain = $(window).manhuaDialog.getParentObj();
			var rowValue = parentmain.$("#resultXML").val();
			var tempJson = convertJson.string2json1(rowValue);
			$("#QPROJECTS_UID").val(tempJson.PROJECT_UID);
			$("#ID").val(tempJson.PROJECT_UID);

			//查询表单赋值
			var data = combineQuery.getQueryCombineData(queryForm, frmPost);
			var data1 = {
				msg : data
			};
			$
					.ajax({
						url : controllername + "?query",
						data : data1,
						cache : false,
						async : false,
						dataType : "json",
						type : 'post',
						success : function(response) {
							$("#shrsjdiv").css("visibility", "visible")
							$("#shrsj1div").css("visibility", "visible");
							var res = dealSpecialCharactor(response.msg);
							$("#resultXML").val(response.msg);
							var resultobj = defaultJson.dealResultJson(res);
							$("#projectsForm").setFormValues(resultobj);
							$("#projectsFormSh").setFormValues(resultobj);

							var positionOne = resultobj.POSITION;
							if(resultobj.XMFQ_Y==0){ // 无分期 //单位工程初始化
								unitsInit(resultobj.PROJECT_UID);
							}
							var xmlxmsg = resultobj.XMFQ_Y==0?"(无分期建设项目)":"(有分期建设项目)";
							$("#xmlxMsg").html(xmlxmsg);
							if (resultobj.JIANSHE_TYPE == "F") {
								$("#xmfqcddiv").hide();
								$("#xmfqcdlb").hide();
								$("#SZ_SZLB_DIV").hide();
								$("#XM_CHANGDU").removeAttr("check-type");
								$("#XM_SZ_SZLB").removeAttr("check-type");
							} else if (resultobj.JIANSHE_TYPE == "S") {
								$("#xmfqcddiv").show();
								$("#xmfqcdlb").show();
								$("#SZ_SZLB_DIV").show();
							}
							//标记地图
							var imgsrc = "http://api.map.baidu.com/staticimage?width=540&height=300&center=无锡新区&zoom=10"
									+ "&paths="
									+ positionOne
									+ "&pathStyles=0xff0000,5,0.9,0xffffff";
							$("#imgbddt").attr("src", imgsrc);
							return true;
						}
					});
			loadProjectLx();
		} else {

			//生成json串
			var parentmain = $(window).manhuaDialog.getParentObj();
			var rowValue = parentmain.$("#resultXML").val();
			var tempJson = convertJson.string2json1(rowValue);
			//alert(tempJson.PROJECTS_UID);		
			$("#QPROJECTS_UID").val(tempJson.PROJECT_UID);
			$("#ID").val(tempJson.PROJECT_UID);
			//查询表单赋值
			var data = combineQuery.getQueryCombineData(queryForm, frmPost);
			var data1 = {
				msg : data
			};
			$
					.ajax({
						url : controllername + "?query",
						data : data1,
						cache : false,
						async : false,
						dataType : "json",
						type : 'post',
						success : function(response) {
							var res = dealSpecialCharactor(response.msg);
							$("#resultXML").val(response.msg);
							var resultobj = defaultJson.dealResultJson(res);
							resultobj.SHENHE_JIEGUO = "1";//默认通过
							$("#projectsForm").setFormValues(resultobj);
							$("#projectsFormSh").setFormValues(resultobj);
							var positionTwo = resultobj.POSITION;
							if(resultobj.XMFQ_Y==0){ // 无分期 //单位工程初始化
								unitsInit(resultobj.PROJECT_UID);
							}
							var xmlxmsg = resultobj.XMFQ_Y==0?"(无分期建设项目)":"(有分期建设项目)";
							$("#xmlxMsg").html(xmlxmsg);
							if (resultobj.JIANSHE_TYPE == "F") {
								$("#xmfqcddiv").hide();
								$("#xmfqcdlb").hide();
								$("#SZ_SZLB_DIV").hide();
								$("#XM_CHANGDU").removeAttr("check-type");
								$("#XM_SZ_SZLB").removeAttr("check-type");
							} else if (resultobj.JIANSHE_TYPE == "S") {
								$("#xmfqcddiv").show();
								$("#xmfqcdlb").show();
								$("#SZ_SZLB_DIV").show();
							}
							//标记地图
							var imgsrc = "http://api.map.baidu.com/staticimage?width=540&height=300&center=无锡新区&zoom=10"
									+ "&paths="
									+ positionTwo
									+ "&pathStyles=0xff0000,5,0.9,0xffffff";
							$("#imgbddt").attr("src", imgsrc);

							return true;
						}
					});
			loadProjectLx();

		}
		
		//验证是否有权显示锁定/解锁按钮
		$.ajax({
			url : "${pageContext.request.contextPath }/bgMenuController/?getByMenuCodeAndUserId",
			data : {code : "1020101"},
			type :"post",
			dataType : "json",
			success : function(response){
				if(response.msg == "1"){
					$("#SP_TABLE").show();
					$("#SP_TABLE").show();
				}else{
					$("#SP_TABLE").hide();
					$("#SP_TABLE").hide();
				}
				 //获取父页面传过来的参数 
			//	 setFormValues();
			}
		});
	}
	function loadProjectLx() {

		var data = combineQuery.getQueryCombineData(queryForm, frmPost,
				lixangList);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername + "?queryLX", data,
				lixangList, null, true);
		//处理IE浏览器在查询无记录时出现的滚动条问题
		// var rows = $("tbody tr" ,$("#lixangList"));
		//	if(rows.size()==0){
		//		$("tbody" ,$("#lixangList")).append("<tr><td colspan=\"7\" style=\"height: 1px;\" align=\"center\">暂无记录</td></tr>");
		//	}
	}
	function lxxx(obj) {

		//return showHtml = "<a href='javascript:void(0);' title='立项信息></a>";
		return showHtml = "<a href='javascript:void(0);' title='立项信息' onclick='showLXinfo(this)' >"
				+ obj.LIXIANG_NAME + "</a>";
	}
	function showLXinfo(obj) {
		while (obj.tagName.toLowerCase() != "tr") {
			obj = obj.parentNode;
			if (obj.tagName.toLowerCase() == "table")
				return null;
		}
		obj.click();
		var data = $("#lixangList").getSelectedRow();

		$("#resultXML").val(data);
		$(window)
				.manhuaDialog(
						{
							"title" : "建设项目>审核>立项信息",
							"type" : "text",
							"content" : "${pageContext.request.contextPath }/jsp/business/project/js-lixiang-show.jsp",
							"modal" : "4"
						});

	}
	function onlyNum() {

		if (!(event.keyCode == 46) && !(event.keyCode == 8)
				&& !(event.keyCode == 37) && !(event.keyCode == 39))
			if ((event.keyCode != 46)
					&& !((event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105))) {
				event.returnValue = false;
			}
	}
	function isMoney(money) {
		var mon = money.value;
		mon = mon * 0.5 / 0.5;
		money.value = mon;
	}

	function jianshetypefun(cellvalue, opts, rowObject) {
		var xmtype = $("input[name='JIANSHE_TYPE']:checked").val();
		if (xmtype == 'S') {
			$("#xmfqcddiv").show();
			$("#xmfqcdlb").show();
			$("#SZ_SZLB_DIV").show();
			$("#XM_CHANGDU").attr("check-type","required");
			$("#XM_SZ_SZLB").attr("check-type","required");
		} else {
			$("#xmfqcddiv").hide();
			$("#xmfqcdlb").hide();
			$("#SZ_SZLB_DIV").hide();
			$("#XM_SZ_SZLB").val("");
			$("#XM_CHANGDU").val("");
			$("#XM_CHANGDU").removeAttr("check-type");
			$("#XM_SZ_SZLB").removeAttr("check-type");
		}

	}
	
	
	
	function unitsInit(id){//单位工程初始化
		$("#DWGC_TABLE").show();
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
			//调用ajax插入
	    var response=defaultJson.doQueryJsonListReturn("${pageContext.request.contextPath }/units/projectsUnitsController?querybygcid&gcid="+id+"&type=XMQUERY"+"&cUid=",data,DT1);
	    var res = dealSpecialCharactor(response.msg);
		 if(response.msg=="0"){
					    return;
		 }
		var resultmsgobj = convertJson.string2json1(res);
		json=resultmsgobj.response.data;
		size=resultmsgobj.response.data.length;
		var units="";
		for(var i=0;i<size;i++){
		units+=resultmsgobj.response.data[i].UNITS_UID+",";
		}
		$("#UNTISS").val(units);
		doSum();
	}
	function doSum(){
		var a = 0;
		$("tbody tr",$("#DT1")).each(function(){
			var val = $(this).find("td").eq(5).html();
			if(val){
				a+=parseFloat(val);
			}
		})
		
		$("#DT1").find("tbody").append("<td align=center height=20>合计</td><td align=center></td><td align=center></td><td align=center></td><td align=center></td><td align=center></td><td align=center>"+a+"</td><td align=center></td>");
		
	}
	function showLong(obj){
		var val=obj.value;
		if(val=="S"){
			$("#ChangDuID").show();
			$("#ChangDuText").show();
		}else{
			$("#ChangDuID").hide();
			$("#ChangDuText").hide();
			$("#CHANGDU").val("");
			
		}
		
	}
</script>
</head>
<body>
	<app:dialogs />
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="B-small-from-table-autoConcise">
				<form method="post" id="queryForm">
					<input type="hidden" id="QPROJECTS_UID" fieldname="PROJECT_UID"
						name="PROJECT_UID" operation="=" />
				</form>
				<form method="post" id="queryForm2">
					<input type="hidden" id="ID" fieldname="t.PROJECT_UID"
						name="PROJECT_UID" operation="=" />
				</form>
			</div>
			<div style="height:5px;"></div>
			<div class="B-small-from-table-autoConcise">
				<!-- 审核 -->
				<table id="SP_TABLE" border="1" width="100%" cellpadding="0" style="display: none;"
					cellspacing="0" class="content">
					<tbody>
						<tr>
							<td class="yw-title">
								<h4>审批意见<span class="pull-right">
						<button id="btnClose" class="btn" type="button"
							style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
						<%
							if (!type.equals("detail")) {
						%>
						<button id="btnSave" class="btn" type="button"
							style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">审核</button>
						<%
							}
						%> </span></h4></td>
						</tr>
						<tr>
							<td>
								<div class="overFlowX">
									<form method="post" id="projectsFormSh">
										<table class="B-table" width="100%"
											style="padding-left: 2px;border: 0px;">

											<tr id="SHJGBox">
												<th width="15%"
													class="right-border bottom-border text-right">审核结果选择&nbsp;<font id="fontId"
													style="color:red;font-size: 16px"><b>*</b> </font></th>
												<td width="35%" class="right-border bottom-border"><input
													class="span12" style="width:94%" id="SHENHE_JIEGUO"
													type="radio" check-type="required"
													fieldname="SHENHE_JIEGUO" name="SHENHE_JIEGUO" kind="dic"
													src="SHENHEJIEGUO" /></td>
												<th id="shrsjdiv" width="15%" style="visibility: hidden;"
													class="right-border bottom-border text-right">审核人和时间</th>
												<td width="35%" id="shrsj1div"
													class="right-border bottom-border"><input 
													class="span12" style="width:30%" type="text"
													fieldname="SHENHENAME" name="SHENHENAME" /> - <input
													class="span12" style="width:65%" type="text"
													fieldname="SHENHE_DATE" name="SHENHE_DATE" /></td>
											</tr>
											<tr id="SHYJBox">
												<th width="15%"
													class="right-border bottom-border text-right">审核详细意见&nbsp;<font id="fontId"
													style="color:red;font-size: 16px"><b>*</b> </font></th>
												<td class="bottom-border right-border" colspan="3"><textarea
														class="span12" rows="2" id="SHENHE_YIJIAN"
														placeholder="必填" check-type="required" maxlength="4000"
														fieldname="SHENHE_YIJIAN" name="SHENHE_YIJIAN"></textarea>
												</td>
											</tr>
										</table>
									</form>
								</div></td>
						</tr>
					</tbody>
				</table>

				<!-- 项目 -->
				<table id="XM_TABLE" border="1" width="100%" cellpadding="0"
					cellspacing="0" class="content">
					<tbody>
						<tr>
							<td class="yw-title">
								<h4>项目信息&nbsp;<span id="xmlxMsg" style="color: red;font-size: 15px;"></span></h4></td>
						</tr>
						<tr>
							<td>
								<div class="overFlowX">

									<form method="post" id="projectsForm">
										<input id="FM_SHENHE_YIJIAN" fieldname="SHENHE_YIJIAN" hidden=""
											name="SHENHE_YIJIAN"> <input id="FM_SHENHE_JIEGUO" hidden=""
											fieldname="SHENHE_JIEGUO" name="SHENHE_JIEGUO" /> <input 
											type="hidden" id="PROJECT_UID" fieldname="PROJECT_UID"
											name="PROJECT_UID" /> <input type="hidden"
											id="JS_COMPANY_UID" fieldname="JS_COMPANY_UID"
											name="JS_COMPANY_UID" />
											<input type="hidden"
											id="UNTISS" fieldname="UNTISS"
											name="UNTISS" />
										<table class="B-table" width="100%">
											
											<tr id="SHYJBox">
												<th width="15%"
													class="right-border bottom-border text-right">项目名称&nbsp;<font id="fontId"
													style="color:red;font-size: 16px"><b>*</b> </font>
												</th>
												<td width="35%" class="right-border bottom-border" >
												<textarea style="width:99%"
														class="span12" rows="2" id="GONGCHENG_NAME"
														placeholder="必填" check-type="required" maxlength="4000"
														fieldname="GONGCHENG_NAME" name="GONGCHENG_NAME"></textarea>
														</td>
												<td width="50%" class="bottom-border right-border " id="map_div" colspan="2"
													rowspan="7"><img id="imgbddt" 
													src="http://api.map.baidu.com/staticimage?width=200&height=350&center=无锡新区&zoom=10&paths=&pathStyles=0x000fff,5,1,0xff0000" />
													<br /></td>
											</tr>
											<tr>
												<th width="15%"
													class="right-border bottom-border text-right">重点项目</th>
												<td class="bottom-border right-border"><label
													class="checkbox inline"> <input type="checkbox"
														name="SZDXM" id="SZDXM" fieldname="SZDXM" value="1" />
														市重点项目 </label> <label class="checkbox inline"> <input
														type="checkbox" name="QZDXM" id="QZDXM" fieldname="QZDXM"
														value="1" /> 区重点项目 </label></td>

											</tr>

											<tr>
												<th width="15%"
													class="right-border bottom-border text-right">项目建设地址&nbsp;<font id="fontId"
													style="color:red;font-size: 16px"><b>*</b> </font></th>
												<td class="right-border bottom-border" >
												<textarea style="width:99%"
														class="span12" rows="2" id="JIANSHE_DIZHI"
														placeholder="必填" check-type="required" maxlength="4000"
														fieldname="JIANSHE_DIZHI" name="JIANSHE_DIZHI"></textarea>
												</td>
											</tr>
											<tr>
												<th width="15%"
													class="right-border bottom-border text-right">项目级别&nbsp;<font id="fontId"
													style="color:red;font-size: 16px"><b>*</b> </font></th>
												<td class="right-border bottom-border" ><input
													class="span12" id="PROJECTS_LEVEL" type="radio" 
													check-type="required" kind="dic" src="PROJECTS_LEVEL"
													name="PROJECTS_LEVEL" fieldname="PROJECTS_LEVEL"></td>
											</tr>
											<tr>
												<th width="15%"
													class="right-border bottom-border text-right">项目性质&nbsp;<font id="fontId"
													style="color:red;font-size: 16px"><b>*</b> </font></th>
												<td class="right-border bottom-border"><input
													class="span12" id="PROJECTS_XINZHI" type="radio"
													check-type="required" kind="dic" src="PROJECTS_XINZHI"
													name="PROJECTS_XINZHI" fieldname="PROJECTS_XINZHI">
												</td>
											</tr>
											<tr>
												<th width="15%"
													class="right-border bottom-border text-right">投资性质&nbsp;<font id="fontId"
													style="color:red;font-size: 16px"><b>*</b> </font></th>
												<td class="right-border bottom-border"><select
													class="span12" style="width:99%" id="TOUZI_XINGZHI" placeholder="必填" check-type="required"
													type="text" fieldname="TOUZI_XINGZHI" kind="dic"
													src="PRO_TZXZ" name="TOUZI_XINGZHI">
												</select></td>
											</tr>
											<tr>
												<th width="15%"
													class="right-border bottom-border text-right">项目所在区域&nbsp;<font id="fontId"
													style="color:red;font-size: 16px"><b>*</b> </font></th>
												<td class="right-border bottom-border"><select
													class="span12" style="width:99%" kind="dic" src="QY" placeholder="必填" 
													name="QUYU" id="QUYU" check-type="required"
													fieldname="QUYU"></select></td>
											</tr>
											<tr>

												<th width="15%"
													class="right-border bottom-border text-right">建设类型&nbsp;<font id="fontId"
													style="color:red;font-size: 16px"><b>*</b> </font></th>
												<td class="right-border bottom-border">
													<div style="width: 30%;float: left;">
														<input id="JIANSHE_TYPE" onclick="jianshetypefun();"
															type="radio" check-type="required" kind="dic"
															src="JIANSHE_TYPE" name="JIANSHE_TYPE"
															fieldname="JIANSHE_TYPE">
													</div>
													<div id="SZ_SZLB_DIV" style="width: 50%;float: left;">
														( <input class="span12" id="XM_SZ_SZLB" type="radio"
															check-type="required" kind="dic" src="XM_FQ_SZLB"
															name="SZ_SZLB" fieldname="SZ_SZLB"> )
													</div></td>
												<th id="xmfqcdlb" width="15%"
													class="right-border bottom-border text-right">长度(米)&nbsp;<font id="fontId"
													style="color:red;font-size: 16px"><b>*</b> </font></th>
												<td id="xmfqcddiv" class="right-border bottom-border">
													<input class="span12 text-right" style="width:99%" placeholder="必填" check-type="required"
													id="XM_CHANGDU" maxlength="20" type="text" fieldname="CHANGDU"
													name="CHANGDU" /></td>
											</tr>
											<tr>

												<th width="15%"
													class="right-border bottom-border text-right">建筑面积(平方米)&nbsp;<font id="fontId"
													style="color:red;font-size: 16px"><b>*</b> </font></th>
												<td class="right-border bottom-border"><input
													class="span12 text-right" style="width:99%" id="JZMJ" placeholder="必填" check-type="required"
													maxlength="20" onkeydown="onlyNum()" type="text"
													fieldname="JZMJ" name="JZMJ" /></td>
												<th width="15%"
													class="right-border bottom-border text-right">总投资(元)&nbsp;<font id="fontId"
													style="color:red;font-size: 16px"><b>*</b> </font></th>
												<td class="right-border bottom-border"><input
													class="span12 text-right" style="width:99%" id="TOUZI" placeholder="必填" check-type="required"
													maxlength="20" onkeydown="onlyNum()" type="text"
													fieldname="TOUZI" name="TOUZI" /></td>
											</tr>
											<tr>

												<th width="15%"
													class="right-border bottom-border text-right">规划许可证号&nbsp;<font id="fontId"
													style="color:red;font-size: 16px"><b>*</b> </font></th>
												<td class="right-border bottom-border"><input
													class="span12 text-right" style="width:99%" placeholder="必填" check-type="required"
													id="GUIHUA_CODE" maxlength="20" type="text"
													fieldname="GUIHUA_CODE" name="GUIHUA_CODE" /></td>
												<th width="15%"
													class="right-border bottom-border text-right">规划许可证日期&nbsp;<font id="fontId"
													style="color:red;font-size: 16px"><b>*</b> </font></th>
												<td class="right-border bottom-border"><input
													style="width:95%" id="GUIHUA_DATE" type="text" placeholder="必填" check-type="required"
													fieldname="GUIHUA_DATE" name="GUIHUA_DATE" fieldtype="date"
													fieldformat="YYYY-MM-DD" class="Wdate"
													onClick="WdatePicker()" /></td>
											</tr>
											<tr>

												<th width="15%"
													class="right-border bottom-border text-right">拟开工日期&nbsp;<font id="fontId"
													style="color:red;font-size: 16px"><b>*</b> </font></th>
												<td class="right-border bottom-border"><input
													style="width:95%" id="PLAN_KG_DATE" type="text" placeholder="必填" check-type="required"
													fieldname="PLAN_KG_DATE" name="PLAN_KG_DATE"
													fieldtype="date" fieldformat="YYYY-MM-DD" class="Wdate"
													onClick="WdatePicker()" /></td>
												<th width="15%"
													class="right-border bottom-border text-right">预计竣工日期&nbsp;<font id="fontId"
													style="color:red;font-size: 16px"><b>*</b> </font></th>
												<td class="right-border bottom-border"><input
													style="width:95%" id="JG_DATE" type="text" placeholder="必填" check-type="required"
													fieldname="JG_DATE" name="JG_DATE" fieldtype="date"
													fieldformat="YYYY-MM-DD" class="Wdate"
													onClick="WdatePicker()" /></td>
											</tr>
											<tr>

												<th width="15%"
													class="right-border bottom-border text-right">预计投产日期&nbsp;<font id="fontId"
													style="color:red;font-size: 16px"><b>*</b> </font></th>
												<td class="right-border bottom-border"><input
													style="width:95%" id="TC_DATE" type="text" placeholder="必填" check-type="required"
													fieldname="TC_DATE" name="TC_DATE" fieldtype="date"
													fieldformat="YYYY-MM-DD" class="Wdate"
													onClick="WdatePicker()" /></td>
											</tr>
											<!-- 
			<tr>
				<th width="15%" class="right-border bottom-border text-right">项目区域经纬度</th>
				<td  class="right-border bottom-border">
					<input class="span12" style="width:99%" id="POSITION" type="text"  fieldname="POSITION" name = "POSITION"  />
				</td>
				
			</tr>
			 -->
											<tr>

												<th width="15%"
													class="right-border bottom-border text-right">负责人&nbsp;<font id="fontId"
													style="color:red;font-size: 16px"><b>*</b> </font></th>
												<td class="right-border bottom-border"><input
													class="span12" style="width:99%" id="FZR" type="text" placeholder="必填" check-type="required"
													fieldname="FZR" name="FZR" /></td>
												<th width="15%"
													class="right-border bottom-border text-right">负责人移动电话&nbsp;<font id="fontId"
													style="color:red;font-size: 16px"><b>*</b> </font></th>
												<td class="right-border bottom-border"><input
													class="span12" style="width:99%" id="FZR_MOBILE" placeholder="必填" check-type="required"
													maxlength="20" type="text" fieldname="FZR_MOBILE"
													name="FZR_MOBILE" /></td>
											</tr>
											<tr>

												<th width="15%"
													class="right-border bottom-border text-right">负责人身份证</th>
												<td class="right-border bottom-border"><input
													class="span12" style="width:99%" id="FZR_ID" type="text"
													maxlength="20" fieldname="FZR_ID" name="FZR_ID" /></td>
												<th width="15%"
													class="right-border bottom-border text-right">负责人固定电话</th>
												<td class="right-border bottom-border"><input
													class="span12" style="width:99%" id="FZR_PHONE" type="text"
													maxlength="20" fieldname="FZR_PHONE" name="FZR_PHONE" />
												</td>
											</tr>
											<tr>

												<th width="15%"
													class="right-border bottom-border text-right">传真</th>
												<td class="right-border bottom-border"><input
													class="span12" style="width:99%" id="FZR_FAX" type="text"
													maxlength="20" fieldname="FZR_FAX" name="FZR_FAX" /></td>
												<th width="15%"
													class="right-border bottom-border text-right">邮政编码</th>
												<td class="right-border bottom-border"><input
													class="span12" style="width:99%" id="POSTCODE" type="text"
													maxlength="20" fieldname="POSTCODE" name="POSTCODE" />
												</td>
											</tr>
											<tr>
												<th width="15%"
													class="right-border bottom-border text-right">负责人Email</th>
												<td class="right-border bottom-border"><input
													class="span12" style="width:99%" id="FZR_EMAIL" type="text"
													maxlength="20" fieldname="FZR_EMAIL" name="FZR_EMAIL" />
												</td>
											</tr>
										</table>

									</form>
								</div></td>
						</tr>
					</tbody>
				</table>
				
				
				<table id="SP_TABLE2" border="1" width="100%" cellpadding="0"
					cellspacing="0" class="content">
					<tbody>
						<tr>
							<td class="yw-title">
								<h4>立项信息</h4></td>
						</tr>
						<tr>
							<td>
								<div class="overFlowX">
									<form method="post" id="lxForm">
					<table class="B-table" width="100%" id="lx">
						<tr>
							<td class="right-border bottom-border">
								<div class="overFlowX">
									<table class="table-hover table-activeTd B-table"
										noprint="true" width="100%" id="lixangList" noPage="true"
										type="single" pageNum="10000">
										<thead>
											<tr>
											<th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
												<th fieldname="LIXIANG_CODE" width="15%" colindex=0
													tdalign="center">&nbsp;立项批文号&nbsp;</th>
												<th fieldname="LIXIANG_NAME" width="18%" colindex=1
													tdalign="center" CustomFunction="lxxx">&nbsp;立项名称&nbsp;</th>
												<th fieldname="TOUZI" width="10%" colindex=2
													tdalign="center">&nbsp;项目总投资（万元）&nbsp;</th>
												<th fieldname="ZDMJ" width="10%" colindex=3 tdalign="center">&nbsp;占地面积&nbsp;</th>
												<th fieldname="ZDMJ_UNIT" width="5%" colindex=4
													tdalign="center">&nbsp;占地面积单位&nbsp;</th>
												<th fieldname="JZMJ" width="10%" colindex=0 tdalign="center">&nbsp;建筑面积（万平方米）&nbsp;</th>
												<th fieldname="ZJLY" width="10%" colindex=1 tdalign="center">&nbsp;资金来源&nbsp;</th>
												<th fieldname="PFBM" width="10%" colindex=2 tdalign="center">&nbsp;立项批复部门&nbsp;</th>
												<th fieldname="PF_DATE" width="10%" colindex=4
													tdalign="center">&nbsp;批复日期&nbsp;</th>
											</tr>
										</thead>
										<tbody></tbody>
									</table>
								</div></td>
						</tr>
					</table>
				</form>
								</div></td>
						</tr>
					</tbody>
				</table>
				
				<table id="DWGC_TABLE" border="1" width="100%" cellpadding="0" style="display: none;"
					cellspacing="0" class="content">
					<tbody>
						<tr>
							<td class="yw-title">
								<h4>单位工程</h4></td>
						</tr>
						<tr>
							<td>
								<div class="overFlowX">
									<form method="post" id="lxForm">
					<table class="B-table" width="100%" id="lx">
						<tr>
							<td class="right-border bottom-border">
								<div class="overFlowX">
									<table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single" noPage="true"  pageNum="9999">
	                <thead>
	                	<tr>
	                		<th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
							<th fieldname="UNITS_UID" colindex=0 tdalign="center" style="display: none;" >&nbsp;单体建筑UID&nbsp;</th>
							<th fieldname="UNITS_NAME" colindex=12 tdalign="center" maxlength="30" >&nbsp;单体建筑名称&nbsp;</th>
							<%--<th fieldname="UNITS_CODE" colindex=13 tdalign="center" maxlength="30"  style="display: none;" >&nbsp;单体建筑代码&nbsp;</th>
							--%>
							<th fieldname="UNITS_TYPE_SV" colindex=14 tdalign="center" maxlength="30" >&nbsp;建筑物类型&nbsp;</th>
							<th fieldname="JGLX" colindex=15 tdalign="center" maxlength="30" >&nbsp;结构类型&nbsp;</th>
							<th fieldname="DJLX_SV" colindex=16 tdalign="center" maxlength="30" >&nbsp;地基类型&nbsp;</th>
							<th fieldname="JCLX_SV" colindex=17 tdalign="center" maxlength="30" >&nbsp;基础类型&nbsp;</th>
							<th fieldname="JZMJ" colindex=18 tdalign="center" >&nbsp;建筑面积（平方米）&nbsp;</th>
							<%--<th fieldname="GCZJ" colindex=19 tdalign="center"  style="display: none;">&nbsp;工程造价&nbsp;</th>
							<th fieldname="DSMJ" colindex=20 tdalign="center"  style="display: none;">&nbsp;地上面积&nbsp;</th>
							<th fieldname="DXMJ" colindex=21 tdalign="center" style="display: none;" >&nbsp;地下面积&nbsp;</th>
							<th fieldname="DSCS" colindex=22 tdalign="center"  style="display: none;">&nbsp;地上层数&nbsp;</th>
							<th fieldname="DXCS" colindex=23 tdalign="center" style="display: none;" >&nbsp;地下层数&nbsp;</th>
							<th fieldname="DSJG" colindex=24 tdalign="center" maxlength="30" style="display: none;" >&nbsp;地上结构类型&nbsp;</th>
							<th fieldname="DXJG" colindex=25 tdalign="center" maxlength="30" style="display: none;" >&nbsp;地下结构类型&nbsp;</th>
							<th fieldname="YCCS" colindex=26 tdalign="center" style="display: none;" >&nbsp;跃层层数&nbsp;</th>
							<th fieldname="CD" colindex=27 tdalign="center" style="display: none;" style="display: none;" >&nbsp;长度&nbsp;</th>
							<th fieldname="ZXBZ" colindex=28 tdalign="center" maxlength="30"  style="display: none;" >&nbsp;装修标准&nbsp;</th>
							--%><th fieldname="YT" colindex=29 tdalign="center" maxlength="30" >&nbsp;用途&nbsp;</th>
	                	</tr>
	                </thead>
	              	<tbody>
	              	</tbody>
	           </table>
								</div></td>
						</tr>
					</table>
				</form>
								</div></td>
						</tr>
					</tbody>
				</table>
				
				
				
			</div>
		</div>
	</div>
	<div align="center">
		<FORM name="frmPost" method="post" style="display:none"
			target="_blank">
			<!--系统保留定义区域-->
			<input type="hidden" name="queryXML" id="queryXML"> <input
				type="hidden" name="txtXML" id="txtXML"> <input
				type="hidden" name="txtFilter" order="desc" fieldname="CREATED_DATE"
				id="txtFilter" /> <input type="hidden" name="txtFilter"
				order="desc" fieldname="STATUS" id="txtFilter" />
			<%--<input type="hidden" name="txtFilter" order="desc" fieldname="PROJECTS_TYPE" id="txtFilter"/>
		 --%>
			<input type="hidden" name="txtFilter" order="desc"
				fieldname="PROJECTS_XINZHI" id="txtFilter" /> <input type="hidden"
				name="resultXML" id="resultXML">
			<!--传递行数据用的隐藏域-->
			<input type="hidden" name="rowData">

		</FORM>
	</div>
</body>
<script>
	
</script>
</html>