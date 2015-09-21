package leibniz.hu;

/**
 * @author Leibniz
 * Student with id, name, gender and mark.
 */
public class Student {
	private int id;
	private String name;
	private String gender;
	private double mark;
	
	//Getter and setter
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public double getMark() {
		return mark;
	}
	public void setMark(double mark) {
		this.mark = mark;
	}
		
	//toString()
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", gender=" + gender
				+ ", mark=" + mark + "]";
	}
	
	//Constructers
	public Student(int id, String name, String gender, double mark) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.mark = mark;
	}
	
	public Student() {
		super();
	}
	
}
