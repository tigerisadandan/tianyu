<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>安装企业首页</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/jxsb/azCompanyController.do";

//页面初始化
$(function() {
	init();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"?query",data,DT1);
	});
	//按钮绑定事件(新增)
	$("#btnInsert").click(function() {
		$(window).manhuaDialog({"title":"安装企业>新增","type":"text","content":"${pageContext.request.contextPath }/jsp/business/jxsb/az_company-add.jsp?type=insert","modal":"1"});
	});
	//按钮绑定事件(修改)
	$("#btnUpdate").click(function() {
		if($("#DT1").getSelectedRowIndex()==-1)
		 {
			requireSelectedOneRow();
		    return
		 }
		$("#resultXML").val($("#DT1").getSelectedRow());
		$(window).manhuaDialog({"title":"安装企业>修改","type":"text","content":"${pageContext.request.contextPath }/jsp/business/jxsb/az_company-add.jsp?type=update","modal":"1"});
	});
	//按钮绑定事件（导出EXCEL）
	$("#btnExpExcel").click(function() {
		 var t = $("#DT1").getTableRows();
		 if(t<=0)
		 {
			 xAlert("提示信息","请至少查询出一条记录！");
			 return;
		 }
	  	 $(window).manhuaDialog({"title":"导出","type":"text","content":"${pageContext.request.contextPath }/jsp/framework/print/TabListEXP.jsp?tabId=DT1","modal":"3"});
	});
	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#queryForm").clearFormResult();
        getNd();
    });
	
});

//页面默认参数
function init(){
	getNd();
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList(controllername+"?query",data,DT1);
}
//默认年度
function getNd(){
	//年度信息，里修改
	$("#ZFRQ").val(new Date().getFullYear());
}

//点击获取行对象
function tr_click(obj,tabListid){
	//alert(JSON.stringify(obj));
}

//回调函数--用于修改新增
getWinData = function(data){
	//var subresultmsgobj = defaultJson.dealResultJson(data);
	var data1 = defaultJson.packSaveJson(data);
	if(JSON.parse(data).ID == "" || JSON.parse(data).ID == null){
		defaultJson.doInsertJson(controllername + "?insert", data1,DT1);
	}else{
		defaultJson.doUpdateJson(controllername + "?update", data1,DT1);
	}
	
};

//详细信息
function rowView(index){
	$("#DT1").setSelect(index);
	if($("#DT1").getSelectedRowIndex()==-1)
	 {
		requireSelectedOneRow();
	    return
	 }
	$("#resultXML").val($("#DT1").getSelectedRow());
	$(window).manhuaDialog({"title":"安装企业>详细信息","type":"text","content":"${pageContext.request.contextPath }/jsp/business/jxsb/az_company-add.jsp?type=detail","modal":"1"});
}
</script>
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
	<p></p>
	<div class="row-fluid">
		<div class="B-small-from-table-autoConcise">
			<h4 class="title">
				安装企业
				<span class="pull-right">  
					<button id="btnInsert" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">新增</button>
      				<button id="btnUpdate" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">修改</button>
      				<button id="btnExpExcel" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">导出</button>
				</span>
			</h4>
			<form method="post" id="queryForm">
				<table class="B-table" width="100%">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<TR style="display: none;">
						<TD class="right-border bottom-border"></TD>
						<TD class="right-border bottom-border">
							<INPUT type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>
						</TD>
					</TR>
					<!--可以再此处加入hidden域作为过滤条件 -->
					<tr>
						<td width="7%" class="right-border bottom-border">
							<select class="span12" id="QND" name="ND" fieldname="ND" operation="=" kind="dic" src="XMNF"  defaultMemo="-年度-">
						</td>
						<th width="5%" class="right-border bottom-border text-right">项目名称</th>
						<td class="right-border bottom-border" width="20%">
							<input class="span12" type="text" id="QXMMC" name="XMMC" fieldname="XMMC" operation="like" >
						</td>
						
						
			            <td class="text-left bottom-border text-right">
	                        <button id="btnQuery" class="btn btn-link"  type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-search"></i>查询</button>
           					<button id="btnClear" class="btn btn-link" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><i class="icon-trash"></i>清空</button>
			            </td>							
					</tr>
				</table>
			</form>
			<div style="height:5px;"> </div>	
			<div class="overFlowX">
	            <table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single"  pageNum="18">
	                <thead>
	                	<tr>
	                		<th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
	                		<th fieldname="ZFRQ" colindex=2 tdalign="center" maxlength="10" hasLink="true" linkFunction="rowView">&nbsp;支付日期&nbsp;</th>
							<th fieldname="AZ_COMPANY_UID" colindex=0 tdalign="center" >&nbsp;&nbsp;</th>
							<th fieldname="SHENHE_REN" colindex=1 tdalign="center" >&nbsp;审核人&nbsp;</th>
							<th fieldname="SHENHE_YIJIAN" colindex=2 tdalign="center" maxlength="30" >&nbsp;审核详细意见&nbsp;</th>
							<th fieldname="SHENHE_DATE" colindex=3 tdalign="center" >&nbsp;审核时间&nbsp;</th>
							<th fieldname="SHENHE_JIEGUO" colindex=4 tdalign="center" >&nbsp;审核结果，1：审核通过；2：审核未通过&nbsp;</th>
							<th fieldname="STATUS" colindex=5 tdalign="center" >&nbsp;状态  1：入库（最终数据）；10：审核通过；20：审核未通过；30：已提交未审核；40：未提交&nbsp;</th>
							<th fieldname="EVENT_UID" colindex=6 tdalign="center" maxlength="30" >&nbsp;事件编号&nbsp;</th>
							<th fieldname="ENABLED" colindex=7 tdalign="center" >&nbsp;是否有效&nbsp;</th>
							<th fieldname="DESCRIBE" colindex=8 tdalign="center" maxlength="30" >&nbsp;备注&nbsp;</th>
							<th fieldname="CREATED_UID" colindex=9 tdalign="center" maxlength="30" >&nbsp;创建人编号&nbsp;</th>
							<th fieldname="CREATED_NAME" colindex=10 tdalign="center" maxlength="30" >&nbsp;创建人&nbsp;</th>
							<th fieldname="CREATED_DATE" colindex=11 tdalign="center" >&nbsp;创建时间&nbsp;</th>
							<th fieldname="UPDATE_UID" colindex=12 tdalign="center" maxlength="30" >&nbsp;更新人编号&nbsp;</th>
							<th fieldname="UPDATE_NAME" colindex=13 tdalign="center" maxlength="30" >&nbsp;更新人&nbsp;</th>
							<th fieldname="UPDATE_DATE" colindex=14 tdalign="center" >&nbsp;更新时间&nbsp;</th>
							<th fieldname="SERIAL_NO" colindex=15 tdalign="center" >&nbsp;排序号&nbsp;</th>
							<th fieldname="COMPANY_NAME" colindex=16 tdalign="center" maxlength="30" >&nbsp;安装单位名称&nbsp;</th>
							<th fieldname="JIGOU_DAIMA" colindex=17 tdalign="center" >&nbsp;组织机构代码&nbsp;</th>
							<th fieldname="DL_CODE" colindex=18 tdalign="center" maxlength="30" >&nbsp;安装单位登陆代码&nbsp;</th>
							<th fieldname="PWD" colindex=19 tdalign="center" maxlength="30" >&nbsp;加密密码&nbsp;</th>
							<th fieldname="MIMA" colindex=20 tdalign="center" maxlength="30" >&nbsp;未加密密码&nbsp;</th>
							<th fieldname="YYZZ_CODE" colindex=21 tdalign="center" maxlength="30" >&nbsp;营业执照注册号&nbsp;</th>
							<th fieldname="XYSC_CODE" colindex=22 tdalign="center" maxlength="30" >&nbsp;企业信用手册编号&nbsp;</th>
							<th fieldname="ZHENGSHU_CODE" colindex=23 tdalign="center" maxlength="30" >&nbsp;资质证书编号&nbsp;</th>
							<th fieldname="ZZDJ" colindex=24 tdalign="center" maxlength="30" >&nbsp;资质等级&nbsp;</th>
							<th fieldname="AQSCXKZ" colindex=25 tdalign="center" maxlength="30" >&nbsp;安全生产许可证编号&nbsp;</th>
							<th fieldname="YXQ_BEGIN" colindex=26 tdalign="center" >&nbsp;有效期开始日期&nbsp;</th>
							<th fieldname="YXQ_END" colindex=27 tdalign="center" >&nbsp;有效期结束日期&nbsp;</th>
							<th fieldname="COMPANYS_TYPE" colindex=28 tdalign="center" maxlength="30" >&nbsp;企业类型&nbsp;</th>
							<th fieldname="JL_DATE" colindex=29 tdalign="center" >&nbsp;建立时间&nbsp;</th>
							<th fieldname="ADDRESS" colindex=30 tdalign="center" maxlength="30" >&nbsp;企业地址&nbsp;</th>
							<th fieldname="WX_ADDRESS" colindex=31 tdalign="center" maxlength="30" >&nbsp;驻无锡地址&nbsp;</th>
							<th fieldname="POSTCODE" colindex=32 tdalign="center" >&nbsp;邮政编码&nbsp;</th>
							<th fieldname="WEB" colindex=33 tdalign="center" maxlength="30" >&nbsp;企业网址&nbsp;</th>
							<th fieldname="EMAIL" colindex=34 tdalign="center" maxlength="30" >&nbsp;电子邮件&nbsp;</th>
							<th fieldname="FAREN_DAIBIAO" colindex=35 tdalign="center" maxlength="30" >&nbsp;法人代表&nbsp;</th>
							<th fieldname="DAIBIAO_PHONE" colindex=36 tdalign="center" maxlength="30" >&nbsp;法人代表电话&nbsp;</th>
							<th fieldname="COMPANY_JL" colindex=37 tdalign="center" >&nbsp;企业经理&nbsp;</th>
							<th fieldname="JL_PHONE" colindex=38 tdalign="center" maxlength="30" >&nbsp;企业经理电话/手机&nbsp;</th>
							<th fieldname="JSFZR" colindex=39 tdalign="center" >&nbsp;技术负责人&nbsp;</th>
							<th fieldname="FZR_PHONE" colindex=40 tdalign="center" maxlength="30" >&nbsp;技术负责人电话/手机&nbsp;</th>
							<th fieldname="LIANXIREN" colindex=41 tdalign="center" maxlength="30" >&nbsp;联系人&nbsp;</th>
							<th fieldname="LIANXIREN_PHONE" colindex=42 tdalign="center" maxlength="30" >&nbsp;联系电话&nbsp;</th>
							<th fieldname="LIANXIREN_MOBILE" colindex=43 tdalign="center" >&nbsp;联系人移动电话&nbsp;</th>
							<th fieldname="QY_FAX" colindex=44 tdalign="center" >&nbsp;企业传真&nbsp;</th>
							<th fieldname="QY_PHONE" colindex=45 tdalign="center" >&nbsp;企业联系电话&nbsp;</th>
	                	</tr>
	                </thead>
	              	<tbody></tbody>
	           </table>
	       </div>
	 	</div>
	</div>     
</div>
<div align="center">
	<FORM name="frmPost" method="post" style="display: none" target="_blank">
		<!--系统保留定义区域-->
		<input type="hidden" name="queryXML"/> 
		<input type="hidden" name="txtXML"/>
		<input type="hidden" name="txtFilter" order="desc" fieldname="LRSJ" id="txtFilter"/>
<%--		<input type="hidden" name="txtFilter" order="desc" fieldname="ID" id="txtFilter"/>--%>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>