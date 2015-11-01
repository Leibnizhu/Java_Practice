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
		//����Service���ȡ���ݿ��ȡImage����
		Image img = service.load(id);
		if(null != img){
			//���ݿ����д�ID��ͼƬ��¼
			//�жϵ�ǰIP�Ƿ������ݿ��¼���ϴ�IP�Ǻ�
			if(img.getIp().equals(request.getRemoteAddr())){
				//�Ǻ���ɾ��
				//��ɾ�����ݿ��¼
				service.delete(img);
				//�ٻ�ȡҪɾ�����ļ���
				String fileName = getServletContext().getRealPath("/up") + "/" + img.getNewname();
				//�����ļ�����ɾ��
				File imgFile = new File(fileName);
				if(imgFile.exists()){
					imgFile.delete();
				}
				//�ض���ȫ��ͼƬҳ��
				response.sendRedirect(request.getContextPath() +"/showServlet");
			} else {
				//���Ǻ������
				System.err.println("User of IP:" + img.getIp() + "do not own the competence to delete...");
				response.sendRedirect(request.getContextPath() +"/showServlet");
			}
		} else {
			System.err.println("Image with id of " + id + " is not existed...");
			response.sendRedirect(request.getContextPath() +"/showServlet");
		}
	}

}
