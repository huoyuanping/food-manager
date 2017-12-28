package com.et.controller.desk;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.et.model.desk.MyDesk;


public class DeleteDesk extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public DeleteDesk() {
        super();
       
    }

	//调用数据库操作类
    MyDesk md=new MyDesk();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取参数名
		String did=request.getParameter("did");
		md.deleteDesk(did);
		//请求转发
		request.getRequestDispatcher("/DeskServlet").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
