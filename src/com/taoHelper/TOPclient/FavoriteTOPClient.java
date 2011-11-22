/**
 * 
 */
package com.taoHelper.TOPclient;

import java.util.List;

import com.taobao.api.domain.CollectItem;

/**
 * @author PeggyGao
 * 
 */
public class FavoriteTOPClient extends BaseTOPClient {

	private static String collectType = "ITEM";

	//查询收藏夹收藏的商品 先不查店铺
	public List<CollectItem> getFavoriteByUserNick(String userNick,
			String sessionKey) {
		return null;

	}

}
