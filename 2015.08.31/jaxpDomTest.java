/**
 * 
 */
package leibniz.hu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author Leibniz
 *
 */
/**
 * @author Leibniz
 *
 */
public class jaxpDomTest {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws TransformerException 
	 * @throws TransformerFactoryConfigurationError 
	 */
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerFactoryConfigurationError, TransformerException {
		// TODO Auto-generated method stub
		//获取document
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = builder.parse("books.xml");
		
		//显示所有Book元素
		System.out.println(getBooks(document));
		
		//添加新book
		addNewBook(document, "b003", "Lost Temple", "13");
		addNewBook(document, "b004", "算法导论", "78");
		System.out.println(getBooks(document));
		
		//删除指定的book
		deleteBook(document, "b002");
		System.out.println(getBooks(document));
		
		//修改指定id的book的价格和标题
		updateBook(document, "b001", "好饿好饿好饿我真的好饿", "233");
		
		//显示所有Book元素
		System.out.println(getBooks(document));
	
		//刷新到XML文件中
		writeXML(document);
	}
	
	
	/*
	 * 获取所有book，返回List
	 * @param document
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static List<Book> getBooks(Document document) throws ParserConfigurationException, SAXException, IOException{
		List<Book> allBookList = new ArrayList<Book>();
		
		//获取所有叫book的元素并遍历
		NodeList bookNodes = document.getElementsByTagName("book");
		for(int i = 0; i < bookNodes.getLength(); i++){
			Element bookEle = (Element)(bookNodes.item(i));
			//System.out.println(bookEle);
			String id = bookEle.getAttribute("id");
			String title="", price="";
			
			NodeList bookChildNodes = bookEle.getChildNodes();
			for(int j = 0; j < bookChildNodes.getLength(); j++){
				Node child = bookChildNodes.item(j);
				String childName = child.getNodeName();
				if("title".equals(childName)){
					title = child.getTextContent();
				} else if("price".equals(childName)){
					price = child.getTextContent();
				}
			}
			Book newbook = new Book(id, title, price);
			allBookList.add(newbook);
		}
		
		return allBookList;
	}
	
	/**
	 * 根据给定的id修改指定book的标题和价格
	 * @param document
	 * @param id
	 * @param title
	 * @param price
	 */
	public static void updateBook(Document document, String id, String title, String price){
		NodeList bookList = document.getElementsByTagName("book");
		for(int i = 0; i < bookList.getLength(); i++){
			Element bookEle = (Element)bookList.item(i);
			String curId = bookEle.getAttribute("id");
			if(id.equals(curId)){
				bookEle.getElementsByTagName("title").item(0).setTextContent(title);
				bookEle.getElementsByTagName("price").item(0).setTextContent(price);
			}
		}
	}
	/**
	 * 根据给定的id值删除指定的book
	 * @param document
	 * @param id
	 */
	public static void deleteBook(Document document, String id){
		NodeList bookList = document.getElementsByTagName("book");
		for(int i = 0; i < bookList.getLength(); i++){
			Element bookEle = (Element)bookList.item(i);
			String curId = bookEle.getAttribute("id");
			if(id.equals(curId)){
				bookEle.getParentNode().removeChild(bookEle);
			}
		}
	}
	
	/**
	 * 根据输入的信息往XML中新增Book
	 * @param document
	 * @param id
	 * @param title
	 * @param price
	 */
	public static void addNewBook(Document document, String id, String title, String price){
		//创建book元素并设置id属性
		Element newBook = document.createElement("book");
		newBook.setAttribute("id", id);
		//新建title元素并设置为book的子元素，并设置其文本内容
		Element newTitle = document.createElement("title");
		newBook.appendChild(newTitle);
		newTitle.setTextContent(title);
		//增加price元素并设置到book中
		Element newPrice = document.createElement("price");
		newBook.appendChild(newPrice);
		newPrice.setTextContent(price);
		//将设置完标题和ID的book添加到document中
		document.getDocumentElement().appendChild(newBook);
	}
	
	/**
	 * 将给定的Document写入XML文件
	 * @param document
	 * @throws TransformerFactoryConfigurationError
	 * @throws TransformerException
	 */
	public static void writeXML(Document document) throws TransformerFactoryConfigurationError, TransformerException{
		Transformer trans = TransformerFactory.newInstance().newTransformer();
		trans.transform(new DOMSource(document), new StreamResult("Book.jaxp.xml"));
		System.out.println("Saved XML File.");
	}

}
