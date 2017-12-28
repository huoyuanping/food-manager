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
		//��ȡ����
		String foodName=request.getParameter("foodName");
		//��ȡ��ǰҳ
		String curPage=request.getParameter("curPage");
		Integer cur=1;
		if(curPage!=null){
			cur=Integer.parseInt(curPage);
		}
		if(foodName==null){
			foodName="";
		}
		PageTools list=mf.getPage(foodName, cur);
		//���õ�request��������
		request.setAttribute("foodlist", list);
		//����ת��
		request.getRequestDispatcher("/detail/foodList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
