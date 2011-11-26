package com.taoHelper.test.TOPClientTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.taoHelper.TOPclient.FavoriteTOPClient;
import com.taobao.api.domain.CollectItem;

public class FavoriteTOPClientTest extends TestCase {

	/*
	 * 目前沙箱不支持收藏夹功能，该代码在正式环境下测试通过
	 */
	@Test
	public void testGetFavoriteByUserNick() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("Please input the sessionKey: ");
			String sessionKey = br.readLine();
			FavoriteTOPClient favorClient = new FavoriteTOPClient();
			List<CollectItem> itemList = favorClient.getFavoriteByUserNick("alipublic03",
					sessionKey);
			System.out.println(itemList.size());
			for(int i = 0; i < itemList.size(); i++){
				CollectItem item = itemList.get(i);
				System.out.println(item.getTitle());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
