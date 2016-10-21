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
import utils.UserUtil;
import utilsImp.FriendImp;
import utilsImp.UserUtilImp;
import bean.User;

/**
 * Servlet implementation class RegisterServlet
 */
public class FriendsManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FriendsManage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String phone = request.getParameter("phone");
		String friendID = request.getParameter("friendID");
		
		User u = new User();
		u.setPhone(phone);
		u.setfriendID(friendID);
		
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				String phone = request.getParameter("phone");
				String friendID = request.getParameter("friendID");
				
				User u = new User();
				u.setPhone(phone);
				u.setfriendID(friendID);
				
				FriendUtil us = new FriendImp();
				JSONObject json = new JSONObject();

				response.setCharacterEncoding("utf8");
				response.setHeader("Access-Control-Allow-Origin","http://localhost:8080");
				PrintWriter pw = response.getWriter();
				pw.append(json.toString());
				pw.flush();
				pw.close();
				
			}

		}
