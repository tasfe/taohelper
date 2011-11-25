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
	
	//获得推荐的店铺的起始终止信用等级，开发人员看情况自己定一下
	private static String startCredit="5diamond" ;
	
	private static String endCredit="5goldencrown";
	
	//根据sellerNick查询店铺
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
	
	//根據商品類目和信用等级获得淘寶客推廣的店铺信息
	public List<TaobaokeShop> getTaobaokeShopByCid(Number cid,String userNick){
		System.err.println("淘客API为增值业务，TODO");
		return null;
	}

}
