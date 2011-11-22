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
	
	protected String appKey = "test";
	protected String appSecret = "test";

}
