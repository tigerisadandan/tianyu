package com.ccthanking.framework.taglib;

import java.io.IOException;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;


import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.MenuVo;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.coreapp.orgmanage.MenuManager;
import com.ccthanking.framework.util.MenuComparator;
import com.visural.common.StringUtil;

public class MenuSubTag extends TagSupport {

	public String getMenuTreeHtml() throws Exception {

		User user = RestContext.getCurrentUser();

		MenuVo[] menus_t = null;
		String menuTreeHtml = "";
		menus_t = getAllowedMenus(user, "");// 显示第一层菜单
		if (menus_t != null && menus_t.length > 0) {
			for (int f = 0; f < menus_t.length; f++) {
				String menusname=menus_t[f].getName();
				MenuVo[] menus = getAllowedMenus(user, menus_t[f].getName());
				for (int i = 0; i < menus.length; i++) {
					String icss = menus[i].getImage();
					if (icss == null || "".equalsIgnoreCase(icss)) {
						icss = "icon-briefcase";
					}
					
					menuTreeHtml += " <li level=\"2\" id=\""+menus[i].getName()+"\" lType=\"out\" appName=\"" + menus[i].getParent() + "\"> ";
					if (!"#".equals(menus[i].getLocation())) {
						menuTreeHtml += "<a href=\"#\"onclick=\"menutree_click('" + menus[i].getName() + "','"
								+ menus[i].getLocation() + "','" + menus[i].getTitle() + "','" + menus[i].getTarget()
								+ "')\">";
					} else {
						menuTreeHtml += "<a href=\"#\">";
					}
					menuTreeHtml += " <i class=\"" + icss + " icon-white\"></i>  <span class=\"title\">"
							+ menus[i].getTitle() + "</span> </a>\r\n";

					MenuVo[] menus_two = getAllowedMenus(user, menus[i].getName());
					// ----------------
					if (menus_two != null && menus_two.length > 0) {
						menuTreeHtml += "<ul class=\"sub-menu\" >\r\n";// style=\"height:400px;display:;overflow:auto;\"
						for (int j = 0; j < menus_two.length; j++) {
							// menuTreeHtml
							// +="<h4><a href=\"#\" onclick=\"menutree_click('"+menus_two[j].getName()+"','"+menus_two[j].getLocation()+"','"+menus_two[j].getTitle()+"','"+menus_two[j].getTarget()+"')\">"+menus_two[j].getTitle()+"</a></h4>\r\n";
							menuTreeHtml += "<li level=\"3\" p_name=\""+menus[i].getName()+"\"  id=\""+menus_two[j].getName()+"\"  appName=\"" + menus[i].getParent()
									+ "\"> <a style=\"padding-right:20px\" id=\"a_"+menus_two[j].getName()+"\" href=\"#\"onclick=\"menutree_click('" + menus_two[j].getName() + "','"
									+ menus_two[j].getLocation() + "','" + menus_two[j].getTitle() + "','"
									+ menus_two[j].getTarget() + "')\">" + menus_two[j].getTitle() + "</a>\r\n";

							MenuVo[] menus_three = getAllowedMenus(user, menus_two[j].getName());
							if (menus_three != null && menus_three.length > 0) {
								menuTreeHtml += " <ul class=\"sub-menu\" style=\"display:;overflow:auto;\">\r\n";
								for (int k = 0; k < menus_three.length; k++) {
									menuTreeHtml += " <li level=\"4\" p_name=\""+menus_two[j].getName()+"\" g_name=\""+menus[i].getName()+"\"  id=\""+menus_three[k].getName()+"\"   appName=\"" + menus[i].getParent()
											+ "\"><a href=\"javascript:void(0);\" onclick=\"menutree_click('"
											+ menus_three[k].getName() + "','" + menus_three[k].getLocation() + "','"
											+ menus_three[k].getTitle() + "','" + menus_three[k].getTarget() + "')\">"
											+ menus_three[k].getTitle() + "</a></li>\r\n";
								}

								menuTreeHtml += "</ul>\r\n";

							} else if (menus_three == null || menus_three.length == 0) {

								menuTreeHtml += "<div></div>\r\n";
							}

						}
						menuTreeHtml += "</ul>\r\n";
					}
					menuTreeHtml += "</li>\r\n";
				}
			}
		}
		System.out.println(menuTreeHtml);
		return menuTreeHtml;
	}

	protected MenuVo[] getAllowedMenus(User user, String parent) throws Exception {
		// 超级用户，看见系统中所有菜单
		if (User.SUPER_USER.equals(user.getPersonKind())) {
			return MenuManager.getInstance().getMenus(parent);
		} else {
			MenuVo[] menus =  user.getAllowedMenus(parent);
			List<MenuVo> temp = Arrays.asList(menus);
			//排序
			if(temp!=null){
				Comparator comp = new MenuComparator();
				Collections.sort(temp, comp);
			}
			return (MenuVo[]) temp.toArray(new MenuVo[temp.size()]);
		}
	}

	public int doEndTag() throws JspTagException {
		try {
			// 获取页面输出流，并输出字符串
			String appPath = ((HttpServletRequest) pageContext.getRequest()).getContextPath();
			String base = "";
			base += "";
		//	pageContext.getOut().write(getMenuTreeHtml()+doYwMenu()); +doYwMenu() 业务菜单 
			pageContext.getOut().write(getMenuTreeHtml());
		}
		// 捕捉异常
		catch (IOException ex) {
			// 抛出新异常
			// throw new JspTagException("错误"};
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 值回返
		return EVAL_PAGE;
	}
	
	
	private String doYwMenu(){
		String menuTreeHtml = "";

		MenuVo[] menus = getYwxxMenu("");
		if(menus!=null&&menus.length>0){
			for (int i = 0; i < menus.length; i++) {
				String icss = menus[i].getImage();
				if (icss == null || "".equalsIgnoreCase(icss)) {
					icss = "icon-briefcase";
				}
				// menuTreeHtml
				// +="<h3><i class=\"icon-briefcase icon-white\"></i>"+menus[i].getTitle()+"</h3>\r\n";
				menuTreeHtml += " <li lType=\"out\" appName=\"" + menus[i].getParent() + "\"> ";
				if (!"#".equals(menus[i].getLocation())) {
					menuTreeHtml += "<a href=\"#\"onclick=\"menutree_click('" + menus[i].getName() + "','"
							+ menus[i].getLocation() + "','" + menus[i].getTitle() + "','" + menus[i].getTarget()
							+ "')\">";
				} else {
					menuTreeHtml += "<a href=\"#\">";
				} 
				menuTreeHtml += " <i class=\"" + icss + " icon-white\"></i>  <span class=\"title\">"
						+ menus[i].getTitle() + "</span><h id=\""+menus[i].getName()+"\"><font color=\"red\"></font></h> </a>\r\n";

				MenuVo[] menus_two = getYwxxMenu(menus[i].getName());
				// ----------------
				if (menus_two != null && menus_two.length > 0) {
					menuTreeHtml += "<ul class=\"sub-menu\" >\r\n";// style=\"height:400px;display:;overflow:auto;\"
					for (int j = 0; j < menus_two.length; j++) {
						
						String twotitle=menus_two[j].getTitle();
						if(StringUtil.isNotBlankStr(twotitle)&&twotitle.length()>10){
							twotitle=twotitle.substring(0, 10);						
						}
						String twocountds=menus_two[j].getOrderNo(); 
						menuTreeHtml += "<li appName=\"" + menus[i].getParent()
								+ "\"> <a href=\"#\" title=\""+menus_two[j].getTitle()+"\"  onclick=\"menutree_click('" + menus_two[j].getName() + "','"
								+ menus_two[j].getLocation() + "','" + menus_two[j].getTitle() + "','"
								+ menus_two[j].getTarget() + "')\">" + twotitle+"<h id=\""+menus_two[j].getName()+"\"><font color=\"red\"></font></h></a>\r\n";
			
						MenuVo[] menus_three = getYwxxMenu(menus_two[j].getName());
						if (menus_three != null && menus_three.length > 0) {
							menuTreeHtml += " <ul class=\"sub-menu\" style=\"display:;overflow:auto;\">\r\n";
							for (int k = 0; k < menus_three.length; k++) {
								
								String threetitle=menus_three[k].getTitle();
								if(StringUtil.isNotBlankStr(threetitle)&&threetitle.length()>10){
									threetitle=threetitle.substring(0, 10);						
								}
								String threecountds=menus_three[k].getOrderNo();
								
								menuTreeHtml += " <li appName=\"" + menus[i].getParent()
										+ "\"><a href=\"javascript:void(0);\"  title=\""+menus_three[k].getTitle()+"\"  onclick=\"menutree_click('"
										+ menus_three[k].getName() + "','" + menus_three[k].getLocation() + "','"
										+ menus_three[k].getTitle() + "','" + menus_three[k].getTarget() + "')\">"
										+ threetitle+"<h id=\""+menus_three[k].getName()+"\"><font color=\"red\"></font></h></a></i>\r\n";
							}

							menuTreeHtml += "</ul>\r\n";

						} else if (menus_three == null || menus_three.length == 0) {

							menuTreeHtml += "<div></div>\r\n";
						}
						

					}
					menuTreeHtml += "</ul>\r\n";
				}
				menuTreeHtml += "</li>\r\n";
			}
			
		}
		System.out.println(menuTreeHtml);
		return menuTreeHtml;
	}
	
	public MenuVo[] getYwxxMenu(String p_yw_uid){
		MenuVo[] menus = null;
//		List<MenuVo> mus = new ArrayList<MenuVo>();
		Connection conn = DBUtil.getConnection();
		try {
			String sql = "select spyw_uid, p_spyw_uid, spywmc, spywlx, "
			+"yzmc, sfzlc, menuid, clurl, multi_y, event_uid, enabled,"
		//	+" (select count(lz.ywlz_uid) from bu_sp_ywlz lz where lz.status='0' and lz.spyw_uid= y.spyw_uid) as countds,"
			+ " serial_no, sxlb from bu_sp_ywxx y ";
			if(StringUtils.isNotBlank(p_yw_uid)){
				sql+= " where p_spyw_uid = "+p_yw_uid;
			}else{
				sql+= " where p_spyw_uid is null";
			}
			sql+=" order by serial_no asc, spywlx ";
			
			String[][] list = DBUtil.query(conn, sql); 
			if (list!=null) {
				menus = new MenuVo[list.length];
				for (int i = 0; i < list.length; i++) {					
					MenuVo menu = new MenuVo();
					menu.setName(list[i][0]);
					menu.setTitle(list[i][2]);
					menu.setParent(StringUtils.isNotBlank(p_yw_uid)?list[i][1]:"sxsp");
					// add by wuxp
					menu.setOrderNo(list[i][11]);
					// add by wuxp
					menu.setTarget("pagearea");
					menu.setLocation("ywlzSpIndex/"+list[i][0]);
					
					menu.setLayersno(list[i][6]);
					menu.setChief(list[i][9]);
//					menu.setLevelno(list[i][11]);
					menus[i] = menu;
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return menus;
	}

}
