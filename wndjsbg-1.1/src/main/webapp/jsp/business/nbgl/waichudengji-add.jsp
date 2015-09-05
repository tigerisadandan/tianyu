<!DOCTYPE html>
<%@page import="com.ccthanking.framework.Constants"%>
<%@page import="java.util.Date" %>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base />
<title>外出登记-添加</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
	var controllername = "${pageContext.request.contextPath }/nbgl/waiChuDengJiController";
	var controllername1 = "${pageContext.request.contextPath }/nbgl/organizeController";
	//页面初始化
	$(function() {
		init();
		$("#btnClose").bind("click", function() {
			var a = $(this).manhuaDialog.close();
		});
		$("#btnSave").bind("click",function(){
			if($("#YJ_FH_SHIJIAN").val()==""||$("#WAICHU_DIDIAN").val()==""){
				return;
			}
			var ids ="";
			var names = "";
			$("input[name='user']:checked").each(function(index,obj){
				ids += $(obj).val()+",";
				names+=$(obj).next("span").text()+",";
			});
			if(ids.length>1){
				ids = ids.substring(0,ids.length-1);
				names = names.substring(0,names.length-1);
					$("#ids").val(ids);
					$("#names").val(names);
				}
				//生成json串
				var data = Form2Json.formToJSON(WaiChuForm);
				//组成保存json串格式
				var data1 = defaultJson.packSaveJson(data);
				$.ajax({
					url :controllername+"?insert",
					data : data1,
					dataType : "json",
					type : 'post',
					success : function(response) {
						//获取父页面
						var a=$(window).manhuaDialog.getParentObj();
						//重新加载数据
						a.init();
						//关闭当前页
						$(window).manhuaDialog.close();	
					}
				});
		});
	});

	//页面默认参数
	function init() {
		//外出时间默认为当前时间
		$("#WAICHU_SHIJIAN").val(CurentTime());
		//查询当前部门所有用户
		$.ajax({
			url :controllername1+"?queryDeptUser",
			dataType : "json",
			type : 'post',
			success : function(response) {
			var res = dealSpecialCharactor(response.msg);
			var resultmsgobj = convertJson.string2json1(res);
			if(resultmsgobj!=null&&resultmsgobj!=''){
							var datslist=resultmsgobj.response.data;
							$(datslist).each(function(){
								var checkboxHtml = "<input type='checkbox' name='user' value='"+this.USER_UID+"' style='margin-bottom:5px;' />";
								var spanHtml = "<span>"+this.ORG_NAME+"</span>";
								$("#sx").append(checkboxHtml);
								$("#sx").append(spanHtml+"&nbsp&nbsp&nbsp&nbsp");
							});
						}	
					}
				});
				//查询其他部门的用户
			$.ajax({
			url :controllername1+"?queryRestDeptUser",
			dataType : "json",
			type : 'post',
			success : function(response) {
			var res = dealSpecialCharactor(response.msg);
			var resultmsgobj = convertJson.string2json1(res);
			if(resultmsgobj!=null&&resultmsgobj!=''){
							var datslist=resultmsgobj.response.data;
							$(datslist).each(function(){
								var checkboxHtml = "<input type='checkbox' name='user' value='"+this.USER_UID+"' style='margin-bottom:5px;' />";
								var spanHtml = "<span>"+this.ORG_NAME+"</span>";
								$("#qitasx").append(checkboxHtml);
								$("#qitasx").append(spanHtml+"&nbsp&nbsp&nbsp&nbsp");
							});
						}	
					}
				});
	}
	function CurentTime()
    { 
        var now = new Date();
       
        var year = now.getFullYear();       //年
        var month = now.getMonth() + 1;     //月
        var day = now.getDate();            //日
       
        var hh = now.getHours();            //时
        var mm = now.getMinutes();          //分
        var ss = now.getSeconds();			//秒
       
        var clock = year + "-";
       
        if(month < 10)
            clock += "0";
       
        clock += month + "-";
       
        if(day < 10)
            clock += "0";
           
        clock += day + " ";
       
        if(hh < 10)
            clock += "0";
           
        clock += hh + ":";
        if (mm < 10) clock += '0'; 
        clock += mm +":"; 
        
        if(ss < 10)
         clock += '0'; 
         clock +=ss;
        
        return clock; 
    } 
</script>
</head>
<body>
	<app:dialogs />
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="B-small-from-table-autoConcise">
				<h4 class="title">
				登记信息
					 <span class="pull-right">
						<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
						<button id="btnSave" class="btn" type="button" style=btnSavet-family:SimYou,Microsoft YaHei;font-weight:bold;">保存</button>
					</span>
				</h4>
				<div style="height:5px;"></div>
				<form method="post" id="WaiChuForm" action="${pageContext.request.contextPath }/nbgl/waiChuDengJiController/?insert">
					<table class="B-table" width="100%">
						<tr>
							<th width="15%" class="right-border bottom-border text-right">外出时间</th>
							<td class="bottom-border right-border" style="width:32%">
								<input id="WAICHU_SHIJIAN" type="text" fieldtype="date" fieldformat="YYYY-MM-DD HH:mm:ss" fieldname="WAICHU_SHIJIAN" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy/MM/dd HH:ss:mm'})" name = "WAICHU_SHIJIAN" readonly="readonly"    />
								<!-- 隐藏的数据 -->
								<input type="hidden" id="ids" name="ids" fieldname="ids" />
								<input type="hidden" id="names" name="names" fieldname="names" />
							</td>
						</tr>
						<tr>
							<th width="15%" class="right-border bottom-border text-right"><font color="red">*</font>外出地点</th>
							<td class="bottom-border right-border" style="width:100%"
								colspan="3"><input class="span12" id="WAICHU_DIDIAN"
								type="text" fieldname="WAICHU_DIDIAN"
								name="WAICHU_DIDIAN" placeholder="外出地点不能为空！" check-type="required" /></td>
						</tr>
						<tr>
							<th width="15%" class="right-border bottom-border text-right"><font color="red">*</font>预计返回时间</th>
							<td colspan="3" class="bottom-border right-border">
								<div id="dis">
									<input id="YJ_FH_SHIJIAN" placeholder="预计返回时间不能为空！" check-type="required" type="text" fieldtype="date" fieldformat="YYYY-MM-DD HH:mm:ss" fieldname="YJ_FH_SHIJIAN" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy/MM/dd HH:ss:mm'})" name = "YJ_FH_SHIJIAN" readonly="readonly"   />
								</div
							</td>
						</tr>
						<tr>
							<th width="15%" class="right-border bottom-border text-right">随行人员</th>
							<td colspan="3" class="bottom-border right-border">
								<div id="sx">
									
								</div
							</td>
						</tr>
						<tr>
							<th width="15%" class="right-border bottom-border text-right">其他随行人员</th>
							<td colspan="3" class="bottom-border right-border">
								<div id="qitasx">
									
								</div
							</td>
						</tr>
						<tr>
							<th width="15%" class="right-border bottom-border text-right">外出事宜</th>
							<td class="bottom-border right-border" style="width:32%"
								colspan="3"><textarea class="span12" rows="4"
									id="WAICHU_NEIRONG" fieldname="WAICHU_NEIRONG" name="WAICHU_NEIRONG"></textarea></td>
						</tr>

					</table>
				</form>
			</div>
		</div>
	</div>

	<jsp:include page="/jsp/file_upload/fileuploadold_config_js.jsp"
		flush="true" />
	<div align="center">
		<FORM name="frmPost" method="post" style="display:none"
			target="_blank">
			<!--系统保留定义区域-->
			<input type="hidden" name="queryXML" id="queryXML"> <input
				type="hidden" name="txtXML" id="txtXML"> <input
				type="hidden" name="txtFilter" order="desc"
				fieldname="SHENHE_JIEGUO" id="txtFilter" /> <input type="hidden"
				name="txtFilter" order="desc" fieldname="CREATED_DATE"
				id="txtFilter" /> <input type="hidden" name="resultXML"
				id="resultXML">
			<!--传递行数据用的隐藏域-->
			<input type="hidden" name="rowData">

		</FORM>
	</div>
</body>
<script>
	
</script>
</html>