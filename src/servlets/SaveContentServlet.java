package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import utils.SaveContentUtil;
import utils.UserUtil;
import utilsImp.SaveContentImp;
import utilsImp.UserUtilImp;
import bean.Content;
import bean.User;

/**
 * Servlet implementation class RegisterServlet
 */
public class SaveContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveContentServlet() {
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
		response.setCharacterEncoding("utf8");
		String toUser = request.getParameter("towho");
		String fromUser = request.getParameter("fromewho");
		String message = request.getParameter("message");
		System.out.println("-------------SaveContentServlet--------Post");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String datetime = sdf.format(new java.util.Date());	
		System.out.println("toUser£º"+toUser+"\nfromUser:"+fromUser+"\nmessage:"+message+"\ndatetime:"+datetime);
//		User user = new User(name, pwd, email,phone);
		Content content=new Content(fromUser,toUser,message,datetime);
		SaveContentUtil save = new SaveContentImp();
		JSONObject json = new JSONObject();
		try {
			save.addMessage(fromUser, toUser, message, datetime);
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
