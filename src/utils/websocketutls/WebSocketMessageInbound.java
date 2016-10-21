package utils.websocketutls;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.List;

import net.sf.json.JSONObject;
import utils.MessageUtil;
import utilsImp.message;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WsOutbound;

import bean.Record;

public class WebSocketMessageInbound extends MessageInbound {

	// 当前连接的用户名称
	private final String user;

	public WebSocketMessageInbound(String user) {
		this.user = user;
	}

	public String getUser() {
		return this.user;
	}

	// 建立连接的触发的事件
	@Override
	protected void onOpen(WsOutbound outbound) {
		// 触发连接事件，在连接池中添加连接
		JSONObject result = new JSONObject();
		result.element("type", "user_join");
		result.element("user", this.user);
		// 向所有在线用户推送当前用户上线的消息
		WebSocketMessageInboundPool.sendMessage(result.toString());

		result = new JSONObject();
		result.element("type", "get_online_user");
		result.element("list", WebSocketMessageInboundPool.getOnlineUser());
		// 向连接池添加当前的连接对象
		WebSocketMessageInboundPool.addMessageInbound(this);
		// 向当前连接发送当前在线用户的列表
		WebSocketMessageInboundPool.sendMessageToUser(this.user,result.toString());
		MessageUtil m = new message();
		List<Record> list =  m.readMessage(this.user);
		
		for(Record tempRecord:list){
			String msg = "来自"+tempRecord.getFromeuser()+"的离线消息：<br/>"+tempRecord.getTyper();
			try {
				this.getWsOutbound().writeTextMessage(CharBuffer.wrap(msg));
				m.deleteMsg(this.user);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		}
		
	    
		
		
	}

	@Override
	protected void onClose(int status) {
		// 触发关闭事件，在连接池中移除连接
		WebSocketMessageInboundPool.removeMessageInbound(this);
		JSONObject result = new JSONObject();
		result.element("type", "user_leave");
		result.element("user", this.user);
		// 向在线用户发送当前用户退出的消息
		WebSocketMessageInboundPool.sendMessage(result.toString());
	}

	@Override
	protected void onBinaryMessage(ByteBuffer message) throws IOException {
		throw new UnsupportedOperationException("Binary message not supported.");
	}

	// 客户端发送消息到服务器时触发事件
	@Override
	protected void onTextMessage(CharBuffer message) throws IOException {
		// 向所有在线用户发送消息
		String msg = message.toString();
		System.out.println(msg);
		if (msg.contains("::")) {
			//100002::message
			String user = "'"+msg.split("::")[0]+"'";
			String mes = msg.split("::")[1];
			System.out.println(user);
			System.out.println(mes);
			
//			user  接受 信息。

			WebSocketMessageInboundPool.sendMessageToUser(this.user,user, mes);
//			WebSocketMessageInboundPool.sendMessageToUser(user, mes);
		} else {
			WebSocketMessageInboundPool.sendMessage(message.toString());
		}
	}
}