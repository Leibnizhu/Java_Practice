package leibniz.hu.domain;

public class Image {
	private String id;
	private String oldname;
	private String newname;
	private String ip;
	private String crdate;
	private String descript;
	
	public Image() {
	}

	public Image(String id, String oldname, String newname, String ip,
			String crdate, String descript) {
		super();
		this.id = id;
		this.oldname = oldname;
		this.newname = newname;
		this.ip = ip;
		this.crdate = crdate;
		this.descript = descript;
	}
	
	@Override
	public String toString() {
		return "Image [id=" + id + ", oldname=" + oldname + ", newname="
				+ newname + ", ip=" + ip + ", descript=" + descript + "]";
	}
	//Getters and Setters... 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOldname() {
		return oldname;
	}
	public void setOldname(String oldname) {
		this.oldname = oldname;
	}
	public String getNewname() {
		return newname;
	}
	public void setNewname(String newname) {
		this.newname = newname;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getCrdate() {
		return crdate;
	}
	public void setCrdate(String crdate) {
		this.crdate = crdate;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
}
