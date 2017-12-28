package com.et.controller.desk;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.et.model.desk.MyDesk;


public class UpdateDeskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UpdateDeskServlet() {
        super();
       
    }

    //调用数据库操作类
    MyDesk md=new MyDesk();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取列名
		String did=request.getParameter("did");
		String dstate=request.getParameter("dstate");
		md.updateDesk(did,dstate);
		//请求转发
		request.getRequestDispatcher("/DeskServlet").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
