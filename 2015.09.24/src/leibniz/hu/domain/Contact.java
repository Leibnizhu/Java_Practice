package leibniz.hu.domain;

public class Contact {
	private String id;
	private String name;
	private String gender;
	private String tel;
	private String addr;
	private String user_id;
	
	//Constructor methods
	public Contact() {
		super();
	}
	
	public Contact(String id, String name, String gender, String tel,
			String addr, String user_id) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.tel = tel;
		this.addr = addr;
		this.user_id = user_id;
	}
	
	//toString method
	@Override
	public String toString() {
		return "contact [id=" + id + ", name=" + name + ", gender=" + gender
				+ ", tel=" + tel + ", addr=" + addr + ", user_id=" + user_id
				+ "]";
	}
	
	//Getter and Setter.
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}	
}
