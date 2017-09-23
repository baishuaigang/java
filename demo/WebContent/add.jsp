<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
request.setCharacterEncoding("utf-8");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加用户</title>
<%
String user=(String) session.getAttribute("user");
if(user==null){
	response.sendRedirect("login.jsp");
}
%>
</head>
<body>
	<form action="addUser.do" method="post">
		用户：<input type="text" name="user" /><br> 密码：<input
			type="password" name="pwd" /><br> 邮箱：<input type="text"
			name="email" /><br>
		<br> <input type="submit" value="submit">
	</form>
</body>
</html>