package leibniz.hu.user;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetCheckCode extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int width = 80;
		int height = 30;
		int border = 2;
		//Create a image buffer
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//Get a Sketchpad
		Graphics grap = image.getGraphics();
		//Draw a blue rectangle as background
		grap.setColor(Color.BLUE);
		grap.fillRect(0, 0, width, height);
		//Draw a inner rectangle to make a border
		grap.setColor(Color.WHITE);
		grap.fillRect(border, border, width-2*border, height-2*border);
		
		//Letter library
		String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		//Set font style
		grap.setFont(new Font("΢���ź�", Font.TYPE1_FONT, 20));
		//Get a random 4-letter string
		StringBuffer strBuf = new StringBuffer();
		Random rand = new Random();
		for(int i = 0; i < 4; i++){
			//Get a random color
			grap.setColor(new Color(10+rand.nextInt(150), 10+rand.nextInt(150), 10+rand.nextInt(150)));
			int randIndex = rand.nextInt(62);
			String strTemp = letters.substring(randIndex, randIndex + 1);
			//Draw a letter
			grap.drawString(strTemp, 5+i*18, 22);
			strBuf.append(strTemp);
		}
		
		//Get session
		HttpSession sess = request.getSession();
		//Write the random 4-letter string into session
		sess.setAttribute("CheckCode", strBuf.toString());
		System.out.println(strBuf);
		
		//Add some Interference line
		for(int i = 0; i < 10; i++){
			grap.setColor(new Color(10+rand.nextInt(245), 10+rand.nextInt(245), 10+rand.nextInt(245)));
			grap.drawLine(rand.nextInt(width), rand.nextInt(height), rand.nextInt(width), rand.nextInt(height));
		}
		
		//Send the check-code image back to Browser
		response.setContentType("image/jpeg");
		ImageIO.write(image, "jpg", response.getOutputStream());
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}
}
