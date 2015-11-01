package leibniz.hu.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import leibniz.hu.domain.Image;
import leibniz.hu.service.DelService;

public class DelServlet extends HttpServlet {

	private static final long serialVersionUID = -1965037703837335808L;
	
	private DelService service = new DelService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		//调用Service层读取数据库获取Image对象
		Image img = service.load(id);
		if(null != img){
			//数据库中有此ID的图片记录
			//判断当前IP是否与数据库记录的上传IP吻合
			if(img.getIp().equals(request.getRemoteAddr())){
				//吻合则删除
				//先删除数据库记录
				service.delete(img);
				//再获取要删除的文件名
				String fileName = getServletContext().getRealPath("/up") + "/" + img.getNewname();
				//建立文件对象并删除
				File imgFile = new File(fileName);
				if(imgFile.exists()){
					imgFile.delete();
				}
				//重定向到全部图片页面
				response.sendRedirect(request.getContextPath() +"/showServlet");
			} else {
				//不吻合则出错
				System.err.println("User of IP:" + img.getIp() + "do not own the competence to delete...");
				response.sendRedirect(request.getContextPath() +"/showServlet");
			}
		} else {
			System.err.println("Image with id of " + id + " is not existed...");
			response.sendRedirect(request.getContextPath() +"/showServlet");
		}
	}

}
