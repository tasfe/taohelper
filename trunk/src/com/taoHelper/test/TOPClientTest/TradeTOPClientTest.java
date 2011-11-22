/**
 * 
 */
package com.taoHelper.test.TOPClientTest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.taoHelper.TOPclient.TradeTOPClient;
import com.taobao.api.domain.Trade;

/**
 * @author PeggyGao
 * 
 */
public class TradeTOPClientTest extends TestCase {

	private String sessionKey;

	
	@Before
	public void setUp() throws Exception {

		URL url = new URL(
				"http://container.api.tbsandbox.com/container?appkey=test&encode=utf-8");

		HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
        httpConnection.setRequestMethod("POST"); 
        //httpConnection.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows 7)"); 
 
			
			InputStream urlStream = httpConnection.getInputStream();  
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlStream));  
			String sCurrentLine = "";  
			//String sTotalString = "";  
			while ((sCurrentLine = bufferedReader.readLine()) != null) {  
			//   System.out.println(sCurrentLine);  
			}  
		
	}

	@Test
	public void testGetCurrentTrades() {

		TradeTOPClient  tradeClient = new TradeTOPClient();
		List<Trade> list = tradeClient.getCurrentTrades("4112132021f6426741c983376694dce9e41e24d8AypfTd51757543691");

		for(Trade t: list){
		System.out.println(t.getPayment()+"    "+t.getTitle()+"  "+t.getStatus());
		}
	}

}
