package utils;

import java.sql.SQLException;
import java.util.List;

import bean.User;

public interface UserUtil {
	// ����û�
	public User addUser(User user) throws SQLException;
	// ɾ���û�
	public int deleteUser(User user) throws SQLException;
	// �޸��û�
	public boolean updateUser(User user) throws SQLException;
	// �û���¼
	public User login(User user) throws SQLException;
	//�����û�
	public List<User> getUsers(int count,int from) throws SQLException;
}
