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
		//newname因为是UUID命名，ISO-8859-1可以接受，无需处理编码
		String newname = request.getParameter("newname");
		//get方法，处理编码
		oldname = new String(oldname.getBytes("ISO-8859-1"), "UTF-8");
		File imgFile = new File(getServletContext().getRealPath("/up") + "/" + newname);
		if(imgFile.exists()){
			//存在这个文件，则可以下载
			//设置相应的http响应头等信息
			response.setContentType("application/force-download");
			//文件名进行URL编码，以确保浏览器能收到正确的文件名
			oldname = URLEncoder.encode(oldname, "UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + oldname);
			response.setContentLength((int) imgFile.length());
			//打开输入流读取文件
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
