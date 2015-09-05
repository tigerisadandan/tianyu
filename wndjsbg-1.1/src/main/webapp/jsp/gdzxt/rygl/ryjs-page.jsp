<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp" %>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>


<!-- PAGE CONTENT BEGINS -->
		<div class="row" style="100%">
					<div class="col-xs-12">
					<form class="form-inline" role="form" id="queryForm"
							style="border:solid 1px #ddd;height:40px;line-height:35px;vertical-align:middle;">
							<div class="col-xs-12">
							<div class="col-xs-12 align-right" style="padding: 0px">
									<a data-target="#lwht-input" id="new" data-toggle="modal">
										<button id="btn_new" class="btn btn-primary btn-sm" type="button"> 	新增申请解锁</button>
									</a>
								</div>
							</div>

						</form>
						<form method="post" id="pageFrm">
							<input class="hidden" id="rownum" type="text" fieldname="rownum"
								value="10000" operation="  &lt;= " />
						</form>
						
												
					</div>
				</div>

<div id="ryjs-input" class="modal"></div>
<script type="text/javascript" charset="utf-8">

var scripts = [null];

	ace.load_ajax_scripts(scripts, function() {
		/*var gridwidth=$("#contentdivid").width();
		JqgridManage.initJqgrid(content_grid_table,queryForm,gridwidth);
JqgridManage.initJqgrid(content_grid_table2,queryForm2,gridwidth);
		//setStyle(xmxxtxformid);
		//DatePicker.datepickerid("#PF_DATE");*/
	});
//页面初始化
$(function() {
	doProQuery();
	$('#new').click(function() {
							type = null;// 清下id 要不然修改会 id回村起来 下次业务就成update了
							$(".info").hide();
							// $("#executeFrm").clearFormResult();

							$('#new').attr("data-target","ryjs-input");
							$('#ryjs-input').removeData("bs.modal");
							$("#ryjs-input").empty();
							$('#ryjs-input').modal({
								backdrop: 'static'
							});
							$.get(contextPath+ "/jsp/gdzxt/rygl/ryjs-page-jsry.jsp",function(data) {$("#ryjs-input").html(data);});
						});
});


function doProQuery(){
	jQuery("#grid-table").jqGrid({		
		datatype : function(postdata) {
			/**	$("#PROJECTS_STATUS").val("40");//过滤掉未提交的项目分期数据
		       var data1 = combineQuery.getQueryCombineData(queryForm, null, "grid-table");
		       var data = {
		   			msg : data1
		   		};**/
		   	   var type=$("#PROJECTS_TYPE").val();
		       jQuery.ajax({   
		           type : 'POST',   
		        //   url : controllerProject+'query?optype=forIndex',
		            url : '',
		           // data :data,
		            dataType : 'json',
		            cache:false,
		            success : function(resp) {
		    	   		jQuery("#grid-table").jqGrid("clearGridData");
		            	var b = convertJson.string2json1(resp.msg);
		            	var thegrid = jQuery("#grid-table")[0];   
		            	thegrid.addJSONData(b);
		            	updatePagerIcons();
		            }
		            //,error : ajaxFailed   
		        });   
		    },
		height: "auto",
		rownumbers: false,
		rownumWidth:50,
		colNames: ["监理员","岗位","证书编号","联系电话"],
		colModel: [	
			  {name: "PROJECTS_UID",index:"PROJECTS_UID",width:90},		      
		      {name: "PROJECTS_CODE",index:"PROJECTS_CODE",width:100},		      
		      {name: "PROJECTNAME", width:150},
		      {name: "STATUS_SV",index:"STATUS",width:100,align:"center"},    
		     
		], 
		tree_root_level:1,
		treeGrid: true,
		treeGridModel: "adjacency",
		ExpandColumn: "PROJECTNAME",
		treeIcons: {
		    plus:'fa fa-plus',
		    minus:'fa fa-minus',
		    leaf:'glyphicon glyphicon-list',
		},
		ExpandColClick: false,
		viewrecords : true,
		rowNum:5,
		rowList:[5,10,30],
		pager : "#grid-pager",
		multiselect: false,
        multiboxonly: false,
		altRows: true,
		autowidth: true,
		//caption: "项目列表",
		jsonReader : {
		  root:function (obj){return obj;},
	/**      root:function (obj){return obj.response.data;},
	      total:function (obj){return obj.pages.totalpage;},
	      page:function (obj){return obj.pages.currentpagenum;},
	      records:function (obj){return obj.pages.countrows;}**/
		},
		loadComplete : function() {
		},
		gridComplete : function(){
		
		}
	});
}





	function sbjc(){
		window.open("${pageContext.request.contextPath }/jsp/business/commons/sbjc.jsp","设备检测");
		
	}

</script>
</script>