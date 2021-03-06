<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
	<head>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<style>
		BODY {SCROLLBAR-FACE-COLOR: #cccccc; SCROLLBAR-HIGHLIGHT-COLOR: #ffffFF; SCROLLBAR-SHADOW-COLOR: #ffffff; SCROLLBAR-3DLIGHT-COLOR: #cccccc; SCROLLBAR-ARROW-COLOR:  #ffffff; SCROLLBAR-TRACK-COLOR: #ffffFF; SCROLLBAR-DARKSHADOW-COLOR: #cccccc; }
	</style>
	
	<script type="text/javascript">
		function addUser(){
			window.location.href="${pageContext.request.contextPath}/jsps/add.jsp";
		}
	</script>
</head>

<body>

<s:form id="form1" name="form1" namespace="/user" action="UserAction_select" method="post">
			<table>
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>查 询 条 件</strong>
						</td>
					</tr>
					<tr>
						<td>
							<table>
								<tr>
									<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
										用户姓名
									</td>
									<td class="ta_01" bgColor="#ffffff">
										<s:textfield name="userName" id="userName" size="15" cssClass="bg" />
									</td>
									<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
										性别：
									</td>
									<td class="ta_01" bgColor="#ffffff">
										<s:select list="{'男','女'}" headerKey="" headerValue="---不限---" 
												name="sex" id="sex"></s:select>

									</td>
								</tr>
								<tr>
									<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
										学历：
									</td>
									<td class="ta_01" bgColor="#ffffff">
										<s:select list="{'博士','硕士','研究生','本科','专科','高中'}"
											headerKey="" headerValue="---不限---" name="education" id="education"></s:select>

									</td>
									<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
										是否上传简历
									</td>
									<td class="ta_01" bgColor="#ffffff">
										<s:select list="#{1:'有',0:'无'}" listKey="key" listValue="value"
											headerKey="" headerValue="---不限---" name="resume" id="resume"></s:select>

									</td>
								</tr>
								<tr>
									<td width="100" height="22" align="center" bgColor="#f5fafe"
										class="ta_01">
									</td>
									<td class="ta_01" bgColor="#ffffff">
										<font face="宋体" color="red"> &nbsp;</font>
									</td>
									<td align="right" bgColor="#ffffff" class="ta_01"><br><br></td>
									<td align="right" bgColor="#ffffff" class="ta_01">
										<s:submit type="button" id="search" name="search" value="查询"></s:submit>
										
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<s:reset type="button" name="reset" id="reset" value="重置"></s:reset>

									</td>
								</tr>
							</table>
						</td>

					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>用 户 列 表</strong>
						</TD>
					</tr>
					<tr>
						<td class="ta_01" align="right">
							<button type="button" id="add" name="add" value="&#28155;&#21152;" class="button_add" onclick="addUser()">
&#28155;&#21152;
</button>

						</td>
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table  id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" width="18%">
										登录名
									</td>
									<td align="center" width="17%">
										用户姓名
									</td>
									<td align="center" width="8%">
										性别
									</td>
									<td align="center" width="23%">
										联系电话
									</td>
									<td width="11%" align="center">
										学历
									</td>
									<td width="7%" align="center">
										编辑
									</td>
									<td width="7%" align="center">
										查看
									</td>
									<td width="7%" align="center">
										删除
									</td>
								</tr>
									<s:iterator value="#request.userList" var="user">
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="18%">
												<s:property value="#user.loginName"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												<s:property value="#user.userName"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="8%">
												<s:property value="#user.sex"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="23%">
												<s:property value="#user.telephone"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center">
												<s:property value="#user.education"/>
											</td>
											<td align="center" style="HEIGHT: 22px">
												<a href="${pageContext.request.contextPath}/user/UserAction_edit.action?userID=<s:property value="#user.userID"/>">
													<img src="${pageContext.request.contextPath}/imgs/i_edit.gif" border="0" style="CURSOR: hand">
												</a>
											</td>
											<td align="center" style="HEIGHT: 22px">
												<a href="${pageContext.request.contextPath}/user/UserAction_view.action?userID=<s:property value="#user.userID"/>">
													<img src="${pageContext.request.contextPath}/imgs/button_view.gif" border="0" style="CURSOR: hand">
												</a>
											</td>
											<td align="center" style="HEIGHT: 22px">
												<a href="${pageContext.request.contextPath}/user/UserAction_delete.action?userID=<s:property value="#user.userID"/>">
													<img src="${pageContext.request.contextPath}/imgs/i_del.gif" width="16" height="16" border="0" style="CURSOR: hand">
												</a>
											</td>
										</tr>
									</s:iterator>
							</table>
						</td>
					</tr>
				</TBODY>
			</table>
		</s:form>

</body>

</html>