<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>监理企业信息库</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/jlEnterpriseController";
var jsonStr = "";
//页面初始化
$(function() {
	init();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"?query",data,DT1);
	});
	
	//按钮绑定事件(修改)
	$("#btnUpdate").click(function() {
		if($("#DT1").getSelectedRowIndex()==-1){
			requireSelectedOneRow();
		    return
		 }
		$("#resultXML").val($("#DT1").getSelectedRow());
		$(window).manhuaDialog({"title":"监理企业信息库>修改","type":"text","content":"${pageContext.request.contextPath }/jsp/business/wxgc/lhenterprise-add.jsp?type=update","modal":"1"});
	});
	
	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#queryForm").clearFormResult();
        $("#ZT_ID").val("30");
        $("#zt0").removeAttr("checked");
        $("#zt1").removeAttr("checked");
        $("#zt2").removeAttr("checked");
        document.getElementById('zt1').checked=true;
        $("#btnQuery").click();
    });

    $("#btnStop").click(function(){
    	doSelectPerson();
     });
});

//页面默认参数
function init(){
	var status = $("input:radio[name='status']:checked").val();
	check(status);
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?query",data,DT1);
}

//获取删除人员的ID
function delSelectID(liyou){
	$.each($("#DT1").find("input:checkbox[name='doSelect']"),function(i){
		if($("#DT1").find("input:checkbox[name='doSelect']")[i].checked){
			
			var id=$(this).val();
			$("#RENYUAN_ID").val($("#RENYUAN_ID").val()+id+",");
		}
	})
  $.ajax({
		    url : controllername+"?tuihui&ids="+$("#RENYUAN_ID").val()+"&yijian="+liyou,//$("#YIJIAN").val(),
			data : {},
			cache : false,
			async :	false,
			dataType : "json",  
			type : 'get',
			success : function(response) {
				$("#resultXML").val(response.msg);
			}
	});
	//$("#btnQuery").click();
	  
	//clickRadioShowDate();
	$("#RENYUAN_ID").val("");
}


function check(status){
	if(status==30){
		$("#btnStop").show();
		//控制一些字段的显示
		$("#doSelect").show();
		$("#doSelect").attr("fieldname","ENTERPRISE_LIBRARY_UID");
		$("#TIJIAO_DATE").show();
		$("#TIJIAO_DATE").attr("fieldname","TIJIAO_DATE");
		$("#SHENHE_DATE").attr("fieldname","");
		$("#SHENHE_DATE").hide();
		
	    
	}else{
		$("#btnStop").hide();
		
		$("#doSelect").attr("fieldname","");
		$("#doSelect").hide();
		
		$("#SHENHE_DATE").show();
		$("#SHENHE_DATE").attr("fieldname","SHENHE_DATE");
		$("#TIJIAO_DATE").attr("fieldname","");
		$("#TIJIAO_DATE").hide();
	}	

	
}
//详细信息
function rowView(obj){
	while(obj.tagName.toLowerCase() != "tr"){
		obj = obj.parentNode;
		if(obj.tagName.toLowerCase() == "table")return null;
	}
	obj.click();
	if($("#DT1").getSelectedRowIndex()==-1){
		requireSelectedOneRow();
	    return
	 }
	$("#resultXML").val($("#DT1").getSelectedRow());
	 var rowValue = $("#DT1").getSelectedRow();
     var tempJson = convertJson.string2json1(rowValue);

	//$(window).manhuaDialog({"title":"监理企业信息库>查看","type":"text","content":"${pageContext.request.contextPath }/jsp/business/jl/jlenterprise-edit.jsp?type=detail","modal":"1"});
	window.open("${pageContext.request.contextPath }/jlEnterprise/"+tempJson.ENTERPRISE_LIBRARY_UID,"企业信息查看");//传递一个ID给详细页面，用于查找 
}

function checkStatus(status){
	 $("#ZT_ID").val(status);
	 check(status);
	 $("#btnQuery").click();
}

function doeditor(obj){
	var rehtml=" ";
	var start=obj.STATUS;
	if(start=='30'){
		rehtml+="<a href='javascript:void(0)' onclick='editorU(this)'  title='审核'><i class='icon-file showXmxxkInfo'></i></a>";
	}else{
		rehtml+="<a href='javascript:void(0)' onclick='rowView(this)'  title='查看'><i class='icon-file showXmxxkInfo'></i></a>";
	}
	return rehtml;
}

function editorU(obj){
	while(obj.tagName.toLowerCase() != "tr"){
		obj = obj.parentNode;
		if(obj.tagName.toLowerCase() == "table")return null;
	}
	obj.click();
	if($("#DT1").getSelectedRowIndex()==-1){
		requireSelectedOneRow();
	    return
	 }
	$("#resultXML").val($("#DT1").getSelectedRow());
	 var rowValue = $("#DT1").getSelectedRow();
     var tempJson = convertJson.string2json1(rowValue);
	//$(window).manhuaDialog({"title":"监理企业信息库>审核","type":"text","content":"${pageContext.request.contextPath }/jsp/business/jl/jlenterprise-sh.jsp?type=update","modal":"1"});
	window.open("${pageContext.request.contextPath }/jlEnterprise/"+tempJson.ENTERPRISE_LIBRARY_UID,"企业信息查看");//传递一个ID给详细页面，用于查找 

}


function doAllSelect(demo){
	var flag = $(demo).prop("checked");
	$("input:checkbox[name='doSelect']").each(function(i){
		if(i>=0){
			$(this).prop("checked",flag);
		}
	})
}

function EnterPress(){
	if(event.keyCode == 13){
		$("#btnQuery").click();
	}
}

function doMoreChoose(obj){
	var id=obj.ENTERPRISE_LIBRARY_UID;
	return "<input type='checkbox' id='doSelect' name='doSelect' value='"+id+"'/>";
}

function doDm(obj){
	return "<a href='javascript:void(0)' title='登陆代码:"+obj.DENGLU_CODE+"' >"+obj.COMPANY_NAME+"</a>";
}

function doSelectPerson(){
	//$.each($("#DT1").find("input:checkbox[name='doSelect']"),function(){
		
		if($("#DT1").find("input:checkbox[name='doSelect']:checked").size()>0){
			optype = 0;
			$(window).manhuaDialog({"title":"退回","type":"text","content":"${pageContext.request.contextPath }/jsp/business/jl/jlenterprise-because.jsp","modal":"3"});	
		}
		else{
			alert("请选择人员");  
			}
}

function doExecl(tabId){
	   $(window).manhuaDialog({"title":"导出","type":"text","content":"/wndjsbg/jsp/framework/print/TabListEXP.jsp?tabId="+tabId+"&dao=jlEnterpriseDaoImpl&jsonStr="+jsonStr,"modal":"3"});
}
</script>
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="B-small-from-table-autoConcise">
			<form method="post" id="queryForm">
				<table class="B-table" width="100%">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<TR style="display: none;">
						<TD class="right-border bottom-border">
							<INPUT type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>
							<INPUT type="text" class="span12" kind="text" id="ZT_ID" fieldname="STATUS" value="1" operation="="/>
							<INPUT type="text" class="span12" kind="text" id="YIJIAN" name="YIJIAN"  fieldname="YIJIAN" value="" />
	        				<INPUT type="text" class="span12" kind="text" id="RENYUAN_ID" name="RENYUAN_ID"  fieldname="RENYUAN_ID" value="" />
						</TD>
					</TR>
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr>
					      <td class="right-border bottom-border" width="10%">
						  <select class="span12" id="WAIDI_Y" operation="=" name="WAIDI_Y" fieldname="t.WAIDI_Y" >
						  	<option value="">-地域-</option>
						  	<option value="N">本地</option>
						  	<option value="Y">外地</option>
						  </select>
					     </td>
						<th width="5%" class="right-border bottom-border text-right">企业名称</th>
						<td class="right-border bottom-border" width="20%">
							<input class="span12" type="text" id="QCOMPANY_NAME" name="COMPANY_NAME" fieldname="COMPANY_NAME" operation="like" >
						</td>
			            <td class="text-left bottom-border text-right">
	                        <button id="btnQuery" class="btn btn-link"  type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-search"></i>查询</button>
           					<button id="btnClear" class="btn btn-link" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-trash"></i>清空</button>
			            </td>							
					</tr>
				</table>
			</form>
			<div style="height:5px;"> </div>	
			<div class="overFlowX">
	            <table width="100%" print="true" class="table-hover table-activeTd B-table" id="DT1" type="single"  pageNum="18">
	                <thead>
	                	<tr>
	                		<th rowspan="2"   name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
	                		<!-- 
	                		<th style="width:10px" fieldname="ENTERPRISE_LIBRARY_UID" id="doSelect" name="doSelect" tdalign="center" colindex=2 CustomFunction="doMoreChoose" >
		                		<label class="checkbox inline" >
		                        <input type="checkbox" id="allSelect" onclick="doAllSelect(this)" name="allSelect"/>
		                        </label>
	                        </th>
	                         -->
	                		<th rowspan="2" fieldname="ENTERPRISE_LIBRARY_UID" colindex=1 tdalign="center" style="width:10px" CustomFunction="doeditor" noprint="true">&nbsp;&nbsp;</th>
							<th fieldname="COMPANY_NAME" colindex=5 tdalign="left" style="width:250px" CustomFunction="doDm">&nbsp;企业全称&nbsp;</th>
							<th fieldname="COMPANY_CODE" colindex=4 tdalign="center" style="width:8px" >&nbsp;组织机构代码&nbsp;</th>
							<th fieldname="ZHIZHAO" colindex=8 tdalign="center" style="width:8px" >&nbsp;营业执照注册号&nbsp;</th>
							<!-- <th fieldname="DENGLU_CODE" colindex=1 tdalign="center" style="width:8px" >&nbsp;登录代码&nbsp;</th>  -->
							<th fieldname="SHUIWU" colindex=10 tdalign="center" style="width:8px" >&nbsp;税务登记号&nbsp;</th>
							<th fieldname="ZIZHI_NAME" colindex=7 tdalign="center" style="width:3px">&nbsp;主项资质&nbsp;</th>
							<th fieldname="WAIDI_Y" colindex=7 tdalign="center" style="width:3px">&nbsp;是否外地&nbsp;</th>
							<th fieldname="ZHIZHAO_VALID" colindex=9 tdalign="center" style="width:8px">&nbsp;执照有效期&nbsp;</th>
							   	 
							<th fieldname="TIJIAO_DATE" colindex=11 tdalign="center" style="width:5px">&nbsp;提交时间&nbsp;</th>
							
							<th fieldname="SHENHE_DATE" colindex=11 tdalign="center" style="width:5px">&nbsp;审核时间 &nbsp;</th>
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
		<input type="hidden" name="txtFilter" order="desc" fieldname="TIJIAO_DATE" id="txtFilter"/>
<%--		<input type="hidden" name="txtFilter" order="desc" fieldname="ID" id="txtFilter"/>--%>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>