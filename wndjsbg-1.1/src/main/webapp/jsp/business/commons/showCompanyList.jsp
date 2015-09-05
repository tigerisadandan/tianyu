<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<title>审核情况</title>
<app:base />
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
	//请求路径，对应后台RequestMapping
	var controllername = "${pageContext.request.contextPath }/sgenter/sgEnterPriseLibraryController.do";

	//页面初始化
	$(function() {
		init();
		$("#btnQuery1").click(function(){
			loadDatas(true);
			addTRTitle();
		});
		$("#btnQuery2").click(function(){
			loadDatas(false);
			addTRTitle();
		});
	});
	//页面默认参数
	function init() {
		loadDatas(true);
		loadDatas(false);
		addTRTitle();
	}
	function loadDatas(type){
		if(type){
			var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
			//调用ajax插入
			defaultJson.doQueryJsonList(controllername+"?queryAppList&status=10",data,DT1,null,true);
			var rows = $("tbody tr" ,$("#DT1"));
			if(rows.size()==0){
				$("tbody" ,$("#DT1")).append("<tr><td colspan=\"3\" style=\"height: 1px;\">&nbsp;</td></tr>");
			}
		}else{
			var data = combineQuery.getQueryCombineData(queryForm1,frmPost,DT2);
			//调用ajax插入
			defaultJson.doQueryJsonList(controllername+"?queryAppList&status=20",data,DT2,null,true);
			var rows = $("tbody tr" ,$("#DT2"));
			if(rows.size()==0){
				$("tbody" ,$("#DT2")).append("<tr><td colspan=\"3\" style=\"height: 1px;\">&nbsp;</td></tr>");
			}
		}

	}

	
	function showMessage(id) {

		$("#resultXML").val($("#DT1").getSelectedRow());
<%--		$(window).manhuaDialog({"title":"查看审核意见","type":"text","content":"","modal":"1"});--%>

	}
	function addTRTitle(){
		$("tbody tr" ,$("#DT1")).each(function(){
			$(this).attr("title","双击查看审批信息和登录代码");
		})
		$("tbody tr" ,$("#DT2")).each(function(){
			$(this).attr("title","双击查看审批信息");
		})
	}
	function strToDate(str) {
		var tempStrs = str.split(" ");
		var dateStrs = tempStrs[0].split("-");
		var year = parseInt(dateStrs[0], 10);
		var month = parseInt(dateStrs[1], 10) - 1;
		var day = parseInt(dateStrs[2], 10);
		var timeStrs = tempStrs[1].split("-");
		var hour = parseInt(timeStrs[0], 10);
		var minute = parseInt(timeStrs[1], 10) - 1;
		var second = parseInt(timeStrs[2], 10);
		var date = new Date(year, month, day, hour, minute, second);
		return date;
	}


	function doDATE(obj){
			var showHtml = obj.COMPANY_NAME;
			var myDate = new Date();
			var s1 = obj.SHENHE_DATE;

			s1 = s1.replace(/-/g, "/");
			s1 = new Date(s1);

			var times = myDate.getTime() - s1.getTime();
			var days = parseInt(times/ (1000 * 60 * 60 * 24));
			if(days<=3){
				showHtml = obj.COMPANY_NAME+'<img src="/wndjssg/img/sg/new.gif">';
			}
		}
	function tr_dbclick(obj,tabId){
		location.href="${pageContext.request.contextPath }/sgmessage/"+obj.SG_ENTERPRISE_LIBRARY_UID+".do";
<%--		location.href="${pageContext.request.contextPath }/sgmessage.do?uid="+;	--%>
	}

</script>
</head>
<body>
	<app:dialogs />
	<div class="container-fluid">
		<div class="row-fluid">
			<input type='hidden' id='previewFileid'>
			<div class="B-small-from-table-autoConcise">
				

				<div style="height:5px;"></div>

			<ul class="nav nav-tabs" style="width:82% ">
				<li class="active"><a href="#xmsc1" data-toggle="tab"
					id="tabPage0">已通过审核的企业名单</a>
				</li>
			</ul>
			<div class="tab-content" style="width:80% ">
				<!-- 静态信息tab页 -->
				<div class="tab-pane active" id="xmsc1" style="300">
					<form method="post" id="queryForm">
						<table class="B-table" width="100%">
							<!--可以再此处加入hidden域作为过滤条件 -->
							<TR style="display:none;">
								<TD class="right-border bottom-border"></TD>
								<TD class="right-border bottom-border"></TD>
							</TR>
							<tr>
								<th width="5%" class="right-border bottom-border text-right">企业名称</th>
									<td class="right-border bottom-border" width="40%">
										<input class="span12" type="text" id="COMPANY_NAME" name="COMPANY_NAME" fieldname="COMPANY_NAME" operation="like" >
									</td>
							    	<td class="text-left bottom-border text-right">
					              		<button id="btnQuery1" class="btn btn-link"  type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-search"></i>查询</button>
							      	</td>	
								</tr>
						</table>
					</form>
					<div style="height:5px;"> </div>	
					<div class="overFlowX">
			            <table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single"  pageNum="5">
			                <thead>
			                	<tr>
			                		<th  name="XH" id="_XH" style="width:10px" colindex=0 tdalign="center">&nbsp;#&nbsp;</th>
									<th fieldname="COMPANY_NAME" colindex=1 tdalign="left" width="70%" CustomFunction="doDATE" >&nbsp;单位名称&nbsp;</th>
									<th fieldname="SHENHE_DATE" colindex=2 tdalign="right">&nbsp;审核时间&nbsp;</th>
			                	</tr>
			                </thead>
			              	<tbody></tbody>
			           </table>
			       </div>
				</div>
			</div>
			
			<div style="height:5px;"></div>
			<ul class="nav nav-tabs" style="width:82% ">
				<li class="active"><a href="#xmsc1" data-toggle="tab"
					id="tabPage0">未通过审核的企业名单</a>
				</li>
			</ul>
			<div class="tab-content" style="width:80% ">
				<!-- 静态信息tab页 -->
				<div class="tab-pane active" id="xmsc1" style="300">
					<form method="post" id="queryForm1">
						<table class="B-table" width="100%">
							<!--可以再此处加入hidden域作为过滤条件 -->
							<TR style="display:none;">
								<TD class="right-border bottom-border"></TD>
								<TD class="right-border bottom-border"></TD>
							</TR>
							<tr>
								<th width="5%" class="right-border bottom-border text-right">企业名称</th>
									<td class="right-border bottom-border" width="40%">
										<input class="span12" type="text" id="COMPANY_NAME" name="COMPANY_NAME" fieldname="COMPANY_NAME" operation="like" >
									</td>
							    	<td class="text-left bottom-border text-right" >
					              		<button id="btnQuery2" class="btn btn-link"  type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-search"></i>查询</button>
							      	</td>	
								</tr>
						</table>
					</form>
					<div style="height:5px;"> </div>	
					<div class="overFlowX">
			            <table width="100%" class="table-hover table-activeTd B-table" id="DT2" type="single"  pageNum="5">
			                <thead>
			                	<tr>
			                		<th  name="XH" id="_XH" style="width:10px" colindex=0 tdalign="center">&nbsp;#&nbsp;</th>
									<th fieldname="COMPANY_NAME" colindex=1 tdalign="left" width="70%">&nbsp;单位名称&nbsp;</th>
									<th fieldname="SHENHE_DATE" colindex=2 tdalign="right">&nbsp;审核时间&nbsp;</th>
			                	</tr>
			                </thead>
			              	<tbody></tbody>
			           </table>
			       </div>
				</div>
			</div>
		</div>
		<br />
			</div>
			
	</div>
	 <div><b style="color:blue;">*双击可查看审批信息</b></div>
	</div>
	<jsp:include page="/jsp/file_upload/fileupload_config.jsp" flush="true" />
	</div>
	<div align="center">
		<FORM name="frmPost" method="post" style="display:none"
			target="_blank">
			<!--系统保留定义区域-->
			<input type="hidden" name="queryXML" id="queryXML"> <input
				type="hidden" name="txtXML" id="txtXML"> <input
				type="hidden" name="txtFilter" order="desc" fieldname="SERIAL_NO"
				id="txtFilter"> <input type="hidden" name="resultXML"
				id="resultXML">
			<!--传递行数据用的隐藏域-->
			<input type="hidden" name="rowData">
		</FORM>
	</div>
</body>
<script>
	
</script>
</html>