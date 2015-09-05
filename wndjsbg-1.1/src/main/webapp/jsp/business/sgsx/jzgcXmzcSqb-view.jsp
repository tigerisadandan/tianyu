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
var controllername= "${pageContext.request.contextPath }/sgsx/buSpywJzgcqyrqjdxgczsbController/";
var contextPath="${pageContext.request.contextPath }";
var controllerfile= "${pageContext.request.contextPath }/controller/fileController/";
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
		 window.location.href =controllername + "download?uid="+uid;
	});
	
});
var uid="";
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
					uid=resultobj.JZGCQYRQJDXGCZSB_UID;
					$("#buSpywZzhydjForm").setHtmlValue(resultobj);
					}
				} 
			});

		}
//页面默认参数
function init(){ 
		query();
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
	     
	     
        	
     <form method="post" id="buSpywZzhydjForm"  > 
          <h4 class="title" align="center">省（内）外建筑工程企业进入无锡新区承接单项工程项目注册申请表</h4>
            <input type="hidden" id="JZGCQYRQJDXGCZSB_UID" fieldname="JZGCQYRQJDXGCZSB_UID" name = "JZGCQYRQJDXGCZSB_UID"/>
	  	  <input type="hidden" id="GC_TCJH_XMXDK_ID" fieldname="XMID" name = "XMID"/>
	  	      <input type="hidden" id="YWLZ_UID" fieldname="YWLZ_UID" value="0" name = "YWLZ_UID"/>
	<div align="center" id="d1">  
 <table width="725px" height="629px" border="1" align="center" cellpadding="0" cellspacing="0"  border="1"  >
  <tr>
    <td colspan="2"><label for="P10030_COMPANY_NAME">
      <div align="center">建筑工程企业名称</div> 
    </label></td>
    <td width="226px" fieldname="COMPANY_NAME" ></td>
    <td width="135px"><label for="P10030_QY_XZ">
      <div align="center">建筑企业性质</div>
    </label></td>
    <td width="207px" fieldname="QY_XZ"></td>
  </tr>
  <tr>
    <td colspan="2"><label for="P10030_FAREN">
      <div align="center">建筑企业<br />
        （法人代表）</div>
    </label></td>
    <td fieldname="FAREN"></td>
    <td><label for="P10030_LIANXI_PHONE">
      <div align="center">企业联系电话</div>
    </label></td>
    <td fieldname="LIANXI_PHONE"></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">
      <label for="P10030_QY_ZCDIZHI">建筑企业<br />
        (注册地址)</label>
    </div></td>
    <td fieldname="QY_ZCDIZHI"></td>
    <td><label for="P10030_FAX">
      <div align="center">企业传真号</div>
    </label></td>
    <td fieldname="FAX"></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">
      <label for="P10030_QY_ZZ_CODE">建筑企业资质编号</label>
    </div></td>
    <td fieldname="QY_ZZ_CODE"></td>
    <td><label for="P10030_ZZ_ZS_LB">
      <div align="center">资质证书类别等级</div>
    </label></td>
    <td fieldname="ZZ_ZS_LB"></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">
      <label for="P10030_AQ_XKZ">安全生产许可证号</label>
    </div></td>
    <td fieldname="AQ_XKZ"></td>
    <td><label for="P10030_QY_ZX_DZ">
      <div align="center">建筑企业<br />
        （驻新区地址）</div>
    </label></td>
    <td fieldname="QY_ZX_DZ"></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">
      <label for="P10030_QY_ZX_WTR">建筑企业<br />
        （驻锡委托人）</label>
    </div></td>
    <td fieldname="QY_ZX_WTR"></td>
    <td><label for="P10030_ZX_PHONE">
      <div align="center">驻锡联系电话</div>
    </label></td>
    <td fieldname="ZX_PHONE"></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">
      <label for="P10030_JS_NAME">建设单位名称</label>
    </div></td>
    <td fieldname="JS_NAME"></td>
    <td><label for="P10030_JS_XZ">
      <div align="center">建设单位性质</div>
    </label></td>
    <td fieldname="JS_XZ"></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">
      <label for="P10030_PROJECTS_NAME">建设项目名称</label>
    </div></td>
    <td fieldname="PROJECTS_NAME"></td>
    <td><label for="P10030_GK_ZB_TZS">
      <div align="center">公开招标中标通知书或直接发包通知书编号</div>
    </label></td>
    <td fieldname="GK_ZB_TZS"></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">
      <label for="P10030_CB_XZ">承包性质</label>
    </div></td>
    <td fieldname="CB_XZ"></td>
    <td><label for="P10030_ZCB_DW">
      <div align="center">总承包单位</div>
    </label></td>
    <td fieldname="ZCB_DW"></td>
  </tr>
  <tr>
    <td colspan="2"><label for="P10030_JS_PROJECTS_DZ">建设项目所在地</label></td>
    <td fieldname="JS_PROJECTS_DZ"></td>
    <td><label for="P10030_CONTRACT_SG_NR">
      <div align="center">合同施工内容</div>
    </label></td>
    <td fieldname="CONTRACT_SG_NR"></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">
      <label for="P10030_GC_ZJ">工程造价（万元）</label>
    </div></td>
    <td fieldname="GC_ZJ"></td>
    <td><label for="P10030_JZ_MJ">
      <div align="center">建筑面积(㎡)</div>
    </label></td>
    <td fieldname="JZ_MJ"></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">
      <label for="P10030_JG_LEIXING">结构类型</label>
    </div></td>
    <td fieldname="JG_LEIXING"></td>
    <td><label for="P10030_CC_KD">
      <div align="center">层次或跨度</div>
    </label></td>
    <td fieldname="CC_KD" ></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">
      <label for="P10030_CONTRACT_GQ">合同工期（天）</label>
    </div></td>
    <td fieldname="CONTRACT_GQ"></td>
    <td><label for="P10030_CONTRACT_ZLDJ">
      <div align="center">合同质量等级</div>
    </label></td>
    <td fieldname="CONTRACT_ZLDJ"></td>
  </tr>
  <tr>
    <td width="60" rowspan="3"><div align="center">建造师<br />
    (项目经理)</div></td>
    <td width="85"><label for="P10030_JZS_NAME">
      <div align="center">姓名</div>
    </label></td>
    <td fieldname="JZS_NAME"></td>
    <td><label for="P10030_JZS_ZY">
      <div align="center">专业</div>
    </label></td>
    <td fieldname="JZS_ZY"></td>
  </tr>
  <tr>
    <td><label for="P10030_ZHENGSHU_CODE">
      <div align="center">证书编号</div>
    </label>    </td>
    <td fieldname="ZHENGSHU_CODE"></td>
    <td><label for="P10030_SFZ_NAMBER">
      <div align="center">身份证号</div>
    </label></td>
    <td fieldname="SFZ_NAMBER"></td>
  </tr>
  <tr>
    <td><div align="center">移动电话</div></td>
    <td fieldname="JZS_MOBILE"></td>
    <td><label for="P10030_JZS_PHONE">
      <div align="center">联系电话</div>
    </label></td>
    <td fieldname="JZS_PHONE"></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">
      <label for="P10030_BEGIN_DATE">开工日期</label>
    </div></td>
    <td fieldname="BEGIN_DATE"></td>
    <td><label for="P10030_END_DATE">
      <div align="center">竣工日期</div>
    </label></td>
    <td fieldname="END_DATE" ></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">
      <label for="P10030_SB_LIANXIREN">申报联系人</label>
    </div></td>
    <td fieldname="SB_LIANXIREN"></td>
    <td><label for="P10030_SBR_PHONE">
      <div align="center">申报人联系电话</div>
    </label></td>
    <td fieldname="SBR_PHONE"></td>
  </tr>
  <tr>
    <td colspan="5">
				     &nbsp;&nbsp;<span id="P10030_SM" style="font-size: 19px">申报声明</span><br>
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我单位保证以上填报提交的材料内容真实、有效，并对申请材料的真实性负责。如有虚假，承担法律责任。<br><br><br>
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
法定代表人（或法人委托人）签&nbsp;&nbsp;&nbsp;&nbsp;字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;企业公章


				     <br><br>                                                                                                                                                                                                           
				  <div >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
    </td>
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