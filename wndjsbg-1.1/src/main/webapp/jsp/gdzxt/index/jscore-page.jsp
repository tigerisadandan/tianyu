<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>需审核加分列表</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/yhzg/zgTzdController";

var controllernameContent= "${pageContext.request.contextPath }/yhzg/zgContentController";
//页面初始化
$(function() {
	init();
	setDate();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {
		var zt = $('input[name="zt"]:checked').val();
		$('#status').val(zt);
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"?query",data,DT1);
	});
	
	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#queryForm").clearFormResult();
        $("#btnQuery").click();  
    });
	
});

//页面默认参数
function init(){
	//var zt = $('input[name="zt"]:checked').val();
	//$('#status').val(zt);
	//$('#gongcheng_uid').val(parent.document.getElementById("GDZXT_XM_ID").value);
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?querySh",data,DT1);

}

function setDate(){
	var nowdate = new Date();
	var nowy = nowdate.getFullYear();
	var nowm = nowdate.getMonth()+1;
	var nowd = nowdate.getDate();
	
	var bnowm = nowm;
	if(bnowm<10){
		bnowm = "0"+bnowm;
	}
	var datestr = nowy+"/"+bnowm+"/"+nowd;
	var enowm = nowm;
	if(nowm-6<=0){
		enowm = nowm+12-6;
		nowy = nowy-1;
	}else{
		enowm = nowm-6;
	}

	if(enowm<10){
		enowm = "0"+enowm;
	}
	
	var datestr2 = nowy+"/"+enowm+"/"+nowd;

	$( "#B_DATE" ).val(datestr2);
	$( "#E_DATE" ).val(datestr);
}

function doQuery(){
	$("#btnQuery").click();
}

function rowSh(obj){

	while(obj.tagName.toLowerCase() != "tr"){
	    obj = obj.parentNode;
	    if(obj.tagName.toLowerCase() == "table")return null;
	   }
	obj.click();   
	var data = $("#DT1").getSelectedRow();
	//$("#resultXML").val(data);
	var tempJson = convertJson.string2json1(data);
	var bguid = tempJson.BGUID;
	var gcuid = tempJson.GONGCHENG_UID;
	$(window).manhuaDialog({"title":"隐患整改>全面停工审核","type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/index/jscore-sh.jsp","modal":"2"});
}


function caozuoFun(obj){
	var showHtml="";
	showHtml +="<span class='btn btn-link' id='addSpan' onclick='rowSh(this);' title='审核' >";
	showHtml +="<i class='icon-file'></i> ";
	showHtml +="</span>";
	return showHtml;
}

function ztFun(obj){
	var str = "";
	var status = obj.STATUS;
	var spStatus = obj.SHENPI_STATUS;
	//审批状态：1－一级待审（安质监待审）；2－二级待审（招标办待审）；3－三级待审（主管部门待审）
	if(spStatus=='1'){
		str+="安质监";
	}else if(spStatus=='2'){
		str+="招标办";
	}else if(spStatus=='3'){
		str+="主管部门";
	}

	if(status=='0'){
		str+="待审核";
	}else if(status=='1'){
		str = "审核完成";
	}else if(status=='-1'){
		str+="退回";
	}
	return str;
}


function queryContent(obj){
	var content = "";
	var tzdUid = obj.ZG_TZD_UID;
	$.ajax({
		url : controllernameContent+"?getContent&tzdUid="+tzdUid,
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var obj1 = eval('('+response.msg+')');
			var v = obj1.CONTENT;
			if(v!=undefined){
				content = "<p>"+v+"</p>";
			}else{
				content = "<p></p>";
			}
		}
	});

	return content;	
}


function getPicNum(obj){
	var content = "";
	var tzdUid = obj.ZG_TZD_UID;
	$.ajax({
		url : controllernameContent+"?getPicNum&tzdUid="+tzdUid,
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var v = response.msg;
			if(v!=0){
				content = "<p><a href=''>"+v+"</a></p>";
			}else{
				content = "<p>"+v+"</p>";
			}
		}
	});

	return content;	
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
<div id="menuiframe"></div>
<div class="container-fluid">
	<p></p>
	<div class="row-fluid">
		<div class="B-small-from-table-autoConcise">

			<form method="post" id="queryForm">
				<table class="B-table" width="100%">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<TR style="display: none;">
						<TD class="right-border bottom-border"></TD>
						<TD class="right-border bottom-border">
							<INPUT type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>
							<INPUT type="hidden" class="span12"   fieldname="e.status" operation="=" value="1"/>
							<INPUT type="hidden" class="span12"   fieldname="se.status" operation="=" value="1"/>
							<INPUT type="hidden" class="span12"   fieldname="tzd.ZG_XINGZHI_UID" operation="=" value="3"/>
						</TD>
					</TR>
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr>
						<th width="3%" class="right-border bottom-border text-right">审核状态</th>
						<td class="right-border bottom-border" width="15%">
							<input type="radio" name="zt" checked="checked" value="0" onclick="doQuery();">待审核
							<input type="radio" name="zt" value="1" onclick="doQuery();">已通过
							<input type="radio" name="zt" value="1" onclick="doQuery();">已退回
						</td>
						<th width="3%" class="right-border bottom-border text-right">扣分对象类型</th>
						<td class="right-border bottom-border" width="16%">
							<input type="radio" name="lx" value="1" onclick="doQuery();">企业
							<input type="radio" name="lx" checked="checked" value="1" onclick="doQuery();">个人
						</td>
						<th width="3%" class="right-border bottom-border text-right">安检组</th>	
						<td class="right-border bottom-border" width="16%">
							<select >
								<option value="">--请选择安检组--</option>
								<option value="1">监督一组</option>
								<option value="2">监督二组</option>
							</select>
						</td>	
						<th width="3%" class="right-border bottom-border text-right">整改单编号</th>
						<td>
							<input type="text"  />
						</td>
			            	<th width="3%" class="right-border bottom-border text-right">扣分对象</th>
						<td>
							<input type="text"  />
						</td>	
					</tr>
					<tr>


						<th width="3%" class="right-border bottom-border text-right">工程名称</th>
						<td>
							<input type="text"  />
						</td>
						<th width="3%" class="right-border bottom-border text-right">日期</th>
						<td class="right-border bottom-border" width="25%" colspan="3">
							<input id="B_DATE" class="Wdate" type="text" onclick="WdatePicker({dateFmt:'yyyy/MM/dd'})" style="width:35%" field=""  name="B_DATE" fieldname="B_DATE" >
							~<input id="E_DATE" class="Wdate" type="text" onclick="WdatePicker({dateFmt:'yyyy/MM/dd'})" style="width:35%" field=""  name="E_DATE" fieldname="E_DATE" >
						</td>	
					
						<td class="text-left" width="11%" colspan="4">
	                        <button id="btnQuery" class="btn btn-link"  type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-search"></i>查询</button>
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
	                		<th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
	                		<th fieldname="ZG_TZD_UID" colindex=0 tdalign="center"  CustomFunction="caozuoFun" >&nbsp;&nbsp;</th>
							<th fieldname="PROJECTS_NAME" colindex=2 tdalign="center" maxlength="30" >&nbsp;打印&nbsp;</th>
							<th fieldname="SGDW" colindex=2 tdalign="center" maxlength="30">&nbsp;审核状态&nbsp;</th>
							<th fieldname="JLDW" colindex=2 tdalign="center" maxlength="30">&nbsp;申请单位&nbsp;</th>
							<th fieldname="ZGXZ" colindex=3 tdalign="center" maxlength="30"> &nbsp;工程名称&nbsp;</th>
							<th fieldname="SGDW_KOUFEN" colindex=2 tdalign="center" maxlength="30">&nbsp;申请原因&nbsp;</th>
							<th fieldname="XMJL_KOUFEN" colindex=2 tdalign="center" maxlength="30" >&nbsp;加分对象&nbsp;</th>
							<th fieldname="XMJL_KOUFEN" colindex=2 tdalign="center" maxlength="30" >&nbsp;日期&nbsp;</th>
							<th fieldname="JLDW_KOUFEN" colindex=2 tdalign="center" maxlength="30" >&nbsp;审核&nbsp;</th>
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
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>
