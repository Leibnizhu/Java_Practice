package leibniz.hu.addr;

import java.util.List;

public class AddrService {
	private AddrDAO addrDao = new AddrDAO();
	public List<Address> queryByUserid(String id) {
		return addrDao.queryByUserid(id);
	}

}
