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

	
	//��ѯĳ������������Ϣ
	public LogisticsInfo getLogisticsInfoByTid(Number tid){
		return null;
		
	}
	
	
	//������͵��д���ڲ�����
	class LogisticsInfo {
		// ������ת��Ϣ
		TransitStepInfo transitStepInfo;
		// ������˾
		String company;
		// ���������
		Number outId;

		LogisticsInfo(TransitStepInfo tsi, String company, Number outid) {

			this.transitStepInfo = tsi;
			this.company = company;
			this.outId = outid;
		}

	}

}
