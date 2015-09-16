package leibniz.hu;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchBookServ extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SearchBookServ() {
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
		//Get search key and type from request
		String type = request.getParameter("type");
		String key = request.getParameter("key");
		//System.out.println(type + ":" + key);
		//new a DAO object and call search method
		BookDAO bookDao = new BookDAO();
		List<Book> bookList = bookDao.getBooksByKey(key, type);
		//Add the books list into field
		request.setAttribute("bookList", bookList);
		
		//Forward to JSP, to show search result.
		request.getRequestDispatcher("/searchBook.jsp").forward(request, response);
	}

}
