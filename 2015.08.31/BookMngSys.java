package leibniz.hu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.dom4j.DocumentException;

/**
 * 显示菜单并读取用户输入
 * @author Leibniz
 *
 */
public class BookMngSys {
	
	public static void main(String[] args) throws IOException, DocumentException {
		System.out.println("Welcome to Books Manager System!");
		//System.in读取流
		BufferedReader bfrd = new BufferedReader(new InputStreamReader(System.in));
		BookDAO bookDao = new BookDAO();
		while(true){
			System.out.println("Search(S), Add(A), Update(U), Delete(D), Exit(Q)...");
			String command = bfrd.readLine();
			//Handle the inputted command
			//exit the program.
			if("Q".equalsIgnoreCase(command)){
				System.exit(0);
			} else if("S".equalsIgnoreCase(command)){
				//Search a book by ID, and return the result in the screen
				System.out.print("Please input book number:");
				String id = bfrd.readLine();
				//Call DAO method to search a book.
				Book bkSearch = bookDao.getBookById(id);
				//Handle the result
				if(bkSearch == null){
					System.out.println("Could not found the book of ID: " + id +".");
					continue;
				} else {
					System.out.println("Searching result of ID-" + id + " is: " + bkSearch);
				}
			} else if("A".equalsIgnoreCase(command)){
				//Add a book by user-input ID, title and price
				System.out.print("Please input ID: ");
				String id = bfrd.readLine();
				if(bookDao.isExist(id)){
					System.out.println("This book (id=" + id + ") is already existed. You can only Update it.");
					continue;
				}
				System.out.print("Please input title: ");
				String title = bfrd.readLine();
				System.out.print("Please input price: ");
				String price = bfrd.readLine();
				//Call Book DAO method to add/append a new book
				bookDao.addBook(id, title, price);
				System.out.println("Done.");
			} else if("U".equalsIgnoreCase(command)){
				//Update a book's information, including title and price
				System.out.print("Please input ID: ");
				String id = bfrd.readLine();
				if(!bookDao.isExist(id)){
					System.out.println("Not such book(ID=" + id +").");
					continue;
				}
				System.out.print("Please input a new title: ");
				String title = bfrd.readLine();
				System.out.print("Please input a new price: ");
				String price = bfrd.readLine();
				//Call Book DAO method to update book information
				if(bookDao.updateBook(id, title, price)){
					System.out.println("Done.");
				} else {
					System.out.println("Not such book(ID=" + id +").");
				}
			} else if("D".equalsIgnoreCase(command)){
				//Delete a book by ID.
				System.out.print("Please input ID: ");
				String id = bfrd.readLine();
				//Call Book DAO method to delete a book
				if(bookDao.deleteBook(id)){
					System.out.println("Done.");					
				} else {
					System.out.println("Not such book(ID=" + id +").");
				}
			}
		}
	}

}
