/**
 * 
 */
package com.taoHelper.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.taoHelper.dataObject.FavoriteItem;
import com.taobao.api.domain.CollectItem;

/**
 * @author PeggyGao
 *
 */
public class FavoriteItemDAO extends BaseDAO {
	
	/**
	 * create a favorite record to record the price of a certain item
	 */
	public boolean createFavorRecord(String item_id,double price){
		Connection con=BaseDAO.getConnection();
		try
		{
					
			PreparedStatement ps = null;
			ps=con.prepareStatement("insert into favorite (item_id, price ,date) values (?,?,?)");
			ps.setString(1,item_id);
			ps.setDouble(2, price);
			ps.setDate(3, new Date(System.currentTimeMillis()));
			
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
	
	public FavoriteItem getFavoriteItemPrice(String item_id){
		Connection con=BaseDAO.getConnection();
		FavoriteItem fItem=new FavoriteItem();
		
		try
		{
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select price,date from favorite where item_id='"+item_id+"' order by date asc");
			
			HashMap<java.util.Date, Double> tmpHp = new HashMap<java.util.Date, Double>();
			while(rs.next())
			{
				double price=rs.getDouble("price");
				java.util.Date date=rs.getDate("date");
				tmpHp.put(date, price);
			}
			CollectItem cItem = new CollectItem();
			cItem.setItemNumid(Long.valueOf(item_id));
			fItem.setCollectItem(cItem);
			
			fItem.setHistoryPrice(tmpHp);		
		}
		catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return fItem;
	}
	
	public FavoriteItem getFavoriteItemPrice(CollectItem ci){
		Connection con=BaseDAO.getConnection();
		FavoriteItem fItem=new FavoriteItem();
		
		try
		{
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select price,date from favorite where item_id='"+ci.getItemNumid().toString()+"'");
			
			HashMap<java.util.Date, Double> tmpHp = new HashMap<java.util.Date, Double>();
			while(rs.next())
			{
				double price=rs.getDouble("price");
				java.util.Date date=rs.getDate("date");
				tmpHp.put(date, price);
			}
			
			fItem.setHistoryPrice(tmpHp);		
		}
		catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return fItem;
	}
	/**
	 *  获取所有的收藏商品的id
	 * 
	 */
	public List<String> getAllItemList(){
		Connection con=BaseDAO.getConnection();
		List<String> item_list=new ArrayList<String>();
		
		try
		{
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select distinct(item_id) from favorite");		
			while(rs.next())
			{
				item_list.add(rs.getString(1));
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return item_list;
	}

}
