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
	private static String status[] = { "WAIT_BUYER_PAY",
			"WAIT_SELLER_SEND_GOODS", "WAIT_BUYER_CONFIRM_GOODS" };

	//获取当前交易中订单
	public List<Trade> getCurrentTrades(String sessionKey) {

		List<Trade> tradeList = new ArrayList<Trade>();
		TaobaoClient client = new DefaultTaobaoClient(sandboxURl, appKey,
				appSecret);
		TradesBoughtGetRequest req = new TradesBoughtGetRequest();
		req.setFields("seller_nick,buyer_nick,title,type,created,sid,tid,seller_rate,buyer_rate,status,payment,discount_fee,adjust_fee,post_fee,total_fee,pay_time,end_time,modified,consign_time,buyer_obtain_point_fee,point_fee,real_point_fee,received_payment,commission_fee,pic_path,num_iid,num_iid,num,price,cod_fee,cod_status,shipping_type,receiver_name,receiver_state,receiver_city,receiver_district,receiver_address,receiver_zip,receiver_mobile,receiver_phone,orders.title,orders.pic_path,orders.price,orders.num,orders.iid,orders.num_iid,orders.sku_id,orders.refund_status,orders.status,orders.oid,orders.total_fee,orders.payment,orders.discount_fee,orders.adjust_fee,orders.sku_properties_name,orders.item_meal_name,orders.buyer_rate,orders.seller_rate,orders.outer_iid,orders.outer_sku_id,orders.refund_id,orders.seller_type");

		try {
			for (int i = 0; i < status.length; i++) {

				req.setStatus(status[i]);
				TradesBoughtGetResponse response = client.execute(req,
						sessionKey);
				tradeList.addAll(response.getTrades());
			}
		} catch (ApiException e) {
			logger.error("taobao.trade.bought.get API调用错误", e);
		}
		return tradeList;

	}

	
	//获取历史交易订单数据
	public List<Trade> getPastTrades(String sessionKey) {
		return null;

	}

}
