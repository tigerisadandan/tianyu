package com.ccthanking.framework.coreapp.orgmanage;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ccthanking.framework.Constants;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.MenuVo;
import com.ccthanking.framework.common.Role;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.common.UsersVO;
import com.ccthanking.framework.common.cache.Cache;
import com.ccthanking.framework.common.cache.CacheManager;
import com.ccthanking.framework.util.MenuComparator;
import com.ccthanking.framework.util.Pub;
import com.copj.modules.utils.exception.DaoException;

public class MenuManager implements Cache {

	private static Logger logger = LoggerFactory.getLogger(MenuManager.class
			.getName());

	private Hashtable menutable;// 全部节点树 map<parent,List<MenuVo>>
	private Hashtable menuarr;// 全部菜单 map<name,MenuVo>

	private static MenuManager instance;

	private static final String ROOT_MENU = "ROOT_MENU_85183815";

	private MenuManager() throws Exception {

		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String querySql = "select name,title,parent,orderno,target,location,layersno,image,altimage,chief,app_name, levelno "
					+ " from FS_eap_menu where app_name='"
					+ Constants.APP_NAME
					+ "' AND sfyx='1' AND MENU_Y='Y' order by parent,orderno ";
			String[][] list = DBUtil.query(conn, querySql);
			if (list != null) {
				menutable = new Hashtable();
				menuarr = new Hashtable(list.length);
				for (int i = 0; i < list.length; i++) {
					MenuVo menu = new MenuVo();
					menu.setName(list[i][0]);
					menu.setTitle(list[i][1]);
					menu.setParent(list[i][2]);
					// add by wuxp
					if (list[i][3] == null || list[i][3].trim().length() == 0) {
						menu.setOrderNo("0");
					} else {
						menu.setOrderNo(list[i][3]);
					}
					// add by wuxp
					menu.setTarget(list[i][4]);
					menu.setLocation(list[i][5]);
					menu.setLayersno(list[i][6]);
					menu.setImage(list[i][7]);
					menu.setAltImage(list[i][8]);
					menu.setChief(list[i][9]);
					menu.setAppName(list[i][10]);
					menu.setLevelno(list[i][11]);
					menuarr.put(menu.getName(), menu);

					// 组织树map<key, List>
					if (menutable.get(menu.getParent()) != null) {
						ArrayList mlist = (ArrayList) menutable.get(menu
								.getParent());
						mlist.add(menu);
					} else {
						if (!Pub.empty(menu.getParent())) {
							ArrayList mlist = (ArrayList) menutable.get(menu
									.getParent());
							if (mlist == null)
								mlist = new ArrayList();
							mlist.add(menu);
							menutable.put(menu.getParent(), mlist);
						} else {
							// 根节点菜单设置默认的key
							ArrayList mlist = (ArrayList) menutable
									.get(this.ROOT_MENU);
							if (mlist == null)
								mlist = new ArrayList();
							mlist.add(menu);
							menutable.put(this.ROOT_MENU, mlist);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
		} finally {
			DBUtil.closeConnetion(conn);
		}
	}

	/**
	 * 获取本系统的第一层所有菜单.<br/>
	 * 
	 * @return
	 * @since v1.00
	 */
	public MenuVo[] getMenusTop() {

		Connection conn = null;
		try {
			conn = DBUtil.getConnection();

			String querySql = "SELECT fem.\"NAME\"  FROM fs_eap_menu fem WHERE fem.app_name='"
					+ Constants.APP_NAME
					+ "' AND fem.sfyx='1' AND fem.levelno=1 ORDER BY fem.orderno ";

			String[][] list = DBUtil.query(conn, querySql);
			if (list != null) {
				MenuVo[] re_menu = new MenuVo[list.length];
				for (int i = 0; i < list.length; i++) {
					String menuname = list[i][0];
					MenuVo menu = (MenuVo) menuarr.get(menuname);
					re_menu[i] = menu;
				}
				return re_menu;
			}

		} catch (Exception e) {
			DaoException.handleException("***查询顶层菜单出错!***");
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return null;
	}

	public MenuVo[] getMenus(String parent) throws Exception {
		try {
			if (Pub.empty(parent))
				return (MenuVo[]) ((ArrayList) menutable.get(this.ROOT_MENU))
						.toArray(new MenuVo[((ArrayList) menutable
								.get(this.ROOT_MENU)).size()]);
			else
				return (MenuVo[]) ((ArrayList) menutable.get(parent))
						.toArray(new MenuVo[((ArrayList) menutable.get(parent))
								.size()]);
		} catch (Exception e) {
			return null;
		}

	}

	public MenuVo getMenu(String name) throws Exception {
		if (Pub.empty(name))
			return null;
		return (MenuVo) menuarr.get(name);
	}

	public MenuVo[] getAllMenus() throws Exception {
		MenuVo[] menus = new MenuVo[menuarr.values().size()];
		return (MenuVo[]) menuarr.values().toArray(menus);
	}

	public MenuVo[] getAllowedMenus(User user) throws Exception {
		Role[] roles = user.getRoles();// 获取用户的角色
		ArrayList ml = null;
		if (roles != null) {
			for (int i = 0; i < roles.length; i++) {
				MenuVo[] menus = roles[i].getMenus();
				if (menus != null) {
					if (ml == null)
						ml = new ArrayList();
					for (int j = 0; j < menus.length; j++) {
						if (ml.contains(menus[j]))
							continue;
						ml.add(menus[j]);
					}
				}
			}
		}
		MenuVo[] menus1 = UsersVO.getMenus(user.getUserSN());// 用户所拥有的权限
		if (menus1 != null) {
			for (int i = 0; i < menus1.length; i++) {
				if (ml == null)
					ml = new ArrayList();
				if (ml.contains(menus1[i]))
					continue;
				ml.add(menus1[i]);
			}
			// yum 增加菜单按OrderNo排序
			if (ml != null) {
				Comparator comp = new MenuComparator();
				Collections.sort(ml, comp);
			}
			// Arrays.sort(ml.toArray());
		}

		if (ml == null) {
			return null;
		} else
			return (MenuVo[]) ml.toArray(new MenuVo[ml.size()]);
	}

	public MenuVo[] getAllowedMenus(User user, String parent) throws Exception {
		Role[] roles = user.getRoles();
		ArrayList ml = null;
		if (roles != null) {
			for (int i = 0; i < roles.length; i++) {
				MenuVo[] menus = roles[i].getMenus();
				if (menus != null) {
					if (ml == null)
						ml = new ArrayList();
					for (int j = 0; j < menus.length; j++) {
						if (ml.contains(menus[j]))
							continue;
						if (Pub.empty(menus[j].getParent())
								&& Pub.empty(parent))
							ml.add(menus[j]);
						else if (!Pub.empty(parent)
								&& parent.equals(menus[j].getParent()))
							ml.add(menus[j]);
					}
				}
			}
		}
		MenuVo[] menus1 = UsersVO.getMenus(user.getUserSN());// 用户所拥有的权限
		if (menus1 != null) {
			for (int i = 0; i < menus1.length; i++) {
				if (ml == null)
					ml = new ArrayList();
				if (ml.contains(menus1[i]))
					continue;
				ml.add(menus1[i]);
			}
		}
		return (MenuVo[]) ml.toArray(new MenuVo[ml.size()]);
	}

	public synchronized static MenuManager getInstance() throws Exception {
		if (instance == null) {
			instance = new MenuManager();
			logger.debug("--------------- 初始化 menu ------------------");
			CacheManager.register(CacheManager.CACHE_MENU, instance);
		}
		return instance;
	}

	public void reBuildMemory() throws Exception {
		if (menutable != null) {
			menutable.clear();
			menutable = null;
		}
		if (menuarr != null) {
			menuarr.clear();
			menuarr = null;
		}
		if (instance != null)
			instance = null;
		if (instance == null) {
			instance = new MenuManager();
		}
	}

	public void synchronize(String data, int action) throws Exception {
		if (menutable != null) {
			menutable.clear();
			menutable = null;
		}
		if (menuarr != null) {
			menuarr.clear();
			menuarr = null;
		}
		if (instance != null)
			instance = null;
		if (instance == null) {
			instance = new MenuManager();
		}
	}

}