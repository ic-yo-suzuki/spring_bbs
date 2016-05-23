<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored = "false" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ログイン</title>

</head>
<body>

	<div class = "login-screen">

		<h3>掲示板　ログイン</h3>
		<h2>${message}</h2>
		<form:form modelAttribute="loginForm">
			<div><form:errors path="*"  /></div>
			ユーザID：<form:input path="loginId" /><br>
			パスワード：<form:password path="password" />
			 <input type="submit">
		</form:form>



	</div>

</body>
</html>