package servlets;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import java.awt.peer.SystemTrayPeer;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;

import utils.websocketutls.WebSocketMessageInbound;
@WebServlet(urlPatterns = { "/message"})  
public class WebSocketMessageServlet extends WebSocketServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int ONLINE_USER_COUNT = 1;

	public String getUser(HttpServletRequest request) {
		return (String) request.getSession().getAttribute("user");
	}

	// 跟平常Servlet不同的是，需要实现createWebSocketInbound，在这里初始化自定义的WebSocket连接对象
	@Override
	protected StreamInbound createWebSocketInbound(String subProtocol,	HttpServletRequest request){
		
		String nametemp = request.getParameter("name");
		String name;
		try {
			System.out.println("MessageServlet.createWebSocketInbound");
			System.out.println("Try");
			name = new String(nametemp.getBytes("ISO-8859-1"),"UTF-8");
			return new WebSocketMessageInbound("'"+name+"'");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block	
			e.printStackTrace();
		}
		System.out.println("??");
//		System.out.println(name);
//		return new WebSocketMessageInbound(this.getUser(request));
		return null;
	
	}

}
