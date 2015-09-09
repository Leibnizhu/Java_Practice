package leibniz.hu;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class JudgeCheckCode extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public JudgeCheckCode() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//Get user input check code
		String userCode = request.getParameter("usercode");
		//Get local check code from session
		HttpSession sess = request.getSession();
		String localCode = (String) sess.getAttribute("CheckCode");
		System.out.println(localCode);
		PrintWriter out = response.getWriter();
		if(localCode == null){
			out.print("Check code Out-Of-Time...");
		} else {
			if(localCode.equalsIgnoreCase(userCode)){
				out.print("Check code right!!!");
			} else {
				out.print("Check code WRONG!!!");
			}
			//Clear check code seesion
			sess.removeAttribute("CheckCode");
		}
	}

}
