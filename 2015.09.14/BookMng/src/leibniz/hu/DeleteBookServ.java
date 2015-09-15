package leibniz.hu;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

public class DeleteBookServ extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DeleteBookServ() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//Get book ID from request header
		String id = request.getParameter("bookID");
		//create DAO and new book object
		BookDAO bookDao = new BookDAO();
		//Call DAO method to update a new book
		boolean success = false;
		try {
			success = bookDao.deleteBook(id);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		//judge success of fail to notice user
		if(success){
			out.print("删除成功！<br/>");
		} else {
			out.print("不存在ID为 " + id + " 的图书！无法删除！<br/>");
		}
		String jumpURL = request.getContextPath() + "/getAllBook";
		out.print("将在3秒内自动跳转到查询页面...<a href='" + jumpURL +"'>手动跳转</a>...");
		response.setHeader("refresh", "3;url=" + jumpURL);
	}
}
