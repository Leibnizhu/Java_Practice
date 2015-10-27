<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>主页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		//通过DOM改变form表单的提交地址，并执行提交
		function login(){
			document.forms[0].action="<c:url value='/login'/>";
			document.forms[0].submit();
		}
		function reg(){
			document.forms[0].action="<c:url value='/register'/>";
			document.forms[0].submit();
		}
	</script>
  </head>
  
  <body>
  	<div align="center">
	    <p>主页</p>
	    <font color="red">
    		<c:choose>
    			<c:when test="${param.error == 1}">
    				用户名已存在，请尝试其他的
    			</c:when>
    			<c:when test="${param.error == 2}">
    				用户名或密码错误
    			</c:when>
    			<c:when test="${param.error == 3}">
    				该用户尚未激活，请前往邮箱查收激活邮件
    			</c:when>
    		</c:choose>
    	</font>
	    <form name="log" action="" method="post">
	    	用户名：<input type="text" name="name"/><br/>
	    	密&nbsp;码：<input type="text" name="pwd"/><br/>
	    	E-mail：<input type="text" name="email"/><br/>
	    	<input type="button" onclick="login()" value="登录"/>
	    	<input type="button" onclick="reg()" value="用以上信息注册"/> 
	    </form>
	</div>
  </body>
</html>
