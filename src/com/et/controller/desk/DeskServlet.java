package com.et.controller.desk;
/**
 * 显示所有的桌面类
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

	//调用模型层
    MyDesk md=new MyDesk();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//获取参数
		String name=request.getParameter("dname");
		//获取当前页
		String curPage=request.getParameter("curPage");
		Integer cur=1;
		if(curPage!=null){
			cur=Integer.parseInt(curPage);
		}
		if(name==null){
			name="";
		}
		PageTools list=md.getPage(name, cur);
		//设置到request作用域中
		request.setAttribute("desklist", list);
		//请求转发
		request.getRequestDispatcher("/detail/boardList.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
