package com.et.controller.desk;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.et.model.desk.MyDesk;


public class AddDeskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AddDeskServlet() {
        super();
        
    }

	MyDesk md=new MyDesk();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�����ַ���
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		//��ȡ����
		String dname=request.getParameter("dname");
		//���
		md.insertDesk(dname);
		//����ת��
		request.getRequestDispatcher("/DeskServlet").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
