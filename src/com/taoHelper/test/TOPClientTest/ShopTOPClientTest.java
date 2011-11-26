package com.taoHelper.test.TOPClientTest;

import junit.framework.TestCase;

import org.junit.Test;

import com.taoHelper.TOPclient.ShopTOPClient;
import com.taobao.api.domain.Shop;

public class ShopTOPClientTest extends TestCase {
	/*
	 * ������ShopTOPClient�е�getShopBySellerNick����
	 */
	@Test
	public void testGetShopBySellerNick() {
		ShopTOPClient shopClient = new ShopTOPClient();
		String nickName = "alipublic03";
		Shop shop = shopClient.getShopBySellerNick(nickName);
		if (shop != null) {
			System.out
					.println(nickName + "�ĵ꣺" + "cid=" + shop.getCid()
							+ ", title=" + shop.getTitle() + ", desc="
							+ shop.getDesc());
		} else {
			System.out.println("û��<" + nickName + ">������ң�");
		}
	}
}
