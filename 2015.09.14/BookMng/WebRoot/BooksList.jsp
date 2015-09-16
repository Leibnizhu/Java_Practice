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
	<div align="center">
		<table border="1">
				<tr>
					<td width="80px" align="center">编号</td>
					<td width="200px" align="center">标题</td>
					<td width="200px" align="center">价格</td>
					<td align="center">操作</td>
				</tr>
		
				<c:forEach items="${requestScope.bookList}" var="book">
					<c:url value="/preUpdateBookServ?bookID=${book.id}" var="preUpBookServ"></c:url>
					<c:url value="/deleteBookServ?bookID=${book.id}" var="delBookServ"></c:url>
					<tr>
						<td align="center">${book.id}</td>
						<td align="center">${book.title}</td>
						<td align="center">${book.price}</td>
						<td align="center">
							<a href="${preUpBookServ}">修改</a>
							<a href="${delBookServ}">删除</a>
						</td>
					</tr>
				</c:forEach>	
		</table>
	</div>
</body>
</html>