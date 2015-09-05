<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp" %>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base />
<title>审批业务材料库-维护</title>
<%
	String type=request.getParameter("type");
%>

<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/spxx/buSpClkController";
var type ="<%=type%>";
//页面初始化
	$(function() {
		$("#addFileSpan").hide();
		init();
		//按钮绑定事件(保存)
		$("#btnSave").click(function() {
		
			if($("#buRequirementsForm").validationButton()){
				//alert("----------2222");	
			    //生成json串
			    var data = Form2Json.formToJSON(buRequirementsForm);
			  //组成保存json串格式
			    var data1 = defaultJson.packSaveJson(data);
			  //调用ajax插入
			    if($("#CLK_UID").val() == "" || $("#CLK_UID").val() == null){
	    			defaultJson.doInsertJson(controllername+ "?insert", data1);
	    		}else{
	    			defaultJson.doInsertJson(controllername+ "?update", data1);
	    		}
			    $("#buRequirementsForm").clearFormResult();
				var a=$(window).manhuaDialog.getParentObj();
			    a.init();
				$(window).manhuaDialog.close();
			}else{
				requireFormMsg();
			  	return;
			}
		});
	
		$("#btnClose").bind("click", function(){
			var a=$(this).manhuaDialog.close();
		});	
		
		//按钮绑定事件（清空）
	    $("#btnClear").click(function() {
	        $("#buRequirementsForm").clearFormResult();
	    });
	    
	    <%
	    if(type.equals("detail")){
		%>
		//置所有input 为disabled
		$(":input").each(function(i){
		   $(this).attr("disabled", "true");
		 });	
		$("#btnClose").attr("disabled", false);	
		$("#addFileSpan").hide();
		<%
			}
		%>		
	});

	
//页面默认参数
function init(){

	if (type == "insert") {
				
	} else if (type == "detail") {
		//生成json串
		var parentmain = $(window).manhuaDialog.getParentObj();
		var rowValue = parentmain.$("#resultXML").val();
		var tempJson = convertJson.string2json1(rowValue);
		$("#ID").val(rwid);
		//查询表单赋值
		var data = combineQuery.getQueryCombineData(buForm, frmPost);
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
				//alert($("#resultXML").val());
				var resultobj = defaultJson.dealResultJson(res);
				$("#buRequirementsForm").setFormValues(resultobj);
				
				return true;
			}
		});
		
	} else {
		//生成json串
		var parentmain = $(window).manhuaDialog.getParentObj();
		var rowValue = parentmain.$("#resultXML").val();
		var tempJson = convertJson.string2json1(rowValue);
		var rwid = tempJson.CLK_UID;
		$("#ID").val(rwid);//点击修改是通过该ID数据查询
		//查询表单赋值
		var data = combineQuery.getQueryCombineData(buForm, frmPost);
//alert("---data---"+data);		
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
			//alert($("#resultXML").val());
			var resultobj = defaultJson.dealResultJson(res);
			$("#buRequirementsForm").setFormValues(resultobj);
             var s=resultobj.SFYFJ;	
			if(s==1){
			 	$("#addFileSpan").show();
			}else{
				$("#addFileSpan").hide();
				}

			//加载附件
			queryFileData(resultobj.CLK_UID, "", "", "");
			return true;
			}
		});
		$("#clk").attr("onlyView","true");
		//setFileData(tempJson.CLK_UID, "", "", "", "1");
		//queryFileData(tempJson.CLK_UID, "", "", "");
	}
}

function sfyfj(sfyfj){
	var s = sfyfj.value;
 	if(s==1){
	 $("#addFileSpan").show();
	// document.getElementById('sfyfjtrid').style.display='none';
	}else{
		$("#addFileSpan").hide();
		//document.getElementById('sfyfjtrid').style.display='none';
		}
	}


function selectClkFile(obj){
	$.ajax({
		url:controllername+"?getYwid",
		data:"",
		dataType:"json",
		async:false,
		success:function(result){
			var ywid = result.msg;
	//alert("--------ywid----"+ywid);		
	//		setFileData(ywid,"", "", "", "1","");

			var inputArr = $(".myKeyValueDiv input");
			for(var xx=0;xx<inputArr.length;xx++){
				if(inputArr[xx].getAttribute("cond")=="true"){
					if(inputArr[xx].getAttribute("condName")=="ywid"){
						inputArr[xx].value=ywid;
					}else if(inputArr[xx].getAttribute("condName")=="fjlb"){
						inputArr[xx].value=$(obj).attr("fjlb");
					}
				}
			}
		}
	});

	document.getElementById("fileupload_btn").click();
}
</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">  
	<div style="height:5px;"></div>
	<div class="B-small-from-table-autoConcise">
      <h4 class="title">审批业务材料库
      	<span class="pull-right">
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
			<% if(!"detail".equals(type)){ %>
				<button id="btnClear_Bins" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">清空</button>
				<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">保存</button>
			<%}%>
      	</span>
      </h4>
      <form action="post" id="buForm">
		<input type="hidden" id="ID" fieldname="CLK_UID" name="ID" operation="=" />
	 </form>
     <form method="post" id="buRequirementsForm" >
      <table class="B-table" width="100%" >
      		<input type="hidden" id="ywid" fieldname="ywid" name="ywid" />
      		<input type="hidden" id id="CLK_UID" fieldname="CLK_UID" name="CLK_UID"/>
	    <tr>
			<th width="8%" class="right-border bottom-border text-right">材料名称</th>
       	 	<td class="bottom-border right-border" width="23%">
         		<input type="text" name="CLMC" id="CLMC" fieldname="CLMC" check-type="required"  size=1000 style="width: 90%;" />
       	 	</td>
         	<th width="8%" class="right-border bottom-border text-right">材料级别</th>
       		<td class="bottom-border right-border"width="15%">
         		<select id="CL_LEVEL" name="CL_LEVEL" fieldname="CL_LEVEL" check-type="required"  operation="=" kind="dic" src="SPYWCLJB"  defaultMemo="-请选择-" />
         	</td>
        </tr>
        <tr>
			<th width="8%" class="right-border bottom-border text-right">排序号</th>
       	 	<td class="bottom-border right-border" width="23%">
         		<input type="number" name="SERIAL_NO" id="SERIAL_NO" fieldname="SERIAL_NO" check-type="required"  size=100 style="width: 90%;" />
       	 	</td>
         	<th width="8%" class="right-border bottom-border text-right">材料类型</th>
       		<td class="bottom-border right-border"width="15%">
         		<select id="CLLX" name="CLLX" fieldname="CLLX" operation="=" kind="dic" src="SPYWCLLX"  defaultMemo="-请选择-" />
         	</td>
        </tr>
      
         <tr>
         	<th width="8%" class="right-border bottom-border text-right">是否有附件</th>
       		<td class="bottom-border right-border"width="15%">
         		<select id="SFYFJ" name="SFYFJ"  onchange="sfyfj(this)" fieldname="SFYFJ" operation="=" kind="dic" src="SF"  defaultMemo="-请选择-" />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">是否有效</th>
       		<td class="bottom-border right-border"width="15%">
         		<select id="ENABLED" name="ENABLED"  fieldname="ENABLED" operation="=" kind="dic" src="SF"  defaultMemo="-请选择-" />
         	</td>
        </tr>
        <tr>
	        <th width="8%" class="right-border bottom-border text-right">备注</th>
	        <td colspan="3" class="bottom-border right-border" >
	        	<textarea class="span12" rows="2" id="DESCRIBE" check-type="maxlength" maxlength="4000" fieldname="DESCRIBE" name="DESCRIBE"></textarea>
	        </td>
        </tr>
        <tr id="sfyfjtrid">
			<th width="8%" class="right-border bottom-border text-right">相关附件</th>
			<td colspan="3" class="bottom-border right-border">
				<div>
					<span class="btn btn-fileUpload" id="addFileSpan" onclick="selectClkFile(this);" fjlb="1607"  >
						<i class="icon-plus"></i> <span>添加文件...</span> 
					</span>
					<table role="presentation" class="table table-striped">
						<tbody fjlb="1607" id="clk" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"  <% if("detail".equals(type)){ out.print("\"onlyView\"=true");} %> ></tbody>
					</table>							
				</div>
			</td>		
		</tr>
      </table>
      </form>
    </div>
   </div>
  </div>
 <jsp:include page="/jsp/file_upload/fileuploadold_config.jsp" flush="true"/> 
   
  <div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id = "queryXML">
         <input type="hidden" name="txtXML" id = "txtXML">       
         <input type="hidden" name="txtFilter"  order="desc" fieldname = "SERIAL_NO" id = "txtFilter">
         <input type="hidden" name="resultXML" id = "resultXML">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
		
 	</FORM>
 </div>
</body>
<script>
</script>
</html>