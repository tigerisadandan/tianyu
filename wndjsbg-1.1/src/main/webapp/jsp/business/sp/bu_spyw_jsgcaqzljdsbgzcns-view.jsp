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
var controllername= "${pageContext.request.contextPath }/sp/buSpywJsgcaqzljdsbgzcnsController/";
var controllerfile= "${pageContext.request.contextPath }/controller/fileController/";



//初始化加载
$(document).ready(function(){
$("#pubAlert").hide();
$("input:checkbox").attr("disabled","true");
	query();
	 $("#btnClose").click(function(){
		    window.close();
		    });
		    $("#btnSave").click(function(){
		    $.ajax({
			url : controllername + "download?uid="+$("#AQZLJDSBGZCNS_UID").val(),
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
	$("#YWLZ_UID").val(id); //查询 绑定 ID
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
				checkbox(resultobj.GCGK_GCLX_XZ,"box1");
					checkbox(resultobj.GCGK_GCLX_XKG,"box2");
					checkbox(resultobj.GCGK_GCLX_TZLX,"box3");
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
function checkbox(NYQDX,name){
    var a=NYQDX;
	$("input:checkbox[name='"+name+"']").each(function(){
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
      <h4 class="title" align="center">安全、质量监督申报告知承诺书 </h4> 	
    <div align="center">  
    <INPUT type="hidden"  id="AQZLJDSBGZCNS_UID" fieldname="AQZLJDSBGZCNS_UID"  />
      <table   class="AQZJBG-table"  > 
  <tr> 
    <td width="58" rowspan="6" align="center">
    <p>工程</p>
    <p>概况</p>
    
    <td width="100" rowspan="3" align="center">工程<br/>类型</td>
   
    <td colspan="6">
       &nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="box1" value="1"/>公共建筑</label>&nbsp;&nbsp;
        <input type="checkbox" name="box1" value="2"/>住宅工程</label>&nbsp;&nbsp;
        <input type="checkbox" name="box1" value="3"/>工业建筑</label>&nbsp;&nbsp;
        <input type="hidden" id="GCGK_GCLX_XZ" fieldname="GCGK_GCLX_XZ" name = "GCGK_GCLX_XZ"   />
      </td>
  </tr>
  <tr>
    <td colspan="6">
         &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;<input type="checkbox" name="box2" value="1"/>新建</label>&nbsp;&nbsp;
        <input type="checkbox" name="box2" value="2"/>改建</label>&nbsp;&nbsp;
        <input type="checkbox" name="box2" value="3"/>扩建</label>&nbsp;&nbsp;
        <input type="hidden" id="GCGK_GCLX_XKG" fieldname="GCGK_GCLX_XKG" name = "GCGK_GCLX_XKG"   /></td>
  </tr>
  <tr>
    <td colspan="6">
        &nbsp;&nbsp;<input type="checkbox" name="box3" value="1"/>政府投资项目</label>           
        <input type="checkbox" name="box3" value="2"/>非政府投资项目</label>&nbsp;
        <input type="checkbox" name="box3" value="3"/>外资项目</label> &nbsp;
        <input type="hidden" id=GCGK_GCLX_TZLX fieldname="GCGK_GCLX_TZLX" name = "GCGK_GCLX_TZLX"   /></td>
  </tr>
  <tr>
    <td  colspan="2">建筑面积</td>
    <td colspan="2" fieldname="GCGK_JZMJ"></td>
    <td width="141">层次</td>
     <td colspan="2" fieldname="GCGK_CC"></td>
  </tr>
  <tr>
    <td colspan="2">结构类型</td>
     <td colspan="2" fieldname="GCGK_JGLX"></td>
    <td>合同造价</td>
     <td colspan="2" fieldname="GCGK_HTZJ"></td>
  </tr>
  <tr>
    <td colspan="2">合同开工日期</td>
     <td colspan="2" fieldname="GCGK_HTKGRQ_DATE"></td>
    <td>合同竣工日期</td>
     <td colspan="2" fieldname="GCGK_HTJGRQ_DATE"></td>
  </tr>
  <tr>
    <td rowspan="5" align="center">
    <p> 建设</p>
    <p>单位</p>  
    <p>信息</p>  </td>
    <td colspan="2">名称</td>
     <td colspan="2" fieldname="JS_MC"></td>
    <td>组织机构代码证号</td>
     <td colspan="2" fieldname="JS_ZZJGDMZH"></td>
  </tr>
  <tr>
    <td colspan="2">法人代表</td>
     <td colspan="2" fieldname="JS_FRDB"></td>
    <td>地址</td>
     <td colspan="2" fieldname="JS_DZ"></td>
  </tr>
  <tr>
    <td colspan="2">资质等级</td>
     <td colspan="2" fieldname="JS_ZZDJ"></td>
    <td>资质证书编号</td>
     <td colspan="2" fieldname="JS_ZZZSBH"></td>
  </tr>
  <tr>
    <td colspan="2">项目负责人</td>
     <td colspan="2" fieldname="JS_XMFZR"></td>
    <td>联系电话</td>
     <td colspan="2" fieldname="JS_LXDH"></td>
  </tr>
  <tr>
    <td colspan="2">安全生产负责人</td>
     <td colspan="2" fieldname="JS_AQSCZRR"></td>
    <td>联系电话</td>
     <td colspan="2"  fieldname="JS_AQ_LXDH"> </td>
  </tr>
  <tr>
    <td rowspan="5" align="center">
      <p>勘察</p>   
      <p>单位</p>
      <p>信息</p>
</td>
    <td colspan="2">名称</td>
     <td colspan="2" fieldname="KC_MX" ></td>
    <td>组织机构代码证号</td>
     <td colspan="2" fieldname="KC_ZZJGDMZH"></td>
  </tr>
  <tr>
    <td colspan="2">法人代表</td>
     <td colspan="2" fieldname="KC_FRDB"> </td>
    <td>地址</td>
     <td colspan="2" fieldname="KC_DZ"></td>
  </tr>
  <tr>
    <td colspan="2">资质等级</td>
     <td colspan="2" fieldname="KC_ZZDJ"> </td>
    <td>资质证书编号</td>
     <td colspan="2" fieldname="KC_ZZZSBH"></td>
  </tr>
  <tr>
    <td colspan="2">项目负责人</td>
     <td colspan="2"  fieldname="KC_XMFZR" > </td>
    <td>联系电话</td>
     <td colspan="2" fieldname="KC_LXDH"></td>
  </tr>
  <tr>
    <td colspan="2">安全生产负责人</td>
     <td colspan="2" fieldname="KC_AQSCZRR"></td>
    <td>联系电话</td>
     <td colspan="2" fieldname="KC_AQ_LXDH"></td>
  </tr>
  <tr>
    <td rowspan="6" align="center">
     <p>设计</p>
     <p>单位</p>
     <p>信息</p>    
    <td colspan="2">名称</td>
     <td colspan="2" fieldname="SJ_MC"></td>
    <td>组织机构代码证号</td>
     <td colspan="2" fieldname="SJ_ZZJGDMZH"></td>
  </tr>
  <tr>
    <td colspan="2">法人代表</td>
     <td colspan="2" fieldname="SJ_FRBC"> </td>
    <td>地址</td>
     <td colspan="2" fieldname="SJ_DZ"> </td>
  </tr>
  <tr>
    <td colspan="2">资质等级</td>
     <td colspan="2" fieldname="SJ_ZZDJ"> </td>
    <td>资质证书编号</td>
     <td colspan="2" fieldname="SJ_ZZZSBH" ></td>
  </tr>
  <tr>
    <td colspan="2">项目负责人</td>
     <td colspan="2"  fieldname="SJ_XMFZR"></td>
    <td>联系电话</td>
     <td colspan="2"  fieldname="SJ_LXDH"> </td>
  </tr>
  <tr>
    <td colspan="2" rowspan="2">建筑工程师</td>
    <td width="45">姓名</td>
    <td width="90"  fieldname="SJ_JZGCS_XM"> </td>
    <td rowspan="2">结构工程师</td>
    <td width="45">姓名</td>
    <td width="64"  fieldname="SJ_JGGCS_XM"></td>
  </tr>
  <tr>
    <td>资格等级</td>
    <td fieldname="SJ_JZGCS_ZZ" > </td>
    <td>资格等级</td>
    <td fieldname="SJ_JGGCS_ZZ"></td>
  </tr>
  <tr>
    <td rowspan="5" align="center">
     <p>监理</p>
     <p>单位</p>
     <p>信息</p>
    <p></p></td>
    <td colspan="2">名称</td>
     <td colspan="2" fieldname="JL_MC" ></td>
    <td>组织机构代码证号</td>
     <td colspan="2" fieldname="JL_MCZZJGDMZH" > </td>
  </tr>
  <tr>
    <td colspan="2">法人代表</td>
     <td colspan="2" fieldname="JL_FRDB"></td>
    <td>地址</td>
     <td colspan="2" fieldname="JL_DZ"></td>
  </tr>
  <tr>
    <td colspan="2">资质等级</td>
     <td colspan="2" fieldname="JL_ZZDJ"></td>
    <td>资质证书编号</td>
     <td colspan="2" fieldname="JL_ZZZSBH"></td>
  </tr>
  <tr>
    <td colspan="2">总监理工程师</td>
     <td colspan="2" fieldname="JL_ZJLGCS"> </td>
    <td>联系电话</td>
     <td colspan="2" fieldname="JL_LXDH"></td>
  </tr>
  <tr>
    <td colspan="2">安全监理工程师</td>
     <td colspan="2" fieldname="JL_AQJLGCS" ></td>
    <td>联系电话</td>
     <td colspan="2" fieldname="JL_AQ_LXDH"></td>
  </tr>
  <tr>
    <td rowspan="10" align="center">
      <p> 施工</p>  
      <p> 单位</p>
      <p> 信息</p> 
      <p> </p> </td>
    <td colspan="2">名称</td>
     <td colspan="2"  fieldname="SG_MC"></td>
    <td>组织机构代码证号</td>
     <td colspan="2" fieldname="SG_ZZJGDMZH" >  </td>
  </tr>
  <tr>
    <td colspan="2">法人代表</td>
     <td colspan="2" fieldname="SG_FRDB"> </td>
    <td>地址</td>
     <td colspan="2" fieldname="SG_DZ"></td>
  </tr>
  <tr>
    <td colspan="2">资质等级</td>
     <td colspan="2" fieldname="SG_ZZDJ"></td>
    <td>资质证书编号</td>
     <td colspan="2" fieldname="SG_ZZZSBH"> </td>
  </tr>
  <tr>
    <td colspan="2">项目负责人</td>
     <td colspan="2" fieldname="SG_XMFZR" > </td>
    <td>注册证号</td>
     <td colspan="2" fieldname="SG_ZCZH" ></td>
  </tr>
  <tr>
    <td colspan="2">联系电话</td>
     <td colspan="2"  fieldname="SG_LXDH"></td>
    <td>安全考核证号</td>
     <td colspan="2" fieldname="SG_AQKHZH"></td>
  </tr>
  <tr>
    <td colspan="2">技术负责人</td>
     <td colspan="2"  fieldname="SG_JSFZR"></td>
    <td>联系电话</td>
     <td colspan="2"  fieldname="SG_JS_LXDH" >  </td>
  </tr>
  <tr>
    <td colspan="2">安全员1</td>
     <td colspan="2" fieldname="SG_AQY1" ></td>
    <td>安全员2</td>
     <td colspan="2"  fieldname="SG_AQY2">  </td>
  </tr>
  <tr>
    <td colspan="2">安全员3</td>
     <td colspan="2" fieldname="SG_AQY3" ></td>
    <td>施工员1</td>
     <td colspan="2" fieldname="SG_SGY1"> </td>
  </tr>
  <tr>
    <td colspan="2">施工员2</td>
     <td colspan="2" fieldname="SG_SGY2" > </td>
    <td>施工员3</td>
     <td colspan="2" fieldname="SG_SGY3"> </td>
  </tr>
  <tr>
    <td colspan="2">质检员1</td>
     <td colspan="2" fieldname="SG_ZJY1"></td>
    <td>质检员2</td>
     <td colspan="2"  fieldname="SG_ZJY2"></td>
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