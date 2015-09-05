<!DOCTYPE html>
<%@page import="com.ccthanking.framework.Constants"%>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<title>快速入口——设置</title>

<%
	String resourceStyle = request.getParameter("resourceStyle");
%>
<script src="${pageContext.request.contextPath }/js/common/xWindow.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/ztree_css/demo.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/ztree_css/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.min.js"></script>
<style type="text/css">

#setting{
	height:570px;	
}

#menuTree2{
	float: left;
	width:300px;
	height: 570px;
	overflow-y:auto;
	padding:2px;
	border:1px gray dotted;
	/*background-color:powderblue*/
}
	
#movebutton{
	float: left;
	width:100px;
	height: 560px;
	text-align:center;
}

#menuTree{
	float: left;
	width:300px;
	height: 560px;
	margin-left: 100px;
	overflow-y:auto;
	border:1px gray dotted;
}

#preview{
	width: 280px;
	margin-left:40px;
	margin-top:0px;
	float: left;
}

#preview a:HOVER{

}

#preview table{
	width: 100%;
}

#setting button{
	display:block;
	width:60px;
	margin-top: 20px;
	margin-left: 20px;
}

#preview a{
	display:block;	
	width:130px;
	height:130px;
	margin-bottom: 12px;
	text-decoration:none;
	border:1px white solid;
	cursor: pointer;
	background-image: url("${pageContext.request.contextPath }/images/dtgl_homepage/quicklink.png");
}

#preview a span{	
	display:block;
	margin-top:90px;
	margin-left:10px;
	text-align:center;
	width:110px;
	color: white;	
	font-size: 18px;
	font-family:Microsoft YaHei;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}

</style>
<script type="text/javascript" charset="utf-8">
	var resourceStyle ="<%=resourceStyle%>";
	var controllername="${pageContext.request.contextPath}/MenuTreeController";
	
	//TREE的操作
	//tree设置
	var setting = {
		check: {
			enable: true,
			chkStyle : "checkbox",
			chkboxType : { "Y" : "", "N" : "" },
		},		
		edit: {
			enable: true,
			showRemoveBtn: false,
			showRenameBtn: false,
			drag:{
				isCopy:true,
				isMove:false,				
				prev: false,
				inner: false,
				next: false
				
			},
		},
		callback: {
			beforeDrag: beforeDrag,
			beforeDrop: beforeDrop
		},
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "parentId"
			}
		}
	};	
	
	var setting2 = {			
			edit: {				
				enable: true,
				showRemoveBtn: false,
				showRenameBtn: false,
				drag: {
					prev: true,
					inner: true,
					next: true
				}
			},
			callback: {
				beforeDrag: beforeDrag2,
				beforeDrop: beforeDrop2
			},
			view: {
				dblClickExpand: false
			},
			data: {
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "parentId"
				}
			}
		};	
	
	
	function beforeDrag(treeId, treeNodes) {
		var treeObj2 = $.fn.zTree.getZTreeObj("menuTree2");
		var tree2Nodes = treeObj2.transformToArray(treeObj2.getNodes());
		var tree2NodesNum = tree2Nodes.length;
		if(tree2NodesNum>7){
			alert("对不起，最多只允许设置八个");
			return false;
		}else {
			for (var i=0,l=treeNodes.length; i<l; i++) {			
				//禁止选择父节点
				if(!treeNodes[i].isParent){
					//判断拖拽的节点是否已存在
					for(var j=0;j<tree2Nodes.length;j++){
						tree1NodesName=treeNodes[i].name;
						tree2NodesName=tree2Nodes[j].name;
						if(tree1NodesName==tree2NodesName){
							alert("《"+tree1NodesName+"》节点已存在");
							return false;
						}
					}
										
					if (treeNodes[i].drag === false) {
						return false;
					}
					return true;
				}else if(treeNodes[i].isParent){
					alert("对不起，不能选择父节点,请选择最低层的节点");
					return false;
				}			
			}			
		}
		return false;	
	}
	function beforeDrop(treeId, treeNodes, targetNode, moveType) {
		if(targetNode!=null&&moveType=="inner"){
			alert("这些节点都是同级子节点的，请不要将节点拖到另一个节点里面");
			return false;
		}
		return targetNode ? targetNode.drop !== false : true;
	}
	
	function beforeDrag2(treeId, treeNodes) {
		for (var i=0,l=treeNodes.length; i<l; i++) {
			if (treeNodes[i].drag === false) {
				return false;
			}
		}
		return true;
	}
	
	function beforeDrop2(treeId, treeNodes, targetNode, moveType) {		
		if(moveInDiv("menuTree")){
			alert("禁止向原树拖拽节点！");
			return false;
		}
		
		if(moveType=="inner"){
			alert("这些节点都是同级子节点的，请不要将节点拖到另一个节点里面");
			return false;
		}
		
	}
	
	//判断鼠标是否在某个DIV中
	function moveInDiv(id){
		var x=window.event.clientX;
		var y=window.event.clientY;
		
		var div=document.getElementById(id);
		var divx1 = div.offsetLeft;  
        var divy1 = div.offsetTop;  
        var divx2 = div.offsetLeft + div.offsetWidth;  
        var divy2 = div.offsetTop + div.offsetHeight;
        if(x>divx1 && x<divx2 && y>divy1 && y<divy2){
        	return true;
        }else{
        	return false;
        }
	}
	
//页面初始化
$(function() {
	
	$.ajax({
		url : controllername+"?getTree",
		cache : false,
		async : false,
		dataType : "json",
		type : 'post',
		success : function(json) {
			var zNodes=json;
			$.fn.zTree.init($("#menuTree"), setting,zNodes);
			//必需保证Tree1和Tree2在同一个div下
			$.fn.zTree.init($("#menuTree2"),setting2);			
			setTree();
		}
	});
	
	init();
	
	initnodes();
	
	$("#btnClose").bind("click", function(){
		var a=$(this).manhuaDialog.close();
	});
	
	$("#btnSave").bind("click",function(){
		preview2();
		
		var preview=$("#preview").html();
				
		var a=$(window).manhuaDialog.getParentObj();//main页面		
		parent.document.getElementById("getResource").value=preview;//homepage页面
		//重新加载数据	
		a.init();
	   	//关闭当前页
		$(window).manhuaDialog.close();
	});	
	
	$("#moveright").click(function(){
		var treeObj = $.fn.zTree.getZTreeObj("menuTree");
		var nodes = treeObj.getCheckedNodes(true);
		var treeObj2 = $.fn.zTree.getZTreeObj("menuTree2");
		var tree2Nodes = treeObj2.transformToArray(treeObj2.getNodes());
		var tree2NodesNum = tree2Nodes.length+nodes.length;
		
		if(tree2NodesNum>8){
			alert("最多允许选择八项");						
		}else if(nodes.length<1){
			alert("请至少选择一项");
		}else if(tree2NodesNum<9&&nodes.length>0){
						
				for (var i=0,l=nodes.length; i<l; i++) {			
					//禁止选择父节点
					if(!nodes[i].isParent){
						for(var j=0;j<tree2Nodes.length;j++){
							var name1=nodes[i].name;
							var name2=tree2Nodes[j].name;
							if(name1==name2){
								alert("《"+name1+"》节点已存在");								
								//取消已经勾选的节点
								for(var m=0;m<nodes.length;m++){
									treeObj.checkNode(nodes[m], false);
								}							
								return;
							}
						}
						treeObj2.addNodes(null, nodes[i]);
					}else if(nodes[i].isParent){
						alert("对不起，不能选择父节点,请选择最底层的节点");								
						break;
					}				
		  		}
		}		
		//取消已经勾选的节点
		for(var j=0;j<nodes.length;j++){
			treeObj.checkNode(nodes[j], false);
		}
	});
	
	$("#tips").bind("click",function(){
		var tips="      选择您想要的项目作为您的快速入口，在项目前面打勾后，点击>>按钮后，项目将添加到右边的菜单中，系统还支持拖拽的方式进行"+"\n"+
		"      对已经选择的项目可以操作移动和<<按钮帮助您编辑快速入口，系统还支持以拖拽的方式进行"+"\n"+
		"      点击预览按钮能够帮助您快速预览左边快速入口您想要的效果。";
		alert(tips);
	});
	
	$("#moveup").bind("click",function(){		
		//获取选择节点可以设置只允许选择一个
		//获取上一个节点
		//使用move方法
		var treeObj2 = $.fn.zTree.getZTreeObj("menuTree2");
		var firstchild =treeObj2.getNodes()[0];
		var nodes = treeObj2.getSelectedNodes();
		if(nodes.length>1){
			alert("上移动作一次只能操作一个节点");
			return;
		}
		if(nodes[0]==firstchild){
			alert("第一个节点无法上移");
		}
		var preNode=nodes[0].getPreNode();
		treeObj2.moveNode(nodes[0], preNode, "next");
	});
	
	$("#movedown").bind("click",function(){		
		var treeObj2 = $.fn.zTree.getZTreeObj("menuTree2");
		var lastchild =treeObj2.getNodes()[treeObj2.getNodes().length-1];
		var nodes = treeObj2.getSelectedNodes();
		if(nodes.length>1){
			alert("下移动作一次只能操作一个节点");
			return;
		}
		if(nodes[0]==lastchild){
			alert("最后一个节点无法下移");
		}
		var nextNode=nodes[0].getNextNode();
		treeObj2.moveNode(nextNode, nodes[0], "next");
	});
	
	$("#delete").bind("click",function(){		
		var treeObj2 = $.fn.zTree.getZTreeObj("menuTree2");
		var nodes = treeObj2.getSelectedNodes();
		if(nodes.length<1){
			alert("请至少选择一项");
			return;
		}
		for (var i=0, l=nodes.length; i < l; i++) {
			treeObj2.removeNode(nodes[i]);
		}
	});
	
	$("#preshow").bind("click",function(){		
		preview2();	
	});
}); 

//页面默认参数
function init(){

	var code=resourceStyle.replaceAll("@","\"");
	$("#preview").html(code);
	
	$("#dtgl_homepage_right_table tr").each(function(){
		
		$(this).children('td').eq(0).css("float","left");
		$(this).children('td').eq(1).css("float","right");
	});
	
	$("#dtgl_homepage_right_table tr").eq(3).children('td').each(function(){
		$(this).css("margin-bottom","0");
		
	});
}

//初始化新树的节点
function initnodes(){
	var treeObj = $.fn.zTree.getZTreeObj("menuTree");
	var treeObj2 = $.fn.zTree.getZTreeObj("menuTree2");
	$("#preview td a").each(function(){
		var name=$(this).attr("title");	
		if(name!=null&&name!="自定义"){			
			var node = treeObj.getNodeByParam("name", name, null);	
			if(node!=null){
				treeObj2.addNodes(null, node);
			}		
		}		
	});
}

//操作TREE
//如果为父节点则不让其选择
function setTree(){
	var treeObj = $.fn.zTree.getZTreeObj("menuTree");	
	var nodes = treeObj.transformToArray(treeObj.getNodes());
	for(var i=0;i<nodes.length;i++){
		if(nodes[i].isParent==true){			
			treeObj.setChkDisabled(nodes[i], true);
		}
	}
}


String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {
    if (!RegExp.prototype.isPrototypeOf(reallyDo)) {
        return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);
    } else {
        return this.replace(reallyDo, replaceWith);
    }
};


//通过新树生成HTML
function preview2(){
	var treeObj2 = $.fn.zTree.getZTreeObj("menuTree2");	
	var nodes = treeObj2.transformToArray(treeObj2.getNodes());	
	var viewHtml="<table id=\"dtgl_homepage_right_table\">";
	for(var i=0;i<nodes.length;i++){
		if(i%2==0){
			viewHtml+="<tr>";
		}

		viewHtml+="<td><a title=\""+nodes[i].name+"\"><span>"+nodes[i].name+"</span></a></td>";
		if(i%2==1){
			viewHtml+="</tr>";
		}
	}
	
	if(nodes.length<8){
		
		for(var j=0;j<8-nodes.length;j++){
			if((nodes.length+j)%2==0){
				viewHtml+="<tr>";	
			}
			viewHtml+="<td><a><span>自定义</span></a></td>";		
			if((nodes.length+j)%2==1){
				viewHtml+="</tr>";	
			}
		}		
	}	
	viewHtml+="</table>";
	
	$("#preview").html(viewHtml);
	
	//重置Style
	$("#dtgl_homepage_right_table tr").each(function(){
		
		$(this).children('td').eq(0).css("float","left");
		$(this).children('td').eq(1).css("float","right");
	});
	
	$("#dtgl_homepage_right_table tr").eq(3).children('td').each(function(){
		$(this).css("margin-bottom","0");
		
	});
}
</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">	
	<div class="B-small-from-table-autoConcise">
      <h4 class="title">快速入口设置
      	<span class="pull-right">
      		<button id="preshow" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">预览</button>
      	    <button id="tips" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">操作说明</button>
      		<button id="btnClose" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">关闭</button>
      		<button id="btnSave" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">保存</button>	  		
      	</span>
      </h4>
      <div id="setting">
      <div id="preview"> 
      </div>
      
      <div id="menuTree" class="ztree">
      </div>
      
      <div id="movebutton">
      		<button id="moveright" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">>></button>
      		<button id="delete" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;"><<</button>
      		<button id="moveup" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">上移</button>     
      		<button id="movedown" class="btn" type="button" style="font-family:SimYou,Microsoft YaHei;font-weight:bold;">下移</button>
      		
      </div>
      
	  <div id="menuTree2" class="ztree">
	  </div>	  	  
      	
      </div>
    </div>
   </div>
  </div>

</body>

</html>
