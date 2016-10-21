package bean;

public class Record {
	private int id;
	private String fromeuser;
	private String touser;
	private String type;	
public int getId() {
		return id;
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
public String getTyper() {
		return type;
	}
	public void setTyper(String type) {
	this.type = type;
	}
	@Override
public String toString() {
		return "Record [id=" + id + ", fromeuser=" + fromeuser + ", touser="
				+ touser + ", typer=" + type + "]";
	}
}

