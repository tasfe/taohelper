/**
 * 
 */
package com.taoHelper.dataObject;

import java.util.HashMap;

/**
 * @author PeggyGao �û��Ĺ���ϲ�ã��û���ע����Ʒ���
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

	public HashMap<Number, Integer> getReferenceMap() {
		return referenceMap;
	}

	public void setReferenceMap(HashMap<Number, Integer> referenceMap) {
		this.referenceMap = referenceMap;
	}

}
