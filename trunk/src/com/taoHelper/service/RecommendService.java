/**
 * 
 */
package com.taoHelper.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.taoHelper.dataObject.Preference;
import com.taobao.api.domain.Item;
import com.taobao.api.domain.TaobaokeShop;

/**
 * @author PeggyGao
 * 
 */
public class RecommendService extends BaseService {

	/*
	 * 根据类别和用户昵称搜索淘宝客推荐店铺 通过ReferenceDAO获得用户购物偏好cid
	 * 通过ShopTOPClient中getTaobaokeShopByCid方法获得某类目下的推广店铺
	 * 通过ShopTOPClient中的getShopBySellerNick方法获得相应店铺具体信息 根据店铺分数进行推荐
	 */
	public List<TaobaokeShop> getRecommondShops(String userNick) {
		return null;

	}

	/*
	 * 根据用户的购买历史 
	 * 通過preDAO中getPreferenceOfUser方法獲得用戶購買每種類型商品的數量
	 * 通过ItemTOPClient中getItemsByCid方法
	 * 向用户推荐其购买数量前两名的类型的获得评分较高的前五个商品
	 *  
	 */
	public List<Item> getRecommondItems(String userNick) {

		Preference pre = preDAO.getPreferenceOfUser(userNick);

		Set<Number> keySet = pre.getPreferenceMap().keySet();

		long maxcid = 0;
		long secondMaxcid = 0;
		int max = Integer.MIN_VALUE;
		int secondMax = Integer.MIN_VALUE;
		int count = 0;
		for (Number n : keySet) {
			count = pre.getPreferenceMap().get(n);
			if (count > max) {
				secondMax = max;
				secondMaxcid = maxcid;
				max = count;
				maxcid = (Long) n;

			} else if (count > secondMax) {
				secondMax = count;
				secondMaxcid = (Long) n;
			}
		}

		List<Item> itemList = new ArrayList<Item>();
		List<Item> tempList = new ArrayList<Item>();

		tempList = itemTopClient.getItemsByCid(maxcid);
		if (tempList.size() > 5) {
			tempList = tempList.subList(0, 5);
		}
		itemList.addAll(tempList);

		tempList = itemTopClient.getItemsByCid(secondMaxcid);
		if (tempList.size() > 5) {
			tempList = tempList.subList(0, 5);
		}
		itemList.addAll(tempList);
		// itemList.addAll(itemTopClient.getItemsByCid(secondMax).subList(0,
		// 5));

		return itemList;

	}

}
