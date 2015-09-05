<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp" %>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<!DOCTYPE html>
<html>
	
<head>
	<base href="${ctx_site}/">
	<title>起重设备选择</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
 	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="无锡建设环保局-建设单位信用管理系统" />
	<%@ include file="/jsp/framework/common/head.jsp"%>
 	<app:base />
</head>
<body class="no-skin">
<div class="main-container" id="main-container">
		<div class="main-content">
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12" id="contentdivid">
							<!-- PAGE CONTENT BEGINS -->
							<form class="form-inline" role="form" id="sbForm" style="border:solid 1px #ddd;height:50px;line-height:50px;vertical-align:middle;">
							<div class="col-xs-10">
							<input type="hidden" value="1" fieldname="SHENHE_JIEGUO" operation="=" logic="and" >
							
							<input type=radio name="SY"  onclick="javascript:checkY()" checked="checked"   >可以使用<h id="dsid"></h>&nbsp;&nbsp;
      						 <input type=radio name="SY"  onclick="javascript:checkN()"  >正在使用<h id="ysid"  ></h>&nbsp;&nbsp;
							  <div class="form-group">
									<!-- <select class="form-control" style="margin-bottom:8px;width: 200px" id="GONGZHONG" type="text" kind="dic" src="GONGZHONG" name="GONGZHONG" fieldname="GONGZHONG" operation="like" logic="and"></select> -->
									<select class="form-control" style="margin-bottom:8px;width: 110px"  id="JXSB_TYPE_UID" type="text" name="JXSB_TYPE_UID" fieldname="JXSB_TYPE_UID"  class="col-xs-10 col-sm-10" operation="=">
									   <option value="">--全部设备--</option>
									   <option value="1">塔式起重机</option>
									   <option value="2">物料提升机</option>
									   <option value="3">施工升降机</option>
									   <option value="4">吊篮</option>
									   <option value="5">爬升式脚手架</option>
									</select>
									<input class="hidden" id="rownum" type="text" fieldname="rownum"  value="10000" operation="  &lt;= "/>
								</div>
								&nbsp;&nbsp;
					          <div class="form-group">
										<label>产权编号</label>
							   </div>
							   <div class="form-group">
									<input class="form-control" style="margin-bottom:8px;width: 80px" name="CQBH" fieldname="CQBH" id="CQBH"  operation="like" logic="and" >
								
								</div>
								&nbsp;&nbsp;
								 <div class="form-group">
										<label>产权单位</label>
							   </div>
							   <div class="form-group">
									<input class="form-control" style="margin-bottom:8px;width: 150px" name="CQDW" fieldname="CQDW" id="CQDW"  operation="like" logic="and" >
								
								</div>
							 
							<button id="search" class="btn btn-link btn-sm" type="button"><i class="#ace-icon glyphicon glyphicon-search  bigger-110"></i>查询</button>
							<button id="clean" class="btn btn-link btn-sm"  type="button"><i class="#ace-icon glyphicon glyphicon-trash  bigger-110"></i>清空</button>
							</div>	
								<div class="col-xs-2 align-right" >
									<button id="savesb" onclick="selSb()" class="btn btn-primary btn-sm" type="button">确定</button>
									<button onclick="javascript:window.close()" class="btn btn-primary btn-sm" type="button">关闭</button>
							</div>
						 </form>
						 <div id="tableY">
							<table  sortname="CREATED_DATE" multiselect=true  rownum="50"
									sortorder="desc" 
									class="auto_startgrid" 
									id="content_grid_table" 
									action="${pageContext.request.contextPath }/jxsb/jxsbController/query?type=y" >
								<tr>
								    <th name="JXSB_UID"  hidden="">id</th>
									<th formatter="view" name="CQBH" width="80"  align=center>产权编号</th>				<!-- formatter="jqgridactions" -->
									<th name="JXSB_TYPE_UID_SV"  width="70" align=center>设备类型</th>
									<th name="CQDW" width="120" align=center >产权单位</th>
									<th name="SB_XH" width="80" align=center >设备型号</th>
									<th name="ZZDW" width="100" align=center >制造厂家</th>
									<th name="CC_DATE" width="70" align=center >出厂日期</th>
									<th name="HGZH" width="80" align=left >合格证号</th>
								</tr>
							</table>
							</div>
							<div id="tableN">
							<table  sortname="CREATED_DATE" multiselect=false  rownum="50"
									sortorder="desc" 
									class="auto_startgrid" 
									id="content_grid_table2" 
									action="${pageContext.request.contextPath }/jxsb/jxsbController/query?type=n" >
								<tr>
								    <th name="JXSB_UID" hidden="">id</th>
									<th formatter="view" name="CQBH" width="80"  align=center>产权编号</th>				<!-- formatter="jqgridactions" -->
									<th name="JXSB_TYPE_UID_SV"  width="70" align=center>设备类型</th>
									<th name="CQDW" width="120" align=center  >产权单位</th>
									<th name="SB_XH" width="80" align=center >设备型号</th>			
									<th name="ZZDW" width="100" align=center >制造厂家</th>
									<th name="CC_DATE" width="70" align=center >出厂日期</th>
									<th name="HGZH" width="80" align=left >合格证号</th>
								</tr>
							</table>
							</div>
						</div>
					</div>

				</div>
			</div>		
		</div>
	<div id="view-input" class="modal"></div>
<%@ include file="/jsp/framework/common/basic_scripts.jsp"%>
 	<app:base />


<script type="text/javascript" charset="utf-8">


var scripts =[null];// ["jsp/business/project/JsProject.js"];
var type="y";
ace.load_ajax_scripts(scripts, function() {
		var gridwidth=$("#contentdivid").width();
		JqgridManage.initJqgrid(content_grid_table,sbForm,gridwidth);
		JqgridManage.initJqgrid(content_grid_table2,sbForm,gridwidth);
        $("#tableN").hide();
        $("#cb_content_grid_table").hide();
		//setStyle(xmxxtxformid);
		//DatePicker.datepickerid("#PF_DATE");
	});
//页面初始化
$(function() {
		$('#new').click(function() {
							type = null;// 清下id 要不然修改会 id回村起来 下次业务就成update了
							$(".info").hide();
							// $("#executeFrm").clearFormResult();孙尚稥丶
							$('#new').attr("data-target","sbsh-input");
							$('#sbsh-input').removeData("bs.modal");
							$("#sbsh-input").empty();
							$('#sbsh-input').modal({
								backdrop: 'static'
							});
							$.get(contextPath+ "/jsp/gdzxt/jxsbgl/sbzl-page-azgz.jsp",function(data) {$("#sbsh-input").html(data);});
						});
						
		$('#search').click(function() {
		if(type=="y"){
			$('#content_grid_table').trigger("reloadGrid");
		}else if(type=="n"){
			$('#content_grid_table2').trigger("reloadGrid");
		}
		});
		$('#clean').click(function(){
			$("#CQBH").val("");
			$("#CQDW").val("");
			document.getElementById("JXSB_TYPE_UID").options[0].selected=true;
			 $("#search").click();  
		});
		
		$('[id=DATE]').each(function() { //ace 时间
			var mydate = this;
			if (mydate.type !== 'date') {//if browser doesn't support "date" input
				$(mydate).datepicker({
					//options  
					autoclose : true,
					todayHighlight : true,
					language : 'zh-CN',

				})
			}
		});
});
function checkY(){
type="y";
$("#savesb").show();
$("#tableY").show();
$("#tableN").hide();
}
function checkN(){
type="n";
$("#savesb").hide();
$("#tableN").show();
$("#tableY").hide();
}

function selSb(){
var id;
var rowData;
if(type=="y"){
id=$("#content_grid_table").jqGrid("getGridParam","selrow");
rowData = $("#content_grid_table").jqGrid('getRowData',id);
} else if(type=="n"){
id=$("#content_grid_table2").jqGrid("getGridParam","selrow");
rowData = $("#content_grid_table2").jqGrid('getRowData',id);
}
var sbid = rowData.JXSB_UID;
if(sbid==null){
bootbox.alert("请选择一条记录!");
}else{
 window.opener.querySb(sbid);
  window.close();  
}
}
function view(cellvalue, opts, rowObject){
var html="<a href=\"#\" data-target=\"#az-rygl-input\" onclick=\"openSpan('"+rowObject.JXSB_UID+"')\" data-toggle=\"modal\">"+rowObject.CQBH+"</a>";
return html;
}


function openSpan(id){
		$('#view-input').removeData("bs.modal");
			$("#view-input").empty();
			$('#view-input').modal({
				backdrop: 'static'
				});
			$.get(contextPath+ "/jsp/gdzxt/jxsbgl/sbzl-page-azgz-baoyang-view.jsp?id='"+id+"'",function(data) {$("#view-input").html(data);});
							
}
</script>		
</body>
</html>