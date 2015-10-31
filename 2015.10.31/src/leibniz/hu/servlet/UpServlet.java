package leibniz.hu.servlet;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import leibniz.hu.domain.Image;
import leibniz.hu.service.UpService;
import leibniz.hu.utils.CommonUtil;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UpServlet extends HttpServlet {

	private static final long serialVersionUID = 4100676821101617373L;

	private UpService upService = new UpService();
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * 用于处理Post请求的上传，需要判断文件类型为图片
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//new一个磁盘缓存工厂
		DiskFileItemFactory dfif = new DiskFileItemFactory(10-24*1024*10, null);
		try{
			ServletFileUpload sfup = new ServletFileUpload(dfif);
			//利用ServletFIleUpload解析Post请求
			@SuppressWarnings("unchecked")
			List<FileItem> fList = sfup.parseRequest(request);
			//创建一个Image对象，并用request中的信息填充
			Image img = new Image();
			img.setIp(request.getRemoteAddr());
			img.setId(CommonUtil.getUUIDString());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			img.setCrdate(df.format(new Date()).toString());
			for(FileItem file:fList){
				if(file.isFormField()){
					//是普通的表单域，根据上传页面的结构，应该是图片的描述
					//根据request中的编码获取图片的描述，post请求
					String desc = file.getString(request.getCharacterEncoding());
					img.setDescript(desc);
				} else {
					//是File上传控件，保存文件
					String type = file.getContentType();
					if(!type.contains("image")){
						//如果文件类型不属于图片，则抛出异常
						throw new RuntimeException("Unsupported file type....");
					}
					//处理旧文件名
					String oldName = file.getName();
					oldName = oldName.substring(oldName.lastIndexOf("\\") + 1);
					//获取扩展名，包含.号
					String extName = oldName.substring(oldName.lastIndexOf("."));
					//通过随机的UUID获得新的文件名，以防中文的文件名出现下载和保存上的异常
					//旧文件名依旧保存在JavaBean/数据库中，以便下载的时候还原 原来的名字，
					String newName = CommonUtil.getUUIDString() + extName;
					img.setNewname(newName);
					img.setOldname(oldName);
					
					//保存上传的文件
					String path = getServletContext().getRealPath("/up");
					file.write(new File(path +  "/" + newName));
					file.delete();
				}
			}
			//image对象封装完毕，调用Service层将Image对象保存到数据库中
			upService.save(img);
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			
		}
	}

}
