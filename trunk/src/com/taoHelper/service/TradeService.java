/**
 * 
 */
package com.taoHelper.service;

import java.util.List;
import java.util.Map;

import com.taobao.api.domain.Trade;

/**
 * @author PeggyGao
 * ����client��DAO����������ϴ���
 *
 */
public class TradeService extends BaseService {
	
		
	/*��õ�ǰ�����ж�����������
	 * ͨ������TradeTOPClient��getCurrentTrade��õ�ǰ�����ж�������Ϣ
	 * ͨ������LogisticsTOPClient��getLogisticsInfoByTid���ÿ�ʶ�����������Ϣ
	 */
	public List<Trade> getCurrentTrades(String sessionKey){
		
		return null;
		
	}
	
	/*���������ͳ��
	 * ͨ������TradeTOPClient��getPastTrades����ͳ�����ѽ��
	 */
	public Map<Integer,Double> getPaymentInMonth(String sessionKey){
		return null;
		
	}
	
	/*�����Ŀ����ͳ��
	 * ͨ������TradeTOPClient��getPastTrades�����ʷ����
	 * ͨ��ItemTOPClient�е�getItemByIdNumId���������Ʒ����Ŀid
	 * ͨ��ItemCatTOPClient�е�getItemCatByCid�����Ʒ��Ŀ
	 * ���ﶼʹ�ø���Ŀ
	 */
	public Map<String,Double> getPaymentInCat(String sessionKey){
		return null;
		
	}

}
