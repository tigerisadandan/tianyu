<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>安装人员首页</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/gongcheng/projectsGongchengController";
var statustj="";
//页面初始化
$(function() {
	init();
	$("#page_DT1").hide();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {
		$('input[name="status"]:checked').each(function(i){
			if(i==0){
				statustj+=$(this).val();
			}else{
			statustj+=","+$(this).val();
			}
			
			});
		
		//工程状态是否一个都未选
		if(statustj.trim()==""||statustj==null){
			alert("请至少选择一种工程状态(未开工，在建，完工，竣工)");
			return;
		}
		
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		
		var AJ_uid=$("#AJ").val();
		var ZJ_uid=$("#ZJ").val();
		var order=$("#order").val();		
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"?query2&status="+statustj+"&AJ_uid="+AJ_uid+"&ZJ_uid="+ZJ_uid+"&order="+order,data,DT1);

		statustj="";
		$("#page_DT1").hide();
	});
	
	//按钮绑定事件(修改)
	$("#btnUpdate").click(function() {
		if($("#DT1").getSelectedRowIndex()==-1)
		 {
			requireSelectedOneRow();
		    return
		 }
		$("#resultXML").val($("#DT1").getSelectedRow());
		$(window).manhuaDialog({"title":"建设单位>审核","type":"text","content":"${pageContext.request.contextPath }/jsp/business/project/js-company-add.jsp?type=update","modal":"2"});
	});
	//按钮绑定事件（导出EXCEL）
	/**	$("#btnExpExcel").click(function() {
		 var t = $("#DT1").getTableRows();
		 if(t<=0)
		 {
			 xAlert("提示信息","请至少查询出一条记录！");
			 return;
		 }
	  	 $(window).manhuaDialog({"title":"导出","type":"text","content":"${pageContext.request.contextPath }/jsp/framework/print/TabListEXP.jsp?tabId=DT1","modal":"3"});
	});**/
	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#queryForm").clearFormResult();
        $("#num").val("1000");
        $("input[name=status]").eq(0).prop("checked",false);
        $("input[name=status]").eq(1).prop("checked",true);
        $("input[name=status]").eq(2).prop("checked",false);
        $("input[name=status]").eq(3).prop("checked",false);
        $("#btnQuery").click();  
    });
	
});

//页面默认参数
function init(){
	getAJY();
	getZJY();
	$("#shrid").hide();			
	$("#shsjid").hide();
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?query2&status=0",data,DT1);
	//getCount();	
}

//获取安监员和质监员信息
function getAJY(){
	$.ajax({
		url : controllername+"?getAJY",
		cache : false,
		async : false,
		dataType : "json",
		type : 'post',
		success : function(json) {
			var res = dealSpecialCharactor(json.msg);
			var resultmsgobj = convertJson.string2json1(res);
			var datslist=resultmsgobj.response.data;
			var selectObj=document.getElementById('AJ');
			$.each(datslist, function() {    
				selectObj.options.add(new Option(this.ORG_NAME,this.USER_UID));				
			});	
		}
	});
}

//获取安监员和质监员信息
function getZJY(){
	$.ajax({
		url : controllername+"?getZJY",
		cache : false,
		async : false,
		dataType : "json",
		type : 'post',
		success : function(json) {
			var res = dealSpecialCharactor(json.msg);
			var resultmsgobj = convertJson.string2json1(res);
			var datslist=resultmsgobj.response.data;
			var selectObj=document.getElementById('ZJ');
			$.each(datslist, function() {    
				selectObj.options.add(new Option(this.ORG_NAME,this.USER_UID));				
			});	
		}
	});
}

//点击获取行对象
function tr_click(obj,tabListid){
	if(obj.STATUS=="30"){
		$("#btnUpdate").show();
	}else{
		$("#btnUpdate").hide();
	}
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
function rowView(obj,id,name,dw,pjId){
	$("#gdzxt_gcid").val(id);//工程id
	$("#gdzxt_projectid").val(pjId);//项目分期id
    var f =document.getElementById('gdzxtformid');
    var url='${pageContext.request.contextPath }/pagegdzxt/framework/portal/gdzxtframe';
	f.action=url;
	f.target="_blank"; 
	f.submit();
	
	//window.open("${pageContext.request.contextPath }/jsp/framework/portal/gdzxtframe.jsp?",'工地子系统');
	//$(window).manhuaDialog({"title":"安装企业>人员信息","type":"text","content":"${pageContext.request.contextPath }/jsp/business/dtgl/azqy/az-rygl-add.jsp?type=detail","modal":"2"});
}


function mchb(obj){	
	return obj.PROJECTS_NAME;
}

function caozuoFun(obj){
	
	var showHtml="<a href='javascript:void(0)' title='查看' onclick=\"rowView(this,'"+obj.GONGCHENG_UID+"','"+obj.GONGCHENG_NAME+"','"+obj.SGDW+"','"+obj.PROJECTS_UID+"')\">"+obj.SGDW+"</a>";
		
	return showHtml;
}

//添加图标
function add_icon(obj){
	//S 正在考勤
	var icon_url1="${pageContext.request.contextPath }/images/gdzxt/shield_information.png";
	//P 已特殊化备案
	var icon_url2="${pageContext.request.contextPath }/images/gdzxt/shield_minus.png";
	//Z 未开，复工项目
	var icon_url3="${pageContext.request.contextPath }/images/gdzxt/shield_plus.png";
	//N 已终止考勤
	var icon_url4="${pageContext.request.contextPath }/images/gdzxt/shield_question.png";
	//Y 正在施工的市政项目
	var icon_url5="${pageContext.request.contextPath }/images/gdzxt/shield_right.png";
	//E 突发事件临时终止项目
	var icon_url6="${pageContext.request.contextPath }/images/gdzxt/shield.png";
	
	var showHtml=obj.GONGCHENG_NAME;

	if(obj.KQ_STATUS=="S"){
		showHtml+="<img src='"+icon_url1+"' title='已特殊化备案'></img>";
	}else if(obj.KQ_STATUS=="P"){
		showHtml+="<img src='"+icon_url2+"' title='突发事件临时终止考勤'></img>";
	}else if(obj.KQ_STATUS=="Z"){
		showHtml+="<img src='"+icon_url3+"' title='正在施工的市政项目'></img>";
	}else if(obj.KQ_STATUS=="N"){
		showHtml+="<img src='"+icon_url4+"' title='未开、复工项目'></img>";
	}else if(obj.KQ_STATUS=="Y"){
		showHtml+="<img src='"+icon_url5+"' title='正在考勤'></img>";
	}else if(obj.KQ_STATUS=="E"){
		showHtml+="<img src='"+icon_url6+"' title='已终止考勤'></img>";
	}
	
	return showHtml;
}

function ajy(o){
	if(o.AJY=="")
		return " ";
	return o.AJY;
}

function zjy(o){
	if(o.ZJY=="")
		return " ";
	return o.ZJY;
}

function getCount(){	
	$.ajax({
		url : controllername+"?getCompanyCount",
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
		    	  
		    dsh.innerHTML = " (<font color=\"red\">"+res.DSH+"</font>)";				
			document.getElementById("dsid").appendChild(dsh);

			var ysh = document.createElement("h");			  
			ysh.innerHTML = " (<font color=\"red\">"+res.YTG+"</font>)";				
			document.getElementById("ysid").appendChild(ysh);

		    var thh = document.createElement("h");			  
		    thh.innerHTML = " (<font color=\"red\">"+res.WTG+"</font>)";				
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
<form id="gdzxtformid" method="post" >
		<input type="hidden" name="gdzxt_gcid" id="gdzxt_gcid" >
		<input type="hidden" name="gdzxt_projectid" id="gdzxt_projectid" >
</form>
<app:dialogs/>
<div class="container-fluid">

	<p></p>
	<div class="row-fluid">
		<div class="B-small-from-table-autoConcise">
<%--			<h4 class="title">
				建设单位
				<span class="pull-right">  
      				<button id="btnUpdate" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">审核</button>
      				button id="btnExpExcel" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">导出</button>
				</span>
			</h4>--%>
			<form method="post" id="queryForm">
				<table class="B-table" width="100%">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<TR style="display: none;">
						<TD class="right-border bottom-border"></TD>
						<TD class="right-border bottom-border">
							<input type="hidden"  kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>
						</TD>
					</TR>
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr>						
						<td class="right-border bottom-border" colspan="2">					
      						 <input type="checkbox"  value="-1" name="status" >未开工<h id="thid" ></h>			
							 <input type="checkbox" value="0" name="status" checked="checked"  >在建<h id="dsid"></h>&nbsp;&nbsp;
      						 <input type="checkbox" value="1" name="status" >完工<h id="ysid"></h>&nbsp;&nbsp;
      						 <input type="checkbox" value="2" name="status" >竣工<h id="thid" ></h>&nbsp;&nbsp;		
						</td>
						<td class="right-border bottom-border" style="padding: 5px;" colspan="1">							
							<!-- 注释的方法为利用VIEW方法来获取值 -->
							<!-- <select id="AJ" name="AJ"  fieldname="" class="span12"  kind="dic" src="T#v_projects_ajzj: users_uid as c:user_name as t:1=1 and jiandu_type =1  order by users_uid" defaultMemo="&nbsp安监" > -->
							<select id="AJ" name="AJ"  fieldname="" class="span12">
							<option value="">-安监-</option>
							</select>							
						</td>
						<td class="right-border bottom-border" style="padding: 5px;" colspan="1">							
							<select id="ZJ" name="ZJ"  fieldname="" class="span12">
							<option value="">-质监-</option>
							</select>							
						</td>
						<td class="right-border bottom-border" style="padding: 5px;" colspan="2">
							<select class="span12" id="GC_DENGJI" name="GC_DENGJI" fieldname="GC_DENGJI" operation="=">
							<option value="">&nbsp&nbsp&nbsp---所有工地---</option>
							<option value="绿色工地">绿色工地</option>
							<option value="标准化工地">标准化工地</option>
							<option value="重点监管工地">重点监管工地</option>
							</select>
						</td>
						<td class="right-border bottom-border" style="padding: 5px;" colspan="2">
							<select class="span12" id="KQ_STATUS" name="KQ_STATUS" fieldname="KQ_STATUS" operation="=">
							<option value="">&nbsp&nbsp&nbsp---所有项目---</option>
							<option value="Y">正在考勤</option>
							<option value="S">已特殊化备案</option>
							<option value="P">突发事件临时终止考勤</option>
							<option value="N">未开、复工项目</option>
							<option value="E">已终止考勤</option>
							<option value="Z">正在施工市政项目</option>
							</select>
						</td>
						<td class="right-border bottom-border" style="padding: 5px;" colspan="2">
							<select class="span12" id="order" name="order" fieldname="" operation="=">
							<option value="default">&nbsp&nbsp&nbsp---默认排序---</option>
							<option value="time">更新时间</option>							
							</select>
						</td>
						<td class="bottom-border text-right right-border" rowspan="2">
	                        <button id="btnQuery" class="btn btn-link"  type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-search"></i>查询</button>
           					<button id="btnClear" class="btn btn-link" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-trash"></i>清空</button>
			            </td>					
					</tr>
					
					<tr>						
						<th width="9%" class="right-border bottom-border text-right" >建设单位</th>
						<td class="right-border bottom-border" width="12%">
							<input class="span12" type="text" onkeydown="enterSumbit()" id="JSDW" name="JSDW" fieldname="JSDW" operation="like" >
						</td>	
						<th width="9%" class="right-border bottom-border text-right">项目名称</th>
						<td class="right-border bottom-border" width="9%">
							<input class="span12" type="text" onkeydown="enterSumbit()" id="PROJECTS_NAME" name="PROJECTS_NAME" fieldname="PROJECTS_NAME" operation="like" >
						</td>	
						<th width="9%" class="right-border bottom-border text-right">施工单位</th>
						<td class="right-border bottom-border" width="9%">						
							<input class="span12" type="text" onkeydown="enterSumbit()" id="SGDW" name="SGDW" fieldname="SGDW" operation="like" >
						</td>
						<th width="7%" class="right-border bottom-border text-right">工程名称</th>
						<td class="right-border bottom-border" width="8%">						
							<input class="span12" type="text" onkeydown="enterSumbit()" id="GONGCHENG_NAME" name="GONGCHENG_NAME" fieldname="GONGCHENG_NAME" operation="like" >
						</td>
						<th width="7%" class="right-border bottom-border text-right">监理单位</th>
						<td class="right-border bottom-border" width="8%">						
							<input class="span12" type="text" onkeydown="enterSumbit()" id="JLDW" name="JLDW" fieldname="JLDW" operation="like" >
						</td>
							
											
					</tr>
				</table>
			</form>
			<div style="height:5px;"> </div>	
			<div ><!-- class="overFlowX" -->
	            <table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single"  pageNum="9999" noheight="true">
	                <thead>
	                	<tr>
	                		<th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center" >&nbsp;#&nbsp;</th>
	                		<th fieldname="JSDW" colindex=2 tdalign="center" maxlength="100" style="width:60px" rowMerge="true" tdalign="center">&nbsp;建设单位&nbsp;</th>
	                		<th fieldname="PROJECTS_NAME" colindex=3 tdalign="center" maxlength="100" style="width:100px;display: none;">&nbsp;项目名称&nbsp;</th>
	                		<th fieldname="PROJECTS_UID" colindex=4 tdalign="center" maxlength="100" rowMerge="true" style="width:60px" CustomFunction="mchb">&nbsp;项目名称&nbsp;</th>
	                		<!-- 防止AJY为空时，自动跨行，AJY前面添加PRJ的UID防止，然后通过ajy()方法处理显示内容 -->
	                		<th fieldname="PRJ_AJY"  colindex=5 tdalign="center" maxlength="100" rowMerge="true" style="width:30px" CustomFunction="ajy">&nbsp;安监员&nbsp;</th>
	                		<th fieldname="PRJ_ZJY"  colindex=6 tdalign="center" maxlength="100" rowMerge="true" style="width:30px" CustomFunction="zjy">&nbsp;质监员&nbsp;</th>
	                		<th fieldname="SGDW" colindex=7 tdalign="center" maxlength="100" style="width:60px" CustomFunction="caozuoFun">&nbsp;施工单位&nbsp;</th>
	                		<th fieldname="GONGCHENG_NAME" colindex=8 tdalign="center" maxlength="100" style="width:60px"  CustomFunction="add_icon">&nbsp;工程名称&nbsp;</th>
	                		<th fieldname="XMJL" colindex=9 tdalign="center" maxlength="30" style="width:30px">&nbsp;项目经理&nbsp;</th>
	                		<th fieldname="JLDW" colindex=10 tdalign="center" maxlength="100" style="width:60px">&nbsp;监理单位&nbsp;</th>
	                		<th fieldname="ZJ" colindex=11 tdalign="center" maxlength="30" style="width:30px">&nbsp;总监&nbsp;</th>
	                		<th fieldname="GC_STATUS" colindex=12 tdalign="center" style="width:30px" maxlength="30">&nbsp;状态&nbsp;</th>
	                		<th fieldname="SCORE" colindex=13  tdalign="center"  style="width:30px">&nbsp;上月分值&nbsp;</th>
	                		<th fieldname="GC_DENGJI" colindex=14  tdalign="center" maxlength="30" style="width:30px">&nbsp;等级&nbsp;</th>
							<th fieldname="LAST_INFO" colindex=15  tdalign="center" maxlength="30" style="width:50px">&nbsp;最后更新&nbsp;</th>
							<!-- 为了拿到AJY,ZJY的值，将其隐藏 -->
							<th fieldname="AJY" style="display: none;"></th>
							<th fieldname="ZJY" style="display: none;"></th>		
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
		<input type="hidden" name="txtFilter" order="desc" fieldname="PROJECTS_UID" id="txtFilter"/>

<%--		<input type="hidden" name="txtFilter" order="desc" fieldname="ID" id="txtFilter"/>--%>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>