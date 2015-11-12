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
			width:230px;
		}
		.txt{
			border:1px solid gray;
			width:100%;
		}
	</style>
  </head>
  
  <body style="text-align:center;margin-top:0px;padding:10%;">
  	<div style="background:#F8F8F8;margin:0 auto;">
	  	<form method="post" action="<c:url value='/userServlet?cmd=login'/>">
	  		<table align="center">
	  			<tr>
	  				<td colspan="2" align="center" style="height:20px;">用户登录</td>
	  			</tr>
	  			<tr>
	  				<td colspan="2" align="center" style="height:20px;color:red;">
	  					<c:if test="${not empty sessionScope.errorMsg}">
	  						<font size=2>${sessionScope.errorMsg}</font>
	  						<c:remove var="errorMsg" scope="session"/>
	  					</c:if>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>用户名</td>
	  				<td><input class="txt" type="text" name="name" value="${user.name}"/></td>
	  			</tr>
	  			<tr>
	  				<td>密&nbsp;码</td>
	  				<td><input class="txt" type="password" name="pswd"/></td>
	  			</tr>
	  			<tr>
	  				<td colspan="2" align="center" style="height:20px;">
	  					<input type="submit" value="登录"/>&nbsp;
	  					<a href="<c:url value='/jsp/register.jsp'/>">注册</a>
	  				</td>
	  			</tr>
	  		</table>
	  	</form>
	 </div>
  </body>
</html>
