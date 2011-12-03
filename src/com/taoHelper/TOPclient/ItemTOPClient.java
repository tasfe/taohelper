/**
 * 
 */
package com.taoHelper.TOPclient;

import java.util.List;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.Item;
import com.taobao.api.request.ItemGetRequest;
import com.taobao.api.request.ItemsGetRequest;
import com.taobao.api.response.ItemGetResponse;
import com.taobao.api.response.ItemsGetResponse;

/**
 * @author PeggyGao
 * 
 */
public class ItemTOPClient extends BaseTOPClient {

	private static long SCORE = 15l;

	// 锟斤拷锟絠d锟斤拷询锟斤拷品锟斤拷锟斤拷
	public Item getItemByIdNumId(Number numId) {
		TaobaoClient client = new DefaultTaobaoClient(this.inUseURL,
				this.appKey, this.appSecret);
		ItemGetRequest req = new ItemGetRequest();
		// 锟矫讹拷锟斤拷锟斤拷没锟叫讹拷取锟斤拷锟斤拷锟斤拷锟揭拷锟斤拷约锟斤拷锟斤拷锟接★拷
		req.setFields("num_iid,title,nick,desc,skus,created,num,price,cid,item_img");
		req.setNumIid(numId.longValue());
		try {
			ItemGetResponse response = client.execute(req);
			return response.getItem();
		} catch (ApiException e) {
			logger.error("taobao.item.get API锟斤拷锟矫达拷锟斤拷", e);
		}

		return null;

	}

	
	/*
	 * 根据类别id 查询该类别信用等级大于15的商品，如果没有就将信用等级-1，知道搜索到
	 */
	public List<Item> getItemsByCid(long cid) {

		List<Item> itemList = null;
		TaobaoClient client = new DefaultTaobaoClient(this.inUseURL,
				this.appKey, this.appSecret);
		ItemsGetRequest req = new ItemsGetRequest();
		req.setFields("num_iid,title,nick,pic_url,cid,detail_url,price,delist_time,post_fee,score,volume");
		req.setCid(cid);
		req.setStartScore(SCORE);

		try {
			for (;;) {
				ItemsGetResponse response = client.execute(req);
				if (response.getTotalResults() != 0) {
					itemList = response.getItems();
					break;
				} else {
					req.setStartScore(SCORE - 1);
				}
			}

		} catch (ApiException e) {
			logger.error("taobao.items.get API调用失败", e);
		}

		return itemList;

	}

}
