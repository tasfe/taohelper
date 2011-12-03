package com.taoHelper.test.TOPClientTest;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.taoHelper.TOPclient.ItemTOPClient;
import com.taobao.api.domain.Item;

public class ItemTOPClientTest extends TestCase {

	@Test
	public void testGetItemByIdNumId() {
		ItemTOPClient itemClient = new ItemTOPClient();
		Item item = itemClient.getItemByIdNumId(6000256733L);
		System.out.println(item.getTitle() + "numIID=" + item.getNumIid()
				+ ", price=" + item.getPrice() + ", cid=" + item.getCid());
	}

	@Test
	public void testGetItemsByCid() {
		ItemTOPClient itemClient = new ItemTOPClient();
		List<Item> itemList = itemClient.getItemsByCid(14).subList(0, 5);

		for (Item i : itemList) {
			System.out.println(i.getCid() + "  " + i.getNick() + "	"
					+ i.getPicUrl() + "	" + i.getPrice() + "	" + i.getScore()
					+ "	" + i.getDetailUrl());
		}

	}
}
