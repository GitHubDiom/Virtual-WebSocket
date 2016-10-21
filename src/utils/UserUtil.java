package utils;

import java.sql.SQLException;
import java.util.List;

import bean.User;

public interface UserUtil {
	// 添加用户
	public User addUser(User user) throws SQLException;
	// 删除用户
	public int deleteUser(User user) throws SQLException;
	// 修改用户
	public boolean updateUser(User user) throws SQLException;
	// 用户登录
	public User login(User user) throws SQLException;
	//查找用户
	public List<User> getUsers(int count,int from) throws SQLException;
}
