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
	 * 鐩墠娌欑涓嶆敮鎸佹敹钘忓す鍔熻兘锛屾敼浠ｇ爜鍦ㄦ寮忕幆澧冧笅娴嬭瘯閫氳繃
	 */
	// 鏌ヨ鏀惰棌澶规敹钘忕殑鎵�湁鍟嗗搧 鍏堜笉鏌ュ簵閾�
	public List<CollectItem> getFavoriteByUserNick(String userNick,
			String sessionKey) {

		TaobaoClient client = new DefaultTaobaoClient(this.onLineURL,
				this.appKey, this.appSecret);
		FavoriteSearchRequest req = new FavoriteSearchRequest();
		req.setUserNick(userNick);
		req.setCollectType(collectType);
		List<CollectItem> itemList = new ArrayList<CollectItem>();
		try {
			Long pageNum = 1L;
			while (true) {
				req.setPageNo(pageNum);
				FavoriteSearchResponse response = client.execute(req,
						sessionKey);
				List<CollectItem> pageList = response.getCollectItems();
				if (pageList != null)
					itemList.addAll(pageList);
				else
					break;
				pageNum++;
			}
		} catch (ApiException e) {
			logger.error("taobao.favorite.search API调用错误", e);
		}
		return itemList;
	}

}
