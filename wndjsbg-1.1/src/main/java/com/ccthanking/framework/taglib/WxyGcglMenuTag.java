package com.ccthanking.framework.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import com.ccthanking.framework.common.MenuVo;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.coreapp.orgmanage.GdzxtMenuManager;
import com.ccthanking.framework.coreapp.orgmanage.WxyGcglMenuManager;

public class WxyGcglMenuTag extends TagSupport {

	public String getMenuTreeHtml() throws Exception {

		User user = RestContext.getCurrentUser();

		MenuVo[] menus_t = null;
		String menuHtml = "";
		menus_t = getAllowedMenus(user, "");
		if (menus_t != null && menus_t.length > 0) {
			for (int f = 0; f < menus_t.length; f++) {
				//第一层菜单不显示
				MenuVo[] menus = getAllowedMenus(user, menus_t[f].getName());
				if (menus != null && menus.length > 0) {
				for (int i = 0; i < menus.length; i++) {
					//------------------------------------------第一级菜单组成
					menuHtml += " <li id=\"li_" + menus[i].getName() + "\" class= > \r\n";//第一级菜单开始
					
					MenuVo[] menus_two = getAllowedMenus(user, menus[i].getName());
					//有子菜单的，则添加 <b class=\"arrow fa fa-angle-down\"></b> 否则不添加
					if(menus_two != null && menus_two.length > 0){
						menuHtml += "<a href=\"#\" class=\'dropdown-toggle\'  onclick=\"menutree_click('" + menus[i].getName() + "','"+ menus[i].getLocation() + "','" + menus[i].getTitle() + "','" + menus[i].getTarget()+ "')\" >";
						menuHtml += "<i class=\"fa fa-bars\"></i>&nbsp; <span class=\"menu-text\">" + menus[i].getTitle() + "</span> " ;
						menuHtml+= "<b class=\"arrow fa fa-angle-down\"></b></a><b class=\"arrow\"></b>\r\n";
					}else{	
						String location=menus[i].getLocation();
						String newStr = "jsp"+location.substring(4, location.length())+".jsp";
						menuHtml += "<a href=\"#\" onclick=\"menutree_click('" + menus[i].getName() + "','"
								+ newStr + "','" + menus[i].getTitle() + "','" + menus[i].getTarget()
								+ "')\">";
						menuHtml += "<i class=\"fa fa-bars\"></i> &nbsp;<span class=\"menu-text\">" + menus[i].getTitle() + "</span> " ;
						menuHtml+= "</a><b class=\"arrow\"></b>\r\n";
					}	
					// ----------------
					if (menus_two != null && menus_two.length > 0) {
						//------------------------------------------第二级菜单组成
						menuHtml += "<ul class=\"submenu\" >\r\n";// 第二级菜单开始
						for (int j = 0; j < menus_two.length; j++) {
							MenuVo[] menus_three = getAllowedMenus(user, menus_two[j].getName());		
							//有子菜单的，则添加 <b class=\"arrow fa fa-angle-down\"></b> 否则不添加
							if(menus_three != null && menus_three.length > 0){
								menuHtml += "<li > " +
										" <a href=\"javascript:void(0);\" class=\'dropdown-toggle\' " +
										" onclick=\"menutree_click('" 
											+ menus_two[j].getName() + "','"
											+ menus_two[j].getLocation() + "','" 
											+ menus_two[j].getTitle() + "','"
											+ menus_two[j].getTarget() + "')\">" +
										"<i class=\"menu-icon fa fa-caret-right\"></i>"+ menus_two[j].getTitle()+"<b class=\"arrow fa fa-angle-down\"></b></a>" +
										"<b class=\"arrow\"></b></li>\r\n";
							}else{
								menuHtml += "<li class> " +
										" <a href=\"wxy/gdzxt/wxy/wxy_frame#"+menus_two[j].getLocation()+"\" " +
										" data-url=\""+menus_two[j].getLocation()+"\" " +
										" onclick=\"menutree_click('" 
											+ menus_two[j].getName() + "','"
											+ menus_two[j].getLocation() + "','" 
											+ menus_two[j].getTitle() + "','"
											+ menus_two[j].getTarget() + "')\"  >" +
										" <i class=\"menu-icon fa fa-caret-right\"></i>"+ menus_two[j].getTitle()+"</a>" +
										" <b class=\"arrow\"></b></li>\r\n";
							}		

//							if (menus_three != null && menus_three.length > 0) {
//								menuHtml += " <ul class=\"submenu\" > \r\n";//第三级菜单开始
//								for (int k = 0; k < menus_three.length; k++) {
//									
//									MenuVo[] menus_four= getAllowedMenus(user, menus_three[k].getName());	
//									if(menus_four!=null&&menus_four.length>0){
//										menuHtml += " <li appName=\"" + menus[i].getParent() + "\"><a href=\"javascript:void(0);\" class=\'dropdown-toggle\'  onclick=\"menutree_click('"+ menus_three[k].getName() + "','" + menus_three[k].getLocation() + "','"+ menus_three[k].getTitle() + "','" + menus_three[k].getTarget() + "')\">" +
//												"<i class=\"menu-icon fa fa-caret-right\"></i>"+ menus_three[k].getTitle() ;										
//										menuHtml+= "<b class=\"arrow fa fa-angle-down\"></b></a><b class=\"arrow\"></b>\r\n";
//									}else{
//										menuHtml += " <li appName=\"" + menus[i].getParent() + "\"><a href=\"javascript:void(0);\"  onclick=\"menutree_click('"+ menus_three[k].getName() + "','" + menus_three[k].getLocation() + "','"+ menus_three[k].getTitle() + "','" + menus_three[k].getTarget() + "')\"  applocal=\""+menus_three[k].getLocation()+"\">" +
//												"<i class=\"menu-icon fa fa-caret-right\"></i>"+ menus_three[k].getTitle() ;								
//										menuHtml+= "</a><b class=\"arrow\"></b></li>\r\n";
//									}
//									
//									if(menus_four!=null&&menus_four.length>0){
//										menuHtml += " <ul class=\"submenu\" >\r\n";//第四级菜单开始
//										for(int f=0;f<menus_four.length;f++){
//											menuHtml += " <li appName=\"" + menus[i].getParent() + "\"><a href=\"javascript:void(0);\"  onclick=\"menutree_click('"+menus_four[f].getName() + "','" + menus_four[f].getLocation() + "','"+ menus_four[f].getTitle() + "','" + menus_four[f].getTarget() + "')\">" +
//													"<b class=\"arrow fa fa-angle-down\"></b>"+menus_four[f].getTitle()+  "</a><b class=\"arrow\"></b></li>\r\n";
//										}
//										menuHtml += "</ul></li>\r\n";	//第四级菜单结束								
//									}
//								}
//
//								menuHtml += "</ul></li>\r\n";//第三级菜单结束
//
//							}
						}
						menuHtml += "</ul>\r\n";// 第二级菜单结束
					}
					menuHtml += "</li>\r\n";//第一级菜单结束
				}
				}
			}
		}
//		System.out.println(menuTreeHtml);
		return menuHtml;
	}



	protected MenuVo[] getAllowedMenus(User user, String parent) throws Exception {
		// 超级用户，看见系统中所有菜单
		if (User.SUPER_USER.equals(user.getPersonKind())) {
			return WxyGcglMenuManager.getInstance().getMenus(parent);
		} else {
//			return user.getAllowedMenus(parent);
			return WxyGcglMenuManager.getInstance().getMenus(parent);
		}
	}

	public int doEndTag() throws JspTagException {
		try {
			// 获取页面输出流，并输出字符串
//			String appPath = ((HttpServletRequest) pageContext.getRequest()).getContextPath();
//			String base = "";
			pageContext.getOut().write(getMenuTreeHtml());// + doYwMenu());
		}
		// 捕捉异常
		catch (IOException ex) {
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 值回返
		return EVAL_PAGE;
	}

	
}
