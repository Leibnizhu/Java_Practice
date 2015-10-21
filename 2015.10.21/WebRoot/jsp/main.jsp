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
  
  <body style="margin:0px">
    <div align="center">
    	<table border="1" width="100%" height="100%">
    		<tr height="10%">
    			<td colspan="2" align="center">欢迎进入管理系统</td>
    		</tr>
    		<tr>
    			<td width="10%" valign="top" align="center">
	    			<c:forEach items="${lsMenu}" var="menu">
	    				<a target="mainFrame" href="<c:url value='${menu.url }'/>">${menu.name }</a><br/>
	    			</c:forEach>
    			</td>
    			<td>
    				<iframe name="mainFrame" frameborder="0" width="100%" height="100%"></iframe>
    			</td>
    		</tr>
    	</table>
    </div>
  </body>
</html>
