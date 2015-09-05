<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>隐患整改局部停工审核</title>
<%
	String type=request.getParameter("type");
	String gcuid = request.getParameter("gcuid");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername = "${pageContext.request.contextPath }/yhzg/zgTzdController";
var controllernameXmjd = "${pageContext.request.contextPath }/yhzg/zgXmjdController";
var controllernameWgsj= "${pageContext.request.contextPath }/yhzg/zgWeiguiSjController";
var controllernameContent= "${pageContext.request.contextPath }/yhzg/zgContentController";
var type ="<%=type%>";
//页面初始化
$(function() {
	init();
	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
		if($("#yxGcTypeForm").validationButton()){
		    //生成json串
		    var data = Form2Json.formToJSON(yxGcTypeForm);
		  //组成保存json串格式
		    var data1 = defaultJson.packSaveJson(data);
		  //调用ajax插入
		    if($("#GC_TYPE_UID").val() != "" && $("#GC_TYPE_UID").val() != null){
    			defaultJson.doUpdateJson(controllername + "?update", data1);
    		}else{
    			defaultJson.doInsertJson(controllername + "?insert", data1);
    		}
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

});
//页面默认参数
function init(){
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT3);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllernameContent+"?query",data,DT3);
	$('#FAFANG_DATE').val(getNowDate());
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
   
	<div style="height:5px;"></div>
	<table id="YJ_LIST"  border="0" width="100%" cellpadding="0" cellspacing="0" class="content">
			<div class="modal-header" style="background:#0866c6;">
					<h3 style="color:white;"> 局部停工整改审核  
				<span class="pull-right">
					<button id="btnTh" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">退回</button>
					<button id="btnTg" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">通过审核</button>
	  			</span>
	  			</h3>
			</div>
			<tr>
				<td>
					<div class="overFlowX">
				 		<table width="100%" class="B-table" >
					    <thead>
					 		<tr class="c2" style="display: none;">
					 			<th>审核人1</th>
					 			<td>
					 				<input type="text" id="SHENHE_USER2" name="SHENHE_USER2" fieldname="SHENHE_USER2" disabled="disabled"/>
					 			</td>
					        </tr>
					 		<tr class="c2" style="display: none;">
					 			<th>审核意见</th>
					 			<td colspan="3">
					 				<textarea class="span12" rows="2" fieldname="SHENHE_YIJIAN2"></textarea>
					 			</td>
					        </tr>
					 		<tr class="c3" style="display: none;">
					 			<th>审核人2</th>
					 			<td>
					 				<input type="text" id="FINISH_USER" name="FINISH_USER" fieldname="FINISH_USER" disabled="disabled"/>
					 			</td>
					        </tr>
					 		<tr class="c3" style="display: none;">
					 			<th>审核意见</th>
					 			<td colspan="3">
					 				<textarea class="span12" rows="2" fieldname="FINISH_YIJIAN"></textarea>
					 			</td>
					        </tr>

					    </thead>
					  </table>  
		       		</div>
				</td>
			</tr>
		</table>
	<div style="height:5px;"></div>
	<div class="B-small-from-table-autoConcise">
     <form method="post" id="yxGcTypeForm"  >
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
       			<input type="text" readonly="readonly" id="KOU_JS" name="KOU_JS" fieldname="KOU_JS">
         	</td>
         </tr>

        <tr>
			<th width="8%" class="right-border bottom-border text-right">施工单位扣分</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="KOU_SG"   type="text" fieldname="KOU_SG" name = "KOU_SG" readonly="readonly"/>
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">监理单位扣分</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="KOU_JL"   type="text" fieldname="KOU_JL" name = "KOU_JL"  readonly="readonly"/>
         	</td>
         </tr>
        <tr>
			<th width="8%" class="right-border bottom-border text-right">项目经理扣分</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="KOU_FEN"   type="text" fieldname="KOU_FEN" name = "KOU_FEN" readonly="readonly"/>
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">总监扣分</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="KOU_FEN2"   type="text" fieldname="KOU_FEN2" name = "KOU_FEN2"  readonly="readonly"/>
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
         		<select  id="XXJD2"  name="XXJD2" fieldname="XXJD2" onchange="selectFun2(this.value);"><option>请选择</option></select>
         		<select  id="XXJD3"  name="XXJD3" fieldname="XXJD3" style="display: none;"><option>请选择</option></select>
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
         		<input  id="FAFANG_DANWEI"   type="checkbox" fieldname="FAFANG_DANWEI" name = "FAFANG_DANWEI"  checked="checked"/>安监站
         		<input  id="FAFANG_DANWEI"   type="checkbox" fieldname="FAFANG_DANWEI" name = "FAFANG_DANWEI" />质监站
         		<input  id="FAFANG_DANWEI"   type="checkbox" fieldname="FAFANG_DANWEI" name = "FAFANG_DANWEI" />建管办
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">下发人</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="RETURN_LINK"   type="text" fieldname="RETURN_LINK" name = "RETURN_LINK"  />
         	</td>
         </tr>
        <tr>
			<th width="8%" class="right-border bottom-border text-right">发放时间</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="FAFANG_DATE"   type="text" fieldname="FAFANG_DATE" name = "FAFANG_DATE"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy/MM/dd'})"/>
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">要求整改完成时间</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="ZG_DATE"   type="text" fieldname="ZG_DATE" name = "ZG_DATE"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy/MM/dd'})" />
         	</td>
         </tr>
        <tr>
			<th width="8%" class="right-border bottom-border text-right">整改性质</th>
       		<td class="bottom-border right-border" width="15%" colspan="3">
         		<input  id="ZG_XINGZHI_UID"   type="radio" fieldname="ZG_XINGZHI_UID" name = "ZG_XINGZHI_UID" checked="checked" value="1" onclick="radioFun(this.value);"/> 限期整改
         		<input  id="ZG_XINGZHI_UID"   type="radio" fieldname="ZG_XINGZHI_UID" name = "ZG_XINGZHI_UID" value="2" onclick="radioFun(this.value);"/>局部停工整改
         		<input  id="ZG_XINGZHI_UID"   type="radio" fieldname="ZG_XINGZHI_UID" name = "ZG_XINGZHI_UID" value="3" onclick="radioFun(this.value);"/> 全面停工整改 
         		
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
      		<div id="shjltxdivid"  style="display: none;">
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
		                	<th fieldname="WG_DENGJI" colindex=1 tdalign="center">&nbsp;违规等级&nbsp;</th>
							<th fieldname="NAME" colindex=1 tdalign="center" maxlength="30" >&nbsp;违规类型 &nbsp;</th>
							<th fieldname="WEIGUI_CONTENT" colindex=2 tdalign="center" maxlength="30" >&nbsp;违规描述 &nbsp;</th>
							<th fieldname="DESCRIPTION" colindex=3 tdalign="center" maxlength="30" >&nbsp;详细描述&nbsp;</th>
							<th fieldname="JLDW_SCORE" colindex=6 tdalign="center"  >&nbsp;照片&nbsp;</th>						
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
      </form>
    </div>
   </div>
  </div>
  <form method="post" id="queryForm"  >
    	  <input type="hidden" id="ZG_TZD_UID" fieldname="t.zg_tzd_uid" name = "ZG_TZD_UID" operation="="/>
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