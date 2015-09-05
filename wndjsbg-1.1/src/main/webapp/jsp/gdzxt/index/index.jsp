<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base/>
<title>首页</title>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/rygl/dtRyBiangengController";
var controllernameZgtzd= "${pageContext.request.contextPath }/yhzg/zgTzdController";

//页面初始化
$(function() {
	$.ajax({
		url : controllername+"?getBgCount",
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var num = response.msg;
			$('#num').text(num);
		}
	});
	
	$.ajax({
		url : controllernameZgtzd+"?getJtCount",
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var num = response.msg;
			$('#jtnum').text(num);
		}
	});
	
	$.ajax({
		url : controllernameZgtzd+"?getQtCount",
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var num = response.msg;
			$('#qtnum').text(num);
		}
	})


});

function shenhe(){
	window.open("${pageContext.request.contextPath }/jsp/gdzxt/index/rybiangeng-page.jsp","人员变更审批");//传递一个ID给详细页面，用于查找 	
}

function jtshenhe(){
	window.open("${pageContext.request.contextPath }/jsp/gdzxt/index/yhzg-jbtg-page.jsp","局部停工审核");//传递一个ID给详细页面，用于查找 	
}

function qtshenhe(){
	window.open("${pageContext.request.contextPath }/jsp/gdzxt/index/yhzg-qmtg-page.jsp","全面停工审核");//传递一个ID给详细页面，用于查找 	
}

</script>
</head>
<body>
    <div class="container">
      <div class="row row-offcanvas row-offcanvas-right">
        <div class="col-xs-12 col-sm-9">
          <div class="row">
            <div class="col-6 col-sm-6 col-lg-4">
              <h4>项目整改状态：</h4>
              <img alt="" src="/wndjsbg/img/ztt.png">
              <p>
              	今日更新  2  张（实体  0  张，考勤  2  张），昨日更新  13  张（实体  3  张，考勤  10  张） <br/>
				今日开具整改单  2  张（实体  0  张，考勤  2  张），局部停工  0  张，全面停工  0  张（实体  0  张，考勤  0  张）； <br/>
				今日扣分  3  条，本周扣分  24  条，本月扣分  129  条 <br/>
				待审核扣分  9  条 待审核加分  5  条 <br/>
				待审核全面停工   <a href="javascript:void(0);" onclick="qtshenhe();" id="qtnum"></a>  张 待审核局部停工   <a href="javascript:void(0);" onclick="jtshenhe();" id="jtnum"></a> 张  待审核复工  0  张 <br/>
				已全面停工未复工项目数  3  个 已局部停工未复工项目数  1  个 <br/>
				未闭口  76  张(实体  33  张，考勤  43  张)，即将超时未答复  8  张(实体  5  张，考勤  3  张)，超时未答复  3  张(实体  1  张，考勤  2  张) <br/>
				企业、个人、项目信用分值查询 <br/>
              
			</p>
              
            </div><!--/span-->
            <div class="col-6 col-sm-6 col-lg-4">
              <h4>项目考勤状态：</h4>
             	<h5>1、考勤信息 </h5>
				<p>昨日需考勤项目共有  112  个，其中满勤项目  15  个，重要人员满勤项目  64  个，主要人员满勤项目  24  个；  <br/>
				昨日缺勤项目  97  个，其中重要人员缺勤项目  48  个，主要人员缺勤项目  88  个，重要人员缺勤2天项目  23  个；  <br/>
				昨日无有效考勤记录项目  2  个：<br/>  
				施工单位关键岗位人员无有效考勤记录项目  2  个，重要人员无有效考勤记录项目  2  个，主要人员无有效考勤记录项目  23  个；  <br/>
				监理单位关键岗位人员无有效考勤记录项目  2  个，重要人员无有效考勤项目  2  个，主要人员无有效考勤记录项目  25  个；<br/>  
				目前需考勤人员  1816  人次，昨日已考勤  1363  人次，有效考勤  1277  人次；  <br/>  
				目前分阶段考勤  95  人次（正在考勤  27  人次，即将考勤  68  人次），暂停考勤  4  人次；  <br/>
				</p>
				<h5>2、考勤待审信息 </h5>
				<p>目前考勤违规应扣分项目  6  个；  <br/>
				待审暂停考勤  3  个，待审终止考勤  36  个，待审分阶段考勤  17  个；  <br/>
				待审项目考勤状态  0  个，待审人员变更  <a href="javascript:void(0);" onclick="shenhe();" id="num"></a> 个，待审人员解锁  31  个  <br/>
				</p>
            </div><!--/span-->

          </div><!--/row-->
        </div><!--/span-->
      </div><!--/row-->
    </div><!--/.container-->
</body>
</html>