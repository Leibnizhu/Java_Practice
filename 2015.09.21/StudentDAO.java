package leibniz.hu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

/**
 * @author Leibniz
 * Access Student class with JDBC-MySQL...
 */
public class StudentDAO {
	/**
	 * Add a student record into table
	 * @param st Student instance
	 * @return
	 */
	public int addStudent(Student st){
		PreparedStatement pstmt = null;
		int state = -1;
		Connection conn = JDBCUtil.getConnection();
		String sql = "INSERT INTO student (name, gender, mark) VALUES(?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, st.getName());
			pstmt.setString(2, st.getGender());
			pstmt.setDouble(3, st.getMark());
			state = pstmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeRes(conn, pstmt, null);
		}
		return state;
	}
	
	/**
	 * Update information of a given student
	 * @param st Student instance
	 * @return
	 */
	public int updateStudent(Student st){
		PreparedStatement pstmt = null;
		int state = -1;
		Connection conn = JDBCUtil.getConnection();
		String sql = "UPDATE student SET name=?,gender=?,mark=? WHERE id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, st.getName());
			pstmt.setString(2, st.getGender());
			pstmt.setDouble(3, st.getMark());
			pstmt.setInt(4, st.getId());
			state = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeRes(conn, pstmt, null);
		}
		return state;
	}
	
	/**
	 * Delete a student by given id
	 * @param id Student ID
	 * @return
	 */
	public int deleteStudentById(int id){
		PreparedStatement pstmt = null;
		int state = -1;
		Connection conn = JDBCUtil.getConnection();
		String sql = "DELETE FROM student WHERE id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			state = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeRes(conn, pstmt, null);
		}
		return state;
	}
	
	/**
	 * Delete all students in the table
	 * @return
	 */
	public int deleteAllStudents(){
		PreparedStatement pstmt = null;
		int state = -1;
		Connection conn = JDBCUtil.getConnection();
		String sql = "DELETE FROM student";
		try {
			pstmt = conn.prepareStatement(sql);
			state = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeRes(conn, pstmt, null);
		}
		return state;
	}
	
	/**
	 * Get student instance by given id
	 * @param id Student ID
	 * @return
	 */
	public Student getStudentById(int id){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Student st = null;
		Connection conn = JDBCUtil.getConnection();	
		String sql = "SELECT * FROM student where id=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				st = new Student(rs.getInt("id"), rs.getString("name"), rs.getString("gender"), rs.getDouble("mark"));
			} else {
				System.out.println("Not Exists...");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeRes(conn, pstmt, rs);
		}
		return st;
	}
	
	/**
	 * Get all students of the table
	 * @return a List contain all student instances in the table
	 */
	public List<Student> getAllStudents(){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Student> ls = new ArrayList<Student>();
		Connection conn = JDBCUtil.getConnection();	
		String sql = "SELECT * FROM student";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Student st = new Student(rs.getInt("id"), rs.getString("name"), rs.getString("gender"), rs.getDouble("mark"));
				ls.add(st);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeRes(conn, pstmt, rs);
		}
		return ls;
	}
}
