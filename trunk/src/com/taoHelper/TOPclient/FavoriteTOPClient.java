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
	 * 閻╊喖澧犲▽娆戭唸娑撳秵鏁幐浣规暪閽樺繐銇欓崝鐔诲厴閿涘本鏁兼禒锝囩垳閸︺劍顒滃蹇曞箚婢у啩绗呭ù瀣槸闁俺绻�
	 */
	// 閺屻儴顕楅弨鎯版婢惰鏁归挊蹇曟畱閹碉拷婀侀崯鍡楁惂 閸忓牅绗夐弻銉ョ暗闁撅拷
	public List<CollectItem> getFavoriteByUserNick(String userNick,
			String sessionKey) {

		TaobaoClient client = new DefaultTaobaoClient(this.inUseURL,
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
			logger.error("taobao.favorite.search API璋冪敤閿欒", e);
		}
		return itemList;
	}

}
