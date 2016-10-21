package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import utils.UserUtil;
import utilsImp.UserUtilImp;

import bean.User;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		String phone = request.getParameter("phone");
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		
		User user = new User(name, pwd, email,phone);
		UserUtil us = new UserUtilImp();
		JSONObject json = new JSONObject();
		try {
			us.addUser(user);
			json.put("flag", true);
			
		} catch (SQLException e) {
			e.printStackTrace();
			json.put("flag", false);
		}
		response.setCharacterEncoding("utf8");
		response.setHeader("Access-Control-Allow-Origin","http://localhost:8080");
		PrintWriter pw = response.getWriter();
		pw.append(json.toString());
		pw.flush();
		pw.close();
		
	}

}
