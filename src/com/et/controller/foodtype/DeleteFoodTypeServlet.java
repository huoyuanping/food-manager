package com.et.controller.foodtype;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.et.model.foodtype.MyFoodType;


public class DeleteFoodTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public DeleteFoodTypeServlet() {
        super();
        
    }

	MyFoodType mft=new MyFoodType();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取列名
		String  typeId=request.getParameter("typeId");
		mft.deleteFood(typeId);
		//请求转发
		request.getRequestDispatcher("/FoodTypeServlet").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
