package leibniz.hu.domain;

public class User {
	private String id;
	private String username;
	private String pswd;
	
	//Constructor methods
	public User() {
		super();
	}
	
	public User(String id, String username, String pswd) {
		super();
		this.id = id;
		this.username = username;
		this.pswd = pswd;
	}
	
	//toString method
	@Override
	public String toString() {
		return "user [id=" + id + ", username=" + username + ", pswd=" + pswd
				+ "]";
	}
	
	//Getter and Setter.
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPswd() {
		return pswd;
	}
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}	
}
