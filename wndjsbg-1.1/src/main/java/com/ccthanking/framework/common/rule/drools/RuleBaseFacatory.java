/*
 * RuleBaseFacatory.java  v1.00  2013-11-27
 * Peoject	copj-drools-test
 * Copyright (c) 2013 KcpmIT
 *
 * Filename	:	RuleBaseFacatory.java  v1.00 2013-11-27
 * Project	: 	copj-drools-test
 * Copyight	:	Copyright (c) 2013 KcpmIT
 */
package com.ccthanking.framework.common.rule.drools;

import org.drools.RuleBase;
import org.drools.RuleBaseFactory;

/**
 * 
 * RuleBaseFacatory 单实例RuleBase生成工具
 * 
 */

public class RuleBaseFacatory {

	private static RuleBase ruleBase;

	public static RuleBase getRuleBase() {

		return null != ruleBase ? ruleBase : RuleBaseFactory.newRuleBase();

	}

}
