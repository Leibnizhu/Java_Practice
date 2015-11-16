package leibniz.hu.addr;

public class Address {
	private String id;
	private String name;
	private String addr;
	private String phone;
	private String uid;
	private String isdef;
	
	public Address() {
	}
	
	public Address(String id, String name, String addr, String phone,
			String uid, String isdef) {
		super();
		this.id = id;
		this.name = name;
		this.addr = addr;
		this.phone = phone;
		this.uid = uid;
		this.isdef = isdef;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", name=" + name + ", addr=" + addr
				+ ", phone=" + phone + ", uid=" + uid + ", isdef=" + isdef
				+ "]";
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
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getIsdef() {
		return isdef;
	}
	public void setIsdef(String isdef) {
		this.isdef = isdef;
	}
}
