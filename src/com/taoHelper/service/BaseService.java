/**
 * 
 */
package com.taoHelper.service;

import org.apache.log4j.Logger;

import com.taoHelper.TOPclient.AuthorizeTOPClient;
import com.taoHelper.TOPclient.FavoriteTOPClient;
import com.taoHelper.TOPclient.ItemCatTOPClient;
import com.taoHelper.TOPclient.ItemTOPClient;
import com.taoHelper.TOPclient.LogisticsTOPClient;
import com.taoHelper.TOPclient.ShopTOPClient;
import com.taoHelper.TOPclient.TradeTOPClient;
import com.taoHelper.dao.BudgetDAO;
import com.taoHelper.dao.FavoriteItemDAO;
import com.taoHelper.dao.PreferenceDAO;

/**
 * @author PeggyGao
 * 
 */
public abstract class BaseService {
	protected static Logger logger = Logger.getLogger(BaseService.class);

	protected static TradeTOPClient tradeTopClient = new TradeTOPClient();
	protected static ItemTOPClient itemTopClient = new ItemTOPClient();
	protected static ItemCatTOPClient itemCatTopClient = new ItemCatTOPClient();
	protected static FavoriteTOPClient favoriteTopClient = new FavoriteTOPClient();
	protected static ShopTOPClient shopTopClient = new ShopTOPClient();
	protected static LogisticsTOPClient logisticsTOPClient = new LogisticsTOPClient();
	protected static AuthorizeTOPClient authoTOPClient = new AuthorizeTOPClient();

	protected static FavoriteItemDAO favoriteItemDAO = new FavoriteItemDAO();
	protected static BudgetDAO budgetDAO = new BudgetDAO();
	protected static PreferenceDAO preDAO = new PreferenceDAO();

}
