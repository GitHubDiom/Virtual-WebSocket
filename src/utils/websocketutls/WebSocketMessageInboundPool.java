package utils.websocketutls;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.ws.Response;

import org.apache.commons.lang.ObjectUtils.Null;

public class WebSocketMessageInboundPool {

	// 保存连接的MAP容器 这个就是保存当前用户的地方
	private static final Map<String, WebSocketMessageInbound> connections = new HashMap<String, WebSocketMessageInbound>();

	// 向连接池中添加连接
	public static void addMessageInbound(WebSocketMessageInbound inbound) {
		// 添加连接并检测是否有登录
		System.out.println("user : " + inbound.getUser() + " join..");
		connections.put(inbound.getUser(), inbound);
		
		
	}

	// 获取所有的在线用户
	public static Set<String> getOnlineUser() {
		return connections.keySet();
	}

	public static void removeMessageInbound(WebSocketMessageInbound inbound) {
		// 移除连接
		System.out.println("user : " + inbound.getUser() + " exit..");
		connections.remove(inbound.getUser());
	}

	public static void sendMessageToUser(String user, String message) {
		try {
			// 向特定的用户发送数据
			System.out.println("send message to user : " + user + " ,message content : " + message);
			WebSocketMessageInbound inbound = connections.get(user);
			if (inbound != null) {
				inbound.getWsOutbound().writeTextMessage(CharBuffer.wrap(message));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void sendMessageToUser(String fromUser, String touser, String message) {
		try {
			// 向特定的用户发送数据
			System.out.println("--------------------sendMessageToUser------------------");
			System.out.println(fromUser + " send message to user : " + touser + " ,message content : " + message);
			touser = "'" + touser + "'";
			System.out.println("在线用户" + connections.keySet());
			WebSocketMessageInbound inbound = connections.get(touser);
			System.out.println("touser:" + touser + "\nfromUser:" + fromUser);
			WebSocketMessageInbound inbound2 = connections.get(fromUser);
			 System.out.println("inbound2："+inbound2);
			String msg = "用户"+fromUser + "对你说： " + message;
			String msg2 = "你对用户" + touser + "说: " + message;
			if (inbound != null) {
				// inbound.getWsOutbound().writeTextMessage(CharBuffer.wrap(message));
				System.out.println("对方在线！！");
				inbound.getWsOutbound().writeTextMessage(CharBuffer.wrap(msg));
				inbound2.getWsOutbound().writeTextMessage(CharBuffer.wrap(msg2));
			} else {
				System.out.println("对方不在线！！");
				utilsImp.message m = new utilsImp.message();
				m.addMessage(fromUser, touser, message);
				inbound2.getWsOutbound().writeTextMessage(CharBuffer.wrap(msg2));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 向所有的用户发送消息
	public static void sendMessage(String message) {
		try {
			Set<String> keySet = connections.keySet();
			for (String key : keySet) {
				WebSocketMessageInbound inbound = connections.get(key);
				if (inbound != null) {
					System.out.println("send message to user : " + key + " ,message content : " + message);
					inbound.getWsOutbound().writeTextMessage(CharBuffer.wrap(message));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}