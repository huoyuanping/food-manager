package com.et.controller.food;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImageServlet
 */
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	   
    public ImageServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String image=request.getParameter("imageName");
		 String absPath = "E:/myImages"+image;
		 //¶ÁÈ¡ÎÄ¼þÍ¼Æ¬
		 BufferedImage bi=ImageIO.read(new File(absPath));
		 ImageIO.write(bi, "png",response.getOutputStream());
		 
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
