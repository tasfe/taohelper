package com.taoHelper.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.taoHelper.constants.ServletConstant;
import com.taoHelper.service.TradeService;

/**
 * Servlet implementation class MoneyInCatServlet
 */
@WebServlet("/MoneyInCatServlet")
public class MoneyInCatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoneyInCatServlet() {
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
		PrintWriter out = response.getWriter();
		
		String sessionKey = request.getParameter("sessionKey");
		TradeService ts = new TradeService();
		Map<String,Double> tmpMap = ts.getPaymentInCat(sessionKey);
		if(tmpMap != null){
			JSONObject jo= new JSONObject(tmpMap);
			out.println(jo.toString());
		}
		else out.print(ServletConstant.MSG_FAIL);
		out.flush();
		out.close();
	}

}
