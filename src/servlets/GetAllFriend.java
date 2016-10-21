package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import bean.Friend;
import bean.User;

import utils.FriendUtil;
import utilsImp.FriendImp;

/**
 * Servlet implementation class GetFriends
 */
public class GetAllFriend extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetAllFriend() {
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
		response.setCharacterEncoding("utf8");
		FriendUtil fu = new FriendImp();
		String phone = request.getParameter("phone");
		User us = new User();
		us.setPhone(phone);
		System.out.println(us.toString());
		JSONObject json = new JSONObject();
		List<Friend> friends = fu.getFriends(us);
		if (friends.size() > 0) {
			//System.out.println("friends.size() > 0-=-------------------------------\n");
			json.put("data", friends);
			for (Friend f : friends) {
				System.out.println(f.toString());
			}
		}
		PrintWriter pw = response.getWriter();
		pw.append(json.toString());
		pw.flush();
		
		try {
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			pw.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
