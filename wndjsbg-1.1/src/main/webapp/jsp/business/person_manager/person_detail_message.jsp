<%@page import="com.ccthanking.framework.Constants"%>
<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<title>详细信息</title>
<%
	String type=(String)request.getAttribute("type");
	String id=(String)request.getAttribute("id");
	
	System.out.print(type);
%>
<app:base/>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping

var controllername="${pageContext.request.contextPath }/person/SgPersonLibraryController/";
var controllernamePersonZhengshu="${pageContext.request.contextPath }/person/SgPersonZhengshuController/"; 

var type ="<%=type%>";
var id ="<%=id%>";
//页面初始化
$(function() {
	init();
//按钮删除事件 
$("#btnDel").click(function() {
    //两个表都得删 
    	if(confirm("您确定要删除本条信息吗？ ")){ 
        deletePerson(); 
        window.close();
     //调用父窗口方法 
    	window.opener.closeParentCloseFunction();
    	}
   });

$("#btnShutDown").click(function() {
       window.close();
      //调用父窗口方法 
   	   window.opener.closeParentCloseFunction();  
})


});

//页面默认参数
function init(){
	$("span[id='addFileSpan']").each(function(){
		$(this).hide();
	})
	
	$("#QID").val(id);
	 //查询表单赋值
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
			$("#sgdetailmessageForm").setFormValues(resultobj);
			$("#QID").val(resultobj.SG_PERSON_LIBRARY_UID);
			$("#QID1").val(resultobj.SG_PERSON_LIBRARY_UID);
			$("#SG_COMPANY_UID").val(resultobj.SG_COMPANY_UID); 
			$("#SG_PERSON_LIBRARY_UID").val(resultobj.SG_PERSON_LIBRARY_UID); 
			$("#STATUS").val(resultobj.STATUS); 
			
			var status=resultobj.STATUS;
			
			queryFileData2('SG_PERSON_LIBRARY_UID','parent','SG_PERSON_LIBRARY');
		}
	});
	       loadPersonZhengshu();

}

function checkStatus(){
	var ty="";
	var flag =true;
	$.ajax({
			url : controllername+"queryStatusIsEmpty?shenfenID="+$("#SHENFENZHENG").val()+"&personUID="+$("#SG_PERSON_UID").val()+"&ty="+ty,
			data : {},
			cache : false,
			async :	false,
			dataType : "json",  
			type : 'post',
			success : function(response) {
				if(response.msg!=null&&response.msg!="0"&&response.msg!=""){//如果数据库中有该ID
					flag = false;
					alert("该信息人员库中已存在! ");
					return flag;
				}
				else{
					return flag;}
			}
		});
		return flag;
	}

function loadPersonZhengshu(){
	
	var data = combineQuery.getQueryCombineData(queryForm1,frmPost,DT1); 
	//调用ajax插入
	defaultJson.doQueryJsonList(controllernamePersonZhengshu+"query",data,DT1,null,true);
    //处理IE浏览器在查询无记录时出现的滚动条问题
	 var rows = $("tbody tr" ,$("#DT1"));
		if(rows.size()==0){
			$("tbody" ,$("#DT1")).append("<tr><td colspan=\"7\" style=\"height: 1px;\" align=\"center\">暂无记录</td></tr>");
		}
}
//点击附件显示，连接到另一个显示页面 
function doFjJx(obj){
	if(obj.FJS!='0'){
		return '<a href="javascript:void(0)" onclick="linkFj('+obj.SG_PERSON_ZHENGSHU_UID+',\'SG_PERSON_ZHENGSHU\')"><img src="${pageContext.request.contextPath }/images/icon-annex.png"/></a>';

		}else{
		return '<div style="text-align:center">—</div>';
	}
}

function linkFj(targetId,businessType){
	//window.showModalDialog('${pageContext.request.contextPath }/jsp/business/commons/showFiles.jsp?target_uid='+targetId,'查看附件','dialogWidth:400px;dialogHeight:300px;');
	//window.open("${pageContext.request.contextPath }/jsp/business/commons/showFiles.jsp?target_uid="+targetId,"查看附件","height=400, width=800, top=200, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no");
	window.open("${pageContext.request.contextPath }/fujianShow/"+targetId+"/"+businessType+".do","查看附件","height=400, width=800, top=200, left=200, toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no");
	
	
}

function setFormValues(){
	var data = combineQuery.getQueryCombineData(queryForm,frmPost);
	var data1 = {
		msg : data
	};
	$.ajax({
		url : controllernameList+"queryList",
		data : data1,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			builderForm(response);
		}
	});
}

function builderForm(response){
	$("#resultXML").val(response.msg);
	var resultobj = defaultJson.dealResultJson(response.msg);
	$("#sgdetailmessageForm").setFormValues(resultobj);
 
}

</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">
    <div class="B-small-from-table-autoConcise">
     <form method="post" id="queryForm"  >
      <table class="B-table" width="100%">
      <!--可以再此处加入hidden域作为过滤条件 -->
      	<TR  style="display:none;">
	        <TD class="right-border bottom-border">
	        	<INPUT type="text" class="span12" kind="text" id="QID" name="ID"  fieldname="SG_PERSON_LIBRARY_UID" value="" operation="="/>
	        </TD>
			<TD class="right-border bottom-border">
			</TD>
        </TR>
      </table>
      </form>
      <form method="post" id="queryForm1"  >
      <table class="B-table" width="100%">
      <!--可以再此处加入hidden域作为过滤条件 -->
      	<TR  style="display:none;">
	        <TD class="right-border bottom-border">
	        	<INPUT type="text" class="span12" kind="text" id="QID1" name="ID"  fieldname="SG_PERSON_UID" value="" operation="="/>
	        </TD>
			<TD class="right-border bottom-border">
			</TD>
        </TR>
      </table>
      </form>
    </div>
	<div class="B-small-from-table-autoConcise">
     <span class="pull-right">
            <button id="btnShutDown" class="btn" type="button">关闭</button>
      </span>
    <form method="post" id="sgdetailmessageForm">
	       <%-- <div class="tab-content"> --%>
				<!-- 静态信息tab页 -->
				<div class="tab-pane active" id="xmsc1" style="height:100%"> 
				<table class="B-table" width="100%" >
				      <input type="hidden" id="STATUS" fieldname="STATUS" name ="STATUS"/>
				      <input type="hidden" id="SG_COMPANY_UID" fieldname="SG_COMPANY_UID" name = "SG_COMPANY_UID"/>
			          <input type="hidden" id="SG_PERSON_LIBRARY_UID" fieldname="SG_PERSON_LIBRARY_UID" name = "SG_PERSON_LIBRARY_UID"/>
			          <input type="hidden" id="SG_PERSON_ZHENGSHU_UID" fieldname="SG_PERSON_ZHENGSHU_UID" name = "SG_PERSON_ZHENGSHU_UID"/>
			          <input type="hidden" id="SG_PERSON_UID" fieldname="SG_PERSON_UID" name = "SG_PERSON_UID"/>
				  	<tr>
						<th width="15%" class="right-border bottom-border text-right">所属企业</th>
			       	 	<td class="bottom-border right-border">
			         		<input class="span12" style="width:50%" id="COMPANY_NAME" type="text" fieldname="COMPANY_NAME" name = "COMPANY_NAME" readonly="true" />
			         	</td>
			       </tr>
				  	<tr>
						<th width="15%" class="right-border bottom-border text-right ">姓名</th>
			       	 	<td class="bottom-border right-border">
			         		<input class="span12" style="width:50%" id="PERSON_NAME" type="text"  fieldname="PERSON_NAME" name = "PERSON_NAME" readonly="true" />
			         	</td>
			        </tr>
			        <tr>
						<th width="15%" class="right-border bottom-border text-right ">性别</th>
						<td  class="right-border bottom-border">
							<input class="span12" style="width:50%" id="SEX" type="text"   fieldname="SEX" name = "SEX" readonly="true" />
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right ">身份证号</th>
						<td width="80%" class="right-border bottom-border">
							<input id="SHENFENZHENG" class="span12" style="width:50%"  check-type="maxlength" maxlength="18" name="SHENFENZHENG" fieldname="SHENFENZHENG" type="text" readonly="true"/>
						</td>
					</tr>
					<tr>
					    <th width="15%" class="right-border bottom-border text-right ">身份证扫描件</th>
						<td  class="right-border bottom-border">
						<div>
						      <span class="btn btn-fileUpload" id="addFileSpan" onclick="doSelectFile(this,'parent');" ywid="SG_PERSON_LIBRARY_UID" target_type="2" file_type="16" business_type="SG_PERSON_LIBRARY">
									<i class="icon-plus"></i>
									<span>添加文件</span>
								</span>
								<table role="presentation" class="table table-striped">
									<tbody onlyView="true" fjlb="0701" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>
								</table>
							</div>
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right ">电话号码</th>
						<td  class="right-border bottom-border">
							<input class="span12" style="width:50%" id="PHONE" type="text"  check-type="maxlength" maxlength="11" fieldname="PHONE" name = "PHONE"  readonly="true"/>
		     		   </td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right ">EMAIL</th>
						<td  class="right-border bottom-border">
							<input id="EMAIL" class="span12" style="width:50%" check-type="maxlength" maxlength="36" name="EMAIL" fieldname="EMAIL" type="text" readonly="true"/>
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right ">职称</th>
						<td  class="right-border bottom-border">
						   <input id="ZHICHENG_NAME" class="span12" style="width:50%" check-type="maxlength" maxlength="36" name="ZHICHENG_NAME" fieldname="ZHICHENG_NAME" type="text" readonly="true"/>
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right ">职称证号</th>
						<td  class="right-border bottom-border">
							<input id="ZHICHENG_CODE" class="span12" style="width:50%" check-type="maxlength" maxlength="36" name="ZHICHENG_CODE" fieldname="ZHICHENG_CODE" type="text" readonly="true"/>
						</td>
					</tr>
					<tr>	
						<th width="15%" class="right-border bottom-border text-right ">职称扫描件</th>
						<td  class="right-border bottom-border">
							<div>
						        <span class="btn btn-fileUpload" id="addFileSpan" onclick="doSelectFile(this,'parent');" ywid="SG_PERSON_LIBRARY_UID" target_type="2" file_type="12" business_type="SG_PERSON_LIBRARY">
									<i class="icon-plus"></i>
									<span>添加文件</span>
								</span>
								<table role="presentation" class="table table-striped">
									<tbody onlyView="true" fjlb="0701" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>
								</table>
							</div>
						</td>
					</tr>
					<tr>
					    <input type="hidden" id="SG_PERSON_ZHENGSHU_UID" fieldname="SG_PERSON_ZHENGSHU_UID" name="SG_PERSON_ZHENGSHU_UID"/>
						<th width="15%" class="right-border bottom-border text-right ">资格证书</th>
						<td  class="right-border bottom-border">
						<div class="overFlowX">
						<table class="table-hover table-activeTd B-table" noprint="true" width="100%" id="DT1" noPage="true" type="single" pageNum="10000">
						 <thead>
	                	  <tr>
	                	    <th fieldname="ZHENGSHU_NAME" width="10%" colindex=0 tdalign="center" >&nbsp;证书类型&nbsp;</th>
							<th fieldname="ZHUANYE_NAME" width="15%" colindex=1 tdalign="center" >&nbsp;注册专业&nbsp;</th>
							<th fieldname="ZHENGSHU_CODE" width="10%" colindex=2 tdalign="center" >&nbsp;证书编号或注册编号&nbsp;</th>
							<th fieldname="BEGIN_DATE" width="10%" colindex=3 tdalign="center" >&nbsp;注册日期&nbsp;</th>
							<th fieldname="END_DATE" width="10%" colindex=4 tdalign="center" >&nbsp;有效期&nbsp;</th>
							<th fieldname="FJS" width="10%" colindex=4 tdalign="center" CustomFunction="doFjJx">&nbsp;附件&nbsp;</th>
						  </tr>
	                    </thead>
	                    <tbody></tbody>
						</table>
						</div>
						</td>
					</tr>
					<tr>
						<th width="15%" class="right-border bottom-border text-right ">合同起止日期</th>
						<td  class="right-border bottom-border">
							<input id="BEGIN_DATE"  class="span9" style="width:18%;;"  name="BEGIN_DATE" fieldname="BEGIN_DATE" type="date" readonly="true"/>
						    —
						    <input id="END_DATE"  class="span9" style="width:18%;"   name="END_DATE" fieldname="END_DATE" type="date" readonly="true"/>
						</td>
					</tr>	
					<tr>  
					   <th width="15%" class="right-border bottom-border text-right ">劳动合同扫描件</th>
					   <td>
						 <div>
						    <span class="btn btn-fileUpload" id="addFileSpan" onclick="doSelectFile(this,'parent');" ywid="SG_PERSON_LIBRARY_UID" target_type="2" file_type="13" business_type="SG_PERSON_LIBRARY">
								<i class="icon-plus"></i>
								<span>添加文件</span>
							</span>
							<table role="presentation" class="table table-striped">
								<tbody onlyView="true" fjlb="0701" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>
							</table>
							</div>
						</td>
					</tr>	
			        
					<tr>
					    <th width="15%" class="right-border bottom-border text-right ">社保证明扫描件</th>
						<td>  
						    <div>
								 <span class="btn btn-fileUpload" id="addFileSpan" onclick="doSelectFile(this,'parent');" ywid="SG_PERSON_LIBRARY_UID" target_type="2" file_type="14" business_type="SG_PERSON_LIBRARY">
									<i class="icon-plus"></i>
									<span>添加文件</span>
								</span>
								<table role="presentation" class="table table-striped">
									<tbody onlyView="true" fjlb="0701" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>
								</table>
							</div>
						</td>
					</tr>	
			        <tr>
				        <th width="15%" class="right-border bottom-border text-right ">备注或个人简介</th>
				        <td  class="bottom-border right-border" >
				        	<textarea class="span12" rows="3" id="DESCRIPTION" check-type="maxlength" maxlength="4000" fieldname="DESCRIPTION" name="DESCRIPTION" readonly="true"></textarea>
				        </td>
			        </tr>
				  
				</table>
			    </div>
			    
				<div class="modal-header" style="background:#0866c6;">
				<h4 id="ryxx_title" style="color:white;">人员审核信息
				</h4>
				</div>
				<table width="100%" class="table-hover table-activeTd B-table" id="RyList" type="single" pageNum="1000" >
				<tr>
				    <th width="15%" class="right-border bottom-border text-right ">审核人</th>
				    <td  class="right-border bottom-border">
							<input id="USER_NAME" class="span12" style="width:50%" check-type="maxlength" maxlength="36" name="USER_NAME" fieldname="USER_NAME" type="text" readonly="true"/>
						</td>
				  </tr> 
				  <tr>
				    <th width="15%" class="right-border bottom-border text-right ">审核结果</th>
				    <td  class="right-border bottom-border">
							<input id="SHENHE_JIEGUO" class="span12" style="width:50%" check-type="maxlength" maxlength="36" name="SHENHE_JIEGUO" fieldname="SHENHE_JIEGUO" type="text" readonly="true"/><%--
						    <input class="span12" id="SPXZZT" type="text" placeholder="" kind="dic" src="SPXZZT" operation="=" name="SPXZZT"  fieldname="SHENHE_JIEGUO">
						--%></td>
				  </tr> 
				  <tr>  
				    <th width="15%" class="right-border bottom-border text-right ">审核时间</th>
				    <td  class="right-border bottom-border">
							<input id="SHENHE_DATE" class="span12" style="width:50%" check-type="maxlength" maxlength="36"  fieldtype="date"  name="SHENHE_DATE" fieldname="SHENHE_DATE" type="text" readonly="true"/>
						</td>
				  </tr> 
				   <tr>  
				    <th width="15%" class="right-border bottom-border text-right ">审核意见</th>
				    <td  class="right-border bottom-border">
							<textarea class="span12" rows="3" id="SHENHE_YIJIAN" check-type="maxlength" maxlength="4000" fieldname="SHENHE_YIJIAN" name="SHENHE_YIJIAN" readonly="true"></textarea>
						</td>
				  </tr> 
				</table>
		</form>
    </div>
   </div>
  </div>
  <jsp:include page="/jsp/file_upload/fileupload_config.jsp" flush="true"/>
  </div>
  <div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id = "queryXML">
         <input type="hidden" name="txtXML" id = "txtXML">
         <input type="hidden" name="txtFilter"  order="desc" fieldname = "a.SERIAL_NO" id = "txtFilter">
         <input type="hidden" name="resultXML" id = "resultXML">
       		 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
		
 	</FORM>
 </div>
</body>
<script>
</script>
</html>