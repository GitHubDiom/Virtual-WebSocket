package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import bean.Group;
import sun.security.acl.GroupImpl;
import utils.GroupUtil;
import utilsImp.GroupUtilImp;

/**
 * Servlet implementation class AddGroup
 */
public class AddGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddGroup() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		response.setCharacterEncoding("utf8");
		String userPhone = request.getParameter("userPhone");
		String groupname = request.getParameter("groupName");
		System.out.println(userPhone+"  "+groupname);
		Group g = new Group();
		g.setGroupName(groupname);
		g.setUserPhone(userPhone);

		GroupUtil group = new GroupUtilImp();
		JSONObject json = new JSONObject();
		if (group.addGroup(g)) {
			json.put("flag", true);
		} else {
			json.put("flag", false);
		}
		
		response.setCharacterEncoding("utf8");
		PrintWriter pw = response.getWriter();
		pw.append(json.toString()); 
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
