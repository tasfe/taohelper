/**
 * 
 */
package com.taoHelper.TOPclient;

import java.util.ArrayList;
import java.util.Arrays;
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
	private static int[] cids = {11,12,13,14,15,16,17,18,20,21,22,23,24,26,27,29,30,31,32,33,
		34,35,36,37,1020,1040,1041,1042,1043,1044,1045,1046,1047,1048,1049,1050,1051,1052,1053,1054,
		1056,1062,1082,1102,1103,1104,1105,1106,1122,1153,1154};

	
	private boolean cidCheck(long cid){
		
		for(int i = 0; i < cids.length ; i++){
			if(cid == cids[i]){
				return true;
			}
		}
		return false;
	}

	// ï¿½ï¿½ï¿½idï¿½ï¿½Ñ¯ï¿½ï¿½Æ·ï¿½ï¿½ï¿½ï¿½
	public Item getItemByIdNumId(Number numId) {
		TaobaoClient client = new DefaultTaobaoClient(this.inUseURL,
				this.appKey, this.appSecret);
		ItemGetRequest req = new ItemGetRequest();
		// ï¿½Ã¶ï¿½ï¿½ï¿½ï¿½ï¿½Ã»ï¿½Ð¶ï¿½È¡ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Òªï¿½ï¿½ï¿½Ô¼ï¿½ï¿½ï¿½ï¿½ï¿½Ó¡ï¿½
		req.setFields("num_iid,title,nick,desc,skus,created,num,price,cid,item_img");
		req.setNumIid(numId.longValue());
		try {
			ItemGetResponse response = client.execute(req);
			return response.getItem();
		} catch (ApiException e) {
			logger.error("taobao.item.get APIï¿½ï¿½ï¿½Ã´ï¿½ï¿½ï¿½", e);
		}

		return null;

	}

	
	/*
	 * ¸ù¾ÝÀà±ðid ²éÑ¯¸ÃÀà±ðÐÅÓÃµÈ¼¶´óÓÚ15µÄÉÌÆ·£¬Èç¹ûÃ»ÓÐ¾Í½«ÐÅÓÃµÈ¼¶-1£¬ÖªµÀËÑË÷µ½
	 */
	public List<Item> getItemsByCid(long cid) {
		
		if(cidCheck(cid) == false){
			logger.error("invalid cid :  " + cid +" for taobao.items.get API");
		}

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
			logger.error("taobao.items.get APIµ÷ÓÃÊ§°Ü", e);
		}

		return itemList;

	}

}
