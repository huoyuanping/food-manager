package com.et.controller.foodtype;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.et.model.PageTools;
import com.et.model.foodtype.MyFoodType;

/**
 * Servlet implementation class FoodTypeServlet
 */
public class FoodTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public FoodTypeServlet() {
        super();
       
    }

	MyFoodType mft=new MyFoodType();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取列名
		String foodName=request.getParameter("typeName");
		//获取当前页
		String curPage=request.getParameter("curPage");
		Integer cur=1;
		if(curPage!=null){
			cur=Integer.parseInt(curPage);
		}
		if(foodName==null){
			foodName="";
		}
		PageTools list=mft.getPage(foodName, cur);
		//设置到request作用域中
		request.setAttribute("foodtypelist", list);
		//请求转发
		request.getRequestDispatcher("/detail/cuisineList.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
