
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
var controllername= "${pageContext.request.contextPath }/sgsx/buSpywSzgcqyrqjdxgczsbController/";
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
		    $("#SZGCQYRQJDXGCZSB_UID").val(resultobj.SZGCQYRQJDXGCZSB_UID);
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
	
	$("#COMPANY_NAME").val(sgobj.COMPANY_NAME); //施工信息
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
	$("#JS_DW_NAME").val(jsobj.COMPANY_NAME);
	$("#JS_DW_XZ").val(jsobj.COMPANY_PROPERTY_SV);
	$("#JS_PROJECTS_NAME").val(jsobj.GONGCHENG_NAME);
	if(jsobj.CB_XINGZHI=="ZB"){
		$("#CB_XZ").val("总承包");
		$("#ZB_FB_NAME").val(sgobj.COMPANY_NAME);
	}
	if(jsobj.CB_XINGZHI=="CB"){
		$("#CB_XZ").val("专业承包");
	}
	$("#ZZ_ZS_LB").val(jsobj.CB_ZIZHI_DENGJI);
	$("#PROJECTS_SZD").val(jsobj.JIANSHE_DIZHI);
	$("#CONTRACT_NR").val(jsobj.NEIRONG_SV);
	$("#GC_ZJ").val(parseFloat(jsobj.JINE)/10000);
	$("#GCL").val(jsobj.MIANJI);
	$("#CD_KD").val(jsobj.KUADU);
	var sta_date =new Date(jsobj.KG_DATE);
	var end_date =new Date(jsobj.JG_DATE);
	var num = (end_date-sta_date)/(1000*3600*24);//求出两个时间的时间差，这个是天数  
	var days = parseInt(Math.ceil(num));//转化为整天（小于零的话剧不用转了） 
	$("#CONTRACT_DATE").val(days);
	$("#CONTRACT_ZLDJ").val("合格");
	$("#MANAGER").val(jsobj.SG_NAME);
	$("#ZHENGSHU_CODE").val(jsobj.ZHENGSHU_CODE);
	$("#ZHUANYE").val(jsobj.ZHUANYE);
	$("#SHENGFZ_CODE").val(jsobj.SHENFENZHENG);
	$("#MANAGER_MOBILE").val(jsobj.MOBILE);
	$("#BEGIN_DATE").val(jsobj.KG_DATE);
	$("#END_DATE").val(jsobj.JG_DATE);
		}else if(jsobj.GANGWEI_UID=='20'){ //负责人
			$("#JISHU_FZR").val(jsobj.SG_NAME);
			$("#JISHU_FZR_PHONE").val(jsobj.MOBILE);
		}else if(jsobj.GANGWEI_UID=='23'){//质检员
			$("#ZL_YUAN").val(jsobj.SG_NAME);
			$("#ZL_YUAN_ZS_CODE").val(jsobj.ZHENGSHU_CODE);
		}else if(jsobj.GANGWEI_UID=='22'){//安全员
			$("#AQ_YUAN").val(jsobj.SG_NAME);
			$("#AQ_YUAN_ZS_CODE").val(jsobj.ZHENGSHU_CODE);
		}else if(jsobj.GANGWEI_UID=='21'){//施工员
			$("#SG_YUAN").val(jsobj.SG_NAME);
			$("#SG_YUAN_ZS_CODE").val(jsobj.ZHENGSHU_CODE);
		}else if(jsobj.GANGWEI_UID=='24'){//材料员 
			$("#CL_YUAN").val(jsobj.SG_NAME);
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
	  		<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">保存</button>
      	</span><br/>
	     
	     
        	
     <form method="post" id="buSpywZzhydjForm"  > 
          <h4 class="title" align="center">省（内）外市政工程企业进入无锡新区承接单项工程项目注册申请表</h4>
         
            <input type="hidden" id="SZGCQYRQJDXGCZSB_UID" fieldname="SZGCQYRQJDXGCZSB_UID" name = "SZGCQYRQJDXGCZSB_UID"/>
	  	  <input type="hidden" id="GC_TCJH_XMXDK_ID" fieldname="XMID" name = "XMID"/>
	  	      <input type="hidden" id="YWLZ_UID" fieldname="YWLZ_UID" value="0" name = "YWLZ_UID"/>
	<div align="center" id="d1">  
<table width="725"  border="1" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td  colspan="2"><label for="P10030_COMPANY_NAME">
      <div align="center">
        <label for="P10020_COMPANY_NAME">市政建设工程企业名称
          (盖章)</label>
      </div>
      </label></td>
    <td width="226"><input readonly="readonly"  maxlength="255" style="margin: 0;width: 226px"  id="COMPANY_NAME" type="text"  fieldname="COMPANY_NAME" name = "COMPANY_NAME"   /></td>
    <td width="135"><label for="P10030_QY_XZ">
      <div align="center">建筑企业性质</div>
      </label></td>
    <td width="207"><input readonly="readonly"  maxlength="255" style="margin: 0;width: 226px"  id="QY_XZ" type="text"  fieldname="QY_XZ" name = "QY_XZ"   /></td>
  </tr>
  <tr>
    <td colspan="2"><label for="P10030_FAREN">
      <div align="center">建筑企业<br />
        （法人代表）</div>
      </label></td>
    <td><input readonly="readonly"  maxlength="255" style="margin: 0;width: 226px"  id="FAREN" type="text"  fieldname="FAREN" name = "FAREN"   /></td>
    <td><label for="P10030_LIANXI_PHONE">
      <div align="center">联系电话</div>
      </label></td>
    <td><input readonly="readonly"  maxlength="255" style="margin: 0;width: 226px"  id="LIANXI_PHONE" type="text"  fieldname="LIANXI_PHONE" name = "LIANXI_PHONE"   /></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">
      <label for="P10030_QY_ZCDIZHI">建筑企业<br />
        (注册地址)</label>
    </div></td>
    <td><input readonly="readonly"  maxlength="255" style="margin: 0;width: 226px"  id="QY_ZCDIZHI" type="text"  fieldname="QY_ZCDIZHI" name = "QY_ZCDIZHI"   /></td>
    <td><label for="P10030_FAX">
      <div align="center">传真号</div>
      </label></td>
    <td><input  maxlength="255" style="margin: 0;width: 226px"  id="FAX" type="text"  fieldname="FAX" name = "FAX"   /></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">
      <label for="P10020_QY_ZZ_CODE">建设企业资质编号</label>
    </div></td>
    <td><input readonly="readonly"  maxlength="255" style="margin: 0;width: 226px"  id="QY_ZZ_CODE" type="text"  fieldname="QY_ZZ_CODE" name = "QY_ZZ_CODE"   /></td>
    <td><label for="P10030_ZZ_ZS_LB">
      <div align="center">
        <label for="P10020_QY_XQ_DIZHI">建设企业<br />
          (驻新区地址)</label>
      </div>
      </label></td>
    <td><input  maxlength="255" style="margin: 0;width: 226px"  id="QY_XQ_DIZHI" type="text"  fieldname="QY_XQ_DIZHI" name = "QY_XQ_DIZHI"   /></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">
      <label for="P10020_QY_ZX_WTR">建设企业<br />
        (驻锡委托人)</label>
    </div></td>
    <td><input readonly="readonly"  maxlength="255" style="margin: 0;width: 226px"  id="QY_ZX_WTR" type="text"  fieldname="QY_ZX_WTR" name = "QY_ZX_WTR"   /></td>
    <td><label for="P10030_QY_ZX_DZ">
      <div align="center">
        <label for="P10020_ZX_PHONE">联系电话</label>
      </div>
      </label></td>
    <td><input readonly="readonly"  maxlength="255" style="margin: 0;width: 226px"  id="ZX_PHONE" type="text"  fieldname="ZX_PHONE" name = "ZX_PHONE"   /></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">
      <label for="P10020_JS_DW_NAME">建设单位名称</label>
    </div></td>
    <td><input readonly="readonly"  maxlength="255" style="margin: 0;width: 226px"  id="JS_DW_NAME" type="text"  fieldname="JS_DW_NAME" name = "JS_DW_NAME"   /></td>
    <td><label for="P10030_ZX_PHONE">
      <div align="center">建设单位性质</div>
      </label></td>
    <td><input readonly="readonly"  maxlength="255" style="margin: 0;width: 226px"  id="JS_DW_XZ" type="text"  fieldname="JS_DW_XZ" name = "JS_DW_XZ"   /></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">
      <label for="P10020_JS_PROJECTS_NAME">建设项目名称</label>
    </div></td>
    <td><input readonly="readonly"  maxlength="255" style="margin: 0;width: 226px"  id="JS_PROJECTS_NAME" type="text"  fieldname="JS_PROJECTS_NAME" name = "JS_PROJECTS_NAME"   /></td>
    <td><label for="P10030_JS_XZ">
      <div align="center">合同施工内容</div>
      </label></td>
    <td><input readonly="readonly"  maxlength="255" style="margin: 0;width: 226px"  id="CONTRACT_NR" type="text"  fieldname="CONTRACT_NR" name = "CONTRACT_NR"   /></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">建设项目所在地</div></td>
    <td><input readonly="readonly"  maxlength="255" style="margin: 0;width: 226px"  id="PROJECTS_SZD" type="text"  fieldname="PROJECTS_SZD" name = "PROJECTS_SZD"   /></td>
    <td><label for="P10030_GK_ZB_TZS">
      <div align="center">
        <label for="P10020_ZB_FB_NAME">总承包及<br />
          分包企业名称</label>
      </div>
      </label></td>
    <td><input readonly="readonly"  maxlength="255" style="margin: 0;width: 226px"  id="ZB_FB_NAME" type="text"  fieldname="ZB_FB_NAME" name = "ZB_FB_NAME"   /></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">
      <label for="P10030_CB_XZ">工程造价(万元)</label>
    </div></td>
    <td><input readonly="readonly"  maxlength="255" style="margin: 0;width: 226px"  id="GC_ZJ" type="text"  fieldname="GC_ZJ" name = "GC_ZJ"   /></td>
    <td><label for="P10030_ZCB_DW">
      <div align="center">
        <label for="P10020_GCL">工程量(㎡)</label>
         </div>
      </label></td>
    <td><input readonly="readonly"  maxlength="255" style="margin: 0;width: 226px"  id="GCL" type="text"  fieldname="GCL" name = "GCL"   /></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">
      <label for="P10030_CB_XZ">工程类型</label>
    </div></td>
    <td><input  maxlength="255" style="margin: 0;width: 226px"  id="GC_LEIXING" type="text"  fieldname="GC_LEIXING" name = "GC_LEIXING"   /></td>
    <td><label for="P10030_CONTRACT_SG_NR">
      <div align="center">
        <label for="P10020_CD_KD">长度或跨度</label>
      </div>
      </label></td>
    <td><input readonly="readonly"  maxlength="255" style="margin: 0;width: 226px"  id="CD_KD" type="text"  fieldname="CD_KD" name = "CD_KD"   /></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">
      <label for="P10030_GC_ZJ">合同工期（天）</label>
    </div></td>
    <td><input readonly="readonly"  maxlength="255" style="margin: 0;width: 226px"  id="CONTRACT_DATE" type="text"  fieldname="CONTRACT_DATE" name = "CONTRACT_DATE"   /></td>
    <td><label for="P10030_JZ_MJ">
      <div align="center">合同质量等级</div>
      </label></td>
    <td><input  maxlength="255" style="margin: 0;width: 226px"  id="CONTRACT_ZLDJ" type="text"  fieldname="CONTRACT_ZLDJ" name = "CONTRACT_ZLDJ"   /></td>
  </tr>
  <tr>
    <td width="60" rowspan="3"><div align="center">
      <div align="center">建造师<br />
      (项目经理)</div></td>
    <td width="85"><label for="P10030_JZS_NAME">
      <div align="center">姓名</div>
    </label></td>
    <td><input readonly="readonly"  maxlength="255" style="margin: 0;width: 226px"  id="MANAGER" type="text"  fieldname="MANAGER" name = "MANAGER"   /></td>
    <td><label for="P10030_JZS_ZY">
      <div align="center">专业</div>
      </label></td>
    <td><input readonly="readonly"  maxlength="255" style="margin: 0;width: 226px"  id="ZHUANYE" type="text"  fieldname="ZHUANYE" name = "ZHUANYE"   /></td>
  </tr>
  <tr>
    <td><div align="center">
      <label for="P10030_ZHENGSHU_CODE">证书编号</label>
    </div></td>
    <td><input readonly="readonly"  maxlength="255" style="margin: 0;width: 226px"  id="ZHENGSHU_CODE" type="text"  fieldname="ZHENGSHU_CODE" name = "ZHENGSHU_CODE"   /></td>
    <td><label for="P10030_SFZ_NAMBER">
      <div align="center">身份证号</div>
      </label></td>
    <td><input readonly="readonly"  maxlength="255" style="margin: 0;width: 226px"  id="SHENGFZ_CODE" type="text"  fieldname="SHENGFZ_CODE" name = "SHENGFZ_CODE"   /></td>
  </tr>
  <tr>
    <td><div align="center">联系电话</div></td>
    <td><input  maxlength="255" style="margin: 0;width: 226px"  id="MANAGER_PHONE" type="text"  fieldname="MANAGER_PHONE" name = "MANAGER_PHONE"   /></td>
    <td><label for="P10030_JZS_PHONE">
      <div align="center">移动电话</div>
      </label></td>
    <td><input readonly="readonly"  maxlength="255" style="margin: 0;width: 226px"  id="MANAGER_MOBILE" type="text"  fieldname="MANAGER_MOBILE" name = "MANAGER_MOBILE"   /></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">
      <label for="P10020_JISHU_FZR">技术负责人</label>
    </div></td>
    <td><input readonly="readonly"  maxlength="255" style="margin: 0;width: 226px"  id="JISHU_FZR" type="text"  fieldname="JISHU_FZR" name = "JISHU_FZR"   /></td>
    <td><label for="P10030_END_DATE">
      <div align="center">联系电话</div>
      </label></td>
    <td><input readonly="readonly"  maxlength="255" style="margin: 0;width: 226px"  id="JISHU_FZR_PHONE" type="text"  fieldname="JISHU_FZR_PHONE" name = "JISHU_FZR_PHONE"   /></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">
      <label for="P10020_ZL_YUAN">质量员</label>
    </div></td>
    <td><input readonly="readonly"  maxlength="255" style="margin: 0;width: 226px"  id="ZL_YUAN" type="text"  fieldname="ZL_YUAN" name = "ZL_YUAN"   /></td>
    <td><label for="P10030_SBR_PHONE">
      <div align="center">证书编号</div>
      </label></td>
    <td><input readonly="readonly"  maxlength="255" style="margin: 0;width: 226px"  id="ZL_YUAN_ZS_CODE" type="text"  fieldname="ZL_YUAN_ZS_CODE" name = "ZL_YUAN_ZS_CODE"   /></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">安全员</div></td>
    <td><input readonly="readonly"  maxlength="255" style="margin: 0;width: 226px"  id="AQ_YUAN" type="text"  fieldname="AQ_YUAN" name = "AQ_YUAN"   /></td>
    <td><div align="center">证书编号</div></td>
    <td><input readonly="readonly"  maxlength="255" style="margin: 0;width: 226px"  id="AQ_YUAN_ZS_CODE" type="text"  fieldname="AQ_YUAN_ZS_CODE" name = "AQ_YUAN_ZS_CODE"   /></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">
      <label for="P10020_SG_YUAN">施工员</label>
    </div></td>
    <td><input readonly="readonly"  maxlength="255" style="margin: 0;width: 226px"  id="SG_YUAN" type="text"  fieldname="SG_YUAN" name = "SG_YUAN"   /></td>
    <td><div align="center">证书编号</div></td>
    <td><input readonly="readonly"  maxlength="255" style="margin: 0;width: 226px"  id="SG_YUAN_ZS_CODE" type="text"  fieldname="SG_YUAN_ZS_CODE" name = "SG_YUAN_ZS_CODE"   /></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">
      <label for="P10020_CL_YUAN">材料员</label>
    </div></td>
    <td><input readonly="readonly"  maxlength="255" style="margin: 0;width: 226px"  id="CL_YUAN" type="text"  fieldname="CL_YUAN" name = "CL_YUAN"   /></td>
    <td><div align="center">核算员</div></td>
    <td><input  maxlength="255" style="margin: 0;width: 226px"  id="HS_YUAN" type="text"  fieldname="HS_YUAN" name = "HS_YUAN"   /></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">
      <label for="P10020_BEGIN_DATE">开工日期</label>
    </div></td>
    <td><input readonly="readonly"  maxlength="255" style="margin: 0;width: 226px"  id="BEGIN_DATE" type="text"  fieldname="BEGIN_DATE" name = "BEGIN_DATE"   /></td>
    <td><div align="center">
      <label for="P10020_END_DATE">竣工日期</label>
    </div></td>
    <td><input readonly="readonly"  maxlength="255" style="margin: 0;width: 226px"  id="END_DATE" type="text"  fieldname="END_DATE" name = "END_DATE"   /></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">
      <label for="P10020_ZB_TZ_CODE">公开招标中标通知书<br />
        或直接发包通知书编号</label>
    </div></td>
    <td><input  maxlength="255" style="margin: 0;width: 226px"  id="ZB_TZ_CODE" type="text"  fieldname="ZB_TZ_CODE" name = "ZB_TZ_CODE"   /></td>
    <td><div align="center">
      <label for="P10020_ZL_YUAN">文明施工验收合格证编号</label>
    </div></td>
    <td><input  maxlength="255" style="margin: 0;width: 226px"  id="WM_SG_CODE" type="text"  fieldname="WM_SG_CODE" name = "WM_SG_CODE"   /></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">
      <label for="P10020_SB_LIANXIREN">申报联系人</label>
    </div></td>
    <td><input maxlength="255" style="margin: 0;width: 226px"  id="SB_LIANXIREN" type="text"  fieldname="SB_LIANXIREN" name = "SB_LIANXIREN" placeholder="必填" check-type="required"  /></td>
    <td><div align="center">
      <label for="P10020_ZL_YUAN">申报人联系电话</label>
    </div></td>
    <td><input   maxlength="255" style="margin: 0;width: 226px"  id="PhoneYz" type="text"  fieldname="SBR_LX_PHONE" name = "SBR_LX_PHONE" placeholder="必填" check-type="required"  /></td>
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
<script>
</script>
</html>