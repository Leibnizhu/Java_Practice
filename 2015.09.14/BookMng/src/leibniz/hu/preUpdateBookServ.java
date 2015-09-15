package leibniz.hu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

public class preUpdateBookServ extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public preUpdateBookServ() {
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
		//System.out.println("test");
		//Get book ID from request header
		String id = request.getParameter("bookID");
		BookDAO bookDao = new BookDAO();
		try {
			Book book = bookDao.getBookById(id);
			if(book != null){				
				//Send it to next jsp to handle it
				request.setAttribute("book",book);
				request.getRequestDispatcher("/updateBook.jsp").forward(request, response);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

}
