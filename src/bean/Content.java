package bean;

public class Content {
	private int id;
	private String fromeuser;
	private String touser;
	private String message;
	private String datetime;
public int getId() {
		return id;
	}
public Content(String fromeuser,String touser,String message,String datetime) {
	this.fromeuser = fromeuser;
	this.touser =touser;
	this.message = message;
	this.datetime = datetime;
}

public Content(String fromeuser) {
	this.fromeuser = fromeuser;
	
	
}
public void setId(int id) {
		this.id = id;
	}
public String getFromeuser() {
		return fromeuser;
	}
public void setFromeuser(String fromeuser) {
		this.fromeuser = fromeuser;
	}
public String getTouser() {
		return touser;
	}
public void setTouser(String touser) {
		this.touser = touser;
	}
public String getmessage() {
		return message;
	}
	public void setmessage(String message) {
	this.message = message;
	}
	@Override
public String toString() {
		return "Record [id=" + id + ", fromeuser=" + fromeuser + ", touser="
				+ touser + ", messager=" + message + "]";
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
}

