package com.taoHelper.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.taoHelper.service.TradeService;
import com.taobao.api.domain.Trade;

/**
 * Servlet implementation class CurrentTradeServlet
 */
@WebServlet("/CurrentTradeServlet")
public class CurrentTradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CurrentTradeServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain");
		
		TradeService ts = new TradeService();
		PrintWriter out = response.getWriter();
		
		String sessionKey = request.getParameter("sessionKey");
		List<Trade> curTradeList = ts.getCurrentTrades(sessionKey);
		JSONArray json = new JSONArray(curTradeList);
		out.println(json.toString());
		
		out.flush();
		out.close();
	}

}
