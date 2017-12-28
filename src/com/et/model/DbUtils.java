package com.et.model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.sun.crypto.provider.RSACipher;

/**
 * jdbc�ķ�װ��
 * @author Administrator
 *
 */
public class DbUtils {
    //��ȡ��Դ�ļ�
	static Properties p=new Properties();
	static{
		//����Դ�ļ���src��ʱ�� /jdbc.properties
		InputStream is=DbUtils.class.getResourceAsStream("orcle.properties");
		try {
			p.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * ��ȡ����
	 */
	public static Connection getConnection(){
		String url=p.getProperty("url");
		String driverClass=p.getProperty("driverClass");
		String uname=p.getProperty("username");
		String password=p.getProperty("password");
		Connection con =null;
		try {
			Class.forName(driverClass);
			 con=DriverManager.getConnection(url, uname, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	/**
	 * ��ѯ����
	 * @param sql
	 * @return
	 */
	public static List<Map> query(String sql){
		//��ȡ����
		Connection con=getConnection();
		PreparedStatement pst;
		List list=null;
		try {
			//Ԥ�����sql���
			pst = con.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			list=new ArrayList<>();
			//��ȡ����
			ResultSetMetaData rsd=rs.getMetaData();
			//��ȡ�еĸ���
			int count=rsd.getColumnCount();
			while(rs.next()){
				Map map=new HashMap();
				for(int i=1;i<=count;i++){
					//����
					String cName=rsd.getColumnLabel(i);
					//��ֵ
					String cValue=rs.getString(i);
					map.put(cName, cValue);
				}
				//��ӵ�������
				list.add(map);
			}
		rs.close();
		con.close();
		pst.close();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * ��ɾ�ķ���
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public static int execute(String sql) throws SQLException{
			Connection conn=getConnection();
			PreparedStatement pst=conn.prepareStatement(sql);
			int i=pst.executeUpdate();
			pst.close();
			conn.close();
			return i;
	}
	
	
}
