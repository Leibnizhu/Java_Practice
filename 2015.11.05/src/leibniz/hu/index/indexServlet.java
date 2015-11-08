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
	 * ֻ��һ�����ܣ���ʾ����ͼ����࣬��Ҫ�Ȳ�ѯ����ͼ����࣬��ת������ʾ��jspҳ��
	 */
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		List<Category> cateList = cateService.queryAll();
		req.setAttribute("cateList", cateList);
		return "index";
	}
	
}
