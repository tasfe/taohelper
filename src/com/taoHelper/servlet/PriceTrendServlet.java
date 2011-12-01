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

import com.taoHelper.dataObject.FavoriteItem;
import com.taoHelper.service.FavoriteService;

/**
 * Servlet implementation class PriceTrendServlet
 */
@WebServlet("/PriceTrendServlet")
public class PriceTrendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PriceTrendServlet() {
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
		
		FavoriteService fs = new FavoriteService();
		PrintWriter out = response.getWriter();

		String userNick = request.getParameter("userNick");
		String sessionKey = request.getParameter("sessionKey");
		List<FavoriteItem> favorList = fs.getFavoritePriceByUserNick(sessionKey, userNick);
		JSONArray json = new JSONArray(favorList);
		out.println(json.toString());
		
		out.flush();
		out.close();
	}

}
