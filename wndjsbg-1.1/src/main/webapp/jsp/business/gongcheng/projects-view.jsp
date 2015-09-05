<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>项目-维护</title>
<%
	String type=request.getParameter("PRIJECTS_UID");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/project/projectsController";

var id ="<%=type%>";
//页面初始化
$(function() {
	init();
    $("input").attr("disabled","disabled");
	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
		   //生成json串
		    var data = Form2Json.formToJSON(projectsForm);
		  //组成保存json串格式
		    var data1 = defaultJson.packSaveJson(data);
		  //调用ajax插入
		    if($("#PROJECTS_UID").val() == "" || $("#PROJECTS_UID").val() == null){
    			defaultJson.doInsertJson(controllername + "?insert", data1);
    			$("#projectsForm").clearFormResult();
    		}else{

    			if($("input:radio[name='SHENHE_JIEGUO']:checked").val()==2){
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
   						defaultJson.doUpdateJson(controllername + "?update", data1);
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
	
		//生成json串
		var parentmain = $(window).manhuaDialog.getParentObj();
		var rowValue = parentmain.$("#resultXML").val();
		var tempJson = convertJson.string2json1(rowValue);
		$("#QPROJECTS_UID").val(id);
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
	

//	unitsInit();
}
function unitsInit(){
	var gcid=$("#QPROJECTS_UID").val();
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		//调用ajax插入
    var response=defaultJson.doQueryJsonListReturn("${pageContext.request.contextPath }/units/projectsUnitsController?querybygcid&gcid="+gcid+"&type=d",data,DT1);
    var res = dealSpecialCharactor(response.msg);
	 if(response.msg=="0"){
				    return;
	 }
					var resultmsgobj = convertJson.string2json1(res);
					json=resultmsgobj.response.data;
					size=resultmsgobj.response.data.length;
	var units="";
	for(var i=0;i<size;i++){
	units+=resultmsgobj.response.data[i].UNITS_UID+",";
	}
	$("#UNTISS").val(units);
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
      <h4 class="title">项目分期信息
      	<span class="pull-right">
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
      		<!-- 
	  	
				<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">审核</button>

			 -->
      	</span>
      </h4>
     <form method="post" id="projectsForm"  >
	      <table class="B-table" width="100%" >
	      		<input type="hidden" id="PROJECTS_UID" fieldname="PROJECTS_UID" name ="PROJECTS_UID"/>
	      		<input type="hidden" id="PROJECTS_TYPE" fieldname="PROJECTS_TYPE" name ="PROJECTS_TYPE"/>
	      		<input type="hidden" id="JS_COMPANY_UID" fieldname="JS_COMPANY_UID" name = "JS_COMPANY_UID"/></TD>
	      		<input type="hidden" id="PROJECTS_CODE" fieldname="PROJECTS_CODE" name ="PROJECTS_CODE"/>
	      		<input type="hidden" id="UNTISS" fieldname="UNTISS" name ="UNTISS"/>
	      	<tr>
				<th width="15%" class="right-border bottom-border text-right">审核结果选择</th>
				<td class="right-border bottom-border">
					<input class="span12" style="width:94%" id="SHENHE_JIEGUO" type="radio" disabled="disabled" check-type="required" fieldname="SHENHE_JIEGUO" name = "SHENHE_JIEGUO" kind="dic" src="SHENHEJIEGUO" />			
				</td>
				
				<th id="shrsjdiv" width="15%" class="right-border bottom-border text-right">审核人和时间</th>
				<td  id="shrsj1div" class="right-border bottom-border">
					<input class="span12" style="width:18%"  type="text"  fieldname="SHENPINAME" disabled="disabled" name = "SHENPINAME"/>
	         		-
	         		<input class="span12" style="width:35%"  type="text" fieldname="SHENPI_DATE" disabled="disabled" name = "SHENPI_DATE"/>         		
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">审核详细意见</th>
	       	 	<td class="bottom-border right-border" colspan="3">
	         		<textarea class="span12" rows="2" id="SHENPI_YIJIAN" check-type="required" disabled="disabled" maxlength="4000"  fieldname="SHENPI_YIJIAN" name="SHENPI_YIJIAN"></textarea>
	       	 	</td>
	        </tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">项目名称</th>
				<td  class="right-border bottom-border">
					<input class="span12" style="width:85%" id="PROJECTS_NAME" type="text" disabled="disabled" placeholder="必填" check-type="required" fieldname="PROJECTS_NAME" name = "PROJECTS_NAME"  />
					<font style="color:red;font-size: 16px"><b>*</b></font>
				</td>
				<th width="15%" class="right-border bottom-border text-right">项目建设地址</th>
				<td  class="right-border bottom-border" >
					<input class="span12" style="width:85%" id="JIANSHE_DIZHI" type="text" disabled="disabled" fieldname="JIANSHE_DIZHI" name = "JIANSHE_DIZHI"  />
				</td>
				
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">项目级别</th>
				<td  class="right-border bottom-border">
					<input class="span12" id="PROJECTS_LEVEL" type="radio" check-type="required" disabled="disabled" kind="dic" src="PROJECTS_LEVEL" name = "PROJECTS_LEVEL" fieldname="PROJECTS_LEVEL">
				</td>
				<th width="15%" class="right-border bottom-border text-right">项目性质</th>
				<td  class="right-border bottom-border">
					<input class="span12" id="PROJECTS_XINZHI" type="radio" check-type="required" disabled="disabled" kind="dic" src="PROJECTS_XINZHI" name = "PROJECTS_XINZHI" fieldname="PROJECTS_XINZHI">
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">建设类型</th>
				<td  class="right-border bottom-border">
					<input class="span12" id="JIANSHE_TYPE" type="radio" placeholder="" disabled="disabled" check-type="required" kind="dic" src="JIANSHE_TYPE" name = "JIANSHE_TYPE" fieldname="JIANSHE_TYPE">
				</td>
				
				<th width="15%" class="right-border bottom-border text-right">项目所在区域</th>
				<td  class="right-border bottom-border" >
					<select class="span12" style="width:85%" kind="dic" src="QY" disabled="disabled" name="QUYU" id="QUYU" check-type="required" fieldname="QUYU"></select>
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">项目占地面积(平方米)</th>
				<td  class="right-border bottom-border">
					<input class="span12 text-right" style="width:85%" id="ZHANDI_MIANJI" maxlength="20" disabled="disabled"  onkeydown="onlyNum()" type="text" fieldname="ZHANDI_MIANJI" name = "ZHANDI_MIANJI"  />
				</td>
				<th width="15%" class="right-border bottom-border text-right">建筑面积(平方米)</th>
				<td  class="right-border bottom-border">
					<input class="span12 text-right" style="width:85%" id="JIANZHU_MIANJI" maxlength="20" disabled="disabled" onkeydown="onlyNum()" type="text" fieldname="JIANZHU_MIANJI" name = "JIANZHU_MIANJI"  />
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">长度(米)</th>
				<td  class="right-border bottom-border">
					<input class="span12 text-right" style="width:85%" id="CHANGDU" maxlength="20" disabled="disabled" onkeydown="onlyNum()" type="text" fieldname="CHANGDU" name = "CHANGDU"  />
				</td>
				<th width="15%" class="right-border bottom-border text-right">总投资(元)</th>
				<td  class="right-border bottom-border">
					<input class="span12 text-right" style="width:85%" id="ZONG_TOUZI" maxlength="20" disabled="disabled" onkeydown="onlyNum()" onkeyup="isMoney(this)" type="text" fieldname="ZONG_TOUZI" name = "ZONG_TOUZI"  />
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">行业分类</th>
				<td  class="right-border bottom-border">
					<input class="span12 text-right" style="width:85%" id="HYFL" type="text" disabled="disabled" fieldname="HYFL" name = "HYFL"  />
				</td>
				<th width="15%" class="right-border bottom-border text-right">拟开工日期</th>
				<td  class="right-border bottom-border">
					<input style="width:85%" id="PLAN_KG_DATE" type="text" fieldname="PLAN_KG_DATE" disabled="disabled" name = "PLAN_KG_DATE" fieldtype="date" fieldformat="YYYY-MM-DD" class="Wdate" onClick="WdatePicker()"/>
				</td>
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">预计投产日期</th>
				<td  class="right-border bottom-border">
					<input style="width:85%" id="TC_DATE" type="text" fieldname="TC_DATE" disabled="disabled" name = "TC_DATE" fieldtype="date" fieldformat="YYYY-MM-DD" class="Wdate" onClick="WdatePicker()"/>
				</td>
				<th width="15%" class="right-border bottom-border text-right">预计竣工日期</th>
				<td  class="right-border bottom-border">
					<input style="width:85%" id="JG_DATE" type="text" fieldname="JG_DATE" disabled="disabled" name = "JG_DATE" fieldtype="date" fieldformat="YYYY-MM-DD" class="Wdate" onClick="WdatePicker()"/>
				</td>
			</tr>
			<tr>
				
				<th width="15%" class="right-border bottom-border text-right">联系人</th>
				<td  class="right-border bottom-border">
					<input class="span12" style="width:85%" id="LIANXIREN" type="text" disabled="disabled" fieldname="LIANXIREN" name = "LIANXIREN"  />
				</td>
				<th width="15%" class="right-border bottom-border text-right">联系人电话 </th>
				<td  class="right-border bottom-border">
					<input class="span12" style="width:85%" id="LIANXIREN_MOBILE" maxlength="20" disabled="disabled" type="text"  fieldname="LIANXIREN_MOBILE" name = "LIANXIREN_MOBILE"  />
				</td>
			</tr>
			<tr>
				
				<th width="15%" class="right-border bottom-border text-right">联系人固定电话</th>
				<td  class="right-border bottom-border">
					<input class="span12" style="width:85%" id="LIANXIREN_PHONE" type="text" maxlength="20" disabled="disabled" fieldname="LIANXIREN_PHONE" name = "LIANXIREN_PHONE"  />
				</td>
				<th width="15%" class="right-border bottom-border text-right">Email</th>
				<td  class="bottom-border">
					<input class="span12" style="width:85%" id="EMAIL" type="text" disabled="disabled" fieldname="EMAIL" name = "EMAIL"  />
				</td>
			</tr>
			
	      </table>
      </form>
      <!-- 
      <div class="overFlowX">
      <h4 class="title">单位工程
      	
      </h4>
	 <table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single"  pageNum="18">
	                <thead>
	                	<tr>
	                		<th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
							<th fieldname="UNITS_UID" colindex=0 tdalign="center" style="display: none;" >&nbsp;单体建筑UID&nbsp;</th>
							<th fieldname="UNITS_NAME" colindex=12 tdalign="center" maxlength="30" >&nbsp;单体建筑名称&nbsp;</th>
							<th fieldname="UNITS_CODE" colindex=13 tdalign="center" maxlength="30"  style="display: none;" >&nbsp;单体建筑代码&nbsp;</th>
							<th fieldname="UNITS_TYPE_SV" colindex=14 tdalign="center" maxlength="30" >&nbsp;建筑物类型&nbsp;</th>
							<th fieldname="JGLX" colindex=15 tdalign="center" maxlength="30" >&nbsp;结构类型&nbsp;</th>
							<th fieldname="DJLX_SV" colindex=16 tdalign="center" maxlength="30" >&nbsp;地基类型&nbsp;</th>
							<th fieldname="JCLX_SV" colindex=17 tdalign="center" maxlength="30" >&nbsp;基础类型&nbsp;</th>
							<th fieldname="JZMJ" colindex=18 tdalign="center" >&nbsp;建筑面积（平方米）&nbsp;</th>
							<th fieldname="GCZJ" colindex=19 tdalign="center"  style="display: none;">&nbsp;工程造价&nbsp;</th>
							<th fieldname="DSMJ" colindex=20 tdalign="center"  style="display: none;">&nbsp;地上面积&nbsp;</th>
							<th fieldname="DXMJ" colindex=21 tdalign="center" style="display: none;" >&nbsp;地下面积&nbsp;</th>
							<th fieldname="DSCS" colindex=22 tdalign="center"  style="display: none;">&nbsp;地上层数&nbsp;</th>
							<th fieldname="DXCS" colindex=23 tdalign="center" style="display: none;" >&nbsp;地下层数&nbsp;</th>
							<th fieldname="DSJG" colindex=24 tdalign="center" maxlength="30" style="display: none;" >&nbsp;地上结构类型&nbsp;</th>
							<th fieldname="DXJG" colindex=25 tdalign="center" maxlength="30" style="display: none;" >&nbsp;地下结构类型&nbsp;</th>
							<th fieldname="YCCS" colindex=26 tdalign="center" style="display: none;" >&nbsp;跃层层数&nbsp;</th>
							<th fieldname="CD" colindex=27 tdalign="center" style="display: none;" style="display: none;" >&nbsp;长度&nbsp;</th>
							<th fieldname="ZXBZ" colindex=28 tdalign="center" maxlength="30"  style="display: none;" >&nbsp;装修标准&nbsp;</th>
							<th fieldname="YT" colindex=29 tdalign="center" maxlength="30" >&nbsp;用途&nbsp;</th>
	                	</tr>
	                </thead>
	              	<tbody></tbody>
	           </table>
	</div> -->
    </div>
   </div>
  </div>
  <div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id = "queryXML">
         <input type="hidden" name="txtXML" id = "txtXML">
         <input type="hidden" name="txtFilter" order="desc" fieldname="CREATED_DATE" id="txtFilter"/>
		 <input type="hidden" name="txtFilter" order="desc" fieldname="STATUS" id="txtFilter"/>
		 <input type="hidden" name="txtFilter" order="desc" fieldname="PROJECTS_TYPE" id="txtFilter"/>
		 <input type="hidden" name="txtFilter" order="desc" fieldname="PROJECTS_XINZHI" id="txtFilter"/>
         <input type="hidden" name="resultXML" id = "resultXML">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
		
 	</FORM>
 </div>
</body>
<script>
</script>
</html>