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
	 * ���������û��ǳ������Ա����Ƽ����� ͨ��ReferenceDAO����û�����ƫ��cid
	 * ͨ��ShopTOPClient��getTaobaokeShopByCid�������ĳ��Ŀ�µ��ƹ����
	 * ͨ��ShopTOPClient�е�getShopBySellerNick���������Ӧ���̾�����Ϣ ���ݵ��̷��������Ƽ�
	 */
	public List<TaobaokeShop> getRecommondShops(String userNick) {
		return null;

	}

	/*
	 * �����û��Ĺ�����ʷ 
	 * ͨ�^preDAO��getPreferenceOfUser�����@���Ñ�ُ�Iÿ�N�����Ʒ�Ĕ���
	 * ͨ��ItemTOPClient��getItemsByCid����
	 * ���û��Ƽ��乺������ǰ���������͵Ļ�����ֽϸߵ�ǰ�����Ʒ
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
