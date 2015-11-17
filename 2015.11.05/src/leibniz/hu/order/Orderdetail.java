package leibniz.hu.order;

public class Orderdetail {
	private String id;
	private String bookid;
	private int counts;
	private double price;
	private String orderid;
	
	public Orderdetail() {
	}
	
	public Orderdetail(String id, String bookid, int counts, double price,
			String orderid) {
		super();
		this.id = id;
		this.bookid = bookid;
		this.counts = counts;
		this.price = price;
		this.orderid = orderid;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBookid() {
		return bookid;
	}
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}
	public int getCounts() {
		return counts;
	}
	public void setCounts(int counts) {
		this.counts = counts;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	@Override
	public String toString() {
		return "Orderdetail [id=" + id + ", bookid=" + bookid + ", counts="
				+ counts + ", price=" + price + ", orderid=" + orderid + "]";
	}
}
