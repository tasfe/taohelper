/**
 * 
 */
package com.taoHelper.service;

import java.util.ArrayList;
import java.util.List;

import com.taoHelper.dataObject.FavoriteItem;
import com.taobao.api.domain.CollectItem;
import com.taobao.api.domain.Item;

/**
 * @author PeggyGao
 * 
 */
public class FavoriteService extends BaseService {

	/*
	 * 查詢用戶收藏夾 通过FavoriteTOPClient中getFavoriteByUserNick方法获得数据收藏夾數據
	 * 通過CollectItemDAO獲得該商品的歷史價格
	 * 对应价格波动页面
	 */
	public List<FavoriteItem> getFavoritePriceByUserNick(String sessionKey,
			String userNick) {

		if(null == sessionKey|| null==userNick){
			logger.error("invald sessionKey or userNick");
			return null;
		}
		List<CollectItem> cItemList = new ArrayList<CollectItem>();
		List<FavoriteItem> fItemList = new ArrayList<FavoriteItem>();

		cItemList = favoriteTopClient.getFavoriteByUserNick(userNick,
				sessionKey);

		Long numId;
		for (int i = 0; i < cItemList.size(); i++) {
			numId = cItemList.get(i).getItemNumid();
			fItemList.add(favoriteItemDAO.getFavoriteItemPrice(numId.toString()));
		}

		return fItemList;

	}

	/*通過ItemTOPClient中getItemByIdNumId方法獲得收藏的商品詳情
	 * 对应收藏宝贝页面
	 */
	public List<Item> getFavoriteItemByUserNick(String sessionKey,
			String userNick) {
		
		if(null == sessionKey|| null==userNick){
			logger.error("invald sessionKey or userNick");
			return null;
		}
		List<CollectItem> cItemList = new ArrayList<CollectItem>();
		List<Item> itemList = new ArrayList<Item>();
		
		cItemList = favoriteTopClient.getFavoriteByUserNick(userNick,
				sessionKey);
		
		for(int i = 0; i < cItemList.size(); i++){
			Long numId = cItemList.get(i).getItemNumid();
			itemList.add(itemTopClient.getItemByIdNumId(numId));
		}
		
		return itemList;

	}

}
