<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>机械设备使用登记-维护</title>
<%
	String type=request.getParameter("type");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/jxsb/jxsbSydjController.do";
var type ="<%=type%>";
//页面初始化
$(function() {
	init();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,jxsbSydjList);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"?query",data,jxsbSydjList);
	});
	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
		if($("#jxsbSydjForm").validationButton())
		{
		    //生成json串
		    var data = Form2Json.formToJSON(jxsbSydjForm);
		  //组成保存json串格式
		    var data1 = defaultJson.packSaveJson(data);
		  //调用ajax插入
		    if($("#ID").val() == "" || $("#ID").val() == null){
    			defaultJson.doInsertJson(controllername + "?insert", data1,jxsbSydjList);
    			$("#jxsbSydjForm").clearFormResult();
    		}else{
    			defaultJson.doUpdateJson(controllername + "?insert", data1,jxsbSydjList);
    		}
		}else{
			requireFormMsg();
		  	return;
		}
	});
	
	//按钮绑定事件（新增）
    $("#btnClear_Bins").click(function() {
        $("#jxsbSydjForm").clearFormResult();
        $("#jxsbSydjForm").cancleSelected();
        
        
        $("#ZFRQ").val(new Date().toLocaleDateString());
        $("#ZFJE").val(0);
        $("#ID").val("");
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
	<%
		}
	%>
	
});
//页面默认参数
function init(){
	getNd();
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,jxsbSydjList);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?query",data,jxsbSydjList);
	
<%--	if(type == "insert"){--%>
<%--	}else if(type == "update" || type == "detail"){--%>
<%--		var tempJson;--%>
<%--		if(navigator.userAgent.indexOf("Firefox")>0) {--%>
<%--			var rowValue = $(parent.frames["menuiframe"]).contents().find("#resultXML").val();--%>
<%--			tempJson = eval("("+rowValue+")");--%>
<%--		}else{--%>
<%--			var rowValue = $(parent.frames["menuiframe"].document).find("#resultXML").val();--%>
<%--			tempJson = eval("("+rowValue+")");--%>
<%--		}--%>
<%--		//表单赋值--%>
<%--		$("#$("#jxsbSydjForm")").setFormValues(tempJson);--%>
<%--	}--%>
}


//点击行事件
function tr_click(obj){
	//alert(JSON.stringify(obj));
	$("#jxsbSydjForm").setFormValues(obj);
}

//默认年度
function getNd(){
	$("#QZFRQ").val(new Date().getFullYear());
}
//选中项目名称弹出页
function selectXm(){
	$(window).manhuaDialog({"title":"","type":"text","content":"${pageContext.request.contextPath }/jsp/business/zjgl/xmcx.jsp","modal":"2"});
}
//弹出区域回调
getWinData = function(data){
	$("#XMMC").val(JSON.parse(data).XMMC);
	$("#XMBH").val(JSON.parse(data).XMBH);
	$("#GC_TCJH_XMXDK_ID").val(JSON.parse(data).GC_TCJH_XMXDK_ID);
};

//详细信息
function rowView(index){
	var obj = $("#jxsbSydjList").getSelectedRowJsonByIndex(index);
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
	        <TD class="right-border bottom-border"></TD>
			<TD class="right-border bottom-border">
			</TD>
        </TR>
         <tr>
        	<th width="5%" class="right-border bottom-border text-right">年度</th>
	        <td class="right-border bottom-border" width="10%">
	            <select class="span12" type="date" fieldtype="year" fieldformat="yyyy" id="QZFRQ" name="ZFRQ" fieldname="ZFRQ" operation="=" kind="dic" src="XMNF"  defaultMemo="-年度-">
	        </td>
	        <th width="5%" class="right-border bottom-border text-right">项目名称</th>
			<td class="right-border bottom-border" width="20%">
				<input class="span12" type="text" name = "QXMMC" fieldname = "gtx.XMMC" operation="like" >
			</td>
			<td class="text-left bottom-border text-right">
	        	<button id="btnQuery" class="btn btn-link"  type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-search"></i>查询</button>
	        	<button id="btnClear" class="btn btn-link" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-trash"></i>清空</button>
	        </td>
		</tr>
      </table>
      </form>
    <div style="height:5px;"></div>
    <div class="overFlowX">
	<table class="table-hover table-activeTd B-table" id="jxsbSydjList" width="100%" type="single" pageNum="5">
		<thead>
			<tr>
 			    <th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
				<th fieldname="JXSB_SYDJ_UID" colindex=0 tdalign="center" >&nbsp;&nbsp;</th>
				<th fieldname="JXSB_SYGL_UID" colindex=1 tdalign="center" >&nbsp;&nbsp;</th>
				<th fieldname="SY_COUNTS" colindex=2 tdalign="center" >&nbsp;使用次数&nbsp;</th>
				<th fieldname="AZ_COMPANY_UID" colindex=3 tdalign="center" >&nbsp;维保单位UID&nbsp;</th>
				<th fieldname="AZ_DANWEI" colindex=4 tdalign="center" maxlength="30" >&nbsp;维保单位名称&nbsp;</th>
				<th fieldname="ZZBH" colindex=5 tdalign="center" maxlength="30" >&nbsp;资质证书编号&nbsp;</th>
				<th fieldname="AQSCBH" colindex=6 tdalign="center" maxlength="30" >&nbsp;安全生产许可证编号&nbsp;</th>
				<th fieldname="SBAZ_DATE" colindex=7 tdalign="center" >&nbsp;设备安全日期（来源于安装过程的安装结束日期）&nbsp;</th>
				<th fieldname="YS_DATE" colindex=8 tdalign="center" >&nbsp;安装验收日期（来源于安装告知的安装验收日期）&nbsp;</th>
				<th fieldname="BGQF_DATE" colindex=9 tdalign="center" >&nbsp;报告签发日期（来源于检测验收的报告签发日期）&nbsp;</th>
				<th fieldname="JC_DW" colindex=10 tdalign="center" maxlength="30" >&nbsp;安装检测单位（来源于检测验收的检测单位）&nbsp;</th>
				<th fieldname="SYDJ_BH" colindex=11 tdalign="center" maxlength="30" >&nbsp;使用登记证编号&nbsp;</th>
				<th fieldname="JL_SHENHE_YJ" colindex=12 tdalign="center" maxlength="30" >&nbsp;监理单位审核意见&nbsp;</th>
				<th fieldname="SHENHE_BY" colindex=13 tdalign="center" >&nbsp;监理审核人&nbsp;</th>
				<th fieldname="SHENHE_DATE" colindex=14 tdalign="center" >&nbsp;监理审核日期&nbsp;</th>
				<th fieldname="XZSH_REN" colindex=15 tdalign="center" >&nbsp;小组审核人&nbsp;</th>
				<th fieldname="XZSH_YIJIAN" colindex=16 tdalign="center" maxlength="30" >&nbsp;小组审核意见&nbsp;</th>
				<th fieldname="XZSH_DATE" colindex=17 tdalign="center" >&nbsp;小组审核时间&nbsp;</th>
				<th fieldname="JBRSH_REN" colindex=18 tdalign="center" >&nbsp;经办人审核人&nbsp;</th>
				<th fieldname="JBRSH_YIJIAN" colindex=19 tdalign="center" maxlength="30" >&nbsp;经办人审核意见&nbsp;</th>
				<th fieldname="JBRSH_DATE" colindex=20 tdalign="center" >&nbsp;经办人审核时间&nbsp;</th>
				<th fieldname="SHOULI_BY" colindex=21 tdalign="center" >&nbsp;内部受理人&nbsp;</th>
				<th fieldname="GZ_SHOULI_YJ" colindex=22 tdalign="center" maxlength="30" >&nbsp;告知受理意见/登记部门意见&nbsp;</th>
				<th fieldname="SHOULI_DATE" colindex=23 tdalign="center" >&nbsp;受理日期&nbsp;</th>
				<th fieldname="EVENT_UID" colindex=24 tdalign="center" maxlength="30" >&nbsp;事件编号&nbsp;</th>
				<th fieldname="ENABLED" colindex=25 tdalign="center" >&nbsp;是否有效&nbsp;</th>
				<th fieldname="DESCRIBE" colindex=26 tdalign="center" maxlength="30" >&nbsp;备注&nbsp;</th>
				<th fieldname="CREATED_UID" colindex=27 tdalign="center" maxlength="30" >&nbsp;创建人编号&nbsp;</th>
				<th fieldname="CREATED_NAME" colindex=28 tdalign="center" maxlength="30" >&nbsp;创建人&nbsp;</th>
				<th fieldname="CREATED_DATE" colindex=29 tdalign="center" >&nbsp;创建时间&nbsp;</th>
				<th fieldname="UPDATE_UID" colindex=30 tdalign="center" maxlength="30" >&nbsp;更新人编号&nbsp;</th>
				<th fieldname="UPDATE_NAME" colindex=31 tdalign="center" maxlength="30" >&nbsp;更新人&nbsp;</th>
				<th fieldname="UPDATE_DATE" colindex=32 tdalign="center" >&nbsp;更新时间&nbsp;</th>
				<th fieldname="SERIAL_NO" colindex=33 tdalign="center" >&nbsp;排序号&nbsp;</th>
             </tr>
		</thead>
		<tbody>
           </tbody>
	</table>
	</div>
	</div>
	<div style="height:5px;"></div>
	<div class="B-small-from-table-autoConcise">
      <h4 class="title">机械设备使用登记
      	<span class="pull-right">
      		<button id="btnClear_Bins" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">清空</button>
	  		<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">保存</button>
      	</span>
      </h4>
     <form method="post" id="jxsbSydjForm"  >
      <table class="B-table" width="100%" >
      <input type="hidden" id="ID" fieldname="ID" name = "ID"/></TD>
	  	<input type="hidden" id="GC_TCJH_XMXDK_ID" fieldname="XMID" name = "XMID"/></TD>
        <tr>
			<th width="8%" class="right-border bottom-border text-right">项目名称</th>
       	 	<td class="bottom-border right-border" width="23%">
         		<input class="span12" style="width:85%" id="XMMC" type="text" placeholder="必填" check-type="required" fieldname="XMMC" name = "XMMC"  disabled />
          		<button class="btn btn-link"  type="button" onclick="selectXm()"><i class="icon-edit"></i></button>
       	 	</td>
         	<th width="8%" class="right-border bottom-border text-right">项目编号</th>
       		<td class="bottom-border right-border"width="15%">
         		<input class="span12" style="width:100%" id="XMBH" type="text" fieldname="XMBH" name = "XMBH"  disabled/>
         	</td>
        </tr>
        <tr>
			<th width="8%" class="right-border bottom-border text-right">支付金额</th>
			<td width="17%" class="right-border bottom-border">
				<input id="ZFJE" class="span12" keep="true" placeholder="必填" check-type="required" check-type="maxlength" maxlength="17" value=0 style="width:70%;text-align:right;" name="ZFJE" fieldname="ZFJE" type="number" />&nbsp;<b>(元)</b>
			</td>
			<th width="8%" class="right-border bottom-border text-right">支付日期</th>
			<td width="17%" class="right-border bottom-border">
				<input id="ZFRQ" class="span12" placeholder="必填" check-type="required" check-type="maxlength" maxlength="10" name="ZFRQ" fieldname="ZFRQ" type="text" />
			</td>
		</tr>
		<tr>
			<th width="8%" class="right-border bottom-border text-right">联系人</th>
			<td width="17%" class="right-border bottom-border">
				<input id="LXR" class="span12" placeholder="必填" check-type="required" check-type="maxlength" maxlength="36" name="LXR" fieldname="LXR" type="text" />
			</td>
			<th width="8%" class="right-border bottom-border text-right">联系方式</th>
			<td width="17%" class="right-border bottom-border">
				<input id="LXFS" class="span12" placeholder="必填" check-type="required" check-type="maxlength" maxlength="40" name="LXFS" fieldname="LXFS" type="text" />
			</td>
		</tr>
        <tr>
	        <th width="8%" class="right-border bottom-border text-right">备注</th>
	        <td colspan="3" class="bottom-border right-border" >
	        	<textarea class="span12" rows="2" id="BZ" check-type="maxlength" maxlength="4000" fieldname="BZ" name="BZ"></textarea>
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
         <input type="hidden" name="txtFilter"  order="desc" fieldname = "t.LRSJ" id = "txtFilter">
         <input type="hidden" name="resultXML" id = "resultXML">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
		
 	</FORM>
 </div>
</body>
<script>
</script>
</html>