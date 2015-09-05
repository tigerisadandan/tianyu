<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="com.ccthanking.framework.util.*"%>
	
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

String bbid = request.getParameter("bbid");

%>
<script type="text/javascript" charset="utf-8">
 
</script>
<body >
   <div style='display:none' id="DT1"></div>
   <form method="post" action="${pageContext.request.contextPath }/servlet/TableExp" id="loginForm" >
    
       <button id="onePageExcel" class="btn btn-primary"  type="button"><a href="${pageContext.request.contextPath }/sgbb/sgBbController.do?query4Print&bbid=<%=bbid %>&type=pdf">导出PDF</a></button>
       <button id="morePageExcel" class="btn btn-primary"  type="button"><a href="${pageContext.request.contextPath }/sgbb/sgBbController.do?query4Print&bbid=<%=bbid %>&type=word">导出WORD</a></button>
 
   </form>
</body>
</html>