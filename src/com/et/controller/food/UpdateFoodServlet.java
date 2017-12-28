package com.et.controller.food;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.et.model.food.MyFood;


public class UpdateFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UpdateFoodServlet() {
        super();
       
    }

	MyFood mf=new MyFood();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		//获取列名
		/*String  foodId=request.getParameter("foodId");
		String price=request.getParameter("price");
		String typeid = request.getParameter("typeId");
		String foodName=request.getParameter("foodName");
		mf.updateFood(foodId,foodName, price,typeid);*/
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload=new ServletFileUpload(factory);
		try {
			//解析出到底是文本输入还是文件
			List<FileItem> items = upload.parseRequest(request);
			FileItem f0 = items.get(0);
			FileItem f1 = items.get(1);
			FileItem f2 = items.get(2);
			FileItem f3 = items.get(3);
			FileItem f4 = items.get(4);
			FileItem f5 = items.get(5);
			FileItem f6 = items.get(6);
			String typeId = f0.getString("utf-8");
			String foodId = f1.getString("utf-8");
			String foodName = f2.getString("utf-8");
			String price = f3.getString("utf-8");
			String introduce = f4.getString("utf-8");
			String image = f5.getString("utf-8");
			String imgurl = f6.getName();
			String path="";
			if(imgurl==null || imgurl.equals("")){
				path = image;
			}else{
				path = "/"+imgurl;
				f6.write(new File("E:/myImages/"+imgurl));
			}
			mf.updateFood(typeId,foodId,foodName,price,introduce,path);
		} catch (Exception e) {
		}
			
		//请求转发
		request.getRequestDispatcher("/FoodServlet").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
