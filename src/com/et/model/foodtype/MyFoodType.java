package com.et.model.foodtype;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.et.model.DbUtils;
import com.et.model.PageTools;

public class MyFoodType {
	
	/**
	 * 获取总条数
	 */
	public Integer getPageCount(String name){
		if(name==null){
			name="";
		}
		String sql="select count(rowid) as cr from FOODTYPE  where 1=1 and typeName like '%"+name+"%'";
		List<Map> result=DbUtils.query(sql);
		return Integer.parseInt(result.get(0).get("CR").toString());
	}
	
	public PageTools getPage(String name,Integer curPage){
		Integer totalCount=getPageCount(name);
		PageTools pt=new PageTools(curPage, totalCount, null);
		StringBuffer sb=new StringBuffer();
		String sql="select * from (select t.*,rownum rn from FOODTYPE t where t.typeName like '%"+name+"%')"
				+ " where rn>="+pt.getStartIndex()+" and rn<="+pt.getEndIndex();
		List<Map> result=DbUtils.query(sql);
		
		pt.setData(result);
		return pt;
	
	}
	/**
	 *添加菜系的方法
	 */
	public void insertFood(String foodName){
		
		String sql="insert into FOODTYPE  values((select max(typeid)+1 from FOODTYPE),'"+foodName+"')";
		
		try {
			DbUtils.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除菜系的方法
	 */
	public void deleteFood(String typeId){
		String sql="delete from FOODTYPE where typeId="+typeId;
		try {
			DbUtils.execute(sql);
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
	
	/**
	 *修改菜系的方法
	 */
	public void updateFoodType(String typeId,String typeName){
		
		String sql="update FOODTYPE set typeName='"+typeName+"' where typeId="+typeId;
		try {
			
			DbUtils.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 *查询所有菜系的方法
	 */
	public List<Map> getFoodType(){
		
		String sql="select * from foodtype";	
			
			return DbUtils.query(sql);
		
	}
	

}
