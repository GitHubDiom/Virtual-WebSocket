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
import bean.Friend;
import bean.Group;
import bean.User;
import utils.FriendUtil;
import utilsImp.FriendImp;

/**
 * Servlet implementation class GetFriends
 */
public class GetFriends extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetFriends() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf8");
		// TODO Auto-generated method stub
		FriendUtil fu = new FriendImp();
		String phone = request.getParameter("phone");
		System.out.println("---------------------GetFriend  Get----------------------");
		User us = new User();
		us.setPhone(phone);		
		System.out.println(us.toString());
		JSONObject json = new JSONObject();
		List<Friend> friend = new ArrayList<Friend>();
		//得到用户的所有分组
		List<Group> groupList = fu.selectGroup(us);
		Group g0 = new Group();
		g0.setGroupID(0);
		//把默认列表0 add进去
		friend.addAll(fu.getGroupFri(g0.getGroupID(), us));
		//利用分组进行查询，如果用老师最新的sql代码可以省去这一步 直接获取即可
		if(groupList.size()>0){
			json.put("group", groupList);
			for(Group gg: groupList){
				//把得到的好友添加到需要返回给页面的好友列表
				friend.addAll(fu.getGroupFri(gg.getGroupID(), us));
			}
		}
		if (friend.size() > 0) {
			json.put("data", friend);
			for (Friend f : friend) {
				System.out.println("得到的朋友的信息"+f.toString());
			}
		}
		response.setCharacterEncoding("utf-8");
		PrintWriter pw = response.getWriter();
		pw.append(json.toString()); //构建json数据
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
		
		String phone = request.getParameter("findfriendID");
		User user = new User();
		user.setPhone(phone);
		FriendUtil us = new FriendImp();
		JSONObject json = new JSONObject();
		try {
			User u = us.searchFriend(user);
			if (u != null) {
				json.put("flag", true);
				json.put("userName", u.getUserName());
				json.put("phone", u.getPhone());
				json.put("email", u.getEmail());
			}else{
				json.put("flag", false);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.put("flag", false);
		}
		response.setCharacterEncoding("utf8");
		response.setHeader("Access-Control-Allow-Origin",
				"http://localhost:8080");
		PrintWriter pw = response.getWriter();
		pw.append(json.toString());
		pw.flush();
		pw.close();
		
		
	}
}
