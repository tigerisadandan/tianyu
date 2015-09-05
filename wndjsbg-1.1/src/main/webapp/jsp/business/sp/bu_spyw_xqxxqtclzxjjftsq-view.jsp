<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>

<title>墙改基金征收及返退审批事项-维护</title>
<%
String id=(String)request.getAttribute("id");
String type = request.getParameter("type");
%>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/sp/css/bootstrap.css?version=20131201"> </LINK>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/sp/css/xAlert.css"> </LINK>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/base/jquery.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/jsp/business/sp/js/printPageHtml.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/base/LockingTable.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/base/bootstrap.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/base/jsBruce.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/Form2Json.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/convertJson.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/combineQuery.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/default.js?version=20131206"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/TabList.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/bootstrap-validation.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/loadFields.js?version=20131206"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"> </script>
<script type="text/javascript" charset="utf-8"> 
//请求路径，对应后台RequestMapping						    
var controllername= "${pageContext.request.contextPath }/sp/bu_spyw_xqxxqtclController/";
var controllernameCl= "${pageContext.request.contextPath }/sp/bu_spyw_xqxxqtclClController/";
var controllerfile= "${pageContext.request.contextPath }/controller/fileController/";
 
var id ="<%=id%>";
var type ="<%=type%>";
var contextPath="${pageContext.request.contextPath }";
//页面初始化
$(function() {
	init();
	$("#downLoad").click(function() {
		download();
	});
	
	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#queryForm").clearFormResult();
    });
    
	
});
//页面默认参数
function init(){

	$("#pubAlert").hide();
	
	if(id!="null"&&id!=""){
		 $("#QID").val(id);
		
		var data = combineQuery.getQueryCombineData(queryForm,frmPost);
		var data1 = {
			msg : data
		};	
		$.ajax({
			url : controllername+"query",
			data : data1,
			cache : false,
			async :	false,
			dataType : "json",  
			type : 'post',
			success : function(response) {
				$("#resultXML").val(response.msg);
				var resultobj = defaultJson.dealResultJson(response.msg);
				 $("#QID2").val(resultobj.XQXXQTCLZXJJFT_UID);
				 $("#XQXXQTCLZXJJFT_UID").val(resultobj.XQXXQTCLZXJJFT_UID);

				 var zonghe=0;
				 var rending=0;
				zonghe+=parseFloat(resultobj.FNTXCL);
				zonghe+=parseFloat(resultobj.FNTSJXCL);
			 	rending+=parseFloat(resultobj.FNTXCLJRD);
				rending+=parseFloat(resultobj.FNTSJXCLJRZ);
			    a=parseFloat((zonghe/rending)*100);
			    $("#BL").html(a);
			    
			     $("#SQDW").html(resultobj.SQDW);
			     //$("#SQRQ").html(resultobj.SQRQ);
			     
			     $("#QZQKSM").html(resultobj.QZQKSM);
			     $("#FNTXCL").html(resultobj.FNTXCL);
			     $("#FNTSJXCL").html(resultobj.FNTSJXCL);
			     $("#NTZKZ").html(resultobj.NTZKZ);	
			     
			     $("#NTKXZ").html(resultobj.NTKXZ);
			     $("#NTSXZ").html(resultobj.NTSXZ);
				 $("#YSSSYL").html(resultobj.YSSSYL);
				 $("#NTQCSYL").html(resultobj.NTQCSYL);
				 $("#FNTSJXCLJRZ").html(resultobj.FNTSJXCLJRZ);
				 $("#FNTXCLJRD").html(resultobj.FNTXCLJRD);
				 $("#QTBJ").html(resultobj.QTBJ);
				 $("#CMJBJ").html(resultobj.CMJBJ);
				 $("#TRFTJEDX").html(resultobj.TRFTJEDX);
				 $("#LDYJ").html(resultobj.LDYJ);
				 
				 var sqrq=resultobj.SQRQ;
				 if(sqrq!=""){
					 
					 var s1 = sqrq.substr(0, 4) + "年" + sqrq.substr(5, 2) + "月" + sqrq.substr(8, 2)+ "月";
					 $("#SQRQ").html(s1);				
					 }
				 
				$("#bU_Spyw_xqxxqtclzxjjftsqForm").setFormValues(resultobj);
				$("#bU_Spyw_xqxxqtclzxjjftsqForm").setHtmlValue(resultobj);
				
		}
		});
		builderMxList();
	}
}
function builderMxList(){
	
	var data = combineQuery.getQueryCombineData(queryForm2,frmPost,sqmx); 
	try{
	//调用ajax插入
	defaultJson.doQueryJsonList(controllernameCl+"query",data,sqmx,null,true);
	}catch(e){
	}

	
	var rows = $("tbody tr" ,$("#sqmx"));
	if(rows.size()==0){
		$("tbody" ,$("#sqmx")).append("<tr><td colspan=\"6\" style=\"height: 1px;\" align=\"center\">暂无记录</td></tr>");
	}
}

function download(){
	
	$.ajax({
		url : controllername+"download?id="+$("#XQXXQTCLZXJJFT_UID").val(),  
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			$("#resultXML").val(response.msg);
<%--			var resultobj = defaultJson.dealResultJson(response.msg);--%>
			//window.open(controllerfile+"download2?path="+response.msg,'文件下载打印'); 

			window.location.href = controllerfile+"download2?path="+response.msg;
	}
	});
	
}
function addSqmx(demo){
	var cloneNew = $("#cloneTR").clone(true);
	$(cloneNew).removeAttr("style")
	$("#sqmx").append(cloneNew);
	$(demo).hide();
}

function removeSqmx(demo){
	if($("#sqmx tr").size()==3){

		return;
		}
	var tr_index = $("#sqmx tr").index($(demo).closest("tr"));
	if(tr_index==$("#sqmx tr").size()-1&&tr_index>2){
		$("#sqmx tr").eq($("#sqmx tr").size()-2).find("td").eq($("#sqmx").find("th").size()-1).append('&nbsp;<img onclick="addSqmx(this)" style="cursor: pointer;" title="增加" src="${pageContext.request.contextPath }/img/sg/icon-addZz.jpg">');
	}
	$(demo).closest("tr").remove();
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
	        	<INPUT type="text" class="span12" kind="text" id="QID" name="ID"  fieldname="YWLZ_UID" value="" operation="="/>
	        </TD>
			<TD class="right-border bottom-border">
			</TD>
        </TR>
      </table>
      </form>
      <form method="post" id="queryForm2"  >
      <table class="B-table" width="100%">
      <!--可以再此处加入hidden域作为过滤条件 -->
      	<TR  style="display:none;">
	        <TD class="right-border bottom-border">
	        	<INPUT type="text" class="span12" kind="text" id="QID2" name="ID"  fieldname="XQXXQTCLZXJJFT_UID" value="" operation="="/>
	        </TD>
			<TD class="right-border bottom-border">
			</TD>
        </TR>
      </table>
      </form>
        <div style="height:5px;"></div>
		<div class="B-small-from-table-autoConcise">
      
      	<span style="float:right">
            <button id="btnShutDown" onclick="window.close();" class="btn" type="button" style="font-family: SimYou, Microsoft YaHei; font-weight: bold;">关闭</button>
      	    <button id="downLoad" class="btn" type="button" style="font-family: SimYou, Microsoft YaHei; font-weight: bold;">打印</button>
      	</span>
      	<br/>
      <form method="post" id="bU_Spyw_xqxxqtclzxjjftsqForm" >
     <h4 class="title" align="center">无锡新区新型墙体材料专项基金返退申请表</h4>
       <table style="width: 800px; height: 20px;" border="0" align="center">
							<tr>
								<td align="left">
								申请单位(盖章):
								<span id="SQDW"></span>
								</td>
								<td align="right">
								申请日期:
								<span id="SQRQ"></span>
								</td>
							</tr>
		</table>
        <table width="800" height="820" border="1"  align="center"  >
                  <input type="hidden" id="XQXXQTCLZXJJFT_UID" fieldname="XQXXQTCLZXJJFT_UID" name = "XQXXQTCLZXJJFT_UID"/>
                 <input type="hidden" id="YWLZ_UID" fieldname="YWLZ_UID" name = "YWLZ_UID"/>
                     <tr>
						<td width="15%" height=30 align="right">工程名称</td>
			       	 	<td  width="35%"  colspan="2" fieldname="GCMC" >
			         	</td>
			            <td width="15%" height=30 align="right">联 系 人</td>
						<td   width="35%"  colspan="2" fieldname="LXR">
						</td>
			        </tr>
			        <tr>
						<td width="15%" height=30  align="right">开户银行及帐号</td>
						<td  width="35%" colspan="2" fieldname="KHYHJZH">
						</td>
						<td width="15%" height=30  align="right">联系电话</td>
						<td   width="35%"  colspan="2" fieldname="LXDH">
					    </td>
					</tr>
					<tr>
						<td width="15%" height=30  align="right">规划许可证编号</td>
						<td width="35%" colspan="2" fieldname="GHXKZBH">
					    </td>
					    <td width="15%" height=30  align="right">施工许可证编号</td>
						<td width="35%" colspan="2" fieldname="SGXKZBH" >
					    </td>
					</tr>	
					<tr>
						<td width="15%" height=30 align="right">报建面积(m2)</td>
						<td width="20%" fieldname="BJMJ">
					    </td>
					    <td width="15%" height=30 align="right">实际面积(m2)</td>
						<td width="15%"  fieldname="SJMJ" >
					    </td>
					    <td width="20%" height=30 align="right"  >缴纳预收款金额(元)</td>
						<td width="15%" fieldname="JHYSKJE">
					    </td>
					</tr>	
					<th colspan="6" height="20" align="center">墙    材    使    用    情    况</th>
					<tr >
						<td align="center" colspan="6" cellSpacing=0 cellPadding=0 >
							<div class="overFlowX" >
							<table border="1" class="table-hover table-activeTd B-table" noprint="true" height="100%" width="100%"   id="sqmx" noPage="true" type="single">
								<thead>
								<tr>
									<th height="30" fieldname="LB" width="10%" colindex=0 tdalign="center" >类别</th>
									<th height="30" fieldname="QTBW" width="12%" colindex=0 tdalign="center" >墙体部位</th>
									<th height="30" fieldname="CLPZJMC" width="13%" colindex=0 tdalign="center" >材料品种及名称</th>
									<th height="30" fieldname="ZSBH" width="25%" colindex=0 tdalign="center" >生产厂家及认定(备案)证书编号</th>
									<th height="30" fieldname="SJSYLHJLDW" width="12%" colindex=0 tdalign="center" >实际使用量(含计量单位)</th>
									<th height="30" fieldname="ZBZ" width="13%" colindex=0 tdalign="center" >折标砖(万块)</th>
								</tr>
								</thead>
								<tbody></tbody>
							</table>
							</div>
						</td>
					</tr>
					<tr>
						<td height=30  colspan="3"  >工程预算书墙体材料使用总量(折标砖)       
					   		 <span id="YSSSYL"></span>
					   		万块
					    </td>
					   <td  height=30  colspan="3" >粘土墙材使用量(折标砖)
					   		 <span id="NTQCSYL"></span>
					   		万块
					    </td>
					</tr>	
				 	<th colspan="6" align="center" height="20" >以上内容由项目建设单位填写</th>
				 	<tr>
						<td  height="25" colspan="3" rowspan="7" align="left"><p>返退情况说明：</p>
	    				<p fieldname="FTQKSM">
					    </p>
					    </td>
					    <td  height=30  align="right">墙材使用总量</td>
						<td  align="right">
						<span id="QZQKSM"></span> 
					    	万块
					    </td>
					    <td >
					    </td>
					</tr>	
				 	<tr>
					    <td  height=30  align="right">非粘土新墙材</td>
						<td  align="right">
						<span id="FNTXCL"></span> 
					    	万块
					    </td>
					    <td >
					  		 经认定  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<span id="FNTXCLJRD"></span> 
					    	 万块
					    </td>
					</tr>
				 	<tr>
					    <td   height=30 align="right">非粘土(烧结)新墙材</td>
						<td   align="right">
						<span id="FNTSJXCL"></span> 
					    	万块
					    </td>
					    <td >
					  		 经认定 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					  		 <span id="FNTSJXCLJRZ"></span>         
					    	 万块
					    </td>
					</tr>
					<tr>
					    <td  height=30 align="right">粘土多孔砖</td>
						<td  align="right">
						<span id="NTZKZ"></span> 
					    	万块
					    </td>
					    <td >
					  		
					    </td>
					</tr>
					<tr>
					    <td  height=30 align="right">粘土空心砖</td>
						<td align="right">
						<span id="NTKXZ"></span>
					    	万块
					    </td>
					    <td >
					  		
					    </td>
					</tr>
					<tr>
					    <td  height=30 align="right">粘土实心砖</td>
						<td align="right">
						<span id="NTSXZ"></span>
					    	万块
					    </td>
					    <td >
					  		
					    </td>
					</tr>
					<tr>
						<td  height=30  colspan="3" align="center">
							新墙材使用比例 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<span id="BL"></span> 
					    	%
					    </td>
					</tr>
					<tr>   
						<td  height=30  align="right">基金缴纳情况</td>
						<td   colspan="2">超面积补缴&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<span id="CMJBJ"></span> 
							元
						</td>
						<td   height=30  colspan="3">
							其他补缴 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<span id="QTBJ"></span> 
					                      元
					    </td>
					</tr>
					<tr>
						<td  height=30  align="right">应返退金额</td>
						<td  align="right">
							<span id="YFTJE">
							</span>
							元
						</td>
						<td  height=30 align="right">补缴金额</td>
						<td   align="right">
							<span id="BJJE">
							</span>
					                      元
					    </td>
					    <td  height=30 align="right">实际返退金额</td>
						<td  align="right">
							<span id="SJFTJE">
							</span>
					                      元
					    </td>
					</tr>
					<th colspan="6" align="center">审   核   确   认</th>
					<tr>
						<td align="center" rowspan="2">确认返退金额</br>(大写)
						</td>
						<td  rowspan="2" colspan="2"  align="right">
							<span id="TRFTJEDX">
							</span>佰  &nbsp;&nbsp;&nbsp;
								拾  &nbsp;&nbsp;&nbsp;
								万 &nbsp;&nbsp;&nbsp;
								仟 &nbsp;&nbsp;&nbsp;
								佰  &nbsp;&nbsp;&nbsp;
								拾  &nbsp;&nbsp;&nbsp;
								元整	
						</td>
						<td  height=30 align="center">会议纪要</td>
						
					    <td  height=30 align="center">复核人</td>
						
						<td  height=30 align="center">经办人</td>
					</tr>
					<tr>
						
						<td  height=30 align="right" fieldname="HYJY">
						</td>
						
					    <td  height=30 align="right"  fieldname="FHR" >
						</td>
						
						<td  height=30 align="right"  fieldname="JBR">
						</td>
					</tr>
					<tr>
						<td height="25" colspan="6" align="left"><p>领导审批意见: </p>
						<p fieldname="LDYJ">
					    </p>
					    	 <div style="float:right">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
					    </td>
				    </tr>
				     </table>
      </form>
	</div>
	
   </div>
  </div>
  </div>
  <div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id = "queryXML">
         <input type="hidden" name="txtXML" id = "txtXML"><%--
         <input type="hidden" name="txtFilter"  order="desc" fieldname = "created_date" id = "txtFilter">
         --%><input type="hidden" name="resultXML" id = "resultXML">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
		
 	</FORM>
 </div>
</body>
<script>
</script>
</html>