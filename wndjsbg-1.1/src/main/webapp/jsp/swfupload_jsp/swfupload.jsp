<%@ page language="java" pageEncoding="UTF-8"%>

<title>文件上传</title>

<link href="${pageContext.request.contextPath}/js/swfupload/css/swfupload-default.css" rel="stylesheet" type="text/css" />
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/js/swfupload/js/jquery-latest.js"></script>--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/swfupload/js/swfupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/swfupload/js/handlers.js"></script>



<script type="text/javascript">
	var contextPath="${pageContext.request.contextPath}";
	function startLoadswf(){
		//initdataswf("10020","","","510001","0","","1609");
		
	//alert(document.getElementById("ywid_id").value);
		var postdata= new Object();
		postdata.ywid=document.getElementById("ywid_id").value;//$("#ywid_id").val();	
		postdata.glid1=document.getElementById("glid1_id").value;//$("#glid1_id").val();	
		postdata.glid2=document.getElementById("glid2_id").value;//$("#glid2_id").val();
		postdata.glid4=document.getElementById("glid4_id").value;//$("#glid4_id").val();
		postdata.sjbh=document.getElementById("sjbh_id").value;//$("#sjbh_id").val();	
		postdata.ywlx=document.getElementById("ywlx_id").value;//$("#ywlx_id").val();	
		postdata.fjzt=document.getElementById("fjzt_id").value;//$("#fjzt_id").val();		
		postdata.fjlb=document.getElementById("fjlb_id").value;//$("#fjlb_id").val();glid2

		var url="${pageContext.request.contextPath}/UploadSwfServlet"; //处理上传的url
		var sizeLimit="20 MB";// 文件的大小  注意: 中间要有空格
		var types="*.*"; //注意是 " ; " 分割 
		var typesdesc="web-iamge-file"; //这里可以自定义
		var uploadLimit=20;  //上传文件的 个数
		initSwfupload(url,sizeLimit,types,typesdesc,uploadLimit,postdata,contextPath);
	}

	
	//----------------------------------------
	//-对关键字段进行赋值操作，保证新增的记录数据完整
	//-@param ywid  业务ID，可以暂时理解为批次编号
	//-@param glid1   项目ID（或企业材料id
	//-@param sjbh	事件编号
	//-@param ywlx	业务类型
	//-@param fjzt	附件状态
	//-@param glid2   审批材料库ID
	//-@param fjlb  附件类别
	//----------------------------------------	
	function initdataswf(ywid,glid1,sjbh,ywlx,fjzt,glid2,fjlb){
		var inputArr = $(".myKeyValueDivSwf input");
		if($("#ywid").val()==undefined || $("#ywid").val()==""){
			$("#ywid").val(ywid);
		}
		
		for(var xx=0;xx<inputArr.length;xx++){
			if(inputArr[xx].getAttribute("cond")=="true"){
				if(inputArr[xx].getAttribute("condName")=="ywid"){
					inputArr[xx].value=ywid;
				}else if(inputArr[xx].getAttribute("condName")=="glid1"){
					inputArr[xx].value=glid1;
				}else if(inputArr[xx].getAttribute("condName")=="sjbh"){
					inputArr[xx].value=sjbh;
				}else if(inputArr[xx].getAttribute("condName")=="ywlx"){
					inputArr[xx].value=ywlx;
				}else if(inputArr[xx].getAttribute("condName")=="fjzt"){
					if(fjzt!=undefined && fjzt!=""){
						inputArr[xx].value=fjzt;
					}else{
						inputArr[xx].value="1";
					}
				}else if(inputArr[xx].getAttribute("condName")=="glid2"){
					inputArr[xx].value=glid2;
				}else if(inputArr[xx].getAttribute("condName")=="fjlb"){
					inputArr[xx].value=fjlb;
				}
			}
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
			return false;
		} 
	}else{
		return false;
	} 	
}


//----------------------------------------
//-查询文件信息，并插入到表格
//-@param ywid 业务ID，可以暂时理解为批次编号
//-@param glid1 项目ID
//-@param sjbh	事件编号
//-@param ywlx	业务类型
//----------------------------------------
function queryFileDataSwf(){
	
	var obj = new Object();
	obj.YWID = document.getElementById("ywid_id").value;
	//obj.GLID1 = document.getElementById("glid1_id").value;
	//obj.GLID2 = document.getElementById("glid2_id").value;
	//obj.SJBH = document.getElementById("sjbh_id").value;
	//obj.YWLX = document.getElementById("ywlx_id").value;
	//obj.FJLB = document.getElementById("fjlb_id").value;
	
	var data = JSON.stringify(obj);
		var data1 = defaultJson.packSaveJson(data);
		$.ajax({
		url : "${pageContext.request.contextPath }/fileUploadOldController?queryFileListSwf",
		data : data1,
		cache : false,
		async :	false,
		dataType : "json",
		success : function(result) {
			//以下两个方法为 fileupload中间的方法的调用；
			clearFileTab("query");
			insertFileTab(result.msg);
			//queryFileDataSwf
		}
	});
}

</script>
<div  style="display:none;"  >
	<center>
		<input id="plfileuploadswf" type="button" onclick="startLoadswf()"  value="上传"/>
	</center>
</div>
<div style="display:none;" class="myKeyValueDivSwf">
	<input type="hidden" cond="true" condName="ywid" id="ywid_id">
	<input type="hidden" cond="true" condName="glid1" id="glid1_id">
	<input type="hidden" cond="true" condName="glid2" id="glid2_id">
	<input type="hidden" cond="true" condName="glid4" id="glid4_id">
	<input type="hidden" cond="true" condName="sjbh" id="sjbh_id">
	<input type="hidden" cond="true" condName="ywlx" id="ywlx_id">
	<input type="hidden" cond="true" condName="fjzt" id="fjzt_id">
	<input type="hidden" cond="true" condName="fjlb" id="fjlb_id">
</div>
