<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>承包商详情信息</title>
<%
	String COMPANY_UID=request.getParameter("COMPANY_UID");
%>

<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/wxgc/yxLhEnterpriseController";
var COMPANY_UID="<%=COMPANY_UID%>";
//页面初始化
$(function() {
	init();
	$("#btnClose").bind("click", function(){
		var a=$(this).manhuaDialog.close();
	});
	
    //置所有input 为disabled
	$(":input").each(function(i){
	   $(this).attr("disabled", "true");
	});
	 $("#btnClose").removeAttr('disabled');
});
//页面默认参数
function init(){
	$("#QLH_ENTERPRISE_UID").val(COMPANY_UID);

	//查询表单赋值
	var data = combineQuery.getQueryCombineData(buForm, frmPost);
	var data1 = {
		msg : data
	};
	$.ajax({
		url : controllername + "?query",
		data : data1,
		cache : false,
		async : false,
		dataType : "json",
		type : 'post',
		success : function(response) {				
			var res = dealSpecialCharactor(response.msg);
			$("#resultXML").val(response.msg);
			var resultobj = defaultJson.dealResultJson(res);
			$("#yxlhEnterpriseForm").setFormValues(resultobj);	

			//加载附件
			queryFileData(resultobj.LH_ENTERPRISE_UID, "", "", "777001");	
			return true;
		}
	});
	
}

</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">
  
	<div style="height:5px;"></div>
	<div class="B-small-from-table-autoConcise">
      <h4 class="title">
      	<span class="pull-right">
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>		
      	</span>
      </h4>
     <form action="post" id="buForm">  	
		 <input type="hidden" id="QLH_ENTERPRISE_UID" fieldname="LH_ENTERPRISE_UID" name = "LH_ENTERPRISE_UID" operation="="/>
	 </form>
     <form method="post" id="yxlhEnterpriseForm"  >
      <table class="B-table" width="100%" >
		<input type="hidden" id="LH_ENTERPRISE_UID" fieldname="LH_ENTERPRISE_UID" name = "LH_ENTERPRISE_UID"/>
        	<tr>
				<th width="8%" class="right-border bottom-border text-right">企业全称</th>
	       	 	<td class="bottom-border right-border" colspan="3">
	         		<input  id="COMPANY_NAME" check-type="required"  type="text" fieldname="COMPANY_NAME" name = "COMPANY_NAME" />
	       	 	</td> 
	       	 	      	
           </tr>
        
           <tr>
				<th width="8%" class="right-border bottom-border text-right">组织机构代码</th>
	       	 	<td class="bottom-border right-border" width="15%">
	         		<input  id="COMPANY_CODE"  type="text" fieldname="COMPANY_CODE" name = "COMPANY_CODE" />
	       	 	</td> 
	       	 	<th width="8%" class="right-border bottom-border text-right">附件</th>
	       	 	<td class="bottom-border right-border" width="15%">
	       	 		<table role="presentation" id="zzjg" class="table table-striped">
						<tbody fjlb="7791" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true"></tbody>
					</table>
	       	 	</td>        	
           </tr>
           <tr>
				<th width="8%" class="right-border bottom-border text-right">营业执照号</th>
	       	 	<td class="bottom-border right-border" width="15%">
	         		<input  id="ZHIZHAO"   type="text" fieldname="ZHIZHAO" name = "ZHIZHAO" />
	       	 	</td> 
	       	 	<th width="8%" class="right-border bottom-border text-right">税务登记号</th>
	       		<td class="bottom-border right-border" width="15%">
	         		<input  id="SHUIWU" type="text" fieldname="SHUIWU" name = "SHUIWU" />
	         	</td>      	
           </tr>
           
            <tr>
				<th width="8%" class="right-border bottom-border text-right">营业有效期</th>
	       	 	<td class="bottom-border right-border" width="15%">
	         		<input  id="ZHIZHAO_VALID"   type="text" fieldname="ZHIZHAO_VALID" name = "ZHIZHAO_VALID" class='Wdate' onClick='WdatePicker()'/>
	       	 	</td> 
	       	 	<th width="8%" class="right-border bottom-border text-right">附件</th>
	       	 	<td class="bottom-border right-border" width="15%">
	       	 		<table role="presentation" id="yyzz" class="table table-striped">
						<tbody fjlb="7792"  class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true">
						</tbody>
					</table>
	       	 	</td>        	
           </tr>
         <tr>
			<th width="8%" class="right-border bottom-border text-right">是否外地企业</th>
       		<td class="bottom-border right-border" width="15%">
         		<input  id="WAIDI_Y" type="radio" kind="dic" src="WAIDI_Y"  name="WAIDI_Y" fieldname="WAIDI_Y" defaultValue="N">
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">企业类型</th>
       		<td class="bottom-border right-border" width="15%">
         		<select  id="COMPANY_TYPE"   name="COMPANY_TYPE" fieldname="COMPANY_TYPE"  kind="dic" src="COMPANY_TYPE" defaultMemo="--请选择--">
				</select>
         	</td>
         </tr>
        
        
           <tr>
				<th width="8%" class="right-border bottom-border text-right">安全生产许可证编号</th>
	       	 	<td class="bottom-border right-border" width="15%">
	         		<input  id="ANQUAN_CODE"   type="text" fieldname="ANQUAN_CODE" name = "ANQUAN_CODE" />
	       	 	</td> 
	       	 	<th width="8%" class="right-border bottom-border text-right"></th>
	       		<td class="bottom-border right-border" width="15%">
	         	</td>      	
           </tr>
           
            <tr>
				<th width="8%" class="right-border bottom-border text-right">有效期</th>
	       	 	<td class="bottom-border right-border" width="15%">
	         		<input  id="ANQUAN_VALID"   type="text" fieldname="ANQUAN_VALID" name = "ANQUAN_VALID" class='Wdate' onClick='WdatePicker()'/>
	       	 	</td> 
	       	 	<th width="8%" class="right-border bottom-border text-right">附件</th>
	       	 	<td class="bottom-border right-border" width="15%">
	       	 		<table role="presentation"  class="table table-striped">
						<tbody fjlb="7793"  class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true">
						</tbody>
					</table>
	       	 	</td>        	
           </tr>
        
        
          <tr>
				<th width="8%" class="right-border bottom-border text-right">信用手册编号</th>
	       	 	<td class="bottom-border right-border" width="15%">
	         		<input  id="XINYONG_CODE"   type="text" fieldname="XINYONG_CODE" name = "XINYONG_CODE" />
	       	 	</td> 
	       	 	<th width="8%" class="right-border bottom-border text-right"></th>
	       		<td class="bottom-border right-border" width="15%">
	         	</td>      	
           </tr>
           
            <tr>
				<th width="8%" class="right-border bottom-border text-right">有效期</th>
	       	 	<td class="bottom-border right-border" width="15%">
	         		<input  id="XINYONG_VALID"   type="text" fieldname="XINYONG_VALID" name = "XINYONG_VALID" class='Wdate' onClick='WdatePicker()'/>
	       	 	</td> 
	       	 	<th width="8%" class="right-border bottom-border text-right">附件</th>
	       	 	<td class="bottom-border right-border" width="15%">
	       	 		<table role="presentation"  class="table table-striped">
						<tbody fjlb="7794"  class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true">
						</tbody>
					</table>
	       	 	</td>        	
           </tr>
          <tr>
			
         	<th width="8%" class="right-border bottom-border text-right">主项资质等级</th>
       		<td class="bottom-border right-border" colspan="3">
				<select  id="SG_ZIZHI_UID"   name="SG_ZIZHI_UID" fieldname="SG_ZIZHI_UID" defaultValue="34"  kind="dic"  src="T#SG_ZIZHI:SG_ZIZHI_UID:ZIZHI_NAME:1=1 order by SERIAL_NO" disabled ></select>
				-
				<select id="ZHU_DENGJI"  name="ZHU_DENGJI" fieldname="ZHU_DENGJI" kind="dic" src="T#SG_ZIZHI_DENGJI:SG_ZIZHI_DENGJI_UID:DENGJI_NAME:SG_ZIZHI_UID='34' order by DENGJI_NUMS  "></select>
         	</td>
         </tr>

            <tr>
				<th width="8%" class="right-border bottom-border text-right">主项资质编号</th>
	       	 	<td class="bottom-border right-border" width="15%">
	         		<input  id="ZHENGSHU_CODE"   type="text" fieldname="ZHENGSHU_CODE" name = "ZHENGSHU_CODE" class='Wdate' onClick='WdatePicker()'/>
	       	 	</td> 
	       	 	<th width="8%" class="right-border bottom-border text-right">附件</th>
	       	 	<td class="bottom-border right-border" width="15%">
	       	 		<table role="presentation"  class="table table-striped">
						<tbody fjlb="7795"  class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" onlyView="true">
						</tbody>
					</table>
	       	 	</td>        	
           </tr>
        
        
          <tr>
				<th width="8%" class="right-border bottom-border text-right">主项资质取得日期</th>
	       	 	<td class="bottom-border right-border" width="15%">
	         		<input  id="ZIZHI_DATE"   type="text" fieldname="ZIZHI_DATE" name = "ZIZHI_DATE" class='Wdate' onClick='WdatePicker()'/>
	       	 	</td> 
	       	 	<th width="8%" class="right-border bottom-border text-right">主项资质有效期</th>
	       	 	<td class="bottom-border right-border" width="15%">
	         		<input  id="VALID_DATE"   type="text" fieldname="VALID_DATE" name = "VALID_DATE" class='Wdate' onClick='WdatePicker()'/>
	       	 	</td>    	
           </tr>
	  	
      </table>
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
  <jsp:include page="/jsp/file_upload/fileuploadold_config_js.jsp" flush="true"/> 
</body>
</html>