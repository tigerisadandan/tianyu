
<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/sp/xAlert.css"> </LINK>
<!-- 
<script type="text/javascript" src="${pageContext.request.contextPath }/js/base/jquery.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/base/LockingTable.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/base/bootstrap.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/base/jsBruce.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/Form2Json.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/convertJson.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/combineQuery.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/default.js?version=20131206"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/TabList.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/bootstrap-validation.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/loadFields.js?version=20131206"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common.js"></script> -->
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
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/sp/css/bootstrap.css?version=20131201"></LINK>


<title></title>
<%
	//String id=request.getParameter("id");
    String id=(String)request.getAttribute("id");
    String projects_uid=(String)request.getAttribute("projects_uid");
	String js_company_uid=(String)request.getAttribute("js_company_uid");
	String filename=(String)request.getAttribute("filename");
	
%>
<script src="${pageContext.request.contextPath}/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/bzwj/buSpywJyfwfsfJsgcjktzdController/";
var controllerfile= "${pageContext.request.contextPath }/controller/fileController/";
var contextPath="${pageContext.request.contextPath }";
var type ="";
var id="<%=id%>";
var projects_uid="<%=projects_uid%>";
var js_company_uid="<%=js_company_uid%>";
var filename="<%=filename %>"
//页面初始化
$(function() {
	$("#pubAlert").hide();
    $("#JYFWFSF_JSGCJKTZD_UID").val(id);
	query();
	 $("#btnClose").click(function(){
		    window.close();
	 });
	//按钮绑定事件(保存)
	$("#btnSave2").click(function() {  //打印
        $.ajax({
			url : controllername + "download?uid="+$("#JYFWFSF_JSGCJKTZD_UID").val()+"&filename="+filename,
			cache : false,
			async : false,
			dataType : "json",
			type : 'post',
			success : function(response) {
				window.open(controllerfile+"download2?path="+response,'文件下载');
			 } 
		    });
	});
});


function query(){ 
			//查询表单赋值       
			var data = combineQuery.getQueryCombineData(scxmmjhsdForm,frmPost);
			var data1 = { 	msg : data};
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
						$("#btnSave2").hide();
						}
					else{
						type="update";
					var obj = defaultJson.dealResultJson(res);
				     $("#BDBH").html(obj.BDBH);
				     $("#XMMC").html(obj.XMMC);
				     $("#BDMC").html(obj.BDMC);
				     $("#HTJE").html(obj.HTJE);
				     $("#JYFWF").html(obj.JYFWF);
				     $("#SKR").html(obj.SKR);
				     $("#KHH").html(obj.KHH);
				     $("#SKZH").html(obj.SKZH);
				     $("#JSDW").html(obj.JSDW);
				     $("#CBR").html(obj.CBR);
				     $("#JSDWYJFY").html(obj.JSDWYJFY);
				     $("#JSDWJKJBRQZ").html(obj.JSDWJKJBRQZ);
				     $("#JSDWSJBH").html(obj.JSDWSJBH);
				     $("#JSDWFWZXQZ").html(obj.JSDWFWZXQZ);
				     $("#JSDWRQ").html(obj.JSDWRQ);
				     $("#CBRYJFY").html(obj.CBRYJFY);
				     $("#CBRJKJBRQZ").html(obj.CBRJKJBRQZ);
				     $("#CBRSJBH").html(obj.CBRSJBH);
				     $("#CBRFWZXQZ").html(obj.CBRFWZXQZ);
				     $("#CBRRQ").html(obj.CBRRQ);
				     
				     
					}
				} 
			});

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
      	<TR  style="">
	        <TD class="right-border bottom-border">
	        <INPUT type="hidden" class="cc-input1" kind="JYFWFSF_JSGCJKTZD_UID" id="QID" fieldname="YWLZ_UID"
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
	  		<button id="btnSave2"  class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">打印</button>
      	</span><br/>
	     
     	  <form method="post" id="scxmmjhsdForm"  >
	        <input   type="hidden" id="JYFWFSF_JSGCJKTZD_UID" fieldname="JYFWFSF_JSGCJKTZD_UID" name="JYFWFSF_JSGCJKTZD_UID" operation="="/>
     	 </form>
        	
     <form method="post" id="buSpywZzhydjForm"   > 
      <h4 class="title" align="center">建设工程交易服务费缴款通知单（施工单位直接发包）</h4>
        
            <input type="hidden" id="JYFWFSF_JSGCJKTZD_UID" fieldname="JYFWFSF_JSGCJKTZD_UID" name = "JYFWFSF_JSGCJKTZD_UID"/>
	  	  <!-- <input type="hidden" id="GC_TCJH_XMXDK_ID" fieldname="XMID" name = "XMID"/>
	  	      <input type="hidden" id="YWLZ_UID" fieldname="YWLZ_UID" value="0" name = "YWLZ_UID"/> -->
	  	      <input type="hidden" id="LZBZ_WJ_UID" fieldname="LZBZ_WJ_UID" name = "LZBZ_WJ_UID"/>
	<div align="center">  
  <table  style="width: 800px;font-size: 17px;"  border="1" class="cc-alert-table" > 
               <tr style="height: 35px;"> 
                   <td width="25%" style="text-align: right;" >标段编号：</td>  
                   <td width="75%" colspan="3" ><span id="BDBH"></td>  
               </tr>
               <tr style="height: 35px;"> 
                   <td width="25%" style="text-align: right;" >项目名称：</td>  
                   <td width="75%" colspan="3" ><span id="XMMC"></td>  
               </tr>
               <tr style="height: 35px;"> 
                   <td width="25%" style="text-align: right;" >标段名称：</td>  
                   <td width="75%" colspan="3" ><span id="BDMC"></td>  
               </tr>
               <tr style="height: 35px;"> 
                   <td width="25%" style="text-align: right;" >合同金额（万元）：</td>  
                   <td width="25%" ><span id="HTJE"></td>  
                   <td width="25%" style="text-align: right;" >交易服务费（元）：</td>  
                   <td width="25%" ><span id="JYFWF"></td>
               </tr>
              <tr style="height: 35px;"> 
                 <td width="25%" style="text-align: right;" >收款人：</td>  
                 <td width="75%" colspan="3" >无锡市人民政府新区管理委员会财政局</td>  
              </tr>
              <tr style="height: 35px;"> 
                  <td width="25%" style="text-align: right;" >开户行：</td>  
                  <td width="75%" colspan="3" >中信银行新区支行</td> 
              </tr>
              <tr style="height: 35px;" > 
                <td width="25%" style="text-align: right;" >收款账户：</td>  
                <td width="75%" colspan="3" >7352310182600002786</td> 
              </tr>
              <tr style="height: 35px;"> 
                   <td width="25%" colspan="2" style="text-align:center;" ><span id="JSDW"></span></td>  
                    
                   <td width="25%" colspan="2" style="text-align: center;" ><span id="CBR"></span></td>  
                   
               </tr>
               <tr style="height: 35px;"> 
                   <td width="25%" style="text-align: right;" >应缴费用：</td>  
                   <td width="25%" ><span id="JSDWYJFY"></span>&nbsp;元</td>  
                   <td width="25%" style="text-align: right;" >应缴费用：</td>  
                   <td width="25%" ><span id="CBRYJFY"></span> &nbsp;元</td>
               </tr>
               <tr style="height: 35px;"> 
                   <td width="25%" style="text-align: right;"  >缴款经办人签字：</td>  
                   <td width="25%" ><span id="JSDWJKJBRQZ"></span></td>  
                   <td width="25%" style="text-align: right;"  >缴款经办人签字：</td>  
                   <td width="25%" ><span id="CBRJKJBRQZ"></td>
               </tr>
               <tr style="height: 35px;"> 
                   <td width="25%" style="text-align: right;" >收据编号：</td>  
                   <td width="25%" ><span id="JSDWSJBH"></td>  
                   <td width="25%" style="text-align: right;" >收据编号：</td>  
                   <td width="25%" ><span id="CBRSJBH"></td>
               </tr>
               <tr style="height: 35px;"> 
                   <td width="25%" style="text-align: right;">服务中心签字：</td>  
                   <td width="25%" ><span id="JSDWFWZXQZ"></td>  
                   <td width="25%" style="text-align: right;">服务中心签字：</td>  
                   <td width="25%" ><span id="CBRFWZXQZ"></td>
               </tr>
                <tr style="height: 35px;"> 
                   <td width="25%" style="text-align: right;" >日期：</td>  
                   <td width="25%" ><span id="JSDWRQ"></td>  
                   <td width="25%" style="text-align: right;" >日期：</td>  
                   <td width="25%" ><span id="CBRRQ"></td>
               </tr>
</table><br/>
  <div align="left" style="width: 800px;" >  
注意事项：<br/>
1、缴款单位经办人持本缴款通知单至指定银行缴费。<br/>
2、经确认到账后，缴款单位经办人携以下资料至无锡市建设工程交易管理中心新区服务点（无锡新区长江路7号 无锡科技创业园四区一楼）开取交易服务费收据，并请交易服务中心经办人签字确认：
   ①本缴款通知单；
②缴款凭证（原件或复印件）；<br/>
3、建设单位持经交易服务中心经办人签字确认后的本通知单，至建设局工程建设处领取直接发包合同备案表。<br/>
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