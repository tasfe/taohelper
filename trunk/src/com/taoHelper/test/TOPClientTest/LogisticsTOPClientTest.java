package com.taoHelper.test.TOPClientTest;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.taoHelper.TOPclient.LogisticsTOPClient;
import com.taobao.api.domain.TransitStepInfo;

public class LogisticsTOPClientTest extends TestCase {

	/*
	 * 目前沙箱不支持物流查询功能，该代码在正式环境下测试通过
	 */
	@Test
	public void testGetLogisticsInfoByTid(){
		LogisticsTOPClient logisticsClient = new LogisticsTOPClient();
		LogisticsTOPClient.LogisticsInfo info = logisticsClient.getLogisticsInfoByTid(112306115355L, "alipublic02");
		
		if(info != null){
			System.out.println("物流公司："+info.getCompany());
			List<TransitStepInfo> transInfo = info.getTransitStepInfo();
			for(int i = 0; i < transInfo.size(); i++){
				System.out.println(transInfo.get(i).getStatusDesc());
			}
		}else{
			System.out.println("没有查到该订单的物流信息！");
		}
	}
}
