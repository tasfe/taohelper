package com.taoHelper.test.TOPClientTest;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.taoHelper.TOPclient.LogisticsTOPClient;
import com.taobao.api.domain.TransitStepInfo;

public class LogisticsTOPClientTest extends TestCase {

	/*
	 * Ŀǰɳ�䲻֧��������ѯ���ܣ��ô�������ʽ�����²���ͨ��
	 */
	@Test
	public void testGetLogisticsInfoByTid(){
		LogisticsTOPClient logisticsClient = new LogisticsTOPClient();
		LogisticsTOPClient.LogisticsInfo info = logisticsClient.getLogisticsInfoByTid(112306115355L, "alipublic02");
		
		if(info != null){
			System.out.println("������˾��"+info.getCompany());
			List<TransitStepInfo> transInfo = info.getTransitStepInfo();
			for(int i = 0; i < transInfo.size(); i++){
				System.out.println(transInfo.get(i).getStatusDesc());
			}
		}else{
			System.out.println("û�в鵽�ö�����������Ϣ��");
		}
	}
}
