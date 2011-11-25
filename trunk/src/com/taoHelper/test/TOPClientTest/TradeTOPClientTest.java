/**
 * 
 */
package com.taoHelper.test.TOPClientTest;

import java.io.BufferedReader;
import java.io.IOException;
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
				"http://container.open.taobao.com/container?appkey=12391726&encode=utf-8");
				//"http://container.api.tbsandbox.com/container?appkey=12391726&encode=utf-8");
		

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

	/*
	 * ������TradeTOPClient�е�getCurrentTrades����,��Ҫ�û��ڿ���̨����sessionKey
	 * 
	 * ��λ��sessionKey?
	 * 1. �����������http://container.api.tbsandbox.com/container?appkey=test
	 * 2. ����ص�URL��дΪ��http://localhost:8080/index.jsp
	 * http://localhost/?top_appkey={appkey} &top_parameters=xxx&top_session=xxx&top_sign=xxx
	 * �ص�url�ϵ�top_session��ΪSessionKey
	 */
	@Test
	public void testGetCurrentTrades() {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("Please input the sessionKey: ");
			String sessionKey = br.readLine();
			TradeTOPClient  tradeClient = new TradeTOPClient();
			List<Trade> list = tradeClient.getCurrentTrades(sessionKey);
			
			for(Trade t: list){
				System.out.println(t.getPayment()+"    "+t.getTitle()+"  "+t.getStatus());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * ������TradeTOPClient�е�getPastTrades����,��Ҫ�û��ڿ���̨����sessionKey
	 * 
	 * ��λ��sessionKey?
	 * 1. �����������http://container.api.tbsandbox.com/container?appkey=test
	 * 2. ����ص�URL��дΪ��http://localhost:8080/index.jsp
	 * http://localhost/?top_appkey={appkey} &top_parameters=xxx&top_session=xxx&top_sign=xxx
	 * �ص�url�ϵ�top_session��ΪSessionKey
	 */
	@Test
	public void testGetPastTrades() {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("Please input the sessionKey: ");
			String sessionKey = br.readLine();
			TradeTOPClient  tradeClient = new TradeTOPClient();
			List<Trade> list = tradeClient.getPastTrades(sessionKey);

			for(Trade t: list){
				System.out.println(t.getPayment()+"    "+t.getTitle()+"  "+t.getStatus());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
