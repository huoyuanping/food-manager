package com.et.model.orderList;

import java.util.List;
import java.util.Map;

import com.et.model.DbUtils;
import com.et.model.PageTools;

public class MyOrderDetail {
	/**
	 * 获取订单总行数
	 */
	public Integer getPageCount(){
		
		String sql="select count(rowid) as cr from FOODORDERDETAIL ";
		List<Map> result=DbUtils.query(sql);
		return Integer.parseInt(result.get(0).get("CR").toString());
	}
	
	/**
	 * 获取订单分页数据
	 * @param curPage
	 * @return
	 */
	public PageTools getOrderDetail(Integer curPage,String orderId){
		if(orderId == null){
			orderId = "";
		}
		Integer totalCount=getPageCount();
		PageTools pt=new PageTools(curPage, totalCount, null);
		StringBuffer sb=new StringBuffer();
		String sql="select * from (select g.*,d.foodname,d.price, rownum rn from FOODORDERDETAIL g inner join food d on g.foodid=d.foodid where g.orderid="+orderId+") where rn>="+pt.getStartIndex()+" and rn<="+pt.getEndIndex();
		List<Map> result=DbUtils.query(sql);
		
		pt.setData(result);
		return pt;
	
	}

	
}
