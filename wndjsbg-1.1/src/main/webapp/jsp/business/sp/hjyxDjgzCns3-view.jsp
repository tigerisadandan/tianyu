<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>

<title>环境影响登记告知承诺—-饮食娱乐服务类——详细信息</title>
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

var controllername= "${pageContext.request.contextPath }/sp/buSpywHjyxdjgzcns3Controller/";
var controllerfile= "${pageContext.request.contextPath }/controller/fileController/";
var uploadController= "${pageContext.request.contextPath }/fileUploadController/";


var type ="<%=type%>";
var id ="<%=id%>";

$(function() {
	init();

	$("#downLoad").click(function() {
		download();
	});
	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
		if($("#buSpywHjyxdjgzcnsForm").validationButton())
		{
		    //生成json串
		    var data = Form2Json.formToJSON(bU_Spyw_JsgcsgxkzSqForm);
		  //组成保存json串格式
		    var data1 = defaultJson.packSaveJson(data);
		    defaultJson.doInsertJson(controllername + "insert", data1);

		   
		}else{
			requireFormMsg();
		  	return;
		}
	});	
});
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
				//alert(response.msg);
				var resultobj = defaultJson.dealResultJson(response.msg);
				
				$("#SXJ_X").html(resultobj.SXJ_X);
				$("#SXJ_T").html(resultobj.SXJ_T);
				$("#GXJ_X").html(resultobj.GXJ_X);
				$("#GXJ_T").html(resultobj.GXJ_T);
				$("#PSQK").html(resultobj.PSQK);
				$("#FZWRCSJS").html(resultobj.FZWRCSJS);
				
				$("#HJYXDJGZCNS_UID").val(resultobj.HJYXDJGZCNS_UID);
				$("#buSpywHjyxdjgzcnsForm").setFormValues(resultobj);
				$("#buSpywHjyxdjgzcnsForm").setHtmlValue(resultobj);
				readFile();
				show();
		}
		});
	
	}
}
function readFile(){
	
	$.ajax({
		url : controllername+"queryReadFile?id="+$("#HJYXDJGZCNS_UID").val(),
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			//$("#resultXML").val(response.msg);
			//var resultobj = eval('(' + response.msg + ')');
			//var resultobj = convertJson.string2json1(response.msg);
			//var resultmsgobj = convertJson.string2json1(response.msg);
			//var resultobj=subresultmsgobj = resultmsgobj.response.data;
			if(response.msg!=null&&response.msg!="0"&&response.msg!=""){//如果数据库中有该ID
				//console.log(response.msg);写日志  
				var resultobj = eval('(' + response.msg + ')')
				$.each(resultobj,function(){
					//$("#syt").val(this.FILEPATH);//获取返回来的该人员的FILEPATH
					var root=this.FILEROOT;
					var name=this.FILENAME;
					var filename=root+"/"+name;
					//alert(filename);
					$("#syt").val(filename);
					//alert($("#syt").val());
					$("#QID2").val(this.AT_FILEUPLOAD_UID);
					//alert($("#syt").val());
					//alert($("#QID2").val());
					})
				}			
			//$("#syt").attr("src",resultobj);
		}
	});
}
function show(){
	if($("#QID2").val()!=""&&$("#QID2").val()!=="0"){
	$.ajax({
		url : uploadController+"doPreview?fileid="+$("#QID2").val(),
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			$("#resultXML").val(response.msg);
			//alert(response.msg);
	
			$("#syt").attr("src",response.msg);
		}
	});
	}
}
function download(){
	
	$.ajax({
		url : controllername+"download?id="+$("#HJYXDJGZCNS_UID").val(), 
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
</script>    
</head>
<body>

<div class="container-fluid">
	</p>	
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
	        	<INPUT type="text" class="span12" kind="text" id="QID2" name="ID"  fieldname="HJYXDJGZCNS_UID" value="" operation="="/>
	        </TD>
			<TD class="right-border bottom-border">
			</TD>
        </TR>
      </table>
      </form>
   <div style="height:5px;"></div>
	<div class="B-small-from-table-autoConcise">
       <span class="pull-right">
      		<button id="btnShutdown" onclick="window.close();" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
      	    <button id="downLoad" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">打印</button>
      	</span>
      	<br/>
      
     <form method="post" id="buSpywHjyxdjgzcnsForm"  >
     <h4 class="title" align="center">无锡市新区建设项目环境影响评价申请表（饮食娱乐服务类）</h4>
    <table width="750" height="1500" border="1" align="center" >
     <input type="hidden" id="HJYXDJGZCNS_UID" fieldname="HJYXDJGZCNS_UID" name = "HJYXDJGZCNS_UID"/>
     <input type="hidden" id="YWLZ_UID" fieldname="YWLZ_UID" name = "YWLZ_UID"/>
  <tr>
    <td height="43" colspan="7" align="left"><strong>（一）建设项目基本信息</strong></td>
  </tr>
  <tr>
    <td width="156" height="25" align="center">项目名称</td>
    <td colspan="6" fieldname="XMMC" >&nbsp;</td>
  </tr>
  <tr>
    <td height="25" align="center">建设单位</td>
    <td colspan="6" fieldname="JSDW">&nbsp;</td>
  </tr>
  <tr>
    <td height="25" align="center">法人代表</td>
    <td colspan="3" fieldname="FRDB">&nbsp;</td>
    <td width="152" align="center">联系人</td>
    <td colspan="2" fieldname="LXR">&nbsp;</td>
  </tr>
  <tr>
    <td height="25" align="center">联系电话</td>
    <td colspan="2" fieldname="LXDH">&nbsp;</td>
    <td width="82" align="center">传真</td>
    <td fieldname="CZ">&nbsp;</td>
    <td width="106" align="center">邮政编码</td>
    <td width="136" fieldname="YZBM">&nbsp;</td>
  </tr>
  <tr>
    <td height="25" align="center">通讯地址</td>
    <td colspan="6" fieldname="TXL" >&nbsp;</td>
  </tr>
  <tr>
    <td height="25" align="center">建设地点</td>
    <td colspan="6" fieldname="JSDD">&nbsp;</td>
  </tr>
  <tr>
    <td height="25" align="center">建设性质</td>
    <td colspan="3" fieldname="JSXZ">&nbsp;</td>
    <td align="center">行业类别及代号</td>
    <td colspan="2" fieldname="HYLBJDM">&nbsp;</td>
  </tr>
  <tr>
    <td height="25" align="center">占地面积</td>
    <td colspan="3" fieldname="ZDMJ">&nbsp;</td>
    <td align="center">绿化面积</td>
    <td colspan="2" fieldname="RHMJ">&nbsp;</td>
  </tr>
  <tr>
    <td height="25" align="center">总投资</td>
    <td colspan="2" fieldname="ZTZ">&nbsp;</td>
    <td align="center" >环保投资</td>
    <td align="left" fieldname="HBTZ">&nbsp;</td>
    <td align="center">投资比列</td>
    <td fieldname="TZBL">%&nbsp;</td>
  </tr>
  <tr>
    <td height="25" align="center">预期投产日期</td>
    <td colspan="3" fieldname="YQTCRQ">&nbsp;</td>
    <td align="center">预计工作日</td>
    <td colspan="2" fieldname="YJGZR">&nbsp;</td>
  </tr>
  <tr>
    <td height="25" colspan="7" align="left"><strong>（二）项目拟选建设地址周围300米范围内主要敏感目标（居民点、河流等）分布状况示意图。</strong></td>
  </tr>
  <tr>
    <td height="25" colspan="7" align="left">示意图：
      <img id="syt" src="">
      </td>
  </tr>
  <tr>
    <td height="25" colspan="7" align="left"><p><strong>（三）经营内容 </strong></p></td>
  </tr>
  <tr>
    <td height="25" colspan="7" align="left"><strong>（1）旅业</strong></td>
  </tr>
  <tr>
    <td height="25" colspan="4" align="left">旅店建筑面积</td>
    <td height="25" colspan="3" align="left" fieldname="RDJZMJ">平方米</td>
  </tr>
  <tr>
    <td height="25" align="center">客房</td>
    <td height="25" colspan="3" align="left" fieldname="KFS">间</td>
    <td height="25" align="center">床位</td>
    <td height="25" colspan="2" align="left" fieldname="CWS">张</td>
  </tr>
  <tr>
    <td height="25" colspan="4" align="left">洗衣房面积</td>
    <td height="25" colspan="3" align="left" fieldname="XYFMJ">平方米</td>
  </tr>
  <tr>
    <td height="25" align="center">湿洗机</td>
    <td height="25" colspan="3" align="left" >
     <span id="SXJ_X"></span>
    型 
     <span id="SXJ_T"></span>
    台
 </td>
    <td height="25" align="center">干洗机</td>
    <td height="25" colspan="2" align="left" >
    <span id="GXJ_X"></span>
    型
    <span id="GXJ_T"></span>
    台</td>
  </tr>
  <tr>
    <td height="25" colspan="7" align="left"><strong>（2）餐饮</strong></td>
  </tr>
  <tr>
    <td height="25" align="center">餐厅面积</td>
    <td height="25" colspan="2" align="left" fieldname="CTMJ">    平方米</td>
    <td height="25" align="center">厨房面积</td>
    <td height="25" align="left" fieldname="QFMJ">平方米</td>
    <td height="25" align="center">炉灶</td>
    <td height="25" align="left" fieldname="LZS">个</td>
  </tr>
  <tr>
    <td height="25" align="center">燃料种类</td>
    <td height="25" colspan="3" align="left" fieldname="RLZL">&nbsp;</td>
    <td height="25" align="center">燃料用量</td>
    <td height="25" colspan="2" align="left" fieldname="RLYL">公斤/日</td>
  </tr>
  <tr>
    <td height="25" colspan="4" align="left">卡拉OK音响</td>
    <td height="25" colspan="3" align="left" fieldname="CT_KLOKYXS">套</td>
  </tr>
  <tr>
    <td height="25" colspan="7" align="left"><strong>（3）歌舞厅</strong></td>
  </tr>
  <tr>
    <td height="25" align="center">娱乐场所面积</td>
    <td height="25" colspan="3" align="left" fieldname="YLCSMJ">平方米</td>
    <td height="25" align="center">DISCO舞厅面积</td>
    <td height="25" colspan="2" align="left" fieldname="DISCOWTMJ">平方米</td>
  </tr>
  <tr>
    <td height="25" colspan="4" align="left">卡拉OK音响</td>
    <td height="25" colspan="3" align="left" fieldname="GWT_KLOKYXS">套</td>
  </tr>
  <tr>
    <td height="25" colspan="7" align="left"><strong>（4）健身娱乐</strong></td>
  </tr>
  <tr>
    <td height="25" colspan="7" align="left">保龄球，桌球</td>
  </tr>
  <tr>
    <td height="25" align="center">球室面积</td>
    <td height="25" colspan="2" align="left" fieldname="QSMJ">平方米</td>
    <td height="25" align="center">球道数</td>
    <td height="25" align="left" fieldname="QDS">道</td>
    <td height="25" align="center">球桌数</td>
    <td height="25" align="left" fieldname="QZS">台</td>
  </tr>
  <tr>
    <td height="25" colspan="7" align="left">桑拿浴，药浴</td>
  </tr>
  <tr>
    <td height="25" align="center">浴室面积</td>
    <td height="25" colspan="3" align="left" fieldname="YSMJ">平方米</td>
    <td height="25" align="center">锅炉型号，数量</td>
    <td height="25" colspan="2" align="left" fieldname="GLXHSL">&nbsp;</td>
  </tr>
  <tr>
    <td height="25" colspan="7" align="left">其它</td>
  </tr>
  <tr>
    <td height="25" align="center">总用水量</td>
    <td height="25" colspan="3" align="left" fieldname="ZYSL">吨</td>
    <td height="25" align="center">废水排放量</td>
    <td height="25" colspan="2" align="left" fieldname="FSPFL">吨</td>
  </tr>
  <tr>
    <td height="25" align="center">重复用水量</td>
    <td height="25" colspan="3" align="left" fieldname="CFYSL">&nbsp;</td>
    <td height="25" align="center">排放去向</td>
    <td height="25" colspan="2" align="left" fieldname="PFQX">&nbsp;</td>
  </tr>
  <tr>
    <td height="25" colspan="7" align="left"><strong>（四）附属设施</strong></td>
  </tr>
  <tr>
    <td height="25" align="center">空调设备</td>
    <td width="119" height="25" align="center" >型号</td>
    <td height="25" colspan="2" align="left" fieldname="KTSB_XH">&nbsp;</td>
    <td height="25" align="center">数量</td>
    <td height="25" colspan="2" align="left" fieldname="KTSB_SL">台</td>
  </tr>
  <tr>
    <td height="25" align="center">发电机</td>
    <td height="25" align="center">型号</td>
    <td height="25" colspan="2" align="left" fieldname="FDJ_XH">&nbsp;</td>
    <td height="25" align="center">数量</td>
    <td height="25" colspan="2" align="left" fieldname="FDJ_SL">台</td>
  </tr>
  <tr>
    <td height="25" align="center">锅炉</td>
    <td height="25" align="center">型号</td>
    <td height="25" colspan="2" align="left" fieldname="GL_XH">&nbsp;</td>
    <td height="25" align="center">数量</td>
    <td height="25" colspan="2" align="left" fieldname="GL_XL">台</td>
  </tr>
  <tr>
    <td height="25" align="center">燃料情况</td>
    <td height="25" align="center">种类</td>
    <td height="25" colspan="2" align="left" fieldname="RLQK_ZL">&nbsp;</td>
    <td height="25" align="center">数量</td>
    <td height="25" colspan="2" align="left" fieldname="RLQK_SL">吨/日</td>
  </tr>
  <tr>
    <td height="25" colspan="7" align="left">其它设备设施：</td>
  </tr>
  <tr>
    <td height="25" colspan="7" align="left" fieldname="QTSBSS">
     
   </td>
  </tr>
  <tr>
    <td height="25" colspan="7" align="left"><strong>（五）排污情况及污染防治措施</strong></td>
  </tr>
  <tr>
    <td height="25" colspan="7" align="left">一、排水情况<br/> <br/>
    <span id="PSQK"></span>
    </td><br/>
  </tr>
  <tr>
    <td height="25" colspan="7" align="left">二、防治废水，废气，噪声污染措施简要说明 <br/> <br/>
    <span id="FZWRCSJS"></span>
    </td>
  </tr>
 
</table>
      	</form>
    </div>
  </div>
</div>
<div align="center">
	<FORM name="frmPost" method="post" style="display:none" target="_blank" id="frmPost">
		<!--系统保留定义区域-->
		<input type="hidden" name="queryXML" id="queryXML"/>
		<input type="hidden" name="txtXML" id="txtXML"/>
		<input type="hidden" name="txtFilter" order="desc" fieldname="CREATED_DATE" id="txtFilter"/>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<input type="hidden" id="queryResult"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>		
	</FORM>
</div>

</body>
</html>