
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%
request.setCharacterEncoding("utf-8");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>列表</title>
<style type="text/css">
h1 {
	text-align: center;
}

tr, td {
	border: 1px solid #eaeaea;
	text-align: center
}

tr:hover {
	background: #ccc;
	opacity: 0.5
}

a {
	text-decoration: none;
}
</style>
</head>

<div style="width: 960px; margin: 0 auto;">
	<h1>员工信息列表</h1>
	<h4 style="text-align: right">
	<%
	String user=(String) session.getAttribute("user");
	if(user!=null){
	%>
		欢迎<span style="color: red"><%=user%></span>登陆 &nbsp;
		<a href="loginout.do">退出</a>
		<%
		}else{
			response.sendRedirect("login.jsp");
		}
			%>
	</h4>
</div>
<body>
	<table
		style="width: 960px; margin: 30px auto; border: 1px solid #ccc;; border-collapse: collapse">
		<tr>
			<td>ID</td>
			<td>用户</td>
			<td>密码</td>
			<td>邮箱</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${list}" var="list" varStatus="listindex">
			<tr>
				<td>${list.uid}</td>
				<td>${list.username}</td>
				<td>${list.password}</td>
				<td>${list.email}</td>
				<td><a onclick="del()" href="delete.do?uid=${list.uid }">删除</a>&nbsp;&nbsp;
					<a href="update.do?uid=${list.uid }">修改</a>&nbsp;&nbsp; <a
					href="add.jsp">添加</a>&nbsp;&nbsp;</td>
			</tr>
		</c:forEach>
	</table>
	<div style="width: 960px; margin: 30px auto; text-align: center">
		<a href="?curpage=1">首页</a>
		<c:forEach begin="${page.lpage}" end="${page.rpage}" var="pageNum">
			<a href="?curpage=${pageNum }">${pageNum }</a>
		</c:forEach>
		<a href="?curpage=${page.last }">尾页</a>
	</div>
</body>
</html>