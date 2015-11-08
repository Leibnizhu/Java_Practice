package leibniz.hu.index;


import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import leibniz.hu.category.Category;
import leibniz.hu.category.CategoryService;
import leibniz.hu.utils.BaseServlet;

public class indexServlet extends BaseServlet {

	private static final long serialVersionUID = 1186166587409393483L;
	private CategoryService cateService = new CategoryService();
	/* (non-Javadoc)
	 * @see leibniz.hu.utils.BaseServlet#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * 只有一个功能：显示所有图书分类，需要先查询所有图书分类，在转发给显示的jsp页面
	 */
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		List<Category> cateList = cateService.queryAll();
		req.setAttribute("cateList", cateList);
		return "index";
	}
	
}
