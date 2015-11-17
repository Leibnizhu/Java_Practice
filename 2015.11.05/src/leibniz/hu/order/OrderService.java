package leibniz.hu.order;

public class OrderService implements IOrderService {
	
	private OrderDAO orderDao = new OrderDAO();
	@Override
	public Order create(Order order) {
		orderDao.createOrder(order);
		orderDao.createOrderDetails(order.getDetails(), order.getId());
		return order;
	}

}
