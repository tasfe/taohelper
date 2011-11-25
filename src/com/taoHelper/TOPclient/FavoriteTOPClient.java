/**
 * 
 */
package com.taoHelper.TOPclient;

import java.util.ArrayList;
import java.util.List;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.CollectItem;
import com.taobao.api.request.FavoriteSearchRequest;
import com.taobao.api.response.FavoriteSearchResponse;

/**
 * @author PeggyGao
 * 
 */
public class FavoriteTOPClient extends BaseTOPClient {

	private static String collectType = "ITEM";

	/*
	 * 目前沙箱不支持收藏夹功能，改代码在正式环境下测试通过
	 */
	//查询收藏夹收藏的所有商品 先不查店铺
	public List<CollectItem> getFavoriteByUserNick(String userNick,
			String sessionKey) {
		
		TaobaoClient client=new DefaultTaobaoClient(this.sandboxURl, this.appKey, this.appSecret);
		FavoriteSearchRequest req=new FavoriteSearchRequest();
		req.setUserNick(userNick);
		req.setCollectType(collectType);
		List<CollectItem> itemList = new ArrayList<CollectItem>();
		try {
			Long pageNum = 1L;
			while(true){
				req.setPageNo(pageNum);
				FavoriteSearchResponse response = client.execute(req , sessionKey);
				List<CollectItem> pageList = response.getCollectItems();
				if(pageList != null)
					itemList.addAll(pageList);
				else 
					break;
				pageNum++;
			}
			return itemList;
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
