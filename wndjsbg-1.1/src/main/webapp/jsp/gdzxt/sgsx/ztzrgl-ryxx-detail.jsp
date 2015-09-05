<!DOCTYPE html>
<%@page import="com.ccthanking.framework.Constants"%>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>人员详细信息</title>
<%
	String PERSON_UID = request.getParameter("PERSON_UID");
	String GONGCHENG_UID = request.getParameter("GONGCHENG_UID");
	String personType = request.getParameter("personType");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script src="${pageContext.request.contextPath }/js/TweenMax/TweenMax.min.js"></script>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/ywlz/css/default.css"> </LINK>
<style type="text/css">
	.h4-style{
   display: block;
   float: left;
   line-height: 30px;   
   }
   
   #h4_button{
   float:right;
   margin-top: 5px;   
   }
   
   .DisplayBar{
   position:relative;
   float:left;
   width:100px;
   height: 20px;
   border: 1px black solid;
   color: black;
   text-align: center;
   font-weight: bold;
   cursor:pointer;
   overflow: hidden;
   }
   
   .DisplayBar span{
   	position:absolute;
   	width:100px;
   	left:0px;
   	z-index: 10;
   }
      
   .DisplayBar div{
   position:absolute;
   left:0px;
   top:0px;
   width:0px;
   height:100%;
   z-index: 1;
   }
   
   .border-right{
   border-right:none !important;
   border-left: none !important;
   }
</style>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername = "${pageContext.request.contextPath}/sgzxt/getZxtIndexInformationController";
var PERSON_UID ="<%=PERSON_UID%>";
var GONGCHENG_UID ="<%=GONGCHENG_UID%>";
var personType ="<%=personType%>";
//页面初始化
$(function() {
	init();
	
	$("#DisplayBar").bind("click",function(){
		window.open("${pageContext.request.contextPath }/jsp/gdzxt/index/geren-xypf.jsp?person_uid="+PERSON_UID+"&type="+personType);
	});
	
	
	$("#btnClose").bind("click", function(){
		$(this).manhuaDialog.close();
	});
		
}); 

//页面默认参数
function init(){
	//查询人员基本信息
	initRYXX();
	
	//findimages(448,"shengfenzheng");
	
	//findimages(448,"jinzhao");
	//-------确定施工人员/监理人员的身份证/近照的照片的类型
	<%--
	施工人员身份证照片 = 2114;
	施工人员人脸识别照片 = 2115;
	监理人员身份证照片 = 2212;
	监理人员人脸识别照片 = 2213;	
	--%>
	var SHFZH_FILEID = "";
	var JZH_FILEID = "";
	if(personType=="SGRY"){
		SHFZH_FILEID="2114";
		JZH_FILEID="2115";
	}else if(personType=="JLRY"){
		SHFZH_FILEID="2212";
		JZH_FILEID="2213";
	}
	
	//-----处理图片
	findFileid(PERSON_UID,SHFZH_FILEID,"shengfenzheng");
	findFileid(PERSON_UID,JZH_FILEID,"jinzhao");
	
	
	//加入查询人员考勤后打开
	//doProQuery();
}

function doProQuery(){
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT2);
	//调用ajax插入	
	defaultJson.doQueryJsonList(controllername+"?queryRYXX_KaoQing",data,DT1);
}

function initRYXX(){
	//生成json串
	var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
	
	var data1 = {
		msg : data
	};
	
	$.ajax({
		url : controllername + "?queryRYXX_detail&personType="+personType,
		data : data1,
		cache : false,
		dataType : "json",
		type : "post",
		async : true,
		success : function(response) {
			var resultObj = defaultJson.dealResultJson(response.msg);		
			$("#RYXXFORM1").setFormValues(resultObj);

			if(resultObj.SCORE!=""){
				$("#person_score").html(resultObj.SCORE);
				initDisplayBar();
			}

			}
		});
	
}

function initDisplayBar(){
	$(".DisplayBar").each(function(){
		var number=$(this).find("span").eq(0).html();
		if(number==null||number.trim()==""){
			$(this).css("display","none");
			return;
		}
		
		if(number>=95){
			$(this).find("div").eq(0).css("background-color","#00FF00");
		}else if(number>=85&&number<95){
			$(this).find("div").eq(0).css("background-color","#FFFF00");
		}else if(number>70&&number<85){
			$(this).find("div").eq(0).css("background-color","#FF0000");
		}else if(number>=0&&number<=70){
			$(this).find("div").eq(0).css("background-color","#525252");
		}
		
		
		if(number>100){
			TweenMax.to($(this).find("div").eq(0), 5, {width:100,ease:Expo.easeOut,opacity:1});
		}else if(number>=0&&number<=100){
			TweenMax.to($(this).find("div").eq(0), 5, {width:number,ease:Expo.easeOut,opacity:1});
		}		
								
	});
}


function findFileid(PERSON_UID,File_type,imgId){	
	$.ajax({
		url : controllername + "?queryRYXX_Fileid&PERSON_UID="+PERSON_UID+"&File_type="+File_type,
		cache : false,
		dataType : "json",
		type : "post",
		async : true,
		success : function(response) {
			if(response.msg!="0"){
				var resultObj = defaultJson.dealResultJson(response.msg);					
				var fileid = resultObj.FS_FILE_UID;
				findimages(fileid,imgId);
			}
			}
		});
}

function findimages(fileid,imgId){
	actionUrl = "${pageContext.request.contextPath }/fileUploadUtilController?doPreview&fileid="+fileid;
	
	$.ajax({
		url:actionUrl,
		data:"",
		dataType:"json",
		async:false,
		success:function(result){
			var res = result.msg;
			if(res!=""){
				var flag = false;
				if(res.lastIndexOf(".jpg")){
					flag = true;
				}
				if(res.lastIndexOf(".png")){
					flag = true;
				}
				if(flag){
					$("#"+imgId).attr("src",res);
				}
			}
		}
	});
}

</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">
	
	<div class="B-small-from-table-autoConcise" style="width: 65%;float: left;">
	<div class="B-small-from-table-autoConcise">
	<table class="content" width="100%">
		<tr><td class="yw-title"><h4 class="h4-style">人员信息</h4>
			<div id="h4_button" class="pull-right">
			<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
      		<button id="btnXXKBF" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">信息卡补发</button>
      		<button id="btnXXKCF" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">重发</button>
      		<button id="btnXXKQX" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">信息卡取消</button>
        	</div></td>
        </tr>
        <tr><td>
     	<div>
     	<form method="post" id="RYXXFORM1">			   
        	<table width="100%" class="B-table" id="DT1">
        	<tr><th>姓名</th><td class="border-right">
        		<input id="PERSON_NAME" name="PERSON_NAME" type="text" fieldname="PERSON_NAME">
        	</td>
        	<td><div class="DisplayBar"  title="点击查看信用分值信息" id="DisplayBar"><span id="person_score"></span><div></div></div></td>
        	<th>人员编号</th>
        	<td class="border-right">
        		<input id="" name="" fieldname="" type="text" readonly="readonly">
        	</td>
        	<td class="border-right">
        	<label><input type="checkbox" disabled="disabled"/>已发卡</label>
        	</td>
        	<td><a>发卡情况</a></td></tr>
        	<tr><th>身份证号</th><td colspan="2">
        		<input id="SHENFENZHENG" name="SHENFENZHENG" type="text" fieldname="SHENFENZHENG">
        	</td><th>证书编号</th>
        	<td colspan="3">
        		<input id="ZHENGSHU_CODE" name="ZHENGSHU_CODE" type="text" fieldname="ZHENGSHU_CODE">
        	</td></tr>
        	<tr><th>专业</th><td colspan="2">
        		<input id="ZHUANYE" name="ZHUANYE" fieldname="ZHUANYE" type="text">
        	</td><th>联系电话</th><td colspan="3">
        		<input id="PHONE" name="PHONE" fieldname="PHONE" type="text">
        	</td></tr>
           </table>
           </form>
	       </div>
	     </td></tr></table>
    </div>
    

    <div style="height:20px;"></div>
    <div class="B-small-from-table-autoConcise">
    <table class="content" width="100%">
		<tr><td class="yw-title"><h4 class="h4-style">考勤信息</h4>
        </tr>
        <tr><td>
     	<div>
     	<table width="100%" class="table-hover table-activeTd B-table" id="DT2" type="single"  pageNum="18">
               <thead>
               	<tr>
               		<th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;&nbsp;#&nbsp;&nbsp;</th>
               		<th id="" fieldname="" colindex=0 tdalign="center" > &nbsp;&nbsp;工程名称 &nbsp;&nbsp;</th>
               		<th id="" fieldname="" colindex=0 tdalign="center" >&nbsp;考勤日期&nbsp;</th>
					<th id="" fieldname="" colindex=0 tdalign="center">&nbsp;首次考勤&nbsp;</th>
					<th id="" fieldname="" colindex=0 tdalign="center">&nbsp;照片 &nbsp;</th>
					<th id="" fieldname="" colindex=0 tdalign="center">&nbsp;末次考勤 &nbsp;</th>
					<th id="" fieldname="" colindex=0 tdalign="center">&nbsp;照片 &nbsp;</th>	
					<th id="" fieldname="" colindex=0 tdalign="center">&nbsp;状态 &nbsp;</th>
               	</tr>
               </thead>
             	<tbody></tbody>
	    </table>			   
	    </div>
	    </td></tr></table>     
      
       </div>
	   </div>
	       
		<div style="width:200px;float: left;margin-left:20px;text-align: center;">
		<table class="content" width="100%">
		<tr><td class="yw-title"><h4 class="h4-style">照片</h4>
        </tr>
        <tr><td style="background-color: #F5F7FB;">
     	<div>
     	<div style="margin-left:30px;padding-top:10px;background-color:#FFFFFF;text-align: center;width:140px;height:170px;"><img id="jinzhao" width="120px" height="160px">
	    </div>
	    <p>近照</p>
	    <div style="margin-left:30px;padding-top:10px;background-color:#FFFFFF;text-align: center;width:140px;height:170px;"><img id="shengfenzheng" width="120px" height="160px">
	    </div>
	    <p>身份照</p>
	    </div>
	    </td></tr></table>     
	    </div>
	    
	    
	     <form method="post" id="queryForm">
			<table class="B-table" width="100%" id="condition">
				<!--可以再此处加入hidden域作为过滤条件 -->
				<tr style="display: none;">
					<td class="right-border bottom-border"></TD>
					<td class="right-border bottom-border">
						<input type="text" class="span12" kind="text" id="num" fieldname="rownum" value="1000" operation="<="/>
						<input type="text" class="span12" kind="text" id="PERSON_UID" fieldname="V.PERSON_UID" operation="=" value="<%=PERSON_UID%>"/>
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
         <input type="hidden" name="txtFilter" order="ASC" fieldname="V.XUHAO" id="txtFilter"/>
         <input type="hidden" name="resultXML" id = "resultXML">
       	 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">		
 	</FORM>
 </div>
</body>
<script>
</script>
</html>