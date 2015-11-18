package leibniz.hu.order;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import leibniz.hu.addr.AddrService;
import leibniz.hu.addr.Address;
import leibniz.hu.book.Book;
import leibniz.hu.user.User;
import leibniz.hu.utils.BaseServlet;
import leibniz.hu.utils.CommonUtil;
import leibniz.hu.utils.TranProxy;

public class OrderServlet extends BaseServlet {

	private static final long serialVersionUID = 7086692819356121842L;
	//处理用户保存的地址
	private AddrService addrServ = new AddrService();
	//启用事务处理订单的service，涉及到orders和orderdetail两个表，必须用事务
	private IOrderService orderServ = (IOrderService) TranProxy.getProxy(new OrderService());
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

	/**
	 * @param req
	 * @param resp
	 * @return
	 * 创建新订单，及对应的订单明细
	 */
	public String create(HttpServletRequest req, HttpServletResponse resp){
		//从请求和Session中读取到订单地址的id号，以及当前用户的user对象
		String addrid = req.getParameter("addrid");
		User user = (User) req.getSession().getAttribute("user");
		//根据地址id查询到地址对象
		Address addr = addrServ.queryByAddrid(addrid);
		//因为订单中的地址不能直接引用address表中的记录（防止以后修改address/用户管理收货地址）
		//所以要复制并重组收货地址保存到订单记录中
		String newAddr = addr.getName() + "," + addr.getAddr() + "," + addr.getPhone();
		//重新计算订单总金额
		//此处不能直接接受页面返回的金额数，因为页面返回的请求可以被随意擅改，必须在服务器后台重新计算
		//在此获得购物车Map并遍历计算金额，同时要将购物车中的记录逐条添加到orderdetail表中
		@SuppressWarnings("unchecked")
		Map<String, Book> cart = (Map<String, Book>) req.getSession().getAttribute("cart");
		Order order =  new Order();
		//配置order对象
		order.setAddr(newAddr);
		order.setCrtime(CommonUtil.getDateTime());
		order.setId(CommonUtil.getOrderId());
		order.setUid(user.getId());
		double sumprice = 0;
		for(Book b: cart.values()){
			Orderdetail orde = new Orderdetail();
			orde.setId(CommonUtil.getUUIDString());
			orde.setBookid(b.getId());
			orde.setBookname(b.getName());
			orde.setOrderid(order.getId());
			orde.setCounts(b.getCartcnt()); //购物车中当前书本的数量，与数据库中的无关
			//四舍五入计算折扣后的书本价格
			double finalprice = (double)Math.round(b.getPrice() * b.getDiscount() * 100) / 100;
			orde.setPrice(finalprice);
			sumprice += finalprice * b.getCartcnt();
			//将一条订单明细添加到Order对象中，需要service层处理事务
			order.getDetails().add(orde);
		}
		order.setTotal(sumprice);
		//调用Service层处理增加订单的事务
		orderServ.create(order);
		//清除购物车
		req.getSession().removeAttribute("cart");
		try {
			resp.getWriter().print("Order number is : " + order.getId());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String list(HttpServletRequest req, HttpServletResponse resp){
		//通过Service层从数据库中读取当前用户的所有订单
		//放入request中并转发给jsp页面进行处理
		User user = (User) req.getSession().getAttribute("user");
		List<Order> orlist = orderServ.list(user);
		req.setAttribute("orderlist", orlist);
		return "orderlist";
	}
}
