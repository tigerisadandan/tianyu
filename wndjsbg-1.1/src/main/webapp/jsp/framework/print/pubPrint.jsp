<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext"%>
<%@ page import="com.ccthanking.framework.util.Pub"%>
<%@ page import="com.ccthanking.framework.coreapp.aplink.*"%>
<%@ page import="com.ccthanking.framework.coreapp.aplink.Process"%>

<%@ page import="java.sql.Connection"%>
<%@ page import="com.ccthanking.framework.common.DBUtil"%>

<%@ taglib uri= "/tld/base.tld" prefix="app" %>


<%
   User user = RestContext.getCurrentUser();
   String tt= (String)request.getParameter("param");
   tt=tt.replace('|','&');

   String isEdit=(String)request.getParameter("isEdit");
   String isview=(String)request.getParameter("isview");
   String sjbh=(String)request.getParameter("sjbh");
   String ywlx=(String)request.getParameter("ywlx");
	
   String operationoid = (String)request.getParameter("operationoid");
   if(operationoid==null){
	   operationoid = "";
   }
   String processtype = (String)request.getParameter("processtype");
   String templateid="";
   templateid=(String)request.getParameter("temlateid");
   String spzt = request.getParameter("spzt");
   if(spzt == null || "".equals(spzt)) spzt = "9";
   
   String ywid = (String)request.getParameter("ywid");
   String html ="";
   if("1".equals(processtype)){
	   TaskMgrBean t = new TaskMgrBean();
	 html = t.showSelectPersion(operationoid,user);
   }

  String condition = (String)request.getParameter("condition");
  if(Pub.empty(condition)){
	  condition = "";
  }
%>
<head>

<title>打印处理</title>
<app:base/>
<script src="${pageContext.request.contextPath }/js/base/spws.js"></script>
<script language="javascript" >
g_nameMaxlength = 20;
var ajbh=1;
var sjbh='<%=sjbh%>';
var ywlx='<%=ywlx%>';

var isedit='<%=isEdit%>';
var condition = '<%=condition%>';
function closeT(){

  window.close();
}

$(function() {
	setFileData("","","<%=sjbh%>","<%=ywlx%>");
	queryFileData("","","<%=sjbh%>","<%=ywlx%>");
	$("#blrBtn").click(function(){
		var actorCode = $("#ACTOR").attr("code");
		selectUserTree('multi',actorCode,'setBlr') ;
	});
	$("#blbmBtn").click(function(){
		var deptidCode = $("#DEPTID").attr("code");
		openDeptTree('single',deptidCode,'setBlbm') ;
	});
	
	
});

function showLct(){
	
	$(window).manhuaDialog({"title":"修改节点","type":"text","content":"${pageContext.request.contextPath}/jsp/business/lcgl/lcpz/spFlowView.jsp?processoid=<%=operationoid%>","modal":"1"});
}

function saveWS(){

    var form1 = iFrame.wsTemplate;
    var data1 = null;
    if(form1)
    {
    	var json = Form2Json.formToJSON(form1);
    	if(json.length <= 2){
    		data1= defaultJson.packSaveJson('{"SJBH":"<%=sjbh%>","YWLX":"<%=ywlx%>","WSTEMPLATEID":"<%=templateid%>"}');
    	} else {
    	  	json = json.replace('}',',"SJBH":"<%=sjbh%>","YWLX":"<%=ywlx%>","WSTEMPLATEID":"<%=templateid%>"}');

    	   	data1 = defaultJson.packSaveJson(json);
    	}
    }else
    {
    	data1= defaultJson.packSaveJson('{"SJBH":"<%=sjbh%>","YWLX":"<%=ywlx%>","WSTEMPLATEID":"<%=templateid%>"}');
    }
    var wsActionURL = "${pageContext.request.contextPath }/PubWS.do?saveWSJson";
	$.ajax({
		url : wsActionURL,
		data : data1,
		dataType : 'json',
		async :	false,
		type : 'post',
		//contentType:'application/json;charset=UTF-8',	    
		success : function(result) {

			if(result)
		       flag = result.msg;
			if("0"==flag)
			{
				alert("文书保存出错！");
				return ;	
			}else
			{
				
				var sjbh="<%=sjbh%>";
				var ywlx="<%=ywlx%>";
				//isTS = isTs('<%=operationoid%>');
				
				if("4"=="<%=processtype%>"){
					var dbr = $("#ACTOR").attr("code");
					if(!dbr)
					{
						requireFormMsg("请选择办理人!");
						//$("#ACTOR").focus();
						return;
					}
					CQSPTS(ajbh,sjbh,ywlx,"",'',"",condition,'<%=operationoid%>',dbr);
				}else{
					var dbr = "";
					var dbrName="";
					if($("#ACTOR")[0].tagName=="INPUT"){
						dbr = $("#ACTOR").attr("code");
						dbrName = $("#ACTOR").val();
					}
					if($("#ACTOR")[0].tagName=="SELECT"){
						dbr = $("#ACTOR").val();
						dbrName = $("#ACTOR").find("option:selected").text(); 
					}					
					if(!dbr||dbr==""||dbr==undefined)
					{
						requireFormMsg("请选择办理人!");
						//$("#ACTOR").focus();
						return;
					}
					var mind = $("#mind").val();
					CQSP("",sjbh,ywlx,"",'',"",'<%=condition%>','<%=operationoid%>',dbr,dbrName,mind);
					//window.parent.getProcess();
				}
				
				
			}

		},
	    error : function(result) {
	     	//alert(234);
	    }
	});


}



function setBlr(data){
	var userId = "";
	var userName = "";
	for(var i=0;i<data.length;i++){
 	 var tempObj =data[i];
 	 if(i<data.length-1){
	  userId +=tempObj.ACCOUNT+",";
	  userName +=tempObj.USERNAME+",";
 	 }else{
 	  userId +=tempObj.ACCOUNT;
 	  userName +=tempObj.USERNAME; 
 	 }
	}
	$("#ACTOR").val(userName);
	$("#ACTOR").attr("code",userId);

}
function setBlbm(data){
	var deptid = "";
	var deptname = "";
	var deptFzr = "";
	var deptFzrName = "";

	for(var i=0;i<data.length;i++){
 	 var tempObj =data[i];
 	 if(i<data.length-1){
 		deptid +=tempObj.DEPTID+",";
 		deptname +=tempObj.DEPTNAME+",";
 		deptFzr +=tempObj.FZR+",";
 		deptFzrName +=tempObj.FZRNAME+"";
 	 }else{
 		deptid +=tempObj.DEPTID;
 		deptname +=tempObj.DEPTNAME; 
 		deptFzr +=tempObj.FZR;
 		deptFzrName +=tempObj.FZRNAME;

 	 }
	}
	document.getElementById("DEPTID").value=deptname;
	$("#DEPTID").attr("code",deptid);
	document.getElementById("ACTOR").value=deptFzrName;
	$("#ACTOR").attr("code",deptFzr);
}
function printDiv()
{
	window.frames["iFrame"].printIframe();
	
}

</script>
</head>
<body TOPMARGIN="0" LEFTMARGIN="0" style="overflow:visible" >
<app:dialogs/>
<div class="container-fluid">

    <div class="row-fluid" >
		<div class="B-small-from-table-autoConcise">

			<%if("14".equals(templateid)){%>
				<div  id="iframeright" style="width=100%;height:30%;overflow:hidden;" >

		    		<h4 class="title" style="width:100%">

						<span class="pull-right">

		             	<%if(isview==null || !("1".equals(isview))){ %>
		                	<button class="btn" id="yl"  onclick="saveWS()">&nbsp;&nbsp;提交&nbsp;&nbsp;</button>
		                	<button id="contact0" class="btn btn-link" type="button" onclick="showLct()"><i class="icon-th-list"></i>查看流程</button>
		              	<%} %>
		              		<button id="example_query" class="btn btn-link" type="button" onclick="printDiv()"><i class="icon-print"></i>打印</button>
						</span>
					</h4>
           	       
                       <form method="post" action="" id="insertForm">
                       <div class="windowOpenAuto" style="height:90%;overflow:auto;">
                       <table class="B-table" width="100%">
                            <% if(("4".equals(processtype))){ %>
                       		<tr style="display:<%if(!("4".equals(processtype))) out.println("none");%>">
								<th class="right-border bottom-border" style="font-size:14px;">办理人</th>
								<td class="bottom-border right-border"  colspan="7">
			         			<input class="span12"  type="text" check-type="required" fieldname="ACTOR" id="ACTOR" name ="ACTOR" style="width:75%" disabled />
			         			<button class="btn btn-link"  type="button" id="blrBtn"><abbr title="点击选择办理人"><i class="icon-edit"></i></abbr></button>
			       	 			</td>
							</tr>
							<%}else {
								out.println(html);
							  }
							 %>
					   <%if(isview==null || !("1".equals(isview))){ %>
							 
					   <tr>
					   <th width="12%"  align="center" valign="middle" style="text-align:center;font-size:14px;">办理意见</th>
				       <td width="88%" colspan="7" class="bottom-border right-border">
					      <div><textarea class="span12" rows="3" name="txtCheckMind" id="mind" maxLength="2000"></textarea></div>				    
				       </td>
					   </tr>		    
                       <tr>
                       <%}%>
						<th width="12%" class="right-border bottom-border text-right">附件信息</th>
						<td width="88%" colspan="7" class="bottom-border right-border">
							<div>
								<input type="hidden" name=ywid id="ywid" value="111">  
								<table role="presentation" class="table table-striped">
									<tbody  class="files showFileTab"
										data-toggle="modal-gallery" data-target="#modal-gallery" showLrr="true" deleteUser="<%=user.getAccount()%>" showSize="false" onlyView="true">
									</tbody>
								</table>
								<%if(isview==null || !("1".equals(isview))){ %>
									<span  class="btn btn-fileUpload" onclick="doSelectFile(this);"	fjlb="3006"> <i class="icon-plus"></i> <span>添加文件...</span>
									</span>
								<%}%>
							</div>
						</td>
					   </tr>
                       </table>
                        </div>
                       </form>
                  
                   
				   <jsp:include page="/jsp/file_upload/fileupload_config.jsp" flush="true"/>
              </div> 
              			
		
               <div id="iframediv" style="width: 100%;height: 70%;overflow:hidden;">
             	<iframe id="iframe" style="width: 100%;height: 600px;" name="iFrame" src="${pageContext.request.contextPath}/jsp/business/zjgl/ykWs.jsp?sjbh=<%=sjbh %>&ywlx=<%=ywlx %>&isEdit=1" FRAMEBORDER=0 SCROLLING=></iframe>
				</div>
			<%}else{ %>
				        	<div class="span7" style="height:80%;overflow:hidden;">
             	<iframe name="iFrame" width="100%" height="95%" src="<%=tt%>" FRAMEBORDER=0 SCROLLING= ></iframe>
			</div>
			
    		<div class="span5" style="height:80%;overflow:hidden;">

		    		<h4 class="title" style="width:100%">

						<span class="pull-right">

		             	<%if(isview==null || !("1".equals(isview))){ %>
		                	<button class="btn" id="yl"  onclick="saveWS()">&nbsp;&nbsp;提交&nbsp;&nbsp;</button>
		                   <button id="contact0" class="btn btn-link" type="button" onclick="showLct()"><i class="icon-th-list"></i>查看流程</button>
		              	<%} %>
		              		<button id="example_query" class="btn btn-link" type="button" onclick="printDiv()"><i class="icon-print"></i>打印</button>
						</span>
					</h4>
           	       
                       <form method="post" action="" id="insertForm">
                       <div class="windowOpenAuto" style="height:90%;overflow:auto;">
                       <table class="B-table" width="100%">
                            <% if(("4".equals(processtype))){ %>
                       		<tr style="display:<%if(!("4".equals(processtype))) out.println("none");%>">
								<th class="right-border bottom-border" style="font-size:14px;">办理人</th>
								<td class="bottom-border right-border"  colspan="7">
			         			<input class="span12"  type="text" check-type="required" fieldname="ACTOR" id="ACTOR" name ="ACTOR" style="width:75%" disabled />
			         			<button class="btn btn-link"  type="button" id="blrBtn"><abbr title="点击选择办理人"><i class="icon-edit"></i></abbr></button>
			       	 			</td>
							</tr>
							<%}else {
								out.println(html);
							  }
							 %>
					   <%if(isview==null || !("1".equals(isview))){ %>
							 
					   <tr>
					   <th width="12%"  align="center" valign="middle" style="text-align:center;font-size:14px;">办理意见</th>
				       <td width="88%" colspan="7" class="bottom-border right-border">
					      <div><textarea class="span12" rows="3" name="txtCheckMind" id="mind" maxLength="2000"></textarea></div>				    
				       </td>
					   </tr>		    
                       <tr>
                       <%}%>
						<th width="12%" class="right-border bottom-border text-right">附件信息</th>
						<td width="88%" colspan="7" class="bottom-border right-border">
							<div>
								
								<input type="hidden" name=ywid id="ywid" value="111">  
								<%if(isview==null || !("1".equals(isview))){ %>
									<span  class="btn btn-fileUpload" onclick="doSelectFile(this);"	fjlb="3006"> <i class="icon-plus"></i> <span>添加文件...</span>
									</span>
								<%}%>
								<table role="presentation" class="table table-striped">
									<tbody  class="files showFileTab"
										data-toggle="modal-gallery" data-target="#modal-gallery" showLrr="true" deleteUser="<%=user.getAccount()%>" showSize="false" onlyView="true">
									</tbody>
								</table>
							</div>
						</td>
					   </tr>
                       </table>
                        </div>
                       </form>
                  
                   
				   <jsp:include page="/jsp/file_upload/fileupload_config.jsp" flush="true"/>
              </div>
			<%} %>

        </div>
    </div>
</div>

</body>

</html>