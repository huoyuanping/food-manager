package com.et.controller.food;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.et.model.food.MyFood;


public class AddFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 String absPath = "E:/myImages";
    public AddFoodServlet() {
        super();
    }

	MyFood mf=new MyFood();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		//获取参数			
		String foodName = null;
		String typeId = null;
		String price = null;
		String desc = null;
		String imgPath = null;

		//用于创建解析文件上传的工厂类
		DiskFileItemFactory factory=new DiskFileItemFactory();
		//解析文件上传的请求
		ServletFileUpload upload=new ServletFileUpload(factory);
		try {
			//解析出到底是文本输入还是文件
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem fi : items) {
				if(fi.isFormField()){	//fi.isFormField()=true表示输入的文本框false文件
					if ("typeId".equals(fi.getFieldName())) {
						typeId = fi.getString();
					} else if ("foodName".equals(fi.getFieldName())) {
						foodName = fi.getString("UTF-8");
					} else if ("price".equals(fi.getFieldName())) {
						price = fi.getString();
					} else {
						desc = fi.getString("UTF-8");
					}
				} else {
					
					//获取文件名
					String fileName=fi.getName();
					
					/*//拼接图片路径
					imgPath = "style/images/" + fileName; */
					
					imgPath="/"+fileName;
					//获取上传文件的流
					InputStream is = fi.getInputStream();
					
					String destPath =absPath + imgPath;
					
					//输出流 
					FileOutputStream fis=new FileOutputStream(destPath);
					
					//缓冲流
					byte[] bt=new byte[1024];
					int readLength=-1;
					while((readLength=is.read(bt))!=-1){
						fis.write(bt, 0, readLength);
						fis.flush();
					}
					fis.close();
					is.close();
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//添加
		mf.insertFood(typeId, foodName, price, imgPath, desc);
		System.out.println("类别ID：" + typeId);
		System.out.println("菜名：" + foodName);
		System.out.println("价钱：" + price);
		System.out.println("图片路径：" + imgPath);
		System.out.println("简介：" + desc);
		
		//请求转发
		request.getRequestDispatcher("/FoodServlet").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
