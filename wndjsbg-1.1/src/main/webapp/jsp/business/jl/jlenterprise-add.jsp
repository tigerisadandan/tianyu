<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>监理企业信息库-维护</title>
<%
	String type=request.getParameter("type");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/jlEnterpriseController";
var controllernameZenZizhi= "${pageContext.request.contextPath }/jl/enterpriseZenZizhiController/";
var type ="<%=type%>";
//页面初始化
$(function() {
	init();
	
	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
		if($("#jlEnterpriseForm").validationButton()){
		    //生成json串
		    var data = Form2Json.formToJSON(jlEnterpriseForm);
		  //组成保存json串格式
		    var data1 = defaultJson.packSaveJson(data);
		  //调用ajax插入
		    if($("#ENTERPRISE_LIBRARY_UID").val() != "" && $("#ENTERPRISE_LIBRARY_UID").val() != null){
    			defaultJson.doInsertJson(controllername + "?update", data1);
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
    
    <%
    if(type.equals("detail")){
	%>
	//置所有input 为disabled
	$(":input").each(function(i){
	   $(this).attr("disabled", "true");
	 });
	$("#shjltxdivid").hide();
	$("#btnClose").removeAttr('disabled');
	<%
		}
	%>
	
});
//页面默认参数
function init(){
if(type == "insert"){
		
	}else if(type == "update" || type == "detail"){
		//生成json串
		var parentmain = $(window).manhuaDialog.getParentObj();
		var rowValue = parentmain.$("#resultXML").val();
		var tempJson = convertJson.string2json1(rowValue);
		$("#QENTERPRISE_LIBRARY_UID").val(tempJson.ENTERPRISE_LIBRARY_UID);
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
				$("#jlEnterpriseForm").setFormValues(resultobj);
				$("#COMPANY_TYPE option").each(function(){ //遍历全部option
				       var txt = $(this).text(); //获取option的内容
				       if(txt==resultobj.COMPANY_TYPE){
				    	   $(this).attr("selected",true);
						}
				 });
				 var code = resultobj.COMPANY_CODE;
				 $("#DAIMA_Z").val(code.split("-")[0]);
				 $("#DAIMA_F").val(code.split("-")[1]);		
				return true;
			}
		});
		//加载附件
		queryFileData(tempJson.ENTERPRISE_LIBRARY_UID, "", "", "999002");
		builderZizhiList();
		//列出审批意见信息
		var queryconditionXX = "{\"logic\":\"and\",\"fieldname\":\"ENTERPRISE_LIBRARY_UID\",\"operation\":\"=\",\"value\":\""+tempJson.ENTERPRISE_LIBRARY_UID+"\",\"fieldtype\":'',\"fieldformat\":''}";
	
		var dataXX = "{querycondition: {conditions: [" +queryconditionXX+"]},pages:{recordsperpage:200, currentpagenum:1, totalpage:0, countrows:0},orders:[{\"fieldname\":\"SHRQ\",\"order\":\"asc\"}]}";
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"?queryspyj", dataXX, spyjList);
	}
}

function builderZizhiList(){
	$.ajax({
		url : controllernameZenZizhi+"queryListZeng?uid="+$("#JL_COMPANY_UID").val(),
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var obj1 = eval('(' + response.msg + ')');
			chushiDengji = "";
			$(obj1).each(function(){


				var cloneNew = $("#cloneTR").clone(true);
				//alert(typeof(cloneNew));
				$(cloneNew).removeAttr("style")
				$(cloneNew).find("select").eq(0).val(this.SG_ZIZHI_UID);
				$("#zizhiList").find("tr").eq(2).before(cloneNew);
				//loadZZDJ($(cloneNew).find("select").eq(0),'zengxiang',this.ZIZHI_DENGJI_UID);
				$(cloneNew).find("#ENTERPRISE_ZEN_ZIZHI_UID").val(this.ENTERPRISE_ZEN_ZIZHI_UID);
				$(cloneNew).find("#ZIZHI_CODE").val(this.ZIZHI_CODE);
				$(cloneNew).find("#ZIZHI_UID").val(this.ZIZHI_UID);
				$(cloneNew).find("#ZIZHI_DENGJI").val(this.ZIZHI_DENGJI);
				//alert(this.VALID_DATE_SV);
				$(cloneNew).find("#ZENG_VALID_DATE").val(this.ZENG_VALID_DATE.substring(0,10));
				$(cloneNew).find("img").eq(1).hide();

				//if(this.CZ!="0"){
				//	$(cloneNew).find("td").eq(3).append("<div id='changeIcon' style='float:right'><i class='icon-warning-sign showXmxxkInfo' title='"+this.CZ+"'></i></div>");
				//	$(cloneNew).css("background","#FFCC99");
				//}
			});
		}
	});
}
</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">
	<div class="B-small-from-table-autoConcise">
 	 <h4 class="title">企业信息
      	<span class="pull-right"> 
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>   		
      	</span>
      </h4>				 
 <form method="post" id="jlEnterpriseForm"  id="val1" >
      <table class="B-table" width="100%" >
	  		<input type="hidden" id="ENTERPRISE_LIBRARY_UID" fieldname="ENTERPRISE_LIBRARY_UID" name = "ENTERPRISE_LIBRARY_UID"/>
        	<input type="hidden" id="JL_COMPANY_UID" fieldname="JL_COMPANY_UID" name = "JL_COMPANY_UID"/>
        	<tr>
				<th width="8%" class="right-border bottom-border text-right">监理企业全称</th>
	       	 	<td class="bottom-border right-border" colspan="3">
	         		<input  id="COMPANY_NAME" check-type="required"  type="text" fieldname="COMPANY_NAME" name = "COMPANY_NAME" />
	       	 	</td> 
	       	 	      	
           </tr>
        	<tr>
				<th width="8%" class="right-border bottom-border text-right">组织机构代码</th>
	       	 	<td class="bottom-border right-border" >
	       	 	<input type="hidden" id="COMPANY_CODE" fieldname="COMPANY_CODE" />
	         		<input id="DAIMA_Z" name="DAIMA_Z" type="text" maxlength="8"  style="width: 49%"/>
						-
					<input type="text" style="width: 15%"  maxlength="1" id="DAIMA_F" name="DAIMA_F">	
	       	 	</td> 
	       	 	<th width="8%" class="right-border bottom-border text-right">附件</th>
	       	 	<td class="bottom-border right-border" width="15%">
					<table role="presentation" id="zzjg" class="table table-striped">
						<tbody fjlb="1071" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true"></tbody>
					</table>
	       	 	</td>    	
           </tr>
           <tr>

         	<th width="8%" class="right-border bottom-border text-right">企业类型</th>
       		<td class="bottom-border right-border" width="15%">
         		<select  id="COMPANY_TYPE"   name="COMPANY_TYPE" fieldname="COMPANY_TYPE"  kind="dic" src="COMPANY_TYPE" defaultMemo="--请选择--">
				</select>
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">是否外地企业</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="WAIDI_Y" type="radio" kind="dic" src="WAIDI_Y"  name="WAIDI_Y" fieldname="WAIDI_Y" defaultValue="N">
         	</td>
         </tr>

           <tr>
				<th width="8%" class="right-border bottom-border text-right">营业执照注册号</th>
	       	 	<td class="bottom-border right-border" width="15%">
	         		<input  id="ZHIZHAO"   type="text" fieldname="ZHIZHAO" name = "ZHIZHAO" />
	       	 	</td> 
  				<th width="8%" class="right-border bottom-border text-right">附件</th>
	       	 	<td class="bottom-border right-border" width="15%">
	       	 		<table role="presentation" id="yyzz" class="table table-striped">
						<tbody fjlb="1061"  class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true">
						</tbody>
					</table>
	       	 	</td>     
           </tr>
           
            <tr>
				<th width="8%" class="right-border bottom-border text-right">营业执照有效期</th>
	       	 	<td class="bottom-border right-border" width="15%" colspan="3">
	         		<input  id="ZHIZHAO_VALID"   type="text" fieldname="ZHIZHAO_VALID" name = "ZHIZHAO_VALID" class='Wdate' onClick='WdatePicker()'/>
	       	 	</td> 
	       	 	   	
           </tr>
           <tr>
           		<th width="8%" class="right-border bottom-border text-right">税务登记号</th>
	       		<td class="bottom-border right-border" width="15%">
	         		<input  id="SHUIWU" type="text" fieldname="SHUIWU" name = "SHUIWU" />
	         	</td>  
	         	<th width="8%" class="right-border bottom-border text-right">附件</th>
	       	 	<td class="bottom-border right-border" width="15%">
							<table role="presentation" id="swdj" class="table table-striped" >
												<tbody fjlb="1062"  class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true">
												</tbody>
										</table>
	       	 	</td>       
           </tr>
         <tr>
			<th width="8%" class="right-border bottom-border text-right">开户银行</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="BANK"   type="text" fieldname="BANK" name = "BANK" />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">附件</th>
	       	 	<td class="bottom-border right-border" width="15%">
					<table role="presentation" id="khxkz" class="table table-striped">
												<tbody fjlb="1063"  class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true">
												</tbody>
										</table>
	       	</td>  
         </tr>
         <tr>
			<th width="8%" class="right-border bottom-border text-right">开户行帐号</th>
       		<td class="bottom-border right-border" width="15%" colspan="3">
         		<input  id="BANK_ACCOUNT" type="text" fieldname="BANK_ACCOUNT" name = "BANK_ACCOUNT" />
         	</td>
         </tr>
       
          <tr>
			
         	<th width="8%" class="right-border bottom-border text-right">主项资质</th>
       		<td class="bottom-border right-border" colspan="3">
					<select  id="ZHU_ZIZHI"   name="ZHU_ZIZHI" fieldname="ZHU_ZIZHI"   kind="dic"  src="T#ZIZHI: ZIZHI_UID:ZIZHI_NAME:1=1 order by SERIAL_NO" defaultValue="" style="width: 15%" >
					</select>
										
					<select  id="ZHU_DENGJI"   name="ZHU_DENGJI" fieldname="ZHU_DENGJI"  kind="dic" src="JLQY_ZXZZ" defaultMemo="--请选择--" style="width: 10%">
				</select>
         	</td>
         </tr>

            <tr>
				<th width="8%" class="right-border bottom-border text-right">主项资质编号</th>
	       	 	<td class="bottom-border right-border" width="15%">
	         		<input  id="ZHENGSHU_CODE"   type="text" fieldname="ZHENGSHU_CODE" name = "ZHENGSHU_CODE" />
	       	 	</td> 
	       	 	<th width="8%" class="right-border bottom-border text-right">附件</th>
	       	 	<td class="bottom-border right-border" width="15%">
	       	 		<table role="presentation" id="zzzs" class="table table-striped">
												<tbody fjlb="1064"  class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true">
												</tbody>
										</table>
	       	 	</td>        	
           </tr>
        
        
         <tr>
				<th width="8%" class="right-border bottom-border text-right">主项资质取得日期</th>
	       	 	<td class="bottom-border right-border" width="15%">
	         		<input  id="ZIZHI_DATE"   type="text" fieldname="ZIZHI_DATE" name = "ZIZHI_DATE" class='Wdate' onClick='WdatePicker()'/>
	       	 	</td> 
	       	 	<th width="8%" class="right-border bottom-border text-right">主项资质有效期</th>
	       	 	<td class="bottom-border right-border" width="15%">
	         		<input  id="VALID_DATE"   type="text" fieldname="VALID_DATE" name = "VALID_DATE" class='Wdate' onClick='WdatePicker()'/>
	       	 	</td>    	
           </tr>
        
					<!-- 增项资质 -->
					<tr>
						<th width="8%" class="right-border bottom-border text-right" >增项资质</th>
						<td  class="right-border bottom-border" colspan="3">
							<table class="B-table" width="100%" id="zizhiList">
								<tr>
									<th style="width:36%">资质</th>
									<th style="width:26%">等级</th>
									<th style="width:14%">资质编号</th>
									<th style="width:24%">有效期</th>
								</tr>
								<tr id="cloneTR" style="display: none;"><!-- 用来复制 -->
									<td>

										<select  id="ZIZHI_UID"   name="ZIZHI_UID" fieldname="ZENG_ZIZHI_UID"   kind="dic"  src="T#ZIZHI:ZIZHI_UID:ZIZHI_NAME:1=1 order by SERIAL_NO" defaultValue="--请选择--" style="width: 100%">
										</select>
									</td>
									<td>
										<select  id="ZIZHI_DENGJI"    name="ZIZHI_DENGJI" fieldname="ZIZHI_DENGJI"  kind="dic" src="JLQY_ZENXZZ" defaultMemo="--请选择--" style="width: 100%">
										</select>
									</td>
									<td><input id="ZIZHI_CODE" style="width:99%" class="span12" name="ZIZHI_CODE" fieldname="ZIZHI_CODE" type="text" /></td>
									<td><input id="ZENG_VALID_DATE"   style="width:80%" fieldtype="date" fieldformat="YYYY-MM-DD" name="ZENG_VALID_DATE" fieldname="ZENG_VALID_DATE" class="Wdate" type="text" onClick="WdatePicker()" /><img onclick="removeZengxiang(this)" style="cursor: pointer;" title="删除" src="/wndjsbg/img/sg/icon-removeZz.jpg"><img onclick="addZengxiang(this)" style="cursor: pointer;" title="增加" src="/wndjsbg/img/sg/icon-addZz.jpg"></td>
								</tr>
				
								<tr>
									<td>
										<select  id="ZIZHI_UID"   name="ZIZHI_UID" fieldname="ZENG_ZIZHI_UID"   kind="dic"  src="T#ZIZHI:ZIZHI_UID:ZIZHI_NAME:1=1 order by SERIAL_NO" defaultValue="--请选择--" style="width: 100%">
										</select>
									</td>
									<td>
										<select  id="ZIZHI_DENGJI"    name="ZIZHI_DENGJI" fieldname="ZIZHI_DENGJI"  kind="dic" src="JLQY_ZENXZZ" defaultMemo="--请选择--" style="width: 100%">
										</select>
									</td>
									<td><input id="ZIZHI_CODE" style="width:99%" class="span12" name="ZIZHI_CODE" fieldname="ZIZHI_CODE" type="text" /></td>
									<td><input id="ZENG_VALID_DATE"   style="width:80%" fieldtype="date" fieldformat="YYYY-MM-DD" name="ZENG_VALID_DATE" fieldname="ZENG_VALID_DATE" class="Wdate" type="text" onClick="WdatePicker()" /><img onclick="removeZengxiang(this)" style="cursor: pointer;" title="删除" src="/wndjsbg/img/sg/icon-removeZz.jpg"><img onclick="addZengxiang(this)" style="cursor: pointer;" title="增加" src="/wndjsbg/img/sg/icon-addZz.jpg"></td>
								
								</tr>
							</table>
							
						</td>
					</tr>
          <tr>
			<th width="8%" class="right-border bottom-border text-right">公司地址</th>
       		<td class="bottom-border right-border" colspan="3">
			       <textarea class="span12"  id="ADDRESS"  type="text" fieldname="ADDRESS" name = "ADDRESS" maxlength="4000" ></textarea>
         	</td>
         	
         </tr>
          <tr>

			<th width="8%" class="right-border bottom-border text-right">邮政编码</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="POSTCODE"  type="text" fieldname="POSTCODE" name = "POSTCODE" />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">公司电话</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="PHONE"  type="text" fieldname="PHONE" name = "PHONE" />
         	</td>
         </tr>
          <tr>

         	<th width="8%" class="right-border bottom-border text-right">公司传真</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="FAX" type="text" fieldname="FAX" name = "FAX" />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">公司主页</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="URL" type="text" fieldname="URL" name = "URL" />
         	</td>
         </tr>
         <tr>
			<th width="8%" class="right-border bottom-border text-right">法人代表</th>
       		<td class="bottom-border right-border" >
         		<input  id="FAREN"  type="text" fieldname="FAREN" name = "FAREN" />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">法人职称</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="FAREN_ZHICHENG"  type="text" fieldname="FAREN_ZHICHENG" name = "FAREN_ZHICHENG" />
         	</td>
         </tr>
          <tr>

         	<th width="8%" class="right-border bottom-border text-right">法人代表移动电话</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="FAREN_MOBILE" type="text" fieldname="FAREN_MOBILE" name = "FAREN_MOBILE" />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">联系人</th>
       		<td class="bottom-border right-border" colspan="3">
         		<input  id="LIANXIREN"  type="text" fieldname="LIANXIREN" name = "LIANXIREN" />
         	</td>
         </tr>
         
        
          <tr>
			<th width="8%" class="right-border bottom-border text-right">联系人手机</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="LIANXIREN_MOBILE"  type="text" fieldname="LIANXIREN_MOBILE" name = "LIANXIREN_MOBILE" />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">联系人邮箱</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="LIANXIREN_MAIL" type="text" fieldname="LIANXIREN_MAIL" name = "LIANXIREN_MAIL" />
         	</td>
         </tr>
         <tr>
			<th width="8%" class="right-border bottom-border text-right">备注</th>
         	<td class="bottom-border right-border" colspan="3"> 
			     <textarea class="span12" rows="" cols="" maxlength="4000" fieldname="DESCRIPTION" name="DESCRIPTION"></textarea>
         	</td>
         </tr>
      </table>
      
    	<div style="height: 5px;"></div>
      		<div id="shjltxdivid">
				<div class="modal-header" style="background:#0866c6;">
				<h3 style="color:white;">监理企业资质审核
				<span class="pull-right">
					<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">审核</button>
	  			</span>
				</h3>
				</div>
      			<table id="shenheTable" class="B-table" width="100%" >
			        <tr>
						<th width="15%" class="right-border bottom-border text-right">是否通过</th>
			       	 	<td class="bottom-border right-border">
			       	 		<input id="SHJG" type="radio" check-type="required" fieldname="SHJG" name = "SHJG" defaultValue="1" kind="dic" src="SHYJ_SY" />	
			       	 	</td>
			        </tr>
			       	<tr>
						<th width="15%" class="right-border bottom-border text-right">审核意见</th>
			       	 	<td class="bottom-border right-border">
			       	 		<textarea class="span12" rows="2" id="SHYJ" check-type="required" maxlength="4000"  fieldname="SHYJ" name="SHYJ"></textarea>
			       	 	</td>
			        </tr>
				</table>
			</div>
      </form>
      
      <div style="height: 5px;"></div>
		<table id="YJ_LIST"  border="0" width="100%" cellpadding="0" cellspacing="0" class="content">
			<div class="modal-header" style="background:#0866c6;">
					<h3 style="color:white;">审核记录</h3>
			</div>
			<tr>
				<td>
					<div class="overFlowX">
				 		<table width="100%" class="table-hover table-activeTd B-table" id="spyjList" type="single" pageNum="2000"  noPage="true">
					    <thead>
					 		<tr style="display:blank">
					           <th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
					            <th fieldname="SHR" style="width:200px;" colindex=1 tdalign="center" >审核人</th>
					            <th fieldname="SHRQ_SV" style="width:250px;" colindex=2 tdalign="center" maxlength="200" >&nbsp;审核时间&nbsp;</th>
								<th fieldname="SHJG_SV" colindex=2 tdalign="center" maxlength="200" >&nbsp;审核结果&nbsp;</th>
								<th fieldname="SHYJ" colindex=2 tdalign="center" maxlength="200" >&nbsp;审核意见&nbsp;</th>
					        </tr>
					    </thead>
					    <tbody></tbody>
					  </table>  
		       		</div>
				</td>
			</tr>
	</table>
    </div>
   </div>
  </div>
     <form method="post" id="queryForm"  >
    	  <input type="hidden" id="QENTERPRISE_LIBRARY_UID" fieldname="ENTERPRISE_LIBRARY_UID" name = "ENTERPRISE_LIBRARY_UID" operation="="/>
     </form> 
  
  <div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id = "queryXML">
         <input type="hidden" name="txtXML" id = "txtXML">
         <input type="hidden" name="txtFilter"  order="desc" fieldname = "ENTERPRISE_LIBRARY_UID" id = "txtFilter">
         <input type="hidden" name="resultXML" id = "resultXML">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
 	</FORM>
 </div>
 <jsp:include page="/jsp/file_upload/fileuploadold_config_js.jsp" flush="true"/> 
</body>
</html>