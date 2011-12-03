/**
 * 
 */
package com.taoHelper.test.ServiceTest;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.taoHelper.service.RecommendService;
import com.taobao.api.domain.Item;

/**
 * @author PeggyGao
 * 
 */
public class RecommendServiceTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetRecommondItems() {

		RecommendService rs = new RecommendService();
		List<Item> itemlist = rs.getRecommondItems("ysj");

		for (Item i : itemlist) {
			System.out.println(i.getDetailUrl() + "  " + i.getTitle() + "  "
					+ i.getCid() + " " + i.getScore());
		}

	}

}
