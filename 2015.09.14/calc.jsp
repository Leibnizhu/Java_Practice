<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page isErrorPage="true" %>
<%@ page errorPage="calc.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="leibniz.hu.Calculator" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'calc.jsp' starting page</title>
    
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
    <jsp:useBean id="calc" class="leibniz.hu.Calculator"></jsp:useBean>
    <jsp:setProperty name="calc" property="*"></jsp:setProperty>
    <c:if test="${empty pageContext.exception }">
    	<% calc.calc(); %>
    </c:if>
    <c:url var="calUrl" value="/calc.jsp"></c:url>
    
    <form action="${calUrl}" method="post">
	    <table border="1">
	    	<tr><td colspan="3" align="center">简易计算器</td></tr>
	    	<tr><td colspan="3" align="center">计算结果：${calc.num1Str} ${calc.opt} ${calc.num2Str} = ${calc.result}</td></tr>
	    	<tr>
	    		<td><input type="text" name="num1Str"/></td>
	    		<td><select name="opt">
	    			<option value="+">+</option>
	    			<option value="-">-</option>
	    			<option value="*">*</option>
	    			<option value="/">/</option>
	    		</select></td>
	    		<td><input type="text" name="num2Str"/></td>
	    	</tr>
	    	<tr><td colspan="3" align="center"><input type="submit" value="计算"/></td></tr>
	    </table>
    </form>
  </body>
</html>
