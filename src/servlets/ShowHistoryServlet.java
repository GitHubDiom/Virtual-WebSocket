package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import bean.Content;
import bean.Friend;
import bean.User;
import utils.FriendUtil;
import utils.SaveContentUtil;
import utilsImp.FriendImp;
import utilsImp.SaveContentImp;

/**
 * Servlet implementation class ManFri
 */
public class ShowHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowHistoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.setCharacterEncoding("utf8");
		String FromUserIdTemp = request.getParameter("FromUserId");
		String FromUserId = new String(FromUserIdTemp.getBytes("ISO-8859-1"),"UTF-8");
		
		String UserPhone = request.getParameter("UserPhone");
		
		String ToUserTemp=request.getParameter("ToUserId");
		String ToUser = new String(ToUserTemp.getBytes("ISO-8859-1"),"UTF-8");
		
		System.out.println("-------------ShowHistoryServlet--------Get");
		User u = new User();
		u.setPhone(UserPhone);
		Content content=new Content(FromUserId);
		SaveContentUtil message = new SaveContentImp();
		JSONObject json = new JSONObject();
		FriendUtil friUtil = new FriendImp();
		
//		List<Content> Content =SaveContentUtil
		List<Friend> friend_list =  friUtil.getFriends(u);
		
		try {
			List<Content> messageInfo_list=message.GetMessage(FromUserId,ToUser);
			System.out.println("--------------messageInfo_list------------\n"+messageInfo_list);
			json.put("friend_list",friend_list);
			json.put("message_list",messageInfo_list);
			json.put("flag", true);
			
		} catch (SQLException e) {
			e.printStackTrace();
			json.put("flag", false);
		}
		
//		json.put("group", group);
		

//		for(Group tempGroup : group){
//			System.out.println("选择分组"+tempGroup);
//		}
		
		
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
	
		
	}

}
