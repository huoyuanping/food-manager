package com.et.controller.orderList;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.et.model.orderList.MyOrderList;

/**
 *∂©µ•Ω·’Àservlet
 */
public class MyOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public MyOrderServlet() {
        super();
       
    }

	MyOrderList list=new MyOrderList();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderId=request.getParameter("orderId");
		try {
			list.payOrder(orderId);
			request.getRequestDispatcher("/OrderListServlet").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
