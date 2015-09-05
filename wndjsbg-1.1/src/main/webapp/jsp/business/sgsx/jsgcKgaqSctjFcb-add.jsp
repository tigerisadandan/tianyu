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
var controllername= "${pageContext.request.contextPath }/sgsx/buSpywJsgckgaqsctjfcController/";
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
		    $("#JSGCKGAQSCTJFC_UID").val(resultobj.JSGCKGAQSCTJFC_UID);
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
					$("#gcname").html(resultobj.GC_NAME);
					$("#sgdw").html(resultobj.COMPANY_NAME);
					$("#jsdw").html(resultobj.JSDW);
					$("#jldw").html(resultobj.JLDW);
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
	var jsobj=jsobjs.response.data[0];
	$("#jsdw").html(jsobj.COMPANY_NAME);
	$("#gcname").html(jsobj.GCNAME);
	$("#sgdw").html(sgobj.COMPANY_NAME);
	$("#jldw").html(jsobj.JL_COMPANY_NAME);
	$("#GC_NAME").val(jsobj.GCNAME);
	$("#COMPANY_NAME").val(sgobj.COMPANY_NAME);
	$("#JSDW").val(jsobj.COMPANY_NAME);
	$("#JLDW").val(jsobj.JL_COMPANY_NAME);
	
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
	     
	     
        	
     <form method="post" id="buSpywZzhydjForm" > 
          <h4 class="title" align="center">建设工程开工安全生产条件复查表</h4>
            <input type="hidden" id="JSGCKGAQSCTJFC_UID" fieldname="JSGCKGAQSCTJFC_UID" name = "JSGCKGAQSCTJFC_UID"/>
	  	  <input type="hidden" id="GC_TCJH_XMXDK_ID" fieldname="XMID" name = "XMID"/>
	  	      <input type="hidden" id="YWLZ_UID" fieldname="YWLZ_UID" value="0" name = "YWLZ_UID"/>
	<div align="center" id="d1">  
	<table style="width: 725px;text-align: center;" >
            <tr>
              <td align="right" style="width: 17%">工程名称：</td>
              <td align="left" id="gcname"  style="width: 33%">&nbsp;</td>
              <td align="right"  style="width: 17%">施工单位：</td>
              <td align="left" id="sgdw"  style="width: 33%">&nbsp;</td>
            </tr>
            <tr>
              <td align="right">建设单位：</td>
              <td align="left" id="jsdw">&nbsp;</td>
              <td align="right">监理单位：</td>
              <td align="left" id="jldw">&nbsp;</td>
            </tr>
          </table>
 <table border="1" cellspacing="0" cellpadding="0">
 <input id="GC_NAME" type="hidden"  fieldname="GC_NAME" >
 <input id="COMPANY_NAME" type="hidden"  fieldname="COMPANY_NAME" >
 <input id="JSDW" type="hidden"  fieldname="JSDW" >
 <input id="JLDW" type="hidden"  fieldname="JLDW" >
  <tr>
    <td width="45" ><p align="center">序号 </p></td>
    <td width="123" ><p align="center">复查内容 </p></td>
    <td width="549" ><p align="center">复查情况 </p></td>
  </tr>
  <tr>
    <td width="45" ><p align="center">1 </p></td>
    <td width="123" ><p align="center">安全生产许可证 </p></td>
    <td width="549" >安全生产许可证
        <input id="AQSCXKZ_Y" type="radio" kind="dic" src="YOUWU" name="AQSCXKZ_Y" fieldname="AQSCXKZ_Y" title="on" disabled="disabled" style="display: none;"></input>
                       ；证号
        <input  maxlength="100" style="margin: 0;width: 110px" type="text" id="AQSCXKZ_CODE" fieldname="AQSCXKZ_CODE" name = "AQSCXKZ_CODE" /> </td>
  </tr>
  <tr>
    <td width="45" ><p align="center">2</p></td>
    <td width="123" ><p align="center">项目经理 </p></td>
    <td width="549" ><p>项目经理<input  maxlength="100" style="margin: 0;width: 110px" type="text" id="XMJL" fieldname="XMJL" name = "XMJL" />
                         ；B类人员安全生产知识考核合格证
       <input id="XMJL_KHZ_Y" type="radio" kind="dic" src="YOUWU" name="XMJL_KHZ_Y" fieldname="XMJL_KHZ_Y" title="on" disabled="disabled" style="display: none;"></input>
                       ；证号 <input  maxlength="100" style="margin: 0;width: 110px" type="text" id="XMJL_KHZ_CODE" fieldname="XMJL_KHZ_CODE" name = "XMJL_KHZ_CODE" /> </td></p></td>
  </tr>
  <tr>
    <td width="45" ><p align="center">3</p></td>
    <td width="123" ><p align="center">安全员 </p></td>
    <td width="549" ><p>安全员数量
       <input  maxlength="100" style="margin: 0;width: 40px" type="text" id="AQY_COUNT" fieldname="AQY_COUNT" name = "AQY_COUNT" /> 
                       名；配备数量是否符合有关要求
       <input id="AQY_COUNT_Y" type="radio" kind="dic" src="FUHE" name="AQY_COUNT_Y" fieldname="AQY_COUNT_Y" title="on" disabled="disabled" style="display: none;"></input>；
       与申报人员是否相符  <input id="AQY_FH_Y" type="radio" kind="dic" src="SHIFOU" name="AQY_FH_Y" fieldname="AQY_FH_Y" title="on" disabled="disabled" style="display: none;"></input></p></td>
  </tr>
  <tr>
    <td width="45" ><p align="center">4</p></td>
    <td width="123" ><p align="center">安全管理制度 </p></td>
    <td width="549" ><p>现场是否按规定建立安全生产管理制度<input id="AQGLZD_Y" type="radio" kind="dic" src="SHIFOU" name="AQGLZD_Y" fieldname="AQGLZD_Y" title="on" disabled="disabled" style="display: none;"></input></p> </p></td>
  </tr>
  <tr>
    <td width="45" ><p align="center">5</p></td>
    <td width="123" ><p align="center">安全施工措施 </p></td>
    <td width="549" ><p>是否编制安全施工措施或方案
    <input id="AQSGFA_Y" type="radio" kind="dic" src="SHIFOU" name="AQSGFA_Y" fieldname="AQSGFA_Y" title="on" disabled="disabled" style="display: none;"></input>
    ；是否履行审核、批准手续<input id="AQSGSX_Y" type="radio" kind="dic" src="SHIFOU" name="AQSGSX_Y" fieldname="AQSGSX_Y" title="on" disabled="disabled" style="display: none;"></input> </p></td>
  </tr>
  <tr>
    <td width="45" ><p align="center">6</p></td>
    <td width="123" ><p align="center">临时设施 </p></td>
    <td width="549" ><p>临时设施是否符合规定要求<input id="LSSS_Y" type="radio" kind="dic" src="SHIFOU" name="LSSS_Y" fieldname="LSSS_Y" title="on" disabled="disabled" style="display: none;"></input></p></td>
  </tr>
  <tr>
    <td width="45" ><p align="center">7</p></td>
    <td width="123" ><p align="center">围档及冲洗台 </p></td>
    <td width="549" ><p>是否按要求设置围挡<input id="WL_Y" type="radio" kind="dic" src="SHIFOU" name="WL_Y" fieldname="WL_Y" title="on" disabled="disabled" style="display: none;"></input>；是否按要求设置冲洗台
    <input id="CXT_Y" type="radio" kind="dic" src="SHIFOU" name="CXT_Y" fieldname="CXT_Y" title="on" disabled="disabled" style="display: none;"></input></p></td>
  </tr>
  <tr>
    <td width="45" ><p align="center">8</p></td>
    <td width="123" ><p align="center">现场&ldquo;三通一平&rdquo;、&ldquo;施工标牌 </p></td>
    <td width="549" ><p>现场&ldquo;三通一平&rdquo;是否符合施工条件<input id="STYP_Y" type="radio" kind="dic" src="FUHE" name="STYP_Y" fieldname="STYP_Y" title="on" disabled="disabled" style="display: none;"></input>； <br />
      &ldquo;施工标牌&rdquo;是否按要求张挂到位<input id="SGBP_Y" type="radio" kind="dic" src="FUHE" name="SGBP_Y" fieldname="SGBP_Y" title="on" disabled="disabled" style="display: none;"></input> </p></td>
  </tr>
  <tr>
    <td width="45" ><p align="center">9</p></td>
    <td width="123" ><p align="center">现场平面布置 </p></td>
    <td width="549" ><p>现场平面布置图<input id="PMT_Y" type="radio" kind="dic" src="SHIFOU" name="PMT_Y" fieldname="PMT_Y" title="on" disabled="disabled" style="display: none;"></input>
    ；是否按平面布置图进行布置<input id="PMT_BZ_Y" type="radio" kind="dic" src="SHIFOU" name="PMT_BZ_Y" fieldname="PMT_BZ_Y" title="on" disabled="disabled" style="display: none;"></input>
    ；平面布置是否符合有关要求<input id="PMT_FH_Y" type="radio" kind="dic" src="FUHE" name="PMT_FH_Y" fieldname="PMT_FH_Y" title="on" disabled="disabled" style="display: none;"></input> </p></td>
  </tr>
  <tr>
    <td width="45" ><p align="center">10</p></td>
    <td width="123" ><p align="center">机械设备 </p></td>
    <td width="549" ><p>是否按要求进行备案<input id="JXSB_Y" type="radio" kind="dic" src="SHIFOU" name="JXSB_Y" fieldname="JXSB_Y" title="on" disabled="disabled" style="display: none;"></input> </p></td>
  </tr>
  <tr>
    <td colspan="3" ><p align="left"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;复查意见： </p>
      <p>&nbsp;</p>
      <p  style="width:600px" align="right">监督人员： </p>
      <p  style="width:600px" align="right">年月日 </p></td>
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