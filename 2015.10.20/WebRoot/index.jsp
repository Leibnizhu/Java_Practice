<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>在线人数统计Demo</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
  	<c:choose>
  		<c:when test="${empty sessionScope.user }">
		    <form method="post" action="<c:url value='/login'/>">
			  	  用户名：<input type="text" name="user"><br/>
			    <input type="submit" value="登录">
		    </form>  		
  		</c:when>
  		<c:otherwise>
  			欢迎您，${user}<br/>
  			<a href="<c:url value='/login'/>">退出登录</a>
  		</c:otherwise>
  	</c:choose>
  	<hr/>
    <a href="<c:url value='/showOnline'/>">查看在线用户</a>
  </body>
</html>
