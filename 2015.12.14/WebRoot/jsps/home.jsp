<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>员工管理系统</title>
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
  	<table width="1200">
  		<tr style="background:aqua;">
  			<td colspan="2" height="60" align="center">
  				<font size=5 >员工管理系统</font>
	  			<div style="float:right">
	  				您好，<s:property value="#session.user.userName"/>
	  			</div>
  			</td>
  		</tr>
  		<tr>
  			<td align="center" valign="top" style="width:100px">
  				<a href="" target="mainFrame">员工列表</a><br/>
  			</td>
  			<td>
  				<iframe name="mainFrame" src="<c:url value='/jsps/welcome.jsp'/>" frameborder="0" width="100%" height="100%">
  			</td>
  		</tr>
  	</table>
  </body>
</html>
</html>
