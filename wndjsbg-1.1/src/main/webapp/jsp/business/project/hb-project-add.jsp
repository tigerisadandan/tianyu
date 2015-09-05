<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>项目-维护</title>
<%
	String type=request.getParameter("type");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/project/projectsController";
var type ="<%=type%>";
//页面初始化
$(function() {
	init();

	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
		   
		  //调用ajax插入
		    if($("#PROJECTS_UID").val() == "" || $("#PROJECTS_UID").val() == null){
    			defaultJson.doInsertJson(controllername + "?insert", data1);
    			$("#projectsForm").clearFormResult();
    		}else{

    			if($("input:radio[name='SHENHE_JIEGUO']:checked").val()==2){
    				//生成json串
	    			 var data = Form2Json.formToJSON(projectsForm);
	    			  //组成保存json串格式
	    			 var data1 = defaultJson.packSaveJson(data);
	   				 var reason = $("#SHENPI_YIJIAN").val();
	   				 if(reason.length==0){
							alert("请填写审批不通过理由!");
							return;
					   }else{
					    	if(confirm("确定不通过此次审核吗？")){
						   		 defaultJson.doUpdateJson(controllername + "?update", data1);
					    	}
						  }
   				}else{
   					//生成json串
   				    var data = Form2Json.formToJSON(projectsForm);
   				    //组成保存json串格式
   				    var data1 = defaultJson.packSaveJson(data);
   					 if(confirm("确定通过此次审核吗？")){
   						defaultJson.doUpdateJson(controllername + "?update", data1);
   					 }
   				}

    		}
		    var a=$(window).manhuaDialog.getParentObj();
		    a.init();
			$(window).manhuaDialog.close();
		
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
	$("#btnClose").attr("disabled", false);
	<%
		}
	%>
	
});
//页面默认参数
function init(){
	$("#shrsjdiv").hide();
	$("#shrsj1div").hide();	
	
	if (type == "insert") {
		//生成json串
			
	} else if (type == "detail") {
		//生成json串
		var parentmain = $(window).manhuaDialog.getParentObj();
		var rowValue = parentmain.$("#resultXML").val();
		var tempJson = convertJson.string2json1(rowValue);
		$("#QPROJECTS_UID").val(tempJson.PROJECTS_UID);
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
				$("#projectsForm").setFormValues(resultobj);				
				return true;
			}
		});
	} else {
		//生成json串
		var parentmain = $(window).manhuaDialog.getParentObj();
		var rowValue = parentmain.$("#resultXML").val();
		var tempJson = convertJson.string2json1(rowValue);
//alert(tempJson.PROJECTS_UID);		
		$("#QPROJECTS_UID").val(tempJson.PROJECTS_UID);
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
				$("#projectsForm").setFormValues(resultobj);				
				return true;
			}
		});
	}	
}
function onlyNum()
{
	
 if(!(event.keyCode==46)&&!(event.keyCode==8)&&!(event.keyCode==37)&&!(event.keyCode==39))
  if((event.keyCode!=46)&&!((event.keyCode>=48&&event.keyCode<=57)||(event.keyCode>=96&&event.keyCode<=105))){
    event.returnValue=false;
  }
}
function isMoney(money){
	var mon=money.value;
	mon=mon*0.5/0.5;
	money.value=mon;
}
</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">
    <div class="B-small-from-table-autoConcise">
      <form method="post" id="queryForm"  >
     	<input type="hidden" id="QPROJECTS_UID" fieldname="PROJECTS_UID" name="PROJECTS_UID" operation="=" />
      </form>
	</div>
	<div style="height:5px;"></div>
	<div class="B-small-from-table-autoConcise">
      <h4 class="title">环保项目信息
      	<span class="pull-right">
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
	  		<%if (!type.equals("detail")) {%>
				<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">审核</button>
			<%} %>	
      	</span>
      </h4>
     <form method="post" id="projectsForm"  >
	      <table class="B-table" width="100%" >
	      		<input type="hidden" id="PROJECTS_UID" fieldname="PROJECTS_UID" name ="PROJECTS_UID"/>
	      		<input type="hidden" id="PROJECTS_TYPE" fieldname="PROJECTS_TYPE" name ="PROJECTS_TYPE"/>
	      		<input type="hidden" id="JS_COMPANY_UID" fieldname="JS_COMPANY_UID" name = "JS_COMPANY_UID"/></TD>
	      		<input type="hidden" id="PROJECTS_CODE" fieldname="PROJECTS_CODE" name ="PROJECTS_CODE"/>
	      	<tr>
				<th width="15%" class="right-border bottom-border text-right">审核结果选择</th>
				<td class="right-border bottom-border">
					<input class="span12" style="width:94%" id="SHENHE_JIEGUO" type="radio" check-type="required" fieldname="SHENHE_JIEGUO" name = "SHENHE_JIEGUO" kind="dic" src="SHENHEJIEGUO" />			
				</td>
				
				<th id="shrsjdiv" width="15%" class="right-border bottom-border text-right">审核人和时间</th>
				<td  id="shrsj1div" class="right-border bottom-border">
					<input class="span12" style="width:18%"  type="text"  fieldname="SHENPINAME" name = "SHENPINAME"/>
	         		-
	         		<input class="span12" style="width:35%"  type="text" fieldname="SHENPI_DATE" name = "SHENHE_DATE"/>         		
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">审核详细意见</th>
	       	 	<td class="bottom-border right-border" colspan="3">
	         		<textarea class="span12" rows="2" id="SHENPI_YIJIAN" check-type="required" maxlength="4000"  fieldname="SHENPI_YIJIAN" name="SHENPI_YIJIAN"></textarea>
	       	 	</td>
	        </tr>
	          <tr>
	       		 <th width="15%" class="right-border bottom-border text-right">重点项目</th>
	       		 <td class="bottom-border right-border">
			         <label class="checkbox inline" >  
		        		<input type="checkbox" name="SZDXM" id="SZDXM" fieldname="SZDXM" value="1"/>
		      			市重点项目
		      		 </label>
		        	 <label class="checkbox inline" >  
		        		<input type="checkbox" name="QZDXM" id="QZDXM" fieldname="QZDXM" value="1"/>
		      			区重点项目
		      		 </label> 
	      		 </td>
	      		  <th width="15%" class="right-border bottom-border text-right"></th>
	      		  <td class="bottom-border right-border">
	      		  </td>
	        </tr>
	        
	        
			<tr>
				<th width="15%" class="right-border bottom-border text-right">项目名称</th>
				<td  class="right-border bottom-border"  colspan="3">
					<input class="span12" style="width:93%" id="PROJECTS_NAME" type="text" placeholder="必填" check-type="required" fieldname="PROJECTS_NAME" name = "PROJECTS_NAME"  />
					<font style="color:red;font-size: 16px"><b>*</b></font>
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">项目建设地址</th>
				<td  class="right-border bottom-border"  colspan="3">
					<input class="span12" style="width:93%" id="JIANSHE_DIZHI" type="text" fieldname="JIANSHE_DIZHI" name = "JIANSHE_DIZHI"  />
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">项目级别</th>
				<td  class="right-border bottom-border">
					<input class="span12" id="PROJECTS_LEVEL" type="radio" check-type="required" kind="dic" src="PROJECTS_LEVEL" name = "PROJECTS_LEVEL" fieldname="PROJECTS_LEVEL">
				</td>
				<th width="15%" class="right-border bottom-border text-right">项目性质</th>
				<td  class="right-border bottom-border">
					<input class="span12" id="PROJECTS_XINZHI" type="radio" check-type="required" kind="dic" src="PROJECTS_XINZHI" name = "PROJECTS_XINZHI" fieldname="PROJECTS_XINZHI">
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">建设类型</th>
				<td  class="right-border bottom-border">
					<input class="span12" id="JIANSHE_TYPE" type="radio" placeholder="" check-type="required" kind="dic" src="JIANSHE_TYPE" name = "JIANSHE_TYPE" fieldname="JIANSHE_TYPE">
				</td>
				<th width="15%" class="right-border bottom-border text-right">项目所在区域</th>
				<td  class="right-border bottom-border" >
					<select class="span12" style="width:85%" kind="dic" src="QY" name="QUYU" id="QUYU" check-type="required" fieldname="QUYU"></select>
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">占地面积(平方米)</th>
				<td  class="right-border bottom-border">
					<input class="span12 text-right" style="width:85%" id="ZHANDI_MIANJI" maxlength="20"  onkeydown="onlyNum()" type="text" fieldname="ZHANDI_MIANJI" name = "ZHANDI_MIANJI"  />
				</td>
				<th width="15%" class="right-border bottom-border text-right">建筑面积(平方米)</th>
				<td  class="right-border bottom-border">
					<input class="span12 text-right" style="width:85%" id="JIANZHU_MIANJI" maxlength="20" onkeydown="onlyNum()" type="text" fieldname="JIANZHU_MIANJI" name = "JIANZHU_MIANJI"  />
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">长度(米)</th>
				<td  class="right-border bottom-border">
					<input class="span12 text-right" style="width:85%" id="CHANGDU" maxlength="20"  onkeydown="onlyNum()" type="text" fieldname="CHANGDU" name = "CHANGDU"  />
				</td>
				<th width="15%" class="right-border bottom-border text-right">总投资(元)</th>
				<td  class="right-border bottom-border">
					<input class="span12 text-right" style="width:85%" id="ZONG_TOUZI" maxlength="20"  onkeydown="onlyNum()" type="text" fieldname="ZONG_TOUZI" name = "ZONG_TOUZI"  />
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">规划许可证号</th>
				<td  class="right-border bottom-border">
					<input class="span12 text-right" style="width:85%" id="GUIHUA_CODE"  type="text" fieldname="GUIHUA_CODE" name = "GUIHUA_CODE"  />
				</td>
				<th width="15%" class="right-border bottom-border text-right">行业分类</th>
				<td  class="right-border bottom-border">
					<input class="span12 text-right" style="width:85%" id="HYFL" type="text" fieldname="HYFL" name = "HYFL"  />
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">许可证发放日期</th>
				<td  class="right-border bottom-border">
					<input style="width:85%" id="GUIHUA_DATE" type="text" fieldname="GUIHUA_DATE" name = GUIHUA_DATE fieldtype="date" fieldformat="YYYY-MM-DD" class="Wdate" onClick="WdatePicker()"/>
				</td>
				<th width="15%" class="right-border bottom-border text-right">拟开工日期</th>
				<td  class="right-border bottom-border">
					<input style="width:85%" id="PLAN_KG_DATE" type="text" fieldname="PLAN_KG_DATE" name = "PLAN_KG_DATE" fieldtype="date" fieldformat="YYYY-MM-DD" class="Wdate" onClick="WdatePicker()"/>
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">预计投产日期</th>
				<td  class="right-border bottom-border">
					<input style="width:85%" id="TC_DATE" type="text" fieldname="TC_DATE" name = "TC_DATE" fieldtype="date" fieldformat="YYYY-MM-DD" class="Wdate" onClick="WdatePicker()"/>
				</td>
				<th width="15%" class="right-border bottom-border text-right">预计竣工日期</th>
				<td  class="right-border bottom-border" >
					<input style="width:85%" id="JG_DATE" type="text" fieldname="JG_DATE" name = "JG_DATE" fieldtype="date" fieldformat="YYYY-MM-DD" class="Wdate" onClick="WdatePicker()"/>
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">企业临时办公地址</th>
				<td  class="right-border bottom-border" colspan="3">
					<input  class="span12" style="width:93%" id="TEMP_DIZHI" type="text" fieldname="TEMP_DIZHI" name = "TEMP_DIZHI" />
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">危险废物经营许可证编号</th>
				<td  class="right-border bottom-border" >
					<input class="span12" style="width:85%" id="WF_XKZ" type="text"  fieldname="WF_XKZ" name = "WF_XKZ"  />
				</td>
				<th width="15%" class="right-border bottom-border text-right">邮政编码</th>
				<td  class="right-border bottom-border">
					<input class="span12" style="width:85%" id="POSTCODE" type="text" maxlength="10"  fieldname="POSTCODE" name = "POSTCODE"  />
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">联系人</th>
				<td  class="right-border bottom-border">
					<input class="span12" style="width:85%" id="LIANXIREN" type="text"  fieldname="LIANXIREN" name = "LIANXIREN"  />
				</td>
				<th width="15%" class="right-border bottom-border text-right">联系人传真</th>
				<td  class="bottom-border" >
					<input class="span12" style="width:85%" id="LIANXIREN_FAX" type="text"  fieldname="LIANXIREN_FAX" name = "LIANXIREN_FAX"  />
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">联系人移动电话 </th>
				<td  class="right-border bottom-border">
					<input class="span12" style="width:85%" id="LIANXIREN_MOBILE" maxlength="20"  type="text"  fieldname="LIANXIREN_MOBILE" name = "LIANXIREN_MOBILE"  />
				</td>
				<th width="15%" class="right-border bottom-border text-right">联系人固定电话</th>
				<td  class="right-border bottom-border">
					<input class="span12" style="width:85%" id="LIANXIREN_PHONE" type="text" maxlength="20"  fieldname="LIANXIREN_PHONE" name = "LIANXIREN_PHONE"  />
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">Email</th>
				<td  class="bottom-border" >
					<input class="span12" style="width:85%" id="EMAIL" type="text"  fieldname="EMAIL" name = "EMAIL"  />
				</td>
				<td  class="bottom-border" width="15%" >
				</td>
				<td  class="right-border bottom-border">
				</td>
			</tr>
	      </table>
      </form>
    </div>
   </div>
  </div>
  <div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id = "queryXML">
         <input type="hidden" name="txtXML" id = "txtXML">
         <input type="hidden" name="txtFilter" order="desc" fieldname="d.CREATED_DATE" id="txtFilter"/>
		 <input type="hidden" name="txtFilter" order="desc" fieldname="d.STATUS" id="txtFilter"/>
		 <input type="hidden" name="txtFilter" order="desc" fieldname="d.PROJECTS_TYPE" id="txtFilter"/>
		 <input type="hidden" name="txtFilter" order="desc" fieldname="d.PROJECTS_XINZHI" id="txtFilter"/>
         <input type="hidden" name="resultXML" id = "resultXML">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
		
 	</FORM>
 </div>
</body>
<script>
</script>
</html>