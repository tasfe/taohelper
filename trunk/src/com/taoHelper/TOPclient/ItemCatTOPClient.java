/**
 * 
 */
package com.taoHelper.TOPclient;

import java.util.List;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.ItemCat;
import com.taobao.api.request.ItemcatsGetRequest;
import com.taobao.api.response.ItemcatsGetResponse;

/**
 * @author PeggyGao 商品类目均采用父类目
 * 
 */
public class ItemCatTOPClient extends BaseTOPClient {

	// 查询商品父类目
	// 传入的cid为商品的cid，方法中查询cid对应的parent_cid，然后返回parent_cid对应的ItemCat
	public ItemCat getItemCatByCid(Number cid) {
		TaobaoClient client = new DefaultTaobaoClient(this.sandboxURl,
				this.appKey, this.appSecret);
		ItemcatsGetRequest req = new ItemcatsGetRequest();
		req.setFields("cid,parent_cid,name,is_parent");
		req.setCids(cid.toString());
		try {
			ItemcatsGetResponse response = client.execute(req);
			List<ItemCat> catList = response.getItemCats();
			if (catList != null && catList.size() > 0) {
				Number parent_cid = catList.get(0).getParentCid();
				req.setCids(parent_cid.toString());
				response = client.execute(req);
				catList = response.getItemCats();
				if (catList != null && catList.size() > 0) {
					return catList.get(0);
				} else
					return null;
			} else {
				return null;
			}
		} catch (ApiException e) {
			logger.error("taobao.itemats.get API调用错误", e);
		}
		return null;
	}

}
