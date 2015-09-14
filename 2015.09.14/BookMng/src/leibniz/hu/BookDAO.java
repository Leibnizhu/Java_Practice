package leibniz.hu;

import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.*;

/**
 * @author Leibniz To search, add, update and delete books by dom4j.
 */
public class BookDAO {
	private static String path;
		
	public static void setPath(String path) {
		BookDAO.path = path;
	}

	/**
	 * 无参数
	 * 
	 * @return books.xml对应的Document对象，静态函数
	 * @throws DocumentException
	 */
	public org.dom4j.Document getDoc() throws DocumentException {
		SAXReader reader = new SAXReader();
		return reader.read(path);
	}

	/**
	 * @return 返回books.xml的XMLWriter
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public XMLWriter getWriter() throws UnsupportedEncodingException, FileNotFoundException {
		return new XMLWriter(new FileOutputStream("books.xml"));
	}

	/**
	 * @param id
	 * @return 判断指定id节点是否存在
	 * @throws DocumentException
	 */
	public boolean isExist(String id) throws DocumentException {
		org.dom4j.Document doc = getDoc();
		// 获取指定ID的节点
		Node bookNode = doc.selectSingleNode("//book[@id='" + id + "']");
		if (bookNode == null) {
			return false;
		}
		return true;
	}
	
	public List<Book> getAllBooks(){
		try {
			List<Book> bookList = new ArrayList<Book>();
			//Get document and its root element
			Document doc = getDoc();
			Element rootEle = doc.getRootElement();
			//Get all child elements
			List<Element> bookEles = rootEle.elements();
			String title = "", price = "";
			//ergodic it
			for (Element bookEle : bookEles) {
				//Get id
				String id = bookEle.attributeValue("id");
				//Get child elements
				List<Element> childEles = bookEle.elements();
				for (Element child : childEles) {
					//Get title
					if ("title".equals(child.getName())) {
						title = child.getText();
					}
					//Get price
					if ("price".equals(child.getName())) {
						price = child.getText();
					}
				}
				Book book = new Book(id, title, price);
				bookList.add(book);
			}
			return bookList;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Book getBookById(String id) throws DocumentException {
		org.dom4j.Document doc = getDoc();
		Node bookNode = doc.selectSingleNode("//book[@id='" + id + "']");
		// 如果找不到改ID的节点，返回null
		if (bookNode == null) {
			return null;
		}
		// 如果找到，则新建Book 对象， 填充信息并返回之
		Element bookEle = (Element) bookNode;
		String title = "", price = "";
		// 列出子元素并遍历
		List childEles = bookEle.elements();
		for (int i = 0; i < childEles.size(); i++) {
			Element childEle = (Element) childEles.get(i);
			// 获取title
			if ("title".equals(childEle.getName())) {
				title = childEle.getText();
			}
			if ("price".equals(childEle.getName())) {
				price = childEle.getText();
			}
		}
		return new Book(id, title, price);
	}

	public void addBook(String id, String title, String price) throws DocumentException, IOException {
		org.dom4j.Document doc = getDoc();
		Element rootEle = doc.getRootElement();

		// 创建Book元素
		Element newbook = DocumentHelper.createElement("book");
		// 新建ID属性并添加到book元素中
		Attribute attrId = DocumentHelper.createAttribute(newbook, "id", id);
		newbook.add(attrId);
		// 创建title子元素，并添加到book元素中
		Element titleEle = DocumentHelper.createElement("title");
		titleEle.setText(title);
		newbook.add(titleEle);
		// 创建price子元素，并添加到book元素中
		Element priceEle = DocumentHelper.createElement("price");
		priceEle.setText(price);
		newbook.add(priceEle);
		// 将新book元素添加到根元素中
		rootEle.add(newbook);

		// 写入books。xml
		XMLWriter writer = getWriter();
		writer.write(doc);
		writer.close();
	}

	public boolean updateBook(String id, String title, String price) throws DocumentException, IOException {
		org.dom4j.Document doc = getDoc();
		// 获取指定ID的节点
		Node bookNode = doc.selectSingleNode("//book[@id='" + id + "']");
		if (bookNode == null) {
			return false;
		}
		// 更新book节点信息
		// 获取title子节点
		Element titleEle = ((Element) bookNode).element("title");
		titleEle.setText(title);
		// 获取price子节点
		Element priceEle = ((Element) bookNode).element("price");
		priceEle.setText(price);
		// 写入books。xml
		XMLWriter writer = getWriter();
		writer.write(doc);
		writer.close();
		return true;
	}

	public boolean deleteBook(String id) throws DocumentException, IOException {
		org.dom4j.Document doc = getDoc();
		// 获取指定ID的节点
		Node bookNode = doc.selectSingleNode("//book[@id='" + id + "']");
		if (bookNode == null) {
			return false;
		}
		// 删除节点
		bookNode.getParent().remove(bookNode);
		// 写入books。xml
		XMLWriter writer = getWriter();
		writer.write(doc);
		writer.close();
		return true;
	}
}