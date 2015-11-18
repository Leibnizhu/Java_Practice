package leibniz.hu.order;

import java.util.ArrayList;
import java.util.List;

public class Order {
	private String id;
	private String addr;
	private double total;
	private String status;
	private String uid;
	private String crtime;
	
	//数据库中没有的字段
	//用于保存当前订单所有对应的订单明细
	private List<Orderdetail> details = new ArrayList<Orderdetail>();
	
	public List<Orderdetail> getDetails() {
		return details;
	}

	public void setDetails(List<Orderdetail> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", addr=" + addr + ", total=" + total
				+ ", uid=" + uid + ", crtime=" + crtime + ", details=" + details
				+ "]";
	}

	public Order() {
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getCrtime() {
		return crtime;
	}

	public void setCrtime(String crtime) {
		this.crtime = crtime;
	}
}
