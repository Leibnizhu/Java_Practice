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
			<td align="center">编号</td>
			<td align="center">标题</td>
			<td align="center">价格</td>
			<td align="center">操作</td>
		</tr>

		<c:forEach items="${requestScope.bookList}" var="book">
			<c:url value="/preUpdateBookServ?bookID=${book.id}" var="preUpBookServ"></c:url>
			<c:url value="/deleteBookServ?bookID=${book.id}" var="delBookServ"></c:url>
			<tr>
				<td>${book.id}</td>
				<td>${book.title}</td>
				<td>${book.price}</td>
				<td>
					<a href="${preUpBookServ}">修改</a>
					<a href="${delBookServ}">删除</a>
				</td>
			</tr>
		</c:forEach>	
	</table>
</body>
</html>