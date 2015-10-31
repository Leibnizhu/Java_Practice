package leibniz.hu.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownServlet extends HttpServlet {

	private static final long serialVersionUID = 2067231969815743809L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String oldname = request.getParameter("oldname");
		//newname��Ϊ��UUID������ISO-8859-1���Խ��ܣ����账�����
		String newname = request.getParameter("newname");
		//get�������������
		oldname = new String(oldname.getBytes("ISO-8859-1"), "UTF-8");
		File imgFile = new File(getServletContext().getRealPath("/up") + "/" + newname);
		if(imgFile.exists()){
			//��������ļ������������
			//������Ӧ��http��Ӧͷ����Ϣ
			response.setContentType("application/force-download");
			//�ļ�������URL���룬��ȷ����������յ���ȷ���ļ���
			oldname = URLEncoder.encode(oldname, "UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + oldname);
			response.setContentLength((int) imgFile.length());
			//����������ȡ�ļ�
			InputStream in = new FileInputStream(imgFile);
			byte[] buf = new byte[1024];
			int len = -1;
			while((len = in.read(buf)) != -1){
				response.getOutputStream().write(buf, 0, len);
			}
		} else {
			System.err.println("File donot exists...");
		}
	}

}
