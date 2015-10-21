package leibniz.hu.service;

import java.util.List;

import leibniz.hu.DAO.MenuDAO;
import leibniz.hu.domain.Menu;

public class MenuService {
	private MenuDAO menuDao = new MenuDAO();
	public List<Menu> query(String uid) {
		return menuDao.query(uid);
	}

}
