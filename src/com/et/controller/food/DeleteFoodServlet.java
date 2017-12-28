package com.et.controller.food;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.et.model.food.MyFood;


public class DeleteFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public DeleteFoodServlet() {
        super();
    }

	MyFood mf=new MyFood();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取列名
		String foodId=request.getParameter("foodId");
		mf.deleteFood(foodId);
		//请求转发
		request.getRequestDispatcher("/FoodServlet").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
