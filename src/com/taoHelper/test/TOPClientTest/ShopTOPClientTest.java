package com.taoHelper.test.TOPClientTest;

import junit.framework.TestCase;

import org.junit.Test;

import com.taoHelper.TOPclient.ShopTOPClient;
import com.taobao.api.domain.Shop;

public class ShopTOPClientTest extends TestCase {
	/*
	 * 测试类ShopTOPClient中的getShopBySellerNick方法
	 */
	@Test
	public void testGetShopBySellerNick() {
		ShopTOPClient shopClient = new ShopTOPClient();
		String nickName = "alipublic03";
		Shop shop = shopClient.getShopBySellerNick(nickName);
		if (shop != null) {
			System.out
					.println(nickName + "的店：" + "cid=" + shop.getCid()
							+ ", title=" + shop.getTitle() + ", desc="
							+ shop.getDesc());
		} else {
			System.out.println("没有<" + nickName + ">这个卖家！");
		}
	}
}
