<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>隐患整改</title>
<%
	String gcuid = request.getParameter("gcuid");
	String username = request.getParameter("username");
%>

<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername = "${pageContext.request.contextPath }/yhzg/zgTzdController";
var controllernameXmjd = "${pageContext.request.contextPath }/yhzg/zgXmjdController";
var controllernameWgsj= "${pageContext.request.contextPath }/yhzg/zgWeiguiSjController";
var controllernameContent= "${pageContext.request.contextPath }/yhzg/zgContentController";
//页面初始化
$(function() {
	init();
	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
		if($("#zgtzdForm").validationButton()){
			var sjuid = $('#SJUID').val();
			if(sjuid==undefined){
				xAlert("信息提示","请添加违规事件！");
			}else{
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
		    			$(window).manhuaDialog.close();
		    		}else{
		    			defaultJson.doInsertJson(controllername + "?insert", data1);
		    			var rowValue = $("#resultXML").val();
		    			var res = dealSpecialCharactor(rowValue);
		    			var resultobj = defaultJson.dealResultJson(res);
		    			$('#QZG_TZD_UID').val(resultobj.ZG_TZD_UID);
		    			//给表单赋值
		    			//resultobj = defaultJson.dealResultJson(rowValue);
		    			
		    			//$("#zgtzdForm").setFormValues(resultobj);
		    		   // $('#shjltxdivid').show();
		    		    //$('#zgtpdivid').show();
		    		    setFormValues();
		    		    var a = $(window).manhuaDialog.getParentObj();
		    		    a.init();
		    		}
				});	
			}


		    //var a = $(window).manhuaDialog.getParentObj();
		    //a.setFormValues();
			//$(window).manhuaDialog.close();
		}else{
			requireFormMsg();
		  	return;
		}
	});

	$("#btnClose").bind("click", function(){
		$(window).manhuaDialog.getParentObj().init();
		var a=$(this).manhuaDialog.close();
		
	});	

});
//页面默认参数
function init(){
	//生成json串
	//var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT3);
	//调用ajax插入
	//defaultJson.doQueryJsonList(controllernameContent+"?query",data,DT3);
	//setFormValues();
	$('#FAFANG_DATE').val(getNowDate());

	var tzdUid = $('#ZG_TZD_UID').val();
	if($("#ZG_TZD_UID").val() != "" && $("#ZG_TZD_UID").val() != null){
		$('#shjltxdivid').show();
	}
	
}

//查询表单值
function setFormValues(){
	var data = combineQuery.getQueryCombineData(queryForm1,frmPost);
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
			var resultobj = defaultJson.dealResultJson(response.msg);
			queryFileData(resultobj.ZG_TZD_UID, "", "", "900009");
			$("#zgtzdForm").setFormValues(resultobj);
		}
	});
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

//---表单操作begin-----
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

function radioFun(demo){
	if(demo=='2'){
		$('#jbtgwz').show();
	}else{
		$('#jbtgwz').hide();
		$('#JBTGWZ').val("");
	}
}

//----表单操作end-----

//----添加违规事件begin--------
function addEvent(){
	window.open("wgsj-select.jsp");
}

//回调
function loadWgsj(str){
	//alert(str);
	$('#QSJ_UID').val("("+str+")");

	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT3);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllernameWgsj+"?queryZgsj",data,DT3);
}

function formatEdit1(obj){
	return "<a href='javascript:void(0)' onclick=\"doEdit1('"+obj.SJUID+"')\"  title='查看'><i class='icon-file showXmxxkInfo'></i></a>";
}

function formatWgdj(obj){
	var html = "<input style=\"width:95%;\"  type=\"text\" fieldname = \"WG_DENGJI\" disabled=\"disabled\" value=\""+obj.WG_DENGJI+"\">";
		html +="<input style=\"width:95%;\" id=\"SJUID\"  type=\"hidden\" fieldname = \"SJUID\"  value=\""+obj.SJUID+"\">";

	return html ;
}

function formatWglx(obj){

	return "<input style=\"width:95%;\" type=\"text\"  fieldname = \"NAME\" disabled=\"disabled\" value=\""+obj.NAME+"\">";
}


function formatWgms(obj){
	return "<textarea style=\"width:95%;\" rows=\"3\"  fieldname = \"WG_MIAOSHU\" disabled=\"disabled\">"+obj.WG_MIAOSHU+"</textarea>";
}


function formatXxms(obj){
	return "<textarea style=\"width:95%;\" rows=\"3\"  fieldname = \"DESCRIPTION\" >"+obj.DESCRIPTION+"</textarea>";
}

function doEdit1(sjuid){
	$(window).manhuaDialog({"title":"隐患整改>整改事件查看","type":"text","content":"${pageContext.request.contextPath }/jsp/gdzxt/yhzg/yhzg-zgsj-edit.jsp?sjuid="+sjuid,"modal":"2"});
}

function formatFileUpload(){
	var html = "<div><span class=\"btn btn-fileUpload\" id=\"addFileSpan\" onclick=\"doSelectFile(this,'child');\" ywid=\"ZG_TZD_UID\" target_type=\"2\" file_type=\"11\" business_type=\"ZG_TZD\">";
		html += "  <i class=\"icon-plus\"></i>";
		html += " <span>添加文件</span></span>";
		html += " <table role=\"presentation\" class=\"table table-striped\">";
		html += " <tbody ywid=\"ZG_TZD_UID\" class=\"files showFileTab\" data-toggle=\"modal-gallery\" data-target=\"#modal-gallery\"></tbody>";
		html += " </table></div>" ;
	return html;
}



//----添加违规事件end--------

function selectFile(obj){
	  var targetUid = $('#target_uid').val();
	  var file_type = $(obj).attr('file_type');
	setFileData("ZG_TZD","ZG_TZD_UID",targetUid,file_type);
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
	  		<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">保存</button>
      	</span>
      </h4>
     <form method="post" id="zgtzdForm"  >
      <table class="B-table" width="100%" >
     	 <input type="hidden" id="ZG_TZD_UID" fieldname="ZG_TZD_UID" name = "ZG_TZD_UID" />
     	 <input type="hidden" id="target_uid" fieldname="target_uid"  />
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
         		<input  id="XIAFA_REN"   type="text" fieldname="XIAFA_REN" name = "XIAFA_REN"   value="<%=username %>"/>
         	</td>
         </tr>
        <tr>
			<th width="8%" class="right-border bottom-border text-right">发放时间</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="FAFANG_DATE"   type="text" fieldname="FAFANG_DATE" name = "FAFANG_DATE"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy/MM/dd'})" fieldformat="YYYY/MM/DD" fieldtype="date"/>
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">要求整改完成时间</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="ZG_DATE" check-type="required"  type="text" fieldname="ZG_DATE" name = "ZG_DATE"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy/MM/dd'})" fieldformat="YYYY/MM/DD" fieldtype="date"/>
         	</td>
         </tr>
        <tr>
			<th width="8%" class="right-border bottom-border text-right">整改性质</th>
       		<td class="bottom-border right-border" width="15%" colspan="3">
         		<input type="radio" fieldname="ZG_XINGZHI_UID" name = "ZG_XINGZHI_UID" checked="checked" value="1" onclick="radioFun(this.value);"/> 限期整改
         		<input type="radio" fieldname="ZG_XINGZHI_UID" name = "ZG_XINGZHI_UID" value="2" onclick="radioFun(this.value);"/>局部停工整改
         		<input type="radio" fieldname="ZG_XINGZHI_UID" name = "ZG_XINGZHI_UID" value="3" onclick="radioFun(this.value);"/> 全面停工整改 
         		
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
      		<div id="shjltxdivid"  >
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
							<th fieldname="SJUID" colindex=1 tdalign="center" CustomFunction="formatEdit1">&nbsp;&nbsp;</th>
		                	<th fieldname="WG_DENGJI" colindex=1 tdalign="center" CustomFunction="formatWgdj">&nbsp;违规等级&nbsp;</th>
							<th fieldname="NAME" colindex=1 tdalign="center" maxlength="30" CustomFunction="formatWglx">&nbsp;违规类型 &nbsp;</th>
							<th fieldname="WG_MIAOSHU" colindex=2 tdalign="center" maxlength="4000" CustomFunction="formatWgms">&nbsp;违规描述 &nbsp;</th>
							<th fieldname="DESCRIPTION" colindex=3 tdalign="center" maxlength="4000" CustomFunction="formatXxms">&nbsp;详细描述&nbsp;</th>
							<!--  <th fieldname="ZG_CONTENT_UID" colindex=6 tdalign="center"  CustomFunction="formatFileUpload" >&nbsp;照片&nbsp;</th>	 -->					
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
			<div style="height: 5px;"></div>
      		<div id="zgtpdivid" >
				<div class="modal-header" style="background:#0866c6;">
				<h3 style="color:white;">整改图片
				<span class="btn btn-fileUpload" id="addFileSpan" onclick="selectFile(this);" file_type="3010" >
					<i class="icon-plus"></i>
					<span>上传...</span>
				</span>

				</h3>
				</div>
					<table role="presentation" class="table table-striped">
					<tbody class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"  file_type="3010"></tbody>
					</table>

			</div>
      </form>
    </div>
   </div>
  </div>
  <form method="post" id="queryForm"  >
    	  <input type="hidden" id="QSJ_UID" fieldname="ws.zg_weigui_sj_uid"  operation="in"/>
  </form>
  <form method="post" id="queryForm1"  >
    	  <input type="hidden" id="QZG_TZD_UID" fieldname="t.ZG_TZD_UID"  operation="="/>
  </form>
<%@ include file="/jsp/file_upload/fileupload_util.jsp"%>
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