/**
 * 
 */
package com.taoHelper.TOPclient;

import org.apache.log4j.Logger;

/**
 * @author PeggyGao
 * 
 */
abstract class BaseTOPClient {

	protected static Logger logger = Logger.getLogger(BaseTOPClient.class);

	// sandbox url
	protected String sandboxURl = "http://gw.api.tbsandbox.com/router/rest";
	
	//online url
	protected String onLineURL = "http://gw.api.taobao.com/router/rest";
	
	//current in use url
	protected String inUseURL =onLineURL;
	
	protected String appKey = "12390550";
	protected String appSecret = "05ba44f4751e847cc76ffd3977b0f0b7";
}
