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
		td{
			text-align:center;
			border:1px solid gray;
		}
	</style>
  </head>
  
  <body style="margin-top:0px;padding:10%;">
  	<div style="margin:0 auto;">
		<c:forEach items="${orderlist}" var="order">
			<table>
				<tr>
					<td colspan="4" style="text-align:left;">订单编号：${order.id}</td>
				</tr>
				<tr>
					<td style="text-align:left;">创建日期：</td><td>${order.crtime}</td>
					<td style="text-align:left;">状态：</td><td>
						<c:choose>
							<c:when test="${order.status == 0  }">新建订单</c:when>
							<c:when test="${order.status == 1  }">已发货</c:when>
							<c:when test="${order.status == 2  }">已收货</c:when>
							<c:when test="${order.status == 3  }">已评价</c:when>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td style="text-align:left;">送货地址：</td>
					<td colspan="3" style="text-align:left;">${order.addr}</td>
				</tr>
				<tr>
					<td colspan="4">订单明细</td>
				</tr>
				<tr>
					<td>书名</td>
					<td>单价(元)</td>
					<td>数量</td>
					<td>小计(元)</td>
				</tr>
				<c:set value="0" var="sum"/>
				<c:forEach items="${order.details}" var="detail">
					<tr>
						<td>${detail.bookname}</td>
						<td>${detail.price}</td>
						<td>${detail.counts}</td>
						<td>${detail.price * detail.counts}</td>
						<c:set value="${sum + detail.price * detail.counts}" var="sum" />
					</tr>
				</c:forEach>
				<tr>
					<td colspan="3" style="text-align:left;">&nbsp;总&nbsp;计：</td>
					<td>${sum}</td>
				</tr>
			</table>
			<hr/>
		</c:forEach>
	</div>
  </body>
  <script type="text/javascript">
  	
  </script>
</html>
