package com.et.controller.desk;
/**
 * ��ʾ���е�������
 */
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.et.model.PageTools;
import com.et.model.desk.MyDesk;


public class DeskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public DeskServlet() {
        super();
       
    }

	//����ģ�Ͳ�
    MyDesk md=new MyDesk();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//��ȡ����
		String name=request.getParameter("dname");
		//��ȡ��ǰҳ
		String curPage=request.getParameter("curPage");
		Integer cur=1;
		if(curPage!=null){
			cur=Integer.parseInt(curPage);
		}
		if(name==null){
			name="";
		}
		PageTools list=md.getPage(name, cur);
		//���õ�request��������
		request.setAttribute("desklist", list);
		//����ת��
		request.getRequestDispatcher("/detail/boardList.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
