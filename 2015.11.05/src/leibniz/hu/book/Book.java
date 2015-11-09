package leibniz.hu.book;

public class Book {
	private String id;
	private String name;
	private double price;
	private double discount;
	private String author;
	private String intro;
	private String directory;
	private String image;
	private int page;
	private String publisher;
	private String publishdate;
	private int stock;
		
	public Book() {
	}

	public Book(String id, String name, double price, double discount,
			String author, String intro, String directory, String image,
			int page, String publisher, String publishdate, int stock) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.discount = discount;
		this.author = author;
		this.intro = intro;
		this.directory = directory;
		this.image = image;
		this.page = page;
		this.publisher = publisher;
		this.publishdate = publishdate;
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", price=" + price
				+ ", discount=" + discount + ", author=" + author + ", intro="
				+ intro + ", directory=" + directory + ", image=" + image
				+ ", page=" + page + ", publisher=" + publisher
				+ ", publishdate=" + publishdate + ", stock=" + stock + "]";
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getDirectory() {
		return directory;
	}
	public void setDirectory(String directory) {
		this.directory = directory;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPublishdate() {
		return publishdate;
	}
	public void setPublishdate(String publishdate) {
		this.publishdate = publishdate;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
}
