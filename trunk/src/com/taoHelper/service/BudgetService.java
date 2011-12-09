/**
 * 
 */
package com.taoHelper.service;

import java.util.Date;
import java.util.HashMap;

import com.taoHelper.dataObject.Budget;

/**
 * @author PeggyGao
 *
 */
public class BudgetService extends BaseService {
	
	
	public Budget getBudgerByUserNick(String userNick){
		
		if(null==userNick){
			logger.error("invald userNick " + userNick);
			return null;
		}
		
		Budget budget = new Budget();
		budget = budgetDAO.getBudget(userNick);
		
		return budget;		
	}
	
	
	public HashMap <Integer, Double> getAllBudgets(String userNick){
		if(null==userNick){
			logger.error("invald userNick " + userNick);
			return null;
		}
		return budgetDAO.getAllBudgets(userNick);
	}
	
	public boolean createBudget(String userNick,double limit){
		if(null == userNick ){
			logger.error("invalid userNick "+userNick);
			return false;
		}
		if(Double.isNaN(limit)){
			logger.error("invalid double "+ limit);
			return false;
		}
		boolean result = budgetDAO.createBudget(userNick, limit);
		
		return result;
		
	}
	
	

}
