<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>当前在线用户列表</title>
</head>
<body>
	共有：<c:choose><c:when test="${empty loggedCnt}">0</c:when>
					<c:otherwise>${loggedCnt}</c:otherwise></c:choose>人登录，${connectedCnt}人在线。
	<table border="1">
		<tr align="center">
			<td>Session ID</td>
			<td>用户名</td>
			<td>用户IP</td>
			<td>登录时间</td>
			<td>最后访问时间</td>
			<td>操作</td>
		</tr>
		<c:forEach var="map" items="${onlineList}">
			<tr>
				<td>${map.id }</td>
				<td>${map.name }</td>
				<td>${map.ip }</td>
				<td>${map.createTime }</td>
				<td>${map.lastTime }</td>
				<td><a href="/onlinecnt/kickOut?id=${map.id}">踢出</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>