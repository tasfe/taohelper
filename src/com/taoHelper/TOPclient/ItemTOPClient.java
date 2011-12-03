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

	// ���id��ѯ��Ʒ����
	public Item getItemByIdNumId(Number numId) {
		TaobaoClient client = new DefaultTaobaoClient(this.inUseURL,
				this.appKey, this.appSecret);
		ItemGetRequest req = new ItemGetRequest();
		// �ö�����û�ж�ȡ�������Ҫ���Լ�����ӡ�
		req.setFields("num_iid,title,nick,desc,skus,created,num,price,cid,item_img");
		req.setNumIid(numId.longValue());
		try {
			ItemGetResponse response = client.execute(req);
			return response.getItem();
		} catch (ApiException e) {
			logger.error("taobao.item.get API���ô���", e);
		}

		return null;

	}

	
	/*
	 * �������id ��ѯ��������õȼ�����15����Ʒ�����û�оͽ����õȼ�-1��֪��������
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
			logger.error("taobao.items.get API����ʧ��", e);
		}

		return itemList;

	}

}
