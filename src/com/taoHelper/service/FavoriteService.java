/**
 * 
 */
package com.taoHelper.service;

import java.util.List;

import com.taoHelper.dataObject.FavoriteItem;

/**
 * @author PeggyGao
 *
 */
public class FavoriteService extends BaseService {
	
		
	/*查詢用戶收藏夾
	 * 通过FavoriteTOPClient中getFavoriteByUserNick方法获得数据收藏夾數據
	 * 再通過ItemTOPClient中getItemByIdNumId方法獲得商品詳情
	 * 通過CollectItemDAO獲得該商品的歷史價格
	 */
	public List<FavoriteItem> getFavoriteByUserNick(String sessionKey,String userNick){
		return null;
		
	}

}
