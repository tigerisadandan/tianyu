/*
 * PointRuleEngine.java  v1.00  2013-11-27
 * Peoject	copj-drools-test
 * Copyright (c) 2013 KcpmIT
 *
 * Filename	:	PointRuleEngine.java  v1.00 2013-11-27
 * Project	: 	copj-drools-test
 * Copyight	:	Copyright (c) 2013 KcpmIT
 */
package com.ccthanking.framework.common.rule.drools;

import com.ccthanking.business.sgenter.vo.SgBbPojo;

/**
 * 
 * 规则接口
 * 
 */

public interface RuleEngine {

	/**
	 * 
	 * 初始化规则引擎
	 */

	public void initEngine();

	/**
	 * 
	 * 刷新规则引擎中的规则
	 */

	public void refreshEnginRule();

	/**
	 * 
	 * 执行规则引擎
	 * 
	 * @param pointDomain
	 *            积分Fact
	 */

	public void executeRuleEngine(final SgBbPojo inPojo, SgBbPojo outPojo);

}
