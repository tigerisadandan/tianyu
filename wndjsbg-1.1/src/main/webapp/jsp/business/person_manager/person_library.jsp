<%@page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext"%>
<%@page import="com.ccthanking.framework.model.User"%>
<%@page import="com.ccthanking.framework.Globals"%><!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>人员信息首页</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/person/SgPersonLibraryController/";
var controllernamePersonZhengshu="${pageContext.request.contextPath }/person/SgPersonZhengshuController/";

//页面初始化
$(function() {
	init();
	//按钮绑定事件（查询）
	$("#btnQuery").click(function() {
		if($("input:radio[name='SHZT']:checked").val()==30){
			var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
			//调用ajax插入
		    defaultJson.doQueryJsonList(controllername+"queryNotNull",data,DT1,null,true);//,null,true无记录时不弹出提示 
		    
			}
		else{
		//生成json串
		var data = combineQuery.getQueryCombineData(queryForm,frmPost2,DT1);
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"query",data,DT1,null,true);
		}
		 var rows = $("tbody tr" ,$("#DT1"));
			if(rows.size()==0){
				$("tbody" ,$("#DT1")).append("<tr><td colspan=\"11\" style=\"height: 1px;\" align=\"center\">暂无记录</td></tr>");
			}
	});
	
	
	//按钮绑定事件（清空）
    $("#btnClear").click(function() {
        $("#find").clearFormResult(); 
        //closeParentCloseFunction();
        init();
    });

    //按钮绑定事件(新增)
	$("#btnInsert").click(function() {
		 
		 window.open("${pageContext.request.contextPath }/sgperson","人员添加");
	    
	});
	//按钮绑定事件(不通过) 
	$("#btnStop").click(function() {
		doSelectPerson();
		 //window.open("${pageContext.request.contextPath }/sgperson","人员添加");
		//delSelectID();
	})
	
});
function doRandering(obj){
	var showHtml = "";
	showHtml = "<a href='javascript:rowView1(this);' title='查看详细信息'><i class='icon-file showXmxxkInfo'></i></a>";
	return showHtml;
}
//给每个多选框附上id值
function doMoreChoose(obj){
	var id=obj.SG_PERSON_LIBRARY_UID;
	return "<input type='checkbox' id='doSelect' name='doSelect' value='"+id+"'/>";
}
//获取删除人员的ID
function delSelectID(liyou){
	$.each($("#DT1").find("input:checkbox[name='doSelect']"),function(i){
		if($("#DT1").find("input:checkbox[name='doSelect']")[i].checked){
			
			var id=$(this).val();
			$("#RENYUAN_ID").val($("#RENYUAN_ID").val()+id+",");
		}
	})
  $.ajax({
		    url : controllername+"tuihui?ids="+$("#RENYUAN_ID").val()+"&yijian="+liyou,//$("#YIJIAN").val(),
			data : {},
			cache : false,
			async :	false,
			dataType : "json",  
			type : 'get',
			success : function(response) {
				$("#resultXML").val(response.msg);
			}
	});
	//$("#btnQuery").click();
	  
	clickRadioShowDate();
	$("#RENYUAN_ID").val("");
}
var optype = 0;
function doSelectPerson(){
	//$.each($("#DT1").find("input:checkbox[name='doSelect']"),function(){
		
		if($("#DT1").find("input:checkbox[name='doSelect']:checked").size()>0){
			optype = 0;
			$(window).manhuaDialog({"title":"退回","type":"text","content":"${pageContext.request.contextPath }/jsp/business/person_manager/because.jsp","modal":"3"});	
		}
		else{
			alert("请选择人员");  
			}
}
//全选\反选
function doAllSelect(demo){
	var flag = true;
	if(!$(demo).checked){
		flag = false;
	}	
	$("input:checkbox[name='doSelect']").each(function(){
		$(this).checked = flag;
	})
}
//全选\反选
function doAllSelect(demo){
	var flag = $(demo).prop("checked");
	$("input:checkbox[name='doSelect']").each(function(i){
		if(i>=0){
			$(this).prop("checked",flag);
		}
	})
}
function rowView1(t){	
	//$("#DT1").setSelect(t);

	if($("#DT1").getSelectedRowIndex()==-1)
	 {
		requireSelectedOneRow();
	    return
	 }
	
		 var rowValue = $("#DT1").getSelectedRow();
	     var tempJson = convertJson.string2json1(rowValue);
	     var status=tempJson.STATUS;
	     
	     if(status==30){

	    	  var t_id=tempJson.SG_PERSON_LIBRARY_UID;
	    	  
	    	  var u_id=tempJson.SG_PERSON_UID;
	    	  
			  $.ajax({
						url : controllername+"queryPersonZhengshu?t_id="+t_id+"&u_id="+u_id,
						data : {},  
						cache : false,
						async :	false,
						dataType : "json",  
						type : 'post',
						success : function(response) {
						$("#resultXML").val(response.msg);
						id=response.msg;
						
						//location.href="${pageContext.request.contextPath }/sgperson/"+tid+"/"+sta+".do";
						//在进入add页面前，把数据进行复制。 
						 window.open("${pageContext.request.contextPath }/sgperson/"+id+"/"+t_id,"人员信息查看");//传递一个ID给详细页面，用于查找 

						}
					});
             }
         else{
             //location.href="${pageContext.request.contextPath }/sgmessage/"+obj.SG_ENTERPRISE_LIBRARY_UID+".do";
             window.open("${pageContext.request.contextPath }/sgpersond/"+tempJson.SG_PERSON_LIBRARY_UID,"人员信息查看");//传递一个ID给详细页面，用于查找 
              }
	  
}
//公司名称换行显示 
function doCn(obj){
	var showHtml = "";
	var arr = obj.COMPANY_NAME;
	var ibs = Math.floor(arr.length/15);
	if(ibs==0){
		showHtml = obj.COMPANY_NAME;
	}else{
		for ( var i = 0; i <= ibs; i++) {
				if(i==ibs){
					showHtml += arr.substring(i*15);
<%--					alert("i倍数："+i+",处理结果："+arr.substring(i*3));--%>
				}else{
					showHtml += arr.substring(i*15,(i+1)*15)+"<br/>";
<%--					alert("i倍数："+i+",处理结果："+arr.substring(i*3,(i+1)*3)+"<br/>");--%>
				}
		}
	}
	return showHtml;
}
//证书换行显示 
function doList(obj){	
	var showHtml = "";
	var rt = "";
	$.ajax({
		url : controllernamePersonZhengshu+"queryZS?id="+obj.SG_PERSON_LIBRARY_UID,
		data : {},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'get',
		success : function(response) {
			rt = response.msg;
		}
	});
	var arr = rt.split(",");
	for (i = 0; i < arr.length; i++) {
		showHtml += (arr[i].toString()+",");
		if((i+1)%2==0){
			showHtml+="<br/>";
		}
	}
	if(showHtml!=""){
		showHtml = showHtml.substring(0,showHtml.length-1);
	}
	return showHtml;
}
//页面默认参数
function init(){
	$("input:radio[name='SHZT']")[3].checked = true;//默认选中这个这个值“1”
	clickRadioShowDate();
	
	$("input:radio[name='SHZT']").each(function(){
		$(this).attr("onchange","clickRadioShowDate()");
	})
}
//单选按钮触发事件 
function clickRadioShowDate(){
	if($("input:radio[name='SHZT']:checked").val()==30){
		$("#btnStop").show();
		//控制一些字段的显示
		$("#doSelect").show();
		$("#doSelect").attr("fieldname","SG_PERSON_LIBRARY_UID");
		$("#TIJIAO_DATE").show();
		$("#TIJIAO_DATE").attr("fieldname","TIJIAO_DATE");
		$("#SHENHE_DATE").attr("fieldname","");
		$("#SHENHE_DATE").hide();
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		//调用ajax插入
	    defaultJson.doQueryJsonList(controllername+"queryNotNull",data,DT1,null,true);//,null,true无记录时不弹出提示 
	    
		}
	else{
		$("#btnStop").hide();
		
		$("#doSelect").attr("fieldname","");
		$("#doSelect").hide();
		
		$("#SHENHE_DATE").show();
		$("#SHENHE_DATE").attr("fieldname","SHENHE_DATE");
		$("#TIJIAO_DATE").attr("fieldname","");
		$("#TIJIAO_DATE").hide();
	var data = combineQuery.getQueryCombineData(queryForm,frmPost2,DT1);
	//调用ajax插入
    defaultJson.doQueryJsonList(controllername+"query",data,DT1,null,true);//,null,true无记录时不弹出提示 
  
	}
	//处理IE浏览器在查询无记录时出现的滚动条问题
    var rows = $("tbody tr" ,$("#DT1"));
	if(rows.size()==0){
		$("tbody" ,$("#DT1")).append("<tr><td colspan=\"11\" style=\"height: 1px;\" align=\"center\">暂无记录</td></tr>");
	}
}
//回调函数--用于修改新增
getWinData = function(data){
	//var subresultmsgobj = defaultJson.dealResultJson(data);
	var data1 = defaultJson.packSaveJson(data);
	if(JSON.parse(data).ID == "" || JSON.parse(data).ID == null){
		defaultJson.doInsertJson(controllername + "insert", data1,DT1);
	}else{
		defaultJson.doUpdateJson(controllername + "update", data1,DT1);
	}
	
};

function EnterPress(){
	if(event.keyCode == 13){
		$("#btnQuery").click();
	}
}
//关闭子窗口，父窗口自动刷新，且父窗口内的信息保持原先状态。 
function closeParentCloseFunction(){
		if(optype==0){return;}
	    var index =	$("#DT1").getSelectedRowIndex();
		var data = combineQuery.getQueryCombineData(queryForm,frmPost,DT1);
		var tempJson = convertJson.string2json1(data);
		var a=$("#DT1").getCurrentpagenum();
		tempJson.pages.currentpagenum=a;
		data = JSON.stringify(tempJson);
		if($("input:radio[name='SHZT']:checked").val()==30){
			defaultJson.doQueryJsonList(controllername+"queryNotNull",data,DT1,null,true);//,null,true无记录时不弹出提示 
		   }
		else{
		defaultJson.doQueryJsonList(controllername+"query",data,DT1,null,true);
		}
		$("#DT1").cancleSelected();
		$("#DT1").setSelect(index);
		//处理IE浏览器在查询无记录时出现的滚动条问题
		  var rows = $("tbody tr" ,$("#DT1"));
			if(rows.size()==0){
				$("tbody" ,$("#DT1")).append("<tr><td colspan=\"11\" style=\"height: 1px;\" align=\"center\">暂无记录</td></tr>");
			}
}
</script>
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
	<p></p>
	<div class="row-fluid">
		<div class="B-small-from-table-autoConcise">
		 
			
			<form method="post" id="queryForm">
				<table class="B-table" width="100%" id="find">
					<!--可以再此处加入hidden域作为过滤条件 -->
					<TR style="display: none;">
						<TD class="right-border bottom-border">
						    <INPUT type="text" class="span12" kind="text" id="STATUS" name="STATUS" fieldname="STATUS" value="" operation="="/>
						</TD>
					</TR>
				<!--可以再此处加入hidden域作为过滤条件 -->
			     <tr>
         	      <th width="4%" class="right-border bottom-border text-right">状态查询</th>
			      <td class="right-border bottom-border" width="19%">&nbsp;
				  <span>
				  <input class="span12" id="SHZT" type="radio" placeholder="" kind="dic" src="SHZT" operation="=" name="SHZT" onchange="clickRadioShowDate()" fieldname="a.STATUS">
				   </span>
			     </td>
			     <%--<td width="70%">
			     <table class="B-table" width="100%" id="find">
			     
			     <tr>--%>
			     <th width="3%" class="right-border bottom-border text-right">姓名</th>
						<td width="6%" class="right-border bottom-border ">
                            <input id="PERSON_NAME" onkeydown="EnterPress()" class="span12" type="text" autocomplete="off" placeholder="" name="PERSON_NAME" check-type="maxlength" maxlength="100" fieldname="PERSON_NAME" operation="like" logic="and"/>
						</td>
				 <th width="3%" class="right-border bottom-border text-right">单位名称</th>
						<td width="13%" class="right-border bottom-border ">
                            <input id="COMPANY_NAME" onkeydown="EnterPress()" class="span12" type="text" autocomplete="off" placeholder="" name="COMPANY_NAME" check-type="maxlength" maxlength="100" fieldname="COMPANY_NAME" operation="like" logic="and"/>
						</td>
				<th width="3%" class="right-border bottom-border text-right">身份证</th>
						<td width="10%" class="right-border bottom-border ">
                            <input id="SHENFENZHENG" onkeydown="EnterPress()" class="span12" type="text" autocomplete="off" placeholder="" name="SHENFENZHENG" check-type="maxlength" maxlength="100" fieldname="SHENFENZHENG" operation="like" logic="and"/>
						</td>
				<td class="right-border bottom-border text-right" width="18%" rowspan="2" style="background-image: none; background-position: initial initial; background-repeat: initial initial;">
	                 <button id="btnQuery" class="btn btn-link"  type="button"><i class="icon-search"></i>查询</button>
	                 <button id="btnClear" class="btn btn-link" type="button"><i class="icon-trash"></i>清空</button>
	                 <button id="btnStop" class="btn btn-link"  type="button">退回</button>
           	   </td>
           	   <%--</tr>
           	   
           	  </table>
           	  </td>
			   --%>
			   </tr>
			   </table>
			</form>
			<form method="post" id="queryForm2"  >
      			<table class="B-table" width="100%">
      			<!--可以再此处加入hidden域作为过滤条件 -->
      				<TR  style="display:none;">
	        			<TD class="right-border bottom-border">
	        			<INPUT type="text" class="span12" kind="text" id="YIJIAN" name="YIJIAN"  fieldname="YIJIAN" value="" />
	        			<INPUT type="text" class="span12" kind="text" id="RENYUAN_ID" name="RENYUAN_ID"  fieldname="RENYUAN_ID" value="" />
	       				 </TD>
						<TD class="right-border bottom-border">
						</TD>
        			</TR>
      			</table>
     	 	</form>
			<div style="height:5px;"> </div>	
			<div class="overFlowX">
			 
	            <table width="100%" class="table-hover table-activeTd B-table" id="DT1" type="single"  pageNum="18">
	              <thead>
	                <tr>
	                		
	                		<th rowspan="2" name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
	                		<th fieldname="SG_PERSON_LIBRARY_UID" id="doSelect" name="doSelect" tdalign="center" colindex=2 CustomFunction="doMoreChoose" >
	                		 <label class="checkbox inline" >
	                        <input type="checkbox" id="allSelect" onclick="doAllSelect(this)" name="allSelect"/>
	                        </label></th>
	                		<th rowspan="2" fieldname="SG_PERSON_LIBRARY_UID" tdalign="center" colindex=2 CustomFunction="doRandering" noprint="true">&nbsp;&nbsp;</th>
	                        <%--<th fieldname="PERSON_NAME" colindex=0 tdalign="center" hasLink="true" linkFunction="rowView1">&nbsp;姓名&nbsp;</th>
	                        --%>
	                        <th fieldname="PERSON_NAME" width="8%" colindex=0 tdalign="center" >&nbsp;姓名&nbsp;</th>
							<th fieldname="COMPANY_NAME" width="20%" colindex=1 tdalign="center" maxlength="30" CustomFunction="doCn">&nbsp;施工单位名称&nbsp;</th>
							<th fieldname="COMPANY_DENGLU_CODE" width="20%" colindex=1 tdalign="center" maxlength="30" hidden>&nbsp;施工单位登录code&nbsp;</th>
							<th fieldname="SEX" width="4%" colindex=3  tdalign="center" >&nbsp;性别&nbsp;</th>
							<th fieldname="SHENFENZHENG" width="15%" colindex=5 tdalign="center" maxlength="30">&nbsp;身份证&nbsp;</th>
							<th fieldname="ZHENGSHU_LIST" width="20%" colindex=4 tdalign="center" maxlength="30" CustomFunction="doList">&nbsp;从业资格证书&nbsp;</th>
							<th fieldname="ZHICHENG_NAME" width="10%" colindex=6 tdalign="center" maxlength="30" >&nbsp;职称&nbsp;</th>
						    <th fieldname="PHONE" width="10%" colindex=7 tdalign="center" maxlength="30" >&nbsp;联系电话&nbsp;</th>
							<th fieldname="TIJIAO_DATE" id="TIJIAO_DATE" width="10%" colindex=8 tdalign="center" >&nbsp;提交时间&nbsp;</th>
							<th fieldname="SHENHE_DATE" id="SHENHE_DATE" width="10%" colindex=8 tdalign="center" >&nbsp;审核时间&nbsp;</th>
	                </thead>
	              	<tbody></tbody>
	           </table>
	       </div>
	 	</div>
	</div>     
</div>
<div align="center">
	<FORM name="frmPost" method="post" style="display: none" target="_blank">
		<!--系统保留定义区域-->
		<input type="hidden" name="queryXML"/> 
		<input type="hidden" name="txtXML"/>
		
		<input type="hidden" name="txtFilter" order="desc" fieldname="TIJIAO_DATE" id="txtFilter"/>
<%--		<input type="hidden" name="txtFilter" order="desc" fieldname="ID" id="txtFilter"/>--%>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
<div align="center">
	<FORM name="frmPost2" method="post" style="display: none" target="_blank">
		<!--系统保留定义区域-->
		<input type="hidden" name="txtFilter" order="desc" fieldname="SHENHE_DATE" id="txtFilter"/>
	</FORM>
</div>
</body>
</html>