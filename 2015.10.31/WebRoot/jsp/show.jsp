<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
	<style type="text/css">
		*{
			font-size:9pt;
		}
		.frame{
			text-align:center;
			border:1px solid red;
			margin:3px;
			float: left;
		}
	</style>
	<script type="text/javascript">
		function down(oname, nname){
			var url = "<c:url value='/downServlet?oldname='/>" + oname + "&newname=" + nname;
			//alert(url);
			window.location.href=url;
		}
	</script>
  </head>
  
  <body>
    <div align="center">
    	<h1>所有照片</h1>
    	<c:forEach items="${images}" var="img">
    		<div align="center" class="frame">
    			<a href="<c:url value='/up/${img.newname}'/>"><img src="<c:url value='/up/${img.newname}'/>" style="width:200px;height:200px;"/></a><br/>
    			<c:choose>
    				<c:when test="${fn:length(img.oldname) > 15}">
    					${fn:substring(img.oldname,0,15)}...
    				</c:when>
    				<c:otherwise>
    					${img.oldname}
    				</c:otherwise>
    			</c:choose>
    			<br/>
    			<c:choose>
    				<c:when test="${fn:length(img.descript) > 15}">
    					${fn:substring(img.descript,0,15)}...
    				</c:when>
    				<c:otherwise>
    					${img.descript}
    				</c:otherwise>
    			</c:choose>
    			<br/>
    			<a href="<c:url value='/delServlet?id=${img.id}'/>">删除</a>
    			<a href="javascript:down('${img.oldname}', '${img.newname }');">下载</a>
    		</div>
    	</c:forEach>
    </div>
  </body>
</html>
