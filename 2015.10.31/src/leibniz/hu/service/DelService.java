package leibniz.hu.service;

import leibniz.hu.dao.ImageDAO;
import leibniz.hu.domain.Image;

public class DelService {

	private ImageDAO imageDao = new ImageDAO();
	
	public Image load(String id) {
		return imageDao.load(id);
	}

	public int delete(Image img) {
		return imageDao.delete(img);
	}
}
