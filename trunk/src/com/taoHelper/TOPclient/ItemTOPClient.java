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

	// 根据id查询商品详情
	public Item getItemByIdNumId(Number numId) {
		TaobaoClient client = new DefaultTaobaoClient(this.sandboxURl,
				this.appKey, this.appSecret);
		ItemGetRequest req = new ItemGetRequest();
		// 好多属性没有读取，如果需要可以继续添加。
		req.setFields("num_iid,title,nick,desc,skus,created,num,price,cid");
		req.setNumIid(numId.longValue());
		try {
			ItemGetResponse response = client.execute(req);
			return response.getItem();
		} catch (ApiException e) {
			logger.error("taobao.item.get API调用错误", e);
		}

		return null;

	}

}
