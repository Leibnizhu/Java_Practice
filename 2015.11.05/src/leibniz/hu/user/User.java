package leibniz.hu.user;

public class User {
	private String id;
	private String name;
	private String pswd;
	private String email;
	
	public User() {
	}
	
	public User(String id, String name, String pswd, String email) {
		super();
		this.id = id;
		this.name = name;
		this.pswd = pswd;
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", pswd=" + pswd
				+ ", email=" + email + "]";
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPswd() {
		return pswd;
	}
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
