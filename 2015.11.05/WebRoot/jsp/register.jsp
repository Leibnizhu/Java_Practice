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
			width:250px;
		}
		.txt{
			border:1px solid gray;
			width:100%;
		}
	</style>
	<script type="text/javascript">
		function regcheck(){
			var msg  = document.getElementById("msg");
			//判断用户名是否为空
			var nm  = document.getElementById("name").value;
			nm = nm.trim();
			if(nm==""){
				msg.innerHTML="用户名为空，请重新输入...";
				return false;
			}
			//判断密码
			var pswd1 = document.getElementsByName("pswd")[0].value;
			var pswd2 = document.getElementsByName("pswd2")[0].value;
			//判断密码是否为空
			if("" == pswd1){
				msg.innerHTML="密码为空，请输入...";
			}
			//判断两次输入密码是否一致
			if(pswd1 != pswd2){
				msg.innerHTML="两次输入的密码不一致，请重新输入...";
				return false;
			}
			//判断输入的邮件格式
			var em = document.getElementsByName("email")[0].value;
			var pattern = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
			if(pattern.test(em)){
				document.forms[0].submit();
			} else {
				msg.innerHTML="邮箱不正确，请重新输入...";
				return false;
			}
		}
		
		function refresh(obj){
            obj.src = obj.src + "?code=" + Math.random();
}
	</script>
  </head>
  
  <body style="text-align:center;margin-top:0px;padding:10%;">
  	<div style="background:#F8F8F8;margin:0 auto;">
	  	<form method="post" action="<c:url value='/userServlet'/>">
	  		<table align="center">
		  		
	  			<tr>
	  				<td colspan="2" align="center" style="height:20px;">用户注册</td>
	  			</tr>
	  			<tr>
	  				<td colspan="2" id="msg" align="center" style="height:20px;color:red;font-size: 1">
	  					<c:if test="${not empty sessionScope.errorMsg}">
	  						${sessionScope.errorMsg}
	  						<c:remove var="errorMsg" scope="session"/>
	  					</c:if>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td style="width: 80px;">用户名</td>
	  				<td><input class="txt" id="name" type="text" name="name" value="${user.name}"/></td>
	  			</tr>
	  			<tr>
	  				<td style="width: 80px;">密&nbsp;码</td>
	  				<td><input class="txt" type="password" name="pswd"/></td>
	  			</tr>
	  			<tr>
	  				<td style="width: 80px;">确认密码</td>
	  				<td><input class="txt" type="password" name="pswd2"/></td>
	  			</tr>
	  			<tr>
	  				<td style="width: 80px;">邮箱</td>
	  				<td><input class="txt" type="text" name="email"/></td>
	  			</tr>
	  			<tr>
	  				<td style="width: 80px;">验证码</td>
	  				<td>
	  					<input style="width: 60px;" type="text" name="checkcode" />
	  					<img src="<c:url value='/getCheckCode' />"  onclick="refresh(this)"/>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td colspan="2" align="center" style="height:20px;">
	  					<input type="button" onclick="regcheck()" value="注册"/>
	  				</td>
	  			</tr>
	  		</table>
	  	</form>
	 </div>
  </body>
</html>
