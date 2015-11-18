package leibniz.hu.order;

import java.util.List;

import leibniz.hu.user.User;

public class OrderService implements IOrderService {
	
	private OrderDAO orderDao = new OrderDAO();
	@Override
	public Order create(Order order) {
		//������ִ��orders������Ӽ�¼��Ȼ����ִ��orderdetail������Ӽ�¼���������Ϊ��������ƶ��޷���Ӷ�����ϸ
		orderDao.createOrder(order);
		orderDao.createOrderDetails(order.getDetails(), order.getId());
		return order;
	}
	@Override
	public List<Order> list(User user) {
		return orderDao.list(user);
	}
	
}
