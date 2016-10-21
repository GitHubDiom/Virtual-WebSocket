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
 * Servlet implementation class upGradeServlet
 */
public class upGradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public upGradeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.err.println("hello");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String phone = request.getParameter("phone");
		String uerName = request.getParameter("uerName");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		
		System.out.println( phone+"-------"+uerName + "-----" + pwd + "------" + email);
	
		User user = new User(uerName, pwd, email, phone);
		UserUtil us = new UserUtilImp();
		JSONObject json = new JSONObject();
		
		try {
			if(us.updateUser(user)){
				json.put("flag", true);
			}else{
				json.put("flag", false);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PrintWriter pw = response.getWriter();
		pw.append(json.toString());
		System.out.println(json.toString());
		pw.flush();
		pw.close();

	}
}
