/**
 * 
 */
package com.taoHelper.dataObject;

import java.util.Date;
import java.util.HashMap;

import com.taobao.api.domain.CollectItem;

/**
 * @author PeggyGao ��¼���ղ���Ʒ�Ĕ�������ʷ�۸�
 */
public class FavoriteItem extends BaseDO {

	private CollectItem collectItem;

	private HashMap<Date, Double> historyPrice = new HashMap<Date, Double>();

	public CollectItem getCollectItem() {
		return collectItem;
	}

	public void setCollectItem(CollectItem collectItem) {
		this.collectItem = collectItem;
	}

	public HashMap<Date, Double> getHistoryPrice() {
		return historyPrice;
	}

	public void setHistoryPrice(HashMap<Date, Double> historyPrice) {
		this.historyPrice = historyPrice;
	}

}
