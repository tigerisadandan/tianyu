/*
 * SgBbRuleEngineTest.java  v1.00  2014-4-22
 * Peoject	wndjssg
 * Copyright (c) 2014 Wisdragon
 *
 * Filename	:	SgBbRuleEngineTest.java  v1.00 2014-4-22
 * Project	: 	wndjssg
 * Copyight	:	Copyright (c) 2014 Wisdragon
 */
package com.ccthanking.business.sgenter.service.rule;

import com.ccthanking.business.sgenter.vo.SgBbPbrsPojo;
import com.ccthanking.business.sgenter.vo.SgBbPddjPojo;
import com.ccthanking.business.sgenter.vo.SgBbPdyjPojo;
import com.ccthanking.business.sgenter.vo.SgBbPojo;
import com.ccthanking.business.sgenter.vo.SgBbZygmPojo;

/**
 * test class.
 * 
 * @author <a href="mailto:genliang.jiang@wisdragon.com">蒋根亮</a>
 * @version v1.00
 * @since 1.00 2014-4-22
 * 
 */
public class SgBbRuleEngineTest {

	public static void main(String[] args) {
		SgBbRuleEngine sgBbRuleEngine = SgBbRuleEngine.getInstance();
		sgBbRuleEngine.initEngine();

		testPdyj(sgBbRuleEngine);

	}

	private static void testPdyj(SgBbRuleEngine sgBbRuleEngine) {

		SgBbPojo outPojo = new SgBbPojo();
		System.out.println(outPojo.isEnabled() + "  befoer " + outPojo.getQyzzpdbz());

		// 测试返回条件.
		// SgBbPdyjPojo inPojo = new SgBbPdyjPojo();
		// inPojo.setGclx(40);

		// sgBbRuleEngine.executeRuleEngine(inPojo, outPojo);
		// System.out.println(outPojo.isEnabled() + "  after1 判断依据=" +
		// outPojo.getQyzzpdbz());

		// 返回等级
//		SgBbPddjPojo inPojo1 = new SgBbPddjPojo();
//		inPojo1.setGclx(27);
//		inPojo1.setJzwcs(13);
//		inPojo1.setDwkd(20);
//		inPojo1.setDwjzmj(4000);
//		sgBbRuleEngine.executeRuleEngine(inPojo1, outPojo);
//		System.out.println(outPojo.isEnabled() + "  after2 企业等级=" + outPojo.getZzdj());

		
		 System.out.println("==================================---------------");
		 // 测试返回人数
//		 SgBbPbrsPojo rs1 = new SgBbPbrsPojo();
//		 rs1.setGclb(22);
//		 rs1.setDwjzmj(40000);
//		
//		 sgBbRuleEngine.executeRuleEngine(rs1, outPojo);
//		 System.out.println(outPojo.isEnabled() + "  after3配备人数=" +
//		 outPojo.getZdpbrs()+"  增加人数："+outPojo.getZdpbrszj());
		 
		 //测试返回人数2
		 SgBbPbrsPojo rs1 = new SgBbPbrsPojo();
		 rs1.setGclb(24);
		 rs1.setDxgchte(200000100);
		 sgBbRuleEngine.executeRuleEngine(rs1, outPojo);
		 System.out.println(outPojo.isEnabled() + "  after3配备人数=" +
		 outPojo.getZdpbrs()+"  增加人数："+outPojo.getZdpbrszj());

		// 测试执业规模
//		 SgBbZygmPojo inPojo2 = new SgBbZygmPojo();
//		 inPojo2.setGclx(27);
//		 inPojo2.setJzwcs(13);
//		 inPojo2.setDwkd(20);
//		 inPojo2.setDwjzmj(40000);
//		 sgBbRuleEngine.executeRuleEngine(inPojo2, outPojo);
//		 System.out.println(outPojo.isEnabled() + "  after4执业规模= " +
//		 outPojo.getJzszygcgmbz());
	}

}
