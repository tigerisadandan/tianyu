
<%@page import="java.util.Date"%><!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<title>编 制 说 明</title>
<%
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
//请求路径，对应后台RequestMapping


//页面初始化
$(function() {
	$("#pubAlert").hide();

	
});



</script>
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
	<p></p>
	<div class="row-fluid">
		<div class="B-small-from-table-autoConcise">
		<form method="post" id="queryLiYou">
	            <table class="B-table" width="100%" id="DT1">
	            <tr>
	            	<td align="center">
	            		<h3>编 制 说 明</h3>
	            	</td>
	            </tr>
	            <tr>
	            	<td>
	            	一、该表作为建设工程项目竣工综合验收备案文件请认真填写，并作为备案文件存档；<br/><br/>
					二、项目交付时间是指预销售合同交付时间；交付性质是指一次性交付或分段交付（注明第几次）。<br/><br/>
					三、“公建配套面积（平方米）”一栏中，应按照物业用房、公共设施、居委管理用房等分类统计汇总，具体
						子项目的位置面积详填下面表格，与公建配套立项批复内容相符、与规划验收数据一致。<br/><br/>
					四、“申请验收面积”一栏中，是指按照规划验收建筑物主体面积汇总填写，分别按照高层、多层、地下室分
						别汇总。具体单体竣工情况按照后面附表分栋完成。<br/><br/>
					五、“竣工项目综合验收备案单体建筑汇总表”是指本次验收范围单体建筑物的情况（不含公建配套建筑物）。
						如果为一次性验收即房号顺序填写全部建筑物内容；如果为分批分段验收的，除按照表中内容如实填写外，
						还需要在备注中明确阐述项目总面积、总户数；合同交付时间，分几次交付；本次交付的情况（含公建配套情况）<br/><br/>
					六、“商品房交付使用的申请报告”应按照本次验收交付的项目名称、地点、总建筑面积（地上、地下）、栋数、
						建筑物层数（高层、小高层、多层）；总户数；公建配套面积；项目的设计、施工、监理单位；项目开竣工时间
						等内容如实填写。<br/><br/>
					 七、开发商与物业共同对室外空调机位确保不改变的承诺是指业主入住后空调外机不按照规定位置安装而造成外立面
						遭到破坏的现象。为此，开发商与物业公司必须制定相应措施，确保落实外立面整洁，不受破坏。办理竣工中和验收
						备案时，开发商必须提供交付<b>建筑物分体空调安装明细、连接管尺寸表、用户告知书</b>。分户空调安装位置图，标注需
						要连接管尺寸，均应以<b>告知书</b>的形式在业主进户前将空调外机距离室内机的具体尺寸书面告知业主。
					</td>
				</tr>
	               	
	           </table>
	        </form>
	 	</div>
	</div>     
</div>
<div align="center">
	<FORM name="frmPost" method="post" style="display: none" target="_blank">
		<!--系统保留定义区域-->
		<input type="hidden" name="queryXML"/> 
		<input type="hidden" name="txtXML"/>
		<input type="hidden" name="txtFilter" order="desc" fieldname="SPBZ_UID" id="txtFilter"/>
		<input type="hidden" name="txtFilter" order="asc" fieldname="SERIAL_NO" id="txtFilter"/>
<%--		<input type="hidden" name="txtFilter" order="desc" fieldname="ID" id="txtFilter"/>--%>
		<input type="hidden" name="resultXML" id="resultXML"/>
		<!--传递行数据用的隐藏域-->
		<input type="hidden" name="rowData"/>
		<input type="hidden" name="queryResult" id="queryResult"/>
	</FORM>
</div>
</body>
</html>