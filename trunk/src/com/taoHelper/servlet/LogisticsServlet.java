package com.taoHelper.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.taoHelper.TOPclient.LogisticsTOPClient.LogisticsInfo;
import com.taoHelper.service.TradeService;

/**
 * Servlet implementation class LogisticsServlet
 */
@WebServlet("/LogisticsServlet")
public class LogisticsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogisticsServlet() {
        super();
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
		
		String tid = request.getParameter("tid");
		String sellerNick = request.getParameter("sellerNick");
		
		LogisticsInfo lInfo = ts.getCurrentLogisticsInfo(Long.valueOf(tid), sellerNick);
		JSONObject jo = new JSONObject(lInfo);
		out.println(jo.toString());
		
		out.flush();
		out.close();
	}

}
