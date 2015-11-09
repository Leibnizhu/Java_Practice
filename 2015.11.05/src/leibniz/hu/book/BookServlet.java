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
	 * ���ݸ�����ͼ������id����ʾ����ͼ�������ͼ��
	 * ���idΪnull������ʾ����ͼ�飬���޷�ҳ��ʾ����
	 */
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String categoryId = req.getParameter("categoryid");
		List<Book> lBook = bookServ.queryByCategory(categoryId);
		req.setAttribute("listBook", lBook);
		return "books";
	}

}
