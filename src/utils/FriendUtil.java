package utils;

import java.sql.SQLException;
import java.util.List;

import bean.Friend;
import bean.Group;
import bean.User;

public interface FriendUtil {

	public List<Friend> getFriends(User user);
//	public List<Friends> getFriends(String phone);
	
	//用于查看没有加为好友的人
	public List<User> showStranger(User user);
	
	public User searchFriend(User user) throws SQLException;
	
	public boolean deleteFriend(User user) throws SQLException ;
	
	
	public boolean addFriend(User host,User adduser);
	
	public List<Group> selectGroup(User user);
	
	public List<Friend> getGroupFri(int flag,User user);
	public boolean manFriend(String userPhone, String friendPhone,String groupID);
}
