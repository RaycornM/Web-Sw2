<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<% 
	String uname = request.getParameter("uname");
//request等是jsp的内置对象，内置对象9个
%>
<body>
	主页面 欢迎你:<%=uname %>
	<!--  -->
</body>
</html>