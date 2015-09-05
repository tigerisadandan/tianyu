<!DOCTYPE html>
<html>
	<head>
		<%@ page language="java" pageEncoding="GBK"%>
		<%@ taglib uri="/tld/base.tld" prefix="app"%>
		<app:base />
		<title>Preview</title>
		<style>
			body{
				margin:0px;
				widht:100%;
				height:100%;
			}
			.iframe{
				widht:100%;
				height:100%;
			}
		</style>
		<script type="text/javascript" charset="utf-8">
			$(function(){
				doInit();
			});
			function doInit(){
				var obj = $(window.opener.document).find("#previewFileid");
				var fileid = obj.val();
				var type = $(window.opener.document).find("#previewMethod");
				var actionUrl = "";
				if(type.val()=="history"){
					var fileName = encodeURI($(window.opener.document).find("#previewName").val());
					var fileType = encodeURI($(window.opener.document).find("#previewType").val());
					actionUrl = "${pageContext.request.contextPath }/PubWS.do?getOldFjAction&filename="+fileName+"&filepath=FlowApply&filenametype="+fileType;
				}else{
					actionUrl = "${pageContext.request.contextPath }/fileUploadOldController?doPreview&fileid="+fileid;
				} 
				$.ajax({
					url:actionUrl,
					data:"",
					dataType:"json",
					async:true,
					success:function(result){
						var res = result.msg;
						if(res!=""){
							var flag = false;
							var res1 = res.toLowerCase();
							//obj.acceptFileTypes =/(\.|\/)(gif|jpe?g|png|bmp|pdf)$/i;//ֻ���ϴ���Щ�ļ�
							if(res1.lastIndexOf(".jpg")|| res1.lastIndexOf(".jpeg") || res1.lastIndexOf(".png")|| res1.lastIndexOf(".bmp")||res1.lastIndexOf(".gif")){
								flag = true;
							}
						
							if(flag){
								window.location.href=res;
							}else{
								$("#showPreviewFrame").attr("src",res);
							}
						}
					}
				});
			}
		</script>
	</head>
	<body >
		<iframe id="showPreviewFrame" frameborder="0" style="width:100%;height:98%;overflow:auto;">
		
		</iframe>
	</body>
</html>
