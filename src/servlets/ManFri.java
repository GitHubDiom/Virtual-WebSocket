package servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class ManFri
 */
public class ManFri extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManFri() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf8");
		String phone = request.getParameter("userPhone");
		User u = new User();
		u.setPhone(phone);
		FriendUtil friUtil = new FriendImp();
		List<Group> group =  friUtil.selectGroup(u);
		List<Friend> friend_list =  friUtil.getFriends(u);
		
		
		JSONObject json = new JSONObject();
		Group g0 = new Group();
		g0.setGroupID(0);
		g0.setGroupName("我的好友");
		group.add(0,g0);
		
		json.put("group", group);
		json.put("friend", friend_list);
		json.put("flag", true);

		for(Group tempGroup : group){
			System.out.println("选择分组"+tempGroup);
		}
		
		
		//下面是构建数据
		response.setCharacterEncoding("utf8");
		PrintWriter pw = response.getWriter();
		pw.append(json.toString()); // 构建json数据
		pw.flush();
		
		try {
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pw.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf8");
		String userPhone = request.getParameter("userPhone");
		String friend = request.getParameter("friend");
		String group = request.getParameter("group");
		
		System.out.println(userPhone+"--"+friend+"--"+group);
		FriendUtil friUtil = new FriendImp();
		boolean flag = friUtil.manFriend(userPhone, friend, group);
		
		
		JSONObject json = new JSONObject();
		if(flag){
			json.put("flag", true);
		}else{
			json.put("flag", false);
		}
		
		
		//下面是构建数据
		response.setCharacterEncoding("utf8");
		PrintWriter pw = response.getWriter();
		pw.append(json.toString()); // 构建json数据
		pw.flush();
		
		try {
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pw.close();
		}
		
	}

}
