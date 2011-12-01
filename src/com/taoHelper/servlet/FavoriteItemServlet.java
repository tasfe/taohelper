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

import com.taoHelper.constants.ServeletConstant;
import com.taoHelper.service.FavoriteService;
import com.taobao.api.domain.Item;

/**
 * Servlet implementation class FavoriteItemServlet
 */
@WebServlet("/FavoriteItemServlet")
public class FavoriteItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FavoriteItemServlet() {
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
		
		String userNick = request.getParameter("nick");
		String sessionKey = request.getParameter("sessionKey");
		
		List<Item> fi_list = fs.getFavoriteItemByUserNick(sessionKey, userNick);
		if(fi_list==null){
			out.println(ServeletConstant.MSG_FAIL);
			return;
		}
		JSONArray ja = new JSONArray(fi_list);
		out.println(ja.toString());
		
		out.flush();
		out.close();
		
	}

}
