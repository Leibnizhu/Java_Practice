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
  		
		.img{
			border:0px solid gray;
			width:160px;
			height:160px;
		}
	</style>
  </head>
  
  <body>
	<div style="margin:0 auto;width:900px;align:center;text-align: center;">
		<table width=80% border="0">
			<tr>
				<td style="width:200px;">
					<img class="img" src="<c:url value='/imgs/${bookDetail.image}'/>"/>
				</td>
				<td valign="bottom">
					书名:${bookDetail.name}<br/>
	  				价格:￥<c:choose>
			  				<c:when test="${bookDetail.discount == 1}">
			  					${bookDetail.price} 
			  				</c:when>
			  				<c:otherwise>
			  					<font style="text-decoration: line-through;">${bookDetail.price}</font>
			  					<font style="color:red;"><b><fmt:formatNumber value="${bookDetail.price*bookDetail.discount}" pattern="#,###.00"/></b></font>
			  				</c:otherwise>
			  			</c:choose>元<br/>
	  				作者:${bookDetail.author}<br/>
	  				出版:${bookDetail.publisher}<br/>
	  				出版日期:${bookDetail.publishdate}<br/>
	  				库存:${bookDetail.stock}<br/>
	  				<a href="<c:url value='/secury/buyServlet?bookid=${bookDetail.id}'/>"><img src="<c:url value='/imgs/buy.png'/>"></img></a>
	  				<br/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<hr/>
					<div style="background:#EEDDEE;">${bookDetail.intro}</div><hr/>
					<div style="background:#BBDDEE;">${bookDetail.directory}</div>
				</td>
			</tr>
		</table>
	</div>
  </body>
</html>
