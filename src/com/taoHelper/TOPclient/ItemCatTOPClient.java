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
 * @author PeggyGao ��Ʒ��Ŀ�����ø���Ŀ
 * 
 */
public class ItemCatTOPClient extends BaseTOPClient {

	// ��ѯ��Ʒ����Ŀ
	// �����cidΪ��Ʒ��cid�������в�ѯcid��Ӧ��parent_cid��Ȼ�󷵻�parent_cid��Ӧ��ItemCat
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
			logger.error("taobao.itemats.get API���ô���", e);
		}
		return null;
	}

}
