<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
	<%@page import="com.ccthanking.framework.Constants"%>
<app:base/>
<title>安装告知表</title>
<% 
String id = request.getParameter("id");
String gcid = request.getParameter("gcid");
String fjlb = Constants.FS_FILEUPLOAD_FJLB_JXSB_AZGC;
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/kindeditor/kindeditor.js"></script>
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/jsp/business/sp/js/printPageHtml.js"> </script>
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/kindeditor/customKE.js"></script>
</head>
<body>
<app:dialogs/>
<div class="main-container" id="main-container">
		
		<div style="padding:10px;padding-top:2px;text-align:left;"
			valign="top">
			<div style="width:730px;">
				<table id="apex_layout_84945431230501312" class="formlayout"
					summary="">
					<tbody>
						<tr>
							<td nowrap="nowrap" align="right"><label for="P6052_BT"><span
									class="t3Optional"></span> </label></td>
							<td colspan="1" rowspan="1" align="center" style="width: 730px"><span
								id="P6052_BT"><font style="font-size:15pt;">起重设备安装过程管理表

								</font><br> <br> </span>
							</td>
							<td style="width: 140px" align="left">
							<span style="float:right">
			<button id="btnClose" class="btn" type="button"
				style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
			<button id="btnSave" class="btn" type="button"
				style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">打印</button>
		</span>
							</td>
						</tr>
					</tbody>
				</table>
			</div>

			<style>
.formlayout {
	border: none;
}

.styleform {
	padding: 5px 10px 5px 10px;
}

.styleform table {
	border-collapse: collapse;
}

.styleform table .radiogroup {
	border: none;
}

.styleform table .radiogroup td {
	border: none;
}

.styleform td {
	border: solid 1px #000000;
}

.styleform td .noborder {
	border: none;
}

.formtd {
	border: solid 1px #00000;
}
</style>

 <form class="form-horizontal" id="azgzform">

			<table class="t3RegionwithoutButtonsandTitles" cellspacing="0"
				border="0" summary="layout" id="R16486711554121236">
				<tbody>
					<tr>
						<td valign="bottom" class="t3RegionHeader"
							style="text-align:center;"></td>
					</tr>

					<tr class="t3Body">
						<td colspan="2" class="styleform"
							style="border:1px solid #000000;padding:0px;"><table
								id="apex_layout_16486711554121236" class="formlayout" summary="">
								<tbody>
									<tr>
										<td  nowrap="" align="right" style="width: 120px"><label
											for="P8023_SHEBEI_NAME"><span class="t3Optional">设备名称</span>
										</label>
										</td>
										<td colspan="1" fieldname="SHEBEI_NAME" rowspan="1" align="left" style="width: 230px">
										</td>
										<td nowrap="" align="right" style="width: 100px"><label for="P8023_CQ_BH"><span
												class="t3Optional">产权编号</span>
										</label>
										</td>
										<td colspan="1" rowspan="1" align="left" style="width: 200px" fieldname="CQ_BH">
										</td>
									</tr>
									<tr>
										<td align="right"><label for="P8023_AZCX_BEGIN_DATE"><span
												class="t3Optional">安装开始时间</span>
										</label>
										</td>
										<td colspan="1" rowspan="1" align="left" fieldname="SBAZ_DATE">
										</td>
										<td align="right"><label for="P8023_AZCX_END_DATE"><span
												class="t3Optional">安装结束时间</span>
										</label>
										</td>
										<td colspan="1" rowspan="1" align="left" fieldname="SBAZ_E_DATE">
										</td>
									</tr>
									<tr>
										<td align="right"><label for="P8023_AZCX_AQJD_QK"><span
												class="t3Optional">安装前安全交底情况</span>
										</label>
										</td>
										<td colspan="1" rowspan="1" align="left" fieldname="AQJD">
										</td>
										<td align="right"><label for="P8023_AZ_BJJC_QK"><span
												class="t3Optional">安装前零部件<br>检查验收表</span>
										</label>
										</td>
										<td colspan="1" rowspan="1" align="left" fieldname="BJJC_SV">
										</td>
									</tr>
									<tr>
										<td align="right"><label for="P8023_AZCX_CZSG_QK"><span
												class="t3Optional">安装过程持证上岗情况</span>
										</label>
										</td>
										<td colspan="1" rowspan="1" align="left" fieldname="CZSG">
										</td>
										<td align="right"><label for="P8023_AZCX_GZQQ"><span
												class="t3Optional">工种是否齐全</span>
										</label>
										</td>
										<td colspan="1" rowspan="1" align="left" fieldname="GZQQ_SV">
										</td>
									</tr>
									<tr>
										<td align="right"><label for="P8023_AZCX_JJSZ_QK"><span
												class="t3Optional">警戒线、警戒标志设置情况</span>
										</label>
										</td>
										<td  colspan="3" rowspan="1" align="left" >
										<textarea name="p_t09" rows="4"  wrap="virtual" style="width: 97%"  fieldname="JJSZ"
												id="P8023_AZCX_JJSZ_QK" class="tarea" readonly="true"></textarea>
										</td>
									</tr>
									<tr>
										<td align="right"><label for="P8023_AZCX_AQYP_QK"><span
												class="t3Optional">安装过程安全用品配备情况</span>
										</label>
										</td>
										<td colspan="3" rowspan="1" align="left">
										<textarea name="p_t10" rows="4" cols="60" wrap="virtual" style="width: 97%" fieldname="AQYP"
												id="P8023_AZCX_AQYP_QK" class="tarea" readonly="true"></textarea>
										</td>
									</tr>
									<tr>
										<td align="right"><label for="P8023_AZCX_CPH"><span
												class="t3Optional">汽车吊牌号</span>
										</label>
										</td>
										<td colspan="1" rowspan="1" align="left" fieldname="DPH">
										</td>
										<td align="right"><label for="P8023_AZCX_JGNJZM"><span
												class="t3Optional">汽车吊起重<br>机构年检证明</span>
										</label>
										</td>
										<td colspan="1" rowspan="1" align="left" fieldname="NJZM">
										</td>
									</tr>
									<tr>
										<td align="right"><label for="P8023_QDBG_BH"><span
												class="t3Optional">基础砼试块强度报告编号</span>
										</label>
										</td>
										<td colspan="1" rowspan="1" align="left" fieldname="QDBGBH">
										</td>
										<td align="right"><label for="P8023_AZ_INIT_HEIGHT"><span
												class="t3Optional">安装高度</span>
										</label>
										</td>
										<td colspan="1" rowspan="1" align="left" fieldname="HEIGHT">
										</td>
									</tr>
									<tr>
										<td align="right"><label for="P8023_AZCX_AQYJD_QK"><span
												class="t3Optional">安装过程安全员<br>全过程监督情况</span>
										</label>
										</td>
										<td colspan="3" rowspan="1" align="left" >
										<textarea name="p_t15" rows="4" cols="60" wrap="virtual" style="width: 97%" fieldname="AQYJD" 
												id="P8023_AZCX_AQYJD_QK" class="tarea" readonly="true"></textarea>
										</td>
									</tr>
									<tr>
										<td nowrap=""
											style="border-right:none;border-bottom:none;border-top:none;"
											align="right"><label for="P8023_JL_SHENHE_YJ"><span
												class="t3Optional">审核意见</span>
										</label>
										</td>
										<td
											style="border-right:none;border-bottom:none;border-top:none;border-left:none;" 
											colspan="3" rowspan="1" align="left"><input
											type="hidden" name="p_arg_names" value="16494311104121256">
										<textarea name="p_t16" rows="5" cols="80" wrap="virtual" style="width: 97%" fieldname="JL_SHENHE_YJ" readonly="readonly"
												id="P8023_JL_SHENHE_YJ" class="tarea"></textarea>
										</td>
									</tr>
								</tbody>
							</table></td>
					</tr>
				</tbody>
			</table>
			</form>
	<div id="imgdiv"></div>
	</div>
	<div style="display: none;">
<table role="presentation" id="YYZZ" class="table table-striped">
							                              <tbody fjlb="<%=fjlb %>"  id="azgcfj" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery">
							                              </tbody>
					                                  	</table>
					                                  	</div>


	<jsp:include page="/jsp/file_upload/fileuploadold_config_js.jsp" flush="true"/> 
	<script type="text/javascript" charset="utf-8">
	var controllername= "${pageContext.request.contextPath }/bzwj/jxsbAzgzController";
	$(document).ready(function(){
		 $("input:checkbox").attr("disabled","true");
	 $("#btnClose").click(function(){
			    window.close(); 
			    });
	 $("#btnSave").click(function(){
		 window.location.href = controllername + "/downloadAzgc?uid="+<%=id %>;
		   
		    
		    });
		init();
	
	});
	var fileurlimg = "";
	var imgfiles;
	function init(){
		query();	
		var html="";
		for(var i=0;i<imgfiles.length;i++){
			html += "<img style='width:200px;height:300px;' src='"+imgfiles[i].url+"' >";
		}
		if(html==""){
			html="无";
		}
		$("#imgdiv").html(html);
		$("#modal-gallery").hide(); 
	}
	var syglid="";
	function query(){
		 var queryconditionXX = "{\"logic\":\"and\",\"fieldname\":\"JXSB_AZGC_UID\",\"operation\":\"=\",\"value\":\""
				+ <%=id%> + "\",\"fieldtype\":'',\"fieldformat\":''}"; 	
		var dataXX = "{querycondition: {conditions: ["
				+ queryconditionXX
				+ " ]},pages:{recordsperpage:200, currentpagenum:1, totalpage:0, countrows:0},orders:[{\"order\":\"desc\",\"fieldname\":\"CREATED_DATE\"}]}";
		var data = {
			msg : dataXX
		};
		$.ajax({
			url : controllername+"?queryAzgc",
			data : data,
			cache : false,
			async : false,
			dataType : "json",
			type : 'post',
			success : function(response) {			
				var resultobj = defaultJson.dealResultJson(response.msg);
				$("#azgzform").setHtmlValue(resultobj);
				$("#azgzform").setFormValues(resultobj);
				if($("#JXSB_AZGC_UID").val()!=""){ //附件
			        setFileData(resultobj.JXSB_AZGC_UID, "", "", "460010","0","");
					queryFileData(resultobj.JXSB_AZGC_UID, "", "", "460010");
					$("#ywid").val(resultobj.JXSB_AZGC_UID);
			        }
				return true;
				}
		});
	}
function selectYyzzFile(obj){
		
		$("#yyzzError").hide();
		//var result=getSpClkUid("LX",$(obj).attr("clkUid"));
		var ywid="";
		var id=$("#ywid").val();
		if(id!=""){	
			ywid=id;
			var temid=$("#ID").val();	
			if(temid!=null&&temid!=""){
				ywid=temid;
			}
		}
		setFileData(ywid,"","","460010","0",$(obj).attr("clkUid"));
		var inputArr = $(".myKeyValueDiv input");
		for(var xx=0;xx<inputArr.length;xx++){
			if(inputArr[xx].getAttribute("cond")=="true"){
				if(inputArr[xx].getAttribute("condName")=="fjlb"){
					inputArr[xx].value=$(obj).attr("fjlb");
				}
			}
		}
		if(isIE()){
			initdataswf(ywid,"","","460010","0",$(obj).attr("clkUid"),$(obj).attr("clkUid"));
			//initdataswf(ywid,"","","470010","0",result.CLK_UID,"1609");
			document.getElementById("plfileuploadswf").click();
		}else{
			document.getElementById("fileupload_btn").click();
		}	
	}
	function isIE(){
		if(navigator.userAgent.indexOf("MSIE")>0){ 
			if(navigator.userAgent.indexOf("MSIE 6.0")>0){ 
				return false;
			} 
			if(navigator.userAgent.indexOf("MSIE 7.0")>0){
				return false;
			} 
			if(navigator.userAgent.indexOf("MSIE 8.0")>0){
				return true;
			} 
			if(navigator.userAgent.indexOf("MSIE 9.0")>0){
				return true;
			} 
		}else{
			return false;
		} 	
	}
	</script>

	<form class="form-inline" role="form" id="azgzQueryForm">
		<input type="hidden" id="JXSB_CXGZ_UID" fieldname="JXSB_CXGZ_UID"
			value="<%=id%>" operation="=" logic="and" />
	</form>
	<form id="gdzxtformid" method="post">
		<input type="hidden" name="gdzxt_gcid" id="gdzxt_gcid">
	</form>
	</body>
</html>