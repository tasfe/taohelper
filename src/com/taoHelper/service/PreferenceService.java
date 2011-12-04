package com.taoHelper.service;

public class PreferenceService extends BaseService {
	public boolean addPreference(String userNick,String cid){
		if(null==cid || null == userNick) return false;
		return preDAO.createPreference(userNick, cid);
	}
}
