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
  			font-size:11pt;
  		}
  		table {
			border:1px solid gray;
			border-collapse:collapse;
			width:60%;
		}
		.img{
			border:0px solid gray;
			width:160px;
			height:160px;
		}
		td{
			text-align:center;
			border:1px solid gray;
		}
	</style>
  </head>
  
  <body style="text-align:center;margin-top:0px;padding:10%;">
  	<div style="background:#F8F8F8;margin:0 auto;">
		<table width=100% border="0">
			<tr>
				<td colspan="5">
					购物车的所有书籍：
				</td>
			</tr>
			<tr>
				<td>书名</td>
				<td>单价(元)</td>
				<td>数量</td>
				<td>小计(元)</td>
				<td>操作</td>
			</tr>
			<c:set value="0" var="sum"/>
			<c:forEach items="${cart}" var="entry">
				<tr>
					<td>${entry.value.name}</td>
					<td><c:choose>
		  				<c:when test="${entry.value.discount == 1}">
		  					${entry.value.price} 
		  				</c:when>
		  				<c:otherwise>
		  					<font style="text-decoration: line-through;">${entry.value.price}</font>
		  					<font style="color:red;"><b><fmt:formatNumber value="${entry.value.price*entry.value.discount}" pattern="#,###.00"/></b></font>
		  				</c:otherwise>
			  			</c:choose>
			  		</td>
					<td>
						<a href="<c:url value='/secury/buyServlet?cmd=chgnum&bookid=${entry.key}&num=-1'/>">-</a>&nbsp;
						${entry.value.cartcnt}&nbsp;
						<a href="<c:url value='/secury/buyServlet?cmd=chgnum&bookid=${entry.key}&num=1'/>">+</a>
					</td>
					<td>
						<fmt:formatNumber value="${entry.value.price*entry.value.discount*entry.value.cartcnt}" var="cal" pattern="#,###.00"/>
						${cal}
						<c:set value="${sum + cal}" var="sum" />
					</td>
					<td><a href="#">删除</a></td>
				</tr>
			</c:forEach>
			<tr><td colspan="5">&nbsp;</td></tr>
			<tr>
				<td colspan="3" style="text-align:left;">&nbsp;总&nbsp;计:</td>
				<td><fmt:formatNumber value="${sum}" pattern="#,###.00"/>元</td>
				<td><a href="<c:url value='/secury/orderServlet'/>">提交订单</a></td>
			</tr>
		</table>
	</div>
  </body>
</html>
