<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>企业信用评分</title>
<%
	String type=request.getParameter("type");
	String gcuid = request.getParameter("gcuid");
	String company_uid = request.getParameter("COMPANY_UID");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername = "${pageContext.request.contextPath }/yhzg/zgTzdController";
var controllername1 = "${pageContext.request.contextPath }/dtgl/xyfzController";
var controllernameScore = "${pageContext.request.contextPath }/yhzg/scoreController";
var controllernameXmjd = "${pageContext.request.contextPath }/yhzg/zgXmjdController";
var controllernameWgsj= "${pageContext.request.contextPath }/yhzg/zgWeiguiSjController";
var controllernameContent= "${pageContext.request.contextPath }/yhzg/zgContentController";
var controllernameBgMenu="${pageContext.request.contextPath }/bgMenuController/";
var type ="<%=type %>";
//页面初始化
$(function() {
	clearTitle();
	changeQuanXian();//权限判断
	initForm();
	//初始化默认时间
	initTime();
	init();
	$("#btnClose").bind("click", function(){
		window.close();
	});
	
	
});
//页面默认参数
function init(){
	//生成json串
	//var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT3);
	//调用ajax插入
	//defaultJson.doQueryJsonList(controllernameContent+"?query",data,DT3);
	//$('#FAFANG_DATE').val(getNowDate());
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm2,frmPost,DT3);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllernameScore+"?query1",data,DT3);
	
}
//清楚radio默认添加的title属性
function clearTitle(){
	$("#wgRadio").removeAttr("title");
	$("#bzRadio").removeAttr("title");
	$("#jnRadio").removeAttr("title");
	$("#lnRadio").removeAttr("title");
}
//初始化头部form值
function initForm(){
	//匹配数据库中的字段
	if(type == 'JS'){
		$("#COMPANY_UID").attr("fieldname","JS_COMPANY_UID");
	}else if(type == 'SG'){
		$("#COMPANY_UID").attr("fieldname","SG_COMPANY_UID");
	}else if(type == 'JL'){
		$("#COMPANY_UID").attr("fieldname","JL_COMPANY_UID");
	}
	//回显
	var data = combineQuery.getQueryCombineData(queryForm1, frmPost);
	var data1 = {
		msg : data
	};
	$.ajax({
		url : controllername1 + "?query&type=qiye&qiyeType="+type,
			data : data1,
			cache : false,
			async : false,
			dataType : "json",
			type : 'post',
			success : function(response) {
				$("#resultXML").val(response.msg);
				var resultobj = defaultJson.dealResultJson(response.msg);
				$("#COMPANY_NAME").text(resultobj.COMPANY_NAME);
				$("#COMPANY_CODE").text(resultobj.COMPANY_CODE);
				$("#SCORE").text(resultobj.SCORE);
				$("#ADDRESS").text(resultobj.ADDRESS);
				//$("#qiyeInfoForm").setFormValues(resultobj);
			}
	});
	calcXyfz();
}

function getNowDate(){
	var nowdate = new Date();
	var nowy = nowdate.getFullYear();
	var nowm = nowdate.getMonth()+1;
	var nowd = nowdate.getDate();
	if(nowm<10){
		nowm = "0"+nowm;
	}
	var datestr = nowy+"/"+nowm+"/"+nowd;
	return datestr;
}

function selectFun(demo){
	
	if(demo=='zt'){
		$('#XXJDT').show();
		$('#XXJD2').hide();
	}else{
		$('#XXJDT').hide();
		$('#XXJD2').show();
		var obj=$("#XXJD2");
		obj.attr("src","T#zg_xmjd: NAME as c:TITLE as t:PARENT ='"+demo+"'ORDER BY ID ");
		obj.attr("kind","dic");
		obj.html('');
		reloadSelectTableDic(obj);	
	}

}

function selectFun2(demo){
	if(demo!='wqzs'){
		$('#XXJD3').hide();
	}else{
		$('#XXJD3').show();
		var obj=$("#XXJD3");
		obj.attr("src","T#zg_xmjd: NAME as c:TITLE as t:PARENT ='"+demo+"'ORDER BY ID ");
		obj.attr("kind","dic");
		obj.html('');
		reloadSelectTableDic(obj);				
	}

}

function addEvent(){
	var tzdUid = $('#ZG_TZD_UID').val();
	alert("tzdUid1=="+tzdUid);
	if(tzdUid==""){
		$.ajax({
			url : controllername + "?queryUid",
			dataType : "json",
			type : 'post',
			success : function(response) {
				$('#ZG_TZD_UID').val(response.msg);				
			}
		});
	}

	window.open("wgsj-select.jsp");
}

function radioFun(demo){
	if(demo=='2'){
		$('#jbtgwz').show();
	}else{
		$('#jbtgwz').hide();
	}
}
//计算信用分值
function calcXyfz(){
	$.ajax({
		url : controllernameScore + "?calcXyf",
		data:{"id":$("#COMPANY_UID").val(),"type":type},
		dataType : "json",
		type : 'post',
		async : false,
		success : function(response) {	
			$("#SCORE").val(response.msg);
		}
	});
}
function doRandering(obj){
	var showHTML = "";
	if(obj.CHULI_TYPE == 'WG'){
		showHTML = "- "+obj.CHANGE_SCORE;
	}
	if(obj.CHULI_TYPE == 'BZ'){
		showHTML = "+ "+obj.CHANGE_SCORE;
	}
	return showHTML;
}
function doRandering1(obj){
	var showHtml = "";
	showHtml = "<a href='javascript:rowView1("+obj.SCORE_UID+");' title='删除'><i class='icon-remove' style='color:red;'></i></a>";
	return showHtml;
}
function rowView1(id){
	xConfirm("信息确认","确认要删除吗？");
		$('#ConfirmYesButton').attr('click','');
		$('#ConfirmYesButton').one("click",function(){
			$.ajax({
				url : controllernameScore + "?delete",
				data:{"id":id},
				dataType : "json",
				type : 'post',
				success : function(response) {
					xAlert("提醒信息","删除成功！");
					calcXyfz();
					//更新对应的表的SCORE字段的值
					$.ajax({
						url : controllername1 + "?updateScore",
						data:{"id":<%=company_uid%>,"score":$("#SCORE").val(),"qiyeType":type},
						dataType : "json",
						type : 'post',
						success : function(response) {
						}
					});
					init();
				}
			});
		});	
}
//更改单选框状态，刷新数据
function checkStatus(type){
	$("#CHULI_TYPE").val(type);
	init();
}
function checkYearStatus(type){
	if(type == 'JN'){
		initTime();
	}else if(type == 'LN') {
		$("#BDATE").val("");
		$("#EDATE").val("");	
	}
	init();
	
}
//单选框默认条件设置为当前年查询数据
function initTime(){
	var d = new Date();
	var year = d.getFullYear();
	$("#BDATE").val(year+"-01-01");
	$("#EDATE").val(year+"-12-31");
}
//验证用户是否有显示删除按钮的权限
function changeQuanXian(){
	//验证是否有权显示删除按钮
	$.ajax({
		url : controllernameBgMenu+"?getByMenuCodeAndUserId",
		data : {code : "3060004"},
		type :"post",
		async : false,
		dataType : "json",
		success : function(response){
			if(response.msg == "1"){
				$("#caoZuoCol").removeAttr("hidden");
			}else{
				$("#caoZuoCol").attr("hidden","");
			}
		}
	});
}
</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">
	<div style="height:5px;"></div>
	<div class="B-small-from-table-autoConcise">
     <form method="post" id="qiyeInfoForm"  >
      <table class="B-table" width="100%" >
     	 <input type="hidden" id="ZG_TZD_UID" fieldname="ZG_TZD_UID" name = "ZG_TZD_UID"/>
     	 <input type="hidden" id="GONGCHENG_UID" fieldname="GONGCHENG_UID" name = "GONGCHENG_UID" value="<%=gcuid %>"/>
        <div class="modal-header" style="background:#0866c6;">
			<h3 style="color:white;">企业信用评分
				<span class="pull-right">
					<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
	  			</span>
	  			</h3>
			</div>
         <tr>
			<th width="8%" class="right-border bottom-border text-right">企业名称：</th>
       		<td class="bottom-border right-border" width="15%" id="COMPANY_NAME" >

         	</td>
         	<th width="8%" class="right-border bottom-border text-right">组织机构代码：</th>
       		<td class="bottom-border right-border" width="15%" id="COMPANY_CODE">
         	</td>
         </tr>

        <tr>
        	<th width="8%" class="right-border bottom-border text-right">信用分值：</th>
       		<td class="bottom-border right-border" width="15%" id="SCORE">
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">地址：</th>
       		<td class="bottom-border right-border" width="15%" id="ADDRESS">
         		
         	</td>
         </tr>
      </table>
       <div style="height: 20px;"></div>
      		<div id="shjltxdivid" >
				<div class="modal-header" style="background:#0866c6;">
				<h3 style="color:white;"> 评定记录  
				</h3>
				</div>
				<table class="B-table" width="100%">
					<!--可以再此处加入hidden域作为过滤条件 -->
					 <tr>
			         	<td class="bottom-border right-border text-left" width="8%">
			         		<input type=radio value="" name="status" onclick="javascript:checkStatus(this.value)" checked="checked" >所有&nbsp;&nbsp;
			         		<input type=radio id="wgRadio" value="WG" name="status" onclick="javascript:checkStatus(this.value)"  >违规违章&nbsp;&nbsp;
			         		<input type=radio id="bzRadio" value="BZ" name="status" onclick="javascript:checkStatus(this.value)" >优秀表彰
			         	</td>
			         	<td class="bottom-border right-border text-left" width="8%" >
			         		<span style="margin-left: 40px;"></span>
			         		<input type=radio id="jnRadio" value="JN" name="YearStatus" onclick="javascript:checkYearStatus(this.value)" checked="checked" >今年记录&nbsp;&nbsp;
			         		<input type=radio id="lnRadio" value="LN" name="YearStatus" onclick="javascript:checkYearStatus(this.value)"  >历年记录
			         	</td>
			         	<td class="bottom-border right-border" width="8%" colspan="2"></td>
        			 </tr>
				</table>
      			<table class="table-hover table-activeTd B-table" width="100%" id="DT3" type="single"  noPage=true >
					<thead>
						<tr>    
							<th  name="XH" id="_XH" style="width:10px" colindex=0 tdalign="center">&nbsp;#&nbsp;</th>
		                	<th fieldname="PROJECT_NAME" colindex=1 tdalign="center" width="15%">&nbsp;项目名称&nbsp;</th>
							<th fieldname="CHANGE_SCORE" colindex=2 tdalign="center"  width="5%" maxlength="30" CustomFunction="doRandering" >&nbsp;分数/限制 &nbsp;</th>
							<th fieldname="YUANYIN" colindex=3 tdalign="center" width="40%" >&nbsp;原因 &nbsp;</th>
							<th fieldname="CHULI_TYPE" colindex=4 tdalign="center"  maxlength="30" hidden >&nbsp;处理类型 &nbsp;</th>
							<th fieldname="SHIJIAN_TYPE" colindex=5 tdalign="center"   maxlength="30"  >&nbsp;评定事件&nbsp;</th>
							<th fieldname="CREATE_DATE" colindex=6 tdalign="center"   >&nbsp;日期 &nbsp;</th>						
							<th id="caoZuoCol" fieldname="SCORE_UID" colindex=7 tdalign="center" CustomFunction="doRandering1" width="10%">&nbsp;操作 &nbsp;</th>	
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
      </form>
    </div>
   </div>
  </div>
  <form method="post" id="queryForm"  >
    	  <input type="hidden" id="ZG_TZD_UID" fieldname="t.zg_tzd_uid" name = "ZG_TZD_UID" operation="="/>
  </form>
  <form method="post" id="queryForm1"  >
        <input type="hidden" id="COMPANY_UID" fieldname="" name = "COMPANY_UID" value="<%=company_uid %>" operation="="/>
  </form>
  <form method="post" id="queryForm2"  >
        <input type="hidden" id="CHULI_UID" fieldname="CHULI_UID" name = "CHULI_UID" value="<%=company_uid %>" operation="="/>
        <input type="hidden" id="CHULI_DUIXIANG" fieldname="CHULI_DUIXIANG" name = "CHULI_DUIXIANG" value="<%=type %>" operation="="/>
        <input type="hidden" id="CHULI_TYPE" fieldname="CHULI_TYPE" name ="CHULI_TYPE" value="" operation="="/>
        <input type="hidden" fieldtype="date" id="BDATE" fieldname="CREATE_DATE" name = "CREATE_DATE" value="" operation=">="/>
        <input type="hidden" fieldtype="date" id="EDATE" fieldname="CREATE_DATE" name = "CREATE_DATE" value="" operation="<="/>
  </form>
  <div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id = "queryXML">
         <input type="hidden" name="txtXML" id = "txtXML">
         <input type="hidden" name="resultXML" id = "resultXML">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
		
 	</FORM>
 </div>
</body>
<script type="text/javascript" charset="utf-8">
function loadWgsj(str){
	var tzdUid = $('#ZG_TZD_UID').val();
	$('#ZG_WEIGUI_SJ_UID').val(str);
	var gongchenguid = $('#GONGCHENG_UID').val();
	var data = "{'tzdUid':'"+tzdUid+"','wgsjUidstr':'"+str+"','gongcheng_uid':'"+gongchenguid+"'}";
	//var data = Form2Json.formToJSON(yxGcTypeForm);
	var data1 = defaultJson.packSaveJson(data);
	$.ajax({
		url : controllernameContent + "?insert",
		data:data1,
		dataType : "json",
		type : 'post',
		success : function(response) {
			//init();		
		}
	});
}
</script>
</html>