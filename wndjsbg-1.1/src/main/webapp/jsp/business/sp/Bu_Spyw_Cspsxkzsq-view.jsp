<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<LINK type="text/css" rel="stylesheet" href="/wndjsbg/jsp/business/sp/css/xAlert.css"> </LINK>
<script type="text/javascript" src="/wndjsbg/js/base/jquery.js"> </script>
<script type="text/javascript" src="/wndjsbg/jsp/business/sp/js/printPageHtml.js"> </script>
<script type="text/javascript" src="/wndjsbg/js/base/LockingTable.js"> </script>
<script type="text/javascript" src="/wndjsbg/js/base/bootstrap.js"> </script>
<script type="text/javascript" src="/wndjsbg/js/base/jsBruce.js"> </script> 
<script type="text/javascript" src="/wndjsbg/js/common/Form2Json.js"> </script>
<script type="text/javascript" src="/wndjsbg/js/common/convertJson.js"> </script>
<script type="text/javascript" src="/wndjsbg/js/common/combineQuery.js"> </script>
<script type="text/javascript" src="/wndjsbg/js/common/default.js?version=20131206"> </script>
<script type="text/javascript" src="/wndjsbg/js/common/TabList.js"> </script>
<script type="text/javascript" src="/wndjsbg/js/common/bootstrap-validation.js"> </script>
<script type="text/javascript" src="/wndjsbg/js/common/loadFields.js?version=20131206"> </script>
<script type="text/javascript" src="/wndjsbg/js/My97DatePicker/WdatePicker.js"> </script>
<title></title>
<%
	String xmbh=request.getParameter("xmbh");
	String id=(String)request.getAttribute("id");
	String zt=request.getParameter("zt");
	String pch=request.getParameter("pch");
	System.out.println(zt+"      "+pch);
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
var id="<%=id%>";
var controllername= "${pageContext.request.contextPath }/sp/buSpywCspsxkzsqController/";
var controllerfile= "${pageContext.request.contextPath }/controller/fileController/";



//初始化加载
$(document).ready(function(){
$("#pubAlert").hide();
	query();
	$("input:checkbox").attr("disabled","true");
	 $("#btnClose").click(function(){
		    window.close();
		    });
		    
	 $("#btnSave").click(function(){
		    $.ajax({
			url : controllername + "download?uid="+$("#CSPSXKZSQ_UID").val(),
			cache : false,
			async : false,
			dataType : "json",
			type : 'post',
			success : function(response) {
		    	window.location.href = controllerfile+"download2?path="+response;
			 } 
		    });
		    
		    });
		    
});
//页面默认参数
function query() {
$("#YWLZ_UID").val(id);
		//查询表单赋值       
		var data = combineQuery.getQueryCombineData(queryForm,
				frmPost);
		var data1 = {
			msg : data
		};
		$.ajax({
			url : controllername + "query",
			data : data1,
			cache : false,
			async : false,
			dataType : "json",
			type : 'post',
			success : function(response) {
				var res = dealSpecialCharactor(response.msg);
				$("#resultXML").val(response.msg);
				//alert($("#resultXML").val());
				var resultobj = defaultJson.dealResultJson(res);
				$("#buSpywJsxmkzsjpssqForm").setHtmlValue(resultobj);
				$("#buSpywJsxmkzsjpssqForm").setFormValues(resultobj);
				checkbox(resultobj.SFCLML);
                if(resultobj.NYQDX_QT!=""){
                    $("input:checkbox[name='other']").each(function(){
                    $(this).attr("checked","checked");
                    })
               }	
			} 
		});

	}
	
	function checkbox(NYQDX){
    var a=NYQDX;
	$("input:checkbox[name='box']").each(function(){
		var flag = false;
		for(i=0;i<a.split(",").length;i++){
		if(a.split(",")[i] == $(this).val()){
				$(this).attr("checked","checked");
				} 
		}
	})
}
function checkbox(NYQDX){
    var a=NYQDX;
	$("input:checkbox[name='box']").each(function(){
		var flag = false;
		for(i=0;i<a.split(",").length;i++){
		if(a.split(",")[i] == $(this).val()){
				$(this).attr("checked","checked");
				} 
		}
	})
}
</script>    
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
	</p>	
	<div class="row-fluid">
    	<div style="height:5px;"></div>
	<div class="B-small-from-table-autoConcise">
	   <span style="float:right">
      		 <button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
	  		<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">打印</button>
      	</span><br/>
      
     <form method="post" id="buSpywJsxmkzsjpssqForm"  >   
      <h4 class="title" align="center">城市排水许可申请表</h4> 	 	
    <div align="center">  
    <INPUT type="hidden" id="CSPSXKZSQ_UID" fieldname="CSPSXKZSQ_UID" />
    <table class="AQZJBG-table">
  <tr height="40px;">
    <td width="78" align="center">申请单位</td>
    <td colspan="12" fieldname="DWMC">
       
    </td>
  </tr>
  <tr>
    <td align="center">项目名称</td>
    <td colspan="12" fieldname="PROJECT_NAME"></td>
  </tr>
  <tr>
    <td align="center">详细地址</td>
    <td colspan="12" fieldname="ADDRESS"></td>
  </tr>
  <tr>
    <td align="center">法人代表</td>
    <td colspan="2" fieldname="FDDBR"> </td>
    <td width="44" align="center">联系电话</td>
    <td colspan="3" fieldname="FDDBR_LXDH"> </td>
    <td width="26" align="center">手机</td>
    <td colspan="2" fieldname="FDDBR_MOBILE"> </td>
    <td colspan="2" align="center">传真号</td>
    <td width="78" fieldname="FDDBR_FAX"> </td>
  </tr>
  <tr>
    <td align="center">联系人</td>
    <td colspan="2" fieldname="LXR"> </td>
    <td align="center">联系电话</td>
    <td colspan="3" fieldname="LXR_LXDH"> </td>
    <td align="center">手机</td>
    <td colspan="2" fieldname="LXR_MOBILE"> </td>
    <td colspan="2" align="center">传真号</td>
    <td fieldname="LXR_FAX"></td>
  </tr>
  <tr>
    <td colspan="2" align="center">经营项目(或产品)</td>
    <td colspan="8" fieldname="JYXM"></td>
    <td colspan="2" align="center">单位人数</td>
    <td fieldname="DWRS"></td>
  </tr>
  <tr>
    <td colspan="2" align="center">用水量(吨/天)</td>
    <td colspan="5" fieldname="YSL"></td>
    <td colspan="3" align="center">排水量(吨/天)</td>
    <td colspan="3" fieldname="PSL"></td>
  </tr>
  <tr>
    <td colspan="2" align="center">工业污水(吨/天)</td>
    <td colspan="5" fieldname="GYWS"></td>
    <td colspan="3" align="center">生活污水(吨/天)</td>
    <td colspan="3" fieldname="SHWS"></td>
  </tr>
  <tr>
    <td colspan="2" align="center">污水管长度(米)</td>
    <td colspan="5" fieldname="WSGCD"></td>
    <td colspan="3" align="center">雨水管长度(米)</td>
    <td colspan="3" fieldname="YSGCD"></td>
  </tr>
  <tr>
    <td colspan="2" align="center">污水口数量(个)</td>
    <td colspan="5" fieldname="WSKSL"></td>
    <td colspan="3" align="center">雨水口数量(个)</td>
    <td colspan="3" fieldname="YSKSL"></td>
  </tr>
  <tr>
    <td colspan="2" align="center">主要污染物</td>
    <td colspan="11" fieldname="ZYWRW"></td>
  </tr>
  <tr>
    <td colspan="2" align="center">污水处理能力</td>
    <td colspan="3" fieldname="WSCLNL"></td>
    <td width="82" align="center">处理工艺</td>
    <td colspan="3" fieldname="WSCLGY"></td>
    <td colspan="2" align="center">处理效果</td>
    <td colspan="2" fieldname="WSCLXG"></td>
  </tr>
  <tr>
    <td colspan="2" align="center">内部排水检测机构<br />
    (有、无)</td>
    <td colspan="3" fieldname="NBPSJCJG"></td>
    <td>检测机构<br />
    负责人</td>
    <td colspan="3" fieldname="JCJG_FZR"></td>
    <td colspan="2" align="center">联系电话</td>
    <td colspan="2" fieldname="JCJG_FZR_LXDH"></td>
  </tr>
  <tr style="height: 200px;">
    <td align="center" >
                  所<br/>
                  附<br/>
                  材<br/>
                  料<br/>
                  目<br/>
                  录<br/>          
    </td>
    <td colspan="12" style="font-size: 15px;padding-left: 0px;">
     
<ul style="padding-left: 0px;height:5px;list-style-type:none;" align="left">&nbsp;1.污水、雨水接管申请批准书<li style="float: right;" style="float: right;" style="float: right;" style="float: right;"><input  type="checkbox" name="box" value="1"/>&nbsp;&nbsp;</li></ul>
<ul style="padding-left: 0px;height:5px;list-style-type:none;" align="left">&nbsp;2.环评批复(环境影响评价表)<li style="float: right;" style="float: right;">	<input type="checkbox" name="box" value="2"/>&nbsp;&nbsp;</li></ul>
<ul style="padding-left: 0px;height:5px;list-style-type:none;" align="left">&nbsp;3.水质监测报告(三个月内)	<li style="float: right;" style="float: right;"><input type="checkbox" name="box" value="3"/>&nbsp;&nbsp;</li></ul>
<ul style="padding-left: 0px;height:5px;list-style-type:none;" align="left">&nbsp;4.近三个月自来水发票复印件	<li style="float: right;" style="float: right;"><input type="checkbox" name="box" value="4"/>&nbsp;&nbsp;</li></ul>
<ul style="padding-left: 0px;height:5px;list-style-type:none;" align="left">&nbsp;5*.与高新水务公司签订的污水接纳协议	<li style="float: right;" style="float: right;"><input type="checkbox" name="box" value="5"/>&nbsp;&nbsp;</li></ul>
<ul style="padding-left: 0px;height:5px;list-style-type:none;" align="left">&nbsp;6*.污水处理工艺和运行资料	<li style="float: right;" style="float: right;"><input type="checkbox" name="box" value="6"/>&nbsp;&nbsp;</li></ul>
<ul style="padding-left: 0px;height:5px;list-style-type:none;" align="left">&nbsp;7*.污水排放口安装对水量、PH.CODcr(或TOC)进行的在线	<li style="float: right;" style="float: right;"><input type="checkbox" name="box" value="7"/>&nbsp;&nbsp;</li></ul>
<ul style="padding-left: 0px;height:5px;list-style-type:none;" align="left">&nbsp;&nbsp;&nbsp;&nbsp;监测装置资料和内部水质检测制度	<li style="float: right;" style="float: right;"></li></ul>
<ul style="padding-left: 0px;height:5px;list-style-type:none;" align="left">&nbsp;注：5*为工业企业需提交；6*为有污水处理设施的企业需提交；		</ul>
<ul style="padding-left: 0px;height:20px;list-style-type:none;" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7*为有相关在线监测装置的企业需提交		</ul>

      </td>
  </tr>
  <tr>
    <td align="center" >
                 申<br/>
                  请<br/>
                  人<br/>
                  签<br/> 	
                  章<br/>   
    </td>
    <td colspan="12">
       <div align="left" height="30" ><br/>&nbsp;本申请人对上述情况及所提供材料的真实性负责。</div>
      <div align="right" >
          <br/><br/><br/><br/>
          法人代表签字__________________&nbsp;&nbsp;&nbsp;&nbsp;(签章)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               &nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;
               <br/>&nbsp;
        </div> 
    </td>
  </tr>
  <tr  align="center" style="height: 80px;" >
    <td>
                  备<br/>
                  注<br/>
    </td>
    <td colspan="12">&nbsp;</td>
  </tr>
  <tr style="visibility:hidden">
    <td>&nbsp;</td>
    <td width="70">&nbsp;</td>
    <td width="27">&nbsp;</td>
    <td>&nbsp;</td>
    <td width="14">&nbsp;</td>
    <td>&nbsp;</td>
    <td width="13">&nbsp;</td>
    <td>&nbsp;</td>
    <td width="62">&nbsp;</td>
    <td width="40">&nbsp;</td>
    <td width="33">&nbsp;</td>
    <td width="25">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
      </div>
      	</form>
    </div>
  </div>
</div>
<div align="center">
	<FORM name="frmPost" method="post" style="display:none" target="_blank" id="frmPost">
		<!--系统保留定义区域-->
		<input type="hidden" name="queryXML" id="queryXML"/>
		<input type="hidden" name="txtXML" id="txtXML"/>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<input type="hidden" id="queryResult"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>		
	</FORM>
</div>
<form method="post" id="queryForm">
	<!--可以再此处加入hidden域作为过滤条件 -->
	<TR style="display: none;">
		<TD class="right-border bottom-border"></TD>
		<TD class="right-border bottom-border">
			<INPUT type="hidden" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>
			<INPUT type="hidden" class="span12"  id="YWLZ_UID" fieldname="YWLZ_UID" 
								operation="=" value="0" />
		</TD>
	</TR>	
</form>
</body>
</html>