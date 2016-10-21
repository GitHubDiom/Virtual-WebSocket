package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import utils.FriendUtil;
import utilsImp.FriendImp;
import bean.User;

/**
 * Servlet implementation class DeleteFriends
 */
public class DeleteFriends extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteFriends() {
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
		String DeletePhone = request.getParameter("DeletePhone");
		String UserPhone = request.getParameter("UserPhone");
		System.out.println("----------------DeleteFriends-------------");
		User u = new User();
		u.setPhone(UserPhone);
		u.setfriendID(DeletePhone);		
		FriendUtil us = new FriendImp();
		JSONObject json = new JSONObject();
		try {
			us.deleteFriend(u);
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
