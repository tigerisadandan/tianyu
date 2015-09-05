<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>

<title>建设工程企业资质申报企业业绩核查汇总表-维护</title>
<%
String type=request.getParameter("type");
String id=(String)request.getAttribute("id");
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
var controllername= "${pageContext.request.contextPath }/sp/buSpywSgqyyjhcsxQyyjhchzController/";
var controllernameMX= "${pageContext.request.contextPath }/sp/buSpywSgqyyjhcsxQyyjhchzMxController/";
var controllerfile= "${pageContext.request.contextPath }/controller/fileController/";

var type ="<%=type%>";
var id ="<%=id%>";

var contextPath="${pageContext.request.contextPath }";

//页面初始化
$(function() {
	init();
	$("#downLoad").click(function() {
		download();
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
				//alert(response.msg);
					$("#resultXML").val(response.msg);
					
						var resultobj = defaultJson.dealResultJson(response.msg);
						$("#zzsbqyyjhchzForm").setFormValues(resultobj); 
						$("#SGQYYJHCSX_QYYJHCHZ_UID").val(resultobj.SGQYYJHCSX_QYYJHCHZ_UID); 
						$("#QID2").val(resultobj.SGQYYJHCSX_QYYJHCHZ_UID);

						$("#TBR").html(resultobj.TBR);
						$("#TBRQ").html(resultobj.TBRQ);
						$("#TBDW").html(resultobj.TBDW);
			}
			});
		builderMxList();
		
	}
}


//更改记录时子表需调用的方法 
function builderMxList(){
	
	$.ajax({
			url : controllernameMX+"queryUpdate?id="+$("#SGQYYJHCSX_QYYJHCHZ_UID").val(),
			data : {},
			cache : false,
			async :	false,
			dataType : "json",  
			type : 'post',
			success : function(response) {
				
				var obj1 = eval('(' + response.msg + ')'); 
				
				$(obj1).each(function(){
					var cloneNew = $("#cloneTR").clone(true);
					//alert(typeof(cloneNew));
					$(cloneNew).removeAttr("style")
					//alert($(cloneNew).html());
					$("#sqmx").find("tr").eq(3).before(cloneNew);
					$(cloneNew).find("#SBDWMC").html(this.SBDWMC);
					$(cloneNew).find("#XMMC").html(this.XMMC);
					$(cloneNew).find("#GCDZ").html(this.GCDZ);
					if(this.ZBSJ!=""){
						var date=this.ZBSJ.substring(0, 10);
						$(cloneNew).find("#ZBSJ").html(date);
						}
					
					$(cloneNew).find("#KJGKCSJSMSJ").html(this.KJGKCSJSMSJ);
					$(cloneNew).find("#ZBJ").html(this.ZBJ);
					$(cloneNew).find("#SJWCDW").html(this.SJWCDW);
					$(cloneNew).find("#ZYJSZB1").html(this.ZYJSZB1);
					$(cloneNew).find("#ZYJSZB2").html(this.ZYJSZB2);
					if(this.SFFSAQSG==1){
						$(cloneNew).find("#SFFSAQSG").html("是");
					}else if(this.SFFSAQSG==0){
						$(cloneNew).find("#SFFSAQSG").html("否");
					}else{
						$(cloneNew).find("#SFFSAQSG").html("");
						}
					if(this.SFYSMSJYZ==1){
						$(cloneNew).find("#SFYSMSJYZ").html("是");
					}else if(this.SFYSMSJYZ==0){
						$(cloneNew).find("#SFYSMSJYZ").html("否");
					}else{
						$(cloneNew).find("#SFYSMSJYZ").html("");
						}
					
					$(cloneNew).find("#DESCRIBE").html(this.DESCRIBE);
				});
			
			}
		});

		
		var rows = $("tbody tr" ,$("#sqmx"));
		if(rows.size()==0){
			$("tbody" ,$("#sqmx")).append("<tr><td colspan=\"7\" style=\"height: 1px;\" align=\"center\">暂无记录</td></tr>");
		}

}

function download(){
	
	$.ajax({
		url : controllername+"download?id="+$("#SGQYYJHCSX_QYYJHCHZ_UID").val(),  
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
	if($("#sqmx tr").size()==5){

		return;
		}
	var tr_index = $("#sqmx tr").index($(demo).closest("tr"));
	if(tr_index==$("#sqmx tr").size()-1&&tr_index>2){
		$("#sqmx tr").eq($("#sqmx tr").size()-2).find("td").eq($("#sqmx").find("th").size()-1).append('&nbsp;<img onclick="addSqmx(this)" style="cursor: pointer;" title="增加" src="${pageContext.request.contextPath }/img/sg/icon-addZz.jpg">');
	}
	$(demo).closest("tr").remove();
}


//只输入数字  
function onlyNum(obj)
{
	obj.value = obj.value.replace(/[^\d]/g,"");
}
//只能输入数字和小数点
function onlyNumAndFloat(obj)
 {
  //先把非数字的都替换掉，除了数字和.
  obj.value = obj.value.replace(/[^\d.]/g,"");
  //必须保证第一个为数字而不是.
  obj.value = obj.value.replace(/^\./g,"");
  //保证只有出现一个.而没有多个.
  obj.value = obj.value.replace(/\.{2,}/g,".");
  //保证.只出现一次，而不能出现两次以上
  obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
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
	        	<INPUT type="text" class="span12" kind="text" id="QID2" name="SGQYYJHCSX_QYYJHCHZ_UID"  fieldname="SGQYYJHCSX_QYYJHCHZ_UID" value="" operation="="/>
	        </TD>
			<TD class="right-border bottom-border">
			</TD>
        </TR>
      </table>
      </form>
        <div style="height:5px;"></div>
		<div class="B-small-from-table-autoConcise">
      
      	<span style="float:right">
      		<button id="btnShutdown" onclick="window.close();" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
	  		<button id="downLoad" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">打印</button>
      	</span><br/>
      <form method="post" id="zzsbqyyjhchzForm" >
     <h3 class="title" align="center">建设工程企业资质申报企业业绩核查汇总表</h3>
  
      <table style="width: 1300px; height: 20px;" border="0" align="center">
				<tr>
					<td colspan="4" align="left">
				  	资质类别:
				      <label class="checkbox inline" >  
				        <input type="checkbox" name="ZZLB" id="ZZLB" fieldname="ZZLB" value="01" disabled/>
				      	工程勘察
				      </label> 
				      &nbsp;
				      <label class="checkbox inline" >  
				        <input type="checkbox" name="ZZLB" id="ZZLB" fieldname="ZZLB" value="02" disabled/>
				      	工程设计
				      </label>  
				      &nbsp;
				      <label class="checkbox inline" >  
				         <input type="checkbox" name="ZZLB" id="ZZLB" fieldname="ZZLB" value="03" disabled/>
				      	建筑业企业	
				      </label> 
				      &nbsp;
				      <label class="checkbox inline" >  
				         <input type="checkbox" name="ZZLB" id="ZZLB" fieldname="ZZLB" value="04" disabled/>
				      	工程监理
				      </label>  
				      &nbsp;
				     <label class="checkbox inline" >  
				        <input type="checkbox" name="ZZLB" id="ZZLB" fieldname="ZZLB" value="05" disabled/>
				      	招标代理机构   
				      </label>  
				      &nbsp;
				      <label class="checkbox inline" >  
				       <input type="checkbox" name="ZZLB" id="ZZLB" fieldname="ZZLB" value="06" disabled/>
				                    设计施工一体化
				      </label>
				    </td>
				</tr>
		</table>
       <table width="1300" height="200" border="1"  align="center"  >
                  <input type="hidden" id="SGQYYJHCSX_QYYJHCHZ_UID" fieldname="SGQYYJHCSX_QYYJHCHZ_UID" name = "SGQYYJHCSX_QYYJHCHZ_UID"/>
                  <input type="hidden" id="YWLZ_UID" fieldname="YWLZ_UID" name = "YWLZ_UID"/>
                  	<tr >
						<td align="center" colspan="3" >
							
							<table  width="100%" id="sqmx" border="1" >								
								<tr height="30">
									<th align="center" style="width:10%" rowspan="3">申报单位名称</th>
									<th align="center" style="width:10%" rowspan="3">项目名称</th>
									<th align="center" style="width:10%" rowspan="3">工程地址</th>
									<th align="center" style="width:45%" colspan="6">需核查内容</th>
									<th align="center" style="width:10%" >工程质量安全情况</th>
									<th align="center" style="width:15%" colspan="2">核查结果</th>
								</tr>
								<tr height="30">
									<th align="center" style="width:10%" rowspan="2">中标时间</th>
									<th align="center" style="width:10%" rowspan="2">开竣工时间/勘察设计工作始末时间</th>
									<th align="center" style="width:5%" rowspan="2">中标价</th>
									<th align="center" style="width:10%" rowspan="2">实际完成单位</th>
									<th align="center" style="width:10%" colspan="2">主要技术指标</th>
									<th align="center" style="width:10%" rowspan="2">是否发生质量安全事故</th>
									<th align="center" style="width:7%" rowspan="2">是否与申报数据一致</th>
									<th align="center" style="width:5%" rowspan="2">备注</th>
								</tr>
								<tr height="30">
									<th align="center" style="width:5%" ></th>
									<th align="center" style="width:5%" ></th>
								</tr>
								<tr id="cloneTR" style="display : none;"><!-- 用来复制 -->
									<td id="SBDWMC"></td>
									<td id="XMMC"></td>
									<td id="GCDZ"></td>
									<td id="ZBSJ" ></td>
									<td id="KJGKCSJSMSJ"></td>
									<td id="ZBJ"></td>
									<td id="SJWCDW"></td>
									<td id="ZYJSZB1"></td>
									<td id="ZYJSZB2"></td>
									<td id="SFFSAQSG"></td>
									<td id="SFYSMSJYZ"></td>
									<td id="DESCRIBE"></td>
									
								</tr>
							</table>
							</div>
						</td>
					</tr>
					<tr>
					   <td>
					   		注：1.在填写“主要技术指标”时，应按照建设工程企业申请资质材料中提供的代表工程业绩主要技术指标填写。如：结算金额（万元）、面积（平方米）、工程层数（层）等。</br>
    					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.“需核查内容”中应填写实际核查数据。</br>
    					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.若工程项目发生质量安全事故，需在“是否发生质量安全事故”一栏中选择"是"；若未发生质量安全事故选择“否”。</br>
   	 					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.若核查结果与企业申报内容一致，需在核查结果“是否与申报一致栏中”内选择“是”；若不一致须选择“否”，并在备注栏中注明不一致的具体指标。</br>
    					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.招标代理机构只须核查中标时间、中标价、实际完成单位和工程总投资额四项指标，其中工程总投资额应在主要技术指标中填写，其具体数据以项目立项批复为准。</br>
					   		
					   </td>
					</tr>
			</table>
			 <table style="width: 1300px; height: 20px;" border="0" align="center">
				<tr>
					<td align="left" style="width:33%" >
					 	填报单位(公章):
					 	<span id="TBDW" >
					 	</span>
					</td>
					<td align="left" style="width:33%">
						填报人：
						<span id="TBR" >
					 	</span>
					</td>
					<td align="left" style="width:33%">
						填报日期：
						<span id="TBRQ" >
					 	</span>
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