package leibniz.hu.service;

import java.util.List;

import leibniz.hu.dao.ImageDAO;
import leibniz.hu.domain.Image;

public class ShowService {

	private ImageDAO imageDao = new ImageDAO();
	
	public List<Image> queryAll() {
		return imageDao.queryAll();
	}

}
