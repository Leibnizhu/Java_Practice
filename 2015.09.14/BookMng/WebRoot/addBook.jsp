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
	<c:url var="addBookURL" value="/addBookServ"></c:url>
	<div align="center">
		<form action="${addBookURL}" method="post">
			<table border="1">
				<tr>
					<td>ID</td>
					<td><input name="id" type="text"/></td>
				</tr>
				<tr>
					<td>标题</td>
					<td><input name="title" type="text"/></td>
				</tr>
				<tr>
					<td>价格</td>
					<td><input name="price" type="text"/></td>
				</tr>
				<tr>
					<td></td>
					<td><input value="添加" type="submit"/></td>
				</tr>
					
			</table>
		</form>
	</div>
</body>
</html>