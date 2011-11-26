/**
 * 
 */
package com.taoHelper.TOPclient;

import java.util.ArrayList;
import java.util.List;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.Trade;
import com.taobao.api.request.TradesBoughtGetRequest;
import com.taobao.api.response.TradesBoughtGetResponse;

/**
 * @author PeggyGao
 * 
 */
public class TradeTOPClient extends BaseTOPClient {

	//当前交易订单的状态
	private static String status_current[] = { "WAIT_BUYER_PAY",
			"WAIT_SELLER_SEND_GOODS", "WAIT_BUYER_CONFIRM_GOODS" };

	//已经完成的交易订单的状态
	private static String status_past[] = {"TRADE_FINISHED"};
	
	//获取当前交易中订单
	public List<Trade> getCurrentTrades(String sessionKey) {

		
		List<Trade> tradeList = new ArrayList<Trade>();
		TaobaoClient client = new DefaultTaobaoClient(this.sandboxURl, appKey,
				appSecret);
		TradesBoughtGetRequest req = new TradesBoughtGetRequest();
		req.setFields("seller_nick,buyer_nick,title,tid,payment,post_fee,total_fee,end_time,pic_path,num_iid,num");

		try {
			for (int i = 0; i < status_current.length; i++) {

				req.setStatus(status_current[i]);
				TradesBoughtGetResponse response = client.execute(req, sessionKey);
				if(response.getTrades() != null)
					tradeList.addAll(response.getTrades());
				else
					System.out.println("You have no opening trades now!");
			}
		} catch (ApiException e) {
			logger.error("taobao.trade.bought.get API调用错误", e);
		}
		return tradeList;

	}
	
	//获取历史交易订单数据，仅仅返回成功完成交易的订单
	public List<Trade> getPastTrades(String sessionKey) {
		
		List<Trade> tradeList = new ArrayList<Trade>();
		TaobaoClient client = new DefaultTaobaoClient(this.sandboxURl, appKey,
				appSecret);
		TradesBoughtGetRequest req = new TradesBoughtGetRequest();
		req.setFields("seller_nick,buyer_nick,title,tid,payment,post_fee,total_fee,end_time,pic_path,num_iid,num");

		try {
			for (int i = 0; i < status_past.length; i++) {
				req.setStatus(status_past[i]);
				TradesBoughtGetResponse response = client.execute(req,
						sessionKey);
				tradeList.addAll(response.getTrades());
			}
		} catch (ApiException e) {
			logger.error("taobao.trade.bought.get API调用错误", e);
		}
		return tradeList;
	}

}
