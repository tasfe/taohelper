/**
 * 
 */
package com.taoHelper.TOPclient;

import java.util.List;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.Shop;
import com.taobao.api.domain.TaobaokeShop;
import com.taobao.api.request.ShopGetRequest;
import com.taobao.api.response.ShopGetResponse;

/**
 * @author PeggyGao
 *
 */
public class ShopTOPClient extends BaseTOPClient {
	
	//����Ƽ��ĵ��̵���ʼ��ֹ���õȼ���������Ա������Լ���һ��
	private static String startCredit="5diamond" ;
	
	private static String endCredit="5goldencrown";
	
	//����sellerNick��ѯ����
	public Shop getShopBySellerNick(String sellerNick){
		
		TaobaoClient client=new DefaultTaobaoClient(this.sandboxURl, this.appKey, this.appSecret);
		ShopGetRequest req=new ShopGetRequest();
		req.setFields("sid,cid,title,nick,desc,bulletin,pic_path,created,modified");
		req.setNick(sellerNick);
		try {
			ShopGetResponse response = client.execute(req);
			return response.getShop();
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//������Ʒ�Ŀ�����õȼ�����Ԍ����ƏV�ĵ�����Ϣ
	public List<TaobaokeShop> getTaobaokeShopByCid(Number cid,String userNick){
		System.err.println("�Կ�APIΪ��ֵҵ��TODO");
		return null;
	}

}
