package leibniz.hu.book;

import java.util.List;

public class BookService {
	private BookDAO bookDao = new BookDAO();

	public List<Book> queryByCategory(String categoryId) {
		return bookDao.queryByCategory(categoryId);
	}
	
}
