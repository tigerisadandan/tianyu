<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>长春市政府投资建设项目管理中心——综合管控中心</title>
<link href="${pageContext.request.contextPath }/css/bootstrap.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath }/css/base.css" rel="stylesheet" media="screen">
<script src="${pageContext.request.contextPath }/js/base/jquery.js"></script>
<script src="${pageContext.request.contextPath }/js/base/bootstrap.js"></script>
<script src="${pageContext.request.contextPath }/js/common/convertJson.js"></script>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script src="${pageContext.request.contextPath }/js/common/TabList.js"></script>

</head>
<%
String tabId = request.getParameter("tabId");
String path  = request.getParameter("path");
%>
<script type="text/javascript" charset="utf-8">
 var tabId = "<%=tabId%>";
 //弹出窗口关闭弹出页面回调空函数
 function closeNowCloseFunction(){
 }
 function doInit(){
	 var parentobj =$(window).manhuaDialog.getParentObj(); 
	 var tabObj_parent = parentobj.document.getElementById(tabId);
     var tabObj = document.getElementById("DT1");
	  if(tabObj){
	    document.getElementById("tabHtml").value = tabObj_parent.outerHTML;//当前页的查询结果集
	    document.getElementById("querycondition").value = tabObj_parent.getAttribute("queryData");//查询条件
	    document.getElementById("actionName").value = tabObj_parent.getAttribute("queryPath");//执行查询路径
	  }
	 
 }
   $(function() {
		var btn = $("#onePageExcel");
		btn.click(function() {
			var parentobj =$(window).manhuaDialog.getParentObj(); 
			var tabObj_parent = parentobj.document.getElementById(tabId);
			var s = getTableRowsJsonString();
			document.getElementById("tabHtml").value = s;//parentobj.document.getElementById("queryResult").value;//当前页的查询结果集
			$("#loginForm").submit();
		});
	});
   $(function() {
		var btn1 = $("#morePageExcel");
		btn1.click(function() {
			var actionName = document.getElementById("actionName").value;
			var parentobj =$(window).manhuaDialog.getParentObj(); 
			var querycondition = setTotalPage(document.getElementById("querycondition").value,parentobj.document.getElementById("queryResult").value);
			
			var data = {
					msg : querycondition
				};
				$.ajax({
					url : actionName,
					data : data,
					cache : false,
					async :	false,
					dataType : "json",  
					type : 'post',
					success : function(result) {
						document.getElementById("queryResult").value = result.msg;//当前页的查询结果集
					}
				});
			
			$("#loginForm").submit();
		});
	});
	function  setTotalPage(querycondition,queryResult)
	{
    	var qr= convertJson.string2json1(queryResult);
    	var countrows = qr.pages["countrows"];
    	var qc= convertJson.string2json1(querycondition);
    	 qc.pages["recordsperpage"] = countrows;
        return JSON.stringify(qc);
		
	}
	function getTableRowsJsonString(){
		var parentobj =$(window).manhuaDialog.getParentObj(); 
		var tabObj_parent = parentobj.document.getElementById(tabId);
		var $this = $(tabObj_parent);
    	var allRowJson = $this.getTabRowsToJsonArray();
    	var temp ;

    	for (var i = 0; i < allRowJson.length; i++)
    	{
    	  for (var j = 0; j < allRowJson.length - i; j++)
    	  {
    		var now = allRowJson[j];
    		var next = allRowJson[j + 1];
    		if(!next) break;
    	    if (now[fieldname] > next[fieldname])
    	    {
    	      temp = allRowJson[j + 1];
    	      allRowJson[j + 1] = allRowJson[j];
    	      allRowJson[j] = temp;
    	     }
    	   }
    	}
    	var rowJsonString = JSON.stringify(allRowJson);
    	rowJsonString ="{response:{data:"+rowJsonString+"}}";
    	return rowJsonString;
	}
 
 
</script>
<body onload="doInit()">
   <form method="post" action="<%=path%>" id="loginForm" >
    
       <button id="onePageExcel" class="btn btn-primary"  type="button">导出当前页excel</button>
       <button id="morePageExcel" class="btn btn-primary"  type="button">导出所有页excel</button>
       
        <textarea type="text" id="queryResult" style="display:none;" name="queryResult"></textarea>
   
   </form>
</body>
</html>