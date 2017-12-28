package com.et.controller.orderList;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.et.model.PageTools;
import com.et.model.orderList.MyOrderList;

/**
 * ¶©µ¥ÁÐ±íservlet
 */
public class OrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public OrderListServlet() {
        super();
       
    }

	MyOrderList list=new MyOrderList();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");
		 response.setCharacterEncoding("UTF-8");
		    
		    String curPage = request.getParameter("curPage");
		    Integer intCurPage = 1;
		    if (curPage != null) {
	            intCurPage = Integer.parseInt(curPage);
	        }
		    
		    try {
		    	PageTools pager =list.getOrderList(intCurPage);
	            request.setAttribute("orderList", pager);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		    request.getRequestDispatcher("/detail/orderList.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		doGet(request, response);
	}

}
