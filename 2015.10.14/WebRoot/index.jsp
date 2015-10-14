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
    
    <title>My JSP 'index.jsp' starting page</title>
	
  </head>
  	
  <body>
    <c:choose>
    	<c:when test="${empty sessionScope.name }">
    		<form name="x" method="post" action="<c:url value='/login'/>">
    			用户名：<input type="text" name="name"><br/>
    			保持登录：<div><input type="radio" name="auto" checked="checked" value="-1">永不<br/>
    						<input type="radio" name="auto" value="1">1天<br/>
    						<input type="radio" name="auto" value="7">1周</br>
    						<input type="submit">
    				</div>    			
    		</form>
    	</c:when>
    	<c:otherwise>
    		已登录,${name },您好<br/>
    		<a href="<c:url value='/login'/>">退出</a>
    	</c:otherwise>
    </c:choose>
    
  </body>
</html>
