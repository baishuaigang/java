<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.entity.User"%>
<%@page import="com.dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
request.setCharacterEncoding("utf-8");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改用户</title>
</head>
<%
String user=(String) session.getAttribute("user");
if(user==null){
	response.sendRedirect("login.jsp");
}
%>

<body>
	<form action="updateUser.do" method="post">
		<input type="text" name="uid" value="${user.uid}"
			style="display: none" /><br> 用户：<input type="text" name="user"
			value="${user.username}" /><br> 密码：<input type="text" name="pwd"
			value="${user.password }" /><br> 邮箱：<input type="text"
			name="email" value="${user.email }" /><br>
		<br> <input type="submit" value="submit">
	</form>
</body>
</html>