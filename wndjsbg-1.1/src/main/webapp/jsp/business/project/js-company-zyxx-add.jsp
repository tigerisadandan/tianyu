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
	String yyzz=Constants.FS_FILEUPLOAD_FJLB_QY_YYZZ;
	String zzjgdm=Constants.FS_FILEUPLOAD_FJLB_QY_ZZJGDM;
	String SQWTS=Constants.FS_FILEIPLOAD_FJLB_QY_SQWTS;
	String CNS=Constants.FS_FILEIPLOAD_FJLB_QY_CNS;
	String JBRSFZ=Constants.FS_FILEIPLOAD_FJLB_JBRSFZ;
	String JBRZP=Constants.FS_FILEIPLOAD_FJLB_JBRZP;
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/project/jsCompanyController";
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
		    var data = Form2Json.formToJSON(jsCompanyForm);
		    
		  //组成保存json串格式
		    var data1 = defaultJson.packSaveJson(data);
		  //调用ajax插入
		    if($("#JS_COMPANY_UID").val() == "" || $("#JS_COMPANY_UID").val() == null){
    			defaultJson.doInsertJson(controllername + "?insert", data1);
    			$("#jsCompanyForm").clearFormResult();
    		}else{

    			if($("input:radio[name='SHENHE_JIEGUO']:checked").val()==2){
    				 var reason = $("#SHENHE_YIJIAN").val();
    				 if(reason.length==0){
 						alert("请填写审批不通过理由!");
 						return;
 						}else{defaultJson.doUpdateJson(controllername + "?updateZyxx", data1);}
    			}else{
    			defaultJson.doUpdateJson(controllername + "?updateZyxx", data1);
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

		$("#QJS_COMPANY_UID").val(tempJson.JS_COMPANY_UID);
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
		queryFileData(tempJson.JS_COMPANY_UID, "", "", "");
	} else {
		//生成json串
		var parentmain = $(window).manhuaDialog.getParentObj();
		var rowValue = parentmain.$("#resultXML").val();
		var tempJson = convertJson.string2json1(rowValue);
		$("#QJS_COMPANY_UID").val(tempJson.JS_COMPANY_UID);
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
				resultobj.ZYXX_SHENHE_YIJIAN="";
				$("#jsCompanyForm").setFormValues(resultobj);	
		
				return true;
			}
		});
		queryFileData(tempJson.JS_COMPANY_UID, "", "", "460010");
	}
	
	    var rows = $("tbody tr" ,$("#zzjgfj"));
		if(rows.size()==0){
			$("tbody" ,$("#zzjgfj")).append("<tr><td colspan=\"1\" style=\"height: 1px;\" align=\"center\">暂无附件记录</td></tr>");
		}
	    var rows2 = $("tbody tr" ,$("#yyzzfj"));
		if(rows2.size()==0){
			$("tbody" ,$("#yyzzfj")).append("<tr><td colspan=\"1\" style=\"height: 1px;\" align=\"center\">暂无附件记录</td></tr>");
		}
		var rows3 = $("tbody tr" ,$("#SQWTSfj"));
		if(rows3.size()==0){
			$("tbody" ,$("#SQWTSfj")).append("<tr><td colspan=\"1\" style=\"height: 1px;\" align=\"center\">暂无附件记录</td></tr>");
		}
		var rows4 = $("tbody tr" ,$("#CNSfj"));
		if(rows4.size()==0){
			$("tbody" ,$("#CNSfj")).append("<tr><td colspan=\"1\" style=\"height: 1px;\" align=\"center\">暂无附件记录</td></tr>");
		}
		var rows5 = $("tbody tr" ,$("#JBRSFZfj"));
		if(rows5.size()==0){
			$("tbody" ,$("#JBRSFZfj")).append("<tr><td colspan=\"1\" style=\"height: 1px;\" align=\"center\">暂无附件记录</td></tr>");
		}
		var rows6 = $("tbody tr" ,$("#JBRZPfj"));
		if(rows6.size()==0){
			$("tbody" ,$("#JBRZPfj")).append("<tr><td colspan=\"1\" style=\"height: 1px;\" align=\"center\">暂无附件记录</td></tr>");
		}
}


</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">
	
	<div class="B-small-from-table-autoConcise">
      <h4 class="title">建设单位
      	<span class="pull-right">
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
	  		<%if (!type.equals("detail")) {%>
				<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">审核</button>
			<%} %>	  		
      	</span>
      </h4>
       <form method="post" id="queryForm"  >
     		<input type="hidden" id="QJS_COMPANY_UID" fieldname="JS_COMPANY_UID" name="JS_COMPANY_UID" operation="=" />
      </form>
      <div style="height:5px;"></div>
     <form method="post" id="jsCompanyForm"  >
     	<table class="B-table" width="100%" >
      		<input type="hidden" id="JS_COMPANY_UID" fieldname="JS_COMPANY_UID" name = "JS_COMPANY_UID"/>
      		<tr>
				<th width="15%" class="right-border bottom-border text-right">审核结果选择</th>
				<td class="right-border bottom-border">
					<input class="span12" style="width:94%" id="SHENHE_JIEGUO_ID" type="radio" check-type="required" fieldname="SHENHE_JIEGUO" name = "SHENHE_JIEGUO" kind="dic" src="SHENHEJIEGUO" />			
				</td>
				
				<th id="shrsjdiv" width="15%" class="right-border bottom-border text-right">审核人和时间</th>
				<td  id="shrsj1div" class="right-border bottom-border">
					<input class="span12" style="width:20%"  type="text"  fieldname="ZYXX_SHENHENAME" name = "SHENHENAME"/>
	         		-
	         		<input class="span12" style="width:40%"  type="text" fieldname="ZYXX_SHENHE_DATE" name = "SHENHE_DATE"/>         		
				</td>
				
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">审核详细意见</th>
	       	 	<td class="bottom-border right-border" colspan="3">
	         		<textarea class="span12" rows="2" id="SHENHE_YIJIAN" check-type="required" maxlength="4000"  fieldname="ZYXX_SHENHE_YIJIAN" name="SHENHE_YIJIAN"></textarea>
	       	 	</td>
	        </tr>
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">登录名</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	         		<input class="span12" style="width:100%" id="USER_NAME" type="text" readonly="readonly"  fieldname="USER_NAME" name = "USER_NAME"  />	         	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">企业类型</th>
	       	 	<td colspan="3" class="bottom-border right-border" id="radio">
					  <input style="width:100%" id="WAIDI_Y" type="radio"   kind="dic" src="JS_COMPANY_TYPE" name = "COMPANY_TYPE" fieldname="COMPANY_TYPE" />
					 
				</td>
	        </tr>	
        	<tr>
				<th width="15%" class="right-border bottom-border text-right">企业名称</th>
				<td  class="right-border bottom-border" colspan="3">
					<input class="span12" style="width:100%" id="COMPANY_NAME" type="text" readonly="readonly"  fieldname="NEW_COMPANY_NAME" name = "COMPANY_NAME"  />					
				</td>
			</tr>
			
			<tr>
				<th width="15%" class="right-border bottom-border text-right">组织机构代码</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="JIGUO_DAIMA" type="text" readonly="readonly"  fieldname="JIGUO_DAIMA" name = "JIGUO_DAIMA"  />	         	        	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">外地企业  </th>
	       	 	<td colspan="3" class="bottom-border right-border">
	       	 	<div id="dis">
					  <input style="width:100%" id="WAIDI_Y" type="radio"  kind="dic" src="WAIDI_Y" name = "WAIDI_Y" fieldname="WAIDI_Y" />
					  </div
				</td>
	        </tr>
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">营业执照注册号</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="ZHIZHAO" type="text" readonly="readonly" fieldname="NEW_ZHIZHAO" name = "ZHIZHAO"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right"> 营业执照有效期</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input style="width:94%" id="ZHIZHAO_VALID" type="text" readonly="readonly" fieldname="NEW_ZHIZHAO_VALID" name = "ZHIZHAO_VALID"  />
				</td>
	        </tr>
	        
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">注册资金</th>
				<td  class="right-border bottom-border" colspan="1">
					<input style="width:85%" id="ZHUCE_ZIJIN" type="text"  readonly="readonly" fieldname="NEW_ZHUCE_ZIJIN" name = "ZHUCE_ZIJIN"  class="col-xs-10 col-sm-10" />万元			
				</td>
				<th width="15%" class="right-border bottom-border text-right">企业性质</th>
				<td  class="right-border bottom-border" colspan="1">
					 <select class="span12" style="width:85%" type="text"  errormsg="" kind="dic" src="JSQYXZ"   class="col-xs-10 col-sm-10"
							fieldname="COMPANY_PROPERTY" maxlength="40" name="COMPANY_PROPERTY" id="COMPANY_PROPERTY">
							</select>			
				</td>
			</tr>
			
			 <tr>
				<th width="15%" class="right-border bottom-border text-right">开户银行 </th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	         		<input id="BANK" style="width:94%" type="text" readonly="readonly" fieldname="BANK" name = "BANK"   class="col-xs-10 col-sm-10" />
					
										         	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">开户银行帐号 </th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input style="width:94%" id="BANK_ACCOUNT" type="text"  readonly="readonly" fieldname="BANK_ACCOUNT" name = "BANK_ACCOUNT" class="col-xs-10 col-sm-10" />
				</td>
	        </tr>
			
			 <tr>
				  <th width="15%" class="right-border bottom-border text-right">公司地址</th>
	       	 	<td class="bottom-border right-border" colspan="3">
	         		<textarea style="width:98%" id="ADDRESS" type="text" readonly="readonly"  fieldname="ADDRESS" name = "ADDRESS" class="col-xs-10 col-sm-10" cols="" rows=""></textarea>
	       	 	</td>
	        </tr>
	         
	         <tr>
				<th width="15%" class="right-border bottom-border text-right">邮政编码</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	         		<input id="POSTCODE" type="text" style="width:94%"  readonly="readonly" fieldname="POSTCODE" name = "POSTCODE"  class="col-xs-10 col-sm-10" />         	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right"> 公司主页</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input id="URL" type="text" style="width:94%" readonly="readonly" fieldname="URL" name="URL" class="input-medium input-mask-email" />
				</td>
	        </tr>
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">公司电话</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	         		<input id="PHONE" type="text" style="width:94%"  readonly="readonly" fieldname="PHONE" name = "PHONE" class="input-medium input-mask-phone" />         	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right"> 传真 </th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input id="FAX" type="text" style="width:94%" readonly="readonly" fieldname="FAX" name = "FAX"  class="col-xs-10 col-sm-10" />
				</td>
	        </tr>
	        
	          <tr>
				<th width="15%" class="right-border bottom-border text-right">法人代表</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	         		<input id="FAREN" type="text"  style="width:94%" readonly="readonly" fieldname="FAREN" name = "FAREN" class="col-xs-10 col-sm-10" />         	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right"> 联系电话 </th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input id="FAREN_MOBILE" type="text" style="width:94%"  readonly="readonly" fieldname="FAREN_MOBILE" name = "FAREN_MOBILE" class="input-medium input-mask-phone" />
				</td>
	        </tr>
	        
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">联系人</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	         		<input id="FZR" type="text" style="width:94%" readonly="readonly" fieldname="FZR" name = "FZR"  class="col-xs-10 col-sm-10" />         	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right"> 联系电话 </th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input id="FZR_MOBILE" type="text" style="width:94%"  readonly="readonly" fieldname="FZR_MOBILE" name = "FZR_MOBILE"  class="col-xs-10 col-sm-10" />
				</td>
	        </tr>
	     
			
			<tr>
				<th width="15%" class="right-border bottom-border text-right">邮箱</th>
				<td  class="right-border bottom-border" colspan="1">
					<input class="span12" style="width:100%" id="FZR_EMAIL" type="text"  readonly="readonly" fieldname="FZR_EMAIL" name = "FZR_EMAIL"  />					
				</td>
			</tr>
	      
	       <tr>
				<th width="15%" class="right-border bottom-border text-right">组织机构代码</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	         		<table role="presentation" class="table table-striped" id="zzjgfj">
							<tbody fjlb="<%=zzjgdm %>" id="zzjgdm" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true"></tbody>
						</table>         	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right"> 营业执照附件</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<table role="presentation" class="table table-striped" id="yyzzfj">
							<tbody fjlb="<%=yyzz %>" id="yyzz" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true" ></tbody>
						</table>
				</td>
	        </tr>
	        <tr>
				<th width="15%" class="right-border bottom-border text-right"> 授权委托书</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	         		<table role="presentation" class="table table-striped" id="SQWTSfj">
							<tbody fjlb="<%=SQWTS %>" id="" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true"></tbody>
						</table>         	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right"> 新区建设环保动态管理系<br/>统注册申请信息表附件</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<table role="presentation" class="table table-striped" id="CNSfj">
							<tbody fjlb="<%=CNS %>" id="CNS" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true" ></tbody>
						</table>
				</td>
	        </tr>
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">经办人身份证</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	         		<table role="presentation" class="table table-striped" id="JBRSFZfj">
							<tbody fjlb="<%=JBRSFZ %>" id="JBRSFZ" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true"></tbody>
						</table>         	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">  经办人照片</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<table role="presentation" class="table table-striped" id="JBRZPfj">
							<tbody fjlb="<%=JBRZP %>" id="JBRZP" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true" ></tbody>
						</table>
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