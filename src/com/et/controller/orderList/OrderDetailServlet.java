package com.et.controller.orderList;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.et.model.PageTools;
import com.et.model.orderList.MyOrderDetail;

/**
 * 订单详细servlet
 *
 *
 */
public class OrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public OrderDetailServlet() {
        super();
       
    }

	MyOrderDetail mod=new MyOrderDetail();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");
		    
		  	String orderId = request.getParameter("orderId");
		   
		    String curPage = request.getParameter("curPage");
		    Integer cur=1;
		    if (curPage != null) {
	            cur= Integer.parseInt(curPage);
	        }
	
			PageTools list=mod.getOrderDetail(cur,orderId);
			//设置到request作用域中
			request.setAttribute("orderDetail", list);
			//请求转发
		    request.getRequestDispatcher("/detail/orderDetail.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
