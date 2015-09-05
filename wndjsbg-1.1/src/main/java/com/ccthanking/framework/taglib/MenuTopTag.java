package com.ccthanking.framework.taglib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

import com.ccthanking.framework.Globals;
import com.ccthanking.framework.common.MenuVo;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.rest.handle.servlet.RestContext;
import com.ccthanking.framework.coreapp.orgmanage.MenuManager;
import com.ccthanking.framework.util.MenuComparator;

/**
 * 项层菜单显示.
 * 
 * @author <a href="mailto:genliang.jiang@wisdragon.com">蒋根亮</a>
 * @version v1.00
 * @since 1.00 2014-5-27
 * 
 */
public class MenuTopTag extends TagSupport {

	private static String contextPath = null;

	// 回调js函数
	private String jsfunc;

	public String getMenuTreeHtml() throws Exception {

		User user = RestContext.getCurrentUser();

		MenuVo[] menus = null;
		StringBuilder menuTreeHtml = new StringBuilder();
		menus = getAllowedMenus(user, "");// 显示第一层菜单
		if (menus != null && menus.length > 0) {
			for (int i = 0; i < menus.length; i++) {

				menuTreeHtml.append("<div class=\"Nav text-center DefaultLink\">");
				menuTreeHtml.append("<a href=\"javascript:void(0)\" onclick=\"" + jsfunc + "('" + menus[i].getName()
						+ "')\"><img class=\"DefaultLinkImg\" src=\"" + contextPath + menus[i].getImage() + "\" alt=\""
						+ menus[i].getTitle() + "\"><br>" + menus[i].getTitle() + "</a>");
				menuTreeHtml.append("</div>\n");

			}
		}
		System.out.println(menuTreeHtml);
		return menuTreeHtml.toString();
	}

	protected MenuVo[] getAllowedMenus(User user, String parent) throws Exception {
		// 超级用户，看见系统中所有菜单
		if (User.SUPER_USER.equals(user.getPersonKind())) {
			return MenuManager.getInstance().getMenusTop();
		} else {
			// 获取非超级用户的
			MenuVo[] temp = user.getAllowedMenus(parent);
			
			List<MenuVo> list = new ArrayList<MenuVo>();
			for (MenuVo menuVo : temp) {
				rollTopMenu(list, menuVo);
			}
			//排序
			if(temp!=null){
				Comparator comp = new MenuComparator();
				Collections.sort(list, comp);
			}
			return list.toArray(new MenuVo[list.size()]);
		}
	}

	// 查询第一级菜单.
	private void rollTopMenu(List<MenuVo> list, MenuVo menuVo) throws Exception {
		if ("1".equals(menuVo.getLevelno())) {
			if (!list.contains(menuVo))
				list.add(menuVo);
		} else if (menuVo.getLevelno() != null && Integer.parseInt(menuVo.getLevelno()) > 1
				&& StringUtils.isNotBlank(menuVo.getParent())) {
			menuVo = MenuManager.getInstance().getMenu(menuVo.getParent());
			if (!list.contains(menuVo)) {
				rollTopMenu(list, menuVo);
			}
		}
	}

	public int doEndTag() throws JspTagException {
		try {
			// 获取页面输出流，并输出字符串
			if (contextPath == null) {
				contextPath = ((HttpServletRequest) pageContext.getRequest()).getContextPath();
			}
			// String appPath = ((HttpServletRequest)
			// pageContext.getRequest()).getContextPath();
			// String base = "";
			// base += "";
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

	public void setJsfunc(String jsfunc) {
		this.jsfunc = jsfunc;
	}

}
