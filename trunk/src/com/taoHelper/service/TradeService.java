/**
 * 
 */
package com.taoHelper.service;

import java.util.List;
import java.util.Map;

import com.taobao.api.domain.Trade;

/**
 * @author PeggyGao
 * 调用client和DAO完成数据整合处理
 *
 */
public class TradeService extends BaseService {
	
		
	/*获得当前交易中订单数据详情
	 * 通过调用TradeTOPClient中getCurrentTrade获得当前交易中订单的信息
	 * 通过调用LogisticsTOPClient中getLogisticsInfoByTid获得每笔订单的物流信息
	 */
	public List<Trade> getCurrentTrades(String sessionKey){
		
		return null;
		
	}
	
	/*获得月消费统计
	 * 通过调用TradeTOPClient中getPastTrades按月统计消费金额
	 */
	public Map<Integer,Double> getPaymentInMonth(String sessionKey){
		return null;
		
	}
	
	/*获得类目消费统计
	 * 通过调用TradeTOPClient中getPastTrades获得历史订单
	 * 通过ItemTOPClient中的getItemByIdNumId方法获得商品的类目id
	 * 通过ItemCatTOPClient中的getItemCatByCid获得商品类目
	 * 这里都使用父类目
	 */
	public Map<String,Double> getPaymentInCat(String sessionKey){
		return null;
		
	}

}
