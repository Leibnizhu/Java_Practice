package leibniz.hu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import leibniz.hu.domain.Image;
import leibniz.hu.service.ShowService;

public class ShowServlet extends HttpServlet {

	private static final long serialVersionUID = 1390388053742926438L;

	private ShowService service = new ShowService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//����Service���ȡ���е�Image�����б�
		List<Image> list = service.queryAll();
		request.setAttribute("images", list);
		//ת�����󵽸�����ʾ��JSPҳ��
		request.getRequestDispatcher("/jsp/show.jsp").forward(request, response);
	}
}
