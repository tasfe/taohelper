package com.taoHelper.test.DAOTest;

import com.taoHelper.dao.PreferenceDAO;
import com.taoHelper.dataObject.Preference;

import junit.framework.TestCase;

public class PreferenceDAOTest extends TestCase {
	
	public void testCreatePreference(){
		
		PreferenceDAO pd=new PreferenceDAO();
		if(pd.createPreference("ysj", "123346")){
			System.out.println("create preference successfully!");
		}
		else System.out.println("create preference failed!");
		
	}
	
	public void testGetCountByCategoryId(){
		PreferenceDAO pd=new PreferenceDAO();
		int count=pd.getCountByCategoryId("ysj", "123346");
		
		System.out.println("ysj,123346,"+count);
	}
	
	public void testGetPreferenceOfUser(){
		PreferenceDAO pd=new PreferenceDAO();
		Preference preference=pd.getPreferenceOfUser("ysj");
		
		System.out.println(preference.getPreferenceMap().get(Long.valueOf(123346)));
		
	}
	

}
