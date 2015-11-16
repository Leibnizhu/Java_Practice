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
		table {
			border:1px solid gray;
			border-collapse:collapse;
			width:1024px;
			height:120%;
		}
		td{
			border:1px solid gray;
		}
	</style>
  </head>
  
  <body>
  	<div style="background:#F8F8F8;margin:0 auto;width:1024px;">
	  	<table>
	  		<tr style="background:aqua;">
	  			<td colspan="2" height="60" align="center">
	  				<font size=5 >网上购书中心</font>
		  			<div style="float:right">
		  				<c:choose>
		  					<c:when test="${empty sessionScope.user}">
		  						<a target="mainFrame" href="<c:url value='/jsp/login.jsp'/>" >登录</a>
		  						<a target="mainFrame" href="<c:url value='/jsp/register.jsp'/>" >注册</a>
		  					</c:when>
			  				<c:otherwise>
			  					欢迎你，<a href="">${user.name}</a>|
			  					<a href="">订单</a>|
		  						<a target="mainFrame" href="<c:url value='/secury/cart.jsp'/>">购物车</a>|
		  						<a href="<c:url value='/userServlet?cmd=logout'/>">退出</a>
			  				</c:otherwise>
		  				</c:choose>
		  			</div>
	  			</td>
	  		</tr>
	  		<tr>
	  			<td align="center" valign="top" style="width:100px">
	  				<a href="<c:url value='/bookServlet'/>" target="mainFrame">全部图书</a><br/>
	  				<c:forEach items="${cateList}" var="cate">
	  					<a href="<c:url value='/bookServlet?categoryid=${cate.id}'/>" target="mainFrame">${cate.name}</a><br/>
	  				</c:forEach>
	  			</td>
	  			<td>
	  				<iframe name="mainFrame" src="<c:url value='/bookServlet'/>" frameborder="0" width="100%" height="100%"></iframe>
	  			</td>
	  		</tr>
	  	</table>
	 </div>
  </body>
</html>
