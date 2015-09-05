<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>微型工程-预选承包商评价信息</title>
<%
	String type=request.getParameter("type");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/wxgc/yxYxcbsXypjController";
var type ="<%=type%>";
//页面初始化
$(function() {
	init();

	$("#btnClose").bind("click", function(){
		var a=$(this).manhuaDialog.close();
	});

	$("#btnPrint").bind("click", function(){
		window.open('${pageContext.request.contextPath }/wxgc/yxYxcbsXypjController?getYxcbsXypjFile&XYPJ_UID='+$("#XYPJ_UID").val()+"&timestamp=" + new Date().getTime());
		return;
	});

    <%
    if(type.equals("detail")){
	%>
	//置所有input 为disabled
	$(":input").each(function(i){
	   $(this).attr("disabled", "true");
	 });
	$("#btnClose").removeAttr('disabled');
	$("#btnPrint").removeAttr('disabled');
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
		$("#QXYPJ_UID").val(tempJson.XYPJ_UID);
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
				$("#yxWxgcForm").setFormValues(resultobj);	
				var PBFS_UID=resultobj.PBFS_UID;
				if(PBFS_UID!='1'){
					$("#ZBJ").val(resultobj.XCBJ);
				}
				return true;
			}
		});
		//列出审批意见信息
		var queryconditionXX = "{\"logic\":\"and\",\"fieldname\":\"XYPJ_UID\",\"operation\":\"=\",\"value\":\""+tempJson.XYPJ_UID+"\",\"fieldtype\":'',\"fieldformat\":''}";
		var dataXX = "{querycondition: {conditions: [" +queryconditionXX+"]},pages:{recordsperpage:200, currentpagenum:1, totalpage:0, countrows:0},orders:[{\"fieldname\":\"XYPJ_UID\",\"order\":\"asc\"}]}";
		//调用ajax插入
		defaultJson.doQueryJsonList("${pageContext.request.contextPath }/wxgc/yxXypjWgsjController?query", dataXX, xypjList);
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
      		<button id="btnPrint" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">打印评价表</button>   		
      	</span>
      </h4>
     <form method="post" id="yxWxgcForm"  >
      <table class="B-table" width="100%" >
		<tr>
		<td  colspan="4">
		<div class="modal-header" style="background:#0866c6;">
			<h3 style="color:white;">评价信息</h3>
		</div>
		</td>
		</tr>
	  		<input type="hidden" id="GC_WXGC_UID" fieldname="GC_WXGC_UID" name = "GC_WXGC_UID"/>
	  		<input type="hidden" id="XYPJ_UID" fieldname="XYPJ_UID" name = "XYPJ_UID"/>
	  		<input type="hidden" id="ZB_YXCBS_UID" fieldname="ZB_YXCBS_UID" name = "ZB_YXCBS_UID"/>
	  	<tr>
			<th width="8%" class="right-border bottom-border text-right">建设单位</th>
       	 	<td class="bottom-border right-border"  colspan="3">
         		<input id="JSDW" type="text" fieldname="JSDW" name = "JSDW"  class="span12" style="width:50%"/>
       	 	</td>
        </tr>
        
        <tr>
			<th width="8%" class="right-border bottom-border text-right">工程名称</th>
       	 	<td class="bottom-border right-border"  colspan="3">
         		<input id="GC_NAME" type="text"  fieldname="GC_NAME" name = "GC_NAME" class="span12" style="width:50%" />
       	 	</td>
        </tr>
        
     
       
        <tr>
         	<th width="8%" class="right-border bottom-border text-right">合同工期</th>
       		<td class="bottom-border right-border"width="15%">
         		<input id="JHGQ" type="text"  fieldname="JHGQ" name = "JHGQ" />天
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">合同价</th>
       		<td class="bottom-border right-border"width="15%">
         		<input id="ZBJ" type="text"  fieldname="ZBJ" name = "ZBJ"  />元
         	</td>
        </tr>
            
        <tr>
			<th width="8%" class="right-border bottom-border text-right">中标单位</th>
       	 	<td class="bottom-border right-border"  colspan="3">
         		<input id="ZB_NAME" type="text"  fieldname="ZB_NAME" name = "ZB_NAME" class="span12" style="width:50%" />
       	 	</td>
        </tr>
         <tr>
         	<th width="8%" class="right-border bottom-border text-right">项目负责人</th>
       		<td class="bottom-border right-border" width="15%">
         		<input id="XMFZR" type="text"  fieldname="XMFZR" name = "XMFZR" />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">证书编号</th>
       		<td class="bottom-border right-border" width="15%">
         		<input id="ZYZGZH" type="text"  fieldname="ZYZGZH" name = "ZYZGZH"  />
         	</td>
        </tr>
            
       <tr>
			<th width="8%" class="right-border bottom-border text-right">开工日期</th>
       		<td class="bottom-border right-border" width="15%">
         		<input id="JHKGRQ" type="text"  fieldname="JHKGRQ" name = "JHKGRQ" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class='Wdate' />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">竣工日期</th>
       		<td class="bottom-border right-border" width="15%">
         		<input id="JHJGRQ" type="text"  fieldname="JHJGRQ" name = "JHJGRQ" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class='Wdate'  />
         	</td>
        </tr>
        <tr>
         	<th width="8%" class="right-border bottom-border text-right">评定得分</th>
       		<td class="bottom-border right-border" width="15%">
         		<input id="KFZFZ" type="text"  fieldname="KFZFZ" name = "KFZFZ" />分
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">评定等级</th>
       		<td class="bottom-border right-border" width="15%">
         		<input id="PDDJNAME" type="text"  fieldname="PDDJNAME" name = "PDDJNAME"  />
         	</td>
        </tr>
      
   		<tr>
   			<th width="8%" class="right-border bottom-border text-right">评定得分</th>
			<td  class="bottom-border right-border" colspan="3">
				<div class="overFlowX">
			 		<table width="100%" class="table-hover table-activeTd B-table" id="xypjList" type="single" pageNum="2000"  noPage="true">
					    <thead>
					 		<tr style="display:blank">
<%--					           <th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>--%>
					            <th fieldname="WGSJFL_SV" style="width:10px;" colindex=1 tdalign="center" >评价内容</th>
								<th fieldname="WGSJNR" colindex=2 tdalign="center" style="width:150px;">&nbsp;具体标准&nbsp;</th>
								<th fieldname="KFFZ" colindex=2 tdalign="center" style="width:10px;">&nbsp;扣分分值&nbsp;</th>
					        </tr>
					    </thead>
					    <tbody></tbody>
				  	</table>  
	       		</div>
			</td>
		</tr>
        
          
      </table>
    
      </form>
      

    </div>
   </div>
  </div>
    <form method="post" id="queryForm"  >
    	  <input type="hidden" id="QXYPJ_UID" fieldname="XYPJ_UID" name = "XYPJ_UID" operation="="/>
     </form>
	
  <div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id = "queryXML">
         <input type="hidden" name="txtXML" id = "txtXML">
         <input type="hidden" name="txtFilter"  order="desc" fieldname = "XYPJ_UID" id = "txtFilter">
         <input type="hidden" name="resultXML" id = "resultXML">
       	 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
 	</FORM>
 </div>
</body>
</html>