/**
 * 
 */
package com.taoHelper.TOPclient;

import java.util.List;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.SellerCat;
import com.taobao.api.request.SellercatsListGetRequest;
import com.taobao.api.response.SellercatsListGetResponse;

/**
 * @author PeggyGao
 *
 */
public class SellerCatTOPClient extends BaseTOPClient {
	
	//���������ǳƲ�ѯ������Ʒ��Ŀ
	public List<SellerCat> getSellerCatBySellerNick(String sellerNick){
		
		TaobaoClient client=new DefaultTaobaoClient(this.sandboxURl, this.appKey, this.appSecret);
		SellercatsListGetRequest req=new SellercatsListGetRequest();
		req.setNick(sellerNick);
		try {
			SellercatsListGetResponse response = client.execute(req);
			return response.getSellerCats();
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
