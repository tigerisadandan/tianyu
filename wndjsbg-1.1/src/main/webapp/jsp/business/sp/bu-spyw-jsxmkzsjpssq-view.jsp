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

var controllername= "${pageContext.request.contextPath }/sp/buSpywJsxmkzsjpssqController/";
var controllerfile= "${pageContext.request.contextPath }/controller/fileController/";
var xmbh,id,json_xg,pch,zt;
id ="<%=id%>";

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
			url : controllername + "download?uid="+$("#KZSJPSSQ_UID").val(),
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
                checkbox(resultobj.NYQDX);    // 选中 checkbox
                $("#NYQDX_QT").html(resultobj.NYQDX_QT);
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
     <h4 class="title" align="center">新区建筑节能、绿色建筑及抗震与初步设计审查申请表</h4>
    <div align="center">  
     <table class="cc-alert-table"> 
     <INPUT type="hidden" class="span12"  id="KZSJPSSQ_UID" fieldname="KZSJPSSQ_UID" />
          <input type="hidden" id="ID" fieldname="ID" name = "ID"/></TD>
	  	  <input type="hidden" id="GC_TCJH_XMXDK_ID" fieldname="XMID" name = "XMID"/></TD>
        <tr >
			<td  class="right-border bottom-border text-right" width="110" >建设项目名称</td>
       	 	<td class="bottom-border right-border"fieldname="JSXMMC" >
          		<!-- <button class="btn btn-link"  type="button" onclick="selectXm()"><i class="icon-edit"></i></button>  -->
       	 	</td>
         	<td  class="right-border bottom-border text-right" width="110" >建设项目地点</td>
       		<td class="bottom-border right-border" fieldname="JSXMDD" >
         	</td>
        </tr>
        <tr >
			<td  class="right-border bottom-border text-right"  >建设项目性质</td>
       	 	<td class="bottom-border right-border" fieldname="JSDWWXZ"  >
          		<!-- <button class="btn btn-link"  type="button" onclick="selectXm()"><i class="icon-edit"></i></button>  -->
       	 	</td>
         	<td  class="right-border bottom-border text-right" >建设单位名称</td>
       		<td class="bottom-border right-border" fieldname="JSDWMC" >
         	</td>
        </tr>
        <tr >
			<td  class="right-border bottom-border text-right"  >联系人</td>
       	 	<td class="bottom-border right-border" fieldname="LXR" >
          		<!-- <button class="btn btn-link"  type="button" onclick="selectXm()"><i class="icon-edit"></i></button>  -->
       	 	</td>
         	<td  class="right-border bottom-border text-right" >联系电话</td>
       		<td class="bottom-border right-border" fieldname="LXDH" >
         	</td>
        </tr>
        <tr >
			<td  class="right-border bottom-border text-right"  >设计单位</td>
       	 	<td class="bottom-border right-border" fieldname="SJDW" >
          		<!-- <button class="btn btn-link"  type="button" onclick="selectXm()"><i class="icon-edit"></i></button>  -->
       	 	</td>
         	<td  class="right-border bottom-border text-right" >设计单位资质</td>
       		<td class="bottom-border right-border" fieldname="SJDWXZ" >
         	</td>
        </tr>
        <tr  class="certer"><td colspan="12">一、公司概况</td></tr>
        <tr >
			<td  class="right-border bottom-border text-right"  >单位成立时间</td>
       	 	<td class="bottom-border right-border" fieldname="DWCLSJ_DATE" >
          		<!-- <button class="btn btn-link"  type="button" onclick="selectXm()"><i class="icon-edit"></i></button>  -->
       	 	</td>
         	<td  class="right-border bottom-border text-right" >批准设立机关</td>
       		<td class="bottom-border right-border" fieldname="PZSLJG" >
         	</td>
        </tr>
        <tr >
			<td  class="right-border bottom-border text-right"  >环评批复意见书文号</td>
       	 	<td class="bottom-border right-border" fieldname="HPPFYJSWH" >
          		<!-- <button class="btn btn-link"  type="button" onclick="selectXm()"><i class="icon-edit"></i></button>  -->
       	 	</td>
         	<td  class="right-border bottom-border text-right" >方案审批意见书文号</td>
       		<td class="bottom-border right-border" fieldname="FASPYJSWH" >
         	</td>
        </tr>
        <tr >
			<td  class="right-border bottom-border text-right"  >主要经营范围</td>
       	 	<td class="bottom-border right-border"  fieldname="ZYJYFW" >
          		<!-- <button class="btn btn-link"  type="button" onclick="selectXm()"><i class="icon-edit"></i></button>  -->
       	 	</td>
         	<td  class="right-border bottom-border text-right" >投资总额(元)</td>
       		<td class="bottom-border right-border"  fieldname="TZZE" onkeyup="isMoney(this)" >
         	</td>
        </tr>
         <tr  class="certer" ><td colspan="4"  >二、扩初设计评审申请内容</td></tr>
        <tr >
			<td  class="right-border bottom-border text-right"  >扩初设计评审会时间</td>
       	 	<td class="bottom-border right-border" 	fieldname="KZSJPSHSJ_DATETIME"  >
          		<!-- <button class="btn btn-link"  type="button" onclick="selectXm()"><i class="icon-edit"></i></button>  -->
       	 	</td>
         	<td  class="right-border bottom-border text-right" >扩初设计评审会地点</td>
       		<td class="bottom-border right-border" fieldname="KZSJPSHDD"  >
         
         			</td>
        </tr>
        <tr height="15">
            <td  class="right-border bottom-border text-right" >拟邀请对象</td>
            <td></td><td></td><td></td> 
        </tr>
        <tr>
			
       	 	<td class="bottom-border right-border"  colspan="6" >
       	 	   <table class="cc-alert-table-zi" cellpadding="" cellspacing="">
         		<tr height="20">
                   <td width="33%;" style="padding-left: 30px;"><label class="checkbox inline" style="font-weight: normal;"><input type="checkbox" name="box" value="1" />市环保局</label> </td>
                   <td width="28%"  ><label class="checkbox inline" style="font-weight: normal;"><input type="checkbox" name="box" value="2"/>市质量技术监督局</label></td>
                   <td width="33%"  ><label class="checkbox inline" style="font-weight: normal;"><input type="checkbox" name="box" value="3"/>市卫生局卫生监督所</label></td>                
                </tr>
                <tr height="20">
                   <td style="padding-left: 30px;"><label class="checkbox inline" style="font-weight: normal;">  <input type="checkbox" name="box" value="4" />新区消防大队</label></td>
                   <td><label class="checkbox inline" style="font-weight: normal;"><input type="checkbox" name="box" value="5"/>新区经济发展局</label></td>
                   <td><label class="checkbox inline" style="font-weight: normal;"><input type="checkbox" name="box" value="6"/>新区安全生产监督局</label></td>                
                </tr>
                <tr height="20">
                   <td style="padding-left: 30px;"><label class="checkbox inline" style="font-weight: normal;"><input type="checkbox" name="box" value="7"/>新区墙改办</label></td>
                   <td><label class="checkbox inline" style="font-weight: normal;"><input type="checkbox" name="box" value="8"/>新区绿委办</label></td>
                   <td><label class="checkbox inline" style="font-weight: normal;"><input type="checkbox"  name="box" value="9"/>新区交巡警大队</label></td>                
                </tr>
                <tr height="20">
                   <td style="padding-left: 30px;"><label class="checkbox inline" style="font-weight: normal;"><input type="checkbox" name="box" value="10"/>新区供电分局</label></td>
                   <td><label class="checkbox inline" style="font-weight: normal;"><input type="checkbox" name="box" value="11" />新区电信分局</label> </td>
                   <td><label class="checkbox inline" style="font-weight: normal;"><input type="checkbox" name="box" value="12"/>新区自来水分公司</label></td>                
                </tr>
                <tr height="20">
                   <td style="padding-left: 30px;"><label class="checkbox inline" style="font-weight: normal;"><input type="checkbox" name="box" value="13"/>新区集中供热单位</label></td>
                   <td><label class="checkbox inline" ><input type="checkbox" name="box" value="14"/>街道社事管理部门</label></td>
                   <td colspan="1"><label class="checkbox inline" style="font-weight: normal;"><input type="checkbox" name="other" />其他</label><u><a type="text" id="NYQDX_QT" fieldname="NYQDX_QT" style="width:90px;height: 15px;" name="NYQDX_QT" /></u>
                   </td>      
                </tr>
                </table>
       	 	</td>
        </tr>
         <tr height="30" class="certer">
             <td colspan="4" >三、注意 事项</td>
        </tr>
        <tr>
                   <td colspan="4" class="text-left">
                       <span style="line-height: 15px;">1、建设单位必须委托资质的设计单位编制项目扩初设计;</span><br/>
                       <span style="line-height: 15px;">2、扩初设计文本必须符合建设项目扩初设计编制要求，各专篇、图纸和设计内容必须齐全;</span><br/>
                       <span style="line-height: 15px;">3、扩初设计文本一律制成A3纸张大小的规格；</span><br/>
                       <span style="line-height: 15px;">4、建设单位提前一周向我局提供扩初设计文本，并随图附上立面效果彩图以及</span><br/>
                       <span style="line-height: 15px;">1:500总平面图和管线图；</span><br/>
                       <span style="line-height: 15px;">5、建设单位提供的文本总数“为参加会议单位总数加三”。</span><br/>
                   </td>
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
								operation="=" />
		</TD>
	</TR>	
</form>
</body>
</html>