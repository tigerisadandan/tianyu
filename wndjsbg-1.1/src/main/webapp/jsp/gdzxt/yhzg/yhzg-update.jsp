<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>隐患整改单修改</title>
<%
	String gcuid = request.getParameter("gcuid");
	String username = request.getParameter("username");
	String zgdid = request.getParameter("zgdid");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername = "${pageContext.request.contextPath }/yhzg/zgTzdController";
var controllernameXmjd = "${pageContext.request.contextPath }/yhzg/zgXmjdController";
var controllernameWgsj= "${pageContext.request.contextPath }/yhzg/zgWeiguiSjController";
var controllernameContent= "${pageContext.request.contextPath }/yhzg/zgContentController";
var controllernameBgMenu="${pageContext.request.contextPath }/bgMenuController/";
var zgdid = <%=zgdid%>;

//页面初始化
$(function() {
	init();
	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
		if($("#zgtzdForm").validationButton()){
			xConfirm("信息确认","确认保存吗？");
			$('#ConfirmYesButton').attr('click','');
			$('#ConfirmYesButton').one("click",function(){
			    //生成json串
			    var data = Form2Json.formToJSON(zgtzdForm);
			  //组成保存json串格式
			    var data1 = defaultJson.packSaveJson(data);
			  //调用ajax插入
			    if($("#ZG_TZD_UID").val() != "" && $("#ZG_TZD_UID").val() != null){
	    			defaultJson.doUpdateJson(controllername + "?update", data1);
	    		}else{
	    			defaultJson.doInsertJson(controllername + "?insert", data1);

	    		    $('#shjltxdivid').show();
	    		}
			});	

		    //var a = $(window).manhuaDialog.getParentObj();
		    //a.setFormValues();
			//$(window).manhuaDialog.close();
		}else{
			requireFormMsg();
		  	return;
		}
	});

	$('#btnUpdate').click(function(){

		xConfirm("信息确认","确认修改吗？");
		$('#ConfirmYesButton').attr('click','');
		$('#ConfirmYesButton').one("click",function(){
			$("#btnSave").show();
			
			//验证是否有权显示删除按钮
			$.ajax({
				url : controllernameBgMenu+"?getByMenuCodeAndUserId",
				data : {code : "4050201"},
				type :"post",
				dataType : "json",
				success : function(response){
					if(response.msg == "1"){
						$('#btnDelete').show();
					}else{
						$('#btnDelete').hide();
					}
				}
			});
			
			
			$('#btnUpdate').hide();
			
		});	

	});

	
	$('#btnDelete').click(function(){
		xConfirm("信息确认","确认删除吗？");
		$('#ConfirmYesButton').attr('click','');
		$('#ConfirmYesButton').one("click",function(){
		    //生成json串
		    var data = Form2Json.formToJSON(zgtzdForm);
		  //组成保存json串格式
		    var data1 = defaultJson.packSaveJson(data);

		    defaultJson.doInsertJson(controllername + "?delete", data1);
		    var a = $(window).manhuaDialog.getParentObj();
		    a.init();
			$(window).manhuaDialog.close();
		    
		});	

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
	//defaultJson.doQueryJsonList(controllername+"?query",data,DT3);
	setFormValues();
	$('#FAFANG_DATE').val(getNowDate());
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT3);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllernameContent+"?query",data,DT3);
}

//查询表单值
function setFormValues(){
	//var parentmain = $(window).manhuaDialog.getParentObj();
	//var rowValue = parentmain.$("#resultXML").val();
	//var tempJson = convertJson.string2json1(rowValue);
	$("#QZG_TZD_UID").val(zgdid);
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

function formatEdit1(obj){
	return "<a href='javascript:void(0)' onclick=\"doEdit1('"+obj.ZG_CONTENT_UID+"')\"  title='查看'><i class='icon-file showXmxxkInfo'></i></a>";
}

function doEdit1(sjuid){
	$(window).manhuaDialog({"title":"隐患整改>整改事件查看","type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/yhzg/yhzg-zgsj.jsp?sjuid="+sjuid,"modal":"2"});
}

function formatFileUpload(obj){
	var zgdUid = $('#QZG_TZD_UID').val();
	var zgcontUid = obj.ZG_CONTENT_UID;
	var html = "<div><span class=\"btn btn-fileUpload\" id=\"addFileSpan\" onclick='selectgcalxx("+zgdUid+","+zgcontUid+");'  ywid='"+zgdUid+"' fjlb='"+zgcontUid+"' target_type=\"2\" file_type=\"11\" business_type=\"ZG_TZD\">";
		html += "  <i class=\"icon-plus\"></i>";
		html += " <span>添加文件</span></span>";
		html += " <table role=\"presentation\" class=\"table table-striped\">";
		html += " <tbody ywid='"+zgdUid+"' fjlb='"+zgcontUid+"' class=\"files showFileTab\" data-toggle=\"modal-gallery\" data-target=\"#modal-gallery\"></tbody>";
		html += " </table></div>" ;
	return html;
}


function selectgcalxx(zgdUid,zgcontUid){
	
	setFileData(zgdUid,"","","900009","0",zgcontUid);
	$("#_fileupload_fjlb").val(zgcontUid);
	document.getElementById("fileupload_btn").click();
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
		//显示 添加属性  隐藏 移除属性
		$('#XXJDT').show();
		$('#XXJDT').attr('fieldname','XXJD2');
		$('#XXJD2').hide();
		$('#XXJD2').removeAttr('fieldname');
	}else{
		$('#XXJDT').hide();
		$('#XXJDT').val("");
		$('#XXJDT').removeAttr('fieldname');
		$('#XXJD2').show();
		$('#XXJD2').attr('fieldname','XXJD2');
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
	window.open("wgsj-select.jsp");
}

function radioFun(demo){
	if(demo=='2'){
		$('#jbtgwz').show();
	}else{
		$('#jbtgwz').hide();
		$('#JBTGWZ').val("");
	}
}



function selectXmtzFile(obj){
	var ywid = $('#ZG_TZD_UID').val();
	setFileData(ywid,"","","900009","1","7763");
	$("#_fileupload_fjlb").val($(obj).attr("fjlb"));
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
      <h4 class="title">
      	<span class="pull-right">
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
	  		<button id="btnUpdate" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">修改</button>
	  		<button id="btnDelete" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold; display: none;">删除</button>
	  		<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold; display: none;">保存</button>
      	</span>
      </h4>
     <form method="post" id="zgtzdForm"  >
      <table class="B-table" width="100%" >
     	 <input type="hidden" id="ZG_TZD_UID" fieldname="ZG_TZD_UID" name = "ZG_TZD_UID"/>
     	 <input type="hidden" id="GONGCHENG_UID" fieldname="GONGCHENG_UID" name = "GONGCHENG_UID" value="<%=gcuid %>"/>
        
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
         		<select id="XXJD"  name="XXJD" fieldname="XXJD" kind="dic" src="T#zg_xmjd: NAME as c:TITLE as t:PARENT IS NULL ORDER BY ID" onchange="selectFun(this.value);"></select>
         		<select  id="XXJD2"  name="XXJD2" fieldname="XXJD2" onchange="selectFun2(this.value);"><option value="">请选择</option></select>
         		<select  id="XXJD3"  name="XXJD3" fieldname="XXJD3" style="display: none;"><option value="">请选择</option></select>
         		<input id="XXJDT" type="text" fieldname="XXJD2" style="display: none;">
         	</td>
         </tr>
         
         <tr>
			<th width="8%" class="right-border bottom-border text-right">抽查内容</th>
       		<td class="bottom-border right-border"colspan="3">
         		<textarea rows="" cols="" style="width: 80%" fieldname="CHOUCHA"></textarea>
         	</td>

         </tr>
         
        <tr>
			<th width="8%" class="right-border bottom-border text-right">发放单位</th>
       		<td class="bottom-border right-border" width="15%">
         		<input type="checkbox" fieldname="FAFANG_DANWEI" name = "FAFANG_DANWEI"  checked="checked" value="AJ"/>安监站
         		<input type="checkbox" fieldname="FAFANG_DANWEI" name = "FAFANG_DANWEI" value="ZJ"/>质监站
         		<input type="checkbox" fieldname="FAFANG_DANWEI" name = "FAFANG_DANWEI" value="JG"/>建管办
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">下发人</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="XIAFA_REN"   type="text" fieldname="XIAFA_REN" name = "XIAFA_REN" />
         	</td>
         </tr>
        <tr>
			<th width="8%" class="right-border bottom-border text-right">发放时间</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="FAFANG_DATE"   type="text" fieldname="FAFANG_DATE" name = "FAFANG_DATE"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy/MM/dd'})"/>
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">要求整改完成时间</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="ZG_DATE" check-type="required"  type="text" fieldname="ZG_DATE" name = "ZG_DATE"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy/MM/dd'})" />
         	</td>
         </tr>
        <tr>
			<th width="8%" class="right-border bottom-border text-right">整改性质</th>
       		<td class="bottom-border right-border" width="15%" colspan="3">
         		<input  type="radio" fieldname="ZG_XINGZHI_UID" name = "ZG_XINGZHI_UID" checked="checked" value="1" onclick="radioFun(this.value);"/> 限期整改
         		<input  type="radio" fieldname="ZG_XINGZHI_UID" name = "ZG_XINGZHI_UID" value="2" onclick="radioFun(this.value);"/>局部停工整改
         		<input  type="radio" fieldname="ZG_XINGZHI_UID" name = "ZG_XINGZHI_UID" value="3" onclick="radioFun(this.value);"/> 全面停工整改 
         		
         	</td>
         </tr>
        <tr id="jbtgwz" style="display: none;">
			<th width="8%" class="right-border bottom-border text-right">局部停工位置</th>
       		<td class="bottom-border right-border" width="15%" colspan="3">
         		<input  id="JBTGWZ"   type="text" fieldname="JBTGWZ" name = "JBTGWZ" />
         	</td>
         </tr>
      </table>
       <div style="height: 5px;"></div>
      		<div id="shjltxdivid">
				<div class="modal-header" style="background:#0866c6;">
				<h3 style="color:white;">整改事件 
				<span class="pull-right">
					<button type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;" onclick="addEvent();">添加</button>
	  			</span>
				</h3>
				</div>
      			<table class="table-hover table-activeTd B-table" width="100%" id="DT3" type="single"  noPage=true >
					<thead>
						<tr>
							<th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
							<th fieldname="ZG_CONTENT_UID" colindex=1 tdalign="center" CustomFunction="formatEdit1">&nbsp;&nbsp;</th>
		                	<th fieldname="WG_DENGJI" colindex=1 tdalign="center">&nbsp;违规等级&nbsp;</th>
							<th fieldname="NAME" colindex=1 tdalign="center" maxlength="30" >&nbsp;违规类型 &nbsp;</th>
							<th fieldname="WG_MIAOSHU" colindex=2 tdalign="center" maxlength="30" >&nbsp;违规描述 &nbsp;</th>
							<th fieldname="DESCRIPTION" colindex=3 tdalign="center" maxlength="30" >&nbsp;详细描述&nbsp;</th>
							<!--  <th fieldname="ZG_CONTENT_UID" colindex=6 tdalign="center"  CustomFunction="formatFileUpload" >&nbsp;照片&nbsp;</th>	 -->						
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
					<span class="btn btn-info  btn-sm" id="addFileSpan3" onclick="selectXmtzFile(this);" fjlb="7763"> 
						<i class="icon-plus"></i> <span>上传图片</span> 
					</span>
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
    	  <input type="hidden" id="QZG_TZD_UID" fieldname="t.zg_tzd_uid" name = "ZG_TZD_UID" operation="="/>
  </form>
<%@ include file="/jsp/file_upload/fileupload_config_ajax_gd.jsp"%>
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
	var rowValue = $("#resultXML").val();
	var res = dealSpecialCharactor(rowValue);
	var resultobj = defaultJson.dealResultJson(res);
	var tzdUid = resultobj.ZG_TZD_UID;
	alert(tzdUid);
	//$('#ZG_WEIGUI_SJ_UID').val(str);
	var gongchenguid = $('#GONGCHENG_UID').val();
	var data = "{'tzdUid':'"+tzdUid+"','wgsjUidstr':'"+str+"','gongcheng_uid':'"+gongchenguid+"'}";
	//var data = Form2Json.formToJSON(zgtzdForm);
	var data1 = defaultJson.packSaveJson(data);
	$.ajax({
		url : controllernameContent + "?insert",
		data:data1,
		dataType : "json",
		type : 'post',
		success : function(response) {
			var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT3);
			//调用ajax插入
			defaultJson.doQueryJsonList(controllernameContent+"?query",data,DT3);	
		}
	});
}
</script>
</html>