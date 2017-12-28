package com.et.model.desk;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.coyote.http11.filters.VoidInputFilter;

import com.et.model.DbUtils;
import com.et.model.PageTools;



/**
 * 餐桌类
 * @author Administrator
 *
 */
public class MyDesk {
	public PageTools getPage(String name,Integer curPage){
		Integer totalCount=getPageCount(name);
		PageTools pt=new PageTools(curPage, totalCount, null);
		StringBuffer sb=new StringBuffer();
		String sql="select * from (select t.*,rownum rn from DESK t where t.dname like '%"+name+"%')"
				+ " where rn>="+pt.getStartIndex()+" and rn<="+pt.getEndIndex();
		List<Map> result=DbUtils.query(sql);
		
		pt.setData(result);
		return pt;
	
	}
	/**
	 * 获取总条数
	 */
	public Integer getPageCount(String name){
		if(name==null){
			name="";
		}
		String sql="select count(rowid) as cr from DESK  where 1=1 and dname like '%"+name+"%'";
		List<Map> result=DbUtils.query(sql);
		return Integer.parseInt(result.get(0).get("CR").toString());
	}
	
	/**
	 *添加餐桌的方法
	 */
	public void insertDesk(String deskName){
		String sql="insert into desk values((select max(deskid)+1 from desk),'"+deskName+"',0,'')";
		try {
			DbUtils.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 *删除餐桌的方法
	 */
	public void deleteDesk(String deskId){
		String sql="delete from desk where deskid="+deskId;
		
		try {
			DbUtils.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 *退桌的方法
	 */
	public void updateDesk(String deskId,String dstate){
		String sql="update desk set dstate="+dstate+" where deskid="+deskId;
		try {
			DbUtils.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
