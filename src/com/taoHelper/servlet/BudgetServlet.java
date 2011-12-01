package com.taoHelper.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.taoHelper.constants.ServletConstant;
import com.taoHelper.dataObject.Budget;
import com.taoHelper.service.BudgetService;

/**
 * Servlet implementation class BudgetServlet
 */
@WebServlet("/BudgetServlet")
public class BudgetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BudgetServlet() {
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
		
		BudgetService bs = new BudgetService();
		PrintWriter out = response.getWriter();
		
		String method=request.getParameter("method");
		if(method.equals("createBudget")){
			String name = request.getParameter("nick");
			double limit = Double.parseDouble(request.getParameter("limit"));
						
			if(bs.createBudget(name, limit)) out.print(ServletConstant.MSG_SUCCESS);
			else out.print(ServletConstant.MSG_FAIL);		
		}
		else if(method.equals("getBudget")){
			String name = request.getParameter("nick");
			
			Budget budget = bs.getBudgerByUserNick(name);
			JSONObject jo = new JSONObject(budget);
			out.println(jo.toString());		
		}
		out.flush();
		out.close();	
	}

}
