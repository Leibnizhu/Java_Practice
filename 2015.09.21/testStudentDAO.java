package leibniz.hu;

import static org.junit.Assert.fail;

import org.junit.Test;

public class testStudentDAO {

	@Test
	public void testAddStudent(){
		Student st = new Student(14,"Jucy","m",93.4);
		StudentDAO stuDao = new StudentDAO();
		System.out.println(stuDao.addStudent(st));
	}
	
	@Test
	public void testUpdateStudent(){
		Student st = new Student(1,"Angel","f",83.45);
		StudentDAO stuDao = new StudentDAO();
		System.out.println(stuDao.updateStudent(st));
	}
	
	@Test
	public void testDeleteStudentById(){
		StudentDAO stuDao = new StudentDAO();
		System.out.println(stuDao.deleteStudentById(3));
	}
	
	@Test
	public void testDeleteAllStudents(){
		StudentDAO stuDao = new StudentDAO();
		System.out.println(stuDao.deleteAllStudents());
	}
	
	@Test
	public void testGetStudentById(){
		StudentDAO stuDao = new StudentDAO();
		System.out.println(stuDao.getStudentById(4));
	}
	
	@Test
	public void testGetAllStudents(){
		StudentDAO stuDao = new StudentDAO();
		System.out.println(stuDao.getAllStudents());
	}
}
