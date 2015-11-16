package leibniz.hu.order;

import java.util.HashMap;
import java.util.Map;

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
	 * 在此将选中的书本放入购物车，进入购物车页面
	 */
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String bookid = req.getParameter("bookid");
		//用一个Map<String id, Book book>来存储购物车的内容，id为书本的id ，book为对应的书本对象，数量保存在book中
		//先从session中获取购物车Map
		@SuppressWarnings("unchecked")
		Map<String, Book> cart = (Map<String, Book>) req.getSession().getAttribute("cart");
		if(null == cart){
			//购物车对象还没构建，应该新建一个，并放如Session
			cart = new HashMap<String, Book>();
			req.getSession().setAttribute("cart", cart);
		}
		
		if(null == cart.get(bookid)){
			//购物车中无此书本
			//先从数据库中查询出该书
			Book book = bookServ.queryBookById(bookid);
			//设置book对象的购物车计数
			book.setCartcnt(1);
			//放入购物车Map
			cart.put(bookid, book);
		} else {
			//购物车中已经有该书本，仅增加计数即可
			Book book = cart.get(bookid);
			book.setCartcnt(book.getCartcnt() + 1);
		}
		return "redirect:cart";
	}

	public String chgnum(HttpServletRequest req, HttpServletResponse resp) {
		String bookid = req.getParameter("bookid");
		int delta = Integer.parseInt(req.getParameter("num"));
		@SuppressWarnings("unchecked")
		Map<String, Book> cart = (Map<String, Book>) req.getSession().getAttribute("cart");
		if(null == cart){
			//购物车对象还没构建（只能是用户故意输入该url），应该新建一个，并放入Session然后返回空，不处理
			cart = new HashMap<String, Book>();
			req.getSession().setAttribute("cart", cart);
			return null;
		} else if(null == cart.get(bookid)){
			//购物车中无此书本
			//不做任何处理
			return null;
		} else {
			//购物车中已经有该书本，则改变计数
			Book book = cart.get(bookid);
			int newNum = book.getCartcnt() + delta;
			if(newNum >= 0){
				book.setCartcnt(newNum);
			}
			return "redirect:cart";
		}
	}
}
