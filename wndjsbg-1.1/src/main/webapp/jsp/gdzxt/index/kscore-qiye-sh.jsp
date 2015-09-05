<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>违章违规查看</title>
<%
	String sUid = request.getParameter("sUid");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername = "${pageContext.request.contextPath }/yhzg/scoreController";

//页面初始化
$(function() {
	init();
	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
		if($("#kscoreForm").validationButton()){
		    //生成json串
		    var data = Form2Json.formToJSON(kscoreForm);
		  //组成保存json串格式
		    var data1 = defaultJson.packSaveJson(data);
		  //调用ajax插入
    		defaultJson.doUpdateJson(controllername + "?updateCompanySh", data1);
		    //var a=$(window).manhuaDialog.getParentObj();
		    //a.init();
		    parent.location.reload(); 
			$(window).manhuaDialog.close();
		}else{
			requireFormMsg();
		  	return;
		}
	});

	$("#btnClose").bind("click", function(){
		var a=$(this).manhuaDialog.close();
	});	


});
//页面默认参数
function init(){
	//生成json串
	//var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT3);
	//调用ajax插入
	//defaultJson.doQueryJsonList(controllernameContent+"?query",data,DT3);
	//$('#FAFANG_DATE').val(getNowDate());

	var sUid = $('#sUid').val();
	$.ajax({
		url : controllername + "?queryById&sUid="+sUid,
		cache : false,
		async : false,
		dataType : "json",
		type : 'post',
		success : function(response) {				
			var res = dealSpecialCharactor(response.msg);
			$("#resultXML").val(response.msg);
			var resultobj = defaultJson.dealResultJson(res);
			$("#kscoreForm").setFormValues(resultobj);
			var sh_status = resultobj.SH_STATUS;
			if(sh_status=='0'){
				$("#shcontent").show();
				$("#shtitle").show();
			}
			//$('#GANGWEI_UID').val(resultobj.GANGWEI_UID);	
			//showTr(resultobj.SHENPI_STATUS);	
		}
	});
}

function seeXy(){
	var clDx = $('#clDx').val();
	var clUid = $('#clUid').val();
	var clType = $('#clType').val();
	window.open("qiye-xypf.jsp?clDx="+clDx+"&clUid="+clUid+"&clType="+clType);
}

</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">
	<div style="height:5px;"></div>
	<div class="B-small-from-table-autoConcise">
     <form method="post" id="kscoreForm"  >
      <table class="B-table" width="100%" >
        <div class="modal-header" style="background:#0866c6;">
			<h3 style="color:white;">单位违规违章 
				<span class="pull-right">
					<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
					<button id="btnTg" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">修改</button>
	  			</span>
	  			</h3>
			</div>
         <tr>
         	<input type="hidden" id="sUid" value="<%=sUid %>" fieldname="SCORE_UID" />
         	<input type="hidden" fieldname="CHULI_DUIXIANG" id="clDx"/>
         	<input type="hidden" fieldname="CHULI_UID" id="clUid"/>
         	<input type="hidden" fieldname="CHULI_TYPE" id="clType"/>
			<th width="8%" class="right-border bottom-border text-right">单位名称</th>
       		<td class="bottom-border right-border"  colspan="3" >
         		<input readonly="readonly"  type="text" fieldname="CHULI_DUIXIANG_SV"  style="width: 80%"/>
         	</td>
         	
         </tr>

        <tr>
			<th width="8%" class="right-border bottom-border text-right">当前分数</th>
       		<td class="bottom-border right-border"  colspan="3">
       			<input type="text" readonly="readonly"  fieldname="KOU_JS" style="width: 80%">
       			<a href="javascript:void(0);" onclick="seeXy();">查看信用评分</a>
         	</td>
         </tr>

         
         <tr>
			<th width="8%" class="right-border bottom-border text-right">处罚原因</th>
       		<td class="bottom-border right-border" colspan="3">
         		<textarea rows="" cols="" style="width: 80%" fieldname="YUANYIN"></textarea>
         	</td>

         </tr>
         <tr>
			<th width="8%" class="right-border bottom-border text-right">处罚依据</th>
       		<td class="bottom-border right-border" colspan="3">
         		<textarea rows="" cols="" style="width: 80%" fieldname="YIJU"></textarea>
         	</td>

         </tr>
        <tr>
			<th width="8%" class="right-border bottom-border text-right">处罚类型</th>
       		<td class="bottom-border right-border" width="15%">
         		<input   type="text" fieldname="CHULI_TYPE_SV"  />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">扣分分值</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  type="text" fieldname="CHANGE_SCORE"   />
         	</td>
         </tr>
         <tr>
			<th width="8%" class="right-border bottom-border text-right">处罚结果</th>
       		<td class="bottom-border right-border"colspan="3">
         		<textarea rows="" cols="" style="width: 80%" fieldname="JIEGUO"></textarea>
         	</td>
         </tr>
      </table>
		<div class="modal-header" style="background:#0866c6; display: none;" id="shtitle">
		<h4 id="ryxx_title" style="color:white;">审核
		<span class="pull-right">
			   <button id="btnSave" class="btn" type="button">确定</button>
		</span>
			</h4>
		</div>
		<div class="overFlowX" id="shcontent" style="display: none;">
			<table  width="100%" class="table-hover table-activeTd B-table" id="RyList" type="single" pageNum="1000" >
				 <tr>
					    <th width="15%" class="right-border bottom-border text-right">审核结果</th>
			            <td class="right-border bottom-border" >&nbsp;
				            <input type="radio" name="SH_STATUS" fieldname="SH_STATUS" value="1" checked="checked">通过
				            <input type="radio" name="SH_STATUS" fieldname="SH_STATUS" value="-1">退回
				        </td>
			    </tr>
				<tr>
				        <th width="15%" class="right-border bottom-border text-right ">审核意见</th>
				        <td  class="bottom-border right-border" >
				        	<textarea class="span12" rows="3"  check-type="maxlength" maxlength="4000" fieldname="SH_YJ1" ></textarea>
				        </td>
			        </tr>	
				</table>
			</div>
      </form>
    </div>
   </div>
  </div>
  <form method="post" id="queryForm"  >
    	  <input type="hidden" id="SCORE_UID" fieldname="t.SCORE_UID" name = "SCORE_UID" operation="="/>
  </form>
  
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
<script type="text/javascript" charset="utf-8">
function loadWgsj(str){
	var tzdUid = $('#ZG_TZD_UID').val();
	$('#ZG_WEIGUI_SJ_UID').val(str);
	var gongchenguid = $('#GONGCHENG_UID').val();
	var data = "{'tzdUid':'"+tzdUid+"','wgsjUidstr':'"+str+"','gongcheng_uid':'"+gongchenguid+"'}";
	//var data = Form2Json.formToJSON(yxGcTypeForm);
	var data1 = defaultJson.packSaveJson(data);
	$.ajax({
		url : controllernameContent + "?insert",
		data:data1,
		dataType : "json",
		type : 'post',
		success : function(response) {
		init();		
		}
	});
}
</script>
</html>