package com.et.model.orderList;

import java.util.List;
import java.util.Map;

import com.et.model.DbUtils;
import com.et.model.PageTools;

/**
 * 订单
 * @author Administrator
 *
 */
public class MyOrderList {
	/**
	 * 获取订单总行数
	 */
	public Integer getPageCount(){
		
		String sql="select count(rowid) as cr from FOODORDER";
		List<Map> result=DbUtils.query(sql);
		return Integer.parseInt(result.get(0).get("CR").toString());
	}
	/**
	 * 获取订单分页数据
	 * @param curPage
	 * @return
	 */
	public PageTools getOrderList(Integer curPage){
		Integer totalCount=getPageCount();
		PageTools pt=new PageTools(curPage, totalCount, null);
		StringBuffer sb=new StringBuffer();
		String sql="select * from (select g.*,d.dname,rownum rn from foodorder g inner join desk d on g.deskid=d.deskid ) where rn>="+pt.getStartIndex()+" and rn<="+pt.getEndIndex();
		List<Map> result=DbUtils.query(sql);
		
		pt.setData(result);
		return pt;
	
	}
	  /**
     * 详细订单
     * @param orderId
     * @throws Exception 
     */
    public void getOrderDetail(String orderId) throws Exception{
        String sql = "select * from FOODORDERDETAIL set ORDERSTATE = 1 where ORDERID = " + orderId;
        DbUtils.execute(sql);
    }
    /**
     * 订单结账
     * @param orderId
     * @throws Exception 
     */
    public void payOrder(String orderId) throws Exception{
        String sql = "update foodorder set ORDERSTATE = 1 where ORDERID = " + orderId;
        DbUtils.execute(sql);
    }
}
