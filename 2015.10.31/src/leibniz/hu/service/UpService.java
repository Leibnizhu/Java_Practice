package leibniz.hu.service;

import leibniz.hu.dao.ImageDAO;
import leibniz.hu.domain.Image;

public class UpService {
	private ImageDAO upDao = new ImageDAO();
	
	public Image save(Image img) {
		return upDao.save(img);
	}

}
