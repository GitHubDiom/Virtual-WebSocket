package utilsImp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import bean.Friend;
import bean.Group;
import bean.User;
import utils.FriendUtil;
import utils.SqlUtil;

public class FriendImp implements FriendUtil {

	@Override
	public List<Friend> getFriends(User user) {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		List<Friend> friends = new ArrayList<Friend>();
		String sql = "SELECT u.* from users u,friends f where u.phone in("
				+ "SELECT f.toUserID from friends f WHERE f.fromUserID='"
				+ user.getPhone()
				+ "')union SELECT u.* from users u,friends f where u.phone in("
				+ "SELECT f.fromUserID from friends f WHERE f.toUserID='"
				+ user.getPhone() + "')";
		System.out.println(sql);
		try {

			con = SqlUtil.getCon();
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Friend f = new Friend();
				f.setEmail(rs.getString("email"));
				f.setUserName(rs.getString("userName"));
				f.setPhone(rs.getString("phone"));
				//f.setFlag(rs.getInt("flag"));
				friends.add(f);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return friends;
	}

	@Override
	@Test
	public List<User> showStranger(User user) {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		List<User> Stranger = new ArrayList<User>();
		String sql = "SELECT email,phone,userName from users where phone NOT in(SELECT u.phone from users u,friends f where u.phone in(SELECT f.toUserID from friends f WHERE f.fromUserID='"
				+ user.getPhone()
				+ "')union SELECT u.phone from users u,friends f where u.phone in(SELECT f.fromUserID from friends f WHERE f.toUserID='"
				+ user.getPhone() + "'))";
		System.out.println(sql);
		try {

			con = SqlUtil.getCon();
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {

				User tempUser = new User();
				tempUser.setEmail(rs.getString("email"));
				tempUser.setUserName(rs.getString("userName"));
				tempUser.setPhone(rs.getString("phone"));
				if (!user.getPhone().equals(tempUser.getPhone())) {
					Stranger.add(tempUser);
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return Stranger;

	}
//查找好友
    public User searchFriend(User user) throws SQLException {
		
		Statement st = null;
		ResultSet rs = null;
		Connection con = SqlUtil.getCon();
		String sql = "select * from users where phone='" + user.getPhone()+ "'";
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
	
    //删除好友
    public boolean deleteFriend(User user) throws SQLException{
		Statement st = null;
		int count = 0;
		Connection con = SqlUtil.getCon();
		String sql = "delete from friends where fromUserID='"+user.getPhone()+"'"+" and toUserID='"+user.getfriendID()+"'";
		String sql1="delete from friends where fromUserID='"+user.getfriendID()+"'"+" and toUserID='"+user.getPhone()+"'";
		
		System.out.println(sql);
		st = con.createStatement();
		count = st.executeUpdate(sql);
		count = st.executeUpdate(sql1);
		st.close();
		con.close();
		return true;
		
		
	}
    
	@Override
	public boolean addFriend(User host, User adduser) {
		// TODO Auto-generated method stub
		String hostPhone = host.getPhone();
		String addUserPhone = adduser.getPhone();
		
		Statement st = null;
		int count = 0;
		Connection con = null;
		try {
			con = SqlUtil.getCon();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "insert into friends(fromUserID,toUserID) value("+hostPhone+","+addUserPhone+")";
		String sq2 = "insert into friends(fromUserID,toUserID) value("+addUserPhone+","+hostPhone+")";
		System.out.println(sql);
		try {
			st = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			count = st.executeUpdate(sql);
			count = st.executeUpdate(sq2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(count>0){
			return true;
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
		return false;

		
		
	}

	@Override
	public List<Group> selectGroup(User user) {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		List<Group>  groupList= new ArrayList<Group>();
		String sql = "select * from groups where userPhone = '"+user.getPhone()+"'";
		System.out.println(sql);
		try {
			con = SqlUtil.getCon();
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Group tempGroup = new Group();
				tempGroup.setGroupID(rs.getInt( "groupID" ));
				tempGroup.setGroupName(rs.getString("groupName"));
				tempGroup.setUserPhone( rs.getString("userPhone"));
				groupList.add(tempGroup);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return groupList;

	}

	@Override
	public List<Friend> getGroupFri(int flag, User user) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				Connection con = null;
				Statement st = null;
				ResultSet rs = null;
				List<Friend> friends = new ArrayList<Friend>();
				String sql = "select u.*,f.flag FROM users u JOIN friends f ON f.toUserID = u.phone where f.fromUserID = '"+user.getPhone()+"' and f.flag = "+flag+"";
//				String sql = "SELECT u.*,f.flag from users u,friends f where f.flag='"+flag+"' AND u.phone in(SELECT f.toUserID from friends f WHERE f.flag='"+flag+"' and f.fromUserID='"+user.getPhone()+"')union SELECT u.*,f.flag from users u,friends f where f.flag='"+flag+"' AND u.phone in(SELECT f.fromUserID from friends f WHERE f.flag='"+flag+"' and f.toUserID='"+user.getPhone()+"')";
//				String sql ="select ff.*,u.* from (select f.toUserID as userId,f.flag as flag from friends f where f.fromUserID='"+user.getPhone()+"'UNION select f.fromUserID as userId,f.flag as flag  from friends f WHERE f.toUserID='"+user.getPhone()+"') as ff,users as u where u.phone=ff.userId ";
				System.out.println(sql);
				try {

					con = SqlUtil.getCon();
					st = con.createStatement();
					rs = st.executeQuery(sql);
					while (rs.next()) {
						Friend f = new Friend();
						f.setEmail(rs.getString("email"));
						f.setUserName(rs.getString("userName"));
						f.setPhone(rs.getString("phone"));
						f.setFlag(rs.getInt("flag"));
						friends.add(f);
					}

				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					if (rs != null) {
						try {
							rs.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (st != null) {
						try {
							st.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (con != null) {
						try {
							con.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}

				return friends;

	}

	@Override
	public boolean manFriend(String userPhone, String friendPhone,
			String groupID) {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement st = null;
		int rs ;
		String sql = "UPDATE friends SET flag = '"+groupID+"' WHERE fromUserID = '"+userPhone+"' and toUserID = '"+friendPhone+"' ";
		try {
			con = SqlUtil.getCon();
			st = con.createStatement();
			rs = st.executeUpdate(sql);
			System.out.println("修改用户之后返回的结果"+rs);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(st!=null){
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return true;
	}


	
	

}
