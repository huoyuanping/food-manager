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
		//��ȡ����
		String foodName=request.getParameter("typeName");
		//��ȡ��ǰҳ
		String curPage=request.getParameter("curPage");
		Integer cur=1;
		if(curPage!=null){
			cur=Integer.parseInt(curPage);
		}
		if(foodName==null){
			foodName="";
		}
		PageTools list=mft.getPage(foodName, cur);
		//���õ�request��������
		request.setAttribute("foodtypelist", list);
		//����ת��
		request.getRequestDispatcher("/detail/cuisineList.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
