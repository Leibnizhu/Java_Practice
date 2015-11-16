package leibniz.hu.order;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import leibniz.hu.addr.AddrService;
import leibniz.hu.addr.Address;
import leibniz.hu.user.User;
import leibniz.hu.utils.BaseServlet;

public class OrderServlet extends BaseServlet {

	private static final long serialVersionUID = 7086692819356121842L;
	private AddrService addrServ = new AddrService();
	/* (non-Javadoc)
	 * @see leibniz.hu.utils.BaseServlet#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * 默认的方法，在用户提交订单的时候
	 * 先查询数据库的address表，获取当前用户的所有保存的地址
	 * 以便在订单确认页面供用户选择所需的地址
	 */
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		//从Session获取当前的User对象，并通过AddressService查询该用户的所有存储的送货地址
		User user = (User) req.getSession().getAttribute("user");
		List<Address> addrList = addrServ.queryByUserid(user.getId());
		//放入request中，并转发到对应的订单确认页面
		req.setAttribute("addrlist", addrList);
		return "order";
	}

	public String create(HttpServletRequest req, HttpServletResponse resp){
		return null;
	}
}
