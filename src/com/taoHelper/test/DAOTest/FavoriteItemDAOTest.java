package com.taoHelper.test.DAOTest;

import java.sql.Date;
import java.sql.Timestamp;

import org.junit.Test;

import com.taoHelper.dao.FavoriteItemDAO;

import junit.framework.TestCase;

public class FavoriteItemDAOTest extends TestCase {
	
//	public void testCreateFavorRecord(){
//		FavoriteItemDAO fid=new FavoriteItemDAO();
//		if(fid.createFavorRecord("112233", 23.0, new Date(System.currentTimeMillis()))){
//			System.out.println("create favor successfully!");
//		}		
//	}
	
	public void testGetFavoriteItemPrice(){
		FavoriteItemDAO fid = new FavoriteItemDAO();
		System.out.println(fid.getFavoriteItemPrice("112233").getHistoryPrice().size());
	}

}
