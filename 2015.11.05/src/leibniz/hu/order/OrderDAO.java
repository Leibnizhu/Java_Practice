package leibniz.hu.order;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import leibniz.hu.user.User;
import leibniz.hu.utils.DataSourceUtil;

public class OrderDAO {

	public Order createOrder(Order order) {
		QueryRunner qRun = new QueryRunner();
		try{
			String sql = "insert into orders(id, addr, total, status, uid, crtime) values(?, ?, ?, '0', ?, ?)";
			//根据Order对象信息填充sql语句执行添加订单
			System.out.println(DataSourceUtil.getConnTrans());
			qRun.update(DataSourceUtil.getConnTrans(), sql, 
									order.getId(), order.getAddr(), order.getTotal(), order.getUid(), order.getCrtime());
			/*
			sql = "insert into orderdetail(id, bookid, counts, price, orderid) values(?, ?, ?, ?, ?)";
			//遍历整个list，逐条添加订单明细
			System.out.println(DataSourceUtil.getConnTrans());
			for(Orderdetail od: order.getDetails()){
				qRun.update(DataSourceUtil.getConnTrans(), sql, 
									od.getId(), od.getBookid(), od.getCounts(), od.getPrice(), order.getId());
			}*/
		} catch(Exception e){
		}
		return order;
	}

	public void createOrderDetails(List<Orderdetail> details, String orderId) {
		String sql = "insert into orderdetail(id, bookid, bookname, counts, price, orderid) values(?, ?, ?, ?, ?, ?)";
		QueryRunner qRun = new QueryRunner();
		try{
			//遍历整个list，逐条添加订单明细
			//System.out.println(DataSourceUtil.getConnTrans());
			for(Orderdetail od: details){
				//System.out.println("insert into orderdetail(id, bookid, counts, price, orderid) values('"+od.getId()+"', '"+od.getBookid()+"', '"+od.getCounts()+"', '"+ od.getPrice()+"', '"+ orderId+"')");
				//sql="insert into orderdetail(id, bookid, counts, price, orderid) values('"+od.getId()+"', '"+od.getBookid()+"', '"+od.getCounts()+"', '"+ od.getPrice()+"', '"+ orderId+"')";
				//qRun.update(DataSourceUtil.getConnTrans(), sql);
				qRun.update(DataSourceUtil.getConnTrans(), sql, 
									od.getId(), od.getBookid(), od.getBookname(), od.getCounts(), od.getPrice(), orderId);
			}
		} catch(Exception e){
		}
	}

	/**
	 * @param user
	 * @return 返回封装了当前用户所有订单的List
	 * 根据用户User对象，查询数据库，获取该用户所有的订单，以及每个订单的明细（封装到订单Order对象中）
	 */
	public List<Order> list(User user) {
		QueryRunner qRun = new QueryRunner();
		List<Order> orderList = null;
		try{
			//查询当前用户所有订单
			String sql = "SELECT  o.id, o.addr, o.total, o.status, o.uid, o.crtime FROM orders o " +
					"INNER JOIN users ON users.id = o.uid " +
					"WHERE users.id = ?";
			orderList = qRun.query(DataSourceUtil.getConnTrans(), sql, new BeanListHandler<Order>(Order.class), user.getId());
			for(Order order : orderList){
				//对于订单List中的每个订单，查询数据库orderdetail表，获取该订单的所有明细记录
				sql = "select * from orderdetail where orderid = ?";
				List<Orderdetail> ordList = qRun.query(DataSourceUtil.getConnTrans(), sql, new BeanListHandler<Orderdetail>(Orderdetail.class), order.getId());
				//将封装了该订单所有明细记录的list放入订单order对象中
				order.setDetails(ordList);
			}
		} catch(Exception e){
		}
		return orderList;
	}
}
