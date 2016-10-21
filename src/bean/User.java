package bean;

public class User {

	private String phone;
	private String userName;
	private String pwd;
	private String email;
	private String friendID;
	
	
	public String getfriendID() {
		return friendID;
	}

	public void setfriendID(String friendID) {
		this.friendID = friendID;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String userName,String pwd,String email,String phone) {
		this.userName = userName;
		this.pwd =pwd;
		this.email = email;
		this.phone = phone;
	}
	
	public User(String userName,String pwd) {
		this.userName = userName;
		this.pwd = pwd;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	@Override
	public String toString() {
		return "User [phone=" + phone + ", userName=" + userName + ", pwd="
				+ pwd + ", email=" + email + "]";
	}

	public String toSqlString(){
		return "'"+phone+"','"+userName+"','"+pwd+"','"+email+"'";
	}
	
}
