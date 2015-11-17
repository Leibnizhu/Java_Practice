package leibniz.hu.order;

import leibniz.hu.utils.Tran;

public interface IOrderService {
	@Tran
	Order create(Order order);
}
