package com.taoHelper.service;

import java.util.List;

import com.taobao.api.domain.Item;

/**
 *  @author yaosj07
 *	每天去淘宝抓取收藏夹里价格的数据
 */
public class GetFavoritePriceService extends BaseService{
	/**
	 * 该函数每天执行一次
	 */
	public void getPriceFromTOP(){
		List<String> itemIdList = favoriteItemDAO.getAllItemList();
		for(int i=0;i<itemIdList.size();i++){
			String item_id = itemIdList.get(i);
			Item item = itemTopClient.getItemByIdNumId(Long.valueOf(item_id));
			double price = Double.valueOf(item.getPrice());
			
			favoriteItemDAO.createFavorRecord(item_id, price);
		}

	}
	
	public static void main(String args[]){
		GetFavoritePriceService gfps = new GetFavoritePriceService();
		gfps.getPriceFromTOP();
	}
}
