package leibniz.hu.order;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import leibniz.hu.book.Book;
import leibniz.hu.book.BookService;
import leibniz.hu.utils.BaseServlet;

public class BuyServlet extends BaseServlet {

	private static final long serialVersionUID = 6213194408660341304L;
	private BookService bookServ = new BookService();
	/* (non-Javadoc)
	 * @see leibniz.hu.utils.BaseServlet#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * �ڴ˽�ѡ�е��鱾���빺�ﳵ�����빺�ﳵҳ��
	 */
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String bookid = req.getParameter("bookid");
		//��һ��Map<String id, Book book>���洢���ﳵ�����ݣ�idΪ�鱾��id ��bookΪ��Ӧ���鱾��������������book��
		//�ȴ�session�л�ȡ���ﳵMap
		Map<String, Book> cart = (Map<String, Book>) req.getSession().getAttribute("cart");
		if(null == cart){
			//���ﳵ����û������Ӧ���½�һ����������Session
			cart = new HashMap<String, Book>();
			req.getSession().setAttribute("cart", cart);
		}
		
		if(null == cart.get(bookid)){
			//���ﳵ���޴��鱾
			//�ȴ����ݿ��в�ѯ������
			Book book = bookServ.queryBookById(bookid);
			//����book����Ĺ��ﳵ����
			book.setCartCnt(1);
			//���빺�ﳵMap
			cart.put(bookid, book);
		} else {
			//���ﳵ���Ѿ��и��鱾�������Ӽ�������
			Book book = cart.get(bookid);
			book.setCartCnt(book.getCartCnt() + 1);
		}
		return "redirect:cart";
	}

}
