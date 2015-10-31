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
    
    <title>相册管理系统</title>
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
    	<h2>相册管理系统</h2>
    	<form action="<c:url value='/upServlet' />" method="post" enctype="multipart/form-data">
    		图片：<input type="file" name="file" width="150"/><br/>
    		描述：<input type="text" name="note" width="200"/><br/>
    		<input type="submit" value="上传"/>
    	</form>
    	<a href="<c:url value='/showServlet'/>">打开所有照片</a>
    </div>
  </body>
</html>
