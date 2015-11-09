package leibniz.hu.book;

import java.util.List;

public class BookService {
	private BookDAO bookDao = new BookDAO();

	public List<Book> queryBooksByCategory(String categoryId) {
		return bookDao.queryBooksByCategory(categoryId);
	}

	public Book queryBookById(String bookid) {
		return bookDao.queryBookById(bookid);
	}
	
}
