<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>施工内容-维护</title>
<%
	String type=request.getParameter("type");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/gongcheng/projectsGongchengController";
var type ="<%=type%>";
//页面初始化
$(function() {
	init();
	$("#dis input").attr("disabled","disabled");
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,projectsGongchengList);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"?query",data,projectsGongchengList);
	});
	$("#btnClose").bind("click", function(){
		var a=$(this).manhuaDialog.close();
	});
	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
	var msg="";
	if($("input:radio[name='STATUS']:checked").val()==20){
	msg="确认审核不通过？";
	}else{
	msg="确认审核通过？";
	}
	  if(confirm(msg)){
		//生成json串
		    var data = Form2Json.formToJSON(projectsGongchengForm);
		  //组成保存json串格式
		    var data1 = defaultJson.packSaveJson(data);
		  //调用ajax插入
		    if($("#GONGCHENG_UID").val() == "" || $("#GONGCHENG_UID").val() == null){
    			defaultJson.doInsertJson(controllername + "?insert", data1);
    			$("#projectsForm").clearFormResult();
    		}else{

    			if($("input:radio[name='STATUS']:checked").val()==20){
   				 var reason = $("#SHENHE_YIJIAN").val();
   				 if(reason==""){
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
	
	
	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#queryForm").clearFormResult();
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
		$("#GONGCHENG_UID").val(tempJson.GONGCHENG_UID);
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
				$("#projectsGongchengForm").setFormValues(resultobj);		
				$("#SHENHE_NAME").val(resultobj.SHENHE_NAME);
				return true;
			}
		});
	} else {
		var parentmain = $(window).manhuaDialog.getParentObj();
		var rowValue = parentmain.$("#resultXML").val();
		var tempJson = convertJson.string2json1(rowValue);
		$("#GONGCHENG_UID").val(tempJson.GONGCHENG_UID);
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
				resultobj.STATUS="10";//默认通过
				$("#projectsGongchengForm").setFormValues(resultobj);			
				$("#xmfq").html("&nbsp;<span><a href=\"#\" title=\"查看项目分期信息\" onclick=\"selectXm("+resultobj.PROJECTS_UID+")\" >"+resultobj.PROJECTS_NAME+"</a></span>");	
				return true;
			}
		});
	}
	unitsInit();
}

function unitsInit(){
var gcid=$("#GONGCHENG_UID").val();
var createdUid=$("#CREATED_UID").val();
var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList("${pageContext.request.contextPath }/units/projectsUnitsController?querybygcid&gcid="+gcid+"&type=n"+"&cUid="+createdUid,data,DT1);
}
/*
function sum(){
var sumhtml = "";
<tr ><th align="center"><strong>合计</strong></th><td align="center" class="tableActive"></td><td align="center" class="tableActive"></td><td align="center" class="tableActive"></td><td align="center" class="tableActive"></td><td align="center" class="tableActive"></td>
  <td align="center" class="tableActive">13212312311323123.65</td>
  <td align="center" class="tableActive"></td>
 </tr>
}
*/

//点击行事件
function tr_click(obj){
	//alert(JSON.stringify(obj));
	$("#projectsGongchengForm").setFormValues(obj);
}

//默认年度
function getNd(){
	$("#QZFRQ").val(new Date().getFullYear());	
}
//选中项目名称弹出页
function selectXm(id){
	$(window).manhuaDialog({"title":"项目分期信息","type":"text","content":"${pageContext.request.contextPath }/jsp/business/gongcheng/projects-view.jsp?PRIJECTS_UID="+id,"modal":"2"});
}
//弹出区域回调
getWinData = function(data){
	$("#XMMC").val(JSON.parse(data).XMMC);
	$("#XMBH").val(JSON.parse(data).XMBH);
	$("#GC_TCJH_XMXDK_ID").val(JSON.parse(data).GC_TCJH_XMXDK_ID);
};

//详细信息
function rowView(index){
	var obj = $("#projectsGongchengList").getSelectedRowJsonByIndex(index);
	var xmbh = eval("("+obj+")").XMBH;
	$(window).manhuaDialog(xmscUrl(xmbh));
}
</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">
    <div class="B-small-from-table-autoConcise">
     <form method="post" id="queryForm"  >
      <table class="B-table" width="100%">
      <!--可以再此处加入hidden域作为过滤条件 -->
      	<TR  style="display:none;">
	        <TD class="right-border bottom-border">
	        <input class="span12" type="hidden" id="GONGCHENG_UID" name = "GONGCHENG_UID" fieldname = "GONGCHENG_UID" operation="=" >
	        </TD>
			<TD class="right-border bottom-border">
			</TD>
        </TR>
			
			
      </table>
      </form>
    <div style="height:5px;"></div>
   
	</div>
	<div style="height:5px;"></div>
	<div class="B-small-from-table-autoConcise">
      <h4 class="title">施工内容
      	<span class="pull-right">
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
      		<%if (!type.equals("detail")) {%>
	  		<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">审核</button>
	  		<%} %>
      	</span>
      </h4>
     <form method="post" id="projectsGongchengForm"  >
      <table class="B-table" width="100%" >
	  	<input type="hidden" id="GONGCHENG_UID2" fieldname="GONGCHENG_UID" name = "GONGCHENG_UID"/></TD>
	  	<input type="hidden" id="PROJECTS_CODE" fieldname="PROJECTS_CODE" name = "PROJECTS_CODE"/></TD><!-- 分期code -->
	  	<input type="hidden" id="PROJECTS_UID" fieldname="PROJECTS_UID" name = "PROJECTS_UID"/></TD>
	  	<input type="hidden" id="CREATED_UID" fieldname="CREATED_UID" name ="CREATED_UID"/>
        <tr>
				<th width="15%" class="right-border bottom-border text-right">审核结果选择</th>
				<td class="right-border bottom-border">
					<input class="span12" style="width:94%" id="STATUS" type="radio" check-type="required" fieldname="STATUS" name = "STATUS" kind="dic" src="SHYJ4_SY" />		
				</td>
				<th  id="shrsjdiv" width="15%" class="right-border bottom-border text-right">审核人和时间</th>
				<td  id="shrsj1div" class="right-border bottom-border">
					<input class="span12" style="width:30%"  type="text" id="SHENHE_NAME" name = "SHENHE_NAME"/>
	         		-
	         		<input class="span12" style="width:55%"  type="text" fieldname="SHENHE_DATE" name = "SHENHE_DATE"/>         		
				</td> 
			</tr>
			<tr>
				<th width="15%" class="right-border bottom-border text-right">审核详细意见</th>
	       	 	<td class="bottom-border right-border" colspan="3">
	         		<textarea class="span12" rows="2" id="SHENHE_YIJIAN" check-type="required" maxlength="4000"  fieldname="SHENHE_YIJIAN" name="SHENHE_YIJIAN"></textarea>
	       	 	</td>
	        </tr>
        
        
        <tr>
			
         	<th width="8%" class="right-border bottom-border text-right">工程名称</th>
       		<td class="bottom-border right-border"width="15%">
         		<input class="span12" style="width:100%" id="GONGCHENG_NAME" type="text" fieldname="GONGCHENG_NAME" name = "GONGCHENG_NAME"  disabled/>
         	
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">项目分期名称</th>
       		<td class="bottom-border right-border"width="15%" id="xmfq">
         		
         	</td>
        </tr>
        <tr>
			<th width="8%" class="right-border bottom-border text-right">工程承包性质</th>
			<td width="17%" class="right-border bottom-border" id="dis">
				<input class="span12" style="width:94%" id="CB_XINGZHI" type="radio" check-type="required" fieldname="CB_XINGZHI" disabled name = "CB_XINGZHI" kind="dic" src="SG_CBXZ" />
			</td>
			<th width="8%" class="right-border bottom-border text-right">发包方式</th>
			<td width="17%" class="right-border bottom-border">
				<select class="span12" style="width:85%" kind="dic" src="SG_FBFS" name="BID_TYPE" id="BID_TYPE" disabled check-type="required" fieldname="BID_TYPE"></select>
					<!--  <button class="btn btn-link"  type="button" onclick="selectXm()"><i class="icon-edit"></i></button>-->
			</td>
		</tr>
		<tr>
		<th width="8%" class="right-border bottom-border text-right">工程施工内容</th>
       	 	<td class="bottom-border right-border" width="23%">
       	 	    <select class="span12" style="width:85%"  name="NEIRONG" id="NEIRONG" check-type="required"  disabled fieldname="NEIRONG">
       	 	      <option value="">请选择</option>
       	 	      <option value="土建">土建</option>
       	 	      <option value="桩基">桩基</option>
       	 	      <option value="消防">消防</option></select>
          		<!-- <button class="btn btn-link"  type="button" onclick="selectXm()"><i class="icon-edit"></i></button> -->
       	 	</td>
			<th width="8%" class="right-border bottom-border text-right">建筑面积（平方米）</th>
			<td width="17%" class="right-border bottom-border">
				<input id="MIANJI" class="span12"  check-type="maxlength" maxlength="36" name="MIANJI" disabled fieldname="MIANJI" type="text" />
			</td>
			
		</tr>
		<tr>
			<th width="8%" class="right-border bottom-border text-right">高度（米）</th>
			<td width="17%" class="right-border bottom-border">
				<input id="GAODU" class="span12"  check-type="maxlength" maxlength="36" name="GAODU" disabled fieldname="GAODU" type="text" />
			</td>
			<th width="8%" class="right-border bottom-border text-right">跨度（米）</th>
			<td width="17%" class="right-border bottom-border">
				<input id="KUADU" class="span12"  check-type="maxlength" maxlength="40" name="KUADU" disabled fieldname="KUADU" type="text" />
			</td>
		</tr>
		<tr>
			<th width="8%" class="right-border bottom-border text-right">深度（米）</th>
			<td width="17%" class="right-border bottom-border">
				<input id="SHENDU" class="span12"  check-type="maxlength" maxlength="36" name="SHENDU" disabled fieldname="SHENDU" type="text" />
			</td>
			<th width="8%" class="right-border bottom-border text-right">合同额（元）</th>
			<td width="17%" class="right-border bottom-border">
				<input id="JINE" class="span12"  check-type="maxlength" maxlength="40" name="JINE" disabled fieldname="JINE" type="text" />
			</td>
		</tr>
        <tr>
	        <th width="8%" class="right-border bottom-border text-right">重量（吨）</th>
	        <td width="17%" class="right-border bottom-border" >
	        <input id="ZHONGLIANG" class="span12"  check-type="maxlength" maxlength="40" name="ZHONGLIANG" disabled fieldname="ZHONGLIANG" type="text" />
	        </td>
	        <th width="8%" class="right-border bottom-border text-right">层数</th>
			<td width="17%" class="right-border bottom-border">
				<input id="CENGSHU" class="span12"  check-type="maxlength" maxlength="40" name="CENGSHU" disabled fieldname="CENGSHU" type="text" />
			</td>
        </tr>
        <tr>
			<th width="8%" class="right-border bottom-border text-right">是否市政工程</th>
			<td width="17%" class="right-border bottom-border" id="dis">
				<input class="span12" style="width:94%" id="CB_XINGZHI" type="radio" check-type="required" fieldname="SFSZGC" disabled name = "SFSZGC" kind="dic" src="SF" />
			</td>
			 <th width="8%" class="right-border bottom-border text-right">预计开工日期</th>
			<td width="17%" class="right-border bottom-border">
				<input id="CENGSHU" class="span12"  check-type="maxlength" maxlength="40" name="KG_DATE" disabled fieldname="KG_DATE" type="text" />
			</td>
		</tr>
		 <tr>
	        <th width="8%" class="right-border bottom-border text-right">预计完工日期</th>
	        <td width="17%" class="right-border bottom-border" >
	        <input id="ZHONGLIANG" class="span12"  check-type="maxlength" maxlength="40" name="WG_DATE" disabled fieldname="WG_DATE" type="text" />
	        </td>
	        <th width="8%" class="right-border bottom-border text-right">预计竣工日期</th>
			<td width="17%" class="right-border bottom-border">
				<input id="CENGSHU" class="span12"  check-type="maxlength" maxlength="40" name="JG_DATE" disabled fieldname="JG_DATE" type="text" />
			</td>
        </tr>
      </table>
      </form>
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
							<th fieldname="UNITS_TYPE" colindex=14 tdalign="center" maxlength="30" >&nbsp;建筑物类型&nbsp;</th>
							<th fieldname="JGLX" colindex=15 tdalign="center" maxlength="30" >&nbsp;结构类型&nbsp;</th>
							<th fieldname="DJLX" colindex=16 tdalign="center" maxlength="30" >&nbsp;地基类型&nbsp;</th>
							<th fieldname="JCLX" colindex=17 tdalign="center" maxlength="30" >&nbsp;基础类型&nbsp;</th>
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
	</div>
    </div>
    
     
	
   </div>
  </div>
  <div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id = "queryXML">
         <input type="hidden" name="txtXML" id = "txtXML">
        <!--  <input type="hidden" name="txtFilter"  order="desc" fieldname = "t.LRSJ" id = "txtFilter"> -->
         <input type="hidden" name="resultXML" id = "resultXML">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
		
 	</FORM>
 </div>
</body>
<script>
</script>
</html>