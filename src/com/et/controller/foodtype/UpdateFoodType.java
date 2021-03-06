package com.et.controller.foodtype;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.et.model.foodtype.MyFoodType;


public class UpdateFoodType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public UpdateFoodType() {
        super();
       
    }

    MyFoodType mft=new MyFoodType();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		//获取列名
		String  typeId=request.getParameter("typeId");
		String typeName=request.getParameter("typeName");
		mft.updateFoodType(typeId, typeName);
		//请求转发
		request.getRequestDispatcher("/FoodTypeServlet").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
