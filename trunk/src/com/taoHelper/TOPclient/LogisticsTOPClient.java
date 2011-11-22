/**
 * 
 */
package com.taoHelper.TOPclient;

import com.taobao.api.domain.TransitStepInfo;

/**
 * @author PeggyGao
 * 
 */
public class LogisticsTOPClient extends BaseTOPClient {

	
	//查询某订单的物流信息
	public LogisticsInfo getLogisticsInfoByTid(Number tid){
		return null;
		
	}
	
	
	//这里我偷懒写成内部类了
	class LogisticsInfo {
		// 物流流转信息
		TransitStepInfo transitStepInfo;
		// 物流公司
		String company;
		// 物流单编号
		Number outId;

		LogisticsInfo(TransitStepInfo tsi, String company, Number outid) {

			this.transitStepInfo = tsi;
			this.company = company;
			this.outId = outid;
		}

	}

}
