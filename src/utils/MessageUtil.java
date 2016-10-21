package utils;

import java.util.List;

import bean.Record;

public interface MessageUtil {
  public boolean addMessage(String fromUser, String toUser, String message);
  public boolean deleteMsg(String id);
  public List<Record> readMessage(String toUser);
}
