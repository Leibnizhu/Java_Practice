<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>网上购书中心</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		*{
  			font-size:10pt;
  		}
  		.book {
			border:1px solid gray;
			width:160px;
			height:220px;
			text-align:center;
			float: left;
			margin:3px;
		}
		.img{
			border:0px solid gray;
			width:160px;
			height:160px;
		}
	</style>
  </head>
  
  <body>
	<div style="background:#FFFFFF;margin:0 auto;width:900px;text-align:center">
	  	显示所有图书：<br/>
	  	<c:forEach items="${listBook}" var="book">
	  		<div class="book">
	  			<a href="<c:url value='/bookServlet?cmd=detail&bookid=${book.id}'/>">
	  				<img class="img" src="<c:url value='/imgs/${book.image}'/>"/><br/>
	  				${book.name}
	  			</a>
	  			<br/>
	  			价格：
	  			<c:choose>
	  				<c:when test="${book.discount == 1}">
	  					${book.price} 
	  				</c:when>
	  				<c:otherwise>
	  					<font style="text-decoration: line-through;">${book.price}</font>
	  					<font style="color:red;"><fmt:formatNumber value="${book.price*book.discount}" pattern="#,###.00"/></font>
	  				</c:otherwise>
	  			</c:choose>
	  			元
	  		</div>
	  	</c:forEach>
	 </div>
  </body>
</html>
