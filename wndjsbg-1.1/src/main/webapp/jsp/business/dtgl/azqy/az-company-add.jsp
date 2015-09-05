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
var controllername= "${pageContext.request.contextPath }/jxsb/azCompanyController";
var type ="<%=type%>";
//页面初始化
$(function() {
	init();
    var input = $("#dis").find("input:radio");
    input.attr("disabled","disabled");
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
		    var data = Form2Json.formToJSON(jsCompanyForm);
		    
		  //组成保存json串格式
		    var data1 = defaultJson.packSaveJson(data);
		  //调用ajax插入
		    if($("#AZ_COMPANY_UID").val() == "" || $("#AZ_COMPANY_UID").val() == null){
    			defaultJson.doInsertJson(controllername + "?insert", data1);
    			$("#jsCompanyForm").clearFormResult();
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

    
    <%
    if(type.equals("detail")){
	%>
	//置所有input 为disabled
	$(":input").each(function(i){
	   $(this).attr("disabled", "true");
	  
	 });
	$("#btnClose").attr("disabled", false);
	<%
		}
	%>
	 
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
		//生成json串
		var parentmain = $(window).manhuaDialog.getParentObj();
		var rowValue = parentmain.$("#resultXML").val();
		var tempJson = convertJson.string2json1(rowValue);

		$("#QAZ_COMPANY_UID").val(tempJson.AZ_COMPANY_UID);
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
				$("#shrsjdiv").show();
				$("#shrsj1div").show();	
				var res = dealSpecialCharactor(response.msg);
				$("#resultXML").val(response.msg);
				var resultobj = defaultJson.dealResultJson(res);
				$("#jsCompanyForm").setFormValues(resultobj);				
				return true;
			}
		});
		queryFileData(tempJson.JS_COMPANY_UID, "", "", "460010");
	} else {
		//生成json串
		var parentmain = $(window).manhuaDialog.getParentObj();
		var rowValue = parentmain.$("#resultXML").val();
		var tempJson = convertJson.string2json1(rowValue);
		$("#QAZ_COMPANY_UID").val(tempJson.AZ_COMPANY_UID);
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
				resultobj.SHENHE_JIEGUO="1";//默认通过
				$("#jsCompanyForm").setFormValues(resultobj);	
		
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
      <h4 class="title">安装企业
      	<span class="pull-right">
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
	  		<%if (!type.equals("detail")) {%>
				<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">审核</button>
			<%} %>	  		
      	</span>
      </h4>
       <form method="post" id="queryForm"  >
     		<input type="hidden" id="QAZ_COMPANY_UID" fieldname="AZ_COMPANY_UID" name="AZ_COMPANY_UID" operation="=" />
      </form>
      <div style="height:5px;"></div>
     <form method="post" id="jsCompanyForm"  >
     	<table class="B-table" width="100%" >
      		<input type="hidden" id="AZ_COMPANY_UID" fieldname="AZ_COMPANY_UID" name = "AZ_COMPANY_UID"/>
      		<tr>
				<th width="15%" class="right-border bottom-border text-right">审核结果选择</th>
				<td class="right-border bottom-border">
					<input class="span12" style="width:94%" id="SHENHE_JIEGUO_ID" type="radio" check-type="required" fieldname="SHENHE_JIEGUO" name = "SHENHE_JIEGUO" kind="dic" src="SHENHEJIEGUO" />			
				</td>
				
				<th id="shrsjdiv" width="15%" class="right-border bottom-border text-right">审核人和时间</th>
				<td  id="shrsj1div" class="right-border bottom-border">
					<input class="span12" style="width:20%"  type="text"  fieldname="SHENHENAME" name = "SHENHENAME"/>
	         		-
	         		<input class="span12" style="width:40%"  type="text" fieldname="SHENHE_DATE" name = "SHENHE_DATE"/>         		
				</td>
				
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">审核详细意见</th>
	       	 	<td class="bottom-border right-border" colspan="3">
	         		<textarea class="span12" rows="2" id="SHENHE_YIJIAN" check-type="required" maxlength="4000"  fieldname="SHENHE_YIJIAN" name="SHENHE_YIJIAN"></textarea>
	       	 	</td>
	        </tr>
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">企业名称</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="COMPANY_NAME" type="text" readonly="readonly"  fieldname="COMPANY_NAME" name = "COMPANY_NAME"  />	         	        	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">组织机构代码  </th>
	       	 	<td colspan="3" class="bottom-border right-border">
	       	 	<div id="dis">
					  <input style="width:100%" id="WAIDI_Y" type="text" readonly="readonly"  name = "JIGOU_DAIMA" fieldname="JIGOU_DAIMA" />
					  </div
				</td>
	        </tr>
	        
	        <%if (type.equals("detail")) {%>
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">单位登陆代码</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	         		<input class="span12" style="width:100%" id="DL_CODE" type="text" readonly="readonly"  fieldname="DL_CODE" name = "DL_CODE"  />	         	
	       	 	</td>
	        </tr>	
	        <% } %>
			
			<tr>
				<th width="15%" class="right-border bottom-border text-right">营业执照注册号</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="JIGUO_DAIMA" type="text" readonly="readonly"  fieldname="YYZZ_CODE" name = "YYZZ_CODE"  />	         	        	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">企业信用手册编号  </th>
	       	 	<td colspan="3" class="bottom-border right-border">
	       	 	<div id="dis">
					  <input style="width:100%" id="WAIDI_Y" type="text" readonly="readonly"  name = "XYSC_CODE" fieldname="XYSC_CODE" />
					  </div
				</td>
	        </tr>
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">资质证书编号</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="ZHIZHAO" type="text" readonly="readonly" fieldname="ZHENGSHU_CODE" name = "ZHENGSHU_CODE"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">资质等级</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input style="width:94%" id="ZHIZHAO_VALID" type="text" readonly="readonly" fieldname="ZZDJ_SV" name = "ZZDJ"  />
				</td>
	        </tr>
	         <tr>
				<th width="15%" class="right-border bottom-border text-right">安全生产许可证编号</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="ZHIZHAO" type="text" readonly="readonly" fieldname="AQSCXKZ" name = "AQSCXKZ"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">有效期从</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input style="width:40%" id="ZHIZHAO_VALID" type="text" readonly="readonly" fieldname="YXQ_BEGIN" name = "YXQ_BEGIN"  />到
					<input style="width:40%" id="ZHIZHAO_VALID" type="text" readonly="readonly" fieldname="YXQ_END" name = "YXQ_END"  />
				</td>
	        </tr>
	         <tr>
				<th width="15%" class="right-border bottom-border text-right">企业类型</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="ZHIZHAO" type="text" readonly="readonly" fieldname="COMPANYS_TYPE" name = "COMPANYS_TYPE"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">建立时间</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input style="width:94%" id="ZHIZHAO_VALID" type="text" readonly="readonly" fieldname="JL_DATE" name = "JL_DATE"  />
				</td>
	        </tr>
	         <tr>
				<th width="15%" class="right-border bottom-border text-right">固定电话</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="ZHIZHAO" type="text" readonly="readonly" fieldname="PHONE" name = "QY_PHONE"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">传真</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input style="width:94%" id="ZHIZHAO_VALID" type="text" readonly="readonly" fieldname="FAX" name = "QY_FAX"  />
				</td>
	        </tr>
	        
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">企业详细地址</th>
				<td  class="right-border bottom-border" colspan="3">
					<input style="width:85%" id="ZHUCE_ZIJIN" type="text"  readonly="readonly" fieldname="ADDRESS" name = "ADDRESS"  class="col-xs-10 col-sm-10" />			
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">驻无锡地址</th>
				<td  class="right-border bottom-border" colspan="3">
					<input style="width:85%" id="ZHUCE_ZIJIN" type="text"  readonly="readonly" fieldname="WX_ADDRESS" name = "WX_ADDRESS"  class="col-xs-10 col-sm-10" />			
				</td>
			</tr>
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">邮政编码</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="ZHIZHAO" type="text" readonly="readonly" fieldname="POSTCODE" name = "POSTCODE"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">企业网址</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input style="width:94%" id="ZHIZHAO_VALID" type="text" readonly="readonly" fieldname="WEB" name = "WEB"  />
				</td>
	        </tr>
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">电子信箱</th>
				<td  class="right-border bottom-border" colspan="1">
					<input style="width:85%" id="ZHUCE_ZIJIN" type="text"  readonly="readonly" fieldname="EMAIL" name = "EMAIL"  class="col-xs-10 col-sm-10" />		
				</td>
			</tr>
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">法人代表</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="ZHIZHAO" type="text" readonly="readonly" fieldname="FAREN_DAIBIAO" name = "FAREN_DAIBIAO"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">法人代表电话/手机</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input style="width:94%" id="ZHIZHAO_VALID" type="text" readonly="readonly" fieldname="DAIBIAO_PHONE" name = "DAIBIAO_PHONE"  />
				</td>
	        </tr>
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">企业经理</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="ZHIZHAO" type="text" readonly="readonly" fieldname="COMPANY_JL" name = "COMPANY_JL"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">经理电话/手机</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input style="width:94%" id="ZHIZHAO_VALID" type="text" readonly="readonly" fieldname="JL_PHONE" name = "JL_PHONE"  />
				</td>
	        </tr>
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">技术负责人</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="ZHIZHAO" type="text" readonly="readonly" fieldname="JSFZR" name = "JSFZR"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">负责人电话/手机</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input style="width:94%" id="ZHIZHAO_VALID" type="text" readonly="readonly" fieldname="FZR_PHONE" name = "FZR_PHONE"  />
				</td>
	        </tr>
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">联系人</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="ZHIZHAO" type="text" readonly="readonly" fieldname="LIANXIREN" name = "LIANXIREN"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">联系人移动电话</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input style="width:94%" id="ZHIZHAO_VALID" type="text" readonly="readonly" fieldname="LIANXIREN_MOBILE" name = "LIANXIREN_MOBILE"  />
				</td>
	        </tr>
			
      	</table>
      </form>
        <jsp:include page="/jsp/file_upload/fileuploadold_config_js.jsp" flush="true"/> 
    </div>
   </div>
  </div>

 
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