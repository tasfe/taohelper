package com.taoHelper.test.TOPClientTest;

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
}
