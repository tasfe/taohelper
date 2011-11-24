/**
 * 
 */
package com.taoHelper.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import com.taoHelper.dataObject.Budget;
import com.taoHelper.dataObject.Preference;

/**
 * @author PeggyGao
 *
 */
public class PreferenceDAO extends BaseDAO {
	
	/*
	 * create a preference record
	 * 
	 */
	public boolean createPreference(String user_nick,String category_id){
		Connection con=BaseDAO.getConnection();
		try
		{
			PreparedStatement ps = null;
			ps=con.prepareStatement("insert into preference (user_nick, category_id ) values (?,?)");
			ps.setString(1,user_nick);
			ps.setString(2, category_id);
			
			ps.executeUpdate();
			ps.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}		
		return true;		
	}
	
	public int getCountByCategoryId(String user_nick,String category_id){
		Connection con=BaseDAO.getConnection();
		int res=0;
		try
		{
			Statement stmt=con.createStatement();
			String sql="select count(*) from preference where user_nick='"+user_nick+"' and category_id='"+category_id+"'";
			ResultSet rs=stmt.executeQuery(sql);		
			while(rs.next())
			{
				res=rs.getInt(1);
			}
			
			stmt.close();
			con.close();
			
		}
		catch (Exception e) {
			// TODO: handle exception
			return -1;
		}
		return res;
	}
	
	public Preference getPreferenceOfUser(String user_nick){
		Connection con=BaseDAO.getConnection();
		Preference res_pre=new Preference();
		res_pre.setUserNick(user_nick);
		
		try
		{
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select category_id,count(*) from preference where user_nick='"+user_nick+"' group by category_id order by count(*) desc");		
			HashMap<Number, Integer> tpm=new HashMap<Number, Integer>();
			while(rs.next())
			{
				String cid=rs.getString(1);
				int count=rs.getInt(2);
			
				tpm.put(Long.valueOf(cid), count);	
			}
			res_pre.setPreferenceMap(tpm);
			stmt.close();
			con.close();
			
		}
		catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return res_pre;
	}

}
