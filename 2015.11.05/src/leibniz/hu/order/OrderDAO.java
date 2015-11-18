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
			//����Order������Ϣ���sql���ִ����Ӷ���
			System.out.println(DataSourceUtil.getConnTrans());
			qRun.update(DataSourceUtil.getConnTrans(), sql, 
									order.getId(), order.getAddr(), order.getTotal(), order.getUid(), order.getCrtime());
			/*
			sql = "insert into orderdetail(id, bookid, counts, price, orderid) values(?, ?, ?, ?, ?)";
			//��������list��������Ӷ�����ϸ
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
			//��������list��������Ӷ�����ϸ
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
	 * @return ���ط�װ�˵�ǰ�û����ж�����List
	 * �����û�User���󣬲�ѯ���ݿ⣬��ȡ���û����еĶ������Լ�ÿ����������ϸ����װ������Order�����У�
	 */
	public List<Order> list(User user) {
		QueryRunner qRun = new QueryRunner();
		List<Order> orderList = null;
		try{
			//��ѯ��ǰ�û����ж���
			String sql = "SELECT  o.id, o.addr, o.total, o.status, o.uid, o.crtime FROM orders o " +
					"INNER JOIN users ON users.id = o.uid " +
					"WHERE users.id = ?";
			orderList = qRun.query(DataSourceUtil.getConnTrans(), sql, new BeanListHandler<Order>(Order.class), user.getId());
			for(Order order : orderList){
				//���ڶ���List�е�ÿ����������ѯ���ݿ�orderdetail����ȡ�ö�����������ϸ��¼
				sql = "select * from orderdetail where orderid = ?";
				List<Orderdetail> ordList = qRun.query(DataSourceUtil.getConnTrans(), sql, new BeanListHandler<Orderdetail>(Orderdetail.class), order.getId());
				//����װ�˸ö���������ϸ��¼��list���붩��order������
				order.setDetails(ordList);
			}
		} catch(Exception e){
		}
		return orderList;
	}
}
