package leibniz.hu.order;

import java.util.List;

import leibniz.hu.user.User;
import leibniz.hu.utils.Tran;

public interface IOrderService {
	@Tran
	Order create(Order order);

	@Tran
	List<Order> list(User user);
}
