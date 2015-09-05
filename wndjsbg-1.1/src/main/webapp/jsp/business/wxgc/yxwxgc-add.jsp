<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>微型工程-维护</title>
<%
	String type=request.getParameter("type");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<script type="text/javascript" charset="utf-8">
//请求路径，对应后台RequestMapping
var controllername= "${pageContext.request.contextPath }/wxgc/yxWxgcController";
var type ="<%=type%>";
//页面初始化
$(function() {
	init();
	//按钮绑定事件(保存)
	$("#btnSave").click(function() {
		if($("#yxWxgcForm").validationButton()){
			xConfirm("信息确认","确认提交备案结果？");
			$('#ConfirmYesButton').attr('click','');
			$('#ConfirmYesButton').one("click",function(){
				
				$('#yxWxgcForm').find("*").each(function(){
					if($(this).attr('disabled')=='disabled'){
					
						$(this).removeAttr('fieldname');
					}
				});
			    //生成json串
			    var data = Form2Json.formToJSON(yxWxgcForm);
			  //组成保存json串格式
			    var data1 = defaultJson.packSaveJson(data);
			  //调用ajax插入
			    if($("#WXGC_UID").val() != "" && $("#WXGC_UID").val() != null){
			    	defaultJson.doUpdateJson(controllername + "?update", data1);
	    		}
	    		
			    var a=$(window).manhuaDialog.getParentObj();
			    a.init();
				$(window).manhuaDialog.close();
			});	
		}else{
			requireFormMsg();
		  	return;
		}
	});
	

	$("#btnClose").bind("click", function(){
		var a=$(this).manhuaDialog.close();
	});
	
    <%
    if(type.equals("detail")){
	%>
	//置所有input 为disabled
	$(":input").each(function(i){
	   $(this).attr("disabled", "true");
	 });
	 $("#shjltxdivid").hide();
	 $("#btnClose").removeAttr('disabled');
	<%
		}
	%>
	
});
//页面默认参数
function init(){
	if(type == "insert"){
		
	}else if(type == "update" || type == "detail"){
		//生成json串
		var parentmain = $(window).manhuaDialog.getParentObj();
		var rowValue = parentmain.$("#resultXML").val();
		var tempJson = convertJson.string2json1(rowValue);
		$("#QWXGC_UID").val(tempJson.WXGC_UID);
		//查询表单赋值
		var data = combineQuery.getQueryCombineData(queryForm, frmPost);
		var data1 = {
			msg : data
		};
		$.ajax({
			url : controllername + "?query",
			data : data1,
			cache : false,
			async : false,
			dataType : "json",
			type : 'post',
			success : function(response) {				
				var res = dealSpecialCharactor(response.msg);
				$("#resultXML").val(response.msg);
				var resultobj = defaultJson.dealResultJson(res);
				zblxfun(resultobj.ZBLX);
				wxgcpbfsfun(resultobj.PBFS_UID);
				gctypecodefun(resultobj.GC_TYPE_CODE);
				$("#yxWxgcForm").setFormValues(resultobj);
				$("#ZT").val(resultobj.ZT);	
				$("#ZBJ").val(resultobj.ZBJ);	
				$("#ZBJ").removeAttr("code");
				if(resultobj.zt=='10'){
					document.getElementById("gcbhdivid").style.display="block";
				}
				
				return true;
			}
		});
		//加载附件
		queryFileData(tempJson.WXGC_UID, "", "", "777002");

		//列出审批意见信息
		var queryconditionXX = "{\"logic\":\"and\",\"fieldname\":\"WXGC_UID\",\"operation\":\"=\",\"value\":\""+tempJson.WXGC_UID+"\",\"fieldtype\":'',\"fieldformat\":''}";
	
		var dataXX = "{querycondition: {conditions: [" +queryconditionXX+"]},pages:{recordsperpage:200, currentpagenum:1, totalpage:0, countrows:0},orders:[{\"fieldname\":\"SPRQ\",\"order\":\"asc\"}]}";
		//调用ajax插入
		defaultJson.doQueryJsonList(controllername+"?queryspyj", dataXX, spyjList);
	}
}

function zblxfun(v){
	if(v=='2'){
		$("#zbdllabelid").hide();
		$("#zbdldivid").hide();
	}else if(v=='1'){
		$("#zbdllabelid").show();
		$("#zbdldivid").show();
	}
}

function checkWxgcshjg(v){
	if(v==1){
		$("#WXGCSHYJ").val("同意备案！");
	}else{
		$("#WXGCSHYJ").val("不同意备案！");
	}
}




function wxgcpbfsfun(v){
	if(v=='1'){
		$("#mbjlabelid").html("明标价");
		$("#kzxstrid").hide();
		$("#gcpbjzjtrid").hide();
	}else if(v=='2'){
		$("#kzxstrid").show();
		$("#gcpbjzjtrid").show();
		$("#mbjlabelid").html("招标控制价");
	}else if(v=='3'){
		$("#kzxstrid").hide();
		$("#gcpbjzjtrid").hide();
		$("#mbjlabelid").html("招标控制价");
	}
	initpbfssmfun(v);
}


//评标方式说明
function initpbfssmfun(uid){
	 $('#popoveraid').popover('destroy');
	if(uid!=null&&uid!=''){
		$("#PBFS_UID_SRARCH").val(uid);
		var data1 = combineQuery.getQueryCombineData(pbfsformbyid, frmPost);
	    var data = {msg : data1};
		$.ajax({
			url :'${pageContext.request.contextPath }/wxgc/yxWxgcPbfsController?query',
			data : data,
			cache : false,
			async :	false,
			dataType : "json",  
			type : 'post',
			success : function(response) {
				if(response.msg!=null&&response.msg!=0){
					var resultobj = defaultJson.dealResultJson(response.msg);	
					var KBZL=resultobj.KBZL;//开标所需资料
					KBZL=dealSpecialCharactor(KBZL);
					$("#popoveraid").attr("data-original-title",resultobj.PBFS_NAME);
					$("#popoveraid").attr("data-content",KBZL);
					$("#popoveraid").popover({html:true});
				}
			}
		});
	}else{
		$("#popoveraid").attr("data-original-title","评标方式");
		$("#popoveraid").attr("data-content","说明：请先选择评标方式！");
		$("#popoveraid").popover({html:true});
	}
}

function gctypecodefun(v){
	$("#WXGCTYPE").val(v);
	if(v=='7'){
		$('.cl').show();
		$('.cl input').each(function(i){
			$(this).attr('disabled',false);
		});
		$('.qt').hide();
		$('.qt select').each(function(i){
			$(this).attr('disabled',true);
		});
		$('.kc').hide();
		$('.kc select').each(function(i){
			$(this).attr('disabled',true);
		});
	}else{
		if(v=='6'){
			$('.kc').show();
			//$('.kc').children().attr('disabled',false);
			$('.kc select').each(function(i){
				$(this).attr('disabled',false);
			});
			$('.qt').hide();
			//$('.qt').children().attr('disabled',true);
			$('.qt select').each(function(i){
				$(this).attr('disabled',true);
			});
			$('.cl').hide();
			//$('.cl').children().attr('disabled',true);
			$('.cl input').each(function(i){
				$(this).attr('disabled',true);
			});
			var obj=$("#kczz"); 
			obj.attr("src","T#YX_GC_TYPE_ZZ:gc_type_uid:kczz:ENABLED='1' and gc_type_code ='"+v+"'and kczz is not null ORDER BY gc_type_code ASC ");
			obj.attr("kind","dic");
			obj.html('');
			reloadSelectTableDic(obj);
			var obj1=$("#sjzz");
			obj1.attr("src","T#YX_GC_TYPE_ZZ:gc_type_uid:sjzz:ENABLED='1' and gc_type_code ='"+v+"'and sjzz is not null ORDER BY gc_type_code ASC ");
			obj1.attr("kind","dic");
			obj1.html('');
			reloadSelectTableDic(obj1);

			var obj2=$('#kcfzr');
			obj2.attr("src","T#YX_GC_TYPE_ZZ:gc_type_uid:xmfzrzz:ENABLED='1' and gc_type_code ='"+v+"' and xmfzrzz is not null ");
			obj2.attr("kind","dic");
			obj2.html('');
			reloadSelectTableDic(obj2);
		}else{
			$('.qt').show();
			$('.qt select').each(function(i){
				$(this).attr('disabled',false);
			});
			$('.kc').hide();
			//$('.kc').children().attr('disabled',true);
			$('.kc select').each(function(i){
				$(this).attr('disabled',true);
			});
			$('.cl').hide();
			//$('.cl').children().attr('disabled',true);
			$('.cl input').each(function(i){
				$(this).attr('disabled',true);
			});
			var obj=$("#xl");
			obj.attr("src","T#YX_GC_TYPE_ZZ:gc_type_uid:qyzz:ENABLED='1' and gc_type_code ='"+v+"' ORDER BY gc_type_code ASC ");
			obj.attr("kind","dic");
			obj.html('');
			reloadSelectTableDic(obj);

			var obj1=$('#fzrzz');
			obj1.attr("src","T#YX_GC_TYPE_ZZ:gc_type_uid:xmfzrzz:ENABLED='1' and gc_type_code ='"+v+"' and xmfzrzz is not null ");
			obj1.attr("kind","dic");
			obj1.html('');
			reloadSelectTableDic(obj1);
		}
	}


}
</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">
	<div class="B-small-from-table-autoConcise">
	 <h4 class="title">
      	<span class="pull-right"> 
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>   		
      	</span>
      </h4>
      
	 <table class="B-table" width="100%" >
	          <tr>		
				<td  colspan="4">
				 <div class="modal-header" style="background:#0866c6;">
					<h3 style="color:white;">审核记录</h3>
				 </div>
					<div class="overFlowX">
				 		<table width="100%" class="table-hover table-activeTd B-table" id="spyjList" type="single" pageNum="2000"  noPage="true">
					    <thead>
					 		<tr style="display:blank">
					           <th  name="XH" id="_XH" style="width:10px" colindex=1 tdalign="center">&nbsp;#&nbsp;</th>
					            <th fieldname="USERNAME" style="width:200px;" colindex=1 tdalign="center" >审批人</th>
					            <th fieldname="SHBZ" style="width:200px;" colindex=1 tdalign="center" >审核步骤</th>
					            <th fieldname="SPRQ_SV" style="width:250px;" colindex=2 tdalign="center" maxlength="100" >&nbsp;审批时间&nbsp;</th>
								<th fieldname="SPJG_SV" colindex=2 tdalign="center" maxlength="200" >&nbsp;审批结果&nbsp;</th>
								<th fieldname="SPYJ" colindex=2 tdalign="center" maxlength="200" >&nbsp;审批意见&nbsp;</th>
					        </tr>
					    </thead>
					    <tbody></tbody>
					  </table>  
		       		</div>
				</td>
			</tr>		
		</table>
		
     <form method="post" id="yxWxgcForm"  >
    	
      <table class="B-table" width="100%" >
	  	<input type="hidden" id="WXGC_UID" fieldname="WXGC_UID" name = "WXGC_UID"/>
	  	<input  type="hidden" id="ZT" name="ZT" fieldname="ZT" >
	   <tr>		
		<td  colspan="4">
	  	<div style="height: 5px;"></div>
      	<div id="shjltxdivid">
			<div class="modal-header" style="background:#0866c6;">
			<h3 style="color:white;">微型工程信息备案
				<span class="pull-right">
					<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">备案</button>
	  			</span>
			</h3>
			</div>
   			<table id="shenheTable" class="B-table" width="100%" >
		        <tr>
					<th width="15%" class="right-border bottom-border text-right">是否备案</th>
		       	 	<td class="bottom-border right-border">
		       	 		<input onclick="checkWxgcshjg(this.value)" id="WXGCSHJG" type="radio" check-type="required" fieldname="WXGCSHJG" name = "WXGCSHJG" defaultValue="1" kind="dic" src="SHYJ_SY" />	
		       	 	</td>
		        </tr>
		       	<tr>
					<th width="15%" class="right-border bottom-border text-right">备案意见</th>
		       	 	<td class="bottom-border right-border">
		       	 		<textarea class="span12" rows="2" id="WXGCSHYJ" check-type="required" maxlength="4000"  fieldname="WXGCSHYJ" name="WXGCSHYJ">同意备案</textarea>
		       	 	</td>
		        </tr>
			</table>
		</div>
		</td>
		</tr>
	  	<tr>
		  	<td  colspan="4">
			<div class="modal-header" style="background:#0866c6;">
				<h3 style="color:white;">工程信息</h3>
			</div>
			</td>
		</tr>
         <tr>
			<th width="8%" class="right-border bottom-border text-right">评标方式</th>
       	 	<td class="bottom-border right-border"  colspan="3">
         		<select onchange="wxgcpbfsfun(this.value);"  id="PBFS_UID" fieldname="PBFS_UID" name="PBFS_UID" kind="dic" src="T#YX_WXGC_PBFS:PBFS_UID:PBFS_NAME:ENABLED='1' ORDER BY SERIAL_NO ASC " ></select>
       	 		&nbsp;&nbsp;
       	 		<span  class="btn btn-primary"  title="评标方式"  id="popoveraid" data-container="body" data-rel="popover" data-trigger="hover" data-placement="right" data-content="">
       	 		&nbsp;*说明
       	 		</span>
       	 	</td>
        </tr>
        <tr>
         	<th width="8%" class="right-border bottom-border text-right">工程类型</th>
       	 	<td class="bottom-border right-border"   width="15%">
          		<select onchange="gctypecodefun(this.value);"  id="GC_TYPE_CODE" fieldname="GC_TYPE_CODE" name="GC_TYPE_CODE" kind="dic" src="T#YX_GC_TYPE_ZZ:distinct(gc_type_code):GC_TYPE_NAME:ENABLED='1' ORDER BY gc_type_code ASC" ></select>
       	 	</td>
       	 	<div id="gcbhdivid" style="display:none">
	       	 	<th width="8%" class="right-border bottom-border text-right">工程编号</th>
	       	 	<td class="bottom-border right-border"   width="15%">
	       	 		<input id="GC_CODE" type="text"  fieldname="GC_CODE" name = "GC_CODE"  />
	       	 	</td>
       	 	</div>
        </tr>
         <tr>
			<th width="8%" class="right-border bottom-border text-right">工程名称</th>
       	 	<td class="bottom-border right-border"  colspan="3">
         		<input id="GC_NAME" type="text" check-type="required" fieldname="GC_NAME" name = "GC_NAME" class="span12" style="width:50%" />
       	 	</td>
        </tr>
        
         <tr>
			<th width="8%" class="right-border bottom-border text-right">批准机构</th>
       	 	<td class="bottom-border right-border"  width="15%">
         		<input id="XMSPJG" type="text" fieldname="XMSPJG" name = "XMSPJG"  />
       	 	</td>
         	<th width="8%" class="right-border bottom-border text-right">批准文件</th>
       		<td class="bottom-border right-border"width="15%">
         		<input id="XMSPBH" type="text"  fieldname="XMSPBH" name = "XMSPBH"  />
         	</td>
        </tr>
         <tr>
        	<th width="8%" class="right-border bottom-border text-right">批准材料</th>
	         <td class="bottom-border right-border"  colspan="3">
	         	 <table role="presentation"  class="table table-striped">
					<tbody fjlb="7761" id="gccltbody" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery">
					</tbody>
				 </table>
		     </td>
	         
	      </tr>
         <tr>
			<th width="8%" class="right-border bottom-border text-right">招标类型</th>
       	 	<td class="bottom-border right-border"  width="15%">
         		<input  id="ZBLX" type="radio" kind="dic" src="WXGCZBLX"  name="ZBLX" fieldname="ZBLX" defaultValue="1">
       	 	</td>
         	<th id="zbdllabelid" width="8%" class="right-border bottom-border text-right">招标代理机构</th>
       		<td id="zbdldivid" class="bottom-border right-border" width="15%">
         		<select id="ZTB_ZBDL_UID" fieldname="ZTB_ZBDL_UID" name="ZTB_ZBDL_UID" kind="dic" src="T#ZTB_ZBDL:ZTB_ZBDL_UID:ZBDL_NAME:ENABLED='1' order by ZBDL_NAME asc "  ></select>
         	</td>
        </tr>
         <tr>
			<th width="8%" class="right-border bottom-border text-right">建设单位</th>
       	 	<td class="bottom-border right-border"  colspan="3">
         		<input id="JSDW" type="text" fieldname="JSDW" name = "JSDW"  class="span12" style="width:50%"/>
       	 	</td>
        </tr>
        
       <tr>
			<th width="8%" class="right-border bottom-border text-right">计划开工日期</th>
       		<td class="bottom-border right-border" width="15%">
         		<input id="JHKGRQ" type="text"  fieldname="JHKGRQ" name = "JHKGRQ" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class='Wdate' />
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">计划竣工日期</th>
       		<td class="bottom-border right-border" width="15%">
         		<input id="JHJGRQ" type="text"  fieldname="JHJGRQ" name = "JHJGRQ" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class='Wdate'  />
         	</td>
        </tr>
       
        <tr>
         	<th width="8%" class="right-border bottom-border text-right">计划工期</th>
       		<td class="bottom-border right-border" width="15%">
         		<input id="JHGQ" type="text"  fieldname="JHGQ" name = "JHGQ" />天
         	</td>
         
        </tr>
          <tr>
         	<th width="8%" class="right-border bottom-border text-right">资金来源</th>
       		<td class="bottom-border right-border" width="15%">
         		<select  id="ZJLY"   name="ZJLY" fieldname="ZJLY" kind="dic" src="WXGC_ZJLY" defaultMemo="--请选择--"></select>
         	</td>
         	<th width="8%" class="right-border bottom-border text-right" id="mbjlabelid">明标价</th>
       		<td class="bottom-border right-border"width="15%">
         		<input id="ZBJ" type="text"  fieldname="ZBJ" name = "ZBJ"  />元
         	</td>
         	
        </tr>
         <tr>
			<th width="8%" class="right-border bottom-border text-right">工程地址</th>
       	 	<td class="bottom-border right-border" colspan="3">
          		<input id="JSDZ" type="text" fieldname="JSDZ" name = "JSDZ" class="span12" style="width:50%" />
       	 	</td>
         	
        </tr>
        
 		<tr id="kzxstrid" >
			<th width="8%" class="right-border bottom-border text-right">K值系数</th>
       	 	<td class="bottom-border right-border" colspan="3">
          		<input type="text" id="HLDJ_K1" fieldname="HLDJ_K1" name="HLDJ_K1" class="span12" style="width:10%" />%&nbsp;
          		<input type="text" id="HLDJ_K2" fieldname="HLDJ_K2" name="HLDJ_K2" class="span12" style="width:10%" />%&nbsp;
          		<input type="text" id="HLDJ_K3" fieldname="HLDJ_K3" name="HLDJ_K3" class="span12" style="width:10%" />%&nbsp;
          		(说明：K值取值范围在95%-98%之间。)
       	 	</td>
        	
       	</tr>
		 <tr  id="gcpbjzjtrid">
         	<th width="8%" class="right-border bottom-border text-right">高出评标基准价1%扣分</th>
       		<td class="bottom-border right-border" width="15%">
         		<input id="HLDJ_PGKFZ" fieldname="HLDJ_PGKFZ" name="HLDJ_PGKFZ" type="text" >分
         	</td>
         	<th width="8%" class="right-border bottom-border text-right">低于评标基准价1%扣分</th>
       		<td class="bottom-border right-border"width="15%">
         		<input id="HLDJ_PDKFZ" fieldname="HLDJ_PDKFZ" name="HLDJ_PDKFZ"    type="text" >分
         	</td>
        </tr>
        
        <tr>
			<th width="8%" class="right-border bottom-border text-right">招标内容</th>
       	 	<td class="bottom-border right-border"  colspan="3">
         		<textarea class="span12" style="width:50%" rows="2" id="XMZBFW"  fieldname="XMZBFW" name="XMZBFW"></textarea>
       	 	</td>
         	
        </tr>
  
	    <tr class="qt">
				<th width="8%" class="right-border bottom-border text-right">投标单位资质</th>
	       	 	<td class="bottom-border right-border" colspan="3">
	          		<select id="xl" fieldname="ZBDWZGYQ" class="span12" style="width:50%"  >
	                       <option>--请选择--</option>
	                </select>
	       	 	</td>
	     </tr>
	     <tr class="qt">	
				<th width="8%" class="right-border bottom-border text-right">项目负责人资质</th>
	       	 	<td class="bottom-border right-border" colspan="3">
	          		<select id="fzrzz" fieldname="XMFZRZZ" class="span12" style="width:50%"  >
	                       <option>--请选择--</option>
	                </select>
	       	 	</td>
	     </tr>
        
       
	        <tr class="cl">
				<th width="8%" class="right-border bottom-border text-right">投标单位资质</th>
	       	 	<td class="bottom-border right-border" colspan="3">
	          		<input id="ZBDWZGYQ" type="text" fieldname="ZBDWZGYQ" name = "ZBDWZGYQ"   class="span12" style="width:50%"  />
	       	 	</td>
	        </tr>        
	        <tr class="cl">
				<th width="8%" class="right-border bottom-border text-right">项目负责人资质</th>
	       	 	<td class="bottom-border right-border" colspan="3">
	          		<input id="XMFZRZZ" type="text" fieldname="XMFZRZZ" name = "XMFZRZZ"   class="span12" style="width:50%"  />
	       	 	</td>
	        </tr>
     
        
        	<tr class="kc">
				<th width="8%" class="right-border bottom-border text-right">勘察资质</th>
	       	 	<td class="bottom-border right-border" colspan="3">
	          		<select id="kczz"  fieldname="KCZZ" class="span12" style="width:50%"  >
	                       <option>--请选择--</option>
	                </select>
	       	 	</td>
	       	 </tr>
        	<tr class="kc">
				<th width="8%" class="right-border bottom-border text-right">设计资质</th>
	       	 	<td class="bottom-border right-border" colspan="3">
	          		<select id="sjzz" fieldname="SJZZ" class="span12" style="width:50%"  >
	                       <option>--请选择--</option>
	                </select>
	       	 	</td>
	       	 </tr>
	       	 <tr class="kc">	
				<th width="8%" class="right-border bottom-border text-right">项目负责人资质</th>
	       	 	<td class="bottom-border right-border" colspan="3">
	          		<select id="kcfzr"  fieldname="XMFZRZZ" class="span12" style="width:50%"  >
	                       <option>--请选择--</option>
	                </select>
	       	 	</td>
	        </tr>
       
           <tr>
			<th width="8%" class="right-border bottom-border text-right">公告(报名)时间</th>
       	 	<td class="bottom-border right-border"  width="15%">
         		<input id="GGKSRQ" type="text"  fieldname="GGKSRQ" name = "GGKSRQ" fieldformat='yyyy-MM-dd HH:mm' class='Wdate' onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  />
       	 	</td>
         	<th width="8%" class="center">~</th>
       		<td class="bottom-border right-border"width="15%">
         		<input id="GGJZRQ" type="text"  fieldname="GGJZRQ" name = "GGJZRQ"  fieldformat='yyyy-MM-dd HH:mm' class='Wdate' onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  />
         	</td>
        </tr>
        
        
        
         <tr>
			<th width="8%" class="right-border bottom-border text-right">开标时间</th>
       	 	<td class="bottom-border right-border"  colspan="3">
         		<input id="KBRQ" type="text"  fieldname="KBRQ" name = "KBRQ" fieldformat='yyyy-MM-dd HH:mm' onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class='Wdate'  />
       	 	</td>
         	
        </tr>
          <tr>
			<th width="8%" class="right-border bottom-border text-right">开标地址</th>
       	 	<td class="bottom-border right-border"  colspan="3">
         		<input id="KBDZ" type="text"  fieldname="KBDZ" name = "KBDZ" class="span12" style="width:50%"  readonly="readonly" />
       	 	</td>
        </tr>
        
        <tr>
	        <th width="8%" class="right-border bottom-border text-right">其他要求</th>
	        <td colspan="3" class="bottom-border right-border" >
	        	<textarea class="span12" style="width:50%" rows="2" id="DESCRIBE"  fieldname="DESCRIBE" name="DESCRIBE"></textarea>
	        </td>
        </tr>
	<div>						
		<table class="table table-bordered">
			<thead>
		      <tr style="background:#0866c6;">
		         <th width="20%" class="text-center" style="color:white;">材料类型</th>
		         <th width="80%" class="text-center" style="color:white;">文件</th>
		      </tr>
		   </thead>
		   <tbody>
		      
		      <tr>
		         <td class="text-center">工程清单材料</td>
		         <td>
		         	<table role="presentation"  class="table table-striped">
						<tbody fjlb="7762" id="gccltbody" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery">
						</tbody>
					 </table>
		         </td>
		        
		      </tr>
		      <tr>
		         <td class="text-center">工程图纸材料</td>
		         <td>
		         	<table role="presentation"  class="table table-striped">
						<tbody fjlb="7763" id="gccltbody" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery">
						</tbody>
					 </table>
		         </td>
		        
		      </tr>
		   </tbody>
		</table>
		</div>
      </table>
  </form>
       
    </div>
   </div>
  </div>
    <form method="post" id="queryForm"  >
    	  <input type="hidden" id="QWXGC_UID" fieldname="WXGC_UID" name = "WXGC_UID" operation="="/>
     </form>
	<form method="post" id="pbfsformbyid"  >
		<input  type="hidden" id="PBFS_UID_SRARCH" name="PBFS_UID" fieldname="PBFS_UID" operation="="  >
	</form>	
  <div align="center">
 	<FORM name="frmPost" method="post" style="display:none" target="_blank">
		 <!--系统保留定义区域-->
         <input type="hidden" name="queryXML" id = "queryXML">
         <input type="hidden" name="txtXML" id = "txtXML">
         <input type="hidden" name="txtFilter"  order="desc" fieldname = "WXGC_UID" id = "txtFilter">
         <input type="hidden" name="resultXML" id = "resultXML">
       	 <!--传递行数据用的隐藏域-->
         <input type="hidden" name="rowData">
 	</FORM>
 </div>
  <jsp:include page="/jsp/file_upload/fileuploadold_config_js.jsp" flush="true"/> 
</body>
</html>