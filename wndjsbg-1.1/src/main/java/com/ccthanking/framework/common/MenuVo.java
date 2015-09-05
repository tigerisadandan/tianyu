package com.ccthanking.framework.common;

public class MenuVo implements java.io.Serializable,Comparable<MenuVo>{

	private String title;
	private String target;
	private String description;
	private String name;
	private String layersno;
	private String image;
	private String altimage;
	private String orderno;
	private String tooltip;
	private String page;
	private String chief;
	private String parent;
	private String location;
	private String appName;
	private String levelno;
	private int aaa;

	private Menu[] child;

	public Menu[] getChild() {
		return child;
	}

	public void setChild(Menu[] child) {
		this.child = child;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAbsoluteLocation() {
		return this.getLocation();
	}

	public String getFakeLocation() {
		return this.getLocation();
	}

	public String getTarget() {
		return this.target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLayersno() {
		return this.layersno;
	}

	public void setLayersno(String layersno) {
		this.layersno = layersno;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAltImage() {
		return this.altimage;
	}

	public void setAltImage(String altimage) {
		this.altimage = altimage;
	}

	public void setOrderNo(String orderNo) {
		this.orderno = orderNo;
	}

	public String getOrderNo() {
		return this.orderno;
	}

	public String getToolTip() {
		return this.tooltip;
	}

	public void setToolTip(String toolTip) {
		this.tooltip = toolTip;
	}

	public String getPage() {
		return this.page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getChief() {
		return this.chief;
	}

	public void setChief(String chief) {
		this.chief = chief;
	}

	public String getParent() {
		return this.parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getLevelno() {
		return levelno;
	}

	public void setLevelno(String levelno) {
		this.levelno = levelno;
	}

	public int compareTo(MenuVo o) {
		Integer i1 = new Integer(this.orderno);
		Integer i2 = new Integer(o.getOrderNo());
		return i1.compareTo(i2);
	}

}