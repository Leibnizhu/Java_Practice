package leibniz.hu.order;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import leibniz.hu.utils.DataSourceUtil;

public class OrderDAO {

	public Order createOrder(Order order) {
		QueryRunner qRun = new QueryRunner();
		try{
			String sql = "insert into orders(id, addr, total, status, uid, crtime) values(?, ?, ?, '0', ?, ?)";
			//����Order������Ϣ���sql���ִ����Ӷ���
			System.out.println(DataSourceUtil.getConnTrans());
			qRun.update(DataSourceUtil.getConnTrans(), sql, 
									order.getId(), order.getAddr(), order.getTotal(), order.getUid(), order.getCtime());
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
		String sql = "insert into orderdetail(id, bookid, counts, price, orderid) values(?, ?, ?, ?, ?)";
		QueryRunner qRun = new QueryRunner();
		try{
			//��������list��������Ӷ�����ϸ
			//System.out.println(DataSourceUtil.getConnTrans());
			for(Orderdetail od: details){
				//System.out.println("insert into orderdetail(id, bookid, counts, price, orderid) values('"+od.getId()+"', '"+od.getBookid()+"', '"+od.getCounts()+"', '"+ od.getPrice()+"', '"+ orderId+"')");
				//sql="insert into orderdetail(id, bookid, counts, price, orderid) values('"+od.getId()+"', '"+od.getBookid()+"', '"+od.getCounts()+"', '"+ od.getPrice()+"', '"+ orderId+"')";
				//qRun.update(DataSourceUtil.getConnTrans(), sql);
				qRun.update(DataSourceUtil.getConnTrans(), sql, 
									od.getId(), od.getBookid(), od.getCounts(), od.getPrice(), orderId);
			}
		} catch(Exception e){
		}
	}
}
