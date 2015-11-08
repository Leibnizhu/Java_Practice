package leibniz.hu.category;

public class Category {
	private String id;
	private String name;
	private String des;
	
	public Category(String id, String name, String des) {
		super();
		this.id = id;
		this.name = name;
		this.des = des;
	}
	public Category() {
		super();
	}
	
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", des=" + des + "]";
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
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
}
