package com.taoHelper.test.TOPClientTest;

import junit.framework.TestCase;

import org.junit.Test;

import com.taoHelper.TOPclient.ItemCatTOPClient;
import com.taobao.api.domain.ItemCat;

public class ItemCatTOPClientTest extends TestCase {

	/*
	 * 实际返回的是cid的parent_cid所在的ItemCat
	 */
	@Test
	public void testGetItemCatByCid(){
		ItemCatTOPClient catClient = new ItemCatTOPClient();
		ItemCat cat = catClient.getItemCatByCid(50003327L);
		if(cat != null)
			System.out.println(cat.getName());
		else
			System.out.println("cat = null");
	}
}
