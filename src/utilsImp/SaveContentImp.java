package utilsImp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.Content;
import bean.User;
import sun.org.mozilla.javascript.internal.regexp.SubString;
import utils.MessageUtil;
import utils.SaveContentUtil;
import utils.SqlUtil;
import utils.UserUtil;

public class SaveContentImp implements  SaveContentUtil  {



	@Override
	public boolean addMessage(String fromUser, String toUser, String message,String datetime) {
		// TODO Auto-generated method stub
		Statement st = null;
		int count = 0;
		
//		fromUser=fromUser.substring(1, fromUser.length()-1);
//		toUser=toUser.substring(1, toUser.length()-1);
		Connection con = null;
		try {
			con = SqlUtil.getCon();
			int id=0;
			String sql = "insert into chatlogs(id,fromeuser,touser,content,datetime) value('"+id+"','"+fromUser+"','"+toUser+"','"+message+"','"+datetime+"')";
			id++;
			System.out.println(sql);
			
			st = con.createStatement();
			count = st.executeUpdate(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			
			try {
				if(st!= null){
					st.close();					
				}
				if(con!=null){
					con.close();					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}



	@Override
	public List<Content> GetMessage(String fromUser,String ToUser) {
		// TODO Auto-generated method stub
		ArrayList<Content> list = new ArrayList<Content>();
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = SqlUtil.getCon();
			String sql = "select * from chatlogs where fromeuser = '"+fromUser+"' and touser= '"+ToUser+"' or fromeuser='"+ToUser+"' and touser='"+fromUser+"'";
			System.out.println(sql);
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Content content = new Content(fromUser);
				content.setmessage(rs.getString("content"));
				content.setFromeuser(rs.getString("fromeuser"));
				content.setTouser(rs.getString("touser"));
				content.setDatetime(rs.getString("datetime"));
				list.add(content);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return list;
		
	}

	


}
