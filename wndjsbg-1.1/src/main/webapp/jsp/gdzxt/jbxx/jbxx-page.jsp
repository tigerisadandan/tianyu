<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.params.SysPara.SysParaConfigureVO"%>
<%@ page import="com.ccthanking.framework.params.ParaManager"%>
<%@ page import="com.ccthanking.framework.Constants"%>
<%@ include file="/jsp/framework/common/taglibs.jsp" %>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<app:base />
<title>审批业务流转实例审批</title>
<%
String type=request.getParameter("type");
SysParaConfigureVO syspara  = (SysParaConfigureVO) ParaManager.getInstance().getSysParameter(Constants.getString(Constants.FILEUPLOADOLD_ROOT_JS, "FILEUPLOADOLD_ROOT_WNDJSJS"));
String fileRoot = syspara.getSysParaConfigureParavalue1();
String spyw_uid = request.getParameter("spyw_uid");
%>
<LINK type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/business/ywlz/css/default.css"> </LINK>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>	
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/kindeditor/plugins/code/prettify.css" />
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/kindeditor/kindeditor.js"></script>
<script charset="UTF-8" src="${pageContext.request.contextPath }/js/kindeditor/lang/zh_CN.js"></script>

<script type="text/javascript" charset="utf-8">
</script>      
</head>
<body>
<app:dialogs/>
<div class="container-fluid">
<div class="row-fluid">  
	<div class="B-small-from-table-autoConcise">
     <h4 class="title"><span id="ywlzmcid">项目信息</span>
      	<span class="pull-right">
			
      	</span>
      </h4>
	    <table border="1" width="100%" cellpadding="0" cellspacing="0" class="content">
			<tr>
				<td class="yw-title">
					<h4 ><span id="spyjtxhid">当前项目状态：在建</span>
								<span class="pull-right" > 
								<button id="btnAddYj" class="btn" type="button"> 组织管理</button>
								<button id="btnAddYj" class="btn" type="button"> 暂停考核    </button>
								<button id="btnAddYj" class="btn" type="button">   暂停考核记录</button>
								<button id="btnAddYj" class="btn" type="button">    完工</button>
								<button id="btnAddYj" class="btn" type="button">    竣工备案    
</button>
								</span>
								</h4>
				</td>
			</tr>
			<tr>
				<td>
					<ul id="ywcl_list">
						<table class="B-table" width="100%" >
      		<input type="hidden" id="AZ_COMPANY_UID" fieldname="AZ_COMPANY_UID" name = "AZ_COMPANY_UID"/>
      		
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">安监组</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="ZHIZHAO" type="text" readonly="readonly" fieldname="ZHENGSHU_CODE" name = "ZHENGSHU_CODE"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">安监人员</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input style="width:94%" id="ZHIZHAO_VALID" type="text" readonly="readonly" fieldname="ZZDJ_SV" name = "ZZDJ"  />
				</td>
	        </tr>
	         <tr>
				<th width="15%" class="right-border bottom-border text-right">质监组</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="ZHIZHAO" type="text" readonly="readonly" fieldname="AQSCXKZ" name = "AQSCXKZ"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">质监人员</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input class="span12" style="width:100%" id="ZHIZHAO" type="text" readonly="readonly" fieldname="AQSCXKZ" name = "AQSCXKZ"  />	     
				</td>
	        </tr>
      	</table>
					</ul>
				</td>
			</tr>
		</table>
		<div style="height: 5px;"></div>
		<table border="1" width="100%" cellpadding="0" cellspacing="0" class="content">
			<tr>
				<td class="yw-title">
					<h4 ><span id="spyjtxhid">施工手续审批情况 </span>
								<span class="pull-right" > 
								</span>
								</h4>
				</td>
			</tr>
			<tr>
				<td>
					<ul id="ywcl_list">
						<table class="B-table" width="100%" >
      		<input type="hidden" id="AZ_COMPANY_UID" fieldname="AZ_COMPANY_UID" name = "AZ_COMPANY_UID"/>
      		
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">安全质量监督申请</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="ZHIZHAO" type="text" readonly="readonly" fieldname="ZHENGSHU_CODE" name = "ZHENGSHU_CODE"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">建筑企业进区施工注册证</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input style="width:94%" id="ZHIZHAO_VALID" type="text" readonly="readonly" fieldname="ZZDJ_SV" name = "ZZDJ"  />
				</td>
	        </tr>
	         <tr>
				<th width="15%" class="right-border bottom-border text-right">安全监督受监通知书</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="ZHIZHAO" type="text" readonly="readonly" fieldname="AQSCXKZ" name = "AQSCXKZ"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">建设工程施工许可证</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input class="span12" style="width:100%" id="ZHIZHAO" type="text" readonly="readonly" fieldname="AQSCXKZ" name = "AQSCXKZ"  />	     
				</td>
	        </tr>
      	</table>
					</ul>
				</td>
			</tr>
		</table>
	
	
	  <table border="1" width="100%" cellpadding="0" cellspacing="0" class="content">
			<tr>
				<td class="yw-title">
					<h4 ><span id="spyjtxhid">建设单位信息 </span>
								<span class="pull-right" > 
								<button id="btnAddYj" class="btn" type="button">设置考勤人员    </button>
								<button id="btnAddYj" class="btn" type="button">  修改项目信息</button>
								</span>
								</h4>
				</td>
			</tr>
			<tr>
				<td>
					<ul id="ywcl_list">
						<table class="B-table" width="100%" >
      		<input type="hidden" id="AZ_COMPANY_UID" fieldname="AZ_COMPANY_UID" name = "AZ_COMPANY_UID"/>
      		
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">建设单位</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="ZHIZHAO" type="text" readonly="readonly" fieldname="ZHENGSHU_CODE" name = "ZHENGSHU_CODE"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">信用分值</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input style="width:94%" id="ZHIZHAO_VALID" type="text" readonly="readonly" fieldname="ZZDJ_SV" name = "ZZDJ"  />
				</td>
	        </tr>
	        <tr >
											<th width="8%" class="right-border bottom-border text-right">项目名称
											</th>
											<td width="92%" class="right-border bottom-border" colspan="2">
												<textarea class="span12" rows="4" id="CHULI_YIJIAN_ID"  fieldname="CHULI_YIJIAN" check-type="required" name="CHULI_YIJIAN"></textarea>						
											</td>
										</tr>
	         <tr>
				<th width="15%" class="right-border bottom-border text-right">勘察单位</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="ZHIZHAO" type="text" readonly="readonly" fieldname="AQSCXKZ" name = "AQSCXKZ"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">设计单位</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input class="span12" style="width:100%" id="ZHIZHAO" type="text" readonly="readonly" fieldname="AQSCXKZ" name = "AQSCXKZ"  />	     
				</td>
	        </tr>
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">开工时间</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="ZHIZHAO" type="text" readonly="readonly" fieldname="AQSCXKZ" name = "AQSCXKZ"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">竣工时间</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input class="span12" style="width:100%" id="ZHIZHAO" type="text" readonly="readonly" fieldname="AQSCXKZ" name = "AQSCXKZ"  />	     
				</td>
	        </tr>
	        
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">联系人</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="ZHIZHAO" type="text" readonly="readonly" fieldname="AQSCXKZ" name = "AQSCXKZ"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">联系电话</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input class="span12" style="width:100%" id="ZHIZHAO" type="text" readonly="readonly" fieldname="AQSCXKZ" name = "AQSCXKZ"  />	     
				</td>
	        </tr>
	        
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">项目造价</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="ZHIZHAO" type="text" readonly="readonly" fieldname="AQSCXKZ" name = "AQSCXKZ"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">建筑面积</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input class="span12" style="width:100%" id="ZHIZHAO" type="text" readonly="readonly" fieldname="AQSCXKZ" name = "AQSCXKZ"  />	     
				</td>
	        </tr>
	        
      	</table>
					</ul>
				</td>
			</tr>
		</table>
			<div style="height: 5px;"></div>	
	      		<div class="overFlowX">
			     	 <table border="1" width="100%" cellpadding="0" cellspacing="0" class="content">
			<tr>
				<td class="yw-title">
					<h4 ><span id="spyjtxhid">施工单位信息(总包)　20141650S001 </span>
								<span class="pull-right" > 
								<button id="btnAddYj" class="btn" type="button"> 施工动态考核    </button>   
								<button id="btnAddYj" class="btn" type="button"> 人员信息      </button>
								<button id="btnAddYj" class="btn" type="button"> 设置人脸登记状态    </button>
								</span>
								</h4>
				</td>
			</tr>
			<tr>
				<td>
					<ul id="ywcl_list">
						<table class="B-table" width="100%" >
      		<input type="hidden" id="AZ_COMPANY_UID" fieldname="AZ_COMPANY_UID" name = "AZ_COMPANY_UID"/>
      		
	        <tr>
				<th width="15%" class="right-border bottom-border text-right">施工单位</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="ZHIZHAO" type="text" readonly="readonly" fieldname="ZHENGSHU_CODE" name = "ZHENGSHU_CODE"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">信用分值</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input style="width:94%" id="ZHIZHAO_VALID" type="text" readonly="readonly" fieldname="ZZDJ_SV" name = "ZZDJ"  />
				</td>
	        </tr>
	         <tr>
				<th width="15%" class="right-border bottom-border text-right">信用分值</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="ZHIZHAO" type="text" readonly="readonly" fieldname="AQSCXKZ" name = "AQSCXKZ"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">资质类别等级</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input class="span12" style="width:100%" id="ZHIZHAO" type="text" readonly="readonly" fieldname="AQSCXKZ" name = "AQSCXKZ"  />	     
				</td>
	        </tr>
	         <tr>
				<th width="15%" class="right-border bottom-border text-right">项目经理</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="ZHIZHAO" type="text" readonly="readonly" fieldname="AQSCXKZ" name = "AQSCXKZ"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">信用分值</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input class="span12" style="width:100%" id="ZHIZHAO" type="text" readonly="readonly" fieldname="AQSCXKZ" name = "AQSCXKZ"  />	     
				</td>
	        </tr>
	         <tr>
				<th width="15%" class="right-border bottom-border text-right">安全生产许可证号</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="ZHIZHAO" type="text" readonly="readonly" fieldname="AQSCXKZ" name = "AQSCXKZ"  />	     	
	       	 	</td>
	        </tr>
	         <tr>
				<th width="15%" class="right-border bottom-border text-right">项目经理电话</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="ZHIZHAO" type="text" readonly="readonly" fieldname="AQSCXKZ" name = "AQSCXKZ"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">联系人</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input class="span12" style="width:100%" id="ZHIZHAO" type="text" readonly="readonly" fieldname="AQSCXKZ" name = "AQSCXKZ"  />	     
				</td>
	        </tr>
	         <tr>
				<th width="15%" class="right-border bottom-border text-right">项目经理证号</th>
	       	 	<td class="bottom-border right-border" style="width:32%">
	       	 	<input class="span12" style="width:100%" id="ZHIZHAO" type="text" readonly="readonly" fieldname="AQSCXKZ" name = "AQSCXKZ"  />	     	
	       	 	</td>
	       	 	<th width="15%" class="right-border bottom-border text-right">联系电话</th>
	       	 	<td colspan="3" class="bottom-border right-border">
					<input class="span12" style="width:100%" id="ZHIZHAO" type="text" readonly="readonly" fieldname="AQSCXKZ" name = "AQSCXKZ"  />	     
				</td>
	        </tr>
	        
      	</table>
					</ul>
				</td>
			</tr>
		</table>
					
					
			</form>
			
			     	
			</div>
			
			<div style="height: 5px;"></div>
			<div class="overFlowX" id="divclhflb">
					<table  id="divid" border="1" width="100%" cellpadding="0" cellspacing="0" class="content">
						<tr>
							<td class="yw-title" >
								<h4 ><span id="spyjtxhid">监理单位信息 </span>
								<span class="pull-right" > 
								<button id="btnAddYj" class="btn" type="button"> 设置监理部       </button>   
								<button id="btnAddYj" class="btn" type="button"> 人员信息      </button>    
								<button id="btnAddYj" class="btn" type="button">    设置绿色通道        </button>
								</span>
								</h4>
							</td>
						</tr>
						
					</table>
					 <form method="post" id="lzbzwjForm"  > 
					     <input type="hidden" id="lz_LZBZ_UID" fieldname="LZBZ_UID" name = "LZBZ_UID"/>
	  	                 <input type="hidden" id="lz_BZWJ_UID" fieldname="BZWJ_UID" name = "BZWJ_UID"/>
	  	                 <input type="hidden" id="lz_JC" fieldname="JC" name = "JC"/>
	  	                  <input type="hidden"  fieldname="ALPRINT" value="0" name = "ALPRINT"/>
	  	                 <input type="hidden" id="lz_CLMC" fieldname="CLMC" name = "CLMC"/>
	  	                 <input type="hidden" id="lz_SAVFILEPATHNAME" fieldname="SAVFILEPATHNAME" name = "SAVFILEPATHNAME"/>
					 </form>
					
				
			</div>
    </div>
	<input type="hidden" name="txtXML" id="wjCbfa">
   </div>
  </div>
  
 <jsp:include page="/jsp/file_upload/fileuploadold_config_forYwlz.jsp" flush="true" />
   <div align="center">

		
		<!-- 步骤文件  -->
		<FORM name="frmPostbzwj" method="post" style="display:none"
			target="_blank">
			<!--系统保留定义区域-->
			<input type="hidden" name="queryXML" id="queryXML"> 
			<input type="hidden" name="txtXML" id="txtXML">
			<input type="hidden" name="resultXML" id="resultXML">

			<!--传递行数据用的隐藏域-->
			<input type="hidden" name="rowData">
		</FORM>
		<!-- 步骤文件  31-->
		<FORM name="frmPost31" method="post" style="display:none"
			target="_blank">
			<!--系统保留定义区域-->
			<input type="hidden" name="queryXML" id="queryXML"> 
			<input type="hidden" name="txtXML" id="txtXML">
			<input type="hidden" name="resultXML" id="resultXML">

			<!--传递行数据用的隐藏域-->
			<input type="hidden" name="rowData">
		</FORM>
		<FORM name="frmPost" method="post" style="display:none"
			target="_blank">
			<!--系统保留定义区域-->
			<input type="hidden" name="queryXML" id="queryXML"> 
			<input type="hidden" name="txtXML" id="txtXML">
			<input type="hidden" name="resultXML" id="resultXML">

			<!--传递行数据用的隐藏域-->
			<input type="hidden" name="rowData">
		</FORM>
	</div>
</body>
<script>
</script>
</html>