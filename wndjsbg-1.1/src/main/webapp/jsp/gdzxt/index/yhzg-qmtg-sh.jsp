<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>隐患整改全面停工审核</title>
<%
	
	String tzdUid = request.getParameter("tzdUid");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername = "${pageContext.request.contextPath }/yhzg/zgTzdController";
var controllernameXmjd = "${pageContext.request.contextPath }/yhzg/zgXmjdController";
var controllernameWgsj= "${pageContext.request.contextPath }/yhzg/zgWeiguiSjController";
var controllernameContent= "${pageContext.request.contextPath }/yhzg/zgContentController";

//页面初始化
$(function() {
	init();

	$("#btnTh").click(function(){
		xConfirm("信息确认","确认退回？");
		$('#ConfirmYesButton').attr('click','');
		$('#ConfirmYesButton').one("click",function(){
		    //生成json串
		    var data = Form2Json.formToJSON(zgtzdForm);
		  //组成保存json串格式
		   var data1 = defaultJson.packSaveJson(data);
		  //调用ajax插入
    	   defaultJson.doInsertJson(controllername + "?updateSh", data1);
		   $(window).manhuaDialog.close();
		});	

	});
	$("#btnTg").click(function(){
		xConfirm("信息确认","确认审核通过？");
		$('#ConfirmYesButton').attr('click','');
		$('#ConfirmYesButton').one("click",function(){
		    //生成json串
		    var data = Form2Json.formToJSON(zgtzdForm);
		  //组成保存json串格式
		   var data1 = defaultJson.packSaveJson(data);
		  //调用ajax插入
    	   defaultJson.doInsertJson(controllername + "?updateSh", data1);
		   $(window).manhuaDialog.close();
		});	
	});

	$("#btnClose").bind("click", function(){
		var a=$(this).manhuaDialog.close();
	});	

});
//页面默认参数
function init(){
	setFormValues();
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT3);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllernameContent+"?query",data,DT3);
	$('#FAFANG_DATE').val(getNowDate());
}

function setFormValues(){
	//var parentmain = $(window).manhuaDialog.getParentObj();
	//var rowValue = parentmain.$("#resultXML").val();
	//var tempJson = convertJson.string2json1(rowValue);
	
	var data = combineQuery.getQueryCombineData(queryForm,frmPost);
	var data1 = {
		msg : data
	};
	$.ajax({
		url : controllername+"?queryForm",
		data : data1,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			$("#resultXML").val(response.msg);
			resultobj = defaultJson.dealResultJson(response.msg);
			$("#zgtzdForm").setFormValues(resultobj);
			queryFileData(resultobj.ZG_TZD_UID, "", "", "900009");
			setXXJD(resultobj);
		}
	});
}


function setXXJD(resultobj){
	var xxjd = resultobj.XXJD;
	var xxjd2 = resultobj.XXJD2;
	var xxjd3 =  resultobj.XXJD3;

	$("#XXJD option").each(function(){ //遍历全部option
	       var txt = $(this).val(); //获取option的内容
	       if(txt==xxjd){
	    	   $(this).attr("selected",true);
	    	   selectFun(xxjd);
			}
	 });

	 if(xxjd2!=""){
		 $("#XXJD2 option").each(function(){ //遍历全部option
		       var txt = $(this).val(); //获取option的内容
		       if(txt==xxjd2){
		    	   $(this).attr("selected",true);
		    	   selectFun2(xxjd2);
				}
		 });
	}

	 if(xxjd3!=""){
		 $("#XXJD3 option").each(function(){ //遍历全部option
		       var txt = $(this).val(); //获取option的内容
		       if(txt==xxjd3){
		    	   $(this).attr("selected",true);
				}
		 });
	}
}

function getNowDate(){
	var nowdate = new Date();
	var nowy = nowdate.getFullYear();
	var nowm = nowdate.getMonth()+1;
	var nowd = nowdate.getDate();
	if(nowm<10){
		nowm = "0"+nowm;
	}
	var datestr = nowy+"/"+nowm+"/"+nowd;
	return datestr;
}

function selectFun(demo){
	
	if(demo=='zt'){
		$('#XXJDT').show();
		$('#XXJD2').hide();
	}else{
		$('#XXJDT').hide();
		$('#XXJD2').show();
		var obj=$("#XXJD2");
		obj.attr("src","T#zg_xmjd: NAME as c:TITLE as t:PARENT ='"+demo+"'ORDER BY ID ");
		obj.attr("kind","dic");
		obj.html('');
		reloadSelectTableDic(obj);	
	}

}

function selectFun2(demo){
	if(demo!='wqzs'){
		$('#XXJD3').hide();
	}else{
		$('#XXJD3').show();
		var obj=$("#XXJD3");
		obj.attr("src","T#zg_xmjd: NAME as c:TITLE as t:PARENT ='"+demo+"'ORDER BY ID ");
		obj.attr("kind","dic");
		obj.html('');
		reloadSelectTableDic(obj);				
	}

}

function addEvent(){
	var tzdUid = $('#ZG_TZD_UID').val();
	alert("tzdUid1=="+tzdUid);
	if(tzdUid==""){
		$.ajax({
			url : controllername + "?queryUid",
			dataType : "json",
			type : 'post',
			success : function(response) {
				$('#ZG_TZD_UID').val(response.msg);				
			}
		});
	}

	window.open("wgsj-select.jsp");
}

function radioFun(demo){
	if(demo=='2'){
		$('#jbtgwz').show();
	}else{
		$('#jbtgwz').hide();
	}
}


</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">
   <form method="post" id="zgtzdForm"  > 
	<div style="height:5px;"></div>
	<div class="B-small-from-table-autoConcise">

			<div class="modal-header" style="background:#0866c6;">
					<h3 style="color:white;"> 全面停工整改审核  
				<span class="pull-right">
					<button id="btnTh" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">退回</button>
					<button id="btnTg" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">通过审核</button>
	  			</span>
	  			</h3>
			</div>

				 <table width="100%" class="B-table" >
					 		<tr class="c2" >
					 			<th width="16%"  class="right-border bottom-border text-right">审核人1</th>
					 			<td colspan="3" class="bottom-border right-border">
					 				<input type="text" id="SHENHE_USER2" name="SHENHE_USER2" fieldname="SHENHE_USER2" disabled="disabled"/>
					 			</td>
					        </tr>
					 		<tr class="c2" >
					 			<th width="16%"  class="right-border bottom-border text-right">审核意见</th>
					 			<td colspan="3" class="bottom-border right-border">
					 				<textarea class="span12" rows="2" fieldname="SHENHE_YIJIAN2" style="width: 80%"></textarea>
					 			</td>
					        </tr>
					 		<tr class="c3" >
					 			<th width="16%"  class="right-border bottom-border text-right">审核人2</th>
					 			<td colspan="3" class="bottom-border right-border">
					 				<input type="text" id="FINISH_USER" name="FINISH_USER" fieldname="FINISH_USER" disabled="disabled"/>
					 			</td>
					        </tr>
					 		<tr class="c3">
					 			<th width="16%"  class="right-border bottom-border text-right">审核意见</th>
					 			<td colspan="3"  class="bottom-border right-border">
					 				<textarea class="span12" rows="2" fieldname="FINISH_YIJIAN" style="width: 80%"></textarea>
					 			</td>
					        </tr>
			</table>  
	
		</div>	
	<div style="height:5px;"></div>
	<div class="B-small-from-table-autoConcise">
    	<div class="modal-header" style="background:#0866c6;">
			<h3 style="color:white;">整改信息
		
			</h3>
		</div>
      <table class="B-table" width="100%" >
     	 <input type="hidden" id="ZG_TZD_UID" fieldname="ZG_TZD_UID" name = "ZG_TZD_UID"/>
     	 <input type="hidden" id="SH_LEVEL" fieldname="SH_LEVEL" name = "SH_LEVEL"/>
         <tr>
			<th width="8%" class="right-border bottom-border text-right">整改单编号</th>
       		<td class="bottom-border right-border" width="15%">
         		<input readonly="readonly"  id="ZG_CODE" type="text" fieldname="ZG_CODE" name = "ZG_CODE"  />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">建设单位扣分</th>
       		<td class="bottom-border right-border" width="15%">
       			<input type="text" readonly="readonly" id="JSDW_KOUFEN" name="JSDW_KOUFEN" fieldname="JSDW_KOUFEN">
         	</td>
         </tr>

        <tr>
			<th width="8%" class="right-border bottom-border text-right">施工单位扣分</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="SGDW_KOUFEN"   type="text" fieldname="SGDW_KOUFEN" name = "SGDW_KOUFEN" readonly="readonly"/>
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">监理单位扣分</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="JLDW_KOUFEN"   type="text" fieldname="JLDW_KOUFEN" name = "JLDW_KOUFEN"  readonly="readonly"/>
         	</td>
         </tr>
        <tr>
			<th width="8%" class="right-border bottom-border text-right">项目经理扣分</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="XMJL_KOUFEN"   type="text" fieldname="XMJL_KOUFEN" name = "XMJL_KOUFEN" readonly="readonly"/>
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">总监扣分</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="JL_KOUFEN"   type="text" fieldname="JL_KOUFEN" name = "JL_KOUFEN"  readonly="readonly"/>
         	</td>
         </tr>
        <tr>
			<th width="8%" class="right-border bottom-border text-right">责任单位（人）</th>
       		<td class="bottom-border right-border" colspan="3" >
         		<input  id="ZR_NAME"   type="text" fieldname="ZR_NAME" name = "ZR_NAME" readonly="readonly" style="width: 80%"/>
         	</td>

         </tr>
         <tr>
			<th width="8%" class="right-border bottom-border text-right">项目进度</th>
       		<td class="bottom-border right-border" width="15%" colspan="3">
         		<select id="XXJD"  name="XXJD" fieldname="XXJD" kind="dic" src="T#zg_xmjd: NAME as c:TITLE as t:PARENT IS NULL ORDER BY ID" onchange="selectFun(this.value);" disabled="disabled"></select>
         		<select  id="XXJD2"  name="XXJD2" fieldname="XXJD2" onchange="selectFun2(this.value);" disabled="disabled"><option>请选择</option></select>
         		<select  id="XXJD3"  name="XXJD3" fieldname="XXJD3" style="display: none;" disabled="disabled"><option>请选择</option></select>
         		<input id="XXJDT" type="text" fieldname="XXJD2" style="display: none;" readonly="readonly">
         	</td>
         </tr>
         
         <tr>
			<th width="8%" class="right-border bottom-border text-right">抽查内容</th>
       		<td class="bottom-border right-border"colspan="3">
         		<textarea rows="" cols="" style="width: 80%" fieldname="CHOUCHA" readonly="readonly"></textarea>
         	</td>

         </tr>
         
        <tr>
			<th width="8%" class="right-border bottom-border text-right">发放单位</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="FAFANG_DANWEI"   type="checkbox" fieldname="FAFANG_DANWEI" name = "FAFANG_DANWEI"  checked="checked" disabled="disabled"/>安监站
         		<input  id="FAFANG_DANWEI"   type="checkbox" fieldname="FAFANG_DANWEI" name = "FAFANG_DANWEI" disabled="disabled"/>质监站
         		<input  id="FAFANG_DANWEI"   type="checkbox" fieldname="FAFANG_DANWEI" name = "FAFANG_DANWEI" disabled="disabled"/>建管办
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">下发人</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="XIAFA_REN"   type="text" fieldname="XIAFA_REN" name = "XIAFA_REN" readonly="readonly"/>
         	</td>
         </tr>
        <tr>
			<th width="8%" class="right-border bottom-border text-right">发放时间</th>
       		<td class="bottom-border right-border" width="15%"> 
         		<input  id="FAFANG_DATE"   type="text" fieldname="FAFANG_DATE" name = "FAFANG_DATE"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy/MM/dd'})" readonly="readonly"/>
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">要求整改完成时间</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="ZG_DATE"   type="text" fieldname="ZG_DATE" name = "ZG_DATE"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy/MM/dd'})" readonly="readonly" />
         	</td>
         </tr>
        <tr>
			<th width="8%" class="right-border bottom-border text-right">整改性质</th>
       		<td class="bottom-border right-border" width="15%" colspan="3">
         		<input  id="ZG_XINGZHI_UID"   type="radio" fieldname="ZG_XINGZHI_UID" name = "ZG_XINGZHI_UID" checked="checked" value="1" onclick="radioFun(this.value);" disabled="disabled"/> 限期整改
         		<input  id="ZG_XINGZHI_UID"   type="radio" fieldname="ZG_XINGZHI_UID" name = "ZG_XINGZHI_UID" value="2" onclick="radioFun(this.value);" disabled="disabled"/>局部停工整改
         		<input  id="ZG_XINGZHI_UID"   type="radio" fieldname="ZG_XINGZHI_UID" name = "ZG_XINGZHI_UID" value="3" onclick="radioFun(this.value);" disabled="disabled"/> 全面停工整改 
         		
         	</td>
         </tr>
        <tr id="jbtgwz" style="display: none;">
			<th width="8%" class="right-border bottom-border text-right">局部停工位置</th>
       		<td class="bottom-border right-border" width="15%" colspan="3">
         		<input  id="JBTGWZ"   type="text" fieldname="JBTGWZ" name = "JBTGWZ" readonly="readonly" />
         	</td>
         </tr>
      </table>
       <div style="height: 5px;"></div>
      		<div id="shjltxdivid">
				<div class="modal-header" style="background:#0866c6;">
				<h3 style="color:white;">整改事件 
				<span class="pull-right">
						<!--  <button type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;" onclick="addEvent();">添加</button> -->
	  			</span>
				</h3>
				</div>
      			<table class="table-hover table-activeTd B-table" width="100%" id="DT3" type="single"  noPage=true >
					<thead>
						<tr>
							<th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
		                	<th fieldname="WG_DENGJI" colindex=1 tdalign="center">&nbsp;违规等级&nbsp;</th>
							<th fieldname="NAME" colindex=1 tdalign="center" maxlength="30" >&nbsp;违规类型 &nbsp;</th>
							<th fieldname="WG_MIAOSHU" colindex=2 tdalign="center" maxlength="30" >&nbsp;违规描述 &nbsp;</th>
							<th fieldname="DESCRIPTION" colindex=3 tdalign="center" maxlength="30" >&nbsp;详细描述&nbsp;</th>
							<!-- <th fieldname="JLDW_SCORE" colindex=6 tdalign="center"  >&nbsp;照片&nbsp;</th>	  -->					
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
			<div style="height: 5px;"></div>
      		<div id="zgtpdivid">
				<div class="modal-header" style="background:#0866c6;">
				<h3 style="color:white;">整改图片
				<span class="pull-right">
				
	  			</span>
				</h3>
				</div>
      			<table role="presentation"  class="table table-striped">
					<tbody fjlb="7763" id="gccltbody" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery">
					</tbody>
				</table>
			</div>
      </form>
    </div>
   </div>
  </div>
  <form method="post" id="queryForm"  >
    	  <input type="hidden" id="QZG_TZD_UID" fieldname="t.zg_tzd_uid" name = "ZG_TZD_UID" operation="=" value="<%=tzdUid %>"/>
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
 <%@ include file="/jsp/file_upload/fileupload_config_ajax_gd.jsp"%>
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