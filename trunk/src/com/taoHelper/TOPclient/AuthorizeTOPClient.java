package com.taoHelper.TOPclient;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class AuthorizeTOPClient extends BaseTOPClient {
	
	/**
	* 使用淘宝帐号登录
	* @return
	* @throws UnsupportedEncodingException
	*/
	private String identifyparams() throws UnsupportedEncodingException {
		TreeMap<String, String> apiparamsMap = new TreeMap<String, String>();
		// 组装协议参数。
		apiparamsMap.put("sign_method", "md5");
		apiparamsMap.put("app_key", appKey);
		apiparamsMap.put("timestamp", new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss").format(new Date()));
		String sign = Util.sign(apiparamsMap, appSecret);
		// 组装协议参数sign
		apiparamsMap.put("sign", sign);
		StringBuilder param = new StringBuilder();
		for (Iterator<Map.Entry<String, String>> it = apiparamsMap.entrySet()
		.iterator(); it.hasNext();) {
			Map.Entry<String, String> e = it.next();
			param.append("&").append(e.getKey()).append("=").append(
			e.getValue());
		}
		return param.toString().substring(1);
	}
	
	/*
	* 获取app使用淘宝帐号登录URL
	*/
	public String getidentifyURL() throws UnsupportedEncodingException {
	// 组装请求URL
		StringBuilder url = new StringBuilder("http://container.api.taobao.com/container/identify?");
		url.append(identifyparams());
		return url.toString();
	}

}
