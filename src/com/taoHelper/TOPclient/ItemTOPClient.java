/**
 * 
 */
package com.taoHelper.TOPclient;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.Item;
import com.taobao.api.request.ItemGetRequest;
import com.taobao.api.response.ItemGetResponse;

/**
 * @author PeggyGao
 * 
 */
public class ItemTOPClient extends BaseTOPClient {

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

}
