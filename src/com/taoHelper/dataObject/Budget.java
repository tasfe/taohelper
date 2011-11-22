/**
 * 
 */
package com.taoHelper.dataObject;

/**
 * @author PeggyGao 用户消费预算
 * 
 */
public class Budget extends BaseDO {

	private String buyerNick;

	private double budget;

	public String getBuyerNick() {
		return buyerNick;
	}

	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

}
