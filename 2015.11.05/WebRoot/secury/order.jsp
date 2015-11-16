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
		<table >
			<tr>
				<td colspan="4">
					请选择收货地址：
				</td>
			</tr>
			<tr>
				<td>选择</td>
				<td>姓名</td>
				<td>电话</td>
				<td>地址</td>
			</tr>
			<c:forEach items="${addrlist}" var="addr">
				<tr>
					<td>
						<c:choose>
							<c:when test="${addr.isdef == 1}">
								<input type="radio" onclick="check(this);" value="${addr.id}" name="id" checked="checked">
							</c:when>
							<c:otherwise>
								<input type="radio" onclick="check(this);" value="${addr.id}" name="id">
							</c:otherwise>
						</c:choose>
					</td>
					<td>${addr.name}</td>
					<td>${addr.phone}</td>
					<td>${addr.addr}</td>
				</tr>
			</c:forEach>
		</table>
		
		<hr/>
		收货方式：
  		<select name="paytype">
			<option value="1">货到付款</option>
			<option value="2">在线支付</option>
  		</select>
  		
 		<hr/>
		<table>
			<tr>
				<td colspan="4">
					以下是您购买的所有书籍：
				</td>
			</tr>
			<tr>
				<td>书名</td>
				<td>单价(元)</td>
				<td>数量</td>
				<td>小计(元)</td>
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
					<td>${entry.value.cartcnt}</td>
					<td>
						<fmt:formatNumber value="${entry.value.price*entry.value.discount*entry.value.cartcnt}" var="cal" pattern="#,###.00"/>
						${cal}
						<c:set value="${sum + cal}" var="sum" />
					</td>
				</tr>
			</c:forEach>
			<tr><td colspan="4">&nbsp;</td></tr>
			<tr>
				<td colspan="3" style="text-align:left;">&nbsp;总&nbsp;计:</td>
				<td><fmt:formatNumber value="${sum}" pattern="#,###.00"/>元</td>
			</tr>
			<tr>
				<td colspan="4" align="right">
					<form name="sub" action="<c:url value='/secury/orderServlet?cmd=create'/>" method="post">
						<input id="addr" type="hidden" name="addr">
						<img src="<c:url value='/imgs/order.png'/>" onclick="create();">
					</form>
				</td>
			</tr>
		</table>
	</div>
  </body>
  <script type="text/javascript">
  	//因为提交订单的表单与地址选择不在同一个表单里，所以通过一个空间和javascript函数来传递
  	function check(obj){
  		if(true == obj.checked){
  			document.getElementById("addr").value = obj.value;
  		}
  	}
  	
  	//用于提交订单
  	function create(){
  		document.forms['sub'].submit();
  	}
  </script>
</html>
