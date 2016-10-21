package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import utils.UserUtil;
import utilsImp.UserUtilImp;

import bean.User;

/**
 * Servlet implementation class UsersServlet
 */
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int from = Integer.parseInt(request.getParameter("from"));
		int count = Integer.parseInt(request.getParameter("count"));
		UserUtil us = new UserUtilImp();
		List<User> lists = new ArrayList<User>();
		try {
			lists = us.getUsers(count, from);
			System.out.println(lists.get(0).toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf8");
		response.setHeader("Access-Control-Allow-Origin","http://localhost:8080");
		JSONObject json = new JSONObject();
		json.put("data", lists);
		PrintWriter pw = response.getWriter();
		pw.append(json.toString());
		pw.flush();
		pw.close();
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
