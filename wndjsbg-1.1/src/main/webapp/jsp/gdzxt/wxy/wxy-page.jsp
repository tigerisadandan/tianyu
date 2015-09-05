<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.params.SysPara.SysParaConfigureVO"%>
<%@ page import="com.ccthanking.framework.params.ParaManager"%>
<%@ page import="com.ccthanking.framework.Constants"%>
<%@ include file="/jsp/framework/common/taglibs.jsp" %>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base />
<title>审批业务流转实例审批</title>
<%
String type=request.getParameter("type");
SysParaConfigureVO syspara  = (SysParaConfigureVO) ParaManager.getInstance().getSysParameter(Constants.getString(Constants.FILEUPLOADOLD_ROOT_JS, "FILEUPLOADOLD_ROOT_WNDJSJS"));
String fileRoot = syspara.getSysParaConfigureParavalue1();
String spyw_uid = request.getParameter("spyw_uid");
%>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/ywlz/css/default.css"> </LINK>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>	
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/kindeditor/plugins/code/prettify.css" />
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/kindeditor/kindeditor.js"></script>
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/kindeditor/lang/zh_CN.js"></script>

<script type="text/javascript" charset="utf-8">
</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">  
	<div class="B-small-from-table-autoConcise">
     	<div style="height: 5px;"></div>
	    <table border="1" width="100%" cellpadding="0" cellspacing="0" class="content">
			<tr>
				<td class="yw-title">
					<h4 ><span id="spyjtxhid">危险性较大的分部分项工程 </span>
								<span class="pull-right" > 
								</span>
								</h4>
				</td>
			</tr>
			<tr>
				<td>
					<ul id="ywcl_list">
						<table class="B-table" width="100%" >
      		<input type="hidden" id="AZ_COMPANY_UID" fieldname="AZ_COMPANY_UID" name = "AZ_COMPANY_UID"/>
      		<span id="jdwxyhtml" style="font-size: 13px;">
      		
      		</span>
      	</table>
					</ul>
				</td>
			</tr>
		</table>
		<div style="height: 5px;"></div>
		<table border="1" width="100%" cellpadding="0" cellspacing="0" class="content">
			<tr>
				<td class="yw-title">
					<h4 ><span id="spyjtxhid">超过一定规模的危险性较大的分部分项工程清单  </span>
								<span class="pull-right" > <!-- 
								<button id="btnAddYj" class="btn" type="button">   重新填写分项工程信息    </button>   -->
								</span>
								</h4>
				</td>
			</tr>
			<tr>
				<td>
					<ul id="ywcl_list" style=""> 
						<div class="overFlowX">
	            <table width="100%" class="table-hover table-activeTd B-table"  id="DT1" type="single" pageNum="9999">
	                <thead>
	                           
	                	<tr>
	                		<th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
	                		<th fieldname="GC_UID" colindex=2 tdalign="center" hidden>&nbsp;建设单位&nbsp;</th>
	                		<th fieldname="GC_Z_TYPE" hidden="hidden">&nbsp;&nbsp;</th>
	                		<th fieldname="GC_TYPE" colindex=3 tdalign="left" style="width:120px" maxlength="30" rowMerge="true">&nbsp;工程类型&nbsp;</th>
	                		<th fieldname="GC_UID" colindex=4 tdalign="left" CustomFunction="wxyView" style="width:250px">&nbsp;具体规模&nbsp;</th>
	                		
	                		<th fieldname="JLSH_STATUS" style="width:60px" colindex=5 tdalign="center" CustomFunction="showStatus" >&nbsp;审核状态&nbsp;</th>
	                		<th fieldname="GC_UID" style="width:50px" colindex=7 tdalign="center" CustomFunction="tianbao" maxlength="30" >&nbsp;查看&nbsp;</th>
	                		<th fieldname="GC_UID" style="width:50px" colindex=6 tdalign="center" CustomFunction="print" maxlength="30">&nbsp;打印&nbsp;</th>
	                		<th fieldname="GC_UID" style="width:80px" colindex=8 tdalign="center" CustomFunction="gcgl"  >&nbsp;过程管理&nbsp;</th>
	                		<th fieldname="PRM1" hidden>&nbsp;项目
经理&nbsp;</th>
	                		<th fieldname="PRM2" hidden>&nbsp;监理单位&nbsp;</th>
	                		<th fieldname="PRM3" hidden>&nbsp;总监&nbsp;</th>
	                		<th fieldname="PRM4" hidden>&nbsp;状态&nbsp;</th> 
	                		<th fieldname="PRM5" hidden>&nbsp;上月
分值&nbsp;</th>
	                		<th fieldname="PRM6" hidden>&nbsp;等级&nbsp;</th>
							<th fieldname="PRM7" hidden>&nbsp;最后更新&nbsp;</th>		
							<th fieldname="STATUS" hidden>&nbsp;最后更新&nbsp;</th>		
	                	</tr>
	                </thead>
	              	<tbody></tbody>
	           </table>
	       </div>
						
					</ul>
				</td>
			</tr>
		</table>
	
			
    </div>
	<input type="hidden" name="txtXML" id="wjCbfa">
   </div>
  </div>
  
 <jsp:include page="/jsp/file_upload/fileuploadold_config_forYwlz.jsp" flush="true" />
  	
	<div align="center">
	<FORM name="frmPost" method="post" style="display: none" target="_blank">
		<!--系统保留定义区域-->
		<input type="hidden" name="queryXML"/> 
		<input type="hidden" name="txtXML"/>
		<input type="hidden" name="txtFilter" order="asc" fieldname="WXYGCQD_UID" id="txtFilter"/>

<%--		<input type="hidden" name="txtFilter" order="desc" fieldname="ID" id="txtFilter"/>--%>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
	
	<form class="form-inline" id="queryForm" style="display: none;" >
							                <input value="0" type="hidden" id="GC_ID" name="GC_UID"  operation="="
									fieldname="GC_UID" > </form>
</body>
<script>

$(function() {
	$("#GC_ID").val(parent.document.getElementById("GDZXT_XM_ID").value);
	init();
	$("#page_DT1").hide();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {
		$('input[name="status"]:checked').each(function(i){
			if(i==0){
				statustj+=$(this).val();
			}else{
			statustj+=","+$(this).val();
			}
			
			});
		
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		//调用ajax插入
		defaultJson.doQueryJsonList("${pageContext.request.contextPath }/wxy/sgSpWxygcqdController/query",data,DT1);
	//	getCount();
		statustj="";

		$("#page_DT1").hide();
	});
	
	//按钮绑定事件(修改)
	$("#btnUpdate").click(function() {
		if($("#DT1").getSelectedRowIndex()==-1)
		 {
			requireSelectedOneRow();
		    return
		 }
		$("#resultXML").val($("#DT1").getSelectedRow());
		$(window).manhuaDialog({"title":"建设单位>审核","type":"text","content":"${pageContext.request.contextPath }/jsp/business/project/js-company-add.jsp?type=update","modal":"2"});
	});
	//按钮绑定事件（导出EXCEL）
/**	$("#btnExpExcel").click(function() {
		 var t = $("#DT1").getTableRows();
		 if(t<=0)
		 {
			 xAlert("提示信息","请至少查询出一条记录！");
			 return;
		 }
	  	 $(window).manhuaDialog({"title":"导出","type":"text","content":"${pageContext.request.contextPath }/jsp/framework/print/TabListEXP.jsp?tabId=DT1","modal":"3"});
	});**/
	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#queryForm").clearFormResult();
     
        $("#QSTATUS").val("30");
        $("#num").val("1000");
        $("input:radio[name='status']")[0].checked=true;
        $("input:radio[name='status']")[1].checked=false;
        $("input:radio[name='status']")[2].checked=false;
        $("#btnQuery").click();  
    });
	
});

//页面默认参数
function init(){
	initwxy();
	$("#shrid").hide();			
	$("#shsjid").hide();
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	//调用ajax插入
	defaultJson.doQueryJsonList("${pageContext.request.contextPath }/wxy/sgSpWxygcqdController/query",data,DT1);
//	getCount();

}

function initwxy(){
	$.ajax({
		url :"${pageContext.request.contextPath }/sgsx/buSpywJsgcaqjcsqController/queryByGcId?gcid="+$("#GC_ID").val(),
		data : null,
		cache : false,
		async : false,
		dataType : "json",
		type : 'post',
		success : function(response) {
			$("#resultXML").val(response.msg);
			//alert($("#resultXML").val());
			if(response.msg==""||response.msg=="0"){
				type="insert";
			    $("#jdwxyhtml").html("暂无较大危险源");
			}
			else{
				var i=1;
				var html="";
				var obj = defaultJson.dealResultJson(response.msg);
				if(obj.KAIWA_DEEP>3||obj.TFKWGC_Y=="1"){
					html+="<li style=\"padding-left:10px;line-height:20px;list-style:none;\">"+
					i+".开挖深度<u><b>"+obj.KAIWA_DEEP+"</b></u>m的基坑（槽）的土方开挖、支护、降水工程;<u><b>"+obj.TFKWGC_Y_SV+"</b></u>开挖深度虽未超过5m，但地质条件、周围环境和地下管线复杂，或影响毗邻建筑（构筑）物安全的基坑(槽)的土方开挖、支护、降水工程;</li>"
					i+=1;
				}
				if(obj.GLGJ_Y=="1"||obj.TSGD>=5||obj.TSKD>=10 ||obj.SGZZH>=10 ||obj.JZXZH>=15 ||obj.ZHICHENGGC_Y=="1" ||obj.CSDDJZZH!=""){
					html+="<li style=\"padding-left:10px;line-height:20px;list-style:none;\">"+
					i+".<u><b>"+obj.GLGJ_Y_SV+"</b></u>各类工具式模板工程：包括打模板、滑模、爬模、飞模工程;混凝土模板支撑工程：搭设高度<u><b>"+obj.TSGD+"</b></u>m；"+
					"搭设跨度<u><b>"+obj.TSKD+"</b></u>m，施工总载荷<u><b>"+obj.SGZZH+"</b></u>KN/m2，集中线载荷<u><b>"+obj.JZXZH+"</b></u>KN/ "+
					"m2；<u><b>"+obj.ZHICHENGGC_Y_SV+"</b></u>高度大于支撑水平投影宽度且相对独立无联系构件的混凝土模板支撑工程;承重支撑系统：用于钢结构安装等满堂支撑体系，承受单点集中荷载<u><b>"+obj.CSDDJZZH+"</b></u>kg;</li>"
					i+=1;
				}
				if(obj.DJQDZL>=10||obj.QZL!=""||obj.AZGD!=""){
					html+="<li style=\"padding-left:10px;line-height:20px;list-style:none;\">"+
					i+".采用非常规起重设备、方法，单件起吊重量<u><b>"+obj.DJQDZL+"</b></u>KN的起重吊装工程;起重量<u><b>"+obj.QZL+"</b></u>KN的起重设备安装工程、"+
					"安装高度<u><b>"+obj.AZGD+"</b></u>m的起重设备的拆除工程;</li>"
					i+=1;
				}if(obj.TS_GD>=24||obj.TISHENG_HEIGHT!=""||obj.JIATI_HEIGHT!=""||obj.DLJSJGC_Y=="1"||obj.ZZYCPT_Y=="1"||obj.JSJGC_Y=="1"){
					html+="<li style=\"padding-left:10px;line-height:20px;list-style:none;\">"+
					i+".搭设高度<u><b>"+obj.TS_GD+"</b></u>m的落地式钢管脚手架工程;提升高度<u><b>"+obj.TISHENG_HEIGHT+"</b></u>m的附着式整体和分片提升脚手架工程;"+
					"架体高度<u><b>"+obj.JIATI_HEIGHT+"</b></u>m的悬挑脚手架工程;<u><b>"+obj.DLJSJGC_Y_SV+"</b></u>吊篮脚手架工程;<u><b>"+obj.ZZYCPT_Y_SV+"</b></u>自制卸料平台、移动操作平台工程;"+
					"<u><b>"+obj.JSJGC_Y_SV+"</b></u>新型及异型脚手架工程;</li>"
					i+=1;
				}if(obj.CCGC_Y=="1"||obj.BPCCGC_Y=="1"){
					html+="<li style=\"padding-left:10px;line-height:20px;list-style:none;\">"+
					i+".<u><b>"+obj.CCGC_Y_SV+"</b></u>建筑物、构筑物拆除工程;<u><b>"+obj.BPCCGC_Y_SV+"</b></u>采用爆破拆除工程;<u><b>"+obj.GJWCCGC_Y_SV+"</b></u>"+
					"码头、桥梁、高架、烟囱、水塔或拆除中容易引起有毒有害气（液）体或粉尘扩散、易燃易爆事故发生的特殊建、构筑物拆除工程;<u><b>"+obj.YXCCGC_Y_SV+"</b></u>"+
					"可能影响行人、交通、电力设施、通讯设施或其他建、构筑物安全的拆除工程;<u><b>"+obj.BFJZCCGC_Y_SV+"</b></u>文物保护建筑、优秀历史建筑历史文化风貌区控制范围的拆除工程;</li>"
					i+=1;
				}if(obj.QT_SG_HEIGHT!=""){
					html+="<li style=\"padding-left:10px;line-height:20px;list-style:none;\">"+
					i+".施工高度<u><b>"+obj.QT_SG_HEIGHT+"</b></u>m的建筑幕墙安装工程;</li>"
					i+=1;
				}if(obj.QT_KD_GJG!=""||obj.QT_KD_SMJG!=""){
					html+="<li style=\"padding-left:10px;line-height:20px;list-style:none;\">"+
					i+".跨度<u><b>"+obj.QT_KD_GJG+"</b></u>m的钢结构安装工程;跨度<u><b>"+obj.QT_KD_SMJG+"</b></u>m的网架和索模结构安装工程;</li>"
					i+=1;
				}
				$("#jdwxyhtml").html(html);
			}
		} 
	});
}
var wxystatus="";
function showStatus(obj){//0：未提交 30：已提交 33：已审核  32：审核未通过
	var type="";
	 if(obj.GC_TYPE=="基坑支护、降水及土方开挖工程"){
		type="SJK";
	 }else if(obj.GC_TYPE=="模板工程及支撑体系"){
		 type="GDMB";
	 }else if(obj.GC_TYPE=="起重吊装安装拆卸工程"){
		 type="DZGC";
	 }else if(obj.GC_TYPE=="脚手架工程"){
		 type="JSJ";
	 }else if(obj.GC_TYPE=="拆除、爆破工程"){
		 //type="JSJ";
	 }else if(obj.GC_TYPE=="幕墙工程"){
		 type="MQ";
	 }else if(obj.GC_TYPE=="钢结构工程"){
		 if(obj.GC_Z_TYPE=="钢结构工程GJG"){
			 type="GJG";
	     }else if(obj.GC_Z_TYPE=="钢结构工程WJ"){
	    	 type="WJ";
	     }
     }
	var status = getGcStatus(obj.GC_UID,type);
	var html="";
	wxystatus=status;
	if(status=="0"){
		html += "未申请";
	}else if(status=="30"){
		html += "待审核";
	}else if(status=="33"){
		html += "监理<br/>已审核";
	}else if(status=="32"){
		html += "未通过";
	}else{
		html += "未填报";
	}
	return html;
}

function getGcStatus(id,type){
	var stu="";
	$.ajax({
		url : "${pageContext.request.contextPath }/wxy/sgSpWxygcqdController/queryGcStatus?id="+id+"&type="+type,
		data : null,
		cache : false,
		async : false,
		dataType : "json",
		type : 'post',
		success : function(response) {
			stu=response;
		}
	});		
	return stu;
}

function tianbao(obj){
	var html=" ";
	if(wxystatus!="0"){
    html = "<button style=\"margin-left:5px;\" id=\"btnInsert\" onclick=\"showTbMsg('"+obj.GC_TYPE+"','"+obj.GC_Z_TYPE+"','view')\" class=\"btn\" type=\"button\">查看</button>";
	}
	return html;
}

function print(obj){
	var html=" ";
	if(wxystatus!="0"){
	html = "<button style=\"margin-left:5px;\" id=\"btnInsert\" onclick=\"showTbMsg('"+obj.GC_TYPE+"','"+obj.GC_Z_TYPE+"','print')\" class=\"btn\" type=\"button\">打印</button>";
	}
	return html;
}

function showTbMsg(type,ztype,openType){
	if(type=="基坑支护、降水及土方开挖工程"){
		window.open("${pageContext.request.contextPath}/jsp/gdzxt/wxy/wxySjk-add.jsp?openType="+openType+"&gcid="+$("#GC_ID").val(),"基坑填报","height=600, width=850");	
    }else if(type=="模板工程及支撑体系"){
    	window.open("${pageContext.request.contextPath}/jsp/gdzxt/wxy/wxyGdmb-add.jsp?openType="+openType+"&gcid="+$("#GC_ID").val(),"基坑填报","height=600, width=850");
    }else if(type=="起重吊装安装拆卸工程"){
    	window.open("${pageContext.request.contextPath}/jsp/gdzxt/wxy/wxyDzgc-add.jsp?openType="+openType+"&gcid="+$("#GC_ID").val(),"基坑填报","height=600, width=850");
    }else if(type=="脚手架工程"){
    	window.open("${pageContext.request.contextPath}/jsp/gdzxt/wxy/wxyJsj-add.jsp?openType="+openType+"&gcid="+$("#GC_ID").val(),"基坑填报","height=600, width=850");
    }else if(type=="拆除、爆破工程"){
    	window.open("${pageContext.request.contextPath}/jsp/gdzxt/wxy/wxySjk-add.jsp?openType="+openType+"&gcid="+$("#GC_ID").val(),"基坑填报","height=600, width=850");
    }else if(type=="幕墙工程"){
    	window.open("${pageContext.request.contextPath}/jsp/gdzxt/wxy/wxyMq-add.jsp?openType="+openType+"&gcid="+$("#GC_ID").val(),"基坑填报","height=600, width=850");
    }else if(type=="钢结构工程"){
    	if(ztype=="钢结构工程GJG"){
    		window.open("${pageContext.request.contextPath}/jsp/gdzxt/wxy/wxyGjg-add.jsp?openType="+openType+"&gcid="+$("#GC_ID").val(),"基坑填报","height=600, width=850");	
    	}else if(ztype=="钢结构工程WJ"){
    		window.open("${pageContext.request.contextPath}/jsp/gdzxt/wxy/wxyWj-add.jsp?openType="+openType+"&gcid="+$("#GC_ID").val(),"基坑填报","height=600, width=850");
    	}
    }
}

var index=1;
function wxyView(rowObject) {
	var html="";
	 if(rowObject.GC_TYPE=="基坑支护、降水及土方开挖工程"){
			html+="开挖深度<u><b>"+rowObject.PRM1+"</b></u>m的基坑（槽）的土方开挖、支护、降水工程;<br/><u><b>"+rowObject.PRM2+"</b></u>开挖深度虽未超过5m，但地质条件、周围环境和地下<br/>管线复杂，或影响毗邻建筑（构筑）物安全的基坑(槽)的土方开挖、支护、降水工程;"
	 }else if(rowObject.GC_TYPE=="模板工程及支撑体系"){
			html+="<u><b>"+rowObject.PRM1+"</b></u>各类工具式模板工程：包括打模板、滑模、爬模、飞模<br/>工程;混凝土模板支撑工程：搭设高度<u><b>"+rowObject.PRM2+"</b></u>m；"+
			"搭设跨度<u><b>"+rowObject.PRM3+"</b></u>m，<br/>施工总载荷<u><b>"+rowObject.PRM4+"</b></u>KN/m2，集中线载荷<u><b>"+rowObject.PRM5+"</b></u>KN/ <br/>"+
			"m2；<u><b>"+rowObject.PRM6+"</b></u>高度大于支撑水平投影宽度且相对独立无联系构件的混凝土模板支撑工程;<br/>承重支撑系统：用于钢结构安装等满堂支撑体系，承受单点集中荷载<u><b>"+rowObject.PRM7+"</b></u>kg;"
	 }else if(rowObject.GC_TYPE=="起重吊装安装拆卸工程"){
			html+="采用非常规起重设备、方法，单件起吊重量<u><b>"+rowObject.PRM1+"</b></u>KN的起重吊装工程;<br/>起重量<u><b>"+rowObject.PRM2+"</b></u>KN的起重设备安装工程、<br/>"+
			"安装高度<u><b>"+rowObject.PRM3+"</b></u>m的起重设备的拆除工程;"
	 }else if(rowObject.GC_TYPE=="脚手架工程"){
			html+="搭设高度<u><b>"+rowObject.PRM1+"</b></u>m的落地式钢管脚手架工程;<br/>提升高度<u><b>"+rowObject.PRM2+"</b></u>m的附着式整体和分片提升脚手架工程;<br/>"+
			"架体高度<u><b>"+rowObject.PRM3+"</b></u>m的悬挑脚手架工程;<br/><u><b>"+rowObject.PRM4+"</b></u>吊篮脚手架工程;<u><b>"+rowObject.PRM5+"</b></u>自制卸料平台、移动操作平台工程;<br/>"+
			"<u><b>"+rowObject.PRM1+"</b></u>新型及异型脚手架工程;"
	 }else if(rowObject.GC_TYPE=="拆除、爆破工程"){
			html+="<u><b>"+rowObject.PRM1+"</b></u>建筑物、构筑物拆除工程;<u><b>"+rowObject.PRM2+"</b></u>采用爆破拆除工程;<br/><u><b>"+rowObject.PRM3+"</b></u>"+
			"码头、桥梁、高架、烟囱、水塔或拆除中容易引起有毒有害气（液）体或粉尘扩散、<br/>易燃易爆事故发生的特殊建、构筑物拆除工程;<br/><u><b>"+rowObject.PRM4+"</b></u>"+
			"可能影响行人、交通、电力设施、通讯设施或其他建、构筑物安全的拆除工程;<br/><u><b>"+rowObject.PRM5+"</b></u>文物保护建筑、优秀历史建筑历史文化风貌区控制范围的拆除工程;"
	 }else if(rowObject.GC_TYPE=="幕墙工程"){
			html+="施工高度<u><b>"+rowObject.PRM1+"</b></u>m的建筑幕墙安装工程;"
	 }else if(rowObject.GC_TYPE=="钢结构工程"){
		 if(rowObject.GC_Z_TYPE=="钢结构工程GJG"){
			html+="跨度<u><b>"+rowObject.PRM1+"</b></u>m的钢结构安装工程;"
	     }else if(rowObject.GC_Z_TYPE=="钢结构工程WJ"){
			html+="跨度<u><b>"+rowObject.PRM1+"</b></u>m的网架和索模结构安装工程;"
	     }
     }
	 index+=1;
	 if(html==""){
		 html="无危险性较大的分部分项工程";
	 }
     return html;
	}

function gcgl(obj){
	var type="";
	if(obj.GC_TYPE=="基坑支护、降水及土方开挖工程"){
		type="SJK";
	 }else if(obj.GC_TYPE=="模板工程及支撑体系"){
		 type="GDMB";
	 }else if(obj.GC_TYPE=="起重吊装安装拆卸工程"){
		 type="DZGC";
	 }else if(obj.GC_TYPE=="脚手架工程"){
		 type="JSJ";
	 }else if(obj.GC_TYPE=="拆除、爆破工程"){
		 //type="JSJ";
	 }else if(obj.GC_TYPE=="幕墙工程"){
		 type="MQ";
	 }else if(obj.GC_TYPE=="钢结构工程"){
		 if(obj.GC_Z_TYPE=="钢结构工程GJG"){
			 type="GJG";
	     }else if(obj.GC_Z_TYPE=="钢结构工程WJ"){
	    	 type="WJ";
	     }
     }
	var html="<a href='javascript:void(0)' onclick='guocheng(\""+type+"\")'>过程管理</a>";
	return html;
}
function guocheng(type){
	//	window.open("${pageContext.request.contextPath }/jsp/gdzxt/sgsx/sxsp/yw_frame.jsp?gcid="+$("#GDZXT_XM_ID").val(),"审批业务信息");

	    $("#gdzxt_gcid").val(parent.document.getElementById("GDZXT_XM_ID").value);
		$("#gc_type").val(type);
        var f =document.getElementById('wxyformid');
        var url='${pageContext.request.contextPath }/wxy/gdzxt/wxy/wxy_frame';
        //var url='${pageContext.request.contextPath }/pagegdzxt/framework/gdzxt/frame_gdzxt_main';
		f.action=url;
		f.target="_blank"; 
		f.submit();
	}

//回车事件
function enterSumbit(){  
    var event=arguments.callee.caller.arguments[0]||window.event;//消除浏览器差异  
   if (event.keyCode == 13){  
	   $("#btnQuery").click();  
   }  
}  
</script>
<form id="wxyformid" method="post" >
		<input type="hidden" name="gdzxt_gcid" id="gdzxt_gcid">
		<input type="hidden" name="gc_type" id="gc_type" >
</form>
</html>