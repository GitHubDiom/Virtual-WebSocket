package utils.websocketutls;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.ws.Response;

import org.apache.commons.lang.ObjectUtils.Null;

public class WebSocketMessageInboundPool {

	// �������ӵ�MAP���� ������Ǳ��浱ǰ�û��ĵط�
	private static final Map<String, WebSocketMessageInbound> connections = new HashMap<String, WebSocketMessageInbound>();

	// �����ӳ����������
	public static void addMessageInbound(WebSocketMessageInbound inbound) {
		// ������Ӳ�����Ƿ��е�¼
		System.out.println("user : " + inbound.getUser() + " join..");
		connections.put(inbound.getUser(), inbound);
		
		
	}

	// ��ȡ���е������û�
	public static Set<String> getOnlineUser() {
		return connections.keySet();
	}

	public static void removeMessageInbound(WebSocketMessageInbound inbound) {
		// �Ƴ�����
		System.out.println("user : " + inbound.getUser() + " exit..");
		connections.remove(inbound.getUser());
	}

	public static void sendMessageToUser(String user, String message) {
		try {
			// ���ض����û���������
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
			// ���ض����û���������
			System.out.println("--------------------sendMessageToUser------------------");
			System.out.println(fromUser + " send message to user : " + touser + " ,message content : " + message);
			touser = "'" + touser + "'";
			System.out.println("�����û�" + connections.keySet());
			WebSocketMessageInbound inbound = connections.get(touser);
			System.out.println("touser:" + touser + "\nfromUser:" + fromUser);
			WebSocketMessageInbound inbound2 = connections.get(fromUser);
			 System.out.println("inbound2��"+inbound2);
			String msg = "�û�"+fromUser + "����˵�� " + message;
			String msg2 = "����û�" + touser + "˵: " + message;
			if (inbound != null) {
				// inbound.getWsOutbound().writeTextMessage(CharBuffer.wrap(message));
				System.out.println("�Է����ߣ���");
				inbound.getWsOutbound().writeTextMessage(CharBuffer.wrap(msg));
				inbound2.getWsOutbound().writeTextMessage(CharBuffer.wrap(msg2));
			} else {
				System.out.println("�Է������ߣ���");
				utilsImp.message m = new utilsImp.message();
				m.addMessage(fromUser, touser, message);
				inbound2.getWsOutbound().writeTextMessage(CharBuffer.wrap(msg2));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// �����е��û�������Ϣ
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