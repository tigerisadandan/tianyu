<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>预选承包商-维护</title>
<%
	String type=request.getParameter("type");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/wxgc/yxYxcbsController";
var type ="<%=type%>";
//页面初始化
$(function() {
	init();
	
	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
		if($("#yxYxcbsForm").validationButton()){
			xConfirm("信息确认","确认提交审核结果？");
			$('#ConfirmYesButton').attr('click','');
			$('#ConfirmYesButton').one("click",function(){
			    //生成json串
			    var data = Form2Json.formToJSON(yxYxcbsForm);
			  //组成保存json串格式
			    var data1 = defaultJson.packSaveJson(data);
			  //调用ajax插入
			    if($("#YXCBS_UID").val() != ""&& $("#YXCBS_UID").val() != null){
	    			defaultJson.doInsertJson(controllername + "?update", data1);
	    		}
	
			    var a=$(window).manhuaDialog.getParentObj();
			    a.init();
				$(window).manhuaDialog.close();
			});	
		}else{
			requireFormMsg();
		  	return;
		}
	});

	$("#btnClose").bind("click", function(){
		var a=$(this).manhuaDialog.close();
	});

    <%
    if(type.equals("detail")){
	%>
	//置所有input 为disabled
	$(":input").each(function(i){
	   $(this).attr("disabled", "true");
	 });
	 
	 $("#shjltxdivid").hide();
	 $("#btnClose").removeAttr('disabled');
	 $("#viewxxid").removeAttr('disabled');
	<%
		}
	%>
	
});
//页面默认参数
function init(){

	if(type == "insert"){
		
	}else if(type == "update" || type == "detail"){
		//生成json串
		var parentmain = $(window).manhuaDialog.getParentObj();
		var rowValue = parentmain.$("#resultXML").val();
		var tempJson = convertJson.string2json1(rowValue);
		$("#QYXCBS_UID").val(tempJson.YXCBS_UID);
		//查询表单赋值
		var data = combineQuery.getQueryCombineData(queryForm, frmPost);
		var data1 = {
			msg : data
		};
		$.ajax({
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
				$("#yxYxcbsForm").setFormValues(resultobj);		
				return true;
			}
		});

		//列出审批意见信息
		var queryconditionXX = "{\"logic\":\"and\",\"fieldname\":\"YXCBS_UID\",\"operation\":\"=\",\"value\":\""+tempJson.YXCBS_UID+"\",\"fieldtype\":'',\"fieldformat\":''}";
		var dataXX = "{querycondition: {conditions: [" +queryconditionXX+"]},pages:{recordsperpage:200, currentpagenum:1, totalpage:0, countrows:0},orders:[{\"fieldname\":\"SHRQ\",\"order\":\"asc\"}]}";
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"?queryspyj", dataXX, spyjList);
		
		querycbsgctype();
	}
}


//该企业已选择的微型工程类型
function querycbsgctype(){
	var yxcbsuid=$("#QYXCBS_UID").val();
	//alert(yxcbsuid);
	if(yxcbsuid==null||yxcbsuid==''){
		return;
	}
	$.ajax({
		url : controllername + "?querycbsgctype&YXCBS_UID="+yxcbsuid,
		//data : data1,
		cache : false,
		async : false,
		dataType : "json",
		type : 'post',
		success : function(response) {				
			var res = dealSpecialCharactor(response.msg);
			var resultobj = convertJson.string2json1(res);
			$.each(resultobj,function(i){
				var obj=this;
				var typecode=obj.GC_TYPE_CODE;
				if(typeof(typecode)!='undefined'&&typecode!=null){
					var r=document.getElementsByName("GC_TYPE_CODE");  
				    for(var i=0;i<r.length;i++){
				    	if(r[i].value==typecode){
				    		r[i].checked="checked";
					    }
				    	r[i].disabled=true;
				    } 
				}
			});
			return true;
		}
	});

}

//SG－施工；JL－监理；LH－绿化；KC－勘察设计

function viewxx(){
	var CBS_TYPE=$("#CBS_TYPE").val();
	var COMPANY_UID=$("#COMPANY_UID").val();
	var url="";
	if(CBS_TYPE=='KC'){
		url="${pageContext.request.contextPath }/jsp/business/wxgc/yxyxcbs-view-kc.jsp?COMPANY_UID="+COMPANY_UID;
		$(window).manhuaDialog({"title":"预选承包商详细信息","type":"text","content":url,"modal":"2"});	
	}else if(CBS_TYPE=='LH'){
		url="${pageContext.request.contextPath }/jsp/business/wxgc/yxyxcbs-view-lh.jsp?COMPANY_UID="+COMPANY_UID;
		$(window).manhuaDialog({"title":"预选承包商详细信息","type":"text","content":url,"modal":"2"});	
	}else if(CBS_TYPE=='SG'){
		window.open("${pageContext.request.contextPath }/sgentview/"+COMPANY_UID,"预选承包商详细信息");//传递一个ID给详细页面，用于查找 
	}else if(CBS_TYPE=='JL'){
		url="${pageContext.request.contextPath }/jsp/business/wxgc/yxyxcbs-view-jl.jsp?COMPANY_UID="+COMPANY_UID;
		$(window).manhuaDialog({"title":"预选承包商详细信息","type":"text","content":url,"modal":"2"});	
	}else if(CBS_TYPE=='SB'){
		url="${pageContext.request.contextPath }/jsp/business/wxgc/yxyxcbs-view-cl.jsp?COMPANY_UID="+COMPANY_UID;
		$(window).manhuaDialog({"title":"预选承包商详细信息","type":"text","content":url,"modal":"2"});	
	}
}

</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">
	<div class="B-small-from-table-autoConcise">
	 <h4 class="title">
      	<span class="pull-right"> 
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>   		
      	</span>
      </h4>
     	<form method="post" id="yxYxcbsForm"  >
	      <table class="B-table" width="100%" >
		  	 <input type="hidden" id="YXCBS_UID" fieldname="YXCBS_UID" name = "YXCBS_UID"/>
		  	 <input type="hidden" id="CBS_TYPE" fieldname="CBS_TYPE" name = "CBS_TYPE"/>
		  	 <input type="hidden" id="COMPANY_UID" fieldname="COMPANY_UID" name = "COMPANY_UID"/>
		  	 
		  	   <tr>
				  	<td  colspan="4">
					<div class="modal-header" style="background:#0866c6;">
						<h3 style="color:white;">预选承包商申请工程信息</h3>
					</div>
					</td>
				</tr>
	       	  <tr>
				
	         	<th width="8%" class="right-border bottom-border text-right">承包商名称</th>
	       		<td class="bottom-border right-border" width="15%">
	         		<input id="USERNAME" readonly="readonly" type="text"  fieldname="USERNAME" name = "USERNAME"  />
	         		<button id="viewxxid" class="btn btn-link" style="width:20%"  type="button" onclick="viewxx()" >查看详情</button>
	         	</td>
	         	<th width="8%" class="right-border bottom-border text-right">承包商类型</th>
	       	 	<td class="bottom-border right-border"  width="15%">
	         		<input id="CBS_TYPE_SV" readonly="readonly" type="text"  fieldname="CBS_TYPE_SV" name = "CBS_TYPE_SV"  />
	          		
	       	 	</td>
	       	 </tr>
	       	 <tr>
				<th width="8%" class="right-border bottom-border text-right">工程类型</th>
	       	 	<td class="bottom-border right-border"   colspan="3">
	       	 		<input type="checkbox"  id="GC_TYPE_CODE" fieldname="GC_TYPE_CODE" name="GC_TYPE_CODE" kind="dic" src="T#YX_GC_TYPE:GC_TYPE_CODE:GC_TYPE_NAME:ENABLED='1' order by SERIAL_NO asc " >
	       	 	</td> 	
	       	</tr>
	     </table>

	
	
       <div style="height: 5px;"></div>
       
      	<div id="shjltxdivid">
			<div class="modal-header" style="background:#0866c6;">
			<h3 style="color:white;">预选承包商审核
				<span class="pull-right">
					<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">审核</button>
	  			</span>
			</h3>
			</div>
     			<table id="shenheTable" class="B-table" width="100%" >
		        <tr>
					<th width="15%" class="right-border bottom-border text-right">是否通过</th>
		       	 	<td class="bottom-border right-border">
		       	 		<input id="SHJG" type="radio" check-type="required" fieldname="SHJG" name = "SHJG" defaultValue="1" kind="dic" src="SHYJ_SY" />	
		       	 	</td>
		        </tr>
		       	<tr>
					<th width="15%" class="right-border bottom-border text-right">审核意见</th>
		       	 	<td class="bottom-border right-border">
		       	 		<textarea class="span12" rows="2" id="SHYJ" check-type="required" maxlength="4000"  fieldname="SHYJ" name="SHYJ"></textarea>
		       	 	</td>
		        </tr>
			</table>
		</div>
      </form>
      
      	<div style="height: 5px;"></div>
		<table id="YJ_LIST"  border="0" width="100%" cellpadding="0" cellspacing="0" class="content">
			<div class="modal-header" style="background:#0866c6;">
					<h3 style="color:white;">审核记录</h3>
			</div>
			<tr>
				<td>
					<div class="overFlowX">
				 		<table width="100%" class="table-hover table-activeTd B-table" id="spyjList" type="single" pageNum="2000"  noPage="true">
					    <thead>
					 		<tr style="display:blank">
					           <th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
					            <th fieldname="SHR" style="width:200px;" colindex=1 tdalign="center" >审核人</th>
					            <th fieldname="SHRQ_SV" style="width:250px;" colindex=2 tdalign="center" maxlength="200" >&nbsp;审核时间&nbsp;</th>
								<th fieldname="SHJG_SV" colindex=2 tdalign="center" maxlength="200" >&nbsp;审核结果&nbsp;</th>
								<th fieldname="SHYJ" colindex=2 tdalign="center" maxlength="200" >&nbsp;审核意见&nbsp;</th>
					        </tr>
					    </thead>
					    <tbody></tbody>
					  </table>  
		       		</div>
				</td>
			</tr>
		</table>
		
    </div>
   </div>
  </div>
    <form method="post" id="queryForm"  >
    	  <input type="hidden" id="QYXCBS_UID" fieldname="YXCBS_UID" name = "YXCBS_UID" operation="="/>
    </form>
     
  <div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id = "queryXML">
         <input type="hidden" name="txtXML" id = "txtXML">
         <input type="hidden" name="txtFilter"  order="desc" fieldname = "YXCBS_UID" id = "txtFilter">
         <input type="hidden" name="resultXML" id = "resultXML">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
		
 	</FORM>
 </div>
</body>
<script>
</script>
</html>