package com.taoHelper.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taoHelper.constants.ServeletConstant;
import com.taoHelper.service.AuthorizeService;

/**
 * Servlet implementation class AuthorizeServlet
 */
@WebServlet("/AuthorizeServlet")
public class AuthorizeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthorizeServlet() {
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
		
		AuthorizeService as = new AuthorizeService();
		PrintWriter out = response.getWriter();
		String url = as.getAuthorizeURL();
		if(url!=null) out.println(url);
		else out.println(ServeletConstant.MSG_FAIL);
		
		out.flush();
		out.close();
	}
	
	

}
