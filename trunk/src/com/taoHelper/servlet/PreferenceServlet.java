package com.taoHelper.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taoHelper.constants.ServletConstant;
import com.taoHelper.service.PreferenceService;

/**
 * Servlet implementation class PreferenceServlet
 */
@WebServlet("/PreferenceServlet")
public class PreferenceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreferenceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		
		String userNick = request.getParameter("userNick");
		String cid = request.getParameter("cid");
		PreferenceService ps = new PreferenceService();
		if(ps.addPreference(userNick, cid)){
			out.print(ServletConstant.MSG_SUCCESS);
		}
		else out.print(ServletConstant.MSG_FAIL);
		
		out.flush();
		out.close();
	}

}
