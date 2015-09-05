<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>人员变更审核</title>
<%
	String bguid = request.getParameter("bguid");
	String gcuid = request.getParameter("gcuid");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllernameBg= "${pageContext.request.contextPath }/rygl/dtRyBiangengController";
var controllername= "${pageContext.request.contextPath }/rygl/xcryglController";
//页面初始化
$(function() {
	init();
	
	//按钮绑定事件(保存)
	$("#btnTg").click(function() {
		if($("#rybgForm").validationButton()){
			if(confirm("确定通过此次审核吗？")){
			    //生成json串
			    var data = Form2Json.formToJSON(rybgForm);
			  //组成保存json串格式
			    var data1 = defaultJson.packSaveJson(data);
			  //调用ajax插入
	    		defaultJson.doInsertJson(controllernameBg + "?updateTg", data1);
			    //var a=$(window).manhuaDialog.getParentObj();
			    //a.init();
			    ///window.location = "${pageContext.request.contextPath }/jsp/gdzxt/index/rybiangeng-page.jsp";
			    parent.location.reload(); 
				$(window).manhuaDialog.close();
			}
		}else{
			requireFormMsg();
		  	return;
		}
	});

	$("#btnTh").click(function() {
		if($("#rybgForm").validationButton()){
			if(confirm("确定退回吗？")){
			    //生成json串
			    var data = Form2Json.formToJSON(rybgForm);
			  //组成保存json串格式
			    var data1 = defaultJson.packSaveJson(data);
			  //调用ajax插入
	    		defaultJson.doInsertJson(controllernameBg + "?updateTh", data1);
			    //var a=$(window).manhuaDialog.getParentObj();
			   // a.init();
			   //window.location = "${pageContext.request.contextPath }/jsp/gdzxt/index/rybiangeng-page.jsp";
			   parent.location.reload(); 
				$(window).manhuaDialog.close();
			}
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
	//var parentmain = $(window).manhuaDialog.getParentObj();
	//var rowValue = parentmain.$("#resultXML").val();
	//var tempJson = $('#data').val();
	//alert(tempJson);
	//var tempJson = convertJson.string2json1(rowValue);
	//alert(tempJson.BGUID);
	//$("#DT_RY_BIANGENG_UID").val(tempJson.BGUID);
	//$("#GONGCHENG_UID").val(tempJson.GONGCHENG_UID);
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
			$("#rybgForm").setFormValues(resultobj);
			$('#GANGWEI_UID').val(resultobj.GANGWEI_UID);	
			showTr(resultobj.SHENPI_STATUS);	
		}
	});
		
	//加载附件
	//queryFileData(tempJson.LH_ENTERPRISE_UID, "", "", "777001");
	
}

function showTr(v){
	if(v=='1'){
		$('.c1').show();
	}else if(v=='2'){
		$('.c1').show();
		$('.c2').show();
		$('.c1').children().children().attr('disabled',true);	
	}else if(v=='3'){
		$('.c1').show();
		$('.c2').show();
		$('.c3').show();
		$('.c1').children().children().attr('disabled',true);
		$('.c2').children().children().attr('disabled',true);
	}
}
</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">
	<div class="B-small-from-table-autoConcise">
 	 <h4 class="title">人员变更审核
      	<span class="pull-right"> 
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>   		
      	</span>
      </h4>				 
     <form method="post" id="rybgForm"  id="val1" >
     	<table class="B-table" width="100%" >
     		<tr>
     		    <input type="hidden" fieldname="DT_RY_BIANGENG_UID" />
     		  	<input type="hidden" fieldname="SHENPI_STATUS" />
     		  	<input type="hidden" fieldname="SG_JL" />
     		  	<input type="hidden" fieldname="BB_RY_UID" />
     		  	<input type="hidden" fieldname="GANGWEI_UID" id="GANGWEI_UID"/>
     		  	<input type="hidden" fieldname="NEW_PERSON_UID" />
     		  	<input type="hidden" fieldname="NEW_ZHENGSHU_CODE2" />
     		  	<input type="hidden" fieldname="NEW_AQKH_CODE" />
     		  	<input type="hidden" fieldname="NEW_AGE" />
     		  	<input type="hidden" fieldname="NEW_SHENFENZHENG" />
     		  	<input type="hidden" fieldname="NEW_MOBILE" />
     		  	<input type="hidden" fieldname="GONGCHENG_UID" />
     			<td></td>
     			<td align="center">
     				<h4>变更前</h4>
     			</td>
     			<td align="center">
     				<h4>变更后</h4>
     			</td>
     		</tr>
     		<tr>
     			<th  class="right-border bottom-border text-right">姓名</th>
     			<td>
     				<input type="text" id="NAME" name="NAME" fieldname="NAME" style="width: 90%"/>
     			</td>
     			<td>
     				<input type="text" id="NEW_PERSON_NAME" name="NEW_PERSON_NAME" fieldname="NEW_PERSON_NAME" style="width: 90%"/>
     			</td>
     		</tr>
     		<tr>
     			<th class="right-border bottom-border text-right">岗位</th>
     			<td>
     				<input type="text" id="GANGWEI" name="GANGWEI" fieldname="GANGWEI" style="width: 90%"/>
     			</td>
     			<td>
     				<input type="text" id="GANGWEI" name="GANGWEI" fieldname="GANGWEI" style="width: 90%"/>
     			</td>
     		</tr>
     		<tr>
     			<th  class="right-border bottom-border text-right">证书名称</th>
     			<td>
     				<input type="text" id="ZHENGSHU_NAME" name="ZHENGSHU_NAME" fieldname="ZS" style="width: 90%"/>
     			</td>
     			<td>
     				<input type="text" id="NEW_ZHENGSHU_NAME" name="NEW_ZHENGSHU_NAME" fieldname="NEW_ZHENGSHU_NAME" style="width: 90%"/>
     			</td>
     		</tr>
     		<tr>
     			<th  class="right-border bottom-border text-right">证书编号</th>
     			<td>
     				<input type="text" id="ZHENGSHU_CODE" name="ZHENGSHU_CODE" fieldname="ZSBH" style="width: 90%"/>
     			</td>
     			<td>
     				<input type="text" id="NEW_ZHENGSHU_CODE" name="NEW_ZHENGSHU_CODE" fieldname="NEW_ZHENGSHU_CODE" style="width: 90%"/>
     			</td>
     		</tr>
     		<tr>
     			<th class="right-border bottom-border text-right">专业</th>
     			<td>
     				<input type="text" id="ZHUANYE" name="ZHUANYE" fieldname="ZY" style="width: 90%"/>
     			</td>
     			<td>
     				<input type="text" id="NEW_ZHUANYE" name="NEW_ZHUANYE" fieldname="NEW_ZHUANYE" style="width: 90%"/>
     			</td>
     		</tr>
     		<tr>
     			<th class="right-border bottom-border text-right">职称</th>
     			<td>
     				<input type="text" id="ZHICHENG_NAME" name="ZHICHENG_NAME" fieldname="ZC" style="width: 90%"/>
     			</td>
     			<td>
     				<input type="text" id="NEW_ZHICHENG_NAME" name="NEW_ZHICHENG_NAME" fieldname="NEW_ZHICHENG_NAME" style="width: 90%"/>
     			</td>
     		</tr>
     		<tr>
     			<th class="right-border bottom-border text-right">在岗时间</th>
     			<td>
     				<input type="text" id="OLD_ZG_BEGIN_DATE" name="OLD_ZG_BEGIN_DATE" fieldname="OLD_ZG_BEGIN_DATE" style="width: 40%" class='Wdate' onClick='WdatePicker()'/>
     				<input type="text" id="OLD_ZG_END_DATE" name="OLD_ZG_END_DATE" fieldname="OLD_ZG_END_DATE" style="width: 40%" class='Wdate' onClick='WdatePicker()'/>
     			</td>
     			<td>
     				<input type="text" id="NEW_ZG_BEGIN_DATE" name="NEW_ZG_BEGIN_DATE" fieldname="NEW_ZG_BEGIN_DATE" style="width: 40%" class='Wdate' onClick='WdatePicker()'/>
     				<input type="text" id="NEW_ZG_END_DATE" name="NEW_ZG_END_DATE" fieldname="NEW_ZG_END_DATE" style="width: 40%" class='Wdate' onClick='WdatePicker()'/>
     			</td>
     		</tr>
     		<tr >
     			<th class="right-border bottom-border text-right">变更原因</th>
     			<td colspan="2">
     				<textarea class="span12" rows="2" fieldname="REASON"></textarea>
     			</td>
     		</tr>
     		<tr >
     			<th class="right-border bottom-border text-right">附件</th>
     			<td colspan="2" >
     				<div>
						<table role="presentation" class="table table-striped">
							<tbody fjlb="2052" target_type="1" file_type="2" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>
						</table>
					</div>
     			</td>
     		</tr>
     	</table>

      	<div style="height: 5px;"></div>
		<table id="YJ_LIST"  border="0" width="100%" cellpadding="0" cellspacing="0" class="content">
			<div class="modal-header" style="background:#0866c6;">
					<h3 style="color:white;">审核信息
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
					 		<tr class="c1" style="display: none;">
					 			<th>扣分分值</th>
					 			<td colspan="3">
					 				<select id="FINISH_SCORE" name="FINISH_SCORE" fieldname="FINISH_SCORE">
					 					<option value="0" selected="selected">不扣分</option>
					 					<option value="1">1分</option>
					 					<option value="2">2分</option>
					 					<option value="3">3分</option>
					 					<option value="5">5分</option>
					 					<option value="10">10分</option>
					 					<option value="15">15分</option>
					 				</select>
					 			</td>
					 			
					        </tr>
					 		<tr class="c1" style="display: none;">
					 			<th>安质监站备案意见</th>
					 			<td colspan="3">
					 				<textarea class="span12" rows="2" fieldname="SHENHE_YIJIAN1"></textarea>
					 			</td>
					        </tr>
					 		<tr class="c1" style="display: none;">
					 			<th>审核人</th>
					 			<td>
					 				<input type="text" id="SHENHE_USER1" name="SHENHE_USER1" fieldname="SHENHE_USER1" disabled="disabled"/>
					 			</td>
					 			<th>审核日期</th>
					 			<td>
					 				<input type="text" id="SHENHE_DATE1" name="SHENHE_DATE1" fieldname="SHENHE_DATE1" class='Wdate' onClick='WdatePicker()' disabled="disabled"/>
					 			</td>
					        </tr>
					 		<tr class="c2" style="display: none;">
					 			<th>招标办备案意见</th>
					 			<td colspan="3">
					 				<textarea class="span12" rows="2" fieldname="SHENHE_YIJIAN2"></textarea>
					 			</td>
					        </tr>
					 		<tr class="c2" style="display: none;">
					 			<th>审核人</th>
					 			<td>
					 				<input type="text" id="SHENHE_USER2" name="SHENHE_USER2" fieldname="SHENHE_USER2" disabled="disabled"/>
					 			</td>
					 			<th>审核日期</th>
					 			<td>
					 				<input type="text" id="SHENHE_DATE2" name="SHENHE_DATE2" fieldname="SHENHE_DATE2" class='Wdate' onClick='WdatePicker()' disabled="disabled"/>
					 			</td>
					        </tr>
					 		<tr class="c3" style="display: none;">
					 			<th>主管部门备案意见</th>
					 			<td colspan="3">
					 				<textarea class="span12" rows="2" fieldname="FINISH_YIJIAN"></textarea>
					 			</td>
					        </tr>
					 		<tr class="c3" style="display: none;">
					 			<th>审核人</th>
					 			<td>
					 				<input type="text" id="FINISH_USER" name="FINISH_USER" fieldname="FINISH_USER" disabled="disabled"/>
					 			</td>
					 			<th>审核日期</th>
					 			<td>
					 				<input type="text" id="FINISH_DATE" name="FINISH_DATE" fieldname="FINISH_DATE" class='Wdate' onClick='WdatePicker()' disabled="disabled"/>
					 			</td>
					        </tr>
					    </thead>
					  </table>  
		       		</div>
				</td>
			</tr>
		</table>
      </form>
    </div>
   </div>
  </div>
     <form method="post" id="queryForm"  >
    	  <input type="hidden" id="DT_RY_BIANGENG_UID" fieldname="DT_RY_BIANGENG_UID" name = "DT_RY_BIANGENG_UID" value="<%=bguid %>" operation="="/>
    	  <input type="hidden" id="GONGCHENG_UID" fieldname="v.GONGCHENG_UID" name = "GONGCHENG_UID" value="<%=gcuid %>" operation="="/>
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
 <jsp:include page="/jsp/file_upload/fileuploadold_config_js.jsp" flush="true"/> 
</body>
</html>