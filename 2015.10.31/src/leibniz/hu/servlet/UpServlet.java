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
	 * ���ڴ���Post������ϴ�����Ҫ�ж��ļ�����ΪͼƬ
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//newһ�����̻��湤��
		DiskFileItemFactory dfif = new DiskFileItemFactory(10-24*1024*10, null);
		try{
			ServletFileUpload sfup = new ServletFileUpload(dfif);
			//����ServletFIleUpload����Post����
			@SuppressWarnings("unchecked")
			List<FileItem> fList = sfup.parseRequest(request);
			//����һ��Image���󣬲���request�е���Ϣ���
			Image img = new Image();
			img.setIp(request.getRemoteAddr());
			img.setId(CommonUtil.getUUIDString());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			img.setCrdate(df.format(new Date()).toString());
			for(FileItem file:fList){
				if(file.isFormField()){
					//����ͨ�ı��򣬸����ϴ�ҳ��Ľṹ��Ӧ����ͼƬ������
					//����request�еı����ȡͼƬ��������post����
					String desc = file.getString(request.getCharacterEncoding());
					img.setDescript(desc);
				} else {
					//��File�ϴ��ؼ��������ļ�
					String type = file.getContentType();
					if(!type.contains("image")){
						//����ļ����Ͳ�����ͼƬ�����׳��쳣
						throw new RuntimeException("Unsupported file type....");
					}
					//������ļ���
					String oldName = file.getName();
					oldName = oldName.substring(oldName.lastIndexOf("\\") + 1);
					//��ȡ��չ��������.��
					String extName = oldName.substring(oldName.lastIndexOf("."));
					//ͨ�������UUID����µ��ļ������Է����ĵ��ļ����������غͱ����ϵ��쳣
					//���ļ������ɱ�����JavaBean/���ݿ��У��Ա����ص�ʱ��ԭ ԭ�������֣�
					String newName = CommonUtil.getUUIDString() + extName;
					img.setNewname(newName);
					img.setOldname(oldName);
					
					//�����ϴ����ļ�
					String path = getServletContext().getRealPath("/up");
					file.write(new File(path +  "/" + newName));
					file.delete();
				}
			}
			//image�����װ��ϣ�����Service�㽫Image���󱣴浽���ݿ���
			upService.save(img);
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			
		}
	}

}
