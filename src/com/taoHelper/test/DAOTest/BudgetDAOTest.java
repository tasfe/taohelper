package com.taoHelper.test.DAOTest;

import com.taoHelper.dao.BudgetDAO;

import junit.framework.TestCase;

public class BudgetDAOTest extends TestCase {
	
//	public void testCreateBudget(){
//		BudgetDAO bd =new BudgetDAO();
//		if(bd.createBudget("ysj", 30.0)) System.out.println("insert budget success");
//		else System.out.println("insert budge failed");
//		
//	}
	
	public void testGetBudget(){
		BudgetDAO bd=new BudgetDAO();		
		System.out.println(bd.getBudget("ysj").getBuyerNick()+","+bd.getBudget("ysj").getBudget());
	}
	
	public void testModifyBudget(){
		
//		BudgetDAO bd=new BudgetDAO();
//		if(bd.modifyBudget("ysj", 40.0)) System.out.println("modified successfully!");
//		else System.out.println("modify failed!");
	}
}
