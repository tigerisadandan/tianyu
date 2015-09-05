<!DOCTYPE html>
<html>
<head> 	
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<%
String isadd = request.getParameter("isadd");
 %>

<app:base/>
<title>审批业务流转实例首页</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>

<link rel="stylesheet"	href="${pageContext.request.contextPath }/js/kindeditor/themes/default/default.css" />
<link rel="stylesheet"	href="${pageContext.request.contextPath }/js/kindeditor/plugins/code/prettify.css" />
<script charset="UTF-8"	src="${pageContext.request.contextPath }/js/kindeditor/kindeditor.js"></script>
<script charset="UTF-8"	src="${pageContext.request.contextPath }/js/kindeditor/lang/zh_CN.js"></script>
			
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/ztree_css/demo.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/ztree_css/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ztree/jquery.ztree.excheck-3.5.min.js"></script>

<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/wxContentController";
var isadd="<%=isadd%>";
//页面初始化
$(function() {	
	init();	

	$("#btnClose").bind("click", function(){
		var a=$(this).manhuaDialog.close();
	});

	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
		if($("#buContentForm").validationButton()){
			var channel_uid=$("#CHANNEL_UID").val();

			//不能为0 也需要判断 为0是根节点
			if(channel_uid==null||channel_uid==''){
				xInfoMsg("栏目不能为空！");
				return;
			}
			
		    //生成json串
		    var data = Form2Json.formToJSON(buSpBzThlyForm);
		  //组成保存json串格式
		    var data1 = defaultJson.packSaveJson(data);
		  //调用ajax插入
		    if($("#CONTENT_UID").val() == "" || $("#CONTENT_UID").val() == null){
    			defaultJson.doInsertJson(controllername + "/insert", data1);
    			$("#buContentForm").clearFormResult();
    		}else{
    			defaultJson.doUpdateJson(controllername + "/update", data1);
    		}
			var a=$(window).manhuaDialog.getParentObj();
		    a.init();
			$(window).manhuaDialog.close();
		}else{
			requireFormMsg();
		  	return;
		}
	});
	var options1 = {
	        filterMode : true,
	      	items:['source', '|', 'fullscreen', 'undo', 'redo', 'cut', 'copy', 'paste','plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright','justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript','superscript', '|', 'selectall', '-','title', 'fontname', 'fontsize', '|', 'textcolor', 'bgcolor', 'bold','italic', 'underline', 'strikethrough', 'removeformat', '|',  'advtable', 'hr', 'emoticons', 'link', 'unlink']
	};
	var editor1 = KindEditor.create('textarea[id="CONTENT_TXT"]',options1);
});

function init(){
	if(isadd=='false'){//修改
		var parentmain = $(window).manhuaDialog.getParentObj();
		var rowValue = parentmain.$("#resultXML").val();
		var tempJson = convertJson.string2json1(rowValue);
		var content_uid = tempJson.CONTENT_UID;
		$("#QCONTENT_UID").val(content_uid);
		//查询表单赋值
		var data = combineQuery.getQueryCombineData(buForm, frmPost);
		var data1 = {
			msg : data
		};
		$.ajax({
			url : controllername + "/query",
			data : data1,
			cache : false,
			async : false,
			dataType : "json",
			type : 'post',
			success : function(response) {				
				var res = dealSpecialCharactor(response.msg);
				$("#resultXML").val(response.msg);
				var resultobj = defaultJson.dealResultJson(res);
				$("#buContentForm").setFormValues(resultobj);		
				return true;
			}
		});
	}else{
		$("#ENABLED").val(1);//新增时 是否有效初始化
	}


}


function selectFile(obj){
	var inputArr = $(".myKeyValueDiv input");
	for(var xx=0;xx<inputArr.length;xx++){
		if(inputArr[xx].getAttribute("cond")=="true"){
			if(inputArr[xx].getAttribute("condName")=="fjlb"){
				inputArr[xx].value=$(obj).attr("fjlb");
			}
		}
	}
	var contentid=$("#CONTENT_UID").val();
	if(contentid==null||contentid==''){
		contentid=$("#ywid").val();
	}
	setFileData(contentid,"","","222201","1","")
	document.getElementById("fileupload_btn").click();
}




function showChannel() {	
	var cityObj = $("#CHANNEL_NAME");
	var cityOffset = $("#CHANNEL_NAME").offset();
	var leftset=eval(cityOffset.left );
	var topset=eval(cityOffset.top );
	$("#channelContent").css({left:leftset + "px", top:topset + cityObj.outerHeight() + "px"}).slideDown("fast");
	$("body").bind("mousedown", onBodyDown);
}
function hideChannel() {
	$("#channelContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "channelid" || event.target.id == "channelContent" || $(event.target).parents("#channelContent").length>0)) {
		hideChannel();
	}
}

var setting = {
	async: {
		enable: true,
		url: "${pageContext.request.contextPath }/wndjsbg/wxChannelController/rightCondition",
		dataFilter: function (treeId, parentNode, responseData) {	
		   return responseData;		   
		}
	},
	check: {
		enable: true,
		chkStyle: "radio",
		radioType: "all"
	},
	data: {
		simpleData: {
			enable: true
		}
	},
	callback: {
		onCheck: zTreeOnCheck,
		onClick: zTreeOnClick
	}
};

function zTreeOnClick(event, treeId, treeNode) {
    //alert(treeNode.tId + ", " + treeNode.name);
};

function zTreeOnCheck(event, treeId, treeNode) {
	//alert(treeNode.name+"---------id:"+treeNode.id);
	$("#CHANNEL_UID").val(treeNode.id);
	$("#CHANNEL_NAME").val(treeNode.name);
	hideChannel();
}

$(document).ready(function(){
	$.fn.zTree.init($("#treeDemo"), setting);
});			

</script>
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">
  
	<div style="height:5px;"></div>
	<div class="B-small-from-table-autoConcise">
      <h4 class="title">新闻内容
      	<span class="pull-right">
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
	  		<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">保存</button>
      	</span>
      </h4>
     <form action="post" id="buForm">
		<input type="hidden" id="QCONTENT_UID" fieldname="CONTENT_UID" name="CONTENT_UID" operation="=" />
	 </form>
     <form method="post" id="buContentForm"  >
      <table class="B-table" width="100%" >
	  	<input type="hidden" id="CONTENT_UID" fieldname="CONTENT_UID" name = "CONTENT_UID"/></TD>
        <tr>				
			<th width="8%" class="right-border bottom-border text-right">标题</th>
       	 	<td colspan="3" class="bottom-border right-border">
       	 		<input id="CONTENT_TITLE" name="CONTENT_TITLE"  fieldname="CONTENT_TITLE"  type="text"  check-type="required" /> 
       	 	</td>
         	
        </tr>
        <tr>
      		<th width="8%" class="right-border bottom-border text-right">栏目名称</th>
			<td class="bottom-border right-border" width="23%">					
				<button class="btn btn-link" id="channelid" type="button" onclick="showChannel()" >
					<input  id="CHANNEL_NAME" name="CHANNEL_NAME" readOnly type="text" value='--请选择--' />				
				</button>		
				<input type="hidden" name="CHANNEL_UID" id="CHANNEL_UID" fieldname="CHANNEL_UID" />	
			</td>
			
        	<th width="8%" class="right-border bottom-border text-right">是否有效</th>
       		<td class="bottom-border right-border" width="15%">
         		<select id="ENABLED" class="span12" style="width:50%" name="ENABLED" fieldname="ENABLED"  kind="dic" src="SF" defaultMemo="--请选择--"></select> 
         	</td>
	        
        </tr>
		<tr>
	        <th width="8%" class="right-border bottom-border text-right">内容简述</th>
	        <td colspan="3" class="bottom-border right-border" >
	        	<textarea class="span12" rows="2" id="CONTENT_STXT"  fieldname="CONTENT_STXT" name="CONTENT_STXT" style="width:70%"></textarea>
	        </td>
        </tr>
        <tr>
	        <th width="8%" class="right-border bottom-border text-right">发布日期</th>
	        <td class="bottom-border right-border" width="23%">
	        	<input type="text" id="PUBLISH_TIME" name="PUBLISH_TIME" fieldname="PUBLISH_TIME" fieldformat='YYYY-MM-DD' class='Wdate' onClick='WdatePicker()' />
	        </td>
	        <th width="8%" class="right-border bottom-border text-right">排序号</th>
	        <td class="bottom-border right-border" width="15%">
         		<input id="SERIAL_NO" name="SERIAL_NO"  fieldname="SERIAL_NO" value=10 type="number"  check-type="required" />
         	</td>
        </tr>
        <tr>
	        <th width="8%" class="right-border bottom-border text-right">内容图片</th>
	        <td colspan="3" class="bottom-border right-border" >
	        	<span class="btn btn-fileUpload" id="addFileSpan" onclick="selectFile(this);" fjlb="2201"> 
					<i class="icon-plus"></i> <span>添加图片...</span> 
				</span>
				<table role="presentation" class="table table-striped">
					<tbody fjlb="2201" class="files showFileTab"	data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>
				</table>
	        </td>
        </tr>
        <tr>
	        <th width="8%" class="right-border bottom-border text-right">内容</th>
	        <td colspan="3" class="bottom-border right-border" >
	        	<textarea class="span12" rows="6" id="CONTENT_TXT" fieldname="CONTENT_TXT" name="CONTENT_TXT"></textarea>
	        </td>
        </tr>
        <tr>
	        <th width="8%" class="right-border bottom-border text-right">备注</th>
	        <td colspan="3" class="bottom-border right-border" >
	        	<textarea class="span12" rows="2" id="DESCRIBE" check-type="maxlength" maxlength="4000" fieldname="DESCRIBE" name="DESCRIBE"  style="width:70%"></textarea>
	        </td>
        </tr>
      </table>
      </form>  
		  <div id="channelContent" class="channelContent" style="display:none; position: absolute;">
			<ul id="treeDemo" class="ztree" style="margin-top:0; width:240px;"></ul>
		  </div>
    </div>
   </div>
  </div>
 <jsp:include page="/jsp/file_upload/fileuploadold_config.jsp" flush="true"/> 
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
</html>