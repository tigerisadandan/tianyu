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
<script type="text/javascript" src="${pageContext.request.contextPath }/jsp/business/sp/js/printPageHtml.js"> </script>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/sp/css/bootstrap.css?version=20131201"></LINK>


<title></title>
<%
	String gcid=request.getParameter("gcid");
    String id=(String)request.getAttribute("id");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/sgsx/buSpywJsxmkqcthbaController/";
var controllerfile= "${pageContext.request.contextPath }/controller/fileController/";
var contextPath="${pageContext.request.contextPath }";
var type ="insert";
var id="<%=id%>";
var gcid="<%=gcid%>";
var controllerfile= "${pageContext.request.contextPath }/controller/fileController/";
//页面初始化
$(function() {
	 $("#pubAlert").hide();
	 init();
	 $("#btnClose").click(function(){
		    window.close();
	 });
	 
	 //按钮绑定事件(保存)
	 $("#btnSave").click(function() {
		 window.location.href =controllername + "download?uid="+uid;
	});
	
});

//修改 查询数据
function query(){ 
			//查询表单赋值       
			$("#QID").val(id);
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
					if(response.msg==""||response.msg=="0"){
						type="insert";
					}else{
						type="update";
					var resultobj = defaultJson.dealResultJson(res);
					$("#buSpywZzhydjForm").setHtmlValue(resultobj);
					uid=resultobj.JSXMKQCTHBA_UID;
					 if(resultobj.ZHONGYAO_RY_Y=="0"){
						$("#ZHONGYAO").prop("checked","checked");
					 }
					 if(resultobj.ZHUYAO_RY_Y=="0"){
							$("#ZHUYAO").prop("checked","checked");
						 }
					}
				} 
			});

		}
//页面默认参数
function init(){ 
		query();
		if(type=="insert"){
			var sgobj=getSgCompany();
			var jsobj=getJsCompany(gcid);
			setobj(sgobj,jsobj);	
		}

}
//验证电话号码是否有效 
function isPhone(card){  
	      var reg =/^(13[0-9]|15[012356789]|17[0-9]|18[012356789]|14[57])\d{8}$/;
		  
		  if($(card).val() == null || $(card).val() == ""){
			  return true;
			  }
		  else{
		     if(reg.test($(card).val()) == false)  
		     {  
		       alert("联系电话输入不合法"); 
		       $(card).attr("style", "color:red;font-size: 14px;margin: 0;width: 207px");
		       return  false;  
		     }else{
		    	 $(card).removeAttr("style");
		    	  $(card).attr("style", "width: 87%;margin: 0;width: 207px"); 
			   return true;
		        }
		  }
}

function setobj(sgobj,jsobjs){
	$("#SGDW").val(sgobj.COMPANY_NAME);
	$("#QY_XZ").val(sgobj.COMPANY_TYPE_SV);
	$("#FAREN").val(sgobj.FAREN);
	$("#LIANXI_PHONE").val(sgobj.PHONE);
	$("#QY_ZCDIZHI").val(sgobj.ADDRESS);
	$("#FAX").val(sgobj.FAX);
	$("#QY_ZZ_CODE").val(sgobj.ZHENGSHU_CODE);
	$("#AQ_XKZ").val(sgobj.ANQUAN_CODE);
	$("#QY_ZX_DZ").val(sgobj.ZHUXI_ADDRESS);
	$("#QY_ZX_WTR").val(sgobj.ZHUXI_FZR);
	$("#ZX_PHONE").val(sgobj.ZHUXI_MOBILE);
	
	var size=jsobjs.response.data.length;
	for(var i=0;i<size;i++){
		var jsobj=jsobjs.response.data[i];
		if(jsobj.GANGWEI_UID=='19') //19项目经理
		{
	$("#JSDW").val(jsobj.COMPANY_NAME);
	$("#JLDW").val(jsobj.JL_COMPANY_NAME);
	$("#XMMC").val(jsobj.GCNAME);
	
	$("#ZZ_ZS_LB").val(jsobj.CB_ZIZHI_DENGJI);
	$("#GCDD").val(jsobj.JIANSHE_DIZHI);
	$("#LIANXIREN").val(jsobj.FZR);
	$("#MOBILE").val(jsobj.FZR_MOBILE);
	$("#ZDMJ").val(jsobj.ZDMJ);
	$("#JZMJ").val(jsobj.MIANJI);
	$("#CONTRACT_SG_NR").val(jsobj.NEIRONG_SV);
	$("#GC_ZJ").val(parseFloat(jsobj.JINE)/10000);
	$("#JZ_MJ").val(jsobj.MIANJI);
	$("#CC_KD").val(jsobj.KUADU);
	if(jsobj.KG_DATE!=""||jsobj.JG_DATE!=""){
		var sta_date =new Date(jsobj.KG_DATE);
		var end_date =new Date(jsobj.JG_DATE);
		var num = (end_date-sta_date)/(1000*3600*24);//求出两个时间的时间差，这个是天数  
		var days = parseInt(Math.ceil(num));//转化为整天（小于零的话剧不用转了） 
		$("#CONTRACT_GQ").val(days);	
	}
	$("#CONTRACT_ZLDJ").val("合格");
	$("#XMJL").val(jsobj.SG_NAME);
	$("#ZHENGSHU_CODE").val(jsobj.ZHENGSHU_CODE);
	$("#JZS_ZY").val(jsobj.ZHUANYE);
	$("#SFZ_NAMBER").val(jsobj.SHENFENZHENG);
	$("#JZS_MOBILE").val(jsobj.MOBILE);
		}else if(jsobj.GANGWEI_UID=='10'){
			$("#ZJ").val(jsobj.SG_NAME);
		}
	}
}
</script>      
</head>
<body>
<div class="container-fluid">
<div class="row-fluid">
    <div class="B-small-from-table-autoConcise">
     <form method="post" id="queryForm"  >
     <table class="B-table" width="100%">
      <!--可以再此处加入hidden域作为过滤条件 -->
      	<TR  style="display:none;">
	        <TD class="right-border bottom-border">
	        <INPUT type="hidden" class="cc-input1" kind="ZZHYDJ_UID" id="QID" fieldname="YWLZ_UID"
								 operation="=" />
	        </TD>
			<TD class="right-border bottom-border">
			</TD>
        </TR>
      </table>
      </form>
    <div style="height:5px;"></div>
    <div class="overFlowX">
	
	</div>
	</div>
	<div style="height:5px;"></div>
	<div class="B-small-from-table-autoConcise">
	    <span style="float:right">
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
	  		<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">打印</button>
      	</span><br/>
     <form method="post" id="buSpywZzhydjForm" > 
          <h4 class="title" align="center">施工工地污水、雨水临时接入市政管网申请表</h4>
            <input type="hidden" id="JSXMKQCTHBA_UID" fieldname="JSXMKQCTHBA_UID" name = "JSXMKQCTHBA_UID"/>
	  	  <input type="hidden" id="GC_TCJH_XMXDK_ID" fieldname="XMID" name = "XMID"/>
	  	      <input type="hidden" id="YWLZ_UID" fieldname="YWLZ_UID" value="0" name = "YWLZ_UID"/>
	<div align="center" id="d1">  
<table style="width: 725px;" border="1" cellspacing="0" cellpadding="0">
  <tr>
    <td width="121" ><p align="center">项目名称 </p></td>
    <td width="448" colspan="3" fieldname="XMMC"></td>
  </tr>
  <tr>
    <td width="121" ><p align="center">建设单位 </p></td>
    <td width="448" colspan="3" fieldname="JSDW"></td>
  </tr>
  <tr>
    <td width="121" ><p align="center">施工单位 </p></td>
    <td width="255" fieldname="SGDW"></td>
    <td width="76" ><p align="center">项目经理 </p></td>
    <td width="117" fieldname="XMJL"></td>
  </tr>
  <tr>
    <td width="121" ><p align="center">监理单位 </p></td>
    <td width="255" fieldname="JLDW"></td>
    <td width="76" ><p align="center">总监 </p></td>
    <td width="117" fieldname="ZJ"></td>
  </tr>
  <tr>
    <td width="121" ><p align="center">工程地点 </p></td>
    <td width="255" fieldname="GCDD"></td>
    <td width="76" ><p align="center">建筑面积 </p></td>
    <td width="117" fieldname="JZMJ"></td>
  </tr>
  <tr>
    <td width="451" colspan="3" ><p align="center">项目计划开工及考勤常态化开始时间 </p></td>
    <td width="117" id="BEGIN_DATE"></td>
  </tr>
  <tr>
    <td width="568" colspan="4" ><p align="center">
       <input id="ZHONGYAO_RY_Y" type="hidden" fieldname="ZHONGYAO_RY_Y" />
       <input id="ZHUYAO_RY_Y" type="hidden" fieldname="ZHUYAO_RY_Y" />
       <label class="checkbox inline"><input disabled="disabled" type="checkbox" id="ZHONGYAO"  sv="重要人员考勤 ">重要人员考勤 </label>
       <label class="checkbox inline"><input disabled="disabled" type="checkbox" id="ZHUYAO" sv="主要人员考勤">主要人员考勤</label>
    </p></td>
  </tr>
  <tr>
    <td width="568" colspan="4" ><p align="center">承 诺<br />
      我施工项目部和现场监理部全体关键岗位人员将遵守国家、省、市及《无锡新区在建工程施工项目部和现场监理部关键岗位人员人脸识别考勤管理暂行实施细则》有关规定，严格执行人脸识别考勤制度，确保人员到岗履职后开工建设。 <br />
      <br />
      </p>
            <p align="center" style="width:500px;">
      &nbsp;&nbsp;项目经理（签章）：                              总监（签章）： <br />
      &nbsp;&nbsp;项目部（章）：                                  监理部（章）： </p></td>
  </tr>
  <tr>
    <td width="568" colspan="4" ><p align="left">&nbsp;&nbsp;施工企业意见：<br />
            <br />
            <br /></p>
            <p align="right" style="width:600px;">
      施工企业（公章）：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <br />
      法人代表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（签字）日期： </p></td>
  </tr>
  <tr>
    <td width="568" colspan="4" ><p align="left">&nbsp;&nbsp;监理企业意见：<br />
            <br />
            <br /></p>
            <p align="right" style="width:600px;">
      监理企业（公章）： &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br />
      法人代表：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（签字）日期： </p></td>
  </tr>
  <tr>
    <td width="568" colspan="4" ><p align="left">&nbsp;&nbsp;建设单位意见：<br />
            <br />
            <br /></p>
            <p align="right" style="width:600px;">
      建设单位（公章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <br />
      单位负责人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（签章）日期： </p></td>
  </tr>
  <tr>
    <td width="568" colspan="4" ><p align="left">&nbsp;&nbsp;安监站备案：<br />
            <br /> 
            <br /></p>
            <p align="right">
      签字：         日期：   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </p></td>
  </tr>
</table>
</div>
      </form>
    </div>
   </div>
  </div>
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
</body>
</html>