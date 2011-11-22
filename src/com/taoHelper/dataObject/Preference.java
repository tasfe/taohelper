/**
 * 
 */
package com.taoHelper.dataObject;

import java.util.HashMap;

/**
 * @author PeggyGao 用户的购物喜好，用户关注的商品类别
 */
public class Preference extends BaseDO {

	private String userNick;

	private HashMap<Number, Integer> preferenceMap = new HashMap<Number, Integer>();

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public HashMap<Number, Integer> getPreferenceMap() {
		return preferenceMap;
	}

	public void setPreferenceMap(HashMap<Number, Integer> preferenceMap) {
		this.preferenceMap = preferenceMap;
	}

}
