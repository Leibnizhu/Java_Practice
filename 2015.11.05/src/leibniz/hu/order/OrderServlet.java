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
	//�����û�����ĵ�ַ
	private AddrService addrServ = new AddrService();
	//��������������service���漰��orders��orderdetail����������������
	private IOrderService orderServ = (IOrderService) TranProxy.getProxy(new OrderService());
	/* (non-Javadoc)
	 * @see leibniz.hu.utils.BaseServlet#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * Ĭ�ϵķ��������û��ύ������ʱ��
	 * �Ȳ�ѯ���ݿ��address����ȡ��ǰ�û������б���ĵ�ַ
	 * �Ա��ڶ���ȷ��ҳ�湩�û�ѡ������ĵ�ַ
	 */
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		//��Session��ȡ��ǰ��User���󣬲�ͨ��AddressService��ѯ���û������д洢���ͻ���ַ
		User user = (User) req.getSession().getAttribute("user");
		List<Address> addrList = addrServ.queryByUserid(user.getId());
		//����request�У���ת������Ӧ�Ķ���ȷ��ҳ��
		req.setAttribute("addrlist", addrList);
		return "order";
	}

	/**
	 * @param req
	 * @param resp
	 * @return
	 * �����¶���������Ӧ�Ķ�����ϸ
	 */
	public String create(HttpServletRequest req, HttpServletResponse resp){
		//�������Session�ж�ȡ��������ַ��id�ţ��Լ���ǰ�û���user����
		String addrid = req.getParameter("addrid");
		User user = (User) req.getSession().getAttribute("user");
		//���ݵ�ַid��ѯ����ַ����
		Address addr = addrServ.queryByAddrid(addrid);
		//��Ϊ�����еĵ�ַ����ֱ������address���еļ�¼����ֹ�Ժ��޸�address/�û������ջ���ַ��
		//����Ҫ���Ʋ������ջ���ַ���浽������¼��
		String newAddr = addr.getName() + "," + addr.getAddr() + "," + addr.getPhone();
		//���¼��㶩���ܽ��
		//�˴�����ֱ�ӽ���ҳ�淵�صĽ��������Ϊҳ�淵�ص�������Ա������øģ������ڷ�������̨���¼���
		//�ڴ˻�ù��ﳵMap�����������ͬʱҪ�����ﳵ�еļ�¼������ӵ�orderdetail����
		@SuppressWarnings("unchecked")
		Map<String, Book> cart = (Map<String, Book>) req.getSession().getAttribute("cart");
		Order order =  new Order();
		//����order����
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
			orde.setCounts(b.getCartcnt()); //���ﳵ�е�ǰ�鱾�������������ݿ��е��޹�
			//������������ۿۺ���鱾�۸�
			double finalprice = (double)Math.round(b.getPrice() * b.getDiscount() * 100) / 100;
			orde.setPrice(finalprice);
			sumprice += finalprice * b.getCartcnt();
			//��һ��������ϸ��ӵ�Order�����У���Ҫservice�㴦������
			order.getDetails().add(orde);
		}
		order.setTotal(sumprice);
		//����Service�㴦�����Ӷ���������
		orderServ.create(order);
		//������ﳵ
		req.getSession().removeAttribute("cart");
		try {
			resp.getWriter().print("Order number is : " + order.getId());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String list(HttpServletRequest req, HttpServletResponse resp){
		//ͨ��Service������ݿ��ж�ȡ��ǰ�û������ж���
		//����request�в�ת����jspҳ����д���
		User user = (User) req.getSession().getAttribute("user");
		List<Order> orlist = orderServ.list(user);
		req.setAttribute("orderlist", orlist);
		return "orderlist";
	}
}
