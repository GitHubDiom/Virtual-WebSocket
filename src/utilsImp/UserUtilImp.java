package utilsImp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utils.SqlUtil;
import utils.UserUtil;
import bean.User;

public class UserUtilImp implements UserUtil{
	

	// 锟斤拷锟斤拷没锟�
	@Override
	public User addUser(User user) throws SQLException {
		Statement st = null;
		int count = 0;
		Connection con = SqlUtil.getCon();
		String sql = "insert into users(phone,userName,pwd,email) value("+user.getPhone()+",'"+user.getUserName()+"','"+user.getPwd()+"','"+user.getEmail()+"')";
		System.out.println(sql);
		st = con.createStatement();
		count = st.executeUpdate(sql);
		if(count>0){
			return user;
		}
		st.close();
		con.close();
		return null;
	}

	// 删锟斤拷锟矫伙拷
	public int deleteUser(User user) throws SQLException {
		int count =0;
		String sql = "delete from users where userName='"+user.getUserName()+"'";
		System.out.println(sql);
		Connection con = SqlUtil.getCon();
		Statement st = con.createStatement();
		count = st.executeUpdate(sql);
		st.close();
		con.close();
		return count;
	}
	public User SaveContent(User user) throws SQLException {
		Statement st = null;
		int count = 0;
		Connection con = SqlUtil.getCon();
		String sql = "insert into users(phone,userName,pwd,email) value("+user.getPhone()+",'"+user.getUserName()+"','"+user.getPwd()+"','"+user.getEmail()+"')";
		System.out.println(sql);
		st = con.createStatement();
		count = st.executeUpdate(sql);
		if(count>0){
			return user;
		}
		st.close();
		con.close();
		return null;
	}
	// 锟睫革拷锟矫伙拷
	public boolean updateUser(User user) throws SQLException {
		Statement st = null;
		int rs ;
		Connection con = SqlUtil.getCon();
		String userName = user.getUserName();
		//UPDATE users SET pwd = '666', phone = ' 666 '  WHERE userName = user
		//
		String sql = "UPDATE users SET pwd = '"+user.getPwd()+"', phone = '"+user.getPhone()+"', email = '"+user.getEmail()+"'  WHERE userName = '"+user.getUserName()+"'";
		System.out.println(sql);
		st = con.createStatement();
		rs = st.executeUpdate(sql);
		System.out.println(rs);
		
		
		
		return true;
	}

	public User login(User user) throws SQLException {
		Statement st = null;
		ResultSet rs = null;
		Connection con = SqlUtil.getCon();
		String sql = "select * from users where userName='" + user.getUserName()
				+ "' and pwd='" + user.getPwd() + "'";
		System.out.println(sql);
		st = con.createStatement();
		rs = st.executeQuery(sql);
		if (rs.next()) {
			user = new User(rs.getString("userName"),"", rs.getString("email"),rs.getString("phone"));
			return user;
		}
		rs.close();
		st.close();
		con.close();
		return null;
	}

	@Override
	public List<User> getUsers(int count,int from) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		con = SqlUtil.getCon();
		String sql = "select * from users limit "+from+","+count;
		System.out.println(sql);
		st = con.createStatement();
		rs = st.executeQuery(sql);
		List<User> users = new ArrayList<User>();
		while (rs.next()) {
			User u = new User(rs.getString("userName"), rs.getString("pwd"), rs.getString("email"),rs.getString("phone"));
			users.add(u);
		}
		rs.close();
		st.close();
		con.close();
		return users;
	}

}
