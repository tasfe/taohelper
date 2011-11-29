package com.taoHelper.service;

public class AuthorizeService extends BaseService{
	
	public String getAuthorizeURL(){
		try{
			String url = authoTOPClient.getidentifyURL();
			return url;
		}
		catch(Exception e){
			return null;
		}	
	}
	

}
