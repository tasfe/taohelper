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
	
	//获得推荐的店铺的起始终止信用等级，开发人员看情况自己定一下
	private static String startCredit="5diamond" ;
	
	private static String endCredit="5goldencrown";
	
	//根据sellerNick查询店铺
	public List<Shop> getShopBySellerNick(String sellerNick){
		return null;
		
	}
	
	//根據商品類目和信用等级获得淘寶客推廣的店铺信息
	public List<TaobaokeShop> getTaobaokeShopByCid(Number cid,String userNick){
		return null;
		
	}

}
