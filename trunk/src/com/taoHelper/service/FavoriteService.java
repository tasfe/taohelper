/**
 * 
 */
package com.taoHelper.service;

import java.sql.Date;
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
	 * ��ԃ�Ñ��ղ؊A ͨ��FavoriteTOPClient��getFavoriteByUserNick�����������ղ؊A����
	 * ͨ�^CollectItemDAO�@��ԓ��Ʒ�Ěvʷ�r��
	 * ��Ӧ�۸񲨶�ҳ��
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

	/*ͨ�^ItemTOPClient��getItemByIdNumId�����@���ղص���ƷԔ��
	 * ��Ӧ�ղر���ҳ��
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
	
	public boolean createFavorRecord(String  item_id,double price){
		return favoriteItemDAO.createFavorRecord(item_id, price);
	}

}
