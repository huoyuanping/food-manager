package com.et.controller.food;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.et.model.PageTools;
import com.et.model.food.MyFood;


public class FoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public FoodServlet() {
        super();
       
    }

	MyFood mf=new MyFood();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取列名
		String foodName=request.getParameter("foodName");
		//获取当前页
		String curPage=request.getParameter("curPage");
		Integer cur=1;
		if(curPage!=null){
			cur=Integer.parseInt(curPage);
		}
		if(foodName==null){
			foodName="";
		}
		PageTools list=mf.getPage(foodName, cur);
		//设置到request作用域中
		request.setAttribute("foodlist", list);
		//请求转发
		request.getRequestDispatcher("/detail/foodList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
