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
	 * Ŀǰɳ�䲻֧�������ѯ����
	 */
	//��ѯĳ������������Ϣ
	public LogisticsInfo getLogisticsInfoByTid(Number tid, String sellerNick){
		
		TaobaoClient client=new DefaultTaobaoClient(this.inUseURL, this.appKey, this.appSecret);
		LogisticsTraceSearchRequest req=new LogisticsTraceSearchRequest();
		req.setTid(tid.longValue());
		req.setSellerNick(sellerNick);
		try {
			LogisticsTraceSearchResponse response = client.execute(req);
			if(response.getOutSid()==null)  return null;
			Number outSid = Long.valueOf(response.getOutSid());
			
			LogisticsInfo info = new LogisticsInfo(response.getTraceList(),
					response.getCompanyName(), outSid);
			return info;
		} catch (ApiException e) {
			logger.error("taobao.itemats.get API���ô���", e);
		}
		return null;
	}
	
	
	//������͵��д���ڲ�����
	//��ʱ���public���ڲ��࣬������ԣ��Ժ���Ҫ���Խ������Ƶ�������
	public class LogisticsInfo {
		// ������ת��Ϣ
		List<TransitStepInfo> transitStepInfo;
		// ����˾
		String company;
		// ������
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
