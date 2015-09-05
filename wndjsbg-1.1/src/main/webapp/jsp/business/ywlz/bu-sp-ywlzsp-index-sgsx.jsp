<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>审批业务材料库首页</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">

//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/ywlz/buSpYwlzController";
var controllerywxxname= "${pageContext.request.contextPath }/spxx/buSpYwxxController";

//页面初始化
$(function() {
    getywlx();
	init();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {
		 var status=$("input[name='status']:checked").val();
		 $("#STATUS_ID").val(status);	
		 if(status=='0'){
				// alert(status);
			$("#sjjssjid").hide();				
		}else{
		$("#ywlzbz").hide();		
			
		}
		var spyw_uid_res=$("#SPYW_UID").val();	
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"?query&spyw_uid_res="+spyw_uid_res,data,DT1);
		getCount();
	});

	//按钮绑定事件(审批)
	$("#btnSh").click(function() {
		if($("#DT1").getSelectedRowIndex()==-1){
			requireSelectedOneRow();
		    return
		 }
		$("#resultXML").val($("#DT1").getSelectedRow());
		$(window).manhuaDialog({"title":"审批业务流转>审批","type":"text","content":"${pageContext.request.contextPath }/jsp/business/ywlz/bu-sp-ywlzsp-sp.jsp?type=ywlzsh","modal":"1"});
	});

	//按钮绑定事件(核发)
	$("#btnHf").click(function() {
		//alert("----");
		if($("#DT1").getSelectedRowIndex()==-1){
			
			requireSelectedOneRow();
		    return
		 }
		$("#resultXML").val($("#DT1").getSelectedRow());
		$(window).manhuaDialog({"title":"审批业务流转>材料核发","type":"text","content":"${pageContext.request.contextPath }/jsp/business/ywlz/bu-sp-ywlzsp-sp.jsp?type=ywclhf","modal":"1"});
	});

	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#queryForm").clearFormResult();
   //     tempradio.checked=false;  
   		$("#STATUS").val("40");
   		$("#STATUS_ID").val("0");
   		$("#num").val("1000");
   		$("input:radio[name='status']")[0].checked=true;
        $("input:radio[name='status']")[1].checked=false;
        $("input:radio[name='status']")[2].checked=false;
        $("#btnQuery").click();  
    });
	
});


function getywlx(){
	$.ajax({
				url : controllerywxxname+"/queryYWLX?SPYW_UID="+${ywid},
			    data : null,
				cache : false,
				async : false,
				dataType : "json",
				type : 'post',
				success : function(response) {
				var res = dealSpecialCharactor(response.msg);
			var resultobj = defaultJson.dealResultJson(res);		
			if(resultobj.SPYWLX=="QY"){
			$("#xiangmu").hide();
			}
            
		}
		});

}
//页面默认参数
//页面默认参数
function init(){

	$("#sjjssjid").hide();	
//	alert(${ywid});
	$("#SPYW_UID").val(${ywid});
	
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?query&spyw_uid_res="+${ywid},data,DT1);
	getCount();

}
function ywlzbz (obj){
//alert(obj.YWLZ_UID);
var html="";
$.ajax({
				url : "${pageContext.request.contextPath}/ywlz/buSpYwlzBzController?getYwLzBzVoNoUser&ywlzuid="+obj.YWLZ_UID,
			//	data : data1,
				cache : false,
				async : false,
				dataType : "json",
				type : 'post',
				success : function(response) {
				var resultdata = dealSpecialCharactor(response.msg);
					var res = convertJson.string2json1(resultdata);
					var BZMC=res.BZMC;
					 html=""+BZMC;
				
				}
				})
	return html;
}

function checkStatus(obj){
	 var status=$("input[name='status']:checked").val();
	 $("#STATUS_ID").val(status);

	 if(status=='0'){
			// alert(status);
		$("#sjjssjid").hide();			
				
			$("#ywlzbz").show();			
	}else{
		$("#sjjssjid").show();			
				
			$("#ywlzbz").hide();		
	}	
	var spyw_uid_res=$("#SPYW_UID").val();
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?query&spyw_uid_res="+spyw_uid_res,data,DT1);	

}

//回调函数--用于修改新增
getWinData = function(data){
	//var subresultmsgobj = defaultJson.dealResultJson(data);
	var data1 = defaultJson.packSaveJson(data);
	if(JSON.parse(data).ID == "" || JSON.parse(data).ID == null){
		defaultJson.doInsertJson(controllername + "?insert", data1,DT1);
	}else{
		defaultJson.doUpdateJson(controllername + "?update", data1,DT1);
	}

	
};


//详细信息
function rowView(obj,spywid){
	while(obj.tagName.toLowerCase() != "tr"){
	    obj = obj.parentNode;
	    if(obj.tagName.toLowerCase() == "table")return null;
	   }
	obj.click();
	var data = $("#DT1").getSelectedRow();
	$("#resultXML").val(data);

	var tempJson = convertJson.string2json1(data);

	var spywlx=tempJson.SPYWLX;
    var title1="实例业务流转>详细信息";

	if(spywlx=="QY"){
		title1="企业事务>详细信息";
	}
	if(spywid=="119"){
		$(window).manhuaDialog({"title":title1,"type":"text","content":"${pageContext.request.contextPath }/jsp/business/ywlz/ywlz-single-add-sgsx.jsp?spyw_uid="+spywid,"modal":"2"});
	}else if(spywid=="117"){
		$(window).manhuaDialog({"title":title1,"type":"text","content":"${pageContext.request.contextPath }/jsp/business/ywlz/ywlz-single-add-kgaqfc.jsp?spyw_uid="+spywid,"modal":"2"});
	}else{
	$(window).manhuaDialog({"title":title1,"type":"text","content":"${pageContext.request.contextPath }/jsp/business/ywlz/ywlz-single-add.jsp?spyw_uid="+spywid,"modal":"2"});
	}
}


function rowSh(obj,spywid){
	while(obj.tagName.toLowerCase() != "tr"){
	    obj = obj.parentNode;
	    if(obj.tagName.toLowerCase() == "table")return null;
	   }
	obj.click();
	var data = $("#DT1").getSelectedRow();
	$("#resultXML").val(data);
	var tempJson = convertJson.string2json1(data);

	var spywlx=tempJson.SPYWLX;
    var title1="审批业务流转>审批";

	if(spywlx=="SG"){
		title1="施工手续>审批";
	}
	
	if(spywid=="119"){
		$(window).manhuaDialog({"title":title1,"type":"text","content":"${pageContext.request.contextPath }/jsp/business/ywlz/bu-sp-ywlzsp-sp-sgsx.jsp?type=ywlzsh&spyw_uid="+spywid,"modal":"1"});
	}else if(spywid=="117"){
		$(window).manhuaDialog({"title":title1,"type":"text","content":"${pageContext.request.contextPath }/jsp/business/ywlz/bu-sp-ywlzsp-sp-kgaqfc.jsp?type=ywlzsh&spyw_uid="+spywid,"modal":"1"});
	}else{
	$(window).manhuaDialog({"title":title1,"type":"text","content":"${pageContext.request.contextPath }/jsp/business/ywlz/bu-sp-ywlzsp-sp-updateTb.jsp?type=ywlzsh&spyw_uid="+spywid,"modal":"1"});
	}
}

function xiangmu(obj){
var html="";
html += "<a href='javascript:void(0)'  onclick=\"showSpywXx('"+obj.PROJECTS_UID+"')\">"+obj.PROJECTS_NAME+"</a>";
if(obj.JS_COMPANY_UID==""){
	html=obj.GONGCHENG_NAME;
}
return html;
}


function ssgs(obj){
if(obj.JS_COMPANY_UID==""){
	html=obj.SG_COMPANY_NAME;
}
return html;
}

function showSpywXx(PROJECTS_UID){
	window.open("${pageContext.request.contextPath }/jsp/business/ywlz/project-allspyw-view.jsp?PROJECTS_UID="+PROJECTS_UID,'项目业务信息');
}

function caozuoFun(obj){
	var status=obj.STATUS;
	var showHtml="";
	if(status=='0'){
		showHtml +="<span class='btn btn-link' id='addSpan' onclick='rowSh(this,\""+obj.SPYW_UID+"\");' title='审核' >";
		showHtml +="<i class='icon-file'></i> ";
		showHtml +="</span>";
		
	//	 showHtml += "<a href='javascript:rowSh("+obj.YWLZ_UID+","+obj.SPYW_UID+","+obj.PROJECTS_UID+");' title='审核'><i class='icon-file'></i></a>";	
	}else{
		showHtml +="<span class='btn btn-link' id='addSpan' onclick='rowView(this,\""+obj.SPYW_UID+"\");' title='查看' >";
		showHtml +="<i class='icon-file'></i> ";
		showHtml +="</span>";
//	    showHtml += "<a href='javascript:rowView("+obj.YWLZ_UID+","+obj.SPYW_UID+","+obj.PROJECTS_UID+");' title='查看'><i class='icon-file'></i></a>";
	}

	var hfclcount=obj.HFCLCOUNT;//不等于0表示该记录存在核发材料
	if(status=="1" &&hfclcount!='0'){
		showHtml +="<span class='btn btn-link' id='hfSpan' onclick='rowHf(this);' title='核发' >";
		showHtml +="<i class='icon-edit'></i> ";
		showHtml +="</span>";		
		//showHtml += "<a href='javascript:rowHf("+obj.YWLZ_UID+","+obj.SPYW_UID+","+obj.PROJECTS_UID+");' title='核发'><i class='icon-file'></i></a>";
	}
	return showHtml;
}

function rowHf(obj){
	while(obj.tagName.toLowerCase() != "tr"){
	    obj = obj.parentNode;
	    if(obj.tagName.toLowerCase() == "table")return null;
	   }
	obj.click();
	var data = $("#DT1").getSelectedRow();

	$("#resultXML").val(data);

	var tempJson = convertJson.string2json1(data);

	var spywlx=tempJson.SPYWLX;
    var title1="审批业务流转>材料核发";

	if(spywlx=="QY"){
		title1="企业事务>材料核发";
	}
	
	$(window).manhuaDialog({"title":title1,"type":"text","content":"${pageContext.request.contextPath }/jsp/business/ywlz/bu-sp-ywlzsp-sp.jsp?type=ywclhf&spyw_uid="+${ywid},"modal":"1"});
	
}


//点击获取行对象
function tr_click(obj,tabListid){
	//alert(obj.STATUS);
	if(obj.STATUS=="0"){
		$("#btnSh").show();
		$("#btnHf").hide();
	}else if(obj.STATUS=="1"){
		$("#btnSh").hide();
		$("#btnHf").show();
	}else{
		$("#btnSh").hide();
		$("#btnHf").hide();
	}
}


function getCount(){
	var spywuid=$("#SPYW_UID").val();
	$.ajax({
		url : controllername+"?getSpCount&spywuid="+spywuid,
	//	data : data1,
		cache : false,
		async : false,
		dataType : "json",
		type : 'post',
		success : function(response) {
			var resultdata = dealSpecialCharactor(response.msg);
			var res = convertJson.string2json1(resultdata);
			//$("h").empty();	
			$("#dsid").empty();
			$("#ysid").empty();
			$("#thid").empty();	
		    var dsh = document.createElement("h");			  
		    dsh.innerHTML = " (<font color=\"red\">"+res.DSZ+"</font>)";				
			document.getElementById("dsid").appendChild(dsh);

			var ysh = document.createElement("h");			  
			ysh.innerHTML = " (<font color=\"red\">"+res.YTG+"</font>)";				
			document.getElementById("ysid").appendChild(ysh);

		    var thh = document.createElement("h");			  
		    thh.innerHTML = " (<font color=\"red\">"+res.YTH+"</font>)";				
			document.getElementById("thid").appendChild(thh);	
		}
	});
	
}


//回车事件
function enterSumbit(){  
  var event=arguments.callee.caller.arguments[0]||window.event;//消除浏览器差异  
 if (event.keyCode == 13){  
	   $("#btnQuery").click();  
 }  
}  

</script>
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
	<p></p>
	<div class="row-fluid">
		<div class="B-small-from-table-autoConcise">
		<!-- 
			<h4 class="title">
				业务流程审批
				<span class="pull-right">  
					<button id="btnSh" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">审批</button> 
				 <button id="btnHf" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">核发</button>	    				      		   
				 </span>
			</h4> -->
			
			<form method="post" id="queryYwxxForm">
			<INPUT type="hidden"  id="SPYWID" fieldname="SPYW_UID"  operation="="/>
			</form>
			<form method="post" id="queryForm">
				<table class="B-table" width="100%">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<TR style="display: none;">
						<TD class="right-border bottom-border"></TD>
						<TD class="right-border bottom-border">
							<INPUT type="text" class="span12" kind="text" id="STATUS" fieldname="STATUS" value="40" operation="!="/>
							<INPUT type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>
							<INPUT type="text" class="span12" id="SPYW_UID" name="SPYW_UID"/>
							<INPUT type="text" class="span12" kind="text" id="STATUS_ID" fieldname="STATUS" value="0"  operation="="/>
						</TD>
					</TR>
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr>	
						<th width="5%" class="right-border bottom-border text-right">审批状态</th>
						<td width="25%" class="right-border bottom-border">
						<nobr>	
							 <input type=radio value="0" name="status" onclick="javascript:checkStatus(this)" checked="checked"  >待审<h id="dsid"></h>&nbsp;&nbsp;
      						 <input type=radio value="1" name="status" onclick="javascript:checkStatus(this)">已审<h id="ysid"></h>&nbsp;&nbsp;
      						 <input type=radio value="-1" name="status" onclick="javascript:checkStatus(this)">退回<h id="thid" ></h>
						</nobr>
						</td>
						<th width="5%" class="right-border bottom-border text-right">公司名称</th>
						<td class="right-border bottom-border" width="20%">
							<input class="span12" type="text" onkeydown="enterSumbit()" id="QCOMPANY_NAME" name="SG_COMPANY_NAME" fieldname="SG_COMPANY_NAME" operation="like" >
						</td>
						<th width="5%" class="right-border bottom-border text-right">项目名称</th>
						<td class="right-border bottom-border" width="20%">
							<input class="span12" type="text" onkeydown="enterSumbit()" id="QPROJECTS_NAME" name="GONGCHENG_NAME" fieldname="GONGCHENG_NAME" operation="like" >
						</td>
			            <td class="text-left bottom-border text-right">
	                        <button id="btnQuery" class="btn btn-link"  type="button" m style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-search"></i>查询</button>
           					<button id="btnClear" class="btn btn-link" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-trash"></i>清空</button>
			            </td>							
					</tr>
					
				</table>
			</form>
			<div style="height:5px;"> </div>	
			<div class="overFlowX">
	            <table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single"  pageNum="18">
	                <thead>
	                	<tr>
	                	    <th fieldname="SPYW_UID" colindex=5 tdalign="center" hidden></th>
	                	    <th fieldname="GONGCHENG_NAME" colindex=5 tdalign="center" hidden></th>
	                	    <th fieldname="SG_COMPANY_NAME" colindex=5 tdalign="center" hidden></th>
	                	    <th fieldname="JS_COMPANY_UID" colindex=5 tdalign="center" hidden></th>
	                		<th  name="XH" id="_XH" style="width:10px" colindex=0 tdalign="center">&nbsp;#&nbsp;</th>
	                		<th fieldname="YWLZ_UID" colindex=1 tdalign="center"  style="width:10px" CustomFunction="caozuoFun"  >&nbsp;&nbsp;</th>
	                		<th id="ywlzbz" fieldname="YWLZ_UID" colindex=2 tdalign="center"  maxlength="6"  CustomFunction="ywlzbz" >&nbsp;当前步骤&nbsp;</th>	                		
	                		<th fieldname="ZSTATUS" colindex=3 tdalign="center" >&nbsp;流转状态&nbsp;</th>
	                		<th fieldname="PROJECTS_NAME" id="xiangmu"  colindex=4 tdalign="center" maxlength="30" CustomFunction="xiangmu"  >&nbsp;项目&nbsp;</th>
							<th fieldname="COMPANY_NAME" colindex=5 tdalign="center"  CustomFunction="ssgs">&nbsp;所属公司&nbsp;</th>
	
							<th fieldname="CISHU" colindex=6 tdalign="center" >&nbsp;申请次数&nbsp;</th>
							<th fieldname="BEGIN_DATE_SV" colindex=7 tdalign="center" >&nbsp;开始时间&nbsp;</th>
							<th fieldname="PLAN_END_DATE_SV" colindex=8 tdalign="center" >&nbsp;计划结束时间&nbsp;</th>
							<th id="sjjssjid" fieldname="ACT_END_DATE_SV" colindex=9 tdalign="center" >&nbsp;实际结束时间&nbsp;</th>
	                	</tr>
	                </thead>
	              	<tbody></tbody>
	           </table>
	       </div>
	 	</div>
	</div>     
</div>
<div align="center">
	<FORM name="frmPost" method="post" style="display: none" target="_blank">
		<!--系统保留定义区域-->
		<input type="hidden" name="queryXML"/> 
		<input type="hidden" name="txtXML"/>
		<input type="hidden" name="txtFilter" order="desc" fieldname="BEGIN_DATE" id="txtFilter"/>
		<input type="hidden" name="txtFilter" order="desc" fieldname="STATUS" id="txtFilter"/>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>