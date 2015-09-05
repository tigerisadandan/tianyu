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
var controllername= "${pageContext.request.contextPath }/sgsx/buSpywSggdsjrgwsbController/";
var contextPath="${pageContext.request.contextPath }";
var type ="insert";
var id="<%=id%>";
var gcid="<%=gcid%>";

//页面初始化
$(function() {
	 $("#pubAlert").hide();
	 init();
	 $("#btnClose").click(function(){
		    window.close();
	 });
	 
	 //按钮绑定事件(保存)
	 $("#btnSave").click(function() {
	 // var ywlzid = $(window.opener.document).find("#YWLZ_UID").val(); //业务流转ID 父页面获取
	 // $("#YWLZ_UID").val(ywlzid);
         var yz=true;
         $("#d1").find("input[id='PhoneYz']").each(function(i,item) {
		    if(!isPhone(item)){
		         yz=false;
		         return false;
		    }
         });
         
		 if($("#buSpywZzhydjForm").validationButton()&&yz){
		  if(type=="insert"){
			var ywlz_uid = window.opener.getYwlz();
		  	$("#YWLZ_UID").val(ywlz_uid);//	YWLZ_UID
		  }
		  //生成json串
		  var data = Form2Json.formToJSON(buSpywZzhydjForm);
		  //组成保存json串格式
		  var data1 = defaultJson.packSaveJson(data);
		  //调用ajax插入
		  if(type=="insert"){
    		defaultJson.doInsertJson(controllername + "insert", data1);
    		//$("#buSpywZzhydjForm").clearFormResult();
    		var json=$("#resultXML").val();//接受返回的数据 
		    var tempJson=eval("("+json+")");
		    var resultobj=tempJson.response.data[0];
		    $("#SGGDSJRGWSB_UID").val(resultobj.SGGDSJRGWSB_UID);
    			type="update";
    			$("#btnSave").hide();
    	  }else if(type=="update"){
    			defaultJson.doUpdateJson(controllername + "update", data1);
    	  }
		}else{
			requireFormMsg();
		  	return;
		}
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
					$("#buSpywZzhydjForm").setFormValues(resultobj);
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
	$("#COMPANY_NAME").val(sgobj.COMPANY_NAME);
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
	$("#JS_NAME").val(jsobj.COMPANY_NAME);
	$("#JS_XZ").val(jsobj.COMPANY_PROPERTY_SV);
	$("#PROJECTS_NAME").val(jsobj.GCNAME);
	if(jsobj.CB_XINGZHI=="ZB"){
		$("#CB_XZ").val("总承包");
		$("#ZCB_DW").val(sgobj.COMPANY_NAME);
	}
	if(jsobj.CB_XINGZHI=="CB"){ 
		$("#CB_XZ").val("专业承包");
	}
	$("#ZZ_ZS_LB").val(jsobj.CB_ZIZHI_DENGJI);
	$("#PROJECTS_ADDRESS").val(jsobj.JIANSHE_DIZHI);
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
	$("#JZS_NAME").val(jsobj.SG_NAME);
	$("#ZHENGSHU_CODE").val(jsobj.ZHENGSHU_CODE);
	$("#JZS_ZY").val(jsobj.ZHUANYE);
	$("#SFZ_NAMBER").val(jsobj.SHENFENZHENG);
	$("#JZS_MOBILE").val(jsobj.MOBILE);
	$("#BEGIN_DATE").val(jsobj.KG_DATE);
	$("#END_DATE").val(jsobj.JG_DATE);
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
            <input type="hidden" id="SGGDSJRGWSB_UID" fieldname="SGGDSJRGWSB_UID" name = "SGGDSJRGWSB_UID"/>
	  	  <input type="hidden" id="GC_TCJH_XMXDK_ID" fieldname="XMID" name = "XMID"/>
	  	      <input type="hidden" id="YWLZ_UID" fieldname="YWLZ_UID" value="0" name = "YWLZ_UID"/>
	<div align="center" id="d1">  
 <table width="750" border="1" cellpadding="0" cellspacing="0">
  <tr>
    <td width="98" ><p align="right">申请单位(章) </p></td>
    <td  colspan="7" ><input readonly="readonly"  maxlength="255" style="margin: 0;width: 98%"  id="COMPANY_NAME" type="text"  fieldname="COMPANY_NAME" name = "COMPANY_NAME"   /></td>
  </tr>
  <tr>
    <td width="98" ><p align="right">项目名称 </p></td>
    <td width="280" colspan="3" ><input readonly="readonly"  maxlength="255" style="margin: 0;width: 95%"  id="PROJECTS_NAME" type="text"  fieldname="PROJECTS_NAME" name = "PROJECTS_NAME"   /></td>
    <td width="100" ><p align="right">项目地点 </p></td>
    <td  colspan="3" ><input readonly="readonly"  maxlength="255" style="margin: 0;width: 95%"  id="PROJECTS_ADDRESS" type="text"  fieldname="PROJECTS_ADDRESS" name = "PROJECTS_ADDRESS"   /></td>
  </tr>
  <tr>
    <td width="98" ><p align="right">联系人 </p></td>
    <td  colspan="3" ><input readonly="readonly"  maxlength="255" style="margin: 0;width: 95%"  id="LIANXIREN" type="text"  fieldname="LIANXIREN" name = "LIANXIREN"   /></td>
    <td  ><p align="right">联系电话 </p></td>
    <td  ><input  maxlength="255" style="margin: 0;width: 85%"  id="PHONE" type="text"  fieldname="PHONE" name = "PHONE"   /></td>
    <td width="80" ><p align="right">手机 </p></td>
    <td  ><input readonly="readonly"  maxlength="255" style="margin: 0;width: 85%"  id="MOBILE" type="text"  fieldname="MOBILE" name = "MOBILE"   /></td>
  </tr>
  <tr>
    <td width="98" ><p align="right">开工日期 </p></td>
    <td  colspan="3" ><input readonly="readonly"  maxlength="255" style="margin: 0;width: 95%"  id="BEGIN_DATE" type="text"  fieldname="BEGIN_DATE" name = "BEGIN_DATE"   /></td>
    <td  ><p align="right">完工日期 </p></td>
    <td  colspan="3" ><input readonly="readonly"  maxlength="255" style="margin: 0;width: 95%"  id="END_DATE" type="text"  fieldname="END_DATE" name = "END_DATE"   /></td>
  </tr>
  <tr>
    <td width="98" ><p align="right">占地面积(m2)</p></td>
    <td width="90" ><input readonly="readonly"  maxlength="255" style="margin: 0;width: 85%"  id="ZDMJ" type="text"  fieldname="ZDMJ" name = "ZDMJ"   /></td>
    <td width="100" ><p align="right">建筑面积(m2)</p></td>
    <td width="90" ><input readonly="readonly"  maxlength="255" style="margin: 0;width: 85%"  id="JZMJ" type="text"  fieldname="JZMJ" name = "JZMJ"   /></td>
    <td  ><p align="right">工地最多人数 </p></td>
    <td  colspan="3" ><input   maxlength="255" style="margin: 0;width: 95%"  id="GDRENSHU" type="text"  fieldname="GDRENSHU" name = "GDRENSHU"   /></td>
  </tr>
  <tr>
    <td width="98" ><p align="right">用水量(m3/日)</p></td>
    <td  ><input   maxlength="255" style="margin: 0;width: 85%"  id="YSL" type="text"  fieldname="YSL" name = "YSL"   /></td>
    <td  ><p align="right">排水量(m3/日)</p></td>
    <td  ><input   maxlength="255" style="margin: 0;width: 85%"  id="PWL" type="text"  fieldname="PWL" name = "PWL"   /></td>
    <td  ><p align="right">化粪池(只)</p></td>
    <td  ><input   maxlength="255" style="margin: 0;width: 85%"  id="HFC" type="text"  fieldname="HFC" name = "HFC"   /></td>
    <td  ><p align="right">尺寸或型号 </p></td>
    <td   ><input   maxlength="255" style="margin: 0;width: 85%"  id="HFNJCCXH" type="text"  fieldname="HFNJCCXH" name = "HFNJCCXH"   /></td>
  </tr>
  <tr>
    <td width="98" ><p align="right">临时污水 <br />
      管径、长度(m)</p></td>
    <td  ><input   maxlength="255" style="margin: 0;width: 85%"  id="LSWSGJ" type="text"  fieldname="LSWSGJ" name = "LSWSGJ"   /></td>
    <td  ><p align="right">临时雨水<br />
      管径、长度(m) </p></td>
    <td   ><input   maxlength="255" style="margin: 0;width: 85%"  id="LSYS" type="text"  fieldname="LSYS" name = "LSYS"   /></td>
    <td  ><p align="right">沉淀池(只)</p></td>
    <td  ><input   maxlength="255" style="margin: 0;width: 85%"  id="YSCD" type="text"  fieldname="YSCD" name = "YSCD"   /></td>
    <td  ><p align="right">尺寸或型号 </p></td>
    <td   ><input   maxlength="255" style="margin: 0;width: 85%"  id="HFNJCCXH1" type="text"  fieldname="HFNJCCXH1" name = "HFNJCCXH1"   /></td>
  </tr>
  <tr>
    <td width="98" ><p align="right">污水接口(个)</p></td>
    <td  ><input   maxlength="255" style="margin: 0;width: 85%"  id="WSJRK" type="text"  fieldname="WSJRK" name = "WSJRK"   /></td>
    <td  ><p align="right">雨水接口(个) </p></td>
    <td   ><input   maxlength="255" style="margin: 0;width: 85%"  id="YSJRK" type="text"  fieldname="YSJRK" name = "YSJRK"   /></td>
    <td  ><p align="right">隔油池(只)</p></td>
    <td  ><input   maxlength="255" style="margin: 0;width: 85%"  id="GYC" type="text"  fieldname="GYC" name = "GYC"   /></td>
    <td  ><p align="right">尺寸或型号 </p></td>
    <td   ><input   maxlength="255" style="margin: 0;width: 85%"  id="HFNJCCXH2" type="text"  fieldname="HFNJCCXH2" name = "HFNJCCXH2"   /></td>
  </tr>
  <tr>
    <td width="98" ><p align="right">接入管网道 </p></td>
    <td  colspan="7" ><input   maxlength="255" style="margin: 0;width: 98%"  id="JRGWDL" type="text"  fieldname="JRGWDL" name = "JRGWDL"   /></td>
  </tr>
  <tr>
    <td  colspan="8" ><p>临时设施： 需附工地平面布置、临时设施及临时管线图 </p></td>
  </tr>
  <tr>
    <td width="98" ><p align="right">建设单位 <br />
      意见(章)</p></td>
    <td  colspan="7" >
       <textarea rows="3" cols="" maxlength="255" style="margin: 0;width: 98%" id="JSDWYJ"  fieldname="JSDWYJ" name = "JSDWYJ"  ></textarea></td>
  </tr>
  <tr>
    <td width="98" ><p align="right">环境监察大队实地勘查记录： </p></td>
    <td  colspan="7" >
        <textarea rows="3" cols="" maxlength="255" style="margin: 0;width: 98%" id="CJKKANCHA"  fieldname="CJKKANCHA" name = "CJKKANCHA"  ></textarea></td>
  </tr>
  <tr>
    <td width="98" ><p align="right">排水处签发： </p></td>
    <td  colspan="7" ><br/><br/><br/>
      <p>                                                          签发人： （章）     年      月   日 </p></td>
  </tr>
  <tr>
    <td width="98" ><p align="right">同意污水接入： </p></td>
    <td  colspan="3" ><p><input   maxlength="255" style="margin: 0;width: 30%"  id="AGREE_LU" type="text"  fieldname="AGREE_LU" name = AGREE_LU   />路<input   maxlength="255" style="margin: 0;width: 30%"  id="JING" type="text"  fieldname="JING" name = "JING"   />井 </p></td>
    <td   ><p>同意雨水接入： </p></td>
    <td  colspan="3" ><p><input   maxlength="255" style="margin: 0;width: 30%"  id="AGREE1_LU" type="text"  fieldname="AGREE1_LU" name = "AGREE1_LU"   />路<input   maxlength="255" style="margin: 0;width: 30%"  id="JING1" type="text"  fieldname="JING1" name = "JING1"   />井 </p></td>
  </tr>
  <tr>
    <td width="98" ><p align="right">备注  </p></td>
    <td  colspan="7" >
    <textarea rows="3" cols="" maxlength="255" style="margin: 0;width: 98%" id="DESCRIBE"  fieldname="DESCRIBE" name = "DESCRIBE"  ></textarea></td>
  </tr>
  <tr>
    <td  colspan="10" ><p>说明：(1)将工地平面布置、临时设施及临时管线图与申请表同时递交。<br />
      (2)临时管线完成需后经城建科验收合格经批准发放《排水许可证（临时）》后方能接入指定的市政管网，正式排水接入市政管网需另行申报审批并办理领取《排水许可证》手续。<br />
      (3)领取《排水许可证（临时）》作为工地开工必备条件之一。<br />
      (4)临时设施参考标准：<br />
      化粪池：02S701砖砌化粪池标准。<br />
      隔油池：04S519小型排水构筑物标准。<br />
      沉淀池：04S519小型排水构筑物标准。<br />
      环境监察大队联系电话：85223156<br />
      排水处联系电话：81890835<br />
      新区建设环保局编制 </p></td>
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