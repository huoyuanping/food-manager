package com.et.controller.foodtype;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.et.model.foodtype.MyFoodType;


public class AddFoodTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AddFoodTypeServlet() {
        super();
      
    }

    MyFoodType mft=new MyFoodType();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�����ַ���
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		//��ȡ����
		
		String FoodName=request.getParameter("typeName");
		//���
		mft.insertFood(FoodName);
		//����ת��
		request.getRequestDispatcher("/FoodTypeServlet").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
