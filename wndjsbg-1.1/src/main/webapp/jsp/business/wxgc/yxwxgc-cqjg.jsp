<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>微型工程-抽签结果</title>
<%
	String type=request.getParameter("type");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/wxgc/yxWxgcController";
var type ="<%=type%>";
//页面初始化
$(function() {
	init();

	$("#btnClose").bind("click", function(){
		var a=$(this).manhuaDialog.close();
	});

    <%
    if(type.equals("detail")){
	%>
	//置所有input 为disabled
	$(":input").each(function(i){
	   $(this).attr("disabled", "true");
	 });
	$("#btnClose").removeAttr('disabled');
	<%
		}
	%>
	
});
//页面默认参数
function init(){
	if(type == "insert"){
		
	}else if(type == "update" || type == "detail"){
		//生成json串
		var parentmain = $(window).manhuaDialog.getParentObj();
		var rowValue = parentmain.$("#resultXML").val();
		var tempJson = convertJson.string2json1(rowValue);
		$("#QWXGC_UID").val(tempJson.WXGC_UID);
		//查询表单赋值
		var data = combineQuery.getQueryCombineData(queryForm, frmPost);
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
				zblxfun(resultobj.ZBLX);
				$("#yxWxgcForm").setFormValues(resultobj);

				var PBFS_UID=resultobj.PBFS_UID;
				if(PBFS_UID!='1'){
					$("#ZBJ").val(resultobj.XCBJ);
				}else{
					$("#ZBJ").val(resultobj.ZBJ);	
				}
				
				return true;
			}
		});
		//加载附件
		queryFileData(tempJson.WXGC_UID, "", "", "777002");


		//列出审批意见信息
		var queryconditionXX = "{\"logic\":\"and\",\"fieldname\":\"WXGC_UID\",\"operation\":\"=\",\"value\":\""+tempJson.WXGC_UID+"\",\"fieldtype\":'',\"fieldformat\":''}";
		var queryconditionXX0 = "{\"logic\":\"and\",\"fieldname\":\"ZBJG\",\"operation\":\"!=\",\"value\":\"0\",\"fieldtype\":'',\"fieldformat\":''}";
		var dataXX = "{querycondition: {conditions: [" +queryconditionXX+","+queryconditionXX0+"]},pages:{recordsperpage:200, currentpagenum:1, totalpage:0, countrows:0},orders:[{\"fieldname\":\"ZBJG\",\"order\":\"asc\"}]}";
		//调用ajax插入
		defaultJson.doQueryJsonList("${pageContext.request.contextPath }/wxgc/yxWxgcCyzController?query", dataXX, spyjList);
	}
}

function zblxfun(v){
	if(v=='2'){
		$("#zbdllabelid").hide();
		$("#zbdldivid").hide();
	}else if(v=='1'){
		$("#zbdllabelid").show();
		$("#zbdldivid").show();
	}
}


</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">
	<div class="B-small-from-table-autoConcise">
		<h4 class="title">
      	<span class="pull-right"> 
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>   		
      	</span>
      </h4>
     <form method="post" id="yxWxgcForm"  >
      <table class="B-table" width="100%" >
   		<div class="modal-header" style="background:#0866c6;">
			<h3 style="color:white;">抽签结果信息</h3>
		</div>
		<tr>
			<td  colspan="4">
				<div class="overFlowX">
			 		<table width="100%" class="table-hover table-activeTd B-table" id="spyjList" type="single" pageNum="2000"  noPage="true">
					    <thead>
					 		<tr style="display:blank">
					           <th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
					            <th fieldname="COMPANYNAME" style="width:200px;" colindex=1 tdalign="center" >中标企业</th>
								<th fieldname="ZBJG_SV" colindex=2 tdalign="center" style="width:20px;">&nbsp;中标结果&nbsp;</th>
								<th fieldname="XMFZR" colindex=2 tdalign="center" style="width:20px;">&nbsp;项目负责人&nbsp;</th>
								<th fieldname="ZYZGZH" colindex=2 tdalign="center" style="width:35px;">&nbsp;职业资格证号&nbsp;</th>
					        </tr>
					    </thead>
					    <tbody></tbody>
				  	</table>  
	       		</div>
			</td>
		</tr>
		<tr>
		<td  colspan="4">
		<div class="modal-header" style="background:#0866c6;">
			<h3 style="color:white;">工程信息</h3>
		</div>
		</td>
		</tr>
	  		<input type="hidden" id="WXGC_UID" fieldname="WXGC_UID" name = "WXGC_UID"/></TD>
        <tr>
         	
         	<th width="8%" class="right-border bottom-border text-right">工程类型</th>
       	 	<td class="bottom-border right-border"   width="15%">
          		<select id="GC_TYPE_CODE" fieldname="GC_TYPE_CODE" name="GC_TYPE_CODE" kind="dic" src="T#YX_GC_TYPE:GC_TYPE_CODE:GC_TYPE_NAME:ENABLED='1' order by SERIAL_NO asc " ></select>
       	 	</td>
       	 	<div id="gcbhdivid" style="display:none">
	       	 	<th width="8%" class="right-border bottom-border text-right">工程编号</th>
	       	 	<td class="bottom-border right-border"   width="15%">
	       	 		<input id="GC_CODE" type="text"  fieldname="GC_CODE" name = "GC_CODE"  />
	       	 	</td>
       	 	</div>
        </tr>
         <tr>
			<th width="8%" class="right-border bottom-border text-right">工程名称</th>
       	 	<td class="bottom-border right-border"  colspan="3">
         		<input id="GC_NAME" type="text" check-type="required" fieldname="GC_NAME" name = "GC_NAME" class="span12" style="width:50%" />
       	 	</td>
        </tr>
        
         <tr>
			<th width="8%" class="right-border bottom-border text-right">批准机构</th>
       	 	<td class="bottom-border right-border"  width="15%">
         		<input id="XMSPJG" type="text" fieldname="XMSPJG" name = "XMSPJG"  />
       	 	</td>
         	<th width="8%" class="right-border bottom-border text-right">批准文件</th>
       		<td class="bottom-border right-border"width="15%">
         		<input id="XMSPBH" type="text"  fieldname="XMSPBH" name = "XMSPBH"  />
         	</td>
        </tr>
         <tr>
        	<th width="8%" class="right-border bottom-border text-right">审批材料</th>
	         <td class="bottom-border right-border"  colspan="3">
	         	 <table role="presentation"  class="table table-striped">
					<tbody fjlb="7761" id="gccltbody" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery">
					</tbody>
				 </table>
		     </td>
	         
	      </tr>
         <tr>
			<th width="8%" class="right-border bottom-border text-right">招标类型</th>
       	 	<td class="bottom-border right-border"  width="15%">
         		<input  id="ZBLX" type="radio" kind="dic" src="WXGCZBLX"  name="ZBLX" fieldname="ZBLX" defaultValue="1">
       	 	</td>
         	<th id="zbdllabelid" width="8%" class="right-border bottom-border text-right">招标代理机构</th>
       		<td id="zbdldivid" class="bottom-border right-border" width="15%">
         		<select id="ZTB_ZBDL_UID" fieldname="ZTB_ZBDL_UID" name="ZTB_ZBDL_UID" kind="dic" src="T#ZTB_ZBDL:ZTB_ZBDL_UID:ZBDL_NAME:ENABLED='1' order by ZBDL_NAME asc "  ></select>
         	</td>
        </tr>
         <tr>
			<th width="8%" class="right-border bottom-border text-right">建设单位</th>
       	 	<td class="bottom-border right-border"  colspan="3">
         		<input id="JSDW" type="text" fieldname="JSDW" name = "JSDW"  class="span12" style="width:50%"/>
       	 	</td>
        </tr>
        
       <tr>
			<th width="8%" class="right-border bottom-border text-right">计划开工日期</th>
       		<td class="bottom-border right-border" width="15%">
         		<input id="JHKGRQ" type="text"  fieldname="JHKGRQ" name = "JHKGRQ" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class='Wdate' />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">计划竣工日期</th>
       		<td class="bottom-border right-border" width="15%">
         		<input id="JHJGRQ" type="text"  fieldname="JHJGRQ" name = "JHJGRQ" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class='Wdate'  />
         	</td>
        </tr>
       
        <tr>
         	<th width="8%" class="right-border bottom-border text-right">计划工期</th>
       		<td class="bottom-border right-border"width="15%">
         		<input id="JHGQ" type="text"  fieldname="JHGQ" name = "JHGQ" />天
         	</td>
         
        </tr>
          <tr>
         	<th width="8%" class="right-border bottom-border text-right">资金来源</th>
       		<td class="bottom-border right-border" width="15%">
         		<select  id="ZJLY"   name="ZJLY" fieldname="ZJLY" kind="dic" src="WXGC_ZJLY" defaultMemo="--请选择--"></select>
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">明标价</th>
       		<td class="bottom-border right-border"width="15%">
         		<input id="ZBJ" type="text"  fieldname="ZBJ" name = "ZBJ"  />元
         	</td>
         	
        </tr>
         <tr>
			<th width="8%" class="right-border bottom-border text-right">工程地址</th>
       	 	<td class="bottom-border right-border" colspan="3">
          		<input id="JSDZ" type="text" fieldname="JSDZ" name = "JSDZ" class="span12" style="width:50%" />
       	 	</td>
         	
        </tr>
          <tr>
			<th width="8%" class="right-border bottom-border text-right">招标内容</th>
       	 	<td class="bottom-border right-border"  colspan="3">
         		<textarea class="span12" style="width:50%" rows="2" id="XMZBFW"  fieldname="XMZBFW" name="XMZBFW"></textarea>
       	 	</td>
         	
        </tr>
        <tr>
			<th width="8%" class="right-border bottom-border text-right">投标单位资质</th>
       	 	<td class="bottom-border right-border" colspan="3">
          		<input id="ZBDWZGYQ" type="text" fieldname="ZBDWZGYQ" name = "ZBDWZGYQ"   class="span12" style="width:50%"  />
       	 	</td>
        </tr>
           <tr>
			<th width="8%" class="right-border bottom-border text-right">公告(报名)时间</th>
       	 	<td class="bottom-border right-border"  width="15%">
         		<input id="GGKSRQ" type="text"  fieldname="GGKSRQ" name = "GGKSRQ" fieldformat='yyyy-MM-dd HH:mm' class='Wdate' onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  />
       	 	</td>
         	<th width="8%" class="center">~</th>
       		<td class="bottom-border right-border"width="15%">
         		<input id="GGJZRQ" type="text"  fieldname="GGJZRQ" name = "GGJZRQ"  fieldformat='yyyy-MM-dd HH:mm' class='Wdate' onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  />
         	</td>
        </tr>
        
        
        
         <tr>
			<th width="8%" class="right-border bottom-border text-right">开标时间</th>
       	 	<td class="bottom-border right-border"  colspan="3">
         		<input id="KBRQ" type="text"  fieldname="KBRQ" name = "KBRQ" fieldformat='yyyy-MM-dd HH:mm' onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class='Wdate'  />
       	 	</td>
         	
        </tr>
          <tr>
			<th width="8%" class="right-border bottom-border text-right">开标地址</th>
       	 	<td class="bottom-border right-border"  colspan="3">
         		<input id="KBDZ" type="text"  fieldname="KBDZ" name = "KBDZ" class="span12" style="width:50%"/>
       	 	</td>
         	
        </tr>
        
        <tr>
	        <th width="8%" class="right-border bottom-border text-right">其他要求</th>
	        <td colspan="3" class="bottom-border right-border" >
	        	<textarea class="span12" style="width:50%" rows="2" id="DESCRIBE"  fieldname="DESCRIBE" name="DESCRIBE"></textarea>
	        </td>
        </tr>
           <div>					
						<table class="table table-bordered">
							<thead>
						      <tr style="background:#0866c6;">
						         <th width="20%" class="text-center" style="color:white;">材料类型</th>
						         <th width="80%" class="text-center" style="color:white;">文件</th>
						      </tr>
						   </thead>
						   <tbody>
						      
						      <tr>
						         <td class="text-center">工程清单材料</td>
						         <td>
						         	<table role="presentation"  class="table table-striped">
										<tbody fjlb="7762" id="gccltbody" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery">
										</tbody>
									 </table>
						         </td>
						        
						      </tr>
						      <tr>
						         <td class="text-center">工程图纸材料</td>
						         <td>
						         	<table role="presentation"  class="table table-striped">
										<tbody fjlb="7763" id="gccltbody" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery">
										</tbody>
									 </table>
						         </td>
						        
						      </tr>
						   </tbody>
						</table>
					</div>
      </table>
    
      </form>
      

    </div>
   </div>
  </div>
    <form method="post" id="queryForm"  >
    	  <input type="hidden" id="QWXGC_UID" fieldname="WXGC_UID" name = "WXGC_UID" operation="="/>
     </form>
	
  <div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id = "queryXML">
         <input type="hidden" name="txtXML" id = "txtXML">
         <input type="hidden" name="txtFilter"  order="desc" fieldname = "WXGC_UID" id = "txtFilter">
         <input type="hidden" name="resultXML" id = "resultXML">
       	 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
 	</FORM>
 </div>
  <jsp:include page="/jsp/file_upload/fileuploadold_config_js.jsp" flush="true"/> 
</body>
</html>