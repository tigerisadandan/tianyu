<!DOCTYPE HTML>

<%@ page language="java" pageEncoding="UTF-8"%>


<!-- Generic page styles -->
<!--<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/sys_resources/plugins/fileupload/css/style.css">-->
<!-- Bootstrap styles for responsive website layout, supporting different screen sizes -->
<!--<link rel="stylesheet" href="http://blueimp.github.com/cdn/css/bootstrap-responsive.min.css">-->
<!-- Bootstrap CSS fixes for IE6 -->
<!--[if lt IE 7]><link rel="stylesheet" href="http://blueimp.github.com/cdn/css/bootstrap-ie6.min.css"><![endif]-->
<!-- Bootstrap Image Gallery styles -->
<!--<link rel="stylesheet" href="http://blueimp.github.com/Bootstrap-Image-Gallery/css/bootstrap-image-gallery.min.css">-->
<!-- CSS to style the file input field as button and adjust the Bootstrap progress bars -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/fileupload/css/jquery.fileupload-ui.css">
<!-- Shim to make HTML5 elements usable in older Internet Explorer versions -->



<div class="row-fluid">
    <!-- The file upload form used as target for the file upload widget -->

    <div id="fileupload">   
        <!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
        <div class="row fileupload-buttonbar"  style="display:none;">
            <div class="span7">
                <!-- The fileinput-button span is used to style the file input field as button -->
                <span class="btn btn-success fileinput-button">
                    <i class="icon-plus icon-white"></i>
                    <span>Add files...</span>
                    <input id="fileupload_btn" type="file" name="files[]" multiple>
                </span>
                <button type="submit" class="btn btn-primary start">
                    <i class="icon-upload icon-white"></i>
                    <span>Start upload</span>
                </button>
                <button type="reset" class="btn btn-warning cancel">
                    <i class="icon-ban-circle icon-white"></i>
                    <span>Cancel upload</span>
                </button>
                <button type="button" class="btn btn-danger delete">
                    <i class="icon-trash icon-white"></i>
                    <span>Delete</span>
                </button>
                <input type="checkbox" class="toggle">
            </div>
            <!-- The global progress information -->
            <div class="span5 fileupload-progress fade">
                <!-- The global progress bar -->
                <div class="progress progress-success progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100">
                    <div class="bar" style="width:0%;"></div>
                </div>
                <!-- The extended global progress information -->
                <div class="progress-extended">&nbsp;</div>
            </div>
        </div>
        <!-- The loading indicator is shown during file processing -->
        <div class="fileupload-loading"></div>
        <br>
        <!-- The table listing the files available for upload/download -->
        <table role="presentation" class="table table-striped"><tbody class="files" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody></table>
   </div>

</div>
<!-- modal-gallery is the modal dialog used for the image gallery -->
<div id="modal-gallery" class="modal modal-gallery hide fade" data-filter=":odd">
    <div class="modal-header">
        <a class="close" data-dismiss="modal">&times;</a>
        <h3 class="modal-title"></h3>
    </div>
    <div class="modal-body"><div class="modal-image"></div></div>
    <div class="modal-footer">
        <a class="btn modal-download" target="_blank">
            <i class="icon-download"></i>
            <span>Download</span>
        </a>
        <a class="btn btn-success modal-play modal-slideshow" data-slideshow="5000">
            <i class="icon-play icon-white"></i>
            <span>Slideshow</span>
        </a>
        <a class="btn btn-info modal-prev">
            <i class="icon-arrow-left icon-white"></i>
            <span>Previous</span>
        </a>
        <a class="btn btn-primary modal-next">
            <span>Next</span>
            <i class="icon-arrow-right icon-white"></i>
        </a>
    </div>
</div>
<div style="display:none;" class="myKeyValueDiv">
	<input type="hidden" cond="true" condName="ywid">
	<input type="hidden" cond="true" condName="glid1">
	<input type="hidden" cond="true" condName="glid2">
	<input type="hidden" cond="true" condName="glid4" >
	<input type="hidden" cond="true" condName="sjbh">
	<input type="hidden" cond="true" condName="ywlx">
	<input type="hidden" cond="true" condName="fjzt">
	<input type="hidden" cond="true" condName="fjlb" id="_fileupload_fjlb">
</div>




<script type="text/javascript" charset="utf-8">
var g_nameMaxlength = 12;
//----------------------------------------
//-点击表格中“添加文件”按钮的事件
//----------------------------------------	
function doSelectFile(obj){
	var inputArr = $(".myKeyValueDiv input");
	for(var xx=0;xx<inputArr.length;xx++){
		if(inputArr[xx].getAttribute("cond")=="true"){
			if(inputArr[xx].getAttribute("condName")=="fjlb"){
				inputArr[xx].value=$(obj).attr("fjlb");
			}
		}
	}
	document.getElementById("fileupload_btn").click();
}
function optionsHandler(obj){
	var imageType = "false";
	$(".showFileTab").each(function(i){
		if($(this).attr("fjlb")==$("#_fileupload_fjlb").val()){
			//acceptFileTypes
			var showTypeAttr = $(this).attr("uploadOptions");
			if(showTypeAttr!=undefined && showTypeAttr!=""){
				var arr = showTypeAttr.split(";");
				for(var x=0;x<arr.length;x++){
					var arrX = arr[x].split(":");
					if(arrX[0]=="type" && (arrX[1]=="image"|| arrX[1]=="img")){
						imageType = "true";
					}
				}
			}
		}
	});
	if(imageType=="true"){
		obj.acceptFileTypes = /(\.|\/)(gif|jpe?g|png)$/i;
	}else{
		obj.acceptFileTypes = /.+$/i;
	}
	return obj;
}
function setFileUploadYwid(){				
	$.ajax({
		url:"${pageContext.request.contextPath }/fileUploadOldController?getYwid",
		data:"",
		dataType:"json",
		async:false,
		success:function(result){
			var ywid = result.msg;
			var inputArr = $(".myKeyValueDiv input");
			if($("#ywid").val()==undefined || $("#ywid").val()==""){
				$("#ywid").val(ywid);
				for(var xx=0;xx<inputArr.length;xx++){
					if(inputArr[xx].getAttribute("cond")=="true"){
						if(inputArr[xx].getAttribute("condName")=="ywid"){
							inputArr[xx].value=$("#ywid").val();
						}
					}
				}
			}
		}
	});
}
$(function () {
	var url = '${pageContext.request.contextPath }/UploadOldServlet';
    //初始化业务ID
 setFileUploadYwid();		    
    //初始化，主要是设置上传参数，以及事件处理方法(回调函数)
 $('#fileupload').fileupload({					    
        autoUpload: true,//是否自动上传
		maxFileSize: 20000000,//大小限制，20M
        url: url,//上传地址
        dataType: 'json',
        maxNumberOfFiles: 99999,//允许最多上传数量，用户要求不要限制，这里直接给个大点的数值
        done: function (e, data) {//设置文件上传完毕事件的回调函数
            var that = $(this).data('fileupload'),
                template;
            if (data.context) {
                data.context.each(function (index) {
                    var file = ($.isArray(data.result) &&
                            data.result[index]) || {error: 'emptyResult'};
                    if (file.error) {
                        that._adjustMaxNumberOfFiles(1);
                    }
                    that._transition($(this)).done(
                        function () {
                            var node = $(this);
                            template = that._renderDownload([file])
                                .replaceAll(node);
                            that._forceReflow(template);
                            that._transition(template).done(
                                function () {
                                    data.context = $(this);
                                    that._trigger('completed', e, data);
                                }
                            );
                        }
                    );
                });
            } else {
                if ($.isArray(data.result)) {
                    $.each(data.result, function (index, file) {
                        if (data.maxNumberOfFilesAdjusted && file.error) {
                            that._adjustMaxNumberOfFiles(1);
                        } else if (!data.maxNumberOfFilesAdjusted &&
                                !file.error) {
                            that._adjustMaxNumberOfFiles(-1);
                        }
                    });
                    data.maxNumberOfFilesAdjusted = true;
                }
                template = that._renderDownload(data.result)
                    .appendTo(that.options.filesContainer);
                that._forceReflow(template);
                that._transition(template).done(
                    function () {
                        data.context = $(this);
                        that._trigger('completed', e, data);
                    }
                );
            }
          
        	insertFileTab(JSON.stringify(data.result));//不再向download模版里面画结果，画到自己的table里
        },
        progressall: function (e, data) {//设置上传进度事件的回调函数
            var progress = parseInt(data.loaded / data.total * 100, 10);
            $('.progress-success .bar').css(
                'width',
                progress + '%'
            );
        }
  });
});



//----------------------------------------
//-清空附件列表
//----------------------------------------
function clearFileTab(n){
	$(".showFileTab").each(function(){
		if($(this).attr("notClear")=="true"){
			//doNothing
		}else{
			$(this).empty();
		}
	});
	if(n=="query"){
		//do nothing
	}else{
		var inputArr = $(".myKeyValueDiv input");
		for(var xx=0;xx<inputArr.length;xx++){
			if(inputArr[xx].getAttribute("cond")=="true"){
				if(inputArr[xx].getAttribute("condName")=="ywid"){
					inputArr[xx].value="";
				}else if(inputArr[xx].getAttribute("condName")=="glid1"){
					inputArr[xx].value="";
				}else if(inputArr[xx].getAttribute("condName")=="sjbh"){
					inputArr[xx].value="";
				}else if(inputArr[xx].getAttribute("condName")=="ywlx"){
					inputArr[xx].value="";
				}else if(inputArr[xx].getAttribute("condName")=="fjzt"){
					inputArr[xx].value="0";
				}
			}
		}
	}
}
function insertFileTab_bak(result){
	var files = eval('(' + result + ')');
	if(g_nameMaxlength==undefined){
		g_nameMaxlength=12;
	}
	$(".showFileTab").each(function(i){
		$(this).width();
		$(this).parent().addClass("nomargin");
		var fjlb = $(this).attr("fjlb");
		for(var i=0;i<files.length;i++){
		var showHtml = "";
			var file = files[i];
			//对关键字段赋值
			$("#ywid").val(file.ywid);
			var inputArr = $(".myKeyValueDiv input");
			for(var xx=0;xx<inputArr.length;xx++){
				if(inputArr[xx].getAttribute("cond")=="true"){
					if(inputArr[xx].getAttribute("condName")=="ywid"){
						inputArr[xx].value=file.ywid;
					}
				}
			}
			var number = 0;
			//如果table没有附件类别，那么插入所有数据，否则只插入附件类别相匹配的数据
			if(fjlb==undefined || fjlb==file.fjlb){
				//showHtml +="<tr class='fu_cellTr'>";
				//showHtml +="<td class='fu_cellTd'>";
				//showHtml +="<table class='fu_cellTable'>";
				showHtml +="<tr class='template-download'>";
				/**停用缩略图功能
				showHtml +="<td class='preview'>";
				//如果不是图片，那么不使用gallery功能
				if(file.fileType.indexOf("image")!=-1){
					showHtml +="<a href='"+file.url+"' title='"+file.name+"' rel='gallery' download='"+file.name+"'><img src='"+file.thumbnail_url+"'></a>";
				}else{
					showHtml +="<a href='"+file.url+"' title='"+file.name+"'  download='"+file.name+"'><img src='"+file.thumbnail_url+"'></a>";
				}
				showHtml +="</td>";
				*/
				showHtml +="<td class='name' style='border:0;'>";
				//名字长度过长，折行处理
				if(file.name.length>g_nameMaxlength){
					//showHtml +="<a href='"+file.url+"' title='"+file.name+"' rel='gallery' download='"+file.name+"'>"+file.name.substring(0,file.name.length/2)+"<br/>"+file.name.substring((file.name.length/2),file.name.length)+"</a>"
					//showHtml +="<a href='"+file.url+"' title='"+file.name+"' fileid='"+file.fileid+"' rel='gallery' download='"+file.name+"'>"+file.name.substring(0,g_nameMaxlength)+"...</a>"
					showHtml +="<span class='my-showPreview-btn nameSpan' fileid='"+file.fileid+"'>"+file.name+"</span>"
				}else{
					showHtml +="<a href='javascript:void(0);' class='my-showPreview-btn nameSpan' fileid='"+file.fileid+"'>"+file.name+"</a>"
				}
				showHtml +="</td>";
				showHtml +="<td class='size ' style='border:0;'>"+file.size+"</td>";
				//showHtml +="<td colspan='2'></td>";
				if($(this).attr("onlyView")=="true"){
					//如果是只读表格，那么不需要删除按钮
					showHtml +="<td class='myPreview' style='border:0;text-align:center;'>";
				}else{
					showHtml +="<td class='delete ' style='border:0;'>";
					showHtml +="<button class='btn btn-link my-del-btn' data-type='"+file.delete_type+"' data-url='"+file.delete_url+"' >";
					showHtml +="<i class='icon-trash'></i>";
					showHtml +="<span>删除</span>";
					showHtml +="</button>&nbsp;";
					//showHtml +="<input type='checkbox' name='delete' value='1'>"
					showHtml +="</td>"
					showHtml +="<td class='myPreview' style='border:0;'>";
				}
				showHtml +="<a href='"+file.url+"' class='btn btn-link' fileid='"+file.fileid+"' rel='gallery' download='"+file.name+"'>";
				showHtml +="<i class='icon-download-alt'></i>";
				showHtml +="<span>下载</span>";
				showHtml +="</a>&nbsp;";
				showHtml +="</td>";
				showHtml +="</tr>";
				//showHtml +="</table>";
				//showHtml +="</td>";
				//showHtml +="<td class='fu_cellTd'>";
				//showHtml +="</td>";
				//showHtml +="</tr>";
				var showHtmlHead = "";
				showHtmlHead +="<tr class='fu_cellTr'>";
				showHtmlHead +="<td class='fu_cellTd' style='border:0px;'>";
				showHtmlHead +="<table class='fu_cellTable'>";
				showHtmlHead += showHtml;
				showHtmlHead +="</table>";
				showHtmlHead +="</td>";
				showHtmlHead +="<td class='fu_cellTd' style='border-top:0px;border-bottom:0;border-left:1px solid #ddd;border-right:0px;'>";
				showHtmlHead +="<table class='fu_cellTable' name='aa'>";
				showHtmlHead +="</table>";
				showHtmlHead +="</td>";
				showHtmlHead +="</tr>";
				var templateObj = $(this).find(".fu_cellTr:last").find(".fu_cellTd:last").find(".fu_cellTable");//;
				if(templateObj.attr("class")!=undefined){
					if(templateObj.find(".template-download").attr("class")==undefined){
						templateObj.append(showHtml);
					}else{
						$(this).append(showHtmlHead);
					}
				}else{
					$(this).append(showHtmlHead);
				}
			}
		}
	});
}
function insertFileTab(result){
	var files = eval('(' + result + ')');
	if(g_nameMaxlength==undefined){
		g_nameMaxlength=12;
	}
	var tab_width = 0;
	$(".showFileTab").each(function(i){
		tab_width = $(this).parent().parent().width();
		$(this).parent().addClass("nomargin");
		var showType = $(this).attr("showType");
		if(showType!=undefined){
			insertImageTab($(this),files,tab_width);
		}else{
			insertDetailTab($(this),files,tab_width);
		}
	});
}
//----------------------------------------
//-预览图片
//----------------------------------------
function showPreview(n){
	/**
	var iframe_count = 0;
	//获取父页面弹出层个数
	parent.$("body  div").each(function(i){
		if($(this).attr("aria-labelledby")=="myModalLabel"){
			if($(this).attr("aria-hidden")=="true"){
				//do nothing
			}else{
				iframe_count++;
			}
		}
	});
	if(iframe_count==0){
		//如果没有弹出层，那么在父页面弹出一个新层预览
		parent.$("body").manhuaDialog({"title" : "附件预览","type" : "text","content" : "${pageContext.request.contextPath }/fileUploadOldController?doPreview&fileid="+n,"modal":"2"});
	}else{
		//如果已经有弹出层，那么在当前页面弹出新层预览
		$("body").manhuaDialog({"title" : "附件预览","type" : "text","content" : "${pageContext.request.contextPath }/fileUploadOldController?doPreview&fileid="+n,"modal":"2"});
	}
	*/
	//window.open("${pageContext.request.contextPath }/fileUploadOldController?doPreview&fileid="+n);doRedirect
	$("#previewFileid").val(n);
	window.open(encodeURI("${pageContext.request.contextPath }/jsp/file_upload/showPreviewold.jsp"));
}
//----------------------------------------
//-删除附件数据,只能删除附件状态为0的数据
//-@param ywid 业务ID
//-@param glid1 项目ID
//-@param sjbh	事件编号
//-@param ywlx	业务类型
//-@param fjzt	附件状态
//----------------------------------------
function deleteFileData(ywid,glid1,sjbh,ywlx){
	var obj = new Object();
	obj.YWID = ywid;
	obj.GLID1 = glid1;
	obj.SJBH = sjbh;
	obj.YWLX = ywlx;
	obj.FJZT = "0";
	var data = JSON.stringify(obj);
		var data1 = defaultJson.packSaveJson(data);
		$.ajax({
		url : "${pageContext.request.contextPath }/fileUploadOldController?deleteFile",
		data : data1,
		cache : false,
		async :	false,
		dataType : "json",
		success : function(result) {
		}
	});
}
//----------------------------------------
//-对关键字段进行赋值操作，保证新增的记录数据完整
//-@param ywid 业务ID，可以暂时理解为批次编号
//-@param glid1 项目ID（或企业材料id
//-@param sjbh	事件编号
//-@param ywlx	业务类型
//-@param fjzt	附件状态
//-@param glid2 审批材料库ID
//----------------------------------------	
function setFileData(ywid,glid1,sjbh,ywlx,fjzt,glid2){
	var inputArr = $(".myKeyValueDiv input");
	if($("#ywid").val()==undefined || $("#ywid").val()==""){
		$("#ywid").val(ywid);
	}
	for(var xx=0;xx<inputArr.length;xx++){
		if(inputArr[xx].getAttribute("cond")=="true"){
			if(inputArr[xx].getAttribute("condName")=="ywid"){
				inputArr[xx].value=ywid;
			}else if(inputArr[xx].getAttribute("condName")=="glid1"){
				inputArr[xx].value=glid1;
			}else if(inputArr[xx].getAttribute("condName")=="sjbh"){
				inputArr[xx].value=sjbh;
			}else if(inputArr[xx].getAttribute("condName")=="ywlx"){
				inputArr[xx].value=ywlx;
			}else if(inputArr[xx].getAttribute("condName")=="fjzt"){
				if(fjzt!=undefined && fjzt!=""){
					inputArr[xx].value=fjzt;
				}else{
					inputArr[xx].value="1";
				}
			}else if(inputArr[xx].getAttribute("condName")=="glid2"){
				inputArr[xx].value=glid2;
			}
		}
	}
}
//----------------------------------------
//-返回某个上传表格中的附件数量
//-@param  fjlb 附件类别，为空的话查询所有的表格
//----------------------------------------
function getFileCounts(fjlb){
	var tabArr = $(".showFileTab");
	var counts = 0;
	if(fjlb==undefined){
		$(".showFileTab").each(function(i){
			$(this).find("tr[class='template-download']").each(function(j){
				counts++;
			});
		});
	}else{
		$(".showFileTab").each(function(i){
			var tempFjlb = $(this).attr("fjlb");
			if(tempFjlb==fjlb){
				$(this).find("tr[class='template-download']").each(function(j){
					counts++;
				});
			}
		});
	}
	return counts;
}
//----------------------------------------
//-查询文件信息，并插入到表格
//-@param ywid 业务ID，可以暂时理解为批次编号
//-@param glid1 项目ID
//-@param sjbh	事件编号
//-@param ywlx	业务类型
//----------------------------------------
function queryFileData(ywid,glid1,sjbh,ywlx){
	var obj = new Object();
	obj.YWID = ywid;
	obj.GLID1 = glid1;
	obj.SJBH = sjbh;
	obj.YWLX = ywlx;
	var data = JSON.stringify(obj);
		var data1 = defaultJson.packSaveJson(data);
		$.ajax({
		url : "${pageContext.request.contextPath }/fileUploadOldController?queryFileList",
		data : data1,
		cache : false,
		async :	false,
		dataType : "json",
		success : function(result) {
			clearFileTab("query");
			insertFileTab(result.msg);
		}
	});
}
//----------------------------------------
//-查询文件信息，并插入到表格
//-@param ywid 业务ID，可以暂时理解为批次编号
//-@param glid1 项目ID
//-@param sjbh	事件编号
//-@param ywlx	业务类型
//----------------------------------------
function queryImageData(ywid,glid1,sjbh,ywlx){
	var obj = new Object();
	obj.YWID = ywid;
	obj.GLID1 = glid1;
	obj.SJBH = sjbh;
	obj.YWLX = ywlx;
	var data = JSON.stringify(obj);
		var data1 = defaultJson.packSaveJson(data);
		$.ajax({
		url : "${pageContext.request.contextPath }/fileUploadOldController?queryFileList",
		data : data1,
		cache : false,
		async :	true,
		dataType : "json",
		success : function(result) {
			clearFileTab("query");
			insertImageTab(result.msg);
		}
	});
}
function insertDetailTab(obj,files,tab_width){
	var fjlb = obj.attr("fjlb");
	for(var i=0;i<files.length;i++){
		var showHtml = "";
		var file = files[i];
		//对关键字段赋值
		$("#ywid").val(file.ywid);
		var inputArr = $(".myKeyValueDiv input");
		for(var xx=0;xx<inputArr.length;xx++){
			if(inputArr[xx].getAttribute("cond")=="true"){
				if(inputArr[xx].getAttribute("condName")=="ywid"){
					inputArr[xx].value=file.ywid;
				}
			}
		}
		var number = 0;
		//如果table没有附件类别，那么插入所有数据，否则只插入附件类别相匹配的数据
		if(fjlb==undefined || fjlb==file.fjlb){
			showHtml +="<tr class='template-download'   rowid='"+file.fileid+"'>";
			showHtml +="<td class='name' style='border:0;padding:0px;'>";
			//名字长度过长，折行处理
			if(file.name.length>30){
				showHtml +="<span class='my-showPreview-btn nameSpan' fileid='"+file.fileid+"'><abbr title='"+file.name+"'>"+file.name.substring(0,g_nameMaxlength)+"...</abbr></span>"
			}else{
				showHtml +="<span class='my-showPreview-btn nameSpan' fileid='"+file.fileid+"' target='_blank'>"+file.name+"</span>"
			}
			showHtml +="</td>";
			if(obj.attr("showSize")!="false"){
				showHtml +="<td class='size ' style='border:0;padding:0px;'>"+file.size+"</td>";
			}
			if(obj.attr("showLrr")!="false" && obj.attr("showLrr")!=undefined){
				showHtml +="<td class='size ' style='border:0;padding:0px;text-align:right;'>"+file.lrr+"</td>";
			}
			if(obj.attr("showLrsj")!="false" && obj.attr("showLrsj")!=undefined){
				showHtml +="<td class='size ' style='border:0;padding:0px;'>"+file.lrsj+"</td>";
			}

			showHtml +="<td class='delete ' style='border:0;padding:0px;'>";
			
			if(obj.attr("onlyView")=="true"){
					//如果是只读表格，那么不需要删除按钮
				if(obj.attr("deleteUser")!=undefined && obj.attr("deleteUser")==file.lrrCode){
// 								showHtml +="<td class='delete ' style='border:0;padding:0px;'>";
						showHtml +="<button class='btn btn-link my-del-btn' data-type='"+file.delete_type+"' data-url='"+file.delete_url+"' >";
						showHtml +="<i class='icon-trash'></i>";
						showHtml +="<span>删除</span>";
						showHtml +="</button>";
						//showHtml +="<input type='checkbox' name='delete' value='1'>"
// 								showHtml +="</td>"
// 								showHtml +="<td class='myPreview' style='border:0;padding:0px;'>";
					}else{
// 								showHtml +="<td class='myPreview' style='border:0;text-align:center;padding:0px;'>";
					}
			}else{
					showHtml +="<button class='btn btn-link my-del-btn' data-type='"+file.delete_type+"' data-url='"+file.delete_url+"' >";
					showHtml +="<i class='icon-trash'></i>";
					showHtml +="<span>删除</span>";
					showHtml +="</button>";
				}
	
			showHtml +="<a href='"+file.url+"' class='btn btn-link' fileid='"+file.fileid+"' download='"+file.name+"'>";
			showHtml +="<i class='icon-download-alt'></i>";
			showHtml +="<span>下载</span>";
			showHtml +="</a>&nbsp;";
		
			showHtml +="</td>";
			showHtml +="</tr>";
			var showHtmlHead = "";

			showHtmlHead += showHtml;
			obj.append(showHtmlHead);
		}
	}

}
function insertImageTab(obj,files,tab_width){
	var fjlb = obj.attr("fjlb");
	var showType = obj.attr("showType");
	var stArr = showType.split(";");
	var _height = "";
	var _width = "";
	var _del = "true";		//多张图片时，是否自动删除旧数据
	var _attr = "single";	//单张图片还是多张图片
	var _noborder = "false";//是否有边框
	var _maxnum = "9999";	//控制图片最多显示的数量
	var _imgalign = "left";
	var _preview = "false";
	for(var stx=0;stx<stArr.length;stx++){
		var attrArr = stArr[stx].split(":");
		for(var attx=0;attx<attrArr.length;attx++){
			if(attrArr[0]=="width"){
				_width = "width:"+attrArr[1]+";";
			}else if(attrArr[0]=="height"){
				_height = "height:"+attrArr[1]+";";
			}else if(attrArr[0]=="del"){
				_del = attrArr[1];
			}else if(attrArr[0]=="attr"){
				_attr = attrArr[1];
			}else if(attrArr[0]=="noborder"){
				_noborder = attrArr[1];
			}else if(attrArr[0]=="maxnum"){
				_maxnum = attrArr[1];
			}else if(attrArr[0]=="i-align"){
				_imgalign = attrArr[1];
			}else if(attrArr[0]=="preview"){
				_preview = attrArr[1];
			}
		}
	}
	var imgNum = 0;
	for(var i=0;i<files.length;i++){
		var showHtml = "";
		var file = files[i];
		$("#ywid").val(file.ywid);
		var inputArr = $(".myKeyValueDiv input");
		for(var xx=0;xx<inputArr.length;xx++){
			if(inputArr[xx].getAttribute("cond")=="true"){
				if(inputArr[xx].getAttribute("condName")=="ywid"){
					inputArr[xx].value=file.ywid;
				}
			}
		}
		//如果table没有附件类别，那么插入所有数据，否则只插入附件类别相匹配的数据
		if(fjlb==undefined || fjlb==file.fjlb){
			if(file.fileType.indexOf("image")!=-1){
				imgNum++;
				if(_attr=="multi"){
					//多张图片就不需要删除了
					if(_noborder=="true"){
						showHtml +="<table class='fu_blockTable_half' cellspacing='0' cellpadding='0' style='border:0;text-align:center;'>";
					}else{
						showHtml +="<table class='fu_blockTable_half' cellspacing='0' cellpadding='0'>";
					}
				}else{
					var delBtn = obj.find("tr").find("td").find(".my-del-btn");
					if(delBtn.attr("class")!=undefined){
						delBtn.click();
					}
				}
				//showHtml +="<table class='fu_blockTable_full' style='background:#FFFFFF;text-align:center;padding:0px;border:0px;' cellspacing='0' cellpadding='0'>";
				showHtml +="<tr class='template-download fu_blockTable_full' style='text-align:center;'>";
				
				showHtml +="<td style='background:#FFFFFF;text-align:center;padding:0px;border:0px;'>";
				//showHtml +="<a data-gallery='gallery' href='"+file.url+"' title='"+file.name+"' rel='gallery'><img src='"+file.thumbnail_url+"' width='70px' height='30px'></a>";
				if(_preview=="true"){
					showHtml +="<a href='javascript:void(0)' class='my-showPreview-btn nameSpan' fileid='"+file.fileid+"'><img src='"+file.url+"' style='"+_width+_height+"'></a>";
				}else{
					showHtml +="<img src='"+file.url+"' style='"+_width+_height+"'>";
				}
				showHtml +="</td>"
				showHtml +="<td class='delete' style='border:0;padding:0px;display:none;'>";
				if(_del=="true"){
					showHtml +="<button class='btn btn-link my-del-btn' data-type='"+file.delete_type+"' data-url='"+file.delete_url+"' >";
				}else{
					showHtml +="<button class='btn btn-link my-del-btn' data-type='"+file.delete_type+"' data-url='"+file.modifyflag_url+"' >";
				}
				showHtml +="<i class='icon-trash'></i>";
				showHtml +="<span>删除</span>";
				showHtml +="</button>";
				showHtml +="</td>"
				showHtml +="</tr>";
				if(_attr=="multi"){
					showHtml +="</table>";
				}
				var showHtmlHead = "";
				showHtmlHead +="<tr class='fu_cellTr'>";
				showHtmlHead +="<td class='fu_cellTd' style='border:0px;padding:0px;'>";
				showHtmlHead +="<div class='p_container' style='border:0px;padding:0px;'>";
				showHtmlHead += showHtml;
				showHtmlHead +="</div>";
				showHtmlHead +="</td>";
				showHtmlHead +="</tr>";
				if(_attr=="multi"){
					//如果是多张图片，那么继续画表格
					if(imgNum>Number(_maxnum)){
						break;
					}else{
						var templateObj = obj.find(".fu_cellTr").find(".fu_cellTd").find(".p_container");//;
						if(templateObj.attr("class")!=undefined){
							templateObj.append(showHtml);
						}else{
							obj.append(showHtmlHead);
						}
					}
				}else{
					obj.append(showHtml);
					break;
				}
			}else{
				continue;
			}
		}
	}
}

</script>

<!-- The template to display files available for upload -->
<script id="template-upload" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-upload fade">
        <td class="preview"><span class="fade"></span></td>
        <td class="name"><span>{%=file.name%}</span></td>
        <td class="size"><span>{%=o.formatFileSize(file.size)%}</span></td>
        {% if (file.error) { %}
            <td class="error" colspan="2"><span class="label label-important">{%=locale.fileupload.error%}</span> {%=locale.fileupload.errors[file.error] || file.error%}</td>
        {% } else if (o.files.valid && !i) { %}
            <td>
                <div class="progress progress-success progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0"><div class="bar" style="width:0%;"></div></div>
            </td>
            <td class="start">{% if (!o.options.autoUpload) { %}
                <button class="btn btn-primary">
                    <i class="icon-upload icon-white"></i>
                    <span>{%=locale.fileupload.start%}</span>
                </button>
            {% } %}</td>
        {% } else { %}
            <td colspan="2"></td>
        {% } %}
        <td class="cancel">{% if (!i) { %}
            <button class="btn btn-warning">
                <i class="icon-ban-circle icon-white"></i>
                <span>{%=locale.fileupload.cancel%}</span>
            </button>
        {% } %}</td>
    </tr>
{% } %}
</script>
<!-- The template to display files available for download -->
<script id="template-download" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-download fade">
        {% if (file.error) { %}
            <td></td>
            <td class="name"><span>{%=file.name%}</span></td>
            <td class="size"><span>{%=o.formatFileSize(file.size)%}</span></td>
            <td class="error" colspan="2"><span class="label label-important">{%=locale.fileupload.error%}</span> {%=locale.fileupload.errors[file.error] || file.error%}</td>
        {% } else { %}
            <td class="preview">{% if (file.thumbnail_url) { %}
                <a href="{%=file.url%}" title="{%=file.name%}" rel="gallery" download="{%=file.name%}"><img src="{%=file.thumbnail_url%}"></a>
            {% } %}</td>
            <td class="name">
                <a href="{%=file.url%}" title="{%=file.name%}" rel="{%=file.thumbnail_url&&'gallery'%}" download="{%=file.name%}">{%=file.name%}</a>
            </td>
            <td class="size"><span>{%=o.formatFileSize(file.size)%}</span></td>
            <td colspan="2"></td>
        {% } %}
        <td class="delete">
            <button class="btn btn-danger" data-type="{%=file.delete_type%}" data-url="{%=file.delete_url%}">
                <i class="icon-trash icon-white"></i>
                <span>{%=locale.fileupload.destroy%}</span>
            </button>
            <input type="checkbox" name="delete" value="1">
        </td>
    </tr>
{% } %}
</script>
<!--<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>-->
<!-- The jQuery UI widget factory, can be omitted if jQuery UI is already included -->
<script src="${pageContext.request.contextPath }/js/fileupload/js/vendor/jquery.ui.widget.js"></script>
<!-- The Templates plugin is included to render the upload/download listings -->

<!--<script src="http://blueimp.github.com/JavaScript-Templates/tmpl.min.js"></script>-->
<!-- The Load Image plugin is included for the preview images and image resizing functionality -->
<!--<script src="http://blueimp.github.com/JavaScript-Load-Image/load-image.min.js"></script>-->
<!-- The Canvas to Blob plugin is included for image resizing functionality -->
<!--<script src="http://blueimp.github.com/JavaScript-Canvas-to-Blob/canvas-to-blob.min.js"></script>-->
<!-- Bootstrap JS and Bootstrap Image Gallery are not required, but included for the demo -->
<!--<script src="http://blueimp.github.com/cdn/js/bootstrap.min.js"></script>-->
<!--<script src="http://blueimp.github.com/Bootstrap-Image-Gallery/js/bootstrap-image-gallery.min.js"></script>-->

<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
<script src="${pageContext.request.contextPath }/js/fileupload/js/jquery.iframe-transport.js"></script>
<!-- The basic File Upload plugin -->
<script src="${pageContext.request.contextPath }/js/fileupload/js/jquery.fileupload.js"></script>
<!-- The File Upload file processing plugin -->
<script src="${pageContext.request.contextPath }/js/fileupload/js/jquery.fileupload-fp.js"></script>
<!-- The File Upload user interface plugin -->
<script src="${pageContext.request.contextPath }/js/fileupload/js/jquery.fileupload-ui.js"></script>
<!-- The localization script -->
<script src="${pageContext.request.contextPath }/js/fileupload/js/locale.js"></script>
<!-- The main application script -->
<script src="${pageContext.request.contextPath }/js/fileupload/js/main.js"></script>
<!-- The XDomainRequest Transport is included for cross-domain file deletion for IE8+ -->
<!--[if gte IE 8]><script src="${pageContext.request.contextPath }/js/fileupload/js/cors/jquery.xdr-transport.js"></script><![endif]-->



