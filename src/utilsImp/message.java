package utilsImp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.Record;
import bean.User;
import sun.org.mozilla.javascript.internal.regexp.SubString;
import utils.MessageUtil;
import utils.SqlUtil;

public class message implements MessageUtil {



	@Override
	public boolean addMessage(String fromUser, String toUser, String message) {
		// TODO Auto-generated method stub
		
		
		Statement st = null;
		int count = 0;
		
		fromUser=fromUser.substring(1, fromUser.length()-1);
		toUser=toUser.substring(1, toUser.length()-1);
		Connection con = null;
		try {
			con = SqlUtil.getCon();
			int id=0;
			String sql = "insert into record(id,fromeuser,touser,type) value('"+id+"',"+fromUser+","+toUser+",'"+message+"')";
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
	public boolean deleteMsg(String toUser) {
		// TODO Auto-generated method stub
		
		toUser=toUser.substring(1, toUser.length()-1);
		String sql = "delete from record where touser ="+toUser+"";
		System.out.println(sql);
		System.out.println(sql);
		Connection con =null;
		Statement st = null;
		try {
			con = SqlUtil.getCon();
			 st = con.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			try {
				if(st!=null){
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
	public List<Record> readMessage(String toUser) {
		// TODO Auto-generated method stub
		ArrayList<Record> list = new ArrayList<Record>();
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			con = SqlUtil.getCon();
			toUser=toUser.substring(1, toUser.length()-1);
			String sql = "select * from record where touser  = "+toUser;
			System.out.println(sql);
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Record rr = new Record();
				rr.setFromeuser(rs.getString("fromeuser"));
				rr.setTouser(rs.getString("touser"));
				rr.setTyper(rs.getString("type"));
				list.add(rr);
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
