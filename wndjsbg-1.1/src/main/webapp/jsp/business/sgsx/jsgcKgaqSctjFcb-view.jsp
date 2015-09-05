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
var controllerfile= "${pageContext.request.contextPath }/controller/fileController/";
var type ="insert";
var id="<%=id%>";
var gcid="<%=gcid%>";
var uid="";

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
					if(response.msg==""||response.msg=="0"){
						type="insert";
					}else{
						type="update";
					var resultobj = defaultJson.dealResultJson(res);
					uid=resultobj.JSGCKGAQSCTJFC_UID;
					$("#AQSCXKZ_Y").html(resultobj.AQSCXKZ_Y);
					$("#AQSCXKZ_CODE").html(resultobj.AQSCXKZ_CODE);
					$("#XMJL").html(resultobj.XMJL);
					$("#XMJL_KHZ_Y").html(resultobj.XMJL_KHZ_Y_SV);
					$("#XMJL_KHZ_CODE").html(resultobj.XMJL_KHZ_CODE);
					$("#AQY_COUNT").html(resultobj.AQY_COUNT);
					$("#AQY_COUNT_Y").html(resultobj.AQY_COUNT_Y_SV);
					$("#AQY_FH_Y").html(resultobj.AQY_FH_Y_SV);
					$("#AQGLZD_Y").html(resultobj.AQGLZD_Y_SV);
					$("#AQSGFA_Y").html(resultobj.AQSGFA_Y_SV);
					$("#AQSGSX_Y").html(resultobj.AQSGSX_Y_SV);
					$("#LSSS_Y").html(resultobj.LSSS_Y_SV);
					$("#WL_Y").html(resultobj.WL_Y_SV);
					$("#CXT_Y").html(resultobj.CXT_Y_SV);
					$("#STYP_Y").html(resultobj.STYP_Y_SV);
					$("#SGBP_Y").html(resultobj.SGBP_Y_SV);
					$("#PMT_Y").html(resultobj.PMT_Y_SV);
					$("#PMT_BZ_Y").html(resultobj.PMT_BZ_Y_SV);
					$("#PMT_FH_Y").html(resultobj.PMT_FH_Y_SV);
					
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
	  		<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">打印</button>
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
       <u><span id="AQSCXKZ_Y"></span></u>
                       ；证号<u><span id="AQSCXKZ_CODE"></span></u>
    </td>
  </tr>
  <tr>
    <td width="45" ><p align="center">2</p></td>
    <td width="123" ><p align="center">项目经理 </p></td>
    <td width="549" ><p>项目经理
     <u><span id="XMJL"></span></u>
                         ；B类人员安全生产知识考核合格证<u><span id="XMJL_KHZ_Y"></span></u>
                       ；证号 <u><span id="XMJL_KHZ_CODE"></span></u></p></td>
  </tr>
  <tr>
    <td width="45" ><p align="center">3</p></td>
    <td width="123" ><p align="center">安全员 </p></td>
    <td width="549" ><p>安全员数量
     <u><span id="AQY_COUNT"></span></u>
                       名；配备数量是否符合有关要求
                       <u><span id="AQY_COUNT"></span></u>
       与申报人员是否相符 <u><span id="AQY_FH_Y"></span></u> </p></td>
  </tr>
  <tr>
    <td width="45" ><p align="center">4</p></td>
    <td width="123" ><p align="center">安全管理制度 </p></td>
    <td width="549" ><p>现场是否按规定建立安全生产管理制度<u><span id="AQGLZD_Y"></span></u></p></td>
  </tr>
  <tr>
    <td width="45" ><p align="center">5</p></td>
    <td width="123" ><p align="center">安全施工措施 </p></td>
    <td width="549" ><p>是否编制安全施工措施或方案
    <u><span id="AQSGFA_Y"></span></u>
    
    ；是否履行审核、批准手续 <u><span id="AQSGSX_Y"></span></u></p></td>
  </tr>
  <tr>
    <td width="45" ><p align="center">6</p></td>
    <td width="123" ><p align="center">临时设施 </p></td>
    <td width="549" ><p>临时设施是否符合规定要求<u><span id="LSSS_Y"></span></u></p></td>
  </tr>
  <tr>
    <td width="45" ><p align="center">7</p></td>
    <td width="123" ><p align="center">围档及冲洗台 </p></td>
    <td width="549" ><p>是否按要求设置围挡<u><span id="WL_Y"></span></u>；是否按要求设置冲洗台<u><span id="CXT_Y"></span></u></p></td>
  </tr>
  <tr>
    <td width="45" ><p align="center">8</p></td>
    <td width="123" ><p align="center">现场&ldquo;三通一平&rdquo;、&ldquo;施工标牌 </p></td>
    <td width="549" ><p>现场&ldquo;三通一平&rdquo;是否符合施工条件<u><span id="STYP_Y"></span></u>； <br />
      &ldquo;施工标牌&rdquo;是否按要求张挂到位<u><span id="SGBP_Y"></span></u></p></td>
  </tr>
  <tr>
    <td width="45" ><p align="center">9</p></td>
    <td width="123" ><p align="center">现场平面布置 </p></td>
    <td width="549" ><p>现场平面布置图<u><span id="PMT_Y"></span></u>
    ；是否按平面布置图进行布置<u><span id="PMT_BZ_Y"></span></u>
    ；平面布置是否符合有关要求<u><span id="PMT_FH_Y"></span></u></p></td>
  </tr>
  <tr>
    <td width="45" ><p align="center">10</p></td>
    <td width="123" ><p align="center">机械设备 </p></td>
    <td width="549" ><p>是否按要求进行备案<u><span id="JXSB_Y"></span></u></p></td>
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