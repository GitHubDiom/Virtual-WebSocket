package utils;

import java.sql.SQLException;
import java.util.List;

import bean.Content;

public interface SaveContentUtil {

	boolean addMessage(String fromUser, String toUser, String message, String datetime)throws SQLException;

//	public List<Record> readMessage(String fromUser, String toUser,String datatime) throws SQLException;


	public List<Content> GetMessage(String fromUser, String ToUser)throws SQLException;
}
