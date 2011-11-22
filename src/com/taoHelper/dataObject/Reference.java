/**
 * 
 */
package com.taoHelper.dataObject;

import java.util.HashMap;

/**
 * @author PeggyGao 用户的购物喜好，用户关注的商品类别
 */
public class Reference extends BaseDO {

	private String userNick;

	private HashMap<Number, Integer> referenceMap = new HashMap<Number, Integer>();

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public HashMap<Number, Integer> getReferenceMap() {
		return referenceMap;
	}

	public void setReferenceMap(HashMap<Number, Integer> referenceMap) {
		this.referenceMap = referenceMap;
	}

}
