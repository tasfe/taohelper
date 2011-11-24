package com.taoHelper.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class BaseDAO {
	public static final String CONNECTIONURL="jdbc:mysql://localhost:3306/taohelper?user=root&password=lled&useUnicode=true&characterEncoding=utf8";
	
	public static Connection getConnection()
	{
		Connection con;
		try
		{
			con = DriverManager.getConnection(CONNECTIONURL);		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return con;
	}

}
