package com.et.model.food;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.et.model.DbUtils;
import com.et.model.PageTools;

public class MyFood {
	/**
	 * 获取总条数
	 */
	public Integer getPageCount(String name){
		String sql="select count(rowid) as cr from FOOD  where 1=1 and foodname like '%"+name+"%'";
		List<Map> result=DbUtils.query(sql);
		return Integer.parseInt(result.get(0).get("CR").toString());
	}
	
	public PageTools getPage(String name,Integer curPage){
		if(name == null){
			name = "";
		}
		Integer totalCount=getPageCount(name);
		PageTools pt=new PageTools(curPage, totalCount, null);
		StringBuffer sb=new StringBuffer();
		String sql="select * from (select t.*,rownum rn from FOOD t where t.foodName like '%"+name+"%')"
				+ " where rn>="+pt.getStartIndex()+" and rn<="+pt.getEndIndex();
		List<Map> result=DbUtils.query(sql);
		pt.setData(result);
		return pt;
	
	}
	
	/**
	 * 删除菜品的方法
	 */
	public void deleteFood(String foodId){
		String sql="delete from FOOD where foodId="+foodId;
		try {
			DbUtils.execute(sql);
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
	

	/**
	 *添加菜品的方法
	 */
	public void insertFood(String typeId , String foodName , String price , String imgPath , String desc){
		String sql="insert into food values((select nvl(max(FOODID),0)+1 from food)  , " + typeId + " , '" + foodName + "' , " + price + " , '" + imgPath + "' , '" + desc +"')";
		try {
			DbUtils.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 *查询所有菜系的方法
	 */
	public List<Map> getListFood(){
		String sql="select * from  foodtype ";
		return DbUtils.query(sql);
	}
	/**
	 *修改菜品的方法
	 * @param path 
	 * @param introduce 
	 */
	public void updateFood(String typeId, String foodId, String foodName, String price, String introduce, String path) {
		String sql="update FOOD set typeid="+typeId+", foodname='"+foodName+"',price='"+price+"',descs='"+introduce+"',imagepath='"+path+"' where foodid="+foodId;
	
		try {
			
			DbUtils.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
