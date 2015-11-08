package leibniz.hu.category;

import java.util.List;

public class CategoryService {

	private CategoryDAO cateDao = new CategoryDAO();
	
	public List<Category> queryAll() {
		return cateDao.queryAll();
	}

}
