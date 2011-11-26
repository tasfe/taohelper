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
 * 调用client和DAO完成数据整合处理
 *
 */
public class TradeService extends BaseService {
	
		
	/*获得当前交易中订单数据详情
	 * 通过调用TradeTOPClient中getCurrentTrade获得当前交易中订单的信息
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
	 * 通过调用LogisticsTOPClient中getLogisticsInfoByTid获得每笔订单的物流信息
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
	 
	/*获得月消费统计
	 * 通过调用TradeTOPClient中getPastTrades按月统计消费金额
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
	
	/*获得类目消费统计
	 * 通过调用TradeTOPClient中getPastTrades获得历史订单
	 * 通过ItemTOPClient中的getItemByIdNumId方法获得商品的类目id
	 * 通过ItemCatTOPClient中的getItemCatByCid获得商品类目
	 * 这里都使用父类目
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
