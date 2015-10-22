<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>**管理系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <div align="center">
    	<font color="red">
    		<c:choose>
    			<c:when test="${param.error == 1}">
    				用户名或密码错误，请重新输入
    			</c:when>
    			<c:when test="${param.error == 2}">
    				请先登录
    			</c:when>
    			<c:when test="${param.error == 3}">
    				无访问此页面的权限
    			</c:when>
    		</c:choose>
    	</font>
    	
    	<c:choose>
	  		<c:when test="${empty sessionScope.user }">
			    <form action="<c:url value='/userServlet?cmd=login'/>" method="post">
		    		用户名：<input type="text" name="username"><br/>
		    		密&nbsp;码：<input type="text" name="pswd"><br/>
		    		<input type="submit" value="登录">
	    		</form>		
	  		</c:when>
	  		<c:otherwise>
	  			<jsp:forward page="/mainServlet"></jsp:forward>
	  		</c:otherwise>
	  	</c:choose>
    </div>
  </body>
</html>
