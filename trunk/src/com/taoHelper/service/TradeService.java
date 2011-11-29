/**
 * 
 */
package com.taoHelper.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.taoHelper.TOPclient.ItemCatTOPClient;
import com.taoHelper.TOPclient.LogisticsTOPClient.LogisticsInfo;
import com.taobao.api.domain.Item;
import com.taobao.api.domain.ItemCat;
import com.taobao.api.domain.Trade;

/**
 * @author PeggyGao
 * ����client��DAO����������ϴ���
 *
 */
public class TradeService extends BaseService {
	
		
	/*��õ�ǰ�����ж�����������
	 * ͨ������TradeTOPClient��getCurrentTrade��õ�ǰ�����ж�������Ϣ
	 * 
	 */
	public List<Trade> getCurrentTrades(String sessionKey){
		
		if(null==sessionKey){
			logger.error("invalid sessionKey "+ sessionKey);
			return null;
		}
		
		List<Trade> tradeList = new ArrayList<Trade>();
		tradeList = tradeTopClient.getCurrentTrades(sessionKey);
		
		return tradeList;
		
	}
	
	
	/*
	 * ͨ������LogisticsTOPClient��getLogisticsInfoByTid���ÿ�ʶ�����������Ϣ
	 */
	public LogisticsInfo getCurrentLogisticsInfo(Number tid,String sellerNick){
		
		if(null==tid || null == sellerNick){
			logger.error("invalid parameter " +tid+"  " +sellerNick);
			return null;
		}
		
		LogisticsInfo logisticsInfo;
		logisticsInfo= logisticsTOPClient.getLogisticsInfoByTid(tid, sellerNick);
		
		return logisticsInfo;
		
	}
	 
	/*���������ͳ��
	 * ͨ������TradeTOPClient��getPastTrades����ͳ�����ѽ��
	 */
	public Map<Integer,Double> getPaymentInMonth(String sessionKey){
		
		if(null==sessionKey){
			logger.error("invalid sessionKey "+ sessionKey);
			return null;
		}
		
		List<Trade> tradeList = new ArrayList<Trade>();
		tradeList = tradeTopClient.getPastTrades(sessionKey);
		HashMap<Integer,Double> moneyInMonth = new HashMap<Integer,Double>(); 
		
		Trade trade;
		Integer month;
		Double money = 0.0;
		for(int i = 0 ; i <tradeList.size(); i++){
			trade = tradeList.get(i);
			month = trade.getEndTime().getMonth()+1;
			if(moneyInMonth.get(month)==null){
				moneyInMonth.put(month, Double.valueOf(trade.getPayment()));
			}else{
				moneyInMonth.put(month, moneyInMonth.get(money)+Double.valueOf(trade.getPayment()));
			}
		
			
		}
		
		return moneyInMonth;
		
	}
	
	/*�����Ŀ����ͳ��
	 * ͨ������TradeTOPClient��getPastTrades�����ʷ����
	 * ͨ��ItemTOPClient�е�getItemByIdNumId���������Ʒ����Ŀid
	 * ͨ��ItemCatTOPClient�е�getItemCatByCid�����Ʒ��Ŀ
	 * ���ﶼʹ�ø���Ŀ
	 */
	public Map<String,Double> getPaymentInCat(String sessionKey){
		
		if(null==sessionKey){
			logger.error("invalid sessionKey "+ sessionKey);
			return null;
		}
		
		List<Trade> tradeList = new ArrayList<Trade>();
		tradeList = tradeTopClient.getPastTrades(sessionKey);
		HashMap<String,Double> moneyInCat = new HashMap<String,Double>(); 
		
		Trade trade;
		Item item;
		ItemCat itemCat;
		String cateName;
		Double money = 0.0;
		for(int i = 0; i < tradeList.size();i++){
			trade = tradeList.get(i);
			item = itemTopClient.getItemByIdNumId(trade.getNumIid());
			itemCat = itemCatTopClient.getItemCatByCid(item.getCid());
			cateName = itemCat.getName();
			if(moneyInCat.get(cateName)==null){
				moneyInCat.put(cateName, Double.valueOf(trade.getPayment()));
			}else{
				moneyInCat.put(cateName, moneyInCat.get(money)+Double.valueOf(trade.getPayment()));
			}
		
		}
		
		return moneyInCat;
		
	}

}