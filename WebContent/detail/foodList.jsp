<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<!-- 包含公共的JSP代码片段 -->
	
<title>无线点餐平台</title>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/detail/style/js/jquery.js"></script>
<script type="text/javascript" src="style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath}/detail/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/detail/style/css/index_1.css" />
<script type="text/javascript">
function deleteFood(fId){
	if(confirm("是否删除当前数据")){
		window.location="DeleteFoodServlet?foodId="+fId;
	}
}
</script>
</head>
<body>
<!-- 页面标题 -->
<div id="TitleArea">
	<div id="TitleArea_Head"></div>
	<div id="TitleArea_Title">
		<div id="TitleArea_Title_Content">
			<img border="0" width="13" height="13" src="style/images/title_arrow.gif"/> 菜品列表
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>


	<!-- 过滤条件 -->
	<div id="QueryArea">
		<form action="${pageContext.request.contextPath}/FoodServlet" method="get">
			<input type="hidden" name="method" value="search">
			<input type="text" name="foodName" title="请输入菜品名称" value="${param.foodName }">
			<input type="submit" value="搜索">
		</form>
	</div>
<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" align="center" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
            <tr align="center" valign="middle" id="TableTitle">
				<td>菜编号</td>
				<td>菜名</td>
				<td>所属菜系</td>
				<td>价格</td>
				<td>操作</td>
			</tr>
		</thead>	
		<!--显示数据列表 -->
        <tbody id="TableData">
        <jsp:useBean id="fid" class ="com.et.model.food.MyFood"></jsp:useBean>
			<c:forEach var="tmp" items="${requestScope.foodlist.data }">
			<tr align="center">
				<td>${pageScope.tmp.FOODID}</td>
				<td>${pageScope.tmp.FOODNAME}</td>
				<c:forEach var="tmp1" items="${fid.listFood }">
				 <c:if test="${tmp1.TYPEID == tmp.TYPEID }">
				 <td>${tmp1.TYPENAME }</td>
				 </c:if>
					
				</c:forEach>
				<td>${pageScope.tmp.PRICE}</td>
				<td>
					<a href="detail/updateFood.jsp
					?foodId=${pageScope.tmp.FOODID}
					&foodName=${pageScope.tmp.FOODNAME}
					&price=${pageScope.tmp.PRICE}
					&typeId=${pageScope.tmp.TYPEID}
					&imgPath=${pageScope.tmp.IMAGEPATH}
					&desc=${pageScope.tmp.DESCS}
					"class="FunctionButton">更新</a>
								
					<a href="javascript:deleteFood(${pageScope.tmp.FOODID})" class="FunctionButton">删除</a>				
				</td>
			</tr>
        </c:forEach>
			
        <tr>
        	<td colspan="4">
	        	<a href="${pageContext.request.contextPath}/FoodServlet?curPage=1">首页</a>
				<a href="${pageContext.request.contextPath}/FoodServlet?curPage=${requestScope.foodlist.prePage}">上一页</a>
				<c:forEach var="i" begin="1" end="${requestScope.foodlist.totalPage}" step="1">
				<a href="${pageContext.request.contextPath}/FoodServlet?curPage=${pageScope.i}">${pageScope.i}</a>
				</c:forEach> 
				<a href="${pageContext.request.contextPath}/FoodServlet?curPage=${requestScope.foodlist.nextPage}">下一页</a>
				<a href="${pageContext.request.contextPath}/FoodServlet?curPage=${requestScope.foodlist.totalPage}">尾页</a>
        	</td>
        </tr>
        </tbody>
    </table>
	
   <!-- 其他功能超链接 -->
	<div id="TableTail" align="center">
		<div class="FunctionButton"><a href="${pageContext.request.contextPath}/detail/saveFood.jsp">添加</a></div>
    </div> 
</div>
</body>
</html>
