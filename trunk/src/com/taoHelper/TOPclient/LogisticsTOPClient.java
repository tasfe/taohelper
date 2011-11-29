/**
 * 
 */
package com.taoHelper.TOPclient;

import java.util.List;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.TransitStepInfo;
import com.taobao.api.request.LogisticsTraceSearchRequest;
import com.taobao.api.response.LogisticsTraceSearchResponse;

/**
 * @author PeggyGao
 * 
 */
public class LogisticsTOPClient extends BaseTOPClient {

	/*
	 * 目前沙箱不支持物流查询功能
	 */
	//查询某订单的物流信息
	public LogisticsInfo getLogisticsInfoByTid(Number tid, String sellerNick){
		
		TaobaoClient client=new DefaultTaobaoClient(this.inUseURL, this.appKey, this.appSecret);
		LogisticsTraceSearchRequest req=new LogisticsTraceSearchRequest();
		req.setTid(tid.longValue());
		req.setSellerNick(sellerNick);
		try {
			LogisticsTraceSearchResponse response = client.execute(req);
			Number outSid = Long.valueOf(response.getOutSid());
			
			LogisticsInfo info = new LogisticsInfo(response.getTraceList(),
					response.getCompanyName(), outSid);
			return info;
		} catch (ApiException e) {
			logger.error("taobao.itemats.get API调用错误", e);
		}
		return null;
	}
	
	
	//这里我偷懒写成内部类了
	//暂时变成public的内部类，方便测试，以后需要可以将该类移到类外面
	public class LogisticsInfo {
		// 物流流转信息
		List<TransitStepInfo> transitStepInfo;
		// 物流公司
		String company;
		// 物流单编号
		Number outId;

		LogisticsInfo(List<TransitStepInfo> tsi, String company, Number outid) {

			this.transitStepInfo = tsi;
			this.company = company;
			this.outId = outid;
		}

		public List<TransitStepInfo> getTransitStepInfo() {
			return transitStepInfo;
		}

		public void setTransitStepInfo(List<TransitStepInfo> transitStepInfo) {
			this.transitStepInfo = transitStepInfo;
		}

		public String getCompany() {
			return company;
		}

		public void setCompany(String company) {
			this.company = company;
		}

		public Number getOutId() {
			return outId;
		}

		public void setOutId(Number outId) {
			this.outId = outId;
		}
		
	}
	
}
