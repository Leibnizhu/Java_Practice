<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<script type="text/javascript">
		function init(){
		   document.getElementById("logname").focus();
		}
	</script>
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
  <body style="text-align:center;margin-top:0px;padding:10%;" onload="init()">
  	<div style="background:#D8E8F8;margin:0 auto;">
  		<s:form id="form1" name="form1" action="/login/LoginAction_login.action" target="_parent" method="post">
	  		<table align="center">
	  			<tr>
	  				<td colspan="2" align="center" style="height:20px;">用户登录</td>
	  			</tr>
	  			<tr>
					<td height="30" nowrap colspan="2">
						<strong><font color="red"><s:fielderror /></font> </strong>
					</td>
				</tr>
	  			<tr>
	  				<td>用户名</td>
	  				<td><s:textfield name="logname" id="logname"/></td>
	  			</tr>
	  			<tr>
	  				<td>密&nbsp;码</td>
	  				<td><s:password name="logpswd" id="logpswd"/></td>
	  			</tr>
	  			<tr>
	  				<td colspan="2" align="center" style="height:20px;">
	  					<s:submit type="button" name="submit" value="登录"></s:submit>
	  					<s:reset type="button" name="reset" value="重置"></s:reset>
	  				</td>
	  			</tr>
	  		</table>
	  	</s:form>
	 </div>
  </body>
</html>
