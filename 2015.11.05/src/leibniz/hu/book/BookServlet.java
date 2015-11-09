package leibniz.hu.book;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import leibniz.hu.utils.BaseServlet;

public class BookServlet extends BaseServlet {

	private static final long serialVersionUID = -352351480631196224L;
	private BookService bookServ = new BookService();
	/* (non-Javadoc)
	 * @see leibniz.hu.utils.BaseServlet#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * 根据给定的图书类型id，显示该类图书的所有图书
	 * 如果id为null，则显示所有图书，暂无分页显示功能
	 */
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String categoryId = req.getParameter("categoryid");
		List<Book> lBook = bookServ.queryByCategory(categoryId);
		req.setAttribute("listBook", lBook);
		return "books";
	}

}
