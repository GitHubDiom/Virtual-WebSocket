package bean;

public class Friend {
	private String phone;
	private String userName;
	private String pwd;
	private String email;
	private int flag;
	private int id;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "friends [phone=" + phone + ", userName=" + userName + ", pwd="
				+ pwd + ", email=" + email + ", flag=" + flag + "]";
	}
	
	

}
