/**
 * 
 */
package com.taoHelper.TOPclient;

import java.util.List;

import com.taobao.api.domain.Shop;
import com.taobao.api.domain.TaobaokeShop;

/**
 * @author PeggyGao
 *
 */
public class ShopTOPClient extends BaseTOPClient {
	
	//����Ƽ��ĵ��̵���ʼ��ֹ���õȼ���������Ա������Լ���һ��
	private static String startCredit="5diamond" ;
	
	private static String endCredit="5goldencrown";
	
	//����sellerNick��ѯ����
	public List<Shop> getShopBySellerNick(String sellerNick){
		return null;
		
	}
	
	//������Ʒ�Ŀ�����õȼ�����Ԍ����ƏV�ĵ�����Ϣ
	public List<TaobaokeShop> getTaobaokeShopByCid(Number cid,String userNick){
		return null;
		
	}

}
