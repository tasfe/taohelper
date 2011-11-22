/**
 * 
 */
package com.taoHelper.service;

import org.apache.log4j.Logger;

import com.taoHelper.TOPclient.FavoriteTOPClient;
import com.taoHelper.TOPclient.ItemCatTOPClient;
import com.taoHelper.TOPclient.ItemTOPClient;
import com.taoHelper.TOPclient.ShopTOPClient;
import com.taoHelper.TOPclient.TradeTOPClient;


/**
 * @author PeggyGao
 *
 */
public abstract class BaseService {
	protected static Logger logger = Logger.getLogger(BaseService.class);
	
	protected TradeTOPClient tradeTopClient = new TradeTOPClient();
	protected ItemTOPClient itemTopClient = new ItemTOPClient();
	protected ItemCatTOPClient itemCatClient = new ItemCatTOPClient(); 
	protected FavoriteTOPClient favoriteTopClient = new FavoriteTOPClient();
	protected ShopTOPClient shopTopClient = new ShopTOPClient();
	
}
