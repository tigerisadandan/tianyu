/*
 * SgBbRuleEngine.java  v1.00  2014-4-22
 * Peoject	wndjssg
 * Copyright (c) 2014 Wisdragon
 *
 * Filename	:	SgBbRuleEngine.java  v1.00 2014-4-22
 * Project	: 	wndjssg
 * Copyight	:	Copyright (c) 2014 Wisdragon
 */
package com.ccthanking.business.sgenter.service.rule;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.drools.RuleBase;
import org.drools.StatefulSession;
import org.drools.compiler.DroolsParserException;
import org.drools.compiler.PackageBuilder;
import org.drools.rule.Package;
import org.drools.spi.Activation;

import com.ccthanking.business.sgenter.vo.SgBbPojo;
import com.ccthanking.framework.common.rule.drools.RuleBaseFacatory;
import com.ccthanking.framework.common.rule.drools.RuleEngine;

/**
 * 施工报备规则.
 * 
 * @author <a href="mailto:genliang.jiang@wisdragon.com">蒋根亮</a>
 * @version v1.00
 * @since 1.00 2014-4-22
 * 
 */
public class SgBbRuleEngine implements RuleEngine {

	private static String rulesFile = "rule/lx-pdyj.drl";
	private static String rulesFile_dj = "rule/lx-pddj.drl";
	private static String rulesFile_rs = "rule/lx-pbrs.drl";
	private static String rulesFile_gm = "rule/lx-zygm.drl";

	private RuleBase ruleBase;

	private static SgBbRuleEngine insance;

	private SgBbRuleEngine() {
	}

	public static SgBbRuleEngine getInstance() {
		if (insance == null) {
			insance = new SgBbRuleEngine();
			insance.initEngine();
		}
		return insance;
	}

	public void initEngine() {
		ruleBase = RuleBaseFacatory.getRuleBase();
		try {
			Reader source1 = new InputStreamReader(SgBbRuleEngine.class.getClassLoader().getResourceAsStream(rulesFile));
			Reader source2 = new InputStreamReader(SgBbRuleEngine.class.getClassLoader().getResourceAsStream(
					rulesFile_rs));
			Reader source3 = new InputStreamReader(SgBbRuleEngine.class.getClassLoader().getResourceAsStream(
					rulesFile_gm));
			Reader source4 = new InputStreamReader(SgBbRuleEngine.class.getClassLoader().getResourceAsStream(
					rulesFile_dj));

			PackageBuilder builder = new PackageBuilder();

			/**
			 * 可以加载多个drl，执行时会遍历多个drl 等同于一个drl包含了多个drl内容
			 */
			builder.addPackageFromDrl(source1);
			builder.addPackageFromDrl(source4);
			builder.addPackageFromDrl(source2);
			builder.addPackageFromDrl(source3);
			Package pkg = builder.getPackage();
			ruleBase.addPackage(pkg);
		} catch (DroolsParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void refreshEnginRule() {
		ruleBase = RuleBaseFacatory.getRuleBase();
		org.drools.rule.Package[] packages = ruleBase.getPackages();
		for (org.drools.rule.Package pg : packages) {
			ruleBase.removePackage(pg.getName());
		}
		initEngine();
	}

	public void executeRuleEngine(SgBbPojo inPojo, SgBbPojo outPojo) {
		if (null == ruleBase.getPackages() || 0 == ruleBase.getPackages().length) {
			return;
		}

		StatefulSession statefulSession = ruleBase.newStatefulSession();
		statefulSession.setGlobal("retPojo", outPojo);

		statefulSession.insert(inPojo);

		// fire
		statefulSession.fireAllRules(new org.drools.spi.AgendaFilter() {
			public boolean accept(Activation activation) {
				System.out.println(" ===========rule name=" + activation.getRule().getName());
				return !activation.getRule().getName().contains("_test");
			}
		});

		statefulSession.dispose();
	}

}
