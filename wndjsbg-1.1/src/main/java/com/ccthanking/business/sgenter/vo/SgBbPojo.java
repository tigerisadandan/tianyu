/*
 * SgBbPojo.java  v1.00  2014-4-21
 * Peoject	wndjssg
 * Copyright (c) 2014 Wisdragon
 *
 * Filename	:	SgBbPojo.java  v1.00 2014-4-21
 * Project	: 	wndjssg
 * Copyight	:	Copyright (c) 2014 Wisdragon
 */
package com.ccthanking.business.sgenter.vo;

/**
 * 施工报备规则pojo.
 * 
 * @author <a href="mailto:genliang.jiang@wisdragon.com">蒋根亮</a>
 * @version v1.00
 * @since 1.00 2014-4-21
 * 
 */
public class SgBbPojo {

	private boolean enabled;// 是否通过

	private int cblx;// 承包类型
	private int qybh;// 企业编号
	private int gclb;// 工程类别
	private int gclx;// 工程类型 = 项目名称
	private String qyzzpdbz;// 企业资质判断标准
	private String dyqyzz;// 对应企业资质
	private int zzid;// 资质编号
	private String zzidstr;// 资质编号串 对应于一种工程多资质情况
	private int zzdj;// 资质等级

	private int jzwcs;// 建筑物层数
	private float dwkd;// 单跨跨度
	private float dwjzmj;// 单体建筑面积
	private float jzqjzmj;// 建筑群建筑面积

	private float gjgkd;// 钢结构跨度
	private float zzl;// 总重量
	private long qyzczb;// 企业注册资本

	private long dxgchte;// 单项工程合同额
	private float jzmj;// 建筑面积

	private float dxgcmj;// 单项工程面积
	private float dxgcgd;// 单项工程高度
	private float jzwgd;// 建筑物高度

	private int zdpbrsnum;// 最低配备人数
	private String zdpbrs;// 最低配备人数String
	private String zdpbrszj;// 最低配备人数增加String
	private int zdpbrszjfs;// 最低配备人数增加NO

	private int jzszygcgmbz;// 建筑师执业工程规模标准

	public float getDwkd() {
		return dwkd;
	}

	public void setDwkd(float dwkd) {
		this.dwkd = dwkd;
	}

	public int getGclb() {
		return gclb;
	}

	public void setGclb(int gclb) {
		this.gclb = gclb;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getQybh() {
		return qybh;
	}

	public int getZdpbrszjfs() {
		return zdpbrszjfs;
	}

	public void setZdpbrszjfs(int zdpbrszjfs) {
		this.zdpbrszjfs = zdpbrszjfs;
	}

	public void setQybh(int qybh) {
		this.qybh = qybh;
	}

	public String getQyzzpdbz() {
		return qyzzpdbz;
	}

	public void setQyzzpdbz(String qyzzpdbz) {
		this.qyzzpdbz = qyzzpdbz;
	}

	public int getCblx() {
		return cblx;
	}

	public void setCblx(int cblx) {
		this.cblx = cblx;
	}

	public int getGclx() {
		return gclx;
	}

	public int getZzid() {
		return zzid;
	}

	public void setZzid(int zzid) {
		this.zzid = zzid;
	}

	public void setGclx(int gclx) {
		this.gclx = gclx;
	}

	public String getDyqyzz() {
		return dyqyzz;
	}

	public void setDyqyzz(String dyqyzz) {
		this.dyqyzz = dyqyzz;
	}

	public int getZzdj() {
		return zzdj;
	}

	public void setZzdj(int zzdj) {
		this.zzdj = zzdj;
	}

	public int getJzwcs() {
		return jzwcs;
	}

	public void setJzwcs(int jzwcs) {
		this.jzwcs = jzwcs;
	}

	public float getDwjzmj() {
		return dwjzmj;
	}

	public void setDwjzmj(float dwjzmj) {
		this.dwjzmj = dwjzmj;
	}

	public float getJzqjzmj() {
		return jzqjzmj;
	}

	public void setJzqjzmj(float jzqjzmj) {
		this.jzqjzmj = jzqjzmj;
	}

	public float getGjgkd() {
		return gjgkd;
	}

	public void setGjgkd(float gjgkd) {
		this.gjgkd = gjgkd;
	}

	public float getZzl() {
		return zzl;
	}

	public void setZzl(float zzl) {
		this.zzl = zzl;
	}

	public long getQyzczb() {
		return qyzczb;
	}

	public void setQyzczb(long qyzczb) {
		this.qyzczb = qyzczb;
	}

	public long getDxgchte() {
		return dxgchte;
	}

	public void setDxgchte(long dxgchte) {
		this.dxgchte = dxgchte;
	}

	public float getJzmj() {
		return jzmj;
	}

	public void setJzmj(float jzmj) {
		this.jzmj = jzmj;
	}

	public float getDxgcmj() {
		return dxgcmj;
	}

	public void setDxgcmj(float dxgcmj) {
		this.dxgcmj = dxgcmj;
	}

	public float getDxgcgd() {
		return dxgcgd;
	}

	public void setDxgcgd(float dxgcgd) {
		this.dxgcgd = dxgcgd;
	}

	public float getJzwgd() {
		return jzwgd;
	}

	public void setJzwgd(float jzwgd) {
		this.jzwgd = jzwgd;
	}

	public int getZdpbrsnum() {
		return zdpbrsnum;
	}

	public void setZdpbrsnum(int zdpbrsnum) {
		this.zdpbrsnum = zdpbrsnum;
	}

	public String getZdpbrs() {
		return zdpbrs;
	}

	public void setZdpbrs(String zdpbrs) {
		this.zdpbrs = zdpbrs;
	}

	public int getJzszygcgmbz() {
		return jzszygcgmbz;
	}

	public void setJzszygcgmbz(int jzszygcgmbz) {
		this.jzszygcgmbz = jzszygcgmbz;
	}

	public String getZzidstr() {
		return zzidstr;
	}

	public void setZzidstr(String zzidstr) {
		this.zzidstr = zzidstr;
	}

	public String getZdpbrszj() {
		return zdpbrszj;
	}

	public void setZdpbrszj(String zdpbrszj) {
		this.zdpbrszj = zdpbrszj;
	}

}
