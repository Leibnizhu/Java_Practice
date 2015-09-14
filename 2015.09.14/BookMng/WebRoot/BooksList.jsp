<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>编号</td>
			<td>标题</td>
			<td>价格</td>
			<td>操作</td>
		</tr>
		<!-- 获得request作用的值bookList，展示 -->
		<c:forEach items="${requestScope.bookList}" var="book">
			<tr>
				<td>${book.id}</td>
				<td>${book.title}</td>
				<td>${book.price}</td>
				<td>
					<input type="button" value="修改"/>
					<input type="button" value="删除"/>
				</td>
			</tr>
		</c:forEach>	
	</table>
</body>
</html>