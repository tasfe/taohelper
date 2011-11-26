package com.taoHelper.test.TOPClientTest;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.taoHelper.TOPclient.SellerCatTOPClient;
import com.taobao.api.domain.SellerCat;

public class SellerCatTOPClientTest extends TestCase {

	/*
	 * 测试类SellerCatTOPClient中的getSellerCatBySellerNick方法
	 */
	@Test
	public void testGetSellerCatBySellerNick(){
		SellerCatTOPClient sellerCatClient = new SellerCatTOPClient();
		List<SellerCat> sellerCats = sellerCatClient.getSellerCatBySellerNick("alipublic03");
		for(int i = 0; i < sellerCats.size(); i++){
			SellerCat sellerCat = sellerCats.get(i);
			System.out.println(i+": "+sellerCat.getName()+"  "+sellerCat.getType());
		}
	}
}
