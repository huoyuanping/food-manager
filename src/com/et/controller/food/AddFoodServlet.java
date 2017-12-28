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
		//�����ַ���
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		//��ȡ����			
		String foodName = null;
		String typeId = null;
		String price = null;
		String desc = null;
		String imgPath = null;

		//���ڴ��������ļ��ϴ��Ĺ�����
		DiskFileItemFactory factory=new DiskFileItemFactory();
		//�����ļ��ϴ�������
		ServletFileUpload upload=new ServletFileUpload(factory);
		try {
			//�������������ı����뻹���ļ�
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem fi : items) {
				if(fi.isFormField()){	//fi.isFormField()=true��ʾ������ı���false�ļ�
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
					
					//��ȡ�ļ���
					String fileName=fi.getName();
					
					/*//ƴ��ͼƬ·��
					imgPath = "style/images/" + fileName; */
					
					imgPath="/"+fileName;
					//��ȡ�ϴ��ļ�����
					InputStream is = fi.getInputStream();
					
					String destPath =absPath + imgPath;
					
					//����� 
					FileOutputStream fis=new FileOutputStream(destPath);
					
					//������
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
		
		//���
		mf.insertFood(typeId, foodName, price, imgPath, desc);
		System.out.println("���ID��" + typeId);
		System.out.println("������" + foodName);
		System.out.println("��Ǯ��" + price);
		System.out.println("ͼƬ·����" + imgPath);
		System.out.println("��飺" + desc);
		
		//����ת��
		request.getRequestDispatcher("/FoodServlet").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
