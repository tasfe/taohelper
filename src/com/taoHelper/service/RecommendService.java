/**
 * 
 */
package com.taoHelper.service;

import java.util.List;

import com.taobao.api.domain.TaobaokeShop;

/**
 * @author PeggyGao
 *
 */
public class RecommendService extends BaseService {
	
	
	/*根据类别和用户昵称搜索淘宝客推荐店铺
	 * 通过ReferenceDAO获得用户购物偏好cid
	 * 通过ShopTOPClient中getTaobaokeShopByCid方法获得某类目下的推广店铺
	 * 通过ShopTOPClient中的getShopBySellerNick方法获得相应店铺具体信息
	 * 根据店铺分数进行推荐
	 */
	public List<TaobaokeShop> getRecommondShops(String userNick){
		return null;
		
	}

}
