/**
 * 
 */
package com.taoHelper.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.taoHelper.dataObject.Budget;
import com.taoHelper.exception.DAOException;
/**
 * @author PeggyGao
 *
 */
public class BudgetDAO extends BaseDAO {
	
	/*
	 * create a budget record
	 * 
	 */
	public boolean createBudget(String user_nick,double limit){
		Connection con=BaseDAO.getConnection();
		try
		{
			//check if the record of the current month exist
			Statement stmt=con.createStatement();
			String sql="select budget_num from budget where user_nick='"+user_nick+"' and (MONTH(NOW())-MONTH(timestamp)=0)";
			ResultSet rs=stmt.executeQuery(sql);
			int flag=0;
			while(rs.next()){
				flag++;
			}
			if(flag==0){
				PreparedStatement ps = null;
				ps=con.prepareStatement("insert into budget (user_nick, budget_num ,timestamp) values (?,?,?)");
				ps.setString(1,user_nick);
				ps.setDouble(2, limit);
				ps.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));
				
				ps.executeUpdate();
				ps.close();
				con.close();
			}
			else{
				modifyBudget(user_nick,limit);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}
	/*
	 * get the budget of current month
	 * if the num is -1 means the user hasnot set any budget
	 */
	public Budget getBudget(String user_nick){
		
		Connection con=BaseDAO.getConnection();
		Budget res_bug=new Budget();
		res_bug.setBudget(-1);
		try
		{
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select budget_num from budget where user_nick='"+user_nick+"' and (MONTH(NOW())-MONTH(timestamp)=0)");
			
			while(rs.next())
			{
				double budget=rs.getDouble(1);
				res_bug.setBudget(budget);
			}
			res_bug.setBuyerNick(user_nick);
		}
		catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return res_bug;
		
	}
	
	/*
	 * modify the budget of this month
	 */
	private boolean modifyBudget(String user_nick,double new_budget){
		Connection con=BaseDAO.getConnection();
		try
		{
			String sql="update budget set budget_num="+new_budget+" where user_nick='"+user_nick+"' and (MONTH(NOW())-MONTH(timestamp)=0)";
			Statement stmt=con.createStatement();
			stmt.executeUpdate(sql);
			
			stmt.close();
			con.close();
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}
	
}
