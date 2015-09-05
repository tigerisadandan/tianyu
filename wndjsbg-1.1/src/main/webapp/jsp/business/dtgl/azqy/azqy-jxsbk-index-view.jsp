<!DOCTYPE html>
<%@page import="com.ccthanking.framework.Constants"%>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>建设单位-维护</title>
<%
	String type=request.getParameter("type");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/jxsb/jxsbController";
var controllernameBgMenu="${pageContext.request.contextPath }/bgMenuController/";


var type ="<%=type%>";
//页面初始化
$(function() {
	init();
	$("#btnClose").bind("click", function(){
		var a=$(this).manhuaDialog.close();
	});
	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
	if($("input:radio[name='SHENHE_JIEGUO']:checked").val()==2){
	msg="确认审核不通过？";
	}else{
	msg="确认审核通过？";
	}
	       if(confirm(msg)){
		    //生成json串
		    var data = Form2Json.formToJSON(personForm);
		    
		  //组成保存json串格式
		    var data1 = defaultJson.packSaveJson(data);
		  //调用ajax插入
		    if($("#JXSB_UID").val() == "" || $("#JXSB_UID").val() == null){
    			defaultJson.doInsertJson(controllername + "?insert", data1);
    			$("#personForm").clearFormResult();
    		}else{

    			if($("input:radio[name='SHENHE_JIEGUO']:checked").val()==2){
    				 var reason = $("#SHENHE_YIJIAN").val();
    				 if(reason.length==0){
 						alert("请填写审批不通过理由!");
 						return;
 						}else{defaultJson.doUpdateJson(controllername + "?update", data1);}
    			}else{
    			defaultJson.doUpdateJson(controllername + "?update", data1);
    			}
    		}
		    var a=$(window).manhuaDialog.getParentObj();
		    a.init();
			$(window).manhuaDialog.close();
		}
	});
	
	$("#btnWb").click(function() {
		$(window).manhuaDialog({"title":"设备保养、维护记录","type":"text","content":"${pageContext.request.contextPath }/jsp/business/dtgl/azqy/azqy-jxsb-wbjl-index.jsp?JXSB_UID="+$("#JXSB_UID").val(),"modal":"2"});
	});	 
	$("#btnDs").click(function() {
		$(window).manhuaDialog({"title":"机械设备顶升加节附墙记录","type":"text","content":"${pageContext.request.contextPath }/jsp/business/dtgl/azqy/azqy-jxsb-dsjl-index.jsp?JXSB_UID="+$("#JXSB_UID").val(),"modal":"2"});
	});	 
	$("#btnJj").click(function() {
		$(window).manhuaDialog({"title":"机械设备降级使用记录","type":"text","content":"${pageContext.request.contextPath }/jsp/business/dtgl/azqy/azqy-jxsb-jjjl-index.jsp?JXSB_UID="+$("#JXSB_UID").val(),"modal":"2"});
	});	 
	$("#btnSy").click(function() {
		$(window).manhuaDialog({"title":"机械设备使用登记记录","type":"text","content":"${pageContext.request.contextPath }/jsp/business/dtgl/azqy/azqy-jxsb-syjl-index.jsp?JXSB_UID="+$("#JXSB_UID").val(),"modal":"2"});
	});
	$("#btnJy").click(function() {
		$.ajax({
			url : controllername+"?disabledJXSB",
			data : {"jxsb_uid":$("#QJXSB_UID").val(),"type":$(this).val()},
			type : "post",
			dataType : "json",
			success: function(response){
				if(response.success){
					xAlert("提示信息","操作成功");
					 //刷新父页面缓存
					 var a=$(window).manhuaDialog.getParentObj();
		  		 	 a.init();
		  		 	 if($("#btnJy").val() == 1){
		  		 	 	$("#btnJy").text("禁用");
						$("#btnJy").val("0");
		  		 	 }else{
		  		 	 	$("#btnJy").text("启用");
						$("#btnJy").val("1");
		  		 	 }
				}
				
			}
		});
	});	 	 
	
});


//页面默认参数
function init(){
	//生成json串
	//var data = combineQuery.getQueryCombineData(queryForm,frmPost);
	//调用ajax插入
//	defaultJson.doQueryJsonList(controllername+"?query",data);
	$("#shrsjdiv").hide();
	$("#shrsj1div").hide();	
	if (type == "insert") {
		//生成json串
			
	} else if (type == "detail") {
	//验证是否有权显示禁用/启用按钮
		$.ajax({
			url : controllernameBgMenu+"?getByMenuCodeAndUserId",
			data : {code : "2060001"},
			type :"post",
			dataType : "json",
			success : function(response){
				if(response.msg == "1"){
					$("#btnJy").show();
				}else{
					$("#btnJy").hide();
				}
			}
		});
		//生成json串
		var parentmain = $(window).manhuaDialog.getParentObj();
		var rowValue = parentmain.$("#resultXML").val();
		var tempJson = convertJson.string2json1(rowValue);

		$("#QJXSB_UID").val(tempJson.JXSB_UID);
		if(tempJson.ENABLED == 1){
			//显示按钮文字为禁用
			$("#btnJy").text("禁用");
			$("#btnJy").val("0");
		}else{
			$("#btnJy").text("启用");
			$("#btnJy").val("1");
		}
		
		//查询表单赋值
		var data = combineQuery.getQueryCombineData(personQueryForm, frmPost);
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
			$("#shrsjdiv").show();
			$("#shrsj1div").show();	
			var res = dealSpecialCharactor(response.msg);
			$("#resultXML").val(response.msg);
			var resultobj = defaultJson.dealResultJson(res);
			$("#personForm").setFormValues(resultobj);				
			return true;
			}
		});
	//	queryFileData(tempJson.JS_COMPANY_UID, "", "", "460010");
	} else {
		//生成json串
		var parentmain = $(window).manhuaDialog.getParentObj();
		var rowValue = parentmain.$("#resultXML").val();
		var tempJson = convertJson.string2json1(rowValue);
		$("#QJXSB_UID").val(tempJson.JXSB_UID);
		
		//安装人员
		var data = combineQuery.getQueryCombineData(personQueryForm, frmPost);
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
				resultobj.SHENHE_JIEGUO="1";//默认通过
				$("#personForm").setFormValues(resultobj);	
		
				return true;
			}
		});
		
	}
}


</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">
	
	<div class="B-small-from-table-autoConcise">
      <h4 class="title">起重设备产权信息 
      	<span class="pull-right">
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
	  		<button id="btnWb" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">维保</button>
	  		<button id="btnJj" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">降级</button>
	  		<button id="btnDs" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">顶升</button>
	  		<button id="btnSy" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">使用</button>
	  		 <button id="btnJy" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"></button> 		
      	</span>
      </h4>
       <form method="post" id="personQueryForm"  >
     		<input type="hidden" id="QJXSB_UID" fieldname="JXSB_UID" name="JXSB_UID" operation="=" />
      </form>
      <div style="height:5px;"></div>
     <form method="post" id="personForm"  >
     	<table class="B-table" width="100%" >
      		<input type="hidden" id="JXSB_UID" fieldname="JXSB_UID" name = "JXSB_UID"/>
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">产权单位</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12"  id="CQDW" type="text" readonly="readonly"  fieldname="CQDW" name = "CQDW"  />	         	        	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">设备产权编号  </th>
	       	 	<td colspan="3" class="bottom-border right-border">
	       	 	<div id="dis">
					  <input   id="CQBH" type="text" fieldname="CQBH" name = "CQBH"  readonly="readonly"  />
					  </div
				</td>
	        </tr>
	         <tr>
				<th width="15%" class="right-border bottom-border text-right">设备类型</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12"  id="JXSB_TYPE_UID" type="text" readonly="readonly"  fieldname="JXSB_TYPE_UID_SV" name = "JXSB_TYPE_UID"  />	         	        	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">设备型号  </th>
	       	 	<td colspan="3" class="bottom-border right-border">
	       	 	<div id="dis">
					  <input  id="SB_XH" type="text" readonly="readonly"  name = "SB_XH" fieldname="SB_XH" />
					  </div
				</td>
	        </tr>
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">制造厂家</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12"  id="ZZDW" type="text" readonly="readonly"  fieldname="ZZDW" name = "ZZDW"  />	         	        	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">制造许可证</th>
	       	 	<td colspan="3" class="bottom-border right-border">
	       	 	<div id="dis">
					  <input  id="ZZXKZ" type="text" readonly="readonly"  name = "ZZXKZ" fieldname="ZZXKZ" />
					  </div
				</td>
	        </tr>
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">产权单位地址</th>
	       	 	<td class="bottom-border right-border" style="width:32%" colspan="3">
	         		<input class="span12"  id="CQDW_ADDRESS" type="text" readonly="readonly"  fieldname="CQDW_ADDRESS" name = "CQDW_ADDRESS"  />	         	
	       	 	</td>
	        </tr>	
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">出厂编号</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12"  id="CC_CODE" type="text" readonly="readonly"  fieldname="CC_CODE" name = "CC_CODE"  />	         	        	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">有效期（设备寿命期）</th>
	       	 	<td colspan="3" class="bottom-border right-border">
	       	 	<div id="dis">
					  <input  id="WAIDI_Y" type="text" readonly="readonly"  name = "YOUXIAO_DATE" fieldname="YOUXIAO_DATE" />
					  </div
				</td>
	        </tr>
	         <tr>
				<th width="15%" class="right-border bottom-border text-right">备注</th>
	       	 	<td class="bottom-border right-border" style="width:32%" colspan="3">
	         		<textarea class="span12" rows="4"  id="DESCRIBE"  readonly="readonly"  fieldname="DESCRIBE" name = "DESCRIBE" ></textarea>	         	
	       	 	</td>
	        </tr>
			
      	</table>
      </form>
    </div>
   </div>
  </div>

  <jsp:include page="/jsp/file_upload/fileuploadold_config_js.jsp" flush="true"/> 
  <div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id = "queryXML">
         <input type="hidden" name="txtXML" id = "txtXML">
         <input type="hidden" name="txtFilter" order="desc" fieldname="SHENHE_JIEGUO" id="txtFilter"/>
		 <input type="hidden" name="txtFilter" order="desc" fieldname="CREATED_DATE" id="txtFilter"/>
         <input type="hidden" name="resultXML" id = "resultXML">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
		
 	</FORM>
 </div>
</body>
<script>
</script>
</html>