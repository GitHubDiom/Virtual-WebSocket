package bean;

public class Group {
	private int groupID;
	private String userPhone;
	private String groupName;

	@Override
	public String toString() {
		return "Group [groupID=" + groupID + ", userPhone=" + userPhone
				+ ", groupName=" + groupName + "]";
	}

	public int getGroupID() {
		return groupID;
	}

	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}
