package leibniz.hu.order;

import java.util.List;

import leibniz.hu.user.User;

public class OrderService implements IOrderService {
	
	private OrderDAO orderDao = new OrderDAO();
	@Override
	public Order create(Order order) {
		//必须先执行orders表的增加记录，然后再执行orderdetail表的增加记录，否则会因为外键的限制而无法添加订单明细
		orderDao.createOrder(order);
		orderDao.createOrderDetails(order.getDetails(), order.getId());
		return order;
	}
	@Override
	public List<Order> list(User user) {
		return orderDao.list(user);
	}
	
}
