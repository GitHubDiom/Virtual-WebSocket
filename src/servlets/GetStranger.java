package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
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
 * 
 * Servlet implementation class GetStranger
 */
public class GetStranger extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see 寰楀埌闄岀敓浜�
     * @see HttpServlet#HttpServlet()
     */
    public GetStranger() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userPhone =  request.getParameter("userPhone");
		FriendImp fimp = new FriendImp();
		User u = new User();
		u.setPhone(userPhone);
	    List<User> userList = fimp.showStranger(u);
		
		JSONObject json = new JSONObject();
		if (userList.size() > 0) {
			json.put("data", userList);
			for (User user : userList) {
				System.out.println(user.toString());
			}
		}
		response.setCharacterEncoding("utf8");
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	
	}

}
